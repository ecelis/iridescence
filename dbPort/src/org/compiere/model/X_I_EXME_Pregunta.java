/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_Pregunta
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_Pregunta extends PO implements I_I_EXME_Pregunta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_Pregunta (Properties ctx, int I_EXME_Pregunta_ID, String trxName)
    {
      super (ctx, I_EXME_Pregunta_ID, trxName);
      /** if (I_EXME_Pregunta_ID == 0)
        {
			setEspecialidad_Value (null);
			setI_EXME_Pregunta_ID (0);
			setPregunta_Name (null);
			setPregunta_TipoDato (null);
			setPregunta_Value (null);
			setTipoPregunta_Name (null);
			setTipoPregunta_Value (null);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_Pregunta (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_Pregunta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Specialty Value.
		@param Especialidad_Value 
		Specialty search value
	  */
	public void setEspecialidad_Value (String Especialidad_Value)
	{
		if (Especialidad_Value == null)
			throw new IllegalArgumentException ("Especialidad_Value is mandatory.");
		set_Value (COLUMNNAME_Especialidad_Value, Especialidad_Value);
	}

	/** Get Specialty Value.
		@return Specialty search value
	  */
	public String getEspecialidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_Especialidad_Value);
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pregunta.Table_Name);
        I_EXME_Pregunta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pregunta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pregunta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Question.
		@param EXME_Pregunta_ID 
		Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID)
	{
		if (EXME_Pregunta_ID < 1) 
			set_Value (COLUMNNAME_EXME_Pregunta_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Pregunta_ID, Integer.valueOf(EXME_Pregunta_ID));
	}

	/** Get Question.
		@return Question
	  */
	public int getEXME_Pregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pregunta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Pregunta_Lista getEXME_Pregunta_Lista() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pregunta_Lista.Table_Name);
        I_EXME_Pregunta_Lista result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pregunta_Lista)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pregunta_Lista_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Fixed Answer.
		@param EXME_Pregunta_Lista_ID 
		Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID)
	{
		if (EXME_Pregunta_Lista_ID < 1) 
			set_Value (COLUMNNAME_EXME_Pregunta_Lista_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Pregunta_Lista_ID, Integer.valueOf(EXME_Pregunta_Lista_ID));
	}

	/** Get Fixed Answer.
		@return Fixed answer for the question in the clinic questionnaire
	  */
	public int getEXME_Pregunta_Lista_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pregunta_Lista_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TipoPregunta getEXME_TipoPregunta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoPregunta.Table_Name);
        I_EXME_TipoPregunta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoPregunta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoPregunta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Question.
		@param EXME_TipoPregunta_ID 
		Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID)
	{
		if (EXME_TipoPregunta_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoPregunta_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoPregunta_ID, Integer.valueOf(EXME_TipoPregunta_ID));
	}

	/** Get Type of Question.
		@return Type of Question
	  */
	public int getEXME_TipoPregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPregunta_ID);
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

	/** Set Questionnaires import interface.
		@param I_EXME_Pregunta_ID 
		Questionnaires import interface
	  */
	public void setI_EXME_Pregunta_ID (int I_EXME_Pregunta_ID)
	{
		if (I_EXME_Pregunta_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_Pregunta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_Pregunta_ID, Integer.valueOf(I_EXME_Pregunta_ID));
	}

	/** Get Questionnaires import interface.
		@return Questionnaires import interface
	  */
	public int getI_EXME_Pregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_Pregunta_ID);
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

	/** Set Question Description.
		@param Pregunta_Description 
		Question Description
	  */
	public void setPregunta_Description (String Pregunta_Description)
	{
		set_Value (COLUMNNAME_Pregunta_Description, Pregunta_Description);
	}

	/** Get Question Description.
		@return Question Description
	  */
	public String getPregunta_Description () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Description);
	}

	/** Set Question's Fixed Answer Description.
		@param Pregunta_Lista_Description 
		Question's Fixed Answer Description
	  */
	public void setPregunta_Lista_Description (String Pregunta_Lista_Description)
	{
		set_Value (COLUMNNAME_Pregunta_Lista_Description, Pregunta_Lista_Description);
	}

	/** Get Question's Fixed Answer Description.
		@return Question's Fixed Answer Description
	  */
	public String getPregunta_Lista_Description () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Lista_Description);
	}

	/** Set Question's Fixed Answer Name.
		@param Pregunta_Lista_Name 
		Question's Fixed Answer Name
	  */
	public void setPregunta_Lista_Name (String Pregunta_Lista_Name)
	{
		set_Value (COLUMNNAME_Pregunta_Lista_Name, Pregunta_Lista_Name);
	}

	/** Get Question's Fixed Answer Name.
		@return Question's Fixed Answer Name
	  */
	public String getPregunta_Lista_Name () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Lista_Name);
	}

	/** Set Question's Fixed Answer Value.
		@param Pregunta_Lista_Value 
		Question's Fixed Answer Value
	  */
	public void setPregunta_Lista_Value (String Pregunta_Lista_Value)
	{
		set_Value (COLUMNNAME_Pregunta_Lista_Value, Pregunta_Lista_Value);
	}

	/** Get Question's Fixed Answer Value.
		@return Question's Fixed Answer Value
	  */
	public String getPregunta_Lista_Value () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Lista_Value);
	}

	/** Set Question's Name.
		@param Pregunta_Name 
		Question's Name
	  */
	public void setPregunta_Name (String Pregunta_Name)
	{
		if (Pregunta_Name == null)
			throw new IllegalArgumentException ("Pregunta_Name is mandatory.");
		set_Value (COLUMNNAME_Pregunta_Name, Pregunta_Name);
	}

	/** Get Question's Name.
		@return Question's Name
	  */
	public String getPregunta_Name () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Name);
	}

	/** Pregunta_TipoDato AD_Reference_ID=1000030 */
	public static final int PREGUNTA_TIPODATO_AD_Reference_ID=1000030;
	/** Decimal = D */
	public static final String PREGUNTA_TIPODATO_Decimal = "D";
	/** Integer = E */
	public static final String PREGUNTA_TIPODATO_Integer = "E";
	/** Text = T */
	public static final String PREGUNTA_TIPODATO_Text = "T";
	/** Date = F */
	public static final String PREGUNTA_TIPODATO_Date = "F";
	/** Image = I */
	public static final String PREGUNTA_TIPODATO_Image = "I";
	/** Logial = L */
	public static final String PREGUNTA_TIPODATO_Logial = "L";
	/** Option List = C */
	public static final String PREGUNTA_TIPODATO_OptionList = "C";
	/** Text Area = A */
	public static final String PREGUNTA_TIPODATO_TextArea = "A";
	/** Image Binary = B */
	public static final String PREGUNTA_TIPODATO_ImageBinary = "B";
	/** Multi Options = O */
	public static final String PREGUNTA_TIPODATO_MultiOptions = "O";
	/** Fixed Image = X */
	public static final String PREGUNTA_TIPODATO_FixedImage = "X";
	/** Set Question's Data Type.
		@param Pregunta_TipoDato 
		Question's Data Type
	  */
	public void setPregunta_TipoDato (String Pregunta_TipoDato)
	{
		if (Pregunta_TipoDato == null) throw new IllegalArgumentException ("Pregunta_TipoDato is mandatory");
		if (Pregunta_TipoDato.equals("D") || Pregunta_TipoDato.equals("E") || Pregunta_TipoDato.equals("T") || Pregunta_TipoDato.equals("F") || Pregunta_TipoDato.equals("I") || Pregunta_TipoDato.equals("L") || Pregunta_TipoDato.equals("C") || Pregunta_TipoDato.equals("A") || Pregunta_TipoDato.equals("B") || Pregunta_TipoDato.equals("O") || Pregunta_TipoDato.equals("X")); else throw new IllegalArgumentException ("Pregunta_TipoDato Invalid value - " + Pregunta_TipoDato + " - Reference_ID=1000030 - D - E - T - F - I - L - C - A - B - O - X");		set_Value (COLUMNNAME_Pregunta_TipoDato, Pregunta_TipoDato);
	}

	/** Get Question's Data Type.
		@return Question's Data Type
	  */
	public String getPregunta_TipoDato () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_TipoDato);
	}

	/** Set Question's Value.
		@param Pregunta_Value 
		Question's Value
	  */
	public void setPregunta_Value (String Pregunta_Value)
	{
		if (Pregunta_Value == null)
			throw new IllegalArgumentException ("Pregunta_Value is mandatory.");
		set_Value (COLUMNNAME_Pregunta_Value, Pregunta_Value);
	}

	/** Get Question's Value.
		@return Question's Value
	  */
	public String getPregunta_Value () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Value);
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

	/** Set Question Type's Description.
		@param TipoPregunta_Description 
		Question Type's Description
	  */
	public void setTipoPregunta_Description (String TipoPregunta_Description)
	{
		set_Value (COLUMNNAME_TipoPregunta_Description, TipoPregunta_Description);
	}

	/** Get Question Type's Description.
		@return Question Type's Description
	  */
	public String getTipoPregunta_Description () 
	{
		return (String)get_Value(COLUMNNAME_TipoPregunta_Description);
	}

	/** Set Question Type's Name.
		@param TipoPregunta_Name 
		Question Type's Name
	  */
	public void setTipoPregunta_Name (String TipoPregunta_Name)
	{
		if (TipoPregunta_Name == null)
			throw new IllegalArgumentException ("TipoPregunta_Name is mandatory.");
		set_Value (COLUMNNAME_TipoPregunta_Name, TipoPregunta_Name);
	}

	/** Get Question Type's Name.
		@return Question Type's Name
	  */
	public String getTipoPregunta_Name () 
	{
		return (String)get_Value(COLUMNNAME_TipoPregunta_Name);
	}

	/** Set Question Type's Value.
		@param TipoPregunta_Value 
		Question Type's Value
	  */
	public void setTipoPregunta_Value (String TipoPregunta_Value)
	{
		if (TipoPregunta_Value == null)
			throw new IllegalArgumentException ("TipoPregunta_Value is mandatory.");
		set_Value (COLUMNNAME_TipoPregunta_Value, TipoPregunta_Value);
	}

	/** Get Question Type's Value.
		@return Question Type's Value
	  */
	public String getTipoPregunta_Value () 
	{
		return (String)get_Value(COLUMNNAME_TipoPregunta_Value);
	}
}