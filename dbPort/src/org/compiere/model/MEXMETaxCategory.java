package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * 
 * @author Expert
 *
 */
public class MEXMETaxCategory extends MTaxCategory {

	/** serialVersionUID */
	private static final long serialVersionUID = 4265667396727847061L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMETaxCategory.class);
	
	/**
	 * MEXMETaxCategory
	 * @param ctx
	 * @param C_TaxCategory_ID
	 * @param trxName
	 */
	public MEXMETaxCategory (Properties ctx, int C_TaxCategory_ID, String trxName)
	{
		super (ctx, C_TaxCategory_ID, trxName);
	}	//	MTaxCategory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs resukt set
	 *	@param trxName trx
	 */
	public MEXMETaxCategory (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MTaxCategory
	
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEDiscountSchema> get(Properties ctx, String trxName) 
	throws Exception{
		
		List<MEXMEDiscountSchema> lista = new ArrayList<MEXMEDiscountSchema>();
		if(ctx == null){
			return null;
		}
		
		MEXMEDiscountSchema retValue = null;
		String sql = " SELECT * FROM C_TaxCategory WHERE IsActive = 'Y' AND AD_Client_ID = ? ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_TaxCategory");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			while(rs.next()){
				retValue = new MEXMEDiscountSchema(ctx, rs, trxName);
				lista.add(retValue);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			sql = null;
		}finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}
	
	/**
	 * getLabelValue
	 * @param ctx
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getLabelValue(Properties ctx, String trxName) 
	throws Exception{
		
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		if(ctx == null){
			return null;
		}
		
		String sql = " SELECT C_TaxCategory_ID, Name FROM C_TaxCategory WHERE IsActive = 'Y' AND AD_Client_ID = ? ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_TaxCategory");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			LabelValueBean blanco = new LabelValueBean("", "0");
			lista.add(blanco);
			
			while(rs.next()){
				LabelValueBean elemento = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("C_TaxCategory_ID")));
				lista.add(elemento);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			sql = null;
		}finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}
	
	/**
	 * Metodo para obtener el listado de cateogiroas de impuesto.
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getList(Properties ctx, boolean blank, String trxName) {
		
		List<KeyNamePair> lista = new ArrayList<KeyNamePair>();
		if(ctx == null){
			return null;
		}
		
		String sql = " SELECT C_TaxCategory_ID, Name FROM C_TaxCategory WHERE IsActive = 'Y' AND AD_Client_ID = ? ";
		
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_TaxCategory");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			
			if(blank){
				KeyNamePair blanco = new KeyNamePair(0, "");
				lista.add(blanco);
			}
			
			while(rs.next()){
				KeyNamePair elemento = new KeyNamePair(rs.getInt("C_TaxCategory_ID"), rs.getString("Name"));
				lista.add(elemento);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			sql = null;
		}finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}
}