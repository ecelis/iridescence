/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_NotificaIngreso
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_NotificaIngreso extends PO implements I_EXME_NotificaIngreso, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_NotificaIngreso (Properties ctx, int EXME_NotificaIngreso_ID, String trxName)
    {
      super (ctx, EXME_NotificaIngreso_ID, trxName);
      /** if (EXME_NotificaIngreso_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_Hist_Exp_ID (0);
			setEXME_NotificaIngreso_ID (0);
			setFecha_Ingreso (new Timestamp( System.currentTimeMillis() ));
			setIngresoPor (null);
			setPrint_AdmSegReso (false);
			setPrint_Cargos (false);
			setPrint_HistClinica (false);
			setPrint_HjFrontal (false);
			setPrint_IndcMedcs (false);
			setPrint_NtMedcs (false);
			setPrint_Sometria (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_NotificaIngreso (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_NotificaIngreso[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Notes.
		@param Comentarios Notes	  */
	public void setComentarios (String Comentarios)
	{
		set_Value (COLUMNNAME_Comentarios, Comentarios);
	}

	/** Get Notes.
		@return Notes	  */
	public String getComentarios () 
	{
		return (String)get_Value(COLUMNNAME_Comentarios);
	}

	public I_EXME_Cama getEXME_Cama() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cama.Table_Name);
        I_EXME_Cama result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cama)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cama_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
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

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Exp.Table_Name);
        I_EXME_Hist_Exp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Exp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Exp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Hist_Exp_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
	}

	/** Get Medical File.
		@return Medical File
	  */
	public int getEXME_Hist_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Checkin Notification.
		@param EXME_NotificaIngreso_ID Checkin Notification	  */
	public void setEXME_NotificaIngreso_ID (int EXME_NotificaIngreso_ID)
	{
		if (EXME_NotificaIngreso_ID < 1)
			 throw new IllegalArgumentException ("EXME_NotificaIngreso_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_NotificaIngreso_ID, Integer.valueOf(EXME_NotificaIngreso_ID));
	}

	/** Get Checkin Notification.
		@return Checkin Notification	  */
	public int getEXME_NotificaIngreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NotificaIngreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Entrance Date.
		@param Fecha_Ingreso Entrance Date	  */
	public void setFecha_Ingreso (Timestamp Fecha_Ingreso)
	{
		if (Fecha_Ingreso == null)
			throw new IllegalArgumentException ("Fecha_Ingreso is mandatory.");
		set_Value (COLUMNNAME_Fecha_Ingreso, Fecha_Ingreso);
	}

	/** Get Entrance Date.
		@return Entrance Date	  */
	public Timestamp getFecha_Ingreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ingreso);
	}

	/** IngresoPor AD_Reference_ID=1200006 */
	public static final int INGRESOPOR_AD_Reference_ID=1200006;
	/** Admission = A */
	public static final String INGRESOPOR_Admission = "A";
	/** Medical Appointment = C */
	public static final String INGRESOPOR_MedicalAppointment = "C";
	/** Emergency = U */
	public static final String INGRESOPOR_Emergency = "U";
	/** Set Enter By.
		@param IngresoPor 
		Enter By
	  */
	public void setIngresoPor (String IngresoPor)
	{
		if (IngresoPor == null) throw new IllegalArgumentException ("IngresoPor is mandatory");
		if (IngresoPor.equals("A") || IngresoPor.equals("C") || IngresoPor.equals("U")); else throw new IllegalArgumentException ("IngresoPor Invalid value - " + IngresoPor + " - Reference_ID=1200006 - A - C - U");		set_Value (COLUMNNAME_IngresoPor, IngresoPor);
	}

	/** Get Enter By.
		@return Enter By
	  */
	public String getIngresoPor () 
	{
		return (String)get_Value(COLUMNNAME_IngresoPor);
	}

	/** Set Print AdmSegReso.
		@param Print_AdmSegReso Print AdmSegReso	  */
	public void setPrint_AdmSegReso (boolean Print_AdmSegReso)
	{
		set_Value (COLUMNNAME_Print_AdmSegReso, Boolean.valueOf(Print_AdmSegReso));
	}

	/** Get Print AdmSegReso.
		@return Print AdmSegReso	  */
	public boolean isPrint_AdmSegReso () 
	{
		Object oo = get_Value(COLUMNNAME_Print_AdmSegReso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Charges.
		@param Print_Cargos Print Charges	  */
	public void setPrint_Cargos (boolean Print_Cargos)
	{
		set_Value (COLUMNNAME_Print_Cargos, Boolean.valueOf(Print_Cargos));
	}

	/** Get Print Charges.
		@return Print Charges	  */
	public boolean isPrint_Cargos () 
	{
		Object oo = get_Value(COLUMNNAME_Print_Cargos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Clinical History.
		@param Print_HistClinica Print Clinical History	  */
	public void setPrint_HistClinica (boolean Print_HistClinica)
	{
		set_Value (COLUMNNAME_Print_HistClinica, Boolean.valueOf(Print_HistClinica));
	}

	/** Get Print Clinical History.
		@return Print Clinical History	  */
	public boolean isPrint_HistClinica () 
	{
		Object oo = get_Value(COLUMNNAME_Print_HistClinica);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Front Sheet.
		@param Print_HjFrontal Print Front Sheet	  */
	public void setPrint_HjFrontal (boolean Print_HjFrontal)
	{
		set_Value (COLUMNNAME_Print_HjFrontal, Boolean.valueOf(Print_HjFrontal));
	}

	/** Get Print Front Sheet.
		@return Print Front Sheet	  */
	public boolean isPrint_HjFrontal () 
	{
		Object oo = get_Value(COLUMNNAME_Print_HjFrontal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Medical Instructions.
		@param Print_IndcMedcs Print Medical Instructions	  */
	public void setPrint_IndcMedcs (boolean Print_IndcMedcs)
	{
		set_Value (COLUMNNAME_Print_IndcMedcs, Boolean.valueOf(Print_IndcMedcs));
	}

	/** Get Print Medical Instructions.
		@return Print Medical Instructions	  */
	public boolean isPrint_IndcMedcs () 
	{
		Object oo = get_Value(COLUMNNAME_Print_IndcMedcs);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Medical Notes.
		@param Print_NtMedcs Print Medical Notes	  */
	public void setPrint_NtMedcs (boolean Print_NtMedcs)
	{
		set_Value (COLUMNNAME_Print_NtMedcs, Boolean.valueOf(Print_NtMedcs));
	}

	/** Get Print Medical Notes.
		@return Print Medical Notes	  */
	public boolean isPrint_NtMedcs () 
	{
		Object oo = get_Value(COLUMNNAME_Print_NtMedcs);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Sometría.
		@param Print_Sometria Print Sometría	  */
	public void setPrint_Sometria (boolean Print_Sometria)
	{
		set_Value (COLUMNNAME_Print_Sometria, Boolean.valueOf(Print_Sometria));
	}

	/** Get Print Sometría.
		@return Print Sometría	  */
	public boolean isPrint_Sometria () 
	{
		Object oo = get_Value(COLUMNNAME_Print_Sometria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}