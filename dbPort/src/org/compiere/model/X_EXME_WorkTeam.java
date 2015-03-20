/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_WorkTeam
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_WorkTeam extends PO implements I_EXME_WorkTeam, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_WorkTeam (Properties ctx, int EXME_WorkTeam_ID, String trxName)
    {
      super (ctx, EXME_WorkTeam_ID, trxName);
      /** if (EXME_WorkTeam_ID == 0)
        {
			setEXME_TeamMember_ID (0);
			setEXME_WorkTeam_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_WorkTeam (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_WorkTeam[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_TeamMember getEXME_TeamMember() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TeamMember.Table_Name);
        I_EXME_TeamMember result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TeamMember)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TeamMember_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Team Member.
		@param EXME_TeamMember_ID 
		Work Team Member
	  */
	public void setEXME_TeamMember_ID (int EXME_TeamMember_ID)
	{
		if (EXME_TeamMember_ID < 1)
			 throw new IllegalArgumentException ("EXME_TeamMember_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TeamMember_ID, Integer.valueOf(EXME_TeamMember_ID));
	}

	/** Get Team Member.
		@return Work Team Member
	  */
	public int getEXME_TeamMember_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TeamMember_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Work Team.
		@param EXME_WorkTeam_ID Work Team	  */
	public void setEXME_WorkTeam_ID (int EXME_WorkTeam_ID)
	{
		if (EXME_WorkTeam_ID < 1)
			 throw new IllegalArgumentException ("EXME_WorkTeam_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_WorkTeam_ID, Integer.valueOf(EXME_WorkTeam_ID));
	}

	/** Get Work Team.
		@return Work Team	  */
	public int getEXME_WorkTeam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_WorkTeam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200110 */
	public static final int TYPE_AD_Reference_ID=1200110;
	/** Project = PY */
	public static final String TYPE_Project = "PY";
	/** Program = PG */
	public static final String TYPE_Program = "PG";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("PY") || Type.equals("PG")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200110 - PY - PG");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}