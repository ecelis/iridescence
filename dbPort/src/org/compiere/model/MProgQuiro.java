/*
 * Created on 04-may-2005
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Modelo de Programacion de Quirofanos
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/11/02 21:34:31 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.7 $
 * Modificado por Lorena Lama,Julio 2013.
 *  - Revision de codigo: se quitan metodos comentados, se comentan metodos obsoletos, se eliminan warnings
 */
public class MProgQuiro extends X_EXME_ProgQuiro {
    
	/** serialVersionUID **/
	private static final long serialVersionUID = 1L;

//	private MEXMECtaPac m_CtaPac = null; 
    
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MProgQuiro.class);
	
	
    /**
     * @param ctx
     * @param EXME_SignoVitalDet_ID
     * @param trxName
     */
    public MProgQuiro(Properties ctx, int EXME_ProgQuiro_ID, String trxName) {
        super(ctx, EXME_ProgQuiro_ID, trxName);
		setIsPrinted(false);
		setIsInvoiced(false);
		setIsApproved(true);
		setIsCreditApproved(true);
		setProcessing(false);
		setProcessed(true);
//		setPosted(true);
		
		if(is_new()){
			setPriorityRule("5");// FIXME ?
			setAD_User_ID(Env.getAD_User_ID(getCtx()));
			final int doctype = getDocType();
			setC_DocType_ID(doctype);
			setC_DocTypeTarget_ID(doctype);
			setDateOrdered(Env.getCurrentDate());
			setDocAction(MProgQuiro.DOCSTATUS_ImmediateNext);
			setDocStatus(MProgQuiro.DOCSTATUS_Pending);
			setEXME_Warehouse_ID(Env.getM_Warehouse_ID(getCtx()));
		}
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MProgQuiro(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
        setIsPrinted(false);
		setIsInvoiced(false);
		setIsApproved(true);
		setIsCreditApproved(true);
		setProcessing(false);
		setProcessed(true);
//		setPosted(true);
    }
    
//    /**
//     * Obtenemos la cuenta paciente
//     * @return
//     */
//    public MEXMECtaPac getCtaPac(){
//        if(m_CtaPac == null || m_CtaPac.getEXME_CtaPac_ID() == 0){
//            m_CtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
//        }
//        return m_CtaPac;
//    }
//    
//    /**
//     * Le asignamos la cuenta paciente.
//     * @param ctaPac
//     */
//    public void setCtaPac(MEXMECtaPac ctaPac){
//        if(ctaPac != null){
//            setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
//            m_CtaPac = ctaPac;
//        }
//    }
//    
    
//	public static MProgQuiro[] get(Properties ctx, int EXME_CtaPac_ID, String trxName) {
//		ArrayList<MProgQuiro> lista = new ArrayList<MProgQuiro>();
//
//		if (ctx == null || EXME_CtaPac_ID == 0) {
//			return null;
//		}
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" SELECT EXME_ProgQuiro.EXME_ProgQuiro_ID ");
//		sql.append(" FROM EXME_ProgQuiro");
//		sql.append(" WHERE EXME_ProgQuiro.EXME_CtaPac_ID = ? ");
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setLong(1, EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MProgQuiro exp = new MProgQuiro(ctx, rs.getInt("EXME_ProgQuiro_ID"), trxName);
//				lista.add(exp);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "get", e);
//
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		MProgQuiro[] listCorte = new MProgQuiro[lista.size()];
//		lista.toArray(listCorte);
//		return listCorte;
//	}

    public MProgQuiroDet[] getLines(){
    	return getLines(getCtx(),getEXME_ProgQuiro_ID(), get_TrxName() );
    }
    
    /**
     * Obtenemos la cuenta paciente
     * @return
     */
	public static MProgQuiroDet[] getLines(Properties ctx, int EXME_ProgQuiro_ID, String trxName) {
		ArrayList<MProgQuiroDet> lista = new ArrayList<MProgQuiroDet>();

		if (ctx == null || EXME_ProgQuiro_ID == 0) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT *  ");
		sql.append(" FROM EXME_ProgQuiroDet");
		sql.append(" WHERE EXME_ProgQuiroDet.EXME_ProgQuiro_ID = ? ");
		if (Env.getUserPatientID(ctx) < 0) {// Si es un usuario paciente no agrega el accessLevel
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",MProgQuiroDet.Table_Name));
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setLong(1, EXME_ProgQuiro_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MProgQuiroDet exp = new MProgQuiroDet(ctx, rs, trxName);
				lista.add(exp);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getHistorial", e);

		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		MProgQuiroDet[] listCorte = new MProgQuiroDet[lista.size()];
		lista.toArray(listCorte);
		return listCorte;
	}

	public MEXMEMedico getMedico() {
		return getEXME_Medico_ID() <= 0 ? null : new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
	}

//	public MEXMEQuirofano getQuirofano() {
//		return getEXME_Quirofano_ID() <= 0 ? null : new MEXMEQuirofano(getCtx(), getEXME_Quirofano_ID(), get_TrxName());
//	}
//	@Deprecated
//	public int getId() {
//		return super.getEXME_ProgQuiro_ID();
//	}


	/**
	 * Se guardan las relaciones a la programacion para las intervenciones
	 * y para los equipos
	 * @param pv
	 * @throws Exception
	 */
	
	public void crearRelaciones(List<MEXMEProductInterv> lstInterv, List<QuirEquiposView> lstEquipo)
			throws Exception {

		try {
			// insertamos el detalle de las intervenciones
			if (lstInterv.size() > 0) {
				for (MEXMEProductInterv piv : lstInterv) {
					final MProgQuiroDet progDet = piv.newProgQuiroDet(get_TrxName());
					progDet.setEXME_ProgQuiro_ID(getEXME_ProgQuiro_ID());
					if (!progDet.save()) {
						throw new Exception(
								"No se logro guardar las intervenciones para la programacion  del quirofano");
					}
				}
			}

			MEXMEQuirofano quirofano = new MEXMEQuirofano(getCtx(), getEXME_Quirofano_ID(), get_TrxName());
			X_EXME_EstServ estacion = null;
			if (quirofano != null && quirofano.getEXME_EstServ_ID() > 0) {
				estacion = new X_EXME_EstServ(getCtx(), quirofano.getEXME_EstServ_ID(), get_TrxName());
			}
			// insertamos el detalle de los equipos a utilizar
			if (lstEquipo.size() > 0) {
				for (int i = 0; i < lstEquipo.size(); i++) {
					QuirEquiposView view = (QuirEquiposView) lstEquipo.get(i);
					if(view.isSelected()){
						MProgQuiroEq progEq = new MProgQuiroEq(getCtx(), 0, get_TrxName());
						progEq.setEXME_Equipo_ID((int) view.getEquipoID());
						progEq.setEXME_ProgQuiro_ID(getEXME_ProgQuiro_ID());
						if (!progEq.save(get_TrxName())) {
							throw new Exception("No se logro guardar el registro del equipo de quirofano");
						}
						X_EXME_EquipoH equipo = new X_EXME_EquipoH(getCtx(), 0, get_TrxName());
						equipo.setEstatus_Equipo(X_EXME_EquipoH.ESTATUS_EQUIPO_Ordered);
						equipo.setEXME_ProgQuiro_ID(getEXME_ProgQuiro_ID());
						equipo.setEXME_Quirofano_ID(getEXME_Quirofano_ID());
						equipo.setEXME_Equipo_ID(progEq.getEXME_Equipo_ID());
						// Fecha prometida de toda la programaci�n
						equipo.setFecha_Ini(new Timestamp(getFechaProg().getTime()));
						// Fecha final de toda la programaci�n
						equipo.setFecha_Fin(new Timestamp(new DateTime(getFechaProg()).plusMinutes(getDuracion().intValue()).toDate().getTime()));
						
						if (quirofano != null) {
							equipo.setEXME_EstServ_ID(quirofano.getEXME_EstServ_ID());
							if (estacion != null) {
								equipo.setEXME_Area_ID(estacion.getEXME_Area_ID());
							}
						}
	
						if (!equipo.save()) {
							throw new Exception("No se logro guardar el registro del equipo de quirofano");
						}
					}
				}
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "guardarProg", e);
			throw new Exception(e.getMessage());
		}

	}
  
 	/**
	 * Actualizamos los datos de una programaci&oacute;n de quir&oacute;fano
	 *
	 * @param pv El objeto con los datos de la programaci&oacute;n
	 * @param ctx El contexto de la aplicaci&oacute;n
	 * @throws Exception en caso de ocurrir un error al actualizar
	 * los datos.
	 */
	public void borrarRelaciones() throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// PreparedStatement con la sentencia sql
		PreparedStatement pstmt = null;
		// ResultSet utilizado para manipular los resultados
		ResultSet rs = null;

		try {

			/**
			 * SE ELIMINAN INTERVENCIONES
			 */
			// en caso de haber intervenciones relacionadas, las borramos
			sql.append(" SELECT * FROM EXME_ProgQuiroDet ");
			sql.append(" WHERE EXME_ProgQuiro_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setLong(1, getEXME_ProgQuiro_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MProgQuiroDet progDet = new MProgQuiroDet(getCtx(), rs, get_TrxName());
				if (!progDet.delete(true, get_TrxName())) {
					throw new Exception("No se logro actualizar la programacion de quirofanos");
				}
			}

			DB.close(rs,pstmt);
			
			/**
			 * SE ELIMINAN EQUIPOS
			 */
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT * FROM EXME_ProgQuiroEq ");
			sql.append(" WHERE EXME_ProgQuiro_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setLong(1, getEXME_ProgQuiro_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MProgQuiroEq progDetEq = new MProgQuiroEq(getCtx(), rs, get_TrxName());
				if (!progDetEq.delete(true, get_TrxName())) {
					throw new Exception("No se logro guardar el registro del equipo de quirofano");
				}
			}

			DB.close(rs, pstmt);

			/**
			 * SE ELIMINAN EQUIPOS EN HISTORICO
			 */
			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append(" SELECT * FROM EXME_EquipoH ");
			sql.append(" WHERE EXME_ProgQuiro_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setLong(1, getEXME_ProgQuiro_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEEquipoH equipoH = new MEXMEEquipoH(getCtx(), rs, get_TrxName());
				if (!equipoH.delete(true, get_TrxName())) {
					throw new Exception("No se logro guardar el registro del historial de equipo de quirofano");
				}
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "actualizarProg", e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * Obtenemos la disponibilidad de un quirofano dada un
	 */
	public static boolean isOccupied(Properties ctx, int quirofano_ID, int progquir_ID, Date dateStart, Date dateEnd, String trxName) {

		boolean retValue = false;

		Interval event = new Interval(new DateTime(dateStart), new DateTime(dateEnd));

		DateTime rangeStart = new DateTime(TimeUtil.getInitialRange(ctx, dateStart)).minusDays(1);
		DateTime rangeEnd = new DateTime(TimeUtil.getInitialRange(ctx, dateStart)).plusDays(1);

		List<MEXMEProgQuiro> list = MEXMEProgQuiro.getFromQuirofano(ctx, quirofano_ID, -1, rangeStart.toDate(), rangeEnd.toDate(), null);

		for (MEXMEProgQuiro progQuiro : list) {
			if (progquir_ID != progQuiro.getEXME_ProgQuiro_ID()) {
				Interval record = null;

				if (MEXMEProgQuiro.DOCSTATUS_Closed.equals(progQuiro.getDocStatus().trim())) {
					record = new Interval(new DateTime(progQuiro.getFechaInicio()), new DateTime(new DateTime(progQuiro.getFechaInicio()).plusMinutes(progQuiro.getDuracion().intValue()).toDate()));
				} else {
					record = new Interval(new DateTime(progQuiro.getFechaProg()), new DateTime(new DateTime(progQuiro.getFechaProg()).plusMinutes(progQuiro.getDuracion().intValue()).toDate()));
				}

				if (event.overlaps(record)) {
					retValue = true;
					break;
				}
			}
		}

		return retValue;
	}

	/**
	 * Verificamos si la cuenta tiene al menos 1 linea
	 * 
	 * @return True si tiene al menos 1 linea, false si no.
	 */
	public static boolean getForCtaPacId(Properties ctx, int ctaPacId, String trxName) {
		return new Query(ctx, Table_Name, " EXME_CtaPac_ID=? ", trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(ctaPacId)//
		.firstId() > 0
		;
	}

// 	/**
// 	 * Revisamos que la cuenta paciente no tenga ya una programacion en la misma fecha
// 	 * @param ctx
// 	 * @param ctaPacId
// 	 * @param progquiroid
// 	 * @param trxName
// 	 * @param fecha
// 	 * @return
// 	 * @throws Exception
// 	 */
//	public static boolean getPacRep(Properties ctx, int ctaPacId, int progquiroid, String trxName, Timestamp fecha)
//			throws Exception {
//
//		boolean retValue = true;
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT * FROM EXME_ProgQuiro ");
//		sql.append(" WHERE EXME_ProgQuiro.isActive = 'Y' AND EXME_ProgQuiro.EXME_CtaPac_ID = ? ");
//		sql.append(" AND TO_CHAR ( EXME_ProgQuiro.FechaProg , 'dd/mm/yyyy HH24:MI') <= ? ");
//		sql.append(" AND TO_CHAR ( EXME_ProgQuiro.FechaFin , 'dd/mm/yyyy HH24:MI') >= ? ");
//		sql.append(" AND EXME_ProgQuiro.EXME_ProgQuiro_ID <> ? ");
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, ctaPacId);
//			pstmt.setString(2, Constantes.getSdfFechaHora().format(fecha));
//			pstmt.setString(3, Constantes.getSdfFechaHora().format(fecha));
//			pstmt.setInt(4, progquiroid);
//			rs = pstmt.executeQuery();
//
//			if (!rs.next()) {
//				retValue = false;
//			}
//
//		} catch (SQLException e) {
//			throw new Exception(e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return retValue;
//
//	}
	
	public static boolean getPacRep(Properties ctx, int ctaPacId, int progquiroid, String trxName, Timestamp fecha, Timestamp fechaFin) throws Exception {
		boolean retValue = true;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  EXME_ProgQuiro ");
		sql.append("WHERE ");
		sql.append("  EXME_ProgQuiro.isActive = 'Y' AND ");
		sql.append("  EXME_ProgQuiro.EXME_CtaPac_ID = ? AND ");
		sql.append("  ((TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
		sql.append("  EXME_ProgQuiro.FechaProg AND ");
		sql.append("  EXME_ProgQuiro.FechaFin) OR ");
		sql.append("  (TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) BETWEEN ");
		sql.append("  EXME_ProgQuiro.FechaProg AND ");
		sql.append("  EXME_ProgQuiro.FechaFin) OR ");
		sql.append("  (EXME_ProgQuiro.FechaProg BETWEEN ");
		sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
		sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' )) OR ");
		sql.append("  (exme_progquiro.fechafin BETWEEN ");
		sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ) AND ");
		sql.append("  TO_DATE(? ,'dd/mm/yyyy HH24:MI' ))) AND ");
		sql.append("  EXME_ProgQuiro.EXME_ProgQuiro_ID <> ? AND ");
		sql.append("  EXME_ProgQuiro.DocStatus not in (?) ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacId);
			pstmt.setString(2, Constantes.getSdfFechaHora().format(fecha));
			pstmt.setString(3, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(4, Constantes.getSdfFechaHora().format(fecha));
			pstmt.setString(5, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setString(6, Constantes.getSdfFechaHora().format(fecha));
			pstmt.setString(7, Constantes.getSdfFechaHora().format(fechaFin));
			pstmt.setInt(8, progquiroid);
			pstmt.setString(9, DOCSTATUS_Canceled);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				retValue = false;
			}

		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}

 	/**
 	 * Obtenemos el tipod de documento para la programaci�n
 	 *
 	 * @throws Exception en caso de ocurrir un error al insertar los datos
 	 */
	public int getDocType() {
		return MEXMEDocType.getOfName(getCtx(), "Prog. Qfano.", get_TrxName());
	}

	public String getIntHora() {
		return Constantes.getDosDigitos().format(intHora);
	}

	public void setIntHora(int intHora) {
		this.intHora = intHora;
	}

	public String getIntMin() {
		return Constantes.getDosDigitos().format(intMin);
	}

	public void setIntMin(int intMin) {
		this.intMin = intMin;
	}

	private int intMin = 0;

	private int intHora = 0;
	
	private String strMin = "00";

	private String strHora = "00";

	public void horas(BigDecimal horario) {
		DecimalFormat dfHora = Constantes.getDfHora();
		double hora = horario.doubleValue();
		this.intHora = Integer.parseInt(dfHora.format(hora).substring(0, 2));
		this.intMin = Integer.parseInt(dfHora.format(hora).substring(3));

	}
	
	public String getStrMin() {
		return strMin;
	}

	public void setStrMin(String strMin) {
		this.strMin = strMin;
	}

	public String getStrHora() {
		return strHora;
	}

	public void setStrHora(String strHora) {
		this.strHora = strHora;
	}

//	/** @deprecated no utilizada*/
//	public void duraciones(BigDecimal paramHoraInicio, BigDecimal paramHoraFin) {
//
//		horas(paramHoraInicio);
//
//		Calendar calIni = Calendar.getInstance();
//		calIni.setTime(getFechaInicio());
//		calIni.set(Calendar.HOUR_OF_DAY, intMin);
//		calIni.set(Calendar.MINUTE, intHora);
//
//		horas(paramHoraFin);
//
//		Calendar calFin = Calendar.getInstance();
//		calFin.setTime(getFechaFin());
//		calFin.set(Calendar.HOUR_OF_DAY, intMin);
//		calFin.set(Calendar.MINUTE, intHora);
//		calFin.add(Calendar.MINUTE, 1); // compensamos el minuto restado al guardar
//
//		String duracion = Utilerias.calcHoraMin(calIni, calFin);
//
//		this.strHora = duracion.substring(0, 2);
//		this.strMin = duracion.substring(3, 5);
//	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MEXMEProductInterv> getIntervenciones() throws Exception {
		return getIntervenciones(getCtx(),getEXME_ProgQuiro_ID(),get_TrxName());
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public static List<MEXMEProductInterv> getIntervenciones(Properties ctx, final int progQuiroId, String trxName) throws Exception {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// PreparedStatement con la sentencia sql
		PreparedStatement pstmt = null;
		// ResultSet utilizado para manipular los resultados
		ResultSet rs = null;

		List<MEXMEProductInterv> lista = new ArrayList<MEXMEProductInterv>();
		try {

			// necesitamos buscar las intervenciones relacionadas con esta programacion
			sql.append("SELECT pqd.description, i.*  ");
			sql.append(" FROM EXME_ProgQuiroDet pqd ");
			sql.append(" INNER JOIN EXME_ProductInterv i ON (pqd.M_Product_ID=i.EXME_ProductInterv_ID AND pqd.EXME_Intervencion_ID=i.EXME_Intervencion_ID) ");
			sql.append(" WHERE pqd.EXME_ProgQuiro_ID=? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MProgQuiroDet.Table_Name, "pqd"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, progQuiroId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMEProductInterv piv = new MEXMEProductInterv(ctx, rs, trxName);
				piv.setObservations(rs.getString("Description"));
				lista.add(piv);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getProgQuiro", e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lista;
	}
	
//	/**
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public ArrayList<QuirEquiposView> getEquiposProgramados() throws Exception {
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		// PreparedStatement con la sentencia sql
//		PreparedStatement pstmt = null;
//		// ResultSet utilizado para manipular los resultados
//		ResultSet rs = null;
//
//		ArrayList<QuirEquiposView> lista = new ArrayList<QuirEquiposView>();
//		try {
//
//			// y el equipo seleccionado
//			sql.append(" SELECT e.EXME_Equipo_ID, e.Name  ");
//			sql.append(" FROM EXME_ProgQuiroEq  ");
//			sql.append(" INNER JOIN EXME_Equipo e ON EXME_ProgQuiroEq.EXME_Equipo_ID = e.EXME_Equipo_ID ");
//			sql.append(" WHERE EXME_ProgQuiroEq.EXME_ProgQuiro_ID = ? ");
//
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setLong(1, getEXME_ProgQuiro_ID());
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				QuirEquiposView view = new QuirEquiposView(getCtx(), 0, get_TrxName());
//				view.setEquipoID(rs.getLong("EXME_Equipo_ID"));
//				view.setEXME_Equipo_ID(rs.getInt("EXME_Equipo_ID"));
//				view.setEquipoName(rs.getString("Name"));
//				view.setSelected(true);
//				lista.add(view);
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getProgQuiro", e);
//			throw new Exception(e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		return lista;
//	}

	public String getDocStatus() {
		return super.getDocStatus() == null ? super.getDocStatus() : super.getDocStatus().trim();
	}

	public static List<LabelValueBean> estatusMinimos(Properties ctx) throws Exception {
		final List<LabelValueBean> lista = MRefList.getListasTraducidas(DOCSTATUS_AD_Reference_ID,
				Env.getAD_Language(ctx), false);

		final List<LabelValueBean> listaMinimos = new ArrayList<LabelValueBean>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getValue().equalsIgnoreCase(DOCSTATUS_ActiveAndNonClose)
					|| lista.get(i).getValue().equalsIgnoreCase(DOCSTATUS_Closed)
					|| lista.get(i).getValue().equalsIgnoreCase(DOCSTATUS_ImmediateNext)
					|| lista.get(i).getValue().equalsIgnoreCase(DOCSTATUS_Pending)) {
				listaMinimos.add(lista.get(i));
			}
		}
		return listaMinimos;
	}

	public boolean isReadyToClose() {
		return DOCSTATUS_ActiveAndNonClose.equals(getDocStatus());
	}

	public boolean isClosed() {
		return DOCSTATUS_Closed.equals(getDocStatus());
	}

	public boolean isEditable() {
		return !(DOCSTATUS_ActiveAndNonClose.equals(getDocStatus()) || DOCSTATUS_Closed.equals(getDocStatus()));
	}
	
	
	public static List<MProgQuiro> get(final Properties ctx, final String where, final List<Object> params, String trxName) {
		return new Query(ctx, Table_Name, where, trxName)//
		.setParameters(params)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.list();
	}
}
