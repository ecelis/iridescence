/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_InvestigacionRel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_InvestigacionRel extends PO implements I_EXME_InvestigacionRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InvestigacionRel (Properties ctx, int EXME_InvestigacionRel_ID, String trxName)
    {
      super (ctx, EXME_InvestigacionRel_ID, trxName);
      /** if (EXME_InvestigacionRel_ID == 0)
        {
			setEXME_InvestigacionRel_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_InvestigacionRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InvestigacionRel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Research Relation.
		@param EXME_InvestigacionRel_ID 
		Project and Program Research Relation
	  */
	public void setEXME_InvestigacionRel_ID (int EXME_InvestigacionRel_ID)
	{
		if (EXME_InvestigacionRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_InvestigacionRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InvestigacionRel_ID, Integer.valueOf(EXME_InvestigacionRel_ID));
	}

	/** Get Research Relation.
		@return Project and Program Research Relation
	  */
	public int getEXME_InvestigacionRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InvestigacionRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_EXME_ProgramaInv_ID, null);
		else 
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

	public I_EXME_ProyectoInv getEXME_ProyectoInv() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProyectoInv.Table_Name);
        I_EXME_ProyectoInv result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProyectoInv)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProyectoInv_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Research Project.
		@param EXME_ProyectoInv_ID Research Project	  */
	public void setEXME_ProyectoInv_ID (int EXME_ProyectoInv_ID)
	{
		if (EXME_ProyectoInv_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProyectoInv_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProyectoInv_ID, Integer.valueOf(EXME_ProyectoInv_ID));
	}

	/** Get Research Project.
		@return Research Project	  */
	public int getEXME_ProyectoInv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProyectoInv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null)
			throw new IllegalArgumentException ("Type is mandatory.");
		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}