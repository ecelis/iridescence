/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProcedureTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProcedureTypeDet extends PO implements I_EXME_ProcedureTypeDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcedureTypeDet (Properties ctx, int EXME_ProcedureTypeDet_ID, String trxName)
    {
      super (ctx, EXME_ProcedureTypeDet_ID, trxName);
      /** if (EXME_ProcedureTypeDet_ID == 0)
        {
			setEXME_Intervencion_ID (0);
			setEXME_ProcedureTypeDet_ID (0);
			setEXME_ProcedureType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcedureTypeDet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcedureTypeDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CPT Code.
		@param EXME_Intervencion_ID 
		Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Intervencion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get CPT Code.
		@return Current Procedural Terminology (CPT) 
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Procedures Version.
		@param EXME_ProceduresVersion_ID Procedures Version	  */
	public void setEXME_ProceduresVersion_ID (int EXME_ProceduresVersion_ID)
	{
		if (EXME_ProceduresVersion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProceduresVersion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProceduresVersion_ID, Integer.valueOf(EXME_ProceduresVersion_ID));
	}

	/** Get Procedures Version.
		@return Procedures Version	  */
	public int getEXME_ProceduresVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProceduresVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Procedure Type Detail.
		@param EXME_ProcedureTypeDet_ID Procedure Type Detail	  */
	public void setEXME_ProcedureTypeDet_ID (int EXME_ProcedureTypeDet_ID)
	{
		if (EXME_ProcedureTypeDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedureTypeDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcedureTypeDet_ID, Integer.valueOf(EXME_ProcedureTypeDet_ID));
	}

	/** Get Procedure Type Detail.
		@return Procedure Type Detail	  */
	public int getEXME_ProcedureTypeDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedureTypeDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProcedureType getEXME_ProcedureType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProcedureType.Table_Name);
        I_EXME_ProcedureType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProcedureType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProcedureType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Procedure Type.
		@param EXME_ProcedureType_ID Procedure Type	  */
	public void setEXME_ProcedureType_ID (int EXME_ProcedureType_ID)
	{
		if (EXME_ProcedureType_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedureType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcedureType_ID, Integer.valueOf(EXME_ProcedureType_ID));
	}

	/** Get Procedure Type.
		@return Procedure Type	  */
	public int getEXME_ProcedureType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedureType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}