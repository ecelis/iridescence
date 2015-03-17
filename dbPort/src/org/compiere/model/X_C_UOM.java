/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_UOM
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_UOM extends PO implements I_C_UOM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_UOM (Properties ctx, int C_UOM_ID, String trxName)
    {
      super (ctx, C_UOM_ID, trxName);
      /** if (C_UOM_ID == 0)
        {
			setCostingPrecision (0);
			setC_UOM_ID (0);
			setIsDefault (false);
			setName (null);
			setStdPrecision (0);
			setX12DE355 (null);
        } */
    }

    /** Load Constructor */
    public X_C_UOM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_UOM[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Costing Precision.
		@param CostingPrecision 
		Rounding used costing calculations
	  */
	public void setCostingPrecision (int CostingPrecision)
	{
		set_Value (COLUMNNAME_CostingPrecision, Integer.valueOf(CostingPrecision));
	}

	/** Get Costing Precision.
		@return Rounding used costing calculations
	  */
	public int getCostingPrecision () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CostingPrecision);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
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

	/** SistemaMedicion AD_Reference_ID=1200380 */
	public static final int SISTEMAMEDICION_AD_Reference_ID=1200380;
	/** None = N */
	public static final String SISTEMAMEDICION_None = "N";
	/** Metric System = M */
	public static final String SISTEMAMEDICION_MetricSystem = "M";
	/** English System = E */
	public static final String SISTEMAMEDICION_EnglishSystem = "E";
	/** Set Systems of measurement.
		@param SistemaMedicion 
		Systems of measurement
	  */
	public void setSistemaMedicion (String SistemaMedicion)
	{

		if (SistemaMedicion == null || SistemaMedicion.equals("N") || SistemaMedicion.equals("M") || SistemaMedicion.equals("E")); else throw new IllegalArgumentException ("SistemaMedicion Invalid value - " + SistemaMedicion + " - Reference_ID=1200380 - N - M - E");		set_Value (COLUMNNAME_SistemaMedicion, SistemaMedicion);
	}

	/** Get Systems of measurement.
		@return Systems of measurement
	  */
	public String getSistemaMedicion () 
	{
		return (String)get_Value(COLUMNNAME_SistemaMedicion);
	}

	/** Set Standard Precision.
		@param StdPrecision 
		Rule for rounding  calculated amounts
	  */
	public void setStdPrecision (int StdPrecision)
	{
		set_Value (COLUMNNAME_StdPrecision, Integer.valueOf(StdPrecision));
	}

	/** Get Standard Precision.
		@return Rule for rounding  calculated amounts
	  */
	public int getStdPrecision () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StdPrecision);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Symbol.
		@param UOMSymbol 
		Symbol for a Unit of Measure
	  */
	public void setUOMSymbol (String UOMSymbol)
	{
		set_Value (COLUMNNAME_UOMSymbol, UOMSymbol);
	}

	/** Get Symbol.
		@return Symbol for a Unit of Measure
	  */
	public String getUOMSymbol () 
	{
		return (String)get_Value(COLUMNNAME_UOMSymbol);
	}

	/** UOMType AD_Reference_ID=1200349 */
	public static final int UOMTYPE_AD_Reference_ID=1200349;
	/** Angle = AN */
	public static final String UOMTYPE_Angle = "AN";
	/** Area = AR */
	public static final String UOMTYPE_Area = "AR";
	/** Data Storage = DS */
	public static final String UOMTYPE_DataStorage = "DS";
	/** Density = DE */
	public static final String UOMTYPE_Density = "DE";
	/** Energy = EN */
	public static final String UOMTYPE_Energy = "EN";
	/** Force = FO */
	public static final String UOMTYPE_Force = "FO";
	/** Kitchen Measures = KI */
	public static final String UOMTYPE_KitchenMeasures = "KI";
	/** Length = LE */
	public static final String UOMTYPE_Length = "LE";
	/** Power = PO */
	public static final String UOMTYPE_Power = "PO";
	/** Pressure = PR */
	public static final String UOMTYPE_Pressure = "PR";
	/** Temperature = TE */
	public static final String UOMTYPE_Temperature = "TE";
	/** Time = TM */
	public static final String UOMTYPE_Time = "TM";
	/** Torque = TO */
	public static final String UOMTYPE_Torque = "TO";
	/** Velocity = VE */
	public static final String UOMTYPE_Velocity = "VE";
	/** Volume Liquid = VL */
	public static final String UOMTYPE_VolumeLiquid = "VL";
	/** Volume Dry = VD */
	public static final String UOMTYPE_VolumeDry = "VD";
	/** Weigth = WE */
	public static final String UOMTYPE_Weigth = "WE";
	/** Currency = CU */
	public static final String UOMTYPE_Currency = "CU";
	/** Data Speed = DV */
	public static final String UOMTYPE_DataSpeed = "DV";
	/** Frequency = FR */
	public static final String UOMTYPE_Frequency = "FR";
	/** Other = OT */
	public static final String UOMTYPE_Other = "OT";
	/** Set UOM Type.
		@param UOMType UOM Type	  */
	public void setUOMType (String UOMType)
	{

		if (UOMType == null || UOMType.equals("AN") || UOMType.equals("AR") || UOMType.equals("DS") || UOMType.equals("DE") || UOMType.equals("EN") || UOMType.equals("FO") || UOMType.equals("KI") || UOMType.equals("LE") || UOMType.equals("PO") || UOMType.equals("PR") || UOMType.equals("TE") || UOMType.equals("TM") || UOMType.equals("TO") || UOMType.equals("VE") || UOMType.equals("VL") || UOMType.equals("VD") || UOMType.equals("WE") || UOMType.equals("CU") || UOMType.equals("DV") || UOMType.equals("FR") || UOMType.equals("OT")); else throw new IllegalArgumentException ("UOMType Invalid value - " + UOMType + " - Reference_ID=1200349 - AN - AR - DS - DE - EN - FO - KI - LE - PO - PR - TE - TM - TO - VE - VL - VD - WE - CU - DV - FR - OT");		set_Value (COLUMNNAME_UOMType, UOMType);
	}

	/** Get UOM Type.
		@return UOM Type	  */
	public String getUOMType () 
	{
		return (String)get_Value(COLUMNNAME_UOMType);
	}

	/** Set UOM Code.
		@param X12DE355 
		UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355)
	{
		if (X12DE355 == null)
			throw new IllegalArgumentException ("X12DE355 is mandatory.");
		set_Value (COLUMNNAME_X12DE355, X12DE355);
	}

	/** Get UOM Code.
		@return UOM EDI X12 Code
	  */
	public String getX12DE355 () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355);
	}
}