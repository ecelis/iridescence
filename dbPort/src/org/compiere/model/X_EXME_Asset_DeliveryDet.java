/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Asset_DeliveryDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Asset_DeliveryDet extends PO implements I_EXME_Asset_DeliveryDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Asset_DeliveryDet (Properties ctx, int EXME_Asset_DeliveryDet_ID, String trxName)
    {
      super (ctx, EXME_Asset_DeliveryDet_ID, trxName);
      /** if (EXME_Asset_DeliveryDet_ID == 0)
        {
			setA_Asset_ID (0);
			setEXME_Asset_DeliveryDet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Asset_DeliveryDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Asset_DeliveryDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_A_Asset_Delivery getA_Asset_Delivery() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset_Delivery.Table_Name);
        I_A_Asset_Delivery result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset_Delivery)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_Delivery_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Asset Delivery.
		@param A_Asset_Delivery_ID 
		Delivery of Asset
	  */
	public void setA_Asset_Delivery_ID (int A_Asset_Delivery_ID)
	{
		if (A_Asset_Delivery_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Delivery_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Delivery_ID, Integer.valueOf(A_Asset_Delivery_ID));
	}

	/** Get Asset Delivery.
		@return Delivery of Asset
	  */
	public int getA_Asset_Delivery_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Delivery_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Delivery Detail.
		@param EXME_Asset_DeliveryDet_ID 
		Identifier of the detail of delivery
	  */
	public void setEXME_Asset_DeliveryDet_ID (int EXME_Asset_DeliveryDet_ID)
	{
		if (EXME_Asset_DeliveryDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_Asset_DeliveryDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Asset_DeliveryDet_ID, Integer.valueOf(EXME_Asset_DeliveryDet_ID));
	}

	/** Get Delivery Detail.
		@return Identifier of the detail of delivery
	  */
	public int getEXME_Asset_DeliveryDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asset_DeliveryDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Transfered.
		@param IsTransfered 
		Sets whether this transfer registration 
	  */
	public void setIsTransfered (boolean IsTransfered)
	{
		set_Value (COLUMNNAME_IsTransfered, Boolean.valueOf(IsTransfered));
	}

	/** Get Is Transfered.
		@return Sets whether this transfer registration 
	  */
	public boolean isTransfered () 
	{
		Object oo = get_Value(COLUMNNAME_IsTransfered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Control Number.
		@param NumeroControl 
		Contains the number of active contro
	  */
	public void setNumeroControl (String NumeroControl)
	{
		throw new IllegalArgumentException ("NumeroControl is virtual column");	}

	/** Get Control Number.
		@return Contains the number of active contro
	  */
	public String getNumeroControl () 
	{
		return (String)get_Value(COLUMNNAME_NumeroControl);
	}
}