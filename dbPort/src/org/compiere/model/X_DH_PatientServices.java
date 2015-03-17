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

/** Generated Model for DH_PatientServices
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_DH_PatientServices extends PO implements I_DH_PatientServices, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_DH_PatientServices (Properties ctx, int DH_PatientServices_ID, String trxName)
    {
      super (ctx, DH_PatientServices_ID, trxName);
      /** if (DH_PatientServices_ID == 0)
        {
			setDH_PatientServices_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DH_PatientServices (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DH_PatientServices[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Patient services / packages.
		@param DH_PatientServices_ID Patient services / packages	  */
	public void setDH_PatientServices_ID (int DH_PatientServices_ID)
	{
		if (DH_PatientServices_ID < 1)
			 throw new IllegalArgumentException ("DH_PatientServices_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DH_PatientServices_ID, Integer.valueOf(DH_PatientServices_ID));
	}

	/** Get Patient services / packages.
		@return Patient services / packages	  */
	public int getDH_PatientServices_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PatientServices_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_DH_PrePayment.Table_Name);
        I_DH_PrePayment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_DH_PrePayment)constructor.newInstance(new Object[] {getCtx(), new Integer(getDH_PrePayment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pre Payment.
		@param DH_PrePayment_ID Pre Payment	  */
	public void setDH_PrePayment_ID (int DH_PrePayment_ID)
	{
		if (DH_PrePayment_ID < 1) 
			set_Value (COLUMNNAME_DH_PrePayment_ID, null);
		else 
			set_Value (COLUMNNAME_DH_PrePayment_ID, Integer.valueOf(DH_PrePayment_ID));
	}

	/** Get Pre Payment.
		@return Pre Payment	  */
	public int getDH_PrePayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PrePayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
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

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PaqBase_Version.Table_Name);
        I_EXME_PaqBase_Version result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PaqBase_Version)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PaqBase_Version_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Package Version.
		@param EXME_PaqBase_Version_ID 
		Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID)
	{
		if (EXME_PaqBase_Version_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_ID, Integer.valueOf(EXME_PaqBase_Version_ID));
	}

	/** Get Package Version.
		@return Package Version
	  */
	public int getEXME_PaqBase_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Membership.
		@param Membership Membership	  */
	public void setMembership (String Membership)
	{
		set_Value (COLUMNNAME_Membership, Membership);
	}

	/** Get Membership.
		@return Membership	  */
	public String getMembership () 
	{
		return (String)get_Value(COLUMNNAME_Membership);
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
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
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

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (int QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, Integer.valueOf(QtyEntered));
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public int getQtyEntered () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QtyEntered);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}