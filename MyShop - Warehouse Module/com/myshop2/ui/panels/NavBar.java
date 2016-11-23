package com.myshop2.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.border.MatteBorder;
import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.fonts.SystemFont;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class NavBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblTime;
	private JLabel lblTitle;
	private JLabel label_2;
	private JLabel lblBack;
	private JLabel lblAction;

	/**
	 * Create the panel.
	 */
	public NavBar(String backButton, String title, String callout) {
		super.setBackground(SystemColor.lightGray);
		super.setBounds(0, 0, 375, 64);
		setLayout(null);
		add(getPanel());
		getLblBack().setText(backButton);
		getLblTitle().setText(title);
		getLblAction().setText(callout);

	}
	
	public NavBar setBackVisible(boolean visible) {
		getLblBack().setVisible(visible);
		return this;
	}
	
	public NavBar setTitleVisible(boolean visible) {
		getLblTitle().setVisible(visible);
		return this;
	}
	
	public NavBar setActionVisible(boolean visible) {
		getLblAction().setVisible(visible);
		return this;
	}
	
	public NavBar setBackAction(MouseAdapter action) {
		getLblBack().addMouseListener(action);
		return this;
	}
	
	public NavBar setCallOutAction(MouseAdapter action) {
		getLblAction().addMouseListener(action);
		return this;
	}
	
	public NavBar setCallOutEnabled(boolean enabled) {
		getLblAction().setEnabled(enabled);
		return this;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.lineGray));
			panel.setBackground(new Color(247, 247, 247));
			panel.setBounds(0, 0, 375, 64);
			panel.add(getLblTime());
			panel.add(getLblTitle());
			panel.add(getLabel_2());
			panel.add(getLblBack());
			panel.add(getLblAction());
		}
		return panel;
	}
	
	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel("12:41 AM");
			lblTime.setHorizontalAlignment(SwingConstants.CENTER);
			lblTime.setFont(SystemFont.caption_1);
			lblTime.setBounds(157, 0, 61, 16);
		}
		return lblTime;
	}
	
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Pedidos (5)");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(SystemFont.headline);
			lblTitle.setBounds(86, 30, 202, 24);
		}
		return lblTitle;
	}
	
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setIcon(new ImageIcon(NavBar.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_2.setBounds(0, 0, 375, 16);
		}
		return label_2;
	}
	
	private JLabel getLblBack() {
		if (lblBack == null) {
			lblBack = new JLabel("Pedidos (-)");
			lblBack.setIcon(new ImageIcon(NavBar.class.getResource("/com/myshop2/ui/icons/back-arrow.png")));
			lblBack.setIconTextGap(10);
			lblBack.setHorizontalAlignment(SwingConstants.LEFT);
			lblBack.setForeground(SystemColor.blue);
			lblBack.setFont(SystemFont.callout);
			lblBack.setBounds(10, 31, 166, 24);
		}
		return lblBack;
	}
	
	private JLabel getLblAction() {
		if (lblAction == null) {
			lblAction = new JLabel("Generar O.T");
			lblAction.setIconTextGap(8);
			lblAction.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAction.setForeground(SystemColor.blue);
			lblAction.setFont(SystemFont.callout);
			lblAction.setBounds(186, 26, 183, 34);
		}
		return lblAction;
	}
}
