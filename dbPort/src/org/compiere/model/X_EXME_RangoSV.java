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

/** Generated Model for EXME_RangoSV
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_RangoSV extends PO implements I_EXME_RangoSV, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RangoSV (Properties ctx, int EXME_RangoSV_ID, String trxName)
    {
      super (ctx, EXME_RangoSV_ID, trxName);
      /** if (EXME_RangoSV_ID == 0)
        {
			setEdadFin (Env.ZERO);
			setEdadIni (Env.ZERO);
			setEXME_RangoSV_ID (0);
			setEXME_SignoVital_ID (0);
			setName (null);
			setSequence (Env.ZERO);
			setSexo (null);
			setValorFin (Env.ZERO);
			setValorIni (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RangoSV (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RangoSV[")
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

	/** Set To Age.
		@param EdadFin To Age	  */
	public void setEdadFin (BigDecimal EdadFin)
	{
		if (EdadFin == null)
			throw new IllegalArgumentException ("EdadFin is mandatory.");
		set_Value (COLUMNNAME_EdadFin, EdadFin);
	}

	/** Get To Age.
		@return To Age	  */
	public BigDecimal getEdadFin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadFin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Age Initial.
		@param EdadIni Age Initial	  */
	public void setEdadIni (BigDecimal EdadIni)
	{
		if (EdadIni == null)
			throw new IllegalArgumentException ("EdadIni is mandatory.");
		set_Value (COLUMNNAME_EdadIni, EdadIni);
	}

	/** Get Age Initial.
		@return Age Initial	  */
	public BigDecimal getEdadIni () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EdadIni);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Vital signs ranges.
		@param EXME_RangoSV_ID Vital signs ranges	  */
	public void setEXME_RangoSV_ID (int EXME_RangoSV_ID)
	{
		if (EXME_RangoSV_ID < 1)
			 throw new IllegalArgumentException ("EXME_RangoSV_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RangoSV_ID, Integer.valueOf(EXME_RangoSV_ID));
	}

	/** Get Vital signs ranges.
		@return Vital signs ranges	  */
	public int getEXME_RangoSV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RangoSV_ID);
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

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
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

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		if (Sequence == null)
			throw new IllegalArgumentException ("Sequence is mandatory.");
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Sexo AD_Reference_ID=1200671 */
	public static final int SEXO_AD_Reference_ID=1200671;
	/** Female = F */
	public static final String SEXO_Female = "F";
	/** Male = M */
	public static final String SEXO_Male = "M";
	/** NotApplicable = N */
	public static final String SEXO_NotApplicable = "N";
	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (String Sexo)
	{
		if (Sexo == null) throw new IllegalArgumentException ("Sexo is mandatory");
		if (Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("N")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1200671 - F - M - N");		set_Value (COLUMNNAME_Sexo, Sexo);
	}

	/** Get Sex.
		@return Sex
	  */
	public String getSexo () 
	{
		return (String)get_Value(COLUMNNAME_Sexo);
	}

	/** Set Final Value.
		@param ValorFin Final Value	  */
	public void setValorFin (BigDecimal ValorFin)
	{
		if (ValorFin == null)
			throw new IllegalArgumentException ("ValorFin is mandatory.");
		set_Value (COLUMNNAME_ValorFin, ValorFin);
	}

	/** Get Final Value.
		@return Final Value	  */
	public BigDecimal getValorFin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValorFin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Initial Value.
		@param ValorIni Initial Value	  */
	public void setValorIni (BigDecimal ValorIni)
	{
		if (ValorIni == null)
			throw new IllegalArgumentException ("ValorIni is mandatory.");
		set_Value (COLUMNNAME_ValorIni, ValorIni);
	}

	/** Get Initial Value.
		@return Initial Value	  */
	public BigDecimal getValorIni () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValorIni);
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
}