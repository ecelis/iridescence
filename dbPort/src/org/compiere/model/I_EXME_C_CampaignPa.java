/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_C_CampaignPa
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_C_CampaignPa 
{

    /** TableName=EXME_C_CampaignPa */
    public static final String Table_Name = "EXME_C_CampaignPa";

    /** AD_Table_ID=1200412 */
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

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Program.
	  * Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Program.
	  * Program
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws RuntimeException;

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

    /** Column name EXME_C_CampaignPa_ID */
    public static final String COLUMNNAME_EXME_C_CampaignPa_ID = "EXME_C_CampaignPa_ID";

	/** Set Program Details.
	  * Program Details
	  */
	public void setEXME_C_CampaignPa_ID (int EXME_C_CampaignPa_ID);

	/** Get Program Details.
	  * Program Details
	  */
	public int getEXME_C_CampaignPa_ID();

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
}
