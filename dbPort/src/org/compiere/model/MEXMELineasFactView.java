package org.compiere.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * @deprecated
 * @author mvrodriguez
 *
 */
public class MEXMELineasFactView {

//	public static int EXT_PARTICULAR = 0;
//    public static int EXT_ASEGURADORA = 1;
//    
//    public static int LINEA_DE_PRODUCTO = 1;
//    public static int LINEA_DE_CITAMEDICA = 2;
//    public static int LINEA_DE_PRODUCTO_SOL = 3;
//    public static int LINEA_DE_CARGO = 4;
//    public static int LINEA_DE_COASDEDUC = 5;
//    
//    private MPrecios  precio = null;
//    private MEXMEInvoiceLine lineaInvoice = null;
//	private MEXMEActPacienteInd actPacienteInd = null;
//
//	//Tipo de linea
//    private int lineaDe = LINEA_DE_PRODUCTO; 
// 	//Numero de linea
//    private int linea = 0;
//    //Aseguradora o particular
//    private int extension = 0;
//    //Producto
//    private int m_Product_ID = 0;
//    //Cargo
//    private int c_Charge_ID = 0;
//    //Almacen
//    private int m_Warehouse_ID = 0;
//    //uom
//    private int c_UOM_ID = 0;
//
//    //Codigo del producto o codigo especial
//	private String productValue = null;
//	//Nombre del producto o Descripcion del producto 
//    private String productName = null;
//    //Tipo de producto
//    private String productType= null;
//    //Nombre del almacen que surte 
//    private String warehouseName = null;
//    //Unidad de medida
//    private String uomName = null;
//    //Cantidad a facturar
//    private BigDecimal cantidad = Env.ZERO;
//    //Incremento o sobreprecio
//    private BigDecimal sobrePrecio = Env.ZERO;
//    //Precio de lista
//    private BigDecimal precioLista = Env.ZERO;
//    //Descuento porcentaje
//    private BigDecimal discount = Env.ZERO;
//    //Descuento cantidad
//    private BigDecimal discountAmt = Env.ZERO;
//    //Precio actual
//    private BigDecimal precioActual = Env.ZERO;
//    //Total linea
//    private BigDecimal totalLine = Env.ZERO;
//    //Impuesto del total de la linea
//    private BigDecimal totalTaxAmt = Env.ZERO;
//    //Descripcion extra
//    private String descripcionExtra = null;
//    //PrecioOriginal
//    private BigDecimal precioOriginal = Env.ZERO;
//    
//    
//    
//    public String getProductType() {
//		return productType;
//	}
//
//	public void setProductType(String productType) {
//		this.productType = productType;
//	}
//
//	public BigDecimal getPrecioOriginal() {
//		return precioOriginal;
//	}
//
//	public void setPriceOriginal(BigDecimal precioOriginal) {
//		this.precioOriginal = precioOriginal;
//	}
//    
//    // Lotes Seleccionados
//    private boolean lotesOk = false;
//    
//    
//    public boolean isLotesOk() {
//		return lotesOk;
//	}
//
//	public void setLotesOk(boolean lotesOk) {
//		this.lotesOk = lotesOk;
//	}
//
//
//	/** Lista de datalles de Lotes por cargo*/
//    private List<MInOutLine> lstSurtidoDet = null;
//
//	public List<MInOutLine> getLstSurtidoDet() {
//		return lstSurtidoDet;
//	}
//
//	public void setLstSurtidoDet(List<MInOutLine> lstSurtidoDet) {
//		this.lstSurtidoDet = lstSurtidoDet;
//	}
    
//    /**
//     * Constructor por defecto mas completo
//     * para producto
//     * @param linea
//     * @param extension
//     * @param warehouseSur
//     * @param product
//     * @param pv
//     * @param uom
//     * @param cantidad
//     * @throws Exception
//     */
//    public MEXMELineasFactView(
//    		int linea,
//    		int extension, 
//    		MWarehouse warehouseSur,  
//    		MProduct product, 
//    		MPrecios pv, 
//    		MUOM uom, 
//    		BigDecimal cantidad,
//    		boolean isBpExento
//    		
//    )
//    
//    	throws Exception{
//
//    	this.linea = linea;
//        this.extension = extension;
//        if(uom!=null){
//        	this.uomName = uom.getName();
//        	this.c_UOM_ID = uom.getC_UOM_ID();
//        }
//        this.m_Product_ID = product.getM_Product_ID();
//        this.productName = product.getName();
//    	this.productValue = product.getValue();
//    	this.productType = product.getProductType();
//    	this.cantidad = cantidad;
//        this.precio = pv;
//        
//        if(warehouseSur!=null){
//        	this.warehouseName = (warehouseSur.getName()== null ? "" : warehouseSur.getName());
//        	this.m_Warehouse_ID = warehouseSur.getM_Warehouse_ID();
//        }
//        
//        // Si el socio de negocios es exento, Agregar linea con total en cero.
//        /*if (isBpExento){
//			if(pv!=null){
//				this.discount = Env.ONEHUNDRED;
//				pv.setSobrePrecio(Env.ZERO);
//				pv.setDiscountPorc(Env.ZERO);
//				pv.setDiscountAmt(Env.ZERO);
//				pv.setPriceList(Env.ZERO);
//				pv.setPriceStd(Env.ZERO);
//			}
//		}*/
//    
//        if(pv!=null){
//        	if(pv.getDescripcion()!=null && pv.getDescripcion().length()>0)
//	        this.productName = pv.getDescripcion(); 
//        	if(pv.getValue()!=null && pv.getValue().length()>0)
//	    	this.productValue = pv.getValue();
//	    	this.sobrePrecio = (pv.getSobrePrecio() == null ? Env.ZERO : pv.getSobrePrecio());//Se muestra en listado al usuario
//	        this.discount = (pv.getDiscountPorc() == null ? Env.ZERO : pv.getDiscountPorc());
//	        this.discountAmt = (pv.getDiscountAmt() == null ? Env.ZERO : pv.getDiscountAmt());//Se muestra en listado al usuario
//	        this.precioLista = (pv.getPriceList() == null ? Env.ZERO : pv.getPriceList());//Se muestra en listado al usuario
//	        this.precioActual = (pv.getPriceStd() == null ? Env.ZERO : pv.getPriceStd());//Se muestra en listado al usuario
//			//Mantiene el precio original sin convenio a precio fijo
//			this.precioOriginal= (pv.getPrecioOriginal() == null ? Env.ZERO : pv.getPrecioOriginal());//Se muestra en listado al usuario
//	        //this.totalLine = pv.getPriceStd().multiply(cantidad);
//	        // if(pv.getTax()!=null)
//	        //	this.totalTaxAmt = (this.totalLine.multiply(pv.getTax().getRate().divide(Env.ONEHUNDRED)));
//	        
//        }
//        
//        // Definir al cargo que los lotes ya han sido seleccionados
//        this.setLotesOk(true);
//    }
//    
    /**
     * Debe ser complementada con la actividad paciente ind 
     * @param linea
     * @param extension
     * @param uom
     * @param warehouseSur
     * @throws Exception
     *

    public MEXMELineasFactView(
    		int linea,
    		int extension, 
    		MUOM uom,
    		MWarehouse warehouseSur
    	) throws Exception {

    	this.linea = linea;
        this.extension = extension;
        if(uom!=null){
        	this.uomName = uom.getName();
        	this.c_UOM_ID = uom.getC_UOM_ID();
        }
        if(warehouseSur!=null){
        	this.warehouseName = (warehouseSur.getName()== null ? "" : warehouseSur.getName());
        	this.m_Warehouse_ID = warehouseSur.getM_Warehouse_ID();
        }
        
    }*/
    
