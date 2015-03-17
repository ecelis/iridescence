/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Rel_Source_Trans
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Rel_Source_Trans extends PO implements I_EXME_Rel_Source_Trans, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Rel_Source_Trans (Properties ctx, int EXME_Rel_Source_Trans_ID, String trxName)
    {
      super (ctx, EXME_Rel_Source_Trans_ID, trxName);
      /** if (EXME_Rel_Source_Trans_ID == 0)
        {
			setC_DocType_ID (0);
			setEXME_Rel_Source_Trans_ID (0);
			setEXME_Source_Code_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Rel_Source_Trans (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Rel_Source_Trans[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_DocType getC_DocType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
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

	/** Set Source Relationship and Inventories Transaction.
		@param EXME_Rel_Source_Trans_ID 
		Source Relationship and Inventories Transaction
	  */
	public void setEXME_Rel_Source_Trans_ID (int EXME_Rel_Source_Trans_ID)
	{
		if (EXME_Rel_Source_Trans_ID < 1)
			 throw new IllegalArgumentException ("EXME_Rel_Source_Trans_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Rel_Source_Trans_ID, Integer.valueOf(EXME_Rel_Source_Trans_ID));
	}

	/** Get Source Relationship and Inventories Transaction.
		@return Source Relationship and Inventories Transaction
	  */
	public int getEXME_Rel_Source_Trans_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Rel_Source_Trans_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Source_Code getEXME_Source_Code() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Source_Code.Table_Name);
        I_EXME_Source_Code result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Source_Code)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Source_Code_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Codigo Fuente.
		@param EXME_Source_Code_ID 
		Codigo Fuente
	  */
	public void setEXME_Source_Code_ID (int EXME_Source_Code_ID)
	{
		if (EXME_Source_Code_ID < 1)
			 throw new IllegalArgumentException ("EXME_Source_Code_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Source_Code_ID, Integer.valueOf(EXME_Source_Code_ID));
	}

	/** Get Codigo Fuente.
		@return Codigo Fuente
	  */
	public int getEXME_Source_Code_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Source_Code_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}