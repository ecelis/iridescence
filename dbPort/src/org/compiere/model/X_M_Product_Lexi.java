/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_Product_Lexi
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_Product_Lexi extends PO implements I_M_Product_Lexi, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Product_Lexi (Properties ctx, int M_Product_Lexi_ID, String trxName)
    {
      super (ctx, M_Product_Lexi_ID, trxName);
      /** if (M_Product_Lexi_ID == 0)
        {
			setCambiaPrecio (false);
			setC_UOM_ID (0);
			setC_UOMVolume_ID (0);
			setEsVacuna (false);
			setIsBOM (false);
			setIsCoveredByInsurance (false);
			setIsDropShip (false);
			setIsExcludeAutoDelivery (false);
			setIsInvoicePrintDetails (false);
			setIsNumSerie (false);
			setIsPickListPrintDetails (false);
			setIsPurchased (false);
			setIsSelfService (false);
			setIsSold (false);
			setIsStocked (false);
			setIsSummary (false);
			setIsVerified (false);
			setIsWebStoreFeatured (false);
			setM_Product_Category_ID (0);
			setm_product_lexi_id (0);
			setName (null);
			setProductClass (null);
			setProductType (false);
			setRequiereContraste (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_M_Product_Lexi (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Product_Lexi[")
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

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Revenue Recognition.
		@param C_RevenueRecognition_ID 
		Method for recording revenue
	  */
	public void setC_RevenueRecognition_ID (int C_RevenueRecognition_ID)
	{
		if (C_RevenueRecognition_ID < 1) 
			set_Value (COLUMNNAME_C_RevenueRecognition_ID, null);
		else 
			set_Value (COLUMNNAME_C_RevenueRecognition_ID, Integer.valueOf(C_RevenueRecognition_ID));
	}

	/** Get Revenue Recognition.
		@return Method for recording revenue
	  */
	public int getC_RevenueRecognition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_RevenueRecognition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Subscription Type.
		@param C_SubscriptionType_ID 
		Type of subscription
	  */
	public void setC_SubscriptionType_ID (int C_SubscriptionType_ID)
	{
		if (C_SubscriptionType_ID < 1) 
			set_Value (COLUMNNAME_C_SubscriptionType_ID, null);
		else 
			set_Value (COLUMNNAME_C_SubscriptionType_ID, Integer.valueOf(C_SubscriptionType_ID));
	}

	/** Get Subscription Type.
		@return Type of subscription
	  */
	public int getC_SubscriptionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SubscriptionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1)
			 throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
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
			 throw new IllegalArgumentException ("C_UOMVolume_ID is mandatory.");
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

	/** Set Download URL.
		@param DownloadURL 
		URL of the Download files
	  */
	public void setDownloadURL (String DownloadURL)
	{
		set_Value (COLUMNNAME_DownloadURL, DownloadURL);
	}

	/** Get Download URL.
		@return URL of the Download files
	  */
	public String getDownloadURL () 
	{
		return (String)get_Value(COLUMNNAME_DownloadURL);
	}

	/** Set Is Vaccine.
		@param EsVacuna Is Vaccine	  */
	public void setEsVacuna (boolean EsVacuna)
	{
		set_Value (COLUMNNAME_EsVacuna, Boolean.valueOf(EsVacuna));
	}

	/** Get Is Vaccine.
		@return Is Vaccine	  */
	public boolean isEsVacuna () 
	{
		Object oo = get_Value(COLUMNNAME_EsVacuna);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set DoseForm.
		@param EXME_DoseForm_ID 
		DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID)
	{
		if (EXME_DoseForm_ID < 1) 
			set_Value (COLUMNNAME_EXME_DoseForm_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DoseForm_ID, Integer.valueOf(EXME_DoseForm_ID));
	}

	/** Get DoseForm.
		@return DoseForm
	  */
	public int getEXME_DoseForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DoseForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set EXME_GenProduct_Lexi_ID.
		@param EXME_GenProduct_Lexi_ID EXME_GenProduct_Lexi_ID	  */
	public void setEXME_GenProduct_Lexi_ID (int EXME_GenProduct_Lexi_ID)
	{
		if (EXME_GenProduct_Lexi_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenProduct_Lexi_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenProduct_Lexi_ID, Integer.valueOf(EXME_GenProduct_Lexi_ID));
	}

	/** Get EXME_GenProduct_Lexi_ID.
		@return EXME_GenProduct_Lexi_ID	  */
	public int getEXME_GenProduct_Lexi_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_Lexi_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Labeler.
		@param EXME_Labeler_ID 
		Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID)
	{
		if (EXME_Labeler_ID < 1) 
			set_Value (COLUMNNAME_EXME_Labeler_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Labeler_ID, Integer.valueOf(EXME_Labeler_ID));
	}

	/** Get Labeler.
		@return Labeler
	  */
	public int getEXME_Labeler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Labeler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LOINC Code.
		@param EXME_Loinc_ID LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID)
	{
		if (EXME_Loinc_ID < 1) 
			set_Value (COLUMNNAME_EXME_Loinc_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
	}

	/** Get LOINC Code.
		@return LOINC Code	  */
	public int getEXME_Loinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Loinc_ID);
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

	/** Set Route.
		@param EXME_Route_ID 
		Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID)
	{
		if (EXME_Route_ID < 1) 
			set_Value (COLUMNNAME_EXME_Route_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Route_ID, Integer.valueOf(EXME_Route_ID));
	}

	/** Get Route.
		@return Route
	  */
	public int getEXME_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Route_ID);
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

	/** Set Generic.
		@param Generico 
		Generic
	  */
	public void setGenerico (boolean Generico)
	{
		set_Value (COLUMNNAME_Generico, Boolean.valueOf(Generico));
	}

	/** Get Generic.
		@return Generic
	  */
	public boolean isGenerico () 
	{
		Object oo = get_Value(COLUMNNAME_Generico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Group1.
		@param Group1 Group1	  */
	public void setGroup1 (String Group1)
	{
		set_Value (COLUMNNAME_Group1, Group1);
	}

	/** Get Group1.
		@return Group1	  */
	public String getGroup1 () 
	{
		return (String)get_Value(COLUMNNAME_Group1);
	}

	/** Set Group2.
		@param Group2 Group2	  */
	public void setGroup2 (String Group2)
	{
		set_Value (COLUMNNAME_Group2, Group2);
	}

	/** Get Group2.
		@return Group2	  */
	public String getGroup2 () 
	{
		return (String)get_Value(COLUMNNAME_Group2);
	}

	/** Set Guarantee Days.
		@param GuaranteeDays 
		Number of days the product is guaranteed or available
	  */
	public void setGuaranteeDays (int GuaranteeDays)
	{
		set_Value (COLUMNNAME_GuaranteeDays, Integer.valueOf(GuaranteeDays));
	}

	/** Get Guarantee Days.
		@return Number of days the product is guaranteed or available
	  */
	public int getGuaranteeDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GuaranteeDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Guarantee Days.
		@param GuaranteeDaysMin 
		Minumum number of guarantee days
	  */
	public void setGuaranteeDaysMin (int GuaranteeDaysMin)
	{
		set_Value (COLUMNNAME_GuaranteeDaysMin, Integer.valueOf(GuaranteeDaysMin));
	}

	/** Get Min Guarantee Days.
		@return Minumum number of guarantee days
	  */
	public int getGuaranteeDaysMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GuaranteeDaysMin);
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

	/** Set Bill of Materials.
		@param IsBOM 
		Bill of Materials
	  */
	public void setIsBOM (boolean IsBOM)
	{
		set_Value (COLUMNNAME_IsBOM, Boolean.valueOf(IsBOM));
	}

	/** Get Bill of Materials.
		@return Bill of Materials
	  */
	public boolean isBOM () 
	{
		Object oo = get_Value(COLUMNNAME_IsBOM);
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

	/** Set Drop Shipment.
		@param IsDropShip 
		Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public void setIsDropShip (boolean IsDropShip)
	{
		set_Value (COLUMNNAME_IsDropShip, Boolean.valueOf(IsDropShip));
	}

	/** Get Drop Shipment.
		@return Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public boolean isDropShip () 
	{
		Object oo = get_Value(COLUMNNAME_IsDropShip);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Exclude Auto Delivery.
		@param IsExcludeAutoDelivery 
		Do you need medical equipment?
	  */
	public void setIsExcludeAutoDelivery (boolean IsExcludeAutoDelivery)
	{
		set_Value (COLUMNNAME_IsExcludeAutoDelivery, Boolean.valueOf(IsExcludeAutoDelivery));
	}

	/** Get Exclude Auto Delivery.
		@return Do you need medical equipment?
	  */
	public boolean isExcludeAutoDelivery () 
	{
		Object oo = get_Value(COLUMNNAME_IsExcludeAutoDelivery);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Preferably generic name.
		@param IsGI Preferably generic name	  */
	public void setIsGI (boolean IsGI)
	{
		set_Value (COLUMNNAME_IsGI, Boolean.valueOf(IsGI));
	}

	/** Get Preferably generic name.
		@return Preferably generic name	  */
	public boolean isGI () 
	{
		Object oo = get_Value(COLUMNNAME_IsGI);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print detail records on invoice.
		@param IsInvoicePrintDetails 
		Print detail BOM elements on the invoice
	  */
	public void setIsInvoicePrintDetails (boolean IsInvoicePrintDetails)
	{
		set_Value (COLUMNNAME_IsInvoicePrintDetails, Boolean.valueOf(IsInvoicePrintDetails));
	}

	/** Get Print detail records on invoice.
		@return Print detail BOM elements on the invoice
	  */
	public boolean isInvoicePrintDetails () 
	{
		Object oo = get_Value(COLUMNNAME_IsInvoicePrintDetails);
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

	/** Set Serial number.
		@param IsNumSerie Serial number	  */
	public void setIsNumSerie (boolean IsNumSerie)
	{
		set_Value (COLUMNNAME_IsNumSerie, Boolean.valueOf(IsNumSerie));
	}

	/** Get Serial number.
		@return Serial number	  */
	public boolean isNumSerie () 
	{
		Object oo = get_Value(COLUMNNAME_IsNumSerie);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPhysicianCharge.
		@param IsPhysicianCharge IsPhysicianCharge	  */
	public void setIsPhysicianCharge (boolean IsPhysicianCharge)
	{
		set_Value (COLUMNNAME_IsPhysicianCharge, Boolean.valueOf(IsPhysicianCharge));
	}

	/** Get IsPhysicianCharge.
		@return IsPhysicianCharge	  */
	public boolean isPhysicianCharge () 
	{
		Object oo = get_Value(COLUMNNAME_IsPhysicianCharge);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print detail records on pick list.
		@param IsPickListPrintDetails 
		Print detail BOM elements on the pick list
	  */
	public void setIsPickListPrintDetails (boolean IsPickListPrintDetails)
	{
		set_Value (COLUMNNAME_IsPickListPrintDetails, Boolean.valueOf(IsPickListPrintDetails));
	}

	/** Get Print detail records on pick list.
		@return Print detail BOM elements on the pick list
	  */
	public boolean isPickListPrintDetails () 
	{
		Object oo = get_Value(COLUMNNAME_IsPickListPrintDetails);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Purchased.
		@param IsPurchased 
		Organization purchases this product
	  */
	public void setIsPurchased (boolean IsPurchased)
	{
		set_Value (COLUMNNAME_IsPurchased, Boolean.valueOf(IsPurchased));
	}

	/** Get Purchased.
		@return Organization purchases this product
	  */
	public boolean isPurchased () 
	{
		Object oo = get_Value(COLUMNNAME_IsPurchased);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Blood.
		@param IsSangre Is Blood	  */
	public void setIsSangre (boolean IsSangre)
	{
		set_Value (COLUMNNAME_IsSangre, Boolean.valueOf(IsSangre));
	}

	/** Get Is Blood.
		@return Is Blood	  */
	public boolean isSangre () 
	{
		Object oo = get_Value(COLUMNNAME_IsSangre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Self-Service.
		@param IsSelfService 
		This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService)
	{
		set_Value (COLUMNNAME_IsSelfService, Boolean.valueOf(IsSelfService));
	}

	/** Get Self-Service.
		@return This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelfService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sold.
		@param IsSold 
		Organization sells this product
	  */
	public void setIsSold (boolean IsSold)
	{
		set_Value (COLUMNNAME_IsSold, Boolean.valueOf(IsSold));
	}

	/** Get Sold.
		@return Organization sells this product
	  */
	public boolean isSold () 
	{
		Object oo = get_Value(COLUMNNAME_IsSold);
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

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Vaccine.
		@param IsVacuna Is Vaccine	  */
	public void setIsVacuna (boolean IsVacuna)
	{
		set_Value (COLUMNNAME_IsVacuna, Boolean.valueOf(IsVacuna));
	}

	/** Get Is Vaccine.
		@return Is Vaccine	  */
	public boolean isVacuna () 
	{
		Object oo = get_Value(COLUMNNAME_IsVacuna);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Verified.
		@param IsVerified 
		The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified)
	{
		set_Value (COLUMNNAME_IsVerified, Boolean.valueOf(IsVerified));
	}

	/** Get Verified.
		@return The BOM configuration has been verified
	  */
	public boolean isVerified () 
	{
		Object oo = get_Value(COLUMNNAME_IsVerified);
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

	/** Set Item Class.
		@param ItemClass 
		Item Class
	  */
	public void setItemClass (boolean ItemClass)
	{
		set_Value (COLUMNNAME_ItemClass, Boolean.valueOf(ItemClass));
	}

	/** Get Item Class.
		@return Item Class
	  */
	public boolean isItemClass () 
	{
		Object oo = get_Value(COLUMNNAME_ItemClass);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Labeler.
		@param LabelerID Labeler	  */
	public void setLabelerID (int LabelerID)
	{
		set_Value (COLUMNNAME_LabelerID, Integer.valueOf(LabelerID));
	}

	/** Get Labeler.
		@return Labeler	  */
	public int getLabelerID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LabelerID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Low Level.
		@param LowLevel Low Level	  */
	public void setLowLevel (int LowLevel)
	{
		set_Value (COLUMNNAME_LowLevel, Integer.valueOf(LowLevel));
	}

	/** Get Low Level.
		@return Low Level	  */
	public int getLowLevel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LowLevel);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Freight Category.
		@param M_FreightCategory_ID 
		Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID)
	{
		if (M_FreightCategory_ID < 1) 
			set_Value (COLUMNNAME_M_FreightCategory_ID, null);
		else 
			set_Value (COLUMNNAME_M_FreightCategory_ID, Integer.valueOf(M_FreightCategory_ID));
	}

	/** Get Freight Category.
		@return Category of the Freight
	  */
	public int getM_FreightCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_FreightCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
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
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
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

	/** Set m_product_lexi_id.
		@param m_product_lexi_id m_product_lexi_id	  */
	public void setm_product_lexi_id (int m_product_lexi_id)
	{
		set_ValueNoCheck (COLUMNNAME_m_product_lexi_id, Integer.valueOf(m_product_lexi_id));
	}

	/** Get m_product_lexi_id.
		@return m_product_lexi_id	  */
	public int getm_product_lexi_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_m_product_lexi_id);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set Procedure Type.
		@param ProcedureType 
		Procedure Type
	  */
	public void setProcedureType (String ProcedureType)
	{
		set_Value (COLUMNNAME_ProcedureType, ProcedureType);
	}

	/** Get Procedure Type.
		@return Procedure Type
	  */
	public String getProcedureType () 
	{
		return (String)get_Value(COLUMNNAME_ProcedureType);
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

	/** Set Product Class.
		@param ProductClass Product Class	  */
	public void setProductClass (String ProductClass)
	{
		if (ProductClass == null)
			throw new IllegalArgumentException ("ProductClass is mandatory.");
		set_Value (COLUMNNAME_ProductClass, ProductClass);
	}

	/** Get Product Class.
		@return Product Class	  */
	public String getProductClass () 
	{
		return (String)get_Value(COLUMNNAME_ProductClass);
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

	/** Set Requires Contrast.
		@param RequiereContraste 
		Requires contrast media
	  */
	public void setRequiereContraste (boolean RequiereContraste)
	{
		set_Value (COLUMNNAME_RequiereContraste, Boolean.valueOf(RequiereContraste));
	}

	/** Get Requires Contrast.
		@return Requires contrast media
	  */
	public boolean isRequiereContraste () 
	{
		Object oo = get_Value(COLUMNNAME_RequiereContraste);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expense Type.
		@param S_ExpenseType_ID 
		Expense report type
	  */
	public void setS_ExpenseType_ID (int S_ExpenseType_ID)
	{
		if (S_ExpenseType_ID < 1) 
			set_Value (COLUMNNAME_S_ExpenseType_ID, null);
		else 
			set_Value (COLUMNNAME_S_ExpenseType_ID, Integer.valueOf(S_ExpenseType_ID));
	}

	/** Get Expense Type.
		@return Expense report type
	  */
	public int getS_ExpenseType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ExpenseType_ID);
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

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_Value (COLUMNNAME_S_Resource_ID, null);
		else 
			set_Value (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Trade name/key.
		@param Trade_Name_ID Trade name/key	  */
	public void setTrade_Name_ID (int Trade_Name_ID)
	{
		if (Trade_Name_ID < 1) 
			set_Value (COLUMNNAME_Trade_Name_ID, null);
		else 
			set_Value (COLUMNNAME_Trade_Name_ID, Integer.valueOf(Trade_Name_ID));
	}

	/** Get Trade name/key.
		@return Trade name/key	  */
	public int getTrade_Name_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Trade_Name_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Units Per Pack.
		@param UnitsPerPack 
		The Units Per Pack indicates the no of units of a product packed together.
	  */
	public void setUnitsPerPack (int UnitsPerPack)
	{
		set_Value (COLUMNNAME_UnitsPerPack, Integer.valueOf(UnitsPerPack));
	}

	/** Get Units Per Pack.
		@return The Units Per Pack indicates the no of units of a product packed together.
	  */
	public int getUnitsPerPack () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnitsPerPack);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Version No.
		@param VersionNo 
		Version Number
	  */
	public void setVersionNo (String VersionNo)
	{
		set_Value (COLUMNNAME_VersionNo, VersionNo);
	}

	/** Get Version No.
		@return Version Number
	  */
	public String getVersionNo () 
	{
		return (String)get_Value(COLUMNNAME_VersionNo);
	}

	/** Set Volume.
		@param Volume 
		Volume of a product
	  */
	public void setVolume (BigDecimal Volume)
	{
		set_Value (COLUMNNAME_Volume, Volume);
	}

	/** Get Volume.
		@return Volume of a product
	  */
	public BigDecimal getVolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Volume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_Value (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}