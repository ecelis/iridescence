/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FechaFact
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FechaFact 
{

    /** TableName=EXME_FechaFact */
    public static final String Table_Name = "EXME_FechaFact";

    /** AD_Table_ID=1201277 */
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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name CtaPacDescription */
    public static final String COLUMNNAME_CtaPacDescription = "CtaPacDescription";

	/** Set Encounter Description.
	  * Encounter Description
	  */
	public void setCtaPacDescription (String CtaPacDescription);

	/** Get Encounter Description.
	  * Encounter Description
	  */
	public String getCtaPacDescription();

    /** Column name EstatusCtaPac */
    public static final String COLUMNNAME_EstatusCtaPac = "EstatusCtaPac";

	/** Set Encounter Estatus.
	  * Encounter Estatus
	  */
	public void setEstatusCtaPac (String EstatusCtaPac);

	/** Get Encounter Estatus.
	  * Encounter Estatus
	  */
	public String getEstatusCtaPac();

    /** Column name EstatusDescription */
    public static final String COLUMNNAME_EstatusDescription = "EstatusDescription";

	/** Set Description Status.
	  * Description Status
	  */
	public void setEstatusDescription (String EstatusDescription);

	/** Get Description Status.
	  * Description Status
	  */
	public String getEstatusDescription();

    /** Column name EstatusFact */
    public static final String COLUMNNAME_EstatusFact = "EstatusFact";

	/** Set Invoice Status.
	  * Invoice Status
	  */
	public void setEstatusFact (String EstatusFact);

	/** Get Invoice Status.
	  * Invoice Status
	  */
	public String getEstatusFact();

    /** Column name EXME_FechaFact_ID */
    public static final String COLUMNNAME_EXME_FechaFact_ID = "EXME_FechaFact_ID";

	/** Set Date Invoice.
	  * Date Invoice
	  */
	public void setEXME_FechaFact_ID (int EXME_FechaFact_ID);

	/** Get Date Invoice.
	  * Date Invoice
	  */
	public int getEXME_FechaFact_ID();

    /** Column name FechaAprob */
    public static final String COLUMNNAME_FechaAprob = "FechaAprob";

	/** Set Date Approved.
	  * Date Approved
	  */
	public void setFechaAprob (Timestamp FechaAprob);

	/** Get Date Approved.
	  * Date Approved
	  */
	public Timestamp getFechaAprob();

    /** Column name FechaCtaPac */
    public static final String COLUMNNAME_FechaCtaPac = "FechaCtaPac";

	/** Set Date Encounter.
	  * Date Encounter
	  */
	public void setFechaCtaPac (Timestamp FechaCtaPac);

	/** Get Date Encounter.
	  * Date Encounter
	  */
	public Timestamp getFechaCtaPac();

    /** Column name FechaEnv */
    public static final String COLUMNNAME_FechaEnv = "FechaEnv";

	/** Set Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv);

	/** Get Sending Date	  */
	public Timestamp getFechaEnv();

    /** Column name FechaEstatus */
    public static final String COLUMNNAME_FechaEstatus = "FechaEstatus";

	/** Set Status Date.
	  * Status Date
	  */
	public void setFechaEstatus (Timestamp FechaEstatus);

	/** Get Status Date.
	  * Status Date
	  */
	public Timestamp getFechaEstatus();

    /** Column name FechaVencimiento */
    public static final String COLUMNNAME_FechaVencimiento = "FechaVencimiento";

	/** Set Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento);

	/** Get Termination Date	  */
	public Timestamp getFechaVencimiento();
}
