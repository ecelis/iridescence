/**
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
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.CtaPacPag;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author tperez
 *
 */
public class MEXMECtaPacPag extends X_EXME_CtaPacPag {
	/** serialVersionUID */
	private static final long serialVersionUID = -165638964528962451L;
	/**	Static Logger				*/
	private static CLogger		slog = CLogger.getCLogger (MEXMECtaPacPag.class);
	/** Variables miembro que indica el pago */
	private MPayment mPayment = null;
	/** indica la extensi&oacuten */
	private MEXMECtaPacExt mExtension = null;
	
	/** Constructor
	 * @param ctx
	 * @param EXME_CtaPacPag_ID
	 * @param trxName
	 */
	public MEXMECtaPacPag( final Properties ctx,  final int exmeCtaPacPagID, final String trxName) {
		super(ctx, exmeCtaPacPagID, trxName);
	}

	/** Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPacPag(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}


	/**
	 * Obtenemos un areglo de Pagos asociados a una extensi&oacuten. 
	 * @param ctx Contexto
	 * @param trxName Nombre de transacci&oacuten
	 * @param EXME_CtaPacExt_ID extensi&oacuten Key
	 * @return
	 */
	public static List <MEXMECtaPacPag> get(final Properties ctx, final String trxName, final int exmeCtaPacExtID){
		return new Query(ctx, Table_Name, " EXME_CtaPacExt_ID = ? AND C_Payment_ID > 0 ", trxName)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY C_Payment_ID ASC ")
		.setParameters(exmeCtaPacExtID)
		.list();
	}
	
	/**
	 * Obtenemos un areglo de Pagos asociados a una extensi&oacuten exeptuando devoluciones. 
	 * @param ctx Contexto
	 * @param trxName Nombre de transacci&oacuten
	 * @param EXME_CtaPacExt_ID extensi&oacuten Key
	 * @return
	 */
	public static List <MEXMECtaPacPag> getAnticiposSinDevolucion(final Properties ctx, final String trxName, final int exmeCtaPacExtID){
		return new Query(ctx, Table_Name, " EXME_CtaPacExt_ID = ? AND C_Payment_ID > 0 AND seDevolvio = 'N' ", trxName)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY C_Payment_ID ASC ")
		.setParameters(exmeCtaPacExtID)
		.list();
	}
	
	public int getExistPaymentFor(int EXME_CtaPacExt_ID){
		String sql = "SELECT EXME_CtaPacPag_ID FROM EXME_CtaPacPag WHERE C_Payment_ID = ? AND EXME_CtaPacExt_ID = ? ";
		return DB.getSQLValue(null, sql, getC_Payment_ID(), EXME_CtaPacExt_ID);
	}
	
