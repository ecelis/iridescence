/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_DiagnosticoSeccion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_DiagnosticoSeccion extends PO implements I_EXME_DiagnosticoSeccion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DiagnosticoSeccion (Properties ctx, int EXME_DiagnosticoSeccion_ID, String trxName)
    {
      super (ctx, EXME_DiagnosticoSeccion_ID, trxName);
      /** if (EXME_DiagnosticoSeccion_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_DiagnosticoSeccion_ID (0);
			setEXME_SectionBody_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_DiagnosticoSeccion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DiagnosticoSeccion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnostic Section.
		@param EXME_DiagnosticoSeccion_ID Diagnostic Section	  */
	public void setEXME_DiagnosticoSeccion_ID (int EXME_DiagnosticoSeccion_ID)
	{
		if (EXME_DiagnosticoSeccion_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiagnosticoSeccion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiagnosticoSeccion_ID, Integer.valueOf(EXME_DiagnosticoSeccion_ID));
	}

	/** Get Diagnostic Section.
		@return Diagnostic Section	  */
	public int getEXME_DiagnosticoSeccion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoSeccion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SectionBody getEXME_SectionBody() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SectionBody.Table_Name);
        I_EXME_SectionBody result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SectionBody)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SectionBody_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Section Body.
		@param EXME_SectionBody_ID Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID)
	{
		if (EXME_SectionBody_ID < 1)
			 throw new IllegalArgumentException ("EXME_SectionBody_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SectionBody_ID, Integer.valueOf(EXME_SectionBody_ID));
	}

	/** Get Section Body.
		@return Section Body	  */
	public int getEXME_SectionBody_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SectionBody_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}