/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_TipoConfigurador
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_TipoConfigurador extends PO implements I_EXME_TipoConfigurador, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoConfigurador (Properties ctx, int EXME_TipoConfigurador_ID, String trxName)
    {
      super (ctx, EXME_TipoConfigurador_ID, trxName);
      /** if (EXME_TipoConfigurador_ID == 0)
        {
			setEXME_Configurador_ID (0);
			setEXME_TipoConfigurador_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoConfigurador (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoConfigurador[")
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

	public I_EXME_Configurador getEXME_Configurador() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Configurador.Table_Name);
        I_EXME_Configurador result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Configurador)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Configurador_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Configurator.
		@param EXME_Configurador_ID Configurator	  */
	public void setEXME_Configurador_ID (int EXME_Configurador_ID)
	{
		if (EXME_Configurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Configurador_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Configurador_ID, Integer.valueOf(EXME_Configurador_ID));
	}

	/** Get Configurator.
		@return Configurator	  */
	public int getEXME_Configurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Configurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EULA getEXME_EULA() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EULA.Table_Name);
        I_EXME_EULA result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EULA)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EULA_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set End User Agreement License.
		@param EXME_EULA_ID 
		The End User Agreement License
	  */
	public void setEXME_EULA_ID (int EXME_EULA_ID)
	{
		if (EXME_EULA_ID < 1) 
			set_Value (COLUMNNAME_EXME_EULA_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EULA_ID, Integer.valueOf(EXME_EULA_ID));
	}

	/** Get End User Agreement License.
		@return The End User Agreement License
	  */
	public int getEXME_EULA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EULA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Configuration Type.
		@param EXME_TipoConfigurador_ID Configuration Type	  */
	public void setEXME_TipoConfigurador_ID (int EXME_TipoConfigurador_ID)
	{
		if (EXME_TipoConfigurador_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoConfigurador_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoConfigurador_ID, Integer.valueOf(EXME_TipoConfigurador_ID));
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public int getEXME_TipoConfigurador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoConfigurador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

		if (TipoArea == null || TipoArea.equals("P") || TipoArea.equals("O") || TipoArea.equals("A") || TipoArea.equals("C")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1200421 - P - O - A - C");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
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