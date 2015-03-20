/* Created on 04-may-2005 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.joda.time.DateTime;

/**
 * Modelo de Plan de Medicamentos
 * <b>Modificado: </b> $Author: aponce $
 * <p>
 * <b>En :</b> $Date: 2006/09/28 17:51:45 $
 * <p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.10 $
 */
public class MPlanMed extends X_EXME_PlanMed {

	/** serialVersionUID */
	private static final long	serialVersionUID	= 617896009491499241L;

	/** static logger */
	protected static CLogger	s_log				= CLogger.getCLogger(MPlanMed.class);

	/**
	 * @param ctx
	 * @param EXME_SignoVitalDet_ID
	 * @param trxName
	 */
	public MPlanMed(Properties ctx, int EXME_PlanMed_ID, String trxName) {
		super(ctx, EXME_PlanMed_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPlanMed(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Propiedades para que puedan ser visible desde el
	 * el listado de medicamentos
	 */
	private MEXMEProduct		product			= null;
	private MUOM			uom				= null;
	private MEXMEPrescription	prescription	= null;

	/**
	 * Busca Una Plan dados la cuenta paciente y medicamento
	 * 
	 * @param ctaPacID Cuenta Paciente
	 * @param productID Medicamento
	 * @return Plan programado
	 */
//	public static MPlanMed getPlanMed(Properties ctx, int ctaPacID, int productID, String trxName) {
//		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		where.append(" EXME_ctapac_id=? AND m_product_id=? AND docstatus IN (?,?) ");// 'DR','IP'
//		return new Query(ctx, Table_Name, where.toString(), trxName)//
//			.setParameters(ctaPacID, productID, DOCSTATUS_Drafted, DOCSTATUS_InProgress)//
//			// .setOnlyActiveRecords(true)// FIXME porque no esta agregado???
//			.addAccessLevelSQL(true)//
//			.first();
//	}

	/**
	 * Cancela el Plan del Medicamentos
	 * @param deleteNotApplied - Delete not completed lines
	 * @param fromDate - Starting date for lines to delete (Card #746)
	 * @throws SQLException
	 */
	public boolean cancelDetail(boolean deleteNotApplied, final Timestamp fromDate) {
		boolean success = true;
		for (MPlanMedLine obj : getPlanMedLineLst()) {
			if (MPlanMedLine.ESTATUS_Prescribed.equals(obj.getEstatus()) && obj.getApliedDate() == null) {
				// si esta programado para desp
				if (deleteNotApplied) { //RQM #4834
					if(obj.getProgDate().after(fromDate == null ? Env.getCurrentDate() : fromDate)) {
						success = obj.delete(true);
					}
				} else {
					obj.setIsActive(false);
					success = obj.save();
				}
				if (!success) {
					break;
				}
			}
		}
		return success;
	}

	/**
	 * Cancela el Plan del Medicamentos
	 * 
	 * @param deleteNotApplied - Delete not completed lines
	 * @param fromDate - Starting date for lines to delete (Card #746)
	 * @throws SQLException
	 */
	public boolean cancel(boolean deleteNotApplied, final Timestamp fromDate) {
		boolean success = true;
		success = cancelDetail(deleteNotApplied, fromDate);
		if (success) {
			setDocStatus(DOCSTATUS_Voided);
			setIsActive(false);
			success = save();
		}
		return success;
	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 */
	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_PlanMed_ID FROM EXME_PlanMed ");
		sql.append(" WHERE EXME_PlanMed.isActive = 'Y' AND EXME_PlanMed.EXME_CtaPac_ID=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(trxName, sql.toString(), ctaPacId) > 0;
	}

	public MEXMEProduct getProduct() {
		if (product == null && getM_Product_ID() > 0) {
			product = new MEXMEProduct(getCtx(), getM_Product_ID(), null);
		}
		return product;
	}

	public MUOM getUom() {
		if (uom == null && getC_UOM_ID() > 0)
			uom = new MUOM(getCtx(), getC_UOM_ID(), null);
		return uom;
	}

	private List<MPlanMedLine>	planMedLineLst	= null;

	public List<MPlanMedLine> getPlanMedLineLst() {
		return getPlanMedLineLst(false);
	}

	public List<MPlanMedLine> getPlanMedLineLst(boolean requery) {
		if (planMedLineLst == null || requery) {
			// ***NOTA: NO QUITAR TRXNAME** //
			planMedLineLst = new Query(getCtx(), MPlanMedLine.Table_Name, " EXME_PlanMed_ID=? ", get_TrxName())//
				.setParameters(getEXME_PlanMed_ID())//
				.addAccessLevelSQL(true)// .setOnlyActiveRecords(true)// ?? (no)
				.list();
		}
		return planMedLineLst;
	}


	/**
	 * Mantiene las lineas que aun no han sido guardadas en
	 * la base de datos y solo se muestran al usuario
	 * NOTA: de trabajo para emar
	 */
	private List<MPlanMedLine>	lines	= new ArrayList<MPlanMedLine>();
	public List<MPlanMedLine> getLines() {
		return lines;
	}
// /** @deprecated Not used */
//	public static List<Integer> getFromCtaPac(Properties ctx, int EXME_CtaPac_ID, String trxName) {
//		final List<Integer> lista = new ArrayList<Integer>();
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("SELECT DISTINCT EXME_PlanMed.M_Product_ID FROM EXME_PlanMed WHERE EXME_PlanMed.isActive = 'Y'  ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name))
//			.append(" AND EXME_planmed.EXME_CtaPac_id = ? ");
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(rs.getInt(1));
//			}
//		}
//		catch (Exception e) {
//			s_log.log(Level.SEVERE, "getFromCtaPac", e);
//		}
//		finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return lista;
//	}

	/**
	 * Obtiene los productos desde un Paciente ID
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getFromPac(Properties ctx, int EXME_Paciente_ID, String trxName) {
		final List<Integer> lista = new ArrayList<Integer>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT DISTINCT EXME_PlanMed.M_Product_ID FROM EXME_PlanMed ")
			.append("INNER JOIN EXME_CtaPac cp ON cp.EXME_CtaPac_ID = EXME_PlanMed.EXME_CtaPac_ID ")
			.append("WHERE EXME_PlanMed.isActive = 'Y' AND EXME_PlanMed.M_Product_ID IS NOT NULL AND cp.EXME_Paciente_ID = ? ")
			.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(rs.getInt("M_Product_ID"));
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getFromPac", e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}

	public MEXMEPrescription getPrescription() {
		if (prescription == null || prescription.getEXME_Prescription_ID() == 0) {
			prescription = new MEXMEPrescription(getCtx(), getEXME_Prescription_ID(), get_TrxName());
		}
		return prescription;
	}

//	public String getIndicacionPresc(boolean conNombre, String descontinuado) {
//		final StringBuilder indicacion = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		if (conNombre) {
//			if (!isActive()){
//				indicacion.append(descontinuado).append(" ");
//			}
//			indicacion.append(getProduct().getValue()).append(" ");
//			indicacion.append(getProduct().getName()).append(" ");
//			indicacion.append(getProduct().getStrength()).append(" ");
//		}
//		if (getProduct() != null && getProduct().getEXME_GenProduct_ID() > 0) {
//			MEXMEGenProduct mgp = new MEXMEGenProduct(Env.getCtx(), getProduct().getEXME_GenProduct_ID(), null);
//			indicacion.append("by ");
//			if (mgp.getVia() != null){
//				indicacion.append(mgp.getVia().getDescription1()).append(" ");
//			}
//			indicacion.append(getQtyPlanned());
//			if (mgp.getDosis() != null){
//				indicacion.append(mgp.getDosis().getDescription()).append(" ");
//			}
//		}
//		indicacion.append(" every ");// FIXME (LABEL)
//		indicacion.append(getIntervalo()).append(" ");
//		indicacion.append(MRefList.getListasTraducidasValue(getCtx(), UOMINTERVALO_AD_Reference_ID, false, getUOMIntervalo()));
//		indicacion.append(" for ");
//		indicacion.append(getDuracion()).append(" ");
//		indicacion.append(MRefList.getListasTraducidasValue(getCtx(), UOMDURACION_AD_Reference_ID, false, getUOMDuracion()));
//		return indicacion.toString();
//	}

	/** @deprecated revisar funcionalidad de tratamientos */
	public static List<MPlanMed> getTratamientosDetalle(Properties ctx, int id, String trxName) {
		final StringBuilder sql = new StringBuilder(" SELECT * FROM EXME_PlanMed ");
		sql.append(" WHERE EXME_TRATAMIENTOS_SESION_ID = ? ");
		return new Query(ctx, Table_Name, " EXME_TRATAMIENTOS_SESION_ID=? ", trxName)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)// FIXME: Porque no se agrego originalmente
			.setParameters(id)//
			.list();
	}

	

	/**
	 * Get the medication plan related to the prescription (cpoe)
	 * @param ctx
	 * @param exmePresxRxId
	 * @return
	 */
	public static int getFromRxId(final Properties ctx, final int exmePresxRxId) {
		int retValue = 0;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_PLANMED.EXME_PRESCRXDET_ID=? ", null)//
				.setParameters(exmePresxRxId)//
				.setOnlyActiveRecords(true)//
				.firstId();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		}
		return retValue;
	}

	/**
	 * Get the medication plan related to the prescription (cpoe)
	 * @param ctx
	 * @param exmePresxRxId
	 * @param trxName
	 * @return
	 */
	public static MPlanMed getFromRxId(final Properties ctx, final int exmePresRxDetId, final String trxName) {
		MPlanMed retValue = null;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_PLANMED.EXME_PRESCRXDET_ID=? ", trxName)//
				.setParameters(exmePresRxDetId)//
				.addAccessLevelSQL(true)//
				.first();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
		}
		return retValue;
	}

	/**
	 * Guardar la programacion de las dosis a aplicar
	 * 
	 * @param dates
	 * @return
	 */
	public boolean saveLines(final List<Date> dates) {
		boolean success = Boolean.TRUE;
		for (Date date : dates) {
			final MPlanMedLine line = createLine(date);
//					new MPlanMedLine(getCtx(), 0, get_TrxName());
//			line.setEXME_PlanMed_ID(getEXME_PlanMed_ID());
//			line.setDescription(getDescription());
//			line.setQtyPlanned(getQtyPlanned());
//			line.setQtyAplied(BigDecimal.ZERO);
//			line.setProgDate(new Timestamp(date.getTime()));
//			line.setEstatus(MPlanMedLine.ESTATUS_Prescribed);
			success = line.save();
			if (!success) {
				getLines().clear();
				break;
			}
			getLines().add(line);
		}
		return success;
	}
	
	public MPlanMedLine createLine(Date date) {
		final MPlanMedLine line = new MPlanMedLine(getCtx(), 0, get_TrxName());
		line.setEXME_PlanMed_ID(getEXME_PlanMed_ID());
		line.setDescription(getDescription());
		line.setQtyPlanned(getQtyPlanned());
		line.setQtyAplied(BigDecimal.ZERO);
		line.setProgDate(new Timestamp(date.getTime()));
		line.setEstatus(MPlanMedLine.ESTATUS_Prescribed);
//		if (!line.save()) {
//			throw new MedsysException();// no msg
//		}
		return line;
	}

	/**
	 * Actualiza las aplicaciones con el NDC correcto
	 * 
	 * @param mejoras
	 * @param productID
	 * @return
	 */
	public boolean saveActPaciente(final MEXMEMejoras mejoras, final int productID) {
		boolean success = true;
		if (productID != mejoras.getNotAvailableNDC() && productID != mejoras.getTemporaryNDC()) {
			// update actpaciente
			final List<MPlanMedLine> planMedLineLst = getPlanMedLineLst();
			if (!planMedLineLst.isEmpty()) {
				final String updateSql =
					"UPDATE EXME_ActPacienteInd SET M_Product_ID=? WHERE EXME_ActPacienteIndH_ID=? AND isActive='Y' ";
				final Object[] params = new Object[2];
				params[0] = productID;
				for (MPlanMedLine line : planMedLineLst) {
					if (line.getEXME_ActPacienteIndH_ID() > 0) {
						params[1] = line.getEXME_ActPacienteIndH_ID();
						s_log.log(Level.INFO, "executeUpdate >> " + updateSql + "; ?1=" + params[0] + " ?2="
							+ params[1]);
						success = DB.executeUpdate(updateSql, params, false, get_TrxName()) <= 0;
						if (!success) {
							break;
						}
					}
				}
			}
		}
		return success;
	}

	/**
	 * Get the last dose applied
	 * 
	 * @param onlyApplied
	 * @return
	 */
	public MPlanMedLine getLastDose(boolean onlyApplied) {
		return MPlanMedLine.getLastDose(getCtx(), get_ID(), onlyApplied, get_TrxName());
	}

	/**
	 * Get the last applied date
	 * 
	 * @return
	 */
	public Timestamp getLastApliedDate() {
		return MPlanMedLine.getLastApliedDate(getCtx(), get_ID(), get_TrxName());
	}

	/**
	 * Get the number of administrated doses 
	 * @param days - range of days
	 * @return
	 */
	public int getAdmistratedDoses(int days) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// Estatus
		sql.append(" AND EXME_PLANMEDLINE.ESTATUS=");
		sql.append(DB.TO_STRING(MPlanMedLine.ESTATUS_Administered));
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(EXME_PlanMedLine.ApliedDate, 'MI') >= TRUNC("+ DB.TO_DATE(Env.getCurrentDate()) + ",'MI')-").append(days);
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('minute', EXME_PlanMedLine.ApliedDate) >= DATE_TRUNC('minute', "+ DB.TO_DATE(Env.getCurrentDate()) + ")-").append(days);
		}
		//sql.append(" AND TRUNC(EXME_PlanMedLine.ApliedDate, 'MI') <= TRUNC(SYSDATE,'MI') ");
		return MPlanMedLine.getCountDoses(getCtx(), get_ID(), sql.toString(), get_TrxName());
	}

