/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Menu_Dinamico
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Menu_Dinamico extends PO implements I_EXME_Menu_Dinamico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Menu_Dinamico (Properties ctx, int EXME_Menu_Dinamico_ID, String trxName)
    {
      super (ctx, EXME_Menu_Dinamico_ID, trxName);
      /** if (EXME_Menu_Dinamico_ID == 0)
        {
			setAD_FormParent_ID (0);
			setChildType (null);
			setEXME_Menu_Dinamico_ID (0);
			setSequence (Env.ZERO);
// 999
        } */
    }

    /** Load Constructor */
    public X_EXME_Menu_Dinamico (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Menu_Dinamico[")
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
			set_Value (COLUMNNAME_AD_FormChild_ID, null);
		else 
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

	/** Set Parent Form.
		@param AD_FormParent_ID 
		Parent Form
	  */
	public void setAD_FormParent_ID (int AD_FormParent_ID)
	{
		if (AD_FormParent_ID < 1)
			 throw new IllegalArgumentException ("AD_FormParent_ID is mandatory.");
		set_Value (COLUMNNAME_AD_FormParent_ID, Integer.valueOf(AD_FormParent_ID));
	}

	/** Get Parent Form.
		@return Parent Form
	  */
	public int getAD_FormParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FormParent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Child Process.
		@param AD_ProcessChild_ID 
		Child Process
	  */
	public void setAD_ProcessChild_ID (int AD_ProcessChild_ID)
	{
		if (AD_ProcessChild_ID < 1) 
			set_Value (COLUMNNAME_AD_ProcessChild_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ProcessChild_ID, Integer.valueOf(AD_ProcessChild_ID));
	}

	/** Get Child Process.
		@return Child Process
	  */
	public int getAD_ProcessChild_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ProcessChild_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Child Window.
		@param AD_WindowChild_ID 
		Child Window
	  */
	public void setAD_WindowChild_ID (int AD_WindowChild_ID)
	{
		if (AD_WindowChild_ID < 1) 
			set_Value (COLUMNNAME_AD_WindowChild_ID, null);
		else 
			set_Value (COLUMNNAME_AD_WindowChild_ID, Integer.valueOf(AD_WindowChild_ID));
	}

	/** Get Child Window.
		@return Child Window
	  */
	public int getAD_WindowChild_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WindowChild_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ChildType AD_Reference_ID=1200437 */
	public static final int CHILDTYPE_AD_Reference_ID=1200437;
	/** Form = F */
	public static final String CHILDTYPE_Form = "F";
	/** Window = V */
	public static final String CHILDTYPE_Window = "V";
	/** Process = P */
	public static final String CHILDTYPE_Process = "P";
	/** Set Child Type.
		@param ChildType 
		Child Type
	  */
	public void setChildType (String ChildType)
	{
		if (ChildType == null) throw new IllegalArgumentException ("ChildType is mandatory");
		if (ChildType.equals("F") || ChildType.equals("V") || ChildType.equals("P")); else throw new IllegalArgumentException ("ChildType Invalid value - " + ChildType + " - Reference_ID=1200437 - F - W - V - P");		set_Value (COLUMNNAME_ChildType, ChildType);
	}

	/** Get Child Type.
		@return Child Type
	  */
	public String getChildType () 
	{
		return (String)get_Value(COLUMNNAME_ChildType);
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

	/** Set Display Logic.
		@param DisplayLogic 
		If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic)
	{
		set_Value (COLUMNNAME_DisplayLogic, DisplayLogic);
	}

	/** Get Display Logic.
		@return If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic () 
	{
		return (String)get_Value(COLUMNNAME_DisplayLogic);
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

	/** Set Dynamic Menu.
		@param EXME_Menu_Dinamico_ID 
		Dynamic Menu
	  */
	public void setEXME_Menu_Dinamico_ID (int EXME_Menu_Dinamico_ID)
	{
		if (EXME_Menu_Dinamico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Menu_Dinamico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Menu_Dinamico_ID, Integer.valueOf(EXME_Menu_Dinamico_ID));
	}

	/** Get Dynamic Menu.
		@return Dynamic Menu
	  */
	public int getEXME_Menu_Dinamico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Menu_Dinamico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Help URI.
		@param Help_URI 
		The URI with the help document for the current window.
	  */
	public void setHelp_URI (String Help_URI)
	{
		set_Value (COLUMNNAME_Help_URI, Help_URI);
	}

	/** Get Help URI.
		@return The URI with the help document for the current window.
	  */
	public String getHelp_URI () 
	{
		return (String)get_Value(COLUMNNAME_Help_URI);
	}

	/** Set Image.
		@param Image Image	  */
	public void setImage (String Image)
	{
		set_Value (COLUMNNAME_Image, Image);
	}

	/** Get Image.
		@return Image	  */
	public String getImage () 
	{
		return (String)get_Value(COLUMNNAME_Image);
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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

	/** Set Parameters.
		@param Params Parameters	  */
	public void setParams (String Params)
	{
		set_Value (COLUMNNAME_Params, Params);
	}

	/** Get Parameters.
		@return Parameters	  */
	public String getParams () 
	{
		return (String)get_Value(COLUMNNAME_Params);
	}

	/** ParentType AD_Reference_ID=1200436 */
	public static final int PARENTTYPE_AD_Reference_ID=1200436;
	/** Form = F */
	public static final String PARENTTYPE_Form = "F";
	/** Struts = W */
	public static final String PARENTTYPE_Struts = "W";
	/** Set Parent Type.
		@param ParentType 
		Parent Type
	  */
	public void setParentType (String ParentType)
	{

		if (ParentType == null || ParentType.equals("F") || ParentType.equals("W")); else throw new IllegalArgumentException ("ParentType Invalid value - " + ParentType + " - Reference_ID=1200436 - F - W");		set_Value (COLUMNNAME_ParentType, ParentType);
	}

	/** Get Parent Type.
		@return Parent Type
	  */
	public String getParentType () 
	{
		return (String)get_Value(COLUMNNAME_ParentType);
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		if (Sequence == null)
			throw new IllegalArgumentException ("Sequence is mandatory.");
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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