package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class MEXMEMergeLexi extends X_EXME_MergeLexi{
	
	private static CLogger slog = CLogger.getCLogger(MEXMEMergeLexi.class);

	
	public MEXMEMergeLexi(Properties ctx, int EXME_MergeLexi_ID, String trxName) {
		super(ctx, EXME_MergeLexi_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEXMEMergeLexi(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static List<MEXMEMergeLexi> getLstMerge(final Properties ctx, final boolean all, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		final List<MEXMEMergeLexi> lst = new ArrayList<MEXMEMergeLexi>();
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select * from  EXME_MergeLexi where isactive = 'Y' and Merged = 'P' ");
			
			if(!all)
				sql.append(" and createdby = ").append(Env.getAD_User_ID(ctx)).append(" ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rSet = pstmt.executeQuery();
	
			while (rSet.next()) {
				lst.add(new MEXMEMergeLexi(ctx, rSet, null));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}

		return lst;
	}
	
	public static Boolean getExistMerge(final Properties ctx, final int mprodID, final int mprodLexiID, final String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		boolean ret = false;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select * from  EXME_MergeLexi where isactive = 'Y' and m_product_id = ? and m_product_lexi_id = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mprodID);
			pstmt.setInt(2, mprodLexiID);

			rSet = pstmt.executeQuery();
				
			if (rSet.next()) {
				ret = true;
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}

		return ret;
	}
	
	public static void getMergeProcess(final Properties ctx, final boolean all) {	
		try {
			for(MEXMEMergeLexi obj : MEXMEMergeLexi.getLstMerge(ctx, all, null)){
				doMerge(ctx, obj);
			}			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			
		}
	}

	
	/**
	 * Actualiza las columnas de M_Product (Mx) con la de Lexi (M_Produc_Lexi)
	 * @param ctx
	 * @param obj
	 */
	private static void doMerge(final Properties ctx, final MEXMEMergeLexi obj) {
		Trx trxMerge = null;
		try {
			MEXMEMProductLexi mpl = new MEXMEMProductLexi(ctx, obj.getM_Product_Lexi_ID(), null);
			MEXMEGenProductLexi gpl = new MEXMEGenProductLexi(ctx, mpl.getEXME_GenProduct_Lexi_ID(), null);

			MProduct mp = new MProduct(ctx, obj.getM_Product_ID(), null);
			MEXMEGenProduct gp = new MEXMEGenProduct(ctx, mp.getEXME_GenProduct_ID(), null);

			gp.setAD_Client_ID(gpl.getAD_Client_ID());
			gp.setAD_Org_ID(gpl.getAD_Org_ID());
			gp.setEXME_DoseForm_ID(gpl.getEXME_DoseForm_ID());
			gp.setEXME_Route_ID(gpl.getEXME_Route_ID());
			// gp.setGeneric_Product_Name(gpl.getGeneric_Product_Name());
			gp.setEXME_GenDrug_ID(gpl.getEXME_GenDrug_ID());
			gp.setEXME_ProductStrength_ID(gpl.getEXME_ProductStrength_ID());
			gp.setGenProduct_ID(gpl.getGenProduct_ID());
			gp.setDrug_ID(gpl.getDrug_ID());

			trxMerge = Trx.get(Trx.createTrxName("ProdLexiMerge"), true);

			if (gp.save(trxMerge.getTrxName())) {
				// mp.setAD_Client_ID(mpl.getAD_Client_ID());
				// mp.setAD_Org_ID(mpl.getAD_Org_ID());
				// mp.setValue(mpl.getValue());
				mp.setEXME_Labeler_ID(mpl.getEXME_Labeler_ID());
				mp.setStrength(mpl.getStrength());
				mp.setStrengthunits(mpl.getStrengthunits());
				mp.setTrade_Name_ID(mpl.getTrade_Name_ID());
				mp.setLabelerID(mpl.getLabelerID());
				mp.setNDC(mpl.getValue());
				mp.setPMID(mpl.getPMID());

				if (mp.save(trxMerge.getTrxName())) {
					obj.setMerged(X_EXME_MergeLexi.MERGED_MergeLexiMerged);
					if (obj.save(trxMerge.getTrxName())) {
						Trx.commit(trxMerge, true);
					} else {
						throw new MedsysException();
					}
				} else {
					throw new MedsysException();
				}
			} else {
				throw new MedsysException();
			}
		} catch (Exception e) {
			Trx.rollback(trxMerge, true);
			slog.log(Level.SEVERE, "get", e);
		} finally {
			Trx.close(trxMerge, true);
		}

	}
	
	public static List<LabelValueBean> getUsrs(final String trxName){
		List<LabelValueBean> ret = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append(" select ad_user_id,  ");
			sql.append(" case when description is null then name ");
			sql.append(" else concat(name, ' / ',description) ");
			sql.append(" end as usrName ");
			sql.append(" from ad_user ");
			sql.append(" where ad_user_id in ( ");
			sql.append(" select createdby ");
			sql.append(" from EXME_MergeLexi  ");
			sql.append(" where isactive = 'Y'  ");
			sql.append(" and merged = 'P' ");
			sql.append(" group by createdby ");
			sql.append(" ) ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rSet = pstmt.executeQuery();
			
			while (rSet.next()) {
				ret.add(new LabelValueBean(rSet.getString("usrName"), String.valueOf(rSet.getInt("ad_user_id"))));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rSet, pstmt);
			rSet = null;
			pstmt = null;
		}
		return ret;
	}
	
}
