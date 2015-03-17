package org.compiere.model;

public class DetalleFacturaElectronica 
{
	private String nombreProducto="";
	private String cantidadProd="";	
	private String importeTotal="";	
	
	public String getCantidadProd() {
		return cantidadProd;
	}
	public void setCantidadProd(String cantidadProd) {
		this.cantidadProd = cantidadProd;
	}
	
	public String getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
}
/*	
 class BaseIVA
	{
	     private  String base="";
	     private  String importeTotalBase="";
	     private  String IVABase="";
	  
	     
	       
	
		public String getBase() {
			return base;
		}
		public void setBase(String base) {
			this.base = base;
		}
		public String getImporteTotalBase() {
			return importeTotalBase;
		}
		public void setImporteTotalBase(String importeTotalBase) {
			this.importeTotalBase = importeTotalBase;
		}
		public String getIVABase() {
			return IVABase;
		}
		public void setIVABase(String base) {
			IVABase = base;
		}		
	       
	       
	}


class DeducibleYCoaseguroFE
{
	private String coaseguro="00.00";
	private String deducible="00.00";
	
	private boolean existeInfo=false;
	
	public boolean isExisteInfo() {
		return existeInfo;
	}
	public void setExisteInfo(boolean existeInfo) {
		this.existeInfo = existeInfo;
	}
	public String getCoaseguro() {
		return coaseguro;
	}
	public void setCoaseguro(String coaseguro) {
		this.coaseguro = coaseguro;
	}
	public String getDeducible() {
		return deducible;
	}
	public void setDeducible(String deducible) {
		this.deducible = deducible;
	}
	
}
*/
class PieFacturaElectronica
{
	     private  String IVAGralFactura="";
	     private  String subTotalGralFactura="";
	     private  String totalGralFactura="";
	     private  String cantidadLetra="";
	     private  String leyenda1="";
	     private  String leyenda2="";
	     private  String leyenda3="";
	     private  String leyenda4="";
	     private  String tipo_cambio="";
	     private  String totalFacturaUSD="";
	     private  String IVAConversionUSD_MXN="";
	     private String IVANeto="";
	     
		public String getIVANeto() {
			return IVANeto;
		}
		public void setIVANeto(String neto) {
			IVANeto = neto;
		}
		public String getTotalFacturaUSD() {
			return totalFacturaUSD;
		}
		public void setTotalFacturaUSD(String totalFacturaUSD) {
			this.totalFacturaUSD = totalFacturaUSD;
		}
		public String getLeyenda1() {
			return leyenda1;
		}
		public void setLeyenda1(String leyenda1) {
			this.leyenda1 = leyenda1;
		}
		public String getLeyenda2() {
			return leyenda2;
		}
		public void setLeyenda2(String leyenda2) {
			this.leyenda2 = leyenda2;
		}
		public String getLeyenda3() {
			return leyenda3;
		}
		public void setLeyenda3(String leyenda3) {
			this.leyenda3 = leyenda3;
		}
		public String getLeyenda4() {
			return leyenda4;
		}
		public void setLeyenda4(String leyenda4) {
			this.leyenda4 = leyenda4;
		}		
		public String getTipo_cambio() {
			return tipo_cambio;
		}
		public void setTipo_cambio(String tipo_cambio) {
			this.tipo_cambio = tipo_cambio;
		}
		public String getIVAGralFactura() {
			return IVAGralFactura;
		}
		public void setIVAGralFactura(String gralFactura) {
			IVAGralFactura = gralFactura;
		}		
		public String getTotalGralFactura() {
			return totalGralFactura;
		}
		public void setTotalGralFactura(String totalGralFactura) {
			this.totalGralFactura = totalGralFactura;
		}
		public String getSubTotalGralFactura() {
			return subTotalGralFactura;
		}
		public void setSubTotalGralFactura(String subTotalGralFactura) {
			this.subTotalGralFactura = subTotalGralFactura;
		}
		public String getCantidadLetra() {
			return cantidadLetra;
		}
		public void setCantidadLetra(String cantidadLetra) {
			this.cantidadLetra = cantidadLetra;
		}
		public String getIVAConversionUSD_MXN() {
			return IVAConversionUSD_MXN;
		}
		public void setIVAConversionUSD_MXN(String conversionUSD_MXN) {
			IVAConversionUSD_MXN = conversionUSD_MXN;
		}
}