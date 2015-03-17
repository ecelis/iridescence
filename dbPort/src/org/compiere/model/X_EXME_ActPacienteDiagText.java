/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ActPacienteDiagText
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ActPacienteDiagText extends PO implements I_EXME_ActPacienteDiagText, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteDiagText (Properties ctx, int EXME_ActPacienteDiagText_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteDiagText_ID, trxName);
      /** if (EXME_ActPacienteDiagText_ID == 0)
        {
			setEstatus (false);
			setEXME_ACTPACIENTEDIAGTEXT_ID (0);
			setEXME_ActPaciente_ID (0);
			setFecha_Diagnostico (new Timestamp( System.currentTimeMillis() ));
			setPadecimiento (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteDiagText (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteDiagText[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (boolean Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Boolean.valueOf(Estatus));
	}

	/** Get Status.
		@return Status
	  */
	public boolean isEstatus () 
	{
		Object oo = get_Value(COLUMNNAME_Estatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set EXME_ACTPACIENTEDIAGTEXT_ID.
		@param EXME_ACTPACIENTEDIAGTEXT_ID EXME_ACTPACIENTEDIAGTEXT_ID	  */
	public void setEXME_ACTPACIENTEDIAGTEXT_ID (int EXME_ACTPACIENTEDIAGTEXT_ID)
	{
		if (EXME_ACTPACIENTEDIAGTEXT_ID < 1)
			 throw new IllegalArgumentException ("EXME_ACTPACIENTEDIAGTEXT_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ACTPACIENTEDIAGTEXT_ID, Integer.valueOf(EXME_ACTPACIENTEDIAGTEXT_ID));
	}

	/** Get EXME_ACTPACIENTEDIAGTEXT_ID.
		@return EXME_ACTPACIENTEDIAGTEXT_ID	  */
	public int getEXME_ACTPACIENTEDIAGTEXT_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ACTPACIENTEDIAGTEXT_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPaciente.Table_Name);
        I_EXME_ActPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPaciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ActPaciente_ID, Integer.valueOf(EXME_ActPaciente_ID));
	}

	/** Get Patient Activity.
		@return Patient Activity
	  */
	public int getEXME_ActPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnostic Date.
		@param Fecha_Diagnostico 
		Diagnostic Date
	  */
	public void setFecha_Diagnostico (Timestamp Fecha_Diagnostico)
	{
		if (Fecha_Diagnostico == null)
			throw new IllegalArgumentException ("Fecha_Diagnostico is mandatory.");
		set_Value (COLUMNNAME_Fecha_Diagnostico, Fecha_Diagnostico);
	}

	/** Get Diagnostic Date.
		@return Diagnostic Date
	  */
	public Timestamp getFecha_Diagnostico () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Diagnostico);
	}

	/** Set Fecha_Estatus.
		@param Fecha_Estatus Fecha_Estatus	  */
	public void setFecha_Estatus (Timestamp Fecha_Estatus)
	{
		set_Value (COLUMNNAME_Fecha_Estatus, Fecha_Estatus);
	}

	/** Get Fecha_Estatus.
		@return Fecha_Estatus	  */
	public Timestamp getFecha_Estatus () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Estatus);
	}

	/** Set Final Execution Date Transplantation.
		@param Fecha_Final 
		Final Execution Date Transplantation
	  */
	public void setFecha_Final (Timestamp Fecha_Final)
	{
		set_Value (COLUMNNAME_Fecha_Final, Fecha_Final);
	}

	/** Get Final Execution Date Transplantation.
		@return Final Execution Date Transplantation
	  */
	public Timestamp getFecha_Final () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Final);
	}

	/** Set Is Nosocomial.
		@param IsNosocomial Is Nosocomial	  */
	public void setIsNosocomial (boolean IsNosocomial)
	{
		set_Value (COLUMNNAME_IsNosocomial, Boolean.valueOf(IsNosocomial));
	}

	/** Get Is Nosocomial.
		@return Is Nosocomial	  */
	public boolean isNosocomial () 
	{
		Object oo = get_Value(COLUMNNAME_IsNosocomial);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Condition.
		@param Padecimiento Condition	  */
	public void setPadecimiento (boolean Padecimiento)
	{
		set_Value (COLUMNNAME_Padecimiento, Boolean.valueOf(Padecimiento));
	}

	/** Get Condition.
		@return Condition	  */
	public boolean isPadecimiento () 
	{
		Object oo = get_Value(COLUMNNAME_Padecimiento);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SEQUENCEDIAG.
		@param SEQUENCEDIAG SEQUENCEDIAG	  */
	public void setSEQUENCEDIAG (int SEQUENCEDIAG)
	{
		set_Value (COLUMNNAME_SEQUENCEDIAG, Integer.valueOf(SEQUENCEDIAG));
	}

	/** Get SEQUENCEDIAG.
		@return SEQUENCEDIAG	  */
	public int getSEQUENCEDIAG () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SEQUENCEDIAG);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}