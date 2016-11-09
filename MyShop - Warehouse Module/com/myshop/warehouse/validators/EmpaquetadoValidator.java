package com.myshop.warehouse.validators;

import java.util.List;

import com.myshop.model.order.OrderItem;
import com.myshop.model.product.Product;

public class EmpaquetadoValidator implements Validator {
	
	private List<OrderItem> items;
	private Product toValidate;
	
	public EmpaquetadoValidator(List<OrderItem> items) {
		this.items = items;
		toValidate = new Product();
	}
	
	public EmpaquetadoValidator(List<OrderItem> items, OrderItem toValidate) {
		this.items = items;
		this.toValidate = toValidate.getProduct();
	}

	@Override
	public boolean validate() {
		for(OrderItem oi : items) {
			if(oi.getProduct().getID() == toValidate.getID())
				return true;
		}
		return false;
	}
	
	public boolean validate(int productID) {
		toValidate.setID(productID);;
		return this.validate();
	}

}