	/**
	 * Obtener el pago (anticipo) relacionado a la extensi&oacuten.
	 * @return
	 */
	public MPayment getPayment(){
		if(mPayment == null || mPayment.getC_Payment_ID() == 0){
			mPayment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		return mPayment;
	}

	/**
	 * Extensi&oacuten
	 * @return
	 */
	public MEXMECtaPacExt getExtension(){
		if(mExtension == null || mExtension.getEXME_CtaPacExt_ID() == 0){
			mExtension = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
		}
		return mExtension;
	}

	/** Obtenemos los pagos(anticipos) relacionados a al extensi&oacuten.*/
	public static List<MEXMECtaPacPag> getAnticipos(Properties ctx, 
			int EXME_CtaPacExt_ID, String whereClause, String trxName ) {
		return new Query(ctx, Table_Name, " EXME_CtaPacExt_ID = ? "+(whereClause==null?StringUtils.EMPTY:whereClause), null)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY C_Payment_ID ")
		.setParameters(EXME_CtaPacExt_ID)
		.list();
	}

	/**
	 * USA Generamos la relacion del pago con la extensi&oacuten de la cuenta paciente
	 * @param ctx
	 * @param cPaymentID
	 * @param extensionID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static MEXMECtaPacPag generarCtaPacPag(Properties ctx, int cPaymentID, int extensionID, String trxName) throws Exception {
		final MEXMECtaPacPag pago = MEXMECtaPacPag.getMCtaPacPag(ctx, cPaymentID, extensionID, trxName);
		if(pago!=null && pago.getEXME_CtaPacPag_ID()>0){
			if(!pago.isActive()){
				pago.setIsActive(true);
				if (!pago.save(trxName)) {
					throw new Exception("error.caja.noSaveLine");
				}
			}
			return pago;
		}
		final MEXMECtaPacPag ctaPacPag = new MEXMECtaPacPag(ctx, 0, trxName);
		ctaPacPag.setC_Payment_ID(cPaymentID);
		ctaPacPag.setEXME_CtaPacExt_ID(extensionID);
		if (!ctaPacPag.save(trxName)) {
			throw new Exception("error.caja.noSaveLine");
		}
		return ctaPacPag;
	}

	/**
	 * Buscar la relacion pago-extensi&oacuten, activo y nivel de acceso
	 * @param ctx Contexto
	 * @param C_Payment_ID Id del pago
	 * @param trxName Nombre de transacci&oacuten
	 * @return Objeto MEXMECtaPacPag del pago y Extensi&oacuten 
	 * @deprecated
	 */
	public static MEXMECtaPacPag getMCtaPacPag(Properties ctx,int C_Payment_ID, String trxName){
		return  new Query(ctx, Table_Name, " C_Payment_ID = ?  ", null)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY C_Payment_ID ASC ")
		.setParameters(C_Payment_ID)
		.first();
	}
	
	/**
	 * Buscar la relacion pago-extensi&oacuten, activo y nivel de acceso
	 * @param ctx Contexto
	 * @param C_Payment_ID Id del pago
	 * @param extension Extension de la cuenta paciente
	 * @param trxName Nombre de transacci&oacuten
	 * @return Objeto MEXMECtaPacPag del pago y Extensi&oacuten 
	 */
	public static MEXMECtaPacPag getMCtaPacPag(Properties ctx,int C_Payment_ID, int extension, String trxName){
		return  new Query(ctx, Table_Name, " C_Payment_ID = ? AND EXME_CtaPacExt_ID = ? ", null)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY C_Payment_ID ASC ")
		.setParameters(C_Payment_ID, extension)
		.first();
	}

	/** Busca el EXME_CtaPacPag relacionado al payment y extensi&oacuten */
	public static MEXMECtaPacPag createFromPayment(final MPayment payment, final int extension) {
		final MEXMECtaPacPag pago = new MEXMECtaPacPag(payment.getCtx(), 0, payment.get_TrxName());
		pago.setC_Payment_ID(payment.getC_Payment_ID());
		pago.setEXME_CtaPacExt_ID(extension);
		pago.setAplicado(payment.getPayAmt().signum()<0?Env.ZERO:payment.getPayAmt());
		pago.setSeDevolvio(payment.getPayAmt().signum()<0);
		if (!pago.save()) {
			throw new MedsysException();
		}
		return pago;
	}

	/** Obtenemos los pagos(anticipos) relacionados a al extensi&oacuten. */
	public static List<MEXMECtaPacPag> getAnticiposPorPaciente(final Properties ctx, 
			final String whereClause, final String trxName ) {
		//final StringBuilder sql = new StringBuilder()
		//.append(" EXME_CtaPacExt_ID IN (  ")
		//.append("     	SELECT EXME_CtaPacExt_ID FROM EXME_CtaPacExt ")
		//.append("     	WHERE  EXME_CtaPac_ID IN ( ").append(whereClause).append(") ")
		//.append("     )        ");
		//return  new Query(ctx, Table_Name, sql.toString(), null)
		//.setOnlyActiveRecords(true)
		//.addAccessLevelSQL(true)
		//.setOrderBy(" ORDER BY C_Payment_ID ASC ")
		//.list();
		final StringBuilder sql = new StringBuilder()
		.append(" EXME_CtaPacPag.exme_ctapacext_id in (SELECT EXME_CtaPacExt_ID FROM EXME_CtaPacExt WHERE  EXME_CtaPac_ID IN (")
		.append(whereClause)
		.append(" ))  ")
		.append(" and (EXME_CtaPacDet_ID is null or EXME_CtaPacDet.isfacturado = 'N') ");
		
		return  new Query(ctx, Table_Name, sql.toString(), null)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setJoins(new StringBuilder(" left join EXME_CtaPacDet on EXME_CtaPacDet.exme_ctapacext_id = EXME_CtaPacPag.exme_ctapacext_id "))
		.list();
	}
	
	/** monto de anticipos relacionados a al extensi&oacuten */
	public static BigDecimal getPayAmtAllocated(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName) {
		return MEXMEAnticipo.getSumAplicado(ctx, EXME_CtaPacExt_ID, trxName);
	}

	/** Monto del anticipo por forma de pago X Extensi&oacuten */
	public static List<CtaPacPag> getAllocationOfExtension(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName) {
		List<CtaPacPag> lst = new ArrayList<CtaPacPag>();
		final StringBuilder sql = new StringBuilder()
		.append(" SELECT C_CashLine.EXME_FormaPago_ID, COALESCE(EXME_CtaPacPag.Aplicado,0) AS aplicado ")
		.append(" , EXME_CtaPacPag.EXME_CtaPacPag_ID ")
		.append(" FROM  EXME_CtaPacPag ")
		.append(" INNER JOIN C_CashLine     ON EXME_CtaPacPag.C_Payment_ID = C_CashLine.C_Payment_ID ")
		.append(" INNER JOIN EXME_FormaPago ON EXME_FormaPago.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID ")
		.append(" WHERE EXME_CtaPacPag.isActive = 'Y' ")
		.append(" AND   EXME_CtaPacPag.EXME_CtaPacExt_ID = ? ")
		.append(" ORDER BY C_CashLine.EXME_FormaPago_ID, EXME_FormaPago.Name ");
		//.append(" GROUP BY C_CashLine.EXME_FormaPago_ID, EXME_FormaPago.Name ");
		
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		pstmt.setInt(1, EXME_CtaPacExt_ID);
    		rs = pstmt.executeQuery();

    		// CREAR UN OBJETO CtaPacPag POR FORMA DE PAGO
    		// PERO LLENANDO LA LISTA CON  LOS PAGOS
    		int formaPagoID = 0;
    		List<MEXMECtaPacPag> mCtaPacPagLst = new ArrayList<>();
    		CtaPacPag bean = null;
    		while (rs.next()){ 
    			if(formaPagoID != rs.getInt(1)){///mal
    				if(formaPagoID > 0){
    					bean.setLstPayments(mCtaPacPagLst);
    					lst.add(bean);
    				}
    				formaPagoID = rs.getInt(1);
    				bean = new CtaPacPag();
    				bean.setAmount(rs.getBigDecimal(2));
    				bean.setmFormaPago(new MFormaPago(ctx, rs.getInt(1), trxName));
    				bean.setmExtension(new MEXMECtaPacExt(ctx, EXME_CtaPacExt_ID, trxName));
    				mCtaPacPagLst = new ArrayList<>();
    			//bean.setLstPayments(MEXMECtaPacPag.get(ctx, trxName, EXME_CtaPacExt_ID));
    			} else {
    				bean.setAmount(bean.getAmount().add(rs.getBigDecimal(2)));//FIXME dloza y erodriguez
    			}
    			mCtaPacPagLst.add(new MEXMECtaPacPag(ctx, rs.getInt(3), trxName));
    		}
    		bean.setLstPayments(mCtaPacPagLst);
    		lst.add(bean);
    	} catch (Exception e) {    		
    		slog.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	return lst;
	}
	
	/** Listado de pagos */
	private static List<MEXMECtaPacPag> getAnticiposSinFacturar(final Properties ctx, final int EXME_CtaPac_ID, final String trxName ) {
		final StringBuilder joins = new StringBuilder()
		.append(" INNER JOIN EXME_CtaPacExt ext  ON ext.EXME_CtaPacExt_ID = EXME_CtaPacPag.EXME_CtaPacExt_ID ")
		.append("                               AND ext.C_Invoice_ID IS NULL "); 
		return new Query(ctx, X_EXME_CtaPacPag.Table_Name, " EXME_CtaPacPag.EXME_CtaPac_ID = ? AND EXME_CtaPacPag.Aplicado > 0 ", trxName)
						.setOnlyActiveRecords(true)
						.addAccessLevelSQL(true)
						.setJoins(joins)
						.setOrderBy(" ORDER BY ext.ExtensionNo ASC, EXME_CtaPacPag.C_Payment_ID ASC ")
						.setParameters(EXME_CtaPac_ID, EXME_CtaPac_ID)
						.list();
	}
	
	/** Listado de anticipos con forma de pago (EXME_FormaPago_ID>0) o todos  */
	public static List<MEXMECtaPacPag> getAnticiposSinFacturar(final Properties ctx, final String trxName, final int EXME_CtaPac_ID, final int EXME_FormaPago_ID, final boolean all) {
		List<MEXMECtaPacPag> lCtaPacPag = new ArrayList<MEXMECtaPacPag>();
		if(EXME_FormaPago_ID>0 && !all){
			lCtaPacPag.addAll(MEXMECtaPacPag.getAnticiposSinFacturar(ctx, EXME_CtaPac_ID, EXME_FormaPago_ID, all, trxName));
		} 
		
		if(EXME_FormaPago_ID<=0 && !all){
			lCtaPacPag.addAll(MEXMECtaPacPag.getAnticiposSinFacturar(ctx, EXME_CtaPac_ID, trxName));
		}
		
		if(EXME_FormaPago_ID>0 && all){
			lCtaPacPag.addAll(MEXMECtaPacPag.getAnticiposSinFacturar(ctx, EXME_CtaPac_ID, EXME_FormaPago_ID, all, trxName));
		}
		return lCtaPacPag;
	}
	
	/** Listado de anticipos por forma de pago, considera los montos netos y que no existan facturas */
	private static List<MEXMECtaPacPag> getAnticiposSinFacturar(final Properties ctx, final int EXME_CtaPac_ID, final int EXME_FormaPago_ID, final boolean all, final String trxName) {
		final List<MEXMECtaPacPag> lst = new ArrayList<MEXMECtaPacPag>();
		
		final StringBuilder select = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT EXME_CtaPacExt.ExtensionNo, EXME_CtaPacPag.* ")
		.append(" FROM  EXME_CtaPacPag ")
		.append(" INNER JOIN EXME_CtaPacExt ON EXME_CtaPacPag.EXME_CtaPacExt_ID = EXME_CtaPacExt.EXME_CtaPacExt_ID ")
		.append("                               AND EXME_CtaPacExt.C_Invoice_ID IS NULL ")
		.append(" INNER JOIN C_CashLine     ON EXME_CtaPacPag.C_Payment_ID      = C_CashLine.C_Payment_ID ")
		.append(" INNER JOIN EXME_FormaPago ON EXME_FormaPago.Ref_FormaPago_ID = C_CashLine.EXME_FormaPago_ID ")
		.append(" WHERE EXME_CtaPacPag.isActive = 'Y'          ")
		.append(" AND   EXME_CtaPacPag.Aplicado > 0            ")
		.append(" AND   EXME_CtaPacPag.SeDevolvio = 'N'        ")
		.append(" AND   EXME_CtaPacExt.EXME_CtaPac_ID = ?      ");
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(select)
		.append(" AND   EXME_FormaPago.EXME_FormaPago_ID = ?    ");// El parametro es la forma de pago inversa
		
		if(all){
			sql.append(" UNION  ")
			.append(select)
			.append(" AND   EXME_FormaPago.EXME_FormaPago_ID <> ?  ")// El parametro es la forma de pago inversa
			.append(" ORDER BY ExtensionNo ASC, C_Payment_ID ASC  ");
		} else {
			sql.append(" ORDER BY EXME_CtaPacExt.ExtensionNo ASC, EXME_CtaPacPag.C_Payment_ID ASC ");
		}
		
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), trxName);
    		pstmt.setInt(1, EXME_CtaPac_ID);
    		pstmt.setInt(2, EXME_FormaPago_ID);
    		if(all){
    			pstmt.setInt(3, EXME_CtaPac_ID);
    			pstmt.setInt(4, EXME_FormaPago_ID);
    		}
    		rs = pstmt.executeQuery();

