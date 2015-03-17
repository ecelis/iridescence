/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Year
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Year 
{

    /** TableName=C_Year */
    public static final String Table_Name = "C_Year";

    /** AD_Table_ID=177 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_Calendar_ID */
    public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

	/** Set Calendar.
	  * Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID);

	/** Get Calendar.
	  * Accounting Calendar Name
	  */
	public int getC_Calendar_ID();

	public I_C_Calendar getC_Calendar() throws RuntimeException;

    /** Column name Cierre */
    public static final String COLUMNNAME_Cierre = "Cierre";

	/** Set ¿Cerrado?	  */
	public void setCierre (boolean Cierre);

	/** Get ¿Cerrado?	  */
	public boolean isCierre();

    /** Column name C_Year_ID */
    public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

	/** Set Year.
	  * Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID);

	/** Get Year.
	  * Calendar Year
	  */
	public int getC_Year_ID();

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

    /** Column name FiscalYear */
    public static final String COLUMNNAME_FiscalYear = "FiscalYear";

	/** Set Year.
	  * The Fiscal Year
	  */
	public void setFiscalYear (String FiscalYear);

	/** Get Year.
	  * The Fiscal Year
	  */
	public String getFiscalYear();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year.
	  * Calendar Year
	  */
	public void setYear (String Year);

	/** Get Year.
	  * Calendar Year
	  */
	public String getYear();
}
