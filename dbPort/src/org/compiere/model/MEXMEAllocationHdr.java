package org.compiere.model;

/** @deprecated use MAllocationHdr */
public class MEXMEAllocationHdr {}
//extends MAllocationHdr {

//	/** serialVersionUID */
//	private static final long serialVersionUID = 5968710312999763452L;
	/**	Static Logger				*/
//	private static CLogger		slog = CLogger.getCLogger (MEXMEAllocationHdr.class);


//	/**************************************************************************
//	 * 	Standard Constructor
//	 *	@param ctx context
//	 *	@param C_AllocationHdr_ID id
//	 *	@param trxName transaction
//	 */
//	/** @deprecated use {@link MAllocationHdr} */
//	public MEXMEAllocationHdr (Properties ctx, int C_AllocationHdr_ID, String trxName)
//	{
//		super (ctx, C_AllocationHdr_ID, trxName);
//		if (C_AllocationHdr_ID == 0)
//		{
//		//	setDocumentNo (null);
//			setDateTrx (Env.getCurrentDate());
//			setDateAcct (getDateTrx());
//			setDocAction (DOCACTION_Complete);	// CO
//			setDocStatus (DOCSTATUS_Drafted);	// DR
//		//	setC_Currency_ID (0);
//			setApprovalAmt (Env.ZERO);
//			setIsApproved (false);
//			setIsManual (false);
//			//
//			setPosted (false);
//			setProcessed (false);
//			setProcessing(false);
//		}
//	}	//	MAllocation

//	/** 
//	 * 	Load Constructor
//	 * 	@param ctx context
//	 *	@param rs result set
//	 *	@param trxName transaction
//	 *@deprecated
//	 */
//	public MEXMEAllocationHdr (Properties ctx, ResultSet rs, String trxName)
//	{
//		super(ctx, rs, trxName);
//	}	//	MAllocation

//	/**
//	 * Obtenemos la distribuciones del pago para la factura especificada
//	 * @param ctx El contexto de la aplicacion
//	 * @param C_Invoice_ID El identificador de la factura
//	 * @param trxName El nombre de la transaccion
//	 * @return Un objeto de tipo MEXMEAllocationLine
//	 */
//	public static List<MAllocationHdr> getAllocationHrdList(Properties ctx, int C_Cash_ID, String trxName) {
//		List<MAllocationHdr> lista = new ArrayList<MAllocationHdr>();
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		StringBuilder sql = new StringBuilder();
//		try {
//			//facturas[i].setDocStatus(MEXMEInvoice.DOCSTATUS_Completed);
//	        //facturas[i].setDocAction(MEXMEInvoice.DOCACTION_Close);
//            
//	        
//			sql.append(" SELECT DISTINCT  C_AllocationHdr.* ")
//			.append(" FROM C_AllocationHdr  ")
//			.append(" INNER JOIN C_AllocationLine l ON (C_AllocationHdr.C_AllocationHdr_ID=l.C_AllocationHdr_ID) ") 
//			.append(" INNER JOIN C_Invoice i ON (l.C_Invoice_ID=i.C_Invoice_ID AND i.C_Cash_ID = ? ")
//			.append("  					     AND i.Afecta_Caja = 'Y' AND i.DocAction = 'CL' ")
//			.append("                        AND i.DocStatus <> 'VO' ) ")
//			.append(" WHERE C_AllocationHdr.IsActive = 'Y' ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MAllocationHdr.Table_Name));
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			pstmt.setInt(1,C_Cash_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MAllocationHdr allHdr = new MAllocationHdr(ctx, rs.getInt("C_AllocationHdr_ID"), trxName);
//				lista.add(allHdr);
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}
	
	
//    /**
//     * Permite cambiar el estatus de processed y generated
//     * @param ctx
//     * @param encabezado
//     * @param trxName
//     * @return
//     */
//    public static boolean cambiarEstatus(final Properties ctx, final MCash encabezado, final String trxName){
//    	List<MAllocationHdr> lineas = MEXMEAllocationHdr.getAllocationHrdList(ctx, encabezado.getC_Cash_ID(), trxName);
//        
//        for (MAllocationHdr hdr : lineas ){
//        	hdr.processIt(DocAction.ACTION_Complete);
//			if (hdr.save() && DocAction.STATUS_Completed.equals(hdr.getDocStatus())) {
//				slog.fine("Allocation Completed >> " + hdr.getC_AllocationHdr_ID());
//			} else {
//				slog.severe("Allocation NOT Completed >> " + hdr.getC_AllocationHdr_ID());
//				return false;
//			}
////        	lineas[i].setDocStatus(lineas[i].completeIt());        	
////
////            if (!lineas[i].getDocStatus().equals(DOCSTATUS_Completed) || !lineas[i].save(trxName))
////                return false;
//        }
//    
//        return true;
//    }
    
