/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Budget
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Budget 
{

    /** TableName=I_EXME_Budget */
    public static final String Table_Name = "I_EXME_Budget";

    /** AD_Table_ID=1200832 */
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

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_Budget_ID */
    public static final String COLUMNNAME_I_EXME_Budget_ID = "I_EXME_Budget_ID";

	/** Set Import budget.
	  * Allows the importation of Budgets
	  */
	public void setI_EXME_Budget_ID (int I_EXME_Budget_ID);

	/** Get Import budget.
	  * Allows the importation of Budgets
	  */
	public int getI_EXME_Budget_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Organizacion */
    public static final String COLUMNNAME_Organizacion = "Organizacion";

	/** Set Organization.
	  * Specify the Organization
	  */
	public void setOrganizacion (String Organizacion);

	/** Get Organization.
	  * Specify the Organization
	  */
	public String getOrganizacion();

    /** Column name Partida */
    public static final String COLUMNNAME_Partida = "Partida";

	/** Set Item.
	  * Specify the budget item
	  */
	public void setPartida (String Partida);

	/** Get Item.
	  * Specify the budget item
	  */
	public String getPartida();

    /** Column name Periodo */
    public static final String COLUMNNAME_Periodo = "Periodo";

	/** Set Period.
	  * Period
	  */
	public void setPeriodo (String Periodo);

	/** Get Period.
	  * Period
	  */
	public String getPeriodo();

    /** Column name Pre_Autorizado */
    public static final String COLUMNNAME_Pre_Autorizado = "Pre_Autorizado";

	/** Set Authorized .
	  * The amount authorized for the budget
	  */
	public void setPre_Autorizado (String Pre_Autorizado);

	/** Get Authorized .
	  * The amount authorized for the budget
	  */
	public String getPre_Autorizado();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
