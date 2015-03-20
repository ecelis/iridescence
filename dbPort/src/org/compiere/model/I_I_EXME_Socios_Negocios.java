/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Socios_Negocios
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Socios_Negocios 
{

    /** TableName=I_EXME_Socios_Negocios */
    public static final String Table_Name = "I_EXME_Socios_Negocios";

    /** AD_Table_ID=1200229 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Ciudad */
    public static final String COLUMNNAME_Ciudad = "Ciudad";

	/** Set City.
	  * description of a city
	  */
	public void setCiudad (String Ciudad);

	/** Get City.
	  * description of a city
	  */
	public String getCiudad();

    /** Column name Cliente_A_Cargo */
    public static final String COLUMNNAME_Cliente_A_Cargo = "Cliente_A_Cargo";

	/** Set Customer at Charge	  */
	public void setCliente_A_Cargo (String Cliente_A_Cargo);

	/** Get Customer at Charge	  */
	public String getCliente_A_Cargo();

    /** Column name Codigo_Impuestos */
    public static final String COLUMNNAME_Codigo_Impuestos = "Codigo_Impuestos";

	/** Set Codigo_Impuestos	  */
	public void setCodigo_Impuestos (String Codigo_Impuestos);

	/** Get Codigo_Impuestos	  */
	public String getCodigo_Impuestos();

    /** Column name Colonia */
    public static final String COLUMNNAME_Colonia = "Colonia";

	/** Set Suburb / District.
	  * Suburb / District
	  */
	public void setColonia (String Colonia);

	/** Get Suburb / District.
	  * Suburb / District
	  */
	public String getColonia();

    /** Column name Delegacion */
    public static final String COLUMNNAME_Delegacion = "Delegacion";

	/** Set Delegacion	  */
	public void setDelegacion (String Delegacion);

	/** Get Delegacion	  */
	public String getDelegacion();

    /** Column name Estado */
    public static final String COLUMNNAME_Estado = "Estado";

	/** Set State	  */
	public void setEstado (String Estado);

	/** Get State	  */
	public String getEstado();

    /** Column name Estado_Activo */
    public static final String COLUMNNAME_Estado_Activo = "Estado_Activo";

	/** Set Estado_Activo	  */
	public void setEstado_Activo (String Estado_Activo);

	/** Get Estado_Activo	  */
	public String getEstado_Activo();

    /** Column name Identificador */
    public static final String COLUMNNAME_Identificador = "Identificador";

	/** Set Identifier	  */
	public void setIdentificador (String Identificador);

	/** Get Identifier	  */
	public String getIdentificador();

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

    /** Column name I_EXME_Socios_Negocios_ID */
    public static final String COLUMNNAME_I_EXME_Socios_Negocios_ID = "I_EXME_Socios_Negocios_ID";

	/** Set I_EXME_Socios_Negocios_ID	  */
	public void setI_EXME_Socios_Negocios_ID (int I_EXME_Socios_Negocios_ID);

	/** Get I_EXME_Socios_Negocios_ID	  */
	public int getI_EXME_Socios_Negocios_ID();

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

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

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

    /** Column name Referencia_Cliente */
    public static final String COLUMNNAME_Referencia_Cliente = "Referencia_Cliente";

	/** Set Customer Reference	  */
	public void setReferencia_Cliente (String Referencia_Cliente);

	/** Get Customer Reference	  */
	public String getReferencia_Cliente();

    /** Column name Referencia_Direccion */
    public static final String COLUMNNAME_Referencia_Direccion = "Referencia_Direccion";

	/** Set Reference Address	  */
	public void setReferencia_Direccion (String Referencia_Direccion);

	/** Get Reference Address	  */
	public String getReferencia_Direccion();

    /** Column name Rfc */
    public static final String COLUMNNAME_Rfc = "Rfc";

	/** Set Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public void setRfc (String Rfc);

	/** Get Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public String getRfc();

    /** Column name Termino_Convenio */
    public static final String COLUMNNAME_Termino_Convenio = "Termino_Convenio";

	/** Set Termino_Convenio	  */
	public void setTermino_Convenio (String Termino_Convenio);

	/** Get Termino_Convenio	  */
	public String getTermino_Convenio();

    /** Column name TipoCliente */
    public static final String COLUMNNAME_TipoCliente = "TipoCliente";

	/** Set Client Type	  */
	public void setTipoCliente (String TipoCliente);

	/** Get Client Type	  */
	public String getTipoCliente();

    /** Column name Unidad_Operativa */
    public static final String COLUMNNAME_Unidad_Operativa = "Unidad_Operativa";

	/** Set Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa);

	/** Get Operational Unit	  */
	public String getUnidad_Operativa();

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
