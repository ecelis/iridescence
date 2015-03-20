/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProductInterv
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductInterv extends PO implements I_EXME_ProductInterv, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductInterv (Properties ctx, int EXME_ProductInterv_ID, String trxName)
    {
      super (ctx, EXME_ProductInterv_ID, trxName);
      /** if (EXME_ProductInterv_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductInterv (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductInterv[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set CPT Code.
		@param EXME_Intervencion_ID 
		Current Procedural Terminology (CPT) 
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
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

	/** Set Product - CPT.
		@param EXME_ProductInterv_ID Product - CPT	  */
	public void setEXME_ProductInterv_ID (int EXME_ProductInterv_ID)
	{
		if (EXME_ProductInterv_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_ProductInterv_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_ProductInterv_ID, Integer.valueOf(EXME_ProductInterv_ID));
	}

	/** Get Product - CPT.
		@return Product - CPT	  */
	public int getEXME_ProductInterv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductInterv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set InterValue.
		@param InterValue InterValue	  */
	public void setInterValue (String InterValue)
	{
		set_Value (COLUMNNAME_InterValue, InterValue);
	}

	/** Get InterValue.
		@return InterValue	  */
	public String getInterValue () 
	{
		return (String)get_Value(COLUMNNAME_InterValue);
	}

	/** Set CPT.
		@param Intervencion CPT	  */
	public void setIntervencion (String Intervencion)
	{
		set_Value (COLUMNNAME_Intervencion, Intervencion);
	}

	/** Get CPT.
		@return CPT	  */
	public String getIntervencion () 
	{
		return (String)get_Value(COLUMNNAME_Intervencion);
	}

	/** ProductClass AD_Reference_ID=1200509 */
	public static final int PRODUCTCLASS_AD_Reference_ID=1200509;
	/** Laboratory = LA */
	public static final String PRODUCTCLASS_Laboratory = "LA";
	/** X Ray = XR */
	public static final String PRODUCTCLASS_XRay = "XR";
	/** Drug = DG */
	public static final String PRODUCTCLASS_Drug = "DG";
	/** Procedures = PR */
	public static final String PRODUCTCLASS_Procedures = "PR";
	/** Immunization = IM */
	public static final String PRODUCTCLASS_Immunization = "IM";
	/** Cultures = CL */
	public static final String PRODUCTCLASS_Cultures = "CL";
	/** Blood = BL */
	public static final String PRODUCTCLASS_Blood = "BL";
	/** Surgeries = SG */
	public static final String PRODUCTCLASS_Surgeries = "SG";
	/** Anesthesic = AN */
	public static final String PRODUCTCLASS_Anesthesic = "AN";
	/** Medical Supplies = MS */
	public static final String PRODUCTCLASS_MedicalSupplies = "MS";
	/** Treatment = TR */
	public static final String PRODUCTCLASS_Treatment = "TR";
	/** Office Visit = OV */
	public static final String PRODUCTCLASS_OfficeVisit = "OV";
	/** Other Service = OS */
	public static final String PRODUCTCLASS_OtherService = "OS";
	/** Ambulance = AB */
	public static final String PRODUCTCLASS_Ambulance = "AB";
	/** Home Healt = HH */
	public static final String PRODUCTCLASS_HomeHealt = "HH";
	/** Hospice = HP */
	public static final String PRODUCTCLASS_Hospice = "HP";
	/** FQHC/RHC = FR */
	public static final String PRODUCTCLASS_FQHCRHC = "FR";
	/** Physician Services = PS */
	public static final String PRODUCTCLASS_PhysicianServices = "PS";
	/** Rooms = RM */
	public static final String PRODUCTCLASS_Rooms = "RM";
	/** Others = TH */
	public static final String PRODUCTCLASS_Others = "TH";
	/** MR = MR */
	public static final String PRODUCTCLASS_MR = "MR";
	/** CT = CT */
	public static final String PRODUCTCLASS_CT = "CT";
	/** NM = NM */
	public static final String PRODUCTCLASS_NM = "NM";
	/** PT = PT */
	public static final String PRODUCTCLASS_PT = "PT";
	/** INSTRUMENTAL = IN */
	public static final String PRODUCTCLASS_INSTRUMENTAL = "IN";
	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{

		if (ProductClass == null || ProductClass.equals("LA") || ProductClass.equals("XR") || ProductClass.equals("DG") || ProductClass.equals("PR") || ProductClass.equals("IM") || ProductClass.equals("CL") || ProductClass.equals("BL") || ProductClass.equals("SG") || ProductClass.equals("AN") || ProductClass.equals("MS") || ProductClass.equals("TR") || ProductClass.equals("OV") || ProductClass.equals("OS") || ProductClass.equals("AB") || ProductClass.equals("HH") || ProductClass.equals("HP") || ProductClass.equals("FR") || ProductClass.equals("PS") || ProductClass.equals("RM") || ProductClass.equals("TH") || ProductClass.equals("MR") || ProductClass.equals("CT") || ProductClass.equals("NM") || ProductClass.equals("PT") || ProductClass.equals("IN")); else throw new IllegalArgumentException ("ProductClass Invalid value - " + ProductClass + " - Reference_ID=1200509 - LA - XR - DG - PR - IM - CL - BL - SG - AN - MS - TR - OV - OS - AB - HH - HP - FR - PS - RM - TH - MR - CT - NM - PT - IN");		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
	}

	/** Set Product Name.
		@param ProductName 
		Name of the Product
	  */
	public void setProductName (String ProductName)
	{
		set_Value (COLUMNNAME_ProductName, ProductName);
	}

	/** Get Product Name.
		@return Name of the Product
	  */
	public String getProductName () 
	{
		return (String)get_Value(COLUMNNAME_ProductName);
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
	}
}