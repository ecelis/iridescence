/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacDieta
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CtaPacDieta 
{

    /** TableName=EXME_CtaPacDieta */
    public static final String Table_Name = "EXME_CtaPacDieta";

    /** AD_Table_ID=1200151 */
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

    /** Column name EXME_CtaPacDieta_ID */
    public static final String COLUMNNAME_EXME_CtaPacDieta_ID = "EXME_CtaPacDieta_ID";

	/** Set Encounter Diet 	  */
	public void setEXME_CtaPacDieta_ID (int EXME_CtaPacDieta_ID);

	/** Get Encounter Diet 	  */
	public int getEXME_CtaPacDieta_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Dieta_ID */
    public static final String COLUMNNAME_EXME_Dieta_ID = "EXME_Dieta_ID";

	/** Set Diet	  */
	public void setEXME_Dieta_ID (int EXME_Dieta_ID);

	/** Get Diet	  */
	public int getEXME_Dieta_ID();

	public I_EXME_Dieta getEXME_Dieta() throws RuntimeException;

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();
}
