/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_EquipoH
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_EquipoH 
{

    /** TableName=I_EXME_EquipoH */
    public static final String Table_Name = "I_EXME_EquipoH";

    /** AD_Table_ID=1200343 */
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

    /** Column name Codigo_Equipo */
    public static final String COLUMNNAME_Codigo_Equipo = "Codigo_Equipo";

	/** Set Equipment code	  */
	public void setCodigo_Equipo (String Codigo_Equipo);

	/** Get Equipment code	  */
	public String getCodigo_Equipo();

    /** Column name Estatus_Equipo */
    public static final String COLUMNNAME_Estatus_Equipo = "Estatus_Equipo";

	/** Set Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo);

	/** Get Equipment Status	  */
	public String getEstatus_Equipo();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_Equipo_ID */
    public static final String COLUMNNAME_EXME_Equipo_ID = "EXME_Equipo_ID";

	/** Set Equipment.
	  * Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID);

	/** Get Equipment.
	  * Equipment
	  */
	public int getEXME_Equipo_ID();

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (String Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public String getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (String Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public String getFecha_Ini();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_EquipoH_ID */
    public static final String COLUMNNAME_I_EXME_EquipoH_ID = "I_EXME_EquipoH_ID";

	/** Set I_EXME_EquipoH_ID	  */
	public void setI_EXME_EquipoH_ID (int I_EXME_EquipoH_ID);

	/** Get I_EXME_EquipoH_ID	  */
	public int getI_EXME_EquipoH_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
