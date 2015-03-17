/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacienteAler_Hist
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacienteAler_Hist 
{

    /** TableName=EXME_PacienteAler_Hist */
    public static final String Table_Name = "EXME_PacienteAler_Hist";

    /** AD_Table_ID=1200828 */
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

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

    /** Column name EXME_PacienteAler_Hist_ID */
    public static final String COLUMNNAME_EXME_PacienteAler_Hist_ID = "EXME_PacienteAler_Hist_ID";

	/** Set History Patient Allergy.
	  * History Patient Allergy Identifier
	  */
	public void setEXME_PacienteAler_Hist_ID (int EXME_PacienteAler_Hist_ID);

	/** Get History Patient Allergy.
	  * History Patient Allergy Identifier
	  */
	public int getEXME_PacienteAler_Hist_ID();

    /** Column name EXME_PacienteAler_ID */
    public static final String COLUMNNAME_EXME_PacienteAler_ID = "EXME_PacienteAler_ID";

	/** Set Allergies Patient.
	  * Allergies Patient
	  */
	public void setEXME_PacienteAler_ID (int EXME_PacienteAler_ID);

	/** Get Allergies Patient.
	  * Allergies Patient
	  */
	public int getEXME_PacienteAler_ID();

	public I_EXME_PacienteAler getEXME_PacienteAler() throws RuntimeException;

    /** Column name Reaccion */
    public static final String COLUMNNAME_Reaccion = "Reaccion";

	/** Set Reaction	  */
	public void setReaccion (String Reaccion);

	/** Get Reaction	  */
	public String getReaccion();

    /** Column name Severidad */
    public static final String COLUMNNAME_Severidad = "Severidad";

	/** Set Severity	  */
	public void setSeveridad (String Severidad);

	/** Get Severity	  */
	public String getSeveridad();
}
