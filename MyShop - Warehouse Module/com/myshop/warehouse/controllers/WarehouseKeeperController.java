package com.myshop.warehouse.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;

import com.myshop.model.order.Incidence;
import com.myshop.model.order.MailBox;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.product.Category;
import com.myshop.model.product.Product;
import com.myshop.model.product.ProductLocation;
import com.myshop.model.product.Side;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.util.DefaultSql2o;
import com.myshop2.sql.QueryLoader;

public class WarehouseKeeperController {

	public List<WarehouseKeeper> getAll() {
		String complexSql = "SELECT * FROM myshop.warehouse_keeper";
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			return con.createQuery(complexSql).addColumnMapping("wk_id", "ID").executeAndFetch(WarehouseKeeper.class);
		}
	}

	public List<WarehouseKeeper> getByID(String id) {
		String complexSql = "SELECT * FROM myshop.warehouse_keeper as w_k WHERE w_k.wk_id = :id";
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			return con.createQuery(complexSql).addParameter("id", id).addColumnMapping("wk_id", "ID")
					.executeAndFetch(WarehouseKeeper.class);
		}
	}

	public List<WorkingPlanController> getCurrentWorkingPlan(WarehouseKeeper wk) {
		List<WorkingPlanController> toReturn = new ArrayList<WorkingPlanController>();
		List<Map<String, Object>> map = null;
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			map = con.createQuery(QueryLoader.load("CurrentWorkingPlan.sql"))
						.addParameter("id", wk.getID()).executeAndFetchTable().asList();
		}
		OrderItem oi;
		Product p;
		ProductLocation pl;
		Incidence i;
		MailBox mB;
		WorkingPlanController wpc = null;
		int lastWpID = -1, nItem = 0;
		System.out.println(1);
		for (Map<String, Object> m : map) {
			if (nItem == 0) {
				lastWpID = (int) m.get("wp_id");
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
			}
			if (nItem == 1 && (int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
			}
			System.out.println(2);
			nItem++;

			pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"), (int) m.get("height"),
					Side.valueOf(((String) m.get("side")).toUpperCase()));
			System.out.println(3);
			p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("p_desc"), (double) m.get("weight"), (double) m.get("price"),
					new Category((String) m.get("category")), null, pl, (double) m.get("company_price"));
			System.out.println(p.getID() + " " + p.getName() + " processed.");
			System.out.println(4);
			if (m.get("incidence_id") != null) {
				i = new Incidence((int)m.get("incidence_id"), (String) m.get("i_desc")).setSolve((boolean) m.get("solved"));
			} else {
				i = null;
			}
			System.out.println(5);
			if (m.get("mail_box_id") != null) {
				mB = new MailBox((int) m.get("mail_box_id"));
			} else {
				mB = null;
			}
			System.out.println(6);
			oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, i, mB);
			System.out.println(5.11);
			Order o = new Order();
			o.setID((int) m.get("order_id"));
			System.out.println(5.12);
			o.setStatus(((String)m.get("status")).toUpperCase());
			System.out.println(5.13);
			oi.setParent(o);
			System.out.println(5.1);
			if ((int) m.get("collected") == 1)
				wpc.addItem(new WorkingPlanItem(oi, true, (int) m.get("items_collected")));
			else
				wpc.addItem(new WorkingPlanItem(oi, false, (int) m.get("items_collected")));
			System.out.println(5.2);
			if ((int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
				System.out.println(5.3);
				/*
				 * wpc = new WorkingPlanController(new WorkingPlan((int)
				 * m.get("wp_id"), wk, new ArrayList<WorkingPlanItem>()));
				 * toReturn.add(wpc); wpc.print(); System.out.print("  this");
				 * //items = new ArrayList<WorkingPlanItem>();
				 */
			}
			/*
			 * wpc = new WorkingPlanController(new WorkingPlan((int)
			 * m.get("wp_id"), wk, new ArrayList<WorkingPlanItem>()));
			 * System.out.println(nItem + " == " + map.size() + " AND " +
			 * items.size());
			 */
			if (nItem == map.size() && wpc.getNumberOfItems() > 0) {
				toReturn.add(wpc);
				wpc.print();
			}

			lastWpID = (int) m.get("wp_id");
		}
		System.out.println(6);
		return toReturn;
	}
	
	public List<WorkingPlanController> getCurrentWorkingPlan(WarehouseKeeper wk, WorkingPlan wp) {
		List<WorkingPlanController> toReturn = new ArrayList<WorkingPlanController>();
		List<Map<String, Object>> map = null;
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			map = con.createQuery(QueryLoader.load("CurrentWorkingPlan.sql"))
						.addParameter("id", wk.getID())
						.addParameter("wpid", wp.getID()).executeAndFetchTable().asList();
		}
		OrderItem oi;
		Product p;
		ProductLocation pl;
		Incidence i;
		MailBox mB;
		WorkingPlanController wpc = null;
		int lastWpID = -1, nItem = 0;
		System.out.println(1);
		for (Map<String, Object> m : map) {
			if (nItem == 0) {
				lastWpID = (int) m.get("wp_id");
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
			}
			if (nItem == 1 && (int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
			}
			System.out.println(2);
			nItem++;

			pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"), (int) m.get("height"),
					Side.valueOf(((String) m.get("side")).toUpperCase()));
			System.out.println(3);
			p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("p_desc"), (double) m.get("weight"), (double) m.get("price"),
					new Category((String) m.get("category")), null, pl, (double) m.get("company_price"));
			System.out.println(p.getID() + " " + p.getName() + " processed.");
			System.out.println(4);
			if (m.get("incidence_id") != null) {
				i = new Incidence((int)m.get("incidence_id"), (String) m.get("i_desc")).setSolve((boolean) m.get("solved"));
			} else {
				i = null;
			}
			System.out.println(5);
			if (m.get("mail_box_id") != null) {
				mB = new MailBox((int) m.get("mail_box_id"));
			} else {
				mB = null;
			}
			System.out.println(6);
			oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, i, mB);
			System.out.println(5.11);
			Order o = new Order();
			o.setID((int) m.get("order_id"));
			System.out.println(5.12);
			o.setStatus(((String)m.get("status")).toUpperCase());
			System.out.println(5.13);
			oi.setParent(o);
			System.out.println(5.1);
			if ((int) m.get("collected") == 1)
				wpc.addItem(new WorkingPlanItem(oi, true, (int) m.get("items_collected")));
			else
				wpc.addItem(new WorkingPlanItem(oi, false, (int) m.get("items_collected")));
			System.out.println(5.2);
			if ((int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController((int) m.get("wp_id"));
				wpc.getWp().setGenerated(((Date) m.get("date_created")));
				System.out.println(5.3);
				/*
				 * wpc = new WorkingPlanController(new WorkingPlan((int)
				 * m.get("wp_id"), wk, new ArrayList<WorkingPlanItem>()));
				 * toReturn.add(wpc); wpc.print(); System.out.print("  this");
				 * //items = new ArrayList<WorkingPlanItem>();
				 */
			}
			/*
			 * wpc = new WorkingPlanController(new WorkingPlan((int)
			 * m.get("wp_id"), wk, new ArrayList<WorkingPlanItem>()));
			 * System.out.println(nItem + " == " + map.size() + " AND " +
			 * items.size());
			 */
			if (nItem == map.size() && wpc.getNumberOfItems() > 0) {
				toReturn.add(wpc);
				wpc.print();
			}

			lastWpID = (int) m.get("wp_id");
		}
		System.out.println(6);
		return toReturn;
	}

	public boolean isOcupied(WarehouseKeeper wk) {
		return getCurrentWorkingPlan(wk) != null;
	}

}
