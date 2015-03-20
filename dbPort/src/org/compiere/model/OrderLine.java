/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.io.Serializable;

/**
 * Modelo que representa una linea de una entrada
 * al almacen
 * <p>
 * Creado: 14-Marzo-2006
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/09/08 01:30:07 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
*/

public class OrderLine implements Serializable {



    /**

     * Propiedad OrderLineID.

     */

    private int orderLineID = 0;



    /**

     * Obtener la propiedad orderLineID.

     * 

     *@return La propiedad OrderLineID.

     */

    public int getOrderLineID() {

        return this.orderLineID;

    }



    /**

     * Establecer la propiedad orderLineID.

     * 

     *@param oderLineID Nueva propiedad orderLineID.

     */

    public void setOrderLineID(int orderLineID) {

        this.orderLineID = orderLineID;

    }



    /**

     * Propiedad Line.

     */

    private long line = 0;



    /**

     * Obtener la propiedad line.

     * 

     *@return La propiedad Line.

     */

    public long getLine() {

        return this.line;

    }



    /**

     * Establecer la propiedad line.

     * 

     *@param line Nueva propiedad line.

     */

    public void setLine(long line) {

        this.line = line;

    }



    /**

    * Propiedad ProductID.

    */

    private int productID = 0;



    /**

     * Obtener la propiedad productID.

     * 

     *@return La propiedad ProductID.

     */

    public int getProductID() {

        return this.productID;

    }



    /**

     * Establecer la propiedad productID.

     * 

     *@param productID Nueva propiedad productID.

     */

    public void setProductID(int productID) {

        this.productID = productID;

    }



    /**

     * Propiedad ProdName.

     */

    private String prodName = null;



    /**

     * Obtener la propiedad prodName.

     * 

     *@return La propiedad ProdName.

     */

    public String getProdName() {

        return this.prodName;

    }



    /**

     * Establecer la propiedad prodName.

     * 

     *@param prodName Nueva propiedad prodName.

     */

    public void setProdName(String prodName) {

        this.prodName = prodName;

    }



    /**

     * Propiedad UomName.

     */

    private String uomName = null;



    /**

     * Obtener la propiedad uomName.

     * 

     *@return La propiedad UomName.

     */

    public String getUomName() {

        return this.uomName;

    }



    /**

     * Establecer la propiedad uomName.

     * 

     *@param uomName Nueva propiedad uomName.

     */

    public void setUomName(String uomName) {

        this.uomName = uomName;

    }



    /**

    * Propiedad Value.

    */

    private String value = null;



    /**

     * Obtener la propiedad Value.

     * 

     *@return La propiedad Value.

     */

    public String getValue() {

        return this.value;

    }



    /**

     * Establecer la propiedad Value.

     * 

     *@param Value Nueva propiedad Value.

     */

    public void setValue(String value) {

        this.value = value;

    }



    /**

     * Propiedad OriginalQty.

     */

    private int originalQty = 0;



    /**

     * Obtener la propiedad originalQty.

     * 

     *@return La propiedad OriginalQty.

     */

    public int getOriginalQty() {

        return this.originalQty;

    }



    /**

     * Establecer la propiedad originalQty.

     * 

     *@param originalQty Nueva propiedad originalQty.

     */

    public void setOriginalQty(int originalQty) {

        this.originalQty = originalQty;

    }



    /**

     * Propiedad Costo Unitario.

     */

    private double costoUnit = 0.0;



    /**

     * Obtener la propiedad costoUnit.

     * 

     *@return La propiedad costoUnit.

     */

    public double getCostoUnit() {

        return this.costoUnit;

    }



    /**

     * Establecer la propiedad costoUnit.

     * 

     *@param originalQty Nueva propiedad costoUnit.

     */

    public void setCostoUnit(double costoUnit) {

        this.costoUnit = costoUnit;

    }

    

    /**

     * Propiedad taxID.

     */

    private int taxID = 0;



    /**

     * Obtener la propiedad taxID.

     * 

     *@return La propiedad taxID.

     */

    public int getTaxID() {

        return this.taxID;

    }



    /**

     * Establecer la propiedad taxID.

     * 

     *@param docTypeID Nueva propiedad taxID.

     */

    public void setTaxID(int taxID) {

        this.taxID = taxID;

    }



    /**

     * Propiedad TaxName.

     */

    private String taxName = null;



    /**

     * Obtener la propiedad taxName.

     * 

     *@return La propiedad taxName.

     */

    public String getTaxName() {

        return this.taxName;

    }



    /**

     * Establecer la propiedad taxName.

     * 

     *@param taxName Nueva propiedad taxName.

     */

    public void setTaxName(String taxName) {

        this.taxName = taxName;

    }



    

    /**

     * Propiedad uPC

     */

    private String upC = null;



    /**

     * Obtener la propiedad uPC

     * 

     *@return La propiedad uPC

     */

    public String getUpC() {

        return this.upC;

    }



    /**

     * Establecer la propiedad uPC.

     * 

     *@param prodName Nueva propiedad uPC.

     */

    public void setUpC(String upC) {

        this.upC = upC;

    }

    /**

    * Propiedad UOM.

    */

    private int uOM = 0;



    /**

     * Obtener la propiedad uOM.

     * 

     *@return La propiedad uOM.

     */

    public int getUOM() {

        return this.uOM;

    }



    /**

     * Establecer la propiedad uOM.

     * 

     *@param productID Nueva propiedad uOM.

     */

    public void setUOM(int uOM) {

        this.uOM = uOM;

    }

    private double costoUnitOld = 0.0;

	public double getCostoUnitOld() {
		return costoUnitOld;
	}

	public void setCostoUnitOld(double costoUnitOld) {
		this.costoUnitOld = costoUnitOld;
	}

	private String lote;

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}	
	
	private String fechaLote;

	public String getFechaLote() {
		return fechaLote;
	}

	public void setFechaLote(String fechaLote) {
		this.fechaLote = fechaLote;
	}
	
	private int M_AttributeSetInstance = 0;

	public int getM_AttributeSetInstance() {
		return M_AttributeSetInstance;
	}

	public void setM_AttributeSetInstance(int attributeSetInstance) {
		M_AttributeSetInstance = attributeSetInstance;
	}

	private String loteInfo;

	public String getLoteInfo() {
		return loteInfo;
	}

	public void setLoteInfo(String loteInfo) {
		this.loteInfo = loteInfo;
	}
	
	private int labelerID = 0;
	private String labelerCode = null;
	private String labelerName = null;



	public int getLabelerID() {
		return labelerID;
	}



	public void setLabelerID(int labelerID) {
		this.labelerID = labelerID;
	}



	public String getLabelerCode() {
		return labelerCode;
	}



	public void setLabelerCode(String labelerCode) {
		this.labelerCode = labelerCode;
	}



	public String getLabelerName() {
		return labelerName;
	}



	public void setLabelerName(String labelerName) {
		this.labelerName = labelerName;
	}
}

