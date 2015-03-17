package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;

/**
 * Modelo de Tratamientos
 * 
 * @author Julio Gutierrez
 * @Date: 2009/05/28 12:06
 * @version Revision: 1.0
 */
public class MEXMETratamientos extends X_EXME_Tratamientos implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = 8963740489197747390L;
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMETratamientos.class);

	public MEXMETratamientos(Properties ctx, int EXME_Tratamientos_ID, String trxName) {
		super(ctx, EXME_Tratamientos_ID, trxName);
	}

	public MEXMETratamientos(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private boolean iniciado = false;
	private boolean added = false;
	private Timestamp fecha = null;
	private String observaciones = null;
	private String citaNo = null;
	private String duracion = null;
	private String productName = null;
	private String paqName = null;
	private String productNameO = null;
	private String nameTratRef = null;

	public String getNameTratRef() {
		if (getRef_Tratamientos_ID() > 0) {
			MEXMETratamientos trat = new MEXMETratamientos(getCtx(), getRef_Tratamientos_ID(), null);
			if (trat != null) {
				nameTratRef = trat.getName();
			}
		}
		return nameTratRef;
	}

	public void setNameTratRef(String nameTratRef) {
		this.nameTratRef = nameTratRef;
	}

	public Timestamp getFecha() {
		if (fecha == null)
			fecha = DB.getTimestampForOrg(getCtx());
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public String getCitaNo() {
		return citaNo;
	}

	public void setCitaNo(String citaNo) {
		this.citaNo = citaNo;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPaqName() {
		return paqName;
	}

	public void setPaqName(String paqName) {
		this.paqName = paqName;
	}

	public String getProductNameO() {
		return productNameO;
	}

	public void setProductNameO(String productNameO) {
		this.productNameO = productNameO;
	}

	/**
	 * Regreda el tratamiento correspondiente a las especialidades del medico
	 * Lama
	 * 
	 * @param ctx
	 * @param tratamientosID
	 * @param medicoID
	 * @param trxName
	 * @return
	 */
	public static MEXMETratamientos getValid(Properties ctx, int tratamientosID, int medicoID, String trxName) {
		MEXMETratamientos retValue = null;

		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;		ResultSet rs = null;

		try {
			sql.append(" SELECT EXME_Tratamientos.* FROM EXME_Tratamientos ")
					.append(" INNER JOIN  EXME_Especialidad on ( EXME_Tratamientos.EXME_Especialidad_ID = EXME_Especialidad.EXME_Especialidad_ID) ")
					.append(" INNER JOIN  EXME_MedicoEsp on ( EXME_MedicoEsp.EXME_Especialidad_ID = EXME_Especialidad.EXME_Especialidad_ID ")
					.append(" AND EXME_MedicoEsp.EXME_Medico_ID = ? ) WHERE EXME_Tratamientos.EXME_Tratamientos_ID = ? ").append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Tratamientos"));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, medicoID);
			psmt.setInt(2, tratamientosID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMETratamientos(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return retValue;

	}

	/**
	 * Regresa el detalle de un tratamiento incluyendo su cantidad de citas,
	 * duracion, productos y minipaquetes relacionados rsolorzano
	 * 
	 * @param ctx
	 * @param tratamientosID
	 * @param medicoID
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMETratamientos> getDetalleTratamiento(Properties ctx, int tratamientosID, String trxName) {

		ArrayList<MEXMETratamientos> resultado = new ArrayList<MEXMETratamientos>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT EXME_Especialidad.Name, EXME_Tratamientos.Name, nvl(EXME_Tratamientos.Description, ' ') AS Description, EXME_Tratamientos.Code, EXME_Tratamientos.Periodicity, ProducOrigen.Name as productNameO,");
			sql.append(" NVL(EXME_Tratamientos_Detalle.SessionNo,0) AS CitaNo,NVL(EXME_Tratamientos_Detalle.Duracion, 0) AS Duracion, NVL (M_Product.Name, ' ') as productName, NVL (EXME_PaqBase_Version.Name, ' ') as paqName ");
			sql.append(" FROM EXME_Tratamientos");
			sql.append(" INNER JOIN EXME_Especialidad ON EXME_Tratamientos.EXME_Especialidad_ID = EXME_Especialidad.EXME_Especialidad_ID ");
			sql.append(" INNER JOIN EXME_Tratamientos_Detalle ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_Tratamientos_Detalle.EXME_Tratamientos_ID ");
			sql.append(" LEFT JOIN EXME_Tratamientos_Productos ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_Tratamientos_Productos.EXME_Tratamientos_ID AND EXME_Tratamientos_Productos.SessionNo= EXME_Tratamientos_Detalle.SessionNo ");
			sql.append(" LEFT JOIN M_Product ON EXME_Tratamientos_Productos.M_Product_ID = M_Product.M_Product_ID");
			sql.append(" LEFT JOIN EXME_PaqBase_Version ON EXME_Tratamientos_Productos.EXME_PaqBase_Version_ID = EXME_PaqBase_Version.EXME_PaqBase_Version_ID ");
			sql.append(" LEFT JOIN M_Product ProducOrigen ON EXME_Tratamientos.M_Product_ID = ProducOrigen.M_Product_ID");
			sql.append(" WHERE EXME_Tratamientos.EXME_Tratamientos_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Tratamientos"));
			sql.append(" ORDER BY EXME_Tratamientos_Detalle.SessionNo ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, tratamientosID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MEXMETratamientos obj = new MEXMETratamientos(ctx, rs, trxName);
				obj.setProductNameO(rs.getString("productNameO"));
				obj.setCitaNo(rs.getString("CitaNo"));
				obj.setDuracion(rs.getString("Duracion"));
				obj.setProductName(rs.getString("productName"));
				obj.setPaqName(rs.getString("paqName"));
				obj.setCode(rs.getString("Code"));
				resultado.add(obj);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return resultado;

	}

	/**
	 * Obtiene los productos de una tratamiento, segun su numero de cita
	 * 
	 * @return
	 */
	public List<MEXMETratamientosProductos> getProductos() {
		return MEXMETratamientosProductos.get(getCtx(), getEXME_Tratamientos_ID(), "AND CitaNo = " + getCitaNo(), get_TrxName());
	}

	/**
	 * Crea el objeto de tratamiento, y asigna el numero de cita
	 * 
	 * @param ctx
	 * @param EXME_Tratamientos_ID
	 * @param citaNo
	 * @param trxName
	 */
	public MEXMETratamientos(Properties ctx, int EXME_Tratamientos_ID, String citaNo, String trxName) {
		super(ctx, EXME_Tratamientos_ID, trxName);
		setCitaNo(citaNo);
	}

	/**
	 * Buscar los tratamientos de una cita, a partir de sus citas relacionadas
	 * 
	 * @author Lorena Lama
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param tratamientoID
	 *            Tratamiento de la cita origen
	 * @param citaMedicaID
	 *            Id de la cita origen (padre)
	 * @param citaNo
	 *            CitaNo de la citas a buscar
	 * @param citaNoRef
	 *            CitaNo del origen, para buscar el tratamiento
	 *            detalle/productos
	 * @param whereClause
	 *            Condiciones adicionales
	 * @param trxName
	 * @return Lista de tratamientos
	 */
	public static ArrayList<MEXMETratamientos> getChildRecords(Properties ctx, int tratamientoID, int citaMedicaID, int citaNo, int citaNoRef, String whereClause, String trxName) {

		ArrayList<MEXMETratamientos> resultado = new ArrayList<MEXMETratamientos>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append("SELECT EXME_Tratamientos.*, ProducOrigen.Name as productNameO, ").append(" EXME_Tratamientos_Detalle.SessionNo, EXME_Tratamientos_Detalle.Duracion, ")
					.append("M_Product.Name as productName, EXME_PaqBase_Version.Name as paqName, EXME_CitaMedica.CitaNo as init ").append(" FROM EXME_Tratamientos")
					.append(" INNER JOIN EXME_CitaMedica ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_CitaMedica.EXME_Tratamiento_ID  ").append(" AND EXME_CitaMedica.Ref_CitaMedica_ID = ? ");// citaPadre
			if (citaNo > 0)
				sql.append(" AND EXME_CitaMedica.CitaNo = ? "); // solo la
																// primer cita
																// hija

			// traemos solo el detalle que le corresonde a la cita padre
			sql.append(" LEFT JOIN EXME_Tratamientos_Detalle ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_Tratamientos_Detalle.EXME_Tratamientos_ID ")
					.append(" AND EXME_Tratamientos_Detalle.SessionNo = ").append(citaNoRef)
					.append(" LEFT JOIN EXME_Tratamientos_Productos ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_Tratamientos_Productos.EXME_Tratamientos_ID ")
					.append(" AND EXME_Tratamientos_Productos.SessionNo = ").append(citaNoRef).append(" LEFT JOIN M_Product ON EXME_Tratamientos_Productos.M_Product_ID = M_Product.M_Product_ID")
					.append(" LEFT JOIN EXME_PaqBase_Version ON EXME_Tratamientos_Productos.EXME_PaqBase_Version_ID = EXME_PaqBase_Version.EXME_PaqBase_Version_ID ")
					.append(" LEFT JOIN M_Product ProducOrigen ON EXME_Tratamientos.M_Product_ID = ProducOrigen.M_Product_ID").append(" WHERE  EXME_Tratamientos.isActive = 'Y' ")
					.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Tratamientos")).append(" AND EXME_Tratamientos.EXME_Tratamientos_ID <> ? ");

			if (whereClause != null)
				sql.append(whereClause);

			psmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 0;
			psmt.setInt(++i, citaMedicaID);
			if (citaNo > 0)
				psmt.setInt(++i, citaNo);
			psmt.setInt(++i, tratamientoID);

			rs = psmt.executeQuery();

			while (rs.next()) {
				MEXMETratamientos obj = new MEXMETratamientos(ctx, rs, trxName);
				obj.setProductNameO(rs.getString("productNameO"));
				obj.setCitaNo(rs.getString("CitaNo"));
				obj.setDuracion(rs.getString("Duracion"));
				obj.setProductName(rs.getString("productName"));
				obj.setPaqName(rs.getString("paqName"));
				obj.setIniciado(rs.getInt("init") > citaNoRef ? true : false);
				resultado.add(obj);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return resultado;
	}

	public static int getNoCitas(Properties ctx, int ExmeTratamientoID, String trxName) {
		int resultado = 0;
		if (ExmeTratamientoID > 0) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				sql.append("SELECT COUNT(EXME_TRATAMIENTOS_DETALLE_ID) FROM EXME_TRATAMIENTOS_DETALLE WHERE EXME_TRATAMIENTOS_ID = ? ").append(" AND  EXME_TRATAMIENTOS_DETALLE.isActive = 'Y' ");
				if (Env.getUserPatientID(ctx) < 0) // Si es un usuario paciente
													// no agrega el accessLevel
					sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMETratamientosDetalle.Table_Name));

				psmt = DB.prepareStatement(sql.toString(), trxName);

				psmt.setInt(1, ExmeTratamientoID);

				rs = psmt.executeQuery();

				if (rs.next()) {
					resultado = rs.getInt(1);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, psmt);
			}
		}
		return resultado;
	}

	/**
	 * Devuelve la ultima cita medica ejecutada del tratamiento
	 * 
	 * @param ctx
	 * @param exmeTratamientoId
	 * @param trxName
	 * @return
	 */
	public static MEXMECitaMedica getLastVisit(Properties ctx, int exmeTratamientoId, String trxName) {
		MEXMECitaMedica cita = null;
		if (exmeTratamientoId > 0) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				sql.append(" SELECT * FROM EXME_CitaMedica WHERE EXME_CitaMedica.Exme_Tratamiento_ID = ? AND EXME_CitaMedica.ESTATUS = ?  ").append(" AND EXME_CitaMedica.FECHAHRFIN IS NOT NULL ");
				if (Env.getUserPatientID(ctx) < 0) // Si es un usuario paciente
													// no agrega el accessLevel
					sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedica.Table_Name));
				sql.append(" ORDER BY EXME_CitaMedica.FECHAHRCITA DESC ");

				psmt = DB.prepareStatement(sql.toString(), trxName);

				psmt.setInt(1, exmeTratamientoId);
				psmt.setString(2, MEXMECitaMedica.ESTATUS_Executed);

				rs = psmt.executeQuery();

				if (rs.next()) {
					cita = new MEXMECitaMedica(ctx, rs.getInt(MEXMECitaMedica.COLUMNNAME_EXME_CitaMedica_ID), trxName);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, psmt);
			}
		}
		return cita;

	}

	/**
	 * Devuelve la siguiente cita medica no ejecutada del tratamiento
	 * 
	 * @param ctx
	 * @param exmeTratamientoId
	 * @param trxName
	 * @return
	 */
	public static MEXMECitaMedica getNextVisit(Properties ctx, int exmeTratamientoId, String trxName) {
		MEXMECitaMedica cita = null;
		if (exmeTratamientoId > 0) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				sql.append(" SELECT * FROM EXME_CitaMedica where EXME_CitaMedica.Exme_Tratamiento_ID = ? AND EXME_CitaMedica.ESTATUS <> ?   ").append(" AND EXME_CitaMedica.FECHAHRFIN IS NULL ");
				if (Env.getUserPatientID(ctx) < 0) // Si es un usuario paciente
													// no agrega el accessLevel
					sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedica.Table_Name));
				sql.append(" ORDER BY EXME_CitaMedica.FECHAHRCITA ");

				psmt = DB.prepareStatement(sql.toString(), trxName);

				psmt.setInt(1, exmeTratamientoId);
				psmt.setString(2, MEXMECitaMedica.ESTATUS_Executed);

				rs = psmt.executeQuery();

				if (rs.next()) {
					cita = new MEXMECitaMedica(ctx, rs.getInt(MEXMECitaMedica.COLUMNNAME_EXME_CitaMedica_ID), trxName);
				}

			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, psmt);
			}
		}
		return cita;

	}

	/**
	 * Devuelve la siguiente cita medica no ejecutada del tratamiento
	 * 
	 * @param ctx
	 * @param exmeTratamientoId
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMETratamientosPaciente> getTratamientos(Properties ctx, int pacID, String trxName) {
		ArrayList<MEXMETratamientosPaciente> ret = new ArrayList<MEXMETratamientosPaciente>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			sql.append(" select tp.* ").append(" from exme_tratamientospaciente tp ").append(" inner join exme_tratamientos t on tp.exme_tratamientos_id = t.exme_tratamientos_id ")
					.append(" where tp.exme_paciente_id =  ? ").append(" and tp.isactive = 'Y' ").append(" and tp.status = 'DR' ").append(" order by tp.created desc ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, pacID);

			rs = psmt.executeQuery();

			while (rs.next()) {
				ret.add(new MEXMETratamientosPaciente(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return ret;

	}

	public static ArrayList<LabelValueBean> getTreatments(Properties ctx, int physicianID, String likeValue, String trxName) {

		ArrayList<LabelValueBean> resultado = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT EXME_Tratamientos.EXME_Tratamientos_ID, EXME_Tratamientos.value, EXME_Tratamientos.Name, EXME_Tratamientos.description ");
			sql.append(" FROM EXME_Tratamientos ");
			sql.append(" INNER JOIN EXME_MedicoEsp ON EXME_MedicoEsp.EXME_Especialidad_ID = EXME_Tratamientos.EXME_Especialidad_ID ");
			sql.append(" WHERE EXME_Tratamientos.isactive='Y' ");
			sql.append(" AND EXME_MedicoEsp.EXME_Medico_ID = ? ");
			if (likeValue != null) {
				sql.append(" AND UPPER(EXME_Tratamientos.Name) LIKE ?");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, physicianID);
			if (likeValue != null) {
				psmt.setString(2, likeValue);
			}
			rs = psmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_Tratamientos_ID"));

				resultado.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return resultado;

	}

	/**
	 * Tratamientos del médico
	 * 
	 * @param ctx
	 *            Contexto
	 * @param medID
	 *            Médico ID
	 * @param text
	 *            Texto a buscar
	 * @return
	 */
	public static List<MEXMETratamientos> get(Properties ctx, int medID, String text) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  EXME_Tratamientos.* ");
		sql.append("FROM ");
		sql.append("  EXME_Tratamientos ");
		sql.append("  INNER JOIN EXME_MedicoEsp ");
		sql.append("  ON EXME_MedicoEsp.EXME_Especialidad_ID = EXME_Tratamientos.EXME_Especialidad_ID ");
		sql.append("WHERE ");
		sql.append("  EXME_Tratamientos.isactive= 'Y' AND ");
		sql.append("  EXME_MedicoEsp.EXME_Medico_ID = ? AND ");
		sql.append("  (upper(EXME_Tratamientos.value) LIKE ? OR ");
		sql.append("  upper(EXME_Tratamientos.name) LIKE ?) ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Tratamientos"));
		sql.append(" order by EXME_Tratamientos.value, EXME_Tratamientos.name desc");
		List<MEXMETratamientos> lst = new ArrayList<MEXMETratamientos>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, medID);
			pstmt.setString(2, '%' + StringUtils.upperCase(text) + '%');
			pstmt.setString(3, '%' + StringUtils.upperCase(text) + '%');
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMETratamientos(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Value Name concatenado
	 * 
	 * @return
	 */
	public String getValueName() {
		StringBuilder st = new StringBuilder();
		st.append(getValue()).append(" - ");
		st.append(getName());
		return st.toString();
	}

	public static ArrayList<LabelValueBean> getTreatment(Properties ctx, String trxName) {

		ArrayList<LabelValueBean> resultado = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			sql.append(" SELECT EXME_Tratamientos.EXME_Tratamientos_ID, EXME_Tratamientos.value, EXME_Tratamientos.Name, EXME_Tratamientos.description ");
			sql.append(" FROM EXME_Tratamientos WHERE Isactive = 'Y'");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Name ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			rs = psmt.executeQuery();
			resultado.add(new LabelValueBean("", ""));

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name"), rs.getString("EXME_Tratamientos_ID"));
				resultado.add(lvb);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return resultado;

	}

	@Override
	public String[] getColumns() {
		return null;
	}

	@Override
	public IDColumn getIdColumn() {
		return null;
	}

	/**
	 * Listado de sesiones por tratamiento
	 */
	public List<MEXMETratamientosDetalle> lstSesiones = null;

	/**
	 * Listado de sesiones por tratamiento
	 * 
	 * @param leer
	 *            Si apesar de que lstsesiones tenga valor se vuelva a hacer la
	 *            busqueda
	 * @return
	 */
	public List<MEXMETratamientosDetalle> getLstSesiones(boolean leer) {

		if (getEXME_Tratamientos_ID() > 0 && (leer || lstSesiones == null))
			setLstSesiones(MEXMETratamientosDetalle.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_ID()));

		return lstSesiones;
	}

	/**
	 * Listado de sesiones por tratamiento
	 * 
	 * @param lstSesiones
	 */
	public void setLstSesiones(List<MEXMETratamientosDetalle> lstSesiones) {
		this.lstSesiones = lstSesiones;
	}

	/**
	 * Busqueda de tratamientos del paciente por cuenta o por paciente
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param EXME_Paciente_ID
	 * @param orderBy
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientos> getTratamientos(Properties ctx, String where, String trxName) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * ").append(" FROM EXME_Tratamientos  ").append(" WHERE isActive= 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(where != null ? where : " ").append(" ");
		sql.append(" ORDER BY Name DESC ");

		return get(ctx, sql.toString(), null, true, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientos> get(Properties ctx, String sql, List<?> params, boolean leerDetalle, String trxName) {

		List<MEXMETratamientos> retValue = new ArrayList<MEXMETratamientos>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				MEXMETratamientos trat = new MEXMETratamientos(ctx, rs, trxName);
				if (trat != null && leerDetalle)
					trat.getLstSesiones(true);

				retValue.add(trat);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Especialidad
	 */
	private MEXMEEspecialidad especialidad = null;

	public MEXMEEspecialidad getEspecialidad() {

		if (getEXME_Especialidad_ID() > 0 && especialidad == null)
			especialidad = new MEXMEEspecialidad(getCtx(), getEXME_Especialidad_ID(), null);

		return especialidad;
	}

	public void setEspecialidad(MEXMEEspecialidad tratPaciente) {
		this.especialidad = tratPaciente;
	}

	// Titulo, Clase, TablaName msj.instrucciones
	public static BasicoTresProps[] secciones = new BasicoTresProps[] { new BasicoTresProps(X_EXME_Tratamientos_Detalle.Table_Name, MEXMETratamientosDetalle.class.getName(), "msj.session"),
			new BasicoTresProps(X_EXME_Tratamientos_Cues.Table_Name, MEXMETratamientosCues.class.getName(), "lstCuestionarios.title"),
			new BasicoTresProps(X_EXME_Tratamientos_Paq.Table_Name, MEXMETratamientosPaq.class.getName(), "msj.minipaquete"),
			new BasicoTresProps(X_EXME_Tratamientos_Serv.Table_Name, MEXMETratamientosServ.class.getName(), "msj.estudios"),
			new BasicoTresProps(X_EXME_Tratamientos_Productos.Table_Name, MEXMETratamientosProductos.class.getName(), "title.mat.des"),
			new BasicoTresProps(X_EXME_Tratamientos_Pres.Table_Name, MEXMETratamientosPres.class.getName(), "msg.prescripciones"),
			new BasicoTresProps(X_EXME_Tratamientos_Detalle.Table_Name, MEXMETratamientosDetalle.class.getName(), "msj.instrucciones") };

	public static List<MEXMETratamientos> getTratamientosDes(Properties ctx, String where, String trxName) {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * ").append(" FROM EXME_Tratamientos  ").append(" WHERE isActive= 'N' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		sql.append(where != null ? where : " ").append(" ");
		sql.append(" ORDER BY Name DESC ");

		return get(ctx, sql.toString(), null, true, trxName);
	}

	/**
	 * Cuando el tratamiento a sido asignado a un paciente
	 */
	private MEXMETratamientosPaciente tratPaciente = null;

	public MEXMETratamientosPaciente getTratPaciente() {
		return tratPaciente;
	}

	public void setTratPaciente(MEXMETratamientosPaciente tratPaciente) {
		this.tratPaciente = tratPaciente;
	}

	/**
	 * Buscamos el tratamiento paciente
	 * 
	 * @param ctaPacID
	 *            cuenta paciente
	 */
	public List<MEXMETratamientosSesion> tratPaciente(int ctaPacID) {

		List<Integer> params = new ArrayList<Integer>();

		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" AND EXME_CtaPac_ID = ? ");
		params.add(ctaPacID);
		where.append(" AND EXME_Tratamientos_ID = ? ");
		params.add(getEXME_Tratamientos_ID());

		// buscamos la relacion paciente tratamiento
		List<MEXMETratamientosPaciente> lstTratPaciente = MEXMETratamientosPaciente.getTratamientos(getCtx(), where.toString(), params, null);
		if (lstTratPaciente != null && lstTratPaciente.size() > 0) {
			setTratPaciente(lstTratPaciente.get(0));
		}

		// Buscamos las sesiones del tratamiento
		int tratPacID = 0;
		if (getTratPaciente() != null) {
			tratPacID = getTratPaciente().getEXME_TratamientosPaciente_ID();
		}

		List<MEXMETratamientosSesion> lstDetSesiones = MEXMETratamientosSesion.getTratamientosDetalle(Env.getCtx(), tratPacID, null);

		return lstDetSesiones;
	}

	// Lista que manda los tratamientos siempre y cuando tengan detalles en
	// ellos
	public static List<LabelValueBean> getTratamientosConDet(Properties ctx, String trxName) {
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql = new StringBuilder();

			sql.append("SELECT NAME, EXME_TRATAMIENTOS_ID FROM EXME_Tratamientos");
			sql.append(" WHERE EXME_TRATAMIENTOS_ID IN (SELECT DISTINCT EXME_TRATAMIENTOS_ID FROM EXME_TRATAMIENTOS_DETALLE)");
			sql.append(" AND EXME_TRATAMIENTOS.ISACTIVE = 'Y'");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
			sql.append(" ORDER BY EXME_TRATAMIENTOS.NAME");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	public static boolean deleteTemp(Properties ctx, int histTempID, String trxName) {

		boolean ret = false;
		MEXMETratamientosPaciente aux = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" select tp.* ").append(" from exme_tratamientospaciente tp ").append(" where tp.exme_tratamientospaciente_id = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, histTempID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				aux = new MEXMETratamientosPaciente(ctx, rs, trxName);
				aux.setIsActive(false);
				if (!aux.save(trxName)) {
					return false;
				} else {
					return deleteTempSessions(ctx, aux.getEXME_TratamientosPaciente_ID(), trxName);
				}

			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}

	public static boolean deleteTempSessions(Properties ctx, int histTempID, String trxName) {

		boolean ret = false;
		MEXMETratamientosSesion aux = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" select *  ").append(" from exme_tratamientos_sesion  ").append(" where exme_tratamientospaciente_id = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, histTempID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				aux = new MEXMETratamientosSesion(ctx, rs, trxName);
				aux.setIsActive(false);
				if (!aux.save(trxName)) {
					return false;
				} else {
					if (!deleteTempCita(ctx, aux.getEXME_Tratamientos_Sesion_ID(), trxName)) {
						return false;
					} else {
						ret = true;
					}
				}

			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}

	public static boolean deleteTempCita(Properties ctx, int sessionTempID, String trxName) {

		boolean ret = false;
		MEXMECitaMedica aux = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" select *  ").append(" from exme_citamedica  ").append(" where exme_tratamientos_sesion_id = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, sessionTempID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				aux = new MEXMECitaMedica(ctx, rs, trxName);
				aux.setIsActive(false);
				aux.setEstatus(MEXMECitaMedica.ESTATUS_Cancelled);
				if (!aux.save(trxName)) {
					return false;
				} else {
					return true;
				}

			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}

}
