package com.myshop.warehouse.controllers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;

import com.myshop.model.customer.Address;
import com.myshop.model.customer.Company;
import com.myshop.model.customer.CreditCards;
import com.myshop.model.customer.Customer;
import com.myshop.model.customer.IndividualCustomer;
import com.myshop.model.order.Incidence;
import com.myshop.model.order.MailBox;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.product.Category;
import com.myshop.model.product.Dimension3D;
import com.myshop.model.product.Product;
import com.myshop.model.product.ProductLocation;
import com.myshop.model.product.Side;
import com.myshop.model.user.User;
import com.myshop.warehouse.util.DefaultSql2o;
import com.myshop2.sql.QueryLoader;

public class OrderController implements Comparable<OrderController> {

	private Order o;
	

	public OrderController() {
	}

	public OrderController(Order o) {
		this.o = o;
	}

	public double getWeight(Order o) {
		double aux = 0.0;
		for (OrderItem oi : o.getProducts()) {
			aux += oi.getProduct().getWeight()*oi.getQuantity();
		}
		return aux;
	}
	
	public int getNumberOfItems() {
		int aux = 0;
		for(OrderItem oi : o.getProducts()) {
			aux += oi.getQuantity();
		}
		return aux;
	}

	public List<Order> getNotAssigned() throws ParseException {
		List<Order> aux = new ArrayList<Order>();
		List<Map<String, Object>> map;
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(QueryLoader.load("NotAssignedOrders.sql")).executeAndFetchTable().asList();
		}
		int last = 0;
		Order o = new Order();
		for (Map<String, Object> m : map) {
			if (((int) m.get("order_id")) != last) {
				if (o.getProducts().size() > 0) {
					 System.out.println("Orden no asignada: " + o.getID() + " con " + o.getProducts().size() + " prod.");
					aux.add(o);
				}
				o = new Order();
				o.setID((int) m.get("order_id"));
				Customer c;
				if (m.get("company_name") == null) {
					c = new IndividualCustomer(-1, (String) m.get("individual_name"), (String) m.get("individual_surname"),
							new User(-1, (String) m.get("indivdual_username"), (String) m.get("individual_password")),
							new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")),
							new CreditCards(-1, /*(int) m.get("credit_card_n")*/-1,
									(java.sql.Date) m.get("credit_card_exp_date")));
				} else {
					c = new Company(-1, (String) m.get("company_name"),
							new User(-1, (String) m.get("company_username"), (String) m.get("company_password")), new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")));
				}
				o.setCustomer(c);
			}

			Category c = new Category((String) m.get("category"));

			ProductLocation pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"),
					(int) m.get("height"), Side.valueOf(((String) m.get("side")).toUpperCase()));

			Product p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("description"), (double) m.get("weight"), (double) m.get("price"), c, c, pl,
					(double) m.get("company_price"));
			p.setDimensions(new Dimension3D((double) m.get("heigth"), (double) m.get("weigth"), (double) m.get("deep")));
			// System.out.println("Producto " + p.getID() + " procesado");

