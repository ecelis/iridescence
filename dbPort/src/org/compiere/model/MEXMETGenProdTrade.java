package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;

public class MEXMETGenProdTrade extends X_EXME_T_GenProd_Trade{
	
	private static final long serialVersionUID = 1L;
	private static CLogger		slog = CLogger.getCLogger (MEXMETGenProdTrade.class);
	
	public MEXMETGenProdTrade(Properties ctx, int EXME_T_GenProd_Trade_ID,
			String trxName) {
		super(ctx, EXME_T_GenProd_Trade_ID, trxName);
	}

	public MEXMETGenProdTrade(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Crea el generico en la tabla de exme_t_genprod_trade
	 * @param ctx : Contexto
	 * @param genprod : id del generico
	 * @param whID : id del almacen
	 * @param isPreF : true, considerado en el formulario
	 * @param trxName : nombre de transaccion
	 * @return
	 */
	public static boolean genNewPrefGenProdTrade(Properties ctx, int genprod, int whID, boolean isPreF, final boolean pFormulary, String trxName) {
		int genProdTrade = 0;
		boolean exist = false;
		try {
			
			// Existe el producto en la tabla ?
			genProdTrade = existPrefGenProdTrade(ctx, genprod, trxName);
			if(genProdTrade > 0){
				exist = true;
			}else{
				// si no existe se crea POSave_03d4a047-90ae-4b21-a403-1a3ae7c6f767
				final MEXMEGenProduct genProd = new MEXMEGenProduct(ctx, genprod, trxName);
				
				final MEXMETGenProdTrade newObj = new MEXMETGenProdTrade(ctx, 0, trxName);
				newObj.setEXME_GenProduct_ID(genprod);
				
				// es preferido (solo DRUGS y que esten en replenish)
				newObj.setIsPrefer(isPreF);// Es parte del charge Master
				newObj.setIsFormulary(pFormulary);// Es parte del charge Master y del formulario
				// Nombre genproduct
				newObj.setGeneric_Product_Name(genProd
						.getGeneric_Product_Name());

				// Nombre trade
				final String tName = getTRADENAME(ctx,
						genProd.getGenProduct_ID(), whID, trxName);
				if (tName != null && tName.length() > 0) {
					newObj.setTrade_Name(tName);
				}
				exist = newObj.save(trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return exist;
	}
	
	public static boolean genNewNotPrefGenProdTrade(Properties ctx, int genprod, String trxName) {
		int genProdTrade = 0;
		boolean exist = false;
		try {
			genProdTrade = existNotPrefGenProdTrade(ctx, genprod, trxName);
			final MEXMEGenProduct genProd = new MEXMEGenProduct(ctx, genprod, trxName); // Se coloca el nombre de la transaccion para importacion de productos. Jesus Cantu 5 Enero 2012
			final MEXMETGenProdTrade newObj = new MEXMETGenProdTrade(ctx, genProdTrade, trxName); // Se coloca el nombre de la transaccion para importacion de productos. Jesus Cantu 5 Enero 2012
			final String tName = getTRADENAME(ctx, genProd.getGenProduct_ID(),0, trxName);
			
			if(genProdTrade > 0){
				if(tName != null && tName.length() > 0 && !newObj.getTrade_Name().equals(tName)){
					newObj.setTrade_Name(tName);
					newObj.setGeneric_Product_Name(genProd.getGeneric_Product_Name());
					exist = newObj.save(trxName);
				}else{
					exist = true;
				}
			}else{
				newObj.setEXME_GenProduct_ID(genprod);
				newObj.setIsPrefer(false);
				newObj.setIsFormulary(false);
				if(tName != null && tName.length() > 0){
					newObj.setTrade_Name(tName);
				}
				newObj.setGeneric_Product_Name(genProd.getGeneric_Product_Name());
				exist = newObj.save(trxName);
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return exist;
	}
	
	/**
	 * Elimina de la tabla exme_t_genprod_trade de manera logica
	 * @param ctx
	 * @param genprod
	 * @param mprodID
	 * @param isPreF : si solo no estar� en el formulario
	 * @param isActive : ya no esta activo
	 * @return
	 */
	public static boolean deletePrefGenProdTrade(Properties ctx, int genprod, int mprodID, boolean isActive, 
			boolean isPreF, final boolean isFormulary, final String trxName) {
		int genProdTrade = 0;
		boolean success = false;
		try {
			
			// Existe el genproduct como preferido ?
			genProdTrade = existPrefGenProdTrade(ctx, genprod, trxName);
			if (genProdTrade > 0) {
				
				// Validacion para que no se repita la informacion a nivel genproduct
				if (!existOtherGenProd(ctx, genprod, mprodID, trxName)) {
					
					final MEXMETGenProdTrade obj = new MEXMETGenProdTrade(ctx, genProdTrade, trxName);
					obj.setIsActive(isActive);
					obj.setIsPrefer(isPreF);
					obj.setIsFormulary(isFormulary);
					success = obj.save(trxName);
				} else {
					success = true;
				}
			} else {
				success = true;
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return success;
	}
	
	public static boolean deleteNotPrefGenProdTrade(Properties ctx, int genprod, final String trxName) {
		int genProdTrade = 0;
		boolean success = false;
		try {
			genProdTrade = existNotPrefGenProdTrade(ctx, genprod, trxName);
			if (genProdTrade > 0) {
				final MEXMETGenProdTrade obj = new MEXMETGenProdTrade(ctx, genProdTrade, trxName);
				obj.setIsActive(false);
				success = obj.save(trxName);
			} else {
				success = true;
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return success;
	}
	
	/**
	 * Existe el genproduct como preferido
	 * @param ctx
	 * @param genprod
	 * @return
	 * @throws Exception
	 */
	public static int existPrefGenProdTrade(Properties ctx, int genprod, String trxName) throws Exception {
	     
		int ret = 0;
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			   
		 sql.append("	select exme_t_genprod_trade_id ")
		 	.append("	from exme_t_genprod_trade")
		 	.append("	where exme_genproduct_id = ? ")
		 	.append("	and IsPrefer    = 'Y' ")
		 	.append("	and IsFormulary = 'Y' ")
		 	.append("	and IsActive    = 'Y' ")
		 	.append("	and AD_Org_ID   = ?   ")
		 	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "  ", MEXMETGenProdTrade.Table_Name));
		  
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), trxName);
	         pstmt.setInt(1, genprod);
	         pstmt.setInt(2, Env.getAD_Org_ID(ctx));
	         	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {		         	
	         	ret = rs.getInt(1);		         	 
	         }		        
		 } catch (Exception e) {
			 slog.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
		 } finally {
			DB.close(rs, pstmt);
		 }
		 return ret;
	 }
	
	public static int existNotPrefGenProdTrade(Properties ctx, int genprod, String trxName) throws Exception {
	     
		int ret = 0;
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			   
		 sql.append("	select exme_t_genprod_trade_id ")
		 	.append("	from exme_t_genprod_trade")
		 	.append("	where exme_genproduct_id = ? ")
		 	.append("	and IsPrefer    = 'N'")
		 	.append("	and IsFormulary = 'N' ")
		 	.append("	and isactive    = 'Y'")
		 	.append("	and AD_Org_ID   = 0 ")
		 	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "  ", MEXMETGenProdTrade.Table_Name));
		  
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), trxName);
	         pstmt.setInt(1, genprod);
	         	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {		         	
	         	ret = rs.getInt(1);		         	 
	         }		        
		 } catch (Exception e) {
			 slog.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
		 } finally {
			 DB.close(rs, pstmt);
		 }
		 return ret;
	 }
	
