/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LayoutUrgDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_LayoutUrgDet extends PO implements I_EXME_LayoutUrgDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LayoutUrgDet (Properties ctx, int EXME_LayoutUrgDet_ID, String trxName)
    {
      super (ctx, EXME_LayoutUrgDet_ID, trxName);
      /** if (EXME_LayoutUrgDet_ID == 0)
        {
			setCaptura (0);
			setC_Invoice_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_LayoutUrgDet_ID (0);
			setEXME_LayoutUrg_ID (0);
			setEXME_Paciente_ID (0);
			setTipo_Servicio (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LayoutUrgDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LayoutUrgDet[")
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

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Urgency Layout Detail.
		@param EXME_LayoutUrgDet_ID Urgency Layout Detail	  */
	public void setEXME_LayoutUrgDet_ID (int EXME_LayoutUrgDet_ID)
	{
		if (EXME_LayoutUrgDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutUrgDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LayoutUrgDet_ID, Integer.valueOf(EXME_LayoutUrgDet_ID));
	}

	/** Get Urgency Layout Detail.
		@return Urgency Layout Detail	  */
	public int getEXME_LayoutUrgDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LayoutUrgDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_LayoutUrg getEXME_LayoutUrg() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_LayoutUrg.Table_Name);
        I_EXME_LayoutUrg result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_LayoutUrg)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_LayoutUrg_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Urgency Layout.
		@param EXME_LayoutUrg_ID 
		Urgency Layout
	  */
	public void setEXME_LayoutUrg_ID (int EXME_LayoutUrg_ID)
	{
		if (EXME_LayoutUrg_ID < 1)
			 throw new IllegalArgumentException ("EXME_LayoutUrg_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_LayoutUrg_ID, Integer.valueOf(EXME_LayoutUrg_ID));
	}

	/** Get Urgency Layout.
		@return Urgency Layout
	  */
	public int getEXME_LayoutUrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LayoutUrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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