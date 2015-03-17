/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_M_Product;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCDiario;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECama;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEInOut;
import org.compiere.model.MEstServAlm;
import org.compiere.model.MInOut;
import org.compiere.model.MPrecios;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.Env;

/**
 * Generación de cargos diarios
 * Para el cliente y organizacion de logueo
 * @author twry basado en {@link ProcesaCargos}
 * Modificado por Lorena Lama - Correccion de errores y revision de codigo
 */
public class ProcesaCargoDiario extends SvrProcess {
	/** Contador de cargos procesados */
	private int count = 0;
	/** Fecha parametro */
	private Timestamp fechaParam;

	@Override
	protected void prepare() {
		fechaParam = Env.getCurrentDate(); 
				
		 final ProcessInfoParameter[] para = getParameter();
		 for (int i = 0; i < para.length; i++) {
			 String name = para[i].getParameterName();
			 if (name.equals("Date")){
				 fechaParam = ((Timestamp) para[i].getParameter());
				 break;
			 }
		 }
		count = 0;
	}

	@Override
	protected String doIt() throws Exception {
		String success = null;

		// Esquema por cliente
		final MAcctSchema acctSchema = MAcctSchema.getClientAcctSchema(getCtx(),
				Env.getAD_Client_ID(getCtx()))[0];
		if (acctSchema != null) {
			Env.setContext(getCtx(), "$C_Currency_ID",
					acctSchema.getC_Currency_ID());
		}

		success = executeOrg(fechaParam);
		if (success != null) {
			return success;
		}

		return "Record: " + count;
	}

