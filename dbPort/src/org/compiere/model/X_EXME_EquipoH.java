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

/** Generated Model for EXME_EquipoH
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EquipoH extends PO implements I_EXME_EquipoH, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EquipoH (Properties ctx, int EXME_EquipoH_ID, String trxName)
    {
      super (ctx, EXME_EquipoH_ID, trxName);
      /** if (EXME_EquipoH_ID == 0)
        {
			setEXME_Area_ID (0);
			setEXME_EquipoH_ID (0);
			setEXME_Equipo_ID (0);
			setEstatus_Equipo (null);
			setFecha_Fin (new Timestamp( System.currentTimeMillis() ));
			setFecha_Ini (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EquipoH (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EquipoH[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Area getEXME_Area() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Area.Table_Name);
        I_EXME_Area result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Area)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Area_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
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
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
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

	/** Set Equipment History.
		@param EXME_EquipoH_ID Equipment History	  */
	public void setEXME_EquipoH_ID (int EXME_EquipoH_ID)
	{
		if (EXME_EquipoH_ID < 1)
			 throw new IllegalArgumentException ("EXME_EquipoH_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EquipoH_ID, Integer.valueOf(EXME_EquipoH_ID));
	}

	/** Get Equipment History.
		@return Equipment History	  */
	public int getEXME_EquipoH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EquipoH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Equipo getEXME_Equipo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Equipo.Table_Name);
        I_EXME_Equipo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Equipo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Equipo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Equipo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
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

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Habitacion.Table_Name);
        I_EXME_Habitacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Habitacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Habitacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		if (EXME_Habitacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
	}

	/** Get Room.
		@return Room
	  */
	public int getEXME_Habitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Habitacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoCita getEXME_MotivoCita() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCita.Table_Name);
        I_EXME_MotivoCita result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCita)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCita_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Motive of appointment.
		@param EXME_MotivoCita_ID 
		Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID)
	{
		if (EXME_MotivoCita_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, Integer.valueOf(EXME_MotivoCita_ID));
	}

	/** Get Motive of appointment.
		@return Motive of appointment
	  */
	public int getEXME_MotivoCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgQuiro.Table_Name);
        I_EXME_ProgQuiro result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgQuiro)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgQuiro_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schedule of Surgery Room.
		@param EXME_ProgQuiro_ID 
		Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
	}

	/** Get Schedule of Surgery Room.
		@return Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgQuiro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Quirofano.Table_Name);
        I_EXME_Quirofano result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Quirofano)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Quirofano_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1) 
			set_Value (COLUMNNAME_EXME_Quirofano_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Quirofano_ID, Integer.valueOf(EXME_Quirofano_ID));
	}

	/** Get Surgery Room.
		@return Surgey Room
	  */
	public int getEXME_Quirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Quirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Estatus_Equipo AD_Reference_ID=1200113 */
	public static final int ESTATUS_EQUIPO_AD_Reference_ID=1200113;
	/** Sacheduled = P */
	public static final String ESTATUS_EQUIPO_Sacheduled = "P";
	/** Used = U */
	public static final String ESTATUS_EQUIPO_Used = "U";
	/** Maintenance = M */
	public static final String ESTATUS_EQUIPO_Maintenance = "M";
	/** Available = D */
	public static final String ESTATUS_EQUIPO_Available = "D";
	/** Ordered = S */
	public static final String ESTATUS_EQUIPO_Ordered = "S";
	/** Cancelled = C */
	public static final String ESTATUS_EQUIPO_Cancelled = "C";
	/** Set Equipment Status.
		@param Estatus_Equipo Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo)
	{
		if (Estatus_Equipo == null) throw new IllegalArgumentException ("Estatus_Equipo is mandatory");
		if (Estatus_Equipo.equals("P") || Estatus_Equipo.equals("U") || Estatus_Equipo.equals("M") || Estatus_Equipo.equals("D") || Estatus_Equipo.equals("S") || Estatus_Equipo.equals("C")); else throw new IllegalArgumentException ("Estatus_Equipo Invalid value - " + Estatus_Equipo + " - Reference_ID=1200113 - P - U - M - D - S - C");		set_Value (COLUMNNAME_Estatus_Equipo, Estatus_Equipo);
	}

	/** Get Equipment Status.
		@return Equipment Status	  */
	public String getEstatus_Equipo () 
	{
		return (String)get_Value(COLUMNNAME_Estatus_Equipo);
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		if (Fecha_Fin == null)
			throw new IllegalArgumentException ("Fecha_Fin is mandatory.");
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini)
	{
		if (Fecha_Ini == null)
			throw new IllegalArgumentException ("Fecha_Ini is mandatory.");
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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