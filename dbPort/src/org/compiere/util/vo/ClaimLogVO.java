package org.compiere.util.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEClaimLog;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MRefList;
import org.compiere.model.X_EXME_ClaimLog;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.Env;

public class ClaimLogVO {
	private MEXMECtaPac ctapac = null;
	private MEXMECtaPacExt ctaPacExt = null;
	private MBPartner bpartner = null;
	private Timestamp date_Submitted = null;
	private Timestamp date_Resubmitted = null;
	private String status = null;
	private BigDecimal billAmount = null;
	private String textProcess = null;
	private String valueProcess = null;
	private boolean enableProcess = false;
	private boolean enableResend = false;
	private boolean enableProfessional = false;
	private boolean enableInstitutional = false;
	private boolean isStatement = false;
	private int listReference = 0;
	private Properties ctx = Env.getCtx();
	private String confType = null;
	private int HL7_Dashboard_ID = 0;
	
private BigDecimal calcBillAmount() {
		
		BigDecimal res = BigDecimal.ZERO;
		for (MCtaPacDet det : MCtaPacDet.getCargosByExt(ctx, getCtaPacExt().getEXME_CtaPacExt_ID(), null)) {
			res = res.add(det.getTotalLine());
		}
		/*
		HashMap<String, Double> payments = MPayment.getTotalPayment(ctapac.getEXME_CtaPac_ID(), null, ctx);
		if (payments != null) {
			Double paymentsAmt = payments.get("1");
			if (paymentsAmt != null) {
				res = res.subtract(new BigDecimal(paymentsAmt));
			}
		}*/
		return res;
	}
	
	public ClaimLogVO(Properties ctx, MEXMEClaimLog log) {
		confType = log.getConfType();
		setHL7_Dashboard_ID(log.getHL7_Dashboard_ID());
		setCtapac(new MEXMECtaPac(ctx, log.getEXME_CtaPac_ID(), null));
		setCtaPacExt(new MEXMECtaPacExt(ctx, log.getEXME_CtaPacExt_ID(), null));
		setBpartner(new MBPartner(ctx, log.getC_BPartner_ID(), null));
		setDate_Submitted(log.getDate_Submitted());
		listReference = MEXMEClaimLog.STATUS_AD_Reference_ID;
		this.ctx = ctx;
		setStatus(log.getStatus());
		setBillAmount(calcBillAmount());
		setEnableProfessional(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim.equals(log.getConfType()));
		setEnableInstitutional(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim.equals(log.getConfType()));
		setStatement(X_HL7_MessageConf.CONFTYPE_PIStatement.equals(log.getConfType()));
	}
	
