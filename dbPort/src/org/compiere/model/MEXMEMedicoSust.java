/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 * Relacion Medico - Sustituto
 * 
 * @author Lama
 * 
 */
public class MEXMEMedicoSust extends X_EXME_Medico_Sust {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param EXME_Medico_Sust_ID
	 * @param trxName
	 */
	public MEXMEMedicoSust(Properties ctx, int EXME_Medico_Sust_ID, String trxName) {
		super(ctx, EXME_Medico_Sust_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMedicoSust(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static CLogger	s_log	= CLogger.getCLogger(MEXMEMedicoSust.class);

	/**
	 * After Save
	 * 
	 * @param newRecord new
	 * @param success success
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		return validate(true);
	}

	public boolean validate(boolean beforeSave) {
		int count = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		if (beforeSave && getEXME_Medico_Sust_ID() > 0) {
			// Validamos el m�dico no cuente con medico sustituto (validacion temporal) //Mantis #0004154 Error al modificar un registro
			sql.append("SELECT * FROM EXME_Medico_Sust WHERE EXME_Medico_ID = ? AND  Substitute_ID = ? AND EXME_Medico_Sust_ID <> ? ");
			count = DB.getSQLValue(get_TrxName(), sql.toString(), getEXME_Medico_ID(), getSubstitute_ID(), getEXME_Medico_Sust_ID());
			if (count > 0) {
				log.saveError("error.repetido", Msg.getElement(getCtx(), "EXME_Medico_Sust_ID"));
				return false;
			}
		}
		// Validamos que ambos m�dicos no sean iguales
		if (getEXME_Medico_ID() == getSubstitute_ID()) {
			log.saveError("error.same.medico", Msg.getElement(getCtx(), "EXME_Medico_Sust_ID"));
			return false;
		}
		/* //Validamos que ambos m�dicos tengan el mismo asistente // ya no aplica
		 * sql = new StringBuilder("SELECT * FROM (SELECT EXME_Asistente_ID, count(*) AS count FROM ")
		 * .append("(SELECT EXME_Asistente_ID FROM EXME_MedicoAsist ")
		 * .append("WHERE EXME_Medico_ID IN (?,?) ) GROUP BY EXME_Asistente_ID ) WHERE count > 1 ");
		 * count = DB.getSQLValue(get_TrxName(),sql.toString(),getEXME_Medico_ID(),getSubstitute_ID());
		 * if(count<=0){
		 * log.saveError("SaveError", Msg.getElement(getCtx(), "EXME_Medico_Sust_ID ") + "No tienen mismo asistente");
		 * return false;
		 * }
		 * 
		 * //Validamos que ambos m�dicos tengan la misms especialidad
		 * sql = new StringBuilder("SELECT * FROM (SELECT EXME_Especialidad_ID, count(*) AS count FROM ")
		 * .append("(SELECT EXME_Especialidad_ID FROM EXME_MedicoEsp ")
		 * .append("WHERE EXME_Medico_ID IN (?,?) ) GROUP BY EXME_Especialidad_ID ) WHERE count > 1 ");
		 * count = DB.getSQLValue(get_TrxName(),sql.toString(),getEXME_Medico_ID(),getSubstitute_ID());
		 * if(count<=0){
		 * log.saveError("SaveError", Msg.getElement(getCtx(), "EXME_Medico_Sust_ID ") + "No tienen la misma especialidad");
		 * return false;
		 * } */
		return true;
	}

	/**
	 * Obtiente la relacion medico - sustituto a partir del medico y/o sustituto
	 * 
	 * @param ctx
	 * @param medico_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEMedicoSust> get(Properties ctx, int medico_ID, int substitute_ID, String trxName) {
		List<Object> param = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		if (medico_ID > 0) {
			sql.append(" AND EXME_Medico_ID=? ");
			param.add(medico_ID);
		}
		if (substitute_ID > 0) {
			sql.append(" AND Substitute_ID=? ");
			param.add(substitute_ID);
		}
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(param)//
			.list();

	}

	private MEXMEMedico	medico;

	private MEXMEMedico	substitute;

	public MEXMEMedico getMedico() {
		if (medico == null) {
			medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
		}
		return medico;
	}

	public MEXMEMedico getSubstitute() {
		if (substitute == null) {
			substitute = new MEXMEMedico(getCtx(), getSubstitute_ID(), get_TrxName());
		}
		return substitute;
	}

	public MEXMEMedico getMedico(boolean isDoctor) {
		if (isDoctor) {
			return getMedico();
		} else {

			return getSubstitute();
		}
	}

	/**
	 * Obtiente la relacion medico - sustituto a partir del medico
	 * 
	 * @param ctx
	 * @param substitute_ID
	 * @param emptyLine
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getPhyisicians(Properties ctx, int substitute_ID, boolean emptyLine, String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<>();
		sql.append("SELECT m.EXME_Medico_ID, m.Nombre_Med ");
		sql.append("FROM EXME_Medico_Sust  ms ");
		sql.append("INNER JOIN EXME_Medico m on (m.EXME_Medico_ID=ms.EXME_Medico_ID) ");
		sql.append("WHERE ms.isActive = 'Y' ");
		if (substitute_ID > 0) {
			sql.append("AND ms.Substitute_ID=? ");
			params.add(substitute_ID);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoSust.Table_Name, "ms"));
		sql.append(" ORDER BY m.Nombre_Med ");

		final List<KeyNamePair> retLst = Query.getListKN(sql.toString(), trxName, params);
		if (emptyLine) {
			retLst.add(0, new KeyNamePair(0, ""));
		}
		return retLst;
	}

	/**
	 * Obtiente la relacion medico - sustituto a partir del medico
	 * 
	 * @param ctx
	 * @param substitute_ID
	 * @param emptyLine
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getMedsSustitutos(Properties ctx, int medID, String trxName) {

		List<Integer> retLst = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT substitute_id FROM EXME_Medico_Sust WHERE exme_medico_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoSust.Table_Name));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, medID);

			rs = psmt.executeQuery();

			while (rs.next()) {
				retLst.add(rs.getInt(1));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}

		return retLst;
	}

	/**
	 * Obtiente la relacion medico - sustituto a partir del medico
	 * 
	 * @param ctx
	 * @param substitute_ID
	 * @param emptyLine
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getMedicos(Properties ctx, int substituteId, String trxName) {
		List<Integer> retLst = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT exme_medico_id FROM EXME_Medico_Sust WHERE substitute_id = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoSust.Table_Name));
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, substituteId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				retLst.add(rs.getInt(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}
		return retLst;
	}

}
