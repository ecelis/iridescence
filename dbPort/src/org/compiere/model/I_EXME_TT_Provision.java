/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TT_Provision
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TT_Provision 
{

    /** TableName=EXME_TT_Provision */
    public static final String Table_Name = "EXME_TT_Provision";

    /** AD_Table_ID=1200307 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name Categoria */
    public static final String COLUMNNAME_Categoria = "Categoria";

	/** Set Category	  */
	public void setCategoria (String Categoria);

	/** Get Category	  */
	public String getCategoria();

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name Centro_Costo */
    public static final String COLUMNNAME_Centro_Costo = "Centro_Costo";

	/** Set Cost	  */
	public void setCentro_Costo (String Centro_Costo);

	/** Get Cost	  */
	public String getCentro_Costo();

    /** Column name Cliente */
    public static final String COLUMNNAME_Cliente = "Cliente";

	/** Set Customer.
	  * Name of Customer
	  */
	public void setCliente (String Cliente);

	/** Get Customer.
	  * Name of Customer
	  */
	public String getCliente();

    /** Column name Credito */
    public static final String COLUMNNAME_Credito = "Credito";

	/** Set Credit	  */
	public void setCredito (BigDecimal Credito);

	/** Get Credit	  */
	public BigDecimal getCredito();

    /** Column name Cta_Cont */
    public static final String COLUMNNAME_Cta_Cont = "Cta_Cont";

	/** Set Cta_Cont	  */
	public void setCta_Cont (String Cta_Cont);

	/** Get Cta_Cont	  */
	public String getCta_Cont();

    /** Column name Cta_Cont_ID */
    public static final String COLUMNNAME_Cta_Cont_ID = "Cta_Cont_ID";

	/** Set Cta_Cont_ID	  */
	public void setCta_Cont_ID (int Cta_Cont_ID);

	/** Get Cta_Cont_ID	  */
	public int getCta_Cont_ID();

    /** Column name Debito */
    public static final String COLUMNNAME_Debito = "Debito";

	/** Set Debit	  */
	public void setDebito (BigDecimal Debito);

	/** Get Debit	  */
	public BigDecimal getDebito();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EXME_TT_Provision_ID */
    public static final String COLUMNNAME_EXME_TT_Provision_ID = "EXME_TT_Provision_ID";

	/** Set Provision	  */
	public void setEXME_TT_Provision_ID (int EXME_TT_Provision_ID);

	/** Get Provision	  */
	public int getEXME_TT_Provision_ID();

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();
}
