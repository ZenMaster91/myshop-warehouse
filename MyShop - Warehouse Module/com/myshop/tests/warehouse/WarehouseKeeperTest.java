package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.WarehouseKeeperController;
import com.myshop.warehouse.controllers.WorkingPlanController;

public class WarehouseKeeperTest {

	@Test
	public void test() {
		System.out.println(new WarehouseKeeperController().getCurrentWorkingPlan(new WarehouseKeeper(3, "Lucia", "Gonzalez Muñiz")));
		for(WorkingPlanController wpc : new WarehouseKeeperController().getCurrentWorkingPlan(new WarehouseKeeper(3, "Lucia", "Gonzalez Muñiz"))) {
			WorkingPlan wp = wpc.getWp();
			System.out.println("WP-ID: "+wp.getID() + " with " + wpc.getNumberOfItems());
		}
	}

}
