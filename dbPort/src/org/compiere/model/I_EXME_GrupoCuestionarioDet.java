/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GrupoCuestionarioDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_GrupoCuestionarioDet 
{

    /** TableName=EXME_GrupoCuestionarioDet */
    public static final String Table_Name = "EXME_GrupoCuestionarioDet";

    /** AD_Table_ID=1201235 */
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

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException;

    /** Column name EXME_GrupoCuestionarioDet_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionarioDet_ID = "EXME_GrupoCuestionarioDet_ID";

	/** Set EXME_GrupoCuestionarioDet_ID.
	  * Form Group Detail
	  */
	public void setEXME_GrupoCuestionarioDet_ID (int EXME_GrupoCuestionarioDet_ID);

	/** Get EXME_GrupoCuestionarioDet_ID.
	  * Form Group Detail
	  */
	public int getEXME_GrupoCuestionarioDet_ID();

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set EXME_GrupoCuestionario_ID.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get EXME_GrupoCuestionario_ID.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException;

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
