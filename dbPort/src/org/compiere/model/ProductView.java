/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;

/**
 * Modelo para los mttos de producto
 * 
 * @author Expert
 * 
 */
public class ProductView extends MEXMEProduct implements GridItem {

	/** serialVersionUID */
	private static final long serialVersionUID = -5936600115859241025L;

	/**
	 * Constructor ID
	 * 
	 * @param ctx
	 *            Contexto
	 * @param M_Product_ID
	 *            ID
	 * @param trxName
	 *            Nombre de transaccion
	 */
	public ProductView(Properties ctx, int M_Product_ID, String trxName) {
		super(ctx, M_Product_ID, trxName);
	}

	/**
	 * Constructor Resultset
	 * 
	 * @param ctx
	 *            contexto
	 * @param rs
	 *            resultset
	 * @param trxName
	 *            Nombre de transaccion
	 */
	public ProductView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/** ID columna */
	private IDColumn idColumn;

	/**
	 * Identificador del renglon
	 */
	@Override
	public IDColumn getIdColumn() {
		if (idColumn == null) {
			idColumn = new IDColumn(getM_Product_ID());
		}
		return idColumn;
	}

	/**
	 * Columnas a mostrar
	 */
	@Override
	public String[] getColumns() {
		return new String[] { "idColumn", "value", "name", "description" };
	}

	/** Precios de lista */
	private BigDecimal priceList = Env.ZERO;
	/** Precios limite mayor */
	private BigDecimal priceLimit = Env.ZERO;
	/** Precios estandar ya no visible */
	private BigDecimal priceStd = Env.ZERO;
	/** Costo */
	private BigDecimal currentCostPrice = Env.ZERO;
	/** Version de lista de precios */
	private int M_PriceList_Version_ID = 0;
	/** almacen */
	private int M_Warehouse_ID = 0;
	/** esquema contable (para el costo) */
	private int C_AcctSchema_ID = 0;
	/** Nombre de version y nombre del almacen */
	private String version = null;
	/** Nombre del almacen */
	private String alm = null;
	/** Fecha de vigencia */
	private Timestamp validFrom;
	/** ID's del rv. code de replenish */
	private int rcRID = 0;
	/** ID's del rv. code del almacen */
	private int EXME_PRODUCTOORG_ID = 0;
	/** Nombres del rv. code de replenish */
	private String rcrName = null;
	/** Nombres del rv. code del almacen */
	private String rcwName = null;
	/** intervencion */
	private String inter = null;
	/** HCPCS */
	private String hcpcs = null;
	/** PMID */
	private String pmid = null;
	/** ID organizacion */
	private int org_ID = 0;
	/** ID cliente */
	private int client_ID = 0;
	/** Nombres categoria de producto */
	private String product_Category_Name = null;
	/** Nombres familia de producto */
	private String product_Family_Name = null;
	/** Nombres categoria de impuesto */
	private String taxCategory_Name = null;
	/** Nombres unidad de medida */
	private String uOM_Name = null;
	/** Nombres FG */
	private String mFGName = null;
	/** Nombres generico */
	private String generic_Product_Name = null;
	/** Nombres tipo de producto */
	private String tipoProd_Name = null;
	/** Nombres categoria (servicios) */
	private String freightCategory_Name = null;
	/** Codigo de CPT */
	private String interValue = null;
	/** Nivel de HCPCS 1 � 2 */
	private String hcpcs_Level = null;
	/** Codigo del rev. code de replenish */
	//private String rcrValue = null;
	/** Codigo del rev. code del almacen */
	//private String rcpValue = null;

	/**
	 * Datos de la vista no propios del objeto de producto
	 * 
	 * @param rs
	 *
	public void set(ResultSet rs) {
		try {
			priceList = rs.getBigDecimal("priceList");
			priceLimit = rs.getBigDecimal("priceLimit");
			priceStd = rs.getBigDecimal("priceStd");
			currentCostPrice = rs.getBigDecimal("currentCostPrice");
			M_PriceList_Version_ID = rs.getInt("M_PriceList_Version_ID");
			C_AcctSchema_ID = rs.getInt("C_AcctSchema_ID");
			version = rs.getString("version");
			validFrom = rs.getTimestamp("validFrom");
			rcRID = rs.getInt("rcID");
			rcrName = rs.getString("rcName");
			inter = rs.getString("inter");
			pmid = rs.getString("pmid");
			org_ID = rs.getInt("AD_Client_ID");
			client_ID = rs.getInt("AD_Org_ID");
			// alm = rs.getString("alm");
			// M_Warehouse_ID = rs.getInt("M_Warehouse_ID");
			// rcRID = rs.getInt("rcRID");
			// rcWID = rs.getInt("rcWID");
			// rcrName = rs.getString("rcrName");
			// rcwName = rs.getString("rcwName");
			// hcpcs = rs.getString("hcpcs");
			// i.Name as inter ,
			// p.pmid,
			// rc.EXME_revenuecode_id as rcid,
			// rc.Name as rcname,
			// pp.PriceList ,
			// pp.PriceLimit,
			// pp.PriceStd,
			// pp.M_PriceList_Version_ID,
			// plv.Name as version,
			// plv.validfrom,
			// c.C_ACCTSCHEMA_ID,
			// c.currentcostprice

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Nombre de la clase de producto
	 */
	private String productClassName = null;

