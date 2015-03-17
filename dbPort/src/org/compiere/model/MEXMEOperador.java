/*
 * Created on 07-mar-2005
 *
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/**
 * <b>Fecha:</b> 07/Marzo/2005<p>
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/10/19 20:33:58 $<p>
 *
 * @author hassan reyes
 * @version $Revision: 1.12 $
 */
public class MEXMEOperador extends X_EXME_Operador {

	/** serialVersionUID */
	private static final long serialVersionUID = -8204145783855564733L;

	/** id de la caja abierta por operador */
	private int cashId = 0;
	/** id del operador cajero relacionado */
	private int cajeroId = 0;
	/** id de la caja abierta por otro operador */
	private int cashRelId = 0;


	/** log */
	private static CLogger logger = CLogger.getCLogger(MEXMEOperador.class);

	/** Constructor
	 * @param ctx: contexto
	 * @param operadorId: identificador del registro, puede ser cero si es nuevo
	 * @param trxName: Nombre de transacción
	 */
	public MEXMEOperador(final Properties ctx, final int operadorId, final String trxName) {
		super(ctx, operadorId, trxName);
	}

	/** Constructor
	 * @param ctx: contexto
	 * @param rs: resulset
	 * @param trxName: Nombre de transacción
	 */
	public MEXMEOperador(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos una Lista de KeyNamePair con las operadores
	 * @param ctx contexto
	 * @param trxName Nombre de la transaccion
	 */
	public static List<KeyNamePair>  getAll(Properties ctx, String trxName){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT EXME_Operador_ID, Name FROM EXME_Operador WHERE isACtive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Name ");
		
		return Query.getListKN(sql.toString(), trxName);
	}

	/**
	 * Obtenemos los datos de un Operador de (Caja, Facturaci�n, Ambos).
	 * No valida nombre de usario ni password.
	 * @param ctx
	 * @param cCashBookID caja del usuario
	 * @return
	 */
	public static MEXMEOperador get(final Properties ctx, final int cCashBookID, final String trxName){
		final List<Object> params = new ArrayList<Object>();

		// Relacion de Caja con Operador
		final StringBuilder sql = new  StringBuilder();
//		.append(" SELECT * ")
//		.append(" FROM  EXME_Operador ")
//		.append(" WHERE IsActive='Y'  ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Operador"))
		sql.append("    AD_User_ID    = ? ");
		params.add(Env.getContextAsInt(ctx,"AD_User_ID"));
		if (cCashBookID == 0) {
			sql.append(" AND ESTATUS_CAJA = 'Y' ");
		} else {
			sql.append(" AND   C_CashBook_ID = ? ");
			params.add(cCashBookID);
		}

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
		.setOnlyActiveRecords(true)//
		.setParameters(params)//
		.addAccessLevelSQL(true)//
		.first();
	}

	/**
	 * Indica si el operador es cajero.
	 * @return true si es cajero, falso si solo es facturador y no ambos
	 */
	public boolean isCajero(){
		return TIPO_OPERADOR_Cashier.equals(getTipo_Operador())
				|| TIPO_OPERADOR_Both.equals(getTipo_Operador());
	}

	/**
	 * Indica si el operador es facturador.
	 * @return true si es facturador, falso si solo es cajero y no ambos
	 */
	public boolean isFacturador(){
		return TIPO_OPERADOR_Invoicer.equals(getTipo_Operador())
				|| TIPO_OPERADOR_Both.equals(getTipo_Operador());
	}

	/**
	 * Indica si el operador es cajero y facturador.
	 * @return true si es ambos
	 */
	public boolean isAmbos(){
		return TIPO_OPERADOR_Both.equals(getTipo_Operador());
	}

	
	/** 
	 * Correspondencia de cajas para usuario
	 * @param ctx contexto
	 * @param AD_User_ID usuario
	 * @param value operador
	 * @param trxName Nombre de la transaccion
	 * @param whereClause 
	 */
	public static MCashBook[] getCajasUsuario(final Properties ctx, final int aD_User_ID, 
			final String whereClause, final String trxName) {

//		// Obtenemos todas aquellas cajas dependiendo del usuario y del estatus de la caja
//		final StringBuilder sql = new  StringBuilder()
//		.append(" SELECT cb.* ")
//		.append(" FROM  EXME_Operador ")
//		.append(" INNER JOIN C_CashBook cb ON cb.C_CashBook_ID = EXME_Operador.C_CashBook_ID ")
//		.append(" WHERE EXME_Operador.IsActive='Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Operador"))
//		.append(" AND   EXME_Operador.AD_User_ID = ? ")
//		.append(estatus==null?" ":estatus);
		final List<MCashBook> lista = getCashbookByUser(ctx, aD_User_ID, whereClause, trxName);

		final MCashBook[] listCash = new MCashBook[lista.size ()];
		lista.toArray(listCash);
		return listCash;
	}
	
	/**
	 * Obtenemos una Lista de KeyNamePair con las cajas relacionadas al usuario.
	 * @param ctx contexto
	 * @param AD_User_ID usuario
	 * @param value operador
	 * @param trxName Nombre de la transaccion
	 * @param estatus Estatus de la caja (Y - abierta, N - cerrada, C - corte)
	 */
	public static List<KeyNamePair> getListKN(final Properties ctx, final int AD_User_ID, final String estatus, final String trxName) {
		// Obtenemos todas aquellas cajas dependiendo del usuario y del estatus de la caja
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cb.* ");
		sql.append(" FROM  EXME_Operador ");
		sql.append(" INNER JOIN C_CashBook cb ON cb.C_CashBook_ID = EXME_Operador.C_CashBook_ID ");
		sql.append(" WHERE EXME_Operador.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Operador"));
		sql.append(" AND EXME_Operador.AD_User_ID = ? ");
		sql.append(estatus == null ? " " : estatus);
		return Query.getListKN(sql.toString(), trxName, AD_User_ID);
	}

