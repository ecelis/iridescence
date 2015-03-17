/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProductClassWhs
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductClassWhs extends PO implements I_EXME_ProductClassWhs, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductClassWhs (Properties ctx, int EXME_ProductClassWhs_ID, String trxName)
    {
      super (ctx, EXME_ProductClassWhs_ID, trxName);
      /** if (EXME_ProductClassWhs_ID == 0)
        {
			setEXME_ProductClassWhs_ID (0);
			setM_Warehouse_ID (0);
			setProductClass (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductClassWhs (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductClassWhs[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Related product class - service unit.
		@param EXME_ProductClassWhs_ID 
		Related product class - service unit
	  */
	public void setEXME_ProductClassWhs_ID (int EXME_ProductClassWhs_ID)
	{
		if (EXME_ProductClassWhs_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductClassWhs_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductClassWhs_ID, Integer.valueOf(EXME_ProductClassWhs_ID));
	}

	/** Get Related product class - service unit.
		@return Related product class - service unit
	  */
	public int getEXME_ProductClassWhs_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductClassWhs_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
	/** Magnetic Resonance = MR */
	public static final String PRODUCTCLASS_MagneticResonance = "MR";
	/** Computed Tomography = CT */
	public static final String PRODUCTCLASS_ComputedTomography = "CT";
	/** Medical Equipment = NM */
	public static final String PRODUCTCLASS_MedicalEquipment = "NM";
	/** Physical therapy = PT */
	public static final String PRODUCTCLASS_PhysicalTherapy = "PT";
	/** INSTRUMENTAL = IN */
	public static final String PRODUCTCLASS_INSTRUMENTAL = "IN";
	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{
		if (ProductClass == null) throw new IllegalArgumentException ("ProductClass is mandatory");
		if (ProductClass.equals("LA") || ProductClass.equals("XR") || ProductClass.equals("DG") || ProductClass.equals("PR") || ProductClass.equals("IM") || ProductClass.equals("CL") || ProductClass.equals("BL") || ProductClass.equals("SG") || ProductClass.equals("AN") || ProductClass.equals("MS") || ProductClass.equals("TR") || ProductClass.equals("OV") || ProductClass.equals("OS") || ProductClass.equals("AB") || ProductClass.equals("HH") || ProductClass.equals("HP") || ProductClass.equals("FR") || ProductClass.equals("PS") || ProductClass.equals("RM") || ProductClass.equals("TH") || ProductClass.equals("MR") || ProductClass.equals("CT") || ProductClass.equals("NM") || ProductClass.equals("PT") || ProductClass.equals("IN")); else throw new IllegalArgumentException ("ProductClass Invalid value - " + ProductClass + " - Reference_ID=1200509 - LA - XR - DG - PR - IM - CL - BL - SG - AN - MS - TR - OV - OS - AB - HH - HP - FR - PS - RM - TH - MR - CT - NM - PT - IN");		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
	}
}