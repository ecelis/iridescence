/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_CartaAutoriza
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_CartaAutoriza 
{

    /** TableName=I_EXME_CartaAutoriza */
    public static final String Table_Name = "I_EXME_CartaAutoriza";

    /** AD_Table_ID=1200067 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name AD_User_Name */
    public static final String COLUMNNAME_AD_User_Name = "AD_User_Name";

	/** Set User Name.
	  * User Name
	  */
	public void setAD_User_Name (String AD_User_Name);

	/** Get User Name.
	  * User Name
	  */
	public String getAD_User_Name();

    /** Column name Aprobada */
    public static final String COLUMNNAME_Aprobada = "Aprobada";

	/** Set Approved.
	  * Approved
	  */
	public void setAprobada (boolean Aprobada);

	/** Get Approved.
	  * Approved
	  */
	public boolean isAprobada();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DatePrinted */
    public static final String COLUMNNAME_DatePrinted = "DatePrinted";

	/** Set Date printed.
	  * Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted);

	/** Get Date printed.
	  * Date the document was printed.
	  */
	public Timestamp getDatePrinted();

    /** Column name Donadores */
    public static final String COLUMNNAME_Donadores = "Donadores";

	/** Set Donors.
	  * Donors
	  */
	public void setDonadores (BigDecimal Donadores);

	/** Get Donors.
	  * Donors
	  */
	public BigDecimal getDonadores();

    /** Column name EXME_CartaAutoriza_ID */
    public static final String COLUMNNAME_EXME_CartaAutoriza_ID = "EXME_CartaAutoriza_ID";

	/** Set Authorization Letter.
	  * Authorization Letter
	  */
	public void setEXME_CartaAutoriza_ID (int EXME_CartaAutoriza_ID);

	/** Get Authorization Letter.
	  * Authorization Letter
	  */
	public int getEXME_CartaAutoriza_ID();

	public I_EXME_CartaAutoriza getEXME_CartaAutoriza() throws RuntimeException;

    /** Column name EXME_Hist_Exp_ID */
    public static final String COLUMNNAME_EXME_Hist_Exp_ID = "EXME_Hist_Exp_ID";

	/** Set Medical File.
	  * Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID);

	/** Get Medical File.
	  * Medical File
	  */
	public int getEXME_Hist_Exp_ID();

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException;

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

    /** Column name EXME_Paciente_Value */
    public static final String COLUMNNAME_EXME_Paciente_Value = "EXME_Paciente_Value";

	/** Set Patient History Number.
	  * Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value);

	/** Get Patient History Number.
	  * Patient History Number
	  */
	public String getEXME_Paciente_Value();

    /** Column name Expediente */
    public static final String COLUMNNAME_Expediente = "Expediente";

	/** Set Medical Record.
	  * Medical Record
	  */
	public void setExpediente (String Expediente);

	/** Get Medical Record.
	  * Medical Record
	  */
	public String getExpediente();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_CartaAutoriza_ID */
    public static final String COLUMNNAME_I_EXME_CartaAutoriza_ID = "I_EXME_CartaAutoriza_ID";

	/** Set Authorization Letter	  */
	public void setI_EXME_CartaAutoriza_ID (int I_EXME_CartaAutoriza_ID);

	/** Get Authorization Letter	  */
	public int getI_EXME_CartaAutoriza_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Testigo1 */
    public static final String COLUMNNAME_Testigo1 = "Testigo1";

	/** Set Witness 1.
	  * Witness 1
	  */
	public void setTestigo1 (String Testigo1);

	/** Get Witness 1.
	  * Witness 1
	  */
	public String getTestigo1();

    /** Column name Testigo2 */
    public static final String COLUMNNAME_Testigo2 = "Testigo2";

	/** Set Witness 2.
	  * Witness 2
	  */
	public void setTestigo2 (String Testigo2);

	/** Get Witness 2.
	  * Witness 2
	  */
	public String getTestigo2();

    /** Column name TipoCarta */
    public static final String COLUMNNAME_TipoCarta = "TipoCarta";

	/** Set Letter Type.
	  * Letter Type
	  */
	public void setTipoCarta (String TipoCarta);

	/** Get Letter Type.
	  * Letter Type
	  */
	public String getTipoCarta();
}
