package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MProduct;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_I_Cost;
import org.compiere.util.DB;
import org.compiere.util.Trx;
/**
 * Importacion de Costos de Productos<br/>
 * Basada en {@link ImportProductCosting}
 * 
 * @author odelarosa
 * 
 */
public class ImportCost extends SvrProcess {

	private int adClientId = 0;
	private boolean deleteOldImported = false;
	
	/**
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		try {
			MCostElement costElement = getCostingMethods(adClientId, MCostElement.COSTINGMETHOD_StandardCosting);

			StringBuilder sql = null;
			int no;

			// Delete Old Imported
			if (deleteOldImported) {
				sql = new StringBuilder("DELETE I_Cost WHERE I_IsImported='Y'");
				sql.append(" AND AD_Client_ID = ");
				sql.append(adClientId);
				no = DB.executeUpdate(sql.toString(), null);
				log.fine("Delete Old Impored = " + no);
			}

			sql = new StringBuilder();
			sql.append("  I_IsImported <> 'Y' OR ");
			sql.append("  I_IsImported IS NULL ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", X_I_Cost.Table_Name));

			Query query = new Query(getCtx(), X_I_Cost.Table_Name, sql.toString(), null);
			List<PO> list = query.list();

			no = 0;
			for (PO po : list) {
				Trx trx = null;
				try {
					trx = Trx.get(Trx.createTrxName("CI"), true);
					no++;
					X_I_Cost iCost = (X_I_Cost) po;
					
					int productId = MProduct.getIdFromValueOrg(getCtx(),iCost.getM_Product_Value(),false);
			 
					// Establecer producto
					if (productId > 0 ) {
						iCost.setM_Product_ID(productId);
					} else {
						iCost.setI_ErrorMsg("ERR=Producto no valido o no existe ");
						iCost.save(trx.getTrxName());
						continue;
					}

					// Establecer esquema
					Query query2 = new Query(getCtx(), MAcctSchema.Table_Name, "trim(name) = ? ", null);
					query2.setParameters(iCost.getC_AcctSchema_Name());
					MAcctSchema schema = query2.first();
					if (schema != null) {
						iCost.setC_AcctSchema_ID(schema.getC_AcctSchema_ID());
					} else {
						iCost.setI_ErrorMsg(StringUtils.defaultIfEmpty(iCost.getI_ErrorMsg(), StringUtils.EMPTY) + "ERR=Esquema Contable no valido,");
						iCost.save(trx.getTrxName());
						continue;
					}

					MCost cost = MCost.get(getCtx(), adClientId, 0, iCost.getM_Product_ID(), schema.getM_CostType_ID(), schema.getC_AcctSchema_ID(), costElement.getM_CostElement_ID(), 0);

					if (cost == null) {
						cost = new MCost(getCtx(), 0, null);
						cost.setClientOrg(adClientId, 0);
						cost.setC_AcctSchema_ID(schema.getC_AcctSchema_ID());
						cost.setM_CostType_ID(schema.getM_CostType_ID());
						cost.setM_Product_ID(iCost.getM_Product_ID());
						cost.setM_AttributeSetInstance_ID(0);
						cost.setM_CostElement_ID(costElement.getM_CostElement_ID());
					}

					cost.setCurrentCostPrice(iCost.getCurrentCostPrice());
					cost.setFutureCostPrice(iCost.getFutureCostPrice());

					iCost.setI_ErrorMsg(null);
					iCost.setProcessed(true);
					iCost.setI_IsImported(true);
					iCost.save(trx.getTrxName());

					if (!cost.save(trx.getTrxName())) {
						iCost.setI_ErrorMsg("ERR=No puede guardarse MCost");
						iCost.setI_IsImported(false);
						iCost.save(trx.getTrxName());
					}
				} catch (Exception e) {
					log.log(Level.SEVERE, null, e);
				} finally {
					Trx.commit(trx);
					Trx.close(trx);
				}
			}

			log.fine("Reset = " + no);
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		}

		return StringUtils.EMPTY;
	}

	/**
	 * Obtiene el elemento de costo segun el cliente y metodo
	 * 
	 * @param AD_Client_ID
	 *            Cliente
	 * @param costingMethod
	 *            Metodo
	 * @return Elemento o nulo
	 */
	public MCostElement getCostingMethods(int AD_Client_ID, String costingMethod) {

		MCostElement retValue = null;

		StringBuilder sql = new StringBuilder(" SELECT * FROM M_CostElement ");
		sql.append(" WHERE AD_Client_ID = ? AND IsActive='Y' ");
		sql.append(" AND CostElementType='M' AND CostingMethod = ?");

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setString(2, costingMethod);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
				retValue = new MCostElement(getCtx(), rs, get_TrxName());

			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "getCostingMethods", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
			} catch (Exception e) {
				log.log(Level.SEVERE, "getCostingMethods", e);
				pstmt = null;
			}
		}

		return retValue;

	}

	/**
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID")) {
				adClientId = ((BigDecimal) para[i].getParameter()).intValue();
			} else if (name.equals("DeleteOldImported")) {
				deleteOldImported = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "ImportProductCosting.prepare - Unknown Parameter: " + name);
			}
		}
	}
}