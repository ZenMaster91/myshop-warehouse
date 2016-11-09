package com.myshop.warehouse.generators;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;

public class AlbaranGenerator implements Generator {
	
	private Order o;
	
	public AlbaranGenerator(Order o) {
		this.o = o;
	}
	

	@Override
	public Object generate() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ALBARÁN.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("==== ALBARÁN DE COMPRA ====\n").append("\t\t\tMY SHOP\n\n")
				.append("PRODUCTO\t\tCANTIDAD\t\tPRECIO/U\t\tTOTAL\n");
		for (OrderItem dp : o.getProducts()) {
			sb.append(dp.getProduct().getID() + "\t\t\t\t\t" + dp.getQuantity() + "\t\t\t"
					+ dp.getProduct().getPrice() + "\t\t\t" + dp.getQuantity() * dp.getProduct().getPrice() + "\n");
		}
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}

}
