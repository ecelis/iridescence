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

/** Generated Model for EXME_DiscontinueOrder
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DiscontinueOrder extends PO implements I_EXME_DiscontinueOrder, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DiscontinueOrder (Properties ctx, int EXME_DiscontinueOrder_ID, String trxName)
    {
      super (ctx, EXME_DiscontinueOrder_ID, trxName);
      /** if (EXME_DiscontinueOrder_ID == 0)
        {
			setAD_Table_ID (0);
			setDiscontinuedDate (new Timestamp( System.currentTimeMillis() ));
			setEXME_DiscontinueOrder_ID (0);
			setEXME_Medico_ID (0);
			setRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_DiscontinueOrder (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DiscontinueOrder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authenticated By.
		@param AuthenticatedBy Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy)
	{
		set_Value (COLUMNNAME_AuthenticatedBy, Integer.valueOf(AuthenticatedBy));
	}

	/** Get Authenticated By.
		@return Authenticated By	  */
	public int getAuthenticatedBy () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AuthenticatedBy);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authentication Date.
		@param Authenticated_Date Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date)
	{
		set_Value (COLUMNNAME_Authenticated_Date, Authenticated_Date);
	}

	/** Get Authentication Date.
		@return Authentication Date	  */
	public Timestamp getAuthenticated_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Authenticated_Date);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Discontinued Date.
		@param DiscontinuedDate Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate)
	{
		if (DiscontinuedDate == null)
			throw new IllegalArgumentException ("DiscontinuedDate is mandatory.");
		set_Value (COLUMNNAME_DiscontinuedDate, DiscontinuedDate);
	}

	/** Get Discontinued Date.
		@return Discontinued Date	  */
	public Timestamp getDiscontinuedDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DiscontinuedDate);
	}

	/** Set Discontinue Order.
		@param EXME_DiscontinueOrder_ID Discontinue Order	  */
	public void setEXME_DiscontinueOrder_ID (int EXME_DiscontinueOrder_ID)
	{
		if (EXME_DiscontinueOrder_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiscontinueOrder_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiscontinueOrder_ID, Integer.valueOf(EXME_DiscontinueOrder_ID));
	}

	/** Get Discontinue Order.
		@return Discontinue Order	  */
	public int getEXME_DiscontinueOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiscontinueOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** OrderType AD_Reference_ID=1200543 */
	public static final int ORDERTYPE_AD_Reference_ID=1200543;
	/** Verbal Order = VO */
	public static final String ORDERTYPE_VerbalOrder = "VO";
	/** Written Order = WO */
	public static final String ORDERTYPE_WrittenOrder = "WO";
	/** Telephone Order = TO */
	public static final String ORDERTYPE_TelephoneOrder = "TO";
	/** Set OrderType.
		@param OrderType OrderType	  */
	public void setOrderType (String OrderType)
	{

		if (OrderType == null || OrderType.equals("VO") || OrderType.equals("WO") || OrderType.equals("TO")); else throw new IllegalArgumentException ("OrderType Invalid value - " + OrderType + " - Reference_ID=1200543 - VO - WO - TO");		set_Value (COLUMNNAME_OrderType, OrderType);
	}

	/** Get OrderType.
		@return OrderType	  */
	public String getOrderType () 
	{
		return (String)get_Value(COLUMNNAME_OrderType);
	}

	/** ReadBack AD_Reference_ID=319 */
	public static final int READBACK_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String READBACK_Yes = "Y";
	/** No = N */
	public static final String READBACK_No = "N";
	/** Set Read Back.
		@param ReadBack 
		Read Back
	  */
	public void setReadBack (String ReadBack)
	{

		if (ReadBack == null || ReadBack.equals("Y") || ReadBack.equals("N")); else throw new IllegalArgumentException ("ReadBack Invalid value - " + ReadBack + " - Reference_ID=319 - Y - N");		set_Value (COLUMNNAME_ReadBack, ReadBack);
	}

	/** Get Read Back.
		@return Read Back
	  */
	public String getReadBack () 
	{
		return (String)get_Value(COLUMNNAME_ReadBack);
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			 throw new IllegalArgumentException ("Record_ID is mandatory.");
		set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}