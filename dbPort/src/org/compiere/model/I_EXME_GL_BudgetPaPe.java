/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GL_BudgetPaPe
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GL_BudgetPaPe 
{

    /** TableName=EXME_GL_BudgetPaPe */
    public static final String Table_Name = "EXME_GL_BudgetPaPe";

    /** AD_Table_ID=1200411 */
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

    /** Column name EXME_GL_BudgetPa_ID */
    public static final String COLUMNNAME_EXME_GL_BudgetPa_ID = "EXME_GL_BudgetPa_ID";

	/** Set Budget Items.
	  * Budget Items
	  */
	public void setEXME_GL_BudgetPa_ID (int EXME_GL_BudgetPa_ID);

	/** Get Budget Items.
	  * Budget Items
	  */
	public int getEXME_GL_BudgetPa_ID();

	public I_EXME_GL_BudgetPa getEXME_GL_BudgetPa() throws RuntimeException;

    /** Column name EXME_GL_BudgetPaPe_ID */
    public static final String COLUMNNAME_EXME_GL_BudgetPaPe_ID = "EXME_GL_BudgetPaPe_ID";

	/** Set Detailed budget items.
	  * Detailed budget items
	  */
	public void setEXME_GL_BudgetPaPe_ID (int EXME_GL_BudgetPaPe_ID);

	/** Get Detailed budget items.
	  * Detailed budget items
	  */
	public int getEXME_GL_BudgetPaPe_ID();

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
