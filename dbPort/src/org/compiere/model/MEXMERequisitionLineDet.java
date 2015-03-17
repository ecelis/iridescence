package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class MEXMERequisitionLineDet extends X_Exme_RequisitionLineDet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Standar constructor
	 * @param ctx context
	 * @param Exme_RequisitionLineDet_ID id
	 * @param trxName transaction
	 */
	public MEXMERequisitionLineDet(final Properties ctx, final int Exme_RequisitionLineDet_ID, final String trxName) {
		super(ctx, Exme_RequisitionLineDet_ID, trxName);
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MEXMERequisitionLineDet(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Busca un producto o un cargo de una requisicion
	 * @param requisitionid
	 * @param productId
	 * @param chargeId
	 * @param categoryId
	 * @return list of requisitionlinedet
	 */
	public static List<MEXMERequisitionLineDet> getLinesFromRequisition(final int requisitionid) {

		final StringBuilder whereClause = new StringBuilder();
		final StringBuilder join = new StringBuilder();
		whereClause.append(" M_Requisition_ID = ? ");
		final Query query = new Query(Env.getCtx(), Table_Name, whereClause.toString(), null)
			.setJoins(join)
			.setParameters(requisitionid);
		return query.list();
	}

	@Override
	protected boolean beforeSave (final boolean newRecord){

		boolean retValue = true;
		// Clase de producto para el post

		if(getName()!=null && retValue){
			retValue = multiplyChargeProdLine();
		}
		return retValue;
	}
	/**
	 * Regresa si existe el nombre del producto en la requisicion
	 * @return
	 */
	public boolean multiplyChargeProdLine(){
		boolean retValue= true;
		final Object param[]=new Object[1];
		if(is_new() && (retValue && param[0]==null)){//2 grupo siempre verdadero (PMD)
			for (final String prodName : getProductsLine()) {
				if(prodName.equalsIgnoreCase(getName())){
					param[0]=getName();
					break;
				}
			}
		}
		if(param[0]!=null){
			log.saveError("Error", Utilerias.getAppMsg(getCtx(), "error.citas.existeMed", param));
			retValue = false;
		}
		return retValue;
	}

	/**
	 * Llena la lista del nombre de productos personalizados de la requisicion
	 */
	public List<String> getProductsLine(){
		final List<String> prodLst = new ArrayList<String>();
		String sql = "SELECT name FROM Exme_RequisitionLineDet WHERE isactive='Y' AND m_requisition_id=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_Requisition_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodLst.add(rs.getString("name"));
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, "getProds", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return prodLst;
	}
}
