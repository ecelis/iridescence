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

/** Generated Model for EXME_Plantilla
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_Plantilla extends PO implements I_EXME_Plantilla, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Plantilla (Properties ctx, int EXME_Plantilla_ID, String trxName)
    {
      super (ctx, EXME_Plantilla_ID, trxName);
      /** if (EXME_Plantilla_ID == 0)
        {
			setEXME_Plantilla_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Plantilla (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Plantilla[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Category AD_Reference_ID=1200475 */
	public static final int CATEGORY_AD_Reference_ID=1200475;
	/** Discharge Instructions = DI */
	public static final String CATEGORY_DischargeInstructions = "DI";
	/** Discharge Instructions VTE = DIV */
	public static final String CATEGORY_DischargeInstructionsVTE = "DIV";
	/** Advanced Directives = AD */
	public static final String CATEGORY_AdvancedDirectives = "AD";
	/** Discharge Instructions Stroke = DIS */
	public static final String CATEGORY_DischargeInstructionsStroke = "DIS";
	/** Comfort Measures Only = CMO */
	public static final String CATEGORY_ComfortMeasuresOnly = "CMO";
	/** Set Category.
		@param Category Category	  */
	public void setCategory (String Category)
	{

		if (Category == null || Category.equals("DI") || Category.equals("DIV") || Category.equals("AD") || Category.equals("DIS") || Category.equals("CMO")); else throw new IllegalArgumentException ("Category Invalid value - " + Category + " - Reference_ID=1200475 - DI - DIV - AD - DIS - CMO");		set_Value (COLUMNNAME_Category, Category);
	}

	/** Get Category.
		@return Category	  */
	public String getCategory () 
	{
		return (String)get_Value(COLUMNNAME_Category);
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

	public I_EXME_ClasificacionPlantilla getEXME_ClasificacionPlantilla() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClasificacionPlantilla.Table_Name);
        I_EXME_ClasificacionPlantilla result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClasificacionPlantilla)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClasificacionPlantilla_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Classification Template.
		@param EXME_ClasificacionPlantilla_ID Classification Template	  */
	public void setEXME_ClasificacionPlantilla_ID (int EXME_ClasificacionPlantilla_ID)
	{
		if (EXME_ClasificacionPlantilla_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClasificacionPlantilla_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClasificacionPlantilla_ID, Integer.valueOf(EXME_ClasificacionPlantilla_ID));
	}

	/** Get Classification Template.
		@return Classification Template	  */
	public int getEXME_ClasificacionPlantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClasificacionPlantilla_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuestionario.Table_Name);
        I_EXME_Cuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Template.
		@param EXME_Plantilla_ID Template	  */
	public void setEXME_Plantilla_ID (int EXME_Plantilla_ID)
	{
		if (EXME_Plantilla_ID < 1)
			 throw new IllegalArgumentException ("EXME_Plantilla_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Plantilla_ID, Integer.valueOf(EXME_Plantilla_ID));
	}

	/** Get Template.
		@return Template	  */
	public int getEXME_Plantilla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Plantilla_ID);
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

	/** Set Template.
		@param Plantilla Template	  */
	public void setPlantilla (byte[] Plantilla)
	{
		set_Value (COLUMNNAME_Plantilla, Plantilla);
	}

	/** Get Template.
		@return Template	  */
	public byte[] getPlantilla () 
	{
		return (byte[])get_Value(COLUMNNAME_Plantilla);
	}

	/** Set Query.
		@param Query 
		SQL
	  */
	public void setQuery (String Query)
	{
		set_Value (COLUMNNAME_Query, Query);
	}

	/** Get Query.
		@return SQL
	  */
	public String getQuery () 
	{
		return (String)get_Value(COLUMNNAME_Query);
	}
}