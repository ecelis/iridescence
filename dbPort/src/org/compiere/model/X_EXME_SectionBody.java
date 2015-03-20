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

/** Generated Model for EXME_SectionBody
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SectionBody extends PO implements I_EXME_SectionBody, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SectionBody (Properties ctx, int EXME_SectionBody_ID, String trxName)
    {
      super (ctx, EXME_SectionBody_ID, trxName);
      /** if (EXME_SectionBody_ID == 0)
        {
			setEXME_SectionBody_ID (0);
			setEXME_SystemBody_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_SectionBody (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SectionBody[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Back Image.
		@param BackImage Back Image	  */
	public void setBackImage (String BackImage)
	{
		set_Value (COLUMNNAME_BackImage, BackImage);
	}

	/** Get Back Image.
		@return Back Image	  */
	public String getBackImage () 
	{
		return (String)get_Value(COLUMNNAME_BackImage);
	}

	/** Set Back Map.
		@param BackMap Back Map	  */
	public void setBackMap (String BackMap)
	{
		set_Value (COLUMNNAME_BackMap, BackMap);
	}

	/** Get Back Map.
		@return Back Map	  */
	public String getBackMap () 
	{
		return (String)get_Value(COLUMNNAME_BackMap);
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

	/** Set Final Diagnosis.
		@param DiagFin 
		Rank of Final Diagnosis
	  */
	public void setDiagFin (String DiagFin)
	{
		set_Value (COLUMNNAME_DiagFin, DiagFin);
	}

	/** Get Final Diagnosis.
		@return Rank of Final Diagnosis
	  */
	public String getDiagFin () 
	{
		return (String)get_Value(COLUMNNAME_DiagFin);
	}

	/** Set Initial Diagnosis.
		@param DiagIni 
		Rank of Initial Diagnosis
	  */
	public void setDiagIni (String DiagIni)
	{
		set_Value (COLUMNNAME_DiagIni, DiagIni);
	}

	/** Get Initial Diagnosis.
		@return Rank of Initial Diagnosis
	  */
	public String getDiagIni () 
	{
		return (String)get_Value(COLUMNNAME_DiagIni);
	}

	/** Set Section Body.
		@param EXME_SectionBody_ID Section Body	  */
	public void setEXME_SectionBody_ID (int EXME_SectionBody_ID)
	{
		if (EXME_SectionBody_ID < 1)
			 throw new IllegalArgumentException ("EXME_SectionBody_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SectionBody_ID, Integer.valueOf(EXME_SectionBody_ID));
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

	public I_EXME_SystemBody getEXME_SystemBody() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SystemBody.Table_Name);
        I_EXME_SystemBody result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SystemBody)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SystemBody_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set System Body.
		@param EXME_SystemBody_ID System Body	  */
	public void setEXME_SystemBody_ID (int EXME_SystemBody_ID)
	{
		if (EXME_SystemBody_ID < 1)
			 throw new IllegalArgumentException ("EXME_SystemBody_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SystemBody_ID, Integer.valueOf(EXME_SystemBody_ID));
	}

	/** Get System Body.
		@return System Body	  */
	public int getEXME_SystemBody_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SystemBody_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Front Image.
		@param FrontImage 
		Front Image
	  */
	public void setFrontImage (String FrontImage)
	{
		set_Value (COLUMNNAME_FrontImage, FrontImage);
	}

	/** Get Front Image.
		@return Front Image
	  */
	public String getFrontImage () 
	{
		return (String)get_Value(COLUMNNAME_FrontImage);
	}

	/** Set Front Map.
		@param FrontMap Front Map	  */
	public void setFrontMap (String FrontMap)
	{
		set_Value (COLUMNNAME_FrontMap, FrontMap);
	}

	/** Get Front Map.
		@return Front Map	  */
	public String getFrontMap () 
	{
		return (String)get_Value(COLUMNNAME_FrontMap);
	}

	/** Set Left Image.
		@param LeftImage Left Image	  */
	public void setLeftImage (String LeftImage)
	{
		set_Value (COLUMNNAME_LeftImage, LeftImage);
	}

	/** Get Left Image.
		@return Left Image	  */
	public String getLeftImage () 
	{
		return (String)get_Value(COLUMNNAME_LeftImage);
	}

	/** Set Left Map.
		@param LeftMap Left Map	  */
	public void setLeftMap (String LeftMap)
	{
		set_Value (COLUMNNAME_LeftMap, LeftMap);
	}

	/** Get Left Map.
		@return Left Map	  */
	public String getLeftMap () 
	{
		return (String)get_Value(COLUMNNAME_LeftMap);
	}

	/** Set Map.
		@param Map Map	  */
	public void setMap (String Map)
	{
		set_Value (COLUMNNAME_Map, Map);
	}

	/** Get Map.
		@return Map	  */
	public String getMap () 
	{
		return (String)get_Value(COLUMNNAME_Map);
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

	/** Set Reference to Exme_SectionBody.
		@param REF_SectionBody_ID Reference to Exme_SectionBody	  */
	public void setREF_SectionBody_ID (int REF_SectionBody_ID)
	{
		if (REF_SectionBody_ID < 1) 
			set_Value (COLUMNNAME_REF_SectionBody_ID, null);
		else 
			set_Value (COLUMNNAME_REF_SectionBody_ID, Integer.valueOf(REF_SectionBody_ID));
	}

	/** Get Reference to Exme_SectionBody.
		@return Reference to Exme_SectionBody	  */
	public int getREF_SectionBody_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_REF_SectionBody_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Right Image.
		@param RightImage Right Image	  */
	public void setRightImage (String RightImage)
	{
		set_Value (COLUMNNAME_RightImage, RightImage);
	}

	/** Get Right Image.
		@return Right Image	  */
	public String getRightImage () 
	{
		return (String)get_Value(COLUMNNAME_RightImage);
	}

	/** Set Right Map.
		@param RightMap Right Map	  */
	public void setRightMap (String RightMap)
	{
		set_Value (COLUMNNAME_RightMap, RightMap);
	}

	/** Get Right Map.
		@return Right Map	  */
	public String getRightMap () 
	{
		return (String)get_Value(COLUMNNAME_RightMap);
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