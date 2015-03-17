/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_PlaticasPaciente
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_PlaticasPaciente 
{

    /** TableName=EXME_MO_PlaticasPaciente */
    public static final String Table_Name = "EXME_MO_PlaticasPaciente";

    /** AD_Table_ID=1200397 */
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

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

    /** Column name EXME_MO_Platicas_ID */
    public static final String COLUMNNAME_EXME_MO_Platicas_ID = "EXME_MO_Platicas_ID";

	/** Set Talk.
	  * Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (int EXME_MO_Platicas_ID);

	/** Get Talk.
	  * Talk  by Speciality
	  */
	public int getEXME_MO_Platicas_ID();

	public I_EXME_MO_Platicas getEXME_MO_Platicas() throws RuntimeException;

    /** Column name EXME_MO_PlaticasPaciente_ID */
    public static final String COLUMNNAME_EXME_MO_PlaticasPaciente_ID = "EXME_MO_PlaticasPaciente_ID";

	/** Set Patient Talk.
	  * Patient Talk
	  */
	public void setEXME_MO_PlaticasPaciente_ID (int EXME_MO_PlaticasPaciente_ID);

	/** Get Patient Talk.
	  * Patient Talk
	  */
	public int getEXME_MO_PlaticasPaciente_ID();

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
