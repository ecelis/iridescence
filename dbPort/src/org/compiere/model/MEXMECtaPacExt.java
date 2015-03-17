package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.bean.TaxConcepts;
import org.compiere.model.billing.BeanAmtInvoice;
import org.compiere.model.bpm.BeanView;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;
import org.compiere.util.WebEnv;
import org.compiere.util.vo.EncounterTransVO;
/**
 * Extension de la cuenta paciente
 * @author twry
 */
public class MEXMECtaPacExt extends X_EXME_CtaPacExt {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 2302000854960687670L;
	/** Static Logger               */
	private static CLogger      s_log = CLogger.getCLogger (MEXMECtaPacExt.class);
	/** Process Message */
	private String m_processMsg = null;
	/** listado de cargos */
	private List<MCtaPacDet> lstCargos;
	/** REDONDE  */
	private int REDONDEO2 = 2;
	/** objeto de la cuenta paciente */
	private MEXMECtaPac m_ctaPac = null;
	private MLocation m_location = null; // direccion del paciente.
	/** Impuesto del descuento global */
	private BigDecimal taxDescAmt = Env.ZERO;
	/** factura */
	private MInvoice m_invoice = null;
	private MEXMEBPartner m_bPartner = null;
	private MCtaPacDet[] lineasDeExtension = null;
	private MCtaPacDet[] lineaDeducible = null;
	private MCtaPacDet[] lineaCoaseguro = null;
	private MCtaPacDet[] lineaCopago = null;
	private MCtaPacDet[] lineaCoaseguroMed = null;
//	private int lineaCoa = 0;
//	private int lineaDed = 0;
//	private int lineaCoaMed = 0;
//	private int lineaCop = 0;
	private MConfigPre m_configPre = null;
	/** Objeto para almacenar la Aseguradora actual **/
	private MBPartner currentPayer = null;
	
	private MInvoice mNoteSales = null;
	private MInvoice mDocInvoice = null;
	private BigDecimal totalAnticipos = Env.ZERO;
	private MEXMEAnticipo mAnticipo = null;
	
	/**
	 * Constructor ID
	 * @param ctx
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 */
	
	public MEXMECtaPacExt(Properties ctx, int EXME_CtaPacExt_ID, String trxName) {
		super(ctx, EXME_CtaPacExt_ID, trxName);

		if (EXME_CtaPacExt_ID == 0) {

			// Valores por defecto.
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DocAction.ACTION_Complete);
			setProcessed(false);
			setProcessing(false);
			setIsApproved(true);
			setIsPrinted(false);
			setDateOrdered(new Timestamp(System.currentTimeMillis()));
			setDateAcct(getDateOrdered());
		}
	}

	/**
	 * Constructor ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	
	public MEXMECtaPacExt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	
		if (getEXME_CtaPacExt_ID() == 0) {
			// Valores por defecto.
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DocAction.ACTION_Complete);
			setProcessed(false);
			setProcessing(false);
			setIsApproved(true);
			setIsPrinted(false);
			setDateOrdered(new Timestamp(System.currentTimeMillis()));
			setDateAcct(getDateOrdered());
		}
	}
	
	/**
	 * Creamos una extension a partir de una cuenta paciente.
	 * Facturacion por extensiones
	 * @param ctaPac
	 * @param ExtensionNo
	 * @param trxName
	 */
	public MEXMECtaPacExt(MEXMECtaPac ctaPac, String trxName) {
		this(ctaPac.getCtx(), 0, trxName);

		// Copiamos los valores por defecto a partir de la Extension cero.
		MEXMECtaPacExt ext = new MEXMECtaPacExt(getCtx(), ctaPac.getEXME_CtaPacExt_ID(), trxName);
		PO.copyValues(ext, this);
		
		setCtaPac(ctaPac);
		setExtensionNo();
		// Setear a cero el valor de la factura
		setC_Invoice_ID(0);
		// Setear el valor del anticipo a cero.
		setAnticipo(Env.ZERO);
		setAD_OrgTrx_ID(ctaPac.getAD_OrgTrx_ID());
		
		// Se crea la referencia a la extension cero
		setRef_CtaPacExt_ID(ext.getEXME_CtaPacExt_ID());
		
		//Se crea una direccion por cada extension pero los datos iniciales son de la extension 0
		MLocation loc = MEXMELocation.copyFrom(ctaPac.getCtx(), ext.getC_Location_ID(), 0, trxName); 
		if(loc!=null && loc.save(trxName)){
			setC_Location_ID(loc.getC_Location_ID());
		}
	}

	/**
	 * Iniciamos los datos 
	 * para la extension cero
	 */
	public void setDataExtCero(){
		
		final String whereClause = 
				" AND AD_Org_ID = ? AND upper(name) like ? ";// Que sea el documento para la extension
		final MEXMEDocType[] dt = 
				MEXMEDocType.getOfDocSubTypeSO(
						getCtx(), 
						MDocType.DOCSUBTYPESO_CgosACtaPac,
						whereClause,
						new Object[] {Env.getAD_Org_ID(getCtx()), "%EXT%"},
						1
				); //Lama
		int docType = dt.length > 0 ? dt[0].getC_DocType_ID() : 0;
		setC_DocType_ID(docType);
		setC_DocTypeTarget_ID(docType);
		setC_BPartner_ID(getCtaPac().getC_BPartner_ID());// Extension cero con el paciente
		setC_BPartner_Location_ID(getCtaPac().getPaciente().getC_BPartner_Location_ID());
		//Colocar el nombre completo. Parte del RQM 2298
		setDescription(getCtaPac().getPaciente().getNombre_Pac());
		setAD_OrgTrx_ID(getCtaPac().getAD_OrgTrx_ID());
		setRfc(getCtaPac().getPaciente().getRfc());
		setC_Location_ID(getCtaPac().getPaciente().getC_Location_ID());
		setChargeAmt(Env.ZERO);                                                                                                                                                                                                                       
		setTotalLines(Env.ZERO);                                                                                                                                                                                                              
		setGrandTotal(Env.ZERO);                                                                                                                                                                                                              
		setAD_User_ID(Env.getAD_User_ID(getCtx()));                                                                                                                                                                      
		setDesctoGlobal(Env.ZERO);                                                                                                                                                                                                            
		setCoaseguro(Env.ZERO);                                                                                                                                                                                                             
		setDeducible(Env.ZERO);                                                                                                                                                                                                               
		setExtensionNo(0);                                                                                                                                                                                                         
		setIsCoasegPercent(false);                                                                                                                                                                                                        
		setIsDeducInCoaseg(false);                                                                                                                                                                                                                
		setDesctoGlobalAmt(Env.ZERO);                                                                                                                                                                                                   
		setAnticipo(Env.ZERO);                                                                                                                                                                                                                       
		setCoaseguroMed(Env.ZERO);                                                                                                                                                                                                                    
		setCopago(Env.ZERO);                                                                                                                                                                                                      
	}
	
	
	/**
	 * Proceso para asignar las lineas de la Extension
	 * @throws SQLException 
	 * @param incluirLineasDescuentos true: Incluye todas las lineas
	 * false: Excluye CCCmD y descuento
	 *
	 */
	public boolean cargarLineasDeExtension(final boolean incluirDescuentos, final boolean saveInvoice) 
	throws SQLException {
		return cargarLineasDeExtension(incluirDescuentos, saveInvoice, false);
	}
		/**
		 * Proceso para asignar las lineas de la Extension
		 * @throws SQLException 
		 * @param incluirLineasDescuentos true: Incluye todas las lineas
		 * false: Excluye CCCmD y descuento
		 *
		 */
	public boolean cargarLineasDeExtension(final boolean incluirDescuentos, final boolean saveInvoice, final boolean reload) 
		throws SQLException {
			
		boolean success = true;
		setLineaCoaseguro(null);
		setLineaDeducible(null);
		setLineaCopago(null);
		setLineaCoaseguroMed(null);
		taxDescAmt = Env.ZERO;
		
		//para no considerar las devoluciones y si considerar lo facturado
		final MCtaPacDet[] arrMCtaPacDet = getCargosDeLaExtension(MEXMECtaPacExt.condicionCargos ());

		try {
			// si existen lineas
			List<MCtaPacDet> lineasDeducible = new ArrayList<MCtaPacDet>();
			List<MCtaPacDet> lineasCoaseguro = new ArrayList<MCtaPacDet>();
			List<MCtaPacDet> lineasCopago = new ArrayList<MCtaPacDet>();
			List<MCtaPacDet> lineasCoaseguroMed = new ArrayList<MCtaPacDet>();
			List<MCtaPacDet> lineasFinales = new ArrayList<MCtaPacDet>();

			//Si existen cargos de la cuenta paciente
			if (arrMCtaPacDet != null) {
				
				int cont = 10;
				for (int x = 0; x < arrMCtaPacDet.length; x++) {

					if(arrMCtaPacDet[x].isVisible()){
						// Deducible
						if(X_EXME_CtaPacDet.TIPOLINEA_Deducible.equals(arrMCtaPacDet[x].getTipoLinea())
								&& arrMCtaPacDet[x].getM_Product_ID() == getConfigPre().getDeducible_ID()){
//							setLineaDed(arrMCtaPacDet[x].getEXME_CtaPacDet_ID());
							lineasDeducible.add(arrMCtaPacDet[x]);
							if(!incluirDescuentos){									
								continue;
							}
						}
						// Coaseguro
						if(X_EXME_CtaPacDet.TIPOLINEA_Coaseguro.equals(arrMCtaPacDet[x].getTipoLinea())
								&& arrMCtaPacDet[x].getM_Product_ID() == getConfigPre().getCoaseguro_ID()){
//							setLineaCoa(arrMCtaPacDet[x].getEXME_CtaPacDet_ID());
							lineasCoaseguro.add(arrMCtaPacDet[x]);
							if(!incluirDescuentos){
								continue;
							}
						}
						// Coaseguro Medico
						if(X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico.equals(arrMCtaPacDet[x].getTipoLinea())
								&& arrMCtaPacDet[x].getM_Product_ID() == getConfigPre().getCoaseguroMed_ID()){
//							setLineaCoaMed(arrMCtaPacDet[x].getEXME_CtaPacDet_ID());
							lineasCoaseguroMed.add(arrMCtaPacDet[x]);
							if(!incluirDescuentos)
								continue;
						}
						// Copago
						if(X_EXME_CtaPacDet.TIPOLINEA_Copago.equals(arrMCtaPacDet[x].getTipoLinea())
								&& arrMCtaPacDet[x].getM_Product_ID() == getConfigPre().getCoPago_ID()){
//							setLineaCop(arrMCtaPacDet[x].getEXME_CtaPacDet_ID());
							lineasCopago.add(arrMCtaPacDet[x]);
							if(!incluirDescuentos){
								continue;
							}
						}

						// Descuento
						if(arrMCtaPacDet[x].getC_Charge_ID() == getConfigPre().getC_Charge_ID()){
							if(!incluirDescuentos){
								taxDescAmt = taxDescAmt.add(arrMCtaPacDet[x].getTaxAmt());
								continue;
							}
						}

						arrMCtaPacDet[x].setLine(cont);
						cont = cont + 10;
					}
					lineasFinales.add(arrMCtaPacDet[x]);						
				}
					
				//Lineas Finales PARA FACTURAR
				if (getLineasDeExtension() == null || getLineasDeExtension().length <= 0) {
					final MCtaPacDet[] finales = new MCtaPacDet[lineasFinales.size()];
					lineasFinales.toArray(finales);
					setLineasDeExtension(finales);
				}				
				
				//Lineas de deducible
				final MCtaPacDet[] lista1 = new MCtaPacDet[lineasDeducible.size()];
				lineasDeducible.toArray(lista1);
				setLineaDeducible(lista1);

				//Lineas de coaseguro
				final MCtaPacDet[] lista2 = new MCtaPacDet[lineasCoaseguro.size()];
				lineasCoaseguro.toArray(lista2);
				setLineaCoaseguro(lista2);
				
				//Lineas de coaseguro Medico
				final MCtaPacDet[] lista3 = new MCtaPacDet[lineasCoaseguroMed.size()];
				lineasCoaseguroMed.toArray(lista3);
				setLineaCoaseguroMed(lista3);
				
				//Lineas de copago
				final MCtaPacDet[] lista4 = new MCtaPacDet[lineasCopago.size()];
				lineasCopago.toArray(lista4);
				setLineaCopago(lista4);
				
				if(saveInvoice && getBpartner()!=null && getBpartner().isAseguradora()){

					if(lista1.length>1){
						s_log.log(Level.SEVERE, "Mas de un de deducible");
						success = false;
					}
					if(lista2.length>1){
						s_log.log(Level.SEVERE, "Mas de un de coaoseguro");
						success = false;
					}
					if(lista3.length>1){
						s_log.log(Level.SEVERE, "Mas de un de coaseguro médico");
						success = false;
					}
					if(lista4.length>1){
						s_log.log(Level.SEVERE, "Mas de un de copago");
						success = false;
					}
				}
				if(reload){
					setLineasDeExtension(arrMCtaPacDet);
				}
			}

		} catch (Exception s) {
			s_log.log(Level.SEVERE, s.getMessage(), s);
			success = false;
		}
		
//		arrMCtaPacDet = null;
		return success;
	}
	
	/**
	 * Calcular el monto del anticipo que le corresponderia a la extension
	 * @param grandTotalExt total de cargos de la extensión
	 * @return
	 */
	private BigDecimal calculateAmtPrePayment(final BigDecimal grandTotalExt) {
		final MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), get_TrxName());
		BigDecimal amt;
		if (anticipo == null) {
			amt =  Env.ZERO;
			
		} else {
			// Si el anticipo es mayor a el grantotal, devuelve el grantotal como anticipo
			if (anticipo.getTotal().compareTo(grandTotalExt) > 0) {
				amt = grandTotalExt;
			} else {
				amt = anticipo.getTotal();
			}
		}
		return amt;
	}
	
	/**
	 * Calcular los totales de una extension
	 * @return
	 */
	private BeanAmtInvoice calculateTotals(final boolean includeDiscount) {
		BigDecimal totalBruto = BigDecimal.ZERO;
		BigDecimal subTotal   = BigDecimal.ZERO;
		BigDecimal totalNeto  = BigDecimal.ZERO;
		BigDecimal taxAmt     = BigDecimal.ZERO;
		BigDecimal granTotal  = BigDecimal.ZERO;
		
		final MCtaPacDet[] lineas = this.getLineasDeExtension();
		if(lineas != null){
			for(final MCtaPacDet cargo: lineas){
				totalBruto = totalBruto.add(cargo.getLineNetAmt());	
				taxAmt = taxAmt.add(cargo.getTaxAmt());
			}
			
			// Suma de los netos de los cargos - descuento global
			if(includeDiscount){
				subTotal  = subTotal.add(totalBruto.add(getDesctoGlobalAmt()));
				totalNeto = totalNeto.add(subTotal.add(getDeducibleAmt().add(getCoaseguroAmt()).add(getCopagoAmt()).add(getCoaseguroMedAmt())));		
				granTotal = totalNeto.add(taxAmt).add(getTaxDescAmt());
				
			} else {
				// Suma de los netos de los cargos - descuento global
				subTotal = subTotal.add(totalBruto);
				totalNeto = totalNeto.add(subTotal);//.add(getDeducibleAmt().add(getCoaseguroAmt()).add(getCopagoAmt()).add(getCoaseguroMedAmt())));		
				granTotal = totalNeto.add(taxAmt);
			}
		}
		
		final BeanAmtInvoice bean = new BeanAmtInvoice ();
		bean.setTotalVta(totalBruto);// Antes de descuento (suma de los cargos con convenio aplicado)
		bean.setTotal(subTotal);// Con descuento
		bean.setSubtotal(totalNeto);// Antes de impuesto
		bean.setImpuesto(taxAmt);
		bean.setGrantotal(granTotal);// Con impuesto
		bean.setAnticipos(calculateAmtPrePayment(granTotal));
		return bean;
	}
	
	/**
	 * Calculo de Totales de la Extension
	 * Para vista en facturacion por extensiones
	 * @param trxName
	 *  
	 **/
	private boolean updateAmountsOfExtension(final boolean includeDiscount, final String trxName){
		final BeanAmtInvoice bean = calculateTotals(includeDiscount);

		this.setTotalBruto(bean.getTotalVta());// Todas las lineas de cargo
		this.setSubTotal(bean.getTotal());// menos descuento
		this.setTotalNeto(bean.getSubtotal());// Con CCCmD//11598.84 antes 
		if(includeDiscount){
			this.setTaxAmt(bean.getImpuesto().add(getTaxDescAmt()));// suma de impuesto de las lineas de cargo
		} else {
			this.setTaxAmt(bean.getImpuesto());// suma de impuesto de las lineas de cargo
		}
		
		this.setGrandTotal(bean.getGrantotal());// total con CCCmD mas impuesto
		this.setAnticipo(bean.getAnticipos());// Anticipos
		return save(trxName);
	}
	
	/**
	 * Calculo de Totales de la Extension
	 * Para vista en facturacion por extensiones
	 * @param trxName
	 *  
	 **/
	public boolean calcularTotales(String trxName){
//		BigDecimal totalBruto = BigDecimal.ZERO;
////		BigDecimal discountAmt = BigDecimal.ZERO;
//		BigDecimal subTotal = BigDecimal.ZERO;
////		BigDecimal deducibleAmt = BigDecimal.ZERO;
////		BigDecimal coaseguroAmt = BigDecimal.ZERO;
//		BigDecimal totalNeto = BigDecimal.ZERO;
//		BigDecimal taxAmt = BigDecimal.ZERO;
//		BigDecimal anticiposAmt = BigDecimal.ZERO;
//		BigDecimal granTotal = BigDecimal.ZERO;
//		
//		MCtaPacDet[] lineas = this.getLineasDeExtension();
//		if(lineas != null){
//			for(int i=0; i < lineas.length; i++){
//				MCtaPacDet cargo = lineas[i];
//				totalBruto = totalBruto.add(cargo.getLineNetAmt());	
//				taxAmt = taxAmt.add(cargo.getTaxAmt());
//			}
//			
//			// Suma de los netos de los cargos - descuento global
//			subTotal = subTotal.add(totalBruto.add(getDesctoGlobalAmt()));
//			totalNeto = totalNeto.add(subTotal.add(getDeducibleAmt().add(getCoaseguroAmt()).add(getCopagoAmt()).add(getCoaseguroMedAmt())));		
//			anticiposAmt = this.getAnticiposAmt();		
//			granTotal = (totalNeto.add(taxAmt).add(getTaxDescAmt()));
//		}
//		this.setTotalBruto(totalBruto);// Todas las lineas de cargo
//		this.setSubTotal(subTotal);// menos descuento
//		this.setTotalNeto(totalNeto);// Con CCCmD//11598.84 antes                
//		this.setTaxAmt(taxAmt.add(getTaxDescAmt()));// suma de impuesto de las lineas de cargo
//		this.setAnticipo(anticiposAmt);// Anticipos
//		this.setGrandTotal(granTotal);// total con CCCmD mas impuesto
//		
//		if(!this.save(trxName)){
//			return false;
//		}
		
		return  updateAmountsOfExtension(true, trxName);
	}
	
	/**
	 * Calculo de Totales de la Extension
	 * Para vista en facturacion por extensiones
	 * @param trxName
	 *  
	 **/
	public boolean calcularTotales(final boolean descuentoIncluido, final String trxName){
//		BigDecimal totalBruto = BigDecimal.ZERO;
////		BigDecimal discountAmt = BigDecimal.ZERO;
//		BigDecimal subTotal = BigDecimal.ZERO;
////		BigDecimal deducibleAmt = BigDecimal.ZERO;
////		BigDecimal coaseguroAmt = BigDecimal.ZERO;
//		BigDecimal totalNeto = BigDecimal.ZERO;
//		BigDecimal taxAmt = BigDecimal.ZERO;
//		BigDecimal anticiposAmt = BigDecimal.ZERO;
//		BigDecimal granTotal = BigDecimal.ZERO;
//		
//		MCtaPacDet[] lineas = this.getLineasDeExtension();
//		if(lineas != null){
//			for(int i=0; i < lineas.length; i++){
//				MCtaPacDet cargo = lineas[i];
//				totalBruto = totalBruto.add(cargo.getLineNetAmt());	
//				taxAmt = taxAmt.add(cargo.getTaxAmt());
//			}
//			
//			// Suma de los netos de los cargos - descuento global
//			subTotal = subTotal.add(totalBruto);
//			totalNeto = totalNeto.add(subTotal);//.add(getDeducibleAmt().add(getCoaseguroAmt()).add(getCopagoAmt()).add(getCoaseguroMedAmt())));		
//			anticiposAmt = this.getAnticiposAmtTotal();		
//			granTotal = (totalNeto.add(taxAmt));
//		}
//		
//		this.setTotalBruto(totalBruto);// Todas las lineas de cargo
//		this.setSubTotal(subTotal);// menos descuento
//		this.setTotalNeto(totalNeto);// Con CCCmD//11598.84 antes                
//		this.setTaxAmt(taxAmt);// suma de impuesto de las lineas de cargo
//		this.setAnticipo(anticiposAmt);// Anticipos
//		this.setGrandTotal(granTotal);// total con CCCmD mas impuesto
//		
//		if(!this.save(trxName)){
//			return false;
//		}
		
//		return true;
		return updateAmountsOfExtension(descuentoIncluido, trxName);
	}
	
	/**
	 * Impuesto del descuento global
	 */
	private BigDecimal getTaxDescAmt(){
		return 	taxDescAmt;
	}
	
	/**
	 * Creacion de linea que ve el usuario como descuento global
	 * 
	 * @return
	 */
