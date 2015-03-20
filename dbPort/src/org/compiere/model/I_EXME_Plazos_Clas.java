/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Plazos_Clas
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Plazos_Clas 
{

    /** TableName=EXME_Plazos_Clas */
    public static final String Table_Name = "EXME_Plazos_Clas";

    /** AD_Table_ID=1000213 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Adeudo_Fin */
    public static final String COLUMNNAME_Adeudo_Fin = "Adeudo_Fin";

	/** Set Final Debt.
	  * Final Debt
	  */
	public void setAdeudo_Fin (BigDecimal Adeudo_Fin);

	/** Get Final Debt.
	  * Final Debt
	  */
	public BigDecimal getAdeudo_Fin();

    /** Column name Adeudo_Ini */
    public static final String COLUMNNAME_Adeudo_Ini = "Adeudo_Ini";

	/** Set Initial Debt.
	  * Initial Debt
	  */
	public void setAdeudo_Ini (BigDecimal Adeudo_Ini);

	/** Get Initial Debt.
	  * Initial Debt
	  */
	public BigDecimal getAdeudo_Ini();

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

    /** Column name Dias */
    public static final String COLUMNNAME_Dias = "Dias";

	/** Set Days	  */
	public void setDias (int Dias);

	/** Get Days	  */
	public int getDias();

    /** Column name EXME_ClasCliente_ID */
    public static final String COLUMNNAME_EXME_ClasCliente_ID = "EXME_ClasCliente_ID";

	/** Set Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID);

	/** Get Classification and Customer	  */
	public int getEXME_ClasCliente_ID();

	public I_EXME_ClasCliente getEXME_ClasCliente() throws RuntimeException;

    /** Column name EXME_Plazos_Clas_ID */
    public static final String COLUMNNAME_EXME_Plazos_Clas_ID = "EXME_Plazos_Clas_ID";

	/** Set Classified Deadlines.
	  * Classified Deadlines
	  */
	public void setEXME_Plazos_Clas_ID (int EXME_Plazos_Clas_ID);

	/** Get Classified Deadlines.
	  * Classified Deadlines
	  */
	public int getEXME_Plazos_Clas_ID();

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

    /** Column name Pagos */
    public static final String COLUMNNAME_Pagos = "Pagos";

	/** Set Payments.
	  * Payments
	  */
	public void setPagos (int Pagos);

	/** Get Payments.
	  * Payments
	  */
	public int getPagos();
}
