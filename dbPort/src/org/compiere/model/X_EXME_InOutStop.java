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

/** Generated Model for EXME_InOutStop
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_InOutStop extends PO implements I_EXME_InOutStop, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InOutStop (Properties ctx, int EXME_InOutStop_ID, String trxName)
    {
      super (ctx, EXME_InOutStop_ID, trxName);
      /** if (EXME_InOutStop_ID == 0)
        {
			setAD_Table_ID (0);
			setAD_User_ID (0);
			setEXME_InOutStop_ID (0);
			setMovementDate (new Timestamp( System.currentTimeMillis() ));
			setM_Product_ID (0);
			setM_Warehouse_ID (0);
			setM_Warehouse_Sol_ID (0);
			setQtyDose (Env.ZERO);
			setQtyOrdered (Env.ZERO);
			setRecord_ID (0);
			setStatus (null);
			setTargetQty (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_InOutStop (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InOutStop[")
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

	public I_C_UOM_Conversion getC_UOM_Conversion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_UOM_Conversion.Table_Name);
        I_C_UOM_Conversion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_UOM_Conversion)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_UOM_Conversion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set UOM Conversion.
		@param C_UOM_Conversion_ID 
		Unit of Measure Conversion
	  */
	public void setC_UOM_Conversion_ID (int C_UOM_Conversion_ID)
	{
		if (C_UOM_Conversion_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Conversion_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Conversion_ID, Integer.valueOf(C_UOM_Conversion_ID));
	}

	/** Get UOM Conversion.
		@return Unit of Measure Conversion
	  */
	public int getC_UOM_Conversion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Conversion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set in out stop.
		@param EXME_InOutStop_ID in out stop	  */
	public void setEXME_InOutStop_ID (int EXME_InOutStop_ID)
	{
		if (EXME_InOutStop_ID < 1)
			 throw new IllegalArgumentException ("EXME_InOutStop_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InOutStop_ID, Integer.valueOf(EXME_InOutStop_ID));
	}

	/** Get in out stop.
		@return in out stop	  */
	public int getEXME_InOutStop_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InOutStop_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lot No.
		@param Lot 
		Lot number (alphanumeric)
	  */
	public void setLot (String Lot)
	{
		set_Value (COLUMNNAME_Lot, Lot);
	}

	/** Get Lot No.
		@return Lot number (alphanumeric)
	  */
	public String getLot () 
	{
		return (String)get_Value(COLUMNNAME_Lot);
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		if (MovementDate == null)
			throw new IllegalArgumentException ("MovementDate is mandatory.");
		set_Value (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
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

	/** Set Requesting Warehouse.
		@param M_Warehouse_Sol_ID 
		Requesting Warehouse
	  */
	public void setM_Warehouse_Sol_ID (int M_Warehouse_Sol_ID)
	{
		if (M_Warehouse_Sol_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_Sol_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_Sol_ID, Integer.valueOf(M_Warehouse_Sol_ID));
	}

	/** Get Requesting Warehouse.
		@return Requesting Warehouse
	  */
	public int getM_Warehouse_Sol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_Sol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Qty dose.
		@param QtyDose Qty dose	  */
	public void setQtyDose (BigDecimal QtyDose)
	{
		if (QtyDose == null)
			throw new IllegalArgumentException ("QtyDose is mandatory.");
		set_Value (COLUMNNAME_QtyDose, QtyDose);
	}

	/** Get Qty dose.
		@return Qty dose	  */
	public BigDecimal getQtyDose () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyDose);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		if (QtyOrdered == null)
			throw new IllegalArgumentException ("QtyOrdered is mandatory.");
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Status AD_Reference_ID=131 */
	public static final int STATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String STATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** Approved = AP */
	public static final String STATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String STATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String STATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String STATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String STATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String STATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String STATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String STATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String STATUS_WaitingConfirmation = "WC";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null) throw new IllegalArgumentException ("Status is mandatory");
		if (Status.equals("DR") || Status.equals("CO") || Status.equals("AP") || Status.equals("NA") || Status.equals("VO") || Status.equals("IN") || Status.equals("RE") || Status.equals("CL") || Status.equals("??") || Status.equals("IP") || Status.equals("WP") || Status.equals("WC")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Target Quantity.
		@param TargetQty 
		Target Movement Quantity
	  */
	public void setTargetQty (BigDecimal TargetQty)
	{
		if (TargetQty == null)
			throw new IllegalArgumentException ("TargetQty is mandatory.");
		set_Value (COLUMNNAME_TargetQty, TargetQty);
	}

	/** Get Target Quantity.
		@return Target Movement Quantity
	  */
	public BigDecimal getTargetQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TargetQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}