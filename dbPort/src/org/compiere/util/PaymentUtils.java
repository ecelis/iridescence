/**
 * 
 */
package org.compiere.util;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCountry;
import org.compiere.model.MPayment;

/**
 * Utilerias para C_Payment
 * 
 * @author lama
 */
public class PaymentUtils {

	/**
	 * Obtiene el siguiente numero de documento de acuerdo a la cuenta bancaria y tipo de pago
	 * 
	 * @param bankAccountID
	 * @param paymentRule
	 * @param tenderType
	 * @return
	 */
	public static String getNextBankAccountDoc(final int bankAccountID, final String paymentRule, final String tenderType) {
		// obtenemos el currentnext
		String docNo = getBankAccountDoc(bankAccountID, paymentRule);
		while (isSequenceUsed(docNo, bankAccountID, tenderType)) {
			updateNextBankAccountDoc(bankAccountID, paymentRule);
			docNo = getBankAccountDoc(bankAccountID, paymentRule);
		}
		return docNo;
	}

	/**
	 * 
	 * @param documentNo
	 * @param bankAccountID
	 * @param tenderType
	 * @return
	 */
	public static boolean isSequenceUsed(final String documentNo, final int bankAccountID, final String tenderType) {
		return DB.getSQLValue(null,//
			"SELECT C_Payment_ID FROM C_Payment WHERE Documentno=? AND C_BankAccount_ID=? AND TenderType=? ",//
			documentNo, bankAccountID, tenderType) > 0;
	}

	/**
	 * 
	 * @param bankAccountID
	 * @param paymentRule
	 */
	public static void updateNextBankAccountDoc(final int bankAccountID, final String paymentRule) {
		DB.executeUpdate("UPDATE C_BankAccountDoc SET CurrentNext=CurrentNext+1 WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y' ",
			new Object[] { bankAccountID, paymentRule }, null);
	}

	/**
	 * 
	 * @param bankAccountID
	 * @param paymentRule
	 * @return
	 */
	public static String getBankAccountDoc(final int bankAccountID, final String paymentRule) {
		return DB.getSQLValueString(null, //
			"SELECT CurrentNext FROM C_BankAccountDoc WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y' ",//
			bankAccountID, paymentRule);
	}
	
	/**
	 * Nombre del reporte de acuerdo a la cuenta bancaria y tipo de pago
	 * @param payment
	 * @return
	 */
	public static String getBankAccountReport(final MPayment payment) {
		final String jasper;
		if (MCountry.isMexico(payment.getCtx())) {
			jasper = DB.getSQLValueString(null,//
				"SELECT classname FROM c_bankaccountdoc WHERE c_bankaccount_id=? AND PaymentRule=? ", //
				payment.getC_BankAccount_ID(), payment.getPaymentRule());
		} else {
			// Reporte para mostrar en USA
			jasper = "PolizaChequeUsa";
		}
		return jasper;
	}
	
	/**
	 * 
	 * @param bankAccountID
	 * @param paymentRule
	 * @return
	 */
	public static String[] getPaymentRules(final int bankAccountID) {
		// validar si todo el detalle esta cancelado ?
		final StringBuilder sql = new StringBuilder();
		// SELECT ARRAY_AGG(distinct Estatus) FROM EXME_ActPacienteInd ;
		sql.append(" SELECT ");
		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
			sql.append(" wm_concat(distinct paymentrule) ");
		} else if (DB.isPostgreSQL()) {
			sql.append(" array_to_string(ARRAY_AGG(distinct paymentrule), ',') ");
		} else {
			sql.append(" null ");
		}
		sql.append(" FROM C_BankAccountDoc ");
		sql.append(" WHERE C_BankAccount_ID=? ");

		final String str = DB.getSQLValueString(null, sql.toString(), bankAccountID);
		return StringUtils.split(str, ",");
	}
}
