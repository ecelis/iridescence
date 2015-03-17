/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * <b>Prop&oacute;sito: </b> L&oacute;gica de negocios para el control
 * de expedientes.<p>
 * <b>Modificado: </b> $Date: 2006/10/24 02:22:42 $<p>
 * <b>Por: </b> $Author: vgarcia $<p>
 * 
 * @author mrojas
 * @version $Revision: 1.3 $
 */
public class MControlExp extends X_EXME_Control_Exp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MControlExp.class);
	
	
	//propiedades para despliegue solamente
	private String especialidad = null;
	private String usuario = null;
	private String nombrePac = null;
	private String historia = null;
	private String expediente = null;//gderreza--Archivo Clinico--
	
	
	/**
	 * @param ctx
	 * @param EXME_Control_Exp_ID
	 * @param trxName
	 */
	public MControlExp(Properties ctx, int EXME_Control_Exp_ID, String trxName) {
		super(ctx, EXME_Control_Exp_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MControlExp(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * Devolvemos una lista de expedientes en pr&eacute;stamo
	 * en base a unos criterios y ordenamiento espec&iacute;ficos.
	 * @param ctx El contexto de la apliaci&oacute;n
	 * @param where El criterio de b&uacute;squeda
	 * @param order El criterio de ordenamiento
	 * @param trxName
	 * @return Un arreglo con los expedientes en pr&eacute;stamo.
	 * @throws SQLException 
	 */
	public static ArrayList<MControlExp> getLstControlExp(Properties ctx, String where, String trxName) throws SQLException {
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MControlExp> lstExp = null;
		
		try {
			
			sql.append(" SELECT ")
			.append(" e.Name as especialidad, u.Name as usuario, he.Expediente, p.Nombre_Pac as paciente, ")
			.append(" p.Value as Historia, he.Expediente, EXME_Control_Exp.* ")
			.append(" FROM EXME_Control_Exp ")
			.append(" INNER JOIN EXME_Especialidad e ON (EXME_Control_Exp.EXME_Especialidad_ID = e.EXME_Especialidad_ID) ")
			.append(" INNER JOIN AD_User u ON (EXME_Control_Exp.AD_User_ID = u.AD_User_ID) ")
			//freyes, mrn activo * org
			.append(" INNER JOIN EXME_Hist_Exp he ON (EXME_Control_Exp.EXME_Hist_Exp_ID = he.EXME_Hist_Exp_ID and he.isactive='Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "he"))
			.append(") ")
			.append(" INNER JOIN EXME_Paciente p ON (he.EXME_Paciente_ID = p.EXME_Paciente_ID) WHERE ");
			
			if(where != null && where.length() > 0)
				sql.append(where)
				.append(" AND ");
				
			sql.append(" EXME_Control_Exp.IsActive = 'Y' ")
			.append(" AND EXME_Control_Exp.FechaDevol IS NULL ")
			.append(" AN he.IsPrestado = 'Y' ");
			
			
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Control_Exp"));
			
			sql.append(" ORDER BY ")
			.append(" he.Expediente, FechaPtmo DESC");
			
			s_log.fine("MControlExp.getLstControlExp - sql = " + sql);
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			rs = pstmt.executeQuery();
			
			lstExp = new ArrayList<MControlExp>();
			
			while(rs.next()) {
				MControlExp ce = new MControlExp(ctx, rs, trxName);
				
				ce.setEspecialidad(rs.getString("especialidad"));
				ce.setUsuario(rs.getString("usuario"));
				ce.setNombrePac(rs.getString("paciente"));
				ce.setExpediente(rs.getString("expediente"));//gderreza--Archivo Clinico--
				ce.setHistoria(rs.getString("Historia"));
				
				lstExp.add(ce);
			}
			
		} catch (SQLException e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		throw e;
    	}finally {
    		DB.close(rs, pstmt);
    	}

		
		
		return lstExp;
	}
	
	/**
	 * Efectuamos la devoluci&oacute;n del expediente.
	 * @param ctx
	 * @param ctrlExp
	 * @throws SQLException
	 */
	public static void devolucion(Properties ctx, MControlExp ctrlExp, String trxName) throws SQLException {

		Timestamp fechaServ = DB.getTimestampForOrg(ctx);

		ctrlExp.setFechaDevol(fechaServ);

		MEXMEHistExp expediente = new MEXMEHistExp(ctx, ctrlExp.getEXME_Hist_Exp_ID(), trxName);
		expediente.setIsPrestado(false);

		boolean saveCtrlExp = ctrlExp.save(trxName);
		boolean saveExp = expediente.save(trxName);

		if (!saveCtrlExp && !saveExp) {
			// DB.rollback(true, trxName);
			// Trx.get(trxName, false).close();
			throw new SQLException(Msg.translate(ctx, "ErrorReturnPatientFile") + " - "
					+ CLogger.retrieveError().getValue());
		}
		// else if(!DB.commit(true, trxName)) {
		// //Trx.get(trxName, false).close();
		// throw new SQLException(
		// Msg.translate(ctx, "ErrorReturnPatientFile") + " - " +
		// CLogger.retrieveError().getValue()
		// );
		// }

		// Trx.get(trxName, false).close();
	}
	
	/**
	 * Efectuamos el pr&eacute;stamo del expediente.
	 * 
	 * @param ctx
	 * @param ctrlExp
	 * @throws SQLException
	 */
	public static void prestamo(Properties ctx, MControlExp ctrlExp) 
	throws SQLException {
		String trxName 	= null;
		Trx m_trx 		= null;
		
		//java.util.Date fechaServ = CConnection.get(Compiere.getCodeBaseHost()).getFechaServidor();
		//fechaServ = CConnection.get(Compiere.getCodeBaseHost()).getFechaServidor();
		
		Timestamp timeToDB = null;
		Timestamp timeFromCliente = null;
		
		/* TEC E_20070206CMONTEJANO1 - Un casting de Object a String no permit�a terminar el proceso*/
		try {
			
			m_trx 	= Trx.get(Trx.createTrxName(), true);
			trxName = m_trx.getTrxName();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = (Date)dateFormat.parse(ctx.get("#FechaCliente").toString());
			
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			//Date date = sdf.parse(ctx.get("#FechaCliente").toString());
			timeFromCliente = new Timestamp(date.getTime());
			timeToDB = timeFromCliente;
			ctrlExp.setFechaPtmo(
					//new Timestamp(fechaServ.getTime())
					timeToDB
			);
		

			MEXMEHistExp expediente = new MEXMEHistExp(ctx, ctrlExp.getEXME_Hist_Exp_ID(), trxName);
			
			boolean saveCtrlExp = false;
			boolean saveExp     = false;
			
			// para validar que no se encuentre prestado (vgarcia)
			if(!expediente.isPrestado()){
				expediente.setIsPrestado(true);
				saveCtrlExp = ctrlExp.save(trxName);
				saveExp     = expediente.save(trxName);
			}
			
			
			if(!saveCtrlExp && !saveExp) {
				DB.rollback(true, trxName);
				m_trx.close();
				throw new SQLException(CLogger.retrieveError().getValue());
				
			} else if(!DB.commit(true, trxName)) {
				m_trx.close();
				throw new SQLException(CLogger.retrieveError().getValue());
			}
		
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}finally{
			if (m_trx!=null)
				m_trx.close();
			m_trx			= null;
			timeToDB		= null;
			timeFromCliente	= null;
		}
	}
	

	/**
	 * Devolvemos el expediente prestado a determinada
	 * especialidad.
	 * @param ctx
	 * @param hist_Exp_ID
	 * @param exmeEspecialidadId
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static MControlExp getForExpEspec(Properties ctx, int hist_Exp_ID, 
			int exmeEspecialidadId, String trxName) throws SQLException {
		

		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MControlExp ce = null;
		
		try {

			sql.append(" SELECT * FROM EXME_Control_Exp ")
			.append(" WHERE EXME_Control_Exp.EXME_Hist_Exp_ID = ? ")
			//.append(hist_Exp_ID)
			.append(" AND EXME_Control_Exp.EXME_Especialidad_ID = ? ")
			//.append(exmeEspecialidadId)
			//.append(" AND EXME_Control_Exp.AD_Client_ID = ? ")Comento esta linea ya que solamente se le manda en el pstmt dos parametros
			//.append(Env.getAD_Client_ID(ctx))                 adem�s que el par�metro del cliente se le agrega en el Nivel de Acceso. Alejandro Garza
			.append(" AND EXME_Control_Exp.TipoMovto = 'S' ")
			.append(" AND EXISTS ")
			.append("  (SELECT null FROM EXME_Hist_Exp he ")
			.append("   WHERE EXME_Control_Exp.EXME_Hist_Exp_ID = he.EXME_Hist_Exp_ID ")
			.append("   AND he.isprestado = 'Y')");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Control_Exp"));
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1, hist_Exp_ID);
			pstmt.setInt(2, exmeEspecialidadId);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				ce = new MControlExp(ctx, rs, trxName);
			}

		} catch (SQLException e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}


		return ce;
	}
	
	

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombrePac() {
		return nombrePac;
	}

	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {//gderreza--Archivo Clinico--
		this.expediente = expediente;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

}