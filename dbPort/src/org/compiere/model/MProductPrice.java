/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Product Price
 * 
 * @author Jorg Janke
 * @version $Id: MProductPrice.java,v 1.4 2006/08/11 02:26:22 mrojas Exp $
 */
public class MProductPrice extends X_M_ProductPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6501485598262480879L;

	/**
	 * Get Product Price
	 * 
	 * @param ctx
	 *            ctx
	 * @param M_PriceList_Version_ID
	 *            id
	 * @param M_Product_ID
	 *            id
	 * @param trxName
	 *            trx
	 * @return product price or null
	 */
	public static MProductPrice get(Properties ctx, int M_PriceList_Version_ID,
			int M_Product_ID, String trxName) {
		
		MProductPrice retValue = null;
		String sql = "SELECT * FROM M_ProductPrice WHERE M_PriceList_Version_ID=? AND M_Product_ID=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, M_PriceList_Version_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MProductPrice(ctx, rs, trxName);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	} // get

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MProductPrice.class);

	/**
	 * Persistency Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param ignored
	 *            ignored
	 */
	public MProductPrice(Properties ctx, int ignored, String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		setPriceLimit(Env.ZERO);
		setPriceList(Env.ZERO);
		setPriceStd(Env.ZERO);
	} // MProductPrice

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 */
	public MProductPrice(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MProductPrice

	/**
	 * New Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_Version_ID
	 *            Price List Version
	 * @param M_Product_ID
	 *            product
	 */
	public MProductPrice(Properties ctx, int M_PriceList_Version_ID,
			int M_Product_ID, String trxName) {
		this(ctx, 0, trxName);
		setM_PriceList_Version_ID(M_PriceList_Version_ID); // FK
		setM_Product_ID(M_Product_ID); // FK
	} // MProductPrice

	/**
	 * New Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_PriceList_Version_ID
	 *            Price List Version
	 * @param M_Product_ID
	 *            product
	 * @param PriceList
	 *            list price
	 * @param PriceStd
	 *            standard price
	 * @param PriceLimit
	 *            limit price
	 */
	public MProductPrice(Properties ctx, int M_PriceList_Version_ID,
			int M_Product_ID, BigDecimal PriceList, BigDecimal PriceStd,
			BigDecimal PriceLimit, String trxName) {
		this(ctx, M_PriceList_Version_ID, M_Product_ID, trxName);
		setPrices(PriceList, PriceStd, PriceLimit);
	} // MProductPrice

	/**
	 * Parent Constructor
	 * 
	 * @param plv
	 *            price list version
	 * @param M_Product_ID
	 *            product
	 * @param PriceList
	 *            list price
	 * @param PriceStd
	 *            standard price
	 * @param PriceLimit
	 *            limit price
	 */
	public MProductPrice(MPriceListVersion plv, int M_Product_ID,
			BigDecimal PriceList, BigDecimal PriceStd, BigDecimal PriceLimit) {
		this(plv.getCtx(), 0, plv.get_TrxName());
		setClientOrg(plv);
		setM_PriceList_Version_ID(plv.getM_PriceList_Version_ID());
		setM_Product_ID(M_Product_ID);
		setPrices(PriceList, PriceStd, PriceLimit);
	} // MProductPrice

	/**
	 * Set Prices
	 * 
	 * @param PriceList
	 *            list price
	 * @param PriceStd
	 *            standard price
	 * @param PriceLimit
	 *            limit price
	 */
	public void setPrices(BigDecimal PriceList, BigDecimal PriceStd,
			BigDecimal PriceLimit) {
		setPriceLimit(PriceLimit);
		setPriceList(PriceList);
		setPriceStd(PriceStd);
	} // setPrice

	// expert : miguel rojas
	/**
	 * Constructor de importaci&oacute;n
	 * 
	 * @param plv
	 *            Versi&oacute;n de la lista de precios
	 * @param iPP
	 *            Registro importado
	 * @param trxName
	 *            Nombre de la transacci&oacute;n
	 */
	public MProductPrice(MPriceListVersion plv, X_I_ProductPrice iPP,
			String trxName) {
		this(plv.getCtx(), 0, trxName);
		setClientOrg(plv);
		setM_PriceList_Version_ID(plv.getM_PriceList_Version_ID());
		setM_Product_ID(iPP.getM_Product_ID());
		setPrices(iPP.getPriceList(), iPP.getPriceStd(), iPP.getPriceLimit());
	}
	// expert : miguel rojas
	
	/**
	 * Version de la lista de precios
	 */
	private MPriceListVersion version = null;

	public MPriceListVersion getVersion() {
		if(version==null)
			version = new MPriceListVersion(getCtx(), getM_PriceList_Version_ID(), null);
		return this.version;
	}

	public void setVersion(MPriceListVersion version) {
		this.version = version;
	}
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		// El precio de lista y el precio estandar nunca deben ir en cero
		/*if((getPriceList()==null || getPriceList().compareTo(Env.ZERO)==0)
				&& (getPriceStd()!=null && getPriceStd().compareTo(Env.ZERO)>0))
			setPriceList(getPriceStd());

		if((getPriceStd()==null || getPriceStd().compareTo(Env.ZERO)==0)
				&& getPriceList()!=null && getPriceList().compareTo(Env.ZERO)>0)
			setPriceStd(getPriceList());*/

		if(validarConversion()){
			MUOMConversion rates = MEXMEUOMConversion.validateConversions(Env.getCtx(), getM_Product_ID(), getC_UOMVolume_ID(),null);
			
			if(rates==null){
				log.saveError(Utilerias.getMsg(getCtx(), "error.udm.noExisteConversion"), getM_Product().getName());
				return false;
			}
			
		
		}

		/******** cambios para UDM ********/
		
		MPriceListVersion version = new MPriceListVersion(Env.getCtx(),getM_PriceList_Version_ID(), get_TrxName());
		final boolean isSOList = version.getM_PriceList().isSOPriceList();
		
		if(isSOList){
			// si se llena precio de unidad minima pero no el de volumen, el precio de volumen toma el precio de la unidad minima covnertido
			if((getPriceList() != null && getPriceList().compareTo(Env.ZERO)!=0) && 
					(getPriceList_Vol() == null || getPriceList_Vol().compareTo(Env.ZERO)== 0)){
//				if(getM_Product().getC_UOM_ID() == getM_Product().getC_UOMVolume_ID()){
//					setPriceList_Vol(getPriceList());
//				}else{
					setPriceList_Vol(MEXMEUOMConversion.convertProductFrom (Env.getCtx(), getM_Product_ID(), getC_UOMVolume_ID(), getPriceList(), null, false));
//				}
				
				
			//por el contario, si llena el precio de volumen pero no el de minima, la minima toma el precio de  volumen convertido
			}else if((getPriceList_Vol() != null && getPriceList_Vol().compareTo(Env.ZERO)!= 0)
					&& (getPriceList() == null || getPriceList().compareTo(Env.ZERO)==0)){
				//if(getM_Product().getC_UOM_ID() == getM_Product().getC_UOMVolume_ID()){
					setPriceList(MEXMEUOMConversion.convertProductTo(Env.getCtx(), getM_Product_ID(), getC_UOMVolume_ID(), getPriceList_Vol(), null, false));
//				}else{
//					setPriceList(getPriceList_Vol());
//				}
				
				
			}
			setPriceStd_Vol(getPriceList_Vol());
			setPriceLimit_Vol(getPriceList_Vol());
			setPriceStd(getPriceList());
			setPriceLimit(getPriceList());
			
		}	
		/******** fin cambios para UDM ********/
		return true;
	}

	/**
	 * Busca Una Plan dados la cuenta paciente y medicamento
	 * 
	 * @param ctaPacID Cuenta Paciente
	 * @param productID Medicamento
	 * @return Plan programado
	 */
	public static List<MProductPrice> get(Properties ctx, int M_PriceList_Version_ID, String trxName) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" M_PriceList_Version_ID = ? ");
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(M_PriceList_Version_ID)//
			.setOnlyActiveRecords(true)//
			.setOrderBy(" M_PriceList_Version_ID DESC ")
			.addAccessLevelSQL(true)//
			.list();
	}
	
	

	/**
	 * Metodo que valida si es necesario llevar a cabo conversiones entre las unidades de medida
	 * @return
	 */
	public boolean validarConversion(){
		
		
		//si las unidades son iguales no requiere conversion
		if(getC_UOM_ID() == getC_UOMVolume_ID()){
			return false;
		}
		
		//no se especifico el precio de lista para la unidad de medida de volumen, pero si se especifico para la unidad de medida minima
		if( (getPriceList_Vol() == null || getPriceList_Vol().compareTo(Env.ZERO) == 0) && (getPriceList()!=null && getPriceList().compareTo(Env.ZERO)>0) ){
			return true;
		}
				
		//no se especifico el precio estandar para la unidad de medida de volumen, pero si se especifico para la unidad de medida minima
		if( (getPriceStd_Vol() == null || getPriceStd_Vol().compareTo(Env.ZERO) == 0) && (getPriceStd()!=null && getPriceStd().compareTo(Env.ZERO)>0) ){
			return true;
		}
				
				
		// si no se especifico el precio de lista para unidad minima, pero si para la unidad de volumen
		if( (getPriceList() == null || getPriceList().compareTo(Env.ZERO) == 0) && (getPriceList_Vol()!=null && getPriceList_Vol().compareTo(Env.ZERO)>0) ){
			return true;
		}
				
		// si no se especifico el precio estandar para unidad minima, pero si para la unidad de volumen
		if( (getPriceStd() == null || getPriceStd().compareTo(Env.ZERO) == 0) && (getPriceStd_Vol()!=null && getPriceStd_Vol().compareTo(Env.ZERO)>0) ){
			return true;
			
		}
				
		
		return false;
	}
	
	/**
	 * Metodo que regresa la lista de precio con las reglas:
	 * 1El precio de la lista capturada en el encabezado.
	 * 2El precio de la lista de precios relacionada al proveedor, tomando la version mas reciente.
	 * 3El precio de la versión MAS RECIENTE de una lista de precios de compra.
	 * 4El precio que se captura manualmente.
	 * @param idSocio Id provedor 
	 * @param idPriceList Id lista de precio,
	 * @param productId producto del que se reuqiere su precio
	 * @param Venta es venta true, es compra false
	 * @return lista de precios utilizada/MAS ACTUAL/ninguna.
	 */
	public static MProductPrice getProductPrice(final int idSocio, final int idPriceList, final int productId, final boolean Venta){

		MProductPrice mPrice = null;
		
		if(Venta){
			// Venta se manejara por ecaresoft, por lo que no esta dentro del alcance de este metodo
		}else{

			// Lista de precio seleccionada
			if(idPriceList>0){
				MPriceList pList = new MPriceList(Env.getCtx(), idPriceList, null);
				MEXMEPriceListVersion version = MEXMEPriceListVersion.getLastPriceListCreated(Env.getCtx(),pList.getM_PriceList_ID(), Venta);
				mPrice =  MProductPrice.get(Env.getCtx(), version == null ? 0 : version.getM_PriceList_Version_ID(), productId, null);
			}

			// Lista de precios del proveedor
			if(idSocio>0 && mPrice==null){
				MBPartner provider = new MBPartner(Env.getCtx(), idSocio, null);
				MEXMEPriceListVersion version = MEXMEPriceListVersion.getLastPriceListCreated(Env.getCtx(),provider.getPO_PriceList_ID(), Venta);
				mPrice =  MProductPrice.get(Env.getCtx(), version == null ? 0 : version.getM_PriceList_Version_ID(), productId, null);
			}
			
			// Lista de precios por defecto
			if(mPrice==null){
				MPriceList mPriceList = MEXMEPriceList.getDefaultPL (Env.getCtx(), Venta);
				if(mPriceList==null){
					s_log.saveError("Error", "Lista de precios por defecto");
				} else {
					MEXMEPriceListVersion version = MEXMEPriceListVersion.getLastPriceListCreated(Env.getCtx(),
							mPriceList.getM_PriceList_ID(), Venta);
					mPrice =  MProductPrice.get(Env.getCtx(), version == null ? 0 : version.getM_PriceList_Version_ID(), productId, null);
				}
			}
		}
		return mPrice;
	}

	/**
	 * 
	 * @param ctx
	 * @param importItem
	 */
	public static void updateImported(Properties ctx, MI_EXME_ListaPrecio importItem){
		List<MProductPrice> list = new Query(ctx, Table_Name, " M_PRODUCT_ID=" + importItem.getM_Product_ID() + " AND M_PRICELIST_VERSION_ID=" + importItem.getM_PriceList_Version_ID() , null).list();
		BigDecimal minPrice = MEXMEUOMConversion.convertProductFrom(ctx, importItem.getM_Product_ID(), importItem.getC_UOMVolume_ID(), importItem.getPriceList_Vol(), null); 
		for(MProductPrice mProductPrice : list){
			mProductPrice.setPriceList(minPrice);
			mProductPrice.setC_UOMVolume_ID(importItem.getC_UOMVolume_ID());
			mProductPrice.setPriceList_Vol(importItem.getPriceList_Vol());
		}
	}
	
	
	/**
	 * obtiene los precios de un producto ordenados por updated desc
	 * @param ctx
	 * @param productID
	 * @param trxName
	 * @return
	 */
	public static List<MProductPrice> getByProduct(Properties ctx, int productID, boolean active, String trxName) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" M_Product_ID = ? ");
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(productID)//
			.setOnlyActiveRecords(active)//
			.setOrderBy(" Updated desc")
			.addAccessLevelSQL(true)//
			.list();
	}
}