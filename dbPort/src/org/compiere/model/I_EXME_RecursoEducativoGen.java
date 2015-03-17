/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativoGen
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_RecursoEducativoGen 
{

    /** TableName=EXME_RecursoEducativoGen */
    public static final String Table_Name = "EXME_RecursoEducativoGen";

    /** AD_Table_ID=1201193 */
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

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

    /** Column name EXME_RecursoEducativoGen_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativoGen_ID = "EXME_RecursoEducativoGen_ID";

	/** Set Educational Resource Generic	  */
	public void setEXME_RecursoEducativoGen_ID (int EXME_RecursoEducativoGen_ID);

	/** Get Educational Resource Generic	  */
	public int getEXME_RecursoEducativoGen_ID();

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException;
}
