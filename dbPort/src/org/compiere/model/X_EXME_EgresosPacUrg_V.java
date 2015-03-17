/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_EgresosPacUrg_V
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EgresosPacUrg_V extends PO implements I_EXME_EgresosPacUrg_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EgresosPacUrg_V (Properties ctx, int EXME_EgresosPacUrg_V_ID, String trxName)
    {
      super (ctx, EXME_EgresosPacUrg_V_ID, trxName);
      /** if (EXME_EgresosPacUrg_V_ID == 0)
        {
			setDocumentNo (Env.ZERO);
			setEXME_CtaPac_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EgresosPacUrg_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EgresosPacUrg_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (BigDecimal DocumentNo)
	{
		throw new IllegalArgumentException ("DocumentNo is virtual column");	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public BigDecimal getDocumentNo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DocumentNo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
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

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}
}