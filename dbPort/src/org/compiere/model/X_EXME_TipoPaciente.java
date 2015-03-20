/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TipoPaciente
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_TipoPaciente extends PO implements I_EXME_TipoPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoPaciente (Properties ctx, int EXME_TipoPaciente_ID, String trxName)
    {
      super (ctx, EXME_TipoPaciente_ID, trxName);
      /** if (EXME_TipoPaciente_ID == 0)
        {
			setEXME_TipoPaciente_ID (0);
			setName (null);
			setTipoArea (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoPaciente (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
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

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdmitType.Table_Name);
        I_EXME_AdmitType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdmitType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdmitType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Admit Type.
		@param EXME_AdmitType_ID 
		Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID)
	{
		if (EXME_AdmitType_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdmitType_ID, Integer.valueOf(EXME_AdmitType_ID));
	}

	/** Get Admit Type.
		@return Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_POS getEXME_POS() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_POS.Table_Name);
        I_EXME_POS result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_POS)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_POS_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Place of Service.
		@param EXME_POS_ID 
		Place of Service
	  */
	public void setEXME_POS_ID (int EXME_POS_ID)
	{
		if (EXME_POS_ID < 1) 
			set_Value (COLUMNNAME_EXME_POS_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_POS_ID, Integer.valueOf(EXME_POS_ID));
	}

	/** Get Place of Service.
		@return Place of Service
	  */
	public int getEXME_POS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_POS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Patient.
		@param EXME_TipoPaciente_ID 
		Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID)
	{
		if (EXME_TipoPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoPaciente_ID, Integer.valueOf(EXME_TipoPaciente_ID));
	}

	/** Get Type of Patient.
		@return Type of Patient
	  */
	public int getEXME_TipoPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type Of Bill.
		@param EXME_TypeOfBill_ID 
		Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID)
	{
		if (EXME_TypeOfBill_ID < 1) 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, Integer.valueOf(EXME_TypeOfBill_ID));
	}

	/** Get Type Of Bill.
		@return Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TypeOfBill_ID);
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

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
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