	/**
	 * Busco que todas las lineas de esta planificacion esten
	 * aplicadas
	 * si es asi esta programacion ya esta completa si no siguen en
	 * proceso
	 * @param pPlanMedLineID
	 */
	public void completeLines(final int pPlanMedLineID){
		for (MPlanMedLine line : getPlanMedLineLst()) {
			if (line.getQtyAplied().compareTo(Env.ZERO) == 0 && line.getEXME_PlanMedLine_ID() != pPlanMedLineID) {
				// si la linea siguen en proceso mi header tambien
				prescription.setDocStatus(MPlanMed.DOCSTATUS_InProgress);
				setDocStatus(MPlanMed.DOCSTATUS_InProgress);
				break;
			}
		}
	}
	
	/** Valida si ya se completo el Plan */
	public static boolean isCompleted(final int exme_prescrxdet_id, final String trxName) {
		final String sql = "SELECT DocStatus FROM EXME_PlanMed WHERE isActive='Y' AND EXME_PrescRxDet_ID=?";
		final String status = DB.getSQLValueString(trxName, sql, exme_prescrxdet_id);
		return DOCSTATUS_Completed.equals(status);
	}
	
	/** Regresa TRUE si el plan es para una insulina (es decir, tiene un tipo de insulina o esquema de insulina relacionado)*/
	public boolean isInsulin() {
		return getEXME_EsqInsulina_ID() > 0 || StringUtils.isNotBlank(getTipo());
	}
	
