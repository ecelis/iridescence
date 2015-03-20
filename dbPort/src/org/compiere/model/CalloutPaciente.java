package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.CalculoCURPyRFC;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * Callouts para Hoja de Reclasificacion
 * Callouts para el mtto de ingreso de pacientes
 * 
 * <b>Fecha:</b> 20/Febrero/2006
 * <p>
 * <b>Modificado: </b> $Author: otorres $
 * <p>
 * <b>En :</b> $Date: 2006/10/24 13:34:00 $
 * <p>
 * 
 * @author Lorena Espinoza | Rocio Solorzano
 * @version $Revision: 1.5 $
 */
public class CalloutPaciente extends CalloutEngine {

	/**
	 * Metodo para mostrar la clasificacion previa del paciente
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String nombrePaciente(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		setCalloutActive(false);

		String msg = "";

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		String nombrePaciente = null;
		Integer expediente = null;
		// Integer exme_paciente_id=new Integer(0);
		String nomSexo = "";
		String noMEXMECama = "";
		String nomClasificacion = "";
		String nomClinico = "";
		Integer idCama = null;
		Integer idClasificacion = null;
		Integer idClinico = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		Integer EXME_Paciente_ID = null;
		if (value instanceof BigDecimal)
			EXME_Paciente_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			EXME_Paciente_ID = (Integer) value;

		if (EXME_Paciente_ID != null && EXME_Paciente_ID.intValue() != 0) {
			sql.append("SELECT EXME_Hist_Exp_ID FROM ");
			sql.append("EXME_Hist_Exp WHERE EXME_Paciente_ID = ");
			sql.append(EXME_Paciente_ID);
			
			if (DB.isOracle()) {
				sql.append(" AND ROWNUM = 1");
			} else if (DB.isPostgreSQL()) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			}
			
			expediente = obtenID(sql);
			
			if (expediente == null) {
				expediente = 0;
			}

			MEXMEPaciente pac = new MEXMEPaciente(ctx, EXME_Paciente_ID.intValue(), null);

			if (pac != null) {
				nombrePaciente = pac.getNombre_Pac();
				nomSexo = MRefList.getListName(ctx, MEXMEPaciente.SEXO_AD_Reference_ID, pac.getSexo());//LAMA
				//nomSexo = (pac.getSexo().equals("F") ? "FEMENINO" : (pac.getSexo().equals("M")) ? "MASCULINO" : (pac.getSexo().equals("U")) ? "SIN ASIGNACION": "");

				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

				sql.append("SELECT classCl.exme_clascliente_id FROM EXME_CLASCliente classCl ");
				sql.append(" WHERE classCl.c_bpartner_id = ");
				sql.append(pac.getC_BPartner_ID());
				
				if (DB.isOracle()) {
					sql.append(" AND ROWNUM = 1");
				} else if (DB.isPostgreSQL()) {
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
				}

				idClasificacion = obtenID(sql);

				if (idClasificacion != null) {
					X_EXME_ClasCliente clas = new X_EXME_ClasCliente(ctx, idClasificacion.intValue(), null);
					nomClasificacion = clas.getValue();
				}
			}

			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT EXME_CtaPac_ID FROM EXME_CtaPac ");
			sql.append(" WHERE EXME_Paciente_ID = ");
			sql.append(EXME_Paciente_ID);
			sql.append(" AND isActive = 'Y' "); // .-Lama
			
			if (DB.isOracle()) {
				sql.append(" AND ROWNUM = 1");
			} else if (DB.isPostgreSQL()) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
			}
			
			Integer id_ctp = obtenID(sql);

			if (id_ctp != null) {
				MEXMECtaPac ctp = new MEXMECtaPac(ctx, id_ctp.intValue(), null);
				MEXMECama cam = new MEXMECama(ctx, ctp.getEXME_Cama_ID(), null);

				if (cam != null) {
					noMEXMECama = cam.getValue();
					idCama = new Integer(cam.getEXME_Cama_ID());

					MEXMEHabitacion hab = new MEXMEHabitacion(ctx, cam.getEXME_Habitacion_ID(), null);
					if (hab != null) {
						MEXMEArea ar = new MEXMEArea(ctx, hab.getEXME_Area_ID(), null);
						nomClinico = ar.getValue();
						idClinico = new Integer(ar.getEXME_Area_ID());
					}
				}
			}
		}

		mTab.setValue("Nombre_Pac", nombrePaciente);

		if ((mTab.getValue("EXME_Hist_Exp_ID") != null && Integer.parseInt(mTab.getValue("EXME_Hist_Exp_ID").toString()) != expediente.intValue())
				|| mTab.getValue("EXME_Hist_Exp_ID") == null)
			mTab.setValue("EXME_Hist_Exp_ID", expediente);

		mTab.setValue("TSSexo", nomSexo);
		mTab.setValue("TSCama", noMEXMECama);
		mTab.setValue("TSClasificacion", nomClasificacion);
		mTab.setValue("TSClinico", nomClinico);
		mTab.setValue("EXME_Cama_ID", idCama == null || idCama == 0 ? null : idCama);
		mTab.setValue("EXME_ClasCliente_ID", idClasificacion == null || idClasificacion == 0 ? null : idClasificacion);
		mTab.setValue("EXME_Clas_Origen_ID", idClasificacion == null || idClasificacion == 0 ? null : idClasificacion);
		mTab.setValue("EXME_Area_ID", idClinico == null || idClinico == 0 ? null : idClinico);

		setCalloutActive(false);

		return msg;
	}

	public String expediente(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		setCalloutActive(false);

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		String msg = "";

		Integer exme_paciente_id = null;
		String nombrePaciente = null;
		String nomSexo = "";
		String noMEXMECama = "";
		String nomClasificacion = "";
		String nomClinico = "";
		Integer idCama = null;
		Integer idClasificacion = null;
		Integer idClinico = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		Integer EXME_Hist_Exp_ID = null;
		if (value instanceof BigDecimal)
			EXME_Hist_Exp_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			EXME_Hist_Exp_ID = (Integer) value;

		if (EXME_Hist_Exp_ID != null && EXME_Hist_Exp_ID.intValue() != 0) {
			// buscar la clasificacion original
			MEXMEHistExp e = new MEXMEHistExp(ctx, EXME_Hist_Exp_ID.intValue(), null);

			if (e != null) {
				MEXMEPaciente pac = new MEXMEPaciente(ctx, e.getEXME_Paciente_ID(), null);

				if (pac != null) {
					exme_paciente_id = new Integer(pac.getEXME_Paciente_ID());
					nombrePaciente = pac.getNombre_Pac();
					nomSexo = MRefList.getListName(ctx, MEXMEPaciente.SEXO_AD_Reference_ID, pac.getSexo());//LAMA
					//nomSexo = (pac.getSexo().equals("F") ? "FEMENINO" : (pac.getSexo().equals("M")) ? "MASCULINO": (pac.getSexo().equals("U")) ? "SIN ASIGNACION" : "");

					sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
					sql.append("SELECT exme_clascliente_id ");
					sql.append(" FROM EXME_CLASCliente ");
					sql.append(" WHERE c_bpartner_id = ");
					sql.append(pac.getC_BPartner_ID());
					
					if (DB.isOracle()) {
						sql.append(" AND ROWNUM = 1");
					} else if (DB.isPostgreSQL()) {
						sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
					}

					idClasificacion = obtenID(sql);
					if (idClasificacion != null) {
						X_EXME_ClasCliente clas = new X_EXME_ClasCliente(ctx, idClasificacion.intValue(), null);
						nomClasificacion = clas.getValue();
					}
				}

				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
				sql.append("SELECT EXME_CtaPac_ID FROM EXME_CtaPac ");
				sql.append(" WHERE EXME_Paciente_ID = ");
				sql.append(exme_paciente_id);
				sql.append(" AND isActive = 'Y' "); // .-Lama
				
				if (DB.isOracle()) {
					sql.append(" AND ROWNUM = 1");
				} else if (DB.isPostgreSQL()) {
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
				}				
				Integer id_ctp = obtenID(sql);

				if (id_ctp != null) {
					MEXMECtaPac ctp = new MEXMECtaPac(ctx, id_ctp.intValue(), null);
					if (ctp != null) {
						MEXMECama cam = new MEXMECama(ctx, ctp.getEXME_Cama_ID(), null);

						if (cam != null) {
							noMEXMECama = cam.getValue();
							idCama = new Integer(cam.getEXME_Cama_ID());

							MEXMEHabitacion hab = new MEXMEHabitacion(ctx, cam.getEXME_Habitacion_ID(), null);
							if (hab != null) {
								MEXMEArea ar = new MEXMEArea(ctx, hab.getEXME_Area_ID(), null);
								if (ar != null) {
									nomClinico = ar.getValue();
									idClinico = new Integer(ar.getEXME_Area_ID());
								}
							}
						}
					}
				}
			}
		}

		mTab.setValue("Nombre_Pac", nombrePaciente);
		if ((mTab.getValue("EXME_Paciente_ID") != null && Integer.parseInt(mTab.getValue("EXME_Paciente_ID").toString()) != exme_paciente_id.intValue())
				|| mTab.getValue("EXME_Paciente_ID") == null)
			mTab.setValue("EXME_Paciente_ID", exme_paciente_id);
		mTab.setValue("TSSexo", nomSexo);
		mTab.setValue("TSCama", noMEXMECama);
		mTab.setValue("TSClasificacion", nomClasificacion);
		mTab.setValue("TSClinico", nomClinico);
		mTab.setValue("EXME_Cama_ID", idCama == null || idCama == 0 ? null : idCama);
		mTab.setValue("EXME_ClasCliente_ID", idClasificacion == null || idClasificacion == 0 ? null : idClasificacion);
		mTab.setValue("EXME_Clas_Origen_ID", idClasificacion == null || idClasificacion == 0 ? null : idClasificacion);
		mTab.setValue("EXME_Area_ID", idClinico == null || idClinico == 0 ? null : idClinico);

		setCalloutActive(false);

		return msg;
	}

	private Integer obtenID(StringBuilder sql) {
		Integer exp = null;
		ResultSet registro = null;
		Statement state = null;

		try {
			state = DB.createStatement();
			registro = state.executeQuery(sql.toString());

			if (registro.next()) {
				exp = new Integer(registro.getInt(1));
			}

			registro.close();
			state.close();
		} catch (Exception ex) {
			log.log(Level.SEVERE, "obtenID - sql: " + sql, ex);
		} finally {
			try {
				if (registro != null) {
					registro.close();
				}

				if (state != null) {
					state.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "obtenID - closing objects: sql: " + sql, e);
			}

			registro = null;
			state = null;
		}

		return exp;
	}

	/*private String obtenSexo(char s) {
		String resp = "";

		switch (s) {
		case 'F':
			resp = "FEMENINO";
			break;
		case 'M':
			resp = "MASCULINO";
			break;
		case 'U':
			resp = "SIN ASIGNAR";
			break;
		}
		return resp;
	}*/