			OrderItem oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, null, null, (int) m.get("items_packaged"));
			oi.setParent(o);
			// System.out.println("Producto " + p.getID() + " procesado con " +
			// oi.getQuantity() + " unidades añadidas");

			o.getProducts().add(oi);
			o.setStatus(((String) m.get("status")).toUpperCase());
			o.setDateReceived((Date) m.get("date_received"));

			if (((int) m.get("order_id")) != last && o.getProducts().size() > 0) {
				// System.out.println("Orden no asignada: " + o.getID() + " con
				// " + o.getProducts().size() + " prod.");
				// aux.add(o);
			}

			last = (int) m.get("order_id");
		}
		if (o.getProducts().size() > 0) {
			// System.out.println("Orden no asignada: " + o.getID() + " con " +
			// o.getProducts().size() + " prod.");
			aux.add(o);
		}
		return aux;
	}
	
	public List<Order> getPendientesEmpaquetando() throws ParseException {
		System.out.println("E"+1);
		List<Order> aux = new ArrayList<Order>();
		//String sql = "SELECT DISTINCT * FROM myshop.full_order AS o LEFT JOIN myshop.order as ord on o.order_id=ord.order_id LEFT JOIN myshop.full_products AS p ON o.product_id = p.product_id LEFT JOIN myshop.full_customers AS c ON o.customer_id = c.customer_id LEFT JOIN myshop.order_item AS oi ON o.order_id = oi.order_id AND o.product_id = oi.product_id WHERE (ord.status_id=3 OR ord.status_id=4) ORDER BY o.order_id";
		List<Map<String, Object>> map;
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(QueryLoader.load("PendientesEmpaquetadoOrders.sql")).executeAndFetchTable().asList();
		}
		System.out.println("E"+2);
		int last = 0;
		Order o = new Order();
		for (Map<String, Object> m : map) {
			if (((int) m.get("order_id")) != last) {
				if (o.getProducts().size() > 0) {
					 //System.out.println("Orden no asignada: " + o.getID() + " con " + o.getProducts().size() + " prod.");
					aux.add(o); // -------
				}
				System.out.println("E"+3);
				o = new Order();
				o.setID((int) m.get("order_id"));
				Customer c;
				System.out.println("E"+4);
				if (m.get("company_name") == null) {
					c = new IndividualCustomer(-1,(String) m.get("individual_name"), (String) m.get("individual_surname"),
							new User(-1, (String) m.get("indivdual_username"), (String) m.get("individual_password")),
							new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")),
							new CreditCards(-1, /*(int) m.get("credit_card_n")*/-1,
									(java.sql.Date) m.get("credit_card_exp_date")));
				} else {
					c = new Company(-1, (String) m.get("company_name"),
							new User(-1, (String) m.get("company_username"), (String) m.get("company_password")), new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")));
				}
				System.out.println("E"+5);
				o.setCustomer(c);
			}

			Category c = new Category((String) m.get("category"));
			System.out.println("E"+6);
			ProductLocation pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"),
					(int) m.get("height"), Side.valueOf(((String) m.get("side")).toUpperCase()));

			Product p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("description"), (double) m.get("weight"), (double) m.get("price"), c, c, pl,
					(double) m.get("company_price"));
			p.setDimensions(new Dimension3D((double) m.get("heigth"), (double) m.get("weigth"), (double) m.get("deep")));
			// System.out.println("Producto " + p.getID() + " procesado");
			System.out.println("E"+7);
			Incidence i = null;
			MailBox mb = null;
			
			/*if(m.get("incidence_id") != null) {
				i = new Incidence((int)m.get("incidence_id"), (String) m.get("incidence")).setSolve((boolean) m.get("incidence_solved"));
			}*/
			
			if(m.get("mail_box") != null) {
				mb = new MailBox((int) m.get("mail_box"));
			}
			System.out.println("E"+8);
			OrderItem oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, i, mb, (int) m.get("items_packaged"));
			oi.setParent(o);
			// System.out.println("Producto " + p.getID() + " procesado con " +
			// oi.getQuantity() + " unidades añadidas");

			o.getProducts().add(oi);
			o.setStatus(((String) m.get("status")).toUpperCase());
			o.setDateReceived((Date) m.get("date_received"));

			if (((int) m.get("order_id")) != last && o.getProducts().size() > 0) {
				// System.out.println("Orden no asignada: " + o.getID() + " con
				// " + o.getProducts().size() + " prod.");
				// aux.add(o);
			}

			last = (int) m.get("order_id");
			System.out.println("E"+9);
		}
		if (o.getProducts().size() > 0) {
			// System.out.println("Orden no asignada: " + o.getID() + " con " +
			// o.getProducts().size() + " prod.");
			aux.add(o); // --------
			System.out.println("E"+10);
		}
		System.out.println("E"+11);
		return aux;
	}

	public String printShippingInfo() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ETIQUETA-ENVIO.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(o.getCustomer().toString() + "\n");
		Address add;
		if(o.getCustomer() instanceof IndividualCustomer) {
			add = ((IndividualCustomer) o.getCustomer()).getAddress();
			sb.append(add.getStreet() + "\n" + add.getCity() + "" + add.getState() + " " + add.getCip_code() + "\n");
		}
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}

	/**
	 * Gives a string containing the bill.
	 * 
	 * @return a formatted string containing the bill.
	 */
	public String printBill() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ALBARÁN.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("==== ALBARÁN DE COMPRA ====\n").append("\t\t\tMY SHOP\n\n")
				.append("PRODUCTO\t\tCANTIDAD\t\tPRECIO/U\t\tTOTAL\n");
		for (OrderItem dp : o.getProducts()) {
			sb.append(dp.getProduct().getID() + "\t\t" + dp.getQuantity() + "\t\t"
					+ dp.getProduct().getPrice() + "\t\t" + dp.getQuantity() * dp.getProduct().getPrice() + "\n");
		}
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}

	@Override
	public int compareTo(OrderController or) {
		return ((Integer) this.o.getID()).compareTo(or.o.getID());
	}
	
	public Order getOrder() {
		return this.o;
	}
	
	public void finalize() {
		String sql = "UPDATE myshop.order SET myshop.order.status_id = 5 WHERE myshop.order.order_id = :oID";
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("oID", o.getID()).executeUpdate();
		}
	}
	
	public double getVolume() {
		double vol = 0.0;
		for(OrderItem oi : o.getProducts()) {
			vol+=oi.getProduct().getDimensions().calculateVolume();
		}
		return vol;
	}

}
