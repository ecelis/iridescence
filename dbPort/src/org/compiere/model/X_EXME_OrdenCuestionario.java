/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_OrdenCuestionario
 *  @author Generated Class 
 *  @version Release @VERSION@ - $Id$ */
public class X_EXME_OrdenCuestionario extends PO implements I_EXME_OrdenCuestionario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_OrdenCuestionario (Properties ctx, int EXME_OrdenCuestionario_ID, String trxName)
    {
      super (ctx, EXME_OrdenCuestionario_ID, trxName);
      /** if (EXME_OrdenCuestionario_ID == 0)
        {
			setEXME_OrdenCuestionario_ID (0);
			setEXME_TipoPregunta_ID (0);
			setIsSelected (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_OrdenCuestionario (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_OrdenCuestionario[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Order.
		@param EXME_OrdenCuestionario_ID Order	  */
	public void setEXME_OrdenCuestionario_ID (int EXME_OrdenCuestionario_ID)
	{
		if (EXME_OrdenCuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_OrdenCuestionario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_OrdenCuestionario_ID, Integer.valueOf(EXME_OrdenCuestionario_ID));
	}

	/** Get Order.
		@return Order	  */
	public int getEXME_OrdenCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_OrdenCuestionario_ID);
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
			 throw new IllegalArgumentException ("EXME_TipoPregunta_ID is mandatory.");
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
}