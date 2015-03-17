package org.compiere.model;


/**
 * Clase para obtener el saldo de la extension
 * con el monto al momento de la obtension del reporte
 * de saldos de cuenta paciente
 * @author Administrador
 *
 */
//@SuppressWarnings("unchecked")
public class MEXMESaldoExtension {

	/**** Arreglo de cargos de la cuenta paciente ****/
//	@SuppressWarnings("unused")
//	private ArrayList<RegCtaPacDet> arrgCargos = new ArrayList<RegCtaPacDet>();
	
	/**** Objeto de la cuenta paciente ****/
//	private MCtaPac objgCtapac = null;
	
	/**
	 * Se requieren los cargos y la cuenta paciente
	 * @param arrpCargos
	 * @param objpCtapac
	 */
//	public MEXMESaldoExtension (ArrayList<RegCtaPacDet> arrpCargos, MCtaPac objpCtapac)
//	{
//		this.arrgCargos = arrpCargos;
//		this.objgCtapac = objpCtapac;
//	}
	
	/**
	 * Saldo de la extesion
	 * @return
	 */
//	public BigDecimal saldoExtension(){
//		
//		try {
//			List<LabelValueBean> lstExtension = MEXMECtaPacExt.getLVBExtension(objgCtapac.getCtx(), 
//					objgCtapac.getEXME_CtaPac_ID(), false, null);
//			
//			for (int i = 0; i < lstExtension.size(); i++) { //el metodo regresa en otro tipo de objeto
//				MEXMECtaPacExt extension = (MEXMECtaPacExt)lstExtension.get(i);
//				if ( extension.getC_Invoice_ID()>0 
//						&& extension.getCoaseguro().compareTo(Env.ZERO)>0 
//						&& extension.getDeducible().compareTo(Env.ZERO)>0 )
//				{
//					
//				}//Fin if facturado coa y deduc
//				else
//				{
//					
//				}
//			}
			
//		}
//		catch (Exception e) {
//			;// TODO: handle exception
//		}
//		return Env.ZERO;
//	}
	
	/**
	 * Extensiones de la cuenta paciente 
	 * de las que se obtendra el saldo
	 * @return
	 */
//	public List  extensiones(){
//		List lista = new ArrayList<MEXMECtaPacExt>();
//		
//		
//		
//		return lista;
//	}
	
	/**
	 * Saldo de la extesion aplicado el descuento por familia
	 * @return
	 */
//	public BigDecimal descuentoFam(){
//		
//		return Env.ZERO;
//	}

	
	/**
	 * Saldo de la extesion aplicado el descuento global
	 * @return
	 */
//	public BigDecimal descuentoGlob(){
//		
//		return Env.ZERO;
//	}
	

	/**
	 * Saldo de la extesion cargando los paquetes y los minipaquetes
	 * @return
	 */
//	public BigDecimal paquetesMinipaquetes(){
//		
//		return Env.ZERO;
//	}



}
