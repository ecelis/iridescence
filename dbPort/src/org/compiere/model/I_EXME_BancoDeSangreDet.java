/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BancoDeSangreDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_BancoDeSangreDet 
{

    /** TableName=EXME_BancoDeSangreDet */
    public static final String Table_Name = "EXME_BancoDeSangreDet";

    /** AD_Table_ID=1200509 */
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

    /** Column name Analisis */
    public static final String COLUMNNAME_Analisis = "Analisis";

	/** Set Analysis.
	  * Analysis
	  */
	public void setAnalisis (String Analisis);

	/** Get Analysis.
	  * Analysis
	  */
	public String getAnalisis();

    /** Column name EXME_BancoDeSangreDet_ID */
    public static final String COLUMNNAME_EXME_BancoDeSangreDet_ID = "EXME_BancoDeSangreDet_ID";

	/** Set Blood Bank Detail.
	  * Blood Bank Detail
	  */
	public void setEXME_BancoDeSangreDet_ID (int EXME_BancoDeSangreDet_ID);

	/** Get Blood Bank Detail.
	  * Blood Bank Detail
	  */
	public int getEXME_BancoDeSangreDet_ID();

    /** Column name EXME_BancoDeSangreH_ID */
    public static final String COLUMNNAME_EXME_BancoDeSangreH_ID = "EXME_BancoDeSangreH_ID";

	/** Set Blood Bank.
	  * Blood Bank
	  */
	public void setEXME_BancoDeSangreH_ID (int EXME_BancoDeSangreH_ID);

	/** Get Blood Bank.
	  * Blood Bank
	  */
	public int getEXME_BancoDeSangreH_ID();

	public I_EXME_BancoDeSangreH getEXME_BancoDeSangreH() throws RuntimeException;

    /** Column name ResultadoAnalisis */
    public static final String COLUMNNAME_ResultadoAnalisis = "ResultadoAnalisis";

	/** Set Result of the Analysis.
	  * Result of the Analysis
	  */
	public void setResultadoAnalisis (String ResultadoAnalisis);

	/** Get Result of the Analysis.
	  * Result of the Analysis
	  */
	public String getResultadoAnalisis();
}
