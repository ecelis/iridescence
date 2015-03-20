package org.compiere.model;

import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * La clase permitira los manejos con los paquete 
 * 
 * @author Tania Perez
 *
 */
public class MEXMEPaquetes {
	
	@SuppressWarnings("unused")
	private static CLogger      log = CLogger.getCLogger (MEXMEPaquetes.class);
	
	/**
	 * Hace el macheo entre el minipaquete cargado
	 * y la configuracion del minipaquete
	 * @param ctx
	 * @param EXME_PaqBase_Version_ID
	 * @param AD_Session_ID
	 * @param fecha
	 * @param trxName
	 * @throws Exception
	 *
	public static void miniPack(Properties ctx, int EXME_PaqBase_Version_ID, 
			int AD_Session_ID, String fecha, String trxName)throws Exception {
		try{
			//Verificamos cuales productos del mini paquete son del paquete original y 
			//las cantiddes que no esten excedidas
			MEXMETPaqBaseDet.paqueteVsCargo(ctx, EXME_PaqBase_Version_ID, AD_Session_ID, trxName);
			
			// Evaluamos las lineas que han quedado pendientes 
			// de relacionarse con algun cargo
			MEXMETPaqBaseDet.lineasSinCargo(ctx, EXME_PaqBase_Version_ID, AD_Session_ID, fecha, trxName);
			
			// Evaluamos las lineas que han quedado pendientes 
			// de relacionarse con un paquete
			MEXMETPaqBaseDet.lineasSinPaq(ctx, EXME_PaqBase_Version_ID, AD_Session_ID, fecha, trxName);
		
		
		}catch (Exception e) {
			throw new Exception("error.paqute.noSave");
		}
		
	}
*/
	
