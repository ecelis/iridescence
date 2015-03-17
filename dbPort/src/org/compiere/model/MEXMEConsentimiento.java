package org.compiere.model;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class MEXMEConsentimiento extends X_EXME_Consentimiento {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -207853435528886003L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEConsentimiento.class);

	public MEXMEConsentimiento(Properties ctx, int EXME_Consentimiento_ID, String trxName) {
		super(ctx, EXME_Consentimiento_ID, trxName);
	}

	public MEXMEConsentimiento(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * FIXME> no utilizado
	 * @param ctx
	 * @param archivo
	 * @param ctaPacID
	 * @param pacienteID
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated
	 *
	public static int insertFile(Properties ctx, File archivo, int ctaPacID, int pacienteID, String trxName) throws Exception {
		final MEXMEConsentimiento reporte = new MEXMEConsentimiento(ctx, 0, trxName);
		reporte.setName(archivo.getName());
		reporte.setEXME_CtaPac_ID(ctaPacID);
		reporte.setEXME_Paciente_ID(pacienteID);
		if (!reporte.save()) {
			throw new Exception("error.guardarReporte");
		}
		final MAttachment att = reporte.createAttachment();
		att.setTitle(archivo.getName());
		att.addEntry(archivo);
		if (!att.save(trxName)) {
			throw new Exception("error.guardarReporte");
		}
		return reporte.getEXME_Consentimiento_ID();
	}*/

	/**
	 * 
	 * @param ctx
	 *            Contexto
	 * @param pacienteID
	 *            id dle paciente
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMEConsentimiento> getHistoria(Properties ctx, int pacienteID, int ctapacID, boolean proMujer,String trxName) {
		List<MEXMEConsentimiento> lista = new ArrayList<MEXMEConsentimiento>();
		try {
			final StringBuilder sql = new StringBuilder();
			final List<Object> params = new ArrayList<Object>();
			
			params.add(pacienteID);
			sql.append(" EXME_PACIENTE_ID=? ");
			if (ctapacID > 0) {
				sql.append(" AND EXME_CTAPAC_ID=? ");
				params.add(ctapacID);
			}
			//Card #1545 ProMujer 
			if(proMujer){
				sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
			}
			
			lista = new Query(ctx, Table_Name, sql.toString(), trxName) //
					.setParameters(params)//
					.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !proMujer)//
					.setOrderBy(" CREATED DESC ")//
					.setOnlyActiveRecords(true)//
					.list();

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}

}
