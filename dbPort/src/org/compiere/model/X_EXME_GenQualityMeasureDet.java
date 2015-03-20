/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_GenQualityMeasureDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_GenQualityMeasureDet extends PO implements I_EXME_GenQualityMeasureDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GenQualityMeasureDet (Properties ctx, int EXME_GenQualityMeasureDet_ID, String trxName)
    {
      super (ctx, EXME_GenQualityMeasureDet_ID, trxName);
      /** if (EXME_GenQualityMeasureDet_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_GenQualityMeasureDet_ID (0);
			setEXME_GenQualityMeasure_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_GenQualityMeasureDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GenQualityMeasureDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail of Generated Quality Measure.
		@param EXME_GenQualityMeasureDet_ID Detail of Generated Quality Measure	  */
	public void setEXME_GenQualityMeasureDet_ID (int EXME_GenQualityMeasureDet_ID)
	{
		if (EXME_GenQualityMeasureDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenQualityMeasureDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GenQualityMeasureDet_ID, Integer.valueOf(EXME_GenQualityMeasureDet_ID));
	}

	/** Get Detail of Generated Quality Measure.
		@return Detail of Generated Quality Measure	  */
	public int getEXME_GenQualityMeasureDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenQualityMeasureDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GenQualityMeasure getEXME_GenQualityMeasure() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenQualityMeasure.Table_Name);
        I_EXME_GenQualityMeasure result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenQualityMeasure)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenQualityMeasure_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generated Quality Measure.
		@param EXME_GenQualityMeasure_ID Generated Quality Measure	  */
	public void setEXME_GenQualityMeasure_ID (int EXME_GenQualityMeasure_ID)
	{
		if (EXME_GenQualityMeasure_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenQualityMeasure_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GenQualityMeasure_ID, Integer.valueOf(EXME_GenQualityMeasure_ID));
	}

	/** Get Generated Quality Measure.
		@return Generated Quality Measure	  */
	public int getEXME_GenQualityMeasure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenQualityMeasure_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Tipo_Reporte AD_Reference_ID=1200495 */
	public static final int TIPO_REPORTE_AD_Reference_ID=1200495;
	/** Numerator = N */
	public static final String TIPO_REPORTE_Numerator = "N";
	/** Denominator = D */
	public static final String TIPO_REPORTE_Denominator = "D";
	/** Exclusion = E */
	public static final String TIPO_REPORTE_Exclusion = "E";
	/** Denominator A = DA */
	public static final String TIPO_REPORTE_DenominatorA = "DA";
	/** Denominator B = DB */
	public static final String TIPO_REPORTE_DenominatorB = "DB";
	/** Denominator C = DC */
	public static final String TIPO_REPORTE_DenominatorC = "DC";
	/** Set Grouped by.
		@param Tipo_Reporte 
		Grouped by
	  */
	public void setTipo_Reporte (String Tipo_Reporte)
	{

		if (Tipo_Reporte == null || Tipo_Reporte.equals("N") || Tipo_Reporte.equals("D") || Tipo_Reporte.equals("E") || Tipo_Reporte.equals("DA") || Tipo_Reporte.equals("DB") || Tipo_Reporte.equals("DC")); else throw new IllegalArgumentException ("Tipo_Reporte Invalid value - " + Tipo_Reporte + " - Reference_ID=1200495 - N - D - E - DA - DB - DC");		set_Value (COLUMNNAME_Tipo_Reporte, Tipo_Reporte);
	}

	/** Get Grouped by.
		@return Grouped by
	  */
	public String getTipo_Reporte () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Reporte);
	}
}