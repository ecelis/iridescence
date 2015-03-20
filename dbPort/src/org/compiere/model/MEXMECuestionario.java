package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.form.Questionnaire;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * @author Expert
 * 
 */
public class MEXMECuestionario extends X_EXME_Cuestionario {

	private static CLogger s_log = CLogger.getCLogger(MEXMECuestionario.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Metodos para poner orden aleatorio a los cuestionarios que no tienen orden
	 * 
	 * @param ctx
	 */
	public static void check(Properties ctx) {
		Trx trx = null;
		try {
			trx = Trx.get(Trx.createTrxName("EF"), true);

			List<Integer> ids = getCuestionariosSinSecuencia(ctx, trx.getTrxName());

			boolean ok = true;

			for (int id : ids) {
				if (!check(ctx, id, trx.getTrxName())) {
					ok = false;
					Trx.rollback(trx);
					break;
				}
			}

			if (ok) {
				Trx.commit(trx);
			}

		} catch (Exception e) {
			Trx.rollback(trx);
		} finally {
			Trx.close(trx);
		}
	}

	/**
	 * Metodos para poner orden aleatorio a los cuestionarios que no tienen orden
	 * 
	 * @param ctx
	 * @param cuestId
	 * @param trxName
	 * @return
	 */
	public static boolean check(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (dt.exme_tipopregunta_id) ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariodt dt ");
		sql.append("  INNER JOIN exme_tipopregunta t ");
		sql.append("  ON dt.exme_tipopregunta_id = t.exme_tipopregunta_id ");
		sql.append("WHERE ");
		sql.append("  dt.exme_cuestionario_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean ok = true;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				MEXMETipoPregunta tipoPregunta = new MEXMETipoPregunta(ctx, rs.getInt(1), null);
				tipoPregunta.setSeqNo(index);
				index = index + 10;
				if (!tipoPregunta.save(trxName)) {
					ok = false;
					break;
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ok;
	}

	/**
	 * Obtener todos los cuestionarios
	 * 
	 * @param ctx
	 *            Contexto
	 * @param name
	 *            true si se quiere ordenar por Name
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<MEXMECuestionario> get(Properties ctx, Boolean name, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionario ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECuestionario.Table_Name));
		if (name == true) {
			sql.append(" order by name");
		} else {
			sql.append(" order by value");
		}
		List<MEXMECuestionario> list = new ArrayList<MEXMECuestionario>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionario(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtiene los cuestionarios que cumplan con el valor a buscar
	 * 
	 * @param ctx
	 * @param value
	 *            Valor a buscar (value/name)
	 * @param orderByName
	 *            Ordenar o no por nombre
	 * @param clientId
	 * @param orgId
	 * @param trxName
	 * @return Lista de cuestionarios que cumplieron con la busqueda
	 */
	public static List<MEXMECuestionario> get(Properties ctx, String value, boolean orderByName, int clientId, int orgId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionario ");
		sql.append("WHERE ");
		sql.append("  (upper(name) LIKE ? OR ");
		sql.append("  upper(value) LIKE ?) ");
		sql.append(" AND AD_Client_ID = ? AND AD_Org_ID = ? ");

		if (orderByName) {
			sql.append(" order by name");
		} else {
			sql.append(" order by value");
		}
		List<MEXMECuestionario> list = new ArrayList<MEXMECuestionario>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, "%" + StringUtils.defaultString(value).toUpperCase() + "%");
			pstmt.setString(2, "%" + StringUtils.defaultString(value).toUpperCase() + "%");

			pstmt.setInt(3, clientId);
			pstmt.setInt(4, orgId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionario(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Listado de todos los cuestionarios del sistema
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<Integer> getAll(Properties ctx) {
		List<Integer> ids = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_cuestionario_id ");
		sql.append("FROM ");
		sql.append("  exme_cuestionario ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ids;
	}

	/**
	 * Cantidad de preguntas por categoria de un cuestionario
	 * 
	 * @param ctx
	 * @param cuestId
	 *            Cuestionario
	 * @param trxName
	 * @return Mapa con el id de la categoria y sus preguntas
	 */
	public static HashMap<Integer, Integer> getCategotyCount(Properties ctx, int cuestId, String trxName) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_tipopregunta_id, ");
		sql.append("  COUNT(exme_tipopregunta_id) ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariodt ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? AND ");
		sql.append("  isActive = 'Y' ");
		sql.append("GROUP BY ");
		sql.append("  exme_tipopregunta_id ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getInt(1), rs.getInt(2));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return map;
	}

	/**
	 * Cuestionarios de la cita
	 * 
	 * @param ctx
	 * @param citaId
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getCuestionariosByCita(Properties ctx, int citaId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (exme_cuestionario_id) ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario ");
		sql.append("WHERE ");
		sql.append("  record_id = ? AND ");
		sql.append("  ad_table_id = ? ");

		List<Integer> list = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaId);
			pstmt.setInt(2, MEXMECitaMedica.Table_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtener los cuestionarios del quirofano
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<LabelValueBean> getCuestionariosQuiro(Properties ctx, String status, String trxName) {
		StringBuilder sql = new StringBuilder();
		if (X_EXME_CuestionarioEstServ.DOCSTATUS_None.equals(status)) {
			sql.append("SELECT EXME_Cuestionario.* FROM exme_cuestionario ").append(" WHERE EXME_Cuestionario.isactive = 'Y' AND EXME_Cuestionario.isGlobal = 'Y' ").append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECuestionario.Table_Name)).append(" UNION (SELECT exme_cuestionario.* FROM exme_cuestionario  ")
					.append(" inner join exme_cuestionarioestserv estServ ON (estserv.exme_cuestionario_id = exme_cuestionario.exme_cuestionario_id ").append(" AND estserv.exme_estserv_id = ").append(Env.getEXME_EstServ_ID(ctx)).append(" )) ");
		} else {
			sql.append(" SELECT exme_cuestionario.* FROM exme_cuestionario ").append(" inner join exme_cuestionarioestserv estServ ON (estserv.exme_cuestionario_id = exme_cuestionario.exme_cuestionario_id ").append(" AND estserv.exme_estserv_id = ").append(Env.getEXME_EstServ_ID(ctx)).append(" AND estserv.docstatus = ").append(status).append(" )");
		}
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new LabelValueBean(rs.getString(COLUMNNAME_Name), String.valueOf(rs.getInt(COLUMNNAME_EXME_Cuestionario_ID))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Metodos para poner orden aleatorio a los cuestionarios que no tienen orden
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getCuestionariosSinSecuencia(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (dt.exme_cuestionario_id) ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariodt dt ");
		sql.append("  INNER JOIN exme_tipopregunta t ");
		sql.append("  ON dt.exme_tipopregunta_id = t.exme_tipopregunta_id ");
		sql.append("WHERE ");
		sql.append("  t.seqno IS NULL ");

		List<Integer> ids = new ArrayList<Integer>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ids;
	}
	
	public static MEXMECuestReport getCuestReport(Properties ctx, int cuestId) {
		MEXMECuestReport cuestReport = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" * ");
		sql.append("FROM ");
		sql.append("  exme_cuestreport ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECuestReport.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cuestReport = new MEXMECuestReport(ctx, rs, null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return cuestReport;
	}

	public static String getCustomReport(Properties ctx, int cuestId) {
		String retValue = null;

		MEXMECuestReport cuestReport = getCuestReport(ctx, cuestId);

		if (cuestReport != null) {
			retValue = cuestReport.getReport();
		}

		return retValue;
	}

	public static List<MEXMECuestionarioDt> getDet(Properties ctx, int cuestId, List<Integer> exclude, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariodt dt ");
		sql.append("  INNER JOIN exme_cuestionario c ");
		sql.append("  ON dt.exme_cuestionario_id = c.exme_cuestionario_id ");
		sql.append("  INNER JOIN exme_pregunta p ");
		sql.append("  ON dt.exme_pregunta_id = p.exme_pregunta_id ");
		sql.append("WHERE ");
		sql.append("  dt.exme_cuestionario_id = ? AND ");
		sql.append("  dt.isactive = 'Y' AND ");
		sql.append("  c.isactive = 'Y' AND ");
		sql.append("  p.isactive = 'Y' ");
		if (exclude != null && !exclude.isEmpty()) {
			sql.append(" AND dt.exme_cuestionariodt_id NOT IN (");
			sql.append(StringUtils.join(exclude, ","));
			sql.append(") ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECuestionarioDt.Table_Name, "dt"));
		sql.append(" order by dt.SECUENCIA ");
		List<MEXMECuestionarioDt> list = new ArrayList<MEXMECuestionarioDt>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionarioDt(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Obtiene el cuestionario deseado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param questionnaireId
	 *            Cuestionario ID
	 * @param isCopy
	 *            Si es una copia
	 * @return Cuestionario
	 */
	public static Questionnaire getForm(Properties ctx, int questionnaireId, boolean isCopy) {
		Questionnaire questionnaire = new Questionnaire();

		MEXMECuestionario cuestionario = new MEXMECuestionario(ctx, questionnaireId, null);

		questionnaire.setId(questionnaireId);
		questionnaire.setValue(cuestionario.getValue());
		questionnaire.setName(cuestionario.getName());
		questionnaire.setDescription(cuestionario.getDescription());
		questionnaire.setActive(cuestionario.isActive());
		questionnaire.setCustomReport(MEXMECuestionario.getCustomReport(ctx, questionnaireId));
		questionnaire.setCategories(MEXMETipoPregunta.getCategories(ctx, questionnaireId, isCopy));
		questionnaire.setType(cuestionario.getType());
		questionnaire.setOrgId(cuestionario.getAD_Org_ID());

		return questionnaire;
	}

	/**
	 * Migracion de version 4 a version 5 (No Usar ni borrar)
	 * 
	 * @param ctx
	 * @param cuestId
	 */
	public static void migrateV5(Properties ctx, int cuestId) {
		Trx trx = null;
		boolean ok = true;
		try {
			trx = Trx.get(Trx.createTrxName("MV5"), true);
			HashMap<Integer, Integer> map = getCategotyCount(ctx, cuestId, null);
			Set<Integer> set = map.keySet();

			for (Integer integer : set) {
				if (!migrateV5(ctx, cuestId, integer, map.get(integer), trx.getTrxName())) {
					ok = false;
					break;
				}
			}

			if (!ok) {
				Trx.rollback(trx);
			}

		} catch (Exception e) {
			Trx.rollback(trx);
			s_log.log(Level.SEVERE, null, e);
		} finally {
			Trx.close(trx);
		}
	}

	/**
	 * Migracion de version 4 a version 5 (No Usar ni borrar)
	 * 
	 * @param ctx
	 * @param cuestId
	 * @param catId
	 * @param cant
	 * @param trxName
	 * @return
	 */
	public static boolean migrateV5(Properties ctx, int cuestId, int catId, int cant, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		sql.append("      DT.*, oc.ISSELECTED ");
		sql.append("    FROM ");
		sql.append("      exme_cuestionariodt dt ");
		sql.append("      INNER JOIN exme_ordencuestionario oc ");
		sql.append("      ON dt.exme_cuestionariodt_id = oc.exme_cuestionariodt_id ");
		sql.append("    WHERE ");
		sql.append("      dt.exme_cuestionario_id = ? AND ");
		sql.append("      dt.exme_tipopregunta_id = ? ");
		sql.append("    ORDER BY ");
		sql.append("      oc.seqno ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean ok = true;
		
		TreeSet<Integer> ints =  new TreeSet<Integer>();

		try {
			MEXMETipoPregunta tipoPregunta = new MEXMETipoPregunta(ctx, catId, null);
			tipoPregunta.setEXME_Cuestionario_ID(cuestId);
			tipoPregunta.setNColumns(9);
			tipoPregunta.setNRows((int) Math.ceil(cant / 2.0));

			int x = 1;
			int y = 1;
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, catId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boolean isSelected = "Y".equals(rs.getString("ISSELECTED")) ? true : false;
				MEXMECuestionarioDt dt = new MEXMECuestionarioDt(ctx, rs, null);
				MEXMEPregunta pregunta = new MEXMEPregunta(ctx, dt.getEXME_Pregunta_ID(), null);
				if (isSelected) {
					if (!ints.contains(pregunta.getEXME_Pregunta_ID())) {
						ints.add(pregunta.getEXME_Pregunta_ID());
						if (dt.isActive()) {
							pregunta.setEXME_TipoPregunta_ID(catId);
							pregunta.setX(x == 1 ? 1 : 6);
							pregunta.setY(y);
							pregunta.setNColumns(4);
							pregunta.setNRows(1);

							if (x == 2) {
								x = 1;
								y++;
							} else {
								x++;
							}
						} else {
							pregunta.setIsActive(false);
						}

						if (!pregunta.save(trxName)) {
							ok = false;
							break;
						}
					}
				} else {
					if (!pregunta.save(trxName)) {
						ok = false;
						break;
					}
				}
			}

			if (ok) {
				if (!tipoPregunta.save(trxName)) {
					ok = false;
				}
			}
		} catch (Exception e) {
			ok = false;
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ok;
	}

	/**
	 * @param ctx
	 * @param EXME_Cuestionario_ID
	 * @param trxName
	 */
	public MEXMECuestionario(Properties ctx, int EXME_Cuestionario_ID, String trxName) {
		super(ctx, EXME_Cuestionario_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECuestionario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public String getCustomReport() {
		return getCustomReport(getCtx(), getEXME_Cuestionario_ID());
	}

	/**
	 * Clase para demostrar que se puede explotar la info de cuestionarios
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class Usage {
		private String type;
		private String name;
		private Timestamp date;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Timestamp getDate() {
			return date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}
	}

	/**
	 * Metodo para demostrar que se puede explotar la info de cuestionarios
	 * 
	 * @param ctx
	 * @param cuestId
	 * @param pregId
	 * @param opId
	 * @param checked
	 * @param trxName
	 * @return
	 */
	public static List<Usage> getUsage(Properties ctx, int cuestId, int pregId, int opId, boolean checked, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  created, ");
		sql.append("  ad_table_id, ");
		sql.append("  record_id ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? AND ");
		sql.append("  exme_pregunta_id = ? AND ");
		if (opId > 0) {
			sql.append("  exme_pregunta_lista_id = ? ");
		} else {
			sql.append("  to_char(textbinary) = ? ");
		}
		List<Usage> list = new ArrayList<Usage>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			pstmt.setInt(2, pregId);
			if (opId > 0) {
				pstmt.setInt(3, opId);
			} else {
				pstmt.setString(3, checked ? "Y" : "N");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Usage usage = new Usage();
				usage.setDate(rs.getTimestamp(1));
				int tableId = rs.getInt(2);
				usage.setType(new MTable(ctx, tableId, null).getName());
				if (MEXMECtaCuest.Table_ID == tableId) {
					MEXMECtaCuest ctaCuest = new MEXMECtaCuest(ctx, rs.getInt(3), null);
					MEXMEPaciente pac = new MEXMEPaciente(ctx, ctaCuest.getEXME_CtaPac().getEXME_Paciente_ID(), null);
					usage.setName(pac.getNombre_Pac());
				} else if (MEXMEEncounterForms.Table_ID == tableId) {
					MEXMEEncounterForms encounterForms = new MEXMEEncounterForms(ctx, rs.getInt(3), null);
					MEXMEPaciente pac = new MEXMEPaciente(ctx, encounterForms.getEXME_CtaPac().getEXME_Paciente_ID(), null);
					usage.setName(pac.getNombre_Pac());
				} else if (MEXMECitaMedica.Table_ID == tableId) {
					MEXMECitaMedica citaMedica = new MEXMECitaMedica(ctx, rs.getInt(3), null);
					usage.setName(citaMedica.getPaciente().getNombre_Pac());
				} else if (MEXMEOrderSet.Table_ID == tableId) {
					MEXMEOrderSet orderSet = new MEXMEOrderSet(ctx, rs.getInt(3), null);
					usage.setName(orderSet.getName());
				} else {
					usage.setName(StringUtils.EMPTY);
				}
				list.add(usage);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Representación de un evento de cuestionarios
	 * 
	 * @author odelarosa
	 *
	 */
	public static class QEvent {
		private int tableId;
		private int recordId;
		private int id;
		private String name;
		private Timestamp date;
		private int userId;
		private String userName;
		private String headLine;

		public String getHeadLine() {
			return headLine;
		}

		public void setHeadLine(String headLine) {
			this.headLine = headLine;
		}

		public int getTableId() {
			return tableId;
		}

		public void setTableId(int tableId) {
			this.tableId = tableId;
		}

		public int getRecordId() {
			return recordId;
		}

		public void setRecordId(int recordId) {
			this.recordId = recordId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Timestamp getDate() {
			return date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
	}
}
