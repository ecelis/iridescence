package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.I_C_Payment;
import org.compiere.model.I_I_BankStatement;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.model.X_I_BankStatement;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author arangel
 *
 */
public class CreateBankLines extends SvrProcess {

	/**	Client to be imported to		*/
	private int				p_AD_Client_ID = 0;
	/**	Organization to be imported to	*/
	private int				p_AD_Org_ID = 0;
	/** Default Bank Account			*/
	private final ArrayList<Integer> p_C_BankAccountLst = new ArrayList<Integer>();
	/**	Delete old Imported				*/
	private boolean			p_deleteOldImported = false;
	/** Properties						*/
	private final Properties 		m_ctx = Env.getCtx();

	private final ArrayList<String> checks = new ArrayList<String>();
	private int noInsertLine = 0;
	private int noInsert = 0;

	private final static String SETMSGERROR= "UPDATE I_BankStatement SET I_ErrorMsg = ?, I_IsImported = ? WHERE I_BankStatement_ID = ?";

	@Override
	protected void prepare() {

		final ProcessInfoParameter[] para = getParameter();
		for (final ProcessInfoParameter element : para) {
			final String name = element.getParameterName();
			if (element.getParameter() == null) {
				continue;
			} else if ("AD_Client_ID".equals(name)) {
				p_AD_Client_ID = ((BigDecimal)element.getParameter()).intValue();
			} else if ("AD_Org_ID".equals(name)) {
				p_AD_Org_ID = ((BigDecimal)element.getParameter()).intValue();
			} else if ("DeleteOldImported".equals(name)) {
				p_deleteOldImported = "Y".equals(element.getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception {
		final String clientCheck = " AND AD_Client_ID=" + p_AD_Client_ID;
		prepareTable(clientCheck);
		detectDuplicates(clientCheck);
		final ArrayList<MBankStatement> headers = getHeaders();
		loadChecks();
		getLines(headers);

		//	Set Error to indicator to not imported
		final StringBuffer sql = new StringBuffer ("UPDATE I_BankStatement ")
			.append(" SET I_IsImported='N', Updated=SysDate ")
			.append(" WHERE I_IsImported<>'Y'").append(clientCheck);
		final int no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@C_BankStatement_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noInsertLine), "@C_BankStatementLine_ID@: @Inserted@");
		return "";
	}

	/**
	 * Querys que cambian values por ids ademas de marcar errores
	 * @param clientCheck
	 */
	private void prepareTable(String clientCheck) {
		StringBuffer sql = null;
		int no=0;
		if (p_deleteOldImported){
			sql = new StringBuffer ("DELETE I_BankStatement WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}

		sql = new StringBuffer ("UPDATE I_BankStatement o ")
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '")
				.append(" WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0 ")
				.append(" OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N'))) ")
				.append(" AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0){
				log.warning ("Invalid Org=" + no);
			}
		//Cambiar el value por el id o dejar el id
		sql = new StringBuffer("UPDATE I_BankStatement SET c_bankaccount_id = COALESCE((SELECT C_BankAccount_id FROM C_BankAccount ")
//			.append(" WHERE I_Bankstatement.C_Bankaccount_ID = TO_NUMBER(C_BankAccount.AccountNo, '9999999999999999999999')), c_bankaccount_id) WHERE isactive='Y' ")
			.append("WHERE I_Bankstatement.bankaccountno = C_BankAccount.AccountNo), c_bankaccount_id) WHERE isactive='Y' ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Update accounts=" + no);
		}

		sql = new StringBuffer("UPDATE I_BankStatement SET c_charge_id = (SELECT C_Charge_id FROM C_Charge ")
			.append("WHERE I_Bankstatement.c_charge_id = C_Charge.c_charge_id) WHERE c_charge_id IS NOT NULL")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Update charges=" + no);
		}
		//	Check Payment<->Invoice combination
		sql = new StringBuffer("UPDATE I_BankStatement ")
			.append( "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Payment<->Invoice, ' ")
			.append( "WHERE I_BankStatement_ID IN ")
			.append( "(SELECT I_BankStatement_ID ")
			.append( "FROM I_BankStatement i ")
			.append( " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) ")
			.append( "WHERE i.C_Invoice_ID IS NOT NULL ")
			.append( " AND p.C_Invoice_ID IS NOT NULL ")
			.append( " AND p.C_Invoice_ID<>i.C_Invoice_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.info("Payment<->Invoice Mismatch=" + no);
		}
		prepareTable2(clientCheck);
	}

	/**
	 * Querys que cambian values por ids ademas de marcar errores
	 * @param clientCheck
	 */
	private void prepareTable2(String clientCheck){
		StringBuffer sql = null;
		int no=0;
		//	Check Payment<->BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement ")
			.append( "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Payment<->BPartner, ' ")
			.append( "WHERE I_BankStatement_ID IN ")
			.append( "(SELECT I_BankStatement_ID ")
			.append( "FROM I_BankStatement i" )
			.append( " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) ")
			.append( "WHERE i.C_BPartner_ID IS NOT NULL ")
			.append( " AND p.C_BPartner_ID IS NOT NULL ")
			.append( " AND p.C_BPartner_ID<>i.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.info("Payment<->BPartner Mismatch=" + no);
		}
		//	Check Invoice<->BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement ")
			.append( "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Invoice<->BPartner, ' ")
			.append( "WHERE I_BankStatement_ID IN ")
			.append( "(SELECT I_BankStatement_ID ")
			.append( "FROM I_BankStatement i ")
			.append( " INNER JOIN C_Invoice v ON (i.C_Invoice_ID=v.C_Invoice_ID) ")
			.append( "WHERE i.C_BPartner_ID IS NOT NULL ")
			.append( " AND v.C_BPartner_ID IS NOT NULL ")
			.append( " AND v.C_BPartner_ID<>i.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.info("Invoice<->BPartner Mismatch=" + no);
		}
		//	Check Invoice.BPartner<->Payment.BPartner combination
		sql = new StringBuffer("UPDATE I_BankStatement ")
			.append( "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Invalid Invoice.BPartner<->Payment.BPartner, ' ")
			.append( "WHERE I_BankStatement_ID IN ")
				.append( "(SELECT I_BankStatement_ID ")
				.append( "FROM I_BankStatement i ")
				.append( " INNER JOIN C_Invoice v ON (i.C_Invoice_ID=v.C_Invoice_ID) ")
				.append( " INNER JOIN C_Payment p ON (i.C_Payment_ID=p.C_Payment_ID) ")
				.append( "WHERE p.C_Invoice_ID<>v.C_Invoice_ID ")
				.append( " AND v.C_BPartner_ID<>p.C_BPartner_ID) ")
			.append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.info("Invoice.BPartner<->Payment.BPartner Mismatch=" + no);
		}
	}

	/**
	 * Detecta los duplicados
	 * @param clientCheck
	 */
	private void detectDuplicates(String clientCheck) {
		StringBuffer sql = null;
		int no=0;
//		Detect Duplicates
		 sql = new StringBuffer("SELECT i.I_BankStatement_ID, l.C_BankStatementLine_ID, i.EftTrxID ")
			.append( "FROM I_BankStatement i, C_BankStatement s, C_BankStatementLine l ")
			.append( "WHERE i.I_isImported='N' ")
			.append( "AND s.C_BankStatement_ID=l.C_BankStatement_ID ")
			.append( "AND i.EftTrxID IS NOT NULL AND ")
			//	Concatinate EFT Info
			.append( "(l.EftTrxID||l.EftAmt||l.EftStatementLineDate||l.EftValutaDate||l.EftTrxType||l.EftCurrency||l.EftReference||s.EftStatementReference ")
			.append( "||l.EftCheckNo||l.EftMemo||l.EftPayee||l.EftPayeeAccount) ")
			.append( "= " )
			.append( "(i.EftTrxID||i.EftAmt||i.EftStatementLineDate||i.EftValutaDate||i.EftTrxType||i.EftCurrency||i.EftReference||i.EftStatementReference ")
			.append( "||i.EftCheckNo||i.EftMemo||i.EftPayee||i.EftPayeeAccount) ");

		final StringBuffer updateSql = new StringBuffer("UPDATE I_Bankstatement ")
				.append( "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'Err=Duplicate['||?||']' ")
				.append( "WHERE I_BankStatement_ID=?").append(clientCheck);
		final PreparedStatement pupdt = DB.prepareStatement(updateSql.toString(), get_TrxName());
		PreparedStatement pstmtDuplicates = null;
		ResultSet rs = null;
		no = 0;
		try{
			pstmtDuplicates = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmtDuplicates.executeQuery();
			while (rs.next()){
				final String info = "Line_ID=" + rs.getInt(2)		//	l.C_BankStatementLine_ID
				 + ",EDTTrxID=" + rs.getString(3);			//	i.EftTrxID
				pupdt.setString(1, info);
				pupdt.setInt(2, rs.getInt(1));	//	i.I_BankStatement_ID
				pupdt.executeUpdate();
				no++;
			}
		}catch(final Exception e){
			log.log(Level.SEVERE, "DetectDuplicates " + e.getMessage());
		}finally{
			DB.close(rs, pupdt);
			DB.close(rs, pstmtDuplicates);
		}
		if (no != 0){
			log.info("Duplicates=" + no);
		}
	}

	/**
	 * Obtiene los encabezados para poder insertar las lineas
	 * @return lista de encabezados
	 */
	private ArrayList<MBankStatement> getHeaders(){
		final ArrayList<MBankStatement> records = new ArrayList<MBankStatement>();
		PreparedStatement pstmt = null;
		final StringBuilder sql = new StringBuilder("SELECT i_bankstatement_id, c_bankaccount_id, i_isimported, processed FROM I_BankStatement ")
		.append(" WHERE statementlinedate IS NULL ")
		.append(" AND AD_Client_ID=?");//1
		ResultSet rs = null;

		try{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1,p_AD_Client_ID);
			rs = pstmt.executeQuery();
			while (rs.next()){
				p_C_BankAccountLst.add(rs.getInt(I_I_BankStatement.COLUMNNAME_C_BankAccount_ID));
				final MBankAccount account = MBankAccount.get (m_ctx, rs.getInt(I_I_BankStatement.COLUMNNAME_C_BankAccount_ID));
				final MBankStatement bankstmt = new MBankStatement(account);
				bankstmt.setClientOrg(p_AD_Client_ID, p_AD_Org_ID);//Cambiar por los parametros y no los de la cuenta
				records.add(bankstmt);
				bankstmt.save(get_TrxName());
				updateIBankLine(rs);
				noInsert++;
			}
		}
		catch(final Exception e){
			log.log(Level.SEVERE, sql.toString() + e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
		 return records;
	}

	/**
	 * Obtiene los cheques para saber cuales conciliar
	 * @return lista de encabezados
	 */
	private void loadChecks(){
		PreparedStatement pstmt = null;
		final StringBuilder sql =  new StringBuilder("SELECT p.CheckNo FROM C_Payment_v p ")
			.append(" INNER JOIN C_BankAccount ba ON (p.C_BankAccount_ID=ba.C_BankAccount_ID) ")
			.append(" INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID) ")
			.append(" LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID) ")
			.append(" WHERE p.Processed='Y' AND p.IsReconciled='N' ")
			.append(" AND p.DocStatus IN ('CO','CL','RE','VO') AND p.PayAmt<>0 ")
			.append(" AND p.C_BankAccount_ID=? ")
			.append(" AND NOT EXISTS (SELECT * FROM C_BankStatementLine l ")
			//	Voided Bank Statements have 0 StmtAmt
			.append("WHERE p.C_Payment_ID=l.C_Payment_ID AND l.StmtAmt <> 0) ")
			.append(" AND p.AD_Client_ID=?");//2

		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			for ( int i=0; i < p_C_BankAccountLst.size(); i++ ) {
				pstmt.setInt(1, p_C_BankAccountLst.get(i));
				pstmt.setInt(2, p_AD_Client_ID);
				rs = pstmt.executeQuery();
				while (rs.next()){
					checks.add(rs.getString(I_C_Payment.COLUMNNAME_CheckNo));
				}
			}
		}
		catch(final Exception e){
			log.log(Level.SEVERE, sql.toString() + e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Inserta las lineas de los encabezados
	 * @param headers lista de encabezados
	 */
	private void getLines(final ArrayList<MBankStatement> headers){
		PreparedStatement pstmt = null;
		final StringBuilder sql = new StringBuilder("SELECT i_bankstatement_id, c_bankaccount_id, statementlinedate, referenceno, ")
		.append("linedescription, chargeamt, c_charge_id, c_currency_id, i_isimported, processed  FROM I_BankStatement ")
		.append(" WHERE statementlinedate IS NOT NULL AND C_BankAccount_ID = ? ")//1
		.append(" AND AD_Client_ID=? ")//2
		.append(" AND  i_isimported <> 'Y' AND processed <> 'Y'" );
		ResultSet rs = null;
		try{
			String msg;
 			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			for (final MBankStatement header : headers){
				pstmt.setInt(1, header.getC_BankAccount_ID());
				pstmt.setInt(2, p_AD_Client_ID);
				rs = pstmt.executeQuery();
				while (rs.next()){
					if(checks.contains(rs.getString(I_I_BankStatement.COLUMNNAME_ReferenceNo))){
						final MPayment payment = new MPayment(m_ctx, MPayment.getPaymentID(m_ctx, rs.getString(I_I_BankStatement.COLUMNNAME_ReferenceNo)), null);
							//insertar si las cantidades son iguales
							if(payment==null || payment.getC_Payment_ID()==0){
								//El docNo no coincide
								msg = Msg.translate(m_ctx, "SinDocumento");
								DB.executeUpdate(SETMSGERROR, new Object[]{msg,"E",rs.getInt(I_I_BankStatement.COLUMNNAME_I_BankStatement_ID)}, get_TrxName());
							}else{//payment!=0
								if(payment.getPayAmt().abs().compareTo(rs.getBigDecimal(I_I_BankStatement.COLUMNNAME_ChargeAmt).abs())==0){
									insertBankstmtLine(rs, header, payment);
									updateIBankLine(rs);
								}else{//Cantidades diferentes
									msg = Msg.translate(m_ctx, "DiferentQtys");
									DB.executeUpdate(SETMSGERROR, new Object[]{msg,"E",rs.getInt(I_I_BankStatement.COLUMNNAME_I_BankStatement_ID)}, get_TrxName());
							}
						}
					}else{//El cheque no se encuentra en la lista
						msg = Msg.translate(m_ctx, "NoCheck");
						DB.executeUpdate(SETMSGERROR, new Object[]{msg,"E",rs.getInt(I_I_BankStatement.COLUMNNAME_I_BankStatement_ID)}, get_TrxName());
					}
				}
			}
		}
		catch(final Exception e){
			log.log(Level.SEVERE, sql.toString() + e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Inserta las lineas de cheques
	 * @param rs resulset con la info
	 * @param header encabezado al que pertenece
	 * @throws SQLException
	 */
	private void insertBankstmtLine(ResultSet rs, final MBankStatement header, final MPayment payment) throws SQLException {
		if(checkExist(rs.getBigDecimal(I_I_BankStatement.COLUMNNAME_ChargeAmt), rs.getString(I_I_BankStatement.COLUMNNAME_ReferenceNo))){
			final String msg = Msg.translate(m_ctx, "CheckExist");
			DB.executeUpdate(SETMSGERROR, new Object[]{msg,"Y",rs.getInt(I_I_BankStatement.COLUMNNAME_I_BankStatement_ID)}, get_TrxName());
		}else{
			final MBankStatementLine bankstmtLine = new MBankStatementLine(m_ctx, 0, null);
			bankstmtLine.setC_BankStatement_ID(header.getC_BankStatement_ID());
			bankstmtLine.setDateAcct(rs.getTimestamp(I_I_BankStatement.COLUMNNAME_StatementLineDate));
			bankstmtLine.setReferenceNo(rs.getString(I_I_BankStatement.COLUMNNAME_ReferenceNo));
			if(StringUtils.isEmpty(rs.getString(I_I_BankStatement.COLUMNNAME_LineDescription))){
				bankstmtLine.setDescription(payment.getDescription());
			}else{
				bankstmtLine.setDescription(I_I_BankStatement.COLUMNNAME_LineDescription);
			}
			bankstmtLine.setChargeAmt(BigDecimal.ZERO);
			bankstmtLine.setTrxAmt(rs.getBigDecimal(I_I_BankStatement.COLUMNNAME_ChargeAmt));
			bankstmtLine.setStmtAmt(rs.getBigDecimal(I_I_BankStatement.COLUMNNAME_ChargeAmt));
			bankstmtLine.setValutaDate(rs.getTimestamp(I_I_BankStatement.COLUMNNAME_StatementLineDate));
			bankstmtLine.setStatementLineDate(rs.getTimestamp(I_I_BankStatement.COLUMNNAME_StatementLineDate));
			bankstmtLine.setC_Payment_ID(payment.getC_Payment_ID());
			bankstmtLine.setC_BPartner_ID(payment.getC_BPartner_ID());
			bankstmtLine.setC_Invoice_ID(payment.getC_Invoice_ID());

			if(rs.getInt(I_I_BankStatement.COLUMNNAME_C_Currency_ID)<=0){
				final MAcctSchema schema = new MAcctSchema(m_ctx, MAcctSchema.getClientSchema(p_AD_Client_ID, null), null);
				bankstmtLine.setC_Currency_ID(schema.getC_Currency_ID());
			}else{
				bankstmtLine.setC_Currency_ID(rs.getInt(I_I_BankStatement.COLUMNNAME_C_Currency_ID));
			}
			bankstmtLine.save(get_TrxName());
			noInsertLine++;

		}
	}

	/**
	 * Actualiza el registro de la tabla de importacion
	 * @throws SQLException
	 */
	private  void updateIBankLine(ResultSet rs) throws SQLException{
		final X_I_BankStatement imp = new X_I_BankStatement(m_ctx, rs.getInt(I_I_BankStatement.COLUMNNAME_I_BankStatement_ID), get_TrxName());
		imp.setI_IsImported(true);
		imp.setProcessed(true);
		imp.save();
	}

	/**
	 * Revisa que el cheque no se encuentre actualmente entre las lineas de la misma importacion
	 * @param header
	 * @param chargeamt
	 * @param documentNo
	 * @return
	 */
	private boolean checkExist(BigDecimal chargeamt, String documentNo){
		boolean exist=false;
		PreparedStatement pstmt = null;
		final StringBuilder sql =  new StringBuilder("SELECT C_BankStatementLine_ID FROM C_BankStatementLine ")
			.append(" WHERE TrxAmt = ? AND ReferenceNo = ? ")//1-2
			.append(" AND AD_Client_ID=?");//3

		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setBigDecimal(1, chargeamt);
			pstmt.setString(2, documentNo);
			pstmt.setInt(3, p_AD_Client_ID);

			rs = pstmt.executeQuery();
			if(rs.next()){
				rs.getInt(1);
				exist=true;
			}
		}
		catch(final Exception e){
			log.log(Level.SEVERE, sql.toString() + e.getMessage());
		}finally{
			DB.close(rs, pstmt);
		}
		return exist;
	}
}