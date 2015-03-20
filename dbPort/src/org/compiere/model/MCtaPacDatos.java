/*
 * Created on 22-mar-2005
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * Modelo de Cuenta Paciente Datos <b>Modificado: </b> $Author: taniap $
 * <p>
 * <b>En :</b> $Date: 2006/08/16 22:30:19 $
 * <p>
 * 
 * @author Lorena Lama
 * @version $Revision: 1.2 $
 */
public class MCtaPacDatos extends X_EXME_CtaPacDatos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MCtaPacDatos.class);

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MCtaPacDatos(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 * @param id
	 * @param trxName
	 */
	public MCtaPacDatos(Properties ctx, int EXME_CtaPacDatos_ID, String trxName) {
		super(ctx, EXME_CtaPacDatos_ID, trxName);
	}

	/**
	 * Initial diagnostic
	 */
	private MDiagnostico diagnosticoIni = null;
	/**
	 * Final diagnostic
	 */
	private MDiagnostico diagnosticoFin = null;
	/**
	 * Intervention
	 */
//	private MIntervencion intervencion = null;
	/**
	 * Patient
	 */
	private MEXMEPaciente paciente = null;
	/**
	 * Name of initial diagnostic
	 */
	private String diagIniName = null;
	/**
	 * Name of final diagnostic
	 */
	private String diagFinName = null;
	/**
	 * Descriptive initial diagnostic
	 */
	private String diagnosisIniText = null;
	/**
	 * Descriptive final diagnostic
	 */
	private String diagnosisFinText = null;
	/**
	 * Activity from patient account
	 */
	private int actPacienteID = 0;
	/**
	 * Patient account
	 */
	private MEXMECtaPac ctaPac = null;

	/**
	 * Returns patient account's complentary data
	 * 
	 * @param ctaPac
	 *            {@link MEXMECtaPac} Patient Account
	 * @return {@link MCtaPacDatos}
	 */
	public static MCtaPacDatos getCtaPacD(MEXMECtaPac ctaPac) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MCtaPacDatos ctaPacDatos = null;

		try {
			sql.append(" SELECT EXME_CtaPacDatos.*, ");
			sql.append(" diagIni.Name as diagIni ");
			sql.append(" , diagFin.Name as diagFin ");
			sql.append(" FROM EXME_CtaPacDatos ");
			sql.append(" LEFT JOIN EXME_Diagnostico diagIni ON EXME_CtaPacDatos.EXME_DiagnosticoIni_ID = diagIni.EXME_Diagnostico_ID");
			sql.append(" LEFT JOIN EXME_Diagnostico diagFin ON EXME_CtaPacDatos.EXME_DiagnosticoFin_ID = diagFin.EXME_Diagnostico_ID");
			sql.append(" WHERE EXME_CtaPacDatos.IsActive = 'Y' ");
			sql.append(" AND EXME_CtaPacDatos.EXME_CtaPac_ID = ? ");
			sql.append(" AND EXME_CtaPacDatos.EXME_Paciente_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctaPac.getCtx(), " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), ctaPac.get_TrxName());
			pstmt.setInt(1, ctaPac.getEXME_CtaPac_ID());
			pstmt.setInt(2, ctaPac.getEXME_Paciente_ID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				ctaPacDatos = new MCtaPacDatos(ctaPac.getCtx(), rs, ctaPac.get_TrxName());
				ctaPacDatos.setDiagIniName(rs.getString("diagIni"));
				ctaPacDatos.setDiagFinName(rs.getString("diagFin"));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ctaPacDatos;
	}

	public void setPaciente(MEXMEPaciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * Patient
	 */
	public MEXMEPaciente getPaciente() {
		if (paciente == null && getEXME_Paciente_ID() > 0) {
			paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		return paciente;
	}

	/**
	 * Initial diagnostic
	 */
	public MDiagnostico getDiagnosticoIni() {
		if (diagnosticoIni == null && getEXME_DiagnosticoIni_ID() > 0) {
			diagnosticoIni = new MDiagnostico(getCtx(), getEXME_DiagnosticoIni_ID(), get_TrxName());
		}
		return diagnosticoIni;
	}

	/**
	 * Final diagnostic
	 */
	public MDiagnostico getDiagnosticoFin() {
		if (diagnosticoFin == null && getEXME_DiagnosticoFin_ID() > 0) {
			diagnosticoFin = new MDiagnostico(getCtx(), getEXME_DiagnosticoFin_ID(), get_TrxName());
		}
		return diagnosticoFin;
	}

	/**
	 * Intervention
	 */
//	public MIntervencion getIntervencion() {
//		if (intervencion == null && getEXME_Intervencion_ID() > 0) {
//			intervencion = new MIntervencion(getCtx(), getEXME_Intervencion_ID(), get_TrxName());
//		}
//		return intervencion;
//	}

	/**
	 * Patient Account
	 */
	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null && getEXME_CtaPac_ID() > 0) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

	/**
	 * Activity from patient account
	 */
	public int getActPacienteID() {
		if (actPacienteID <= 0 && getEXME_CtaPac_ID() > 0) {
			final MEXMEActPaciente actPac = MEXMEActPaciente.getActPaciente(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());
			if (actPac != null) {
				setActPacienteID(actPac.getEXME_ActPaciente_ID());
			}
		}
		return actPacienteID;
	}

	public void setActPacienteID(int actPacienteID) {
		this.actPacienteID = actPacienteID;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			success = saveDiag(newRecord);
		}
		return success;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//FIXME: Debe determinarse con Kim si el Type Of Bill por defecto
		// en su ultimo caracter se puede configurar por organizacion, 
		// o se mantiene el resultado de esta consulta como defecto.
		if (newRecord && getEXME_TypeOfBill_ID()<= 0 ) {
			setEXME_TypeOfBill_ID(DB.getSQLValue(null, "SELECT EXME_TypeOfBill_ID FROM EXME_TypeOfBill WHERE Sequence = 3 and Code = 1  ") );
		}
		return Boolean.TRUE;
	}

	/**
	 * Save/Update diagnostic of patient
	 * 
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean saveDiag(boolean newRecord) {
		boolean saved;
		if (getEXME_CtaPac_ID() <= 0 || getActPacienteID() <= 0) { // find actPaciente
			saved = false;
		} else {
			// if diagnosis code has changed or diagnosis descriptions has been assigned
			// initial diagnostic -
			boolean saveIniDiag = getEXME_DiagnosticoIni_ID() > 0 || StringUtils.isNotBlank(diagnosisIniText);
			// final diagnostic -
			boolean saveFinDiag = getEXME_DiagnosticoFin_ID() > 0 || StringUtils.isNotBlank(diagnosisFinText);
			if (saveIniDiag) {
				saved = MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), COLUMNNAME_EXME_DiagnosticoIni_ID,
						diagnosisIniText, null);
			} else {
				saved = true;
			}
			if (saved && saveFinDiag) {
				saved = MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), COLUMNNAME_EXME_DiagnosticoFin_ID,
						diagnosisFinText, null);
			}
		}
		return saved;
	}
	
	@Deprecated
	public boolean saveDiag(int diagNewID, String diagColumn, String diagNewText) {
		return true;
	}

	/**
	 * Descriptive initial diagnostic
	 */
	public String getDiagnosisIniText() {
		if (diagnosisIniText == null && getEXME_CtaPacDatos_ID() > 0) {
			// The diagnosis text is not saved in this table
			final MActPacienteDiag actdiag = MActPacienteDiag.get(getCtx(), get_ID(), get_Table_ID(),
					getActPacienteID(), getEXME_DiagnosticoIni_ID(), COLUMNNAME_EXME_DiagnosticoIni_ID, get_TrxName());
			if (actdiag != null) {
				setDiagnosisIniText(actdiag.getDiagnosis() == null ? "" : actdiag.getDiagnosis());
			}
		}
		return diagnosisIniText;
	}

	public void setDiagnosisIniText(String diagnosisIniText) {
		this.diagnosisIniText = diagnosisIniText;
	}

	/**
	 * Descriptive final diagnostic
	 */
	public String getDiagnosisFinText() {
		if (diagnosisFinText == null && getEXME_CtaPacDatos_ID() > 0) {
			// The diagnosis text is not saved in this table
			final MActPacienteDiag actdiag = MActPacienteDiag.get(getCtx(), get_ID(), get_Table_ID(),
					getActPacienteID(), getEXME_DiagnosticoFin_ID(), COLUMNNAME_EXME_DiagnosticoFin_ID, get_TrxName());
			if (actdiag != null) {
				setDiagnosisFinText(actdiag.getDiagnosis() == null ? "" : actdiag.getDiagnosis());
			}
		}
		return diagnosisFinText;
	}

	public void setDiagnosisFinText(String diagnosisFinText) {
		this.diagnosisFinText = diagnosisFinText;
	}

	/**
	 * Name of initial diagnostic
	 */
	public String getDiagIniName() {
		return diagIniName;
	}

	public void setDiagIniName(String diagIniName) {
		this.diagIniName = diagIniName;
	}

	/**
	 * Name of final diagnostic
	 */
	public String getDiagFinName() {
		return diagFinName;
	}

	public void setDiagFinName(String diagFinName) {
		this.diagFinName = diagFinName;
	}
	
	
	

}
