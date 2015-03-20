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

/** Generated Model for EXME_TT_Provision
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TT_Provision extends PO implements I_EXME_TT_Provision, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TT_Provision (Properties ctx, int EXME_TT_Provision_ID, String trxName)
    {
      super (ctx, EXME_TT_Provision_ID, trxName);
      /** if (EXME_TT_Provision_ID == 0)
        {
			setAD_Session_ID (0);
			setC_Currency_ID (0);
			setCentro_Costo (null);
			setCliente (null);
			setCredito (Env.ZERO);
			setCta_Cont (null);
			setCta_Cont_ID (0);
			setDebito (Env.ZERO);
			setDocumentNo (null);
			setEXME_TT_Provision_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_TT_Provision (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TT_Provision[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Session getAD_Session() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Session.Table_Name);
        I_AD_Session result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Session)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Session_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Session.
		@param AD_Session_ID 
		User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID)
	{
		if (AD_Session_ID < 1)
			 throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Session_ID, Integer.valueOf(AD_Session_ID));
	}

	/** Get Session.
		@return User Session Online or Web
	  */
	public int getAD_Session_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Session_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Category.
		@param Categoria Category	  */
	public void setCategoria (String Categoria)
	{
		set_Value (COLUMNNAME_Categoria, Categoria);
	}

	/** Get Category.
		@return Category	  */
	public String getCategoria () 
	{
		return (String)get_Value(COLUMNNAME_Categoria);
	}

	public I_C_Currency getC_Currency() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost.
		@param Centro_Costo Cost	  */
	public void setCentro_Costo (String Centro_Costo)
	{
		if (Centro_Costo == null)
			throw new IllegalArgumentException ("Centro_Costo is mandatory.");
		set_Value (COLUMNNAME_Centro_Costo, Centro_Costo);
	}

	/** Get Cost.
		@return Cost	  */
	public String getCentro_Costo () 
	{
		return (String)get_Value(COLUMNNAME_Centro_Costo);
	}

	/** Set Customer.
		@param Cliente 
		Name of Customer
	  */
	public void setCliente (String Cliente)
	{
		if (Cliente == null)
			throw new IllegalArgumentException ("Cliente is mandatory.");
		set_Value (COLUMNNAME_Cliente, Cliente);
	}

	/** Get Customer.
		@return Name of Customer
	  */
	public String getCliente () 
	{
		return (String)get_Value(COLUMNNAME_Cliente);
	}

	/** Set Credit.
		@param Credito Credit	  */
	public void setCredito (BigDecimal Credito)
	{
		if (Credito == null)
			throw new IllegalArgumentException ("Credito is mandatory.");
		set_Value (COLUMNNAME_Credito, Credito);
	}

	/** Get Credit.
		@return Credit	  */
	public BigDecimal getCredito () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Credito);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cta_Cont.
		@param Cta_Cont Cta_Cont	  */
	public void setCta_Cont (String Cta_Cont)
	{
		if (Cta_Cont == null)
			throw new IllegalArgumentException ("Cta_Cont is mandatory.");
		set_Value (COLUMNNAME_Cta_Cont, Cta_Cont);
	}

	/** Get Cta_Cont.
		@return Cta_Cont	  */
	public String getCta_Cont () 
	{
		return (String)get_Value(COLUMNNAME_Cta_Cont);
	}

	/** Set Cta_Cont_ID.
		@param Cta_Cont_ID Cta_Cont_ID	  */
	public void setCta_Cont_ID (int Cta_Cont_ID)
	{
		if (Cta_Cont_ID < 1)
			 throw new IllegalArgumentException ("Cta_Cont_ID is mandatory.");
		set_Value (COLUMNNAME_Cta_Cont_ID, Integer.valueOf(Cta_Cont_ID));
	}

	/** Get Cta_Cont_ID.
		@return Cta_Cont_ID	  */
	public int getCta_Cont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cta_Cont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Debit.
		@param Debito Debit	  */
	public void setDebito (BigDecimal Debito)
	{
		if (Debito == null)
			throw new IllegalArgumentException ("Debito is mandatory.");
		set_Value (COLUMNNAME_Debito, Debito);
	}

	/** Get Debit.
		@return Debit	  */
	public BigDecimal getDebito () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Debito);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Provision.
		@param EXME_TT_Provision_ID Provision	  */
	public void setEXME_TT_Provision_ID (int EXME_TT_Provision_ID)
	{
		if (EXME_TT_Provision_ID < 1)
			 throw new IllegalArgumentException ("EXME_TT_Provision_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TT_Provision_ID, Integer.valueOf(EXME_TT_Provision_ID));
	}

	/** Get Provision.
		@return Provision	  */
	public int getEXME_TT_Provision_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TT_Provision_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}
}