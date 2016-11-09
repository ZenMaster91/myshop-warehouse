package com.myshop.warehouse.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.myshop.model.order.MailBox;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.order.Status;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.IncidenceController;
import com.myshop.warehouse.controllers.MailBoxController;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.OrderItemController;
import com.myshop.warehouse.controllers.WarehouseKeeperController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.controllers.WorkingPlanItemController;
import com.myshop.warehouse.generators.AlbaranGenerator;
import com.myshop.warehouse.generators.ShippingInfoGenerator;
import com.myshop.warehouse.generators.WorkingPlanGenerator;
import com.myshop.warehouse.validators.EmpaquetadoValidator;
import com.myshop.warehouse.validators.PedidoRecogidoEnteroValidator;
import com.myshop.warehouse.validators.ReferenciasValidator;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnInicioAlmacenero;
	private JTextField TxLogin;
	private JLabel lblInicio;
	private JButton btnAceptar;
	private JLabel lblRecogida;
	private JLabel lblEmpaquetado;
	private JPanel pnOpciones;
	private DefaultTableModel modeloTablaRecogida;
	private DefaultTableModel modeloTablaEmpaquetado;
	private DefaultTableModel modeloTablaEmpaquetadoPedido;
	private DefaultNonEditableTableModel<WorkingPlanController> modeloTablaPedidosPorRecogerAlmacen;
	private DefaultNonEditableTableModel<WorkingPlanItemController> modeloTablaOrdenTrabajoIndividual;
	private DefaultNonEditableTableModel<OrderItemController> modeloTablaEmpaquetarPedido;
	private Long id;
	private JLabel lblIdAlmacenero;
	private JLabel lblalmacn;
	private JPanel panel;
	private JPanel pnOrdenesDeTrabajo;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblMyshopV;
	private JLabel lblAm;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable tbOTsPendientes;
	private JPanel panel_3;
	private JLabel lblOrdenesDeTrabajo;
	private JLabel label;
	private JPanel pnOrdenTrabajo;
	private JPanel panel_4;
	private JLabel lblOrdenTrabajo;
	private JTextField txtOrderID;
	private JLabel lblReload;
	private WarehouseKeeper almacenero = null;
	private JLabel lblOtN;
	private JScrollPane scrollPane_1;
	private JTable tbOrdenTrabajoIndividual;
	private JLabel lblEscanear;
	private JPanel panel_5;
	private JLabel lblGenerarIncidenca;
	private JPanel panel_6;
	private JPanel pn_referenciaCorrecta;
	private JLabel lblReferenciaCorrecta;
	private JPanel pn_referenciaIncorrecta;
	private JLabel lblReferenciaIncorreecta;
	private JPanel pnEntradaReferencia;
	private JTextField txtInpReferencia;
	private JPanel pnWarning;
	private JLabel lblWarning;
	private boolean bussy;
	private JPanel pnEntradaIncidencia;
	private JScrollPane scrollPane_2;
	private JTextPane txtIncidencia;
	private JCheckBox chckbxSolucionada;
	private JPanel pnListaPedidosEmpaquetado;
	private JPanel panel_7;
	private JLabel lblPedidosParaEmpaquetar;
	private JLabel label_1;
	private JScrollPane scrollPane_3;
	private JTable tbListaPedidosParaEmpaquetar;
	private JPanel pnEmpaquetadoPedido;
	private JPanel panel_8;
	private JLabel lblPedidoParaEmpaquetar;
	private JScrollPane scrollPane_4;
	private JPanel panel_9;
	private JLabel label_2;
	private JLabel lblGenerarAlbarn;
	private JPanel panel_10;
	private JLabel lblGenerarEtiquetaEnvo;
	private JLabel lblFianlizar;
	private JPanel panel_11;
	private Order currentOrder;
	private JLabel lblNuevaCaja;
	private MailBox mailBox;
	private JPanel pnEntradaReferenciaEmpaquetado;
	private JTextField txtEntradaReferenciaEmpaquetado;
	private WorkingPlanController currentWorkingPlanController;
	private DefaultNonEditableTableModel<OrderController> modeloTablaPedidosPorEmpaquetar;
	private JTable tbEmpaquetadoPedidoIndividual;
	private JPanel pnReferenciaIncorrectaEmpaquetado;
	private JLabel label_3;
	private JPanel pnReferenciaCorrectaEmpaquetado;
	private JLabel lblCorrecta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlmaceneros frame = new VentanaAlmaceneros();
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
	public VentanaAlmaceneros() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
		contentPane.add(getPanel_2());
	}

	private JPanel getPnInicioAlmacenero() {
		if (pnInicioAlmacenero == null) {
			pnInicioAlmacenero = new JPanel();
			pnInicioAlmacenero.setLayout(null);
			pnInicioAlmacenero.add(getTxLogin());
			pnInicioAlmacenero.add(getLblInicio());
			pnInicioAlmacenero.add(getLblIdAlmacenero());
			pnInicioAlmacenero.add(getBtnAceptar());
			pnInicioAlmacenero.add(getLblalmacn());
		}
		return pnInicioAlmacenero;
	}

	private JTextField getTxLogin() {
		if (TxLogin == null) {
			TxLogin = new JTextField();
			TxLogin.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						login();
				}
			});
			TxLogin.setBounds(0, 240, 331, 26);
			TxLogin.setHorizontalAlignment(SwingConstants.CENTER);
			TxLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			TxLogin.setColumns(10);
		}
		return TxLogin;
	}

	private void login() {
		if (login(Integer.parseInt(getTxLogin().getText())))
			mostrarVentanaOpciones();
	}

	private boolean login(int id) {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		List<WarehouseKeeper> wk = new WarehouseKeeperController().getByID(String.valueOf(id));
		if (wk.size() > 0)
			almacenero = new WarehouseKeeperController().getByID(getTxLogin().getText()).get(0);
		else
			almacenero = null;

		if (almacenero != null) {

			List<WorkingPlanController> wpc = new WarehouseKeeperController().getCurrentWorkingPlan(almacenero);

			if (wpc.size() > 0) {
				// Code here for busy warehouse keeper.
				System.out.println("Ocupado --> " + wpc.size());
				bussy = true;
			} else {
				// Code here for the free warehouse keeper.
				bussy = false;
			}

			System.out.println(almacenero.getName() + " ID " + almacenero.getID());
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return true;
		} else {
			JOptionPane.showMessageDialog(contentPane, "Usuario no valido, vuelva a intentarlo", "Inicio almacenero",
					JOptionPane.INFORMATION_MESSAGE);
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			return false;
		}
	}

	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("myShop");
			lblInicio.setBounds(0, 49, 331, 56);
			lblInicio.setFont(new Font("Dialog", Font.PLAIN, 31));
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblInicio;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Entrar");
			btnAceptar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					login();
				}
			});
			btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAceptar.setBounds(0, 267, 331, 26);
		}
		return btnAceptar;
	}

	private JLabel getLblRecogida() {
		if (lblRecogida == null) {
			lblRecogida = new JLabel("Órdenes de Trabajo");
			lblRecogida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblRecogida.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// mostrarVentanaOpcionesRecogida();
					iniciarVentanaListaWPPorRecoger();
					try {
						if (bussy)
							updateVentanaListaWPPorRecoger(
									new WarehouseKeeperController().getCurrentWorkingPlan(almacenero));
						else
							updateVentanaListaWPPorRecoger(new WorkingPlanGenerator().generate().getAll());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					((CardLayout) (getPanel().getLayout())).show(getPanel(), "ventanaPedidosPendientes");
				}
			});
			lblRecogida.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblRecogida.setIconTextGap(10);
			lblRecogida.setHorizontalTextPosition(SwingConstants.CENTER);
			lblRecogida.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblRecogida.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/recogida.png")));
			lblRecogida.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblRecogida;
	}

	private JLabel getLblEmpaquetado() {
		if (lblEmpaquetado == null) {
			lblEmpaquetado = new JLabel("Empaquetar pedidos");
			lblEmpaquetado.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) (getPanel().getLayout())).show(getPanel(), "ventanaPedidosPorEmpaquetar");
					try {
						iniciarVentanaEmpaquetadoPedidos();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblEmpaquetado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblEmpaquetado.setIconTextGap(10);
			lblEmpaquetado.setHorizontalTextPosition(SwingConstants.CENTER);
			lblEmpaquetado.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblEmpaquetado.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblEmpaquetado.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/empaquetado.png")));
			lblEmpaquetado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEmpaquetado;
	}

	private void mostrarVentanaOpciones() {
		((CardLayout) (getPanel().getLayout())).show(getPanel(), "opciones");
	}

	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setLayout(null);
			pnOpciones.add(getPanel_11());
		}
		return pnOpciones;
	}

	private JLabel getLblIdAlmacenero() {
		if (lblIdAlmacenero == null) {
			lblIdAlmacenero = new JLabel("ID Almacenero:");
			lblIdAlmacenero.setBounds(0, 218, 331, 26);
			lblIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblIdAlmacenero;
	}

	private JLabel getLblalmacn() {
		if (lblalmacn == null) {
			lblalmacn = new JLabel("(Almacén)");
			lblalmacn.setBounds(0, 97, 331, 26);
			lblalmacn.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblalmacn;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 33, 331, 433);
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getPnInicioAlmacenero(), "inicio");
			panel.add(getPnOpciones(), "opciones");
			panel.add(getPnOrdenesDeTrabajo(), "ventanaPedidosPendientes");
			panel.add(getPnOrdenTrabajo(), "ordenTrabajo");
			panel.add(getPnListaPedidosEmpaquetado(), "ventanaPedidosPorEmpaquetar");
			panel.add(getPnEmpaquetadoPedido(), "ventanaPedidoEmpaquetando");
		}
		return panel;
	}

	private JPanel getPnOrdenesDeTrabajo() {
		if (pnOrdenesDeTrabajo == null) {
			pnOrdenesDeTrabajo = new JPanel();
			pnOrdenesDeTrabajo.setLayout(null);
			pnOrdenesDeTrabajo.add(getScrollPane());
			pnOrdenesDeTrabajo.add(getPanel_3());
		}
		return pnOrdenesDeTrabajo;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(0, 465, 331, 33);
			panel_1.setBackground(Color.decode("#FF9500"));
			panel_1.setLayout(null);
			panel_1.add(getLblNewLabel());
			panel_1.add(getLabel());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBackground(new Color(255, 149, 0));
			panel_2.setBounds(0, 0, 331, 33);
			panel_2.add(getLblMyshopV());
			panel_2.add(getLblAm());
		}
		return panel_2;
	}

	private JLabel getLblMyshopV() {
		if (lblMyshopV == null) {
			lblMyshopV = new JLabel("myShop v2.1");
			lblMyshopV.setForeground(new Color(255, 255, 255));
			lblMyshopV.setBounds(6, 8, 118, 16);
		}
		return lblMyshopV;
	}

	private JLabel getLblAm() {
		if (lblAm == null) {
			lblAm = new JLabel("11:10 AM");
			lblAm.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblAm.setForeground(new Color(255, 255, 255));
			lblAm.setBounds(228, 8, 97, 16);
		}
		return lblAm;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) (getPanel().getLayout())).show(getPanel(), "inicio");
				}
			});
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/logout-white.png")));
			lblNewLabel.setBounds(298, 6, 27, 21);
		}
		return lblNewLabel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setRequestFocusEnabled(false);
			scrollPane.setFocusable(false);
			scrollPane.setFocusTraversalKeysEnabled(false);
			scrollPane.setBounds(0, 30, 331, 403);
			scrollPane.setViewportView(getTbOTsPendientes());
		}
		return scrollPane;
	}

	private JTable getTbOTsPendientes() {
		if (tbOTsPendientes == null) {
			tbOTsPendientes = new JTable();
			tbOTsPendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbOTsPendientes.setFocusTraversalKeysEnabled(false);
			tbOTsPendientes.setFocusable(false);
			tbOTsPendientes.setRequestFocusEnabled(false);
			tbOTsPendientes.getTableHeader().setReorderingAllowed(false);
			tbOTsPendientes.getTableHeader().setResizingAllowed(false);
			tbOTsPendientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

						((CardLayout) (getPanel().getLayout())).show(getPanel(), "ordenTrabajo");

						WorkingPlanController wpc = modeloTablaPedidosPorRecogerAlmacen
								.getObjectAtRow(getTbOTsPendientes().getSelectedRow());

						if (!bussy) {
							if (wpc.isChild())
								WorkingPlanController.assign(wpc.getParent(), almacenero);
							else {
								WorkingPlanController.assign(wpc, almacenero);
							}
						}

						iniciarVentanaOrdenTrabajo();

						try {
							updateVentanaOrdenTrabajo(wpc);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}

			});
		}
		return tbOTsPendientes;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBounds(0, 0, 331, 31);
			panel_3.setLayout(null);
			panel_3.add(getLblOrdenesDeTrabajo());
			panel_3.add(getLblReload());
		}
		return panel_3;
	}

	private JLabel getLblOrdenesDeTrabajo() {
		if (lblOrdenesDeTrabajo == null) {
			lblOrdenesDeTrabajo = new JLabel("Órdenes de Trabajo Pendientes");
			lblOrdenesDeTrabajo.setBounds(6, 7, 273, 16);
		}
		return lblOrdenesDeTrabajo;
	}

	private void iniciarVentanaListaWPPorRecoger() {
		String[] columNames = { "ID", "ID Padre", "Nº Prod.", "Peso", "Estado" };
		modeloTablaPedidosPorRecogerAlmacen = new DefaultNonEditableTableModel<>(columNames, 5);
		tbOTsPendientes.setModel(modeloTablaPedidosPorRecogerAlmacen);
	}

	private void updateVentanaListaWPPorRecoger(List<WorkingPlanController> list) throws Exception {
		modeloTablaPedidosPorRecogerAlmacen.removeAll();
		int index = 1;
		for (WorkingPlanController wpc : list) {
			wpc.print();
			if (wpc.getWp().getID() == -1)
				wpc.getWp().setID(index);
			modeloTablaPedidosPorRecogerAlmacen.addRow(wpc,
					String.valueOf(wpc.getWp().getItems().get(0).getOrderItem().getParent().getID()), " - ",
					String.valueOf(wpc.getNumberOfItems()), String.valueOf(wpc.getTotalWeight()), wpc.getStatus());

			for (WorkingPlanController wpcChild : wpc.getChilds()) {
				index++;
				wpcChild.getWp().setID(index);
				modeloTablaPedidosPorRecogerAlmacen.addRow(wpcChild, String.valueOf(wpcChild.getWp().getID()),
						String.valueOf(
								wpcChild.getParent().getWp().getItems().get(0).getOrderItem().getParent().getID()),
						String.valueOf(wpcChild.getNumberOfItems()), String.valueOf(wpcChild.getTotalWeight()),
						wpcChild.getStatus());
			}
			if (wpc.getChilds().isEmpty())
				index++;
			if (bussy) {
				getLblOrdenesDeTrabajo().setText("Órdenes de Trabajo Asignadas");
			} else {
				getLblOrdenesDeTrabajo().setText("Órdenes de Trabajo Pendientes");
			}
			modeloTablaPedidosPorRecogerAlmacen.fireTableDataChanged();
			getTbOTsPendientes().repaint();
			getLblReload().requestFocus();
		}
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) (getPanel().getLayout())).previous(getPanel());
				}
			});
			label.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/goBack.png")));
			label.setHorizontalTextPosition(SwingConstants.CENTER);
			label.setForeground(Color.WHITE);
			label.setBounds(6, 6, 27, 21);
		}
		return label;
	}

	private JPanel getPnOrdenTrabajo() {
		if (pnOrdenTrabajo == null) {
			pnOrdenTrabajo = new JPanel();
			pnOrdenTrabajo.setLayout(null);
			pnOrdenTrabajo.add(getPanel_4());
			pnOrdenTrabajo.add(getScrollPane_1());
			pnOrdenTrabajo.add(getPanel_5());
			pnOrdenTrabajo.add(getPanel_6());

		}
		return pnOrdenTrabajo;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBounds(0, 0, 331, 31);
			panel_4.add(getLblOrdenTrabajo());
			panel_4.add(getTxtOrderID());
			panel_4.add(getLblOtN());
		}
		return panel_4;
	}

	private JLabel getLblOrdenTrabajo() {
		if (lblOrdenTrabajo == null) {
			lblOrdenTrabajo = new JLabel("Órden de Trabajo");
			lblOrdenTrabajo.setBounds(6, 7, 170, 16);
		}
		return lblOrdenTrabajo;
	}

	private JTextField getTxtOrderID() {
		if (txtOrderID == null) {
			txtOrderID = new JTextField();
			txtOrderID.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			txtOrderID.setHorizontalAlignment(SwingConstants.TRAILING);
			txtOrderID.setEditable(false);
			txtOrderID.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtOrderID.setBounds(270, 2, 55, 26);
			txtOrderID.setColumns(10);
		}
		return txtOrderID;
	}

	private JLabel getLblReload() {
		if (lblReload == null) {
			lblReload = new JLabel("");
			lblReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblReload.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					try {
						lblReload.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						login(almacenero.getID());
						if (bussy)
							updateVentanaListaWPPorRecoger(
									new WarehouseKeeperController().getCurrentWorkingPlan(almacenero));
						else
							updateVentanaListaWPPorRecoger(new WorkingPlanGenerator().generate().getAll());

						lblReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			lblReload.setHorizontalAlignment(SwingConstants.TRAILING);
			lblReload.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/refresh.png")));
			lblReload.setBounds(290, 0, 35, 31);
		}
		return lblReload;
	}

	protected void iniciarVentanaOrdenTrabajo() {
		String[] columNames = { "Pr. ID", "Cant.", "Pasillo", "Lado", "Posición", "Altura" };
		modeloTablaOrdenTrabajoIndividual = new DefaultNonEditableTableModel<>(columNames, 5);
		getTbOrdenTrabajoIndividual().setModel(modeloTablaOrdenTrabajoIndividual);
		// tbOTsPendientes.setModel(modeloTablaPedidosPorRecogerAlmacen);
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(0).setCellRenderer(new WorkingPlanItemCellRenderer());
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(1).setCellRenderer(new WorkingPlanItemCellRenderer());
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(2).setCellRenderer(new WorkingPlanItemCellRenderer());
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(3).setCellRenderer(new WorkingPlanItemCellRenderer());
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(4).setCellRenderer(new WorkingPlanItemCellRenderer());
		getTbOrdenTrabajoIndividual().getColumnModel().getColumn(5).setCellRenderer(new WorkingPlanItemCellRenderer());
		((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnEntradaReferencia");

	}

	protected void updateVentanaOrdenTrabajo(WorkingPlanController wpc) throws Exception {
		currentWorkingPlanController = wpc;
		getTxtOrderID().setText(String.valueOf(wpc.getWp().getID()));
		modeloTablaOrdenTrabajoIndividual.removeAll();
		for (WorkingPlanItem wpi : wpc.getItems()) {
			if (!wpi.getCollected()) {
				modeloTablaOrdenTrabajoIndividual.addRow(new WorkingPlanItemController(wpi),
						String.valueOf(wpi.getOrderItem().getProduct().getID()),
						String.valueOf(wpi.getOrderItem().getQuantity()),
						String.valueOf(wpi.getOrderItem().getProduct().getCorridor()),
						wpi.getOrderItem().getProduct().getSide().toUpperCase(),
						String.valueOf(wpi.getOrderItem().getProduct().getPosition()),
						String.valueOf(wpi.getOrderItem().getProduct().getHeight()));
			}
		}
	}

	private JLabel getLblOtN() {
		if (lblOtN == null) {
			lblOtN = new JLabel("OT Nº:");
			lblOtN.setHorizontalAlignment(SwingConstants.TRAILING);
			lblOtN.setBounds(198, 7, 61, 16);
		}
		return lblOtN;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 30, 331, 255);
			scrollPane_1.setViewportView(getTbOrdenTrabajoIndividual());
		}
		return scrollPane_1;
	}

	private JTable getTbOrdenTrabajoIndividual() {
		if (tbOrdenTrabajoIndividual == null) {
			tbOrdenTrabajoIndividual = new JTable();
			tbOrdenTrabajoIndividual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbOrdenTrabajoIndividual.setFocusTraversalKeysEnabled(false);
			tbOrdenTrabajoIndividual.setFocusable(false);
			tbOrdenTrabajoIndividual.setRequestFocusEnabled(false);
			tbOrdenTrabajoIndividual.getTableHeader().setReorderingAllowed(false);
			tbOrdenTrabajoIndividual.getTableHeader().setResizingAllowed(false);
		}
		return tbOrdenTrabajoIndividual;
	}

	private JLabel getLblEscanear() {
		if (lblEscanear == null) {
			lblEscanear = new JLabel("Escanear");
			lblEscanear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblEscanear.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnEntradaReferencia");
					getTxtInpReferencia().requestFocus();
				}
			});
			lblEscanear.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblEscanear.setIconTextGap(0);
			lblEscanear.setVerticalAlignment(SwingConstants.BOTTOM);
			lblEscanear.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblEscanear.setHorizontalTextPosition(SwingConstants.CENTER);
			lblEscanear.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/scan-small.png")));
			lblEscanear.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEscanear;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBounds(5, 288, 320, 73);
			panel_5.setLayout(new GridLayout(1, 2, 1, 0));
			panel_5.add(getLblEscanear());
			panel_5.add(getLblGenerarIncidenca());
		}
		return panel_5;
	}

	private JLabel getLblGenerarIncidenca() {
		if (lblGenerarIncidenca == null) {
			lblGenerarIncidenca = new JLabel("Ver/Generar Incidenca");
			lblGenerarIncidenca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblGenerarIncidenca.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (getTbOrdenTrabajoIndividual().getSelectedRow() == -1) {

						((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnWarning");
						getLblWarning().setText("Primero selecione un producto");
						new java.util.Timer().schedule(new java.util.TimerTask() {
							@Override
							public void run() {
								((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnEntradaReferencia");
							}
						}, 900);
					} else {
						System.out.println("Incidencia A");
						WorkingPlanItemController wpi = modeloTablaOrdenTrabajoIndividual
								.getObjectAtRow(getTbOrdenTrabajoIndividual().getSelectedRow());
						System.out.println("Incidencia B");
						((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnEntradaIncidencia");
						if (wpi.getWPI().getOrderItem().getIncidence() != null) {
							getTxtIncidencia().setText(wpi.getWPI().getOrderItem().getIncidence().getDescription());
							getChckbxSolucionada().setSelected(wpi.getWPI().getOrderItem().getIncidence().isSolve());
						}
					}
				}
			});
			lblGenerarIncidenca.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblGenerarIncidenca.setIconTextGap(6);
			lblGenerarIncidenca.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/warning-small.png")));
			lblGenerarIncidenca.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblGenerarIncidenca.setVerticalAlignment(SwingConstants.BOTTOM);
			lblGenerarIncidenca.setHorizontalTextPosition(SwingConstants.CENTER);
			lblGenerarIncidenca.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblGenerarIncidenca;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBounds(5, 373, 320, 54);
			panel_6.setLayout(new CardLayout(0, 0));
			panel_6.add(getPn_referenciaIncorrecta(), "pnReferenciaIncorrecta");
			panel_6.add(getPn_referenciaCorrecta(), "pnReferenciaCorrecta");
			panel_6.add(getPnEntradaReferencia(), "pnEntradaReferencia");
			panel_6.add(getPnWarning(), "pnWarning");
			panel_6.add(getPnEntradaIncidencia(), "pnEntradaIncidencia");
		}
		return panel_6;
	}

	private JPanel getPn_referenciaCorrecta() {
		if (pn_referenciaCorrecta == null) {
			pn_referenciaCorrecta = new JPanel();
			pn_referenciaCorrecta.setLayout(null);
			pn_referenciaCorrecta.add(getLblReferenciaCorrecta());
		}
		return pn_referenciaCorrecta;
	}

	private JLabel getLblReferenciaCorrecta() {
		if (lblReferenciaCorrecta == null) {
			lblReferenciaCorrecta = new JLabel("Referencia Correcta");
			lblReferenciaCorrecta.setHorizontalAlignment(SwingConstants.CENTER);
			lblReferenciaCorrecta.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/correct.png")));
			lblReferenciaCorrecta.setForeground(new Color(0, 153, 51));
			lblReferenciaCorrecta.setBounds(6, 6, 308, 42);
		}
		return lblReferenciaCorrecta;
	}

	private JPanel getPn_referenciaIncorrecta() {
		if (pn_referenciaIncorrecta == null) {
			pn_referenciaIncorrecta = new JPanel();
			pn_referenciaIncorrecta.setLayout(null);
			pn_referenciaIncorrecta.add(getLblReferenciaIncorreecta());
		}
		return pn_referenciaIncorrecta;
	}

	private JLabel getLblReferenciaIncorreecta() {
		if (lblReferenciaIncorreecta == null) {
			lblReferenciaIncorreecta = new JLabel("Referencia Incorreecta");
			lblReferenciaIncorreecta.setHorizontalAlignment(SwingConstants.CENTER);
			lblReferenciaIncorreecta.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/incorrect-small.png")));
			lblReferenciaIncorreecta.setForeground(new Color(255, 51, 102));
			lblReferenciaIncorreecta.setBounds(6, 6, 308, 42);
		}
		return lblReferenciaIncorreecta;
	}

	private JPanel getPnEntradaReferencia() {
		if (pnEntradaReferencia == null) {
			pnEntradaReferencia = new JPanel();
			pnEntradaReferencia.setLayout(null);
			pnEntradaReferencia.add(getTxtInpReferencia());
		}
		return pnEntradaReferencia;
	}

	private JTextField getTxtInpReferencia() {
		if (txtInpReferencia == null) {
			txtInpReferencia = new JTextField();
			txtInpReferencia.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// Chekear si las tecla es enter.
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (new ReferenciasValidator(txtInpReferencia.getText(),
								modeloTablaOrdenTrabajoIndividual.getAllRows()).validate()) {
							((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnReferenciaCorrecta");
							for (WorkingPlanItemController wpic : modeloTablaOrdenTrabajoIndividual.getAllRows()) {
								if (wpic.getWPI().getOrderItem().getProductID() == Integer
										.parseInt(txtInpReferencia.getText())) {
									WorkingPlanItemController.collect(wpic.getWPI().getOrderItem().getID());
									wpic.getWPI().setCollected(true);
									modeloTablaOrdenTrabajoIndividual.removeRow(wpic);
									if (new PedidoRecogidoEnteroValidator(currentWorkingPlanController).validate()) {
										((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnWarning");
										getLblWarning().setText("Orden de Trabajo Completada.");
										try {
											updatearVentanaEmpaquetadoPedidos();
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										new java.util.Timer().schedule(new java.util.TimerTask() {
											@Override
											public void run() {
												((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(),
														"opciones");
											}
										}, 900);
									}
								}
							}
						} else {
							((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnReferenciaIncorrecta");
						}
						txtInpReferencia.setText("");
						new java.util.Timer().schedule(new java.util.TimerTask() {
							@Override
							public void run() {
								((CardLayout) (getPanel_6().getLayout())).show(getPanel_6(), "pnEntradaReferencia");
								txtInpReferencia.requestFocus();
							}
						}, 900);
					}
				}
			});
			txtInpReferencia.setHorizontalAlignment(SwingConstants.CENTER);
			txtInpReferencia.setBounds(6, 14, 308, 26);
			txtInpReferencia.setColumns(10);
		}
		return txtInpReferencia;
	}

	private JPanel getPnWarning() {
		if (pnWarning == null) {
			pnWarning = new JPanel();
			pnWarning.setLayout(null);
			pnWarning.add(getLblWarning());
		}
		return pnWarning;
	}

	private JLabel getLblWarning() {
		if (lblWarning == null) {
			lblWarning = new JLabel("Selecione primero un producto");
			lblWarning.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/warning-icon-small.png")));
			lblWarning.setForeground(new Color(255, 165, 0));
			lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
			lblWarning.setBounds(6, 6, 308, 42);
		}
		return lblWarning;
	}

	private JPanel getPnEntradaIncidencia() {
		if (pnEntradaIncidencia == null) {
			pnEntradaIncidencia = new JPanel();
			pnEntradaIncidencia.setBounds(0, 0, 10, 10);
			pnEntradaIncidencia.setLayout(null);
			pnEntradaIncidencia.add(getScrollPane_2_1());
			pnEntradaIncidencia.add(getChckbxSolucionada());
		}
		return pnEntradaIncidencia;
	}

	private JScrollPane getScrollPane_2_1() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(6, 6, 190, 42);
			scrollPane_2.setViewportView(getTxtIncidencia());
		}
		return scrollPane_2;
	}

	private JTextPane getTxtIncidencia() {
		if (txtIncidencia == null) {
			txtIncidencia = new JTextPane();
			txtIncidencia.addFocusListener(new FocusAdapter() {

				@Override
				public void focusLost(FocusEvent e) {
					new IncidenceController(modeloTablaOrdenTrabajoIndividual
							.getObjectAtRow(getTbOrdenTrabajoIndividual().getSelectedRow()).getWPI().getOrderItem())
									.setDescription(txtIncidencia.getText());
				}
			});
			txtIncidencia.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			txtIncidencia.setText(
					"Texto de demostración sobre una incicencia que perfectamente puede ocurrir con cualquier producto.");
		}
		return txtIncidencia;
	}

	private JCheckBox getChckbxSolucionada() {
		if (chckbxSolucionada == null) {
			chckbxSolucionada = new JCheckBox("Solucionada");
			chckbxSolucionada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IncidenceController(modeloTablaOrdenTrabajoIndividual
							.getObjectAtRow(getTbOrdenTrabajoIndividual().getSelectedRow()).getWPI().getOrderItem())
									.setSolved(chckbxSolucionada.isSelected());
				}
			});
			chckbxSolucionada.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
			chckbxSolucionada.setBounds(208, 15, 106, 23);
		}
		return chckbxSolucionada;
	}

	private JPanel getPnListaPedidosEmpaquetado() {
		if (pnListaPedidosEmpaquetado == null) {
			pnListaPedidosEmpaquetado = new JPanel();
			pnListaPedidosEmpaquetado.setLayout(null);
			pnListaPedidosEmpaquetado.add(getPanel_7());
			pnListaPedidosEmpaquetado.add(getScrollPane_3());
		}
		return pnListaPedidosEmpaquetado;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(null);
			panel_7.setBounds(0, 0, 331, 31);
			panel_7.add(getLblPedidosParaEmpaquetar());
			panel_7.add(getLabel_1());
		}
		return panel_7;
	}

	private JLabel getLblPedidosParaEmpaquetar() {
		if (lblPedidosParaEmpaquetar == null) {
			lblPedidosParaEmpaquetar = new JLabel("Pedidos para empaquetar");
			lblPedidosParaEmpaquetar.setBounds(6, 7, 170, 16);
		}
		return lblPedidosParaEmpaquetar;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
			label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_1.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/refresh.png")));
			label_1.setHorizontalAlignment(SwingConstants.TRAILING);
			label_1.setBounds(290, 0, 35, 31);
		}
		return label_1;
	}

	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(0, 31, 331, 402);
			scrollPane_3.setViewportView(getTbListaPedidosParaEmpaquetar());
		}
		return scrollPane_3;
	}

	private JTable getTbListaPedidosParaEmpaquetar() {
		if (tbListaPedidosParaEmpaquetar == null) {
			tbListaPedidosParaEmpaquetar = new JTable();
			tbListaPedidosParaEmpaquetar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						currentOrder = modeloTablaPedidosPorEmpaquetar
								.getObjectAtRow(tbListaPedidosParaEmpaquetar.getSelectedRow()).getOrder();
						System.out.println("Detected 2 clicks at: " + currentOrder.getID());
						try {
							iniciarVentanaEmpaquetadoPedido(currentOrder);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						((CardLayout) (getPanel().getLayout())).show(getPanel(), "ventanaPedidoEmpaquetando");
					}
				}
			});
			tbListaPedidosParaEmpaquetar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tbListaPedidosParaEmpaquetar;
	}

	private JPanel getPnEmpaquetadoPedido() {
		if (pnEmpaquetadoPedido == null) {
			pnEmpaquetadoPedido = new JPanel();
			pnEmpaquetadoPedido.setLayout(null);
			pnEmpaquetadoPedido.add(getPanel_8());
			pnEmpaquetadoPedido.add(getScrollPane_4());
			pnEmpaquetadoPedido.add(getPanel_9());
			pnEmpaquetadoPedido.add(getPanel_10());
		}
		return pnEmpaquetadoPedido;
	}

	private void iniciarVentanaEmpaquetadoPedidos() throws Exception {
		String[] columNames = { "Ped. ID", "Nº Paquetes", "Peso" };
		modeloTablaPedidosPorEmpaquetar = new DefaultNonEditableTableModel<>(columNames, 0);
		for (Order o : new OrderController().getPendientesEmpaquetando()) {
			// System.out.println("Row added");
			modeloTablaPedidosPorEmpaquetar.addRow(new OrderController(o), o.getID(),
					new OrderController(o).getNumberOfItems(), new OrderController().getWeight(o));
		}
		getTbListaPedidosParaEmpaquetar().setModel(modeloTablaPedidosPorEmpaquetar);
	}

	private void updatearVentanaEmpaquetadoPedidos() throws Exception {
		if (modeloTablaPedidosPorEmpaquetar != null) {
			modeloTablaPedidosPorEmpaquetar.removeAll();
			for (Order o : new OrderController().getPendientesEmpaquetando()) {
				modeloTablaPedidosPorEmpaquetar.addRow(new OrderController(o), o.getID(),
						new OrderController(o).getNumberOfItems(), new OrderController().getWeight(o));
			}
		} else {
			String[] columNames = { "Ped. ID", "Nº Paquetes", "Peso" };
			modeloTablaPedidosPorEmpaquetar = new DefaultNonEditableTableModel<>(columNames, 0);
			updatearVentanaEmpaquetadoPedidos();
		}
		
	}

	private void iniciarVentanaEmpaquetadoPedido(Order o) throws Exception {
		mailBox = new MailBoxController().open();
		String[] columNames = { "Prod. ID", "Cantidad", "Empaquetado", "Id Caja" };
		modeloTablaEmpaquetarPedido = new DefaultNonEditableTableModel<>(columNames, 0);
		for (OrderItem oi : o.getProducts()) {
			System.out.println("Order: " + o.getID() + " Product: " + oi.getProductID());
			if (oi.getMailbox() != null) {
				modeloTablaEmpaquetarPedido.addRow(new OrderItemController(oi), oi.getProductID(), oi.getQuantity(),
						false, oi.getMailBoxID());
			} else {
				modeloTablaEmpaquetarPedido.addRow(new OrderItemController(oi), oi.getProductID(), oi.getQuantity(),
						false, " - ");
			}
		}
		getTbEmpaquetadoPedidoIndividual().setModel(modeloTablaEmpaquetarPedido);
	}

	private void updateVentanaEmpaquetadoPedido(OrderItem oiToChange, boolean empaquetado) throws Exception {
		modeloTablaEmpaquetarPedido.removeAll();
		for (OrderItem oi : currentOrder.getProducts()) {
			if (oi.getMailbox() != null) {
				modeloTablaEmpaquetarPedido.addRow(new OrderItemController(oi), oi.getProductID(), oi.getQuantity(),
						false, oi.getMailBoxID());
			} else {
				modeloTablaEmpaquetarPedido.addRow(new OrderItemController(oi), oi.getProductID(), oi.getQuantity(),
						false, " - ");
			}
		}
	}

	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(null);
			panel_8.setBounds(0, 0, 331, 31);
			panel_8.add(getLblPedidoParaEmpaquetar());
		}
		return panel_8;
	}

	private JLabel getLblPedidoParaEmpaquetar() {
		if (lblPedidoParaEmpaquetar == null) {
			lblPedidoParaEmpaquetar = new JLabel("Pedido para empaquetar");
			lblPedidoParaEmpaquetar.setBounds(6, 7, 170, 16);
		}
		return lblPedidoParaEmpaquetar;
	}

	private JScrollPane getScrollPane_4() {
		if (scrollPane_4 == null) {
			scrollPane_4 = new JScrollPane();
			scrollPane_4.setBounds(0, 31, 331, 85);
			scrollPane_4.setViewportView(getTbEmpaquetadoPedidoIndividual());
		}
		return scrollPane_4;
	}

	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setBounds(5, 128, 320, 233);
			panel_9.setLayout(new GridLayout(3, 2, 1, 1));
			panel_9.add(getLabel_2());
			panel_9.add(getLblNuevaCaja());
			panel_9.add(getLblGenerarAlbarn());
			panel_9.add(getLblGenerarEtiquetaEnvo());
			panel_9.add(getLabel_3_1());
		}
		return panel_9;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Escanear");
			label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					getTxtEntradaReferenciaEmpaquetado().requestFocus();
				}
			});
			label_2.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/scan-small.png")));
			label_2.setVerticalTextPosition(SwingConstants.BOTTOM);
			label_2.setVerticalAlignment(SwingConstants.BOTTOM);
			label_2.setIconTextGap(0);
			label_2.setHorizontalTextPosition(SwingConstants.CENTER);
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return label_2;
	}

	private JLabel getLblGenerarAlbarn() {
		if (lblGenerarAlbarn == null) {
			lblGenerarAlbarn = new JLabel("Generar Albarán");
			lblGenerarAlbarn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblGenerarAlbarn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					new AlbaranGenerator(currentOrder).generate();
				}
			});
			lblGenerarAlbarn.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/albaran.png")));
			lblGenerarAlbarn.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblGenerarAlbarn.setVerticalAlignment(SwingConstants.BOTTOM);
			lblGenerarAlbarn.setIconTextGap(10);
			lblGenerarAlbarn.setHorizontalTextPosition(SwingConstants.CENTER);
			lblGenerarAlbarn.setHorizontalAlignment(SwingConstants.CENTER);
			lblGenerarAlbarn.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblGenerarAlbarn;
	}

	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setBounds(5, 373, 320, 54);
			panel_10.setLayout(new CardLayout(0, 0));
			panel_10.add(getPnEntradaReferenciaEmpaquetado(), "entradaReferencia");
			panel_10.add(getPnReferenciaIncorrectaEmpaquetado(), "incorrecta");
			panel_10.add(getPnReferenciaCorrectaEmpaquetado(), "correcta");
		}
		return panel_10;
	}

	private JLabel getLblGenerarEtiquetaEnvo() {
		if (lblGenerarEtiquetaEnvo == null) {
			lblGenerarEtiquetaEnvo = new JLabel("Generar Etiqueta Envío");
			lblGenerarEtiquetaEnvo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblGenerarEtiquetaEnvo.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					new ShippingInfoGenerator(currentOrder).generate();
				}
			});
			lblGenerarEtiquetaEnvo.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/shipment.png")));
			lblGenerarEtiquetaEnvo.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblGenerarEtiquetaEnvo.setVerticalAlignment(SwingConstants.BOTTOM);
			lblGenerarEtiquetaEnvo.setIconTextGap(7);
			lblGenerarEtiquetaEnvo.setHorizontalTextPosition(SwingConstants.CENTER);
			lblGenerarEtiquetaEnvo.setHorizontalAlignment(SwingConstants.CENTER);
			lblGenerarEtiquetaEnvo.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblGenerarEtiquetaEnvo;
	}

	private JLabel getLabel_3_1() {
		if (lblFianlizar == null) {
			lblFianlizar = new JLabel("Fianlizar");
			lblFianlizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblFianlizar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lblFianlizar.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					currentOrder.setStatus(Status.FINALIZADO.toString().toUpperCase());
					new OrderController(currentOrder).finalize();
					((CardLayout) (getPanel().getLayout())).show(getPanel(), "opciones");
					try {
						updatearVentanaEmpaquetadoPedidos();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lblFianlizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});
			lblFianlizar.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/correct.png")));
			lblFianlizar.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblFianlizar.setVerticalAlignment(SwingConstants.BOTTOM);
			lblFianlizar.setIconTextGap(-3);
			lblFianlizar.setHorizontalTextPosition(SwingConstants.CENTER);
			lblFianlizar.setHorizontalAlignment(SwingConstants.CENTER);
			lblFianlizar.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblFianlizar;
	}

	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setBounds(6, 6, 319, 174);
			panel_11.setLayout(new GridLayout(0, 2, 2, 2));
			panel_11.add(getLblRecogida());
			panel_11.add(getLblEmpaquetado());
		}
		return panel_11;
	}

	private JLabel getLblNuevaCaja() {
		if (lblNuevaCaja == null) {
			lblNuevaCaja = new JLabel("Nueva caja");
			lblNuevaCaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblNuevaCaja.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lblNuevaCaja.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					mailBox = new MailBoxController().open();
					getLblCorrecta().setText("Nueva Caja Creada.");
					lblNuevaCaja.setCursor(new Cursor(Cursor.HAND_CURSOR));
					((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "correcta");

					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "entradaReferencia");
							getLblCorrecta().setText("Referencia Correcta");
						}
					}, 900);
				}
			});
			lblNuevaCaja.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/box.png")));
			lblNuevaCaja.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblNuevaCaja.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNuevaCaja.setIconTextGap(0);
			lblNuevaCaja.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNuevaCaja.setHorizontalAlignment(SwingConstants.CENTER);
			lblNuevaCaja.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblNuevaCaja;
	}

	private JPanel getPnEntradaReferenciaEmpaquetado() {
		if (pnEntradaReferenciaEmpaquetado == null) {
			pnEntradaReferenciaEmpaquetado = new JPanel();
			pnEntradaReferenciaEmpaquetado.setLayout(null);
			pnEntradaReferenciaEmpaquetado.add(getTxtEntradaReferenciaEmpaquetado());
		}
		return pnEntradaReferenciaEmpaquetado;
	}

	private JTextField getTxtEntradaReferenciaEmpaquetado() {
		if (txtEntradaReferenciaEmpaquetado == null) {
			txtEntradaReferenciaEmpaquetado = new JTextField();
			txtEntradaReferenciaEmpaquetado.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (new EmpaquetadoValidator(currentOrder.getProducts())
								.validate(Integer.parseInt(txtEntradaReferenciaEmpaquetado.getText()))) {
							for (OrderItemController oic : modeloTablaEmpaquetarPedido.getAllRows()) {
								if (oic.getItem().getProduct().getID() == Integer
										.parseInt(txtEntradaReferenciaEmpaquetado.getText())) {
									new MailBoxController().update(oic.getItem(), mailBox);
									try {
										updateVentanaEmpaquetadoPedido(oic.getItem(), true);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							}
							((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "correcta");

							new java.util.Timer().schedule(new java.util.TimerTask() {
								@Override
								public void run() {
									((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "entradaReferencia");
									txtEntradaReferenciaEmpaquetado.requestFocus();
								}
							}, 900);
						} else {
							((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "incorrecta");

							new java.util.Timer().schedule(new java.util.TimerTask() {
								@Override
								public void run() {
									((CardLayout) (getPanel_10().getLayout())).show(getPanel_10(), "entradaReferencia");
									txtEntradaReferenciaEmpaquetado.requestFocus();
								}
							}, 900);
						}
						txtEntradaReferenciaEmpaquetado.setText("");
						txtEntradaReferenciaEmpaquetado.requestFocus();
						
					}
				}
			});
			txtEntradaReferenciaEmpaquetado.setHorizontalAlignment(SwingConstants.CENTER);
			txtEntradaReferenciaEmpaquetado.setBounds(6, 14, 308, 26);
			txtEntradaReferenciaEmpaquetado.setColumns(10);
		}
		return txtEntradaReferenciaEmpaquetado;
	}

	private JTable getTbEmpaquetadoPedidoIndividual() {
		if (tbEmpaquetadoPedidoIndividual == null) {
			tbEmpaquetadoPedidoIndividual = new JTable();
			tbEmpaquetadoPedidoIndividual.setFocusTraversalKeysEnabled(false);
			tbEmpaquetadoPedidoIndividual.setFocusable(false);
			tbEmpaquetadoPedidoIndividual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbEmpaquetadoPedidoIndividual.setRowSelectionAllowed(false);
		}
		return tbEmpaquetadoPedidoIndividual;
	}

	private JPanel getPnReferenciaIncorrectaEmpaquetado() {
		if (pnReferenciaIncorrectaEmpaquetado == null) {
			pnReferenciaIncorrectaEmpaquetado = new JPanel();
			pnReferenciaIncorrectaEmpaquetado.setLayout(null);
			pnReferenciaIncorrectaEmpaquetado.add(getLabel_3());
		}
		return pnReferenciaIncorrectaEmpaquetado;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Referencia Incorreecta");
			label_3.setIcon(new ImageIcon(
					VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/incorrect-small.png")));
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
			label_3.setForeground(new Color(255, 51, 102));
			label_3.setBounds(6, 6, 308, 42);
		}
		return label_3;
	}

	private JPanel getPnReferenciaCorrectaEmpaquetado() {
		if (pnReferenciaCorrectaEmpaquetado == null) {
			pnReferenciaCorrectaEmpaquetado = new JPanel();
			pnReferenciaCorrectaEmpaquetado.setLayout(null);
			pnReferenciaCorrectaEmpaquetado.add(getLblCorrecta());
		}
		return pnReferenciaCorrectaEmpaquetado;
	}

	private JLabel getLblCorrecta() {
		if (lblCorrecta == null) {
			lblCorrecta = new JLabel("Referencia Correcta");
			lblCorrecta.setIcon(
					new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/igu/img/correct.png")));
			lblCorrecta.setHorizontalAlignment(SwingConstants.CENTER);
			lblCorrecta.setForeground(new Color(0, 153, 51));
			lblCorrecta.setBounds(6, 6, 308, 42);
		}
		return lblCorrecta;
	}
}
