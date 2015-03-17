/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigFE
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigFE 
{

    /** TableName=EXME_ConfigFE */
    public static final String Table_Name = "EXME_ConfigFE";

    /** AD_Table_ID=1200292 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public I_AD_Process getAD_Process() throws RuntimeException;

    /** Column name CaracPermitidos */
    public static final String COLUMNNAME_CaracPermitidos = "CaracPermitidos";

	/** Set Characters Permitted	  */
	public void setCaracPermitidos (String CaracPermitidos);

	/** Get Characters Permitted	  */
	public String getCaracPermitidos();

    /** Column name EXME_ConfigFE_ID */
    public static final String COLUMNNAME_EXME_ConfigFE_ID = "EXME_ConfigFE_ID";

	/** Set Configuration of Electronic Invoice	  */
	public void setEXME_ConfigFE_ID (int EXME_ConfigFE_ID);

	/** Get Configuration of Electronic Invoice	  */
	public int getEXME_ConfigFE_ID();

    /** Column name IsInProduction */
    public static final String COLUMNNAME_IsInProduction = "IsInProduction";

	/** Set In Production.
	  * The system is in production
	  */
	public void setIsInProduction (boolean IsInProduction);

	/** Get In Production.
	  * The system is in production
	  */
	public boolean isInProduction();

    /** Column name Leyenda1 */
    public static final String COLUMNNAME_Leyenda1 = "Leyenda1";

	/** Set Leyend1	  */
	public void setLeyenda1 (String Leyenda1);

	/** Get Leyend1	  */
	public String getLeyenda1();

    /** Column name Leyenda2 */
    public static final String COLUMNNAME_Leyenda2 = "Leyenda2";

	/** Set Leyend2	  */
	public void setLeyenda2 (String Leyenda2);

	/** Get Leyend2	  */
	public String getLeyenda2();

    /** Column name Leyenda3 */
    public static final String COLUMNNAME_Leyenda3 = "Leyenda3";

	/** Set Leyend3	  */
	public void setLeyenda3 (String Leyenda3);

	/** Get Leyend3	  */
	public String getLeyenda3();

    /** Column name Leyenda4 */
    public static final String COLUMNNAME_Leyenda4 = "Leyenda4";

	/** Set Leyend4	  */
	public void setLeyenda4 (String Leyenda4);

	/** Get Leyend4	  */
	public String getLeyenda4();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name PasswordPK */
    public static final String COLUMNNAME_PasswordPK = "PasswordPK";

	/** Set Password Private Key.
	  * Password Private Key
	  */
	public void setPasswordPK (String PasswordPK);

	/** Get Password Private Key.
	  * Password Private Key
	  */
	public String getPasswordPK();

    /** Column name PrintFacturaElect */
    public static final String COLUMNNAME_PrintFacturaElect = "PrintFacturaElect";

	/** Set Print Electronic Invoice	  */
	public void setPrintFacturaElect (boolean PrintFacturaElect);

	/** Get Print Electronic Invoice	  */
	public boolean isPrintFacturaElect();

    /** Column name RFC_Extranjero */
    public static final String COLUMNNAME_RFC_Extranjero = "RFC_Extranjero";

	/** Set RFC Foreigner	  */
	public void setRFC_Extranjero (String RFC_Extranjero);

	/** Get RFC Foreigner	  */
	public String getRFC_Extranjero();

    /** Column name RFC_Nacional */
    public static final String COLUMNNAME_RFC_Nacional = "RFC_Nacional";

	/** Set National RFC	  */
	public void setRFC_Nacional (String RFC_Nacional);

	/** Get National RFC	  */
	public String getRFC_Nacional();

    /** Column name SerieDocFac */
    public static final String COLUMNNAME_SerieDocFac = "SerieDocFac";

	/** Set Serial Invoice Document	  */
	public void setSerieDocFac (String SerieDocFac);

	/** Get Serial Invoice Document	  */
	public String getSerieDocFac();

    /** Column name SerieDocNC */
    public static final String COLUMNNAME_SerieDocNC = "SerieDocNC";

	/** Set Serial of Credit Note	  */
	public void setSerieDocNC (String SerieDocNC);

	/** Get Serial of Credit Note	  */
	public String getSerieDocNC();

    /** Column name SerieDocND */
    public static final String COLUMNNAME_SerieDocND = "SerieDocND";

	/** Set Serial of Debit Note	  */
	public void setSerieDocND (String SerieDocND);

	/** Get Serial of Debit Note	  */
	public String getSerieDocND();

    /** Column name StampInvoice */
    public static final String COLUMNNAME_StampInvoice = "StampInvoice";

	/** Set Stamp Invoice	  */
	public void setStampInvoice (boolean StampInvoice);

	/** Get Stamp Invoice	  */
	public boolean isStampInvoice();

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set Registered EMail.
	  * Email of the responsible for the System
	  */
	public void setUserName (String UserName);

	/** Get Registered EMail.
	  * Email of the responsible for the System
	  */
	public String getUserName();
}
