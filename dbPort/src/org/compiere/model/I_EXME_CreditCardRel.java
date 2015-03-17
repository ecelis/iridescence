/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CreditCardRel
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CreditCardRel 
{

    /** TableName=EXME_CreditCardRel */
    public static final String Table_Name = "EXME_CreditCardRel";

    /** AD_Table_ID=1200159 */
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

    /** Column name Commission */
    public static final String COLUMNNAME_Commission = "Commission";

	/** Set Commission %.
	  * Commission stated as a percentage
	  */
	public void setCommission (BigDecimal Commission);

	/** Get Commission %.
	  * Commission stated as a percentage
	  */
	public BigDecimal getCommission();

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

    /** Column name EXME_CreditCardRel_ID */
    public static final String COLUMNNAME_EXME_CreditCardRel_ID = "EXME_CreditCardRel_ID";

	/** Set CreditCardRel.
	  * Credit Card Relation
	  */
	public void setEXME_CreditCardRel_ID (int EXME_CreditCardRel_ID);

	/** Get CreditCardRel.
	  * Credit Card Relation
	  */
	public int getEXME_CreditCardRel_ID();

    /** Column name EXME_FormaPago_ID */
    public static final String COLUMNNAME_EXME_FormaPago_ID = "EXME_FormaPago_ID";

	/** Set Payment Form.
	  * Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID);

	/** Get Payment Form.
	  * Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID();

    /** Column name IVA */
    public static final String COLUMNNAME_IVA = "IVA";

	/** Set Tax Commission %.
	  * Tax Commission %
	  */
	public void setIVA (BigDecimal IVA);

	/** Get Tax Commission %.
	  * Tax Commission %
	  */
	public BigDecimal getIVA();

    /** Column name IVAComision */
    public static final String COLUMNNAME_IVAComision = "IVAComision";

	/** Set Tax Comission.
	  * Tax Comission
	  */
	public void setIVAComision (String IVAComision);

	/** Get Tax Comission.
	  * Tax Comission
	  */
	public String getIVAComision();

    /** Column name MontoComision */
    public static final String COLUMNNAME_MontoComision = "MontoComision";

	/** Set Commission Amount.
	  * Commission Amount
	  */
	public void setMontoComision (String MontoComision);

	/** Get Commission Amount.
	  * Commission Amount
	  */
	public String getMontoComision();

    /** Column name MontoOperacion */
    public static final String COLUMNNAME_MontoOperacion = "MontoOperacion";

	/** Set Operation Amount.
	  * Operation Amount
	  */
	public void setMontoOperacion (String MontoOperacion);

	/** Get Operation Amount.
	  * Operation Amount
	  */
	public String getMontoOperacion();
}
