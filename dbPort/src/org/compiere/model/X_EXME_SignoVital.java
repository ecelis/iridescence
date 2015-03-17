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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_SignoVital
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SignoVital extends PO implements I_EXME_SignoVital, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SignoVital (Properties ctx, int EXME_SignoVital_ID, String trxName)
    {
      super (ctx, EXME_SignoVital_ID, trxName);
      /** if (EXME_SignoVital_ID == 0)
        {
			setC_UOM_ID (0);
			setEXME_SignoVital_ID (0);
			setName (null);
			setSecuencia (Env.ZERO);
			setValorMax (Env.ZERO);
			setValorMin (Env.ZERO);
			setValue (null);
			setWindowType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_SignoVital (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SignoVital[")
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

	public I_EXME_FormulaSigVital getEXME_FormulaSigVital() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FormulaSigVital.Table_Name);
        I_EXME_FormulaSigVital result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FormulaSigVital)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FormulaSigVital_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Signs Formula.
		@param EXME_FormulaSigVital_ID Vital Signs Formula	  */
	public void setEXME_FormulaSigVital_ID (int EXME_FormulaSigVital_ID)
	{
		if (EXME_FormulaSigVital_ID < 1) 
			set_Value (COLUMNNAME_EXME_FormulaSigVital_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FormulaSigVital_ID, Integer.valueOf(EXME_FormulaSigVital_ID));
	}

	/** Get Vital Signs Formula.
		@return Vital Signs Formula	  */
	public int getEXME_FormulaSigVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormulaSigVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vital Sign.
		@param EXME_SignoVital_ID Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID)
	{
		if (EXME_SignoVital_ID < 1)
			 throw new IllegalArgumentException ("EXME_SignoVital_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SignoVital_ID, Integer.valueOf(EXME_SignoVital_ID));
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

	/** Set ValorMax.
		@param ValorMax ValorMax	  */
	public void setValorMax (BigDecimal ValorMax)
	{
		if (ValorMax == null)
			throw new IllegalArgumentException ("ValorMax is mandatory.");
		set_Value (COLUMNNAME_ValorMax, ValorMax);
	}

	/** Get ValorMax.
		@return ValorMax	  */
	public BigDecimal getValorMax () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValorMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ValorMin.
		@param ValorMin ValorMin	  */
	public void setValorMin (BigDecimal ValorMin)
	{
		if (ValorMin == null)
			throw new IllegalArgumentException ("ValorMin is mandatory.");
		set_Value (COLUMNNAME_ValorMin, ValorMin);
	}

	/** Get ValorMin.
		@return ValorMin	  */
	public BigDecimal getValorMin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValorMin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** WindowType AD_Reference_ID=1200454 */
	public static final int WINDOWTYPE_AD_Reference_ID=1200454;
	/** All = A */
	public static final String WINDOWTYPE_All = "A";
	/** Vital Signs = S */
	public static final String WINDOWTYPE_VitalSigns = "S";
	/** Body Mass Calculator = M */
	public static final String WINDOWTYPE_BodyMassCalculator = "M";
	/** None = N */
	public static final String WINDOWTYPE_None = "N";
	/** Set Window Type.
		@param WindowType 
		Type or classification of a Window
	  */
	public void setWindowType (String WindowType)
	{
		if (WindowType == null) throw new IllegalArgumentException ("WindowType is mandatory");
		if (WindowType.equals("A") || WindowType.equals("S") || WindowType.equals("M") || WindowType.equals("N")); else throw new IllegalArgumentException ("WindowType Invalid value - " + WindowType + " - Reference_ID=1200454 - A - S - M - N");		set_Value (COLUMNNAME_WindowType, WindowType);
	}

	/** Get Window Type.
		@return Type or classification of a Window
	  */
	public String getWindowType () 
	{
		return (String)get_Value(COLUMNNAME_WindowType);
	}
}