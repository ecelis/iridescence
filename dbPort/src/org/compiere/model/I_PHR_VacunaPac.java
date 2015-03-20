/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_VacunaPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_VacunaPac 
{

    /** TableName=PHR_VacunaPac */
    public static final String Table_Name = "PHR_VacunaPac";

    /** AD_Table_ID=1200934 */
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

    /** Column name Comentarios */
    public static final String COLUMNNAME_Comentarios = "Comentarios";

	/** Set Notes	  */
	public void setComentarios (String Comentarios);

	/** Get Notes	  */
	public String getComentarios();

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

    /** Column name EXME_Vacuna_ID */
    public static final String COLUMNNAME_EXME_Vacuna_ID = "EXME_Vacuna_ID";

	/** Set Vaccine.
	  * Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID);

	/** Get Vaccine.
	  * Vaccine
	  */
	public int getEXME_Vacuna_ID();

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException;

    /** Column name PHR_Evento_ID */
    public static final String COLUMNNAME_PHR_Evento_ID = "PHR_Evento_ID";

	/** Set Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID);

	/** Get Patient Event	  */
	public int getPHR_Evento_ID();

	public I_PHR_Evento getPHR_Evento() throws RuntimeException;

    /** Column name PHR_VacunaPac_ID */
    public static final String COLUMNNAME_PHR_VacunaPac_ID = "PHR_VacunaPac_ID";

	/** Set Vaccines received	  */
	public void setPHR_VacunaPac_ID (int PHR_VacunaPac_ID);

	/** Get Vaccines received	  */
	public int getPHR_VacunaPac_ID();
}
