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

/** Generated Model for B_TopicType
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_B_TopicType extends PO implements I_B_TopicType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_B_TopicType (Properties ctx, int B_TopicType_ID, String trxName)
    {
      super (ctx, B_TopicType_ID, trxName);
      /** if (B_TopicType_ID == 0)
        {
			setAuctionType (null);
			setB_TopicType_ID (0);
			setM_PriceList_ID (0);
			setM_Product_ID (0);
			setM_ProductMember_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_B_TopicType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_B_TopicType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Auction Type.
		@param AuctionType Auction Type	  */
	public void setAuctionType (String AuctionType)
	{
		if (AuctionType == null)
			throw new IllegalArgumentException ("AuctionType is mandatory.");
		set_Value (COLUMNNAME_AuctionType, AuctionType);
	}

	/** Get Auction Type.
		@return Auction Type	  */
	public String getAuctionType () 
	{
		return (String)get_Value(COLUMNNAME_AuctionType);
	}

	/** Set Topic Type.
		@param B_TopicType_ID 
		Auction Topic Type
	  */
	public void setB_TopicType_ID (int B_TopicType_ID)
	{
		if (B_TopicType_ID < 1)
			 throw new IllegalArgumentException ("B_TopicType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_B_TopicType_ID, Integer.valueOf(B_TopicType_ID));
	}

	/** Get Topic Type.
		@return Auction Topic Type
	  */
	public int getB_TopicType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_TopicType_ID);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	public I_M_PriceList getM_PriceList() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList.Table_Name);
        I_M_PriceList result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Membership.
		@param M_ProductMember_ID 
		Product used to deternine the price of the membership for the topic type
	  */
	public void setM_ProductMember_ID (int M_ProductMember_ID)
	{
		if (M_ProductMember_ID < 1)
			 throw new IllegalArgumentException ("M_ProductMember_ID is mandatory.");
		set_Value (COLUMNNAME_M_ProductMember_ID, Integer.valueOf(M_ProductMember_ID));
	}

	/** Get Membership.
		@return Product used to deternine the price of the membership for the topic type
	  */
	public int getM_ProductMember_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductMember_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}