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

/** Generated Model for EXME_DiarioEnf
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_DiarioEnf extends PO implements I_EXME_DiarioEnf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DiarioEnf (Properties ctx, int EXME_DiarioEnf_ID, String trxName)
    {
      super (ctx, EXME_DiarioEnf_ID, trxName);
      /** if (EXME_DiarioEnf_ID == 0)
        {
			setEXME_CtaPac_ID (0);
			setEXME_DiarioEnf_ID (0);
			setFecha_Ingreso (new Timestamp( System.currentTimeMillis() ));
			setNiv_Caida (0);
			setNiv_Caida_E (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_DiarioEnf (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DiarioEnf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ApoyoVentilatorio getEXME_ApoyoVentilatorio() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ApoyoVentilatorio.Table_Name);
        I_EXME_ApoyoVentilatorio result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ApoyoVentilatorio)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ApoyoVentilatorio_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Ventilatory support.
		@param EXME_ApoyoVentilatorio_ID Ventilatory support	  */
	public void setEXME_ApoyoVentilatorio_ID (int EXME_ApoyoVentilatorio_ID)
	{
		if (EXME_ApoyoVentilatorio_ID < 1) 
			set_Value (COLUMNNAME_EXME_ApoyoVentilatorio_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ApoyoVentilatorio_ID, Integer.valueOf(EXME_ApoyoVentilatorio_ID));
	}

	/** Get Ventilatory support.
		@return Ventilatory support	  */
	public int getEXME_ApoyoVentilatorio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ApoyoVentilatorio_ID);
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exit Diagnosis.
		@param EXME_DiagnosticoEgreso_ID Exit Diagnosis	  */
	public void setEXME_DiagnosticoEgreso_ID (int EXME_DiagnosticoEgreso_ID)
	{
		if (EXME_DiagnosticoEgreso_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosticoEgreso_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosticoEgreso_ID, Integer.valueOf(EXME_DiagnosticoEgreso_ID));
	}

	/** Get Exit Diagnosis.
		@return Exit Diagnosis	  */
	public int getEXME_DiagnosticoEgreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoEgreso_ID);
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
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
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

	/** Set Infirmary Diary.
		@param EXME_DiarioEnf_ID Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID)
	{
		if (EXME_DiarioEnf_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiarioEnf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiarioEnf_ID, Integer.valueOf(EXME_DiarioEnf_ID));
	}

	/** Get Infirmary Diary.
		@return Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiarioEnf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EdoConciencia getEXME_EdoConciencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EdoConciencia.Table_Name);
        I_EXME_EdoConciencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EdoConciencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EdoConciencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Conscience.
		@param EXME_EdoConciencia_ID Conscience	  */
	public void setEXME_EdoConciencia_ID (int EXME_EdoConciencia_ID)
	{
		if (EXME_EdoConciencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_EdoConciencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EdoConciencia_ID, Integer.valueOf(EXME_EdoConciencia_ID));
	}

	/** Get Conscience.
		@return Conscience	  */
	public int getEXME_EdoConciencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EdoConciencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Discharge.
		@param EXME_Enfermeria_Egr Nursing Discharge	  */
	public void setEXME_Enfermeria_Egr (int EXME_Enfermeria_Egr)
	{
		set_Value (COLUMNNAME_EXME_Enfermeria_Egr, Integer.valueOf(EXME_Enfermeria_Egr));
	}

	/** Get Nursing Discharge.
		@return Nursing Discharge	  */
	public int getEXME_Enfermeria_Egr () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_Egr);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1) 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Admission.
		@param EXME_Enfermeria_Ing Nursing Admission	  */
	public void setEXME_Enfermeria_Ing (int EXME_Enfermeria_Ing)
	{
		set_Value (COLUMNNAME_EXME_Enfermeria_Ing, Integer.valueOf(EXME_Enfermeria_Ing));
	}

	/** Get Nursing Admission.
		@return Nursing Admission	  */
	public int getEXME_Enfermeria_Ing () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_Ing);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FnRespiratoria getEXME_FnRespiratoria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FnRespiratoria.Table_Name);
        I_EXME_FnRespiratoria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FnRespiratoria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FnRespiratoria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Respiratory Function.
		@param EXME_FnRespiratoria_ID Respiratory Function	  */
	public void setEXME_FnRespiratoria_ID (int EXME_FnRespiratoria_ID)
	{
		if (EXME_FnRespiratoria_ID < 1) 
			set_Value (COLUMNNAME_EXME_FnRespiratoria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FnRespiratoria_ID, Integer.valueOf(EXME_FnRespiratoria_ID));
	}

	/** Get Respiratory Function.
		@return Respiratory Function	  */
	public int getEXME_FnRespiratoria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FnRespiratoria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoEgreso getEXME_MotivoEgreso() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoEgreso.Table_Name);
        I_EXME_MotivoEgreso result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoEgreso)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoEgreso_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discharge Reason.
		@param EXME_MotivoEgreso_ID Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID)
	{
		if (EXME_MotivoEgreso_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoEgreso_ID, Integer.valueOf(EXME_MotivoEgreso_ID));
	}

	/** Get Discharge Reason.
		@return Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoEgreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoTraslado getEXME_MotivoTraslado() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoTraslado.Table_Name);
        I_EXME_MotivoTraslado result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoTraslado)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoTraslado_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Motive of Movement.
		@param EXME_MotivoTraslado_ID 
		Motive of Movement
	  */
	public void setEXME_MotivoTraslado_ID (int EXME_MotivoTraslado_ID)
	{
		if (EXME_MotivoTraslado_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoTraslado_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoTraslado_ID, Integer.valueOf(EXME_MotivoTraslado_ID));
	}

	/** Get Motive of Movement.
		@return Motive of Movement
	  */
	public int getEXME_MotivoTraslado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoTraslado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_NivelUlcera getEXME_NivelUlcera() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_NivelUlcera.Table_Name);
        I_EXME_NivelUlcera result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_NivelUlcera)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_NivelUlcera_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Ulcer Level.
		@param EXME_NivelUlcera_ID 
		Ulcer Level
	  */
	public void setEXME_NivelUlcera_ID (int EXME_NivelUlcera_ID)
	{
		if (EXME_NivelUlcera_ID < 1) 
			set_Value (COLUMNNAME_EXME_NivelUlcera_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_NivelUlcera_ID, Integer.valueOf(EXME_NivelUlcera_ID));
	}

	/** Get Ulcer Level.
		@return Ulcer Level
	  */
	public int getEXME_NivelUlcera_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NivelUlcera_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProcEsp getEXME_ProcEsp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProcEsp.Table_Name);
        I_EXME_ProcEsp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProcEsp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProcEsp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Special Procedures.
		@param EXME_ProcEsp_ID Special Procedures	  */
	public void setEXME_ProcEsp_ID (int EXME_ProcEsp_ID)
	{
		if (EXME_ProcEsp_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProcEsp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProcEsp_ID, Integer.valueOf(EXME_ProcEsp_ID));
	}

	/** Get Special Procedures.
		@return Special Procedures	  */
	public int getEXME_ProcEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcEsp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Origin Discharge.
		@param EXME_Procedencia_Egreso_ID Origin Discharge	  */
	public void setEXME_Procedencia_Egreso_ID (int EXME_Procedencia_Egreso_ID)
	{
		if (EXME_Procedencia_Egreso_ID < 1) 
			set_Value (COLUMNNAME_EXME_Procedencia_Egreso_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Procedencia_Egreso_ID, Integer.valueOf(EXME_Procedencia_Egreso_ID));
	}

	/** Get Origin Discharge.
		@return Origin Discharge	  */
	public int getEXME_Procedencia_Egreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Procedencia_Egreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Procedencia.Table_Name);
        I_EXME_Procedencia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Procedencia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Procedencia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Provenance.
		@param EXME_Procedencia_ID 
		Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID)
	{
		if (EXME_Procedencia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Procedencia_ID, Integer.valueOf(EXME_Procedencia_ID));
	}

	/** Get Provenance.
		@return Provenance
	  */
	public int getEXME_Procedencia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Procedencia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoHerida getEXME_TipoHerida() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoHerida.Table_Name);
        I_EXME_TipoHerida result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoHerida)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoHerida_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of wound.
		@param EXME_TipoHerida_ID Type of wound	  */
	public void setEXME_TipoHerida_ID (int EXME_TipoHerida_ID)
	{
		if (EXME_TipoHerida_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoHerida_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoHerida_ID, Integer.valueOf(EXME_TipoHerida_ID));
	}

	/** Get Type of wound.
		@return Type of wound	  */
	public int getEXME_TipoHerida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoHerida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Height.
		@param Estatura 
		Height
	  */
	public void setEstatura (BigDecimal Estatura)
	{
		set_Value (COLUMNNAME_Estatura, Estatura);
	}

	/** Get Height.
		@return Height
	  */
	public BigDecimal getEstatura () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Estatura);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fecha_Defuncion.
		@param Fecha_Defuncion Fecha_Defuncion	  */
	public void setFecha_Defuncion (Timestamp Fecha_Defuncion)
	{
		set_Value (COLUMNNAME_Fecha_Defuncion, Fecha_Defuncion);
	}

	/** Get Fecha_Defuncion.
		@return Fecha_Defuncion	  */
	public Timestamp getFecha_Defuncion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Defuncion);
	}

	/** Set Discharge Date.
		@param Fecha_Egreso Discharge Date	  */
	public void setFecha_Egreso (Timestamp Fecha_Egreso)
	{
		set_Value (COLUMNNAME_Fecha_Egreso, Fecha_Egreso);
	}

	/** Get Discharge Date.
		@return Discharge Date	  */
	public Timestamp getFecha_Egreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Egreso);
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

	/** Glasgow_Motora AD_Reference_ID=1200161 */
	public static final int GLASGOW_MOTORA_AD_Reference_ID=1200161;
	/** Nothing (1) = 1 */
	public static final String GLASGOW_MOTORA_Nothing1 = "1";
	/** Flaccid (2) = 2 */
	public static final String GLASGOW_MOTORA_Flaccid2 = "2";
	/** Abnormal Extension (3) = 3 */
	public static final String GLASGOW_MOTORA_AbnormalExtension3 = "3";
	/** Abnormal Flexion (4) = 4 */
	public static final String GLASGOW_MOTORA_AbnormalFlexion4 = "4";
	/** Localized pain (5) = 5 */
	public static final String GLASGOW_MOTORA_LocalizedPain5 = "5";
	/** Obey Orders (6) = 6 */
	public static final String GLASGOW_MOTORA_ObeyOrders6 = "6";
	/** Set Glasgow_Motora.
		@param Glasgow_Motora 
		Motor Glasgow Valuation
	  */
	public void setGlasgow_Motora (String Glasgow_Motora)
	{

		if (Glasgow_Motora == null || Glasgow_Motora.equals("1") || Glasgow_Motora.equals("2") || Glasgow_Motora.equals("3") || Glasgow_Motora.equals("4") || Glasgow_Motora.equals("5") || Glasgow_Motora.equals("6")); else throw new IllegalArgumentException ("Glasgow_Motora Invalid value - " + Glasgow_Motora + " - Reference_ID=1200161 - 1 - 2 - 3 - 4 - 5 - 6");		set_Value (COLUMNNAME_Glasgow_Motora, Glasgow_Motora);
	}

	/** Get Glasgow_Motora.
		@return Motor Glasgow Valuation
	  */
	public String getGlasgow_Motora () 
	{
		return (String)get_Value(COLUMNNAME_Glasgow_Motora);
	}

	/** Glasgow_Ocular AD_Reference_ID=1200159 */
	public static final int GLASGOW_OCULAR_AD_Reference_ID=1200159;
	/** Never (1) = 1 */
	public static final String GLASGOW_OCULAR_Never1 = "1";
	/** Response at Pain (2) = 2 */
	public static final String GLASGOW_OCULAR_ResponseAtPain2 = "2";
	/** Response at Talk (3) = 3 */
	public static final String GLASGOW_OCULAR_ResponseAtTalk3 = "3";
	/** Spontaneous (4) = 4 */
	public static final String GLASGOW_OCULAR_Spontaneous4 = "4";
	/** Set Glasgow_Ocular.
		@param Glasgow_Ocular 
		Eye Glasgow Valuation
	  */
	public void setGlasgow_Ocular (String Glasgow_Ocular)
	{

		if (Glasgow_Ocular == null || Glasgow_Ocular.equals("1") || Glasgow_Ocular.equals("2") || Glasgow_Ocular.equals("3") || Glasgow_Ocular.equals("4")); else throw new IllegalArgumentException ("Glasgow_Ocular Invalid value - " + Glasgow_Ocular + " - Reference_ID=1200159 - 1 - 2 - 3 - 4");		set_Value (COLUMNNAME_Glasgow_Ocular, Glasgow_Ocular);
	}

	/** Get Glasgow_Ocular.
		@return Eye Glasgow Valuation
	  */
	public String getGlasgow_Ocular () 
	{
		return (String)get_Value(COLUMNNAME_Glasgow_Ocular);
	}

	/** Glasgow_Verbal AD_Reference_ID=1200160 */
	public static final int GLASGOW_VERBAL_AD_Reference_ID=1200160;
	/** Never (1) = 1 */
	public static final String GLASGOW_VERBAL_Never1 = "1";
	/** Incomprehensible Sounds (2) = 2 */
	public static final String GLASGOW_VERBAL_IncomprehensibleSounds2 = "2";
	/** Inappropriate words (3) = 3 */
	public static final String GLASGOW_VERBAL_InappropriateWords3 = "3";
	/** Confused (4) = 4 */
	public static final String GLASGOW_VERBAL_Confused4 = "4";
	/** Oriented (5) = 5 */
	public static final String GLASGOW_VERBAL_Oriented5 = "5";
	/** Set Glasgow_Verbal.
		@param Glasgow_Verbal 
		Verbal Glasgow Valuation
	  */
	public void setGlasgow_Verbal (String Glasgow_Verbal)
	{

		if (Glasgow_Verbal == null || Glasgow_Verbal.equals("1") || Glasgow_Verbal.equals("2") || Glasgow_Verbal.equals("3") || Glasgow_Verbal.equals("4") || Glasgow_Verbal.equals("5")); else throw new IllegalArgumentException ("Glasgow_Verbal Invalid value - " + Glasgow_Verbal + " - Reference_ID=1200160 - 1 - 2 - 3 - 4 - 5");		set_Value (COLUMNNAME_Glasgow_Verbal, Glasgow_Verbal);
	}

	/** Get Glasgow_Verbal.
		@return Verbal Glasgow Valuation
	  */
	public String getGlasgow_Verbal () 
	{
		return (String)get_Value(COLUMNNAME_Glasgow_Verbal);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fall Reason.
		@param Motivo_Caida_E 
		Fall Reason
	  */
	public void setMotivo_Caida_E (String Motivo_Caida_E)
	{
		set_Value (COLUMNNAME_Motivo_Caida_E, Motivo_Caida_E);
	}

	/** Get Fall Reason.
		@return Fall Reason
	  */
	public String getMotivo_Caida_E () 
	{
		return (String)get_Value(COLUMNNAME_Motivo_Caida_E);
	}

	/** Set Fall Reason.
		@param Motivo_Caida_I 
		Fall Reason
	  */
	public void setMotivo_Caida_I (String Motivo_Caida_I)
	{
		set_Value (COLUMNNAME_Motivo_Caida_I, Motivo_Caida_I);
	}

	/** Get Fall Reason.
		@return Fall Reason
	  */
	public String getMotivo_Caida_I () 
	{
		return (String)get_Value(COLUMNNAME_Motivo_Caida_I);
	}

	/** Set Admission Reason.
		@param Motivo_Ingreso Admission Reason	  */
	public void setMotivo_Ingreso (String Motivo_Ingreso)
	{
		set_Value (COLUMNNAME_Motivo_Ingreso, Motivo_Ingreso);
	}

	/** Get Admission Reason.
		@return Admission Reason	  */
	public String getMotivo_Ingreso () 
	{
		return (String)get_Value(COLUMNNAME_Motivo_Ingreso);
	}

	/** Set Fall Level.
		@param Niv_Caida Fall Level	  */
	public void setNiv_Caida (int Niv_Caida)
	{
		set_Value (COLUMNNAME_Niv_Caida, Integer.valueOf(Niv_Caida));
	}

	/** Get Fall Level.
		@return Fall Level	  */
	public int getNiv_Caida () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Niv_Caida);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Fall Level.
		@param Niv_Caida_E Discharge Fall Level	  */
	public void setNiv_Caida_E (int Niv_Caida_E)
	{
		set_Value (COLUMNNAME_Niv_Caida_E, Integer.valueOf(Niv_Caida_E));
	}

	/** Get Discharge Fall Level.
		@return Discharge Fall Level	  */
	public int getNiv_Caida_E () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Niv_Caida_E);
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

	/** Set Admission Notes.
		@param Observaciones_Ingreso Admission Notes	  */
	public void setObservaciones_Ingreso (String Observaciones_Ingreso)
	{
		set_Value (COLUMNNAME_Observaciones_Ingreso, Observaciones_Ingreso);
	}

	/** Get Admission Notes.
		@return Admission Notes	  */
	public String getObservaciones_Ingreso () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones_Ingreso);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Pupilas AD_Reference_ID=1200166 */
	public static final int PUPILAS_AD_Reference_ID=1200166;
	/** Reacting = RE */
	public static final String PUPILAS_Reacting = "RE";
	/** Non-Reacting = NR */
	public static final String PUPILAS_Non_Reacting = "NR";
	/** Set Pupilas.
		@param Pupilas 
		Pupil
	  */
	public void setPupilas (String Pupilas)
	{

		if (Pupilas == null || Pupilas.equals("RE") || Pupilas.equals("NR")); else throw new IllegalArgumentException ("Pupilas Invalid value - " + Pupilas + " - Reference_ID=1200166 - RE - NR");		set_Value (COLUMNNAME_Pupilas, Pupilas);
	}

	/** Get Pupilas.
		@return Pupil
	  */
	public String getPupilas () 
	{
		return (String)get_Value(COLUMNNAME_Pupilas);
	}

	/** Riesgo_Caida_E AD_Reference_ID=1200158 */
	public static final int RIESGO_CAIDA_E_AD_Reference_ID=1200158;
	/** Low Risk = B */
	public static final String RIESGO_CAIDA_E_LowRisk = "B";
	/** High Risk = A */
	public static final String RIESGO_CAIDA_E_HighRisk = "A";
	/** Set Risk of a fall in the discharge of the Patient .
		@param Riesgo_Caida_E Risk of a fall in the discharge of the Patient 	  */
	public void setRiesgo_Caida_E (String Riesgo_Caida_E)
	{

		if (Riesgo_Caida_E == null || Riesgo_Caida_E.equals("B") || Riesgo_Caida_E.equals("A")); else throw new IllegalArgumentException ("Riesgo_Caida_E Invalid value - " + Riesgo_Caida_E + " - Reference_ID=1200158 - B - A");		set_Value (COLUMNNAME_Riesgo_Caida_E, Riesgo_Caida_E);
	}

	/** Get Risk of a fall in the discharge of the Patient .
		@return Risk of a fall in the discharge of the Patient 	  */
	public String getRiesgo_Caida_E () 
	{
		return (String)get_Value(COLUMNNAME_Riesgo_Caida_E);
	}

	/** Riesgo_Caida_I AD_Reference_ID=1200158 */
	public static final int RIESGO_CAIDA_I_AD_Reference_ID=1200158;
	/** Low Risk = B */
	public static final String RIESGO_CAIDA_I_LowRisk = "B";
	/** High Risk = A */
	public static final String RIESGO_CAIDA_I_HighRisk = "A";
	/** Set Risk of a fall in the Income of the Patient .
		@param Riesgo_Caida_I Risk of a fall in the Income of the Patient 	  */
	public void setRiesgo_Caida_I (String Riesgo_Caida_I)
	{

		if (Riesgo_Caida_I == null || Riesgo_Caida_I.equals("B") || Riesgo_Caida_I.equals("A")); else throw new IllegalArgumentException ("Riesgo_Caida_I Invalid value - " + Riesgo_Caida_I + " - Reference_ID=1200158 - B - A");		set_Value (COLUMNNAME_Riesgo_Caida_I, Riesgo_Caida_I);
	}

	/** Get Risk of a fall in the Income of the Patient .
		@return Risk of a fall in the Income of the Patient 	  */
	public String getRiesgo_Caida_I () 
	{
		return (String)get_Value(COLUMNNAME_Riesgo_Caida_I);
	}

	/** TipoSangre AD_Reference_ID=1200172 */
	public static final int TIPOSANGRE_AD_Reference_ID=1200172;
	/** AB+ = AB+ */
	public static final String TIPOSANGRE_ABPlus = "AB+";
	/** AB- = AB- */
	public static final String TIPOSANGRE_AB_ = "AB-";
	/** A+ = A+  */
	public static final String TIPOSANGRE_APlus = "A+";
	/** A- = A-  */
	public static final String TIPOSANGRE_A_ = "A-";
	/** B+ = B+  */
	public static final String TIPOSANGRE_BPlus = "B+";
	/** B- = B-  */
	public static final String TIPOSANGRE_B_ = "B-";
	/** O+ = O+  */
	public static final String TIPOSANGRE_OPlus = "O+";
	/** O- = O-  */
	public static final String TIPOSANGRE_O_ = "O-";
	/** Unknown = UK  */
	public static final String TIPOSANGRE_Unknown = "UK";
	/** Set Blood Type.
		@param TipoSangre Blood Type	  */
	public void setTipoSangre (String TipoSangre)
	{

		if (TipoSangre == null || TipoSangre.equals("AB+") || TipoSangre.equals("AB-") || TipoSangre.equals("A+") || TipoSangre.equals("A-") || TipoSangre.equals("B+") || TipoSangre.equals("B-") || TipoSangre.equals("O+") || TipoSangre.equals("O-") || TipoSangre.equals("UK")); else throw new IllegalArgumentException ("TipoSangre Invalid value - " + TipoSangre + " - Reference_ID=1200172 - AB+ - AB- - A+  - A-  - B+  - B-  - O+  - O-  - UK ");		set_Value (COLUMNNAME_TipoSangre, TipoSangre);
	}

	/** Get Blood Type.
		@return Blood Type	  */
	public String getTipoSangre () 
	{
		return (String)get_Value(COLUMNNAME_TipoSangre);
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