	/**
	 * Ejecuta la funcion para obtener EXME_TRADENAMES
	 * @param ctx
	 * @param genprod
	 * @param whID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static String getTRADENAME(Properties ctx, int genprod, int whID, String trxName) throws Exception {
	     
		String ret = null;
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			   
		 sql.append("	select EXME_TRADENAMES(?, ?) ")
		 	.append("	from DUAL ");
		 	
		  
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), trxName);
	         pstmt.setInt(1, genprod);
	         pstmt.setInt(2, whID);
	         	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {		         	
	         	ret = rs.getString(1);		         	 
	         }		        
		 } catch (Exception e) {
			 slog.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
		 } finally {
			 DB.close(rs, pstmt);
		 }
		 return ret;
	 }
	
	/**
	 * Verifica exista otro genproduct
	 * @param ctx
	 * @param genprod
	 * @param m_prodID
	 * @return
	 * @throws Exception
	 */
	public static boolean existOtherGenProd(Properties ctx, int genprod, int m_prodID, final String trxName) throws Exception {
	     
		boolean ret = false;
		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			   
		      sql.append("	select * from ( ")
				 .append("	select gp.exme_genproduct_id as exme_genproduct_id, p.m_product_id as m_product_id, tn.exme_t_genprod_trade_id as exme_t_genprod_trade_id ")
				 .append("	from exme_genproduct gp ")
				 .append("	inner join m_product p on p.exme_genproduct_id = gp.exme_genproduct_id and p.isactive = 'Y' ")
				 .append("	inner join exme_t_genprod_trade tn on tn.exme_genproduct_id = gp.exme_genproduct_id ")
				 .append("	where gp.exme_genproduct_id = ? ")
				 .append("	) ");
		      
		      //Ticket 04387 — Mantenimiento de Producto - no permite inactivar
		      //Query mal formado para postgresql, faltaba alias. Jesus Cantu el 29 Oct 2012.
		      if (DB.isPostgreSQL()) {
		    	  sql.append(" as genProd ");
		      }
		       
		      sql.append(" where m_product_id != ? ");
		 	
		  
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
	
	     try {
	         pstmt = DB.prepareStatement(sql.toString(), trxName);
	         pstmt.setInt(1, genprod);
	         pstmt.setInt(2, m_prodID);
	         	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {		         	
	         	ret = true;		         	 
	         }		        
		 } catch (Exception e) {
			 slog.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
		 } finally {
			 DB.close(rs, pstmt);
		 }
		 return ret;
	 }
	
//	public static boolean genTGenProdTrade(Properties ctx, MProduct product, String trxName) {
//		MEXMETGenProdTrade genProdTrade = null;
//		boolean exist = false;
//		try {
//			// 
//			final MEXMEGenProduct genProd = new MEXMEGenProduct(ctx, product.getEXME_GenProduct_ID(), null);
//			MEXMETGenProdTrade newObj = null;
//			
//			
//			// Existe el producto en la tabla ?
//			genProdTrade = existPrefGenProdTrade(ctx, genProd.getEXME_GenProduct_ID(), null);
//			if(genProdTrade !=null && genProdTrade.getEXME_T_GenProd_Trade_ID()>0){
//				newObj = genProdTrade;
//			}else{
//				newObj = new MEXMETGenProdTrade(ctx, 0, null);
//			}
//
//			//
//			newObj.setIsActive(product.isService());
//			
//			//
//			newObj.setEXME_GenProduct_ID(product.getEXME_GenProduct_ID());
//
//			// es preferido (solo DRUGS y que esten en replenish)
//			newObj.setIsPrefer(product.getProductOrg().getAD_Org_ID()>0);
//
//			// Nombre genproduct
//			newObj.setGeneric_Product_Name(genProd.getGeneric_Product_Name());
//
//			// Nombre trade
//			final String tName = getTRADENAME(ctx, genProd.getGenProduct_ID(), 
//					product.getProductOrg().getAD_Org_ID(), trxName);
//			if (tName != null && tName.length() > 0) {
//				newObj.setTrade_Name(tName);
//			}
//			
//			exist = newObj.save();
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage(), e);
//		}
//		return exist;
//	}

	
//	/**
//	 * Existe el genproduct como preferido
//	 * @param ctx
//	 * @param genprod
//	 * @return
//	 * @throws Exception
//	 */
//	public static MEXMETGenProdTrade existPrefGenProdTrade(Properties ctx, int genprod, String trxName)
//	throws Exception {
//	     
//		MEXMETGenProdTrade ret = null;
//		 StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			   
//		 sql.append("	select * ")
//		 	.append("	from exme_t_genprod_trade")
//		 	.append("	where exme_genproduct_id = ? ")
//		 	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, "  ", MEXMETGenProdTrade.Table_Name));
//		  
//		 PreparedStatement pstmt = null;
//		 ResultSet rs = null;
//	
//	     try {
//	         pstmt = DB.prepareStatement(sql.toString(), null);
//	         pstmt.setInt(1, genprod);
//	        // pstmt.setInt(2, Env.getAD_Org_ID(ctx));
//	         	         
//	         rs = pstmt.executeQuery();
//	         
//	         if (rs.next()) {		         	
//	         	ret = new MEXMETGenProdTrade(ctx, rs, null);
//	         }		        
//		 } catch (Exception e) {
//			 slog.log(Level.SEVERE, "getObservacionCuest - sql = " + sql, e);
//		 } finally {
//			DB.close(rs, pstmt);
//		 }
//		 return ret;
//	 }
	
	
	public static int getGenProductId(final int exmeTGenProdId){
		final String sql = "SELECT EXME_GenProduct_ID FROM EXME_T_GenProd_Trade WHERE EXME_T_GenProd_Trade_ID=?";
		return DB.getSQLValue(null, sql, exmeTGenProdId);
	}
	
	public static MEXMETGenProdTrade get(final Properties ctx, final int exmeGenProdId, final Boolean isPrefer, String trxName, final boolean formulary){
		
		MEXMETGenProdTrade retValue =null;
		
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM EXME_T_GenProd_Trade ");
		sql.append("WHERE EXME_GenProduct_ID=? AND isActive='Y' ");
		if (isPrefer != null) {
			sql.append(" AND isPrefer=? ");
		}
		if(formulary){
			sql.append(" AND IsFormulary='Y' ");
		}
		sql.append(" AND AD_Client_ID=? AND AD_Org_ID=? ");
		
		// FIXME: revisar si requiere AccessLevel
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int indx = 0;
			pstmt.setInt(++indx, exmeGenProdId);
			if (isPrefer != null) {
				pstmt.setString(++indx, DB.TO_STRING(isPrefer));
			}
			if(formulary || (isPrefer!=null&&isPrefer)) {
				pstmt.setInt(++indx, Env.getAD_Client_ID(ctx));
				pstmt.setInt(++indx, Env.getAD_Org_ID(ctx));
			} else {
				pstmt.setInt(++indx, 0);
				pstmt.setInt(++indx, 0);
			}
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				retValue = new MEXMETGenProdTrade(ctx, rs, trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		QuickSearchTables.rebuildAll(Table_Name, getCtx());
		
		return true;

	}
	
	
}
