/*
 * Created on 23/06/2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.joda.time.DateTime;

/**
 * Modelo de Plan de Medicamentos Linea
 * <b>Modificado: </b> $Author: Lorena Lama $<p>
 * <b>En :</b> $Date: 2006/09/27 23:07:05 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.6 $
 */
public class MPlanMedLine extends X_EXME_PlanMedLine {

	/** serialVersionUID */
	private static final long serialVersionUID = 6244492531433898705L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MPlanMedLine.class);
	/** */
	private MPlanMed planMed;
	/** dosis seleccionada (modelo) */
	private boolean selectDoses = false;
	
	/**
     * @param ctx
     * @param EXME_PlanMedLine_ID
     * @param trxName
     */
    public MPlanMedLine(Properties ctx, int EXME_PlanMedLine_ID, String trxName) {
        super(ctx, EXME_PlanMedLine_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MPlanMedLine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public String toString() {
        return "Plan: " + getEXME_PlanMed_ID() + " PlanLine: " + getEXME_PlanMedLine_ID();
    }
    
	/**
	 * Obtiene las lineas del plan de medicamento
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 * @deprecated
	 */
	public static List<MPlanMedLine> getPlanMedLine(Properties ctx, String sql, List<?> params, String trxName) {
		List<MPlanMedLine> results = new ArrayList<MPlanMedLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {// Defecto 1730 - se agrega parametro transaccion;
			pstmt = DB.prepareStatement(DB.getDatabase().convertStatement(sql.toString()), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MPlanMedLine planMedLine = new MPlanMedLine(ctx, rs, trxName);
				results.add(planMedLine);
			}
		}
		catch (SQLException sqle) {
			log.log(Level.SEVERE, sql, sqle);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return results;
	}

	/**
	 * Obtiene la cantidad de dosis programadas de los medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * 
	 * @param ctx
	 * @param exmeCtaPacId 	cuenta paciente
	 * @param dateIni 		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin 		fecha final
	 * @param estatus 		arreglo de estatus
	 * @return 	int			total de registros
	 */
	public static int getCountPrescRX(Properties ctx, int exmeCtaPacId, final Date dateIni, Date dateFin, String... estatus) {

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = getSQLPrescRX(ctx, true, exmeCtaPacId, dateIni, dateFin, null, null, false, false,params, estatus);

		return DB.getSQLValue(null, sql.toString(), params);
	}

	/**
	 * Obtiene la dosis programadas de los medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * 
	 * @param ctx
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param isPRN			PRN Orders<br>
	 * 						EXME_PRESCRXDET.ISPRN=Y
	 * @param past24Med		Muestra los medicamentos programados apartir de la fecha actual, hasta las 24
	 *            			siguientes horas<br>
	 *            			No son consideradas la fecha inicial y la fecha final.
	 * @param doctors 		arreglo de medicos que prescriben
	 * @param estatus		arreglo de estatus
	 * @return 	List<MPlanMedLine>
	 */
	public static List<MPlanMedLine> getLinesPrescRX(Properties ctx, int exmeCtaPacId, final Date dateIni, Date dateFin, Boolean isPRN,
		Integer[] doctors, boolean past24Med, String... estatus) {
		return getLinesPrescRX(ctx, exmeCtaPacId, dateIni, dateFin, isPRN, doctors, past24Med, false, estatus);
	}
	
	/**
	 * Obtiene la dosis programadas de los medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * 
	 * @param ctx
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param isPRN			PRN Orders<br>
	 * 						EXME_PRESCRXDET.ISPRN=Y
	 * @param past24Med		Muestra los medicamentos programados apartir de la fecha actual, hasta las 24
	 *            			siguientes horas<br>
	 *            			No son consideradas la fecha inicial y la fecha final.
	 * @param doctors 		arreglo de medicos que prescriben
	 * @param derechoHabiente
	 * @param estatus		arreglo de estatus
	 * @return 	List<MPlanMedLine>
	 */
	public static List<MPlanMedLine> getLinesPrescRX(Properties ctx, int exmeCtaPacId, final Date dateIni, Date dateFin, Boolean isPRN,
		Integer[] doctors, boolean past24Med, boolean derechoHabiente, String... estatus) {
		final List<MPlanMedLine> list = new ArrayList<MPlanMedLine>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = getSQLPrescRX(ctx, false, exmeCtaPacId, dateIni, dateFin, isPRN, doctors, past24Med, derechoHabiente, params, estatus);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			result = pstmt.executeQuery();

			while (result.next()) {
				list.add(new MPlanMedLine(ctx, result, null));
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
	 * Genera el SQL para obtener las dosis programadas de los medicamentos prescritos.<br>
	 * EXME_PRESCRX.TIPO=MedicalPrescription
	 * 
	 * @param ctx
	 * @param count			regresa solo el total de registros
	 * @param exmeCtaPacId	cuenta paciente
	 * @param dateIni		fecha inicial (si es nula, solo se toma la fecha final y se utiliza la fecha de aplicacion)
	 * @param dateFin		fecha final
	 * @param isPRN			PRN Orders<br>
	 * 						EXME_PRESCRXDET.ISPRN=Y
	 * @param doctors 		arreglo de medicos que prescriben
	 * @param past24Med		Muestra los medicamentos programados apartir de la fecha actual, hasta las 24
	 *            			siguientes horas<br>
	 *            			No son consideradas la fecha inicial y la fecha final.
	 * @param estatus		arreglo de estatus
	 * @return 				SQL
	 */
	public static StringBuilder getSQLPrescRX(Properties ctx, boolean count, int exmeCtaPacId, //
		final Date dateIni, Date dateFin, Boolean isPRN, // 
		Integer[] doctors, boolean past24Med, boolean derechoHabiente, final List<Object> params, String... estatus) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		if (count) {
			sql.append("SELECT count(EXME_PlanMedLine.EXME_PlanMedLine_ID) ");
		} else {
			sql.append("SELECT EXME_PlanMedLine.* ");
		}

		sql.append("FROM EXME_PlanMedLine ");
		sql.append("INNER JOIN EXME_PlanMed    pl ON (pl.EXME_PlanMed_ID    = EXME_PlanMedLine.EXME_PlanMed_ID");
		if (exmeCtaPacId > 0) {
			sql.append("                         AND  pl.EXME_CtaPac_ID     = ? ");// #1
			params.add(exmeCtaPacId);// #1
		}
		sql.append("                                  ) ");// #1

		sql.append("INNER JOIN EXME_PrescRxDet pD ON (pl.EXME_PrescRxDet_ID = pD.EXME_PrescRxDet_ID) ");
		sql.append("INNER JOIN EXME_PrescRx    pr ON (pr.EXME_PrescRx_ID    = pD.EXME_PrescRx_ID");
		sql.append("                             AND  pr.Tipo               = ?) ");// #2
		sql.append("WHERE EXME_PlanMedLine.IsActive='Y' ");

		params.add(MEXMEPrescRX.TIPO_MedicalPrescription);// #2

		// only PRN orders #3
		if (isPRN != null) {
			sql.append("AND COALESCE(pD.IsPRN,'N')=? ");
			params.add(DB.TO_STRING(isPRN));
		}
		// Debe mostrar los medicamentos programados apartir de la fecha actual, hasta las 24 horas siguientes
		if (past24Med) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_PlanMedLine.ProgDate, 'MI') <= ");
				sql.append("    TRUNC(" + DB.TO_DATE(Env.getCurrentDate(), false) + ",'MI')+1 ");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('minute', EXME_PlanMedLine.ProgDate) <= ");
				sql.append("    DATE_TRUNC('minute'," + DB.TO_DATE(Env.getCurrentDate(), false) + " )+1 ");
			}
		} else {
			// Dates (ini/fin) #4 & #5
			if (dateIni != null && dateFin != null) {
				if (DB.isOracle()) {
					sql.append("AND      TRUNC(EXME_PlanMedLine.ProgDate, 'MI') ");
				} else if (DB.isPostgreSQL()) {
					sql.append("AND      DATE_TRUNC('minute', EXME_PlanMedLine.ProgDate) ");
				}
				sql.append("BETWEEN  ? ");
				sql.append("AND      ? ");
				params.add(new Timestamp(dateIni.getTime()));
				params.add(new Timestamp(dateFin.getTime()));
			}
			// Only Final Date #4
			else if (dateIni != null) {
				if (DB.isOracle()) {
					sql.append("AND TRUNC(COALESCE(EXME_PlanMedLine.ApliedDate,EXME_PlanMedLine.ProgDate), 'MI') >= ?");
				} else if (DB.isPostgreSQL()) {
					sql.append("AND DATE_TRUNC('minute', COALESCE(EXME_PlanMedLine.ApliedDate,EXME_PlanMedLine.ProgDate)) >= ?");
				}
				params.add(new Timestamp(dateIni.getTime()));
			}
		}
		// status #6 ...
		if (estatus != null && estatus.length > 0) {
			sql.append("AND EXME_PlanMedLine.Estatus IN (").append(DB.TO_STRING(estatus)).append(") ");
			for (String status : estatus) {
				params.add(status);
			}
		}
		// doctors #7 ...
		if (doctors != null && doctors.length > 0) {
			sql.append("AND pr.EXME_MEDICO_ID IN (").append(DB.TO_STRING(doctors)).append(") ");
			for (int doctor : doctors) {
				params.add(doctor);
			}
		}
		//Card #1545 ProMujer 
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));// Only Client/Org records
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		
		if(!count) {
			// this one blows in postgresql if we're counting : 
			// org.postgresql.util.PSQLException: ERROR: column "exme_planmedline.progdate" 
			// must appear in the GROUP BY clause or be used in an aggregate function
			sql.append(" ORDER BY EXME_PlanMedLine.ProgDate ASC,  EXME_PlanMedLine.ApliedDate ASC ");
		}

		return sql;
	}
	
	/**
	 * Obtiene la fecha de la ultima dosis que se ha aplicado del planmed seleccionado
	 * @param ctx
	 * @param exmePlanMedId
	 * @param trxName
	 * @param derechoHabiente
	 * @return
	 */
	public static Timestamp getLastApliedDate(Properties ctx, int exmePlanMedId, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT MAX(ApliedDate) AS LastDose ");
		sql.append(" FROM EXME_PlanMedLine ");
		sql.append(" WHERE EXME_PlanMedLine.EXME_PlanMed_ID= ? AND EXME_PlanMedLine.IsActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValueTS(trxName, sql.toString(), exmePlanMedId);
	}
	
	/**
	 * Obtiene la fecha de la ultima dosis que se ha aplicado del planmed seleccionado
	 * @param ctx
	 * @param exmePlanMedId
	 * @param trxName 
	 * @return
	 */
	public static MPlanMedLine getLastDose(Properties ctx, int exmePlanMedId, boolean onlyApplied, String trxName) {
		final StringBuilder where = new StringBuilder();
		where.append("EXME_PlanMedLine.EXME_PlanMed_ID=?");// #1
		if (onlyApplied) {
			where.append(" AND EXME_PlanMedLine.ApliedDate IS NOT NULL ");
			where.append(" AND EXME_PlanMedLine.ESTATUS=").append(DB.TO_STRING(ESTATUS_Administered));
		}
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(exmePlanMedId)//
			.setOrderBy(onlyApplied ? " EXME_PlanMedLine.ApliedDate DESC " : " EXME_PlanMedLine.EXME_PlanMedLine_ID DESC ")//
			.first();
	}
	
	/**
	 * Obtiene la fecha de la ultima dosis que se ha aplicado del planmed seleccionado
	 * @param ctx
	 * @param exmePlanMedId
	 * @param trxName
	 * @return
	 */
	public static int getCountDoses(Properties ctx, int exmePlanMedId, String whereClause, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT count(EXME_PlanMedLine_ID) AS total ");
		sql.append(" FROM EXME_PlanMedLine ");
		sql.append(" WHERE EXME_PlanMedLine.EXME_PlanMed_ID=? AND EXME_PlanMedLine.IsActive='Y' ");
		sql.append(whereClause);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(trxName, sql.toString(), exmePlanMedId);
	}
	
	/**
	 * Objeto MPlanMed
	 * @return
	 */
	public MPlanMed getPlanMed() {
		if (planMed == null && getEXME_PlanMed_ID() > 0) {
			planMed = new MPlanMed(getCtx(), getEXME_PlanMed_ID(), get_TrxName());
		}
		return planMed;
	}

	/**
	 * Header
	 * @param planMed
	 */
	public void setPlanMed(MPlanMed planMed) {
		this.planMed = planMed;
	}
	
	/**
	 * Validamos si existen datos para el reporte de eMar 4 horas
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static boolean hasData(Properties ctx, int ctaPacID, String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT count(planMedLine.EXME_PLANMEDLINE_ID) ");
		sql.append(" FROM EXME_PLANMEDLINE planMedLine  ");
		sql.append(" INNER JOIN EXME_PLANMED    planMed   ON (planMed.EXME_PLANMED_ID = planMedLine.EXME_PLANMED_ID) ");
		sql.append(" INNER JOIN EXME_PRESCRXDET prescDet  ON (planMed.EXME_PRESCRXDET_ID = prescDet.EXME_PRESCRXDET_ID) ");
		sql.append(" INNER JOIN EXME_PRESCRX              ON (EXME_PRESCRX.EXME_PRESCRX_ID  = prescDet.EXME_PRESCRX_ID) ");
		sql.append(" WHERE planMed.EXME_CTAPAC_ID =? ");
		sql.append(" AND EXME_PRESCRX.TIPO =? ");
		sql.append(" AND planMedLine.PROGDATE >=? ");
		sql.append(" AND planMedLine.PROGDATE <= ? "); // 4 hours later
		sql.append(" AND planMedLine.ESTATUS NOT IN(");
		sql.append(DB.TO_STRING(ESTATUS_Held)).append(", ");
		sql.append(DB.TO_STRING(ESTATUS_Missed)).append(", ");
		sql.append(DB.TO_STRING(ESTATUS_AutoCancel)).append(") ");
		sql.append(" AND planMedLine.ISACTIVE ='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "planMedLine"));
		
		DateTime dt = new DateTime(Env.getCurrentDate()).plusHours(4);
		
		
		return DB.getSQLValue(
				trxName, 
				sql.toString(), 
				ctaPacID, // Encounter
				MEXMEPrescRX.TIPO_MedicalPrescription, // Type
				Env.getCurrentDate(), // StartDate
				new Timestamp(dt.getMillis()) // EndDate
		) > 0;
	}
	
	public static List<MPlanMedLine> getData(Properties ctx, int ctaPacID, String trxName) {
		List<MPlanMedLine> list = new ArrayList<MPlanMedLine>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT planMedLine.* ");
		sql.append(" FROM EXME_PLANMEDLINE planMedLine  ");
		sql.append(" INNER JOIN EXME_PLANMED    planMed   ON (planMed.EXME_PLANMED_ID = planMedLine.EXME_PLANMED_ID) ");
		sql.append(" INNER JOIN EXME_PRESCRXDET prescDet  ON (planMed.EXME_PRESCRXDET_ID = prescDet.EXME_PRESCRXDET_ID) ");
		sql.append(" INNER JOIN EXME_PRESCRX              ON (EXME_PRESCRX.EXME_PRESCRX_ID  = prescDet.EXME_PRESCRX_ID) ");
		sql.append(" WHERE planMed.EXME_CTAPAC_ID =? ");
		sql.append(" AND EXME_PRESCRX.TIPO =? ");
		sql.append(" AND planMedLine.PROGDATE >=? ");
		sql.append(" AND planMedLine.PROGDATE <= ? "); // 4 hours later
		sql.append(" AND planMedLine.ESTATUS NOT IN(");
		sql.append(DB.TO_STRING(ESTATUS_Held)).append(", ");
		sql.append(DB.TO_STRING(ESTATUS_Missed)).append(", ");
		sql.append(DB.TO_STRING(ESTATUS_AutoCancel)).append(") ");
		sql.append(" AND planMedLine.ISACTIVE ='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "planMedLine"));
		
		DateTime dt = new DateTime(Env.getCurrentDate()).plusHours(4);
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
//			DB.setParameters(pstmt, new Object[]{ctaPacID, // Encounter
//					MEXMEPrescRX.TIPO_MedicalPrescription, // Type
//					Env.getCurrentDate(), // StartDate
//					new Timestamp(dt.getMillis()}); // EndDate);
			pstmt.setInt(1, ctaPacID);
			pstmt.setString(2, MEXMEPrescRX.TIPO_MedicalPrescription);
			pstmt.setTimestamp(3, Env.getCurrentDate());
			pstmt.setTimestamp(4, new Timestamp(dt.getMillis()));
			result = pstmt.executeQuery();

			while (result.next()) {
				list.add(new MPlanMedLine(ctx, result, null));
			}
		}
		catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(result, pstmt);
		}
		return list;
//		
//		return DB.getSQLValue(
//				trxName, 
//				sql.toString(), 
//				ctaPacID, // Encounter
//				MEXMEPrescRX.TIPO_MedicalPrescription, // Type
//				Env.getCurrentDate(), // StartDate
//				new Timestamp(dt.getMillis()) // EndDate
//		) > 0;
	}
	
	/**
	 * La dosis ha sido seleccionada
	 * @return
	 */
	public boolean isSelectDoses() {
		return selectDoses;
	}

	/**
	 * True: Seleccionada
	 * @param selectDoses
	 */
	public void setSelectDoses(boolean selectDoses) {
		this.selectDoses = selectDoses;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			// if the medication is mark as held
			if (ESTATUS_Held.equals(getEstatus())
			// que sea de CPOE y que no tenga asignada una vacuna
					&& getPlanMed().getEXME_PrescRXDet_ID() > 0 //
					&& getEXME_Hist_Vacuna_ID() <= 0) {
				success = rejectVaccine();
			} else if (is_ValueChanged(COLUMNNAME_Estatus) && ESTATUS_Administered.equals(getEstatus()) && getPlanMed().isInsulin()) {
				// si se administra la linea y es insulina (crea el registro de control diabetico)
				final MEXMEEsqInsulinaDet det = new MEXMEEsqInsulinaDet(getCtx(), 0, get_TrxName());
				det.setEXME_CtaPac_ID(getPlanMed().getEXME_CtaPac_ID());
				det.setEXME_Enfermeria_ID(Env.getEXME_Enfermeria_ID(getCtx()));
				det.setEXME_PlanMedLine_ID(getEXME_PlanMedLine_ID());
				det.setCantidad(getUnidad());
				det.setDescription(getPlanMed().getInsulinStr());
				det.setFechaAplica(getApliedDate());
				det.setM_Product_ID(getPlanMed().getM_Product_ID());
				det.setTipo(getPlanMed().getTipo());
				success = det.save();
			}
		}
		return super.afterSave(newRecord, success);
	}
	
	/**
	 * Crea el registro de historico de vacuna como Rechazado, <br>
	 * Debe de estar relacionado a un registro de EXME_PrescRxDet y copia los datos coincidentes <br>
	 * copia el motivo (debe ser valido de lo contrario se queda como null)<br>
	 * El ID del historico de la vacuna se relaciona a la línea de detalle del plan de medicamentos
	 */
	public boolean rejectVaccine() {
		boolean success = true;
		try {
			final MEXMEPrescRXDet detail = new MEXMEPrescRXDet(getCtx(), //
					getPlanMed().getEXME_PrescRXDet_ID(), // Id de la prescripcion CPOE
					get_TrxName());
			// si es vacuna crear rechazo de vacuna
			final MEXMEVacuna vaccine = detail.getVaccine();
			if (vaccine != null) {
				final MEXMEVacunaDet vaccineDet = MEXMEVacunaDet.getDetalle(getCtx(), vaccine.get_ID()).get(0);
				vaccineDet.setVaccine(vaccine);
				final MEXMEHistVacuna histVacuna = vaccineDet.createHistVaccine();
				histVacuna.setFromPresRx(detail);
				histVacuna.setRejected(true);
				histVacuna.setMotivoRechazo(getMotivos());// same reason
				success = histVacuna.save(get_TrxName());
				if (success) {
					setEXME_Hist_Vacuna_ID(histVacuna.getEXME_Hist_Vacuna_ID());
					success = saveUpdate();
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			success = false;
		}
		return success;
	}
	
	/**
	 * Crea el registro de historico de vacuna para una administración de medicamentos<br>
	 * Debe de estar relacionado a un registro de EXME_PrescRxDet <br>
	 * El ID del historico de la vacuna se relaciona a la línea de detalle del plan de medicamentos
	 */
	public boolean applyVAccine(final MEXMEHistVacuna histVacuna) {
		boolean success = true;
		try {
			// debe estar administrado y no tener asignada una vacuna
			if (isActive() && ESTATUS_Administered.equals(getEstatus()) 
					&& getEXME_Hist_Vacuna_ID() <= 0) {
				histVacuna.set_TrxName(get_TrxName());
				success = histVacuna.save(get_TrxName());
				if (success) {
					setEXME_Hist_Vacuna_ID(histVacuna.getEXME_Hist_Vacuna_ID());
					success = saveUpdate();
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			success = false;
		}
		return success;
	}
	
	/*Propiedades para reporte eMar 4 Horas / emar4Hours.jasper*/
	/**
	 * Nombre del producto generico aplicado
	 * @return Generic Product Name 
	 */
	public String getMedName(){
		return getPlanMed().getPrescRXDet().getMedicationName();
	}
	/**
	 * Tasa/Dosis
	 * @return DoseRate
	 */
	public String getDoseRate(){
		return getPlanMed().getPrescRXDet().getDoseRate();
	}
	
	/**
	 * Prn 
	 * @return isPrn
	 */
	public String getIsPrnString(){
		return  Constantes.yesNoStr(getCtx(), getPlanMed().getPrescRXDet().isPRN());
	}
	/**
	 * Prn 
	 * @return userName
	 */
	public String getUserName(){
		return getUpdatedBy(true); 
	}
	
	/**
	 * Propiedad para reporte eMar 4 Horas
	 * @return Via de administracion||Exme_Route.name
	 */
	public String getRoute(){
		return getPlanMed().getPrescRXDet().getRouteName();
	}
	/**
	 * Frecuencia de administración
	 * @return Frequency1
	 */
	public String getFreq1(){
		return getPlanMed().getPrescRXDet().getFreq1();
	}
	/**
	 * Frecuencia de administración
	 * @return Frequency2
	 */
	public String getFreq2(){
		return getPlanMed().getPrescRXDet().getFreq2();
	}
	/**
	 * Frecuencia de administración Frequency1 + Frequency2
	 * @return Frequency
	 */
	public String getFreq(){
		return getPlanMed().getPrescRXDet().getFrequency();
	}
	/**
	 * Indicaciones de la aplicación
	 * @return EXME_PrescRXDet.getIndicatciones()
	 */
	public String getIndications(){
		return getPlanMed().getPrescRXDet().getIndicaciones();
	}
	
	/**
	 * 
	 * @param lineFrom
	 * @param freq2
	 * @param trxName
	 * @return
	 */
	public MPlanMedLine createInsulinMedOrder(MEXMEEsqInsulinaLine lineFrom, MEXMEFrequency2 freq2, String trxName) {
		if(lineFrom == null || freq2 == null || is_new()) {
			return null;
		}
		// Create prescription
		final int prescRxDetId = getPlanMed().getEXME_PrescRXDet_ID();
		if(prescRxDetId <= 0) {
			return null;
		}
		final MEXMEPrescRXDet detailOrigin = new MEXMEPrescRXDet(getCtx(),prescRxDetId, null);
		// header
		final MEXMEPrescription prescOrigin = detailOrigin.getPrescRx(false).getPrescription(false);
		// create copy
		final MEXMEPrescRXDet detailCopy = new MEXMEPrescRXDet(getCtx(), 0, null);
		PO.copyValues(detailOrigin, detailCopy);
		// set default values
		detailCopy.setDose(X_EXME_PrescRXDet.DOSE_Auto);// 1
		detailCopy.setEXME_ViasAdministracion_ID(0);
		// set values from MEXMEEsqInsulinaLine
		detailCopy.setEXME_GenProduct_ID(lineFrom.getEXME_GenProduct_ID());
		detailCopy.setM_Product_ID(MProduct.getNDC(getCtx(), lineFrom.getEXME_GenProduct_ID()));
		if (StringUtils.isNotBlank(lineFrom.getInstructions())) {
			detailCopy.setIndicaciones(lineFrom.getInstructions());
		}
		// set frequency values
		detailCopy.setEXME_Frequency1_ID(freq2.getEXME_Frequency1_ID());
		detailCopy.setEXME_Frequency2_ID(freq2.getEXME_Frequency2_ID());
		if (!detailCopy.save(trxName)) {
			throw new MedsysException();
		}
		// create Plan
		final MPlanMed planNew = detailCopy.createPlan(prescOrigin);
		if (!planNew.save(trxName)) {
			throw new MedsysException();
		}
		// create administration Line
		final MPlanMedLine planLineNew = planNew.createLine(Env.getCurrentDate());
		planLineNew.setRef_PlanMedLine_ID(getEXME_PlanMedLine_ID());
		if (!planLineNew.save()) {
			throw new MedsysException();
		}
		detailCopy.approveIt();
		return planLineNew;
	}
	
	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == COLUMNNAME_ApliedDate.hashCode()) {
			comparable = getApliedDate();
		} else if (type == COLUMNNAME_Description.hashCode()) {
			comparable = getDescription();
		} else if (type == COLUMNNAME_Estatus.hashCode()) {
			comparable = getEstatus();
		} else if (type == COLUMNNAME_Motivos.hashCode()) {
			comparable = getMotivos();
		} else if(getPlanMed() != null){
			comparable = getPlanMed().onCompare(type);
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}
	
	public Timestamp getHeldDate() {
		return X_EXME_PlanMedLine.ESTATUS_Held.equals(getEstatus()) ? super.getUpdated() : null;
	}

	public String getUserHeld() {
		return X_EXME_PlanMedLine.ESTATUS_Held.equals(getEstatus()) ? getUserName() : null;
	}
	
	public String getReasonHeld() {
		return X_EXME_PlanMedLine.ESTATUS_Held.equals(getEstatus()) ? getMotivos() : null;
	}

	public String getUserAplied() {
		return X_EXME_PlanMedLine.ESTATUS_Administered.equals(getEstatus()) ? getUserName() : null;
	}
	
	public String getReasonLateDose() {
		return X_EXME_PlanMedLine.ESTATUS_Administered.equals(getEstatus()) ? getMotivos() : null;
	}

}
