package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;



public class MEXMETratamientosCues extends X_EXME_Tratamientos_Cues implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = -3950633118090061065L;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param EXME_Tratamientos_Cues_ID
	 * @param trxName
	 */
	public MEXMETratamientosCues(Properties ctx, int EXME_Tratamientos_Cues_ID,
			String trxName) {
		super(ctx, EXME_Tratamientos_Cues_ID, trxName);
	}

	public MEXMETratamientosCues(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		cual();
	}

	private MCuestionario _cuestionario = null;
	public MCuestionario getCuestionario(){
		if(_cuestionario== null)
			_cuestionario = new MCuestionario(getCtx(), getEXME_Cuestionario_ID(), null);
		return _cuestionario;
	}
	
	public String getCuestionarioName(){
		return getCuestionario().getName();
	}
	
	/** */
	private static CLogger	s_log = CLogger.getCLogger (MEXMETratamientosCues.class);
	/**
	 * Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente solo activos 
	 * @param ctx contexto
	 * @param EXME_Tratamientos_Detalle_ID identificador de la relacion tratamiento - detalle 
	 * (cada detalle del tratamiento es una sesion)
	 * @param trxName nombre de transaccion
	 * @return Listado de objetos MEXMETratamientosCues
	 */
	public static List<MEXMETratamientosCues> getTratamientosDetalle(Properties ctx, 
 			int EXME_Tratamientos_Detalle_ID, String WhereClause, String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(EXME_Tratamientos_Detalle_ID);
 		
 		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
 		sql.append(" SELECT * FROM EXME_Tratamientos_Cues ")
 		.append(" WHERE IsActive = 'Y' AND EXME_Tratamientos_Detalle_ID = ? ")
 		.append(WhereClause != null ? WhereClause : "");
 		
 		return  MEXMETratamientosCues.get(
 				ctx, sql.toString(), params,  trxName);

 	}
	
	/**
	 * Generico .- Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente
	 * 
	 * @param ctx contexto
	 * @param sql consulta para la tabla MEXMETratamientosCues
	 * @param params parametros para realizar la consulta ( reemplazando "?" )
	 * @param trxName nombre de transaccion
	 * @return regresa la lista con objetos MEXMETratamientosCues
	 */
 	public static List<MEXMETratamientosCues> get(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMETratamientosCues> retorno = new ArrayList<MEXMETratamientosCues>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, X_EXME_Tratamientos_Cues.Table_Name);
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosCues mtd = new MEXMETratamientosCues(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
 	}

 	private String[] valor = null;

 	@Override
 	public String[] getColumns() {
 		return valor;
 	}

 	@Override
 	public IDColumn getIdColumn() {
 		return new IDColumn(getEXME_Tratamientos_Cues_ID());
 	}

 	public void cual(){

 		valor = new String[]{"cuestionarioName","caden"};

 		List<MEXMEActPacienteDet> lst =  MEXMEActPacienteDet.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_Detalle_ID(), null);
 		for (int i = 0; i < lst.size(); i++) {
 			caden = " Aplicado el "+lst.get(i).getCreated();
 		}

 	}

 	private String caden = null;

 	public String getCaden() {
 		return caden;
 	}

 	public void setCaden(String caden) {
 		this.caden = caden;
 	}
}
