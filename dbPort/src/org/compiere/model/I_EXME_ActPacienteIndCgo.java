/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteIndCgo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPacienteIndCgo 
{

    /** TableName=EXME_ActPacienteIndCgo */
    public static final String Table_Name = "EXME_ActPacienteIndCgo";

    /** AD_Table_ID=1201300 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name Classname */
    public static final String COLUMNNAME_Classname = "Classname";

	/** Set Classname.
	  * Java Classname
	  */
	public void setClassname (String Classname);

	/** Get Classname.
	  * Java Classname
	  */
	public String getClassname();

    /** Column name ErrorMsg */
    public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";

	/** Set Error Msg	  */
	public void setErrorMsg (String ErrorMsg);

	/** Get Error Msg	  */
	public String getErrorMsg();

    /** Column name EXME_ActPacienteIndCgo_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndCgo_ID = "EXME_ActPacienteIndCgo_ID";

	/** Set Indication's detail (charges)	  */
	public void setEXME_ActPacienteIndCgo_ID (int EXME_ActPacienteIndCgo_ID);

	/** Get Indication's detail (charges)	  */
	public int getEXME_ActPacienteIndCgo_ID();

    /** Column name EXME_ActPacienteIndH_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndH_ID = "EXME_ActPacienteIndH_ID";

	/** Set Patient's Indication.
	  * Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID);

	/** Get Patient's Indication.
	  * Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID();

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException;

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indication's detail.
	  * Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indication's detail.
	  * Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name EXME_PlanMed_ID */
    public static final String COLUMNNAME_EXME_PlanMed_ID = "EXME_PlanMed_ID";

	/** Set Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID);

	/** Get Medical Plan	  */
	public int getEXME_PlanMed_ID();

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException;

    /** Column name EXME_PlanMedLine_ID */
    public static final String COLUMNNAME_EXME_PlanMedLine_ID = "EXME_PlanMedLine_ID";

	/** Set Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID);

	/** Get Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID();

	public I_EXME_PlanMedLine getEXME_PlanMedLine() throws RuntimeException;

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();
}
