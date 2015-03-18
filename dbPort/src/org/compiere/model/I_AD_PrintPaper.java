/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_PrintPaper
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_PrintPaper 
{

    /** TableName=AD_PrintPaper */
    public static final String Table_Name = "AD_PrintPaper";

    /** AD_Table_ID=492 */
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

    /** Column name AD_PrintPaper_ID */
    public static final String COLUMNNAME_AD_PrintPaper_ID = "AD_PrintPaper_ID";

	/** Set Print Paper.
	  * Printer paper definition
	  */
	public void setAD_PrintPaper_ID (int AD_PrintPaper_ID);

	/** Get Print Paper.
	  * Printer paper definition
	  */
	public int getAD_PrintPaper_ID();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

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

    /** Column name DimensionUnits */
    public static final String COLUMNNAME_DimensionUnits = "DimensionUnits";

	/** Set Dimension Units.
	  * Units of Dimension
	  */
	public void setDimensionUnits (String DimensionUnits);

	/** Get Dimension Units.
	  * Units of Dimension
	  */
	public String getDimensionUnits();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsLandscape */
    public static final String COLUMNNAME_IsLandscape = "IsLandscape";

	/** Set Landscape.
	  * Landscape orientation
	  */
	public void setIsLandscape (boolean IsLandscape);

	/** Get Landscape.
	  * Landscape orientation
	  */
	public boolean isLandscape();

    /** Column name MarginBottom */
    public static final String COLUMNNAME_MarginBottom = "MarginBottom";

	/** Set Bottom Margin.
	  * Bottom Space in 1/72 inch
	  */
	public void setMarginBottom (int MarginBottom);

	/** Get Bottom Margin.
	  * Bottom Space in 1/72 inch
	  */
	public int getMarginBottom();

    /** Column name MarginLeft */
    public static final String COLUMNNAME_MarginLeft = "MarginLeft";

	/** Set Left Margin.
	  * Left Space in 1/72 inch
	  */
	public void setMarginLeft (int MarginLeft);

	/** Get Left Margin.
	  * Left Space in 1/72 inch
	  */
	public int getMarginLeft();

    /** Column name MarginRight */
    public static final String COLUMNNAME_MarginRight = "MarginRight";

	/** Set Right Margin.
	  * Right Space in 1/72 inch
	  */
	public void setMarginRight (int MarginRight);

	/** Get Right Margin.
	  * Right Space in 1/72 inch
	  */
	public int getMarginRight();

    /** Column name MarginTop */
    public static final String COLUMNNAME_MarginTop = "MarginTop";

	/** Set Top Margin.
	  * Top Space in 1/72 inch
	  */
	public void setMarginTop (int MarginTop);

	/** Get Top Margin.
	  * Top Space in 1/72 inch
	  */
	public int getMarginTop();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name SizeX */
    public static final String COLUMNNAME_SizeX = "SizeX";

	/** Set Size X.
	  * X (horizontal) dimension size
	  */
	public void setSizeX (BigDecimal SizeX);

	/** Get Size X.
	  * X (horizontal) dimension size
	  */
	public BigDecimal getSizeX();

    /** Column name SizeY */
    public static final String COLUMNNAME_SizeY = "SizeY";

	/** Set Size Y.
	  * Y (vertical) dimension size
	  */
	public void setSizeY (BigDecimal SizeY);

	/** Get Size Y.
	  * Y (vertical) dimension size
	  */
	public BigDecimal getSizeY();
}