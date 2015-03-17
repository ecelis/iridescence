/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ProceduresVersion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProceduresVersion extends PO implements I_EXME_ProceduresVersion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProceduresVersion (Properties ctx, int EXME_ProceduresVersion_ID, String trxName)
    {
      super (ctx, EXME_ProceduresVersion_ID, trxName);
      /** if (EXME_ProceduresVersion_ID == 0)
        {
			setEXME_ProceduresVersion_ID (0);
			setEXME_ProcedureType_ID (0);
			setStage (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProceduresVersion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProceduresVersion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Procedures Version.
		@param EXME_ProceduresVersion_ID Procedures Version	  */
	public void setEXME_ProceduresVersion_ID (int EXME_ProceduresVersion_ID)
	{
		if (EXME_ProceduresVersion_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProceduresVersion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProceduresVersion_ID, Integer.valueOf(EXME_ProceduresVersion_ID));
	}

	/** Get Procedures Version.
		@return Procedures Version	  */
	public int getEXME_ProceduresVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProceduresVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ProcedureType getEXME_ProcedureType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProcedureType.Table_Name);
        I_EXME_ProcedureType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProcedureType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProcedureType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Procedure Type.
		@param EXME_ProcedureType_ID Procedure Type	  */
	public void setEXME_ProcedureType_ID (int EXME_ProcedureType_ID)
	{
		if (EXME_ProcedureType_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcedureType_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProcedureType_ID, Integer.valueOf(EXME_ProcedureType_ID));
	}

	/** Get Procedure Type.
		@return Procedure Type	  */
	public int getEXME_ProcedureType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcedureType_ID);
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