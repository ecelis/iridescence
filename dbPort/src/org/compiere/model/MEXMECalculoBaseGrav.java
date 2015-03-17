package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class MEXMECalculoBaseGrav {
	
    /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMECalculoBaseGrav.class);
	
//	/**
//	 * Regresa ina lista con objetos tipo MEXMEDescImpView
//	 * que llevan las bases grabables para crear las suficientes lineas
//	 * 
//	 * @param ctx
//	 * @param lineas
//	 * @param configPre
//	 * @param extension
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MEXMEDescImpView> getLineasImpuestoInvoice(Properties ctx, List<MEXMELineasFactView> lineas, 
//			MConfigPre configPre, int extension,
//			String trxName) {
//
//		List<MEXMEDescImpView> listaTax = new ArrayList<MEXMEDescImpView>();
//
//		try {
//
//			// suma por base gravable todas las lineas que se quieren facturar
//			// excepto coaseguro y deducible
//			for (int i = 0; i < lineas.size(); i++) {
//				
//				//Linea de cargo
//				MEXMELineasFactView imp = lineas.get(i);
//				
//				//No se calculan otras lineas que no sean productos cargados
//				if(imp.getExtension() == extension  
//					&& imp.getLineaInvoice()!=null
//					&& imp.getLineaInvoice().getM_Product_ID()!= configPre.getDeducible_ID()
//					&& imp.getLineaInvoice().getM_Product_ID()!= configPre.getCoaseguro_ID()
//					&& imp.getLineaInvoice().getM_Product_ID()!= configPre.getCoaseguroMed_ID()
//					&& imp.getLineaInvoice().getM_Product_ID()!= configPre.getCoPago_ID()
//					&& imp.getLineaInvoice().getM_Product_ID() > 0){
//					
//					MEXMEDescImpView mm = null;
//					boolean nuevo = true;
//					//Barremos las bases grabables para ir sumarizando
//					for (int j = 0; j < listaTax.size(); j++) {
//						MEXMEDescImpView tim = (MEXMEDescImpView)listaTax.get(j);
//						
//						if(imp.getLineaInvoice().getC_Tax_ID() == ((MEXMEDescImpView)listaTax.get(j)).getTax_ID()){
//							((MEXMEDescImpView)listaTax.get(j)).setBase(tim.getBase().add(imp.getLineaInvoice().getLineNetAmt()));
//							nuevo = false;
//							break;
//						}
//					}
//	
//					//La primera vez la lista que se barrio anteriormente esta vacia por lo
//					//que se agregaron las sig lineas para ese caso 
//					if(nuevo){
//						mm = new MEXMEDescImpView();
//						mm.setTax_ID(imp.getLineaInvoice().getC_Tax_ID());
//						mm.setBase(imp.getLineaInvoice().getLineNetAmt());
//						listaTax.add(mm);
//					}
//				/*
//					System.out.println("Prorrateo " + mm.getProrrateo());
//					System.out.println("Prorrateo Iva " + mm.getIvaProrrateo());
//					System.out.println("Base " + mm.getBase());
//					System.out.println("Iva " + mm.getIva());
//					System.out.println("getNewBase " + mm.getNewBase());
//					System.out.println("getNewTotal " + mm.getNewTotal());
//					System.out.println("getNewTotalProrrateo " + mm.getNewTotalProrrateo());
//					System.out.println("getRate " + mm.getRate());*/
//				}										
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return listaTax;
//
//	}
	
	
	/**
	 * Devuelve las bases gravables y el impuesto que aplica
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * @throws SQLException 
	 */

	public static List<MEXMEDescImpView> getLineasImpuestoCtaPacDet(Properties ctx, int EXME_CtaPacExt_ID,
			int coaseguro_ID, int deducible_ID, String trxName) throws SQLException {
		
		List<MEXMEDescImpView> lista = new ArrayList<MEXMEDescImpView>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT EXME_CtaPacDet.C_Tax_ID, NVL(SUM(EXME_CtaPacDet.Linenetamt),0)AS LineNetAmt ")
		.append(" FROM EXME_CtaPacDet WHERE EXME_CtaPacDet.IsActive = 'Y' ");
		
		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacDet"));
		
		//Que los cargos sean de la extension y quitamos el coaseguro y el deducible
		sql.append(" AND EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
		.append(" AND EXME_CtaPacDet.M_Product_ID <> ? ")
		.append(" AND EXME_CtaPacDet.M_Product_ID <> ? ")
		.append(" AND EXME_CtaPacDet.M_Product_ID > 0 ")
		.append(" And Exme_Ctapacdet.Sedevolvio  <> 'Y' "); //No considerar los cargos devueltos. Expert:aaranda 26102010
		
		//Agrego las condiciones para que no se consideren las l�neas de paquetes que son solo para el usuario
		sql.append(" AND EXME_CtaPacDet.EXME_CtaPacDet_ID NOT IN ( SELECT t.EXME_CtaPacDet_ID ") 
		.append(" FROM EXME_CtaPacDet t WHERE t.EXME_PaqBase_Version_ID > 0  ")
		.append(" AND t.Visible = 'Y' AND t.TipoLinea = 'PB' AND t.IsActive = 'Y'  ")
		.append(" AND t.EXME_CtaPacExt_ID = ? ) ");

		//Agrego las condiciones para que no se consideren las l�neas de ajuste
		sql.append(" AND EXME_CtaPacDet.EXME_CtaPacDet_ID NOT IN ( SELECT t.EXME_CtaPacDet_ID ") 
		.append(" FROM EXME_CtaPacDet t WHERE t.TipoLinea = 'LD' ")
		.append(" AND t.IsDescription = 'N' AND t.IsActive = 'Y'  ")
		.append(" AND t.EXME_CtaPacExt_ID = ? ) ");
		
		sql.append(" AND EXME_CtaPacDet.isdescription = 'N' GROUP BY EXME_CtaPacDet.C_Tax_ID ");
		
		s_log.log(Level.FINEST,"SQL> " + sql.toString() + " ID > " + EXME_CtaPacExt_ID);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			pstmt.setInt(2, coaseguro_ID);
			pstmt.setInt(3, deducible_ID);
			pstmt.setInt(4, EXME_CtaPacExt_ID);
			pstmt.setInt(5, EXME_CtaPacExt_ID);
			rs = pstmt.executeQuery();
			
			BigDecimal total = Env.ZERO;
			while (rs.next()) {
				MEXMEDescImpView refValue = new MEXMEDescImpView();
				refValue.setTax_ID(rs.getInt("C_Tax_ID"));
				refValue.setBase(rs.getBigDecimal("LineNetAmt"));
				total = total.add(refValue.getBase());
				lista.add(refValue);
				//refValue =  null;
			}

			totalOrigFact = total;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}


		return lista;
		
	}
	
	public static BigDecimal totalOrigFact = Env.ZERO;

	public static BigDecimal getTotalOrigFact() {
		return totalOrigFact;
	}

	public static void setTotalOrigFact(BigDecimal totalOrigFact) {
		MEXMECalculoBaseGrav.totalOrigFact = totalOrigFact;
	}
	

}
