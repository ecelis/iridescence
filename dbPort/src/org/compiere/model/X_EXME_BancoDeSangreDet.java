/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_BancoDeSangreDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_BancoDeSangreDet extends PO implements I_EXME_BancoDeSangreDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BancoDeSangreDet (Properties ctx, int EXME_BancoDeSangreDet_ID, String trxName)
    {
      super (ctx, EXME_BancoDeSangreDet_ID, trxName);
      /** if (EXME_BancoDeSangreDet_ID == 0)
        {
			setEXME_BancoDeSangreDet_ID (0);
			setEXME_BancoDeSangreH_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_BancoDeSangreDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BancoDeSangreDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Analysis.
		@param Analisis 
		Analysis
	  */
	public void setAnalisis (String Analisis)
	{
		set_Value (COLUMNNAME_Analisis, Analisis);
	}

	/** Get Analysis.
		@return Analysis
	  */
	public String getAnalisis () 
	{
		return (String)get_Value(COLUMNNAME_Analisis);
	}

	/** Set Blood Bank Detail.
		@param EXME_BancoDeSangreDet_ID 
		Blood Bank Detail
	  */
	public void setEXME_BancoDeSangreDet_ID (int EXME_BancoDeSangreDet_ID)
	{
		if (EXME_BancoDeSangreDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_BancoDeSangreDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BancoDeSangreDet_ID, Integer.valueOf(EXME_BancoDeSangreDet_ID));
	}

	/** Get Blood Bank Detail.
		@return Blood Bank Detail
	  */
	public int getEXME_BancoDeSangreDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BancoDeSangreDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_BancoDeSangreH getEXME_BancoDeSangreH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BancoDeSangreH.Table_Name);
        I_EXME_BancoDeSangreH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BancoDeSangreH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BancoDeSangreH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Blood Bank.
		@param EXME_BancoDeSangreH_ID 
		Blood Bank
	  */
	public void setEXME_BancoDeSangreH_ID (int EXME_BancoDeSangreH_ID)
	{
		if (EXME_BancoDeSangreH_ID < 1)
			 throw new IllegalArgumentException ("EXME_BancoDeSangreH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_BancoDeSangreH_ID, Integer.valueOf(EXME_BancoDeSangreH_ID));
	}

	/** Get Blood Bank.
		@return Blood Bank
	  */
	public int getEXME_BancoDeSangreH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BancoDeSangreH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Result of the Analysis.
		@param ResultadoAnalisis 
		Result of the Analysis
	  */
	public void setResultadoAnalisis (String ResultadoAnalisis)
	{
		set_Value (COLUMNNAME_ResultadoAnalisis, ResultadoAnalisis);
	}

	/** Get Result of the Analysis.
		@return Result of the Analysis
	  */
	public String getResultadoAnalisis () 
	{
		return (String)get_Value(COLUMNNAME_ResultadoAnalisis);
	}
}