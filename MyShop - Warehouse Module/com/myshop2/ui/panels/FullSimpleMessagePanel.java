package com.myshop2.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.fonts.SystemFont;

public class FullSimpleMessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNoHayPedidos;

	/**
	 * Create the panel.
	 */
	public FullSimpleMessagePanel(String message) {
		setBackground(Color.WHITE);
		setLayout(null);
		getLblNoHayPedidos().setText(message);
		add(getLblNoHayPedidos());

	}
	private JLabel getLblNoHayPedidos() {
		if (lblNoHayPedidos == null) {
			lblNoHayPedidos = new JLabel("No hay Pedidos");
			lblNoHayPedidos.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoHayPedidos.setBounds(6, 145, 363, 96);
			lblNoHayPedidos.setFont(new SystemFont().bigLabel);
			lblNoHayPedidos.setForeground(SystemColor.textGray);
		}
		return lblNoHayPedidos;
	}
}
