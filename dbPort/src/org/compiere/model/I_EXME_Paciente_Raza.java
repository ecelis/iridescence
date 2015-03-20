/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Paciente_Raza
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Paciente_Raza 
{

    /** TableName=EXME_Paciente_Raza */
    public static final String Table_Name = "EXME_Paciente_Raza";

    /** AD_Table_ID=1201355 */
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

    /** Column name EXME_Paciente_Raza_ID */
    public static final String COLUMNNAME_EXME_Paciente_Raza_ID = "EXME_Paciente_Raza_ID";

	/** Set EXME_Paciente_Raza_ID	  */
	public void setEXME_Paciente_Raza_ID (int EXME_Paciente_Raza_ID);

	/** Get EXME_Paciente_Raza_ID	  */
	public int getEXME_Paciente_Raza_ID();

    /** Column name EXME_Razas_ID */
    public static final String COLUMNNAME_EXME_Razas_ID = "EXME_Razas_ID";

	/** Set Race.
	  * Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID);

	/** Get Race.
	  * Races
	  */
	public int getEXME_Razas_ID();
}
