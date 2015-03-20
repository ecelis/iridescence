package org.compiere.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Env;

/**
 * Datos necesarios para hacer el calculo de CCCmD y descuentos
 * @author twry
 *
 */
public class BeanConcepts {
	/** Aplica el descuento a particular */
	private boolean descPart = false;
	/** Socio de negocios considerado como aseguradora */
	public MEXMEBPartner objSocio = null;
	/** Socio de negocios considerado como particular */
	public MEXMEBPartner objSocioP = null;
	/** extension que ve el usuario podria tener relacionada una aseguradora */
	private MEXMECtaPacExt mCtaPacExt;
	/** extension del particular */
	private MEXMECtaPacExt mCtaPacExtP;
	/** lista de cargos */
	private List<MCtaPacDet> charges = new ArrayList<MCtaPacDet>();
	/** lista de cargos */
	private List<MCtaPacDet> chargesPart = new ArrayList<MCtaPacDet>();	
	/** lista de TODOS los cargos */
	private List<MCtaPacDet> chargesAll = new ArrayList<MCtaPacDet>();
	/** Total de la suma de los cargos (P) */
	protected BigDecimal totalVtaP = Env.ZERO;
	/** Total de la suma de los cargos (A) */
	protected BigDecimal totalVta = Env.ZERO;
	/** Total de la suma de los cargos (A) */
	protected BigDecimal totalVtaOrig = Env.ZERO;
	/** Despues de descuento */
	private BigDecimal subtotal = Env.ZERO;
	/** Impuestos */
	protected BigDecimal impuesto = Env.ZERO;
	/** Impuestos */
	private BigDecimal impuestoP = Env.ZERO;
	/** Total con impuestos */
	protected BigDecimal grantotal = Env.ZERO;
	/** Subtotal 1 CCCmD */
	private BigDecimal subtotalD = Env.ZERO;
	/** Subtotal 2 CCCmD */
	private BigDecimal subtotalC = Env.ZERO;
	/** Subtotal 3 CCCmD */
	private BigDecimal subtotalDm = Env.ZERO;
	/** Subtotal 4 CCCmD */
	private BigDecimal subtotalCo = Env.ZERO;
	/** Descuentos */
	protected BeanAmts descuento = new BeanAmts();
	/** Descuentos */
	private BeanAmts descuentoPar = new BeanAmts();
	/** Deducible */
	protected BeanAmts deducible =  new BeanAmts();
	/** Coaseguro */
	private BeanAmts coaseguro = new BeanAmts();
	/** Coaseguro medico */
	private BeanAmts coaseguroMed = new BeanAmts();
	/** Copago */
	private BeanAmts copago    = new BeanAmts();
	/** Configuracion de precios */
	private MEXMEConfigPre confPre = null;
	/** Numero de factura */
	private int factura = 0;
	/** Numero de factura del particular */
	private int facturaP = 0;
	/** Impuestos */
	protected BigDecimal impuestoGT = Env.ZERO;
	
	
	private BigDecimal mTxtPSumCargo = Env.ZERO;
	private BigDecimal mTxtPSubTotal = Env.ZERO;
	private BigDecimal mTxtPSubTotalConDesc = Env.ZERO;
	private BigDecimal mTxtPiva = Env.ZERO;
	private BigDecimal mTxtPivaConDesc = Env.ZERO;
	private BigDecimal mTxtPTotal = Env.ZERO;
	
	

	public BigDecimal getmTxtPSubTotalConDesc() {
		return mTxtPSubTotalConDesc;
	}
	public void setmTxtPSubTotalConDesc(BigDecimal mTxtPSubTotalConDesc) {
		this.mTxtPSubTotalConDesc = mTxtPSubTotalConDesc;
	}
	public BigDecimal getmTxtPivaConDesc() {
		return mTxtPivaConDesc;
	}
	public void setmTxtPivaConDesc(BigDecimal mTxtPivaAll) {
		this.mTxtPivaConDesc = mTxtPivaAll;
	}
	public BigDecimal getmTxtPSumCargo() {
		return mTxtPSumCargo;
	}
	public void setmTxtPSumCargo(BigDecimal mTxtPSumCargo) {
		this.mTxtPSumCargo = mTxtPSumCargo;
	}
	public BigDecimal getmTxtPSubTotal() {
		return mTxtPSubTotal;
	}
	public void setmTxtPSubTotal(BigDecimal mTxtPSubTotal) {
		this.mTxtPSubTotal = mTxtPSubTotal;
	}
	public BigDecimal getmTxtPiva() {
		return mTxtPiva;
	}
	public void setmTxtPiva(BigDecimal mTxtPiva) {
		this.mTxtPiva = mTxtPiva;
	}
	public BigDecimal getmTxtPTotal() {
		return mTxtPTotal;
	}
	public void setmTxtPTotal(BigDecimal mTxtPTotal) {
		this.mTxtPTotal = mTxtPTotal;
	}
	
