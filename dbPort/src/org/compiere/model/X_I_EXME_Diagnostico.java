/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_Diagnostico
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Diagnostico extends PO implements I_I_EXME_Diagnostico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Diagnostico (Properties ctx, int I_EXME_Diagnostico_ID, String trxName)
    {
      super (ctx, I_EXME_Diagnostico_ID, trxName);
      /** if (I_EXME_Diagnostico_ID == 0)
        {
			setI_EXME_Diagnostico_ID (0);
			setI_IsImported (false);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Diagnostico (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Diagnostico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Code GRD.
		@param CodGrd 
		Code GRD
	  */
	public void setCodGrd (String CodGrd)
	{
		set_Value (COLUMNNAME_CodGrd, CodGrd);
	}

	/** Get Code GRD.
		@return Code GRD
	  */
	public String getCodGrd () 
	{
		return (String)get_Value(COLUMNNAME_CodGrd);
	}

	/** Set Code INEGI.
		@param CodInegi 
		Code INEGI
	  */
	public void setCodInegi (String CodInegi)
	{
		set_Value (COLUMNNAME_CodInegi, CodInegi);
	}

	/** Get Code INEGI.
		@return Code INEGI
	  */
	public String getCodInegi () 
	{
		return (String)get_Value(COLUMNNAME_CodInegi);
	}

	/** Set World Organization Health Code.
		@param CodOms 
		World Organization Health Code
	  */
	public void setCodOms (String CodOms)
	{
		set_Value (COLUMNNAME_CodOms, CodOms);
	}

	/** Get World Organization Health Code.
		@return World Organization Health Code
	  */
	public String getCodOms () 
	{
		return (String)get_Value(COLUMNNAME_CodOms);
	}

	/** Set Snomed Code.
		@param CodSnomed 
		Snomed Code
	  */
	public void setCodSnomed (String CodSnomed)
	{
		set_Value (COLUMNNAME_CodSnomed, CodSnomed);
	}

	/** Get Snomed Code.
		@return Snomed Code
	  */
	public String getCodSnomed () 
	{
		return (String)get_Value(COLUMNNAME_CodSnomed);
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

	/** Set ICD.
		@param EXME_DiagnosticoHdr_ID 
		International Classification of Diseases
	  */
	public void setEXME_DiagnosticoHdr_ID (int EXME_DiagnosticoHdr_ID)
	{
		if (EXME_DiagnosticoHdr_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosticoHdr_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosticoHdr_ID, Integer.valueOf(EXME_DiagnosticoHdr_ID));
	}

	/** Get ICD.
		@return International Classification of Diseases
	  */
	public int getEXME_DiagnosticoHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
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

	/** Set Imported Diagnostic.
		@param I_EXME_Diagnostico_ID 
		Imported Diagnostic
	  */
	public void setI_EXME_Diagnostico_ID (int I_EXME_Diagnostico_ID)
	{
		if (I_EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Diagnostico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Diagnostico_ID, Integer.valueOf(I_EXME_Diagnostico_ID));
	}

	/** Get Imported Diagnostic.
		@return Imported Diagnostic
	  */
	public int getI_EXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Diagnostico_ID);
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

	/** Set Header Value.
		@param ValueHdr Header Value	  */
	public void setValueHdr (String ValueHdr)
	{
		set_Value (COLUMNNAME_ValueHdr, ValueHdr);
	}

	/** Get Header Value.
		@return Header Value	  */
	public String getValueHdr () 
	{
		return (String)get_Value(COLUMNNAME_ValueHdr);
	}
}