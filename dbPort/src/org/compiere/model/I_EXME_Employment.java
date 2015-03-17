/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Employment
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Employment 
{

    /** TableName=EXME_Employment */
    public static final String Table_Name = "EXME_Employment";

    /** AD_Table_ID=1201093 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name EXME_Employment_ID */
    public static final String COLUMNNAME_EXME_Employment_ID = "EXME_Employment_ID";

	/** Set Employment Information.
	  * Employment Information
	  */
	public void setEXME_Employment_ID (int EXME_Employment_ID);

	/** Get Employment Information.
	  * Employment Information
	  */
	public int getEXME_Employment_ID();

    /** Column name EXME_Puesto_ID */
    public static final String COLUMNNAME_EXME_Puesto_ID = "EXME_Puesto_ID";

	/** Set Job Position.
	  * Job Position
	  */
	public void setEXME_Puesto_ID (int EXME_Puesto_ID);

	/** Get Job Position.
	  * Job Position
	  */
	public int getEXME_Puesto_ID();

	public I_EXME_Puesto getEXME_Puesto() throws RuntimeException;

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

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name Phone3 */
    public static final String COLUMNNAME_Phone3 = "Phone3";

	/** Set 3nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone3 (String Phone3);

	/** Get 3nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone3();

    /** Column name Puesto */
    public static final String COLUMNNAME_Puesto = "Puesto";

	/** Set Position	  */
	public void setPuesto (String Puesto);

	/** Get Position	  */
	public String getPuesto();

    /** Column name Supervisor */
    public static final String COLUMNNAME_Supervisor = "Supervisor";

	/** Set Supervisor	  */
	public void setSupervisor (String Supervisor);

	/** Get Supervisor	  */
	public String getSupervisor();

    /** Column name Work1ComCode */
    public static final String COLUMNNAME_Work1ComCode = "Work1ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Use Code Work Phone 1
	  */
	public void setWork1ComCode (String Work1ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Use Code Work Phone 1
	  */
	public String getWork1ComCode();

    /** Column name Work2ComCode */
    public static final String COLUMNNAME_Work2ComCode = "Work2ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Code Work Phone 2
	  */
	public void setWork2ComCode (String Work2ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Code Work Phone 2
	  */
	public String getWork2ComCode();

    /** Column name Work3ComCode */
    public static final String COLUMNNAME_Work3ComCode = "Work3ComCode";

	/** Set Telecommunication Code.
	  * Telecommunication Code Work Phone 3
	  */
	public void setWork3ComCode (String Work3ComCode);

	/** Get Telecommunication Code.
	  * Telecommunication Code Work Phone 3
	  */
	public String getWork3ComCode();
}
