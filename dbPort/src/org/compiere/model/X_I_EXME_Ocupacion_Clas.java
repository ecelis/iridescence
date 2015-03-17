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

/** Generated Model for I_EXME_Ocupacion_Clas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Ocupacion_Clas extends PO implements I_I_EXME_Ocupacion_Clas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Ocupacion_Clas (Properties ctx, int I_EXME_Ocupacion_Clas_ID, String trxName)
    {
      super (ctx, I_EXME_Ocupacion_Clas_ID, trxName);
      /** if (I_EXME_Ocupacion_Clas_ID == 0)
        {
			setI_EXME_Ocupacion_Clas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Ocupacion_Clas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Ocupacion_Clas[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_Ocupacion_Clas getEXME_Ocupacion_Clas() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ocupacion_Clas.Table_Name);
        I_EXME_Ocupacion_Clas result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ocupacion_Clas)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ocupacion_Clas_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classified Ocupation.
		@param EXME_Ocupacion_Clas_ID 
		Classified Ocupation
	  */
	public void setEXME_Ocupacion_Clas_ID (int EXME_Ocupacion_Clas_ID)
	{
		if (EXME_Ocupacion_Clas_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ocupacion_Clas_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ocupacion_Clas_ID, Integer.valueOf(EXME_Ocupacion_Clas_ID));
	}

	/** Get Classified Ocupation.
		@return Classified Ocupation
	  */
	public int getEXME_Ocupacion_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Clas_ID);
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

	/** Set Occupation Class.
		@param I_EXME_Ocupacion_Clas_ID Occupation Class	  */
	public void setI_EXME_Ocupacion_Clas_ID (int I_EXME_Ocupacion_Clas_ID)
	{
		if (I_EXME_Ocupacion_Clas_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Ocupacion_Clas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Ocupacion_Clas_ID, Integer.valueOf(I_EXME_Ocupacion_Clas_ID));
	}

	/** Get Occupation Class.
		@return Occupation Class	  */
	public int getI_EXME_Ocupacion_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Ocupacion_Clas_ID);
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

	/** Set Score.
		@param Puntaje 
		Score
	  */
	public void setPuntaje (BigDecimal Puntaje)
	{
		set_Value (COLUMNNAME_Puntaje, Puntaje);
	}

	/** Get Score.
		@return Score
	  */
	public BigDecimal getPuntaje () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Puntaje);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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