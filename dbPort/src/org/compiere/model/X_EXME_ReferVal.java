/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ReferVal
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ReferVal extends PO implements I_EXME_ReferVal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReferVal (Properties ctx, int EXME_ReferVal_ID, String trxName)
    {
      super (ctx, EXME_ReferVal_ID, trxName);
      /** if (EXME_ReferVal_ID == 0)
        {
			setAD_OrgRec_ID (0);
			setEXME_ReferVal_ID (0);
			setTipoArea (null);
			setTipoAreaRec (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReferVal (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReferVal[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Receiving Organization.
		@param AD_OrgRec_ID 
		Receiving Organization for Referenced Patient
	  */
	public void setAD_OrgRec_ID (int AD_OrgRec_ID)
	{
		if (AD_OrgRec_ID < 1)
			 throw new IllegalArgumentException ("AD_OrgRec_ID is mandatory.");
		set_Value (COLUMNNAME_AD_OrgRec_ID, Integer.valueOf(AD_OrgRec_ID));
	}

	/** Get Receiving Organization.
		@return Receiving Organization for Referenced Patient
	  */
	public int getAD_OrgRec_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgRec_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Valid References.
		@param EXME_ReferVal_ID 
		Valid References
	  */
	public void setEXME_ReferVal_ID (int EXME_ReferVal_ID)
	{
		if (EXME_ReferVal_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferVal_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReferVal_ID, Integer.valueOf(EXME_ReferVal_ID));
	}

	/** Get Valid References.
		@return Valid References
	  */
	public int getEXME_ReferVal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferVal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** TipoAreaRec AD_Reference_ID=1000039 */
	public static final int TIPOAREAREC_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREAREC_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREAREC_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREAREC_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREAREC_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREAREC_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREAREC_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREAREC_Other = "O";
	/** External = E */
	public static final String TIPOAREAREC_External = "E";
	/** Admission = D */
	public static final String TIPOAREAREC_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREAREC_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREAREC_SocialComunication = "S";
	/** Set Receiving Area.
		@param TipoAreaRec 
		Type of receiving area
	  */
	public void setTipoAreaRec (String TipoAreaRec)
	{
		if (TipoAreaRec == null) throw new IllegalArgumentException ("TipoAreaRec is mandatory");
		if (TipoAreaRec.equals("H") || TipoAreaRec.equals("U") || TipoAreaRec.equals("A") || TipoAreaRec.equals("C") || TipoAreaRec.equals("P") || TipoAreaRec.equals("R") || TipoAreaRec.equals("O") || TipoAreaRec.equals("E") || TipoAreaRec.equals("D") || TipoAreaRec.equals("T") || TipoAreaRec.equals("S")); else throw new IllegalArgumentException ("TipoAreaRec Invalid value - " + TipoAreaRec + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoAreaRec, TipoAreaRec);
	}

	/** Get Receiving Area.
		@return Type of receiving area
	  */
	public String getTipoAreaRec () 
	{
		return (String)get_Value(COLUMNNAME_TipoAreaRec);
	}
}