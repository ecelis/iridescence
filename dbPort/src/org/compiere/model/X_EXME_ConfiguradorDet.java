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

/** Generated Model for EXME_ConfiguradorDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfiguradorDet extends PO implements I_EXME_ConfiguradorDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfiguradorDet (Properties ctx, int EXME_ConfiguradorDet_ID, String trxName)
    {
      super (ctx, EXME_ConfiguradorDet_ID, trxName);
      /** if (EXME_ConfiguradorDet_ID == 0)
        {
			setClassname (null);
			setEXME_ConfiguradorDet_ID (0);
			setEXME_Configurador_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfiguradorDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfiguradorDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		if (Classname == null)
			throw new IllegalArgumentException ("Classname is mandatory.");
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
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

	/** Set Configurator Detail.
		@param EXME_ConfiguradorDet_ID Configurator Detail	  */
	public void setEXME_ConfiguradorDet_ID (int EXME_ConfiguradorDet_ID)
	{
		if (EXME_ConfiguradorDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfiguradorDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfiguradorDet_ID, Integer.valueOf(EXME_ConfiguradorDet_ID));
	}

	/** Get Configurator Detail.
		@return Configurator Detail	  */
	public int getEXME_ConfiguradorDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfiguradorDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		set_ValueNoCheck (COLUMNNAME_EXME_Configurador_ID, Integer.valueOf(EXME_Configurador_ID));
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