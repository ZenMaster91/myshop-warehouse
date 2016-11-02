package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.WarehouseKeeperController;
import com.myshop.warehouse.util.GetLastIndexCreated;

public class WarehouseControllerTest {

	@Test
	public void getAllTest() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for(WarehouseKeeper wk : new WarehouseKeeperController().getAll())
			System.out.println(wk);
	}
	
	@Test
	public void getByIDTest() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for(WarehouseKeeper wk : new WarehouseKeeperController().getByID("1"))
			System.out.println(wk);
	}
	
	@Test
	public void getLastIndexInsertedTest() {
		System.out.println(new GetLastIndexCreated().get());
	}

}
