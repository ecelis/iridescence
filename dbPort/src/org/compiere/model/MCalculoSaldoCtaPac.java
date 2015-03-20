package org.compiere.model;

/** @Deprecated*/
public class MCalculoSaldoCtaPac {

	
/*	public static void lineasDeFacturacion(Properties ctx, int EXME_CtaPac_ID,
			int EXME_CtaPacExt_ID, String tabla, String trxName) throws Exception {
		
		//List linesCtaPacDet = new ArrayList();
		try {
			//el siguiente numero de secuencia, permite que si el usuario
			// entra nuevamente sepamos en que sesion del usuario vamos
		//	int secuencia = MFacturacionEdoCtaPac.getNumSecu(ctx, tabla, null) + 1;
			/**
			 * Como las lineas originales (tabla exme_ctapacdet) 
			 * han sido afectadas por las devoluciones, 
			 * se borraran las lineas de TTCtaPacDet que no esten ni facturadas,
			 * ni que su extension tenga calculo de coaseguro y/o deducible,
			 * y que la columna exme_ctapacdet_id no sea nula,
			 */
		//	MFacturacionEdoCtaPac.isClear(ctx, EXME_CtaPac_ID, Env.getContextAsInt(ctx, "#AD_Session_ID"), tabla, trxName);
			
			//Limpiar la tabla de EXME_T_PaqBaseDet y EXME_T_PaqBaseDetCargo.
		//	MEXMETPaqBaseDet.borrarLineas(ctx, EXME_CtaPac_ID, true, trxName);
			
			/**
			 * traerse todas las lineas que estan pendientes de visualizarse
			 * en la facturacion por extensiones, es decir las lineas que esten
			 * en EXME_CtaPacDet y no en EXME_CtaPacDet.
			 * Como inicialmente se borro la tabla de EXME_CtaPacDet 
			 * Los cargos obtenidos son sin devoluciones,
			 * Los cargos estan en orden ascendente para que se consideren los cargos
			 * en el orden en que fueron ingresados
			 * Se guardaran en una tabla de trabajo para la comparacion de paquetes
			 */
			
			//Linea para traerse los cargos (cargos que no tengan calculado coaseguro ni deducible en su extension ni que esten facturadas) 
			//y Se guardaran en la tabla de trabajo. 
		//	MCtaPacDet.getCargosPendientes(ctx, EXME_CtaPac_ID, new BigDecimal( secuencia ), true, trxName);
			
			//Verificar que cargos estan dentro del paquete
		//	MEXMEPaquetes.lineasFacturar(ctx, EXME_CtaPac_ID, EXME_CtaPacExt_ID, Env.getContextAsInt(ctx, "#AD_Session_ID"),
		//			new BigDecimal( secuencia ), false, 0, trxName);
			
			//Actualizo la cantidad pendiente dentro de a tabla EXME_CtaPacDet 
		//	MEXMETPaqBaseDetCargo.actualizaLineasCargo(ctx, 
		//			EXME_CtaPac_ID, Env.getContextAsInt(ctx, "#AD_Session_ID"), new BigDecimal( secuencia), trxName);
	//		
			//Sacar extras Linea para traerse los cargos y guardar en tabla TTCtaPacDet 
	//		MCtaPacDet.getCargosPendientes(ctx, EXME_CtaPac_ID, new BigDecimal( secuencia ), false, trxName);
			
			//Guardar lineas de mini paquetes y paquetes que aun no han sido
			//incluidos en la extension cero
		//	MEXMEPaquetes.miniPaquete(ctx, EXME_CtaPac_ID, EXME_CtaPacExt_ID, trxName);
	
		//	lineasPaquetes(ctx, EXME_CtaPac_ID, trxName);
			
	/*	} catch (Exception e) {
		//	s_log.log(Level.SEVERE, "lineasDeFacturacion", e);
			throw new Exception(e);
		}

	}
*/
}
