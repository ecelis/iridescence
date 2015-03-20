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

/**
 * 
 * @author Expert
 * 
 */
public class MEXMETratamientosPaq extends X_EXME_Tratamientos_Paq implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = 8487540819361769811L;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param EXME_Tratamientos_Paq_ID
	 * @param trxName
	 */
	public MEXMETratamientosPaq(Properties ctx, int EXME_Tratamientos_Paq_ID,
			String trxName) {
		super(ctx, EXME_Tratamientos_Paq_ID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETratamientosPaq(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		cual();
	}

	private MEXMEPaqBase _paquete = null;
	
	public MEXMEPaqBase getPaquete(){
		if(_paquete==null)
			_paquete = new MEXMEPaqBase(getCtx(), getEXME_PaqBase_ID(), null);
		
		return _paquete;
	}
	
	public String getPaqueteName(){
		return getPaquete().getName();
	}

	/** */
	private static CLogger	s_log = CLogger.getCLogger (MEXMETratamientosPaq.class);
	/**
	 * Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente solo activos 
	 * @param ctx contexto
	 * @param EXME_Tratamientos_Detalle_ID identificador del tratamiento
	 * (cada detalle del tratamiento es una sesion)
	 * @param trxName nombre de transaccion
	 * @return Listado de objetos MEXMETratamientosPaq
	 */
	public static List<MEXMETratamientosPaq> getTratamientosDetalle(Properties ctx, int EXME_Tratamientos_Detalle_ID, String WhereClause, String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(EXME_Tratamientos_Detalle_ID);
 		
 		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
 		sql.append(" SELECT * FROM ").append(Table_Name)
 		.append(" WHERE IsActive = 'Y' AND EXME_Tratamientos_Detalle_ID = ? ")
 		.append(WhereClause != null ? WhereClause : "");
 		
 		return  MEXMETratamientosPaq.get(
 				ctx, sql.toString(), params,  trxName);

 	}
	
	/**
	 * Generico .- Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente
	 * 
	 * @param ctx contexto
	 * @param sql consulta para la tabla MEXMETratamientosPaq
	 * @param params parametros para realizar la consulta ( reemplazando "?" )
	 * @param trxName nombre de transaccion
	 * @return regresa la lista con objetos MEXMETratamientosPaq
	 */
 	public static List<MEXMETratamientosPaq> get(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMETratamientosPaq> retorno = new ArrayList<MEXMETratamientosPaq>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, X_EXME_Tratamientos_Paq.Table_Name);
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosPaq mtd = new MEXMETratamientosPaq(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
			e.printStackTrace();
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
 		return new IDColumn(getEXME_Tratamientos_Paq_ID());
 	}

 	public void cual(){

 		valor = new String[]{"paqueteName","caden"};

// 		List<MCtaPacDet> lst =  null;//MCtaPacDet.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_Detalle_ID(), null);
// 		for (int i = 0; i < lst.size(); i++) { //null pointer
// 			caden = " Aplicado el "+lst.get(i).getCreated();
// 		}
 	}

 	private String caden = null;

 	public String getCaden() {
 		return caden;
 	}

 	public void setCaden(String caden) {
 		this.caden = caden;
 	}
}
