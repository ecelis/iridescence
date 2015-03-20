package org.compiere.model.billing;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECtaPacPaq;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_CtaPacDet;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Creacion de cargos que son parte del paquete
 * @author twry
 *
 */
public class ChargePackages {

	/** static logger   */
	private static CLogger slog = CLogger.getCLogger(ChargePackages.class);
	/** Listado de cargos modificados */
	private transient List<MCtaPacDet> lstCargosModif = new ArrayList<MCtaPacDet>();
	/** Listado de cargos creados */
	private transient List<MCtaPacDet> lstCargosNuevo = new ArrayList<MCtaPacDet>();
	/** saber si la cuenta tiene paquetes relacionados   */
	private transient boolean tienePaquetes = false;
	
	
	/**
	 * Desde WEnterCharge
	 * @param ctx
	 * @param detino
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	public static List<MCtaPacDet> getCharges(final Properties ctx, final MCtaPacDet detino, final int EXME_CtaPac_ID) {
		return new ChargePackages(ctx, EXME_CtaPac_ID).getCharges(detino);
	}
	
	/**
	 * Constructor
	 * @param pCtx
	 * @param pEXME_CtaPac_ID
	 */
	public ChargePackages(final Properties pCtx, final int pEXME_CtaPac_ID){
		super();
//		ctx = pCtx;
//		exmeCtaPacID = pEXME_CtaPac_ID;
		tienePaquetes = !MEXMECtaPacPaq.getFromCtaPac(pCtx, pEXME_CtaPac_ID, null, null).isEmpty();
		lstCargosNuevo.clear();
		lstCargosModif.clear();
	}
	
	/**
	 * Cargos
	 * SubTotal = Cantidad del paquete - (Cantidad de los originales cargos + Cantidad de los sustituto cargados)
	 *
	 * si SubTotal es positivo
	 *    TOTAL = Subtotal - Cantidad_a_cargar
	 *	  si TOTAL es positivo o cero
	 *       Cantidad_a_cargar es la cantidad del nuevo cargo
	 *    si TOTAL es negativo
	 *   	 TOTAL es un extra
	 *   	 Subtotal es la cantidad del nuevo cargo
	 *    si no
	 *       salir de aki es un extra
	 * @param cargoOriginal
	 * @return
	 */
	public List<MCtaPacDet> getCharges(final MCtaPacDet cargoOriginal) {
		final List<MCtaPacDet> lstFinal = new ArrayList<MCtaPacDet>();

		// Paquetes
		if(!tienePaquetes){
			cargoOriginal.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
			lstFinal.add(cargoOriginal);
			return lstFinal;
		}
		// Si la cantidad del cargo es mayor a cero (Para evitar que entre al hacer alguna devolución)
		if(cargoOriginal.getQtyDelivered().compareTo(Env.ZERO)>0){//6
			// SubTotal = Cantidad del paquete - Cantidad de los originales cargos/sustituto
			final BigDecimal[] qty = get(cargoOriginal.getM_Product_ID(), cargoOriginal.getEXME_CtaPac_ID(), cargoOriginal.getC_UOM_ID(), cargoOriginal.get_TrxName());
			
			// Que no exista el registro que no tenga cantidad en el paquete
			if(qty==null || qty[1]==null || qty[1]==Env.ZERO){// No existe un registro para ese producto 
				cargoOriginal.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
				lstFinal.add(cargoOriginal);
				return lstFinal;
			}
			
			// BigDecimal[]{rs.getBigDecimal(QTYCHARGE), rs.getBigDecimal(QTYPACK)};
			//               subtotal = paquete menos cargo (5-6)=-1
			final BigDecimal subtotal = qty[1].subtract(qty[0]);// paquete completo//
			final BigDecimal total = subtotal.subtract(cargoOriginal.getQtyDelivered());// cantidad completa

			// salir de aki es un extra(-1<=0)
			if(subtotal.compareTo(Env.ZERO)<=0){//Cuando el cargo 
				cargoOriginal.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
				lstFinal.add(cargoOriginal);
				return lstFinal;//cuando el paquete ya se ha cargodo por completo para ese producto
			}

			if(total.compareTo(Env.ZERO)>=0){
				// Se cambia el tipo de Lina a Exempt, para no mostrarlos en facturacion
				cargoOriginal.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
				lstFinal.add(cargoOriginal);
				return lstFinal;
			}

			final MCtaPacDet extra = MCtaPacDet.copyFrom(cargoOriginal, cargoOriginal.getEXME_CtaPacExt_ID(), cargoOriginal.get_TrxName());
			PO.copyValues(cargoOriginal, extra, true, false);
			extra.setQtyOrdered(total.abs());
			extra.setQtyEntered(total.abs());
			extra.setQtyDelivered(total.abs());
			extra.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
			extra.setLineNetAmt();
			extra.setPrecioCambio(cargoOriginal.getPrecioAnterior());
			extra.setTaxAmt(false, extra.getLineNetAmt());
			lstFinal.add(extra);
			
			
			cargoOriginal.setQtyEntered(subtotal);
			cargoOriginal.setQtyDelivered(subtotal);
			cargoOriginal.setQtyOrdered(subtotal.abs());
			// Se cambia el tipo de Lina a Exempt, para no mostrarlos en facturacion
			cargoOriginal.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
			cargoOriginal.setLineNetAmt();
			cargoOriginal.setTaxAmt(false, cargoOriginal.getLineNetAmt());
			lstFinal.add(cargoOriginal);
		} else {
			lstFinal.add(cargoOriginal);
		}
		
		return lstFinal;
	}
	

