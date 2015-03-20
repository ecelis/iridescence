/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ResidenciaRot
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ResidenciaRot 
{

    /** TableName=EXME_ResidenciaRot */
    public static final String Table_Name = "EXME_ResidenciaRot";

    /** AD_Table_ID=1200637 */
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

    /** Column name EXME_ResidenciaRot_ID */
    public static final String COLUMNNAME_EXME_ResidenciaRot_ID = "EXME_ResidenciaRot_ID";

	/** Set Rotating Residence 	  */
	public void setEXME_ResidenciaRot_ID (int EXME_ResidenciaRot_ID);

	/** Get Rotating Residence 	  */
	public int getEXME_ResidenciaRot_ID();

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

    /** Column name FechaInicio */
    public static final String COLUMNNAME_FechaInicio = "FechaInicio";

	/** Set Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio);

	/** Get Beginning Date	  */
	public Timestamp getFechaInicio();

    /** Column name HoraFin */
    public static final String COLUMNNAME_HoraFin = "HoraFin";

	/** Set End Hour	  */
	public void setHoraFin (String HoraFin);

	/** Get End Hour	  */
	public String getHoraFin();

    /** Column name HoraInicio */
    public static final String COLUMNNAME_HoraInicio = "HoraInicio";

	/** Set Start Hour	  */
	public void setHoraInicio (String HoraInicio);

	/** Get Start Hour	  */
	public String getHoraInicio();
}
