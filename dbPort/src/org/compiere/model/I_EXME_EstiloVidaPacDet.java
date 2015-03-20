/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EstiloVidaPacDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EstiloVidaPacDet 
{

    /** TableName=EXME_EstiloVidaPacDet */
    public static final String Table_Name = "EXME_EstiloVidaPacDet";

    /** AD_Table_ID=1201080 */
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

    /** Column name EXME_ActPacienteDiag_ID */
    public static final String COLUMNNAME_EXME_ActPacienteDiag_ID = "EXME_ActPacienteDiag_ID";

	/** Set Patient's Diagnostic.
	  * Patient's Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID);

	/** Get Patient's Diagnostic.
	  * Patient's Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID();

	public I_EXME_ActPacienteDiag getEXME_ActPacienteDiag() throws RuntimeException;

    /** Column name EXME_EstiloVidaPacDet_ID */
    public static final String COLUMNNAME_EXME_EstiloVidaPacDet_ID = "EXME_EstiloVidaPacDet_ID";

	/** Set Patient Life Style History	  */
	public void setEXME_EstiloVidaPacDet_ID (int EXME_EstiloVidaPacDet_ID);

	/** Get Patient Life Style History	  */
	public int getEXME_EstiloVidaPacDet_ID();

    /** Column name EXME_EstiloVidaPaciente_ID */
    public static final String COLUMNNAME_EXME_EstiloVidaPaciente_ID = "EXME_EstiloVidaPaciente_ID";

	/** Set Patient's Life Style	  */
	public void setEXME_EstiloVidaPaciente_ID (int EXME_EstiloVidaPaciente_ID);

	/** Get Patient's Life Style	  */
	public int getEXME_EstiloVidaPaciente_ID();

	public I_EXME_EstiloVidaPaciente getEXME_EstiloVidaPaciente() throws RuntimeException;

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

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

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

    /** Column name Seguimiento */
    public static final String COLUMNNAME_Seguimiento = "Seguimiento";

	/** Set Monitoring	  */
	public void setSeguimiento (String Seguimiento);

	/** Get Monitoring	  */
	public String getSeguimiento();
}
