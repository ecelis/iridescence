/*
 * Created on 10-mar-2005
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * <b>Fecha:</b> 10/Marzo/2005
 * <p>
 * <b>Modificado: </b> $Author: gisela $
 * <p>
 * <b>En :</b> $Date: 2006/08/30 00:58:54 $
 * <p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.11 $
 */
public class MFormaPago extends X_EXME_FormaPago {

	/** log de la clase */
	private static CLogger		slog					= CLogger.getCLogger(MFormaPago.class);

	/** serialVersionUID */
	private static final long	serialVersionUID		= 1023237148391560684L;
	/** Forma de Pago Efectivo "E" */
	public static final String	EFECTIVO				= "E";
	/** Forma de Pago Tarjeta "T" */
	public static final String	TARJETA					= "T";
	/** Forma de Pago Cheque "C" */
	public static final String	CHEQUE					= "C";
	/** Forma de Pago Efectivo "Otros" */
	public static final String	OTROS					= "O";
	/**
	 * Forma de Pago Anticipos "A". Esta forma de Pago no es solo ficticia. No
	 * existe un registro en la BD. Solo se usa para mostrar en pantalla el
	 * total de Allocations.
	 */
	public static final String	ANTICIPO				= "A";
	public static final String	DEVOL_ANTICIPO			= "D";

//	/**
//	 * KEY de archivo de propiedades para obtener el nombre por concepto de
//	 * Anticipo.
//	 */
//	public static final String	ANTICIPO_NAME_KEY		= "msg.Anticipo";
//	public static final String	DEV_ANTICIPO_NAME_KEY	= "msg.Dev_Anticipo";

	/** Contiene el valor de una de las constantes definidas anteriormente */
	private String				formaPago				= null;

