package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
/**
 * Modificado por Lorena Lama,Julio 2013.
 *  - Revision de codigo: se quitan metodos comentados, se comentan metodos obsoletos, se eliminan warnings
 */
public class MProgQuiroEq extends X_EXME_ProgQuiroEq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MProgQuiroEq.class);
	
	/**
	 * Formatea a fecha y hora (24) dd/MM/yyyy HH:mm
	 */
//	public static SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	/**
     * @param ctx
     * @param EXME_SignoVitalDet_ID
     * @param trxName
     */
    public MProgQuiroEq(Properties ctx, int EXME_ProgQuiroEq_ID, String trxName) {
        super(ctx, EXME_ProgQuiroEq_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MProgQuiroEq(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    
	/**
	 * Informa si un equipo determinado esta siendo utilizado en otro(s)
	 * quir&oacute;fano)s a la misma hora en la que deseamos utilizarlo para
	 * otro programa y si la disponibilidad del equipo ya fue superada.
	 *
	 * @param xxEquipoId El equipo que deseamos validar.
	 * @param fechaInicio La fecha de inicio del programa (incl. hora)
	 * @param fechaFin La fecha de fin del programa (incl. hora)
	 * @param con La conexi&oacute;n en la que hacemos la transacci&oacute;n
	 *
	 * @return Un valor String conteniendo el (los) quirofano(s) en el (los) que
	 * se est&aacute; utilizando el equipo.
	 * @throws Exception en caso de ocurrir un error al hacer la consulta.
	 * @deprecated No se utiliza
	 */
//	public static String equipoUsado(Properties ctx, long xxEquipoId, Calendar fechaInicio, 
//			Calendar fechaFin, String trxName) throws Exception {
//	    
//        StringBuilder sql = new StringBuilder();
//		
//		//PreparedStatement con la sentencia sql
//		PreparedStatement pstmt = null;
//		//ResultSet utilizado para manipular los resultados
//		ResultSet rs = null;
//	    
//		StringBuilder quirofanos = new StringBuilder();
//	    
//		try {
//			final SimpleDateFormat sdfFechaHora = Constantes.getSdfFechaHora();
//			
//			sql.append("SELECT eq.canttotal, q.Name, eq.disponible, ")
//			.append("TO_CHAR(EXME_ProgQuiro.fechainicio, 'DD/MM/YYYY HH24:MI') as FechaIni, ")
//			.append("TO_CHAR(EXME_ProgQuiro.fechafin, 'DD/MM/YYYY HH24:MI') AS FechaFin, m.nombre_med ")
//			.append("FROM EXME_ProgQuiro EXME_ProgQuiro, EXME_ProgQuiroEq pqe, EXME_Equipo eq, ")
//			.append("EXME_Quirofano q, EXME_Medico m ")
//			//.append("WHERE fechainicio + (horainicio/24) <= TO_DATE('")
//			.append("WHERE fechainicio <= TO_DATE('")
//			.append(sdfFechaHora.format(fechaFin.getTime()))
//			.append("', 'dd/MM/yyyy hh24:MI') ")
//			//.append("AND fechainicio + (horainicio/24) >= TO_DATE('")
//			.append("AND fechainicio >= TO_DATE('")
//			.append(sdfFechaHora.format(fechaInicio.getTime()))
//			.append("', 'dd/MM/yyyy hh24:MI') ")
//			.append("AND pqe.EXME_ProgQuiro_ID = EXME_ProgQuiro.EXME_ProgQuiro_ID ")
//			.append("AND q.EXME_Quirofano_ID = EXME_ProgQuiro.EXME_Quirofano_ID ")
//			.append("AND pqe.EXME_Equipo_ID = " + xxEquipoId + " ")
//			.append("AND eq.EXME_Equipo_ID = pqe.EXME_Equipo_ID ")
//			.append("AND EXME_ProgQuiro.exme_medico_id = m.exme_medico_id");
//			
//			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_ProgQuiro"));
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//
//			int usados = 0;
//			int cantTotal = 0;
//
//			while(rs.next()) {
//				if(quirofanos.length() > 0)
//					quirofanos.append(", ");
//				quirofanos.append("Quirofano: ").append(rs.getString("Name").trim())
//                        .append(" de ").append(rs.getString("FechaIni"))
//                        .append(" a ").append(rs.getString("FechaFin"))
//                        .append(" por Medico ").append(rs.getString("Nombre_Med"));
//				//cantTotal = rs.getInt("canttotal");
//				cantTotal = rs.getInt("Disponible");
//				usados++;
//			}
//            
//			if(usados > 0)
//				if(usados < cantTotal)
//					return null;
//				else
//					return quirofanos.toString();
//			else
//				return null;
//			
//			
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE, "getHistorial", e);
//			throw new Exception(e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//            rs = null;
//            pstmt = null;
//        }
//
//	}
	
	/**
	 * Informa si un equipo determinado esta siendo utilizado en otro(s)
	 * quir&oacute;fano)s a la misma hora en la que deseamos utilizarlo para
	 * otro programa y si la disponibilidad del equipo ya fue superada.
	 *
	 * @param xxEquipoId El equipo que deseamos validar.
	 * @param fechaInicio La fecha de inicio del programa (incl. hora)
	 * @param fechaFin La fecha de fin del programa (incl. hora)
	 * @param con La conexi&oacute;n en la que hacemos la transacci&oacute;n
	 *
	 * @return Un valor String conteniendo el (los) quirofano(s) en el (los) que
	 * se est&aacute; utilizando el equipo.
	 */
	public static List<MProgQuiroEq> getByProgQuiroID(Properties ctx, int progQuiroID, String trxName) {

		List<MProgQuiroEq> retValue = new ArrayList<MProgQuiroEq>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {
			sql.append("SELECT * FROM exme_progquiroeq WHERE exme_progquiro_ID = ? ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ProgQuiroEq"));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, progQuiroID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MProgQuiroEq(ctx, rs, trxName));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getHistorial", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}
}
