/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstServResp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EstServResp 
{

    /** TableName=EXME_EstServResp */
    public static final String Table_Name = "EXME_EstServResp";

    /** AD_Table_ID=1200467 */
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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EXME_Emp_ID */
    public static final String COLUMNNAME_EXME_Emp_ID = "EXME_Emp_ID";

	/** Set Employee.
	  * Employee
	  */
	public void setEXME_Emp_ID (int EXME_Emp_ID);

	/** Get Employee.
	  * Employee
	  */
	public int getEXME_Emp_ID();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

    /** Column name EXME_EstServResp_ID */
    public static final String COLUMNNAME_EXME_EstServResp_ID = "EXME_EstServResp_ID";

	/** Set Responsible Station.
	  * Contains responsible for service station
	  */
	public void setEXME_EstServResp_ID (int EXME_EstServResp_ID);

	/** Get Responsible Station.
	  * Contains responsible for service station
	  */
	public int getEXME_EstServResp_ID();

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name IsTransfered */
    public static final String COLUMNNAME_IsTransfered = "IsTransfered";

	/** Set Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public void setIsTransfered (String IsTransfered);

	/** Get Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public String getIsTransfered();
}
