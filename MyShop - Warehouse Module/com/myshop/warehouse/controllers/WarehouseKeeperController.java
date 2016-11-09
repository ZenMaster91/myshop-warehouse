package com.myshop.warehouse.controllers;

import java.util.ArrayList;
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
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.util.DefaultSql2o;

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
		String complexSQL = "SELECT  p.corridor, p.position, p.height, p.side, p.product_id, wp.wp_id, "
				+ "p.stock, p.name, p.description as 'p_desc', p.weight, p.price, o.order_id, p.company_price, "
				+ "p.category, i.incidence_id, i.description as 'i_desc', i.solved, oi.mail_box_id, oi.order_item_id, s.name AS 'status', "
				+ "oi.quantity, wpi.collected " + "FROM myshop.working_plan AS wp "
				+ "LEFT JOIN myshop.working_plan_item AS wpi ON wp.wp_id = wpi.wp_id "
				+ "LEFT JOIN myshop.order_item AS oi ON wpi.order_item_id = oi.order_item_id "
				+ "LEFT JOIN myshop.order AS o ON oi.order_id = o.order_id "
				+ "LEFT JOIN myshop.status AS s ON s.status_id = o.status_id "
				+ "LEFT JOIN myshop.full_products as p on oi.product_id=p.product_id "
				+ "LEFT JOIN myshop.incidence as i on oi.incidence_id=i.incidence_id "
				+ "WHERE wk_id = :id AND collected = 0 ORDER BY wp.wp_id";

		List<Map<String, Object>> map = null;
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			map = con.createQuery(complexSQL).addParameter("id", wk.getID()).executeAndFetchTable().asList();
		}
		OrderItem oi;
		Product p;
		ProductLocation pl;
		Incidence i;
		MailBox mB;
		WorkingPlanController wpc = null;
		int lastWpID = -1, nItem = 0;

		for (Map<String, Object> m : map) {
			if (nItem == 0) {
				lastWpID = (int) m.get("wp_id");
				wpc = new WorkingPlanController();
			}
			if (nItem == 1 && (int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController();
			}
			nItem++;

			pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"), (int) m.get("height"),
					Side.valueOf(((String) m.get("side")).toUpperCase()));

			p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("p_desc"), (double) m.get("weight"), (double) m.get("price"),
					new Category((String) m.get("category")), null, pl, (double) m.get("company_price"));
			System.out.println(p.getID() + " " + p.getName() + " processed.");

			if (m.get("incidence_id") != null) {
				i = new Incidence((int) m.get("incidence_id"), (String) m.get("i_desc"), (boolean) m.get("solved"));
			} else {
				i = null;
			}

			if (m.get("mail_box_id") != null) {
				mB = new MailBox((int) m.get("mail_box_id"));
			} else {
				mB = null;
			}

			oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, i, mB);
			Order o = new Order();
			o.setID((int) m.get("order_id"));
			o.setStatus(((String)m.get("status")).toUpperCase());
			oi.setParent(o);
			if ((int) m.get("collected") == 1)
				wpc.addItem(new WorkingPlanItem(oi, true));
			else
				wpc.addItem(new WorkingPlanItem(oi, false));

			if ((int) m.get("wp_id") != lastWpID) {
				wpc.getWp().setID((int) m.get("wp_id"));
				wpc.assignWareHouseKeeper(wk);
				wpc.print();
				toReturn.add(wpc);
				wpc = new WorkingPlanController();

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

		return toReturn;
	}

	public boolean isOcupied(WarehouseKeeper wk) {
		return getCurrentWorkingPlan(wk) != null;
	}

}
