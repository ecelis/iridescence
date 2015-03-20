package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

import com.ecaresoft.util.PackageBalance;

/**
 * Control de Saldo del Paquete
 * 
 * @author odelarosa
 * 
 */
public class MEXMESaldoPaquete extends X_EXME_SaldoPaquete {

	private static final long serialVersionUID = 3339761856216029834L;
	private static CLogger LOGGER = CLogger.getCLogger(MEXMESaldoPaquete.class);
	public final static String SQL_INSERT;
	
	static {
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO EXME_SaldoPaquete (EXME_SaldoPaquete_ID, Created, Updated, IsActive, ");
		str.append("CreatedBy, UpdatedBy, AD_Client_ID, AD_Org_ID, EXME_CtaPac_ID, EXME_PaqBase_Version_ID, ");
		str.append("M_Product_ID, C_UOM_ID, Amount, RemainingAmt) VALUES (nextid(?, 'N'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		SQL_INSERT = str.toString();
	}

	/**
	 * @param ctx
	 * @param EXME_SaldoPaquete_ID
	 * @param trxName
	 */
	public MEXMESaldoPaquete(Properties ctx, int EXME_SaldoPaquete_ID, String trxName) {
		super(ctx, EXME_SaldoPaquete_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESaldoPaquete(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Revisa las líneas del balance donde el producto y cuenta coinciden
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @param productId
	 *            Producto
	 * @param uomId
	 *            Unidad del Producto
	 * @param Versión
	 *            del Paquete
	 * @param trxName
	 *            Nombre de Trx
	 * @return Listado de coincidencias
	 */
	public static List<MEXMESaldoPaquete> getAvailableLines(Properties ctx, int ctaPacId, int productId, int uomId, int paqBaseVersionId, String trxName) {
		List<MEXMESaldoPaquete> list = new ArrayList<MEXMESaldoPaquete>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  sp.* ");
		sql.append("FROM ");
		sql.append("  exme_saldopaquete sp ");
		sql.append("WHERE ");
		sql.append("  sp.m_product_id = ? AND ");
		sql.append("  sp.remainingamt > 0 AND ");
		sql.append("  sp.exme_ctapac_id = ? AND ");
		sql.append("  sp.c_uom_id = ? AND ");
		sql.append("  sp.sustituto_id IS NULL AND ");
		sql.append("  sp.exme_paqbase_version_id = ? ");
		sql.append("ORDER BY ");
		sql.append("  sp.created asc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, ctaPacId);
			pstmt.setInt(3, uomId);
			pstmt.setInt(4, paqBaseVersionId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMESaldoPaquete(ctx, rs, null));
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Revisa las líneas del balance donde el producto sustituto y cuenta
	 * coinciden
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param productId
	 *            Producto Sustituto
	 * @param uomId
	 *            Unidad de Medida
	 * @param paqBaseVersionId
	 *            Versión del Paquete
	 * @param trxName
	 *            Nombre de Trx
	 * @return Listado de coincidencias
	 */
	public static List<MEXMESaldoPaquete> getAvailableLinesSubs(Properties ctx, int ctaPacId, int productId, int uomId, int paqBaseVersionId, String trxName) {
		List<MEXMESaldoPaquete> list = new ArrayList<MEXMESaldoPaquete>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  sp.* ");
		sql.append("FROM ");
		sql.append("  exme_saldopaquete sp ");
		sql.append("WHERE ");
		sql.append("  sp.sustituto_ID = ? AND ");
		sql.append("  sp.remainingamt > 0 AND ");
		sql.append("  sp.exme_ctapac_id = ? AND ");
		sql.append("  sp.uom_substitute_ID = ? AND ");
		sql.append("  sp.exme_paqbase_version_id = ? ");
		sql.append("ORDER BY ");
		sql.append("  sp.remainingamt asc");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, ctaPacId);
			pstmt.setInt(3, uomId);
			pstmt.setInt(4, paqBaseVersionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMESaldoPaquete(ctx, rs, null));
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Revisa las líneas disponibles para usar, puede ser como producto o
	 * sustituto
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param productId
	 *            Producto
	 * @param uomId
	 *            Unidad de Medida
	 * @param Versión
	 *            del Paquete
	 * @param trxName
	 *            Nombre de Trx
	 * @return Listado de coincidencias
	 */
	public static List<MEXMESaldoPaquete> getAvailableLinesNewSubs(Properties ctx, int ctaPacId, int productId, int uomId, int paqBaseVersionId, String trxName) {
		List<MEXMESaldoPaquete> list = new ArrayList<MEXMESaldoPaquete>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  s.qtyTarget, sp.* ");
		sql.append("FROM ");
		sql.append("  exme_saldopaquete sp ");
		sql.append("  INNER JOIN m_substitute s ");
		sql.append("  ON (sp.M_product_id = s.m_product_id AND ");
		sql.append("  sp.c_uom_id = s.c_uom_id AND ");
		sql.append("  s.substitute_id = ? AND ");
		sql.append("  s.uom_substitute_id = ?) ");
		sql.append("WHERE ");
		sql.append("  sp.sustituto_id IS NULL AND ");
		sql.append("  sp.exme_ctapac_id = ? AND ");
		sql.append("  sp.exme_paqbase_version_id = ? AND ");
		sql.append("  s.isactive = ");
		sql.append("'Y' ORDER BY ");
		sql.append("  sp.created asc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, uomId);
			pstmt.setInt(3, ctaPacId);
			pstmt.setInt(4, paqBaseVersionId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMESaldoPaquete balance = new MEXMESaldoPaquete(ctx, rs, null);
				balance.setSustituto_ID(productId);
				balance.setUOM_Substitute_ID(uomId);
				balance.setAmount(rs.getBigDecimal("qtyTarget"));
				balance.setRemainingAmt(rs.getBigDecimal("qtyTarget"));

				list.add(balance);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Borrar todo el saldo de paquetes de la cuenta
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param trxName
	 *            Nombre de la trx
	 */
	public static void deleteAll(Properties ctx, int ctaPacId, int paqVersionId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  EXME_SaldoPaquete ");
		sql.append("WHERE ");
		sql.append("  EXME_CtaPac_ID = ? AND ");
		sql.append("  EXME_PaqBase_Version_ID = ? ");

		DB.executeUpdate(sql.toString(), new Object[] { ctaPacId , paqVersionId }, trxName);

		sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  exme_ctapacdet ");
		sql.append("SET ");
		sql.append("  tipolinea = ? ,");
		sql.append("  EXME_PaqBase_Version_ID = null ");
		sql.append(" WHERE ");
		sql.append("  exme_ctapac_id = ? AND ");
		sql.append("  tipolinea = ? AND ");
		sql.append("  EXME_PaqBase_Version_ID = ? AND ");
		sql.append("  isActive = 'Y' AND ");
		sql.append("  seDevolvio = 'N' AND ");
		sql.append("  isFacturado = 'N' ");

		DB.executeUpdate(sql.toString(), new Object[] { MCtaPacDet.TIPOLINEA_Charge, ctaPacId, MCtaPacDet.TIPOLINEA_Exempt, paqVersionId }, trxName);
	}
	
	/**
	 * Remueve del saldo de paquetes productos sin llamar el march completo
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param paqBaseVersionId
	 *            Versión del paquete
	 * @param productId
	 *            Producto a remover
	 * @param uomId
	 *            Unidad del producto
	 * @param amount
	 *            Cantidad
	 * @param substitute
	 *            Revisar o no sustitutos
	 * @param trxName
	 *            Trx
	 */
	private static boolean removeFromPackage(Properties ctx, int ctaPacId, int paqBaseVersionId, int productId, int uomId, BigDecimal amount, boolean substitute, String trxName){
		boolean ok = true;
		
		List<Object> params = new ArrayList<Object>();
		params.add(ctaPacId);
		params.add(paqBaseVersionId);
		params.add(productId);
		params.add(uomId);
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("  exme_ctapac_id = ? AND ");
		sql.append("  exme_paqbase_version_id = ? AND ");
		
		if (substitute) {
			sql.append("  sustituto_id = ? AND ");
			sql.append("  uom_substitute_id = ? ");
		} else {
			sql.append("  m_product_id = ? AND ");
			sql.append("  c_uom_id = ? AND ");
			sql.append("  sustituto_id  = ? ");
			params.add(productId);
		}	

		Query query = new Query(ctx, Table_Name, sql.toString(), trxName);
		query.addAccessLevelSQL(true);
		query.setOnlyActiveRecords(true);
		query.setParameters(params);
		query.setOrderBy(" remainingamt ");

		List<MEXMESaldoPaquete> saldos = query.list();

		BigDecimal diff = amount;
		
		List<int[]> values = new ArrayList<int[]>();

		for (MEXMESaldoPaquete saldo : saldos) {
			if (BigDecimal.ZERO.compareTo(diff) != 0) {
				values.add(new int[] { saldo.getM_Product_ID(), saldo.getC_UOM_ID() });
				if (diff.compareTo(saldo.getRemainingAmt()) >= 0) {
					saldo.setRemainingAmt(saldo.getAmount());
					saldo.setSustituto_ID(-1);
				} else if (diff.compareTo(saldo.getRemainingAmt()) < 0) {
					saldo.setRemainingAmt(saldo.getRemainingAmt().min(diff));
				}
				
				diff = diff.subtract(saldo.getRemainingAmt());
				
				ok = saldo.save();

				if (!ok) {
					break;
				}
			} else {
				break;
			}
		}
		
		// Esto revisará cargos extras que pueden pertenecer al paquete
		if (ok && !values.isEmpty()) {
			sql = new StringBuilder("select exme_ctapacdet_id, '' from exme_ctapacdet where ");
			sql.append("  exme_ctapac_id = ? AND ");
			sql.append("  m_product_id = ? AND ");
			sql.append("  c_uom_id = ? AND ");
			sql.append("  tipolinea = ? AND ");
			sql.append("  sedevolvio = ? AND ");
			sql.append("  isactive = ? AND ");
			sql.append("  isfacturado = ? ");
			sql.append(" Order By created");

			for (int[] value : values) {
				params = new ArrayList<Object>();
				params.add(ctaPacId);
				params.add(value[0]);
				params.add(value[1]);
				params.add(X_EXME_CtaPacDet.TIPOLINEA_Charge);
				params.add("N");
				params.add("Y");
				params.add("N");

				List<KeyNamePair> list = DB.getKeyNamePairsList(sql.toString(), false, params.toArray(new Object[] {}));

				for (KeyNamePair pair : list) {
					new PackageBalance(ctx, pair.getKey(), trxName).doIt();
				}
			}
		}
		
		// Si no es revision de sustituto, busca sus sustitutos
		if (ok && !substitute) {
			List<Integer> ids = MCtaPacDet.getSubstituteCharges(ctx, ctaPacId, productId, uomId, trxName);

			for (int id : ids) {
				new PackageBalance(ctx, id, trxName).doIt();
			}
		}
		
		return ok;
	}
	
	/**
	 * Remueve del saldo de paquetes productos sin llamar el march completo
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param paqBaseVersionId
	 *            Versión del paquete
	 * @param productId
	 *            Producto a remover
	 * @param uomId
	 *            Unidad del producto
	 * @param amount
	 *            Cantidad
	 * @param trxName
	 *            Trx
	 */
	public static boolean removeFromPackage(Properties ctx, int ctaPacId, int paqBaseVersionId, int productId, int uomId, BigDecimal amount, String trxName) {
		boolean ok = removeFromPackage(ctx, ctaPacId, paqBaseVersionId, productId, uomId, amount, false, trxName);

		if (ok) {
			ok = removeFromPackage(ctx, ctaPacId, paqBaseVersionId, productId, uomId, amount, true, trxName);
		}

		return ok;
	}

}