	/** 
	 * Correspondencia de cajas para usuario
	 * @param ctx contexto
	 * @param AD_User_ID usuario
	 * @param value operador
	 * @param trxName Nombre de la transaccion
	 * @param whereClause 
	 */
	public static List<MCashBook> getCashbookByUser(final Properties ctx, final int aD_User_ID, 
			final String whereClause, final String trxName) {
		// Obtenemos todas aquellas cajas dependiendo del usuario y del estatus de la caja
		final StringBuilder sql = new  StringBuilder()
//		.append(" SELECT cb.* ")
//		.append(" FROM  EXME_Operador ")
//		.append(" INNER JOIN C_CashBook cb ON cb.C_CashBook_ID = EXME_Operador.C_CashBook_ID ")
//		.append(" WHERE EXME_Operador.IsActive='Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Operador"))
		.append(" EXME_Operador.AD_User_ID = ? ")
		.append(whereClause==null?" ":whereClause);

		return new Query(ctx, MCashBook.Table_Name, sql.toString(), trxName)//
		.setOnlyActiveRecords(true)//
		.setJoins(new StringBuilder(" INNER JOIN EXME_Operador ON C_CashBook.C_CashBook_ID = EXME_Operador.C_CashBook_ID "))
		.setParameters(aD_User_ID)//
		.addAccessLevelSQL(true)//
		.list();
	}
	
	/**
	 * Obtenemos una Lista de LabelValueBean con las cajas relacionadas al usuario.
	 * @param ctx contexto
	 * @param AD_User_ID usuario
	 * @param value operador
	 * @param trxName Nombre de la transaccion
	 * @param estatus Estatus de la caja (Y - abierta, N - cerrada, C - corte)
	 * @deprecated use {@link KeyNamePair} use {@link #getKName(Properties, int, String, String)}
	 */
	public static List<LabelValueBean> getLVCajasUsuario(final Properties ctx, final int AD_User_ID, final String estatus, final String trxName){

		final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		try {
			//cambiar de arreglo a lista de labelvaluebean
			final MCashBook[] cashb = getCajasUsuario(ctx, AD_User_ID, estatus, trxName);
			for (int i = 0; i < cashb.length; i++){
				final MCashBook line = cashb[i];
				lista.add(new LabelValueBean(line.getName(), String.valueOf(line.getC_CashBook_ID())));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "getLVCajasUsuario", e);
		}  
		return lista;    
	}

