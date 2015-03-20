/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Group
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Asset_Group extends PO implements I_A_Asset_Group, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Group (Properties ctx, int A_Asset_Group_ID, String trxName)
    {
      super (ctx, A_Asset_Group_ID, trxName);
      /** if (A_Asset_Group_ID == 0)
        {
			setA_Asset_Group_ID (0);
			setIsCreateAsActive (true);
// Y
			setIsDepreciated (false);
			setIsOneAssetPerUOM (false);
			setIsOwned (false);
			setIsTrackIssues (false);
// N
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Group (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Group[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Group_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_Clase getEXME_Clase() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Clase.Table_Name);
        I_EXME_Clase result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Clase)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Clase_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Class.
		@param EXME_Clase_ID 
		Class identifier
	  */
	public void setEXME_Clase_ID (int EXME_Clase_ID)
	{
		if (EXME_Clase_ID < 1) 
			set_Value (COLUMNNAME_EXME_Clase_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Clase_ID, Integer.valueOf(EXME_Clase_ID));
	}

	/** Get Class.
		@return Class identifier
	  */
	public int getEXME_Clase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Clase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Cuenta getEXME_Cuenta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuenta.Table_Name);
        I_EXME_Cuenta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuenta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuenta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Account.
		@param EXME_Cuenta_ID 
		Account identifier
	  */
	public void setEXME_Cuenta_ID (int EXME_Cuenta_ID)
	{
		if (EXME_Cuenta_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuenta_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuenta_ID, Integer.valueOf(EXME_Cuenta_ID));
	}

	/** Get Account.
		@return Account identifier
	  */
	public int getEXME_Cuenta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuenta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Create As Active.
		@param IsCreateAsActive 
		Create Asset and activate it
	  */
	public void setIsCreateAsActive (boolean IsCreateAsActive)
	{
		set_Value (COLUMNNAME_IsCreateAsActive, Boolean.valueOf(IsCreateAsActive));
	}

	/** Get Create As Active.
		@return Create Asset and activate it
	  */
	public boolean isCreateAsActive () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreateAsActive);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Depreciate.
		@param IsDepreciated 
		The asset will be depreciated
	  */
	public void setIsDepreciated (boolean IsDepreciated)
	{
		set_Value (COLUMNNAME_IsDepreciated, Boolean.valueOf(IsDepreciated));
	}

	/** Get Depreciate.
		@return The asset will be depreciated
	  */
	public boolean isDepreciated () 
	{
		Object oo = get_Value(COLUMNNAME_IsDepreciated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set One Asset Per UOM.
		@param IsOneAssetPerUOM 
		Create one asset per UOM
	  */
	public void setIsOneAssetPerUOM (boolean IsOneAssetPerUOM)
	{
		set_Value (COLUMNNAME_IsOneAssetPerUOM, Boolean.valueOf(IsOneAssetPerUOM));
	}

	/** Get One Asset Per UOM.
		@return Create one asset per UOM
	  */
	public boolean isOneAssetPerUOM () 
	{
		Object oo = get_Value(COLUMNNAME_IsOneAssetPerUOM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Owned.
		@param IsOwned 
		The asset is owned by the organization
	  */
	public void setIsOwned (boolean IsOwned)
	{
		set_Value (COLUMNNAME_IsOwned, Boolean.valueOf(IsOwned));
	}

	/** Get Owned.
		@return The asset is owned by the organization
	  */
	public boolean isOwned () 
	{
		Object oo = get_Value(COLUMNNAME_IsOwned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Track Issues.
		@param IsTrackIssues 
		Enable tracking issues for this asset
	  */
	public void setIsTrackIssues (boolean IsTrackIssues)
	{
		set_Value (COLUMNNAME_IsTrackIssues, Boolean.valueOf(IsTrackIssues));
	}

	/** Get Track Issues.
		@return Enable tracking issues for this asset
	  */
	public boolean isTrackIssues () 
	{
		Object oo = get_Value(COLUMNNAME_IsTrackIssues);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** MetodoDepre AD_Reference_ID=1200016 */
	public static final int METODODEPRE_AD_Reference_ID=1200016;
	/** None = N */
	public static final String METODODEPRE_None = "N";
	/** Straight line = L */
	public static final String METODODEPRE_StraightLine = "L";
	/** Accederated = A */
	public static final String METODODEPRE_Accederated = "A";
	/** Set Depreciation Method.
		@param MetodoDepre Depreciation Method	  */
	public void setMetodoDepre (String MetodoDepre)
	{

		if (MetodoDepre == null || MetodoDepre.equals("N") || MetodoDepre.equals("L") || MetodoDepre.equals("A")); else throw new IllegalArgumentException ("MetodoDepre Invalid value - " + MetodoDepre + " - Reference_ID=1200016 - N - L - A");		set_Value (COLUMNNAME_MetodoDepre, MetodoDepre);
	}

	/** Get Depreciation Method.
		@return Depreciation Method	  */
	public String getMetodoDepre () 
	{
		return (String)get_Value(COLUMNNAME_MetodoDepre);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}