	/**
	 * Proceso principal, se ejecutará por cada organización de cada cliente
	 */
	public String executeOrg(final Timestamp fechaCargo) {
		log.info("Executing daily charges process (manual)..." + fechaCargo);
		try {
			// Lista de cuentas pacientes en status 'Admission', y que cumplen
			// con las pre-condiciones de Daily Charges
//			final MEXMECtaPac[] lstCtaPac = MEXMECtaPac.getCtaPac(getCtx(), null);
			// Todas las camas con cuenta que sean censables
			final List<MEXMECama> lstcama = new Query(getCtx(), MEXMECama.Table_Name,
				" EXME_Cama.EXME_CtaPac_ID IS NOT NULL AND EXME_Cama.IsCensable='Y' ", null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true) //
			.list();
			// Determina si el cargo diario puede o no llevarse a cabo.
			boolean bandera = false;
			int countConfigError = 0;
			
			// Ciclo por cuenta paciente de la organización
			for (MEXMECama cama : lstcama) {
				MEXMECtaPac ctapac = null;
				try {
					ctapac = cama.getCtaPac();
					log.info("cDiario >" + cama.getEXME_CtaPac_ID());
					ctapac.set_TrxName(get_TrxName());
					// Validar que se pueda cargar dependiendo de la fecha
					bandera = validarFechaCargo(fechaCargo, ctapac);

					// Si es valido aplicar el cargo
					if (bandera) {
						// Obtener el cargo diario en base al cargo diario
						// asignado al tipo de habitación de la cuenta paciente.
						final MCDiario diario = MCDiario.getByRoomType(getCtx(), 
								cama.getHabitacion().getEXME_TipoHabitacion_ID(),
								ctapac.getEXME_TipoPaciente_ID(),
								ctapac.getEXME_EstServ_ID(), null);

						if (diario == null) {
							final StringBuilder erromsg = new StringBuilder();
							erromsg.append("ProcesaCargos >> No CDiario camaId: ");
							erromsg.append(cama.getEXME_Cama_ID());
							erromsg.append(", ctapacId: ").append(ctapac.getEXME_CtaPac_ID());
							erromsg.append(", tipoPacId: ").append(ctapac.getEXME_TipoPaciente_ID());
							erromsg.append(", estServId: ").append(ctapac.getEXME_EstServ_ID());
							erromsg.append(", tipoHabId: ").append(cama.getHabitacion().getEXME_TipoHabitacion_ID());
							log.severe(erromsg.toString());
							countConfigError++;
							continue;
						}

						final MEXMECtaPacExt extension = getExtensionCta(ctapac);
						if (extension == null) {
							log.severe("ProcesaCargos No se creo Registro en EXME_CtaPacExt, ctapac: " + ctapac.getEXME_CtaPac_ID());
							continue;
//							return "@Error@"; // se quita return, ya que se hace rollback a los demas cargos creados efectivamente
						}

						// Estación de servicio en la que se creó la cuenta paciente.
						final MEXMEEstServ estServ = new MEXMEEstServ(getCtx(), ctapac.getEXME_EstServ_ID(), null);
						// Crea M_InOut, M_InOutLine, EXME_CtaPacDet
						final MInOut m_InOut = createInOutApplyCharge(ctapac, extension, estServ, diario, fechaCargo);

						if (m_InOut == null) {
							log.severe("cDiario ctapacId: " + ctapac.getEXME_CtaPac_ID() + " Inout no generado: " + MEXMEInOut.getS_ProcessMsg());
							super.rollback();//Se hace rollback a los cambios (Lama)
							continue;
						} else {
							if (m_InOut.getDocumentNo() == null) {
								log.severe("cDiario Inout sin numero de documento: " + MEXMEInOut.getS_ProcessMsg());
								super.rollback();
								continue;
							} else {
								// Asignar a la cuenta paciente la fecha del último
								// cargo diario que se le realizó (es decir, el de
								// hoy).
								ctapac.setFechaCargoDiario(fechaCargo);

								// Poner el estatus de la cama como "OCUPADA SUCIA".
								ctapac.getCama().setEstatus(MEXMECama.ESTATUS_OccupiedDirty);

								if (!ctapac.save(get_TrxName())) {
									super.rollback();
									log.severe("cDiario Cuenta paciente " + ctapac.getEXME_CtaPac_ID());
									continue;
								}
								count++;
								log.info("Cargo diario manual aplicado a la cuenta: " + ctapac.getDocumentNo() + " [" + count + "] ");
							}
						}
						super.commitEx();//Se hace commit a los cambios (Lama)
					}
				} catch (Exception e) {
					super.rollback();//Se hace rollback a los cambios (Lama)
					log.log(Level.SEVERE, "Error durante la aplicacion de la cuenta" + ctapac.getDocumentNo(), e);
					continue; // se evita hacer return, ya que se hace rollback a los demas cargos creados efectivamente
				}
			}
			// Log, errores
			if(lstcama == null || lstcama.isEmpty()){
				log.config("No hay camas ocupadas/censables para orgId: "+Env.getAD_Org_ID(getCtx()));
			} else if(countConfigError > 0){
				log.severe("Total de camas sin cargos diarios: " + countConfigError + " para la orgId: " + Env.getAD_Org_ID(getCtx()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
//			return "@Error@"; // se evita hacer return, ya que se hace rollback a los demas cargos creados efectivamente
		}
		return null;
	}

	/**
	 * Crea el cargo a la cuenta paciente y hace la salida de inventario
	 * 
	 * @param mCtaPac
	 * @param extension
	 * @param estServ
	 * @param diario
	 * @param fechaIti
	 * @return
	 */
	private MInOut createInOutApplyCharge(final MEXMECtaPac mCtaPac,
			final MEXMECtaPacExt extension, final MEXMEEstServ estServ,
			final MCDiario diario, final Timestamp fechaIti) {
		String status = null;
		log.info("cDiario getInout "+ mCtaPac.getEXME_CtaPac_ID());
		MInOut m_InOut = null;
		
		// Crea el registro de EXME_CtaPacDet (no lo guarda)
		final MCtaPacDet[] linesToDeliver = getLinesCtaPacDet(getCtx(), //
			diario, //
			mCtaPac.getEXME_CtaPac_ID(),//
			get_TrxName(), //
			extension.getEXME_CtaPacExt_ID(),//
			fechaIti);

		if (linesToDeliver == null || linesToDeliver.length == 0) {
			log.severe("cDiario No se creo EXME_CtaPacDet, cuenta: " + mCtaPac.getDocumentNo());
		} else {
			// Generar el embarque y guarda el cargo
			m_InOut = MEXMEInOut.createFrom(getCtx(),//
				mCtaPac,//
				linesToDeliver, //
				estServ, //
				diario.getM_Warehouse_ID(),//
				fechaIti, //
				get_TrxName());
		}

		if (m_InOut == null) {
			log.severe("cDiario Imposible la rsalida de inventario (m_InOut) Cargos Diario, cuenta: "+mCtaPac.getDocumentNo());
			
		} else {
			// Validar el registro de M_InOut
			status = m_InOut.prepareIt();

			if (DocAction.STATUS_InProgress.equals(status)) {
				// Guardado, pendiente commit
				status = m_InOut.completeIt();
				m_InOut.setDocStatus(status);

				if (DocAction.STATUS_Completed.equals(status)) {

					if (m_InOut.save(get_TrxName())) {
						log.info("cDiario Inout "+ mCtaPac.getEXME_CtaPac_ID() + " OK " + m_InOut.getM_InOut_ID());
					} else {
						log.severe("cDiario ctapacId: " + mCtaPac.getEXME_CtaPac_ID() + "m_InOut.save(get_TrxName()) " + m_InOut.getProcessMsg());
						m_InOut = null;						
					}

				} else {
					log.severe("cDiario DocAction.STATUS_Completed.equals(status) " + m_InOut.getProcessMsg());
					m_InOut = null;

				}

			} else {
				if ("@NoLines@".equals(m_InOut.getProcessMsg())) {
					log.severe("cDiario ctapacId: " + mCtaPac.getEXME_CtaPac_ID() + " @NoLines@ " + m_InOut.getProcessMsg());
				} else {
					log.severe("cDiario ctapacId: " + mCtaPac.getEXME_CtaPac_ID() + "Error: " + m_InOut.getProcessMsg());
				}
				m_InOut = null;
			}

		}
		return m_InOut;
	}

	/**
	 * Extension cero de la cuenta paciente
	 * @param mCtaPac Cuenta paciente
	 * @return Extension cero
	 */
	private MEXMECtaPacExt getExtensionCta(final MEXMECtaPac mCtaPac) {
		// Obtener la extensión 0 de la cuenta paciente
		MEXMECtaPacExt extension = MEXMECtaPacExt.getExtCero(getCtx(),
				mCtaPac.getEXME_CtaPac_ID(), get_TrxName());

		if (extension == null) {
			// Crear la extensión en EXME_CtaPacExt para la
			// cuenta paciente en cuestión.
			extension = agregaExtension(mCtaPac, getCtx(), get_TrxName());

			if (extension == null) {
				log.log(Level.SEVERE,
						"ProcesaCargos No se creo Registro en EXME_CtaPacExt");
			} else {
				// Agregar la extension a la cuenta
				// paciente.
				mCtaPac.addExtension(extension);
				mCtaPac.setEXME_CtaPacExt_ID(extension.getEXME_CtaPacExt_ID());
			}
		}
		return extension;
	}

	/**
	 * Validacion de fecha
	 * 
	 * @param fechaIti
	 * @param fecha
	 * @param formaFechaFin
	 * @param mCtaPac
	 * @return
	 * @throws ParseException
	 */
	private boolean validarFechaCargo(final Timestamp fechaParam, final MEXMECtaPac mCtaPac) throws ParseException {
		
		boolean bandera = false;
		final Timestamp fechaIngreso = getSoloFecha(mCtaPac.getDateOrdered());
		final Timestamp fechaCargoDiario = getSoloFecha(mCtaPac.getFechaCargoDiario() == null ? Env.getCurrentDate() : mCtaPac.getFechaCargoDiario());
		final Timestamp fechaCargo = getSoloFecha(fechaParam);
		
		log.info("cDiario "+ mCtaPac.getEXME_CtaPac_ID() +" fechaIngreso > " + fechaIngreso);
		log.info("cDiario "+ mCtaPac.getEXME_CtaPac_ID() +" fechaCargoDiario > " + fechaCargoDiario);
		log.info("cDiario "+ mCtaPac.getEXME_CtaPac_ID() +" fechaCargo > " + fechaCargo);
		
		// Tiene un cargo previo aplicado
		if (mCtaPac.getFechaCargoDiario() == null) {
//			if (fechaCargo.after(fechaIngreso)) {
				bandera = true;
				log.info("cDiario " + mCtaPac.getEXME_CtaPac_ID() + " fechaCargo>=fechaIngreso ");
//			} else {// después
//				log.info("cDiario " + mCtaPac.getEXME_CtaPac_ID() + " Cargo aplicado previamente ");
//			}
		} else if (fechaCargo.after(fechaCargoDiario)
				// La fecha del ultimo cargo aplicado a la ctapac debe
				// ser anterior a la fecha en que se aplicará el cargo
				// actual

				// La fecha de creación de la ctapac debe de ser
				// menor a la fecha en que se aplicará el cargo
				// y también debe de ser menor a la fecha final para
				// aplicar pagos (seleccionada en la forma)
				&& fechaCargo.after(fechaIngreso)){
			bandera = true;
			log.info("cDiario "+ mCtaPac.getEXME_CtaPac_ID() +" fechaCargo.after(fechaCargoDiario) && fechaCargo.after(fechaIngreso) ");
		} else {//después
			log.info("cDiario "+ mCtaPac.getEXME_CtaPac_ID() +" No cumple con los criterios de fechas ");
		}
		return bandera;
	}

	/**
	 * Poblamos el detalle de la CtaPac con el cargo diario generado
	 * 
	 * @param ctx
	 * @param diario
	 * @param ctapac
	 * @param trxName
	 * @param ctaPacExt
	 * @param dateOrdered
	 * @return
	 */
	private MCtaPacDet[] getLinesCtaPacDet(final Properties ctx, final MCDiario diario, final int ctapac,
			final String trxName, final int ctaPacExt, final Timestamp dateOrdered) {
		
		log.info("cDiario lines (CtaPac:"+ ctapac+ "MCDiario:"+diario.getEXME_CDiario_ID());
		final ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		
		try {
			final I_M_Product prod = diario.getM_Product();
			if (MProduct.PRODUCTTYPE_Service.equals(prod.getProductType())) {
				final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, ctapac, null);
				MCtaPacDet ctaPacDet = new MCtaPacDet(ctx, 0, trxName);
				ctaPacDet.setEXME_CtaPac_ID(ctapac);
				ctaPacDet.setLine();
				ctaPacDet.setDateOrdered(dateOrdered);

				ctaPacDet.setQtyOrdered(new BigDecimal(1));
				ctaPacDet.setQtyDelivered(new BigDecimal(1));
				ctaPacDet.setM_Product_ID(diario.getM_Product_ID());
				ctaPacDet.setC_UOM_ID(prod.getC_UOM_ID());
				ctaPacDet.setM_Warehouse_ID(diario.getM_Warehouse_ID());
				ctaPacDet.setEXME_CtaPacExt_ID(ctaPacExt);
				ctaPacDet.setC_Currency_ID(Env.getC_Currency_ID(ctx));
				ctaPacDet.setDateDelivered(dateOrdered);
				ctaPacDet.setTipoArea(ctaPac.getTipoArea());

				// Estación de servicio a la que entró la cuenta paciente.
				final MEXMEEstServ estServ = new MEXMEEstServ(ctx, ctaPac.getEXME_EstServ_ID(), null);
				ctaPacDet.setEXME_Area_ID(estServ.getEXME_Area_ID());
				ctaPacDet.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());// Ticket #07590

				if (ctaPacDet.getTipoArea() == null) {
					final MEXMEEstServ estServLog = MEstServAlm.getEstServ(ctx, ctaPacDet.getM_Warehouse_ID(), trxName);
					ctaPacDet.setTipoArea(estServLog.getTipoArea());
				}
				// Precio de venta
				final MPrecios pv = GetPrice.getPriceCargo(ctx, ctaPacDet);
				ctaPacDet = pv.preciosActual(ctaPacDet);

				log.info("cDiario CtaPac:" + ctapac + " Precio " + pv.getPriceStd());

				// Calcular el taxAmt para el cargo diario
				final MTax tax = new MTax(ctx, ctaPacDet.getC_Tax_ID(), trxName);
				if (tax != null) {
					ctaPacDet.setTaxAmt(tax.calculateTax(ctaPacDet.getLineNetAmt(), false, 2));
				}

				ctaPacDet.setQtyPaquete(ctaPacDet.getQtyOrdered());
				ctaPacDet.setQtyPendiente(ctaPacDet.getQtyOrdered());
				ctaPacDet.setDescription("CDiario" + ctaPacDet.toString());
				list.add(ctaPacDet);
			} else {
				log.severe("Producto no es Servicio, prodId: " + prod.getM_Product_ID() + ", ctapacId: " + ctapac);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		final MCtaPacDet[] ctaPacDets = new MCtaPacDet[list.size()];
		list.toArray(ctaPacDets);

		return ctaPacDets;
	}

	/**
	 * Agrega una extension cuando la cuenta paciente no llegara a tener
	 * @param ctaPac Cuenta paciente
	 * @param ctx Contexto, si llegara a correr del lado del servidor se necesita establecer el cliente
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	private MEXMECtaPacExt agregaExtension(final MEXMECtaPac ctaPac, final Properties ctx,
			final String trxName) {
		// insertamos en cta pac x extension
		MEXMECtaPacExt cpe = new MEXMECtaPacExt(ctaPac, trxName);
		cpe.setDescription(ctaPac.getPaciente().getDescription());
		cpe.setCliente(ctaPac);
		if (!cpe.save(trxName)) {
			log.saveError("Error",
					"No fue posible guardar la cuenta paciente x extension");
			cpe = null;
		}
		return cpe;
	}
	
	/**
	 * Quitar la hora,min y seg a la fecha que se pasa de parametro
	 * @param fechaOriginal Fecha aquitar la hora
	 * @return Timestamp sin horas, ni min ni segundos
	 */
	private Timestamp getSoloFecha(final Timestamp fechaOriginal){
		final java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTimeInMillis(fechaOriginal.getTime());
		cal.set(java.util.Calendar.MILLISECOND, 0);
		cal.set(java.util.Calendar.SECOND, 0);
		cal.set(java.util.Calendar.MINUTE, 0);
		cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
		final java.sql.Timestamp soloFecha = new java.sql.Timestamp(cal.getTime().getTime());
		return soloFecha;
	}
	
	public void testProcess() throws Exception {
		setCtx(Env.getCtx());
		fechaParam = Env.getCurrentDate(); 
		count = 0;
		doIt();
	}
}