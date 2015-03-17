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

/** Generated Model for EXME_Beneficios
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Beneficios extends PO implements I_EXME_Beneficios, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Beneficios (Properties ctx, int EXME_Beneficios_ID, String trxName)
    {
      super (ctx, EXME_Beneficios_ID, trxName);
      /** if (EXME_Beneficios_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_Beneficios (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Beneficios[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BeneficiosH.Table_Name);
        I_EXME_BeneficiosH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BeneficiosH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BeneficiosH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_BeneficiosH_ID.
		@param EXME_BeneficiosH_ID EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID)
	{
		if (EXME_BeneficiosH_ID < 1) 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_BeneficiosH_ID, Integer.valueOf(EXME_BeneficiosH_ID));
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

	/** Set Benefits.
		@param EXME_Beneficios_ID 
		Benefits
	  */
	public void setEXME_Beneficios_ID (int EXME_Beneficios_ID)
	{
		if (EXME_Beneficios_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Beneficios_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Beneficios_ID, Integer.valueOf(EXME_Beneficios_ID));
	}

	/** Get Benefits.
		@return Benefits
	  */
	public int getEXME_Beneficios_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Beneficios_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CoverageType getEXME_CoverageType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CoverageType.Table_Name);
        I_EXME_CoverageType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CoverageType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CoverageType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Coverage Type.
		@param EXME_CoverageType_ID Coverage Type	  */
	public void setEXME_CoverageType_ID (int EXME_CoverageType_ID)
	{
		if (EXME_CoverageType_ID < 1) 
			set_Value (COLUMNNAME_EXME_CoverageType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CoverageType_ID, Integer.valueOf(EXME_CoverageType_ID));
	}

	/** Get Coverage Type.
		@return Coverage Type	  */
	public int getEXME_CoverageType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CoverageType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_InsuranceType getEXME_InsuranceType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_InsuranceType.Table_Name);
        I_EXME_InsuranceType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_InsuranceType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_InsuranceType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Insurance Type.
		@param EXME_InsuranceType_ID Insurance Type	  */
	public void setEXME_InsuranceType_ID (int EXME_InsuranceType_ID)
	{
		if (EXME_InsuranceType_ID < 1) 
			set_Value (COLUMNNAME_EXME_InsuranceType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_InsuranceType_ID, Integer.valueOf(EXME_InsuranceType_ID));
	}

	/** Get Insurance Type.
		@return Insurance Type	  */
	public int getEXME_InsuranceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InsuranceType_ID);
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

	/** Set EXME_PACIENTE_S_ID.
		@param EXME_PACIENTE_S_ID EXME_PACIENTE_S_ID	  */
	public void setEXME_PACIENTE_S_ID (int EXME_PACIENTE_S_ID)
	{
		if (EXME_PACIENTE_S_ID < 1) 
			set_Value (COLUMNNAME_EXME_PACIENTE_S_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PACIENTE_S_ID, Integer.valueOf(EXME_PACIENTE_S_ID));
	}

	/** Get EXME_PACIENTE_S_ID.
		@return EXME_PACIENTE_S_ID	  */
	public int getEXME_PACIENTE_S_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PACIENTE_S_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PeriodQual getEXME_PeriodQual() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PeriodQual.Table_Name);
        I_EXME_PeriodQual result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PeriodQual)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PeriodQual_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Period Qualifier.
		@param EXME_PeriodQual_ID Period Qualifier	  */
	public void setEXME_PeriodQual_ID (int EXME_PeriodQual_ID)
	{
		if (EXME_PeriodQual_ID < 1) 
			set_Value (COLUMNNAME_EXME_PeriodQual_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PeriodQual_ID, Integer.valueOf(EXME_PeriodQual_ID));
	}

	/** Get Period Qualifier.
		@return Period Qualifier	  */
	public int getEXME_PeriodQual_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PeriodQual_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_QuantityQual getEXME_QuantityQual() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_QuantityQual.Table_Name);
        I_EXME_QuantityQual result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_QuantityQual)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_QuantityQual_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Quantity Qualifier.
		@param EXME_QuantityQual_ID Quantity Qualifier	  */
	public void setEXME_QuantityQual_ID (int EXME_QuantityQual_ID)
	{
		if (EXME_QuantityQual_ID < 1) 
			set_Value (COLUMNNAME_EXME_QuantityQual_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_QuantityQual_ID, Integer.valueOf(EXME_QuantityQual_ID));
	}

	/** Get Quantity Qualifier.
		@return Quantity Qualifier	  */
	public int getEXME_QuantityQual_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_QuantityQual_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_StatusEleg getEXME_StatusEleg() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_StatusEleg.Table_Name);
        I_EXME_StatusEleg result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_StatusEleg)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_StatusEleg_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Elegibility Status.
		@param EXME_StatusEleg_ID Elegibility Status	  */
	public void setEXME_StatusEleg_ID (int EXME_StatusEleg_ID)
	{
		if (EXME_StatusEleg_ID < 1) 
			set_Value (COLUMNNAME_EXME_StatusEleg_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_StatusEleg_ID, Integer.valueOf(EXME_StatusEleg_ID));
	}

	/** Get Elegibility Status.
		@return Elegibility Status	  */
	public int getEXME_StatusEleg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_StatusEleg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoProd.Table_Name);
        I_EXME_TipoProd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoProd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoProd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoProd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Plan Network.
		@param IsPlanNetwork Is Plan Network	  */
	public void setIsPlanNetwork (boolean IsPlanNetwork)
	{
		set_Value (COLUMNNAME_IsPlanNetwork, Boolean.valueOf(IsPlanNetwork));
	}

	/** Get Is Plan Network.
		@return Is Plan Network	  */
	public boolean isPlanNetwork () 
	{
		Object oo = get_Value(COLUMNNAME_IsPlanNetwork);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percentage.
		@param Percentage 
		Percent of the entire amount
	  */
	public void setPercentage (BigDecimal Percentage)
	{
		set_Value (COLUMNNAME_Percentage, Percentage);
	}

	/** Get Percentage.
		@return Percent of the entire amount
	  */
	public BigDecimal getPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Percentage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Plan Coverage.
		@param PlanCoverage Plan Coverage	  */
	public void setPlanCoverage (String PlanCoverage)
	{
		set_Value (COLUMNNAME_PlanCoverage, PlanCoverage);
	}

	/** Get Plan Coverage.
		@return Plan Coverage	  */
	public String getPlanCoverage () 
	{
		return (String)get_Value(COLUMNNAME_PlanCoverage);
	}

	/** Set Quantity.
		@param Quantity Quantity	  */
	public void setQuantity (int Quantity)
	{
		set_Value (COLUMNNAME_Quantity, Integer.valueOf(Quantity));
	}

	/** Get Quantity.
		@return Quantity	  */
	public int getQuantity () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Quantity);
		if (ii == null)
			 return 0;
		return ii.intValue();
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