package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEPacienteRaza extends X_EXME_Paciente_Raza {
	private static final CLogger	LOGGER				= CLogger.getCLogger(MEXMEPacienteRaza.class);

	private static final long		serialVersionUID	= 1L;

	public MEXMEPacienteRaza(Properties ctx, int EXME_Paciente_Raza_ID, String trxName) {
		super(ctx, EXME_Paciente_Raza_ID, trxName);
	}

	public MEXMEPacienteRaza(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene toda registros de razas de un paciente dado
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPacienteRaza> getMEXMEPacieneRazas(Properties ctx, int EXME_Paciente_ID, String trxName) {
		return new Query(ctx, X_EXME_Paciente_Raza.Table_Name, " EXME_Paciente_ID = ?", trxName).setParameters(EXME_Paciente_ID)
				.setOnlyActiveRecords(true).addAccessLevelSQL(true).setOrderBy("Created desc").list();
	}

	/**
	 * Obtiene todos los EXME_Razas del paciente. <br/>
	 * 
	 * Si en EXME_PacienteRaza no se encuentra ningun registro para el paciente
	 * entonces se agrega el EXME_Razas_ID del propio paciente ya que se asume
	 * que no se han actualizado los cambios a base de datos necesarios
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERazas> getPatientRaces(Properties ctx, int EXME_Paciente_ID, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  case when pac.EXME_Razas_ID = r.EXME_Razas_ID then 'Y' ELSE 'N' END ");
		sql.append("  AS isDefault, ");
		sql.append("  r.* ");
		sql.append("FROM ");
		sql.append("  exme_paciente pac ");
		sql.append("  INNER JOIN EXME_Paciente_Raza pr ");
		sql.append("  ON pac.EXME_Paciente_ID = pr.EXME_Paciente_ID ");
		sql.append("  INNER JOIN EXME_Razas r ");
		sql.append("  ON pr.EXME_Razas_ID = r.EXME_Razas_ID ");
		sql.append("WHERE ");
		sql.append("  pac.exme_paciente_id = ? ");

		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MEXMERazas> list = new ArrayList<MEXMERazas>();
		try {
			pstm = DB.prepareStatement(sql.toString(), trxName);
			pstm.setInt(1, EXME_Paciente_ID);
			rs = pstm.executeQuery();
			while (rs.next()) {
				MEXMERazas raza = new MEXMERazas(ctx, rs, trxName);
				raza.setDefault(DB.TO_BOOLEAN(rs.getString("isDefault")));
				list.add(raza);
			}
			if (list.isEmpty()) {
				MEXMEPaciente pac = new MEXMEPaciente(ctx, EXME_Paciente_ID, null);
				if (pac.getEXME_Razas_ID() > 0) {
					MEXMERazas raza = new MEXMERazas(ctx, pac.getEXME_Razas_ID(), null);
					raza.setDefault(true);
					list.add(raza);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			list = null;
		} finally {
			DB.close(rs, pstm);
		}
		return list;
	}

	/**
	 * Asigna el Set ids de EXME_Razas a un paciente. Si el paciente ya contaba
	 * con registros en EXME_Paciente_Raza, el sistema agregara o quitara para
	 * las relaciones cuadren con el Set de Ids enviados de parametro
	 * 
	 * @param ctx
	 * @param EXME_Razas_IDs
	 * @param EXME_Paciente_ID
	 * @param trxName
	 */
	public static void save(Properties ctx, List<Integer> EXME_Razas_IDs, int EXME_Paciente_ID, String trxName) {
		// Obtenemos las razas actuales
		List<MEXMEPacienteRaza> razas = getMEXMEPacieneRazas(ctx, EXME_Paciente_ID, trxName);

		// borramos los que no se encuentren dentro de las lista de ids
		// EXME_Razas_IDs
		uno : for (MEXMEPacienteRaza pacRace : razas) {
			for (Integer EXME_Razas_ID : EXME_Razas_IDs) {
				if (pacRace.getEXME_Razas_ID() == EXME_Razas_ID) {
					continue uno;
				}
			}
			if (!pacRace.delete(true, trxName)) {
				throw new MedsysException();
			}
		}

		// Agregamos los que no se encuentren dentro de las lista de ids
		// EXME_Razas_IDs
		uno : for (Integer EXME_Razas_ID : EXME_Razas_IDs) {
			if (EXME_Razas_ID > 0) {
				for (MEXMEPacienteRaza pacRace : razas) {
					if (pacRace.getEXME_Razas_ID() == EXME_Razas_ID) {
						continue uno;
					}
				}
				MEXMEPacienteRaza newMEXMEPacRaza = new MEXMEPacienteRaza(ctx, 0, null);
				newMEXMEPacRaza.setEXME_Paciente_ID(EXME_Paciente_ID);
				newMEXMEPacRaza.setEXME_Razas_ID(EXME_Razas_ID);
				if (!newMEXMEPacRaza.save(trxName)) {
					throw new MedsysException();
				}
			}
		}
	}

}
