/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Core_Csa
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Core_Csa extends PO implements I_EXME_Core_Csa, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Core_Csa (Properties ctx, int EXME_Core_Csa_ID, String trxName)
    {
      super (ctx, EXME_Core_Csa_ID, trxName);
      /** if (EXME_Core_Csa_ID == 0)
        {
			setC_Region_ID (0);
			setCSA_Code (null);
			setEXME_CORE_CSA_ID (0);
			setEXME_GenProduct_ID (0);
			setGenProduct_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Core_Csa (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Core_Csa[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Region getC_Region() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Region.Table_Name);
        I_C_Region result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Region)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Region_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Region.
		@param C_Region_ID 
		Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID)
	{
		if (C_Region_ID < 1)
			 throw new IllegalArgumentException ("C_Region_ID is mandatory.");
		set_Value (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
	}

	/** Get Region.
		@return Identifies a geographical Region
	  */
	public int getC_Region_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** CSA_Code AD_Reference_ID=1200616 */
	public static final int CSA_CODE_AD_Reference_ID=1200616;
	/** No current medical use = 1 */
	public static final String CSA_CODE_NoCurrentMedicalUse = "1";
	/** High potential for abuse = 2 */
	public static final String CSA_CODE_HighPotentialForAbuse = "2";
	/** Some potential for abuse = 3 */
	public static final String CSA_CODE_SomePotentialForAbuse = "3";
	/** Low potential for abuse = 4 */
	public static final String CSA_CODE_LowPotentialForAbuse = "4";
	/** Subject to state and local regulation = 5 */
	public static final String CSA_CODE_SubjectToStateAndLocalRegulation = "5";
	/** Not subject to the Controlled Substances = 0 */
	public static final String CSA_CODE_NotSubjectToTheControlledSubstances = "0";
	/** Set CSA Code.
		@param CSA_Code CSA Code	  */
	public void setCSA_Code (String CSA_Code)
	{
		if (CSA_Code == null) throw new IllegalArgumentException ("CSA_Code is mandatory");
		if (CSA_Code.equals("1") || CSA_Code.equals("2") || CSA_Code.equals("3") || CSA_Code.equals("4") || CSA_Code.equals("5") || CSA_Code.equals("0")); else throw new IllegalArgumentException ("CSA_Code Invalid value - " + CSA_Code + " - Reference_ID=1200616 - 1 - 2 - 3 - 4 - 5 - 0");		set_Value (COLUMNNAME_CSA_Code, CSA_Code);
	}

	/** Get CSA Code.
		@return CSA Code	  */
	public String getCSA_Code () 
	{
		return (String)get_Value(COLUMNNAME_CSA_Code);
	}

	/** Set EXME_CORE_CSA_ID.
		@param EXME_CORE_CSA_ID EXME_CORE_CSA_ID	  */
	public void setEXME_CORE_CSA_ID (int EXME_CORE_CSA_ID)
	{
		if (EXME_CORE_CSA_ID < 1)
			 throw new IllegalArgumentException ("EXME_CORE_CSA_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CORE_CSA_ID, Integer.valueOf(EXME_CORE_CSA_ID));
	}

	/** Get EXME_CORE_CSA_ID.
		@return EXME_CORE_CSA_ID	  */
	public int getEXME_CORE_CSA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CORE_CSA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GenProduct.Table_Name);
        I_EXME_GenProduct result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GenProduct)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GenProduct_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenProduct_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Product ID.
		@param GenProduct_ID Generic Product ID	  */
	public void setGenProduct_ID (int GenProduct_ID)
	{
		if (GenProduct_ID < 1)
			 throw new IllegalArgumentException ("GenProduct_ID is mandatory.");
		set_Value (COLUMNNAME_GenProduct_ID, Integer.valueOf(GenProduct_ID));
	}

	/** Get Generic Product ID.
		@return Generic Product ID	  */
	public int getGenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}