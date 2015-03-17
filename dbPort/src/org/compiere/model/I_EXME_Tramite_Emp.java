/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Tramite_Emp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Tramite_Emp 
{

    /** TableName=EXME_Tramite_Emp */
    public static final String Table_Name = "EXME_Tramite_Emp";

    /** AD_Table_ID=1200572 */
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

    /** Column name EXME_Emp_ID */
    public static final String COLUMNNAME_EXME_Emp_ID = "EXME_Emp_ID";

	/** Set Employee.
	  * Employee
	  */
	public void setEXME_Emp_ID (int EXME_Emp_ID);

	/** Get Employee.
	  * Employee
	  */
	public int getEXME_Emp_ID();

    /** Column name EXME_TramiteDet_ID */
    public static final String COLUMNNAME_EXME_TramiteDet_ID = "EXME_TramiteDet_ID";

	/** Set Procedure Detail	  */
	public void setEXME_TramiteDet_ID (int EXME_TramiteDet_ID);

	/** Get Procedure Detail	  */
	public int getEXME_TramiteDet_ID();

    /** Column name EXME_Tramite_Emp_ID */
    public static final String COLUMNNAME_EXME_Tramite_Emp_ID = "EXME_Tramite_Emp_ID";

	/** Set Employee's Procedure	  */
	public void setEXME_Tramite_Emp_ID (int EXME_Tramite_Emp_ID);

	/** Get Employee's Procedure	  */
	public int getEXME_Tramite_Emp_ID();

    /** Column name EXME_TramiteHdr_ID */
    public static final String COLUMNNAME_EXME_TramiteHdr_ID = "EXME_TramiteHdr_ID";

	/** Set Procedures	  */
	public void setEXME_TramiteHdr_ID (int EXME_TramiteHdr_ID);

	/** Get Procedures	  */
	public int getEXME_TramiteHdr_ID();

	public I_EXME_TramiteHdr getEXME_TramiteHdr() throws RuntimeException;

    /** Column name Fecha_Entr */
    public static final String COLUMNNAME_Fecha_Entr = "Fecha_Entr";

	/** Set Delivery Date (social work).
	  * Delivery Date (social work)
	  */
	public void setFecha_Entr (Timestamp Fecha_Entr);

	/** Get Delivery Date (social work).
	  * Delivery Date (social work)
	  */
	public Timestamp getFecha_Entr();

    /** Column name Fecha_Sol */
    public static final String COLUMNNAME_Fecha_Sol = "Fecha_Sol";

	/** Set Request Date 	  */
	public void setFecha_Sol (Timestamp Fecha_Sol);

	/** Get Request Date 	  */
	public Timestamp getFecha_Sol();

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
