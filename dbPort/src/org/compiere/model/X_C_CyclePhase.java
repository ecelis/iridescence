/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_CyclePhase
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_CyclePhase extends PO implements I_C_CyclePhase, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_CyclePhase (Properties ctx, int C_CyclePhase_ID, String trxName)
    {
      super (ctx, C_CyclePhase_ID, trxName);
      /** if (C_CyclePhase_ID == 0)
        {
			setC_CycleStep_ID (0);
			setC_Phase_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_CyclePhase (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_CyclePhase[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_CycleStep getC_CycleStep() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_CycleStep.Table_Name);
        I_C_CycleStep result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_CycleStep)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_CycleStep_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cycle Step.
		@param C_CycleStep_ID 
		The step for this Cycle
	  */
	public void setC_CycleStep_ID (int C_CycleStep_ID)
	{
		if (C_CycleStep_ID < 1)
			 throw new IllegalArgumentException ("C_CycleStep_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_CycleStep_ID, Integer.valueOf(C_CycleStep_ID));
	}

	/** Get Cycle Step.
		@return The step for this Cycle
	  */
	public int getC_CycleStep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CycleStep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Phase getC_Phase() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Phase.Table_Name);
        I_C_Phase result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Phase)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Phase_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Standard Phase.
		@param C_Phase_ID 
		Standard Phase of the Project Type
	  */
	public void setC_Phase_ID (int C_Phase_ID)
	{
		if (C_Phase_ID < 1)
			 throw new IllegalArgumentException ("C_Phase_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Phase_ID, Integer.valueOf(C_Phase_ID));
	}

	/** Get Standard Phase.
		@return Standard Phase of the Project Type
	  */
	public int getC_Phase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Phase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}