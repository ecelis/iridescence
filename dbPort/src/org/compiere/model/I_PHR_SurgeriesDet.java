/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_SurgeriesDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_SurgeriesDet 
{

    /** TableName=PHR_SurgeriesDet */
    public static final String Table_Name = "PHR_SurgeriesDet";

    /** AD_Table_ID=1201011 */
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

    /** Column name PHR_SurgeriesDet_ID */
    public static final String COLUMNNAME_PHR_SurgeriesDet_ID = "PHR_SurgeriesDet_ID";

	/** Set Detail of Medical Procedures & Surgeries	  */
	public void setPHR_SurgeriesDet_ID (int PHR_SurgeriesDet_ID);

	/** Get Detail of Medical Procedures & Surgeries	  */
	public int getPHR_SurgeriesDet_ID();

    /** Column name PHR_Surgeries_ID */
    public static final String COLUMNNAME_PHR_Surgeries_ID = "PHR_Surgeries_ID";

	/** Set Medical Procedures & Surgeries	  */
	public void setPHR_Surgeries_ID (int PHR_Surgeries_ID);

	/** Get Medical Procedures & Surgeries	  */
	public int getPHR_Surgeries_ID();

	public I_PHR_Surgeries getPHR_Surgeries() throws RuntimeException;
}
