package com.myshop.warehouse.controllers;

import org.sql2o.Connection;

import com.myshop.model.order.Incidence;
import com.myshop.warehouse.util.DefaultSql2o;
import com.sun.xml.internal.ws.addressing.policy.AddressingPolicyMapConfigurator;

public class IncidenceController {
	
	private Incidence i = null;
	private WorkingPlanController oi;
	
	public IncidenceController(Incidence i) {
		this.i = i;
	}
	
	public IncidenceController(WorkingPlanController oi) {
		this.oi = oi;
	}
	
	public Incidence setDescription(String description) {
		if(i == null) {
			String sql = "INSERT INTO myshop.incidence (myshop.incidence.description) VALUES (:description) ";
			int incidenceID;
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				incidenceID = (int) (long) con.createQuery(sql, true).addParameter("description", description).executeUpdate().getKey();
			}
			sql = "UPDATE myshop.working_plan SET myshop.working_plan.incidence_id=:incID WHERE myshop.working_plan.wp_id = :wpID";
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				con.createQuery(sql).addParameter("incID", incidenceID).addParameter("wpID", oi.getWp().getID()).executeUpdate();
			}
			if(oi.getWp().incidence== null) {
				oi.getWp().incidence = new Incidence(incidenceID, description).setSolve(false);
				updateIncidenceStatus(false);
			} else {
				oi.getWp().incidence = new Incidence(incidenceID, description).setSolve(oi.getWp().incidence.isSolve());
				updateIncidenceStatus(oi.getWp().incidence.isSolve());
			}
			i = new Incidence(incidenceID, "");
		} else {
			String sql = "UPDATE myshop.incidence SET myshop.incidence.description = :description WHERE myshop.incidence.incidence_id = :id";
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				con.createQuery(sql).addParameter("description", "").addParameter("id", i.getID()).executeUpdate();
			}
			i.setDescription(description);
		}
		return i;
	}
	
	public void setSolved(boolean solved) {
		String sql = "UPDATE myshop.incidence SET myshop.incidence.solved = :solved WHERE incidence_id = :incidence_id";
		int s = (solved) ? 1 : 0;
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("solved", s).addParameter("incidence_id", oi.getWp().incidence.getID()).executeUpdate();
			oi.getWp().incidence.setSolve(solved);
		}
		updateIncidenceStatus(solved);
	}
	
	private void updateIncidenceStatus(boolean solved) {
		String sql = "UPDATE myshop.order SET myshop.order.status_id = :status WHERE myshop.order.order_id = :o_id";
		int statusID=0;
		if(solved)
			statusID = 3;
		else
			statusID = 8;
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql).addParameter("status", statusID).addParameter("o_id", oi.getItems().get(0).getOrderItem().getParent().getID()).executeUpdate();
		}
	}

}
