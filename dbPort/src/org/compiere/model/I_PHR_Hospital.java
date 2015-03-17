/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_Hospital
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_Hospital 
{

    /** TableName=PHR_Hospital */
    public static final String Table_Name = "PHR_Hospital";

    /** AD_Table_ID=1200922 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 9 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(9);

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name EsPrincipal */
    public static final String COLUMNNAME_EsPrincipal = "EsPrincipal";

	/** Set Primary physician.
	  * Primary physician
	  */
	public void setEsPrincipal (boolean EsPrincipal);

	/** Get Primary physician.
	  * Primary physician
	  */
	public boolean isEsPrincipal();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

    /** Column name PHR_Hospital_ID */
    public static final String COLUMNNAME_PHR_Hospital_ID = "PHR_Hospital_ID";

	/** Set Patient Hospital	  */
	public void setPHR_Hospital_ID (int PHR_Hospital_ID);

	/** Get Patient Hospital	  */
	public int getPHR_Hospital_ID();

    /** Column name Telefono */
    public static final String COLUMNNAME_Telefono = "Telefono";

	/** Set Telephone.
	  * friend telephone
	  */
	public void setTelefono (String Telefono);

	/** Get Telephone.
	  * friend telephone
	  */
	public String getTelefono();
}
