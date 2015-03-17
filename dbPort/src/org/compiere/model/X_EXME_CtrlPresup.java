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

/** Generated Model for EXME_CtrlPresup
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CtrlPresup extends PO implements I_EXME_CtrlPresup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtrlPresup (Properties ctx, int EXME_CtrlPresup_ID, String trxName)
    {
      super (ctx, EXME_CtrlPresup_ID, trxName);
      /** if (EXME_CtrlPresup_ID == 0)
        {
			setAmount (Env.ZERO);
			setAmountAbr (Env.ZERO);
			setAmountAgo (Env.ZERO);
			setAmountDic (Env.ZERO);
			setAmountEne (Env.ZERO);
			setAmountFeb (Env.ZERO);
			setAmountJul (Env.ZERO);
			setAmountJun (Env.ZERO);
			setAmountMar (Env.ZERO);
			setAmountMay (Env.ZERO);
			setAmountNov (Env.ZERO);
			setAmountOct (Env.ZERO);
			setAmountSep (Env.ZERO);
			setCo1 (Env.ZERO);
			setCo10 (Env.ZERO);
			setCo11 (Env.ZERO);
			setCo12 (Env.ZERO);
			setCo2 (Env.ZERO);
			setCo3 (Env.ZERO);
			setCo4 (Env.ZERO);
			setCo5 (Env.ZERO);
			setCo6 (Env.ZERO);
			setCo7 (Env.ZERO);
			setCo8 (Env.ZERO);
			setCo9 (Env.ZERO);
			setCoSum (Env.ZERO);
			setC_Project_ID (0);
			setDe1 (Env.ZERO);
			setDe10 (Env.ZERO);
			setDe11 (Env.ZERO);
			setDe12 (Env.ZERO);
			setDe2 (Env.ZERO);
			setDe3 (Env.ZERO);
			setDe4 (Env.ZERO);
			setDe5 (Env.ZERO);
			setDe6 (Env.ZERO);
			setDe7 (Env.ZERO);
			setDe8 (Env.ZERO);
			setDe9 (Env.ZERO);
			setDescription (null);
			setDeSum (Env.ZERO);
			setDi1 (Env.ZERO);
			setDi10 (Env.ZERO);
			setDi11 (Env.ZERO);
			setDi12 (Env.ZERO);
			setDi2 (Env.ZERO);
			setDi3 (Env.ZERO);
			setDi4 (Env.ZERO);
			setDi5 (Env.ZERO);
			setDi6 (Env.ZERO);
			setDi7 (Env.ZERO);
			setDi8 (Env.ZERO);
			setDi9 (Env.ZERO);
			setDisum (Env.ZERO);
			setEj1 (Env.ZERO);
			setEj10 (Env.ZERO);
			setEj11 (Env.ZERO);
			setEj12 (Env.ZERO);
			setEj2 (Env.ZERO);
			setEj3 (Env.ZERO);
			setEj4 (Env.ZERO);
			setEj5 (Env.ZERO);
			setEj6 (Env.ZERO);
			setEj7 (Env.ZERO);
			setEj8 (Env.ZERO);
			setEj9 (Env.ZERO);
			setEjSum (Env.ZERO);
			setEXME_ActInstitucional_ID (0);
			setEXME_ClasFuncional_Fun_ID (0);
			setEXME_ClasFuncional_ID (0);
			setEXME_ClasFuncional_Sfu_ID (0);
			setEXME_CtrlPresup_ID (0);
			setEXME_FteFinanciamiento_ID (0);
			setEXME_PartidaPres_ID (0);
			setEXME_PresupuestoDet_ID (0);
			setEXME_PresupuestoEgr_ID (0);
			setEXME_ProgInstitucional_ID (0);
			setEXME_ProgPresupuestal_ID (0);
			setEXME_TipoGasto_ID (0);
			setLine (0);
			setMa1 (Env.ZERO);
			setMa10 (Env.ZERO);
			setMa11 (Env.ZERO);
			setMa12 (Env.ZERO);
			setMa2 (Env.ZERO);
			setMa3 (Env.ZERO);
			setMa4 (Env.ZERO);
			setMa5 (Env.ZERO);
			setMa6 (Env.ZERO);
			setMa7 (Env.ZERO);
			setMa8 (Env.ZERO);
			setMa9 (Env.ZERO);
			setMaSum (Env.ZERO);
			setName (null);
			setPa1 (Env.ZERO);
			setPa10 (Env.ZERO);
			setPa11 (Env.ZERO);
			setPa12 (Env.ZERO);
			setPa2 (Env.ZERO);
			setPa3 (Env.ZERO);
			setPa4 (Env.ZERO);
			setPa5 (Env.ZERO);
			setPa6 (Env.ZERO);
			setPa7 (Env.ZERO);
			setPa8 (Env.ZERO);
			setPa9 (Env.ZERO);
			setPaSum (Env.ZERO);
			setRt1 (Env.ZERO);
			setRt10 (Env.ZERO);
			setRt11 (Env.ZERO);
			setRt12 (Env.ZERO);
			setRt2 (Env.ZERO);
			setRt3 (Env.ZERO);
			setRt4 (Env.ZERO);
			setRt5 (Env.ZERO);
			setRt6 (Env.ZERO);
			setRt7 (Env.ZERO);
			setRt8 (Env.ZERO);
			setRt9 (Env.ZERO);
			setRtSum (Env.ZERO);
			setTipo (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtrlPresup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtrlPresup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		if (Amount == null)
			throw new IllegalArgumentException ("Amount is mandatory.");
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount April.
		@param AmountAbr 
		Amount April
	  */
	public void setAmountAbr (BigDecimal AmountAbr)
	{
		if (AmountAbr == null)
			throw new IllegalArgumentException ("AmountAbr is mandatory.");
		set_Value (COLUMNNAME_AmountAbr, AmountAbr);
	}

	/** Get Amount April.
		@return Amount April
	  */
	public BigDecimal getAmountAbr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountAbr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount August.
		@param AmountAgo 
		Amount August
	  */
	public void setAmountAgo (BigDecimal AmountAgo)
	{
		if (AmountAgo == null)
			throw new IllegalArgumentException ("AmountAgo is mandatory.");
		set_Value (COLUMNNAME_AmountAgo, AmountAgo);
	}

	/** Get Amount August.
		@return Amount August
	  */
	public BigDecimal getAmountAgo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountAgo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount December.
		@param AmountDic 
		Amount December
	  */
	public void setAmountDic (BigDecimal AmountDic)
	{
		if (AmountDic == null)
			throw new IllegalArgumentException ("AmountDic is mandatory.");
		set_Value (COLUMNNAME_AmountDic, AmountDic);
	}

	/** Get Amount December.
		@return Amount December
	  */
	public BigDecimal getAmountDic () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountDic);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount January.
		@param AmountEne 
		Amount January
	  */
	public void setAmountEne (BigDecimal AmountEne)
	{
		if (AmountEne == null)
			throw new IllegalArgumentException ("AmountEne is mandatory.");
		set_Value (COLUMNNAME_AmountEne, AmountEne);
	}

	/** Get Amount January.
		@return Amount January
	  */
	public BigDecimal getAmountEne () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountEne);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount February.
		@param AmountFeb 
		Amount February
	  */
	public void setAmountFeb (BigDecimal AmountFeb)
	{
		if (AmountFeb == null)
			throw new IllegalArgumentException ("AmountFeb is mandatory.");
		set_Value (COLUMNNAME_AmountFeb, AmountFeb);
	}

	/** Get Amount February.
		@return Amount February
	  */
	public BigDecimal getAmountFeb () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountFeb);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount July.
		@param AmountJul 
		Amount July
	  */
	public void setAmountJul (BigDecimal AmountJul)
	{
		if (AmountJul == null)
			throw new IllegalArgumentException ("AmountJul is mandatory.");
		set_Value (COLUMNNAME_AmountJul, AmountJul);
	}

	/** Get Amount July.
		@return Amount July
	  */
	public BigDecimal getAmountJul () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountJul);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount June.
		@param AmountJun 
		Amount June
	  */
	public void setAmountJun (BigDecimal AmountJun)
	{
		if (AmountJun == null)
			throw new IllegalArgumentException ("AmountJun is mandatory.");
		set_Value (COLUMNNAME_AmountJun, AmountJun);
	}

	/** Get Amount June.
		@return Amount June
	  */
	public BigDecimal getAmountJun () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountJun);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount March.
		@param AmountMar 
		Amount March
	  */
	public void setAmountMar (BigDecimal AmountMar)
	{
		if (AmountMar == null)
			throw new IllegalArgumentException ("AmountMar is mandatory.");
		set_Value (COLUMNNAME_AmountMar, AmountMar);
	}

	/** Get Amount March.
		@return Amount March
	  */
	public BigDecimal getAmountMar () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountMar);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount May.
		@param AmountMay 
		Amount May
	  */
	public void setAmountMay (BigDecimal AmountMay)
	{
		if (AmountMay == null)
			throw new IllegalArgumentException ("AmountMay is mandatory.");
		set_Value (COLUMNNAME_AmountMay, AmountMay);
	}

	/** Get Amount May.
		@return Amount May
	  */
	public BigDecimal getAmountMay () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountMay);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount November.
		@param AmountNov 
		Amount November
	  */
	public void setAmountNov (BigDecimal AmountNov)
	{
		if (AmountNov == null)
			throw new IllegalArgumentException ("AmountNov is mandatory.");
		set_Value (COLUMNNAME_AmountNov, AmountNov);
	}

	/** Get Amount November.
		@return Amount November
	  */
	public BigDecimal getAmountNov () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountNov);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount October.
		@param AmountOct 
		Amount October
	  */
	public void setAmountOct (BigDecimal AmountOct)
	{
		if (AmountOct == null)
			throw new IllegalArgumentException ("AmountOct is mandatory.");
		set_Value (COLUMNNAME_AmountOct, AmountOct);
	}

	/** Get Amount October.
		@return Amount October
	  */
	public BigDecimal getAmountOct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountOct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount September.
		@param AmountSep 
		Amount September
	  */
	public void setAmountSep (BigDecimal AmountSep)
	{
		if (AmountSep == null)
			throw new IllegalArgumentException ("AmountSep is mandatory.");
		set_Value (COLUMNNAME_AmountSep, AmountSep);
	}

	/** Get Amount September.
		@return Amount September
	  */
	public BigDecimal getAmountSep () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmountSep);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. January.
		@param Co1 
		GL
	  */
	public void setCo1 (BigDecimal Co1)
	{
		if (Co1 == null)
			throw new IllegalArgumentException ("Co1 is mandatory.");
		set_Value (COLUMNNAME_Co1, Co1);
	}

	/** Get Co. January.
		@return GL
	  */
	public BigDecimal getCo1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. October.
		@param Co10 
		GL
	  */
	public void setCo10 (BigDecimal Co10)
	{
		if (Co10 == null)
			throw new IllegalArgumentException ("Co10 is mandatory.");
		set_Value (COLUMNNAME_Co10, Co10);
	}

	/** Get Co. October.
		@return GL
	  */
	public BigDecimal getCo10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. November.
		@param Co11 
		GL
	  */
	public void setCo11 (BigDecimal Co11)
	{
		if (Co11 == null)
			throw new IllegalArgumentException ("Co11 is mandatory.");
		set_Value (COLUMNNAME_Co11, Co11);
	}

	/** Get Co. November.
		@return GL
	  */
	public BigDecimal getCo11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. December.
		@param Co12 
		GL
	  */
	public void setCo12 (BigDecimal Co12)
	{
		if (Co12 == null)
			throw new IllegalArgumentException ("Co12 is mandatory.");
		set_Value (COLUMNNAME_Co12, Co12);
	}

	/** Get Co. December.
		@return GL
	  */
	public BigDecimal getCo12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. February.
		@param Co2 
		GL
	  */
	public void setCo2 (BigDecimal Co2)
	{
		if (Co2 == null)
			throw new IllegalArgumentException ("Co2 is mandatory.");
		set_Value (COLUMNNAME_Co2, Co2);
	}

	/** Get Co. February.
		@return GL
	  */
	public BigDecimal getCo2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. March.
		@param Co3 
		GL
	  */
	public void setCo3 (BigDecimal Co3)
	{
		if (Co3 == null)
			throw new IllegalArgumentException ("Co3 is mandatory.");
		set_Value (COLUMNNAME_Co3, Co3);
	}

	/** Get Co. March.
		@return GL
	  */
	public BigDecimal getCo3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. April.
		@param Co4 
		GL
	  */
	public void setCo4 (BigDecimal Co4)
	{
		if (Co4 == null)
			throw new IllegalArgumentException ("Co4 is mandatory.");
		set_Value (COLUMNNAME_Co4, Co4);
	}

	/** Get Co. April.
		@return GL
	  */
	public BigDecimal getCo4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. May.
		@param Co5 
		GL
	  */
	public void setCo5 (BigDecimal Co5)
	{
		if (Co5 == null)
			throw new IllegalArgumentException ("Co5 is mandatory.");
		set_Value (COLUMNNAME_Co5, Co5);
	}

	/** Get Co. May.
		@return GL
	  */
	public BigDecimal getCo5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. June.
		@param Co6 
		GL
	  */
	public void setCo6 (BigDecimal Co6)
	{
		if (Co6 == null)
			throw new IllegalArgumentException ("Co6 is mandatory.");
		set_Value (COLUMNNAME_Co6, Co6);
	}

	/** Get Co. June.
		@return GL
	  */
	public BigDecimal getCo6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. July.
		@param Co7 
		GL
	  */
	public void setCo7 (BigDecimal Co7)
	{
		if (Co7 == null)
			throw new IllegalArgumentException ("Co7 is mandatory.");
		set_Value (COLUMNNAME_Co7, Co7);
	}

	/** Get Co. July.
		@return GL
	  */
	public BigDecimal getCo7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. August.
		@param Co8 
		GL
	  */
	public void setCo8 (BigDecimal Co8)
	{
		if (Co8 == null)
			throw new IllegalArgumentException ("Co8 is mandatory.");
		set_Value (COLUMNNAME_Co8, Co8);
	}

	/** Get Co. August.
		@return GL
	  */
	public BigDecimal getCo8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. September.
		@param Co9 
		GL
	  */
	public void setCo9 (BigDecimal Co9)
	{
		if (Co9 == null)
			throw new IllegalArgumentException ("Co9 is mandatory.");
		set_Value (COLUMNNAME_Co9, Co9);
	}

	/** Get Co. September.
		@return GL
	  */
	public BigDecimal getCo9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Co9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Co. Sum.
		@param CoSum 
		GL
	  */
	public void setCoSum (BigDecimal CoSum)
	{
		if (CoSum == null)
			throw new IllegalArgumentException ("CoSum is mandatory.");
		set_Value (COLUMNNAME_CoSum, CoSum);
	}

	/** Get Co. Sum.
		@return GL
	  */
	public BigDecimal getCoSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CoSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Project getC_Project() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Project.Table_Name);
        I_C_Project result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Project)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Project_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1)
			 throw new IllegalArgumentException ("C_Project_ID is mandatory.");
		set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Region getC_Region() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Region.Table_Name);
        I_C_Region result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Region)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Region_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Region.
		@param C_Region_ID 
		Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID)
	{
		if (C_Region_ID < 1) 
			set_Value (COLUMNNAME_C_Region_ID, null);
		else 
			set_Value (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
	}

	/** Get Region.
		@return Identifies a geographical Region
	  */
	public int getC_Region_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set De. January.
		@param De1 
		GL
	  */
	public void setDe1 (BigDecimal De1)
	{
		if (De1 == null)
			throw new IllegalArgumentException ("De1 is mandatory.");
		set_Value (COLUMNNAME_De1, De1);
	}

	/** Get De. January.
		@return GL
	  */
	public BigDecimal getDe1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. October.
		@param De10 
		GL
	  */
	public void setDe10 (BigDecimal De10)
	{
		if (De10 == null)
			throw new IllegalArgumentException ("De10 is mandatory.");
		set_Value (COLUMNNAME_De10, De10);
	}

	/** Get De. October.
		@return GL
	  */
	public BigDecimal getDe10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. November.
		@param De11 
		GL
	  */
	public void setDe11 (BigDecimal De11)
	{
		if (De11 == null)
			throw new IllegalArgumentException ("De11 is mandatory.");
		set_Value (COLUMNNAME_De11, De11);
	}

	/** Get De. November.
		@return GL
	  */
	public BigDecimal getDe11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. December.
		@param De12 
		GL
	  */
	public void setDe12 (BigDecimal De12)
	{
		if (De12 == null)
			throw new IllegalArgumentException ("De12 is mandatory.");
		set_Value (COLUMNNAME_De12, De12);
	}

	/** Get De. December.
		@return GL
	  */
	public BigDecimal getDe12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. February.
		@param De2 
		GL
	  */
	public void setDe2 (BigDecimal De2)
	{
		if (De2 == null)
			throw new IllegalArgumentException ("De2 is mandatory.");
		set_Value (COLUMNNAME_De2, De2);
	}

	/** Get De. February.
		@return GL
	  */
	public BigDecimal getDe2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. March.
		@param De3 
		GL
	  */
	public void setDe3 (BigDecimal De3)
	{
		if (De3 == null)
			throw new IllegalArgumentException ("De3 is mandatory.");
		set_Value (COLUMNNAME_De3, De3);
	}

	/** Get De. March.
		@return GL
	  */
	public BigDecimal getDe3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. April.
		@param De4 
		GL
	  */
	public void setDe4 (BigDecimal De4)
	{
		if (De4 == null)
			throw new IllegalArgumentException ("De4 is mandatory.");
		set_Value (COLUMNNAME_De4, De4);
	}

	/** Get De. April.
		@return GL
	  */
	public BigDecimal getDe4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. May.
		@param De5 
		GL
	  */
	public void setDe5 (BigDecimal De5)
	{
		if (De5 == null)
			throw new IllegalArgumentException ("De5 is mandatory.");
		set_Value (COLUMNNAME_De5, De5);
	}

	/** Get De. May.
		@return GL
	  */
	public BigDecimal getDe5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. June.
		@param De6 
		GL
	  */
	public void setDe6 (BigDecimal De6)
	{
		if (De6 == null)
			throw new IllegalArgumentException ("De6 is mandatory.");
		set_Value (COLUMNNAME_De6, De6);
	}

	/** Get De. June.
		@return GL
	  */
	public BigDecimal getDe6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. July.
		@param De7 
		GL
	  */
	public void setDe7 (BigDecimal De7)
	{
		if (De7 == null)
			throw new IllegalArgumentException ("De7 is mandatory.");
		set_Value (COLUMNNAME_De7, De7);
	}

	/** Get De. July.
		@return GL
	  */
	public BigDecimal getDe7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. August.
		@param De8 
		GL
	  */
	public void setDe8 (BigDecimal De8)
	{
		if (De8 == null)
			throw new IllegalArgumentException ("De8 is mandatory.");
		set_Value (COLUMNNAME_De8, De8);
	}

	/** Get De. August.
		@return GL
	  */
	public BigDecimal getDe8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set De. September.
		@param De9 
		GL
	  */
	public void setDe9 (BigDecimal De9)
	{
		if (De9 == null)
			throw new IllegalArgumentException ("De9 is mandatory.");
		set_Value (COLUMNNAME_De9, De9);
	}

	/** Get De. September.
		@return GL
	  */
	public BigDecimal getDe9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_De9);
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
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set De. Sum.
		@param DeSum 
		GL
	  */
	public void setDeSum (BigDecimal DeSum)
	{
		if (DeSum == null)
			throw new IllegalArgumentException ("DeSum is mandatory.");
		set_Value (COLUMNNAME_DeSum, DeSum);
	}

	/** Get De. Sum.
		@return GL
	  */
	public BigDecimal getDeSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DeSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. January.
		@param Di1 
		GL
	  */
	public void setDi1 (BigDecimal Di1)
	{
		if (Di1 == null)
			throw new IllegalArgumentException ("Di1 is mandatory.");
		set_Value (COLUMNNAME_Di1, Di1);
	}

	/** Get Di. January.
		@return GL
	  */
	public BigDecimal getDi1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. October.
		@param Di10 
		GL
	  */
	public void setDi10 (BigDecimal Di10)
	{
		if (Di10 == null)
			throw new IllegalArgumentException ("Di10 is mandatory.");
		set_Value (COLUMNNAME_Di10, Di10);
	}

	/** Get Di. October.
		@return GL
	  */
	public BigDecimal getDi10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. November.
		@param Di11 
		GL
	  */
	public void setDi11 (BigDecimal Di11)
	{
		if (Di11 == null)
			throw new IllegalArgumentException ("Di11 is mandatory.");
		set_Value (COLUMNNAME_Di11, Di11);
	}

	/** Get Di. November.
		@return GL
	  */
	public BigDecimal getDi11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. December.
		@param Di12 
		GL
	  */
	public void setDi12 (BigDecimal Di12)
	{
		if (Di12 == null)
			throw new IllegalArgumentException ("Di12 is mandatory.");
		set_Value (COLUMNNAME_Di12, Di12);
	}

	/** Get Di. December.
		@return GL
	  */
	public BigDecimal getDi12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. February.
		@param Di2 
		GL
	  */
	public void setDi2 (BigDecimal Di2)
	{
		if (Di2 == null)
			throw new IllegalArgumentException ("Di2 is mandatory.");
		set_Value (COLUMNNAME_Di2, Di2);
	}

	/** Get Di. February.
		@return GL
	  */
	public BigDecimal getDi2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. March.
		@param Di3 
		GL
	  */
	public void setDi3 (BigDecimal Di3)
	{
		if (Di3 == null)
			throw new IllegalArgumentException ("Di3 is mandatory.");
		set_Value (COLUMNNAME_Di3, Di3);
	}

	/** Get Di. March.
		@return GL
	  */
	public BigDecimal getDi3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. April.
		@param Di4 
		GL
	  */
	public void setDi4 (BigDecimal Di4)
	{
		if (Di4 == null)
			throw new IllegalArgumentException ("Di4 is mandatory.");
		set_Value (COLUMNNAME_Di4, Di4);
	}

	/** Get Di. April.
		@return GL
	  */
	public BigDecimal getDi4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. May.
		@param Di5 
		GL
	  */
	public void setDi5 (BigDecimal Di5)
	{
		if (Di5 == null)
			throw new IllegalArgumentException ("Di5 is mandatory.");
		set_Value (COLUMNNAME_Di5, Di5);
	}

	/** Get Di. May.
		@return GL
	  */
	public BigDecimal getDi5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. June.
		@param Di6 
		GL
	  */
	public void setDi6 (BigDecimal Di6)
	{
		if (Di6 == null)
			throw new IllegalArgumentException ("Di6 is mandatory.");
		set_Value (COLUMNNAME_Di6, Di6);
	}

	/** Get Di. June.
		@return GL
	  */
	public BigDecimal getDi6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. July.
		@param Di7 
		GL
	  */
	public void setDi7 (BigDecimal Di7)
	{
		if (Di7 == null)
			throw new IllegalArgumentException ("Di7 is mandatory.");
		set_Value (COLUMNNAME_Di7, Di7);
	}

	/** Get Di. July.
		@return GL
	  */
	public BigDecimal getDi7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. August.
		@param Di8 
		GL
	  */
	public void setDi8 (BigDecimal Di8)
	{
		if (Di8 == null)
			throw new IllegalArgumentException ("Di8 is mandatory.");
		set_Value (COLUMNNAME_Di8, Di8);
	}

	/** Get Di. August.
		@return GL
	  */
	public BigDecimal getDi8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. September.
		@param Di9 
		GL
	  */
	public void setDi9 (BigDecimal Di9)
	{
		if (Di9 == null)
			throw new IllegalArgumentException ("Di9 is mandatory.");
		set_Value (COLUMNNAME_Di9, Di9);
	}

	/** Get Di. September.
		@return GL
	  */
	public BigDecimal getDi9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Di9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Di. Sum.
		@param Disum 
		GL
	  */
	public void setDisum (BigDecimal Disum)
	{
		if (Disum == null)
			throw new IllegalArgumentException ("Disum is mandatory.");
		set_Value (COLUMNNAME_Disum, Disum);
	}

	/** Get Di. Sum.
		@return GL
	  */
	public BigDecimal getDisum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Disum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. January.
		@param Ej1 
		GL
	  */
	public void setEj1 (BigDecimal Ej1)
	{
		if (Ej1 == null)
			throw new IllegalArgumentException ("Ej1 is mandatory.");
		set_Value (COLUMNNAME_Ej1, Ej1);
	}

	/** Get Ej. January.
		@return GL
	  */
	public BigDecimal getEj1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. October.
		@param Ej10 
		GL
	  */
	public void setEj10 (BigDecimal Ej10)
	{
		if (Ej10 == null)
			throw new IllegalArgumentException ("Ej10 is mandatory.");
		set_Value (COLUMNNAME_Ej10, Ej10);
	}

	/** Get Ej. October.
		@return GL
	  */
	public BigDecimal getEj10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. November.
		@param Ej11 
		GL
	  */
	public void setEj11 (BigDecimal Ej11)
	{
		if (Ej11 == null)
			throw new IllegalArgumentException ("Ej11 is mandatory.");
		set_Value (COLUMNNAME_Ej11, Ej11);
	}

	/** Get Ej. November.
		@return GL
	  */
	public BigDecimal getEj11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. December.
		@param Ej12 
		GL
	  */
	public void setEj12 (BigDecimal Ej12)
	{
		if (Ej12 == null)
			throw new IllegalArgumentException ("Ej12 is mandatory.");
		set_Value (COLUMNNAME_Ej12, Ej12);
	}

	/** Get Ej. December.
		@return GL
	  */
	public BigDecimal getEj12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. February.
		@param Ej2 
		GL
	  */
	public void setEj2 (BigDecimal Ej2)
	{
		if (Ej2 == null)
			throw new IllegalArgumentException ("Ej2 is mandatory.");
		set_Value (COLUMNNAME_Ej2, Ej2);
	}

	/** Get Ej. February.
		@return GL
	  */
	public BigDecimal getEj2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. March.
		@param Ej3 
		GL
	  */
	public void setEj3 (BigDecimal Ej3)
	{
		if (Ej3 == null)
			throw new IllegalArgumentException ("Ej3 is mandatory.");
		set_Value (COLUMNNAME_Ej3, Ej3);
	}

	/** Get Ej. March.
		@return GL
	  */
	public BigDecimal getEj3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. April.
		@param Ej4 
		GL
	  */
	public void setEj4 (BigDecimal Ej4)
	{
		if (Ej4 == null)
			throw new IllegalArgumentException ("Ej4 is mandatory.");
		set_Value (COLUMNNAME_Ej4, Ej4);
	}

	/** Get Ej. April.
		@return GL
	  */
	public BigDecimal getEj4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. May.
		@param Ej5 
		GL
	  */
	public void setEj5 (BigDecimal Ej5)
	{
		if (Ej5 == null)
			throw new IllegalArgumentException ("Ej5 is mandatory.");
		set_Value (COLUMNNAME_Ej5, Ej5);
	}

	/** Get Ej. May.
		@return GL
	  */
	public BigDecimal getEj5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. June.
		@param Ej6 
		GL
	  */
	public void setEj6 (BigDecimal Ej6)
	{
		if (Ej6 == null)
			throw new IllegalArgumentException ("Ej6 is mandatory.");
		set_Value (COLUMNNAME_Ej6, Ej6);
	}

	/** Get Ej. June.
		@return GL
	  */
	public BigDecimal getEj6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. July.
		@param Ej7 
		GL
	  */
	public void setEj7 (BigDecimal Ej7)
	{
		if (Ej7 == null)
			throw new IllegalArgumentException ("Ej7 is mandatory.");
		set_Value (COLUMNNAME_Ej7, Ej7);
	}

	/** Get Ej. July.
		@return GL
	  */
	public BigDecimal getEj7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. August.
		@param Ej8 
		GL
	  */
	public void setEj8 (BigDecimal Ej8)
	{
		if (Ej8 == null)
			throw new IllegalArgumentException ("Ej8 is mandatory.");
		set_Value (COLUMNNAME_Ej8, Ej8);
	}

	/** Get Ej. August.
		@return GL
	  */
	public BigDecimal getEj8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. September.
		@param Ej9 
		GL
	  */
	public void setEj9 (BigDecimal Ej9)
	{
		if (Ej9 == null)
			throw new IllegalArgumentException ("Ej9 is mandatory.");
		set_Value (COLUMNNAME_Ej9, Ej9);
	}

	/** Get Ej. September.
		@return GL
	  */
	public BigDecimal getEj9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ej9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ej. Sum.
		@param EjSum 
		GL
	  */
	public void setEjSum (BigDecimal EjSum)
	{
		if (EjSum == null)
			throw new IllegalArgumentException ("EjSum is mandatory.");
		set_Value (COLUMNNAME_EjSum, EjSum);
	}

	/** Get Ej. Sum.
		@return GL
	  */
	public BigDecimal getEjSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EjSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_ActInstitucional getEXME_ActInstitucional() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActInstitucional.Table_Name);
        I_EXME_ActInstitucional result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActInstitucional)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActInstitucional_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institutional Activity.
		@param EXME_ActInstitucional_ID Institutional Activity	  */
	public void setEXME_ActInstitucional_ID (int EXME_ActInstitucional_ID)
	{
		if (EXME_ActInstitucional_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActInstitucional_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActInstitucional_ID, Integer.valueOf(EXME_ActInstitucional_ID));
	}

	/** Get Institutional Activity.
		@return Institutional Activity	  */
	public int getEXME_ActInstitucional_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActInstitucional_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClasFuncional_Fun getEXME_ClasFuncional_Fun() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasFuncional_Fun.Table_Name);
        I_EXME_ClasFuncional_Fun result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasFuncional_Fun)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasFuncional_Fun_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Function.
		@param EXME_ClasFuncional_Fun_ID Function	  */
	public void setEXME_ClasFuncional_Fun_ID (int EXME_ClasFuncional_Fun_ID)
	{
		if (EXME_ClasFuncional_Fun_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClasFuncional_Fun_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ClasFuncional_Fun_ID, Integer.valueOf(EXME_ClasFuncional_Fun_ID));
	}

	/** Get Function.
		@return Function	  */
	public int getEXME_ClasFuncional_Fun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasFuncional_Fun_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClasFuncional getEXME_ClasFuncional() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasFuncional.Table_Name);
        I_EXME_ClasFuncional result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasFuncional)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasFuncional_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Functional classification.
		@param EXME_ClasFuncional_ID Functional classification	  */
	public void setEXME_ClasFuncional_ID (int EXME_ClasFuncional_ID)
	{
		if (EXME_ClasFuncional_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClasFuncional_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ClasFuncional_ID, Integer.valueOf(EXME_ClasFuncional_ID));
	}

	/** Get Functional classification.
		@return Functional classification	  */
	public int getEXME_ClasFuncional_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasFuncional_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClasFuncional_Sfu getEXME_ClasFuncional_Sfu() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasFuncional_Sfu.Table_Name);
        I_EXME_ClasFuncional_Sfu result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasFuncional_Sfu)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasFuncional_Sfu_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Subfunction.
		@param EXME_ClasFuncional_Sfu_ID Subfunction	  */
	public void setEXME_ClasFuncional_Sfu_ID (int EXME_ClasFuncional_Sfu_ID)
	{
		if (EXME_ClasFuncional_Sfu_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClasFuncional_Sfu_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ClasFuncional_Sfu_ID, Integer.valueOf(EXME_ClasFuncional_Sfu_ID));
	}

	/** Get Subfunction.
		@return Subfunction	  */
	public int getEXME_ClasFuncional_Sfu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasFuncional_Sfu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Budgetary control.
		@param EXME_CtrlPresup_ID Budgetary control	  */
	public void setEXME_CtrlPresup_ID (int EXME_CtrlPresup_ID)
	{
		if (EXME_CtrlPresup_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtrlPresup_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtrlPresup_ID, Integer.valueOf(EXME_CtrlPresup_ID));
	}

	/** Get Budgetary control.
		@return Budgetary control	  */
	public int getEXME_CtrlPresup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtrlPresup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FteFinanciamiento getEXME_FteFinanciamiento() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FteFinanciamiento.Table_Name);
        I_EXME_FteFinanciamiento result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FteFinanciamiento)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FteFinanciamiento_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Source of Funding.
		@param EXME_FteFinanciamiento_ID 
		Source of Funding
	  */
	public void setEXME_FteFinanciamiento_ID (int EXME_FteFinanciamiento_ID)
	{
		if (EXME_FteFinanciamiento_ID < 1)
			 throw new IllegalArgumentException ("EXME_FteFinanciamiento_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FteFinanciamiento_ID, Integer.valueOf(EXME_FteFinanciamiento_ID));
	}

	/** Get Source of Funding.
		@return Source of Funding
	  */
	public int getEXME_FteFinanciamiento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FteFinanciamiento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PartidaPres getEXME_PartidaPres() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PartidaPres.Table_Name);
        I_EXME_PartidaPres result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PartidaPres)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PartidaPres_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget Item.
		@param EXME_PartidaPres_ID 
		Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID)
	{
		if (EXME_PartidaPres_ID < 1)
			 throw new IllegalArgumentException ("EXME_PartidaPres_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PartidaPres_ID, Integer.valueOf(EXME_PartidaPres_ID));
	}

	/** Get Budget Item.
		@return Budget Item
	  */
	public int getEXME_PartidaPres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PartidaPres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PresupuestoDet getEXME_PresupuestoDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PresupuestoDet.Table_Name);
        I_EXME_PresupuestoDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PresupuestoDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PresupuestoDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Egress budget (Detail).
		@param EXME_PresupuestoDet_ID Egress budget (Detail)	  */
	public void setEXME_PresupuestoDet_ID (int EXME_PresupuestoDet_ID)
	{
		if (EXME_PresupuestoDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PresupuestoDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PresupuestoDet_ID, Integer.valueOf(EXME_PresupuestoDet_ID));
	}

	/** Get Egress budget (Detail).
		@return Egress budget (Detail)	  */
	public int getEXME_PresupuestoDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PresupuestoDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PresupuestoEgr getEXME_PresupuestoEgr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PresupuestoEgr.Table_Name);
        I_EXME_PresupuestoEgr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PresupuestoEgr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PresupuestoEgr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Egress budget.
		@param EXME_PresupuestoEgr_ID Egress budget	  */
	public void setEXME_PresupuestoEgr_ID (int EXME_PresupuestoEgr_ID)
	{
		if (EXME_PresupuestoEgr_ID < 1)
			 throw new IllegalArgumentException ("EXME_PresupuestoEgr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PresupuestoEgr_ID, Integer.valueOf(EXME_PresupuestoEgr_ID));
	}

	/** Get Egress budget.
		@return Egress budget	  */
	public int getEXME_PresupuestoEgr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PresupuestoEgr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgInstitucional getEXME_ProgInstitucional() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgInstitucional.Table_Name);
        I_EXME_ProgInstitucional result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgInstitucional)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgInstitucional_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Institutional program.
		@param EXME_ProgInstitucional_ID Institutional program	  */
	public void setEXME_ProgInstitucional_ID (int EXME_ProgInstitucional_ID)
	{
		if (EXME_ProgInstitucional_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgInstitucional_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProgInstitucional_ID, Integer.valueOf(EXME_ProgInstitucional_ID));
	}

	/** Get Institutional program.
		@return Institutional program	  */
	public int getEXME_ProgInstitucional_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgInstitucional_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProgPresupuestal getEXME_ProgPresupuestal() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgPresupuestal.Table_Name);
        I_EXME_ProgPresupuestal result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgPresupuestal)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgPresupuestal_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget programmes.
		@param EXME_ProgPresupuestal_ID 
		Budget programmes
	  */
	public void setEXME_ProgPresupuestal_ID (int EXME_ProgPresupuestal_ID)
	{
		if (EXME_ProgPresupuestal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProgPresupuestal_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProgPresupuestal_ID, Integer.valueOf(EXME_ProgPresupuestal_ID));
	}

	/** Get Budget programmes.
		@return Budget programmes
	  */
	public int getEXME_ProgPresupuestal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgPresupuestal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Reasignacion getEXME_Reasignacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Reasignacion.Table_Name);
        I_EXME_Reasignacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Reasignacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Reasignacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reallocation.
		@param EXME_Reasignacion_ID 
		Reallocation
	  */
	public void setEXME_Reasignacion_ID (int EXME_Reasignacion_ID)
	{
		if (EXME_Reasignacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Reasignacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Reasignacion_ID, Integer.valueOf(EXME_Reasignacion_ID));
	}

	/** Get Reallocation.
		@return Reallocation
	  */
	public int getEXME_Reasignacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Reasignacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoGasto getEXME_TipoGasto() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoGasto.Table_Name);
        I_EXME_TipoGasto result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoGasto)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoGasto_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of expenditure.
		@param EXME_TipoGasto_ID 
		Type of expenditure
	  */
	public void setEXME_TipoGasto_ID (int EXME_TipoGasto_ID)
	{
		if (EXME_TipoGasto_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoGasto_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoGasto_ID, Integer.valueOf(EXME_TipoGasto_ID));
	}

	/** Get Type of expenditure.
		@return Type of expenditure
	  */
	public int getEXME_TipoGasto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoGasto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MA. January.
		@param Ma1 
		GL
	  */
	public void setMa1 (BigDecimal Ma1)
	{
		if (Ma1 == null)
			throw new IllegalArgumentException ("Ma1 is mandatory.");
		set_Value (COLUMNNAME_Ma1, Ma1);
	}

	/** Get MA. January.
		@return GL
	  */
	public BigDecimal getMa1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. October.
		@param Ma10 
		GL
	  */
	public void setMa10 (BigDecimal Ma10)
	{
		if (Ma10 == null)
			throw new IllegalArgumentException ("Ma10 is mandatory.");
		set_Value (COLUMNNAME_Ma10, Ma10);
	}

	/** Get MA. October.
		@return GL
	  */
	public BigDecimal getMa10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. November.
		@param Ma11 
		GL
	  */
	public void setMa11 (BigDecimal Ma11)
	{
		if (Ma11 == null)
			throw new IllegalArgumentException ("Ma11 is mandatory.");
		set_Value (COLUMNNAME_Ma11, Ma11);
	}

	/** Get MA. November.
		@return GL
	  */
	public BigDecimal getMa11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. December.
		@param Ma12 
		GL
	  */
	public void setMa12 (BigDecimal Ma12)
	{
		if (Ma12 == null)
			throw new IllegalArgumentException ("Ma12 is mandatory.");
		set_Value (COLUMNNAME_Ma12, Ma12);
	}

	/** Get MA. December.
		@return GL
	  */
	public BigDecimal getMa12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. February.
		@param Ma2 
		GL
	  */
	public void setMa2 (BigDecimal Ma2)
	{
		if (Ma2 == null)
			throw new IllegalArgumentException ("Ma2 is mandatory.");
		set_Value (COLUMNNAME_Ma2, Ma2);
	}

	/** Get MA. February.
		@return GL
	  */
	public BigDecimal getMa2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. March.
		@param Ma3 
		GL
	  */
	public void setMa3 (BigDecimal Ma3)
	{
		if (Ma3 == null)
			throw new IllegalArgumentException ("Ma3 is mandatory.");
		set_Value (COLUMNNAME_Ma3, Ma3);
	}

	/** Get MA. March.
		@return GL
	  */
	public BigDecimal getMa3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. April.
		@param Ma4 
		GL
	  */
	public void setMa4 (BigDecimal Ma4)
	{
		if (Ma4 == null)
			throw new IllegalArgumentException ("Ma4 is mandatory.");
		set_Value (COLUMNNAME_Ma4, Ma4);
	}

	/** Get MA. April.
		@return GL
	  */
	public BigDecimal getMa4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. May.
		@param Ma5 
		GL
	  */
	public void setMa5 (BigDecimal Ma5)
	{
		if (Ma5 == null)
			throw new IllegalArgumentException ("Ma5 is mandatory.");
		set_Value (COLUMNNAME_Ma5, Ma5);
	}

	/** Get MA. May.
		@return GL
	  */
	public BigDecimal getMa5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. June.
		@param Ma6 
		GL
	  */
	public void setMa6 (BigDecimal Ma6)
	{
		if (Ma6 == null)
			throw new IllegalArgumentException ("Ma6 is mandatory.");
		set_Value (COLUMNNAME_Ma6, Ma6);
	}

	/** Get MA. June.
		@return GL
	  */
	public BigDecimal getMa6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. July.
		@param Ma7 
		GL
	  */
	public void setMa7 (BigDecimal Ma7)
	{
		if (Ma7 == null)
			throw new IllegalArgumentException ("Ma7 is mandatory.");
		set_Value (COLUMNNAME_Ma7, Ma7);
	}

	/** Get MA. July.
		@return GL
	  */
	public BigDecimal getMa7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. August.
		@param Ma8 
		GL
	  */
	public void setMa8 (BigDecimal Ma8)
	{
		if (Ma8 == null)
			throw new IllegalArgumentException ("Ma8 is mandatory.");
		set_Value (COLUMNNAME_Ma8, Ma8);
	}

	/** Get MA. August.
		@return GL
	  */
	public BigDecimal getMa8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA. September.
		@param Ma9 
		GL
	  */
	public void setMa9 (BigDecimal Ma9)
	{
		if (Ma9 == null)
			throw new IllegalArgumentException ("Ma9 is mandatory.");
		set_Value (COLUMNNAME_Ma9, Ma9);
	}

	/** Get MA. September.
		@return GL
	  */
	public BigDecimal getMa9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ma9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set MA Sum.
		@param MaSum 
		GL
	  */
	public void setMaSum (BigDecimal MaSum)
	{
		if (MaSum == null)
			throw new IllegalArgumentException ("MaSum is mandatory.");
		set_Value (COLUMNNAME_MaSum, MaSum);
	}

	/** Get MA Sum.
		@return GL
	  */
	public BigDecimal getMaSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Pa. January.
		@param Pa1 
		GL
	  */
	public void setPa1 (BigDecimal Pa1)
	{
		if (Pa1 == null)
			throw new IllegalArgumentException ("Pa1 is mandatory.");
		set_Value (COLUMNNAME_Pa1, Pa1);
	}

	/** Get Pa. January.
		@return GL
	  */
	public BigDecimal getPa1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. October.
		@param Pa10 
		GL
	  */
	public void setPa10 (BigDecimal Pa10)
	{
		if (Pa10 == null)
			throw new IllegalArgumentException ("Pa10 is mandatory.");
		set_Value (COLUMNNAME_Pa10, Pa10);
	}

	/** Get Pa. October.
		@return GL
	  */
	public BigDecimal getPa10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. November.
		@param Pa11 
		GL
	  */
	public void setPa11 (BigDecimal Pa11)
	{
		if (Pa11 == null)
			throw new IllegalArgumentException ("Pa11 is mandatory.");
		set_Value (COLUMNNAME_Pa11, Pa11);
	}

	/** Get Pa. November.
		@return GL
	  */
	public BigDecimal getPa11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. December.
		@param Pa12 
		GL
	  */
	public void setPa12 (BigDecimal Pa12)
	{
		if (Pa12 == null)
			throw new IllegalArgumentException ("Pa12 is mandatory.");
		set_Value (COLUMNNAME_Pa12, Pa12);
	}

	/** Get Pa. December.
		@return GL
	  */
	public BigDecimal getPa12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. February.
		@param Pa2 
		GL
	  */
	public void setPa2 (BigDecimal Pa2)
	{
		if (Pa2 == null)
			throw new IllegalArgumentException ("Pa2 is mandatory.");
		set_Value (COLUMNNAME_Pa2, Pa2);
	}

	/** Get Pa. February.
		@return GL
	  */
	public BigDecimal getPa2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. March.
		@param Pa3 
		GL
	  */
	public void setPa3 (BigDecimal Pa3)
	{
		if (Pa3 == null)
			throw new IllegalArgumentException ("Pa3 is mandatory.");
		set_Value (COLUMNNAME_Pa3, Pa3);
	}

	/** Get Pa. March.
		@return GL
	  */
	public BigDecimal getPa3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. April.
		@param Pa4 
		GL
	  */
	public void setPa4 (BigDecimal Pa4)
	{
		if (Pa4 == null)
			throw new IllegalArgumentException ("Pa4 is mandatory.");
		set_Value (COLUMNNAME_Pa4, Pa4);
	}

	/** Get Pa. April.
		@return GL
	  */
	public BigDecimal getPa4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. May.
		@param Pa5 
		GL
	  */
	public void setPa5 (BigDecimal Pa5)
	{
		if (Pa5 == null)
			throw new IllegalArgumentException ("Pa5 is mandatory.");
		set_Value (COLUMNNAME_Pa5, Pa5);
	}

	/** Get Pa. May.
		@return GL
	  */
	public BigDecimal getPa5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. June.
		@param Pa6 
		GL
	  */
	public void setPa6 (BigDecimal Pa6)
	{
		if (Pa6 == null)
			throw new IllegalArgumentException ("Pa6 is mandatory.");
		set_Value (COLUMNNAME_Pa6, Pa6);
	}

	/** Get Pa. June.
		@return GL
	  */
	public BigDecimal getPa6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. July.
		@param Pa7 
		GL
	  */
	public void setPa7 (BigDecimal Pa7)
	{
		if (Pa7 == null)
			throw new IllegalArgumentException ("Pa7 is mandatory.");
		set_Value (COLUMNNAME_Pa7, Pa7);
	}

	/** Get Pa. July.
		@return GL
	  */
	public BigDecimal getPa7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. August.
		@param Pa8 
		GL
	  */
	public void setPa8 (BigDecimal Pa8)
	{
		if (Pa8 == null)
			throw new IllegalArgumentException ("Pa8 is mandatory.");
		set_Value (COLUMNNAME_Pa8, Pa8);
	}

	/** Get Pa. August.
		@return GL
	  */
	public BigDecimal getPa8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. September.
		@param Pa9 
		GL
	  */
	public void setPa9 (BigDecimal Pa9)
	{
		if (Pa9 == null)
			throw new IllegalArgumentException ("Pa9 is mandatory.");
		set_Value (COLUMNNAME_Pa9, Pa9);
	}

	/** Get Pa. September.
		@return GL
	  */
	public BigDecimal getPa9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Pa9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pa. Sum.
		@param PaSum 
		GL
	  */
	public void setPaSum (BigDecimal PaSum)
	{
		if (PaSum == null)
			throw new IllegalArgumentException ("PaSum is mandatory.");
		set_Value (COLUMNNAME_PaSum, PaSum);
	}

	/** Get Pa. Sum.
		@return GL
	  */
	public BigDecimal getPaSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PaSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. January.
		@param Rt1 
		GL
	  */
	public void setRt1 (BigDecimal Rt1)
	{
		if (Rt1 == null)
			throw new IllegalArgumentException ("Rt1 is mandatory.");
		set_Value (COLUMNNAME_Rt1, Rt1);
	}

	/** Get RT. January.
		@return GL
	  */
	public BigDecimal getRt1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. October.
		@param Rt10 
		GL
	  */
	public void setRt10 (BigDecimal Rt10)
	{
		if (Rt10 == null)
			throw new IllegalArgumentException ("Rt10 is mandatory.");
		set_Value (COLUMNNAME_Rt10, Rt10);
	}

	/** Get RT. October.
		@return GL
	  */
	public BigDecimal getRt10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT November.
		@param Rt11 
		GL
	  */
	public void setRt11 (BigDecimal Rt11)
	{
		if (Rt11 == null)
			throw new IllegalArgumentException ("Rt11 is mandatory.");
		set_Value (COLUMNNAME_Rt11, Rt11);
	}

	/** Get RT November.
		@return GL
	  */
	public BigDecimal getRt11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. December.
		@param Rt12 
		GL
	  */
	public void setRt12 (BigDecimal Rt12)
	{
		if (Rt12 == null)
			throw new IllegalArgumentException ("Rt12 is mandatory.");
		set_Value (COLUMNNAME_Rt12, Rt12);
	}

	/** Get RT. December.
		@return GL
	  */
	public BigDecimal getRt12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. February.
		@param Rt2 
		GL
	  */
	public void setRt2 (BigDecimal Rt2)
	{
		if (Rt2 == null)
			throw new IllegalArgumentException ("Rt2 is mandatory.");
		set_Value (COLUMNNAME_Rt2, Rt2);
	}

	/** Get RT. February.
		@return GL
	  */
	public BigDecimal getRt2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. March.
		@param Rt3 
		GL
	  */
	public void setRt3 (BigDecimal Rt3)
	{
		if (Rt3 == null)
			throw new IllegalArgumentException ("Rt3 is mandatory.");
		set_Value (COLUMNNAME_Rt3, Rt3);
	}

	/** Get RT. March.
		@return GL
	  */
	public BigDecimal getRt3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. April.
		@param Rt4 
		GL
	  */
	public void setRt4 (BigDecimal Rt4)
	{
		if (Rt4 == null)
			throw new IllegalArgumentException ("Rt4 is mandatory.");
		set_Value (COLUMNNAME_Rt4, Rt4);
	}

	/** Get RT. April.
		@return GL
	  */
	public BigDecimal getRt4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. May.
		@param Rt5 
		GL
	  */
	public void setRt5 (BigDecimal Rt5)
	{
		if (Rt5 == null)
			throw new IllegalArgumentException ("Rt5 is mandatory.");
		set_Value (COLUMNNAME_Rt5, Rt5);
	}

	/** Get RT. May.
		@return GL
	  */
	public BigDecimal getRt5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. June.
		@param Rt6 
		GL
	  */
	public void setRt6 (BigDecimal Rt6)
	{
		if (Rt6 == null)
			throw new IllegalArgumentException ("Rt6 is mandatory.");
		set_Value (COLUMNNAME_Rt6, Rt6);
	}

	/** Get RT. June.
		@return GL
	  */
	public BigDecimal getRt6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. July.
		@param Rt7 
		GL
	  */
	public void setRt7 (BigDecimal Rt7)
	{
		if (Rt7 == null)
			throw new IllegalArgumentException ("Rt7 is mandatory.");
		set_Value (COLUMNNAME_Rt7, Rt7);
	}

	/** Get RT. July.
		@return GL
	  */
	public BigDecimal getRt7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. August.
		@param Rt8 
		GL
	  */
	public void setRt8 (BigDecimal Rt8)
	{
		if (Rt8 == null)
			throw new IllegalArgumentException ("Rt8 is mandatory.");
		set_Value (COLUMNNAME_Rt8, Rt8);
	}

	/** Get RT. August.
		@return GL
	  */
	public BigDecimal getRt8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. September.
		@param Rt9 
		GL
	  */
	public void setRt9 (BigDecimal Rt9)
	{
		if (Rt9 == null)
			throw new IllegalArgumentException ("Rt9 is mandatory.");
		set_Value (COLUMNNAME_Rt9, Rt9);
	}

	/** Get RT. September.
		@return GL
	  */
	public BigDecimal getRt9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rt9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RT. Sum.
		@param RtSum 
		GL
	  */
	public void setRtSum (BigDecimal RtSum)
	{
		if (RtSum == null)
			throw new IllegalArgumentException ("RtSum is mandatory.");
		set_Value (COLUMNNAME_RtSum, RtSum);
	}

	/** Get RT. Sum.
		@return GL
	  */
	public BigDecimal getRtSum () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RtSum);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Tipo AD_Reference_ID=1200652 */
	public static final int TIPO_AD_Reference_ID=1200652;
	/** Purpose = FI */
	public static final String TIPO_Purpose = "FI";
	/** Function = FN */
	public static final String TIPO_Function = "FN";
	/** Subfunction = SF */
	public static final String TIPO_Subfunction = "SF";
	/** Unidad = UR */
	public static final String TIPO_Unidad = "UR";
	/** Reassignment = RG */
	public static final String TIPO_Reassignment = "RG";
	/** Contracts = AI */
	public static final String TIPO_Contracts = "AI";
	/** Prosupuestal = PP */
	public static final String TIPO_Prosupuestal = "PP";
	/** Institutional = PI */
	public static final String TIPO_Institutional = "PI";
	/** PTDA = PT */
	public static final String TIPO_PTDA = "PT";
	/** TipoGasto = TG */
	public static final String TIPO_TipoGasto = "TG";
	/**  FteFinanciamiento	 = FF */
	public static final String TIPO_FteFinanciamiento = "FF";
	/** Entidad = EF */
	public static final String TIPO_Entidad = "EF";
	/** Proyecto = PY */
	public static final String TIPO_Proyecto = "PY";
	/** Set Type.
		@param Tipo 
		GL
	  */
	public void setTipo (String Tipo)
	{
		if (Tipo == null) throw new IllegalArgumentException ("Tipo is mandatory");
		if (Tipo.equals("FI") || Tipo.equals("FN") || Tipo.equals("SF") || Tipo.equals("UR") || Tipo.equals("RG") || Tipo.equals("AI") || Tipo.equals("PP") || Tipo.equals("PI") || Tipo.equals("PT") || Tipo.equals("TG") || Tipo.equals("FF") || Tipo.equals("EF") || Tipo.equals("PY")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200652 - FI - FN - SF - UR - RG - AI - PP - PI - PT - TG - FF - EF - PY");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return GL
	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}