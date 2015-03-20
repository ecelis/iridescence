/**
 * 
 */
package org.compiere.model;


/**
 * @author LLama
 * @deprecated
 */
public class MEXMEWarehouse {} // extends MWarehouse {

//	/** serialVersionUID */
//	private static final long serialVersionUID = 3263934060431545499L;
//
//	public MEXMEWarehouse(Properties ctx, int M_Warehouse_ID, String trxName) {
//		super(ctx, M_Warehouse_ID, trxName);
//		if (M_Warehouse_ID == 0) {
//			setSeparator("*");
//		}
//	}
//	
//    public MEXMEWarehouse(Properties ctx, ResultSet rs, String trxName) {
//        super(ctx, rs, trxName);
//    }
//
//    /** Cache                   */
//    @SuppressWarnings("unused")
//	private static CCache<Integer,MWarehouse> s_cache = new CCache<Integer,MWarehouse>("M_Warehouse", 5);
//    /** Static Logger   */
//    private static CLogger  s_log   = CLogger.getCLogger (MWarehouse.class);
//    
//    /**
//     * 
//     * @param value
//     * @return
//     */
//    public static MEXMEWarehouse get(Properties ctx, String value, String trxName){
//        
//        MEXMEWarehouse retValue = null;
//        //String sql = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//                
//        String sql = "SELECT * FROM M_Warehouse WHERE Value = ? AND IsActive = 'Y' ";
//
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setString(1, value);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                retValue = new MEXMEWarehouse(ctx, rs, trxName);
//            }
//        } catch (SQLException e) {
//            s_log.log(Level.SEVERE, sql.toString(), e);
//        } finally {
//        	DB.close(rs, pstmt);
//            rs = null;
//            pstmt = null;
//        }
//       
//        return retValue;
//        
//    }
//	public static MEXMEWarehouse[] getForOrg (Properties ctx, int AD_Org_ID)
//	{
//		
//		List<MEXMEWarehouse> list = new ArrayList<MEXMEWarehouse>();
//		
//		//Almacenes por organizacion
//		list=MEXMEWarehouse.getLstForOrg (ctx, AD_Org_ID);
//		
//		//Que sea arreglo
//		MEXMEWarehouse[] retValue = new MEXMEWarehouse[list.size ()];
//		list.toArray (retValue);
//		
//		return retValue;
//	}	//	get
//	
//	/**
//	 * Obtiene y modifica el almacen y estacion de servicio en contexto
//	 * 
//	 * @param ctx
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMEWarehouse getForOrg(Properties ctx, String trxName) {
//		MEXMEWarehouse warehouse = null;
//		MEXMEEstServ estServ = MEXMEEstServ.getFirstEstServ(ctx, trxName);
//		if (estServ != null) {
//			MEXMEWarehouse[] arr = estServ.getWarehouseRel(null);
//			if (arr.length > 0) {
//				warehouse = arr[0];
//				Env.setContext(ctx, "#EXME_EstServ_ID", estServ.getEXME_EstServ_ID());
//				Env.setContext(ctx, "#M_Warehouse_ID", warehouse.getM_Warehouse_ID());
//			}
//		}
//		return warehouse;
//	}
//	
//	/**
//	 * 	Get Warehouses for Org
//	 *	@param ctx context
//	 *	@param AD_Org_ID id
//	 *	@return warehouse
//	 */
//	public static List<MEXMEWarehouse> getLstForOrg (Properties ctx, int AD_Org_ID)
//	{
//		List<MEXMEWarehouse> list = new ArrayList<MEXMEWarehouse>();
//		String sql = "SELECT * FROM M_Warehouse WHERE AD_Org_ID=? ORDER BY Name";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement (sql, null);
//			pstmt.setInt (1, AD_Org_ID);
//			rs = pstmt.executeQuery ();
//			
//			while (rs.next ())
//				list.add (new MEXMEWarehouse (ctx, rs, null));
//			
//		}
//		catch (Exception e)
//		{
//			s_log.log(Level.SEVERE, sql, e);
//		}
//		finally
//		{
//			DB.close(rs, pstmt);
//			pstmt = null;
//		}
//		return list;
//	}	//	get
//    
//	/**
//	 * 	Get Warehouses for Org
//	 *	@param ctx context
//	 *	@param AD_Org_ID id
//	 *	@return warehouse
//	 */
//	public static List<LabelValueBean> getLstForOrgLVB (Properties ctx, boolean blanco)
//	{
//		StringBuilder sql = new StringBuilder(100); 
//		sql.append(" SELECT Value||'-'||Name AS nombre, M_Warehouse_ID ")
//		.append(" FROM M_Warehouse WHERE IsActive = 'Y' ")
//		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Warehouse")))
//		.append(" ORDER BY Value ");
//		
//		return MEXMEWarehouse.getLstForOrgLVB (ctx, sql.toString(), blanco, null);
//	}
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param almacen
//	 * @param blanco
//	 * @return
//	 */
//	public static List<LabelValueBean> getLstProductClassLVB (Properties ctx, int almacen, boolean blanco)
//	{
//		StringBuilder sql = new StringBuilder(100); 
//		sql.append(" SELECT w.Value||'-'||w.Name AS nombre, w.M_Warehouse_ID ")
//		.append(" FROM M_Warehouse w ")
//		// Almacenes que tenga una solicitud. Sin estatus para la funcionalidad de editar
//		.append(" INNER JOIN EXME_ActPacienteIndH apih ON apih.M_Warehouse_Sol_ID  = w.M_Warehouse_ID ")
//		.append(" WHERE w.IsActive = 'Y' ")
//		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Warehouse", "w")))
//		// Almacen que aplica es el mismo de login
//		.append(" AND apih.M_Warehouse_ID = ? ")
//		.append(" GROUP BY w.Value, w.Name, w.M_Warehouse_ID ")
//		.append(" ORDER BY w.Value ");
//		Object[] params = new Object[]{almacen};
//		
//		return MEXMEWarehouse.getLstForOrgLVB (ctx, sql.toString(), blanco, params);
//	}
//	
//	/**
//	 * 	Get Warehouses for Org
//	 *	@param ctx context
//	 * @param params 
//	 *	@param AD_Org_ID id
//	 *	@return warehouse
//	 */
//	public static List<LabelValueBean> getLstForOrgLVB (Properties ctx, String query, boolean blanco, Object[] params)
//	{
//		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement (query.toString(), null);
//			DB.setParameters(pstmt, params);
//			rs = pstmt.executeQuery ();
//			if(blanco)
//				list.add(new LabelValueBean(" ","0"));
//			while (rs.next ())
//				list.add (new LabelValueBean(rs.getString(1),String.valueOf(rs.getInt(2))));
//		}
//		catch (Exception e)
//		{
//			s_log.log(Level.SEVERE, query.toString(), e);
//		}
//		finally{
//			DB.close(rs,pstmt);
//		}
//		return list;
//	}	//	get
//    
//	
//    /**
//     * Obtiene el almacen
//     * @param ctx
//     * @param M_Movement_ID
//     * @param trxName
//     * @return
//     */
//    public static MEXMEWarehouse getFromLocator(Properties ctx, int M_Locator_ID, String trxName){
//        
//        MEXMEWarehouse retValue = null;
//        
//        StringBuilder sql = new StringBuilder("SELECT M_Warehouse.* ")
//            .append("FROM M_Warehouse ")
//            .append("INNER JOIN M_Locator ON M_Locator.M_Warehouse_ID = M_Warehouse.M_Warehouse_ID ")
//            .append("WHERE M_Warehouse.IsActive = 'Y' ")
//            .append("AND M_Locator.M_Locator_ID = ? ");
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Warehouse"));
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, M_Locator_ID);
//            rs = pstmt.executeQuery();
//        
//            if (rs.next()) {
//                retValue = new MEXMEWarehouse(ctx, rs, trxName);
//            }
//            
//        } catch (Exception e) {
//            s_log.log(Level.SEVERE, sql.toString(), e);
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//                
//        return retValue;        
//    }
//
//    /**
//     * Obtiene lista de almacenes ordenados por su nombre
//     * @param ctx
//     * @param trxName
//     * @return
//     */
//    public static List<LabelValueBean> getAll(Properties ctx, String trxName){
//		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
//		StringBuilder sql = new StringBuilder();
//		ResultSet rs = null;
//		sql.append("select * from M_WAREHOUSE ")
//		   .append("where M_WAREHOUSE.isActive = 'Y' ")	;
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Warehouse"));
//		sql.append(" ORDER BY M_WAREHOUSE.name ");
//		PreparedStatement pstmt = null;
//        try {
//        	pstmt = DB.prepareStatement(sql.toString(), trxName);        	
//        	rs = pstmt.executeQuery();        	
//        	while(rs.next()){
//        		LabelValueBean res = new LabelValueBean(rs.getString("Name"), rs.getString("M_Warehouse_ID"));
//        		lista.add(res);
//        	}
//        } catch (Exception e) {
//			lista = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}
//    
//    /*** merge */
//    
//    /**
//     * Estacion y Area a los cuales pertenece el almacen
//     * @param ctx
//     * @param trxName
//     * @return
//     */
//    public static LabelValueBean getEstacionArea(Properties ctx, int almacenId, String trxName){
//		LabelValueBean lista = null;
//		StringBuilder sql = new StringBuilder();
//		ResultSet rs = null;
//		
//		sql.append(" SELECT es.exme_estserv_id, es.exme_area_id ")
//		.append(" FROM M_Warehouse w ")
//		.append(" INNER JOIN EXME_EstServAlm esa ON w.m_warehouse_id = esa.m_warehouse_id ")
//		.append(" INNER JOIN EXME_EstServ es ON esa.EXME_EstServ_ID = es.EXME_EstServ_ID ")
//		.append(" WHERE w.M_Warehouse_ID = ? ");
//		
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_Warehouse", "w"));
//		PreparedStatement pstmt = null;
//        
//		try {
//        	pstmt = DB.prepareStatement(sql.toString(), trxName);  
//        	pstmt.setInt(1, almacenId);
//        	rs = pstmt.executeQuery();        	
//        	 
//        	if (rs.next()){
//        		lista = new LabelValueBean(String.valueOf(rs.getInt(1)), String.valueOf(rs.getInt(2)));
//        	}
//
//        } catch (Exception e) {
//			lista = null;
//		} finally {
//			DB.close(rs,pstmt);
//		}
//		return lista;
//	}
//    
//    /**
//	 * Nos trae la lista de almacenes que estan relacionados a un producto
//	 * @param ctx Contexto
//	 * @param active Estatus de los registros a consultar (Y/N), en caso de que se deseen todos los registros mandar null
//	 * @param productId Id del producto del que obtendremos los almacenes relacionados
//	 * @return List<MWarehouse>
//	 * @throws Exception
//	 * @author mvrodriguez
//	 *
//	public static List<MWarehouse> getWarehouseReplenish(Properties ctx, int productId) throws Exception {
//		
//		List<MWarehouse> list = new ArrayList<MWarehouse>();
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql
//		.append(" SELECT w.*, rc1.Value ||' - '|| rc1.Name AS RvCRe, rc1.EXME_RevenueCode_ID AS RvCReID, ")
//		.append("             rc2.Value ||' - '|| rc2.Name AS RvCWr, rc1.EXME_RevenueCode_ID AS RvCWrID ")
//		.append("   FROM M_Replenish r                                   ")
//		.append("  INNER JOIN  M_Warehouse       w                       ")
//		.append("     ON r.M_Warehouse_ID = w.M_Warehouse_ID             ")
//		.append("    AND r.IsActive = 'Y'                                ")
//		.append("   LEFT JOIN  EXME_RevenueCode rc1                      ")
//		.append("     ON r.EXME_RevenueCode_ID = rc1.EXME_RevenueCode_ID ")
//		.append("    AND rc1.IsActive = 'Y'                              ")
//		.append("   LEFT JOIN  EXME_RevenueCode rc2                      ")
//		.append("     ON w.EXME_RevenueCode_ID = rc2.EXME_RevenueCode_ID ")
//		.append("    AND rc2.IsActive = 'Y'                              ")
//		.append("  WHERE r.IsActive = 'Y'                                ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_Replenish","r"));
//		sql.append(" AND r.M_Product_ID = ? ) ");
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, productId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MWarehouse alm = new MWarehouse(ctx, rs, null);
//				alm.setRevCodeAlm(new LabelValueBean(rs.getString("RvCWr"), String.valueOf(rs.getInt("RvCWrID"))));
//				alm.setRevCodeRep(new LabelValueBean(rs.getString("RvCRe"), String.valueOf(rs.getInt("RvCReID"))));
//				list.add(alm);
//			}
//               
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getWarehouseRelatedProduct - sql = " + sql.toString(), e);
//    	} finally {
//    		DB.close(rs, pstmt);
//    	}
//		return list;
//	}
//	*/
//	
//	public void setSelected(boolean selected) {
//		this.selected = selected;
//	}
//
//	public boolean isSelected() {
//		return selected;
//	}
//
//	private boolean selected = false;
//	
//	  /**
//	 * Nos trae la lista de almacenes que estan relacionados a un producto
//	 * @param ctx Contexto
//	 * @param active Estatus de los registros a consultar (Y/N), en caso de que se deseen todos los registros mandar null
//	 * @param productId Id del producto del que obtendremos los almacenes relacionados
//	 * @return List<MEXMEReplenish>
//	 * @throws Exception
//	 * @author mvrodriguez
//	 */
//	public static List<MEXMEReplenish> getReplenishRevenueCode(Properties ctx, int productId) throws Exception {
//		
//		List<MEXMEReplenish> list = new ArrayList<MEXMEReplenish>();
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append(" SELECT r.*, ")
//		.append("  w.Name AS AlmacenName, w.M_Warehouse_ID AS AlmacenID  ")
//		.append("   FROM M_Replenish r                                   ")
//		.append("  INNER JOIN  M_Warehouse       w                       ")
//		.append("     ON r.M_Warehouse_ID = w.M_Warehouse_ID             ")
//		.append("    AND w.IsActive = 'Y'                                ")
//		.append("  WHERE r.IsActive = 'Y'                                ");
//		
//		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_Replenish","r"));
//		sql.append(" AND r.M_Product_ID = ?  ");
//		
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, productId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				MEXMEReplenish alm = new MEXMEReplenish(ctx, rs, null);
//				alm.setLbvAlmacen(new LabelValueBean(rs.getString("AlmacenName"), String.valueOf(rs.getInt("AlmacenID"))));
//				list.add(alm);
//			}
//               
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getWarehouseRelatedProduct - sql = " + sql.toString(), e);
//    	} finally {
//    		DB.close(rs, pstmt);
//    	}
//		return list;
//	}
//	
//	public void setProductClassWhsID(int productClassWhsID) {
//		this.productClassWhsID = productClassWhsID;
//	}
//
//	public int getProductClassWhsID() {
//		return productClassWhsID;
//	}
//
//	private int productClassWhsID = 0;
//	
//	public static int getSterilization(Properties ctx) {
//		int id = -1;
//		List<LabelValueBean> lstAlmacenes;
//		try {
//			lstAlmacenes = Datos.getEstServAlm(ctx, Env.getEXME_EstServ_ID(ctx), true);
//			for (int j = 0; j < lstAlmacenes.size(); j++) {
//				MWarehouse wh = MWarehouse.get(ctx, Integer.parseInt(lstAlmacenes.get(j).getValue()));
//				if (MWarehouse.TYPE_Sterilization.equalsIgnoreCase(wh.getType())) {
//					id = wh.getM_Warehouse_ID();
//					break;
//				}
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, null, e);
//		}
//
//		return id;
//	}
//}
