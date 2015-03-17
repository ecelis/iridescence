/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_GenQualityMeasure
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_GenQualityMeasure extends PO implements I_EXME_GenQualityMeasure, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GenQualityMeasure (Properties ctx, int EXME_GenQualityMeasure_ID, String trxName)
    {
      super (ctx, EXME_GenQualityMeasure_ID, trxName);
      /** if (EXME_GenQualityMeasure_ID == 0)
        {
			setEXME_GenQualityMeasure_ID (0);
			setFecha_Fin_Urgencia (new Timestamp( System.currentTimeMillis() ));
			setFecha_Ini_Urgencia (new Timestamp( System.currentTimeMillis() ));
			setFolio (0);
			setReport (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GenQualityMeasure (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GenQualityMeasure[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Generated Quality Measure.
		@param EXME_GenQualityMeasure_ID Generated Quality Measure	  */
	public void setEXME_GenQualityMeasure_ID (int EXME_GenQualityMeasure_ID)
	{
		if (EXME_GenQualityMeasure_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenQualityMeasure_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GenQualityMeasure_ID, Integer.valueOf(EXME_GenQualityMeasure_ID));
	}

	/** Get Generated Quality Measure.
		@return Generated Quality Measure	  */
	public int getEXME_GenQualityMeasure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenQualityMeasure_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date End Emergency.
		@param Fecha_Fin_Urgencia Date End Emergency	  */
	public void setFecha_Fin_Urgencia (Timestamp Fecha_Fin_Urgencia)
	{
		if (Fecha_Fin_Urgencia == null)
			throw new IllegalArgumentException ("Fecha_Fin_Urgencia is mandatory.");
		set_Value (COLUMNNAME_Fecha_Fin_Urgencia, Fecha_Fin_Urgencia);
	}

	/** Get Date End Emergency.
		@return Date End Emergency	  */
	public Timestamp getFecha_Fin_Urgencia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin_Urgencia);
	}

	/** Set Date Initial Emergency.
		@param Fecha_Ini_Urgencia Date Initial Emergency	  */
	public void setFecha_Ini_Urgencia (Timestamp Fecha_Ini_Urgencia)
	{
		if (Fecha_Ini_Urgencia == null)
			throw new IllegalArgumentException ("Fecha_Ini_Urgencia is mandatory.");
		set_Value (COLUMNNAME_Fecha_Ini_Urgencia, Fecha_Ini_Urgencia);
	}

	/** Get Date Initial Emergency.
		@return Date Initial Emergency	  */
	public Timestamp getFecha_Ini_Urgencia () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini_Urgencia);
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Report AD_Reference_ID=1200480 */
	public static final int REPORT_AD_Reference_ID=1200480;
	/** STROKE 2 = ST2 */
	public static final String REPORT_STROKE2 = "ST2";
	/** STROKE 3 = ST3 */
	public static final String REPORT_STROKE3 = "ST3";
	/** STROKE 4 = ST4 */
	public static final String REPORT_STROKE4 = "ST4";
	/** STROKE 5 = ST5 */
	public static final String REPORT_STROKE5 = "ST5";
	/** STROKE 6 = ST6 */
	public static final String REPORT_STROKE6 = "ST6";
	/** STROKE 8 = ST8 */
	public static final String REPORT_STROKE8 = "ST8";
	/** STROKE 10 = ST0 */
	public static final String REPORT_STROKE10 = "ST0";
	/** Emergency Department 1 = ED1 */
	public static final String REPORT_EmergencyDepartment1 = "ED1";
	/** Emergency Department 2 = ED2 */
	public static final String REPORT_EmergencyDepartment2 = "ED2";
	/** VENOUS THROMBOEMBOLISM VTE-1 = VT1 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_1 = "VT1";
	/** VENOUS THROMBOEMBOLISM VTE-2 = VT2 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_2 = "VT2";
	/** VENOUS THROMBOEMBOLISM VTE-3 = VT3 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_3 = "VT3";
	/** VENOUS THROMBOEMBOLISM VTE-4 = VT4 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_4 = "VT4";
	/** VENOUS THROMBOEMBOLISM VTE-5 = VT5 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_5 = "VT5";
	/** VENOUS THROMBOEMBOLISM VTE-6 = VT6 */
	public static final String REPORT_VENOUSTHROMBOEMBOLISMVTE_6 = "VT6";
	/** Set Report.
		@param Report Report	  */
	public void setReport (String Report)
	{
		if (Report == null) throw new IllegalArgumentException ("Report is mandatory");
		if (Report.equals("ST2") || Report.equals("ST3") || Report.equals("ST4") || Report.equals("ST5") || Report.equals("ST6") || Report.equals("ST8") || Report.equals("ST0") || Report.equals("ED1") || Report.equals("ED2") || Report.equals("VT1") || Report.equals("VT2") || Report.equals("VT3") || Report.equals("VT4") || Report.equals("VT5") || Report.equals("VT6")); else throw new IllegalArgumentException ("Report Invalid value - " + Report + " - Reference_ID=1200480 - ST2 - ST3 - ST4 - ST5 - ST6 - ST8 - ST0 - ED1 - ED2 - VT1 - VT2 - VT3 - VT4 - VT5 - VT6");		set_Value (COLUMNNAME_Report, Report);
	}

	/** Get Report.
		@return Report	  */
	public String getReport () 
	{
		return (String)get_Value(COLUMNNAME_Report);
	}
}