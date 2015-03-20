/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LogImpPlantilla
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_LogImpPlantilla 
{

    /** TableName=EXME_LogImpPlantilla */
    public static final String Table_Name = "EXME_LogImpPlantilla";

    /** AD_Table_ID=1201024 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_LogImpPlantilla_ID */
    public static final String COLUMNNAME_EXME_LogImpPlantilla_ID = "EXME_LogImpPlantilla_ID";

	/** Set EXME_LogImpPlantilla_ID	  */
	public void setEXME_LogImpPlantilla_ID (int EXME_LogImpPlantilla_ID);

	/** Get EXME_LogImpPlantilla_ID	  */
	public int getEXME_LogImpPlantilla_ID();

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

    /** Column name EXME_Plantilla_ID */
    public static final String COLUMNNAME_EXME_Plantilla_ID = "EXME_Plantilla_ID";

	/** Set Template	  */
	public void setEXME_Plantilla_ID (int EXME_Plantilla_ID);

	/** Get Template	  */
	public int getEXME_Plantilla_ID();

	public I_EXME_Plantilla getEXME_Plantilla() throws RuntimeException;

    /** Column name ImpresionExitosa */
    public static final String COLUMNNAME_ImpresionExitosa = "ImpresionExitosa";

	/** Set Successful Printing.
	  * Successful Printing
	  */
	public void setImpresionExitosa (boolean ImpresionExitosa);

	/** Get Successful Printing.
	  * Successful Printing
	  */
	public boolean isImpresionExitosa();
}
