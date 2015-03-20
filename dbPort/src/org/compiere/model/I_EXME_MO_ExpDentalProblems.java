/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_ExpDentalProblems
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_ExpDentalProblems 
{

    /** TableName=EXME_MO_ExpDentalProblems */
    public static final String Table_Name = "EXME_MO_ExpDentalProblems";

    /** AD_Table_ID=1200579 */
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

    /** Column name Cara */
    public static final String COLUMNNAME_Cara = "Cara";

	/** Set Dental Face	  */
	public void setCara (BigDecimal Cara);

	/** Get Dental Face	  */
	public BigDecimal getCara();

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

    /** Column name EXME_MO_DentalProblems_ID */
    public static final String COLUMNNAME_EXME_MO_DentalProblems_ID = "EXME_MO_DentalProblems_ID";

	/** Set Dental Problem	  */
	public void setEXME_MO_DentalProblems_ID (int EXME_MO_DentalProblems_ID);

	/** Get Dental Problem	  */
	public int getEXME_MO_DentalProblems_ID();

	public I_EXME_MO_DentalProblems getEXME_MO_DentalProblems() throws RuntimeException;

    /** Column name EXME_MO_ExpDentalProblems_ID */
    public static final String COLUMNNAME_EXME_MO_ExpDentalProblems_ID = "EXME_MO_ExpDentalProblems_ID";

	/** Set Exp Dental Problem	  */
	public void setEXME_MO_ExpDentalProblems_ID (int EXME_MO_ExpDentalProblems_ID);

	/** Get Exp Dental Problem	  */
	public int getEXME_MO_ExpDentalProblems_ID();

    /** Column name EXME_MO_Expediente_ID */
    public static final String COLUMNNAME_EXME_MO_Expediente_ID = "EXME_MO_Expediente_ID";

	/** Set MO Record	  */
	public void setEXME_MO_Expediente_ID (int EXME_MO_Expediente_ID);

	/** Get MO Record	  */
	public int getEXME_MO_Expediente_ID();

	public I_EXME_MO_Expediente getEXME_MO_Expediente() throws RuntimeException;

    /** Column name FromTable */
    public static final String COLUMNNAME_FromTable = "FromTable";

	/** Set FromTable	  */
	public void setFromTable (boolean FromTable);

	/** Get FromTable	  */
	public boolean isFromTable();

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

    /** Column name Valor */
    public static final String COLUMNNAME_Valor = "Valor";

	/** Set Value	  */
	public void setValor (String Valor);

	/** Get Value	  */
	public String getValor();

    /** Column name Vestibular */
    public static final String COLUMNNAME_Vestibular = "Vestibular";

	/** Set Vestibular	  */
	public void setVestibular (boolean Vestibular);

	/** Get Vestibular	  */
	public boolean isVestibular();
}