	/**
	 * Este metodo tiene la tarea de grabar en la tablas de proceso 
	 * de calculo de saldo de la cuenta paciente, facturacion y estado de cuenta
	 * por lo que al momento de hacer modificaciones, sera necesario evaluar
	 * si se debe aplicar el cambio en las lineas condicionadas 
	 * para los demas procesos antes mensionados.
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param EXME_CtaPacExt_ID Extension Cero de la cuenta paciente
	 * @param tabla
	 * @param trxName
	 * @throws Exception
	 *
		
		public static void miniPaquete(Properties ctx, int EXME_CtaPac_ID,
    		int EXME_CtaPacExt_ID, 
    		String tabla, String trxName)	throws Exception {
		//Obtenemos todos los paquetes considerados mini que estan afectando a la cuenta paciente
		//List lstMiniPack = MCtaPacDet.getMinipaquetes(ctx, EXME_CtaPac_ID, EXME_CtaPacExt_ID,  trxName);
		List<MPaqBaseVersion> lstMiniPack = MFacturacionEdoCtaPac.getMinipaquetes(ctx, EXME_CtaPac_ID, EXME_CtaPacExt_ID, tabla, trxName);
			
		//Las lineas que corresponen a la tabla EXME_CtaPacDet, que es una por paquete
		//con el fin de que el usuario solo vea una linea representante del minipaquete
		/**
		 * Cualquier cambio aplicado a esta seccion de codigo se debe replicar
		 * en la condicion if (tabla.equalsIgnoreCase("EXME_T_CtaPacDet")) {
		 *
		if (tabla.equalsIgnoreCase("EXME_CtaPacDet")) {
			for (int i = 0; i < lstMiniPack.size(); i++) {
				MCtaPacDet m_cargo = MFacturacion.getLineasPaquete(ctx, 
						((MPaqBaseVersion)lstMiniPack.get(i)).getSecuencia(),
						(MPaqBaseVersion)lstMiniPack.get(i), EXME_CtaPac_ID, 
						EXME_CtaPacExt_ID, trxName); 
				//La linea que se esta creando en la tabla EXME_CtaPacDet
				//mantienen una relacion con la linea en la tabla EXME_CtaPacDet
				//por medio de la secuencia
				m_cargo.setSerie(((MPaqBaseVersion)lstMiniPack.get(i)).getSecuencia());
				m_cargo.setEXME_PaqBase_Version_ID(((MPaqBaseVersion)lstMiniPack.get(i)).getEXME_PaqBase_Version_ID());
				m_cargo.setVisible(true);
				m_cargo.setTipoLinea(MCtaPacDet.TIPOLINEA_MiniPack);
				if(!m_cargo.save(trxName)){
					throw new Exception("error.paqute.noSave");
				}
			}
		}
		/**
		 * Cualquier cambio aplicado a esta seccion de codigo se debe replicar
		 * en la condicion if (tabla.equalsIgnoreCase("EXME_T_CtaPacDet")) {
		 *
			
		if (tabla.equalsIgnoreCase("EXME_T_CtaPacDet")) {
			for (int i = 0; i < lstMiniPack.size(); i++) {
				MTCtaPacDet m_cargo = MFacturacion.getLineasPaqueteT(ctx, 
						((MPaqBaseVersion)lstMiniPack.get(i)).getSecuencia(),
						(MPaqBaseVersion)lstMiniPack.get(i), EXME_CtaPac_ID, 
						EXME_CtaPacExt_ID, trxName); 
				//La linea que se esta creando en la tabla EXME_CtaPacDet
				//mantienen una relacion con la linea en la tabla EXME_CtaPacDet
				//por medio de la secuencia
				m_cargo.setSerie(((MPaqBaseVersion)lstMiniPack.get(i)).getSecuencia());
				m_cargo.setEXME_PaqBase_Version_ID(((MPaqBaseVersion)lstMiniPack.get(i)).getEXME_PaqBase_Version_ID());
				m_cargo.setVisible(true);
//				m_cargo.setTipoLinea(MTTCtaPacDet.TIPOLINEA_MiniPack);
				if(!m_cargo.save(trxName)){
					throw new Exception("error.paqute.noSave");
				}
			}
		}

	}
	*/
	/**
	 * Obtengo todos los paquetes relacionados a la cuenta paciente
	 * es posible que algunos paquetes ya esten facturados sin embargo
	 * se seguiran calculando los cargos 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 *
	@SuppressWarnings("rawtypes")
	public static MEXMETPaqBaseDet[] lstPaquete(Properties ctx, int EXME_CtaPac_ID,
    		int AD_Session_ID, BigDecimal secuencia, int paqbaseversion_id, 
    		String tablaOrigen, String fecha, String trxName)throws Exception {

        //8.1.1 GENERAR LISTA CON EL DETALLE DEL PAQUETE INCLUYENDO TODOS LOS PAQUETES
        if(paqbaseversion_id > 0){  //Reporte comparativo de paquetes
            // Obtengo la información del paquete indicado, 
			// relacionado a la ctaPac y se guardan en 
			//la tabla temporal exme_t_paqbasedet
        	MPaqBaseDet.getPaquetes(ctx, EXME_CtaPac_ID, AD_Session_ID, secuencia, paqbaseversion_id, trxName);
        }else{
    		// (Generar PAQUETOTE) Obtengo todos los paquetes relacionados a la cuenta paciente 
    		// agrupados por productos y se guardan en la tabla temporal
    		// exme_t_paqbasedet 
    		MCtaPacPaq.getPaquetes(ctx, EXME_CtaPac_ID, AD_Session_ID, secuencia,  trxName);
		
			//8.1.2 OBTENGO LAS LINEAS QUE YA ESTAN FACTURADAS y/o
    		//que tienen coaseguro y deducible
    		//y verificamos si alguna linea esta partida.
    		//Si la linea es una linea partida se descontara
    		//del paquete esa cantidad, puesto que ya fue considerada
    		MFacturacionEdoCtaPac.getLnsFacturadas(ctx, EXME_CtaPac_ID, AD_Session_ID, tablaOrigen, trxName);
    		
    		//8.1.3.1 LISTA DE SUTITUTOS PREVIAMENTE YA MACHEADOS
    		List listSustitutosPaquete = MEXMEPaquetes.sustitutosCalculadosPaquetes(ctx, EXME_CtaPac_ID, AD_Session_ID, fecha, tablaOrigen, trxName);
    		
    		//8.1.3.2 LISTA DE CARGOS QUE SON SUSTITUTOS EN PREVIO MACHEO
    		List listCargos = MFacturacionEdoCtaPac.sustitutosCalculadosCargos(ctx, EXME_CtaPac_ID, AD_Session_ID, fecha, tablaOrigen, trxName);
    		
    		//8.1.3.3 Detalle paquete prod sustitutos
    		for (int i = 0; i < listSustitutosPaquete.size(); i++) {
    			MFacturacionView sustituto = (MFacturacionView)listSustitutosPaquete.get(i);
    			
    			//Detalle paquete prod cargos
				for (int j = 0; j < listCargos.size(); j++) {
					
					MFacturacionView cargo = (MFacturacionView)listCargos.get(j);//Con cantidad
					
					//Descontamos los sustitutos previamente macheados, del detalle del paquete
					if(cargo.getQtySobrante().compareTo(Env.ZERO)>0
							&& cargo.getProductId() == sustituto.getProductId()
							&& cargo.getUomId() == sustituto.getUomId()){
						//Linea del paquete en macheo
						MEXMETPaqBaseDet paquete = new MEXMETPaqBaseDet(ctx, sustituto.getId(), trxName);
						//Cantidad del paquete menos la cantidad del cargo macheado
						BigDecimal qtyQueda = sustituto.getQtySobrante().subtract(cargo.getQtySobrante());
						
						if(qtyQueda.compareTo(Env.ZERO)>0){
							paquete.setQtyPendiente(qtyQueda);
							((MFacturacionView)listCargos.get(j)).setQtySobrante(Env.ZERO);
						} else {
							//if(qtyQueda.compareTo(Env.ZERO)<=0){
							paquete.setQtyPendiente(Env.ZERO);
							((MFacturacionView)listCargos.get(j)).setQtySobrante(qtyQueda);
							//}
						}
						
						if(!paquete.save(trxName)){
							;//System.out.println("Error");
						}
					}
			
				}//For detalle cargo
				
				
			}//For detalle sustituto
        
        }
		//8.1.4 Con las lineas del paquete que aun tiene cantidad pendiente
		//se hara una comparacion con las lineas que son cargos
		MEXMETPaqBaseDet[] lstPaquete = MEXMETPaqBaseDet.getLnsPendientes(ctx, EXME_CtaPac_ID, AD_Session_ID, secuencia, trxName);
		return lstPaquete;
	}
	
	
	*/
	/**
	 * Lineas conciliadas cargos VS paquetes para una ctapac
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 *
	public static void lineasFacturar(Properties ctx, int EXME_CtaPac_ID,
			int AD_Session_ID, BigDecimal secuencia, boolean isReporte, int paqbaseversion_id, 
			String tablaOrigen, String trxName) throws Exception {
		try {

            //Lineas del paquete a descontar 
            MEXMETPaqBaseDet[] lineasPack = null;
			SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
			MCtaPac m_ctapac = new MCtaPac(ctx, EXME_CtaPac_ID, trxName);
			
			//Si se manda a llamar desde el reporte comparativo de paquetes
			//especificamos el paquete con el cual se llenara la tabla temporal
            if (isReporte) {
                lineasPack =  MEXMEPaquetes.lstPaquete(ctx, EXME_CtaPac_ID, AD_Session_ID, secuencia, paqbaseversion_id, tablaOrigen, sdfFecha.format(m_ctapac.getDateOrdered()), trxName);
            } else {
            	//Regresa las lineas que forman el paquete, con cantidades que ya tienen descontado lo que se ha facturado, o calculado coaseguro y deducible
                lineasPack =  MEXMEPaquetes.lstPaquete(ctx, EXME_CtaPac_ID, AD_Session_ID, secuencia, 0, tablaOrigen, sdfFecha.format(m_ctapac.getDateOrdered()), trxName);
			}

            
            StringBuffer varsProductId = new StringBuffer();
            for (MEXMETPaqBaseDet tPaq : lineasPack) {
        		varsProductId.append(tPaq.getM_Product_ID()).append(",");
            }
            MEXMETPaqBaseDetCargo[] allLineasCargos = MEXMETPaqBaseDetCargo.getAllLineas(ctx, 
            		EXME_CtaPac_ID, varsProductId, trxName);
            
            
			//Barremos las lineas de paquete
			for (int j = 0; j < lineasPack.length; j++) {

				// Obtenemos la cantidad del paquete. 
				// Disminuye hasta cero
				double qtyToPack = lineasPack[j].getQtyPendiente().doubleValue();
				//System.out.println(" Cantidad inicial del producto "+ lineasPack[j].getM_Product_ID() +" del paquete " + qtyToPack );//Favor de no quitar estas lineas aunque esten comentadas
				
				if (qtyToPack > 0.0) {

					//Lineas de los cargos de cualquier extension que no este facturada
					//ni tenga calculado el coaseguro y el deducible 
					//ya que estas no pueden ser tomadas encuenta para el recalculo 
					//de paquetes
//					MEXMETPaqBaseDetCargo[] lineasCargos = MEXMETPaqBaseDetCargo.getLineas(ctx, EXME_CtaPac_ID, lineasPack[j].getM_Product_ID(), trxName);
					MEXMETPaqBaseDetCargo[] lineasCargos = getPaqBaseDetCargo(allLineasCargos, 
							lineasPack[j].getM_Product_ID());
					
					
					// Barremos las lineas de los cargos con el producto del paquete
					for (int i = 0; i < lineasCargos.length ; i++) {

						// cantidad de la linea de cargo. 
						// Disminuye hasta cero
						double qtyToCargo = lineasCargos[i].getQtyPendiente().doubleValue();
						//System.out.println(" Cantidad del producto "+ lineasPack[j].getM_Product_ID() +" del cargo " + qtyToCargo );//Favor de no quitar estas lineas aunque esten comentadas
						
						if (qtyToCargo > 0.0 && qtyToPack > 0.0) {

							double qtySobrante = qtyToPack - qtyToCargo; 
							//System.out.println(" Cantidad sobrante "+qtySobrante );//Favor de no quitar estas lineas aunque esten comentadas
							// si qtySobrante es positivo es que aun queda cantidad de paquete y se acabo la cant del cargo
							// si qtySobrante es negativo es que ya ni queda cantidad del paquete y aun queda cant. del cargo
							// si qtySobrante es cero ya no hay nada que calcular

							if (qtySobrante > 0.0) {
								lineasPack[j].setQtyPendiente(new BigDecimal(qtySobrante));
								lineasCargos[i].setQtyPendiente(Env.ZERO);
								if(!lineasPack[j].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								if(!lineasCargos[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								
							} else if(qtySobrante == 0.0){
								lineasPack[j].setQtyPendiente(Env.ZERO);
								lineasCargos[i].setQtyPendiente(Env.ZERO);
								if(!lineasPack[j].save(trxName)){							
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								if(!lineasCargos[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}

							} else if(qtySobrante < 0.0){
								lineasPack[j].setQtyPendiente(Env.ZERO);
								lineasCargos[i].setQtyPendiente(new BigDecimal(qtySobrante).abs());
								if(!lineasPack[j].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								if(!lineasCargos[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
							}
							
							qtyToPack = lineasPack[j].getQtyPendiente().doubleValue();
						}
					}// fin productos iguales
				}// fin if
			}// fin cantidad de paquete != 0

			/**
			 * Ahora debemos evaluar los sustitutos
			 */
			//Lineas del paquete a descontar. Las lineas deberan tiener la cantidad mayor a cero
	/*		MEXMETPaqBaseDet[] lineasPackTwo = MEXMETPaqBaseDet.getLnsPendientes(ctx, EXME_CtaPac_ID, 
					AD_Session_ID, secuencia, trxName); 
				
				/*MEXMEPaquetes.lstPaquete(ctx, EXME_CtaPac_ID, 
					EXME_CtaPacExt_ID, AD_Session_ID, secuencia, 
					trxName);*/
			
//            varsProductId = new StringBuffer();
//            for (MEXMETPaqBaseDet tPaqTwo : lineasPackTwo) {
//            	System.out.println("==> "+tPaqTwo.getM_Product_ID());
//        		varsProductId.append(tPaqTwo.getM_Product_ID()).append(",");
//            }
//            MEXMETPaqBaseDetCargo[] allSubstitutes = MEXMETPaqBaseDetCargo.getAllSustitutos(ctx,
//					EXME_CtaPac_ID, AD_Session_ID, varsProductId,  
//					sdfFecha.format(m_ctapac.getDateOrdered()), trxName);

