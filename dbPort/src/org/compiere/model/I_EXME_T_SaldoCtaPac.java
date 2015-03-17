/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_SaldoCtaPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_SaldoCtaPac 
{

    /** TableName=EXME_T_SaldoCtaPac */
    public static final String Table_Name = "EXME_T_SaldoCtaPac";

    /** AD_Table_ID=1200132 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name Anticipos */
    public static final String COLUMNNAME_Anticipos = "Anticipos";

	/** Set Advance Payments.
	  * Payments made in advance
	  */
	public void setAnticipos (BigDecimal Anticipos);

	/** Get Advance Payments.
	  * Payments made in advance
	  */
	public BigDecimal getAnticipos();

    /** Column name Cargos */
    public static final String COLUMNNAME_Cargos = "Cargos";

	/** Set Charges.
	  * Charges of the patient account
	  */
	public void setCargos (BigDecimal Cargos);

	/** Get Charges.
	  * Charges of the patient account
	  */
	public BigDecimal getCargos();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

	public I_EXME_Cama getEXME_Cama() throws RuntimeException;

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

    /** Column name EXME_Habitacion_ID */
    public static final String COLUMNNAME_EXME_Habitacion_ID = "EXME_Habitacion_ID";

	/** Set Room.
	  * Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID);

	/** Get Room.
	  * Room
	  */
	public int getEXME_Habitacion_ID();

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException;

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

    /** Column name EXME_T_SaldoCtaPac_ID */
    public static final String COLUMNNAME_EXME_T_SaldoCtaPac_ID = "EXME_T_SaldoCtaPac_ID";

	/** Set Patient Account Balance.
	  * The Balance of the Patient Account
	  */
	public void setEXME_T_SaldoCtaPac_ID (int EXME_T_SaldoCtaPac_ID);

	/** Get Patient Account Balance.
	  * The Balance of the Patient Account
	  */
	public int getEXME_T_SaldoCtaPac_ID();

    /** Column name Pagos */
    public static final String COLUMNNAME_Pagos = "Pagos";

	/** Set Payments.
	  * Payments
	  */
	public void setPagos (BigDecimal Pagos);

	/** Get Payments.
	  * Payments
	  */
	public BigDecimal getPagos();

    /** Column name Saldo */
    public static final String COLUMNNAME_Saldo = "Saldo";

	/** Set Balance.
	  * Balance of Patient Account
	  */
	public void setSaldo (BigDecimal Saldo);

	/** Get Balance.
	  * Balance of Patient Account
	  */
	public BigDecimal getSaldo();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();
}
