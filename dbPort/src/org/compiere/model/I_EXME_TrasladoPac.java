/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TrasladoPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TrasladoPac 
{

    /** TableName=EXME_TrasladoPac */
    public static final String Table_Name = "EXME_TrasladoPac";

    /** AD_Table_ID=1200636 */
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

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Institution.
	  * Institution
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Institution.
	  * Institution
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

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

    /** Column name EXME_TrasladoPac_ID */
    public static final String COLUMNNAME_EXME_TrasladoPac_ID = "EXME_TrasladoPac_ID";

	/** Set Patient Transfer.
	  * Patient Transfer
	  */
	public void setEXME_TrasladoPac_ID (int EXME_TrasladoPac_ID);

	/** Get Patient Transfer.
	  * Patient Transfer
	  */
	public int getEXME_TrasladoPac_ID();

    /** Column name FechaTraslado */
    public static final String COLUMNNAME_FechaTraslado = "FechaTraslado";

	/** Set Transfer Date.
	  * Transfer Date
	  */
	public void setFechaTraslado (Timestamp FechaTraslado);

	/** Get Transfer Date.
	  * Transfer Date
	  */
	public Timestamp getFechaTraslado();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
