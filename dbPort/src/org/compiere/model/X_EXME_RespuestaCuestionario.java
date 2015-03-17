/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RespuestaCuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_RespuestaCuestionario extends PO implements I_EXME_RespuestaCuestionario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RespuestaCuestionario (Properties ctx, int EXME_RespuestaCuestionario_ID, String trxName)
    {
      super (ctx, EXME_RespuestaCuestionario_ID, trxName);
      /** if (EXME_RespuestaCuestionario_ID == 0)
        {
			setAD_Table_ID (0);
			setEXME_Cuestionario_ID (0);
			setEXME_Pregunta_ID (0);
			setEXME_RespuestaCuestionario_ID (0);
			setIsPhysician (false);
			setRecord_ID (0);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_RespuestaCuestionario (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RespuestaCuestionario[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
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
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
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

	public I_EXME_Ejecucion_Cuest getEXME_Ejecucion_Cuest() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ejecucion_Cuest.Table_Name);
        I_EXME_Ejecucion_Cuest result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ejecucion_Cuest)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ejecucion_Cuest_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Execution of Questionnaire.
		@param EXME_Ejecucion_Cuest_ID Execution of Questionnaire	  */
	public void setEXME_Ejecucion_Cuest_ID (int EXME_Ejecucion_Cuest_ID)
	{
		if (EXME_Ejecucion_Cuest_ID < 1) 
			set_Value (COLUMNNAME_EXME_Ejecucion_Cuest_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Ejecucion_Cuest_ID, Integer.valueOf(EXME_Ejecucion_Cuest_ID));
	}

	/** Get Execution of Questionnaire.
		@return Execution of Questionnaire	  */
	public int getEXME_Ejecucion_Cuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ejecucion_Cuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_OrderSet.Table_Name);
        I_EXME_OrderSet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_OrderSet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_OrderSet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order Set.
		@param EXME_OrderSet_ID Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID)
	{
		if (EXME_OrderSet_ID < 1) 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_OrderSet_ID, Integer.valueOf(EXME_OrderSet_ID));
	}

	/** Get Order Set.
		@return Order Set	  */
	public int getEXME_OrderSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrderSet_ID);
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
			 throw new IllegalArgumentException ("EXME_Pregunta_ID is mandatory.");
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

	/** Set Anwser.
		@param EXME_RespuestaCuestionario_ID Anwser	  */
	public void setEXME_RespuestaCuestionario_ID (int EXME_RespuestaCuestionario_ID)
	{
		if (EXME_RespuestaCuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_RespuestaCuestionario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RespuestaCuestionario_ID, Integer.valueOf(EXME_RespuestaCuestionario_ID));
	}

	/** Get Anwser.
		@return Anwser	  */
	public int getEXME_RespuestaCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RespuestaCuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Content.
		@param FileContent File Content	  */
	public void setFileContent (byte[] FileContent)
	{
		set_Value (COLUMNNAME_FileContent, FileContent);
	}

	/** Get File Content.
		@return File Content	  */
	public byte[] getFileContent () 
	{
		return (byte[])get_Value(COLUMNNAME_FileContent);
	}

	/** Set Physician.
		@param IsPhysician 
		Indicates whether is a physician or not
	  */
	public void setIsPhysician (boolean IsPhysician)
	{
		set_Value (COLUMNNAME_IsPhysician, Boolean.valueOf(IsPhysician));
	}

	/** Get Physician.
		@return Indicates whether is a physician or not
	  */
	public boolean isPhysician () 
	{
		Object oo = get_Value(COLUMNNAME_IsPhysician);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Multiple.
		@param Multiple Multiple	  */
	public void setMultiple (boolean Multiple)
	{
		set_Value (COLUMNNAME_Multiple, Boolean.valueOf(Multiple));
	}

	/** Get Multiple.
		@return Multiple	  */
	public boolean isMultiple () 
	{
		Object oo = get_Value(COLUMNNAME_Multiple);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0)
			 throw new IllegalArgumentException ("Record_ID is mandatory.");
		set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
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

	/** Set Binary Text.
		@param TextBinary Binary Text	  */
	public void setTextBinary (String TextBinary)
	{
		set_Value (COLUMNNAME_TextBinary, TextBinary);
	}

	/** Get Binary Text.
		@return Binary Text	  */
	public String getTextBinary () 
	{
		return (String)get_Value(COLUMNNAME_TextBinary);
	}
}