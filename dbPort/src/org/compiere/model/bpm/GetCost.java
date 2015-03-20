package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MClientInfo;
import org.compiere.model.MProduct;
import org.compiere.model.ModelError;
import org.compiere.model.ProductCost;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Busca el costo del producto
 * 
 * @author Expert
 * 
 */
public class GetCost {

	/** Static Log */
	protected CLogger log = CLogger.getCLogger(getClass());
	/** Producto */
	private int productID;
	/** Listado de errros */
	private List<ModelError> errors;
	/** Contexto */
	private Properties ctx;
	/** Costo Promedio */
	private BigDecimal	costAverage;
	/** Costo estandar */
	private BigDecimal	costStandard;
	/** Precio de ultima compra */
	private BigDecimal	priceLastPO;

	/**
	 * Constructor Obligatorio los parametros
	 * 
	 * @param ctx
	 * @param pProductID
	 */
	public GetCost(Properties ctx, int pProductID) {
		this.productID = pProductID;
		this.errors = new ArrayList<ModelError>();
		this.ctx = ctx;
	}

	/**
	 * Obtenemos el costo
	 * 
	 * @param zeroCostsOKs
	 *            zero/non-zero costs
	 * @return Costo
	 */
	public BigDecimal costo(boolean zeroCostsOKs) {

		// Class AcctProcessor
		// Metodo doWork ()
		// Get Schemata
		/*
		 * if (m_model.getC_AcctSchema_ID() == 0) m_ass =
		 * MAcctSchema.getClientAcctSchema(ctx, m_model.getAD_Client_ID()); else
		 * // only specific accounting schema m_ass = new MAcctSchema[] {new
		 * MAcctSchema (ctx, m_model.getC_AcctSchema_ID(), null)};
		 */
		// Class MAcctShema
		// Metodo public static MAcctSchema[] getClientAcctSchema (Properties
		// ctx, int AD_Client_ID, String trxName)
		MClientInfo info = MClientInfo.get(ctx, Env.getAD_Client_ID(ctx), null);
		MAcctSchema as = MAcctSchema.get(ctx, info.getC_AcctSchema1_ID(), null);

		BigDecimal costo = Env.ZERO;

		if (as == null) {
			return null;
		} else {
			costo = costo(as, zeroCostsOKs);
		}

		return costo;
	}

	/**
	 * Clase class Doc_MatchPO Tomado de public ArrayList<Fact> createFacts
	 * (MAcctSchema as)
	 * 
	 * @param as
	 * @return
	 */
	public BigDecimal costo(MAcctSchema as, boolean zeroCostsOKs) {
		// Validamos siempre tener el producto y el contesto
		if (productID == 0 || ctx == null) {
			log.fine("No Product/Qty - M_Product_ID=" + productID);
			errors.add(new ModelError(ModelError.TIPOERROR_Error,
					"No Product/Qty - M_Product_ID=" + productID));
			return Env.ZERO;
		}

		// Costo/producto
		ProductCost m_pc = new ProductCost(ctx, productID, 0, null);
		m_pc.setQty(Env.ONE);

		// Current Costs
		final MProduct product = MProduct.get(ctx, productID);
//		String costingMethod = as.getCostingMethod();
//		
//
//		// Categoria de producto
//		// Expert: #Post,Cost And Price
//		MProductCategoryAcct pca = MProductCategoryAcct.getAcct( 
//				 product, as);
//
//		// Si no se existe el metodo de costeo se busca mas adelante
//		if (pca.getCostingMethodDefault() != null)
//			costingMethod = pca.getCostingMethodDefault();

		// Obtiene el costo
		BigDecimal costs = m_pc.getProductCosts(as, Env.getAD_Org_ID(ctx),
				product.getCostingMethod(as), 0, zeroCostsOKs, false); // non-zero costs

		// Depende del parametro si se quiere cero el costo o no
		if (zeroCostsOKs) {
			// Obtenido de la clase DOC_InOut
			// metodo public ArrayList<Fact> createFacts (MAcctSchema as)
			if (costs == null || costs.signum() == 0) // zero costs OK
			{
				if (product.isStocked()) {
					errors.add(new ModelError(ModelError.TIPOERROR_Error,
							"error.costoProd.noExiste"));
					log.log(Level.WARNING, "No Costs for " + product.getName());
					return null;
				}
				// else // ignore service
				// continue;
			}
		} else {
			// No Costs yet - no PPV
			if (costs == null || costs.signum() == 0) {
				errors.add(new ModelError(ModelError.TIPOERROR_Error,
						"error.costoProd.noExiste"));
				log.log(Level.SEVERE, "Resubmit - No Costs for "
						+ product.getName());
				return null;
			}
		}
		return costs;
	}
	
	/**
	 * Obtiene los costos estándar, promedio y precio de última compra de un producto
	 * Card #1374
	 */
	public GetCost setCosts() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		final String sql = "SELECT * FROM get_cost(?,?) ";// Client_ID, Product_ID

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, productID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				setCostStandard(rs.getBigDecimal("costo_estandar"));
				setCostAverage(rs.getBigDecimal("costo_promedio"));
				setPriceLastPO(rs.getBigDecimal("price_lastPO"));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return this;
	}
	
	public BigDecimal getCostAverage() {
		return costAverage;
	}

	public void setCostAverage(BigDecimal costAverage) {
		this.costAverage = costAverage;
	}

	public BigDecimal getCostStandard() {
		return costStandard;
	}

	public void setCostStandard(BigDecimal costStandard) {
		this.costStandard = costStandard;
	}

	public BigDecimal getPriceLastPO() {
		return priceLastPO;
	}

	public void setPriceLastPO(BigDecimal priceLastPO) {
		this.priceLastPO = priceLastPO;
	}
	
	public BigDecimal getCosto() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal cost = BigDecimal.ZERO;
		final String sql = "SELECT * FROM get_costo_kardex(?, ?) ";// Client_ID, Product_ID

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, productID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cost = rs.getBigDecimal(1);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return cost;
	}
}