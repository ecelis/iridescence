/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.IDHPatientInfo;
import org.compiere.model.bean.ResponseProMujer;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.SecureEngine;
import org.compiere.util.Trx;
import org.compiere.util.confHL7.MessageSenderProMujer;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.Client;

/**
 * Informacion adicional de paciente para ProMujer
 * Se llena por medio de la migracion de datos, o bien desde una de las transacciones de intefase
 * con la respuesta de FIN+
 * 
 * @author Lorena Lama
 */
public class MDHPatientInfo extends X_DH_PatientInfo {

	private static final long	serialVersionUID	= 1L;
	/** Static Logger */
	private static CLogger		slog				= CLogger.getCLogger(MDHPatientInfo.class);

	/**
	 * @param ctx
	 * @param DH_PatientInfo_ID
	 * @param trxName
	 */
	public MDHPatientInfo(final Properties ctx, final int DH_PatientInfo_ID, final String trxName) {
		super(ctx, DH_PatientInfo_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHPatientInfo(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Ejecuta el procesod de importacion, utiliza la tabla DH_I_Patient
	 * para crear el registro de paciente.
	 * 
	 * @param ctx
	 * @param res
	 * @param pSOCreditStatus
	 * @param trxName
	 * @return
	 */
	public static int createPatient(final Properties ctx, final IDHPatientInfo res, final String trxName) {
		final int patientId;
		// si no insertamos el valor de importacion
		final int nextId = DB.getSQLValue(null, "SELECT max(DH_I_Patient_ID)+1 FROM DH_I_Patient ");
		final List<Object> params = new ArrayList<>();
		final StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO DH_I_Patient VALUES (");
		sql.append(nextId).append(",");
		sql.append(Env.getAD_Client_ID(ctx)).append(",");
		sql.append("0,'Y',now(),0,now(),0,");
		sql.append("?,?,?,?,?,?,?,?,?,?,");// 10
		sql.append("null,null,null,");// FIXME
		sql.append("?,?,?,?,?,?,?,");// 7
		sql.append("?,?,?,?,?,?,");// 6
		sql.append("'N',null,null,null,null,null,?,?)");// 2

		params.add(StringUtils.trimToNull(StringUtils.upperCase(res.getCodUsuario())));// 1
		String names = StringUtils.trimToNull(StringUtils.upperCase(res.getNombres()));
		String lastname1 = StringUtils.trimToNull(StringUtils.upperCase(res.getPaterno()));
		String lastname2 = StringUtils.trimToNull(StringUtils.upperCase(res.getMaterno()));
		StringBuilder name = new StringBuilder();
		if(StringUtils.contains(names, " ")){
			String name1 = StringUtils.substringBefore(names, " ");
			String name2 = StringUtils.substringAfter(names, " ");
			name.append(SecureEngine.encrypt(name1)).append(" ").append(SecureEngine.encrypt(name2));
		} else {
			name.append(SecureEngine.encrypt(names));
		}
		params.add(name.toString());// 2
		params.add(SecureEngine.encrypt(lastname1));// 3
		params.add(SecureEngine.encrypt(lastname2));// 4
		params.add(res.getSexo());// 5
		params.add(StringUtils.trimToNull(StringUtils.upperCase(res.getDI())));// 6
		params.add(res.getCodDocIden());// 7
		params.add(res.getFechaNacimiento());// 8
		params.add(res.getEstadoCivil());// 9
		params.add(MCountry.get(ctx, Env.getC_Country_ID(ctx)).getCountryCode());// 10 FIXME
		params.add(res.getDomicilio());// 1
		params.add(res.getTelefono());// 2
		params.add(res.getCodCF());// 3
		params.add(res.getTipoCredito());// 4
		params.add(res.getBancaComunal());// 5
		params.add(res.getNombreAsesor());// 6
		params.add(res.getCiclo());// 7
		params.add(res.getFechaDesem());// 1
		params.add(res.getFechaUltPago());// 2
		params.add(res.getApellidoConyugue());// 3
		params.add(res.getNombreConyugue());// 4
		params.add(res.getDIConyugue());// 5
		params.add(res.getCodDocIdenConyugue());// 6
		params.add(res.getSOCreditStatus());// 1
		
		StringBuilder fullName = new StringBuilder();
		if (StringUtils.isNotBlank(lastname1)) {
			fullName.append(lastname1);
		}
		if (StringUtils.isNotBlank(lastname2)) {
			if (fullName.length() > 0) {
				fullName.append(" ");
			}
			fullName.append(lastname2);
		}
		if (StringUtils.isNotBlank(names)) {
			if (fullName.length() > 0) {
				fullName.append(" ");
			}
			fullName.append(names);
		}

		params.add(SecureEngine.encrypt(fullName.toString()));// 1
		
		final int val = DB.executeUpdate(sql.toString(), params.toArray(), trxName);
		if (val > 0) {
			patientId = DB.getSQLValue(trxName, "SELECT importDHPatients(?,?)", Env.getAD_Client_ID(ctx), nextId);
		} else {
			slog.log(Level.SEVERE, "Error al crear registro de importacion codigo FIN+: " + res.getCodUsuario());
			patientId = -1;
		}
		return patientId;
	}

	/**
	 * 
	 * @param patient
	 * @param trxName
	 * @return
	 */
	public static int getBPartnerId(final Properties ctx, final int patientId) {
		int bpartnerId;
		final MDHPatientInfo retValue = getFrom(ctx, patientId, null);
		if (retValue == null) {
			// bpartnerId = patient.getPatientBPartner().getC_BPartner_ID();
			bpartnerId = MConfigEC.get(ctx).getC_BPartner_ID();
		} else {
			bpartnerId = retValue.getBPartnerId();
		}
		return bpartnerId;
	}

	/**
	 * Objeto con la informacion adicional de promujer, si no tiene informacion
	 * no se puede considerar como cliente
	 * 
	 * @param ctx
	 * @param patientId
	 * @param trxName
	 * @return
	 */
	public static MDHPatientInfo getFrom(final Properties ctx, final int patientId, final String trxName) {
		final MDHPatientInfo retValue = new Query(ctx, Table_Name, " EXME_Paciente_ID=? ", trxName)//
			.addAccessLevelSQL(true)//
			.setOnlyActiveRecords(true)//
			.setParameters(patientId)//
			.first();
		return retValue;
	}

	/**
	 * Pacientes relacionados clientes de promujer.
	 * 
	 * @param ctx
	 * @param patientId
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static List<Integer> getLines(final Properties ctx, final int patientId, final String trxName) throws SQLException {
		final List<Integer> list = new ArrayList<>();
		// si el paciente no es cliente
		final MDHPatientInfo dhInfo = getFrom(ctx, patientId, null);

		// buscar los familiares
		if (dhInfo == null || !SOCREDITSTATUS_ActiveCredit.equals(dhInfo.getSOCreditStatus())) {
			// buscar los registros de paciente rel que si tengan
			final StringBuilder sql = new StringBuilder();
			sql.append(" (EXME_PacienteRel.EXME_Paciente2_ID=? AND  ");
			sql.append(" EXME_PacienteRel.EXME_Paciente1_ID IN (");
			sql.append("  SELECT dh.EXME_Paciente_ID FROM DH_PatientServices dh ");
			sql.append("  WHERE dh.EXME_Paciente_ID=EXME_PacienteRel.EXME_Paciente1_ID)) ");

			sql.append(" OR (EXME_PacienteRel.EXME_Paciente1_ID=? AND  ");
			sql.append(" EXME_PacienteRel.EXME_Paciente2_ID IN (");
			sql.append("  SELECT dh.EXME_Paciente_ID FROM DH_PatientServices dh ");
			sql.append("  WHERE dh.EXME_Paciente_ID=EXME_PacienteRel.EXME_Paciente2_ID)) ");
			final List<MEXMEPacienteRel> list2 = new Query(ctx, I_EXME_PacienteRel.Table_Name, sql.toString(), null)//
				.addAccessLevelSQL(true)//
				.setOnlyActiveRecords(true)//
				.setParameters(patientId, patientId)//
				.list();
			for (final MEXMEPacienteRel reg : list2) {
				if (reg.getEXME_Paciente1_ID() != patientId && !list.contains(reg.getEXME_Paciente1_ID())) {
					list.add(reg.getEXME_Paciente1_ID());
				} else if (reg.getEXME_Paciente2_ID() != patientId && !list.contains(reg.getEXME_Paciente2_ID())) {
					list.add(reg.getEXME_Paciente2_ID());
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param finMasCode
	 * @param orgRFC
	 * @return
	 */
	public static int getImportedPatient(String userCode, String orgRFC) {
		int patientId = DB.getSQLValue(null,//
			"SELECT EXME_Paciente_ID FROM EXME_Paciente WHERE lower(expediente)=? ", userCode);
		if (patientId <= 0) {
			patientId = DB.getSQLValue(null, //
				"SELECT EXME_Paciente_ID FROM DH_I_Patient WHERE lower(DocumentNo)=? AND lower(orgValue)=? ",//
				StringUtils.lowerCase(userCode), StringUtils.lowerCase(orgRFC));
		}
		slog.log(Level.INFO, "getImported patient code FIN+: " + userCode + " OrgInfo RFC: " + orgRFC + " >> PatientId: " + patientId);
		return patientId;
	}
	
	/**
	 * Procesa respuesta de transaccion 01 de FIN+
	 * Crea el paciente, expediente
	 * 
	 * @param ctx
	 * @param response
	 * @return
	 */
	public static boolean processResponseT01(final Properties ctx, final ResponseProMujer response){
		boolean success = false;
		final Trx mtrx = Trx.get(Trx.createTrxName("FIN+T01"), true);
		try {
			final int patientId = processResponseT01(ctx, response, mtrx.getTrxName());
			success = patientId > 0;
			if (success) {// actualizar lucene
				Trx.commit(mtrx, true);
				updateLucene(ctx, patientId);
			} else {
				Trx.rollback(mtrx, true);
			}
		} catch (final Exception e) {
			Trx.rollback(mtrx, true);
			slog.log(Level.SEVERE, "MDHPatientInfo - sendMessageT01 Fail", e);
			success = false;
		} finally {
			Trx.close(mtrx);
		}
		return success;
	}

	/**
	 * Procesa respuesta de transaccion 01 de FIN+
	 * Crea el paciente, expediente
	 * 
	 * @param ctx
	 * @param response
	 * @param trxName
	 * @return
	 */
	public static int processResponseT01(final Properties ctx, final IDHPatientInfo res, final String trxName) {
		// si ya existe el paciente
		int patientId = DB.getSQLValue(null,//
			"SELECT p.EXME_Paciente_ID FROM EXME_Paciente p INNER JOIN EXME_Hist_Exp e ON p.EXME_Paciente_ID=e.EXME_Paciente_ID  WHERE e.AD_Org_ID=? AND lower(p.Curp)=? ", 
			Env.getAD_Org_ID(ctx), StringUtils.lowerCase(res.getDI()));
		MDHPatientInfo info = null;
		// si ya existe
		if (patientId > 0) {
			info = updatePatientInfo(ctx, res, patientId, trxName);
		} else {// si no insertamos el valor de importacion
			patientId = createPatient(ctx, res, trxName);
			// si la respuesta dice que tiene credito
			if (patientId > 0) {
				if (StringUtils.isNotBlank(res.getSOCreditStatus()) && !SOCREDITSTATUS_NoCredit.equals(res.getSOCreditStatus())) {
					info = getFrom(ctx, patientId, trxName);
				}
			} else {
				slog.log(Level.SEVERE, "Error al importar los pacientes");
			}
		}
		if (info != null) {
			info.addPackage(null, trxName);
		}
		return patientId;
	}

	/**
	 * Card #1535 Pro Mujer {@link MessageSenderProMujer#sendMessageT01(String, String)}
	 * 
	 * @param ctx
	 * @param apellido1
	 * @param curp
	 */
	public static boolean sendMessageT01(final Properties ctx, final String apellido1, final String curp) {
		boolean success = true;
		try {
			if (StringUtils.isNotBlank(apellido1) && StringUtils.isNotBlank(curp)) {
				final Client mirthClient = MirthClient.getClient(ctx);
				if (mirthClient == null) {
					slog.log(Level.SEVERE, "sendMessageT01: No MirthClient");
					if(Env.getAD_User_ID(ctx) == 100 && Ini.APP_MODE_DEV.equalsIgnoreCase(Ini.getProperty(Ini.P_APPLICATION_MODE))){
						dummyTest(ctx);
					}
				} else {
					final MessageSenderProMujer msg = new MessageSenderProMujer(mirthClient, Env.getC_Country_ID(ctx));
					final ResponseProMujer response = msg.sendMessageT01(apellido1, curp);
					if (response == null) {
						slog.log(Level.SEVERE, "sendMessageT01: No ResponseProMujer");
					} else {
						success = response.isSuccess() && processResponseT01(ctx, response);
					}
				}
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, "MDHPatientInfo - sendMessageT01 Fail", e);
		}
		return success;
	}
	
	

	/**
	 * 
	 * @param ctx
	 * @param patientId
	 */
	public static void updateLucene(final Properties ctx, final int patientId) {
		try {// actualizar lucene
			if (patientId > 0) {
				slog.log(Level.CONFIG, "Updating indexes patientId: " + patientId);
				QuickSearchTables.checkTables(MEXMEPaciente.class, I_EXME_Paciente.Table_Name, patientId, null, ctx);
				QuickSearchTables.checkTables(MEXMEPacienteExpedienteV.class, I_EXME_Paciente_Expediente_V.Table_Name, patientId, null, ctx);
			}
		} catch (final Exception ex) {
			slog.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}
	}

	/**
	 * Procesa respuesta de transaccion 01 de FIN+
	 * Actualiza o crea el registro de DH_PatientInfo
	 * 
	 * @param ctx
	 * @param res
	 * @param patientId
	 * @param trxName
	 * @return
	 */
	public static MDHPatientInfo updatePatientInfo(final Properties ctx, final IDHPatientInfo res, final int patientId, final String trxName) {
		slog.log(Level.INFO, "update DH_PatientInfo: " + patientId);
		MDHPatientInfo info = getFrom(ctx, patientId, null);
		if (info == null) {
			info = new MDHPatientInfo(ctx, 0, null);
			info.setEXME_Paciente_ID(patientId);
		}
		try {
			info.setDocType(res.getCodDocIden());//si no es valido, continuar
		} catch (Exception e) {
			info.setDocType(DOCTYPE_National_ID);//obligatorio
			slog.log(Level.SEVERE, "PROMUJER valor invalido para DocType: " + res.getCodDocIden());
		}
		try {
			info.setCreditType(res.getTipoCredito());
		} catch (Exception e) {
			slog.log(Level.SEVERE, "PROMUJER valor invalido para TipoCredito: " + res.getTipoCredito());
		}
		info.setBancaComunal(res.getBancaComunal());
		info.setNombreAsesor(res.getNombreAsesor());
		info.setCiclo(res.getCiclo());
		info.setFechaDesem(res.getFechaDesem());
		info.setFechaUltPago(res.getFechaUltPago());
		if (StringUtils.isNotBlank(res.getSOCreditStatus())) {
			try {
				info.setSOCreditStatus(res.getSOCreditStatus());
			} catch (Exception e) {
				slog.log(Level.SEVERE, "PROMUJER valor invalido para SOCreditStatus: " + res.getSOCreditStatus());
			}
		}

		if (info.save(trxName)) {
			slog.log(Level.FINE, "updatePatientInfo: MDHPatientInfo saved for patient: " + patientId);
		} else {
			slog.log(Level.SEVERE, "updatePatientInfo: MDHPatientInfo no saved for patient: " + patientId);
			throw new MedsysException();
		}
		int value = getImportedPatient(res.getCodUsuario(), res.getCodCF());
		if(value <= 0){
			slog.log(Level.SEVERE, "updatePatientInfo: Patient no imported DH_I_PatientInfo: " + patientId);
		}
		return info;
	}

	/**
	 * Agrega un paquete/componente a un paciente con derechohabiencia
	 * 
	 * @param packageName llave de busqueda del paquete base, si es null, considera que es el componente universal
	 * @param trxName
	 */
	public MEXMEPaqBaseVersion addPackage(final String packageName, final String trxName) {
		MEXMEPaqBaseVersion version = null;
		// agregar explicitamente un paquete al paciente
		if (StringUtils.isNotBlank(packageName)) {
			version =
				new Query(getCtx(), I_EXME_PaqBase_Version.Table_Name, " pb.IsUniversalComp=? AND pb.IsActive='Y' AND lower(pb.Value)=? ", null)
					//
					.setJoins(new StringBuilder(" INNER JOIN EXME_PaqBase pb ON pb.EXME_PaqBase_ID=EXME_PaqBase_Version.EXME_PaqBase_ID "))
					.setOnlyActiveRecords(true)//
					.addAccessLevelSQL(true)//
					.setParameters(DB.TO_STRING(false), packageName)//
					// .setOrderBy("EXME_PaqBase_Version.ValidFrom DESC ")//
					.first();
		} else if (hasPackage(true)) { // FIXME: validacion de vigencia !!
			slog.log(Level.CONFIG, "addPackaget: Patient aalready has universal component: " + getEXME_Paciente_ID());
		} else if(StringUtils.isBlank(getSOCreditStatus()) || SOCREDITSTATUS_NoCredit.equals(getSOCreditStatus())){
			slog.log(Level.CONFIG, "addPackaget: No credit for patient " + getEXME_Paciente_ID() + " SOCreditStatus: " + getSOCreditStatus());
		} else {
			// buscar el componente universal
			version =
				new Query(getCtx(), I_EXME_PaqBase_Version.Table_Name, "  pb.IsUniversalComp=? AND pb.IsActive='Y'  ", null)
					//
					.setJoins(new StringBuilder(" INNER JOIN EXME_PaqBase pb ON pb.EXME_PaqBase_ID=EXME_PaqBase_Version.EXME_PaqBase_ID "))
					.setOnlyActiveRecords(true)//
					.addAccessLevelSQL(true)//
					.setParameters(DB.TO_STRING(true))//
					// .setOrderBy("EXME_PaqBase_Version.ValidFrom DESC ")//
					.first();
		}
		if (version == null) {
			slog.log(Level.SEVERE, "Error: MEXMEPaqBase_Version :" + packageName + " not found, unable to assign package to patientId : ",
				getEXME_Paciente_ID());
		} else {
			final MDHPatientServices patServ = new MDHPatientServices(getCtx(), 0, trxName);
			patServ.setEXME_Paciente_ID(getEXME_Paciente_ID());
			patServ.setEXME_PaqBase_Version_ID(version.getEXME_PaqBase_Version_ID());
			patServ.setM_Product_ID(version.getM_Product_ID());
			// setValidFrom(ValidFrom);//TODO
			// setValidTo(ValidFrom);
			if (patServ.save()) {
				slog.log(Level.FINE, "addPackage: MDHPatientServices saved for patient: " + getEXME_Paciente_ID());
			} else {
				slog.log(Level.SEVERE, "addPackage: MDHPatientServices no saved for patient: " + getEXME_Paciente_ID());
				throw new MedsysException();
			}
		}
		return version;
	}

	/**
	 * @param patient
	 * @param trxName
	 * @return
	 */
	public int getBPartnerId() {
		int bpartnerId;
		final String creditStatus = getSOCreditStatus();
		if (creditStatus == null) {
			bpartnerId = MConfigEC.get(getCtx()).getC_BPartner_ID();
		} else {
			switch (creditStatus) {
			case SOCREDITSTATUS_NoCredit:
				bpartnerId = MConfigEC.get(getCtx()).getC_BPartner_ID();
				break;
			default:
				final MConfigPre config = MConfigPre.get(getCtx(), null);
				if (hasPackage(false)) {
					bpartnerId = config.getC_BPartner_ID();
				} else {
					bpartnerId = config.getC_BPartner_Sales_ID();
				}
				break;
			}
		}
		return bpartnerId;
	}

	/**
	 * @return
	 */
	public boolean hasPackage(final boolean universalComp) {
		return MDHPatientServices.getTotalOfPackages(getCtx(), getEXME_Paciente_ID(), universalComp, null) > 0;
	}
	
	/**
	 * @param ctx
	 */
	public static void dummyTest(Properties ctx){
		ResponseProMujer response = new ResponseProMujer();
		StringBuilder str = new StringBuilder();
		str.append("|RESPUESTA:=CV|");
		str.append("CODUSUARIO:=IOPLMN789|");
		str.append("NOMBRES:=NOMBRE PACIENTE|");
		str.append("PATERNO:=APELLIDOPAT|");
		str.append("MATERNO:=APELLIDOMAT|");
		str.append("SEXO:=0|");
		str.append("DI:=QWRTY9876|");
		str.append("CODDOCIDEN:=DNI|");
		str.append("FECHANACIMIENTO:=19700512000000|");
		str.append("ESTADOCIVIL:=C|");
		str.append("DOMICILIO:=CALLE 23 COLONIA CIUDAD MUNICIPIO ESTADO|");
		str.append("TELEFONO:=81818120|");
		str.append("CODCF:=HME090528B34|");//ZÁNITAS
		str.append("BANCACOMUNAL:=BANCA COM|");
		str.append("NOMBREASESOR:=NOMBRE ASESOR|");
		str.append("CICLO:=1|");
		str.append("FECHADESEM:=20140102123021|");
		str.append("FECHAULTPAGO:=20141210102106|");
		str.append("APELLIDOCONYUGUE:=APELLIDOCON|");
		str.append("NOMBRECONYUGUE:=NOMBRECON|");
		str.append("DICONYUGUE:=ACB1234XYZ|");
		str.append("CODDOCIDENCONYUGUE:=DNI|");
		str.append("BANDERA:=OK|");
		response.setData(str.toString());
		
		boolean success = false;
		final Trx mtrx = Trx.get(Trx.createTrxName("TestT01"), true);
		try {
			if(response.getRespuesta() == null){
				throw new MedsysException();
			}
			
			final int patientId = processResponseT01(ctx, response, mtrx.getTrxName());
			success = patientId > 0;
			if (success) {// actualizar lucene
				Trx.commit(mtrx, true);
				updateLucene(ctx, patientId);
			} else {
				Trx.rollback(mtrx, true);
			}
		} catch (final Exception e) {
			Trx.rollback(mtrx, true);
			slog.log(Level.SEVERE, "MDHPatientInfo - sendMessageT01 Fail", e);
			success = false;
		} finally {
			Trx.close(mtrx);
		}
		
	}
	
}
