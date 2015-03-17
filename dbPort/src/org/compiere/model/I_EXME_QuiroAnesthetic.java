/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_QuiroAnesthetic
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_QuiroAnesthetic 
{

    /** TableName=EXME_QuiroAnesthetic */
    public static final String Table_Name = "EXME_QuiroAnesthetic";

    /** AD_Table_ID=1201264 */
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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_ProgQuiro_ID */
    public static final String COLUMNNAME_EXME_ProgQuiro_ID = "EXME_ProgQuiro_ID";

	/** Set Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID);

	/** Get Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID();

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException;

    /** Column name EXME_QuiroAnesthetic_ID */
    public static final String COLUMNNAME_EXME_QuiroAnesthetic_ID = "EXME_QuiroAnesthetic_ID";

	/** Set Anesthetic Surgery	  */
	public void setEXME_QuiroAnesthetic_ID (int EXME_QuiroAnesthetic_ID);

	/** Get Anesthetic Surgery	  */
	public int getEXME_QuiroAnesthetic_ID();

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
}
