/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProgramaInvLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProgramaInvLine extends PO implements I_EXME_ProgramaInvLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProgramaInvLine (Properties ctx, int EXME_ProgramaInvLine_ID, String trxName)
    {
      super (ctx, EXME_ProgramaInvLine_ID, trxName);
      /** if (EXME_ProgramaInvLine_ID == 0)
        {
			setDescription (null);
			setEXME_ProgramaInv_ID (0);
			setEXME_ProgramaInvLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProgramaInvLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProgramaInvLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgramaInv.Table_Name);
        I_EXME_ProgramaInv result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgramaInv)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgramaInv_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Research Program.
		@param EXME_ProgramaInv_ID Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID)
	{
		if (EXME_ProgramaInv_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgramaInv_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProgramaInv_ID, Integer.valueOf(EXME_ProgramaInv_ID));
	}

	/** Get Research Program.
		@return Research Program	  */
	public int getEXME_ProgramaInv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgramaInv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Research Program Detail.
		@param EXME_ProgramaInvLine_ID Research Program Detail	  */
	public void setEXME_ProgramaInvLine_ID (int EXME_ProgramaInvLine_ID)
	{
		if (EXME_ProgramaInvLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgramaInvLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProgramaInvLine_ID, Integer.valueOf(EXME_ProgramaInvLine_ID));
	}

	/** Get Research Program Detail.
		@return Research Program Detail	  */
	public int getEXME_ProgramaInvLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgramaInvLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}