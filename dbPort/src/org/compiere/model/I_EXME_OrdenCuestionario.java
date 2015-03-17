/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_OrdenCuestionario
 *  @author Generated Class 
 *  @version Release @VERSION@
 */
public interface I_EXME_OrdenCuestionario 
{

    /** TableName=EXME_OrdenCuestionario */
    public static final String Table_Name = "EXME_OrdenCuestionario";

    /** AD_Table_ID=1201230 */
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

    /** Column name EXME_CuestionarioDt_ID */
    public static final String COLUMNNAME_EXME_CuestionarioDt_ID = "EXME_CuestionarioDt_ID";

	/** Set DT Questinnaire.
	  * DT Questionnaire
	  */
	public void setEXME_CuestionarioDt_ID (int EXME_CuestionarioDt_ID);

	/** Get DT Questinnaire.
	  * DT Questionnaire
	  */
	public int getEXME_CuestionarioDt_ID();

	public I_EXME_CuestionarioDt getEXME_CuestionarioDt() throws RuntimeException;

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

    /** Column name EXME_OrdenCuestionario_ID */
    public static final String COLUMNNAME_EXME_OrdenCuestionario_ID = "EXME_OrdenCuestionario_ID";

	/** Set Order	  */
	public void setEXME_OrdenCuestionario_ID (int EXME_OrdenCuestionario_ID);

	/** Get Order	  */
	public int getEXME_OrdenCuestionario_ID();

    /** Column name EXME_TipoPregunta_ID */
    public static final String COLUMNNAME_EXME_TipoPregunta_ID = "EXME_TipoPregunta_ID";

	/** Set Type of Question.
	  * Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID);

	/** Get Type of Question.
	  * Type of Question
	  */
	public int getEXME_TipoPregunta_ID();

	public I_EXME_TipoPregunta getEXME_TipoPregunta() throws RuntimeException;

    /** Column name IsSelected */
    public static final String COLUMNNAME_IsSelected = "IsSelected";

	/** Set Selected	  */
	public void setIsSelected (boolean IsSelected);

	/** Get Selected	  */
	public boolean isSelected();

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
