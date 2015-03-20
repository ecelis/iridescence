/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_RecursoEducativoStr
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecursoEducativoStr extends PO implements I_EXME_RecursoEducativoStr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativoStr (Properties ctx, int EXME_RecursoEducativoStr_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativoStr_ID, trxName);
      /** if (EXME_RecursoEducativoStr_ID == 0)
        {
			setEXME_RecursoEducativo_ID (0);
			setEXME_RecursoEducativoStr_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativoStr (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativoStr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecursoEducativo.Table_Name);
        I_EXME_RecursoEducativo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecursoEducativo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecursoEducativo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Education Resource Stroke.
		@param EXME_RecursoEducativoStr_ID Education Resource Stroke	  */
	public void setEXME_RecursoEducativoStr_ID (int EXME_RecursoEducativoStr_ID)
	{
		if (EXME_RecursoEducativoStr_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativoStr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativoStr_ID, Integer.valueOf(EXME_RecursoEducativoStr_ID));
	}

	/** Get Education Resource Stroke.
		@return Education Resource Stroke	  */
	public int getEXME_RecursoEducativoStr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativoStr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=1200474 */
	public static final int TYPE_AD_Reference_ID=1200474;
	/** Ischemic Stroke = IS */
	public static final String TYPE_IschemicStroke = "IS";
	/** Hemorragic Stroke = HS */
	public static final String TYPE_HemorragicStroke = "HS";
	/** Venous Thomboembolic = VT */
	public static final String TYPE_VenousThomboembolic = "VT";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("IS") || Type.equals("HS") || Type.equals("VT")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200474 - IS - HS - VT");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}