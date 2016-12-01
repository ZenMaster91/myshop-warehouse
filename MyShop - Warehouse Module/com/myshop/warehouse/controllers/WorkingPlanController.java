package com.myshop.warehouse.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.OrderItem;
import com.myshop.model.order.Status;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.util.DefaultSql2o;
import com.myshop2.maths.FactorEstiba;
import com.myshop2.sql.QueryLoader;

public class WorkingPlanController implements Comparable<WorkingPlanController> {

	private WorkingPlan wp;
	private List<WorkingPlanController> childs;
	private boolean isChild = false;
	private WorkingPlanController parent;

	public WorkingPlanController(int ID) {
		childs = new ArrayList<WorkingPlanController>();
		wp = new WorkingPlan(ID, null, new ArrayList<WorkingPlanItem>());
	}

	public WorkingPlanController(WorkingPlan wp) {
		childs = new ArrayList<WorkingPlanController>();
		this.wp = wp;
	}

	/**
	 * @return the wp
	 */
	public WorkingPlan getWp() {
		return wp;
	}

	/**
	 * @param wp
	 *            the wp to set
	 */
	public void setWp(WorkingPlan wp) {
		this.wp = wp;
	}

	/**
	 * @return all the items in the working plan.
	 */
	public List<WorkingPlanItem> getItems() {
		return this.wp.getItems();
	}

	/**
	 * @return the number of items in the list.
	 */
	public int getNumberOfItems() {
		int cant = 0;
		for(WorkingPlanItem wpi : this.wp.getItems()) {
			cant += wpi.getOrderItem().getQuantity();
		}
		return cant;
	}

	/**
	 * @param orderItem
	 *            item to add to the wpc.
	 */
	public WorkingPlanController addItem(OrderItem orderItem) {
		this.wp.getItems().add(new WorkingPlanItem(orderItem, false));
		System.out.println(">> Adding Item: " + orderItem.getID() + " to WP: " +this.wp.getID());
		return this;
	}
	
	/**
	 * @param orderItem
	 *            item to add to the wpc.
	 */
	public WorkingPlanController addItem(WorkingPlanItem wpi) {
		this.wp.getItems().add(wpi);
		return this;
	}
	
	

	/**
	 * @param items
	 *            items to be added.
	 */
	public WorkingPlanController addAll(List<OrderItem> items) {
		for (OrderItem oi : items) {
			addItem(oi);
		}
		return this;
	}

	/**
	 * @param wk
	 *            warehouse keeper.
	 */
	public WorkingPlanController assignWareHouseKeeper(WarehouseKeeper wk) {
		wp.setWarehouseKeeper(wk);
		return this;
	}

	/**
	 * @return the total weight of the order.
	 */
	public double getTotalWeight() {
		double weight = 0.0;
		for (WorkingPlanItem wpi : this.wp.getItems()) {
			OrderItem oi = wpi.getOrderItem();
			weight += oi.getProduct().getWeight();
		}
		return weight;
	}
	
	/**
	 * @return the total volume of the order.
	 */
	public double getTotalVolume() {
		double volume = 0.0;
		for (WorkingPlanItem wpi : this.wp.getItems()) {
			OrderItem oi = wpi.getOrderItem();
			volume += oi.getProduct().getDimensions().calculateVolume();
		}
		return volume;
	}

	/**
	 * @return the splitted
	 */
	public boolean isChild() {
		return isChild;
	}

	public List<WorkingPlanController> getChilds() {
		return childs;
	}

	public WorkingPlanController addChild(WorkingPlanController child) {
		this.childs.add(child);
		return this;
	}

	public WorkingPlanController setParent(WorkingPlanController parent) {
		this.isChild = true;
		parent.addChild(this);
		this.parent = parent;
		return this;
	}

	public WorkingPlanController getParent() {
		return parent;
	}

	public String getStatus() {
		return this.getWp().getItems().get(0).getOrderItem().getParent().getStatus();
	}

	@Override
	public int compareTo(WorkingPlanController o) {
		return ((Integer) this.wp.hashCode()).compareTo(o.getWp().hashCode());
	}
	
	public void assignWK(WarehouseKeeper wk, WorkingPlanController... wpcs) {
		for(WorkingPlanController wpc : wpcs) {
			assign(wpc, wk);
		}
	}

	public void assign(WorkingPlanController wpc, WarehouseKeeper almacenero) {

		// WorkingPlan wp = new WorkingPlan(-1, almacenero, wpc.getItems());
		wpc.assignWareHouseKeeper(almacenero);
		System.out.println("WPC has: " + wpc.getItems().size() + " Items");
		wpc.getItems().get(0).getOrderItem().getParent().setStatus(Status.PREPARANDO.toString().toUpperCase());
		String updateStatus = "UPDATE myshop.order SET myshop.order.status_id=2 WHERE myshop.order.order_id = :order_id";
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(updateStatus).addParameter("order_id", wpc.getItems().get(0).getOrderItem().getParent().getID()).executeUpdate();
		}

		String insertWorkingPlan = "INSERT INTO myshop.working_plan (wk_id) VALUES (:wk_id)";

		Integer wp_id = -1;

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			wp_id = (int) (long) con.createQuery(insertWorkingPlan, true).addParameter("wk_id", almacenero.getID())
					.executeUpdate().getKey();
		}
		System.out.println(">>> Created the WorkingPlan: " + wp_id);
		String insertWorkingPlanItem = "UPDATE myshop.order_item SET myshop.order_item.working_plan_id = :wp_id, myshop.order_item.collected = :col WHERE myshop.order_item.order_item_id = :oi_id";
		try (Connection con = DefaultSql2o.SQL2O.beginTransaction()) {
			Query query = con.createQuery(insertWorkingPlanItem);

			for (WorkingPlanItem wpi : wpc.getItems()) {
				System.out.println(">>>> Assigning WP:" + wp_id + " to OrderItem_ID: " + wpi.getOrderItem().getID());
				query.addParameter("oi_id", wpi.getOrderItem().getID()).addParameter("wp_id", wp_id)
						.addParameter("col", false).addToBatch();
			}

			query.executeBatch(); // executes entire batch
			con.commit(); // remember to call commit(), else sql2o will
							// automatically rollback.
		}
		wpc.getWp().setID(wp_id);
	}

	public List<WorkingPlanController> asController(List<WorkingPlan> wps) {
		List<WorkingPlanController> aux = new ArrayList<WorkingPlanController>();
		
		for(WorkingPlan wp : wps) {
			WorkingPlanController wpc = new WorkingPlanController(wp);
			aux.add(wpc);
		}
		
		return aux;
		
	}

	public void print() {
		System.out.println("WorkingPlanController: " + this.getWp().getID() + ". With: " + this.getChilds().size() + " children. And parent id: " + this.getParent() + " Nº Products: " +this.getNumberOfItems() + " and Weight: " +this.getTotalWeight());
	}
	
	public List<WorkingPlanController> toList() {
		List<WorkingPlanController> aux = new ArrayList<WorkingPlanController>();
		aux.add(this);
		return aux;
	}
	
	public boolean canAdd(OrderItem oi) {
		return new FactorEstiba((getTotalVolume() + (oi.getProduct().getDimensions().calculateVolume()*oi.getQuantity())),
				
				(getTotalWeight() + (oi.getProduct().getWeight()*oi.getQuantity()))).validate();
	}

}
