/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FolDigDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FolDigDet 
{

    /** TableName=EXME_FolDigDet */
    public static final String Table_Name = "EXME_FolDigDet";

    /** AD_Table_ID=1201362 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

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

    /** Column name EXME_FolDigDet_ID */
    public static final String COLUMNNAME_EXME_FolDigDet_ID = "EXME_FolDigDet_ID";

	/** Set Detail of Digital Folio ID	  */
	public void setEXME_FolDigDet_ID (int EXME_FolDigDet_ID);

	/** Get Detail of Digital Folio ID	  */
	public int getEXME_FolDigDet_ID();

    /** Column name EXME_FolDig_ID */
    public static final String COLUMNNAME_EXME_FolDig_ID = "EXME_FolDig_ID";

	/** Set Folios Assigned ID.
	  * Folios Assigned ID
	  */
	public void setEXME_FolDig_ID (int EXME_FolDig_ID);

	/** Get Folios Assigned ID.
	  * Folios Assigned ID
	  */
	public int getEXME_FolDig_ID();

    /** Column name FechaCorte */
    public static final String COLUMNNAME_FechaCorte = "FechaCorte";

	/** Set Cash Balance Date.
	  * Cash Balance Date
	  */
	public void setFechaCorte (Timestamp FechaCorte);

	/** Get Cash Balance Date.
	  * Cash Balance Date
	  */
	public Timestamp getFechaCorte();

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

    /** Column name Pagado */
    public static final String COLUMNNAME_Pagado = "Pagado";

	/** Set Paid	  */
	public void setPagado (boolean Pagado);

	/** Get Paid	  */
	public boolean isPagado();

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
