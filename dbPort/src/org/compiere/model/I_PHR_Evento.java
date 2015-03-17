/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_Evento
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_Evento 
{

    /** TableName=PHR_Evento */
    public static final String Table_Name = "PHR_Evento";

    /** AD_Table_ID=1200931 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name BinaryData */
    public static final String COLUMNNAME_BinaryData = "BinaryData";

	/** Set BinaryData.
	  * Binary Data
	  */
	public void setBinaryData (byte[] BinaryData);

	/** Get BinaryData.
	  * Binary Data
	  */
	public byte[] getBinaryData();

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

    /** Column name EventSection */
    public static final String COLUMNNAME_EventSection = "EventSection";

	/** Set Event Section.
	  * Event Section
	  */
	public void setEventSection (String EventSection);

	/** Get Event Section.
	  * Event Section
	  */
	public String getEventSection();

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

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

    /** Column name PHR_Evento_ID */
    public static final String COLUMNNAME_PHR_Evento_ID = "PHR_Evento_ID";

	/** Set Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID);

	/** Get Patient Event	  */
	public int getPHR_Evento_ID();

    /** Column name PHR_MedicoPac_ID */
    public static final String COLUMNNAME_PHR_MedicoPac_ID = "PHR_MedicoPac_ID";

	/** Set Patient medical.
	  * Patient medical
	  */
	public void setPHR_MedicoPac_ID (int PHR_MedicoPac_ID);

	/** Get Patient medical.
	  * Patient medical
	  */
	public int getPHR_MedicoPac_ID();

	public I_PHR_MedicoPac getPHR_MedicoPac() throws RuntimeException;

    /** Column name TipoEvento */
    public static final String COLUMNNAME_TipoEvento = "TipoEvento";

	/** Set Event Type	  */
	public void setTipoEvento (String TipoEvento);

	/** Get Event Type	  */
	public String getTipoEvento();
}
