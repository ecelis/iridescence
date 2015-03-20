package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

/**
 * @author odelarosa
 * 
 */
public class MEXMEPedidoBase extends MPedidoBase implements Comparable<MEXMEPedidoBase> {

	private static CLogger s_log = CLogger.getCLogger(MEXMEPedidoBase.class);
	private static final long serialVersionUID = 1401010067826785736L;

	public static Collection<MEXMEPedidoBase> get(Properties ctx, List<Integer> interventions, int doctorId, String trxName) {
		TreeSet<MEXMEPedidoBase> lst = new TreeSet<MEXMEPedidoBase>();
		lst.addAll(getFromInterventions(ctx, interventions, trxName));
		lst.addAll(getFromDoctor(ctx, doctorId, trxName));
		return lst;
	}

	/**
	 * Líneas del pedido base, no se toman en cuenta productos inactivos o en
	 * desuso
	 * 
	 * @param ctx
	 * @param pedidoBaseId
	 * @param trxName
	 * @return
	 */
	public static List<MEXME_PedidoBaseDet> getDet(Properties ctx, int pedidoBaseId, String trxName) {
		List<MEXME_PedidoBaseDet> lst = new ArrayList<MEXME_PedidoBaseDet>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select det.* from EXME_PedidoBaseDet det ");
			sql.append(" INNER JOIN M_Product p on det.m_product_id = p.m_product_id ");
			sql.append(" INNER JOIN EXME_ProductoOrg pOrg on (p.m_product_id = pOrg.m_product_id and pOrg.ad_org_id = det.ad_org_id )");
			sql.append(" where det.EXME_PedidoBase_ID = ? ");
			sql.append(" AND det.isActive = 'Y' AND p.isActive = 'Y' AND pOrg.unused = 'N' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXME_PedidoBaseDet.Table_Name, "det"));
			sql.append(" Order by p.name");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pedidoBaseId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXME_PedidoBaseDet(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lst;
	}

	public static List<MEXMEPedidoBase> getFromDoctor(Properties ctx, int doctorId, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEPedidoBase> lst = new ArrayList<MEXMEPedidoBase>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from EXME_PedidoBase where EXME_Medico_ID = ? ");
			sql.append(" AND isActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPedidoBase.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, doctorId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEPedidoBase(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lst;
	}

	public static List<MEXMEPedidoBase> getFromInterventions(Properties ctx, List<Integer> interventions, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEPedidoBase> lst = new ArrayList<MEXMEPedidoBase>();
		if (interventions != null && !interventions.isEmpty()) {
			try {
				StringBuilder sql = new StringBuilder();
				sql.append(" select * from EXME_PedidoBase where EXME_Intervencion_ID in ( ");
				sql.append(StringUtils.join(interventions, ','));
				sql.append(" ) AND isActive = 'Y' ");
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPedidoBase.Table_Name));

				pstmt = DB.prepareStatement(sql.toString(), trxName);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					lst.add(new MEXMEPedidoBase(ctx, rs, trxName));
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, null, e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		return lst;
	}

	public static List<MEXMEPedidoBase> search(Properties ctx, String name, int doctorId, String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEXMEPedidoBase> lst = new ArrayList<MEXMEPedidoBase>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from EXME_PedidoBase where upper(name) like ? ");
			sql.append(" AND isActive = 'Y' ");
			if (doctorId > 0) {
				sql.append(" AND EXME_Medico_ID = ? ");
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEPedidoBase.Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, StringUtils.isEmpty(name) ? "%" : "%" + name.toUpperCase() + "%");
			if (doctorId > 0) {
				pstmt.setInt(2, doctorId);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new MEXMEPedidoBase(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lst;
	}

	/**
	 * @param ctx
	 * @param EXME_PedidoBase_ID
	 * @param trxName
	 */
	public MEXMEPedidoBase(Properties ctx, int EXME_PedidoBase_ID, String trxName) {
		super(ctx, EXME_PedidoBase_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPedidoBase(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public int compareTo(MEXMEPedidoBase other) {
		Integer t = getEXME_PedidoBase_ID();
		Integer p = other.getEXME_PedidoBase_ID();
		return t.compareTo(p);
	}

	public MEXMEPedidoBase copy() {
		MEXMEPedidoBase copy = null;
		Trx m_trx = null;
		try {
			m_trx = Trx.get(Trx.createTrxName("copy"), true);
			copy = copy(m_trx.getTrxName());
			if (copy != null) {
				Trx.commit(m_trx);
			} else {
				Trx.rollback(m_trx);
			}
		} catch (Exception ex) {
			Trx.rollback(m_trx);
		} finally {
			Trx.close(m_trx);
		}
		return copy;
	}

	public MEXMEPedidoBase copy(String trxName) {
		MEXMEPedidoBase copy = new MEXMEPedidoBase(getCtx(), 0, null);

		copy.setName(getName() + " - " + Utilerias.getAppMsg(getCtx(), "msj.copia"));
		copy.setEXME_Medico_ID(getEXME_Medico_ID());
		copy.setEXME_Intervencion_ID(getEXME_Intervencion_ID());

		if (copy.save(trxName)) {
			List<MEXME_PedidoBaseDet> lst = getDet(getCtx(), getEXME_PedidoBase_ID(), trxName);
			for (MEXME_PedidoBaseDet det : lst) {
				MEXME_PedidoBaseDet copyDet = new MEXME_PedidoBaseDet(getCtx(), 0, null);
				PO.copyValues(det, copyDet);
				copyDet.setEXME_PedidoBase_ID(copy.getEXME_PedidoBase_ID());

				if (!copyDet.save(trxName)) {
					copy = null;
					break;
				}
			}
		} else {
			copy = null;
		}

		return copy;
	}

}
