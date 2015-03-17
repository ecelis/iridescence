/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos_Pres
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos_Pres 
{

    /** TableName=EXME_Tratamientos_Pres */
    public static final String Table_Name = "EXME_Tratamientos_Pres";

    /** AD_Table_ID=1201105 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

    /** Column name Dosis */
    public static final String COLUMNNAME_Dosis = "Dosis";

	/** Set Dose	  */
	public void setDosis (String Dosis);

	/** Get Dose	  */
	public String getDosis();

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (BigDecimal Duracion);

	/** Get Duration.
	  * Duration
	  */
	public BigDecimal getDuracion();

    /** Column name EXME_Tratamientos_Detalle_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Detalle_ID = "EXME_Tratamientos_Detalle_ID";

	/** Set Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID);

	/** Get Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID();

	public I_EXME_Tratamientos_Detalle getEXME_Tratamientos_Detalle() throws RuntimeException;

    /** Column name EXME_Tratamientos_Pres_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Pres_ID = "EXME_Tratamientos_Pres_ID";

	/** Set Prescription treatment	  */
	public void setEXME_Tratamientos_Pres_ID (int EXME_Tratamientos_Pres_ID);

	/** Get Prescription treatment	  */
	public int getEXME_Tratamientos_Pres_ID();

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (BigDecimal Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public BigDecimal getIntervalo();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

    /** Column name SessionNo */
    public static final String COLUMNNAME_SessionNo = "SessionNo";

	/** Set Session Number.
	  * Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo);

	/** Get Session Number.
	  * Session Number of a treatment
	  */
	public int getSessionNo();

    /** Column name UOMDuracion */
    public static final String COLUMNNAME_UOMDuracion = "UOMDuracion";

	/** Set Duration UOM	  */
	public void setUOMDuracion (String UOMDuracion);

	/** Get Duration UOM	  */
	public String getUOMDuracion();

    /** Column name UOMIntervalo */
    public static final String COLUMNNAME_UOMIntervalo = "UOMIntervalo";

	/** Set Interval UOM.
	  * Interval UOM
	  */
	public void setUOMIntervalo (String UOMIntervalo);

	/** Get Interval UOM.
	  * Interval UOM
	  */
	public String getUOMIntervalo();
}
