/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Calendario
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Calendario 
{

    /** TableName=EXME_Calendario */
    public static final String Table_Name = "EXME_Calendario";

    /** AD_Table_ID=1200360 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name Dia_Mes */
    public static final String COLUMNNAME_Dia_Mes = "Dia_Mes";

	/** Set Day of Month	  */
	public void setDia_Mes (int Dia_Mes);

	/** Get Day of Month	  */
	public int getDia_Mes();

    /** Column name Dia_Semana */
    public static final String COLUMNNAME_Dia_Semana = "Dia_Semana";

	/** Set Day Of  Week	  */
	public void setDia_Semana (int Dia_Semana);

	/** Get Day Of  Week	  */
	public int getDia_Semana();

    /** Column name EsFestivo */
    public static final String COLUMNNAME_EsFestivo = "EsFestivo";

	/** Set Holiday	  */
	public void setEsFestivo (boolean EsFestivo);

	/** Get Holiday	  */
	public boolean isEsFestivo();

    /** Column name EXME_Calendario_ID */
    public static final String COLUMNNAME_EXME_Calendario_ID = "EXME_Calendario_ID";

	/** Set Calendar	  */
	public void setEXME_Calendario_ID (int EXME_Calendario_ID);

	/** Get Calendar	  */
	public int getEXME_Calendario_ID();

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

    /** Column name Mes */
    public static final String COLUMNNAME_Mes = "Mes";

	/** Set Month	  */
	public void setMes (int Mes);

	/** Get Month	  */
	public int getMes();

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year.
	  * Calendar Year
	  */
	public void setYear (int Year);

	/** Get Year.
	  * Calendar Year
	  */
	public int getYear();
}
