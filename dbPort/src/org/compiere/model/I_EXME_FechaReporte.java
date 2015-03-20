/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FechaReporte
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_FechaReporte 
{

    /** TableName=EXME_FechaReporte */
    public static final String Table_Name = "EXME_FechaReporte";

    /** AD_Table_ID=1200276 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name EXME_FechaReporte_ID */
    public static final String COLUMNNAME_EXME_FechaReporte_ID = "EXME_FechaReporte_ID";

	/** Set Report Dates for Transplant.
	  * Report Dates for Transplant
	  */
	public void setEXME_FechaReporte_ID (int EXME_FechaReporte_ID);

	/** Get Report Dates for Transplant.
	  * Report Dates for Transplant
	  */
	public int getEXME_FechaReporte_ID();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

    /** Column name FechaReporte */
    public static final String COLUMNNAME_FechaReporte = "FechaReporte";

	/** Set Report Date.
	  * Report Date
	  */
	public void setFechaReporte (Timestamp FechaReporte);

	/** Get Report Date.
	  * Report Date
	  */
	public Timestamp getFechaReporte();
}
