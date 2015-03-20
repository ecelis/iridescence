/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Form
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_Form extends PO implements I_AD_Form, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Form (Properties ctx, int AD_Form_ID, String trxName)
    {
      super (ctx, AD_Form_ID, trxName);
      /** if (AD_Form_ID == 0)
        {
			setAccessLevel (null);
			setAD_Form_ID (0);
			setEntityType (null);
// U
			setIsBetaFunctionality (false);
			setIsForPatientAccess (false);
			setMultiAccess (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Form (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_Form[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AccessLevel AD_Reference_ID=5 */
	public static final int ACCESSLEVEL_AD_Reference_ID=5;
	/** Organization = 1 */
	public static final String ACCESSLEVEL_Organization = "1";
	/** Client+Organization = 3 */
	public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
	/** System only = 4 */
	public static final String ACCESSLEVEL_SystemOnly = "4";
	/** All = 7 */
	public static final String ACCESSLEVEL_All = "7";
	/** System+Client = 6 */
	public static final String ACCESSLEVEL_SystemPlusClient = "6";
	/** Client only = 2 */
	public static final String ACCESSLEVEL_ClientOnly = "2";
	/** Client to System = 9 */
	public static final String ACCESSLEVEL_ClientToSystem = "9";
	/** Set Data Access Level.
		@param AccessLevel 
		Access Level required
	  */
	public void setAccessLevel (String AccessLevel)
	{
		if (AccessLevel == null) throw new IllegalArgumentException ("AccessLevel is mandatory");
		if (AccessLevel.equals("1") || AccessLevel.equals("3") || AccessLevel.equals("4") || AccessLevel.equals("7") || AccessLevel.equals("6") || AccessLevel.equals("2") || AccessLevel.equals("9")); else throw new IllegalArgumentException ("AccessLevel Invalid value - " + AccessLevel + " - Reference_ID=5 - 1 - 3 - 4 - 7 - 6 - 2 - 9");		set_Value (COLUMNNAME_AccessLevel, AccessLevel);
	}

	/** Get Data Access Level.
		@return Access Level required
	  */
	public String getAccessLevel () 
	{
		return (String)get_Value(COLUMNNAME_AccessLevel);
	}

	/** Set Special Form.
		@param AD_Form_ID 
		Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID)
	{
		if (AD_Form_ID < 1)
			 throw new IllegalArgumentException ("AD_Form_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Form_ID, Integer.valueOf(AD_Form_ID));
	}

	/** Get Special Form.
		@return Special Form
	  */
	public int getAD_Form_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Form_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Help URI.
		@param Help_URI 
		The URI with the help document for the current window.
	  */
	public void setHelp_URI (String Help_URI)
	{
		set_Value (COLUMNNAME_Help_URI, Help_URI);
	}

	/** Get Help URI.
		@return The URI with the help document for the current window.
	  */
	public String getHelp_URI () 
	{
		return (String)get_Value(COLUMNNAME_Help_URI);
	}

	/** Set Beta Functionality.
		@param IsBetaFunctionality 
		This functionality is considered Beta
	  */
	public void setIsBetaFunctionality (boolean IsBetaFunctionality)
	{
		set_Value (COLUMNNAME_IsBetaFunctionality, Boolean.valueOf(IsBetaFunctionality));
	}

	/** Get Beta Functionality.
		@return This functionality is considered Beta
	  */
	public boolean isBetaFunctionality () 
	{
		Object oo = get_Value(COLUMNNAME_IsBetaFunctionality);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsForPatientAccess.
		@param IsForPatientAccess IsForPatientAccess	  */
	public void setIsForPatientAccess (boolean IsForPatientAccess)
	{
		set_Value (COLUMNNAME_IsForPatientAccess, Boolean.valueOf(IsForPatientAccess));
	}

	/** Get IsForPatientAccess.
		@return IsForPatientAccess	  */
	public boolean isForPatientAccess () 
	{
		Object oo = get_Value(COLUMNNAME_IsForPatientAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsSection.
		@param IsSection IsSection	  */
	public void setIsSection (boolean IsSection)
	{
		set_Value (COLUMNNAME_IsSection, Boolean.valueOf(IsSection));
	}

	/** Get IsSection.
		@return IsSection	  */
	public boolean isSection () 
	{
		Object oo = get_Value(COLUMNNAME_IsSection);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set jsp URL.
		@param JSPURL 
		Web URL of the jsp function
	  */
	public void setJSPURL (String JSPURL)
	{
		set_Value (COLUMNNAME_JSPURL, JSPURL);
	}

	/** Get jsp URL.
		@return Web URL of the jsp function
	  */
	public String getJSPURL () 
	{
		return (String)get_Value(COLUMNNAME_JSPURL);
	}

	/** Set Multi Access.
		@param MultiAccess 
		Multi Access
	  */
	public void setMultiAccess (boolean MultiAccess)
	{
		set_Value (COLUMNNAME_MultiAccess, Boolean.valueOf(MultiAccess));
	}

	/** Get Multi Access.
		@return Multi Access
	  */
	public boolean isMultiAccess () 
	{
		Object oo = get_Value(COLUMNNAME_MultiAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}