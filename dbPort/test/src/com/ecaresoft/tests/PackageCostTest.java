package com.ecaresoft.tests;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.I_EXME_CtaPac;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECtaPacPaq;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Pruebas de costos de paquetes
 * 
 * @author odelarosa
 * 
 */
public class PackageCostTest extends TestCase {

	private static CLogger s_log = CLogger.getCLogger(PackageCostTest.class);
	private int ctaPacId;
	private int packVersionId;

	/**
	 * 
	 */
	public PackageCostTest() {

	}

	/**
	 * @param name
	 */
	public PackageCostTest(String name) {
		super(name);
	}

	/**
	 * Llena datos de cuenta paciente y versión de paquete
	 */
	private void fillData() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  cta.exme_ctapac_id, ");
		sql.append("  det.EXME_PaqBase_Version_id ");
		sql.append("FROM ");
		sql.append("  exme_ctapac cta ");
		sql.append("  INNER JOIN exme_ctapacdet det ");
		sql.append("  ON cta.exme_ctapac_id = det.exme_ctapac_id ");
		sql.append("WHERE ");
		sql.append("  det.isactive = 'Y' AND ");
		sql.append("  det.tipolinea = 'EX' AND ");
		sql.append("  det.EXME_PaqBase_Version_id IS NOT ");
		sql.append("  NULL AND ");
		sql.append("  det.costaverage IS NOT ");
		sql.append("  NULL AND ");
		sql.append("  det.coststandard IS NOT ");
		sql.append("  NULL AND ");
		sql.append("  det.pricelastpo IS NOT ");
		sql.append("  NULL ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), Constantes.SPACE, I_EXME_CtaPac.Table_Name, "cta"));

		if (DB.isPostgreSQL()) {
			sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ctaPacId = rs.getInt(1);
				packVersionId = rs.getInt(2);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Costo del paquete
	 * 
	 * @return
	 */
	private BigDecimal getCost() {
		BigDecimal cost = BigDecimal.ZERO;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  SUM(qtyordered * coststandard) ");
		sql.append("  AS cost ");
		sql.append("FROM ");
		sql.append("  exme_ctapacdet ");
		sql.append("WHERE ");
		sql.append("  exme_ctapac_id = ? AND ");
		sql.append("  exme_paqbase_version_id = ? AND ");
		sql.append("  tipolinea = 'EX' AND ");
		sql.append("  isactive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacId);
			pstmt.setInt(2, packVersionId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cost = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return cost;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		try {
			TestUtils.setUpMx();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
	}

	@Test
	public void testCost() {
		fillData();

		if (ctaPacId > 0) {
			BigDecimal cost = getCost();

			List<MEXMECtaPacPaq> list = MEXMECtaPacPaq.get(Env.getCtx(), " exme_ctapac_id = ? and exme_paqbase_version_id = ? ", null, null, ctaPacId, packVersionId);

			MEXMECtaPacPaq ctaPaq = list.get(0);

			MCtaPacDet det = ctaPaq.getCargo();

			if (det != null) {
				if (det.getCostStandard().compareTo(cost) != 0) {
					assertNotNull(null);
				}
			}
		}
	}

}
