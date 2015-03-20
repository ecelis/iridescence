/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LoincVersion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_LoincVersion extends PO implements I_EXME_LoincVersion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LoincVersion (Properties ctx, int EXME_LoincVersion_ID, String trxName)
    {
      super (ctx, EXME_LoincVersion_ID, trxName);
      /** if (EXME_LoincVersion_ID == 0)
        {
			setEXME_LoincType_ID (0);
			setEXME_LoincVersion_ID (0);
			setStage (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_LoincVersion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LoincVersion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_LoincType getEXME_LoincType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_LoincType.Table_Name);
        I_EXME_LoincType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_LoincType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_LoincType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Loinc Type.
		@param EXME_LoincType_ID Loinc Type	  */
	public void setEXME_LoincType_ID (int EXME_LoincType_ID)
	{
		if (EXME_LoincType_ID < 1)
			 throw new IllegalArgumentException ("EXME_LoincType_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_LoincType_ID, Integer.valueOf(EXME_LoincType_ID));
	}

	/** Get Loinc Type.
		@return Loinc Type	  */
	public int getEXME_LoincType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Loinc Version.
		@param EXME_LoincVersion_ID Loinc Version	  */
	public void setEXME_LoincVersion_ID (int EXME_LoincVersion_ID)
	{
		if (EXME_LoincVersion_ID < 1)
			 throw new IllegalArgumentException ("EXME_LoincVersion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LoincVersion_ID, Integer.valueOf(EXME_LoincVersion_ID));
	}

	/** Get Loinc Version.
		@return Loinc Version	  */
	public int getEXME_LoincVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Stage AD_Reference_ID=1200659 */
	public static final int STAGE_AD_Reference_ID=1200659;
	/** Stage 1 = 1 */
	public static final String STAGE_Stage1 = "1";
	/** Stage 2 = 2 */
	public static final String STAGE_Stage2 = "2";
	/** Set Stage.
		@param Stage Stage	  */
	public void setStage (String Stage)
	{
		if (Stage == null) throw new IllegalArgumentException ("Stage is mandatory");
		if (Stage.equals("1") || Stage.equals("2")); else throw new IllegalArgumentException ("Stage Invalid value - " + Stage + " - Reference_ID=1200659 - 1 - 2");		set_Value (COLUMNNAME_Stage, Stage);
	}

	/** Get Stage.
		@return Stage	  */
	public String getStage () 
	{
		return (String)get_Value(COLUMNNAME_Stage);
	}
}