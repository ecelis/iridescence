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

/** Generated Model for EXME_MensajePregunta
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_MensajePregunta extends PO implements I_EXME_MensajePregunta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MensajePregunta (Properties ctx, int EXME_MensajePregunta_ID, String trxName)
    {
      super (ctx, EXME_MensajePregunta_ID, trxName);
      /** if (EXME_MensajePregunta_ID == 0)
        {
			setEXME_MensajePregunta_ID (0);
			setEXME_Pregunta_ID (0);
			setFinalValue (Env.ZERO);
			setInitialValue (Env.ZERO);
			setMessage (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MensajePregunta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MensajePregunta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Question Message.
		@param EXME_MensajePregunta_ID Question Message	  */
	public void setEXME_MensajePregunta_ID (int EXME_MensajePregunta_ID)
	{
		if (EXME_MensajePregunta_ID < 1)
			 throw new IllegalArgumentException ("EXME_MensajePregunta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MensajePregunta_ID, Integer.valueOf(EXME_MensajePregunta_ID));
	}

	/** Get Question Message.
		@return Question Message	  */
	public int getEXME_MensajePregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MensajePregunta_ID);
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

	/** Set Final Value.
		@param FinalValue Final Value	  */
	public void setFinalValue (BigDecimal FinalValue)
	{
		if (FinalValue == null)
			throw new IllegalArgumentException ("FinalValue is mandatory.");
		set_Value (COLUMNNAME_FinalValue, FinalValue);
	}

	/** Get Final Value.
		@return Final Value	  */
	public BigDecimal getFinalValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FinalValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Initial Value.
		@param InitialValue Initial Value	  */
	public void setInitialValue (BigDecimal InitialValue)
	{
		if (InitialValue == null)
			throw new IllegalArgumentException ("InitialValue is mandatory.");
		set_Value (COLUMNNAME_InitialValue, InitialValue);
	}

	/** Get Initial Value.
		@return Initial Value	  */
	public BigDecimal getInitialValue () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InitialValue);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		if (Message == null)
			throw new IllegalArgumentException ("Message is mandatory.");
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
	}
}