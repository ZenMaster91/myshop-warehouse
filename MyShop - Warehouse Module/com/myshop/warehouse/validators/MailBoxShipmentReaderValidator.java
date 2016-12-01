package com.myshop.warehouse.validators;

import java.util.List;

import com.myshop.model.order.MailBox;
import com.myshop.warehouse.controllers.MailBoxController;

public class MailBoxShipmentReaderValidator implements Validator {
	
	private List<MailBoxController> boxes;
	private int boxID;
	
	public MailBoxShipmentReaderValidator(String boxID, List<MailBoxController> boxes) {
		this.boxes = boxes;
		this.boxID = Integer.parseInt(boxID);
	}

	@Override
	public boolean validate() {
		for(MailBoxController mbc : boxes) {
			if(mbc.getMailBox().getID() == boxID)
				return true;
		} return false;
	}
	
	public MailBox validateRet() {
		for(MailBoxController mbc : boxes) {
			if(mbc.getMailBox().getID() == boxID)
				return mbc.getMailBox();
		} return null;
	}

}
