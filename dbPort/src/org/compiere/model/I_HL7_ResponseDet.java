/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_ResponseDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_ResponseDet 
{

    /** TableName=HL7_ResponseDet */
    public static final String Table_Name = "HL7_ResponseDet";

    /** AD_Table_ID=1200902 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name HL7_MessageConf_ID */
    public static final String COLUMNNAME_HL7_MessageConf_ID = "HL7_MessageConf_ID";

	/** Set HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID);

	/** Get HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID();

	public I_HL7_MessageConf getHL7_MessageConf() throws RuntimeException;

    /** Column name HL7_ResponseDet_ID */
    public static final String COLUMNNAME_HL7_ResponseDet_ID = "HL7_ResponseDet_ID";

	/** Set Response Details	  */
	public void setHL7_ResponseDet_ID (int HL7_ResponseDet_ID);

	/** Get Response Details	  */
	public int getHL7_ResponseDet_ID();

    /** Column name HL7_ResponseDetParent_ID */
    public static final String COLUMNNAME_HL7_ResponseDetParent_ID = "HL7_ResponseDetParent_ID";

	/** Set Parent Segment	  */
	public void setHL7_ResponseDetParent_ID (int HL7_ResponseDetParent_ID);

	/** Get Parent Segment	  */
	public int getHL7_ResponseDetParent_ID();

    /** Column name IsGroup */
    public static final String COLUMNNAME_IsGroup = "IsGroup";

	/** Set Is Group.
	  * Marks this segment as a group
	  */
	public void setIsGroup (boolean IsGroup);

	/** Get Is Group.
	  * Marks this segment as a group
	  */
	public boolean isGroup();

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

    /** Column name Script */
    public static final String COLUMNNAME_Script = "Script";

	/** Set Script.
	  * Dynamic Java Language Script to calculate result
	  */
	public void setScript (String Script);

	/** Get Script.
	  * Dynamic Java Language Script to calculate result
	  */
	public String getScript();
}
