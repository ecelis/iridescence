/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Prescription
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Prescription 
{

    /** TableName=EXME_Prescription */
    public static final String Table_Name = "EXME_Prescription";

    /** AD_Table_ID=1200350 */
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

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name Dosificacion */
    public static final String COLUMNNAME_Dosificacion = "Dosificacion";

	/** Set Dosificacion	  */
	public void setDosificacion (String Dosificacion);

	/** Get Dosificacion	  */
	public String getDosificacion();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Prescription_ID */
    public static final String COLUMNNAME_EXME_Prescription_ID = "EXME_Prescription_ID";

	/** Set Prescription.
	  * Prescription
	  */
	public void setEXME_Prescription_ID (int EXME_Prescription_ID);

	/** Get Prescription.
	  * Prescription
	  */
	public int getEXME_Prescription_ID();

    /** Column name EXME_PrescRX_ID */
    public static final String COLUMNNAME_EXME_PrescRX_ID = "EXME_PrescRX_ID";

	/** Set RXNorm Prescription Identifier.
	  * RXNorm Prescription Identifier
	  */
	public void setEXME_PrescRX_ID (int EXME_PrescRX_ID);

	/** Get RXNorm Prescription Identifier.
	  * RXNorm Prescription Identifier
	  */
	public int getEXME_PrescRX_ID();

	public I_EXME_PrescRX getEXME_PrescRX() throws RuntimeException;

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

    /** Column name IsIntravenosa */
    public static final String COLUMNNAME_IsIntravenosa = "IsIntravenosa";

	/** Set Is intravenous	  */
	public void setIsIntravenosa (boolean IsIntravenosa);

	/** Get Is intravenous	  */
	public boolean isIntravenosa();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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

    /** Column name Ref_Prescription_ID */
    public static final String COLUMNNAME_Ref_Prescription_ID = "Ref_Prescription_ID";

	/** Set Prescription Reference	  */
	public void setRef_Prescription_ID (int Ref_Prescription_ID);

	/** Get Prescription Reference	  */
	public int getRef_Prescription_ID();
}
