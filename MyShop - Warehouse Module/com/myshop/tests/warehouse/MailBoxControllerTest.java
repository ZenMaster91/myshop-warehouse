package com.myshop.tests.warehouse;

import org.junit.Test;

import com.myshop.model.order.MailBox;
import com.myshop.warehouse.controllers.MailBoxController;
import com.myshop2.session.Session;

public class MailBoxControllerTest {

	@Test
	public void test() {
		for(MailBoxController mbc : MailBoxController.getReadyMailBoxes()) {
			System.out.println("Mail Box: " +mbc.getMailBox().getID());
		}
	}

}
