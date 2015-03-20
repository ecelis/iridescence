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

/** Generated Model for EXME_PrescDietaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PrescDietaDet extends PO implements I_EXME_PrescDietaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PrescDietaDet (Properties ctx, int EXME_PrescDietaDet_ID, String trxName)
    {
      super (ctx, EXME_PrescDietaDet_ID, trxName);
      /** if (EXME_PrescDietaDet_ID == 0)
        {
			setEXME_PrescDietaDet_ID (0);
			setEXME_PrescDieta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PrescDietaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PrescDietaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Dieta getEXME_Dieta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Dieta.Table_Name);
        I_EXME_Dieta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Dieta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Dieta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diet.
		@param EXME_Dieta_ID Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID)
	{
		if (EXME_Dieta_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dieta_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dieta_ID, Integer.valueOf(EXME_Dieta_ID));
	}

	/** Get Diet.
		@return Diet	  */
	public int getEXME_Dieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dietetics Prescription Details.
		@param EXME_PrescDietaDet_ID 
		Dietetics Prescription Details
	  */
	public void setEXME_PrescDietaDet_ID (int EXME_PrescDietaDet_ID)
	{
		if (EXME_PrescDietaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PrescDietaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PrescDietaDet_ID, Integer.valueOf(EXME_PrescDietaDet_ID));
	}

	/** Get Dietetics Prescription Details.
		@return Dietetics Prescription Details
	  */
	public int getEXME_PrescDietaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PrescDietaDet_ID);
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

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}