/**
 * 
 */
package org.compiere.model.bean;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMECtaPacPag;
import org.compiere.model.MFormaPago;
import org.compiere.util.Env;

/**
 * @author tperez
 *
 */
public class CtaPacPag {
	public CtaPacPag(){
		super();
	}
	private MFormaPago mFormaPago = null;
	private MEXMECtaPacExt mExtension = null; 
	private List<MEXMECtaPacPag> lstPayments = null;
	private List<MEXMECtaPacPag> lstPaymentsAllocated = null;
	private BigDecimal amount = Env.ZERO;
	private BigDecimal amtAllocated = Env.ZERO;

	public List<MEXMECtaPacPag> getLstPaymentsAllocated() {
		return lstPaymentsAllocated;
	}
	public void setLstPaymentsAllocated(List<MEXMECtaPacPag> lstPaymentsAllocated) {
		this.lstPaymentsAllocated = lstPaymentsAllocated;
	}
	public BigDecimal getAmtAllocated() {
		return amtAllocated;
	}
	public void setAmtAllocated(BigDecimal amtAllocated) {
		this.amtAllocated = amtAllocated;
	}
	public MFormaPago getmFormaPago() {
		return mFormaPago;
	}
	public void setmFormaPago(MFormaPago mFormaPago) {
		this.mFormaPago = mFormaPago;
	}
	public MEXMECtaPacExt getmExtension() {
		return mExtension;
	}
	public void setmExtension(MEXMECtaPacExt mExtension) {
		this.mExtension = mExtension;
	}
	public List<MEXMECtaPacPag> getLstPayments() {
		return lstPayments;
	}
	public void setLstPayments(List<MEXMECtaPacPag> lstPayments) {
		this.lstPayments = lstPayments;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}