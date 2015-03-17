/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Ensenanza_8
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Ensenanza_8 
{

    /** TableName=EXME_Ensenanza_8 */
    public static final String Table_Name = "EXME_Ensenanza_8";

    /** AD_Table_ID=1000220 */
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

    /** Column name EXME_Ensenanza_8_ID */
    public static final String COLUMNNAME_EXME_Ensenanza_8_ID = "EXME_Ensenanza_8_ID";

	/** Set Teaching Courses 8.
	  * Teaching Courses 8
	  */
	public void setEXME_Ensenanza_8_ID (int EXME_Ensenanza_8_ID);

	/** Get Teaching Courses 8.
	  * Teaching Courses 8
	  */
	public int getEXME_Ensenanza_8_ID();

    /** Column name Fecha_Evento */
    public static final String COLUMNNAME_Fecha_Evento = "Fecha_Evento";

	/** Set Dates Event.
	  * Dates Event
	  */
	public void setFecha_Evento (Timestamp Fecha_Evento);

	/** Get Dates Event.
	  * Dates Event
	  */
	public Timestamp getFecha_Evento();

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

    /** Column name Num_Asistentes */
    public static final String COLUMNNAME_Num_Asistentes = "Num_Asistentes";

	/** Set Number Attending.
	  * Number Attending
	  */
	public void setNum_Asistentes (int Num_Asistentes);

	/** Get Number Attending.
	  * Number Attending
	  */
	public int getNum_Asistentes();

    /** Column name Tema */
    public static final String COLUMNNAME_Tema = "Tema";

	/** Set Theme.
	  * Theme
	  */
	public void setTema (String Tema);

	/** Get Theme.
	  * Theme
	  */
	public String getTema();
}
