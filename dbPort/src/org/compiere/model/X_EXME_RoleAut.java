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

/** Generated Model for EXME_RoleAut
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RoleAut extends PO implements I_EXME_RoleAut, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RoleAut (Properties ctx, int EXME_RoleAut_ID, String trxName)
    {
      super (ctx, EXME_RoleAut_ID, trxName);
      /** if (EXME_RoleAut_ID == 0)
        {
			setEXME_RoleAut_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RoleAut (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RoleAut[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Role getAD_Role() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Role.Table_Name);
        I_AD_Role result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Role)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Role_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 1) 
			set_Value (COLUMNNAME_AD_Role_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set First Supervisor.
		@param AD_Role_Supervisor1_ID First Supervisor	  */
	public void setAD_Role_Supervisor1_ID (int AD_Role_Supervisor1_ID)
	{
		if (AD_Role_Supervisor1_ID < 1) 
			set_Value (COLUMNNAME_AD_Role_Supervisor1_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_Supervisor1_ID, Integer.valueOf(AD_Role_Supervisor1_ID));
	}

	/** Get First Supervisor.
		@return First Supervisor	  */
	public int getAD_Role_Supervisor1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_Supervisor1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Supervisor.
		@param AD_Role_Supervisor2_ID Second Supervisor	  */
	public void setAD_Role_Supervisor2_ID (int AD_Role_Supervisor2_ID)
	{
		if (AD_Role_Supervisor2_ID < 1) 
			set_Value (COLUMNNAME_AD_Role_Supervisor2_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_Supervisor2_ID, Integer.valueOf(AD_Role_Supervisor2_ID));
	}

	/** Get Second Supervisor.
		@return Second Supervisor	  */
	public int getAD_Role_Supervisor2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_Supervisor2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Supervisor.
		@param AD_Role_Supervisor3_ID Third Supervisor	  */
	public void setAD_Role_Supervisor3_ID (int AD_Role_Supervisor3_ID)
	{
		if (AD_Role_Supervisor3_ID < 1) 
			set_Value (COLUMNNAME_AD_Role_Supervisor3_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_Supervisor3_ID, Integer.valueOf(AD_Role_Supervisor3_ID));
	}

	/** Get Third Supervisor.
		@return Third Supervisor	  */
	public int getAD_Role_Supervisor3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_Supervisor3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Authorization by Profile.
		@param EXME_RoleAut_ID Authorization by Profile	  */
	public void setEXME_RoleAut_ID (int EXME_RoleAut_ID)
	{
		if (EXME_RoleAut_ID < 1)
			 throw new IllegalArgumentException ("EXME_RoleAut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RoleAut_ID, Integer.valueOf(EXME_RoleAut_ID));
	}

	/** Get Authorization by Profile.
		@return Authorization by Profile	  */
	public int getEXME_RoleAut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RoleAut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
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
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}

	/** Set Amount.
		@param Importe Amount	  */
	public void setImporte (BigDecimal Importe)
	{
		set_Value (COLUMNNAME_Importe, Importe);
	}

	/** Get Amount.
		@return Amount	  */
	public BigDecimal getImporte () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Importe);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount to authorize.
		@param Importe1 Amount to authorize	  */
	public void setImporte1 (BigDecimal Importe1)
	{
		set_Value (COLUMNNAME_Importe1, Importe1);
	}

	/** Get Amount to authorize.
		@return Amount to authorize	  */
	public BigDecimal getImporte1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Importe1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount to authorize.
		@param Importe2 Amount to authorize	  */
	public void setImporte2 (BigDecimal Importe2)
	{
		set_Value (COLUMNNAME_Importe2, Importe2);
	}

	/** Get Amount to authorize.
		@return Amount to authorize	  */
	public BigDecimal getImporte2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Importe2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount to authorize.
		@param Importe3 Amount to authorize	  */
	public void setImporte3 (BigDecimal Importe3)
	{
		set_Value (COLUMNNAME_Importe3, Importe3);
	}

	/** Get Amount to authorize.
		@return Amount to authorize	  */
	public BigDecimal getImporte3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Importe3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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