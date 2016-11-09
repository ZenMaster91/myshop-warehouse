package com.myshop.warehouse.validators;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;

public class PedidoEmpaquetadoEnteroValidator implements Validator {
	
	private Order o;
	
	public PedidoEmpaquetadoEnteroValidator(Order o) {
		this.o = o;
	}

	@Override
	public boolean validate() {
		for(OrderItem oi : o.getProducts()) {
			if(oi.getMailbox()==null)
				return false;
		} return true;
	}

}
