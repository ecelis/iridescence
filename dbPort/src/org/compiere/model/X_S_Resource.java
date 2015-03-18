/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for S_Resource
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_S_Resource extends PO implements I_S_Resource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_S_Resource (Properties ctx, int S_Resource_ID, String trxName)
    {
      super (ctx, S_Resource_ID, trxName);
      /** if (S_Resource_ID == 0)
        {
			setIsAvailable (true);
// Y
			setM_Warehouse_ID (0);
			setName (null);
			setPercentUtilization (Env.ZERO);
// 100
			setS_Resource_ID (0);
			setS_ResourceType_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_S_Resource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_S_Resource[")
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

	/** Set Chargeable Quantity.
		@param ChargeableQty Chargeable Quantity	  */
	public void setChargeableQty (BigDecimal ChargeableQty)
	{
		set_Value (COLUMNNAME_ChargeableQty, ChargeableQty);
	}

	/** Get Chargeable Quantity.
		@return Chargeable Quantity	  */
	public BigDecimal getChargeableQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeableQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Daily Capacity.
		@param DailyCapacity Daily Capacity	  */
	public void setDailyCapacity (BigDecimal DailyCapacity)
	{
		set_Value (COLUMNNAME_DailyCapacity, DailyCapacity);
	}

	/** Get Daily Capacity.
		@return Daily Capacity	  */
	public BigDecimal getDailyCapacity () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DailyCapacity);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Available.
		@param IsAvailable 
		Resource is available
	  */
	public void setIsAvailable (boolean IsAvailable)
	{
		set_Value (COLUMNNAME_IsAvailable, Boolean.valueOf(IsAvailable));
	}

	/** Get Available.
		@return Resource is available
	  */
	public boolean isAvailable () 
	{
		Object oo = get_Value(COLUMNNAME_IsAvailable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Manufacturing Resource.
		@param IsManufacturingResource Manufacturing Resource	  */
	public void setIsManufacturingResource (boolean IsManufacturingResource)
	{
		set_Value (COLUMNNAME_IsManufacturingResource, Boolean.valueOf(IsManufacturingResource));
	}

	/** Get Manufacturing Resource.
		@return Manufacturing Resource	  */
	public boolean isManufacturingResource () 
	{
		Object oo = get_Value(COLUMNNAME_IsManufacturingResource);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** ManufacturingResourceType AD_Reference_ID=1200278 */
	public static final int MANUFACTURINGRESOURCETYPE_AD_Reference_ID=1200278;
	/** Production Line = PL */
	public static final String MANUFACTURINGRESOURCETYPE_ProductionLine = "PL";
	/** Plant = PT */
	public static final String MANUFACTURINGRESOURCETYPE_Plant = "PT";
	/** Work Center = WC */
	public static final String MANUFACTURINGRESOURCETYPE_WorkCenter = "WC";
	/** Work Station = WS */
	public static final String MANUFACTURINGRESOURCETYPE_WorkStation = "WS";
	/** Set Manufacturing Resource Type.
		@param ManufacturingResourceType Manufacturing Resource Type	  */
	public void setManufacturingResourceType (String ManufacturingResourceType)
	{

		if (ManufacturingResourceType == null || ManufacturingResourceType.equals("PL") || ManufacturingResourceType.equals("PT") || ManufacturingResourceType.equals("WC") || ManufacturingResourceType.equals("WS")); else throw new IllegalArgumentException ("ManufacturingResourceType Invalid value - " + ManufacturingResourceType + " - Reference_ID=1200278 - PL - PT - WC - WS");		set_Value (COLUMNNAME_ManufacturingResourceType, ManufacturingResourceType);
	}

	/** Get Manufacturing Resource Type.
		@return Manufacturing Resource Type	  */
	public String getManufacturingResourceType () 
	{
		return (String)get_Value(COLUMNNAME_ManufacturingResourceType);
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	/** Set % Utilization.
		@param PercentUtilization % Utilization	  */
	public void setPercentUtilization (BigDecimal PercentUtilization)
	{
		if (PercentUtilization == null)
			throw new IllegalArgumentException ("PercentUtilization is mandatory.");
		set_Value (COLUMNNAME_PercentUtilization, PercentUtilization);
	}

	/** Get % Utilization.
		@return % Utilization	  */
	public BigDecimal getPercentUtilization () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PercentUtilization);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Planning Horizon.
		@param PlanningHorizon 
		The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.
	  */
	public void setPlanningHorizon (int PlanningHorizon)
	{
		set_Value (COLUMNNAME_PlanningHorizon, Integer.valueOf(PlanningHorizon));
	}

	/** Get Planning Horizon.
		@return The planning horizon is the amount of time (Days) an organisation will look into the future when preparing a strategic plan.
	  */
	public int getPlanningHorizon () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PlanningHorizon);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Queuing Time.
		@param QueuingTime Queuing Time	  */
	public void setQueuingTime (BigDecimal QueuingTime)
	{
		set_Value (COLUMNNAME_QueuingTime, QueuingTime);
	}

	/** Get Queuing Time.
		@return Queuing Time	  */
	public BigDecimal getQueuingTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QueuingTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1)
			 throw new IllegalArgumentException ("S_Resource_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_S_ResourceType getS_ResourceType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_S_ResourceType.Table_Name);
        I_S_ResourceType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_S_ResourceType)constructor.newInstance(new Object[] {getCtx(), new Integer(getS_ResourceType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Resource Type.
		@param S_ResourceType_ID Resource Type	  */
	public void setS_ResourceType_ID (int S_ResourceType_ID)
	{
		if (S_ResourceType_ID < 1)
			 throw new IllegalArgumentException ("S_ResourceType_ID is mandatory.");
		set_Value (COLUMNNAME_S_ResourceType_ID, Integer.valueOf(S_ResourceType_ID));
	}

	/** Get Resource Type.
		@return Resource Type	  */
	public int getS_ResourceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_ResourceType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Waiting Time.
		@param WaitingTime 
		Workflow Simulation Waiting time
	  */
	public void setWaitingTime (BigDecimal WaitingTime)
	{
		set_Value (COLUMNNAME_WaitingTime, WaitingTime);
	}

	/** Get Waiting Time.
		@return Workflow Simulation Waiting time
	  */
	public BigDecimal getWaitingTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WaitingTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}