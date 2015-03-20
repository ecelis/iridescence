/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CIF
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CIF 
{

    /** TableName=EXME_CIF */
    public static final String Table_Name = "EXME_CIF";

    /** AD_Table_ID=1200537 */
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

    /** Column name CodGrd */
    public static final String COLUMNNAME_CodGrd = "CodGrd";

	/** Set Code GRD.
	  * Code GRD
	  */
	public void setCodGrd (String CodGrd);

	/** Get Code GRD.
	  * Code GRD
	  */
	public String getCodGrd();

    /** Column name CodInegi */
    public static final String COLUMNNAME_CodInegi = "CodInegi";

	/** Set Code INEGI.
	  * Code INEGI
	  */
	public void setCodInegi (String CodInegi);

	/** Get Code INEGI.
	  * Code INEGI
	  */
	public String getCodInegi();

    /** Column name CodOms */
    public static final String COLUMNNAME_CodOms = "CodOms";

	/** Set World Organization Health Code.
	  * World Organization Health Code
	  */
	public void setCodOms (String CodOms);

	/** Get World Organization Health Code.
	  * World Organization Health Code
	  */
	public String getCodOms();

    /** Column name CodSnomed */
    public static final String COLUMNNAME_CodSnomed = "CodSnomed";

	/** Set Snomed Code.
	  * Snomed Code
	  */
	public void setCodSnomed (String CodSnomed);

	/** Get Snomed Code.
	  * Snomed Code
	  */
	public String getCodSnomed();

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

    /** Column name EXME_CIFHdr_ID */
    public static final String COLUMNNAME_EXME_CIFHdr_ID = "EXME_CIFHdr_ID";

	/** Set ICF Version	  */
	public void setEXME_CIFHdr_ID (int EXME_CIFHdr_ID);

	/** Get ICF Version	  */
	public int getEXME_CIFHdr_ID();

	public I_EXME_CIFHdr getEXME_CIFHdr() throws RuntimeException;

    /** Column name EXME_CIF_ID */
    public static final String COLUMNNAME_EXME_CIF_ID = "EXME_CIF_ID";

	/** Set ICF.
	  * International Classification of Functioning, Disability and Health
	  */
	public void setEXME_CIF_ID (int EXME_CIF_ID);

	/** Get ICF.
	  * International Classification of Functioning, Disability and Health
	  */
	public int getEXME_CIF_ID();

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
