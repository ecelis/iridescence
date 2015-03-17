package org.compiere.util.confHL7;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCountry;
import org.compiere.model.MDHPatientInfo;
import org.compiere.model.MDHPrePaymentLine;
import org.compiere.model.MDashProMujer;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPaqBase;
import org.compiere.model.bean.ResponseProMujer;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Trx;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.ChannelProperties;
import com.mirth.connect.model.MessageObject;

/**
 * Proyecto Pro Mujer
 * http://control.ecaresoft.com/epic/82/
 * Transferencia de información entre NIMBO (eCareSoft) y FIN+
 * 
 * @author abautista
 */
public class MessageSenderProMujer {

	/**
	 * @param reg
	 * @param trxPrefix
	 * @return
	 */
	public static boolean saveDashRecord(final MDashProMujer reg, String trxPrefix) {
		boolean success = false;
		Trx mtrx = null;
		try {
			mtrx = Trx.get(Trx.createTrxName(trxPrefix), true);
			success = reg.save(mtrx.getTrxName());
			if (success) {
				mtrx.commit();
			} else {
				mtrx.rollback();
			}
		} catch (final Exception e) {
			Trx.rollback(mtrx);
		} finally {
			Trx.close(mtrx);
			reg.set_TrxName(null);
		}
		return success;
	}

	public static CLogger		log					= CLogger.getCLogger(MessageObject.class);
	private final Properties	ctx;
	private final Client		mirthClient;
	private final MCountry		country;

	private final String		PREFIX_PROMUJER		= "eCS_ProMujer_";
	private final String		TRXNAME_NEWTRANS	= "saveDashProMujer_";
	private final String		DELIM				= "|";
	private final String		DELIM_LINE			= "\n";
	private final String		REG_PAQ				= "PAQ";
	private final String		REG_SRV				= "SRV";

	/**
	 * 
	 * @param mirthClient
	 * @param C_Country_ID
	 * @throws ClientException
	 */
	public MessageSenderProMujer(final Client mirthClient, final int C_Country_ID) throws ClientException {
		this.ctx = Env.getCtx();
		this.mirthClient = mirthClient;
		this.country = MCountry.get(ctx, C_Country_ID);
	}

	/**
	 * 
	 * @param reg
	 * @return
	 * @throws Exception
	 */
	private MDashProMujer saveDashRecord(final MDashProMujer reg) throws Exception {
		if (!saveDashRecord(reg, TRXNAME_NEWTRANS)) {
			throw new Exception("Error at registering the message to be sended.");
		}
		return reg;
	}

