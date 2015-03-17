/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ArchCliMov
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ArchCliMov 
{

    /** TableName=EXME_ArchCliMov */
    public static final String Table_Name = "EXME_ArchCliMov";

    /** AD_Table_ID=1200477 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EnArchivo */
    public static final String COLUMNNAME_EnArchivo = "EnArchivo";

	/** Set InArchive	  */
	public void setEnArchivo (boolean EnArchivo);

	/** Get InArchive	  */
	public boolean isEnArchivo();

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

    /** Column name EXME_ArchCli_ID */
    public static final String COLUMNNAME_EXME_ArchCli_ID = "EXME_ArchCli_ID";

	/** Set Clinical Archive.
	  * Clinical Archive
	  */
	public void setEXME_ArchCli_ID (int EXME_ArchCli_ID);

	/** Get Clinical Archive.
	  * Clinical Archive
	  */
	public int getEXME_ArchCli_ID();

	public I_EXME_ArchCli getEXME_ArchCli() throws RuntimeException;

    /** Column name EXME_ArchCliMov_ID */
    public static final String COLUMNNAME_EXME_ArchCliMov_ID = "EXME_ArchCliMov_ID";

	/** Set Movement of Expedient	  */
	public void setEXME_ArchCliMov_ID (int EXME_ArchCliMov_ID);

	/** Get Movement of Expedient	  */
	public int getEXME_ArchCliMov_ID();

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

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

    /** Column name EXME_TipoExped_ID */
    public static final String COLUMNNAME_EXME_TipoExped_ID = "EXME_TipoExped_ID";

	/** Set Type of Medical Record.
	  * Type of Medical Record
	  */
	public void setEXME_TipoExped_ID (int EXME_TipoExped_ID);

	/** Get Type of Medical Record.
	  * Type of Medical Record
	  */
	public int getEXME_TipoExped_ID();

	public I_EXME_TipoExped getEXME_TipoExped() throws RuntimeException;

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public String getExpediente();

    /** Column name Extemporaneo */
    public static final String COLUMNNAME_Extemporaneo = "Extemporaneo";

	/** Set Extemporaneo.
	  * Extemporaneous
	  */
	public void setExtemporaneo (boolean Extemporaneo);

	/** Get Extemporaneo.
	  * Extemporaneous
	  */
	public boolean isExtemporaneo();

    /** Column name FechaSolicitud */
    public static final String COLUMNNAME_FechaSolicitud = "FechaSolicitud";

	/** Set Date Request	  */
	public void setFechaSolicitud (Timestamp FechaSolicitud);

	/** Get Date Request	  */
	public Timestamp getFechaSolicitud();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name Ubicacion */
    public static final String COLUMNNAME_Ubicacion = "Ubicacion";

	/** Set Location of Element.
	  * Location of Element
	  */
	public void setUbicacion (String Ubicacion);

	/** Get Location of Element.
	  * Location of Element
	  */
	public String getUbicacion();
}
