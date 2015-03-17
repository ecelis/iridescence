/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_CtaPacBitacora
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CtaPacBitacora extends PO implements I_EXME_CtaPacBitacora, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacBitacora (Properties ctx, int EXME_CtaPacBitacora_ID, String trxName)
    {
      super (ctx, EXME_CtaPacBitacora_ID, trxName);
      /** if (EXME_CtaPacBitacora_ID == 0)
        {
			setEXME_CtaPacBitacora_ID (0);
			setEXME_CtaPac_ID (0);
			setOpcion (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacBitacora (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacBitacora[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Patient Account Log.
		@param EXME_CtaPacBitacora_ID 
		Description of the Patient Account Log
	  */
	public void setEXME_CtaPacBitacora_ID (int EXME_CtaPacBitacora_ID)
	{
		if (EXME_CtaPacBitacora_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacBitacora_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacBitacora_ID, Integer.valueOf(EXME_CtaPacBitacora_ID));
	}

	/** Get Patient Account Log.
		@return Description of the Patient Account Log
	  */
	public int getEXME_CtaPacBitacora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacBitacora_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PackBase New Version.
		@param EXME_PaqBase_Version_New_ID PackBase New Version	  */
	public void setEXME_PaqBase_Version_New_ID (int EXME_PaqBase_Version_New_ID)
	{
		if (EXME_PaqBase_Version_New_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_New_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_New_ID, Integer.valueOf(EXME_PaqBase_Version_New_ID));
	}

	/** Get PackBase New Version.
		@return PackBase New Version	  */
	public int getEXME_PaqBase_Version_New_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_New_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PackBase Old Version.
		@param EXME_PaqBase_Version_Old_ID PackBase Old Version	  */
	public void setEXME_PaqBase_Version_Old_ID (int EXME_PaqBase_Version_Old_ID)
	{
		if (EXME_PaqBase_Version_Old_ID < 1) 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_Old_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PaqBase_Version_Old_ID, Integer.valueOf(EXME_PaqBase_Version_Old_ID));
	}

	/** Get PackBase Old Version.
		@return PackBase Old Version	  */
	public int getEXME_PaqBase_Version_Old_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqBase_Version_Old_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Opcion AD_Reference_ID=1200100 */
	public static final int OPCION_AD_Reference_ID=1200100;
	/** Open = AP */
	public static final String OPCION_Open = "AP";
	/** Assign = AS */
	public static final String OPCION_Assign = "AS";
	/** Delete = EL */
	public static final String OPCION_Delete = "EL";
	/** Change of package = CA */
	public static final String OPCION_ChangeOfPackage = "CA";
	/** Close = CL */
	public static final String OPCION_Close = "CL";
	/** Reactivation = RE */
	public static final String OPCION_Reactivation = "RE";
	/** OpenAsign = AA */
	public static final String OPCION_OpenAsign = "AA";
	/** Set Option.
		@param Opcion 
		Description of options
	  */
	public void setOpcion (String Opcion)
	{
		if (Opcion == null) throw new IllegalArgumentException ("Opcion is mandatory");
		if (Opcion.equals("AP") || Opcion.equals("AS") || Opcion.equals("EL") || Opcion.equals("CA") || Opcion.equals("CL") || Opcion.equals("RE") || Opcion.equals("AA")); else throw new IllegalArgumentException ("Opcion Invalid value - " + Opcion + " - Reference_ID=1200100 - AP - AS - EL - CA - CL - RE - AA");		set_Value (COLUMNNAME_Opcion, Opcion);
	}

	/** Get Option.
		@return Description of options
	  */
	public String getOpcion () 
	{
		return (String)get_Value(COLUMNNAME_Opcion);
	}
}