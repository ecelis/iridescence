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
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.StorageVO;
import org.compiere.util.StorageVOList;

public class MEXMEStorage extends MStorage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Log								*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEStorage.class);

	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MEXMEStorage(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} //	MStorage

	public static List<StorageVO> divideByLot(Properties ctx, 
			StorageVOList st, 
			int M_Product_ID, 
			int M_Locator_ID,
			BigDecimal pedido, 
			boolean isDevol, 
			boolean isConsulta, 
			String trxName) {
		List<StorageVO> retValue = new ArrayList<StorageVO>();

		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal demanda = pedido;
		boolean seleccionPrevia = false;

		try {
			sql.append("SELECT P.M_Product_ID, ST.M_Locator_ID, P.Value, P.Name, P.Description, U.Name as unidadMedida, ")
				.append(" U.C_Uom_ID, SI.M_AttributeSetInstance_ID, SI.Lot, to_char(SI.GuaranteeDate,'dd/MM/yyyy') as GuaranteeDate, ST.QtyOnHand, ST.QtyReserved ")
				.append(", WH.NAME || ' - ' || LOC.VALUE as almacen, WH.M_Warehouse_ID ")
				.append("FROM M_Storage ST ")
				.append("INNER JOIN M_AttributeSetInstance SI ON SI.M_AttributeSetInstance_ID = ST.M_AttributeSetInstance_ID ")
				.append("INNER JOIN M_Product P on P.M_Product_ID = ST.M_Product_ID ")
				.append("INNER JOIN C_Uom U ON P.C_Uom_ID = U.C_Uom_ID ")
				.append("INNER JOIN M_Locator LOC on LOC.M_Locator_ID = ST.M_Locator_ID ")
				.append("INNER JOIN M_Warehouse WH on WH.M_Warehouse_ID = LOC.M_Warehouse_ID ");
			sql.append("WHERE SI.Lot IS NOT NULL ");
			if (isDevol) {
				sql.append("AND SI.GuaranteeDate >= to_date('")
						.append(Constantes.getSdfFecha().format(new java.util.Date())).append("','dd/MM/yyyy') ");
			}
			sql.append("AND ST.QtyOnHand > 0 ").append("AND ST.M_Product_ID = ? ");
			if (M_Locator_ID != -1) {
				sql.append("AND ST.M_Locator_ID = ? ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Storage", "SI")).append(" ORDER BY SI.GuaranteeDate asc");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			if (M_Locator_ID != -1) {
				pstmt.setInt(2, M_Locator_ID);
			}
			rs = pstmt.executeQuery();

			if (st != null && st.size() > 0) {
				List<StorageVO> vo = st.getByProductID(M_Product_ID);
				if (vo != null) {
					if (vo.size() > 0) {
						seleccionPrevia = true;
					}
				}
			}

			while (rs.next()) {
				StorageVO tmp = new StorageVO();
				tmp.setM_Product_ID(rs.getInt("M_Product_ID"));
				tmp.setValue(rs.getString("Value"));
				tmp.setName(rs.getString("Name"));
				tmp.setDescripcion(rs.getString("Description"));
				tmp.setUnidadMedida(rs.getString("unidadMedida"));
				tmp.setC_Uom_ID(rs.getInt("C_Uom_ID"));
				tmp.setM_AttributeSetInstance_ID(rs.getInt("M_AttributeSetInstance_ID"));
				tmp.setLot(rs.getString("lot"));
				tmp.setGuaranteeDate(rs.getString("GuaranteeDate"));
				tmp.setQtyOnHand(rs.getBigDecimal("QtyOnHand"));
				tmp.setQtyReserved((rs.getBigDecimal("QtyReserved")));
				tmp.setM_Locator_ID(rs.getInt("M_Locator_ID"));
				tmp.setAlmacenInfo(rs.getString("almacen"));
				tmp.setM_Warehouse_ID(rs.getInt("M_Warehouse_ID"));
				tmp.setQtyOriginal(pedido);
				if (!seleccionPrevia) {

					if (demanda.equals(BigDecimal.ZERO)) {
						tmp.setQtyRequested(BigDecimal.ZERO);
						tmp.setSeleccionado(false);
					} else {
						if (tmp.getQtyOnHand().compareTo(demanda) > 0) {
							tmp.setQtyRequested(demanda);
							tmp.setSeleccionado(true);
							demanda = BigDecimal.ZERO;
						} else {
							tmp.setQtyRequested(tmp.getQtyOnHand());
							tmp.setSeleccionado(true);
							demanda = demanda.subtract(tmp.getQtyOnHand());
						}
					}
				} else {
					tmp.setQtyRequested(BigDecimal.ZERO);
					tmp.setSeleccionado(false);
				}

				if (isConsulta) {
					tmp.setQtyRequested(pedido);
				}

				retValue.add(tmp);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		if (seleccionPrevia) {
			List<StorageVO> vo = st.getByProductID(M_Product_ID);
			if(vo != null)//Mantis #0003691 .- Lama
			for (int i = 0; i < vo.size(); i++) {
				StorageVO anterior = vo.get(i);
				for (int j = 0; j < retValue.size(); j++) {
					StorageVO actual = retValue.get(j);
					if (anterior.getM_AttributeSetInstance_ID() == actual.getM_AttributeSetInstance_ID()) {
						retValue.get(j).setQtyRequested(anterior.getQtyRequested());
						retValue.get(j).setSeleccionado(true);
						break;
					}
				}
			}
		}

		return retValue;
	}
	
	public static MEXMEStorage getFromAttribute(Properties ctx, int M_AttributeSetInstance, String trxName){
		MEXMEStorage lote = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append(" SELECT * FROM M_Storage where M_AttributeSetInstance_ID = ? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Storage"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,M_AttributeSetInstance);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    lote = new MEXMEStorage(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFromName", e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lote;
	}
	
	/**
	 * Lista con lotes y M_AttributeSetInstance_ID para la aplicacion de vacunas. 
	 * @param ctx
	 * @param M_Product_ID
	 * @param M_Locator_ID
	 * @return LabelValueBean
	 * lhernandez
	 */
	public static List<LabelValueBean> getLotVaccine(Properties ctx, int M_Product_ID, int M_Locator_ID){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT si.lot, si.m_attributesetinstance_id FROM M_STORAGE ST ")
				.append("INNER JOIN M_AttributeSetInstance SI ON SI.M_AttributeSetInstance_ID = ST.M_AttributeSetInstance_ID ")
				.append("INNER JOIN M_Product p ON (ST.m_product_id = P.m_product_id AND P.isActive = 'Y')")
				.append("WHERE ST.m_product_id=? ")
				//.append("AND ST.m_locator_id=? ") no deberia estar considerando el almacen --GC
				.append("AND SI.Lot IS NOT NULL ")
				.append("AND ST.QtyOnHand > ( CASE WHEN p.IsStocked='Y' THEN 0 ELSE -99 END ) ")
				.append("AND SI.GuaranteeDate >= "+ DB.TO_DATE(DB.getTimestampForOrg(Env.getCtx())) + " ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Storage", "ST"))
				.append(" ORDER BY SI.GuaranteeDate asc");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, M_Product_ID);
//			pstmt.setInt(2, M_Locator_ID);
			rs = pstmt.executeQuery();
			 while(rs.next()){
				 LabelValueBean lv = new LabelValueBean(rs.getString(1) ,String.valueOf(rs.getInt(2)));
				 lista.add(lv);
			 }
			
		} catch(Exception ex){
			s_log.log(Level.SEVERE, "getLotVaccine", ex);
		}finally{
			DB.close(rs,pstmt);
		}
		return lista;
	}
	
	/**
	 * Get Storage Info
	 * 
	 * @param ctx
	 *            context
	 * @param M_Locator_ID
	 *            locator
	 * @param M_Product_ID
	 *            product
	 * @param trxName
	 *            transaction
	 * @return existing or null
	 */
	public static MEXMEStorage getStorageInfo(Properties ctx, int M_Warehouse_ID, int M_Product_ID, String trxName) {
		MEXMEStorage retValue = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT stor.* FROM M_Storage stor ")
		.append("INNER JOIN M_Locator loc ON loc.M_Locator_ID = stor.M_Locator_ID ")
		.append("INNER JOIN M_Warehouse ware ON ware.M_Warehouse_ID = loc.M_Warehouse_ID ")
		.append("WHERE ware.M_Warehouse_ID = ? AND stor.M_Product_ID = ? ");
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEStorage(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getStorageInfo", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}

}
