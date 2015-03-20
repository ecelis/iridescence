package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.struts.util.LabelValueBean;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * Modelo de Medicos <b>Modificado: </b> $Author: gisela $
 * <p>
 * <b>En :</b> $Date: 2006/08/23 16:04:49 $
 * <p>
 * 
 * @author Hassan Reyes
 * @version $Revision: 1.11 $
 */
public class MEXMEMedico extends X_EXME_Medico implements GridItem {


	/** serialVersionUID */
	private static final long serialVersionUID = -339775530187343449L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEMedico.class);
	/** obj array especialidades */
	private MEXMEEspecialidad[] especialidades;
	/** Para los queries que obtienen las citas (ActPaciente) del medico. */
//	private Timestamp desde = null;

	/**
	 * @param ctx
	 * @param EXME_Medico_ID
	 * @param trxName
	 */
	public MEXMEMedico(Properties ctx, int EXME_Medico_ID, String trxName) {
		super(ctx, EXME_Medico_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMedico(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Creamos un m&eacute;dico a partir de uno importado.
	 * 
	 * @param iMedico
	 *            El medico en la tabla de importaci&oacute;n
	 */
	public MEXMEMedico(X_I_EXME_Medico iMedico, String trxName) {
		this(iMedico.getCtx(), 0, trxName);

		setValue(iMedico.getValue());
		setName(iMedico.getName());
		setDescription(iMedico.getDescription());
		setApellido1(iMedico.getApellido1());
		setApellido2(iMedico.getApellido2());
		setSexo(iMedico.getSexo());
		setEstaSuspendido(iMedico.isEstaSuspendido());
		setFechaNac(iMedico.getFechaNac());
		setCelular(iMedico.getCelular());
		setEMail(iMedico.getEMail());
		setEsInterno(iMedico.isEsInterno());
		setEXME_TipoMedico_ID(iMedico.getEXME_TipoMedico_ID());
		setRfc(iMedico.getRfc());
		setCurp(iMedico.getCurp());
		setEdoCivil(iMedico.getEdoCivil());
		setTelParticular(iMedico.getTelParticular());
		setRadio(iMedico.getRadio());
		setFechaIngreso(iMedico.getFechaIngreso());
		setCedProfesional(iMedico.getCedProfesional());
		setCodSanidad(iMedico.getCodSanidad());
		setFechaTitulo(iMedico.getFechaTitulo());
		setEXME_Universidad_ID(iMedico.getEXME_Universidad_ID());
		setEstaCertifConsejo(iMedico.isEstaCertifConsejo());
		setFechaCertifConsejo(iMedico.getFechaCertifConsejo());
		setFechaVencimCertif(iMedico.getFechaVencimCertif());
		setEstaRecertificado(iMedico.isEstaRecertificado());
		setTieneIncentivo(iMedico.isTieneIncentivo());
		setTelConsultorio(iMedico.getTelConsultorio());
		//setEXME_CentroMedico_ID(iMedico.getEXME_CentroMedico_ID());//lhernandez
		setNoConsultorio(iMedico.getNoConsultorio());
		//setEXME_Turnos_ID(iMedico.getEXME_Turnos_ID()); //lhernandez
		//setIntervaloConsulta(iMedico.getIntervaloConsulta());//lhernandez. se cambia campo a EXME_Medico_Org 22/09/2010
		//setAD_User_ID(iMedico.getAD_User_ID());//lhernandez. se cambia campo a EXME_Medico_Org 22/09/2010
		//setM_Product_ID(iMedico.getM_Product_ID()); //lhernandez
		setC_Location_ID(iMedico.getC_Location_ID());
		setC_Location_Cons_ID(iMedico.getC_Location_Cons_ID());
	}

	/**
	 * Obtenemos todos los medicos que hagan consultas externas. Los medicos que
	 * esten asociados a una asistente son medicos de consulta externa.
	 * 
	 * @param ctx
	 * @param trxName
	 *            crea los MMedico con una transaccion.
	 * @return
	 * @deprecated only used in STRUTS clases, will be removed
	 */
	public static MEXMEMedico[] getMedicosConsultaExt(Properties ctx, String trxName) {

		if (ctx == null)
			return null;

		ArrayList<MEXMEMedico> list = new ArrayList<MEXMEMedico>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DISTINCT(ma.EXME_MEDICO_ID), m.name FROM EXME_MEDICOASIST ma ")
		.append("INNER JOIN EXME_Medico m ON (m.EXME_Medico_id = ma.EXME_Medico_id) ")
		.append("WHERE AND m.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append("ORDER BY m.Name");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int medico_ID = rs.getInt(1);
				MEXMEMedico medico = new MEXMEMedico(ctx, medico_ID, trxName);
				list.add(medico);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getMedicosConsultaExt", e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		MEXMEMedico[] medicos = new MEXMEMedico[list.size()];
		list.toArray(medicos);
		return medicos;
	}

	/**
	 * Ver getConsultaEjec(desde, facturadas, aditionalWare).
	 * 
	 * @param desde
	 * @param facturadas
	 * @return
	 *
	public List<MEXMEActPaciente> getConsultaEjecFact() {

		if (desde == null)
			desde = DB.getTimestampForOrg(getCtx());

		MEXMEActPaciente[] actsPaciente = getConsultaEjec(desde, true, null);
		List<MEXMEActPaciente> retValue =  Arrays.asList(actsPaciente);

		return retValue;
	}

	public List<MEXMEActPaciente> getConsultaEjecNoFact() {
		if (desde == null)
			desde = DB.getTimestampForOrg(getCtx());

		MEXMEActPaciente[] actsPaciente = getConsultaEjec(desde, false, null);
		List<MEXMEActPaciente> retValue = Arrays.asList(actsPaciente);

		return retValue;
	}*/

	/**
	 * Ver getConsultaEjec(desde, facturadas, aditionalWare).
	 * 
	 * @param desde
	 * @param facturadas
	 * @return
	 *
	public MEXMEActPaciente[] getConsultaEjec(Timestamp desde, boolean facturadas) {
		return getConsultaEjec(desde, facturadas, null);
	}

	/**
	 * Retorna todas las consultas ejecutadas a partir de una fecha.
	 * 
	 * @param desde
	 *            A partir de una fecha determinada al dia de hoy (fecha:hora).
	 *            (opcional)
	 * @param facturadas
	 *            Obtener las Facturadas = true, o las NO facturadas = false.
	 * @param aditionalWhere
	 *            Where adicional (empezar con AND o OR).
	 * @return
	 *
	public MEXMEActPaciente[] getConsultaEjec(Timestamp desde, boolean facturadas,
			String aditionalWhere) {
		ArrayList<MEXMEActPaciente> list = new ArrayList<MEXMEActPaciente>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_ActPaciente WHERE EXME_Medico_ID = ? "
			+ "AND AD_Client_ID = ? AND AD_Org_ID IN (0,?) ");

		if (desde != null) {
			sql.append("AND Fecha >= TO_DATE('" + sdf.format(desde)
			+ "','DD/MM/YYYY') ");
		}

		sql.append(" AND EXME_CitaMedica_ID IS NOT NULL "
			+ "AND EXME_ActPaciente_ID IN "
			+ "(SELECT EXME_ActPaciente_ID FROM EXME_ActPacienteIndH WHERE ");

		if (facturadas) {
			sql.append("isInvoiced = 'Y') ");
		} else {
			sql.append("isInvoiced = 'N') ");
		}

		if (aditionalWhere != null) {
			sql.append(aditionalWhere);
		}

		sql.append(" ORDER BY Fecha DESC");

		if (WebEnv.DEBUG) {
			s_log.log(Level.SEVERE, "SQL - " + sql + " - [Medico - "
					+ getEXME_Medico_ID() + "]");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",
		"EXME_ActPaciente"));
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_Medico_ID());
			pstmt.setInt(2, getAD_Client_ID());
			pstmt.setInt(3, Env.getContextAsInt(getCtx(), "#AD_Org_ID"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEActPaciente actPac = new MEXMEActPaciente(getCtx(), rs, get_TrxName());
				list.add(actPac);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getConsultaEjec", e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		MEXMEActPaciente[] actsPaciente = new MEXMEActPaciente[list.size()];
		list.toArray(actsPaciente);
		return actsPaciente;

	}*/

	/**
	 * Retornamos el nombre completo del paciente. Apellido1 + Apellido2 +
	 * Appellido3 + Name + Nombre2
	 * 
	 * @return Nombre completo del paciente.
	 */
	public String getFullName() {

		StringBuffer fullName = new StringBuffer();

		fullName.append((getApellido1() == null ? "" : getApellido1() + " "));
		fullName.append((getApellido2() == null ? "" : getApellido2() + " "));
		fullName.append((getName() == null ? "" : getName() + " "));

		return fullName.toString();
	}

	/**
	 * Obtenemos las especialidades de un medico
	 * 
	 * @param reQuery
	 * @return
	 */
	public List<MEXMEEspecialidad> getEspecialidades() {

		List<MEXMEEspecialidad> list = new ArrayList<MEXMEEspecialidad>();

		MEXMEEspecialidad[] especialidad = getEspecialidades(false);
		if (especialidad != null && especialidad.length > 0)
			list.addAll(Arrays.asList(especialidad));

		return list;
	}

	/**
	 * Obtenemos las especialidades de un medico
	 * 
	 * @param reQuery
	 * @return
	 */
	public List<MEXMEEspecialidad> getLstEspecialidad(String whereClause) {

		List<MEXMEEspecialidad> list = new ArrayList<MEXMEEspecialidad>();

		MEXMEEspecialidad[] especialidad;
		try {
			especialidad = getEspecialidades(whereClause);
			if (especialidad != null && especialidad.length > 0)
				list.addAll(Arrays.asList(especialidad));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Obtenemos las especialidades de un medico
	 * 
	 * @param reQuery
	 * @return
	 */
	public MEXMEEspecialidad[] getEspecialidades(boolean reQuery) {
		if (especialidades == null || especialidades.length == 0 || reQuery) {
			try {
				especialidades = getEspecialidades(null);
			} catch (SQLException e) {
				s_log.log(Level.SEVERE, "While getting specialties ... ", e);
			}
		}
		return especialidades;
	}

	/**
	 * Obtenemos las especialidades de un medico
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public MEXMEEspecialidad[] getEspecialidades(String whereClause) throws SQLException {
		ArrayList<MEXMEEspecialidad> list = new ArrayList<MEXMEEspecialidad>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Especialidad.* FROM EXME_Especialidad EXME_Especialidad ")
		.append(" INNER JOIN EXME_MedicoEsp me ON ")
		.append("(EXME_Especialidad.EXME_Especialidad_ID = me.EXME_Especialidad_ID) ")
		.append(" WHERE me.EXME_Medico_ID = ? ")
		.append(" AND EXME_Especialidad.IsActive = 'Y' ");

		if (whereClause != null)
			sql.append(whereClause);

		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MEXMEEspecialidad.Table_Name))
		.append(" ORDER BY EXME_Especialidad.Name ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_Medico_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEEspecialidad esp = new MEXMEEspecialidad(getCtx(), rs, get_TrxName());
				// MEspecialidad.setCtaPac(this);
				list.add(esp);
			}


		} catch (Exception e) {
			log.log(Level.SEVERE, "getEspecialidades", e);
		} finally{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		MEXMEEspecialidad[] especialidad = new MEXMEEspecialidad[list.size()];
		list.toArray(especialidad);

		return especialidad;
	}

	/**
	 * Para los queries que obtienen las citas (ActPaciente) del medico.
	 * 
	 * @param desde
	 *
	public void setDesde(Timestamp desde) {
		this.desde = desde;
	}

	/**
	 * WARNING: Creado para Interfaces Medsys.
	 * 
	 * @author hassan reyes
	 */
	public void setClientOrg(int ad_client_id, int ad_org_id) {
		setAD_Client_ID(ad_client_id);
		setAD_Org_ID(ad_org_id);
	}

	/**
	 * Metodo que regresa un HashMap con los horarios de entrada y salida del medico
	 * Key ("ID") = ID del medico.
	 * Key ("HoraEnt1Fs") = horario de entrada los fines de semana 
	 * Key ("HoraSal1Fs") = horario de salida los fines de semana
	 * Key ("HoraEnt1Es") = horario de entrada entre semana 1
	 * Key ("HoraSal1Es") = horario de salida entre semana 1 
	 * Key ("HoraEnt2Es") = horario de entrada entre semana 2 
	 * Key ("HoraSal2Es") = horario de salida entre semana 2 
	 * Key ("HoraEnt3Es") = horario de entrada entre semana 3 
	 * Key ("HoraSal3Es") = horario de salida entre semana 3
	 * 
	 * @author Julio Gutierrez
	 * @param medico
	 * @return Regresa un hashmap con los datos del horario del medico
	 */
	public static HashMap<String, String> getHorarioMap(Properties ctx, long medico) {
		HashMap<String, String> mp = new HashMap<String, String>();
		mp.put("ID", "" + medico);
		try {
			HashMap<String, String> rs = getHorario(ctx, medico);
			if (rs.size() >0 ) { // guardo los valores en las listas
				mp.put("HoraEnt1Fs", rs.get("HoraEnt1Fs"));
				mp.put("HoraSal1Fs", rs.get("HoraSal1Fs"));
				mp.put("HoraEnt1Es", rs.get("HoraEnt1Es"));
				mp.put("HoraSal1Es", rs.get("HoraSal1Es"));
				mp.put("HoraEnt2Es", rs.get("HoraEnt2Es"));
				mp.put("HoraSal2Es", rs.get("HoraSal2Es"));
				mp.put("HoraEnt3Es", rs.get("HoraEnt3Es"));
				mp.put("HoraSal3Es", rs.get("HoraSal3Es"));
			}


		} catch (Exception e) {
			// Error
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

		return mp;

	}

	/**
	 * Obtenemos el horario del medico
	 * 
	 * @param medico
	 *            El medico a obtener su horario
	 * @return Un resultset con el horario del medico
	 */
	public static HashMap<String,String> getHorario(Properties ctx, long medico) {
		// buscamos el horario del medico y su intervalo de consulta
		HashMap<String, String> ret = new HashMap<String, String>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql
		.append(
				" SELECT t.HoraEnt1Es, t.HoraSal1Es, t.HoraEnt1Fs, t.HoraSal1Fs, ")
				.append(
				" EXME_Medico_Org.IntervaloConsulta, t.HoraEnt2Es, t.HoraSal2Es, t.HoraEnt3Es, ")//lhernandez. el intervalo se obtiene de EXME_Medico_Org. 23/09/2010
				.append(" t.HoraSal3Es ")
				.append(" FROM EXME_Medico m LEFT JOIN ")
				.append(" EXME_Medico_Org ON(EXME_Medico_Org.EXME_Medico_ID = m.EXME_Medico_ID) ")
				.append(" LEFT JOIN EXME_Turnos t ON (EXME_Medico_Org.EXME_Turnos_ID = t.EXME_Turnos_ID) ")
				.append(" WHERE t.EXME_Turnos_ID = EXME_Medico_Org.EXME_Turnos_ID ").append(
				" AND m.EXME_Medico_ID = ? ").append(
				" AND t.IsActive = 'Y' AND m.IsActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Resultset para el horario del medico e intervalo
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, medico);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				//horario de fin de semana
				ret.put("HoraEnt1Fs",rs.getString("HoraEnt1Fs"));
				ret.put("HoraSal1Fs",rs.getString("HoraSal1Fs"));

				//primer horario entre semana
				ret.put("HoraEnt1Es",rs.getString("HoraEnt1Es"));
				ret.put("HoraSal1Es",rs.getString("HoraSal1Es"));

				//segundo horario entre semana
				ret.put("HoraEnt2Es",rs.getString("HoraEnt2Es"));
				ret.put("HoraSal2Es",rs.getString("HoraSal2Es"));

				//segundo horario entre semana
				ret.put("HoraEnt3Es",rs.getString("HoraEnt3Es"));
				ret.put("HoraSal3Es",rs.getString("HoraSal3Es"));
				ret.put("IntervaloConsulta",String.valueOf(rs.getInt("IntervaloConsulta")));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHorario", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}

//	private List<MEXMECitaMedica> citasFacturacion = new ArrayList<MEXMECitaMedica>();
//
//	public void setCitasFacturacion(List<MEXMECitaMedica> citasFacturacion) {
//		this.citasFacturacion = citasFacturacion;
//	}
//
//	public List<MEXMECitaMedica> getCitasFacturacion() {
//		return citasFacturacion;
//	}

//	public static BigDecimal getPrecioConsulta() {
//		return Env.ZERO;
//	}

	/**
	 * Intervalo de consulta
	 * 
	 * @param ctx
	 * @param medicoID
	 * @param especialidadID
	 * @param trxName
	 * @return
	 *
	public static int getIntervaloConsul(Properties ctx, int medicoID,
			int especialidadID, String trxName) {
		int intervalo = 0;

		//MEXMEMedico medico = new MEXMEMedico(ctx, medicoID, trxName);//lhernandez. se comenta línea. 22/09/2010
		MEXMEMedicoOrg medorg  = MEXMEMedicoOrg.getMedicoOrg(ctx, medicoID);//lhernandez. 22/09/2010
		if(medorg != null){//lhernandez. 22/09/2010
			intervalo = medorg.getIntervaloConsulta();//lhernandez. 22/09/2010
		}

		if (intervalo <= 0) {
			MEXMEMedicoEsp medicoEsp = MEXMEMedicoEsp.get(ctx, medicoID,
					especialidadID, trxName);
			if (medicoEsp != null && medicoEsp.getIntervaloConsulta() != null
					&& medicoEsp.getIntervaloConsulta().compareTo(Env.ZERO) > 0)
				intervalo = medicoEsp.getIntervaloConsulta().intValue();
		}

		if (intervalo <= 0) {
			MEXMEEspecialidad especialidad = new MEXMEEspecialidad(ctx, especialidadID,
					trxName);
			if (especialidad.getIntervaloConsulta() != null
					&& especialidad.getIntervaloConsulta().compareTo(Env.ZERO) > 0)
				intervalo = especialidad.getIntervaloConsulta().intValue();
		}

		if (intervalo <= 0) {
			intervalo = 30;
		}
		return intervalo;
	}*/

	/**
	 * Obtiene el intervalo de consulta de un medico, 
	 * del intervalo del medico, o del medico-especilidad, 
	 * o de la especialidad, o del minimo configurado
	 * 
	 * Lorena Lama
	 * @param ctx
	 * @param medicoID
	 * @param especialidadID
	 * @param trxName
	 * @return
	 *
	public static int getIntervaloConsulta(Properties ctx, int medicoID,int especialidadID, String trxName) {
		int intervalo = 0;

		if(medicoID<=0 || especialidadID<=0)
			return intervalo;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT CASE ")
			//lhernandez. El intervalo de consulta se toma de EXME_Medico_Org. 23/09/2010
			.append("WHEN nvl(MO.IntervaloConsulta,0) > 0 THEN MO.IntervaloConsulta ")
			.append("WHEN nvl(me.IntervaloConsulta,0) > 0 THEN me.IntervaloConsulta ")
			.append("WHEN nvl(e.intervaloconsulta,0) > 0 THEN e.intervaloconsulta ")
			.append("ELSE nvl(ec.durationMin,0) END AS intervalo ")
			.append("FROM EXME_medico m ")
			.append("INNER JOIN EXME_MEDICO_ORG MO ON ( m.EXME_MEDICO_ID = MO.EXME_MEDICO_ID ")
			//lhernandez. El intervalo de consulta se toma de EXME_Medico_Org. 23/09/2010
			.append("AND MO.AD_CLIENT_ID = ? AND MO.AD_ORG_ID = ? )  ")
			.append("INNER JOIN EXME_medicoesp me ON ( m.EXME_medico_ID = me.EXME_medico_ID ")
			.append("AND m.EXME_medico_ID = ? ) ")
			.append("INNER JOIN EXME_especialidad e ON ( me.EXME_especialidad_ID = e.EXME_especialidad_ID ")
			.append("AND e.EXME_especialIDad_ID = ? ) ")
			.append("LEFT JOIN EXME_configec ec ON ( ec.ad_client_ID = ? AND ec.ad_org_ID in (0, ? ) ")
			.append("AND ec.isActive = 'Y' )  ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "WHERE ", Table_Name, "m"));
			sql.append(" ORDER BY ec.AD_Org_ID desc");

			final List<Object> params = new ArrayList<Object>();
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
			params.add(medicoID);			
			params.add(especialidadID);
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
			
			intervalo = DB.getSQLValueEx(trxName, sql.toString(), params);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getIntervaloConsulta - SQL : " + sql, e);
		} 
		return intervalo;
	}*/
	
	public static int getIntervaloConsulta(Properties ctx, int medicoID, String trxName) {
		int intervalo = 0;

		if(medicoID<=0)
			return intervalo;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT CASE ")
			//lhernandez. El intervalo de consulta se toma de EXME_Medico_Org. 23/09/2010
			.append("WHEN nvl(MO.IntervaloConsulta,0) > 0 THEN MO.IntervaloConsulta ")
//			.append("WHEN nvl(me.IntervaloConsulta,0) > 0 THEN me.IntervaloConsulta ")			
			.append("ELSE nvl(ec.durationMin,0) END AS intervalo ")
			.append("FROM EXME_medico m ")
			.append("INNER JOIN EXME_MEDICO_ORG MO ON ( m.EXME_MEDICO_ID = MO.EXME_MEDICO_ID ")
			//lhernandez. El intervalo de consulta se toma de EXME_Medico_Org. 23/09/2010
			.append("AND MO.AD_CLIENT_ID = ? AND MO.AD_ORG_ID = ? )  ")
//			.append("INNER JOIN EXME_medicoesp me ON ( m.EXME_medico_ID = me.EXME_medico_ID ")
//			.append("AND m.EXME_medico_ID = ? ) ")
			.append("LEFT JOIN EXME_configec ec ON ( ec.ad_client_ID = ? AND ec.ad_org_ID in (0, ? ) ")
			.append("AND ec.isActive = 'Y' )  ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "WHERE ", Table_Name, "m"));
			sql.append(" AND m.EXME_medico_ID = ? ");
			sql.append(" ORDER BY ec.AD_Org_ID desc");

			final List<Object> params = new ArrayList<Object>();
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));					
			params.add(Env.getAD_Client_ID(ctx));
			params.add(Env.getAD_Org_ID(ctx));
			params.add(medicoID);
			
			intervalo = DB.getSQLValueEx(trxName, sql.toString(), params);

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getIntervaloConsulta - SQL : " + sql, e);
		} 
		return intervalo;
	}
	/**
	 * @param ctx
	 * @param sql
	 * @param trxName
	 * @return
	 *
	public static List<MEXMEMedico> getMedico(Properties ctx, String whereClause,
			String orderby, boolean block, String orderBlock, int blocksize,
			String trxName) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEMedico> list = new ArrayList<MEXMEMedico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT EXME_Medico.* FROM EXME_Medico ")
			.append("WHERE EXME_Medico.isActive = 'Y' ");

			if (whereClause != null && !whereClause.equals(""))
				sql.append(whereClause);

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			if (orderby != null) {
				sql.append(orderby);
			} else {
				sql.append(" ORDER BY EXME_Medico.Value ");
			}

			// separa en bloques el listado
			if (block) {
				Properties prop = WebEnv.getXPT_Properties();

				sql = new StringBuilder("SELECT * FROM ( ").append(sql).append(
				" ) WHERE ROWNUM <= ");

				sql.append(blocksize > 0 ? blocksize : prop
						.getProperty(WebEnv.NoRegistros));

				if (orderBlock != null && !orderBlock.equals("")) {
					sql.append(" ORDER BY ROWNUM ").append(orderBlock);
				}
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEMedico medico = new MEXMEMedico(ctx, rs, trxName);
				list.add(medico);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getMedico - SQL = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}*/

	/**
	 * 
	 * @return
	 *
	public MEXMECentroMedico getCentroMedico() {
		return new MEXMECentroMedico(getCtx(), getEXME_CentroMedico_ID(),
				get_TrxName());
	}

	/**
	 * Direccion particular
	 * 
	 * @param ctx
	 * @param medicoID
	 * @param especialidadID
	 * @param trxName
	 * @return
	 */
	public String getAddress() {
		String direccion = null;

		MLocation direc = new MLocation(getCtx(), getC_Location_ID(),
				get_TrxName());

		direccion = (direc.getAddress1() == null ? ""
				: (direc.getAddress1() + ", "))
				+ (direc.getAddress2() == null ? ""
						: (direc.getAddress2() + ", "))
						+ (direc.getAddress3() == null ? ""
								: (direc.getAddress3() + ", "))
								+ (direc.getAddress4() == null ? ""
										: (direc.getAddress4() + ", "))
										+ (direc.getPostal() == null ? "" : (direc.getPostal() + ", "))
										+ (direc.getCity() == null ? "" : (direc.getCity() + ", "));

		if (direc.getTownCouncil() != null)
			direccion += direc.getTownCouncil().getName() == null ? "" : (direc
					.getTownCouncil().getName() + ", ");
		direccion += (direc.getRegionName() == null ? "" : (direc
				.getRegionName() + ", "))
				+ (direc.getCountryName() == null ? "" : (direc
						.getCountryName()));

		return direccion;
	}

	/**
	 * Obtiene el medico a partir de la llave de busqueda
	 * @param ctx
	 * @param value
	 * @param whereClause
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static MEXMEMedico getByValue(Properties ctx, String value,
			String whereClause, String orderBy, String trxName) {
		
		return new Query(ctx, Table_Name, " UPPER(Value) = UPPER(?) "+whereClause, null)//
		.setParameters(value)//
		.setOrderBy(orderBy) //
		.addAccessLevelSQL(true)//
		.first();

//		MEXMEMedico retValue = null;
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT * FROM EXME_Medico EXME_Medico WHERE UPPER(Value) = UPPER(?) ");
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		if (whereClause != null) {
//			sql.append(whereClause);
//		}
//		if (orderBy != null) {
//			sql.append(" order by ").append(orderBy);
//		}
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setString(1, value);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = new MEXMEMedico(ctx, rs, trxName);
//			}
//		}
//		catch (SQLException sqle) {
//			s_log.log(Level.SEVERE, "Error: " + sqle.getMessage() + " " + sql, sqle);
//		}
//		finally {
//			DB.close(rs, pstmt);
//			pstmt = null;
//			rs = null;
//		}
//		return retValue;
	}

	/***************************************************************************
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		log.fine("");
		
		if (newRecord) {
			setAD_User_ID(0);// user cero
		}
		
		// No Partner Info - set Template
		MEXMEMejoras mejoras = MEXMEMejoras.get(getCtx(), get_TrxName());
		if (newRecord || is_ValueChanged(COLUMNNAME_Name) || is_ValueChanged(COLUMNNAME_Apellido1) || is_ValueChanged(COLUMNNAME_Apellido2)) {
			if (mejoras != null && mejoras.isNombrePorApellido())
				setNombre_Med(getApellido1() + " " + getApellido2() + " " + getName());
			else
				setNombre_Med(getName() + " " + getApellido1() + " " + (StringUtils.isEmpty(getApellido2()) ? "" : getApellido2()));
		}


		//Obtener la configuracion por pais
		MEXMEI18N inter = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);

		/* Si la configuracion no esta marcada para validar el medico o la configuracion es nula.
		 * No validar tampoco el telefono ni el fax ni el medico para Mexico. Revisado con Helio
		 * A raiz de adecuaciones al wizard de medicos en el telefono y fax. Jesus Cantu
		 */
		if ((inter != null && !inter.isnoValidatePhysician()) || inter == null) {
		
			// if we are not in demonstration mode, then apply validations
			if(!Ini.APP_MODE_DEMO.equals(Ini.getProperty(Ini.P_APPLICATION_MODE))) {
				if (newRecord || is_ValueChanged(COLUMNNAME_TelParticular)) {
					String msg = Utilerias.validateTelephone(getCtx(), getTelParticular());
					if (msg != null) {
						log.saveError(" ", msg);
						return false;
					}	
				}
	
				/** Validacion de Telefono Particular */
	
				int p;		// Length of Telephone Number
				String phone = getTelParticular();
	
				if(phone == null || phone.equals("")) { // El telefono debe ser obligatorio
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhone"));
					return false;
				} else {
					p = phone.length();
				}
	
				if (p != 10) { // El telefono no puede tener mas de 10 digitos
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhoneDigit"));
					return false;
				}
	
				if(!StringUtils.isNumeric(phone)){
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhoneNumeric"));
					return false;
				}
	
				if (maximoRepetido(phone) > 7) { // No puede haber mas de 7 digitos repetidos en un numero telefonico
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhoneRepeat"));
					return false;
				}
	
				/** Validacion de su area */
	
				if (phone.startsWith("555")) { // El area del telefono no puede tener 555
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhoneArea"));
					return false;
				}
	
				if (phone.startsWith("0") || phone.startsWith("1")) { // El area no puede empezar con 0 o 1
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.homePhoneAreaStart"));
					return false;
				}
	
				/** Validacion del Fax */
	
				int f;		// Length of Fax Number
				String fax = getFaxNumber();
	
				if(fax == null || fax.equals("")) { // El fax debe ser obligatorio
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.fax"));
					return false;
				} else {
					f = fax.length();
				}
	
				if (f != 10) { // El fax no puede tener mas de 10 digitos
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.faxDigit"));
					return false;
				}
	
	
				if(!StringUtils.isNumeric(fax)){
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.faxNumeric"));
					return false;
				}
	
				/** Validacion de caracteres especiales aceptados en el nombre y apellido del Medico */
				String nombre = getName();
				if(StringUtils.isNotBlank(nombre)) {
					if (!caracteresPatron(nombre)) {
						log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.doctorName"));
						return false;
					}
				}
	
				String app1 = getApellido1();
				if(StringUtils.isNotBlank(app1)) {
					if (!caracteresPatron(app1)) {
						log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.doctorLastName"));
						return false;
					}
				}
	
				
				/** Validacion de NPI */
	
				/**
				 * Check if the NPI Luhn check-digit is correct.
				 * 
				 * @see {@link https://www.claredi.com/download/npi_checkdigit.cs} 
				 * 
				 */
	
				int tmp;	// Current digit of interest
				int sum;	// Hash
				int i;		// Length of NPI
				int j;		// Even-odd bit
	
				String npi = getValue(); // The 10 or 10+5 digit NPI number that is to be validated.
				if (!StringUtils.isNumeric(npi)) {
					log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.npiNumeric"));
					return false;
				}
	
				if (getValue() != null) {
					i = npi.length();
					if ((i == 15) && (npi.startsWith("80840"))) {
						sum = 0;
					} else if (i == 10) {
						sum = 24;
					} else {
						log.saveError(null, Utilerias.getMsg(getCtx(), "msj.validate.npiDigit"));
						return false;
					}
	
					j = 0;
					while (i != 0) {
	
						tmp = npi.charAt(i - 1) - '0';
						if ((j++ % 2) != 0) {
							if ((tmp <<= 1) > 9) {
								tmp -= 10;
								tmp++;
							}
						}
						sum += tmp;
						i--;
					}
	
					if ((sum % 10) == 0) {
						return true;
					} else {
						log.saveError(null, Utilerias.getAppMsg(getCtx(), "msj.validate.npi"));
						return false;
					}
				}
			}
		} // End if no validar medico.

		return true;

	} // beforeSave

	
	public static int maximoRepetido(String cadena) {
		int maxCount = 0;
		int repeticiones;
		for (int i = 0; i < cadena.length(); i++) {
			repeticiones = repeticionesPatron(cadena, String.valueOf(cadena.charAt(i)));
			if (repeticiones > maxCount) {
				maxCount = repeticiones;
			}
		}
		return maxCount;
	}

	public static int repeticionesPatron(String cadena, String patron) {
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(cadena);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}
	
	public static boolean caracteresPatron(String cadena) {
		Pattern p = Pattern.compile("[\\'\\-[a-zA-Z]\\s]*");
		Matcher m = p.matcher(cadena);
		return m.matches();
	}

	public int nivelControl()
	{
		if(getItemClass()!=null)
		{
			if(getItemClass().equalsIgnoreCase(ITEMCLASS_Schedule_II) || getItemClass().equalsIgnoreCase(ITEMCLASS_Misc_Controlled_2))
				return 2;
			if(getItemClass().equalsIgnoreCase(ITEMCLASS_Schedule_III))
				return 3;
			if(getItemClass().equalsIgnoreCase(ITEMCLASS_Misc_Controlled_1))
				return 1;
		}
		return 6;
	}
	/**
	 * @author Gustavo
	 * Obtener el Medico dependiendo de ad_user_id
	 * 
	 * *
	public static MEXMEMedico getByUser(Properties ctx, int AD_User_ID, String trxName) {

		MEXMEMedico retValue = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Medico.* ")
		.append(" FROM EXME_Medico EXME_Medico ")
		.append(" INNER JOIN EXME_MEDICO_ORG MO ON (EXME_Medico.EXME_MEDICO_ID = MO.EXME_MEDICO_ID) ")//lhernandez. el usuario se toma de EXME_Medico_Org 23/09/2010
		.append(" WHERE MO.AD_User_ID = ? ");//lhernandez. el usuario se toma de EXME_Medico_Org 23/09/2010

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name, "MO"));		

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_User_ID);			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMEMedico(ctx, rs, trxName);
			}

		} catch (SQLException sqle) {
			s_log.log(Level.SEVERE, sql.toString(), sqle);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return retValue;
	}*/

	/**
	 *  Devolvemos el nombre de un medico. Este se conforma de Apellido1 + " " +
	 *  Apellido2 + " " + Name
	 *
	 *@param  medicoId    El identificador del medico
	 *@return             Un valor String con el nombre del medico
	 *@throws  Exception  en caso de ocurrir un error al procesar la consulta.
	 */
	public static String nombreMedico(Properties ctx, long medicoId) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// NVL(Nombre_Med,Apellido1||' '||Apellido2||' '||Name)
		sql.append("SELECT Nombre_Med ");
		sql.append("FROM EXME_Medico ");
		sql.append("WHERE EXME_Medico_ID=? ");
//		sql.append("AND isActive='Y' ");
		//sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", Table_Name));

		return DB.getSQLValueString(null, sql.toString(), medicoId);
	}

	/**
	 * Metodo que nos trae <strong>Nombre </strong> y <strong> EXME_Medico_ID </strong>
	 * de todas los medicos existentes para poder desplegarlos en un combo
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getAllMedicos(Properties ctx, String trxName)  {

		List<LabelValueBean> lstMedicos = new ArrayList<LabelValueBean>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Medico_ID, nombre_med ")
		.append(" FROM EXME_Medico WHERE isActive = 'Y' ");		

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY nombre_med");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean nac =
					new LabelValueBean(
							rs.getString("nombre_med"),
							String.valueOf(rs.getLong("EXME_Medico_ID"))
					);
				lstMedicos.add(nac);
			}


		} catch (Exception e) {
			
		} finally {
			DB.close(rs, pstmt);
		}
		return lstMedicos;	
	}

	public static HashMap<String, Integer> getNPI(Properties ctx, String trxName) {

		HashMap<String, Integer> lstMedicos = new HashMap<String, Integer>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Medico_ID, value ").append(" FROM EXME_Medico WHERE isActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Medico"));
		sql.append(" ORDER BY nombre_med");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lstMedicos.put(rs.getString("value"), rs.getInt("EXME_Medico_ID"));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstMedicos;
	}
	/**
	 *
	 *
	public static int getBuscarUsuario(Properties ctx) /*throws Exception*{
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_Medico_Org.EXME_Medico_ID ") 
		//lhernandez. el usuario se guarda en EXME_Medico_Org. 23/09/2010
		.append(" FROM EXME_Medico_Org " )
		.append(" WHERE EXME_Medico_Org.AD_User_ID = ? ");

		sql.append( MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name));
		return DB.getSQLValue(null, sql.toString(), Env.getAD_User_ID(ctx));

	}*/
	
	
	public static void getDocuments(Properties ctx, IndexWriter w) {
		if(w == null || ctx == null){
			return;
		}
		final StringBuilder st = new StringBuilder();
		st.append("select m.EXME_Medico_ID, m.value, NVL(m.nombre_med, m.value) as nombre_med from EXME_Medico m ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name, "m"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Document doc = new Document();
				doc.add(new Field("label", Utilerias.getMessage(ctx, null, "msj.medico"), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("tabla", MEXMEMedico.Table_Name, Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("id", rs.getString("EXME_Medico_ID"), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("value", rs.getString("value").toUpperCase(), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("name", rs.getString("nombre_med").toUpperCase(), Field.Store.YES, Field.Index.ANALYZED));
				w.addDocument(doc);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
	}


	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success){
			return success;
		}
		boolean status = success;

		if(success) {
			MLocation loc = new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
			//we need to set to System/* because MEXME_Medico has Client To System table level access
			loc.setAD_Client_ID(0);
			loc.setAD_Org_ID(0);
			status = loc.save();
			
			if(status && newRecord){
				MEXMEConfigMedico configMed = MEXMEConfigMedico.getConfig(getCtx(), getEXME_Medico_ID(), null);
				if(configMed ==  null){
					configMed = new MEXMEConfigMedico(getCtx(), 0, get_TrxName());
					configMed.setEXME_Medico_ID(getEXME_Medico_ID());
					configMed.setSimpleWindow(true);
					status = configMed.save();
				}
			}
			
		}

		return status;
	}


	/** 
	 * Regresa una lista de medicos
	 * filtrados por los parametros
	 * si no recibe alguno de los parametros o recibe 0 
	 * ignora la condicion
	 * @return 
	 */
	public static List<MEXMEMedico> getMedicoxDireccion(Properties ctx, String name, String lastName, int townCouncilId, int regionId, int countryId , String trxName){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<MEXMEMedico> lstMedicoPac = new ArrayList<MEXMEMedico>();
		final List<Object> params = new ArrayList<Object>();
		try {
			sql.append(" SELECT EXME_MEDICO.EXME_MEDICO_ID ");
			sql.append(" FROM EXME_MEDICO ");
			sql.append(" LEFT JOIN C_LOCATION ON (C_LOCATION.C_LOCATION_ID=EXME_MEDICO.C_LOCATION_ID) ");
			sql.append(" LEFT JOIN C_COUNTRY ON (C_LOCATION.C_COUNTRY_ID=C_COUNTRY.C_COUNTRY_ID) ");
			sql.append(" LEFT JOIN C_REGION ON (C_LOCATION.C_REGION_ID=C_REGION.C_REGION_ID) ");
			sql.append(" LEFT JOIN EXME_TOWNCOUNCIL ON (C_LOCATION.EXME_TOWNCOUNCIL_ID=EXME_TOWNCOUNCIL.EXME_TOWNCOUNCIL_ID) ");
			sql.append(" WHERE EXME_MEDICO.ISACTIVE='Y' ");

			if (StringUtils.isNotBlank(name)) {
				sql.append(" AND UPPER(EXME_MEDICO.NAME) LIKE UPPER(?) ");
				params.add(name);
			}
			if (StringUtils.isNotBlank(lastName)) {
				sql.append(" AND UPPER(EXME_MEDICO.APELLIDO1) LIKE UPPER(?) ");
				params.add(lastName);
			}
			if (townCouncilId > 0) {
				sql.append(" AND C_LOCATION.EXME_TOWNCOUNCIL_ID=? ");
				params.add(townCouncilId);
			}
			if (regionId > 0) {
				sql.append(" AND C_LOCATION.C_REGION_ID=? ");
				params.add(regionId);
			}
			if (countryId > 0) {
				sql.append(" AND C_LOCATION.C_COUNTRY_ID=? ");
				params.add(countryId);
			}

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lstMedicoPac.add(new MEXMEMedico(ctx, rs.getInt(1), trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstMedicoPac;
	}

	public static String[] doctor = { MEXMEMedico.COLUMNNAME_Nombre_Med,
		MEXMEMedico.COLUMNNAME_Value };
	
//	private MEXMEMedEstServ medEstServ = null;
//    private boolean selected = false;
    
    /**
     * Estación de Servicio por Médico
     * @return
     *
	public MEXMEMedEstServ getMedEstServ() {
		if (medEstServ == null) {
			medEstServ = MEXMEMedEstServ.get(getCtx(), getEXME_Medico_ID(), true, get_TrxName());
		}
		return medEstServ;
	}*/

	/**
	 * Está Seleccionado
	 * @return
	 */
//	public boolean isSelected() {
//		return selected;
//	}

	/**
	 * Está Seleccionado
	 * 
	 * @param selected
	 */
//	public void setSelected(boolean selected) {
//		this.selected = selected;
//	}

	/**
	 * Está trabajando hoy
	 * 
	 * @return
	 *
	public boolean isWorkingToday() {
		boolean retValue = false;
		MEXMEMedEstServ mes = getMedEstServ();
		if (mes != null) {
			DateTime dt = new DateTime(DB.getDateForOrg(getCtx()));
			int dow = dt.getDayOfWeek();
			switch (dow) {
			case 1:
				retValue = mes.isMonday();
				break;
			case 2:
				retValue = mes.isTuesday();
				break;
			case 3:
				retValue = mes.isWednesday();
				break;
			case 4:
				retValue = mes.isThursday();
				break;
			case 5:
				retValue = mes.isFriday();
				break;
			case 6:
				retValue = mes.isSaturday();
				break;
			case 7:
				retValue = mes.isSunday();
				break;
			}
		}
		return retValue;
	}*/
	
	/**
	 * Citas del dia
	 * 
	 * @param ctx
	 *            Contexto
	 * @param date
	 *            Fecha de Citas
	 * @return
	 */
	public int getAppointments(Properties ctx, Date date) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sql.append("  count(*) ");
		sql.append("FROM ");
		sql.append("  EXME_CitaMedica ");
		sql.append("WHERE ");
		sql.append("  EXME_Medico_ID = ? AND ");
		sql.append("  TO_CHAR(FechaHrCita,'dd/MM/yyyy' ) = ? AND ");
		if (DB.isOracle()) {
			sql.append("  Estatus <> 5 ");
		} else if (DB.isPostgreSQL()) {
			sql.append("  Estatus <> '5' ");
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CitaMedica"));
		return DB.getSQLValue(null, sql.toString(), getEXME_Medico_ID(), Constantes.getSdfFecha().format(date));
	}
	
	/**
	 * Metodo que regresa un HashMap con los horarios de entrada y salida del medico
	 * Key ("ID") = ID del medico.
	 * Key ("HoraEnt1Fs") = horario de entrada los fines de semana 
	 * Key ("HoraSal1Fs") = horario de salida los fines de semana
	 * Key ("HoraEnt1Es") = horario de entrada entre semana 1
	 * Key ("HoraSal1Es") = horario de salida entre semana 1 
	 * Key ("HoraEnt2Es") = horario de entrada entre semana 2 
	 * Key ("HoraSal2Es") = horario de salida entre semana 2 
	 * Key ("HoraEnt3Es") = horario de entrada entre semana 3 
	 * Key ("HoraSal3Es") = horario de salida entre semana 3
	 * 
	 * @author Julio Gutierrez
	 * @param medico
	 * @return Regresa un hashmap con los datos del horario del medico
	 */
	public static HashMap<String, String> getHorarioMap(long medico) {
		HashMap<String, String> mp = new HashMap<String, String>();
		mp.put("ID", "" + medico);
		try {
			HashMap<String, String> rs = getHorario(medico);
			if (rs.size() >0 ) { // guardo los valores en las listas
				mp.put("HoraEnt1Fs", rs.get("HoraEnt1Fs"));
				mp.put("HoraSal1Fs", rs.get("HoraSal1Fs"));
				mp.put("HoraEnt1Es", rs.get("HoraEnt1Es"));
				mp.put("HoraSal1Es", rs.get("HoraSal1Es"));
				mp.put("HoraEnt2Es", rs.get("HoraEnt2Es"));
				mp.put("HoraSal2Es", rs.get("HoraSal2Es"));
				mp.put("HoraEnt3Es", rs.get("HoraEnt3Es"));
				mp.put("HoraSal3Es", rs.get("HoraSal3Es"));
			}
			
			
		} catch (Exception e) {
			// Error
			s_log.log(Level.SEVERE,e.getMessage(), e);
		} 

		return mp;

	}
	
	/**
	 * Obtenemos el horario del medico
	 * 
	 * @param medico
	 *            El medico a obtener su horario
	 * @return Un resultset con el horario del medico
	 */
	public static HashMap<String,String> getHorario(long medico) {
		// buscamos el horario del medico y su intervalo de consulta
		HashMap<String, String> ret = new HashMap<String, String>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
		sql
				.append(
						" SELECT t.HoraEnt1Es, t.HoraSal1Es, t.HoraEnt1Fs, t.HoraSal1Fs, ")
				.append(
						" m.IntervaloConsulta, t.HoraEnt2Es, t.HoraSal2Es, t.HoraEnt3Es, ")
				.append(" t.HoraSal3Es ")
				.append(" FROM EXME_Medico m LEFT JOIN ")
				.append(
						" EXME_Turnos t ON (m.EXME_Turnos_id = t.EXME_Turnos_id) ")
				.append(" WHERE t.EXME_Turnos_ID = m.EXME_Turnos_ID ").append(
						" AND m.EXME_Medico_ID = ? ").append(
						" AND t.IsActive = 'Y' AND m.IsActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Resultset para el horario del medico e intervalo
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, medico);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
                //horario de fin de semana
				ret.put("HoraEnt1Fs",rs.getString("HoraEnt1Fs"));
				ret.put("HoraSal1Fs",rs.getString("HoraSal1Fs"));

                //primer horario entre semana
				ret.put("HoraEnt1Es",rs.getString("HoraEnt1Es"));
				ret.put("HoraSal1Es",rs.getString("HoraSal1Es"));

                //segundo horario entre semana
				ret.put("HoraEnt2Es",rs.getString("HoraEnt2Es"));
				ret.put("HoraSal2Es",rs.getString("HoraSal2Es"));
				
				 //segundo horario entre semana
				ret.put("HoraEnt3Es",rs.getString("HoraEnt3Es"));
				ret.put("HoraSal3Es",rs.getString("HoraSal3Es"));
				ret.put("IntervaloConsulta",new Integer(rs.getInt("IntervaloConsulta")).toString());
				

			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getHorario", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ret;
	}
	
	private MEXMEMedicoOrg medicoOrg = null;

	/**
	 * Medico organizacion
	 * 
	 * @return
	 */
	public MEXMEMedicoOrg getMedicoOrg() {
		if (medicoOrg == null) {
			medicoOrg = MEXMEMedicoOrg.getMedicoOrg(getCtx(), getEXME_Medico_ID());
		}
		return medicoOrg;
	}

	/**
	 * Asignar Medico organizacion
	 * 
	 * @param medicoOrg
	 */
//	public void setMedicoOrg(MEXMEMedicoOrg medicoOrg) {
//		this.medicoOrg = medicoOrg;
//	}

	/**
	 * Maximo de citas por dias
	 * 
	 * @return
	 */
	public BigDecimal getMaxCitas() {
		BigDecimal retValue = new BigDecimal(-1);
		MEXMEMedicoOrg medicoOrg = getMedicoOrg();
		if (medicoOrg != null) {
			retValue = medicoOrg.getMaxCitas();
		}

		return retValue;
	}

	/**
	 * Traslapar citas
	 * 
	 * @return
	 */
	public boolean isOverlapAppointments() {
		boolean retValue = true;
		MEXMEMedicoOrg medicoOrg = getMedicoOrg();
		if (medicoOrg != null) {
			retValue = medicoOrg.isOverlapAppointments();
		}
		return retValue;
	}

	/**
	 * Fecha tentativa para otorgar una cita
	 */
	private Timestamp fechaTentativa = null;

	/**
	 * Fecha tentativa para otorgar una cita
	 * 
	 * @return
	 */
	public Timestamp getFechaTentativa() {
		return fechaTentativa;
	}

	/**
	 * Fecha tentativa para otorgar una cita
	 * 
	 * @param fechaTentativa
	 */
	public void setFechaTentativa(Timestamp fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getEXME_Medico_ID());
		}
		return idColumn;
	}
	
	@Override
	public String[] getColumns() {
		return new String[]{"value", "nombre_Med", "noConsultorio", "fechaTentativa"};
	}
	
	private IDColumn idColumn = null; 
	
	
	/**
	 * 
	 * @param ctx
	 * @param id
	 * @param trxName
	 * @return
	 */
	public static MEXMEMedico getMedicoByName(Properties ctx, String id, String trxName) {

		MEXMEMedico retValue = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_Medico EXME_Medico WHERE Nombre_Med = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMEMedico(ctx, rs, trxName);
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return retValue;
	}
	
	
	/***/
//	public static List<LabelValueBean> getLstEspecialidades(Properties ctx, int id, String trxName){
//		return getEspecialidades(ctx, id, "value", trxName);
//	}
	
	/**
	 * Lista las especialidades de un medico y
	 * devuelve el id de la especialidad como value, en la lista labelvaluebean
	 * @param ctx
	 * @param id
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getEspecialidades(Properties ctx, int id, String trxName){
		return getEspecialidades(ctx, id, "exme_especialidad_id", trxName);
	}
	
	public static List<LabelValueBean> getEspecialidades(Properties ctx, int EXME_Medico_ID, String value, String trxName){
		final List<LabelValueBean> lstEspecialidades = new ArrayList<LabelValueBean>();
		final StringBuilder sql = getSpecialtiesSQL();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Medico_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean esp = new LabelValueBean(
						// label
						rs.getString("name"),
						// value
						rs.getString(StringUtils.isEmpty(value) ? "value" : value));
				lstEspecialidades.add(esp);
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lstEspecialidades;
	}
	
	private static StringBuilder getSpecialtiesSQL(){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("select esp.* from exme_medicoesp medesp ");
		sql.append("inner join exme_especialidad esp on (medesp.exme_especialidad_id = esp.exme_especialidad_id) ");
		sql.append("where exme_medico_id = ?");
		return sql;
	}
	
	
	/**
	 * Lista las especialidades de un medico y
	 * devuelve la lista
	 * @param ctx
	 * @param EXME_Medico_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEspecialidad> getLstSpecialties(Properties ctx, int EXME_Medico_ID, String trxName){
		final List<MEXMEEspecialidad> lstEspecialidades = new ArrayList<MEXMEEspecialidad>();
		final StringBuilder sql = getSpecialtiesSQL();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Medico_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				lstEspecialidades.add(new MEXMEEspecialidad(ctx, rs, null));
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, "Error: " + ex.getMessage() + " " + sql, ex);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lstEspecialidades;
		
	}
	
	/**
	 * Obtiene los médicos que han realizado una prescripcion para la cuenta paciente.
	 * @param exmeCtaPacId
	 * 			cuenta paciente
	 * @return List<KeyNamePair>
	 */
	public static List<KeyNamePair> getDoctorsFromPrescRX(Properties ctx, int exmeCtaPacId){
		final List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append(" SELECT DISTINCT EXME_MEDICO.EXME_MEDICO_ID, EXME_MEDICO.NOMBRE_MED ");
		sql.append(" FROM EXME_PRESCRX ");//FIXME: SERVICIOS REUTILIZAR RX
		sql.append(" INNER JOIN EXME_MEDICO ON (EXME_MEDICO.EXME_MEDICO_ID = EXME_PRESCRX.EXME_MEDICO_ID ");
		sql.append(" AND EXME_PRESCRX.EXME_CTAPAC_ID=? ) ");
		sql.append(" WHERE EXME_MEDICO.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPrescRX.Table_Name));
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exmeCtaPacId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, ex.getLocalizedMessage());
		} finally {
			DB.close(rs, pstmt);

		}
		return list;
	}
	
	/**
	 * Obtiene los médicos que han realizado una solicitud o prescripcion de una cuenta
	 * @param exmeCtaPacId
	 * 			cuenta paciente
	 * @return List<LabelValueBean>
	 *
	public static List<MEXMEMedico> getPhysicianByCtaPac(Properties ctx, int recordID, String type){
		List<MEXMEMedico> list = new ArrayList<MEXMEMedico>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		boolean union = false;
		
		sql.append(" SELECT EXME_Medico_ID FROM (");
//		if (type.equalsIgnoreCase(MEXMECtaPacMed.TYPE_Admitting)){
//			sql.append(" SELECT EXME_Medico_ID, Created ")
//			.append(" FROM EXME_CtaPac ")
//			.append(" WHERE EXME_CtaPac_ID= ? ");
//			union = true;
//		}else if (type.equalsIgnoreCase(MEXMECtaPacMed.TYPE_Attending)){
//			sql.append(" SELECT EXME_Medico2_ID EXME_Medico_ID, Created ")
//			.append(" FROM EXME_CtaPac ")
//			.append(" WHERE EXME_CtaPac_ID= ? ");
//			union = true;
//		}else if (type.equalsIgnoreCase(MEXMECtaPacMed.TYPE_Consultant)){
//			sql.append(" SELECT APH.EXME_Medico_ID, MAX(APH.Created) Created ")
//			.append(" FROM EXME_ActPaciente AP  ")
//			.append(" INNER JOIN EXME_ActPacienteIndH APH ")
//			.append(" ON APH.EXME_ActPaciente_ID = AP.EXME_ActPaciente_ID ")
//			.append(" WHERE AP.EXME_CtaPac_ID= ?  ")
//			.append(" GROUP BY APH.EXME_Medico_ID  ");
//			union = true;
//		}
//		if (union){
//			sql.append(" UNION ALL ");
//		}
		sql.append(" SELECT EXME_Medico_ID, Created ")
		.append(" FROM EXME_CtaPacMed ")
		.append(" WHERE EXME_CtaPac_ID= ?  ")
		.append(" AND   Type= ? ) ")
		.append(" WHERE EXME_Medico_ID NOT IN ")
		.append("(SELECT DISTINCT EXME_Medico_ID ")
		.append(" FROM EXME_CtaPacMed ")
		.append(" WHERE EXME_CtaPac_ID = ? ")
		.append(" AND Type = ? ")
		.append(" AND IsActive = 'N')")
		.append(" ORDER BY Created Desc ");
		
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 1;
			pstmt.setInt(i, recordID);
			i++;
//			if (!type.equalsIgnoreCase(MEXMECtaPacMed.TYPE_Surgeon)){
//				pstmt.setInt(i, recordID);
//				i++;
//			}
			pstmt.setString(i, type);
			i++;
			pstmt.setInt(i, recordID);
			i++;
			pstmt.setString(i, type);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				list.add(new MEXMEMedico(ctx, rs.getInt(1), null));	
			}

		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		} finally {
			DB.close(rs, pstmt);

		}
		return list;
	}
*/

	/**
	 * 
	 * @param ad_Org_ID
	 * @return
	 * @throws SQLException
	 *
	public List<MEXMEEspecialidad> getEspecialidadesbyOrg(int ad_Org_ID) throws SQLException {
		List<MEXMEEspecialidad> list = new ArrayList<MEXMEEspecialidad>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT EXME_Especialidad.* FROM EXME_Especialidad EXME_Especialidad ")
		.append(" INNER JOIN EXME_MedicoEsp me ON ")
		.append("(EXME_Especialidad.EXME_Especialidad_ID = me.EXME_Especialidad_ID) ")
		.append(" WHERE me.EXME_Medico_ID = ? ")
		.append(" AND EXME_Especialidad.IsActive = 'Y' ")
		.append(" AND me.AD_Org_ID = ?")
		.append(" ORDER BY EXME_Especialidad.Name ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_Medico_ID());
			pstmt.setInt(2, ad_Org_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEEspecialidad esp = new MEXMEEspecialidad(getCtx(), rs, get_TrxName());
				list.add(esp);
			}


		} catch (Exception e) {
			log.log(Level.SEVERE, "getEspecialidadesbyOrg", e);
		} finally{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}

		return list;
	}*/

	public int getM_Product_ID() {
		MEXMEMedicoOrg medorg = MEXMEMedicoOrg.getMedicoOrg(getCtx(), getEXME_Medico_ID());
		if (medorg != null) {
			return medorg.getM_Product_ID();
		}
		return super.getM_Product_ID();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static String getNameByUserId(Properties ctx, int userId) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT Nombre_Med ");
		sql.append("FROM EXME_Medico ");
		sql.append("INNER JOIN EXME_Medico_Org ON (EXME_Medico.EXME_Medico_ID=EXME_Medico_Org.EXME_Medico_ID) ");
		sql.append("WHERE EXME_Medico_Org.AD_User_ID=? ");
		sql.append("AND EXME_Medico.isActive='Y' AND EXME_Medico_Org.isActive='Y'  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", MEXMEMedicoOrg.Table_Name));

		return DB.getSQLValueString(null, sql.toString(), userId);
	}
	/** @return ad user id from physician */
	public static int getUserId(final Properties ctx, final int medicoId, final boolean useDoctorCtx) {
		return MEXMEMedicoOrg.getUserId(ctx, medicoId, useDoctorCtx);
	}

}
