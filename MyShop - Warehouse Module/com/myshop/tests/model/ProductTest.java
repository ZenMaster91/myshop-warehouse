package com.myshop.tests.model;

import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.myshop.model.product.Product;

public class ProductTest {
	private Sql2o sql2o;
	 
	@Before
	public void setUp() {
		this.sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void fullProductsModelTest() {
		String complexSql = "SELECT * FROM myshop.full_products";

	    try (Connection con = sql2o.open()) {
	        for(Product p: con.createQuery(complexSql).addColumnMapping("product_id", "productID").executeAndFetch(Product.class)) {
	        	System.out.println(p.getProductID() +" CATEGORY: "+ p.getCategory() + " LOCATION: " +p.getProductLocation().toString());
	        }
	    }
	}

}
