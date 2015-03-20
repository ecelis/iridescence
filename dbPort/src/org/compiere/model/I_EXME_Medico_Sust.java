/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Medico_Sust
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Medico_Sust 
{

    /** TableName=EXME_Medico_Sust */
    public static final String Table_Name = "EXME_Medico_Sust";

    /** AD_Table_ID=1200369 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

    /** Column name EXME_Medico_Sust_ID */
    public static final String COLUMNNAME_EXME_Medico_Sust_ID = "EXME_Medico_Sust_ID";

	/** Set Substitute Doctor.
	  * Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID);

	/** Get Substitute Doctor.
	  * Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();

    /** Column name Substitute_ID */
    public static final String COLUMNNAME_Substitute_ID = "Substitute_ID";

	/** Set Substitute.
	  * Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID);

	/** Get Substitute.
	  * Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID();
}
