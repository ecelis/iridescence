/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_DrugFormulary
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DrugFormulary extends PO implements I_EXME_DrugFormulary, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DrugFormulary (Properties ctx, int EXME_DrugFormulary_ID, String trxName)
    {
      super (ctx, EXME_DrugFormulary_ID, trxName);
      /** if (EXME_DrugFormulary_ID == 0)
        {
			setEXME_DrugFormulary_ID (0);
			setEXME_GenProduct_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_DrugFormulary (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DrugFormulary[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set EXME_DrugFormulary_ID.
		@param EXME_DrugFormulary_ID EXME_DrugFormulary_ID	  */
	public void setEXME_DrugFormulary_ID (int EXME_DrugFormulary_ID)
	{
		if (EXME_DrugFormulary_ID < 1)
			 throw new IllegalArgumentException ("EXME_DrugFormulary_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DrugFormulary_ID, Integer.valueOf(EXME_DrugFormulary_ID));
	}

	/** Get EXME_DrugFormulary_ID.
		@return EXME_DrugFormulary_ID	  */
	public int getEXME_DrugFormulary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DrugFormulary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenProduct_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}