/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
 * Modelo de la tabla Tratamientos Productos
 * @author Lorena Lama
 *
 */
public class MEXMETratamientosProductos extends X_EXME_Tratamientos_Productos implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = 80669034245409058L;

	/**
	 * @param ctx
	 * @param EXME_Tratamientos_Productos_ID
	 * @param trxName
	 */
	public MEXMETratamientosProductos(Properties ctx, int EXME_Tratamientos_Productos_ID,
			String trxName) {
		super(ctx, EXME_Tratamientos_Productos_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETratamientosProductos(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		cual();
	}
	
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMETratamientosProductos.class);

	/**
	 * Regresa los tratamientos productos para un tratamiento
	 * @param ctx
	 * @param EXME_Tratamientos_ID
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosProductos> get(Properties ctx, int EXME_Tratamientos_ID, 
			String whereClause, String trxName){
		List<MEXMETratamientosProductos> retValue = new ArrayList<MEXMETratamientosProductos>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT * FROM EXME_Tratamientos_Productos ")
			.append(" WHERE isActive = 'Y' ");

			if(EXME_Tratamientos_ID>0)
				sql.append(" AND EXME_Tratamientos_ID = ? ");
			
			sql.append(whereClause != null ? whereClause : "")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, EXME_Tratamientos_ID);
			rs = psmt.executeQuery();

			while (rs.next()) {
				retValue.add(new MEXMETratamientosProductos(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}
/* t
	private MPaqBase_Version m_pqbVersion = null;
	private MProduct m_product = null;
	private MEXMETratamientos m_treatment = null;

	public MPaqBase_Version getPaqBaseVersion() {
		if (m_pqbVersion == null && getEXME_PaqBase_Version_ID() != 0)
			m_pqbVersion = new MPaqBase_Version(getCtx(), getEXME_PaqBase_Version_ID(), get_TrxName());
		return m_pqbVersion;
	}
	public MProduct getProduct() {
		if (m_product == null && getM_Product_ID() != 0)
			m_product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		return m_product;
	}

	public MEXMETratamientos getTreatment() {
		if (m_treatment == null && getEXME_Tratamientos_ID() != 0)
			m_treatment = new MEXMETratamientos(getCtx(), getEXME_Tratamientos_ID(), get_TrxName());
		return m_treatment;
	}
	*/
	private int warehouseID = 0;

	public int getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
	}
	
	private Timestamp fechaServ = null;

	public Timestamp getFechaServ() {
		return fechaServ;
	}

	public void setFechaServ(Timestamp fechaServ) {
		this.fechaServ = fechaServ;
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
	 * 
	 * @param ctx
	 * @param EXME_Tratamientos_Detalle_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosProductos> getTratamientosDetalle(Properties ctx, 
 			int EXME_Tratamientos_Detalle_ID, String WhereClause, String trxName){

 		List<Integer> params = new ArrayList<Integer>();
 		params.add(EXME_Tratamientos_Detalle_ID);
 		
 		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
 		sql.append(" SELECT * FROM ").append(Table_Name)
 		.append(" WHERE IsActive = 'Y' AND EXME_Tratamientos_Detalle_ID = ? ")
 		.append(WhereClause != null ? WhereClause : "");
 		
 		return  MEXMETratamientosProductos.get(
 				ctx, sql.toString(), params,  trxName);

 	}
	
	/**
	 * Generico .- Obtenemos las sesiones de un tratamiento
	 * asignado a un paciente
	 * 
	 * @param ctx contexto
	 * @param sql consulta para la tabla MEXMETratamientosProductos
	 * @param params parametros para realizar la consulta ( reemplazando "?" )
	 * @param trxName nombre de transaccion
	 * @return regresa la lista con objetos MEXMETratamientosProductos
	 */
 	public static List<MEXMETratamientosProductos> get(
			Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMETratamientosProductos> retorno = new ArrayList<MEXMETratamientosProductos>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, Table_Name);
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosProductos mtd = new MEXMETratamientosProductos(
						ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMETratamientosProductos", e);
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
 		return new IDColumn(getEXME_Tratamientos_Productos_ID());
 	}

 	public void cual(){

 		valor = new String[]{"productName","caden"};

 		List<MEXMESalidaGasto> lst =  MEXMESalidaGasto.getTratamientosDetalle(getCtx(), getEXME_Tratamientos_Detalle_ID(), null);
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
