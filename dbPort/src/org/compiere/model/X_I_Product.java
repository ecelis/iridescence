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

/** Generated Model for I_Product
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_Product extends PO implements I_I_Product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_Product (Properties ctx, int I_Product_ID, String trxName)
    {
      super (ctx, I_Product_ID, trxName);
      /** if (I_Product_ID == 0)
        {
			setI_IsImported (false);
			setI_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_Product[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Company Key.
		@param BPartner_Value 
		The Key of the Company
	  */
	public void setBPartner_Value (String BPartner_Value)
	{
		set_Value (COLUMNNAME_BPartner_Value, BPartner_Value);
	}

	/** Get Company Key.
		@return The Key of the Company
	  */
	public String getBPartner_Value () 
	{
		return (String)get_Value(COLUMNNAME_BPartner_Value);
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

	/** Set Classification.
		@param Classification 
		Classification for grouping
	  */
	public void setClassification (String Classification)
	{
		set_Value (COLUMNNAME_Classification, Classification);
	}

	/** Get Classification.
		@return Classification for grouping
	  */
	public String getClassification () 
	{
		return (String)get_Value(COLUMNNAME_Classification);
	}

	/** Set Cost per Order.
		@param CostPerOrder 
		Fixed Cost Per Order
	  */
	public void setCostPerOrder (BigDecimal CostPerOrder)
	{
		set_Value (COLUMNNAME_CostPerOrder, CostPerOrder);
	}

	/** Get Cost per Order.
		@return Fixed Cost Per Order
	  */
	public BigDecimal getCostPerOrder () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostPerOrder);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Vendor UOM.
		@param C_UOM_Vendor_ID 
		Vendor Unit of Measure
	  */
	public void setC_UOM_Vendor_ID (int C_UOM_Vendor_ID)
	{
		if (C_UOM_Vendor_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Vendor_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Vendor_ID, Integer.valueOf(C_UOM_Vendor_ID));
	}

	/** Get Vendor UOM.
		@return Vendor Unit of Measure
	  */
	public int getC_UOM_Vendor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Vendor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Volume.
		@param C_UOMVolume_ID 
		Unit of measure of volume
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID)
	{
		if (C_UOMVolume_ID < 1) 
			set_Value (COLUMNNAME_C_UOMVolume_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMVolume_ID, Integer.valueOf(C_UOMVolume_ID));
	}

	/** Get UOM of Volume.
		@return Unit of measure of volume
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

	/** Set UOM of Weight.
		@param C_UOMWeight_ID 
		UOM of Weight
	  */
	public void setC_UOMWeight_ID (int C_UOMWeight_ID)
	{
		if (C_UOMWeight_ID < 1) 
			set_Value (COLUMNNAME_C_UOMWeight_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOMWeight_ID, Integer.valueOf(C_UOMWeight_ID));
	}

	/** Get UOM of Weight.
		@return UOM of Weight
	  */
	public int getC_UOMWeight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOMWeight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UOM of Weight Value.
		@param C_UOMWeight_Value 
		UOM of Weight Search Key
	  */
	public void setC_UOMWeight_Value (String C_UOMWeight_Value)
	{
		set_Value (COLUMNNAME_C_UOMWeight_Value, C_UOMWeight_Value);
	}

	/** Get UOM of Weight Value.
		@return UOM of Weight Search Key
	  */
	public String getC_UOMWeight_Value () 
	{
		return (String)get_Value(COLUMNNAME_C_UOMWeight_Value);
	}

	/** Set Promised Delivery Time.
		@param DeliveryTime_Promised 
		Promised days between order and delivery
	  */
	public void setDeliveryTime_Promised (int DeliveryTime_Promised)
	{
		set_Value (COLUMNNAME_DeliveryTime_Promised, Integer.valueOf(DeliveryTime_Promised));
	}

	/** Get Promised Delivery Time.
		@return Promised days between order and delivery
	  */
	public int getDeliveryTime_Promised () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DeliveryTime_Promised);
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

	/** Set Description URL.
		@param DescriptionURL 
		URL for the description
	  */
	public void setDescriptionURL (String DescriptionURL)
	{
		set_Value (COLUMNNAME_DescriptionURL, DescriptionURL);
	}

	/** Get Description URL.
		@return URL for the description
	  */
	public String getDescriptionURL () 
	{
		return (String)get_Value(COLUMNNAME_DescriptionURL);
	}

	/** Set DfID.
		@param DfID DfID	  */
	public void setDfID (int DfID)
	{
		set_Value (COLUMNNAME_DfID, Integer.valueOf(DfID));
	}

	/** Get DfID.
		@return DfID	  */
	public int getDfID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DfID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discontinued.
		@param Discontinued 
		This product is no longer available
	  */
	public void setDiscontinued (boolean Discontinued)
	{
		set_Value (COLUMNNAME_Discontinued, Boolean.valueOf(Discontinued));
	}

	/** Get Discontinued.
		@return This product is no longer available
	  */
	public boolean isDiscontinued () 
	{
		Object oo = get_Value(COLUMNNAME_Discontinued);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discontinued by.
		@param DiscontinuedBy 
		Discontinued By
	  */
	public void setDiscontinuedBy (Timestamp DiscontinuedBy)
	{
		set_Value (COLUMNNAME_DiscontinuedBy, DiscontinuedBy);
	}

	/** Get Discontinued by.
		@return Discontinued By
	  */
	public Timestamp getDiscontinuedBy () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DiscontinuedBy);
	}

	/** Set Document Note.
		@param DocumentNote 
		Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote)
	{
		set_Value (COLUMNNAME_DocumentNote, DocumentNote);
	}

	/** Get Document Note.
		@return Additional information for a Document
	  */
	public String getDocumentNote () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNote);
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

	/** Set GCNSEQNO.
		@param GCNSEQNO GCNSEQNO	  */
	public void setGCNSEQNO (int GCNSEQNO)
	{
		set_Value (COLUMNNAME_GCNSEQNO, Integer.valueOf(GCNSEQNO));
	}

	/** Get GCNSEQNO.
		@return GCNSEQNO	  */
	public int getGCNSEQNO () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GCNSEQNO);
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

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Import Product.
		@param I_Product_ID 
		Import Item or Service
	  */
	public void setI_Product_ID (int I_Product_ID)
	{
		if (I_Product_ID < 1)
			 throw new IllegalArgumentException ("I_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_Product_ID, Integer.valueOf(I_Product_ID));
	}

	/** Get Import Product.
		@return Import Item or Service
	  */
	public int getI_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ISO Currency Code.
		@param ISO_Code 
		Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code)
	{
		set_Value (COLUMNNAME_ISO_Code, ISO_Code);
	}

	/** Get ISO Currency Code.
		@return Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code () 
	{
		return (String)get_Value(COLUMNNAME_ISO_Code);
	}

	/** Set Labeler.
		@param LabelerID Labeler	  */
	public void setLabelerID (String LabelerID)
	{
		set_Value (COLUMNNAME_LabelerID, LabelerID);
	}

	/** Get Labeler.
		@return Labeler	  */
	public String getLabelerID () 
	{
		return (String)get_Value(COLUMNNAME_LabelerID);
	}

	/** Set Manufacturer.
		@param Manufacturer 
		Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer)
	{
		set_Value (COLUMNNAME_Manufacturer, Manufacturer);
	}

	/** Get Manufacturer.
		@return Manufacturer of the Product
	  */
	public String getManufacturer () 
	{
		return (String)get_Value(COLUMNNAME_Manufacturer);
	}

	/** Set MEDID.
		@param MEDID MEDID	  */
	public void setMEDID (int MEDID)
	{
		set_Value (COLUMNNAME_MEDID, Integer.valueOf(MEDID));
	}

	/** Get MEDID.
		@return MEDID	  */
	public int getMEDID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MEDID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MNID.
		@param MNID MNID	  */
	public void setMNID (int MNID)
	{
		set_Value (COLUMNNAME_MNID, Integer.valueOf(MNID));
	}

	/** Get MNID.
		@return MNID	  */
	public int getMNID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MNID);
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

	/** Set Minimum Order Qty.
		@param Order_Min 
		Minimum order quantity in UOM
	  */
	public void setOrder_Min (int Order_Min)
	{
		set_Value (COLUMNNAME_Order_Min, Integer.valueOf(Order_Min));
	}

	/** Get Minimum Order Qty.
		@return Minimum order quantity in UOM
	  */
	public int getOrder_Min () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Order_Min);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Pack Qty.
		@param Order_Pack 
		Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (int Order_Pack)
	{
		set_Value (COLUMNNAME_Order_Pack, Integer.valueOf(Order_Pack));
	}

	/** Get Order Pack Qty.
		@return Package order size in UOM (e.g. order set of 5 units)
	  */
	public int getOrder_Pack () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Order_Pack);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PMID.
		@param PMID PMID	  */
	public void setPMID (String PMID)
	{
		set_Value (COLUMNNAME_PMID, PMID);
	}

	/** Get PMID.
		@return PMID	  */
	public String getPMID () 
	{
		return (String)get_Value(COLUMNNAME_PMID);
	}

	/** Set Sell Price.
		@param PrecioVenta 
		Product Sell Price
	  */
	public void setPrecioVenta (BigDecimal PrecioVenta)
	{
		set_Value (COLUMNNAME_PrecioVenta, PrecioVenta);
	}

	/** Get Sell Price.
		@return Product Sell Price
	  */
	public BigDecimal getPrecioVenta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PrecioVenta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Price effective.
		@param PriceEffective 
		Effective Date of Price
	  */
	public void setPriceEffective (Timestamp PriceEffective)
	{
		set_Value (COLUMNNAME_PriceEffective, PriceEffective);
	}

	/** Get Price effective.
		@return Effective Date of Price
	  */
	public Timestamp getPriceEffective () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PriceEffective);
	}

	/** Set Last PO Price.
		@param PriceLastPO 
		Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO)
	{
		set_Value (COLUMNNAME_PriceLastPO, PriceLastPO);
	}

	/** Get Last PO Price.
		@return Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLastPO);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set PO Price.
		@param PricePO 
		Price based on a purchase order
	  */
	public void setPricePO (BigDecimal PricePO)
	{
		set_Value (COLUMNNAME_PricePO, PricePO);
	}

	/** Get PO Price.
		@return Price based on a purchase order
	  */
	public BigDecimal getPricePO () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PricePO);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard Price.
		@param PriceStd 
		Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd)
	{
		set_Value (COLUMNNAME_PriceStd, PriceStd);
	}

	/** Get Standard Price.
		@return Standard Price
	  */
	public BigDecimal getPriceStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Product Category.
		@param ProductCategory Product Category	  */
	public void setProductCategory (String ProductCategory)
	{
		set_Value (COLUMNNAME_ProductCategory, ProductCategory);
	}

	/** Get Product Category.
		@return Product Category	  */
	public String getProductCategory () 
	{
		return (String)get_Value(COLUMNNAME_ProductCategory);
	}

	/** Set Product Category Key.
		@param ProductCategory_Value Product Category Key	  */
	public void setProductCategory_Value (String ProductCategory_Value)
	{
		set_Value (COLUMNNAME_ProductCategory_Value, ProductCategory_Value);
	}

	/** Get Product Category Key.
		@return Product Category Key	  */
	public String getProductCategory_Value () 
	{
		return (String)get_Value(COLUMNNAME_ProductCategory_Value);
	}

	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{
		set_Value (COLUMNNAME_ProductClass, ProductClass);
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

	/** Set Royalty Amount.
		@param RoyaltyAmt 
		(Included) Amount for copyright, etc.
	  */
	public void setRoyaltyAmt (BigDecimal RoyaltyAmt)
	{
		set_Value (COLUMNNAME_RoyaltyAmt, RoyaltyAmt);
	}

	/** Get Royalty Amount.
		@return (Included) Amount for copyright, etc.
	  */
	public BigDecimal getRoyaltyAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RoyaltyAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RtID.
		@param RtID RtID	  */
	public void setRtID (int RtID)
	{
		set_Value (COLUMNNAME_RtID, Integer.valueOf(RtID));
	}

	/** Get RtID.
		@return RtID	  */
	public int getRtID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RtID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shelf Depth.
		@param ShelfDepth 
		Shelf depth required
	  */
	public void setShelfDepth (int ShelfDepth)
	{
		set_Value (COLUMNNAME_ShelfDepth, Integer.valueOf(ShelfDepth));
	}

	/** Get Shelf Depth.
		@return Shelf depth required
	  */
	public int getShelfDepth () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfDepth);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shelf Height.
		@param ShelfHeight 
		Shelf height required
	  */
	public void setShelfHeight (int ShelfHeight)
	{
		set_Value (COLUMNNAME_ShelfHeight, Integer.valueOf(ShelfHeight));
	}

	/** Get Shelf Height.
		@return Shelf height required
	  */
	public int getShelfHeight () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfHeight);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shelf Width.
		@param ShelfWidth 
		Shelf width required
	  */
	public void setShelfWidth (int ShelfWidth)
	{
		set_Value (COLUMNNAME_ShelfWidth, Integer.valueOf(ShelfWidth));
	}

	/** Get Shelf Width.
		@return Shelf width required
	  */
	public int getShelfWidth () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfWidth);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SKU.
		@param SKU 
		Stock Keeping Unit
	  */
	public void setSKU (String SKU)
	{
		set_Value (COLUMNNAME_SKU, SKU);
	}

	/** Get SKU.
		@return Stock Keeping Unit
	  */
	public String getSKU () 
	{
		return (String)get_Value(COLUMNNAME_SKU);
	}

	/** Set Strength.
		@param Strength 
		Strength
	  */
	public void setStrength (String Strength)
	{
		set_Value (COLUMNNAME_Strength, Strength);
	}

	/** Get Strength.
		@return Strength
	  */
	public String getStrength () 
	{
		return (String)get_Value(COLUMNNAME_Strength);
	}

	/** Set Strengthunits.
		@param Strengthunits 
		Strengthunits 
	  */
	public void setStrengthunits (String Strengthunits)
	{
		set_Value (COLUMNNAME_Strengthunits, Strengthunits);
	}

	/** Get Strengthunits.
		@return Strengthunits 
	  */
	public String getStrengthunits () 
	{
		return (String)get_Value(COLUMNNAME_Strengthunits);
	}

	/** Set Units Per Pallet.
		@param UnitsPerPallet 
		Units Per Pallet
	  */
	public void setUnitsPerPallet (int UnitsPerPallet)
	{
		set_Value (COLUMNNAME_UnitsPerPallet, Integer.valueOf(UnitsPerPallet));
	}

	/** Get Units Per Pallet.
		@return Units Per Pallet
	  */
	public int getUnitsPerPallet () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnitsPerPallet);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set Partner Category.
		@param VendorCategory 
		Product Category of the Business Partner
	  */
	public void setVendorCategory (String VendorCategory)
	{
		set_Value (COLUMNNAME_VendorCategory, VendorCategory);
	}

	/** Get Partner Category.
		@return Product Category of the Business Partner
	  */
	public String getVendorCategory () 
	{
		return (String)get_Value(COLUMNNAME_VendorCategory);
	}

	/** Set Partner Product Key.
		@param VendorProductNo 
		Product Key of the Business Partner
	  */
	public void setVendorProductNo (String VendorProductNo)
	{
		set_Value (COLUMNNAME_VendorProductNo, VendorProductNo);
	}

	/** Get Partner Product Key.
		@return Product Key of the Business Partner
	  */
	public String getVendorProductNo () 
	{
		return (String)get_Value(COLUMNNAME_VendorProductNo);
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

	/** Set Supplier EDI code.
		@param X12DE355Vendor 
		Supplier EDI of UdM code
	  */
	public void setX12DE355Vendor (String X12DE355Vendor)
	{
		set_Value (COLUMNNAME_X12DE355Vendor, X12DE355Vendor);
	}

	/** Get Supplier EDI code.
		@return Supplier EDI of UdM code
	  */
	public String getX12DE355Vendor () 
	{
		return (String)get_Value(COLUMNNAME_X12DE355Vendor);
	}
}