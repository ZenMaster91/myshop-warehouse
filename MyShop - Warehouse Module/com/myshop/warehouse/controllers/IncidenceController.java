package com.myshop.warehouse.controllers;

import org.sql2o.Connection;

import com.myshop.model.order.Incidence;
import com.myshop.model.order.OrderItem;
import com.myshop.model.order.Status;
import com.myshop.warehouse.util.DefaultSql2o;

public class IncidenceController {
	
	private Incidence i;
	private OrderItem oi;
	
	public IncidenceController(Incidence i) {
		this.i = i;
	}
	
	public IncidenceController(OrderItem oi) {
		this.oi = oi;
	}
	
	public void setDescription(String description) {
		if(i == null) {
			String sql = "INSERT INTO myshop.incidence (myshop.incidence.description) VALUES (:description) ";
			int incidenceID;
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				incidenceID = (int) (long) con.createQuery(sql, true).addParameter("description", description).executeUpdate().getKey();
			}
			sql = "UPDATE myshop.order_item SET myshop.order_item.incidence_id=:incID WHERE myshop.order_item.order_item_id = :oiID";
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				con.createQuery(sql).addParameter("incID", incidenceID).addParameter("oiID", oi.getID()).executeUpdate();
			}
			if(oi.getIncidence()== null) {
				oi.setIncidence(new Incidence(incidenceID, description, false));
				updateIncidenceStatus(false);
				oi.getParent().setStatus(Status.INCIDENCIA.toString().toUpperCase());
			} else {
				oi.setIncidence(new Incidence(incidenceID, description, oi.getIncidence().isSolve()));
				updateIncidenceStatus(oi.getIncidence().isSolve());
			}
		} else {
			String sql = "UPDATE myshop.incidence SET myshop.incidence.description = :description WHERE myshop.incidence.incidence_id = :id";
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				con.createQuery(sql).addParameter("id", i.getID()).executeUpdate();
			}
			i.setDescription(description);
		}
	}
	
	public void setSolved(boolean solved) {
		String sql = "UPDATE myshop.incidence SET myshop.incidence.solved = :solved WHERE incidence_id = :incidence_id";
		int s = (solved) ? 1 : 0;
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("solved", s).addParameter("incidence_id", oi.getIncidenceID()).executeUpdate();
			oi.getIncidence().setSolve(solved);
		}
		if(solved) {
			oi.getParent().setStatus(Status.PREPARANDO.toString().toUpperCase());
		} else {
			oi.getParent().setStatus(Status.INCIDENCIA.toString().toUpperCase());
		}
		updateIncidenceStatus(solved);
	}
	
	private void updateIncidenceStatus(boolean solved) {
		String sql = "UPDATE myshop.order SET myshop.order.status_id = :status WHERE myshop.order.order_id = :o_id";
		int statusID=0;
		if(solved)
			statusID = 2;
		else
			statusID = 6;
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("status", statusID).addParameter("o_id", oi.getParent().getID()).executeUpdate();
		}
	}

}
