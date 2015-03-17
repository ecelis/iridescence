/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_RssPac
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_RssPac extends PO implements I_PHR_RssPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_RssPac (Properties ctx, int PHR_RssPac_ID, String trxName)
    {
      super (ctx, PHR_RssPac_ID, trxName);
      /** if (PHR_RssPac_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setPHR_RssPac_ID (0);
			setRss (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_RssPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_RssPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
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

	/** Set Primary Key.
		@param PHR_RssPac_ID 
		Primary Key
	  */
	public void setPHR_RssPac_ID (int PHR_RssPac_ID)
	{
		if (PHR_RssPac_ID < 1)
			 throw new IllegalArgumentException ("PHR_RssPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_RssPac_ID, Integer.valueOf(PHR_RssPac_ID));
	}

	/** Get Primary Key.
		@return Primary Key
	  */
	public int getPHR_RssPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_RssPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Articles Address.
		@param Rss 
		Articles Address
	  */
	public void setRss (String Rss)
	{
		if (Rss == null)
			throw new IllegalArgumentException ("Rss is mandatory.");
		set_Value (COLUMNNAME_Rss, Rss);
	}

	/** Get Articles Address.
		@return Articles Address
	  */
	public String getRss () 
	{
		return (String)get_Value(COLUMNNAME_Rss);
	}
}