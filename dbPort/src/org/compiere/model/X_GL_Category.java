/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for GL_Category
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_GL_Category extends PO implements I_GL_Category, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_Category (Properties ctx, int GL_Category_ID, String trxName)
    {
      super (ctx, GL_Category_ID, trxName);
      /** if (GL_Category_ID == 0)
        {
			setCategoryType (null);
// M
			setGL_Category_ID (0);
			setIsDefault (false);
			setName (null);
			setPolicyType (null);
        } */
    }

    /** Load Constructor */
    public X_GL_Category (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_GL_Category[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** CategoryType AD_Reference_ID=207 */
	public static final int CATEGORYTYPE_AD_Reference_ID=207;
	/** Manual = M */
	public static final String CATEGORYTYPE_Manual = "M";
	/** Import = I */
	public static final String CATEGORYTYPE_Import = "I";
	/** Document = D */
	public static final String CATEGORYTYPE_Document = "D";
	/** System generated = S */
	public static final String CATEGORYTYPE_SystemGenerated = "S";
	/** Set Category Type.
		@param CategoryType 
		Source of the Journal with this category
	  */
	public void setCategoryType (String CategoryType)
	{
		if (CategoryType == null) throw new IllegalArgumentException ("CategoryType is mandatory");
		if (CategoryType.equals("M") || CategoryType.equals("I") || CategoryType.equals("D") || CategoryType.equals("S")); else throw new IllegalArgumentException ("CategoryType Invalid value - " + CategoryType + " - Reference_ID=207 - M - I - D - S");		set_Value (COLUMNNAME_CategoryType, CategoryType);
	}

	/** Get Category Type.
		@return Source of the Journal with this category
	  */
	public String getCategoryType () 
	{
		return (String)get_Value(COLUMNNAME_CategoryType);
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

	/** Set GL Category.
		@param GL_Category_ID 
		General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID)
	{
		if (GL_Category_ID < 1)
			 throw new IllegalArgumentException ("GL_Category_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_Category_ID, Integer.valueOf(GL_Category_ID));
	}

	/** Get GL Category.
		@return General Ledger Category
	  */
	public int getGL_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Item Name.
		@param ItemName Print Item Name	  */
	public void setItemName (String ItemName)
	{
		set_Value (COLUMNNAME_ItemName, ItemName);
	}

	/** Get Print Item Name.
		@return Print Item Name	  */
	public String getItemName () 
	{
		return (String)get_Value(COLUMNNAME_ItemName);
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

	/** PolicyType AD_Reference_ID=1200673 */
	public static final int POLICYTYPE_AD_Reference_ID=1200673;
	/** None = N */
	public static final String POLICYTYPE_None = "N";
	/** Journal = J */
	public static final String POLICYTYPE_Journal = "J";
	/** Income = I */
	public static final String POLICYTYPE_Income = "I";
	/** Expense = E */
	public static final String POLICYTYPE_Expense = "E";
	/** Set Policy Type.
		@param PolicyType Policy Type	  */
	public void setPolicyType (String PolicyType)
	{
		if (PolicyType == null) throw new IllegalArgumentException ("PolicyType is mandatory");
		if (PolicyType.equals("N") || PolicyType.equals("J") || PolicyType.equals("I") || PolicyType.equals("E")); else throw new IllegalArgumentException ("PolicyType Invalid value - " + PolicyType + " - Reference_ID=1200673 - N - J - I - E");		set_Value (COLUMNNAME_PolicyType, PolicyType);
	}

	/** Get Policy Type.
		@return Policy Type	  */
	public String getPolicyType () 
	{
		return (String)get_Value(COLUMNNAME_PolicyType);
	}
}