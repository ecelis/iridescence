/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Curso
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Curso 
{

    /** TableName=EXME_Curso */
    public static final String Table_Name = "EXME_Curso";

    /** AD_Table_ID=1200436 */
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

    /** Column name EXME_Curso_ID */
    public static final String COLUMNNAME_EXME_Curso_ID = "EXME_Curso_ID";

	/** Set Course.
	  * Course
	  */
	public void setEXME_Curso_ID (int EXME_Curso_ID);

	/** Get Course.
	  * Course
	  */
	public int getEXME_Curso_ID();

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

    /** Column name Lugar */
    public static final String COLUMNNAME_Lugar = "Lugar";

	/** Set Place.
	  * Place
	  */
	public void setLugar (String Lugar);

	/** Get Place.
	  * Place
	  */
	public String getLugar();

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

    /** Column name Plantel */
    public static final String COLUMNNAME_Plantel = "Plantel";

	/** Set Operations Centers	  */
	public void setPlantel (String Plantel);

	/** Get Operations Centers	  */
	public String getPlantel();

    /** Column name Tipo_Curso */
    public static final String COLUMNNAME_Tipo_Curso = "Tipo_Curso";

	/** Set Course Type.
	  * Course Type
	  */
	public void setTipo_Curso (String Tipo_Curso);

	/** Get Course Type.
	  * Course Type
	  */
	public String getTipo_Curso();

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
