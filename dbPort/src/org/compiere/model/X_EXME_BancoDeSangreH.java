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

/** Generated Model for EXME_BancoDeSangreH
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_BancoDeSangreH extends PO implements I_EXME_BancoDeSangreH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BancoDeSangreH (Properties ctx, int EXME_BancoDeSangreH_ID, String trxName)
    {
      super (ctx, EXME_BancoDeSangreH_ID, trxName);
      /** if (EXME_BancoDeSangreH_ID == 0)
        {
			setEXME_BancoDeSangreH_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_BancoDeSangreH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BancoDeSangreH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cost of the Study.
		@param CostoEstudio 
		Cost of the Study
	  */
	public void setCostoEstudio (int CostoEstudio)
	{
		set_Value (COLUMNNAME_CostoEstudio, Integer.valueOf(CostoEstudio));
	}

	/** Get Cost of the Study.
		@return Cost of the Study
	  */
	public int getCostoEstudio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CostoEstudio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set National Identification Number.
		@param Curp 
		National Identification Number
	  */
	public void setCurp (String Curp)
	{
		set_Value (COLUMNNAME_Curp, Curp);
	}

	/** Get National Identification Number.
		@return National Identification Number
	  */
	public String getCurp () 
	{
		return (String)get_Value(COLUMNNAME_Curp);
	}

	/** Set Requested Study name.
		@param EstudioSolicitado 
		Requested Study name
	  */
	public void setEstudioSolicitado (String EstudioSolicitado)
	{
		set_Value (COLUMNNAME_EstudioSolicitado, EstudioSolicitado);
	}

	/** Get Requested Study name.
		@return Requested Study name
	  */
	public String getEstudioSolicitado () 
	{
		return (String)get_Value(COLUMNNAME_EstudioSolicitado);
	}

	/** Set Blood Bank.
		@param EXME_BancoDeSangreH_ID 
		Blood Bank
	  */
	public void setEXME_BancoDeSangreH_ID (int EXME_BancoDeSangreH_ID)
	{
		if (EXME_BancoDeSangreH_ID < 1)
			 throw new IllegalArgumentException ("EXME_BancoDeSangreH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BancoDeSangreH_ID, Integer.valueOf(EXME_BancoDeSangreH_ID));
	}

	/** Get Blood Bank.
		@return Blood Bank
	  */
	public int getEXME_BancoDeSangreH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BancoDeSangreH_ID);
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
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, null);
		else 
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

	/** Set Study Date.
		@param FechaEstudio 
		Study Date
	  */
	public void setFechaEstudio (Timestamp FechaEstudio)
	{
		set_Value (COLUMNNAME_FechaEstudio, FechaEstudio);
	}

	/** Get Study Date.
		@return Study Date
	  */
	public Timestamp getFechaEstudio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaEstudio);
	}

	/** Set Analisys of the Folio.
		@param FolioAnalisis 
		Analisys of the Folio
	  */
	public void setFolioAnalisis (BigDecimal FolioAnalisis)
	{
		set_Value (COLUMNNAME_FolioAnalisis, FolioAnalisis);
	}

	/** Get Analisys of the Folio.
		@return Analisys of the Folio
	  */
	public BigDecimal getFolioAnalisis () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FolioAnalisis);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Analysis of Observations.
		@param ObservacionesAnalisis 
		Analysis of Observations
	  */
	public void setObservacionesAnalisis (String ObservacionesAnalisis)
	{
		set_Value (COLUMNNAME_ObservacionesAnalisis, ObservacionesAnalisis);
	}

	/** Get Analysis of Observations.
		@return Analysis of Observations
	  */
	public String getObservacionesAnalisis () 
	{
		return (String)get_Value(COLUMNNAME_ObservacionesAnalisis);
	}
}