/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MovementLineB
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MovementLineB 
{

    /** TableName=EXME_MovementLineB */
    public static final String Table_Name = "EXME_MovementLineB";

    /** AD_Table_ID=1200604 */
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

    /** Column name AntEmbarazo */
    public static final String COLUMNNAME_AntEmbarazo = "AntEmbarazo";

	/** Set History of Pregnancy	  */
	public void setAntEmbarazo (boolean AntEmbarazo);

	/** Get History of Pregnancy	  */
	public boolean isAntEmbarazo();

    /** Column name AntIsoinmuMater */
    public static final String COLUMNNAME_AntIsoinmuMater = "AntIsoinmuMater";

	/** Set AntIsoinmuMater	  */
	public void setAntIsoinmuMater (boolean AntIsoinmuMater);

	/** Get AntIsoinmuMater	  */
	public boolean isAntIsoinmuMater();

    /** Column name Autotransfusion */
    public static final String COLUMNNAME_Autotransfusion = "Autotransfusion";

	/** Set Autotransfusion	  */
	public void setAutotransfusion (boolean Autotransfusion);

	/** Get Autotransfusion	  */
	public boolean isAutotransfusion();

    /** Column name C_UOM_Fib_ID */
    public static final String COLUMNNAME_C_UOM_Fib_ID = "C_UOM_Fib_ID";

	/** Set C UOM FIB ID	  */
	public void setC_UOM_Fib_ID (int C_UOM_Fib_ID);

	/** Get C UOM FIB ID	  */
	public int getC_UOM_Fib_ID();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name C_UOM_Neu_ID */
    public static final String COLUMNNAME_C_UOM_Neu_ID = "C_UOM_Neu_ID";

	/** Set C_UOM_Neu_ID	  */
	public void setC_UOM_Neu_ID (int C_UOM_Neu_ID);

	/** Get C_UOM_Neu_ID	  */
	public int getC_UOM_Neu_ID();

    /** Column name C_UOM_Pla_ID */
    public static final String COLUMNNAME_C_UOM_Pla_ID = "C_UOM_Pla_ID";

	/** Set UOM Platelets	  */
	public void setC_UOM_Pla_ID (int C_UOM_Pla_ID);

	/** Get UOM Platelets	  */
	public int getC_UOM_Pla_ID();

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_MovementLineB_ID */
    public static final String COLUMNNAME_EXME_MovementLineB_ID = "EXME_MovementLineB_ID";

	/** Set Movement Line Blood	  */
	public void setEXME_MovementLineB_ID (int EXME_MovementLineB_ID);

	/** Get Movement Line Blood	  */
	public int getEXME_MovementLineB_ID();

    /** Column name FechaSol */
    public static final String COLUMNNAME_FechaSol = "FechaSol";

	/** Set Date of application	  */
	public void setFechaSol (Timestamp FechaSol);

	/** Get Date of application	  */
	public Timestamp getFechaSol();

    /** Column name FechaUltimaTrans */
    public static final String COLUMNNAME_FechaUltimaTrans = "FechaUltimaTrans";

	/** Set Date of last transfusion	  */
	public void setFechaUltimaTrans (Timestamp FechaUltimaTrans);

	/** Get Date of last transfusion	  */
	public Timestamp getFechaUltimaTrans();

    /** Column name Fibrinogeno */
    public static final String COLUMNNAME_Fibrinogeno = "Fibrinogeno";

	/** Set Fibrinogen	  */
	public void setFibrinogeno (BigDecimal Fibrinogeno);

	/** Get Fibrinogen	  */
	public BigDecimal getFibrinogeno();

    /** Column name Hemoglobina */
    public static final String COLUMNNAME_Hemoglobina = "Hemoglobina";

	/** Set Hemoglobin	  */
	public void setHemoglobina (BigDecimal Hemoglobina);

	/** Get Hemoglobin	  */
	public BigDecimal getHemoglobina();

    /** Column name Indicaciones */
    public static final String COLUMNNAME_Indicaciones = "Indicaciones";

	/** Set Indications	  */
	public void setIndicaciones (String Indicaciones);

	/** Get Indications	  */
	public String getIndicaciones();

    /** Column name M_MovementLine_ID */
    public static final String COLUMNNAME_M_MovementLine_ID = "M_MovementLine_ID";

	/** Set Move Line.
	  * Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID);

	/** Get Move Line.
	  * Inventory Move document Line
	  */
	public int getM_MovementLine_ID();

	public I_M_MovementLine getM_MovementLine() throws RuntimeException;

    /** Column name Neutrofilos */
    public static final String COLUMNNAME_Neutrofilos = "Neutrofilos";

	/** Set Neutrophil	  */
	public void setNeutrofilos (BigDecimal Neutrofilos);

	/** Get Neutrophil	  */
	public BigDecimal getNeutrofilos();

    /** Column name Plaquetas */
    public static final String COLUMNNAME_Plaquetas = "Plaquetas";

	/** Set Platelets	  */
	public void setPlaquetas (BigDecimal Plaquetas);

	/** Get Platelets	  */
	public BigDecimal getPlaquetas();

    /** Column name Priority */
    public static final String COLUMNNAME_Priority = "Priority";

	/** Set Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (String Priority);

	/** Get Priority.
	  * Indicates if this request is of a high, medium or low priority.
	  */
	public String getPriority();

    /** Column name ReaccionTransPre */
    public static final String COLUMNNAME_ReaccionTransPre = "ReaccionTransPre";

	/** Set Previous transfusion reactions 	  */
	public void setReaccionTransPre (boolean ReaccionTransPre);

	/** Get Previous transfusion reactions 	  */
	public boolean isReaccionTransPre();

    /** Column name TipoReacTransPre */
    public static final String COLUMNNAME_TipoReacTransPre = "TipoReacTransPre";

	/** Set Type of previous transfusion reactions 	  */
	public void setTipoReacTransPre (String TipoReacTransPre);

	/** Get Type of previous transfusion reactions 	  */
	public String getTipoReacTransPre();

    /** Column name TipoSangre */
    public static final String COLUMNNAME_TipoSangre = "TipoSangre";

	/** Set Bloody Type	  */
	public void setTipoSangre (String TipoSangre);

	/** Get Bloody Type	  */
	public String getTipoSangre();

    /** Column name TP */
    public static final String COLUMNNAME_TP = "TP";

	/** Set TP	  */
	public void setTP (BigDecimal TP);

	/** Get TP	  */
	public BigDecimal getTP();

    /** Column name TransPrevias */
    public static final String COLUMNNAME_TransPrevias = "TransPrevias";

	/** Set Previous transfusions	  */
	public void setTransPrevias (boolean TransPrevias);

	/** Get Previous transfusions	  */
	public boolean isTransPrevias();

    /** Column name TTPA */
    public static final String COLUMNNAME_TTPA = "TTPA";

	/** Set TTPA	  */
	public void setTTPA (BigDecimal TTPA);

	/** Get TTPA	  */
	public BigDecimal getTTPA();

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
