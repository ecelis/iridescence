/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Vacuna
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Vacuna extends PO implements I_EXME_Vacuna, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Vacuna (Properties ctx, int EXME_Vacuna_ID, String trxName)
    {
      super (ctx, EXME_Vacuna_ID, trxName);
      /** if (EXME_Vacuna_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_Vacuna_ID (0);
			setIncluyeCartilla (false);
// N
			setSexo (null);
			setValue (null);
			setVia (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Vacuna (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Vacuna[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set CVX Code.
		@param CodeCVX CVX Code	  */
	public void setCodeCVX (String CodeCVX)
	{
		set_Value (COLUMNNAME_CodeCVX, CodeCVX);
	}

	/** Get CVX Code.
		@return CVX Code	  */
	public String getCodeCVX () 
	{
		return (String)get_Value(COLUMNNAME_CodeCVX);
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

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
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

	/** Set Second Diagnostic.
		@param EXME_Diagnostico2_ID 
		Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID)
	{
		if (EXME_Diagnostico2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico2_ID, Integer.valueOf(EXME_Diagnostico2_ID));
	}

	/** Get Second Diagnostic.
		@return Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diagnostic.
		@param EXME_Diagnostico3_ID 
		Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID)
	{
		if (EXME_Diagnostico3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico3_ID, Integer.valueOf(EXME_Diagnostico3_ID));
	}

	/** Get Third Diagnostic.
		@return Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fourth Diagnosis.
		@param EXME_Diagnostico4_ID Fourth Diagnosis	  */
	public void setEXME_Diagnostico4_ID (int EXME_Diagnostico4_ID)
	{
		if (EXME_Diagnostico4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico4_ID, Integer.valueOf(EXME_Diagnostico4_ID));
	}

	/** Get Fourth Diagnosis.
		@return Fourth Diagnosis	  */
	public int getEXME_Diagnostico4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fifth Diagnosis.
		@param EXME_Diagnostico5_ID Fifth Diagnosis	  */
	public void setEXME_Diagnostico5_ID (int EXME_Diagnostico5_ID)
	{
		if (EXME_Diagnostico5_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico5_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico5_ID, Integer.valueOf(EXME_Diagnostico5_ID));
	}

	/** Get Fifth Diagnosis.
		@return Fifth Diagnosis	  */
	public int getEXME_Diagnostico5_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico5_ID);
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

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1) 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Included in Immunization Cards.
		@param IncluyeCartilla Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla)
	{
		set_Value (COLUMNNAME_IncluyeCartilla, Boolean.valueOf(IncluyeCartilla));
	}

	/** Get Included in Immunization Cards.
		@return Included in Immunization Cards	  */
	public boolean isIncluyeCartilla () 
	{
		Object oo = get_Value(COLUMNNAME_IncluyeCartilla);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Related Vaccine.
		@param Rel_Vacuna_ID Related Vaccine	  */
	public void setRel_Vacuna_ID (int Rel_Vacuna_ID)
	{
		if (Rel_Vacuna_ID < 1) 
			set_Value (COLUMNNAME_Rel_Vacuna_ID, null);
		else 
			set_Value (COLUMNNAME_Rel_Vacuna_ID, Integer.valueOf(Rel_Vacuna_ID));
	}

	/** Get Related Vaccine.
		@return Related Vaccine	  */
	public int getRel_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rel_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		if (Sexo == null) throw new IllegalArgumentException ("Sexo is mandatory");
		if (Sexo.equals("F") || Sexo.equals("M") || Sexo.equals("U") || Sexo.equals("A") || Sexo.equals("N") || Sexo.equals("O")); else throw new IllegalArgumentException ("Sexo Invalid value - " + Sexo + " - Reference_ID=1000018 - F - M - U - A - N - O");		set_Value (COLUMNNAME_Sexo, Sexo);
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

	/** Set Route of Administration.
		@param Via 
		Route of Administration
	  */
	public void setVia (String Via)
	{
		if (Via == null)
			throw new IllegalArgumentException ("Via is mandatory.");
		set_Value (COLUMNNAME_Via, Via);
	}

	/** Get Route of Administration.
		@return Route of Administration
	  */
	public String getVia () 
	{
		return (String)get_Value(COLUMNNAME_Via);
	}

	/** Set Date on VIS.
		@param VIS_Date 
		Vaccine Information Statement date
	  */
	public void setVIS_Date (Timestamp VIS_Date)
	{
		set_Value (COLUMNNAME_VIS_Date, VIS_Date);
	}

	/** Get Date on VIS.
		@return Vaccine Information Statement date
	  */
	public Timestamp getVIS_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_VIS_Date);
	}
}