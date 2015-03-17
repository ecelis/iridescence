/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacBeneficiarios
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacBeneficiarios 
{

    /** TableName=EXME_PacBeneficiarios */
    public static final String Table_Name = "EXME_PacBeneficiarios";

    /** AD_Table_ID=1201052 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_Beneficario_ID */
    public static final String COLUMNNAME_EXME_Beneficario_ID = "EXME_Beneficario_ID";

	/** Set Beneficiary	  */
	public void setEXME_Beneficario_ID (int EXME_Beneficario_ID);

	/** Get Beneficiary	  */
	public int getEXME_Beneficario_ID();

    /** Column name EXME_PacBeneficiarios_ID */
    public static final String COLUMNNAME_EXME_PacBeneficiarios_ID = "EXME_PacBeneficiarios_ID";

	/** Set Patient's Beneficiaries	  */
	public void setEXME_PacBeneficiarios_ID (int EXME_PacBeneficiarios_ID);

	/** Get Patient's Beneficiaries	  */
	public int getEXME_PacBeneficiarios_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;
}
