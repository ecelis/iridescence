/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ColacionDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ColacionDet extends PO implements I_EXME_ColacionDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ColacionDet (Properties ctx, int EXME_ColacionDet_ID, String trxName)
    {
      super (ctx, EXME_ColacionDet_ID, trxName);
      /** if (EXME_ColacionDet_ID == 0)
        {
			setDescription (null);
			setEXME_ColacionDet_ID (0);
			setEXME_Colacion_ID (0);
			setEXME_PlatilloHdr_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ColacionDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ColacionDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Snack Detail.
		@param EXME_ColacionDet_ID Snack Detail	  */
	public void setEXME_ColacionDet_ID (int EXME_ColacionDet_ID)
	{
		if (EXME_ColacionDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ColacionDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ColacionDet_ID, Integer.valueOf(EXME_ColacionDet_ID));
	}

	/** Get Snack Detail.
		@return Snack Detail	  */
	public int getEXME_ColacionDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ColacionDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Colacion getEXME_Colacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Colacion.Table_Name);
        I_EXME_Colacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Colacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Colacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Snack.
		@param EXME_Colacion_ID Snack	  */
	public void setEXME_Colacion_ID (int EXME_Colacion_ID)
	{
		if (EXME_Colacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Colacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Colacion_ID, Integer.valueOf(EXME_Colacion_ID));
	}

	/** Get Snack.
		@return Snack	  */
	public int getEXME_Colacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Colacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlatilloHdr getEXME_PlatilloHdr() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlatilloHdr.Table_Name);
        I_EXME_PlatilloHdr result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlatilloHdr)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlatilloHdr_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Saucer.
		@param EXME_PlatilloHdr_ID Saucer	  */
	public void setEXME_PlatilloHdr_ID (int EXME_PlatilloHdr_ID)
	{
		if (EXME_PlatilloHdr_ID < 1)
			 throw new IllegalArgumentException ("EXME_PlatilloHdr_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PlatilloHdr_ID, Integer.valueOf(EXME_PlatilloHdr_ID));
	}

	/** Get Saucer.
		@return Saucer	  */
	public int getEXME_PlatilloHdr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlatilloHdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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