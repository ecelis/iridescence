/*
 * Created on 06-abr-2005
 *
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * @author hassan reyes
 * 
 * Modelo de relacion Extension - Pagos (Anticipos).
 *
 */
public class MCtaPacPag extends X_EXME_CtaPacPag {


	/** serialVersionUID */
	private static final long serialVersionUID = -4483641505106963517L;
	/**	Static Logger				*/
	private static CLogger		slog = CLogger.getCLogger (MCtaPacPag.class);
	/** Indica si la instancia esta seleccionada. */
	@SuppressWarnings("unused")
	private boolean selected = false;
	/** Variables miembro que indica el pago */
	private MPayment mPayment = null;
	/** indica la extension */
	private MEXMECtaPacExt mExtension = null;
	/** permite almacenar el monto disponible si es que el pago tiene mas de una factura asignada */
	private BigDecimal available = Env.ZERO;
	
	/** Constructor
	 * @param ctx
	 * @param EXME_CtaPacPag_ID
	 * @param trxName
	 */
	public MCtaPacPag( final Properties ctx,  final int exmeCtaPacPagID, final String trxName) {
		super(ctx, exmeCtaPacPagID, trxName);
	}

	/** Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MCtaPacPag(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos un areglo de Pagos asociados a una extension. 
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static MCtaPacPag[] get(final Properties ctx, final int exmeCtaPacExtID, final String trxName){

		final ArrayList<MCtaPacPag> list = new ArrayList<MCtaPacPag>();

		String sql = "SELECT * FROM EXME_CtaPacPag WHERE EXME_CtaPacExt_ID = ? " +
				" AND C_Payment_ID > 0 AND IsActive = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, exmeCtaPacExtID);
			rSet = pstmt.executeQuery();
			while (rSet.next())
			{
				MCtaPacPag ctaPacPag = new MCtaPacPag(ctx, rSet, trxName);
				list.add(ctaPacPag);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rSet, pstmt);
		}
		MCtaPacPag[] ctaPacPags = new MCtaPacPag[list.size()];
		list.toArray(ctaPacPags);

		return ctaPacPags;

	}

	/**
	 * Obtenemos todos aquellos Anticipos (C_Payment) que fueron echos a la cuenta paciente
	 * y que estan relacionados con la extension.
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECtaPacPag> getPayments(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName){

		List<MEXMECtaPacPag> list = new Query(ctx, MCtaPacPag.Table_Name, " EXME_CtaPacExt_ID = ? ", trxName)
		.setParameters(EXME_CtaPacExt_ID).list();

//		StringBuilder sql = new StringBuilder();
//		.append(" SELECT *, paymentAvailable(C_Payment_ID) AS payAvailable ")
//		.append(" FROM C_Payment               ")
//		.append(" WHERE  C_Payment_ID IN       ")
//		.append("        ( SELECT C_Payment_ID ")
//		.append("          FROM EXME_CtaPacPag ")
//		.append("          WHERE EXME_CtaPacExt_ID = ? ")
//		.append("          AND IsActive = 'Y'  ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacPag.Table_Name))
//		.append("        )                     ")
//		.append(" AND IsActive = 'Y'           ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Payment.Table_Name))
//		.append(" ORDER BY DateTrx ASC         ");
		
		return list;
	}

	/**
	 * Obrtenemos el pago (anticipo) relacionado a la extencion.
	 * @return
	 */
	public MPayment getPayment(){
		if(mPayment == null || mPayment.getC_Payment_ID() == 0){
			mPayment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		mPayment.getPayAmt();
		return mPayment;
	}

	/**
	 * 
	 * @return
	 */
	public MEXMECtaPacExt getExtension(){
		if(mExtension == null || mExtension.getEXME_CtaPacExt_ID() == 0){
			mExtension = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
		}
		return mExtension;
	}


	/**ASAEL
	 * Devuelve los diferentes numeros de recibo (sin repetirlos) asociados a una cuenta paciente
	 * 
	 * @param ctaPacID		ID de la cuenta paciente
	 * @param asLvb			true para enviar la lista con valores de LabelValueBean. 
	 * @return Lista con los diferentes numeros de recibo
	 * 					List<LabelValueBean>  - asLvb = true
	 * 					List<String>  -  asLvb = false
	 */
	public static List<Object> getDistinctReciboNo(Properties ctx, int ctaPacID, boolean asLvb){

		List<Object> recibosNo = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT DISTINCT ReciboNo FROM C_CashLine WHERE EXME_CtaPac_ID = ")
		.append(ctaPacID)
		.append(" AND C_CashLine.IsActive = 'Y' ");
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CashLine"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			if (asLvb) {
				while (rs.next()) {
					LabelValueBean lvb = new LabelValueBean(rs.getString(1), rs.getString(1));
					recibosNo.add(lvb);
				}
			}

			else {
				while (rs.next()) {
					String reciboNo = rs.getString(1);
					recibosNo.add(reciboNo);
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}
		return recibosNo;   
	}

	/**
	 * Obtenemos el registro correspondiente a la devolucion 
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static MCtaPacPag getByInvoice(Properties ctx,int C_Invoice_ID, String trxName){

		MCtaPacPag ctaPacPag = null;

		StringBuilder sql = new StringBuilder ();

		sql.append("SELECT EXME_CtaPacPag.* FROM exme_ctapacpag ")
		.append(" INNER JOIN c_payment p on (p.c_payment_id = EXME_CtaPacPag.c_payment_id ) ")
		.append(" INNER JOIN c_invoice i on (i.c_invoice_id = p.c_invoice_id) ")
		.append(" INNER JOIN c_cashline cl on (cl.c_payment_id = p.c_payment_id) ")
		.append(" INNER JOIN exme_formapago f on (cl.exme_formapago_id = f.exme_formapago_id) ")
		.append(" WHERE p.c_invoice_id = ? AND f.esdevol = 'Y' AND EXME_CtaPacPag.IsActive = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				ctaPacPag = new MCtaPacPag(ctx, rs, trxName);
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
			
		}finally {
			DB.close(rs);
		}
		return ctaPacPag;

	}

	/**
	 * Obtenemos todos aquellos Anticipos (C_Payment) que fueron echos a la cuenta paciente
	 * y que estan relacionados con la extension.
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static MPayment[] getPaymentsByInvoice(Properties ctx,int EXME_CtaPacExt_ID, int C_Invoice_ID, String trxName){

		ArrayList<MPayment> list = new ArrayList<MPayment>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT * FROM C_Payment WHERE C_Payment_ID IN ")
		.append(" (SELECT C_Payment_ID FROM EXME_CtaPacPag WHERE EXME_CtaPacExt_ID = ").append(EXME_CtaPacExt_ID);
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag"));
		sql.append(" AND IsActive = 'Y' AND C_Invoice_ID = ").append(C_Invoice_ID).append(" ) ")
		.append("AND IsActive = 'Y'");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Payment"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MPayment pay = new MPayment(ctx, rs, trxName);
				list.add(pay);
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
			
		}finally {
			DB.close(rs);
		}

		MPayment[] pays = new MPayment[list.size()];
		list.toArray(pays);

		return pays;

	}


	/**
	 * Obtenemos los pagos(anticipos) relacionados a al extencion.
	 * 
	 * @param whereClause
	 * @return
	 */
	public static MCtaPacPag[] getAnticipos(Properties ctx, 
			String whereClause, int EXME_CtaPacExt_ID, String trxName ) {
		ArrayList<MCtaPacPag> list = new ArrayList<MCtaPacPag>();

		String sql = " SELECT * FROM EXME_CtaPacPag WHERE EXME_CtaPacExt_ID = ? "
				+ "AND IsActive = 'Y' ";
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag");
		if (whereClause != null) {
			sql += whereClause;
		}

		sql += " ORDER BY C_Payment_ID";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCtaPacPag(ctx, rs, trxName));
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}
		MCtaPacPag[] ctaPacPags = new MCtaPacPag[list.size()];
		list.toArray(ctaPacPags);