			// SUSTITUTOS
	/*		for (int j = 0; j < lineasPackTwo.length; j++) {

				// Obtenemos la cantidad del paquete. Disminuye hasta cero. lo
				// que necesito para el paquete
				double qtyToPack = lineasPackTwo[j].getQtyPendiente().doubleValue();

				if (qtyToPack > 0.0) {

					// Obtengo los sustitutos para este producto, dentro de los cargos
					MEXMETPaqBaseDetCargo[] substitutes = MEXMETPaqBaseDetCargo.getSustitutos(ctx,
							EXME_CtaPac_ID, AD_Session_ID, lineasPackTwo[j].getM_Product_ID(),  
							sdfFecha.format(m_ctapac.getDateOrdered()), trxName);
					
//					MEXMETPaqBaseDetCargo[] substitutes = getPaqBaseDetCargo(allSubstitutes, 
//							lineasPackTwo[j].getM_Product_ID());
					
					//Barremos las lineas de los cargos con el producto del paquete
					for (int i = 0; i < substitutes.length; i++) {
						//substitutes[i].setInvoice_UOM_ID(substitutes[i].getProducto().getC_UOM_ID());

						// cantidad de la linea de cargo. UDM almacenamiento
						// Disminuye hasta cero
						double qtyToCargo = (substitutes[i].getQtyPendiente()).doubleValue();

						if (qtyToCargo > 0.0 && qtyToPack > 0.0) {

							double qtySobrante = qtyToPack - qtyToCargo; 
							// si qtySobrante es positivo es que aun queda cantidad de paquete y se acabo la cant del cargo
							// si qtySobrante es negativo es que ya ni queda cantidad del paquete y aun queda cant. del cargo
							// si qtySobrante es cero ya no hay nada que calcular
							
							if (qtySobrante > 0.0) {
								lineasPackTwo[j].setQtyPendiente(new BigDecimal(qtySobrante));
								substitutes[i].setQtyPendiente(Env.ZERO);
								substitutes[i].setCantidadAlm(substitutes[i].getCantidadAlm().add(new BigDecimal(qtyToCargo)));
								if(!lineasPackTwo[j].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception();
								}
								if(!substitutes[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}

							} else if(qtySobrante == 0.0){
								lineasPackTwo[j].setQtyPendiente(Env.ZERO);
								substitutes[i].setQtyPendiente(Env.ZERO);
								substitutes[i].setCantidadAlm(substitutes[i].getCantidadAlm().add(new BigDecimal(qtyToCargo)));
								if(!lineasPackTwo[j].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								if(!substitutes[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}

							} else if(qtySobrante < 0.0){
								//lineasPackTwo[j].setQtyPendiente(new BigDecimal(qtySobrante).abs());
								//substitutes[i].setQtyPendiente(Env.ZERO);
								lineasPackTwo[j].setQtyPendiente(Env.ZERO);
								substitutes[i].setQtyPendiente(new BigDecimal(qtySobrante).abs());
								substitutes[i].setCantidadAlm(substitutes[i].getCantidadAlm().add(new BigDecimal(qtyToPack)));
								if(!lineasPackTwo[j].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
								if(!substitutes[i].save(trxName)){
									//System.out.println("No jala el 1er save");
									throw new Exception("error.paqute.noSave");
								}
							}
							
							qtyToPack = lineasPackTwo[j].getQtyPendiente().doubleValue();
						}
					}// fin productos iguales
				}// fin for
			}// fin cantidad de paquete != 0

		} catch (Exception e) {
			throw new Exception(Msg.getMsg(ctx, "ErrLineasFacturacion"));
		}
	}
	
	
	
	/**
	 * Lineas conciliadas cargos VS paquetes para una ctapac.
	 * Calculo de los CARGOS de las Cta Paciente para obtener los EXTRAS de la cuenta
	 * 	1. Hacer Macth Cargos vs Paquete (Originales) para obtener cargos extras
	 * 	2. Hacer Macth Cargos vs Sustitutos de productos del Paquete para obtener cargos extras
	 * @author aaranda
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 *
	public static void lineasExtras(int ctaPacId, 
						ArrayList<RegCtaPacDet> arrayCargos, 
						ArrayList<RegCtaPacDet> paqVerGlobal,
						ArrayList<RegCtaPacDet> sustVerGlobal)  {
		 	
			// 1. Hacer Macth Cargos vs Paquete (Originales) para obtener cargos extras
			matchCargosVsPaq(arrayCargos, paqVerGlobal);
			
			// 2. Hacer Macth Cargos vs Sustitutos de productos del Paquete para obtener cargos extras
			matchCargosVsSust(arrayCargos, paqVerGlobal, sustVerGlobal);
		
	}

	
	
	/*
	 * Hacer Macth Cargos vs Sustitutos de productos del Paquete para obtener cargos extras
	 * @author aaranda
	 * @param arrayCargos
	 * @param paqVerGlobal
	 * @param sustVerGlobal
	 *
	@SuppressWarnings("unused")
	private static void matchCargosVsSust_TMP(ArrayList<RegCtaPacDet> arrayCargos, 
				ArrayList<RegCtaPacDet> paqVerGlobal, 
				ArrayList<RegCtaPacDet> sustVerGlobal){
			
			//Si nohay paquetes o Sutitutos no hacer matcho
			if (paqVerGlobal==null || sustVerGlobal==null){
				return;
			}
			
			ordenarCargos(arrayCargos);
			
			for (int i = 0; i < sustVerGlobal.size(); i++) {
				RegCtaPacDet regDetSust = sustVerGlobal.get(i);
				
				// Define el producto del paquete
				int prodSustId = regDetSust.getInt("PRODUCTO_ORIGINAL");
				
				// Buscar el registro del paquete correspondiente al producto del sustituto
				RegCtaPacDet regDetPaq =  searchProdPaq(prodSustId, paqVerGlobal);
				
				// Si no encotro el producto continuar con el siguiente sustituto
				if (regDetPaq==null){
					continue;
				}
				
				// Si no lhay cargos continuar intentando hacer match
				if (arrayCargos==null){
					break;
				}
				
				// Iterar por todos los registros del arreglo de paquetes
				for (int j = 0; j < arrayCargos.size(); j++) {

					// Obtener el mapa del registro del paquete 
					RegCtaPacDet regDetCargo = arrayCargos.get(j);
					
					int prodSust = regDetSust.getInt("PRODUCTO_SUSTITUTO");
					int qtyToCargo =  regDetCargo.getInt("QTYPENDIENTE");
					int qtyToPack = regDetPaq.getInt("CANTIDAD");
					
					// Si el producto del paquete es igual al producto sustituto y la cantidad pendiente del cargo es mayor que cero hacer el match
					if (regDetCargo.getInt("M_PRODUCT_ID")==prodSust && qtyToCargo>0){
					
						//Si las cantidades del paquete son iguales a cero
						//continuar con el sig. producto del paquete Global
						if (qtyToPack <= 0.0){
							break;
						}
						
						//Si las cantidades del cargo son iguales a cero
						//continuar con el sig. producto del cargo
						if (qtyToCargo <= 0.0 ) {
							continue;
						}
						
						// Obtener la diferencia de cantidades Paquetes vs Cargos
						int qtySobrante = qtyToPack - qtyToCargo; 
						
						
						// si qtySobrante es positivo es que aun queda cantidad de paquete y se acabo la cant del cargo
						// si qtySobrante es negativo es que ya ni queda cantidad del paquete y aun queda cant. del cargo
						// si qtySobrante es cero ya no hay nada que calcular
						if (qtySobrante > 0.0) {
							
							//lineasPack[j].setQtyPendiente(new BigDecimal(qtySobrante));
							regDetPaq.removeKey("CANTIDAD");
							regDetPaq.setValue("CANTIDAD", qtySobrante);
							
							//lineasCargos[i].setQtyPendiente(Env.ZERO);
							regDetCargo.removeKey("QTYPENDIENTE");
							regDetCargo.setValue("QTYPENDIENTE", 0);
							
						} else if(qtySobrante == 0.0){
							
							//lineasPack[j].setQtyPendiente(Env.ZERO);
							regDetPaq.removeKey("CANTIDAD");
							regDetPaq.setValue("CANTIDAD", 0);
							
							//lineasCargos[i].setQtyPendiente(Env.ZERO);
							regDetCargo.removeKey("QTYPENDIENTE");
							regDetCargo.setValue("QTYPENDIENTE", 0);
							
						} else if(qtySobrante < 0.0){
							
							//lineasPack[j].setQtyPendiente(Env.ZERO);
							regDetPaq.removeKey("CANTIDAD");
							regDetPaq.setValue("CANTIDAD", 0);
							
							//lineasCargos[i].setQtyPendiente(Env.ZERO);
							regDetCargo.removeKey("QTYPENDIENTE");
							regDetCargo.setValue("QTYPENDIENTE", new BigDecimal(qtySobrante).abs());
							
						}
						
						if (regDetPaq.getInt("CANTIDAD") <= regDetCargo.getInt("QTYPENDIENTE") ){
							break;
						}
						
					}
				}
			}
	}
	
	
	/*
	 * Buscar si el producto del cargo es un producto sustituto del producto original del paquete
	 * @return 	true = si es un sustituto del producto original
	 * 			false = si no es un sustituto del producto original
	 *
	private static boolean searchExistProdSust(int prodCargo, int productoPack, ArrayList<RegCtaPacDet> sustVerGlobal){
		boolean exist =false;
		
		for (int i = 0; i < sustVerGlobal.size(); i++) {
			RegCtaPacDet regDetSust = sustVerGlobal.get(i);
		    
			if(regDetSust.getInt("PRODUCTO_SUSTITUTO")==prodCargo && regDetSust.getInt("PRODUCTO_ORIGINAL")==productoPack){
				exist = true;
				break;
			}
			
		}
		
		return exist;
	}*/
	
	
	
