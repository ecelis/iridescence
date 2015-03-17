package org.compiere.model;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class MEXMEPatientDocument extends X_EXME_PatientDocument {

	private static final long	serialVersionUID	= 387412059879305269L;

	/**	Static Logger				*/
	private static CLogger		SLOG = CLogger.getCLogger (MEXMEPatientDocument.class);
	
	public MEXMEPatientDocument(Properties ctx, int EXME_PatientDocument_ID, String trxName) {
		super(ctx, EXME_PatientDocument_ID, trxName);
	}

	public MEXMEPatientDocument(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEPatientDocument> getFromPatient(final Properties ctx, final int EXME_Paciente_ID, final String type, final String trxName) {
		final List<Object> params = new ArrayList<Object>();
		if (EXME_Paciente_ID > 0) {
			params.add(EXME_Paciente_ID);
		}
		return get(ctx, type, "EXME_Paciente_ID=?", params, trxName);
	}

	public static List<MEXMEPatientDocument> getFromCtaPac(final Properties ctx, final int EXME_CtaPac_ID, final String type, final String trxName) {
		final List<Object> params = new ArrayList<Object>();
		if (EXME_CtaPac_ID > 0) {
			params.add(EXME_CtaPac_ID);
		}
		return get(ctx, type, "EXME_CtaPac_ID=?", params, trxName);
	}

	public static List<MEXMEPatientDocument> get(final Properties ctx, final String type, final String where, final List<Object> params,
		final String trxName) {
		final StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotBlank(where)) {
			sql.append(where);
		}
		if (StringUtils.isNotBlank(type)) {
			params.add(type);
			if (sql.length() > 0) {
				sql.append(" AND ");
			}
			sql.append("TRIM(EXME_PatientDocument.Type)=?");
		}
		return getQuery(ctx, type, where, params, trxName).list();
	}
	
	public static Query getQuery(final Properties ctx, final String type, final String where, final List<Object> params, final String trxName) {
		final StringBuilder sql = new StringBuilder();
		if (StringUtils.isNotBlank(where)) {
			sql.append(where);
		}
		if (StringUtils.isNotBlank(type)) {
			params.add(type);
			if (sql.length() > 0) {
				sql.append(" AND ");
			}
			sql.append("TRIM(EXME_PatientDocument.Type)=?");
		}
		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy("EXME_PatientDocument.Created DESC ");
	}

	public static MEXMEPatientDocument create(final PO from, final String type, final File file) {
		final MEXMEPatientDocument pDoc = MEXMEPatientDocument.create(from.getCtx(), type, from.get_TrxName(), file);
		pDoc.setEXME_Paciente_ID(getInteger(from.get_Value(COLUMNNAME_EXME_Paciente_ID)));
		pDoc.setEXME_CtaPac_ID(getInteger(from.get_Value(COLUMNNAME_EXME_CtaPac_ID)));
		return pDoc;
	}

	public static MEXMEPatientDocument create(final Properties ctx, final String type, final String trxName, final File file) {
		final MEXMEPatientDocument pDoc = new MEXMEPatientDocument(ctx, 0, trxName);
		pDoc.setType(type);
		pDoc.setRequestDate(Env.getCurrentDate());
		pDoc.setFile(file);
		return pDoc;
	}

	public void setFile(final File file) {
		if (file != null) {
			try {
				setArchivo(FileUtils.readFileToByteArray(file));
				setFormatoArchivo(FilenameUtils.getExtension(file.getName()));
				setNombreArchivo(file.getName());
			}
			catch (IOException e) {
				SLOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private static int getInteger(Object obj) {
		if (obj instanceof Integer) {
			return (Integer) obj;
		} else {
			return -1;
		}
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success && getEXME_CtaPac_ID() > 0) {
			MEXMECtaPac ctapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
			if (TYPE_DischargeInstructions.equals(getType())) {
				ctapac.setEMail(getEMail());
				ctapac.setRequester(getRequester());
				ctapac.setArchivo(getArchivo());
				ctapac.setNombreArchivo(getNombreArchivo());
				ctapac.setFormatoArchivo(getFormatoArchivo());
				ctapac.setFechaEnv(getDeliveryDate());
				ctapac.save();
			}
		}
		return super.afterSave(newRecord, success);
	}

}
