/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_InterfaceSender
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha) - $Id$ */
public class X_EXME_InterfaceSender extends PO implements I_EXME_InterfaceSender, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InterfaceSender (Properties ctx, int EXME_InterfaceSender_ID, String trxName)
    {
      super (ctx, EXME_InterfaceSender_ID, trxName);
      /** if (EXME_InterfaceSender_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setClient (0);
			setEXME_EstServ_ID (0);
			setEXME_InterfaceSender_ID (0);
			setImprimir_CodZebra (false);
			setOrg (0);
			setORM_Param (null);
			setORM_ParamValue (null);
			setUsar_ImagenORM (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_InterfaceSender (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InterfaceSender[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Client.
		@param Client Client	  */
	public void setClient (int Client)
	{
		set_Value (COLUMNNAME_Client, Integer.valueOf(Client));
	}

	/** Get Client.
		@return Client	  */
	public int getClient () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Client);
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
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
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

	/** Set Iterfase Sender.
		@param EXME_InterfaceSender_ID 
		Iterfase Sender
	  */
	public void setEXME_InterfaceSender_ID (int EXME_InterfaceSender_ID)
	{
		if (EXME_InterfaceSender_ID < 1)
			 throw new IllegalArgumentException ("EXME_InterfaceSender_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InterfaceSender_ID, Integer.valueOf(EXME_InterfaceSender_ID));
	}

	/** Get Iterfase Sender.
		@return Iterfase Sender
	  */
	public int getEXME_InterfaceSender_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfaceSender_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ImagenORM_R.
		@param ImagenORM_R ImagenORM_R	  */
	public void setImagenORM_R (String ImagenORM_R)
	{
		set_Value (COLUMNNAME_ImagenORM_R, ImagenORM_R);
	}

	/** Get ImagenORM_R.
		@return ImagenORM_R	  */
	public String getImagenORM_R () 
	{
		return (String)get_Value(COLUMNNAME_ImagenORM_R);
	}

	/** Set Print Code Zebra.
		@param Imprimir_CodZebra Print Code Zebra	  */
	public void setImprimir_CodZebra (boolean Imprimir_CodZebra)
	{
		set_Value (COLUMNNAME_Imprimir_CodZebra, Boolean.valueOf(Imprimir_CodZebra));
	}

	/** Get Print Code Zebra.
		@return Print Code Zebra	  */
	public boolean isImprimir_CodZebra () 
	{
		Object oo = get_Value(COLUMNNAME_Imprimir_CodZebra);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Organization.
		@param Org Organization	  */
	public void setOrg (int Org)
	{
		set_Value (COLUMNNAME_Org, Integer.valueOf(Org));
	}

	/** Get Organization.
		@return Organization	  */
	public int getOrg () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Org);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Image Parameters ORM.
		@param ORM_Param Image Parameters ORM	  */
	public void setORM_Param (String ORM_Param)
	{
		if (ORM_Param == null)
			throw new IllegalArgumentException ("ORM_Param is mandatory.");
		set_Value (COLUMNNAME_ORM_Param, ORM_Param);
	}

	/** Get Image Parameters ORM.
		@return Image Parameters ORM	  */
	public String getORM_Param () 
	{
		return (String)get_Value(COLUMNNAME_ORM_Param);
	}

	/** Set Image Parameters Value ORM.
		@param ORM_ParamValue Image Parameters Value ORM	  */
	public void setORM_ParamValue (String ORM_ParamValue)
	{
		if (ORM_ParamValue == null)
			throw new IllegalArgumentException ("ORM_ParamValue is mandatory.");
		set_Value (COLUMNNAME_ORM_ParamValue, ORM_ParamValue);
	}

	/** Get Image Parameters Value ORM.
		@return Image Parameters Value ORM	  */
	public String getORM_ParamValue () 
	{
		return (String)get_Value(COLUMNNAME_ORM_ParamValue);
	}

	/** Set Use Image ORM.
		@param Usar_ImagenORM 
		Use Image ORM
	  */
	public void setUsar_ImagenORM (boolean Usar_ImagenORM)
	{
		set_Value (COLUMNNAME_Usar_ImagenORM, Boolean.valueOf(Usar_ImagenORM));
	}

	/** Get Use Image ORM.
		@return Use Image ORM
	  */
	public boolean isUsar_ImagenORM () 
	{
		Object oo = get_Value(COLUMNNAME_Usar_ImagenORM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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