/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DiagnosticoSeccion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_DiagnosticoSeccion 
{

    /** TableName=EXME_DiagnosticoSeccion */
    public static final String Table_Name = "EXME_DiagnosticoSeccion";

    /** AD_Table_ID=1200903 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

    /** Column name EXME_DiagnosticoSeccion_ID */
    public static final String COLUMNNAME_EXME_DiagnosticoSeccion_ID = "EXME_DiagnosticoSeccion_ID";

	/** Set Diagnostic Section	  */
	public void setEXME_DiagnosticoSeccion_ID (int EXME_DiagnosticoSeccion_ID);

	/** Get Diagnostic Section	  */
	public int getEXME_DiagnosticoSeccion_ID();

    /** Column name EXME_SectionBody_ID */
    public static final String COLUMNNAME_EXME_SectionBody_ID = "EXME_SectionBody_ID";

	/** Set Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID);

	/** Get Section Body	  */
	public int getEXME_SectionBody_ID();

	public I_EXME_SectionBody getEXME_SectionBody() throws RuntimeException;
}