    /**
     * Constructor para el descuento global que aun se maneja como cargo
     * @param charge
     * @param uomName
     * @param EXME_Area_ID
     * @param extension
     * @param warehouseSur
     * @param numLinea
     * @param pv
     * @throws Exception
     */
//    public MEXMELineasFactView(MCharge charge, 
//    		String uomName, 
//        	int EXME_Area_ID, 
//        	int extension, 
//        	MWarehouse warehouseSur,  
//        	int numLinea, 
//        	MPrecios pv
//        	) 
//        	throws Exception{
//
//            this.extension = extension;
//            this.linea = numLinea;
//        	this.productName = charge.getName();
//            this.productValue = charge.getName();
//            if(warehouseSur!=null){
//            	this.warehouseName = (warehouseSur.getName()== null ? "" : warehouseSur.getName());
//            	this.m_Warehouse_ID = warehouseSur.getM_Warehouse_ID();
//            }
//            this.sobrePrecio = Env.ZERO;
//            this.discount = Env.ZERO;
//            this.discountAmt = Env.ZERO;
//            this.precio = pv;
//            this.cantidad = Env.ONE;
//            this.totalLine = (this.precio.getPriceStd().multiply(this.cantidad)).negate();
//      		this.lineaDe = MEXMELineasFactView.LINEA_DE_CARGO;
//      		this.precioActual = pv.getPriceStd().negate();
//      		this.precioLista = pv.getPriceList().negate();
//      		this.totalLine = pv.getPriceStd().negate();
//      		this.c_Charge_ID = charge.getC_Charge_ID();
//      		this.precioOriginal= (pv.getPrecioOriginal() == null ? Env.ZERO : pv.getPrecioOriginal());//Se muestra en listado al usuario
//        }
//        
// 