		return ctaPacPags;

	}

	/**
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param reciboNo
	 * @param extension
	 * @param trxName
	 * @return
	 */
	public static List<MPayment> getRecibos(Properties ctx, int ctaPac, String reciboNo, int extension, String trxName){
		return MPayment.getAdvancePayments(ctaPac, ctx);
	}


	/**
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param reciboNo
	 * @param extension
	 * @param trxName
	 * @return
	 */
	public static List<LabelValueBean> getNumRecibos(Properties ctx, int ctaPac, String trxName){

		ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  ")
		.append(" p.recibono, e.EXME_CtaPacExt_ID ")
		.append(" FROM EXME_CtaPacPag ")
		.append(" INNER JOIN C_Payment p ON (p.C_Payment_ID = EXME_CtaPacPag.C_Payment_ID AND p.IsActive = 'Y'  ) ") 
		.append(" INNER JOIN EXME_CtaPacExt e ON (e.EXME_CtaPacExt_ID = EXME_CtaPacPag.EXME_CtaPacExt_ID AND e.IsActive = 'Y' ) ") 
		.append(" INNER JOIN EXME_CtaPac cpa ON (cpa.EXME_CtaPac_ID = e.EXME_CtaPac_ID AND cpa.IsActive = 'Y' )  ")
		.append(" WHERE EXME_CtaPacPag.IsActive = 'Y'   ")
		.append(" AND cpa.EXME_CtaPac_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag"));
		sql.append(" GROUP BY p.ReciboNo, e.EXME_CtaPacExt_ID ORDER BY p.ReciboNo, e.EXME_CtaPacExt_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPac);

			rs = pstmt.executeQuery();
			list.add(new LabelValueBean("", ""));
			while (rs.next())
			{
				LabelValueBean anti = new LabelValueBean(rs.getString("ReciboNo"), rs.getString("ReciboNo"));
				list.add(anti);
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}

		return list;

	}


	/**
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param reciboNo
	 * @param extension
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacPag> getLineasRecibo(Properties ctx, int ctaPac, String reciboNo, int extension, String trxName){

		ArrayList<MCtaPacPag> list = new ArrayList<MCtaPacPag>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT EXME_CtaPacPag.* ")
		.append(" FROM EXME_CtaPacPag ")
		.append(" INNER JOIN C_Payment p ON (p.C_Payment_ID = EXME_CtaPacPag.C_Payment_ID AND p.IsActive = 'Y'  ) ") 
		.append(" INNER JOIN EXME_CtaPacExt e ON (e.EXME_CtaPacExt_ID = EXME_CtaPacPag.EXME_CtaPacExt_ID AND e.IsActive = 'Y' ) ") 
		.append(" INNER JOIN EXME_CtaPac cpa ON (cpa.EXME_CtaPac_ID = e.EXME_CtaPac_ID AND cpa.IsActive = 'Y' )  ")
		.append(" WHERE EXME_CtaPacPag.IsActive = 'Y'   ")
		.append(" AND cpa.EXME_CtaPac_ID = ? ");

		if(reciboNo!=null)
			sql.append(" AND UPPER(p.ReciboNo) like UPPER('%").append(reciboNo).append("%') ");

		if(extension>0)
			sql.append(" AND e.EXME_CtaPacExt_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag"));


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPac);
			if(extension>0)
				pstmt.setInt(2, extension);
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				MCtaPacPag anti = new MCtaPacPag(ctx, rs, trxName);
				list.add(anti);
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}

		return list;

	}

	/**
	 * 
	 * @param ctx
	 * @param C_Payment_ID
	 * @param trxName
	 * @return
	 */
	public static MCtaPacPag getMCtaPacPag(Properties ctx,int C_Payment_ID, String trxName){

		MCtaPacPag pago = new MCtaPacPag(ctx,0,trxName);

		String sql = "SELECT * FROM EXME_CtaPacPag WHERE C_Payment_ID = ? ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Payment_ID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pago = new MCtaPacPag(ctx, rs, trxName);
			}			
		} catch (Exception e) {
			//sql = null;
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}		
		return pago;		
	}


	/**
	 * Generamos la relacion del pago con la extension de la cuenta paciente
	 * @param ctx
	 * @param cPaymentID
	 * @param extensionID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static MCtaPacPag generarCtaPacPag(Properties ctx, int cPaymentID, int extensionID, String trxName) throws Exception {
		MCtaPacPag pago = MCtaPacPag.getMCtaPacPag(ctx, cPaymentID, extensionID, trxName);
		if(pago!=null && pago.getEXME_CtaPacPag_ID()>0){
			if(!pago.isActive()){
				pago.setIsActive(true);
				if (!pago.save(trxName)) {
					throw new Exception("error.caja.noSaveLine");
				}
			}
			return pago;
		}

		MCtaPacPag ctaPacPag = new MCtaPacPag(ctx, 0, trxName);
		ctaPacPag.setC_Payment_ID(cPaymentID);
		ctaPacPag.setEXME_CtaPacExt_ID(extensionID);
		if (!ctaPacPag.save(trxName)) {
			throw new Exception("error.caja.noSaveLine");
		}

		return ctaPacPag;
	}

	/**
	 * 
	 * @param ctx
	 * @param C_Payment_ID
	 * @param extension
	 * @param trxName
	 * @return
	 */
	public static MCtaPacPag getMCtaPacPag(Properties ctx,int C_Payment_ID, int extension, String trxName){

		MCtaPacPag pago = new MCtaPacPag(ctx,0,trxName);

		String sql = "SELECT * FROM EXME_CtaPacPag WHERE C_Payment_ID=? and EXME_ctaPAcExt_ID = ? and isActive = 'Y' ";

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CtaPacPag");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Payment_ID);
			pstmt.setInt(2, extension);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pago = new MCtaPacPag(ctx, rs, trxName);
			}			
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}		
		return pago;		
	}

	/**
	 * Busca el EXME_CtaPacPag relacionado al payment y extension,
	 * 
	 * @param payment
	 * @param extension
	 * @param createNewActivate TRUE: si está inactivo, lo activa, si no existe,
	 *            genera la relacion del pago con la extension de la cuenta paciente.
	 *            FALSE: valida solo registros Activos.
	 * @return
	 */
	public static MCtaPacPag getFromPayment(MPayment payment, int extension, boolean createNewActivate) {
		MCtaPacPag pago = new Query(payment.getCtx(), Table_Name, " C_Payment_ID=? AND EXME_CtaPacExt_ID=? ", payment.get_TrxName())//
			.setParameters(payment.getC_Payment_ID(), extension)//
			.setOnlyActiveRecords(!createNewActivate)// ??
			.addAccessLevelSQL(true)//
			.first();

		if (createNewActivate) {
			if (pago == null) {
				pago = new MCtaPacPag(payment.getCtx(), 0, payment.get_TrxName());
				pago.setC_Payment_ID(payment.getC_Payment_ID());
				pago.setEXME_CtaPacExt_ID(extension);

			} else if (!pago.isActive()) {
				pago.setIsActive(true); // ?? siempre es 'Y'
			}
			if (!pago.save()) {
				throw new MedsysException();
			}
		}
		return pago;
	}

	/**
	 * Obtenemos los pagos(anticipos) relacionados a al extension.
	 * 
	 * @param whereClause
	 * @return
	 */
	public static List<MCtaPacPag> getAnticiposPorPaciente(final Properties ctx, 
			final String whereClause, final String trxName ) {
		final List<MCtaPacPag> list = new ArrayList<MCtaPacPag>();

		final StringBuilder sql = new StringBuilder()
		.append(" SELECT * ")
		.append(" FROM EXME_CtaPacPag   ")
		.append(" WHERE IsActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacPag"))
		.append(" AND EXME_CtaPacExt_ID IN (  ")
		.append("     SELECT EXME_CTAPACEXT_ID FROM EXME_CTAPACEXT WHERE EXME_CTAPAC_ID IN ( ")
		.append(whereClause).append(")       ) ")
		.append(" ORDER BY C_Payment_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MCtaPacPag ctaPacPag = new MCtaPacPag(ctx, rs,
						trxName);
				list.add(ctaPacPag);
			}
		} catch (Exception e) {    		
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs);
		}
		return list;
	}
	
	
	protected boolean beforeSave(boolean newRecord) {
		// Check Storage
		if (newRecord && getC_Payment_ID()>0){
			setDisponible(getPayment().getPaymentAvailable());
		}
		return true;
	}
	
	
	/**
	 * Obtenemos todos aquellos Anticipos (C_Payment) que fueron echos a la cuenta paciente
	 * y que estan relacionados con la extension.
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static MCtaPacPag[] getCtaPacPagPayments(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName){

		ArrayList<MCtaPacPag> list = new ArrayList<MCtaPacPag>();

		StringBuilder sql = new StringBuilder()
		.append(" SELECT cpp.*, paymentAvailable(cpp.C_Payment_ID) AS payAvailable ")
		.append(" FROM EXME_CtaPacPag  cpp              ")
		.append(" INNER JOIN C_Payment   p ON  p.C_Payment_ID = cpp.C_Payment_ID ")
		.append("                          AND p.IsActive = 'Y'                  ")
		.append(" WHERE cpp.IsActive = 'Y'              ")
		.append(" AND   cpp.EXME_CtaPacExt_ID = ?       ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacPag.Table_Name, "cpp"))
		.append(" ORDER BY p.DateTrx ASC         ");
		

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			rSet = pstmt.executeQuery();
			while (rSet.next())
			{
				MCtaPacPag pay = new MCtaPacPag(ctx, rSet, trxName);
				pay.setAvailable(rSet.getBigDecimal("payAvailable"));
				list.add(pay);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rSet, pstmt);
		}

		MCtaPacPag[] pays = new MCtaPacPag[list.size()];
		list.toArray(pays);

		return pays;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal bigDecimal) {
		available = bigDecimal;
	}

	/**
	 * Nombres de extensiones donde esta el pago
	 * @param ctx
	 * @param cPaymentID
	 * @param trxName
	 * @return
	 */
	public static String getLineaDePago(final Properties ctx, final int cPaymentID,
			final int cCtaPacId, final String trxName) {

    	if (ctx == null) {
    		return null;
    	}
    	StringBuilder extensiones = new StringBuilder();
    	StringBuilder sql = new StringBuilder()
    	.append(" SELECT cpe.ExtensionNo, cpr.ExtensionNo AS extInv, ia.DocumentNo, COALESCE(ia.GrandTotal,0) as total ")
    	// anticipos
    	.append(" FROM EXME_CtaPacPag cpp  ")
    	// pagos
    	.append(" INNER JOIN C_Payment        p ON cpp.C_Payment_ID = p.C_Payment_ID AND p.IsActive = 'Y' ")   
    	// Extension de factura
    	.append(" LEFT  JOIN EXME_CtaPacExt cpr ON cpp.EXME_CtaPacExt_ID =  cpr.EXME_CtaPacExt_ID         ")
    	// factura de la extension
    	.append(" LEFT  JOIN C_Invoice       ia ON cpr.C_Invoice_ID = ia.C_Invoice_ID                     ")
    	.append("                              AND ia.C_Invoice_id IN ( SELECT C_Invoice_ID ")
    	.append("                            				FROM  EXME_CtaPacExt            ")
    	.append("                           				WHERE EXME_CtaPac_ID = ?        ")
    	.append("                            				AND   C_Invoice_ID IS NOT NULL )")
    	
    	// Extension de pago
    	.append(" LEFT  JOIN EXME_CtaPacExt cpe ON cpp.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID ")   
    	//
    	.append(" WHERE cpp.IsActive = 'Y' ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacPag.Table_Name, "cpp"))
    	.append(" AND   p.C_Payment_ID = ? ") ;

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, cCtaPacId);
    		pstmt.setInt(2, cPaymentID);
    		rs = pstmt.executeQuery();

    		while (rs.next()){ 
    			if(rs.getString("extInv")!=null && !rs.getString("extInv").isEmpty()){
    				extensiones.append(Utilerias.getLabel("caja.extension")).append(": ").append(rs.getString("extInv")).append(" ");
    			}
    			if(rs.getString("DocumentNo")!=null && !rs.getString("DocumentNo").isEmpty()){
    				extensiones.append(Utilerias.getLabel("factura"       )).append(": ").append(rs.getString("DocumentNo")).append(" ");
    			}
    		}
    	} catch (Exception e) {    		
    		slog.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}

		return extensiones.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Obtenemos todos aquellos Anticipos (C_Payment) que fueron echos a la cuenta paciente
	 * y que estan relacionados con la extension descontando las devoluciones
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static List<MPayment> getPaymentsExtension(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName){
		List<MPayment> list = new ArrayList<MPayment>();
		final StringBuilder sql = new StringBuilder()
		.append("  WITH devolucion AS ( ")
		.append(" 		SELECT EXME_CtaPac_ID, coalesce(SUM(paymentAvailable(C_Payment_ID)),0) AS pagoNegativo ")
		.append(" 		FROM  C_Payment       ")        
		.append(" 		WHERE IsActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Payment.Table_Name))
		.append(" 		AND   PayAmt < 0        ") 
		.append(" 		AND   C_Payment_ID IN      ")   
		.append(" 			( SELECT C_Payment_ID  ")
		.append(" 			  FROM EXME_CtaPacPag  ")
		.append(" 			  WHERE EXME_CtaPacExt_ID = ? ")
		.append(" 			  AND IsActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacPag.Table_Name))
		.append(" 			)   ")
		.append(" 		GROUP BY EXME_CtaPac_ID ")
		.append(" 	) ")

		.append(" 	SELECT paymentAvailable(C_Payment_ID) AS payAvailable ")
		.append(" 	, COALESCE(devolucion.pagoNegativo,0) AS pagoNegativo ")
		.append(" 	, paymentAvailable(C_Payment_ID) + COALESCE(devolucion.pagoNegativo,0) AS Saldo ")
		.append(" 	, * ")
		.append(" 	FROM  C_Payment  pag ")
		.append(" 	LEFT  JOIN devolucion on devolucion.EXME_CtaPac_ID = pag.EXME_CtaPac_ID     ")
		.append(" 	WHERE IsActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_Payment.Table_Name, "pag"))
		.append(" 	AND   paymentAvailable(C_Payment_ID) > 0     ")    
		.append(" 	AND   C_Payment_ID IN         ")
		.append(" 		( SELECT C_Payment_ID  ")
		.append(" 		  FROM EXME_CtaPacPag  ")
		.append(" 		  WHERE EXME_CtaPacExt_ID = ?  ")
		.append(" 		  AND IsActive = 'Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPacPag.Table_Name))
		.append(" 		)                 ")          
		.append(" 	ORDER BY Saldo DESC  ");
		
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPacExt_ID);
			pstmt.setInt(2, EXME_CtaPacExt_ID);
			rSet = pstmt.executeQuery();
			BigDecimal sumPayAmt = Env.ZERO;
			
			while (rSet.next()){
				// 550 - 350 = 200
				// 500 -350 = 150 // 450 - 350 = 100 //  400-350=50
				final BigDecimal saldo = rSet.getBigDecimal("Saldo");
				if(saldo!=null){
					
					if(saldo.signum()>=0 ){
						
						// Cuando no hay devolucion
						if(saldo.compareTo(rSet.getBigDecimal("payAvailable"))==0){ // 
							final MPayment   pay   = new MPayment(ctx, rSet, trxName);
							pay.setAvailable(rSet.getBigDecimal("payAvailable"));
							list.add(pay);
						
						} else if(sumPayAmt.compareTo(Env.ZERO)==0){ 
							BigDecimal mntDevol = rSet.getBigDecimal("payAvailable").subtract(saldo);
							sumPayAmt = sumPayAmt.add(mntDevol);
							
							final MPayment   pay   = new MPayment(ctx, rSet, trxName);
							pay.setAvailable(saldo);
							list.add(pay);
							
						} else if(sumPayAmt.compareTo(rSet.getBigDecimal("pagoNegativo").abs())>=0){ 
							final MPayment   pay   = new MPayment(ctx, rSet, trxName);
							pay.setAvailable(rSet.getBigDecimal("payAvailable"));
							list.add(pay);
						}
						
												
					} else {//-210
						if(sumPayAmt.compareTo(rSet.getBigDecimal("pagoNegativo").abs())>0){
							final MPayment   pay   = new MPayment(ctx, rSet, trxName);
							pay.setAvailable(rSet.getBigDecimal("payAvailable"));
							list.add(pay);

						} else {
							sumPayAmt = sumPayAmt.add(rSet.getBigDecimal("payAvailable"));// 80 + 70
							if(sumPayAmt.compareTo(rSet.getBigDecimal("pagoNegativo").abs())>0){ // 150 > 100

								final BigDecimal oldSumPayAmt = sumPayAmt.add(rSet.getBigDecimal("pagoNegativo"));// 50 = 150 - 100

								final MPayment   pay   = new MPayment(ctx, rSet, trxName); 
								pay.setAvailable(oldSumPayAmt);
								list.add(pay);
							} else {
								// Aun no se cubre la devolución
							}
						}
					}// fin_si:saldo_por_linea
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		}finally {
			DB.close(rSet, pstmt);
		}
		return list;
	}
	
	
}