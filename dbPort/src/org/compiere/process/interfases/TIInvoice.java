package org.compiere.process.interfases;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MCtaPacFam;
import org.compiere.model.MEXMEInvoiceLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MTax;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class TIInvoice extends TIInvoiceModel{
	
	private final int lineaDescuentoDefault = 30000;
	private final int lineaImpuestoDefault = 9000;
	
	//@SuppressWarnings("unused")
	private final String invoiceLine = "LINE";
	private final String taxLine = "TAX";
	private final String discountLine = "TAX";
	
	public TIInvoice(ResultSet resultSet){
		this.rs = resultSet;
	}
	
	
	/*lineaDescuento servira para ir aumentando el consecutivo de Numero de linea de descuento por cada factura.
	 * Una factura puede tener mas de una linea de descuento, en donde la primera linea comienza con 30, 000.
	 * El Numero de linea vuelve a comenzar en 30, 000 por cada nueva factura.
	 */
	private int lineaDescuento = lineaDescuentoDefault;
	
	/*lineaImpuesto aun no tiene uso*/
	private int lineaImpuesto = lineaImpuestoDefault;
	
	/* Para las lineas de descuento, la columna 'Attribute14' puede variar dependiendo de lo siguiente:
	 * 	-Si es descuento global, el valor en 'Attribute14' debe de ser 'DESCUENTO GLOBAL'.
	 * 	-Si es descuento por familia de productos, el valor en 'Attribute14' debe de ser 'DESCUENTOS POR FAMILIA'.
	 * 	-Si es descuento por convenio, el valor en 'Attribute14' debe de ser 'DESCUENTO AL FACTURAR'
	 * 
	 * Una linea de factura (C_InvoiceLine) puede tener mas de uno de dichos descuentos
	 * 
	 *  El descuento por familia de productos esta asociado a una extension, la cual a su vez esta asociada a un paciente.
	 */
	BigDecimal globalDiscountAmt = null;
	BigDecimal descuentoFamilia = new BigDecimal(0); 
	BigDecimal convenioDiscountAmt = null;
	
	ResultSet rs = null;

	/**
	 * GET
	 * 
	 *  Devuelve un objeto de tipo TIInvoice que contiene la informacion
	 * de un registro especifico de un resultset
	 * 
	 * @param resultSet		ResultSet del cual se sacara la informacion
	 * @param rowNumber		Numero de linea del resultSet. La informacion contenida en esa linea sera la que se le asigne al objeto a devolver (retValue)
	 * @return retValue
	 */
	public TIInvoice getRegistry(ResultSet resultSet, int rowNumber){
		
		TIInvoice retValue = new TIInvoice(resultSet);

		try{
			
			if (resultSet.getRow() == rowNumber){
				
				retValue = setRegistry(retValue);
				return retValue;
					
			} else {
				while (resultSet.next()){
					if (resultSet.getRow() == rowNumber){
						retValue = setRegistry(retValue);
						return retValue;
					}
				}
					
			}
			
		} catch (SQLException sqle){
			return null;
		}
		
		return null;
		
	}

	/**
	 * setRegistry
	 * 
	 * Se manda llamar desde el mwtodo getRegistry().
	 *  Tiene como funcion llenar la instancia de TIInvoice con los datos
	 *  que se encuentren en el Numero de linea del resultSet
	 *  (ambos enviados como parametros al metodo getRegistry).
	 * 
	 * @param TiInvoice
	 * @return
	 */
	private TIInvoice setRegistry(TIInvoice TiInvoice){

		try {
			
			/*Si el ID de factura cambia, es necesario reinicializar
			 * los consecutivos de lineas de impuesto y descuentos
			 * a su valor default.
			 */
			
			/*******NO ESTA AGARRANDO LOS DIFERENTES ID **********/
			if (this.getC_invoice_id() != rs.getInt("C_INVOICE_ID")) {
				this.lineaDescuento = lineaDescuentoDefault;
				this.lineaImpuesto = lineaImpuestoDefault;
			}
			
			TiInvoice.setAd_client_id(rs.getInt("AD_CLIENT_ID"));
			TiInvoice.setAd_org_id(rs.getInt("AD_ORG_ID"));
			TiInvoice.setAmount(rs.getBigDecimal("AMOUNT"));
			TiInvoice.setAttribute1(rs.getString("ATTRIBUTE1"));
			TiInvoice.setAttribute2(rs.getString("ATTRIBUTE2"));
			TiInvoice.setAttribute3(rs.getString("ATTRIBUTE3"));
			TiInvoice.setAttribute4(rs.getString("ATTRIBUTE4"));
			TiInvoice.setAttribute5(rs.getString("ATTRIBUTE5"));
			TiInvoice.setAttribute6(rs.getDate("ATTRIBUTE6"));
			TiInvoice.setAttribute7(rs.getDate("ATTRIBUTE7"));
			TiInvoice.setAttribute8(rs.getString("ATTRIBUTE8"));
			TiInvoice.setAttribute9(rs.getString("ATTRIBUTE9"));
			TiInvoice.setAttribute10(rs.getString("ATTRIBUTE10"));
			TiInvoice.setAttribute11(rs.getString("ATTRIBUTE11"));
			TiInvoice.setAttribute12(rs.getString("ATTRIBUTE12"));
			TiInvoice.setAttribute13(rs.getString("ATTRIBUTE13"));
			TiInvoice.setAttribute14(rs.getString("ATTRIBUTE14"));
			TiInvoice.setAttribute15(rs.getString("ATTRIBUTE15"));
			TiInvoice.setBatch_source_name(rs.getString("BATCH_SOURCE_NAME"));
			TiInvoice.setConversion_date(rs.getDate("CONVERSION_DATE"));
			TiInvoice.setConversion_rate(rs.getInt("CONVERSION_RATE"));
			TiInvoice.setConversion_type(rs.getString("CONVERSION_TYPE"));
			TiInvoice.setCreation_date(rs.getDate("CREATION_DATE"));
			TiInvoice.setCurrency_code(rs.getString("CURRENCY_CODE"));
			TiInvoice.setCust_trx_type_name(rs.getString("CUST_TRX_TYPE_NAME"));
			TiInvoice.setDescription(rs.getString("DESCRIPTION"));
			TiInvoice.setGl_date(rs.getDate("GL_DATE"));
			TiInvoice.setHeader_attribute1(rs.getString("HEADER_ATTRIBUTE1"));
			TiInvoice.setHeader_attribute2(rs.getString("HEADER_ATTRIBUTE2"));
			TiInvoice.setHeader_attribute3(rs.getString("HEADER_ATTRIBUTE3"));
			TiInvoice.setHeader_attribute4(rs.getString("HEADER_ATTRIBUTE4"));
			TiInvoice.setHeader_attribute5(rs.getString("HEADER_ATTRIBUTE5"));
			TiInvoice.setHeader_attribute6(rs.getString("HEADER_ATTRIBUTE6"));
			TiInvoice.setHeader_attribute7(rs.getString("HEADER_ATTRIBUTE7"));
			TiInvoice.setHeader_attribute8(rs.getString("HEADER_ATTRIBUTE8"));
			TiInvoice.setHeader_attribute9(rs.getString("HEADER_ATTRIBUTE9"));
			TiInvoice.setHeader_attribute10(rs.getString("HEADER_ATTRIBUTE10"));
			TiInvoice.setHeader_attribute11(rs.getString("HEADER_ATTRIBUTE11"));
			TiInvoice.setHeader_attribute12(rs.getString("HEADER_ATTRIBUTE12"));
			TiInvoice.setHeader_attribute13(rs.getString("HEADER_ATTRIBUTE13"));
			TiInvoice.setHeader_attribute14(rs.getDate("HEADER_ATTRIBUTE14"));
			TiInvoice.setHeader_attribute15(rs.getString("HEADER_ATTRIBUTE15"));
			TiInvoice.setId_unico_de_direccion(rs.getInt("ID_UNICO_DE_DIRECCION"));
			TiInvoice.setC_invoice_id(rs.getInt("C_INVOICE_ID"));
			TiInvoice.setInterface_line_attribute1(rs.getString("INTERFACE_LINE_ATTRIBUTE1"));
			TiInvoice.setInterface_line_attribute2(rs.getString("INTERFACE_LINE_ATTRIBUTE2"));
			TiInvoice.setInterface_line_attribute3(rs.getInt("INTERFACE_LINE_ATTRIBUTE3"));
			TiInvoice.setInterface_line_attribute4(rs.getString("INTERFACE_LINE_ATTRIBUTE4"));
			TiInvoice.setInterface_line_attribute5(rs.getString("INTERFACE_LINE_ATTRIBUTE5"));
			TiInvoice.setInterface_line_context(rs.getString("INTERFACE_LINE_CONTEXT"));
			TiInvoice.setLast_update_date(rs.getDate("LAST_UPDATE_DATE"));
			TiInvoice.setLine_gdf_attribute1(rs.getString("LINE_GDF_ATTRIBUTE1"));
			TiInvoice.setLine_number(rs.getInt("LINE_NUMBER"));
			TiInvoice.setLine_type(rs.getString("LINE_TYPE"));
			TiInvoice.setLink_to_line_attribute1(rs.getString("LINK_TO_LINE_ATTRIBUTE1"));
			TiInvoice.setLink_to_line_attribute2(rs.getString("LINK_TO_LINE_ATTRIBUTE2"));
			TiInvoice.setLink_to_line_attribute3(rs.getInt("LINK_TO_LINE_ATTRIBUTE3"));
			TiInvoice.setLink_to_line_attribute4(rs.getString("LINK_TO_LINE_ATTRIBUTE4"));
			TiInvoice.setLink_to_line_attribute5(rs.getString("LINK_TO_LINE_ATTRIBUTE5"));
			TiInvoice.setLink_to_line_context(rs.getString("LINK_TO_LINE_CONTEXT"));
			TiInvoice.setMtl_system_items_seg1(rs.getString("MTL_SYSTEM_ITEMSSEG1"));
			TiInvoice.setMtl_system_items_seg2(rs.getString("MTL_SYSTEM_ITEMSSEG2"));
			TiInvoice.setMtl_system_items_seg3(rs.getString("MTL_SYSTEM_ITEMSSEG3"));
			TiInvoice.setNumero_cliente(rs.getString("NUMERO_CLIENTE"));
			TiInvoice.setPrimarysalesrep_number(rs.getString("PRIMARYSALESREP_NUMBER"));
			TiInvoice.setQtyordered(rs.getInt("QTYORDERED"));
			TiInvoice.setQuantity(rs.getInt("QUANTITY"));
			TiInvoice.setReason_code(rs.getString("REASON_CODE"));
			TiInvoice.setReference_line_attribute1(rs.getString("REFERENCE_LINE_ATTRIBUTE1"));
			TiInvoice.setReference_line_attribute2(rs.getString("REFERENCE_LINE_ATTRIBUTE2"));
			TiInvoice.setReference_line_attribute3(rs.getInt("REFERENCE_LINE_ATTRIBUTE3"));
			TiInvoice.setReference_line_attribute4(rs.getString("REFERENCE_LINE_ATTRIBUTE4"));
			TiInvoice.setReference_line_attribute5(rs.getString("REFERENCE_LINE_ATTRIBUTE5"));
			TiInvoice.setReference_line_context(rs.getString("REFERENCE_LINE_CONTEXT"));
			TiInvoice.setSales_order_line(rs.getInt("SALES_ORDER_LINE"));
			TiInvoice.setTax_code(rs.getString("TAX_CODE"));
			TiInvoice.setTrx_date(rs.getDate("TRX_DATE"));
			TiInvoice.setTrx_number(rs.getString("TRX_NUMBER"));
			TiInvoice.setUnidad_operativa(rs.getString("UNIDAD_OPERATIVA"));
			TiInvoice.setUnit_selling_price(rs.getBigDecimal("UNIT_SELLING_PRICE"));
			TiInvoice.setUom_code(rs.getString("UOM_CODE"));
			
		} catch (SQLException sqle) {
			
		}
		
		return TiInvoice;
	}
	
	/**
	 * insertRegistry
	 * 
	 * Inserta manualmente un registro a la tabla TI_C_Invoice
	 * 
	 * @param ctx
	 * @param Ti_Invoice
	 * @param trxName
	 */
	private void insertRegistry(Properties ctx, TIInvoice Ti_Invoice, String trxName){
		
		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder(500); 
			
			sql.append("insert into TI_C_Invoice values ( ")
			.append(" ?, ") 	//interface_line_context		
			.append(" ?, ") 	//interface_line_attribute1
			.append(" ?, ") 	//interface_line_attribute2
			.append(" ?, ") 	//interface_line_attribute3		number(10)
			.append(" ?, ") 	//interface_line_attribute4
			.append(" ?, ") 	//interface_line_attribute5
			.append(" ?, ") 	//batch_source_name
			.append(" ?, ") 	//line_type
			.append(" ?, ") 	//description
			.append(" ?, ") 	//currency_code
			.append(" ?, ") 	//uom_code
			.append(" ?, ") 	//quantity						number
			.append(" ?, ") 	//unit_selling_price			number
			.append(" ?, ") 	//amount						number
			.append(" ?, ")		//cust_trx_type_name
			.append(" ?, ")		//id_unico_de_direccion			number(10)
			.append(" ?, ")		//numero_cliente
			.append(" ?, ") 	//conversion_type
			.append(" ?, ") 	//conversion_date				date
			.append(" ?, ") 	//conversion_rate				number(1)
			.append(" ?, ") 	//trx_date						date
			.append(" ?, ") 	//gl_date						date
			.append(" ?, ") 	//trx_number
			.append(" ?, ") 	//line_number					number(10)
			.append(" ?, ") 	//qtyordered					number
			.append(" ?, ") 	//reason_code
			.append(" ?, ") 	//tax_code
			.append(" ?, ") 	//primarysalesrep_number
			.append(" ?, ") 	//mtl_system_items_seg1
			.append(" ?, ") 	//mtl_system_items_seg2
			.append(" ?, ") 	//mtl_system_items_seg3
			.append(" ?, ") 	//creation_date					date
			.append(" ?, ") 	//last_update_date				date
			.append(" ?, ") 	//unidad_operativa
			.append(" ?, ") 	//header_attribute1
			.append(" ?, ") 	//header_attribute2
			.append(" ?, ") 	//header_attribute3
			.append(" ?, ") 	//header_attribute4
			.append(" ?, ") 	//header_attribute5
			.append(" ?, ") 	//header_attribute6
			.append(" ?, ") 	//header_attribute7
			.append(" ?, ") 	//header_attribute8
			.append(" ?, ") 	//header_attribute9
			.append(" ?, ") 	//header_attribute10
			.append(" ?, ") 	//header_attribute11
			.append(" ?, ") 	//header_attribute12
			.append(" ?, ") 	//header_attribute13
			.append(" ?, ") 	//header_attribute14			date
			.append(" ?, ") 	//header_attribute15
			.append(" ?, ") 	//attribute1
			.append(" ?, ") 	//attribute2
			.append(" ?, ") 	//attribute3
			.append(" ?, ") 	//attribute4
			.append(" ?, ") 	//attribute5
			.append(" ?, ") 	//attribute6					date
			.append(" ?, ") 	//attribute7					date
			.append(" ?, ") 	//attribute8
			.append(" ?, ") 	//attribute9
			.append(" ?, ") 	//attribute10
			.append(" ?, ") 	//attribute11
			.append(" ?, ") 	//attribute12
			.append(" ?, ") 	//attribute13
			.append(" ?, ") 	//attribute14
			.append(" ?, ") 	//attribute15
			.append(" ?, ") 	//link_to_line_context
			.append(" ?, ") 	//link_to_line_attribute1
			.append(" ?, ") 	//link_to_line_attribute2
			.append(" ?, ") 	//link_to_line_attribute3		number(10)
			.append(" ?, ") 	//link_to_line_attribute4
			.append(" ?, ") 	//link_to_line_attribute5
			.append(" ?, ") 	//reference_line_context
			.append(" ?, ") 	//reference_line_attribute1
			.append(" ?, ") 	//reference_line_attribute2
			.append(" ?, ") 	//reference_line_attribute3		number(10)
			.append(" ?, ") 	//reference_line_attribute4
			.append(" ?, ") 	//reference_line_attribute5
			.append(" ?, ") 	//sales_order_line				number(10)
			.append(" ?, ") 	//line_gdf_attribute1
			.append(" ?, ") 	//purchase_order		
			.append(" ?, ") 	//c_invoice_id					number(10)
			.append(" ?, ") 	//ad_client_id					number(10)
			.append(" ? ") 	//ad_org_id						number(10)
			.append(" ) ");

		try {
	
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setString(1, Ti_Invoice.getInterface_line_context());
			
			pstmt.setString(2, Ti_Invoice.getInterface_line_attribute1());
			
			pstmt.setString(3, Ti_Invoice.getInterface_line_attribute2());
			
			pstmt.setInt(4, Ti_Invoice.getInterface_line_attribute3());
			
			pstmt.setString(5, Ti_Invoice.getInterface_line_attribute4());
			
			pstmt.setString(6, Ti_Invoice.getInterface_line_attribute5());
			
			pstmt.setString(7, Ti_Invoice.getBatch_source_name());
			
			pstmt.setString(8, Ti_Invoice.getLine_type());
			
			pstmt.setString(9, Ti_Invoice.getDescription());
			
			pstmt.setString(10, Ti_Invoice.getCurrency_code());
			
			pstmt.setString(11, Ti_Invoice.getUom_code());
			
			pstmt.setInt(12, Ti_Invoice.getQuantity());
			
			pstmt.setBigDecimal(13, Ti_Invoice.getUnit_selling_price());
			
			pstmt.setBigDecimal(14, Ti_Invoice.getAmount());
			
			pstmt.setString(15, Ti_Invoice.getCust_trx_type_name());
			
			pstmt.setInt(16, Ti_Invoice.getId_unico_de_direccion());
			
			pstmt.setString(17, Ti_Invoice.getNumero_cliente());
			
			pstmt.setString(18, Ti_Invoice.getConversion_type());
			
			pstmt.setDate(19, Ti_Invoice.getConversion_date());
			
			pstmt.setInt(20, Ti_Invoice.getConversion_rate());
			
			pstmt.setDate(21, Ti_Invoice.getTrx_date());
			
			pstmt.setDate(22, Ti_Invoice.getGl_date());
			
			pstmt.setString(23, Ti_Invoice.getTrx_number());
			
			pstmt.setInt(24, Ti_Invoice.getLine_number());
			
			pstmt.setInt(25, Ti_Invoice.getQtyordered());
			
			pstmt.setString(26, Ti_Invoice.getReason_code());
			
			pstmt.setString(27, Ti_Invoice.getTax_code());
			
			pstmt.setString(28, Ti_Invoice.getPrimarysalesrep_number());
			
			pstmt.setString(29, Ti_Invoice.getMtl_system_items_seg1());
			
			pstmt.setString(30, Ti_Invoice.getMtl_system_items_seg2());
			
			pstmt.setString(31, Ti_Invoice.getMtl_system_items_seg3());
			
			pstmt.setDate(32, Ti_Invoice.getCreation_date());
			
			pstmt.setDate(33, Ti_Invoice.getLast_update_date());
			
			pstmt.setString(34, Ti_Invoice.getUnidad_operativa());
			
			pstmt.setString(35, Ti_Invoice.getHeader_attribute1());
			
			pstmt.setString(36, Ti_Invoice.getHeader_attribute2());
		
			pstmt.setString(37, Ti_Invoice.getHeader_attribute3());
			
			pstmt.setString(38, Ti_Invoice.getHeader_attribute4());
			
			pstmt.setString(39, Ti_Invoice.getHeader_attribute5());
			
			pstmt.setString(40, Ti_Invoice.getHeader_attribute6());
			
			pstmt.setString(41, Ti_Invoice.getHeader_attribute7());
			
			pstmt.setString(42, Ti_Invoice.getHeader_attribute8());
			
			pstmt.setString(43, Ti_Invoice.getHeader_attribute9());
			
			pstmt.setString(44, Ti_Invoice.getHeader_attribute10());
			
			pstmt.setString(45, Ti_Invoice.getHeader_attribute11());
			
			pstmt.setString(46, Ti_Invoice.getHeader_attribute12());
			
			pstmt.setString(47, Ti_Invoice.getHeader_attribute13());
			
			pstmt.setDate(48, Ti_Invoice.getHeader_attribute14());
			
			pstmt.setString(49, Ti_Invoice.getHeader_attribute15());
			
			pstmt.setString(50, Ti_Invoice.getAttribute1());
			
			pstmt.setString(51, Ti_Invoice.getAttribute2());
			
			pstmt.setString(52, Ti_Invoice.getAttribute3());
			
			pstmt.setString(53, Ti_Invoice.getAttribute4());
			
			pstmt.setString(54, Ti_Invoice.getAttribute5());
			
			pstmt.setDate(55, Ti_Invoice.getAttribute6());
			
			pstmt.setDate(56, Ti_Invoice.getAttribute7());
			
			pstmt.setString(57, Ti_Invoice.getAttribute8());
			
			pstmt.setString(58, Ti_Invoice.getAttribute9());
			
			pstmt.setString(59, Ti_Invoice.getAttribute10());
			
			pstmt.setString(60, Ti_Invoice.getAttribute11());
			
			pstmt.setString(61, Ti_Invoice.getAttribute12());
			
			pstmt.setString(62, Ti_Invoice.getAttribute13());
			
			pstmt.setString(63, Ti_Invoice.getAttribute14());
			
			pstmt.setString(64, Ti_Invoice.getAttribute15());
			
			pstmt.setString(65, Ti_Invoice.getLink_to_line_context());
			
			pstmt.setString(66, Ti_Invoice.getLink_to_line_attribute1());
			
			pstmt.setString(67, Ti_Invoice.getLink_to_line_attribute2());
			
			pstmt.setInt(68, Ti_Invoice.getLink_to_line_attribute3());
		
			pstmt.setString(69, Ti_Invoice.getLink_to_line_attribute4());
			
			pstmt.setString(70, Ti_Invoice.getLink_to_line_attribute5());
			
			pstmt.setString(71, Ti_Invoice.getReference_line_context());
			
			pstmt.setString(72, Ti_Invoice.getReference_line_attribute1());
			
			pstmt.setString(73, Ti_Invoice.getReference_line_attribute2());
			
			pstmt.setInt(74, Ti_Invoice.getReference_line_attribute3());
			
			pstmt.setString(75, Ti_Invoice.getReference_line_attribute4());
			
			pstmt.setString(76, Ti_Invoice.getReference_line_attribute5());
			
			pstmt.setInt(77, Ti_Invoice.getSales_order_line());
			
			pstmt.setString(78, Ti_Invoice.getLine_gdf_attribute1());
			
			pstmt.setString(79, Ti_Invoice.getPurchase_order());
			
			pstmt.setInt(80, Ti_Invoice.getC_invoice_id());
			
			pstmt.setInt(81, Ti_Invoice.getAd_client_id());
			
			pstmt.setInt(82, Ti_Invoice.getAd_org_id());

			int inserted = pstmt.executeUpdate();
			
			if (inserted > 0){
				DB.commit(false, trxName);
			}

			pstmt.close();
			pstmt = null;
		
		} catch (SQLException sqle) {
			
			if (pstmt != null){
				try {

					if (pstmt != null){
						pstmt.close();
						pstmt = null;
					}
					
				} catch (SQLException e){
					
				}
			}
		}	
	}

	/**
	 * crearImpuestoDesdeHeader
	 * 
	 * Si la factura tiene un impuesto, este metodo se encarga de crear una linea con la cantidad
	 * del impuesto.
	 * 
	 * Es necesario que este metodo devuelva el porcentaje de impuesto que tiene la factura
	 * ya que por cada linea de descuento debe de crearse tambien una linea de impuesto sobre dicho descuento.
	 * Por lo tanto, hay que enviar el impuesto de la factura a cada uno de los metodos de generacion de lineas
	 * de descuento para que estos puedan crear sus lineas de impuesto
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public BigDecimal crearImpuestoDesdeHeader(Properties ctx, String trxName){

		MInvoiceLine invoiceLine = new MInvoiceLine(ctx, this.getC_invoice_id(), trxName);
		MTax tax = new MTax(ctx, invoiceLine.getC_Tax_ID(), null);
		BigDecimal retValue = tax.getRate();
		
		//el TaxRate debe de ser mayor a 0 para que la linea de impuesto se genere
		if  (retValue.compareTo(BigDecimal.ZERO) > 1){

				this.setInterface_line_attribute3(lineaImpuesto);
				this.setInterface_line_attribute4(taxLine);
				
				/*El metodo insertRow() de ResultSet produce errores, por lo que se creo un metodo (insertRegistry) en la clase TIInvoice
				 * que hace el insert manualmente  a la tabla de paso
				 */
				insertRegistry(ctx, this, trxName);
				
				this.lineaImpuesto++;

			}

		return retValue;
		
	}
	
	public void crearDescuentoGlobal(Properties ctx, BigDecimal taxRate, String trxName){
		
		MInvoice invoice = new MInvoice(ctx, this.getC_invoice_id(), trxName);
		
		this.setAmount(invoice.getDiscountAmt());
		this.setInterface_line_attribute3(lineaDescuento);
		this.setInterface_line_attribute4(discountLine);
		this.setAttribute14("DESCUENTO GLOBAL");

		insertRegistry(ctx, this, trxName);
		
		this.lineaDescuento++;

		crearImpuestoPorDescuento(ctx, taxRate, trxName);	
		
	}
	
	/*****************INCOMPLETO. VERIFICAR EL FUNCIONAMIENTO DE DESCUENTOS POR FAMILIAS***************/
	public void crearDescuentoPorFamilia (Properties ctx, BigDecimal taxRate, String trxName){
		
		/*Traer la linea (dado un C_Invoice_ID y una numero de linea de factura).
		 * Esto es para buscar, por cada uno de los productos de la factura, si estos tienen
		 * un descuento por familia
		 */
		
		List <MInvoiceLine>invoiceLine = MEXMEInvoiceLine.getLineas(ctx, this.getC_invoice_id(), " and c_invoiceline.line = " + this.getLine_number(), trxName);

		//El maximo de registros que podria devolver es 1, ya que estamos buscando un Numero de linea especifico para una factura
		for (int x = 0; x < invoiceLine.size(); x++){
			
			MInvoice invoice = new MInvoice(ctx, invoiceLine.get(x).getC_Invoice_ID(), trxName);

			MCtaPacFam fam[] = MCtaPacFam.getCtasPacFamXExt(ctx, invoice.getEXME_CtaPacExt_ID(), null);

			//Obtener la suma de todos los descuentos que se hayan encontrado en la extension
			for (int famLength = 0; famLength < fam.length; famLength++){

				/*REVISAR BIEN COMO FUNCIONA EL DESCUENTO POR FAMILIA*/
				 descuentoFamilia = descuentoFamilia.add(fam[x].getDiscountPorcent());
				
			}
			
		}
		
		this.setAmount(null);
		this.setInterface_line_attribute3(lineaDescuento);
		this.setInterface_line_attribute4(discountLine);
		this.setAttribute14("DESCUENTOS POR FAMILIA");
		
		insertRegistry(ctx, this, trxName);
		
		this.lineaDescuento++;
		
		crearImpuestoPorDescuento(ctx, taxRate, trxName);
		
	}
	
	/**
	 * crearImpuestoPorDescuento
	 * 
	 * Se manda a llamar dentro de los metodos de creacion de los descuentos.
	 * Su funcion es crear una linea de impuesto sobre el descuento que haya mandado
	 * llamar a este metodo (ya sea descuento global, descuento por familia, o descuento al facturar).
	 * 
	 * @param ctx
	 * @param taxRate
	 * @param trxName
	 */
	private void crearImpuestoPorDescuento(Properties ctx, BigDecimal taxRate, String trxName){

		//Si hay un impuesto (taxRate), crear la linea de impuesto sobre el descuento
		if (taxRate != null && (taxRate.compareTo(BigDecimal.ZERO) > 1)){
			
			this.setInterface_line_attribute3(lineaImpuesto);
			
			this.setInterface_line_attribute4(taxLine);
			
			this.setAmount(this.getAmount().multiply(taxRate.divide(Env.ONEHUNDRED)));
			
			insertRegistry(ctx, this, trxName);
			
			lineaImpuesto++;
			
		}

	}
	
	/**
	 * deleteAll
	 * 
	 *  Borra todos los registros relacionados al cliente y organizacion
	 *  que estan en el contexto
	 * 
	 * @param ctx
	 * @param trxName
	 */
	public static void deleteAll(Properties ctx, String trxName){
		
		PreparedStatement pstmt = null;
		
		String sql = "delete  ti_c_invoice where ad_client_id= ? and ad_org_id = ? ";
		
		pstmt = DB.prepareStatement(sql, trxName);
		 
		try {
			pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
			pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
				 
			pstmt.executeUpdate();
			
			DB.commit(false, trxName);
		} catch(SQLException sqle){
			
			try {
				if (pstmt != null){
					pstmt.close ();
					pstmt = null;
				}
			} catch(SQLException sqle2){
				
			}

		}

	}
	
	/**
	 * getAll
	 * 
	 * Devuelve TODOS los registros relacionados
	 * al cliente y organizacion que estan en el contexto
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static ResultSet getAll(Properties ctx, String trxName){
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		StringBuilder sql =  new StringBuilder(2300);
		
		sql.append(" SELECT")
		     
			.append(" INTERFACE_LINE_CONTEXT,")			//1
			.append(" INTERFACE_LINE_ATTRIBUTE1,")		//2
			.append(" INTERFACE_LINE_ATTRIBUTE2,")   	//3
			.append(" INTERFACE_LINE_ATTRIBUTE3,")  	//4
			.append(" INTERFACE_LINE_ATTRIBUTE4,")		//5
			.append(" INTERFACE_LINE_ATTRIBUTE5,")   	//6
			.append(" BATCH_SOURCE_NAME,")				//7
			.append(" LINE_TYPE,")						//8
			.append(" DESCRIPTION,")					//9
			.append(" CURRENCY_CODE,")					//10
			.append(" UOM_CODE,")						//11
			.append(" QUANTITY,")						//12
			.append(" UNIT_SELLING_PRICE,")   			//13
			.append(" AMOUNT,")							//14
			.append(" CUST_TRX_TYPE_NAME,")				//15
			.append(" ID_UNICO_DE_DIRECCION,")			//16
			.append(" NUMERO_CLIENTE,")					//17
			.append(" CONVERSION_TYPE,")				//18
			.append(" CONVERSION_DATE,")				//19
			.append(" CONVERSION_RATE,")				//20
			.append(" TRX_DATE,") 						//21
			.append(" GL_DATE,")						//22
			.append(" TRX_NUMBER,")						//23
			.append(" LINE_NUMBER,")					//24
			.append(" QTYORDERED,") 					//25
			.append(" REASON_CODE,")					//26
			.append(" TAX_CODE,")						//27
			.append(" PRIMARYSALESREP_NUMBER,") 		//28
			.append(" MTL_SYSTEM_ITEMS_SEG1,")    		//29
			.append(" MTL_SYSTEM_ITEMS_SEG2,")  		//30
			.append(" MTL_SYSTEM_ITEMS_SEG3,")  		//31
			.append(" CREATION_DATE,")					//32
			.append(" LAST_UPDATE_DATE,")				//33
			.append(" UNIDAD_OPERATIVA,")				//34
			.append(" HEADER_ATTRIBUTE1,")				//35
			.append(" HEADER_ATTRIBUTE2,")   			//36
			.append(" HEADER_ATTRIBUTE3,")				//37
			.append(" HEADER_ATTRIBUTE4,")				//38
			.append(" HEADER_ATTRIBUTE5,")				//39
			.append(" HEADER_ATTRIBUTE6,")				//40
			.append(" HEADER_ATTRIBUTE7,") 				//41
			.append(" HEADER_ATTRIBUTE8,")				//42
			.append(" HEADER_ATTRIBUTE9,")    			//43
			.append(" HEADER_ATTRIBUTE10,")				//44
			.append(" HEADER_ATTRIBUTE11,")				//45
			.append(" HEADER_ATTRIBUTE12,")				//46
			.append(" HEADER_ATTRIBUTE13,")				//47
			.append(" HEADER_ATTRIBUTE14,")				//48
			.append(" HEADER_ATTRIBUTE15,")				//49
			.append(" ATTRIBUTE1,")						//50
			.append(" ATTRIBUTE2,")						//51 
			.append(" ATTRIBUTE3,")						//52
			.append(" ATTRIBUTE4,")						//53
			.append(" ATTRIBUTE5,")						//54
			.append(" ATTRIBUTE6,")						//55
			.append(" ATTRIBUTE7,")						//56
			.append(" ATTRIBUTE8,")						//57
			.append(" ATTRIBUTE9,")						//58
			.append(" ATTRIBUTE10,")					//59
			.append(" ATTRIBUTE11,")					//60
			.append(" ATTRIBUTE12,") 					//61
			.append(" ATTRIBUTE13,")  					//62
			.append(" ATTRIBUTE14,")					//63
			.append(" ATTRIBUTE15,")					//64
			.append(" LINK_TO_LINE_CONTEXT,") 			//65
			.append(" LINK_TO_LINE_ATTRIBUTE1,")  		//66
			.append(" LINK_TO_LINE_ATTRIBUTE2,")   		//67
			.append(" LINK_TO_LINE_ATTRIBUTE3,")    	//68
			.append(" LINK_TO_LINE_ATTRIBUTE4,")  		//69
			.append(" LINK_TO_LINE_ATTRIBUTE5,")    	//70
			.append(" REFERENCE_LINE_CONTEXT,")			//71
			.append(" REFERENCE_LINE_ATTRIBUTE1,")		//72
			.append(" REFERENCE_LINE_ATTRIBUTE2,")  	//73
			.append(" REFERENCE_LINE_ATTRIBUTE3,")		//74
			.append(" REFERENCE_LINE_ATTRIBUTE4,")		//75
			.append(" REFERENCE_LINE_ATTRIBUTE5,")  	//76
			.append(" SALES_ORDER_LINE,")				//77
			.append(" LINE_GDF_ATTRIBUTE1,")			//78
			.append(" PURCHASE_ORDER,")					//79
			.append(" C_INVOICE_ID,")					//80
			.append(" AD_CLIENT_ID,")					//81
			.append(" AD_ORG_ID")						//82

			.append(" FROM TI_C_INVOICE")
			.append(" WHERE AD_CLIENT_ID = ? AND AD_ORG_ID = ?")
			
			//Ordenamos los resultados primero por ID de factura y luego por el Numero de linea de factura (INTERFACE_LINE_ATTRIBUTE3)
			.append(" ORDER BY C_INVOICE_ID, INTERFACE_LINE_ATTRIBUTE3");
		
			//usar el resultSet para agregar lineas no funciono, por lo que los inserts se haran mediante metodos de TIInvoice.
			//pstmt = DB.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, trxName); <--De esta manera se pueden hacer inserts y updates sobre el resultSet

			pstmt = DB.prepareStatement(sql.toString(),trxName);
		 
			try {
				pstmt.setInt(1, Env.getContextAsInt(ctx, "#AD_Client_ID"));
				pstmt.setInt(2, Env.getContextAsInt(ctx, "#AD_Org_ID"));
				
				rs = pstmt.executeQuery();

			} catch(SQLException sqle){
				
				try {
					if (pstmt != null){
						pstmt.close ();
						pstmt = null;
					}
					
					if (rs != null){
						rs.close ();
						rs = null;
					}

				} catch(SQLException sqle2){
					
				}

			}

			return rs;

	}
	
}
