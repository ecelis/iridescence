/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnviosPago
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnviosPago 
{

    /** TableName=EXME_EnviosPago */
    public static final String Table_Name = "EXME_EnviosPago";

    /** AD_Table_ID=1000221 */
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

    /** Column name ConcPago */
    public static final String COLUMNNAME_ConcPago = "ConcPago";

	/** Set Payment Concept.
	  * Payment Concept
	  */
	public void setConcPago (String ConcPago);

	/** Get Payment Concept.
	  * Payment Concept
	  */
	public String getConcPago();

    /** Column name Debe */
    public static final String COLUMNNAME_Debe = "Debe";

	/** Set Debit.
	  * Debit
	  */
	public void setDebe (BigDecimal Debe);

	/** Get Debit.
	  * Debit
	  */
	public BigDecimal getDebe();

    /** Column name EXME_EnviosPago_ID */
    public static final String COLUMNNAME_EXME_EnviosPago_ID = "EXME_EnviosPago_ID";

	/** Set Shipping Payment.
	  * Shipping Payment
	  */
	public void setEXME_EnviosPago_ID (int EXME_EnviosPago_ID);

	/** Get Shipping Payment.
	  * Shipping Payment
	  */
	public int getEXME_EnviosPago_ID();

    /** Column name EXME_Hist_Exp_ID */
    public static final String COLUMNNAME_EXME_Hist_Exp_ID = "EXME_Hist_Exp_ID";

	/** Set Medical File.
	  * Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID);

	/** Get Medical File.
	  * Medical File
	  */
	public int getEXME_Hist_Exp_ID();

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

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public String getIsPrinted();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name Paga */
    public static final String COLUMNNAME_Paga = "Paga";

	/** Set Pay.
	  * Pay
	  */
	public void setPaga (BigDecimal Paga);

	/** Get Pay.
	  * Pay
	  */
	public BigDecimal getPaga();

    /** Column name Pagado */
    public static final String COLUMNNAME_Pagado = "Pagado";

	/** Set Paid	  */
	public void setPagado (boolean Pagado);

	/** Get Paid	  */
	public boolean isPagado();

    /** Column name TS */
    public static final String COLUMNNAME_TS = "TS";

	/** Set Social Worker.
	  * Social Worker
	  */
	public void setTS (String TS);

	/** Get Social Worker.
	  * Social Worker
	  */
	public String getTS();

    /** Column name TSClasificacion */
    public static final String COLUMNNAME_TSClasificacion = "TSClasificacion";

	/** Set Social Work Clasification	  */
	public void setTSClasificacion (String TSClasificacion);

	/** Get Social Work Clasification	  */
	public String getTSClasificacion();
}
