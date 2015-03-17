/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Cornea
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Cornea 
{

    /** TableName=EXME_Cornea */
    public static final String Table_Name = "EXME_Cornea";

    /** AD_Table_ID=1200269 */
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

    /** Column name ConteoCelular */
    public static final String COLUMNNAME_ConteoCelular = "ConteoCelular";

	/** Set Cell Counting	  */
	public void setConteoCelular (BigDecimal ConteoCelular);

	/** Get Cell Counting	  */
	public BigDecimal getConteoCelular();

    /** Column name DateReceived */
    public static final String COLUMNNAME_DateReceived = "DateReceived";

	/** Set Date received.
	  * Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived);

	/** Get Date received.
	  * Date a product was received
	  */
	public Timestamp getDateReceived();

    /** Column name DiasPreservacion */
    public static final String COLUMNNAME_DiasPreservacion = "DiasPreservacion";

	/** Set Preservation Days	  */
	public void setDiasPreservacion (BigDecimal DiasPreservacion);

	/** Get Preservation Days	  */
	public BigDecimal getDiasPreservacion();

    /** Column name EXME_BancoProcedencia_ID */
    public static final String COLUMNNAME_EXME_BancoProcedencia_ID = "EXME_BancoProcedencia_ID";

	/** Set Bank of Origin	  */
	public void setEXME_BancoProcedencia_ID (int EXME_BancoProcedencia_ID);

	/** Get Bank of Origin	  */
	public int getEXME_BancoProcedencia_ID();

	public I_EXME_BancoProcedencia getEXME_BancoProcedencia() throws RuntimeException;

    /** Column name EXME_Cornea_ID */
    public static final String COLUMNNAME_EXME_Cornea_ID = "EXME_Cornea_ID";

	/** Set Corneal.
	  * Corneal
	  */
	public void setEXME_Cornea_ID (int EXME_Cornea_ID);

	/** Get Corneal.
	  * Corneal
	  */
	public int getEXME_Cornea_ID();

    /** Column name EXME_Destino_ID */
    public static final String COLUMNNAME_EXME_Destino_ID = "EXME_Destino_ID";

	/** Set Destination	  */
	public void setEXME_Destino_ID (int EXME_Destino_ID);

	/** Get Destination	  */
	public int getEXME_Destino_ID();

	public I_EXME_Destino getEXME_Destino() throws RuntimeException;

    /** Column name Invoice */
    public static final String COLUMNNAME_Invoice = "Invoice";

	/** Set Invoice	  */
	public void setInvoice (String Invoice);

	/** Get Invoice	  */
	public String getInvoice();

    /** Column name MontoUSD */
    public static final String COLUMNNAME_MontoUSD = "MontoUSD";

	/** Set Dollar Amount	  */
	public void setMontoUSD (BigDecimal MontoUSD);

	/** Get Dollar Amount	  */
	public BigDecimal getMontoUSD();

    /** Column name NumCornea */
    public static final String COLUMNNAME_NumCornea = "NumCornea";

	/** Set Cornea Number	  */
	public void setNumCornea (String NumCornea);

	/** Get Cornea Number	  */
	public String getNumCornea();

    /** Column name TipoCornea */
    public static final String COLUMNNAME_TipoCornea = "TipoCornea";

	/** Set Cornea Type	  */
	public void setTipoCornea (String TipoCornea);

	/** Get Cornea Type	  */
	public String getTipoCornea();

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
