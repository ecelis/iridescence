/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ManifiestoDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ManifiestoDet 
{

    /** TableName=EXME_ManifiestoDet */
    public static final String Table_Name = "EXME_ManifiestoDet";

    /** AD_Table_ID=1200827 */
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

    /** Column name EXME_ManifiestoDet_ID */
    public static final String COLUMNNAME_EXME_ManifiestoDet_ID = "EXME_ManifiestoDet_ID";

	/** Set Manifesto Detail.
	  * Allows recording details of the vouchers issued by the collector
	  */
	public void setEXME_ManifiestoDet_ID (int EXME_ManifiestoDet_ID);

	/** Get Manifesto Detail.
	  * Allows recording details of the vouchers issued by the collector
	  */
	public int getEXME_ManifiestoDet_ID();

    /** Column name EXME_Manifiesto_ID */
    public static final String COLUMNNAME_EXME_Manifiesto_ID = "EXME_Manifiesto_ID";

	/** Set Manifest.
	  * It contains the vouchers issued by the garbage collector
	  */
	public void setEXME_Manifiesto_ID (int EXME_Manifiesto_ID);

	/** Get Manifest.
	  * It contains the vouchers issued by the garbage collector
	  */
	public int getEXME_Manifiesto_ID();

	public I_EXME_Manifiesto getEXME_Manifiesto() throws RuntimeException;

    /** Column name EXME_Residuo_ID */
    public static final String COLUMNNAME_EXME_Residuo_ID = "EXME_Residuo_ID";

	/** Set Residue.
	  * Specifies the types of biological / infectious waste
	  */
	public void setEXME_Residuo_ID (int EXME_Residuo_ID);

	/** Get Residue.
	  * Specifies the types of biological / infectious waste
	  */
	public int getEXME_Residuo_ID();

	public I_EXME_Residuo getEXME_Residuo() throws RuntimeException;

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();
}
