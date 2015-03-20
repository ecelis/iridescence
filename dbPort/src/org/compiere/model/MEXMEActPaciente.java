package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.vo.ActPacienteVO;

/**
 * Modelo de Actividad Paciente Paciente
 */
public class MEXMEActPaciente extends X_EXME_ActPaciente {

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEActPaciente.class);
	/** Patient */
	private MEXMEPaciente paciente = null;
	/** Medico */
	private MEXMEMedico medico = null;
	/** cuenta paciente relacionada a la actividad */
	private MEXMECtaPac ctaPac = null;
	/** Especialidad */
	private MEXMEEspecialidad especialidad = null;
	/** Cita medica */
	private MEXMECitaMedica citaMedica = null;
	// expediente .- Lama
//	private List<MActPacienteDiag> lstDiag = null;// diagnosis
//	private List<MEXMEActPacienteIndH> lstMed = null; // prescriptions
//	private List<MEXMEActPacienteIndH> lstServ = null;// studies
//	private List<MActPacienteDet> medicalNotes = null;// notas medicas
//	private List<MEXMEActPacienteIndH> lstProc = null;// procedures


	/**
	 * @param ctx
	 * @param EXME_ActPaciente_ID
	 * @param trxName
	 */
	public MEXMEActPaciente(Properties ctx, int EXME_ActPaciente_ID, String trxName) {
		super(ctx, EXME_ActPaciente_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEActPaciente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	/**
//	 * 
//	 * @param actPac
//	 * @return
//	 */
//	public MEXMEActPacienteIndH createActPacienteIndH() {
//		MEXMEActPacienteIndH hdr = new MEXMEActPacienteIndH(getCtx(), 0, get_TrxName());
//		// HEADER
//		hdr.setEXME_ActPaciente_ID(getEXME_ActPaciente_ID());
//		// DOCUMENT
//		MEXMEConfigDocType docType = MEXMEConfigDocType.get(getCtx(), null);
//		if (docType != null) {
//			hdr.setC_DocType_ID(docType.getC_DocTypeVacuna_ID());
//			hdr.setC_DocTypeTarget_ID(docType.getC_DocTypeVacuna_ID());
//		} else {
//			throw new MedsysException(Utilerias.getMessage(getCtx(), null, "error.tipoDoc"));
//		}
//		hdr.setDocStatus(DocAction.STATUS_Completed);
//		hdr.setDocAction(DocAction.ACTION_None);
//		hdr.setDateAcct(DB.getTimestampForOrg(Env.getCtx()));
//		hdr.setDateOrdered(DB.getTimestampForOrg(Env.getCtx()));
//		hdr.setDateDelivered(DB.getTimestampForOrg(Env.getCtx()));
//		hdr.setIsDelivered(true);
//		hdr.setProcessed(true);
//		// LOGUEO
//		int mWarehouseID = Env.getM_Warehouse_ID(getCtx());
//		hdr.setM_Warehouse_ID(mWarehouseID);
//		hdr.setM_Warehouse_Sol_ID(mWarehouseID);
//
//		try {
//			hdr.setAD_OrgTrx_ID(MEXMEEstServ.getEstServAlmOrgTrx(getCtx(), mWarehouseID));
//		} catch (Exception e) {
//			throw new MedsysException(e.getMessage());
//		}
//		// PACIENTE
//		hdr.setC_BPartner_ID(getPaciente().getC_BPartner_ID());
//		hdr.setC_Location_ID(getPaciente().getC_Location_ID());
//
//		// PRECIO
//		MPriceList price = MEXMEConfigPre.getPriceList(getCtx(), null);
//		hdr.setM_PriceList_ID(price.getM_PriceList_ID());
//		hdr.setC_Currency_ID(price.getC_Currency_ID());
//		hdr.setIsTaxIncluded(price.isTaxIncluded());
//
//		return hdr;
//	}

	/**
	 * Obtener actividad por cita
	 * 
	 * @param ctx
	 *            Contexto
	 * @param citaMedicaId
	 *            Cita id
	 * @param trxName
	 *            Trx Name
	 * @return Id de Actividad
	 */
	public static int getByCitaMedica(Properties ctx, int citaMedicaId, String trxName) {
		int id = 0;
		try {
			id = new Query(ctx, Table_Name, " exme_citamedica_id=? ", trxName)//
				.setParameters(citaMedicaId)//
				.setOrderBy(" created ")//
				.firstId();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getByCitaMedica", e);
		}
		return id;
	}
	
	public static int getByProgQuiro(Properties ctx, int progQuiroId, String trxName) {
		int id = 0;
		try {
			id = new Query(ctx, Table_Name, " exme_progquiro_id=? ", trxName)//
				.setParameters(progQuiroId)//
				.setOrderBy(" created ")//
				.firstId();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getByProgQuiro", e);
		}
		return id;
	}
	
	/**
	 * Obtenemos la instancia del Paciente relacionado.
	 * 
	 * @return MEXMEPaciente
	 */
	public MEXMEPaciente getPaciente() {
		if (paciente == null) {
			paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		return paciente;
	}

	/**
	 * Obtenemos la instancia del Medico relacionado.
	 * 
	 * @return MMedico
	 */
	public MEXMEMedico getMedico() {
		if (medico == null) {
			medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
		}
		return medico;
	}
	
	/**
	 * Obtenemos la cuenta paciente
	 * 
	 * @return
	 */
	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}
	
	public void setCtaPac(MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}
	
	public MEXMEEspecialidad getEspecialidad() {
		if (especialidad == null) {
			especialidad = new MEXMEEspecialidad(getCtx(), getEXME_Especialidad_ID(), get_TrxName());
		}
		return especialidad;
	}
	
	/**
	 * Obtenemos la instancia del Medico relacionado.
	 * 
	 * @return MMedico
	 */
	public MEXMECitaMedica getCitaMedica() {
		if (citaMedica == null) {
			citaMedica = new MEXMECitaMedica(getCtx(),
					getEXME_CitaMedica_ID(), get_TrxName());
		}
		return citaMedica;
	}

	public void setCitaMedica(MEXMECitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}
	
	/**
	 * Diagnosticos
	 * 
	 * @return
	 */
//	public List<MActPacienteDiag> getLstDiag() {
//		if (lstDiag == null || lstDiag.isEmpty()) {
//			if (getEXME_Paciente_ID() > 0) {
//				MHealthRecord hr = new MHealthRecord(getCtx(),
//						getEXME_Paciente_ID(), get_TrxName());
//				lstDiag = hr.getDiagnosis(getCtx(), getEXME_Paciente_ID(),
//						getEXME_ActPaciente_ID(), get_TrxName());
//			}
//		}
//		return lstDiag;
//	}

//	public void setLstDiag(List<MActPacienteDiag> lstDiag) {
//		this.lstDiag = lstDiag;
//	}

	/**
	 * Medications
	 * 
	 * @return
	 */
//	public List<MEXMEActPacienteIndH> getLstMed() {
//		if (lstMed == null || lstMed.isEmpty()) {
//			if (getEXME_ActPaciente_ID() > 0) {
//				MHealthRecord hr = new MHealthRecord(getCtx(), getEXME_Paciente_ID(), get_TrxName());
//				lstMed = hr.getPrescriptions(getCtx(), 0, getEXME_ActPaciente_ID(), get_TrxName());
//			}
//		}
//		return lstMed;
//	}


	/**
	 * Studies
	 * 
	 * @return
	 */
//	public List<MEXMEActPacienteIndH> getLstServ() {
//		if (lstServ == null || lstServ.isEmpty()) {
//			if (getEXME_ActPaciente_ID() > 0) {
//				MHealthRecord hr = new MHealthRecord(getCtx(), getEXME_Paciente_ID(), get_TrxName());
//				lstServ = hr.getExams(getCtx(), 0, getEXME_ActPaciente_ID(), get_TrxName());
//			}
//		}
//		return lstServ;
//	}

	
	/**
	 * Procedures
	 * @return
	 */
//	public List<MEXMEActPacienteIndH> getLstProc() {
//		if (lstProc == null || lstProc.isEmpty()) {
//			if (getEXME_ActPaciente_ID() > 0) {
//				MHealthRecord hr = new MHealthRecord(getCtx(), getEXME_Paciente_ID(), get_TrxName());
//				lstProc = hr.getProcedures(getCtx(), 0, getEXME_ActPaciente_ID(), get_TrxName());
//			}
//		}
//		return lstProc;
//	}

	/**
	 * 
	 * @param ctx
	 * @param actPac
	 * @param trxName
	 * @param onlyAnswer
	 * @return
	 * @throws Exception
	 * @deprecated Revisar si esto aun aplica para la nueva funcionalidad de cuestionarios
	 */
	public static List<DetalleView> getHistorialDetalleHC(Properties ctx,
			int actPac, String trxName, boolean onlyAnswer) throws Exception {

		List<DetalleView> lista = new ArrayList<DetalleView>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT apd.EXME_ActPaciente_ID,  c.Name AS cName,  ");
		sql.append(" apd.Secuencia, tp.Name AS tpName, ");
		sql.append(" p.name AS pName, p.TipoDato,  ");
		sql.append(" apd.Respuesta, apd.Description,  ");
		sql.append(" apd.RutaImagen, apd.Confidencial, ");
		sql.append(" c.Name as cuestName,  ");
		sql.append(" nvl(c.EXME_Cuestionario_ID,0) as EXME_Cuestionario_ID,  ");
		sql.append(" nvl(apd.folio,0) as folio ");
		sql.append(" FROM EXME_ActPaciente ");
		sql.append("     INNER JOIN EXME_ActPacientedet apd  ");
		sql.append(" ON (EXME_ActPaciente.EXME_ActPaciente_ID   = apd.EXME_ActPaciente_ID) ");
		sql.append("     INNER JOIN EXME_Pregunta p         ");
		sql.append(" ON (apd.EXME_Pregunta_ID      = p.EXME_Pregunta_ID) ");
		sql.append("     INNER JOIN EXME_TipoPregunta tp     ");
		sql.append(" ON (p.EXME_TipoPregunta_ID    = tp.EXME_TipoPregunta_ID) ");
		sql.append("     INNER JOIN exme_configec ce  ");
		sql.append(" ON ( apd.exme_cuestionario_id = ce.exme_cuestionario2_id) ");
		sql.append("     LEFT  JOIN EXME_Cuestionario c      ");
		sql.append(" ON (apd.EXME_Cuestionario_ID  = c.EXME_Cuestionario_ID) ");
		sql.append(" WHERE EXME_ActPaciente.IsActive = 'Y'  ");
		sql.append(" AND EXME_ActPaciente.EXME_ActPaciente_ID = ? ");

		if (onlyAnswer) {// Liz de la Garza - Se filtra si el cuestionario esta
			// configurado a que muestre solo las preguntas
			// respondidas
			sql.append(" AND apd.Respuesta IS NOT NULL");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPaciente"));

		sql.append(" ORDER BY apd.created desc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, actPac);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DetalleView exp = new DetalleView();
				exp.setRespuesta(rs.getString("Respuesta"));
				exp.setRutaImagen(rs.getString("RutaImagen"));
				exp.setConfidencial(rs.getString("Confidencial"));
				exp.setNombre(rs.getString("cName"));
				exp.setPregName(rs.getString("pName"));
				exp.setTipoPregName(rs.getString("tpName"));
				exp.setDescripcion(rs.getString("Description"));
				exp.setTipoDato(rs.getString("TipoDato"));
				exp.setCuestionario(rs.getInt("EXME_Cuestionario_ID"));
				exp.setNombreCuest(rs.getString("cuestName"));
				exp.setFolio(rs.getInt("folio"));
				lista.add(exp);
			}

			// agregamos a la lista los titulos de las preguntas
			String anterior = "";
			for (int i = 0; i < lista.size(); i++) {
				DetalleView detCuest = (DetalleView) lista.get(i);
				if (!detCuest.getTipoPregName().equalsIgnoreCase(anterior)) {
					DetalleView cuest = new DetalleView();
					cuest.setTipoPregName(detCuest.getTipoPregName());
					cuest.setPregName(detCuest.getPregName());
					cuest.setTipoDato("titulo");
					cuest.setCuestionario(detCuest.getCuestionario());
					cuest.setNombreCuest(detCuest.getNombreCuest());
					cuest.setFolio(detCuest.getFolio());
					lista.add(i, cuest);
					anterior = cuest.getTipoPregName();
				}
			}

			// agregamos a la lista el titulo del cuestionario.. Twry
			long cuestIni = 0;
			long cuestAnte = 0;
			for (int i = 0; i < lista.size(); i++) {
				DetalleView cuestDet = (DetalleView) lista.get(i);
				cuestIni = cuestDet.getCuestionario();
				if (cuestIni != cuestAnte) {
					DetalleView cuest = new DetalleView();
					cuest.setNombreCuest(cuestDet.getNombreCuest());
					cuest.setCuestionario(cuestDet.getCuestionario());
					cuest.setTipoDato("cuestionario");
					cuest.setFolio(cuestDet.getFolio());
					lista.add(i, cuest);
					cuestAnte = cuestIni;
				}
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getHistorialDetalle", e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 * Obtenemos una consulta sobre todos los registros activos de la tabla
	 * EXME_ActPaciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param sql
	 *            Consulta sql
	 * @param where
	 *            Si solo se agrega condicion a la consulta basica
	 * @param params
	 *            Parametros para la consulta
	 * @param trxName
	 *            Nombre de transaccion
	 * @return Regresa un objeto de tipo MEXMEActPaciente a partir de la consulta o
	 *         null
	 * @deprecated use {@link Query} instead*/
	public static MEXMEActPaciente getActPaciente(Properties ctx,
			StringBuilder sql, String where, List<?> params, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEActPaciente actPac = null;

		// consulta clasica
		if (sql == null || sql.length() <= 0) {
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT EXME_ActPaciente.* FROM EXME_ActPaciente ");
			sql.append(" WHERE EXME_ActPaciente.IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(where != null ? where : "");
		}

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params != null && params.size() > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				actPac = new MEXMEActPaciente(ctx, rs, trxName);
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getActPaciente (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return actPac;
	}

	/**
	 * Obtenemos el cuestionario que se hicieron en una actividad paciente.
	 * 
	 * @param whereClause
	 * @return
	 */
	public static MEXMEActPaciente getActPaciente(Properties ctx, int ctaPacId, String whereClause, String trxName) {
		MEXMEActPaciente retValue = null;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_CtaPac_ID=? "+(whereClause == null ? "" : whereClause), trxName)//
				.setParameters(ctaPacId)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.first();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getActPaciente", e);
		}
		return retValue;
	}

	/**
	 * Obtenemos el cuestionario actividad paciente.
	 * 
	 * @param pacienteId
	 * @param fromCuest
	 * @return
	 * @deprecated
	 **/
	public static MEXMEActPaciente getActPaciente(Properties ctx, int pacienteId, String trxName, boolean fromCuest) {
		MEXMEActPaciente retValue = null;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_Paciente_ID=? "
				+(fromCuest ? "AND EXME_ActPaciente.Name='Trabajo Social' " : "")
				, trxName)//
				.setParameters(pacienteId)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.setOrderBy("created desc")
				.first();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getActPaciente", e);
		}
		return retValue;
	}
	
	/**
	 * Obtenemos el cuestionario que se hicieron en una actividad paciente.
	 * 
	 * @param whereClause
	 * @return
	 */
	public static MEXMEActPaciente getActPacienteCitaMed(Properties ctx, int citaMedica, String whereClause, String trxName) {
		MEXMEActPaciente retValue = null;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_CitaMedica_ID=? "+(whereClause == null ? "" : whereClause), trxName)//
				.setParameters(citaMedica)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.first();
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getActPacienteCitaMed", e);
		}
		return retValue;
	}

	/**
	 * Devolvemos la actividad paciente relacionada con una cuenta paciente
	 * determinada
	 * 
	 * @param ctaPac
	 * @return
	 * @throws SQLException
	 */
	public static MEXMEActPaciente getForCtaPac(MEXMECtaPac ctaPac) {
		return MEXMEActPaciente.getActPaciente(ctaPac.getCtx(), ctaPac.getEXME_CtaPac_ID(), null, ctaPac.get_TrxName());
	}
	
	/**
	 * Obtiene los folios relacionados a un paciente/cuenta y cuestionario
	 * 
	 * @param ctx
	 * @param EXME_Cuestionario_ID
	 * @param principal
	 *            DocumentNo cuenta o Value paciente
	 * @param columnName
	 *            Nombre de la columna
	 * @param trxName
	 *            Transaccion
	 * @TODO Revisar si esto aun aplica para la nueva funcionalidad de cuestionarios
	 * @return
	 */
	public static List<LabelValueBean> getFolios(Properties ctx,
			int EXME_Cuestionario_ID, String principal, String columnName,
			String trxName) {
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" select distinct(FOLIO), act.CREATED ");
		sql.append(" from EXME_ACTPACIENTEDET actd ");
		sql.append(" inner join EXME_ACTPACIENTE act ");
		sql.append(" on act.EXME_ACTPACIENTE_ID = actd.EXME_ACTPACIENTE_ID ");

		if (columnName.toLowerCase().equals("cita")) {
			sql.append("where act.EXME_CitaMedica_ID = ? ");
		} else if (columnName.toLowerCase().equals("cuenta")) {
			sql.append("INNER JOIN EXME_CtaPac ctaPac ");
			sql.append(" ON ctaPac.EXME_CtaPac_ID = act.EXME_CtaPac_ID ");
			sql.append("where cuenta.documentNo = ? ");
		} else if (columnName.toLowerCase().equals("paciente")) {
			sql.append("INNER JOIN EXME_Paciente paciente ");
			sql.append(" ON paciente.EXME_Paciente_ID = act.EXME_Paciente_ID ");
			sql.append("where paciente.value = ? ");
		} else {
			return null;
		}

		sql.append("and actd.EXME_CUESTIONARIO_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPacienteDet", "actd"));

		sql.append(" order by act.CREATED");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, principal);
			pstmt.setInt(2, EXME_Cuestionario_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String folio = rs.getString(1);
				Timestamp fecha = rs.getTimestamp(2);
				LabelValueBean lb = new LabelValueBean(Constantes
						.getSdfFechaHora().format(fecha)
						+ " - " + folio, folio);
				lista.add(lb);
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getResumenMedico (" + sql + ")", e);

		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	
	/**
	 * Devuelve una lista con los folios relacionados a una actividad
	 * 
	 * @author raul
	 * @TODO Revisar si esto aun aplica para la nueva funcionalidad de cuestionarios
	 */
	public ArrayList<Integer> getFoliosFromAct() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> folios = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT FOLIO FROM EXME_ACTPACIENTEDET  ").append(
				" WHERE EXME_ACTPACIENTE_ID = ? ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getEXME_ActPaciente_ID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				folios.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getFoliosFromAct", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return folios;
	}

	/**
	 * Obtiene listado de Actividades relacionadas a un producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param M_Product_ID
	 *            Producto
	 * @param EXME_Paciente_ID
	 *            Paciente
	 * @param EXME_Medico_ID
	 *            Medico
	 * @return Listado de actividades
	 */
	public static List<ActPacienteVO> getFromProduct(Properties ctx,
			int M_Product_ID, int EXME_Paciente_ID, int EXME_Medico_ID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActPacienteVO> lst = new ArrayList<ActPacienteVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("select act.exme_actpaciente_id, ");
		sql
				.append("  NVL(cta.documentno,'') as cta, pac.nombre_pac, act.created,  ");
		sql.append(" (prod.value || ' - ' || prod.name) as prod  ");
		sql.append(" from exme_actpaciente act ");
		sql.append("inner join exme_actpacienteindh indh  ");
		sql.append(" on indh.exme_actpaciente_id = act.exme_actpaciente_id ");
		sql.append("inner join exme_actpacienteind ind ");
		sql
				.append("  on ind.exme_actpacienteindh_id = indh.exme_actpacienteindh_id ");
		sql.append("inner join m_product prod  ");
		sql.append(" on prod.m_product_id = ind.m_product_id ");
		sql.append("inner join exme_paciente pac  ");
		sql.append(" on pac.exme_paciente_id = act.exme_paciente_id ");
		sql.append("left join exme_ctapac cta  ");
		sql.append(" on cta.exme_ctapac_id = act.exme_ctapac_id ");

		sql.append("where prod.m_product_id = ? ");
		if (EXME_Paciente_ID > 0) {
			sql.append(" AND pac.exme_paciente_id = ? ");
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPaciente", "act"));
		sql.append(" order by act.created desc ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, M_Product_ID);

			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(2, EXME_Paciente_ID);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActPacienteVO vo = new ActPacienteVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("exme_actpaciente_id")));
				vo.setNombrePac(rs.getString("nombre_pac"));
				vo.setCtaPac(rs.getString("cta"));
				vo.setValor(rs.getString("prod"));
				lst.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lst;
	}

	/**
	 * Obtiene listado de Actividades relacionadas a un diagnostico
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_Diagnostico_ID
	 *            Diagnostico
	 * @param EXME_Paciente_ID
	 *            Paciente
	 * @param EXME_Medico_ID
	 *            Medico
	 * @return Listado de actividades
	 */
	public static List<ActPacienteVO> getFromDiagnostico(Properties ctx,
			int EXME_Diagnostico_ID, int EXME_Paciente_ID, int EXME_Medico_ID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActPacienteVO> lst = new ArrayList<ActPacienteVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select act.exme_actpaciente_id, ");
		sql.append(" NVL(cta.documentno,'') as cta, pac.nombre_pac, act.created, ");
		sql.append(" diag.value || ' - ' || diag.name as diag  ");
		sql.append(" from exme_actpaciente act ");
		sql.append(" inner join exme_actpacientediag actd  ");
		sql.append(" on actd.exme_actpaciente_id = act.exme_actpaciente_id ");
		sql.append(" inner join exme_diagnostico diag  ");
		sql.append(" on diag.exme_diagnostico_id = actd.exme_diagnostico_id ");
		sql.append(" inner join exme_paciente pac  ");
		sql.append(" on pac.exme_paciente_id = act.exme_paciente_id ");
		sql.append(" left join exme_ctapac cta  ");
		sql.append(" on cta.exme_ctapac_id = act.exme_ctapac_id ");

		sql.append("where diag.exme_diagnostico_id = ? ");
		if (EXME_Paciente_ID > 0) {
			sql.append(" AND pac.exme_paciente_id = ? ");
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPaciente", "act"));
		sql.append(" order by act.created desc ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Diagnostico_ID);
			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(2, EXME_Paciente_ID);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActPacienteVO vo = new ActPacienteVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("exme_actpaciente_id")));
				vo.setNombrePac(rs.getString("nombre_pac"));
				vo.setValor(rs.getString("diag"));
				vo.setCtaPac(rs.getString("cta"));
				lst.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lst;
	}

	/**
	 * Obtiene listado de Actividades relacionadas a una intervencion
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_Intervencion_ID
	 *            Intervencion
	 * @param EXME_Paciente_ID
	 *            Paciente
	 * @param EXME_Medico_ID
	 *            Medico
	 * @return Listado de actividades
	 */
	public static List<ActPacienteVO> getFromIntervencion(Properties ctx,
			int EXME_Intervencion_ID, int EXME_Paciente_ID, int EXME_Medico_ID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActPacienteVO> lst = new ArrayList<ActPacienteVO>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select act.exme_actpaciente_id, ");
		sql.append(" cta.documentno as cta, pac.nombre_pac, act.created,  ");
		sql.append(" inte.value || ' - ' || inte.name as inte  ");
		sql.append(" from exme_actpaciente act ");
		sql.append(" inner join exme_ctapac cta ");
		sql.append("  on cta.exme_ctapac_id = act.exme_ctapac_id ");
		sql.append(" inner join exme_progquiro prog ");
		sql.append("  on prog.exme_ctapac_id = cta.exme_ctapac_id ");
		sql.append(" inner join exme_progquirodet det ");
		sql.append("  on det.exme_progquiro_id = prog.exme_progquiro_id ");
		sql.append(" inner join exme_intervencion inte ");
		sql.append("  on inte.exme_intervencion_id = det.exme_intervencion_id ");
		sql.append(" inner join exme_paciente pac ");
		sql.append("  on pac.exme_paciente_id = act.exme_paciente_id ");

		sql.append(" where inte.exme_intervencion_id = ? ");
		if (EXME_Paciente_ID > 0) {
			sql.append(" AND pac.exme_paciente_id = ? ");
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPaciente", "act"));
		sql.append(" order by act.created desc ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Intervencion_ID);
			pstmt.setInt(1, EXME_Intervencion_ID);
			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(2, EXME_Paciente_ID);
			}
			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(2, EXME_Paciente_ID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ActPacienteVO vo = new ActPacienteVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("exme_actpaciente_id")));
				vo.setNombrePac(rs.getString("nombre_pac"));
				vo.setValor(rs.getString("inte"));
				vo.setCtaPac(rs.getString("cta"));
				lst.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Obtiene listado de Actividades relacionadas a un medico
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_Medico_ID
	 *            Medico
	 * @param EXME_Paciente_ID
	 *            Paciente
	 * @return Listado de actividades
	 */
	public static List<ActPacienteVO> getFromMedico(Properties ctx,
			int EXME_Medico_ID, int EXME_Paciente_ID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActPacienteVO> lst = new ArrayList<ActPacienteVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("select act.exme_actpaciente_id,  ");
		sql.append(" NVL(cta.documentno,'') as cta, pac.nombre_pac, act.created, ");
		sql.append("  (med.value || ' - ' || med.nombre_med) as prod  ");
		sql.append(" from exme_actpaciente act ");
		sql.append("inner join exme_medico med  ");
		sql.append(" on med.exme_medico_id = act.exme_medico_id ");
		sql.append("inner join exme_paciente pac  ");
		sql.append(" on pac.exme_paciente_id = act.exme_paciente_id ");
		sql.append("left join exme_ctapac cta  ");
		sql.append(" on cta.exme_ctapac_id = act.exme_ctapac_id ");
		sql.append("where med.exme_medico_id = ? ");
		if (EXME_Paciente_ID > 0) {
			sql.append(" AND pac.exme_paciente_id = ? ");
		}
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "EXME_ActPaciente", "act"));
		sql.append(" order by act.created desc ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Medico_ID);
			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(2, EXME_Paciente_ID);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ActPacienteVO vo = new ActPacienteVO();
				vo.setFecha(rs.getTimestamp("created"));
				vo.setIdColumn(new IDColumn(rs.getInt("exme_actpaciente_id")));
				vo.setNombrePac(rs.getString("nombre_pac"));
				vo.setCtaPac(rs.getString("cta"));
				vo.setValor(rs.getString("prod"));
				lst.add(vo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pacienteId
	 * @param trxName
	 * @param AD_Org_ID
	 * @TODO Revisar si esto aun aplica para la nueva funcionalidad de cuestionarios
	 * @return
	 */
	public static MEXMEActPaciente getActPacienteHC(Properties ctx, int pacienteId,
			String trxName, int AD_Org_ID) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ActPaciente.*  ");
		sql.append(" FROM EXME_ActPaciente ");
		sql.append(" inner join EXME_ActPacientedet apd  ");
		sql.append(" on EXME_ActPaciente.exme_actpaciente_id = apd.exme_actpaciente_id ");
		sql.append(" inner join exme_configec ce  ");
		sql.append(" on apd.exme_cuestionario_id = ce.exme_cuestionario2_id ");
		sql.append(" WHERE EXME_ActPaciente.IsActive = 'Y'  ");
		sql.append(" AND EXME_ActPaciente.EXME_Paciente_ID = ?");

		POInfo poInfo = POInfo.getPOInfo(ctx, MEXMEActPaciente.Table_ID);

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), poInfo, null, AD_Org_ID));
		sql.append(" order by EXME_ActPaciente.created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MEXMEActPaciente actPac = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				actPac = new MEXMEActPaciente(ctx, rs, trxName);

			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getActPaciente (" + sql + ")", e);

		} finally {
			DB.close(rs, pstmt);
		}
		//
		return actPac;
	}

}
