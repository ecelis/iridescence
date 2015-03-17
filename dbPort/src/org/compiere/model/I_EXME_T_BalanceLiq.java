/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_T_BalanceLiq
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_T_BalanceLiq 
{

    /** TableName=EXME_T_BalanceLiq */
    public static final String Table_Name = "EXME_T_BalanceLiq";

    /** AD_Table_ID=1200640 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name Dia */
    public static final String COLUMNNAME_Dia = "Dia";

	/** Set Day	  */
	public void setDia (int Dia);

	/** Get Day	  */
	public int getDia();

    /** Column name EXME_T_BalanceLiq_ID */
    public static final String COLUMNNAME_EXME_T_BalanceLiq_ID = "EXME_T_BalanceLiq_ID";

	/** Set Fluid Balance	  */
	public void setEXME_T_BalanceLiq_ID (int EXME_T_BalanceLiq_ID);

	/** Get Fluid Balance	  */
	public int getEXME_T_BalanceLiq_ID();

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

    /** Column name FechaInicio */
    public static final String COLUMNNAME_FechaInicio = "FechaInicio";

	/** Set Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio);

	/** Get Beginning Date	  */
	public Timestamp getFechaInicio();
}
