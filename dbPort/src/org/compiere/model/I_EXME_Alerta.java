/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Alerta
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Alerta 
{

    /** TableName=EXME_Alerta */
    public static final String Table_Name = "EXME_Alerta";

    /** AD_Table_ID=1201283 */
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

    /** Column name Altura */
    public static final String COLUMNNAME_Altura = "Altura";

	/** Set Height	  */
	public void setAltura (BigDecimal Altura);

	/** Get Height	  */
	public BigDecimal getAltura();

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

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (BigDecimal Edad);

	/** Get Age.
	  * Age
	  */
	public BigDecimal getEdad();

    /** Column name EXME_Alerta_ID */
    public static final String COLUMNNAME_EXME_Alerta_ID = "EXME_Alerta_ID";

	/** Set EXME_Alerta_ID	  */
	public void setEXME_Alerta_ID (int EXME_Alerta_ID);

	/** Get EXME_Alerta_ID	  */
	public int getEXME_Alerta_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (BigDecimal Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public BigDecimal getFrequency();

    /** Column name Frequency2 */
    public static final String COLUMNNAME_Frequency2 = "Frequency2";

	/** Set Frequency 2	  */
	public void setFrequency2 (BigDecimal Frequency2);

	/** Get Frequency 2	  */
	public BigDecimal getFrequency2();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name Operator */
    public static final String COLUMNNAME_Operator = "Operator";

	/** Set Operator	  */
	public void setOperator (String Operator);

	/** Get Operator	  */
	public String getOperator();

    /** Column name Operator2 */
    public static final String COLUMNNAME_Operator2 = "Operator2";

	/** Set Operator 2	  */
	public void setOperator2 (String Operator2);

	/** Get Operator 2	  */
	public String getOperator2();

    /** Column name Operator3 */
    public static final String COLUMNNAME_Operator3 = "Operator3";

	/** Set Operator 3	  */
	public void setOperator3 (String Operator3);

	/** Get Operator 3	  */
	public String getOperator3();

    /** Column name Operator4 */
    public static final String COLUMNNAME_Operator4 = "Operator4";

	/** Set Operator 4	  */
	public void setOperator4 (String Operator4);

	/** Get Operator 4	  */
	public String getOperator4();

    /** Column name Peso */
    public static final String COLUMNNAME_Peso = "Peso";

	/** Set Weight.
	  * Weight
	  */
	public void setPeso (BigDecimal Peso);

	/** Get Weight.
	  * Weight
	  */
	public BigDecimal getPeso();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
