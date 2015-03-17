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

/** Generated Model for EXME_LoincRange
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_LoincRange extends PO implements I_EXME_LoincRange, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LoincRange (Properties ctx, int EXME_LoincRange_ID, String trxName)
    {
      super (ctx, EXME_LoincRange_ID, trxName);
      /** if (EXME_LoincRange_ID == 0)
        {
			setEXME_LoincRange_ID (0);
			setEXME_Loinc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LoincRange (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LoincRange[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Loinc Range.
		@param EXME_LoincRange_ID Loinc Range	  */
	public void setEXME_LoincRange_ID (int EXME_LoincRange_ID)
	{
		if (EXME_LoincRange_ID < 1)
			 throw new IllegalArgumentException ("EXME_LoincRange_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LoincRange_ID, Integer.valueOf(EXME_LoincRange_ID));
	}

	/** Get Loinc Range.
		@return Loinc Range	  */
	public int getEXME_LoincRange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincRange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Loinc.Table_Name);
        I_EXME_Loinc result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Loinc)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Loinc_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set LOINC Code.
		@param EXME_Loinc_ID LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID)
	{
		if (EXME_Loinc_ID < 1)
			 throw new IllegalArgumentException ("EXME_Loinc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
	}

	/** Get LOINC Code.
		@return LOINC Code	  */
	public int getEXME_Loinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Loinc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum age.
		@param EdadMaxima Maximum age	  */
	public void setEdadMaxima (BigDecimal EdadMaxima)
	{
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

	/** Sexo AD_Reference_ID=1000018 */
	public static final int SEXO_AD_Reference_ID=1000018;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** Unassigned = U */
	public static final String SEXO_Unassigned = "U";
	/** Ambiguous = A */
	public static final String SEXO_Ambiguous = "A";
	/** Not Applicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Other = O */
	public static final String SEXO_Other = "O";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{

		if (Sexo == null || Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}

	/** Set Max. Value.
		@param ValueMax 
		Maximum Value for a field
	  */
	public void setValueMax (BigDecimal ValueMax)
	{
		set_Value (COLUMNNAME_ValueMax, ValueMax);
	}

	/** Get Max. Value.
		@return Maximum Value for a field
	  */
	public BigDecimal getValueMax () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min. Value.
		@param ValueMin 
		Minimum Value for a field
	  */
	public void setValueMin (BigDecimal ValueMin)
	{
		set_Value (COLUMNNAME_ValueMin, ValueMin);
	}

	/** Get Min. Value.
		@return Minimum Value for a field
	  */
	public BigDecimal getValueMin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}