//    /**
//	 * Obtenemos la distribuciones del pago para la factura especificada
//	 * @param ctx El contexto de la aplicacion
//	 * @param cInvoiceID El identificador de la factura
//	 * @param trxName El nombre de la transaccion
//	 * @return Un objeto de tipo MEXMEAllocationLine
//	 */
//	public static List<MAllocationHdr> getAllocationHrdInv(final Properties ctx, final int cInvoiceID, final boolean isPrePayment, final String trxName) {
//		final List<MAllocationHdr> lista = new ArrayList<MAllocationHdr>();
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final StringBuilder sql = new StringBuilder();
//		try {
//			
//			final String nivelAccess = MEXMELookupInfo.addAccessLevelSQL(ctx, "", "C_AllocationLine", "aLine");
//	        
//			sql.append(" SELECT DISTINCT ( aLine.C_AllocationHdr_ID ) AS C_AllocationHdr_ID ")
//			.append(" FROM C_AllocationLine aLine ") 
//			.append(isPrePayment?" INNER JOIN C_Payment cPay ON aLine.C_Payment_ID = cPay.C_Payment_ID ":"")
//			.append(" WHERE aLine.IsActive = 'Y' ")
//			.append(nivelAccess==null?"":nivelAccess)
//			.append(" AND   aLine.C_Invoice_ID  = ? ")
//			.append(" AND   aLine.C_Payment_ID IS NOT NULL ");
//			if(isPrePayment){
//				sql.append(" AND cPay.IsActive = 'Y' ")
//				.append("    AND cPay.Type='A'       ")
//				.append("    AND cPay.EXME_CtaPac_ID IS NOT NULL ");
//			}
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			pstmt.setInt(1,cInvoiceID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MAllocationHdr(ctx, rs.getInt("C_AllocationHdr_ID"), trxName));
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		return lista;
//	}
	
//	/**
//	 * Obtenemos la distribuciones del pago para la factura especificada
//	 * @param ctx El contexto de la aplicacion
//	 * @param cInvoiceID El identificador de la factura
//	 * @param trxName El nombre de la transaccion
//	 * @return Un objeto de tipo MEXMEAllocationLine
//	 */
//	public static MAllocationHdr[] getAllocationHrdInv(Properties ctx, int cInvoiceID, final String where, String trxName) {
//		List<MAllocationHdr> lista = new ArrayList<MAllocationHdr>();
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		StringBuilder sql = new StringBuilder();
//		try {
//	        
//			sql.append(" SELECT DISTINCT ( C_AllocationLine.C_AllocationHdr_ID ) AS C_AllocationHdr_ID ")
//			.append(" FROM  C_AllocationLine  ") 
//			.append(" WHERE C_AllocationLine.C_Invoice_ID  = ? ")
//			.append(where==null?" AND   C_AllocationLine.C_Payment_ID IS NOT NULL ":where);
//			
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//			pstmt.setInt(1,cInvoiceID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MAllocationHdr(ctx, rs.getInt("C_AllocationHdr_ID"), trxName));
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		MAllocationHdr[] retValue = new MAllocationHdr[lista.size()];
//		lista.toArray(retValue);
//		return retValue;
//	}
//}
