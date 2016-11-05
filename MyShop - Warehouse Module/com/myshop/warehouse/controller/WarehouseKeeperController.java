package com.myshop.warehouse.controller;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;


public class WarehouseKeeperController {
	
	public List<WarehouseKeeper> getAll() {
		String complexSql = "SELECT * FROM myshop.warehouse_keeper";
		try (Connection con = new DefaultSql2o().open()) {
			return con.createQuery(complexSql).addColumnMapping("wk_id", "ID").executeAndFetch(WarehouseKeeper.class);
		}
	}
	
	public List<WarehouseKeeper> getWarehouseKeeperbyNameSur(String id){
		String complexSql = "SELECT * FROM myshop.warehouse_keeper as w_k WHERE w_k.wk_id = :id";
		try (Connection con = new DefaultSql2o().open()) {
			return con.createQuery(complexSql).addParameter("id", id).addColumnMapping("wk_id", "ID")
					.executeAndFetch(WarehouseKeeper.class);
		}
	}
	
	
}