	/**
	 * Obtiene el nombre de la clase de producto
	 * 
	 * @return
	 */
	public String getProductClassName() {
		productClassName = MRefList.getListName(Env.getCtx(), X_M_Product.PRODUCTCLASS_AD_Reference_ID, getProductClass());
//		if (ref != null)
//			productClassName = ref.getNameStr();

		return this.productClassName;
	}

	public void setProductClassName(String productClassName) {
		this.productClassName = productClassName;
	}

	/**
	 * Nombre del tipo de producto
	 */
	private String productTypeName = null;

	/**
	 * Obtiene el nombre del tipo de producto
	 * 
	 * @return
	 */
	public String getProductTypeName() {
		productTypeName = MRefList.getListName(Env.getCtx(), X_M_Product.PRODUCTTYPE_AD_Reference_ID, getProductType());
//		if (ref != null)
//			productTypeName = ref.getNameStr();

		return this.productTypeName;
	}

	public void setProductTypeName(String pproductTypeName) {
		this.productTypeName = pproductTypeName;
	}

	/**
	 * Obtiene los nombres de las tablas relacionadas al producto
	 */
	public void getProductNames() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT pc.Name AS Product_Category_Name, ");
		sql.append(" tc.Name AS TaxCategory_Name, ");
		sql.append(" uo.Name AS UOM_Name, ");
		sql.append(" it.Name AS Intervencion_Name, ");
		sql.append(" rc.Name AS RevenueCode_Name, ");
		sql.append(" lb.MFGName, ");
		sql.append(" gp.Generic_Product_Name, ");
		sql.append(" tp.Name AS TipoProd_Name, ");
		sql.append(" fc.Name AS FreightCategory_Name, ");
		sql.append(" pf.Name AS Product_Family_Name ");
		sql.append(" FROM M_Product p ");
		sql
				.append(" INNER JOIN M_Product_Category  pc ON p.M_Product_Category_ID = pc.M_Product_Category_ID ");
		sql
				.append(" INNER JOIN C_TaxCategory       tc ON p.C_TaxCategory_ID      = tc.C_TaxCategory_ID ");
		sql
				.append(" INNER JOIN C_UOM               uo ON p.C_UOM_ID              = uo.C_UOM_ID ");
		sql
				.append(" LEFT  JOIN EXME_Intervencion   it ON p.EXME_Intervencion_ID  = it.EXME_Intervencion_ID ");
		sql
				.append(" LEFT  JOIN EXME_RevenueCode    rc ON p.EXME_RevenueCode_ID   = rc.EXME_RevenueCode_ID ");
		sql
				.append(" LEFT  JOIN EXME_Labeler        lb ON p.EXME_Labeler_ID       = lb.EXME_Labeler_ID ");
		sql
				.append(" LEFT  JOIN EXME_GenProduct     gp ON p.EXME_GenProduct_ID    = gp.EXME_GenProduct_ID ");
		sql
				.append(" LEFT  JOIN EXME_TipoProd       tp ON p.EXME_TipoProd_ID      = tp.EXME_TipoProd_ID ");
		sql
				.append(" LEFT  JOIN M_FreightCategory   fc ON p.M_FreightCategory_ID  = fc.M_FreightCategory_ID ");
		sql
				.append(" LEFT  JOIN EXME_ProductFam     pf ON p.EXME_ProductFam_ID    = pf.EXME_ProductFam_ID ");
		sql.append(" WHERE p.IsActive = 'Y' ");
		sql.append(" AND p.M_Product_ID = ? AND p.AD_Client_ID =? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql
				.toString(), "M_Product", "p"));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getM_Product_ID());
			pstmt.setInt(2, Env.getAD_Client_ID(getCtx()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product_Category_Name = rs.getString("Product_Category_Name");
				product_Family_Name = rs.getString("Product_Family_Name");
				taxCategory_Name = rs.getString("TaxCategory_Name");
				uOM_Name = rs.getString("UOM_Name");
				inter = rs.getString("Intervencion_Name");
				rcwName = rs.getString("RevenueCode_Name");
				mFGName = rs.getString("MFGName");
				generic_Product_Name = rs.getString("Generic_Product_Name");
				tipoProd_Name = rs.getString("TipoProd_Name");
				freightCategory_Name = rs.getString("FreightCategory_Name");
			}
		} catch (Exception e) {
			// s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/*******************************/
	public int getOrg_ID() {
		return this.org_ID;
	}

	public void setOrg_ID(int orgID) {
		this.org_ID = orgID;
	}

	public int getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(int clientID) {
		this.client_ID = clientID;
	}

	public BigDecimal getPriceList() {
		return this.priceList;
	}

	public void setPriceList(BigDecimal priceList) {
		this.priceList = priceList;
	}

	public BigDecimal getPriceLimit() {
		return this.priceLimit;
	}

	public void setPriceLimit(BigDecimal priceLimit) {
		this.priceLimit = priceLimit;
	}

	public BigDecimal getPriceStd() {
		return this.priceStd;
	}

	public void setPriceStd(BigDecimal priceStd) {
		this.priceStd = priceStd;
	}

	public BigDecimal getCurrentCostPrice() {
		return this.currentCostPrice;
	}

	public void setCurrentCostPrice(BigDecimal currentCostPrice) {
		this.currentCostPrice = currentCostPrice;
	}

	public int getM_PriceList_Version_ID() {
		return this.M_PriceList_Version_ID;
	}

	public void setM_PriceList_Version_ID(int mPriceListVersionID) {
		this.M_PriceList_Version_ID = mPriceListVersionID;
	}

	public int getM_Warehouse_ID() {
		return this.M_Warehouse_ID;
	}

	public void setM_Warehouse_ID(int mWarehouseID) {
		this.M_Warehouse_ID = mWarehouseID;
	}

	public int getC_AcctSchema_ID() {
		return this.C_AcctSchema_ID;
	}

	public void setC_AcctSchema_ID(int cAcctSchemaID) {
		this.C_AcctSchema_ID = cAcctSchemaID;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAlm() {
		return this.alm;
	}

	public void setAlm(String alm) {
		this.alm = alm;
	}

	public Timestamp getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public int getRcRID() {
		return this.rcRID;
	}

	public void setRcRID(int rcRID) {
		this.rcRID = rcRID;
	}

	public int getEXME_PRODUCTOORG_ID() {
		return this.EXME_PRODUCTOORG_ID;
	}

	public void setEXME_PRODUCTOORG_ID(int rcWID) {
		this.EXME_PRODUCTOORG_ID = rcWID;
	}
/*
	public String getRcrName() {
		return this.rcrName;
	}

	public void setRcrName(String rcrName) {
		this.rcrName = rcrName;
	}

	public String getRcwName() {
		return this.rcwName;
	}

	public void setRcwName(String rcwName) {
		this.rcwName = rcwName;
	}
*/
	public String getInter() {
		return this.inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	public String getHcpcs() {
		return this.hcpcs;
	}

	public void setHcpcs(String hcpcs) {
		this.hcpcs = hcpcs;
	}

	public String getPmid() {
		return this.pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getProduct_Category_Name() {
		return this.product_Category_Name;
	}

	public void setProduct_Category_Name(String productCategoryName) {
		this.product_Category_Name = productCategoryName;
	}

	public String getTaxCategory_Name() {
		return this.taxCategory_Name;
	}

	public void setTaxCategory_Name(String taxCategoryName) {
		this.taxCategory_Name = taxCategoryName;
	}

	public String getuOM_Name() {
		return this.uOM_Name;
	}

	public void setuOM_Name(String uOMName) {
		this.uOM_Name = uOMName;
	}
	public String getProduct_Family_Name() {
		return product_Family_Name;
	}

	public void setProduct_Family_Name(String product_Family_Name) {
		this.product_Family_Name = product_Family_Name;
	}
	/*
	 * public String getIntervencion_Name() { return this.intervencion_Name; }
	 * 
	 * public void setIntervencion_Name(String intervencionName) {
	 * this.intervencion_Name = intervencionName; }
	 * 
	 * public String getRevenueCode_Name() { return this.revenueCode_Name; }
	 * 
	 * public void setRevenueCode_Name(String revenueCodeName) {
	 * this.revenueCode_Name = revenueCodeName; }
	 */
	public String getmFGName() {
		return this.mFGName;
	}

	public void setmFGName(String mFGName) {
		this.mFGName = mFGName;
	}

	public String getGeneric_Product_Name() {
		return this.generic_Product_Name;
	}

	public void setGeneric_Product_Name(String genericProductName) {
		this.generic_Product_Name = genericProductName;
	}

	public String getTipoProd_Name() {
		return this.tipoProd_Name;
	}

	public void setTipoProd_Name(String tipoProdName) {
		this.tipoProd_Name = tipoProdName;
	}

	public String getFreightCategory_Name() {
		return this.freightCategory_Name;
	}

	public void setFreightCategory_Name(String freightCategoryName) {
		this.freightCategory_Name = freightCategoryName;
	}

	public String getInterValue() {
		return this.interValue;
	}

	public void setInterValue(String interValue) {
		this.interValue = interValue;
	}

	public String getHcpcs_Level() {
		return this.hcpcs_Level;
	}

	public void setHcpcs_Level(String hcpcsLevel) {
		this.hcpcs_Level = hcpcsLevel;
	}
/*
	public String getRcrValue() {
		return this.rcrValue;
	}

	public void setRcrValue(String rcrValue) {
		this.rcrValue = rcrValue;
	}
*/
	public void setIdColumn(IDColumn idColumn) {
		this.idColumn = idColumn;
	}
/*
	public String getRcpValue() {
		return this.rcpValue;
	}

	public void setRcpValue(String rcpvalue) {
		this.rcpValue = rcpvalue;
	}
	*/
}