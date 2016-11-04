package com.myshop.warehouse.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;

import com.myshop.model.customer.Address;
import com.myshop.model.customer.Company;
import com.myshop.model.customer.CreditCards;
import com.myshop.model.customer.Customer;
import com.myshop.model.customer.IndividualCustomer;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.product.Category;
import com.myshop.model.product.Product;
import com.myshop.model.product.ProductLocation;
import com.myshop.model.product.Side;
import com.myshop.model.user.User;
import com.myshop.warehouse.util.DefaultSql2o;

public class OrderController {

	public double getWeight(Order o) {
		double aux = 0.0;
		for (OrderItem oi : o.getProducts()) {
			aux += oi.getProduct().getWeight();
		}
		return aux;
	}

	public List<Order> getNotAssigned() throws ParseException {
		List<Order> aux = new ArrayList<Order>();
		String sql = "SELECT * FROM myshop.full_order AS o LEFT JOIN myshop.full_products AS p ON o.product_id = p.product_id LEFT JOIN myshop.full_customers AS c ON o.customer_id = c.customer_id WHERE o.working_plan_id IS NULL ORDER BY order_id";
		List<Map<String, Object>> map;
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(sql).executeAndFetchTable().asList();
		}
		int last = 0;
		Order o = null;
		for (Map<String, Object> m : map) {
			if (((int) m.get("order_id")) != last) {
				o = new Order();
				o.setID((int) m.get("order_id"));
				Customer c;
				if (m.get("company_name") == null) {
					c = new IndividualCustomer((String) m.get("individual_name"), (String) m.get("individual_surname"),
							new User(-1, (String) m.get("indivdual_username"), (String) m.get("individual_password")),
							new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")),
							new CreditCards(-1, (int) m.get("credit_card_n"),
									(java.sql.Date) m.get("credit_card_exp_date")));
				} else {
					c = new Company(-1, (String) m.get("company_name"),
							new User(-1, (String) m.get("company_username"), (String) m.get("company_password")));
				}
				o.setCustomer(c);
			}
			if (((int) m.get("order_id")) != last)
				aux.add(o);

			Category c = new Category((String) m.get("category"));
			ProductLocation pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"),
					(int) m.get("height"), Side.LEFT);
			Product p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("description"), (double) m.get("weight"), (double) m.get("price"), c, c, pl, (double) m.get("company_price"));
			OrderItem oi = new OrderItem(-1, (int) m.get("quantity"), p, null, null);
			o.getProducts().add(oi);
			last = (int) m.get("order_id");
		}
		return aux;
	}

}
