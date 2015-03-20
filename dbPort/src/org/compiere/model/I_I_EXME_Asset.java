/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Asset
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Asset 
{

    /** TableName=I_EXME_Asset */
    public static final String Table_Name = "I_EXME_Asset";

    /** AD_Table_ID=1200860 */
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

    /** Column name Clase */
    public static final String COLUMNNAME_Clase = "Clase";

	/** Set Class.
	  * Indicates the class to which belongs the group of assets
	  */
	public void setClase (String Clase);

	/** Get Class.
	  * Indicates the class to which belongs the group of assets
	  */
	public String getClase();

    /** Column name Cuenta */
    public static final String COLUMNNAME_Cuenta = "Cuenta";

	/** Set Account	  */
	public void setCuenta (String Cuenta);

	/** Get Account	  */
	public String getCuenta();

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

    /** Column name Estacion */
    public static final String COLUMNNAME_Estacion = "Estacion";

	/** Set Station.
	  * Sets the search key of the Service Station
	  */
	public void setEstacion (String Estacion);

	/** Get Station.
	  * Sets the search key of the Service Station
	  */
	public String getEstacion();

    /** Column name FechaCompra */
    public static final String COLUMNNAME_FechaCompra = "FechaCompra";

	/** Set Purchase Date.
	  * It contains the date of purchase of the asset
	  */
	public void setFechaCompra (String FechaCompra);

	/** Get Purchase Date.
	  * It contains the date of purchase of the asset
	  */
	public String getFechaCompra();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Asset_ID */
    public static final String COLUMNNAME_I_EXME_Asset_ID = "I_EXME_Asset_ID";

	/** Set Import Assets.
	  * Allows importing assets related to its administration
	  */
	public void setI_EXME_Asset_ID (int I_EXME_Asset_ID);

	/** Get Import Assets.
	  * Allows importing assets related to its administration
	  */
	public int getI_EXME_Asset_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name NumeroControl */
    public static final String COLUMNNAME_NumeroControl = "NumeroControl";

	/** Set Control Number.
	  * Contains the number of active contro
	  */
	public void setNumeroControl (String NumeroControl);

	/** Get Control Number.
	  * Contains the number of active contro
	  */
	public String getNumeroControl();

    /** Column name NumeroFactura */
    public static final String COLUMNNAME_NumeroFactura = "NumeroFactura";

	/** Set Invoice Number.
	  * Contains the number of the invoice, if you want to add, since it is optional.
	  */
	public void setNumeroFactura (String NumeroFactura);

	/** Get Invoice Number.
	  * Contains the number of the invoice, if you want to add, since it is optional.
	  */
	public String getNumeroFactura();

    /** Column name Precio */
    public static final String COLUMNNAME_Precio = "Precio";

	/** Set Price	  */
	public void setPrecio (String Precio);

	/** Get Price	  */
	public String getPrecio();

    /** Column name Procedencia */
    public static final String COLUMNNAME_Procedencia = "Procedencia";

	/** Set Provanance.
	  * Sets from which the active 
	  */
	public void setProcedencia (String Procedencia);

	/** Get Provanance.
	  * Sets from which the active 
	  */
	public String getProcedencia();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Recurso */
    public static final String COLUMNNAME_Recurso = "Recurso";

	/** Set Resource.
	  * Describe the type of resource
	  */
	public void setRecurso (boolean Recurso);

	/** Get Resource.
	  * Describe the type of resource
	  */
	public boolean isRecurso();

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
