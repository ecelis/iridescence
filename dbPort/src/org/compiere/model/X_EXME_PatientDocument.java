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

/** Generated Model for EXME_PatientDocument
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PatientDocument extends PO implements I_EXME_PatientDocument, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PatientDocument (Properties ctx, int EXME_PatientDocument_ID, String trxName)
    {
      super (ctx, EXME_PatientDocument_ID, trxName);
      /** if (EXME_PatientDocument_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_PatientDocument_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PatientDocument (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PatientDocument[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Action AD_Reference_ID=1200545 */
	public static final int ACTION_AD_Reference_ID=1200545;
	/** CCD Request = R */
	public static final String ACTION_CCDRequest = "R";
	/** CCD Load = L */
	public static final String ACTION_CCDLoad = "L";
	/** Set Action.
		@param Action 
		Indicates the Action to be performed
	  */
	public void setAction (String Action)
	{

		if (Action == null || Action.equals("R") || Action.equals("L")); else throw new IllegalArgumentException ("Action Invalid value - " + Action + " - Reference_ID=1200545 - R - L");		set_Value (COLUMNNAME_Action, Action);
	}

	/** Get Action.
		@return Indicates the Action to be performed
	  */
	public String getAction () 
	{
		return (String)get_Value(COLUMNNAME_Action);
	}

	/** Set File.
		@param Archivo File	  */
	public void setArchivo (byte[] Archivo)
	{
		set_Value (COLUMNNAME_Archivo, Archivo);
	}

	/** Get File.
		@return File	  */
	public byte[] getArchivo () 
	{
		return (byte[])get_Value(COLUMNNAME_Archivo);
	}

	/** Set Delivery Date.
		@param DeliveryDate Delivery Date	  */
	public void setDeliveryDate (Timestamp DeliveryDate)
	{
		set_Value (COLUMNNAME_DeliveryDate, DeliveryDate);
	}

	/** Get Delivery Date.
		@return Delivery Date	  */
	public Timestamp getDeliveryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DeliveryDate);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Document Patient.
		@param EXME_PatientDocument_ID Type of Document Patient	  */
	public void setEXME_PatientDocument_ID (int EXME_PatientDocument_ID)
	{
		if (EXME_PatientDocument_ID < 1)
			 throw new IllegalArgumentException ("EXME_PatientDocument_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PatientDocument_ID, Integer.valueOf(EXME_PatientDocument_ID));
	}

	/** Get Type of Document Patient.
		@return Type of Document Patient	  */
	public int getEXME_PatientDocument_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientDocument_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Format.
		@param FormatoArchivo 
		File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo)
	{
		set_Value (COLUMNNAME_FormatoArchivo, FormatoArchivo);
	}

	/** Get File Format.
		@return File Format
	  */
	public String getFormatoArchivo () 
	{
		return (String)get_Value(COLUMNNAME_FormatoArchivo);
	}

	/** Set File Name.
		@param NombreArchivo 
		File Name
	  */
	public void setNombreArchivo (String NombreArchivo)
	{
		set_Value (COLUMNNAME_NombreArchivo, NombreArchivo);
	}

	/** Get File Name.
		@return File Name
	  */
	public String getNombreArchivo () 
	{
		return (String)get_Value(COLUMNNAME_NombreArchivo);
	}

	/** Set Request Date.
		@param RequestDate Request Date	  */
	public void setRequestDate (Timestamp RequestDate)
	{
		set_Value (COLUMNNAME_RequestDate, RequestDate);
	}

	/** Get Request Date.
		@return Request Date	  */
	public Timestamp getRequestDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RequestDate);
	}

	/** Set Requester.
		@param Requester Requester	  */
	public void setRequester (String Requester)
	{
		set_Value (COLUMNNAME_Requester, Requester);
	}

	/** Get Requester.
		@return Requester	  */
	public String getRequester () 
	{
		return (String)get_Value(COLUMNNAME_Requester);
	}

	/** Type AD_Reference_ID=1200544 */
	public static final int TYPE_AD_Reference_ID=1200544;
	/** PHR = C */
	public static final String TYPE_PHR = "C";
	/** Summary Record = S */
	public static final String TYPE_SummaryRecord = "S";
	/** Discharge instructions = D */
	public static final String TYPE_DischargeInstructions = "D";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("C") || Type.equals("S") || Type.equals("D")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200544 - C - S - D");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}