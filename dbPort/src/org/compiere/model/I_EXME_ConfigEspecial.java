/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigEspecial
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigEspecial 
{

    /** TableName=EXME_ConfigEspecial */
    public static final String Table_Name = "EXME_ConfigEspecial";

    /** AD_Table_ID=1200085 */
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

    /** Column name AutorizaAseg */
    public static final String COLUMNNAME_AutorizaAseg = "AutorizaAseg";

	/** Set Require Insurer Authorization.
	  * Require Insurer Authorization
	  */
	public void setAutorizaAseg (boolean AutorizaAseg);

	/** Get Require Insurer Authorization.
	  * Require Insurer Authorization
	  */
	public boolean isAutorizaAseg();

    /** Column name CaptDatosLab */
    public static final String COLUMNNAME_CaptDatosLab = "CaptDatosLab";

	/** Set Capture Labor Data.
	  * Capture Labor Data
	  */
	public void setCaptDatosLab (boolean CaptDatosLab);

	/** Get Capture Labor Data.
	  * Capture Labor Data
	  */
	public boolean isCaptDatosLab();

    /** Column name CheckVoucher */
    public static final String COLUMNNAME_CheckVoucher = "CheckVoucher";

	/** Set Requires Check Insurer and Vouchers	  */
	public void setCheckVoucher (boolean CheckVoucher);

	/** Get Requires Check Insurer and Vouchers	  */
	public boolean isCheckVoucher();

    /** Column name EditValuePac */
    public static final String COLUMNNAME_EditValuePac = "EditValuePac";

	/** Set Edit Patient History	  */
	public void setEditValuePac (boolean EditValuePac);

	/** Get Edit Patient History	  */
	public boolean isEditValuePac();

    /** Column name EXME_ConfigEspecial_ID */
    public static final String COLUMNNAME_EXME_ConfigEspecial_ID = "EXME_ConfigEspecial_ID";

	/** Set Special Configuration.
	  * Special Configuration
	  */
	public void setEXME_ConfigEspecial_ID (int EXME_ConfigEspecial_ID);

	/** Get Special Configuration.
	  * Special Configuration
	  */
	public int getEXME_ConfigEspecial_ID();

    /** Column name FactAlmOrg */
    public static final String COLUMNNAME_FactAlmOrg = "FactAlmOrg";

	/** Set Invoice origin warehouse	  */
	public void setFactAlmOrg (boolean FactAlmOrg);

	/** Get Invoice origin warehouse	  */
	public boolean isFactAlmOrg();

    /** Column name FechaCorte */
    public static final String COLUMNNAME_FechaCorte = "FechaCorte";

	/** Set Cash Balance Date.
	  * Cash Balance Date
	  */
	public void setFechaCorte (Timestamp FechaCorte);

	/** Get Cash Balance Date.
	  * Cash Balance Date
	  */
	public Timestamp getFechaCorte();

    /** Column name IsDonativo */
    public static final String COLUMNNAME_IsDonativo = "IsDonativo";

	/** Set Donation	  */
	public void setIsDonativo (boolean IsDonativo);

	/** Get Donation	  */
	public boolean isDonativo();

    /** Column name IsPension */
    public static final String COLUMNNAME_IsPension = "IsPension";

	/** Set Pension	  */
	public void setIsPension (boolean IsPension);

	/** Get Pension	  */
	public boolean isPension();

    /** Column name LoginFact */
    public static final String COLUMNNAME_LoginFact = "LoginFact";

	/** Set Login invoice	  */
	public void setLoginFact (boolean LoginFact);

	/** Get Login invoice	  */
	public boolean isLoginFact();

    /** Column name PrintPreFac */
    public static final String COLUMNNAME_PrintPreFac = "PrintPreFac";

	/** Set Print Pre-Invoice	  */
	public void setPrintPreFac (boolean PrintPreFac);

	/** Get Print Pre-Invoice	  */
	public boolean isPrintPreFac();

    /** Column name PrintRecibo */
    public static final String COLUMNNAME_PrintRecibo = "PrintRecibo";

	/** Set Print receipt	  */
	public void setPrintRecibo (boolean PrintRecibo);

	/** Get Print receipt	  */
	public boolean isPrintRecibo();

    /** Column name RutasServ */
    public static final String COLUMNNAME_RutasServ = "RutasServ";

	/** Set RutasServ	  */
	public void setRutasServ (boolean RutasServ);

	/** Get RutasServ	  */
	public boolean isRutasServ();

    /** Column name Theme */
    public static final String COLUMNNAME_Theme = "Theme";

	/** Set Theme.
	  * Theme
	  */
	public void setTheme (String Theme);

	/** Get Theme.
	  * Theme
	  */
	public String getTheme();

    /** Column name TipoCliente */
    public static final String COLUMNNAME_TipoCliente = "TipoCliente";

	/** Set Client Type	  */
	public void setTipoCliente (boolean TipoCliente);

	/** Get Client Type	  */
	public boolean isTipoCliente();

    /** Column name VerInfMilitar */
    public static final String COLUMNNAME_VerInfMilitar = "VerInfMilitar";

	/** Set VerInfMilitar	  */
	public void setVerInfMilitar (boolean VerInfMilitar);

	/** Get VerInfMilitar	  */
	public boolean isVerInfMilitar();
}
