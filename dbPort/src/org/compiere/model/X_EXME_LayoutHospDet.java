/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LayoutHospDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LayoutHospDet extends PO implements I_EXME_LayoutHospDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LayoutHospDet (Properties ctx, int EXME_LayoutHospDet_ID, String trxName)
    {
      super (ctx, EXME_LayoutHospDet_ID, trxName);
      /** if (EXME_LayoutHospDet_ID == 0)
        {
			setCaptura (0);
			setC_Invoice_ID (0);
			setEXME_LayoutHospDet_ID (0);
			setEXME_LayoutHosp_ID (0);
			setTipo_Servicio (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LayoutHospDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LayoutHospDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Capture.
		@param Captura 
		Capture
	  */
	public void setCaptura (int Captura)
	{
		set_Value (COLUMNNAME_Captura, Integer.valueOf(Captura));
	}

	/** Get Capture.
		@return Capture
	  */
	public int getCaptura () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Captura);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
		set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hospital Layout Detail.
		@param EXME_LayoutHospDet_ID Hospital Layout Detail	  */
	public void setEXME_LayoutHospDet_ID (int EXME_LayoutHospDet_ID)
	{
		if (EXME_LayoutHospDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutHospDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LayoutHospDet_ID, Integer.valueOf(EXME_LayoutHospDet_ID));
	}

	/** Get Hospital Layout Detail.
		@return Hospital Layout Detail	  */
	public int getEXME_LayoutHospDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LayoutHospDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_LayoutHosp getEXME_LayoutHosp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_LayoutHosp.Table_Name);
        I_EXME_LayoutHosp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_LayoutHosp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_LayoutHosp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inpatient Layout.
		@param EXME_LayoutHosp_ID 
		Inpatient Layout
	  */
	public void setEXME_LayoutHosp_ID (int EXME_LayoutHosp_ID)
	{
		if (EXME_LayoutHosp_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutHosp_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_LayoutHosp_ID, Integer.valueOf(EXME_LayoutHosp_ID));
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

	/** Set Service Type.
		@param Tipo_Servicio 
		Service Type
	  */
	public void setTipo_Servicio (int Tipo_Servicio)
	{
		set_Value (COLUMNNAME_Tipo_Servicio, Integer.valueOf(Tipo_Servicio));
	}

	/** Get Service Type.
		@return Service Type
	  */
	public int getTipo_Servicio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Tipo_Servicio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}