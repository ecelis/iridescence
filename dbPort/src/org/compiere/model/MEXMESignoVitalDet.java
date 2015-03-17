package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
import org.compiere.util.XYValues;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Patient's Vital Signs
 * 
 * @author Lorena Lama
 */
public class MEXMESignoVitalDet extends X_EXME_SignoVitalDet {

	/** serialVersionUID */
	private static final long serialVersionUID = 7805582302432854472L;

	/** Objeto cuenta paciente */
	private MEXMECtaPac ctaPac = null;
	private MEXMESignoVital signoVital = null;
	private BigDecimal valorTo = null;
	private MUser user = null;
	private int exmeRangoSVid = 0;
	private String signoName = getSignoVital().getName();

	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MEXMESignoVitalDet.class);

	/**
	 * @param ctx
	 * @param EXME_SignoVitalDet_ID
	 * @param trxName
	 */
	public MEXMESignoVitalDet(Properties ctx, int EXME_SignoVitalDet_ID, String trxName) {
		super(ctx, EXME_SignoVitalDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESignoVitalDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Copia la signo vital det a partir de un objeto origen
	 * 
	 * @param object
	 *            MSignoVitalDet
	 * @return nuevo objeto MSignoVitalDet igual al oldObject pero con id en cero.
	 * @author Noelia
	 */
	public static MEXMESignoVitalDet copyFrom(MEXMESignoVitalDet object) {
		final MEXMESignoVitalDet newObject = new MEXMESignoVitalDet(object.getCtx(), 0, object.get_TrxName());
		copyValuesNew(object, newObject);
		return newObject;
	}

	/**
	 * 
	 * @param ctx
	 * @param columnParams
	 * @param orderBy
	 * @param firstResult
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getAllFrom(Properties ctx, Map<String, Object> columnParams, String orderBy,
			boolean firstResult, String trxName) {
		// Where clause
		final StringBuilder whereClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// Parameters
		final List<Object> params = new ArrayList<Object>();
		if (columnParams != null) {
			for (Entry<String, Object> entry : columnParams.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getKey())) {
					if (whereClause.length() > 0) {
						whereClause.append(" AND ");
					}
					whereClause.append(entry.getKey());
				}
				if (entry.getValue() != null) {
					params.add(entry.getValue());
				}
			}
		}
		final StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		joins.append(" INNER JOIN EXME_SignoVital ON ");
		joins.append("(EXME_SignoVital.EXME_signovital_id=EXME_SignoVitalDet.EXME_signovital_id ");
		joins.append(" AND EXME_SignoVital.IsActive='Y') ");

		if (StringUtils.isEmpty(orderBy)) {
			orderBy = "EXME_SignoVitalDet.folio,EXME_SignoVitalDet.fecha,EXME_SignoVitalDet.Estatus,EXME_SignoVital.Secuencia";
		}
		final Query query = new Query(ctx, Table_Name, whereClause.toString(), trxName)//
				.setJoins(joins)//
				.setParameters(params)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(orderBy);
		final List<MEXMESignoVitalDet> retValue;
		if (firstResult) {
			retValue = new ArrayList<MEXMESignoVitalDet>();
			final MEXMESignoVitalDet obj = (MEXMESignoVitalDet) query.first();
			if (obj != null) {
				retValue.add(obj);
			}
		} else {
			retValue = query.list();
		}
		return retValue;
	}

	/**
	 * Obtenemos los ultimos signos vitales registrados a un Paciente
	 * 
	 * @param ctx
	 * @param patientID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getLastSignoVital(Properties ctx, long patientID, String trxName) {
		return getLastSignoVital(ctx, (int)patientID, -1, null, null, trxName);
	}
	/**
	 * Obtenemos los ultimos signos vitales registrados a un Paciente
	 * 
	 * @param ctx
	 * @param patientID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getLastSignoVital(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, Date fechaIni,
			Date fechaFin, String trxName) {
		return getLastSignoVital(ctx, EXME_Paciente_ID, EXME_CtaPac_ID, fechaIni, fechaFin, false, trxName);
	}
	/**
	 * Obtenemos los ultimos signos vitales registrados a un Paciente
	 * 
	 * @param ctx
	 * @param patientID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getLastSignoVital(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, Date fechaIni,
			Date fechaFin, boolean derechoHabiente, String trxName) {
		final List<MEXMESignoVitalDet> retValue = new ArrayList<MEXMESignoVitalDet>();
		//Card #1545 ProMujer 
		//Se agrega nivel de acceso a Cliente unicamente
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM  EXME_SignoVitalDet ");
		sql.append(" INNER JOIN EXME_SignoVital ON ");
		sql.append("(EXME_SignoVital.EXME_signovital_id=EXME_SignoVitalDet.EXME_signovital_id ");
		sql.append(" AND EXME_SignoVital.IsActive='Y') ");
		sql.append(" WHERE EXME_SignoVitalDet.IsActive='Y' ");
		// mvrodriguez
		sql.append(" AND EXME_SignoVitalDet.exme_signovitaldet_id IN ");
		sql.append(" (  SELECT B.EXME_signovitaldet_id FROM  ");
		
		sql.append("                 ( SELECT MAX(fecha) as fecha, EXME_SIGNOVITAL_ID ");
		sql.append("                   FROM EXME_signovitaldet ");
		sql.append("                   WHERE isActive='Y' ");// Only active Records
		sql.append("                   AND trim(Estatus)=? ");//#1
		sql.append("                   AND EXME_paciente_id=? ");//#2
		params.add(ESTATUS_Active);
		params.add(EXME_Paciente_ID);
		if (EXME_CtaPac_ID > 0) {
			sql.append("               AND EXME_CTAPAC_ID=? ");//#3
			params.add(EXME_CtaPac_ID);
		}
		if (fechaIni != null) {//#4
			sql.append("               AND FECHA >=? ");
			params.add(new Timestamp(fechaIni.getTime()));
		}
		if (fechaFin != null) {//#5
			sql.append("               AND FECHA <=? ");
			params.add(new Timestamp(fechaFin.getTime()));
		}
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));// Only Client/Org records
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		
		
		sql.append("                   GROUP BY EXME_SignoVital_ID ");
		sql.append("                  ) A, ");
		
		sql.append("                 ( SELECT * FROM EXME_signovitaldet ");
		sql.append("                   WHERE isActive='Y' ");// Only active Records
		sql.append("                   AND trim(Estatus)=? ");//#1
		sql.append("                   AND EXME_paciente_id=? ");//#2
		params.add(ESTATUS_Active);
		params.add(EXME_Paciente_ID);
		if (EXME_CtaPac_ID > 0) {
			sql.append("               AND EXME_CTAPAC_ID=? ");//#3
			params.add(EXME_CtaPac_ID);
		}
		if (fechaIni != null) {//#4
			sql.append("               AND FECHA >=? ");
			params.add(new Timestamp(fechaIni.getTime()));
		}
		if (fechaFin != null) {//#5
			sql.append("               AND FECHA <=? ");
			params.add(new Timestamp(fechaFin.getTime()));
		}
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));// Only Client/Org records
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		sql.append("                  ) B ");
		
		sql.append("     WHERE A.fecha = B.fecha ");
		sql.append("     AND A.EXME_SIGNOVITAL_ID = B.EXME_SIGNOVITAL_ID ");
		sql.append(" ) ");
		
		if(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente){
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));// Only Client/Org records
		}else if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
		}
		sql.append(" ORDER BY EXME_SignoVital.secuencia" );
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.add(new MEXMESignoVitalDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 */
	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {
		final StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COUNT(*) FROM EXME_SignoVitalDet ");
		sql.append("WHERE EXME_SignoVitalDet.isActive='Y' ");
		sql.append("AND EXME_SignoVitalDet.EXME_CtaPac_ID= ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(trxName, sql.toString(), ctaPacId) > 0;
	}

	/**
	 * Liz de la Garza- Se obtiene el valor del siguiente folio
	 * 
	 * @param trxName
	 * @param clientID
	 * @return
	 */
	public static int nextFolio(String trxName, int clientID) {
		final String sql = "SELECT COALESCE(MAX(folio),0)+1 FROM EXME_SignoVitalDet WHERE AD_Client_ID=?";
		return DB.getSQLValue(trxName, sql, clientID);
	}

	/**
	 * 
	 * @param ctx
	 * @param columnParams
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getAllFrom(Properties ctx, Map<String, Object> columnParams, String orderBy,
			String trxName) {
		return getAllFrom(ctx, columnParams, orderBy, false, trxName);
	}

	/**
	 * Returns Patient Activity's vital signs grouped by Folio
	 * 
	 * @param ctx
	 * @param actPacienteId
	 *            Patient Activity
	 * @param trxName
	 * @return
	 * @author Lorena Lama
	 * @throws Exception
	 */
	public static List<MEXMESignoVitalDet> getFromAppointment(Properties ctx, int appointmentId, String trxName)
			throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EXME_SignoVital.EXME_CitaMedica_ID=?", appointmentId);
		map.put("TRIM(EXME_SIGNOVITALDET.Estatus)=?", MEXMESignoVitalDet.ESTATUS_Active);
		return getAllFrom(ctx, map, "EXME_SignoVital.Folio,EXME_SignoVitalDet.Secuencia", trxName);
	}

	/**
	 * Returns Appointment's vital signs grouped by Folio
	 * 
	 * @param ctx
	 * @param actPacienteId
	 *            Patient Activity
	 * @param trxName
	 * @return
	 * @author Lorena Lama
	 * @throws Exception
	 */
	public static List<List<MEXMESignoVitalDet>> getFromAct(Properties ctx, int actPacienteId, String trxName)
			throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EXME_SignoVital.EXME_ActPaciente_ID=?", actPacienteId);
		return getGroupByFolio(ctx, map, true, trxName);
	}

	/**
	 * Returns all vital signs grouped by Folio
	 * 
	 * @param ctx
	 * @param columnParams
	 *            (WHERECLAUSE+ParamValue)
	 * @param orderByDesc
	 *            if TRUE orders by EXME_SignoVitalDet.Fecha DESC
	 * @param trxName
	 * @return
	 * @author Lorena Lama
	 * @throws Exception
	 */
	public static List<List<MEXMESignoVitalDet>> getGroupByFolio(Properties ctx, Map<String, Object> columnParams,
			boolean orderByDesc, String trxName) throws Exception {
		return getGroupByFolio(ctx, columnParams, orderByDesc, trxName, false);
	}
	
	/**
	 * Returns all vital signs grouped by Folio
	 * 
	 * @param ctx
	 * @param columnParams
	 *            (WHERECLAUSE+ParamValue)
	 * @param orderByDesc
	 *            if TRUE orders by EXME_SignoVitalDet.Fecha DESC
	 * @param trxName
	 * @param derechoHabiente
	 * @return
	 * @author Lorena Lama
	 * @throws Exception
	 */
	public static List<List<MEXMESignoVitalDet>> getGroupByFolio(Properties ctx, Map<String, Object> columnParams,
			boolean orderByDesc, String trxName, boolean derechoHabiente) throws Exception {
		final List<List<MEXMESignoVitalDet>> lst = new ArrayList<List<MEXMESignoVitalDet>>();

		final StringBuilder orderby = new StringBuilder();
		orderby.append("EXME_SignoVitalDet.Fecha");
		if (orderByDesc) {
			orderby.append(" DESC");
		}
		orderby.append(",EXME_SignoVitalDet.Folio");
		if (orderByDesc) {
			orderby.append(" DESC");
		}// Defecto #506
		orderby.append(",EXME_SignoVitalDet.Estatus");
		orderby.append(",EXME_SignoVital.Secuencia");

		final List<MEXMESignoVitalDet> list = getAllFrom(ctx, columnParams, orderby.toString(), trxName);
		Integer folioInt = null;
		List<Integer> lstSignoId = new ArrayList<Integer>();
		List<MEXMESignoVitalDet> listSigVitales = new ArrayList<MEXMESignoVitalDet>();
		for (MEXMESignoVitalDet vsDet : list) {
			final int signId = vsDet.getEXME_SignoVital_ID();
			if (folioInt == null) {
				folioInt = vsDet.getFolio();
			} else if (folioInt != vsDet.getFolio() || lstSignoId.contains(signId)) {
				folioInt = vsDet.getFolio();
				lst.add(listSigVitales);
				listSigVitales = new ArrayList<MEXMESignoVitalDet>();
				lstSignoId.clear();
			}
			listSigVitales.add(vsDet);
			if (!lstSignoId.contains(signId)) {
				lstSignoId.add(signId);
			}
		}
		lst.add(listSigVitales);
		return lst;
	}

	/**
	 * Método que retorna lista de signos vitales capturados de un paciente.
	 * 
	 * @param ctx
	 * @param exmePacienteId
	 * @param fechaIni
	 * @param fechaFin
	 * @return lstSignos TODO
	 * @author lhernandez
	 * @throws Exception
	 */
	public static List<List<MEXMESignoVitalDet>> getHistoricSV(Properties ctx, int exmePacienteId, Timestamp fechaIni,
			Timestamp fechaFin, String estatus) throws Exception {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EXME_SIGNOVITALDET.EXME_PACIENTE_ID=?", exmePacienteId);
		map.put("EXME_SIGNOVITALDET.FECHA >= ? ", fechaIni);
		map.put("EXME_SIGNOVITALDET.FECHA <= ? ", fechaFin);
		map.put("TRIM(EXME_SIGNOVITALDET.Estatus)=?", estatus);// Lama: se parametriza el estatus
		return getGroupByFolio(ctx, map, true, null);
	}

	public static List<MEXMESignoVitalDet> getByFolio(Properties ctx, int exmePacienteId, int folio) {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EXME_SIGNOVITALDET.EXME_PACIENTE_ID=?", exmePacienteId);
		map.put("EXME_SIGNOVITALDET.Folio=?", folio);
		map.put("TRIM(EXME_SIGNOVITALDET.Estatus)=?", MEXMESignoVitalDet.ESTATUS_Active);
		final List<MEXMESignoVitalDet> retValue = getAllFrom(ctx, map, null, null);
		return retValue;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		if (success && newRecord) {
			// save BMI metrics
			if (Constantes.MASA.equals(getSignoVital().getValue())) {
				success = createMetrics();
			} else if (ESTATUS_Active.equals(getEstatus())) {
				/*
				 * mvrodriguez Actualice los campos de Talla y Peso de la tabla EXME_PACIENTE que es
				 * necesario que esten actualizados con los ultimos datos para Soporte de Decisiones
				 * Clinicas
				 */
				if (Constantes.PESO.equals(this.getEXME_SignoVital().getValue())
						|| Constantes.TALLA.equals(this.getEXME_SignoVital().getValue())) {
					// Lama: valida primero que sea por cuenta paciente por error en aplicacion de
					// vacunas
					if (getEXME_CtaPac_ID() > 0) {
						final MEXMECtaPac ctaPat = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
						if (getEXME_Paciente_ID() <= 0) {
							setEXME_Paciente_ID(ctaPat.getEXME_Paciente_ID());
						}
						if (getEXME_ActPaciente_ID() <= 0) {
							// actualizamos todas las actividades relacionadas a la cuenta
							final List<MEXMEActPaciente> actpacs = ctaPat.getActividades();
							for (MEXMEActPaciente actpac : actpacs) {
								actpac.set_TrxName(get_TrxName());
								saveWeightHeight(actpac, -1, -1, super.getValor());
							}
						}
					}
					// Lama: actualizamos la actividad paciente.
					if (getEXME_ActPaciente_ID() > 0) {
						saveWeightHeight(null, MEXMEActPaciente.Table_ID, getEXME_ActPaciente_ID(), super.getValor());
					}
					// Nurse journal
					if (getEXME_DiarioEnf_ID() > 0) {
						saveWeightHeight(null, MEXMEDiarioEnf.Table_ID, getEXME_DiarioEnf_ID(), super.getValor());
					}
					// Patient
					if (getEXME_Paciente_ID() > 0) {
						saveWeightHeight(null, MEXMEPaciente.Table_ID, getEXME_Paciente_ID(), super.getValor());
					}
				}
			}
		}
		return success;
	}

	private boolean saveWeightHeight(PO po, int tableID, int recordID, BigDecimal valor) {
		try {
			if (po == null && tableID > 0 && recordID > 0) {
				po = getPOModel(getCtx(), tableID, recordID, get_TrxName());
			}
			if (po != null && po.get_ID() > 0) {
				int index = -1;
				if (Constantes.PESO.equals(this.getEXME_SignoVital().getValue())) {
					index = po.get_ColumnIndex(X_EXME_Paciente.COLUMNNAME_Peso);
				} else if (Constantes.TALLA.equals(this.getEXME_SignoVital().getValue())) {
					index = po.get_ColumnIndex(X_EXME_Paciente.COLUMNNAME_Talla);
					if (index < 0) {
						index = po.get_ColumnIndex(X_EXME_ActPaciente.COLUMNNAME_Estatura);
					}
				}
				if (index >= 0) {
					po.set_Value(index, valor);
					return po.saveUpdate();
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return true;
	}

	/***
	 * Metodo para traer el ultimo valor capturado del signo vital de PESO
	 * 
	 * @param PacienteID
	 * @return
	 */
	public static BigDecimal getPesoPatient(Properties ctx, int PacienteID) {
		return getValuePatient(ctx, PacienteID, Constantes.PESO);
	}

	/***
	 * Metodo para traer el ultimo valor capturado del signo vital de TALLA
	 * 
	 * @param PacienteID
	 * @return
	 */
	public static BigDecimal getTallaPatient(Properties ctx, int PacienteID) {
		return getValuePatient(ctx, PacienteID, Constantes.TALLA);
	}

	/***
	 * Metodo para traer el ultimo valor capturado del signo vital ya sea Talla / Peso / etc.
	 * 
	 * @param PacienteID
	 * @return
	 */
	public static BigDecimal getValuePatient(Properties ctx, int PacienteID, String type) {
		BigDecimal retValue = Env.ZERO;
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("EXME_SIGNOVITALDET.EXME_PACIENTE_ID=?", PacienteID);
		map.put("upper(EXME_SignoVital.value)=?", type);
		map.put("TRIM(EXME_SIGNOVITALDET.Estatus)=?", MEXMESignoVitalDet.ESTATUS_Active);
		final List<MEXMESignoVitalDet> list = getAllFrom(ctx, map, "EXME_SignoVitalDet.fecha DESC", true, null);
		if (!list.isEmpty()) {
			retValue = list.get(0).getValorTo();
		}
		return retValue;
	}

	@Override
	public void setValor(BigDecimal valor) {
		if (valor == null) {
			valor = BigDecimal.ZERO;
		}
		super.setValor(valor);
	}

	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null) {
			ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctaPac;
	}

	public void setCtaPac(MEXMECtaPac ctaPac) {
		if (ctaPac != null) {
			setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
			this.ctaPac = ctaPac;
		}
	}

	public MEXMESignoVital getSignoVital() {
		if (signoVital == null || getEXME_SignoVital_ID() != signoVital.get_ID()) {
			signoVital = new MEXMESignoVital(getCtx(), getEXME_SignoVital_ID(), get_TrxName());
		}
		return signoVital;
	}

	public void setSignoVital(MEXMESignoVital signo) {
		if (signo != null) {
			setEXME_SignoVital_ID(signo.getEXME_SignoVital_ID());
			signoVital = signo;
		}
	}


	public MUser getUser() {
		if (user == null) {
			user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		}
		return user;
	}

	public MUOM getUOM() {
		return getSignoVital().getUom();
	}

	/**
	 * unidad de medida del Signo Vital para las conversiones
	 * 
	 * @param origWhenIsNull
	 *            - true: regresa el valor de C_UOM_ID (metrico) cuando no haya uom de conversion<br>
	 *            - false: regresa null si no hay uom de conversion
	 * @return
	 */
	public MUOM getUomTo(boolean origWhenIsNull) {
		return getSignoVital().getUomTo(origWhenIsNull);
	}

	/**
	 * Regresa el valor convertido si el usuario esta configurado que requiere conversion, de lo
	 * contrario regresa el valor original de la base de datos
	 * 
	 * @return valor del signo vital
	 */
	public BigDecimal getValorTo() {
		if (getSignoVital().getUomTo(false) != null) {
			valorTo = signoVital.getUtils().convertFromDB(super.getValor());
			if (valorTo != null) {
				return valorTo;
			}
		}
		return super.getValor();
	}

	public void setValorTo(BigDecimal valorTo) {
		this.valorTo = valorTo;
	}

	public int getEXME_RangoSV_ID() {
		return exmeRangoSVid;
	}

	public void setEXME_RangoSV_ID(int exmeRangoSVid) {
		this.exmeRangoSVid = exmeRangoSVid;
	}
	/** Regresa el nombre del signo y su unidad de medida */
	public String getSignoUomToName() {
		final StringBuilder str = new StringBuilder();
		str.append(StringUtils.capitalize(getSignoVital().getName()));
		final MUOM uom = getUomTo(true);
		if (uom != null) {
			str.append(" (").append(uom.getX12DE355().toLowerCase()).append(")");
		}
		return str.toString();
	}

//	public String getSignoName() {
//		return signoName;
//	}

//	public void setSignoName(String signoName) {
//		this.signoName = signoName;
//	}
	/**
	 * save BMI metrics
	 * 
	 * @return
	 */
	public boolean createMetrics() {
		if (getEXME_RangoSV_ID() <= 0) {
			slog.warning("createMetrics: Not EXME_RangoSV_ID for EXME_SignoVital_ID --> " + getEXME_SignoVital_ID());
			return true;
		}
		final MEXMEMetricasIMC metrica = new MEXMEMetricasIMC(getCtx(), 0, get_TrxName());
		MEXMEMetricasIMC.copyValues(this, metrica);
		metrica.setEXME_SignoVitalDet_ID(get_ID());
		metrica.setFecha_IMC(getFecha());
		metrica.setObservaciones(getSeguimiento());
		metrica.setEXME_RangoSV_ID(getEXME_RangoSV_ID()); // ?
		return metrica.save();
	}

	/** History Title : Date + Created By + Patient Age */
	public String getTitle(boolean createdDate) {
		final StringBuilder str = new StringBuilder();
		if (createdDate) {
			if (ESTATUS_Modified.equals(getEstatus().trim())) {
				str.append(Utilerias.getMsg(getCtx(), "msg.modified")).append(": ");
				str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getUpdated()));
			} else {
				str.append(Utilerias.getMsg(getCtx(), "msj.creado")).append(": ");
				str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getCreated()));
			}
		} else {
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getFecha()));
		}
		str.append(" ").append(Utilerias.getMsg(getCtx(), "msj.usuario"));
		str.append(": ").append(StringUtils.isEmpty(getUser().getDescription()) ? getUser().getName() : getUser().getDescription());
		str.append(" ").append(Utilerias.getMsg(getCtx(), "msj.edad"));
		str.append(": ").append(getEdad());
		str.append(" ").append(Utilerias.getMsg(getCtx(), "msg.anios"));
		return str.toString();
	}

	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param exmeGraficaDefaultVID
	 * @param exmeSignoVitalID
	 * @param minAge
	 * @param maxAge
	 * @param cUomID
	 * @param isMonth
	 * @param trxName
	 * @return
	 */
	public static List<XYValues> getValuesByAge(Properties ctx, int exmePacienteID, int exmeGraficaDefaultVID,
			int exmeSignoVitalID, BigDecimal minAge, BigDecimal maxAge, int cUomID, boolean isMonth, String trxName) {
		List<XYValues> lst = new ArrayList<XYValues>();
		CachedRowSet rs = null;
		try {
			rs = getGraphicRs(ctx, exmePacienteID, exmeGraficaDefaultVID, exmeSignoVitalID, -1, minAge, maxAge, trxName);
			while (rs.next()) {
				final MEXMESignoVitalDet obj = new MEXMESignoVitalDet(ctx, rs, null);
				if (isMonth) {
					obj.setEdad(new BigDecimal(obj.getEdad().doubleValue() * 12));
				}
				MUOM muom = null;
				if (exmePacienteID != 0) {
					muom = new MUOM(ctx, rs.getInt("unidad"), null);
				} else if (exmeGraficaDefaultVID != 0) {
					muom = new MUOM(ctx, obj.getC_UOM_ID(), null);
				}
				BigDecimal valor = obj.getValor();
				if (cUomID > 0 && obj.getC_UOM_ID() != cUomID
						&& !MUOM.SISTEMAMEDICION_None.equals(muom.getSistemaMedicion())) {
					MUOM uomGraph = new MUOM(ctx, cUomID, null);
					valor = MUOM
							.convertirMedida(ctx, muom.getC_UOM_ID(), obj.getValor(), uomGraph.getSistemaMedicion());
				}
				lst.add(new XYValues(obj.getEdad(), valor));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs);
		}
		return lst;
	}

	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param exmeGraficaDefaultVID
	 * @param exmeSignoVitalID
	 * @param minAge
	 * @param maxAge
	 * @param cUomID
	 * @param isMonth
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getVitalSignByAge(Properties ctx, int exmePacienteID,
			int exmeGraficaDefaultVID, int exmeSignoVitalID, BigDecimal minAge, BigDecimal maxAge, int cUomID,
			boolean isMonth, String trxName) {
		List<MEXMESignoVitalDet> lst = new ArrayList<MEXMESignoVitalDet>();
		CachedRowSet rs = null;
		try {
			rs = getGraphicRs(ctx, exmePacienteID, exmeGraficaDefaultVID, exmeSignoVitalID, -1, minAge, maxAge, trxName);

			while (rs.next()) {
				MEXMESignoVitalDet obj = new MEXMESignoVitalDet(ctx, rs, trxName);
				if (isMonth) {
					obj.setEdad(new BigDecimal(obj.getEdad().doubleValue() * 12));
				}
				if (cUomID > 0 && obj.getC_UOM_ID() != cUomID) {
					MUOM muom = new MUOM(ctx, obj.getC_UOM_ID(), null);
					if (!MUOM.SISTEMAMEDICION_None.equals(muom.getSistemaMedicion())) {
						MUOM uomGraph = new MUOM(ctx, cUomID, null);
						BigDecimal valor = MUOM.convertirMedida(ctx, muom.getC_UOM_ID(), obj.getValor(),
								uomGraph.getSistemaMedicion());
						obj.setValor(valor == null ? new BigDecimal(0) : valor);
					}
				}
				lst.add(obj);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs);
		}
		return lst;
	}

	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param exmeGraficaDefaultVID
	 * @param exmeSignoVitalIDX
	 * @param exmeSignoVitalIDY
	 * @param cUomIDX
	 * @param cUomIDY
	 * @param trxName
	 * @return
	 */
	public static List<MEXMESignoVitalDet> getValues(Properties ctx, int exmePacienteID, int exmeGraficaDefaultVID,
			int exmeSignoVitalIDX, int exmeSignoVitalIDY, int cUomIDX, int cUomIDY, String trxName) {
		List<MEXMESignoVitalDet> lst = new ArrayList<MEXMESignoVitalDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		CachedRowSet rs = null;
		try {
			rs = getGraphicRs(ctx, exmePacienteID, exmeGraficaDefaultVID, exmeSignoVitalIDX, exmeSignoVitalIDY, null,
					null, trxName);

			while (rs.next()) {
				MEXMESignoVitalDet obj = new MEXMESignoVitalDet(ctx, rs, null);
				MUOM muom = null;
				if (exmePacienteID != 0) {
					muom = new MUOM(ctx, rs.getInt("unidad"), null);
				} else if (exmeGraficaDefaultVID != 0) {
					muom = new MUOM(ctx, obj.getC_UOM_ID(), null);
				}

				MUOM uomGraph = null;
				if (obj.getEXME_SignoVital_ID() == exmeSignoVitalIDX && cUomIDX != muom.getC_UOM_ID()) {
					uomGraph = new MUOM(ctx, cUomIDX, null);
				} else if (obj.getEXME_SignoVital_ID() == exmeSignoVitalIDY && cUomIDY != muom.getC_UOM_ID()) {
					uomGraph = new MUOM(ctx, cUomIDY, null);
				}
				if (uomGraph != null && muom.getC_UOM_ID() != uomGraph.get_ID()
						&& !MUOM.SISTEMAMEDICION_None.equals(muom.getSistemaMedicion())) {
					obj.setValor(MUOM.convertirMedida(ctx, muom.getC_UOM_ID(), obj.getValor(),
							uomGraph.getSistemaMedicion()));
				}
				lst.add(obj);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs);
		}
		return lst;
	}

	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param exmeGraficaDefaultVID
	 * @param exmeSignoVitalID
	 * @param exmeSignoVital2ID
	 * @param minAge
	 * @param maxAge
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	private static CachedRowSet getGraphicRs(Properties ctx, int exmePacienteID, int exmeGraficaDefaultVID,
			int exmeSignoVitalID, int exmeSignoVital2ID, BigDecimal minAge, BigDecimal maxAge, String trxName)
			throws SQLException {
		return getGraphicRs(ctx, exmePacienteID, exmeGraficaDefaultVID, exmeSignoVitalID, exmeSignoVital2ID, minAge, maxAge, trxName, false);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param exmeGraficaDefaultVID
	 * @param exmeSignoVitalID
	 * @param exmeSignoVital2ID
	 * @param minAge
	 * @param maxAge
	 * @param trxName
	 * @param derechoHabiente
	 * @return
	 * @throws SQLException
	 */
	private static CachedRowSet getGraphicRs(Properties ctx, int exmePacienteID, int exmeGraficaDefaultVID,
			int exmeSignoVitalID, int exmeSignoVital2ID, BigDecimal minAge, BigDecimal maxAge, String trxName, boolean derechoHabiente)
			throws SQLException {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_SIGNOVITALDET.*, SV.C_UOM_ID AS unidad FROM EXME_SIGNOVITALDET ");
		sql.append("LEFT JOIN EXME_SIGNOVITAL SV ON (EXME_SIGNOVITALDET.EXME_SIGNOVITAL_ID=SV.EXME_SIGNOVITAL_ID) ");
		sql.append("WHERE TRIM(EXME_SIGNOVITALDET.ESTATUS)=? ");// #1
		sql.append("AND EXME_SIGNOVITALDET.ISACTIVE='Y' ");
		if (exmePacienteID > 0) {
			sql.append("AND EXME_SIGNOVITALDET.EXME_PACIENTE_ID=? ");// #2
		} else if (exmeGraficaDefaultVID > 0) {
			sql.append("AND EXME_SIGNOVITALDET.EXME_GraficaDefaultV_ID=? ");// #2
		}
		sql.append("AND EXME_SIGNOVITALDET.EXME_SIGNOVITAL_ID ").append(exmeSignoVital2ID > 0 ? "IN (?,?) " : "=? ");// #3
		if (minAge != null && maxAge != null) {
			sql.append("AND EXME_SIGNOVITALDET.EDAD BETWEEN ? AND ? ");// #4
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (minAge != null && maxAge != null) {
			sql.append(" ORDER BY EXME_SIGNOVITALDET.EDAD");
		} else {
			sql.append(" ORDER BY EXME_SIGNOVITALDET.FECHA, EXME_SIGNOVITALDET.EXME_SIGNOVITAL_ID");
		}

		final List<Object> params = new ArrayList<Object>();
		params.add(ESTATUS_Active);// #1 Solo Activos
		if (exmePacienteID > 0) {
			params.add(exmePacienteID);// #2
		} else if (exmeGraficaDefaultVID > 0) {
			params.add(exmeGraficaDefaultVID);// #2
		}
		params.add(exmeSignoVitalID);// #3
		if (exmeSignoVital2ID > 0) {
			params.add(exmeSignoVital2ID);// #3
		}
		if (minAge != null && maxAge != null) {
			params.add(minAge);// #4
			params.add(maxAge);// #4
		}

		PreparedStatement pstmt = null;
		CachedRowSet crs = new CachedRowSetImpl();
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			crs.populate(pstmt.executeQuery());
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(pstmt);
		}
		return crs;
	}

	/**
	 * Obtiene el detalle de los signos vitales de uan cuenta paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param EXME_CtaPac_ID
	 *            Cuenta Paciente
	 * @param fechaIni
	 *            Fecha inicial
	 * @param fechaFin
	 *            Fecha final
	 * @param ids
	 *            IDS de los signos vitales
	 * @param trxName
	 *            Nombre de la transacci�n
	 * @return
	 */
	public static String getDetalle(Properties ctx, int EXME_Paciente_ID, int EXME_CtaPac_ID, int EXME_CitaMedica_ID, Date fechaIni,
			Date fechaFin, String ids, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("select sig.NAME, det.FECHA, det.VALOR, sig.C_UOM_ID from EXME_SIGNOVITALDET det ");
		sql.append("inner join EXME_SIGNOVITAL sig on sig.EXME_SIGNOVITAL_ID = det.EXME_SIGNOVITAL_ID ");
		sql.append("WHERE trim(det.Estatus)=? ");
		if (EXME_Paciente_ID > 0) {
			sql.append("AND det.EXME_Paciente_ID=? ");
		}
		if (EXME_CtaPac_ID > 0) {
			sql.append("AND det.EXME_CTAPAC_ID=? ");
		} else if (EXME_CitaMedica_ID > 0) {
			sql.append("AND det.EXME_CitaMedica_ID=? ");
		}
		if (fechaIni != null) {
			sql.append("AND det.FECHA >=? ");
		}
		if (fechaFin != null) {
			sql.append("AND det.FECHA <=? ");
		}
		if (ids.length() > 0) {
			sql.append("AND sig.EXME_SIGNOVITAL_ID in (");
			sql.append(ids).append(") ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "det"));
		sql.append("order by sig.EXME_SIGNOVITAL_ID,det.FECHA ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder retValue = new StringBuilder();
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setString(index++, ESTATUS_Active);// Solo Activos
			if (EXME_Paciente_ID > 0) {
				pstmt.setInt(index++, EXME_Paciente_ID);
			}
			if (EXME_CtaPac_ID > 0) {
				pstmt.setInt(index++, EXME_CtaPac_ID);
			} else if (EXME_CitaMedica_ID > 0) {
				pstmt.setInt(index++, EXME_CitaMedica_ID);
			} 
			if (fechaIni != null) {
				pstmt.setTimestamp(index++, new Timestamp(fechaIni.getTime()));
			}
			if (fechaFin != null) {
				pstmt.setTimestamp(index++, new Timestamp(fechaFin.getTime()));
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.append(rs.getString("name")).append('|');
				Timestamp fecha = rs.getTimestamp("fecha");
				retValue.append(Constantes.getSdfFechaHoraAmpliaAmPm().format(fecha)).append('|');
				int uomTo = MUOMConversion.getCUOMToID(ctx, rs.getInt("C_UOM_ID"),
						MEXMEUOM.SISTEMAMEDICION_EnglishSystem);
				final BigDecimal valor;
				if (uomTo >= 0) {
					valor = VitalSignsUtils.getFromDB(ctx, uomTo, rs.getBigDecimal("valor"), true);
				} else {
					valor = rs.getBigDecimal("valor");
				}
				retValue.append(valor).append("@SEP@");
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue.toString();
	}

}
