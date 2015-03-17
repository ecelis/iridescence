/*

 *  Created on 07-mar-2005

 *

 */

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCorteCaja.MCashLineDiff;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  <b>Fecha:</b> 07/Marzo/2005<p>
 *  <b>Modificado: </b> $Author: vgarcia $<p>
 *  <b>En :</b> $Date: 2006/09/01 19:15:37 $<p>
 *@author     hassan reyes
 *@created    17 de marzo de 2005
 *@version    $Revision: 1.3 $
 */
public class MCorteCajaDet extends X_EXME_CorteCajaDet {
	private static final long serialVersionUID = 1L;

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MCorteCajaDet.class);

	/**
	 *  Key para el contexto del CorteCajaDet.
	 */
    public static String EXME_CorteCajaDet_ID = "EXME_CorteCajaDet_ID";

	/**
	 *@param  ctx
	 *@param  ID
	 *@param  trxName
	 */
	public MCorteCajaDet(Properties ctx, int ID, String trxName) {
		super(ctx, ID, trxName);
	}

	/**
	 *@param  ctx
	 *@param  rs
	 *@param  trxName
	 */
    public MCorteCajaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 *  Obtenemos la informacion del Corte de Caja
     *@param  ctx
	 *@param  trxName
	 *@param  exme_CorteCaja_ID  Description of the Parameter
	 *@return
	 *@throws  Exception
	 *@deprecated use {@link #getLstLineas(Properties, int, String)}
	 */
	public static MCorteCajaDet[] getLineas(Properties ctx, int exme_CorteCaja_ID, String trxName) throws Exception {

		List<MCorteCajaDet> lista = getLstLineas(ctx, exme_CorteCaja_ID, trxName);
		MCorteCajaDet[] listCorte = new MCorteCajaDet[lista.size()];
		lista.toArray(listCorte);
		return listCorte;
	}

	/**
	 * Obtiene las lineas del corte de caja.
	 * @param ctx
	 * @param exme_CorteCaja_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MCorteCajaDet> getLstLineas(Properties ctx, int exme_CorteCaja_ID, String trxName){
		return new Query(ctx, Table_Name, "EXME_CorteCaja_ID =?", trxName)
		.setParameters(exme_CorteCaja_ID)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy("line").list();
	}
	
	
	/**
	 * Obtiene las lineas del corte de caja.
	 * @param ctx
	 * @param exme_CorteCaja_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static List<MCorteCajaDet> getDetail(Properties ctx, int cashId, String trxName){
		return new Query(ctx, Table_Name, " cc.C_Cash_ID=? ", trxName)
		.setJoins(new StringBuilder(" INNER JOIN EXME_CorteCaja cc ON cc.EXME_CorteCaja_ID=EXME_CorteCajaDet.EXME_CorteCaja_ID "))
		.setParameters(cashId)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" EXME_CorteCajaDet.line").list();
	}
	
	
//	/**
//	 *  Retorna todas las formas de pago (de la tabla c_cashline)que se efectuaron 
//	 *  con un mismo encabezado
//	 *@param  ctx            Description of the Parameter
//	 *@param  c_Cash_ID      Description of the Parameter
//	 *@param  trxName        Description of the Parameter
//	 *@exception  Exception  Description of the Exception
//	 *@Param                 ctx
//	 *@Param                 exme_CorteCaja_ID
//	 *@Return                true si no hay diferencias , false si existen diferencias
//	 *@deprecated
//	 */
//    public static MFormaPago[] getLineasFormasPago(Properties ctx, int c_Cash_ID, String trxName) throws Exception {
//    	List<MFormaPago> lista = getListFormasPago(ctx, c_Cash_ID, trxName);
//		MFormaPago[] formas = new MFormaPago[lista.size()];
//		lista.toArray(formas);
//		return formas;
//	}
    
	/**
	 * Retorna todas las formas de pago (de la tabla c_cashline)que se efectuaron
	 * con un mismo encabezado
	 * 
	 * @param cash Diario de caja
	 */
	private static Query getListFormasPagoQuery(MCash cash) {
		StringBuilder sql = new StringBuilder();
		sql.append(" EXME_FormaPago_ID IN ");
		sql.append(" ( SELECT DISTINCT( CASE WHEN fp.EsDevol='Y' THEN FP.REF_FormaPago_ID ");
		sql.append(" ELSE FP.EXME_FormaPago_ID END ) as EXME_FormaPago_ID ");
		sql.append(" FROM C_CashLine ");
		sql.append(" INNER JOIN EXME_FormaPago FP ON FP.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID ");
		sql.append(" WHERE C_CashLine.IsActive='Y' ");
		sql.append(" AND C_Cash_ID=? ");
		sql.append(" AND CashType IN ('I', 'P', 'X') ");
		sql.append(" AND C_Currency_ID=? ");//Card #1170
		sql.append(" AND (C_CashLine.isCancelled='N' OR C_CashLine.Ref_Cash_id!=?)");//Card #1201 - ASnchez
		sql.append(MEXMELookupInfo.addAccessLevelSQL(cash.getCtx(), " ", MCashLine.Table_Name));
		sql.append(" ) ");
		
		return new Query(cash.getCtx(), MFormaPago.Table_Name, sql.toString(), cash.get_TrxName())//
			.setOnlyActiveRecords(true)//
			.setParameters(cash.getC_Cash_ID(), Env.getC_Currency_ID(cash.getCtx()),cash.getC_Cash_ID());
	}
	
	/**
	 * Retorna todas las formas de pago (de la tabla c_cashline)que se efectuaron
	 * con un mismo encabezado
	 * 
	 * @param cash Diario de caja
	 */
	public static List<MFormaPago> getListFormasPago(MCash cash) {
		final List<MFormaPago> lista ;
		if (cash == null) {
			lista = new ArrayList<MFormaPago>();
		} else {
//			StringBuilder sql = new StringBuilder();
//			sql.append(" EXME_FormaPago_ID IN ");
//			sql.append(" ( SELECT DISTINCT( CASE WHEN fp.EsDevol='Y' THEN FP.REF_FormaPago_ID ");
//			sql.append(" ELSE FP.EXME_FormaPago_ID END ) as EXME_FormaPago_ID ");
//			sql.append(" FROM C_CashLine ");
//			sql.append(" INNER JOIN EXME_FormaPago FP ON FP.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID ");
//			sql.append(" WHERE C_CashLine.IsActive='Y' ");
//			sql.append(" AND C_Cash_ID=? ");
//			sql.append(" AND CashType IN ('I', 'P', 'X') ");
//			sql.append(" AND C_Currency_ID=? ");//Card #1170
//			sql.append(" AND (C_CashLine.isCancelled='N' OR C_CashLine.Ref_Cash_id!=?)");//Card #1201 - ASnchez
//			sql.append(MEXMELookupInfo.addAccessLevelSQL(cash.getCtx(), " ", MCashLine.Table_Name));
//			sql.append(" ) ");
			
			lista = 
//			 new Query(cash.getCtx(), MFormaPago.Table_Name, sql.toString(), cash.get_TrxName())//
//				.setOnlyActiveRecords(true)//
//				.setParameters(cash.getC_Cash_ID(), Env.getC_Currency_ID(cash.getCtx()),cash.getC_Cash_ID())//
				getListFormasPagoQuery(cash)
				.setOrderBy(MFormaPago.COLUMNNAME_PaymentRule)
				.list();
		}
		return lista;
	}
	
	/**
	 * Retorna todas las formas de pago (de la tabla c_cashline)que se efectuaron
	 * con un mismo encabezado
	 * 
	 * @param cash Diario de caja
	 */
	public static List<Integer> getListFormasPagoIds(MCash cash) {
		final List<Integer> lista ;
		if (cash == null) {
			lista = new ArrayList<Integer>();
		} else {
			lista = getListFormasPagoQuery(cash).listIds();
		}
		return lista;
	}

    /**
	 *  Retorna todas las formas de pago (de la tabla c_cashline)que se efectuaron 
	 *  con un mismo encabezado
	 *@param  ctx            Description of the Parameter
	 *@param  c_Cash_ID      Description of the Parameter
	 *@param  trxName        Description of the Parameter
	 *@exception  Exception  Description of the Exception
	 *@Param                 ctx
	 *@Param                 exme_CorteCaja_ID
	 *@Return                true si no hay diferencias , false si existen diferencias
	 */
    public static List<MFormaPago> getListFormasPago(Properties ctx, int c_Cash_ID, boolean includeDevol, String trxName) throws Exception {
		List<MFormaPago> lista = new ArrayList<MFormaPago>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT(FP.*) FROM C_CashLine ");
		sql.append(" INNER JOIN EXME_FormaPago FP ON FP.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID ");
		sql.append(" WHERE FP.IsActive='Y' AND C_CashLine.IsActive='Y' AND C_Cash_ID = ? AND CashType IN ('I', 'P', 'X') ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"));
		if(!includeDevol) {
			sql.append(" AND fp.EsDevol = 'N' ");
		}
		sql.append(" ORDER BY EXME_FormaPago_ID");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, c_Cash_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lista.add(new MFormaPago(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 *  Formas de Pago que no coninciden en la conciliacion de caja
	 *@param  ctx                Description of the Parameter
	 *@param  exme_CorteCaja_ID  Description of the Parameter
	 *@param  trxName            Description of the Parameter
	 *@return                    Description of the Return Value
	 *@exception  Exception      Description of the Exception
	 *@Param                     ctx
	 *@Param                     exme_CorteCaja_ID
	 */
	public static List<MCashLineDiff>  conciliacion(Properties ctx, int exme_CorteCaja_ID, String trxName)  {
		//registro del corte de caja header
		final MCorteCaja corte = new MCorteCaja(ctx, exme_CorteCaja_ID, trxName);
//        MCashLine[] formas = new MCashLine[lista.size()];
//		lista.toArray(formas);
		return corte.getConciliacion();
	}

	/**
	 *  Obtenemos una Lista del detalle de corte de caja correspondiente a un
	 *  header
	 *@param  ctx                contexto
	 *@param  exme_CorteCaja_ID  usuario
	 *@param  trxName            Nombre de la transaccion
	 *@return                    The lstDetalle value
	 *@deprecated use {@link #getLstLineas(Properties, int, String)}
	 */
    public static List<MCorteCajaDet> getLstDetalle(Properties ctx, int exme_CorteCaja_ID, String trxName) {
        List<MCorteCajaDet> lista = new ArrayList<MCorteCajaDet>();
		try {
			//cambiar de arreglo a lista de labelvaluebean
			MCorteCajaDet[] detalle = getLineas(ctx, exme_CorteCaja_ID, trxName);
			for (int i = 0; i < detalle.length; i++) {
				MCorteCajaDet line = detalle[i];
				lista.add(new MCorteCajaDet(ctx, line.getEXME_CorteCajaDet_ID(), trxName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

    /**
	 * Gets the formaPago attribute of the MCorteCajaDet object
	 *@return    The formaPago value
	 */
    public MFormaPago getFormaPago() {
		MFormaPago formaPago = new MFormaPago(getCtx(), getEXME_FormaPago_ID(), get_TrxName());
		return formaPago;
	}

	/**
	 * Gets the id attribute of the MCorteCajaDet object
	 *@return    The id value
	 */
	public int getId() {
		return getEXME_CorteCajaDet_ID();
	}

	/**
	 * Regresa una lista con las formas de pago en cashline
	 *@param  ctx                Description of the Parameter
	 *@param  exme_CorteCaja_ID  Description of the Parameter
	 *@param  trxName            Description of the Parameter
	 *@Param                     ctx contexto
	 *@Param                     c_Cash_ID id del encabezado
	 *@Param                     trxName nombre de la transaccion
	 *@Return                    lista con las formas de pago (MFormaPago)
	 *@deprecated user {@link MCorteCaja#getConciliacion()}
	 */
    public static List<MCashLineDiff> getLstConciliacion(Properties ctx, int exme_CorteCaja_ID, String trxName) {
//		List<MCashLine> lista = new ArrayList<MCashLine>();
//		try {
//			//informacion de las formas de pago
//			MCashLine[] detalle = conciliacion(ctx, exme_CorteCaja_ID, trxName);
//			for (int i = 0; i < detalle.length; i++) {
//				MCashLine line = detalle[i];
//				MCashLine linea2 = new MCashLine(ctx, line.getC_CashLine_ID(), trxName);
//				linea2.setAmount(line.getAmount());
//				linea2.setEXME_FormaPago_ID(line.getEXME_FormaPago_ID());
//				linea2.setC_Cash_ID(line.getC_Cash_ID());
//				lista.add(linea2);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, e.toString(), e);
//		}
		return conciliacion(ctx, exme_CorteCaja_ID, trxName);
	}

//	/**
//	 *  Regresa una lista con las diferencias
//	 *
//	 *@param  ctx        Description of the Parameter
//	 *@param  c_Cash_ID  Description of the Parameter
//	 *@param  trxName    Description of the Parameter
//	 *@Param             ctx contexto
//	 *@Param             c_Cash_ID id del encabezado
//	 *@Param             trxName nombre de la transaccion
//	 *@Return            lista con las formas de pago (MFormaPago)
//	 */
//	public static List<MFormaPago> getLstFormasPagoCash(Properties ctx, int c_Cash_ID, String trxName) {
//		List<MFormaPago> lista = new ArrayList<MFormaPago>();
//		try {
//			//informacion de las formas de pago
//			MFormaPago[] detalle = getLineasFormasPago(ctx, c_Cash_ID, trxName);
//			for (int i = 0; i < detalle.length; i++) {
//				MFormaPago line = detalle[i];
//				lista.add(new MFormaPago(ctx, line.getEXME_FormaPago_ID(), trxName));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lista;
//	}
}