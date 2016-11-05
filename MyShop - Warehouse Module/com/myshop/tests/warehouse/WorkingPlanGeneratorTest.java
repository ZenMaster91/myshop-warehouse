package com.myshop.tests.warehouse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.generators.WorkingPlanGenerator;

public class WorkingPlanGeneratorTest {

	@Test
	public void test() {
		for(WorkingPlanController wpc : new WorkingPlanGenerator().generate().getAll()) {
			System.out.println(wpc.getNumberOfItems());
		}
	}

}
