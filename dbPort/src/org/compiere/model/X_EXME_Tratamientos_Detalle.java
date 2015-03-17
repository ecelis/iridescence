/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Tratamientos_Detalle
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Tratamientos_Detalle extends PO implements I_EXME_Tratamientos_Detalle, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Tratamientos_Detalle (Properties ctx, int EXME_Tratamientos_Detalle_ID, String trxName)
    {
      super (ctx, EXME_Tratamientos_Detalle_ID, trxName);
      /** if (EXME_Tratamientos_Detalle_ID == 0)
        {
			setDuracion (0);
// 1
			setEXME_Tratamientos_Detalle_ID (0);
			setEXME_Tratamientos_ID (0);
			setIntervalo (null);
			setName (null);
			setSessionNo (0);
			setType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Tratamientos_Detalle (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Tratamientos_Detalle[")
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

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (int Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Integer.valueOf(Duracion));
	}

	/** Get Duration.
		@return Duration
	  */
	public int getDuracion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duracion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase.Table_Name);
        I_EXME_PaqBase result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Base Package.
		@param EXME_PaqBase_ID 
		Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID)
	{
		if (EXME_PaqBase_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_ID, Integer.valueOf(EXME_PaqBase_ID));
	}

	/** Get Base Package.
		@return Base Package
	  */
	public int getEXME_PaqBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatments Detail.
		@param EXME_Tratamientos_Detalle_ID Treatments Detail	  */
	public void setEXME_Tratamientos_Detalle_ID (int EXME_Tratamientos_Detalle_ID)
	{
		if (EXME_Tratamientos_Detalle_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_Detalle_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_Detalle_ID, Integer.valueOf(EXME_Tratamientos_Detalle_ID));
	}

	/** Get Treatments Detail.
		@return Treatments Detail	  */
	public int getEXME_Tratamientos_Detalle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Detalle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatments.
		@param EXME_Tratamientos_ID 
		Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID)
	{
		if (EXME_Tratamientos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Tratamientos_ID, Integer.valueOf(EXME_Tratamientos_ID));
	}

	/** Get Treatments.
		@return Treatments
	  */
	public int getEXME_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Indications.
		@param Indicaciones_Med Medical Indications	  */
	public void setIndicaciones_Med (String Indicaciones_Med)
	{
		set_Value (COLUMNNAME_Indicaciones_Med, Indicaciones_Med);
	}

	/** Get Medical Indications.
		@return Medical Indications	  */
	public String getIndicaciones_Med () 
	{
		return (String)get_Value(COLUMNNAME_Indicaciones_Med);
	}

	/** Intervalo AD_Reference_ID=1200485 */
	public static final int INTERVALO_AD_Reference_ID=1200485;
	/** Years = YR */
	public static final String INTERVALO_Years = "YR";
	/** Months = MT */
	public static final String INTERVALO_Months = "MT";
	/** Weeks = WK */
	public static final String INTERVALO_Weeks = "WK";
	/** Days = DY */
	public static final String INTERVALO_Days = "DY";
	/** Hours = HR */
	public static final String INTERVALO_Hours = "HR";
	/** Not Specified = SE */
	public static final String INTERVALO_NotSpecified = "SE";
	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (String Intervalo)
	{
		if (Intervalo == null) throw new IllegalArgumentException ("Intervalo is mandatory");
		if (Intervalo.equals("YR") || Intervalo.equals("MT") || Intervalo.equals("WK") || Intervalo.equals("DY") || Intervalo.equals("HR") || Intervalo.equals("SE")); else throw new IllegalArgumentException ("Intervalo Invalid value - " + Intervalo + " - Reference_ID=1200485 - YR - MT - WK - DY - HR - SE");		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public String getIntervalo () 
	{
		return (String)get_Value(COLUMNNAME_Intervalo);
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Quantity Range.
		@param QtyIntervalo Quantity Range	  */
	public void setQtyIntervalo (int QtyIntervalo)
	{
		set_Value (COLUMNNAME_QtyIntervalo, Integer.valueOf(QtyIntervalo));
	}

	/** Get Quantity Range.
		@return Quantity Range	  */
	public int getQtyIntervalo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QtyIntervalo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Session Number.
		@param SessionNo 
		Session Number of a treatment
	  */
	public void setSessionNo (int SessionNo)
	{
		set_Value (COLUMNNAME_SessionNo, Integer.valueOf(SessionNo));
	}

	/** Get Session Number.
		@return Session Number of a treatment
	  */
	public int getSessionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SessionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200483 */
	public static final int TYPE_AD_Reference_ID=1200483;
	/** Outpatient = CE */
	public static final String TYPE_Outpatient = "CE";
	/** Ambulatory = AB */
	public static final String TYPE_Ambulatory = "AB";
	/** Inpatient = HS */
	public static final String TYPE_Inpatient = "HS";
	/** Home = HM */
	public static final String TYPE_Home = "HM";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("CE") || Type.equals("AB") || Type.equals("HS") || Type.equals("HM")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200483 - CE - AB - HS - HM");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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