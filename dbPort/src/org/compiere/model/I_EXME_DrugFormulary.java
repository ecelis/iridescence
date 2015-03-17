/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DrugFormulary
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DrugFormulary 
{

    /** TableName=EXME_DrugFormulary */
    public static final String Table_Name = "EXME_DrugFormulary";

    /** AD_Table_ID=1201367 */
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

    /** Column name EXME_DrugFormulary_ID */
    public static final String COLUMNNAME_EXME_DrugFormulary_ID = "EXME_DrugFormulary_ID";

	/** Set EXME_DrugFormulary_ID	  */
	public void setEXME_DrugFormulary_ID (int EXME_DrugFormulary_ID);

	/** Get EXME_DrugFormulary_ID	  */
	public int getEXME_DrugFormulary_ID();

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

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
}
