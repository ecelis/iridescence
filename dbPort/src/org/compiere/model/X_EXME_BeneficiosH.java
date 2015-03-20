/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_BeneficiosH
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_BeneficiosH extends PO implements I_EXME_BeneficiosH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BeneficiosH (Properties ctx, int EXME_BeneficiosH_ID, String trxName)
    {
      super (ctx, EXME_BeneficiosH_ID, trxName);
      /** if (EXME_BeneficiosH_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_BeneficiosH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BeneficiosH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ElegibilityType AD_Reference_ID=1200461 */
	public static final int ELEGIBILITYTYPE_AD_Reference_ID=1200461;
	/** Drug elegibility = D */
	public static final String ELEGIBILITYTYPE_DrugElegibility = "D";
	/** Coverage Elegibility = C */
	public static final String ELEGIBILITYTYPE_CoverageElegibility = "C";
	/** Set Elegibility Type.
		@param ElegibilityType Elegibility Type	  */
	public void setElegibilityType (String ElegibilityType)
	{

		if (ElegibilityType == null || ElegibilityType.equals("D") || ElegibilityType.equals("C")); else throw new IllegalArgumentException ("ElegibilityType Invalid value - " + ElegibilityType + " - Reference_ID=1200461 - D - C");		set_Value (COLUMNNAME_ElegibilityType, ElegibilityType);
	}

	/** Get Elegibility Type.
		@return Elegibility Type	  */
	public String getElegibilityType () 
	{
		return (String)get_Value(COLUMNNAME_ElegibilityType);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	public I_EXME_Alternative getEXME_Alternative() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Alternative.Table_Name);
        I_EXME_Alternative result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Alternative)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Alternative_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Formulary Alternative.
		@param EXME_Alternative_ID Formulary Alternative	  */
	public void setEXME_Alternative_ID (int EXME_Alternative_ID)
	{
		if (EXME_Alternative_ID < 1) 
			set_Value (COLUMNNAME_EXME_Alternative_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Alternative_ID, Integer.valueOf(EXME_Alternative_ID));
	}

	/** Get Formulary Alternative.
		@return Formulary Alternative	  */
	public int getEXME_Alternative_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alternative_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_BeneficiosH_ID.
		@param EXME_BeneficiosH_ID EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID)
	{
		if (EXME_BeneficiosH_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_BeneficiosH_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_BeneficiosH_ID, Integer.valueOf(EXME_BeneficiosH_ID));
	}

	/** Get EXME_BeneficiosH_ID.
		@return EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BeneficiosH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Copay getEXME_Copay() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Copay.Table_Name);
        I_EXME_Copay result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Copay)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Copay_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Benefit Copay.
		@param EXME_Copay_ID Benefit Copay	  */
	public void setEXME_Copay_ID (int EXME_Copay_ID)
	{
		if (EXME_Copay_ID < 1) 
			set_Value (COLUMNNAME_EXME_Copay_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Copay_ID, Integer.valueOf(EXME_Copay_ID));
	}

	/** Get Benefit Copay.
		@return Benefit Copay	  */
	public int getEXME_Copay_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Copay_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Coverage getEXME_Coverage() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Coverage.Table_Name);
        I_EXME_Coverage result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Coverage)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Coverage_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Benfit Coverage.
		@param EXME_Coverage_ID Benfit Coverage	  */
	public void setEXME_Coverage_ID (int EXME_Coverage_ID)
	{
		if (EXME_Coverage_ID < 1) 
			set_Value (COLUMNNAME_EXME_Coverage_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Coverage_ID, Integer.valueOf(EXME_Coverage_ID));
	}

	/** Get Benfit Coverage.
		@return Benfit Coverage	  */
	public int getEXME_Coverage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Coverage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Formulary getEXME_Formulary() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Formulary.Table_Name);
        I_EXME_Formulary result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Formulary)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Formulary_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Formulary status.
		@param EXME_Formulary_ID Formulary status	  */
	public void setEXME_Formulary_ID (int EXME_Formulary_ID)
	{
		if (EXME_Formulary_ID < 1) 
			set_Value (COLUMNNAME_EXME_Formulary_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Formulary_ID, Integer.valueOf(EXME_Formulary_ID));
	}

	/** Get Formulary status.
		@return Formulary status	  */
	public int getEXME_Formulary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Formulary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_Paciente_Sus_ID.
		@param EXME_Paciente_Sus_ID EXME_Paciente_Sus_ID	  */
	public void setEXME_Paciente_Sus_ID (int EXME_Paciente_Sus_ID)
	{
		if (EXME_Paciente_Sus_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_Sus_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_Sus_ID, Integer.valueOf(EXME_Paciente_Sus_ID));
	}

	/** Get EXME_Paciente_Sus_ID.
		@return EXME_Paciente_Sus_ID	  */
	public int getEXME_Paciente_Sus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Sus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** IsElegibleMailOrder AD_Reference_ID=1200251 */
	public static final int ISELEGIBLEMAILORDER_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ISELEGIBLEMAILORDER_NO = "0";
	/** Yes = 1 */
	public static final String ISELEGIBLEMAILORDER_Yes = "1";
	/** Set Elegible for mail order pharmacy.
		@param IsElegibleMailOrder Elegible for mail order pharmacy	  */
	public void setIsElegibleMailOrder (String IsElegibleMailOrder)
	{

		if (IsElegibleMailOrder == null || IsElegibleMailOrder.equals("0") || IsElegibleMailOrder.equals("1")); else throw new IllegalArgumentException ("IsElegibleMailOrder Invalid value - " + IsElegibleMailOrder + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_IsElegibleMailOrder, IsElegibleMailOrder);
	}

	/** Get Elegible for mail order pharmacy.
		@return Elegible for mail order pharmacy	  */
	public String getIsElegibleMailOrder () 
	{
		return (String)get_Value(COLUMNNAME_IsElegibleMailOrder);
	}

	/** IsElegibleRetail AD_Reference_ID=1200251 */
	public static final int ISELEGIBLERETAIL_AD_Reference_ID=1200251;
	/** NO = 0 */
	public static final String ISELEGIBLERETAIL_NO = "0";
	/** Yes = 1 */
	public static final String ISELEGIBLERETAIL_Yes = "1";
	/** Set Elegible for retail pharmacy.
		@param IsElegibleRetail Elegible for retail pharmacy	  */
	public void setIsElegibleRetail (String IsElegibleRetail)
	{

		if (IsElegibleRetail == null || IsElegibleRetail.equals("0") || IsElegibleRetail.equals("1")); else throw new IllegalArgumentException ("IsElegibleRetail Invalid value - " + IsElegibleRetail + " - Reference_ID=1200251 - 0 - 1");		set_Value (COLUMNNAME_IsElegibleRetail, IsElegibleRetail);
	}

	/** Get Elegible for retail pharmacy.
		@return Elegible for retail pharmacy	  */
	public String getIsElegibleRetail () 
	{
		return (String)get_Value(COLUMNNAME_IsElegibleRetail);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}