    /**
     * Constructor
     * @param prodName
     * @param prodValue
     * @param uomName
     * @param sobrePrecio
     * @param EXME_Area_ID
     * @param extension
     * @param wareName
     * @param actPac
     * @param linea
     * @param numLinea
     * @param servicio
     * @param value
     * @param costo
     * @param discount
     * @param descAplicado
     * @throws Exception
     */
//    public MEXMELineasFactView(String prodName, String prodValue, String uomName,  String value, 
//    	int EXME_Area_ID, int extension, String wareName,  int numLinea, boolean servicio, BigDecimal cantidad,
//    	MEXMEActPacienteInd actPac, MEXMEInvoiceLine linea,	MPrecios pv, int uom_ID, int almacen_Sur_ID
//    	) 
//    	throws Exception{
//
//        this.extension = extension;
//    	this.productName = prodName;
//        this.uomName = uomName;
//        this.actPacienteInd = actPac;
//        this.linea = numLinea;
//        this.productValue = prodValue;
//        this.warehouseName = wareName;
//        this.sobrePrecio = (pv.getSobrePrecio() == null ? Env.ZERO : pv.getSobrePrecio());//Se muestra en listado al usuario
//        this.discount = (pv.getDiscountPorc() == null ? Env.ZERO : pv.getDiscountPorc());
//        this.discountAmt = (pv.getDiscountAmt() == null ? Env.ZERO : pv.getDiscountAmt());//Se muestra en listado al usuario
//        this.precio = pv;
//        this.cantidad = cantidad;
//        if(this.actPacienteInd != null){
//        	addLineInv(this.actPacienteInd, servicio);
//        }
//        this.totalLine = this.precio.getPriceStd().multiply(this.cantidad);
//    }
//    
//
//    /**
//     * Constructor para la cita medica
//     * @param extension Extension Particular � Aseguradora
//     * @param numLinea Numero delinea siguiente
//     * @param actPac Actividad Paciente Indicacion
//     * @throws Exception
//     */
//     
//    public MEXMELineasFactView( int numLinea, int extension){
//        this.extension = extension;
//        this.linea = numLinea;
//    }
    
//    /**
//       * crear lineas para agregar
//     * @param ctx
//     * @param extensionNo
//     * @param descImp
//     * @param C_Charge_ID
//     * @param trxName
//     * @param area
//     * @param lineaNo
//     * @param orgTrx
//     * @param M_Product_ID
//     * @param C_UOM_ID
//     * @return
//     */
//    public static MEXMELineasFactView addLinea(Properties ctx, int extensionNo, 
//    		MEXMEDescImpView descImp, int C_Charge_ID, String trxName,
//    		int area, int lineaNo, int orgTrx,
//    		int M_Product_ID, int C_UOM_ID, boolean visible, int extension
//    	){
//    	
//    	/*MEXMELineasFactView lineaDes = MEXMELineasFactView.addLinea
//		(ctx, 1, aseg, 0, trxName, area, lineaNo, orgTrx, 
//				configPre.getDeducible_ID(), product.getC_UOM_ID(), false, EXT_ASEGURADORA);
//		*/
//    	
//   	MEXMELineasFactView linea =  null;//new MEXMELineasFactView();
     //   MEXMEInvoiceLine cargo = new MEXMEInvoiceLine(ctx, 0, trxName);
     /*   
    	linea.setExtension(extensionNo);
    	linea.setLinea(0);
    	linea.setEXME_Area_ID(area);
    	linea.setService(false);
        linea.setCosto(Env.ZERO);
        linea.setVisible(visible);
        
        if(C_Charge_ID>0);
        	cargo.setC_Charge_ID(C_Charge_ID);

        if(M_Product_ID>0){
        	
    		precio.setM_Product_ID(M_Product_ID);
    		cargo.setC_UOM_ID(C_UOM_ID);
    	}
        
        cargo.setAD_OrgTrx_ID(orgTrx);
        cargo.setC_Tax_ID(descImp.getTax_ID());
        cargo.setDescription(null);
        cargo.setLineNetAmt(desc);
        cargo.setLine(lineaNo);
        if(extension == EXT_PARTICULAR)
        	cargo.setTaxAmt(descImp.getIvaProrrateo().setScale(2,BigDecimal.ROUND_HALF_UP));
        else
        	cargo.setTaxAmt(descImp.getIvaProrrateo().multiply(Env.ONE.negate()).setScale(2,BigDecimal.ROUND_HALF_UP));
        cargo.setLineTotalAmt(cargo.getLineNetAmt().add(cargo.getTaxAmt()));
        cargo.setPriceEntered(desc);
	    cargo.setPriceLimit(desc);
	    cargo.setPriceActual(desc);
	    cargo.setPriceList(desc);
        cargo.setQtyEntered(Env.ONE);
	    cargo.setQtyInvoiced(Env.ONE);
	    cargo.setProcessed(false);
	    cargo.setIsDescription(false);
	    cargo.setIsPrinted(false);
	    linea.setQtyOrdered(Env.ONE);
	    linea.setLineaFact(cargo);
    	*/
//    	return linea;
//	}
    
