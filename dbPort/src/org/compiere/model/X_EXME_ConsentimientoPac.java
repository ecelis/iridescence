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

/** Generated Model for EXME_ConsentimientoPac
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_ConsentimientoPac extends PO implements I_EXME_ConsentimientoPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConsentimientoPac (Properties ctx, int EXME_ConsentimientoPac_ID, String trxName)
    {
      super (ctx, EXME_ConsentimientoPac_ID, trxName);
      /** if (EXME_ConsentimientoPac_ID == 0)
        {
			setEXME_ConsentimientoPac_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConsentimientoPac (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConsentimientoPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set File.
		@param Archivo File	  */
	public void setArchivo (byte[] Archivo)
	{
		set_Value (COLUMNNAME_Archivo, Archivo);
	}

	/** Get File.
		@return File	  */
	public byte[] getArchivo () 
	{
		return (byte[])get_Value(COLUMNNAME_Archivo);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set EXME_ConsentimientoPac_ID.
		@param EXME_ConsentimientoPac_ID EXME_ConsentimientoPac_ID	  */
	public void setEXME_ConsentimientoPac_ID (int EXME_ConsentimientoPac_ID)
	{
		if (EXME_ConsentimientoPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConsentimientoPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConsentimientoPac_ID, Integer.valueOf(EXME_ConsentimientoPac_ID));
	}

	/** Get EXME_ConsentimientoPac_ID.
		@return EXME_ConsentimientoPac_ID	  */
	public int getEXME_ConsentimientoPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConsentimientoPac_ID);
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	public I_EXME_Plantilla getEXME_Plantilla() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Plantilla.Table_Name);
        I_EXME_Plantilla result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Plantilla)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Plantilla_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Template.
		@param EXME_Plantilla_ID Template	  */
	public void setEXME_Plantilla_ID (int EXME_Plantilla_ID)
	{
		if (EXME_Plantilla_ID < 1) 
			set_Value (COLUMNNAME_EXME_Plantilla_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Plantilla_ID, Integer.valueOf(EXME_Plantilla_ID));
	}

	/** Get Template.
		@return Template	  */
	public int getEXME_Plantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Plantilla_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Format.
		@param FormatoArchivo 
		File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo)
	{
		set_Value (COLUMNNAME_FormatoArchivo, FormatoArchivo);
	}

	/** Get File Format.
		@return File Format
	  */
	public String getFormatoArchivo () 
	{
		return (String)get_Value(COLUMNNAME_FormatoArchivo);
	}

	/** Set File Name.
		@param NombreArchivo 
		File Name
	  */
	public void setNombreArchivo (String NombreArchivo)
	{
		set_Value (COLUMNNAME_NombreArchivo, NombreArchivo);
	}

	/** Get File Name.
		@return File Name
	  */
	public String getNombreArchivo () 
	{
		return (String)get_Value(COLUMNNAME_NombreArchivo);
	}

	/** ResStatus AD_Reference_ID=1200502 */
	public static final int RESSTATUS_AD_Reference_ID=1200502;
	/** Limited Support = LS */
	public static final String RESSTATUS_LimitedSupport = "LS";
	/** Do not resuscitate = NR */
	public static final String RESSTATUS_DoNotResuscitate = "NR";
	/** Do not intubate = NI */
	public static final String RESSTATUS_DoNotIntubate = "NI";
	/** Full code = 1F */
	public static final String RESSTATUS_FullCode = "1F";
	/** Comfort care only = CO */
	public static final String RESSTATUS_ComfortCareOnly = "CO";
	/** Set Resuscitative Status.
		@param ResStatus Resuscitative Status	  */
	public void setResStatus (String ResStatus)
	{

		if (ResStatus == null || ResStatus.equals("LS") || ResStatus.equals("NR") || ResStatus.equals("NI") || ResStatus.equals("1F") || ResStatus.equals("CO")); else throw new IllegalArgumentException ("ResStatus Invalid value - " + ResStatus + " - Reference_ID=1200502 - LS - NR - NI - 1F - CO");		set_Value (COLUMNNAME_ResStatus, ResStatus);
	}

	/** Get Resuscitative Status.
		@return Resuscitative Status	  */
	public String getResStatus () 
	{
		return (String)get_Value(COLUMNNAME_ResStatus);
	}
}