	/**
	 * Interfase con FIN+ TRANSACCION 01<br>
	 * <br>
	 * Consulta de clientes que quieran acceder a un paquete, servicio o componente universal.<br>
	 * Se dispará desde ingreso de paciente:<br>
	 * a) Cada vez que se selecciona un paciente existente en Nimbo (desde la búsqueda)<br>
	 * b) Al capturar los campos Apellido1 y CURP para crear un nuevo paciente.<br>
	 * 
	 * @param apellido1
	 * @param curp
	 * @return ResponseProMujer
	 */
	public ResponseProMujer sendMessageT01(final String apellido1, final String curp) {
		final ResponseProMujer res = new ResponseProMujer();
		MEXMEMejoras conf = MEXMEMejoras.get(ctx);
		if (conf == null) {
			return res;
		}

		MDashProMujer reg = new MDashProMujer(ctx, country, null);
		reg.setProcess(MDashProMujer.TRANSACTION_01);
		reg.setApellido1(apellido1);
		reg.setCurp(curp);

		try {
			reg = saveDashRecord(reg);
			// Prepare message to be sent
			final StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			msg.append(reg.getEXME_DashProMujer_ID()).append(DELIM).append(apellido1);
			msg.append(DELIM).append(curp).append(DELIM).append(MDashProMujer.TRANSACTION_01);

			final boolean success = sendMessage(msg.toString(), DELIM, getChannelName(MDashProMujer.TRANSACTION_01));
			final int regSend = reg.getEXME_DashProMujer_ID();
			if (success) {
				// Ciclo de espera para recibir respuesta
				TimeUnit.SECONDS.sleep(conf.getTimeOutWS() / 3);
				reg = new MDashProMujer(ctx, regSend, null);
				int ntry = 1;
				while (reg.isProcessing() && !reg.isProcessed() && ntry <= conf.getMaxTryWS()) {
					TimeUnit.SECONDS.sleep(conf.getTimeOutWS());
					reg = new MDashProMujer(ctx, regSend, null);
					ntry++;
				}
				if (reg.getTextMsg() != null) {
					res.setData(reg.getTextMsg());
					if (MDashProMujer.ERROR.equals(reg.getStatusCode())) {
						res.setStatusCode(MDashProMujer.ERROR);
					} else {
						res.setStatusCode(MDashProMujer.OK);
					}
				} else {
					res.setStatusCode(MDashProMujer.ERROR);
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, "sendMessageT01", e);
			res.setStatusCode(MDashProMujer.ERROR);
			res.setErrorMsg(e.getMessage());
		}

		return res;
	}

	/**
	 * Interfase con FIN+ TRANSACCION 02<br>
	 * Se dispará desde la pantalla Precios cada vez que se cree un nuevo Pre pago, para uno o más servicios.
	 * 
	 * @param DH_PrePaymentLine_ID
	 * @return ResponseProMujer
	 */
	public ResponseProMujer sendMessageT02(final int DH_PrePaymentLine_ID) {
		final ResponseProMujer res = new ResponseProMujer();
		MEXMEMejoras conf = MEXMEMejoras.get(ctx);
		if (conf == null) {
			return res;
		}
		
		MDashProMujer reg = new MDashProMujer(ctx, country, null);
		reg.setProcess(MDashProMujer.TRANSACTION_02);

		final MDHPrePaymentLine pl = new MDHPrePaymentLine(ctx, DH_PrePaymentLine_ID, null);
		reg.setTransactionCode(pl.getTransactionCode());

		try {
			reg = saveDashRecord(reg);

			final int patientId = pl.getDH_PrePayment().getEXME_Paciente_ID();
//			final MEXMEHistExp exp = MEXMEHistExp.get(ctx, patientId, true, null);
			final MEXMEPaciente pac = new MEXMEPaciente(ctx, patientId, null);
			final MDHPatientInfo pinfo = MDHPatientInfo.getFrom(ctx, patientId, null);

			// Prepare message to be sent
			final StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			msg.append(StringUtils.trimToEmpty(pac.getExpediente())).append(DELIM); // CodUsuario ** se cambio, para que el expediente (hist_exp) fuera el DI ** aarmendaiz
			msg.append(pac.getName()).append(StringUtils.isNotBlank(pac.getNombre2()) ? " " + pac.getNombre2() : "").append(DELIM); // Nombres
			msg.append(pac.getApellido1()).append(DELIM); // Paterno
			msg.append(pac.getCurp()).append(DELIM); // DI
			msg.append(pinfo == null ? MDHPatientInfo.DOCTYPE_National_ID : pinfo.getDocType()).append(DELIM_LINE); // CodDocIden
			DecimalFormat decFormat = new DecimalFormat("#.00");
			if (pl.getEXME_PaqBase_Version_ID() > 0) { // Paquete
				final MEXMEPaqBase paq = new MEXMEPaqBase(ctx, pl.getEXME_PaqBase_Version().getEXME_PaqBase_ID(), null);
				msg.append(REG_PAQ).append(DELIM).append(StringUtils.right(paq.getValue(), 2)).append(DELIM); // CodPaquete
				msg.append(paq.getName()).append(DELIM); // NombrePaquete
//				msg.append(decFormat.format(pl.getEXME_PaqBase_Version().getTotalAmt().doubleValue())).append(DELIM_LINE); // Monto del paquete
				msg.append(decFormat.format(pl.getLineTotalAmt().doubleValue())).append(DELIM_LINE); // Monto del paquete
			} else { // Servicio
				msg.append(REG_SRV).append(DELIM).append(StringUtils.right(pl.getM_Product().getValue(), 3)).append(DELIM); // CodServicio
				msg.append(pl.getM_Product().getName()).append(DELIM); // NombreServicio
				msg.append(decFormat.format(pl.getLineTotalAmt().doubleValue())).append(DELIM_LINE); // MontoServicio
			}
			msg.append(Constantes.getSdfFecha_CCD(ctx).format(pl.getDH_PrePayment().getDateTrx())).append(DELIM);// FechaHora
			msg.append(pl.getTransactionCode()).append(DELIM_LINE);

			final boolean success = sendMessage(msg.toString(), DELIM, getChannelName(MDashProMujer.TRANSACTION_02));

			final int regSend = reg.getEXME_DashProMujer_ID();
			if (success) {
				// Ciclo de espera para recibir respuesta
				TimeUnit.SECONDS.sleep(conf.getTimeOutWS() / 3);
				reg = new MDashProMujer(ctx, regSend, null);
				int ntry = 1;
				while (reg.isProcessing() && !reg.isProcessed() && ntry <= conf.getMaxTryWS()) {
					TimeUnit.SECONDS.sleep(conf.getTimeOutWS());
					reg = new MDashProMujer(ctx, regSend, null);
					ntry++;
				}
				res.setData(reg.getTextMsg());
				if (MDashProMujer.ERROR.equals(reg.getStatusCode())) {
					res.setStatusCode(MDashProMujer.ERROR);
				} else {
					res.setStatusCode(MDashProMujer.OK);
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, "sendMessageT02", e);
			res.setStatusCode(MDashProMujer.ERROR);
			res.setErrorMsg(e.getMessage());
		}
		return res;
	}

	/**
	 * 
	 * @param msg
	 * @param delim
	 * @param channelName
	 * @return
	 */
	private boolean sendMessage(final String msg, final String delim, final String channelName) {
		boolean res = Boolean.FALSE;

		try {

			final Channel channel = getChannel(channelName);

			final MessageObject messageObject = new MessageObject();
			messageObject.setId(mirthClient.getGuid());
			messageObject.setServerId(mirthClient.getServerId());
			messageObject.setChannelId(channel.getId());

			messageObject.setRawDataProtocol(channel.getSourceConnector().getTransformer().getInboundProtocol());
			messageObject.setDateCreated(Calendar.getInstance());
			messageObject.setConnectorName("Source");

			messageObject.setEncrypted(Boolean.valueOf(channel.getProperties().getProperty(ChannelProperties.ENCRYPT_DATA)).booleanValue());

			if (msg != null) {
				messageObject.setRawData(decryptMessage(msg, delim));

				mirthClient.processMessage(messageObject);

				res = Boolean.TRUE;
			}

		} catch (final Exception e) {
			log.log(Level.SEVERE, "Error: Exception while sending HL7 message", e);
		}
		return res;
	}

	/**
	 * 
	 * @param encryptedMessage
	 * @param delim
	 * @return
	 */
	private String decryptMessage(final String encryptedMessage, final String delim) {

		final StringBuilder decryptedMessage = new StringBuilder("");

		final StringTokenizer t = new StringTokenizer(encryptedMessage, delim + "\n\r^&~ ", true);

		while (t.hasMoreTokens()) {
			final String value = t.nextToken();
			if (StringUtils.isNotBlank(value)) {
				decryptedMessage.append(SecureEngine.decrypt(value));
			} else {
				decryptedMessage.append(value);
			}
		}
		return decryptedMessage.toString();
	}

	/**
	 * 
	 * @param channelName
	 * @return
	 * @throws ClientException
	 */
	private Channel getChannel(final String channelName) throws ClientException {

		Channel channel = new Channel();
		channel.setName(channelName);

		final List<Channel> channels = mirthClient.getChannel(channel);

		if (channels.isEmpty()) {
			channel.setId(mirthClient.getGuid());
			channel.setVersion(mirthClient.getVersion());
			// por cualquer motivo se asigna el nombre definido en el
			// registro actual
			channel.setName(channelName);
			return channel;
		} else {
			channel = channels.get(0);// el primero y unico?
		}
		return channel;
	}

	/**
	 * 
	 * @param channelTrans
	 * @return
	 */
	private String getChannelName(final String channelTrans) {
		return PREFIX_PROMUJER + channelTrans + "_" + country.getCountryCode();
	}
}
