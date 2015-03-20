/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LayoutHospDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LayoutHospDet 
{

    /** TableName=EXME_LayoutHospDet */
    public static final String Table_Name = "EXME_LayoutHospDet";

    /** AD_Table_ID=1200215 */
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

    /** Column name EXME_LayoutHospDet_ID */
    public static final String COLUMNNAME_EXME_LayoutHospDet_ID = "EXME_LayoutHospDet_ID";

	/** Set Hospital Layout Detail	  */
	public void setEXME_LayoutHospDet_ID (int EXME_LayoutHospDet_ID);

	/** Get Hospital Layout Detail	  */
	public int getEXME_LayoutHospDet_ID();

    /** Column name EXME_LayoutHosp_ID */
    public static final String COLUMNNAME_EXME_LayoutHosp_ID = "EXME_LayoutHosp_ID";

	/** Set Inpatient Layout.
	  * Inpatient Layout
	  */
	public void setEXME_LayoutHosp_ID (int EXME_LayoutHosp_ID);

	/** Get Inpatient Layout.
	  * Inpatient Layout
	  */
	public int getEXME_LayoutHosp_ID();

	public I_EXME_LayoutHosp getEXME_LayoutHosp() throws RuntimeException;

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
