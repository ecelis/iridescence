/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Package_Exp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_Package_Exp extends PO implements I_AD_Package_Exp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Package_Exp (Properties ctx, int AD_Package_Exp_ID, String trxName)
    {
      super (ctx, AD_Package_Exp_ID, trxName);
      /** if (AD_Package_Exp_ID == 0)
        {
			setAD_Package_Exp_ID (0);
			setDescription (null);
			setEMail (null);
			setFile_Directory (null);
			setInstructions (null);
			setName (null);
			setPK_Version (null);
			setProcessing (false);
			setReleaseNo (null);
			setUserName (null);
			setVersion (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Package_Exp (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_Package_Exp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AD_Package_Exp_ID.
		@param AD_Package_Exp_ID AD_Package_Exp_ID	  */
	public void setAD_Package_Exp_ID (int AD_Package_Exp_ID)
	{
		if (AD_Package_Exp_ID < 1)
			 throw new IllegalArgumentException ("AD_Package_Exp_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Package_Exp_ID, Integer.valueOf(AD_Package_Exp_ID));
	}

	/** Get AD_Package_Exp_ID.
		@return AD_Package_Exp_ID	  */
	public int getAD_Package_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Package_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_Package_Exp_ID()));
    }

	/** AD_Package_Type AD_Reference_ID=1200270 */
	public static final int AD_PACKAGE_TYPE_AD_Reference_ID=1200270;
	/** Local Transfer = L */
	public static final String AD_PACKAGE_TYPE_LocalTransfer = "L";
	/** Remote Transfer = R */
	public static final String AD_PACKAGE_TYPE_RemoteTransfer = "R";
	/** XML File = X */
	public static final String AD_PACKAGE_TYPE_XMLFile = "X";
	/** Set AD_Package_Type.
		@param AD_Package_Type AD_Package_Type	  */
	public void setAD_Package_Type (String AD_Package_Type)
	{

		if (AD_Package_Type == null || AD_Package_Type.equals("L") || AD_Package_Type.equals("R") || AD_Package_Type.equals("X")); else throw new IllegalArgumentException ("AD_Package_Type Invalid value - " + AD_Package_Type + " - Reference_ID=1200270 - L - R - X");		set_Value (COLUMNNAME_AD_Package_Type, AD_Package_Type);
	}

	/** Get AD_Package_Type.
		@return AD_Package_Type	  */
	public String getAD_Package_Type () 
	{
		return (String)get_Value(COLUMNNAME_AD_Package_Type);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		if (EMail == null)
			throw new IllegalArgumentException ("EMail is mandatory.");
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set File_Directory.
		@param File_Directory File_Directory	  */
	public void setFile_Directory (String File_Directory)
	{
		if (File_Directory == null)
			throw new IllegalArgumentException ("File_Directory is mandatory.");
		set_Value (COLUMNNAME_File_Directory, File_Directory);
	}

	/** Get File_Directory.
		@return File_Directory	  */
	public String getFile_Directory () 
	{
		return (String)get_Value(COLUMNNAME_File_Directory);
	}

	/** Set Instructions.
		@param Instructions Instructions	  */
	public void setInstructions (String Instructions)
	{
		if (Instructions == null)
			throw new IllegalArgumentException ("Instructions is mandatory.");
		set_Value (COLUMNNAME_Instructions, Instructions);
	}

	/** Get Instructions.
		@return Instructions	  */
	public String getInstructions () 
	{
		return (String)get_Value(COLUMNNAME_Instructions);
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

	/** Set Package Version.
		@param PK_Version Package Version	  */
	public void setPK_Version (String PK_Version)
	{
		if (PK_Version == null)
			throw new IllegalArgumentException ("PK_Version is mandatory.");
		set_Value (COLUMNNAME_PK_Version, PK_Version);
	}

	/** Get Package Version.
		@return Package Version	  */
	public String getPK_Version () 
	{
		return (String)get_Value(COLUMNNAME_PK_Version);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ReleaseNo AD_Reference_ID=1200271 */
	public static final int RELEASENO_AD_Reference_ID=1200271;
	/** Release 3.1.0 = Release 3.1.0 */
	public static final String RELEASENO_Release310 = "Release 3.1.0";
	/** Release 3.2.0 = Release 3.2.0 */
	public static final String RELEASENO_Release320 = "Release 3.2.0";
	/** Release 3.3.0 = Release 3.3.0 */
	public static final String RELEASENO_Release330 = "Release 3.3.0";
	/** Release 2.5.2a = Release 2.5.2a */
	public static final String RELEASENO_Release252a = "Release 2.5.2a";
	/** Release 2.5.2b = Release 2.5.2b */
	public static final String RELEASENO_Release252b = "Release 2.5.2b";
	/** Release 2.5.2c = Release 2.5.2c */
	public static final String RELEASENO_Release252c = "Release 2.5.2c";
	/** Release 2.5.2d = Release 2.5.2d */
	public static final String RELEASENO_Release252d = "Release 2.5.2d";
	/** Release 2.5.2e = Release 2.5.2e */
	public static final String RELEASENO_Release252e = "Release 2.5.2e";
	/** Release 2.5.3a = Release 2.5.3a */
	public static final String RELEASENO_Release253a = "Release 2.5.3a";
	/** Release 2.5.3b = Release 2.5.3b */
	public static final String RELEASENO_Release253b = "Release 2.5.3b";
	/** No specific release = all */
	public static final String RELEASENO_NoSpecificRelease = "all";
	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		if (ReleaseNo == null) throw new IllegalArgumentException ("ReleaseNo is mandatory");
		if (ReleaseNo.equals("Release 3.1.0") || ReleaseNo.equals("Release 3.2.0") || ReleaseNo.equals("Release 3.3.0") || ReleaseNo.equals("Release 2.5.2a") || ReleaseNo.equals("Release 2.5.2b") || ReleaseNo.equals("Release 2.5.2c") || ReleaseNo.equals("Release 2.5.2d") || ReleaseNo.equals("Release 2.5.2e") || ReleaseNo.equals("Release 2.5.3a") || ReleaseNo.equals("Release 2.5.3b") || ReleaseNo.equals("all")); else throw new IllegalArgumentException ("ReleaseNo Invalid value - " + ReleaseNo + " - Reference_ID=1200271 - Release 3.1.0 - Release 3.2.0 - Release 3.3.0 - Release 2.5.2a - Release 2.5.2b - Release 2.5.2c - Release 2.5.2d - Release 2.5.2e - Release 2.5.3a - Release 2.5.3b - all");		set_Value (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		if (UserName == null)
			throw new IllegalArgumentException ("UserName is mandatory.");
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (String Version)
	{
		if (Version == null)
			throw new IllegalArgumentException ("Version is mandatory.");
		set_Value (COLUMNNAME_Version, Version);
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public String getVersion () 
	{
		return (String)get_Value(COLUMNNAME_Version);
	}
}