/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VentaSuspendida
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_VentaSuspendida 
{

    /** TableName=EXME_VentaSuspendida */
    public static final String Table_Name = "EXME_VentaSuspendida";

    /** AD_Table_ID=1200200 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name EXME_Operador_ID */
    public static final String COLUMNNAME_EXME_Operador_ID = "EXME_Operador_ID";

	/** Set Operator.
	  * Operator
	  */
	public void setEXME_Operador_ID (int EXME_Operador_ID);

	/** Get Operator.
	  * Operator
	  */
	public int getEXME_Operador_ID();

	public I_EXME_Operador getEXME_Operador() throws RuntimeException;

    /** Column name EXME_RFC_ID */
    public static final String COLUMNNAME_EXME_RFC_ID = "EXME_RFC_ID";

	/** Set RFC	  */
	public void setEXME_RFC_ID (int EXME_RFC_ID);

	/** Get RFC	  */
	public int getEXME_RFC_ID();

	public I_EXME_RFC getEXME_RFC() throws RuntimeException;

    /** Column name EXME_VentaSuspendida_ID */
    public static final String COLUMNNAME_EXME_VentaSuspendida_ID = "EXME_VentaSuspendida_ID";

	/** Set Suspended Sale	  */
	public void setEXME_VentaSuspendida_ID (int EXME_VentaSuspendida_ID);

	/** Get Suspended Sale	  */
	public int getEXME_VentaSuspendida_ID();

    /** Column name isListaPreciosPublico */
    public static final String COLUMNNAME_isListaPreciosPublico = "isListaPreciosPublico";

	/** Set List Price is Public	  */
	public void setisListaPreciosPublico (boolean isListaPreciosPublico);

	/** Get List Price is Public	  */
	public boolean isListaPreciosPublico();

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
}
