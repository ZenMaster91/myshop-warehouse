package com.myshop2.ui.mouse;


import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.myshop2.ui.WarehouseAPP;

public class GoToEnviosMouseListener {
	
	public static MouseAdapter get(WarehouseAPP app) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((CardLayout) app.getContentPane().getLayout()).show(app.getContentPane(), "vistaEnvios");
				// app.updateVistaEmpaquetado();
			}
		};
	}

}
