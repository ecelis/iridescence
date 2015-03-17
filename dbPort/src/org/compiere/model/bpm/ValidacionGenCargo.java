package org.compiere.model.bpm;


/**
 * Validacion de generacion de cargos
 * si se permite o no
 * @author Expert
 * @deprecated
 */
public class ValidacionGenCargo {
//	/** Static Logger */
//	private static CLogger log = CLogger.getCLogger(ValidacionGenCargo.class);
//	
//	/**
//	 * Constructor
//	 * @param EXME_CtaPac_ID
//	 */
//	public ValidacionGenCargo (Properties ctx , int EXME_CtaPac_ID , String trxName ){
//		super();
//		
//	}
	
	/**
	 * Valida si se pueden generar los cargos o no
	 * @return true : se generan los cargos 
	 */
//	public static boolean sePuedeGenerarCargo(Properties pCtx , int EXME_CtaPac_ID , String pTrxName){
//		boolean exito = true;
//		
//		if(tieneTodosLosPrecios(pCtx , EXME_CtaPac_ID , pTrxName)){
//			exito = tieneTodosLosBeneficio(pCtx , EXME_CtaPac_ID , pTrxName);
//		} else {
//			exito = false;
//		}
//		
//		return exito;
//	}
	
//	/**
//	 * Valida si se tienen los precios
//	 * @return
//	 */
//	public static  boolean tieneTodosLosPrecios(Properties pCtx , int EXME_CtaPac_ID , String pTrxName){
//		try {
//			List<MEXMEActPacienteInd> lst = ValidacionGenCargo.getSinPrecios(pCtx, EXME_CtaPac_ID, pTrxName);
//			if(lst!=null && lst.size()>0){
//				// tiene cargos sin precio
//				return false; //TODO: Mejorar se podr�a asignar los precios desde este momento
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * Valida si se tienen los beneficios
//	 * @return
//	 */
//	public static  boolean tieneTodosLosBeneficio(Properties pCtx , int EXME_CtaPac_ID , String pTrxName){
//		boolean exito = true;
//		try {
//			// hay unos que no tienen retorna true;
//			if(ValidacionGenCargo.getSinRevCode(pCtx, EXME_CtaPac_ID, pTrxName)){
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			exito = false;
//		}
//		return exito;
//	}

	/**
	 * Valida que todo el detalle de la cuenta paciente
	 * tenga precio PriceActual >0 
	 * @param ctx
	 * @param actPac
	 * @param tipo
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
//	public static List<MEXMEActPacienteInd> getSinPrecios(Properties ctx, int ctapac, String trxName) throws Exception {
//		
//		
//		
//		
//		
//		HashMap<Integer, List<MEXMEActPacienteInd>>
//		mapa = AsignacionPrecios.getLstDetalle(getCtx(), ctapac);
//
//		
//		
//		
//		
//		
//		
//		
//		
//		final List<MEXMEActPacienteInd> lista = new ArrayList<MEXMEActPacienteInd>();
//
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" SELECT EXME_ActPacienteInd.* ")
//		.append(" FROM EXME_ActPacienteInd ")
//		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.EXME_ActPacienteIndH_ID = EXME_ActPacienteInd.EXME_ActPacienteIndH_ID) ")
//		.append(" WHERE EXME_ActPacienteInd.IsActive = 'Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_ActPacienteInd.Table_Name))
//		.append(" AND indh.IsActive = 'Y' ")
//		.append(" AND indh.EXME_CtaPac_ID = ?  ")
//		.append(" AND ( EXME_ActPacienteInd.PriceActual IS NULL OR EXME_ActPacienteInd.PriceActual <=0 )  ");
//		
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setLong(1, ctapac);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEActPacienteInd(ctx, rs, trxName));
//			}
//		} catch (SQLException e) {
//			log.log(Level.SEVERE, "getSinPrecios", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}
	
//	/**
//	 * Valida que todo el detalle de la cuenta paciente
//	 * tenga precio PriceActual >0 
//	 * @param ctx
//	 * @param actPac
//	 * @param tipo
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean getSinRevCode(Properties ctx, int ctapac, String trxName) throws Exception {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" SELECT NVL(r.EXME_RevenueCode_ID,0) AS EXME_RevenueCode_ID ")
//		.append(" FROM EXME_ActPacienteInd  api ")
//		.append(" INNER JOIN exme_actpacienteIndh apih on api.exme_actpacienteIndh_id = apih.exme_actpacienteIndh_id ")
//		.append(" INNER JOIN M_Warehouse w on apih.M_Warehouse_ID = w.M_Warehouse_ID AND w.EXME_RevenueCode_ID IS NULL  ")
//		.append(" INNER JOIN M_Replenish r on api.M_Product_ID = r.M_Product_ID AND api.M_Warehouse_ID = r.M_Warehouse_ID AND r.EXME_RevenueCode_ID IS NULL ")
//		.append(" WHERE api.IsActive = 'Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_ActPacienteInd.Table_Name, "api"))
//		.append(" AND apih.EXME_CtaPac_ID = ? ");
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setLong(1, ctapac);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				if(rs.getInt("EXME_RevenueCode_ID")<=0){
//					return true;
//				}
//			}
//		} catch (SQLException e) {
//			log.log(Level.SEVERE, "getSinRevCode", e);
//			return false;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return false;
//	}
}
