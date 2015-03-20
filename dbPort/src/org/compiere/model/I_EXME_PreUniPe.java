/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PreUniPe
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PreUniPe 
{

    /** TableName=EXME_PreUniPe */
    public static final String Table_Name = "EXME_PreUniPe";

    /** AD_Table_ID=1200409 */
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

    /** Column name EXME_PreUni_ID */
    public static final String COLUMNNAME_EXME_PreUni_ID = "EXME_PreUni_ID";

	/** Set Budget unit.
	  * Is the identifier of each budget request by administrative unit
	  */
	public void setEXME_PreUni_ID (int EXME_PreUni_ID);

	/** Get Budget unit.
	  * Is the identifier of each budget request by administrative unit
	  */
	public int getEXME_PreUni_ID();

	public I_EXME_PreUni getEXME_PreUni() throws RuntimeException;

    /** Column name EXME_PreUniPe_ID */
    public static final String COLUMNNAME_EXME_PreUniPe_ID = "EXME_PreUniPe_ID";

	/** Set Budget Unit detail.
	  * Is the identifier of each detail of the budget request by administrative unit
	  */
	public void setEXME_PreUniPe_ID (int EXME_PreUniPe_ID);

	/** Get Budget Unit detail.
	  * Is the identifier of each detail of the budget request by administrative unit
	  */
	public int getEXME_PreUniPe_ID();

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

    /** Column name Periodo */
    public static final String COLUMNNAME_Periodo = "Periodo";

	/** Set Period.
	  * Period
	  */
	public void setPeriodo (int Periodo);

	/** Get Period.
	  * Period
	  */
	public int getPeriodo();

    /** Column name Pre_Autorizado */
    public static final String COLUMNNAME_Pre_Autorizado = "Pre_Autorizado";

	/** Set Authorized .
	  * The amount authorized for the budget
	  */
	public void setPre_Autorizado (BigDecimal Pre_Autorizado);

	/** Get Authorized .
	  * The amount authorized for the budget
	  */
	public BigDecimal getPre_Autorizado();

    /** Column name Pre_Comprometido */
    public static final String COLUMNNAME_Pre_Comprometido = "Pre_Comprometido";

	/** Set Committed.
	  * Committed is the amount of the budget 
	  */
	public void setPre_Comprometido (BigDecimal Pre_Comprometido);

	/** Get Committed.
	  * Committed is the amount of the budget 
	  */
	public BigDecimal getPre_Comprometido();

    /** Column name Pre_Devengado */
    public static final String COLUMNNAME_Pre_Devengado = "Pre_Devengado";

	/** Set Earned.
	  * The amount of budget earned
	  */
	public void setPre_Devengado (BigDecimal Pre_Devengado);

	/** Get Earned.
	  * The amount of budget earned
	  */
	public BigDecimal getPre_Devengado();

    /** Column name Pre_Ejercido */
    public static final String COLUMNNAME_Pre_Ejercido = "Pre_Ejercido";

	/** Set Exercised.
	  * The amount of the budget Exercised
	  */
	public void setPre_Ejercido (BigDecimal Pre_Ejercido);

	/** Get Exercised.
	  * The amount of the budget Exercised
	  */
	public BigDecimal getPre_Ejercido();

    /** Column name Pre_Solicitado */
    public static final String COLUMNNAME_Pre_Solicitado = "Pre_Solicitado";

	/** Set Requested.
	  * Requested is the amount of the budget
	  */
	public void setPre_Solicitado (BigDecimal Pre_Solicitado);

	/** Get Requested.
	  * Requested is the amount of the budget
	  */
	public BigDecimal getPre_Solicitado();
}
