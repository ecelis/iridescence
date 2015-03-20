/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PrintedFormatHdr
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PrintedFormatHdr extends PO implements I_EXME_PrintedFormatHdr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrintedFormatHdr (Properties ctx, int EXME_PrintedFormatHdr_ID, String trxName)
    {
      super (ctx, EXME_PrintedFormatHdr_ID, trxName);
      /** if (EXME_PrintedFormatHdr_ID == 0)
        {
			setEXME_FormSectionHeader_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_PrintedFormatHdr_ID (0);
			setIsComplete (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_PrintedFormatHdr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrintedFormatHdr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FormSectionHeader.Table_Name);
        I_EXME_FormSectionHeader result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FormSectionHeader)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FormSectionHeader_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Section's Header.
		@param EXME_FormSectionHeader_ID Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID)
	{
		if (EXME_FormSectionHeader_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormSectionHeader_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FormSectionHeader_ID, Integer.valueOf(EXME_FormSectionHeader_ID));
	}

	/** Get Form Section's Header.
		@return Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormSectionHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Printed Format Header.
		@param EXME_PrintedFormatHdr_ID Printed Format Header	  */
	public void setEXME_PrintedFormatHdr_ID (int EXME_PrintedFormatHdr_ID)
	{
		if (EXME_PrintedFormatHdr_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrintedFormatHdr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrintedFormatHdr_ID, Integer.valueOf(EXME_PrintedFormatHdr_ID));
	}

	/** Get Printed Format Header.
		@return Printed Format Header	  */
	public int getEXME_PrintedFormatHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrintedFormatHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Complete.
		@param IsComplete 
		It is complete
	  */
	public void setIsComplete (boolean IsComplete)
	{
		set_Value (COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
	}

	/** Get Complete.
		@return It is complete
	  */
	public boolean isComplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsComplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}