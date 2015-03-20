package com.ecaresoft.acct.xml.auxiliarfolios;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.acct.xml.XmlValidator;
import com.ecaresoft.acct.xml.auxiliarfolios.RepAuxFol.DetAuxFol;
import com.ecaresoft.acct.xml.auxiliarfolios.RepAuxFol.DetAuxFol.ComprNal;
import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * @author asanchez
 *
 */
public class RepAuxFolValidator implements XmlValidator {
	public static final String[] MONTHS = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	public static final String NO_ORDER_REGEX = "[A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2}";
	public static final String RFC_REGEX = "[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?";
	public static final String TRAMITE_REGEX = "[0-9]{10}";
	public static final String[] TYPES = new String[] { "AF", "FC", "DE", "CO" };
	public static final String UUID_REGEX = "[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}";
	private RepAuxFol repAuxFol;
	
	public RepAuxFolValidator(RepAuxFol repAuxFol) {
		this.repAuxFol = repAuxFol;
	}

	@Override
	public ErrorList validate() {
		ErrorList errorList = new ErrorList();

		if (!Pattern.matches(RFC_REGEX, repAuxFol.getRFC())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.rfcInvalido"));
		}
		if (!Arrays.asList(MONTHS).contains(repAuxFol.getMes())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.mesInvalido"));
		}
		if (repAuxFol.getAnio() < 2015 || repAuxFol.getAnio() > 2099) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.anoInvalido"));
		}

		if (!Arrays.asList(TYPES).contains(repAuxFol.getTipoSolicitud())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.solicitudInvalida"));
		}
		if (StringUtils.isNotBlank(repAuxFol.getNumOrden())) {
			if (!Pattern.matches(NO_ORDER_REGEX, repAuxFol.getNumOrden())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noOrdenInvalido"));
			}
		}
		if (StringUtils.isNotBlank(repAuxFol.getNumTramite())) {
			if (!Pattern.matches(TRAMITE_REGEX, repAuxFol.getNumTramite())) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noTramiteInvalido"));
			}
		}
		for (DetAuxFol detFol : repAuxFol.getDetAuxFol()) {
			int lengthPol = StringUtils.length(detFol.getNumUnIdenPol());
			if (lengthPol < 1 || lengthPol > 50) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.noPolizaInvalida", detFol.getNumUnIdenPol()));
			}

			if (detFol.getFecha() == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "egresos.reg.daterequired"));
			}

			for (ComprNal comprNal : detFol.getComprNal()) {
				int lengthUUID = StringUtils.length(comprNal.getUUIDCFDI());

				if (lengthUUID < 1 || lengthUUID > 36 && !Pattern.matches(UUID_REGEX, comprNal.getUUIDCFDI()) || StringUtils.isBlank(comprNal.getUUIDCFDI())) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "msj.uuidInvalido", detFol.getNumUnIdenPol()));
				}
			}
		}
		return  errorList;
	}

}
