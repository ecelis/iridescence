/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LayoutHosp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LayoutHosp extends PO implements I_EXME_LayoutHosp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LayoutHosp (Properties ctx, int EXME_LayoutHosp_ID, String trxName)
    {
      super (ctx, EXME_LayoutHosp_ID, trxName);
      /** if (EXME_LayoutHosp_ID == 0)
        {
			setAnio (0);
			setC_BPartner_ID (0);
			setEXME_LayoutHosp_ID (0);
			setNo_Layout (0);
			setPeriodo (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LayoutHosp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LayoutHosp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Year.
		@param Anio 
		Year
	  */
	public void setAnio (int Anio)
	{
		set_Value (COLUMNNAME_Anio, Integer.valueOf(Anio));
	}

	/** Get Year.
		@return Year
	  */
	public int getAnio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Anio);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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

	/** Set Inpatient Layout.
		@param EXME_LayoutHosp_ID 
		Inpatient Layout
	  */
	public void setEXME_LayoutHosp_ID (int EXME_LayoutHosp_ID)
	{
		if (EXME_LayoutHosp_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutHosp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LayoutHosp_ID, Integer.valueOf(EXME_LayoutHosp_ID));
	}

	/** Get Inpatient Layout.
		@return Inpatient Layout
	  */
	public int getEXME_LayoutHosp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LayoutHosp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Layout Number.
		@param No_Layout 
		Layout Number
	  */
	public void setNo_Layout (int No_Layout)
	{
		set_Value (COLUMNNAME_No_Layout, Integer.valueOf(No_Layout));
	}

	/** Get Layout Number.
		@return Layout Number
	  */
	public int getNo_Layout () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Layout);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period.
		@param Periodo 
		Period
	  */
	public void setPeriodo (int Periodo)
	{
		set_Value (COLUMNNAME_Periodo, Integer.valueOf(Periodo));
	}

	/** Get Period.
		@return Period
	  */
	public int getPeriodo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Periodo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}