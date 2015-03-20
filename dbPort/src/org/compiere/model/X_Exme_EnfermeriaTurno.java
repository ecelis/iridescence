/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for Exme_EnfermeriaTurno
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_Exme_EnfermeriaTurno extends PO implements I_Exme_EnfermeriaTurno, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_Exme_EnfermeriaTurno (Properties ctx, int Exme_EnfermeriaTurno_ID, String trxName)
    {
      super (ctx, Exme_EnfermeriaTurno_ID, trxName);
      /** if (Exme_EnfermeriaTurno_ID == 0)
        {
			setEXME_Enfermeria_ID (0);
			setExme_EnfermeriaTurno_ID (0);
			setTurno (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_Exme_EnfermeriaTurno (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Exme_EnfermeriaTurno[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Enfermeria_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing Turn.
		@param Exme_EnfermeriaTurno_ID Nursing Turn	  */
	public void setExme_EnfermeriaTurno_ID (int Exme_EnfermeriaTurno_ID)
	{
		if (Exme_EnfermeriaTurno_ID < 1)
			 throw new IllegalArgumentException ("Exme_EnfermeriaTurno_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Exme_EnfermeriaTurno_ID, Integer.valueOf(Exme_EnfermeriaTurno_ID));
	}

	/** Get Nursing Turn.
		@return Nursing Turn	  */
	public int getExme_EnfermeriaTurno_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Exme_EnfermeriaTurno_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Turno AD_Reference_ID=1200226 */
	public static final int TURNO_AD_Reference_ID=1200226;
	/** First turn = 1 */
	public static final String TURNO_FirstTurn = "1";
	/** Second Turn = 2 */
	public static final String TURNO_SecondTurn = "2";
	/** Nocturne = 3 */
	public static final String TURNO_Nocturne = "3";
	/** Set Turn.
		@param Turno Turn	  */
	public void setTurno (String Turno)
	{
		if (Turno == null) throw new IllegalArgumentException ("Turno is mandatory");
		if (Turno.equals("1") || Turno.equals("2") || Turno.equals("3")); else throw new IllegalArgumentException ("Turno Invalid value - " + Turno + " - Reference_ID=1200226 - 1 - 2 - 3");		set_Value (COLUMNNAME_Turno, Turno);
	}

	/** Get Turn.
		@return Turn	  */
	public String getTurno () 
	{
		return (String)get_Value(COLUMNNAME_Turno);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
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