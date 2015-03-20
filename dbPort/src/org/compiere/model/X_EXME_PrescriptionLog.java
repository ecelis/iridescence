/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PrescriptionLog
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PrescriptionLog extends PO implements I_EXME_PrescriptionLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescriptionLog (Properties ctx, int EXME_PrescriptionLog_ID, String trxName)
    {
      super (ctx, EXME_PrescriptionLog_ID, trxName);
      /** if (EXME_PrescriptionLog_ID == 0)
        {
			setAD_Table_ID (0);
			setEstatus (false);
			setEXME_CtaPac_ID (0);
			setEXME_PrescriptionLog_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescriptionLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescriptionLog[")
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

	/** Set Comment.
		@param Comentario Comment	  */
	public void setComentario (String Comentario)
	{
		set_Value (COLUMNNAME_Comentario, Comentario);
	}

	/** Get Comment.
		@return Comment	  */
	public String getComentario () 
	{
		return (String)get_Value(COLUMNNAME_Comentario);
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (boolean Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Boolean.valueOf(Estatus));
	}

	/** Get Status.
		@return Status
	  */
	public boolean isEstatus () 
	{
		Object oo = get_Value(COLUMNNAME_Estatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Account Without Medication.
		@param EXME_PrescriptionLog_ID 
		Patient Account Without Medication
	  */
	public void setEXME_PrescriptionLog_ID (int EXME_PrescriptionLog_ID)
	{
		if (EXME_PrescriptionLog_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescriptionLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescriptionLog_ID, Integer.valueOf(EXME_PrescriptionLog_ID));
	}

	/** Get Patient Account Without Medication.
		@return Patient Account Without Medication
	  */
	public int getEXME_PrescriptionLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescriptionLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}