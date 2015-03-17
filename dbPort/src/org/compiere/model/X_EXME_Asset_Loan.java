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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Asset_Loan
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Asset_Loan extends PO implements I_EXME_Asset_Loan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Asset_Loan (Properties ctx, int EXME_Asset_Loan_ID, String trxName)
    {
      super (ctx, EXME_Asset_Loan_ID, trxName);
      /** if (EXME_Asset_Loan_ID == 0)
        {
			setAD_User_ID (0);
// -1
			setCaptureDate (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setEXME_Asset_Loan_ID (0);
			setLoanDate (new Timestamp( System.currentTimeMillis() ));
// @#Date@
        } */
    }

    /** Load Constructor */
    public X_EXME_Asset_Loan (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Asset_Loan[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
    }

	/** Set Capture Date.
		@param CaptureDate 
		Is set to capture date of the loan application
	  */
	public void setCaptureDate (Timestamp CaptureDate)
	{
		if (CaptureDate == null)
			throw new IllegalArgumentException ("CaptureDate is mandatory.");
		set_Value (COLUMNNAME_CaptureDate, CaptureDate);
	}

	/** Get Capture Date.
		@return Is set to capture date of the loan application
	  */
	public Timestamp getCaptureDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CaptureDate);
	}

	/** Set Asset Loan.
		@param EXME_Asset_Loan_ID 
		Loan applications are recorded bibliographic material and periodicals library
	  */
	public void setEXME_Asset_Loan_ID (int EXME_Asset_Loan_ID)
	{
		if (EXME_Asset_Loan_ID < 1)
			 throw new IllegalArgumentException ("EXME_Asset_Loan_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Asset_Loan_ID, Integer.valueOf(EXME_Asset_Loan_ID));
	}

	/** Get Asset Loan.
		@return Loan applications are recorded bibliographic material and periodicals library
	  */
	public int getEXME_Asset_Loan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asset_Loan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Closed Status.
		@param IsClosed 
		The status is closed
	  */
	public void setIsClosed (boolean IsClosed)
	{
		set_Value (COLUMNNAME_IsClosed, Boolean.valueOf(IsClosed));
	}

	/** Get Closed Status.
		@return The status is closed
	  */
	public boolean isClosed () 
	{
		Object oo = get_Value(COLUMNNAME_IsClosed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Loan Date.
		@param LoanDate 
		Establishing a loan application date
	  */
	public void setLoanDate (Timestamp LoanDate)
	{
		if (LoanDate == null)
			throw new IllegalArgumentException ("LoanDate is mandatory.");
		set_Value (COLUMNNAME_LoanDate, LoanDate);
	}

	/** Get Loan Date.
		@return Establishing a loan application date
	  */
	public Timestamp getLoanDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LoanDate);
	}
}