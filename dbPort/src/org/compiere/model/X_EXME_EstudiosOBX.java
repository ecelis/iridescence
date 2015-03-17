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

/** Generated Model for EXME_EstudiosOBX
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_EstudiosOBX extends PO implements I_EXME_EstudiosOBX, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstudiosOBX (Properties ctx, int EXME_EstudiosOBX_ID, String trxName)
    {
      super (ctx, EXME_EstudiosOBX_ID, trxName);
      /** if (EXME_EstudiosOBX_ID == 0)
        {
			setCodeID (0);
// 0
			setCodeOBX (null);
			setEXME_ActPacienteInd_ID (0);
			setEXME_EstudiosOBX_ID (0);
			setObservation (null);
			setSystemCode (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EstudiosOBX (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstudiosOBX[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AbnormalFlags AD_Reference_ID=1200473 */
	public static final int ABNORMALFLAGS_AD_Reference_ID=1200473;
	/** Below low normal = L */
	public static final String ABNORMALFLAGS_BelowLowNormal = "L";
	/** Above high normal = H */
	public static final String ABNORMALFLAGS_AboveHighNormal = "H";
	/** Below lower panic limits = LL */
	public static final String ABNORMALFLAGS_BelowLowerPanicLimits = "LL";
	/** Above upper panic limits  = HH */
	public static final String ABNORMALFLAGS_AboveUpperPanicLimits = "HH";
	/** Below absolute low-off instrument scale  = < */
	public static final String ABNORMALFLAGS_BelowAbsoluteLow_OffInstrumentScale = "<";
	/** Above absolute high-off instrument scale  = > */
	public static final String ABNORMALFLAGS_AboveAbsoluteHigh_OffInstrumentScale = ">";
	/** Normal = N */
	public static final String ABNORMALFLAGS_Normal = "N";
	/** Abnormal = A */
	public static final String ABNORMALFLAGS_Abnormal = "A";
	/** Very abnormal = AA */
	public static final String ABNORMALFLAGS_VeryAbnormal = "AA";
	/** Significant change up  = U */
	public static final String ABNORMALFLAGS_SignificantChangeUp = "U";
	/** Significant change down  = D */
	public static final String ABNORMALFLAGS_SignificantChangeDown = "D";
	/** Better = B */
	public static final String ABNORMALFLAGS_Better = "B";
	/** Worse = W */
	public static final String ABNORMALFLAGS_Worse = "W";
	/** Susceptible = S */
	public static final String ABNORMALFLAGS_Susceptible = "S";
	/** Resistant = R */
	public static final String ABNORMALFLAGS_Resistant = "R";
	/** Intermediate = I */
	public static final String ABNORMALFLAGS_Intermediate = "I";
	/** Moderately susceptible = MS */
	public static final String ABNORMALFLAGS_ModeratelySusceptible = "MS";
	/** Very susceptible = VS */
	public static final String ABNORMALFLAGS_VerySusceptible = "VS";
	/** Cytotoxic substance present = TOX */
	public static final String ABNORMALFLAGS_CytotoxicSubstancePresent = "TOX";
	/** Detected = DET */
	public static final String ABNORMALFLAGS_Detected = "DET";
	/** Indeterminate = IND */
	public static final String ABNORMALFLAGS_Indeterminate = "IND";
	/** Negative = NEG */
	public static final String ABNORMALFLAGS_Negative = "NEG";
	/** No range defined = -- */
	public static final String ABNORMALFLAGS_NoRangeDefined = "--";
	/** Non-reactive = NR */
	public static final String ABNORMALFLAGS_Non_Reactive = "NR";
	/** Not Detected = ND */
	public static final String ABNORMALFLAGS_NotDetected = "ND";
	/** Positive = POS */
	public static final String ABNORMALFLAGS_Positive = "POS";
	/** Quality Control Failure = QCF */
	public static final String ABNORMALFLAGS_QualityControlFailure = "QCF";
	/** Reactive = RR */
	public static final String ABNORMALFLAGS_Reactive = "RR";
	/** Weakly reactive = WR */
	public static final String ABNORMALFLAGS_WeaklyReactive = "WR";
	/** Anti complementary substances = AC */
	public static final String ABNORMALFLAGS_AntiComplementarySubstances = "AC";
	/** Set Abnormal Flags.
		@param AbnormalFlags Abnormal Flags	  */
	public void setAbnormalFlags (String AbnormalFlags)
	{

		if (AbnormalFlags == null || AbnormalFlags.equals("L") || AbnormalFlags.equals("H") || AbnormalFlags.equals("LL") || AbnormalFlags.equals("HH") || AbnormalFlags.equals("<") || AbnormalFlags.equals(">") || AbnormalFlags.equals("N") || AbnormalFlags.equals("A") || AbnormalFlags.equals("AA") || AbnormalFlags.equals("U") || AbnormalFlags.equals("D") || AbnormalFlags.equals("B") || AbnormalFlags.equals("W") || AbnormalFlags.equals("S") || AbnormalFlags.equals("R") || AbnormalFlags.equals("I") || AbnormalFlags.equals("MS") || AbnormalFlags.equals("VS") || AbnormalFlags.equals("TOX") || AbnormalFlags.equals("DET") || AbnormalFlags.equals("IND") || AbnormalFlags.equals("NEG") || AbnormalFlags.equals("--") || AbnormalFlags.equals("NR") || AbnormalFlags.equals("ND") || AbnormalFlags.equals("POS") || AbnormalFlags.equals("QCF") || AbnormalFlags.equals("RR") || AbnormalFlags.equals("WR") || AbnormalFlags.equals("AC")); else throw new IllegalArgumentException ("AbnormalFlags Invalid value - " + AbnormalFlags + " - Reference_ID=1200473 - L - H - LL - HH - < - > - N - A - AA - U - D - B - W - S - R - I - MS - VS - TOX - DET - IND - NEG - -- - NR - ND - POS - QCF - RR - WR - AC");		set_Value (COLUMNNAME_AbnormalFlags, AbnormalFlags);
	}

	/** Get Abnormal Flags.
		@return Abnormal Flags	  */
	public String getAbnormalFlags () 
	{
		return (String)get_Value(COLUMNNAME_AbnormalFlags);
	}

	/** Set Analysis Date.
		@param AnalysisDate Analysis Date	  */
	public void setAnalysisDate (Timestamp AnalysisDate)
	{
		set_Value (COLUMNNAME_AnalysisDate, AnalysisDate);
	}

	/** Get Analysis Date.
		@return Analysis Date	  */
	public Timestamp getAnalysisDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AnalysisDate);
	}

	/** Set Address .
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address .
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Code Identifier.
		@param CodeID Code Identifier	  */
	public void setCodeID (int CodeID)
	{
		set_Value (COLUMNNAME_CodeID, Integer.valueOf(CodeID));
	}

	/** Get Code Identifier.
		@return Code Identifier	  */
	public int getCodeID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CodeID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Observation Code.
		@param CodeOBX Observation Code	  */
	public void setCodeOBX (String CodeOBX)
	{
		if (CodeOBX == null)
			throw new IllegalArgumentException ("CodeOBX is mandatory.");
		set_Value (COLUMNNAME_CodeOBX, CodeOBX);
	}

	/** Get Observation Code.
		@return Observation Code	  */
	public String getCodeOBX () 
	{
		return (String)get_Value(COLUMNNAME_CodeOBX);
	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Approved.
		@param DateApproved Date Approved	  */
	public void setDateApproved (Timestamp DateApproved)
	{
		set_Value (COLUMNNAME_DateApproved, DateApproved);
	}

	/** Get Date Approved.
		@return Date Approved	  */
	public Timestamp getDateApproved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateApproved);
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
			 throw new IllegalArgumentException ("EXME_ActPacienteInd_ID is mandatory.");
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

	/** Set Test Results.
		@param EXME_EstudiosOBX_ID 
		OBX Laboratory Test Results
	  */
	public void setEXME_EstudiosOBX_ID (int EXME_EstudiosOBX_ID)
	{
		if (EXME_EstudiosOBX_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstudiosOBX_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstudiosOBX_ID, Integer.valueOf(EXME_EstudiosOBX_ID));
	}

	/** Get Test Results.
		@return OBX Laboratory Test Results
	  */
	public int getEXME_EstudiosOBX_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstudiosOBX_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lab address.
		@param LabAddress Lab address	  */
	public void setLabAddress (String LabAddress)
	{
		set_Value (COLUMNNAME_LabAddress, LabAddress);
	}

	/** Get Lab address.
		@return Lab address	  */
	public String getLabAddress () 
	{
		return (String)get_Value(COLUMNNAME_LabAddress);
	}

	/** Set Lab Facility Name.
		@param LabName Lab Facility Name	  */
	public void setLabName (String LabName)
	{
		set_Value (COLUMNNAME_LabName, LabName);
	}

	/** Get Lab Facility Name.
		@return Lab Facility Name	  */
	public String getLabName () 
	{
		return (String)get_Value(COLUMNNAME_LabName);
	}

	/** Set Observation.
		@param Observation 
		Optional short description of the record
	  */
	public void setObservation (String Observation)
	{
		if (Observation == null)
			throw new IllegalArgumentException ("Observation is mandatory.");
		set_Value (COLUMNNAME_Observation, Observation);
	}

	/** Get Observation.
		@return Optional short description of the record
	  */
	public String getObservation () 
	{
		return (String)get_Value(COLUMNNAME_Observation);
	}

	/** Set Observation Date.
		@param ObservationDate Observation Date	  */
	public void setObservationDate (Timestamp ObservationDate)
	{
		set_Value (COLUMNNAME_ObservationDate, ObservationDate);
	}

	/** Get Observation Date.
		@return Observation Date	  */
	public Timestamp getObservationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ObservationDate);
	}

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Parent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Range Reference.
		@param RangeReference Range Reference	  */
	public void setRangeReference (String RangeReference)
	{
		set_Value (COLUMNNAME_RangeReference, RangeReference);
	}

	/** Get Range Reference.
		@return Range Reference	  */
	public String getRangeReference () 
	{
		return (String)get_Value(COLUMNNAME_RangeReference);
	}

	/** ResultStatus AD_Reference_ID=1200472 */
	public static final int RESULTSTATUS_AD_Reference_ID=1200472;
	/** Order Received = O */
	public static final String RESULTSTATUS_OrderReceived = "O";
	/** Not Results aviable; procedure incomplete = I */
	public static final String RESULTSTATUS_NotResultsAviableProcedureIncomplete = "I";
	/** Procedure Scheduled = S */
	public static final String RESULTSTATUS_ProcedureScheduled = "S";
	/** Some but no all results aviable = A */
	public static final String RESULTSTATUS_SomeButNoAllResultsAviable = "A";
	/** Preliminary = P */
	public static final String RESULTSTATUS_Preliminary = "P";
	/** Correction to results = C */
	public static final String RESULTSTATUS_CorrectionToResults = "C";
	/** Results stored, not yet verified = R */
	public static final String RESULTSTATUS_ResultsStoredNotYetVerified = "R";
	/** Final Results = F */
	public static final String RESULTSTATUS_FinalResults = "F";
	/** No results aviable, Order canceled = X */
	public static final String RESULTSTATUS_NoResultsAviableOrderCanceled = "X";
	/** Set Result Status.
		@param ResultStatus Result Status	  */
	public void setResultStatus (String ResultStatus)
	{

		if (ResultStatus == null || ResultStatus.equals("O") || ResultStatus.equals("I") || ResultStatus.equals("S") || ResultStatus.equals("A") || ResultStatus.equals("P") || ResultStatus.equals("C") || ResultStatus.equals("R") || ResultStatus.equals("F") || ResultStatus.equals("X")); else throw new IllegalArgumentException ("ResultStatus Invalid value - " + ResultStatus + " - Reference_ID=1200472 - O - I - S - A - P - C - R - F - X");		set_Value (COLUMNNAME_ResultStatus, ResultStatus);
	}

	/** Get Result Status.
		@return Result Status	  */
	public String getResultStatus () 
	{
		return (String)get_Value(COLUMNNAME_ResultStatus);
	}

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SystemCode AD_Reference_ID=1200407 */
	public static final int SYSTEMCODE_AD_Reference_ID=1200407;
	/** LOINC = LN */
	public static final String SYSTEMCODE_LOINC = "LN";
	/** NONE = -- */
	public static final String SYSTEMCODE_NONE = "--";
	/** Set System Code.
		@param SystemCode System Code	  */
	public void setSystemCode (String SystemCode)
	{
		if (SystemCode == null) throw new IllegalArgumentException ("SystemCode is mandatory");
		if (SystemCode.equals("LN") || SystemCode.equals("--")); else throw new IllegalArgumentException ("SystemCode Invalid value - " + SystemCode + " - Reference_ID=1200407 - LN - --");		set_Value (COLUMNNAME_SystemCode, SystemCode);
	}

	/** Get System Code.
		@return System Code	  */
	public String getSystemCode () 
	{
		return (String)get_Value(COLUMNNAME_SystemCode);
	}

	/** Set UOM Value.
		@param UOMValue UOM Value	  */
	public void setUOMValue (String UOMValue)
	{
		set_Value (COLUMNNAME_UOMValue, UOMValue);
	}

	/** Get UOM Value.
		@return UOM Value	  */
	public String getUOMValue () 
	{
		return (String)get_Value(COLUMNNAME_UOMValue);
	}
}