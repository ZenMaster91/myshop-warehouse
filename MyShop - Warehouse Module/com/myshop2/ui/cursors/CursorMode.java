package com.myshop2.ui.cursors;

import java.awt.Component;
import java.awt.Cursor;

public class CursorMode {
	
	public static void wait(Component c) {
		c.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	}
	
	public static void normal(Component c) {
		c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
