package com.myshop2.ui.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.fonts.SystemFont;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import java.awt.Dimension;
import java.awt.Color;

public class DefaultListPanelSmall extends JPanel {

	public static final long serialVersionUID = 1L;

	public JSeparator separator;
	public JLabel title, dateMoreInfo, descLine1;
	public JLabel indicator;

	/**
	 * Create the panel.
	 */
	public DefaultListPanelSmall() {
		setBorder(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(365, 44));
		setLayout(null);
		add(getTitle());
		add(getDateMoreInfo());
		add(getDescLine1());
		add(getSeparator());
		add(getIndicator());

	}

	public JLabel getDateMoreInfo() {
		if (dateMoreInfo == null) {
			dateMoreInfo = new JLabel("nov 11, 10:30 AM");
			dateMoreInfo.setIconTextGap(5);
			dateMoreInfo.setFont(SystemFont.caption_1);
			dateMoreInfo.setHorizontalTextPosition(SwingConstants.LEFT);
			dateMoreInfo.setIcon(
					new ImageIcon(DefaultListPanelSmall.class.getResource("/com/myshop2/ui/icons/next-small.png")));
			dateMoreInfo.setBackground(Color.WHITE);
			dateMoreInfo.setForeground(SystemColor.textGray);
			dateMoreInfo.setHorizontalAlignment(SwingConstants.RIGHT);
			dateMoreInfo.setBounds(239, 14, 129, 16);
		}
		return dateMoreInfo;
	}
	
	public DefaultListPanelSmall setDateInfo(String content) {
		getDateMoreInfo().setText(content);
		return this;
	}
	
	public JLabel getDescLine1() {
		if (descLine1 == null) {
			descLine1 = new JLabel("Nº Objetos: 5");
			descLine1.setBackground(Color.WHITE);
			descLine1.setForeground(SystemColor.textGray);
			descLine1.setBounds(119, 11, 240, 22);
			descLine1.setFont(SystemFont.caption_1);
		}
		return descLine1;
	}
	
	public DefaultListPanelSmall setDescLine1(String content) {
		getDescLine1().setText(content);
		return this;
	}
	
	public DefaultListPanelSmall setDescLine2(String content) {
		getDescLine1().setText(getDescLine1().getText()+" "+content);
		return this;
	}

	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(25, 38, 420, 10);
			separator.setBackground(SystemColor.lightGray);
		}
		return separator;
	}

	public JLabel getTitle() {
		if (title == null) {
			title = new JLabel("P.XXX");
			title.setBackground(Color.WHITE);
			title.setVerticalAlignment(SwingConstants.BOTTOM);
			title.setFont(SystemFont.headline);
			title.setBounds(25, 12, 93, 20);
		}
		return title;
	}
	
	public DefaultListPanelSmall setTitle(String content) {
		getTitle().setText(content);
		return this;
	}
	
	public JLabel getIndicator() {
		if (indicator == null) {
			indicator = new JLabel("");
			indicator.setIcon(new ImageIcon(DefaultListPanelSmall.class.getResource("/com/myshop2/ui/icons/warning.png")));
			indicator.setBounds(8, 14, 16, 16);
		}
		return indicator;
	}
	
	public DefaultListPanelSmall activeIndicator(boolean active) {
		getIndicator().setVisible(active);
		return this;
	}
}
