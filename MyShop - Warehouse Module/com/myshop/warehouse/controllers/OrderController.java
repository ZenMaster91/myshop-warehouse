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
import org.sql2o.Sql2o;

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
import com.myshop.model.product.Product;
import com.myshop.model.product.ProductLocation;
import com.myshop.model.product.Side;
import com.myshop.model.user.User;
import com.myshop.warehouse.util.DefaultSql2o;

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
			aux += oi.getProduct().getWeight();
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
		String sql = "SELECT DISTINCT * FROM myshop.full_order AS o LEFT JOIN myshop.order as ord on o.order_id=ord.order_id LEFT JOIN myshop.full_products AS p ON o.product_id = p.product_id LEFT JOIN myshop.full_customers AS c ON o.customer_id = c.customer_id LEFT JOIN myshop.order_item AS oi ON o.order_id = oi.order_id AND o.product_id = oi.product_id WHERE o.working_plan_id IS NULL AND (ord.status_id=2 OR ord.status_id=6) ORDER BY o.order_id";
		List<Map<String, Object>> map;
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(sql).executeAndFetchTable().asList();
		}
		int last = 0;
		Order o = new Order();
		for (Map<String, Object> m : map) {
			if (((int) m.get("order_id")) != last) {
				if (o.getProducts().size() > 0) {
					 //System.out.println("Orden no asignada: " + o.getID() + " con " + o.getProducts().size() + " prod.");
					aux.add(o);
				}
				o = new Order();
				o.setID((int) m.get("order_id"));
				Customer c;
				if (m.get("company_name") == null) {
					c = new IndividualCustomer((String) m.get("individual_name"), (String) m.get("individual_surname"),
							new User(-1, (String) m.get("indivdual_username"), (String) m.get("individual_password")),
							new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")),
							new CreditCards(-1, /*(int) m.get("credit_card_n")*/-1,
									(java.sql.Date) m.get("credit_card_exp_date")));
				} else {
					c = new Company(-1, (String) m.get("company_name"),
							new User(-1, (String) m.get("company_username"), (String) m.get("company_password")));
				}
				o.setCustomer(c);
			}

			Category c = new Category((String) m.get("category"));

			ProductLocation pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"),
					(int) m.get("height"), Side.valueOf(((String) m.get("side")).toUpperCase()));

			Product p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("description"), (double) m.get("weight"), (double) m.get("price"), c, c, pl,
					(double) m.get("company_price"));
			// System.out.println("Producto " + p.getID() + " procesado");

			OrderItem oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, null, null);
			oi.setParent(o);
			// System.out.println("Producto " + p.getID() + " procesado con " +
			// oi.getQuantity() + " unidades añadidas");

			o.getProducts().add(oi);
			o.setStatus(((String) m.get("status")).toUpperCase());

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
		List<Order> aux = new ArrayList<Order>();
		String sql = "SELECT DISTINCT * FROM myshop.full_order AS o LEFT JOIN myshop.order as ord on o.order_id=ord.order_id LEFT JOIN myshop.full_products AS p ON o.product_id = p.product_id LEFT JOIN myshop.full_customers AS c ON o.customer_id = c.customer_id LEFT JOIN myshop.order_item AS oi ON o.order_id = oi.order_id AND o.product_id = oi.product_id WHERE (ord.status_id=3 OR ord.status_id=4) ORDER BY o.order_id";
		List<Map<String, Object>> map;
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(sql).executeAndFetchTable().asList();
		}
		int last = 0;
		Order o = new Order();
		for (Map<String, Object> m : map) {
			if (((int) m.get("order_id")) != last) {
				if (o.getProducts().size() > 0) {
					 //System.out.println("Orden no asignada: " + o.getID() + " con " + o.getProducts().size() + " prod.");
					aux.add(o);
				}
				o = new Order();
				o.setID((int) m.get("order_id"));
				Customer c;
				if (m.get("company_name") == null) {
					c = new IndividualCustomer((String) m.get("individual_name"), (String) m.get("individual_surname"),
							new User(-1, (String) m.get("indivdual_username"), (String) m.get("individual_password")),
							new Address((String) m.get("street"), (String) m.get("city"), (String) m.get("state"),
									(String) m.get("zip_code")),
							new CreditCards(-1, /*(int) m.get("credit_card_n")*/-1,
									(java.sql.Date) m.get("credit_card_exp_date")));
				} else {
					c = new Company(-1, (String) m.get("company_name"),
							new User(-1, (String) m.get("company_username"), (String) m.get("company_password")));
				}
				o.setCustomer(c);
			}

			Category c = new Category((String) m.get("category"));

			ProductLocation pl = new ProductLocation((int) m.get("corridor"), (int) m.get("position"),
					(int) m.get("height"), Side.valueOf(((String) m.get("side")).toUpperCase()));

			Product p = new Product((int) m.get("product_id"), (int) m.get("stock"), (String) m.get("name"),
					(String) m.get("description"), (double) m.get("weight"), (double) m.get("price"), c, c, pl,
					(double) m.get("company_price"));
			// System.out.println("Producto " + p.getID() + " procesado");

			Incidence i = null;
			MailBox mb = null;
			
			if(m.get("incidence_id") != null) {
				i = new Incidence((int) m.get("incidence_id"), (String) m.get("incidence"), (boolean) m.get("incidence_solved"));
			}
			
			if(m.get("mail_box") != null) {
				mb = new MailBox((int) m.get("mail_box"));
			}
			
			OrderItem oi = new OrderItem((int) m.get("order_item_id"), (int) m.get("quantity"), p, i, mb);
			oi.setParent(o);
			// System.out.println("Producto " + p.getID() + " procesado con " +
			// oi.getQuantity() + " unidades añadidas");

			o.getProducts().add(oi);
			o.setStatus(((String) m.get("status")).toUpperCase());

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

	private Sql2o sql2o = DefaultSql2o.SQL2O;

	public List<Order> getAll() {
		String complexSql = "SELECT * FROM myshop.order";
		try (Connection con = sql2o.open()) {
			return con.createQuery(complexSql).executeAndFetch(Order.class);
		}
	}

	public Order getOrderById(String id) {
		String complexSql = "SELECT o.*,s.name FROM myshop.order o , myshop.status s where s.status_id=o.status_id and o.order_id= "
				+ id;
		List<Map<String, Object>> map;
		try (Connection con = sql2o.open()) {
			map = con.createQuery(complexSql).executeAndFetchTable().asList();
		}
		Order o = new Order();
		for (Map<String, Object> m : map) {
			o.setID((int) m.get("order_id"));
			o.setCustomer(new Customer((int) m.get("customer_id")));
			o.setStatus((String) m.get("name"));
			o.setProducts(getAllByOrderId(o.getID() + ""));
			o.setDateReceived((Date) m.get("date_received"));
		}
		return o;
	}

	public List<Order> getOrderByStatus(String status) {
		String complexSql = "SELECT o.*,i.*, p.* " + "FROM myshop.order_item i , myshop.order o , myshop.status s, "
				+ "myshop.full_products p where i.product_id=p.product_id and " + "o.order_id=i.order_id and "
				+ "o.status_id=s.status_id and s.name= :status";
		List<Map<String, Object>> map;
		List<Order> orders = new ArrayList<Order>();
		List<OrderItem> lista;
		try (Connection con = sql2o.open()) {
			map = con.createQuery(complexSql).addParameter("status", status).executeAndFetchTable().asList();
		}
		for (Map<String, Object> m : map) {
			Order o = new Order();
			o.setID((int) m.get("order_id"));
			lista = new ArrayList<OrderItem>();
			for (Map<String, Object> m2 : map) {
				if ((int) m.get("order_id") == (int) m2.get("order_id")) {
					OrderItem i = new OrderItem();
					i.setID((int) m2.get("order_item_id"));
					i.setQuantity((int) m2.get("quantity"));
					Product p = new Product();
					p.setCorridor((int) m2.get("corridor"));
					p.setHeight((int) m2.get("height"));
					p.setPosition((int) m2.get("position"));
					p.setPrice((double) m2.get("price"));
					p.setSide((String) m2.get("side"));
					lista.add(i);
				}
			}
			o.setProducts(lista);
			o.setDateReceived((Date) m.get("date_received"));

			if (!contieneOrder(orders, o)) {
				orders.add(o);
			}
		}
		return orders;
	}

	private boolean contieneOrder(List<Order> orders, Order o) {
		for (Order or : orders) {
			if (or.getID() == o.getID()) {
				return true;
			}
		}
		return false;
	}

	public List<OrderItem> getAllByOrderId(String id) {
		String complexSql = "SELECT * FROM myshop.order_item o, myshop.product_location l, myshop.side s , myshop.product p where s.side_id=l.side_id and p.product_id=o.product_id and l.product_id=o.product_id and o.order_id = "
				+ id + " order by l.corridor";
		List<Map<String, Object>> map;
		try (Connection con = sql2o.open()) {
			map = con.createQuery(complexSql).executeAndFetchTable().asList();
		}

		List<OrderItem> orders = new ArrayList<OrderItem>();
		for (Map<String, Object> m : map) {
			OrderItem o = new OrderItem();
			Product p = new Product();
			o.setQuantity((int) m.get("quantity"));
			o.setID((int) m.get("order_item_id"));
			p.setCorridor((int) m.get("corridor"));
			p.setHeight((int) m.get("heigth"));
			p.setID((int) m.get("product_id"));
			p.setPosition((int) m.get("position"));
			p.setPrice((double) m.get("price"));
			p.setSide((String) m.get("name"));
			o.setProduct(p);
			orders.add(o);
		}
		return orders;
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

}
