/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_GraficaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_GraficaDet 
{

    /** TableName=EXME_GraficaDet */
    public static final String Table_Name = "EXME_GraficaDet";

    /** AD_Table_ID=1201084 */
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

    /** Column name DividerNum */
    public static final String COLUMNNAME_DividerNum = "DividerNum";

	/** Set Number of Dividers	  */
	public void setDividerNum (BigDecimal DividerNum);

	/** Get Number of Dividers	  */
	public BigDecimal getDividerNum();

    /** Column name EXME_GraficaDet_ID */
    public static final String COLUMNNAME_EXME_GraficaDet_ID = "EXME_GraficaDet_ID";

	/** Set Graphic Detail	  */
	public void setEXME_GraficaDet_ID (int EXME_GraficaDet_ID);

	/** Get Graphic Detail	  */
	public int getEXME_GraficaDet_ID();

    /** Column name EXME_Grafica_ID */
    public static final String COLUMNNAME_EXME_Grafica_ID = "EXME_Grafica_ID";

	/** Set Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID);

	/** Get Graphic	  */
	public int getEXME_Grafica_ID();

	public I_EXME_Grafica getEXME_Grafica() throws RuntimeException;

    /** Column name EXME_SignoVital_ID */
    public static final String COLUMNNAME_EXME_SignoVital_ID = "EXME_SignoVital_ID";

	/** Set Vital Sign	  */
	public void setEXME_SignoVital_ID (int EXME_SignoVital_ID);

	/** Get Vital Sign	  */
	public int getEXME_SignoVital_ID();

	public I_EXME_SignoVital getEXME_SignoVital() throws RuntimeException;

    /** Column name Intervalos */
    public static final String COLUMNNAME_Intervalos = "Intervalos";

	/** Set Intervals.
	  * Intervals
	  */
	public void setIntervalos (BigDecimal Intervalos);

	/** Get Intervals.
	  * Intervals
	  */
	public BigDecimal getIntervalos();

    /** Column name IsXAxis */
    public static final String COLUMNNAME_IsXAxis = "IsXAxis";

	/** Set X Axis	  */
	public void setIsXAxis (boolean IsXAxis);

	/** Get X Axis	  */
	public boolean isXAxis();

    /** Column name IsYAxis */
    public static final String COLUMNNAME_IsYAxis = "IsYAxis";

	/** Set Y Axis	  */
	public void setIsYAxis (boolean IsYAxis);

	/** Get Y Axis	  */
	public boolean isYAxis();

    /** Column name Lim_Inferior */
    public static final String COLUMNNAME_Lim_Inferior = "Lim_Inferior";

	/** Set Minimum Level	  */
	public void setLim_Inferior (BigDecimal Lim_Inferior);

	/** Get Minimum Level	  */
	public BigDecimal getLim_Inferior();

    /** Column name Lim_Superior */
    public static final String COLUMNNAME_Lim_Superior = "Lim_Superior";

	/** Set Maximum Level	  */
	public void setLim_Superior (BigDecimal Lim_Superior);

	/** Get Maximum Level	  */
	public BigDecimal getLim_Superior();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

    /** Column name Ubicacion */
    public static final String COLUMNNAME_Ubicacion = "Ubicacion";

	/** Set Location of Element.
	  * Location of Element
	  */
	public void setUbicacion (String Ubicacion);

	/** Get Location of Element.
	  * Location of Element
	  */
	public String getUbicacion();

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

    /** Column name Visible */
    public static final String COLUMNNAME_Visible = "Visible";

	/** Set Visible	  */
	public void setVisible (boolean Visible);

	/** Get Visible	  */
	public boolean isVisible();
}
