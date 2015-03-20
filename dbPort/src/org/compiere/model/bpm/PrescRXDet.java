/**
 * 
 */
package org.compiere.model.bpm;


/**
 * @author twry
 *
 */
public class PrescRXDet { //extends MEXMEPrescRXDet {
//	/** log de la clase */
//	private static CLogger log = CLogger.getCLogger(PrescRXDet.class);
//	/** Movimiento de inventario */
//	private AbstractStockTransfer trans;
//	/** Backorder document */
//	private String backOrder = null;
//	/** Id chargemaster */
//	private int chargeMaster = 0;
//	/** qty Available */ 
//	private BigDecimal qtyAvailable = Env.ZERO;
//	/** level Min */
//	private BigDecimal levelMin = Env.ZERO;
//	/** Resultado de la distribucion */
//	private List<Bean4String> distribucion = new ArrayList<Bean4String>();
//	
//	/**
//	 * @param ctx
//	 * @param EXME_PrescRXDet_ID
//	 * @param trxName
//	 */
//	public PrescRXDet(Properties ctx, int EXME_PrescRXDet_ID, String trxName) {
//		super(ctx, EXME_PrescRXDet_ID, trxName);
//	}
//
//	/**
//	 * @param ctx
//	 * @param rs
//	 * @param trxName
//	 */
//	public PrescRXDet(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	}
//
//	/**
//	 * Stock
//	 * Solicitud/Surtido
//	 * Confirmación
//	 * @param confTransOrder Confirm  transfer order
//	 * @return
//	 */
//	public boolean stock(boolean confTransOrder) {
//		try {
//			if(confTransOrder){
//				trans = new ConfirmTransferOrder();
//			} else {
//				trans = new TransferRequirement();
//			}
//			
//			trans.setInfoParameter(getParameters());
//			confTransOrder = trans.startProcess(getCtx(), null);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return confTransOrder;
//	}
//	
//	/**
//	 * 	Get Parameter
//	 *	@return parameter
//	 * @throws SQLException 
//	 */
//	public ProcessInfoParameter[] getParameters() throws SQLException {
//		List<ProcessInfoParameter> ls = new ArrayList<ProcessInfoParameter>();
//		ls.add(new ProcessInfoParameter("MovementLines",Traspasos.createMovementLine(this),null,null,null));
//		ls.add(new ProcessInfoParameter("Parent",this,null,null,null));
//		ls.add(new ProcessInfoParameter("WarehouseReq",getMWarehouseID(),null,null,null));
//		ls.add(new ProcessInfoParameter("DocumentNo","",null,null,null));
//		ls.add(new ProcessInfoParameter("Description","",null,null,null));
//		ls.add(new ProcessInfoParameter("Priority","5",null,null,null));
//		ls.add(new ProcessInfoParameter("ProgQuiro","0",null,null,null));
//		
//		final ProcessInfoParameter[] pars = new ProcessInfoParameter[ls.size()];
//		ls.toArray(pars);
//		return pars;
//	}
//	
//	public String getBackOrder() {
//		return backOrder;
//	}
//
//	public void setBackOrder(final String backOrder) {
//		this.backOrder = backOrder;
//	}
//
//	public int getChargeMaster() {
//		return chargeMaster;
//	}
//
//	public void setChargeMaster(final int chargeMaster) {
//		this.chargeMaster = chargeMaster;
//	}
//
//	public BigDecimal getQtyAvailable() {
//		if(qtyAvailable==null){
//			qtyAvailable = Env.ZERO;
//		}
//		return qtyAvailable;
//	}
//
//	public void setQtyAvailable(final BigDecimal qtyAvailable) {
//		this.qtyAvailable = qtyAvailable;
//	}
//
//	public BigDecimal getLevelMin() {
//		return levelMin;
//	}
//
//	public void setLevelMin(final BigDecimal levelMin) {
//		this.levelMin = levelMin;
//	}
//	public List<Bean4String> getDistribucion() {
//		return distribucion;
//	}
//	public void setDistribucion(List<Bean4String> distribucion) {
//		this.distribucion = distribucion;
//	}
//	
//	public int getMWarehouseID (){
//		int mWarehouseID = 0;
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
//		.append(" SELECT wr.M_Warehouse_ID ") 
//		.append(" FROM  EXME_PrescRXDet ")
//		.append(" INNER JOIN EXME_PrescRX    prx ON prx.EXME_PrescRX_ID = EXME_PrescRXDet.EXME_PrescRX_ID ")
//		.append(" INNER JOIN EXME_CtaPac     cta ON cta.EXME_CtaPac_ID  = prx.EXME_CtaPac_ID ")
//		.append(" INNER JOIN EXME_EstServAlm wr  ON wr.EXME_EstServ_ID  = cta.EXME_EstServ_ID AND wr.IsActive = 'Y' ")
//		.append(" WHERE EXME_PrescRXDet.EXME_PrescRXDet_ID = ? ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, getEXME_PrescRXDet_ID());
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				mWarehouseID = rs.getInt(1);
//			}
//		}catch (Exception e) {
//			log.log(Level.SEVERE, "getMWarehouseID", e);
//		}finally {
//			DB.close(rs, pstmt);
//		}
//		return mWarehouseID;
//	}
}
