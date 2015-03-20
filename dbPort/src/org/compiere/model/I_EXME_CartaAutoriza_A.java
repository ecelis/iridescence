/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CartaAutoriza_A
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CartaAutoriza_A 
{

    /** TableName=EXME_CartaAutoriza_A */
    public static final String Table_Name = "EXME_CartaAutoriza_A";

    /** AD_Table_ID=1200038 */
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
	public void setDonadores (int Donadores);

	/** Get Donors.
	  * Donors
	  */
	public int getDonadores();

    /** Column name EXME_CartaAutoriza_A_ID */
    public static final String COLUMNNAME_EXME_CartaAutoriza_A_ID = "EXME_CartaAutoriza_A_ID";

	/** Set Audit Authorization Letter.
	  * Audit Authorization Letter
	  */
	public void setEXME_CartaAutoriza_A_ID (int EXME_CartaAutoriza_A_ID);

	/** Get Audit Authorization Letter.
	  * Audit Authorization Letter
	  */
	public int getEXME_CartaAutoriza_A_ID();

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

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (int Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public int getVersion();
}
