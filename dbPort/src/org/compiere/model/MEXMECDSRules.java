package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MEXMECDSRules extends X_EXME_CDS_Rules {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMECDSRules.class);

	public MEXMECDSRules(Properties ctx, int EXME_CDS_Rules_ID, String trxName) {
		super(ctx, EXME_CDS_Rules_ID, trxName);
	}

	public MEXMECDSRules(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	public static List<MEXMECDSRules> getCDSRulesLst(Properties ctx, String trxName, int cdsID){
		return getCDSRulesLst(ctx, trxName, cdsID, true);
	}
	/**
	 * 
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECDSRules> getCDSRulesLst(Properties ctx, String trxName, int cdsID, boolean onlyIsActive) {
//		List<MEXMECDSRules> list = new ArrayList<MEXMECDSRules>();

		if (ctx == null) {
			return new ArrayList<MEXMECDSRules>();
		}

//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT * FROM EXME_CDS_Rules ");
//		sql.append(" WHERE EXME_CDS_ID = ? ");
//		if(onlyIsActive){
//			sql.append(" AND IsActive = 'Y' ");
//		}
//		
//		/*
//		 * mvrodriguez se agrega comparisonoperator que es necesario para el armado de la condicion
//		 * para el armado de la condicion de la regla
//		 */
//		sql.append(" ORDER BY SeqNo, ruleType, comparisonoperator ");// LAMA: se agrega secuencia
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);//TRANSACCION!!!
//			pstmt.setInt(1, cdsID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MEXMECDSRules cdsRules = new MEXMECDSRules(ctx, rs, trxName);
//				list.add(cdsRules);
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getCDSRulesLst", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
		return new Query(ctx, Table_Name, " EXME_CDS_ID=?", trxName)//
			.setParameters(cdsID)//
			.setOrderBy(" SeqNo, ruleType, comparisonoperator ")//
			.setOnlyActiveRecords(onlyIsActive)//
			.addAccessLevelSQL(true)//
			.list();
	}

	/**
	 * Eval if not exists a Note link to the Rule
	 * 
	 * @return true (to avoid delete)
	 */
	@Override
	protected boolean beforeDelete() {
		// MNote notas;
		// boolean error = false;
		final String adLanguage = Env.getAD_Language(getCtx());
		try {
			if (MNote.get(getCtx(), getEXME_CDS_ID(), null) != null) {
				log.log(Level.SEVERE, "Cannot delete " + this.toString() + " there is an AD_Note related");
				log.saveError("CDSRulesCannotDelete", Msg.getMsg(adLanguage, "CDSRules"));
				return false;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			log.saveError("CDSRulesFailDeleteMessage", Msg.getMsg(adLanguage, "CDSRules"));
			return false;
		}
		// if (error) {
		// log.saveError(Msg.getMsg(Env.getAD_Language(getCtx()), "CDSRulesFailDeleteMessage"), Msg.getMsg(Env
		// .getAD_Language(getCtx()), "CDSRules"));
		// return false;
		// } else if (notas != null) {
		// log.saveError(Msg.getMsg(Env.getAD_Language(getCtx()), "CDSRulesCannotDelete"), Msg.getMsg(Env
		// .getAD_Language(getCtx()), "CDSRules"));
		// return false;
		// }
		return true;
	}

	/**
	 * Eval if all the information need to eval tu Rule is registered by user
	 * 
	 * @return true (to avoid save)
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		boolean retVal = true;

		HashMap<String, String> msg = validateBeforeSave();
		if (!msg.isEmpty()) {
			Iterator<Entry<String, String>> itEntries = msg.entrySet().iterator();
			while (itEntries.hasNext()) {
				Entry<String, String> element = itEntries.next();
				log.saveError(element.getKey(), element.getValue());
			}
			return false;
		}

		return retVal;
	}

	public HashMap<String, String> validateBeforeSave() {

		HashMap<String, String> retValue = new HashMap<String, String>();
		try {
			String adLanguage = Env.getAD_Language(getCtx());
			// Si existe registro pero sin tipo de regla
			if (getRuleType() == null) {
				retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveRuleType"), Msg.getMsg(adLanguage, "CDSRules"));
				return retValue;
			}
			/*
			 * Bloque Datos Demograficos del Paciente
			 */
			if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientDemographics)) {
				// Si no se escojio campo demografico
				if (getDemoField() == null) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveDemoField"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Age) && getIntValue() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveAge"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Nacionality) && getEXME_Nacionalidad_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveNacionality"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Race) && getEXME_Razas_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveRace"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_EthnicGroup) && getEXME_GpoEtnico_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveEthnicGroup"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Sex)
					&& (getCharValue() == null || getCharValue().equalsIgnoreCase(""))) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveSex"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Weight) && getDoubleValue().compareTo(BigDecimal.ZERO) <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveWeight"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				} else if (getDemoField().equalsIgnoreCase(MEXMECDSRules.DEMOFIELD_Height) && getDoubleValue().compareTo(BigDecimal.ZERO) <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveHeight"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				}
			} else {
				// LAMA: quitar valor de campo demografico, si no selecciono ese tipo de regla
				if (!StringUtils.isBlank(getDemoField())) {
					setDemoField(null);
				}
				/*
				 * Bloque Condición Médica del Paciente
				 */
				if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicalCondition) && getEXME_DiagnosticoConMed_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveMedicalCondition"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				}
				/*
				 * Bloque Diagnósticos del Paciente
				 */
				else if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_SpecificPatientDiagnostic) && getEXME_Diagnostico_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSavePatientDiagnostic"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				}
				/*
				 * Bloque Medicamentos prescritos del Paciente
				 */
				else if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_MedicationList) && getM_Product_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveMedicationList"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				}
				/*
				 * Alergias
				 */
				else if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_PatientAllergies) && getEXME_SActiva_ID() <= 0) {
					retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSavePatientAllergies"), Msg.getMsg(adLanguage, "CDSRules"));
					return retValue;
				}

				/*
				 * Bloque observaciones de estudios del Paciente
				 */
				else if (getRuleType().equalsIgnoreCase(MEXMECDSRules.RULETYPE_LaboratoryTestResults)) {
					if (getEXME_Loinc_ID() <= 0) {
						retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveDiagnosticTestLOINC"), Msg.getMsg(adLanguage, "CDSRules"));
						return retValue;
					} else if (getDoubleValue().compareTo(BigDecimal.ZERO) <= 0) {
						retValue.put(Msg.getMsg(adLanguage, "CDSRulesFailSaveDiagnosticTestValue"), Msg.getMsg(adLanguage, "CDSRules"));
						return retValue;
					}

				}
			}

		} catch (Exception e) {
			return retValue;
		}
		return retValue;
	}
	private Date startDate = new Date();
	private Date endDate = new Date();
	private int ageUom;
	private int patientPreference;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAgeUom() {
		return ageUom;
	}

	public void setAgeUom(int ageUom) {
		this.ageUom = ageUom;
	}

	public int getPatientPreference() {
		return patientPreference;
	}

	public void setPatientPreference(int patientPreference) {
		this.patientPreference = patientPreference;
	}
}
