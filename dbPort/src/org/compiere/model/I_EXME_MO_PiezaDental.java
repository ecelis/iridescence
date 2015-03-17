/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MO_PiezaDental
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MO_PiezaDental 
{

    /** TableName=EXME_MO_PiezaDental */
    public static final String Table_Name = "EXME_MO_PiezaDental";

    /** AD_Table_ID=1200387 */
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

    /** Column name EXME_MO_PiezaDental_ID */
    public static final String COLUMNNAME_EXME_MO_PiezaDental_ID = "EXME_MO_PiezaDental_ID";

	/** Set Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID);

	/** Get Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID();

    /** Column name IsAdulto */
    public static final String COLUMNNAME_IsAdulto = "IsAdulto";

	/** Set Is Adult	  */
	public void setIsAdulto (boolean IsAdulto);

	/** Get Is Adult	  */
	public boolean isAdulto();

    /** Column name IsLingual */
    public static final String COLUMNNAME_IsLingual = "IsLingual";

	/** Set IsLingual	  */
	public void setIsLingual (boolean IsLingual);

	/** Get IsLingual	  */
	public boolean isLingual();

    /** Column name MapaAdulto */
    public static final String COLUMNNAME_MapaAdulto = "MapaAdulto";

	/** Set Adult Map	  */
	public void setMapaAdulto (String MapaAdulto);

	/** Get Adult Map	  */
	public String getMapaAdulto();

    /** Column name MapaInfantil */
    public static final String COLUMNNAME_MapaInfantil = "MapaInfantil";

	/** Set Dental Infant Map	  */
	public void setMapaInfantil (String MapaInfantil);

	/** Get Dental Infant Map	  */
	public String getMapaInfantil();

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

    /** Column name Ruta */
    public static final String COLUMNNAME_Ruta = "Ruta";

	/** Set Route.
	  * Route of screen or process
	  */
	public void setRuta (String Ruta);

	/** Get Route.
	  * Route of screen or process
	  */
	public String getRuta();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();

    /** Column name Tipo_PiezaDental */
    public static final String COLUMNNAME_Tipo_PiezaDental = "Tipo_PiezaDental";

	/** Set Dental Piece Type	  */
	public void setTipo_PiezaDental (String Tipo_PiezaDental);

	/** Get Dental Piece Type	  */
	public String getTipo_PiezaDental();

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
