/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CuidadosPac
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CuidadosPac 
{

    /** TableName=EXME_CuidadosPac */
    public static final String Table_Name = "EXME_CuidadosPac";

    /** AD_Table_ID=1200445 */
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

    /** Column name AuthenticatedBy */
    public static final String COLUMNNAME_AuthenticatedBy = "AuthenticatedBy";

	/** Set Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy);

	/** Get Authenticated By	  */
	public int getAuthenticatedBy();

    /** Column name Authenticated_Date */
    public static final String COLUMNNAME_Authenticated_Date = "Authenticated_Date";

	/** Set Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date);

	/** Get Authentication Date	  */
	public Timestamp getAuthenticated_Date();

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

    /** Column name DiscontinuedBy */
    public static final String COLUMNNAME_DiscontinuedBy = "DiscontinuedBy";

	/** Set Discontinued by.
	  * Discontinued By
	  */
	public void setDiscontinuedBy (int DiscontinuedBy);

	/** Get Discontinued by.
	  * Discontinued By
	  */
	public int getDiscontinuedBy();

    /** Column name DiscontinuedDate */
    public static final String COLUMNNAME_DiscontinuedDate = "DiscontinuedDate";

	/** Set Discontinued Date	  */
	public void setDiscontinuedDate (Timestamp DiscontinuedDate);

	/** Get Discontinued Date	  */
	public Timestamp getDiscontinuedDate();

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

    /** Column name EXME_CuidadosPac_ID */
    public static final String COLUMNNAME_EXME_CuidadosPac_ID = "EXME_CuidadosPac_ID";

	/** Set Patient's Care	  */
	public void setEXME_CuidadosPac_ID (int EXME_CuidadosPac_ID);

	/** Get Patient's Care	  */
	public int getEXME_CuidadosPac_ID();

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException;

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

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

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name NotedBy */
    public static final String COLUMNNAME_NotedBy = "NotedBy";

	/** Set Noted By	  */
	public void setNotedBy (int NotedBy);

	/** Get Noted By	  */
	public int getNotedBy();

    /** Column name NotedDate */
    public static final String COLUMNNAME_NotedDate = "NotedDate";

	/** Set Noted Date	  */
	public void setNotedDate (Timestamp NotedDate);

	/** Get Noted Date	  */
	public Timestamp getNotedDate();

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name ReadBack */
    public static final String COLUMNNAME_ReadBack = "ReadBack";

	/** Set Read Back.
	  * Read Back
	  */
	public void setReadBack (String ReadBack);

	/** Get Read Back.
	  * Read Back
	  */
	public String getReadBack();
}