	/**
	 * Obtenemos el usuario asignada 
	 * @return
	 */
	public MUser getUsuario() {
		if(getAD_User_ID() <= 0){
			return null;
		}
		return new MUser(getCtx(), getAD_User_ID(), get_TrxName());
	}

	/**
	 * Obtener el objeto de caja
	 * @return null si no hay una relación operador-caja
	 */
	public MCashBook getCaja() {
		if(getC_CashBook_ID() < 1) {
			return null;
		}
		return new MCashBook(getCtx(), getC_CashBook_ID(), get_TrxName());
	}


	protected boolean beforeSave(boolean newRecord){
		boolean success = true;
		
		if(newRecord){/*Cuando es un nuevo registro, esta bandera debe ser false, eruiz*/
			setEstatus_Caja(false);

		} else {
			
			// Si tiene una caja abierta por usuario
			// no deberá tener modificaciones su configuración
			if(is_ValueChanged("IsActive")
					|| is_ValueChanged("AD_Client_ID")
					|| is_ValueChanged("AD_Org_ID")
					|| is_ValueChanged("C_CashBook_ID")
					|| is_ValueChanged("EXME_Operador_ID")
					|| is_ValueChanged("AD_User_ID")
					|| (is_ValueChanged("Tipo_Operador") && !getTipo_Operador().equals(X_EXME_Operador.TIPO_OPERADOR_Both))
					){
				
				int caja = is_ValueChanged("C_CashBook_ID")?get_ValueOldAsInt ("C_CashBook_ID"):getC_CashBook_ID();
				// Buscamos una caja con estatus DR si existe no se le pueden hacer cambios al operador
				final int cash_id = MCash.getCash(getCtx(), caja, getEXME_Operador_ID(), get_TrxName());
				if(cash_id != 0){
					success = false;
				}
			}
		}
		return success;
	}
	
	/**
	 * Buscar la relacion usuario - Operador - cajero 
	 * @param ctx: Contexto
	 * @param tipoOperador: Tipo de operador (Cajero/Facturador)
	 * @return Listado de operadores del usuario, si existe una caja abierta del usuario de logueo tomara ese registro.
	 */
	public static List<MEXMEOperador> getOperadorSelectCash(final Properties ctx, final String tipoOperador){

		final List<MEXMEOperador> lista = new ArrayList<MEXMEOperador>();
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT op.EXME_Operador_ID AS login, ca.EXME_Operador_ID AS caja, ca.C_Cash_ID ")
		.append(" FROM  EXME_Operador op ")
		.append(" INNER JOIN C_Cashbook cb ON cb.C_Cashbook_ID = op.C_Cashbook_ID ")// Caja
		.append(" INNER JOIN C_Cash     ca ON ca.C_Cashbook_ID  = op.C_Cashbook_ID ")// Diario de caja
		.append("                         AND ca.Docstatus IN ('DR') ")// Abierta
		.append("                         AND ca.IsActive = 'Y'      ")// Activa
		.append(" WHERE op.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ","EXME_Operador","op"))
		.append(" AND   op.AD_User_ID = ? ");// Operador del usuario de login
		
		if(tipoOperador.length()==1){
			sql.append(" AND   op.Tipo_Operador IN (?,'A') ");// Tipo de operador ambos y ?
		} else {
			sql.append(" AND   op.Tipo_Operador IN ('")
			.append(tipoOperador)
			.append("','A') ");// Tipo de operador ambos y ?
		}
		sql.append(" ORDER BY ca.C_Cash_ID DESC, cb.isDefault DESC ");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement (sql.toString(), null);
			pstmt.setInt(1, Env.getAD_User_ID(ctx));
			if(tipoOperador.length()==1){
				pstmt.setString(2, tipoOperador);
			}
			
