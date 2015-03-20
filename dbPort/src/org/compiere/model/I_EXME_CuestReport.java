/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CuestReport
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CuestReport 
{

    /** TableName=EXME_CuestReport */
    public static final String Table_Name = "EXME_CuestReport";

    /** AD_Table_ID=1201289 */
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

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

    /** Column name EXME_Cuest_Report_ID */
    public static final String COLUMNNAME_EXME_Cuest_Report_ID = "EXME_Cuest_Report_ID";

	/** Set Report Questionnaire	  */
	public void setEXME_Cuest_Report_ID (int EXME_Cuest_Report_ID);

	/** Get Report Questionnaire	  */
	public int getEXME_Cuest_Report_ID();

    /** Column name Report */
    public static final String COLUMNNAME_Report = "Report";

	/** Set Report	  */
	public void setReport (String Report);

	/** Get Report	  */
	public String getReport();
}
