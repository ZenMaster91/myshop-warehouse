package com.myshop2.ui.fonts;

import java.awt.Font;

import com.myshop2.ui.displays.RetinaDisplay;
import com.myshop2.ui.fonts.sanfrancisco.SanFranciscoFont;

public class SystemFont {

	public Font header = new SanFranciscoFont().getSF_UI_Text_Medium().deriveFont(14f);
	@Deprecated
	public Font title, date, description, normalText, smallerText, tabBarText, backButton, mediumText, blackText, bigLabel;
	public static Font title_1 = new Font("SFUIText-Medium", Font.PLAIN, 17);
	public static Font title_2 = new Font("SFUIText-Regular", Font.PLAIN, 17);
	public static Font title_3 = new Font("SFUIText-Regulat", Font.PLAIN, 16);
	public static Font headline = new Font("SFUIText-Medium", Font.PLAIN, 17);
	public static Font body = new Font("SFUIText-Regular", Font.PLAIN, 14);
	public static Font callout = new Font("SFUIText-Medium", Font.PLAIN, 14);
	public static Font subhead = new Font("SFUIText-Regular", Font.PLAIN, 14);
	public static Font footnote = new Font("SFUIText-Regular", Font.PLAIN, 12);
	public static Font caption_1 = new Font("SFUIText-Medium", Font.PLAIN, 12);
	public static Font caption_2 = new Font("SFUIText-Medium", Font.PLAIN, 11);

	@Deprecated
	public SystemFont() {
		int scale = 1;
		if (RetinaDisplay.hasRetinaDisplay()) {
			scale = 2;
			//System.out.println("Retina Display: " + (13 / 2) + 1);
		}
		/*title = headline;
		date = callout;
		description = body;
		normalText = body;
		mediumText = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		blackText  = new Font("SFUIText-Semibold", Font.PLAIN, (13 / scale) + 1);
		smallerText = caption_1;
		tabBarText = caption_2;
		backButton = headline;
		bigLabel = title_1;*/
		
		title = new Font("SFUIText-Medium", Font.PLAIN, (16 / scale) + 1);
		date = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		//date = new SanFranciscoFont().getSF_UI_Text_Medium().deriveFont(6.5f);
		description = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		normalText = new Font("SFUIText-Regular", Font.PLAIN, (13 / scale) + 1);
		mediumText = new Font("SFUIText-Medium", Font.PLAIN, (13 / scale) + 1);
		blackText  = new Font("SFUIText-Semibold", Font.PLAIN, (13 / scale) + 1);
		smallerText = new Font("SFUIText-Medium", Font.PLAIN, (11 / scale) + 1);
		tabBarText = new Font("SFUIText-Medium", Font.PLAIN, (10 / scale) + 1);
		backButton = new Font("SFUIText-Medium", Font.PLAIN, (14 / scale) + 1);
		bigLabel = new Font("SFUIText-Medium", Font.PLAIN, (22 / scale) + 1);
	}

}
