/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Prof_Ens
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Prof_Ens 
{

    /** TableName=EXME_Prof_Ens */
    public static final String Table_Name = "EXME_Prof_Ens";

    /** AD_Table_ID=1200057 */
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

    /** Column name Especialidad_TS */
    public static final String COLUMNNAME_Especialidad_TS = "Especialidad_TS";

	/** Set SW Specialty.
	  * Social Work Specialty
	  */
	public void setEspecialidad_TS (String Especialidad_TS);

	/** Get SW Specialty.
	  * Social Work Specialty
	  */
	public String getEspecialidad_TS();

    /** Column name EXME_Ensenanza_5_ID */
    public static final String COLUMNNAME_EXME_Ensenanza_5_ID = "EXME_Ensenanza_5_ID";

	/** Set Teaching Courses 5.
	  * Teaching Courses 5
	  */
	public void setEXME_Ensenanza_5_ID (int EXME_Ensenanza_5_ID);

	/** Get Teaching Courses 5.
	  * Teaching Courses 5
	  */
	public int getEXME_Ensenanza_5_ID();

	public I_EXME_Ensenanza_5 getEXME_Ensenanza_5() throws RuntimeException;

    /** Column name EXME_Prof_Ens_ID */
    public static final String COLUMNNAME_EXME_Prof_Ens_ID = "EXME_Prof_Ens_ID";

	/** Set Teaching Professor	  */
	public void setEXME_Prof_Ens_ID (int EXME_Prof_Ens_ID);

	/** Get Teaching Professor	  */
	public int getEXME_Prof_Ens_ID();

    /** Column name Num_Profesor */
    public static final String COLUMNNAME_Num_Profesor = "Num_Profesor";

	/** Set Profesor Number	  */
	public void setNum_Profesor (BigDecimal Num_Profesor);

	/** Get Profesor Number	  */
	public BigDecimal getNum_Profesor();
}
