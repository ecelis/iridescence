package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.bpm.ProgramadorTratamientos;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * 
 * @author Expert
 * 
 */
public class MEXMEMedicoEsp extends X_EXME_MedicoEsp {

	/** serialVersionUID */
	private static final long serialVersionUID = 8826891891295720461L;
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEMedicoEsp.class);

	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 */
	public MEXMEMedicoEsp(Properties ctx, int EXME_MedicoEsp_ID, String trxName) {
		super(ctx, EXME_MedicoEsp_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMedicoEsp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos las especialidades de un medico
	 * 
	 * @return
	 */
	public static MEXMEMedicoEsp get(Properties ctx, int medicoID,
			int especialidadID, String trxName) {

		MEXMEMedicoEsp esp = null;

		String sql = " SELECT * FROM EXME_MedicoEsp WHERE EXME_Medico_ID = ? "
				+ " AND EXME_Especialidad_ID = ? AND IsActive = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_MedicoEsp");

		// sql += " ORDER BY me.Name ASC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, medicoID);
			pstmt.setInt(2, especialidadID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				esp = new MEXMEMedicoEsp(ctx, rs, trxName);
			}
	
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEspecialidades", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return esp;
	}

	/**
	 * Obtiene las especialidades relacionadas a un medico en particular
	 * 
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param medicoId
	 *            Medico ID
	 * @param odontologia
	 *            Indica si se recuperan las especialidades de odontologia
	 * @param blankLine
	 * @param trxName
	 * @return Lista "KeyNamePair" de especialidades
	 * @throws Exception
	 */
	public static List<KeyNamePair> getEspecialidades(Properties ctx, int medicoId, boolean odontologia, boolean blankLine, String trxName) {

		final List<Object> params = new ArrayList<Object>();
		params.add(medicoId);

		// buscamos las especialidades del medico
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT e.EXME_Especialidad_ID, e.Name ");
		sql.append("FROM EXME_Especialidad e ");
		sql.append("INNER JOIN EXME_MedicoEsp me ON e.EXME_Especialidad_ID=me.EXME_Especialidad_ID  ");
		sql.append("WHERE me.EXME_Medico_ID=? ");
		sql.append("AND e.IsActive='Y' ");
		// excluimos odonto
		if (!odontologia) {
			sql.append("AND e.EXME_Especialidad_ID <> ? ");
			params.add(MEXMECitaMedica_MO.getEspecialidadforOdonto(ctx, null));
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoEsp.Table_Name, "me"));
		sql.append("ORDER BY me.isDefault DESC, e.Name");
		// lista con las especialidades
		final List<KeyNamePair> retValue = Query.getListKN(sql.toString(), trxName, params);
		if (blankLine) {
			retValue.add(0, new KeyNamePair(0, " "));
		}
		return retValue;
	}

	/**
	 * Obtenemos las especialidades de los medicos relacionados al asistente
	 * 
	 * @return
	 *
	public static List<LabelValueBean> getFromAsist(Properties ctx,
			int asistenteID, boolean blankLine, String trxName) {

		List<LabelValueBean> lstEspecialidades = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (asistenteID <= 0)
				return lstEspecialidades;

			sql
					.append(
							"SELECT DISTINCT EXME_Especialidad.EXME_Especialidad_ID, EXME_Especialidad.Name ")
					.append("FROM EXME_Especialidad ")
					.append(
							"INNER JOIN EXME_MedicoEsp mesp ON (EXME_Especialidad.EXME_Especialidad_ID = mesp.EXME_Especialidad_ID) ")
					.append(
							"INNER JOIN EXME_MedicoAsist tabla ON (mesp.EXME_Medico_ID = tabla.EXME_Medico_ID) ")
					.append("WHERE tabla.EXME_Asistente_ID = ? ").append(
							"AND EXME_Especialidad.IsActive = 'Y' ").append(
							MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
									MEXMEEspecialidad.Table_Name)).append(
							" ORDER BY EXME_Especialidad.Name");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, asistenteID);
			rs = pstmt.executeQuery();

			if (blankLine)
				lstEspecialidades.add(new LabelValueBean(" ", "0"));

			while (rs.next()) {
				LabelValueBean esp = new LabelValueBean(rs.getString(2), String
						.valueOf(rs.getInt(1)));
				lstEspecialidades.add(esp);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEspecialidades", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return lstEspecialidades;
	}*/

	/**
	 * Valida que la especialidad este relacionada al medico.
	 * 
	 * @param ctx
	 * @param especialidadID
	 * @param medicoID
	 * @return
	 */
	public static boolean validateEsp(Properties ctx, int especialidadID,
			int medicoID) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (especialidadID <= 0 && medicoID <= 0)
				return false;

			sql.append("SELECT EXME_MedicoEsp_ID ").append(
					"FROM EXME_MedicoEsp ").append(
					"WHERE EXME_MedicoEsp.EXME_Medico_ID = ? ").append(
					"AND EXME_MedicoEsp.EXME_Especialidad_ID = ? ").append(
					MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, medicoID);
			pstmt.setInt(2, especialidadID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEspecialidades", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return false;
	}

	/**
	 * Devolvemos el nombre de un medico. Este se conforma de Apellido1 + " " +
	 * Apellido2 + " " + Name
	 * 
	 *@param medicoId
	 *            El identificador del medico
	 *@return Un valor String con el nombre del medico
	 *@throws Exception
	 *             en caso de ocurrir un error al procesar la consulta.
	 *
	public static String nombreMedico(Properties ctx, long medicoId)
			throws Exception {
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String nombre = null;
		sql = " SELECT Apellido1||' '||Apellido2||' '||Name AS Nombre "
				+ " FROM EXME_Medico WHERE EXME_Medico_ID = ? AND isActive = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(),
				MEXMEMedico.Table_Name);

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setLong(1, medicoId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				nombre = rs.getString("Nombre");
			} else {
				throw new Exception("No existe el medico " + medicoId);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return nombre;
	}*/

	/**
	 * Consulta de medicos por especialidad
	 * 
	 * @param ctx
	 *            contexto
	 * @param especialidadID
	 *            identificador de la especialidad
	 * @param trxName
	 *            nombre de transaccion
	 * @return regresa la lista con objetos MEXMEMedicoEsp relacion medico -
	 *         especialidad todos activos
	 */
	public static List<MEXMEMedicoEsp> getForSpec(Properties ctx,
			int especialidadID, String trxName) {
		List<Integer> params = new ArrayList<Integer>();
		params.add(especialidadID);
		StringBuilder sqlEsp = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sqlEsp.append(" SELECT * FROM EXME_MedicoEsp ").append(
				" WHERE IsActive = 'Y' AND EXME_Especialidad_ID = ?  ");

		return get(ctx, params, sqlEsp, trxName);
	}

	/**
	 * Generico .- Obtenemos las especialidades de un medico
	 * 
	 * @param ctx
	 *            contexto
	 * @param params
	 *            parametros para realizar la consulta ( reemplazando "?" )
	 * @param sql
	 *            consulta para la tabla EXME_MedicoEsp
	 * @param trxName
	 *            nombre de transaccion
	 * @return regresa la lista con objetos MEXMEMedicoEsp
	 */
	public static List<MEXMEMedicoEsp> get(Properties ctx, List<?> params,
			StringBuilder sql, String trxName) {

		List<MEXMEMedicoEsp> esp = new ArrayList<MEXMEMedicoEsp>();

		if (sql == null) {
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT * FROM EXME_MedicoEsp WHERE IsActive = 'Y' ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_MedicoEsp.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				esp.add(new MEXMEMedicoEsp(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getEspecialidades", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return esp;
	}
	
	/**
	 * Disponibilidad de horario por medicos de la
	 * misma especialidad
	 * @return 
	 * 
	 * @return Listado de medicos con fecha disponible
	 */
	public static List<MEXMEMedico> getDisponibilidad(Properties ctx, int especialidadId,
			Timestamp fechaTentativa, int duracion, String trxName) {
		
		ProgramadorTratamientos prog = new ProgramadorTratamientos();
		
		return prog.getDisponibilidad(ctx, especialidadId, fechaTentativa,
				duracion, trxName);
	}
	
	/**
	 * Obtener un hash de los cuestionarios por especialidad </br>relacionadas a
	 * un medico
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docId
	 *            Medico
	 * @param trxName
	 *            Trx
	 * @return Hash de cuestionarios por especialidad
	 */
	public static HashMap<Integer, List<Integer>> getQuestionnaires(Properties ctx, int docId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  me.exme_especialidad_id, ");
		sql.append("  sf.exme_cuestionario_id ");
		sql.append("FROM ");
		sql.append("  exme_medicoesp me ");
		sql.append("  INNER JOIN exme_specialtyform sf ");
		sql.append("  ON me.exme_especialidad_id = sf.exme_especialidad_id ");
		sql.append("WHERE ");
		sql.append("  me.exme_medico_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "me"));
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				List<Integer> ids = hashMap.get(rs.getInt(1));
				if (ids == null) {
					ids = new ArrayList<Integer>();
				}
				ids.add(rs.getInt(2));
				hashMap.put(rs.getInt(1), ids);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return hashMap;
	}
	
	/**
	 * Before Save Trigger
	 * 
	 * @param newRecord
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (isDefault()) {
			final int count = DB.getSQLValue(get_TrxName(), "Select Count(EXME_Medico_id) From EXME_MedicoEsp Where EXME_Medico_ID = ? And isDefault = 'Y'", getEXME_Medico_ID());
			if(count >= 1){
				log.saveError(Utilerias.getMessage(getCtx(), null, "msj.defaultSpeciality"), "");
				return false;
			}
		}
		return true;
	}
}
