/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacChanges
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CtaPacChanges 
{

    /** TableName=EXME_CtaPacChanges */
    public static final String Table_Name = "EXME_CtaPacChanges";

    /** AD_Table_ID=1201180 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AdmitDateAct */
    public static final String COLUMNNAME_AdmitDateAct = "AdmitDateAct";

	/** Set Current Admit Date.
	  * Current Admit Date
	  */
	public void setAdmitDateAct (Timestamp AdmitDateAct);

	/** Get Current Admit Date.
	  * Current Admit Date
	  */
	public Timestamp getAdmitDateAct();

    /** Column name AdmitDateAnt */
    public static final String COLUMNNAME_AdmitDateAnt = "AdmitDateAnt";

	/** Set Previous Admit Date.
	  * Previous Admit Date
	  */
	public void setAdmitDateAnt (Timestamp AdmitDateAnt);

	/** Get Previous Admit Date.
	  * Previous Admit Date
	  */
	public Timestamp getAdmitDateAnt();

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

    /** Column name DepartureDate */
    public static final String COLUMNNAME_DepartureDate = "DepartureDate";

	/** Set DepartureDate	  */
	public void setDepartureDate (Timestamp DepartureDate);

	/** Get DepartureDate	  */
	public Timestamp getDepartureDate();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

    /** Column name EXME_AreaAct_ID */
    public static final String COLUMNNAME_EXME_AreaAct_ID = "EXME_AreaAct_ID";

	/** Set Current Area	  */
	public void setEXME_AreaAct_ID (int EXME_AreaAct_ID);

	/** Get Current Area	  */
	public int getEXME_AreaAct_ID();

    /** Column name EXME_AreaAnt_ID */
    public static final String COLUMNNAME_EXME_AreaAnt_ID = "EXME_AreaAnt_ID";

	/** Set Previous Area	  */
	public void setEXME_AreaAnt_ID (int EXME_AreaAnt_ID);

	/** Get Previous Area	  */
	public int getEXME_AreaAnt_ID();

    /** Column name EXME_CtaPacChanges_ID */
    public static final String COLUMNNAME_EXME_CtaPacChanges_ID = "EXME_CtaPacChanges_ID";

	/** Set Encounter Log	  */
	public void setEXME_CtaPacChanges_ID (int EXME_CtaPacChanges_ID);

	/** Get Encounter Log	  */
	public int getEXME_CtaPacChanges_ID();

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

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

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

    /** Column name EXME_TipoPacAct_ID */
    public static final String COLUMNNAME_EXME_TipoPacAct_ID = "EXME_TipoPacAct_ID";

	/** Set Current Patient Type	  */
	public void setEXME_TipoPacAct_ID (int EXME_TipoPacAct_ID);

	/** Get Current Patient Type	  */
	public int getEXME_TipoPacAct_ID();

    /** Column name EXME_TipoPacAnt_ID */
    public static final String COLUMNNAME_EXME_TipoPacAnt_ID = "EXME_TipoPacAnt_ID";

	/** Set Previous Patient Type	  */
	public void setEXME_TipoPacAnt_ID (int EXME_TipoPacAnt_ID);

	/** Get Previous Patient Type	  */
	public int getEXME_TipoPacAnt_ID();

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
	public void setReadBack (boolean ReadBack);

	/** Get Read Back.
	  * Read Back
	  */
	public boolean isReadBack();

    /** Column name ResStatusAct */
    public static final String COLUMNNAME_ResStatusAct = "ResStatusAct";

	/** Set Current Resuscitative Status	  */
	public void setResStatusAct (String ResStatusAct);

	/** Get Current Resuscitative Status	  */
	public String getResStatusAct();

    /** Column name ResStatusAnt */
    public static final String COLUMNNAME_ResStatusAnt = "ResStatusAnt";

	/** Set Previous Resuscitative Status	  */
	public void setResStatusAnt (String ResStatusAnt);

	/** Get Previous Resuscitative Status	  */
	public String getResStatusAnt();
}
