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

/** Generated Model for C_CycleStep
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_CycleStep extends PO implements I_C_CycleStep, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_CycleStep (Properties ctx, int C_CycleStep_ID, String trxName)
    {
      super (ctx, C_CycleStep_ID, trxName);
      /** if (C_CycleStep_ID == 0)
        {
			setC_Cycle_ID (0);
			setC_CycleStep_ID (0);
			setName (null);
			setRelativeWeight (Env.ZERO);
// 1
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_CycleStep WHERE C_Cycle_ID=@C_Cycle_ID@
        } */
    }

    /** Load Constructor */
    public X_C_CycleStep (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_CycleStep[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Cycle getC_Cycle() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Cycle.Table_Name);
        I_C_Cycle result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Cycle)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Cycle_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Project Cycle.
		@param C_Cycle_ID 
		Identifier for this Project Reporting Cycle
	  */
	public void setC_Cycle_ID (int C_Cycle_ID)
	{
		if (C_Cycle_ID < 1)
			 throw new IllegalArgumentException ("C_Cycle_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Cycle_ID, Integer.valueOf(C_Cycle_ID));
	}

	/** Get Project Cycle.
		@return Identifier for this Project Reporting Cycle
	  */
	public int getC_Cycle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Cycle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Relative Weight.
		@param RelativeWeight 
		Relative weight of this step (0 = ignored)
	  */
	public void setRelativeWeight (BigDecimal RelativeWeight)
	{
		if (RelativeWeight == null)
			throw new IllegalArgumentException ("RelativeWeight is mandatory.");
		set_Value (COLUMNNAME_RelativeWeight, RelativeWeight);
	}

	/** Get Relative Weight.
		@return Relative weight of this step (0 = ignored)
	  */
	public BigDecimal getRelativeWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RelativeWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}