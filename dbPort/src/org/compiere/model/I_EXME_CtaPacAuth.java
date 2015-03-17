/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacAuth
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CtaPacAuth 
{

    /** TableName=EXME_CtaPacAuth */
    public static final String Table_Name = "EXME_CtaPacAuth";

    /** AD_Table_ID=1201199 */
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

    /** Column name AuthCode */
    public static final String COLUMNNAME_AuthCode = "AuthCode";

	/** Set Authorization Code	  */
	public void setAuthCode (String AuthCode);

	/** Get Authorization Code	  */
	public String getAuthCode();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name DOSThrough */
    public static final String COLUMNNAME_DOSThrough = "DOSThrough";

	/** Set Date of Service Throug	  */
	public void setDOSThrough (Timestamp DOSThrough);

	/** Get Date of Service Throug	  */
	public Timestamp getDOSThrough();

    /** Column name EXME_CtaPacAuth_ID */
    public static final String COLUMNNAME_EXME_CtaPacAuth_ID = "EXME_CtaPacAuth_ID";

	/** Set Procedures Authorization	  */
	public void setEXME_CtaPacAuth_ID (int EXME_CtaPacAuth_ID);

	/** Get Procedures Authorization	  */
	public int getEXME_CtaPacAuth_ID();

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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_PacienteAseg_ID */
    public static final String COLUMNNAME_EXME_PacienteAseg_ID = "EXME_PacienteAseg_ID";

	/** Set Patient's Insurance	  */
	public void setEXME_PacienteAseg_ID (int EXME_PacienteAseg_ID);

	/** Get Patient's Insurance	  */
	public int getEXME_PacienteAseg_ID();

	public I_EXME_PacienteAseg getEXME_PacienteAseg() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;
}
