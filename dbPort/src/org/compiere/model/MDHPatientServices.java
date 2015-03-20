/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Relacion de paquetes / servicios y paciente (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class MDHPatientServices extends X_DH_PatientServices {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		sLog				= CLogger.getCLogger(MDHPatientServices.class);

	/**
	 * @param ctx
	 * @param DH_PatientServices_ID
	 * @param trxName
	 */
	public MDHPatientServices(final Properties ctx, final int DH_PatientServices_ID, final String trxName) {
		super(ctx, DH_PatientServices_ID, trxName);
		if(is_new()){
			setQtyEntered(1);
			setValidFrom(Env.getCurrentDate());
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHPatientServices(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Registra la asignacion de un servicio / paquete pagado
	 * 
	 * @param ctx Contexto
	 * @param patientId Id del paciente
	 * @param line linea del servicio / paquete pagado
	 * @param trxName nombre de la transaccion
	 * @return listado de errores
	 */
	public static ErrorList saveFrom(final Properties ctx, final int patientId, final MDHPrePaymentLine line, final String trxName) {
		final ErrorList errors = new ErrorList();
		try {
			if (line == null) {
				errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "msg.jheader.mandatory"));
			} else if (patientId <= 0) {
				errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "odontologia.msj.Paciente"));
			} else if (line.getEXME_PaqBase_Version_ID() <= 0 && line.getM_Product_ID() <= 0) {
				errors.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "msj.error.productoRequerido"));
			} else if (line.isPaid()) {
				final MDHPatientServices patServ = new MDHPatientServices(ctx, 0, trxName);
				patServ.setEXME_Paciente_ID(patientId);
				patServ.setEXME_PaqBase_Version_ID(line.getEXME_PaqBase_Version_ID());
				patServ.setDH_PrePayment_ID(line.getDH_PrePayment_ID());
				patServ.setM_Product_ID(line.getM_Product_ID());
				patServ.setQtyEntered(line.getQtyEntered());
				// setValidFrom(ValidFrom);//TODO
				// setValidTo(ValidFrom);
				if (!patServ.save()) {
					throw new MedsysException();
				}
			}

		} catch (final Exception e) {
			sLog.log(Level.SEVERE, "savePayment", e);
			errors.add(Error.EXCEPTION_ERROR, e.getLocalizedMessage());
		}
		return errors;
	}

	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		if (newRecord && success) {
			if (!MDHBalanceServices.createBalance(this)) {
				log.saveError(null, Utilerias.getAppMsg(getCtx(), "error.censoHosp.save", MedsysException.getMessageFromLogger()));
				return false;
			}
		}
		return super.afterSave(newRecord, success);
	}
	
	/**
	 * Valida si la versión del paquete ya fue asignada a un o más pacientes
	 * @param ctx
	 * @param versionId
	 * @param trxName
	 * @return
	 */
	public static boolean isPackageUsed(final Properties ctx, final int paqBaseId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(DISTINCT l.EXME_Paciente_ID) ");
		sql.append(" FROM DH_PatientServices l ");
		sql.append(" INNER JOIN EXME_PaqBase_Version v ON l.EXME_PaqBase_Version_ID=v.EXME_PaqBase_Version_ID ");
		sql.append(" WHERE l.isActive='Y' ");
		sql.append(" AND v.EXME_PaqBase_ID =? ");
		
		int totalPatients = DB.getSQLValue(trxName, sql.toString(), paqBaseId);
		return totalPatients > 0;
	}
	
	/**
	 * Valida si la versión del paquete ya fue asignada a un o más pacientes
	 * @param ctx
	 * @param versionId
	 * @param trxName
	 * @return
	 */
	public static boolean isPackVersionUsed(final Properties ctx, final int versionId, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(DISTINCT l.EXME_Paciente_ID) ");
		sql.append(" FROM DH_PatientServices l ");
		sql.append(" WHERE l.isActive='Y' ");
		sql.append(" AND l.EXME_PaqBase_Version_ID =? ");
		
		int totalPatients = DB.getSQLValue(trxName, sql.toString(), versionId);
		return totalPatients > 0;
	}
	
	
	public static int getTotalOfPackages(final Properties ctx, final int patientId, final boolean universalComp, final String trxName){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) FROM DH_PatientServices ds ");
		sql.append(" INNER JOIN EXME_PaqBase_Version pv ON ds.EXME_PaqBase_Version_ID=pv.EXME_PaqBase_Version_ID ");
		sql.append(" INNER JOIN EXME_PaqBase p ON p.EXME_PaqBase_ID=pv.EXME_PaqBase_ID AND p.IsUniversalComp=? ");
		sql.append(" WHERE ds.EXME_Paciente_ID=? ");
		int tot = DB.getSQLValue(null, sql.toString(),  DB.TO_STRING(universalComp), patientId);
		return tot;
	}

}
