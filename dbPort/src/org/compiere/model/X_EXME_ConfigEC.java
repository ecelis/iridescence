/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_ConfigEC
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigEC extends PO implements I_EXME_ConfigEC, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigEC (Properties ctx, int EXME_ConfigEC_ID, String trxName)
    {
      super (ctx, EXME_ConfigEC_ID, trxName);
      /** if (EXME_ConfigEC_ID == 0)
        {
			setAplicaPedCtaPac (false);
			setCargaDiferAlm (true);
// Y
			setCargDiarioAuto (Env.ZERO);
// 00.00
			setC_BPartner_ID (0);
			setCDA (false);
			setClasCliente (false);
// N
			setCreateUserPatient (false);
			setEXME_ConfigEC_ID (0);
			setEXME_EstServ_ID (0);
			setGeneraBO (false);
			setHL7ADT (false);
			setImpNotaEntMed (false);
			setImpRecetaMed (false);
			setImpValeEntMed (false);
			setImpValeParcial (false);
			setIncluyeCartilla (false);
			setInterfaseLaserFiche (false);
			setIsReqConfirm (false);
			setMostrarPacRefer (false);
			setM_PriceList_ID (0);
			setMultipleUDM (false);
// N
			setM_Warehouse_ID (0);
			setM_WarehouseSer_ID (0);
			setName (null);
			setNotasMedicas (false);
			setNo_Usuarios_HL7 (0);
			setPermCamAlm (false);
			setPermCamEst (false);
// N
			setPreReqCita (false);
			setPrivado (true);
// Y
			setQty_Hr_Abierta (0);
			setReqFactCE (false);
			setReqFactIndH (false);
			setReqFactZero (false);
			setSurteConfirma (false);
			setSurtir (false);
			setTEST (false);
			setValidaCita (false);
			setvalidaDiagCita (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigEC (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigEC[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Automatic Application of Orders to Patient acct.
		@param AplicaPedCtaPac 
		Automatic Application of Orders to Patient acct
	  */
	public void setAplicaPedCtaPac (boolean AplicaPedCtaPac)
	{
		set_Value (COLUMNNAME_AplicaPedCtaPac, Boolean.valueOf(AplicaPedCtaPac));
	}

	/** Get Automatic Application of Orders to Patient acct.
		@return Automatic Application of Orders to Patient acct
	  */
	public boolean isAplicaPedCtaPac () 
	{
		Object oo = get_Value(COLUMNNAME_AplicaPedCtaPac);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Blood bank .
		@param BancoDeSangre Blood bank 	  */
	public void setBancoDeSangre (boolean BancoDeSangre)
	{
		set_Value (COLUMNNAME_BancoDeSangre, Boolean.valueOf(BancoDeSangre));
	}

	/** Get Blood bank .
		@return Blood bank 	  */
	public boolean isBancoDeSangre () 
	{
		Object oo = get_Value(COLUMNNAME_BancoDeSangre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Charges Differences to the Delivering Warehouse.
		@param CargaDiferAlm 
		Charges differences to the delivering warehouse
	  */
	public void setCargaDiferAlm (boolean CargaDiferAlm)
	{
		set_Value (COLUMNNAME_CargaDiferAlm, Boolean.valueOf(CargaDiferAlm));
	}

	/** Get Charges Differences to the Delivering Warehouse.
		@return Charges differences to the delivering warehouse
	  */
	public boolean isCargaDiferAlm () 
	{
		Object oo = get_Value(COLUMNNAME_CargaDiferAlm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hour of Automatic Daily Charge.
		@param CargDiarioAuto 
		Hour to make the daily Automatic Charge
	  */
	public void setCargDiarioAuto (BigDecimal CargDiarioAuto)
	{
		if (CargDiarioAuto == null)
			throw new IllegalArgumentException ("CargDiarioAuto is mandatory.");
		set_Value (COLUMNNAME_CargDiarioAuto, CargDiarioAuto);
	}

	/** Get Hour of Automatic Daily Charge.
		@return Hour to make the daily Automatic Charge
	  */
	public BigDecimal getCargDiarioAuto () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CargDiarioAuto);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Clinical Document Architecture.
		@param CDA Clinical Document Architecture	  */
	public void setCDA (boolean CDA)
	{
		set_Value (COLUMNNAME_CDA, Boolean.valueOf(CDA));
	}

	/** Get Clinical Document Architecture.
		@return Clinical Document Architecture	  */
	public boolean isCDA () 
	{
		Object oo = get_Value(COLUMNNAME_CDA);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Customer classification.
		@param ClasCliente 
		Customer classification
	  */
	public void setClasCliente (boolean ClasCliente)
	{
		set_Value (COLUMNNAME_ClasCliente, Boolean.valueOf(ClasCliente));
	}

	/** Get Customer classification.
		@return Customer classification
	  */
	public boolean isClasCliente () 
	{
		Object oo = get_Value(COLUMNNAME_ClasCliente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Close encounter when discharge.
		@param CloseEncWD Close encounter when discharge	  */
	public void setCloseEncWD (boolean CloseEncWD)
	{
		set_Value (COLUMNNAME_CloseEncWD, Boolean.valueOf(CloseEncWD));
	}

	/** Get Close encounter when discharge.
		@return Close encounter when discharge	  */
	public boolean isCloseEncWD () 
	{
		Object oo = get_Value(COLUMNNAME_CloseEncWD);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Create user for patient.
		@param CreateUserPatient 
		Create user for patient
	  */
	public void setCreateUserPatient (boolean CreateUserPatient)
	{
		set_Value (COLUMNNAME_CreateUserPatient, Boolean.valueOf(CreateUserPatient));
	}

	/** Get Create user for patient.
		@return Create user for patient
	  */
	public boolean isCreateUserPatient () 
	{
		Object oo = get_Value(COLUMNNAME_CreateUserPatient);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Images Directory.
		@param DirectorioImag 
		Images Directory
	  */
	public void setDirectorioImag (String DirectorioImag)
	{
		set_Value (COLUMNNAME_DirectorioImag, DirectorioImag);
	}

	/** Get Images Directory.
		@return Images Directory
	  */
	public String getDirectorioImag () 
	{
		return (String)get_Value(COLUMNNAME_DirectorioImag);
	}

	/** Set Minimum duration.
		@param DurationMin Minimum duration	  */
	public void setDurationMin (int DurationMin)
	{
		set_Value (COLUMNNAME_DurationMin, Integer.valueOf(DurationMin));
	}

	/** Get Minimum duration.
		@return Minimum duration	  */
	public int getDurationMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DurationMin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMR Configuration.
		@param EXME_ConfigEC_ID 
		EMR Configuration
	  */
	public void setEXME_ConfigEC_ID (int EXME_ConfigEC_ID)
	{
		if (EXME_ConfigEC_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigEC_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigEC_ID, Integer.valueOf(EXME_ConfigEC_ID));
	}

	/** Get EMR Configuration.
		@return EMR Configuration
	  */
	public int getEXME_ConfigEC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigEC_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Clinic History Questionnaire.
		@param EXME_Cuestionario2_ID Clinic History Questionnaire	  */
	public void setEXME_Cuestionario2_ID (int EXME_Cuestionario2_ID)
	{
		if (EXME_Cuestionario2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario2_ID, Integer.valueOf(EXME_Cuestionario2_ID));
	}

	/** Get Clinic History Questionnaire.
		@return Clinic History Questionnaire	  */
	public int getEXME_Cuestionario2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CPT Code.
		@param EXME_Intervencion_ID 
		Current Procedural Terminology (CPT)
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get CPT Code.
		@return Current Procedural Terminology (CPT)
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Genera Back Order.
		@param GeneraBO 
		Genera Back Order
	  */
	public void setGeneraBO (boolean GeneraBO)
	{
		set_Value (COLUMNNAME_GeneraBO, Boolean.valueOf(GeneraBO));
	}

	/** Get Genera Back Order.
		@return Genera Back Order
	  */
	public boolean isGeneraBO () 
	{
		Object oo = get_Value(COLUMNNAME_GeneraBO);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requires HL7 ADT Message.
		@param HL7ADT Requires HL7 ADT Message	  */
	public void setHL7ADT (boolean HL7ADT)
	{
		set_Value (COLUMNNAME_HL7ADT, Boolean.valueOf(HL7ADT));
	}

	/** Get Requires HL7 ADT Message.
		@return Requires HL7 ADT Message	  */
	public boolean isHL7ADT () 
	{
		Object oo = get_Value(COLUMNNAME_HL7ADT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Eligibility Days.
		@param Hours_Eleg Eligibility Days	  */
	public void setHours_Eleg (int Hours_Eleg)
	{
		set_Value (COLUMNNAME_Hours_Eleg, Integer.valueOf(Hours_Eleg));
	}

	/** Get Eligibility Days.
		@return Eligibility Days	  */
	public int getHours_Eleg () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Hours_Eleg);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient WinLab Access Web .
		@param IDPaziente Patient WinLab Access Web 	  */
	public void setIDPaziente (String IDPaziente)
	{
		set_Value (COLUMNNAME_IDPaziente, IDPaziente);
	}

	/** Get Patient WinLab Access Web .
		@return Patient WinLab Access Web 	  */
	public String getIDPaziente () 
	{
		return (String)get_Value(COLUMNNAME_IDPaziente);
	}

	/** Set Print Delivery Note of Medications.
		@param ImpNotaEntMed 
		Print delivery note of medications
	  */
	public void setImpNotaEntMed (boolean ImpNotaEntMed)
	{
		set_Value (COLUMNNAME_ImpNotaEntMed, Boolean.valueOf(ImpNotaEntMed));
	}

	/** Get Print Delivery Note of Medications.
		@return Print delivery note of medications
	  */
	public boolean isImpNotaEntMed () 
	{
		Object oo = get_Value(COLUMNNAME_ImpNotaEntMed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Medications Prescription.
		@param ImpRecetaMed 
		Print Medication prescription
	  */
	public void setImpRecetaMed (boolean ImpRecetaMed)
	{
		set_Value (COLUMNNAME_ImpRecetaMed, Boolean.valueOf(ImpRecetaMed));
	}

	/** Get Print Medications Prescription.
		@return Print Medication prescription
	  */
	public boolean isImpRecetaMed () 
	{
		Object oo = get_Value(COLUMNNAME_ImpRecetaMed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Medication Coupon for Delivery.
		@param ImpValeEntMed 
		Print Medication Coupon for delivery
	  */
	public void setImpValeEntMed (boolean ImpValeEntMed)
	{
		set_Value (COLUMNNAME_ImpValeEntMed, Boolean.valueOf(ImpValeEntMed));
	}

	/** Get Print Medication Coupon for Delivery.
		@return Print Medication Coupon for delivery
	  */
	public boolean isImpValeEntMed () 
	{
		Object oo = get_Value(COLUMNNAME_ImpValeEntMed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Coupon of Partial Medications.
		@param ImpValeParcial 
		Print Coupon of Partial Medications
	  */
	public void setImpValeParcial (boolean ImpValeParcial)
	{
		set_Value (COLUMNNAME_ImpValeParcial, Boolean.valueOf(ImpValeParcial));
	}

	/** Get Print Coupon of Partial Medications.
		@return Print Coupon of Partial Medications
	  */
	public boolean isImpValeParcial () 
	{
		Object oo = get_Value(COLUMNNAME_ImpValeParcial);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Included in Immunization Cards.
		@param IncluyeCartilla Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla)
	{
		set_Value (COLUMNNAME_IncluyeCartilla, Boolean.valueOf(IncluyeCartilla));
	}

	/** Get Included in Immunization Cards.
		@return Included in Immunization Cards	  */
	public boolean isIncluyeCartilla () 
	{
		Object oo = get_Value(COLUMNNAME_IncluyeCartilla);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set InterfaseLaserFiche.
		@param InterfaseLaserFiche InterfaseLaserFiche	  */
	public void setInterfaseLaserFiche (boolean InterfaseLaserFiche)
	{
		set_Value (COLUMNNAME_InterfaseLaserFiche, Boolean.valueOf(InterfaseLaserFiche));
	}

	/** Get InterfaseLaserFiche.
		@return InterfaseLaserFiche	  */
	public boolean isInterfaseLaserFiche () 
	{
		Object oo = get_Value(COLUMNNAME_InterfaseLaserFiche);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set HL 7 Interface.
		@param Interfaz_HL7 
		HL 7 Interface
	  */
	public void setInterfaz_HL7 (boolean Interfaz_HL7)
	{
		set_Value (COLUMNNAME_Interfaz_HL7, Boolean.valueOf(Interfaz_HL7));
	}

	/** Get HL 7 Interface.
		@return HL 7 Interface
	  */
	public boolean isInterfaz_HL7 () 
	{
		Object oo = get_Value(COLUMNNAME_Interfaz_HL7);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Require Medical Appoinment Confirmation.
		@param IsReqConfirm 
		Require Medical Appoinment Confirmation
	  */
	public void setIsReqConfirm (boolean IsReqConfirm)
	{
		set_Value (COLUMNNAME_IsReqConfirm, Boolean.valueOf(IsReqConfirm));
	}

	/** Get Require Medical Appoinment Confirmation.
		@return Require Medical Appoinment Confirmation
	  */
	public boolean isReqConfirm () 
	{
		Object oo = get_Value(COLUMNNAME_IsReqConfirm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kodak.
		@param KODAK_R Kodak	  */
	public void setKODAK_R (String KODAK_R)
	{
		set_Value (COLUMNNAME_KODAK_R, KODAK_R);
	}

	/** Get Kodak.
		@return Kodak	  */
	public String getKODAK_R () 
	{
		return (String)get_Value(COLUMNNAME_KODAK_R);
	}

	/** Set Kodak Web Link.
		@param LIGAKODAK 
		Kodak Web Link
	  */
	public void setLIGAKODAK (boolean LIGAKODAK)
	{
		set_Value (COLUMNNAME_LIGAKODAK, Boolean.valueOf(LIGAKODAK));
	}

	/** Get Kodak Web Link.
		@return Kodak Web Link
	  */
	public boolean isLIGAKODAK () 
	{
		Object oo = get_Value(COLUMNNAME_LIGAKODAK);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Web Link to Winlab.
		@param LigaWinlab 
		Web Link to Winlab
	  */
	public void setLigaWinlab (boolean LigaWinlab)
	{
		set_Value (COLUMNNAME_LigaWinlab, Boolean.valueOf(LigaWinlab));
	}

	/** Get Web Link to Winlab.
		@return Web Link to Winlab
	  */
	public boolean isLigaWinlab () 
	{
		Object oo = get_Value(COLUMNNAME_LigaWinlab);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Show printer.
		@param MostrarImpresora Show printer	  */
	public void setMostrarImpresora (boolean MostrarImpresora)
	{
		set_Value (COLUMNNAME_MostrarImpresora, Boolean.valueOf(MostrarImpresora));
	}

	/** Get Show printer.
		@return Show printer	  */
	public boolean isMostrarImpresora () 
	{
		Object oo = get_Value(COLUMNNAME_MostrarImpresora);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Show Referenced Patient.
		@param MostrarPacRefer 
		Show Referenced Patient in Medical Consultation
	  */
	public void setMostrarPacRefer (boolean MostrarPacRefer)
	{
		set_Value (COLUMNNAME_MostrarPacRefer, Boolean.valueOf(MostrarPacRefer));
	}

	/** Get Show Referenced Patient.
		@return Show Referenced Patient in Medical Consultation
	  */
	public boolean isMostrarPacRefer () 
	{
		Object oo = get_Value(COLUMNNAME_MostrarPacRefer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Regulated price.
		@param M_PriceList_Reg_ID Regulated price	  */
	public void setM_PriceList_Reg_ID (int M_PriceList_Reg_ID)
	{
		if (M_PriceList_Reg_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_Reg_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_Reg_ID, Integer.valueOf(M_PriceList_Reg_ID));
	}

	/** Get Regulated price.
		@return Regulated price	  */
	public int getM_PriceList_Reg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Reg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Multiple Units of Measure.
		@param MultipleUDM 
		Handles multiples unit of measures
	  */
	public void setMultipleUDM (boolean MultipleUDM)
	{
		set_Value (COLUMNNAME_MultipleUDM, Boolean.valueOf(MultipleUDM));
	}

	/** Get Multiple Units of Measure.
		@return Handles multiples unit of measures
	  */
	public boolean isMultipleUDM () 
	{
		Object oo = get_Value(COLUMNNAME_MultipleUDM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Services Warehouse.
		@param M_WarehouseSer_ID 
		Requesting warehouse of services for external consult
	  */
	public void setM_WarehouseSer_ID (int M_WarehouseSer_ID)
	{
		if (M_WarehouseSer_ID < 1)
			 throw new IllegalArgumentException ("M_WarehouseSer_ID is mandatory.");
		set_Value (COLUMNNAME_M_WarehouseSer_ID, Integer.valueOf(M_WarehouseSer_ID));
	}

	/** Get Services Warehouse.
		@return Requesting warehouse of services for external consult
	  */
	public int getM_WarehouseSer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseSer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Medical Record.
		@param NotasMedicas Medical Record	  */
	public void setNotasMedicas (boolean NotasMedicas)
	{
		set_Value (COLUMNNAME_NotasMedicas, Boolean.valueOf(NotasMedicas));
	}

	/** Get Medical Record.
		@return Medical Record	  */
	public boolean isNotasMedicas () 
	{
		Object oo = get_Value(COLUMNNAME_NotasMedicas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set HL7 Allowed Users.
		@param No_Usuarios_HL7 
		HL7 Allowed Users
	  */
	public void setNo_Usuarios_HL7 (int No_Usuarios_HL7)
	{
		set_ValueNoCheck (COLUMNNAME_No_Usuarios_HL7, Integer.valueOf(No_Usuarios_HL7));
	}

	/** Get HL7 Allowed Users.
		@return HL7 Allowed Users
	  */
	public int getNo_Usuarios_HL7 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Usuarios_HL7);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Permit Change of Warehouse.
		@param PermCamAlm 
		Permit Change of Warehouse
	  */
	public void setPermCamAlm (boolean PermCamAlm)
	{
		set_Value (COLUMNNAME_PermCamAlm, Boolean.valueOf(PermCamAlm));
	}

	/** Get Permit Change of Warehouse.
		@return Permit Change of Warehouse
	  */
	public boolean isPermCamAlm () 
	{
		Object oo = get_Value(COLUMNNAME_PermCamAlm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Permit Change Station Service.
		@param PermCamEst 
		Permit change station service
	  */
	public void setPermCamEst (boolean PermCamEst)
	{
		set_Value (COLUMNNAME_PermCamEst, Boolean.valueOf(PermCamEst));
	}

	/** Get Permit Change Station Service.
		@return Permit change station service
	  */
	public boolean isPermCamEst () 
	{
		Object oo = get_Value(COLUMNNAME_PermCamEst);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Medical Consultation's Prerequisites.
		@param PreReqCita Medical Consultation's Prerequisites	  */
	public void setPreReqCita (boolean PreReqCita)
	{
		set_Value (COLUMNNAME_PreReqCita, Boolean.valueOf(PreReqCita));
	}

	/** Get Medical Consultation's Prerequisites.
		@return Medical Consultation's Prerequisites	  */
	public boolean isPreReqCita () 
	{
		Object oo = get_Value(COLUMNNAME_PreReqCita);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Private.
		@param Privado Private	  */
	public void setPrivado (boolean Privado)
	{
		set_Value (COLUMNNAME_Privado, Boolean.valueOf(Privado));
	}

	/** Get Private.
		@return Private	  */
	public boolean isPrivado () 
	{
		Object oo = get_Value(COLUMNNAME_Privado);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Products by Specialty.
		@param ProductoXEsp 
		Filtrate Products by specialty
	  */
	public void setProductoXEsp (boolean ProductoXEsp)
	{
		set_Value (COLUMNNAME_ProductoXEsp, Boolean.valueOf(ProductoXEsp));
	}

	/** Get Products by Specialty.
		@return Filtrate Products by specialty
	  */
	public boolean isProductoXEsp () 
	{
		Object oo = get_Value(COLUMNNAME_ProductoXEsp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set How many hours are the windows open?.
		@param Qty_Hr_Abierta 
		How many hours at day are the windows open?
	  */
	public void setQty_Hr_Abierta (int Qty_Hr_Abierta)
	{
		set_Value (COLUMNNAME_Qty_Hr_Abierta, Integer.valueOf(Qty_Hr_Abierta));
	}

	/** Get How many hours are the windows open?.
		@return How many hours at day are the windows open?
	  */
	public int getQty_Hr_Abierta () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Qty_Hr_Abierta);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Receiving Application.
		@param Receiving_Application Receiving Application	  */
	public void setReceiving_Application (String Receiving_Application)
	{
		set_Value (COLUMNNAME_Receiving_Application, Receiving_Application);
	}

	/** Get Receiving Application.
		@return Receiving Application	  */
	public String getReceiving_Application () 
	{
		return (String)get_Value(COLUMNNAME_Receiving_Application);
	}

	/** Set Receiving Facility.
		@param Receiving_Facility Receiving Facility	  */
	public void setReceiving_Facility (String Receiving_Facility)
	{
		set_Value (COLUMNNAME_Receiving_Facility, Receiving_Facility);
	}

	/** Get Receiving Facility.
		@return Receiving Facility	  */
	public String getReceiving_Facility () 
	{
		return (String)get_Value(COLUMNNAME_Receiving_Facility);
	}

	/** Set Require Invoicing Before Executing Medical Appointment.
		@param ReqFactCE Require Invoicing Before Executing Medical Appointment	  */
	public void setReqFactCE (boolean ReqFactCE)
	{
		set_Value (COLUMNNAME_ReqFactCE, Boolean.valueOf(ReqFactCE));
	}

	/** Get Require Invoicing Before Executing Medical Appointment.
		@return Require Invoicing Before Executing Medical Appointment	  */
	public boolean isReqFactCE () 
	{
		Object oo = get_Value(COLUMNNAME_ReqFactCE);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requiring receipts before applying indicators.
		@param ReqFactIndH 
		Indicates if necessary check requests for outpatient services, requests for service and execution of medications medical appointment before applying
	  */
	public void setReqFactIndH (boolean ReqFactIndH)
	{
		set_Value (COLUMNNAME_ReqFactIndH, Boolean.valueOf(ReqFactIndH));
	}

	/** Get Requiring receipts before applying indicators.
		@return Indicates if necessary check requests for outpatient services, requests for service and execution of medications medical appointment before applying
	  */
	public boolean isReqFactIndH () 
	{
		Object oo = get_Value(COLUMNNAME_ReqFactIndH);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Require Invoice in Zero for Bussines Partner Exempt.
		@param ReqFactZero Require Invoice in Zero for Bussines Partner Exempt	  */
	public void setReqFactZero (boolean ReqFactZero)
	{
		set_Value (COLUMNNAME_ReqFactZero, Boolean.valueOf(ReqFactZero));
	}

	/** Get Require Invoice in Zero for Bussines Partner Exempt.
		@return Require Invoice in Zero for Bussines Partner Exempt	  */
	public boolean isReqFactZero () 
	{
		Object oo = get_Value(COLUMNNAME_ReqFactZero);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set RutaInterfaseLaserFiche.
		@param RutaInterfaseLaserFiche RutaInterfaseLaserFiche	  */
	public void setRutaInterfaseLaserFiche (String RutaInterfaseLaserFiche)
	{
		set_Value (COLUMNNAME_RutaInterfaseLaserFiche, RutaInterfaseLaserFiche);
	}

	/** Get RutaInterfaseLaserFiche.
		@return RutaInterfaseLaserFiche	  */
	public String getRutaInterfaseLaserFiche () 
	{
		return (String)get_Value(COLUMNNAME_RutaInterfaseLaserFiche);
	}

	/** Set Pixels - Minute Scale.
		@param ScaleMin 
		Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin)
	{
		set_Value (COLUMNNAME_ScaleMin, Integer.valueOf(ScaleMin));
	}

	/** Get Pixels - Minute Scale.
		@return Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ScaleMin);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set See closed encounters.
		@param seeCE See closed encounters	  */
	public void setseeCE (boolean seeCE)
	{
		set_Value (COLUMNNAME_seeCE, Boolean.valueOf(seeCE));
	}

	/** Get See closed encounters.
		@return See closed encounters	  */
	public boolean isseeCE () 
	{
		Object oo = get_Value(COLUMNNAME_seeCE);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Server for Report Download.
		@param Servidor_Reportes 
		Server from which reports would be downloaded (server:port)
	  */
	public void setServidor_Reportes (String Servidor_Reportes)
	{
		set_Value (COLUMNNAME_Servidor_Reportes, Servidor_Reportes);
	}

	/** Get Server for Report Download.
		@return Server from which reports would be downloaded (server:port)
	  */
	public String getServidor_Reportes () 
	{
		return (String)get_Value(COLUMNNAME_Servidor_Reportes);
	}

	/** Set Takes / Confirm.
		@param SurteConfirma 
		Takes / Confirm
	  */
	public void setSurteConfirma (boolean SurteConfirma)
	{
		set_Value (COLUMNNAME_SurteConfirma, Boolean.valueOf(SurteConfirma));
	}

	/** Get Takes / Confirm.
		@return Takes / Confirm
	  */
	public boolean isSurteConfirma () 
	{
		Object oo = get_Value(COLUMNNAME_SurteConfirma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Deliver.
		@param Surtir 
		If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir)
	{
		set_Value (COLUMNNAME_Surtir, Boolean.valueOf(Surtir));
	}

	/** Get Deliver.
		@return If the product or service will deliver internally
	  */
	public boolean isSurtir () 
	{
		Object oo = get_Value(COLUMNNAME_Surtir);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set TEST.
		@param TEST TEST	  */
	public void setTEST (boolean TEST)
	{
		set_Value (COLUMNNAME_TEST, Boolean.valueOf(TEST));
	}

	/** Get TEST.
		@return TEST	  */
	public boolean isTEST () 
	{
		Object oo = get_Value(COLUMNNAME_TEST);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Time_Eleg.
		@param Time_Eleg Time_Eleg	  */
	public void setTime_Eleg (Timestamp Time_Eleg)
	{
		set_Value (COLUMNNAME_Time_Eleg, Time_Eleg);
	}

	/** Get Time_Eleg.
		@return Time_Eleg	  */
	public Timestamp getTime_Eleg () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Time_Eleg);
	}

	/** Tipov_Eleg AD_Reference_ID=1200498 */
	public static final int TIPOV_ELEG_AD_Reference_ID=1200498;
	/** Ranged = R */
	public static final String TIPOV_ELEG_Ranged = "R";
	/** Fixed = F */
	public static final String TIPOV_ELEG_Fixed = "F";
	/** Set Tipov_Eleg.
		@param Tipov_Eleg Tipov_Eleg	  */
	public void setTipov_Eleg (String Tipov_Eleg)
	{

		if (Tipov_Eleg == null || Tipov_Eleg.equals("R") || Tipov_Eleg.equals("F")); else throw new IllegalArgumentException ("Tipov_Eleg Invalid value - " + Tipov_Eleg + " - Reference_ID=1200498 - R - F");		set_Value (COLUMNNAME_Tipov_Eleg, Tipov_Eleg);
	}

	/** Get Tipov_Eleg.
		@return Tipov_Eleg	  */
	public String getTipov_Eleg () 
	{
		return (String)get_Value(COLUMNNAME_Tipov_Eleg);
	}

	/** Set Vademecum.
		@param Vademecum Vademecum	  */
	public void setVademecum (String Vademecum)
	{
		set_Value (COLUMNNAME_Vademecum, Vademecum);
	}

	/** Get Vademecum.
		@return Vademecum	  */
	public String getVademecum () 
	{
		return (String)get_Value(COLUMNNAME_Vademecum);
	}

	/** Set Validate Medical appointment.
		@param ValidaCita Validate Medical appointment	  */
	public void setValidaCita (boolean ValidaCita)
	{
		set_Value (COLUMNNAME_ValidaCita, Boolean.valueOf(ValidaCita));
	}

	/** Get Validate Medical appointment.
		@return Validate Medical appointment	  */
	public boolean isValidaCita () 
	{
		Object oo = get_Value(COLUMNNAME_ValidaCita);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Validate Medical appointment Diagnostic.
		@param validaDiagCita Validate Medical appointment Diagnostic	  */
	public void setvalidaDiagCita (boolean validaDiagCita)
	{
		set_Value (COLUMNNAME_validaDiagCita, Boolean.valueOf(validaDiagCita));
	}

	/** Get Validate Medical appointment Diagnostic.
		@return Validate Medical appointment Diagnostic	  */
	public boolean isvalidaDiagCita () 
	{
		Object oo = get_Value(COLUMNNAME_validaDiagCita);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Vigencia AD_Reference_ID=1200427 */
	public static final int VIGENCIA_AD_Reference_ID=1200427;
	/** Six Months = 0M */
	public static final String VIGENCIA_SixMonths = "0M";
	/** Year = 1Y */
	public static final String VIGENCIA_Year = "1Y";
	/** Two Years = 2Y */
	public static final String VIGENCIA_TwoYears = "2Y";
	/** Five Years = 5Y */
	public static final String VIGENCIA_FiveYears = "5Y";
	/** Set Validity.
		@param Vigencia Validity	  */
	public void setVigencia (String Vigencia)
	{

		if (Vigencia == null || Vigencia.equals("0M") || Vigencia.equals("1Y") || Vigencia.equals("2Y") || Vigencia.equals("5Y")); else throw new IllegalArgumentException ("Vigencia Invalid value - " + Vigencia + " - Reference_ID=1200427 - 0M - 1Y - 2Y - 5Y");		set_Value (COLUMNNAME_Vigencia, Vigencia);
	}

	/** Get Validity.
		@return Validity	  */
	public String getVigencia () 
	{
		return (String)get_Value(COLUMNNAME_Vigencia);
	}

	/** Set CodiceUtente for winlab.
		@param WinLab_CodiceUtente CodiceUtente for winlab	  */
	public void setWinLab_CodiceUtente (String WinLab_CodiceUtente)
	{
		set_Value (COLUMNNAME_WinLab_CodiceUtente, WinLab_CodiceUtente);
	}

	/** Get CodiceUtente for winlab.
		@return CodiceUtente for winlab	  */
	public String getWinLab_CodiceUtente () 
	{
		return (String)get_Value(COLUMNNAME_WinLab_CodiceUtente);
	}

	/** Set Winlab Mirth Http Request.
		@param WinlabMirthHttpRequest 
		Winlab Mirth Http Request
	  */
	public void setWinlabMirthHttpRequest (String WinlabMirthHttpRequest)
	{
		set_Value (COLUMNNAME_WinlabMirthHttpRequest, WinlabMirthHttpRequest);
	}

	/** Get Winlab Mirth Http Request.
		@return Winlab Mirth Http Request
	  */
	public String getWinlabMirthHttpRequest () 
	{
		return (String)get_Value(COLUMNNAME_WinlabMirthHttpRequest);
	}

	/** Set Winlab Route.
		@param WINLAB_R Winlab Route	  */
	public void setWINLAB_R (String WINLAB_R)
	{
		set_Value (COLUMNNAME_WINLAB_R, WINLAB_R);
	}

	/** Get Winlab Route.
		@return Winlab Route	  */
	public String getWINLAB_R () 
	{
		return (String)get_Value(COLUMNNAME_WINLAB_R);
	}
}