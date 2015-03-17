/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEDiscontinueOrder.DiscontinueParams;
import org.compiere.model.MEXMEDiscontinueOrder.IDiscontinueOrder;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.StockTransfer;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.CreatedUpdatedInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
import org.compiere.util.vo.PrescERXReqVO;

/**
 * @author lhernandez
 * 
 */
public class MEXMEPrescRXDet extends X_EXME_PrescRXDet implements DocAction, Worklist, IDiscontinueOrder {


	/** END_DATE = endDate */
	public static final String END_DATE = "endDate";
	public static final String START_DATE = "startDate";
	/** LIST_DATES = listDates */
	public static final String LIST_DATES = "listDates";

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** log de la clase */
	private static CLogger		log					= CLogger.getCLogger(MEXMEPrescRXDet.class);
	/**
	 * @param ctx
	 * @param EXME_PrescRXDet_ID
	 * @param trxName
	 */
	public MEXMEPrescRXDet(Properties ctx, int EXME_PrescRXDet_ID, String trxName) {
		super(ctx, EXME_PrescRXDet_ID, trxName);
		if(StringUtils.isBlank(getType())){
			setType(TYPE_Drug);
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPrescRXDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		if(StringUtils.isBlank(getType())) {
			setType(TYPE_Drug);
		}
	}

	/**
	 * Obtiene las lineas de una prescripcion
	 * @param ctx
	 * @param ctaPacID
	 * @return List<MEXMEPrescRXDet>
	 */
	public static List<MEXMEPrescRXDet> getMEXMEPrescDet(final Properties ctx, final int prescRXID) {
		StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// sql.append("SELECT EXME_PrescRXDet.* ");
		// sql.append(" FROM EXME_PrescRXDet ");
		joins.append(" INNER JOIN EXME_PrescRX ON EXME_PrescRXDet.EXME_PrescRX_ID=EXME_PrescRX.EXME_PrescRX_ID ");
		sql.append(" EXME_PrescRXDet.EXME_PrescRX_ID = ?");
		// SIEMPRE LAS ACTIVAS
		// sql.append(" AND EXME_PrescRXDet.isActive='Y' ");
		sql.append(" AND 'Y'=(CASE WHEN EXME_PrescRX.Tipo =").append(DB.TO_STRING(MEXMEPrescRX.TIPO_MedicalPrescription))
			.append(" THEN 'Y' ELSE EXME_PrescRXDet.Status END) ");
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		// sql.append(" ORDER BY EXME_PrescRXDet.Created ");

		return new Query(ctx, Table_Name, sql.toString(), null)//
			.setJoins(joins)//
			.setParameters(prescRXID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" EXME_PrescRXDet.Created ")//
			.list();
	}


	/**
	 * Obtiene todas las lineas de prescripciones home medication de una cuenta paciente determinada.
	 * @author natalia
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPrescRXDet> getDetOfCta(Properties ctx, int EXME_CtaPac_ID, String tipo, String trxName) {

//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		// sql.append("SELECT EXME_PrescRXDet.* from EXME_PrescRXDet ")
		joins.append(" INNER JOIN EXME_PrescRX h ");
		joins.append("   ON (EXME_PrescRXDet.EXME_PrescRX_ID = h.EXME_PrescRX_ID ");
		joins.append("       AND h.Tipo=? ");
		joins.append("       AND h.isActive='Y' ");
		joins.append("       AND h.EXME_CtaPac_ID=?) ");
		joins.append(" INNER JOIN EXME_GenProduct g ");
		joins.append("   ON (EXME_PrescRXDet.EXME_GenProduct_ID = g.EXME_GenProduct_ID ");
		joins.append("       AND g.isActive='Y') ");
//		sql.append(" WHERE EXME_PrescRXDet.isActive = 'Y' ")
		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
//			.append(" ORDER BY g.Generic_Product_Name ");

		return new Query(ctx, Table_Name, null, null)//
			.setJoins(joins)//
			.setParameters(tipo, EXME_CtaPac_ID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" g.Generic_Product_Name ")//
			.list();
	}


	/**
	 * 
	 * @param ctx
	 * @param exmeCtaPacId
	 *            numero de cuenta paciente
	 * @param dateIni
	 *            fecha a partir de la cual se filtra
	 * @param dateFin
	 *            fecha hasta la cual se filtrara en caso de que fechaIni no sea null
	 * @param active
	 *            activas o inactivas(descontinuadas)
	 * @param onlyVerbalOrder
	 *            medicamentos pendientes de autenticar
	 * @param notReviewed
	 *            indica si se buscara medicamentos que aun no tengan revision del farmaceutico
	 * @param doctors
	 *            buscar prescripciones realizadas por los medicos seleccionados
	 * @return List<MEXMEPrescRXDet>
	 */
	public static List<MEXMEPrescRXDet> getAllOrders(final Properties ctx, final int exmeCtaPacId,
		final Date dateIni, final Date dateFin, boolean active, final boolean onlyVerbalOrder,
		boolean notReviewed, int encounterFormId, Integer... doctors) {

		List<MEXMEPrescRXDet> list = new ArrayList<MEXMEPrescRXDet>();
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			final List<Object> params = new ArrayList<Object>();
			// Order type
			sql.append(" TRIM(EXME_PrescRX.TIPO)=? ");
			params.add(MEXMEPrescRX.TIPO_MedicalPrescription);
			// Active / Discontinued
			sql.append(" AND EXME_PrescRXDet.ISACTIVE=? ");
			params.add(DB.TO_STRING(active));
			// Encounter 
			if(exmeCtaPacId > 0) {
				sql.append(" AND EXME_PrescRX.EXME_CTAPAC_ID=? "); 
				params.add(exmeCtaPacId);
			} 
			// Encounter Form
			if(encounterFormId > 0){
				sql.append(" AND EXME_PrescRX.EXME_EncounterForms_ID=? ");
				params.add(encounterFormId);
			}
			// Dates
			if (dateIni != null && dateFin != null) {
				if(DB.isOracle()) {
					sql.append(" AND TRUNC(EXME_PrescRXDet.StartDate, 'MI') ");
				} else if (DB.isPostgreSQL()) {
					sql.append(" AND DATE_TRUNC('MINUTE', EXME_PrescRXDet.StartDate) ");
				}
				sql.append(" BETWEEN ? ");
				sql.append(" AND ? ");
				params.add(new Timestamp(dateIni.getTime()));
				params.add(new Timestamp(dateFin.getTime()));
				
			} else if (dateIni != null) {// solo fecha inicial
				if(DB.isOracle()) {
					sql.append(" AND (TRUNC(EXME_PrescRXDet.StartDate,'MI') >= ?");
				} else if(DB.isPostgreSQL()) {
					sql.append(" AND (DATE_TRUNC('MINUTE', EXME_PrescRXDet.StartDate) >= ?");
				}
				sql.append(" OR COALESCE(EXME_PrescRXDet.AUTHENTICATED,'N')=? ) ");
				params.add(new Timestamp(dateIni.getTime()));
				params.add(DB.TO_STRING(false));
			}
			// not authenticated
			if (onlyVerbalOrder) {
				sql.append(" AND COALESCE(EXME_PrescRXDet.AUTHENTICATED,'N')=? ");// #4
				params.add(DB.TO_STRING(false));
			}
			// Reviewed orders
			if (notReviewed) {
				sql.append(" AND EXME_PrescRXDet.RXREVIEW IS NULL ");
			}
			// prescribed by
			if (doctors != null && doctors.length > 0) {
				sql.append(" AND EXME_PrescRX.EXME_MEDICO_ID IN (");
				sql.append(DB.TO_STRING(doctors));
				sql.append(") ");
				params.addAll(Arrays.asList(doctors));
			}
			list = new Query(ctx, Table_Name, sql.toString(), null)//
					.setJoins(new StringBuilder(" INNER JOIN EXME_PrescRX ON (EXME_PrescRX.EXME_PrescRX_ID=EXME_PrescRXDet.EXME_PrescRX_ID) "))//
					.setParameters(params)//
					.addAccessLevelSQL(true)//
					.setOrderBy(" EXME_PrescRXDet.StartDate DESC ")//
					.list();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
		return list;
	}

	/**
	 * Obtiene la cantidad de dosis PRN medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * EXME_PRESCRXDET.ISPRN=Y
	 * 
	 * @param ctx
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param doctors 		arreglo de medicos que prescriben
	 * @return				total de registros
	 */
	public static int getCountPRN(Properties ctx, int exmeCtaPacId, final Date dateIni,
		Date dateFin) {
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = getSQLPRN(ctx, true, exmeCtaPacId, dateIni, dateFin, null, params);
		
		return DB.getSQLValue(null, sql.toString(), params);
	}
	
	/**
	 * Obtiene la dosis PRN medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * EXME_PRESCRXDET.ISPRN=Y
	 * 
	 * @param ctx
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param doctors 		arreglo de medicos que prescriben
	 * @return				List<MEXMEPrescRXDet>
	 */
	public static List<MEXMEPrescRXDet> getPrescribedPRN(Properties ctx, int exmeCtaPacId, final Date dateIni,
		Date dateFin, Integer[] doctors) {
		final List<MEXMEPrescRXDet> list = new ArrayList<MEXMEPrescRXDet>();

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = getSQLPRN(ctx, false, exmeCtaPacId, dateIni, dateFin, doctors, params);
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			result = pstmt.executeQuery();

			while (result.next()) {
				list.add(new MEXMEPrescRXDet(ctx, result, null));
			}

		}
		catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(result, pstmt);
		}
		return list;
	}
	
	/**
	 * Genera el SQL para obtener la dosis PRN medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * EXME_PRESCRXDET.ISPRN=Y
	 * 
	 * @param ctx
	 * @param count			regresa solo el total de registros
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param doctors 		arreglo de medicos que prescriben
	 * @return				SQL
	 */
	public static StringBuilder getSQLPRN(Properties ctx, boolean count, int exmeCtaPacId, final Date dateIni, Date dateFin,
		Integer[] doctors, final List<Object> params) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		if (count) {
			sql.append("SELECT count(EXME_PrescRxDet.EXME_PrescRxDet_ID) ");
		} else {
			sql.append("SELECT EXME_PrescRxDet.* ");
		}
		sql.append("FROM EXME_PrescRxDet ");
		sql.append("INNER JOIN EXME_PrescRx    pr ON (pr.EXME_PrescRx_ID    = EXME_PrescRxDet.EXME_PrescRx_ID");
		sql.append("                             AND  pr.EXME_CtaPac_ID     = ? ");// #1
		sql.append("                             AND  pr.Tipo               = ?) ");// #2
		sql.append("INNER JOIN EXME_PlanMed    pl ON (pl.EXME_PrescRxDet_ID = EXME_PrescRxDet.EXME_PrescRxDet_ID");
		sql.append("                             AND  pl.IsActive           = 'Y' ");
		sql.append("                             AND  pl.DocStatus     NOT IN (?,?)) ");// #4

		sql.append("WHERE EXME_PrescRxDet.IsActive='Y' ");
		sql.append("AND   COALESCE(EXME_PrescRxDet.IsPRN,'N')='Y' ");// only PRN order

		params.add(exmeCtaPacId);// #1
		params.add(MEXMEPrescRX.TIPO_MedicalPrescription);// #2
		params.add(MPlanMed.DOCSTATUS_Closed);// #3 ...
		params.add(MPlanMed.DOCSTATUS_Voided);

		// Date ini #4
		if (dateIni != null) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_PrescRxDet.StartDate, 'MI') ");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('MINUTE', EXME_PrescRxDet.StartDate) ");
			}
			sql.append(">=  TO_DATE(?, 'DD/MM/YYYY HH24:MI') ");
			params.add(Constantes.getSdfFechaHora().format(dateIni));
		}
		// Date ini #5
		if (dateFin != null) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_PrescRxDet.StartDate, 'MI') ");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('MINUTE', EXME_PrescRxDet.StartDate) ");
			}
			sql.append("<=  TO_DATE(?, 'DD/MM/YYYY HH24:MI') ");
			params.add(Constantes.getSdfFechaHora().format(dateFin));
		}

		// doctors #6 ...
		if (doctors != null && doctors.length > 0) {
			sql.append("AND pr.EXME_Medico_ID IN (").append(DB.TO_STRING(doctors)).append(") ");
			for (int doctor : doctors) {
				params.add(doctor);
			}
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		if(!count) {
			// this one will blow in PostgreSQL, because it requires the field
			// to be in a group by, and if you are counting, why do you need an
			// order by anyway?
			sql.append(" ORDER BY EXME_PrescRxDet.StartDate ASC ");
		}

		return sql;
	}

	private MEXMEGenProduct	genProduct	= null;

	@Deprecated
	public MEXMEGenProduct getGenProduct() {
		return getEXME_GenProduct();
	}

	/** @return route name from either EXME_Route or EXME_ViasAdministracion. Ticket 1000187 */
	public String getRouteName() {
		return getRouteName(false);
	}
	
	/** @return route name from either EXME_Route or EXME_ViasAdministracion. Ticket 1000187 */
	public String getRouteName(boolean key) {
		final String lblRouteAdm;
		if (getEXME_ViasAdministracion_ID() > 0) {
			if (key) {
				lblRouteAdm = getEXME_ViasAdministracion().getTipo();
			} else {
				lblRouteAdm =
					StringUtils.isEmpty(getEXME_ViasAdministracion().getDescription()) ? getEXME_ViasAdministracion().getName()
						: getEXME_ViasAdministracion().getDescription();
			}
		} else if (getEXME_Route_ID() > 0) {
			if (key) {
				lblRouteAdm = getEXME_Route().getAbrev();
			} else {
				lblRouteAdm = getEXME_Route().getDescription1();
			}
		} else {
			lblRouteAdm = StringUtils.EMPTY;
		}
		return lblRouteAdm;
	}
	
	/**
	 * ActiveMedication en forma de lista
	 * 
	 * @param ctx Contexto
	 * @param exmePacId Paciente
	 * @param trxName Trx
	 * @return Listado de medicamentos
	 */
	public static String getActiveMedication(Properties ctx, int exmePacId, String trxName) {
		String str = null;

		try {
			List<String> list = new ArrayList<String>();
			List<MEXMEPrescRXDet> lstPrescRXDet = getSavedMeds(ctx, trxName, true, exmePacId);
			for (MEXMEPrescRXDet det : lstPrescRXDet) {
				if (X_EXME_PrescRXDet.TYPE_Compound.equalsIgnoreCase(det.getType())) {
					list.add(det.getNotas());
				} else {
					if (det.getEXME_GenProduct() != null) {
						list.add(det.getGenProdName());
					}
				}
			}
			str = StringUtils.join(list, ",");
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		}
		return str;
	}

	/**
	 * Obtiene todos los medicamentos activos del paciente (tipo Office Visit / home medications / discharge
	 * medications)
	 * 
	 * @param ctx
	 * @param trxName
	 * @param onlyActiveMeds - si es true entonces agrega la condicion: status = 'Y'
	 * @param exmePacienteId - id del paciente
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEPrescRXDet> getSavedMeds(Properties ctx, String trxName, boolean onlyActiveMeds,
		int exmePacienteId) throws Exception {
		return getSavedMeds(ctx, onlyActiveMeds, null, null, exmePacienteId, trxName);
	}
	


	/**
	 * Obtiene todos los medicamentos activos del paciente (tipo Office Visit / home medications / discharge
	 * medications)
	 * 
	 * @param ctx
	 * @param onlyActiveMeds - si es true entonces agrega la condicion: status = 'Y'
	 * @param order - Especificacion de ordenamiento de registros, si es blanco agrega: "ORDER BY prxd.created desc"
	 * @param whereClause - clausula where (puede incluir joins) si no termina en where angrega al final un "AND"
	 * @param exmePacienteId - id del paciente
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEPrescRXDet> getSavedMeds(Properties ctx, boolean onlyActiveMeds, String orderBy,
		String whereClause, int exmePacienteId,String trxName) throws Exception {

		List<MEXMEPrescRXDet> list = new ArrayList<MEXMEPrescRXDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" select prxd.* from exme_prescrxdet prxd ")
			.append(
				" inner join exme_prescrx prx on (prxd.exme_prescrx_id = prx.exme_prescrx_id AND trim(prx.tipo) IN (")
			.append(DB.TO_STRING(MEXMEPrescRX.TIPO_OV))
			.append(",")
			.append(DB.TO_STRING(MEXMEPrescRX.TIPO_DischargeMedication))
			.append(",")
			.append(DB.TO_STRING(MEXMEPrescRX.TIPO_HomeMedication))
			.append(") ) ")
			.append(
				" inner join exme_ctapac cp on (prx.exme_ctapac_id = cp.exme_ctapac_id AND cp.exme_paciente_id = ?) ");
		if (StringUtils.isNotBlank(whereClause)) {
			sql.append(whereClause);
			if (!whereClause.trim().endsWith("WHERE")) {
				sql.append(" AND ");
			}
		}
		else {
			sql.append(" WHERE ");
		}
		sql.append(" prxd.isActive = 'Y' ");
		sql.append(" AND prxd.type in ('D', 'C') ");
		if (onlyActiveMeds) {
			sql.append(" AND prxd.status = 'Y' ");
		}
		
		if (StringUtils.isNotBlank(orderBy)) {
			sql.append(orderBy);
		}
		else {
			sql.append(" ORDER BY prxd.created desc ");
		}
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmePacienteId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEPrescRXDet(ctx, rs, trxName));
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		}
		finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Obtiene todos los medicamentos activos del paciente (dischargemedication o tipo Office Visit y home medications en caso de ser tipo HM)
	 * 
	 * @param ctx
	 * @param onlyActiveMeds - si es true entonces agrega la condicion: status = 'Y'
	 * @param exmePacienteId - id del paciente
	 * @param tipo			 - Indica si es home medication o discharge medication
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEPrescRXDet> getSavedMeds(Properties ctx, boolean onlyActiveMeds, int exmePacienteId, String tipo, String trxName) throws Exception {
		List<MEXMEPrescRXDet> list = new ArrayList<MEXMEPrescRXDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" select prxd.* from exme_prescrxdet prxd ")
			.append(" inner join exme_prescrx prx on (prxd.exme_prescrx_id = prx.exme_prescrx_id AND trim(prx.tipo) IN (");
		if (MEXMEPrescRX.TIPO_DischargeMedication.equals(tipo)) {
			sql.append(DB.TO_STRING(MEXMEPrescRX.TIPO_DischargeMedication));
		} else {
			sql.append(DB.TO_STRING(MEXMEPrescRX.TIPO_OV))
			.append(",")
			.append(DB.TO_STRING(MEXMEPrescRX.TIPO_HomeMedication));
		}
		sql.append(") ) ")
		.append(" inner join exme_ctapac cp on (prx.exme_ctapac_id = cp.exme_ctapac_id AND cp.exme_paciente_id = ?) ")
		.append(" WHERE ")
		.append(" prxd.isActive = 'Y' ")
		.append(" AND prxd.type in ('D', 'C') ");
		if (onlyActiveMeds) {
			sql.append(" AND prxd.status = 'Y' ");
		}
		sql.append(" ORDER BY prxd.created desc ");

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmePacienteId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEPrescRXDet(ctx, rs, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al optener prescripciones");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static MEXMEPrescRXDet getByActInd(Properties ctx, int actPacienteIndID, String trxName) {
		return get(ctx, trxName, " EXME_ActPacienteInd_ID = ?", null, null, false, true, new Object[] {actPacienteIndID});
	}
	
	public MEXMEClaimCodes getRelationPrescAct(MEXMECtaPac mCtaPac, int actPacienteIndID) {
		return MEXMEClaimCodes.get(getCtx(), //
			mCtaPac.getEXME_Paciente_ID(), mCtaPac.get_ID(), // Patient
			MEXMEActPacienteInd.Table_ID, actPacienteIndID, // Table / Record
			MEXMEPrescRXDet.Table_ID, getEXME_PrescRXDet_ID() // TableOrig / RecordOrig
			);
	}

	public static MEXMEPrescRXDet get(Properties ctx, String trxName, String whereClause, StringBuilder joins,
		String orderBy, boolean onlyActiveRecords, boolean accesslevel, Object... parameters) {
		return new Query(ctx, Table_Name, whereClause, trxName)//
			.setJoins(joins)//
			.setParameters(parameters)//
			.setOrderBy(orderBy)//
			.setOnlyActiveRecords(onlyActiveRecords)//
			.addAccessLevelSQL(accesslevel)//
			.first();
	}

	/**
	 * Trae informacion de usuarios que crearon y/o actualizaron el registro de la prescripcion
	 * 
	 * @param ctx
	 * @param EXME_PrescRxDet_ID
	 * @return
	 * @throws Exception
	 */
	public static String getInfoPrescRx(Properties ctx, int EXME_PrescRxDet_ID) throws Exception {
		CreatedUpdatedInfo infoPrescRx = new CreatedUpdatedInfo();
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		Date date = null;
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		sql.append("SELECT ");
		sql.append("EXME_PrescRxDet.EXME_PrescRxDet_ID AS PrescRxDet_ID, EXME_PrescRxDet.CreatedBy AS Created_By, EXME_PrescRxDet.UpdatedBy AS Updated_By, ");
		sql.append("TO_CHAR(EXME_PrescRxDet.Created, 'dd/mm/yyyy hh24:mi') AS Fecha_C, TO_CHAR(EXME_PrescRxDet.Updated, 'dd/mm/yyyy hh24:mi') AS Fecha_U, ");
		sql.append("medC.nombre_med AS Med_C, medU.nombre_med AS med_u, moC.AD_User_ID AS Med_Org_C, moU.AD_User_ID As Med_Org_U, ");
		sql.append("asiC.Name AS Asi_C, asiU.Name AS Asi_U, Enfc.nombre_enf AS Enf_C, Enfu.nombre_enf AS Enf_U ");
		sql.append("FROM EXME_PrescRxDet ");
		sql.append("LEFT JOIN EXME_Medico_Org moC ON (moC.AD_User_ID = EXME_PrescRxDet.CreatedBy AND moC.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND moC.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Medico_Org moU ON (moU.AD_User_ID = EXME_PrescRxDet.UpdatedBy AND moU.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND moU.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Asistente asiC ON (asiC.AD_User_ID = EXME_PrescRxDet.CreatedBy AND asiC.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND asiC.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Asistente asiU ON (asiU.AD_User_ID = EXME_PrescRxDet.UpdatedBy AND asiU.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND asiU.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Enfermeria enfC ON (enfC.AD_User_ID = EXME_PrescRxDet.CreatedBy AND enfC.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND enfC.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Enfermeria enfU ON (enfU.AD_User_ID = EXME_PrescRxDet.UpdatedBy AND enfU.AD_Client_ID =  EXME_PrescRxDet.AD_Client_ID AND enfU.AD_Org_ID = EXME_PrescRxDet.AD_Org_ID) ");
		sql.append("LEFT JOIN EXME_Medico medC ON medC.EXME_Medico_ID = moC.EXME_Medico_ID ");
		sql.append("LEFT JOIN EXME_Medico medU On medU.EXME_Medico_Id = moU.EXME_Medico_Id ");
		sql.append("WHERE EXME_PrescRxDet.Isactive = 'Y' And EXME_PrescRxDet.Exme_PrescRxDet_Id = ? ");
		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		sql.append(" ORDER BY EXME_PrescRxDet.CreatedBy");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_PrescRxDet_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				infoPrescRx.setCreatedBy(rs.getInt(2));
				infoPrescRx.setUpdatedBy(rs.getInt(3));
				date = simpleFormat.parse(rs.getString(4));
				infoPrescRx.setDateCreated(simpleFormat.format(DB.convert(ctx, date)));
				date = simpleFormat.parse(rs.getString(5));
				infoPrescRx.setDateUpdated(simpleFormat.format(DB.convert(ctx, date)));				
				infoPrescRx.setMedCreated(rs.getString(6));
				infoPrescRx.setMedUpdated(rs.getString(7));
				infoPrescRx.setAsiCreated(rs.getString(10));
				infoPrescRx.setAsiUpdated(rs.getString(11));
				infoPrescRx.setEnfCreated(rs.getString(12));
				infoPrescRx.setEnfUpdated(rs.getString(13));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getInfoPrescRx", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return infoPrescRx.getCreatedUpdatedInfo(ctx);
	}

	@Override
	public void setDocStatus(String newStatus) {}

	public void setDocAction(String action) {}

	@Override /* Draft */
	public String getDocStatus() {
		log.log(Level.CONFIG, "getDocStatus()");
		String docStatus = DocAction.STATUS_NotApproved;
		if (!isActive()) {
			docStatus = DocAction.STATUS_Voided;
		}
		else if (isAuthenticated()) {
			docStatus = DocAction.STATUS_Approved;
		} else {
			if (MPlanMed.isCompleted(getEXME_PrescRXDet_ID(), null)) {
				docStatus = DocAction.STATUS_Completed;
			}
		}
		log.log(Level.CONFIG, "DocStatus>>"+docStatus);
		return docStatus;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		log.log(Level.CONFIG, "processIt()");
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		return true;
	}

	@Override
	public boolean invalidateIt() {
		return true;
	}

	@Override
	public String prepareIt() {
		return DocAction.STATUS_InProgress;
	}

	/**
	 * <b>'Authenticates'</b> an order if user is a <u>physician</u> <br>
	 *        <i>(EXME_PrescRxDet.Authenticated=true & EXME_PrescRxDet.Authenticated_Date=sysdate)</i><br><br>
	 * <b>'Reviews'</b> an order for <u>pharmacist</u> user <br>
	 *        <i>(EXME_PrescRxDet.RXReview [RXReviewC >> when Type = Certified] =sysdate <br>
	 *                 & EXME_PrescRxDet.EXME_PharmacistT_ID [EXME_PharmacistC_ID >> when Type = certified]=@#EXME_Pharmacist_ID@ )</i><br><br>
	 * <b>'Notes'</b> an order for a <u>nurse</u> user <br>
	 *        <i>(EXME_PrescRxDet.NotedBy=@#AD_User_ID@ & EXME_PrescRxDet.NotedDate=sysdate)</i>
	 */
	@Override
	public boolean approveIt() {
		log.log(Level.CONFIG, "approveIt()");
		boolean retValue = true;
		if (isActive()) {
			// if doctor: Authenticate
			if (Env.getEXME_Medico_ID(getCtx()) > 0) {
				setAuthenticated(true);
			} else
			// if nurse: Note
			if (Env.getEXME_Enfermeria_ID(getCtx()) > 0) {
				if (getNotedBy() <= 0 || getNotedDate() == null) {
					setNotedDate(Env.getCurrentDate());
					setNotedBy(Env.getAD_User_ID(getCtx()));
				}

			} else
			// if pharmacist: Review
			if (Env.getEXME_Pharmacist_ID(getCtx()) > 0) {
				final String pharmacistType = Env.getEXME_PharmacistType(getCtx());
				if (MEXMEPharmacist.TYPE_Technician.equals(pharmacistType)) {
					if (getEXME_PharmacistT_ID() <= 0 || getRXReview() == null) {
						setRXReview(Env.getCurrentDate());
						setEXME_PharmacistT_ID(Env.getEXME_Pharmacist_ID(getCtx()));
					}
				} else if (MEXMEPharmacist.TYPE_Certificate.equals(pharmacistType)) {
					if (getEXME_Pharmacistc_ID() <= 0 || getRXReviewC() == null) {
						setRXReviewC(Env.getCurrentDate());
						setEXME_Pharmacistc_ID(Env.getEXME_Pharmacist_ID(getCtx()));
					}
				}
			} else {
				return false;
			}
			if (is_Changed()) {
				retValue = save();
			}
		} else if (Env.getEXME_Medico_ID(getCtx()) > 0) {
			// Card #746 - Authentication of Discontinuation Orders
			MEXMEDiscontinueOrder.authenticate(this, get_TrxName());
		}
		return retValue;
	}

	/** Marks as held an administration */
	@Override
	public boolean rejectIt() {
		return true;
	}

	/** Administrate a medication */
	@Override
	public String completeIt() {
		return DocAction.STATUS_Completed;
	}

	/**
	 * Discontinues an order (EXME_PrescRxDet.IsActive = 'N') <br>
	 * and cancels the medication plan (EXME_PlanMed.IsActive='N' EXME_PlanMed.DocStatus='VO') <br>
	 * cancels or deletes the administration lines (EXME_PlanMedLine)<br>
	 * <i>** Deletes only prescribed lines</i>
	 * */
	@Override
	public boolean voidIt() {
		log.log(Level.CONFIG, "voidIt()");
		boolean retValue = true;
		// descontinua medicamento actual
		if(super.getDiscontinuedDate() == null){
			setDiscontinuedDate(Env.getCurrentDate());
		}
		setIsActive(false);
		// Card #746 - Authentication of Discontinuation Orders
//		if (Env.getEXME_Medico_ID(getCtx()) > 0) {
//			setAuthenticated(true);
//		} else if (Env.getEXME_Enfermeria_ID(getCtx()) > 0 || Env.getEXME_Pharmacist_ID(getCtx()) > 0) {
//			setAuthenticated(false);
//		}
		// verificar si ya había un plan de medicamentos creado (del objeto original)
		final MPlanMed exmePlanMed = getPlanMed(true);
		// MPlanMed.getFromRxId(getCtx(), getEXME_PrescRXDet_ID(), get_TrxName());
		if (exmePlanMed != null) {
			// not completed lines will be deleted
			retValue = exmePlanMed.cancel(true, super.getDiscontinuedDate());
		}
		if (retValue) {
			retValue = save();
		}
		return retValue;// Defecto #1458
	}

	@Override
	public void discontinue(String trxName, DiscontinueParams params) {
		// Card #746 - Authentication of Discontinuation Orders
		set_TrxName(trxName);
		if (params != null) {
			setDiscontinuedDate(params.getDate());
		}
		voidIt();
		if(isActive()){
			throw new MedsysException();
		}
	}
	
	@Override
	public void setAuthenticated(boolean Authenticated) {
		super.setAuthenticated(Authenticated);
		if (isAuthenticated() && is_ValueChanged(COLUMNNAME_Authenticated)) {
			if (getAuthenticated_Date() == null) {
				super.setAuthenticated_Date(Env.getCurrentDate());
			}
			if (getAuthenticatedBy() <= 0) {
				super.setAuthenticatedBy(Env.getAD_User_ID(getCtx()));
			}
		}
	}
	
	
	/**
	 * Discontinues {@link DocAction#voidIt()}) all the prescriptions in the list.
	 * 
	 * @param list List of prescriptions
	 * @param trxName Transaction name
	 */
	public static void voidAll(final List<MEXMEPrescRXDet> list, final String trxName) {
		log.log(Level.CONFIG, "voidAll");
		for (MEXMEPrescRXDet med : list) {
			final MEXMEPrescRXDet mPrescRXdet = new MEXMEPrescRXDet(med.getCtx(), med.get_ID(), trxName);
			if (!mPrescRXdet.voidIt()) {// mark as discontinued
				throw new MedsysException(Utilerias.getMsg(mPrescRXdet.getCtx(), "msj.cannnotDisc") + ": " + mPrescRXdet.getMedicationName());//Card #1165
			}
		}
	}

	/**
	 * Discontinues an order (EXME_PrescRxDet.IsActive = 'N') <br>
	 * and closes the medication plan (EXME_PlanMed.DocStatus='CL') <br>
	 * cancels the prescribed lines (EXME_PlanMedLine)
	 * */
	@Override
	public boolean closeIt() {
		log.log(Level.CONFIG, "coseIt()");
		boolean retValue = true;
		setIsActive(Boolean.FALSE);
		final MPlanMed exmePlanMed = MPlanMed.getFromRxId(getCtx(), getEXME_PrescRXDet_ID(), get_TrxName());
		if (exmePlanMed != null) {
			exmePlanMed.setDocStatus(MPlanMed.DOCSTATUS_Closed);
			retValue = exmePlanMed.cancelDetail(false, Env.getCurrentDate()) && exmePlanMed.save();
		}
		if (retValue) {
			retValue = save();
		}
		return retValue;
	}

	@Override
	public boolean reverseCorrectIt() {
		return true;
	}

	@Override
	public boolean reverseAccrualIt() {
		return true;
	}

	@Override
	public boolean reActivateIt() {
		return true;
	}

	@Override
	public String getSummary() {
		final StringBuilder str = new StringBuilder();
		// patient
		str.append("<b>").append(getMsgTranslate(MEXMEPaciente.COLUMNNAME_EXME_Paciente_ID)).append("</b>: ");
		final MEXMECtaPac ctapac = (MEXMECtaPac)getEXME_PrescRX().getEXME_CtaPac();
		str.append(ctapac.getDocumentNo()).append(" - ");
		str.append(ctapac.getPaciente().getNombre_Pac()).append(".<br>");
		str.append(getSummaryDetail());
		
		return str.toString();
	}

	@Override
	public String getDocumentNo() {
		return null;
	}

	@Override
	public String getDocumentInfo() {
		return getSummary();
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return getEXME_Medico_ID() > 0 ? getEXME_Medico().getAD_User_ID() : 0;
	}

	@Override
	public int getC_Currency_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	@Override
	/* Draft */
	public String getDocAction() {
		log.log(Level.CONFIG, "getDocAction()");
		// Action: is not approved then "Prepare" else "Complete"
		String docAction = DocAction.ACTION_Prepare;
		if (!isActive()) {
			docAction = DocAction.ACTION_None;
		}
		else if (isAuthenticated()) {
			docAction = DocAction.ACTION_Complete;
		}
		else {
			if (MPlanMed.isCompleted(getEXME_PrescRXDet_ID(), null)) {
				docAction = DocAction.ACTION_Close;
			}
		}
		log.log(Level.CONFIG, "DocAction>>"+docAction);
		return docAction;
	}

	/** Verifica campos valores validos para campos reqqueridos en prescripcion electronica */
	public boolean isReadeyForEPrescripbing() {
		return getM_Product_ID() > 0 & getEXME_Farmacia_ID() > 0 //
			& getNumDias() > 0 & getEXME_Frequency1_ID() > 0 //
			& getEXME_ViasAdministracion_ID() > 0 //
			& (getQuantity().compareTo(BigDecimal.ZERO) > 0) & (getQtyPlan().compareTo(BigDecimal.ZERO) > 0);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		return super.beforeSave(newRecord);		
	}

	/**
	 * Guardar en la tabala EXME_PlanMed cuando el farmaceutico crea la prescripcion
	 * 
	 * @param trxName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean savePlanMed(final MEXMEPrescription prescription, MPlanMed plan) {
		log.log(Level.CONFIG, "savePlanMed");
		boolean success = true;

		if (prescription == null && plan == null) {
			throw new MedsysException("Prescription is required to create plan");
		}

		// calcular las fechas de cada aplicacion de dosis (EXME_PlanMedLine)
		final HashMap<String, Object> map;
		// lista de fechas de dosis
		final List<Date> dates;

		if (plan == null || plan.is_new()) {
			if (getM_Product_ID() <= 0) {
				throw new MedsysException("NDC is required to create plan");
			}
			map = schedule(null);// map=null
			dates = (List<Date>) map.get(LIST_DATES);
			if (dates.isEmpty()) {
				// se debe revisar la configuración de Automatic Stop Policy
				throw new MedsysException(Utilerias.getMsg(getCtx(), "error.stopPolicy.checkConf"));
			}
			if (map.get(START_DATE) != null) {
				// fecha de la primer dosis de la toma de medicamento
				final Timestamp start = new Timestamp(((Date) map.get(START_DATE)).getTime());
				setStartDate(start);
			} else {
				throw new MedsysException("No start day");
			}
			// fecha de la ultima dosis de aplicacion de medicamento
			if (map.get(END_DATE) != null) {
				final Timestamp end = new Timestamp(((Date) map.get(END_DATE)).getTime());
				setLastDay(end);
			} else {
				throw new MedsysException("No last day");
			}
			// guardar el plan en EXME_PrescRXDet (del Generic Product)
			if(!save()) {
				throw new MedsysException();
			}
			if (getM_Product_ID() > 0) {
				plan = createPlan(prescription);
				if(!plan.save()) {
					throw new MedsysException();
				}
			}
			if (success && isPRN()) {
				dates.clear();
			}
		}
		else {
			if (isPRN()) {
				dates = new ArrayList<Date>();
				dates.add(getNextDose(plan, false));
			}
			else {
				map = schedule(plan.getLastApliedDate());
				dates = (List<Date>) map.get(LIST_DATES);
			}
		}
		if (success && !dates.isEmpty()) {
			// guardar las dosis del plan
			if(!plan.saveLines(dates)) {
				throw new MedsysException();
			}
		}
		return success;
	}

	private Date	nextDose	= null;

	public Date getNextDose(MPlanMed plan, boolean requery) {
		log.log(Level.CONFIG, "getNextDose");
		if (plan != null && (nextDose == null || requery)) {
			// calcular las fechas de cada aplicacion de dosis (EXME_PlanMedLine)
			final HashMap<String, Object> map = schedule(plan.getLastApliedDate());
			// lista de fechas de dosis
			@SuppressWarnings("unchecked")
			final List<Date> dates = (List<Date>) map.get(LIST_DATES);
			if (dates.isEmpty()) {
				// se debe revisar la configuración de Automatic Stop Policy
				throw new MedsysException(Utilerias.getMsg(getCtx(), "error.stopPolicy.checkConf"));
			}
			nextDose = dates.get(0);
		}
		return nextDose;
	}

	public void setNextDose(Date nextDose) {
		this.nextDose = nextDose;
	}

	/**
	 * Crea el plan de medicamentos
	 * @param prescription
	 * @return MPlanMed creado
	 */
	public MPlanMed createPlan(final MEXMEPrescription prescription) {
		log.log(Level.CONFIG, "createPlan");

		final MPlanMed plan = new MPlanMed(getCtx(), 0, prescription.get_TrxName());
		plan.setEXME_Prescription_ID(prescription.getEXME_Prescription_ID());
		plan.setName(StringUtils.isBlank(getM_Product().getDescription()) ? getM_Product().getName() : getM_Product()
			.getDescription());
		plan.setDescription(getIndicaciones());
		plan.setEXME_PrescRXDet_ID(getEXME_PrescRXDet_ID());

//		plan.setEXME_Paciente_ID(prescription.getEXME_CtaPac().getEXME_Paciente_ID());
		plan.setEXME_Medico_ID(getEXME_Medico_ID());
		plan.setEXME_CtaPac_ID(prescription.getEXME_CtaPac_ID());
		plan.setEXME_Especialidad_ID(prescription.getEXME_CtaPac().getEXME_Especialidad_ID());
		plan.setM_Product_ID(getM_Product_ID());
		plan.setTomadoCasa(MEXMEPrescRX.TIPO_HomeMedication.equals(getPrescRx(false).getTipo()));

		plan.setStartDate(getStartDate());
		plan.setEndDate(getLastDay());
		plan.setQtyPlanned(getQuantity());
		plan.setQtyTotPlanned(getQuantity());
		plan.setQtyTotAplied(BigDecimal.ZERO);

		plan.setIntervalo(0);
		plan.setUOMIntervalo(X_EXME_PlanMed.UOMINTERVALO_Hours);// es obligatoria //default

		plan.setDuracion(0);
		plan.setUOMDuracion(X_EXME_PlanMed.UOMDURACION_Hours);// es obligatorio //default
		plan.setDocStatus(MPlanMed.DOCSTATUS_Drafted);
		if (isPRN()) { // el detalle se crea 1 en 1
			plan.setDocStatus(MPlanMed.DOCSTATUS_WaitingConfirmation);
		}
		plan.setC_UOM_ID(getM_Product().getC_UOM_ID());
		plan.setM_Warehouse_ID(Env.getM_Warehouse_ID(getCtx()));
		plan.setDose(getDose());
		plan.setSurtir(isPrefer());// si es preferido se surte si no no ->>

		return plan;
	}

	/**
	 * Calcular los horarios de aplicacion del medicamento. (para EXME_PlanMedLine)
	 * 
	 * @return HashMap<String, Object>
	 *         lista de fecha/horas y fecha/hora de última aplicación
	 */
	public HashMap<String, Object> schedule(Timestamp lastDose) {
		log.log(Level.CONFIG, "schedule");
		// Dosis (Auto, 2 Doses...) seleccionada
		final String dose = getDose();
		// si la frecuencia 2 es Once
		final boolean isOnce = getEXME_Frequency1().isAutoCalculate() && getEXME_Frequency2().getQuantity() == 0;
		// Auto Dose
		boolean isAutoDose = DOSE_Auto.equals(dose);

		// Revision de codigo: Ticket #0100313
		final HashMap<String, Object> map = new HashMap<String, Object>();

		// lista de fecha y hora de aplicación
		final List<Date> listDates = new ArrayList<Date>();
		
		// variables auxiliares para manipular la fecha inicial y la fecha final
		final Calendar endDate = Calendar.getInstance(); // fecha final
		final Calendar start = Calendar.getInstance(); // fecha de inicio

		// inicializar las fechas inicial y final con la fecha guardada en la prescripcion (EXME_PrescRXDet)
		endDate.setTimeInMillis(getStartDate().getTime());
		if (!isPRN() || lastDose == null) {
			start.setTimeInMillis(getStartDate().getTime());
		}
		// si se quiere crear la siguiente dosis
		else {
			start.setTimeInMillis(lastDose.getTime());
		}
		// si existe la configuracion de Automatic Stop Policy (Envia excepcion si no existe y es AutoDose)
		final MEXMEStopPolicy policy = getM_Product().getAutoStopPolicy(isAutoDose && !isOnce);
		// si existe la configuracion de Automatic Stop Policy
		if (policy == null) {
			isAutoDose = false;// una sola dosis
		} else {
			// a la fecha final se le suma el numero de dias durante los cuales se puede administrar el medicamento
			endDate.add(Calendar.DAY_OF_MONTH, policy.getMaxAutoDuration());
		}
		final int numDosis = Integer.valueOf(dose);// numero de dosis
		// ending date
		Date endingDate = null;
		// Si la frecuencia 1 seleccionada esta como "Autocalculated"
		if (getEXME_Frequency1().isAutoCalculate()) {
			// la fecha de inicio es la primer hora programada
			if (lastDose == null) {
				listDates.add(new Date(start.getTimeInMillis()));
			}
			// intervalo de tiempo configurado en la frecuencia 2
			final int qty = getEXME_Frequency2().getQuantity();
			// si la cantidad de tiempo de freq2 es mayor a cero
			if (qty > 0) {
				// si la Duracion (dosis) seleccionada es "Auto"
				if (isAutoDose) {
					// mientras la fecha inicial sea menor a la final
					while (start.getTime().before(endDate.getTime())) {
						// se agregan las lineas
						listDates.add(getEXME_Frequency2().getDateTime(start, qty));
						if (isPRN() || lastDose != null) {
							break;
						}
					}
					// agregar al mapa la fecha final calculada
					endingDate = endDate.getTime();
				}
				// la Duracion (dosis) seleccionada es diferente "Auto"
				else {
					// por cada numero de dosis
					for (int i = 1; i < numDosis; i++) {
						// se agrega una nueva toma
						listDates.add(getEXME_Frequency2().getDateTime(start, qty));
						if (lastDose != null) {
							break;
						}
					}
					if (!listDates.isEmpty()) {
						// la última fecha/hora de aplicación es la fecha final
						endingDate = listDates.get(numDosis - 1);
					}
				}
			}// cuando la qty=0 entonces se toma como única aplicacion
			else { // Si qty=0 Al momento.
				// agregar al mapa la fecha final misma que la incial.
				endingDate = start.getTime();
			}
			// Asignar fecha final
			if(!listDates.isEmpty()) {
				map.put(START_DATE, new Date(listDates.get(0).getTime()));
			}
			map.put(END_DATE, endingDate);
		}
		// si la Frecuencia 1 esta configurada como "Not autocalculated"
		else {
			// lista de horarios (freq3) definidos para la frecuencia 2
			final List<MEXMEFrequency3> listHoras = getEXME_Frequency2().getFrequencies3();
			if(listHoras.isEmpty()) {
				throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.stopPolicy.frequency3", getEXME_Frequency2().getName()));
			}
			// se agrega como auxiliar para que no haya problemas al manipular los valores.
			final Date strtDate = new Date(start.getTimeInMillis());

			// la Duracion (dosis) seleccionada es "Auto"
			if (isAutoDose) {
				// mientras la fecha inicial no pase de la final (segun autoStopPolicy)
				while (start.getTime().before(endDate.getTime())) {
					for (MEXMEFrequency3 freq3 : listHoras) {
						// si la nueva fecha inicial es posterior a la fecha seleccionada de inicio
						if (freq3.isDateAfter(start, strtDate)
						// y la nueva fecha inicial es previa ala fecha final
							&& start.getTime().before(endDate.getTime())) {
							// se agrega la nueva hora
							listDates.add(new Date(start.getTimeInMillis()));
							if (isPRN() || lastDose != null) {
								break;
							}
						}
					}
					if ((isPRN() || lastDose != null) && !listDates.isEmpty()) {
						if (lastDose == null) {
							endDate.setTime(start.getTime());
							endDate.add(Calendar.DAY_OF_MONTH, policy.getMaxAutoDuration());// autostop
						}
						endingDate = endDate.getTime();
						break;
					}
					// aumentar al siguiente dia
					Utilerias.addDay(start);
				}
			}
			// la Duracion (dosis) seleccionada es diferente "Auto"
			else {
				// Itera por numero de dosis
				for (int i = 0; i <= numDosis; i++) {
					for (MEXMEFrequency3 freq3 : listHoras) {
						// si la nueva fecha inicial es posterior a la fecha seleccionada de inicio
						if (freq3.isDateAfter(start, strtDate)
						// y la cantidad de fechas calculadas no sobrepasa el num de dosis
							&& listDates.size() < numDosis) {
							// se agrega la nueva hora
							listDates.add(new Date(start.getTimeInMillis()));
							if (lastDose != null && !listDates.isEmpty()) {
								break;
							}
						}
					}
					if (lastDose != null && !listDates.isEmpty()) {
						break;
					}
					// aumentar al siguiente dia
					Utilerias.addDay(start);
					// hasta el numero de dosis definida
					if (listDates.size() == numDosis) {
						break;
					}
				}
			}
			// Asignar fecha final
			if (!listDates.isEmpty()) {
				map.put(START_DATE, listDates.get(0));
				map.put(END_DATE, endingDate == null ? listDates.get(listDates.size() - 1) : endingDate);
			}
		}
		map.put(LIST_DATES, listDates);
		return map;
	}

	/**
	 * @param trxName
	 * @return
	 */
	public MPlanMed getFromRxId(final String trxName) {
		return MPlanMed.getFromRxId(getCtx(), get_ID(), trxName);
	}

	/**
	 * Obtiene la Prescripcion en base al EXME_PrescRX_ID
	 * 
	 * @param trxName
	 * @return MEXMEPrescription
	 */
	public MEXMEPrescription getPrescription(final boolean createNew, final String trxName) {
		return MEXMEPrescription.getPrescriptionByPrescRxId(getCtx(), getEXME_PrescRX_ID(), createNew, trxName);
	}

	private MEXMEPrescRX	prescRx		= null;
	private MEXMEFrequency1	freq1		= null;
	private MEXMEFrequency2	freq2		= null;
	private MProduct		mProducto	= null;
	private MPlanMed	planMed		= null;


	public MEXMEPrescRX getPrescRx(boolean reQuery) {
		if (prescRx == null || reQuery) {
			prescRx = new MEXMEPrescRX(getCtx(), getEXME_PrescRX_ID(), get_TrxName());
		}
		return prescRx;
	}
	public MPlanMed getPlanMed(boolean reQuery) {
		if (planMed == null || reQuery) {
			planMed = getFromRxId(get_TrxName());
		}
		return planMed;
	}
	
	@Override
	public MEXMEFrequency1 getEXME_Frequency1() throws RuntimeException {
		if (freq1 == null) {
			freq1 = new MEXMEFrequency1(getCtx(), getEXME_Frequency1_ID(), get_TrxName());
		}
		return freq1;
	}
	@Override
	public MEXMEFrequency2 getEXME_Frequency2() throws RuntimeException {
		if (freq2 == null) {
			freq2 = new MEXMEFrequency2(getCtx(), getEXME_Frequency2_ID(), get_TrxName());
		}
		return freq2;
	}
	
	@Override
	public MProduct getM_Product() throws RuntimeException {
		if (mProducto == null && getM_Product_ID() > 0) {
			mProducto = new MEXMEProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return mProducto;
	}
	
	@Override
	public MEXMEGenProduct getEXME_GenProduct() throws RuntimeException {
		if (genProduct == null && getEXME_GenProduct_ID() > 0) {
			genProduct = new MEXMEGenProduct(getCtx(), getEXME_GenProduct_ID(), get_TrxName());
		}
		return genProduct;
	}
	
	public int getEXME_CtaPac_ID() {
		if(getEXME_PrescRX_ID() > 0){
			return getPrescRx(false).getEXME_CtaPac_ID();
		}
		return -1;
	}

	/** valida si el producto generico es vacuna, es decir la clase del producto relacionado es 'IM' */
	public boolean isVaccineRx() {
		boolean retValue = false;
		if (getM_Product_ID() > 0 && MEXMEMejoras.isNDC(getCtx(), getM_Product_ID())) {
			retValue = MProduct.PRODUCTCLASS_Immunization.equals(getM_Product().getProductClass());
		} else if(getEXME_GenProduct_ID() > 0){
			retValue = getEXME_GenProduct().isProductClass(MProduct.PRODUCTCLASS_Immunization);
		}
		return retValue;
	}
	
	/** recupera la vacuna relacionada a la prescripcion */
	public MEXMEVacuna getVaccine() {
		MEXMEVacuna retValue = null;
		if (getM_Product_ID() > 0 // si es NDC y el productClass es IM
				&& MEXMEMejoras.isNDC(getCtx(), getM_Product_ID())
				&& MProduct.PRODUCTCLASS_Immunization.equals(getM_Product().getProductClass())) {
			retValue = MEXMEVacuna.getFromNDC(getCtx(), getM_Product_ID());

		} else if (getEXME_GenProduct_ID() > 0 // si el productclass de un producto relacionado es IM
				&& getEXME_GenProduct().isProductClass(MProduct.PRODUCTCLASS_Immunization)) {
			retValue = MEXMEVacuna.getFromRxNorm(getCtx(), getEXME_GenProduct_ID());
		}
		return retValue;
	}
	
	public static List<MEXMEPrescRXDet> getActiveMedications(Properties ctx, MEXMECitaMedica exmeCitaMedica){
		List<MEXMEPrescRXDet> list = new ArrayList<MEXMEPrescRXDet>();
		try {
			List<MEXMEPrescRXDet> lstPrescRXDet = MEXMEPrescRXDet.getSavedMeds(ctx, null, true, exmeCitaMedica.getEXME_Paciente_ID());
			for (MEXMEPrescRXDet prescRXDet : lstPrescRXDet) {
				if (prescRXDet.getEXME_ActPacienteInd_ID() == 0 || 
						(prescRXDet.getEXME_PrescRX().getEXME_CtaPac_ID() != exmeCitaMedica.getEXME_CtaPac_ID()
							&& prescRXDet.getEXME_ActPacienteInd_ID() != 0)) {
					
					list.add(prescRXDet);;
				}
			}
		}catch (Exception e) {
			log.log(Level.SEVERE, "getActiveMedications", e);
		}
		return list;
	}
	
	
	/*Propiedades para reporte eMar 4 Horas / emar4Hours.jasper*/
	/** @return Generic Product Name */
	public String getGenProdName(){
		return getEXME_GenProduct().getGeneric_Product_Name();
	}
	/** @return Via de administracion||Exme_Route.name */
	public String getRoute(){
		if(getEXME_Route() != null){
			return getEXME_ViasAdministracion().getName();
		}else{
			return getEXME_Route().getDescription1();
		}
	}

	/** @return Frequency1 + Frequency2 */
	public String getFrequency() {
		final StringBuilder str = new StringBuilder();
		str.append(getFreq1());
		if (str.length() > 0) {
			str.append(" ");
		}
		str.append(getFreq2());
		return str.toString();
	}

	/** @return Frequency1 */
	public String getFreq1() {
		return getEXME_Frequency1_ID() > 0 ? getEXME_Frequency1().getName() : "";
	}

	/** @return Frequency2 */
	public String getFreq2() {
		return getEXME_Frequency2_ID() > 0 ? getEXME_Frequency2().getName() : "";
	}

	/**  @return Updated by name */
	public String getUserUpdate(){
		return MUser.getUserName(getCtx(), getUpdatedBy());
	}
	/** @return Last application date */
	public Timestamp getLastDose(){
		final MPlanMed exmePlanMed = MPlanMed.getFromRxId(getCtx(), getEXME_PrescRXDet_ID(), get_TrxName());
		if(exmePlanMed.getLastDose(false) != null){
			return exmePlanMed.getLastDose(false).getApliedDate();
		}else{
			return null;
		}
	}
	/** @return total of administration in the last 24 hours  (MPlanMed getAdmistratedDoses)  */
	public int getAdministered24(){
		final MPlanMed exmePlanMed = MPlanMed.getFromRxId(getCtx(), getEXME_PrescRXDet_ID(), get_TrxName());
		return exmePlanMed.getAdmistratedDoses(1);
	}
	
	/** @return Order Type label  */
	public String getOrderTypeStr() {
		return MRefList.getListName(getCtx(), ORDERTYPE_AD_Reference_ID, COLUMNNAME_OrderType);
	}
	
	/** @return Read back label: Yes / No */
	public String getReadBackStr() {
		return MEXMECuidadosPac.getReadBackLabel(this);
	}

	/** @return Noted label: User Pin (or name) and date (dd/MM HH:mm)  */
	public String getNotedStr() {
		return MEXMECuidadosPac.getUserPINDate(getNotedBy(), getNotedDate());
	}

	@Override
	public String getPatientName() {
		return  ((MEXMECtaPac)getEXME_PrescRX().getEXME_CtaPac()).getPatientName();
	}

	/** @return Cadena con la informacion de la medicacion .- Lama */
	public StringBuilder getMedicationDetail() {
		final StringBuilder str = new StringBuilder();
		// medication
		if (getEXME_GenProduct_ID() > 0 || getM_Product_ID() > 0) {
			str.append("<b>").append(Utilerias.getMsg(getCtx(), "progMed.articulo")).append("</b>: ");
			str.append(getMedicationName()).append(". ");//Card #1165
		}
		if (StringUtils.isNotBlank(getDoseRate())) {
			str.append(getDoseRate()).append(" ");
		}
		if (getEXME_Route_ID() > 0 || getEXME_ViasAdministracion_ID() > 0) {
			str.append(getRouteName());
		}
		if (getEXME_Frequency1_ID() > 0) {
			str.append(". ").append(getFrequency()).append(". ");
		}
		if (StringUtils.isNotBlank(getIndicaciones())) {
			str.append(" <b>").append(getMsgTranslate(COLUMNNAME_Indicaciones)).append("</b>: ");
			str.append(getIndicaciones()).append(". ");
		}
		if (MEXMEPrescRX.TIPO_HomeMedication.equals(getEXME_PrescRX().getTipo())) {
			if (getLastDay() != null) {
				str.append(" <b>").append(Utilerias.getMsg(getCtx(), "msj.lasttaken"));// agregar (PTA)
				str.append(" (PTA)");
				str.append("</b>: ");
				str.append(Constantes.sdfFecha(getCtx()).formatConvert(getLastDay()));
			}
		} else if (getStartDate() != null) {
			str.append("<br><b>").append(Utilerias.getMsg(getCtx(), "msg.moConsultarCitas.finicio"));
			str.append("</b>: ");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getStartDate()));
			if(getLastDay() != null && MEXMEPrescRX.TIPO_MedicalPrescription.equals(getEXME_PrescRX().getTipo())) {
				str.append(" <b>").append(getMsgTranslate(I_EXME_PlanMed.COLUMNNAME_EndDate));
				str.append("</b>: ");
				str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getLastDay()));
			}
		}
		return str;
	}
	
	@Override
	public String getSummaryDetail() {
		final StringBuilder str = getMedicationDetail();//Lama
		// order type
		if(StringUtils.isNotBlank(getOrderType())) {
			str.append("<br><b>").append(getMsgTranslate( COLUMNNAME_OrderType)).append("</b>: ");
			str.append(getOrderType());
			if(StringUtils.isNotBlank(getReadBack())) {
				str.append("  <b>").append(getMsgTranslate( COLUMNNAME_ReadBack)).append("</b>: ");
				str.append(getReadBackStr());
			} 
		}
		return str.toString();
	}
	
	/** Regresa la cadena de la receta sin formato, utilizado para reporte*/
	public String getSummaryNoFormat() {
		String str = StringUtils.substringAfter(getMedicationDetail().toString(), ":");
		return StringUtils.replaceEach(str, new String[] {"</b>","<b>","<br>"}, new String[] {"","",""});
	}
	
	/** @return requested by and prescription detail .- Lama Card #1173 */
	public String getDischargeDetail() {
		final StringBuilder str = new StringBuilder();
		// physician, nurse
		if (StringUtils.isNotBlank(getOrderType())) {
			str.append("<b>").append(getMsgTranslate(COLUMNNAME_EXME_Medico_ID)).append("</b>: ");
			str.append(getEXME_Medico().getNombre_Med()).append(".");
			if (!isAuthenticated()) {// solo si no esta autenticado, mostrar usuario (enfermera/farmacia)
				str.append("  <b>").append(getMsgTranslate(COLUMN_CreatedBy)).append("</b>: ");
				str.append(getCreatedBy(true)).append(".");
			}
			str.append("<br>");
		}
		str.append(getMedicationDetail());
		return str.toString();
	}

	@Override
	public boolean setAction(String action) {
		if (ACTION_AUTHENTICATE.equals(action) || ACTION_REVIEW.equals(action)) {
			return approveIt();
		} else if (ACTION_DISCONTINUE.equals(action)) {
			return voidIt();
		} else if(ACTION_RENEW.equals(action)) {
			return renewOrder(false);
		}
		return true;
	}
	
	@Override
	public String getRecordType() {
		return getMsgTranslate("Medications");
	}
	
	/**
	 * Renovar orden-
	 * Crea una orden nueva a partir de la existente, sin cancelar la anterior.
	 * El inicio de la orden debe ser en al menos una hora posterior a la fecha final de la orden.
	 * Si el proceso se completa se actualiza la columna Status='Y'
	 * @param ignoreChanges si es TRUE, ignora cambios realizados en la orden y solo guarda la columa Status.
	 * @return true si el proceso se ejecuto correctamente.
	 */
	public boolean renewOrder(boolean ignoreChanges) {
		if (is_new()) {
			return false;
		}
		// prescription header
		final MEXMEPrescRX mPrescRX = getPrescRx(false);
		if (mPrescRX.is_new() || !MEXMEPrescRX.TIPO_MedicalPrescription.equals(mPrescRX.getTipo())) {
			return false;
		}
		// nuevo medicamento
		final MEXMEPrescRXDet newPrescRXDet = mPrescRX.newDetail();
		copyValues(this, newPrescRXDet, true, true);
		// remove info
		newPrescRXDet.setIsActive(true);
		// Lama: no se debe llenar por defecto
		newPrescRXDet.set_ValueNoCheck(COLUMNNAME_NotedBy, null);
		newPrescRXDet.setNotedDate(null);
		newPrescRXDet.set_ValueNoCheck(COLUMNNAME_AuthenticatedBy, null);
		newPrescRXDet.setAuthenticated_Date(null);
		newPrescRXDet.setAuthenticated(Env.getEXME_Medico_ID(getCtx()) > 0);
		newPrescRXDet.setRXReview(null);
		newPrescRXDet.setRXReviewC(null);
		// start date
		if (getStartDate().before(getLastDay()) || getStartDate().equals(getLastDay())) {
			final Calendar cal = Calendar.getInstance();
			cal.setTime(getLastDay());
			cal.add(Calendar.HOUR_OF_DAY, 1);
			newPrescRXDet.setStartDate(new Timestamp(cal.getTimeInMillis()));
			newPrescRXDet.setLastDay(null);
		}
		// schedule prescription document
		final MEXMEPrescription prescription = mPrescRX.getPrescription(false);
		if (prescription == null || prescription.is_new()) {
			return false;
		}
		// scheduled doses
		boolean success = newPrescRXDet.savePlanMed(prescription, null);
		if (success) {
			setStatus(success);
			if (ignoreChanges) {
				// guarda si fue existoso o no, sin guardar los cambios previos
				final MEXMEPrescRXDet det = new MEXMEPrescRXDet(getCtx(), getEXME_PrescRXDet_ID(), null);
				det.setStatus(success);
				success = det.save(get_TrxName());
			} else {
				success = save();
			}
			// Plan
			final MPlanMed plan = getPlanMed(false);// no requery!!!
			if (plan.isInsulin()) {
				// Plan 2 - save insulin information
				final MPlanMed plan2 = newPrescRXDet.getPlanMed(false);
				if (!plan2.is_new()) {
					plan2.setEXME_EsqInsulina_ID(plan.getEXME_EsqInsulina_ID());
					plan2.setTipo(plan.getTipo());
					if (!plan2.save()) {
						throw new MedsysException();
					}
				}
			}
		}
		return success;
	}
	
	
	/**
	 * Obtiene todas las lineas de prescripciones home medication de una cuenta paciente determinada.
	 * @author natalia
	 * @param ctx
	 * @param medID
	 * @param pacID
	 * @param genID
	 * @param trxName
	 * @return
	 */
	public static List<PrescERXReqVO> getPrescByAppt(Properties ctx, int medID, int pacID, int genID, String trxName) {

		ArrayList<PrescERXReqVO> lista = new ArrayList<PrescERXReqVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append(" SELECT rxd.exme_prescrxdet_id, ")
		    .append(" rxd.exme_genproduct_id, ")
		    .append(" cta.documentno, ")
		    .append(" TO_CHAR(c.fechahrini, 'mm/dd/yyyy hh24:mi') AS fechahrini, ")
		    .append(" TO_CHAR(c.fechahrfin, 'mm/dd/yyyy hh24:mi') AS fechahrfin, ")
		    .append(" gp.generic_product_name, ")
		    .append(" p.value, ")
		    .append(" p.description, ")
		    .append(" p.help, ")
		    .append(" p.m_product_id ")
		  .append(" FROM exme_citamedica c ")
		  .append(" INNER JOIN exme_prescrx rx  ON rx.exme_citamedica_id = c.exme_citamedica_id ")
		  .append(" INNER JOIN exme_prescrxdet rxd  ON rxd.exme_prescrx_id = rx.exme_prescrx_id ")
		  .append(" INNER JOIN exme_ctapac cta  ON cta.exme_ctapac_id = c.exme_ctapac_id ")
		  .append(" INNER JOIN exme_genproduct gp  ON gp.exme_genproduct_id = rxd.exme_genproduct_id ")
		  .append(" INNER JOIN m_product p  ON p.m_product_id = rxd.m_product_id ")
		  .append(" Where c.exme_medico_id = ? ")
		  .append(" and c.exme_paciente_id = ? ")
		  .append(" AND rxd.exme_genproduct_id =  ? ")
		  .append(" ORDER BY c.created DESC ");
			
			

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, medID);
			pstmt.setInt(2, pacID);
			pstmt.setInt(3, genID);
			rs = pstmt.executeQuery();
			PrescERXReqVO prescRX = null;
			while (rs.next()) {
				prescRX = new PrescERXReqVO();
				prescRX.setEXME_PrescRXDet_ID(rs.getInt(1));
				prescRX.setEXME_GenProduct_ID(rs.getInt(2));
				prescRX.setDocumentNo(rs.getString(3));
				prescRX.setFechaHrIni(rs.getString(4));
				prescRX.setFechaHrFin(rs.getString(5));
				prescRX.setGeneric_Product_Name(rs.getString(6));
				prescRX.setValue(rs.getString(7));
				prescRX.setDescription(rs.getString(8));
				prescRX.setHelp(rs.getString(9));
				prescRX.setM_Product_ID(rs.getInt(10));
				
				lista.add(prescRX);
			}

		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}
	/**
	 * Prn 
	 * @return isPrn
	 */
	public String getIsPrnString(){
		return  Constantes.yesNoStr(getCtx(), this.isPRN());
	}

	public String getNombreMed(){
		return getEXME_Medico().getNombre_Med();
	}
	
	public String getMedicationName(){
		// Medicamento
		String medicationName;
		if (getM_Product_ID() > 0 && MEXMEMejoras.isNDC(getCtx(), getM_Product_ID())) {
			medicationName = getM_Product().getName();
		} else if (getEXME_GenProduct_ID() > 0) {
			medicationName = getEXME_GenProduct().getGeneric_Product_Name();
		} else {
			medicationName = "";
		}
		return medicationName;
	}
	public String getDuracion(){
		return MRefList.getListName(getCtx(), X_EXME_PrescRXDet.DOSE_AD_Reference_ID, getDose());
		
	}

	@Override
	public boolean isAuthenticatedDisc() {
		// Card #746 - Authentication of Discontinuation Orders
		return MEXMEDiscontinueOrder.isAuthenticatedDisc(this);
	}

	/** Backorder document */
	private String backOrder = null;
	/** Id chargemaster */
	private int chargeMaster = 0;
	/** qty Available */ 
	private BigDecimal qtyAvailable = Env.ZERO;
	/** Resultado de la distribucion */
	private List<BeanView> distribucion = new ArrayList<BeanView>();
	/** qty Fill */ 
	private BigDecimal qtyFill = Env.ZERO;
	/** qty */ 
	private int dispenseId = 0;
	/** */
	private List<MEXMEPrescRXDet> lstNDCs = new ArrayList<MEXMEPrescRXDet>();
	

	/**
	 * Stock
	 * Solicitud/Surtido
	 * Confirmación
	 * @param confTransOrder Confirm  transfer order
	 * @return
	 * @throws MedsysException 
	 */
	public boolean stock(final boolean surtidoCompleto) throws MedsysException {
		boolean success = true;
		final String trxNameOld = get_TrxName();
		set_TrxName(null);
		
		if(MEXMEMejoras.isControlaExistencias(getAD_Client_ID(), getAD_Org_ID(), null)){
			StockTransfer.prescRXDetTransfer(getCtx(), this);
		}
		
		set_TrxName(trxNameOld);
		fillDispense(surtidoCompleto);
		return success;
	}
	
	/**
	 * Guardar la cantidad pendiente de entregar
	 * @return
	 */
	private boolean fillDispense(final boolean surtidoCompleto){
		final X_EXME_Dispense mDispense = new X_EXME_Dispense(getCtx(), dispenseId, get_TrxName());
		mDispense.setEXME_Pharmacist_ID(Env.getEXME_Pharmacist_ID(getCtx()));
		mDispense.setEXME_PrescRXDet_ID(getEXME_PrescRXDet_ID());//setQtyFill(getQuantity());
		mDispense.setDeliveredQty(dispenseId>0?mDispense.getDeliveredQty().add(getQtyFill()):getQtyFill());
		mDispense.setUndeliveredQty(getQuantity().subtract(mDispense.getDeliveredQty()));
		mDispense.setFecha (new Timestamp (System.currentTimeMillis()));
		boolean success = mDispense.save();
//		if(success && mDispense.getUndeliveredQty().compareTo(Env.ZERO)<=0){
		if(surtidoCompleto){
			setIsDelivered(true);
			success = save(get_TrxName());
		}
		return success;
	}
	
	public boolean isProductInChargeMaster() {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT po.EXME_ProductoOrg_ID                          \n");
		sql.append("FROM  EXME_ProductoOrg po                              \n");
		sql.append("WHERE    po.IsActive            = 'Y'                  \n");
		sql.append("  AND    po.AD_Org_ID           = ?                    \n");
		sql.append("  AND    po.exme_product_id     = ?                    \n");
		return DB.getSQLValue(get_TrxName(), sql.toString(), getAD_Org_ID(), getM_Product_ID()) > 0;
	}

	public boolean isQtyAvailable() {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(s.QtyOnHand)-sum(s.QtyReserved) as Unit     \n");
		sql.append("FROM       M_Storage s                                 \n");
		sql.append("INNER JOIN EXME_ProductoOrg  po ON                      \n");
		sql.append("         po.m_product_id        = s.m_product_id       \n");
		sql.append("  AND    po.AD_Org_ID           = ?                    \n");
		sql.append("  AND    po.IsActive            = 'Y'                  \n");
		sql.append("  AND    po.m_product_id     = ?                    \n");
		sql.append("INNER JOIN M_Locator         l ON                      \n");
		sql.append("         s.M_Locator_ID         = l.M_Locator_ID       \n");
		sql.append("     AND l.IsActive             = 'Y'                  \n");
		sql.append("     AND l.M_Warehouse_ID       = ?                    \n");
		final BigDecimal bd = DB.getSQLValueBD(get_TrxName(), 
			sql.toString(), 
			getAD_Org_ID(), 
			getM_Product_ID(), 
			Env.getM_Warehouse_ID(getCtx()));
		setQtyAvailable(bd);
		return (bd == null ? BigDecimal.ZERO : bd).compareTo(getQuantity()) >= 0;
	}
	
	/**
	 * Almacen que corresponde solicitar
	 * deacuerdo a la cuenta paciente
	 * @return
	 * FIXME usar {@link #getEXME_EstServ_ID()}
	 * @deprecated
	 */
	public int getMWarehouseCtaPacID (){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT wr.M_Warehouse_ID ") 
		.append(" FROM  EXME_PrescRXDet ")
		.append(" INNER JOIN EXME_PrescRX    prx ON prx.EXME_PrescRX_ID = EXME_PrescRXDet.EXME_PrescRX_ID ")
		.append(" INNER JOIN EXME_CtaPac     cta ON cta.EXME_CtaPac_ID  = prx.EXME_CtaPac_ID ")
		.append(" INNER JOIN EXME_EstServAlm wr  ON wr.EXME_EstServ_ID  = cta.EXME_EstServ_ID AND wr.IsActive = 'Y' ")
		.append(" WHERE EXME_PrescRXDet.EXME_PrescRXDet_ID = ? ");
		return DB.getSQLValue(null, sql.toString(), getEXME_PrescRXDet_ID());
	}
	
	public String getBackOrder() {
		return backOrder;
	}

	public void setBackOrder(final String backOrder) {
		this.backOrder = backOrder;
	}

	public int getChargeMaster() {
		return chargeMaster;
	}

	public void setChargeMaster(final int chargeMaster) {
		this.chargeMaster = chargeMaster;
	}

	public BigDecimal getQtyAvailable() {
		if(qtyAvailable == null){
			qtyAvailable = Env.ZERO;
		}
		return qtyAvailable;
	}
	
	public void setQtyAvailable(final BigDecimal qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}
	public BigDecimal getQtyFill() {
		return qtyFill;
	}

	public void setQtyFill(BigDecimal qtyFill) {
		this.qtyFill = qtyFill;
	}
	public int getDispenseId() {
		return dispenseId;
	}

	public void setDispenseId(int dispenseId) {
		this.dispenseId = dispenseId;
	}
	
	public List<BeanView> getDistribucion() {
		return distribucion;
	}
	public void setDistribucion(List<BeanView> distribucion) {
		this.distribucion = distribucion;
	}
	public List<MEXMEPrescRXDet> getLstNDCs() {
		return lstNDCs;
	}
	public void setLstNDCs(List<MEXMEPrescRXDet> lstNDCs) {
		this.lstNDCs = lstNDCs;
	}
	
	@Override
	public int getAuthenticatedBy() {
		int authenticatedById;
		if (!isAuthenticated() || getAuthenticated_Date() == null) {
			authenticatedById = -1;
		} else if (isAuthenticated() && super.getAuthenticatedBy() <= 0) {
			authenticatedById = super.getUpdatedBy();
		} else {
			authenticatedById = super.getAuthenticatedBy();
		}
		return authenticatedById;
	}
	
	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_Authenticated_Date.hashCode()) {
			comparable = getAuthenticated_Date();
		} else if (type == COLUMNNAME_AuthenticatedBy.hashCode()) {
			comparable = getUserAuthenticated();
		} else if (type == COLUMNNAME_DiscontinuedDate.hashCode()) {
			comparable = getDiscontinuedDate();
		} else if (type == COLUMNNAME_Dose.hashCode()) {
			comparable = getDose();
		} else if (type == COLUMNNAME_DoseRate.hashCode()) {
			comparable = getDoseRate();
		} else if (type == COLUMNNAME_EXME_Medico_ID.hashCode()) {
			comparable = getNombreMed();
		} else if (type == COLUMNNAME_EXME_ViasAdministracion_ID.hashCode()) {
			comparable = getRouteName();
		} else if (type == COLUMNNAME_EXME_Frequency1_ID.hashCode()) {
			comparable = getFreq1();
		} else if (type == COLUMNNAME_EXME_Frequency2_ID.hashCode()) {
			comparable = getFreq2();
		} else if (type == COLUMNNAME_EXME_GenProduct_ID.hashCode() || type == COLUMNNAME_M_Product_ID.hashCode()) {
			comparable = getMedicationName();
		} else if (type == COLUMNNAME_Indicaciones.hashCode()) {
			comparable = getIndicaciones();
		} else if (type == COLUMNNAME_IsPRN.hashCode()) {
			comparable = getIsPrnString();
		} else if (type == COLUMNNAME_NotedBy.hashCode()) {
			comparable = getUserNoted();
		} else if (type == COLUMNNAME_NotedDate.hashCode()) {
			comparable = getNotedDate();
		} else if (type == COLUMNNAME_OrderType.hashCode()) {
			comparable = getOrderTypeStr();
		} else if (type == COLUMNNAME_ReadBack.hashCode()) {
			comparable = getReadBackStr();
		} else if (type == COLUMNNAME_StartDate.hashCode()) {
			comparable = getStartDate();
		} else if (type == COLUMNNAME_Type.hashCode()) {
			comparable = getType();
		} else if (!isActive() && getDis() != null) {
			comparable = dis.onCompare(type);
		} else {
			comparable = getPrescRx(false).onCompare(type);
		}
		return comparable;
	}

	/** @return enteredBy */
	public String getUserEntered() {
		return MUser.getUserName(getCtx(), getCreatedBy());
	}

	/** @return AuthenticatedBy */
	public String getUserAuthenticated() {
		return !isAuthenticated() || getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), this.getAuthenticatedBy());
	}

	/** @return NotedBy */
	public String getUserNoted() {
		return getNotedDate() == null ? "" : MUser.getUserName(getCtx(), getNotedBy());
	}

	public MEXMEDiscontinueOrder	dis	= null;

	public MEXMEDiscontinueOrder getDis() {
		if (!isActive() && dis == null) {
			dis = MEXMEDiscontinueOrder.getFrom(this);
		}
		return dis;
	}
	
	/** @return Discontinued date */
	public Timestamp getDiscontinuedDate() {//RQM #4834
		return getDis() == null ? super.getDiscontinuedDate() : dis.getDiscontinuedDate();
	}

	/**  @return AuthenticatedBy */
	public String getDiscontinuedBy() {
		return getDis() == null ? "" : dis.getCreatedBy(true);
	}

	/** @return DiscontinuedOrderTypeStr() */
	public String getDiscontinuedOrderTypeStr() {
		return getDis() == null ? "" : dis.getOrderTypeStr();
	}

	/** @return DiscontinuedNombre_Med */
	public String getDiscontinuedNombre_Med() {
		return getDis() == null ? "" : dis.getEXME_Medico().getNombre_Med();
	}

	/** @return Authenticated date */
	public Timestamp getDiscontinuedAuthenticatedDate() {
		return getDis() == null ? null : dis.getAuthenticated_Date();
	}
	
	/** @return DiscontinuedAuthenticatedBy */
	public String getUserDiscontinuedAuthenticatedBy() {
		return getDis() == null || dis.getAuthenticated_Date() == null ? "" : MUser.getUserName(getCtx(), dis.getAuthenticatedBy());
	}

	/**  @return DiscontinuedReadBackStr */
	public String getDiscontinuedReadBackStr() {
		return getDis() == null ? "" : MEXMECuidadosPac.getReadBackLabel(dis);
	}
	
	public boolean isReviewed() {
		return getRXReview() != null || getRXReviewC() != null;
	}
	
}