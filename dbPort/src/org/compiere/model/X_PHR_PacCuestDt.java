/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for PHR_PacCuestDt
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacCuestDt extends PO implements I_PHR_PacCuestDt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacCuestDt (Properties ctx, int PHR_PacCuestDt_ID, String trxName)
    {
      super (ctx, PHR_PacCuestDt_ID, trxName);
      /** if (PHR_PacCuestDt_ID == 0)
        {
			setEXME_Pregunta_ID (0);
			setEXME_TipoPregunta_ID (0);
			setPHR_PacCuestDt_ID (0);
			setPHR_PacCuest_ID (0);
			setSecuencia (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacCuestDt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacCuestDt[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Detail of Patient Questionaire.
		@param PHR_PacCuestDt_ID Detail of Patient Questionaire	  */
	public void setPHR_PacCuestDt_ID (int PHR_PacCuestDt_ID)
	{
		if (PHR_PacCuestDt_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacCuestDt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacCuestDt_ID, Integer.valueOf(PHR_PacCuestDt_ID));
	}

	/** Get Detail of Patient Questionaire.
		@return Detail of Patient Questionaire	  */
	public int getPHR_PacCuestDt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacCuestDt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_PHR_PacCuest getPHR_PacCuest() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_PacCuest.Table_Name);
        I_PHR_PacCuest result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_PacCuest)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_PacCuest_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Questionaire.
		@param PHR_PacCuest_ID Patient Questionaire	  */
	public void setPHR_PacCuest_ID (int PHR_PacCuest_ID)
	{
		if (PHR_PacCuest_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacCuest_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_PacCuest_ID, Integer.valueOf(PHR_PacCuest_ID));
	}

	/** Get Patient Questionaire.
		@return Patient Questionaire	  */
	public int getPHR_PacCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacCuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}