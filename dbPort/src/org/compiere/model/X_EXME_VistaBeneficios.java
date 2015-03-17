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

/** Generated Model for EXME_VistaBeneficios
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_VistaBeneficios extends PO implements I_EXME_VistaBeneficios, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VistaBeneficios (Properties ctx, int EXME_VistaBeneficios_ID, String trxName)
    {
      super (ctx, EXME_VistaBeneficios_ID, trxName);
      /** if (EXME_VistaBeneficios_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_VistaBeneficios (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_VistaBeneficios[")
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

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	/** Set EXME_VistaBeneficios_ID.
		@param EXME_VistaBeneficios_ID EXME_VistaBeneficios_ID	  */
	public void setEXME_VistaBeneficios_ID (int EXME_VistaBeneficios_ID)
	{
		if (EXME_VistaBeneficios_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_VistaBeneficios_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_VistaBeneficios_ID, Integer.valueOf(EXME_VistaBeneficios_ID));
	}

	/** Get EXME_VistaBeneficios_ID.
		@return EXME_VistaBeneficios_ID	  */
	public int getEXME_VistaBeneficios_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VistaBeneficios_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsData.
		@param IsData IsData	  */
	public void setIsData (boolean IsData)
	{
		set_Value (COLUMNNAME_IsData, Boolean.valueOf(IsData));
	}

	/** Get IsData.
		@return IsData	  */
	public boolean isData () 
	{
		Object oo = get_Value(COLUMNNAME_IsData);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsData_In.
		@param IsData_In IsData_In	  */
	public void setIsData_In (boolean IsData_In)
	{
		set_Value (COLUMNNAME_IsData_In, Boolean.valueOf(IsData_In));
	}

	/** Get IsData_In.
		@return IsData_In	  */
	public boolean isData_In () 
	{
		Object oo = get_Value(COLUMNNAME_IsData_In);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsData_Out.
		@param IsData_Out IsData_Out	  */
	public void setIsData_Out (boolean IsData_Out)
	{
		set_Value (COLUMNNAME_IsData_Out, Boolean.valueOf(IsData_Out));
	}

	/** Get IsData_Out.
		@return IsData_Out	  */
	public boolean isData_Out () 
	{
		Object oo = get_Value(COLUMNNAME_IsData_Out);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Valores.
		@param Valores Valores	  */
	public void setValores (String Valores)
	{
		set_Value (COLUMNNAME_Valores, Valores);
	}

	/** Get Valores.
		@return Valores	  */
	public String getValores () 
	{
		return (String)get_Value(COLUMNNAME_Valores);
	}

	/** Set Valores_In.
		@param Valores_In Valores_In	  */
	public void setValores_In (String Valores_In)
	{
		set_Value (COLUMNNAME_Valores_In, Valores_In);
	}

	/** Get Valores_In.
		@return Valores_In	  */
	public String getValores_In () 
	{
		return (String)get_Value(COLUMNNAME_Valores_In);
	}

	/** Set Valores_Out.
		@param Valores_Out Valores_Out	  */
	public void setValores_Out (String Valores_Out)
	{
		set_Value (COLUMNNAME_Valores_Out, Valores_Out);
	}

	/** Get Valores_Out.
		@return Valores_Out	  */
	public String getValores_Out () 
	{
		return (String)get_Value(COLUMNNAME_Valores_Out);
	}
}