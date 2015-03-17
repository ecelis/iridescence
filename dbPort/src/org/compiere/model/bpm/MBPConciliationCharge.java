package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.BeanActPacienteInd;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MPlanMed;
import org.compiere.model.MPlanMedLine;
import org.compiere.model.ModelError;
import org.compiere.model.X_EXME_ActPacienteInd;
import org.compiere.model.X_EXME_ActPacienteIndH;
import org.compiere.model.X_EXME_PlanMed;
import org.compiere.model.X_EXME_PlanMedLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Charge vs Medication
 * 
 * @author twry
 * 
 */
public class MBPConciliationCharge {

	/** Logger */
	protected transient static CLogger slog = CLogger
			.getCLogger(MBPConciliationCharge.class);
	/** Contexto */
	private Properties ctx = null;
	/** Nombre de transaccion */
	private String trxName = null;
	/** Id de cuenta paciente */
	private int ctaPacID = 0;
	/** Listado de act paciente */
	private List<BeanActPacienteInd> listAplicadas = new ArrayList<BeanActPacienteInd>();
	/** Listado de errores */
	private List<ModelError> lstErrors = new ArrayList<ModelError>();

	/**
	 * Constructor
	 */
	public MBPConciliationCharge() {
		super();
	}

	/**
	 * Cuenta paciente
	 * 
	 * @param pCtx
	 * @param pCtaPacID
	 * @param pTrxName
	 */
	public void setCtaPac(final Properties pCtx, final int pCtaPacID,
			final String pTrxName) {
		ctaPacID = pCtaPacID;
		ctx = pCtx;
		trxName = pTrxName;
	}

	/**
	 * Listado de actividades del paciente
	 * 
	 * @return
	 */
	public List<BeanActPacienteInd> getPatientCharges(
			final StringBuilder sqlMed, final StringBuilder sqlServ) {
		listAplicadas.clear();

		// medicamentos
		if (sqlMed != null) {
			List<BeanActPacienteInd> lstMed = getPrescriptionsLst(
					getSqlMedicamentos(), sqlMed.toString(),
					new Object[] { ctaPacID });
			listAplicadas.addAll(lstMed);
		}
		// otros
		if (sqlServ != null) {
			List<BeanActPacienteInd> lstOtrs = getPrescriptionsLst(
					getSqlOtrs(), sqlServ.toString(), new Object[] { ctaPacID });
			listAplicadas.addAll(lstOtrs);
		}

		return listAplicadas;
	}