	/*
	 * Hacer Macth Cargos vs Sustitutos de productos del Paquete para obtener cargos extras
	 * @author aaranda
	 * @param arrayCargos
	 * @param paqVerGlobal
	 * @param sustVerGlobal
	 *
	private static void matchCargosVsSust(ArrayList<RegCtaPacDet> arrayCargos, 
				ArrayList<RegCtaPacDet> paqVerGlobal, 
				ArrayList<RegCtaPacDet> sustVerGlobal){
			
			//Si nohay paquetes o Sutitutos no hacer matcho
			if (paqVerGlobal==null || sustVerGlobal==null){
				return;
			}
			
			//ordenarCargos(arrayCargos);
			
			
			//8.3 Barremos las lineas de paquete
			for (int i = 0; i < paqVerGlobal.size(); i++) {
				
				//Registro del detalle del paquete Global a la cta pac
				RegCtaPacDet regDetPaq = paqVerGlobal.get(i);
				int productoPack = regDetPaq.getInt("M_PRODUCT_ID");
				
				int qtyToPack = regDetPaq.getInt("CANTIDAD");
				
				//Si las cantidades del paquete son iguales a cero
				//continuar con el sig. producto del paquete Global
				if (qtyToPack <= 0.0) {
					continue;
				}
				
				// Iterar por todos los registros del arreglo de paquetes
				for (int j = 0; j < arrayCargos.size(); j++) {

					// Obtener el mapa del registro del paquete 
					RegCtaPacDet regDetCargo = arrayCargos.get(j);
					
					int prodCargo =  regDetCargo.getInt("M_PRODUCT_ID");
					int qtyToCargo =  regDetCargo.getInt("QTYPENDIENTE");
					
					//Si las cantidades del cargo son iguales a cero
					//continuar con el sig. producto del cargo
					if (qtyToCargo <= 0.0 ) {
						continue;
					}
					
					// El producto del cargo es sustituto del producto del paquete en proceso
					// Si no es sustituto contitunuar con el siguiente cargo
					if (!searchExistProdSust(prodCargo, productoPack, sustVerGlobal)){
						continue;
					}
					
					// Actualizar la cantidad de productos restantes del paquete
					qtyToPack = regDetPaq.getInt("CANTIDAD");
					
					// Obtener la diferencia de cantidades Paquetes vs Cargos
					int qtySobrante = qtyToPack - qtyToCargo; 
					
					int cantFrei = regDetCargo.getInt("FREIGHTAMT");
					
					// si qtySobrante es positivo es que aun queda cantidad de paquete y se acabo la cant del cargo
					// si qtySobrante es negativo es que ya ni queda cantidad del paquete y aun queda cant. del cargo
					// si qtySobrante es cero ya no hay nada que calcular
					if (qtySobrante > 0.0) {
						
						//lineasPack[j].setQtyPendiente(new BigDecimal(qtySobrante));
						regDetPaq.removeKey("CANTIDAD");
						regDetPaq.setValue("CANTIDAD", qtySobrante);
						
						
						regDetCargo.removeKey("QTYPENDIENTE");
						regDetCargo.setValue("QTYPENDIENTE", 0);
						
						
						regDetCargo.removeKey("FREIGHTAMT");
						regDetCargo.setValue("FREIGHTAMT", cantFrei + qtyToCargo);
						
					} else if(qtySobrante == 0.0){
						
						//lineasPack[j].setQtyPendiente(Env.ZERO);
						regDetPaq.removeKey("CANTIDAD");
						regDetPaq.setValue("CANTIDAD", 0);
						
						//lineasCargos[i].setQtyPendiente(Env.ZERO);
						regDetCargo.removeKey("QTYPENDIENTE");
						regDetCargo.setValue("QTYPENDIENTE", 0);
						

						regDetCargo.removeKey("FREIGHTAMT");
						regDetCargo.setValue("FREIGHTAMT", cantFrei + qtyToCargo);
						
						// la cantidad del paquete es cero
						// no es conveniente seguir buscando 
						// cargos para intentar descontar al paquete
						break;
						
					} else if(qtySobrante < 0.0){
						
						//lineasPack[j].setQtyPendiente(Env.ZERO);
						regDetPaq.removeKey("CANTIDAD");
						regDetPaq.setValue("CANTIDAD", 0);
						
						//lineasCargos[i].setQtyPendiente(Env.ZERO);
						regDetCargo.removeKey("QTYPENDIENTE");
						regDetCargo.setValue("QTYPENDIENTE", new BigDecimal(qtySobrante).abs());
						

						regDetCargo.removeKey("FREIGHTAMT");
						regDetCargo.setValue("FREIGHTAMT", cantFrei + qtyToPack);
						
						// la cantidad del paquete es cero
						// no es conveniente seguir buscando 
						// cargos para intentar descontar al paquete
						break;
						
					}
				
				}//Fin del for cargos indice i
			
			}//Fin del for  paquetes indice j	

	}
	
	/*
	 * Buscar en el arreglo de paquetes un Id de Producto especifico y 
	 * devolver el mapa del registro correspondientes al producto
	 * Arturo
	 *
	private static RegCtaPacDet searchProdPaq(int prodId, ArrayList<RegCtaPacDet> paqVerGlobal){
		RegCtaPacDet regDetProd = null;
		
		boolean encontrado = false;
		
		// Iterar por todos los registros del arreglo de paquetes
		for (int i = 0; i < paqVerGlobal.size(); i++) {
			
			// Obtener el mapa del registro del paquete 
			regDetProd = paqVerGlobal.get(i);

			// Si el producto del paquete es igual al producto sustituto terminar la iteracion
			if (regDetProd.getInt("M_PRODUCT_ID")==prodId && regDetProd.getInt("CANTIDAD")>0 ){
				encontrado = true;
				break;
			}
		}
		
		// Si  no se encontro el producto devolver nulo de lo contrario devolver el registro del mapa encontrado
		if (!encontrado) regDetProd = null;

		return regDetProd;
		
	}

	
	/*
	 * Buscar en el arreglo de paquetes un Id de Producto especifico y 
	 * devolver el mapa del registro correspondientes al producto
	 * Arturo
	 *
	@SuppressWarnings("unused")
	private static RegCtaPacDet searchProdCargo(int prodId, ArrayList<RegCtaPacDet> arrayCargos){
		RegCtaPacDet regDetProd = null;
		
		boolean encontrado = false;
		
		// Iterar por todos los registros del arreglo de paquetes
		for (int i = 0; i < arrayCargos.size(); i++) {
			
			// Obtener el mapa del registro del paquete 
			regDetProd = arrayCargos.get(i);

			// Si el producto del paquete es igual al producto sustituto terminar la iteracion
			if (regDetProd.getInt("M_PRODUCT_ID")==prodId && regDetProd.getInt("QTYPENDIENTE")>0){
				encontrado = true;
				break;
			}
		}
		
		// Si  no se encontro el producto devolver nulo de lo contrario devolver el registro del mapa encontrado
		if (!encontrado) regDetProd = null;

		return regDetProd;
		
	}
	
	/*** * Arturo **
	private static void matchCargosVsPaq(ArrayList<RegCtaPacDet> arrayCargos, 
										ArrayList<RegCtaPacDet> paqVerGlobal){
		
		//Se ordenan los productos del cargo 
		//ordenarCargos(arrayCargos);  // 
		
		// Si no hay paquetes no hacer match
		if (paqVerGlobal==null){
			return;
		}
		
		//8.3 Barremos las lineas de paquete
		for (int j = 0; j < paqVerGlobal.size(); j++) {
			
			//Registro del detalle del paquete Global a la cta pac
			RegCtaPacDet regDetVersion = paqVerGlobal.get(j);
			
			// Obtenemos la cantidad del paquete. 
			// Disminuye hasta cero
			int qtyToPack = regDetVersion.getInt("CANTIDAD");
			
			//Exista cantidad a comparar
			if (qtyToPack > 0.0) {

				//
				@SuppressWarnings("unused")
				int productoId = 0;
				
				//Si no hay cargos no hacer mathc
				if (arrayCargos==null){
					break;
				}
				
				// Barremos las lineas de los cargos con el producto del paquete
				for (int i = 0; i < arrayCargos.size() ; i++) {
					
					//Resgistro del cargo de la cta pac
					RegCtaPacDet regDetCargos = arrayCargos.get(i);
					
					//Si el producto del paquete es diferente al producto del cargo
					//continuar con el sig. producto del paquete Global
					if (regDetVersion.getInt("M_PRODUCT_ID") != regDetCargos.getInt("M_PRODUCT_ID")){
						continue;
					}
					
					//Si el producto del paquete es diferente al producto del cargo
					//continuar con el sig. producto del paquete Global
					if (regDetVersion.getInt("C_UOM_ID") != regDetCargos.getInt("C_UOM_ID")){
						continue;
					}
					
					// cantidad de la linea de cargo. 
					// Disminuye hasta cero
					int qtyToCargo =    regDetCargos.getInt("QTYPENDIENTE") ;
					
					//Producto del cargo a evaluar
					productoId =  regDetCargos.getInt("M_PRODUCT_ID");
						
					//Si las cantidades del paquete y del cargo son iguales a cero
					//continuar con el sig. producto del paquete Global
					if (qtyToCargo <= 0.0 || qtyToPack <= 0.0) {
						break;
					}
					
					// Obtener la diferencia de cantidades Paquetes vs Cargos
					int qtySobrante = qtyToPack - qtyToCargo; 
					
					
					// si qtySobrante es positivo es que aun queda cantidad de paquete y se acabo la cant del cargo
					// si qtySobrante es negativo es que ya ni queda cantidad del paquete y aun queda cant. del cargo
					// si qtySobrante es cero ya no hay nada que calcular
					if (qtySobrante > 0.0) {
						
						//lineasPack[j].setQtyPendiente(new BigDecimal(qtySobrante));
						regDetVersion.removeKey("CANTIDAD");
						regDetVersion.setValue("CANTIDAD", qtySobrante);
						
						//lineasCargos[i].setQtyPendiente(Env.ZERO);
						regDetCargos.removeKey("QTYPENDIENTE");
						regDetCargos.setValue("QTYPENDIENTE", 0);
						
					} else if(qtySobrante == 0.0){
						
						//lineasPack[j].setQtyPendiente(Env.ZERO);
						regDetVersion.removeKey("CANTIDAD");
						regDetVersion.setValue("CANTIDAD", 0);
						
						//lineasCargos[i].setQtyPendiente(Env.ZERO);
						regDetCargos.removeKey("QTYPENDIENTE");
						regDetCargos.setValue("QTYPENDIENTE", 0);
						
					} else if(qtySobrante < 0.0){
						
						//lineasPack[j].setQtyPendiente(Env.ZERO);
						regDetVersion.removeKey("CANTIDAD");
						regDetVersion.setValue("CANTIDAD", 0);
						
						//lineasCargos[i].setQtyPendiente(Env.ZERO);
						regDetCargos.removeKey("QTYPENDIENTE");
						regDetCargos.setValue("QTYPENDIENTE", new BigDecimal(qtySobrante).abs());
						
					}
					
					
					qtyToPack  = regDetVersion.getInt("CANTIDAD");
					
					
					
					
					/*if (productoId!=0 && i< arrayCargos.size()){
						if (productoId!=arrayCargos.get(i+1).getInt("M_PRODUCT_ID") ){
							break;
						}
					}*/
											
