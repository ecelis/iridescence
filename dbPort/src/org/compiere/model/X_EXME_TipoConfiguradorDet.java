/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TipoConfiguradorDet
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_TipoConfiguradorDet extends PO implements I_EXME_TipoConfiguradorDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoConfiguradorDet (Properties ctx, int EXME_TipoConfiguradorDet_ID, String trxName)
    {
      super (ctx, EXME_TipoConfiguradorDet_ID, trxName);
      /** if (EXME_TipoConfiguradorDet_ID == 0)
        {
			setEXME_Configurador_ID (0);
			setEXME_TipoConfiguradorDet_ID (0);
			setEXME_TipoConfigurador_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoConfiguradorDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoConfiguradorDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Configurador getEXME_Configurador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Configurador.Table_Name);
        I_EXME_Configurador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Configurador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Configurador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Configurator.
		@param EXME_Configurador_ID Configurator	  */
	public void setEXME_Configurador_ID (int EXME_Configurador_ID)
	{
		if (EXME_Configurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Configurador_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Configurador_ID, Integer.valueOf(EXME_Configurador_ID));
	}

	/** Get Configurator.
		@return Configurator	  */
	public int getEXME_Configurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Configurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Configuration Type Detail.
		@param EXME_TipoConfiguradorDet_ID Configuration Type Detail	  */
	public void setEXME_TipoConfiguradorDet_ID (int EXME_TipoConfiguradorDet_ID)
	{
		if (EXME_TipoConfiguradorDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoConfiguradorDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoConfiguradorDet_ID, Integer.valueOf(EXME_TipoConfiguradorDet_ID));
	}

	/** Get Configuration Type Detail.
		@return Configuration Type Detail	  */
	public int getEXME_TipoConfiguradorDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoConfiguradorDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoConfigurador getEXME_TipoConfigurador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoConfigurador.Table_Name);
        I_EXME_TipoConfigurador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoConfigurador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoConfigurador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Configuration Type.
		@param EXME_TipoConfigurador_ID Configuration Type	  */
	public void setEXME_TipoConfigurador_ID (int EXME_TipoConfigurador_ID)
	{
		if (EXME_TipoConfigurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoConfigurador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoConfigurador_ID, Integer.valueOf(EXME_TipoConfigurador_ID));
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public int getEXME_TipoConfigurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoConfigurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}