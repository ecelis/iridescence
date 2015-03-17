/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_BatchClaimD
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_BatchClaimD extends PO implements I_EXME_BatchClaimD, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BatchClaimD (Properties ctx, int EXME_BatchClaimD_ID, String trxName)
    {
      super (ctx, EXME_BatchClaimD_ID, trxName);
      /** if (EXME_BatchClaimD_ID == 0)
        {
			setEXME_BatchClaimD_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_BatchClaimD (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BatchClaimD[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_BatchClaimD_ID.
		@param EXME_BatchClaimD_ID EXME_BatchClaimD_ID	  */
	public void setEXME_BatchClaimD_ID (int EXME_BatchClaimD_ID)
	{
		if (EXME_BatchClaimD_ID < 1)
			 throw new IllegalArgumentException ("EXME_BatchClaimD_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BatchClaimD_ID, Integer.valueOf(EXME_BatchClaimD_ID));
	}

	/** Get EXME_BatchClaimD_ID.
		@return EXME_BatchClaimD_ID	  */
	public int getEXME_BatchClaimD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BatchClaimD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_BatchClaimH getEXME_BatchClaimH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BatchClaimH.Table_Name);
        I_EXME_BatchClaimH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BatchClaimH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BatchClaimH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_BatchClaimH_ID.
		@param EXME_BatchClaimH_ID EXME_BatchClaimH_ID	  */
	public void setEXME_BatchClaimH_ID (int EXME_BatchClaimH_ID)
	{
		if (EXME_BatchClaimH_ID < 1) 
			set_Value (COLUMNNAME_EXME_BatchClaimH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_BatchClaimH_ID, Integer.valueOf(EXME_BatchClaimH_ID));
	}

	/** Get EXME_BatchClaimH_ID.
		@return EXME_BatchClaimH_ID	  */
	public int getEXME_BatchClaimH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BatchClaimH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPacExt.Table_Name);
        I_EXME_CtaPacExt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPacExt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPacExt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
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

	/** Status AD_Reference_ID=1200598 */
	public static final int STATUS_AD_Reference_ID=1200598;
	/** Accepted = A */
	public static final String STATUS_Accepted = "A";
	/** Accepted Errors = E */
	public static final String STATUS_AcceptedErrors = "E";
	/** Rejected MAC = M */
	public static final String STATUS_RejectedMAC = "M";
	/** Rejected = R */
	public static final String STATUS_Rejected = "R";
	/** Rejected Assurance = W */
	public static final String STATUS_RejectedAssurance = "W";
	/** Rejected Analyzed = X */
	public static final String STATUS_RejectedAnalyzed = "X";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("A") || Status.equals("E") || Status.equals("M") || Status.equals("R") || Status.equals("W") || Status.equals("X")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=1200598 - A - E - M - R - W - X");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}