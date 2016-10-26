package com.myshop.model.warehouse;

/**
 * Warehouse keeper ODM.
 * 
 * @version 2610161321
 * @author Guillermo Facundo Colunga
 */
public class WarehouseKeeper {
	
	private int warehouseKeeperID;
	private String name, surname;
	
	/**
	 * @return the warehouse keeper id.
	 */
	public int getWarehouseKeeperID() { return this.warehouseKeeperID; }
	
	/**
	 * @param warehouseKeeperID the warehouse keeper id.
	 */
	public void setWarehouseKeeperID(int warehouseKeeperID) { this.warehouseKeeperID = warehouseKeeperID; }
	
	/**
	 * @return the name of the warehouse keeper.
	 */
	public String getName() { return this.name; }
	
	/**
	 * @param name the name of the warehouse keeper.
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return the surname of the warehouse keeper.
	 */
	public String getSurname() { return this.surname; }
	
	/**
	 * @param surname the surname of the warehouse keeper.
	 */
	public void setSurname(String surname) { this.surname = surname; }

}
