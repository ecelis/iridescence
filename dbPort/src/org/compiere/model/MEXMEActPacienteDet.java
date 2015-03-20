/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Expert
 * 
 */
public class MEXMEActPacienteDet extends X_EXME_ActPacienteDet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** bitacora */
	private static CLogger slog = CLogger.getCLogger(MEXMEActPacienteDet.class);

	/**
	 * @param ctx
	 * @param EXME_ActPacienteDet_ID
	 * @param trxName
	 */
	public MEXMEActPacienteDet(Properties ctx, int EXME_ActPacienteDet_ID, String trxName) {
		super(ctx, EXME_ActPacienteDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEActPacienteDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 * @param ctaPacID
	 * @param folio
	 * @param pCuestionarioID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEActPacienteDet> getSQLPlantilla(Properties ctx, Integer pacienteID, Integer ctaPacID, Integer folio, Integer pCuestionarioID, String trxName) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT apd.* , cp.Value ").append(" FROM EXME_Paciente p ").append(" INNER JOIN EXME_ActPaciente     ap ").append(" ON p.EXME_Paciente_ID = ap.EXME_Paciente_ID ").append(" INNER JOIN EXME_ActPacienteDet apd ").append(" ON ap.EXME_ActPaciente_ID = apd.EXME_ActPaciente_ID ").append(" INNER JOIN EXME_CampoPlantilla  cp ").append(" ON apd.EXME_Pregunta_ID = cp.EXME_Pregunta_ID ")
				.append(" WHERE P.EXME_Paciente_id = ? ").append(" AND ap.EXME_CtaPac_ID = ? ").append(" AND apd.Folio = ? ").append(" AND apd.EXME_Cuestionario_ID = ? ");

		List<Integer> params = new ArrayList<Integer>();
		params.add(pacienteID);
		params.add(ctaPacID);
		params.add(folio);
		params.add(pCuestionarioID);

		return MEXMEActPacienteDet.find(ctx, sql.toString(), params, true, trxName);
	}

	/**
	 * Ejecutar la consulta
	 * 
	 * @param ctx
	 * @param sql
	 * @param lvb
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEActPacienteDet> find(Properties ctx, String sql, List<?> params, boolean report, String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEActPacienteDet> lst = new ArrayList<MEXMEActPacienteDet>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEActPacienteDet obj = new MEXMEActPacienteDet(ctx, rs, null);
				if (report) {
					if (rs.getObject("Value") != null) {
						obj.setCampoPlantillaValue(rs.getString("Value"));
					}
				}

				lst.add(obj);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lst;
	}

	/**
	 * 
	 * @param ctx
	 * @param pacienteID
	 *            332
	 * @param ctaPacID
	 *            1000354
	 * @param folio
	 *            1000154
	 * @param pCuestionarioID
	 *            1000064
	 * @param trxName
	 * @return
	 */
	public static int getFolioMax(Properties ctx, int pacienteID, int ctaPacID, int pCuestionarioID, String trxName) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COALESCE(MAX(apd.folio),0) ").append(" FROM EXME_Paciente p ").append(" INNER JOIN EXME_ActPaciente     ap ").append(" ON p.EXME_Paciente_ID = ap.EXME_Paciente_ID ").append(" INNER JOIN EXME_ActPacienteDet apd ").append(" ON ap.EXME_ActPaciente_ID = apd.EXME_ActPaciente_ID ").append(" INNER JOIN EXME_CampoPlantilla  cp ")
				.append(" ON apd.EXME_Pregunta_ID = cp.EXME_Pregunta_ID ").append(" WHERE P.EXME_Paciente_id = ? ").append(" AND ap.EXME_CtaPac_ID = ? ").append(" AND apd.EXME_Cuestionario_ID = ? ");

		List<Integer> params = new ArrayList<Integer>();
		params.add(pacienteID);
		params.add(ctaPacID);
		params.add(pCuestionarioID);

		return DB.getSQLValue(trxName, sql.toString(), (Integer) pacienteID, (Integer) ctaPacID, (Integer) pCuestionarioID);
	}

	private String campoPlantillaValue = null;

	public void setCampoPlantillaValue(String value) {
		campoPlantillaValue = value;
	}

	public String getCampoPlantillaValue() {
		return campoPlantillaValue;
	}

	/**
	 * Obtener detalles de actividad por medio de cuestionario y actividad
	 * 
	 * @param ctx
	 *            Contexto
	 * @param actPacId
	 *            Actividad
	 * @param cuestId
	 *            Cuestionario
	 * @param multioption
	 *            Regresar o no preguntas de multi respuesta
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<MEXMEActPacienteDet> get(Properties ctx, int actPacId, int cuestId, boolean multioption, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_actpacientedet ");
		sql.append("WHERE ");
		sql.append("  exme_actpaciente_id = ? AND ");
		sql.append("  exme_cuestionario_id = ? ");
		if (multioption) {
			sql.append(" AND REF_EXME_PREGUNTA_ID is not null ");
		} else {
			sql.append(" AND REF_EXME_PREGUNTA_ID is null ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEActPacienteDet.Table_Name));
		sql.append("order by secuencia ");
		List<MEXMEActPacienteDet> list = new ArrayList<MEXMEActPacienteDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, actPacId);
			pstmt.setInt(2, cuestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEActPacienteDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public static List<MEXMEActPacienteDet> get(Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMEActPacienteDet> retorno = new ArrayList<MEXMEActPacienteDet>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEActPacienteDet mtd = new MEXMEActPacienteDet(ctx, rs, null);
				retorno.add(mtd);
			}

		}
		catch (Exception e) {
			slog.log(Level.SEVERE, "getCitasMedico", e);
			e.printStackTrace();
		}
		finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}
	
	/**
 	 * 
 	 */
 	public static List<MEXMEActPacienteDet> getTratamientosDetalle(Properties ctx, 
 			int id , String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(id);
 		String sql = " SELECT * FROM EXME_ActPacienteDet "
 		//+ " inner join exme_cuestionario c on EXME_ActPacienteDet.exme_cuestionario_id = c.exme_cuestionario_id  "
 		+ " WHERE EXME_ActPacienteDet.EXME_TRATAMIENTOS_SESION_ID = ? ";
 		return  MEXMEActPacienteDet.get(
 				ctx, sql, params,  trxName);

 	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 */
	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {

//		boolean retValue = true;
//		StringBuffer sql =
//			new StringBuffer("Select * from EXME_ActPacienteDet ")
//				.append(" left join EXME_ActPaciente on (EXME_ActPaciente.EXME_ActPaciente_ID = EXME_ActPacienteDet.EXME_ActPaciente_ID) ")
//				.append(" where EXME_ActPaciente.isActive = 'Y' and EXME_ActPacienteDet.isActive = 'Y' and EXME_ActPaciente.EXME_CtaPac_ID = ")
//				.append(ctaPacId);
//
//		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ActPacienteDet"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			rs = pstmt.executeQuery();
//
//			if (!rs.next()) {
//				retValue = false;
//			}
//
//		}
//		catch (SQLException e) {
//			throw new Exception(e.getMessage());
//		}
//		finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return retValue;
		return new Query(ctx, Table_Name, " EXME_ActPaciente.EXME_CtaPac_ID=? ", trxName)//
		.setJoins(new StringBuilder(" left join EXME_ActPaciente on (EXME_ActPaciente.EXME_ActPaciente_ID = EXME_ActPacienteDet.EXME_ActPaciente_ID) "))
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(ctaPacId)//
		.firstId() > 0
		;
	}

	/**
	 * Noelia: Metodo para obtener el siguiente
	 * folio de una nota medica para determinado clienteID
	 */
	public static int nextFolio(String trxName, int clientID) {
		String sql = "SELECT COALESCE(MAX(folio),0)+1 FROM EXME_ActPacienteDet WHERE AD_Client_ID=?";
		return DB.getSQLValue(trxName, sql, clientID);
	}

	/**
	 * @author Lorena Lama
	 * @param ctx
	 * @param folio
	 * @param trxName
	 * @return
	 */
	public static ArrayList<Integer> getCuestIdByFolio(Properties ctx, Integer actPaciente, Integer folio, String trxName) {

		ArrayList<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_ActPacienteDet ");
		boolean entro = false;
		if (actPaciente != null && actPaciente.intValue() > 0) {
			sql.append(" where exme_actpaciente_id = ").append(actPaciente.intValue());
			entro = true;
		}
		if (folio != null && folio.intValue() > 0) {
			if (entro)
				sql.append("  and folio = ").append(folio.intValue());
			else {
				sql.append("  where folio = ").append(folio.intValue());
				entro = true;
			}
		}

		if (entro)
			sql.append("  and exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
		else
			sql.append("  where exme_cuestionario_id is not null").append(" and isActive = 'Y' ");

		if (Env.getUserPatientID(ctx) < 0) // Si es un usuario paciente no agrega el accessLevel
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), I_EXME_ActPacienteDet.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new Integer(rs.getInt(1)));
			}

		}
		catch (SQLException e) {
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;

	}

	public static ArrayList<Integer> getCuestIdByFolio(Properties ctx, Integer actPaciente, Integer folio, Integer cuestID, String trxName) {

		ArrayList<Integer> retValue = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder("Select distinct exme_cuestionario_id from EXME_ActPacienteDet ");
		boolean entro = false;
		if (actPaciente != null && actPaciente.intValue() > 0) {
			sql.append(" where exme_actpaciente_id = ").append(actPaciente.intValue());
			entro = true;
		}
		if (folio != null && folio.intValue() > 0) {
			if (entro)
				sql.append("  and folio = ").append(folio.intValue());
			else {
				sql.append("  where folio = ").append(folio.intValue());
				entro = true;
			}
		}

		if (cuestID != null && cuestID.intValue() > 0) {
			if (entro)
				sql.append("  and exme_cuestionario_id = ").append(cuestID.intValue());
			else {
				sql.append("  where exme_cuestionario_id = ").append(cuestID.intValue());
				entro = true;
			}
		}

		if (entro)
			sql.append("  and exme_cuestionario_id is not null").append(" and isActive = 'Y' ");
		else
			sql.append("  where exme_cuestionario_id is not null").append(" and isActive = 'Y' ");

		if (Env.getUserPatientID(ctx) < 0) // Si es un usuario paciente no agrega el accessLevel
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), I_EXME_ActPacienteDet.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new Integer(rs.getInt(1)));
			}

		}
		catch (SQLException e) {
			return null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;

	}

	/**
	 * @author Lorena Lama
	 * @param actpaciente
	 * @return
	 */
	public static Integer getExistFolioAct(Integer actpaciente) {

		Integer retorno = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select t.folio").append("    from EXME_ActPacienteDet  t").append("    where t.exme_actpaciente_id = ?")
			.append("    and t.isActive = 'Y'").append("    order by t.created");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, actpaciente.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = new Integer(rs.getInt(1));
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.close(rs, pstmt);
		}

		return retorno;

	}

	public static Integer getExistFolioActForms(Integer encountFormsID, Integer questID) {

		Integer retorno = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("	select t.folio").append("    from EXME_ActPacienteDet  t").append("    where EXME_ENCOUNTERFORMS_ID = ?")
			.append("    and exme_cuestionario_id = ?").append("    and t.isActive = 'Y'").append("    order by t.created");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, encountFormsID.intValue());
			pstmt.setInt(2, questID.intValue());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				retorno = new Integer(rs.getInt(1));
			}

		}
		catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			DB.close(rs, pstmt);
		}

		return retorno;
	}
}
