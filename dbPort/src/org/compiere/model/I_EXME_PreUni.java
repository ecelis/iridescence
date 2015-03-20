/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PreUni
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PreUni 
{

    /** TableName=EXME_PreUni */
    public static final String Table_Name = "EXME_PreUni";

    /** AD_Table_ID=1200408 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name BudgetStatus */
    public static final String COLUMNNAME_BudgetStatus = "BudgetStatus";

	/** Set Budget Status.
	  * Indicates the current status of this budget
	  */
	public void setBudgetStatus (String BudgetStatus);

	/** Get Budget Status.
	  * Indicates the current status of this budget
	  */
	public String getBudgetStatus();

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

    /** Column name Ejercicio */
    public static final String COLUMNNAME_Ejercicio = "Ejercicio";

	/** Set Year.
	  * Year
	  */
	public void setEjercicio (BigDecimal Ejercicio);

	/** Get Year.
	  * Year
	  */
	public BigDecimal getEjercicio();

    /** Column name EXME_Partida_ID */
    public static final String COLUMNNAME_EXME_Partida_ID = "EXME_Partida_ID";

	/** Set Budget Item.
	  * Budget Item
	  */
	public void setEXME_Partida_ID (int EXME_Partida_ID);

	/** Get Budget Item.
	  * Budget Item
	  */
	public int getEXME_Partida_ID();

	public I_EXME_Partida getEXME_Partida() throws RuntimeException;

    /** Column name EXME_PreUni_ID */
    public static final String COLUMNNAME_EXME_PreUni_ID = "EXME_PreUni_ID";

	/** Set Budget unit.
	  * Is the identifier of each budget request by administrative unit
	  */
	public void setEXME_PreUni_ID (int EXME_PreUni_ID);

	/** Get Budget unit.
	  * Is the identifier of each budget request by administrative unit
	  */
	public int getEXME_PreUni_ID();

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

    /** Column name IsDistributed */
    public static final String COLUMNNAME_IsDistributed = "IsDistributed";

	/** Set Is Distributed.
	  * Describes whether the item is distributed proportionally 
	  */
	public void setIsDistributed (boolean IsDistributed);

	/** Get Is Distributed.
	  * Describes whether the item is distributed proportionally 
	  */
	public boolean isDistributed();

    /** Column name IsTransfered */
    public static final String COLUMNNAME_IsTransfered = "IsTransfered";

	/** Set Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public void setIsTransfered (boolean IsTransfered);

	/** Get Is Transfered.
	  * Sets whether this transfer registration 
	  */
	public boolean isTransfered();

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

    /** Column name Pre_Autorizado */
    public static final String COLUMNNAME_Pre_Autorizado = "Pre_Autorizado";

	/** Set Authorized .
	  * The amount authorized for the budget
	  */
	public void setPre_Autorizado (BigDecimal Pre_Autorizado);

	/** Get Authorized .
	  * The amount authorized for the budget
	  */
	public BigDecimal getPre_Autorizado();

    /** Column name Pre_Comprometido */
    public static final String COLUMNNAME_Pre_Comprometido = "Pre_Comprometido";

	/** Set Committed.
	  * Committed is the amount of the budget 
	  */
	public void setPre_Comprometido (BigDecimal Pre_Comprometido);

	/** Get Committed.
	  * Committed is the amount of the budget 
	  */
	public BigDecimal getPre_Comprometido();

    /** Column name Pre_Devengado */
    public static final String COLUMNNAME_Pre_Devengado = "Pre_Devengado";

	/** Set Earned.
	  * The amount of budget earned
	  */
	public void setPre_Devengado (BigDecimal Pre_Devengado);

	/** Get Earned.
	  * The amount of budget earned
	  */
	public BigDecimal getPre_Devengado();

    /** Column name Pre_Ejercido */
    public static final String COLUMNNAME_Pre_Ejercido = "Pre_Ejercido";

	/** Set Exercised.
	  * The amount of the budget Exercised
	  */
	public void setPre_Ejercido (BigDecimal Pre_Ejercido);

	/** Get Exercised.
	  * The amount of the budget Exercised
	  */
	public BigDecimal getPre_Ejercido();

    /** Column name Pre_Solicitado */
    public static final String COLUMNNAME_Pre_Solicitado = "Pre_Solicitado";

	/** Set Requested.
	  * Requested is the amount of the budget
	  */
	public void setPre_Solicitado (BigDecimal Pre_Solicitado);

	/** Get Requested.
	  * Requested is the amount of the budget
	  */
	public BigDecimal getPre_Solicitado();

    /** Column name TipoPresup */
    public static final String COLUMNNAME_TipoPresup = "TipoPresup";

	/** Set Budget Type.
	  * Budget Type
	  */
	public void setTipoPresup (String TipoPresup);

	/** Get Budget Type.
	  * Budget Type
	  */
	public String getTipoPresup();
}
