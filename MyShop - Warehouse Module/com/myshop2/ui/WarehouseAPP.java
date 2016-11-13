package com.myshop2.ui;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.myshop.warehouse.controllers.OrderController;
import com.myshop2.time.TimerThread;
import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.cursors.CursorMode;
import com.myshop2.ui.fonts.SystemFont;
import com.myshop2.ui.loaders.LoadPedidosInContainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

public class WarehouseAPP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel Inicio;
	private JPanel VistaListaPedidos;
	private JPanel VistaPedidoIndividual;
	private JPanel VistaOTs;
	private JPanel VistaOTIndividual;
	private JPanel VistaEmpaquetado;
	private JPanel VistaEmpaquetadoIndividual;
	private JLabel lblInicioDeLa;
	private JScrollPane scPaneListaPedidos;
	private JButton btnEntrar;
	private JPanel navigationBar;
	private JPanel tabBar;
	private JLabel time;
	private JLabel pedidosTitleCounter;
	private JTextField txtIdAlmacenero;
	private JLabel lblIdAlmacenero;
	private JLabel tabIconPedidos;
	private JLabel tabIconOT;
	private JLabel lblEmpaquetado;
	private JLabel statusBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.setProperty("awt.useSystemAAFontSettings", "on");
					System.setProperty("swing.aatext", "true");
					WarehouseAPP frame = new WarehouseAPP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WarehouseAPP() {
		new TimerThread(getTime()).start();
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 667);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getInicio(), "inicio");
		contentPane.add(getVistaListaPedidos(), "vistaListaPedidos");
		contentPane.add(getVistaPedidoIndividual(), "vistaPedidoIndividual");
		contentPane.add(getVistaOTs(), "vistaOTs");
		contentPane.add(getVistaOTIndividual(), "vistaOTIndividual");
		contentPane.add(getVistaEmpaquetado(), "vistaEmpaquetado");
		contentPane.add(getVistaEmpaquetadoIndividual(), "vistaEmpaquetadoIndividual");
	}

	private JPanel getInicio() {
		if (Inicio == null) {
			Inicio = new JPanel();
			Inicio.setBackground(Color.WHITE);
			Inicio.setLayout(null);
			Inicio.add(getLblInicioDeLa());
			Inicio.add(getBtnEntrar());
			Inicio.add(getTxtIdAlmacenero());
			Inicio.add(getLblIdAlmacenero());
		}
		return Inicio;
	}

	private JPanel getVistaListaPedidos() {
		if (VistaListaPedidos == null) {
			VistaListaPedidos = new JPanel();
			VistaListaPedidos.setBorder(null);
			VistaListaPedidos.setBackground(Color.WHITE);
			VistaListaPedidos.setLayout(null);
			VistaListaPedidos.add(getScPaneListaPedidos());
			VistaListaPedidos.add(getNavigationBar());
			VistaListaPedidos.add(getTabBar());
		}
		return VistaListaPedidos;
	}

	private JPanel getVistaPedidoIndividual() {
		if (VistaPedidoIndividual == null) {
			VistaPedidoIndividual = new JPanel();
			VistaPedidoIndividual.setBackground(Color.WHITE);
		}
		return VistaPedidoIndividual;
	}

	private JPanel getVistaOTs() {
		if (VistaOTs == null) {
			VistaOTs = new JPanel();
			VistaOTs.setBackground(Color.WHITE);
		}
		return VistaOTs;
	}

	private JPanel getVistaOTIndividual() {
		if (VistaOTIndividual == null) {
			VistaOTIndividual = new JPanel();
			VistaOTIndividual.setBackground(Color.WHITE);
		}
		return VistaOTIndividual;
	}

	private JPanel getVistaEmpaquetado() {
		if (VistaEmpaquetado == null) {
			VistaEmpaquetado = new JPanel();
			VistaEmpaquetado.setBackground(Color.WHITE);
		}
		return VistaEmpaquetado;
	}

	private JPanel getVistaEmpaquetadoIndividual() {
		if (VistaEmpaquetadoIndividual == null) {
			VistaEmpaquetadoIndividual = new JPanel();
			VistaEmpaquetadoIndividual.setBackground(Color.WHITE);
		}
		return VistaEmpaquetadoIndividual;
	}

	private JLabel getLblInicioDeLa() {
		if (lblInicioDeLa == null) {
			lblInicioDeLa = new JLabel("myShop");
			lblInicioDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicioDeLa.setBounds(55, 128, 254, 57);
			lblInicioDeLa.setFont(SystemFont.HEADER);
		}
		return lblInicioDeLa;
	}

	private JScrollPane getScPaneListaPedidos() {
		if (scPaneListaPedidos == null) {
			scPaneListaPedidos = new JScrollPane();
			scPaneListaPedidos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPaneListaPedidos.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPaneListaPedidos.setViewportBorder(null);
			scPaneListaPedidos.setBounds(0, 64, 375, 532);
			scPaneListaPedidos.setBorder(BorderFactory.createEmptyBorder());
		}
		return scPaneListaPedidos;
	}

	private void loadPedidosInView() throws ParseException {
		getScPaneListaPedidos().getViewport().setView(LoadPedidosInContainer
				.load(new OrderController().getNotAssigned(), getScPaneListaPedidos().getWidth()));
		revalidate();
		repaint();
	}

	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {
							// Here we invoke the method that will change the localization.
							try {
								CursorMode.wait(btnEntrar);
								loadPedidosInView();
								getPedidosTitleCounter().setText("Pedidos ("+LoadPedidosInContainer.numberOfOrders()+")");
							} catch (ParseException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
							return null;
						}

						@Override
						protected void done() {
							CursorMode.normal(btnEntrar);
							((CardLayout) getContentPane().getLayout()).show(getContentPane(), "vistaListaPedidos");
						}
					};

					sw.execute(); // this will start the processing on a separate thread
				}
			});
			btnEntrar.setBounds(129, 340, 117, 29);
		}
		return btnEntrar;
	}

	private JPanel getNavigationBar() {
		if (navigationBar == null) {
			navigationBar = new JPanel();
			navigationBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineGray));
			navigationBar.setBounds(0, 0, 375, 64);
			navigationBar.setBackground(SystemColor.lightGray);
			navigationBar.setLayout(null);
			navigationBar.add(getTime());
			navigationBar.add(getPedidosTitleCounter());
			navigationBar.add(getStatusBar());
		}
		return navigationBar;
	}

	private JPanel getTabBar() {
		if (tabBar == null) {
			tabBar = new JPanel();
			tabBar.setBounds(0, 596, 375, 49);
			tabBar.setBackground(SystemColor.lightGray);
			tabBar.setLayout(null);
			tabBar.add(getTabIconPedidos());
			tabBar.add(getTabIconOT());
			tabBar.add(getLblEmpaquetado());
		}
		return tabBar;
	}
	private JLabel getTime() {
		if (time == null) {
			time = new JLabel("12:41 AM");
			time.setHorizontalAlignment(SwingConstants.CENTER);
			time.setFont(SystemFont.smallerText);
			time.setBounds(157, 0, 61, 16);
		}
		return time;
	}
	private JLabel getPedidosTitleCounter() {
		if (pedidosTitleCounter == null) {
			pedidosTitleCounter = new JLabel("Pedidos (5)");
			pedidosTitleCounter.setHorizontalAlignment(SwingConstants.CENTER);
			pedidosTitleCounter.setBounds(131, 30, 112, 24);
			pedidosTitleCounter.setFont(SystemFont.TITLE);
		}
		return pedidosTitleCounter;
	}
	private JTextField getTxtIdAlmacenero() {
		if (txtIdAlmacenero == null) {
			txtIdAlmacenero = new JTextField();
			txtIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txtIdAlmacenero.setBounds(44, 302, 286, 26);
			txtIdAlmacenero.setColumns(10);
			txtIdAlmacenero.setFont(SystemFont.normalText);
		}
		return txtIdAlmacenero;
	}
	private JLabel getLblIdAlmacenero() {
		if (lblIdAlmacenero == null) {
			lblIdAlmacenero = new JLabel("ID Almacenero:");
			lblIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			lblIdAlmacenero.setBounds(116, 281, 142, 16);
			lblIdAlmacenero.setFont(SystemFont.DESCRIPTION);
		}
		return lblIdAlmacenero;
	}
	private JLabel getTabIconPedidos() {
		if (tabIconPedidos == null) {
			tabIconPedidos = new JLabel("Pedidos");
			tabIconPedidos.setIconTextGap(-2);
			tabIconPedidos.setHorizontalTextPosition(SwingConstants.CENTER);
			tabIconPedidos.setHorizontalAlignment(SwingConstants.CENTER);
			tabIconPedidos.setVerticalTextPosition(SwingConstants.BOTTOM);
			tabIconPedidos.setFont(SystemFont.tabBarText);
			tabIconPedidos.setForeground(SystemColor.blue);
			tabIconPedidos.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/basket-selected.png")));
			tabIconPedidos.setBounds(6, 3, 62, 43);
		}
		return tabIconPedidos;
	}
	private JLabel getTabIconOT() {
		if (tabIconOT == null) {
			tabIconOT = new JLabel("OTs");
			tabIconOT.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/management.png")));
			tabIconOT.setVerticalTextPosition(SwingConstants.BOTTOM);
			tabIconOT.setIconTextGap(-2);
			tabIconOT.setHorizontalTextPosition(SwingConstants.CENTER);
			tabIconOT.setHorizontalAlignment(SwingConstants.CENTER);
			tabIconOT.setForeground(SystemColor.textGray);
			tabIconOT.setFont(SystemFont.tabBarText);
			tabIconOT.setBounds(148, 3, 62, 43);
		}
		return tabIconOT;
	}
	private JLabel getLblEmpaquetado() {
		if (lblEmpaquetado == null) {
			lblEmpaquetado = new JLabel("Empaquetado");
			lblEmpaquetado.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/delivery.png")));
			lblEmpaquetado.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblEmpaquetado.setIconTextGap(-2);
			lblEmpaquetado.setHorizontalTextPosition(SwingConstants.CENTER);
			lblEmpaquetado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEmpaquetado.setForeground(SystemColor.textGray);
			lblEmpaquetado.setFont(SystemFont.tabBarText);
			lblEmpaquetado.setBounds(276, 3, 93, 43);
		}
		return lblEmpaquetado;
	}
	private JLabel getStatusBar() {
		if (statusBar == null) {
			statusBar = new JLabel("");
			statusBar.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			statusBar.setBounds(0, 0, 375, 16);
		}
		return statusBar;
	}
}
