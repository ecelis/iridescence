/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_EXME_PresupuestoEgr
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_PresupuestoEgr extends PO implements I_I_EXME_PresupuestoEgr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_PresupuestoEgr (Properties ctx, int I_EXME_PresupuestoEgr_ID, String trxName)
    {
      super (ctx, I_EXME_PresupuestoEgr_ID, trxName);
      /** if (I_EXME_PresupuestoEgr_ID == 0)
        {
			setI_EXME_PresupuestoEgr_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_PresupuestoEgr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_PresupuestoEgr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Name "Institutional Activity".
		@param ActInsName Name "Institutional Activity"	  */
	public void setActInsName (String ActInsName)
	{
		set_Value (COLUMNNAME_ActInsName, ActInsName);
	}

	/** Get Name "Institutional Activity".
		@return Name "Institutional Activity"	  */
	public String getActInsName () 
	{
		return (String)get_Value(COLUMNNAME_ActInsName);
	}

	/** Set Value  "Institutional Activity".
		@param ActInsValue Value  "Institutional Activity"	  */
	public void setActInsValue (String ActInsValue)
	{
		set_Value (COLUMNNAME_ActInsValue, ActInsValue);
	}

	/** Get Value  "Institutional Activity".
		@return Value  "Institutional Activity"	  */
	public String getActInsValue () 
	{
		return (String)get_Value(COLUMNNAME_ActInsValue);
	}

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
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

	/** Set Code.
		@param Codigo Code	  */
	public void setCodigo (String Codigo)
	{
		set_Value (COLUMNNAME_Codigo, Codigo);
	}

	/** Get Code.
		@return Code	  */
	public String getCodigo () 
	{
		return (String)get_Value(COLUMNNAME_Codigo);
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

	public I_C_Year getC_Year() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Year.Table_Name);
        I_C_Year result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Year)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Year_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1) 
			set_Value (COLUMNNAME_C_Year_ID, null);
		else 
			set_Value (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
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

	/** Set State.
		@param Entidad 
		State
	  */
	public void setEntidad (String Entidad)
	{
		set_Value (COLUMNNAME_Entidad, Entidad);
	}

	/** Get State.
		@return State
	  */
	public String getEntidad () 
	{
		return (String)get_Value(COLUMNNAME_Entidad);
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
			set_ValueNoCheck (COLUMNNAME_EXME_PresupuestoEgr_ID, null);
		else 
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

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Finality.
		@param Finalidad Finality	  */
	public void setFinalidad (String Finalidad)
	{
		set_Value (COLUMNNAME_Finalidad, Finalidad);
	}

	/** Get Finality.
		@return Finality	  */
	public String getFinalidad () 
	{
		return (String)get_Value(COLUMNNAME_Finalidad);
	}

	/** Set Key finality.
		@param FinalidadClv 
		Key finality
	  */
	public void setFinalidadClv (String FinalidadClv)
	{
		set_Value (COLUMNNAME_FinalidadClv, FinalidadClv);
	}

	/** Get Key finality.
		@return Key finality
	  */
	public String getFinalidadClv () 
	{
		return (String)get_Value(COLUMNNAME_FinalidadClv);
	}

	/** Set Value "Source of Funding".
		@param FteFinValue Value "Source of Funding"	  */
	public void setFteFinValue (String FteFinValue)
	{
		set_Value (COLUMNNAME_FteFinValue, FteFinValue);
	}

	/** Get Value "Source of Funding".
		@return Value "Source of Funding"	  */
	public String getFteFinValue () 
	{
		return (String)get_Value(COLUMNNAME_FteFinValue);
	}

	/** Set Function.
		@param Funcion Function	  */
	public void setFuncion (String Funcion)
	{
		set_Value (COLUMNNAME_Funcion, Funcion);
	}

	/** Get Function.
		@return Function	  */
	public String getFuncion () 
	{
		return (String)get_Value(COLUMNNAME_Funcion);
	}

	/** Set Key function.
		@param FuncionClv Key function	  */
	public void setFuncionClv (String FuncionClv)
	{
		set_Value (COLUMNNAME_FuncionClv, FuncionClv);
	}

	/** Get Key function.
		@return Key function	  */
	public String getFuncionClv () 
	{
		return (String)get_Value(COLUMNNAME_FuncionClv);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Egress budget (Import).
		@param I_EXME_PresupuestoEgr_ID Egress budget (Import)	  */
	public void setI_EXME_PresupuestoEgr_ID (int I_EXME_PresupuestoEgr_ID)
	{
		if (I_EXME_PresupuestoEgr_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_PresupuestoEgr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_PresupuestoEgr_ID, Integer.valueOf(I_EXME_PresupuestoEgr_ID));
	}

	/** Get Egress budget (Import).
		@return Egress budget (Import)	  */
	public int getI_EXME_PresupuestoEgr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_PresupuestoEgr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Organization Key.
		@param OrgValue 
		Key of the Organization
	  */
	public void setOrgValue (String OrgValue)
	{
		set_Value (COLUMNNAME_OrgValue, OrgValue);
	}

	/** Get Organization Key.
		@return Key of the Organization
	  */
	public String getOrgValue () 
	{
		return (String)get_Value(COLUMNNAME_OrgValue);
	}

	/** Set Name "Budget Item".
		@param PartName Name "Budget Item"	  */
	public void setPartName (String PartName)
	{
		set_Value (COLUMNNAME_PartName, PartName);
	}

	/** Get Name "Budget Item".
		@return Name "Budget Item"	  */
	public String getPartName () 
	{
		return (String)get_Value(COLUMNNAME_PartName);
	}

	/** Set Value "Budget Item".
		@param PartValue Value "Budget Item"	  */
	public void setPartValue (String PartValue)
	{
		set_Value (COLUMNNAME_PartValue, PartValue);
	}

	/** Get Value "Budget Item".
		@return Value "Budget Item"	  */
	public String getPartValue () 
	{
		return (String)get_Value(COLUMNNAME_PartValue);
	}

	/** Set Value "Egress budget".
		@param PresupuestoValue 
		Value "Egress budget"
	  */
	public void setPresupuestoValue (String PresupuestoValue)
	{
		set_Value (COLUMNNAME_PresupuestoValue, PresupuestoValue);
	}

	/** Get Value "Egress budget".
		@return Value "Egress budget"
	  */
	public String getPresupuestoValue () 
	{
		return (String)get_Value(COLUMNNAME_PresupuestoValue);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name "Institutional program".
		@param ProgInsName Name "Institutional program"	  */
	public void setProgInsName (String ProgInsName)
	{
		set_Value (COLUMNNAME_ProgInsName, ProgInsName);
	}

	/** Get Name "Institutional program".
		@return Name "Institutional program"	  */
	public String getProgInsName () 
	{
		return (String)get_Value(COLUMNNAME_ProgInsName);
	}

	/** Set Value "Institutional program".
		@param ProgInsValue Value "Institutional program"	  */
	public void setProgInsValue (String ProgInsValue)
	{
		set_Value (COLUMNNAME_ProgInsValue, ProgInsValue);
	}

	/** Get Value "Institutional program".
		@return Value "Institutional program"	  */
	public String getProgInsValue () 
	{
		return (String)get_Value(COLUMNNAME_ProgInsValue);
	}

	/** Set Name "Budget programmes".
		@param ProgPresName Name "Budget programmes"	  */
	public void setProgPresName (String ProgPresName)
	{
		set_Value (COLUMNNAME_ProgPresName, ProgPresName);
	}

	/** Get Name "Budget programmes".
		@return Name "Budget programmes"	  */
	public String getProgPresName () 
	{
		return (String)get_Value(COLUMNNAME_ProgPresName);
	}

	/** Set Value "Budget programmes".
		@param ProgPresValue Value "Budget programmes"	  */
	public void setProgPresValue (String ProgPresValue)
	{
		set_Value (COLUMNNAME_ProgPresValue, ProgPresValue);
	}

	/** Get Value "Budget programmes".
		@return Value "Budget programmes"	  */
	public String getProgPresValue () 
	{
		return (String)get_Value(COLUMNNAME_ProgPresValue);
	}

	/** Set Project Key.
		@param ProjectValue 
		Key of the Project
	  */
	public void setProjectValue (String ProjectValue)
	{
		set_Value (COLUMNNAME_ProjectValue, ProjectValue);
	}

	/** Get Project Key.
		@return Key of the Project
	  */
	public String getProjectValue () 
	{
		return (String)get_Value(COLUMNNAME_ProjectValue);
	}

	/** Set Key Reallocation.
		@param ReasignacionValue Key Reallocation	  */
	public void setReasignacionValue (String ReasignacionValue)
	{
		set_Value (COLUMNNAME_ReasignacionValue, ReasignacionValue);
	}

	/** Get Key Reallocation.
		@return Key Reallocation	  */
	public String getReasignacionValue () 
	{
		return (String)get_Value(COLUMNNAME_ReasignacionValue);
	}

	/** Set Revision.
		@param Revision Revision	  */
	public void setRevision (BigDecimal Revision)
	{
		set_Value (COLUMNNAME_Revision, Revision);
	}

	/** Get Revision.
		@return Revision	  */
	public BigDecimal getRevision () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Revision);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Subfunction.
		@param Subfuncion 
		Subfunction
	  */
	public void setSubfuncion (String Subfuncion)
	{
		set_Value (COLUMNNAME_Subfuncion, Subfuncion);
	}

	/** Get Subfunction.
		@return Subfunction
	  */
	public String getSubfuncion () 
	{
		return (String)get_Value(COLUMNNAME_Subfuncion);
	}

	/** Set Subfunction key.
		@param SubfuncionClv 
		Subfunction key
	  */
	public void setSubfuncionClv (String SubfuncionClv)
	{
		set_Value (COLUMNNAME_SubfuncionClv, SubfuncionClv);
	}

	/** Get Subfunction key.
		@return Subfunction key
	  */
	public String getSubfuncionClv () 
	{
		return (String)get_Value(COLUMNNAME_SubfuncionClv);
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

	/** Set Value "Type of expenditure".
		@param TipoGastoValue Value "Type of expenditure"	  */
	public void setTipoGastoValue (String TipoGastoValue)
	{
		set_Value (COLUMNNAME_TipoGastoValue, TipoGastoValue);
	}

	/** Get Value "Type of expenditure".
		@return Value "Type of expenditure"	  */
	public String getTipoGastoValue () 
	{
		return (String)get_Value(COLUMNNAME_TipoGastoValue);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (String Version)
	{
		set_Value (COLUMNNAME_Version, Version);
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public String getVersion () 
	{
		return (String)get_Value(COLUMNNAME_Version);
	}

	/** Set Year.
		@param Year 
		Calendar Year
	  */
	public void setYear (String Year)
	{
		set_Value (COLUMNNAME_Year, Year);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public String getYear () 
	{
		return (String)get_Value(COLUMNNAME_Year);
	}
}