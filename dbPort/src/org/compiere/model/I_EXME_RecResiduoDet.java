/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecResiduoDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecResiduoDet 
{

    /** TableName=EXME_RecResiduoDet */
    public static final String Table_Name = "EXME_RecResiduoDet";

    /** AD_Table_ID=1200825 */
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

    /** Column name EXME_Contenedor_ID */
    public static final String COLUMNNAME_EXME_Contenedor_ID = "EXME_Contenedor_ID";

	/** Set Container.
	  * Specifies that contains the hazardous biological and infectious waste
	  */
	public void setEXME_Contenedor_ID (int EXME_Contenedor_ID);

	/** Get Container.
	  * Specifies that contains the hazardous biological and infectious waste
	  */
	public int getEXME_Contenedor_ID();

	public I_EXME_Contenedor getEXME_Contenedor() throws RuntimeException;

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_RecResiduoDet_ID */
    public static final String COLUMNNAME_EXME_RecResiduoDet_ID = "EXME_RecResiduoDet_ID";

	/** Set Waste Collection Detail.
	  * Allows recording details of the collection made
	  */
	public void setEXME_RecResiduoDet_ID (int EXME_RecResiduoDet_ID);

	/** Get Waste Collection Detail.
	  * Allows recording details of the collection made
	  */
	public int getEXME_RecResiduoDet_ID();

    /** Column name EXME_RecResiduo_ID */
    public static final String COLUMNNAME_EXME_RecResiduo_ID = "EXME_RecResiduo_ID";

	/** Set Waste Collection.
	  * Lets keep track of collections that are made on such collection is made
	  */
	public void setEXME_RecResiduo_ID (int EXME_RecResiduo_ID);

	/** Get Waste Collection.
	  * Lets keep track of collections that are made on such collection is made
	  */
	public int getEXME_RecResiduo_ID();

	public I_EXME_RecResiduo getEXME_RecResiduo() throws RuntimeException;

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

    /** Column name EXME_SubEstacion_ID */
    public static final String COLUMNNAME_EXME_SubEstacion_ID = "EXME_SubEstacion_ID";

	/** Set Substation.
	  * Contains a catalog of substations, which belong to a service station
	  */
	public void setEXME_SubEstacion_ID (int EXME_SubEstacion_ID);

	/** Get Substation.
	  * Contains a catalog of substations, which belong to a service station
	  */
	public int getEXME_SubEstacion_ID();

	public I_EXME_SubEstacion getEXME_SubEstacion() throws RuntimeException;

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
