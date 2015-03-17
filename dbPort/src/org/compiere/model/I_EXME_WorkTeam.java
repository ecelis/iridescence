/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_WorkTeam
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_WorkTeam 
{

    /** TableName=EXME_WorkTeam */
    public static final String Table_Name = "EXME_WorkTeam";

    /** AD_Table_ID=1200329 */
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

    /** Column name EXME_ProgramaInv_ID */
    public static final String COLUMNNAME_EXME_ProgramaInv_ID = "EXME_ProgramaInv_ID";

	/** Set Research Program	  */
	public void setEXME_ProgramaInv_ID (int EXME_ProgramaInv_ID);

	/** Get Research Program	  */
	public int getEXME_ProgramaInv_ID();

	public I_EXME_ProgramaInv getEXME_ProgramaInv() throws RuntimeException;

    /** Column name EXME_ProyectoInv_ID */
    public static final String COLUMNNAME_EXME_ProyectoInv_ID = "EXME_ProyectoInv_ID";

	/** Set Research Project	  */
	public void setEXME_ProyectoInv_ID (int EXME_ProyectoInv_ID);

	/** Get Research Project	  */
	public int getEXME_ProyectoInv_ID();

	public I_EXME_ProyectoInv getEXME_ProyectoInv() throws RuntimeException;

    /** Column name EXME_TeamMember_ID */
    public static final String COLUMNNAME_EXME_TeamMember_ID = "EXME_TeamMember_ID";

	/** Set Team Member.
	  * Work Team Member
	  */
	public void setEXME_TeamMember_ID (int EXME_TeamMember_ID);

	/** Get Team Member.
	  * Work Team Member
	  */
	public int getEXME_TeamMember_ID();

	public I_EXME_TeamMember getEXME_TeamMember() throws RuntimeException;

    /** Column name EXME_WorkTeam_ID */
    public static final String COLUMNNAME_EXME_WorkTeam_ID = "EXME_WorkTeam_ID";

	/** Set Work Team	  */
	public void setEXME_WorkTeam_ID (int EXME_WorkTeam_ID);

	/** Get Work Team	  */
	public int getEXME_WorkTeam_ID();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();
}