    /**
     * @return
     * @throws Exception 
     */
    
//    public void addLineInv(MEXMEActPacienteInd actPacLine, boolean servicio) throws Exception{
//	
//	    MEXMEInvoiceLine cargo = new MEXMEInvoiceLine(actPacLine.getCtx(), 0, actPacLine.get_TrxName());
//	    
//	    cargo.setAD_OrgTrx_ID(actPacLine.getAD_OrgTrx_ID());
//	    cargo.setC_UOM_ID(actPacLine.getC_UOM_ID());
//	    
//	    cargo.setDescription("Venta directa");
//	    
//	    BigDecimal cantidad = actPacLine.getQtyDelivered();
//        if(servicio && cantidad.compareTo(Env.ZERO)==0)
//        	cantidad = actPacLine.getQtyOrdered();
//        
//	    cargo.setM_Warehouse_ID(actPacLine.getM_Warehouse_ID());
//	    cargo.setM_Product_ID(actPacLine.getM_Product_ID());
//	    cargo.setProcessed(false);
//	    cargo.setPriceEntered(actPacLine.getPriceActual());
//	    cargo.setPriceLimit(actPacLine.getPriceLimit());
//	    cargo.setPriceActual(actPacLine.getPriceActual());
//	    cargo.setPriceList(actPacLine.getPriceList());
//	    cargo.setQtyEntered(actPacLine.getQtyDelivered()); //Cantidad real
//	    cargo.setQtyInvoiced(actPacLine.getQtyInvoiced());
//	    
//        cargo.setC_Tax_ID();//
//        
//        cargo.setLineNetAmt();// Oblig Esta linea queda asi para que el posteo sea correcto
//        cargo.setTaxAmt();
//        cargo.setLineTotalAmt();//Oblig Esta linea queda asi para que el posteo sea correcto
//	    
//    	setLineaInvoice(cargo);
//	}