//	public MCtaPacDet lineaDescuento(final BigDecimal impuestoDesc) {
//		
//		MCtaPacDet linea = new MCtaPacDet (getCtx(), 0, get_TrxName());
//		try{
//			//agregamos la linea que ve el usuario del descuento global
//			linea.completeLine(getEXME_CtaPacExt_ID(),
//					getConfigPre().getC_Charge_ID(), 0, 
//					getDesctoGlobalAmt(), impuestoDesc); 
////					true);
//			
//			linea.setVisible(true);
//			linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Discount);
//			linea.setTaxAmt(impuestoDesc);
//			
//		}catch (Exception e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de descuento que ve el usuario : ", e);
//			linea = null;
//		}
//		return linea;
//	}
	
	/**
	 * Creacion de linea que ve el usuario como deducible
	 * @param trxName
	 * @return error
	 */
//	public MCtaPacDet creaLineaDeducible(boolean negativo, BigDecimal deducible) {
//		
//		MCtaPacDet linea = new MCtaPacDet (getCtx(), 0, get_TrxName());
//		try{
//			//agregamos la linea que ve el usuario de deducible
//			linea.completeLine(getEXME_CtaPacExt_ID(), 
//					0, getConfigPre().getDeducible_ID(), 
//					deducible, null);
////					negativo);
//			
//			linea.setVisible(true);
////			linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Deducible);
////			linea.setTaxAmt(Env.ZERO);
//			
//			
//			// El cargo de la aseguradora es negativo
//			// es deducible el tipo de linea
//			// y no lleva impuesto
//			if(negativo){
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Deducible);
//				linea.setC_Tax_ID(MEXMETax.getExemptTaxID(getCtx(), get_TrxName()));
//				linea.setTaxAmt(Env.ZERO);
//			}else{
//				// El cargo del particular es positivo
//				// es cargo(normal) el tipo de linea
//				// y lleva impuesto (deacuerdo a la configuracion del producto)
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Charge);
//				setPricesTaxPatient(linea);
//			}
//						
//			if(!negativo && !linea.save(get_TrxName())){
//				linea = null;
//			}
//			
//		}catch (Exception e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de deducible que ve el usuario : ", e);
//			linea = null;
//		}
//		
//		return linea;
//	}
	
	/**
	 * Creacion de linea que ve el usuario como coaseguro
	 * @param trxName
	 * @return error
	 */
//	public MCtaPacDet creaLineaCoaseguro(boolean negativo, BigDecimal coaseguro) {
//		
//		MCtaPacDet linea = new MCtaPacDet (getCtx(), 0, get_TrxName());
//		try{
//			linea.completeLine(getEXME_CtaPacExt_ID(), 
//					0, getConfigPre().getCoaseguro_ID(), 
//					coaseguro, null);
////					negativo);
//			
//			linea.setVisible(true);
//
//			// El cargo de la aseguradora es negativo
//			// es coaseguro el tipo de linea
//			// y no lleva impuesto
//			if(negativo){
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Coaseguro);
//				linea.setC_Tax_ID(MEXMETax.getExemptTaxID(getCtx(), get_TrxName()));
//				linea.setTaxAmt(Env.ZERO);
//			}else{
//				// El cargo del particular es positivo
//				// es cargo(normal) el tipo de linea
//				// y lleva impuesto (deacuerdo a la configuracion del producto)
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Charge);
//				setPricesTaxPatient(linea);
//			}
//
//			if(!negativo && !linea.save(get_TrxName())){//TODO revisar ...
//				linea = null;
//			}
//			
//		}catch (Exception e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de coaseguro que ve el usuario : ", e);
//			linea = null;
//		}
//		
//		return linea;
//	}
	
	/**
	 * Creacion de linea que ve el usuario como copago
	 * @param trxName
	 * @return error
	 */
//	public MCtaPacDet creaLineaCopago(boolean negativo, BigDecimal copago) {
//		
//		MCtaPacDet linea = new MCtaPacDet (getCtx(), 0, get_TrxName());
//		try{
//			linea.completeLine(getEXME_CtaPacExt_ID(), 
//					0, getConfigPre().getCoPago_ID(), 
//					copago, null);
//			
//			linea.setVisible(true);
////			linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Copago);
////			linea.setTaxAmt(Env.ZERO);
//			
//			
//			if(negativo){
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Copago);
//				linea.setC_Tax_ID(MEXMETax.getExemptTaxID(getCtx(), get_TrxName()));
//				linea.setTaxAmt(Env.ZERO);
//			}else{
//				// El cargo del particular es positivo
//				// es cargo(normal) el tipo de linea
//				// y lleva impuesto (deacuerdo a la configuracion del producto)
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Charge);
//				setPricesTaxPatient(linea);
//			}
//			
//			if(!negativo && !linea.save(get_TrxName())){
//				linea = null;
//			}
//			
//		}catch (Exception e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de copago que ve el usuario : ", e);
//			linea = null;
//		}
//		
//		return linea;
//	}
	
	/**
	 * Creacion de linea que ve el usuario como coaseguro Medico
	 * @param trxName
	 * @return error
	 */
//	public MCtaPacDet creaLineaCoaseguroMed(boolean negativo, BigDecimal coaseguroMed) {
//		
//		MCtaPacDet linea = new MCtaPacDet (getCtx(), 0, get_TrxName());
//		try{
//			linea.completeLine(getEXME_CtaPacExt_ID(), 
//					0, getConfigPre().getCoaseguroMed_ID(), 
//					coaseguroMed, null);
//			
//			linea.setVisible(true);
////			linea.setTipoLinea(MCtaPacDet.TIPOLINEA_CoaseguroMedico);
////			linea.setTaxAmt(Env.ZERO);
//			
//			if(negativo){
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_CoaseguroMedico);
//				linea.setC_Tax_ID(MEXMETax.getExemptTaxID(getCtx(), get_TrxName()));
//				linea.setTaxAmt(Env.ZERO);
//			}else{
//				// El cargo del particular es positivo
//				// es cargo(normal) el tipo de linea
//				// y lleva impuesto (deacuerdo a la configuracion del producto)
//				linea.setTipoLinea(MCtaPacDet.TIPOLINEA_Charge);
//				setPricesTaxPatient(linea);
//			}
//			
//			if(!negativo && !linea.save(get_TrxName())){
//				linea = null;
//			}
//			
//		}catch (Exception e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de coaseguro medico que ve el usuario : ", e);
//			linea = null;
//		}
//		
//		return linea;
//	}
	
	/**
	 * setPricesTaxPatient
	 */
//	private void setPricesTaxPatient(final MCtaPacDet linea){
//		if(linea.getProducto()!=null
//				&& linea.getProducto().getTax()!=null){
//			// Obtenemos el impuesto configurado
//			linea.setC_Tax_ID(linea.getProducto().getTax().getC_Tax_ID());
//			// Se calcula el monto del impuesto YA INCLUIDO en el importe
//			linea.setTaxAmt(true);
//			// Se asigna el importe a las columnas sin impuesto
//			linea.setPrices(linea.getPriceList().subtract(linea.getTaxAmt()), false);
//		}
//	}
	
	
	/** ANTES MCTAPACEXT */
	
	/**
	 * Establecemos la cuenta paciente a la que pertecence la extension.
	 * 
	 * @param ctaPac
	 */
	public MEXMECtaPac getCtaPac() {

		if (getEXME_CtaPac_ID() == 0)
			return null;

		if (m_ctaPac == null || m_ctaPac.getEXME_CtaPac_ID() == 0)
			m_ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());

		return m_ctaPac;

	}

	public void setCtaPac(MEXMECtaPac ctaPac) {

		setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
		this.m_ctaPac = ctaPac;
	}

	
	/**
	 * Obtenemos el socio de Negocios.
	 * 
	 * @return
	 */
	public MEXMEBPartner getBpartner() {

		if (m_bPartner == null || m_bPartner.getC_BPartner_ID() == 0) {
			m_bPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(),
					get_TrxName());
		}

		return m_bPartner;

	}

	/**
	 * Obtenemos el socio de Negocios.
	 * 
	 * @return
	 */
	public MEXMEBPartner getBpartner(final boolean reload) {

		if (m_bPartner == null || reload ) {
			m_bPartner = new MEXMEBPartner(getCtx(), getC_BPartner_ID(),
					get_TrxName());
		}

		return m_bPartner;

	}
	
	/**
	 * Obtenemos el socio de Negocios.
	 * 
	 * @return
	 */
	public void setBpartner(MEXMEBPartner bPartner) {

		if (getC_BPartner_ID() != bPartner.getC_BPartner_ID()
				|| getBpartner().getC_BPartner_ID() != bPartner
				.getC_BPartner_ID()) {
			m_bPartner = bPartner;
			setC_BPartner_ID(bPartner.getC_BPartner_ID());
		}

	}

	/**
	 * Indica si la extension se esta facturando a una Aseguradora o no.
	 * 
	 * @return true si se esta facturando a un Aseguradora, false si es a un
	 *         Particular.
	 */
	public boolean facturaAseguradora() {
		return getBpartner().isAseguradora();
	}

	/**
	 * Obtenemos la factura relacionada. Si no existe una factura relacionada,
	 * obtendremos un template.
	 * 
	 * @return
	 */
	public MInvoice getInvoice() {

		if (m_invoice == null) {
			m_invoice = new MInvoice(getCtx(), getC_Invoice_ID(),
					get_TrxName());
		}

		return m_invoice;
	}

	/**
	 * Obtenemos la factura relacionada. Si no existe una factura relacionada,
	 * obtendremos un template.
	 * 
	 * @return
	 */
	public MInvoice getInvoice(boolean reload) {

		if (m_invoice == null || reload) {
			m_invoice = new MInvoice(getCtx(), getC_Invoice_ID(),
					get_TrxName());
		}

		return m_invoice;
	}

	/**
	 * Lineas de cargos de la extencion
	 */

	
	/**
	 * Lineas de cargos de la extension
	 * 
	 * @return
	 */
	public MCtaPacDet[] getLineasDeExtension() {
		return lineasDeExtension;
	}

	public void setLineasDeExtension(MCtaPacDet[] lineasExtension) {
		lineasDeExtension = lineasExtension;
	}

	public void setLstCargos(List<MCtaPacDet> lstCargos) {
		this.lstCargos = lstCargos;
	}

	/**
	 * Lista de cargos
	 * (puede salir de una consulta)
	 * @return
	 */
	public List<MCtaPacDet> getLines() {
		return lstCargos;
	}

	/**
	 * Lineas de deducible (Aseguradora)
	 *
	 */

	public MCtaPacDet[] getLineaDeducible() {
		return lineaDeducible;
	}

	public void setLineaDeducible(MCtaPacDet[] lineaDeducible) {
		this.lineaDeducible = lineaDeducible;
	}
	/**
	 * Linea de cargo por concepto de coaseguro
	 */


	public MCtaPacDet[] getLineaCoaseguro() {
		return lineaCoaseguro;
	}

	public void setLineaCoaseguro(MCtaPacDet[] lineaCoaseguro) {
		this.lineaCoaseguro = lineaCoaseguro;
	}
	
	
	/**
	 * Linea de cargo por concepto de copago
	 */
	

	public MCtaPacDet[] getLineaCopago() {
		return lineaCopago;
	}

	public void setLineaCopago(MCtaPacDet[] lineaCopago) {
		this.lineaCopago = lineaCopago;
	}
	
	/**
	 * Linea de cargo por concepto de coaseguro medico
	 */

	public MCtaPacDet[] getLineaCoaseguroMed() {
		return lineaCoaseguroMed;
	}

	public void setLineaCoaseguroMed(MCtaPacDet[] lineaCoaseguroMed) {
		this.lineaCoaseguroMed = lineaCoaseguroMed;
	}


	/**
	 * Obtenemos el monto total de anticipos.
	 */
	public BigDecimal getAnticiposAmt() {
		
		/*if (getAnticipo().compareTo(Env.ZERO) > 0) {
			return getAnticipo();
		}*/

		MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), get_TrxName());

		if(0==getExtensionNo() && anticipo!=null){
			try {
				cargarLineasDeExtension(true, false, true);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			calcularTotales(false, null);
		}
		
		if (anticipo != null) {
			// Si el anticipo es mayor a el grantotal, devuelve el grantotal como anticipo
			if (anticipo.getTotal().compareTo(getGrandTotal()) > 0) {
				return getGrandTotal();
			} else {
				return anticipo.getTotal();
			}

		} else {
			return Env.ZERO;
		}
	}
	
	/**
	 * Obtenemos el monto total de anticipos.
	 */
	public BigDecimal getAnticiposAmtTotal() {
		final MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), get_TrxName());
		if (anticipo == null) {
			return Env.ZERO;
		} else {
			return anticipo.getTotal();
		}
	}
	
	
//	/**
//	 * Linea ID del coaseguro
//	 */
//	
//
//	public int getLineaCoa() {
//		return lineaCoa;
//	}
//
//	public void setLineaCoa(int lineaCoa) {
//		this.lineaCoa = lineaCoa;
//	}
	
//	/**
//	 * Linea ID del deducible
//	 */
	

//	public int getLineaDed() {
//		return lineaDed;
//	}
//
//	public void setLineaDed(int lineaDed) {
//		this.lineaDed = lineaDed;
//	}


//	public int getLineaCoaMed() {
//		return lineaCoaMed;
//	}
//
//	public void setLineaCoaMed(int lineaCoaMed) {
//		this.lineaCoaMed = lineaCoaMed;
//	}
//	

