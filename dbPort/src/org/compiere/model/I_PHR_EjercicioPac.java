/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PHR_EjercicioPac
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PHR_EjercicioPac 
{

    /** TableName=PHR_EjercicioPac */
    public static final String Table_Name = "PHR_EjercicioPac";

    /** AD_Table_ID=1201008 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name Comentario */
    public static final String COLUMNNAME_Comentario = "Comentario";

	/** Set Comment	  */
	public void setComentario (String Comentario);

	/** Get Comment	  */
	public String getComentario();

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (int Duracion);

	/** Get Duration.
	  * Duration
	  */
	public int getDuracion();

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

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaIni */
    public static final String COLUMNNAME_FechaIni = "FechaIni";

	/** Set Initial Date.
	  * Initial Date
	  */
	public void setFechaIni (Timestamp FechaIni);

	/** Get Initial Date.
	  * Initial Date
	  */
	public Timestamp getFechaIni();

    /** Column name FrecEjerc */
    public static final String COLUMNNAME_FrecEjerc = "FrecEjerc";

	/** Set Exercise Frequency.
	  * Exercise Frequency
	  */
	public void setFrecEjerc (String FrecEjerc);

	/** Get Exercise Frequency.
	  * Exercise Frequency
	  */
	public String getFrecEjerc();

    /** Column name Itervalo */
    public static final String COLUMNNAME_Itervalo = "Itervalo";

	/** Set Itervalo.
	  * Itervalo
	  */
	public void setItervalo (int Itervalo);

	/** Get Itervalo.
	  * Itervalo
	  */
	public int getItervalo();

    /** Column name Nombre */
    public static final String COLUMNNAME_Nombre = "Nombre";

	/** Set Name.
	  * Name of friend
	  */
	public void setNombre (String Nombre);

	/** Get Name.
	  * Name of friend
	  */
	public String getNombre();

    /** Column name PHR_EjercicioPac_ID */
    public static final String COLUMNNAME_PHR_EjercicioPac_ID = "PHR_EjercicioPac_ID";

	/** Set Patient Exercise.
	  * Patient Exercise
	  */
	public void setPHR_EjercicioPac_ID (int PHR_EjercicioPac_ID);

	/** Get Patient Exercise.
	  * Patient Exercise
	  */
	public int getPHR_EjercicioPac_ID();
}
