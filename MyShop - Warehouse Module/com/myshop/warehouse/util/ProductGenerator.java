package com.myshop.warehouse.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.myshop.warehouse.generators.Generator;

public class ProductGenerator implements Generator {

	@Override
	public Object generate() {
		Random rnd;
		String name, desc;
		BigDecimal price, company_price, weight, heigth, width, deep;
		int iva;
		int catID = 37, stock;
		for (int i = 31; i <= 231; i++) {
			rnd = new Random();
			name = "name" + i;
			desc = "desc" + i;
			price = BigDecimal.valueOf(rnd.nextDouble() * 100 + 17);
			company_price = BigDecimal.valueOf(rnd.nextDouble() * 90 + 17);
			weight = BigDecimal.valueOf(rnd.nextDouble() * 9);
			stock = rnd.nextInt(4) + 1;
			iva = 1 + (int)(Math.random() * 2); 
			heigth = BigDecimal.valueOf(rnd.nextInt(20));
			width = BigDecimal.valueOf(rnd.nextInt(20));
			deep = BigDecimal.valueOf(rnd.nextInt(20));

			System.out.println((i + 231) + ",\"" + name + "\",\"" + desc + "\"," + catID + ","
					+ weight.setScale(2, RoundingMode.HALF_UP) + "," + price.setScale(2, RoundingMode.HALF_UP) + ","
					+ stock + "," + company_price.setScale(2, RoundingMode.HALF_UP) + ","
					+ iva + "," + heigth.setScale(2, RoundingMode.HALF_UP)
					+ "," + width.setScale(2, RoundingMode.HALF_UP) + "," + deep.setScale(2, RoundingMode.HALF_UP));
		}
		return this;
	}

}
