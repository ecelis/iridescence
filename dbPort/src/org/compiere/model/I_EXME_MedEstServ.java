/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MedEstServ
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MedEstServ 
{

    /** TableName=EXME_MedEstServ */
    public static final String Table_Name = "EXME_MedEstServ";

    /** AD_Table_ID=1201109 */
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

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_MedEstServ_ID */
    public static final String COLUMNNAME_EXME_MedEstServ_ID = "EXME_MedEstServ_ID";

	/** Set Physician Per Service Station	  */
	public void setEXME_MedEstServ_ID (int EXME_MedEstServ_ID);

	/** Get Physician Per Service Station	  */
	public int getEXME_MedEstServ_ID();

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

    /** Column name Friday */
    public static final String COLUMNNAME_Friday = "Friday";

	/** Set Friday	  */
	public void setFriday (boolean Friday);

	/** Get Friday	  */
	public boolean isFriday();

    /** Column name Monday */
    public static final String COLUMNNAME_Monday = "Monday";

	/** Set Monday	  */
	public void setMonday (boolean Monday);

	/** Get Monday	  */
	public boolean isMonday();

    /** Column name Saturday */
    public static final String COLUMNNAME_Saturday = "Saturday";

	/** Set Saturday	  */
	public void setSaturday (boolean Saturday);

	/** Get Saturday	  */
	public boolean isSaturday();

    /** Column name Sunday */
    public static final String COLUMNNAME_Sunday = "Sunday";

	/** Set Sunday	  */
	public void setSunday (boolean Sunday);

	/** Get Sunday	  */
	public boolean isSunday();

    /** Column name Thursday */
    public static final String COLUMNNAME_Thursday = "Thursday";

	/** Set Thursday	  */
	public void setThursday (boolean Thursday);

	/** Get Thursday	  */
	public boolean isThursday();

    /** Column name Tuesday */
    public static final String COLUMNNAME_Tuesday = "Tuesday";

	/** Set Tuesday	  */
	public void setTuesday (boolean Tuesday);

	/** Get Tuesday	  */
	public boolean isTuesday();

    /** Column name Wednesday */
    public static final String COLUMNNAME_Wednesday = "Wednesday";

	/** Set Wednesday	  */
	public void setWednesday (boolean Wednesday);

	/** Get Wednesday	  */
	public boolean isWednesday();
}
