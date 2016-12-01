package com.myshop.tests.warehouse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myshop.model.shipment.Shipment;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.ShipmentController;

public class ShipmentControllerTest {

	@Test
	public void test() {
		for(ShipmentController s : ShipmentController.getOpened(new WarehouseKeeper().setID(1))) {
			System.out.print(s.shipment.getID());
		}
	}

}
