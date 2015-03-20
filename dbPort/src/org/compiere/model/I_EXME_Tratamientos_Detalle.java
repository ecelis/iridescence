/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tratamientos_Detalle
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tratamientos_Detalle 
{

    /** TableName=EXME_Tratamientos_Detalle */
    public static final String Table_Name = "EXME_Tratamientos_Detalle";

    /** AD_Table_ID=1200374 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (int Duracion);

	/** Get Duration.
	  * Duration
	  */
	public int getDuracion();

    /** Column name EXME_PaqBase_ID */
    public static final String COLUMNNAME_EXME_PaqBase_ID = "EXME_PaqBase_ID";

	/** Set Base Package.
	  * Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID);

	/** Get Base Package.
	  * Base Package
	  */
	public int getEXME_PaqBase_ID();

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException;

    /** Column name EXME_Tratamientos_Detalle_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Detalle_ID = "EXME_Tratamientos_Detalle_ID";

	/** Set Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID);

	/** Get Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID();

    /** Column name EXME_Tratamientos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_ID = "EXME_Tratamientos_ID";

	/** Set Treatments.
	  * Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID);

	/** Get Treatments.
	  * Treatments
	  */
	public int getEXME_Tratamientos_ID();

    /** Column name Indicaciones_Med */
    public static final String COLUMNNAME_Indicaciones_Med = "Indicaciones_Med";

	/** Set Medical Indications	  */
	public void setIndicaciones_Med (String Indicaciones_Med);

	/** Get Medical Indications	  */
	public String getIndicaciones_Med();

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (String Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public String getIntervalo();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name QtyIntervalo */
    public static final String COLUMNNAME_QtyIntervalo = "QtyIntervalo";

	/** Set Quantity Range	  */
	public void setQtyIntervalo (int QtyIntervalo);

	/** Get Quantity Range	  */
	public int getQtyIntervalo();

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

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
