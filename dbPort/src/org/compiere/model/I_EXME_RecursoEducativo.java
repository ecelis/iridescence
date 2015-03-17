/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_RecursoEducativo 
{

    /** TableName=EXME_RecursoEducativo */
    public static final String Table_Name = "EXME_RecursoEducativo";

    /** AD_Table_ID=1201027 */
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

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

    /** Column name FechaRecursoPac */
    public static final String COLUMNNAME_FechaRecursoPac = "FechaRecursoPac";

	/** Set Date Patient Resource	  */
	public void setFechaRecursoPac (Timestamp FechaRecursoPac);

	/** Get Date Patient Resource	  */
	public Timestamp getFechaRecursoPac();

    /** Column name TipoRecurso */
    public static final String COLUMNNAME_TipoRecurso = "TipoRecurso";

	/** Set Resource Type	  */
	public void setTipoRecurso (String TipoRecurso);

	/** Get Resource Type	  */
	public String getTipoRecurso();

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL();
}
