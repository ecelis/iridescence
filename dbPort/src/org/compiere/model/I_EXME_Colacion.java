/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Colacion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Colacion 
{

    /** TableName=EXME_Colacion */
    public static final String Table_Name = "EXME_Colacion";

    /** AD_Table_ID=1200522 */
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

    /** Column name Calorias */
    public static final String COLUMNNAME_Calorias = "Calorias";

	/** Set Calories	  */
	public void setCalorias (BigDecimal Calorias);

	/** Get Calories	  */
	public BigDecimal getCalorias();

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

    /** Column name EXME_Colacion_ID */
    public static final String COLUMNNAME_EXME_Colacion_ID = "EXME_Colacion_ID";

	/** Set Snack	  */
	public void setEXME_Colacion_ID (int EXME_Colacion_ID);

	/** Get Snack	  */
	public int getEXME_Colacion_ID();

    /** Column name EXME_Dieta1_ID */
    public static final String COLUMNNAME_EXME_Dieta1_ID = "EXME_Dieta1_ID";

	/** Set Firts Diet	  */
	public void setEXME_Dieta1_ID (int EXME_Dieta1_ID);

	/** Get Firts Diet	  */
	public int getEXME_Dieta1_ID();

    /** Column name EXME_Dieta2_ID */
    public static final String COLUMNNAME_EXME_Dieta2_ID = "EXME_Dieta2_ID";

	/** Set Second Diet	  */
	public void setEXME_Dieta2_ID (int EXME_Dieta2_ID);

	/** Get Second Diet	  */
	public int getEXME_Dieta2_ID();

    /** Column name EXME_Dieta3_ID */
    public static final String COLUMNNAME_EXME_Dieta3_ID = "EXME_Dieta3_ID";

	/** Set Third Diet	  */
	public void setEXME_Dieta3_ID (int EXME_Dieta3_ID);

	/** Get Third Diet	  */
	public int getEXME_Dieta3_ID();

    /** Column name EXME_Dieta4_ID */
    public static final String COLUMNNAME_EXME_Dieta4_ID = "EXME_Dieta4_ID";

	/** Set Fourth Diet	  */
	public void setEXME_Dieta4_ID (int EXME_Dieta4_ID);

	/** Get Fourth Diet	  */
	public int getEXME_Dieta4_ID();

    /** Column name EXME_TipoColacion */
    public static final String COLUMNNAME_EXME_TipoColacion = "EXME_TipoColacion";

	/** Set Snack Type	  */
	public void setEXME_TipoColacion (String EXME_TipoColacion);

	/** Get Snack Type	  */
	public String getEXME_TipoColacion();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

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
