/*
 * Created on 07-abr-2005
 */
package org.compiere.model;


/**
 * Modelo de Linea de Caja (Captura de Pagos)
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/08/16 22:28:41 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.9 $
 * @deprecated use MCashLine instead will be removed
 */
public class MEXMECashLine { 
//extends MCashLine {
    
//	private static final long serialVersionUID = 1L;

	/** Static Logger               */
//    private static CLogger      log = CLogger.getCLogger (MEXMECashLine.class);
    
//	/**
//	 * 	Standard Constructor
//	 *	@param ctx context
//	 *	@param C_CashLine_ID id
//	 */
//	public MEXMECashLine(Properties ctx, int C_CashLine_ID, String trxName) {
//		super(ctx, C_CashLine_ID, trxName);
//	}	//	MCashLine

//	/**
//	 * 	Load Cosntructor
//	 *	@param ctx context
//	 *	@param rs result set
//	 */
//	public MEXMECashLine(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	} // MCashLine

//	/**
//	 * 	Parent Cosntructor
//	 *	@param cash parent
//	 */
//	public MEXMECashLine(MCash cash) {
//		super(cash.getCtx(), 0, cash.get_TrxName());
//		setClientOrg(cash);
//		setC_Cash_ID(cash.getC_Cash_ID());
//	} //	MCashLine
	
//	/**
//	 * 	Crea una nueva linea de pago a partir de otra
//	 * 	@param ctx contexto
//	 *	@param C_Cash_ID El identificador de la caja abierta 
//	 * 	@param trxName El nombre de la transaccion
//	 *	@return la linea del pago
//	 */
//	public static MEXMECashLine copyFrom(MEXMECashLine from, int C_Cash_ID, String trxName) {
//		final MEXMECashLine to = new MEXMECashLine(from.getCtx(), 0, null);
//		to.set_TrxName(trxName);
//		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
//		to.setC_Cash_ID(C_Cash_ID);
//		to.setProcessed(false);
//		return to;
//	} // copyFrom
	
//    /**
//     * Obtenemos la distribuciones del pago para la factura especificada
//     * @param ctx El contexto de la aplicacion
//     * @param C_Invoice_ID El identificador de la factura
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMECashLine
//     * @deprecated will be removed, use {@link #getOfInvoiceList(Properties, int, String)} instead
//     */
//    public static MEXMECashLine[] getOfInvoice(Properties ctx, int C_Invoice_ID, String trxName) {
//		// sql.append("SELECT * FROM C_CashLine ")
//		// .append("WHERE C_Invoice_ID = ?")
//		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"))
//		// .append("ORDER BY C_CashLine_ID");
//    	List<MEXMECashLine> lista = getOfInvoiceList(ctx, C_Invoice_ID, trxName);
//		MEXMECashLine[] retValue = new MEXMECashLine[lista.size()];
//		lista.toArray(retValue);
//		return retValue;
//    }
    
//    /**
//     * Obtenemos la distribuciones del pago para la factura especificada
//     * @param ctx El contexto de la aplicacion
//     * @param C_Invoice_ID El identificador de la factura
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMECashLine
//     */
//    public static List<MEXMECashLine> getOfInvoiceList(Properties ctx, int C_Invoice_ID, String trxName) {
//		
//    	final StringBuilder join = new StringBuilder();
//		join.append(" LEFT JOIN C_Payment p ON (C_CashLine.c_payment_id=p.c_payment_id AND p.type=?)");
//		join.append(" INNER JOIN C_AllocationLine l ON (l.c_cashline=C_CashLine.c_payment_id");
//		join.append("  OR l.C_payment_id=p.C_payment_ID AND l.C_Invoice_ID=? )");
//
//		return new Query(ctx, Table_Name, "", trxName)//
//			.setOnlyActiveRecords(true)// ?
//			.addAccessLevelSQL(true)//
//			.setParameters(MPayment.TYPE_InvoicePayment, C_Invoice_ID)//
//			.setOrderBy(" C_CashLine_ID ")//
//			.setClass(MEXMECashLine.class)
//			.list();
//
//		// sql.append("SELECT * FROM C_CashLine ")
//		// .append("WHERE C_Invoice_ID = ?")
//		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"))
//		// .append("ORDER BY C_CashLine_ID");
//    }
	
//    /**
//     * Obtenemos la distribuciones del pago para la cuenta paciente especificada
//     * @param ctx El contexto de la aplicacion
//     * @param EXME_CtaPac_ID El identificador de la cuenta paciente
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMECashLine
//     */
//    public static List <MCashLine> getOfCtaPac(Properties ctx, int EXME_CtaPac_ID, String where, String trxName) {
//    	List <MCashLine>lista = new ArrayList<MCashLine>();
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        
//        sql.append("select c.*, coalesce(p.c_invoice_id,0) as facturado, cc.c_charge_id as adjtype_id, cc.type as adjType ")
//           .append("from EXME_CTAPAC cp ")
//           .append("inner join C_CASHLINE c on c.EXME_CTAPAC_ID = cp.EXME_CTAPAC_ID ")
//           .append("inner join C_PAYMENT p on c.C_PAYMENT_ID = p.C_PAYMENT_ID ")
//           .append("LEFT join EXME_CTAPACPAG cpp on cpp.C_PAYMENT_ID = p.C_PAYMENT_ID ")
//           .append("LEFT join EXME_CTAPACEXT e on cpp.EXME_CTAPACEXT_ID = e.EXME_CTAPACEXT_ID ")
//           .append(" left join c_allocationline al on al.c_payment_id = p.c_payment_id ")
//           .append(" left join c_charge cc on cc.c_charge_id = p.c_charge_id ")
//           .append("where cp.EXME_CTAPAC_ID = ? ");  
//        if(where != null)
//        	sql.append(where);
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_CtaPac", "cp"))
//           .append(" ORDER BY p.c_payment_id ");
//        
//        try {
//            pstmt = DB.prepareStatement(sql.toString(),trxName);
//            
//            pstmt.setInt(1,EXME_CtaPac_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MCashLine cashLine = new MCashLine(ctx, rs, trxName);
//			    cashLine.setFacturado(new Boolean (rs.getInt("facturado")>0 ? true : false));
//			    cashLine.setAdjCodeID(rs.getInt("adjtype_id"));
//			    cashLine.setAdjType(rs.getString("adjType"));
//			    lista.add(cashLine);
//			}
//        } catch (Exception e) {
//
//			log.log(Level.SEVERE, "", e);
//			
//		}finally {
//			DB.close(rs, pstmt);
//		}
//       
////        MEXMECashLine[] retValue = new MEXMECashLine[lista.size()];
////    	lista.toArray(retValue);
//    	return lista;
//    }

//    /**
//     * Obtenemos la distribuciones del pago para la cuenta paciente especificada
//     * @param ctx El contexto de la aplicacion
//     * @param EXME_CtaPac_ID El identificador de la cuenta paciente
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMECashLine
//     */
//    public static List<MEXMECashLine> getOfCtaPacCaptPago(Properties ctx, int EXME_CtaPac_ID, String where, String trxName) {
//		return new Query(ctx, Table_Name, " EXME_CTAPAC_ID=? AND C_PAYMENT_ID IS NOT NULL ", trxName)//
//			.setOnlyActiveRecords(true)// ?
//			.setParameters(EXME_CtaPac_ID)//
//			.addAccessLevelSQL(true)//
//			.setOrderBy(" C_CashLine_ID DESC ")//
//			.setClass(MEXMECashLine.class)//
//			.list();
//        
////        .append(" SELECT * FROM C_CashLine WHERE IsActive = 'Y'  ")
////        .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",X_C_CashLine.Table_Name))
////        .append(" AND EXME_CTAPAC_ID = ?  ")
////        .append(" AND C_PAYMENT_ID is not null ")
////        .append(" ORDER BY C_CashLine_ID DESC    ");
//    }

//    /**
//     * Permite cambiar el estatus de processed y generated
//     * @param ctx
//     * @param encabezado
//     * @param trxName
//     * @return
//     */
//    public static boolean cambiarEstatus(Properties ctx, MCash encabezado, String trxName){
//        MEXMECashLine[] lineas = MEXMECashLine.lineasEnCash(ctx, encabezado.getC_Cash_ID(), trxName);
//        
//        for (int i=0; i<lineas.length; i++){
//            lineas[i].setProcessed(true);
//            if (!lineas[i]..get(i)e(trxName))
//                return false;
//        }
//    
//        return true;
//    }
    
//    /**
//     * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de la caja
//     * @param ctx
//     * @param C_CashBook_ID Caja
//     * @param C_Cash_ID         Linea de Apertura de caja
//     * @param trxName
//     * @return
//     */
//	public static MEXMECashLine[] lineasEnCash(Properties ctx, int C_Cash_ID, String trxName) {
//
//		List<MEXMECashLine> lista = new Query(ctx, Table_Name, " C_CashLine.C_Cash_ID=? ", trxName)//
//			.setOnlyActiveRecords(true)//
//			.setParameters(C_Cash_ID)//
//			.addAccessLevelSQL(true)//
//    		.setClass(MEXMECashLine.class)//
//			.list();
//		// sql.append(" SELECT C_CashLine.* FROM C_CashLine WHERE C_CashLine.IsActive = 'Y' ")
//		// .append(" AND C_CashLine.C_Cash_ID = ? ");
//
//		MEXMECashLine[] listFact = new MEXMECashLine[lista.size()];
//		lista.toArray(listFact);
//		return listFact;
//	}
    
    
//    /**
//     * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de la caja
//     * @param ctx
//     * @param C_CashBook_ID Caja
//     * @param C_Cash_ID         Linea de Apertura de caja
//     * @param trxName
//     * @return
//     */
//    public static List<CashHeader> lineasCash(Properties ctx, String clausWhere,
//                    String valorStr, String trxName){
//
//        List<CashHeader> lista = new ArrayList<CashHeader>();
//        
//        if (ctx == null) {
//            return null;
//        }
//      
//        StringBuilder sql = new StringBuilder();
//        
//         sql.append(" SELECT DISTINCT (C_CashLine.Recibono) AS recibo, C_CashLine.EXME_Ctapac_id, ") 
//            .append(" C_CashLine.C_Invoice_id, C_CashLine.C_Cash_ID ") 
//            .append(" , SUM(C_CashLine.Amount) AS monto FROM C_CashLine ")  
//            .append(" INNER JOIN C_Cash c ON (C_CashLine.C_Cash_ID = c.C_Cash_ID) " ) 
//            .append(" INNER JOIN C_CashBook cb ON (c.C_CashBook_ID = cb.C_CashBook_ID) " )
//                     
//              .append(" LEFT  JOIN C_Invoice i ON (i.C_Invoice_ID = C_CashLine.C_Invoice_ID ) " )
//              .append(" LEFT  JOIN EXME_CtaPacExt cte ON (cte.EXME_CtaPacExt_ID = i.EXME_CtaPacExt_ID) " ) 
//              .append(" LEFT  JOIN EXME_CtaPac ct ON (ct.EXME_CtaPac_ID = cte.EXME_CtaPac_ID AND ct.isActive = 'Y' )  " )  //.-Lama
//              .append(" LEFT  JOIN EXME_Paciente p ON (p.EXME_Paciente_ID = ct.EXME_Paciente_ID) " )
//                      
//              .append(" LEFT JOIN EXME_CtaPac cp ON (cp.EXME_CtaPac_ID = C_CashLine.EXME_CtaPac_ID AND cp.isActive = 'Y' ) " ) //.-Lama
//              .append(" LEFT JOIN EXME_Paciente pac ON (pac.EXME_Paciente_ID = cp.EXME_Paciente_ID) " )
//                      
//              .append(" WHERE C_CashLine.IsActive = 'Y' ")
//              .append(" AND C_CashLine.updated >= to_date('"+valorStr+"', 'dd/MM/yyyy') ")
//              .append(" AND i.REF_invoice_id is null  ");
//                      
//            if (clausWhere != null)
//            	sql.append(clausWhere);
//       
//        
//        sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CashLine"));
//        
//        sql.append(" GROUP BY C_CashLine.Recibono, C_CashLine.EXME_Ctapac_id, C_CashLine.C_Invoice_id, C_CashLine.C_Cash_ID "); 
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//       try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            rs = pstmt.executeQuery();
//        
//            while(rs.next()){
//                CashHeader pagos = new CashHeader();
//                MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs.getInt("EXME_CtaPac_ID"), trxName);
//                if(ctaPac!=null)
//                    pagos.setTipoPago("Anticipo");
//                
//                MEXMEInvoice invoice = new MEXMEInvoice(ctx, rs.getInt("C_Invoice_ID"), trxName);
//                if(invoice!=null){
//                    pagos.setPartner(new MEXMEBPartner(ctx, invoice.getC_BPartner_ID(), trxName));
//                    pagos.setTipoPago("Factura");
//                }
//
//    			pagos.setCtaPac(ctaPac);
//    			pagos.setInvoice(invoice);
//    			pagos.setCash(new MCash(ctx, rs.getInt("C_Invoice_ID"), trxName));
//    			pagos.setCashBook(new MEXMECashBook(ctx, pagos.getCash().getC_CashBook_ID(), trxName));
//    			pagos.setFecha(valorStr);
//    			pagos.setAmount(rs.getBigDecimal("monto"));
//    			pagos.setRecibo(rs.getString("recibo"));
//
//    			lista.add(pagos);
//
//    		} 
//    	} catch (Exception e) {
//    		log.log(Level.SEVERE, sql.toString(), e);
//    		
//    	}finally {
//    		DB.close(rs, pstmt);
//    	}
//
//    	return lista;
//
//    }
    
//    /**
//     * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de la caja
//     * @param ctx
//     * @param C_CashBook_ID Caja
//     * @param C_Cash_ID         Linea de Apertura de caja
//     * @param trxName
//     * @return
//     */
//    public static MEXMECashLine[] lineasCashRecib(Properties ctx, String clausWhere,
//    		String valorStr, String trxName){
//        List<MEXMECashLine> lista = new ArrayList<MEXMECashLine>();
//    	if (ctx == null) {
//    		return null;
//    	}
//        StringBuilder sql = new StringBuilder();
//    	sql.append(" SELECT C_CashLine.C_CashLine_ID FROM C_CashLine WHERE C_CashLine.IsActive = 'Y' ") 
//    	   .append(" AND C_CashLine.Recibono = ? "); <-- No se pasa parametro !
//
//    	if (clausWhere != null)
//    		sql.append(clausWhere);
//    	sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CashLine"));
//    	sql.append(" GROUP BY cl.Recibono, cl.EXME_Ctapac_id, cl.C_Invoice_id, cl.C_Cash_ID "); <-- Columnas no incluidas en select !
//
//    	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//        
//    	try {
//    		pstmt = DB.prepareStatement(sql.toString(), trxName);
//    		rs = pstmt.executeQuery();
//
//    		while(rs.next()){
//    			MEXMECashLine pagos = new MEXMECashLine(ctx, rs.getInt("C_CashLine_ID"), trxName);
//    			lista.add(pagos);
//    		} 
//    	} catch (Exception e) {
//     		log.log(Level.SEVERE, sql.toString(), e);
//    		
//    	}finally {
//    		DB.close(rs, pstmt);
//    	}
//    	MEXMECashLine[] listFact = new MEXMECashLine[lista.size()];
//    	lista.toArray(listFact);
//    	return listFact;
//    }
    
//    /***
//     * Devolucion de anticipos de ctapaciente
//     * 
//     * @param ctaPac_ID
//     * @param client_ID
//     * @param trxName
//     * @return
//     * @deprecated will be removed
//     */
//    public static boolean devolucion(Properties ctx, 
//            int ctapac_ID,  int bPartner, 
//            MEXMEOperador operador, int extensionID, 
//            BigDecimal monto, //Object linea, 
//            String trxName){
//        boolean devol=false;
//
//        int currencyID = 0; 
//        try {
//        	if(monto!= null) {
//                
//        		MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(ctx,ctapac_ID, extensionID, trxName);
//        		currencyID = Env.getContextAsInt(ctx, "$C_Currency_ID");
//        		String recibo = MEXMEAreaCaja.createCounterRecibo(ctx, MCashBook.getAreaCajaID(operador.getC_CashBook_ID()), trxName);//el numero de recibo
//                       
//        		MCashLine cashLine = new MCashLine(ctx, 0, trxName); // linea del pago
//                
//        		int cashID = MCash.getCash(ctx, operador.getC_CashBook_ID(), operador.getEXME_Operador_ID(), trxName);//referencia a la caja
//        		if(cashID==0) {
//        			DB.rollback(false, trxName);
//        			devol = false;
//        			return devol;
//        		}
//        		cashLine.setC_Cash_ID(cashID);
//        		cashLine.setLine((1) * 10);//la linea del pago
//        		cashLine.setReciboNo(recibo);
//        		cashLine.setCashType(MCashLine.CASHTYPE_Anticipo);
//        		cashLine.setEXME_CtaPac_ID(ctapac_ID);
//        		cashLine.setIsPrepayment(true);
//                
//        		//generamos el pago en C_Payment
//        		MPayment payment;
//        		try {
//        			payment = new MPayment(ctx, 0, trxName);
////        			payment.setBackoffice(false);
//        			//payment.setDateTrx(now);
//        			//payment.setIsReceipt(true);
//        			payment.setAD_OrgTrx_ID(operador.getAD_OrgTrx_ID());
//        			payment.setC_DocType_ID(MEXMEDocType.getOfName(ctx, "AR Receipt", trxName));
//        			payment.setTrxType(MEXMEPayment.TRXTYPE_Sales);
//                    
//        			MConfigPre configPre = MConfigPre.get(ctx, trxName);
//        			if(configPre == null) {
//        				throw new Exception("error.caja.configPre");
//        			}
//        			payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
//        			payment.setC_BPartner_ID(bPartner);
//        			payment.setTenderType(MPayment.TENDERTYPE_Cash);
//        			payment.setC_Currency_ID(currencyID);
//        			payment.setPayAmt(monto.negate());
//        			anticipo.setTotal(anticipo.getTotal().subtract(monto));
//        			// payment.setDocStatus(DocAction.STATUS_Completed);//estatus de completado
//        			//payment.setDocAction(DocAction.ACTION_Close);//accion de cerrar
//        			//payment.setIsAllocated(false);//esta distribuido = N
//        			payment.setProcessed(false);//procesado = N
//        			//payment.setPosted(true);//posteado = Y
//        			//payment.setDateAcct(now);
//        			//payment.setIsPrepayment(false);//Para que se pueda postear debe ser negativo
//        			payment.setEXME_CtaPac_ID(ctapac_ID);
//        			payment.setReciboNo(recibo);
//
//        			payment.prepareIt();
//        			payment.setDocStatus(payment.completeIt());
//        			if(!payment.save(trxName) || !anticipo.save(trxName)) {
//        				throw new Exception ("error.caja.noSaveLine");
//        			}
//        		} catch (Exception exc) {
//        			DB.rollback(true, trxName);
//        			devol = false;
//        			return devol;
//        		}
//            
//        		//generamos la relacion de pago - cta pag
//        		if(payment != null) {
//        			MCtaPacPag ctaPacPag;
//        			try {
//        				ctaPacPag = new MCtaPacPag(ctx, 0, trxName);
//                        
//        				ctaPacPag.setC_Payment_ID(payment.getC_Payment_ID());
//        				ctaPacPag.setEXME_CtaPacExt_ID(extensionID);
//        				ctaPacPag.setIsPay(false);
//                        
//        				if(!ctaPacPag.save(trxName)) {
//        					throw new Exception ("error.caja.noSaveLine");
//        				}
//        			} catch (Exception exc) {
//        				DB.rollback(false, trxName);
//        				devol = false;
//        				return devol;
//        			}
//        		}
// 
//        		cashLine.setC_Payment_ID(payment.getC_Payment_ID());//la referencia al pago
//                
//        		//actualizamos el socio de negocios a EXME_CtaPacExt
//        		MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, extensionID, trxName);
//        		if(bPartner!=ctaPacExt.getC_BPartner_ID()) {
//        			ctaPacExt.setC_BPartner_ID(bPartner);
//        			//guardamos la factura
//        			if(!ctaPacExt.save(trxName)) {
//        				DB.rollback(false, trxName);
//        				devol = false;
//        				return devol;
//        			}   
//        		}
//        		cashLine.setC_Currency_ID(currencyID);//la moneda del documento 
//        		cashLine.setAmount(monto.negate());//la cantidad aplicada en el pago
//
//
//        		cashLine.setEXME_FormaPago_ID(MFormaPago.getDevol(ctx, trxName));//la forma de pago efectiva
//        		cashLine.setProcessed(false);
//                
//        		if(!cashLine.save(trxName)) {
//        			DB.rollback(false, trxName);
//        			devol = false;
//        			return devol;
//        		}
//        	}else {
//                return devol;
//        	}
//            
//            devol = true;            
//             
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        return devol;
//    }
    
//    /**
//     * Obtenemos la linea cashline a partir de un payment_id  
//     * @param ctx
//     * @param C_CashBook_ID Caja
//     * @param C_Cash_ID         Linea de Apertura de caja
//     * @param trxName
//     * @return
//     */
//    public static MEXMECashLine getOfPayment(Properties ctx, int C_Payment_ID, String trxName){
//
////    	String sql =  " SELECT * FROM c_cashline WHERE  C_Payment_ID= ? " ;
////    	sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_CashLine");
//
//    	return new Query(ctx, Table_Name, " C_CashLine.C_Payment_ID=? ", trxName)//
////		.setOnlyActiveRecords(true)// ??
//		.setParameters(C_Payment_ID)//
//		.addAccessLevelSQL(true)//
//		.setClass(MEXMECashLine.class)//
//		.first();
//    }

//	/**
//	 * Lineas cashline de cita médica
//	 * @param ctx
//	 * @param exmeCitaMedicaId
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMECashLine[] getOfMedApp(Properties ctx, int exmeCitaMedicaId) {
//		List <MEXMECashLine>lista = new ArrayList<MEXMECashLine>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" SELECT C.* FROM EXME_CITAMEDICA CITA ")
//		.append(" INNER JOIN C_CASHLINE C on C.EXME_CITAMEDICA_ID = CITA.EXME_CITAMEDICA_ID ")
//		.append(" INNER JOIN C_PAYMENT P on C.C_PAYMENT_ID = P.C_PAYMENT_ID ")
//		.append(" WHERE CITA.EXME_CITAMEDICA_ID = ? ");  
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",MEXMECitaMedica.Table_Name, "CITA"))
//		.append(" ORDER BY C.LINE ");
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//
//			pstmt.setInt(1,exmeCitaMedicaId);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MEXMECashLine cashLine = new MEXMECashLine(ctx, rs, null);
//				lista.add(cashLine);
//			}
//		} catch (Exception e) {
//
//			log.log(Level.SEVERE, e.getMessage(), e);
//
//		}finally {
//			DB.close(rs, pstmt);
//		}
//
//		MEXMECashLine[] retValue = new MEXMECashLine[lista.size()];
//		lista.toArray(retValue);
//		return retValue;
//	}
	
//	/**
//	 * Linea de caja para un pago
//	 * @param ctx
//	 * @param cPaymentID
//	 * @param trxName
//	 * @return
//	 */
//	public static MEXMECashLine getLineaDePago(final Properties ctx,
//			final int cPaymentID, final String trxName) {
//		return MEXMECashLine.getOfPayment(ctx,  cPaymentID, trxName);
//	}
}