	/**
	 * Metodo para llenar los campos del mantenimiento Pacientes con los datos de la tabala EXME_Paciente.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return msg
	 * @author lhernandez
	 */
	public String getDatosPaciente(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		setCalloutActive(false);

		String msg = "";

		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);

		Integer EXME_Paciente_ID = null;
		if (value instanceof BigDecimal)
			EXME_Paciente_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			EXME_Paciente_ID = (Integer) value;
		Integer expediente = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		if (EXME_Paciente_ID != null && EXME_Paciente_ID.intValue() != 0) {

			MEXMEPaciente pac = null;
			pac = new MEXMEPaciente(ctx, EXME_Paciente_ID.intValue(), null);

			sql.append("SELECT EXME_Hist_Exp_ID FROM EXME_Hist_Exp WHERE EXME_Paciente_ID = ");
			sql.append(EXME_Paciente_ID);
			expediente = obtenID(sql);

			if (pac != null) {

				mTab.setValue("Nombre_Pac", pac.getNombre_Pac());
				mTab.setValue("Name", pac.getName());
				mTab.setValue("Nombre2", pac.getNombre2() != null ? pac.getNombre2() : "");
				mTab.setValue("Apellido1", pac.getApellido1());
				mTab.setValue("Apellido2", pac.getApellido2());
				mTab.setValue("Apellido3", pac.getApellido3() != null ? pac.getApellido3() : "");
				mTab.setValue("Sexo", pac.getSexo());
				mTab.setValue("FechaNac", pac.getFechaNac());
				mTab.setValue("HoraNac", pac.getHoraNac());
				mTab.setValue("Value", pac.getValue());
				mTab.setValue("Description ", pac.getDescription() != null ? pac.getDescription() : "");
				mTab.setValue("Imss", pac.getImss() != null ? pac.getImss() : "");
				mTab.setValue("Rfc", pac.getRfc() != null ? pac.getRfc() : "");
				mTab.setValue("Curp", pac.getCurp() != null ? pac.getCurp() : "");
				mTab.setValue("EdoCivil", pac.getEdoCivil());
				mTab.setValue("EsAsegurado", pac.isEsAsegurado());
				mTab.setValue("LimiteCredito ", pac.getLimiteCredito() != null ? pac.getLimiteCredito() : "");
				mTab.setValue("TelParticular", pac.getTelParticular() != null ? pac.getTelParticular() : "");
				mTab.setValue("EMail", pac.getEMail() != null ? pac.getEdad() : "");
				mTab.setValue("Nombre_Fam", pac.getNombre_Fam() != null ? pac.getNombre_Fam() : "");
				mTab.setValue("Apellido1_Fam", pac.getApellido1_Fam() != null ? pac.getApellido1_Fam() : "");
				mTab.setValue("Apellido2_Fam", pac.getApellido2_Fam() != null ? pac.getApellido2_Fam() : "");
				mTab.setValue("TelParticular_Fam", pac.getTelParticular_Fam() != null ? pac.getTelParticular_Fam() : "");
				mTab.setValue("Antiguedad_Fam", pac.getAntiguedad_Fam());
				mTab.setValue("Puesto_Fam", pac.getPuesto_Fam() != null ? pac.getPuesto_Fam() : "");
				mTab.setValue("TelFamiliar", pac.getTelFamiliar() != null ? pac.getTelFamiliar() : "");
				mTab.setValue("DirFamiliar", pac.getDirFamiliar() != null ? pac.getDirFamiliar() : "");
				mTab.setValue("Poliza", pac.getPoliza() != null ? pac.getPoliza() : "");
				mTab.setValue("NombreFamiliar", pac.getNombreFamiliar() != null ? pac.getNombreFamiliar() : "");
				mTab.setValue("Titular", pac.getTitular() != null ? pac.getTitular() : "");
				mTab.setValue("NoAutorizacion", pac.getNoAutorizacion() != null ? pac.getNoAutorizacion() : "");
				mTab.setValue("Observaciones", pac.getObservaciones() != null ? pac.getObservaciones() : "");
				mTab.setValue("NoSiniestro", pac.getNoSiniestro() != null ? pac.getNoSiniestro() : "");
				mTab.setValue("RFC_Resp", pac.getRFC_Resp() != null ? pac.getRFC_Resp() : "");
				mTab.setValue("DirPersResp", pac.getDirPersResp() != null ? pac.getDirPersResp() : "");
				mTab.setValue("DirTrabPersResp", pac.getDirTrabPersResp() != null ? pac.getDirTrabPersResp() : "");
				mTab.setValue("LimCredDerechoh", pac.getLimCredDerechoh() != null ? pac.getLimCredDerechoh() : "");
				mTab.setValue("Imagen", pac.getImagen() != null ? pac.getImagen() : "");
				mTab.setValue("EXME_Institucion_Names", pac.getEXME_Institucion_Names() != null ? pac.getEXME_Institucion_Names() : "");
				mTab.setValue("EXME_Otra_Inst", pac.getEXME_Otra_Inst() != null ? pac.getEXME_Otra_Inst() : "");
				mTab.setValue("EXME_Diagnostico_Ingreso_Descr", pac.getEXME_Diagnostico_Ingreso_Descr() != null ? pac.getEXME_Diagnostico_Ingreso_Descr() : "");
				mTab.setValue("EXME_Diagnostico_Egreso_Descr", pac.getEXME_Diagnostico_Egreso_Descr() != null ? pac.getEXME_Diagnostico_Egreso_Descr() : "");
				mTab.setValue("DocumentoConvenio", pac.getDocumentoConvenio() != null ? pac.getDocumentoConvenio() : "");
				mTab.setValue("Nomina", pac.getNomina() != null ? pac.getNomina() : "");
				mTab.setValue("NombreFamiliar", pac.getNombreFamiliar() != null ? pac.getNombreFamiliar() : "");
				mTab.setValue("Empresa_Lab_Pac", pac.getEmpresa_Lab_Pac() != null ? pac.getEmpresa_Lab_Pac() : "");
				mTab.setValue("Puesto_Lab_Pac", pac.getPuesto_Lab_Pac() != null ? pac.getPuesto_Lab_Pac() : "");
				mTab.setValue("Direccion_Lab_Pac", pac.getDireccion_Lab_Pac() != null ? pac.getDireccion_Lab_Pac() : "");
				mTab.setValue("Telefono_Lab_Pac1", pac.getTelefono_Lab_Pac1() != null ? pac.getTelefono_Lab_Pac1() : "");
				mTab.setValue("Telefono_Lab_Pac2", pac.getTelefono_Lab_Pac2() != null ? pac.getTelefono_Lab_Pac2() : "");
				mTab.setValue("Telefono_Lab_Pac3", pac.getTelefono_Lab_Pac3() != null ? pac.getTelefono_Lab_Pac3() : "");
				mTab.setValue("ColoniaPersResp", pac.getColoniaPersResp() != null ? pac.getColoniaPersResp() : "");
				mTab.setValue("CiudadPersResp", pac.getCiudadPersResp() != null ? pac.getCiudadPersResp() : "");
				mTab.setValue("CpPersResp", pac.getCpPersResp() != null ? pac.getCpPersResp() : "");
				mTab.setValue("SeguroPopular", pac.getSeguroPopular() != null ? pac.getSeguroPopular() : "");
				mTab.setValue("RFC_Resp", pac.getRFC_Resp() != null ? pac.getRFC_Resp() : "");
				mTab.setValue("Expediente", pac.getExpediente() != null ? pac.getExpediente() : "");
				mTab.setValue("FactEspec", pac.isFactEspec());
				mTab.setValue("Verificado", pac.isVerificado());
				mTab.setValue("IsPension", pac.isPension());
				mTab.setValue("IsDonador", pac.isDonador());
				mTab.setValue("IsReceptor", pac.isReceptor());
				mTab.setValue("Derechohabiente", pac.isDerechohabiente());
				mTab.setValue("EsTitular", pac.isEsTitular());
				mTab.setValue("DerechohabienteOtro", pac.isDerechohabienteOtro());
				mTab.setValue("PoblacionAbierta", pac.isPoblacionAbierta());
				mTab.setValue("Particular", pac.isParticular());
				mTab.setValue("IsPrinted", pac.getIsPrinted());
				mTab.setValue("IsChangeLog", pac.getIsChangeLog());
				mTab.setValue("IsRefer", pac.isRefer());
				// mTab.setValue("fechaini",pac.getfechaIni]());

				if (pac.getC_Country_Nac_ID() > 0)
					mTab.setValue("C_Country_Nac_ID", pac.getC_Country_Nac_ID());
				if (pac.getEXME_RFC_ID() > 0)
					mTab.setValue("EXME_RFC_ID", pac.getEXME_RFC_ID());
				if (pac.getC_Location_ID() > 0)
					mTab.setValue("C_Location_ID", pac.getC_Location_ID());
				if (pac.getEXME_PatientClass_ID() > 0)
					mTab.setValue("EXME_TipoPaciente_ID", pac.getEXME_PatientClass_ID());
				if (pac.getEXME_Religion_ID() > 0)
					mTab.setValue("EXME_Religion_ID", pac.getEXME_Religion_ID());
				if (pac.getC_BPartner_ID() > 0)
					mTab.setValue("C_BPartner_ID", pac.getC_BPartner_ID());
				if (pac.getC_BPartner_Seg_ID() > 0)
					mTab.setValue("C_BPartner_Seg_ID", pac.getC_BPartner_Seg_ID());
				if (pac.getC_Location_Nac_ID() > 0)
					mTab.setValue("C_Location_Nac_ID", pac.getC_Location_Nac_ID());
				if (pac.getEXME_Parentesco_ID() > 0)
					mTab.setValue("EXME_Parentesco_ID", pac.getEXME_Parentesco_ID());
				/*
				 * if(pac.getC_BPartner_Location_ID()>0)
				 * mTab.setValue("C_BPartner_Location_ID",pac.getC_BPartner_Location_ID()); lhernandez
				 */
				if (pac.getFechaBaja() != null)
					mTab.setValue("FechaBaja", pac.getFechaBaja());
				if (pac.getFechaVigencia() != null)
					mTab.setValue("FechaVigencia", pac.getFechaVigencia());
				if (pac.getTitular_ID() > 0)
					mTab.setValue("Titular_ID", pac.getTitular_ID());
				if (pac.getEXME_ArchClinico_ID() > 0)
					mTab.setValue("EXME_ArchClinico_ID", pac.getEXME_ArchClinico_ID());
				if (pac.getEXME_Ocupacion_Fam_ID() > 0)
					mTab.setValue("EXME_Ocupacion_Fam_ID", pac.getEXME_Ocupacion_Fam_ID());
				if (pac.getEXME_Nacionalidad_ID() > 0)
					mTab.setValue("EXME_Nacionalidad_ID", pac.getEXME_Nacionalidad_ID());
				if (pac.getEXME_Escolaridad_ID() > 0)
					mTab.setValue("EXME_Escolaridad_ID", pac.getEXME_Escolaridad_ID());
				if (pac.getEXME_Ocupacion_ID() > 0)
					mTab.setValue("EXME_Ocupacion_ID", pac.getEXME_Ocupacion_ID());
				if (pac.getEXME_Institucion_ID() > 0)
					mTab.setValue("EXME_Institucion_ID", pac.getEXME_Institucion_ID());
				if (pac.getEXME_Especialidad_TS_ID() > 0)
					mTab.setValue("EXME_Especialidad_TS_ID", pac.getEXME_Especialidad_TS_ID());
				if (pac.getEXME_Referencia_ID() > 0)
					mTab.setValue("EXME_Referencia_ID", pac.getEXME_Referencia_ID());
				if (pac.getEXME_Referencia_Int_ID() > 0)
					mTab.setValue("EXME_Referencia_Int_ID", pac.getEXME_Referencia_Int_ID());
				if (pac.getEXME_Expediente_ID() > 0)
					mTab.setValue("EXME_Expediente_ID", pac.getEXME_Expediente_ID());
				if (pac.getC_Country_Nac_ID() > 0)
					mTab.setValue("C_Country_Nac_ID", pac.getC_Country_Nac_ID());
				if (pac.getC_Region_Nac_ID() > 0)
					mTab.setValue("C_Region_Nac_ID", pac.getC_Region_Nac_ID());
				if (pac.getEXME_Delegacion_Paciente_ID() > 0)
					mTab.setValue("EXME_Delegacion_Paciente_ID", pac.getEXME_Delegacion_Paciente_ID());
				if (pac.getEXME_Delegacion_Nacimiento_ID() > 0)
					mTab.setValue("EXME_Delegacion_Nacimiento_ID", pac.getEXME_Delegacion_Nacimiento_ID());
				if (pac.getFechaVencimiento() != null)
					mTab.setValue("FechaVencimiento", pac.getFechaVencimiento());
				if (pac.getC_Region_PersResp_ID() > 0)
					mTab.setValue("C_Region_PersResp_ID", pac.getC_Region_PersResp_ID());
				if (pac.getC_Country_PersResp_ID() > 0)
					mTab.setValue("C_Country_PersResp_ID", pac.getC_Country_PersResp_ID());
				if (pac.getC_LocationFam_ID() > 0)
					mTab.setValue("C_LocationFam_ID", pac.getC_LocationFam_ID());
				if (pac.getC_LocationPerResp_ID() > 0)
					mTab.setValue("C_LocationPerResp_ID", pac.getC_LocationPerResp_ID());
				if (pac.getEXME_Refer_ID() > 0)
					mTab.setValue("EXME_Refer_ID", pac.getEXME_Refer_ID());
				if (pac.getFechaRegistro() != null)
					mTab.setValue("FechaRegistro", pac.getFechaRegistro());
				if (pac.getC_LocationReg_ID() > 0)
					mTab.setValue("C_LocationReg_ID", pac.getC_LocationReg_ID());
				if (pac.getEXME_Arma_ID() > 0)
					mTab.setValue("EXME_Arma_ID", pac.getEXME_Arma_ID());
				if (pac.getEXME_Grado_ID() > 0)
					mTab.setValue("EXME_Grado_ID", pac.getEXME_Grado_ID());
				if (pac.getEXME_Grupo_Especialidad_ID() > 0)
					mTab.setValue("EXME_Grupo_Especialidad_ID", pac.getEXME_Grupo_Especialidad_ID());
				if (pac.getEXME_Unidad_ID() > 0)
					mTab.setValue("EXME_Unidad_ID", pac.getEXME_Unidad_ID());
				if (pac.getFechaIni_Seg() != null)
					mTab.setValue("FechaIni_Seg", pac.getFechaIni_Seg());
				if (pac.getFechaFin_Seg() != null)
					mTab.setValue("FechaFin_Seg", pac.getFechaFin_Seg());
				if ((mTab.getValue("EXME_Hist_Exp_ID") != null && Integer.parseInt(mTab.getValue("EXME_Hist_Exp_ID").toString()) != expediente.intValue())
						|| mTab.getValue("EXME_Hist_Exp_ID") == null)
					mTab.setValue("EXME_Hist_Exp_ID", expediente);
			}
		}
		setCalloutActive(false);

