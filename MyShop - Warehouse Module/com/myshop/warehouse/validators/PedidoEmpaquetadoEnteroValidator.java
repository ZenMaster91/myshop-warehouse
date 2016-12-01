package com.myshop.warehouse.validators;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.util.DefaultSql2o;

public class PedidoEmpaquetadoEnteroValidator implements Validator {
	
	private Order o;
	
	public PedidoEmpaquetadoEnteroValidator(Order o) {
		this.o = o;
	}

	@Override
	public boolean validate() {
		/*for(OrderItem oi : o.getProducts()) {
			if(oi.getMailbox()==null)
				return false;
		}*/
		
		int artEmp = 0;
		System.out.println(">> Haciendo comprobación de pedido completo");
		for(OrderItem oi : o.getProducts()) {
			artEmp += oi.itemsPackaged;
		}
		System.out.println(">> Articulos empaquetados totales " + artEmp + " sobre " + new OrderController(o).getNumberOfItems() + " que contiene el pedido");
		if(artEmp < new OrderController(o).getNumberOfItems())
			return false;
		
		String updateOrderStatus = "UPDATE myshop.order AS o SET o.status_id = 6 WHERE o.order_id = :id";
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			Query query = con.createQuery(updateOrderStatus);
			query.addParameter("id", o.getID()).executeUpdate();
		}
		// Session.mailbox = null; No es automático...
		return true;
	}

}
