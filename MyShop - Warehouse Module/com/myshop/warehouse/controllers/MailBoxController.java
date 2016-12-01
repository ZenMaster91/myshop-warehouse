package com.myshop.warehouse.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.MailBox;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.util.DefaultSql2o;
import com.myshop2.session.Session;

public class MailBoxController implements Comparable<MailBoxController>{

	private MailBox mailbox;
	public Order order;
	
	public MailBoxController(MailBox mb) {
		this.mailbox = mb;
	}
	
	public MailBoxController() {
	}

	public MailBox open() {
		String sql = "INSERT INTO myshop.mail_box values();";
		int insertedId;

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			insertedId = (int) (long) con.createQuery(sql, true).executeUpdate().getKey();
		}
		return mailbox = new MailBox(insertedId);
	}

	public MailBoxController open(Order o) {
		String sql = "INSERT INTO myshop.mail_box values();";
		int insertedId;

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			insertedId = (int) (long) con.createQuery(sql, true).executeUpdate().getKey();
		}
		this.mailbox = new MailBox(insertedId);
		this.order = o;
		return this;
	}

	public void update(OrderItem oi, MailBox mb) {
		String sql = "UPDATE myshop.order_item SET myshop.order_item.mail_box_id=:mbID WHERE myshop.order_item.order_item_id = :oiID";

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("mbID", mb.getID()).addParameter("oiID", oi.getID()).executeUpdate();
			oi.setMailbox(mb);
		}
	}

	public static void scan(OrderItem oi, MailBoxController mb, int quantity) {
		System.out.println("MailBoxID: " + mb.mailbox.getID() + " OIID: " + oi.getID());

		String sql = "INSERT INTO myshop.order_item_mailBox_rel (myshop.order_item_mailBox_rel.mail_box_id, myshop.order_item_mailBox_rel.order_item_id, myshop.order_item_mailBox_rel.quantity) VALUES (:id, :oiID, :quantity)";

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("oiID", oi.getID()).addParameter("quantity", quantity)
					.addParameter("id", mb.mailbox.getID()).executeUpdate();
			oi.setMailbox(mb.mailbox);
		} catch (Exception e) {
			sql = "UPDATE myshop.order_item_mailBox_rel SET myshop.order_item_mailBox_rel.quantity = :quantity WHERE myshop.order_item_mailBox_rel.order_item_id = :oi_id && myshop.order_item_mailBox_rel.mail_box_id = :mb_id";
			try (Connection con = DefaultSql2o.SQL2O.open()) {
				con.createQuery(sql).addParameter("quantity", oi.itemsPackaged).addParameter("oi_id", oi.getID())
						.addParameter("mb_id", mb.mailbox.getID()).executeUpdate();
				oi.setMailbox(mb.mailbox);
			}
		}
	}
	
	public static List<MailBoxController> getReadyMailBoxes() {
		List<MailBoxController> mailboxes = new ArrayList<MailBoxController>();
		List<Map<String, Object>> map;
		String sql = "SELECT * FROM myshop.order_item_mailBox_rel AS rel, myshop.mail_box AS mb WHERE rel.mail_box_id = mb.mail_box_id AND mb.closed = 1 AND mb.shipment_id IS NULL";
		try (Connection con = new DefaultSql2o().open()) {
			map = con.createQuery(sql).executeAndFetchTable().asList();
		}
		List<Integer> insertedIndexes = new ArrayList<Integer>();
		for (Map<String, Object> m : map) {
			MailBox mb = new MailBox((int) m.get("mail_box_id"));
			if(!insertedIndexes.contains(mb.getID())) {
				mailboxes.add(new MailBoxController(mb));
				insertedIndexes.add(mb.getID());
			}
		} return mailboxes;
	}

	public MailBox getMailBox() {
		return this.mailbox;
	}
	
	public void close() {
		String sql = "UPDATE myshop.mail_box SET myshop.mail_box.closed = 1 WHERE mail_box_id = :id";
		try (Connection con = new DefaultSql2o().open()) {
			Query query = con.createQuery(sql);
			query.addParameter("id", mailbox.getID()).executeUpdate();
		}	
	}

	@Override
	public int compareTo(MailBoxController o) {
		return ((Integer) mailbox.getID()).compareTo(o.getMailBox().getID());
	}

}
