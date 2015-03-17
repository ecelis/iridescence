/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_PaqBase_BP
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_PaqBase_BP extends PO implements I_I_EXME_PaqBase_BP, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_PaqBase_BP (Properties ctx, int I_EXME_PaqBase_BP_ID, String trxName)
    {
      super (ctx, I_EXME_PaqBase_BP_ID, trxName);
      /** if (I_EXME_PaqBase_BP_ID == 0)
        {
			setI_EXME_PaqBase_BP_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_PaqBase_BP (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_PaqBase_BP[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Business Partner Value.
		@param C_BPartner_Value Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value)
	{
		set_Value (COLUMNNAME_C_BPartner_Value, C_BPartner_Value);
	}

	/** Get Business Partner Value.
		@return Business Partner Value	  */
	public String getC_BPartner_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_Value);
	}

	public I_EXME_PaqBase_BP getEXME_PaqBase_BP() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_BP.Table_Name);
        I_EXME_PaqBase_BP result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_BP)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_BP_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner - Base Package.
		@param EXME_PaqBase_BP_ID 
		Business Partner - Base Package
	  */
	public void setEXME_PaqBase_BP_ID (int EXME_PaqBase_BP_ID)
	{
		if (EXME_PaqBase_BP_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_BP_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_BP_ID, Integer.valueOf(EXME_PaqBase_BP_ID));
	}

	/** Get Business Partner - Base Package.
		@return Business Partner - Base Package
	  */
	public int getEXME_PaqBase_BP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_BP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Base Package Version Name.
		@param EXME_PaqBase_Version_Name 
		Base Package Version Name
	  */
	public void setEXME_PaqBase_Version_Name (String EXME_PaqBase_Version_Name)
	{
		set_Value (COLUMNNAME_EXME_PaqBase_Version_Name, EXME_PaqBase_Version_Name);
	}

	/** Get Base Package Version Name.
		@return Base Package Version Name
	  */
	public String getEXME_PaqBase_Version_Name () 
	{
		return (String)get_Value(COLUMNNAME_EXME_PaqBase_Version_Name);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import Business Partner - Base Package.
		@param I_EXME_PaqBase_BP_ID 
		Business Partner - Base Package
	  */
	public void setI_EXME_PaqBase_BP_ID (int I_EXME_PaqBase_BP_ID)
	{
		if (I_EXME_PaqBase_BP_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_PaqBase_BP_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_PaqBase_BP_ID, Integer.valueOf(I_EXME_PaqBase_BP_ID));
	}

	/** Get Import Business Partner - Base Package.
		@return Business Partner - Base Package
	  */
	public int getI_EXME_PaqBase_BP_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_PaqBase_BP_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}