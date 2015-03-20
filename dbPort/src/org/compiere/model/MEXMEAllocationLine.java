/*

 * Created on 04-abr-2005

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

 */

package org.compiere.model;


/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @deprecated use {@link MAllocationLine}
 */
public class MEXMEAllocationLine {}
//extends MAllocationLine {
///**
//	 * 
//	 */
//	private static final long serialVersionUID = -1337949379819240680L;
//	/**	Static Logger				*/
//	private static CLogger		sLog = CLogger.getCLogger (MEXMEAllocationLine.class);
//	/**
//	 * 	Standard Constructor
//	 *	@param ctx context
//	 *	@param C_AllocationLine_ID id
//	 *	@param trxName name
//	 *@deprecated use {@link MAllocationLine}
//	 */
//	public MEXMEAllocationLine (Properties ctx, int C_AllocationLine_ID, String trxName)
//	{
//		super (ctx, C_AllocationLine_ID, trxName);
//		if (C_AllocationLine_ID == 0)
//		{
//		//	setC_AllocationHdr_ID (0);
//			setAmount (Env.ZERO);
//			setDiscountAmt (Env.ZERO);
//			setWriteOffAmt (Env.ZERO);
//			setOverUnderAmt(Env.ZERO);
//		}	
//	}	//	MAllocationLine

//	/**
//	 * 	Load Constructor
//	 *	@param ctx ctx
//	 *	@param rs result set
//	 * @deprecated
//	 */
//	public MEXMEAllocationLine (Properties ctx, ResultSet rs, String trxName)
//	{
//		super(ctx, rs, trxName);
//	}	//	MAllocationLine

//	/**
//	 * 	Parent Constructor
//	 *	@param parent parent
//	 *
//	 */
//	public MEXMEAllocationLine (MAllocationHdr parent)
//	{
//		this (parent.getCtx(), 0, parent.get_TrxName());
//		setClientOrg(parent);
//		setC_AllocationHdr_ID(parent.getC_AllocationHdr_ID());
//		//m_parent = parent;
//		setParent(parent);
//		set_TrxName(parent.get_TrxName());
//	}	//	MAllocationLine
//
//	/**
//	 * 	Parent Constructor
//	 *	@param parent parent
//	 *	@param Amount amount
//	 *	@param DiscountAmt optional discount
//	 *	@param WriteOffAmt optional write off
//	 */
//	public MEXMEAllocationLine (MAllocationHdr parent, BigDecimal Amount, 
//		BigDecimal DiscountAmt, BigDecimal WriteOffAmt, BigDecimal OverUnderAmt)
//	{
//		this (parent);
//		setAmount (Amount);
//		setDiscountAmt (DiscountAmt == null ? Env.ZERO : DiscountAmt);
//		setWriteOffAmt (WriteOffAmt == null ? Env.ZERO : WriteOffAmt);
//		setOverUnderAmt (OverUnderAmt == null ? Env.ZERO : OverUnderAmt);
//	}	//	MAllocationLine

//	/**
//	 * 	Crea una nueva linea de la distribucion del pago a partir de otra
//	 * 	@param ctx contexto
//	 *	@param C_AllocationHdr_ID El identificador del encabezado de la distribucion 
//	 *	@param dateDoc La fecha del documento
//	 * 	@param trxName El nombre de la transaccion
//	 *	@return la linea de la distribucion del pago
//	 */
//	public static MEXMEAllocationLine copyFrom (MEXMEAllocationLine from, int C_AllocationHdr_ID, Timestamp dateDoc, String trxName)
//	{
//	    MEXMEAllocationLine to = new MEXMEAllocationLine (from.getCtx(), 0, null);
//		to.set_TrxName(trxName);
//		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
//		to.setC_AllocationHdr_ID(C_AllocationHdr_ID);
////		to.setC_AllocationLine_ID(0);
//		to.setDateTrx(dateDoc);
//
//		return to;
//	}	//	copyFrom

//    /**
//     * Obtenemos la distribucion del pago para el pago y la factura especificada
//     * @param ctx El contexto de la aplicacion
//     * @param C_Invoice_ID El identificador de la factura
//     * @param C_Payment El identificador del pago
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMEAllocationLine
//     */
//	public static MEXMEAllocationLine getMEXMEAllocationLine(Properties ctx, int C_Invoice_ID, int C_Payment_ID, String trxName) {
//        MEXMEAllocationLine retValue = null;
//        
//        PreparedStatement pstmt = null;
//        try {
//            pstmt = DB.prepareStatement(
//                    "SELECT * FROM C_AllocationLine " + 
//                    "WHERE C_Invoice_ID = ? AND " +
//                    "C_Payment_ID = ?", trxName);
//            
//			pstmt.setInt(1,C_Invoice_ID);
//			pstmt.setInt(2,C_Payment_ID);
//			retValue = new MEXMEAllocationLine(ctx, pstmt.executeQuery(), trxName);
//	       
//        	} catch (Exception e) {
//            	e.printStackTrace();//FIXME
//            } finally {
//            	DB.close(pstmt);
//            }
//        return retValue;
//    }
	
