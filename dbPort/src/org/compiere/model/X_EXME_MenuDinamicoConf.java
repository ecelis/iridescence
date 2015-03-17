/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MenuDinamicoConf
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_MenuDinamicoConf extends PO implements I_EXME_MenuDinamicoConf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MenuDinamicoConf (Properties ctx, int EXME_MenuDinamicoConf_ID, String trxName)
    {
      super (ctx, EXME_MenuDinamicoConf_ID, trxName);
      /** if (EXME_MenuDinamicoConf_ID == 0)
        {
			setAD_Form_ID (0);
			setEXME_MenuDinamicoConf_ID (0);
			setEXME_Menu_Dinamico_ID (0);
			setVisible (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_MenuDinamicoConf (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_MenuDinamicoConf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Special Form.
		@param AD_Form_ID 
		Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID)
	{
		if (AD_Form_ID < 1)
			 throw new IllegalArgumentException ("AD_Form_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Form_ID, Integer.valueOf(AD_Form_ID));
	}

	/** Get Special Form.
		@return Special Form
	  */
	public int getAD_Form_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Form_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Graphic menu settings.
		@param EXME_MenuDinamicoConf_ID 
		Graphic menu settings
	  */
	public void setEXME_MenuDinamicoConf_ID (int EXME_MenuDinamicoConf_ID)
	{
		if (EXME_MenuDinamicoConf_ID < 1)
			 throw new IllegalArgumentException ("EXME_MenuDinamicoConf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MenuDinamicoConf_ID, Integer.valueOf(EXME_MenuDinamicoConf_ID));
	}

	/** Get Graphic menu settings.
		@return Graphic menu settings
	  */
	public int getEXME_MenuDinamicoConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MenuDinamicoConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Menu_Dinamico getEXME_Menu_Dinamico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Menu_Dinamico.Table_Name);
        I_EXME_Menu_Dinamico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Menu_Dinamico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Menu_Dinamico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dynamic Menu.
		@param EXME_Menu_Dinamico_ID Dynamic Menu	  */
	public void setEXME_Menu_Dinamico_ID (int EXME_Menu_Dinamico_ID)
	{
		if (EXME_Menu_Dinamico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Menu_Dinamico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Menu_Dinamico_ID, Integer.valueOf(EXME_Menu_Dinamico_ID));
	}

	/** Get Dynamic Menu.
		@return Dynamic Menu	  */
	public int getEXME_Menu_Dinamico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Menu_Dinamico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Visible.
		@param Visible Visible	  */
	public void setVisible (boolean Visible)
	{
		set_Value (COLUMNNAME_Visible, Boolean.valueOf(Visible));
	}

	/** Get Visible.
		@return Visible	  */
	public boolean isVisible () 
	{
		Object oo = get_Value(COLUMNNAME_Visible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}