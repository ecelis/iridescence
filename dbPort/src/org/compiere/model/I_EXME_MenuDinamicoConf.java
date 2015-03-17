/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MenuDinamicoConf
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_MenuDinamicoConf 
{

    /** TableName=EXME_MenuDinamicoConf */
    public static final String Table_Name = "EXME_MenuDinamicoConf";

    /** AD_Table_ID=1201195 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

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

    /** Column name EXME_MenuDinamicoConf_ID */
    public static final String COLUMNNAME_EXME_MenuDinamicoConf_ID = "EXME_MenuDinamicoConf_ID";

	/** Set Graphic menu settings.
	  * Graphic menu settings
	  */
	public void setEXME_MenuDinamicoConf_ID (int EXME_MenuDinamicoConf_ID);

	/** Get Graphic menu settings.
	  * Graphic menu settings
	  */
	public int getEXME_MenuDinamicoConf_ID();

    /** Column name EXME_Menu_Dinamico_ID */
    public static final String COLUMNNAME_EXME_Menu_Dinamico_ID = "EXME_Menu_Dinamico_ID";

	/** Set Dynamic Menu	  */
	public void setEXME_Menu_Dinamico_ID (int EXME_Menu_Dinamico_ID);

	/** Get Dynamic Menu	  */
	public int getEXME_Menu_Dinamico_ID();

	public I_EXME_Menu_Dinamico getEXME_Menu_Dinamico() throws RuntimeException;

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

    /** Column name Visible */
    public static final String COLUMNNAME_Visible = "Visible";

	/** Set Visible	  */
	public void setVisible (boolean Visible);

	/** Get Visible	  */
	public boolean isVisible();
}