	/**
	 * Constructor
	 */
	public BeanConcepts(){
		confPre = MEXMEConfigPre.get(Env.getCtx(), null);
	}
	
	/**
	 * Inicializa
	 */
	public void clean(){
		descPart = false;
		objSocio = null;
		objSocioP = null;
		charges = new ArrayList<MCtaPacDet>();
		chargesPart = new ArrayList<MCtaPacDet>();
		chargesAll = new ArrayList<MCtaPacDet>();
		totalVtaP = Env.ZERO;
		totalVta = Env.ZERO;
		totalVtaOrig = Env.ZERO;
		subtotal = Env.ZERO;
		impuesto = Env.ZERO;
		grantotal = Env.ZERO;
		impuestoGT = Env.ZERO;
		
		subtotalD = Env.ZERO;
		subtotalC = Env.ZERO;
		subtotalDm = Env.ZERO;
		subtotalCo = Env.ZERO;
		
		descuento.clean();
		deducible.clean();
		coaseguro.clean();
		coaseguroMed.clean();
		copago.clean();
	}
	
	public void cleanAmt(){
		totalVtaP = Env.ZERO;
		totalVta = Env.ZERO;
		totalVtaOrig = Env.ZERO;
		subtotal = Env.ZERO;
		impuesto = Env.ZERO;
		grantotal = Env.ZERO;
		impuestoGT = Env.ZERO;
		
		subtotalD = Env.ZERO;
		subtotalC = Env.ZERO;
		subtotalDm = Env.ZERO;
		subtotalCo = Env.ZERO;
	}
	/** (Directa y Extensiones) */
	public void cleanBeanAmts(final boolean isDiscount){
		if(isDiscount){
			descuento.clean();
			descuentoPar.clean();
		}
		deducible.clean();
		coaseguro.clean();
		coaseguroMed.clean();
		copago.clean();
	}
	
/******************************************************/	
	public MEXMEConfigPre getConfPre() {
		return confPre;
	}
	public void setConfPre(final MEXMEConfigPre confPre) {
		this.confPre = confPre;
	}
	
	public boolean isDescPart() {
		return descPart;
	}
	public void setDescPart(final boolean descPart) {
		this.descPart = descPart;
	}
	public MEXMEBPartner getObjSocio() {
		return objSocio;
	}
	public void setObjSocio(final MEXMEBPartner objSocio) {
		this.objSocio = objSocio;
	}
	public BeanAmts getDescuento() {
		return descuento;
	}
	public void setDescuento(final BeanAmts descuento) {
		this.descuento = descuento;
	}
	public BeanAmts getDescuentoPar() {
		return descuentoPar;
	}

	public void setDescuentoPar(BeanAmts descuentoPar) {
		this.descuentoPar = descuentoPar;
	}
	public BeanAmts getDeducible() {
		return deducible;
	}
	public void setDeducible(final BeanAmts deducible) {
		this.deducible = deducible;
	}
	public BeanAmts getCoaseguro() {
		return coaseguro;
	}
	public void setCoaseguro(final BeanAmts coaseguro) {
		this.coaseguro = coaseguro;
	}
	public BeanAmts getCoaseguroMed() {
		return coaseguroMed;
	}
	public void setCoaseguroMed(final BeanAmts coaseguroMed) {
		this.coaseguroMed = coaseguroMed;
	}
	public BeanAmts getCopago() {
		return copago;
	}
	public void setCopago(final BeanAmts copago) {
		this.copago = copago;
	}