    /**
     * Crea la linea de la factura apartir de la actividad paciente adicional
     * agrega el objeto
     * @param actPacLine
     * @param servicio
     *
    public void setInvoiceLine(MEXMEActPacienteInd actPacLine, boolean servicio, boolean reqFactIndH){
    	this.lineaInvoice = MEXMEInvoiceLine.addLineInv(actPacLine, servicio, reqFactIndH);
    }

//    /**
//     * Creacion de linea de factura apartir de otra liena de factura
//     * y completar los datos con la factura
//     * @param line
//     * @param invoice
//     * @return
//     */
//    public static MEXMEInvoiceLine copyLineInvoice(MEXMEInvoiceLine line, MEXMEInvoice invoice){
//	
//	    MEXMEInvoiceLine cargo = new MEXMEInvoiceLine(line.getCtx(), 0, line.get_TrxName());
//	    
//	    
//	    cargo.setC_Invoice_ID(invoice.getC_Invoice_ID());
//	    cargo.setInvoice(invoice);
//	    cargo.setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());//Es esta para poder identificar quien esta haciendo la venta
//	    cargo.setC_UOM_ID(line.getC_UOM_ID());
//	    cargo.setC_Tax_ID(line.getC_Tax_ID());
//	    cargo.setDescription(line.getDescription());
//	    //cargo.setLineNetAmt(line.getLineNetAmt());
//	    //cargo.setTaxAmt(line.getTaxAmt());
//	    //cargo.setLineTotalAmt(line.getLineTotalAmt());
//	    cargo.setM_Warehouse_ID(line.getM_Warehouse_ID());
//	    cargo.setM_Product_ID(line.getM_Product_ID());
//	    cargo.setProcessed(line.isProcessed());
//	    cargo.setPriceEntered(line.getPriceActual());
//	    cargo.setPriceLimit(line.getPriceLimit());
//	    cargo.setPriceActual(line.getPriceActual());
//	    cargo.setPriceList(line.getPriceList());
//	    cargo.setQtyEntered(line.getQtyEntered()); //Cantidad real
//	    cargo.setQtyInvoiced(line.getQtyInvoiced());
//	    cargo.setC_Invoice_ID(line.getC_Invoice_ID());
//	    cargo.setEXME_ActPacienteInd_ID(line.getEXME_ActPacienteInd_ID());
//	    cargo.setIsDescription(line.isDescription());
//	    cargo.setIsPrinted(line.isPrinted());
//	    cargo.setLine(line.getLine());
//	    cargo.setM_InOutLine_ID(line.getM_InOutLine_ID());
//	    cargo.setC_OrderLine_ID(line.getC_OrderLine_ID());
//	    cargo.setDescriptionServ(line.getDescriptionServ());
//	    cargo.setLineNetAmt();
//	    cargo.setTaxAmt();
//	    cargo.setLineTotalAmt();//Rmontemayor.- Para que guarde en LineTotalAmt la cantidad con iva, es decir, LineNetAmt + TaxAmt
//    	return cargo;
//	}
//    

//
//	public MEXMEActPacienteInd getActPacienteInd() {
//		return this.actPacienteInd;
//	}



//	public void setActPacienteInd(MEXMEActPacienteInd actPacLine) {
//		
//		if(actPacLine != null){
//		
//			this.actPacienteInd = actPacLine;
//			this.m_Product_ID = actPacLine.getM_Product_ID();
//			this.productName = actPacLine.getProduct().getName();
//			this.productValue = actPacLine.getProduct().getValue();
//			this.cantidad = Env.ZERO.compareTo(actPacLine.getQtyDelivered())<0?actPacLine.getQtyDelivered():actPacLine.getQtyOrdered();//La cantidad Aplicada
//			this.discountAmt = (actPacLine.getDiscount() == null ? Env.ZERO : actPacLine.getDiscount());
//			this.precioLista = (actPacLine.getPriceList() == null ? Env.ZERO : actPacLine.getPriceList());//Se muestra en listado al usuario
//			this.precioActual = (actPacLine.getPriceActual() == null ? Env.ZERO : actPacLine.getPriceActual());//Se muestra en listado al usuario
//			this.totalLine = this.getPrecioActual().multiply(this.cantidad);
//			
//			//Si existe un descuento saber como se obtuvo
//			if(actPacLine.getEXME_EsqDesLine_ID()>0){
//				MEXMEEsqDesLine esquema = new MEXMEEsqDesLine(actPacLine.getCtx(), actPacLine.getEXME_EsqDesLine_ID(), null);
//				
//				//Hay descuento, es un precio fijo menor al precio del lista
//				if(esquema.getList_AddAmt().compareTo(Env.ZERO)>0){
//					if(actPacLine.getPriceList().compareTo(esquema.getList_AddAmt())!=0 ){
//						BigDecimal discountAmt = actPacLine.getPriceList().subtract(esquema.getList_AddAmt());
//						BigDecimal discountPorc = (discountAmt.multiply(Env.ONEHUNDRED)).divide(actPacLine.getPriceList(), new MathContext(6));
//						this.discount = discountPorc;
//					}else
//						this.discount = Env.ZERO;
//				} else {
//					this.discount = (esquema.getList_Discount() == null ? Env.ZERO : esquema.getList_Discount());
//				}
//			}
//
//			//Obtener el impuesto
//			if(actPacLine.getC_Tax_ID()!=0){
//				MTax tax = new MTax(actPacLine.getCtx(),actPacLine.getC_Tax_ID(),null);
//				if(tax!=null)
//					setTotalTaxAmt(this.totalLine.multiply(tax.getRate().divide(Env.ONEHUNDRED)));
//			}
//
//			//Unidad de medida de venta
//			MUOM udm = new MUOM(this.actPacienteInd.getCtx(), 
//					this.actPacienteInd.getC_UOM_ID(), null);
//			this.c_UOM_ID = udm.getC_UOM_ID();
//			this.uomName = udm.getName();
//			
//			//Almacen que surte
//			MWarehouse warehouse = new MWarehouse(this.actPacienteInd.getCtx(), 
//					this.actPacienteInd.getM_Warehouse_ID(), null);
//			this.m_Warehouse_ID = warehouse.getM_Warehouse_ID();
//			this.warehouseName = warehouse.getName();
//		}
//	}


//
//	public int getC_Charge_ID() {
//		return this.c_Charge_ID;
//	}
//
//
//
//	public void setC_Charge_ID(int charge_ID) {
//		this.c_Charge_ID = charge_ID;
//	}
//
//
//
//	public BigDecimal getCantidad() {
//		return this.cantidad;
//	}
//
//
//
//	public void setCantidad(BigDecimal cantidad) {
//		this.cantidad = cantidad;
//	}
//
//
//
//	public String getDescripcionExtra() {
//		return this.descripcionExtra;
//	}
//
//
//
//	public void setDescripcionExtra(String descripcionExtra) {
//		this.descripcionExtra = descripcionExtra;
//	}
//
//
//
//	public BigDecimal getDiscount() {
//		return this.discount;
//	}
//
//
//
//	public void setDiscount(BigDecimal discount) {
//		this.discount = discount;
//	}
//
//
//
//	public BigDecimal getDiscountAmt() {
//		return discountAmt;
//	}
//
//
//
//	public void setDiscountAmt(BigDecimal discountAmt) {
//		this.discountAmt = discountAmt;
//	}
//
//
//
//	public int getExtension() {
//		return extension;
//	}
//
//
//
//	public void setExtension(int extension) {
//		this.extension = extension;
//	}
//
//
//
//	public int getLinea() {
//		return linea;
//	}
//
//
//
//	public void setLinea(int linea) {
//		this.linea = linea;
//	}
//
//
//
//	public int getLineaDe() {
//		return lineaDe;
//	}
//
//
//
//	public void setLineaDe(int lineaDe) {
//		this.lineaDe = lineaDe;
//	}
//
//
//
//	public int getM_Product_ID() {
//		return m_Product_ID;
//	}
//
//
//
//	public void setM_Product_ID(int product_ID) {
//		m_Product_ID = product_ID;
//	}
//
//
//
//	public MPrecios getPrecio() {
//		return this.precio;
//	}
//
//
//
//	public void setPrecio(MPrecios precio) {
//		this.precio = precio;
//	}
//
//
//
//	public BigDecimal getPrecioActual() {
//		return precioActual;
//	}
//
//
//
//	public void setPrecioActual(BigDecimal precioActual) {
//		this.precioActual = precioActual;
//	}
//
//
//
//	public BigDecimal getPrecioLista() {
//		return precioLista;
//	}
//
//
//
//	public void setPrecioLista(BigDecimal precioLista) {
//		this.precioLista = precioLista;
//	}
//
//
//
//	public String getProductName() {
//		return productName;
//	}
//
//
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//
//
//	public String getProductValue() {
//		return productValue;
//	}
//
//
//
//	public void setProductValue(String productValue) {
//		this.productValue = productValue;
//	}
//
//
//
//	public BigDecimal getSobrePrecio() {
//		return sobrePrecio;
//	}
//
//
//
//	public void setSobrePrecio(BigDecimal sobrePrecio) {
//		this.sobrePrecio = sobrePrecio;
//	}
//
//
//
//	public BigDecimal getTotalLine() {
//		return totalLine;
//	}
//
//
//
//	public void setTotalLine(BigDecimal totalLine) {
//		this.totalLine = totalLine;
//	}
//
//	public void setTotalLine() {
//		BigDecimal net = getPrecioActual().multiply(getCantidad()); 
//		setTotalLine (net);
//	}
//
//
//	public BigDecimal getTotalTaxAmt() {
//		return totalTaxAmt;
//	}
//
//	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
//		this.totalTaxAmt = totalTaxAmt;
//	}
//
//	public String getUomName() {
//		return uomName;
//	}
//
//
//
//	public void setUomName(String uomName) {
//		this.uomName = uomName;
//	}
//
//
//
//	public String getWarehouseName() {
//		return warehouseName;
//	}
//
//
//
//	public void setWarehouseName(String warehouseName) {
//		this.warehouseName = warehouseName;
//	}
//
//
//
//	public MEXMEInvoiceLine getLineaInvoice() {
//		return lineaInvoice;
//	}

//    /**
//     * Obtenemos datos de la factura
//     * @param lineaInvoice
//     */
//	public void setLineaInvoice(MEXMEInvoiceLine lineaInvoice) {
//		this.lineaInvoice = lineaInvoice;
//		this.setTotalLine(lineaInvoice.getLineNetAmt());
//		this.setTotalTaxAmt(lineaInvoice.getTaxAmt());
//		//setM_Product_ID(lineaInvoice.getC_Invoice_ID());
//	}

//	public int getM_Warehouse_ID() {
//		return m_Warehouse_ID;
//	}
//
//
//	public void setM_Warehouse_ID(int warehouse_ID) {
//		m_Warehouse_ID = warehouse_ID;
//	}
//
//
//	public int getC_UOM_ID() {
//		return c_UOM_ID;
//	}
//
//
//	public void setC_UOM_ID(int c_uom_id) {
//		c_UOM_ID = c_uom_id;
//	}
//
//

	
}
