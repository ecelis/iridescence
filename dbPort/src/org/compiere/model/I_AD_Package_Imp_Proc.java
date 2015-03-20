/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Package_Imp_Proc
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Package_Imp_Proc 
{

    /** TableName=AD_Package_Imp_Proc */
    public static final String Table_Name = "AD_Package_Imp_Proc";

    /** AD_Table_ID=1200805 */
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

    /** Column name AD_Override_Dict */
    public static final String COLUMNNAME_AD_Override_Dict = "AD_Override_Dict";

	/** Set Update System Maintained Application Dictionary	  */
	public void setAD_Override_Dict (boolean AD_Override_Dict);

	/** Get Update System Maintained Application Dictionary	  */
	public boolean isAD_Override_Dict();

    /** Column name AD_Package_Dir */
    public static final String COLUMNNAME_AD_Package_Dir = "AD_Package_Dir";

	/** Set Package Directory.
	  * Package directory, default to AdempiereHome/packages
	  */
	public void setAD_Package_Dir (String AD_Package_Dir);

	/** Get Package Directory.
	  * Package directory, default to AdempiereHome/packages
	  */
	public String getAD_Package_Dir();

    /** Column name AD_Package_Imp_Proc_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Proc_ID = "AD_Package_Imp_Proc_ID";

	/** Set AD_Package_Imp_Proc_ID	  */
	public void setAD_Package_Imp_Proc_ID (int AD_Package_Imp_Proc_ID);

	/** Get AD_Package_Imp_Proc_ID	  */
	public int getAD_Package_Imp_Proc_ID();

    /** Column name AD_Package_Source */
    public static final String COLUMNNAME_AD_Package_Source = "AD_Package_Source";

	/** Set Package Source.
	  * Fully qualified package source file name
	  */
	public void setAD_Package_Source (String AD_Package_Source);

	/** Get Package Source.
	  * Fully qualified package source file name
	  */
	public String getAD_Package_Source();

    /** Column name AD_Package_Source_Type */
    public static final String COLUMNNAME_AD_Package_Source_Type = "AD_Package_Source_Type";

	/** Set Package Source Type.
	  * Type of package source - file, ftp, webservice etc
	  */
	public void setAD_Package_Source_Type (String AD_Package_Source_Type);

	/** Get Package Source Type.
	  * Type of package source - file, ftp, webservice etc
	  */
	public String getAD_Package_Source_Type();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
