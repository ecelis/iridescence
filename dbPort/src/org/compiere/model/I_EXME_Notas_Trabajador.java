/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Notas_Trabajador
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Notas_Trabajador 
{

    /** TableName=EXME_Notas_Trabajador */
    public static final String Table_Name = "EXME_Notas_Trabajador";

    /** AD_Table_ID=1200022 */
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

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

    /** Column name EXME_ClasCliente_ID */
    public static final String COLUMNNAME_EXME_ClasCliente_ID = "EXME_ClasCliente_ID";

	/** Set Classification and Customer	  */
	public void setEXME_ClasCliente_ID (int EXME_ClasCliente_ID);

	/** Get Classification and Customer	  */
	public int getEXME_ClasCliente_ID();

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

    /** Column name EXME_Notas_Trabajador_ID */
    public static final String COLUMNNAME_EXME_Notas_Trabajador_ID = "EXME_Notas_Trabajador_ID";

	/** Set Notes of the Social Worker.
	  * Notes of the Social Worker
	  */
	public void setEXME_Notas_Trabajador_ID (int EXME_Notas_Trabajador_ID);

	/** Get Notes of the Social Worker.
	  * Notes of the Social Worker
	  */
	public int getEXME_Notas_Trabajador_ID();

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

    /** Column name Fecha_Nota */
    public static final String COLUMNNAME_Fecha_Nota = "Fecha_Nota";

	/** Set Note Date.
	  * Note Date
	  */
	public void setFecha_Nota (Timestamp Fecha_Nota);

	/** Get Note Date.
	  * Note Date
	  */
	public Timestamp getFecha_Nota();

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

    /** Column name Nota */
    public static final String COLUMNNAME_Nota = "Nota";

	/** Set Note.
	  * Note
	  */
	public void setNota (String Nota);

	/** Get Note.
	  * Note
	  */
	public String getNota();

    /** Column name Nota2 */
    public static final String COLUMNNAME_Nota2 = "Nota2";

	/** Set Note2	  */
	public void setNota2 (String Nota2);

	/** Get Note2	  */
	public String getNota2();

    /** Column name Nota3 */
    public static final String COLUMNNAME_Nota3 = "Nota3";

	/** Set Nota3	  */
	public void setNota3 (String Nota3);

	/** Get Nota3	  */
	public String getNota3();

    /** Column name TSSexo */
    public static final String COLUMNNAME_TSSexo = "TSSexo";

	/** Set Sex	  */
	public void setTSSexo (String TSSexo);

	/** Get Sex	  */
	public String getTSSexo();
}
