/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ReporteImpreso
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ReporteImpreso 
{

    /** TableName=EXME_ReporteImpreso */
    public static final String Table_Name = "EXME_ReporteImpreso";

    /** AD_Table_ID=1200857 */
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

    /** Column name EXME_ReporteImpreso_ID */
    public static final String COLUMNNAME_EXME_ReporteImpreso_ID = "EXME_ReporteImpreso_ID";

	/** Set Printed Report	  */
	public void setEXME_ReporteImpreso_ID (int EXME_ReporteImpreso_ID);

	/** Get Printed Report	  */
	public int getEXME_ReporteImpreso_ID();

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

    /** Column name Params */
    public static final String COLUMNNAME_Params = "Params";

	/** Set Parameters	  */
	public void setParams (String Params);

	/** Get Parameters	  */
	public String getParams();

    /** Column name Sql */
    public static final String COLUMNNAME_Sql = "Sql";

	/** Set Sql	  */
	public void setSql (String Sql);

	/** Get Sql	  */
	public String getSql();
}
