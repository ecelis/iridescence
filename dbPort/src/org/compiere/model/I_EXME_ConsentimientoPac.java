/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConsentimientoPac
 *  @author Generated Class 
 *  @version Release 2.0.0 (alpha)
 */
public interface I_EXME_ConsentimientoPac 
{

    /** TableName=EXME_ConsentimientoPac */
    public static final String Table_Name = "EXME_ConsentimientoPac";

    /** AD_Table_ID=1201025 */
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

    /** Column name Archivo */
    public static final String COLUMNNAME_Archivo = "Archivo";

	/** Set File	  */
	public void setArchivo (byte[] Archivo);

	/** Get File	  */
	public byte[] getArchivo();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

    /** Column name EXME_ConsentimientoPac_ID */
    public static final String COLUMNNAME_EXME_ConsentimientoPac_ID = "EXME_ConsentimientoPac_ID";

	/** Set EXME_ConsentimientoPac_ID	  */
	public void setEXME_ConsentimientoPac_ID (int EXME_ConsentimientoPac_ID);

	/** Get EXME_ConsentimientoPac_ID	  */
	public int getEXME_ConsentimientoPac_ID();

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

    /** Column name FormatoArchivo */
    public static final String COLUMNNAME_FormatoArchivo = "FormatoArchivo";

	/** Set File Format.
	  * File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo);

	/** Get File Format.
	  * File Format
	  */
	public String getFormatoArchivo();

    /** Column name NombreArchivo */
    public static final String COLUMNNAME_NombreArchivo = "NombreArchivo";

	/** Set File Name.
	  * File Name
	  */
	public void setNombreArchivo (String NombreArchivo);

	/** Get File Name.
	  * File Name
	  */
	public String getNombreArchivo();

    /** Column name ResStatus */
    public static final String COLUMNNAME_ResStatus = "ResStatus";

	/** Set Resuscitative Status	  */
	public void setResStatus (String ResStatus);

	/** Get Resuscitative Status	  */
	public String getResStatus();
}
