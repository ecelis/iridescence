package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProductCosting;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Importaci&oacute;n de Costos de Productos
 *
 * 	@author 	Gisela Lee
 * 	@version 	$Id: ImportProductCosting.java,v 1.2 2006/10/11 17:12:08 vgarcia Exp $
 */
public class ImportProductCosting extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*
	private int				m_AD_Org_ID = 0;
	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "ImportProductCosting.prepare - Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{		
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		// **** Prepare ****

		// Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_Product_Costing "
					+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), null);
			log.fine("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_Product_Costing "
				+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
						+ " AD_Org_ID = COALESCE (AD_Org_ID, 0),"
						+ " IsActive = COALESCE (IsActive, 'Y'),"
						+ " Created = COALESCE (Created, SysDate),"
						+ " CreatedBy = COALESCE (CreatedBy, 0),"
						+ " Updated = COALESCE (Updated, SysDate),"
						+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
						+ " I_ErrorMsg = NULL,"
						+ " I_IsImported = 'N' "
						+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Reset=" + no);

		// Establecer producto
		sql = new StringBuffer ("UPDATE I_Product_Costing i "
				+ "SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p"
				+ " WHERE TRIM(i.M_Product_Value)=TRIM(p.Value) "
				+ " AND i.AD_Client_ID IN (p.AD_Client_ID)) "
				+ "WHERE M_Product_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Establecer producto=" + no);

		// Establecer error si producto no valido
		sql = new StringBuffer ("UPDATE I_Product_Costing "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Producto no valido, ' "
				+ "WHERE M_Product_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Producto no valido=" + no);

		// Establecer esquema
		sql = new StringBuffer ("UPDATE I_Product_Costing i "
				+ "SET C_AcctSchema_ID=(SELECT C_AcctSchema_ID FROM C_AcctSchema s"
				+ " WHERE TRIM(i.C_AcctSchema_Name)=TRIM(s.Name) "
				+ " AND i.AD_Client_ID IN (s.AD_Client_ID)) "
				+ "WHERE C_AcctSchema_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("Establecer esquema=" + no);

		// Establecer error si Esquema Contable no valido
		sql = new StringBuffer ("UPDATE I_Product_Costing "
				+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Esquema Contable no valido, ' "
				+ "WHERE C_AcctSchema_ID IS NULL"
				+ " AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		log.config("Esquema no valido=" + no);

		int noUpdate = 0;
		int noInsert = 0;
		int noMCostUpdate=0;
		int noMCostInsert=0;
		// Go through Records
		sql = new StringBuffer ("SELECT I_Product_Costing_ID, M_Product_ID, C_AcctSchema_ID, AD_Client_ID, AD_Org_ID  "
				+ "FROM I_Product_Costing "
				+ "WHERE I_IsImported='N'").append(clientCheck);

		Connection conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			/* Noelia: Se actualiza/inserta en la tabla M_Product_Costing*/

			// Insert costo de productos from Import
			PreparedStatement pstmt_insertProductCost = conn.prepareStatement
			("INSERT INTO M_Product_Costing "
					+ " (M_Product_ID,C_AcctSchema_ID, AD_Client_ID, AD_Org_ID,Created, CreatedBy, " 
					+ " Updated, UpdatedBy, CurrentCostPrice, FutureCostPrice, CostStandard, CostAverage, PriceList, PriceActual) "
					+ " SELECT ?, C_AcctSchema_ID, AD_Client_ID, AD_Org_ID, Created, CreatedBy, "
					+ " SysDate, UpdatedBy, CurrentCostPrice, FutureCostPrice, CostStandard, CostAverage, PriceList, PriceActual"
					+ " FROM I_Product_Costing WHERE I_Product_Costing_ID = ?");

			// Update costo estandar de productos from Import
			PreparedStatement pstmt_updateProductCost = conn.prepareStatement
			("UPDATE M_Product_Costing "
					+ " SET (Updated, UpdatedBy, CostStandard) = "
					+ " (SELECT SysDate, UpdatedBy, CostStandard"
					+ " FROM I_Product_Costing WHERE I_Product_Costing_ID = ?)"
					+ " WHERE M_Product_ID = ? AND C_AcctSchema_ID = ?");

			/******************OMAR*************************************/

			// Insert costo de productos from Import
			PreparedStatement pstmt_insertMCost = conn.prepareStatement
			("INSERT INTO M_Cost "
					+ " (M_Product_ID, C_AcctSchema_ID, AD_Client_ID, AD_Org_ID,Created, CreatedBy, " 
					+ " Updated, UpdatedBy, CurrentCostPrice, FutureCostPrice, M_CostType_ID, M_CostElement_ID, M_AttributeSetInstance_ID) "
					+ " SELECT ?, C_AcctSchema_ID, AD_Client_ID, AD_Org_ID, Created, CreatedBy, "
					+ " SysDate, UpdatedBy, CurrentCostPrice, FutureCostPrice, ?, ?, ? "
					+ " FROM I_Product_Costing WHERE I_Product_Costing_ID = ?");

			// Update costo de productos from Import
			PreparedStatement pstmt_updateMCost = conn.prepareStatement
			("UPDATE M_Cost "
					+ " SET (Updated, UpdatedBy, CurrentCostPrice, FutureCostPrice) = "
					+ " (SELECT SysDate, UpdatedBy, CurrentCostPrice, FutureCostPrice"
					+ " FROM I_Product_Costing WHERE I_Product_Costing_ID = ?)"
					+ " WHERE M_Product_ID = ? AND C_AcctSchema_ID = ? AND M_CostType_ID = ? AND M_CostElement_ID = ? " 
					+ " AND M_AttributeSetInstance_ID = ?");

			/******************OMAR*************************************/

			MCostElement costElement = null;
			int AD_ClientAux_ID = 0;

			// Set Imported = Y
			PreparedStatement pstmt_setImported = conn.prepareStatement
			("UPDATE I_Product_Costing SET I_IsImported='Y', "
					+ "Updated=SysDate, Processed='Y' WHERE I_Product_Costing_ID=?");

			while (rs.next()) {

				int I_Product_Costing_ID = rs.getInt("I_Product_Costing_ID");
				int M_Product_ID         = rs.getInt("M_Product_ID");
				int C_AcctSchema_ID      = rs.getInt("C_AcctSchema_ID");
				int AD_Client_ID =  rs.getInt("AD_Client_ID");
				int AD_Org_ID = rs.getInt("AD_Org_ID");

				if(AD_Client_ID != AD_ClientAux_ID) { 
					/* Noelia: Se obtiene el cost elements que tiene el cliente, con elementos de tipo material
					 *  y costingMethod sea de Costo Estandar*/
					costElement = getCostingMethods(AD_Client_ID, MCostElement.COSTINGMETHOD_StandardCosting);
					AD_ClientAux_ID = AD_Client_ID;
				}


				/* Se actualiza/inserta en la tabla M_Product_Costing*/

				MProductCosting mpc = MProductCosting.get(getCtx(), M_Product_ID, C_AcctSchema_ID, get_TrxName());

				try {

					if(mpc != null)	{
						// Para un nuevo registro (vgarcia)
						pstmt_updateProductCost.setInt(1, I_Product_Costing_ID);
						pstmt_updateProductCost.setInt(2, M_Product_ID);
						pstmt_updateProductCost.setInt(3, C_AcctSchema_ID);						
						no = pstmt_updateProductCost.executeUpdate();
						noUpdate++;
						log.finer("Update product costing = " + no);
					} else {
						// Para actualizar un registro (vgarcia)
						pstmt_insertProductCost.setInt(1, M_Product_ID);
						pstmt_insertProductCost.setInt(2, I_Product_Costing_ID);						
						no = pstmt_insertProductCost.executeUpdate();
						log.finer("Insert product costing = " + no);
						noInsert++;				
					}

					/******************Actualizacion de la tabla M_Cost*************************************/

					MAcctSchema esq = MAcctSchema.get(getCtx(), C_AcctSchema_ID);

					if(esq != null && costElement != null) {

						MCost cost = MCost.get(getCtx(), AD_Client_ID, AD_Org_ID, M_Product_ID, esq.getM_CostType_ID(), 
								C_AcctSchema_ID, costElement.getM_CostElement_ID(), 0);

						if(cost != null) {
							pstmt_updateMCost.setInt(1, I_Product_Costing_ID);
							pstmt_updateMCost.setInt(2, M_Product_ID);
							pstmt_updateMCost.setInt(3, C_AcctSchema_ID);
							pstmt_updateMCost.setInt(4, esq.getM_CostType_ID());
							pstmt_updateMCost.setInt(5, costElement.getM_CostElement_ID());
							pstmt_updateMCost.setInt(6, 0);
							no = pstmt_updateMCost.executeUpdate();
							log.finer("Update product Mcosting = " + no);						
							noMCostUpdate++;			
						} else {
							pstmt_insertMCost.setInt(1, M_Product_ID);
							pstmt_insertMCost.setInt(2, esq.getM_CostType_ID());
							pstmt_insertMCost.setInt(3, costElement.getM_CostElement_ID());
							pstmt_insertMCost.setInt(4, 0);
							pstmt_insertMCost.setInt(5, I_Product_Costing_ID);
							no = pstmt_insertMCost.executeUpdate();
							log.finer("Insert product Mcosting = " + no);						
							noMCostInsert++;
						}

					} else {    
						if(esq == null)
							new Throwable("No existe esquema contable para el producto");
						else
							new Throwable("No existe cost element con costeo Estandar para el cliente ID: " +  AD_Client_ID);
					}

					/******************Actualizacion de la tabla M_Cost*************************************/

				} catch (SQLException ex)	{
					conn.rollback();
					log.warning("Update product costing - " + ex.toString());
					sql = new StringBuffer ("UPDATE I_Product_Costing i "
							+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||").append(DB.TO_STRING("Update product costing: " + ex.toString()))
							.append("WHERE M_Product_ID=").append(M_Product_ID)
							.append(" AND C_AcctSchema_ID=").append(C_AcctSchema_ID);
					DB.executeUpdate(sql.toString(), null);
					conn.commit();
					continue;
				}

				// Update I_Product_Costing
				pstmt_setImported.setInt(1, I_Product_Costing_ID);
				no = pstmt_setImported.executeUpdate();
				conn.commit();

			}// while

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.close();
				conn = null;
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException ex){
				ex.printStackTrace();
			}
			
			log.log(Level.SEVERE, "doIt", e);
			throw new Exception ("doIt", e);
			
		} finally {
			if (conn != null)
				conn.close();
			conn = null;
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			rs=null;
			pstmt=null;
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_Product_Costing "
				+ "SET I_IsImported='N', Updated=SysDate "
				+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), null);
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noUpdate), "@M_Product_Costing@: @Updated@");
		addLog (0, null, new BigDecimal (noInsert), "@M_Product_Costing_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noMCostUpdate), "@M_Cost@: @Updated@");
		addLog (0, null, new BigDecimal (noMCostInsert), "@M_Cost@: @Inserted@");

		return "";

	}	//	doIt



	public  MCostElement getCostingMethods (int AD_Client_ID, String costingMethod)	{

		MCostElement retValue = null;

		StringBuilder sql = new StringBuilder(" SELECT * FROM M_CostElement ")
		.append(" WHERE AD_Client_ID = ? AND IsActive='Y' ")
		.append(" AND CostElementType='M' AND CostingMethod = ?");			

		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt (1, AD_Client_ID);
			pstmt.setString(2, costingMethod);
			ResultSet rs = pstmt.executeQuery ();

			while (rs.next ())
				retValue = new MCostElement (getCtx(), rs, get_TrxName());

			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "getCostingMethods", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close ();
				pstmt = null;
			} catch (Exception e)	{
				log.log(Level.SEVERE, "getCostingMethods", e);
				pstmt = null;
			}
		}

		return retValue;

	} // getMaterialCostElement

} // ImportProductCosting