/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Equipo_Falla
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Equipo_Falla 
{

    /** TableName=EXME_Equipo_Falla */
    public static final String Table_Name = "EXME_Equipo_Falla";

    /** AD_Table_ID=1200540 */
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

    /** Column name Comentarios */
    public static final String COLUMNNAME_Comentarios = "Comentarios";

	/** Set Notes	  */
	public void setComentarios (String Comentarios);

	/** Get Notes	  */
	public String getComentarios();

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

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

    /** Column name EXME_Equipo_Falla_ID */
    public static final String COLUMNNAME_EXME_Equipo_Falla_ID = "EXME_Equipo_Falla_ID";

	/** Set Equipment Failure	  */
	public void setEXME_Equipo_Falla_ID (int EXME_Equipo_Falla_ID);

	/** Get Equipment Failure	  */
	public int getEXME_Equipo_Falla_ID();

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

    /** Column name FechaAtencion */
    public static final String COLUMNNAME_FechaAtencion = "FechaAtencion";

	/** Set Warning Date	  */
	public void setFechaAtencion (Timestamp FechaAtencion);

	/** Get Warning Date	  */
	public Timestamp getFechaAtencion();

    /** Column name FechaReporte */
    public static final String COLUMNNAME_FechaReporte = "FechaReporte";

	/** Set Report Date.
	  * Report Date
	  */
	public void setFechaReporte (Timestamp FechaReporte);

	/** Get Report Date.
	  * Report Date
	  */
	public Timestamp getFechaReporte();

    /** Column name FechaSolucion */
    public static final String COLUMNNAME_FechaSolucion = "FechaSolucion";

	/** Set Settlement Date	  */
	public void setFechaSolucion (Timestamp FechaSolucion);

	/** Get Settlement Date	  */
	public Timestamp getFechaSolucion();

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

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

    /** Column name Proveedor */
    public static final String COLUMNNAME_Proveedor = "Proveedor";

	/** Set Supplier	  */
	public void setProveedor (String Proveedor);

	/** Get Supplier	  */
	public String getProveedor();

    /** Column name Representante */
    public static final String COLUMNNAME_Representante = "Representante";

	/** Set Agent	  */
	public void setRepresentante (String Representante);

	/** Get Agent	  */
	public String getRepresentante();

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