		return msg;
	} // fin metodo

	/**
	 * Metodo para llenar la edad del paciente.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void calcularEdad(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			//Lama - Mantis#4786 - Correcciones y cambios eCare
			if (mTab.get_ValueAsString(X_EXME_Paciente.COLUMNNAME_FechaNac) != null && mTab.get_ValueAsString(X_EXME_Paciente.COLUMNNAME_FechaNac).length() > 0) {
				// SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
				// fechaNac = spd.format(mTab.getValue("FechaNac"));
				try {
					// calcular las horas de nacido
					String fecha = mTab.get_ValueAsString(X_EXME_Paciente.COLUMNNAME_FechaNac);
					SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date fechaNac = spd.parse(fecha);
					CalcularEdadAMD edad = CalcularEdadAMD.getEdadHrs(ctx, fechaNac);
					if(edad != null)
						mTab.setValue(X_EXME_Paciente.COLUMNNAME_Edad, edad.getEdadAMD());
					//this.crearRFCandCURP(ctx, WindowNo, mTab, mField, value);//no para USA
				} catch (ParseException e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				} catch (Exception e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				}

			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Metodo para copiar los datos del paciente al familiar cercano.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void copiarAFamiliar(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			String nombreFamiliar = "";
			String telFamiliar = "";
			String rfcFamiliar = "";

			if (mTab.getValue("copyKin") != null && mTab.getValue("copyKin").equals(true)) {

				nombreFamiliar = mTab.get_ValueAsString("Name") + " " + mTab.get_ValueAsString("Apellido1") + " " + mTab.get_ValueAsString("Apellido2");
				telFamiliar = mTab.get_ValueAsString("TelParticular");
				rfcFamiliar = mTab.get_ValueAsString("Rfc");

				mTab.setValue("NombreFamiliar", nombreFamiliar);
				mTab.setValue("TelFamiliar", telFamiliar);
				mTab.setValue("RFC_Fam", rfcFamiliar);

				if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
					int locationKinID = 0;
					if (mTab.getValue("C_LocationFam_ID") != null && mTab.get_ValueAsString("C_LocationFam_ID").length() > 0) {
						locationKinID = Integer.parseInt(mTab.get_ValueAsString("C_LocationFam_ID"));
					}
					MLocation locationPac = new MLocation(ctx, Integer.parseInt(mTab.get_ValueAsString("C_Location_ID")), null);
					MLocation locationKin = MLocation.copyFrom(ctx, locationPac, locationKinID, null);

					//Trx m_trx = null;//lama: no se usa y no es requerida
					//String trxName = Trx.createTrxName("address");
					//m_trx = Trx.get(trxName, true);
					boolean guardado = true;

					try {
						if (!locationKin.save()) {
							guardado = false;
						}

						if (guardado) {
							//m_trx.commit();
							//m_trx.close();
							mTab.setValue("C_LocationFam_ID", locationKin.getC_Location_ID());
						}

					} catch (Exception e) {
						//if (m_trx != null) {
						//	m_trx.rollback();
						//	m_trx.close();
						//}
					} finally {
						//m_trx = null;
						//trxName = null;
					}
				}
			}
			else if (mTab.getValue("copyKin") != null && mTab.getValue("copyKin").equals(false)) {
				mTab.setValue("NombreFamiliar", "");
				mTab.setValue("TelFamiliar", "");
				mTab.setValue("RFC_Fam", "");
				int locationKinID = 0;
				if (mTab.getValue("C_LocationFam_ID") != null && mTab.get_ValueAsString("C_LocationFam_ID").length() > 0) {
					locationKinID = Integer.parseInt(mTab.get_ValueAsString("C_LocationFam_ID"));
					if (locationKinID > 0) {
						MLocation locationKin = new MLocation(ctx, locationKinID, null);

						locationKin.setAddress1(null);
						locationKin.setAddress2(null);
						locationKin.setCity(null);
						locationKin.setPostal(null);
						locationKin.setPostal_Add(null);
						locationKin.setC_Region_ID(0);
						locationKin.setC_City_ID(0);
						locationKin.setRegionName(null);
						locationKin.setAddress3(null);
						locationKin.setAddress4(null);
						locationKin.setEXME_TownCouncil_ID(0);
						locationKin.setNumExt(null);
						locationKin.setNumIn(null);
						locationKin.setAddress3(null);
						locationKin.setAddress4(null);

						//Trx m_trx = null;//lama: no se usa y no es requerida
						//String trxName = Trx.createTrxName("address");
						//m_trx = Trx.get(trxName, true);
						boolean guardado = true;

						try {
							if (!locationKin.save()) {
								guardado = false;
							}

							if (guardado) {
								//m_trx.commit();
								//m_trx.close();
								mTab.setValue("C_LocationFam_ID", locationKin.getC_Location_ID());
							}

						} catch (Exception e) {
							//if (m_trx != null) {
							//	m_trx.rollback();
							//	m_trx.close();
							//}
						} finally {
							//m_trx = null;
							//trxName = null;
						}
					}
				}
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Metodo para copiar los datos del paciente a la persona responsable.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void copiarAResponsable(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			String nombre = "";
			String apellido1 = "";
			String apellido2 = "";
			String tel = "";
			String rfc = "";
			String tipoComunicacion = "";

			if (mTab.getValue("copyResponsible") != null && mTab.getValue("copyResponsible").equals(true)) {

				nombre = mTab.get_ValueAsString("Name") + " " + mTab.get_ValueAsString("Nombre2");
				apellido1 = mTab.get_ValueAsString("Apellido1");
				apellido2 = mTab.get_ValueAsString("Apellido2");
				tel = mTab.get_ValueAsString("TelParticular");
				rfc = mTab.get_ValueAsString("Rfc");
				tipoComunicacion = mTab.get_ValueAsString("ParticularComCode");

				mTab.setValue("Nombre_Fam", nombre);
				mTab.setValue("Apellido1_Fam", apellido1);
				mTab.setValue("Apellido2_Fam", apellido2);
				mTab.setValue("TelParticular_Fam", tel);
				mTab.setValue("FamiliarComCode", tipoComunicacion);
				mTab.setValue("RFC_Resp", rfc);

				if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
					int locationRespID = 0;
					if (mTab.getValue("C_LocationPerResp_ID") != null && mTab.get_ValueAsString("C_LocationPerResp_ID").length() > 0) {
						locationRespID = Integer.parseInt(mTab.get_ValueAsString("C_LocationPerResp_ID"));
					}
					MLocation locationPac = new MLocation(ctx, Integer.parseInt(mTab.get_ValueAsString("C_Location_ID")), null);
					MLocation locationResp = MLocation.copyFrom(ctx, locationPac, locationRespID, null);

					//Trx m_trx = null; //lama: no se usa y no es requerida
					//String trxName = Trx.createTrxName("address");
					//m_trx = Trx.get(trxName, true);
					boolean guardado = true;

					try {
						if (!locationResp.save()) {
							guardado = false;
						}

						if (guardado) {
							//m_trx.commit();
							//m_trx.close();
							mTab.setValue("C_LocationPerResp_ID", locationResp.getC_Location_ID());
						}

					} catch (Exception e) {
						/*if (m_trx != null) {
							m_trx.rollback();
							m_trx.close();
						}*/
					} finally {
						//m_trx = null;
						//trxName = null;
					}
				}
			}
			else if (mTab.getValue("copyResponsible") != null && mTab.getValue("copyResponsible").equals(false)) {
				mTab.setValue("Nombre_Fam", "");
				mTab.setValue("Apellido1_Fam", "");
				mTab.setValue("Apellido2_Fam", "");
				mTab.setValue("TelParticular_Fam", "");
				mTab.setValue("RFC_Resp", "");
				mTab.setValue("FamiliarComCode", "PRN");

				int locationRespID = 0;
				if (mTab.getValue("C_LocationPerResp_ID") != null && mTab.get_ValueAsString("C_LocationPerResp_ID").length() > 0) {
					locationRespID = Integer.parseInt(mTab.get_ValueAsString("C_LocationPerResp_ID"));
					if (locationRespID > 0) {
						MLocation locationResp = new MLocation(ctx, locationRespID, null);

						locationResp.setAddress1(null);
						locationResp.setAddress2(null);
						locationResp.setCity(null);
						locationResp.setPostal(null);
						locationResp.setPostal_Add(null);
						locationResp.setC_Region_ID(0);
						locationResp.setC_City_ID(0);
						locationResp.setRegionName(null);
						locationResp.setAddress3(null);
						locationResp.setAddress4(null);
						locationResp.setEXME_TownCouncil_ID(0);
						locationResp.setNumExt(null);
						locationResp.setNumIn(null);
						locationResp.setAddress3(null);
						locationResp.setAddress4(null);

						//Trx m_trx = null; //lama: no se usa y no es requerida
						//String trxName = Trx.createTrxName("address");
						//m_trx = Trx.get(trxName, true);
						boolean guardado = true;

						try {
							if (!locationResp.save()) {
								guardado = false;
							}

							if (guardado) {
								//m_trx.commit();
								//m_trx.close();
								mTab.setValue("C_LocationPerResp_ID", locationResp.getC_Location_ID());
							}

						} catch (Exception e) {
							/*if (m_trx != null) {
								m_trx.rollback();
								m_trx.close();
							}**/
						} finally {
							//m_trx = null;
							//trxName = null;
						}
					}
				}
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Metodo para crear el rfc de la persona responsable
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void crearRFCResponsable(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			CalculoCURPyRFC calculo = new CalculoCURPyRFC();
			if (mTab.getValue("Nombre_Fam") != null) {
				calculo.setNombreFam(mTab.get_ValueAsString("Nombre_Fam"));
			}
			if (mTab.getValue("Apellido1_Fam") != null) {
				calculo.setApellidoPaternoFam(mTab.get_ValueAsString("Apellido1_Fam"));
			}
			if (mTab.getValue("Apellido2_Fam") != null) {
				calculo.setApellidoMaternoFam(mTab.get_ValueAsString("Apellido2_Fam"));
			}

			calculo.createRFCResp();

			if (calculo.getRfcResp() != null) {
				mTab.setValue("RFC_Resp", calculo.getRfcResp());
			}

			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Metodo para crear el rfc del paciente
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void crearRFCandCURP(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			CalculoCURPyRFC calculo = getCalculo(ctx, mTab);//Lama
			calculo.createRfcAndCurp();

			if (calculo.getCurp() != null) {
				mTab.setValue("Curp", calculo.getCurp());
			}

			if (calculo.getRfc() != null) {
				mTab.setValue("Rfc", calculo.getRfc());
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Metodo para crear solamente el CURP
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void crearCURP(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			CalculoCURPyRFC calculo = getCalculo(ctx, mTab);//Lama
			calculo.createOnlyCURP();

			if (calculo.getCurp() != null) {
				mTab.setValue("Curp", calculo.getCurp());
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}



	/**
	 * Metodo para crear el rfc del paciente
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void crearRFC(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			CalculoCURPyRFC calculo = getCalculo(ctx, mTab);//Lama
			calculo.createOnlyRFC();

			if (calculo.getRfc() != null) {
				mTab.setValue("Rfc", calculo.getRfc());
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Llena las propiedades para el calculo del RFC y CURP
	 * @param ctx
	 * @param mTab
	 * @return CalculoCURPyRFC
	 * @author lorena lama
	 */
	public CalculoCURPyRFC getCalculo(Properties ctx, GridTab mTab) {

		CalculoCURPyRFC calculo = new CalculoCURPyRFC();

		calculo.setCtx(ctx);

		if (mTab.getValue("Name") != null) {
			calculo.setNombre(mTab.get_ValueAsString("Name"));
		}
		if (mTab.getValue("Nombre2") != null) {
			calculo.setNombre2(mTab.get_ValueAsString("Nombre2"));
		}
		if (mTab.getValue("Apellido1") != null) {
			calculo.setApellidoPaterno(mTab.get_ValueAsString("Apellido1"));
		}
		if (mTab.getValue("Apellido2") != null) {
			calculo.setApellidoMaterno(mTab.get_ValueAsString("Apellido2"));
		}
		if (mTab.getValue("Sexo") != null) {
			calculo.setSexo(mTab.get_ValueAsString("Sexo"));
		}
		if (mTab.getValue("FechaNac") != null) {
			String fechaNacStr = mTab.get_ValueAsString("FechaNac");
			if (fechaNacStr != null) {
				try {
					calculo.setFechaNac(Constantes.getSdfFecha().format(Constantes.getSdfFechaInvertida().parse(fechaNacStr)));
				} catch (ParseException e) {
					log.log(Level.SEVERE, "CalculoCURPyRFC",e);
				}
			}
		}
		if (mTab.getValue("C_Region_Nac_ID") != null) {
			int regionID = 0;
			regionID = Integer.parseInt(mTab.get_ValueAsString("C_Region_Nac_ID"));

			MRegion region = new MRegion(ctx, regionID, null);
			if (region.getDescription() != null) {
				calculo.setEstadoNac(region.getDescription().trim());
			}
		}

		if (mTab.getValue("EXME_Paciente_ID") != null) {
			int pacienteID = 0;
			pacienteID = Integer.parseInt(mTab.get_ValueAsString("EXME_Paciente_ID"));
			calculo.setPacienteID(pacienteID);
		}

		return calculo;
	}

	public String actualizaDireccion(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		setCalloutActive(false);

		String msg = "";

		if (isCalloutActive())
			return "";

		setCalloutActive(true);

		if (mTab.getValue("EXME_Paciente_TS_ID") != null && mTab.getValue("EXME_Paciente_ID") != null) {

			int pacTS = Integer.parseInt(mTab.getValue("EXME_Paciente_TS_ID").toString());
			int pacID = Integer.parseInt(mTab.getValue("EXME_Paciente_ID").toString());

			MConfigEC configEC = MConfigEC.get(ctx, null);

			if (configEC != null && !configEC.isPrivado()) {
				MEXMEPaciente pac = new MEXMEPaciente(ctx, pacID, null);
				MEXMEPacienteTS pacTrabSoc = new MEXMEPacienteTS(ctx, pacTS, null);
				if (pac.getC_Location_ID() != pacTrabSoc.getC_Location_Perm_ID()) {
					pac.setEXME_Nacionalidad_ID(pacTrabSoc.getEXME_Nacionalidad_ID());
					if (pacTrabSoc.getC_Location_Perm_ID() != 0) {
						pac.setC_Location_ID(pacTrabSoc.getC_Location_Perm_ID());
					}
					pac.setTelParticular(pacTrabSoc.getTel_Perm());

					if (!pac.save()) {
						return msg;
					}
				}
			}
		}
		setCalloutActive(false);

		return msg;
	}

	/**
	 * Metodo para copiar los datos del paciente al familiar cercano.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author ldelagarza
	 */
	public String crearAFamiliar(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		String msg = "";
		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);


			if (mTab.getValue(MEXMEPaciente.COLUMNNAME_copyKin) != null && mTab.getValue(MEXMEPaciente.COLUMNNAME_copyKin).equals(true)) {

				Trx m_trx = null;
				String trxName = Trx.createTrxName("pacFam");
				m_trx = Trx.get(trxName, true);
				try {
					MEXMEPaciente paciente = new MEXMEPaciente(ctx, 0, null);
					paciente.setName(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_Name));
					paciente.setApellido1(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_Apellido1));
					paciente.setApellido2(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_Apellido2));
					paciente.setSexo(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_Sexo));
					paciente.setRfc(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_Rfc));
					paciente.setEdoCivil(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EdoCivil));
					paciente.setFechaNac(new Timestamp(Constantes.getSdfFechaInvertida().parse(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_FechaNac)).getTime()));
					paciente.setC_Location_ID(Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_C_Location_ID)));
					paciente.setEXME_PatientClass_ID(Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EXME_PatientClass_ID)));
					paciente.setEsAsegurado(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_EsAsegurado));
					paciente.setC_BPartner_ID(Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_C_BPartner_ID)));
					paciente.setDerechohabiente(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_Derechohabiente));
					paciente.setIsFactEspec(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_IsFactEspec));
					paciente.setIsPension(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_IsPension));
					paciente.setIsFactEspec(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_IsDonador));
					paciente.setIsFactEspec(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_IsReceptor));
					paciente.setIsPrinted(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_IsPrinted));
					paciente.setIsChangeLog(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_IsChangeLog));
					paciente.setIsRefer(mTab.getValueAsBoolean(MEXMEPaciente.COLUMNNAME_IsRefer));
					paciente.setcopyKin(false);
					paciente.setcopyResponsible(false);
					MLocation locationPac = new MLocation(ctx, Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_C_Location_ID)), null);
					MLocation locationKin = MLocation.copyFrom(ctx, locationPac, 0, null);

					boolean guardado = true;

					if (!paciente.save(trxName)) {
						guardado = false;
					}
					if (guardado) {
						if (!locationKin.save(trxName)) {
							guardado = false;
						}
					}
					if (guardado) {
						m_trx.commit();
						m_trx.close();
						mTab.setValue(MEXMEPaciente.COLUMNNAME_EXME_PacienteFam_ID, paciente.getEXME_Paciente_ID());
					}else{
						m_trx.rollback();
						m_trx.close();
					}

				} catch (Exception e) {
					if (m_trx != null) {
						m_trx.rollback();
						m_trx.close();
					}
					msg = Msg.getMsg(ctx, "Mandatory fields for patient");
					mTab.setValue(MEXMEPaciente.COLUMNNAME_copyKin, false);
				} finally {
					m_trx = null;
					trxName = null;
				}
			}		

			else if (mTab.getValue(MEXMEPaciente.COLUMNNAME_copyKin) != null && mTab.getValue(MEXMEPaciente.COLUMNNAME_copyKin).equals(false)) {
				if (mTab.getValue(MEXMEPaciente.COLUMNNAME_EXME_PacienteFam_ID)  != null &&  
						Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EXME_PacienteFam_ID)) != 0) {

					boolean borrado = true;

					Trx m_trx = null;
					String trxName = Trx.createTrxName("pacFamde");
					m_trx = Trx.get(trxName, true);
					try {
						if (mTab.getValue(MEXMEPaciente.COLUMNNAME_EXME_Paciente_ID) != null &&
								Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EXME_Paciente_ID)) != 0) {
							MEXMEPaciente paciente = new MEXMEPaciente(ctx, Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EXME_Paciente_ID)), null);
							paciente.setEXME_PacienteFam_ID(0);
							if (!paciente.save(trxName))
								borrado = false;
						} else {
							MEXMEPaciente paciente = new MEXMEPaciente(ctx, Integer.parseInt(mTab.get_ValueAsString(MEXMEPaciente.COLUMNNAME_EXME_PacienteFam_ID)), null);
							MLocation location = new MLocation(ctx, paciente.getC_Location_ID(), null);						
							if (!paciente.delete(true, trxName)) {
								borrado = false;
							}
							location.setIsActive(false);
							if (!location.save(trxName)) {
								borrado = false;
							}
						}
						if (borrado) {
							m_trx.commit();
							m_trx.close();
							mTab.setValue(MEXMEPaciente.COLUMNNAME_EXME_PacienteFam_ID, null);
						} else {
							if (m_trx != null) {
								m_trx.rollback();
								m_trx.close();
								mTab.setValue(MEXMEPaciente.COLUMNNAME_copyKin, true);
							}
						}
					} catch (Exception e) {
						if (m_trx != null) {
							m_trx.rollback();
							m_trx.close();
							mTab.setValue(MEXMEPaciente.COLUMNNAME_copyKin, true);
						}
					} finally {
						m_trx = null;
						trxName = null;
					}
				}
			}
			setCalloutActive(false); // inactivamos el callout
		}
		return msg;
	}


	/**
	 * Metodo para copiar los datos del paciente a la persona responsable.
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return void
	 * @author rsolorzano
	 */
	public void copyLocation(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);


			if (mTab.getValue("copyLocation") != null && mTab.getValue("copyLocation").equals(true)) {


				if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
					int locationID = 0;
					locationID = Integer.parseInt(mTab.get_ValueAsString("C_Location_ID"));
					MLocation locationPac = new MLocation(ctx, locationID, null);
					int locationMailID = 0;
					if (mTab.getValue("C_Location_Mail_ID") != null && mTab.get_ValueAsString("C_Location_Mail_ID").length() > 0) {
						locationMailID = Integer.parseInt(mTab.get_ValueAsString("C_Location_Mail_ID"));
					}
					MLocation locationMail = MLocation.copyFrom(ctx, locationPac, locationMailID, null);

					//Trx m_trx = null;//lama: no se usa y no es requerida
					//String trxName = Trx.createTrxName("address");
					//m_trx = Trx.get(trxName, true);
					boolean guardado = true;

					try {
						if (!locationMail.save()) {
							guardado = false;
						}

						if (guardado) {
							//m_trx.commit();
							//m_trx.close();
							mTab.setValue("C_Location_Mail_ID", locationMail.getC_Location_ID());
						}

					} catch (Exception e) {
						//if (m_trx != null) {
						//	m_trx.rollback();
						//	m_trx.close();
						//}
					} finally {
						//m_trx = null;
						//trxName = null;
					}
				}
			}
			else if (mTab.getValue("copyLocation") != null && mTab.getValue("copyLocation").equals(false)) {

				int locationMailID = 0;
				if (mTab.getValue("C_Location_Mail_ID") != null && mTab.get_ValueAsString("C_Location_Mail_ID").length() > 0) {
					locationMailID = Integer.parseInt(mTab.get_ValueAsString("C_Location_Mail_ID"));
					if (locationMailID > 0) {
						MLocation locationMail = new MLocation(ctx, locationMailID, null);

						locationMail.setAddress1(null);
						locationMail.setAddress2(null);
						locationMail.setCity(null);
						locationMail.setPostal(null);
						locationMail.setPostal_Add(null);
						locationMail.setC_Region_ID(0);
						locationMail.setC_City_ID(0);
						locationMail.setRegionName(null);
						locationMail.setAddress3(null);
						locationMail.setAddress4(null);
						locationMail.setEXME_TownCouncil_ID(0);
						locationMail.setNumExt(null);
						locationMail.setNumIn(null);
						locationMail.setAddress3(null);
						locationMail.setAddress4(null);

						//Trx m_trx = null;//lama: no se usa y no es requerida
						//String trxName = Trx.createTrxName("address");
						//m_trx = Trx.get(trxName, true);
						boolean guardado = true;

						try {
							if (!locationMail.save()) {
								guardado = false;
							}

							if (guardado) {
								//m_trx.commit();
								//m_trx.close();
								mTab.setValue("C_Location_Mail_ID", locationMail.getC_Location_ID());
							}

						} catch (Exception e) {
							//if (m_trx != null) {
							//	m_trx.rollback();
							//	m_trx.close();
							//}
						} finally {
							//m_trx = null;
							//trxName = null;
						}
					}
				}
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

	/**
	 * Validate phone value format
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String validateTelephone(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		String msg = "";

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);

			String phone = null;

			if (mField.getColumnName().equals(X_EXME_Paciente.COLUMNNAME_TelParticular)) {
				phone = mTab.get_ValueAsString(X_EXME_Paciente.COLUMNNAME_TelParticular);
			}
			// Familiar phone is also validate on beforesave
			else if (mField.getColumnName().equals(X_EXME_Paciente.COLUMNNAME_TelFamiliar)) {
				phone = mTab.get_ValueAsString(X_EXME_Paciente.COLUMNNAME_TelFamiliar);
			}
			if (!StringUtils.isBlank(phone)) {
				msg = Utilerias.validateTelephone(ctx, phone);
				if (!StringUtils.isBlank(msg)) {
					log.log(Level.SEVERE, msg);
				} else {
					msg = "";
				}
			}
			setCalloutActive(false);
		}
		return msg;
	}
	
	public void copyLocPolicyHolder(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (!isCalloutActive() || value != null) { // verificamos que el callout no se encuentre activo
			setCalloutActive(true);


			if (mTab.getValue("copyLocation") != null && mTab.getValue("copyLocation").equals(true)) {


				//if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
					MEXMEPaciente pac = new MEXMEPaciente(ctx, Integer.valueOf(mTab.get_ValueAsString("EXME_Paciente_ID")), null);
					MLocation locationPac = new MLocation(ctx, pac.getC_Location_ID(), null);
					int locationMailID = 0;
					if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
						locationMailID = Integer.parseInt(mTab.get_ValueAsString("C_Location_ID"));
					}
					MLocation locationMail = MLocation.copyFrom(ctx, locationPac, locationMailID, null);					

					boolean guardado = true;

					try {
						if (!locationMail.save()) {
							guardado = false;
						}

						if (guardado) {
							mTab.setValue("C_Location_ID", locationMail.getC_Location_ID());
						}

					} catch (Exception e) {
						//if (m_trx != null) {
						//	m_trx.rollback();
						//	m_trx.close();
						//}
					} finally {
						//m_trx = null;
						//trxName = null;
					}
				//}
			}
			else if (mTab.getValue("copyLocation") != null && mTab.getValue("copyLocation").equals(false)) {

				int locationMailID = 0;
				if (mTab.getValue("C_Location_ID") != null && mTab.get_ValueAsString("C_Location_ID").length() > 0) {
					locationMailID = Integer.parseInt(mTab.get_ValueAsString("C_Location_ID"));
					if (locationMailID > 0) {
						MLocation locationMail = new MLocation(ctx, locationMailID, null);

						locationMail.setAddress1(null);
						locationMail.setAddress2(null);
						locationMail.setCity(null);
						locationMail.setPostal(null);
						locationMail.setPostal_Add(null);
						locationMail.setC_Region_ID(0);
						locationMail.setC_City_ID(0);
						locationMail.setRegionName(null);
						locationMail.setAddress3(null);
						locationMail.setAddress4(null);
						locationMail.setEXME_TownCouncil_ID(0);
						locationMail.setNumExt(null);
						locationMail.setNumIn(null);
						locationMail.setAddress3(null);
						locationMail.setAddress4(null);

						boolean guardado = true;

						try {
							if (!locationMail.save()) {
								guardado = false;
							}

							if (guardado) {
								mTab.setValue("C_Location_ID", locationMail.getC_Location_ID());
							}

						} catch (Exception e) {
							log.log(Level.SEVERE, "copyLocPolicyHolder",e);
						} finally {
							//m_trx = null;
							//trxName = null;
						}
					}
				}
			}
			setCalloutActive(false); // inactivamos el callout
		}
	}

}