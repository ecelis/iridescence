package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMELot extends MLot{

	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger (MEXMELot.class);
	public final static String DELIMITADOR = "|";

	/**
	 * Parent Constructor
	 * @param ctl
	 * @param M_Product_ID
	 * @param Name
	 */
	public MEXMELot(MLotCtl ctl, int M_Product_ID, String Name) {
		super(ctl, M_Product_ID, Name);
	}

	/**
	 * Standard Constructor
	 * @param ctx
	 * @param M_Lot_ID
	 * @param trxName
	 */
	public MEXMELot(Properties ctx, int M_Lot_ID, String trxName) {
		super(ctx, M_Lot_ID, trxName);
	}

	/**
	 * Load Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMELot(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene el lote segun el producto
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMELot getFromProduct(Properties ctx, int M_Product_ID, String trxName){
		MEXMELot lote = null;
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append(" SELECT * FROM M_Lot WHERE M_Product_ID = ? ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Lot"));

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1,M_Product_ID);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    lote = new MEXMELot(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getFromProduct", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
		}
		return lote;
	}
	
	/**
	 * Obtiene la informacion del lote
	 * @return lote|descripcion|fecha o null si no tiene control
	 */
	public String getLotInformation(){
		StringBuilder infoBuilder = new StringBuilder();
		String info = null;
		try{
			MEXMELotCtl ctl = MEXMELotCtl.getFromID(getCtx(), getM_LotCtl_ID(), get_TrxName());
			if(ctl != null){
				infoBuilder.append(ctl.updateSeq(get_TrxName()));
				infoBuilder.append(DELIMITADOR);
				infoBuilder.append(getDescription());
				infoBuilder.append(DELIMITADOR);
				if(getDateTo() != null)
					infoBuilder.append(Constantes.getSdfFecha().format(getDateTo()));
				info = infoBuilder.toString();
			}
		}catch (Exception e) {
			s_log.log(Level.SEVERE, "getLotInformation", e);
		}
		return info;
	}
	
	/**
	 * Separa la informacion del lote en un arreglo
	 * @return
	 */
	public static String[] getLotInformationArr(String palabra){
		String [] arr = new String[4];
		if(palabra != null){
			int indice = 0;
	        indice = palabra.indexOf(MEXMELot.DELIMITADOR);
	        arr[0] = (palabra.substring(0,palabra.indexOf(MEXMELot.DELIMITADOR)));
	        palabra = palabra.substring(indice + 1, palabra.length());
	        indice = palabra.indexOf(MEXMELot.DELIMITADOR);
	        arr[1] = (palabra.substring(0, indice));
	        palabra = palabra.substring(indice + 1, palabra.length());
	        indice = palabra.indexOf(MEXMELot.DELIMITADOR);
	        arr[2] = (palabra.substring(0, indice));
	        palabra = palabra.substring(indice + 1, palabra.length());
	        if(palabra.length() > 0){
		        arr[3] = palabra;
	        }	
		}else{
			arr = null;
		}
		return arr;
	}
}
