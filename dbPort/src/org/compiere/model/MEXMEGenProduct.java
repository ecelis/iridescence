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

/**
 * MEXMEGenProduct
 * 
 * @author
 * 
 */
public class MEXMEGenProduct extends X_EXME_GenProduct {

	/** serialVersionUID */
	private static final long serialVersionUID = 806628980784947649L;
	/** log */
	private static CLogger slog = CLogger.getCLogger(MEXMEGenProduct.class);
	/** cantidad (bean) */
	private String qty = null;
	/** dosis (bean) */
	private String dose = null;
	/** via de admin (bean) */
	private MEXMERoute via = null;
	/** NDC relacionado (bean) */
	private String ndc = null;
	/** id del NDC relacionado (bean) */
	private int key = 0;
	/** cantidad (bean) */
	private BigDecimal quantity = Env.ZERO;
	/** frecuencia (bean) */
	private transient int frecuenciaID = 0;
	/** refill (bean) */
	private int refill = 0;
	/** dosis (bean) */
	private MEXMEDoseForm dosis = null;

	/** Standard Constructor */
	public MEXMEGenProduct(final Properties ctx, final int genProductID,
			final String trxName) {
		super(ctx, genProductID, trxName);
	}

	/** Load Constructor */
	public MEXMEGenProduct(final Properties ctx, final ResultSet rSet,
			final String trxName) {
		super(ctx, rSet, trxName);
	}

	@Override
	protected boolean afterSave(final boolean newRecord, boolean success) {
		/**
		 * se comenta el codigo por rqm 4126, esta generando 3 registros en la tabla, tambien se comenta en el afterSave de MProduct
		 * unicamente se deja el codigo en el afterSave de MEXMEProductoOrg
		 */
		
		/*if (!success) {
			return success;
		}
		
		if (success) {
			if (isActive()) {
				success = MEXMETGenProdTrade.genNewNotPrefGenProdTrade(
						getCtx(), getEXME_GenProduct_ID(), get_TrxName());
			} else {
				success = MEXMETGenProdTrade.deleteNotPrefGenProdTrade(
						getCtx(), getEXME_GenProduct_ID(), get_TrxName());
			}
		}*/
		return success;
	}

	@Override
	protected boolean afterDelete(boolean success) {
		if (success) {
			success = MEXMETGenProdTrade.deleteNotPrefGenProdTrade(getCtx(),
					getEXME_GenProduct_ID(), get_TrxName());
		}
		return success;
	}

	/**
	 * Actualiza la tabla exme_t_genprod_trade con el generico del producto
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param mproductID
	 * @param whID
	 *            : id del almacen
	 * @param isNew
	 *            : si el registro es nuevo
	 * @param isActive
	 *            : si esta activo o no
	 * @param pIsPref
	 *            : true, considerado en el formulario
	 * @param trxName
	 *            : nombre de transaccion
	 */
	public static void actualizarEXMETGenProdTrade(final Properties ctx,
			final int mproductID, final int whID, final boolean isNew,
			final boolean isActive, final boolean pIsPref, final boolean pFormulary,
			final String trxName) {
		final MProduct prod = new MProduct(ctx, mproductID, trxName);
		// Si es nuevo y esta activo
		if (isNew && isActive) {
			
			if (prod.getEXME_GenProduct_ID() > 0) {
				MEXMETGenProdTrade.genNewPrefGenProdTrade(ctx,
						prod.getEXME_GenProduct_ID(), whID, pIsPref,
						pFormulary, trxName);
			}
		} else {

			// Y si no esta activo o dejo de ser del formulario
			if (!isActive || !pIsPref || !pFormulary) {
//				final MProduct prod = new MProduct(ctx, mproductID, null);
				MEXMETGenProdTrade.deletePrefGenProdTrade(ctx,
						prod.getEXME_GenProduct_ID(), mproductID, isActive,
						pIsPref, pFormulary, trxName);
			} else {
				if (isActive && pIsPref && pFormulary) {
//					final MProduct prod = new MProduct(ctx, mproductID, trxName);
					if (prod.getEXME_GenProduct_ID() > 0) {
						MEXMETGenProdTrade.genNewPrefGenProdTrade(ctx,
								prod.getEXME_GenProduct_ID(), whID, pIsPref,
								pFormulary, trxName);
					}
				}
			}
		}

	}

