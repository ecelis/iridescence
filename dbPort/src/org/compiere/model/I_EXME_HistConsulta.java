/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_HistConsulta
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_HistConsulta 
{

    /** TableName=EXME_HistConsulta */
    public static final String Table_Name = "EXME_HistConsulta";

    /** AD_Table_ID=1201282 */
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

    /** Column name Cerrada */
    public static final String COLUMNNAME_Cerrada = "Cerrada";

	/** Set Cerrada	  */
	public void setCerrada (boolean Cerrada);

	/** Get Cerrada	  */
	public boolean isCerrada();

    /** Column name Descripcion */
    public static final String COLUMNNAME_Descripcion = "Descripcion";

	/** Set Description	  */
	public void setDescripcion (String Descripcion);

	/** Get Description	  */
	public String getDescripcion();

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

    /** Column name EXME_HistConsulta_ID */
    public static final String COLUMNNAME_EXME_HistConsulta_ID = "EXME_HistConsulta_ID";

	/** Set EXME_HistConsulta_ID	  */
	public void setEXME_HistConsulta_ID (int EXME_HistConsulta_ID);

	/** Get EXME_HistConsulta_ID	  */
	public int getEXME_HistConsulta_ID();

    /** Column name FechaCita */
    public static final String COLUMNNAME_FechaCita = "FechaCita";

	/** Set FechaCita	  */
	public void setFechaCita (Timestamp FechaCita);

	/** Get FechaCita	  */
	public Timestamp getFechaCita();

    /** Column name NombreMedico */
    public static final String COLUMNNAME_NombreMedico = "NombreMedico";

	/** Set NombreMedico	  */
	public void setNombreMedico (String NombreMedico);

	/** Get NombreMedico	  */
	public String getNombreMedico();
}
