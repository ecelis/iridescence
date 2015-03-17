/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteIndLog
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_ActPacienteIndLog 
{

    /** TableName=EXME_ActPacienteIndLog */
    public static final String Table_Name = "EXME_ActPacienteIndLog";

    /** AD_Table_ID=1201170 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_Attachment_ID */
    public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";

	/** Set Attachment.
	  * Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID);

	/** Get Attachment.
	  * Attachment for the document
	  */
	public int getAD_Attachment_ID();

	public I_AD_Attachment getAD_Attachment() throws RuntimeException;

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

    /** Column name Anotaciones */
    public static final String COLUMNNAME_Anotaciones = "Anotaciones";

	/** Set Notes.
	  * Notes related to the annexed image of the indication
	  */
	public void setAnotaciones (String Anotaciones);

	/** Get Notes.
	  * Notes related to the annexed image of the indication
	  */
	public String getAnotaciones();

    /** Column name EXME_ActPacienteIndH_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndH_ID = "EXME_ActPacienteIndH_ID";

	/** Set Header Indcations Patient Activity.
	  * Header Indications Patient Activity
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID);

	/** Get Header Indcations Patient Activity.
	  * Header Indications Patient Activity
	  */
	public int getEXME_ActPacienteIndH_ID();

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException;

    /** Column name EXME_ActPacienteIndLog_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndLog_ID = "EXME_ActPacienteIndLog_ID";

	/** Set Order Detail Log.
	  * Order Detail Log
	  */
	public void setEXME_ActPacienteIndLog_ID (int EXME_ActPacienteIndLog_ID);

	/** Get Order Detail Log.
	  * Order Detail Log
	  */
	public int getEXME_ActPacienteIndLog_ID();

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indications Patient Activity.
	  * Indications Patient Activity
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indications Patient Activity.
	  * Indications Patient Activity
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name Reference */
    public static final String COLUMNNAME_Reference = "Reference";

	/** Set Reference.
	  * Reference for this record
	  */
	public void setReference (String Reference);

	/** Get Reference.
	  * Reference for this record
	  */
	public String getReference();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