			rs = pstmt.executeQuery ();
			while (rs.next ()){
				final MEXMEOperador bean = new MEXMEOperador(ctx, rs.getInt("login"), null);
				bean.setCashId(rs.getInt("C_Cash_ID"));
				bean.setCajeroId(rs.getInt("caja"));
				lista.add(bean);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "getCajaOperadorId", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	/**
	 * Buscar la realcion usuario - caja (Operador) para aperturar una caja
	 * @param ctx: Contexto
	 * @param tipoOperador: Tipo de operador (Cajero/Facturador)
	 * @return Listado de operadores del usuario, si existe una caja abierta del usuario de logueo tomara ese registro.
	 */
	public static List<MEXMEOperador> getOperadorOpenCash(final Properties ctx, final String tipoOperador){
		final List<MEXMEOperador> lista = new ArrayList<MEXMEOperador>();
		// Relacion de Caja con Operador
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT op.*, c.C_Cash_ID, other.EXME_Operador_ID AS cajero,  other.C_Cash_ID AS otherCash ")
		.append(" FROM  EXME_Operador op ")
		.append(" INNER JOIN C_Cashbook cb ON  cb.C_Cashbook_ID   = op.C_Cashbook_ID ")// Caja
		// Otro usuario con caja abierta
		.append(" LEFT  JOIN (SELECT C_Cashbook_ID, EXME_Operador_ID, C_Cash_ID FROM C_Cash WHERE IsActive = 'Y' AND AD_Client_ID = ? AND AD_Org_ID = ? AND Docstatus IN ('DR')  AND CreatedBy <> ? ) as other ON other.C_Cashbook_ID = op.C_Cashbook_ID ")
		// Caja abierta por el mismo usuario
		.append(" LEFT  JOIN C_Cash      c ON c.C_Cashbook_ID    = op.C_Cashbook_ID  ")// Caja
		.append(" 		 			      AND c.EXME_Operador_ID = op.EXME_Operador_ID ")// Operador
		.append("                         AND c.Docstatus IN ('DR') AND c.IsActive = 'Y' ")// Abierta y activa
		.append(" WHERE op.IsActive = 'Y' ")
		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ","EXME_Operador","op")))
		.append(" AND   op.AD_User_ID = ? ")// Operador del usuario de login
//		.append(" AND   op.Tipo_Operador IN (?,'A') ")// Tipo de operador ambos y ?
		.append(" ORDER BY c.C_Cash_ID DESC, cb.isDefault DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.setInt(3, Env.getAD_User_ID(ctx));
			pstmt.setInt(4, Env.getAD_User_ID(ctx));
//			pstmt.setString(3, tipoOperador);
			
			rs = pstmt.executeQuery ();
			while (rs.next ()){
				// Registros de caja-usuario que se pueden abrir
				final MEXMEOperador bean = new MEXMEOperador(ctx, rs, null);
				bean.setCashId(rs.getInt("C_Cash_ID"));
				bean.setCajeroId(rs.getInt("cajero"));
				bean.setCashRelId(rs.getInt("otherCash"));
//				// Si la caja esta abierta pero no es del usuario de logueo no se puede seleccionar 
//				if(rs.getInt("C_Cash_ID")<1 && rs.getInt("cajero")>0){
//					continue;
//				} else {
//					lista.add(bean);
//				}
//				
//				// Si alguno de la lista ya esta abierta y es del usuario de logueo esa es por defecto
//				if(bean.isEstatus_Caja() && rs.getInt("C_Cash_ID")>0){
//					lista.clear();
//					lista.add(bean);
//					break;
//				}
				
				lista.add(bean);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "getCajaOperadorId", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	public void setDataCash(int cashID, int cajeroID){
		this.cashId = cashID;
		this.cajeroId = cajeroID;
	}
	public int getCashId() {
		return cashId;
	}
	private void setCashId(final int int1) {
		cashId = int1;
	}
	public int getCajeroId() {
		return cajeroId;
	}
	public void setCajeroId(final int cajeroId) {
		this.cajeroId = cajeroId;
	}
	
	public boolean esAmbos(){
		return X_EXME_Operador.TIPO_OPERADOR_Both.equals(getTipo_Operador());
	}
	
	public int getCashRelId() {
		return cashRelId;
	}

	public void setCashRelId(int cashRelId) {
		this.cashRelId = cashRelId;
	}
}
