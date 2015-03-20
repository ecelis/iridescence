/**
 * 
 */
package org.compiere.process.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.compiere.model.CargosAGenerarV;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEInOut;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MInOut;
import org.compiere.model.MTax;
import org.compiere.model.X_C_Tax;
import org.compiere.model.bpm.GetCost;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * @author Expert Tres campos nuevos EXME_ActPacienteInd CargosGenerados Sabemos
 *         si se genero el cargo o no EXME_CtaPacDet AD_PInstance_ID
 */
public class GeneracionCargos extends SvrProcess {

	/** cliente para el que se aplicaran los cargos */
	private int pEXMECtaPacID = -1;
	/** listado de cargos no generados */
	private List<CargosAGenerarV> listado = new ArrayList<CargosAGenerarV>();
	/** listado de cargos de ctapac */
	private List<MCtaPacDet> listadoDet = new ArrayList<MCtaPacDet>();
	/** configuracion demejoras */
	private MEXMEMejoras mejoras = null;
	/** impuestos */
	private HashMap<Integer,List<MTax>> mapaTax = new HashMap<Integer,List<MTax>>();
	/** costos */
	private HashMap<Integer,BigDecimal> mapaCost = new HashMap<Integer,BigDecimal>();

	@Override
	protected void prepare() {

		// Posible parametro es la cuenta paciente
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			if (name.equals("EXME_CtaPac_ID"))
				pEXMECtaPacID = (new BigDecimal(para[i].getParameter()
						.toString())).intValue();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}

		// configuracion de mejoras
		if (mejoras == null) {
			mejoras = MEXMEMejoras.get(getCtx());
		}

