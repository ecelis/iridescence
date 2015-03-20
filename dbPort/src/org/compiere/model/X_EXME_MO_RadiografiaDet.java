/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MO_RadiografiaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_RadiografiaDet extends PO implements I_EXME_MO_RadiografiaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_RadiografiaDet (Properties ctx, int EXME_MO_RadiografiaDet_ID, String trxName)
    {
      super (ctx, EXME_MO_RadiografiaDet_ID, trxName);
      /** if (EXME_MO_RadiografiaDet_ID == 0)
        {
			setEXME_MO_RadiografiaDet_ID (0);
			setEXME_TratamientosPaciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_RadiografiaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_RadiografiaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set X-Ray detail.
		@param EXME_MO_RadiografiaDet_ID X-Ray detail	  */
	public void setEXME_MO_RadiografiaDet_ID (int EXME_MO_RadiografiaDet_ID)
	{
		if (EXME_MO_RadiografiaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_RadiografiaDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_RadiografiaDet_ID, Integer.valueOf(EXME_MO_RadiografiaDet_ID));
	}

	/** Get X-Ray detail.
		@return X-Ray detail	  */
	public int getEXME_MO_RadiografiaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_RadiografiaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_Radiografias getEXME_MO_Radiografias() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_Radiografias.Table_Name);
        I_EXME_MO_Radiografias result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_Radiografias)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_Radiografias_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set X-Ray.
		@param EXME_MO_Radiografias_ID X-Ray	  */
	public void setEXME_MO_Radiografias_ID (int EXME_MO_Radiografias_ID)
	{
		if (EXME_MO_Radiografias_ID < 1) 
			set_Value (COLUMNNAME_EXME_MO_Radiografias_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MO_Radiografias_ID, Integer.valueOf(EXME_MO_Radiografias_ID));
	}

	/** Get X-Ray.
		@return X-Ray	  */
	public int getEXME_MO_Radiografias_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Radiografias_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_TratamientosPaciente getEXME_TratamientosPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TratamientosPaciente.Table_Name);
        I_EXME_TratamientosPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TratamientosPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TratamientosPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Treatments.
		@param EXME_TratamientosPaciente_ID Patient Treatments	  */
	public void setEXME_TratamientosPaciente_ID (int EXME_TratamientosPaciente_ID)
	{
		if (EXME_TratamientosPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TratamientosPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TratamientosPaciente_ID, Integer.valueOf(EXME_TratamientosPaciente_ID));
	}

	/** Get Patient Treatments.
		@return Patient Treatments	  */
	public int getEXME_TratamientosPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TratamientosPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}