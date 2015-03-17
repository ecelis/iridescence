package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEPaqBaseTax extends X_EXME_PaqBaseTax{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEPaqBaseTax.class);
	
	/**
	 * @param ctx
	 * @param C_CashLine_ID
	 * @param trxName
	 */
	public MEXMEPaqBaseTax(Properties ctx, int EXME_PacBaseTax_ID, String trxName) {
		super(ctx, EXME_PacBaseTax_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPaqBaseTax(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Crea nuevas lineas por cada base gravable
	 * @param ctx
	 * @param EXME_PaqBase_Version_ID
	 * @param totalBruto
	 * @param lines
	 * @param trxName
	 * @return
	 */
	public static MEXMEPaqBaseTax copyFrom(Properties ctx, int EXME_PaqBase_Version_ID,
			BigDecimal totalBruto, MEXMEDescImpView lines, String trxName) {
		
		MEXMEPaqBaseTax pacBaseTax = new MEXMEPaqBaseTax(ctx, 0, trxName);
		pacBaseTax.setC_Tax_ID(lines.getTax_ID());
		pacBaseTax.setEXME_PaqBase_Version_ID(EXME_PaqBase_Version_ID);
		pacBaseTax.setIsTaxIncluded(false);
		pacBaseTax.setProcessed(true);
		pacBaseTax.setTaxAmt(lines.getIva());
		pacBaseTax.setTaxBaseAmt(lines.getBase());
		pacBaseTax.save(trxName);
		
		return pacBaseTax;
		
	}

	/**
	 * Devuelve las bases gravables y el impuesto que aplica
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	
	public static List<MEXMEDescImpView> getTax(Properties ctx, int EXME_PacBase_Version_ID, String trxName) {
		
		List<MEXMEDescImpView> lista = new ArrayList<MEXMEDescImpView>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT C_Tax_ID, NVL(SUM(Linenetamt),0)AS LineNetAmt  ")
		.append(" FROM EXME_PaqBaseDet ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND EXME_PaqBase_Version_ID =  ").append(EXME_PacBase_Version_ID);
		
		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseDet"));
		
		sql.append(" GROUP BY C_Tax_ID ");
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_PacBase_Version_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEDescImpView refValue = new MEXMEDescImpView();
				refValue.setTax_ID(rs.getInt("C_Tax_ID"));
				refValue.setBase(rs.getBigDecimal("LineNetAmt"));
				lista.add(refValue);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}
		
		
		return lista;
		
	}
	
	/**
	 * Devuelve las bases grabables y el impuesto que aplica
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	
	public static void getDelTax(Properties ctx, int EXME_PaqBase_Version_ID, String trxName) {
		
		//int valor = 0;
    	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_PaqBaseTax ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND EXME_PaqBase_Version_ID =  ?");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseTax"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_PaqBase_Version_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEPaqBaseTax paqBaseTax = new MEXMEPaqBaseTax(ctx, rs, null);
				if (!paqBaseTax.delete(true, trxName))
					s_log.log(Level.SEVERE, "error.insulinas.registro.eliminar");
				paqBaseTax = null;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			
		} finally {
			DB.close(rs, pstmt);
		}
		/*sql.append(" DELETE EXME_PaqBaseTax ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND EXME_PaqBase_Version_ID =  ").append(EXME_PacBase_Version_ID);
		
		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseTax"));
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_PacBase_Version_ID);
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			//valor = pstmt.executeUpdate();
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(pstmt);
			pstmt = null;
		}
		return valor;*/
		
	}
    
    
    /**
     * Devuelve una lista con objetos MEXMEPaqBaseTax configurados para una Versin de Paquete.
     * @param ctx
     * @param EXME_CtaPacDet_ID
     * @param EXME_CtaPac_ID
     * @param trxName
     * @return
     */
    
    public static List<MEXMEPaqBaseTax> getTaxByVersion(Properties ctx, int EXME_PaqBase_Version_ID, String trxName) {
        
        List<MEXMEPaqBaseTax> lista = new ArrayList<MEXMEPaqBaseTax>();
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT * FROM EXME_PaqBaseTax WHERE EXME_PaqBase_Version_ID = ")
           .append(EXME_PaqBase_Version_ID)
           .append(" AND isActive = 'Y'");
        
        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_PaqBaseTax"));
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MEXMEPaqBaseTax tax = new MEXMEPaqBaseTax(ctx, rs, trxName);
                lista.add(tax);
            }
           
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getLineas ", e);
        } finally {
        	DB.close(rs, pstmt);
            pstmt = null;
            rs = null;
        }
        
        return lista;
        
    }

	/**
	 * Devuelve el impuesto que aplica
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * @throws Exception 
	 */
	public static MTax getImpuestoAplicado(Properties ctx, 
			int EXME_PacBase_Version_ID, String trxName) 
	throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DISTINCT (EXME_PaqBaseDet.C_Tax_ID) AS tax,  t.Rate ")
		.append(" FROM EXME_PaqBaseDet ")
		.append(" INNER JOIN C_Tax t ON t.C_Tax_ID = EXME_PaqBaseDet.C_Tax_ID AND t.IsActive = 'Y' ")
		.append(" WHERE EXME_PaqBaseDet.IsActive = 'Y' AND EXME_PaqBaseDet.AD_Client_ID=? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_PaqBaseDet"))
		.append(" AND EXME_PaqBaseDet.EXME_PaqBase_Version_ID = ? ")
		.append(" GROUP BY EXME_PaqBaseDet.C_Tax_ID, t.Rate ");
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_PacBase_Version_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MTax rate = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, EXME_PacBase_Version_ID);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			while (rs.next()) {
				if(count==0)
					rate = new MTax(ctx, rs.getInt("tax"), null);
				if(rate!=null && rate.getC_Tax_ID() != rs.getInt("tax"))
				{
					rate = null;
					break;
				}
				++count;
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
			throw new Exception("error.impuesto.noExiste");
		} finally {
			DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
		}
		return rate;
	}
}