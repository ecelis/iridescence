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

/** Generated Model for EXME_Control_Exp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Control_Exp extends PO implements I_EXME_Control_Exp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Control_Exp (Properties ctx, int EXME_Control_Exp_ID, String trxName)
    {
      super (ctx, EXME_Control_Exp_ID, trxName);
      /** if (EXME_Control_Exp_ID == 0)
        {
			setAD_User_ID (0);
			setDocumentos (null);
			setEXME_Control_Exp_ID (0);
			setEXME_Especialidad_ID (0);
			setEXME_Hist_Exp_ID (0);
			setTipoExp (null);
			setTipoMovto (null);
			setTipoPrestamo (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Control_Exp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Control_Exp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User Doing the Return.
		@param AD_User_Devol_ID User Doing the Return	  */
	public void setAD_User_Devol_ID (int AD_User_Devol_ID)
	{
		if (AD_User_Devol_ID < 1) 
			set_Value (COLUMNNAME_AD_User_Devol_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_Devol_ID, Integer.valueOf(AD_User_Devol_ID));
	}

	/** Get User Doing the Return.
		@return User Doing the Return	  */
	public int getAD_User_Devol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_Devol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Documentos AD_Reference_ID=1200046 */
	public static final int DOCUMENTOS_AD_Reference_ID=1200046;
	/** Rays = R */
	public static final String DOCUMENTOS_Rays = "R";
	/** Sheet = H */
	public static final String DOCUMENTOS_Sheet = "H";
	/** All = T */
	public static final String DOCUMENTOS_All = "T";
	/** Set Documents.
		@param Documentos Documents	  */
	public void setDocumentos (String Documentos)
	{
		if (Documentos == null) throw new IllegalArgumentException ("Documentos is mandatory");
		if (Documentos.equals("R") || Documentos.equals("H") || Documentos.equals("T")); else throw new IllegalArgumentException ("Documentos Invalid value - " + Documentos + " - Reference_ID=1200046 - R - H - T");		set_Value (COLUMNNAME_Documentos, Documentos);
	}

	/** Get Documents.
		@return Documents	  */
	public String getDocumentos () 
	{
		return (String)get_Value(COLUMNNAME_Documentos);
	}

	/** Estatus AD_Reference_ID=1200079 */
	public static final int ESTATUS_AD_Reference_ID=1200079;
	/** None = N */
	public static final String ESTATUS_None = "N";
	/** Lended = P */
	public static final String ESTATUS_Lended = "P";
	/** Returned = D */
	public static final String ESTATUS_Returned = "D";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("N") || Estatus.equals("P") || Estatus.equals("D")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200079 - N - P - D");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Patient File Control.
		@param EXME_Control_Exp_ID Patient File Control	  */
	public void setEXME_Control_Exp_ID (int EXME_Control_Exp_ID)
	{
		if (EXME_Control_Exp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Control_Exp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Control_Exp_ID, Integer.valueOf(EXME_Control_Exp_ID));
	}

	/** Get Patient File Control.
		@return Patient File Control	  */
	public int getEXME_Control_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Control_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
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

	/** Set Return Date.
		@param FechaDevol 
		Return Date
	  */
	public void setFechaDevol (Timestamp FechaDevol)
	{
		set_Value (COLUMNNAME_FechaDevol, FechaDevol);
	}

	/** Get Return Date.
		@return Return Date
	  */
	public Timestamp getFechaDevol () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaDevol);
	}

	/** Set Lending Date.
		@param FechaPtmo 
		Lending Date and Time
	  */
	public void setFechaPtmo (Timestamp FechaPtmo)
	{
		set_Value (COLUMNNAME_FechaPtmo, FechaPtmo);
	}

	/** Get Lending Date.
		@return Lending Date and Time
	  */
	public Timestamp getFechaPtmo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaPtmo);
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

	/** TipoExp AD_Reference_ID=1200047 */
	public static final int TIPOEXP_AD_Reference_ID=1200047;
	/** Hospitalization = H */
	public static final String TIPOEXP_Hospitalization = "H";
	/** External Consult = E */
	public static final String TIPOEXP_ExternalConsult = "E";
	/** Vale = V */
	public static final String TIPOEXP_Vale = "V";
	/** Set Patient File Type.
		@param TipoExp Patient File Type	  */
	public void setTipoExp (String TipoExp)
	{
		if (TipoExp == null) throw new IllegalArgumentException ("TipoExp is mandatory");
		if (TipoExp.equals("H") || TipoExp.equals("E") || TipoExp.equals("V")); else throw new IllegalArgumentException ("TipoExp Invalid value - " + TipoExp + " - Reference_ID=1200047 - H - E - V");		set_Value (COLUMNNAME_TipoExp, TipoExp);
	}

	/** Get Patient File Type.
		@return Patient File Type	  */
	public String getTipoExp () 
	{
		return (String)get_Value(COLUMNNAME_TipoExp);
	}

	/** TipoMovto AD_Reference_ID=1200048 */
	public static final int TIPOMOVTO_AD_Reference_ID=1200048;
	/** Lending = S */
	public static final String TIPOMOVTO_Lending = "S";
	/** Return = E */
	public static final String TIPOMOVTO_Return = "E";
	/** Set Movement Type.
		@param TipoMovto Movement Type	  */
	public void setTipoMovto (String TipoMovto)
	{
		if (TipoMovto == null) throw new IllegalArgumentException ("TipoMovto is mandatory");
		if (TipoMovto.equals("S") || TipoMovto.equals("E")); else throw new IllegalArgumentException ("TipoMovto Invalid value - " + TipoMovto + " - Reference_ID=1200048 - S - E");		set_Value (COLUMNNAME_TipoMovto, TipoMovto);
	}

	/** Get Movement Type.
		@return Movement Type	  */
	public String getTipoMovto () 
	{
		return (String)get_Value(COLUMNNAME_TipoMovto);
	}

	/** Set Lending Type.
		@param TipoPrestamo Lending Type	  */
	public void setTipoPrestamo (boolean TipoPrestamo)
	{
		set_Value (COLUMNNAME_TipoPrestamo, Boolean.valueOf(TipoPrestamo));
	}

	/** Get Lending Type.
		@return Lending Type	  */
	public boolean isTipoPrestamo () 
	{
		Object oo = get_Value(COLUMNNAME_TipoPrestamo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Volumes.
		@param Tomos Volumes	  */
	public void setTomos (BigDecimal Tomos)
	{
		set_Value (COLUMNNAME_Tomos, Tomos);
	}

	/** Get Volumes.
		@return Volumes	  */
	public BigDecimal getTomos () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tomos);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}