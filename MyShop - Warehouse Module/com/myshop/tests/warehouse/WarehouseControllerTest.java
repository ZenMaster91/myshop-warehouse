package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.WarehouseKeeperController;

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

}
