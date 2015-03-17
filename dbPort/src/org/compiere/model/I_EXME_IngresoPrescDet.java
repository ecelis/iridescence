/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_IngresoPrescDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_IngresoPrescDet 
{

    /** TableName=EXME_IngresoPrescDet */
    public static final String Table_Name = "EXME_IngresoPrescDet";

    /** AD_Table_ID=1200472 */
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

    /** Column name Aplicacion */
    public static final String COLUMNNAME_Aplicacion = "Aplicacion";

	/** Set Application	  */
	public void setAplicacion (String Aplicacion);

	/** Get Application	  */
	public String getAplicacion();

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

    /** Column name EXME_DiarioEnf_ID */
    public static final String COLUMNNAME_EXME_DiarioEnf_ID = "EXME_DiarioEnf_ID";

	/** Set Infirmary Diary	  */
	public void setEXME_DiarioEnf_ID (int EXME_DiarioEnf_ID);

	/** Get Infirmary Diary	  */
	public int getEXME_DiarioEnf_ID();

	public I_EXME_DiarioEnf getEXME_DiarioEnf() throws RuntimeException;

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_IngresoPrescDet_ID */
    public static final String COLUMNNAME_EXME_IngresoPrescDet_ID = "EXME_IngresoPrescDet_ID";

	/** Set Entry detail	  */
	public void setEXME_IngresoPrescDet_ID (int EXME_IngresoPrescDet_ID);

	/** Get Entry detail	  */
	public int getEXME_IngresoPrescDet_ID();

    /** Column name EXME_IngresoPresc_ID */
    public static final String COLUMNNAME_EXME_IngresoPresc_ID = "EXME_IngresoPresc_ID";

	/** Set Entry	  */
	public void setEXME_IngresoPresc_ID (int EXME_IngresoPresc_ID);

	/** Get Entry	  */
	public int getEXME_IngresoPresc_ID();

	public I_EXME_IngresoPresc getEXME_IngresoPresc() throws RuntimeException;

    /** Column name FechaAplica */
    public static final String COLUMNNAME_FechaAplica = "FechaAplica";

	/** Set Date of Application.
	  * Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica);

	/** Get Date of Application.
	  * Date of Application
	  */
	public Timestamp getFechaAplica();

    /** Column name FechaProg */
    public static final String COLUMNNAME_FechaProg = "FechaProg";

	/** Set Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg);

	/** Get Scheduled Date	  */
	public Timestamp getFechaProg();
}
