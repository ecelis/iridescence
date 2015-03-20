/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Acervo
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Acervo 
{

    /** TableName=EXME_Acervo */
    public static final String Table_Name = "EXME_Acervo";

    /** AD_Table_ID=1200478 */
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

    /** Column name EXME_Acervo_ID */
    public static final String COLUMNNAME_EXME_Acervo_ID = "EXME_Acervo_ID";

	/** Set Acquis	  */
	public void setEXME_Acervo_ID (int EXME_Acervo_ID);

	/** Get Acquis	  */
	public int getEXME_Acervo_ID();

    /** Column name EXME_ArchCli_ID */
    public static final String COLUMNNAME_EXME_ArchCli_ID = "EXME_ArchCli_ID";

	/** Set Clinical Archive.
	  * Clinical Archive
	  */
	public void setEXME_ArchCli_ID (int EXME_ArchCli_ID);

	/** Get Clinical Archive.
	  * Clinical Archive
	  */
	public int getEXME_ArchCli_ID();

	public I_EXME_ArchCli getEXME_ArchCli() throws RuntimeException;

    /** Column name EXME_ArchCliMov_ID */
    public static final String COLUMNNAME_EXME_ArchCliMov_ID = "EXME_ArchCliMov_ID";

	/** Set Movement of Expedient	  */
	public void setEXME_ArchCliMov_ID (int EXME_ArchCliMov_ID);

	/** Get Movement of Expedient	  */
	public int getEXME_ArchCliMov_ID();

	public I_EXME_ArchCliMov getEXME_ArchCliMov() throws RuntimeException;

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_TipoExped_ID */
    public static final String COLUMNNAME_EXME_TipoExped_ID = "EXME_TipoExped_ID";

	/** Set Type of Medical Record.
	  * Type of Medical Record
	  */
	public void setEXME_TipoExped_ID (int EXME_TipoExped_ID);

	/** Get Type of Medical Record.
	  * Type of Medical Record
	  */
	public int getEXME_TipoExped_ID();

	public I_EXME_TipoExped getEXME_TipoExped() throws RuntimeException;

    /** Column name Fecha_IngAcervo */
    public static final String COLUMNNAME_Fecha_IngAcervo = "Fecha_IngAcervo";

	/** Set Date Acquis Income 	  */
	public void setFecha_IngAcervo (Timestamp Fecha_IngAcervo);

	/** Get Date Acquis Income 	  */
	public Timestamp getFecha_IngAcervo();

    /** Column name Fecha_IngArch */
    public static final String COLUMNNAME_Fecha_IngArch = "Fecha_IngArch";

	/** Set Date of entry of File 	  */
	public void setFecha_IngArch (Timestamp Fecha_IngArch);

	/** Get Date of entry of File 	  */
	public Timestamp getFecha_IngArch();

    /** Column name Fecha_SalAcervo */
    public static final String COLUMNNAME_Fecha_SalAcervo = "Fecha_SalAcervo";

	/** Set Acquis Release Date	  */
	public void setFecha_SalAcervo (Timestamp Fecha_SalAcervo);

	/** Get Acquis Release Date	  */
	public Timestamp getFecha_SalAcervo();

    /** Column name Fecha_SalArch */
    public static final String COLUMNNAME_Fecha_SalArch = "Fecha_SalArch";

	/** Set File Exit Date	  */
	public void setFecha_SalArch (Timestamp Fecha_SalArch);

	/** Get File Exit Date	  */
	public Timestamp getFecha_SalArch();

    /** Column name Hist_Pac */
    public static final String COLUMNNAME_Hist_Pac = "Hist_Pac";

	/** Set Pacient History	  */
	public void setHist_Pac (String Hist_Pac);

	/** Get Pacient History	  */
	public String getHist_Pac();

    /** Column name Ing_Acervo */
    public static final String COLUMNNAME_Ing_Acervo = "Ing_Acervo";

	/** Set Ing_Acervo	  */
	public void setIng_Acervo (boolean Ing_Acervo);

	/** Get Ing_Acervo	  */
	public boolean isIng_Acervo();

    /** Column name Ing_Archivo */
    public static final String COLUMNNAME_Ing_Archivo = "Ing_Archivo";

	/** Set Ing_Archivo	  */
	public void setIng_Archivo (boolean Ing_Archivo);

	/** Get Ing_Archivo	  */
	public boolean isIng_Archivo();

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name Name_Pac */
    public static final String COLUMNNAME_Name_Pac = "Name_Pac";

	/** Set Patient Name	  */
	public void setName_Pac (String Name_Pac);

	/** Get Patient Name	  */
	public String getName_Pac();

    /** Column name Sal_Acervo */
    public static final String COLUMNNAME_Sal_Acervo = "Sal_Acervo";

	/** Set Sal_Acervo	  */
	public void setSal_Acervo (boolean Sal_Acervo);

	/** Get Sal_Acervo	  */
	public boolean isSal_Acervo();

    /** Column name Sal_Archivo */
    public static final String COLUMNNAME_Sal_Archivo = "Sal_Archivo";

	/** Set Sal_Archivo	  */
	public void setSal_Archivo (boolean Sal_Archivo);

	/** Get Sal_Archivo	  */
	public boolean isSal_Archivo();

    /** Column name User_IngAcervo */
    public static final String COLUMNNAME_User_IngAcervo = "User_IngAcervo";

	/** Set User_IngAcervo	  */
	public void setUser_IngAcervo (String User_IngAcervo);

	/** Get User_IngAcervo	  */
	public String getUser_IngAcervo();

    /** Column name User_IngArch */
    public static final String COLUMNNAME_User_IngArch = "User_IngArch";

	/** Set User IngArch	  */
	public void setUser_IngArch (String User_IngArch);

	/** Get User IngArch	  */
	public String getUser_IngArch();

    /** Column name User_SalAcervo */
    public static final String COLUMNNAME_User_SalAcervo = "User_SalAcervo";

	/** Set User_SalAcervo	  */
	public void setUser_SalAcervo (String User_SalAcervo);

	/** Get User_SalAcervo	  */
	public String getUser_SalAcervo();

    /** Column name User_SalArchivo */
    public static final String COLUMNNAME_User_SalArchivo = "User_SalArchivo";

	/** Set User_SalArchivo	  */
	public void setUser_SalArchivo (String User_SalArchivo);

	/** Get User_SalArchivo	  */
	public String getUser_SalArchivo();
}
