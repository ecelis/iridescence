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

/** Generated Model for EXME_Alerta
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Alerta extends PO implements I_EXME_Alerta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Alerta (Properties ctx, int EXME_Alerta_ID, String trxName)
    {
      super (ctx, EXME_Alerta_ID, trxName);
      /** if (EXME_Alerta_ID == 0)
        {
			setEXME_Alerta_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Alerta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Alerta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Height.
		@param Altura Height	  */
	public void setAltura (BigDecimal Altura)
	{
		set_Value (COLUMNNAME_Altura, Altura);
	}

	/** Get Height.
		@return Height	  */
	public BigDecimal getAltura () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Altura);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (BigDecimal Edad)
	{
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public BigDecimal getEdad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Edad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set EXME_Alerta_ID.
		@param EXME_Alerta_ID EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID)
	{
		if (EXME_Alerta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Alerta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Alerta_ID, Integer.valueOf(EXME_Alerta_ID));
	}

	/** Get EXME_Alerta_ID.
		@return EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alerta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Frequency);
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public BigDecimal getFrequency () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Frequency);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Frequency 2.
		@param Frequency2 Frequency 2	  */
	public void setFrequency2 (BigDecimal Frequency2)
	{
		set_Value (COLUMNNAME_Frequency2, Frequency2);
	}

	/** Get Frequency 2.
		@return Frequency 2	  */
	public BigDecimal getFrequency2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Frequency2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Operator.
		@param Operator Operator	  */
	public void setOperator (String Operator)
	{
		set_Value (COLUMNNAME_Operator, Operator);
	}

	/** Get Operator.
		@return Operator	  */
	public String getOperator () 
	{
		return (String)get_Value(COLUMNNAME_Operator);
	}

	/** Set Operator 2.
		@param Operator2 Operator 2	  */
	public void setOperator2 (String Operator2)
	{
		set_Value (COLUMNNAME_Operator2, Operator2);
	}

	/** Get Operator 2.
		@return Operator 2	  */
	public String getOperator2 () 
	{
		return (String)get_Value(COLUMNNAME_Operator2);
	}

	/** Set Operator 3.
		@param Operator3 Operator 3	  */
	public void setOperator3 (String Operator3)
	{
		set_Value (COLUMNNAME_Operator3, Operator3);
	}

	/** Get Operator 3.
		@return Operator 3	  */
	public String getOperator3 () 
	{
		return (String)get_Value(COLUMNNAME_Operator3);
	}

	/** Set Operator 4.
		@param Operator4 Operator 4	  */
	public void setOperator4 (String Operator4)
	{
		set_Value (COLUMNNAME_Operator4, Operator4);
	}

	/** Get Operator 4.
		@return Operator 4	  */
	public String getOperator4 () 
	{
		return (String)get_Value(COLUMNNAME_Operator4);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
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