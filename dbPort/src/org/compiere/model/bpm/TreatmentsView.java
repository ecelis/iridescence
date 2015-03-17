package org.compiere.model.bpm;

import static org.compiere.util.Utilerias.getMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMESalidaGasto;
import org.compiere.model.MEXMESalidaGastoLine;
import org.compiere.model.MEXMETratamientosProductos;
import org.compiere.model.MEXMEUserOrgTrx;
import org.compiere.model.MLocator;
import org.compiere.model.MRefList;
import org.compiere.model.MUser;
import org.compiere.model.ModelError;
import org.compiere.model.MovementLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Salida al gastos
 * 
 * @author Expert
 * 
 */
public class TreatmentsView {
	/** */
	private Properties ctx = null;
	/** */
	private int docTypeID = 0;
	/** */
	private int almacenID = 0;
	/** */
	private String areaDepto = null;
	/** */
	private String description = null;
	/** */
	private MEXMECtaPac ctapac = null;
	/** */
	private List<ModelError> errores = new ArrayList<ModelError>();
	/** */
	private int tratDetalleID = 0;
	/** */
	private int tratSesionID = 0;

	/**
	 * 
	 * @param ctx
	 * @param ctapac
	 * @param docTypeID
	 * @param almacenID
	 * @param AreaDepto
	 * @param Description
	 * @param EXME_Tratamientos_Detalle_ID
	 */
	public TreatmentsView(Properties ctx, MEXMECtaPac ctapac, int docTypeID,
			int almacenID, String AreaDepto, String Description,
			int EXME_Tratamientos_Detalle_ID, int EXME_Tratamientos_Sesion_ID) {
		super();

		this.ctx = ctx;
		this.ctapac = ctapac;
		this.docTypeID = docTypeID;
		this.almacenID = almacenID;
		this.areaDepto = AreaDepto;
		this.description = Description;
		this.tratDetalleID = EXME_Tratamientos_Detalle_ID;
		this.tratSesionID = EXME_Tratamientos_Sesion_ID;

		begin();
	}

	// int docTypeID,
	// int almacenID,
	// List<MovementLine> LstProductos,
	// int extension,
	// int OrgTrxID,
	// int Extension,
	// String AreaDepto,
	// int UserIDSol, // los datos del usuario que solicita
	// String Description,
	// int ActivityID,
	// int CampaignID,
	// int DocTypeID,
	// int ProjectID

	private void begin() {

		List<MovementLine> lstProductos = new ArrayList<MovementLine>();
		List<MEXMETratamientosProductos> lstTratProd = MEXMETratamientosProductos
				.getTratamientosDetalle(ctx, tratDetalleID, null, null);

		for (int i = 0; i < lstTratProd.size(); i++) {
			MEXMETratamientosProductos tp = lstTratProd.get(i);
			MovementLine toAdd = new MovementLine();

			toAdd.setProductID(tp.getM_Product_ID());
			toAdd.setOriginalQty(tp.getQty().longValue());
			toAdd.setProdCosto(new BigDecimal(1));
			toAdd.setDescription(tp.getDescription());
			//toAdd.setValue(String.valueOf(tp.getProducto().getC_Charge_ID()));// cargo

			lstProductos.add(toAdd);
		}

		saveNew(docTypeID, almacenID, lstProductos, ctapac
				.getEXME_CtaPacExt_ID(), Env.getAD_OrgTrx_ID(ctx), areaDepto,
				Env.getAD_User_ID(ctx), // los datos del usuario que solicita
				description, ctapac.getC_Activity_ID(), ctapac
						.getC_Campaign_ID(), ctapac.getC_Project_ID());

	}

