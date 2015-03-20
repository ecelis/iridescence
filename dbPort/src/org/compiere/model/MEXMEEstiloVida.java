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
/**
 * Clase para catalogo de estatus de fumador
 * @author Lizeth
 *
 */
public class MEXMEEstiloVida extends X_EXME_EstiloVida {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623115325517750583L;
	
	private static CLogger log = CLogger.getCLogger(MEXMEEstiloVida.class);

	public MEXMEEstiloVida(Properties ctx, int EXME_EstiloVida_ID, String trxName) {
		super(ctx, EXME_EstiloVida_ID, trxName);
	}
	
	public MEXMEEstiloVida(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param docNo
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getEstilos(Properties ctx)

	throws Exception {
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LabelValueBean> lstEstatus = new ArrayList<LabelValueBean>();
		
		try {

			
			sql.append(" SELECT * FROM EXME_ESTILOVIDA WHERE ISACTIVE = 'Y' ");
			pstmt = DB.prepareStatement(sql.toString(), null);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MEXMEEstiloVida est = new MEXMEEstiloVida(ctx, rs, null);
				lstEstatus.add(new LabelValueBean(est.getName(), String.valueOf(est.getEXME_EstiloVida_ID())));
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEESTILOVIDA.getEstilos - sql = " + sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lstEstatus;
		
	}
	
	public static List<LabelValueBean> getEstilos(Properties ctx, String tipoEstilo)

	throws Exception {
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LabelValueBean> lstEstatus = new ArrayList<LabelValueBean>();
		
		try {

			
			sql.append(" SELECT * FROM EXME_ESTILOVIDA WHERE ISACTIVE = 'Y' ");
			if(tipoEstilo != null && tipoEstilo.length() > 0)
				sql.append(" and TIPOESTILO = ").append("'").append(tipoEstilo).append("'");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MEXMEEstiloVida est = new MEXMEEstiloVida(ctx, rs, null);
				lstEstatus.add(new LabelValueBean(est.getName(), String.valueOf(est.getEXME_EstiloVida_ID())));
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEESTILOVIDA.getEstilos - sql = " + sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lstEstatus;
		
	}

}
