/* The contents of this file are subject to the   Compiere License  Version 1.1
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
package org.compiere.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.compiere.model.I_I_EXME_ListaPrecio;
import org.compiere.model.I_M_ProductPrice;
import org.compiere.model.MBPartner;
import org.compiere.model.MConfigEC;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MI_EXME_ListaPrecio;
import org.compiere.model.MPriceList;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.model.X_EXME_AudListaPrecio;
import org.compiere.model.X_I_EXME_ListaPrecio;
import org.compiere.model.X_M_ProductPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

public class ImportPriceList extends SvrProcess {
	private static CLogger		LOGGER				= CLogger.getCLogger(ImportPriceList.class);

	private int				adClientID			= 0;
	private boolean			deleteOldImported	= false;
	private int				socioNegocio		= 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		for (final ProcessInfoParameter element : para) {
			final String name = element.getParameterName();
			if (element.getParameter() != null) {
				if (name.equals("C_BPartner_ID")) {
					socioNegocio = ((BigDecimal) element.getParameter()).intValue();
				} else if (name.equals("AD_Client_ID")) {
					adClientID = ((BigDecimal) element.getParameter()).intValue();
				} else if (name.equals("DeleteOldImported")) {
					deleteOldImported = "Y".equals(element.getParameter());
				} else {
					log.log(Level.SEVERE, "Unknown Parameter: " + name);
				}
			}
		}

	}

	/**
	 * Metodo que realiza la importacion de precios de producto
	 *
	 * @autor pmendoza
	 */
	@Override
	protected String doIt() throws java.lang.Exception {
		/*
		 * Para el desarrollador del futuro que le toque mover a este metodo
		 *
		 * El proceso se realiza para cada registro que no haya sido importado
		 * aun.
		 *
		 * Por cada registro que se va a importar se crea un transaccion nueva y
		 * en caso de que suceda algun error o excepcion este se debe de agregar
		 * a la lista de "importErrors" ya que esta lista maneja la excepciones
		 * y al final del proceso se barre para actualizar los registros que
		 * tuvieron errores y asi guardar el mensaje de la excpecion en el campo
		 * I_ErrorMsg e indicandar que el registro a no sido importado aun.
		 *
		 * Este proceso de reescribio completamente apatir de una version
		 * antigua que no funcionaba, si hay algo que omite del proceso anterior
		 * fue por indicaciones externas o por que causaba confuncion en lo
		 * que realizaba el proceso
		 *
		 * 18/09/2012
		 *
		 * @autor pmendoza
		 */

		// Paso previo a la importación
		if (deleteOldImported) {
			MI_EXME_ListaPrecio.deleteImported(getCtx(), adClientID);
		}
		int configECID = 0;
		final MConfigEC configEC = MConfigEC.get(getCtx(), get_TrxName());
		if (configEC != null && configEC.getEXME_ConfigEC_ID() > 0) {
			configECID = configEC.getEXME_ConfigEC_ID();
		}

		// Inicio a la importación

		/*
		 * Lista de precios que no se an importado aun
		 */
		final List<X_I_EXME_ListaPrecio> notImported = new Query(getCtx(), I_I_EXME_ListaPrecio.Table_Name, " I_IsImported='N' AND isActive='Y' ",
				null).addAccessLevelSQL(true).list();

		/*
		 * Lista para el control de error y excepciones en el proceso de
		 * importacion
		 */
		final List<ImportException> importErrors = new ArrayList<ImportException>();

		for (final X_I_EXME_ListaPrecio item : notImported) {
			final Trx mTrx = Trx.get(Trx.createTrxName("importPriceList"), true);
			final String trxName = mTrx.getTrxName();
			/*
			 * Nota: Se hace teniendo en cuenta que se estaria instancion el
			 * objecto por segunda vez, pero si no se hace de esta forma la
			 * primera instancia venia con campos o valores imcompletos
			 */
			final MI_EXME_ListaPrecio iListPrecio = new MI_EXME_ListaPrecio(getCtx(), item.getI_EXME_ListaPrecio_ID(), null);
			try {

				iListPrecio.prepareToImport();


				/*
				 * Verificamos si se trata de una lista de compra o venta
				 *
				 */
				boolean isVenta = false;
				final int listaID = item.getM_PriceList_ID();
				final MPriceList priceList = MPriceList.get(getCtx(), listaID, null);
				if(priceList!=null){
					isVenta = priceList.isSOPriceList();
				}


				if (iListPrecio.getM_Product_ID() > 0) {
					if (iListPrecio.getPriceList_Vol() == null || iListPrecio.getPriceList_Vol().doubleValue() <= 0) {
						throw new ImportException(getLabel("import.price.error.OUMVol1"),iListPrecio.getI_EXME_ListaPrecio_ID());
					}

					/*
					 * se establecen las unidades de medida de acuerdo al producto importado
					 */
					final MUOM oumMin = new MUOM(getCtx(), iListPrecio.getM_Product().getC_UOM_ID(), null);
					iListPrecio.setC_UOM_ID(oumMin.getC_UOM_ID());
					iListPrecio.setC_UOM_Name(oumMin.getName());
					iListPrecio.setC_UOMVolume_ID(iListPrecio.getM_Product().getC_UOMVolume_ID());


					/*
					 * Caculo del nuevo precio de unidad minima apartir de la
					 * undidad de volumen
					 */
					final BigDecimal newPrice = MEXMEUOMConversion.convertProductTo(
							getCtx(), iListPrecio.getM_Product_ID(), iListPrecio.getC_UOMVolume_ID(), iListPrecio.getPriceList_Vol(), null);

					if (newPrice == null) {
						throw new ImportException(getLabel("import.price.error.OUMVol2"),iListPrecio.getI_EXME_ListaPrecio_ID());
					}

					/*
					 * Se obtiene el productPriceActual, si no hay uno se crea
					 */
					X_M_ProductPrice currentProductPrice = new Query(getCtx(), I_M_ProductPrice.Table_Name, " M_PRODUCT_ID="
							+ iListPrecio.getM_Product_ID()
							+ " and M_PRICELIST_VERSION_ID="
							+ iListPrecio.getM_PriceList_Version_ID(), trxName).addAccessLevelSQL(true).first();

					if (currentProductPrice == null) {
						currentProductPrice = new X_M_ProductPrice(getCtx(), 0, trxName);
						currentProductPrice.setM_Product_ID(iListPrecio.getM_Product_ID());
						currentProductPrice.setM_PriceList_Version_ID(iListPrecio.getM_PriceList_Version_ID());
					}

					final BigDecimal currentPrice = currentProductPrice.getPriceList();


					/*
					 * Actualizacion de los nuevos precios en m_productprice
					 */
					currentProductPrice.setC_UOM_ID(iListPrecio.getC_UOM_ID());
					/*
					 * solo se actualiza si es de compra, si es de venta se queda con el precio
					 * especificado en el archivo
					 */
					if(isVenta){
						currentProductPrice.setPriceList(iListPrecio.getPrecio());
					}else{
						currentProductPrice.setPriceList(newPrice);
					}
					if(isSpecialBill(iListPrecio.getM_PriceList_ID())){
						currentProductPrice.setProductDescription(iListPrecio.getProductDescription());
						currentProductPrice.setProductValue(iListPrecio.getProductValue());
					}
					currentProductPrice.setPriceList_Vol(iListPrecio.getPriceList_Vol());
					currentProductPrice.setC_UOMVolume_ID(iListPrecio.getC_UOMVolume_ID());
					if (!currentProductPrice.save(trxName)) {
						throw new ImportException(getError(), iListPrecio.getI_EXME_ListaPrecio_ID());
					}

					/*
					 * Se guarda un historico de cambios en el precio del
					 * producto
					 */
					final X_EXME_AudListaPrecio aud = new X_EXME_AudListaPrecio(getCtx(), 0, get_TrxName());
					aud.setM_PriceList_ID(iListPrecio.getM_PriceList_ID());
					aud.setM_PriceList_Version_ID(iListPrecio.getM_PriceList_Version_ID());
					if (socioNegocio > 0) {
						aud.setC_BPartner_ID(socioNegocio);
					}
					aud.setM_Product_ID(iListPrecio.getM_Product_ID());
					aud.setC_UOM_ID(iListPrecio.getC_UOM_ID());
					aud.setPrecioAnterior(currentPrice.setScale(2, BigDecimal.ROUND_UP));
					aud.setPrecioNuevo(newPrice.setScale(2, BigDecimal.ROUND_UP));
					aud.setEXME_ConfigEC_ID(configECID);
					if (!aud.save(trxName)) {
						throw new ImportException(getError(), iListPrecio.getI_EXME_ListaPrecio_ID());
					}

					/*
					 * Actualizacion del registro de importacion importado,
					 * procesado y sin mensajes de error
					 */
					iListPrecio.setI_IsImported(true);
					iListPrecio.setProcessed(true);
					iListPrecio.setI_ErrorMsg(null);
					if (!iListPrecio.save(trxName)) {
						throw new ImportException(getError(), iListPrecio.getI_EXME_ListaPrecio_ID());
					}

				} else {
					throw new ImportException(getLabel("error.oc.noproduct"), iListPrecio.getI_EXME_ListaPrecio_ID());
				}
				mTrx.commit();
			} catch (final ImportException e) {
				mTrx.rollback();
				LOGGER.log(Level.INFO, "I_EXME_ListaPrecio_ID=" +e.getI_EXME_ListaPrecio_ID());
				importErrors.add(e);
			} catch (final Exception e) {
				mTrx.rollback();
				LOGGER.log(Level.SEVERE, "I_EXME_ListaPrecio_ID=" + iListPrecio.getI_EXME_ListaPrecio_ID(), e);
				importErrors.add(new ImportException(e.getLocalizedMessage(), iListPrecio.getI_EXME_ListaPrecio_ID()));
			} finally {
				mTrx.close();
			}
		}
		for (final ImportException error : importErrors) {
			final MI_EXME_ListaPrecio iListPrecio = new MI_EXME_ListaPrecio(getCtx(), error.getI_EXME_ListaPrecio_ID(), null);
			iListPrecio.setI_IsImported(false);
			iListPrecio.setProcessed(false);
			iListPrecio.setI_ErrorMsg(error.getMessage());
			iListPrecio.save();
		}
		return null;
	}

	/**
	 * Obtiene el mensaje de error de la consola. Util cuando un proceso save()
	 * de PO regresa false
	 *
	 * @return
	 */
	private String getError() {
		final ValueNamePair error = CLogger.retrieveError();
		if (error == null) {
			return null;
		} else {
			return error.getValue() + " " + error.getName();
		}
	}

	/**
	 * Clase para menajer las exceptiones, se agrega el id del registro donde
	 * ocurrio una exception para luego ser procesados
	 *
	 * @author pedro
	 *
	 */
	private class ImportException extends Exception {
		private static final long	serialVersionUID	= 1L;
		private final int			recordID;

		/**
		 *
		 * @param message
		 *            Mensaje de Error
		 * @param iexmeListaPrecioID
		 *            ID del registro donde ocurrio el error
		 */
		public ImportException(final String message, final int iexmeListaPrecioID) {
			super(message);
			recordID = iexmeListaPrecioID;
		}

		public int getI_EXME_ListaPrecio_ID() {
			return recordID;
		}
	}

	private String getLabel(final String key) {
		String retValue;
		final ResourceBundle res = Utilerias.getApplicationResources(getCtx(), Env.getLanguage(getCtx()).getLocale());
		try {
			if (res == null) {
				retValue = key;
			} else {
				retValue = res.getString(key);
			}
		}
		catch (final Exception e) {
			LOGGER.config("Key not found in the ApplicationResources: " + key);
			// if label is not found, searches it as Message or Element
			retValue = key;
		}
		return retValue;
	}

	/**
	 * es factura especial
	 * @return
	 */
	private boolean isSpecialBill(int piceLstId){
		final MBPartner partner = MBPartner.getParterByPriceLst(piceLstId);
		return partner.isFactEspec();
	}
}