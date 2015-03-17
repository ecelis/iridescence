/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PagoGen
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PagoGen 
{

    /** TableName=EXME_PagoGen */
    public static final String Table_Name = "EXME_PagoGen";

    /** AD_Table_ID=1200161 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name ActGeneral */
    public static final String COLUMNNAME_ActGeneral = "ActGeneral";

	/** Set General Activity	  */
	public void setActGeneral (String ActGeneral);

	/** Get General Activity	  */
	public String getActGeneral();

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

    /** Column name Anticipo */
    public static final String COLUMNNAME_Anticipo = "Anticipo";

	/** Set Advance payment	  */
	public void setAnticipo (String Anticipo);

	/** Get Advance payment	  */
	public String getAnticipo();

    /** Column name Devolucion */
    public static final String COLUMNNAME_Devolucion = "Devolucion";

	/** Set Return	  */
	public void setDevolucion (String Devolucion);

	/** Get Return	  */
	public String getDevolucion();

    /** Column name Efectivo */
    public static final String COLUMNNAME_Efectivo = "Efectivo";

	/** Set Cash	  */
	public void setEfectivo (String Efectivo);

	/** Get Cash	  */
	public String getEfectivo();

    /** Column name EXME_PagoGen_ID */
    public static final String COLUMNNAME_EXME_PagoGen_ID = "EXME_PagoGen_ID";

	/** Set Generic payments 	  */
	public void setEXME_PagoGen_ID (int EXME_PagoGen_ID);

	/** Get Generic payments 	  */
	public int getEXME_PagoGen_ID();

    /** Column name FechaLimite */
    public static final String COLUMNNAME_FechaLimite = "FechaLimite";

	/** Set Date limits 	  */
	public void setFechaLimite (Timestamp FechaLimite);

	/** Get Date limits 	  */
	public Timestamp getFechaLimite();

    /** Column name Pago */
    public static final String COLUMNNAME_Pago = "Pago";

	/** Set Payment	  */
	public void setPago (String Pago);

	/** Get Payment	  */
	public String getPago();
}
