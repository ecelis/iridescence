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

/** Generated Model for EXME_CtaPacAuth
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_CtaPacAuth extends PO implements I_EXME_CtaPacAuth, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacAuth (Properties ctx, int EXME_CtaPacAuth_ID, String trxName)
    {
      super (ctx, EXME_CtaPacAuth_ID, trxName);
      /** if (EXME_CtaPacAuth_ID == 0)
        {
			setAuthCode (null);
			setDOSThrough (new Timestamp( System.currentTimeMillis() ));
			setEXME_CtaPacAuth_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_PacienteAseg_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacAuth (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacAuth[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Authorization Code.
		@param AuthCode Authorization Code	  */
	public void setAuthCode (String AuthCode)
	{
		if (AuthCode == null)
			throw new IllegalArgumentException ("AuthCode is mandatory.");
		set_Value (COLUMNNAME_AuthCode, AuthCode);
	}

	/** Get Authorization Code.
		@return Authorization Code	  */
	public String getAuthCode () 
	{
		return (String)get_Value(COLUMNNAME_AuthCode);
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

	/** Set Date of Service Throug.
		@param DOSThrough Date of Service Throug	  */
	public void setDOSThrough (Timestamp DOSThrough)
	{
		if (DOSThrough == null)
			throw new IllegalArgumentException ("DOSThrough is mandatory.");
		set_Value (COLUMNNAME_DOSThrough, DOSThrough);
	}

	/** Get Date of Service Throug.
		@return Date of Service Throug	  */
	public Timestamp getDOSThrough () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DOSThrough);
	}

	/** Set Procedures Authorization.
		@param EXME_CtaPacAuth_ID Procedures Authorization	  */
	public void setEXME_CtaPacAuth_ID (int EXME_CtaPacAuth_ID)
	{
		if (EXME_CtaPacAuth_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacAuth_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacAuth_ID, Integer.valueOf(EXME_CtaPacAuth_ID));
	}

	/** Get Procedures Authorization.
		@return Procedures Authorization	  */
	public int getEXME_CtaPacAuth_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacAuth_ID);
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

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PacienteAseg getEXME_PacienteAseg() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PacienteAseg.Table_Name);
        I_EXME_PacienteAseg result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PacienteAseg)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PacienteAseg_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Insurance.
		@param EXME_PacienteAseg_ID Patient's Insurance	  */
	public void setEXME_PacienteAseg_ID (int EXME_PacienteAseg_ID)
	{
		if (EXME_PacienteAseg_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAseg_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PacienteAseg_ID, Integer.valueOf(EXME_PacienteAseg_ID));
	}

	/** Get Patient's Insurance.
		@return Patient's Insurance	  */
	public int getEXME_PacienteAseg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAseg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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