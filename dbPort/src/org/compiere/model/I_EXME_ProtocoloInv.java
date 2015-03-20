/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProtocoloInv
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProtocoloInv 
{

    /** TableName=EXME_ProtocoloInv */
    public static final String Table_Name = "EXME_ProtocoloInv";

    /** AD_Table_ID=1200334 */
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

    /** Column name Criterio */
    public static final String COLUMNNAME_Criterio = "Criterio";

	/** Set Selection Criterion	  */
	public void setCriterio (String Criterio);

	/** Get Selection Criterion	  */
	public String getCriterio();

    /** Column name DateApproved */
    public static final String COLUMNNAME_DateApproved = "DateApproved";

	/** Set Date Approved	  */
	public void setDateApproved (Timestamp DateApproved);

	/** Get Date Approved	  */
	public Timestamp getDateApproved();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

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

    /** Column name EXME_Investigador_ID */
    public static final String COLUMNNAME_EXME_Investigador_ID = "EXME_Investigador_ID";

	/** Set Researcher.
	  * Researcher
	  */
	public void setEXME_Investigador_ID (int EXME_Investigador_ID);

	/** Get Researcher.
	  * Researcher
	  */
	public int getEXME_Investigador_ID();

	public I_EXME_Investigador getEXME_Investigador() throws RuntimeException;

    /** Column name EXME_ProtocoloInv_ID */
    public static final String COLUMNNAME_EXME_ProtocoloInv_ID = "EXME_ProtocoloInv_ID";

	/** Set Researching Protocol.
	  * Researching Protocol
	  */
	public void setEXME_ProtocoloInv_ID (int EXME_ProtocoloInv_ID);

	/** Get Researching Protocol.
	  * Researching Protocol
	  */
	public int getEXME_ProtocoloInv_ID();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsCreated */
    public static final String COLUMNNAME_IsCreated = "IsCreated";

	/** Set Records created	  */
	public void setIsCreated (boolean IsCreated);

	/** Get Records created	  */
	public boolean isCreated();

    /** Column name IsVerified */
    public static final String COLUMNNAME_IsVerified = "IsVerified";

	/** Set Verified.
	  * The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified);

	/** Get Verified.
	  * The BOM configuration has been verified
	  */
	public boolean isVerified();

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

    /** Column name Objetivo */
    public static final String COLUMNNAME_Objetivo = "Objetivo";

	/** Set Objetive	  */
	public void setObjetivo (String Objetivo);

	/** Get Objetive	  */
	public String getObjetivo();

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

    /** Column name Vigencia_Fin */
    public static final String COLUMNNAME_Vigencia_Fin = "Vigencia_Fin";

	/** Set Validity final date.
	  * Validity final date
	  */
	public void setVigencia_Fin (Timestamp Vigencia_Fin);

	/** Get Validity final date.
	  * Validity final date
	  */
	public Timestamp getVigencia_Fin();

    /** Column name Vigencia_Ini */
    public static final String COLUMNNAME_Vigencia_Ini = "Vigencia_Ini";

	/** Set Validity initial date.
	  * Validity initial date
	  */
	public void setVigencia_Ini (Timestamp Vigencia_Ini);

	/** Get Validity initial date.
	  * Validity initial date
	  */
	public Timestamp getVigencia_Ini();
}
