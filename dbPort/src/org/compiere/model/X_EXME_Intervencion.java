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

/** Generated Model for EXME_Intervencion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Intervencion extends PO implements I_EXME_Intervencion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Intervencion (Properties ctx, int EXME_Intervencion_ID, String trxName)
    {
      super (ctx, EXME_Intervencion_ID, trxName);
      /** if (EXME_Intervencion_ID == 0)
        {
			setEXME_Intervencion_ID (0);
			setName (null);
			setProcedureGroup (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Intervencion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Intervencion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Code GRD.
		@param CodGrd 
		Code GRD
	  */
	public void setCodGrd (String CodGrd)
	{
		set_Value (COLUMNNAME_CodGrd, CodGrd);
	}

	/** Get Code GRD.
		@return Code GRD
	  */
	public String getCodGrd () 
	{
		return (String)get_Value(COLUMNNAME_CodGrd);
	}

	/** Set Code INEGI.
		@param CodInegi 
		Code INEGI
	  */
	public void setCodInegi (String CodInegi)
	{
		set_Value (COLUMNNAME_CodInegi, CodInegi);
	}

	/** Get Code INEGI.
		@return Code INEGI
	  */
	public String getCodInegi () 
	{
		return (String)get_Value(COLUMNNAME_CodInegi);
	}

	/** Set World Organization Health Code.
		@param CodOms 
		World Organization Health Code
	  */
	public void setCodOms (String CodOms)
	{
		set_Value (COLUMNNAME_CodOms, CodOms);
	}

	/** Get World Organization Health Code.
		@return World Organization Health Code
	  */
	public String getCodOms () 
	{
		return (String)get_Value(COLUMNNAME_CodOms);
	}

	/** Set Snomed Code.
		@param CodSnomed 
		Snomed Code
	  */
	public void setCodSnomed (String CodSnomed)
	{
		set_Value (COLUMNNAME_CodSnomed, CodSnomed);
	}

	/** Get Snomed Code.
		@return Snomed Code
	  */
	public String getCodSnomed () 
	{
		return (String)get_Value(COLUMNNAME_CodSnomed);
	}

	/** Set Cleaning.
		@param CostoLimpStd 
		Cleaning Standard Cost
	  */
	public void setCostoLimpStd (BigDecimal CostoLimpStd)
	{
		set_Value (COLUMNNAME_CostoLimpStd, CostoLimpStd);
	}

	/** Get Cleaning.
		@return Cleaning Standard Cost
	  */
	public BigDecimal getCostoLimpStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostoLimpStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Preparation.
		@param CostoPrepStd 
		Preparation Standard Cost
	  */
	public void setCostoPrepStd (BigDecimal CostoPrepStd)
	{
		set_Value (COLUMNNAME_CostoPrepStd, CostoPrepStd);
	}

	/** Get Preparation.
		@return Preparation Standard Cost
	  */
	public BigDecimal getCostoPrepStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostoPrepStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Use.
		@param CostoUsoStd 
		Use Standard Cost
	  */
	public void setCostoUsoStd (BigDecimal CostoUsoStd)
	{
		set_Value (COLUMNNAME_CostoUsoStd, CostoUsoStd);
	}

	/** Get Use.
		@return Use Standard Cost
	  */
	public BigDecimal getCostoUsoStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostoUsoStd);
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

	public I_EXME_IntervencionHdr getEXME_IntervencionHdr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_IntervencionHdr.Table_Name);
        I_EXME_IntervencionHdr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_IntervencionHdr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_IntervencionHdr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set ICD 9 Vol III.
		@param EXME_IntervencionHdr_ID ICD 9 Vol III	  */
	public void setEXME_IntervencionHdr_ID (int EXME_IntervencionHdr_ID)
	{
		if (EXME_IntervencionHdr_ID < 1) 
			set_Value (COLUMNNAME_EXME_IntervencionHdr_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_IntervencionHdr_ID, Integer.valueOf(EXME_IntervencionHdr_ID));
	}

	/** Get ICD 9 Vol III.
		@return ICD 9 Vol III	  */
	public int getEXME_IntervencionHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IntervencionHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CPT Code.
		@param EXME_Intervencion_ID 
		Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Intervencion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get CPT Code.
		@return Current Procedural Terminology (CPT) 
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RevenueCode.Table_Name);
        I_EXME_RevenueCode result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RevenueCode)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RevenueCode_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Revenue Code.
		@param EXME_RevenueCode_ID Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID)
	{
		if (EXME_RevenueCode_ID < 1) 
			set_Value (COLUMNNAME_EXME_RevenueCode_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RevenueCode_ID, Integer.valueOf(EXME_RevenueCode_ID));
	}

	/** Get Revenue Code.
		@return Revenue Code	  */
	public int getEXME_RevenueCode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RevenueCode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** HCPCS_Level AD_Reference_ID=1200554 */
	public static final int HCPCS_LEVEL_AD_Reference_ID=1200554;
	/** 1 = 1 */
	public static final String HCPCS_LEVEL_1 = "1";
	/** 2 = 2 */
	public static final String HCPCS_LEVEL_2 = "2";
	/** Set HCPCS Level.
		@param HCPCS_Level 
		HCPCS Level
	  */
	public void setHCPCS_Level (String HCPCS_Level)
	{

		if (HCPCS_Level == null || HCPCS_Level.equals("1") || HCPCS_Level.equals("2")); else throw new IllegalArgumentException ("HCPCS_Level Invalid value - " + HCPCS_Level + " - Reference_ID=1200554 - 1 - 2");		set_Value (COLUMNNAME_HCPCS_Level, HCPCS_Level);
	}

	/** Get HCPCS Level.
		@return HCPCS Level
	  */
	public String getHCPCS_Level () 
	{
		return (String)get_Value(COLUMNNAME_HCPCS_Level);
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

	/** ProcedureGroup AD_Reference_ID=1200456 */
	public static final int PROCEDUREGROUP_AD_Reference_ID=1200456;
	/** Office Visit = OV */
	public static final String PROCEDUREGROUP_OfficeVisit = "OV";
	/** Surgeries = SU */
	public static final String PROCEDUREGROUP_Surgeries = "SU";
	/** Injections = IN */
	public static final String PROCEDUREGROUP_Injections = "IN";
	/** Immunizations = IM */
	public static final String PROCEDUREGROUP_Immunizations = "IM";
	/** Laboratoy = LB */
	public static final String PROCEDUREGROUP_Laboratoy = "LB";
	/** Procedures = PR */
	public static final String PROCEDUREGROUP_Procedures = "PR";
	/** Cultures = CL */
	public static final String PROCEDUREGROUP_Cultures = "CL";
	/** XRay = XR */
	public static final String PROCEDUREGROUP_XRay = "XR";
	/** Set Procedure Group.
		@param ProcedureGroup Procedure Group	  */
	public void setProcedureGroup (String ProcedureGroup)
	{
		if (ProcedureGroup == null) throw new IllegalArgumentException ("ProcedureGroup is mandatory");
		if (ProcedureGroup.equals("OV") || ProcedureGroup.equals("SU") || ProcedureGroup.equals("IN") || ProcedureGroup.equals("IM") || ProcedureGroup.equals("LB") || ProcedureGroup.equals("PR") || ProcedureGroup.equals("CL") || ProcedureGroup.equals("XR")); else throw new IllegalArgumentException ("ProcedureGroup Invalid value - " + ProcedureGroup + " - Reference_ID=1200456 - OV - SU - IN - IM - LB - PR - CL - XR");		set_Value (COLUMNNAME_ProcedureGroup, ProcedureGroup);
	}

	/** Get Procedure Group.
		@return Procedure Group	  */
	public String getProcedureGroup () 
	{
		return (String)get_Value(COLUMNNAME_ProcedureGroup);
	}

	/** Set Cleaning.
		@param TiempoLimpStd 
		cleaning standard time
	  */
	public void setTiempoLimpStd (BigDecimal TiempoLimpStd)
	{
		set_Value (COLUMNNAME_TiempoLimpStd, TiempoLimpStd);
	}

	/** Get Cleaning.
		@return cleaning standard time
	  */
	public BigDecimal getTiempoLimpStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TiempoLimpStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Preparation.
		@param TiempoPrepStd 
		preparation standard time
	  */
	public void setTiempoPrepStd (BigDecimal TiempoPrepStd)
	{
		set_Value (COLUMNNAME_TiempoPrepStd, TiempoPrepStd);
	}

	/** Get Preparation.
		@return preparation standard time
	  */
	public BigDecimal getTiempoPrepStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TiempoPrepStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Use.
		@param TiempoUsoStd 
		use standard time
	  */
	public void setTiempoUsoStd (BigDecimal TiempoUsoStd)
	{
		set_Value (COLUMNNAME_TiempoUsoStd, TiempoUsoStd);
	}

	/** Get Use.
		@return use standard time
	  */
	public BigDecimal getTiempoUsoStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TiempoUsoStd);
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