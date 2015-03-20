/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for I_EXME_GrupoCuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_GrupoCuestionario extends PO implements I_I_EXME_GrupoCuestionario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_GrupoCuestionario (Properties ctx, int I_EXME_GrupoCuestionario_ID, String trxName)
    {
      super (ctx, I_EXME_GrupoCuestionario_ID, trxName);
      /** if (I_EXME_GrupoCuestionario_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_I_EXME_GrupoCuestionario (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_GrupoCuestionario[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** DataType AD_Reference_ID=1000030 */
	public static final int DATATYPE_AD_Reference_ID=1000030;
	/** Decimal = D */
	public static final String DATATYPE_Decimal = "D";
	/** Integer = E */
	public static final String DATATYPE_Integer = "E";
	/** Text = T */
	public static final String DATATYPE_Text = "T";
	/** Date = F */
	public static final String DATATYPE_Date = "F";
	/** Image = I */
	public static final String DATATYPE_Image = "I";
	/** Logial = L */
	public static final String DATATYPE_Logial = "L";
	/** Option List = C */
	public static final String DATATYPE_OptionList = "C";
	/** Text Area = A */
	public static final String DATATYPE_TextArea = "A";
	/** Image Binary = B */
	public static final String DATATYPE_ImageBinary = "B";
	/** Multi Options = O */
	public static final String DATATYPE_MultiOptions = "O";
	/** Fixed Image = X */
	public static final String DATATYPE_FixedImage = "X";
	/** Set Data Type.
		@param DataType 
		Type of data
	  */
	public void setDataType (String DataType)
	{

		if (DataType == null || DataType.equals("D") || DataType.equals("E") || DataType.equals("T") || DataType.equals("F") || DataType.equals("I") || DataType.equals("L") || DataType.equals("C") || DataType.equals("A") || DataType.equals("B") || DataType.equals("O") || DataType.equals("X")); else throw new IllegalArgumentException ("DataType Invalid value - " + DataType + " - Reference_ID=1000030 - D - E - T - F - I - L - C - A - B - O - X");		set_Value (COLUMNNAME_DataType, DataType);
	}

	/** Get Data Type.
		@return Type of data
	  */
	public String getDataType () 
	{
		return (String)get_Value(COLUMNNAME_DataType);
	}

	public I_EXME_CuestionarioDt getEXME_CuestionarioDt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CuestionarioDt.Table_Name);
        I_EXME_CuestionarioDt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CuestionarioDt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CuestionarioDt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set DT Questinnaire.
		@param EXME_CuestionarioDt_ID 
		DT Questionnaire
	  */
	public void setEXME_CuestionarioDt_ID (int EXME_CuestionarioDt_ID)
	{
		if (EXME_CuestionarioDt_ID < 1) 
			set_Value (COLUMNNAME_EXME_CuestionarioDt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CuestionarioDt_ID, Integer.valueOf(EXME_CuestionarioDt_ID));
	}

	/** Get DT Questinnaire.
		@return DT Questionnaire
	  */
	public int getEXME_CuestionarioDt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestionarioDt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuestionario.Table_Name);
        I_EXME_Cuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Specialty.
		@param EXME_Especialidad2_ID Specialty	  */
	public void setEXME_Especialidad2_ID (int EXME_Especialidad2_ID)
	{
		if (EXME_Especialidad2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad2_ID, Integer.valueOf(EXME_Especialidad2_ID));
	}

	/** Get Specialty.
		@return Specialty	  */
	public int getEXME_Especialidad2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GrupoCuestionarioDet getEXME_GrupoCuestionarioDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoCuestionarioDet.Table_Name);
        I_EXME_GrupoCuestionarioDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoCuestionarioDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoCuestionarioDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Group Detail.
		@param EXME_GrupoCuestionarioDet_ID 
		Form Group Detail
	  */
	public void setEXME_GrupoCuestionarioDet_ID (int EXME_GrupoCuestionarioDet_ID)
	{
		if (EXME_GrupoCuestionarioDet_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionarioDet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionarioDet_ID, Integer.valueOf(EXME_GrupoCuestionarioDet_ID));
	}

	/** Get Form Group Detail.
		@return Form Group Detail
	  */
	public int getEXME_GrupoCuestionarioDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionarioDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoCuestionario.Table_Name);
        I_EXME_GrupoCuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoCuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoCuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Group.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
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

	/** Set Form Description.
		@param FormDescription Form Description	  */
	public void setFormDescription (String FormDescription)
	{
		set_Value (COLUMNNAME_FormDescription, FormDescription);
	}

	/** Get Form Description.
		@return Form Description	  */
	public String getFormDescription () 
	{
		return (String)get_Value(COLUMNNAME_FormDescription);
	}

	/** Set Form Detail Sequence.
		@param FormDetSeq 
		Form Detail Sequence
	  */
	public void setFormDetSeq (int FormDetSeq)
	{
		set_Value (COLUMNNAME_FormDetSeq, Integer.valueOf(FormDetSeq));
	}

	/** Get Form Detail Sequence.
		@return Form Detail Sequence
	  */
	public int getFormDetSeq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FormDetSeq);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Form Group Detail Sequence.
		@param FormGroupDetSeq 
		Form Group Detail Sequence
	  */
	public void setFormGroupDetSeq (int FormGroupDetSeq)
	{
		set_Value (COLUMNNAME_FormGroupDetSeq, Integer.valueOf(FormGroupDetSeq));
	}

	/** Get Form Group Detail Sequence.
		@return Form Group Detail Sequence
	  */
	public int getFormGroupDetSeq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FormGroupDetSeq);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Form Group Name.
		@param FormGroupName Form Group Name	  */
	public void setFormGroupName (String FormGroupName)
	{
		set_Value (COLUMNNAME_FormGroupName, FormGroupName);
	}

	/** Get Form Group Name.
		@return Form Group Name	  */
	public String getFormGroupName () 
	{
		return (String)get_Value(COLUMNNAME_FormGroupName);
	}

	/** Set Form Name.
		@param FormName Form Name	  */
	public void setFormName (String FormName)
	{
		set_Value (COLUMNNAME_FormName, FormName);
	}

	/** Get Form Name.
		@return Form Name	  */
	public String getFormName () 
	{
		return (String)get_Value(COLUMNNAME_FormName);
	}

	/** Set Form Value.
		@param FormValue Form Value	  */
	public void setFormValue (String FormValue)
	{
		set_Value (COLUMNNAME_FormValue, FormValue);
	}

	/** Get Form Value.
		@return Form Value	  */
	public String getFormValue () 
	{
		return (String)get_Value(COLUMNNAME_FormValue);
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

	/** Set Form Group Import.
		@param I_EXME_GrupoCuestionario_ID 
		Form Group Import
	  */
	public void setI_EXME_GrupoCuestionario_ID (int I_EXME_GrupoCuestionario_ID)
	{
		if (I_EXME_GrupoCuestionario_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_EXME_GrupoCuestionario_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_EXME_GrupoCuestionario_ID, Integer.valueOf(I_EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group Import.
		@return Form Group Import
	  */
	public int getI_EXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_GrupoCuestionario_ID);
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

	/** Set Multi System.
		@param isMultiSys 
		Indicates whether or not is multi system
	  */
	public void setisMultiSys (boolean isMultiSys)
	{
		set_Value (COLUMNNAME_isMultiSys, Boolean.valueOf(isMultiSys));
	}

	/** Get Multi System.
		@return Indicates whether or not is multi system
	  */
	public boolean isMultiSys () 
	{
		Object oo = get_Value(COLUMNNAME_isMultiSys);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Selected.
		@param IsSelected Selected	  */
	public void setIsSelected (boolean IsSelected)
	{
		set_Value (COLUMNNAME_IsSelected, Boolean.valueOf(IsSelected));
	}

	/** Get Selected.
		@return Selected	  */
	public boolean isSelected () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelected);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set List Value Description.
		@param ListDesc 
		List Value Description
	  */
	public void setListDesc (String ListDesc)
	{
		set_Value (COLUMNNAME_ListDesc, ListDesc);
	}

	/** Get List Value Description.
		@return List Value Description
	  */
	public String getListDesc () 
	{
		return (String)get_Value(COLUMNNAME_ListDesc);
	}

	/** Set List Value Name.
		@param ListName 
		List Value Name
	  */
	public void setListName (String ListName)
	{
		set_Value (COLUMNNAME_ListName, ListName);
	}

	/** Get List Value Name.
		@return List Value Name
	  */
	public String getListName () 
	{
		return (String)get_Value(COLUMNNAME_ListName);
	}

	/** Set List Value Key.
		@param ListValue 
		List Value Key
	  */
	public void setListValue (String ListValue)
	{
		set_Value (COLUMNNAME_ListValue, ListValue);
	}

	/** Get List Value Key.
		@return List Value Key
	  */
	public String getListValue () 
	{
		return (String)get_Value(COLUMNNAME_ListValue);
	}

	/** Set Only Answer.
		@param OnlyAnswer Only Answer	  */
	public void setOnlyAnswer (boolean OnlyAnswer)
	{
		set_Value (COLUMNNAME_OnlyAnswer, Boolean.valueOf(OnlyAnswer));
	}

	/** Get Only Answer.
		@return Only Answer	  */
	public boolean isOnlyAnswer () 
	{
		Object oo = get_Value(COLUMNNAME_OnlyAnswer);
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

	/** Set Question Description.
		@param QuestionDesc 
		Question Description
	  */
	public void setQuestionDesc (String QuestionDesc)
	{
		set_Value (COLUMNNAME_QuestionDesc, QuestionDesc);
	}

	/** Get Question Description.
		@return Question Description
	  */
	public String getQuestionDesc () 
	{
		return (String)get_Value(COLUMNNAME_QuestionDesc);
	}

	/** Set Public Domain.
		@param QuestionEsCore 
		Public Domain
	  */
	public void setQuestionEsCore (boolean QuestionEsCore)
	{
		set_Value (COLUMNNAME_QuestionEsCore, Boolean.valueOf(QuestionEsCore));
	}

	/** Get Public Domain.
		@return Public Domain
	  */
	public boolean isQuestionEsCore () 
	{
		Object oo = get_Value(COLUMNNAME_QuestionEsCore);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory Question.
		@param QuestionMandatory 
		Mandatory Question
	  */
	public void setQuestionMandatory (boolean QuestionMandatory)
	{
		set_Value (COLUMNNAME_QuestionMandatory, Boolean.valueOf(QuestionMandatory));
	}

	/** Get Mandatory Question.
		@return Mandatory Question
	  */
	public boolean isQuestionMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_QuestionMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Question Name.
		@param QuestionName 
		Question Name
	  */
	public void setQuestionName (String QuestionName)
	{
		set_Value (COLUMNNAME_QuestionName, QuestionName);
	}

	/** Get Question Name.
		@return Question Name
	  */
	public String getQuestionName () 
	{
		return (String)get_Value(COLUMNNAME_QuestionName);
	}

	/** Set Question Specialty Description.
		@param QuestionSpecDesc 
		Question Specialty Description
	  */
	public void setQuestionSpecDesc (String QuestionSpecDesc)
	{
		set_Value (COLUMNNAME_QuestionSpecDesc, QuestionSpecDesc);
	}

	/** Get Question Specialty Description.
		@return Question Specialty Description
	  */
	public String getQuestionSpecDesc () 
	{
		return (String)get_Value(COLUMNNAME_QuestionSpecDesc);
	}

	/** Set Question Specialty Name.
		@param QuestionSpecName 
		Question Specialty Name
	  */
	public void setQuestionSpecName (String QuestionSpecName)
	{
		set_Value (COLUMNNAME_QuestionSpecName, QuestionSpecName);
	}

	/** Get Question Specialty Name.
		@return Question Specialty Name
	  */
	public String getQuestionSpecName () 
	{
		return (String)get_Value(COLUMNNAME_QuestionSpecName);
	}

	/** Set Question Specialty Key.
		@param QuestionSpecValue 
		Question Specialty Key
	  */
	public void setQuestionSpecValue (String QuestionSpecValue)
	{
		set_Value (COLUMNNAME_QuestionSpecValue, QuestionSpecValue);
	}

	/** Get Question Specialty Key.
		@return Question Specialty Key
	  */
	public String getQuestionSpecValue () 
	{
		return (String)get_Value(COLUMNNAME_QuestionSpecValue);
	}

	/** Set Question Key.
		@param QuestionValue 
		Question Key
	  */
	public void setQuestionValue (String QuestionValue)
	{
		set_Value (COLUMNNAME_QuestionValue, QuestionValue);
	}

	/** Get Question Key.
		@return Question Key
	  */
	public String getQuestionValue () 
	{
		return (String)get_Value(COLUMNNAME_QuestionValue);
	}

	/** Set Question Type Description.
		@param QuestTypeDesc 
		Question Type Description
	  */
	public void setQuestTypeDesc (String QuestTypeDesc)
	{
		set_Value (COLUMNNAME_QuestTypeDesc, QuestTypeDesc);
	}

	/** Get Question Type Description.
		@return Question Type Description
	  */
	public String getQuestTypeDesc () 
	{
		return (String)get_Value(COLUMNNAME_QuestTypeDesc);
	}

	/** Set Question Type Name.
		@param QuestTypeName 
		Question Type Name
	  */
	public void setQuestTypeName (String QuestTypeName)
	{
		set_Value (COLUMNNAME_QuestTypeName, QuestTypeName);
	}

	/** Get Question Type Name.
		@return Question Type Name
	  */
	public String getQuestTypeName () 
	{
		return (String)get_Value(COLUMNNAME_QuestTypeName);
	}

	/** Set Question Type Key.
		@param QuestTypeValue 
		Question Type Key
	  */
	public void setQuestTypeValue (String QuestTypeValue)
	{
		set_Value (COLUMNNAME_QuestTypeValue, QuestTypeValue);
	}

	/** Get Question Type Key.
		@return Question Type Key
	  */
	public String getQuestTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_QuestTypeValue);
	}

	/** Set Score.
		@param Score Score	  */
	public void setScore (int Score)
	{
		set_Value (COLUMNNAME_Score, Integer.valueOf(Score));
	}

	/** Get Score.
		@return Score	  */
	public int getScore () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Score);
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

	/** Set  Single System Specialities.
		@param SingleSysEsp 
		Specialities for Single System
	  */
	public void setSingleSysEsp (String SingleSysEsp)
	{
		set_Value (COLUMNNAME_SingleSysEsp, SingleSysEsp);
	}

	/** Get  Single System Specialities.
		@return Specialities for Single System
	  */
	public String getSingleSysEsp () 
	{
		return (String)get_Value(COLUMNNAME_SingleSysEsp);
	}

	/** Set Specialty Description.
		@param SpecialtyDesc 
		Specialty Description
	  */
	public void setSpecialtyDesc (String SpecialtyDesc)
	{
		set_Value (COLUMNNAME_SpecialtyDesc, SpecialtyDesc);
	}

	/** Get Specialty Description.
		@return Specialty Description
	  */
	public String getSpecialtyDesc () 
	{
		return (String)get_Value(COLUMNNAME_SpecialtyDesc);
	}

	/** Set Specialty Name.
		@param SpecialtyName 
		Specialty Name
	  */
	public void setSpecialtyName (String SpecialtyName)
	{
		set_Value (COLUMNNAME_SpecialtyName, SpecialtyName);
	}

	/** Get Specialty Name.
		@return Specialty Name
	  */
	public String getSpecialtyName () 
	{
		return (String)get_Value(COLUMNNAME_SpecialtyName);
	}

	/** Set Specialty Key.
		@param SpecialtyValue 
		Specialty Key
	  */
	public void setSpecialtyValue (String SpecialtyValue)
	{
		set_Value (COLUMNNAME_SpecialtyValue, SpecialtyValue);
	}

	/** Get Specialty Key.
		@return Specialty Key
	  */
	public String getSpecialtyValue () 
	{
		return (String)get_Value(COLUMNNAME_SpecialtyValue);
	}

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{

		if (TipoArea == null || TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}
}