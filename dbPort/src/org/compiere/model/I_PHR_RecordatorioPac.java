/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_RecordatorioPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_RecordatorioPac 
{

    /** TableName=PHR_RecordatorioPac */
    public static final String Table_Name = "PHR_RecordatorioPac";

    /** AD_Table_ID=1201009 */
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

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name HoraInicial */
    public static final String COLUMNNAME_HoraInicial = "HoraInicial";

	/** Set Start Hour	  */
	public void setHoraInicial (Timestamp HoraInicial);

	/** Get Start Hour	  */
	public Timestamp getHoraInicial();

    /** Column name ItervaloHrs */
    public static final String COLUMNNAME_ItervaloHrs = "ItervaloHrs";

	/** Set ItervaloHrs	  */
	public void setItervaloHrs (int ItervaloHrs);

	/** Get ItervaloHrs	  */
	public int getItervaloHrs();

    /** Column name ItervaloMins */
    public static final String COLUMNNAME_ItervaloMins = "ItervaloMins";

	/** Set ItervaloMins	  */
	public void setItervaloMins (int ItervaloMins);

	/** Get ItervaloMins	  */
	public int getItervaloMins();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

    /** Column name PHR_RecordatorioPac_ID */
    public static final String COLUMNNAME_PHR_RecordatorioPac_ID = "PHR_RecordatorioPac_ID";

	/** Set Patient Reminder.
	  * Patient Reminder
	  */
	public void setPHR_RecordatorioPac_ID (int PHR_RecordatorioPac_ID);

	/** Get Patient Reminder.
	  * Patient Reminder
	  */
	public int getPHR_RecordatorioPac_ID();

    /** Column name Telefono */
    public static final String COLUMNNAME_Telefono = "Telefono";

	/** Set Telephone.
	  * friend telephone
	  */
	public void setTelefono (String Telefono);

	/** Get Telephone.
	  * friend telephone
	  */
	public String getTelefono();

    /** Column name TipoRecordatorio */
    public static final String COLUMNNAME_TipoRecordatorio = "TipoRecordatorio";

	/** Set Reminder Type	  */
	public void setTipoRecordatorio (String TipoRecordatorio);

	/** Get Reminder Type	  */
	public String getTipoRecordatorio();

    /** Column name ZonaHoraria */
    public static final String COLUMNNAME_ZonaHoraria = "ZonaHoraria";

	/** Set Time Zone.
	  * Time Zone
	  */
	public void setZonaHoraria (String ZonaHoraria);

	/** Get Time Zone.
	  * Time Zone
	  */
	public String getZonaHoraria();
}