		getImpuestos();
	}

	@Override
	protected String doIt() throws Exception {
		Trx m_trx = null;
		boolean continuar = true;
		try {
			// transaccion
			m_trx = Trx.get(Trx.createTrxName("GCar"), true);

			// Mapa con el detalle de los cargos
			HashMap<Integer, List<CargosAGenerarV>> mapa = getLstDetalle();

			// Iteramos el mapa
			Iterator<Entry<Integer, List<CargosAGenerarV>>> itEntries = mapa
					.entrySet().iterator();
			while (itEntries.hasNext()) {
				Entry<Integer, List<CargosAGenerarV>> element = itEntries
						.next();

				// Iteramos la lista de productos
				List<CargosAGenerarV> lst = element.getValue();
				for (int i = 0; i < lst.size(); i++) {
					MCtaPacDet cargo = new MCtaPacDet(getCtx(), 0, null);
					cargo.setEXME_ActPacienteInd_ID(lst.get(i)
							.getEXME_ActPacienteInd_ID());
					cargo.setM_Warehouse_ID(lst.get(i).getM_Warehouse_ID()>0?lst.get(i).getM_Warehouse_ID():mejoras.getM_Warehouse_ID());
					cargo.setEXME_Area_ID(lst.get(i).getEXME_Area_ID());
					
					int taxID = lst.get(i).getC_Tax_ID();
					if(taxID<=0){
						List<MTax> taxs = mapaTax.get(lst.get(i).getC_TaxCategory_ID());
						// siempre el primero //TODO Definir los criterios de orden
						if(taxs!=null && taxs.size()>0){
							taxID = taxs.get(0).getC_Tax_ID();
						}
					}
					
					cargo.setC_Tax_ID(taxID);
					cargo.setC_UOM_ID(lst.get(i).getC_UOM_ID());
					cargo.setM_Product_ID(lst.get(i).getM_Product_ID());
					cargo.setEXME_CtaPac_ID(lst.get(i).getEXME_CtaPac_ID());
					cargo.setC_Currency_ID(lst.get(i).getC_Currency_ID());
					cargo.setQtyOrdered(lst.get(i).getQtyOrdered());
					cargo.setQtyDelivered(lst.get(i).getQtyDelivered());
					
					BigDecimal costo = lst.get(i).getCosto();
					if(costo==null || costo.compareTo(Env.ZERO)<=0){
						// buscamos en el mapa
						if(mapaCost.containsKey(lst.get(i).getM_Product_ID())){
							costo = mapaCost.get(lst.get(i).getM_Product_ID());
						} 
						// buscamos el costo
						if(costo==null || costo.compareTo(Env.ZERO)<=0){
							GetCost getCost = new GetCost(getCtx(), lst.get(i).getM_Product_ID());
							costo = getCost.costo(true);
						}
						// lo creamos
						if(costo==null || costo.compareTo(Env.ZERO)<=0){
								//crear
								//MCost.create(new MClient(getCtx(), m_trx.getTrxName()));
//								MCost.createCost(cargo.getProducto());
//								costo = Env.ONE;
								log.log(Level.FINEST, "Costo creado para el producto ");
						}
						// para no volver a pasar por los pasos anteriores
						if(costo!=null && costo.compareTo(Env.ZERO)>0 
								&& !mapaCost.containsKey(lst.get(i).getM_Product_ID())){
							mapaCost.put(lst.get(i).getM_Product_ID(), costo);
						}
					}
					
					cargo.setCosto(costo);
					cargo.setDateOrdered(lst.get(i).getDateOrdered());

					//
					cargo.setEXME_CtaPac_ID(lst.get(i).getEXME_CtaPac_ID());
					cargo.setEXME_CtaPacExt_ID(lst.get(i)
							.getEXME_CtaPacExt_ID());
					cargo.setTipoArea(lst.get(i).getTipoArea());
					cargo.setAD_PInstance_ID(getAD_PInstance_ID());// corre por
					// el
					// servidor
					// / reporte
					// y proceso
					cargo.setLine(i);
					if(lst.get(i).getPriceActual().compareTo(Env.ZERO)<=0){
						listado.add(lst.get(i));
						log.log(Level.FINEST, "CtaPacID> " + element.getKey()
								+ " ActPacienteInd> "
								+ lst.get(i).getEXME_ActPacienteInd_ID());
						continue;
					}
					cargo.setPriceActual(lst.get(i).getPriceActual());
					cargo.setPriceLimit(lst.get(i).getPriceActual());
					cargo.setPriceList(lst.get(i).getPriceActual());
					cargo.setPricesInv();
					cargo.setLineNetAmt();

					// guardamos el cargos
					if (!cargo.save(m_trx.getTrxName())) {
						listado.add(lst.get(i));
						log.log(Level.FINEST, "CtaPacID> " + element.getKey()
								+ " ActPacienteInd> "
								+ lst.get(i).getEXME_ActPacienteInd_ID());
					} else {
						listadoDet.add(cargo);
					}
				}
				if (listadoDet.size() > 0) {
					try{
						continuar = generarSalidaInventario(listadoDet, element.getKey(), m_trx
							.getTrxName());
					} catch (Exception e) {
						log.log(Level.SEVERE, "SQLException", e.getMessage());
					}
				}
			}

			if(continuar && m_trx!=null){
				m_trx.commit();
				m_trx.close();
				m_trx = null;
			}

			setResult(listado);
		} catch (Exception e) {
			log.log(Level.SEVERE, "SQLException", e.getMessage());
		} finally {
			// cerramos la transaccion
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
				m_trx = null;
			}
		}
		return "@Chargeshavebeenapplied@";
	}

	/**
	 * Creamos la salida de inventario por cuenta paciente
	 * 
	 * @param lst
	 * @param cta
	 * @param trxName
	 */
	private boolean generarSalidaInventario(List<MCtaPacDet> lst, int cta,
			String trxName) {

		if (mejoras.isChargeOnStock()) {
			MCtaPacDet[] estServ = new MCtaPacDet[lst.size()];
			lst.toArray(estServ);
			// TODO: Falta validar las existencias

			// Creamos la salida un Header por ctapac
			MInOut mInOut = MEXMEInOut.salidaDesdeCargos(getCtx(),
					new MEXMECtaPac(getCtx(), cta, null), mejoras
							.getM_Warehouse_ID(), estServ, trxName);
			if(mInOut!=null){
				lstInOut.add(mInOut);
				
			}else{
				return false;
			}
			
		}
		return true;
	}

	/************************************************************************************/
	/**
	 * Obtenemos los cargos a procesar por nivel de acceso
	 */
	private HashMap<Integer, List<CargosAGenerarV>> getLstDetalle() {
		String sql = null;
		if (pEXMECtaPacID <= 0) {
			sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(),
					"SELECT * FROM EXME_CargosAGenerar_V ",
					"EXME_CargosAGenerar_V");
		} else {
			sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(),
					"SELECT * FROM EXME_CargosAGenerar_V WHERE EXME_CtaPac_ID = "
							+ pEXMECtaPacID, "EXME_CargosAGenerar_V");
		}
		HashMap<Integer, List<CargosAGenerarV>> mapa = new HashMap<Integer, List<CargosAGenerarV>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (mapa.containsKey(rs.getInt("EXME_CtaPac_ID"))) {
					List<CargosAGenerarV> list = mapa.get(rs
							.getInt("EXME_CtaPac_ID"));
					list.add(new CargosAGenerarV(rs
							.getInt("EXME_ActPacienteInd_ID"), rs
							.getInt("M_Warehouse_ID"), rs
							.getInt("EXME_Area_ID"), rs.getInt("C_Tax_ID"), rs
							.getInt("C_UOM_ID"), rs.getInt("M_Product_ID"), rs
							.getInt("EXME_CtaPac_ID"), rs
							.getInt("EXME_ActPacienteIndH_ID"), rs
							.getInt("C_Currency_ID"), rs
							.getInt("EXME_CtaPacExt_ID"), rs
							.getBigDecimal("QtyOrdered"), rs
							.getBigDecimal("QtyDelivered"), rs
							.getBigDecimal("Costo"), rs
							.getBigDecimal("PriceActual"), rs
							.getTimestamp("DateOrdered"), rs
							.getString("TipoArea"),
							rs.getInt("C_TaxCategory_ID"),
							rs.getString("Documentno"),
							rs.getString("Nombre_pac")
					));
				} else {
					List<CargosAGenerarV> list = new ArrayList<CargosAGenerarV>();
					list.add(new CargosAGenerarV(rs
							.getInt("EXME_ActPacienteInd_ID"), rs
							.getInt("M_Warehouse_ID"), rs
							.getInt("EXME_Area_ID"), rs.getInt("C_Tax_ID"), rs
							.getInt("C_UOM_ID"), rs.getInt("M_Product_ID"), rs
							.getInt("EXME_CtaPac_ID"), rs
							.getInt("EXME_ActPacienteIndH_ID"), rs
							.getInt("C_Currency_ID"), rs
							.getInt("EXME_CtaPacExt_ID"), rs
							.getBigDecimal("QtyOrdered"), rs
							.getBigDecimal("QtyDelivered"), rs
							.getBigDecimal("Costo"), rs
							.getBigDecimal("PriceActual"), rs
							.getTimestamp("DateOrdered"), rs
							.getString("TipoArea"),
							rs.getInt("C_TaxCategory_ID"),
							rs.getString("Documentno"),
							rs.getString("Nombre_pac")
					));
					mapa.put(rs.getInt("EXME_CtaPac_ID"), list);
				}

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mapa;
	}

	private void getImpuestos(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = MEXMELookupInfo.addAccessLevelSQL(getCtx(),
					"SELECT * FROM C_Tax WHERE ISACTIVE = 'Y' AND AD_Client_ID =? ",
					X_C_Tax.Table_Name);
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(getCtx()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (mapaTax.containsKey(rs.getInt("C_TaxCategory_ID"))) {
					List<MTax> list = mapaTax.get(rs
							.getInt("C_TaxCategory_ID"));
					list.add(new MTax(getCtx(), rs, null));
				} else {
					List<MTax> list = new ArrayList<MTax>();
					list.add(new MTax(getCtx(), rs, null));
					mapaTax.put(rs.getInt("C_TaxCategory_ID"), list);
				}

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "MEstServ.get", e);
		} finally {
			DB.close(rs, pstmt);
		}
		
	}
	
	
	public List<CargosAGenerarV> getListado() {
		return listado;
	}

	public void setListado(List<CargosAGenerarV> listado) {
		this.listado = listado;
	}

	private List<MInOut> lstInOut = new ArrayList<MInOut>();

	public List<MInOut> getLstInOut() {
		return lstInOut;
	}

	public void setLstInOut(List<MInOut> lstInOut) {
		this.lstInOut = lstInOut;
	}
	
}