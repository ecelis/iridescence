package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.action.ActionErrors;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEBPartnerExcepciones {

//    /** Static Logger               */
//    private static CLogger      s_log = CLogger.getCLogger (MEXMEBPartnerExcepciones.class);
//    
//    /**
//     * Formato de fecha
//     */
//    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
//
////    /**
////     * Nos permitira tomar los datos dependiento de que objeto
////     * se le a enviado como parametro
////     * @param ctx
////     * @param cargo
////     * @param linea
////     * @param C_BPartner_ID
////     * @param trxName
////     * @return Si es mayor a cero se encontro una excepcion del socio
////     * de negocios
////     * 		Si es cero no se encontro nunguna excepcion del socio de 
////     * negocios
////     * 		Si es menor a cero hubo conflictos para encontrar la excepcion
////     */
////     public static int excepcionesFacturacion(Properties ctx, MCtaPacDet cargo, 
////    		MEXMELineasFactView linea, int C_BPartner_ID, String trxName) {
////
////    	int excepcion = 0;
////    	int productoID = 0;
////    	int warehouseId = 0;
////    	Timestamp fecha = null;
////    	
////    	//Dependiendo de donde es invocado el proceso sera 
////    	if(cargo!=null){
////    		productoID = cargo.getM_Product_ID();
////        	warehouseId = cargo.getM_Warehouse_ID();
////        	fecha = cargo.getDateOrdered();
////    	}else{
////    		if(linea!=null){
////    			productoID = linea.getM_Product_ID();
////            	warehouseId = linea.getM_Warehouse_ID();
////            	fecha = linea.getLineaInvoice().getUpdated();
////    		}
////    	} 
////    	
////    	excepcion = excepciones(ctx, C_BPartner_ID, 
////    			productoID,fecha, warehouseId,  trxName);
////    	
////    	return excepcion;
////    }
////    
//	/**
//	 *  Metodologia con los pasos para la busqueda de las excepciones de socios de negocios
//	 *
//	 *@param  precioList  Precio sobre el que se hacen los calculos de descuento
//	 *@return             una cadena con los motivos con los cuales no se calculo
//	 *      descuento que no son errores
//	 */
//	public static int excepciones(Properties ctx, int C_BPartner_ID, 
//			int M_Product_ID, Timestamp fecha, int M_Warehouse_ID,  
//			String trxName) {
//    	
//		int excepcion = 0;
//        if (C_BPartner_ID <=0 || M_Product_ID<=0) {
//        	s_log.log(Level.SEVERE,"Requerido Socio de Negocios y Producto ");
//            return -1;
//		}
//
//        //Socio de negocios
//        MBPartner m_BPartner = new MBPartner(ctx, C_BPartner_ID, trxName);
//        
//        //Producto
//        MProduct m_Product = new MProduct(ctx, M_Product_ID, trxName);
//        
//        if(fecha ==null)
//            fecha = DB.getTimestampForOrg(ctx);
//        //
//        String cad = "";
//        
//        // Socio - Producto
//        cad = " AND EXME_BPartner_Excep.C_BPartner_ID = " + 
//			     m_BPartner.getC_BPartner_ID() + 
//			     " AND EXME_BPartner_Excep.M_Product_ID = " + 
//			     m_Product.getM_Product_ID();
//        excepcion = calculo(ctx, cad, fecha, M_Warehouse_ID, trxName); 
//		if (excepcion==0) {
//		    // Socio - Categoria
//			cad = " AND EXME_BPartner_Excep.C_BPartner_ID = " + 
//			      m_BPartner.getC_BPartner_ID() + 
//			      " AND EXME_BPartner_Excep.M_Product_Category_ID = " + 
//			      m_Product.getM_Product_Category_ID();
//			excepcion = calculo(ctx, cad, fecha, M_Warehouse_ID, trxName); 
//            if (excepcion==0) {
//                // Grupo - Producto
//				cad = " AND EXME_BPartner_Excep.C_BP_Group_ID = " + 
//				      m_BPartner.getC_BP_Group_ID() + 
//				      " AND EXME_BPartner_Excep.M_Product_ID = " + 
//				      m_Product.getM_Product_ID();
//				excepcion = calculo(ctx, cad, fecha, M_Warehouse_ID, trxName); 
//                if (excepcion==0) {
//                    // Grupo - Categoria
//					cad = " AND EXME_BPartner_Excep.C_BP_Group_ID = " + 
//					      m_BPartner.getC_BP_Group_ID() + 
//					      " AND EXME_BPartner_Excep.M_Product_Category_ID = " + 
//					      m_Product.getM_Product_Category_ID();
//					excepcion = calculo(ctx, cad, fecha, M_Warehouse_ID, trxName); 
//                    if (excepcion==0) {
//                        m_BPartner = null;
//                        m_Product = null;
//                        s_log.log(Level.INFO,"No hubo excepcion ");
//						return excepcion;
//					}
//				}
//			}
//		}
//        
//        m_BPartner = null;
//        m_Product = null;
//		
//		return excepcion;
//	}
//
//
//	/**
//	 *  Asigna el identificador de la excepcion
//	 *
//	 *@param  cad    parte del where que permite hacer la busqueda de las excepciones
//	 *@param  fecha  actual
//	 *@return un valor mayor a cero si se encontro una excepcion
//	 */
//	public static int calculo(Properties ctx, String cad, Timestamp fecha, 
//            int M_Warehouse_ID, String trxName) {
//        
//		int excepcion = 0;
//		
//		String sql = " SELECT EXME_BPartner_Excep.EXME_BPartner_Excep_ID " +
//				" FROM EXME_BPartner_Excep " +
//				" WHERE EXME_BPartner_Excep.IsActive = 'Y' " +
//                " AND to_date(?,'dd/MM/yyyy') BETWEEN " +
//                " EXME_BPartner_Excep.ValidFrom AND EXME_BPartner_Excep.ValidTo AND ";
//                
//                if(M_Warehouse_ID>0)
//                    sql += " ( EXME_BPartner_Excep.M_Warehouse_ID = "+M_Warehouse_ID+" OR  ";
//                
//                sql +=" EXME_BPartner_Excep.M_Warehouse_ID IS NULL ";
//                
//                if(M_Warehouse_ID>0)
//                    sql += " ) ";
//        
//        if (cad != null)
//            sql += cad;
//		
//		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_BPartner_Excep");
//		
//		sql += " ORDER BY " + 
//               " EXME_BPartner_Excep.ValidFrom DESC ";
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//            pstmt = DB.prepareStatement(sql, trxName);
//            pstmt.setString(1, formatoFecha.format(fecha));
//            
//			rs = pstmt.executeQuery();
//			if(rs.next()){
//			    excepcion = rs.getInt("EXME_BPartner_Excep_ID");
//            }
//			
//    	} catch (Exception e) {
//    		excepcion = -1;
//    		s_log.log(Level.SEVERE, sql.toString(), e);
//     	}finally {
//     		DB.close(rs,pstmt);
//    	}
//		
//		return excepcion;
//	}
//	
//	
//	
//	  /**
//     * Nos permitira tomar los datos dependiento de que objeto
//     * se le a enviado como parametro
//     * @param ctx
//     * @param cargo
//     * @param linea
//     * @param C_BPartner_ID
//     * @param trxName
//     * @return Si es mayor a cero se encontro una excepcion del socio
//     * de negocios
//     * 		Si es cero no se encontro nunguna excepcion del socio de 
//     * negocios
//     * 		Si es menor a cero hubo conflictos para encontrar la excepcion
//     */
//    public static int excepciones(Properties ctx, int C_BPartner_ID,
//    		HashMap<Integer, List<MCtaPacDet>> hashMapCargosSeleccionados, 
//    		String trxName) {
//
//    	int excepcion = 0;
//    	int productoID = 0;
//    	int warehouseId = 0;
//    	Timestamp fecha = null;
//    	
//    	//Dependiendo de donde es invocado el proceso ser� 
//    	
//    	excepcion = excepciones(ctx, C_BPartner_ID, 
//    			productoID,fecha, warehouseId,  trxName);
//    	
//    	return excepcion;
//    }
//
//    /**
//     * Hace una busqueda para encontrar las excepciones de un grupo de productos,
//     * podr�a ser que 
//     * @param ctx: Contexto
//     * @param productos: Cadena de Id's de Productos
//     * @param fecha: Fecha de 
//     * @param m_BPartner: Objeto de Socio de Negocio
//     * @param hashMapCargosSeleccionados: Mapa de cargos seleccionados para mover
//     * @param trxName
//     * @return
//     * @throws SQLException
//     */
//    public static HashMap<Integer,MCtaPacDet> busqueda(Properties ctx, String productos, String fecha, 
//    		MBPartner m_BPartner, HashMap<Integer, List<MCtaPacDet>> hashMapCargosSeleccionados,
//    		String trxName) throws SQLException {
//
//    	HashMap<Integer,MCtaPacDet> listaExcepcionesCargos = new HashMap<Integer,MCtaPacDet>();
//    	
//    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//    	sql.append(" SELECT bpe.*, p.M_Product_id As prod   ")
//    	.append(" FROM EXME_BPartner_Excep bpe ")
//    	.append(" LEFT  JOIN M_Product_Category pc ON bpe.M_Product_Category_ID = pc.M_Product_Category_ID ")
//    	.append(" INNER JOIN M_Product p           ON  pc.M_Product_Category_ID = p.M_Product_Category_ID ")
//    	.append(" WHERE bpe.IsActive = 'Y' ");
//    	if (DB.isOracle()) {
//    		sql.append(" AND TO_DATE( ?,'dd/MM/yyyy') BETWEEN  TRUNC(bpe.ValidFrom) AND TRUNC(bpe.ValidTo) ");
//    	} else if (DB.isPostgreSQL()) {
//    		sql.append(" AND TO_DATE( ?,'dd/MM/yyyy') BETWEEN  DATE_TRUNC('day', bpe.ValidFrom) AND DATE_TRUNC('day', bpe.ValidTo) ");
//    	}
//    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_BPartner_Excep", "bpe"))
//    	.append(" OR ( bpe.C_BPartner_ID = ? ") 
//    	.append(" AND bpe.M_Product_ID IN ( ").append(productos).append(" ) ")
//    	.append(" )")
//    	.append(" OR ( bpe.C_BPartner_ID = ? ")
//    	.append(" AND bpe.M_Product_Category_ID IN ")
//    	.append(" (SELECT M_Product_Category_ID FROM M_Product WHERE M_Product_ID IN ( ").append(productos).append(" ) ) ")
//    	.append(" )")
//    	.append(" OR ( bpe.C_BP_Group_ID = ? ") 
//    	.append(" AND bpe.M_Product_ID IN ( ") 
//    	.append(productos).append(" ) ")
//    	.append(" )")
//    	.append(" OR ( bpe.C_BP_Group_ID = ? ")
//    	.append(" AND bpe.M_Product_Category_ID IN ")
//    	.append(" (SELECT M_Product_Category_ID FROM M_Product WHERE M_Product_ID IN ( ").append(productos).append(" ) ) ")
//    	.append(" )")
//    	.append(" ORDER BY ")
//    	.append(" bpe.ValidFrom DESC ");
//
//    	PreparedStatement pstmt = null;
//    	ResultSet rs = null;
//    	try {
//    		pstmt = DB.prepareStatement(sql.toString(), null);
//    		pstmt.setString(1, fecha);
//    		pstmt.setInt(2, m_BPartner.getC_BPartner_ID());
//    		pstmt.setInt(3, m_BPartner.getC_BPartner_ID());
//    		pstmt.setInt(4, m_BPartner.getC_BP_Group_ID());
//    		pstmt.setInt(5, m_BPartner.getC_BP_Group_ID());
//
//    		rs = pstmt.executeQuery();
//    		while(rs.next()){
//
//    			MEXMEBPartner_Excep excep = new MEXMEBPartner_Excep(ctx, rs, null);
//
//    			int productID = 0;
//    			if(excep.getM_Product_ID()>0 && hashMapCargosSeleccionados.containsKey(excep.getM_Product_ID()))
//    				productID = excep.getM_Product_ID();
//    			if(excep.getM_Product_Category_ID()>0 && hashMapCargosSeleccionados.containsKey(rs.getInt("prod")))
//    				productID = rs.getInt("prod");
//
//    			if(productID<=0)
//    				continue;
//
//    			List<MCtaPacDet> detalle = hashMapCargosSeleccionados.get(productID);
//    			for (int i = 0; i < detalle.size(); i++) {
//
//    				if(listaExcepcionesCargos.containsKey(detalle.get(i).getEXME_CtaPacDet_ID()))
//    					continue;
//
//    				if( detalle.get(i).getDateOrdered().after(excep.getValidTo()) )
//    					continue;
//
//    				if(excep.getM_Warehouse_ID()>0 ){ 
//    					if( detalle.get(i).getM_Warehouse_ID() == excep.getM_Warehouse_ID())
//    						listaExcepcionesCargos.put( detalle.get(i).getEXME_CtaPacDet_ID(), detalle.get(i));
//    				}else{
//    					listaExcepcionesCargos.put( detalle.get(i).getEXME_CtaPacDet_ID(), detalle.get(i));
//    				}
//    			}
//    		}
//    	} catch (Exception e) {
//    		s_log.log(Level.SEVERE, sql.toString(), e);
//    	}finally {
//    		DB.close(rs,pstmt);
//    		pstmt = null;
//    		rs = null;
//    	}
//
//    	return listaExcepcionesCargos;
//    }
//	
//	/**
//	 * Verifica las Excepciones de los cargos
//	 * @param ctx: Contexto
//	 * @param selectedExtension: Extension origen
//	 * @param extTarget: Extension Destino
//	 * @param deducibleID: ID del Producto Deducible
//	 * @param coaseguroID: ID del Producto Coaseguro
//	 * @param errors: Objeto con Errores
//	 * @param ctaPac: Objeto con datos de la cuenta paciente
//	 * @return HashMap<Integer,MTTCtaPacDet>
//	 * @throws SQLException
//	 */
//    public static HashMap<Integer,MCtaPacDet> cargosExcepciones(Properties ctx, MEXMECtaPacExt selectedExtension, 
//    		MEXMECtaPacExt extTarget, int deducibleID, int coaseguroID, ActionErrors errors, MEXMECtaPac ctaPac )
//    		throws SQLException{
//
//    	HashMap<Integer,MCtaPacDet> listaExcepciones=null;
//    	//Solo cuando la extension Destino es diferente de la 0
//    	if(extTarget.getExtensionNo() != 0){
//
//    		String cadenaIdProduct = "";
//    		HashMap<Integer, List<MCtaPacDet>> hashMapProductosSeleccionados = new HashMap<Integer, List<MCtaPacDet>>();
//    		List<MCtaPacDet> listaCargos = new ArrayList<MCtaPacDet>();
//    		long count = 0;
//
//    		//Organizamos los cargos para buscar si existen excepciones para algunos de ellos.
//    		for(int i = 0; i < selectedExtension.getLineasDeExtension().length; i++){
//
//    			// Si la l�nea fue seleccionada, la movemos.
//    			MCtaPacDet cargo = selectedExtension.getLineasDeExtension()[i];
//
//    			if(!cargo.getSelected())
//    				continue;
//
//    			int productoAMover = cargo.getM_Product_ID();
//    			count += 1;
//
//    			//Si la extension a la que se le va a mover los cargos de coaseguro y deducible deben ser
//    			//a una extension que se de un particular
//    			if(productoAMover != deducibleID && productoAMover != coaseguroID) {
//    				listaCargos = new ArrayList<MCtaPacDet>();
//    				
//    				//Para filtrar las excepciones de los productos
//    				cadenaIdProduct = cadenaIdProduct + (count>1?", ":"") + productoAMover;
//    				if(hashMapProductosSeleccionados.containsKey(productoAMover)){
//    					listaCargos = hashMapProductosSeleccionados.get(productoAMover);
//    					listaCargos.add(cargo);
//    					hashMapProductosSeleccionados.remove(productoAMover);
//    				}else{
//    					listaCargos.add(cargo);
//    				}
//    				hashMapProductosSeleccionados.put(productoAMover, listaCargos);
//    			}
//    		}
//
//    		//Se obtienen la lista de excepciones para la lista de productos a mover seleccionados
//    		listaExcepciones = MEXMEBPartnerExcepciones.busqueda(ctx, cadenaIdProduct, 
//    				Constantes.sdfFecha(Env.getCtx()).format(ctaPac.getCreated()), 
//    				extTarget.getBpartner(), hashMapProductosSeleccionados, null);
//    	}
//
//    	return listaExcepciones;
//    }
//


}

