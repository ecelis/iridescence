/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ProyectoInv
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProyectoInv extends PO implements I_EXME_ProyectoInv, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProyectoInv (Properties ctx, int EXME_ProyectoInv_ID, String trxName)
    {
      super (ctx, EXME_ProyectoInv_ID, trxName);
      /** if (EXME_ProyectoInv_ID == 0)
        {
			setEXME_ProyectoInv_ID (0);
			setEXME_TipoProyecto_ID (0);
			setIsApproved (false);
			setIsEvaluated (false);
			setIsProgrammed (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProyectoInv (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProyectoInv[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Deliverable.
		@param Entregable Deliverable	  */
	public void setEntregable (String Entregable)
	{
		set_Value (COLUMNNAME_Entregable, Entregable);
	}

	/** Get Deliverable.
		@return Deliverable	  */
	public String getEntregable () 
	{
		return (String)get_Value(COLUMNNAME_Entregable);
	}

	/** Set Research Project.
		@param EXME_ProyectoInv_ID Research Project	  */
	public void setEXME_ProyectoInv_ID (int EXME_ProyectoInv_ID)
	{
		if (EXME_ProyectoInv_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProyectoInv_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProyectoInv_ID, Integer.valueOf(EXME_ProyectoInv_ID));
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

	public I_EXME_TipoProyecto getEXME_TipoProyecto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoProyecto.Table_Name);
        I_EXME_TipoProyecto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoProyecto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoProyecto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Project Type.
		@param EXME_TipoProyecto_ID Project Type	  */
	public void setEXME_TipoProyecto_ID (int EXME_TipoProyecto_ID)
	{
		if (EXME_TipoProyecto_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProyecto_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProyecto_ID, Integer.valueOf(EXME_TipoProyecto_ID));
	}

	/** Get Project Type.
		@return Project Type	  */
	public int getEXME_TipoProyecto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProyecto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Initial Date.
		@param FechaIni 
		Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni)
	{
		set_Value (COLUMNNAME_FechaIni, FechaIni);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFechaIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIni);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Evaluated.
		@param IsEvaluated Is Evaluated	  */
	public void setIsEvaluated (boolean IsEvaluated)
	{
		set_Value (COLUMNNAME_IsEvaluated, Boolean.valueOf(IsEvaluated));
	}

	/** Get Is Evaluated.
		@return Is Evaluated	  */
	public boolean isEvaluated () 
	{
		Object oo = get_Value(COLUMNNAME_IsEvaluated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set is Programmed.
		@param IsProgrammed is Programmed	  */
	public void setIsProgrammed (boolean IsProgrammed)
	{
		set_Value (COLUMNNAME_IsProgrammed, Boolean.valueOf(IsProgrammed));
	}

	/** Get is Programmed.
		@return is Programmed	  */
	public boolean isProgrammed () 
	{
		Object oo = get_Value(COLUMNNAME_IsProgrammed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Result.
		@param Result 
		Result of the action taken
	  */
	public void setResult (String Result)
	{
		set_Value (COLUMNNAME_Result, Result);
	}

	/** Get Result.
		@return Result of the action taken
	  */
	public String getResult () 
	{
		return (String)get_Value(COLUMNNAME_Result);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}