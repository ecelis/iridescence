/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CalcMasa
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CalcMasa 
{

    /** TableName=EXME_CalcMasa */
    public static final String Table_Name = "EXME_CalcMasa";

    /** AD_Table_ID=1201046 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name ActivityLevel */
    public static final String COLUMNNAME_ActivityLevel = "ActivityLevel";

	/** Set Level of Physic Activity	  */
	public void setActivityLevel (String ActivityLevel);

	/** Get Level of Physic Activity	  */
	public String getActivityLevel();

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

    /** Column name EXME_CalcMasa_ID */
    public static final String COLUMNNAME_EXME_CalcMasa_ID = "EXME_CalcMasa_ID";

	/** Set Register Body Mass	  */
	public void setEXME_CalcMasa_ID (int EXME_CalcMasa_ID);

	/** Get Register Body Mass	  */
	public int getEXME_CalcMasa_ID();

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

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();
}
