/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TipoPaciente
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_TipoPaciente 
{

    /** TableName=EXME_TipoPaciente */
    public static final String Table_Name = "EXME_TipoPaciente";

    /** AD_Table_ID=1000011 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EXME_AdmitType_ID */
    public static final String COLUMNNAME_EXME_AdmitType_ID = "EXME_AdmitType_ID";

	/** Set Admit Type.
	  * Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID);

	/** Get Admit Type.
	  * Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID();

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException;

    /** Column name EXME_POS_ID */
    public static final String COLUMNNAME_EXME_POS_ID = "EXME_POS_ID";

	/** Set Place of Service.
	  * Place of Service
	  */
	public void setEXME_POS_ID (int EXME_POS_ID);

	/** Get Place of Service.
	  * Place of Service
	  */
	public int getEXME_POS_ID();

	public I_EXME_POS getEXME_POS() throws RuntimeException;

    /** Column name EXME_TipoPaciente_ID */
    public static final String COLUMNNAME_EXME_TipoPaciente_ID = "EXME_TipoPaciente_ID";

	/** Set Type of Patient.
	  * Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID);

	/** Get Type of Patient.
	  * Type of Patient
	  */
	public int getEXME_TipoPaciente_ID();

    /** Column name EXME_TypeOfBill_ID */
    public static final String COLUMNNAME_EXME_TypeOfBill_ID = "EXME_TypeOfBill_ID";

	/** Set Type Of Bill.
	  * Type Of Bill
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID);

	/** Get Type Of Bill.
	  * Type Of Bill
	  */
	public int getEXME_TypeOfBill_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

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
