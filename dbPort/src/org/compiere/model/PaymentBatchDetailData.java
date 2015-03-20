package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;

/**
 * @author arangel
 *
 */
public class PaymentBatchDetailData {
	
	private static CLogger		slog= CLogger.getCLogger(PaymentBatchDetailData.class);
	
	/**
	 * Report Cashiering Payment Detail
	 * 
	 * @param filters
	 *            filtros del query
	 * @param params
	 *            parametros de las fechas
	 * @param order
	 *            order by en el query
	 * @param name
	 *            Nombre de la clase para distinguir que query realizar
	 * @param totals
	 *            es el query normal o el resumen
	 * @param abrev
	 *            Alias de la tabla Fc(financial class) o Pt (patient type)
	 * @return lista de datos
	 */
	public static ArrayList<PaymentBatchDetailData> getCashieringPayment(StringBuilder filters, 
			List<Object> params, Object order, String name, boolean totals, String abrev, String supportBilling) {
		ArrayList<PaymentBatchDetailData> values = new ArrayList<PaymentBatchDetailData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder reporte;
		String payment = "WPaymentBatchDetails";
		int j = 1;
		try {
			if (StringUtils.isNotEmpty(name) && name.contains(payment)) {
				reporte = new StringBuilder(SQLFinancialRpts.getPaymentsBatchDetail(supportBilling, Boolean.TRUE));

			} else {
				reporte = new StringBuilder(SQLFinancialRpts.cashieringPaymentDetails(filters.toString()));
			}
			if (order != null && StringUtils.isNotEmpty(order.toString()) && !totals) {
				reporte.append("ORDER BY ").append(order);
			}

			pstm = DB.prepareStatement(reporte.toString(), null);
			if (!totals && name.contains(payment)) {

				pstm.setString(j++, Constantes.REG_NOT_ACTIVE);
				pstm.setString(j++, Constantes.REG_ACTIVE);
				pstm.setInt(j++, MEXMEClaimPaymentH.BILLINGTYPE_AD_Reference_ID);

			}
			if (name.contains(payment)) {

				pstm.setString(j++, Constantes.REG_ACTIVE);
				pstm.setString(j++, Constantes.REG_NOT_ACTIVE);

				if (!totals) {
					pstm.setInt(j++, MEXMEClaimPayment.TRANSACTIONTYPE_AD_Reference_ID);
				}
			}
			pstm.setInt(j++, Env.getAD_Org_ID(Env.getCtx()));
			pstm.setInt(j++, Env.getAD_Client_ID(Env.getCtx()));
			if (StringUtils.isNotEmpty(filters.toString()) && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					// Solo filtros de fechas
					pstm.setTimestamp((i + j), (java.sql.Timestamp) params.get(i));
				}
			}
			rs = pstm.executeQuery();

			if (StringUtils.isNotEmpty(name) && name.contains(payment)) {
				values.addAll(createListPaymentBatchDetail(rs, totals));
			} else {
				values.addAll(PaymentBatchDetailData.createListCashieringPaymentDetails(rs, totals));
			}
			if (MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)) {
				Collections.sort(values, new Comparator<PaymentBatchDetailData>() {

					public int compare(PaymentBatchDetailData o1, PaymentBatchDetailData o2) {

						return o1.getPacName().compareTo(o2.getPacName());
					}
				});
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);

		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}
	
	/**
	 * Report Payment Batch Detail
	 * 
	 * @param rs
	 * @param totals
	 *            es el query normal o el resumen
	 * @return lista de datos
	 */
	public static ArrayList<PaymentBatchDetailData> createListPaymentBatchDetail(ResultSet rs, boolean totals) {
		ArrayList<PaymentBatchDetailData> lst = new ArrayList<PaymentBatchDetailData>();
		try {
			if (totals) {
				while (rs.next()) {
					PaymentBatchDetailData rValues = new PaymentBatchDetailData();
					rValues.setName(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));// Tipo
																						// paciente
																						// y
																						// clase
																						// financiera
					rValues.setChargeP(rs.getBigDecimal("CHARGEPR"));
					rValues.setChargeI(rs.getBigDecimal("CHARGEIN"));
					rValues.setCounter(rs.getInt("COUNTER"));
					lst.add(rValues);
				}
			} else {
				while (rs.next()) {
					PaymentBatchDetailData rValues = new PaymentBatchDetailData();
					rValues.setEncounter(rs.getString("ENCOUNTER"));
					rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
					rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
					rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
					rValues.setFinanciClass(rs.getString("FINANCIALCLASS"));
					rValues.setClaimType(rs.getString("CLAIMTYPE"));
					rValues.setChargesP(rs.getLong("CHARGEPR"));
					rValues.setChargesI(rs.getLong("CHARGEIN"));
					rValues.setPayDate(rs.getDate(MEXMEClaimPaymentH.COLUMNNAME_PaymentDate));
					rValues.setCode(rs.getString("TRANCODE"));
					rValues.setAmount(rs.getBigDecimal("AMT"));
					rValues.setBatch(rs.getString(MEXMEClaimPaymentH.COLUMNNAME_DocumentNo));
					lst.add(rValues);
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	/**
	 * Report Payment Batch Detail
	 * 
	 * @param filters
	 *            filtros del query
	 * @param params
	 *            parametros de las fechas
	 * @param order
	 *            order by en el query
	 * @param name
	 *            Nombre de la clase para distinguir que query realizar
	 * @param totals
	 *            es el query normal o el resumen
	 * @param abrev
	 *            Alias de la tabla Fc(financial class) o Pt (patient type)
	 * @return lista de datos
	 */
//	public static ArrayList<PaymentBatchDetailData> getPaymentBatchDetail(StringBuilder filters, 
//			List<Object> params, String name, String supportBilling) {
//		ArrayList<PaymentBatchDetailData> values = new ArrayList<PaymentBatchDetailData>();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		StringBuilder reporte;
//		int j = 1;
//		try {
//			reporte = new StringBuilder(SQLFinancialRpts.getPaymentsBatchDetail(supportBilling, Boolean.TRUE,filters));
//
//			} else {
//				reporte = new StringBuilder(SQLFinancialRpts.cashieringPaymentDetails(totals, abrev, filters));
//			}
//			if (order != null && StringUtils.isNotEmpty(order.toString()) && !totals) {
//				reporte.append("ORDER BY ").append(order);
//			}
//
//			pstm = DB.prepareStatement(reporte.toString(), null);
//			if (!totals && name.contains(payment)) {
//
//				pstm.setString(j++, Constantes.REG_NOT_ACTIVE);
//				pstm.setString(j++, Constantes.REG_ACTIVE);
//				pstm.setInt(j++, BILLINGTYPE_AD_Reference_ID);
//
//			}
//			if (name.contains(payment)) {
//
//				pstm.setString(j++, Constantes.REG_ACTIVE);
//				pstm.setString(j++, Constantes.REG_NOT_ACTIVE);
//
//				if (!totals) {
//					pstm.setInt(j++, MEXMEClaimPayment.TRANSACTIONTYPE_AD_Reference_ID);
//				}
//			}
//			pstm.setInt(j++, Env.getAD_Org_ID(Env.getCtx()));
//			pstm.setInt(j++, Env.getAD_Client_ID(Env.getCtx()));
//			if (StringUtils.isNotEmpty(filters.toString()) && !params.isEmpty()) {
//				for (int i = 0; i < params.size(); i++) {
//					// Solo filtros de fechas
//					pstm.setTimestamp((i + j), (java.sql.Timestamp) params.get(i));
//				}
//			}
//			rs = pstm.executeQuery();
//
//			if (StringUtils.isNotEmpty(name) && name.contains(payment)) {
//				values.addAll(createListPaymentBatchDetail(rs, totals));
//			} else {
//				values.addAll(createListCashieringPaymentDetails(rs, totals));
//			}
//			if (MEXMEPaciente.COLUMNNAME_Nombre_Pac.equals(order)) {
//				Collections.sort(values, new Comparator<PaymentBatchDetailData>() {
//
//					public int compare(PaymentBatchDetailData o1, PaymentBatchDetailData o2) {
//
//						return o1.getPacName().compareTo(o2.getPacName());
//					}
//				});
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage(), e);
//
//		} finally {
//			DB.close(rs, pstm);
//		}
//		return values;
//	}
	
	/**
	 * Report Cashiering Payment DetailS
	 * 
	 * @param rs
	 * @param totals
	 *            es el query normal o el resumen
	 * @return lista de datos
	 */
	public static ArrayList<PaymentBatchDetailData> createListCashieringPaymentDetails(ResultSet rs, boolean totals) {
		ArrayList<PaymentBatchDetailData> lst = new ArrayList<PaymentBatchDetailData>();
		try {
			if (totals) {
				while (rs.next()) {
					PaymentBatchDetailData rValues = new PaymentBatchDetailData();
					rValues.setName(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));// Tipo
																						// paciente
																						// y
																						// clase
																						// financiera
					rValues.setCharge(rs.getBigDecimal("CHARGE"));
					lst.add(rValues);
				}
			} else {
				while (rs.next()) {
					PaymentBatchDetailData rValues = new PaymentBatchDetailData();
					rValues.setEncounter(rs.getString("ENCOUNTER"));
					rValues.setPacName(SecureEngine.decrypt(rs.getString(MEXMEPaciente.COLUMNNAME_Nombre_Pac)));
					rValues.setDcDate(rs.getDate(MEXMECtaPac.COLUMNNAME_FechaAlta));
					rValues.setPacType(rs.getString(MEXMETipoPaciente.COLUMNNAME_Name));
					rValues.setFinanciClass(rs.getString("FINANCIALCLASS"));
					rValues.setPayDate(rs.getDate(MPayment.COLUMNNAME_DateTrx));
					rValues.setCode(rs.getString("TransCode"));
					rValues.setAmount(rs.getBigDecimal(MPayment.COLUMNNAME_PayAmt));
					rValues.setUser(rs.getString("USUARIO"));
					lst.add(rValues);
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
		}
		return lst;
	}
	
	private String encounter;
	private String pacName;
	private String pacType;
	private Date dcDate;	
	private String financiClass;
	private String claimType;
	private long chargesP;
	private long chargesI;
	private Date payDate;
	private String code;
	private BigDecimal amount;
	private String batch;
	private String user;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEncounter() {
		return encounter;
	}
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	public String getPacName() {
		return pacName;
	}
	public void setPacName(String pacName) {
		this.pacName = pacName;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}
	public Date getDcDate() {
		return dcDate;
	}
	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}
	public String getFinanciClass() {
		return financiClass;
	}
	public void setFinanciClass(String financiClass) {
		this.financiClass = financiClass;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}	
	public long getChargesP() {
		return chargesP;
	}
	public void setChargesP(long chargesP) {
		this.chargesP = chargesP;
	}
	public long getChargesI() {
		return chargesI;
	}
	public void setChargesI(long chargesI) {
		this.chargesI = chargesI;
	}

	private String name;
	private int counter;
	private BigDecimal charge;
	private BigDecimal chargeI;
	private BigDecimal chargeP;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public BigDecimal getChargeI() {
		return chargeI;
	}
	public void setChargeI(BigDecimal chargeI) {
		this.chargeI = chargeI;
	}
	public BigDecimal getChargeP() {
		return chargeP;
	}
	public void setChargeP(BigDecimal chargeP) {
		this.chargeP = chargeP;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	
}
