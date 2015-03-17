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

/** Generated Model for EXME_FormSectionConf
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FormSectionConf extends PO implements I_EXME_FormSectionConf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FormSectionConf (Properties ctx, int EXME_FormSectionConf_ID, String trxName)
    {
      super (ctx, EXME_FormSectionConf_ID, trxName);
      /** if (EXME_FormSectionConf_ID == 0)
        {
			setAD_FormChild_ID (0);
			setEXME_FormSectionConf_ID (0);
			setName (null);
			setSequence (0);
			setTitle (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FormSectionConf (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FormSectionConf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Child Form.
		@param AD_FormChild_ID 
		Child Form
	  */
	public void setAD_FormChild_ID (int AD_FormChild_ID)
	{
		if (AD_FormChild_ID < 1)
			 throw new IllegalArgumentException ("AD_FormChild_ID is mandatory.");
		set_Value (COLUMNNAME_AD_FormChild_ID, Integer.valueOf(AD_FormChild_ID));
	}

	/** Get Child Form.
		@return Child Form
	  */
	public int getAD_FormChild_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FormChild_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Form Section Configuration.
		@param EXME_FormSectionConf_ID Form Section Configuration	  */
	public void setEXME_FormSectionConf_ID (int EXME_FormSectionConf_ID)
	{
		if (EXME_FormSectionConf_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormSectionConf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FormSectionConf_ID, Integer.valueOf(EXME_FormSectionConf_ID));
	}

	/** Get Form Section Configuration.
		@return Form Section Configuration	  */
	public int getEXME_FormSectionConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormSectionConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FormSectionHeader.Table_Name);
        I_EXME_FormSectionHeader result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FormSectionHeader)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FormSectionHeader_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Section's Header.
		@param EXME_FormSectionHeader_ID Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID)
	{
		if (EXME_FormSectionHeader_ID < 1) 
			set_Value (COLUMNNAME_EXME_FormSectionHeader_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FormSectionHeader_ID, Integer.valueOf(EXME_FormSectionHeader_ID));
	}

	/** Get Form Section's Header.
		@return Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormSectionHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoCuestionario.Table_Name);
        I_EXME_GrupoCuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoCuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoCuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Group.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
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

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		if (Title == null)
			throw new IllegalArgumentException ("Title is mandatory.");
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
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