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

/** Generated Model for AD_WF_Block
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_WF_Block extends PO implements I_AD_WF_Block, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_WF_Block (Properties ctx, int AD_WF_Block_ID, String trxName)
    {
      super (ctx, AD_WF_Block_ID, trxName);
      /** if (AD_WF_Block_ID == 0)
        {
			setAD_WF_Block_ID (0);
			setAD_Workflow_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_WF_Block (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_WF_Block[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Workflow Block.
		@param AD_WF_Block_ID 
		Workflow Transaction Execution Block
	  */
	public void setAD_WF_Block_ID (int AD_WF_Block_ID)
	{
		if (AD_WF_Block_ID < 1)
			 throw new IllegalArgumentException ("AD_WF_Block_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_WF_Block_ID, Integer.valueOf(AD_WF_Block_ID));
	}

	/** Get Workflow Block.
		@return Workflow Transaction Execution Block
	  */
	public int getAD_WF_Block_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Block_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Workflow getAD_Workflow() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Workflow.Table_Name);
        I_AD_Workflow result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Workflow)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Workflow_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1)
			 throw new IllegalArgumentException ("AD_Workflow_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
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