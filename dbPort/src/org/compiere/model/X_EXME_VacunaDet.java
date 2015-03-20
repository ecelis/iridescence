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

/** Generated Model for EXME_VacunaDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_VacunaDet extends PO implements I_EXME_VacunaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VacunaDet (Properties ctx, int EXME_VacunaDet_ID, String trxName)
    {
      super (ctx, EXME_VacunaDet_ID, trxName);
      /** if (EXME_VacunaDet_ID == 0)
        {
			setEdadMaxima (Env.ZERO);
			setEdadMinima (Env.ZERO);
			setEXME_VacunaDet_ID (0);
			setEXME_Vacuna_ID (0);
			setSecuencia (0);
// @SQL=SELECT NVL(MAX(Secuencia),0)+1 AS DefaultValue FROM EXME_VacunaDet WHERE EXME_Vacuna_ID=@EXME_Vacuna_ID@
			setTipoDosis (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_VacunaDet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - All 
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
      StringBuffer sb = new StringBuffer ("X_EXME_VacunaDet[")
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
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
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

	/** Set Maximum age.
		@param EdadMaxima Maximum age	  */
	public void setEdadMaxima (BigDecimal EdadMaxima)
	{
		if (EdadMaxima == null)
			throw new IllegalArgumentException ("EdadMaxima is mandatory.");
		set_Value (COLUMNNAME_EdadMaxima, EdadMaxima);
	}

	/** Get Maximum age.
		@return Maximum age	  */
	public BigDecimal getEdadMaxima () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadMaxima);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum age.
		@param EdadMinima Minimum age	  */
	public void setEdadMinima (BigDecimal EdadMinima)
	{
		if (EdadMinima == null)
			throw new IllegalArgumentException ("EdadMinima is mandatory.");
		set_Value (COLUMNNAME_EdadMinima, EdadMinima);
	}

	/** Get Minimum age.
		@return Minimum age	  */
	public BigDecimal getEdadMinima () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadMinima);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Detail immunization.
		@param EXME_VacunaDet_ID 
		Detail immunization
	  */
	public void setEXME_VacunaDet_ID (int EXME_VacunaDet_ID)
	{
		if (EXME_VacunaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_VacunaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_VacunaDet_ID, Integer.valueOf(EXME_VacunaDet_ID));
	}

	/** Get Detail immunization.
		@return Detail immunization
	  */
	public int getEXME_VacunaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VacunaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vaccine.
		@param EXME_Vacuna_ID 
		Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID)
	{
		if (EXME_Vacuna_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vacuna_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Vacuna_ID, Integer.valueOf(EXME_Vacuna_ID));
	}

	/** Get Vaccine.
		@return Vaccine
	  */
	public int getEXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (BigDecimal Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Intervalo);
	}

	/** Get Interval.
		@return Interval
	  */
	public BigDecimal getIntervalo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Intervalo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoDosis AD_Reference_ID=1200129 */
	public static final int TIPODOSIS_AD_Reference_ID=1200129;
	/** Only = UNI */
	public static final String TIPODOSIS_Only = "UNI";
	/** Preliminary = PRE */
	public static final String TIPODOSIS_Preliminary = "PRE";
	/** First = PRI */
	public static final String TIPODOSIS_First = "PRI";
	/** Second = SEG */
	public static final String TIPODOSIS_Second = "SEG";
	/** Third = TER */
	public static final String TIPODOSIS_Third = "TER";
	/** Additional = ADI */
	public static final String TIPODOSIS_Additional = "ADI";
	/** Reinforcement 1 = RE1 */
	public static final String TIPODOSIS_Reinforcement1 = "RE1";
	/** Reinforcement 2 = RE2 */
	public static final String TIPODOSIS_Reinforcement2 = "RE2";
	/** Reinforcement = RE */
	public static final String TIPODOSIS_Reinforcement = "RE";
	/** Fourth = FOU */
	public static final String TIPODOSIS_Fourth = "FOU";
	/** Fifth = FIF */
	public static final String TIPODOSIS_Fifth = "FIF";
	/** Set Dose rate.
		@param TipoDosis Dose rate	  */
	public void setTipoDosis (String TipoDosis)
	{
		if (TipoDosis == null) throw new IllegalArgumentException ("TipoDosis is mandatory");
		if (TipoDosis.equals("UNI") || TipoDosis.equals("PRE") || TipoDosis.equals("PRI") || TipoDosis.equals("SEG") || TipoDosis.equals("TER") || TipoDosis.equals("ADI") || TipoDosis.equals("RE1") || TipoDosis.equals("RE2") || TipoDosis.equals("RE") || TipoDosis.equals("FOU") || TipoDosis.equals("FIF")); else throw new IllegalArgumentException ("TipoDosis Invalid value - " + TipoDosis + " - Reference_ID=1200129 - UNI - PRE - PRI - SEG - TER - ADI - RE1 - RE2 - RE - FOU - FIF");		set_Value (COLUMNNAME_TipoDosis, TipoDosis);
	}

	/** Get Dose rate.
		@return Dose rate	  */
	public String getTipoDosis () 
	{
		return (String)get_Value(COLUMNNAME_TipoDosis);
	}

	/** Set Type of Vaccine.
		@param VaccineType 
		Type of Vaccine (generic abreviation)
	  */
	public void setVaccineType (String VaccineType)
	{
		set_Value (COLUMNNAME_VaccineType, VaccineType);
	}

	/** Get Type of Vaccine.
		@return Type of Vaccine (generic abreviation)
	  */
	public String getVaccineType () 
	{
		return (String)get_Value(COLUMNNAME_VaccineType);
	}
}