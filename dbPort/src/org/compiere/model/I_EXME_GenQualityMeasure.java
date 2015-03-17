/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GenQualityMeasure
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GenQualityMeasure 
{

    /** TableName=EXME_GenQualityMeasure */
    public static final String Table_Name = "EXME_GenQualityMeasure";

    /** AD_Table_ID=1201094 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name EXME_GenQualityMeasure_ID */
    public static final String COLUMNNAME_EXME_GenQualityMeasure_ID = "EXME_GenQualityMeasure_ID";

	/** Set Generated Quality Measure	  */
	public void setEXME_GenQualityMeasure_ID (int EXME_GenQualityMeasure_ID);

	/** Get Generated Quality Measure	  */
	public int getEXME_GenQualityMeasure_ID();

    /** Column name Fecha_Fin_Urgencia */
    public static final String COLUMNNAME_Fecha_Fin_Urgencia = "Fecha_Fin_Urgencia";

	/** Set Date End Emergency	  */
	public void setFecha_Fin_Urgencia (Timestamp Fecha_Fin_Urgencia);

	/** Get Date End Emergency	  */
	public Timestamp getFecha_Fin_Urgencia();

    /** Column name Fecha_Ini_Urgencia */
    public static final String COLUMNNAME_Fecha_Ini_Urgencia = "Fecha_Ini_Urgencia";

	/** Set Date Initial Emergency	  */
	public void setFecha_Ini_Urgencia (Timestamp Fecha_Ini_Urgencia);

	/** Get Date Initial Emergency	  */
	public Timestamp getFecha_Ini_Urgencia();

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name Report */
    public static final String COLUMNNAME_Report = "Report";

	/** Set Report	  */
	public void setReport (String Report);

	/** Get Report	  */
	public String getReport();
}
