package com.myshop.warehouse.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controller.OrderController;
import com.myshop.warehouse.controller.WarehouseKeeperController;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
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

public class VentanaAlmaceneros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnInicioAlmacenero;
	private JTextField TxLogin;
	private JLabel lblInicio;
	private JButton btnAceptar;
	private JPanel pnOpcionesAlmacenero;
	private JLabel lblRecogida;
	private JLabel lblEmpaquetado;
	private JButton btnRecogida;
	private JButton btnEmpaquetado;
	private JPanel pnOpciones;
	private JPanel pnVolverOpciones;
	private JButton btnOpciones;
	private JPanel pnCardOpciones;
	private JScrollPane scpRecogida;
	private JTable tbRecogida;
	private JScrollPane scpEmpaquetado;
	private JTable tbEmpaquetado;
	private DefaultTableModel modeloTablaRecogida;
	private DefaultTableModel modeloTablaEmpaquetado;
	private DefaultTableModel modeloTablaRecogidaProductos;
	private DefaultTableModel modeloTablaEmpaquetadoPedido;
	private JPanel pnRecogidaEmpaquetado;
	private JPanel pnPedido;
	private JLabel lblPedido;
	private JTextField txPedido;
	private JPanel pnRecogidaEmpaquetadoProductos;
	private JScrollPane scpRecogidaProductos;
	private JTable tbRecogidaProductos;
	private JScrollPane scpEmpaquetadoPedido;
	private JTable tbEmpaquetadoPedido;
	private JPanel pnComprobarReferencias;
	private JButton btnComprobarReferencia;
	private JPanel pnAuxiliarEmpaquetadoPedido;
	private JPanel pnIntroducirReferencia;
	private JButton btnCancelarReferencia;
	private JButton btnComprobarRerefencia;
	private JLabel lblIntroducirReferenciaDel;
	private JTextField txReferencia;
	private Long id;

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
		setBounds(100, 100, 318, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnInicioAlmacenero(), "name_62048243610437");
		contentPane.add(getPnOpcionesAlmacenero(), "name_62329189689275");
		contentPane.add(getPnOpciones(), "name_62590809505526");
		contentPane.add(getPnRecogidaEmpaquetado(), "name_66035732794389");
		contentPane.add(getPnIntroducirReferencia(), "name_67971427393689");
	}

	private JPanel getPnInicioAlmacenero() {
		if (pnInicioAlmacenero == null) {
			pnInicioAlmacenero = new JPanel();
			pnInicioAlmacenero.setLayout(null);
			pnInicioAlmacenero.add(getTxLogin());
			pnInicioAlmacenero.add(getLblInicio());
			pnInicioAlmacenero.add(getBtnAceptar());
		}
		return pnInicioAlmacenero;
	}
	private JTextField getTxLogin() {
		if (TxLogin == null) {
			TxLogin = new JTextField();
			TxLogin.setBounds(82, 166, 145, 31);
			TxLogin.setColumns(10);
		}
		return TxLogin;
	}
	private JLabel getLblInicio() {
		if (lblInicio == null) {
			lblInicio = new JLabel("INICIO");
			lblInicio.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 17));
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicio.setBounds(44, 54, 207, 46);
		}
		return lblInicio;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO
					WarehouseKeeper almacenero = new WarehouseKeeperController().getWarehouseKeeperbyNameSur(getTxLogin().getText());

					if(almacenero != null){
						mostrarVentanaOpciones();
					}
					else{
						JOptionPane.showConfirmDialog(null, "Usuario no valido, vuelva a intentarlo.");
					}
				}
			});
			btnAceptar.setBounds(108, 276, 89, 23);
		}
		return btnAceptar;
	}
	
	private void mostrarVentanaOpciones(){
		getPnInicioAlmacenero().setVisible(false);
		getPnOpcionesAlmacenero().setVisible(true);
		getPnVolverOpciones().setVisible(false);
	}
	private JPanel getPnOpcionesAlmacenero() {
		if (pnOpcionesAlmacenero == null) {
			pnOpcionesAlmacenero = new JPanel();
			pnOpcionesAlmacenero.setLayout(null);
			pnOpcionesAlmacenero.add(getLblRecogida());
			pnOpcionesAlmacenero.add(getLblEmpaquetado());
			pnOpcionesAlmacenero.add(getBtnRecogida());
			pnOpcionesAlmacenero.add(getBtnEmpaquetado());
		}
		return pnOpcionesAlmacenero;
	}
	private JLabel getLblRecogida() {
		if (lblRecogida == null) {
			lblRecogida = new JLabel("Recogida");
			lblRecogida.setIcon(new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/files/recogida.png")));
			lblRecogida.setHorizontalAlignment(SwingConstants.CENTER);
			lblRecogida.setBounds(31, 64, 123, 132);
		}
		return lblRecogida;
	}
	private JLabel getLblEmpaquetado() {
		if (lblEmpaquetado == null) {
			lblEmpaquetado = new JLabel("Empaquetado");
			lblEmpaquetado.setIcon(new ImageIcon(VentanaAlmaceneros.class.getResource("/com/myshop/warehouse/files/empaquetado.png")));
			lblEmpaquetado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEmpaquetado.setBounds(164, 64, 138, 132);
		}
		return lblEmpaquetado;
	}
	private JButton getBtnRecogida() {
		if (btnRecogida == null) {
			btnRecogida = new JButton("Recogida");
			btnRecogida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaOpcionesRecogida();
				}
			});
			btnRecogida.setBounds(31, 236, 102, 23);
		}
		return btnRecogida;
	}
	
	private void mostrarVentanaOpcionesRecogida(){
		getPnOpcionesAlmacenero().setVisible(false);
		getPnOpciones().setVisible(true);
		getScpEmpaquetado().setVisible(false);
		getScpRecogida().setVisible(true);
		getPnVolverOpciones().setVisible(true);
	}
	
	private void mostrarVentanaOpcionesEmpaquetado(){
		getPnOpcionesAlmacenero().setVisible(false);
		getPnOpciones().setVisible(true);
		getScpRecogida().setVisible(false);
		getScpEmpaquetado().setVisible(true);
		getPnVolverOpciones().setVisible(true);
	}
	
	private JButton getBtnEmpaquetado() {
		if (btnEmpaquetado == null) {
			btnEmpaquetado = new JButton("Empaquetado");
			btnEmpaquetado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaOpcionesEmpaquetado();
				}
			});
			btnEmpaquetado.setBounds(176, 236, 102, 23);
		}
		return btnEmpaquetado;
	}
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setLayout(new BorderLayout(0, 0));
			pnOpciones.add(getPnVolverOpciones(), BorderLayout.NORTH);
			pnOpciones.add(getPnCardOpciones(), BorderLayout.CENTER);
		}
		return pnOpciones;
	}
	private JPanel getPnVolverOpciones() {
		if (pnVolverOpciones == null) {
			pnVolverOpciones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnVolverOpciones.getLayout();
			flowLayout.setVgap(7);
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnVolverOpciones.add(getBtnOpciones());
		}
		return pnVolverOpciones;
	}
	private JButton getBtnOpciones() {
		if (btnOpciones == null) {
			btnOpciones = new JButton("Atras");
			btnOpciones.setIcon(null);
			btnOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaOpciones();
				}
			});
		}
		return btnOpciones;
	}
	private JPanel getPnCardOpciones() {
		if (pnCardOpciones == null) {
			pnCardOpciones = new JPanel();
			pnCardOpciones.setLayout(new CardLayout(0, 0));
			pnCardOpciones.add(getScpRecogida(), "name_62972787916349");
			pnCardOpciones.add(getScpEmpaquetado(), "name_63064214024801");
		}
		return pnCardOpciones;
	}
	private JScrollPane getScpRecogida() {
		if (scpRecogida == null) {
			scpRecogida = new JScrollPane();
			scpRecogida.setViewportView(getTbRecogida());
		}
		return scpRecogida;
	}
	private JTable getTbRecogida() {
		if (tbRecogida == null) {
			String[] columnas = { "ID","Cantidad", "Fecha" };
			modeloTablaRecogida = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbRecogida = new JTable(modeloTablaRecogida);
			tbRecogida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int seleccionada = tbRecogida.getSelectedRow();
					Long id = Long.parseLong(tbRecogida
							.getValueAt(seleccionada, 0).toString());
					
					//TODO Order o = new GetOrders().getOrderByID(id);
					WarehouseKeeper almacenero = new WarehouseKeeperController().
							getWarehouseKeeperbyNameSur(getTxLogin().getText());
					//getTxPedido().setText(o.getID()+"");
					//TODO
					//crear OT con el almacenero que soy ("almacenero") y el pedido seleccionado
					//muestroPantalla de recogida de productos del pedido elegido
					mostrarVentanaRecogiendo();
				}
			});
		}
		return tbRecogida;
	}
	private JScrollPane getScpEmpaquetado() {
		if (scpEmpaquetado == null) {
			scpEmpaquetado = new JScrollPane();
			scpEmpaquetado.setViewportView(getTbEmpaquetado());
		}
		return scpEmpaquetado;
	}
	
	private JTable getTbEmpaquetado() {
		if (tbEmpaquetado == null) {
			String[] columnas = { "ID","Cantidad", "Fecha"};
			modeloTablaEmpaquetado = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbEmpaquetado = new JTable(modeloTablaEmpaquetado);
			tbEmpaquetado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int seleccionada = tbEmpaquetado.getSelectedRow();
					Long id = Long.parseLong(tbEmpaquetado
							.getValueAt(seleccionada, 0).toString());
					//TODO
					Order o = new OrderController().getOrderById(id+"");
					o.setStatus("EMPAQUETANDO");
					getTxPedido().setText(o.getID()+"");
					//cambiar estado del pedido a empaquetando
					//muestroPantalla de empaquetado de productos del pedido elegido
					mostrarVentanaEmpaquetando();
				}
			});
		}
		return tbEmpaquetado;
	}
	
	private void mostrarVentanaEmpaquetando(){
		getPnRecogidaEmpaquetado().setVisible(true);
		getScpRecogidaProductos().setVisible(false);
		getPnAuxiliarEmpaquetadoPedido().setVisible(true);
	}
	//TODO
	private void mostrarVentanaRecogiendo(){
		getPnRecogidaEmpaquetado().setVisible(true);
		getPnAuxiliarEmpaquetadoPedido().setVisible(false);
		getScpRecogidaProductos().setVisible(true);
	}
	
	private JPanel getPnRecogidaEmpaquetado() {
		if (pnRecogidaEmpaquetado == null) {
			pnRecogidaEmpaquetado = new JPanel();
			pnRecogidaEmpaquetado.setLayout(new BorderLayout(0, 0));
			pnRecogidaEmpaquetado.add(getPnPedido(), BorderLayout.NORTH);
			pnRecogidaEmpaquetado.add(getPnRecogidaEmpaquetadoProductos(), BorderLayout.CENTER);
		}
		return pnRecogidaEmpaquetado;
	}
	private JPanel getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPedido.getLayout();
			flowLayout.setHgap(35);
			pnPedido.add(getLblPedido());
			pnPedido.add(getTxPedido());
		}
		return pnPedido;
	}
	private JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido: ");
			lblPedido.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 12));
		}
		return lblPedido;
	}
	private JTextField getTxPedido() {
		if (txPedido == null) {
			txPedido = new JTextField();
			txPedido.setColumns(10);
		}
		return txPedido;
	}
	private JPanel getPnRecogidaEmpaquetadoProductos() {
		if (pnRecogidaEmpaquetadoProductos == null) {
			pnRecogidaEmpaquetadoProductos = new JPanel();
			pnRecogidaEmpaquetadoProductos.setLayout(new CardLayout(0, 0));
			pnRecogidaEmpaquetadoProductos.add(getScpRecogidaProductos(), "name_66858758134312");
			pnRecogidaEmpaquetadoProductos.add(getPnAuxiliarEmpaquetadoPedido(), "name_67775349536801");
		}
		return pnRecogidaEmpaquetadoProductos;
	}
	private JScrollPane getScpRecogidaProductos() {
		if (scpRecogidaProductos == null) {
			scpRecogidaProductos = new JScrollPane();
			scpRecogidaProductos.setViewportView(getTbRecogidaProductos());
		}
		return scpRecogidaProductos;
	}
	private JTable getTbRecogidaProductos() {
		if (tbRecogidaProductos == null) {
			String[] columnas = { "ID","Cantidad","Precio",
					"Pasillo", "Lado","Posicion","Altura" };
			modeloTablaRecogidaProductos = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbRecogidaProductos = new JTable(modeloTablaRecogidaProductos);
			tbRecogidaProductos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int seleccionada = tbRecogidaProductos.getSelectedRow();
					Long id = Long.parseLong(tbRecogidaProductos
							.getValueAt(seleccionada, 0).toString());
					//TODO
				}
			});
		}
		return tbRecogidaProductos;
	}
	private JScrollPane getScpEmpaquetadoPedido() {
		if (scpEmpaquetadoPedido == null) {
			scpEmpaquetadoPedido = new JScrollPane();
			scpEmpaquetadoPedido.setViewportView(getTbEmpaquetadoPedido());
		}
		return scpEmpaquetadoPedido;
	}
	private JTable getTbEmpaquetadoPedido() {
		if (tbEmpaquetadoPedido == null) {
			String[] columnas = { "ID","Cantidad","Precio",
					"Pasillo", "Lado","Posicion","Altura" };
			modeloTablaRecogidaProductos = new DefaultTableModel(columnas, 0) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tbEmpaquetadoPedido = new JTable(modeloTablaRecogidaProductos);
			tbEmpaquetadoPedido.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int seleccionada = tbEmpaquetadoPedido.getSelectedRow();
					Long id = Long.parseLong(tbEmpaquetadoPedido
							.getValueAt(seleccionada, 0).toString());
					//TODO
				}
			});
		}
		return tbEmpaquetadoPedido;
	}
	private JPanel getPnComprobarReferencias() {
		if (pnComprobarReferencias == null) {
			pnComprobarReferencias = new JPanel();
			pnComprobarReferencias.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnComprobarReferencias.add(getBtnComprobarReferencia());
		}
		return pnComprobarReferencias;
	}
	private JButton getBtnComprobarReferencia() {
		if (btnComprobarReferencia == null) {
			btnComprobarReferencia = new JButton("Comprobar referencia");
			btnComprobarReferencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getTbEmpaquetadoPedido().getSelectedRow() == -1){
						JOptionPane.showMessageDialog(null, "Seleccione uno de"
								+ " los productos \npara comprobar su referencia");
					}
					else{
						id = Long.parseLong(getTbEmpaquetadoPedido()
								.getValueAt(getTbEmpaquetadoPedido().getSelectedRow(), 0).toString());
						getPnIntroducirReferencia().setVisible(true);
					}
				}
			});
		}
		return btnComprobarReferencia;
	}
	private JPanel getPnAuxiliarEmpaquetadoPedido() {
		if (pnAuxiliarEmpaquetadoPedido == null) {
			pnAuxiliarEmpaquetadoPedido = new JPanel();
			pnAuxiliarEmpaquetadoPedido.setLayout(new BorderLayout(0, 0));
			pnAuxiliarEmpaquetadoPedido.add(getScpEmpaquetadoPedido(), BorderLayout.CENTER);
			pnAuxiliarEmpaquetadoPedido.add(getPnComprobarReferencias(), BorderLayout.SOUTH);
		}
		return pnAuxiliarEmpaquetadoPedido;
	}
	private JPanel getPnIntroducirReferencia() {
		if (pnIntroducirReferencia == null) {
			pnIntroducirReferencia = new JPanel();
			pnIntroducirReferencia.setLayout(null);
			pnIntroducirReferencia.add(getBtnCancelarReferencia());
			pnIntroducirReferencia.add(getBtnComprobarRerefencia());
			pnIntroducirReferencia.add(getLblIntroducirReferenciaDel());
			pnIntroducirReferencia.add(getTxReferencia());
		}
		return pnIntroducirReferencia;
	}
	private JButton getBtnCancelarReferencia() {
		if (btnCancelarReferencia == null) {
			btnCancelarReferencia = new JButton("Cancelar Referencia");
			btnCancelarReferencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getPnIntroducirReferencia().setVisible(false);
				}
			});
			btnCancelarReferencia.setBounds(61, 279, 169, 23);
		}
		return btnCancelarReferencia;
	}
	private JButton getBtnComprobarRerefencia() {
		if (btnComprobarRerefencia == null) {
			btnComprobarRerefencia = new JButton("Comprobar Rerefencia");
			btnComprobarRerefencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!getTxReferencia().getText().equals(id+"")){
						JOptionPane.showConfirmDialog(null, "Error, ese producto"
								+ " no se encuentra en dicho pedido");
					}
					else{
						JOptionPane.showConfirmDialog(null, "Correcto");
						getPnIntroducirReferencia().setVisible(false);
					}
				}
			});
			btnComprobarRerefencia.setBounds(61, 203, 169, 23);
		}
		return btnComprobarRerefencia;
	}
	private JLabel getLblIntroducirReferenciaDel() {
		if (lblIntroducirReferenciaDel == null) {
			lblIntroducirReferenciaDel = new JLabel("Introducir referencia del producto");
			lblIntroducirReferenciaDel.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 14));
			lblIntroducirReferenciaDel.setBounds(49, 63, 203, 23);
		}
		return lblIntroducirReferenciaDel;
	}
	private JTextField getTxReferencia() {
		if (txReferencia == null) {
			txReferencia = new JTextField();
			txReferencia.setBounds(49, 124, 203, 23);
			txReferencia.setColumns(10);
		}
		return txReferencia;
	}
	
	
	private void rellenarPedidosARecoger() throws ClassNotFoundException{

		Object[] nuevaFila = new Object[3];
		
		List<Order> pedidos = new OrderController().getOrderByStatus("PENDIENTE");

		for (Order c : pedidos) {
			nuevaFila[0] = c.getID();
			int cantidad = 0;
			for(OrderItem o : c.getProducts()){
				cantidad = cantidad + o.getQuantity();
			}
			nuevaFila[1] = cantidad;
			nuevaFila[2] = c.getDateReceived();
			modeloTablaRecogida.addRow(nuevaFila);
		}
	}
	
	private void rellenarPedidosAEmpaquetar() throws ClassNotFoundException{

		Object[] nuevaFila = new Object[3];
		
		List<Order> pedidos = new OrderController().getOrderByStatus("SOLICITADO");

		for (Order c : pedidos) {
			nuevaFila[0] = c.getID();
			int cantidad = 0;
			for(OrderItem o : c.getProducts()){
				cantidad = cantidad + o.getQuantity();
			}
			nuevaFila[1] = cantidad;
			nuevaFila[2] = c.getDateReceived();
			modeloTablaEmpaquetado.addRow(nuevaFila);
		}
	}
	
	private void rellenarProductosPedido(){
		Object[] nuevaFila = new Object[7];

		List<OrderItem> productos = new OrderController().
				getAllByOrderId(getTxPedido().getText());

		for (OrderItem c : productos) {
			nuevaFila[0] = c.getID();
			nuevaFila[1] = c.getQuantity();
			nuevaFila[2] = c.getProductPrice();
			nuevaFila[3] = c.getProductProductLocationCorridor();
			nuevaFila[4] = c.getProductProductLocationSide();
			nuevaFila[5] = c.getProductProductLocationPosition();
			nuevaFila[6] = c.getProductProductLocationHeight();
			modeloTablaRecogidaProductos.addRow(nuevaFila);
		}
	}
	
}
