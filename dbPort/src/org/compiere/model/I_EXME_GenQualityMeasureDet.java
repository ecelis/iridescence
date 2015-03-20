/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GenQualityMeasureDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GenQualityMeasureDet 
{

    /** TableName=EXME_GenQualityMeasureDet */
    public static final String Table_Name = "EXME_GenQualityMeasureDet";

    /** AD_Table_ID=1201096 */
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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_GenQualityMeasureDet_ID */
    public static final String COLUMNNAME_EXME_GenQualityMeasureDet_ID = "EXME_GenQualityMeasureDet_ID";

	/** Set Detail of Generated Quality Measure	  */
	public void setEXME_GenQualityMeasureDet_ID (int EXME_GenQualityMeasureDet_ID);

	/** Get Detail of Generated Quality Measure	  */
	public int getEXME_GenQualityMeasureDet_ID();

    /** Column name EXME_GenQualityMeasure_ID */
    public static final String COLUMNNAME_EXME_GenQualityMeasure_ID = "EXME_GenQualityMeasure_ID";

	/** Set Generated Quality Measure	  */
	public void setEXME_GenQualityMeasure_ID (int EXME_GenQualityMeasure_ID);

	/** Get Generated Quality Measure	  */
	public int getEXME_GenQualityMeasure_ID();

	public I_EXME_GenQualityMeasure getEXME_GenQualityMeasure() throws RuntimeException;

    /** Column name Tipo_Reporte */
    public static final String COLUMNNAME_Tipo_Reporte = "Tipo_Reporte";

	/** Set Grouped by.
	  * Grouped by
	  */
	public void setTipo_Reporte (String Tipo_Reporte);

	/** Get Grouped by.
	  * Grouped by
	  */
	public String getTipo_Reporte();
}
