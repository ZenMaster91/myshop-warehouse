package com.myshop2.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.myshop.warehouse.controllers.OrderController;
import com.myshop2.session.Session;
import com.myshop2.time.TimerThread;
import com.myshop2.ui.colors.SystemColor;
import com.myshop2.ui.fonts.SystemFont;
import com.myshop2.ui.loaders.LoadOTsInContainer;
import com.myshop2.ui.loaders.LoadPedidosInContainer;
import com.myshop2.ui.loaders.LoadPedidosInEmpaquetado;
import com.myshop2.ui.mouse.CursorMode;
import com.myshop2.ui.mouse.GoToEmpaquetadoMouseListener;
import com.myshop2.ui.mouse.GoToOTsMouseListener;
import com.myshop2.ui.mouse.GoToPedidosMouseListener;
import com.myshop2.ui.panels.TabBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import org.jdesktop.xswingx.PromptSupport;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class WarehouseAPP extends JFrame {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	public JPanel contentPane;
	public JPanel Inicio;
	public JPanel VistaListaPedidos;
	public JPanel VistaPedidoIndividual;
	public JPanel VistaOTs;
	public JPanel VistaOTIndividual;
	public JPanel VistaEmpaquetado;
	public JPanel VistaEmpaquetadoIndividual;
	public JLabel lblInicioDeLa;
	public JScrollPane scPaneListaPedidos;
	public JPanel navigationBar;
	public JLabel time;
	public JLabel pedidosTitleCounter;
	public JTextField txtIdAlmacenero;
	public JLabel lblIdAlmacenero;
	public JLabel statusBar;
	public JPanel panel;
	public JLabel timePedidoIndv;
	public JLabel label_2;
	public JLabel lblGenerarOt;
	public JLabel llblPedidosLink;
	public JPanel panel_1;
	public JLabel lblPedido;
	public JLabel lblIdpedido;
	public JLabel lblFecha;
	public JLabel lblFecha_1;
	public JLabel lblNArtculos;
	public JLabel lblNarticulos;
	public JLabel lblPeso;
	public JLabel lblPeso_1;
	public JScrollPane scPedidoIndividual;
	public JPanel panel_2;
	public JLabel timeOts;
	public JLabel lblOrdenes;
	public JLabel label_3;
	public JPanel panel_4;
	public JLabel timeEmpaquetado;
	public JLabel lblEmpaquetar;
	public JLabel label_7;
	public JPanel panel_6;
	public JLabel label;
	public JLabel label_1;
	public JLabel lblEscanear;
	public JLabel lblOts;
	public JPanel panel_7;
	public JLabel lblpedidoOTIndv;
	public JLabel idPedidoOTIndv;
	public JLabel lblfechaOTIndv;
	public JLabel fechaOTIndv;
	public JLabel lblnArticulosOTIndv;
	public JLabel articulosOTIdnv;
	public JLabel lblpesoOTIdiv;
	public JLabel pesoOTIndv;
	public JScrollPane scPaneOTIndiv;
	public JScrollPane scPaneOTs;
	public JScrollPane scPaneEmpaquetado;
	public JPanel panel_8;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel lblEmpaquetar_1;
	public JPanel panel_9;
	public JLabel label_15;
	public JLabel idPedidoEmpInd;
	public JLabel label_17;
	public JLabel fechaEmpInd;
	public JLabel label_19;
	public JLabel narticulosEmpInd;
	public JLabel label_21;
	public JLabel pesoEmpInd;
	public JPanel toolsOTIndv;
	public JPanel scanner;
	public JLabel scannerToolOTidProd;
	public JTextField textField;
	public JPanel incidence;
	public JLabel lblIncidence;
	public JCheckBox chckbxSolucionada;
	public JScrollPane scPaneIncidenceDescription;
	public JTextPane txtIncidence;
	public JPanel feedback;
	public JLabel lblNewLabel;
	public JPanel panel_10;
	private JLabel lblEntrar;

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
		new TimerThread(getTime(),
						getTimePedidoIndv(),
						getTimeOts(),
						getTimeEmpaquetado()).start();
		promptSupport();
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 667);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getInicio(), "name_28087690175426");
		contentPane.add(getVistaListaPedidos(), "vistaListaPedidos");
		contentPane.add(getVistaPedidoIndividual(), "vistaPedidoIndividual");
		contentPane.add(getVistaOTs(), "vistaOTs");
		contentPane.add(getVistaOTIndividual(), "vistaOTIndividual");
		contentPane.add(getVistaEmpaquetado(), "vistaEmpaquetado");
		contentPane.add(getVistaEmpaquetadoIndividual(), "vistaEmpaquetadoIndividual");
	}

	public JPanel getInicio() {
		if (Inicio == null) {
			Inicio = new JPanel();
			Inicio.setBackground(Color.WHITE);
			Inicio.setLayout(null);
			Inicio.add(getLblInicioDeLa());
			Inicio.add(getTxtIdAlmacenero());
			Inicio.add(getLblIdAlmacenero());
			Inicio.add(getLblEntrar());
		}
		return Inicio;
	}

	public JPanel getVistaListaPedidos() {
		if (VistaListaPedidos == null) {
			VistaListaPedidos = new JPanel();
			VistaListaPedidos.setBorder(null);
			VistaListaPedidos.setBackground(Color.WHITE);
			VistaListaPedidos.setLayout(null);
			VistaListaPedidos.add(getScPaneListaPedidos());
			VistaListaPedidos.add(getNavigationBar());
			TabBar tb = new TabBar();
			tb.getOts().addMouseListener(GoToOTsMouseListener.get(this));
			tb.getEmpaquetado().addMouseListener(GoToEmpaquetadoMouseListener.get(this));
			VistaListaPedidos.add(tb.setActive(tb.getPedidos()));
		}
		return VistaListaPedidos;
	}

	public JPanel getVistaPedidoIndividual() {
		if (VistaPedidoIndividual == null) {
			VistaPedidoIndividual = new JPanel();
			VistaPedidoIndividual.setBackground(Color.WHITE);
			VistaPedidoIndividual.setLayout(null);
			VistaPedidoIndividual.add(getPanel());
			VistaPedidoIndividual.add(getPanel_1());
			VistaPedidoIndividual.add(getScPedidoIndividual());
		}
		return VistaPedidoIndividual;
	}

	public JPanel getVistaOTs() {
		if (VistaOTs == null) {
			VistaOTs = new JPanel();
			VistaOTs.setBackground(Color.WHITE);
			VistaOTs.setLayout(null);
			VistaOTs.add(getPanel_2());
			TabBar tb = new TabBar();
			tb.getPedidos().addMouseListener(GoToPedidosMouseListener.get(this));
			tb.getEmpaquetado().addMouseListener(GoToEmpaquetadoMouseListener.get(this));
			VistaOTs.add(tb.setActive(tb.getOts()));
			VistaOTs.add(getScPaneOTs());
		}
		return VistaOTs;
	}

	public JPanel getVistaOTIndividual() {
		if (VistaOTIndividual == null) {
			VistaOTIndividual = new JPanel();
			VistaOTIndividual.setBackground(Color.WHITE);
			VistaOTIndividual.setLayout(null);
			VistaOTIndividual.add(getPanel_6());
			VistaOTIndividual.add(getPanel_7());
			VistaOTIndividual.add(getScPaneOTIndiv());
			VistaOTIndividual.add(getToolsOTIndv());
		}
		return VistaOTIndividual;
	}

	public JPanel getVistaEmpaquetado() {
		if (VistaEmpaquetado == null) {
			VistaEmpaquetado = new JPanel();
			VistaEmpaquetado.setBackground(Color.WHITE);
			VistaEmpaquetado.setLayout(null);
			VistaEmpaquetado.add(getPanel_4());
			TabBar tb = new TabBar();
			tb.getPedidos().addMouseListener(GoToPedidosMouseListener.get(this));
			tb.getOts().addMouseListener(GoToOTsMouseListener.get(this));
			VistaEmpaquetado.add(tb.setActive(tb.getEmpaquetado()));
			VistaEmpaquetado.add(getScPaneEmpaquetado());
		}
		return VistaEmpaquetado;
	}

	public JPanel getVistaEmpaquetadoIndividual() {
		if (VistaEmpaquetadoIndividual == null) {
			VistaEmpaquetadoIndividual = new JPanel();
			VistaEmpaquetadoIndividual.setBackground(Color.WHITE);
			VistaEmpaquetadoIndividual.setLayout(null);
			VistaEmpaquetadoIndividual.add(getPanel_8());
			VistaEmpaquetadoIndividual.add(getPanel_9());
		}
		return VistaEmpaquetadoIndividual;
	}

	public JLabel getLblInicioDeLa() {
		if (lblInicioDeLa == null) {
			lblInicioDeLa = new JLabel("myShop");
			lblInicioDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicioDeLa.setBounds(55, 128, 254, 57);
			lblInicioDeLa.setFont(new SystemFont().header);
		}
		return lblInicioDeLa;
	}

	public JScrollPane getScPaneListaPedidos() {
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

	public void loadPedidosInView() {
		try {
			getScPaneListaPedidos().getViewport().setView(LoadPedidosInContainer
					.load(new OrderController().getNotAssigned(),
							getScPaneListaPedidos().getWidth(),
							getContentPane(),
							"vistaPedidoIndividual",
							getScPedidoIndividual(),
							this));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		revalidate();
		repaint();
		loadOTsInView();
		//loadPedidosInEmpaquetadoView();
	}

	public void loadOTsInView() {
		getScPaneOTs().getViewport().setView(LoadOTsInContainer
				.load(Session.getWorkingPlans(), this));
		revalidate();
		repaint();
	}
	
	public void loadPedidosInEmpaquetadoView() {
		try {
			getScPaneEmpaquetado().getViewport().setView(LoadPedidosInEmpaquetado
					.load(new OrderController().getPendientesEmpaquetando(),
							getScPaneListaPedidos().getWidth(),
							getContentPane(),
							"vistaEmpaquetadoIndividual",
							getScPedidoIndividual(),
							getLblIdpedido(),
							getLblFecha_1(),
							getLblNarticulos(),
							getLblPeso_1()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JPanel getNavigationBar() {
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
	public JLabel getTime() {
		if (time == null) {
			time = new JLabel("12:41 AM");
			time.setHorizontalAlignment(SwingConstants.CENTER);
			time.setFont(new SystemFont().smallerText);
			time.setBounds(157, 0, 61, 16);
		}
		return time;
	}
	public JLabel getPedidosTitleCounter() {
		if (pedidosTitleCounter == null) {
			pedidosTitleCounter = new JLabel("Pedidos (5)");
			pedidosTitleCounter.setHorizontalAlignment(SwingConstants.CENTER);
			pedidosTitleCounter.setBounds(86, 30, 202, 24);
			pedidosTitleCounter.setFont(new SystemFont().title);
		}
		return pedidosTitleCounter;
	}
	public JTextField getTxtIdAlmacenero() {
		if (txtIdAlmacenero == null) {
			txtIdAlmacenero = new JTextField();
			txtIdAlmacenero.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			txtIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txtIdAlmacenero.setBounds(44, 302, 286, 26);
			txtIdAlmacenero.setColumns(10);
			txtIdAlmacenero.setFont(new SystemFont().normalText);
		}
		return txtIdAlmacenero;
	}
	public JLabel getLblIdAlmacenero() {
		if (lblIdAlmacenero == null) {
			lblIdAlmacenero = new JLabel("ID Almacenero:");
			lblIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			lblIdAlmacenero.setBounds(116, 281, 142, 16);
			lblIdAlmacenero.setFont(new SystemFont().description);
		}
		return lblIdAlmacenero;
	}
	public JLabel getStatusBar() {
		if (statusBar == null) {
			statusBar = new JLabel("");
			statusBar.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			statusBar.setBounds(0, 0, 375, 16);
		}
		return statusBar;
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineDark));
			panel.setBackground(SystemColor.lightGray);
			panel.setBounds(0, 0, 375, 64);
			panel.add(getTimePedidoIndv());
			panel.add(getLabel_2());
			panel.add(getLblGenerarOt());
			panel.add(getLlblPedidosLink());
		}
		return panel;
	}
	public JLabel getTimePedidoIndv() {
		if (timePedidoIndv == null) {
			timePedidoIndv = new JLabel("12:41 AM");
			timePedidoIndv.setHorizontalAlignment(SwingConstants.CENTER);
			timePedidoIndv.setFont(null);
			timePedidoIndv.setBounds(157, 0, 61, 16);
			timePedidoIndv.setFont(new SystemFont().smallerText);
		}
		return timePedidoIndv;
	}
	public JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			label_2.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_2.setBounds(0, 0, 375, 16);
		}
		return label_2;
	}
	public JLabel getLblGenerarOt() {
		if (lblGenerarOt == null) {
			lblGenerarOt = new JLabel("Generar O.T");
			lblGenerarOt.setIconTextGap(8);
			lblGenerarOt.setIcon(null);
			lblGenerarOt.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGenerarOt.setForeground(SystemColor.blue);
			lblGenerarOt.setFont(new SystemFont().backButton);
			lblGenerarOt.setBounds(184, 23, 183, 34);
		}
		return lblGenerarOt;
	}
	public JLabel getLlblPedidosLink() {
		if (llblPedidosLink == null) {
			llblPedidosLink = new JLabel("Pedidos (-)");
			llblPedidosLink.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout)getContentPane().getLayout()).show(getContentPane(), "vistaListaPedidos");
				}
			});
			llblPedidosLink.setIconTextGap(10);
			llblPedidosLink.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/back-arrow.png")));
			llblPedidosLink.setHorizontalAlignment(SwingConstants.LEFT);
			llblPedidosLink.setFont(new SystemFont().backButton);
			llblPedidosLink.setForeground(SystemColor.blue);
			llblPedidosLink.setBounds(6, 28, 166, 24);
		}
		return llblPedidosLink;
	}
	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.lineGray));
			panel_1.setBackground(SystemColor.lightGrayPanel);
			panel_1.setBounds(0, 64, 375, 73);
			panel_1.setLayout(null);
			panel_1.add(getLblPedido());
			panel_1.add(getLblIdpedido());
			panel_1.add(getLblFecha());
			panel_1.add(getLblFecha_1());
			panel_1.add(getLblNArtculos());
			panel_1.add(getLblNarticulos());
			panel_1.add(getLblPeso());
			panel_1.add(getLblPeso_1());
		}
		return panel_1;
	}
	public JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido:");
			lblPedido.setFont(new SystemFont().normalText);
			lblPedido.setBounds(25, 6, 61, 16);
		}
		return lblPedido;
	}
	public JLabel getLblIdpedido() {
		if (lblIdpedido == null) {
			lblIdpedido = new JLabel("idPedido");
			lblIdpedido.setFont(new SystemFont().blackText);
			lblIdpedido.setBounds(79, 6, 61, 16);
		}
		return lblIdpedido;
	}
	public JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new SystemFont().normalText);
			lblFecha.setBounds(25, 26, 61, 16);
		}
		return lblFecha;
	}
	public JLabel getLblFecha_1() {
		if (lblFecha_1 == null) {
			lblFecha_1 = new JLabel("fecha");
			lblFecha_1.setFont(new SystemFont().blackText);
			lblFecha_1.setBounds(73, 26, 285, 16);
		}
		return lblFecha_1;
	}
	public JLabel getLblNArtculos() {
		if (lblNArtculos == null) {
			lblNArtculos = new JLabel("Nº Artículos:");
			lblNArtculos.setFont(new SystemFont().normalText);
			lblNArtculos.setBounds(25, 47, 100, 16);
		}
		return lblNArtculos;
	}
	public JLabel getLblNarticulos() {
		if (lblNarticulos == null) {
			lblNarticulos = new JLabel("narticulos");
			lblNarticulos.setFont(new SystemFont().blackText);
			lblNarticulos.setBounds(112, 47, 92, 16);
		}
		return lblNarticulos;
	}
	public JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso:");
			lblPeso.setFont(new SystemFont().normalText);
			lblPeso.setBounds(170, 47, 46, 16);
		}
		return lblPeso;
	}
	public JLabel getLblPeso_1() {
		if (lblPeso_1 == null) {
			lblPeso_1 = new JLabel("peso");
			lblPeso_1.setFont(new SystemFont().blackText);
			lblPeso_1.setBounds(210, 47, 126, 16);
		}
		return lblPeso_1;
	}
	public JScrollPane getScPedidoIndividual() {
		if (scPedidoIndividual == null) {
			scPedidoIndividual = new JScrollPane();
			scPedidoIndividual.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPedidoIndividual.setBounds(0, 137, 375, 508);
			scPedidoIndividual.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPedidoIndividual.setViewportBorder(null);
			scPedidoIndividual.setBorder(BorderFactory.createEmptyBorder());
		}
		return scPedidoIndividual;
	}
	public JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineGray));
			panel_2.setBackground(new Color(247, 247, 247));
			panel_2.setBounds(0, 0, 375, 64);
			panel_2.add(getTimeOts());
			panel_2.add(getLblOrdenes());
			panel_2.add(getLabel_3());
		}
		return panel_2;
	}
	public JLabel getTimeOts() {
		if (timeOts == null) {
			timeOts = new JLabel("12:41 AM");
			timeOts.setHorizontalAlignment(SwingConstants.CENTER);
			timeOts.setFont(new SystemFont().smallerText);
			timeOts.setBounds(157, 0, 61, 16);
		}
		return timeOts;
	}
	public JLabel getLblOrdenes() {
		if (lblOrdenes == null) {
			lblOrdenes = new JLabel("Ordenes de Trabajo (5)");
			lblOrdenes.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrdenes.setFont(new SystemFont().title);
			lblOrdenes.setBounds(86, 30, 202, 24);
		}
		return lblOrdenes;
	}
	public JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
			label_3.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_3.setBounds(0, 0, 375, 16);
		}
		return label_3;
	}
	public JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineGray));
			panel_4.setBackground(new Color(247, 247, 247));
			panel_4.setBounds(0, 0, 375, 64);
			panel_4.add(getTimeEmpaquetado());
			panel_4.add(getLabel_1_1());
			panel_4.add(getLabel_7());
		}
		return panel_4;
	}
	public JLabel getTimeEmpaquetado() {
		if (timeEmpaquetado == null) {
			timeEmpaquetado = new JLabel("12:41 AM");
			timeEmpaquetado.setHorizontalAlignment(SwingConstants.CENTER);
			timeEmpaquetado.setFont(new SystemFont().smallerText);
			timeEmpaquetado.setBounds(157, 0, 61, 16);
		}
		return timeEmpaquetado;
	}
	public JLabel getLabel_1_1() {
		if (lblEmpaquetar == null) {
			lblEmpaquetar = new JLabel("Empaquetar (5)");
			lblEmpaquetar.setHorizontalAlignment(SwingConstants.CENTER);
			lblEmpaquetar.setFont(new SystemFont().title);
			lblEmpaquetar.setBounds(86, 30, 202, 24);
		}
		return lblEmpaquetar;
	}
	public JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel("");
			label_7.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_7.setBounds(0, 0, 375, 16);
		}
		return label_7;
	}
	public JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(null);
			panel_6.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineDark));
			panel_6.setBackground(new Color(247, 247, 247));
			panel_6.setBounds(0, 0, 375, 64);
			panel_6.add(getLabel());
			panel_6.add(getLabel_1());
			panel_6.add(getLblEscanear());
			panel_6.add(getLblOts());
		}
		return panel_6;
	}
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("12:41 AM");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new SystemFont().smallerText);
			label.setBounds(157, 0, 61, 16);
		}
		return label;
	}
	public JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_1.setBounds(0, 0, 375, 16);
		}
		return label_1;
	}
	public JLabel getLblEscanear() {
		if (lblEscanear == null) {
			lblEscanear = new JLabel("Escanear");
			lblEscanear.setIconTextGap(8);
			lblEscanear.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEscanear.setForeground(new Color(0, 121, 255));
			lblEscanear.setFont(new SystemFont().backButton);
			lblEscanear.setBounds(184, 23, 183, 34);
		}
		return lblEscanear;
	}
	public JLabel getLblOts() {
		if (lblOts == null) {
			lblOts = new JLabel("OTs (-)");
			lblOts.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/back-arrow.png")));
			lblOts.setIconTextGap(10);
			lblOts.setHorizontalAlignment(SwingConstants.LEFT);
			lblOts.setForeground(new Color(0, 121, 255));
			lblOts.setFont(new SystemFont().backButton);
			lblOts.setBounds(6, 28, 166, 24);
			lblOts.addMouseListener(GoToOTsMouseListener.get(this));
		}
		return lblOts;
	}
	public JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.lineGray));
			panel_7.setBackground(new Color(235, 235, 235));
			panel_7.setBounds(0, 64, 375, 73);
			panel_7.add(getLblpedidoOTIndv());
			panel_7.add(getIdPedidoOTIndv());
			panel_7.add(getLblfechaOTIndv());
			panel_7.add(getFechaOTIndv());
			panel_7.add(getLblnArticulosOTIndv());
			panel_7.add(getArticulosOTIdnv());
			panel_7.add(getLblpesoOTIdiv());
			panel_7.add(getPesoOTIndv());
		}
		return panel_7;
	}
	public JLabel getLblpedidoOTIndv() {
		if (lblpedidoOTIndv == null) {
			lblpedidoOTIndv = new JLabel("Pedido:");
			lblpedidoOTIndv.setFont(null);
			lblpedidoOTIndv.setBounds(25, 6, 61, 16);
		}
		return lblpedidoOTIndv;
	}
	public JLabel getIdPedidoOTIndv() {
		if (idPedidoOTIndv == null) {
			idPedidoOTIndv = new JLabel("idPedido");
			idPedidoOTIndv.setFont(null);
			idPedidoOTIndv.setBounds(79, 6, 61, 16);
		}
		return idPedidoOTIndv;
	}
	public JLabel getLblfechaOTIndv() {
		if (lblfechaOTIndv == null) {
			lblfechaOTIndv = new JLabel("Fecha:");
			lblfechaOTIndv.setFont(null);
			lblfechaOTIndv.setBounds(25, 26, 61, 16);
		}
		return lblfechaOTIndv;
	}
	public JLabel getFechaOTIndv() {
		if (fechaOTIndv == null) {
			fechaOTIndv = new JLabel("fecha");
			fechaOTIndv.setFont(null);
			fechaOTIndv.setBounds(73, 26, 285, 16);
		}
		return fechaOTIndv;
	}
	public JLabel getLblnArticulosOTIndv() {
		if (lblnArticulosOTIndv == null) {
			lblnArticulosOTIndv = new JLabel("Nº Artículos:");
			lblnArticulosOTIndv.setFont(null);
			lblnArticulosOTIndv.setBounds(25, 47, 100, 16);
		}
		return lblnArticulosOTIndv;
	}
	public JLabel getArticulosOTIdnv() {
		if (articulosOTIdnv == null) {
			articulosOTIdnv = new JLabel("narticulos");
			articulosOTIdnv.setFont(null);
			articulosOTIdnv.setBounds(112, 47, 92, 16);
		}
		return articulosOTIdnv;
	}
	public JLabel getLblpesoOTIdiv() {
		if (lblpesoOTIdiv == null) {
			lblpesoOTIdiv = new JLabel("Peso:");
			lblpesoOTIdiv.setFont(null);
			lblpesoOTIdiv.setBounds(170, 47, 46, 16);
		}
		return lblpesoOTIdiv;
	}
	public JLabel getPesoOTIndv() {
		if (pesoOTIndv == null) {
			pesoOTIndv = new JLabel("peso");
			pesoOTIndv.setFont(null);
			pesoOTIndv.setBounds(210, 47, 126, 16);
		}
		return pesoOTIndv;
	}
	public JScrollPane getScPaneOTIndiv() {
		if (scPaneOTIndiv == null) {
			scPaneOTIndiv = new JScrollPane();
			scPaneOTIndiv.setViewportBorder(null);
			scPaneOTIndiv.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPaneOTIndiv.setBorder(BorderFactory.createEmptyBorder());
			scPaneOTIndiv.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPaneOTIndiv.setBounds(0, 137, 375, 350);
		}
		return scPaneOTIndiv;
	}
	public JScrollPane getScPaneOTs() {
		if (scPaneOTs == null) {
			scPaneOTs = new JScrollPane();
			scPaneOTs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPaneOTs.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPaneOTs.setViewportBorder(null);
			scPaneOTs.setBounds(0, 64, 375, 532);
			scPaneOTs.setBorder(BorderFactory.createEmptyBorder());
		}
		return scPaneOTs;
	}
	public JScrollPane getScPaneEmpaquetado() {
		if (scPaneEmpaquetado == null) {
			scPaneEmpaquetado = new JScrollPane();
			scPaneEmpaquetado.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPaneEmpaquetado.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPaneEmpaquetado.setViewportBorder(null);
			scPaneEmpaquetado.setBounds(0, 64, 375, 532);
			scPaneEmpaquetado.setBorder(BorderFactory.createEmptyBorder());
		}
		return scPaneEmpaquetado;
	}
	public JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(null);
			panel_8.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.lineDark));
			panel_8.setBackground(new Color(247, 247, 247));
			panel_8.setBounds(0, 0, 375, 64);
			panel_8.add(getLabel_11());
			panel_8.add(getLabel_12());
			panel_8.add(getLabel_13());
			panel_8.add(getLblEmpaquetar_1());
		}
		return panel_8;
	}
	public JLabel getLabel_11() {
		if (label_11 == null) {
			label_11 = new JLabel("12:41 AM");
			label_11.setHorizontalAlignment(SwingConstants.CENTER);
			label_11.setFont(null);
			label_11.setBounds(157, 0, 61, 16);
		}
		return label_11;
	}
	public JLabel getLabel_12() {
		if (label_12 == null) {
			label_12 = new JLabel("");
			label_12.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/status-bar22.png")));
			label_12.setBounds(0, 0, 375, 16);
		}
		return label_12;
	}
	public JLabel getLabel_13() {
		if (label_13 == null) {
			label_13 = new JLabel("Escanear");
			label_13.setIconTextGap(8);
			label_13.setHorizontalAlignment(SwingConstants.RIGHT);
			label_13.setForeground(new Color(0, 121, 255));
			label_13.setFont(null);
			label_13.setBounds(184, 23, 183, 34);
		}
		return label_13;
	}
	public JLabel getLblEmpaquetar_1() {
		if (lblEmpaquetar_1 == null) {
			lblEmpaquetar_1 = new JLabel("Empaquetar (-)");
			lblEmpaquetar_1.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/back-arrow.png")));
			lblEmpaquetar_1.setIconTextGap(2);
			lblEmpaquetar_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmpaquetar_1.setForeground(new Color(0, 121, 255));
			lblEmpaquetar_1.setFont(null);
			lblEmpaquetar_1.setBounds(6, 28, 166, 24);
		}
		return lblEmpaquetar_1;
	}
	public JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setLayout(null);
			panel_9.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.lineGray));
			panel_9.setBackground(new Color(235, 235, 235));
			panel_9.setBounds(0, 64, 375, 73);
			panel_9.add(getLabel_15());
			panel_9.add(getIdPedidoEmpInd());
			panel_9.add(getLabel_17());
			panel_9.add(getFechaEmpInd());
			panel_9.add(getLabel_19());
			panel_9.add(getNarticulosEmpInd());
			panel_9.add(getLabel_21());
			panel_9.add(getPesoEmpInd());
		}
		return panel_9;
	}
	public JLabel getLabel_15() {
		if (label_15 == null) {
			label_15 = new JLabel("Pedido:");
			label_15.setFont(null);
			label_15.setBounds(25, 6, 61, 16);
		}
		return label_15;
	}
	public JLabel getIdPedidoEmpInd() {
		if (idPedidoEmpInd == null) {
			idPedidoEmpInd = new JLabel("idPedido");
			idPedidoEmpInd.setFont(null);
			idPedidoEmpInd.setBounds(79, 6, 61, 16);
		}
		return idPedidoEmpInd;
	}
	public JLabel getLabel_17() {
		if (label_17 == null) {
			label_17 = new JLabel("Fecha:");
			label_17.setFont(null);
			label_17.setBounds(25, 26, 61, 16);
		}
		return label_17;
	}
	public JLabel getFechaEmpInd() {
		if (fechaEmpInd == null) {
			fechaEmpInd = new JLabel("fecha");
			fechaEmpInd.setFont(null);
			fechaEmpInd.setBounds(73, 26, 285, 16);
		}
		return fechaEmpInd;
	}
	public JLabel getLabel_19() {
		if (label_19 == null) {
			label_19 = new JLabel("Nº Artículos:");
			label_19.setFont(null);
			label_19.setBounds(25, 47, 100, 16);
		}
		return label_19;
	}
	public JLabel getNarticulosEmpInd() {
		if (narticulosEmpInd == null) {
			narticulosEmpInd = new JLabel("narticulos");
			narticulosEmpInd.setFont(null);
			narticulosEmpInd.setBounds(112, 47, 92, 16);
		}
		return narticulosEmpInd;
	}
	public JLabel getLabel_21() {
		if (label_21 == null) {
			label_21 = new JLabel("Peso:");
			label_21.setFont(null);
			label_21.setBounds(170, 47, 46, 16);
		}
		return label_21;
	}
	public JLabel getPesoEmpInd() {
		if (pesoEmpInd == null) {
			pesoEmpInd = new JLabel("peso");
			pesoEmpInd.setFont(null);
			pesoEmpInd.setBounds(210, 47, 126, 16);
		}
		return pesoEmpInd;
	}
	public JPanel getToolsOTIndv() {
		if (toolsOTIndv == null) {
			toolsOTIndv = new JPanel();
			toolsOTIndv.setBorder(new MatteBorder(1, 0, 0, 0, SystemColor.lineGray));
			toolsOTIndv.setBackground(Color.WHITE);
			toolsOTIndv.setBounds(0, 490, 375, 155);
			toolsOTIndv.setLayout(new CardLayout(0, 0));
			toolsOTIndv.add(getScanner(), "scanner");
			toolsOTIndv.add(getIncidence(), "incidence");
			toolsOTIndv.add(getFeedback(), "feedback");
		}
		return toolsOTIndv;
	}
	public JPanel getScanner() {
		if (scanner == null) {
			scanner = new JPanel();
			scanner.setBackground(Color.WHITE);
			scanner.setBorder(null);
			scanner.setLayout(null);
			scanner.add(getScannerToolOTidProd());
			scanner.add(getTextField());
		}
		return scanner;
	}
	public JLabel getScannerToolOTidProd() {
		if (scannerToolOTidProd == null) {
			scannerToolOTidProd = new JLabel("ID Producto:");
			scannerToolOTidProd.setHorizontalAlignment(SwingConstants.CENTER);
			scannerToolOTidProd.setBounds(6, 45, 363, 34);
			scannerToolOTidProd.setFont(new SystemFont().mediumText);
		}
		return scannerToolOTidProd;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setBounds(6, 79, 363, 26);
			textField.setColumns(10);
			textField.setFont(new SystemFont().normalText);
		}
		return textField;
	}
	public JPanel getIncidence() {
		if (incidence == null) {
			incidence = new JPanel();
			incidence.setBackground(Color.WHITE);
			incidence.setLayout(null);
			incidence.add(getLblIncidence());
			incidence.add(getChckbxSolucionada());
			incidence.add(getScPaneIncidenceDescription());
		}
		return incidence;
	}
	public JLabel getLblIncidence() {
		if (lblIncidence == null) {
			lblIncidence = new JLabel("Incidence:");
			lblIncidence.setBounds(6, 16, 168, 16);
		}
		return lblIncidence;
	}
	public JCheckBox getChckbxSolucionada() {
		if (chckbxSolucionada == null) {
			chckbxSolucionada = new JCheckBox("Solucionada");
			chckbxSolucionada.setHorizontalAlignment(SwingConstants.TRAILING);
			chckbxSolucionada.setBounds(241, 12, 128, 23);
		}
		return chckbxSolucionada;
	}
	public JScrollPane getScPaneIncidenceDescription() {
		if (scPaneIncidenceDescription == null) {
			scPaneIncidenceDescription = new JScrollPane();
			scPaneIncidenceDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scPaneIncidenceDescription.setBounds(6, 44, 363, 106);
			scPaneIncidenceDescription.getVerticalScrollBar().setPreferredSize(new Dimension(3, 0));
			scPaneIncidenceDescription.setViewportView(getTxtIncidence());
		}
		return scPaneIncidenceDescription;
	}
	public JTextPane getTxtIncidence() {
		if (txtIncidence == null) {
			txtIncidence = new JTextPane();
		}
		return txtIncidence;
	}
	public JPanel getFeedback() {
		if (feedback == null) {
			feedback = new JPanel();
			feedback.setBackground(Color.WHITE);
			feedback.setLayout(null);
			feedback.add(getLblNewLabel());
		}
		return feedback;
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Message");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(6, 6, 363, 144);
			lblNewLabel.setFont(new SystemFont().mediumText);
		}
		return lblNewLabel;
	}
	
	public JLabel getLblEntrar() {
		if (lblEntrar == null) {
			lblEntrar = new JLabel("Entrar");
			lblEntrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {
							CursorMode.wait(lblEntrar);
							loadPedidosInView();
							if(LoadPedidosInContainer.numberOfOrders()>0) {
								getPedidosTitleCounter().setText("Pedidos ("+LoadPedidosInContainer.numberOfOrders()+")");
								getLlblPedidosLink().setText("Pedidos ("+LoadPedidosInContainer.numberOfOrders()+")");
							} else {
								getPedidosTitleCounter().setText("Pedidos");
								getLlblPedidosLink().setText("Pedidos");
							}
							return null;
						}

						@Override
						protected void done() {
							CursorMode.normal(lblEntrar);
							((CardLayout) getContentPane().getLayout()).show(getContentPane(), "vistaListaPedidos");
						}
					};

					sw.execute(); // this will start the processing on a separate thread
				}
			});
			lblEntrar.setHorizontalTextPosition(SwingConstants.LEADING);
			lblEntrar.setIcon(new ImageIcon(WarehouseAPP.class.getResource("/com/myshop2/ui/icons/next-selected.png")));
			lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrar.setBounds(129, 329, 117, 36);
			lblEntrar.setFont(new SystemFont().backButton);
			lblEntrar.setForeground(SystemColor.blue);
			
		}
		return lblEntrar;
	}
	
	private void promptSupport() {
		PromptSupport.setPrompt("ID Almacenero", getTxtIdAlmacenero());
		PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, getTxtIdAlmacenero());
		PromptSupport.setFontStyle(Font.PLAIN, getTxtIdAlmacenero());
	}
}
