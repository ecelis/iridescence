/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProcedPacDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProcedPacDet 
{

    /** TableName=EXME_ProcedPacDet */
    public static final String Table_Name = "EXME_ProcedPacDet";

    /** AD_Table_ID=1200611 */
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

    /** Column name EXME_PlanMedicoAlt_ID */
    public static final String COLUMNNAME_EXME_PlanMedicoAlt_ID = "EXME_PlanMedicoAlt_ID";

	/** Set Alternative procedure as the patient	  */
	public void setEXME_PlanMedicoAlt_ID (int EXME_PlanMedicoAlt_ID);

	/** Get Alternative procedure as the patient	  */
	public int getEXME_PlanMedicoAlt_ID();

    /** Column name EXME_PlanMedico_ID */
    public static final String COLUMNNAME_EXME_PlanMedico_ID = "EXME_PlanMedico_ID";

	/** Set Health Plan	  */
	public void setEXME_PlanMedico_ID (int EXME_PlanMedico_ID);

	/** Get Health Plan	  */
	public int getEXME_PlanMedico_ID();

	public I_EXME_PlanMedico getEXME_PlanMedico() throws RuntimeException;

    /** Column name EXME_ProcedPacDet_ID */
    public static final String COLUMNNAME_EXME_ProcedPacDet_ID = "EXME_ProcedPacDet_ID";

	/** Set Details of the procedure as the patient	  */
	public void setEXME_ProcedPacDet_ID (int EXME_ProcedPacDet_ID);

	/** Get Details of the procedure as the patient	  */
	public int getEXME_ProcedPacDet_ID();

    /** Column name EXME_ProcedPac_ID */
    public static final String COLUMNNAME_EXME_ProcedPac_ID = "EXME_ProcedPac_ID";

	/** Set Procedures applied to the patient	  */
	public void setEXME_ProcedPac_ID (int EXME_ProcedPac_ID);

	/** Get Procedures applied to the patient	  */
	public int getEXME_ProcedPac_ID();

	public I_EXME_ProcedPac getEXME_ProcedPac() throws RuntimeException;

    /** Column name EXME_ResultadoMedico_ID */
    public static final String COLUMNNAME_EXME_ResultadoMedico_ID = "EXME_ResultadoMedico_ID";

	/** Set Medical Staff	  */
	public void setEXME_ResultadoMedico_ID (int EXME_ResultadoMedico_ID);

	/** Get Medical Staff	  */
	public int getEXME_ResultadoMedico_ID();

	public I_EXME_ResultadoMedico getEXME_ResultadoMedico() throws RuntimeException;

    /** Column name EXME_RiesgoMedico_ID */
    public static final String COLUMNNAME_EXME_RiesgoMedico_ID = "EXME_RiesgoMedico_ID";

	/** Set Medical risk	  */
	public void setEXME_RiesgoMedico_ID (int EXME_RiesgoMedico_ID);

	/** Get Medical risk	  */
	public int getEXME_RiesgoMedico_ID();

	public I_EXME_RiesgoMedico getEXME_RiesgoMedico() throws RuntimeException;

    /** Column name TipoRegistro */
    public static final String COLUMNNAME_TipoRegistro = "TipoRegistro";

	/** Set Record Type	  */
	public void setTipoRegistro (String TipoRegistro);

	/** Get Record Type	  */
	public String getTipoRegistro();

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
}
