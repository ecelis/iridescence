/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProyectoInv
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProyectoInv 
{

    /** TableName=EXME_ProyectoInv */
    public static final String Table_Name = "EXME_ProyectoInv";

    /** AD_Table_ID=1200327 */
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

    /** Column name Entregable */
    public static final String COLUMNNAME_Entregable = "Entregable";

	/** Set Deliverable	  */
	public void setEntregable (String Entregable);

	/** Get Deliverable	  */
	public String getEntregable();

    /** Column name EXME_ProyectoInv_ID */
    public static final String COLUMNNAME_EXME_ProyectoInv_ID = "EXME_ProyectoInv_ID";

	/** Set Research Project	  */
	public void setEXME_ProyectoInv_ID (int EXME_ProyectoInv_ID);

	/** Get Research Project	  */
	public int getEXME_ProyectoInv_ID();

    /** Column name EXME_TipoProyecto_ID */
    public static final String COLUMNNAME_EXME_TipoProyecto_ID = "EXME_TipoProyecto_ID";

	/** Set Project Type	  */
	public void setEXME_TipoProyecto_ID (int EXME_TipoProyecto_ID);

	/** Get Project Type	  */
	public int getEXME_TipoProyecto_ID();

	public I_EXME_TipoProyecto getEXME_TipoProyecto() throws RuntimeException;

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsEvaluated */
    public static final String COLUMNNAME_IsEvaluated = "IsEvaluated";

	/** Set Is Evaluated	  */
	public void setIsEvaluated (boolean IsEvaluated);

	/** Get Is Evaluated	  */
	public boolean isEvaluated();

    /** Column name IsProgrammed */
    public static final String COLUMNNAME_IsProgrammed = "IsProgrammed";

	/** Set is Programmed	  */
	public void setIsProgrammed (boolean IsProgrammed);

	/** Get is Programmed	  */
	public boolean isProgrammed();

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

    /** Column name Result */
    public static final String COLUMNNAME_Result = "Result";

	/** Set Result.
	  * Result of the action taken
	  */
	public void setResult (String Result);

	/** Get Result.
	  * Result of the action taken
	  */
	public String getResult();

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