    		while (rs.next()){ 
    			lst.add(new MEXMECtaPacPag(ctx, rs, trxName));
    		}
    	} catch (Exception e) {    		
    		slog.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	return lst;
	}
	
	
	
	
	
	
	
	
	
	
	
/** Column name SeDevolvio */
public static final String COLUMNNAME_SeDevolvio = "SeDevolvio";	
	
/** Column name Aplicado */
public static final String COLUMNNAME_Aplicado = "Aplicado";
	
	/** Set Applied Amount.
	@param Aplicado 
	Applied Amount
  */
public void setAplicado (BigDecimal Aplicado)
{
	set_Value (COLUMNNAME_Aplicado, Aplicado);
}

/** Get Applied Amount.
	@return Applied Amount
  */
public BigDecimal getAplicado () 
{
	BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Aplicado);
	if (bd == null)
		 return Env.ZERO;
	return bd;
}

/** Set Was Returned.
@param SeDevolvio Was Returned	  */
public void setSeDevolvio (boolean SeDevolvio)
{
	set_Value (COLUMNNAME_SeDevolvio, Boolean.valueOf(SeDevolvio));
}

/** Get Was Returned.
@return Was Returned	  */
public boolean isSeDevolvio () 
{
	Object oo = get_Value(COLUMNNAME_SeDevolvio);
	if (oo != null) 
	{
		if (oo instanceof Boolean) 
			return ((Boolean)oo).booleanValue(); 
		return "Y".equals(oo);
	}
	return false;
}

}
