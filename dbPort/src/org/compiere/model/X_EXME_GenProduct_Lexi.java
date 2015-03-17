/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_GenProduct_Lexi
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_GenProduct_Lexi extends PO implements I_EXME_GenProduct_Lexi, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GenProduct_Lexi (Properties ctx, int EXME_GenProduct_Lexi_ID, String trxName)
    {
      super (ctx, EXME_GenProduct_Lexi_ID, trxName);
      /** if (EXME_GenProduct_Lexi_ID == 0)
        {
			setCSA_Code (null);
			setDrug_ID (null);
			setEXME_DoseForm_ID (0);
			setexme_genproduct_lexi_id (0);
			setEXME_ProductStrength_ID (0);
			setEXME_Route_ID (0);
			setGeneric_Product_Name (null);
			setGenProduct_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GenProduct_Lexi (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_GenProduct_Lexi[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CSA Code.
		@param CSA_Code CSA Code	  */
	public void setCSA_Code (String CSA_Code)
	{
		if (CSA_Code == null)
			throw new IllegalArgumentException ("CSA_Code is mandatory.");
		set_Value (COLUMNNAME_CSA_Code, CSA_Code);
	}

	/** Get CSA Code.
		@return CSA Code	  */
	public String getCSA_Code () 
	{
		return (String)get_Value(COLUMNNAME_CSA_Code);
	}

	/** Set Drug.
		@param Drug_ID Drug	  */
	public void setDrug_ID (String Drug_ID)
	{
		if (Drug_ID == null)
			throw new IllegalArgumentException ("Drug_ID is mandatory.");
		set_Value (COLUMNNAME_Drug_ID, Drug_ID);
	}

	/** Get Drug.
		@return Drug	  */
	public String getDrug_ID () 
	{
		return (String)get_Value(COLUMNNAME_Drug_ID);
	}

	/** Set DoseForm.
		@param EXME_DoseForm_ID 
		DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID)
	{
		if (EXME_DoseForm_ID < 1)
			 throw new IllegalArgumentException ("EXME_DoseForm_ID is mandatory.");
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

	/** Set Generic Drug.
		@param EXME_GenDrug_ID Generic Drug	  */
	public void setEXME_GenDrug_ID (int EXME_GenDrug_ID)
	{
		if (EXME_GenDrug_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenDrug_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenDrug_ID, Integer.valueOf(EXME_GenDrug_ID));
	}

	/** Get Generic Drug.
		@return Generic Drug	  */
	public int getEXME_GenDrug_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenDrug_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set exme_genproduct_lexi_id.
		@param exme_genproduct_lexi_id exme_genproduct_lexi_id	  */
	public void setexme_genproduct_lexi_id (int exme_genproduct_lexi_id)
	{
		set_ValueNoCheck (COLUMNNAME_exme_genproduct_lexi_id, Integer.valueOf(exme_genproduct_lexi_id));
	}

	/** Get exme_genproduct_lexi_id.
		@return exme_genproduct_lexi_id	  */
	public int getexme_genproduct_lexi_id () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_exme_genproduct_lexi_id);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Strength.
		@param EXME_ProductStrength_ID Product Strength	  */
	public void setEXME_ProductStrength_ID (int EXME_ProductStrength_ID)
	{
		if (EXME_ProductStrength_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductStrength_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProductStrength_ID, Integer.valueOf(EXME_ProductStrength_ID));
	}

	/** Get Product Strength.
		@return Product Strength	  */
	public int getEXME_ProductStrength_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductStrength_ID);
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
			 throw new IllegalArgumentException ("EXME_Route_ID is mandatory.");
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

	/** Set Generic Product Name.
		@param Generic_Product_Name Generic Product Name	  */
	public void setGeneric_Product_Name (String Generic_Product_Name)
	{
		if (Generic_Product_Name == null)
			throw new IllegalArgumentException ("Generic_Product_Name is mandatory.");
		set_Value (COLUMNNAME_Generic_Product_Name, Generic_Product_Name);
	}

	/** Get Generic Product Name.
		@return Generic Product Name	  */
	public String getGeneric_Product_Name () 
	{
		return (String)get_Value(COLUMNNAME_Generic_Product_Name);
	}

	/** Set Generic Product.
		@param GenProduct_ID Generic Product	  */
	public void setGenProduct_ID (int GenProduct_ID)
	{
		if (GenProduct_ID < 1)
			 throw new IllegalArgumentException ("GenProduct_ID is mandatory.");
		set_Value (COLUMNNAME_GenProduct_ID, Integer.valueOf(GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getGenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GLOBAL_ID.
		@param GLOBAL_ID GLOBAL_ID	  */
	public void setGLOBAL_ID (int GLOBAL_ID)
	{
		if (GLOBAL_ID < 1) 
			set_Value (COLUMNNAME_GLOBAL_ID, null);
		else 
			set_Value (COLUMNNAME_GLOBAL_ID, Integer.valueOf(GLOBAL_ID));
	}

	/** Get GLOBAL_ID.
		@return GLOBAL_ID	  */
	public int getGLOBAL_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GLOBAL_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set JCode.
		@param JCode JCode	  */
	public void setJCode (String JCode)
	{
		set_Value (COLUMNNAME_JCode, JCode);
	}

	/** Get JCode.
		@return JCode	  */
	public String getJCode () 
	{
		return (String)get_Value(COLUMNNAME_JCode);
	}

	/** Set JCode Description.
		@param JCode_Description JCode Description	  */
	public void setJCode_Description (String JCode_Description)
	{
		set_Value (COLUMNNAME_JCode_Description, JCode_Description);
	}

	/** Get JCode Description.
		@return JCode Description	  */
	public String getJCode_Description () 
	{
		return (String)get_Value(COLUMNNAME_JCode_Description);
	}

	/** Set LEXI_ONLINE_DOC_ID.
		@param LEXI_ONLINE_DOC_ID LEXI_ONLINE_DOC_ID	  */
	public void setLEXI_ONLINE_DOC_ID (int LEXI_ONLINE_DOC_ID)
	{
		if (LEXI_ONLINE_DOC_ID < 1) 
			set_Value (COLUMNNAME_LEXI_ONLINE_DOC_ID, null);
		else 
			set_Value (COLUMNNAME_LEXI_ONLINE_DOC_ID, Integer.valueOf(LEXI_ONLINE_DOC_ID));
	}

	/** Get LEXI_ONLINE_DOC_ID.
		@return LEXI_ONLINE_DOC_ID	  */
	public int getLEXI_ONLINE_DOC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LEXI_ONLINE_DOC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manufact Generic Product Name.
		@param Manufact_Generic_Product_Name Manufact Generic Product Name	  */
	public void setManufact_Generic_Product_Name (String Manufact_Generic_Product_Name)
	{
		set_Value (COLUMNNAME_Manufact_Generic_Product_Name, Manufact_Generic_Product_Name);
	}

	/** Get Manufact Generic Product Name.
		@return Manufact Generic Product Name	  */
	public String getManufact_Generic_Product_Name () 
	{
		return (String)get_Value(COLUMNNAME_Manufact_Generic_Product_Name);
	}

	/** Set Obsolete Date.
		@param Obsolete_Date Obsolete Date	  */
	public void setObsolete_Date (Timestamp Obsolete_Date)
	{
		set_Value (COLUMNNAME_Obsolete_Date, Obsolete_Date);
	}

	/** Get Obsolete Date.
		@return Obsolete Date	  */
	public Timestamp getObsolete_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Obsolete_Date);
	}

	/** Set RXCUI.
		@param RXCUI RXCUI	  */
	public void setRXCUI (String RXCUI)
	{
		set_Value (COLUMNNAME_RXCUI, RXCUI);
	}

	/** Get RXCUI.
		@return RXCUI	  */
	public String getRXCUI () 
	{
		return (String)get_Value(COLUMNNAME_RXCUI);
	}

	/** Set RX OTC Status.
		@param RX_OTC_Status RX OTC Status	  */
	public void setRX_OTC_Status (String RX_OTC_Status)
	{
		set_Value (COLUMNNAME_RX_OTC_Status, RX_OTC_Status);
	}

	/** Get RX OTC Status.
		@return RX OTC Status	  */
	public String getRX_OTC_Status () 
	{
		return (String)get_Value(COLUMNNAME_RX_OTC_Status);
	}

	/** Set Trade Name.
		@param Trade_Name Trade Name	  */
	public void setTrade_Name (String Trade_Name)
	{
		set_Value (COLUMNNAME_Trade_Name, Trade_Name);
	}

	/** Get Trade Name.
		@return Trade Name	  */
	public String getTrade_Name () 
	{
		return (String)get_Value(COLUMNNAME_Trade_Name);
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