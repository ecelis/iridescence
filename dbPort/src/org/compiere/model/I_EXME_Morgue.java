/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Morgue
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Morgue 
{

    /** TableName=EXME_Morgue */
    public static final String Table_Name = "EXME_Morgue";

    /** AD_Table_ID=1200315 */
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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name Comentario */
    public static final String COLUMNNAME_Comentario = "Comentario";

	/** Set Comment	  */
	public void setComentario (String Comentario);

	/** Get Comment	  */
	public String getComentario();

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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

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

    /** Column name EXME_Diagnostico2_ID */
    public static final String COLUMNNAME_EXME_Diagnostico2_ID = "EXME_Diagnostico2_ID";

	/** Set Second Diagnostic.
	  * Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID);

	/** Get Second Diagnostic.
	  * Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID();

    /** Column name EXME_Funeraria_ID */
    public static final String COLUMNNAME_EXME_Funeraria_ID = "EXME_Funeraria_ID";

	/** Set Funeral.
	  * Funeral
	  */
	public void setEXME_Funeraria_ID (int EXME_Funeraria_ID);

	/** Get Funeral.
	  * Funeral
	  */
	public int getEXME_Funeraria_ID();

    /** Column name EXME_MedicoForense_ID */
    public static final String COLUMNNAME_EXME_MedicoForense_ID = "EXME_MedicoForense_ID";

	/** Set Forensic Doctor.
	  * Forensic Doctor
	  */
	public void setEXME_MedicoForense_ID (int EXME_MedicoForense_ID);

	/** Get Forensic Doctor.
	  * Forensic Doctor
	  */
	public int getEXME_MedicoForense_ID();

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

    /** Column name EXME_Ministerio_ID */
    public static final String COLUMNNAME_EXME_Ministerio_ID = "EXME_Ministerio_ID";

	/** Set Ministry.
	  * Ministry
	  */
	public void setEXME_Ministerio_ID (int EXME_Ministerio_ID);

	/** Get Ministry.
	  * Ministry
	  */
	public int getEXME_Ministerio_ID();

	public I_EXME_Ministerio getEXME_Ministerio() throws RuntimeException;

    /** Column name EXME_Morgue_ID */
    public static final String COLUMNNAME_EXME_Morgue_ID = "EXME_Morgue_ID";

	/** Set Morgue.
	  * Morgue
	  */
	public void setEXME_Morgue_ID (int EXME_Morgue_ID);

	/** Get Morgue.
	  * Morgue
	  */
	public int getEXME_Morgue_ID();

    /** Column name EXME_MotivoMuerte_ID */
    public static final String COLUMNNAME_EXME_MotivoMuerte_ID = "EXME_MotivoMuerte_ID";

	/** Set Death Cause.
	  * Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID);

	/** Get Death Cause.
	  * Death Cause
	  */
	public int getEXME_MotivoMuerte_ID();

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

    /** Column name EXME_Parentesco_ID */
    public static final String COLUMNNAME_EXME_Parentesco_ID = "EXME_Parentesco_ID";

	/** Set Kinship.
	  * Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID);

	/** Get Kinship.
	  * Kinship
	  */
	public int getEXME_Parentesco_ID();

    /** Column name EXME_Res_Ministerio_ID */
    public static final String COLUMNNAME_EXME_Res_Ministerio_ID = "EXME_Res_Ministerio_ID";

	/** Set Responsible Ministry.
	  * Responsables de Ministerios
	  */
	public void setEXME_Res_Ministerio_ID (int EXME_Res_Ministerio_ID);

	/** Get Responsible Ministry.
	  * Responsables de Ministerios
	  */
	public int getEXME_Res_Ministerio_ID();

    /** Column name Fecha_Muerte */
    public static final String COLUMNNAME_Fecha_Muerte = "Fecha_Muerte";

	/** Set Date of Death.
	  * Date of Death
	  */
	public void setFecha_Muerte (Timestamp Fecha_Muerte);

	/** Get Date of Death.
	  * Date of Death
	  */
	public Timestamp getFecha_Muerte();

    /** Column name Fecha_Salida */
    public static final String COLUMNNAME_Fecha_Salida = "Fecha_Salida";

	/** Set Delivered Date.
	  * Delivered Date
	  */
	public void setFecha_Salida (Timestamp Fecha_Salida);

	/** Get Delivered Date.
	  * Delivered Date
	  */
	public Timestamp getFecha_Salida();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name IsReclamado */
    public static final String COLUMNNAME_IsReclamado = "IsReclamado";

	/** Set Is claimed.
	  * Is claimed
	  */
	public void setIsReclamado (boolean IsReclamado);

	/** Get Is claimed.
	  * Is claimed
	  */
	public boolean isReclamado();

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

    /** Column name Nombre_Pariente */
    public static final String COLUMNNAME_Nombre_Pariente = "Nombre_Pariente";

	/** Set Relative Name.
	  * Relative Name
	  */
	public void setNombre_Pariente (String Nombre_Pariente);

	/** Get Relative Name.
	  * Relative Name
	  */
	public String getNombre_Pariente();

    /** Column name Num_Acta_Defuncion */
    public static final String COLUMNNAME_Num_Acta_Defuncion = "Num_Acta_Defuncion";

	/** Set Death Act Number.
	  * Death Act Number
	  */
	public void setNum_Acta_Defuncion (String Num_Acta_Defuncion);

	/** Get Death Act Number.
	  * Death Act Number
	  */
	public String getNum_Acta_Defuncion();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
