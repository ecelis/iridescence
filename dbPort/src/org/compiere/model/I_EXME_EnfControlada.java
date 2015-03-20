/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnfControlada
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnfControlada 
{

    /** TableName=EXME_EnfControlada */
    public static final String Table_Name = "EXME_EnfControlada";

    /** AD_Table_ID=1200493 */
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

    /** Column name EPI_Clave */
    public static final String COLUMNNAME_EPI_Clave = "EPI_Clave";

	/** Set EPI Key.
	  * EPI Key
	  */
	public void setEPI_Clave (int EPI_Clave);

	/** Get EPI Key.
	  * EPI Key
	  */
	public int getEPI_Clave();

    /** Column name EXME_EnfControlada_ID */
    public static final String COLUMNNAME_EXME_EnfControlada_ID = "EXME_EnfControlada_ID";

	/** Set Controlled Illness.
	  * Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID);

	/** Get Controlled Illness.
	  * Controlled Illness
	  */
	public int getEXME_EnfControlada_ID();

    /** Column name EXME_GrupoEnf_ID */
    public static final String COLUMNNAME_EXME_GrupoEnf_ID = "EXME_GrupoEnf_ID";

	/** Set Illness Group.
	  * Controlled illness group
	  */
	public void setEXME_GrupoEnf_ID (int EXME_GrupoEnf_ID);

	/** Get Illness Group.
	  * Controlled illness group
	  */
	public int getEXME_GrupoEnf_ID();

	public I_EXME_GrupoEnf getEXME_GrupoEnf() throws RuntimeException;

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

    /** Column name NotificarCaso */
    public static final String COLUMNNAME_NotificarCaso = "NotificarCaso";

	/** Set Notify Case.
	  * Notify case of a controlled illness
	  */
	public void setNotificarCaso (boolean NotificarCaso);

	/** Get Notify Case.
	  * Notify case of a controlled illness
	  */
	public boolean isNotificarCaso();

    /** Column name ReqEstEpidemiologico */
    public static final String COLUMNNAME_ReqEstEpidemiologico = "ReqEstEpidemiologico";

	/** Set Requires Epidemiological Study.
	  * The controlled illness requires epidemiological study
	  */
	public void setReqEstEpidemiologico (boolean ReqEstEpidemiologico);

	/** Get Requires Epidemiological Study.
	  * The controlled illness requires epidemiological study
	  */
	public boolean isReqEstEpidemiologico();

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
