package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;


public class MEXMEPayerClass extends X_EXME_PayerClass{
	
	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger (MEXMEPayerClass.class);
	
	public MEXMEPayerClass(Properties ctx, int EXME_PayerClass_ID,
			String trxName) {
		super(ctx, EXME_PayerClass_ID, trxName);
	}
	public MEXMEPayerClass(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * @deprecated utilizar {@link MEXMEPayerClass#getAll(Properties)}
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getList(Properties ctx){
		final List<LabelValueBean> array = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append("select * from exme_payerclass where isactive = 'Y'");
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new LabelValueBean(rs.getString(MEXMEPayerClass.COLUMNNAME_Name), String.valueOf(rs.getString(MEXMEPayerClass.COLUMNNAME_Value))));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}		
		return array;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {

		if (!success){
			return success;
		}
		
		// Se regenera la vista EXME_Paciente_Cta_V solo para los campos que se muestran.
		if (newRecord 
			|| is_ValueChanged(COLUMNNAME_Value)
			|| is_ValueChanged(COLUMNNAME_Name)){
//  		Si alguien ocupa la vista MEXMEPacienteCtaV y crea indices con subfijo descomentar lineas siguientes		
//			try {
//				QuickSearchTables.deleteIndexes(getCtx(), MEXMEPacienteCtaV.Table_Name, null);
//			} catch (Exception ex) {
//				log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
//			}
			MEXMEPacienteCtaV.updateSearchPClass(p_ctx, getEXME_PayerClass_ID(), get_TrxName());
		}
		
		return success;
				
	}
	
	
	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<ValueNamePair> getAll(Properties ctx){
		
		final List<ValueNamePair> array = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("select * from exme_payerclass where isactive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new ValueNamePair(rs.getString(MEXMEPayerClass.COLUMNNAME_Value), rs.getString(MEXMEPayerClass.COLUMNNAME_Name)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}		
		return array;
	}

}
