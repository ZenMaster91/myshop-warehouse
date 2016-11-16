package com.myshop2.ui.displays;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.lang.reflect.Field;

public class RetinaDisplay {

	public static boolean hasRetinaDisplay() {
		boolean isRetina = false;
		GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	 
		try {
			Field field = graphicsDevice.getClass().getDeclaredField("scale");
			if (field != null) {
				field.setAccessible(true);
				Object scale = field.get(graphicsDevice);
				if(scale instanceof Integer && ((Integer) scale).intValue() == 2) {
					isRetina = true;
				}
			}
		} catch (Exception e) {
			System.err.println("The system does not support retina display.");
		}
		return isRetina;
	}
}