	/*			}// fin de for de cargos 
			}// fin if
			//paqVerGlobal.set(j, regDetVersion);
		}// fin cantidad de paquete != 0
	}
	
	private static void ordenarCargos(List<RegCtaPacDet> aCargos) {
		Collections.sort(aCargos);
	}
	
	
	private static MEXMETPaqBaseDetCargo[] getPaqBaseDetCargo (
			MEXMETPaqBaseDetCargo[] lineasCargos, int mProductId ) {
		
		List<MEXMETPaqBaseDetCargo> list = new ArrayList <MEXMETPaqBaseDetCargo>();
		
		for (int i=0; i<lineasCargos.length; i++) {
			
			if (lineasCargos[i].getM_Product_ID() == mProductId) {
				list.add(lineasCargos[i]);
			}
		}
		
		MEXMETPaqBaseDetCargo[] lineas = new MEXMETPaqBaseDetCargo[list.size()];
		list.toArray(lineas);
		return lineas;
	}


	/**
	 * Permite agregar la linea del paquete
	 * 
	 * @param secuencia
	 * @return
	 */
/*	public static MTTCtaPacDet getLineasPaquete(Properties ctx, int secuencia,
			MPaqBaseVersion pack, int EXME_CtaPac_ID, int EXME_CtaPacExt_ID, 
			String trxName) {

		MTTCtaPacDet chargePaq = new MTTCtaPacDet(ctx, 0, trxName);

		chargePaq.setEXME_CtaPac_ID(EXME_CtaPac_ID);
		chargePaq.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
		chargePaq.setC_Currency_ID(pack.getC_Currency_ID());
		chargePaq.setM_Product_ID(pack.getM_Product_ID());
		chargePaq.setC_UOM_ID(pack.getProduct().getC_UOM_ID());
		chargePaq.setInvoice_UOM_ID(pack.getProduct().getC_UOM_ID());
		chargePaq.setDescription("Cargo por concepto de Paquete - ["
				+ pack.get_ID() + ", "
				+ pack.getName() + "]");
		chargePaq.setTipoLinea("PB");
        chargePaq.setEXME_PaqBase_Version_ID(pack.getEXME_PaqBase_Version_ID());
        chargePaq.setDiscount(Env.ZERO);
        chargePaq.setDiscountAmt(Env.ZERO);
        chargePaq.setDiscountFam(Env.ZERO);
		
        return getLineasCargo(ctx, secuencia, chargePaq, trxName);
	}
*/
	/**
	 * Agrega valores a las columnas no nulas para la tabla EXME_CtaPacDet
	 * 
	 * @param secuencia
	 * @param cargo
	 * @return
	 *
	public static MCtaPacDet getLineasCargo(Properties ctx, int secuencia,
			MCtaPacDet cargo, String trxName) {
		cargo.setLine(0);
		cargo.setDateOrdered(new Timestamp(System.currentTimeMillis()));
		cargo.setQtyOrdered(Env.ONE);
		cargo.setQtyReserved(Env.ONE);
		cargo.setQtyDelivered(Env.ONE);
		cargo.setQtyInvoiced(Env.ONE);
		cargo.setFreightAmt(Env.ZERO);
		cargo.setChargeAmt(Env.ZERO);
		cargo.setIsDescription(false);
		cargo.setQtyPaquete(Env.ZERO);
		cargo.setQtyPendiente(Env.ONE);
		cargo.setIsFacturado(false);
		cargo.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
		cargo.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
		cargo.setSecuencia(secuencia);
		cargo.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));// El
																				// almacen
																				// lo
		// tomamos del
		// contexto.
		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getContextAsInt(ctx,
				"#EXME_EstServ_ID"), trxName);
		cargo.setTipoArea(estServ.getTipoArea());
		cargo.setEXME_Area_ID(estServ.getEXME_Area_ID());
		cargo.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
		return cargo;
	}
	
	public static MTCtaPacDet getLineasCargo(Properties ctx, int secuencia,
			MTCtaPacDet cargo, String trxName) {
		cargo.setLine(0);
		cargo.setDateOrdered(new Timestamp(System.currentTimeMillis()));
		cargo.setQtyOrdered(Env.ONE);
		cargo.setQtyReserved(Env.ONE);
		cargo.setQtyDelivered(Env.ONE);
		cargo.setQtyInvoiced(Env.ONE);
		cargo.setFreightAmt(Env.ZERO);
		cargo.setChargeAmt(Env.ZERO);
		cargo.setIsDescription(false);
		cargo.setQtyPaquete(Env.ZERO);
		cargo.setQtyPendiente(Env.ONE);
		cargo.setIsFacturado(false);
		cargo.setAD_User_ID(Env.getContextAsInt(ctx, "#AD_User_ID"));
		cargo.setAD_Session_ID(Env.getContextAsInt(ctx, "#AD_Session_ID"));
		cargo.setSecuencia(secuencia);
		cargo.setM_Warehouse_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));// El
																				// almacen
																				// lo
		// tomamos del
		// contexto.
		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getContextAsInt(ctx,
				"#EXME_EstServ_ID"), trxName);
		cargo.setTipoArea(estServ.getTipoArea());
		cargo.setEXME_Area_ID(estServ.getEXME_Area_ID());
		cargo.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
		return cargo;
	}
	/**
	 * 
	 * @param ctx
	 * @param secuencia
	 * @param pack
	 * @param chargePaq
	 * @param EXME_CtaPac_ID
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 *
	public static MCtaPacDet getLineasPaqueteTax(Properties ctx, 
			MEXMEPaqBaseTax pack, MCtaPacDet chargePaq, String trxName) {
		chargePaq.setCosto(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceList(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceActual(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceLimit(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setLineNetAmt(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setC_Tax_ID(pack.getC_Tax_ID());
		chargePaq.setPriceActualInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceListInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceLimitInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setTaxAmt(pack.getTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		return chargePaq;
	}
	public static MTCtaPacDet getLineasPaqueteTaxT(Properties ctx, 
			MEXMEPaqBaseTax pack, MTCtaPacDet chargePaq, String trxName) {
		chargePaq.setCosto(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceList(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceActual(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceLimit(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setLineNetAmt(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setC_Tax_ID(pack.getC_Tax_ID());
		chargePaq.setPriceActualInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceListInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setPriceLimitInv(pack.getTaxBaseAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		chargePaq.setTaxAmt(pack.getTaxAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		return chargePaq;
	}	
*/
	public static List<MCtaPacDet> getLineasPaqueteNoVisibles(Properties ctx, MCtaPacDet paquete, 
			int target_CtaPacExt_ID, String trxName) {
		
		return MCtaPacDet.getCambiaExtPaq(ctx, paquete.getEXME_PaqBase_Version_ID(),  
				paquete.getEXME_CtaPacExt_ID(), paquete.getEXME_CtaPac_ID(), 
				paquete.getEXME_CtaPacDet_ID(), paquete.getSerie(), 
				target_CtaPacExt_ID, trxName);

	}
	


