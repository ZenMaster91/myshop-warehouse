package com.myshop.warehouse.generators;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.myshop.model.customer.Address;
import com.myshop.model.customer.IndividualCustomer;
import com.myshop.model.order.Order;

public class ShippingInfoGenerator implements Generator {
	
	private Order o;
	
	public ShippingInfoGenerator(Order o) {
		this.o = o;
	}

	@Override
	public Object generate() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("IMPRESORA--ETIQUETA-ENVIO.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(o.getCustomer().toString() + "\n");
		Address add;
		if(o.getCustomer() instanceof IndividualCustomer) {
			add = ((IndividualCustomer) o.getCustomer()).getAddress();
			sb.append(add.getStreet() + "\n" + add.getCity() + "" + add.getState() + " " + add.getCip_code() + "\n");
		}
		writer.write(sb.toString());
		writer.close();
		return sb.toString();
	}

}
