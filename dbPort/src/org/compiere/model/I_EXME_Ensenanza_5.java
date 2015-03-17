/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Ensenanza_5
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Ensenanza_5 
{

    /** TableName=EXME_Ensenanza_5 */
    public static final String Table_Name = "EXME_Ensenanza_5";

    /** AD_Table_ID=1000219 */
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

    /** Column name Costo */
    public static final String COLUMNNAME_Costo = "Costo";

	/** Set Cost.
	  * Cost
	  */
	public void setCosto (boolean Costo);

	/** Get Cost.
	  * Cost
	  */
	public boolean isCosto();

    /** Column name Costo_Curso */
    public static final String COLUMNNAME_Costo_Curso = "Costo_Curso";

	/** Set Cost of the Course.
	  * Cost of the Course
	  */
	public void setCosto_Curso (BigDecimal Costo_Curso);

	/** Get Cost of the Course.
	  * Cost of the Course
	  */
	public BigDecimal getCosto_Curso();

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

    /** Column name EXME_Ensenanza_5_ID */
    public static final String COLUMNNAME_EXME_Ensenanza_5_ID = "EXME_Ensenanza_5_ID";

	/** Set Teaching Courses 5.
	  * Teaching Courses 5
	  */
	public void setEXME_Ensenanza_5_ID (int EXME_Ensenanza_5_ID);

	/** Get Teaching Courses 5.
	  * Teaching Courses 5
	  */
	public int getEXME_Ensenanza_5_ID();

    /** Column name Fecha_Curso */
    public static final String COLUMNNAME_Fecha_Curso = "Fecha_Curso";

	/** Set Course Date.
	  * Course Date
	  */
	public void setFecha_Curso (Timestamp Fecha_Curso);

	/** Get Course Date.
	  * Course Date
	  */
	public Timestamp getFecha_Curso();

    /** Column name Fecha_Registro */
    public static final String COLUMNNAME_Fecha_Registro = "Fecha_Registro";

	/** Set Date of Record.
	  * Date of Record
	  */
	public void setFecha_Registro (Timestamp Fecha_Registro);

	/** Get Date of Record.
	  * Date of Record
	  */
	public Timestamp getFecha_Registro();

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

    /** Column name Num_Alumnos */
    public static final String COLUMNNAME_Num_Alumnos = "Num_Alumnos";

	/** Set Number of Students.
	  * Number of Students
	  */
	public void setNum_Alumnos (int Num_Alumnos);

	/** Get Number of Students.
	  * Number of Students
	  */
	public int getNum_Alumnos();

    /** Column name Num_Horas */
    public static final String COLUMNNAME_Num_Horas = "Num_Horas";

	/** Set Number of Hours.
	  * Number of Hours
	  */
	public void setNum_Horas (String Num_Horas);

	/** Get Number of Hours.
	  * Number of Hours
	  */
	public String getNum_Horas();
}
