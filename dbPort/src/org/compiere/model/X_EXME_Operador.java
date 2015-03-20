/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Operador
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Operador extends PO implements I_EXME_Operador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Operador (Properties ctx, int EXME_Operador_ID, String trxName)
    {
      super (ctx, EXME_Operador_ID, trxName);
      /** if (EXME_Operador_ID == 0)
        {
			setAD_OrgTrx_ID (0);
			setAD_User_ID (0);
			setC_CashBook_ID (0);
			setEstatus_Caja (false);
// N
			setEXME_Operador_ID (0);
			setName (null);
			setTipo_Operador (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Operador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Operador[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgTrx_ID is mandatory.");
		set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Register.
		@param C_CashBook_ID 
		Cash Register for recording payments transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID)
	{
		if (C_CashBook_ID < 1)
			 throw new IllegalArgumentException ("C_CashBook_ID is mandatory.");
		set_Value (COLUMNNAME_C_CashBook_ID, Integer.valueOf(C_CashBook_ID));
	}

	/** Get Cash Register.
		@return Cash Register for recording payments transactions
	  */
	public int getC_CashBook_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CashBook_ID);
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

	/** Set Cash Book Status.
		@param Estatus_Caja 
		Cash Book Status
	  */
	public void setEstatus_Caja (boolean Estatus_Caja)
	{
		set_Value (COLUMNNAME_Estatus_Caja, Boolean.valueOf(Estatus_Caja));
	}

	/** Get Cash Book Status.
		@return Cash Book Status
	  */
	public boolean isEstatus_Caja () 
	{
		Object oo = get_Value(COLUMNNAME_Estatus_Caja);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Operator.
		@param EXME_Operador_ID 
		Operator
	  */
	public void setEXME_Operador_ID (int EXME_Operador_ID)
	{
		if (EXME_Operador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Operador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Operador_ID, Integer.valueOf(EXME_Operador_ID));
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

	/** Tipo_Operador AD_Reference_ID=1000089 */
	public static final int TIPO_OPERADOR_AD_Reference_ID=1000089;
	/** Invoicer = F */
	public static final String TIPO_OPERADOR_Invoicer = "F";
	/** Cashier = C */
	public static final String TIPO_OPERADOR_Cashier = "C";
	/** Both = A */
	public static final String TIPO_OPERADOR_Both = "A";
	/** Set Cashier Type.
		@param Tipo_Operador 
		Cashier Type ('I', 'C', 'B')
	  */
	public void setTipo_Operador (String Tipo_Operador)
	{
		if (Tipo_Operador == null) throw new IllegalArgumentException ("Tipo_Operador is mandatory");
		if (Tipo_Operador.equals("F") || Tipo_Operador.equals("C") || Tipo_Operador.equals("A")); else throw new IllegalArgumentException ("Tipo_Operador Invalid value - " + Tipo_Operador + " - Reference_ID=1000089 - F - C - A");		set_Value (COLUMNNAME_Tipo_Operador, Tipo_Operador);
	}

	/** Get Cashier Type.
		@return Cashier Type ('I', 'C', 'B')
	  */
	public String getTipo_Operador () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Operador);
	}
}