/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnfControlCuest
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnfControlCuest 
{

    /** TableName=EXME_EnfControlCuest */
    public static final String Table_Name = "EXME_EnfControlCuest";

    /** AD_Table_ID=1200514 */
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

    /** Column name EXME_EnfControlada_ID */
    public static final String COLUMNNAME_EXME_EnfControlada_ID = "EXME_EnfControlada_ID";

	/** Set Controlled Illness.
	  * Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID);

	/** Get Controlled Illness.
	  * Controlled Illness
	  */
	public int getEXME_EnfControlada_ID();

    /** Column name EXME_EnfControlCuest_ID */
    public static final String COLUMNNAME_EXME_EnfControlCuest_ID = "EXME_EnfControlCuest_ID";

	/** Set Questionary of Controlled Disease.
	  * Questionary of Controlled Disease
	  */
	public void setEXME_EnfControlCuest_ID (int EXME_EnfControlCuest_ID);

	/** Get Questionary of Controlled Disease.
	  * Questionary of Controlled Disease
	  */
	public int getEXME_EnfControlCuest_ID();

    /** Column name EXME_Formato_ID */
    public static final String COLUMNNAME_EXME_Formato_ID = "EXME_Formato_ID";

	/** Set Format	  */
	public void setEXME_Formato_ID (int EXME_Formato_ID);

	/** Get Format	  */
	public int getEXME_Formato_ID();

    /** Column name TipoCuestionario */
    public static final String COLUMNNAME_TipoCuestionario = "TipoCuestionario";

	/** Set Questionary Type	  */
	public void setTipoCuestionario (String TipoCuestionario);

	/** Get Questionary Type	  */
	public String getTipoCuestionario();
}
