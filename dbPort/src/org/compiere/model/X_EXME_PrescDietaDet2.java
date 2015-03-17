/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PrescDietaDet2
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PrescDietaDet2 extends PO implements I_EXME_PrescDietaDet2, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescDietaDet2 (Properties ctx, int EXME_PrescDietaDet2_ID, String trxName)
    {
      super (ctx, EXME_PrescDietaDet2_ID, trxName);
      /** if (EXME_PrescDietaDet2_ID == 0)
        {
			setEXME_ConfigDieta_Det_ID (0);
			setEXME_ConfigDieta_ID (0);
			setEXME_PrescDietaDet2_ID (0);
			setEXME_PrescDieta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescDietaDet2 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescDietaDet2[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_ConfigDieta_Det getEXME_ConfigDieta_Det() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConfigDieta_Det.Table_Name);
        I_EXME_ConfigDieta_Det result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConfigDieta_Det)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConfigDieta_Det_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Configuration Diet Detail.
		@param EXME_ConfigDieta_Det_ID 
		Configuration Diet Detail
	  */
	public void setEXME_ConfigDieta_Det_ID (int EXME_ConfigDieta_Det_ID)
	{
		if (EXME_ConfigDieta_Det_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDieta_Det_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConfigDieta_Det_ID, Integer.valueOf(EXME_ConfigDieta_Det_ID));
	}

	/** Get Configuration Diet Detail.
		@return Configuration Diet Detail
	  */
	public int getEXME_ConfigDieta_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDieta_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ConfigDieta getEXME_ConfigDieta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConfigDieta.Table_Name);
        I_EXME_ConfigDieta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConfigDieta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConfigDieta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diet Configuration.
		@param EXME_ConfigDieta_ID Diet Configuration	  */
	public void setEXME_ConfigDieta_ID (int EXME_ConfigDieta_ID)
	{
		if (EXME_ConfigDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDieta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ConfigDieta_ID, Integer.valueOf(EXME_ConfigDieta_ID));
	}

	/** Get Diet Configuration.
		@return Diet Configuration	  */
	public int getEXME_ConfigDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDieta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dietetics Prescription Details.
		@param EXME_PrescDietaDet2_ID Dietetics Prescription Details	  */
	public void setEXME_PrescDietaDet2_ID (int EXME_PrescDietaDet2_ID)
	{
		if (EXME_PrescDietaDet2_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescDietaDet2_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescDietaDet2_ID, Integer.valueOf(EXME_PrescDietaDet2_ID));
	}

	/** Get Dietetics Prescription Details.
		@return Dietetics Prescription Details	  */
	public int getEXME_PrescDietaDet2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescDietaDet2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PrescDieta getEXME_PrescDieta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PrescDieta.Table_Name);
        I_EXME_PrescDieta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PrescDieta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PrescDieta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dietetics Prescription.
		@param EXME_PrescDieta_ID 
		Dietetics Prescription
	  */
	public void setEXME_PrescDieta_ID (int EXME_PrescDieta_ID)
	{
		if (EXME_PrescDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescDieta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PrescDieta_ID, Integer.valueOf(EXME_PrescDieta_ID));
	}

	/** Get Dietetics Prescription.
		@return Dietetics Prescription
	  */
	public int getEXME_PrescDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescDieta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}