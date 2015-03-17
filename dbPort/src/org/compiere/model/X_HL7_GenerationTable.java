/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for HL7_GenerationTable
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_GenerationTable extends PO implements I_HL7_GenerationTable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_GenerationTable (Properties ctx, int HL7_GenerationTable_ID, String trxName)
    {
      super (ctx, HL7_GenerationTable_ID, trxName);
      /** if (HL7_GenerationTable_ID == 0)
        {
			setAD_Table_ID (0);
			setHL7_GenerationTable_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_GenerationTable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_GenerationTable[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
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

	/** Set Message Generation Table.
		@param HL7_GenerationTable_ID Message Generation Table	  */
	public void setHL7_GenerationTable_ID (int HL7_GenerationTable_ID)
	{
		if (HL7_GenerationTable_ID < 1)
			 throw new IllegalArgumentException ("HL7_GenerationTable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_GenerationTable_ID, Integer.valueOf(HL7_GenerationTable_ID));
	}

	/** Get Message Generation Table.
		@return Message Generation Table	  */
	public int getHL7_GenerationTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_GenerationTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}