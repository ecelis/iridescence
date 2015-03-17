/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GL_FundRestriction
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_GL_FundRestriction 
{

    /** TableName=GL_FundRestriction */
    public static final String Table_Name = "GL_FundRestriction";

    /** AD_Table_ID=824 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

	public I_C_ElementValue getC_ElementValue() throws RuntimeException;

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

    /** Column name GL_Fund_ID */
    public static final String COLUMNNAME_GL_Fund_ID = "GL_Fund_ID";

	/** Set GL Fund.
	  * General Ledger Funds Control
	  */
	public void setGL_Fund_ID (int GL_Fund_ID);

	/** Get GL Fund.
	  * General Ledger Funds Control
	  */
	public int getGL_Fund_ID();

	public I_GL_Fund getGL_Fund() throws RuntimeException;

    /** Column name GL_FundRestriction_ID */
    public static final String COLUMNNAME_GL_FundRestriction_ID = "GL_FundRestriction_ID";

	/** Set Fund Restriction.
	  * Restriction of Funds
	  */
	public void setGL_FundRestriction_ID (int GL_FundRestriction_ID);

	/** Get Fund Restriction.
	  * Restriction of Funds
	  */
	public int getGL_FundRestriction_ID();

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
