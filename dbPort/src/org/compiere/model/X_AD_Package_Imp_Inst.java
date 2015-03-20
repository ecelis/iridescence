/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Package_Imp_Inst
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_Package_Imp_Inst extends PO implements I_AD_Package_Imp_Inst, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Package_Imp_Inst (Properties ctx, int AD_Package_Imp_Inst_ID, String trxName)
    {
      super (ctx, AD_Package_Imp_Inst_ID, trxName);
      /** if (AD_Package_Imp_Inst_ID == 0)
        {
			setAD_PACKAGE_IMP_INST_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_Package_Imp_Inst (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Package_Imp_Inst[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Packages Installed.
		@param AD_PACKAGE_IMP_INST_ID 
		Packages Installed
	  */
	public void setAD_PACKAGE_IMP_INST_ID (int AD_PACKAGE_IMP_INST_ID)
	{
		if (AD_PACKAGE_IMP_INST_ID < 1)
			 throw new IllegalArgumentException ("AD_PACKAGE_IMP_INST_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PACKAGE_IMP_INST_ID, Integer.valueOf(AD_PACKAGE_IMP_INST_ID));
	}

	/** Get Packages Installed.
		@return Packages Installed
	  */
	public int getAD_PACKAGE_IMP_INST_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PACKAGE_IMP_INST_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Creator.
		@param Creator Creator	  */
	public void setCreator (String Creator)
	{
		set_Value (COLUMNNAME_Creator, Creator);
	}

	/** Get Creator.
		@return Creator	  */
	public String getCreator () 
	{
		return (String)get_Value(COLUMNNAME_Creator);
	}

	/** Set CreatorContact.
		@param CreatorContact CreatorContact	  */
	public void setCreatorContact (String CreatorContact)
	{
		set_Value (COLUMNNAME_CreatorContact, CreatorContact);
	}

	/** Get CreatorContact.
		@return CreatorContact	  */
	public String getCreatorContact () 
	{
		return (String)get_Value(COLUMNNAME_CreatorContact);
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

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set PK_Status.
		@param PK_Status PK_Status	  */
	public void setPK_Status (String PK_Status)
	{
		set_Value (COLUMNNAME_PK_Status, PK_Status);
	}

	/** Get PK_Status.
		@return PK_Status	  */
	public String getPK_Status () 
	{
		return (String)get_Value(COLUMNNAME_PK_Status);
	}

	/** Set Package Version.
		@param PK_Version Package Version	  */
	public void setPK_Version (String PK_Version)
	{
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

	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		set_Value (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

	/** Set Uninstall.
		@param Uninstall Uninstall	  */
	public void setUninstall (boolean Uninstall)
	{
		set_Value (COLUMNNAME_Uninstall, Boolean.valueOf(Uninstall));
	}

	/** Get Uninstall.
		@return Uninstall	  */
	public boolean isUninstall () 
	{
		Object oo = get_Value(COLUMNNAME_Uninstall);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (String Version)
	{
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