	public MBPartner getBpartner() {
		return bpartner;
	}
	public void setBpartner(MBPartner bpartner) {
		this.bpartner = bpartner;
	}
	public Timestamp getDate_Submitted() {
		return date_Submitted;
	}
	public void setDate_Submitted(Timestamp date_Submitted) {
		this.date_Submitted = date_Submitted;
	}
	public Timestamp getDate_Resubmitted() {
		return date_Resubmitted;
	}
	public void setDate_Resubmitted(Timestamp date_Resubmitted) {
		this.date_Resubmitted = date_Resubmitted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {	
		this.status = MRefList.getListName(ctx, listReference, status);
		
		if (status.equals(X_EXME_ClaimLog.STATUS_A) || status.equals(X_EXME_ClaimLog.STATUS_E)) {
			// Si el claim esta Aceptado no se puede reenviar o continuar con el proceso hasta que reciba pago
			this.enableResend = false;
			this.enableProcess = false;
		} else if (status.equals(X_EXME_ClaimLog.STATUS_P)) {
			// Si el claim recibe pago no se puede reenviar (verificar mas adelante si esto sera correcto) pero dependiendo si tiene mas aseguradoras se puede generar
			// el claim para las siguientes aseguradoras o comenzar el cobro al paciente
			// Si no existe un Insurance mas entonces se continua el proceso sobre el paciente
			this.enableResend = false;
			MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getBillingInsurance(ctx, ctapac.getEXME_CtaPac_ID(), confType);
			
			if (aseg != null) {
				boolean existeAseg = MEXMEPacienteAseg.getBillingInsurance(ctx, ctapac.getEXME_CtaPac_ID(), aseg.getPriority()+1) != null ? true : false;
				if (aseg.getC_BPartner_ID() == bpartner.getC_BPartner_ID()) {
					this.enableProcess = true;
					if (ctapac.getBillingStatus().equals(X_EXME_CtaPac.BILLINGSTATUS_BillToFirstInsurance) && existeAseg) {
						this.textProcess = MRefList.getListName(ctx, X_EXME_CtaPac.BILLINGSTATUS_AD_Reference_ID, X_EXME_CtaPac.BILLINGSTATUS_BillToSecondInsurance);
						this.valueProcess = X_EXME_CtaPac.BILLINGSTATUS_BillToSecondInsurance;
					} else if (ctapac.getBillingStatus().equals(X_EXME_CtaPac.BILLINGSTATUS_BillToSecondInsurance) && existeAseg) {
						this.textProcess = MRefList.getListName(ctx, X_EXME_CtaPac.BILLINGSTATUS_AD_Reference_ID, X_EXME_CtaPac.BILLINGSTATUS_BillToThirdInsurance);
						this.valueProcess = X_EXME_CtaPac.BILLINGSTATUS_BillToThirdInsurance;
					} else {
						this.textProcess = MRefList.getListName(ctx, X_EXME_CtaPac.BILLINGSTATUS_AD_Reference_ID, X_EXME_CtaPac.BILLINGSTATUS_BillToPatient);
						this.valueProcess = X_EXME_CtaPac.BILLINGSTATUS_BillToPatient;
					}				
				}
			}
		} else {
			// Cualquier otro caso (que no ha sido Aceptado y que no ha recibido Pago) indica un rechazo, en cuyo caso se permite el reenvio del claim mas no 
			// el continuar con el proceso hacia la siguiente Insurance
			this.enableResend = ctapac.isGenerated();
			this.enableProcess = false;
		}
	}
	public MEXMECtaPac getCtapac() {
		return ctapac;
	}
	public void setCtapac(MEXMECtaPac ctapac) {
		this.ctapac = ctapac;
	}
	
	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public String getTextProcess() {
		return textProcess;
	}

	public void setTextProcess(String textProcess) {
		this.textProcess = textProcess;
	}

	public boolean isEnableProcess() {
		return enableProcess;
	}

	public void setEnableProcess(boolean enableProcess) {
		this.enableProcess = enableProcess;
	}

	public boolean isEnableResend() {
		return enableResend;
	}

	public void setEnableResend(boolean enableResend) {
		this.enableResend = enableResend;
	}

	public String getValueProcess() {
		return valueProcess;
	}

	public void setValueProcess(String valueProcess) {
		this.valueProcess = valueProcess;
	}
	public void setEnableProfessional(boolean enableProfessional) {
		this.enableProfessional = enableProfessional;
	}
	public void setEnableInstitutional(boolean enableInstitutional) {
		this.enableInstitutional = enableInstitutional;
	}
	public boolean isEnableProfessional() {
		return enableProfessional;
	}
	public boolean isEnableInstitutional() {
		return enableInstitutional;
	}

	public void setStatement(boolean isStatement) {
		this.isStatement = isStatement;
	}

	public boolean isStatement() {
		return isStatement;
	}

	public String getConfType() {
		return confType;
	}

	public void setConfType(String confType) {
		this.confType = confType;
	}

	public void setCtaPacExt(MEXMECtaPacExt ctaPacExt) {
		this.ctaPacExt = ctaPacExt;
	}

	public MEXMECtaPacExt getCtaPacExt() {
		return ctaPacExt;
	}
	public void setHL7_Dashboard_ID(int HL7_Dashboard_ID) {
		this.HL7_Dashboard_ID = HL7_Dashboard_ID;
	}

	public int getHL7_Dashboard_ID() {
		return HL7_Dashboard_ID;
	}
}
