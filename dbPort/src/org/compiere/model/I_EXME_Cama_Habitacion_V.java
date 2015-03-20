/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Cama_Habitacion_V
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Cama_Habitacion_V 
{

    /** TableName=EXME_Cama_Habitacion_V */
    public static final String Table_Name = "EXME_Cama_Habitacion_V";

    /** AD_Table_ID=1200099 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Cama */
    public static final String COLUMNNAME_Cama = "Cama";

	/** Set Bed.
	  * Bed
	  */
	public void setCama (String Cama);

	/** Get Bed.
	  * Bed
	  */
	public String getCama();

    /** Column name EXME_Cama_Habitacion_ID_V */
    public static final String COLUMNNAME_EXME_Cama_Habitacion_ID_V = "EXME_Cama_Habitacion_ID_V";

	/** Set EXME_Cama_Habitacion_ID_V	  */
	public void setEXME_Cama_Habitacion_ID_V (int EXME_Cama_Habitacion_ID_V);

	/** Get EXME_Cama_Habitacion_ID_V	  */
	public int getEXME_Cama_Habitacion_ID_V();

    /** Column name Habitacion */
    public static final String COLUMNNAME_Habitacion = "Habitacion";

	/** Set Room.
	  * Room
	  */
	public void setHabitacion (String Habitacion);

	/** Get Room.
	  * Room
	  */
	public String getHabitacion();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();
}
