package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.WarehouseKeeperController;

public class WarehouseKeeperTest {

	@Test
	public void test() {
		System.out.println(new WarehouseKeeperController().getCurrentWorkingPlan(new WarehouseKeeper(2, "Lucia", "Gonzalez Muñiz")));
	}

}
