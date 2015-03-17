/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfiguradorDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfiguradorDet 
{

    /** TableName=EXME_ConfiguradorDet */
    public static final String Table_Name = "EXME_ConfiguradorDet";

    /** AD_Table_ID=1200899 */
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

    /** Column name Classname */
    public static final String COLUMNNAME_Classname = "Classname";

	/** Set Classname.
	  * Java Classname
	  */
	public void setClassname (String Classname);

	/** Get Classname.
	  * Java Classname
	  */
	public String getClassname();

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

    /** Column name EXME_ConfiguradorDet_ID */
    public static final String COLUMNNAME_EXME_ConfiguradorDet_ID = "EXME_ConfiguradorDet_ID";

	/** Set Configurator Detail	  */
	public void setEXME_ConfiguradorDet_ID (int EXME_ConfiguradorDet_ID);

	/** Get Configurator Detail	  */
	public int getEXME_ConfiguradorDet_ID();

    /** Column name EXME_Configurador_ID */
    public static final String COLUMNNAME_EXME_Configurador_ID = "EXME_Configurador_ID";

	/** Set Configurator	  */
	public void setEXME_Configurador_ID (int EXME_Configurador_ID);

	/** Get Configurator	  */
	public int getEXME_Configurador_ID();

	public I_EXME_Configurador getEXME_Configurador() throws RuntimeException;

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
