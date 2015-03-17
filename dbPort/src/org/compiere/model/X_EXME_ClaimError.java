/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ClaimError
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimError extends PO implements I_EXME_ClaimError, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimError (Properties ctx, int EXME_ClaimError_ID, String trxName)
    {
      super (ctx, EXME_ClaimError_ID, trxName);
      /** if (EXME_ClaimError_ID == 0)
        {
			setCode (null);
			setErrorDescription (null);
			setErrorStatus (true);
// Y
			setEXME_ClaimError_ID (0);
			setEXME_CtaPac_ID (0);
			setType (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimError (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimError[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Column_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		if (Code == null)
			throw new IllegalArgumentException ("Code is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_ValueNoCheck (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** ConfType AD_Reference_ID=1200425 */
	public static final int CONFTYPE_AD_Reference_ID=1200425;
	/** Surveillance Report = W */
	public static final String CONFTYPE_SurveillanceReport = "W";
	/** Clinical Record CDA = C */
	public static final String CONFTYPE_ClinicalRecordCDA = "C";
	/** Clinical Record CCR = R */
	public static final String CONFTYPE_ClinicalRecordCCR = "R";
	/** Elegibility = E */
	public static final String CONFTYPE_Elegibility = "E";
	/** Reportable Lab Results = L */
	public static final String CONFTYPE_ReportableLabResults = "L";
	/** Inmunization Messaging = I */
	public static final String CONFTYPE_InmunizationMessaging = "I";
	/** RX Script = S */
	public static final String CONFTYPE_RXScript = "S";
	/** LabDAQ = Q */
	public static final String CONFTYPE_LabDAQ = "Q";
	/** Professional Claim = P */
	public static final String CONFTYPE_ProfessionalClaim = "P";
	/** Institutional Claim = T */
	public static final String CONFTYPE_InstitutionalClaim = "T";
	/** PIStatement = A */
	public static final String CONFTYPE_PIStatement = "A";
	/** Set Configuration Type.
		@param ConfType Configuration Type	  */
	public void setConfType (String ConfType)
	{

		if (ConfType == null || ConfType.equals("W") || ConfType.equals("C") || ConfType.equals("R") || ConfType.equals("E") || ConfType.equals("L") || ConfType.equals("I") || ConfType.equals("S") || ConfType.equals("Q") || ConfType.equals("P") || ConfType.equals("T") || ConfType.equals("A")); else throw new IllegalArgumentException ("ConfType Invalid value - " + ConfType + " - Reference_ID=1200425 - W - C - R - E - L - I - S - Q - P - T - A");		set_Value (COLUMNNAME_ConfType, ConfType);
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public String getConfType () 
	{
		return (String)get_Value(COLUMNNAME_ConfType);
	}

	/** Set ErrorDescription.
		@param ErrorDescription 
		ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription)
	{
		if (ErrorDescription == null)
			throw new IllegalArgumentException ("ErrorDescription is mandatory.");
		set_ValueNoCheck (COLUMNNAME_ErrorDescription, ErrorDescription);
	}

	/** Get ErrorDescription.
		@return ErrorDescription
	  */
	public String getErrorDescription () 
	{
		return (String)get_Value(COLUMNNAME_ErrorDescription);
	}

	/** Set ErrorStatus.
		@param ErrorStatus 
		ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus)
	{
		set_ValueNoCheck (COLUMNNAME_ErrorStatus, Boolean.valueOf(ErrorStatus));
	}

	/** Get ErrorStatus.
		@return ErrorStatus
	  */
	public boolean isErrorStatus () 
	{
		Object oo = get_Value(COLUMNNAME_ErrorStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set EXME_ClaimError_ID.
		@param EXME_ClaimError_ID EXME_ClaimError_ID	  */
	public void setEXME_ClaimError_ID (int EXME_ClaimError_ID)
	{
		if (EXME_ClaimError_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimError_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimError_ID, Integer.valueOf(EXME_ClaimError_ID));
	}

	/** Get EXME_ClaimError_ID.
		@return EXME_ClaimError_ID	  */
	public int getEXME_ClaimError_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimError_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
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

	/** Type AD_Reference_ID=1200570 */
	public static final int TYPE_AD_Reference_ID=1200570;
	/** Mandatory Fields = MF */
	public static final String TYPE_MandatoryFields = "MF";
	/** Message Response = MR */
	public static final String TYPE_MessageResponse = "MR";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("MF") || Type.equals("MR")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200570 - MF - MR");		set_ValueNoCheck (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}