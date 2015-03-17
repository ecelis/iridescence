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

/** Generated Model for EXME_Asset_LoanDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Asset_LoanDet extends PO implements I_EXME_Asset_LoanDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Asset_LoanDet (Properties ctx, int EXME_Asset_LoanDet_ID, String trxName)
    {
      super (ctx, EXME_Asset_LoanDet_ID, trxName);
      /** if (EXME_Asset_LoanDet_ID == 0)
        {
			setA_Asset_ID (0);
			setEffectiveDate (new Timestamp( System.currentTimeMillis() ));
			setEXME_Asset_LoanDet_ID (0);
			setEXME_Asset_Loan_ID (0);
			setIsReturned (false);
			setIsStrayed (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Asset_LoanDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Asset_LoanDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_A_Asset getA_Asset() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset.Table_Name);
        I_A_Asset result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
		set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Astray Date.
		@param AstrayDate 
		Set the date on which the borrowed material was lost
	  */
	public void setAstrayDate (Timestamp AstrayDate)
	{
		set_Value (COLUMNNAME_AstrayDate, AstrayDate);
	}

	/** Get Astray Date.
		@return Set the date on which the borrowed material was lost
	  */
	public Timestamp getAstrayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AstrayDate);
	}

	/** Set Commentary Loan.
		@param CommentaryLoan 
		It provides a commentary regarding the loan
	  */
	public void setCommentaryLoan (String CommentaryLoan)
	{
		set_Value (COLUMNNAME_CommentaryLoan, CommentaryLoan);
	}

	/** Get Commentary Loan.
		@return It provides a commentary regarding the loan
	  */
	public String getCommentaryLoan () 
	{
		return (String)get_Value(COLUMNNAME_CommentaryLoan);
	}

	/** Set Commentary Returned.
		@param CommentaryReturned 
		It provides a commentary to make the return for
	  */
	public void setCommentaryReturned (String CommentaryReturned)
	{
		set_Value (COLUMNNAME_CommentaryReturned, CommentaryReturned);
	}

	/** Get Commentary Returned.
		@return It provides a commentary to make the return for
	  */
	public String getCommentaryReturned () 
	{
		return (String)get_Value(COLUMNNAME_CommentaryReturned);
	}

	/** Set Effective Date.
		@param EffectiveDate 
		It establishes the effective date of the loan
	  */
	public void setEffectiveDate (Timestamp EffectiveDate)
	{
		if (EffectiveDate == null)
			throw new IllegalArgumentException ("EffectiveDate is mandatory.");
		set_Value (COLUMNNAME_EffectiveDate, EffectiveDate);
	}

	/** Get Effective Date.
		@return It establishes the effective date of the loan
	  */
	public Timestamp getEffectiveDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EffectiveDate);
	}

	/** Set Asset Loan detail.
		@param EXME_Asset_LoanDet_ID 
		Contain details of the request for material made
	  */
	public void setEXME_Asset_LoanDet_ID (int EXME_Asset_LoanDet_ID)
	{
		if (EXME_Asset_LoanDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_Asset_LoanDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Asset_LoanDet_ID, Integer.valueOf(EXME_Asset_LoanDet_ID));
	}

	/** Get Asset Loan detail.
		@return Contain details of the request for material made
	  */
	public int getEXME_Asset_LoanDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asset_LoanDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Asset_Loan getEXME_Asset_Loan() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Asset_Loan.Table_Name);
        I_EXME_Asset_Loan result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Asset_Loan)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Asset_Loan_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Returned.
		@param IsReturned Returned	  */
	public void setIsReturned (boolean IsReturned)
	{
		set_Value (COLUMNNAME_IsReturned, Boolean.valueOf(IsReturned));
	}

	/** Get Returned.
		@return Returned	  */
	public boolean isReturned () 
	{
		Object oo = get_Value(COLUMNNAME_IsReturned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Strayed.
		@param IsStrayed 
		Defines whether the material reported missing
	  */
	public void setIsStrayed (boolean IsStrayed)
	{
		set_Value (COLUMNNAME_IsStrayed, Boolean.valueOf(IsStrayed));
	}

	/** Get Strayed.
		@return Defines whether the material reported missing
	  */
	public boolean isStrayed () 
	{
		Object oo = get_Value(COLUMNNAME_IsStrayed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Return Date.
		@param ReturnDate 
		It sets a return date when material is returned
	  */
	public void setReturnDate (Timestamp ReturnDate)
	{
		set_Value (COLUMNNAME_ReturnDate, ReturnDate);
	}

	/** Get Return Date.
		@return It sets a return date when material is returned
	  */
	public Timestamp getReturnDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ReturnDate);
	}
}