//	public int getLineaCop() {
//		return lineaCop;
//	}
//
//	public void setLineaCop(int lineaCop) {
//		this.lineaCop = lineaCop;
//	}

	/**
	 * Obtenemos la configuracion de precios actual.
	 * 
	 * @return
	 */
	
	public MConfigPre getConfigPre() {

		if (m_configPre == null)
			m_configPre = MConfigPre.get(getCtx(), get_TrxName());

		return m_configPre;
	}

	/**
	 * Indica si es una extension cero.
	 * 
	 * @return
	 */
	
	public boolean getIsExtCero() {
		return getExtensionNo() == 0;
	}

	/**
	 * Obtenemos la extension cero de un determinada cuenta paciente.
	 * 
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	public static MEXMECtaPacExt getExtCero(Properties ctx, int EXME_CtaPac_ID,
			String trxName) {
		MEXMECtaPac mCtaPac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, trxName);
		return new MEXMECtaPacExt(ctx, mCtaPac.getEXME_CtaPacExt_ID(), trxName);
	}

	/**
	 * Establecemos el Numero de Extension.
	 */
	private void setExtensionNo() {

		int setValue = 0;

		if (getCtaPac() == null)
			setExtensionNo(0);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT MAX(ExtensionNo) FROM EXME_CtaPacExt WHERE EXME_CtaPac_ID = ")
		.append(getEXME_CtaPac_ID())
		.append(" AND ExtensionNo != 99 ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "EXME_CtaPacExt"));
		
	  	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				setValue = rs.getInt(1) + 1;
				if(setValue==99)
					setValue=100;
			}
			
			// Establecemos el Numero de Extension.
			setExtensionNo(setValue);
		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
	}


	/**
	 * OBTENGO TODAS LAS EXTENCIONES DE LA CTAPACIENTE
	 * 
	 * @param refresh
	 *            Indica si de vuelven a determinar las linneas a facturar.
	 * @return
	 */
	public static MEXMECtaPacExt[] get(Properties ctx, int EXME_CtaPac_ID,
			String whereClause, String trxName) {

		List<MEXMECtaPacExt> lista = new ArrayList<MEXMECtaPacExt>();

		String sql = "SELECT * FROM EXME_CtaPacExt WHERE IsActive = 'Y' AND EXME_CtaPac_ID = ? ";

		if (whereClause != null)
			sql += whereClause;

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_CtaPacExt");

		sql += " ORDER BY ExtensionNo ASC";

	  	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt retValue = new MEXMECtaPacExt(ctx, rs, trxName);
				lista.add(retValue);
			}
		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}


		MEXMECtaPacExt[] list = new MEXMECtaPacExt[lista.size()];
		lista.toArray(list);
		return list;

	}
	
	/***
	 * Obtiene Lista de Extensiones de Cuentas Paciente 
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author Rosy Velazquez 
	 */
	public static List<MEXMECtaPacExt> getExtZeroCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPacExt> cuentasExt = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ext.* FROM EXME_CtaPacExt ext INNER JOIN EXME_CtaPac cta ON cta.EXME_CtaPac_ID = ext.EXME_CtaPac_ID ")
		   .append("WHERE cta.IsActive = 'Y' AND ext.IsActive = 'Y' AND ext.ExtensionNo = 0 ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "ext"));
		sql.append(" ORDER BY ext.DATEORDERED desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs, trxName);
				cuentasExt.add(ctaPacExt);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return cuentasExt; 
	}
	
	
	/**
	 * Obtengo un arreglo de MCtaPacDet donde correspondan a la extencion que
	 * se requiere
	 * 
	 * @param cadena
	 * @return MCtaPacDet[]
	 * @throws SQLException 
	 **/	
	public MCtaPacDet[] getCargosDeLaExtension(final String cadena) 
			throws SQLException {

		final List<MCtaPacDet> list = getLstCargos(cadena);
		final MCtaPacDet[] plan = new MCtaPacDet[list.size()];
		list.toArray(plan);
		return plan;
	}

	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static List<EncounterTransVO> getTransactions(Properties ctx, int ctaPacID,  int ctaPacExtID, String trxName) {
		List<EncounterTransVO> lstDiv = new ArrayList<EncounterTransVO>();
		
		// Charges
		List<MCtaPacDet> lstCtaPacDet = MCtaPacDet.getCargosByExt(ctx, ctaPacExtID, trxName);
		for (MCtaPacDet ctapacdet : lstCtaPacDet) {
			lstDiv.add(new EncounterTransVO(ctx, ctapacdet));
		}
		List<MPayment> lstPayments = MPayment.getPaymentsByExtUSA(ctaPacID, ctaPacExtID, ctx);
		for (MPayment payment : lstPayments) {
				lstDiv.add(new EncounterTransVO(ctx, payment));
		}
		
		return lstDiv;
	}
	
		
	/**
	 * Obtengo un arreglo de MCtaPacDet donde correspondan a la extencion que
	 * se requiere
	 * 
	 * @param cadena
	 * @return MCtaPacDet[]
	 * @throws SQLException 
	 **/	
	public List<MCtaPacDet> getLstCargos(final String cadena) 
	throws SQLException {

		final List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * ")
		.append(" FROM  EXME_CtaPacDet ")
		.append(" WHERE EXME_CtaPacDet.IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ","EXME_CtaPacDet"))
		.append(" AND   EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
		.append(cadena != null ? cadena : " ");

		if(sql!=null)
			s_log.log(Level.INFO, "MCtaPacExt.getCtaPacExt>>"+sql.toString()+"ID: "+getEXME_CtaPacExt_ID());

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPacExt_ID());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCtaPacDet(getCtx(), rs, get_TrxName()));
			}

		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}

		return list;

	}


	/**
	 * Devuelve una lista de los id-documento de las extensiones relacionadas a
	 * la cuenta paciente
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param ctaPac
	 *            La cuenta paciente
	 * 
	 * @return Un valor ArrayList con los datos
	 * @throws Exception
	 *             en caso de ocurrir un error al hacer la consulta.
	 */
	public static ArrayList<LabelValueBean> getExtNoFacturasDeCuenta(Properties ctx, long ctaPac)
	throws Exception {
		
		ArrayList<LabelValueBean> lstExtensiones = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			sql.append(" SELECT EXME_CtaPacExt_ID, ExtensionNo ")
			.append("FROM EXME_CtaPacExt ")
			.append("WHERE IsActive = 'Y' ")
			.append(" AND EXME_CtaPac_ID = ? AND C_Invoice_ID IS NULL ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacExt"))
			.append(" ORDER BY ExtensionNo ");
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, ctaPac);

			if (WebEnv.DEBUG){
				s_log.log(Level.SEVERE,sql + "  ctaPac: " + ctaPac);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs
						.getString("ExtensionNo"), String.valueOf(rs
								.getLong("EXME_CtaPacExt_ID")));

				lstExtensiones.add(lvb);
			}

		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return lstExtensiones;
	}


	/**
	 * METODOS DE ESTADO DE CUENTA
	 */


	/**
	 * Devuelve una lista de los id-documento de las extensiones relacionadas a
	 * la cuenta paciente
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param ctaPac
	 *            La cuenta paciente
	 * 
	 * @return Un valor ArrayList con los datos
	 * @throws Exception
	 *             en caso de ocurrir un error al hacer la consulta.
	 */
	public static ArrayList<LabelValueBean> getLVBExtension(final Properties ctx, final int ctaPac, final boolean aseguradora, final String trxName)
	throws Exception {
		
		final ArrayList<LabelValueBean> lstExtensiones = new ArrayList<LabelValueBean>();
		final StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String slFacturada = null;
		
		slFacturada = Utilerias.getMsg(ctx, "factExtension.title.facturada");
		
		try {
			//Deben considerar las facturas globales ?
			sql.append(" SELECT ext.EXME_CtaPacExt_ID ")
			.append("        ,  ext.ExtensionNo || ' ' ||  ( ")
			
			
			.append("           CASE WHEN ext.C_Invoice_ID > 0 THEN")// tiene remision
			.append("                CASE WHEN      c_invoice_customer_v.C_Invoice_ID > 0 THEN '")
			.append(slFacturada)
			.append("'") // remision facturada
			.append("                ELSE CASE WHEN ci.Multiple_ID > 0  THEN '")
			.append(slFacturada)
			.append("'")// factura multiple
			.append("                     ELSE '")
			.append(Utilerias.getMsg(ctx, "factExtension.title.procesada"))
			.append("' ")
			.append("                     END ")
			.append("                END ")
			.append("           ELSE ' ' ")
			.append("           END ")
			.append(" )  AS ExtName  ")
			
			
			.append(" FROM EXME_CtaPacExt ext")  
			.append(" LEFT JOIN C_Invoice            ci ON ci.C_Invoice_ID         = ext.C_Invoice_ID ")// Factura Global
			.append(" LEFT JOIN ").append(MInvoice.getCInvoiceCustomerV(ctx, true)).append(" ON c_invoice_customer_v.Ref_Invoice_Sales_ID = ci.C_Invoice_ID ")// Factura Normal
			.append(" WHERE  ext.IsActive = 'Y'  ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacExt", "ext"))
			.append(" AND    ext.EXME_CtaPac_ID = ? ");   
			
			
			if(aseguradora){
				sql.append(" AND ext.ExtensionNo IN (0,99) ");
			}

			sql.append(" ORDER BY ext.ExtensionNo");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPac);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean lvb = new LabelValueBean(rs.getString("ExtName"), String.valueOf(rs.getLong("EXME_CtaPacExt_ID")));
				lstExtensiones.add(lvb);
			}

		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString()+"  Param[ctapac]: " + ctaPac, e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
    	return lstExtensiones;
	}
	
	/**
	 * Obtener una extension de la cuenta paciente
	 * segun numero de la extension
	 * 
	 * @param ctaPac
	 * @param noExt
	 * @return
	 * @throws SQLException 
	 */
	public static MEXMECtaPacExt getExtNoPac(Properties ctx, int EXME_Paciente_ID, 
			int noExt, String trxName)  {

		MEXMECtaPacExt retValue = null;
		//Correccion indices ttpr
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT EXME_CtaPacExt.* ") 
		.append(" FROM EXME_CtaPacExt ") 
		.append(" INNER JOIN EXME_CtaPac  ON (EXME_CtaPac.EXME_CtaPac_id = EXME_CtaPacExt.EXME_CtaPac_ID ") 
		.append(" AND EXME_CtaPac.IsActive = 'Y' ") 
		//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
		.append(" AND EXME_CtaPac.EncounterStatus = '").append(MEXMECtaPac.ENCOUNTERSTATUS_Admission).append("' ")
		.append(" ) ") 
		.append(" WHERE EXME_CtaPacExt.IsActive = 'Y' ")
		.append(new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_CtaPacExt")))   
		.append(" AND EXME_CtaPac.EXME_Paciente_ID = ? ")
		.append(" AND EXME_CtaPacExt.ExtensionNo = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, noExt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMECtaPacExt(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs,pstmt);
		}

		return retValue;

	}

	/**
	 * METODOS DE REAPERTURA DE EXTENSION
	 */

	/**
	 * Reapertura la extension actual para que pueda volver a facturarse
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param EXME_CtaPacExt_ID
	 *            El identificador de la extension a reaperturar
	 * @param trxName
	 *            El nombre de la transacci&oacute;n
	 * @throws Exception
	 *             En caso de que no pueda reaperturar la extension
	 */
	public static void reaperturaExtension(Properties ctx,
			int EXME_CtaPacExt_ID, final MInvoice invoice, String trxName) throws Exception {
		//TODO: TWRY Se deben considerar las facturas globales ?
		// Conciderar si es una factura con nota de remision
		// invoice.getRef_Invoice_Sales_ID()==0 ó es Factura viejita R13-6 o es una remision
		boolean moverExtension = invoice.getRef_Invoice_Sales_ID()==0;// true si es nota de remision no facturada

		// Si tiene nota de remision no mover la extension
		// Si no tiene nota de remision reactivar la extension
		if(moverExtension){
			// Extension
			final MEXMECtaPacExt ext = new MEXMECtaPacExt(ctx, EXME_CtaPacExt_ID, trxName);
			if (!ext.reaperturaExtension(ctx)) {
				throw new Exception("error.caja.ctaPacExt.noSave");
			}//Limpiar el descuento siempre y cuando no tenga coaseguro ni deducible
		}
	}

	/**
	 * Reapertura la extension actual para que pueda volver a facturarse
	 * 
	 * @param ctx
	 *            El contexto de la aplicacion
	 * @param EXME_CtaPacExt_ID
	 *            El identificador de la extension a reaperturar
	 * @param trxName
	 *            El nombre de la transacci&oacute;n
	 * @throws Exception
	 *             En caso de que no pueda reaperturar la extension
	 */
	private boolean reaperturaExtension(final Properties ctx)
	throws Exception {
		setC_Invoice_ID(0);
		m_invoice = null;
		setDocStatus(DocAction.STATUS_Drafted);
		setDocAction(DocAction.ACTION_Complete);
	
		MCtaPacDet[] eLines = getCargosDeLaExtension(MEXMECtaPacExt.condicionCargos ());
		if (eLines != null) {
			for (int i = 0; i < eLines.length; i++) {
				MCtaPacDet eLine = eLines[i];
				if (eLine != null) {
					eLine.setIsFacturado(false);
					if (!eLine.save(get_TrxName())) {
						m_processMsg = "No fue posible cancelar la prefactura. ";
						return false;
					}

				}
				// Si el cargo es por concepto de Paquete, asociamos el nuevo
				// cargo
			}
		}
		
		if (!save(get_TrxName())) {
			return false;
		}
		return true;
	}

	/**
	 * Retornamos la direccion donde vive el paciente.
	 * @return
	 */
	public MLocation getLocation() {
		if (m_location == null || m_location.getC_Location_ID() == 0) {
			m_location = new MLocation(getCtx(), getC_Location_ID(), get_TrxName());
		}
		return m_location;
	}

	/**
	 * Mensajes
	 * 
	 * @return
	 */
	public String getM_processMsg() {
		return m_processMsg;
	}

	public void setM_processMsg(String msg) {
		m_processMsg = msg;
	}

	/**
	 * Sub total de impuestos despues de descuentos
	 * @return
	 */
//	private BigDecimal subTotalImp = Env.ZERO;
//	
//	public BigDecimal getSubTotalImp() {
//		return subTotalImp.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}
	
	/**
	 * Saber si el descuento global de la extencion sera en porcentaje o en
	 * monto true = cuando es un porcentaje false = cuando es una cantidad fija
	 */
//	private boolean isDescPorcent = true;
//
//	public boolean getIsDescPorcent() {
//		return isDescPorcent;
//	}
//
//	public void setIsDescPercent(boolean isDescPorcent) {		
//			this.isDescPorcent = isDescPorcent;
//	}
	/**
	 * TOTAL LINEAS sin considerar COAS Y DEDU Total de lineas - coaseguro y
	 * deducible
	 * 
	 * @return
	 */
	private BigDecimal totalBruto = Env.ZERO;
	
	public BigDecimal getTotalBruto() {
		return totalBruto.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTotalBruto(BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
	}
//
//	public void setDiscountAmt(BigDecimal discountAmt) {
//		this.discountAmt = discountAmt;
//	}

//	public void setSubTotalImp(BigDecimal subTotalImp) {
//		this.subTotalImp = subTotalImp;
//	}
	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
//
//	public void setDeducibleAmt(BigDecimal deducibleAmt) {
//		this.deducibleAmt = deducibleAmt;
//	}
//
//	public void setCoaseguroAmt(BigDecimal coaseguroAmt) {
//		this.coaseguroAmt = coaseguroAmt;
//	}
//
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}


	/**
	 * total de descuentos Descuentos de Familia mas el descuento Global
	 * 
	 * @return
	 */
//	private BigDecimal discountAmt = Env.ZERO;
//	
//	public BigDecimal getDiscountAmt() {
//		return discountAmt.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}
//
	/**
	 * TOTAL bruto - DESC Obtenemos el total de las lineas antes de impuestos, y
	 * con desc. global y/o por Fam. de productos y sin el coaseguro y
	 * dducible.
	 */
	private BigDecimal subTotal = Env.ZERO;
	
	public BigDecimal getSubTotal() {
		return subTotal.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
	}
//
//	/**
//	 * Total de Deducible sin impuesto
//	 */
//	private BigDecimal deducibleAmt = Env.ZERO;
//	
//	public BigDecimal getDeducibleAmt() {
//		return deducibleAmt.setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP);
//	}
//
//	/**
//	 * Total de coaseguro sin impuesto
//	 */
//	private BigDecimal coaseguroAmt = Env.ZERO;
//	
//	public BigDecimal getCoaseguroAmt() {
//		return coaseguroAmt.setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP);
//	}
//
	/**
	 * TOTAL LINEAS - DESC Total de lineas - descuentos
	 */
	private BigDecimal totalNeto = Env.ZERO;
	
	public BigDecimal getTotalNeto() {
		return totalNeto.setScale(REDONDEO2,BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Total de Impuestos
//	 */
//	private BigDecimal taxAmt = Env.ZERO;
//	
//	public BigDecimal getTaxAmt() {
//		return taxAmt.setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}
//
//	public void setTaxAmt(BigDecimal taxAmt) {
//		this.taxAmt = taxAmt;
//	}

//	/**
//	 * X_EXME_CtaPacExt.TotalLines
//	 */
//	public BigDecimal getTotalLines(){
//		return super.getTotalLines().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP); 
//	}
//	
//	public void setTotalLines(BigDecimal monto){
//		super.setTotalLines(monto.setScale(REDONDEO4, BigDecimal.ROUND_HALF_UP)); 
//	}
//
//	/**
//	 * X_EXME_CtaPacExt.ChargeAmt
//	 */
//	public BigDecimal getChargeAmt(){
//		return super.getChargeAmt().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP); 
//	}
//	
//	public void setChargeAmt(BigDecimal monto){
//		super.setChargeAmt(monto.setScale(REDONDEO4, BigDecimal.ROUND_HALF_UP)); 
//	}
//	
//	/**
//	 * X_EXME_CtaPacExt.GranTotal
//	 * Utilizarlo solo para presentar al usuario
//	 */
//	public BigDecimal getGrandTotal() {
//		return super.getGrandTotal().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}
//	
//	/**
//	 * Redondea a 4 decimales
//	 */
//	public void setGrandTotal(BigDecimal monto){
//		super.setGrandTotal(monto.setScale(REDONDEO4, BigDecimal.ROUND_HALF_UP)); 
//	}	
//	
//	/**
//	 * @return Regresa el valor tal cual fue almacenado
//	 * a 4 decimas
//	 */
//	public BigDecimal getGrandTotalBD() {
//		return super.getGrandTotal();
//	}
//	
	/** X_EXME_CtaPacExt Set Global Discount.
	@param DesctoGlobal Global Discount */
//	public void setDesctoGlobal (BigDecimal DesctoGlobal)
//	{
//		super.setDesctoGlobal(DesctoGlobal.setScale(REDONDEO4, BigDecimal.ROUND_HALF_UP));
//	}
//	
//	/**
//	 * Se deberá utilizar solo para mostrar al usuario
//	 * ya que esta redondeado a 2 decimales
//	 */
//	public BigDecimal getDesctoGlobal() 
//	{
//		return super.getDesctoGlobal().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}

	/**
	 * Utilizarlo cuando es necesario hacer algun calculo
	 * @return Regresa el valor redondeado a 4 decimas
	 */
//	public BigDecimal getDesctoGlobalBD() 
//	{
//		return super.getDesctoGlobal();
//	}
//	
	/** X_EXME_CtaPacExt Set Global Disct $.
	@param DesctoGlobalAmt Amount of Global Discount */
//	public void setDesctoGlobalAmt (BigDecimal DesctoGlobalAmt)
//	{
//		super.setDesctoGlobalAmt(DesctoGlobalAmt.setScale(REDONDEO4, BigDecimal.ROUND_HALF_UP));
//	}
//
//	/**
//	 * Utilizar solo cuando se muestre al usuario
//	 */
//	public BigDecimal getDesctoGlobalAmt() 
//	{
//		return super.getDesctoGlobalAmt().setScale(REDONDEO2, BigDecimal.ROUND_HALF_UP);
//	}

	/**
	 * Utilizar para hacer calculos
	 * @return Regresa el valor redondeado a 4 decimas
	 */
//	public BigDecimal getDesctoGlobalAmtBD() 
//	{
//		return super.getDesctoGlobalAmt();
//	}

	/**
	 * true si la extension esta facturada
	 * @return
	 */
	public boolean isInvoiced() {
		return getC_Invoice_ID()>0;
	}

//	/**
//	 * Regresa el objeto del anticipo
//	 * @return
//	 */
//	public BigDecimal getAnticipos() {
//		MEXMEAnticipo mAnticipo = MEXMEAnticipo.getAnticipo(
//				getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), null);
//		if(mAnticipo!=null){
//			return mAnticipo.getSaldo();
//		} else {
//			return Env.ZERO;
//		}
//	}

	/**
	 * Actualiza los datos del socio de negocios
	 * que se le esta relacionando a la extension
	 * @param socio
	 */
	public void setNewSocio(int socio) {
		setRfc(getBpartner(true).getTaxID());
		setDescription(getBpartner().getName());
		
		MBPartnerLocation bpLocartion = MEXMEBPartner.getLocations(getCtx(), getC_BPartner_ID(), null);
		MLocation location = null;
		if(bpLocartion!=null){
			location = bpLocartion.getLocation(true);
		}
		getLocation().clean();
		if(location!=null){
			copyValueExt(location, getLocation());
		}
	}

	/**
	 * Actualiza los datos del socio de negocios
	 * que se le esta relacionando a la extension
	 * @param socio
	 */
	public void setNewSocioPac(int socio) {
		// Direccion
		final BeanDatosFacturacion datosFactura = getCtaPac().getDireccionParticular();

		if(datosFactura!=null && datosFactura.getC_BPartner_ID()>0){
			setRfc(datosFactura.getRfc());//Paciente
			setDescription(datosFactura.getName());
			setC_BPartner_ID(datosFactura.getC_BPartner_ID());
			getLocation().clean();


			MLocation location = datosFactura;
			if (location != null) {
				copyValueExt(location, getLocation());
			}
		}
//		// Paciente es el Responsable
//		if(paciente.iscopyResponsible()){
//			MLocation location = new MLocation(getCtx(), paciente.getC_Location_ID(), null);
//			if (location != null) {
//				copyValueExt(location, getLocation());
//			}
//			
//		} else {
//			// Obtiene los datos de la persona responsable
//			if(!personaResponsable(paciente)){
//				//Si no encontro datos traer los datos del paciente
//				MLocation location = new MLocation(getCtx(), paciente.getC_Location_ID(), null);
//				if (location != null) {
//					copyValueExt(location, getLocation());
//				}
//			}
//		}
	}
	
//	/**
//	 * Datos de la persona responsable de la persona responsable
//	 * @param paciente
//	 * @return
//	 */
//	private boolean personaResponsable(final MEXMEPaciente paciente){
//		StringBuilder whereClause = new StringBuilder()
//		.append(" AND Type2 = ")
//		.append(DB.TO_STRING(X_EXME_PacienteRel.TYPE2_Responsible));
//		List<X_EXME_PacienteRel> lst = 
//			MEXMEPacienteRel.getFromPatient(
//				getCtx(), 
//				null, 
//				whereClause, new Object[] {paciente.getEXME_Paciente_ID()}
//			);
//		
//		if( lst!=null &&  !lst.isEmpty()){
//			StringBuilder name = new StringBuilder();
//			name.append(lst.get(0).getName()).append(" ");
//			name.append(lst.get(0).getLAST_NAME());
//			
//			setRfc(lst.get(0).getRfc());
//			setDescription(name.toString());
//			
//			MLocation location = new MLocation(getCtx(), lst.get(0).getC_Location_ID(), null);
//
//			if(location!=null){
//				copyValueExt(location, getLocation());
//			}
//			
//			return true;
//		}
//		
//		return false;
//	}
	/**
	 * Copiar Valores de un objeto MLocation para la extension
	 * @param from
	 * @param to
	 */
	public void copyValueExt(MLocation from, MLocation to){
		to.setAddress1(from.getAddress1());
		to.setAddress2(from.getAddress2());
		to.setAddress3(from.getAddress3());
		to.setAddress4(from.getAddress4());
		to.setNumExt(from.getNumExt());
		to.setNumIn(from.getNumIn());
		to.setC_Country_ID(from.getC_Country_ID());
		to.setCity(from.getCity());
		to.setC_City_ID(from.getC_City_ID());
		to.setPostal(from.getPostal());
		to.setC_Region_ID(from.getC_Region_ID());
		to.setRegionName(from.getRegionName());
		to.setEXME_TownCouncil_ID(from.getEXME_TownCouncil_ID());
	}
	
//	/**
//	 * Elimina una linea de la extension
//	 * @param tipolinea
//	 * @throws SQLException 
//	 */
//	public void delLine(String tipolinea) throws SQLException {
//		List<Integer> idsDel = new ArrayList<Integer>();
//		
//		// Primero se borra la de la aseguradora
//		final MCtaPacDet[] lineas = getCargosDeLaExtension(" AND EXME_CtaPacDet.QtyDelivered > 0  ");
//		for (int i = 0; i < lineas.length; i++) {
//			if(lineas[i].getTipoLinea().equals(tipolinea)){
//				
//				idsDel.add(lineas[i].getRef_CtaPacDet_ID());
//				if(!lineas[i].delete(true, get_TrxName())){
//					s_log.log(Level.SEVERE, "lineas[i].delete(true, get_TrxName()");
//				}
//			}
//		}
//		
//		// Despues se elimina la parte del particular, ya que estan referenciadas
//		for (int j = 0; j < idsDel.size(); j++) {
//			MCtaPacDet part = new MCtaPacDet(getCtx(), idsDel.get(j),  get_TrxName());
//			if(!part.delete(true, get_TrxName())){
//				s_log.log(Level.SEVERE, "lineas[i].delete(true, get_TrxName()");
//			}
//		}
//	}
	
//	/**
//	 * Identifica si la contraparte ya ha sido facturada
//	 * @param tipolinea
//	 * @return
//	 * @throws SQLException
//	 */
//	public boolean existeFacturaContraparte() {
//		boolean existeFactura = false;
//		try {
//			// guarda los ids de los cargos de la aseguradora
//			List<Integer> idsDel = new ArrayList<Integer>();
//			MCtaPacDet[] lineas;
//
//			lineas = getCargosDeLaExtension(" AND EXME_CtaPacDet.QtyDelivered > 0  ");
//
//
//			for (int i = 0; i < lineas.length; i++) {
//				if(lineas[i].isCCCmD()){
//					idsDel.add(lineas[i].getRef_CtaPacDet_ID());
//				}
//			}
//
//			for (int j = 0; j < idsDel.size(); j++) {
//				MCtaPacDet part = new MCtaPacDet(getCtx(), idsDel.get(j),  get_TrxName());
//				if(part.isFacturado()){
//					existeFactura = true;
//					break;
//				}
//			}
//
//		} catch (SQLException e) {
//			s_log.log(Level.SEVERE,"No fue posible crear la linea de descuento que ve el usuario : ", e);
//		}
//		return existeFactura;
//	}
	
//	/**
//	 * Creacion del descuento global
//	 */
//	public boolean calcularDescuento(final BigDecimal montoReal, boolean isPorcentaje, final BigDecimal grandTotal) throws Exception {
//
////		setDesctoGlobal(montoReal);
////		setDesctoGlobalAmt(montoReal);// Monto
////		setIsDiscPercent(isPorcentaje);
////		
////		if (isPorcentaje) {
////			setDesctoGlobalAmt(getTotalLines().multiply(monto.divide(Env.ONEHUNDRED)));
////		}
////		return montoReal.compareTo(getTotalLines()) <= 0;
//		
//		setDesctoGlobal(montoReal);
//		setDesctoGlobalAmt(montoReal);// Monto
//		setIsDiscPercent(isPorcentaje);
//		
//		BigDecimal descuentoAmt = montoReal;
//		if (isPorcentaje) {
//			descuentoAmt = grandTotal.multiply(montoReal.divide(Env.ONEHUNDRED));
//			setDesctoGlobalAmt(descuentoAmt);// Cuando es porcentaje
//		}
//		return descuentoAmt.compareTo(grandTotal) <= 0;
//		
//	}
	
	/**
	 * Creacion del descuento global
	 */
//	public boolean calcularDescuentoReal(BigDecimal monto, BigDecimal montoReal, boolean isPorcentaje) throws Exception {
//
//		setDesctoGlobal(montoReal);
//		setDesctoGlobalAmt(montoReal);// Monto
//		setIsDiscPercent(isPorcentaje);
//		
//		if (isPorcentaje) {
//			setDesctoGlobalAmt(getGrandTotal().multiply(monto.divide(Env.ONEHUNDRED)));
//		}
//		return montoReal.compareTo(getGrandTotal()) <= 0;
//	}
//	
//	/**
//	 * Creacion de coaseguro y/o deducible para la extension
//	 * 
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 * 
//	 */
//	public void calcularCoaseguro(BigDecimal coasAmt, BigDecimal coasAmtBig, boolean isPorcentaje, boolean incluirDeduc) {
//
//		// Coaseguro
//		//if (coasAmt > 0.00) {
//			
//			setCoaseguro(coasAmtBig);
//			setIsCoasegPercent(isPorcentaje);
//			setCoaseguroAmt(coasAmtBig.negate());
//			
//			if (isPorcentaje && coasAmt.compareTo(Env.ZERO) > 0) {
//				// Cuando es por porcentaje el coaseguro se calcula con lo
//				// obtenido de la resta del deducible
//				BigDecimal divisor = incluirDeduc ?getTotalLines().subtract(getDesctoGlobalAmt()).add(getTaxAmt()).add(getDeducibleAmt()):getTotalLines().subtract(getDesctoGlobalAmt()).add(getTaxAmt());
//
//				// Calculo
//						BigDecimal montoRealCoaseguro = divisor.multiply(coasAmt.divide(Env.ONEHUNDRED));
//				setCoaseguroAmt(montoRealCoaseguro.negate());
//			}
//		//}
//		
//	}
	
//	/**
//	 * Creacion de coaseguro medico para la extension
//	 * 
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 * 
//	 */
//	public void calcularCoaseguroMed(BigDecimal coasMedAmt, BigDecimal coasMedAmtBig, boolean isPorcentaje) {
//
//		setCoaseguroMed(coasMedAmtBig);
//		setIsCoasMedPercent(isPorcentaje);
//		setCoaseguroMedAmt(coasMedAmtBig.negate());
//
//		if (isPorcentaje && coasMedAmt.compareTo(Env.ZERO) > 0) {
//			
//			BigDecimal divisor = getTotalLines().subtract(getDesctoGlobalAmt()).add(getTaxAmt());
//
//			// Calculo
//			BigDecimal montoRealCoaseguroMed = divisor.multiply(coasMedAmt.divide(Env.ONEHUNDRED));
//			setCoaseguroMedAmt(montoRealCoaseguroMed.negate());
//		}
//		
//	}
	
//	/**
//	 * Creacion de copago para la extension
//	 * 
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 * 
//	 */
//	public void calcularCopago(BigDecimal copagoAmt, BigDecimal copagoAmtBig, boolean isPorcentaje) {
//
//		setCopago(copagoAmtBig);
//		setIsCopPercent(isPorcentaje);
//		setCopagoAmt(copagoAmtBig.negate());
//
//		if (isPorcentaje && copagoAmt.compareTo(Env.ZERO) > 0) {
//			// Cuando es por porcentaje calcular el copago
//			BigDecimal divisor = getTotalLines().subtract(getDesctoGlobalAmt()).add(getTaxAmt());
//
//			// Calculo
//			BigDecimal montoRealCopago = divisor.multiply(copagoAmt.divide(Env.ONEHUNDRED));
//			setCopagoAmt(montoRealCopago.negate());
//		}
//
//	}
	
//	/**
//	 * Actualiza la base de datos 
//	 * @return
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 */
//	public MCtaPacDet[] createChargeCoas() {
//		MCtaPacDet chargeAseg = null;
//		MCtaPacDet chargePart = null;
//		try {
//			
//			// Borra las lienas anteriores de coaseguro
//			delLine(X_EXME_CtaPacDet.TIPOLINEA_Coaseguro);
//
//			//
//			if (getCoaseguroAmt().compareTo(BigDecimal.ZERO) != 0) {
//				// Linea Negativa en Aseguradora (Actual)
//				// Linea Positiva en Extension Cero (Para ser movida a extension de
//				// Particular)
//				// (INSERT) crea la linea de coaseguro de la aseguradora (ext actual)
//				chargeAseg = creaLineaCoaseguro(true,getCoaseguroAmt());
//
//				// (INSERT) crea la linea de coaseguro del particular (ext cero) 
//				chargePart = new MEXMECtaPacExt(Env.getCtx(), getCtaPac().getEXME_CtaPacExt_ID(), get_TrxName())
//				.creaLineaCoaseguro(false, getCoaseguroAmt().abs());
//				
//				// Se guarda la referencia
//				chargeAseg.setRef_CtaPacDet_ID(chargePart.getEXME_CtaPacDet_ID());
//			}
//
//		} catch (Exception e) {
//			return null;
//		}
//		return new MCtaPacDet[]{chargeAseg, chargePart};
//	}

	
//	/**
//	 * Actualiza la base de datos 
//	 * @return
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 */
//	public MCtaPacDet[] createChargeCopago() {
//		MCtaPacDet chargeAseg = null;
//		MCtaPacDet chargePart = null;
//		try {
//			
//			// Borra las lienas anteriores de copago
//			delLine(X_EXME_CtaPacDet.TIPOLINEA_Copago);
//
//			//
//			if (getCopagoAmt().compareTo(BigDecimal.ZERO) != 0) {
//				// Linea Negativa en Aseguradora (Actual)
//				// Linea Positiva en Extension Cero (Para ser movida a extension de Particular
//				// (INSERT) crea la linea de coaseguro de la aseguradora (ext actual)
//				chargeAseg = creaLineaCopago(true,getCopagoAmt());
//
//				// (INSERT) crea la linea de coaseguro del particular (ext cero) 
//				chargePart = new MEXMECtaPacExt(Env.getCtx(), getCtaPac().getEXME_CtaPacExt_ID(), get_TrxName())
//				.creaLineaCopago(false, getCopagoAmt().abs());
//				
//				// Se guarda la referencia
//				chargeAseg.setRef_CtaPacDet_ID(chargePart.getEXME_CtaPacDet_ID());
//			}
//
//		} catch (Exception e) {
//			return null;
//		}
//		return new MCtaPacDet[]{chargeAseg, chargePart};
//	}
	
//	/**
//	 * Actualiza la base de datos 
//	 * @return
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 */
//	public MCtaPacDet[] createChargeCoasegMed() {
//		MCtaPacDet chargeAseg = null;
//		MCtaPacDet chargePart = null;
//		try {
//			
//			// Borra las lienas anteriores de Coaseguro Medico
//			delLine(X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico);
//
//			//
//			if (getCoaseguroMedAmt().compareTo(BigDecimal.ZERO) != 0) {
//				// Linea Negativa en Aseguradora (Actual)
//				// Linea Positiva en Extension Cero (Para ser movida a extension de Particular
//				// (INSERT) crea la linea de coaseguro de la aseguradora (ext actual)
//				chargeAseg = creaLineaCoaseguroMed(true, getCoaseguroMedAmt());
//
//				// (INSERT) crea la linea de coaseguro del particular (ext cero) 
//				chargePart = new MEXMECtaPacExt(Env.getCtx(), getCtaPac().getEXME_CtaPacExt_ID(), get_TrxName())
//				.creaLineaCoaseguroMed(false, getCoaseguroMedAmt().abs());
//				
//				// Se guarda la referencia
//				chargeAseg.setRef_CtaPacDet_ID(chargePart.getEXME_CtaPacDet_ID());
//			}
//
//		} catch (Exception e) {
//			return null;
//		}
//		return new MCtaPacDet[]{chargeAseg, chargePart};
//	}

	
//	/**
//	 * Creacion de coaseguro y/o deducible para la extension
//	 * 
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 * 
//	 */
//	public void calcularDeducible(BigDecimal coasAmt, 
//			BigDecimal coasAmtBig, boolean isPorcentaje) {
//
//		// Coaseguro
//		//if (coasAmt > 0.00) {
//			
//			setDeducible(coasAmtBig);
//			setIsDeducPercent(isPorcentaje);
//			setDeducibleAmt(coasAmtBig.negate());
//			
//			if (isPorcentaje && coasAmt.compareTo(Env.ZERO) > 0) {
//				// Calculo
//				BigDecimal montoRealCoaseguro = getTotalLines().subtract(getDesctoGlobalAmt()).add(getTaxAmt()).multiply(coasAmt.divide(Env.ONEHUNDRED));
//				setDeducibleAmt(montoRealCoaseguro.negate());
//			}
//		//}
//	}
	
//	/**
//	 * Actualiza la base de datos 
//	 * @return
//	 * @throws InterruptedException
//	 * @throws SQLException
//	 */
//	public MCtaPacDet[] createChargeDeduc() {
//		MCtaPacDet chargeAseg = null;
//		MCtaPacDet chargePart = null;
//		try {
//			
//			// Borra las lienas anteriores de coaseguro
//			delLine(X_EXME_CtaPacDet.TIPOLINEA_Deducible);
//
//			//
//			if (getDeducibleAmt().compareTo(BigDecimal.ZERO) != 0) {
//				// Linea Negativa en Aseguradora (Actual)
//				// Linea Positiva en Extension Cero (Para ser movida a extension de
//				// Particular)
//				// (INSERT) crea la linea de coaseguro de la aseguradora (ext actual)
//				chargeAseg = creaLineaDeducible(true,getDeducibleAmt());
//
//				// (INSERT) crea la linea de coaseguro del particular (ext cero) 
//				chargePart = new MEXMECtaPacExt(Env.getCtx(), getCtaPac().getEXME_CtaPacExt_ID(), get_TrxName())
//				.creaLineaDeducible(false, getDeducibleAmt().abs()); 
//				
//				// Se guarda la referencia
//				chargeAseg.setRef_CtaPacDet_ID(chargePart.getEXME_CtaPacDet_ID());
//			}
//
//		} catch (Exception e) {
//			return null;
//		}
//		return new MCtaPacDet[]{chargeAseg, chargePart};
//	}
//	
//	/**
//	 * Actualizacion de impuesto
//	 * @param lines
//	 */
//	public void setTaxAmt(List<MCtaPacDet> lines){
//		BigDecimal taxAmt = Env.ZERO; 
//		for (int i = 0; i < lines.size(); i++) {
//			taxAmt = taxAmt.add(lines.get(i).getTaxAmt());
//		}
//		setTaxAmt(taxAmt);
//	}
	
//	/**
//	 * Actualizacion de totales
//	 */
//	public void updateTotales(){
//		BigDecimal sub = getTotalLines().subtract(getDesctoGlobalAmt());
//		BigDecimal other = getDeducibleAmt().add(getCoaseguroAmt()).add(getCoaseguroMedAmt()).add(getCopagoAmt());
//		setGrandTotal((sub.add(other)).add(getTaxAmt()));
//	}
	
	/**
	 * Ext. del particular facturada
	 * @return
	 */
	public boolean extParticularFacturada() {
		if(getDeducibleAmt().compareTo(Env.ZERO)!=0
			&& getCoaseguroAmt().compareTo(Env.ZERO)!=0) {
			
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
			.append(" select partext.exme_ctapacext_id ")
			.append(" from exme_ctapacdet aseg")
			.append(" inner join exme_ctapacdet part on aseg.exme_ctapacdet_id = part.ref_ctapacdet_id")
			.append("       inner join exme_ctapacext partext on part.exme_ctapacext_id = partext.exme_ctapacext_id")
			.append(" where aseg.exme_ctapacext_id = ? ")
			.append(" and partext.c_invoice_id is not null");
			
		  	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, getEXME_CtaPacExt_ID());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1)>0;
				}
			} catch (Exception e) {    		
	    		s_log.log(Level.SEVERE, sql.toString(), e);
	    	}finally {
	    		DB.close(rs,pstmt);
	    	}
		}
		return false;
	}
	
	/**
	 * Obtiene el listado del reporte de Zero Balance Summary By FC
	 * @return
	 * @throws Exception
	 */
	public static List<ZeroBalanceSummaryFCView> getZeroBalanceFCList() throws Exception{
		
		final List<ZeroBalanceSummaryFCView> array = new ArrayList<ZeroBalanceSummaryFCView>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String status = MEXMECtaPacExt.COLUMNNAME_InstitutionalStatus;// Para Manejar despues ambos lados
		String statusVal = MEXMECtaPacExt.INSTITUTIONALSTATUS_ZeroBalance;// Para Manejar despues ambos lados
		
		StringBuilder sql = new StringBuilder(" SELECT T.NAME, T.TOTAL_CHARGES, T.TOTAL_PAYMENT, (T.TOTAL_PAYMENT / T.TOTAL_CHARGES) AS PAYMENT_PERCENT, T.TOTAL_ADJUSTMENT ")
	       .append(", (T.TOTAL_ADJUSTMENT / T.TOTAL_CHARGES) AS ADJUSTMENT_PERCENT, T.BAD_ADJUSTMENT, (T.BAD_ADJUSTMENT / T.TOTAL_CHARGES) AS BAD_PERCENT ")
	       .append(", T.INSURANCE_PAYMENT, T.INSURANCE_ADJUSTMENT, T.PATIENT_PAYMENT, T.PATIENT_ADJUSTMENT ")
	       .append("FROM   ( ")
			        .append("WITH CHARGE_CTAPAC as ")
			        .append("			( SELECT (( SELECT COALESCE(SUM(DET.LINENETAMT),0) ")
			        .append("				          FROM EXME_CTAPACDET DET ")
			        .append("				          INNER JOIN EXME_PRODUCTOORG PO ")
			        .append("						ON PO.EXME_PRODUCTOORG_ID = FNC_GETPRODUCTORG(DET.M_PRODUCT_ID, DET.AD_ORG_ID) ")
			        .append("				          WHERE DET.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			        .append("				          AND FNC_ISPROFESSIONAL(PO.EXME_PRODUCTOORG_ID, DET.AD_ORG_ID) = ?) ")
			        .append("				 ) TOTAL_CHARGES , CP.EXME_CTAPAC_ID ")
			        .append("			  FROM   EXME_CTAPAC CP ")
			        .append("			  WHERE CP.AD_CLIENT_ID = ? ")
			        .append("			  AND CP.AD_ORG_ID = ? ")
			        .append("			  AND CP.ISACTIVE = 'Y' ")
			        .append("			  AND CP." +status+ " = ? ) ,  ")
			        .append(" PAYMENT_CTAPAC as ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_PAYMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN 0 ELSE P.PAYAMT END),0) AS PATIENT_PAYMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_PAYMENT , P.EXME_CTAPAC_ID")
			        .append("			  FROM  C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE IN (?, ?, ?, ?, ?, ?)")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) , ")
			        .append(" ADJUSTMENT_CTAPAC AS ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS PATIENT_ADJUSTMENT ,")
			        .append("			COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END), 0) AS BAD_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_ADJUSTMENT ,")
			        .append("				P.EXME_CTAPAC_ID")
			        .append("			  FROM   C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE  IN (?, ?, ?) ")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) ")
			
			        .append("SELECT FC.NAME ")
			               .append(", SUM(CC.TOTAL_CHARGES) AS TOTAL_CHARGES, SUM(PAYC.TOTAL_PAYMENT) AS TOTAL_PAYMENT ")
			               .append(", SUM(ADJC.TOTAL_ADJUSTMENT) AS TOTAL_ADJUSTMENT, SUM(ADJC.BAD_ADJUSTMENT) AS BAD_ADJUSTMENT ")
			               .append(", SUM(PAYC.INSURANCE_PAYMENT) AS INSURANCE_PAYMENT, SUM(ADJC.INSURANCE_ADJUSTMENT) AS INSURANCE_ADJUSTMENT ")
			               .append(", SUM(PAYC.PATIENT_PAYMENT) AS PATIENT_PAYMENT, SUM(ADJC.PATIENT_ADJUSTMENT) AS PATIENT_ADJUSTMENT ")
			
			        .append("FROM   EXME_FINANCIALCLASS FC ")
			        .append("INNER JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID ")
			        .append("LEFT JOIN ( ")
			        .append("		SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, CP.ENCOUNTERSTATUS, ")
			        .append(" 		CASE WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			        .append("		ELSE P.C_BPARTNER_ID END C_BPARTNER_ID ")
			        .append("		FROM   EXME_CTAPAC CP ")
			        .append("		INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			        .append("		LEFT JOIN EXME_PACIENTEASEG PA ON PA.ISACTIVE = 'Y' AND PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID  ")
			        .append("					AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			        .append("		WHERE CP.AD_CLIENT_ID = ? ")
			        .append("		AND CP.AD_ORG_ID = ? ")
			        .append("		AND CP.ISACTIVE = 'Y' ")
			        .append("		AND CP." +status+ " = ?")
			        .append(") C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			        .append("LEFT JOIN CHARGE_CTAPAC CC ON CC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN PAYMENT_CTAPAC PAYC ON PAYC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN ADJUSTMENT_CTAPAC ADJC ON ADJC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
					.append("GROUP BY FC.NAME ")
					.append(") T ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 1;
			pstmt.setString(i++, Constantes.REG_NOT_ACTIVE); //solo institutional ahorita
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			//SECCION PAYMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_Payment);
			pstmt.setString(i++, MCharge.TYPE_CopayPayment);
			pstmt.setString(i++, MCharge.TYPE_DeductiblePayment);
			pstmt.setString(i++, MCharge.TYPE_CoinsurancePayment);
			pstmt.setString(i++, MCharge.TYPE_OthersPayment);
			//SECCION ADJUSTMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);			
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			
			
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_FirstInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_SecondInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_ThirdInsurance); 
			pstmt.setString(i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			

			
			
			result = pstmt.executeQuery();
			while (result.next()){
				
				ZeroBalanceSummaryFCView view = new ZeroBalanceSummaryFCView();
				
				view.setPrimary_fc(result.getString("name"));
				view.setTotal_charges(result.getDouble("total_charges"));
				view.setTotal_payment(result.getDouble("total_payment"));
				view.setTotal_pmt_charges(result.getDouble("payment_percent"));
				view.setTotal_adjustment(result.getDouble("total_adjustment"));
				view.setTotal_adj_charges(result.getDouble("adjustment_percent"));
				view.setTotal_bd_adjustment(result.getDouble("bad_adjustment"));
				view.setTotal_db_charges(result.getDouble("bad_percent"));
				view.setTotal_insurance_payments(result.getDouble("insurance_payment"));
				view.setTotal_insurance_adjustments(result.getDouble("insurance_adjustment"));
				view.setTotal_patient_payment(result.getDouble("patient_payment"));
				view.setTotal_patient_adjustments(result.getDouble("patient_adjustment"));
						
				array.add(view);
				
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, null, e);
		}finally {
			DB.close(result, pstmt);
		}

		return array;
	}

	/**
	 * Obtiene el listado del reporte de Zero Balance Summary By PC
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static List<ZeroBalanceSummaryFCView> getZeroBalancePCList() throws Exception {

		final List<ZeroBalanceSummaryFCView> array = new ArrayList<ZeroBalanceSummaryFCView>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String status = MEXMECtaPac.COLUMNNAME_InstitutionalStatus;// Para Manejar despues ambos lados
		String statusVal = MEXMECtaPac.INSTITUTIONALSTATUS_ZeroBalance;// Para Manejar despues ambos lados
		
		StringBuilder sql = new StringBuilder(" SELECT T.NAME, T.TOTAL_CHARGES, T.TOTAL_PAYMENT, (T.TOTAL_PAYMENT / T.TOTAL_CHARGES) AS PAYMENT_PERCENT, T.TOTAL_ADJUSTMENT ")
	       .append(", (T.TOTAL_ADJUSTMENT / T.TOTAL_CHARGES) AS ADJUSTMENT_PERCENT, T.BAD_ADJUSTMENT, (T.BAD_ADJUSTMENT / T.TOTAL_CHARGES) AS BAD_PERCENT ")
	       .append(", T.INSURANCE_PAYMENT, T.INSURANCE_ADJUSTMENT, T.PATIENT_PAYMENT, T.PATIENT_ADJUSTMENT ")
	       .append("FROM   ( ")
			        .append("WITH CHARGE_CTAPAC as ")
			        .append("			( SELECT (( SELECT COALESCE(SUM(DET.LINENETAMT),0) ")
			        .append("				          FROM EXME_CTAPACDET DET ")
			        .append("				          INNER JOIN EXME_PRODUCTOORG PO ")
			        .append("						ON PO.EXME_PRODUCTOORG_ID = FNC_GETPRODUCTORG(DET.M_PRODUCT_ID, DET.AD_ORG_ID) ")
			        .append("				          WHERE DET.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			        .append("				          AND FNC_ISPROFESSIONAL(PO.EXME_PRODUCTOORG_ID, DET.AD_ORG_ID) = ?) ")
			        .append("				 ) TOTAL_CHARGES , CP.EXME_CTAPAC_ID ")
			        .append("			  FROM   EXME_CTAPAC CP ")
			        .append("			  WHERE CP.AD_CLIENT_ID = ? ")
			        .append("			  AND CP.AD_ORG_ID = ? ")
			        .append("			  AND CP.ISACTIVE = 'Y' ")
			        .append("			  AND CP." +status+ " = ? ) ,  ")
			        .append(" PAYMENT_CTAPAC as ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_PAYMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN 0 ELSE P.PAYAMT END),0) AS PATIENT_PAYMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_PAYMENT , P.EXME_CTAPAC_ID")
			        .append("			  FROM  C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE IN (?, ?, ?, ?, ?, ?)")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) , ")
			        .append(" ADJUSTMENT_CTAPAC AS ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS PATIENT_ADJUSTMENT ,")
			        .append("			COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END), 0) AS BAD_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_ADJUSTMENT ,")
			        .append("				P.EXME_CTAPAC_ID")
			        .append("			  FROM   C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE  IN (?, ?, ?) ")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) ")
			
			        .append("SELECT FC.NAME ")
			               .append(", SUM(CC.TOTAL_CHARGES) AS TOTAL_CHARGES, SUM(PAYC.TOTAL_PAYMENT) AS TOTAL_PAYMENT ")
			               .append(", SUM(ADJC.TOTAL_ADJUSTMENT) AS TOTAL_ADJUSTMENT, SUM(ADJC.BAD_ADJUSTMENT) AS BAD_ADJUSTMENT ")
			               .append(", SUM(PAYC.INSURANCE_PAYMENT) AS INSURANCE_PAYMENT, SUM(ADJC.INSURANCE_ADJUSTMENT) AS INSURANCE_ADJUSTMENT ")
			               .append(", SUM(PAYC.PATIENT_PAYMENT) AS PATIENT_PAYMENT, SUM(ADJC.PATIENT_ADJUSTMENT) AS PATIENT_ADJUSTMENT ")
			
			        .append("FROM   EXME_PAYERCLASS FC ")
			        .append("INNER JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID ")
			        .append("LEFT JOIN ( ")
			        .append("		SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, CP.ENCOUNTERSTATUS, ")
			        .append(" 		CASE WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			        .append("		ELSE P.C_BPARTNER_ID END C_BPARTNER_ID ")
			        .append("		FROM   EXME_CTAPAC CP ")
			        .append("		INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			        .append("		LEFT JOIN EXME_PACIENTEASEG PA ON PA.ISACTIVE = 'Y' AND PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID  ")
			        .append("					AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			        .append("		WHERE CP.AD_CLIENT_ID = ? ")
			        .append("		AND CP.AD_ORG_ID = ? ")
			        .append("		AND CP.ISACTIVE = 'Y' ")
			        .append("		AND CP." +status+ " = ?")
			        .append(") C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			        .append("LEFT JOIN CHARGE_CTAPAC CC ON CC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN PAYMENT_CTAPAC PAYC ON PAYC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN ADJUSTMENT_CTAPAC ADJC ON ADJC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
					.append("GROUP BY FC.NAME ")
					.append(") T ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 1;
			pstmt.setString(i++, Constantes.REG_NOT_ACTIVE); //solo institutional ahorita
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			//SECCION PAYMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_Payment);
			pstmt.setString(i++, MCharge.TYPE_CopayPayment);
			pstmt.setString(i++, MCharge.TYPE_DeductiblePayment);
			pstmt.setString(i++, MCharge.TYPE_CoinsurancePayment);
			pstmt.setString(i++, MCharge.TYPE_OthersPayment);
			//SECCION ADJUSTMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);			
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			
			
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_FirstInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_SecondInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_ThirdInsurance); 
			pstmt.setString(i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			
			result = pstmt.executeQuery();
			
			while (result.next()){
				ZeroBalanceSummaryFCView view = new ZeroBalanceSummaryFCView();

				view.setPrimary_fc(result.getString("name"));
				view.setTotal_charges(result.getDouble("total_charges"));
				view.setTotal_payment(result.getDouble("total_payment"));
				view.setTotal_pmt_charges(result.getDouble("payment_percent"));
				view.setTotal_adjustment(result.getDouble("total_adjustment"));
				view.setTotal_adj_charges(result.getDouble("adjustment_percent"));
				view.setTotal_bd_adjustment(result.getDouble("bad_adjustment"));
				view.setTotal_db_charges(result.getDouble("bad_percent"));
				view.setTotal_insurance_payments(result.getDouble("insurance_payment"));
				view.setTotal_insurance_adjustments(result.getDouble("insurance_adjustment"));
				view.setTotal_patient_payment(result.getDouble("patient_payment"));
				view.setTotal_patient_adjustments(result.getDouble("patient_adjustment"));
				view.setTotal(view.getTotal_charges() + view.getTotal_payment() + view.getTotal_pmt_charges() + view.getTotal_adjustment() 
						+ view.getTotal_adj_charges() + view.getTotal_bd_adjustment() + view.getTotal_db_charges() + view.getTotal_insurance_payments() 
						+ view.getTotal_insurance_adjustments() + view.getTotal_patient_payment());

				array.add(view);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}

	/**
	 * Obtiene el listado del reporte de Zero Balance Summary By PG
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static List<ZeroBalanceSummaryFCView> getZeroBalancePGList() throws Exception {

		final List<ZeroBalanceSummaryFCView> array = new ArrayList<ZeroBalanceSummaryFCView>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String status = MEXMECtaPac.COLUMNNAME_InstitutionalStatus;// Para Manejar despues ambos lados
		String statusVal = MEXMECtaPac.INSTITUTIONALSTATUS_ZeroBalance;// Para Manejar despues ambos lados
		
		StringBuilder sql = new StringBuilder(" SELECT T.NAME, T.TOTAL_CHARGES, T.TOTAL_PAYMENT, (T.TOTAL_PAYMENT / T.TOTAL_CHARGES) AS PAYMENT_PERCENT, T.TOTAL_ADJUSTMENT ")
	       .append(", (T.TOTAL_ADJUSTMENT / T.TOTAL_CHARGES) AS ADJUSTMENT_PERCENT, T.BAD_ADJUSTMENT, (T.BAD_ADJUSTMENT / T.TOTAL_CHARGES) AS BAD_PERCENT ")
	       .append(", T.INSURANCE_PAYMENT, T.INSURANCE_ADJUSTMENT, T.PATIENT_PAYMENT, T.PATIENT_ADJUSTMENT ")
	       .append("FROM   ( ")
			        .append("WITH CHARGE_CTAPAC as ")
			        .append("			( SELECT (( SELECT COALESCE(SUM(DET.LINENETAMT),0) ")
			        .append("				          FROM EXME_CTAPACDET DET ")
			        .append("				          INNER JOIN EXME_PRODUCTOORG PO ")
			        .append("						ON PO.EXME_PRODUCTOORG_ID = FNC_GETPRODUCTORG(DET.M_PRODUCT_ID, DET.AD_ORG_ID) ")
			        .append("				          WHERE DET.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			        .append("				          AND FNC_ISPROFESSIONAL(PO.EXME_PRODUCTOORG_ID, DET.AD_ORG_ID) = ?) ")
			        .append("				 ) TOTAL_CHARGES , CP.EXME_CTAPAC_ID ")
			        .append("			  FROM   EXME_CTAPAC CP ")
			        .append("			  WHERE CP.AD_CLIENT_ID = ? ")
			        .append("			  AND CP.AD_ORG_ID = ? ")
			        .append("			  AND CP.ISACTIVE = 'Y' ")
			        .append("			  AND CP." +status+ " = ? ) ,  ")
			        .append(" PAYMENT_CTAPAC as ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_PAYMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN 0 ELSE P.PAYAMT END),0) AS PATIENT_PAYMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_PAYMENT , P.EXME_CTAPAC_ID")
			        .append("			  FROM  C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE IN (?, ?, ?, ?, ?, ?)")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) , ")
			        .append(" ADJUSTMENT_CTAPAC AS ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS PATIENT_ADJUSTMENT ,")
			        .append("			COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END), 0) AS BAD_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_ADJUSTMENT ,")
			        .append("				P.EXME_CTAPAC_ID")
			        .append("			  FROM   C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE  IN (?, ?, ?) ")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) ")
			
			        .append("SELECT FC.NAME ")
			               .append(", SUM(CC.TOTAL_CHARGES) AS TOTAL_CHARGES, SUM(PAYC.TOTAL_PAYMENT) AS TOTAL_PAYMENT ")
			               .append(", SUM(ADJC.TOTAL_ADJUSTMENT) AS TOTAL_ADJUSTMENT, SUM(ADJC.BAD_ADJUSTMENT) AS BAD_ADJUSTMENT ")
			               .append(", SUM(PAYC.INSURANCE_PAYMENT) AS INSURANCE_PAYMENT, SUM(ADJC.INSURANCE_ADJUSTMENT) AS INSURANCE_ADJUSTMENT ")
			               .append(", SUM(PAYC.PATIENT_PAYMENT) AS PATIENT_PAYMENT, SUM(ADJC.PATIENT_ADJUSTMENT) AS PATIENT_ADJUSTMENT ")
			
			        .append("FROM   C_BP_GROUP FC ")
			        .append("LEFT JOIN C_BPARTNER BP ON BP.C_BP_GROUP_ID = FC.C_BP_GROUP_ID ")
			        .append("LEFT JOIN ( ")
			        .append("		SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, CP.ENCOUNTERSTATUS, ")
			        .append(" 		CASE WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 2 THEN PA.C_BPARTNER_ID ")
			        .append("		WHEN CP.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 3 THEN PA.C_BPARTNER_ID ")
			        .append("		ELSE P.C_BPARTNER_ID END C_BPARTNER_ID ")
			        .append("		FROM   EXME_CTAPAC CP ")
			        .append("		INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			        .append("		LEFT JOIN EXME_PACIENTEASEG PA ON PA.ISACTIVE = 'Y' AND PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID  ")
			        .append("					AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			        .append("		WHERE CP.AD_CLIENT_ID = ? ")
			        .append("		AND CP.AD_ORG_ID = ? ")
			        .append("		AND CP.ISACTIVE = 'Y' ")
			        .append("		AND CP." +status+ " = ?")
			        .append(") C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
			        .append("LEFT JOIN CHARGE_CTAPAC CC ON CC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN PAYMENT_CTAPAC PAYC ON PAYC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN ADJUSTMENT_CTAPAC ADJC ON ADJC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
					.append("GROUP BY FC.NAME ")
					.append(") T ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 1;
			pstmt.setString(i++, Constantes.REG_NOT_ACTIVE); //solo institutional ahorita
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			//SECCION PAYMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_Payment);
			pstmt.setString(i++, MCharge.TYPE_CopayPayment);
			pstmt.setString(i++, MCharge.TYPE_DeductiblePayment);
			pstmt.setString(i++, MCharge.TYPE_CoinsurancePayment);
			pstmt.setString(i++, MCharge.TYPE_OthersPayment);
			//SECCION ADJUSTMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);			
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			
			
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_FirstInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_SecondInsurance); 
			pstmt.setString(i++, X_EXME_CtaPacExt.INSTITUTIONALSTEP_ThirdInsurance); 
			pstmt.setString(i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);		       

			result = pstmt.executeQuery();

			while (result.next()) {
				ZeroBalanceSummaryFCView view = new ZeroBalanceSummaryFCView();

				view.setPrimary_fc(result.getString("name"));
				view.setTotal_charges(result.getDouble("total_charges"));
				view.setTotal_payment(result.getDouble("total_payment"));
				view.setTotal_pmt_charges(result.getDouble("payment_percent"));
				view.setTotal_adjustment(result.getDouble("total_adjustment"));
				view.setTotal_adj_charges(result.getDouble("adjustment_percent"));
				view.setTotal_bd_adjustment(result.getDouble("bad_adjustment"));
				view.setTotal_db_charges(result.getDouble("bad_percent"));
				view.setTotal_insurance_payments(result.getDouble("insurance_payment"));
				view.setTotal_insurance_adjustments(result.getDouble("insurance_adjustment"));
				view.setTotal_patient_payment(result.getDouble("patient_payment"));
				view.setTotal_patient_adjustments(result.getDouble("patient_adjustment"));
				view.setTotal(view.getTotal_charges() + view.getTotal_payment() + view.getTotal_pmt_charges() + view.getTotal_adjustment() 
						+ view.getTotal_adj_charges() + view.getTotal_bd_adjustment() + view.getTotal_db_charges() + view.getTotal_insurance_payments() 
						+ view.getTotal_insurance_adjustments() + view.getTotal_patient_payment());

				array.add(view);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtiene el listado del reporte de Zero Balance Encounter Detail
	 * @return
	 * @throws Exception
	 */
	public static List<ZeroBalanceDetailView> getZeroBalanceDetailList(String filter) throws Exception{
		
		final List<ZeroBalanceDetailView> array = new ArrayList<ZeroBalanceDetailView>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String status = MEXMECtaPac.COLUMNNAME_InstitutionalStatus;// Para Manejar despues ambos lados
		String statusVal = MEXMECtaPac.INSTITUTIONALSTATUS_ZeroBalance;// Para Manejar despues ambos lados
		
		StringBuilder sql = new StringBuilder("select PRIMARY_FC, PRIMARY_PC, PRIMARY_PAYER, PRIMARY_PG, T.ENCOUNTER, T.MRN, BILLINGSTATUS ")
				.append(" ,EPISODE, T.TOTAL_CHARGES, T.TOTAL_PAYMENT, ")// (T.TOTAL_PAYMENT / T.TOTAL_CHARGES) AS PAYMENT_PERCENT, T.TOTAL_ADJUSTMENT ")
	      // .append(", (T.TOTAL_ADJUSTMENT / T.TOTAL_CHARGES) AS ADJUSTMENT_PERCENT, T.BAD_ADJUSTMENT, (T.BAD_ADJUSTMENT / T.TOTAL_CHARGES) AS BAD_PERCENT ")
	       
	       .append("CASE  ")
	       .append("WHEN T.TOTAL_CHARGES > 0 THEN (T.TOTAL_PAYMENT / T.TOTAL_CHARGES) ") 
	       .append("ELSE T.TOTAL_PAYMENT END AS PAYMENT_PERCENT, ")
	       .append("T.TOTAL_ADJUSTMENT , ")
	       .append("CASE ") 
	       .append("WHEN T.TOTAL_CHARGES > 0 THEN (T.TOTAL_ADJUSTMENT / T.TOTAL_CHARGES) ") 
	       .append("ELSE T.TOTAL_ADJUSTMENT END AS ADJUSTMENT_PERCENT, ")
	       .append("T.BAD_ADJUSTMENT, ")
	       .append("CASE ") 
	       .append("WHEN T.TOTAL_CHARGES > 0 THEN (T.BAD_ADJUSTMENT / T.TOTAL_CHARGES) ") 
	       .append("ELSE T.BAD_ADJUSTMENT END AS BAD_PERCENT  ")	       
	       
	       .append(", T.INSURANCE_PAYMENT, T.INSURANCE_ADJUSTMENT, T.PATIENT_PAYMENT, T.PATIENT_ADJUSTMENT ")
	       .append("FROM   ( ")
			        .append("WITH CHARGE_CTAPAC as ")
			        .append("			( SELECT (( SELECT COALESCE(SUM(DET.LINENETAMT),0) ")
			        .append("				          FROM EXME_CTAPACDET DET ")
			        .append("				          INNER JOIN EXME_PRODUCTOORG PO ")
			        .append("						ON PO.EXME_PRODUCTOORG_ID = FNC_GETPRODUCTORG(DET.M_PRODUCT_ID, DET.AD_ORG_ID) ")
			        .append("				          WHERE DET.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
			        .append("				          AND FNC_ISPROFESSIONAL(PO.EXME_PRODUCTOORG_ID, DET.AD_ORG_ID) = ?) ")
			        .append("				 ) TOTAL_CHARGES , CP.EXME_CTAPAC_ID ")
			        .append("			  FROM   EXME_CTAPAC CP ")
			        .append("			  WHERE CP.AD_CLIENT_ID = ? ")
			        .append("			  AND CP.AD_ORG_ID = ? ")
			        .append("			  AND CP.ISACTIVE = 'Y' ")
			        .append("			  AND CP." +status+ " = ? ) ,  ")
			        .append(" PAYMENT_CTAPAC as ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_PAYMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN 0 ELSE P.PAYAMT END),0) AS PATIENT_PAYMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_PAYMENT , P.EXME_CTAPAC_ID")
			        .append("			  FROM  C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE IN (?, ?, ?, ?, ?, ?)")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) , ")
			        .append(" ADJUSTMENT_CTAPAC AS ")
			        .append("			( SELECT COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS PATIENT_ADJUSTMENT ,")
			        .append("			COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END),0) AS INSURANCE_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(CASE WHEN AT.TYPE = ? THEN P.PAYAMT ELSE 0 END), 0) AS BAD_ADJUSTMENT ,")
			        .append("				COALESCE(SUM(P.PAYAMT), 0) AS TOTAL_ADJUSTMENT ,")
			        .append("				P.EXME_CTAPAC_ID")
			        .append("			  FROM   C_PAYMENT P")
			        .append("			  INNER JOIN C_CHARGE AT ON AT.C_CHARGE_ID = P.C_CHARGE_ID")
			        .append("			  WHERE  P.AD_CLIENT_ID = ?")
			        .append("			  AND P.AD_ORG_ID = ?")
			        .append("			  AND P.CONFTYPE = ?")
			        .append("			  AND AT.TYPE  IN (?, ?, ?) ")
			        .append("			  GROUP BY P.EXME_CTAPAC_ID ) ")
			        
		.append(" SELECT FC.NAME AS PRIMARY_FC, PC.NAME AS PRIMARY_PC, BP.NAME AS PRIMARY_PAYER, BPG.NAME AS PRIMARY_PG, C.ENCOUNTER, C.MRN, ")
			.append(" ? AS BILLINGSTATUS , C.EXTDOC || ' - ' || C.EXTNO AS EPISODE ")
			 .append(", SUM(CC.TOTAL_CHARGES) AS TOTAL_CHARGES, SUM(PAYC.TOTAL_PAYMENT) AS TOTAL_PAYMENT ")
			               .append(", SUM(ADJC.TOTAL_ADJUSTMENT) AS TOTAL_ADJUSTMENT, SUM(ADJC.BAD_ADJUSTMENT) AS BAD_ADJUSTMENT ")
			               .append(", SUM(PAYC.INSURANCE_PAYMENT) AS INSURANCE_PAYMENT, SUM(ADJC.INSURANCE_ADJUSTMENT) AS INSURANCE_ADJUSTMENT ")
			               .append(", SUM(PAYC.PATIENT_PAYMENT) AS PATIENT_PAYMENT, SUM(ADJC.PATIENT_ADJUSTMENT) AS PATIENT_ADJUSTMENT ")
		.append(" FROM   EXME_PAYERCLASS PC ")
		.append(" LEFT JOIN EXME_FINANCIALCLASS FC ON FC.EXME_FINANCIALCLASS_ID = PC.EXME_FINANCIALCLASS_ID ")
		.append(" LEFT JOIN C_BPARTNER BP ON BP.EXME_FINANCIALCLASS_ID = FC.EXME_FINANCIALCLASS_ID AND BP.EXME_PAYERCLASS_ID = PC.EXME_PAYERCLASS_ID ")
		.append(" LEFT JOIN C_BP_GROUP BPG ON BPG.C_BP_GROUP_ID = BP.C_BP_GROUP_ID ")
		.append(" INNER JOIN ( SELECT DISTINCT CP.EXME_CTAPAC_ID, CP.AD_CLIENT_ID, CP.AD_ORG_ID, CP.ENCOUNTERSTATUS, HE.DOCUMENTNO AS MRN, ")
			.append(" CP.DOCUMENTNO AS ENCOUNTER , CPE.DOCUMENTNO AS EXTDOC, CPE.EXTENSIONNO AS EXTNO, ")
			.append(" CASE  ")
					.append(" WHEN CPE.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 ")
					.append(" THEN PA.C_BPARTNER_ID ")
					.append(" WHEN CPE.INSTITUTIONALSTEP = ? AND PA.PRIORITY = 1 ")
					.append(" THEN P.C_BPARTNER_ID ")
					.append(" ELSE P.C_BPARTNER_ID ")
					.append("  END C_BPARTNER_ID, ")
					.append(" CPE.EXME_CTAPACEXT_ID")
			.append(" FROM   EXME_CTAPAC CP ")
			.append(" INNER JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND CPE.EXTENSIONNO = 0 ")
			.append(" INNER JOIN EXME_PACIENTE P ON P.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
			.append(" LEFT JOIN EXME_PACIENTEASEG PA ON PA.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID AND PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
			.append(" LEFT JOIN EXME_HIST_EXP HE ON HE.EXME_PACIENTE_ID = P.EXME_PACIENTE_ID ")
			.append(" AND HE.AD_CLIENT_ID = CP.AD_CLIENT_ID AND HE.AD_ORG_ID = CP.AD_ORG_ID  ")
			.append(" WHERE CP.ISACTIVE = 'Y' AND CP.INSTITUTIONALSTATUS = ? )  C ON C.C_BPARTNER_ID = BP.C_BPARTNER_ID ")
		.append("LEFT JOIN CHARGE_CTAPAC CC ON CC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN PAYMENT_CTAPAC PAYC ON PAYC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ")
			        .append("LEFT JOIN ADJUSTMENT_CTAPAC ADJC ON ADJC.EXME_CTAPAC_ID = C.EXME_CTAPAC_ID ");
			if(StringUtils.isNotEmpty(filter)){
				sql.append("WHERE ");
				sql.append(filter.substring(4, filter.length())); // para quitar el primer "AND
			}
			sql.append("  GROUP BY C.ENCOUNTER, C.MRN, C.EXTDOC || ' - ' || C.EXTNO, FC.NAME, PC.NAME, BP.NAME, BPG.NAME, ?  ");
			if (DB.isOracle()) {
				sql.append(" ) T "); 
			} else if (DB.isPostgreSQL()) {
				sql.append(" ::TEXT ) T  ");
			}  
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			int i = 1;
			pstmt.setString(i++, Constantes.REG_NOT_ACTIVE); //solo institutional ahorita
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, statusVal);
			//SECCION PAYMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);
			pstmt.setString(i++, MCharge.TYPE_InsurancePayments);
			pstmt.setString(i++, MCharge.TYPE_Payment);
			pstmt.setString(i++, MCharge.TYPE_CopayPayment);
			pstmt.setString(i++, MCharge.TYPE_DeductiblePayment);
			pstmt.setString(i++, MCharge.TYPE_CoinsurancePayment);
			pstmt.setString(i++, MCharge.TYPE_OthersPayment);
	                		//SECCION ADJUSTMENT_CTAPAC
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			pstmt.setInt(i++, Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++, Env.getAD_Org_ID(Env.getCtx()));
			pstmt.setString(i++, MPayment.CONFTYPE_Technical);			
			pstmt.setString(i++, MCharge.TYPE_PatientAdjustments);
			pstmt.setString(i++, MCharge.TYPE_Adjustment);
			pstmt.setString(i++, MCharge.TYPE_BadDebtAdjustment);
			
			//FIXME mandar como parametro el ctx
			String valueStatus = MRefList.getListName(Env.getCtx(), 
					MEXMECtaPacExt.INSTITUTIONALSTATUS_AD_Reference_ID, 
					MEXMECtaPacExt.INSTITUTIONALSTATUS_ZeroBalance);
			
			
			pstmt.setString(i++, valueStatus);
			pstmt.setString(i++, MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
			pstmt.setString(i++, MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
			pstmt.setString(i++, MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
			pstmt.setString(i++, MEXMECtaPacExt.INSTITUTIONALSTATUS_ZeroBalance);
			pstmt.setString(i++, valueStatus);
		
			result = pstmt.executeQuery();
			while (result.next()){
				
				ZeroBalanceDetailView view = new ZeroBalanceDetailView();
				
				view.setEncounter(result.getString("encounter"));
				view.setMrn(result.getString("mrn"));
				view.setBilling_status(result.getString("billingstatus"));
				view.setEpisode(result.getString("episode"));
				view.setPrimary_payer(result.getString("primary_payer"));
				view.setPrimary_fc(result.getString("primary_fc"));
				view.setPrimary_pc(result.getString("primary_pc"));
				view.setPrimary_pg(result.getString("primary_pg"));
				view.setTotal_charges(result.getDouble("total_charges"));
				view.setTotal_payments(result.getDouble("TOTAL_PAYMENT"));
				view.setTotal_pmt_charges(result.getDouble("PAYMENT_PERCENT"));
				view.setTotal_adjustment(result.getDouble("total_adjustment"));
				view.setTotal_adj_charges(result.getDouble("ADJUSTMENT_PERCENT"));
				view.setTotal_insurance_payments(result.getDouble("INSURANCE_PAYMENT"));
				view.setTotal_insurance_adjustments(result.getDouble("INSURANCE_ADJUSTMENT"));
				view.setTotal_patient_payments(result.getDouble("PATIENT_PAYMENT"));
				view.setTotal_patient_adjustments(result.getDouble("patient_adjustment"));
				view.setTotal_bad_debt_adjustments(result.getDouble("BAD_PERCENT"));							
		
				array.add(view);
				
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, null, e);
		}finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Busca su la extension actual tiene la contraparte 
	 * del CCCmD
	 * @return
	 */
	public boolean tieneLineasCCCmD() {
		boolean existe = false;
		try {
			List<MCtaPacDet> lstCargos = getLstCargos(MEXMECtaPacExt.condicionCargos ());
			if(!lstCargos.isEmpty()){
				for (int j = 0; j < lstCargos.size(); j++) {
					if (lstCargos.get(j).getProduct()!=null && lstCargos.get(j).getProduct().isCCCmD()) {
						existe = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
		return existe;
	}

	/**
	 * La extension esta facturada?
	 * @return true: extension facturada
	 */
	public boolean extActualFacturada() {
		return getC_Invoice_ID()>0;
	}
	
	/**
	 * Condicion sql para ver los cargos exclusivamente en facturación por extensiones
	 * @return String sql
	 */
	public static String condicionCargos (){
		String sql =" AND EXME_CtaPacDet.SeDevolvio='N' AND EXME_CtaPacDet.QtyDelivered > 0 AND EXME_CtaPacDet.TipoLinea <>  "
				+ DB.TO_STRING(X_EXME_CtaPacDet.TIPOLINEA_Exempt);
		return sql;
	} 
	
	/**
	 * lineasCCCmD
	 * @param lstChargesOrig
	 * @return
	 */
	public boolean existenLineasCCCmD(final List<MCtaPacDet> lstChargesOrig) {
		// que no este vacia
		return !leerCargosPorTipo(lstChargesOrig, new  String[]{
				X_EXME_CtaPacDet.TIPOLINEA_Charge}).isEmpty();
	}
	
	/**
	 * Selecciona solo las lineas del tipo que se solicitan
	 * @param lstChargesOrig
	 * @param tipoLinea
	 * @return
	 */
	private List<MCtaPacDet> leerCargosPorTipo(final List<MCtaPacDet> lstChargesOrig, final String[] tipoLinea) {
		
		final List<MCtaPacDet> lstChargesNew = new ArrayList<MCtaPacDet>();
		for (final MCtaPacDet bean: lstChargesOrig) {
			
			// Por tipo de linea
			if(lstChargesNew.isEmpty()){
				if (bean.getM_Product_ID()>0 && !bean.getProduct().isProduct()){

					
					for (final String tipo: tipoLinea) {
						if (tipo.equals(bean.getTipoLinea())){
							lstChargesNew.add(bean);
							break;
						}
					}
					
					
				}
			} else {
				break;
			}
		}
		return lstChargesNew;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if (newRecord && getExtensionNo()==0){
			if (getInstitutionalStep() == null){
				setInstitutionalStep(INSTITUTIONALSTEP_Default);
			}
			if (getInstitutionalStatus() == null){
				setInstitutionalStatus(INSTITUTIONALSTATUS_NotBilled);
			}
			if (getProfessionalStep() == null){
				setProfessionalStep(PROFESSIONALSTEP_Default);
			}
			if (getProfessionalStatus() == null){
				setProfessionalStatus(PROFESSIONALSTATUS_NotBilled);
			}			
		}
		
		if(getExtensionNo()==0){
			cleanDiscount();
		}
		
		return true;
	}
	
	/***
	 * Obtiene Lista de Extensiones de Cuentas Paciente a Ser Facturadas 
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author gvaldez
	 */
	public static List<MEXMECtaPacExt> getExtBillCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPacExt> cuentasExt = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ext.* FROM EXME_CtaPacExt ext INNER JOIN EXME_CtaPac cta ON cta.EXME_CtaPac_ID = ext.EXME_CtaPac_ID ")
		   .append("WHERE cta.IsActive = 'Y' AND ext.IsActive = 'Y' AND ext.ExtensionNo = 0 ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "ext"));
		sql.append(" ORDER BY ext.DATEORDERED desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs, trxName);
				cuentasExt.add(ctaPacExt);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return cuentasExt; 
	}
	
	/***
	 * Obtiene Lista de Extensiones de Cuentas Paciente Facturadas 
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author gvaldez
	 */
	public static List<MEXMECtaPacExt> getExtBilledCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPacExt> cuentasExt = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ext.* FROM EXME_CtaPacExt ext INNER JOIN EXME_CtaPac cta ON cta.EXME_CtaPac_ID = ext.EXME_CtaPac_ID ")
		   .append("WHERE cta.exme_Ctapac_id = ? and cta.IsActive = 'Y' AND ext.IsActive = 'Y' AND ext.ExtensionNo > 0 ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "ext"));
		sql.append(" ORDER BY ext.DATEORDERED desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs, trxName);
				cuentasExt.add(ctaPacExt);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return cuentasExt; 
	}
	
	
	/**
	 * Get the insurance according to ConfType and Step
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	
	public static MBPartner getBillingInsurance(Properties ctx, int ctaPacExtID) {
		final MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, ctaPacExtID, null);
		final int priority;
		String status = ctaPacExt.getConfType().equals(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim) || 
				ctaPacExt.getConfType().equals(X_HL7_MessageConf.CONFTYPE_OutpatientProfessionalClaim) 
					    ? ctaPacExt.getProfessionalStep() : ctaPacExt.getInstitutionalStep();
		if (MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance.equals(status)) {
			priority = 1;
		} else if (MEXMECtaPacExt.INSTITUTIONALSTEP_SecondInsurance.equals(status)) {
			priority = 2;
		} else if (MEXMECtaPacExt.INSTITUTIONALSTEP_ThirdInsurance.equals(status)) {
			priority = 3;
		} else { 
			priority = -1;
			return null;
		}
		return (MBPartner) 
				((MEXMEPacienteAseg)
						new Query(ctx, MEXMEPacienteAseg.Table_Name, 
								" EXME_CtaPac_ID=? AND Priority=? AND SupportBilling=? AND IsActive = 'Y' ", null)
						.setParameters(ctaPacExt.getEXME_CtaPac_ID(), priority, 
								ctaPacExt.getConfType().equals(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim) 
									? MEXMEPacienteAseg.SUPPORTBILLING_Institucional 
											: MEXMEPacienteAseg.SUPPORTBILLING_Professional)
				.first()).getC_BPartner();
	}
	

	/**
	 * Establecemos la aseguradora actual para la extension
	 * @return current Payer for USA
	 * 
	 */
	public MBPartner getCurrentPayer() {

		//
		if (getEXME_CtaPac_ID() == 0){
			return null;
		}else if (getEXME_CtaPacExt_ID() == 0){
			return null;
		}else if (getExtensionNo() == 0)
			return null;
		if (currentPayer == null || currentPayer.getC_BPartner_ID() == 0)
			if (getConfType().equalsIgnoreCase(CONFTYPE_InstitutionalClaim)
					&& getInstitutionalStep().equalsIgnoreCase(INSTITUTIONALSTEP_SelftPay)){
				currentPayer = getCtaPac().getBPartner();
			}else if (getConfType().equalsIgnoreCase(CONFTYPE_ProfessionalClaim)
					&& getProfessionalStep().equalsIgnoreCase(PROFESSIONALSTEP_SelftPay)){
				currentPayer = getCtaPac().getBPartner();
				
			}else{
				currentPayer= getBillingInsurance(getCtx(), getEXME_CtaPacExt_ID());
			}

		return currentPayer;

	}
	
	/***
	 * Obtiene Lista de Extensiones de Cuentas Paciente 
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author Rosy Velazquez, Adrian Bautista
	 */
	public static List<MEXMECtaPacExt> getExtCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPacExt> extcuentas = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM EXME_CtaPacExt ext WHERE ext.IsActive = 'Y' ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "ext"));
		sql.append(" ORDER BY ext.DocumentNo Asc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs, trxName);
				extcuentas.add(ctaPacExt);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return extcuentas; 
	}
	
	/***
	 * Obtiene Lista de Extensiones agrupadas por la Cuenta Paciente (la extension que se muestra es la mas reciente)  
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author Rosy Velazquez, Adrian Bautista
	 */
	public static List<MEXMECtaPacExt> getExtGroupCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPacExt> extcuentas = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ext.EXME_CtaPac_ID, MAX(ext.EXME_CtaPacExt_ID) AS EXME_CtaPacExt_ID FROM EXME_CtaPacExt ext WHERE ext.IsActive = 'Y' ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "ext"));
		sql.append(" GROUP BY ext.EXME_CtaPac_ID ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs.getInt(COLUMNNAME_EXME_CtaPacExt_ID), trxName);
				extcuentas.add(ctaPacExt);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return extcuentas; 
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (MCountry.isUSA(Env.getCtx())
				&& success && !newRecord
				&& (getExtensionNo()==0 &&
						(is_ValueChanged(COLUMNNAME_InstitutionalStatus)
						|| is_ValueChanged(COLUMNNAME_InstitutionalStep)
						|| is_ValueChanged(COLUMNNAME_ProfessionalStatus)
						|| is_ValueChanged(COLUMNNAME_ProfessionalStep)
						|| is_ValueChanged(COLUMNNAME_ChargeStatus)))){

						MEXMEPacienteCtaV.updateSearchCta(p_ctx, getEXME_CtaPac_ID(), get_TrxName());
						QuickSearchTables.checkTables(MEXMECtaPacV.class, 
								MEXMECtaPacV.Table_Name, getEXME_CtaPac_ID(), get_TrxName(), getCtx());
		}
		return success;
	}
	
	/** Get Total Lines.
	@return Total of Lines, from field for Ext >0 and for CtaPacDet if Ext = 0
	 */
	@Override
	public BigDecimal getTotalLines () 
	{
		
		if (getExtensionNo()==0){
			BigDecimal retVal = BigDecimal.ZERO;
			try {
				for (MCtaPacDet cargo : getLstCargos(MEXMECtaPacExt.condicionCargos())){
					retVal = retVal.add(cargo.getLineNetAmt());
				}
			} catch (SQLException e) {
				log.severe("Error al cargar lineas de Ext Cero: TotalLines , ID = " + getEXME_CtaPacExt_ID());
			}
			return retVal;
		}else{
			return super.getTotalLines();
		}
	}
	
//	
//	/**
//	 * Rfc primero el de la persona responsable
//	 * @return
//	 */
//	public String rfc(){
//		String rfc =  getRfc();
//		if(getCtaPac()!=null
//				&& getCtaPac().getPaciente()!=null
//				&& getCtaPac().getPaciente().getMEXMEPacienteRel()!=null){
//			rfc =   getCtaPac().getPaciente().getMEXMEPacienteRel().getRfc();
//		}
//		return rfc;
//	}

	/**
	 * Consulta para obtener las Lineas dobles de CCCmD
	 * por extension 
	 * @param tipoLineas Cadena con los tipos de lineas de obtner
	 */
	public List<MCtaPacDet> correccionLineasDobles(final String tipoLineas) {
		final List<MCtaPacDet> cargos = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder("	SELECT *   ") 
		.append(" FROM  EXME_CtaPacDet     ") 
		.append(" WHERE isActive = 'Y'     ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_EXME_CtaPacExt.Table_Name))
		.append(" AND   EXME_CtaPacExt_ID = ?      ")
		.append(" AND   TipoLinea in (").append(tipoLineas).append(") ")
		.append(" ORDER BY tipoLinea, created desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getEXME_CtaPacExt_ID());
			rset = pstmt.executeQuery();
			
			while (rset.next()){
				cargos.add(new MCtaPacDet(getCtx(), rset, null));
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, null, e);
		}finally {
			DB.close(rset, pstmt);
		}

		return cargos;
	}
	
	public static List<MEXMECtaPacExt> getExtentions(Properties ctx, int exmeCtaPac, String configType, String trxName) {
		List<MEXMECtaPacExt> list = new ArrayList<MEXMECtaPacExt>();
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT * from exme_ctapacext where exme_ctapac_id = ? and confType = ? and isactive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeCtaPac);
			pstmt.setString(2, configType);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECtaPacExt(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			list = null;
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Crea una extension nueva con la siguiente numeracion para mover cargos
	 * basado en 
	 * @see {@link com.ecaresoft.web.forms.billing.Extension.crearExtension()}
	 * **/
	public void setCliente(final MEXMECtaPac ctaPac) {
		// Creamos la nueva extension a partir de la extension cero de la cuenta
		// paciente.
		final MEXMEPaciente paciente = new MEXMEPaciente(ctaPac.getCtx(),
				ctaPac.getEXME_Paciente_ID(), ctaPac.get_TrxName());
		boolean dataRecord = false;// Nombre del paciente

		// Razon social (Por defecto el nombre del paciente)
		if (getBpartner() != null
				&& !getBpartner().isAseguradora()
				&& paciente.getDescription() != null
				&& !paciente.getDescription().isEmpty()) {
			// si ya se ha facturado con anterioridad
			setDescription(paciente.getDescription());
			dataRecord = true;// Razon socual
		}

		// Si la extension es particular
		if (getBpartner() != null
				&& !getBpartner().isAseguradora()) {
			final BeanDatosFacturacion datosFactura = ctaPac
					.getDireccionParticular();

			if (datosFactura != null) {
				setRfc(datosFactura.getRfc());

				if (!dataRecord && datosFactura.getName() != null
						&& !datosFactura.getName().isEmpty()) {
					setDescription(datosFactura.getName());// Responsable
				}

				if (getC_Location_ID() > 0) {
					// MLocation copyFrom(Properties ctx, int originId, int
					// C_Location_ID, String trxName)
					final MLocation loc = MEXMELocation.copyFrom(
							ctaPac.getCtx(),
							datosFactura.getC_Location_ID(),
							getC_Location_ID(), null);
					loc.save();
				}
			} else {
				setRfc(paciente.getRfc());
			}
		}
	}
	
	/**
	 * lineasCCCmD
	 * @param lstChargesOrig
	 * @return
	 */
	public boolean isLinesExistOnlyCCCmD(final List<MCtaPacDet> lstChargesOrig) {
		// que no este vacia
		return !leerCargosPorTipo(lstChargesOrig, new  String[]{
				X_EXME_CtaPacDet.TIPOLINEA_Charge}).isEmpty();
	}
	
	/**
	 * lineasCCCmD
	 * @param lstChargesOrig
	 * @return
	 */
	public boolean isLinesExistCCCmD(final List<MCtaPacDet> lstChargesOrig) {
		// que no este vacia
		return !leerCargosPorTipo(lstChargesOrig, new  String[]{
				X_EXME_CtaPacDet.TIPOLINEA_Coaseguro,X_EXME_CtaPacDet.TIPOLINEA_Deducible,X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico,X_EXME_CtaPacDet.TIPOLINEA_Copago}).isEmpty();
	}

	/**
	 * Totales de la extension
	 * @throws SQLException
	 */
	public void setSumTotal(final BeanConcepts beanConcp) throws SQLException {
		if(getC_Invoice_ID()>0){
			s_log.log(Level.INFO, "No se puede actualizar la extensión, actualmente esta facturada: "+getC_Invoice_ID());
			
		} else {
			cleanSum();
			// Suma de las lineas solo con convenios
			setTotalLines(beanConcp.getTotalVtaOrig());// 284503.45
			// Suma de las lineas solo con convenios, desc global y cccmd
			setTaxAmt(beanConcp.getImpuestoGT().setScale(MCtaPacDet.REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP));//24788.523200
			// Impuesto de descuento 
			setDiscountTaxAmt(beanConcp.getDescuento().getMontoImpuesto().setScale(MCtaPacDet.REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP));//6196.985600
			setDesctoGlobalAmt(beanConcp.getDescuento().getMontoCalculado()); //56900.6900
			// Total venta con convenios
			setTotalBruto(getTotalLines());//284503.45
			// Total despues de descuento
			setSubTotal(getTotalLines().subtract(getDesctoGlobalAmt()));//227602.7600
			// Total despues de descuento sin CCCmD
			setTotalNeto(getSubTotal());//227602.76

			// Total del documento (venta-descuentos+impuestos)
			setGrandTotal();//252391.2800
			setAnticipo(this.getAnticiposAmtTotal());
		}
	}
	
	public String toString(){
		return "Sum(TotalLines:"+getTotalLines()+" DiscountAmt"+getDesctoGlobalAmt()+" TaxAmt:"+ getTaxAmt()+")=Total"+getGrandTotal()+"Invoice"+getC_Invoice_ID();
	}
	
	/**
	 * Gran Total
	 */
	public void setGrandTotal(){
		// convenio-descuentoglobal-cccmd+impuestos //252391.2800
		setGrandTotal(getTotalLines().subtract(getDesctoGlobalAmt())
				.subtract(getCoaseguroAmt())
				.subtract(getCopagoAmt())
				.subtract(getCoaseguroMedAmt())
				.subtract(getDeducibleAmt())
				.add(getTaxAmt()));
	}
	
	public MEXMECtaPacExt cleanCCCmD(){
		setDeducible(Env.ZERO);
		setDeducibleAmt(Env.ZERO);
		setCoaseguro(Env.ZERO);
		setCoaseguroAmt(Env.ZERO);
		setCoaseguroMedAmt(Env.ZERO);
		setCoaseguroMed(Env.ZERO);
		setCopago(Env.ZERO);
		setCopagoAmt(Env.ZERO);
		return this;
	}
	
	/**
	 * Inicializa los campos de totales 
	 * @return
	 */
	public MEXMECtaPacExt cleanSum(){
		this.setTotalBruto(Env.ZERO);// Todas las lineas de cargo
		this.setSubTotal(Env.ZERO);// menos descuento
		this.setTotalNeto(Env.ZERO);// Con CCCmD//11598.84 antes                
		this.setTaxAmt(Env.ZERO);// suma de impuesto de las lineas de cargo
		this.setDiscountTaxAmt(Env.ZERO);// suma de impuesto del descuento
		this.setAnticipo(Env.ZERO);// Anticipos
		this.setGrandTotal(Env.ZERO);// total con CCCmD
		this.setTotalLines(Env.ZERO);// total con CCCmD 
		this.setDesctoGlobalAmt(Env.ZERO);// total con CCCmD 
		return this;
	}
	
	/**
	 * Asignacion de nuevo socio de negocios
	 * @param customerSwitch Socio previamente relacionado al paciente/cuenta
	 * @param customerSelect Socio seleccionado por el usurio
	 * @param isCCCmD tiene CCCmD
	 * @return error
	 */
	public String setNewCustomer(final int customerSwitch, final int customerSelect, final boolean isCCCmD){
		// Cual es el socio nuevo ?
		int idSocioCambiar = 0;
		if (customerSelect > 0) {
			// Socio seleccionado
			idSocioCambiar = customerSelect;
		} else {
			// Switch Aseguradora <-> Paciente
			idSocioCambiar = customerSwitch;
		}
		return setCustomer(idSocioCambiar, customerSelect > 0, isCCCmD);
	}
		
	/**
	 * Asignacion de cliente a la extension
	 * @param customer Cliente (C_BPartner_ID)
	 * @param insurance true: Asegurador
	 * @param isCCCmD tiene CCCmD
	 * @return error o null
	 */
	private String setCustomer(final int customer, final boolean insurance, final boolean isCCCmD){
		String msg = null;
		// Socio de negocios particular o aseguradora
		final MEXMEBPartner mBPartner = new MEXMEBPartner(Env.getCtx(),customer, null);

		// Validar: Es aseguradora y el detalles es CCCmD no se puede
		// cambiar
		if (mBPartner.isAseguradora() && isCCCmD) {
			msg = "factExtension.error.findBPartner.isAseguradora";
		}

		// Si no han ocurrido errores
		if (msg==null) {
			setC_BPartner_ID(customer);
			if(insurance){
				setNewSocio(customer);
			}else{
				setNewSocioPac(customer);
			}

			if(!getLocation().save()) {
				if(mBPartner.isAseguradora()){
					msg = "error.factDirecta.facturar.noBpartnerLocationAseg";
				} else {
					msg = "error.factDirecta.facturar.noBpartnerLocationPart";
				}
			}
			
			if (!save(null) ){
				msg = "factExtension.error.refreshExtension";
			}else{
				this.m_bPartner = null;
			}
		}
		return msg;
	}
	
	/** Actualizacion a base de datos */
	public boolean updateExt(final BeanConcepts bean) throws SQLException{
		setSumTotal(bean);
		return save();
	}
	
	/** Elimina los cargos ya no necesarios */
	public void deleteRef(List<? extends MCtaPacDet> lstDeleteRef){
		if (lstDeleteRef != null && !lstDeleteRef.isEmpty()) {
			for (MCtaPacDet cargo : lstDeleteRef) {
				if (!cargo.delete(true, get_TrxName())) {
					cargo.setIsActive(false);
					if (!cargo.save(get_TrxName())) {
						log.log(Level.SEVERE,
								"cargo.delete(true, mTrx.getTrxName()))");
					}
				}
			}
		}
	}
	/** Elimina los cargos ya no necesarios por CCCmD y descuentos */
	public boolean deleteConcept(List<? extends MCtaPacDet> lstDeleteRef){
		boolean descEliminado = true;
		if (lstDeleteRef != null && !lstDeleteRef.isEmpty()) {
			for (final MCtaPacDet cargo : lstDeleteRef) {
				// El defecto solo se detecto para cargos, por lo
				// que se condiciona para ese caso
				if (cargo.getC_Charge_ID() > 0 || !cargo.getProduct().isProduct()) {
					if (!cargo.delete(true, get_TrxName())) {
						
						// Si no se puede borrar se inactiva
						cargo.setIsActive(false);
						if (!cargo.save(get_TrxName())) {
							log.log(Level.SEVERE,
									"cargo.delete(true, mTrx.getTrxName()))");
							descEliminado = false;
						}
					}
				}
			}
		}
		return descEliminado;
	}
	
	
	public boolean cleanConcept(final int opc){
		if(opc==0){
			setDesctoGlobal(Env.ZERO);
			setDesctoGlobalAmt(Env.ZERO);
		} else if(opc==1){
			setDeducible(Env.ZERO);
			setDeducibleAmt(Env.ZERO);
		} else if(opc==2){
			setCoaseguro(Env.ZERO);
			setCoaseguroAmt(Env.ZERO);
		} else if(opc==3){
			setCoaseguroMedAmt(Env.ZERO);
			setCoaseguroMed(Env.ZERO);
		} else if(opc==4){
			setCopago(Env.ZERO);
			setCopagoAmt(Env.ZERO);
		}
		return save();
	}
	
	/**
	 * Actualiza las lineas (cargos) como facturadas
	 * @param trxName
	 */
	public void updateDetInvoiced(final String trxName){
		// Actualizamos los cargos, modificar lineas cuando ya se creo una
		// factura
		for (int i = 0; i < getLineasDeExtension().length; i++) {
			if(getLineasDeExtension()[i]!=null && getLineasDeExtension()[i].getEXME_CtaPacDet_ID()>0){
				getLineasDeExtension()[i].set_TrxName(trxName);
				getLineasDeExtension()[i].setIsFacturado(true);
				if (!getLineasDeExtension()[i].save(trxName)) {
					throw new MedsysException(Utilerias.getLabel("factExtension.error.createInvoiceExt"));
				}
			}
		}
	}

	/**
	 * Busca y elimina la linea de descuento
	 * @param lstChargesOrig
	 */
	public int deleteDiscount(final List<MCtaPacDet> lstChargesOrig) {
		int count=0;
		// Itera la lista de productos que si sin son productos o servicios
		// quita descuento y/o CCCmD
		for (int i = 0; i < lstChargesOrig.size(); i++) {
			final MCtaPacDet bean = (MCtaPacDet) lstChargesOrig.get(i);
			if (X_EXME_CtaPacDet.TIPOLINEA_Discount.equals(bean.getTipoLinea())
				&& bean.getEXME_CtaPacDet_ID() > 0) {
				count++;
				if(bean.delete(true)){
					break;
				} else {
					bean.setIsActive(false);
					bean.save(null);
					break;
				}
			}
		}// for
		return count;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param confType
	 * @param startDate
	 * @param endDate
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMECtaPacExt> getClaimsReport(Properties ctx, String confType, Date startDate, Date endDate, String trxName) {
		ArrayList<MEXMECtaPacExt> list = new ArrayList<MEXMECtaPacExt>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT ID AS EXME_CTAPACEXT_ID ");
		if (DB.isOracle()) {
			sql.append("FROM TABLE(fnc_getListaClaimsR(?, ?, ?, ?, ?))");
		} else if (DB.isPostgreSQL()) {
			sql.append("FROM fnc_getListaClaimsR(?, ?, ?, ?, ?) T ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.setString(3, confType);
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(5, new java.sql.Date(endDate.getTime()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, rs.getInt("EXME_CtaPacExt_ID"), trxName);
				list.add(ctaPacExt);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}
	
	/**
	 * Selecciona solo las lineas del tipo que se solicitan
	 * @param lstChargesOrig
	 * @param tipoLinea
	 * @param numExt Si tiene extension separar por extension
	 * @return
	 */
	public List<MCtaPacDet> leerCargosPorExtension(final List<? extends MCtaPacDet> lstChargesOrig, final int numExt) {
		final List<MCtaPacDet> lstChargesNew = new ArrayList<MCtaPacDet>();
		for (final MCtaPacDet bean: lstChargesOrig) {
			// Que pertenezcan a la extension
			if( numExt == Integer.parseInt(bean.getExtLVB().getValue())){
				lstChargesNew.add(bean);
			} 
		}
		return lstChargesNew;
	}
	
	/**
	 * Selecciona solo las lineas del tipo que se solicitan
	 * @param lstChargesOrig
	 * @param tipoLinea
	 * @param numExt Si tiene extension separar por extension
	 * @return
	 */
	public List<MCtaPacDet> leerCargosPorTipo(final List<MCtaPacDet> lstChargesOrig, final String[] tipoLinea, final int numExt) {
		final List<MCtaPacDet> lstChargesNew = new ArrayList<MCtaPacDet>();
		for (final MCtaPacDet bean: lstChargesOrig) {
			// Que pertenezcan a la extension
			if( numExt == Integer.parseInt(bean.getExtLVB().getValue())){
				lstChargesNew.add(bean);
				
			} else if( numExt<0 ) {
				// o por tipo de 
				for (String tipo: tipoLinea) {
					if (tipo.equals(bean.getTipoLinea()) && bean.getProduct()!=null && !bean.getProduct().isProduct()){
						lstChargesNew.add(bean);
						break;
					}
				}
			}
		}
		return lstChargesNew;
	}
	
	/**
	 * Crea una extension nueva con la siguiente numeracion para mover cargos
	 * 
	 * @throws InterruptedException
	 * 
	 * **/
	public boolean crearExtension(final MEXMECtaPac mCtaPac) throws InterruptedException {
		// Creamos la nueva extension a partir de la extension cero de la cuenta
		// paciente.
		final MEXMEPaciente paciente = new MEXMEPaciente(mCtaPac.getCtx(),
				mCtaPac.getEXME_Paciente_ID(), mCtaPac.get_TrxName());
		mCtaPac.setPaciente(paciente);
		boolean dataRecord = false;// Nombre del paciente

		// Razon social (Por defecto el nombre del paciente)
		if (getBpartner() != null
				&& !getBpartner().isAseguradora()
				&& paciente.getDescription() != null
				&& !paciente.getDescription().isEmpty()) {
			// si ya se ha facturado con anterioridad
			setDescription(paciente.getDescription());
			dataRecord = true;// Razon socual
		}
		// Se pone en ceros el gran total
		setGrandTotal(Env.ZERO);
		
		// Si la extension es particular
		if (getBpartner() != null
				&& !getBpartner().isAseguradora()) {
			final BeanDatosFacturacion datosFactura = mCtaPac
					.getDireccionParticular();

			if (datosFactura != null) {
				setRfc(datosFactura.getRfc());

				if (!dataRecord && datosFactura.getName() != null
						&& !datosFactura.getName().isEmpty()) {
					setDescription(datosFactura.getName());// Responsable
				}

				if (getC_Location_ID() > 0) {
					// MLocation copyFrom(Properties ctx, int originId, int
					// C_Location_ID, String trxName)
					MLocation loc = MEXMELocation.copyFrom(
							mCtaPac.getCtx(),
							datosFactura.getC_Location_ID(),
							getC_Location_ID(), null);
					loc.save();
				}
			} else {
				setRfc(paciente.getRfc());
			}
		}

		return save(null); 
	}
	
	/** Update TotalLines */
	public void updateTotals() throws SQLException{
		
		BigDecimal totalLines = BigDecimal.ZERO;
		
		for (MCtaPacDet det : MCtaPacDet.getCargosByExt(getCtx(),getEXME_CtaPacExt_ID(), get_TrxName())) {
			totalLines = totalLines.add(det.getLineNetAmt());
		}
		
		setTotalLines(totalLines);
		setGrandTotal(totalLines);
		
	}
	
	
	public void cleanDiscount(){
		if(getExtensionNo()==0){
			setDiscountTaxAmt(Env.ZERO);
			setDesctoGlobal(Env.ZERO);
			setDesctoGlobalAmt(Env.ZERO);
		}
	}
	

	public int validateTypeline(int ctapac_id, String tipolinea){
		int refID = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_ctapacdet_id ");
		sql.append("FROM ");
		sql.append("  exme_ctapacdet ");
		sql.append("  AS ecd ");
		sql.append("  INNER JOIN exme_ctapacext ");
		sql.append("  AS ece ");
		sql.append("  ON (ece.exme_ctapacext_id = ecd.exme_ctapacext_id) ");
		sql.append("WHERE ");
		sql.append("  ecd.tipolinea = ? AND ");
		sql.append("  ecd.exme_ctapacext_ID = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, tipolinea);
			pstmt.setInt(2, ctapac_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refID = rs.getInt(1);
				
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return  refID;
		
	}
	
	public int validateFactura(int refID){
		int val = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COALESCE(c_invoice_id, ");
		sql.append("  0) ");
		sql.append(" FROM ");
		sql.append("  exme_ctapacdet ");
		sql.append("  AS ecd ");
		sql.append("  INNER JOIN exme_ctapacext ");
		sql.append("  AS ece ");
		sql.append("  ON (ece.exme_ctapacext_id = ecd.exme_ctapacext_id) ");
		sql.append(" WHERE ");
		sql.append("  ecd.ref_ctapacdet_id = ? ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setInt(1, refID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				val = rs.getInt(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return  val;
		
	}
	
	//CARD #1299
	public MCtaPacDet[] getLineasDeExtension(final boolean incluirDescuentos, final boolean saveInvoice) {
		try {
			cargarLineasDeExtension(incluirDescuentos, saveInvoice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getLineasDeExtension();
	}
	
	public MEXMEAnticipo getMAnticipo() {
		if(mAnticipo==null){
			mAnticipo = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPacExt_ID(), null);
		}
		return mAnticipo;
	}
	
	/**
	 * Obtenemos el monto total de anticipos.
	 */
	public BigDecimal getAmountOfPaymentAdvance () {
		if(super.getAnticipo()==Env.ZERO){
			final BigDecimal anticipo = MEXMECtaPacPag.getPayAmtAllocated(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
			if (anticipo == null) {
				return Env.ZERO;
			} else if (anticipo.compareTo(getGrandTotal())>0) {
				return getGrandTotal();
			} else {
				return anticipo;
			}
		}
		return super.getAnticipo();
	}
	
	/** Extensiones de la cuenta paciente */
	public static  List<ValueNamePair> getExtensionesVN(Properties ctx, int EXME_CtaPac_ID, String whereClause, String trxName) {
		final StringBuilder sql = new StringBuilder() 
		.append(" SELECT ").append(COLUMNNAME_EXME_CtaPacExt_ID).append(",").append(COLUMNNAME_ExtensionNo)
		.append(" FROM   ").append(Table_Name)
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name))
		.append(" AND EXME_CtaPac_ID = ? ")
		.append(whereClause==null?StringUtils.EMPTY:whereClause);
		return Query.getListVN(sql.toString(), null, EXME_CtaPac_ID);
	}
	
	/** Actualizar mientras no haya sido facturado */
	private void updateAmtExtension(){
		if(getC_Invoice_ID()<=0 && !is_ValueChanged(COLUMNNAME_C_Invoice_ID)){
			BeanView oBeanView = getExtensionTotal();
			if(getTotalLines().compareTo(Env.ZERO)==0){
				setTotalLines(oBeanView.getDcimal()==null?Env.ZERO:oBeanView.getDcimal());
			}
			if(getTaxAmt() .compareTo(Env.ZERO)==0){
				setTaxAmt(oBeanView.getDcimal2()==null?Env.ZERO:oBeanView.getDcimal2());
			}

			if(getGrandTotal().compareTo(Env.ZERO)==0){
				setGrandTotal();
			}
			if(getAnticipo().compareTo(Env.ZERO)==0){
				setAnticipo(getAmountOfPaymentAdvance ());
			}
		}
	}
	
	/** Actualizacion a base de datos */
	public boolean updateTotalsExt(final BeanConcepts bean) {
		if(bean==null){
			final BeanView oBeanView = getExtensionTotal();
			if(oBeanView!=null){
				setTotalLines(oBeanView.getDcimal()==null?Env.ZERO:oBeanView.getDcimal());
				setTaxAmt(oBeanView.getDcimal2()==null?Env.ZERO:oBeanView.getDcimal2());
				setGrandTotal();
			}
		} else {
			try {
				setSumTotal(bean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return save();
	}
	
	/** Total de anticipos */
	public BigDecimal getTotalAnticipo() {
		if(totalAnticipos==null || totalAnticipos.compareTo(Env.ZERO)==0){
			totalAnticipos = MEXMEAnticipo.getSumAplicado(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
		}
		return totalAnticipos;
	}
	
	/** Nota de remision de la extension */
	public MInvoice getNoteSales(){
		if(mNoteSales==null){
			if(getC_Invoice_ID()>0 && X_C_Invoice.DOCSTATUS_WaitingConfirmation.equals(getInvoice().getDocStatus())){
				mNoteSales = m_invoice;
			}
		}
		return mNoteSales;
	}
	
	/** Factura de la extension */
	public MInvoice getMInvoice(){
		if(mDocInvoice==null){
			if(getC_Invoice_ID()>0 && getNoteSales()==null){
				// Factura sin nota de remision
				mDocInvoice = getInvoice();

			} else {
				// Factura normal o factura global
				mDocInvoice = getInvoice().existInvoiceTarget(get_TrxName());
			}
		}
		return mDocInvoice;
	}
	
	/** Informacion de la factura o de la remision */
	public ValueNamePair getInfoDoc(){
		String docInvoiceNo = null;
		String docInvoiceType = null;
		//final MInvoice mInvoice = existInvoiceTarget(get_TrxName());
		/*if(getC_Invoice_ID()>0 && getMInvoice()!=null){
			docInvoiceNo = getMInvoice().getDocumentNo();
			docInvoiceType = Utilerias.getLabel("msj.factura");

		} else if(getC_Invoice_ID()>0 && getNoteSales()!=null){
			docInvoiceNo = getNoteSales().getDocumentNo();
			docInvoiceType = Utilerias.getLabel("msj.notaRemision");
		}*/
		
		
		if(getMInvoice() != null){
			if (getC_Invoice_ID()>0 && getMInvoice().getDocumentNo() != null) {
				// Es nota de remision
				docInvoiceType = Utilerias.getLabel("msj.notaRemision");
				docInvoiceNo = getMInvoice().getDocumentNo();
				
				// Es factura
				final MInvoice mInvoice = getMInvoice().existInvoiceTarget(get_TrxName());
				if(mInvoice!=null){
					docInvoiceType = Utilerias.getLabel("msj.factura");
					docInvoiceNo = mInvoice.getDocumentNo();
				}
			}
		}
		
		docInvoiceNo = docInvoiceNo==null?StringUtils.EMPTY:docInvoiceNo;
		docInvoiceType = docInvoiceType==null?StringUtils.EMPTY:docInvoiceType;
		return new ValueNamePair(docInvoiceNo,docInvoiceType);
	}
	
//	/**
//	 * Obtengo un arreglo de MCtaPacDet donde correspondan a la extencion que
//	 * se requiere
//	 * 
//	 * @param cadena
//	 * @return MCtaPacDet[]
//	 * @throws SQLException 
//	 **/	//
//	public List<TaxConcepts> breakDownOfTaxes()  {
//
//		final List<TaxConcepts> list = new ArrayList<TaxConcepts>();
//		final StringBuilder sql = new StringBuilder();
//
//		sql.append(" SELECT C_Tax_ID, SUM(COALESCE(LineNetAmt,0)) , SUM(COALESCE(TaxAmt,0)) ")
//		.append(" FROM  EXME_CtaPacDet ")
//		.append(" WHERE EXME_CtaPacDet.IsActive= 'Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ","EXME_CtaPacDet"))
//		.append(" AND   EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
//		.append(MEXMECtaPacExt.condicionCargos ())
//		.append(" GROUP BY C_Tax_ID ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			pstmt.setInt(1, getEXME_CtaPacExt_ID());
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final TaxConcepts bean = new TaxConcepts();
//				bean.setTasa(rs.getInt(1));
//				bean.setBase(rs.getBigDecimal(2));
//				bean.setImpuesto(rs.getBigDecimal(3));
//				list.add(bean);
//			}
//
//		} catch (Exception e) {    		
//    		s_log.log(Level.SEVERE, sql.toString(), e);
//    	}finally {
//    		DB.close(rs,pstmt);
//    	}
//		return list;
//	}
	
	
	/** Obtengo los saldos por extension */
//	 * 
//	 * 
//	 * 
//	 * @param cadena
//	 * @return MCtaPacDet[]
//	 * @throws SQLException 
//	 **/	//
	public BeanView getExtensionTotal()  {

		final StringBuilder sql = new StringBuilder();
		final BeanView bean = new BeanView();
		sql.append(" SELECT EXME_CtaPacExt_ID, SUM(COALESCE(LineNetAmt,0)) , SUM(COALESCE(TaxAmt,0)) ")
		.append(" FROM  EXME_CtaPacDet ")
		.append(" WHERE EXME_CtaPacDet.IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ","EXME_CtaPacDet"))
		.append(" AND   EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
		.append(MEXMECtaPacExt.condicionCargos ())
		.append(" GROUP BY EXME_CtaPacDet.EXME_CtaPacExt_ID ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPacExt_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean.setDcimal(rs.getBigDecimal(2));
				bean.setDcimal2(rs.getBigDecimal(3));
			}

		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}

		return bean;

	}
	
	/**
	 * Listado de Impuestos de la extension
	 * @return
	 */
	public List<TaxConcepts> breakDownOfTaxes()  {

		final List<TaxConcepts> list = new ArrayList<TaxConcepts>();
		final StringBuilder sql = new StringBuilder();
		final MConfigPre configPre = MConfigPre.get(getCtx(), get_TrxName());
		
		sql.append(" SELECT C_Tax_ID, SUM(COALESCE(LineNetAmt,0)) , SUM(COALESCE(TaxAmt,0)) ")
		.append(" FROM  EXME_CtaPacDet ")
		.append(" WHERE EXME_CtaPacDet.IsActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ","EXME_CtaPacDet"))
		.append(" AND   EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
		.append(MEXMECtaPacExt.condicionCargos ())

		.append(" AND   EXME_CtaPacDet.M_Product_ID NOT IN (      ")
		.append(configPre.getCoaseguro_ID()).append("   , ")
		.append(configPre.getDeducible_ID()).append("   , ")
		.append(configPre.getCoaseguroMed_ID()).append(", ")
		.append(configPre.getCoPago_ID()).append(" )      ")

		.append(" GROUP BY C_Tax_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPacExt_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final TaxConcepts bean = new TaxConcepts();
				bean.setTasa(rs.getInt(1));
				bean.setBase(rs.getBigDecimal(2));
				bean.setImpuesto(rs.getBigDecimal(3));
				list.add(bean);
			}

		} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
		return list;
	}
}