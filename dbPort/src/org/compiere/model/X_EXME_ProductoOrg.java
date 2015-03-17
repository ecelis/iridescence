/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProductoOrg
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductoOrg extends PO implements I_EXME_ProductoOrg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductoOrg (Properties ctx, int EXME_ProductoOrg_ID, String trxName)
    {
      super (ctx, EXME_ProductoOrg_ID, trxName);
      /** if (EXME_ProductoOrg_ID == 0)
        {
			setConsigna (false);
			setEXME_ProductoOrg_ID (0);
			setIsLot (false);
			setIsObsolete (false);
// N
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductoOrg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductoOrg[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Change price.
		@param CambiaPrecio Change price	  */
	public void setCambiaPrecio (boolean CambiaPrecio)
	{
		set_Value (COLUMNNAME_CambiaPrecio, Boolean.valueOf(CambiaPrecio));
	}

	/** Get Change price.
		@return Change price	  */
	public boolean isCambiaPrecio () 
	{
		Object oo = get_Value(COLUMNNAME_CambiaPrecio);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Consignment Warehosue.
		@param Consigna Is Consignment Warehosue	  */
	public void setConsigna (boolean Consigna)
	{
		set_Value (COLUMNNAME_Consigna, Boolean.valueOf(Consigna));
	}

	/** Get Is Consignment Warehosue.
		@return Is Consignment Warehosue	  */
	public boolean isConsigna () 
	{
		Object oo = get_Value(COLUMNNAME_Consigna);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_TaxCategory.Table_Name);
        I_C_TaxCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_TaxCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_TaxCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Producto Org.
		@param EXME_ProductoOrg_ID Producto Org	  */
	public void setEXME_ProductoOrg_ID (int EXME_ProductoOrg_ID)
	{
		if (EXME_ProductoOrg_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductoOrg_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductoOrg_ID, Integer.valueOf(EXME_ProductoOrg_ID));
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

	/** Set In formulary.
		@param IsFormulary 
		In formulary
	  */
	public void setIsFormulary (boolean IsFormulary)
	{
		set_Value (COLUMNNAME_IsFormulary, Boolean.valueOf(IsFormulary));
	}

	/** Get In formulary.
		@return In formulary
	  */
	public boolean isFormulary () 
	{
		Object oo = get_Value(COLUMNNAME_IsFormulary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lot.
		@param IsLot 
		The product instances have a Lot Number
	  */
	public void setIsLot (boolean IsLot)
	{
		set_Value (COLUMNNAME_IsLot, Boolean.valueOf(IsLot));
	}

	/** Get Lot.
		@return The product instances have a Lot Number
	  */
	public boolean isLot () 
	{
		Object oo = get_Value(COLUMNNAME_IsLot);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Attribute Set.
		@param M_AttributeSet_ID 
		Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID)
	{
		if (M_AttributeSet_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSet_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSet_ID, Integer.valueOf(M_AttributeSet_ID));
	}

	/** Get Attribute Set.
		@return Product Attribute Set
	  */
	public int getM_AttributeSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set M_Product_Rel_ID.
		@param M_Product_Rel_ID M_Product_Rel_ID	  */
	public void setM_Product_Rel_ID (int M_Product_Rel_ID)
	{
		if (M_Product_Rel_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Rel_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Rel_ID, Integer.valueOf(M_Product_Rel_ID));
	}

	/** Get M_Product_Rel_ID.
		@return M_Product_Rel_ID	  */
	public int getM_Product_Rel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Rel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}