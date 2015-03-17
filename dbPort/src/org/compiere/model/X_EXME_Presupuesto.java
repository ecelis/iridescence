/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Presupuesto
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Presupuesto extends PO implements I_EXME_Presupuesto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Presupuesto (Properties ctx, int EXME_Presupuesto_ID, String trxName)
    {
      super (ctx, EXME_Presupuesto_ID, trxName);
      /** if (EXME_Presupuesto_ID == 0)
        {
			setEXME_ConceptoFin_ID (0);
			setEXME_Presupuesto_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Presupuesto (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Presupuesto[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ConceptoFin getEXME_ConceptoFin() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConceptoFin.Table_Name);
        I_EXME_ConceptoFin result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConceptoFin)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConceptoFin_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Financial Concept.
		@param EXME_ConceptoFin_ID Financial Concept	  */
	public void setEXME_ConceptoFin_ID (int EXME_ConceptoFin_ID)
	{
		if (EXME_ConceptoFin_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConceptoFin_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConceptoFin_ID, Integer.valueOf(EXME_ConceptoFin_ID));
	}

	/** Get Financial Concept.
		@return Financial Concept	  */
	public int getEXME_ConceptoFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConceptoFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budget.
		@param EXME_Presupuesto_ID Budget	  */
	public void setEXME_Presupuesto_ID (int EXME_Presupuesto_ID)
	{
		if (EXME_Presupuesto_ID < 1)
			 throw new IllegalArgumentException ("EXME_Presupuesto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Presupuesto_ID, Integer.valueOf(EXME_Presupuesto_ID));
	}

	/** Get Budget.
		@return Budget	  */
	public int getEXME_Presupuesto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Presupuesto_ID);
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