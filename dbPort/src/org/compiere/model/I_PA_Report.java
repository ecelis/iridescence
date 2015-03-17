/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_Report
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PA_Report 
{

    /** TableName=PA_Report */
    public static final String Table_Name = "PA_Report";

    /** AD_Table_ID=445 */
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

    /** Column name AD_PrintFormat_ID */
    public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";

	/** Set Print Format.
	  * Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID);

	/** Get Print Format.
	  * Data Print Format
	  */
	public int getAD_PrintFormat_ID();

	public I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException;

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

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

    /** Column name JasperProcess_ID */
    public static final String COLUMNNAME_JasperProcess_ID = "JasperProcess_ID";

	/** Set Jasper Process.
	  * The Jasper Process used by the printengine if any process defined
	  */
	public void setJasperProcess_ID (int JasperProcess_ID);

	/** Get Jasper Process.
	  * The Jasper Process used by the printengine if any process defined
	  */
	public int getJasperProcess_ID();

    /** Column name JasperProcessing */
    public static final String COLUMNNAME_JasperProcessing = "JasperProcessing";

	/** Set Jasper Process Now	  */
	public void setJasperProcessing (String JasperProcessing);

	/** Get Jasper Process Now	  */
	public String getJasperProcessing();

    /** Column name ListSources */
    public static final String COLUMNNAME_ListSources = "ListSources";

	/** Set List Sources.
	  * List Report Line Sources
	  */
	public void setListSources (boolean ListSources);

	/** Get List Sources.
	  * List Report Line Sources
	  */
	public boolean isListSources();

    /** Column name ListTrx */
    public static final String COLUMNNAME_ListTrx = "ListTrx";

	/** Set List Transactions.
	  * List the report transactions
	  */
	public void setListTrx (boolean ListTrx);

	/** Get List Transactions.
	  * List the report transactions
	  */
	public boolean isListTrx();

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

    /** Column name PA_ReportColumnSet_ID */
    public static final String COLUMNNAME_PA_ReportColumnSet_ID = "PA_ReportColumnSet_ID";

	/** Set Report Column Set.
	  * Collection of Columns for Report
	  */
	public void setPA_ReportColumnSet_ID (int PA_ReportColumnSet_ID);

	/** Get Report Column Set.
	  * Collection of Columns for Report
	  */
	public int getPA_ReportColumnSet_ID();

	public I_PA_ReportColumnSet getPA_ReportColumnSet() throws RuntimeException;

    /** Column name PA_ReportCube_ID */
    public static final String COLUMNNAME_PA_ReportCube_ID = "PA_ReportCube_ID";

	/** Set Report Cube.
	  * Define reporting cube for pre-calculation of summary accounting data.
	  */
	public void setPA_ReportCube_ID (int PA_ReportCube_ID);

	/** Get Report Cube.
	  * Define reporting cube for pre-calculation of summary accounting data.
	  */
	public int getPA_ReportCube_ID();

    /** Column name PA_Report_ID */
    public static final String COLUMNNAME_PA_Report_ID = "PA_Report_ID";

	/** Set Financial Report.
	  * Financial Report
	  */
	public void setPA_Report_ID (int PA_Report_ID);

	/** Get Financial Report.
	  * Financial Report
	  */
	public int getPA_Report_ID();

    /** Column name PA_ReportLineSet_ID */
    public static final String COLUMNNAME_PA_ReportLineSet_ID = "PA_ReportLineSet_ID";

	/** Set Report Line Set	  */
	public void setPA_ReportLineSet_ID (int PA_ReportLineSet_ID);

	/** Get Report Line Set	  */
	public int getPA_ReportLineSet_ID();

	public I_PA_ReportLineSet getPA_ReportLineSet() throws RuntimeException;

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
