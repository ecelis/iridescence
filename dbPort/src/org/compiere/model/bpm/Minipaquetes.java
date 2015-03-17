package org.compiere.model.bpm;


/**
 * 
 * @author Expert
 * @deprecated
 */
public class Minipaquetes {
//	/** */
//	private static CLogger slog = CLogger.getCLogger(Minipaquetes.class);
//	/** */
//	private List<ModelError> lstErrores = new ArrayList<ModelError>();
//	/** */
//	private Properties ctx = null;
//
//	/**
//	 * 
//	 * @param ctx
//	 */
//	public Minipaquetes(Properties ctx) {
//		super();
//
//		this.ctx = ctx;
//	}
//
//	/**
//	 * 
//	 * @param trxName
//	 * @param lstPaqBaseVer
//	 * @param ctapac
//	 * @param fechaIniGen
//	 *            //Fecha a partir de la cual se aplicar�n cargos
//	 * @param estServ
//	 * @param M_Warehouse_ID
//	 * @param AD_OrgTrx_ID
//	 * @return
//	 */
//	public boolean saveNew(String trxName, int EXME_Tratamientos_Sesion_ID,
//			List<MEXMEPaqBaseVersion> lstPaqBaseVer, MEXMECtaPac ctapac,
//			java.util.Date fechaIniGen, int estServ, int M_Warehouse_ID,
//			int AD_OrgTrx_ID) {
//
//		slog.log(Level.INFO, "***** saveNew ***** ");
//
//		try {
//			if (ctapac == null || ctapac.getEXME_CtaPac_ID() <= 0) {
//				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.buscarPaciente"));
//				return false;
//			}
//
//			if (lstPaqBaseVer.size() <= 0) {
//				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.noProducto"));
//				return false;
//			}
//
//			//
//			if (!minipack(EXME_Tratamientos_Sesion_ID, trxName, lstPaqBaseVer,
//					ctapac, fechaIniGen, estServ, M_Warehouse_ID, AD_OrgTrx_ID)) {
//				return false;
//			} else {
//				lstErrores.add(new ModelError(ModelError.TIPOERROR_Informativo,
//						"exito.recetaIndividual", ctapac.getPaciente()
//								.getNombre_Pac()));
//			}
//
//		} catch (Exception e) {
//			if (WebEnv.DEBUG)
//				e.printStackTrace();
//			lstErrores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//					.getMessage())); 
//			return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * 
//	 * @param EXME_Tratamientos_Sesion_ID
//	 * @param trxName
//	 * @param lstPaqBaseVer
//	 * @param ctapac
//	 * @param fechaIti
//	 *            //Fecha a partir de la cual se aplicar�n cargos
//	 * @param pEstServ
//	 * @param M_Warehouse_ID
//	 * @param AD_OrgTrx_ID
//	 * @return
//	 */
//	public boolean minipack(int EXME_Tratamientos_Sesion_ID, String trxName,
//			List<MEXMEPaqBaseVersion> lstPaqBaseVer, MEXMECtaPac ctapac,
//			java.util.Date fechaIti, int pEstServ, int M_Warehouse_ID,
//			int AD_OrgTrx_ID) {
//
////		MEXMEEstServ _estServ = null;
//		int estServID = pEstServ;
//
//		if (pEstServ <= 0) {
//			estServID = ctapac.getEXME_EstServ_ID();
//		}
//
////		_estServ = new MEXMEEstServ(ctx, estServID, null);
//
//		/*
//		 * (MEXMECtaPacExt ctaPacExt, Timestamp movementDate, MCtaPacDet[]
//		 * linesToDeliver, boolean allAttributeInstances, Timestamp
//		 * minGuaranteeDate, boolean devolucion, MEstServ estServLog, int
//		 * M_Warehouse_ID, String trxName,boolean esCargoAutomatico) {
//		 */
//
//		// Generar el embarque //Modifique el parametro enviado por el de la
//		// variable local trxName --Julio Gutierrez
//		MInOut m_InOut = null;
////		MEXMEInOut.createFrom(ctx,
////				ctapac, 
////				lines(ctx, lstPaqBaseVer, ctapac.getEXME_CtaPac_ID(), ctapac.getEXME_CtaPacExt_ID(), fechaIti,
////				trxName, M_Warehouse_ID, AD_OrgTrx_ID,EXME_Tratamientos_Sesion_ID), _estServ,
////				M_Warehouse_ID, new Timestamp(System.currentTimeMillis()), trxName);
////
////		if (m_InOut == null) {
////			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
////					"error.cargosdiarios", MEXMEInOut.getS_ProcessMsg()));
////			return false;
////		}
//
//		String status = m_InOut.prepareIt(); // Validar el registro de M_InOut
//		if (!DocAction.STATUS_InProgress.equals(status)) {
//			if (MEXMEInOut.getS_ProcessMsg().equals("@NoLines@")) {
//				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.cargosDiarios.noLineas", ""));
//			} else {
//				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.cargosdiarios", MEXMEInOut.getS_ProcessMsg()));
//			}
//			return false;
//		}
//
//		status = m_InOut.completeIt(); // Guardar el registro. Solo est�
//		// pendiente el commit.
//		m_InOut.setDocStatus(status);
//
//		if (!DocAction.STATUS_Completed.equals(status)) {
//			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//					"error.cargosdiarios", MEXMEInOut.getS_ProcessMsg()));
//			return false;
//		}
//		// Modifique el parametro enviado por el de la variable local trxName
//		// --Julio Gutierrez
//		if (!m_InOut.save(trxName)) {
//			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
//					"error.cargosdiarios" + MEXMEInOut.getS_ProcessMsg()));
//			return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param lstPaqBaseVer
//	 * @param ctapac
//	 * @param ctaPacExt
//	 * @param dateOrdered
//	 * @param trxName
//	 * @param M_Warehouse_ID
//	 * @param AD_OrgTrx_ID
//	 * @return
//	 */
//	public MCtaPacDet[] lines(Properties ctx,
//			List<MEXMEPaqBaseVersion> lstPaqBaseVer, int ctapac, int ctaPacExt,
//			Date dateOrdered, String trxName, int M_Warehouse_ID,
//			int AD_OrgTrx_ID, int EXME_Tratamientos_Sesion_ID) {
//
//		// Obtener los productos
//		List<MEXMEPaqBaseDet> lstDetalleVersion = MEXMEPaqBaseDet.getLines(ctx,
//				lstPaqBaseVer, trxName);
//
//		return lines(ctx, lstDetalleVersion, ctapac, ctaPacExt, dateOrdered,
//				M_Warehouse_ID, AD_OrgTrx_ID, EXME_Tratamientos_Sesion_ID);
//	}
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param lstDetalle
//	 * @param ctapac
//	 * @param ctaPacExt
//	 * @param dateOrdered
//	 * @param M_Warehouse_ID
//	 * @param AD_OrgTrx_ID
//	 * @return
//	 */
//	public MCtaPacDet[] lines(Properties ctx, List<MEXMEPaqBaseDet> lstDetalle,
//			int ctapac, int ctaPacExt, Date dateOrdered, int M_Warehouse_ID,
//			int AD_OrgTrx_ID, int EXME_Tratamientos_Sesion_ID) {
//
//		SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
//		String cargoHrMn = formato.format(DB.getDateForOrg(ctx));
//		formato = new SimpleDateFormat("yyyy-MM-dd");
//		String fechaOrden = formato.format(dateOrdered);
//		formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
//
//		try {
//			java.util.Date fechaCargo = formato.parse(fechaOrden + " "
//					+ cargoHrMn);
//
//			for (int i = 0; i < lstDetalle.size(); i++) {
//				// Modifique el parametro enviado por null, no necesita
//				// transaccion --Julio Gutierrez
//				MCtaPacDet ctaPacDet = new MCtaPacDet(ctx, 0, null);
//				ctaPacDet.setEXME_CtaPac_ID(ctapac);
//				ctaPacDet.setLine();
//
//				/*
//				 * Mandrake:ini --> Genera el cargo asignanod a QtyDelivered el
//				 * numero configurado en la configuracion.
//				 */
//
//				ctaPacDet.setQtyDelivered(lstDetalle.get(i).getCantidad());
//
//				StringBuffer descriptionTag = new StringBuffer();
//				if (lstDetalle.get(i).getDescription() != null)
//					descriptionTag.append(lstDetalle.get(i).getDescription())
//							.append(" - Daily");
//				else
//					descriptionTag.append("Daily Charge");
//
//				ctaPacDet.setDescription(descriptionTag.toString());
//				descriptionTag = null;
//
//				/*
//				 * <--Mandrake:fin
//				 */
//
//				ctaPacDet.setDateOrdered(new Timestamp(fechaCargo.getTime()));
//				ctaPacDet.setQtyOrdered(lstDetalle.get(i).getCantidad());
//				ctaPacDet.setQtyDelivered(lstDetalle.get(i).getCantidad());
//				ctaPacDet.setQtyEntered(lstDetalle.get(i).getCantidad());
//
//				/*
//				 * Mandrake:ini -->
//				 */
//
//				// System.out.println("Valor de CDiariosAction.ctaPacDet.setQtyDelivered(lstDetalle.get(i).getCantidad()) : "+ctaPacDet.getQtyDelivered());
//
//				/*
//				 * <--Mandrake:fin
//				 */
//
//				ctaPacDet.setM_Product_ID(lstDetalle.get(i).getM_Product_ID());
//				ctaPacDet.setC_UOM_ID(lstDetalle.get(i).getC_UOM_ID());
//				ctaPacDet.setEXME_PaqBase_Version_ID(lstDetalle.get(i)
//						.getEXME_PaqBase_Version_ID());
//				// Modifique el parametro enviado por null, no necesita
//				// transaccion --Julio Gutierrez
//
//				ctaPacDet.setM_Warehouse_ID(M_Warehouse_ID);
//				// ctaPacDet.setM_Warehouse_Sol_ID(cDiario.getM_Warehouse_Sol_ID());
//				// //No aplica //Modifique el parametro enviado por null, no
//				// necesita transaccion --Julio Gutierrez
////				MEXMECtaPac m_CtaPac = new MEXMECtaPac(ctx, ctapac, null);
//				ctaPacDet.setAD_OrgTrx_ID(AD_OrgTrx_ID);// Organizacion
//				// transaccional de la
//				// configuracion
//				// ctaPacDet.setC_Charge_ID(lstDetalle.get(i).getC_Charge_ID());
//				ctaPacDet.setEXME_CtaPacExt_ID(ctaPacExt);
//				ctaPacDet.setCosto(Env.ZERO);
//				ctaPacDet.setC_Currency_ID(Env.getContextAsInt(ctx,"$C_Currency_ID"));
//				ctaPacDet.setDateDelivered(new Timestamp(fechaCargo.getTime()));
//
//				// Inicio RMontemayor.- Junio 2007, Cambios para guardar
//				// informacion de PreciosVenta dentro de la linea del Detalle.
////				MEXMEEstServ estServLog = MEstServAlm.getEstServ(ctx, ctaPacDet.getM_Warehouse_ID(), null);
//
//				// Modifique el parametro enviado por null, no necesita
//				// transaccion --Julio Gutierrez
//	/*			PreciosVenta.m_configPre = MConfigPre.get(ctx, null);
//				PreciosVenta.m_Paciente = new MEXMEPaciente(ctx, m_CtaPac
//						.getEXME_Paciente_ID(), null);
//				PreciosVenta.m_ConfigEC = MConfigEC.get(ctx, null);
//*/
//				// Modifique el parametro enviado por null, no necesita
//				// transaccion --Julio Gutierrez
////				MPrecios pv = PreciosVenta.precioProdVta(ctx, estServLog
////						.getTipoArea(), ctaPacDet.getM_Product_ID(), ctaPacDet
////						.getQtyOrdered(), ctaPacDet.getC_UOM_ID(),
////						PreciosVenta.PVH, 0, 0, ctaPacDet
////								.getM_Warehouse_Sol_ID(), ctaPacDet
////								.getM_Warehouse_ID(), estServLog
////								.getEXME_Area_ID(), ctaPacDet.getDateOrdered(),
////						true, null);
//
//				MPrecios pv = GetPrice.getPriceCargo(ctx, ctaPacDet);
//				ctaPacDet = pv.preciosActual(ctaPacDet);
//
//				ctaPacDet.setQtyPaquete(ctaPacDet.getQtyOrdered());
//				ctaPacDet.setQtyPendiente(ctaPacDet.getQtyOrdered());
//
//				if (EXME_Tratamientos_Sesion_ID > 0)
//					ctaPacDet
//							.setEXME_Tratamientos_Sesion_ID(EXME_Tratamientos_Sesion_ID);
//				list.add(ctaPacDet);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		MCtaPacDet[] ctaPacDets = new MCtaPacDet[list.size()];
//		list.toArray(ctaPacDets);
//
//		return ctaPacDets;
//	}
//
//	public List<ModelError> getLstErrores() {
//		return lstErrores;
//	}
//
//	public void setLstErrores(List<ModelError> lstErrores) {
//		this.lstErrores = lstErrores;
//	}
}
