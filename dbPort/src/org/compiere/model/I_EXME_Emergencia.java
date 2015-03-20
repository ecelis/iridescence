/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Emergencia
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Emergencia 
{

    /** TableName=EXME_Emergencia */
    public static final String Table_Name = "EXME_Emergencia";

    /** AD_Table_ID=1200848 */
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

    /** Column name EXME_Emergencia_ID */
    public static final String COLUMNNAME_EXME_Emergencia_ID = "EXME_Emergencia_ID";

	/** Set Emergency	  */
	public void setEXME_Emergencia_ID (int EXME_Emergencia_ID);

	/** Get Emergency	  */
	public int getEXME_Emergencia_ID();

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFecha_Ini();

    /** Column name IniciadoPor */
    public static final String COLUMNNAME_IniciadoPor = "IniciadoPor";

	/** Set Started By	  */
	public void setIniciadoPor (int IniciadoPor);

	/** Get Started By	  */
	public int getIniciadoPor();

    /** Column name TerminadoPor */
    public static final String COLUMNNAME_TerminadoPor = "TerminadoPor";

	/** Set Finished By	  */
	public void setTerminadoPor (int TerminadoPor);

	/** Get Finished By	  */
	public int getTerminadoPor();
}