	/**
	 * Consulta sobre los medicamentos
	 * 
	 * @return
	 */
	private String getSqlMedicamentos() {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT api.*, cpd.EXME_CtaPacDet_ID, pml.EXME_PlanMedLine_ID, p.M_Product_ID AS Prod1, ")
				.append("        pml.ApliedDate AS AplicadaExp, cpd.Datedelivered AS AplicadaChar,               ")
				.append("        'eMAR' AS Process, cpdt.ErrorMsg AS Razon,                                      ")
				.append("        cpdc.ErrorMsg AS Razon2, cpdl.ErrorMsg AS Razon3, cpdm.ErrorMsg AS Razon4,      ")
				.append("        p.Value AS ProdValue, p.Name || '/' || p.Description AS ProdDesc,               ")
				.append("        i.Value AS ProdServ, pml.EXME_PlanMed_ID                                        ")
				.append(" FROM EXME_PlanMed pm ")
				.append(" INNER JOIN EXME_PlanMedLine      pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID AND pml.IsActive = 'Y' ")
				.append(" INNER JOIN M_Product               p ON pm.M_Product_ID = p.M_Product_ID  ")
				.append(" LEFT  JOIN EXME_Intervencion       i ON p.EXME_Intervencion_ID = i.EXME_Intervencion_ID AND i.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_ActPacienteIndH apih ON pml.EXME_ActPacienteIndH_ID = apih.EXME_ActPacienteIndH_ID AND apih.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_ActPacienteInd   api ON apih.EXME_ActPacienteIndH_ID = api.EXME_ActPacienteIndH_ID AND api.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_CtaPacDet        cpd ON api.EXME_ActPacienteInd_ID = cpd.EXME_ActPacienteInd_ID AND cpd.IsActive = 'Y' ")
				// .append(" LEFT  JOIN EXME_CtaPacDet        cpd ON pm.M_Product_ID = cpd.M_Product_ID AND cpd.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdt ON api.EXME_ActPacienteInd_ID = cpdt.EXME_ActPacienteInd_ID   AND cpdt.IsActive = 'Y' ")
				// CONTIENE TODOS LOS CAMPOS
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdc ON api.EXME_ActPacienteIndH_ID = cpdc.EXME_ActPacienteIndH_ID AND cpdc.IsActive = 'Y' AND cpdc.EXME_ActPacienteInd_ID IS NULL ")
				// SOLO EL HEADER
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdl ON pml.EXME_PlanMedLine_ID = cpdl.EXME_PlanMedLine_ID         AND cpdl.IsActive = 'Y' AND cpdl.EXME_ActPacienteIndH_ID IS NULL AND cpdl.EXME_ActPacienteInd_ID IS NULL ")
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdm ON pm.EXME_PlanMed_ID = cpdm.EXME_PlanMed_ID                  AND cpdm.IsActive = 'Y' AND cpdm.EXME_PlanMedLine_ID IS NULL     AND cpdm.EXME_ActPacienteIndH_ID IS NULL AND cpdm.EXME_ActPacienteInd_ID IS NULL ")

				.append(" WHERE pm.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PlanMed.Table_Name, "pm"))
				.append(" AND pm.EXME_CtaPac_ID = ? ")
				.append(" AND pml.Estatus = 'AD' ");
		return sql.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String getSqlOtrs() {
		StringBuilder sql = new StringBuilder()
				.append(" SELECT api.*, cpd.EXME_CtaPacDet_ID, 0 AS EXME_PlanMedLine_ID, p.M_Product_ID AS Prod1, ")
				.append("        api.Datedelivered AS AplicadaExp, cpd.Datedelivered AS AplicadaChar,    ")
				.append("        'Studies Order Completion' AS Process, cpdt.ErrorMsg AS Razon,          ")
				.append("        cpdc.ErrorMsg AS Razon2,  ' ' AS Razon3, ' ' AS Razon4,                 ")
				.append("        p.Value AS ProdValue, p.Name || '/' || p.Description AS ProdDesc,       ")
				.append("        i.Value AS ProdServ                                                     ")
				.append(" FROM EXME_ActPacienteIndH apih ")
				.append(" INNER JOIN EXME_ActPacienteInd     api ON apih.EXME_ActPacienteIndH_ID = api.EXME_ActPacienteIndH_ID AND api.IsActive = 'Y' ")
				// Todos los servicios
				.append(" INNER JOIN M_Product                 p ON api.M_Product_ID = p.M_Product_ID AND p.IsActive = 'Y' AND p.ProductClass <> 'DG'  AND P.PRODUCTTYPE = 'S' AND p.ProductClass <> 'MS' ")
				.append(" LEFT  JOIN EXME_Intervencion         i ON p.EXME_Intervencion_ID = i.EXME_Intervencion_ID AND i.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_CtaPacDet          cpd ON api.EXME_ActPacienteInd_ID = cpd.EXME_ActPacienteInd_ID AND cpd.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdt ON api.EXME_ActPacienteInd_ID = cpdt.EXME_ActPacienteInd_ID AND cpdt.IsActive = 'Y' ")
				.append(" LEFT  JOIN EXME_ActPacienteIndCgo cpdc ON api.EXME_ActPacienteIndH_ID = cpdc.EXME_ActPacienteIndH_ID AND cpdc.IsActive = 'Y' AND cpdc.EXME_ActPacienteInd_ID IS NULL ")
				.append(" WHERE apih.IsActive = 'Y'    ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_ActPacienteIndH.Table_Name, "apih"))
				.append(" AND apih.EXME_CtaPac_ID = ? ")
				.append(" AND apih.IsService  = 'Y'   ")
				.append(" AND api.Estatus     =       ")
				.append(DB
						.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_CompletedService))
				.append(" AND apih.EXME_ActPacienteIndH_ID NOT IN ( ")
				.append("      SELECT COALESCE(EXME_ActPacienteIndH_ID,0) FROM EXME_PlanMedLine ")
				.append("      WHERE IsActive = 'Y'   ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_PlanMedLine.Table_Name))
				.append("     )                       ");
		return sql.toString();
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<BeanActPacienteInd> getPrescriptionsLst(final String sql,
			final String where, final Object[] params) {
		final List<BeanActPacienteInd> lista = new ArrayList<BeanActPacienteInd>();

		if (ctx == null) {
			return lista;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString() + " " + where, null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BeanActPacienteInd obj = new BeanActPacienteInd(ctx, rs,
						trxName);
				obj.setEXME_CtaPacDet_ID(rs.getInt("EXME_CtaPacDet_ID"));
				obj.setEXME_PlanMedLine_ID(rs.getInt("EXME_PlanMedLine_ID"));
				obj.setAplicadaExp(rs.getTimestamp("AplicadaExp"));
				obj.setAplicadaChar(rs.getTimestamp("AplicadaChar"));
				obj.setProcess(rs.getString("Process"));
				obj.setProdDescript(rs.getString("ProdDesc"));
				obj.setProdValue(rs.getString("ProdValue"));
				obj.setProdCPTHCPCS(rs.getString("ProdServ"));
				if (obj.getEXME_ActPacienteInd_ID() <= 0) {
					obj.setM_Product_ID(rs.getInt("Prod1"));
					obj.setPlanMed(new MPlanMed(ctx, rs
							.getInt("EXME_PlanMed_ID"), trxName));
					obj.setPMLine(new MPlanMedLine(ctx, rs
							.getInt("EXME_PlanMedLine_ID"), trxName));
				}

				String motivo = StringUtils.EMPTY;
				if (rs.getString("Razon") != null
						&& !StringUtils.isEmpty(rs.getString("Razon").trim())) {
					motivo = rs.getString("Razon");
				} else if (rs.getString("Razon2") != null
						&& !StringUtils.isEmpty(rs.getString("Razon2").trim())) {
					motivo = rs.getString("Razon2");
				} else if (rs.getString("Razon3") != null
						&& !StringUtils.isEmpty(rs.getString("Razon3").trim())) {
					motivo = rs.getString("Razon3");
				} else if (rs.getString("Razon4") != null
						&& !StringUtils.isEmpty(rs.getString("Razon4").trim())) {
					motivo = rs.getString("Razon4");
				}

				obj.setRazon(motivo);

				lista.add(obj);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getPrescriptionsLst" + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Test
	 * 
	 * @param pCtx
	 * @param pCtaPacID
	 * @param pTrxName
	 * @return
	 */
	public static List<BeanActPacienteInd> getPatientChargesTest(
			final Properties pCtx, final int pCtaPacID, final String where,
			final String pTrxName) {
		MBPConciliationCharge charge = new MBPConciliationCharge();
		charge.setCtaPac(pCtx, pCtaPacID, pTrxName);
		// medicamentos
		List<BeanActPacienteInd> lstMed = charge.getPrescriptionsLst(
				charge.getSqlMedicamentos(), where, new Object[] { pCtaPacID });
		charge.getListAplicadas().addAll(lstMed);
		// otros
		List<BeanActPacienteInd> lstOtrs = charge.getPrescriptionsLst(
				charge.getSqlOtrs(), where, new Object[] { pCtaPacID });
		charge.getListAplicadas().addAll(lstOtrs);

		return charge.getListAplicadas();
	}

	/**
	 * Creacion de cargos en batch tanto para servicios como medicamentos
	 * planeados
	 * 
	 * @param lst
	 * @return
	 */
	public boolean save(final List<BeanActPacienteInd> lst, final String trxName) {
		// Se resta cada vez que se cargo un producto
		boolean count = true;
		// Ejecuta la carga de productos
		CreateCharge mCreateCharge = new CreateCharge(ctx);
		// Ejecuta la creacion del expediente y la carga del medicamento
		MedicationSave mMedicationSave = new MedicationSave(ctx,
				new MEXMECtaPac(ctx, ctaPacID, trxName), trxName);

		// Itera para crear el cargo
		for (BeanActPacienteInd bean : lst) {

			// Programaciones medicas
			if (bean.getEXME_ActPacienteInd_ID() <= 0
					&& bean.getPlanMed() != null && bean.getPMLine() != null) {
				// Cargo y actividad paciente
				if (mMedicationSave.insertActPacIndH(bean.getPlanMed(),
						bean.getPMLine()) != null) {
					count = false;
					break;
				}
			} else if (bean.getEXME_ActPacienteInd_ID() > 0) {
				// Cargo
				if (mCreateCharge.insertActPacIndCharges(bean, false, false, trxName)) {//Lama: cargos 2014
					// Los servicios siempre tiene una actividad paciente
					count = false;
					break;
				}
			}
		}

		// aplica los medicamentos/studios con expdiente previo
		if (count && !mCreateCharge.getLstCharges().isEmpty()) {//Lama: cargos 2014
			mCreateCharge.insertAllCharges(mCreateCharge.getLstCharges(), /* true,
					false, false, false,*/ trxName);
		} else {
			lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"captCargo.isEmpty"));
		}

		lstErrors.addAll(mCreateCharge.getErrores());
		lstErrors.addAll(mMedicationSave.getErrores());
		return count;
	}

	public List<BeanActPacienteInd> getListAplicadas() {
		return listAplicadas;
	}

	public void setListAplicadas(final List<BeanActPacienteInd> listAplicadas) {
		this.listAplicadas = listAplicadas;
	}

	public List<ModelError> getLstErrors() {
		return lstErrors;
	}

	public void setLstErrors(final List<ModelError> lstErrors) {
		this.lstErrors = lstErrors;
	}
}
