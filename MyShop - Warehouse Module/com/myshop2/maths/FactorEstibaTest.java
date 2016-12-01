package com.myshop2.maths;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class FactorEstibaTest {
	
	private double vol = 1, weight = 10;
	private int counter, total;

	@Test @Ignore
	public void calculateTest() {
		assertEquals(0.1, new FactorEstiba(vol, weight).calculate(), 0.001);
		for(double i = 0.1; i < 10; i+=0.1) {
			for(double j = 0.1; j < 10; j+=0.1) {
				System.out.println("Factor de estiva para " + i + "m3 y " + j + "Kg = " + new FactorEstiba(i, j).calculate());
				total++;
				if(new FactorEstiba(i, j).calculate() <= 0.1)
					counter++;
			}
		} System.out.println("Sólo se podrían recoger " + counter + " pedidos de " + total + " generados");
	}
	
	@Test
	public void validateTest() {
		assertEquals(false, new FactorEstiba(2000000.01, 15.00).validate());
		assertEquals(false, new FactorEstiba(2000000.00, 15.01).validate());
		assertEquals(true, new FactorEstiba(2000000.00, 15.00).validate());
	}

}
