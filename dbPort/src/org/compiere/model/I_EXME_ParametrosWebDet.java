/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ParametrosWebDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ParametrosWebDet 
{

    /** TableName=EXME_ParametrosWebDet */
    public static final String Table_Name = "EXME_ParametrosWebDet";

    /** AD_Table_ID=1200510 */
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

    /** Column name EXME_ParametrosWebDet_ID */
    public static final String COLUMNNAME_EXME_ParametrosWebDet_ID = "EXME_ParametrosWebDet_ID";

	/** Set Parametros Header para ruta web externas.
	  * Parametros Header para ruta web externas
	  */
	public void setEXME_ParametrosWebDet_ID (int EXME_ParametrosWebDet_ID);

	/** Get Parametros Header para ruta web externas.
	  * Parametros Header para ruta web externas
	  */
	public int getEXME_ParametrosWebDet_ID();

    /** Column name EXME_ParametrosWebH_ID */
    public static final String COLUMNNAME_EXME_ParametrosWebH_ID = "EXME_ParametrosWebH_ID";

	/** Set Access Link Configuration Parameter.
	  * Access Link Configuration Parameter
	  */
	public void setEXME_ParametrosWebH_ID (int EXME_ParametrosWebH_ID);

	/** Get Access Link Configuration Parameter.
	  * Access Link Configuration Parameter
	  */
	public int getEXME_ParametrosWebH_ID();

	public I_EXME_ParametrosWebH getEXME_ParametrosWebH() throws RuntimeException;

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

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

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
