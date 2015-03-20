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

/** Generated Model for EXME_Paciente_Cta_V
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Paciente_Cta_V extends PO implements I_EXME_Paciente_Cta_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente_Cta_V (Properties ctx, int EXME_Paciente_Cta_V_ID, String trxName)
    {
      super (ctx, EXME_Paciente_Cta_V_ID, trxName);
      /** if (EXME_Paciente_Cta_V_ID == 0)
        {
			setAdmitDate (new Timestamp( System.currentTimeMillis() ));
			setApellido1 (null);
			setC_BPartnerPac_ID (0);
			setCBPIns_ID (0);
			setCBPIns_Name (null);
			setCBPIns_Value (null);
			setCBPProf_ID (0);
			setCBPProf_Name (null);
			setCBPProf_Value (null);
			setCPCIns_Name (null);
			setCPCIns_Value (null);
			setCPCProf_Name (null);
			setCPCProf_Value (null);
			setCtaPacDoc (null);
			setEXME_CtaPac_ID (0);
			setEXME_Paciente_Cta_V_ID (0);
			setEXME_Paciente_ID (0);
			setHistoria (null);
			setInstitutionalStatus (null);
			setInstitutionalStatus_Name (null);
			setInstitutionalStep (null);
			setInstitutionalStep_Name (null);
			setMRN (null);
			setNombre (null);
			setPatientType (null);
			setProfessionalStatus (null);
			setProfessionalStatus_Name (null);
			setProfessionalStep (null);
			setProfessionalStep_Name (null);
			setSexo (false);
			setStatus (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente_Cta_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente_Cta_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Admit Date.
		@param AdmitDate 
		Admit Date
	  */
	public void setAdmitDate (Timestamp AdmitDate)
	{
		if (AdmitDate == null)
			throw new IllegalArgumentException ("AdmitDate is mandatory.");
		set_Value (COLUMNNAME_AdmitDate, AdmitDate);
	}

	/** Get Admit Date.
		@return Admit Date
	  */
	public Timestamp getAdmitDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AdmitDate);
	}

	/** Set Last Name.
		@param Apellido1 
		Last Name
	  */
	public void setApellido1 (String Apellido1)
	{
		if (Apellido1 == null)
			throw new IllegalArgumentException ("Apellido1 is mandatory.");
		set_ValueE (COLUMNNAME_Apellido1, Apellido1);
	}

	/** Get Last Name.
		@return Last Name
	  */
	public String getApellido1 () 
	{
		return (String)get_ValueE(COLUMNNAME_Apellido1);
	}

	/** Set Company of the Patient.
		@param C_BPartnerPac_ID Company of the Patient	  */
	public void setC_BPartnerPac_ID (int C_BPartnerPac_ID)
	{
		if (C_BPartnerPac_ID < 1)
			 throw new IllegalArgumentException ("C_BPartnerPac_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartnerPac_ID, Integer.valueOf(C_BPartnerPac_ID));
	}

	/** Get Company of the Patient.
		@return Company of the Patient	  */
	public int getC_BPartnerPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insurance ID (INS).
		@param CBPIns_ID Insurance ID (INS)	  */
	public void setCBPIns_ID (int CBPIns_ID)
	{
		if (CBPIns_ID < 1)
			 throw new IllegalArgumentException ("CBPIns_ID is mandatory.");
		set_Value (COLUMNNAME_CBPIns_ID, Integer.valueOf(CBPIns_ID));
	}

	/** Get Insurance ID (INS).
		@return Insurance ID (INS)	  */
	public int getCBPIns_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CBPIns_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insurance Name (INS).
		@param CBPIns_Name Insurance Name (INS)	  */
	public void setCBPIns_Name (String CBPIns_Name)
	{
		if (CBPIns_Name == null)
			throw new IllegalArgumentException ("CBPIns_Name is mandatory.");
		set_Value (COLUMNNAME_CBPIns_Name, CBPIns_Name);
	}

	/** Get Insurance Name (INS).
		@return Insurance Name (INS)	  */
	public String getCBPIns_Name () 
	{
		return (String)get_Value(COLUMNNAME_CBPIns_Name);
	}

	/** Set Payer ID (INS).
		@param CBPIns_PayerID Payer ID (INS)	  */
	public void setCBPIns_PayerID (String CBPIns_PayerID)
	{
		set_Value (COLUMNNAME_CBPIns_PayerID, CBPIns_PayerID);
	}

	/** Get Payer ID (INS).
		@return Payer ID (INS)	  */
	public String getCBPIns_PayerID () 
	{
		return (String)get_Value(COLUMNNAME_CBPIns_PayerID);
	}

	/** Set Insurance Value (INS).
		@param CBPIns_Value Insurance Value (INS)	  */
	public void setCBPIns_Value (String CBPIns_Value)
	{
		if (CBPIns_Value == null)
			throw new IllegalArgumentException ("CBPIns_Value is mandatory.");
		set_Value (COLUMNNAME_CBPIns_Value, CBPIns_Value);
	}

	/** Get Insurance Value (INS).
		@return Insurance Value (INS)	  */
	public String getCBPIns_Value () 
	{
		return (String)get_Value(COLUMNNAME_CBPIns_Value);
	}

	/** Set Insurance ID (PROF).
		@param CBPProf_ID Insurance ID (PROF)	  */
	public void setCBPProf_ID (int CBPProf_ID)
	{
		if (CBPProf_ID < 1)
			 throw new IllegalArgumentException ("CBPProf_ID is mandatory.");
		set_Value (COLUMNNAME_CBPProf_ID, Integer.valueOf(CBPProf_ID));
	}

	/** Get Insurance ID (PROF).
		@return Insurance ID (PROF)	  */
	public int getCBPProf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CBPProf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insurance Name (PROF).
		@param CBPProf_Name Insurance Name (PROF)	  */
	public void setCBPProf_Name (String CBPProf_Name)
	{
		if (CBPProf_Name == null)
			throw new IllegalArgumentException ("CBPProf_Name is mandatory.");
		set_Value (COLUMNNAME_CBPProf_Name, CBPProf_Name);
	}

	/** Get Insurance Name (PROF).
		@return Insurance Name (PROF)	  */
	public String getCBPProf_Name () 
	{
		return (String)get_Value(COLUMNNAME_CBPProf_Name);
	}

	/** Set Payer ID (PROF).
		@param CBPProf_PayerID Payer ID (PROF)	  */
	public void setCBPProf_PayerID (String CBPProf_PayerID)
	{
		set_Value (COLUMNNAME_CBPProf_PayerID, CBPProf_PayerID);
	}

	/** Get Payer ID (PROF).
		@return Payer ID (PROF)	  */
	public String getCBPProf_PayerID () 
	{
		return (String)get_Value(COLUMNNAME_CBPProf_PayerID);
	}

	/** Set Insurance Value (PROF).
		@param CBPProf_Value Insurance Value (PROF)	  */
	public void setCBPProf_Value (String CBPProf_Value)
	{
		if (CBPProf_Value == null)
			throw new IllegalArgumentException ("CBPProf_Value is mandatory.");
		set_Value (COLUMNNAME_CBPProf_Value, CBPProf_Value);
	}

	/** Get Insurance Value (PROF).
		@return Insurance Value (PROF)	  */
	public String getCBPProf_Value () 
	{
		return (String)get_Value(COLUMNNAME_CBPProf_Value);
	}

	/** Set Charge Status.
		@param ChargeStatus Charge Status	  */
	public void setChargeStatus (boolean ChargeStatus)
	{
		set_Value (COLUMNNAME_ChargeStatus, Boolean.valueOf(ChargeStatus));
	}

	/** Get Charge Status.
		@return Charge Status	  */
	public boolean isChargeStatus () 
	{
		Object oo = get_Value(COLUMNNAME_ChargeStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set PayerClass Name (INS).
		@param CPCIns_Name PayerClass Name (INS)	  */
	public void setCPCIns_Name (String CPCIns_Name)
	{
		if (CPCIns_Name == null)
			throw new IllegalArgumentException ("CPCIns_Name is mandatory.");
		set_Value (COLUMNNAME_CPCIns_Name, CPCIns_Name);
	}

	/** Get PayerClass Name (INS).
		@return PayerClass Name (INS)	  */
	public String getCPCIns_Name () 
	{
		return (String)get_Value(COLUMNNAME_CPCIns_Name);
	}

	/** Set PayerClass Value (INS).
		@param CPCIns_Value PayerClass Value (INS)	  */
	public void setCPCIns_Value (String CPCIns_Value)
	{
		if (CPCIns_Value == null)
			throw new IllegalArgumentException ("CPCIns_Value is mandatory.");
		set_Value (COLUMNNAME_CPCIns_Value, CPCIns_Value);
	}

	/** Get PayerClass Value (INS).
		@return PayerClass Value (INS)	  */
	public String getCPCIns_Value () 
	{
		return (String)get_Value(COLUMNNAME_CPCIns_Value);
	}

	/** Set PayerClass Name (PROF).
		@param CPCProf_Name PayerClass Name (PROF)	  */
	public void setCPCProf_Name (String CPCProf_Name)
	{
		if (CPCProf_Name == null)
			throw new IllegalArgumentException ("CPCProf_Name is mandatory.");
		set_Value (COLUMNNAME_CPCProf_Name, CPCProf_Name);
	}

	/** Get PayerClass Name (PROF).
		@return PayerClass Name (PROF)	  */
	public String getCPCProf_Name () 
	{
		return (String)get_Value(COLUMNNAME_CPCProf_Name);
	}

	/** Set PayerClass Value (PROF).
		@param CPCProf_Value PayerClass Value (PROF)	  */
	public void setCPCProf_Value (String CPCProf_Value)
	{
		if (CPCProf_Value == null)
			throw new IllegalArgumentException ("CPCProf_Value is mandatory.");
		set_Value (COLUMNNAME_CPCProf_Value, CPCProf_Value);
	}

	/** Get PayerClass Value (PROF).
		@return PayerClass Value (PROF)	  */
	public String getCPCProf_Value () 
	{
		return (String)get_Value(COLUMNNAME_CPCProf_Value);
	}

	/** Set CtaPacDoc.
		@param CtaPacDoc CtaPacDoc	  */
	public void setCtaPacDoc (String CtaPacDoc)
	{
		if (CtaPacDoc == null)
			throw new IllegalArgumentException ("CtaPacDoc is mandatory.");
		set_Value (COLUMNNAME_CtaPacDoc, CtaPacDoc);
	}

	/** Get CtaPacDoc.
		@return CtaPacDoc	  */
	public String getCtaPacDoc () 
	{
		return (String)get_Value(COLUMNNAME_CtaPacDoc);
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

	/** EncounterStatus AD_Reference_ID=1200542 */
	public static final int ENCOUNTERSTATUS_AD_Reference_ID=1200542;
	/** Pre Admission = A */
	public static final String ENCOUNTERSTATUS_PreAdmission = "A";
	/** Admission = I */
	public static final String ENCOUNTERSTATUS_Admission = "I";
	/** Predischarge = P */
	public static final String ENCOUNTERSTATUS_Predischarge = "P";
	/** Discharge = D */
	public static final String ENCOUNTERSTATUS_Discharge = "D";
	/** Abstracting = B */
	public static final String ENCOUNTERSTATUS_Abstracting = "B";
	/** Ready To Bill = R */
	public static final String ENCOUNTERSTATUS_ReadyToBill = "R";
	/** Billing = F */
	public static final String ENCOUNTERSTATUS_Billing = "F";
	/** Waiting Message 835 = W */
	public static final String ENCOUNTERSTATUS_WaitingMessage835 = "W";
	/** Error, Mandatory Fields = M */
	public static final String ENCOUNTERSTATUS_ErrorMandatoryFields = "M";
	/** OK, Mandatory Fields = C */
	public static final String ENCOUNTERSTATUS_OKMandatoryFields = "C";
	/** Error, Message 997 = 9 */
	public static final String ENCOUNTERSTATUS_ErrorMessage997 = "9";
	/** Ok, Message 997 = O */
	public static final String ENCOUNTERSTATUS_OkMessage997 = "O";
	/** Error, RSP File = E */
	public static final String ENCOUNTERSTATUS_ErrorRSPFile = "E";
	/** Ok, RSP File = K */
	public static final String ENCOUNTERSTATUS_OkRSPFile = "K";
	/** Waiting 835, First Insurance = X */
	public static final String ENCOUNTERSTATUS_Waiting835FirstInsurance = "X";
	/** Waiting 835, Second Insurance = Y */
	public static final String ENCOUNTERSTATUS_Waiting835SecondInsurance = "Y";
	/** Waiting 835, Third Insurance = Z */
	public static final String ENCOUNTERSTATUS_Waiting835ThirdInsurance = "Z";
	/** 835 Received, First Insurance = 1 */
	public static final String ENCOUNTERSTATUS_835ReceivedFirstInsurance = "1";
	/** 835 Received, Second Insurance = 2 */
	public static final String ENCOUNTERSTATUS_835ReceivedSecondInsurance = "2";
	/** 835 Received, Third Insurance = 3 */
	public static final String ENCOUNTERSTATUS_835ReceivedThirdInsurance = "3";
	/** Waiting Patient Payments = G */
	public static final String ENCOUNTERSTATUS_WaitingPatientPayments = "G";
	/** Close = L */
	public static final String ENCOUNTERSTATUS_Close = "L";
	/** Returned to Abstracting = N */
	public static final String ENCOUNTERSTATUS_ReturnedToAbstracting = "N";
	/** Balanced = Q */
	public static final String ENCOUNTERSTATUS_Balanced = "Q";
	/** Set Encounter Status.
		@param EncounterStatus Encounter Status	  */
	public void setEncounterStatus (String EncounterStatus)
	{

		if (EncounterStatus == null || EncounterStatus.equals("A") || EncounterStatus.equals("I") || EncounterStatus.equals("P") || EncounterStatus.equals("D") || EncounterStatus.equals("B") || EncounterStatus.equals("R") || EncounterStatus.equals("F") || EncounterStatus.equals("W") || EncounterStatus.equals("M") || EncounterStatus.equals("C") || EncounterStatus.equals("9") || EncounterStatus.equals("O") || EncounterStatus.equals("E") || EncounterStatus.equals("K") || EncounterStatus.equals("X") || EncounterStatus.equals("Y") || EncounterStatus.equals("Z") || EncounterStatus.equals("1") || EncounterStatus.equals("2") || EncounterStatus.equals("3") || EncounterStatus.equals("G") || EncounterStatus.equals("L") || EncounterStatus.equals("N") || EncounterStatus.equals("Q")); else throw new IllegalArgumentException ("EncounterStatus Invalid value - " + EncounterStatus + " - Reference_ID=1200542 - A - I - P - D - B - R - F - W - M - C - 9 - O - E - K - X - Y - Z - 1 - 2 - 3 - G - L - N - Q");		set_Value (COLUMNNAME_EncounterStatus, EncounterStatus);
	}

	/** Get Encounter Status.
		@return Encounter Status	  */
	public String getEncounterStatus () 
	{
		return (String)get_Value(COLUMNNAME_EncounterStatus);
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

	/** Set EXME_Paciente_Cta_V_ID.
		@param EXME_Paciente_Cta_V_ID EXME_Paciente_Cta_V_ID	  */
	public void setEXME_Paciente_Cta_V_ID (int EXME_Paciente_Cta_V_ID)
	{
		if (EXME_Paciente_Cta_V_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_Cta_V_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_Cta_V_ID, Integer.valueOf(EXME_Paciente_Cta_V_ID));
	}

	/** Get EXME_Paciente_Cta_V_ID.
		@return EXME_Paciente_Cta_V_ID	  */
	public int getEXME_Paciente_Cta_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Cta_V_ID);
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

	/** Set Discharge Date.
		@param FechaAlta 
		Discharge Date
	  */
	public void setFechaAlta (Timestamp FechaAlta)
	{
		set_Value (COLUMNNAME_FechaAlta, FechaAlta);
	}

	/** Get Discharge Date.
		@return Discharge Date
	  */
	public Timestamp getFechaAlta () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAlta);
	}

	/** Set Birth Date.
		@param FechaNac 
		Birth Date
	  */
	public void setFechaNac (String FechaNac)
	{
		set_Value (COLUMNNAME_FechaNac, FechaNac);
	}

	/** Get Birth Date.
		@return Birth Date
	  */
	public String getFechaNac () 
	{
		return (String)get_Value(COLUMNNAME_FechaNac);
	}

	/** Set Guarantor Name.
		@param Guar_Name Guarantor Name	  */
	public void setGuar_Name (String Guar_Name)
	{
		set_ValueE (COLUMNNAME_Guar_Name, Guar_Name);
	}

	/** Get Guarantor Name.
		@return Guarantor Name	  */
	public String getGuar_Name () 
	{
		return (String)get_ValueE(COLUMNNAME_Guar_Name);
	}

	/** Set Unique Patient Identification.
		@param Historia 
		Unique Patient Identification
	  */
	public void setHistoria (String Historia)
	{
		if (Historia == null)
			throw new IllegalArgumentException ("Historia is mandatory.");
		set_Value (COLUMNNAME_Historia, Historia);
	}

	/** Get Unique Patient Identification.
		@return Unique Patient Identification
	  */
	public String getHistoria () 
	{
		return (String)get_Value(COLUMNNAME_Historia);
	}

	/** InstitutionalStatus AD_Reference_ID=1200581 */
	public static final int INSTITUTIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String INSTITUTIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String INSTITUTIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String INSTITUTIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String INSTITUTIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String INSTITUTIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String INSTITUTIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String INSTITUTIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String INSTITUTIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String INSTITUTIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String INSTITUTIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String INSTITUTIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String INSTITUTIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String INSTITUTIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String INSTITUTIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String INSTITUTIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String INSTITUTIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String INSTITUTIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String INSTITUTIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String INSTITUTIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String INSTITUTIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String INSTITUTIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String INSTITUTIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String INSTITUTIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String INSTITUTIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String INSTITUTIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String INSTITUTIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String INSTITUTIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String INSTITUTIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String INSTITUTIONALSTATUS_ZeroBalance = "24";
	/** Set Institutional Status.
		@param InstitutionalStatus 
		Institutional Status
	  */
	public void setInstitutionalStatus (String InstitutionalStatus)
	{
		if (InstitutionalStatus == null) throw new IllegalArgumentException ("InstitutionalStatus is mandatory");
		if (InstitutionalStatus.equals("4") || InstitutionalStatus.equals("3") || InstitutionalStatus.equals("11") || InstitutionalStatus.equals("7") || InstitutionalStatus.equals("12") || InstitutionalStatus.equals("13") || InstitutionalStatus.equals("10") || InstitutionalStatus.equals("14") || InstitutionalStatus.equals("18") || InstitutionalStatus.equals("RSI") || InstitutionalStatus.equals("16") || InstitutionalStatus.equals("RTI") || InstitutionalStatus.equals("15") || InstitutionalStatus.equals("25") || InstitutionalStatus.equals("WSI") || InstitutionalStatus.equals("WTI") || InstitutionalStatus.equals("2") || InstitutionalStatus.equals("1") || InstitutionalStatus.equals("5") || InstitutionalStatus.equals("6") || InstitutionalStatus.equals("8") || InstitutionalStatus.equals("9") || InstitutionalStatus.equals("17") || InstitutionalStatus.equals("23") || InstitutionalStatus.equals("19") || InstitutionalStatus.equals("21") || InstitutionalStatus.equals("20") || InstitutionalStatus.equals("22") || InstitutionalStatus.equals("24")); else throw new IllegalArgumentException ("InstitutionalStatus Invalid value - " + InstitutionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_Value (COLUMNNAME_InstitutionalStatus, InstitutionalStatus);
	}

	/** Get Institutional Status.
		@return Institutional Status
	  */
	public String getInstitutionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStatus);
	}

	/** Set Institutional Status Name.
		@param InstitutionalStatus_Name Institutional Status Name	  */
	public void setInstitutionalStatus_Name (String InstitutionalStatus_Name)
	{
		if (InstitutionalStatus_Name == null)
			throw new IllegalArgumentException ("InstitutionalStatus_Name is mandatory.");
		set_Value (COLUMNNAME_InstitutionalStatus_Name, InstitutionalStatus_Name);
	}

	/** Get Institutional Status Name.
		@return Institutional Status Name	  */
	public String getInstitutionalStatus_Name () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStatus_Name);
	}

	/** InstitutionalStep AD_Reference_ID=1200595 */
	public static final int INSTITUTIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String INSTITUTIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String INSTITUTIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String INSTITUTIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String INSTITUTIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String INSTITUTIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String INSTITUTIONALSTEP_SelftPay = "P";
	/** Set Institutional Step.
		@param InstitutionalStep 
		Insurance that has the liability for institutional charges
	  */
	public void setInstitutionalStep (String InstitutionalStep)
	{
		if (InstitutionalStep == null) throw new IllegalArgumentException ("InstitutionalStep is mandatory");
		if (InstitutionalStep.equals("D") || InstitutionalStep.equals("F") || InstitutionalStep.equals("S") || InstitutionalStep.equals("T") || InstitutionalStep.equals("O") || InstitutionalStep.equals("P")); else throw new IllegalArgumentException ("InstitutionalStep Invalid value - " + InstitutionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_Value (COLUMNNAME_InstitutionalStep, InstitutionalStep);
	}

	/** Get Institutional Step.
		@return Insurance that has the liability for institutional charges
	  */
	public String getInstitutionalStep () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStep);
	}

	/** Set Institutional Step Name.
		@param InstitutionalStep_Name Institutional Step Name	  */
	public void setInstitutionalStep_Name (String InstitutionalStep_Name)
	{
		if (InstitutionalStep_Name == null)
			throw new IllegalArgumentException ("InstitutionalStep_Name is mandatory.");
		set_Value (COLUMNNAME_InstitutionalStep_Name, InstitutionalStep_Name);
	}

	/** Get Institutional Step Name.
		@return Institutional Step Name	  */
	public String getInstitutionalStep_Name () 
	{
		return (String)get_Value(COLUMNNAME_InstitutionalStep_Name);
	}

	/** Set Last_Name.
		@param Last_Name Last_Name	  */
	public void setLast_Name (String Last_Name)
	{
		set_ValueE (COLUMNNAME_Last_Name, Last_Name);
	}

	/** Get Last_Name.
		@return Last_Name	  */
	public String getLast_Name () 
	{
		return (String)get_ValueE(COLUMNNAME_Last_Name);
	}

	/** Set Medical Record Number.
		@param MRN 
		Medical Record Number
	  */
	public void setMRN (String MRN)
	{
		if (MRN == null)
			throw new IllegalArgumentException ("MRN is mandatory.");
		set_Value (COLUMNNAME_MRN, MRN);
	}

	/** Get Medical Record Number.
		@return Medical Record Number
	  */
	public String getMRN () 
	{
		return (String)get_Value(COLUMNNAME_MRN);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_ValueE (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_ValueE(COLUMNNAME_Name);
	}

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_ValueE (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_ValueE(COLUMNNAME_Name2);
	}

	/** Set Name.
		@param Nombre 
		Name of friend
	  */
	public void setNombre (String Nombre)
	{
		if (Nombre == null)
			throw new IllegalArgumentException ("Nombre is mandatory.");
		set_ValueE (COLUMNNAME_Nombre, Nombre);
	}

	/** Get Name.
		@return Name of friend
	  */
	public String getNombre () 
	{
		return (String)get_ValueE(COLUMNNAME_Nombre);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_ValueE (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_ValueE(COLUMNNAME_Nombre_Pac);
	}

	/** Set Social Security Number.
		@param NSS Social Security Number	  */
	public void setNSS (String NSS)
	{
		set_Value (COLUMNNAME_NSS, NSS);
	}

	/** Get Social Security Number.
		@return Social Security Number	  */
	public String getNSS () 
	{
		return (String)get_Value(COLUMNNAME_NSS);
	}

	/** Set PatientType.
		@param PatientType PatientType	  */
	public void setPatientType (String PatientType)
	{
		if (PatientType == null)
			throw new IllegalArgumentException ("PatientType is mandatory.");
		set_Value (COLUMNNAME_PatientType, PatientType);
	}

	/** Get PatientType.
		@return PatientType	  */
	public String getPatientType () 
	{
		return (String)get_Value(COLUMNNAME_PatientType);
	}

	/** ProfessionalStatus AD_Reference_ID=1200581 */
	public static final int PROFESSIONALSTATUS_AD_Reference_ID=1200581;
	/** Coding Complete = 4 */
	public static final String PROFESSIONALSTATUS_CodingComplete = "4";
	/** Coding Incomplete = 3 */
	public static final String PROFESSIONALSTATUS_CodingIncomplete = "3";
	/** Error, Message 997 = 11 */
	public static final String PROFESSIONALSTATUS_ErrorMessage997 = "11";
	/** Error, Mandatory Fields = 7 */
	public static final String PROFESSIONALSTATUS_ErrorMandatoryFields = "7";
	/** Error RSP File = 12 */
	public static final String PROFESSIONALSTATUS_ErrorRSPFile = "12";
	/** OK, Message 997 = 13 */
	public static final String PROFESSIONALSTATUS_OKMessage997 = "13";
	/** OK, Mandatory Fields = 10 */
	public static final String PROFESSIONALSTATUS_OKMandatoryFields = "10";
	/** OK, RSP File = 14 */
	public static final String PROFESSIONALSTATUS_OKRSPFile = "14";
	/** 835 Received = 18 */
	public static final String PROFESSIONALSTATUS_835Received = "18";
	/** 835 Received Second Insurance = RSI */
	public static final String PROFESSIONALSTATUS_835ReceivedSecondInsurance = "RSI";
	/** Returned to Abstracting = 16 */
	public static final String PROFESSIONALSTATUS_ReturnedToAbstracting = "16";
	/** 835 Received Third Insurance = RTI */
	public static final String PROFESSIONALSTATUS_835ReceivedThirdInsurance = "RTI";
	/** Waiting Insurance Payments = 15 */
	public static final String PROFESSIONALSTATUS_WaitingInsurancePayments = "15";
	/** Waiting Guarantor Payments = 25 */
	public static final String PROFESSIONALSTATUS_WaitingGuarantorPayments = "25";
	/** Waiting 835 Second Insurance = WSI */
	public static final String PROFESSIONALSTATUS_Waiting835SecondInsurance = "WSI";
	/** Waiting 835 Third Insurance = WTI */
	public static final String PROFESSIONALSTATUS_Waiting835ThirdInsurance = "WTI";
	/** Not Applicable = 2 */
	public static final String PROFESSIONALSTATUS_NotApplicable = "2";
	/** Not Billed = 1 */
	public static final String PROFESSIONALSTATUS_NotBilled = "1";
	/** Error, Orders Incomplete = 5 */
	public static final String PROFESSIONALSTATUS_ErrorOrdersIncomplete = "5";
	/** Error, Prices in Zero = 6 */
	public static final String PROFESSIONALSTATUS_ErrorPricesInZero = "6";
	/** OK, Orders Complete = 8 */
	public static final String PROFESSIONALSTATUS_OKOrdersComplete = "8";
	/** OK, Prices in Zero = 9 */
	public static final String PROFESSIONALSTATUS_OKPricesInZero = "9";
	/** Returned to Coding = 17 */
	public static final String PROFESSIONALSTATUS_ReturnedToCoding = "17";
	/** Payment Plan = 23 */
	public static final String PROFESSIONALSTATUS_PaymentPlan = "23";
	/** Self Pay = 19 */
	public static final String PROFESSIONALSTATUS_SelfPay = "19";
	/** Bad Debt Collection = 21 */
	public static final String PROFESSIONALSTATUS_BadDebtCollection = "21";
	/** Early Out = 20 */
	public static final String PROFESSIONALSTATUS_EarlyOut = "20";
	/** Credit Balance = 22 */
	public static final String PROFESSIONALSTATUS_CreditBalance = "22";
	/** Zero Balance = 24 */
	public static final String PROFESSIONALSTATUS_ZeroBalance = "24";
	/** Set Professional Status.
		@param ProfessionalStatus 
		Professional Status
	  */
	public void setProfessionalStatus (String ProfessionalStatus)
	{
		if (ProfessionalStatus == null) throw new IllegalArgumentException ("ProfessionalStatus is mandatory");
		if (ProfessionalStatus.equals("4") || ProfessionalStatus.equals("3") || ProfessionalStatus.equals("11") || ProfessionalStatus.equals("7") || ProfessionalStatus.equals("12") || ProfessionalStatus.equals("13") || ProfessionalStatus.equals("10") || ProfessionalStatus.equals("14") || ProfessionalStatus.equals("18") || ProfessionalStatus.equals("RSI") || ProfessionalStatus.equals("16") || ProfessionalStatus.equals("RTI") || ProfessionalStatus.equals("15") || ProfessionalStatus.equals("25") || ProfessionalStatus.equals("WSI") || ProfessionalStatus.equals("WTI") || ProfessionalStatus.equals("2") || ProfessionalStatus.equals("1") || ProfessionalStatus.equals("5") || ProfessionalStatus.equals("6") || ProfessionalStatus.equals("8") || ProfessionalStatus.equals("9") || ProfessionalStatus.equals("17") || ProfessionalStatus.equals("23") || ProfessionalStatus.equals("19") || ProfessionalStatus.equals("21") || ProfessionalStatus.equals("20") || ProfessionalStatus.equals("22") || ProfessionalStatus.equals("24")); else throw new IllegalArgumentException ("ProfessionalStatus Invalid value - " + ProfessionalStatus + " - Reference_ID=1200581 - 4 - 3 - 11 - 7 - 12 - 13 - 10 - 14 - 18 - RSI - 16 - RTI - 15 - 25 - WSI - WTI - 2 - 1 - 5 - 6 - 8 - 9 - 17 - 23 - 19 - 21 - 20 - 22 - 24");		set_Value (COLUMNNAME_ProfessionalStatus, ProfessionalStatus);
	}

	/** Get Professional Status.
		@return Professional Status
	  */
	public String getProfessionalStatus () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStatus);
	}

	/** Set Professional Status Name.
		@param ProfessionalStatus_Name Professional Status Name	  */
	public void setProfessionalStatus_Name (String ProfessionalStatus_Name)
	{
		if (ProfessionalStatus_Name == null)
			throw new IllegalArgumentException ("ProfessionalStatus_Name is mandatory.");
		set_Value (COLUMNNAME_ProfessionalStatus_Name, ProfessionalStatus_Name);
	}

	/** Get Professional Status Name.
		@return Professional Status Name	  */
	public String getProfessionalStatus_Name () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStatus_Name);
	}

	/** ProfessionalStep AD_Reference_ID=1200595 */
	public static final int PROFESSIONALSTEP_AD_Reference_ID=1200595;
	/** Default = D */
	public static final String PROFESSIONALSTEP_Default = "D";
	/** First Insurance = F */
	public static final String PROFESSIONALSTEP_FirstInsurance = "F";
	/** Second Insurance = S */
	public static final String PROFESSIONALSTEP_SecondInsurance = "S";
	/** Third Insurance = T */
	public static final String PROFESSIONALSTEP_ThirdInsurance = "T";
	/** Other Insurance = O */
	public static final String PROFESSIONALSTEP_OtherInsurance = "O";
	/** Selft Pay = P */
	public static final String PROFESSIONALSTEP_SelftPay = "P";
	/** Set Professional Step.
		@param ProfessionalStep 
		Insurance that has the liability for professional charges
	  */
	public void setProfessionalStep (String ProfessionalStep)
	{
		if (ProfessionalStep == null) throw new IllegalArgumentException ("ProfessionalStep is mandatory");
		if (ProfessionalStep.equals("D") || ProfessionalStep.equals("F") || ProfessionalStep.equals("S") || ProfessionalStep.equals("T") || ProfessionalStep.equals("O") || ProfessionalStep.equals("P")); else throw new IllegalArgumentException ("ProfessionalStep Invalid value - " + ProfessionalStep + " - Reference_ID=1200595 - D - F - S - T - O - P");		set_Value (COLUMNNAME_ProfessionalStep, ProfessionalStep);
	}

	/** Get Professional Step.
		@return Insurance that has the liability for professional charges
	  */
	public String getProfessionalStep () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStep);
	}

	/** Set Professional Step Name.
		@param ProfessionalStep_Name Professional Step Name	  */
	public void setProfessionalStep_Name (String ProfessionalStep_Name)
	{
		if (ProfessionalStep_Name == null)
			throw new IllegalArgumentException ("ProfessionalStep_Name is mandatory.");
		set_Value (COLUMNNAME_ProfessionalStep_Name, ProfessionalStep_Name);
	}

	/** Get Professional Step Name.
		@return Professional Step Name	  */
	public String getProfessionalStep_Name () 
	{
		return (String)get_Value(COLUMNNAME_ProfessionalStep_Name);
	}

	/** Set Sex.
		@param Sexo 
		Sex
	  */
	public void setSexo (boolean Sexo)
	{
		set_Value (COLUMNNAME_Sexo, Boolean.valueOf(Sexo));
	}

	/** Get Sex.
		@return Sex
	  */
	public boolean isSexo () 
	{
		Object oo = get_Value(COLUMNNAME_Sexo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null)
			throw new IllegalArgumentException ("Status is mandatory.");
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set SSN.
		@param SuffixNSS SSN	  */
	public void setSuffixNSS (String SuffixNSS)
	{
		set_Value (COLUMNNAME_SuffixNSS, SuffixNSS);
	}

	/** Get SSN.
		@return SSN	  */
	public String getSuffixNSS () 
	{
		return (String)get_Value(COLUMNNAME_SuffixNSS);
	}

	/** Set Home Phone.
		@param TelParticular 
		Home Phone
	  */
	public void setTelParticular (String TelParticular)
	{
		set_Value (COLUMNNAME_TelParticular, TelParticular);
	}

	/** Get Home Phone.
		@return Home Phone
	  */
	public String getTelParticular () 
	{
		return (String)get_Value(COLUMNNAME_TelParticular);
	}
}