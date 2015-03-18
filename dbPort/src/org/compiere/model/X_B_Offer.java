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

/** Generated Model for B_Offer
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_B_Offer extends PO implements I_B_Offer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_B_Offer (Properties ctx, int B_Offer_ID, String trxName)
    {
      super (ctx, B_Offer_ID, trxName);
      /** if (B_Offer_ID == 0)
        {
			setAD_User_ID (0);
			setB_Offer_ID (0);
			setB_SellerFunds_ID (0);
			setB_Topic_ID (0);
			setIsWillingToCommit (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_B_Offer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_B_Offer[")
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

	/** Set Offer.
		@param B_Offer_ID 
		Offer for a Topic
	  */
	public void setB_Offer_ID (int B_Offer_ID)
	{
		if (B_Offer_ID < 1)
			 throw new IllegalArgumentException ("B_Offer_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_B_Offer_ID, Integer.valueOf(B_Offer_ID));
	}

	/** Get Offer.
		@return Offer for a Topic
	  */
	public int getB_Offer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Offer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_B_SellerFunds getB_SellerFunds() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_B_SellerFunds.Table_Name);
        I_B_SellerFunds result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_B_SellerFunds)constructor.newInstance(new Object[] {getCtx(), new Integer(getB_SellerFunds_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Seller Funds.
		@param B_SellerFunds_ID 
		Seller Funds from Offers on Topics
	  */
	public void setB_SellerFunds_ID (int B_SellerFunds_ID)
	{
		if (B_SellerFunds_ID < 1)
			 throw new IllegalArgumentException ("B_SellerFunds_ID is mandatory.");
		set_Value (COLUMNNAME_B_SellerFunds_ID, Integer.valueOf(B_SellerFunds_ID));
	}

	/** Get Seller Funds.
		@return Seller Funds from Offers on Topics
	  */
	public int getB_SellerFunds_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_SellerFunds_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_B_Topic getB_Topic() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_B_Topic.Table_Name);
        I_B_Topic result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_B_Topic)constructor.newInstance(new Object[] {getCtx(), new Integer(getB_Topic_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Topic.
		@param B_Topic_ID 
		Auction Topic
	  */
	public void setB_Topic_ID (int B_Topic_ID)
	{
		if (B_Topic_ID < 1)
			 throw new IllegalArgumentException ("B_Topic_ID is mandatory.");
		set_Value (COLUMNNAME_B_Topic_ID, Integer.valueOf(B_Topic_ID));
	}

	/** Get Topic.
		@return Auction Topic
	  */
	public int getB_Topic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Topic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Willing to commit.
		@param IsWillingToCommit Willing to commit	  */
	public void setIsWillingToCommit (boolean IsWillingToCommit)
	{
		set_Value (COLUMNNAME_IsWillingToCommit, Boolean.valueOf(IsWillingToCommit));
	}

	/** Get Willing to commit.
		@return Willing to commit	  */
	public boolean isWillingToCommit () 
	{
		Object oo = get_Value(COLUMNNAME_IsWillingToCommit);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Private Note.
		@param PrivateNote 
		Private Note - not visible to the other parties
	  */
	public void setPrivateNote (String PrivateNote)
	{
		set_Value (COLUMNNAME_PrivateNote, PrivateNote);
	}

	/** Get Private Note.
		@return Private Note - not visible to the other parties
	  */
	public String getPrivateNote () 
	{
		return (String)get_Value(COLUMNNAME_PrivateNote);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}
}