	public List<MCtaPacDet> getCharges() {
		return charges;
	}
	public void setCharges(final List<MCtaPacDet> charges) {
		this.charges = charges;
	}
	public BigDecimal getTotalVta() {
		return totalVta;
	}
	public void setTotalVta(BigDecimal totalVta) {
		this.totalVta = totalVta;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(final BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(final BigDecimal impuesto) {
		this.impuesto = impuesto;
	}
	public BigDecimal getSubtotalD() {
		return subtotalD;
	}
	public void setSubtotalD(final BigDecimal subtotalD) {
		this.subtotalD = subtotalD;
	}
	public BigDecimal getSubtotalC() {
		return subtotalC;
	}
	public void setSubtotalC(final BigDecimal subtotalC) {
		this.subtotalC = subtotalC;
	}
	public BigDecimal getSubtotalDm() {
		return subtotalDm;
	}
	public void setSubtotalDm(final BigDecimal subtotalDm) {
		this.subtotalDm = subtotalDm;
	}
	public BigDecimal getSubtotalCo() {
		return subtotalCo;
	}
	public void setSubtotalCo(final BigDecimal subtotalCo) {
		this.subtotalCo = subtotalCo;
	}
	public BigDecimal getGrantotal() {
		return grantotal;
	}
	public void setGrantotal(final BigDecimal grantotal) {
		this.grantotal = grantotal;
	}
	public BigDecimal getTotalVtaP() {
		return totalVtaP;
	}
	public void setTotalVtaP(final BigDecimal totalVtaP) {
		this.totalVtaP = totalVtaP;
	}
	public MEXMEBPartner getObjSocioP() {
		return objSocioP;
	}
	public void setObjSocioP(final MEXMEBPartner objSocioP) {
		this.objSocioP = objSocioP;
	}
	public MEXMECtaPacExt getmCtaPacExt() {
		return mCtaPacExt;
	}

	public void setmCtaPacExt(MEXMECtaPacExt mCtaPacExt) {
		this.mCtaPacExt = mCtaPacExt;
	}

	public MEXMECtaPacExt getmCtaPacExtP() {
		return mCtaPacExtP;
	}

	public void setmCtaPacExtP(MEXMECtaPacExt mCtaPacExtP) {
		this.mCtaPacExtP = mCtaPacExtP;
	}
	
	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getFacturaP() {
		return facturaP;
	}

	public void setFacturaP(int facturaP) {
		this.facturaP = facturaP;
	}
	public BigDecimal getImpuestoP() {
		return impuestoP;
	}

	public void setImpuestoP(BigDecimal impuestoP) {
		this.impuestoP = impuestoP;
	}
	public List<MCtaPacDet> getChargesPart() {
		return chargesPart;
	}

	public void setChargesPart(List<MCtaPacDet> chargesPart) {
		this.chargesPart = chargesPart;
	}
	
	public List<MCtaPacDet> getChargesAll() {
		return chargesAll;
	}

	public void setChargesAll(List<MCtaPacDet> chargesAll) {
		this.chargesAll = chargesAll;
	}
	public BigDecimal getTotalVtaOrig() {
		return totalVtaOrig;
	}

	public void setTotalVtaOrig(BigDecimal totalVtaOrig) {
		this.totalVtaOrig = totalVtaOrig;
	}
	public BigDecimal getImpuestoGT() {
		return impuestoGT;
	}

	public void setImpuestoGT(BigDecimal impuestoGT) {
		this.impuestoGT = impuestoGT;
	}
	
	
	/**
	 * Montos
	 * @author twry
	 *
	 */
	public class BeanAmts {
		
		private BigDecimal montoCaptura = Env.ZERO;
		private BigDecimal montoCalculado = Env.ZERO;
		private BigDecimal montoPositivo = Env.ZERO;
		private BigDecimal montoNegativo = Env.ZERO;
		private BigDecimal montoImpuesto = Env.ZERO;
		private boolean esPorcentaje = true;
		
		/**
		 * Inicializar
		 */
		public void clean(){
			montoCaptura = Env.ZERO;
			montoCalculado = Env.ZERO;
			montoPositivo = Env.ZERO;
			montoNegativo = Env.ZERO;
			montoImpuesto = Env.ZERO;
			esPorcentaje = true;
		}
		
/******************************************************/		
		public BigDecimal getMontoCaptura() {
			return montoCaptura;
		}
		public void setMontoCaptura(final BigDecimal montoCaptura) {
			this.montoCaptura = montoCaptura;
		}
		public BigDecimal getMontoCalculado() {
			return montoCalculado;
		}
		public void setMontoCalculado(final BigDecimal montoCalculado) {
			this.montoCalculado = montoCalculado;
		}
		public BigDecimal getMontoPositivo() {
			return montoPositivo;
		}
		public void setMontoPositivo(final BigDecimal montoPositivo) {
			this.montoPositivo = montoPositivo;
		}
		public BigDecimal getMontoNegativo() {
			return montoNegativo;
		}
		public void setMontoNegativo(final BigDecimal montoNegativo) {
			this.montoNegativo = montoNegativo;
		}
		public BigDecimal getMontoImpuesto() {
			return montoImpuesto;
		}
		public void setMontoImpuesto(final BigDecimal montoImpuesto) {
			this.montoImpuesto = montoImpuesto;
		}
		public boolean isEsPorcentaje() {
			return esPorcentaje;
		}
		public void setEsPorcentaje(final boolean esPorcentaje) {
			this.esPorcentaje = esPorcentaje;
		}
	}
	
	
	/**
	 * Saldos de la extension
	 * @param plineas: Lineas
	 * @param isInsurance: Si es aseguradora o no
	 * @param reload: true Si se quiere actualizar desde los datos de la lista o false si no y solo suma totales
	 * @param beanConcp: Bean donde se guardaran los resultados
	 * @return BeanConcepts actualizado con los totales
	 * @throws SQLException
	 */
	public BeanConcepts setSumTotalBeanAmts(final List<MCtaPacDet> plineas, final boolean isDiscount, 
			final MEXMECtaPacExt mCtaPacExt, final boolean isInsurance, final boolean reload, final boolean isCCCmD) throws SQLException {
		List<MCtaPacDet> lineas;
		BigDecimal totalsVtas = Env.ZERO;
		
		if(plineas==null || plineas.isEmpty()){
			lineas = mCtaPacExt.getLstCargos(MEXMECtaPacExt.condicionCargos());//desc
		} else {
			lineas=plineas;// cccmd
		}
		
		cleanAmt();
		if(reload){
			cleanBeanAmts(isDiscount || isCCCmD);
		}
		
		if(reload || !isDiscount){
			descuento.setMontoNegativo(Env.ZERO);
			descuento.setMontoPositivo(Env.ZERO);
			descuento.setMontoImpuesto(Env.ZERO);
			descuento.setMontoCaptura (Env.ZERO);
			descuento.setMontoCalculado(Env.ZERO);
		}
		
		for (final MCtaPacDet bean: lineas) {
			
			if(reload && X_EXME_CtaPacDet.TIPOLINEA_Coaseguro.equals(bean.getTipoLinea())){
				coaseguro.setMontoNegativo(coaseguro.getMontoNegativo().add(bean.getLineNetAmt()));
				coaseguro.setMontoPositivo(coaseguro.getMontoPositivo().add(bean.getLineNetAmt()));
				coaseguro.setMontoImpuesto(coaseguro.getMontoImpuesto().add(bean.getTaxAmt()));
				coaseguro.setMontoCaptura (coaseguro.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				coaseguro.setMontoCalculado(coaseguro.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico.equals(bean.getTipoLinea())){
				coaseguroMed.setMontoNegativo(coaseguroMed.getMontoNegativo().add(bean.getLineNetAmt()));
				coaseguroMed.setMontoPositivo(coaseguroMed.getMontoPositivo().add(bean.getLineNetAmt()));
				coaseguroMed.setMontoImpuesto(coaseguroMed.getMontoImpuesto().add(bean.getTaxAmt()));
				coaseguroMed.setMontoCaptura (coaseguroMed.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				coaseguroMed.setMontoCalculado(coaseguroMed.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_Copago.equals(bean.getTipoLinea())){
				copago.setMontoNegativo(copago.getMontoNegativo().add(bean.getLineNetAmt()));
				copago.setMontoPositivo(copago.getMontoPositivo().add(bean.getLineNetAmt()));
				copago.setMontoImpuesto(copago.getMontoImpuesto().add(bean.getTaxAmt()));
				copago.setMontoCaptura (copago.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				copago.setMontoCalculado(copago.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_Deducible.equals(bean.getTipoLinea())){
				deducible.setMontoNegativo(deducible.getMontoNegativo().add(bean.getLineNetAmt()));
				deducible.setMontoPositivo(deducible.getMontoPositivo().add(bean.getLineNetAmt()));
				deducible.setMontoImpuesto(deducible.getMontoImpuesto().add(bean.getTaxAmt()));
				deducible.setMontoCaptura (deducible.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				deducible.setMontoCalculado(deducible.getMontoCalculado ().add(bean.getLineNetAmt()));

				impuestoGT=impuestoGT.add(bean.getTaxAmt());//
			} else {
				if(reload || !isDiscount){
					// Descuento coaseDeducDesc.getDescuento().getMontoDbxPos()
					descuento.setMontoNegativo(descuento.getMontoNegativo().add(bean.getOverallDiscountAdj()));
					descuento.setMontoPositivo(descuento.getMontoPositivo().add(bean.getOverallDiscountAdj()));
					descuento.setMontoImpuesto(descuento.getMontoImpuesto().add(bean.getDiscountTaxAmt(true)));
					descuento.setMontoCaptura (descuento.getMontoCaptura ().add(bean.getOverallDiscountAdj().add(bean.getDiscountTaxAmt(true))));
					descuento.setMontoCalculado(descuento.getMontoCalculado().add(bean.getOverallDiscountAdj()));
				}
				
				// La suma de todos los cargos con convenio
				totalVtaOrig=totalVtaOrig.add(bean.getLineGrossAmt());
				// La suma de imp. de todos los cargos con convenio
				impuesto = impuesto.add(bean.getTaxAmt()).add(bean.getDiscountTaxAmt(true));
				// La suma de imp. todos los cargos con convenio, desc global, cccmd
				impuestoGT=impuestoGT.add(bean.getTaxAmt());//
			}
			
			// La suma de todos los cargos con convenio, desc global, cccmd
			if(bean.getLineNetAmt().compareTo(new BigDecimal(70440))>= 0){
				totalsVtas = totalsVtas.add(bean.getLineNetAmt());//
			} else {
				totalsVtas = totalsVtas.add(bean.getLineNetAmt());
			}
			totalVta = totalVta.add(bean.getLineNetAmt());
			totalVtaP= totalVtaP.add(bean.getLineNetAmt());
		}
		BigDecimal descTotal = descuento.getMontoNegativo();//
		BigDecimal lcTotalVta = totalVta;//
		BigDecimal lcImpuestoGT = impuestoGT;//
		BigDecimal lcGrantotal = lcTotalVta.add(lcImpuestoGT);//12263.528000
		// Total
		grantotal = lcGrantotal;//
		return this;
	}

	/**
	 * Saldos de la extension
	 * @param plineas: Lineas
	 * @param isInsurance: Si es aseguradora o no
	 * @param reload: true Si se quiere actualizar desde los datos de la lista o false si no y solo suma totales
	 * @param beanConcp: Bean donde se guardaran los resultados
	 * @return BeanConcepts actualizado con los totales
	 * @throws SQLException
	 */
	public BeanConcepts setSumTotalBeanAmts(final List<MCtaPacDet> plineas, final boolean isDiscount, 
			final boolean isInsurance, final boolean reload, final int numext, final boolean isCCCmD) throws SQLException {
		List<MCtaPacDet> lineas;
		if(plineas==null || plineas.isEmpty()){
			lineas = mCtaPacExt.getLstCargos(MEXMECtaPacExt.condicionCargos());//desc
		} else {
			lineas=plineas;// cccmd
		}
		
		cleanAmt();
		if(reload){
			cleanBeanAmts(isDiscount || isCCCmD);
		}
		
		if(reload || !isDiscount){
			descuento.setMontoNegativo(Env.ZERO);
			descuento.setMontoPositivo(Env.ZERO);
			descuento.setMontoImpuesto(Env.ZERO);
			descuento.setMontoCaptura (Env.ZERO);
			descuento.setMontoCalculado(Env.ZERO);
		}
		
		for (final MCtaPacDet bean: lineas) {
			
			if(reload && X_EXME_CtaPacDet.TIPOLINEA_Coaseguro.equals(bean.getTipoLinea())){
				coaseguro.setMontoNegativo(coaseguro.getMontoNegativo().add(bean.getLineNetAmt()));
				coaseguro.setMontoPositivo(coaseguro.getMontoPositivo().add(bean.getLineNetAmt()));
				coaseguro.setMontoImpuesto(coaseguro.getMontoImpuesto().add(bean.getTaxAmt()));
				coaseguro.setMontoCaptura (coaseguro.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				coaseguro.setMontoCalculado(coaseguro.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_CoaseguroMedico.equals(bean.getTipoLinea())){
				coaseguroMed.setMontoNegativo(coaseguroMed.getMontoNegativo().add(bean.getLineNetAmt()));
				coaseguroMed.setMontoPositivo(coaseguroMed.getMontoPositivo().add(bean.getLineNetAmt()));
				coaseguroMed.setMontoImpuesto(coaseguroMed.getMontoImpuesto().add(bean.getTaxAmt()));
				coaseguroMed.setMontoCaptura (coaseguroMed.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				coaseguroMed.setMontoCalculado(coaseguroMed.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_Copago.equals(bean.getTipoLinea())){
				copago.setMontoNegativo(copago.getMontoNegativo().add(bean.getLineNetAmt()));
				copago.setMontoPositivo(copago.getMontoPositivo().add(bean.getLineNetAmt()));
				copago.setMontoImpuesto(copago.getMontoImpuesto().add(bean.getTaxAmt()));
				copago.setMontoCaptura (copago.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				copago.setMontoCalculado(copago.getMontoCalculado ().add(bean.getLineNetAmt()));
			
				impuestoGT=impuestoGT.add(bean.getTaxAmt());
				
			} else if(reload && X_EXME_CtaPacDet.TIPOLINEA_Deducible.equals(bean.getTipoLinea())){
				deducible.setMontoNegativo(deducible.getMontoNegativo().add(bean.getLineNetAmt()));
				deducible.setMontoPositivo(deducible.getMontoPositivo().add(bean.getLineNetAmt()));
				deducible.setMontoImpuesto(deducible.getMontoImpuesto().add(bean.getTaxAmt()));
				deducible.setMontoCaptura (deducible.getMontoCaptura().add(bean.getLineNetAmt().add(bean.getTaxAmt())));
				deducible.setMontoCalculado(deducible.getMontoCalculado ().add(bean.getLineNetAmt()));

				impuestoGT=impuestoGT.add(bean.getTaxAmt());//-48.00
			} else {
				if(reload || !isDiscount){
					// Descuento coaseDeducDesc.getDescuento().getMontoDbxPos()
					descuento.setMontoNegativo(descuento.getMontoNegativo().add(bean.getOverallDiscount()));
					descuento.setMontoPositivo(descuento.getMontoPositivo().add(bean.getOverallDiscount()));
					descuento.setMontoImpuesto(descuento.getMontoImpuesto().add(bean.getDiscountTaxAmt(true)));
					descuento.setMontoCaptura (descuento.getMontoCaptura ().add(bean.getOverallDiscount().add(bean.getDiscountTaxAmt(true))));
					descuento.setMontoCalculado(descuento.getMontoCalculado().add(bean.getOverallDiscount()));
				}

				// La suma de todos los cargos con convenio
				totalVtaOrig=totalVtaOrig.add(bean.getLineGrossAmt());
				// La suma de imp. de todos los cargos con convenio
				impuesto = impuesto.add(bean.getTaxAmt()).add(bean.getDiscountTaxAmt(true));
				// La suma de imp. todos los cargos con convenio, desc global, cccmd
				impuestoGT=impuestoGT.add(bean.getTaxAmt());//432
			}
			
			// La suma de todos los cargos con convenio, desc global, cccmd
			if(numext==1){ totalVta = totalVta.add(bean.getLineNetAmt()); }
			if(numext==0){ totalVtaP= totalVtaP.add(bean.getLineNetAmt());}
		}
		// Total
		grantotal = totalVta.add(impuestoGT); 
		return this;
	}
}