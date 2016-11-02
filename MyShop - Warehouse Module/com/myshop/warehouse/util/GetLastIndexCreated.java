package com.myshop.warehouse.util;

import org.sql2o.Connection;

public class GetLastIndexCreated {

	public int get() {
		try (Connection con = new DefaultSql2o().open()) {
			return con.createQuery("SELECT LAST_INSERT_ID();").executeScalar(Integer.class);
		}
	}

}
