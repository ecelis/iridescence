/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pension
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Pension 
{

    /** TableName=EXME_Pension */
    public static final String Table_Name = "EXME_Pension";

    /** AD_Table_ID=1200218 */
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

    /** Column name AfiliacionNo */
    public static final String COLUMNNAME_AfiliacionNo = "AfiliacionNo";

	/** Set Affiliation Number	  */
	public void setAfiliacionNo (int AfiliacionNo);

	/** Get Affiliation Number	  */
	public int getAfiliacionNo();

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

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name EXME_Pension_ID */
    public static final String COLUMNNAME_EXME_Pension_ID = "EXME_Pension_ID";

	/** Set Pension.
	  * Pension
	  */
	public void setEXME_Pension_ID (int EXME_Pension_ID);

	/** Get Pension.
	  * Pension
	  */
	public int getEXME_Pension_ID();

    /** Column name Fecha_Egreso */
    public static final String COLUMNNAME_Fecha_Egreso = "Fecha_Egreso";

	/** Set Discharge Date	  */
	public void setFecha_Egreso (Timestamp Fecha_Egreso);

	/** Get Discharge Date	  */
	public Timestamp getFecha_Egreso();

    /** Column name FechaIngreso */
    public static final String COLUMNNAME_FechaIngreso = "FechaIngreso";

	/** Set Admission Date.
	  * Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso);

	/** Get Admission Date.
	  * Admission Date
	  */
	public Timestamp getFechaIngreso();
}
