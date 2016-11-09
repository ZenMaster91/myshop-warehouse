package com.myshop.warehouse.validators;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.order.Status;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.util.DefaultSql2o;

public class PedidoRecogidoEnteroValidator implements Validator {
	
	private WorkingPlanController wpc;
	
	public PedidoRecogidoEnteroValidator(WorkingPlanController wpc) {
		this.wpc = wpc;
	}

	@Override
	public boolean validate() {
		if(wpc.getParent()!=null) {
			return new PedidoRecogidoEnteroValidator(wpc.getParent()).validate();
		} else {
			for(WorkingPlanItem wpi: wpc.getItems()) {
				if(!wpi.getCollected())
					return false;
			}
			
			for(WorkingPlanController wpcChild : wpc.getChilds()) {
				for(WorkingPlanItem wpiChild: wpcChild.getItems()) {
					if(!wpiChild.getCollected())
						return false;
				}
			}
			wpc.getWp().getItems().get(0).getOrderItem().getParent().setStatus(Status.PENDIENTE_EMPAQUETADO.toString().toUpperCase());
			String setPendienteEmpaquetado = "UPDATE myshop.order SET myshop.order.status_id=3 WHERE myshop.order.order_id = :order_id";
			try(Connection con = DefaultSql2o.SQL2O.open()) {
				Query query = con.createQuery(setPendienteEmpaquetado);
				for(WorkingPlanItem wpi: wpc.getWp().getItems())
					query.addParameter("order_id", wpi.getOrderItem().getParent().getID()).addToBatch();
				query.executeBatch();
			}
			
		}
		return true;
	}

}
