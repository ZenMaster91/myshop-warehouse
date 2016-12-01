package com.myshop.warehouse.validators;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controllers.MailBoxController;
import com.myshop.warehouse.util.DefaultSql2o;

public class EmpaquetadoValidator implements Validator {

	private List<OrderItem> items;
	private String toValidate;
	private int quantity;
	private MailBoxController mb;

	public EmpaquetadoValidator(List<OrderItem> items) {
		this.items = items;
	}

	public EmpaquetadoValidator(List<OrderItem> items, String toValidate, int quantity, MailBoxController mb) {
		this.items = items;
		this.toValidate = toValidate;
		this.quantity = quantity;
		this.mb = mb;
	}

	@Override
	public boolean validate() {
		for (OrderItem oi : items) {
			if (Integer.toString(oi.getProduct().getID()).equals(toValidate)
					&& oi.getQuantity() >= oi.itemsPackaged + quantity) {
				if (oi.getParent().getID() == mb.order.getID()) {
					oi.itemsPackaged = oi.itemsPackaged + quantity;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public OrderItem validateRet() {
		for (OrderItem oi : items) {
			if (Integer.toString(oi.getProduct().getID()).equals(toValidate)
					&& oi.getQuantity() >= oi.itemsPackaged + quantity) {
				if (oi.getParent().getID() == mb.order.getID()) {
					oi.itemsPackaged = oi.itemsPackaged + quantity;
					String sql = "UPDATE myshop.order_item AS oi SET oi.items_packaged = :items WHERE oi.order_item_id = :id";

					try (Connection con = DefaultSql2o.SQL2O.open()) {
						Query query = con.createQuery(sql);
						query.addParameter("items", oi.itemsPackaged).addParameter("id", oi.getID()).executeUpdate();
					}

					return oi;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	public boolean validate(int productID) {
		// toValidate.setID(productID);;
		return this.validate();
	}

}
