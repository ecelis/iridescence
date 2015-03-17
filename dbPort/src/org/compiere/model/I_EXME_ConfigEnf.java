/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigEnf
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigEnf 
{

    /** TableName=EXME_ConfigEnf */
    public static final String Table_Name = "EXME_ConfigEnf";

    /** AD_Table_ID=1200503 */
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

    /** Column name AllUnits */
    public static final String COLUMNNAME_AllUnits = "AllUnits";

	/** Set All Units	  */
	public void setAllUnits (boolean AllUnits);

	/** Get All Units	  */
	public boolean isAllUnits();

    /** Column name DiagMandatory */
    public static final String COLUMNNAME_DiagMandatory = "DiagMandatory";

	/** Set Diagnostic Required.
	  * Diagnostic Required
	  */
	public void setDiagMandatory (boolean DiagMandatory);

	/** Get Diagnostic Required.
	  * Diagnostic Required
	  */
	public boolean isDiagMandatory();

    /** Column name DischargeForDecease */
    public static final String COLUMNNAME_DischargeForDecease = "DischargeForDecease";

	/** Set Discharge for Decease	  */
	public void setDischargeForDecease (int DischargeForDecease);

	/** Get Discharge for Decease	  */
	public int getDischargeForDecease();

    /** Column name EXME_ConfigEnf_ID */
    public static final String COLUMNNAME_EXME_ConfigEnf_ID = "EXME_ConfigEnf_ID";

	/** Set Infirmary's Configuration	  */
	public void setEXME_ConfigEnf_ID (int EXME_ConfigEnf_ID);

	/** Get Infirmary's Configuration	  */
	public int getEXME_ConfigEnf_ID();

    /** Column name EXME_EnfeCuest_ID */
    public static final String COLUMNNAME_EXME_EnfeCuest_ID = "EXME_EnfeCuest_ID";

	/** Set Nursing's Questionnaire	  */
	public void setEXME_EnfeCuest_ID (int EXME_EnfeCuest_ID);

	/** Get Nursing's Questionnaire	  */
	public int getEXME_EnfeCuest_ID();

    /** Column name EXME_IndicaCuest_ID */
    public static final String COLUMNNAME_EXME_IndicaCuest_ID = "EXME_IndicaCuest_ID";

	/** Set Indication´s Questionnaire 	  */
	public void setEXME_IndicaCuest_ID (int EXME_IndicaCuest_ID);

	/** Get Indication´s Questionnaire 	  */
	public int getEXME_IndicaCuest_ID();

    /** Column name EXME_TipoProdFoley_ID */
    public static final String COLUMNNAME_EXME_TipoProdFoley_ID = "EXME_TipoProdFoley_ID";

	/** Set Foley Catheter	  */
	public void setEXME_TipoProdFoley_ID (int EXME_TipoProdFoley_ID);

	/** Get Foley Catheter	  */
	public int getEXME_TipoProdFoley_ID();

    /** Column name EXME_TipoProdSng_ID */
    public static final String COLUMNNAME_EXME_TipoProdSng_ID = "EXME_TipoProdSng_ID";

	/** Set Nasogastric Drainage Catheter	  */
	public void setEXME_TipoProdSng_ID (int EXME_TipoProdSng_ID);

	/** Get Nasogastric Drainage Catheter	  */
	public int getEXME_TipoProdSng_ID();

    /** Column name Fam_Cateteres_ID */
    public static final String COLUMNNAME_Fam_Cateteres_ID = "Fam_Cateteres_ID";

	/** Set Cathetere's Family	  */
	public void setFam_Cateteres_ID (int Fam_Cateteres_ID);

	/** Get Cathetere's Family	  */
	public int getFam_Cateteres_ID();

    /** Column name Fam_Insulinas_ID */
    public static final String COLUMNNAME_Fam_Insulinas_ID = "Fam_Insulinas_ID";

	/** Set Insulin's Family	  */
	public void setFam_Insulinas_ID (int Fam_Insulinas_ID);

	/** Get Insulin's Family	  */
	public int getFam_Insulinas_ID();

    /** Column name Fam_Medicamentos_ID */
    public static final String COLUMNNAME_Fam_Medicamentos_ID = "Fam_Medicamentos_ID";

	/** Set Medecine's Family	  */
	public void setFam_Medicamentos_ID (int Fam_Medicamentos_ID);

	/** Get Medecine's Family	  */
	public int getFam_Medicamentos_ID();

    /** Column name Fam_Soluciones_ID */
    public static final String COLUMNNAME_Fam_Soluciones_ID = "Fam_Soluciones_ID";

	/** Set Solution's Family	  */
	public void setFam_Soluciones_ID (int Fam_Soluciones_ID);

	/** Get Solution's Family	  */
	public int getFam_Soluciones_ID();

    /** Column name TipoDieta */
    public static final String COLUMNNAME_TipoDieta = "TipoDieta";

	/** Set Diet Type	  */
	public void setTipoDieta (String TipoDieta);

	/** Get Diet Type	  */
	public String getTipoDieta();

    /** Column name TipoMedicamento */
    public static final String COLUMNNAME_TipoMedicamento = "TipoMedicamento";

	/** Set Medicine Type	  */
	public void setTipoMedicamento (String TipoMedicamento);

	/** Get Medicine Type	  */
	public String getTipoMedicamento();

    /** Column name TouchScr */
    public static final String COLUMNNAME_TouchScr = "TouchScr";

	/** Set Touchscreen	  */
	public void setTouchScr (boolean TouchScr);

	/** Get Touchscreen	  */
	public boolean isTouchScr();

    /** Column name UniDosis */
    public static final String COLUMNNAME_UniDosis = "UniDosis";

	/** Set Unidose	  */
	public void setUniDosis (boolean UniDosis);

	/** Get Unidose	  */
	public boolean isUniDosis();
}
