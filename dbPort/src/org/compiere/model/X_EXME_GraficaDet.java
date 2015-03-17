/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_GraficaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_GraficaDet extends PO implements I_EXME_GraficaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GraficaDet (Properties ctx, int EXME_GraficaDet_ID, String trxName)
    {
      super (ctx, EXME_GraficaDet_ID, trxName);
      /** if (EXME_GraficaDet_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_GraficaDet_ID (0);
			setEXME_Grafica_ID (0);
			setEXME_SignoVital_ID (0);
			setIntervalos (Env.ZERO);
			setLim_Inferior (Env.ZERO);
			setLim_Superior (Env.ZERO);
			setSecuencia (Env.ZERO);
			setTitle (null);
			setUbicacion (null);
			setValue (null);
			setVisible (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_GraficaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GraficaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Dividers.
		@param DividerNum Number of Dividers	  */
	public void setDividerNum (BigDecimal DividerNum)
	{
		set_Value (COLUMNNAME_DividerNum, DividerNum);
	}

	/** Get Number of Dividers.
		@return Number of Dividers	  */
	public BigDecimal getDividerNum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DividerNum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Graphic Detail.
		@param EXME_GraficaDet_ID Graphic Detail	  */
	public void setEXME_GraficaDet_ID (int EXME_GraficaDet_ID)
	{
		if (EXME_GraficaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_GraficaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GraficaDet_ID, Integer.valueOf(EXME_GraficaDet_ID));
	}

	/** Get Graphic Detail.
		@return Graphic Detail	  */
	public int getEXME_GraficaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GraficaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Grafica getEXME_Grafica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grafica.Table_Name);
        I_EXME_Grafica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grafica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grafica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Graphic.
		@param EXME_Grafica_ID Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID)
	{
		if (EXME_Grafica_ID < 1)
			 throw new IllegalArgumentException ("EXME_Grafica_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Grafica_ID, Integer.valueOf(EXME_Grafica_ID));
	}

	/** Get Graphic.
		@return Graphic	  */
	public int getEXME_Grafica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grafica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SignoVital.Table_Name);
        I_EXME_SignoVital result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SignoVital)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SignoVital_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Sign.
		@param EXME_SignoVital_ID Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID)
	{
		if (EXME_SignoVital_ID < 1)
			 throw new IllegalArgumentException ("EXME_SignoVital_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SignoVital_ID, Integer.valueOf(EXME_SignoVital_ID));
	}

	/** Get Vital Sign.
		@return Vital Sign	  */
	public int getEXME_SignoVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Intervals.
		@param Intervalos 
		Intervals
	  */
	public void setIntervalos (BigDecimal Intervalos)
	{
		if (Intervalos == null)
			throw new IllegalArgumentException ("Intervalos is mandatory.");
		set_Value (COLUMNNAME_Intervalos, Intervalos);
	}

	/** Get Intervals.
		@return Intervals
	  */
	public BigDecimal getIntervalos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set X Axis.
		@param IsXAxis X Axis	  */
	public void setIsXAxis (boolean IsXAxis)
	{
		set_Value (COLUMNNAME_IsXAxis, Boolean.valueOf(IsXAxis));
	}

	/** Get X Axis.
		@return X Axis	  */
	public boolean isXAxis () 
	{
		Object oo = get_Value(COLUMNNAME_IsXAxis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Y Axis.
		@param IsYAxis Y Axis	  */
	public void setIsYAxis (boolean IsYAxis)
	{
		set_Value (COLUMNNAME_IsYAxis, Boolean.valueOf(IsYAxis));
	}

	/** Get Y Axis.
		@return Y Axis	  */
	public boolean isYAxis () 
	{
		Object oo = get_Value(COLUMNNAME_IsYAxis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Minimum Level.
		@param Lim_Inferior Minimum Level	  */
	public void setLim_Inferior (BigDecimal Lim_Inferior)
	{
		if (Lim_Inferior == null)
			throw new IllegalArgumentException ("Lim_Inferior is mandatory.");
		set_Value (COLUMNNAME_Lim_Inferior, Lim_Inferior);
	}

	/** Get Minimum Level.
		@return Minimum Level	  */
	public BigDecimal getLim_Inferior () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Lim_Inferior);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximum Level.
		@param Lim_Superior Maximum Level	  */
	public void setLim_Superior (BigDecimal Lim_Superior)
	{
		if (Lim_Superior == null)
			throw new IllegalArgumentException ("Lim_Superior is mandatory.");
		set_Value (COLUMNNAME_Lim_Superior, Lim_Superior);
	}

	/** Get Maximum Level.
		@return Maximum Level	  */
	public BigDecimal getLim_Superior () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Lim_Superior);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
		if (Secuencia == null)
			throw new IllegalArgumentException ("Secuencia is mandatory.");
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		if (Title == null)
			throw new IllegalArgumentException ("Title is mandatory.");
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}

	/** Ubicacion AD_Reference_ID=1200470 */
	public static final int UBICACION_AD_Reference_ID=1200470;
	/** Bottom or Right = BR */
	public static final String UBICACION_BottomOrRight = "BR";
	/** Bottom or Left = BL */
	public static final String UBICACION_BottomOrLeft = "BL";
	/** Top or Right = TR */
	public static final String UBICACION_TopOrRight = "TR";
	/** Top or Left = TL */
	public static final String UBICACION_TopOrLeft = "TL";
	/** Set Location of Element.
		@param Ubicacion 
		Location of Element
	  */
	public void setUbicacion (String Ubicacion)
	{
		if (Ubicacion == null) throw new IllegalArgumentException ("Ubicacion is mandatory");
		if (Ubicacion.equals("BR") || Ubicacion.equals("BL") || Ubicacion.equals("TR") || Ubicacion.equals("TL")); else throw new IllegalArgumentException ("Ubicacion Invalid value - " + Ubicacion + " - Reference_ID=1200470 - BR - BL - TR - TL");		set_Value (COLUMNNAME_Ubicacion, Ubicacion);
	}

	/** Get Location of Element.
		@return Location of Element
	  */
	public String getUbicacion () 
	{
		return (String)get_Value(COLUMNNAME_Ubicacion);
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

	/** Set Visible.
		@param Visible Visible	  */
	public void setVisible (boolean Visible)
	{
		set_Value (COLUMNNAME_Visible, Boolean.valueOf(Visible));
	}

	/** Get Visible.
		@return Visible	  */
	public boolean isVisible () 
	{
		Object oo = get_Value(COLUMNNAME_Visible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}