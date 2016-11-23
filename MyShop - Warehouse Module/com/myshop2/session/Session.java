package com.myshop2.session;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.myshop.model.order.Order;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.WarehouseKeeperController;
import com.myshop.warehouse.controllers.WorkingPlanController;

public class Session {
	
	public static WarehouseKeeper almacenero = new WarehouseKeeper(1, "Pepe", "Suarez Rodriguez");
	public static Order order = new Order();
	public static List<Order> orders = new ArrayList<Order>();
	public static List<Order> ordersToPackage = new ArrayList<Order>();
	
	public static boolean isAlmaceneroOccupied = false;
	
	public static List<WorkingPlanController> getWorkingPlans() {
		List<WorkingPlanController> aux = new WarehouseKeeperController().getCurrentWorkingPlan(almacenero);
		if(aux != null && aux.size() > 0)
			isAlmaceneroOccupied = true;
		else
			isAlmaceneroOccupied = false;
		return aux;
	}
	
	public static List<WorkingPlanController> getWorkingPlans(WarehouseKeeper wk) {
		almacenero = wk;
		return getWorkingPlans();
	}
	
	public static WarehouseKeeper getAlmacenero(String id) {
		return new WarehouseKeeperController().getByID(id).get(0);
	}
	
	public static List<Order> getNotAssignedOrders() {
		try {
			orders = new OrderController().getNotAssigned();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static List<Order> getPendientesEmpaquetado() {
		try {
			ordersToPackage = new OrderController().getPendientesEmpaquetando();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return ordersToPackage;
	}
}