	/** Regresa el nombre del tipo o esuqema de insulina*/
	public String getInsulinStr() {
		final StringBuilder str = new StringBuilder();
		// insulinas
		if (getEXME_EsqInsulina_ID() > 0) {
			str.append(((MEXMEEsqInsulina) getEXME_EsqInsulina()).getSummary());
		} else if (StringUtils.isNotEmpty(getTipo())) {
			str.append(MRefList.getListName(getCtx(), MEXMEEsqInsulina.TIPO_AD_Reference_ID, StringUtils.trimToEmpty(super.getTipo())));// FIXME
		}
		return str.toString();
	}
	
	/**
	 * Obj encuentro
	 */
	private MEXMECtaPac mCtaPac = null;
	public MEXMECtaPac getCtaPac() {
		if (mCtaPac == null && getEXME_CtaPac_ID() > 0) {
			mCtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), null);
		}
		return mCtaPac;
	}
	
	/**
	 * Prescripción con medicamento
	 */
	private MEXMEPrescRXDet prescRXDet = null;
	public MEXMEPrescRXDet getPrescRXDet() {
		if (prescRXDet == null || prescRXDet.getEXME_PrescRXDet_ID() == 0) {
			prescRXDet = new MEXMEPrescRXDet(getCtx(), getEXME_PrescRXDet_ID(),
					get_TrxName());
		}
		return prescRXDet;
	}
	
	@Override
	public Object onCompare(int type) {
		Object comparable;
		if(getPrescRXDet() != null){
			comparable = getPrescRXDet().onCompare(type);
		} else {
			comparable = super.onCompare(type);
		}
		return comparable;
	}
	
	/** Buscar si hay registros donde el minipaquete asociado sea el del parametro
	 * @param ctx: contexto
	 * @param versionPackId: Version del minipaquete/paquete
	 * @param trxName: Nombre de transaccion
	 * @return true: si existen registros con la version
	 */
	public static boolean haveMinipacks(final Properties ctx, final int versionPackId, final String trxName){
		String sql = " EXME_PaqBase_Version_ID = ? ";
		final List<MPlanMed> lista = new Query(ctx, MPlanMed.Table_Name, sql, trxName)//
		.setOrderBy("Created") //
		.setParameters(versionPackId) //
		.setOnlyActiveRecords(true) //
		.addAccessLevelSQL(true).list();
		return lista!=null && !lista.isEmpty();
	}
	
	/** la prescripcion esta proxima a vencer (24 hrs) */
	public boolean isAboutToExpire() {
		if (getEndDate() != null) {
			Date now = new Date();
			Date n24H = new DateTime(now).plusHours(24).toDate();
			return getEndDate().after(now) && getEndDate().before(n24H);
		}
		return false;
	}

	/** @return true para Dosis automaticas que ya vencieron */
	public boolean isExpired() {
		if (getEndDate() != null) {
			Date now = new Date();
			return X_EXME_PrescRXDet.DOSE_Auto.equals(getPrescRXDet().getDose()) && getEndDate().before(now);
		}
		return false;
	}
}