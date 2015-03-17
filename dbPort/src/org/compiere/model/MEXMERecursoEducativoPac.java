package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;

/**
 * Modelo para la tabla EXME_RecursoEducativoPac
 * 
 * @author raul 20/08/2010<br>
 *         Modificado por: Lorena Lama (revision de codigo y comentarios) Nov 2012
 * 
 */
public class MEXMERecursoEducativoPac extends X_EXME_RecursoEducativoPac {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 2827330240590375159L;
	private static CLogger		log					= CLogger.getCLogger(MEXMERecursoEducativoPac.class);

	/**
	 * Borra los registros de recursos educativos relacionados a un paciente
	 * 
	 * @param ctx
	 * @param patientId
	 * @param trxName
	 * @return
	 */
	public static boolean deletePatientsResources(final Properties ctx, final int patientId, final String trxName) {
		final List<MEXMERecursoEducativoPac> lst = get(ctx, patientId, trxName);
		boolean retValue = true;
		for (final MEXMERecursoEducativoPac res : lst) {
			if (!res.delete(true, trxName)) {
				retValue = false;
				break;
			}
		}
		return retValue;
	}

	/**
	 * Regresa la lista de objetos MEXMERecursoEducativo que el paciente pasado
	 * como parametro tenga relacionados en la tabla EXME_RecursoEducativoPac
	 * 
	 * @author raul
	 * @param ctx
	 * @param patientId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoPac> get(final Properties ctx, final int patientId, final String trxName) {
		return get(ctx, patientId, false, trxName);
	}
	
	/**
	 * Regresa la lista de objetos MEXMERecursoEducativo que el paciente pasado
	 * como parametro tenga relacionados en la tabla EXME_RecursoEducativoPac
	 * 
	 * @author raul
	 * @param ctx
	 * @param patientId
	 * @param derechoHabiente
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoPac> get(final Properties ctx, final int patientId, boolean derechoHabiente, final String trxName) {
		List<MEXMERecursoEducativoPac> lista = new ArrayList<MEXMERecursoEducativoPac>();
		try {
			//Card #1545 ProMujer 
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" EXME_Paciente_ID=? ");
			if(derechoHabiente){
				sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
			}
			
			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(patientId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
				.list();
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

	/**
	 * Pacientes con un recurso educativo
	 * 
	 * @param ctx
	 * @param recursoEducativoID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoPac> getFromRecursoEducativo(final Properties ctx, final int recursoEducativoID, final String trxName) {
		List<MEXMERecursoEducativoPac> lista = new ArrayList<MEXMERecursoEducativoPac>();
		try {
			lista = new Query(ctx, Table_Name, " EXME_RecursoEducativo_ID=? ", trxName)//
				.setParameters(recursoEducativoID)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Created ")//
				.list();
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

	/**
	 * Esta asignado el recursos al paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param recursoEducativoID
	 *            Recurso Educativo
	 * @param pacId
	 *            Paciente
	 * @param trxName
	 * @return
	 */
	public static boolean isSet(final Properties ctx, final int recursoEducativoID, final int pacId, final String trxName) {
		boolean retValue = false;
		try {
			retValue = new Query(ctx, Table_Name, " EXME_RecursoEducativo_ID=? AND EXME_Paciente_ID=? ", trxName)//
				.setParameters(recursoEducativoID, pacId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Created ")//
				.firstId() > 0;
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
		return retValue;
	}

	/**
	 * Constructor que recibe el identificador como entero
	 * 
	 * @param ctx
	 * @param EXME_RecursoEducativoPac_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativoPac(final Properties ctx, final int EXME_RecursoEducativoPac_ID, final String trxName) {
		super(ctx, EXME_RecursoEducativoPac_ID, trxName);
	}

	/**
	 * Constructor que crea un nuevo registro de MEXMERecursoEducativoPac copiando la informacion de un recurso educativo
	 * 
	 * @param mRecursoEdicativo Recurso educativo a guardar
	 * @param EXME_Paciente_ID Id del paciente
	 * @param trxName
	 * @throws NullPointerException si el parametro <b>mRecursoEdicativo</b> es nulo
	 */
	public MEXMERecursoEducativoPac(final MEXMERecursoEducativo mRecursoEdicativo, final int EXME_Paciente_ID, final String trxName) {
		this(mRecursoEdicativo.getCtx(), 0, trxName);
		setEXME_Paciente_ID(EXME_Paciente_ID);
		setEXME_RecursoEducativo_ID(mRecursoEdicativo.getEXME_RecursoEducativo_ID());
		setName(mRecursoEdicativo.getDescription());
		setURL(mRecursoEdicativo.getURL());
	}

	/**
	 * Constructor que recibe un ResultSet
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativoPac(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Pacientes con un recurso educativo
	 * 
	 * @param ctx
	 * @param recursoEducativoID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativoPac> getFromEncounterForm(final Properties ctx, final int encounterFormId, final String trxName) {
		List<MEXMERecursoEducativoPac> lista = new ArrayList<MEXMERecursoEducativoPac>();
		try {
			lista = new Query(ctx, Table_Name, " EXME_EncounterForms_ID=? ", trxName)//
				.setParameters(encounterFormId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Created ")//
				.list();
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

}
