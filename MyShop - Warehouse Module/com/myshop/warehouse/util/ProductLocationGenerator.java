package com.myshop.warehouse.util;

import com.myshop.warehouse.generators.Generator;

public class ProductLocationGenerator implements Generator {

	@SuppressWarnings("unused")
	private final static int CORRIDORS = 4;
	@SuppressWarnings("unused")
	private final static int SIDE = 2;
	private final static int POSITION = 10;
	private final static int HEIGTH = 3;

	@Override
	public Object generate() {
		int cor = 1, sid = 2, pos = 1, hei = 1;
		for (int i = 31; i <= 231; i++) {
			System.out.println(i + "," + cor + "," + sid + "," + pos + "," + hei);
		
			if (hei == HEIGTH) {
				hei = 1;
				pos++;
			} else
				hei++;
			if(pos > POSITION) {
				if(sid==1) {
					sid=2;
					pos=1;
				} else {
					sid=1;
					cor++;
					pos=1;
				}
			}
		}

		return this;
	}

}
