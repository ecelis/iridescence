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
import org.compiere.util.KeyNamePair;

/** Generated Model for I_EXME_Articulos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Articulos extends PO implements I_I_EXME_Articulos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Articulos (Properties ctx, int I_EXME_Articulos_ID, String trxName)
    {
      super (ctx, I_EXME_Articulos_ID, trxName);
      /** if (I_EXME_Articulos_ID == 0)
        {
			setI_EXME_Articulos_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Articulos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Articulos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Active.
		@param Activo Active	  */
	public void setActivo (boolean Activo)
	{
		set_Value (COLUMNNAME_Activo, Boolean.valueOf(Activo));
	}

	/** Get Active.
		@return Active	  */
	public boolean isActivo () 
	{
		Object oo = get_Value(COLUMNNAME_Activo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Duty.
		@param Arancel 
		Define the tax on imports or exports of goods
	  */
	public void setArancel (String Arancel)
	{
		set_Value (COLUMNNAME_Arancel, Arancel);
	}

	/** Get Duty.
		@return Define the tax on imports or exports of goods
	  */
	public String getArancel () 
	{
		return (String)get_Value(COLUMNNAME_Arancel);
	}

	/** Set AwaitedLife.
		@param AwaitedLife AwaitedLife	  */
	public void setAwaitedLife (String AwaitedLife)
	{
		set_Value (COLUMNNAME_AwaitedLife, AwaitedLife);
	}

	/** Get AwaitedLife.
		@return AwaitedLife	  */
	public String getAwaitedLife () 
	{
		return (String)get_Value(COLUMNNAME_AwaitedLife);
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

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Business Partner Value.
		@param C_BPartner_Value Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value)
	{
		set_Value (COLUMNNAME_C_BPartner_Value, C_BPartner_Value);
	}

	/** Get Business Partner Value.
		@return Business Partner Value	  */
	public String getC_BPartner_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_BPartner_Value);
	}

	public I_C_Currency getC_Currency() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Currency_Value.
		@param C_Currency_Value C_Currency_Value	  */
	public void setC_Currency_Value (String C_Currency_Value)
	{
		set_Value (COLUMNNAME_C_Currency_Value, C_Currency_Value);
	}

	/** Get C_Currency_Value.
		@return C_Currency_Value	  */
	public String getC_Currency_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_Currency_Value);
	}

	/** Set Classification.
		@param Classification 
		Classification for grouping
	  */
	public void setClassification (boolean Classification)
	{
		set_Value (COLUMNNAME_Classification, Boolean.valueOf(Classification));
	}

	/** Get Classification.
		@return Classification for grouping
	  */
	public boolean isClassification () 
	{
		Object oo = get_Value(COLUMNNAME_Classification);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Code_Bol.
		@param Code_Bol Code_Bol	  */
	public void setCode_Bol (String Code_Bol)
	{
		set_Value (COLUMNNAME_Code_Bol, Code_Bol);
	}

	/** Get Code_Bol.
		@return Code_Bol	  */
	public String getCode_Bol () 
	{
		return (String)get_Value(COLUMNNAME_Code_Bol);
	}

	/** Set Buyer.
		@param Comprador Buyer	  */
	public void setComprador (String Comprador)
	{
		set_Value (COLUMNNAME_Comprador, Comprador);
	}

	/** Get Buyer.
		@return Buyer	  */
	public String getComprador () 
	{
		return (String)get_Value(COLUMNNAME_Comprador);
	}

	/** Set Costing Method.
		@param CostingMethod 
		Indicates how Costs will be calculated
	  */
	public void setCostingMethod (boolean CostingMethod)
	{
		set_Value (COLUMNNAME_CostingMethod, Boolean.valueOf(CostingMethod));
	}

	/** Get Costing Method.
		@return Indicates how Costs will be calculated
	  */
	public boolean isCostingMethod () 
	{
		Object oo = get_Value(COLUMNNAME_CostingMethod);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ISO Country Code.
		@param CountryCode 
		Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode)
	{
		set_Value (COLUMNNAME_CountryCode, CountryCode);
	}

	/** Get ISO Country Code.
		@return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode () 
	{
		return (String)get_Value(COLUMNNAME_CountryCode);
	}

	/** Set CreateDate.
		@param CreateDate CreateDate	  */
	public void setCreateDate (Timestamp CreateDate)
	{
		set_ValueNoCheck (COLUMNNAME_CreateDate, CreateDate);
	}

	/** Get CreateDate.
		@return CreateDate	  */
	public Timestamp getCreateDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CreateDate);
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

	/** Set C_TaxCategory_Value.
		@param C_TaxCategory_Value C_TaxCategory_Value	  */
	public void setC_TaxCategory_Value (String C_TaxCategory_Value)
	{
		set_Value (COLUMNNAME_C_TaxCategory_Value, C_TaxCategory_Value);
	}

	/** Get C_TaxCategory_Value.
		@return C_TaxCategory_Value	  */
	public String getC_TaxCategory_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_TaxCategory_Value);
	}

	/** Set Questionnaire.
		@param Cuestionario 
		Questionnaire
	  */
	public void setCuestionario (String Cuestionario)
	{
		set_Value (COLUMNNAME_Cuestionario, Cuestionario);
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public String getCuestionario () 
	{
		return (String)get_Value(COLUMNNAME_Cuestionario);
	}

	public I_C_UOM getC_UOM() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM.Table_Name);
        I_C_UOM result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set C_UOM_Value.
		@param C_UOM_Value C_UOM_Value	  */
	public void setC_UOM_Value (String C_UOM_Value)
	{
		set_Value (COLUMNNAME_C_UOM_Value, C_UOM_Value);
	}

	/** Get C_UOM_Value.
		@return C_UOM_Value	  */
	public String getC_UOM_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOM_Value);
	}

	/** Set Payment Description.
		@param Desc_Pago Payment Description	  */
	public void setDesc_Pago (String Desc_Pago)
	{
		set_Value (COLUMNNAME_Desc_Pago, Desc_Pago);
	}

	/** Get Payment Description.
		@return Payment Description	  */
	public String getDesc_Pago () 
	{
		return (String)get_Value(COLUMNNAME_Desc_Pago);
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

	public I_EXME_ConceptoFac getEXME_ConceptoFac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConceptoFac.Table_Name);
        I_EXME_ConceptoFac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConceptoFac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConceptoFac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice Concept.
		@param EXME_ConceptoFac_ID Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID)
	{
		if (EXME_ConceptoFac_ID < 1) 
			set_Value (COLUMNNAME_EXME_ConceptoFac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ConceptoFac_ID, Integer.valueOf(EXME_ConceptoFac_ID));
	}

	/** Get Invoice Concept.
		@return Invoice Concept	  */
	public int getEXME_ConceptoFac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConceptoFac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice Concept's Value.
		@param EXME_ConceptoFac_Value 
		Invoice Concept's Value
	  */
	public void setEXME_ConceptoFac_Value (String EXME_ConceptoFac_Value)
	{
		set_Value (COLUMNNAME_EXME_ConceptoFac_Value, EXME_ConceptoFac_Value);
	}

	/** Get Invoice Concept's Value.
		@return Invoice Concept's Value
	  */
	public String getEXME_ConceptoFac_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_ConceptoFac_Value);
	}

	public I_EXME_FactorPre getEXME_FactorPre() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FactorPre.Table_Name);
        I_EXME_FactorPre result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FactorPre)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FactorPre_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price Factor.
		@param EXME_FactorPre_ID 
		Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID)
	{
		if (EXME_FactorPre_ID < 1) 
			set_Value (COLUMNNAME_EXME_FactorPre_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FactorPre_ID, Integer.valueOf(EXME_FactorPre_ID));
	}

	/** Get Price Factor.
		@return Sales Price Factor
	  */
	public int getEXME_FactorPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price Factor Key.
		@param EXME_FactorPre_Value 
		Price Factor Key
	  */
	public void setEXME_FactorPre_Value (String EXME_FactorPre_Value)
	{
		set_Value (COLUMNNAME_EXME_FactorPre_Value, EXME_FactorPre_Value);
	}

	/** Get Price Factor Key.
		@return Price Factor Key
	  */
	public String getEXME_FactorPre_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_FactorPre_Value);
	}

	public I_EXME_ProductFam getEXME_ProductFam() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProductFam.Table_Name);
        I_EXME_ProductFam result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProductFam)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProductFam_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Family Products Code.
		@param EXME_ProductFam_Value 
		Family products Code
	  */
	public void setEXME_ProductFam_Value (String EXME_ProductFam_Value)
	{
		set_Value (COLUMNNAME_EXME_ProductFam_Value, EXME_ProductFam_Value);
	}

	/** Get Family Products Code.
		@return Family products Code
	  */
	public String getEXME_ProductFam_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_ProductFam_Value);
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

	/** Set Subtype Product Code.
		@param EXME_TipoProd_Value 
		Subtypy Product Code
	  */
	public void setEXME_TipoProd_Value (String EXME_TipoProd_Value)
	{
		set_Value (COLUMNNAME_EXME_TipoProd_Value, EXME_TipoProd_Value);
	}

	/** Get Subtype Product Code.
		@return Subtypy Product Code
	  */
	public String getEXME_TipoProd_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TipoProd_Value);
	}

	/** Set GeneraExpClinico.
		@param GeneraExpClinico GeneraExpClinico	  */
	public void setGeneraExpClinico (String GeneraExpClinico)
	{
		set_Value (COLUMNNAME_GeneraExpClinico, GeneraExpClinico);
	}

	/** Get GeneraExpClinico.
		@return GeneraExpClinico	  */
	public String getGeneraExpClinico () 
	{
		return (String)get_Value(COLUMNNAME_GeneraExpClinico);
	}

	/** Set Generico.
		@param Generico Generico	  */
	public void setGenerico (String Generico)
	{
		set_Value (COLUMNNAME_Generico, Generico);
	}

	/** Get Generico.
		@return Generico	  */
	public String getGenerico () 
	{
		return (String)get_Value(COLUMNNAME_Generico);
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

	/** Set I_EXME_Articulos_ID.
		@param I_EXME_Articulos_ID I_EXME_Articulos_ID	  */
	public void setI_EXME_Articulos_ID (int I_EXME_Articulos_ID)
	{
		if (I_EXME_Articulos_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Articulos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Articulos_ID, Integer.valueOf(I_EXME_Articulos_ID));
	}

	/** Get I_EXME_Articulos_ID.
		@return I_EXME_Articulos_ID	  */
	public int getI_EXME_Articulos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Articulos_ID);
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

	/** Set Inspeccion.
		@param Inspeccion Inspeccion	  */
	public void setInspeccion (String Inspeccion)
	{
		set_Value (COLUMNNAME_Inspeccion, Inspeccion);
	}

	/** Get Inspeccion.
		@return Inspeccion	  */
	public String getInspeccion () 
	{
		return (String)get_Value(COLUMNNAME_Inspeccion);
	}

	/** Set MedidaVenta.
		@param MedidaVenta MedidaVenta	  */
	public void setMedidaVenta (String MedidaVenta)
	{
		set_Value (COLUMNNAME_MedidaVenta, MedidaVenta);
	}

	/** Get MedidaVenta.
		@return MedidaVenta	  */
	public String getMedidaVenta () 
	{
		return (String)get_Value(COLUMNNAME_MedidaVenta);
	}

	/** Set MLI.
		@param MLI MLI	  */
	public void setMLI (String MLI)
	{
		set_Value (COLUMNNAME_MLI, MLI);
	}

	/** Get MLI.
		@return MLI	  */
	public String getMLI () 
	{
		return (String)get_Value(COLUMNNAME_MLI);
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

	/** Set Product Code.
		@param M_Product_Value 
		product search Code
	  */
	public void setM_Product_Value (String M_Product_Value)
	{
		set_Value (COLUMNNAME_M_Product_Value, M_Product_Value);
	}

	/** Get Product Code.
		@return product search Code
	  */
	public String getM_Product_Value () 
	{
		return (String)get_Value(COLUMNNAME_M_Product_Value);
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

	/** Set Number of Sales Charges.
		@param NoCargosVentas Number of Sales Charges	  */
	public void setNoCargosVentas (String NoCargosVentas)
	{
		set_Value (COLUMNNAME_NoCargosVentas, NoCargosVentas);
	}

	/** Get Number of Sales Charges.
		@return Number of Sales Charges	  */
	public String getNoCargosVentas () 
	{
		return (String)get_Value(COLUMNNAME_NoCargosVentas);
	}

	/** Set Order Pack Qty.
		@param Order_Pack 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (BigDecimal Order_Pack)
	{
		set_Value (COLUMNNAME_Order_Pack, Order_Pack);
	}

	/** Get Order Pack Qty.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Order_Pack);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Order Policy.
		@param OrderPolicy Order Policy	  */
	public void setOrderPolicy (String OrderPolicy)
	{
		set_Value (COLUMNNAME_OrderPolicy, OrderPolicy);
	}

	/** Get Order Policy.
		@return Order Policy	  */
	public String getOrderPolicy () 
	{
		return (String)get_Value(COLUMNNAME_OrderPolicy);
	}

	/** Set Permit Back Order.
		@param PermitBackOrder Permit Back Order	  */
	public void setPermitBackOrder (String PermitBackOrder)
	{
		set_Value (COLUMNNAME_PermitBackOrder, PermitBackOrder);
	}

	/** Get Permit Back Order.
		@return Permit Back Order	  */
	public String getPermitBackOrder () 
	{
		return (String)get_Value(COLUMNNAME_PermitBackOrder);
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

	/** Set Product Type.
		@param ProductType 
		Type of product
	  */
	public void setProductType (boolean ProductType)
	{
		set_Value (COLUMNNAME_ProductType, Boolean.valueOf(ProductType));
	}

	/** Get Product Type.
		@return Type of product
	  */
	public boolean isProductType () 
	{
		Object oo = get_Value(COLUMNNAME_ProductType);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Operational Unit.
		@param Unidad_Operativa Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa)
	{
		set_Value (COLUMNNAME_Unidad_Operativa, Unidad_Operativa);
	}

	/** Get Operational Unit.
		@return Operational Unit	  */
	public String getUnidad_Operativa () 
	{
		return (String)get_Value(COLUMNNAME_Unidad_Operativa);
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

	/** Set UpdateDart.
		@param UpdateDart UpdateDart	  */
	public void setUpdateDart (Timestamp UpdateDart)
	{
		set_Value (COLUMNNAME_UpdateDart, UpdateDart);
	}

	/** Get UpdateDart.
		@return UpdateDart	  */
	public Timestamp getUpdateDart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_UpdateDart);
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

	/** Set Value B.
		@param Value_B Value B	  */
	public void setValue_B (String Value_B)
	{
		set_Value (COLUMNNAME_Value_B, Value_B);
	}

	/** Get Value B.
		@return Value B	  */
	public String getValue_B () 
	{
		return (String)get_Value(COLUMNNAME_Value_B);
	}

	/** Set Value C.
		@param Value_C Value C	  */
	public void setValue_C (String Value_C)
	{
		set_Value (COLUMNNAME_Value_C, Value_C);
	}

	/** Get Value C.
		@return Value C	  */
	public String getValue_C () 
	{
		return (String)get_Value(COLUMNNAME_Value_C);
	}

	/** Set Volume.
		@param Volume 
		Volume of a product
	  */
	public void setVolume (int Volume)
	{
		set_Value (COLUMNNAME_Volume, Integer.valueOf(Volume));
	}

	/** Get Volume.
		@return Volume of a product
	  */
	public int getVolume () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Volume);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (int Weight)
	{
		set_Value (COLUMNNAME_Weight, Integer.valueOf(Weight));
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public int getWeight () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Weight);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM Code.
		@param X12DE355 
		UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355)
	{
		set_Value (COLUMNNAME_X12DE355, X12DE355);
	}

	/** Get UOM Code.
		@return UOM EDI X12 Code
	  */
	public String getX12DE355 () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355);
	}

	/** Set X12DE355B.
		@param X12DE355B X12DE355B	  */
	public void setX12DE355B (String X12DE355B)
	{
		set_Value (COLUMNNAME_X12DE355B, X12DE355B);
	}

	/** Get X12DE355B.
		@return X12DE355B	  */
	public String getX12DE355B () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355B);
	}
}