	/**
	 * 
	 * @param docTypeID
	 * @param almacenID
	 * @param LstProductos
	 * @param extension
	 * @param OrgTrxID
	 * @param AreaDepto
	 * @param UserIDSol
	 * @param Description
	 * @param ActivityID
	 * @param CampaignID
	 * @param ProjectID
	 */
	private void saveNew(int docTypeID, int almacenID,
			List<MovementLine> LstProductos, int extension, int OrgTrxID,
			String AreaDepto, int UserIDSol, // los datos del usuario que
			// solicita
			String Description, int ActivityID, int CampaignID, int ProjectID) {

		// idioma logeado
		// Locale locale = Utilerias.getLocale(ctx);

		// validamos el tipo de documento
		if (docTypeID == 0)
			errores.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.traspasos.requerido", getMessage(ctx, null,
							"traspasos.docType")));

		// validamos el almacen resurtido
		if (almacenID == 0)
			errores.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.traspasos.requerido", getMessage(ctx, null,
							"traspasos.locator")));

		// validar que exista al menos una linea
		if (LstProductos == null || LstProductos.size() <= 0)
			errores.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.traspasos.lines"));

		// validar la extension, mayor a cero
		if (extension == 0)
			errores.add(new ModelError(ModelError.TIPOERROR_Error,
					"salidaGasto.error.noExt"));

		// mandar llamar a error
		if (!errores.isEmpty()) {
			return;
		}

		Trx m_trx = null;
		m_trx = Trx.get(Trx.createTrxName("SG"), true);
		// forma.setTrxName(m_trx.getTrxName());
		String trxName = m_trx != null ? m_trx.getTrxName() : null; // LRHU

		try {

			/*
			 * if (forma.getDocumentNo() == null ||
			 * forma.getDocumentNo().trim().equals("")) { // asignamos el
			 * siguiente numero de documento
			 * //forma.setDocumentNo(DB.getDocumentNo(ctx, "EXME_SalidaGasto",
			 * forma.getTrxName()));
			 * forma.setDocumentNo(MSequence.getDocumentNo(
			 * (int)forma.getDocTypeID(), null));//LRHU se cambia
			 * forma.getTrxName() por la variable local trxName }
			 */
			MUser user = MUser.get(ctx);

			// insertamos el encabezado de movimiento
			MEXMESalidaGasto head = new MEXMESalidaGasto(ctx, 0, trxName);
			// (int) MovementID, trxName);//LRHU se cambia forma.getTrxName()
			// por la variable local trxName

			head.setAD_OrgTrx_ID((int) OrgTrxID);

			// head.setDocumentNo(forma.getDocumentNo()); automatico
			String estatus = null;

			boolean autorizador = false;
			if (MEXMEUserOrgTrx.getFromUser(ctx, user.getAD_User_ID(), null).length > 0)
				autorizador = true;

			// si el usuario es autorizador, autorizar
			if (autorizador)
				estatus = MEXMESalidaGasto.DOCSTATUS_Approved;
			else
				estatus = MEXMESalidaGasto.DOCSTATUS_Drafted;

			head.setDocStatus(estatus);
			head.setDocAction(MEXMESalidaGasto.DOCACTION_Complete);
			head.setExtension(extension);
			head.setAreaDepto(AreaDepto);
			head.setM_Warehouse_ID((int) almacenID);
			if (head.getEXME_SalidaGasto_ID() <= 0)
				head.setMovementDate(DB.getTimestampForOrg(ctx));
			// los datos del usuario que solicita
			head.setUser1_ID((int) UserIDSol);
			head.setDescription(Description);
			// el esquema contable
			head.setC_Activity_ID(ActivityID);
			head.setC_Campaign_ID(CampaignID);
			head.setC_DocType_ID(docTypeID);
			head.setC_Project_ID(ProjectID);
			if (tratSesionID > 0)
				head.setEXME_Tratamientos_Sesion_ID(tratSesionID);
			if (!head.save(trxName)) {// LRHU
				if (m_trx != null) {
					m_trx.rollback();
					m_trx.close();
					m_trx = null;
					trxName = null; // LRHU
				}
				errores.add(new ModelError(ModelError.TIPOERROR_Error,
						"salidaGasto.error.noSave"));
				return;
			}

			int n = 10;

			// insertamos el detalle
			for (int i = 0; i < LstProductos.size(); i++) {
				MovementLine lineas = (MovementLine) LstProductos.get(i);

				MEXMESalidaGastoLine detail = new MEXMESalidaGastoLine(ctx, 0,
						trxName);// LRHU se cambia forma.getTrxName() por la
				// variable local trxName

				// salida al gasto
				detail.setEXME_SalidaGasto_ID(head.getEXME_SalidaGasto_ID());
				detail.setLine(n);
				detail.setM_Product_ID((int) lineas.getProductID());
				detail.setOriginalQty(BigDecimal.valueOf(lineas
						.getOriginalQty()));
				detail.setCostAverage(lineas.getProdCosto());

				detail.setC_Charge_ID(Integer.parseInt(lineas.getValue()));// cargo
				detail.setDescription(lineas.getDescripLine());
				detail
						.setInventoryType(MEXMESalidaGastoLine.INVENTORYTYPE_ChargeAccount);
				detail.setM_Locator_ID(MLocator.getLocatorID(ctx, almacenID,
						trxName));// LRHU se cambia forma.getTrxName() por la
				// variable local trxName

				if (!detail.save(trxName)) {
					if (m_trx != null) {
						m_trx.rollback();
						m_trx.close();
						m_trx = null;
						trxName = null; // LRHU
					}
					errores.add(new ModelError(ModelError.TIPOERROR_Error,
							"salidaGasto.error.noSaveDet"));
					return;
				}
				((MovementLine) LstProductos.get(i)).setMovementLineID(detail
						.getEXME_SalidaGastoLine_ID());
				n += 10;
			}

			// int MovementID = head.getEXME_SalidaGasto_ID();

			// hacemos commit
			if (m_trx != null) {
				m_trx.commit();
				m_trx.close();
				m_trx = null;
				trxName = null; // LRHU
			}
			// mostramos el mensaje de exito
			// ActionMessages messages = new ActionMessages();
			// si es supervisor
			if (autorizador) {
				errores.add(new ModelError(ModelError.TIPOERROR_Informativo,
						"traspasos.msg.insert", head.getDocumentNo()));
			} else {
				estatus = MRefList.getListName(ctx,
						MEXMESalidaGasto.DOCSTATUS_AD_Reference_ID, head
								.getDocStatus());
				errores.add(new ModelError(ModelError.TIPOERROR_Informativo,
						"traspasos.msg.insert", head.getDocumentNo()));

				// creamos el mail si el usuario no es autorizador
				// boolean email = false;

				// errores.add(new ModelError(ModelError.TIPOERROR_Informativo,
				// "salidaGasto.email.msj",head.getDocumentNo(),user.getName(),Constantes.getSdfFecha().format(head.getMovementDate()));
				// errores.add(new ModelError(ModelError.TIPOERROR_Informativo,
				// "salidaGasto.email.subj");

				// email = sendMail(ctx,head.getAD_OrgTrx_ID(),msg,subject);
				// if(email){
				// errores.add(new ModelError(ModelError.TIPOERROR_Error,
				// "salidaGasto.email.success"));
				// }
			}

		} catch (Exception e) {

			errores.add(new ModelError(ModelError.TIPOERROR_Excepcion,
					"salidaGasto.error.save", e.getMessage()));

		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
				m_trx = null;
				trxName = null; // LRHU
			}
		}

		return;
	}

	public List<ModelError> getErrores() {
		return errores;
	}

	public void setErrores(List<ModelError> errores) {
		this.errores = errores;
	}

}
/**
 * Enviamos el correo de aviso de solicitudes pendientes de autorizar a los
 * autorizadores correspondientes al a orgtrx.
 * 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * 
 *         public boolean sendMail(Properties ctx, int AD_OrgTrx_ID, String
 *         message, String subject ) {
 * 
 *         EMail email = null; MClient client = null; MUser user = null; String
 *         requestMail = null;
 * 
 *         try {
 * 
 *         if(AD_OrgTrx_ID == 0 || message == null || subject == null) return
 *         false;
 * 
 *         //el cliente, para los datos del servidor client = MClient.get (ctx,
 *         Env.getAD_Client_ID(ctx));
 * 
 *         //Buscamos el autorizador para esa organizacion transaccional
 *         MEXMEUserOrgTrx[] lista = MEXMEUserOrgTrx.getFromOrgTrx(ctx,
 *         AD_OrgTrx_ID, null);
 * 
 *         if(lista.length > 0){ //enviamos un correo de aviso paraa cada uno de
 *         los //autorizadores de la organizacion trx for (int i = 0; i <
 *         lista.length; i++) {
 * 
 *         user = MUser.get(ctx, lista[i].getAD_User_ID());
 * 
 *         if(user == null){ continue; } //correo que tiene configurado el
 *         usuario requestMail = user.getEMail();
 * 
 *         if(requestMail == null){ continue; } //creamos el mail email = new
 *         EMail(client,client.getRequestUser(),requestMail,subject, message);
 * 
 *         if (email == null){ continue; } if (client.isSmtpAuthorization())
 *         email.createAuthenticator (client.getRequestUser(),
 *         client.getRequestUserPW());
 * 
 *         try{ String msg = email.send(); if (EMail.SENT_OK.equals (msg)) user
 *         = null; }catch (Exception ex){} } return true;
 * 
 *         } else return false;
 * 
 *         } catch (Exception e) { s_log.log(Level.SEVERE, "Error en sendMail: "
 *         + e.getMessage()); } return false; }
 */

