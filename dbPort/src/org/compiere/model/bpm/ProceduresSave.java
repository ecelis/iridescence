package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMEConfigOV;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEPaqBaseDet;
import org.compiere.model.MEXMEProductClassWhs;
import org.compiere.model.MProduct;
import org.compiere.model.ServicioView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class ProceduresSave {
	/** log */
	private static CLogger log = CLogger.getCLogger (ProceduresSave.class);
	private Properties ctx;
	
	/**
	 * Constructor
	 * @param pCtx
	 */
	public ProceduresSave(Properties pCtx){
		ctx = pCtx;
	}
	
	/**
	 * Guarda los procedimientos
	 * los procedimientos pueden tener servicios
	 * @param procedures : Listado de procedimientos (MProduct)
	 * @param EXME_ActPaciente_ID : id Actividad paciente header
	 * @param EXME_CtaPac_ID : id cuenta paciente
	 * @param trxName : nombre de transaccion
	 * @throws Exception
	 */
	public void saveProcedures(List<MProduct> procedures, int EXME_ActPaciente_ID, int EXME_CtaPac_ID, MedicationSave medicationSave, ServicesSave servicesSave, String trxName) throws Exception {
		
		//
		if (procedures != null && !procedures.isEmpty()) {
			
			//MEXMEActPacienteIndH header = createHeader(EXME_ActPaciente_ID, EXME_CtaPac_ID, true, trxName);
			//createLines(procedures, header, true, trxName);
			
			// Los procemimientos
			List<MProduct> productsAll = new ArrayList<MProduct>();
			// Medicamentos o items
			List<MProduct> productsItem = new ArrayList<MProduct>();
			// Servicios
			List<MProduct> productsServ = new ArrayList<MProduct>();
			// Servicios que no seran aplicados en el hospital
//			List<ServicioView> productsServExt = new ArrayList<ServicioView>();
			// Es necesario separar las solicitudes por almacen
//			HashMap<Integer, List<ServicioView>> servicesAddedMap = new HashMap<Integer, List<ServicioView>>();
//			 Servicios que no seran aplicados en el hospital
//			HashMap<Integer, List<ServicioView>> servicesAddedMapExt = new HashMap<Integer, List<ServicioView>>();
			
			// Mostrar el resultado de las solictudes creadas
//			@SuppressWarnings("unused")
//			List<MEXMEActPacienteIndH> actPacienteIndHServ = new ArrayList<MEXMEActPacienteIndH>();
			// Mostrar el resultado de las solictudes creadas que no seran aplicadas en el hospital
//			@SuppressWarnings("unused")
//			List<MEXMEActPacienteIndH> actPacienteIndHServExt = new ArrayList<MEXMEActPacienteIndH>();
			
			// Listamos los productos, los servicios
			for (MProduct procedure: procedures) {
				
				// Identifica si el procedimiento es un minipaquete
				List<MProduct> products = getPackageLine(ctx, procedure.getM_Product_ID(), null);
				
				// Si es minipaquete se crea linea por linea
				if (products != null && !products.isEmpty()) {
					for (MProduct product: products) {
						/**Se elimina referencia a columna isEstudio  GADC**/
						//if (!product.isEstudio() || !MProduct.PRODUCTCLASS_Laboratory.equals(product.getProductClass()) 
						if (MProduct.PRODUCTCLASS_Laboratory.equals(product.getProductClass())
								|| MProduct.PRODUCTCLASS_XRay.equals(product.getProductClass())) {
							//createCharge(product, EXME_CtaPac_ID, product.getQuantity(), product.getEXME_PaqBase_Version_ID(), trxName);
							productsServ.add(product);
						} else {
							// Se crea la indicacion por detalle, para que se vea el expediente
							productsItem.add(product);
						}
					}
					productsAll.add(procedure);
				} else {
					//createCharge(procedure, EXME_CtaPac_ID, Env.ONE, trxName);
					productsAll.add(procedure);
				}
			}
			//Se agrego configuracion para habilitar y deshabilitar la generacion de los cargos desde NIMBO
			MEXMEConfigOV confOV = MEXMEConfigOV.findActPac(ctx, EXME_ActPaciente_ID, null);
			if (medicationSave!=null ) {
				// Crea la indicacion medica el cargo y la salida de inventario (Procedimientos)
				if (productsAll.size()>0) {
					if (confOV != null && confOV.isGenCharges()) {
						// Crea el encabezado y lineas de la indicacion y los cargos y la salida de inventario
						medicationSave.insertCharge(medicationSave.createActPacIndH(productsAll, true, trxName, true));
					} else {
						// Crea el encabezado y lineas de la indicacion 
						medicationSave.createActPacIndH(productsAll, true, trxName, true);
					}
				}
				// Crea la indicacion medica el cargo y la salida de inventario (Items)
				if (productsItem.size()>0) {
					if (confOV != null && confOV.isGenCharges()) {
						// Crea el encabezado y lineas de la indicacion y los cargos y la salida de inventario 
						medicationSave.insertCharge(medicationSave.createActPacIndH(productsItem, true, trxName, false));
					} else {
						// Crea el encabezado y lineas de la indicacion
						medicationSave.createActPacIndH(productsItem, true, trxName, false);
					}
				}
			}
			// Crea la indicacion medica (Estudios)
			final List<ServicioView> lst = new ArrayList<ServicioView>();
			if (servicesSave!=null && !productsServ.isEmpty()) {
				
				// Separa por almacen los servicios a solicitar
				for (MProduct prod : productsServ) {
					final ServicioView view = new ServicioView(prod);
					
					// si el servicio es a solicitar y aplicar dentro de la organizacion
					if(!prod.isExternal()) {

						// buscamos el almacen que deberia de aplicar
						final List<LabelValueBean> lstalm = MEXMEProductClassWhs.getAllProdClass(ctx, prod.getProductClass(), true, null);
						//List<LabelValueBean> lstalm = MEXMEReplenish.validaAlmacenes(ctx, productsServ.get(i).getM_Product_ID());
						if(lstalm!=null && !lstalm.isEmpty()){
							// Agregamos al mapa el almacen con su detalle
							int almacen = Integer.valueOf(lstalm.get(0).getValue());
//							ServicioView view = new ServicioView(ctx, productsServ.get(i));
							view.setAlmaServ(almacen);//LAMA
//							if(servicesAddedMap.containsKey(almacen)){
//								servicesAddedMap.get(almacen).add(view);
//							} else {
//								List<ServicioView> lst = new ArrayList<ServicioView>();
//								lst.add(view);
//								servicesAddedMap.put(almacen, lst);
//							}
						}
//					} else {
//						// solo se creara el expediente
//						productsServExt.add(new ServicioView(ctx, productsServ.get(i)));
					}
					lst.add(view);
				}
				
				//
//				if(!productsServExt.isEmpty()){
//					servicesAddedMapExt.put(0, productsServExt);
//				}
				
				// se crea el expediente (solicitudes de servicios)
				if(lst.isEmpty()) { //LAMA
					servicesSave.save(lst, EXME_ActPaciente_ID, new MEXMECtaPac(ctx,EXME_CtaPac_ID,trxName), false, trxName);
				}
						
//				if(!servicesAddedMap.isEmpty()){
//					// Internos
//					if(servicesSave.save(servicesAddedMap,
//							0,0,0,
//							EXME_ActPaciente_ID, new MEXMECtaPac(ctx,EXME_CtaPac_ID,trxName),
//							null, false, trxName)){ 
//						actPacienteIndHServ = servicesSave.getLstActPacienteIndH();
//					}
//					
//					// Externos //TODO: completos ....
//					if(servicesSave.save(servicesAddedMapExt,
//							0,0,0,
//							EXME_ActPaciente_ID, new MEXMECtaPac(ctx,EXME_CtaPac_ID,trxName),
//							null, false, trxName)){
//						actPacienteIndHServExt = servicesSave.getLstActPacienteIndH();
//					}
//				}
			}// Estudios
		}// Que existan procedimientos....
	}
	
	/**
	 * Guarda los procedimientos de la cita medica
	 * @param procedures : Listado de procedimientos (ServicioView)
	 * @param EXME_ActPaciente_ID : id Actividad paciente header
	 * @param EXME_CtaPac_ID : id cuenta paciente
	 * @param trxName : nombre de transaccion
	 * @throws Exception
	 */
	public void saveProcedure(List<ServicioView> procedures, int EXME_ActPaciente_ID, int EXME_CtaPac_ID, MedicationSave medicationSave, ServicesSave servicesSave, String trxName) throws Exception {
		
		//
		if (procedures != null && !procedures.isEmpty()) {
			MEXMEConfigOV confOV = MEXMEConfigOV.findActPac(ctx, EXME_ActPaciente_ID, null);
			if (medicationSave!=null ) {
				// Crea la indicacion medica el cargo y la salida de inventario (Procedimientos)
				if (confOV != null && confOV.isGenCharges()) {
					// Crea el encabezado y lineas de la indicacion y los cargos y la salida de inventario
					medicationSave.insertCharge(medicationSave.createActPacIndHProc(procedures, true, trxName, true));
				} else {
					// Crea el encabezado y lineas de la indicacion 
					medicationSave.createActPacIndHProc(procedures, true, trxName, true);
				}
			}
		}// Que existan procedimientos....
	}

	/**
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MProduct> getPackageLine(Properties ctx, int M_Product_ID, String trxName) throws Exception {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT line.M_Product_ID, line.Cantidad, line.exme_paqbase_version_id FROM EXME_PaqBaseDet line")
		.append(" INNER JOIN EXME_PaqBase_Version version ON (line.exme_paqbase_version_id = version.exme_paqbase_version_id) ")
		.append(" WHERE version.M_Product_ID = ? AND version.IsActive = 'Y'");


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MProduct product = new MProduct(ctx, rs.getInt(MProduct.COLUMNNAME_M_Product_ID), trxName);
				product.setQuantity(rs.getBigDecimal(MEXMEPaqBaseDet.COLUMNNAME_Cantidad));
				product.setEXME_PaqBase_Version_ID(rs.getInt(MEXMEPaqBaseDet.COLUMNNAME_EXME_PaqBase_Version_ID));
				list.add(product);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
}
