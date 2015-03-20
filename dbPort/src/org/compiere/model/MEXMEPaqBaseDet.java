/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.bpm.GetCost;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Modelo del detalle de la version del paquete
 * 
 * @author Expert
 */
public class MEXMEPaqBaseDet extends X_EXME_PaqBaseDet {

	/** serialVersionUID. */
	private static final long			serialVersionUID	= -7447334418012330652L;
	/** Static logger */
	private static CLogger				slog				= CLogger.getCLogger(MEXMEPaqBaseDet.class);
	// /** Producto */
	// private transient MProduct mProducto = null;
	// /** Precio de la linea */
	// private MPrecios precio = null;
	// /** Atributos */
	// private List<MEXMEPaqBaseAtributo> lstAtributos = null;
	// /** Costos */
	// private GetCost costos = null;
	/** Version del paquete */
	private MEXMEPaqBaseVersion			paqBaseVersion		= null;
	/** Producto */
	private transient MEXMEProduct		mProduct			= null;
	// /** Tasa de impuestos */
	// private transient MEXMETax mTax = null;
	/** Listado de atributos modificadores */
	private List<MEXMEPaqBaseAtributo>	lstAtributosModif	= new ArrayList<MEXMEPaqBaseAtributo>();
	/** Listado de atributos diagnostico */
	private List<MEXMEPaqBaseAtributo>	lstAtributosDiag	= new ArrayList<MEXMEPaqBaseAtributo>();

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param exmePaqBaseDetID
	 *            Id de la linea
	 * @param trxName
	 *            Nombre de transaccion
	 */
	public MEXMEPaqBaseDet(final Properties ctx, final int exmePaqBaseDetID, final String trxName) {
		super(ctx, exmePaqBaseDetID, trxName);
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            Contexto
	 * @param rs
	 *            Resultset
	 * @param trxName
	 *            nombre de transaccion
	 */
	public MEXMEPaqBaseDet(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	}

	// public MProduct getMProducto() {
	// if (mProducto == null) {
	// mProducto = MProduct.get(getCtx(), getM_Product_ID());
	// }
	// return mProducto;
	// }

	// public MPrecios getPrecio() {
	// return this.precio;
	// }

	public void setPrecio(final MPrecios pprecio) {
		setPriceLimit(pprecio.getPriceLimit() == null ? Env.ZERO : pprecio.getPriceLimit());
		setPriceList(pprecio.getPriceList() == null ? Env.ZERO : pprecio.getPriceList());
		setPriceActual(pprecio.getPriceStd() == null ? Env.ZERO : pprecio.getPriceStd());
		// this.precio = pprecio;
	}

	//
	// public List<MEXMEPaqBaseAtributo> getLstAtributos() {
	// return this.lstAtributos;
	// }
	//
	// public void setLstAtributos(final List<MEXMEPaqBaseAtributo> lstAtributos) {
	// this.lstAtributos = lstAtributos;
	// }
	//
	// public GetCost getCostos() {
	// return this.costos;
	// }
	//
	// public void setCostos(final GetCost costos) {
	// this.costos = costos;
	// }
	//
	// public void setPaqBaseVersion(final MEXMEPaqBaseVersion paqBaseVersion) {
	// this.paqBaseVersion = paqBaseVersion;
	// }

	public MEXMEPaqBaseVersion getPaqBaseVersion() {
		if (paqBaseVersion == null) {
			paqBaseVersion = new MEXMEPaqBaseVersion(getCtx(), getEXME_PaqBase_Version_ID(), null);
		}
		return paqBaseVersion;
	}

	// public MEXMETax getTax() {
	// if (mTax == null) {
	// mTax = MEXMETax.getImpuestoProducto(getCtx(), getM_Product_ID(),
	// get_TrxName(), getLineNetAmt());
	// }
	// return mTax;
	// }

	/**
	 * Totales
	 */
	public void setLineNetAmt() {
		final MPriceList lista = new MPriceList(getCtx(), getPaqBaseVersion().getM_PriceList_ID(), get_TrxName());
		setC_Currency_ID(lista.getC_Currency_ID());
		setLineNetAmt(getCantidad().multiply(getPriceActual()));
		setTaxAmt(new MTax(getCtx(), getC_Tax_ID(), null).calculateTax(getLineNetAmt(), lista.isTaxIncluded(), lista.getPricePrecision().intValue()));
		setLineTotalAmt(getLineNetAmt().add(getTaxAmt()));
	}

	// public void setProduct(final MEXMEProduct paqBaseVersion) {
	// this.mProduct = paqBaseVersion;
	// }

	public MEXMEProduct getProduct() {
		if (mProduct == null) {
			mProduct = new MEXMEProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return mProduct;
	}

	// public MEXMEUOM getmUom() {
	// return mUom;
	// }

	// public void setmUom(final MEXMEUOM mUom) {
	// this.mUom = mUom;
	// }

	// public MEXMEUOM getUom() {
	// return new MEXMEUOM(getCtx(), getC_UOM_ID(), get_TrxName());
	// }
	//
	//
	public MUOM getMOp_Uom() {
		return new MUOM(getCtx(), getOp_Uom(), get_TrxName());
	}

	/**
	 * getAtributo
	 * 
	 * @param ctx
	 * @param exmePacienteID
	 * @param trxName
	 * @return
	 * @author erick
	 * @throws SQLException
	 */
	public static List<MEXMEPaqBaseAtributo> getAtributo(final Properties ctx, final int pPaqBaseDetID, final String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		final List<MEXMEPaqBaseAtributo> atributo = new ArrayList<MEXMEPaqBaseAtributo>();

		try {
			sql.append("select * from  EXME_PAQBASEATRIBUTO where EXME_PaqBaseDet_ID = ?").append(
				" AND EXME_PAQBASEATRIBUTO.IsActive = 'Y' order by CREATED desc");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pPaqBaseDetID);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				atributo.add(new MEXMEPaqBaseAtributo(ctx, rset, trxName));
			}

		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		return atributo;
	}

	// /**
	// * Todos los paquetes por nivel de acceso activos
	// *
	// * @param ctx
	// * @param trxName
	// * @return
	// */
	// public static List<MEXMEPaqBaseDet> get(final Properties ctx,
	// final int paqBaseVersionID, final String trxName) {
	//
	// if (ctx == null) {
	// return null;
	// }
	//
	// final StringBuilder sql = new StringBuilder();
	// final List<Integer> params = new ArrayList<Integer>();
	//
	// sql.append(" SELECT * FROM EXME_PaqBaseDet ")
	// .append(" WHERE EXME_PaqBaseDet.IsActive = 'Y' ")
	// .append(" AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
	// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
	// X_EXME_PaqBaseDet.Table_Name))
	// .append(" ORDER BY EXME_PaqBaseDet.Secuencia ASC ");
	//
	// params.add(paqBaseVersionID);
	// return get(ctx, sql.toString(), params, null);
	// }

	/**
	 * Todos los paquetes por nivel de acceso activos
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getDetalleDeVersion(final Properties ctx, final int paqBaseVersionID, final boolean isActive,
		final String trxName) {
		return new Query(ctx, MEXMEPaqBaseDet.Table_Name, " EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ? ", trxName)
		//
			.setOnlyActiveRecords(isActive)
			//
			.setOrderBy(" EXME_PaqBaseDet.Secuencia ").setParameters(paqBaseVersionID).list();
	}

	/**
	 * Obtener varias versiones
	 * 
	 * @param ctx
	 * @param cadena
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getMuchasVersiones(final Properties ctx, final String cadena, final String trxName) {

		if (ctx == null) {
			return null;
		}

		final StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM EXME_PaqBaseDet ").append(" WHERE EXME_PaqBaseDet.IsActive = 'Y' ")
			.append(" AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID IN (").append(cadena).append(") ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_PaqBaseDet.Table_Name)).append(" ORDER BY EXME_PaqBaseDet.Secuencia DESC ");

		return get(ctx, sql.toString(), null, null);
	}

	/**
	 * Metdoos genericopara ejecutar una consulta y devuelva una lista de
	 * objetos MEXMEPaqBase
	 * 
	 * @param ctx
	 *            contexto Obligatorio
	 * @param sql
	 *            consulta
	 * @param params
	 *            parametros
	 * @param trxName
	 *            nombre de la transaccion
	 * @return List<MEXMEPaqBase>
	 */
	public static List<MEXMEPaqBaseDet> get(final Properties ctx, final String sql, final List<?> params, final String trxName) {

		final List<MEXMEPaqBaseDet> resultados = new ArrayList<MEXMEPaqBaseDet>();

		if (ctx == null || sql == null) {
			return null;
		}

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			DB.setParameters(pstmt, params);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				resultados.add(new MEXMEPaqBaseDet(ctx, rset, trxName));
			}
		} catch (SQLException sqle) {
			slog.log(Level.SEVERE, sql, sqle);
		} finally {
			DB.close(rset, pstmt);
		}

