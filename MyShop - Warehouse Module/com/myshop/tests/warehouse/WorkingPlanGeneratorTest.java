package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.generators.WorkingPlanGenerator;

public class WorkingPlanGeneratorTest {

	@Test
	public void test() {
		for(WorkingPlanController wpc : new WorkingPlanGenerator().generate().getAll()) {
			System.out.println("\nID: "+ wpc.getWp().getID() +" Items: "+wpc.getNumberOfItems() + " - " + wpc.getTotalWeight() + " Kg");
			
			for(WorkingPlanItem wpi : wpc.getItems()) {
				System.out.println("    --> "+ wpi.getOrderItem().getProduct().getName() + " " + wpi.getOrderItem().getProduct().getWeight() + " Kg");
			}
			
			for(WorkingPlanController wpc2 : wpc.getChilds()) {
				System.out.println("\n  ParentID: "+ wpc.getWp().getID() +" Items: "+wpc2.getNumberOfItems() + " - " + wpc2.getTotalWeight() + " Kg");
				
				for(WorkingPlanItem wpi2 : wpc2.getItems()) {
					System.out.println("      --> "+ wpi2.getOrderItem().getProduct().getName() + " " + wpi2.getOrderItem().getProduct().getWeight() + " Kg");
				}
			}
		}
	}

}
