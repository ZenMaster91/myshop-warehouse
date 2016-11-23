package com.myshop2.ui.panels;

import javax.swing.JPanel;

import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.fonts.SystemFont;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.ImageIcon;

public class TabBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel pedidos;
	private JLabel ots;
	private JLabel empaquetado;
	private JLabel envios;

	/**
	 * Create the panel.
	 */
	public TabBar() {
		setLayout(null);
		add(getPedidos());
		add(getOts());
		add(getEmpaquetado());
		add(getEnvios());
		super.setBackground(SystemColor.lightGray);
		super.setBounds(0, 459, 375, 49);
		super.setBorder(new MatteBorder(1, 0, 0, 0, (Color) SystemColor.lineGray));
	}
	
	public TabBar setActive (JLabel menuItem) {
		if(menuItem == getPedidos()) {
			getPedidos().setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/basket-selected.png")));
			getPedidos().setForeground(SystemColor.blue);
		} else if(menuItem == getOts()) {
			getOts().setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/management-selected.png")));
			getOts().setForeground(SystemColor.blue);
		} else if(menuItem == getEmpaquetado()) {
			getEmpaquetado().setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/delivery-selected.png")));
			getEmpaquetado().setForeground(SystemColor.blue);
		} else if(menuItem == getEnvios()) {
			getEnvios().setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/earth-selected.png")));
			getEnvios().setForeground(SystemColor.blue); 
		}
		
		return this;
	}

	public JLabel getPedidos() {
		if (pedidos == null) {
			pedidos = new JLabel("Pedidos");
			pedidos.setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/basket.png")));
			pedidos.setVerticalTextPosition(SwingConstants.BOTTOM);
			pedidos.setIconTextGap(-2);
			pedidos.setHorizontalTextPosition(SwingConstants.CENTER);
			pedidos.setHorizontalAlignment(SwingConstants.CENTER);
			pedidos.setForeground(SystemColor.textGray);
			pedidos.setFont(new SystemFont().tabBarText);
			pedidos.setBounds(6, 3, 62, 43);
		}
		return pedidos;
	}
	
	public JLabel getOts() {
		if (ots == null) {
			ots = new JLabel("OTs");
			ots.setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/management.png")));
			ots.setVerticalTextPosition(SwingConstants.BOTTOM);
			ots.setIconTextGap(-2);
			ots.setHorizontalTextPosition(SwingConstants.CENTER);
			ots.setHorizontalAlignment(SwingConstants.CENTER);
			ots.setForeground(SystemColor.textGray);
			ots.setFont(new SystemFont().tabBarText);
			ots.setBounds(100, 3, 62, 43);
		}
		return ots;
	}
	
	public JLabel getEmpaquetado() {
		if (empaquetado == null) {
			empaquetado = new JLabel("Empaquetado");
			empaquetado.setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/delivery.png")));
			empaquetado.setVerticalTextPosition(SwingConstants.BOTTOM);
			empaquetado.setIconTextGap(-2);
			empaquetado.setHorizontalTextPosition(SwingConstants.CENTER);
			empaquetado.setHorizontalAlignment(SwingConstants.CENTER);
			empaquetado.setForeground(SystemColor.textGray);
			empaquetado.setFont(new SystemFont().tabBarText);
			empaquetado.setBounds(187, 3, 93, 43);
		}
		return empaquetado;
	}
	private JLabel getEnvios() {
		if (envios == null) {
			envios = new JLabel("Envios");
			envios.setIcon(new ImageIcon(TabBar.class.getResource("/com/myshop2/ui/icons/earth.png")));
			envios.setVerticalTextPosition(SwingConstants.BOTTOM);
			envios.setIconTextGap(-2);
			envios.setHorizontalTextPosition(SwingConstants.CENTER);
			envios.setHorizontalAlignment(SwingConstants.CENTER);
			envios.setForeground(SystemColor.textGray);
			envios.setFont(new SystemFont().tabBarText);
			envios.setBounds(307, 3, 62, 43);
		}
		return envios;
	}
}
