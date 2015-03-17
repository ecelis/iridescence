/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_AreaConfigurador
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_AreaConfigurador extends PO implements I_EXME_AreaConfigurador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AreaConfigurador (Properties ctx, int EXME_AreaConfigurador_ID, String trxName)
    {
      super (ctx, EXME_AreaConfigurador_ID, trxName);
      /** if (EXME_AreaConfigurador_ID == 0)
        {
			setEXME_AreaConfigurador_ID (0);
			setEXME_Area_ID (0);
			setTipoArea (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AreaConfigurador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AreaConfigurador[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Configurator Area.
		@param EXME_AreaConfigurador_ID Configurator Area	  */
	public void setEXME_AreaConfigurador_ID (int EXME_AreaConfigurador_ID)
	{
		if (EXME_AreaConfigurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaConfigurador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AreaConfigurador_ID, Integer.valueOf(EXME_AreaConfigurador_ID));
	}

	/** Get Configurator Area.
		@return Configurator Area	  */
	public int getEXME_AreaConfigurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaConfigurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Area getEXME_Area() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Area.Table_Name);
        I_EXME_Area result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Area)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Area_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Area.
		@param EXME_Area_ID 
		Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Area.
		@return Area
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoArea AD_Reference_ID=1200421 */
	public static final int TIPOAREA_AD_Reference_ID=1200421;
	/** PHYSICIAN OFFICE  = P */
	public static final String TIPOAREA_PHYSICIANOFFICE = "P";
	/** OUTPATIENT CARE CENTER  = O */
	public static final String TIPOAREA_OUTPATIENTCARECENTER = "O";
	/** AMBULATORY SURGERY CENTER  = A */
	public static final String TIPOAREA_AMBULATORYSURGERYCENTER = "A";
	/** HOSPITAL CENTER = C */
	public static final String TIPOAREA_HOSPITALCENTER = "C";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("P") || TipoArea.equals("O") || TipoArea.equals("A") || TipoArea.equals("C")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1200421 - P - O - A - C");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}
}