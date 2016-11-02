package com.myshop.warehouse.controller;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;


public class WarehouseKeeperController {
	
	public List<WarehouseKeeper> getAll() {
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.warehouse_keeper";
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).addColumnMapping("wk_id", "ID").executeAndFetch(WarehouseKeeper.class);
		 }
	}
	
	public WarehouseKeeper getWarehouseKeeperbyNameSur(String id){
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.warehouse_keeper w where w.wk_id = "+id;
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).executeAndFetch(WarehouseKeeper.class).get(0);
		 }
	}
	
	
}
