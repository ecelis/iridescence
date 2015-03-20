package com.ecaresoft.acct.xml.polizas;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.acct.xml.XmlValidator;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.Cheque;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.CompNal;
import com.ecaresoft.acct.xml.polizas.Polizas.Poliza.Transaccion.Transferencia;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * @author odelarosa
 *
 */
public class PolicyValidator implements XmlValidator {

	public static final String[] MONTHS = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	public static final String NO_ORDER_REGEX = "[A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2}";
	public static final String RFC_REGEX = "[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?";
	public static final String TRAMITE_REGEX = "[0-9]{10}";
	public static final String[] TYPES = new String[] { "AF", "FC", "DE", "CO" };
	private Polizas polizas;

	public PolicyValidator(Polizas polizas) {
		this.polizas = polizas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ecaresoft.acct.xml.XmlValidator#validate()
	 */
	@Override
	public ErrorList validate() {
		ErrorList errorList = new ErrorList();

		if (!Pattern.matches(RFC_REGEX, polizas.getRFC())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
		}

		if (!Arrays.asList(MONTHS).contains(polizas.getMes())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.mesInvalido"));
		}

		if (polizas.getAnio() < 2015 || polizas.getAnio() > 2099) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.anoInvalido"));
		}

		if (!Arrays.asList(TYPES).contains(polizas.getTipoSolicitud())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.solicitudInvalida"));
		}
		if (StringUtils.isNotBlank(polizas.getNumOrden())) {
			if (!Pattern.matches(NO_ORDER_REGEX, polizas.getNumOrden())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noOrdenInvalido"));
			}
		}
		if (StringUtils.isNotBlank(polizas.getNumTramite())) {
			if (!Pattern.matches(TRAMITE_REGEX, polizas.getNumTramite())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noTramiteInvalido"));
			}
		}

		for (Poliza poliza : polizas.getPoliza()) {
			int lengthPol = StringUtils.length(poliza.getNumUnIdenPol());
			if (lengthPol < 1 || lengthPol > 50) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noPolizaInvalida", poliza.getNumUnIdenPol()));
			}

			if (poliza.getFecha() == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "egresos.reg.daterequired"));
			}

			if (StringUtils.isBlank(poliza.getConcepto())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.conceptoRequerido", poliza.getNumUnIdenPol()));
			}

			for (Transaccion transaccion : poliza.getTransaccion()) {
				int lengthNumCta = StringUtils.length(transaccion.getNumCta());

				if (lengthNumCta < 1 || lengthNumCta > 100) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.ctaInvalida", poliza.getNumUnIdenPol()));
				}

				int lengthDesCta = StringUtils.length(transaccion.getDesCta());

				if (lengthDesCta < 1 || lengthDesCta > 100) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.descCtaInvalida", poliza.getNumUnIdenPol()));
				}

				int lengthConc = StringUtils.length(transaccion.getConcepto());

				if (lengthConc < 1 || lengthConc > 200) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.conceptoInvalido", poliza.getNumUnIdenPol()));
				}

				if (transaccion.getDebe().compareTo(BigDecimal.ZERO) < 0) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.debeIncorrecto", poliza.getNumUnIdenPol()));
				}

				if (transaccion.getHaber().compareTo(BigDecimal.ZERO) < 0) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.haberIncorrecto", poliza.getNumUnIdenPol()));
				}
				for (Cheque cheque : transaccion.getCheque()) {

					if (!Pattern.matches(RFC_REGEX, cheque.getRFC())) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
					}
					if (StringUtils.isBlank(cheque.getBanEmisNal()) && StringUtils.isBlank(cheque.getBanEmisExt())) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.sinCodigoBanco" + "" + poliza.getNumUnIdenPol()));
					}
				}
				for (Transferencia transferencia : transaccion.getTransferencia()) {
					if (!Pattern.matches(RFC_REGEX, transferencia.getRFC())) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
					}
				}
			}

		}

		return errorList;
	}

}
