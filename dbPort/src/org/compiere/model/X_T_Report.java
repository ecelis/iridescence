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

/** Generated Model for T_Report
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_T_Report extends PO implements I_T_Report, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_T_Report (Properties ctx, int T_Report_ID, String trxName)
    {
      super (ctx, T_Report_ID, trxName);
      /** if (T_Report_ID == 0)
        {
			setAD_PInstance_ID (0);
			setFact_Acct_ID (0);
			setPA_ReportLine_ID (0);
			setRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_T_Report (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_Report[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PInstance getAD_PInstance() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PInstance.Table_Name);
        I_AD_PInstance result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PInstance)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PInstance_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1)
			 throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Col 0.
		@param Col_0 Col 0	  */
	public void setCol_0 (BigDecimal Col_0)
	{
		set_ValueNoCheck (COLUMNNAME_Col_0, Col_0);
	}

	/** Get Col 0.
		@return Col 0	  */
	public BigDecimal getCol_0 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_0);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 1.
		@param Col_1 Col 1	  */
	public void setCol_1 (BigDecimal Col_1)
	{
		set_ValueNoCheck (COLUMNNAME_Col_1, Col_1);
	}

	/** Get Col 1.
		@return Col 1	  */
	public BigDecimal getCol_1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 10.
		@param Col_10 Col 10	  */
	public void setCol_10 (BigDecimal Col_10)
	{
		set_ValueNoCheck (COLUMNNAME_Col_10, Col_10);
	}

	/** Get Col 10.
		@return Col 10	  */
	public BigDecimal getCol_10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 11.
		@param Col_11 Col 11	  */
	public void setCol_11 (BigDecimal Col_11)
	{
		set_ValueNoCheck (COLUMNNAME_Col_11, Col_11);
	}

	/** Get Col 11.
		@return Col 11	  */
	public BigDecimal getCol_11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 12.
		@param Col_12 Col 12	  */
	public void setCol_12 (BigDecimal Col_12)
	{
		set_ValueNoCheck (COLUMNNAME_Col_12, Col_12);
	}

	/** Get Col 12.
		@return Col 12	  */
	public BigDecimal getCol_12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 13.
		@param Col_13 Col 13	  */
	public void setCol_13 (BigDecimal Col_13)
	{
		set_ValueNoCheck (COLUMNNAME_Col_13, Col_13);
	}

	/** Get Col 13.
		@return Col 13	  */
	public BigDecimal getCol_13 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_13);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 14.
		@param Col_14 Col 14	  */
	public void setCol_14 (BigDecimal Col_14)
	{
		set_ValueNoCheck (COLUMNNAME_Col_14, Col_14);
	}

	/** Get Col 14.
		@return Col 14	  */
	public BigDecimal getCol_14 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_14);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 15.
		@param Col_15 Col 15	  */
	public void setCol_15 (BigDecimal Col_15)
	{
		set_ValueNoCheck (COLUMNNAME_Col_15, Col_15);
	}

	/** Get Col 15.
		@return Col 15	  */
	public BigDecimal getCol_15 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_15);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 16.
		@param Col_16 Col 16	  */
	public void setCol_16 (BigDecimal Col_16)
	{
		set_ValueNoCheck (COLUMNNAME_Col_16, Col_16);
	}

	/** Get Col 16.
		@return Col 16	  */
	public BigDecimal getCol_16 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_16);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 17.
		@param Col_17 Col 17	  */
	public void setCol_17 (BigDecimal Col_17)
	{
		set_ValueNoCheck (COLUMNNAME_Col_17, Col_17);
	}

	/** Get Col 17.
		@return Col 17	  */
	public BigDecimal getCol_17 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_17);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 18.
		@param Col_18 Col 18	  */
	public void setCol_18 (BigDecimal Col_18)
	{
		set_ValueNoCheck (COLUMNNAME_Col_18, Col_18);
	}

	/** Get Col 18.
		@return Col 18	  */
	public BigDecimal getCol_18 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_18);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 19.
		@param Col_19 Col 19	  */
	public void setCol_19 (BigDecimal Col_19)
	{
		set_ValueNoCheck (COLUMNNAME_Col_19, Col_19);
	}

	/** Get Col 19.
		@return Col 19	  */
	public BigDecimal getCol_19 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_19);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 2.
		@param Col_2 Col 2	  */
	public void setCol_2 (BigDecimal Col_2)
	{
		set_ValueNoCheck (COLUMNNAME_Col_2, Col_2);
	}

	/** Get Col 2.
		@return Col 2	  */
	public BigDecimal getCol_2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 20.
		@param Col_20 Col 20	  */
	public void setCol_20 (BigDecimal Col_20)
	{
		set_ValueNoCheck (COLUMNNAME_Col_20, Col_20);
	}

	/** Get Col 20.
		@return Col 20	  */
	public BigDecimal getCol_20 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_20);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 3.
		@param Col_3 Col 3	  */
	public void setCol_3 (BigDecimal Col_3)
	{
		set_ValueNoCheck (COLUMNNAME_Col_3, Col_3);
	}

	/** Get Col 3.
		@return Col 3	  */
	public BigDecimal getCol_3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 4.
		@param Col_4 Col 4	  */
	public void setCol_4 (BigDecimal Col_4)
	{
		set_ValueNoCheck (COLUMNNAME_Col_4, Col_4);
	}

	/** Get Col 4.
		@return Col 4	  */
	public BigDecimal getCol_4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 5.
		@param Col_5 Col 5	  */
	public void setCol_5 (BigDecimal Col_5)
	{
		set_ValueNoCheck (COLUMNNAME_Col_5, Col_5);
	}

	/** Get Col 5.
		@return Col 5	  */
	public BigDecimal getCol_5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 6.
		@param Col_6 Col 6	  */
	public void setCol_6 (BigDecimal Col_6)
	{
		set_ValueNoCheck (COLUMNNAME_Col_6, Col_6);
	}

	/** Get Col 6.
		@return Col 6	  */
	public BigDecimal getCol_6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 7.
		@param Col_7 Col 7	  */
	public void setCol_7 (BigDecimal Col_7)
	{
		set_ValueNoCheck (COLUMNNAME_Col_7, Col_7);
	}

	/** Get Col 7.
		@return Col 7	  */
	public BigDecimal getCol_7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 8.
		@param Col_8 Col 8	  */
	public void setCol_8 (BigDecimal Col_8)
	{
		set_ValueNoCheck (COLUMNNAME_Col_8, Col_8);
	}

	/** Get Col 8.
		@return Col 8	  */
	public BigDecimal getCol_8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Col 9.
		@param Col_9 Col 9	  */
	public void setCol_9 (BigDecimal Col_9)
	{
		set_ValueNoCheck (COLUMNNAME_Col_9, Col_9);
	}

	/** Get Col 9.
		@return Col 9	  */
	public BigDecimal getCol_9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Col_9);
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
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Accounting Fact.
		@param Fact_Acct_ID Accounting Fact	  */
	public void setFact_Acct_ID (int Fact_Acct_ID)
	{
		if (Fact_Acct_ID < 1)
			 throw new IllegalArgumentException ("Fact_Acct_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Fact_Acct_ID, Integer.valueOf(Fact_Acct_ID));
	}

	/** Get Accounting Fact.
		@return Accounting Fact	  */
	public int getFact_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fact_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Level no.
		@param LevelNo Level no	  */
	public void setLevelNo (int LevelNo)
	{
		set_ValueNoCheck (COLUMNNAME_LevelNo, Integer.valueOf(LevelNo));
	}

	/** Get Level no.
		@return Level no	  */
	public int getLevelNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LevelNo);
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
		set_ValueNoCheck (COLUMNNAME_Name, Name);
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

	public I_PA_ReportLine getPA_ReportLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PA_ReportLine.Table_Name);
        I_PA_ReportLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PA_ReportLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getPA_ReportLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Report Line.
		@param PA_ReportLine_ID Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID)
	{
		if (PA_ReportLine_ID < 1)
			 throw new IllegalArgumentException ("PA_ReportLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, Integer.valueOf(PA_ReportLine_ID));
	}

	/** Get Report Line.
		@return Report Line	  */
	public int getPA_ReportLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			 throw new IllegalArgumentException ("Record_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
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

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_ValueNoCheck (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
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