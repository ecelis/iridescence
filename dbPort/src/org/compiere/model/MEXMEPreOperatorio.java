package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

/**
 * Modelo de PreOperatorio <b>Modificado: </b> $Author: tperez $
 * <p>
 * <b>En :</b> $Date: 2009/08/20 15:15:26 $
 * <p>
 * Modelo de las solicitudes de cirugias
 * 
 * 
 * Crear        =      Draft -  Aprobar
 * Aprobar      =   Aprobado -  Completar
 * Programar    =   Completo -  Cerrar
 * Diagnosticar =    Cerrado -  -- 
 * 
 * Reagendar Nuevo =        Draft -  Aprobar
 * Reagendar Viejo =      Invalid -  --
 * Cancelar        =         Void -  --
 * No Aprobado     = Not Approved -  --
 * 
 * @author Twry Perez
 * @version $Revision: 1.0 $
 */
public class MEXMEPreOperatorio extends X_EXME_PreOperatorio {

	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MEXMEPreOperatorio.class);
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor Identificador
	 * @param ctx
	 * @param EXME_PreOperatorio_ID
	 * @param trxName
	 */
	public MEXMEPreOperatorio(Properties ctx, int EXME_PreOperatorio_ID, String trxName) {
		super(ctx, EXME_PreOperatorio_ID, trxName);
	}

	/**
	 * Constructor Result Set
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPreOperatorio(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Obtenemos el Medico
	 * @return
	 */
	private MEXMEMedico medico = null;
	
	public MEXMEMedico getMedico() {
		if (medico == null && getEXME_Medico_ID() > 0) {
			medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
		}
		return medico;
	}
	
	/**
	 * Obtenemos el Anestesi�logo
	 * @return
	 */
	private MEXMEMedico anestesiologo = null;
	
	public MEXMEMedico getAnestesiologo() {
		if (anestesiologo == null && getEXME_Medico_Anest_ID() > 0) {
			anestesiologo = new MEXMEMedico(getCtx(), getEXME_Medico_Anest_ID(), get_TrxName());
		}
		return anestesiologo;
	}
	
	
	/**
	 * Obtenemos el Diagnostico Original
	 * @return
	 */
	private MDiagnostico diagnostico = null;
	
	public MDiagnostico getDiagnostico() {
		if (diagnostico == null && getEXME_Diagnostico_ID() > 0) {
			diagnostico = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
		}
		return diagnostico;
	}
	
	/**
	 * Obtenemos el Diagnostico postoperatorio
	 * @return
	 */
	private MDiagnostico diagnosticoPost = null;
	
	public MDiagnostico getDiagnosticoPost() {
		if (diagnosticoPost == null && getEXME_Diagnostico_Post_ID() > 0) {
			diagnosticoPost = new MDiagnostico(getCtx(), getEXME_Diagnostico_Post_ID(), get_TrxName());
		}
		return diagnosticoPost;
	}
	
	/**
	 * Obtenemos la Intervencion
	 * @return
	 */
	private MIntervencion intervencion = null;
	
	public MIntervencion getIntervencion() {
		if (intervencion == null && getEXME_Intervencion_ID() > 0) {
			intervencion = new MIntervencion(getCtx(), getEXME_Intervencion_ID(), get_TrxName());
		}
		return intervencion;
	}
	
	
	/**
	 * Obtenemos la Programacion de quirofanos
	 * @return
	 */
	private MProgQuiro progQuiro = null;
	
	public MProgQuiro getProgQuiro() {
		if (progQuiro == null && getEXME_ProgQuiro_ID() > 0) {
			progQuiro = new MProgQuiro(getCtx(), getEXME_ProgQuiro_ID(), get_TrxName());
		}
		return progQuiro;
	}
	
	/**
	 * Obtenemos las solicitudes de cirugias,
	 * los filtros dependeran del usuario
	 * @param ctx El contexto de la aplicacion
	 * @param buscar  campo a filtrar
	 * @param valor Valor del filtro
	 * @param fecha Fecha de Programacion (formato > Constantes.sdfFecha(ctx))
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPreOperatorio> getSolicitudes(Properties ctx, String buscar, String valor, String fecha,
			String trxName) {
		return getSolicitudes(ctx, buscar, valor, fecha, null, false, trxName);
	}
	
	/**
	 * Obtenemos las solicitudes de cirugias,
	 * los filtros dependeran del usuario
	 * @param ctx El contexto de la aplicacion
	 * @param buscar campo a filtrar
	 * @param valor Valor del filtro
	 * @param fecha Fecha de Programacion (formato > Constantes.sdfFecha(ctx))
	 * @param orderBy Orden de los registros
	 * @param all incluye las completadas
	 * @param trxName Nombre de la transacci&oacute;n
	 * @return
	 */
	public static List<MEXMEPreOperatorio> getSolicitudes(Properties ctx, String buscar, String valor, String fecha,
			String orderBy, boolean all, String trxName) {

		final List<MEXMEPreOperatorio> list = new ArrayList<MEXMEPreOperatorio>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT po.EXME_PreOperatorio_ID ");
		sql.append(" , p.Value AS Historia ");
		sql.append(" , p.Nombre_Pac ");
		sql.append(" , m.Nombre_Med  ");
		sql.append(" , e.Name AS Especialidad ");
		sql.append(" , i.Name AS Intervencion ");
		sql.append(" , w.Name AS AlmacenSol ");
		sql.append(" , cp.DocumentNo ");
		sql.append(" , he.Expediente ");
		sql.append(" , q.Name AS Quirofano ");
		sql.append(" , pa.Value AS ProcAnestVal ");
		sql.append(" , pa.Name AS ProcAnestesico ");

		sql.append(" FROM EXME_PreOperatorio po ");

		sql.append(" LEFT  JOIN EXME_Paciente p       ON ( po.EXME_Paciente_ID     = p.EXME_Paciente_ID     )  ");
		sql.append(" LEFT  JOIN EXME_Medico m         ON ( po.EXME_Medico_ID       = m.EXME_Medico_ID       ) ");
		sql.append(" LEFT  JOIN EXME_Especialidad e   ON ( po.EXME_Especialidad_ID = e.EXME_Especialidad_ID ) ");
		sql.append(" LEFT  JOIN EXME_Intervencion i   ON ( po.EXME_Intervencion_ID = i.EXME_Intervencion_ID ) ");
		sql.append(" LEFT  JOIN EXME_ProcAnestesico pa ON ( po.exme_procanestesico_id = pa.exme_procanestesico_id ) ");
		sql.append(" LEFT  JOIN M_Warehouse w         ON ( po.M_Warehouse_Sol_ID   = w.M_Warehouse_ID      ) ");
		sql.append(" LEFT  JOIN EXME_CtaPac cp    ON ( po.EXME_CtaPac_ID    = cp.EXME_CtaPac_ID ) ");
		//freyes solo un mrn por org
		sql.append(" LEFT  JOIN EXME_Hist_Exp he  ON ( po.EXME_Paciente_ID  = he.EXME_Paciente_ID and he.isactive ='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "he"));
		sql.append(") ");
		sql.append(" LEFT  JOIN EXME_ProgQuiro pq ON ( po.EXME_ProgQuiro_ID = pq.EXME_ProgQuiro_ID ) ");
		sql.append(" LEFT  JOIN EXME_Quirofano q  ON ( pq.EXME_Quirofano_ID = q.EXME_Quirofano_ID ) ");

		sql.append(" WHERE po.IsActive = 'Y' ");
		sql.append(all ? "" : " AND po.DocStatus <> ? ");// #1 'CL'

		// VO Cancelado - CL Cerrado - IN Reagendado
		sql.append(" AND po.DocStatus NOT IN ( ?, ? ) ");// #2 #3 'VO' , 'IN'
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "po"));

		if (StringUtils.isNotBlank(fecha)) {
			sql.append(" AND TO_CHAR(po.FechaProg, ");
			sql.append(DB.TO_STRING(Constantes.sdfFecha(ctx).toPattern()));
			sql.append(") = ? ");// 'DD/MM/YYYY'
		}

		// el campo a buscar es la fecha ??
		if (valor != null) {
			if (buscar.equalsIgnoreCase("po.DateOrdered")) {
				if (!valor.contains("%")) {
					if (DB.isOracle()) {
						sql.append(" AND TRUNC(").append(buscar).append(",'DD') = TO_DATE(?, ");
					} else if (DB.isPostgreSQL()) {
						sql.append(" AND DATE_TRUNC('day', ").append(buscar).append(" ) = TO_DATE(?, ");
					}
					sql.append(DB.TO_STRING(Constantes.sdfFecha(ctx).toPattern())).append(") ");// 'DD/MM/YYYY'
				}
			} else {
				sql.append(" AND UPPER(").append(buscar).append(") LIKE UPPER(?) ");
			}
		}

		sql.append(orderBy == null ? " ORDER BY po.Priorityrule DESC, po.DateOrdered ASC, po.EXME_PreOperatorio_ID ASC "
				: orderBy);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			if (!all) {
				pstmt.setString(index++, DOCSTATUS_Closed);
			}
			pstmt.setString(index++, DOCSTATUS_Voided);
			pstmt.setString(index++, DOCSTATUS_Invalid);
			if (StringUtils.isNotBlank(fecha)) {
				pstmt.setString(index++, fecha);
			}
			if (valor != null && (!buscar.equalsIgnoreCase("po.DateOrdered") || !valor.contains("%"))) {
				pstmt.setString(index++, valor);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEPreOperatorio preOperatorio = new MEXMEPreOperatorio(ctx, rs.getInt(1), trxName);
				preOperatorio.setHistoria(rs.getString("Historia"));
				preOperatorio.setNombre_Pac(SecureEngine.decrypt(rs.getString("Nombre_Pac")));
				preOperatorio.setNombre_Med(rs.getString("Nombre_Med"));
				preOperatorio.setEspecialidad(rs.getString("Especialidad"));
				preOperatorio.setCirugia(rs.getString("Intervencion"));
				preOperatorio.setAlmacenSol(rs.getString("AlmacenSol"));
				preOperatorio.setDocumentNo(rs.getString("DocumentNo"));
				preOperatorio.setExpediente(rs.getString("Expediente"));
				preOperatorio.setQuirofano(rs.getString("Quirofano"));	
				preOperatorio.setProcAnestesico(rs.getString("ProcAnestesico"));
				preOperatorio.setProcAnestValue(rs.getString("ProcAnestVal"));
				list.add(preOperatorio);
			}
			
			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/** propiedades **/

	private String historia = null;

	private String nombre_Med = null;

	private String almacenSol = null;

	private String documentNo = null;

	private String expediente = null;

	private String quirofano = null;

	private String especialidad = null;

	private String cirugia = null;

	// proc anestesico
	private String procAnestesico = null;
	private String procAnestValue = null;

	public MEXMEMedico getM_Medico() {
		return medico;
	}

	public void setM_Medico(MEXMEMedico medico) {
		this.medico = medico;
	}

	public MEXMEMedico getM_Anestesiologo() {
		return anestesiologo;
	}

	public void setM_Anestesiologo(MEXMEMedico anestesiologo) {
		this.anestesiologo = anestesiologo;
	}

	public MDiagnostico getM_Diagnostico() {
		return diagnostico;
	}

	public void setM_Diagnostico(MDiagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public MDiagnostico getM_DiagnosticoPost() {
		return diagnosticoPost;
	}

	public void setM_DiagnosticoPost(MDiagnostico diagnosticoPost) {
		this.diagnosticoPost = diagnosticoPost;
	}

	public MIntervencion getM_Intervencion() {
		return intervencion;
	}

	public void setM_Intervencion(MIntervencion intervencion) {
		this.intervencion = intervencion;
	}

	public MProgQuiro getM_ProgQuiro() {
		return progQuiro;
	}

	public void setM_ProgQuiro(MProgQuiro progQuiro) {
		this.progQuiro = progQuiro;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public String getFechaSolicitud() {
		return getDateOrdered() == null ? "12/12/1900 00:00" : Constantes.getSdfFechaHora().format(getDateOrdered());
	}

	public String getNombre_Med() {
		return nombre_Med;
	}

	public void setNombre_Med(String nombre_Med) {
		this.nombre_Med = nombre_Med;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getAlmacenSol() {
		return almacenSol;
	}

	public void setAlmacenSol(String almacenSol) {
		this.almacenSol = almacenSol;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(String quirofano) {
		this.quirofano = quirofano;
	}
	
	public String getCirugia() {
		return cirugia;
	}

	public void setCirugia(String cirugia) {
		this.cirugia = cirugia;
	}
	
	public String getProcAnestesico() {
		return procAnestesico;
	}

	public void setProcAnestesico(String procAnestesico) {
		this.procAnestesico = procAnestesico;
	}	
	
	
	public MEXMEPreOperatorio copy() {
		final MEXMEPreOperatorio preoOperatorioNew = new MEXMEPreOperatorio(getCtx(), 0, get_TrxName());
		
		preoOperatorioNew.setDateOrdered (getDateOrdered());
		preoOperatorioNew.setDocAction (getDocAction());
		preoOperatorioNew.setDocStatus (getDocStatus());
		preoOperatorioNew.setEdad (getEdad());
		preoOperatorioNew.setEXME_Diagnostico_ID (getEXME_Diagnostico_ID());
		preoOperatorioNew.setEXME_Especialidad_ID (getEXME_Especialidad_ID());
		preoOperatorioNew.setEXME_Intervencion_ID (getEXME_Intervencion_ID());
		preoOperatorioNew.setEXME_Medico_ID (getEXME_Medico_ID());
		preoOperatorioNew.setEXME_Paciente_ID (getEXME_Paciente_ID());
		preoOperatorioNew.setIsApproved (isApproved());	// Y
		preoOperatorioNew.setM_Warehouse_Sol_ID (getM_Warehouse_Sol_ID());
		
		preoOperatorioNew.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		preoOperatorioNew.setEXME_Medico_Anest_ID(getEXME_Medico_Anest_ID());
		preoOperatorioNew.setEXME_Quirofano_ID(getEXME_Quirofano_ID());
		preoOperatorioNew.setEXME_MotivoCancel_ID(getEXME_MotivoCancel_ID());
		preoOperatorioNew.setEXME_MotivoReagenda_ID(getEXME_MotivoReagenda_ID());
		preoOperatorioNew.setEXME_Diagnostico_Post_ID(getEXME_Diagnostico_Post_ID());
		preoOperatorioNew.setEXME_ProgQuiro_ID(getEXME_ProgQuiro_ID());
		
		preoOperatorioNew.setTipoAnestesia(getTipoAnestesia());
		preoOperatorioNew.setDescription(getDescription());
		preoOperatorioNew.setPriorityRule(getPriorityRule());
		preoOperatorioNew.setTrasplante(isTrasplante());
		preoOperatorioNew.setFechaProg(getFechaProg());
		preoOperatorioNew.setFechaAprob(getFechaAprob());
		preoOperatorioNew.setEXME_PreOperatorioReagen_ID(getEXME_PreOperatorioReagen_ID());
		preoOperatorioNew.setNotasReagenda(getNotasReagenda());
		preoOperatorioNew.setEXME_ProcAnestesico_ID(getEXME_ProcAnestesico_ID());
		preoOperatorioNew.setAD_User_ID(getAD_User_ID());
		preoOperatorioNew.setFecha(getFecha());
		preoOperatorioNew.setEXME_PatientClass_ID(getEXME_PatientClass_ID());
		preoOperatorioNew.setNombre_Pac(getNombre_Pac());
		preoOperatorioNew.setForaneo(isForaneo());
		preoOperatorioNew.setTrasplante(isTrasplante());
		//preoOperatorioNew.setTipoCirugia(getTipoCirugia().trim());
		preoOperatorioNew.setEXME_Medico_Aprob_ID(getEXME_Medico_Aprob_ID());
		preoOperatorioNew.setFechaAnest(getFechaAnest());
		preoOperatorioNew.setDuracion(getDuracion());
		preoOperatorioNew.setIsApprovedAnest(isApprovedAnest());
		preoOperatorioNew.setProgramado(isProgramado());
		
		if (getTipoCirugia() == null) {
			preoOperatorioNew.setTipoCirugia(X_EXME_PreOperatorio.TIPOCIRUGIA_Hospitalization);
		} else {
			preoOperatorioNew.setTipoCirugia(getTipoCirugia().trim());
		}
		return preoOperatorioNew;
	}

	@Deprecated
	public int getId() {
		return getEXME_PreOperatorio_ID();
	}
	
	/**
	 * Obtenemos las solicitudes de cirug�as,
	 * los filtros depender�n del usuario
	 * @param ctx      El contexto de la aplicacion
	 * @param estatus  El estatus de la cuenta paciente
	 * @param value    Filtrar por value del paciente
	 * @param trxName  Nombre de la transacci&oacute;n
	 * @return
	 */
	public static MEXMEPreOperatorio getSolicitud(Properties ctx, int EXME_ProgQuiro_ID, String trxName) {

		MEXMEPreOperatorio prog = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT po.* ")
		.append(" FROM EXME_PreOperatorio po ")
		.append(" WHERE po.IsActive = 'Y' AND po.EXME_ProgQuiro_ID = ? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "po"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ProgQuiro_ID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				prog = new MEXMEPreOperatorio(ctx, rs, trxName);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return prog;
	}

	/**
	 * Obtenemos las solicitudes de cirug�as,
	 * los filtros depender�n del usuario
	 * @param ctx      El contexto de la aplicacion
	 * @param estatus  El estatus de la cuenta paciente
	 * @param value    Filtrar por value del paciente
	 * @param trxName  Nombre de la transacci&oacute;n
	 * @return
	 */
	public static boolean getSolicitudPac(Properties ctx, 
			int EXME_Paciente_ID, int EXME_CtaPac_ID, 
			String fecha, String trxName) {

		boolean retValue = true;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT po.* ")
		.append(" FROM EXME_PreOperatorio po ")
		.append(" WHERE po.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "po"))
		.append("  AND po.DocStatus <> 'VO' AND po.DocStatus <> 'CL' AND po.DocStatus <> 'IN' ")
		.append("  AND po.DocStatus <> 'NA' AND po.EXME_Paciente_ID = ? ")
		.append(" AND to_char( po.DateOrdered,'dd/mm/yyyy') >= ? "); 

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setString(2, fecha);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEPreOperatorio prog = new MEXMEPreOperatorio(ctx, rs, trxName);
				prog.setEXME_CtaPac_ID(EXME_CtaPac_ID);
				retValue = prog.save();
				if (!retValue) {
					break;
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			retValue = false;
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	public String getProcAnestValue() {
		return procAnestValue;
	}

	public void setProcAnestValue(String procAnestValue) {
		this.procAnestValue = procAnestValue;
	}

	// diagnostico

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			success = saveDiag(newRecord);
		}
		return success;
	}
	
	/**
	 * Save/Update diagnostic of patient
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean saveDiag(boolean newRecord){
		boolean saved;
		if (getActPacienteID() <= 0) { // find actPaciente
			saved = false;
		} else {
			saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID), diagnosisText);
			if (saved) {
				saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_Post_ID), diagnosisPostText);
			}
		}
		return saved;
	}
	
	private int actPacienteID = 0;

	public int getActPacienteID() {

		if (actPacienteID <= 0) {
			if (getEXME_CtaPac_ID() > 0) {
				final MEXMEActPaciente actPac = MEXMEActPaciente.getActPaciente(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());
				if (actPac != null) {
					actPacienteID = actPac.getEXME_ActPaciente_ID();
				}
			} else {
				createAct();
			}
		}
		return actPacienteID;
	}

	/**
	 * Creates an activity of patient
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean createAct(){
		if (getEXME_Paciente_ID() <= 0 && getEXME_Especialidad_ID() <= 0) {
			return false;
		}
		
		final MEXMEActPaciente actPac = new MEXMEActPaciente(getCtx(), 0, get_TrxName());
		actPac.setEXME_Paciente_ID(getEXME_Paciente_ID());
		actPac.setEXME_Especialidad_ID(getEXME_Especialidad_ID());
		actPac.setTipoArea(Env.getTipoArea(getCtx()));
		actPac.setFecha(DB.getTimestampForOrg(getCtx()));
		actPac.setName(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_EXME_PreOperatorio_ID));
		
		final boolean saved = actPac.save();
		if(saved){
			actPacienteID = actPac.getEXME_ActPaciente_ID();
		} else {
			log.log(Level.SEVERE, "EXME_ActPaciente.save" );
		}
		return saved;
	}
	
	/**
	 * Obtener las solicitudes pendientes (para recordatorios)
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 * @author lizeth de la garza
	 * @author lorena lama
	 */
	public static List<ValueNamePair> getPacSolPending(Properties ctx, int pacienteID, String trxName) {

		final List<ValueNamePair> list = new ArrayList<ValueNamePair>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT pre.value, pre.EXME_PreOperatorio_ID ");
		sql.append(", pre.FechaProg ");
		sql.append(" FROM EXME_PreOperatorio pre  ");
		sql.append(" WHERE pre.EXME_Paciente_ID=? "); // #1
		sql.append(" AND pre.DocStatus IN (?,?) ");// #2,3
		if (DB.isOracle()) {
			sql.append(" AND trunc(pre.FechaProg,'DD') >= ? ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', pre.FechaProg) >= ? ");
		}
		// sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pre")); ? 
		sql.append(" ORDER BY pre.FechaProg DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			pstmt.setString(2, DOCSTATUS_Drafted);
			pstmt.setString(3, DOCSTATUS_WaitingConfirmation);
			pstmt.setTimestamp(4, TimeUtil.getDay(Env.getCurrentDate()));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Lama
				final StringBuilder label = new StringBuilder();
				label.append(Utilerias.getMsg(ctx, "title.solicitudCirugia"));
				label.append(rs.getString("Value")).append("-");
				label.append(Constantes.getSDFDateTime(ctx).formatConvert(rs.getTimestamp("FechaProg")));

				final StringBuilder value = new StringBuilder();
				value.append(MEXMEProgRecordatorio.TIPORECORDATORIO_EXME_PreOperatorio);
				value.append("-").append(rs.getInt("EXME_PreOperatorio_ID"));
				list.add(new ValueNamePair( value.toString(),label.toString()));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	
	/**
	 * Quick search
	 * @param ctx
	 * @param trxName
	 * @param value
	 * @param results maximum number of results
	 * @param colsNames
	 * @return
	 */
	public static List<MEXMEPreOperatorio> quickSearch(Properties ctx, String trxName, String value, int results,
			String... colsNames) {
		final List<MEXMEPreOperatorio> retValue = new ArrayList<MEXMEPreOperatorio>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			sql.append(" SELECT EXME_PreOperatorio.*, pac.Nombre_Pac, med.Nombre_Med ");
			sql.append(" FROM EXME_PreOperatorio ");
			sql.append(" LEFT  JOIN EXME_Paciente pac ON EXME_PreOperatorio.EXME_Paciente_ID = pac.EXME_Paciente_ID ");
			sql.append(" INNER JOIN EXME_Medico med ON EXME_PreOperatorio.EXME_Medico_ID = med.EXME_Medico_ID ");
			sql.append(" WHERE EXME_PreOperatorio.IsActive = 'Y' ");
			sql.append(" AND EXME_PreOperatorio.DocStatus IN ( ? ) ");
			if (ctx != null) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			}

			final String[] txt = StringUtils.split(value);
			final Object[] params = new Object[colsNames.length * txt.length];
			int x = 0;
			for (int j = 0; j < txt.length; j++) {
				sql.append(" AND ( ");
				// agregar las columnas
				for (int i = 0; i < colsNames.length; i++) {
					if (i > 0) {
						sql.append(" OR ");
					}
					sql.append(" UPPER(TRANSLATE(");
					sql.append(colsNames[i]).append(", ").append(Constantes.TRANSLATE_VOCALS).append("))");
					sql.append(" LIKE UPPER(TRANSLATE(?, ").append(Constantes.TRANSLATE_VOCALS).append("))");

					params[x++] = StringUtils.isBlank(txt[j]) ? txt[j] : StringUtils.replace(txt[j].toUpperCase(), "*", "%");
				}
				sql.append(") ");
			}
			
			if (DB.isOracle()) {
				sql.append(" AND rownum <=  ").append(results);	
			}
			
			sql.append(" ORDER BY pac.nombre_pac ");
			
			if (DB.isPostgreSQL()) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, results));	
			}

			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 0;
			DB.setParameter(pstmt, ++i, DOCSTATUS_Approved);
			for (int j = 0; j < params.length; j++) {
				DB.setParameter(pstmt, ++i, params[j]);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMEPreOperatorio obj = new MEXMEPreOperatorio(ctx, rs, trxName);
				obj.setNombre_Med(rs.getString("Nombre_Med"));
				retValue.add(obj);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	 /**Descriptive diagnostic */
	private String diagnosisText = null;
	/** Descriptive diagnostic 2 */
	private String diagnosisPostText = null;
	
	
	private boolean saveDiagnosis(boolean newRecord, int columnIdx, String diagnosis) {
		boolean saveDiag;
		boolean saved;
		if (newRecord) {
			// if has a diagnostic
			saveDiag = get_ValueAsInt(columnIdx) > 0 || StringUtils.isNotBlank(diagnosis);
		} else {
			// if diagnosis code has changed or diagnosis descriptions has been assigned
			saveDiag = is_ValueChanged(columnIdx) || StringUtils.isNotBlank(diagnosis);
		}
		saved = !saveDiag;
		if (saveDiag) {
			// The diagnosis text is not saved in this table
			saved = MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), get_ColumnName(columnIdx), diagnosis,
					null);
		}
		return saved;
	}

	public String getDiagnosisText() {
		if (diagnosisText == null && get_ID() > 0) {
			diagnosisText = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID));
		}
		return diagnosisText;
	}

	public void setDiagnosisText(String diagnosisText) {
		this.diagnosisText = diagnosisText;
	}

	public String getDiagnosisPostText() {
		if (diagnosisPostText == null && get_ID() > 0) {
			diagnosisPostText = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_Post_ID));
		}
		return diagnosisPostText;
	}

	public void setDiagnosisPostText(String diagnosisPostText) {
		this.diagnosisPostText = diagnosisPostText;
	}

	private String getDiagnosisText(int columnIdx) {
		// The diagnosis text is not saved in this table
		final MActPacienteDiag actdiag = MActPacienteDiag.get(getCtx(), get_ID(), get_Table_ID(), getActPacienteID(),
				get_ValueAsInt(columnIdx), get_ColumnName(columnIdx), get_TrxName());
		return actdiag == null ? "" : actdiag.getDiagnosis() == null ? "" : actdiag.getDiagnosis();
	}
	
	
	public String getMsjEmail() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "title.solicitudCirugia"));
		msj.append(getValue());
		msj.append(" ");
		msj.append(Utilerias.getMsg(getCtx(), "msj.fechaProgramada"));
		msj.append(":");
		msj.append(Constantes.getSdfFechaHora().format(getFechaProg()));
		return msj.toString();
	}
	
	public String getMsjSMS() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.solCirugiaSMS"));
		msj.append(getValue());
		msj.append("+");
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.FechaProg"));
		msj.append(":");
		msj.append(StringUtils.replaceChars(Constantes.getSdfFechaHora().format(getFechaProg()), " ", "+"));
		return msj.toString();
	}
	

}
