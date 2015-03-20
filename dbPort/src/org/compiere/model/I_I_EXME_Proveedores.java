/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Proveedores
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Proveedores 
{

    /** TableName=I_EXME_Proveedores */
    public static final String Table_Name = "I_EXME_Proveedores";

    /** AD_Table_ID=1200230 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

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

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Codigo_Proveedor */
    public static final String COLUMNNAME_Codigo_Proveedor = "Codigo_Proveedor";

	/** Set Provider Code	  */
	public void setCodigo_Proveedor (String Codigo_Proveedor);

	/** Get Provider Code	  */
	public String getCodigo_Proveedor();

    /** Column name Estado */
    public static final String COLUMNNAME_Estado = "Estado";

	/** Set State	  */
	public void setEstado (String Estado);

	/** Get State	  */
	public String getEstado();

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

    /** Column name I_EXME_Proveedores_ID */
    public static final String COLUMNNAME_I_EXME_Proveedores_ID = "I_EXME_Proveedores_ID";

	/** Set I_EXME_Proveedores_ID	  */
	public void setI_EXME_Proveedores_ID (int I_EXME_Proveedores_ID);

	/** Get I_EXME_Proveedores_ID	  */
	public int getI_EXME_Proveedores_ID();

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

    /** Column name Moneda */
    public static final String COLUMNNAME_Moneda = "Moneda";

	/** Set Coin	  */
	public void setMoneda (String Moneda);

	/** Get Coin	  */
	public String getMoneda();

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

    /** Column name Pais */
    public static final String COLUMNNAME_Pais = "Pais";

	/** Set Pais	  */
	public void setPais (String Pais);

	/** Get Pais	  */
	public String getPais();

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

    /** Column name Sucursal */
    public static final String COLUMNNAME_Sucursal = "Sucursal";

	/** Set Branch.
	  * Branch
	  */
	public void setSucursal (String Sucursal);

	/** Get Branch.
	  * Branch
	  */
	public String getSucursal();

    /** Column name Termino_Pago */
    public static final String COLUMNNAME_Termino_Pago = "Termino_Pago";

	/** Set Payment Terms	  */
	public void setTermino_Pago (String Termino_Pago);

	/** Get Payment Terms	  */
	public String getTermino_Pago();

    /** Column name Unidad_Operativa */
    public static final String COLUMNNAME_Unidad_Operativa = "Unidad_Operativa";

	/** Set Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa);

	/** Get Operational Unit	  */
	public String getUnidad_Operativa();
}
