/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LayoutUrgDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LayoutUrgDet 
{

    /** TableName=EXME_LayoutUrgDet */
    public static final String Table_Name = "EXME_LayoutUrgDet";

    /** AD_Table_ID=1200250 */
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

    /** Column name Captura */
    public static final String COLUMNNAME_Captura = "Captura";

	/** Set Capture.
	  * Capture
	  */
	public void setCaptura (int Captura);

	/** Get Capture.
	  * Capture
	  */
	public int getCaptura();

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

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

    /** Column name EXME_LayoutUrgDet_ID */
    public static final String COLUMNNAME_EXME_LayoutUrgDet_ID = "EXME_LayoutUrgDet_ID";

	/** Set Urgency Layout Detail	  */
	public void setEXME_LayoutUrgDet_ID (int EXME_LayoutUrgDet_ID);

	/** Get Urgency Layout Detail	  */
	public int getEXME_LayoutUrgDet_ID();

    /** Column name EXME_LayoutUrg_ID */
    public static final String COLUMNNAME_EXME_LayoutUrg_ID = "EXME_LayoutUrg_ID";

	/** Set Urgency Layout.
	  * Urgency Layout
	  */
	public void setEXME_LayoutUrg_ID (int EXME_LayoutUrg_ID);

	/** Get Urgency Layout.
	  * Urgency Layout
	  */
	public int getEXME_LayoutUrg_ID();

	public I_EXME_LayoutUrg getEXME_LayoutUrg() throws RuntimeException;

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

    /** Column name Tipo_Servicio */
    public static final String COLUMNNAME_Tipo_Servicio = "Tipo_Servicio";

	/** Set Service Type.
	  * Service Type
	  */
	public void setTipo_Servicio (int Tipo_Servicio);

	/** Get Service Type.
	  * Service Type
	  */
	public int getTipo_Servicio();
}
