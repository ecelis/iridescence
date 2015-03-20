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

/** Generated Model for EXME_PresupuestoDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PresupuestoDet extends PO implements I_EXME_PresupuestoDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PresupuestoDet (Properties ctx, int EXME_PresupuestoDet_ID, String trxName)
    {
      super (ctx, EXME_PresupuestoDet_ID, trxName);
      /** if (EXME_PresupuestoDet_ID == 0)
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
			setEXME_PresupuestoDet_ID (0);
			setEXME_PresupuestoEgr_ID (0);
			setLine (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PresupuestoDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PresupuestoDet[")
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
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
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

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
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
			set_Value (COLUMNNAME_EXME_ActInstitucional_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_ClasFuncional_Fun_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_ClasFuncional_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_ClasFuncional_Sfu_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_FteFinanciamiento_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_PartidaPres_ID, null);
		else 
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

	/** Set Egress budget (Detail).
		@param EXME_PresupuestoDet_ID Egress budget (Detail)	  */
	public void setEXME_PresupuestoDet_ID (int EXME_PresupuestoDet_ID)
	{
		if (EXME_PresupuestoDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_PresupuestoDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PresupuestoDet_ID, Integer.valueOf(EXME_PresupuestoDet_ID));
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

	public I_EXME_PresupuestoModif getEXME_PresupuestoModif() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PresupuestoModif.Table_Name);
        I_EXME_PresupuestoModif result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PresupuestoModif)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PresupuestoModif_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Reallocation.
		@param EXME_PresupuestoModif_ID Reallocation	  */
	public void setEXME_PresupuestoModif_ID (int EXME_PresupuestoModif_ID)
	{
		if (EXME_PresupuestoModif_ID < 1) 
			set_Value (COLUMNNAME_EXME_PresupuestoModif_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PresupuestoModif_ID, Integer.valueOf(EXME_PresupuestoModif_ID));
	}

	/** Get Reallocation.
		@return Reallocation	  */
	public int getEXME_PresupuestoModif_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PresupuestoModif_ID);
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
			set_Value (COLUMNNAME_EXME_ProgInstitucional_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_ProgPresupuestal_ID, null);
		else 
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
			set_Value (COLUMNNAME_EXME_TipoGasto_ID, null);
		else 
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

		if (Tipo == null || Tipo.equals("FI") || Tipo.equals("FN") || Tipo.equals("SF") || Tipo.equals("UR") || Tipo.equals("RG") || Tipo.equals("AI") || Tipo.equals("PP") || Tipo.equals("PI") || Tipo.equals("PT") || Tipo.equals("TG") || Tipo.equals("FF") || Tipo.equals("EF") || Tipo.equals("PY")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200652 - FI - FN - SF - UR - RG - AI - PP - PI - PT - TG - FF - EF - PY");		set_Value (COLUMNNAME_Tipo, Tipo);
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