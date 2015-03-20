package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

public class MEXMETratamientosServ extends X_EXME_Tratamientos_Serv implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = -2973735549564113310L;
	 private static CLogger      s_log = CLogger.getCLogger (MEXMETratamientosServ.class);
	
	/**
	 * Constructor
	 * @param ctx
	 * @param EXME_Tratamientos_Serv_ID
	 * @param trxName
	 */
	public MEXMETratamientosServ(Properties ctx, int EXME_Tratamientos_Serv_ID,
			String trxName) {
		super(ctx, EXME_Tratamientos_Serv_ID, trxName);
	}
	
	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETratamientosServ(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		cual();
	}
	
	
	private MEXMEProduct _product = null;
	
	public MEXMEProduct getProducto(){
		if(_product==null)
			_product = new MEXMEProduct(getCtx(), getM_Product_ID(), null);
		return _product;
	}

	public String getProductName(){
		return getProducto().getName();
	}

	
	public static List<MEXMETratamientosServ> getForTratamiento (Properties ctx, int EXME_Tratamiento_ID, String Where) throws SQLException {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMETratamientosServ> servicios = new ArrayList<MEXMETratamientosServ>();

		try {	            
			sql.append("SELECT EXME_Tratamientos_Serv.* FROM EXME_Tratamientos_Serv ")
			.append(" INNER JOIN EXME_Tratamientos_detalle td on EXME_Tratamientos_Serv.EXME_Tratamientos_detalle_ID = td.EXME_Tratamientos_detalle_ID")
			.append(" INNER JOIN EXME_Tratamientos t on td.EXME_Tratamientos_ID = t.EXME_Tratamientos_ID")
			.append(" WHERE t.EXME_Tratamientos_ID = ? AND EXME_Tratamientos_Serv.IsActive = 'Y'")
			.append(Where != null ? Where : "");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Tratamiento_ID);            

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMETratamientosServ trat = new MEXMETratamientosServ(ctx, rs, null);
				servicios.add(trat);
			} 

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getForTratamiento (" + sql + ")", e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

		return servicios;
	}

	public static List<MEXMETratamientosServ> getTratamientosDetalle(Properties ctx, 
 			int EXME_Tratamientos_Detalle_ID ,String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(EXME_Tratamientos_Detalle_ID);
 		
 		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
 		sql.append(" SELECT * FROM ").append(Table_Name)
 		.append(" WHERE IsActive = 'Y' AND EXME_Tratamientos_Detalle_ID = ? ");
 		
 		return  MEXMETratamientosServ.get(
 				ctx, sql.toString(), params,  trxName);

 	}
	
	/**
	 * Generico .- Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente
	 * 
	 * @param ctx contexto
	 * @param sql consulta para la tabla MEXMETratamientosServ
	 * @param params parametros para realizar la consulta ( reemplazando "?" )
	 * @param trxName nombre de transaccion
	 * @return regresa la lista con objetos MEXMETratamientosServ
	 */
 	public static List<MEXMETratamientosServ> get(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMETratamientosServ> retorno = new ArrayList<MEXMETratamientosServ>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, Table_Name);
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosServ mtd = new MEXMETratamientosServ(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMETratamientosServ", e);
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
		return new IDColumn(getEXME_Tratamientos_Serv_ID());
	}
	
	public void cual(){
		valor = new String[]{"productName","caden"};
			
			List<MEXMEActPacienteIndH> lst =  MEXMEActPacienteIndH.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_Detalle_ID(), null);
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
