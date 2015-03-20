/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ActPacienteIndCgo
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPacienteIndCgo extends PO implements I_EXME_ActPacienteIndCgo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteIndCgo (Properties ctx, int EXME_ActPacienteIndCgo_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteIndCgo_ID, trxName);
      /** if (EXME_ActPacienteIndCgo_ID == 0)
        {
			setEXME_ActPacienteIndCgo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteIndCgo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteIndCgo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
	}

	/** Set Error Msg.
		@param ErrorMsg Error Msg	  */
	public void setErrorMsg (String ErrorMsg)
	{
		set_Value (COLUMNNAME_ErrorMsg, ErrorMsg);
	}

	/** Get Error Msg.
		@return Error Msg	  */
	public String getErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_ErrorMsg);
	}

	/** Set Indication's detail (charges).
		@param EXME_ActPacienteIndCgo_ID Indication's detail (charges)	  */
	public void setEXME_ActPacienteIndCgo_ID (int EXME_ActPacienteIndCgo_ID)
	{
		if (EXME_ActPacienteIndCgo_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteIndCgo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPacienteIndCgo_ID, Integer.valueOf(EXME_ActPacienteIndCgo_ID));
	}

	/** Get Indication's detail (charges).
		@return Indication's detail (charges)	  */
	public int getEXME_ActPacienteIndCgo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndCgo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteInd.Table_Name);
        I_EXME_ActPacienteInd result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteInd)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteInd_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Indication's detail.
		@param EXME_ActPacienteInd_ID 
		Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID)
	{
		if (EXME_ActPacienteInd_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteInd_ID, Integer.valueOf(EXME_ActPacienteInd_ID));
	}

	/** Get Indication's detail.
		@return Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteInd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMed getEXME_PlanMed() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMed.Table_Name);
        I_EXME_PlanMed result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMed)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMed_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Plan.
		@param EXME_PlanMed_ID Medical Plan	  */
	public void setEXME_PlanMed_ID (int EXME_PlanMed_ID)
	{
		if (EXME_PlanMed_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMed_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMed_ID, Integer.valueOf(EXME_PlanMed_ID));
	}

	/** Get Medical Plan.
		@return Medical Plan	  */
	public int getEXME_PlanMed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PlanMedLine getEXME_PlanMedLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PlanMedLine.Table_Name);
        I_EXME_PlanMedLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PlanMedLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PlanMedLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Scheduled Doctor.
		@param EXME_PlanMedLine_ID 
		Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID)
	{
		if (EXME_PlanMedLine_ID < 1) 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PlanMedLine_ID, Integer.valueOf(EXME_PlanMedLine_ID));
	}

	/** Get Scheduled Doctor.
		@return Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PlanMedLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}