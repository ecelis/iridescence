/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_GrupoCuestionarioDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_GrupoCuestionarioDet extends PO implements I_EXME_GrupoCuestionarioDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GrupoCuestionarioDet (Properties ctx, int EXME_GrupoCuestionarioDet_ID, String trxName)
    {
      super (ctx, EXME_GrupoCuestionarioDet_ID, trxName);
      /** if (EXME_GrupoCuestionarioDet_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_GrupoCuestionarioDet_ID (0);
			setEXME_GrupoCuestionario_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_GrupoCuestionarioDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GrupoCuestionarioDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set EXME_GrupoCuestionarioDet_ID.
		@param EXME_GrupoCuestionarioDet_ID 
		Form Group Detail
	  */
	public void setEXME_GrupoCuestionarioDet_ID (int EXME_GrupoCuestionarioDet_ID)
	{
		if (EXME_GrupoCuestionarioDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_GrupoCuestionarioDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GrupoCuestionarioDet_ID, Integer.valueOf(EXME_GrupoCuestionarioDet_ID));
	}

	/** Get EXME_GrupoCuestionarioDet_ID.
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

	/** Set EXME_GrupoCuestionario_ID.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_GrupoCuestionario_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get EXME_GrupoCuestionario_ID.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
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
}