package org.compiere.model;

/**
 * Bean para almacenar los datos para la creacion de los detalles de las cuentas paciente globales
 * antes de crear el registro de MCtaPacDet, guardamos la sumatoria y los datos de id de producto y
 * id de unidad de venta 
 *  
 * <p>
 * Modificado por: $Author:  $ <p>
 * Fecha: $Date: 2006/12/06 $ <p>
 * 
 * @author Valentin Garcia
 * @version $Revision: 1.0 $
 */

public class CtaFactEspecBean {
	
	// propiedades del bean

	private int product_id = 0;
	
	private int unidadProducto_id = 0;
	
	private int qtyOrdered = 0;
	
	private int qtyDelivered = 0;
	
	private double price = 0;

	//Getters y Setters de las propiedades
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQtyDelivered() {
		return qtyDelivered;
	}

	public void setQtyDelivered(int qtyDelivered) {
		this.qtyDelivered = qtyDelivered;
	}

	public int getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(int qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public int getUnidadProducto_id() {
		return unidadProducto_id;
	}

	public void setUnidadProducto_id(int unidadProducto_id) {
		this.unidadProducto_id = unidadProducto_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
