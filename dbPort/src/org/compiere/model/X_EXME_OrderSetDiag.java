/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_OrderSetDiag
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_OrderSetDiag extends PO implements I_EXME_OrderSetDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_OrderSetDiag (Properties ctx, int EXME_OrderSetDiag_ID, String trxName)
    {
      super (ctx, EXME_OrderSetDiag_ID, trxName);
      /** if (EXME_OrderSetDiag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_OrderSetDiag_ID (0);
			setEXME_OrderSet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_OrderSetDiag (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_OrderSetDiag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Set Diagnosis.
		@param EXME_OrderSetDiag_ID Order Set Diagnosis	  */
	public void setEXME_OrderSetDiag_ID (int EXME_OrderSetDiag_ID)
	{
		if (EXME_OrderSetDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_OrderSetDiag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_OrderSetDiag_ID, Integer.valueOf(EXME_OrderSetDiag_ID));
	}

	/** Get Order Set Diagnosis.
		@return Order Set Diagnosis	  */
	public int getEXME_OrderSetDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSetDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderSet.Table_Name);
        I_EXME_OrderSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1)
			 throw new IllegalArgumentException ("EXME_OrderSet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}