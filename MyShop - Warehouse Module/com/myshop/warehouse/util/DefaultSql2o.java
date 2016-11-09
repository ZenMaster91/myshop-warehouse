package com.myshop.warehouse.util;

import org.sql2o.Sql2o;

public class DefaultSql2o extends Sql2o {

	public static Sql2o SQL2O = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop",
			"myshop-app", "'m:9AU7n");

	public DefaultSql2o() {
		super("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
	}
}
