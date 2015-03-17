package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Versiones de las listas de precios
 * @author Expert
 *
 */
public class MEXMEPriceListVersion extends MPriceListVersion {

	/** serialVersionUID. */
	private static final long serialVersionUID = 4514442616234062390L;
	/** Static Logger */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMEPriceListVersion.class);

	/**
	 * Standard Cinstructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_Version_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MEXMEPriceListVersion(Properties ctx, int M_PriceList_Version_ID,
			String trxName) {
		super(ctx, M_PriceList_Version_ID, trxName);
	} // MPriceListVersion

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MEXMEPriceListVersion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MPriceListVersion

	public MDiscountSchema getEsquema() {
		return new MDiscountSchema(getCtx(), getM_DiscountSchema_ID(),
				get_TrxName());
	}

	public int getPriceListVerId() {
		return getM_PriceList_Version_ID();
	}

	public MPriceList getListaPrecios() {
		return new MPriceList(getCtx(), getM_PriceList_ID(), get_TrxName());
	}

	public MPriceList getListaPreciosBase() {
		return new MPriceList(getCtx(), getM_Pricelist_Version_Base_ID(),
				get_TrxName());
	}

	/**
	 * Obtenemos la informacion del Corte de Caja
	 * 
	 * @param ctx
	 * @param id
	 *            cash
	 * @param trxName
	 * @return
	 * @throws Exception
	 *

	public static List<MEXMEPriceListVersion> get(Properties ctx,
			int M_PriceList_ID, String trxName) throws Exception {

		List<MEXMEPriceListVersion> lista = new ArrayList<MEXMEPriceListVersion>();
		if (ctx == null) {
			return null;
		}

		MEXMEPriceListVersion retValue = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM M_PriceList_Version ").append(
				" WHERE IsActive = 'Y' AND M_PriceList_ID = ? ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						"M_PriceList_Version"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_PriceList_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue = new MEXMEPriceListVersion(ctx, rs, trxName);
				lista.add(retValue);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtenemos la informacion de las listas de precios
	 * 
	 * @param ctx
	 * @param id
	 *            cash
	 * @param trxName
	 * @return
	 * @throws Exception
	 * @deprecated use {@link #getLstVersion(Properties, int, String)} ya no se deben utilizar los LabelValueBean
	 */
	public static List<LabelValueBean> getLabelValue(Properties ctx,
			int M_PriceList_ID, String trxName) throws Exception {

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT M_PriceList_Version_ID, Name ")
		.append(" FROM M_PriceList_Version WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo
				.addAccessLevelSQL(ctx, " ", "M_PriceList_Version"))
		.append(" AND M_PriceList_ID = ? ORDER BY Created DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_PriceList_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new LabelValueBean(rs
						.getString("Name"), String.valueOf(rs
						.getInt("M_PriceList_Version_ID"))));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}
	
	
	/**
	 * Regresa las versiones activas de una lista de precios.
	 * @param ctx
	 * @param M_PriceList_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPriceListVersion> getLstVersion(Properties ctx, int M_PriceList_ID, String trxName) {

		List<MEXMEPriceListVersion> lista = new ArrayList<MEXMEPriceListVersion>();
		
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT * FROM M_PriceList_Version WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" AND M_PriceList_ID = ? ORDER BY Created DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_PriceList_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMEPriceListVersion(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause
	 * @return
	 * @throws Exception
	 *
	public static MEXMEPriceListVersion getPriceListVersion(Properties ctx,
			String trxName, String whereClause) throws Exception {

		if (ctx == null) {
			return null;
		}
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM M_PriceList_Version WHERE isActive = 'Y' ")
				.append(whereClause != null ? whereClause : "").append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								"M_PriceList_Version")).append(
						" ORDER BY Created DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MEXMEPriceListVersion(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}

		return null;

	}

	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @param mInouID
	 * @param mPriceListID
	 * @return
	 * @deprecated
	 */
	public static int getProductPriceMissing(Properties ctx, String trxName,
			int mInouID, int mPriceListID) {

		int result = 0;

		if (ctx == null) {
			return 0;
		}
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql
				.append(
						" SELECT COUNT(MP.M_Product_ID) -  COUNT(PL.M_PriceList_ID) Faltantes ")
				.append(" FROM M_InOut M")
				.append(
						" INNER JOIN M_InOutLine ML ON ML.m_Inout_Id = M.M_InOut_ID")
				.append(
						" INNER JOIN M_Product MP ON MP.M_Product_ID = ML.M_Product_ID")
				.append(
						" left JOIN M_ProductPrice pp ON mp.M_Product_ID = pp.M_Product_ID")
				.append(
						" left JOIN M_PriceList_Version pv ON pp.M_PriceList_Version_ID = pv.M_PriceList_Version_ID")
				.append(
						"                                 AND pv.M_PriceList_ID = ?")
				.append(
						" left JOIN M_Pricelist pl ON (pv.M_PriceList_ID = pl.M_PriceList_ID)")
				.append(" WHERE M.m_Inout_Id= ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mPriceListID);
			pstmt.setInt(2, mInouID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("Faltantes");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}

		return result;

	}

	/**
	 * Obtiene el id de la ultima lista de precios
	 * 
	 * @param ctx
	 * @return id de la ultima lista de precios
	 * @author mvrodriguez
	 */

	public static int getIdPriceListVersionLast(Properties ctx) {
		int result = 0;

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT M_PRICELIST_VERSION.M_PRICELIST_VERSION_ID ")
				.append(" FROM M_PRICELIST_VERSION ").append(
						" WHERE M_PRICELIST_VERSION.ISACTIVE = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
				.toString(), "M_PriceList_Version"));
		// FIXME: No necesariamente la ultima version creada es la ultima, tiene
		// fecha de vigencia
		sql
				.append(" ORDER BY M_PRICELIST_VERSION.M_PRICELIST_VERSION_ID DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getInt("M_PriceList_Version_ID");

			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return result;

	}
	
	public static MEXMEPriceListVersion getPriceVersion(Properties ctx, int priceListID, int productID) {
		MEXMEPriceListVersion plv = null;

		PreparedStatement psmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(" SELECT M_PriceList_Version.* from M_PriceList_Version ")
		.append(" INNER JOIN M_ProductPrice pp on M_PriceList_Version.M_PriceList_Version_id = pp.M_PriceList_Version_id ")
		.append(" WHERE M_PriceList_Version.m_pricelist_version_id = ? and pp.m_product_id = ?");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_PriceList_Version"));

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, priceListID);
			psmt.setInt(2, productID);
			rs = psmt.executeQuery();

			if (rs.next()) {				
				plv = new MEXMEPriceListVersion(ctx, rs, null);				
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return plv;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param productID
	 * @return
	 */
	public static int getLastPriceListVersion(Properties ctx, int productID) {
		int result = 0;

		StringBuilder sql = new StringBuilder();
		sql.append(" select plv.m_pricelist_version_id from M_PRICELIST_VERSION plv ");
		sql.append(" inner join m_productprice mpp on mpp.m_pricelist_version_id = plv.m_pricelist_version_id ");
		sql.append(" where mpp.m_product_id = ? ");
		sql.append(" order by mpp.created desc ");
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, productID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("M_PriceList_Version_ID");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return result;

	}
	
	/**
	 * 
	 * @param ctx
	 * @param productID
	 * @return
	 */
	public static int getLastPricePurchaseListVersion(Properties ctx, int productID) {
		int result = 0;

		StringBuilder sql = new StringBuilder();
		sql.append(" select M_PRICELIST_VERSION.m_pricelist_version_id from M_PRICELIST_VERSION  ");
		sql.append(" inner join m_productprice mpp on mpp.m_pricelist_version_id = M_PriceList_Version.m_pricelist_version_id ");
		sql.append(" left join m_pricelist pl on M_PriceList_Version.m_pricelist_id = pl.m_pricelist_id ");
		sql.append(" where mpp.m_product_id = ?  and pl.issopricelist = 'N'  ");		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "M_PriceList_Version"));
		sql.append(" order by mpp.created desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, productID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("M_PriceList_Version_ID");
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return result;

	}
	
	/**
	 * obtiene la lista ultima version creada para la lista de precios
	 * @param ctx
	 * @param priceListID
	 * @return
	 */
	public static MEXMEPriceListVersion getLastPriceListCreated(Properties ctx, int priceListID, boolean Venta) {

		MEXMEPriceListVersion version = null;
		
		StringBuilder sql = new StringBuilder()
		.append(" SELECT PV.*  ")
		.append(" FROM M_PRICELIST_VERSION PV ")
		.append(" INNER JOIN M_PriceList pl ON pv.M_PriceList_ID = pl.M_PriceList_ID ")
		.append(" WHERE PV.ISACTIVE = 'Y' ")
		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_PriceList_Version", "PV")))
		.append(" AND PV.M_PRICELIST_ID = ? ");
		if(DB.isOracle()){
			sql.append(" AND PV.VALIDFROM <= SYSDATE ");
		}
		if(DB.isPostgreSQL()){
			sql.append(" AND PV.VALIDFROM <= NOW() ");
		}
		sql.append(" AND pl.ISSOPRICELIST = ").append(Venta?DB.TO_STRING("Y"):DB.TO_STRING("N"))
		.append(" ORDER BY PV.CREATED DESC");
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, priceListID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				version = new MEXMEPriceListVersion(Env.getCtx(),rs,null);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return version;

	}
	
	

}
