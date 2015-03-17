/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for R_MailText
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_R_MailText 
{

    /** TableName=R_MailText */
    public static final String Table_Name = "R_MailText";

    /** AD_Table_ID=416 */
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

    /** Column name IsHtml */
    public static final String COLUMNNAME_IsHtml = "IsHtml";

	/** Set HTML.
	  * Text has HTML tags
	  */
	public void setIsHtml (boolean IsHtml);

	/** Get HTML.
	  * Text has HTML tags
	  */
	public boolean isHtml();

    /** Column name MailHeader */
    public static final String COLUMNNAME_MailHeader = "MailHeader";

	/** Set Subject.
	  * Mail Header (Subject)
	  */
	public void setMailHeader (String MailHeader);

	/** Get Subject.
	  * Mail Header (Subject)
	  */
	public String getMailHeader();

    /** Column name MailText */
    public static final String COLUMNNAME_MailText = "MailText";

	/** Set Mail Text.
	  * Text used for Mail message
	  */
	public void setMailText (String MailText);

	/** Get Mail Text.
	  * Text used for Mail message
	  */
	public String getMailText();

    /** Column name MailText2 */
    public static final String COLUMNNAME_MailText2 = "MailText2";

	/** Set Mail Text 2.
	  * Optional second text part used for Mail message
	  */
	public void setMailText2 (String MailText2);

	/** Get Mail Text 2.
	  * Optional second text part used for Mail message
	  */
	public String getMailText2();

    /** Column name MailText3 */
    public static final String COLUMNNAME_MailText3 = "MailText3";

	/** Set Mail Text 3.
	  * Optional third text part used for Mail message
	  */
	public void setMailText3 (String MailText3);

	/** Get Mail Text 3.
	  * Optional third text part used for Mail message
	  */
	public String getMailText3();

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

    /** Column name R_MailText_ID */
    public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

	/** Set Mail Template.
	  * Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID);

	/** Get Mail Template.
	  * Text templates for mailings
	  */
	public int getR_MailText_ID();
}
