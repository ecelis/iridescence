/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Autopsia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Autopsia 
{

    /** TableName=EXME_Autopsia */
    public static final String Table_Name = "EXME_Autopsia";

    /** AD_Table_ID=1200316 */
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

    /** Column name EXME_Autopsia_ID */
    public static final String COLUMNNAME_EXME_Autopsia_ID = "EXME_Autopsia_ID";

	/** Set Autopsy.
	  * Autopsy
	  */
	public void setEXME_Autopsia_ID (int EXME_Autopsia_ID);

	/** Get Autopsy.
	  * Autopsy
	  */
	public int getEXME_Autopsia_ID();

    /** Column name EXME_MedicoAux1_ID */
    public static final String COLUMNNAME_EXME_MedicoAux1_ID = "EXME_MedicoAux1_ID";

	/** Set Auxiliar Doctor 1.
	  * Auxiliar Doctor 1
	  */
	public void setEXME_MedicoAux1_ID (int EXME_MedicoAux1_ID);

	/** Get Auxiliar Doctor 1.
	  * Auxiliar Doctor 1
	  */
	public int getEXME_MedicoAux1_ID();

    /** Column name EXME_MedicoAux2_ID */
    public static final String COLUMNNAME_EXME_MedicoAux2_ID = "EXME_MedicoAux2_ID";

	/** Set Auxiliar Doctor 2.
	  * Auxiliar Doctor 2
	  */
	public void setEXME_MedicoAux2_ID (int EXME_MedicoAux2_ID);

	/** Get Auxiliar Doctor 2.
	  * Auxiliar Doctor 2
	  */
	public int getEXME_MedicoAux2_ID();

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

    /** Column name EXME_Morgue_ID */
    public static final String COLUMNNAME_EXME_Morgue_ID = "EXME_Morgue_ID";

	/** Set Morgue.
	  * Morgue
	  */
	public void setEXME_Morgue_ID (int EXME_Morgue_ID);

	/** Get Morgue.
	  * Morgue
	  */
	public int getEXME_Morgue_ID();

	public I_EXME_Morgue getEXME_Morgue() throws RuntimeException;

    /** Column name EXME_MotivoMuerte_ID */
    public static final String COLUMNNAME_EXME_MotivoMuerte_ID = "EXME_MotivoMuerte_ID";

	/** Set Death Cause.
	  * Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID);

	/** Get Death Cause.
	  * Death Cause
	  */
	public int getEXME_MotivoMuerte_ID();

	public I_EXME_MotivoMuerte getEXME_MotivoMuerte() throws RuntimeException;

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

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
