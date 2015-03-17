package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEPrescription extends X_EXME_Prescription {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEPrescription.class);

	/**
	 * Constructor de MEXME_Prescription
	 * 
	 * @param ctx Propiedades
	 * @param EXME_Prescription_ID  ID de Procedencia
	 * @param trxName  Nombre de la transaccion
	 */
	public MEXMEPrescription(Properties ctx, int EXME_Prescription_ID, String trxName) {
		super(ctx, EXME_Prescription_ID, trxName);
		if (EXME_Prescription_ID == 0) {
			setDocStatus(DocAction.STATUS_Drafted);
			setDocAction(DocAction.ACTION_Complete);
			setProcessing(false); // N
			setProcessed(false); // N
			setDateOrdered(Env.getCurrentDate());
		}
	}

	/**
	 * Constructor de MEXME_Prescription
	 * 
	 * @param ctx  Propiedades
	 * @param rs Resultset con que se crea el objeto
	 * @param trxName Nombre de la transaccion
	 */
	public MEXMEPrescription(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Noelia: obtenemos todas las prescripciones activas ordenadas por fecha de
	 * creacion
	 * 
	 * @param ctx
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrescription[] getPrescription(Properties ctx, String whereClause, String trxName) {

		final List<MEXMEPrescription> resultados = new Query(ctx, MEXMEPrescription.Table_Name, whereClause, trxName)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)//
			.setOrderBy(" Created desc ").list();

		final MEXMEPrescription[] prescriptions = new MEXMEPrescription[resultados.size()];
		resultados.toArray(prescriptions);
		return prescriptions;
	}

	/**
	 * Arreglo de prescripciones
	 * @param ctx Contexto
	 * @param sql Sql que se desea se ejecute
 	 * @param trxName  Nombre de la transaccion
	 * @return Arreglo
	 */
	public static MEXMEPrescription[] getPrescription(Properties ctx, StringBuilder sql, List<?> params, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<MEXMEPrescription> resultados = new ArrayList<MEXMEPrescription>();
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params != null) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resultados.add(new MEXMEPrescription(ctx, rs, trxName));
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		final MEXMEPrescription[] prescriptions = new MEXMEPrescription[resultados.size()];
		resultados.toArray(prescriptions);
		return prescriptions;
	}

	/**
	 * Regresa las lineas de la base de datos de la prescripcion
	 */
	private List<MPlanMed> planMedLst = null;

	public List<MPlanMed> getPlanMed() {
		if (planMedLst == null) { // ***NOTA: NO QUITAR TRXNAME** //
			planMedLst = new Query(getCtx(), MPlanMed.Table_Name, " EXME_Prescription_ID=? ", get_TrxName())//
				.setParameters(getEXME_Prescription_ID())//
				.addAccessLevelSQL(true)//.setOnlyActiveRecords(true)// ?? (no)
				.list();
		}
		return planMedLst;
	}

	
	/**
	 * Consulta para el expediente apartir de la actividad del paciente
	 * (CtaPac) o bien por medico o de acuerdo a los joins y condiciones, 
	 * siempre debe existir cuenta paciente
	 * @param EXME_ActPaciente_ID
	 * @param EXME_Medico_ID
	 * @param join
	 * @param where
	 * @return StringBuilder
	 *
	public static StringBuilder sql(int EXME_ActPaciente_ID, int EXME_Medico_ID, StringBuilder join, StringBuilder where){
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT EXME_Prescription.* ");
		sql.append(" FROM  EXME_Prescription ");
		
		// Obtenemos el paciente o la cuenta paciente apartir del ActPaciente
		sql.append(" INNER JOIN EXME_ActPaciente ap ");
		sql.append("       ON EXME_Prescription.EXME_CtaPac_ID = ap.EXME_CtaPac_ID ");
		sql.append(join);
		
		// Solo las que son respaldo no se ven en ningun lugar
		if(!where.toString().contains(" WHERE ")) {
			where.append(" WHERE ");
		} else {
			where.append(" AND ");
		}
		
		where.append(" EXME_Prescription.IsActive = 'Y' ");
		where.append(" AND EXME_Prescription.DocStatus NOT IN ('NA','CO') ");
		where.append(" AND ap.EXME_Paciente_ID = ? ");

		if(EXME_ActPaciente_ID > 0) {
			where.append(" AND ap.EXME_ActPaciente_ID = ? ");
		}
			
		// Filtrado por medico si se requiere
		if(EXME_Medico_ID > 0) {
			where.append(" AND EXME_Prescription.EXME_Medico_ID = ? ");
		}
		sql.append(where);
		
		// Ordenado por fecha de creacion
		sql.append(" ORDER BY EXME_Prescription.DateOrdered DESC, ");
		sql.append(" EXME_Prescription.EXME_Prescription_ID DESC ");

		return sql;
	}*/

	/**
	 * Titulo de la prescripcion
	 * " M.D. "" Specialty "" Patient acct "
	 *
	public StringBuilder getTitulo(String titulo1, String titulo2, String titulo3){
		StringBuilder titulo = new StringBuilder();
		titulo.append(Constantes.getSdfFechaHora().format(getDateOrdered())).append(" ");
		titulo.append(getDocumentNo()).append(titulo1);
		titulo.append(getEXME_Medico().getNombre_Med()).append(titulo2);
		titulo.append(getEXME_Especialidad().getValue()).append(titulo3);
		titulo.append(getEXME_CtaPac().getDocumentNo());
		return titulo;
	}*/
	
	/**
	 * Obtiene la Prescripcion
	 * en base al EXME_PrescRX_ID
	 * 
	 * @param ctx
	 * @param exmePrescRxId
	 * @param trxName
	 * @return MEXMEPrescription
	 */
	public static MEXMEPrescription getPrescriptionByPrescRxId(final Properties ctx, final int exmePrescRxId,
		final boolean createNew, final String trxName) {
		MEXMEPrescription retValue = null;
		if (exmePrescRxId > 0) {
			retValue = new Query(ctx, Table_Name, " EXME_Prescription.EXME_PrescRX_ID=? ", trxName)//
				.setParameters(exmePrescRxId)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.first();
		}
		if (retValue == null && createNew) {
			retValue = new MEXMEPrescription(ctx, 0, trxName);
		}
		return retValue;
	}
	
	/**
	 * Pongo el estatus de completo por defecto almenos que cambie por
	 * que el plan en cuestion no se ha
	 * aplicado en su totalidad
	 * @param planMed
	 * @return
	 */
	public String completeLines(final MPlanMed planMed){
		String status = MEXMEPrescription.DOCSTATUS_Voided;

		// verifico que todas sus lineas tambien esten completas
		for (MPlanMed plan :  getPlanMed()) { // porque se consideran las inactivas?
			//encab
			if (MPlanMed.DOCSTATUS_InProgress.equals(plan.getDocStatus())
					// Lama: PRN no completar ni validar lineas, su detalle se crea infinitamente hasta
					// que se alcance la fecha final del plan (AutoStopPolicy) o sea descontinuada
					|| MPlanMed.DOCSTATUS_WaitingConfirmation.equals(planMed.getDocStatus())) {
				status = MEXMEPrescription.DOCSTATUS_InProgress;
				break;
			}
			if (MPlanMed.DOCSTATUS_Drafted.equals(plan.getDocStatus())) {
				status = MEXMEPrescription.DOCSTATUS_Drafted;
			}
			if (!status.equals(MEXMEPrescription.DOCSTATUS_Drafted)) {
				if (MPlanMed.DOCSTATUS_Completed.equals(plan.getDocStatus())) {
					status = MEXMEPrescription.DOCSTATUS_Completed;
				}

				if (!status.equals(MEXMEPrescription.DOCSTATUS_Completed)
						&& MPlanMed.DOCSTATUS_Invalid.equals(plan.getDocStatus())) {
					status = MEXMEPrescription.DOCSTATUS_Invalid;
				}
			}
		}
		
		return status;
	}
}
