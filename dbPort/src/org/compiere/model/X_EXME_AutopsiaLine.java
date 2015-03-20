/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_AutopsiaLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AutopsiaLine extends PO implements I_EXME_AutopsiaLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AutopsiaLine (Properties ctx, int EXME_AutopsiaLine_ID, String trxName)
    {
      super (ctx, EXME_AutopsiaLine_ID, trxName);
      /** if (EXME_AutopsiaLine_ID == 0)
        {
			setEXME_Autopsia_ID (0);
			setEXME_AutopsiaLine_ID (0);
			setEXME_Diagnostico_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_AutopsiaLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AutopsiaLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Autopsia getEXME_Autopsia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Autopsia.Table_Name);
        I_EXME_Autopsia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Autopsia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Autopsia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Autopsy.
		@param EXME_Autopsia_ID 
		Autopsy
	  */
	public void setEXME_Autopsia_ID (int EXME_Autopsia_ID)
	{
		if (EXME_Autopsia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Autopsia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Autopsia_ID, Integer.valueOf(EXME_Autopsia_ID));
	}

	/** Get Autopsy.
		@return Autopsy
	  */
	public int getEXME_Autopsia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Autopsia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Autopsy Line.
		@param EXME_AutopsiaLine_ID 
		Autopsy Line
	  */
	public void setEXME_AutopsiaLine_ID (int EXME_AutopsiaLine_ID)
	{
		if (EXME_AutopsiaLine_ID < 1)
			 throw new IllegalArgumentException ("EXME_AutopsiaLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AutopsiaLine_ID, Integer.valueOf(EXME_AutopsiaLine_ID));
	}

	/** Get Autopsy Line.
		@return Autopsy Line
	  */
	public int getEXME_AutopsiaLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AutopsiaLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
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
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}
}