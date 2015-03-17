/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_OrderSetProd
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_OrderSetProd 
{

    /** TableName=EXME_OrderSetProd */
    public static final String Table_Name = "EXME_OrderSetProd";

    /** AD_Table_ID=1201140 */
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

    /** Column name Days */
    public static final String COLUMNNAME_Days = "Days";

	/** Set Number of days	  */
	public void setDays (int Days);

	/** Get Number of days	  */
	public int getDays();

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

    /** Column name Dose */
    public static final String COLUMNNAME_Dose = "Dose";

	/** Set Medication Doses.
	  * Medication Doses
	  */
	public void setDose (String Dose);

	/** Get Medication Doses.
	  * Medication Doses
	  */
	public String getDose();

    /** Column name DoseMax */
    public static final String COLUMNNAME_DoseMax = "DoseMax";

	/** Set Dose Maximum	  */
	public void setDoseMax (int DoseMax);

	/** Get Dose Maximum	  */
	public int getDoseMax();

    /** Column name Dose_txt */
    public static final String COLUMNNAME_Dose_txt = "Dose_txt";

	/** Set Dose	  */
	public void setDose_txt (String Dose_txt);

	/** Get Dose	  */
	public String getDose_txt();

    /** Column name EXME_Frequency1_ID */
    public static final String COLUMNNAME_EXME_Frequency1_ID = "EXME_Frequency1_ID";

	/** Set Frequency 1.
	  * Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID);

	/** Get Frequency 1.
	  * Frequency Header ID
	  */
	public int getEXME_Frequency1_ID();

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException;

    /** Column name EXME_Frequency2_ID */
    public static final String COLUMNNAME_EXME_Frequency2_ID = "EXME_Frequency2_ID";

	/** Set Frequency 2.
	  * Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID);

	/** Get Frequency 2.
	  * Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID();

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException;

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

    /** Column name EXME_Modifiers_ID */
    public static final String COLUMNNAME_EXME_Modifiers_ID = "EXME_Modifiers_ID";

	/** Set EXME_Modifiers_ID	  */
	public void setEXME_Modifiers_ID (int EXME_Modifiers_ID);

	/** Get EXME_Modifiers_ID	  */
	public int getEXME_Modifiers_ID();

	public I_EXME_Modifiers getEXME_Modifiers() throws RuntimeException;

    /** Column name EXME_OrderSetProd_ID */
    public static final String COLUMNNAME_EXME_OrderSetProd_ID = "EXME_OrderSetProd_ID";

	/** Set Order Set Products	  */
	public void setEXME_OrderSetProd_ID (int EXME_OrderSetProd_ID);

	/** Get Order Set Products	  */
	public int getEXME_OrderSetProd_ID();

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

    /** Column name EXME_Route_ID */
    public static final String COLUMNNAME_EXME_Route_ID = "EXME_Route_ID";

	/** Set Route.
	  * Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID);

	/** Get Route.
	  * Route
	  */
	public int getEXME_Route_ID();

	public I_EXME_Route getEXME_Route() throws RuntimeException;

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name EdadFinal */
    public static final String COLUMNNAME_EdadFinal = "EdadFinal";

	/** Set Year Maximum	  */
	public void setEdadFinal (int EdadFinal);

	/** Get Year Maximum	  */
	public int getEdadFinal();

    /** Column name EdadInicial */
    public static final String COLUMNNAME_EdadInicial = "EdadInicial";

	/** Set Year Minimum	  */
	public void setEdadInicial (int EdadInicial);

	/** Get Year Minimum	  */
	public int getEdadInicial();

    /** Column name ISWEIGHTRANK */
    public static final String COLUMNNAME_ISWEIGHTRANK = "ISWEIGHTRANK";

	/** Set ISWEIGHTRANK	  */
	public void setISWEIGHTRANK (boolean ISWEIGHTRANK);

	/** Get ISWEIGHTRANK	  */
	public boolean isWEIGHTRANK();

    /** Column name IsEstudio */
    public static final String COLUMNNAME_IsEstudio = "IsEstudio";

	/** Set IsEstudio	  */
	public void setIsEstudio (boolean IsEstudio);

	/** Get IsEstudio	  */
	public boolean isEstudio();

    /** Column name IsExternal */
    public static final String COLUMNNAME_IsExternal = "IsExternal";

	/** Set External.
	  * External
	  */
	public void setIsExternal (boolean IsExternal);

	/** Get External.
	  * External
	  */
	public boolean isExternal();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name MesFinal */
    public static final String COLUMNNAME_MesFinal = "MesFinal";

	/** Set Month Maximum	  */
	public void setMesFinal (int MesFinal);

	/** Get Month Maximum	  */
	public int getMesFinal();

    /** Column name MesInicial */
    public static final String COLUMNNAME_MesInicial = "MesInicial";

	/** Set Month Minimum	  */
	public void setMesInicial (int MesInicial);

	/** Get Month Minimum	  */
	public int getMesInicial();

    /** Column name ProductType */
    public static final String COLUMNNAME_ProductType = "ProductType";

	/** Set Product Type.
	  * Type of product
	  */
	public void setProductType (String ProductType);

	/** Get Product Type.
	  * Type of product
	  */
	public String getProductType();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity Plan.
	  * Planned Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity Plan.
	  * Planned Quantity
	  */
	public BigDecimal getQty();

    /** Column name Quantity */
    public static final String COLUMNNAME_Quantity = "Quantity";

	/** Set Quantity	  */
	public void setQuantity (BigDecimal Quantity);

	/** Get Quantity	  */
	public BigDecimal getQuantity();

    /** Column name Quantity_txt */
    public static final String COLUMNNAME_Quantity_txt = "Quantity_txt";

	/** Set Quantity	  */
	public void setQuantity_txt (String Quantity_txt);

	/** Get Quantity	  */
	public String getQuantity_txt();

    /** Column name Refill */
    public static final String COLUMNNAME_Refill = "Refill";

	/** Set Refill	  */
	public void setRefill (int Refill);

	/** Get Refill	  */
	public int getRefill();

    /** Column name UnitMeasure */
    public static final String COLUMNNAME_UnitMeasure = "UnitMeasure";

	/** Set Unit Measure	  */
	public void setUnitMeasure (String UnitMeasure);

	/** Get Unit Measure	  */
	public String getUnitMeasure();

    /** Column name WeightMax */
    public static final String COLUMNNAME_WeightMax = "WeightMax";

	/** Set Weight Max	  */
	public void setWeightMax (BigDecimal WeightMax);

	/** Get Weight Max	  */
	public BigDecimal getWeightMax();

    /** Column name WeightMin */
    public static final String COLUMNNAME_WeightMin = "WeightMin";

	/** Set Weight Min	  */
	public void setWeightMin (BigDecimal WeightMin);

	/** Get Weight Min	  */
	public BigDecimal getWeightMin();
}
