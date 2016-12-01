package com.myshop.warehouse.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.MailBox;
import com.myshop.model.shipment.Shipment;
import com.myshop.model.shipment.Transporter;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.util.DefaultSql2o;
import com.myshop2.session.Session;

public class ShipmentController implements Comparable<ShipmentController>{

	public Shipment shipment;

	public ShipmentController(Shipment shipment) {
		this.shipment = shipment;
	}

	public ShipmentController() {
	}
	
	public List<MailBoxController> getAsController() {
		List<MailBoxController> aux = new ArrayList<MailBoxController>();
		if(shipment.getBoxes() == null)
			return aux;
		for(MailBox mb : shipment.getBoxes()) {
			aux.add(new MailBoxController(mb));
		} return aux;
	}

	public void addBox(MailBox box) {
		shipment.getBoxes().add(box);
		String sql = "UPDATE myshop.mail_box SET myshop.mail_box.shipment_id = :shipment WHERE myshop.mail_box.mail_box_id = :id";

		try (Connection con = new DefaultSql2o().open()) {
			Query query = con.createQuery(sql);
			query.addParameter("shipment", shipment.getID()).addParameter("id", box.getID()).executeUpdate();
		}
	}

	public Shipment create() {
		String sql = "INSERT INTO myshop.shipment (myshop.shipment.warehouse_keeper_id) values (:id);";
		int insertedId;

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			insertedId = (int) (long) con.createQuery(sql, true).addParameter("id", Session.almacenero.getID())
					.executeUpdate().getKey();
		}
		return Session.shipment = new Shipment().setID(insertedId);
	}

	public void changeTransporter(String transporter) {
		int tID = -1;
		if (transporter.equals("UPS")) {
			tID = 1;
		} else if (transporter.equals("DHL")) {
			tID = 2;
		} else if (transporter.equals("NACEX")) {
			tID = 3;
		} else if (transporter.equals("SEUR")) {
			tID = 4;
		}

		String sql = "UPDATE myshop.shipment AS s SET s.transporter_id = :tID WHERE s.shipment_id = :id";
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("tID", tID).addParameter("id", shipment.getID()).executeUpdate();
		}

	}

	public void sent() {
		String sql = "UPDATE myshop.shipment AS s SET s.sent = 1 WHERE s.shipment_id = :id";
		try (Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("id", shipment.getID()).executeUpdate();
		}

		Session.shipment = null;
	}

	public static List<ShipmentController> getOpened(WarehouseKeeper wk) {
		List<ShipmentController> shipments = new ArrayList<ShipmentController>();
		List<Map<String, Object>> map;

		String sql = "SELECT * FROM myshop.shipment, myshop.transporter WHERE myshop.shipment.transporter_id = myshop.transporter.transporter_id AND myshop.shipment.warehouse_keeper_id = :id AND myshop.shipment.sent = 0";

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			map = con.createQuery(sql).addParameter("id", wk.getID()).executeAndFetchTable().asList();
		}
		Shipment s;
		for (Map<String, Object> m : map) {
			s = new Shipment((int) m.get("shipment_id"),
					new Transporter((int) m.get("transporter_id"), (String) m.get("name")),
					new WarehouseKeeper().setID((int) m.get("warehouse_keeper_id")));
			
			shipments.add(new ShipmentController(s));
		}

		return shipments;
	}

	@Override
	public int compareTo(ShipmentController o) {
		return ((Integer) this.shipment.getID()).compareTo(o.shipment.getID());
	}

}
