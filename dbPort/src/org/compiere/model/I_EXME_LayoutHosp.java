/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LayoutHosp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LayoutHosp 
{

    /** TableName=EXME_LayoutHosp */
    public static final String Table_Name = "EXME_LayoutHosp";

    /** AD_Table_ID=1200199 */
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

    /** Column name Anio */
    public static final String COLUMNNAME_Anio = "Anio";

	/** Set Year.
	  * Year
	  */
	public void setAnio (int Anio);

	/** Get Year.
	  * Year
	  */
	public int getAnio();

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

    /** Column name EXME_LayoutHosp_ID */
    public static final String COLUMNNAME_EXME_LayoutHosp_ID = "EXME_LayoutHosp_ID";

	/** Set Inpatient Layout.
	  * Inpatient Layout
	  */
	public void setEXME_LayoutHosp_ID (int EXME_LayoutHosp_ID);

	/** Get Inpatient Layout.
	  * Inpatient Layout
	  */
	public int getEXME_LayoutHosp_ID();

    /** Column name No_Layout */
    public static final String COLUMNNAME_No_Layout = "No_Layout";

	/** Set Layout Number.
	  * Layout Number
	  */
	public void setNo_Layout (int No_Layout);

	/** Get Layout Number.
	  * Layout Number
	  */
	public int getNo_Layout();

    /** Column name Periodo */
    public static final String COLUMNNAME_Periodo = "Periodo";

	/** Set Period.
	  * Period
	  */
	public void setPeriodo (int Periodo);

	/** Get Period.
	  * Period
	  */
	public int getPeriodo();
}
