/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LeyendaCte
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LeyendaCte 
{

    /** TableName=EXME_LeyendaCte */
    public static final String Table_Name = "EXME_LeyendaCte";

    /** AD_Table_ID=1200131 */
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

    /** Column name Agregar_Fechas */
    public static final String COLUMNNAME_Agregar_Fechas = "Agregar_Fechas";

	/** Set Add Dates.
	  * Add Dates
	  */
	public void setAgregar_Fechas (boolean Agregar_Fechas);

	/** Get Add Dates.
	  * Add Dates
	  */
	public boolean isAgregar_Fechas();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name EXME_LeyendaCte_ID */
    public static final String COLUMNNAME_EXME_LeyendaCte_ID = "EXME_LeyendaCte_ID";

	/** Set Client Special Legend.
	  * Client Special Legend
	  */
	public void setEXME_LeyendaCte_ID (int EXME_LeyendaCte_ID);

	/** Get Client Special Legend.
	  * Client Special Legend
	  */
	public int getEXME_LeyendaCte_ID();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();
}
