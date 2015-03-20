/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for R_Group
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_R_Group 
{

    /** TableName=R_Group */
    public static final String Table_Name = "R_Group";

    /** AD_Table_ID=773 */
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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name M_BOM_ID */
    public static final String COLUMNNAME_M_BOM_ID = "M_BOM_ID";

	/** Set BOM.
	  * Bill of Material
	  */
	public void setM_BOM_ID (int M_BOM_ID);

	/** Get BOM.
	  * Bill of Material
	  */
	public int getM_BOM_ID();

	public I_M_BOM getM_BOM() throws RuntimeException;

    /** Column name M_ChangeNotice_ID */
    public static final String COLUMNNAME_M_ChangeNotice_ID = "M_ChangeNotice_ID";

	/** Set Change Notice.
	  * Bill of Materials (Engineering) Change Notice (Version)
	  */
	public void setM_ChangeNotice_ID (int M_ChangeNotice_ID);

	/** Get Change Notice.
	  * Bill of Materials (Engineering) Change Notice (Version)
	  */
	public int getM_ChangeNotice_ID();

	public I_M_ChangeNotice getM_ChangeNotice() throws RuntimeException;

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

    /** Column name PP_Product_BOM_ID */
    public static final String COLUMNNAME_PP_Product_BOM_ID = "PP_Product_BOM_ID";

	/** Set BOM & Formula.
	  * BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID);

	/** Get BOM & Formula.
	  * BOM & Formula
	  */
	public int getPP_Product_BOM_ID();

	public I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException;

    /** Column name R_Group_ID */
    public static final String COLUMNNAME_R_Group_ID = "R_Group_ID";

	/** Set Group.
	  * Request Group
	  */
	public void setR_Group_ID (int R_Group_ID);

	/** Get Group.
	  * Request Group
	  */
	public int getR_Group_ID();
}
