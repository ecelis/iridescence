/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_VentaSuspendida
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_VentaSuspendida extends PO implements I_EXME_VentaSuspendida, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_VentaSuspendida (Properties ctx, int EXME_VentaSuspendida_ID, String trxName)
    {
      super (ctx, EXME_VentaSuspendida_ID, trxName);
      /** if (EXME_VentaSuspendida_ID == 0)
        {
			setEXME_Operador_ID (0);
			setEXME_VentaSuspendida_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_VentaSuspendida (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_VentaSuspendida[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Operador getEXME_Operador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Operador.Table_Name);
        I_EXME_Operador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Operador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Operador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Operator.
		@param EXME_Operador_ID 
		Operator
	  */
	public void setEXME_Operador_ID (int EXME_Operador_ID)
	{
		if (EXME_Operador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Operador_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Operador_ID, Integer.valueOf(EXME_Operador_ID));
	}

	/** Get Operator.
		@return Operator
	  */
	public int getEXME_Operador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Operador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RFC getEXME_RFC() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RFC.Table_Name);
        I_EXME_RFC result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RFC)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RFC_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set RFC.
		@param EXME_RFC_ID RFC	  */
	public void setEXME_RFC_ID (int EXME_RFC_ID)
	{
		if (EXME_RFC_ID < 1) 
			set_Value (COLUMNNAME_EXME_RFC_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RFC_ID, Integer.valueOf(EXME_RFC_ID));
	}

	/** Get RFC.
		@return RFC	  */
	public int getEXME_RFC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RFC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suspended Sale.
		@param EXME_VentaSuspendida_ID Suspended Sale	  */
	public void setEXME_VentaSuspendida_ID (int EXME_VentaSuspendida_ID)
	{
		if (EXME_VentaSuspendida_ID < 1)
			 throw new IllegalArgumentException ("EXME_VentaSuspendida_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_VentaSuspendida_ID, Integer.valueOf(EXME_VentaSuspendida_ID));
	}

	/** Get Suspended Sale.
		@return Suspended Sale	  */
	public int getEXME_VentaSuspendida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_VentaSuspendida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set List Price is Public.
		@param isListaPreciosPublico List Price is Public	  */
	public void setisListaPreciosPublico (boolean isListaPreciosPublico)
	{
		set_Value (COLUMNNAME_isListaPreciosPublico, Boolean.valueOf(isListaPreciosPublico));
	}

	/** Get List Price is Public.
		@return List Price is Public	  */
	public boolean isListaPreciosPublico () 
	{
		Object oo = get_Value(COLUMNNAME_isListaPreciosPublico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}