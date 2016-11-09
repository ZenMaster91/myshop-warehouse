package com.myshop.warehouse.igu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class DefaultNonEditableTableModel<T extends Comparable<T>> extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<T> list = new ArrayList<T>();

	/**
	 * Constructor intended to create a DefaultTableModel from its column names,
	 * its rowCount and a reserva object type.
	 * 
	 * @param columnNames are the names of each column.
	 * @param rowCount the number of rows the table holds.
	 * @param reserva the current reserva where the model is going to work.
	 */
	public DefaultNonEditableTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}
	
	/**
	 * Given a camarote as parameter this method will added to the model by its
	 * attributes while at the same time will add it to a list where it will be
	 * stored.
	 * 
	 * @param c is the camarote that will be added to the model.
	 * @throws Exception 
	 */
	public void addRow(T row, Object... values) throws Exception {
		if(/*contains(row)*/false)
			throw new Exception();
		Object[] newRow = new Object[getColumnCount()];

		for(int i = 0; i < getColumnCount(); i++) {
			newRow[i] = values[i];
		}
		super.addRow(newRow);
		list.add(row);
	}

	private boolean contains(T wpc) {
		return list.contains(wpc);
	}

	/**
	 * Gets a completely different list with all the camarotes.
	 * 
	 * @return a different list with all the camarotes.
	 */
	public List<T> getAllRows() {
		List<T> aux = new ArrayList<T>();
		for (T iterator : list)
			aux.add(iterator);
		return aux;
	}

	/**
	 * Given a row index will return the camarote that is being displayed at.
	 * 
	 * @param row is the index in the table.
	 * @return the camarote associated with that table row.
	 */
	public T getObjectAtRow(int row) {
		return list.get(row);

	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Refreshes the table.
	 */
	public void refresh() {
		List<T> aux = getAllRows();
		removeAll();
		for(T iterator : aux) {
			try {
				addRow(iterator);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fireTableDataChanged();
	}

	/**
	 * Removes all the elements in the model and in the auxiliary list.
	 */
	public void removeAll() {
		dataVector.removeAllElements();
		list.clear();
	}
	
	/**
	 * Given a camarote will remove it from the model and from the auxiliary
	 * list.
	 * 
	 * @param wpc is the camarote to remove.
	 * @throws IllegalStateException if the camarote given as a parameter is not
	 *             in the model.
	 */
	public void removeRow(T wpc) {
		if (!list.contains(wpc))
			throw new IllegalStateException("This room is not in the model");
		super.removeRow(list.indexOf(wpc));
		list.remove(wpc);
	}
}