	/**
	 * Actualiza el paquete por si el paquete fue editado
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param EXME_CtaPacExt_ID
	 * @param tablaDestino
	 * @param trxName
	 *
	public static List<MFacturacionView> sustitutosCalculadosPaquetes(Properties ctx, int EXME_CtaPac_ID,
			int AD_Session_ID, String fecha, String tablaOrigen, String trxName) {
		
		List<MFacturacionView> lista = new ArrayList<MFacturacionView>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append(" SELECT EXME_T_PaqBaseDet.EXME_T_PaqBaseDet_ID, M_Substitute.M_Product_ID, M_Substitute.Substitute_ID, EXME_T_PaqBaseDet.QtyPendiente ")
		.append(" FROM M_Substitute ")
		.append(" INNER JOIN EXME_T_PaqBaseDet ON ( EXME_T_PaqBaseDet.M_Product_ID = M_Substitute.M_Product_ID AND EXME_T_PaqBaseDet.IsActive = 'Y')  ")
		.append(" INNER JOIN ").append(tablaOrigen).append(" ON ( ").append(tablaOrigen).append(".M_Product_Id = M_Substitute.Substitute_ID  )  ")
		.append(" INNER JOIN EXME_CtaPacExt cpe ON ( cpe.EXME_CtaPacExt_ID = ").append(tablaOrigen).append(".EXME_CtaPacExt_ID ) ")
		
		.append(" WHERE M_Substitute.IsActive = 'Y' AND EXME_T_PaqBaseDet.EXME_CtaPac_ID = ? ")
		.append(" AND EXME_T_PaqBaseDet.AD_Session_ID = ? AND EXME_T_PaqBaseDet.QtyPendiente > 0  ")
		.append(" AND M_Substitute.ValidFrom <= to_date(?,'dd/mm/yyyy') AND M_Substitute.ValidTo >= to_date(?,'dd/mm/yyyy') ")  
		
		.append( new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_T_PaqBaseDet"))).append(" ")
		.append( new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Substitute"))).append(" ")
		.append( new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", tablaOrigen))).append(" ")
		
		.append(" AND ").append(tablaOrigen).append(".IsActive = 'Y'  AND ").append(tablaOrigen).append(".EXME_CtaPac_ID = ?  ")
		.append(" AND ").append(tablaOrigen).append(".EXME_PaqBase_Version_ID IS NULL AND ").append(tablaOrigen).append(".FreightAmt > 0      ")
		.append(" AND ( cpe.C_Invoice_ID IS NOT NULL  OR cpe.IsInvoiced = 'Y' OR cpe.Coaseguro IS NOT NULL OR cpe.Coaseguro <> 0   ")
		.append(" OR cpe.Deducible IS NOT NULL OR cpe.Deducible <> 0 )      ");
		
		if(tablaOrigen.equalsIgnoreCase("EXME_T_CtaPacCargos"))
			sql.append(" AND EXME_T_CtaPacCargos.AD_Session_ID = ?  ");
			
		sql.append(" GROUP BY EXME_T_PaqBaseDet.EXME_T_PaqBaseDet_ID, M_Substitute.M_Product_ID, M_Substitute.Substitute_ID,  EXME_T_PaqBaseDet.QtyPendiente  ")
		.append(" ORDER BY M_Substitute.Substitute_ID ASC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setInt(2, AD_Session_ID);
			pstmt.setString(3, fecha);
			pstmt.setString(4, fecha);
			pstmt.setInt(5, EXME_CtaPac_ID);
			if(tablaOrigen.equalsIgnoreCase("EXME_T_CtaPacCargos"))
				pstmt.setInt(6, AD_Session_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				MFacturacionView paqbaseView = new MFacturacionView();
				paqbaseView.setProductId(rs.getInt(3));
				paqbaseView.setQtySobrante(rs.getBigDecimal(4));//Qty dentro del paquete
				paqbaseView.setId(rs.getInt(1));
				lista.add(paqbaseView);				
			}
			
		
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		
		return lista;

	}*/
}
