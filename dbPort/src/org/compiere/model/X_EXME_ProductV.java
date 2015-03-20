/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ProductV
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductV extends PO implements I_EXME_ProductV, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductV (Properties ctx, int EXME_ProductV_ID, String trxName)
    {
      super (ctx, EXME_ProductV_ID, trxName);
      /** if (EXME_ProductV_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductV (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductV[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Product View.
		@param EXME_ProductV_ID Product View	  */
	public void setEXME_ProductV_ID (int EXME_ProductV_ID)
	{
		if (EXME_ProductV_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_ProductV_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_ProductV_ID, Integer.valueOf(EXME_ProductV_ID));
	}

	/** Get Product View.
		@return Product View	  */
	public int getEXME_ProductV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Is Obsolete.
		@param IsObsolete 
		The product is obsolete
	  */
	public void setIsObsolete (boolean IsObsolete)
	{
		set_Value (COLUMNNAME_IsObsolete, Boolean.valueOf(IsObsolete));
	}

	/** Get Is Obsolete.
		@return The product is obsolete
	  */
	public boolean isObsolete () 
	{
		Object oo = get_Value(COLUMNNAME_IsObsolete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Stocked.
		@param IsStocked 
		Organization stocks this product
	  */
	public void setIsStocked (boolean IsStocked)
	{
		set_Value (COLUMNNAME_IsStocked, Boolean.valueOf(IsStocked));
	}

	/** Get Stocked.
		@return Organization stocks this product
	  */
	public boolean isStocked () 
	{
		Object oo = get_Value(COLUMNNAME_IsStocked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Featured in Web Store.
		@param IsWebStoreFeatured 
		If selected, the product is displayed in the inital or any empy search
	  */
	public void setIsWebStoreFeatured (boolean IsWebStoreFeatured)
	{
		set_Value (COLUMNNAME_IsWebStoreFeatured, Boolean.valueOf(IsWebStoreFeatured));
	}

	/** Get Featured in Web Store.
		@return If selected, the product is displayed in the inital or any empy search
	  */
	public boolean isWebStoreFeatured () 
	{
		Object oo = get_Value(COLUMNNAME_IsWebStoreFeatured);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

		if (ProductClass == null || ProductClass.equals("LA") || ProductClass.equals("XR") || ProductClass.equals("DG") || ProductClass.equals("PR") || ProductClass.equals("IM") || ProductClass.equals("CL") || ProductClass.equals("BL") || ProductClass.equals("SG") || ProductClass.equals("AN") || ProductClass.equals("MS") || ProductClass.equals("TR") || ProductClass.equals("OV") || ProductClass.equals("OS") || ProductClass.equals("AB") || ProductClass.equals("HH") || ProductClass.equals("HP") || ProductClass.equals("FR") || ProductClass.equals("PS") || ProductClass.equals("RM") || ProductClass.equals("TH") || ProductClass.equals("MR") || ProductClass.equals("CT") || ProductClass.equals("NM") || ProductClass.equals("PT") || ProductClass.equals("IN")); else throw new IllegalArgumentException ("ProductClass Invalid value - " + ProductClass + " - Reference_ID=1200509 - LA - XR - DG - PR - IM - CL - BL - SG - AN - MS - TR - OV - OS - AB - HH - HP - FR - PS - RM - TH - MR - CT - NM - PT - IN");		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
	}

	/** ProductType AD_Reference_ID=270 */
	public static final int PRODUCTTYPE_AD_Reference_ID=270;
	/** Item = I */
	public static final String PRODUCTTYPE_Item = "I";
	/** Service = S */
	public static final String PRODUCTTYPE_Service = "S";
	/** Resource = R */
	public static final String PRODUCTTYPE_Resource = "R";
	/** Expense type = E */
	public static final String PRODUCTTYPE_ExpenseType = "E";
	/** Online = O */
	public static final String PRODUCTTYPE_Online = "O";
	/** Package = P */
	public static final String PRODUCTTYPE_Package = "P";
	/** Set Product Type.
		@param ProductType 
		Type of product
	  */
	public void setProductType (String ProductType)
	{

		if (ProductType == null || ProductType.equals("I") || ProductType.equals("S") || ProductType.equals("R") || ProductType.equals("E") || ProductType.equals("O") || ProductType.equals("P")); else throw new IllegalArgumentException ("ProductType Invalid value - " + ProductType + " - Reference_ID=270 - I - S - R - E - O - P");		set_Value (COLUMNNAME_ProductType, ProductType);
	}

	/** Get Product Type.
		@return Type of product
	  */
	public String getProductType () 
	{
		return (String)get_Value(COLUMNNAME_ProductType);
	}

	/** Set Unused.
		@param Unused Unused	  */
	public void setUnused (boolean Unused)
	{
		set_Value (COLUMNNAME_Unused, Boolean.valueOf(Unused));
	}

	/** Get Unused.
		@return Unused	  */
	public boolean isUnused () 
	{
		Object oo = get_Value(COLUMNNAME_Unused);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set UPC/EAN.
		@param UPC 
		Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC)
	{
		set_Value (COLUMNNAME_UPC, UPC);
	}

	/** Get UPC/EAN.
		@return Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC () 
	{
		return (String)get_Value(COLUMNNAME_UPC);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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