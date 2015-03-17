/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Periodo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Periodo 
{

    /** TableName=EXME_Periodo */
    public static final String Table_Name = "EXME_Periodo";

    /** AD_Table_ID=1200551 */
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

    /** Column name EsActual */
    public static final String COLUMNNAME_EsActual = "EsActual";

	/** Set IsActual	  */
	public void setEsActual (boolean EsActual);

	/** Get IsActual	  */
	public boolean isEsActual();

    /** Column name EsCerrada */
    public static final String COLUMNNAME_EsCerrada = "EsCerrada";

	/** Set IsClosed	  */
	public void setEsCerrada (boolean EsCerrada);

	/** Get IsClosed	  */
	public boolean isEsCerrada();

    /** Column name EsOrdinaria */
    public static final String COLUMNNAME_EsOrdinaria = "EsOrdinaria";

	/** Set IsOrdinary	  */
	public void setEsOrdinaria (boolean EsOrdinaria);

	/** Get IsOrdinary	  */
	public boolean isEsOrdinaria();

    /** Column name EXME_Periodo_ID */
    public static final String COLUMNNAME_EXME_Periodo_ID = "EXME_Periodo_ID";

	/** Set Period	  */
	public void setEXME_Periodo_ID (int EXME_Periodo_ID);

	/** Get Period	  */
	public int getEXME_Periodo_ID();

    /** Column name FechaCierre */
    public static final String COLUMNNAME_FechaCierre = "FechaCierre";

	/** Set Closing Date.
	  * Date of Intervention Closing
	  */
	public void setFechaCierre (Timestamp FechaCierre);

	/** Get Closing Date.
	  * Date of Intervention Closing
	  */
	public Timestamp getFechaCierre();

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

    /** Column name Fecha_Ini */
    public static final String COLUMNNAME_Fecha_Ini = "Fecha_Ini";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFecha_Ini();

    /** Column name Fecha_Pago */
    public static final String COLUMNNAME_Fecha_Pago = "Fecha_Pago";

	/** Set Paiment Date	  */
	public void setFecha_Pago (Timestamp Fecha_Pago);

	/** Get Paiment Date	  */
	public Timestamp getFecha_Pago();

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

    /** Column name No_Mes */
    public static final String COLUMNNAME_No_Mes = "No_Mes";

	/** Set Month Number	  */
	public void setNo_Mes (int No_Mes);

	/** Get Month Number	  */
	public int getNo_Mes();

    /** Column name No_Periodo */
    public static final String COLUMNNAME_No_Periodo = "No_Periodo";

	/** Set Period Number	  */
	public void setNo_Periodo (int No_Periodo);

	/** Get Period Number	  */
	public int getNo_Periodo();

    /** Column name No_Quincena */
    public static final String COLUMNNAME_No_Quincena = "No_Quincena";

	/** Set Fortnight Number	  */
	public void setNo_Quincena (int No_Quincena);

	/** Get Fortnight Number	  */
	public int getNo_Quincena();

    /** Column name No_Semana */
    public static final String COLUMNNAME_No_Semana = "No_Semana";

	/** Set Week Number	  */
	public void setNo_Semana (int No_Semana);

	/** Get Week Number	  */
	public int getNo_Semana();

    /** Column name TipoNomina */
    public static final String COLUMNNAME_TipoNomina = "TipoNomina";

	/** Set Type Paiment	  */
	public void setTipoNomina (boolean TipoNomina);

	/** Get Type Paiment	  */
	public boolean isTipoNomina();

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
