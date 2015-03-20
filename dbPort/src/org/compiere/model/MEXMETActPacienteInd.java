/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.StorageVO;
import org.compiere.util.StorageVOList;

/**
 * Modelo para aplicacion de tratamientos
 * @author Lorena Lama
 */
public class MEXMETActPacienteInd {/* extends X_EXME_T_ActPacienteInd {

	/**
	 * 
	 
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param EXME_T_ActPacienteInd_ID
	 * @param trxName
	 *
	public MEXMETActPacienteInd(Properties ctx, int EXME_T_ActPacienteInd_ID, String trxName) {
		super(ctx, EXME_T_ActPacienteInd_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 *
	public MEXMETActPacienteInd(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	private static CLogger		s_log = CLogger.getCLogger (MEXMETActPacienteInd.class);

	private LabelValueBean btpProducto = null;
	private LabelValueBean lvbUOM = null;
	private String tratamientoName = null;
	private String paqueteName = null;
	private boolean editable = true;
	private boolean lote = false;	
	private BigDecimal differenceQty = Env.ZERO;
	private boolean disable = false;
	private boolean disableA = false;
	private boolean disableLine = false;
	
	private MProduct prod = null;
	private MUOM m_uom = null;
	private MPaqBase_Version paq = null;
	private MEXMETratamientos trat = null;
	
	/**
	 * 
	 * @param ctx
	 * @param citaMedicaID
	 * @param sessionID
	 * @param trxName
	 * @return
	 *
	public static ArrayList<MEXMETActPacienteInd> getFromCita(Properties ctx, int citaMedicaID, String trxName) {
		ArrayList<MEXMETActPacienteInd> retValue = new ArrayList<MEXMETActPacienteInd>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append(" SELECT EXME_T_ActPacienteInd.* ")
				.append(" FROM EXME_T_ActPacienteInd ")
				.append(" WHERE EXME_T_ActPacienteInd.EXME_CitaMedica_ID = ? ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1, citaMedicaID);
			
			rs = psmt.executeQuery();
			int i = 0;
			
			while (rs.next()) {
				MEXMETActPacienteInd obj = new MEXMETActPacienteInd(ctx, rs, trxName);
				
				// las l�neas deshabilitadas // si el estatus es S - tanto reserved como delivered
				if (obj.isAlergic() 
						|| obj.getEstatus().equals(ESTATUS_CancelledService) 
						|| obj.getEstatus().equals(ESTATUS_CompletedService)
						|| obj.getEstatus().equals(ESTATUS_RequestedService)) {
					obj.setDisableLine(true);
					obj.setDisableA(true);
					obj.setDisable(true);
				}
				// si el estatus es P - solo reserved
				if (obj.getEstatus().equals(ESTATUS_ScheduleService)) 
					obj.setDisable(true);
				obj.setEditable(false);
				
				// tratamientos
				int tratID = obj.getEXME_Tratamiento_ID();
				if(tratID > 0){
					obj.setTrat(new MEXMETratamientos(ctx, tratID, trxName));
					// paquetes
					int paqID = obj.getEXME_PaqBase_Version_ID();
					if(paqID > 0)
						obj.setPaq(new MPaqBase_Version(ctx, paqID, trxName));
				}				
				//producto
				obj.setProd(new MProduct(ctx, obj.getM_Product_ID(), trxName));
				obj.setM_uom(new MUOM(ctx, obj.getC_UOM_ID(), trxName));
				obj.setLote(obj.getProd().isLot());
				LabelValueBean btpProd = new LabelValueBean(obj.getProd().getName(),obj.getProd().getValue());
				LabelValueBean uom = new LabelValueBean(obj.getM_uom().getName(),String.valueOf(obj.getM_uom().getC_UOM_ID()));				
				obj.setBtpProducto(btpProd);				
				obj.setLvbUOM(uom);
				obj.setLine(i*10);
				i++;
				
				retValue.add(obj);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "-- getFromCita: ", e);
		} finally {
			DB.close(rs, psmt);
			rs = null;
			psmt = null;
		}
		return retValue;
	}
	
	/**
	 * Obtiene los productos de una version de paquetes
	 * @param ctx
	 * @param EXME_PaqueteVersion_ID
	 * @param trxName
	 * @return List<MEXMETActPacienteInd> con las lineas de aplicacion de tratamientos
	 *
	public static List<MEXMETActPacienteInd> getPaqProducts(Properties ctx, int EXME_PaqueteVersion_ID, String trxName){
		List<MEXMETActPacienteInd> retValue = new ArrayList<MEXMETActPacienteInd>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			sql.append("SELECT prod.m_product_id as prodID, prod.value as prodVal, prod.name as prodName,  nvl(prodOrg.ISLOT,'N') as lote,")
				.append(" pbd.C_UOM_ID as uomID, uom.name as uomName, pbd.cantidad as cantSol, EXME_PaqBase_Version.name as paqName ")
				.append("FROM  EXME_PaqBase_Version  ")
				.append("INNER JOIN EXME_PaqBaseDet pbd ON (pbd.EXME_PaqBase_Version_ID = EXME_PaqBase_Version.EXME_PaqBase_Version_ID) ")
				.append("INNER JOIN M_Product prod ON (prod.M_Product_ID = pbd.M_Product_ID) ")
				.append("INNER JOIN C_UOM uom ON (pbd.C_UOM_ID = uom.C_UOM_ID) ")
				.append(" INNER JOIN exme_productoorg prodOrg on (prod.m_product_id = prodorg.m_product_id AND prodorg.ad_org_id  = "+ Env.getAD_Org_ID(ctx)+" )  ")
				
				.append("WHERE EXME_PaqBase_Version.isActive = 'Y' ")
				.append("AND EXME_PaqBase_Version.EXME_PaqBase_Version_ID = ? ")				
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MPaqBase_Version.Table_Name));
			
			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setInt(1,EXME_PaqueteVersion_ID);
			rs = psmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				
				MEXMETActPacienteInd actPac = new MEXMETActPacienteInd(ctx,0,trxName);
				//producto
				LabelValueBean btpProd = new LabelValueBean(rs.getString("prodName"),rs.getString("prodVal"));
				LabelValueBean uom = new LabelValueBean(rs.getString("uomName"),rs.getString("uomID"));
				actPac.setBtpProducto(btpProd);				
				actPac.setLvbUOM(uom);
				actPac.setC_UOM_ID(rs.getInt("uomID"));
				actPac.setEstatus(ESTATUS_ScheduleService);
				actPac.setEXME_PaqBase_Version_ID(EXME_PaqueteVersion_ID);
				actPac.setQtyOrdered(rs.getBigDecimal("cantSol"));
				actPac.setLine(i*10);
				actPac.setM_Product_ID(rs.getInt("prodID"));
				//actPac.setM_Warehouse_ID(Env.getContextAsInt(ctx,"#M_Warehouse_ID"));
				actPac.setEditable(false);
				actPac.setPaqueteName(rs.getString("paqName"));
				actPac.setLote(rs.getString("lote").equals("Y")?true:false);
				i++;
				retValue.add(actPac);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "-- getPaqProducts: ", e);
		} finally {
			DB.close(rs, psmt);
			rs = null;
			psmt = null;
		}
		
		return retValue;
		
	}
	
	/**
	 * Revisa los productos de las l�neas y compara contra la lista de lotes seleccionados
	 * divide una linea si sera surtida por mas de un lote
	 * @author Omar de la Rosa (modifiedBy @author Lorena Lama) 
	 * @param ctx
	 * @param original
	 * @param lotes
	 * @param trxName
	 * @return
	 * @throws Exception
	 *
	public static List<MEXMETActPacienteInd> divideByLot(Properties ctx, List<MEXMETActPacienteInd> original,
			StorageVOList lotes, String trxName) throws Exception {
		List<MEXMETActPacienteInd> retValue = new ArrayList<MEXMETActPacienteInd>();
		int linea = 10;
		for (int i = 0; i < original.size(); i++) {
			MEXMETActPacienteInd actPacTemp = original.get(i);
			//si no ha sido cancelado
			if (!actPacTemp.isDisable()) {
				
				MProduct p = new MProduct(ctx, actPacTemp.getM_Product_ID(), null);
				if (p.isLot()) {
					List<StorageVO> lstLotesTemp = lotes.getByProductID(actPacTemp.getM_Product_ID());
					if (lstLotesTemp == null) {
						throw new Exception("msj.selccionarLotes");
					} else {
						if (lstLotesTemp.size() == 0) {
							throw new Exception("msj.selccionarLotes");
						}
						for (int j = 0; j < lstLotesTemp.size(); j++) {
							StorageVO lote = lstLotesTemp.get(j);
							MEXMETActPacienteInd toAdd = new MEXMETActPacienteInd(ctx, 0, trxName);
							PO.copyValues(actPacTemp, toAdd);
							toAdd.setC_UOM_ID((new MProduct(ctx, lote.getM_Product_ID(), null)).getC_UOM_ID());
							toAdd.setM_AttributeSetInstance_ID(lote.getM_AttributeSetInstance_ID());
							toAdd.setLot(lote.getLot());
							toAdd.setGuaranteeDate(lote.getGuaranteeDate());
							toAdd.setLine(linea);
							toAdd.setQtyReserved(lote.getQtyRequested());
							retValue.add(toAdd);
							linea = linea + 10;
						}
						actPacTemp.delete(true, trxName);
					}
				} else {
					actPacTemp.setLine(linea);
					retValue.add(actPacTemp);
					linea = linea + 10;
				}
			} else {
				actPacTemp.setLine(linea);
				retValue.add(actPacTemp);
				linea = linea + 10;
			}
		}
		return retValue;
	}
	

	public LabelValueBean getBtpProducto() {
		return btpProducto;
	}

	public void setBtpProducto(LabelValueBean btpProducto) {
		this.btpProducto = btpProducto;
	}

	public LabelValueBean getLvbUOM() {
		return lvbUOM;
	}

	public void setLvbUOM(LabelValueBean lvbUOM) {
		this.lvbUOM = lvbUOM;
	}

	public String getTratamientoName() {
		return tratamientoName;
	}

	public void setTratamientoName(String tratamientoName) {
		this.tratamientoName = tratamientoName;
	}

	public String getPaqueteName() {
		return paqueteName;
	}

	public void setPaqueteName(String paqueteName) {
		this.paqueteName = paqueteName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isLote() {
		return lote;
	}

	public void setLote(boolean lote) {
		this.lote = lote;
	}

	public BigDecimal getDifferenceQty() {
		return differenceQty;
	}

	public void setDifferenceQty(BigDecimal differenceQty) {
		this.differenceQty = differenceQty;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public MProduct getProd() {
		return prod;
	}

	public void setProd(MProduct prod) {
		this.prod = prod;
	}

	public MUOM getM_uom() {
		return m_uom;
	}

	public void setM_uom(MUOM mUom) {
		m_uom = mUom;
	}

	public MPaqBase_Version getPaq() {
		return paq;
	}

	public void setPaq(MPaqBase_Version paq) {
		this.paq = paq;
		this.setPaqueteName(paq.getName());
	}

	public MEXMETratamientos getTrat() {
		return trat;
	}

	public void setTrat(MEXMETratamientos trat) {
		this.trat = trat;
		this.tratamientoName = trat.getName();
	}

	public boolean isDisableA() {
		return disableA;
	}

	public void setDisableA(boolean disableA) {
		this.disableA = disableA;
	}

	public boolean isDisableLine() {
		return disableLine;
	}

	public void setDisableLine(boolean disableLine) {
		this.disableLine = disableLine;
	}
	*/
}
