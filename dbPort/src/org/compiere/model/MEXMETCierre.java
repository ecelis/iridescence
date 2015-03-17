package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMETCierre extends X_EXME_T_Cierre{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMETCierre.class);
    
	public MEXMETCierre(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MEXMETCierre(Properties ctx, int EXME_T_Cierre_ID, String trxName) {
		super(ctx, EXME_T_Cierre_ID, trxName);
	}
	
	/**
	 * Metodo para insertar registro en EXME_T_Cierre segun registro en Fact_Acct
	 * @param ctx Contexto
	 * @param fact registro de Fact_Acct para copiar
	 * @param trxName Nombre de la transaccion
	 * @return cierre MEXMETCierre
	 * @author rosy 
	 * */	
	public static boolean copiaDeFactAcct(Properties ctx, MFactAcct fact, String trxName){
		
		if(fact == null){
			return false;
		}
		
		try{
			MEXMETCierre cierre = new MEXMETCierre(ctx, 0, trxName);		
			cierre.setAccount_ID(fact.getAccount_ID());          //
			cierre.setAD_Client_ID(fact.getAD_Client_ID());
			cierre.setAD_Org_ID(fact.getAD_Org_ID());            //
			cierre.setAD_OrgTrx_ID(fact.getAD_OrgTrx_ID());		 //
			cierre.setAmtAcctCr(fact.getAmtAcctCr());
			cierre.setAmtAcctDr(fact.getAmtAcctDr());
			cierre.setAmtSourceCr(fact.getAmtSourceCr());
			cierre.setAmtAcctDr(fact.getAmtSourceDr());
			cierre.setDateAcct(fact.getDateAcct());
			cierre.setDescription(fact.getDescription());
			cierre.setLine_ID(fact.getLine_ID());
			cierre.setM_Product_ID(fact.getM_Product_ID());		   //
			cierre.setQty(fact.getQty());
			cierre.setUser1_ID(fact.getUser1_ID());                //
			cierre.setUser2_ID(fact.getUser2_ID());				   //
			cierre.setUserElement1_ID(fact.getUserElement1_ID());  //
			cierre.setUserElement2_ID(fact.getUserElement2_ID());  //
			
			cierre.setC_AcctSchema_ID(fact.getC_AcctSchema_ID());   //
			cierre.setC_Activity_ID(fact.getC_Activity_ID());       //
			cierre.setC_BPartner_ID(fact.getC_BPartner_ID());		//
			cierre.setC_Campaign_ID(fact.getC_Campaign_ID());       //
			cierre.setC_Currency_ID(fact.getC_Currency_ID());
			cierre.setC_LocFrom_ID(fact.getC_LocFrom_ID());         //
			cierre.setC_LocTo_ID(fact.getC_LocTo_ID());             //
			cierre.setC_Project_ID(fact.getC_Project_ID());         //
			cierre.setC_SalesRegion_ID(fact.getC_SalesRegion_ID()); //
			cierre.setC_SubAcct_ID(fact.getC_SubAcct_ID());			//
			cierre.setC_UOM_ID(fact.getC_UOM_ID());	
			
			cierre.save(trxName);
							
		}catch(Exception e){
			s_log.log(Level.SEVERE, "Error al copiar Fact_Acct en EXME_T_Cierre" + e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Metodo para obtener registro en EXME_T_Cierre segun el indice de la tabla y tomando registros de Fact_Acct
	 * @param ctx Contexto
	 * @param fact registro de Fact_Acct desde el cual se hara la busqueda
	 * @param trxName Nombre de la transaccion
	 * @return cierre MEXMETCierre
	 * @author rosy 
	 * */
	public static MEXMETCierre getSegunIndice(Properties ctx, MFactAcct fact, String trxName){

		MEXMETCierre cierre = null;
		//Se hace la busqueda utilizando el indice de la tabla
		String sql = "SELECT * " +
					 " FROM EXME_T_Cierre " +
					 " WHERE c_acctschema_id = ? " +
					          " AND c_salesregion_id = ? " +
					          " AND c_project_id = ? " +
					          " AND c_campaign_id = ? " +
					          " AND c_activity_id = ? " +
					          " AND user1_id = ? " +
					          " AND user2_id = ? " +
					          " AND userelement1_id = ? " +
					          " AND userelement2_id = ? " +
					          " AND ad_org_id = ? " +
					          " AND account_id = ? " +
					          " AND c_subacct_id = ? " +
					          " AND m_product_id = ? " +
					          " AND c_bpartner_id = ? " +
					          " AND ad_orgtrx_id = ? " +
					          " AND c_locfrom_id = ? " +
					          " AND c_locto_id = ? ";
		
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, fact.getC_AcctSchema_ID());
			pstmt.setInt(2, fact.getC_SalesRegion_ID());
			pstmt.setInt(3, fact.getC_Project_ID());
			pstmt.setInt(4, fact.getC_Campaign_ID());
			pstmt.setInt(5, fact.getC_Activity_ID());
			pstmt.setInt(6, fact.getUser1_ID());
			pstmt.setInt(7, fact.getUser2_ID());
			pstmt.setInt(8, fact.getUserElement1_ID());
			pstmt.setInt(9, fact.getUserElement2_ID());
			pstmt.setInt(10, fact.getAD_Org_ID());
			pstmt.setInt(11, fact.getAccount_ID());
			pstmt.setInt(12, fact.getC_SubAcct_ID());
			pstmt.setInt(13, fact.getM_Product_ID());
			pstmt.setInt(14, fact.getC_BPartner_ID());
			pstmt.setInt(15, fact.getAD_OrgTrx_ID());
			pstmt.setInt(16, fact.getC_LocFrom_ID());
			pstmt.setInt(17, fact.getC_LocTo_ID());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cierre = new MEXMETCierre(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}        
        return cierre;
	}	
	
	
	/**
	 * Obtener una lista de registros de EXME_T_Cierre
	 * @param ctx Contexto
	 * @return list Lista de MEXMETCierre
	 * @author rosy
	 * **/
	public static List<MEXMETCierre> getTodos(Properties ctx){
		ArrayList<MEXMETCierre> list = new ArrayList<MEXMETCierre>();
		String sql = "SELECT * FROM EXME_T_Cierre "
			       + "WHERE isActive= 'Y' ";
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);			
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MEXMETCierre(ctx, rs, null));
			
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		
		return list;
	}
	
	
	/**
	 * Borrado de registros en EXME_T_Cierre
	 * @param ctx Properties
	 * @return String
	 * @author rosy
	 *  
	 * **/
	public static String borraTemporal(Properties ctx){
		
		String sql = "DELETE FROM EXME_T_Cierre";
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);			
			pstmt.executeUpdate();
									
			
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(pstmt);
		}
		
		
		return "";
	}
}
