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

/** Generated Model for I_EXME_ProductoOrg
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_ProductoOrg extends PO implements I_I_EXME_ProductoOrg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ProductoOrg (Properties ctx, int I_EXME_ProductoOrg_ID, String trxName)
    {
      super (ctx, I_EXME_ProductoOrg_ID, trxName);
      /** if (I_EXME_ProductoOrg_ID == 0)
        {
			setI_EXME_ProductoOrg_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ProductoOrg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ProductoOrg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Charge.
		@param Charge Charge	  */
	public void setCharge (String Charge)
	{
		set_Value (COLUMNNAME_Charge, Charge);
	}

	/** Get Charge.
		@return Charge	  */
	public String getCharge () 
	{
		return (String)get_Value(COLUMNNAME_Charge);
	}

	/** Set Cost.
		@param Cost 
		Cost information
	  */
	public void setCost (BigDecimal Cost)
	{
		set_Value (COLUMNNAME_Cost, Cost);
	}

	/** Get Cost.
		@return Cost information
	  */
	public BigDecimal getCost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set CPT.
		@param CPT 
		CPT Code
	  */
	public void setCPT (String CPT)
	{
		set_Value (COLUMNNAME_CPT, CPT);
	}

	/** Get CPT.
		@return CPT Code
	  */
	public String getCPT () 
	{
		return (String)get_Value(COLUMNNAME_CPT);
	}

	/** Set Tax Category.
		@param C_TaxCategory_ID 
		Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID)
	{
		if (C_TaxCategory_ID < 1) 
			set_Value (COLUMNNAME_C_TaxCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_TaxCategory_ID, Integer.valueOf(C_TaxCategory_ID));
	}

	/** Get Tax Category.
		@return Tax Category
	  */
	public int getC_TaxCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name of Tax Category.
		@param C_TaxCategory_Name 
		Name of Tax Category
	  */
	public void setC_TaxCategory_Name (String C_TaxCategory_Name)
	{
		set_Value (COLUMNNAME_C_TaxCategory_Name, C_TaxCategory_Name);
	}

	/** Get Name of Tax Category.
		@return Name of Tax Category
	  */
	public String getC_TaxCategory_Name () 
	{
		return (String)get_Value(COLUMNNAME_C_TaxCategory_Name);
	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
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

	/** Set Pack UOM.
		@param C_UOMVolume_ID 
		Unit of measure of volume or packing
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID)
	{
		if (C_UOMVolume_ID < 1) 
			set_Value (COLUMNNAME_C_UOMVolume_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMVolume_ID, Integer.valueOf(C_UOMVolume_ID));
	}

	/** Get Pack UOM.
		@return Unit of measure of volume or packing
	  */
	public int getC_UOMVolume_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMVolume_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Volume Value.
		@param C_UOMVolume_Value 
		UOM of Volume Search Key
	  */
	public void setC_UOMVolume_Value (String C_UOMVolume_Value)
	{
		set_Value (COLUMNNAME_C_UOMVolume_Value, C_UOMVolume_Value);
	}

	/** Get UOM of Volume Value.
		@return UOM of Volume Search Key
	  */
	public String getC_UOMVolume_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOMVolume_Value);
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

	/** Set Effective Date.
		@param Effective_Date Effective Date	  */
	public void setEffective_Date (Timestamp Effective_Date)
	{
		set_Value (COLUMNNAME_Effective_Date, Effective_Date);
	}

	/** Get Effective Date.
		@return Effective Date	  */
	public Timestamp getEffective_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Effective_Date);
	}

	/** Set Modifier 1.
		@param EXME_Modifier1_ID Modifier 1	  */
	public void setEXME_Modifier1_ID (int EXME_Modifier1_ID)
	{
		if (EXME_Modifier1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifier1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifier1_ID, Integer.valueOf(EXME_Modifier1_ID));
	}

	/** Get Modifier 1.
		@return Modifier 1	  */
	public int getEXME_Modifier1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifier1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Modifier 2.
		@param EXME_Modifier2_ID Modifier 2	  */
	public void setEXME_Modifier2_ID (int EXME_Modifier2_ID)
	{
		if (EXME_Modifier2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifier2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifier2_ID, Integer.valueOf(EXME_Modifier2_ID));
	}

	/** Get Modifier 2.
		@return Modifier 2	  */
	public int getEXME_Modifier2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifier2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Modifier 3.
		@param EXME_Modifier3_ID Modifier 3	  */
	public void setEXME_Modifier3_ID (int EXME_Modifier3_ID)
	{
		if (EXME_Modifier3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifier3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifier3_ID, Integer.valueOf(EXME_Modifier3_ID));
	}

	/** Get Modifier 3.
		@return Modifier 3	  */
	public int getEXME_Modifier3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifier3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Modifier 4.
		@param EXME_Modifier4_ID Modifier 4	  */
	public void setEXME_Modifier4_ID (int EXME_Modifier4_ID)
	{
		if (EXME_Modifier4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Modifier4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Modifier4_ID, Integer.valueOf(EXME_Modifier4_ID));
	}

	/** Get Modifier 4.
		@return Modifier 4	  */
	public int getEXME_Modifier4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Modifier4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
	}

	/** Get Family Products.
		@return Family Products
	  */
	public int getEXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProductoOrg getEXME_ProductoOrg() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProductoOrg.Table_Name);
        I_EXME_ProductoOrg result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProductoOrg)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProductoOrg_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Producto Org.
		@param EXME_ProductoOrg_ID Producto Org	  */
	public void setEXME_ProductoOrg_ID (int EXME_ProductoOrg_ID)
	{
		if (EXME_ProductoOrg_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProductoOrg_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProductoOrg_ID, Integer.valueOf(EXME_ProductoOrg_ID));
	}

	/** Get Producto Org.
		@return Producto Org	  */
	public int getEXME_ProductoOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductoOrg_ID);
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

	/** Set HCPCS.
		@param HCPCS 
		HCPCS Code
	  */
	public void setHCPCS (String HCPCS)
	{
		set_Value (COLUMNNAME_HCPCS, HCPCS);
	}

	/** Get HCPCS.
		@return HCPCS Code
	  */
	public String getHCPCS () 
	{
		return (String)get_Value(COLUMNNAME_HCPCS);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Charge Master Import Interface.
		@param I_EXME_ProductoOrg_ID Charge Master Import Interface	  */
	public void setI_EXME_ProductoOrg_ID (int I_EXME_ProductoOrg_ID)
	{
		if (I_EXME_ProductoOrg_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ProductoOrg_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ProductoOrg_ID, Integer.valueOf(I_EXME_ProductoOrg_ID));
	}

	/** Get Charge Master Import Interface.
		@return Charge Master Import Interface	  */
	public int getI_EXME_ProductoOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ProductoOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Covered By Insurance.
		@param IsCoveredByInsurance Is Covered By Insurance	  */
	public void setIsCoveredByInsurance (boolean IsCoveredByInsurance)
	{
		set_Value (COLUMNNAME_IsCoveredByInsurance, Boolean.valueOf(IsCoveredByInsurance));
	}

	/** Get Is Covered By Insurance.
		@return Is Covered By Insurance	  */
	public boolean isCoveredByInsurance () 
	{
		Object oo = get_Value(COLUMNNAME_IsCoveredByInsurance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Professional?.
		@param IsProfessional Is Professional?	  */
	public void setIsProfessional (boolean IsProfessional)
	{
		set_Value (COLUMNNAME_IsProfessional, Boolean.valueOf(IsProfessional));
	}

	/** Get Is Professional?.
		@return Is Professional?	  */
	public boolean isProfessional () 
	{
		Object oo = get_Value(COLUMNNAME_IsProfessional);
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

	/** Set Modifier 1.
		@param Modifier_1 Modifier 1	  */
	public void setModifier_1 (String Modifier_1)
	{
		set_Value (COLUMNNAME_Modifier_1, Modifier_1);
	}

	/** Get Modifier 1.
		@return Modifier 1	  */
	public String getModifier_1 () 
	{
		return (String)get_Value(COLUMNNAME_Modifier_1);
	}

	/** Set Modifier 2.
		@param Modifier_2 Modifier 2	  */
	public void setModifier_2 (String Modifier_2)
	{
		set_Value (COLUMNNAME_Modifier_2, Modifier_2);
	}

	/** Get Modifier 2.
		@return Modifier 2	  */
	public String getModifier_2 () 
	{
		return (String)get_Value(COLUMNNAME_Modifier_2);
	}

	/** Set Modifier 3.
		@param Modifier_3 Modifier 3	  */
	public void setModifier_3 (String Modifier_3)
	{
		set_Value (COLUMNNAME_Modifier_3, Modifier_3);
	}

	/** Get Modifier 3.
		@return Modifier 3	  */
	public String getModifier_3 () 
	{
		return (String)get_Value(COLUMNNAME_Modifier_3);
	}

	/** Set Modifier 4.
		@param Modifier_4 Modifier 4	  */
	public void setModifier_4 (String Modifier_4)
	{
		set_Value (COLUMNNAME_Modifier_4, Modifier_4);
	}

	/** Get Modifier 4.
		@return Modifier 4	  */
	public String getModifier_4 () 
	{
		return (String)get_Value(COLUMNNAME_Modifier_4);
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name Price List.
		@param M_PriceList_Name 
		Name Price List
	  */
	public void setM_PriceList_Name (String M_PriceList_Name)
	{
		set_Value (COLUMNNAME_M_PriceList_Name, M_PriceList_Name);
	}

	/** Get Name Price List.
		@return Name Price List
	  */
	public String getM_PriceList_Name () 
	{
		return (String)get_Value(COLUMNNAME_M_PriceList_Name);
	}

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Category.
		@param M_Product_Category_Value Product Category	  */
	public void setM_Product_Category_Value (String M_Product_Category_Value)
	{
		set_Value (COLUMNNAME_M_Product_Category_Value, M_Product_Category_Value);
	}

	/** Get Product Category.
		@return Product Category	  */
	public String getM_Product_Category_Value () 
	{
		return (String)get_Value(COLUMNNAME_M_Product_Category_Value);
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

	/** Set NDC.
		@param NDC 
		NDC Code
	  */
	public void setNDC (String NDC)
	{
		set_Value (COLUMNNAME_NDC, NDC);
	}

	/** Get NDC.
		@return NDC Code
	  */
	public String getNDC () 
	{
		return (String)get_Value(COLUMNNAME_NDC);
	}

	/** Set Other Code.
		@param Other Other Code	  */
	public void setOther (String Other)
	{
		set_Value (COLUMNNAME_Other, Other);
	}

	/** Get Other Code.
		@return Other Code	  */
	public String getOther () 
	{
		return (String)get_Value(COLUMNNAME_Other);
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pack Price List.
		@param PriceList_Vol 
		Price List for the Pack UOM
	  */
	public void setPriceList_Vol (int PriceList_Vol)
	{
		set_Value (COLUMNNAME_PriceList_Vol, Integer.valueOf(PriceList_Vol));
	}

	/** Get Pack Price List.
		@return Price List for the Pack UOM
	  */
	public int getPriceList_Vol () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PriceList_Vol);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Revenue Code.
		@param Rev_Code Revenue Code	  */
	public void setRev_Code (String Rev_Code)
	{
		set_Value (COLUMNNAME_Rev_Code, Rev_Code);
	}

	/** Get Revenue Code.
		@return Revenue Code	  */
	public String getRev_Code () 
	{
		return (String)get_Value(COLUMNNAME_Rev_Code);
	}

	/** Set Uom Key.
		@param UOM_Value Uom Key	  */
	public void setUOM_Value (String UOM_Value)
	{
		set_Value (COLUMNNAME_UOM_Value, UOM_Value);
	}

	/** Get Uom Key.
		@return Uom Key	  */
	public String getUOM_Value () 
	{
		return (String)get_Value(COLUMNNAME_UOM_Value);
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
}