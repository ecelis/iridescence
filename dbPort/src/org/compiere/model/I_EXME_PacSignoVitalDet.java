/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacSignoVitalDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacSignoVitalDet 
{

    /** TableName=EXME_PacSignoVitalDet */
    public static final String Table_Name = "EXME_PacSignoVitalDet";

    /** AD_Table_ID=1200883 */
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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

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

    /** Column name EXME_PacSignoVitalDet_ID */
    public static final String COLUMNNAME_EXME_PacSignoVitalDet_ID = "EXME_PacSignoVitalDet_ID";

	/** Set Patient Vital Sings detail.
	  * Patient Vital Sings detail
	  */
	public void setEXME_PacSignoVitalDet_ID (int EXME_PacSignoVitalDet_ID);

	/** Get Patient Vital Sings detail.
	  * Patient Vital Sings detail
	  */
	public int getEXME_PacSignoVitalDet_ID();

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException;

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name Valor */
    public static final String COLUMNNAME_Valor = "Valor";

	/** Set Value	  */
	public void setValor (BigDecimal Valor);

	/** Get Value	  */
	public BigDecimal getValor();
}
