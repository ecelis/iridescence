/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativoStr
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecursoEducativoStr 
{

    /** TableName=EXME_RecursoEducativoStr */
    public static final String Table_Name = "EXME_RecursoEducativoStr";

    /** AD_Table_ID=1201088 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException;

    /** Column name EXME_RecursoEducativoStr_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativoStr_ID = "EXME_RecursoEducativoStr_ID";

	/** Set Education Resource Stroke	  */
	public void setEXME_RecursoEducativoStr_ID (int EXME_RecursoEducativoStr_ID);

	/** Get Education Resource Stroke	  */
	public int getEXME_RecursoEducativoStr_ID();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();
}