//    /**
//     * Obtenemos la distribuciones del pago para la factura especificada
//     * @param ctx El contexto de la aplicacion
//     * @param C_Invoice_ID El identificador de la factura
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMEAllocationLine
//     */
//	public static MAllocationLine[] getOfInvoice(final Properties ctx, final int C_Invoice_ID, final String trxName) {
//		final List<MAllocationLine> lista = new ArrayList<MAllocationLine>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final StringBuilder sql = new StringBuilder();
//		try {
//			sql.append(" SELECT C_AllocationLine.*, nvl(EXME_CtaPacPag.isPay,'Y') as PAID ")
//			.append(" FROM C_AllocationLine ")
//			.append(" INNER JOIN C_CashLine c ON c.C_CashLine_id = C_AllocationLine.c_cashline_id AND cashtype = ?")
//			.append(" LEFT JOIN EXME_CtaPacPag ON (C_AllocationLine.C_Payment_ID = EXME_CtaPacPag.C_Payment_ID AND EXME_CtaPacPag.IsActive = 'Y' ) ")
//			.append(" WHERE C_AllocationLine.C_Invoice_ID = ? ")
//			// excluimos pagos a facturas directos
//			.append(" AND nvl(C_CashLine_ID,0) NOT IN ( SELECT C_CashLine_ID FROM C_CashLine WHERE C_Invoice_ID = ? ) ")
//
//			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_AllocationLine"))
//			.append(" ORDER BY C_AllocationLine.C_AllocationHdr_ID ASC ");  
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			if(WebEnv.DEBUG){
//				sLog.log(Level.SEVERE,"SELECT * FROM C_AllocationLine " + "WHERE C_Invoice_ID = " + C_Invoice_ID);
//			}
//
//			pstmt.setString(1, MPayment.TYPE_AdvancePayment);
//			pstmt.setInt(2,C_Invoice_ID);
//			pstmt.setInt(3,C_Invoice_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final MAllocationLine allLine = new MAllocationLine(ctx, rs, trxName);
//				allLine.setPaymentPaid(rs.getString("PAID").equals("Y")?true:false);
//				lista.add(allLine);
//			}
//
//		} catch (Exception e) {
//			sLog.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		final MAllocationLine[] retValue = new MAllocationLine[lista.size()];
//		lista.toArray(retValue);
//		return retValue;
//	}
    
//    private boolean isPaymentPaid = false;
//    
//    public boolean isPaymentPaid() {
//		return isPaymentPaid;
//	}
//
//	public void setPaymentPaid(boolean isPaymentPaid) {
//		this.isPaymentPaid = isPaymentPaid;
//	}
	
//    /**
//     * Obtenemos la distribuciones del pago para la factura y pago especificado
//     * @param ctx El contexto de la aplicacion
//     * @param C_CashLine_ID El identificador del pago de caja
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMEAllocationLine
//     */
 /*   public static MEXMEAllocationLine[] getOfCashLine(Properties ctx, int C_CashLine_ID, String trxName) {
        List lista = new ArrayList();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement("SELECT * FROM C_AllocationLine WHERE C_CashLine_ID = ?", trxName);
            
            pstmt.setInt(1,C_CashLine_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MEXMEAllocationLine allLine = new MEXMEAllocationLine(ctx, rs, trxName);
			    lista.add(allLine);
			}

        } catch (Exception e) {
        	//
        } finally {
        	DB.close(rs, pstmt);
        }
        
        MEXMEAllocationLine[] retValue = new MEXMEAllocationLine[lista.size()];
    	lista.toArray(retValue);
    	return retValue;
    }*/
    
   

//	/**
//     * Obtenemos la distribucion del pago para el pago y la factura especificada
//     * @param ctx El contexto de la aplicacion
//     * @param C_Invoice_ID El identificador de la factura
//     * @param C_Payment El identificador del pago
//     * @param trxName El nombre de la transaccion
//     * @return Un objeto de tipo MEXMEAllocationLine
//     */
//    public static Integer getFormaPago(Properties ctx, int C_Invoice_ID, int C_Payment_ID, String trxName) {
//        int retValue = 0;
//       
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//        try{
//            
//           sql.append("SELECT cl.exme_formapago_id FROM C_AllocationLine ")
//              .append("INNER JOIN c_payment p on (p.c_payment_id = C_AllocationLine.c_payment_id) ")
//              .append("INNER JOIN c_cashline cl on (cl.c_payment_id = p.c_payment_id) ")
//              .append("INNER JOIN exme_formapago f on (cl.exme_formapago_id = f.exme_formapago_id) ")
//              .append("WHERE C_AllocationLine.c_invoice_id = ? AND C_AllocationLine.c_payment_id = ? ")
//              .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_AllocationLine"));
//    
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, C_Invoice_ID);
//            pstmt.setInt(2, C_Payment_ID);
//            rs = pstmt.executeQuery();
//            
//            if (rs.next()){
//                retValue = rs.getInt(1);
//            }
//            
//        }
//        catch (Exception e) {
//        	//TODO
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//        
//        return retValue;
//    }
	
//	public static MEXMEAllocationLine[] getOfHeader(Properties ctx, int C_AllocationHdr_ID, String trxName) {
//        List<MEXMEAllocationLine> lista = new ArrayList<MEXMEAllocationLine>();
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        StringBuilder sql = new StringBuilder();
//        try {
//        	sql.append(" SELECT * from C_AllocationLine where C_ALLOCATIONHDR_ID = ?")
//        	   .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_AllocationLine"));
//              
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//			
//            pstmt.setInt(1,C_AllocationHdr_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//			    MEXMEAllocationLine allLine = new MEXMEAllocationLine(ctx, rs, trxName);
//			    lista.add(allLine);
//			}
//			
//        } catch (Exception e) {
//        	//TODO
//        } finally {
//        	DB.close(rs, pstmt);
//        }
//        MEXMEAllocationLine[] retValue = new MEXMEAllocationLine[lista.size()];
//    	lista.toArray(retValue);
//    	return retValue;
//    }
//    private MPayment payment = null;
//	public MPayment getPayment() {
//		if (payment == null && getC_Invoice_ID() != 0)
//			payment = new MPayment (getCtx(), getC_Payment_ID(), get_TrxName());
//		return payment;
//	}

//}

