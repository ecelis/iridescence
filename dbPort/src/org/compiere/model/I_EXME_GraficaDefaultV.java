/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GraficaDefaultV
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GraficaDefaultV 
{

    /** TableName=EXME_GraficaDefaultV */
    public static final String Table_Name = "EXME_GraficaDefaultV";

    /** AD_Table_ID=1201086 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_GraficaDefaultV_ID */
    public static final String COLUMNNAME_EXME_GraficaDefaultV_ID = "EXME_GraficaDefaultV_ID";

	/** Set Graphic Default Values	  */
	public void setEXME_GraficaDefaultV_ID (int EXME_GraficaDefaultV_ID);

	/** Get Graphic Default Values	  */
	public int getEXME_GraficaDefaultV_ID();

    /** Column name EXME_Grafica_ID */
    public static final String COLUMNNAME_EXME_Grafica_ID = "EXME_Grafica_ID";

	/** Set Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID);

	/** Get Graphic	  */
	public int getEXME_Grafica_ID();

	public I_EXME_Grafica getEXME_Grafica() throws RuntimeException;

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
