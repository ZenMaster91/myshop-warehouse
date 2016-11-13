package com.myshop2.ui.fonts;

import java.awt.Font;

import com.myshop2.ui.fonts.sansserif.SanFranciscoFont;

public class SystemFont {
	
	public static final Font HEADER = new SanFranciscoFont().getSF_UI_Text_Medium().deriveFont(14f);
	public static final Font TITLE = new Font("SFUIText-Medium", Font.PLAIN, 17);
	public static final Font DATE = new Font("SFUIText-Medium", Font.PLAIN, 13);
	public static final Font DESCRIPTION = new Font("SFUIText-Medium", Font.PLAIN, 13);
	public static final Font normalText = new Font("SFUIText-Regular", Font.PLAIN, 13);
	public static final Font smallerText = new Font("SFUIText-Medium", Font.PLAIN, 11);
	public static final Font tabBarText = new Font("SFUIText-Medium", Font.PLAIN, 10);

}
