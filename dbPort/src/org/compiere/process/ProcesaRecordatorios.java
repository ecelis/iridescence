package org.compiere.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.AgendaMedicaModel;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECDS;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEHistRecordatorio;
import org.compiere.model.MEXMEInterconsulta;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPreOperatorio;
import org.compiere.model.X_EXME_ProgRecordatorio;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Procesa los recordatorios del paciente para su envio
 * 
 * @author Lizeth de la Garza <br>
 *         Modificado por Lorena Lama
 */
public class ProcesaRecordatorios extends SvrProcess {

	private static CLogger	log	= CLogger.getCLogger(ProcesaRecordatorios.class);

	/** Prepare parameters */
	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		String adClientID = "-1";
		String adOrgID = "-1";

		for (final ProcessInfoParameter element : para) {
			if (element.getParameter() == null) {
				break;
			}
			final String paramValue = element.getParameter().toString();
			if (StringUtils.isBlank(paramValue) || !StringUtils.isNumeric(paramValue)) {
				break;
			}
			if ("AD_Client_ID".equals(element.getParameterName())) {
				adClientID = paramValue;
			} else if ("AD_Org_ID".equals(element.getParameterName())) {
				adOrgID = paramValue;
			} else {
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + element.getParameterName());
			}
		}
		getCtx().setProperty("#AD_Client_ID", adClientID);
		getCtx().setProperty("#AD_Org_ID", adOrgID);
	}

	/** Do Patient Reminder Process */
	@Override
	protected String doIt() throws Exception {

		final List<MEXMEHistRecordatorio> hist = MEXMEHistRecordatorio.getRecordatorios(getCtx(), Constantes
				.getSdfFechaHora().format(Env.getCurrentDate()), Env.getAD_Client_ID(getCtx()), Env
				.getAD_Org_ID(getCtx()), null);

		if (hist != null && !hist.isEmpty()) {

			for (final MEXMEHistRecordatorio histRec : hist) {

				if (!histRec.getProg().isSMS() && !histRec.getProg().isEMail()) {
					continue; // Nothing to do...
				}

				final String selectedDoc = histRec.getProg().getTipoRecordatorio();
				final int recordID = histRec.getProg().getRecord_ID();

				if (X_EXME_ProgRecordatorio.TIPORECORDATORIO_EXME_CitaMedica.equals(selectedDoc)) {
					final MEXMECitaMedica cita = new MEXMECitaMedica(getCtx(), recordID, null);
					send(histRec, cita.getMedico(), cita.getMsjEmail(), cita.getMsjSMS());

				} else if (X_EXME_ProgRecordatorio.TIPORECORDATORIO_EXME_ActPacienteIndH.equals(selectedDoc)) {
					final MEXMEActPacienteIndH actPacIndH = new MEXMEActPacienteIndH(getCtx(), recordID, null);
					final MEXMEMedico med = new MEXMEMedico(getCtx(), actPacIndH.getEXME_Medico_ID(), null);
					send(histRec, med, actPacIndH.getMsjEmail(), actPacIndH.getMsjSMS());

				} else if (X_EXME_ProgRecordatorio.TIPORECORDATORIO_EXME_Interconsulta.equals(selectedDoc)) {
					final MEXMEInterconsulta inter = new MEXMEInterconsulta(getCtx(), recordID, null);
					final MEXMEMedico med = new MEXMEMedico(getCtx(), inter.getEXME_Medico_ID(), null);
					send(histRec, med, inter.getMsjEmail(), inter.getMsjSMS());

				} else if (X_EXME_ProgRecordatorio.TIPORECORDATORIO_EXME_PreOperatorio.equals(selectedDoc)) {
					final MEXMEPreOperatorio quiro = new MEXMEPreOperatorio(getCtx(), recordID, null);
					final MEXMEMedico med = new MEXMEMedico(getCtx(), quiro.getEXME_Medico_ID(), null);
					send(histRec, med, quiro.getMsjEmail(), quiro.getMsjSMS());

				} else if (X_EXME_ProgRecordatorio.TIPORECORDATORIO_ClinicalDecison.equals(selectedDoc)) {
					final String msjEmail = getMsg("msg.histRecord.subject") + histRec.getProg().getObservaciones();
					final String msjSMS = getMsg("msg.histRecord.subject") + histRec.getProg().getObservaciones();
					send(histRec, null, msjEmail, msjSMS);

				} else {
					continue; // invalid type
				}

				if (!histRec.save()) {
					log.log(Level.SEVERE, getMsg("error.guardar"));
				}
			}
		}
		return "";
	}

	/**
	 * Regresa el msg del ApplicationResources.properties
	 * 
	 * @param key
	 * @return
	 */
	public String getMsg(final String key) {
		return Utilerias.getMsg(getCtx(), key);
	}

	/**
	 * Envia el mail/sms al paciente, medico o la lista de pacientes de CDS
	 * 
	 * @param histRec
	 *            historico de recordatorio
	 * @param med
	 *            medico
	 * @param msjEmail
	 *            mensaje para enviar por mail
	 * @param msjSMS
	 *            mensaje para enviar por sms
	 */
	public void send(final MEXMEHistRecordatorio histRec, final MEXMEMedico med, final String msjEmail,
			final String msjSMS) {

		// Recordatorio a Medico
		if (histRec.isToMedico()) {
			if (med == null) {
				log.log(Level.SEVERE, "Doctor is mandatory");
			} else {
				send(histRec, msjEmail, msjSMS, med.getCelular(), med.getEMail());
			}
		}
		// Recordatorio a paciente
		else if (histRec.getEXME_Paciente_ID() > 0) {
			send(histRec, msjEmail, msjSMS, histRec.getPac().getTelCelular(), histRec.getPac().getEMail());
		} else {
			// Recordatorio a pacientes / CDS
			// FIXME: que pasa si tiene mas de un paciente y uno de ellos no
			// envia el correo ?? Se marca como enviado ? o se va a estar
			// reenviando a todos los demas hasta que sea correcto.
			final List<Integer> lstCds = MEXMECDS.ruleComplianPatient(histRec.getProg().getRecord_ID(), getCtx());
			if (lstCds == null || lstCds.isEmpty()) {
				log.log(Level.CONFIG, "No CDS for reminders");
			} else {
				int sentMail = 0;
				int sentSMS = 0;
				for (final int patientID : lstCds) {
					final MEXMEPaciente pat = new MEXMEPaciente(getCtx(), patientID, null);
					if (histRec.getProg().isEMail()
							&& AgendaMedicaModel.sendMail(getCtx(), msjEmail, getMsg("msg.histRecord.subject"),
									pat.getEMail())) {
						sentMail++;
					}
					if (histRec.getProg().isSMS()
							&& AgendaMedicaModel.sendSMS(getCtx(), msjSMS, null, pat.getTelCelular())) {
						sentSMS++;
					}
				}
				histRec.setIsEMail(sentMail > 0);
				histRec.setIsSMS(sentSMS > 0);
			}
			// FIN CDS
		}
	}

	/**
	 * Envia el mail o sms segun la configuracion, se asigna si fue enviado o
	 * no, y la fecha de envio
	 * 
	 * @param histRec
	 *            historico de recordatorio
	 * @param msjEmail
	 *            mensaje para enviar por mail
	 * @param msjSMS
	 *            mensaje para enviar por sms
	 * @param cel
	 *            numero de celular
	 * @param mail
	 *            correo electronico
	 */
	public void send(final MEXMEHistRecordatorio histRec, final String msjEmail, final String msjSMS, final String cel,
			final String mail) {
		if (histRec.getProg().isEMail()) {
			histRec.setIsEMail(AgendaMedicaModel.sendMail(getCtx(), msjEmail, getMsg("msg.histRecord.subject"), mail));
		}
		if (histRec.getProg().isSMS()) {
			histRec.setIsSMS(AgendaMedicaModel.sendSMS(getCtx(), msjSMS, null, cel));
		}
	}
}
