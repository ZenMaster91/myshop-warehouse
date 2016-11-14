package com.myshop2.ui.fonts;

import java.awt.Font;

import com.myshop2.ui.displays.RetinaDisplay;
import com.myshop2.ui.fonts.sanfrancisco.SanFranciscoFont;

public class SystemFont {

	public Font header = new SanFranciscoFont().getSF_UI_Text_Medium().deriveFont(14f);
	public Font title, date, description, normalText, smallerText, tabBarText;

	public SystemFont() {
		int scale = 1;
		if (RetinaDisplay.hasRetinaDisplay()) {
			scale = 2;
			//System.out.println("Retina Display: " + (13 / 2) + 1);
		}
		title = new Font("SFUIText-Medium", Font.PLAIN, (17 / scale) + 1);
		date = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		description = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		normalText = new Font("SFUIText-Regular", Font.PLAIN, (13 / scale) + 1);
		smallerText = new Font("SFUIText-Medium", Font.PLAIN, (11 / scale) + 1);
		tabBarText = new Font("SFUIText-Medium", Font.PLAIN, (10 / scale) + 1);
	}

}
