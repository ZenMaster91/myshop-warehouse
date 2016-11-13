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

public class DefaultListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JSeparator separator;
	private JLabel title, dateMoreInfo, descLine1, descLine2;
	private JLabel indicator;

	/**
	 * Create the panel.
	 */
	public DefaultListPanel() {
		setBorder(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(365, 80));
		setLayout(null);
		add(getTitle());
		add(getDateMoreInfo());
		add(getDescLine1());
		add(getDescLine2());
		add(getSeparator());
		add(getIndicator());

	}

	public JLabel getDateMoreInfo() {
		if (dateMoreInfo == null) {
			dateMoreInfo = new JLabel("Noviembre 11, 10:30 AM");
			dateMoreInfo.setIconTextGap(5);
			dateMoreInfo.setFont(SystemFont.DATE);
			dateMoreInfo.setHorizontalTextPosition(SwingConstants.LEFT);
			dateMoreInfo.setIcon(
					new ImageIcon(DefaultListPanel.class.getResource("/com/myshop2/ui/icons/Next.png")));
			dateMoreInfo.setBackground(Color.WHITE);
			dateMoreInfo.setForeground(SystemColor.textGray);
			dateMoreInfo.setHorizontalAlignment(SwingConstants.RIGHT);
			dateMoreInfo.setBounds(125, 10, 243, 16);
		}
		return dateMoreInfo;
	}
	public JLabel getDescLine1() {
		if (descLine1 == null) {
			descLine1 = new JLabel("Nº Objetos: 5");
			descLine1.setBackground(Color.WHITE);
			descLine1.setForeground(SystemColor.textGray);
			descLine1.setBounds(25, 27, 334, 22);
			descLine1.setFont(SystemFont.DESCRIPTION);
		}
		return descLine1;
	}

	public JLabel getDescLine2() {
		if (descLine2 == null) {
			descLine2 = new JLabel("Peso: 10Kg");
			descLine2.setBackground(Color.WHITE);
			descLine2.setForeground(SystemColor.textGray);
			descLine2.setBounds(25, 50, 334, 22);
			descLine2.setFont(SystemFont.DESCRIPTION);
		}
		return descLine2;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(25, 74, 420, 10);
			separator.setBackground(SystemColor.lightGray);
		}
		return separator;
	}

	public JLabel getTitle() {
		if (title == null) {
			title = new JLabel("Pedido: XXX");
			title.setBackground(Color.WHITE);
			title.setVerticalAlignment(SwingConstants.BOTTOM);
			title.setFont(SystemFont.TITLE);
			title.setBounds(25, 6, 123, 20);
		}
		return title;
	}
	
	public JLabel getIndicator() {
		if (indicator == null) {
			indicator = new JLabel("");
			indicator.setIcon(new ImageIcon(DefaultListPanel.class.getResource("/com/myshop2/ui/icons/warning.png")));
			indicator.setBounds(8, 7, 16, 16);
		}
		return indicator;
	}
}
