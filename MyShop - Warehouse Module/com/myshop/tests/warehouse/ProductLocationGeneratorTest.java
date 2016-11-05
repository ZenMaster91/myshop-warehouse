package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.warehouse.util.ProductLocationGenerator;

public class ProductLocationGeneratorTest {

	@Test
	public void test() {
		new ProductLocationGenerator().generate();
	}

}
