/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Quirofano_Acct
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Quirofano_Acct extends PO implements I_EXME_Quirofano_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Quirofano_Acct (Properties ctx, int EXME_Quirofano_Acct_ID, String trxName)
    {
      super (ctx, EXME_Quirofano_Acct_ID, trxName);
      /** if (EXME_Quirofano_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setEXME_Quirofano_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Quirofano_Acct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Quirofano_Acct[")
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
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Quirofano.Table_Name);
        I_EXME_Quirofano result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Quirofano)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Quirofano_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1)
			 throw new IllegalArgumentException ("EXME_Quirofano_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Quirofano_ID, Integer.valueOf(EXME_Quirofano_ID));
	}

	/** Get Surgery Room.
		@return Surgey Room
	  */
	public int getEXME_Quirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Quirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cleaning Account.
		@param Q_LimpReal_Acct 
		Cleaning Account
	  */
	public void setQ_LimpReal_Acct (int Q_LimpReal_Acct)
	{
		set_Value (COLUMNNAME_Q_LimpReal_Acct, Integer.valueOf(Q_LimpReal_Acct));
	}

	/** Get Cleaning Account.
		@return Cleaning Account
	  */
	public int getQ_LimpReal_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Q_LimpReal_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Preparation Account.
		@param Q_PrepReal_Acct 
		Preparation account
	  */
	public void setQ_PrepReal_Acct (int Q_PrepReal_Acct)
	{
		set_Value (COLUMNNAME_Q_PrepReal_Acct, Integer.valueOf(Q_PrepReal_Acct));
	}

	/** Get Preparation Account.
		@return Preparation account
	  */
	public int getQ_PrepReal_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Q_PrepReal_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use Account.
		@param Q_UsoReal_Acct 
		use Account
	  */
	public void setQ_UsoReal_Acct (int Q_UsoReal_Acct)
	{
		set_Value (COLUMNNAME_Q_UsoReal_Acct, Integer.valueOf(Q_UsoReal_Acct));
	}

	/** Get Use Account.
		@return use Account
	  */
	public int getQ_UsoReal_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Q_UsoReal_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}