/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Element
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Element 
{

    /** TableName=C_Element */
    public static final String Table_Name = "C_Element";

    /** AD_Table_ID=142 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AD_Tree_ID */
    public static final String COLUMNNAME_AD_Tree_ID = "AD_Tree_ID";

	/** Set Tree.
	  * Identifies a Tree
	  */
	public void setAD_Tree_ID (int AD_Tree_ID);

	/** Get Tree.
	  * Identifies a Tree
	  */
	public int getAD_Tree_ID();

	public I_AD_Tree getAD_Tree() throws RuntimeException;

    /** Column name C_Element_ID */
    public static final String COLUMNNAME_C_Element_ID = "C_Element_ID";

	/** Set Element.
	  * Accounting Element
	  */
	public void setC_Element_ID (int C_Element_ID);

	/** Get Element.
	  * Accounting Element
	  */
	public int getC_Element_ID();

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

    /** Column name ElementType */
    public static final String COLUMNNAME_ElementType = "ElementType";

	/** Set Type.
	  * Element Type (account or user defined)
	  */
	public void setElementType (String ElementType);

	/** Get Type.
	  * Element Type (account or user defined)
	  */
	public String getElementType();

    /** Column name IsBalancing */
    public static final String COLUMNNAME_IsBalancing = "IsBalancing";

	/** Set Balancing.
	  * All transactions within an element value must balance (e.g. cost centers)
	  */
	public void setIsBalancing (boolean IsBalancing);

	/** Get Balancing.
	  * All transactions within an element value must balance (e.g. cost centers)
	  */
	public boolean isBalancing();

    /** Column name IsNaturalAccount */
    public static final String COLUMNNAME_IsNaturalAccount = "IsNaturalAccount";

	/** Set Natural Account.
	  * The primary natural account
	  */
	public void setIsNaturalAccount (boolean IsNaturalAccount);

	/** Get Natural Account.
	  * The primary natural account
	  */
	public boolean isNaturalAccount();

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

    /** Column name VFormat */
    public static final String COLUMNNAME_VFormat = "VFormat";

	/** Set Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public void setVFormat (String VFormat);

	/** Get Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public String getVFormat();
}
