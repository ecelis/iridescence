/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_MedicoPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_MedicoPac 
{

    /** TableName=PHR_MedicoPac */
    public static final String Table_Name = "PHR_MedicoPac";

    /** AD_Table_ID=1200921 */
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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EsPrincipal */
    public static final String COLUMNNAME_EsPrincipal = "EsPrincipal";

	/** Set Primary physician.
	  * Primary physician
	  */
	public void setEsPrincipal (boolean EsPrincipal);

	/** Get Primary physician.
	  * Primary physician
	  */
	public boolean isEsPrincipal();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

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

    /** Column name PHR_MedicoPac_ID */
    public static final String COLUMNNAME_PHR_MedicoPac_ID = "PHR_MedicoPac_ID";

	/** Set Patient medical.
	  * Patient medical
	  */
	public void setPHR_MedicoPac_ID (int PHR_MedicoPac_ID);

	/** Get Patient medical.
	  * Patient medical
	  */
	public int getPHR_MedicoPac_ID();
}
