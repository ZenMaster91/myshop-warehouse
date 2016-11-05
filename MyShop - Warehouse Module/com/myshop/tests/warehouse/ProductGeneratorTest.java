package com.myshop.tests.warehouse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myshop.warehouse.util.ProductGenerator;

public class ProductGeneratorTest {

	@Test
	public void test() {
		new ProductGenerator().generate();
	}

}
