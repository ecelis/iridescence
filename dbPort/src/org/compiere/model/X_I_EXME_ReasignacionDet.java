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

/** Generated Model for I_EXME_ReasignacionDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_ReasignacionDet extends PO implements I_I_EXME_ReasignacionDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_ReasignacionDet (Properties ctx, int I_EXME_ReasignacionDet_ID, String trxName)
    {
      super (ctx, I_EXME_ReasignacionDet_ID, trxName);
      /** if (I_EXME_ReasignacionDet_ID == 0)
        {
			setI_EXME_ReasignacionDet_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_ReasignacionDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_ReasignacionDet[")
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

	/** Set Reallocation.
		@param EXME_ReasignacionDet_ID Reallocation	  */
	public void setEXME_ReasignacionDet_ID (int EXME_ReasignacionDet_ID)
	{
		if (EXME_ReasignacionDet_ID < 1) 
			set_Value (COLUMNNAME_EXME_ReasignacionDet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ReasignacionDet_ID, Integer.valueOf(EXME_ReasignacionDet_ID));
	}

	/** Get Reallocation.
		@return Reallocation	  */
	public int getEXME_ReasignacionDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReasignacionDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Reallocation.
		@param I_EXME_ReasignacionDet_ID Reallocation	  */
	public void setI_EXME_ReasignacionDet_ID (int I_EXME_ReasignacionDet_ID)
	{
		if (I_EXME_ReasignacionDet_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_ReasignacionDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_ReasignacionDet_ID, Integer.valueOf(I_EXME_ReasignacionDet_ID));
	}

	/** Get Reallocation.
		@return Reallocation	  */
	public int getI_EXME_ReasignacionDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_ReasignacionDet_ID);
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

	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (boolean Tipo)
	{
		set_Value (COLUMNNAME_Tipo, Boolean.valueOf(Tipo));
	}

	/** Get Type.
		@return Type	  */
	public boolean isTipo () 
	{
		Object oo = get_Value(COLUMNNAME_Tipo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}