		return resultados;
	}

	/**
	 * Detalle del paquete
	 * No considera las lineas en desuso
	 * 
	 * @param ctx
	 * @param paqBaseVersionID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getDetalle(final Properties ctx, final int paqBaseVersionID, final String trxName) {
		final List<MEXMEPaqBaseDet> resultados = new ArrayList<MEXMEPaqBaseDet>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  baseDet.* ");
		sql.append("FROM ");
		sql.append("  EXME_PaqBaseDet baseDet ");
		sql.append("  INNER JOIN M_Product prod ");
		sql.append("  ON (prod.m_product_id = baseDet.m_product_id) ");
		sql.append("  INNER JOIN EXME_ProductoOrg prodOrg ");
		sql.append("  ON (prodOrg.ad_org_id = baseDet.ad_org_id AND ");
		sql.append("  prodOrg.m_product_id = baseDet.m_product_id) ");
		sql.append("WHERE ");
		sql.append("  EXME_PaqBase_Version_ID =? AND ");
		sql.append("  prodOrg.unused = 'N' AND ");
		sql.append("  prod.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPaqBaseDet.Table_Name, "baseDet"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, paqBaseVersionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultados.add(new MEXMEPaqBaseDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return resultados;
	}

	public List<MEXMEPaqBaseAtributo> getLstAtributosModif() {
		return getLstAtributosModif(false);
	}

	public List<MEXMEPaqBaseAtributo> getLstAtributosModif(boolean reload) {
		if (reload) {
			lstAtributosModif = MEXMEPaqBaseAtributo.get(getCtx(), getEXME_PaqBaseDet_ID(), X_EXME_Modifiers.Table_ID, get_TableName());
		}

		if (lstAtributosModif == null) {
			lstAtributosModif = new ArrayList<MEXMEPaqBaseAtributo>();
		}
		return lstAtributosModif;
	}

	// public void setLstAtributosModif(
	// List<MEXMEPaqBaseAtributo> lstAtributosModif) {
	// this.lstAtributosModif = lstAtributosModif;
	// }

	public List<MEXMEPaqBaseAtributo> getLstAtributosDiag() {
		return getLstAtributosDiag(false);
	}

	public List<MEXMEPaqBaseAtributo> getLstAtributosDiag(boolean reload) {
		if (reload) {
			lstAtributosDiag = MEXMEPaqBaseAtributo.get(getCtx(), getEXME_PaqBaseDet_ID(), X_EXME_Diagnostico.Table_ID, get_TableName());
		}

		if (lstAtributosDiag == null) {
			lstAtributosDiag = new ArrayList<MEXMEPaqBaseAtributo>();
		}
		return lstAtributosDiag;
	}

	// public void setLstAtributosDiag(List<MEXMEPaqBaseAtributo> lstAtributosDiag) {
	// this.lstAtributosDiag = lstAtributosDiag;
	// }

	/**
	 * Guardar lineas o actualizarlas
	 * 
	 * @param ctx
	 * @param lines
	 * @param trxName
	 * @return
	 */
	public static boolean saveDetail(final Properties ctx, final List<MEXMEPaqBaseDet> lines, final int pPaqBaseVersionID, final String trxName) {
		boolean success = false;
		for (final MEXMEPaqBaseDet bean : lines) {
			bean.setEXME_PaqBase_Version_ID(pPaqBaseVersionID);
			if (bean.save(trxName)) {
				success = bean.saveAtributos(bean, bean.getEXME_PaqBaseDet_ID(), false, trxName);
			}
			if (!success) {
				break;
			}
		}
		return success;
	}

	/**
	 * Guardar los modificadores
	 * 
	 * @param pack
	 * @param pPaqBaseDetID
	 * @param delete
	 *            true para borrar en lugar de guardar
	 * @param trxName
	 *            Nombre de transaccion
	 * @author erick
	 * @return
	 */
	public boolean saveAtributos(final MEXMEPaqBaseDet pack, final int pPaqBaseDetID, final boolean delete, final String trxName) {
		boolean exito = true;

		// Borra o crea los diagnosticos relacionados a la linea del detalle del
		// paquete
		for (int i = 0; i < pack.getLstAtributosDiag().size(); i++) {

			if (delete) {
				if (!pack.getLstAtributosDiag().get(i).delete(true, trxName)) {
					exito = false;
				}
			} else {
				pack.getLstAtributosDiag().get(i).setEXME_PaqBaseDet_ID(pPaqBaseDetID);

				if (!pack.getLstAtributosDiag().get(i).save(trxName)) {
					exito = false;
				}
			}
		}

		// Borra o crea los modificadores relacionados a la linea del detalle
		// del paquete
		for (int i = 0; i < pack.getLstAtributosModif().size(); i++) {

			if (delete) {
				if (!pack.getLstAtributosModif().get(i).delete(true, trxName)) {
					exito = false;
				}
			} else {
				pack.getLstAtributosModif().get(i).setEXME_PaqBaseDet_ID(pPaqBaseDetID);

				if (!pack.getLstAtributosModif().get(i).save(trxName)) {
					exito = false;
				}
			}
		}

		return exito;
	}

	/** Llenar los datos adicionales de la línea del paquete */
	public String fillLinePackage() {
		final StringBuilder error = new StringBuilder();
		setName(getProduct().getName());
		// setDescription(getProduct().getDescription());
		setC_UOM_ID(getProduct().getC_UOM_ID());
		setC_UOMVolume_ID(getProduct().getC_UOMVolume_ID());

		error.append(searchPrices()).append("\n");
		error.append(searchCost());

		setC_Tax_ID(getProduct().getC_Tax_ID());
		setLineNetAmt();

		final BigDecimal[] qtys = getProduct().qtyConversion(getCantidad(), getOp_Uom());
		setCantidadAlm(qtys[0]);
		setCantidadVol(qtys[1]);
		return error.toString();
	}

	/** Buscar los precio del producto de la línea del paquete */
	private String searchPrices() {
		String lblError = StringUtils.EMPTY;
		final MPrecios mprecio =
			GetPrice.getPriceVta(getCtx(), getM_Product_ID(), 0, getOp_Uom(), new Timestamp(System.currentTimeMillis()),
				X_EXME_TipoPaciente.TIPOAREA_Hospitalization);
		// if (mprecio.getPriceStd() == null
		// || mprecio.getPriceStd().compareTo(Env.ZERO) == 0) {
		// lblError = Utilerias.getLabel("error.factDirecta.findPrice",
		// getProduct().getName());
		//
		// } else {
		setPrecio(mprecio);
		// }
		return lblError;
	}

	/** Buscar los costos del producto de la línea del paquete */
	private String searchCost() {
		String lblError = StringUtils.EMPTY;
		// Costo del producto
		final GetCost costos = new GetCost(getCtx(), getM_Product_ID());
		BigDecimal costo = costos.costo(true);
		// if (costo == null) {
		// lblError = Utilerias.getLabel("error.costoProd.noExiste");
		//
		// } else {
		setCosto(costo == null ? Env.ZERO : costo);

		// Obtenemos el Esquema
		final MAcctSchema m_as = MAcctSchema.get(getCtx(), Env.getC_AcctSchema_ID(getCtx()));
		if (m_as == null) {
			lblError = Utilerias.getLabel("error.getAcctSchemaElement");

		} else {

			// //
			// final BigDecimal priceLastPO = MTransaction.getProductItemCost(
			// getCtx(), getM_Product_ID(), as,
			// MAcctSchema.COSTINGMETHOD_LastPOPrice);
			//
			// //
			// final BigDecimal costAverage = MCost.getCurrentCost(getProduct(),
			// 0, as, getAD_Org_ID(), MAcctSchema.COSTINGMETHOD_AveragePO,
			// Env.ONE, 0, true, false, get_TrxName());
			//
			// //
			// final BigDecimal costStandar = MCost.getCurrentCost(getProduct(),
			// 0, as, getAD_Org_ID(),
			// MAcctSchema.COSTINGMETHOD_StandardCosting, Env.ONE, 0,
			// true, false, get_TrxName());
			//
			// setPriceLastPO(priceLastPO);
			// setCostStandard(costStandar);
			// setCostAverage(costAverage);

			setCostStandard(getCostStandardRed());
			setCostAverage(getCostAverageRed());
			setPriceLastPO(getPriceLastPORed());
		}
		// }
		return lblError;
	}

	private String	messageError	= null;

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	private int	scale	= MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(getCtx(), "$C_Currency_ID")); // decimas.

	public BigDecimal getCostStandardRed() {
		MCost mcost = MEXMECost.get(getCtx(), getProduct(), X_M_CostElement.COSTINGMETHOD_StandardCosting, null, get_TrxName());
		BigDecimal costo = Env.ZERO;
		if (mcost != null)
			costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);

		return costo;
		// return getCostStandard().setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getCostAverageRed() {
		MCost mcost = MEXMECost.get(getCtx(), getProduct(), X_M_CostElement.COSTINGMETHOD_AveragePO, null, get_TrxName());
		BigDecimal costo = Env.ZERO;
		if (mcost != null)
			costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);

		return costo;
		// return getCostAverage().setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getPriceLastPORed() {
		MCost mcost = MEXMECost.get(getCtx(), getProduct(), X_M_CostElement.COSTINGMETHOD_LastPOPrice, null, get_TrxName());

		BigDecimal costo = Env.ZERO;
		if (mcost != null)
			costo = mcost.getCurrentCostPrice().setScale(scale, BigDecimal.ROUND_HALF_UP);

		return costo;
		// return getPriceLastPO().setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Cuando estamos en el mtto de paquetes, en la edicion es posible que el usuario
	 * decida siempre no actualizar en la lista de detalle de la version la cantidad que
	 * incluye el paquete. CantidadOld conserva la cantidad original
	 */
	private BigDecimal	cantidadOld	= Env.ZERO;

	public BigDecimal getCantidadOld() {
		return cantidadOld;
	}

	public void setCantidadOld(BigDecimal cantidadOld) {
		this.cantidadOld = cantidadOld;
	}

	/**
	 * Retorna el detalle de una version de paquetes
	 * 
	 * @param ctx
	 * @param EXME_PaqBaseVer_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, int EXME_PaqBaseVer_ID, String trxName) {

		List<Integer> params = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM EXME_PaqBaseDet ").append(" WHERE EXME_PaqBase_Version_ID = ? ").append(" AND IsActive = 'Y' ");
		params.add(EXME_PaqBaseVer_ID);
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));

		sql.append(" ORDER BY Secuencia ASC");

		return getLines(ctx, sql.toString(), params, trxName);
	}

	/**
	 * Detalle de varios paquetes
	 * 
	 * @param ctx
	 * @param lstPaqBaseVer
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, List<MEXMEPaqBaseVersion> lstPaqBaseVer, String trxName) {

		List<Integer> params = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM EXME_PaqBaseDet ").append(" WHERE IsActive = 'Y'").append(" AND EXME_PaqBase_Version_ID IN ( ");

		for (int i = 0; i < lstPaqBaseVer.size(); i++) {
			if (i > 0)
				sql.append(" , ");

			sql.append(" ? ");
			params.add(lstPaqBaseVer.get(i).getEXME_PaqBase_Version_ID());
		}

		sql.append(") ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
		sql.append(" ORDER BY Secuencia ASC");

		return getLines(ctx, sql.toString(), params, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPaqBaseDet> getLines(Properties ctx, String sql, List<?> params, String trxName) {

		List<MEXMEPaqBaseDet> lista = new ArrayList<MEXMEPaqBaseDet>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEPaqBaseDet ctaPacPaq = new MEXMEPaqBaseDet(ctx, rs, trxName);
				ctaPacPaq.setCantidadOld(ctaPacPaq.getCantidad());
				lista.add(ctaPacPaq);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}

	// en unidad de medida de almacenamiento
	private BigDecimal	qtyPendiente	= Env.ZERO;

	public BigDecimal getQtyPendiente() {
		return qtyPendiente;
	}

	public void setQtyPendiente(BigDecimal qtyPendiente) {
		this.qtyPendiente = qtyPendiente;
	}

	/**
	 * Busqueda del producto en el detalle de la version
	 * 
	 * @param ctx: Contexto
	 * @param productId: Producto repetido
	 * @param paqBaseDetId: Id de la linea del paquete
	 * @param paqBaseVerId: Id de la version del paquete
	 * @param trxName: Nombre de transacción
	 * @return true, si hay resultados con ese mismo producto
	 */
	public static boolean existeProductoEnDetalle(final Properties ctx, final int productId, final int paqBaseDetId, final int paqBaseVerId,
		final String trxName) {
		final List<MEXMEPaqBaseDet> resultados =
			new Query(ctx, X_EXME_PaqBaseDet.Table_Name,
				" EXME_PaqBaseDet.M_Product_ID = ? AND EXME_PaqBaseDet.EXME_PaqBaseDet_ID <> ? AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ? ",
				trxName)//
				.setParameters(productId, paqBaseDetId, paqBaseVerId)
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EXME_PaqBaseDet.Secuencia ASC ").list();
		return !resultados.isEmpty();
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success && (newRecord || is_ValueChanged(COLUMNNAME_Cantidad))) {
			try {
				int qtyNew = getCantidad().intValue();
				int qtyOld;
				Object obj = get_ValueOld(COLUMNNAME_Cantidad);
				if (newRecord || obj == null) {
					qtyOld = 0;
				} else {
					qtyOld = ((BigDecimal) obj).intValue();
				}
				success = MDHBalanceServices.addServiceToAllPatient(this, qtyNew - qtyOld, get_TrxName());
			} catch (Exception e) {
				slog.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return success;
	}
}
