/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_I18N
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_I18N extends PO implements I_EXME_I18N, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_I18N (Properties ctx, int EXME_I18N_ID, String trxName)
    {
      super (ctx, EXME_I18N_ID, trxName);
      /** if (EXME_I18N_ID == 0)
        {
			setcanInsertNewProducts (false);
			setC_Country_ID (0);
			setEXME_I18N_ID (0);
			setHideChargesFields (false);
			setmoveTaxes (false);
// N
			setnoValidatePhysician (false);
			setpatientFormMexico (false);
			setspanishDefaultLanguage (false);
			setvalidarCaja (false);
			setvalidatecurp (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_I18N (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_I18N[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Can Insert New Products.
		@param canInsertNewProducts 
		Can Insert New Products
	  */
	public void setcanInsertNewProducts (boolean canInsertNewProducts)
	{
		set_Value (COLUMNNAME_canInsertNewProducts, Boolean.valueOf(canInsertNewProducts));
	}

	/** Get Can Insert New Products.
		@return Can Insert New Products
	  */
	public boolean iscanInsertNewProducts () 
	{
		Object oo = get_Value(COLUMNNAME_canInsertNewProducts);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_Country getC_Country() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Country.Table_Name);
        I_C_Country result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Country)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Country_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1)
			 throw new IllegalArgumentException ("C_Country_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set National Identification Number.
		@param Curp 
		National Identification Number
	  */
	public void setCurp (String Curp)
	{
		set_Value (COLUMNNAME_Curp, Curp);
	}

	/** Get National Identification Number.
		@return National Identification Number
	  */
	public String getCurp () 
	{
		return (String)get_Value(COLUMNNAME_Curp);
	}

	/** Set EXME_I18N_ID.
		@param EXME_I18N_ID EXME_I18N_ID	  */
	public void setEXME_I18N_ID (int EXME_I18N_ID)
	{
		if (EXME_I18N_ID < 1)
			 throw new IllegalArgumentException ("EXME_I18N_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_I18N_ID, Integer.valueOf(EXME_I18N_ID));
	}

	/** Get EXME_I18N_ID.
		@return EXME_I18N_ID	  */
	public int getEXME_I18N_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_I18N_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hide Fields in Charges.
		@param HideChargesFields 
		Hide Fields in Charges
	  */
	public void setHideChargesFields (boolean HideChargesFields)
	{
		set_Value (COLUMNNAME_HideChargesFields, Boolean.valueOf(HideChargesFields));
	}

	/** Get Hide Fields in Charges.
		@return Hide Fields in Charges
	  */
	public boolean isHideChargesFields () 
	{
		Object oo = get_Value(COLUMNNAME_HideChargesFields);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Move taxes between accounts.
		@param moveTaxes 
		Move taxes between accounts
	  */
	public void setmoveTaxes (boolean moveTaxes)
	{
		set_Value (COLUMNNAME_moveTaxes, Boolean.valueOf(moveTaxes));
	}

	/** Get Move taxes between accounts.
		@return Move taxes between accounts
	  */
	public boolean ismoveTaxes () 
	{
		Object oo = get_Value(COLUMNNAME_moveTaxes);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set No Validate Physician.
		@param noValidatePhysician 
		No Validate Physician
	  */
	public void setnoValidatePhysician (boolean noValidatePhysician)
	{
		set_Value (COLUMNNAME_noValidatePhysician, Boolean.valueOf(noValidatePhysician));
	}

	/** Get No Validate Physician.
		@return No Validate Physician
	  */
	public boolean isnoValidatePhysician () 
	{
		Object oo = get_Value(COLUMNNAME_noValidatePhysician);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Patient Form Mexico.
		@param patientFormMexico 
		Patient Form Mexico
	  */
	public void setpatientFormMexico (boolean patientFormMexico)
	{
		set_Value (COLUMNNAME_patientFormMexico, Boolean.valueOf(patientFormMexico));
	}

	/** Get Patient Form Mexico.
		@return Patient Form Mexico
	  */
	public boolean ispatientFormMexico () 
	{
		Object oo = get_Value(COLUMNNAME_patientFormMexico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Responsible Mexico.
		@param ResponsibleMexico Responsible Mexico	  */
	public void setResponsibleMexico (boolean ResponsibleMexico)
	{
		set_Value (COLUMNNAME_ResponsibleMexico, Boolean.valueOf(ResponsibleMexico));
	}

	/** Get Responsible Mexico.
		@return Responsible Mexico	  */
	public boolean isResponsibleMexico () 
	{
		Object oo = get_Value(COLUMNNAME_ResponsibleMexico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Spanish Default Language.
		@param spanishDefaultLanguage 
		Spanish Default Language
	  */
	public void setspanishDefaultLanguage (boolean spanishDefaultLanguage)
	{
		set_Value (COLUMNNAME_spanishDefaultLanguage, Boolean.valueOf(spanishDefaultLanguage));
	}

	/** Get Spanish Default Language.
		@return Spanish Default Language
	  */
	public boolean isspanishDefaultLanguage () 
	{
		Object oo = get_Value(COLUMNNAME_spanishDefaultLanguage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Internal Supply.
		@param SurtidoInterno 
		Internal Supply
	  */
	public void setSurtidoInterno (boolean SurtidoInterno)
	{
		set_Value (COLUMNNAME_SurtidoInterno, Boolean.valueOf(SurtidoInterno));
	}

	/** Get Internal Supply.
		@return Internal Supply
	  */
	public boolean isSurtidoInterno () 
	{
		Object oo = get_Value(COLUMNNAME_SurtidoInterno);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set validarCaja.
		@param validarCaja 
		Validate Cash Register
	  */
	public void setvalidarCaja (boolean validarCaja)
	{
		set_Value (COLUMNNAME_validarCaja, Boolean.valueOf(validarCaja));
	}

	/** Get validarCaja.
		@return Validate Cash Register
	  */
	public boolean isvalidarCaja () 
	{
		Object oo = get_Value(COLUMNNAME_validarCaja);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set validatecurp.
		@param validatecurp validatecurp	  */
	public void setvalidatecurp (boolean validatecurp)
	{
		set_Value (COLUMNNAME_validatecurp, Boolean.valueOf(validatecurp));
	}

	/** Get validatecurp.
		@return validatecurp	  */
	public boolean isvalidatecurp () 
	{
		Object oo = get_Value(COLUMNNAME_validatecurp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}