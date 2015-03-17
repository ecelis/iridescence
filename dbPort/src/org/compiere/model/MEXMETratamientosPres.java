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
public class MEXMETratamientosPres extends X_EXME_Tratamientos_Pres implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = 5818301959025955545L;
	/** Static logger */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMETratamientosPres.class);
	/**
	 * Constructor
	 * @param ctx
	 * @param EXME_Tratamientos_Pres_ID
	 * @param trxName
	 */
	public MEXMETratamientosPres(Properties ctx, int EXME_Tratamientos_Pres_ID,
			String trxName) {
		super(ctx, EXME_Tratamientos_Pres_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param EXME_Tratamientos_Pres_ID
	 * @param trxName
	 */
	public MEXMETratamientosPres(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		cual();
	}

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosPres> getDetalle(Properties ctx, String sql,
			List<?> params, String trxName) {

		List<MEXMETratamientosPres> retorno = new ArrayList<MEXMETratamientosPres>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosPres mtd = new MEXMETratamientosPres(ctx, rs, trxName);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getDetalle", e);
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
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
	/**
	 * Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente solo activos 
	 * @param ctx contexto
	 * @param EXME_Tratamientos_Detalle_ID identificador del tratamiento
	 * (cada detalle del tratamiento es una sesion)
	 * @param trxName nombre de transaccion
	 * @return Listado de objetos MEXMETratamientosPres
	 */
	public static List<MEXMETratamientosPres> getTratamientosDetalle(Properties ctx, 
 			int EXME_Tratamientos_Detalle_ID ,String WhereClause, String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(EXME_Tratamientos_Detalle_ID);
 		
 		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
 		sql.append(" SELECT * FROM ").append(Table_Name)
 		.append(" WHERE IsActive = 'Y' AND EXME_Tratamientos_Detalle_ID = ? ")
 		.append(WhereClause != null ? WhereClause : "");
 		return  MEXMETratamientosPres.getDetalle(
 				ctx, sql.toString(), params,  trxName);

 	}

	private String[] valor = null;	
	
	@Override
	public String[] getColumns() {
		return valor;
	}

	@Override
	public IDColumn getIdColumn() {
		return new IDColumn(getEXME_Tratamientos_Pres_ID());
	}
	
	public void cual(){

		valor = new String[]{"productName","caden"};

		List<MPlanMed> lst =  MPlanMed.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_Detalle_ID(), null);
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