	/**
	 * Busqueda de producto en los cargo que ya fue consumido por el paquete
	 * @param pMProductID : Producto a buscar
	 * @param pEXMECtaPacID : Cuenta paciente
	 * @param pCUomID : unidad de medida que debe ser la del paquete
	 * @param trxName : Nombre de transaccion
	 * @return
	 */
	private BigDecimal[] get(final int pMProductID, final int pEXMECtaPacID, 
			final int pCUomID, final String trxName){

		BigDecimal[] retorno = null;
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		// El numero de registros cargados
		.append(" SELECT prod.M_Product_ID, ")
		.append("    (SELECT COALESCE (SUM(cpd.QtyDelivered),0) AS sQTYCHARGE ")
		.append("     FROM M_Product pd                                  ")
		.append("     LEFT JOIN EXME_CtaPacDet cpd ON pd.M_Product_ID = cpd.M_Product_ID ")
		.append("     AND cpd.IsActive       = 'Y'                        ")
		.append("     AND cpd.EXME_CtaPac_ID = ?                          ")//# 1
		.append("     AND cpd.TipoLinea      =       ").append(DB.TO_STRING(X_EXME_CtaPacDet.TIPOLINEA_Exempt))
		.append("     AND cpd.seDevolvio     = 'N'   ")
		.append("     AND cpd.C_UOM_ID       = ?     ")//# 2
		.append("     WHERE prod.M_Product_ID  = pd.M_Product_ID  ")
		.append("                                             ) AS QTYCHARGE, ")

		// Que el producto sea una linea de paquete
		.append("    COALESCE (SUM(pbd.Cantidad),0) AS QTYPACK                ")
		.append(" FROM M_Product prod  ")
		.append("     LEFT JOIN EXME_PaqBaseDet pbd ON prod.M_Product_ID = pbd.M_Product_ID       ")
		.append("     AND pbd.IsActive = 'Y'                            ")
		.append("     AND pbd.Op_Uom   = ?                              ")//# 3
		.append("     AND pbd.EXME_PaqBase_Version_ID IN                ")
		.append("       (SELECT cpp.EXME_PaqBase_Version_ID             ")
		.append("        FROM EXME_CtaPacPaq cpp                        ")
		.append("        WHERE cpp.isActive     = 'Y'                   ")
		.append("        AND cpp.EXME_CtaPac_ID =  ? )                  ")//# 4
		
		// Producto
		.append(" WHERE prod.IsActive     = 'Y'      ")
		.append(" AND   prod.M_Product_ID = ?        ")//# 5
		.append(" GROUP BY prod.M_Product_ID         ") ;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pEXMECtaPacID);
			pstmt.setInt(2, pCUomID);
			pstmt.setInt(3, pCUomID);
			pstmt.setInt(4, pEXMECtaPacID);
			pstmt.setInt(5, pMProductID);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {//QTYCHARGE, QTYPACK
				retorno = new BigDecimal[]{rset.getBigDecimal("QTYCHARGE"), rset.getBigDecimal("QTYPACK")};
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rset, pstmt);
		}

		return retorno;
	}

	
	/************************* PROCESS ************************************/
	/**
	 * Desde WEnterCharge
	 * @param ctx
	 * @param detino
	 * @param exmeCtaPacID
	 * @return
	 */
	public static boolean getMatch(final Properties pCtx, final int  pEXMECtaPacID, final String trxName) {
		// Clase
		final ChargePackages match = new ChargePackages(pCtx, pEXMECtaPacID);
		
		// Match
		match.calcularMatch(match.getListadoCargos(pCtx, pEXMECtaPacID, trxName));
		return match.actualizarCargos(trxName);
	}
	
	/**
	 * Actualiza los cargos
	 * @param trxName
	 * @return
	 */
	private boolean actualizarCargos(final String trxName){
		boolean success = true;
		// Si se hizo el match y hay cargos por modificar
		if(!getLstCargosModificados().isEmpty()){
			// Update
			for (MCtaPacDet bean: getLstCargosModificados()) {
				if(bean.save(trxName)){
					//slog.log(Level.SEVERE, "Match Update EXME_CtaPacDet_ID = ", bean.getEXME_CtaPacDet_ID());
				} else {
					success = false;
				}
			}
		}

		if (!getLstCargosNuevo().isEmpty()){
			// Insert
			for (MCtaPacDet bean: getLstCargosNuevo()) {
				if(bean.save(trxName)){
					//slog.log(Level.SEVERE, "Match Insert EXME_CtaPacDet_ID = ", bean.getEXME_CtaPacDet_ID());
				} else {
					success = false;
				}
			}
		}
		return success;
	}
	
	/**
	 * Listado de paquetes vs cargos
	 * @param ctx : Contexto
	 * @param exme_ctapac_id : Cuenta paciente
	 * @param trxName : Nombre de transacción
	 */
	private void calcularMatch(final Map<Integer, List<MCtaPacDet>> mapa){

		// Mapa de cargos que serian parte del paquete
		final Iterator<Entry<Integer, List<MCtaPacDet>>> itEntries = mapa.entrySet().iterator();
		while (itEntries.hasNext()) {
			// element.getValue() , element.getKey());
			final Entry<Integer, List<MCtaPacDet>> element = itEntries.next();

			final int productId = element.getKey();//1474332
			final List<MCtaPacDet> lstCargos = element.getValue();

			slog.log(Level.SEVERE, "Producto = ", productId);
			
			// Existen lineas
			if(productId>0 && !lstCargos.isEmpty()
				&& productId==lstCargos.get(0).getM_Product_ID()){
					iteracionDeCargosPorProducto(lstCargos);//, lstCargos.get(0).getQtyPack());
			}
		}// fin while
	}

	/**
	 * iteracion De Cargos Por Producto previamente hechos match
	 * @param lstCharges
	 * @param paqCantidad
	 * @return
	 */
	private void iteracionDeCargosPorProducto(final List<MCtaPacDet> lstCharges){ 
			//final BigDecimal pPaqCantidad){
		Map<Integer, BigDecimal> mapaUomQty = new HashMap<Integer, BigDecimal>();
		// Cantidad que permite el paquete del producto
//		BigDecimal paqCantidad = pPaqCantidad;
		
		// Todos los cargos del mismo producto
		for (MCtaPacDet bean: lstCharges) { // Cargo o Exempt
			
			// Crea Cantidades por unidad de medida por producto
			if(!mapaUomQty.containsKey(bean.getC_UOM_ID())){
				mapaUomQty.put(bean.getC_UOM_ID(), bean.getQtyPack());
			} 
			
			BigDecimal paqQty = mapaUomQty.get(bean.getC_UOM_ID());
			
			
					
			
			
			// Condiciones del cargo para hacerle match
			// que este activo, que no sea una devolución que la cantidad cargada sea mayor a cero y que sea parte del paquete
			if(bean.isActive() 
					&& !bean.isSeDevolvio() 
					&& bean.getQtyDelivered().compareTo(Env.ZERO)>0 
					&& bean.getQtyPack().compareTo(Env.ZERO)>0
					// Mientras exista cantidad del match
					&& paqQty.compareTo(Env.ZERO)> 0){
				
				
					// QTY : Paquete - Cargo // 10-5  // 6-6 // 5-10
				final BigDecimal qty = paqQty.subtract(bean.getQtyDelivered());


									if(qty.compareTo(Env.ZERO)> 0){
										// sobra paquete
										bean.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
										bean.setTaxAmt(false, bean.getLineNetAmt());
										paqQty = qty;
										lstCargosModif.add(bean);			
										
									} else if(qty.compareTo(Env.ZERO) == 0){
										// completo
										bean.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
										bean.setTaxAmt(false, bean.getLineNetAmt());
										paqQty = Env.ZERO;
										lstCargosModif.add(bean);			
										
									} else if(qty.compareTo(Env.ZERO) < 0){
										// sobra cargo (linea dividida) // 5-12 = -7
		
										final MCtaPacDet dentroDePaq = new MCtaPacDet(bean.getCtaPac(), bean.getEXME_CtaPacExt_ID(), bean.getEXME_Area_ID(), bean.get_TrxName());
										PO.copyValues(bean, dentroDePaq, true, false);
										dentroDePaq.copyFrom(bean);
										dentroDePaq.setQtyEntered(paqQty);// 5 del paquete
										dentroDePaq.setQtyDelivered(paqQty);// 5 del paquete
										dentroDePaq.setQtyOrdered(paqQty);// 5 del paquete
										dentroDePaq.setQtyInvoiced(paqQty);// 5 del paquete
										dentroDePaq.setQtyPendiente(paqQty);// 5 del paquete
										dentroDePaq.setQtyPaquete(Env.ZERO);
//										dentroDePaq.setQtyDevolucion(Env.ZERO);
										dentroDePaq.setQtyReserved(Env.ZERO);
										
										dentroDePaq.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
										dentroDePaq.setLine();
										dentroDePaq.setLineNetAmt();
										dentroDePaq.setTaxAmt(false, dentroDePaq.getLineNetAmt());
										lstCargosNuevo.add(dentroDePaq);
		
										bean.setQtyEntered(qty.abs());//-7 extras
										bean.setQtyDelivered(qty.abs());//-7 extras
										bean.setQtyOrdered(qty.abs());//-7 extras
										bean.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
										bean.setLineNetAmt();
										bean.setTaxAmt(false, bean.getLineNetAmt());
										lstCargosModif.add(bean);										
		
										paqQty = Env.ZERO;
									}
									
									// Actualiza Cantidades por unidad de medida por producto
									if(mapaUomQty.containsKey(bean.getC_UOM_ID())){
										BigDecimal paqQtyOld = mapaUomQty.get(bean.getC_UOM_ID());
										if(paqQty.compareTo(paqQtyOld)!=0){
											mapaUomQty.remove(bean.getC_UOM_ID());
											mapaUomQty.put(bean.getC_UOM_ID(), paqQty);
										}
									} 
									
				
			} else {
				// Si el cargo previo match no cumple las condiciones se regresa a cargo 
				if(X_EXME_CtaPacDet.TIPOLINEA_Exempt.equals(bean.getTipoLinea())){
					bean.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
					bean.setTaxAmt(false, bean.getLineNetAmt());
					lstCargosModif.add(bean);
				}
			} // se devolvio o inactivo o etc..
			
			
			
		}// fin for
	}
	
	
	/**
	 * Cargos de un match
	 * @param ctx : contexto
	 * @param exme_ctapac_id : cuenta paciente
	 * @param trxName : nombre de transacción
	 * @return listado de cargos
	 */
	private Map<Integer, List<MCtaPacDet>> getListadoCargos(final Properties ctx, final int exme_ctapac_id, final String trxName){
		final Map<Integer, List<MCtaPacDet>> mapa = new HashMap<Integer, List<MCtaPacDet>>();
//		List<MCtaPacDet> lstCargos = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)

		// Productos del paquete que estan cargados 
		.append(" select cpd.*, coalesce( prod.qtyPaq,0) AS mQtyPaq, cpd.tipoLinea, cpd.M_Product_ID ")
		.append(" from exme_ctapacdet cpd                                                            ")
		// Puede haber mas de un paquete activo a la vez
		.append(" left join                                                                          ")
		// Productos del paquete, udm y cantidad 
        .append("			  (                                                                      ")
        .append(" 				select p.m_product_id,  pbd.op_uom as c_uom_id , sum(pbd.cantidad) as qtyPaq     ")
        .append(" 				from exme_paqbasedet pbd                                             ")
        .append(" 				inner join exme_ctapacpaq pq on pbd.exme_paqbase_version_id = pq.exme_paqbase_version_id and pq.isactive = 'Y' ")
        .append(" 				inner join m_product       p on pbd.m_product_id  = p.m_product_id   ")
        .append(" 				where pbd.isactive = 'Y'                                             ")
        .append(" 				and pq.exme_ctapac_id = ?                                            ") // #1
        .append(" 				group by p.m_product_id, pbd.op_uom                                  ")
        .append(" 			  ) prod on cpd.c_uom_id = prod.c_uom_id and cpd.m_product_id = prod.m_product_id ")
        // Cuenta paciente del match
		.append(" where cpd.exme_ctapac_id = ?               ") // #2
		.append(" and   cpd.tipoLinea IN (                   ")
		.append(DB.TO_STRING(X_EXME_CtaPacDet.TIPOLINEA_Exempt)).append(", ")
		.append(DB.TO_STRING(X_EXME_CtaPacDet.TIPOLINEA_Charge)).append(" ) ")
		.append(" order by cpd.tipoLinea DESC                ");// Primero hay que evaluar las Exempt

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exme_ctapac_id);
			pstmt.setInt(2, exme_ctapac_id);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				
				final int productId = rset.getInt("M_Product_ID");
				
				// si es parte del paquete o si la linea es exempt
				if(rset.getBigDecimal("mQtyPaq").compareTo(Env.ZERO)>0 
						|| X_EXME_CtaPacDet.TIPOLINEA_Exempt.equals(rset.getString("tipoLinea"))) {
					
					// si ya existe
					if(mapa.containsKey(productId)){
						// se remueve la lista anterior
						final List<MCtaPacDet> lstCargosOld = mapa.get(productId);
						mapa.remove(productId);
						
						// crear el cargo
						final MCtaPacDet cargo = new MCtaPacDet(ctx, rset, trxName);
						cargo.setQtyPack(rset.getBigDecimal("mQtyPaq"));// la cantidad del paquete
						
						// se agrega el nuevo cargo
						lstCargosOld.add(cargo);
						mapa.put(productId, lstCargosOld);// (Id producto)(Cargo[qtyPack])
					} else {
						// crear el cargo
						final MCtaPacDet cargo = new MCtaPacDet(ctx, rset, trxName);
						cargo.setQtyPack(rset.getBigDecimal("mQtyPaq"));// la cantidad del paquete5
						
						final List<MCtaPacDet> lstCargosNew = new ArrayList<MCtaPacDet>();
						lstCargosNew.add(cargo);//1474334//1474333//1474336//1474329
						mapa.put(productId, lstCargosNew);
					}
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rset, pstmt);
		}
		return mapa;
	}
	
	
	public List<MCtaPacDet> getLstCargosModificados() {
		return lstCargosModif;
	}

	public void setLstCargosModificados(final List<MCtaPacDet> pLstCargosMod) {
		this.lstCargosModif = pLstCargosMod;
	}

	public List<MCtaPacDet> getLstCargosNuevo() {
		return lstCargosNuevo;
	}

	public void setLstCargosNuevo(final List<MCtaPacDet> lstCargosNuevo) {
		this.lstCargosNuevo = lstCargosNuevo;
	}

}
