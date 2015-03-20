/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_VacunaPacDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_VacunaPacDet 
{

    /** TableName=PHR_VacunaPacDet */
    public static final String Table_Name = "PHR_VacunaPacDet";

    /** AD_Table_ID=1200935 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name PHR_VacunaPacDet_ID */
    public static final String COLUMNNAME_PHR_VacunaPacDet_ID = "PHR_VacunaPacDet_ID";

	/** Set Vaccine Detail	  */
	public void setPHR_VacunaPacDet_ID (int PHR_VacunaPacDet_ID);

	/** Get Vaccine Detail	  */
	public int getPHR_VacunaPacDet_ID();

    /** Column name PHR_VacunaPac_ID */
    public static final String COLUMNNAME_PHR_VacunaPac_ID = "PHR_VacunaPac_ID";

	/** Set Vaccines received	  */
	public void setPHR_VacunaPac_ID (int PHR_VacunaPac_ID);

	/** Get Vaccines received	  */
	public int getPHR_VacunaPac_ID();

	public I_PHR_VacunaPac getPHR_VacunaPac() throws RuntimeException;
}
