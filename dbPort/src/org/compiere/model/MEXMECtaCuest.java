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

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 * Modelo de cuestionarios ligados con una cuenta paciente
 * 
 * @author lherrera
 * 
 */
public class MEXMECtaCuest extends X_EXME_CtaCuest {
	private static CLogger log = CLogger.getCLogger(MEXMECtaCuest.class);
	private static final long serialVersionUID = 1094421521587703336L;

	/**
	 * @param ctx
	 * @param EXME_CtaCuest_ID
	 * @param trxName
	 */
	public MEXMECtaCuest(final Properties ctx, final int EXME_CtaCuest_ID, final String trxName) {
		super(ctx, EXME_CtaCuest_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaCuest(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtener todos los cuestionarios relacionados con la cuenta paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<MEXMECtaCuest> get(final Properties ctx, final int ctaPacId, final Date start, final Date end, final String trxName) {
		List<MEXMECtaCuest> list = new ArrayList<MEXMECtaCuest>();
		try {
			list = new Query(ctx, Table_Name, " EXME_CtaPac_ID=? AND Created BETWEEN ? AND ? ", trxName)//
					.setOnlyActiveRecords(true)//
					.setParameters(ctaPacId, TimeUtil.getInitialRange(ctx, start), TimeUtil.getFinalRange(ctx, end))//
					.addAccessLevelSQL(true)//
					.setOrderBy(" Created DESC")//
					.list();

		} catch (final Exception e) {
			log.log(Level.SEVERE, null, e);
		}
		return list;
	}

	public static List<MEXMECtaCuest> getCuest(final Properties ctx, final int ctaPacId, final boolean isPhysician, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct EXME_CtaCuest.*, cue.name as cuestionario FROM EXME_CtaCuest ");
		sql.append(" INNER JOIN  Exme_Respuestacuestionario Resp On EXME_CtaCuest.Exme_Ctacuest_Id=Resp.Record_Id ");
		sql.append(" inner join exme_cuestionario cue on exme_ctacuest.exme_cuestionario_id=cue.exme_cuestionario_id ");
		sql.append("WHERE EXME_CtaPac_ID=? AND Resp.IsPhysician=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_CtaCuest.Table_Name));
		sql.append(" ORDER BY EXME_CtaCuest.Created DESC");
		final List<MEXMECtaCuest> list = new ArrayList<MEXMECtaCuest>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			pstmt.setString(2, DB.TO_STRING(isPhysician));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMECtaCuest cuest = new MEXMECtaCuest(ctx, rs, trxName);
				cuest.setDescription(rs.getString("cuestionario"));
				list.add(cuest);
			}

		} catch (final Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Metodo para obtener las cuentas del paciente relacionadas a un cuestionario
	 * 
	 * @author gderreza
	 * @param isMedico
	 *            Si el cuestionario fue contestado por medico
	 **/
	public static List<BasicoTresProps> getCtasCues(final Properties ctx, final boolean isMedico, final int EXME_Patient_ID, final String trxName) {

		final List<BasicoTresProps> lista = new ArrayList<BasicoTresProps>();
		final StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT DISTINCT cta.exme_ctapac_id, cta.documentno, med.nombre_med ");
		sql.append(" FROM exme_ctapac cta ");
		sql.append(" INNER JOIN EXME_CtaCuest  on cta.exme_ctapac_id=EXME_CTACUEST.exme_ctapac_id ");
		sql.append(" INNER JOIN exme_medico med on cta.exme_medico_id=med.exme_medico_id ");
		sql.append(" INNER JOIN Exme_Respuestacuestionario Resp On EXME_CtaCuest.Exme_Ctacuest_Id=Resp.Record_Id ");
		sql.append("where cta.exme_paciente_id=? AND Resp.IsPhysician=? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", I_EXME_CtaCuest.Table_Name));
		sql.append(" ORDER BY cta.documentNo, med.nombre_med ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			pstmt.setString(2, DB.TO_STRING(isMedico));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BasicoTresProps btp = new BasicoTresProps();
				btp.setId(rs.getLong("exme_ctapac_id"));
				btp.setValue(rs.getString("documentno"));
				btp.setNombre(rs.getString("nombre_med"));
				lista.add(btp);
			}

		} catch (final SQLException e) {
			log.log(Level.SEVERE, "getCtasCues: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Búsqueda de contenido en formas
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pacId
	 *            Paciente dueño de las formas
	 * @param searchString
	 *            String a buscar
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de resultados que contiene el texto
	 */
	public static List<MEXMECuestionario.QEvent> search(Properties ctx, int pacId, String searchString, String trxName) {
		List<MEXMECuestionario.QEvent> lst = new ArrayList<MEXMECuestionario.QEvent>();

		searchString = StringUtils.join(StringUtils.split(searchString, Constantes.SPACE), " | ");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  u.description ");
		sql.append("  AS user, ");
		sql.append("  cc.created, ");
		sql.append("  cp.documentno ");
		sql.append("  AS ctapac, ");
		sql.append("  cc.EXME_CtaCuest_ID, ");
		sql.append("  cc.EXME_Cuestionario_id, ");
		sql.append("  ts_headline(rc.textbinary, ");
		sql.append("  to_tsquery(?)) as hl, ");
		sql.append("  cu.name, ");
		sql.append("  u.ad_user_id ");
		sql.append("FROM ");
		sql.append("  exme_respuestacuestionario rc ");
		sql.append("  INNER JOIN EXME_CtaCuest cc ");
		sql.append("  ON rc.record_id = cc.EXME_CtaCuest_ID ");
		sql.append("  INNER JOIN EXME_CtaPac cp ");
		sql.append("  ON cc.EXME_CtaPac_id = cp.EXME_CtaPac_ID ");
		sql.append("  INNER JOIN EXME_cuestionario cu ");
		sql.append("  ON rc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("  INNER JOIN AD_User u ");
		sql.append("  ON cc.createdby = u.ad_user_id ");
		sql.append("WHERE ");
		sql.append("  cp.exme_paciente_id = ? AND ");
		sql.append("  rc.ad_table_id = ? AND ");
		sql.append("  to_tsvector(rc.textbinary) @@ to_tsquery(?) ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, searchString);
			pstmt.setInt(2, pacId);
			pstmt.setInt(3, MEXMECtaCuest.Table_ID);
			pstmt.setString(4, searchString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setDate(rs.getTimestamp("created"));
				event.setHeadLine(rs.getString("hl"));
				event.setId(rs.getInt("EXME_Cuestionario_id"));
				event.setName(rs.getString("name"));
				event.setRecordId(rs.getInt("EXME_CtaCuest_ID"));
				event.setTableId(Table_ID);
				event.setUserName(rs.getString("user"));
				event.setUserId(rs.getInt("ad_user_id"));

				lst.add(event);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Forma padre (de la que se copió)
	 * 
	 * @return Padre o null si no tiene
	 */
	public MEXMECtaCuest getParent() {
		MEXMECtaCuest ctaCuest = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_ctacuest ");
		sql.append("WHERE ");
		sql.append("  exme_ctacuest_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getParent_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ctaCuest = new MEXMECtaCuest(getCtx(), rs, null);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ctaCuest;
	}

	/**
	 * Bean que representa un resultado de busqueda
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class SearchResult {
		private String user;
		private Timestamp created;
		private String ctaPac;
		private int recordId;
		private int cuestId;
		private String cuestName;
		private String headline;

		public String getCuestName() {
			return cuestName;
		}

		public void setCuestName(String cuestName) {
			this.cuestName = cuestName;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public Timestamp getCreated() {
			return created;
		}

		public void setCreated(Timestamp created) {
			this.created = created;
		}

		public String getCtaPac() {
			return ctaPac;
		}

		public void setCtaPac(String ctaPac) {
			this.ctaPac = ctaPac;
		}

		public int getRecordId() {
			return recordId;
		}

		public void setRecordId(int recordId) {
			this.recordId = recordId;
		}

		public int getCuestId() {
			return cuestId;
		}

		public void setCuestId(int cuestId) {
			this.cuestId = cuestId;
		}

		public String getHeadline() {
			return headline;
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}
	}

	/**
	 * Obtiene formas de un paciente por nombre o value
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pacId
	 *            Paciente a buscar
	 * @param name
	 *            Nombre o value a buscar
	 * @param trxName
	 *            Trx
	 * @return Listado de formas del paciente
	 */
	public static List<MEXMECuestionario.QEvent> getFromPatient(Properties ctx, int pacId, String name, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  cu.EXME_cuestionario_id, ");
		sql.append("  cc.EXME_ctaCuest_id, ");
		sql.append("  cc.created, ");
		sql.append("  cu.name as cuest, ");
		sql.append("  u.ad_user_id, ");
		sql.append("  u.name as userName ");
		sql.append("FROM ");
		sql.append("  EXME_ctacuest cc ");
		sql.append("  INNER JOIN exme_cuestionario cu ");
		sql.append("  ON cc.exme_cuestionario_id = cu.exme_cuestionario_id ");
		sql.append("  INNER JOIN EXME_Ctapac cta ");
		sql.append("  ON cc.EXME_Ctapac_id = cta.exme_ctapac_id ");
		sql.append("  INNER JOIN ad_user u ");
		sql.append("  ON cc.createdby = u.ad_user_id ");
		sql.append("WHERE ");
		sql.append("  cta.EXME_Paciente_id = ? AND ");
		sql.append("  (upper(cu.name) LIKE ? OR ");
		sql.append("  upper(cu.value) LIKE ?) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "cc"));
		sql.append(" ORDER BY ");
		sql.append("  cc.created desc ");

		List<MEXMECuestionario.QEvent> list = new ArrayList<MEXMECuestionario.QEvent>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			pstmt.setString(2, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
			pstmt.setString(3, "%" + StringUtils.defaultIfEmpty(name, StringUtils.EMPTY).toUpperCase() + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECuestionario.QEvent event = new MEXMECuestionario.QEvent();
				event.setDate(rs.getTimestamp("created"));
				event.setId(rs.getInt("exme_cuestionario_id"));
				event.setRecordId(rs.getInt("exme_ctacuest_id"));
				event.setName(rs.getString("cuest"));
				event.setUserName(rs.getString("userName"));
				event.setUserId(rs.getInt("ad_user_id"));
				event.setTableId(Table_ID);
				list.add(event);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

}