	/**
	 * Listado de <LabelValueBean>
	 * 
	 * @param ctx
	 *            Contexto
	 * @param trxName
	 *            nombre de transaccion
	 * @return Listado de <LabelValueBean>
	 */
	public static List<LabelValueBean> getLVB(final Properties ctx,
			final String trxName) {
		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			sql.append("SELECT  * FROM EXME_GenProduct ").append(
					" where isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_GenProduct_ID");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				lista.add(new LabelValueBean(rSet
						.getString(COLUMNNAME_CSA_Code), rSet
						.getString(COLUMNNAME_EXME_GenProduct_ID)));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rSet, pstmt);
		}
		return lista;
	}

	/**
	 * Localiza los productos relacionado a l genproduct
	 * 
	 * @param ctx
	 *            Contexto
	 * @param genProductId
	 *            id gen product a buscar
	 * @param trxName
	 *            nombre de transaccion
	 * @return List<Integer> listado de Ids del producto relacionado al
	 *         genproduct
	 */
	public static List<Integer> getDetail(final Properties ctx,
			final Integer genProductId, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		final List<Integer> lst = new ArrayList<Integer>();
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select m_product_id from  M_Product where EXME_GenProduct_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, genProductId);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				lst.add(rSet.getInt("m_product_id"));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}

		return lst;
	}

	/**
	 * Localiza el producto relacionado a l genproduct
	 * 
	 * @param ctx
	 *            Contexto
	 * @param genProductId
	 *            id gen product a buscar
	 * @param trxName
	 *            nombre de transaccion
	 * @return el id del producto primera coincidencia
	 */
	public static int getOneProductId(final Properties ctx,
			final Integer genProductId, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		int key = 0;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select m_product_id from  M_Product where EXME_GenProduct_ID = ? and isActive = 'Y' ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, genProductId);
			rSet = pstmt.executeQuery();

			if (rSet.next()) {
				key = rSet.getInt("m_product_id");
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}

		return key;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(final BigDecimal quantity) {
		this.quantity = quantity;
	}

	public int getExme_Frecuencia_ID() {
		return frecuenciaID;
	}

	public void setExme_Frecuencia_ID(final int exme_Frecuencia_ID) {
		this.frecuenciaID = exme_Frecuencia_ID;
	}

	public int getRefill() {
		return refill;
	}

	public void setRefill(final int refill) {
		this.refill = refill;
	}

	public void setQty(final String qty) {
		this.qty = qty;
	}

	public String getQty() {
		return qty;
	}

	public void setDose(final String dose) {
		this.dose = dose;
	}

	public String getDose() {
		return dose;
	}

	public MEXMERoute getVia() {
		if (via == null && getEXME_Route_ID() > 0) {
			via = new MEXMERoute(getCtx(), getEXME_Route_ID(), null);
		}
		return via;
	}

	public void setVia(final MEXMERoute via) {
		this.via = via;
	}

	public void setDosis(final MEXMEDoseForm dosis) {
		this.dosis = dosis;
	}

	public MEXMEDoseForm getDosis() {
		if (dosis == null && getEXME_DoseForm_ID() > 0) {
			dosis = new MEXMEDoseForm(getCtx(), getEXME_DoseForm_ID(), null);
		}
		return dosis;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(final String ndc) {
		this.ndc = ndc;
	}

	public int getKey() {
		return key;
	}

	public void setKey(final int key) {
		this.key = key;
	}

	/**
	 * Valida si un producto generico es de determinada clase de producto
	 * @param productClass Clase de producto
	 * @return
	 */
	public boolean isProductClass(String productClass) {
		if (productClass == null) {
			return false;
		} else {
			String sql = "SELECT DISTINCT ProductClass FROM M_Product WHERE isActive='Y' AND EXME_GenProduct_ID=? AND ProductClass=?";
			return productClass.equals(DB.getSQLValueString(null, sql, this.getEXME_GenProduct_ID(), productClass));
		}
	}
	
	/** valida si el producto generico es vacuna, es decir la clase del producto relacionado es 'IM' */
	public static boolean isVaccine(int genProdId) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT count(*) ");
		sql.append("FROM  M_Product  ");
		sql.append("WHERE isActive='Y' ");
		sql.append("AND   ProductClass=? ");
		sql.append("AND   EXME_GenProduct_ID=? ");
		return DB.getSQLValue(null, sql.toString(), MProduct.PRODUCTCLASS_Immunization, genProdId) > 0;
	}

}
