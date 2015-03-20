/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Operador
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Operador 
{

    /** TableName=EXME_Operador */
    public static final String Table_Name = "EXME_Operador";

    /** AD_Table_ID=1000025 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_CashBook_ID */
    public static final String COLUMNNAME_C_CashBook_ID = "C_CashBook_ID";

	/** Set Cash Register.
	  * Cash Register for recording payments transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID);

	/** Get Cash Register.
	  * Cash Register for recording payments transactions
	  */
	public int getC_CashBook_ID();

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

    /** Column name Estatus_Caja */
    public static final String COLUMNNAME_Estatus_Caja = "Estatus_Caja";

	/** Set Cash Book Status.
	  * Cash Book Status
	  */
	public void setEstatus_Caja (boolean Estatus_Caja);

	/** Get Cash Book Status.
	  * Cash Book Status
	  */
	public boolean isEstatus_Caja();

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

    /** Column name Tipo_Operador */
    public static final String COLUMNNAME_Tipo_Operador = "Tipo_Operador";

	/** Set Cashier Type.
	  * Cashier Type ('I', 'C', 'B')
	  */
	public void setTipo_Operador (String Tipo_Operador);

	/** Get Cashier Type.
	  * Cashier Type ('I', 'C', 'B')
	  */
	public String getTipo_Operador();
}
