/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigEnf
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigEnf extends PO implements I_EXME_ConfigEnf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigEnf (Properties ctx, int EXME_ConfigEnf_ID, String trxName)
    {
      super (ctx, EXME_ConfigEnf_ID, trxName);
      /** if (EXME_ConfigEnf_ID == 0)
        {
			setAllUnits (false);
			setDischargeForDecease (0);
			setEXME_ConfigEnf_ID (0);
			setEXME_EnfeCuest_ID (0);
			setEXME_IndicaCuest_ID (0);
			setEXME_TipoProdFoley_ID (0);
			setEXME_TipoProdSng_ID (0);
			setTouchScr (false);
			setUniDosis (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigEnf (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigEnf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set All Units.
		@param AllUnits All Units	  */
	public void setAllUnits (boolean AllUnits)
	{
		set_Value (COLUMNNAME_AllUnits, Boolean.valueOf(AllUnits));
	}

	/** Get All Units.
		@return All Units	  */
	public boolean isAllUnits () 
	{
		Object oo = get_Value(COLUMNNAME_AllUnits);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diagnostic Required.
		@param DiagMandatory 
		Diagnostic Required
	  */
	public void setDiagMandatory (boolean DiagMandatory)
	{
		set_Value (COLUMNNAME_DiagMandatory, Boolean.valueOf(DiagMandatory));
	}

	/** Get Diagnostic Required.
		@return Diagnostic Required
	  */
	public boolean isDiagMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_DiagMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discharge for Decease.
		@param DischargeForDecease Discharge for Decease	  */
	public void setDischargeForDecease (int DischargeForDecease)
	{
		set_Value (COLUMNNAME_DischargeForDecease, Integer.valueOf(DischargeForDecease));
	}

	/** Get Discharge for Decease.
		@return Discharge for Decease	  */
	public int getDischargeForDecease () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DischargeForDecease);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Infirmary's Configuration.
		@param EXME_ConfigEnf_ID Infirmary's Configuration	  */
	public void setEXME_ConfigEnf_ID (int EXME_ConfigEnf_ID)
	{
		if (EXME_ConfigEnf_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEnf_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEnf_ID, Integer.valueOf(EXME_ConfigEnf_ID));
	}

	/** Get Infirmary's Configuration.
		@return Infirmary's Configuration	  */
	public int getEXME_ConfigEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEnf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nursing's Questionnaire.
		@param EXME_EnfeCuest_ID Nursing's Questionnaire	  */
	public void setEXME_EnfeCuest_ID (int EXME_EnfeCuest_ID)
	{
		if (EXME_EnfeCuest_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfeCuest_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EnfeCuest_ID, Integer.valueOf(EXME_EnfeCuest_ID));
	}

	/** Get Nursing's Questionnaire.
		@return Nursing's Questionnaire	  */
	public int getEXME_EnfeCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfeCuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Indication´s Questionnaire .
		@param EXME_IndicaCuest_ID Indication´s Questionnaire 	  */
	public void setEXME_IndicaCuest_ID (int EXME_IndicaCuest_ID)
	{
		if (EXME_IndicaCuest_ID < 1)
			 throw new IllegalArgumentException ("EXME_IndicaCuest_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_IndicaCuest_ID, Integer.valueOf(EXME_IndicaCuest_ID));
	}

	/** Get Indication´s Questionnaire .
		@return Indication´s Questionnaire 	  */
	public int getEXME_IndicaCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IndicaCuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Foley Catheter.
		@param EXME_TipoProdFoley_ID Foley Catheter	  */
	public void setEXME_TipoProdFoley_ID (int EXME_TipoProdFoley_ID)
	{
		if (EXME_TipoProdFoley_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProdFoley_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProdFoley_ID, Integer.valueOf(EXME_TipoProdFoley_ID));
	}

	/** Get Foley Catheter.
		@return Foley Catheter	  */
	public int getEXME_TipoProdFoley_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProdFoley_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nasogastric Drainage Catheter.
		@param EXME_TipoProdSng_ID Nasogastric Drainage Catheter	  */
	public void setEXME_TipoProdSng_ID (int EXME_TipoProdSng_ID)
	{
		if (EXME_TipoProdSng_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProdSng_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProdSng_ID, Integer.valueOf(EXME_TipoProdSng_ID));
	}

	/** Get Nasogastric Drainage Catheter.
		@return Nasogastric Drainage Catheter	  */
	public int getEXME_TipoProdSng_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProdSng_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cathetere's Family.
		@param Fam_Cateteres_ID Cathetere's Family	  */
	public void setFam_Cateteres_ID (int Fam_Cateteres_ID)
	{
		if (Fam_Cateteres_ID < 1) 
			set_Value (COLUMNNAME_Fam_Cateteres_ID, null);
		else 
			set_Value (COLUMNNAME_Fam_Cateteres_ID, Integer.valueOf(Fam_Cateteres_ID));
	}

	/** Get Cathetere's Family.
		@return Cathetere's Family	  */
	public int getFam_Cateteres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fam_Cateteres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insulin's Family.
		@param Fam_Insulinas_ID Insulin's Family	  */
	public void setFam_Insulinas_ID (int Fam_Insulinas_ID)
	{
		if (Fam_Insulinas_ID < 1) 
			set_Value (COLUMNNAME_Fam_Insulinas_ID, null);
		else 
			set_Value (COLUMNNAME_Fam_Insulinas_ID, Integer.valueOf(Fam_Insulinas_ID));
	}

	/** Get Insulin's Family.
		@return Insulin's Family	  */
	public int getFam_Insulinas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fam_Insulinas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medecine's Family.
		@param Fam_Medicamentos_ID Medecine's Family	  */
	public void setFam_Medicamentos_ID (int Fam_Medicamentos_ID)
	{
		if (Fam_Medicamentos_ID < 1) 
			set_Value (COLUMNNAME_Fam_Medicamentos_ID, null);
		else 
			set_Value (COLUMNNAME_Fam_Medicamentos_ID, Integer.valueOf(Fam_Medicamentos_ID));
	}

	/** Get Medecine's Family.
		@return Medecine's Family	  */
	public int getFam_Medicamentos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fam_Medicamentos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Solution's Family.
		@param Fam_Soluciones_ID Solution's Family	  */
	public void setFam_Soluciones_ID (int Fam_Soluciones_ID)
	{
		if (Fam_Soluciones_ID < 1) 
			set_Value (COLUMNNAME_Fam_Soluciones_ID, null);
		else 
			set_Value (COLUMNNAME_Fam_Soluciones_ID, Integer.valueOf(Fam_Soluciones_ID));
	}

	/** Get Solution's Family.
		@return Solution's Family	  */
	public int getFam_Soluciones_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fam_Soluciones_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoDieta AD_Reference_ID=1200197 */
	public static final int TIPODIETA_AD_Reference_ID=1200197;
	/** Diet = D */
	public static final String TIPODIETA_Diet = "D";
	/** Medicine = M */
	public static final String TIPODIETA_Medicine = "M";
	/** Set Diet Type.
		@param TipoDieta Diet Type	  */
	public void setTipoDieta (String TipoDieta)
	{

		if (TipoDieta == null || TipoDieta.equals("D") || TipoDieta.equals("M")); else throw new IllegalArgumentException ("TipoDieta Invalid value - " + TipoDieta + " - Reference_ID=1200197 - D - M");		set_Value (COLUMNNAME_TipoDieta, TipoDieta);
	}

	/** Get Diet Type.
		@return Diet Type	  */
	public String getTipoDieta () 
	{
		return (String)get_Value(COLUMNNAME_TipoDieta);
	}

	/** TipoMedicamento AD_Reference_ID=1200197 */
	public static final int TIPOMEDICAMENTO_AD_Reference_ID=1200197;
	/** Diet = D */
	public static final String TIPOMEDICAMENTO_Diet = "D";
	/** Medicine = M */
	public static final String TIPOMEDICAMENTO_Medicine = "M";
	/** Set Medicine Type.
		@param TipoMedicamento Medicine Type	  */
	public void setTipoMedicamento (String TipoMedicamento)
	{

		if (TipoMedicamento == null || TipoMedicamento.equals("D") || TipoMedicamento.equals("M")); else throw new IllegalArgumentException ("TipoMedicamento Invalid value - " + TipoMedicamento + " - Reference_ID=1200197 - D - M");		set_Value (COLUMNNAME_TipoMedicamento, TipoMedicamento);
	}

	/** Get Medicine Type.
		@return Medicine Type	  */
	public String getTipoMedicamento () 
	{
		return (String)get_Value(COLUMNNAME_TipoMedicamento);
	}

	/** Set Touchscreen.
		@param TouchScr Touchscreen	  */
	public void setTouchScr (boolean TouchScr)
	{
		set_Value (COLUMNNAME_TouchScr, Boolean.valueOf(TouchScr));
	}

	/** Get Touchscreen.
		@return Touchscreen	  */
	public boolean isTouchScr () 
	{
		Object oo = get_Value(COLUMNNAME_TouchScr);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Unidose.
		@param UniDosis Unidose	  */
	public void setUniDosis (boolean UniDosis)
	{
		set_Value (COLUMNNAME_UniDosis, Boolean.valueOf(UniDosis));
	}

	/** Get Unidose.
		@return Unidose	  */
	public boolean isUniDosis () 
	{
		Object oo = get_Value(COLUMNNAME_UniDosis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}