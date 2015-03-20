/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Hist_Cama
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Hist_Cama 
{

    /** TableName=EXME_Hist_Cama */
    public static final String Table_Name = "EXME_Hist_Cama";

    /** AD_Table_ID=1200042 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name EXME_Cama_Act_ID */
    public static final String COLUMNNAME_EXME_Cama_Act_ID = "EXME_Cama_Act_ID";

	/** Set Actual Bed.
	  * Actual Bed
	  */
	public void setEXME_Cama_Act_ID (int EXME_Cama_Act_ID);

	/** Get Actual Bed.
	  * Actual Bed
	  */
	public int getEXME_Cama_Act_ID();

    /** Column name EXME_Cama_Ant_ID */
    public static final String COLUMNNAME_EXME_Cama_Ant_ID = "EXME_Cama_Ant_ID";

	/** Set Previous Bed	  */
	public void setEXME_Cama_Ant_ID (int EXME_Cama_Ant_ID);

	/** Get Previous Bed	  */
	public int getEXME_Cama_Ant_ID();

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

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_EstServ_Act_ID */
    public static final String COLUMNNAME_EXME_EstServ_Act_ID = "EXME_EstServ_Act_ID";

	/** Set Actual Service Station.
	  * Actual Service Station
	  */
	public void setEXME_EstServ_Act_ID (int EXME_EstServ_Act_ID);

	/** Get Actual Service Station.
	  * Actual Service Station
	  */
	public int getEXME_EstServ_Act_ID();

    /** Column name EXME_EstServ_Ant_ID */
    public static final String COLUMNNAME_EXME_EstServ_Ant_ID = "EXME_EstServ_Ant_ID";

	/** Set Previous Service Station.
	  * Previous Service Station
	  */
	public void setEXME_EstServ_Ant_ID (int EXME_EstServ_Ant_ID);

	/** Get Previous Service Station.
	  * Previous Service Station
	  */
	public int getEXME_EstServ_Ant_ID();

    /** Column name EXME_Hist_Cama_ID */
    public static final String COLUMNNAME_EXME_Hist_Cama_ID = "EXME_Hist_Cama_ID";

	/** Set Bed History.
	  * Bed History
	  */
	public void setEXME_Hist_Cama_ID (int EXME_Hist_Cama_ID);

	/** Get Bed History.
	  * Bed History
	  */
	public int getEXME_Hist_Cama_ID();

    /** Column name Fecha_Cambio */
    public static final String COLUMNNAME_Fecha_Cambio = "Fecha_Cambio";

	/** Set Date of Change	  */
	public void setFecha_Cambio (Timestamp Fecha_Cambio);

	/** Get Date of Change	  */
	public Timestamp getFecha_Cambio();

    /** Column name Fecha_Cambio_Ant */
    public static final String COLUMNNAME_Fecha_Cambio_Ant = "Fecha_Cambio_Ant";

	/** Set Date for Previous Bed	  */
	public void setFecha_Cambio_Ant (Timestamp Fecha_Cambio_Ant);

	/** Get Date for Previous Bed	  */
	public Timestamp getFecha_Cambio_Ant();
}
