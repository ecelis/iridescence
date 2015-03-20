/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_Radiografias
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_Radiografias 
{

    /** TableName=EXME_MO_Radiografias */
    public static final String Table_Name = "EXME_MO_Radiografias";

    /** AD_Table_ID=1200391 */
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

    /** Column name EXME_MO_Radiografias_ID */
    public static final String COLUMNNAME_EXME_MO_Radiografias_ID = "EXME_MO_Radiografias_ID";

	/** Set X-Ray	  */
	public void setEXME_MO_Radiografias_ID (int EXME_MO_Radiografias_ID);

	/** Get X-Ray	  */
	public int getEXME_MO_Radiografias_ID();

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

    /** Column name Imagen */
    public static final String COLUMNNAME_Imagen = "Imagen";

	/** Set Image.
	  * Name of stored image
	  */
	public void setImagen (byte[] Imagen);

	/** Get Image.
	  * Name of stored image
	  */
	public byte[] getImagen();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Nota */
    public static final String COLUMNNAME_Nota = "Nota";

	/** Set Note.
	  * Note
	  */
	public void setNota (String Nota);

	/** Get Note.
	  * Note
	  */
	public String getNota();
}