	/**
	 * @param ctx
	 * @param ID
	 * @param trxName
	 */
	public MFormaPago(final Properties ctx, final int EXME_FormaPago_ID, final String trxName) {
		super(ctx, EXME_FormaPago_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MFormaPago(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtenemos los datos de una Forma de Pago No valida nombre de usario ni
	 * password.
	 * 
	 * @param ctx
	 * @param AD_User_ID
	 * @return
	 */
	public static MFormaPago getEfectivo(final Properties ctx, final String value, final String trxName) {
		// La relaci�n Operador (value) <---> caja es Uno a Uno.
		//        sql.append("SELECT * FROM EXME_FormaPago WHERE IsActive='Y' ")
		//            .append("AND UPPER(Name)= UPPER('" + value + "') ")
		//            .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		return new Query(ctx, Table_Name, " UPPER(Name)=? ", trxName)//
		.setParameters(StringUtils.upperCase(value))//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.first();
	}

	/**
	 * Obtenemos los datos de una Forma de Pago a partir de las reglas de pago
	 * 
	 * @param ctx  El contexto de la aplicacion
	 * @param paymentRule Una cadena con el value de las Reglas de Pago
	 * @param isDevol indica si la forma de pago es una devolucion o no
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public static MFormaPago getOfPaymentRule(final Properties ctx, final String PaymentRule, final boolean esDevol, final String trxName) {
		// sql.append("SELECT * FROM EXME_FormaPago ")
		// .append("WHERE IsActive='Y' ")
		// .append("AND PaymentRule = ? AND EsDevol = ? ")
		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		return new Query(ctx, Table_Name, " PaymentRule=? AND EsDevol=?  ", trxName)//
		.setParameters(PaymentRule, DB.TO_STRING(esDevol))//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.first();
	}

	/** @return Indica si la forma de pago es efectivo */
	public boolean isCash() {
		return PAYMENTRULE_Cash.equals(getPaymentRule());
	}

	/** @return Indica si la forma de pago es tarjeta de credito */
	public boolean isCreditCard() {
		return PAYMENTRULE_CreditCard.equals(getPaymentRule());
	}

	/** @return Indica si la forma de pago es cheque */
	public boolean isCheck() {
		return PAYMENTRULE_Check.equals(getPaymentRule());
	}

	/** @return Indica si la forma de pago es cuentas x cobrar */
	public boolean isCxC() {
		return PAYMENTRULE_OnCredit.equals(getPaymentRule());
	}

	/** @return Indica si la forma de pago es otros */
	public boolean isOther() {
		return PAYMENTRULE_Other.equals(getPaymentRule());
	}

//	/** @return Indica si la forma de pago es anticipo */
//	public boolean isPrepayment() {
//		return PAYMENTRULE_Prepayment.equals(getPaymentRule());
//	}

	/**
	 * Obtenemos todas las formas de pago que existen en la tabla
	 * 
	 * @param ctx
	 * @param trxName
	 * @return arreglo de formas de pago
	 */
	public static MFormaPago[] getTiposFormasPago(final Properties ctx, final String trxName) {
		// // Traer todas las formas de pago
		// sql.append(" SELECT * FROM EXME_FormaPago WHERE IsActive='Y' ")
		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		final List<MFormaPago> lista = new Query(ctx, Table_Name, null, trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.list();

		final MFormaPago[] formas = new MFormaPago[lista.size()];
		lista.toArray(formas);
		return formas;
	}

	//    /**
	//     * Obtenemos las formas de pago existentes para empresa organizacion que
	//     * existen
	//     *
	//     * @param ctx
	//     * @param AD_User_ID
	//     * @return
	//     */
	//    public static List<LabelValueBean> getLVTiposFormasPago(Properties ctx, String trxName) {
	//        List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
	//        try {
	//            // cambiar de arreglo a lista de labelvaluebean
	//            MFormaPago[] formas = getTiposFormasPago(ctx, trxName);
	//            for (int i = 0; i < formas.length; i++) {
	//                MFormaPago line = formas[i];
	//                lista.add(new LabelValueBean(line.getName(), String
	//                        .valueOf(line.getEXME_FormaPago_ID())));
	//            }
	//        } catch (Exception e) {
	//        	slog.log(Level.SEVERE, e.toString(), e);
	//        }
	//        return lista;
	//    }

	// twry falta cashtype
	/**
	 * Obtengo el monto por cada forma de pago que exista cashline que
	 * corresponda aun solo header de C_Cash
	 * 
	 * @Param ctx
	 * @Param c_Cash_ID
	 * @Param exme_FormaPago_ID
	 * @Param trxName
	 * @Return monto
	 */
	public static BigDecimal getAmountCashLine(final Properties ctx, final int c_Cash_ID, final int exme_FormaPago_ID, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT COALESCE(SUM(c_cashline.Amount),0) AS monto FROM c_cashline ");
		sql.append(" WHERE c_cashline.IsActive='Y' ");
		sql.append(" AND c_cashline.C_Cash_ID = ? ");
		sql.append(" AND c_cashline.C_Currency_ID=? ");//Card #1170
		sql.append(" AND (c_CashLine.isCancelled='N' OR C_CashLine.Ref_Cash_id!=?)");//Card #1201 - ASnchez
		if (MCountry.isMexico(ctx)) {
			sql.append(" AND c_cashline.EXME_FormaPago_ID IN ");
			sql.append(" ( SELECT DISTINCT fp.EXME_FormaPago_ID ");
			sql.append(" FROM  EXME_FormaPago fp ");
			sql.append(" WHERE (CASE WHEN fp.EsDevol='Y' THEN fp.REF_FormaPago_ID ");
			sql.append(" ELSE fp.EXME_FormaPago_ID END) = ?  ");
			sql.append(" ) ");
		} else {
			sql.append(" AND c_cashline.EXME_FormaPago_ID = ? ");
		}
		// Condicion añadida para evitar que los registros tipo ajuste sean tomados en cuenta 
		// en el total para Close Cash Register (WClosingCashRegister)
		// RQM 4429 - Count Cash. Manda mensaje de diferencias cuando no las hay, 
		// ya que esta tomando montos de ajustes realizados desde LATE CHARGES
		// Mayo 15 de 2013
		sql.append(" AND NOT EXISTS(SELECT C_PAYMENT_ID \n");
		sql.append("                FROM C_PAYMENT P \n");
		sql.append("                INNER JOIN C_CHARGE CH ON CH.C_CHARGE_ID = P.C_CHARGE_ID \n");
		sql.append("                WHERE C_PAYMENT_ID = C_CashLine.C_PAYMENT_ID \n");
		sql.append("                AND CH.TYPE IN ('A','B','G','C','D','I')) \n");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"));
		final BigDecimal amount = DB.getSQLValueBD(trxName, sql.toString(), c_Cash_ID, Env.getC_Currency_ID(ctx),c_Cash_ID, exme_FormaPago_ID);
		return amount == null ? BigDecimal.ZERO : amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	//    public int getId() {
	//        return getEXME_FormaPago_ID();
	//    }
	
	public void setFormaPago(final String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Obtenemos una cadena descriptiva de la forma de pago.
	 * 
	 * @param El contexto de la aplicacion
	 * @param El identificador de la forma de pago
	 * @param El nombre de la transaccion
	 * @return La descripcion generica de la forma de pago (Efectivo, tarjeta,
	 *         cheque, anticipo, devolucion, otros)
	 */
	public String getFormaPago() {
		setFormaPago(null);
		if (isCash()) {
			formaPago = EFECTIVO; // efectivo: habilitar 2 montos
		} else if (isCreditCard()) {
			formaPago = TARJETA; // tarjeta credito
		} else if (isCheck()) {
			formaPago = CHEQUE; // cheque
//		} else if (isPrepayment()) {
//			formaPago = ANTICIPO; // anticipo
		} else { // default o: cxc y otros (solo habilita monto)
			formaPago = OTROS; // otros
		}
		return formaPago;
	}

	/**
	 * Obtenemos la forma de pago inversa a la actual p.e. Efectivo -> inverso =
	 * Devolucion de efectivo Devolucion Cheque -> Inverso = Cheque
	 * 
	 * @return el identificador de la forma de pago inversa a la actual
	 */
	public int getInverso() {
		int inverso = 0;
		if (isEsDevol()) {
			inverso = getRef_FormaPago_ID();
		} else {
			// buscar la devolucion correspondiente al pago
//			sql.append("SELECT EXME_FormaPago_ID FROM EXME_FormaPago ")
//			.append("WHERE Ref_FormaPago_ID= ? AND EsDevol = 'Y' AND IsActive='Y' ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", Table_Name));
			inverso = new Query(getCtx(), Table_Name, " Ref_FormaPago_ID=? AND EsDevol='Y' ", null)//
				.setParameters(getEXME_FormaPago_ID())//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.firstId();
		}
		return inverso;
	}

	//    public static long getTipoDevolucion(Properties ctx, int formaPago_ID) {
	//        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	//        sql.append("SELECT REF_FORMAPAGO_ID FROM EXME_FORMAPAGO ")
	//            .append("WHERE EXME_FORMAPAGO_ID=? AND ISACTIVE='Y' ")
	//            .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
	//        return DB.getSQLValue(null, sql.toString(), formaPago_ID);
	//    }

	/**
	 * 
	 * @param ctx
	 * @param tipoDoc
	 * @param isDevol
	 * @return
	 */
	public static List<KeyNamePair> getFormaPagoDevol(final Properties ctx, final String tipoDoc, final boolean isDevol) {
		return getFormaPago(ctx, tipoDoc, isDevol);
	}

	/**
	 * Devuelve una list objetos KeyNamePair con el ID y Nombre de las formas
	 * de pago que aplican para la captura de pagos en caja (cash, check, credit
	 * card, on credit, return y other)
	 * 
	 * @param ctx Contexto
	 * @param tipoDoc F=Factura y CP=Cuenta Paciente
	 * 
	 * @return un lista de objetos KeyNamePair con el Value y Nombre
	 * @throws Exception en caso de ocurrir un error en la consulta
	 */
	public static List<KeyNamePair> getFormaPago(final Properties ctx, final String tipoDoc) {
		return getFormaPago(ctx, tipoDoc, null);
	}
	
	/**
	 * Devuelve una list objetos KeyNamePair con el ID y Nombre de las formas
	 * de pago que aplican para la captura de pagos en caja (cash, check, credit
	 * card, on credit, return y other)
	 * 
	 * @param ctx Contexto
	 * @param tipoDoc F=Factura y CP=Cuenta Paciente
	 * @param isDevol si es o no devolucion (puede ser nulo)
	 * @return un lista de objetos KeyNamePair con el Value y Nombre
	 * @throws Exception en caso de ocurrir un error en la consulta
	 */
	public static List<KeyNamePair> getFormaPago(final Properties ctx, final String tipoDoc, final Boolean isDevol) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT EXME_FormaPago_ID, Name ");
		sql.append(" FROM EXME_FormaPago ");
		sql.append(" WHERE isActive='Y' ");
		if (isDevol != null) {
			sql.append(" AND esDevol=? ");
			params.add(DB.TO_STRING(isDevol));
		}
		sql.append(" AND PaymentRule IN (");
		if ("F".equalsIgnoreCase(tipoDoc)) {
			sql.append("?,");
			params.add(PAYMENTRULE_OnCredit);
		}
		sql.append("?,?,?,?)");
		params.add(PAYMENTRULE_Cash);
		params.add(PAYMENTRULE_Check);
		params.add(PAYMENTRULE_CreditCard);
		params.add(PAYMENTRULE_Other);
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY Name ");
		
		return Query.getListKN(sql.toString(), null, params.toArray());
	}
	
	
	/**
	 * Devuelve una list objetos KeyNamePair con el ID y Nombre de las formas
	 * de pago que aplican para la captura de pagos en caja (cash, check, credit
	 * card, on credit, return y other)
	 * 
	 * @param ctx Contexto
	 * @param tipoDoc F=Factura y CP=Cuenta Paciente
	 * @param isDevol si es o no devolucion (puede ser nulo)
	 * @return un lista de objetos MFormaPago
	 * @throws Exception en caso de ocurrir un error en la consulta
	 */
	public static List<MFormaPago> getList(final Properties ctx, final String tipoDoc, final Boolean isDevol) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		sql.append(" PaymentRule IN (");
		if ("F".equalsIgnoreCase(tipoDoc)) {
			sql.append("?,");
			params.add(PAYMENTRULE_OnCredit);
		}
		sql.append("?,?,?,?)");
		params.add(PAYMENTRULE_Cash);
		params.add(PAYMENTRULE_Check);
		params.add(PAYMENTRULE_CreditCard);
		params.add(PAYMENTRULE_Other);

		if (isDevol != null) {
			sql.append(" AND esDevol=? ");
			params.add(DB.TO_STRING(isDevol));
		}
		return new Query(ctx, Table_Name, sql.toString(), null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" Name ")//
			.setParameters(params)//
			.list();
	}
	
	/**
	 * @param ctx
	 * @param trxName
	 * @return  Busca la forma de pago de efectivo
	 */
	public static int getCash(final Properties ctx, final String trxName) {
		return getID(ctx, false, trxName);
	}

	/**
	 * @param ctx
	 * @param trxName
	 * @return Busca la forma de pago de devolucion
	 */
	public static int getDevol(final Properties ctx, final String trxName) {
		return getID(ctx, true, trxName);
	}

	/**
	 * Busca la forma de pago en efectivo
	 * 
	 * @param ctx
	 * @param isDevol
	 * @param trxName
	 * @return
	 */
	public static int getID(final Properties ctx, final boolean isDevol, final String trxName) {
//		sql.append(" SELECT EXME_FormaPago_ID FROM EXME_FormaPago ");
//		sql.append(" WHERE PaymentRule=? AND EsDevol=? AND isActive = 'Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return new Query(ctx, Table_Name, " PaymentRule=? AND EsDevol=? ", trxName)//
			.setParameters(X_EXME_FormaPago.PAYMENTRULE_Cash, DB.TO_STRING(isDevol))//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.firstId();
		//TODO: que pasa si hay mas de una forma de pago con las mismas consideraciones
		// Ejemplo: transferencia electronica, (hospitaria)
	}

	/**
	 * Obtenemos todas las formas de pago relacionadas a un pago agrupadas
	 * si es que se repiten las formas de pago y sumamos sus totales
	 * 
	 * @param ctx
	 * @param trxName
	 * @return arreglo de formas de pago
	 */
	public static MFormaPago[] getLinasPagos(final Properties ctx, final int documento, final String trxName) {
		final ArrayList<MFormaPago> lista = new ArrayList<MFormaPago>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder accesslevel = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"));

		sql.append(" SELECT FormaPago, COALESCE( SUM (Amount),0 ) AS Amount FROM ( ")

		.append(" SELECT fp.EXME_FormaPago_ID AS FormaPago, COALESCE( SUM (C_CashLine.Amount),0 ) AS Amount ")
		.append(" FROM C_CashLine  ")
		.append(" LEFT JOIN EXME_FormaPago fp ON (fp.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID AND fp.isActive = 'Y') ")
		.append(" WHERE C_CashLine.IsActive='Y' ")
		.append(" AND C_CashLine.C_Invoice_ID IS NOT NULL ")
		.append(" AND C_CashLine.C_Invoice_ID = ? ")
		.append(" AND fp.EsDevol = 'N' ")
		.append(" AND fp.Ref_FormaPago_ID IS NULL ")
		.append(accesslevel)
		.append(" GROUP BY fp.EXME_FormaPago_ID ")

		.append(" UNION ")

		.append(" SELECT fp.Ref_FormaPago_ID AS FormaPago, COALESCE( SUM (C_CashLine.Amount),0 ) AS Amount ")
		.append(" FROM C_CashLine ")
		.append(" LEFT JOIN EXME_FormaPago fp ON (fp.EXME_FormaPago_ID = C_CashLine.EXME_FormaPago_ID AND fp.isActive = 'Y') ")
		.append(" WHERE C_CashLine.IsActive='Y' ")
		.append(" AND C_CashLine.C_Invoice_ID IS NOT NULL ")
		.append(" AND C_CashLine.C_Invoice_ID = ? ")
		.append(" AND fp.EsDevol = 'Y' ")
		.append(" AND fp.Ref_FormaPago_ID IS NOT NULL ")
		.append(accesslevel)
		.append(" GROUP BY fp.Ref_FormaPago_ID ")

		.append(" ) as lines ")
		.append(" GROUP BY FormaPago ") ;

		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, documento);
			pstmt.setInt(2, documento);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MFormaPago formaPago = new MFormaPago(ctx, rs.getInt(1), trxName);
				formaPago.setAmount(rs.getBigDecimal(2));
				lista.add(formaPago);
			}
		} catch (final Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		final MFormaPago[] formas = new MFormaPago[lista.size()];
		lista.toArray(formas);
		return formas;
	}

	private BigDecimal	amount	= BigDecimal.ZERO;

	/**
	 * FIXME: solo se llena en {@link #getLinasPagos(Properties, int, String)} pero no hay referencias
	 * @return
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}
	
	//CARD #1299
	/** Nombre forma pago */
	public static String getNameFrom(final Properties ctx, int id) {
		final String name;
		final Object value = Utilerias.getFieldFrom(ctx, X_EXME_FormaPago.Table_Name
				, X_EXME_FormaPago.COLUMNNAME_Name, X_EXME_FormaPago.COLUMNNAME_EXME_FormaPago_ID, id);
		if (value == null) {
			name = StringUtils.EMPTY;
		} else if (value instanceof String) {
			name = (String) value;
		} else {
			name = value.toString();
		}
		return name;
	}

}