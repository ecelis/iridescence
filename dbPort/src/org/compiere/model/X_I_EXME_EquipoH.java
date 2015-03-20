/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_EquipoH
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_EquipoH extends PO implements I_I_EXME_EquipoH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_EquipoH (Properties ctx, int I_EXME_EquipoH_ID, String trxName)
    {
      super (ctx, I_EXME_EquipoH_ID, trxName);
      /** if (I_EXME_EquipoH_ID == 0)
        {
			setEstatus_Equipo (null);
// M
			setI_EXME_EquipoH_ID (0);
			setI_IsImported (false);
// N
        } */
    }

    /** Load Constructor */
    public X_I_EXME_EquipoH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_EquipoH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Equipment code.
		@param Codigo_Equipo Equipment code	  */
	public void setCodigo_Equipo (String Codigo_Equipo)
	{
		set_Value (COLUMNNAME_Codigo_Equipo, Codigo_Equipo);
	}

	/** Get Equipment code.
		@return Equipment code	  */
	public String getCodigo_Equipo () 
	{
		return (String)get_Value(COLUMNNAME_Codigo_Equipo);
	}

	/** Estatus_Equipo AD_Reference_ID=1200113 */
	public static final int ESTATUS_EQUIPO_AD_Reference_ID=1200113;
	/** Sacheduled = P */
	public static final String ESTATUS_EQUIPO_Sacheduled = "P";
	/** Used = U */
	public static final String ESTATUS_EQUIPO_Used = "U";
	/** Maintenance = M */
	public static final String ESTATUS_EQUIPO_Maintenance = "M";
	/** Available = D */
	public static final String ESTATUS_EQUIPO_Available = "D";
	/** Ordered = S */
	public static final String ESTATUS_EQUIPO_Ordered = "S";
	/** Cancelled = C */
	public static final String ESTATUS_EQUIPO_Cancelled = "C";
	/** Set Equipment Status.
		@param Estatus_Equipo Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo)
	{
		if (Estatus_Equipo == null) throw new IllegalArgumentException ("Estatus_Equipo is mandatory");
		if (Estatus_Equipo.equals("P") || Estatus_Equipo.equals("U") || Estatus_Equipo.equals("M") || Estatus_Equipo.equals("D") || Estatus_Equipo.equals("S") || Estatus_Equipo.equals("C")); else throw new IllegalArgumentException ("Estatus_Equipo Invalid value - " + Estatus_Equipo + " - Reference_ID=1200113 - P - U - M - D - S - C");		set_Value (COLUMNNAME_Estatus_Equipo, Estatus_Equipo);
	}

	/** Get Equipment Status.
		@return Equipment Status	  */
	public String getEstatus_Equipo () 
	{
		return (String)get_Value(COLUMNNAME_Estatus_Equipo);
	}

	public I_EXME_Area getEXME_Area() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Area.Table_Name);
        I_EXME_Area result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Area)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Area_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Area.
		@param EXME_Area_ID 
		Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1) 
			set_Value (COLUMNNAME_EXME_Area_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Area.
		@return Area
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1) 
			set_Value (COLUMNNAME_EXME_Equipo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (String Fecha_Fin)
	{
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public String getFecha_Fin () 
	{
		return (String)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (String Fecha_Ini)
	{
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public String getFecha_Ini () 
	{
		return (String)get_Value(COLUMNNAME_Fecha_Ini);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_EXME_EquipoH_ID.
		@param I_EXME_EquipoH_ID I_EXME_EquipoH_ID	  */
	public void setI_EXME_EquipoH_ID (int I_EXME_EquipoH_ID)
	{
		if (I_EXME_EquipoH_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_EquipoH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_EquipoH_ID, Integer.valueOf(I_EXME_EquipoH_ID));
	}

	/** Get I_EXME_EquipoH_ID.
		@return I_EXME_EquipoH_ID	  */
	public int getI_EXME_EquipoH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_EquipoH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}