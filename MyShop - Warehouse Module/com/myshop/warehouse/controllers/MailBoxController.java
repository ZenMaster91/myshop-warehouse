package com.myshop.warehouse.controllers;

import org.sql2o.Connection;

import com.myshop.model.order.MailBox;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.util.DefaultSql2o;

public class MailBoxController {
	
	private MailBox mailbox;
	
	public MailBox open() {
		String sql = "INSERT INTO myshop.mail_box values();";
		int insertedId;
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			 insertedId = (int)(long) con.createQuery(sql, true)
					    .executeUpdate()
					    .getKey();
		}
		return mailbox = new MailBox(insertedId);
	}
	
	public void update(OrderItem oi, MailBox mb) {
		String sql = "UPDATE myshop.order_item SET myshop.order_item.mail_box_id=:mbID WHERE myshop.order_item.order_item_id = :oiID";
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("mbID", mb.getID()).addParameter("oiID", oi.getID()).executeUpdate();
			oi.setMailbox(mb);
		}
	}
	
	public MailBox getMailBox() {
		return this.mailbox;
	}

}
