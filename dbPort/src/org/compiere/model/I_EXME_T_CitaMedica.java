/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_CitaMedica
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_CitaMedica 
{

    /** TableName=EXME_T_CitaMedica */
    public static final String Table_Name = "EXME_T_CitaMedica";

    /** AD_Table_ID=1200398 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

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

    /** Column name EXME_T_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_T_CitaMedica_ID = "EXME_T_CitaMedica_ID";

	/** Set Temporal Medical Appointment	  */
	public void setEXME_T_CitaMedica_ID (int EXME_T_CitaMedica_ID);

	/** Get Temporal Medical Appointment	  */
	public int getEXME_T_CitaMedica_ID();

    /** Column name FechaHrCita */
    public static final String COLUMNNAME_FechaHrCita = "FechaHrCita";

	/** Set Date.
	  * Date
	  */
	public void setFechaHrCita (Timestamp FechaHrCita);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFechaHrCita();

    /** Column name IsInfoSent */
    public static final String COLUMNNAME_IsInfoSent = "IsInfoSent";

	/** Set Send Info.
	  * Send informational messages and copies
	  */
	public void setIsInfoSent (boolean IsInfoSent);

	/** Get Send Info.
	  * Send informational messages and copies
	  */
	public boolean isInfoSent();

    /** Column name TipoOperacion */
    public static final String COLUMNNAME_TipoOperacion = "TipoOperacion";

	/** Set Operation Type	  */
	public void setTipoOperacion (String TipoOperacion);

	/** Get Operation Type	  */
	public String getTipoOperacion();
}
