package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.apps.AEnv;
import org.compiere.model.MAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMETCierre;
import org.compiere.model.MElementValue;
import org.compiere.model.MFactAcct;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
import org.compiere.model.MMovement;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MTable;
import org.compiere.model.MYear;
//import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * Proceso de Cierre de Ejercicio Contable
 * 
 * Fecha: 10/02/2010
 * @author Rosy Velazquez
 * @version $Revision: 1.0
 * **/
public class CierreEjercicio extends SvrProcess{
	
	 /** Static Logger               */
    private static CLogger      slog = CLogger.getCLogger (CierreEjercicio.class);
    
	private int periodoAjuste = 0;
	private int contAjuste = 0;
	
	private BigDecimal yearId = null;
	private BigDecimal cuentaId = null; //Cuenta del Resultado	
	
	private BigDecimal utilidadEjercicio = BigDecimal.ZERO;
	private BigDecimal amtAccDr = BigDecimal.ZERO;
	private BigDecimal amtAccCr = BigDecimal.ZERO;
	/** LITERAL **/
	public final static String ADORGID = "#AD_Org_ID";
	//private CFrame frame = new CFrame(); 

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			else if (name.equals("C_Year_ID"))
				yearId = (BigDecimal)para[i].getParameter();
			else if (name.equals("Cuenta_Resultado"))
				cuentaId = (BigDecimal)para[i].getParameter();		
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}	
	}
	
	@Override
	protected String doIt() throws Exception {
		
		Trx trx = null;
		trx = Trx.get(Trx.createTrxName("CIERRE"), true); //Se crea la transaccion
		String trxName = trx.getTrxName();
		
		MYear year = new MYear(getCtx(), yearId.intValue(), trxName);
		MElementValue cuenta = new MElementValue(getCtx(), cuentaId.intValue(), trxName);
				
		String valida = validaCierre(year, cuenta, trxName);
		
		if(!valida.equals("")){
			trx.rollback();
			return valida;
		}else{
			trx.commit();
		}
		
	    trx.close();
	    trx = null;
		   					
		return "";
	}
	
	protected String validaCierre(MYear year, MElementValue cuenta, String trxName){

		//Validar que no se halla realizado el cierre de ejercicio
		if(year.isCierre()){
			String errorCierre = Msg.getMsg(getCtx(), "CierreRealizado");
			
			return errorCierre + " " + year.getYear();
		}
	
		//Validar que las cuentas de Ingresos y Gastos tengan naturaleza deudora o acreedora
		int numGastos = 0;
		int numIngresos = 0;
		List<MElementValue> list = MElementValue.getListaCuentas(getCtx());
		if(list != null){
			for(int i=0;i < list.size();i++){
				MElementValue element = list.get(i);
				if(element.getAccountType().equals(MElementValue.ACCOUNTTYPE_Revenue) || element.getAccountType().equals(MElementValue.ACCOUNTTYPE_Expense)){
					
					if(element.getAccountType().equals(MElementValue.ACCOUNTTYPE_Revenue)){
						numIngresos++;
					}else
						numGastos++;
										
					if(!element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Credit) && !element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Debit)){
						String errorCierre = Msg.getMsg(getCtx(), "CuentasNaturaleza");
						return errorCierre;
					}
				}
			}
		}
		
		//String pasos = Msg.getMsg(getCtx(), "PasosProcesoCierre");
		
		//log.saveError("PasosProcesoCierre", "titulo del Mensaje");
		//JOptionPane.showMessageDialog(null, Msg.getMsg(getCtx(), "CierreRealizado"));
		log.saveWarning("PasosProcesoCierre", "titulo del Mensaje");
		
		//log.saveError("SaveError", Msg.getElement(getCtx(), "EXME_Especialidad_ID ") + "Color repetido");
		
	////------------------comentado por error de INSTANCIA *-------------------------------
		//Si pasa las validaciones anteriores se despliegan los pasos a seguir
	/*	Object[] options = {"Salir",
                			"Pasos Realizados"};
		
		int n = JOptionPane.showOptionDialog(null,
		"<html><b>Los pasos a seguir son: </b>"
		+ "\n\n1.- Dar de baja el motor contable."
		+ "\n2.- Depurar los asientos contables, opción: Reconfigurar Contabilidad."
		+ "\n3.- Cambiarle los signos a las cuentas, opción: Elemento Cuenta."
		+ "\n4.- Levantar el motor contable para que vuelva a postear los asientos contables."
		+ "\n5.- Consultar los saldos de las cuentas que les fue corregida su naturaleza mediante el "
		+ "\n      visor contable, otros reportes, etc."
		+ "\n6.- Cerrar los periodos del año.",
		
		"Cierre del Ejercicio " + year.getYear(),
		JOptionPane.YES_NO_OPTION,
		JOptionPane.INFORMATION_MESSAGE, 
		null,
		options,
		options[0]);
		
		PasosProcesoCierre
		
		String errorCierre = Msg.getMsg(getCtx(), "ErrorPasosCierre");
		if(n == JOptionPane.CLOSED_OPTION){		
			return errorCierre;						
		}else
			if(n == JOptionPane.YES_OPTION){
				return errorCierre;
			}else
				if(n == JOptionPane.NO_OPTION){
					//Continua con el proceso 
				}
		
		*/
		//--------------------------------------------------------------------
		
		if(numIngresos==0){
			String errorCuenta = Msg.getMsg(getCtx(), "CuentaIngresos");
			return errorCuenta;
		}
		
		if(numGastos==0){
			String errorCuenta = Msg.getMsg(getCtx(), "CuentaGastos");
			return errorCuenta;
		}
		
		//Validar que la cuenta de resultados sea una cuenta de capital y tenga naturaleza acreedora
		if(!(cuenta.getAccountType().equals(MElementValue.ACCOUNTTYPE_OwnerSEquity) && cuenta.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Credit))){
			String errorCuenta = Msg.getMsg(getCtx(), "CuentaCapital");
			return errorCuenta;
		}					
					
		//Traer periodos del año
		List<MPeriod> periods = MPeriod.getPeriods(year.getC_Year_ID(), getCtx());		
		if(periods != null){
			for(int i=0; i < periods.size(); i++){
				MPeriod period = periods.get(i);
				if(period.isStandardPeriod()){ //Validacion periodos estandar cerrados permanentemente
					MPeriodControl[] controls = period.getPeriodControls(false);
					if(controls != null){
						for(int j=0; j < controls.length; j++){
							MPeriodControl control = controls[j];
							if(!control.getPeriodStatus().equals(MPeriodControl.PERIODSTATUS_PermanentlyClosed)){
								String errorDocumento = Msg.getMsg(getCtx(), "DocumentosCerrados");
								return errorDocumento;
							}
						}
					}
				}else if(period.getPeriodType().equals(MPeriod.PERIODTYPE_AdjustmentPeriod)){//Guardado del periodo de ajuste
					contAjuste++;
					if(contAjuste>1){
						String errorAjuste = Msg.getMsg(getCtx(), "UnPeriodoAjuste");
						return errorAjuste;
					}
					//Valida Periodo de Ajuste Abierto
					MPeriodControl[] controls = period.getPeriodControls(false);
					if(controls != null){
						for(int j=0; j < controls.length; j++){
							MPeriodControl control = controls[j];
							
							if(control.getDocBaseType().equals(MPeriodControl.DOCBASETYPE_GLJournal)){//Solo el periodControl = GL Journal							
								if(!control.getPeriodStatus().equals(MPeriodControl.PERIODSTATUS_Open)){ 
									String errorAjuste = Msg.getMsg(getCtx(), "DocumentosAjuste");
									return errorAjuste;
								}else{								
									this.periodoAjuste = period.getC_Period_ID();								
								}
							}
						}
					}
				}
				
			}
		}
		
		String posteo = validaPosteo(year);
		if(posteo != ""){
			return posteo;
		}
		
		//Valida que la cuenta de resultado no tenga asiento contable en ningun ejercicio
		//String where = null;
		List<MFactAcct> fa = MFactAcct.getMFactAcct(getCtx(), null, trxName);
		if(fa.size() > 0){
			for(int i=0 ; i < fa.size(); i++){
				MFactAcct fact = fa.get(i);
				if(fact.getAccount_ID() == this.cuentaId.intValue()){
					String errorAsiento = Msg.getMsg(getCtx(), "AsientoCuentaResultado");
					return errorAsiento;					//Comentado para visualizar mensajes de error
				}
			}
		}
		
		//Proceso !!!<<<<<<<
		
		obtieneUtilidadPerdida(year);	
		boolean cuentas = saldarCuentas(year, trxName);
		
		if(!cuentas){
			String errorSaldar = Msg.getMsg(getCtx(), "ErrorSaldaCuenta");
			return errorSaldar;			
		}
		
		if(!guardarAsiento(year, trxName)){
			String errorAsiento = Msg.getMsg(getCtx(), "ErrorAsientoContable");
			return errorAsiento;	
		}
			
		//cerrar el periodo de ajuste
		MPeriod pAjuste = new MPeriod(getCtx(), this.periodoAjuste, trxName);
		MPeriodControl[] controls = pAjuste.getPeriodControls(false);
		if(controls != null){
			if(controls.length > 0){
				for(int j=0; j < controls.length; j++){
					MPeriodControl control = controls[j];
					control.setPeriodStatus(MPeriodControl.PERIODSTATUS_PermanentlyClosed);
					control.save(trxName);
				}
			}
		}
		
		
		//Borrar tabla temporal
		MEXMETCierre.borraTemporal(getCtx());
		
		//Anio (Ejercicio) Cerrado				
		year.setCierre(true);
		
		year.save(trxName);
		
		return "";
	}		
	
	/**
	 * Se obtiene la utilidad o perdida del ejercicio
	 * @param year ejercicio de cierre
	 * @author rosy
	 * **/
	public void obtieneUtilidadPerdida(final MYear year){	
		utilidadEjercicio = obtieneUtilidadPerdida(getCtx(), year,  
				null, this.getAD_Client_ID(), utilidadEjercicio, 
				year.get_TrxName());	

		//utilidad o perdida
		if(utilidadEjercicio.compareTo(BigDecimal.ZERO) == 1){
			amtAccDr = BigDecimal.ZERO;
			amtAccCr = utilidadEjercicio;
		}else{
			amtAccDr = utilidadEjercicio;
			amtAccCr = BigDecimal.ZERO;
		}
	}
	
	/**
	 * Se obtiene la utilidad o perdida del ejercicio
	 * @author rosy
	 * @param ctx
	 * @param year ejercicio de cierre
	 * @param AD_Client_ID
	 * @param pUtilEjercicio
	 * @return
	 */
	public static BigDecimal obtieneUtilidadPerdida(final Properties ctx, 
			final MYear year, final MPeriod period, final int AD_Client_ID, 
			final BigDecimal pUtilEjercicio, final String trxName){			

		BigDecimal lUEjercicio = pUtilEjercicio;

		int ad_org = 0;
		if(ctx.containsKey(ADORGID)){
			ad_org = Integer.valueOf(ctx.get(ADORGID).toString());			
			slog.log(Level.INFO, ADORGID + ad_org);
		}

		// Obtiene una lista de registros en Fact_Acct segun los parametros de consulta
		List<MFactAcct> fact = MFactAcct.getMFactAcct(ctx, year, period, AD_Client_ID, ad_org, trxName);
		
		if(fact != null && !fact.isEmpty()){

			BigDecimal lRevenue = pUtilEjercicio;
			BigDecimal lExpense = pUtilEjercicio;

			for(int i = 0 ; i < fact.size(); i++ ){//7161
				MFactAcct fa = fact.get(i);
				MElementValue element = new MElementValue(ctx , fa.getAccount_ID(), trxName);

				// Solo ingresos
				if(element.getAccountType().equals(MElementValue.ACCOUNTTYPE_Revenue)){
					if(element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Credit)){
						lRevenue = lRevenue.add(fa.getAmtAcctCr()).subtract(fa.getAmtAcctDr());

					}else
						if(element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Debit)){
							lRevenue = lRevenue.add(fa.getAmtAcctDr()).subtract(fa.getAmtAcctCr());
						}		
				}
				// ó gastos/costos
				if( element.getAccountType().equals(MElementValue.ACCOUNTTYPE_Expense)){


					if(element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Credit)){
						lExpense = lExpense.add(fa.getAmtAcctCr()).subtract(fa.getAmtAcctDr());

					}else
						if(element.getAccountSign().equals(MElementValue.ACCOUNTSIGN_Debit)){
							lExpense = lExpense.add(fa.getAmtAcctDr()).subtract(fa.getAmtAcctCr());
						}					  
				}				   				   					   
			}

			lUEjercicio =  lUEjercicio.add(lRevenue.subtract(lExpense));
			slog.log(Level.INFO, "Utilidad del Ejercicio " + lUEjercicio);//1027374.29
		}		

		return lUEjercicio;
	}

	/**
	 * Salda las cuentas creando registros en tabla temporal para despues invertir cr dr
	 * @param year ejercicio de cierre
	 * @param trxName transaccion
	 * @return ret
	 * @author rosy
	 * **/
	protected boolean saldarCuentas(MYear year, String trxName){
		boolean ret=true;
		int ad_org = 0;
		if(getCtx().containsKey("#AD_Org_ID")){
			ad_org = Integer.valueOf(getCtx().get("#AD_Org_ID").toString());			
			System.out.println("AD_Org " + ad_org);	
		}
		
		String where = "";
		where = where + " AND ad_client_id = " + this.getAD_Client_ID() +
				      " AND ad_org_id = " + ad_org +
				      " AND postingType = '" + MFactAcct.POSTINGTYPE_Actual + "' " + 
				      " AND to_char("+DB.truncDate("dateacct","year")+",'yyyy') = '" + year.getYear() + "' " +
				      " AND c_acctschema_id IN (SELECT c_acctschema_id FROM C_AcctSchema WHERE ad_client_id = " + getAD_Client_ID() + " AND (ad_org_id = 0 OR ad_org_id = " + ad_org + "))";
								
	   List<MFactAcct> fact = MFactAcct.getMFactAcct(getCtx(), where, trxName);
	   List<String> errores = new ArrayList<String>();	   	   
	   	   	   
	   if(fact != null){
		   if(fact.size() > 0){
			   for(int i = 0 ; i < fact.size(); i++ ){
				   MFactAcct fa = fact.get(i);
				   MElementValue cuenta = new MElementValue(getCtx() , fa.getAccount_ID(), null);
				   if(cuenta.getAccountType().equals(MElementValue.ACCOUNTTYPE_Revenue) || cuenta.getAccountType().equals(MElementValue.ACCOUNTTYPE_Expense)){
					   MEXMETCierre cierre = MEXMETCierre.getSegunIndice(getCtx(), fa, null);
					   if(cierre != null){ //Si existe solo acumular cr y dr
						   cierre.setAmtAcctCr(cierre.getAmtAcctCr().add(fa.getAmtAcctCr()));
						   cierre.setAmtAcctDr(cierre.getAmtAcctDr().add(fa.getAmtAcctDr()));
						   cierre.save(trxName);
					   }else{
						   if(!MEXMETCierre.copiaDeFactAcct(getCtx(), fa, trxName)) //Crear nuevo registro en EXME_T_Cierre
						   {
							   errores.add("Error en copia");
							   ret = false;
						   }
					   }
				   }
			   }
	       }
	   }	   	   
	   
	   return ret;
	}
	
	/**
	 * Guarda asiento contable generando registros en polizas de diario
	 * @param year ejercicio a cerrar
	 * @param trxName transaccion
	 * @return exito
	 * @author rosy
	 * **/
	protected boolean guardarAsiento(MYear year, String trxName){
		boolean exito = true;
		
		//Registro en GL_JournalBatch (Lote) y GL_Journal (Diario)
		//--------------LOTE
		//---Periodo de ajuste, dateacct 31/12/ejercicio
		MJournalBatch journalBatch = new MJournalBatch(getCtx(), 0, trxName);
		
		journalBatch.setDescription("Lote de Diario de Ajuste");
		journalBatch.setC_Period_ID(this.periodoAjuste);
		
		Calendar fecha = new GregorianCalendar();
		fecha.set(Integer.valueOf(year.getYear()), Calendar.DECEMBER, 31);
		Timestamp dateAcct = DB.getTimestampForOrg(Env.getCtx());				
		journalBatch.setDateAcct(dateAcct);
		
		MDocType doc[] = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_GLJournal);						
		journalBatch.setC_DocType_ID(doc[0].getC_DocType_ID());
		journalBatch.save(trxName);
		
		//------DIARIO
		MJournal journal = new MJournal(getCtx(), 0, trxName);
		journal.setGL_JournalBatch_ID(journalBatch.getGL_JournalBatch_ID());
		journal.setDescription("Diario de Ajuste");
		journal.setDateAcct(journalBatch.getDateAcct());
		journal.setC_Period_ID(this.periodoAjuste);
		journal.save(trxName);
		
		//GL_JournalLine ** Si existen registros en EXME_T_Cierre
		//---invertir cargos por creditos y viceversa
		List<MEXMETCierre> cierre = MEXMETCierre.getTodos(getCtx());
		if(cierre != null){
			if(cierre.size() > 0){
				for(int i=0 ; i < cierre.size(); i++ ){
					MEXMETCierre mc = cierre.get(i);
					
					if(!MJournalLine.copiaCierre(getCtx(), mc, journal, trxName)){
						exito = false;
					}
				}
			}
		}
		
		if(exito){
			//Insertar linea de la utilidad del ejercicio
			MJournalLine jl = new MJournalLine(getCtx(), 0, trxName);
			jl.setGL_Journal_ID(journal.getGL_Journal_ID());
			jl.setDateAcct(journal.getDateAcct());
			jl.setAmtAcctCr(this.amtAccCr);
			jl.setAmtAcctDr(this.amtAccDr);
			//--se tiene que buscar la c_validcombination_id ?? para la cuenta --solo campos obligatorios 
			MAccount combinacion = MAccount.get(getCtx(), journal.getAD_Client_ID(), journal.getAD_Org_ID(), journal.getC_AcctSchema_ID(), this.cuentaId.intValue(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			jl.setC_ValidCombination_ID(combinacion.getC_ValidCombination_ID());
			jl.save(trxName);			
		}
		
		postearAsiento(journal);
		
		return exito;
	}
	
	/**
	 * Postea registros insertados por el cierre en GL_Journal y sus lineas GL_JournalLine
	 * @param journal Diario
	 * @author rosy
	 * 
	 * **/
	protected void postearAsiento(MJournal journal){
		AEnv.postImmediate(0, journal.getAD_Client_ID(), MTable.getTable_ID("GL_Journal"), journal.getGL_Journal_ID(), true);
	}
	
	
	/**
	 * Valida que todos los documentos esten posteados
	 * @param year ejercicio
	 * @return ventana de advertencia
	 * @author rosy
	 * 
	 * **/
	protected String validaPosteo(MYear year){  

		//Validar documentos posteados (los de estatus completo) que pertenezcan a los periodos del anio		
		//M_InOut
		List<String> docRM = new ArrayList<String>(); //Recepcion de Material
		List<String> docEC = new ArrayList<String>(); //Embarque (clientes)					
		
		String strRet = Msg.getMsg(getCtx(), "ValidaDocumentosPosteados"); //"<html> <b>Todos los documentos con estatus completo deben estar posteados. Favor de verificar.</b> <BR>";
		
				//fecha contable sea del año del ejercicio y estatus Completado(CO)
				String where = " AND docstatus = '" + MInOut.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("dateacct","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MInOut> inOut = MInOut.getMInOuts(where, getCtx(), new ArrayList<Object>());
				if(inOut != null){					
					
					String strRM = "";
					String strEC = "";
					
					for(int k=0 ; k < inOut.size(); k++){						
						MInOut out = inOut.get(k);										
						
						MDocType doc = new MDocType(getCtx(), out.getC_DocType_ID(), null);
						if(doc.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialReceipt)){
							docRM.add(out.getDocumentNo());
						}else 
							if(doc.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialDelivery)){
								docEC.add(out.getDocumentNo());
							}													
					}
					
					if(docRM != null){		
						if(docRM.size() > 0){
							strRM = convierteCadena(docRM);
							String msj = Msg.getMsg(getCtx(), "RecepcionMaterial");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strRM + "].";//" <html><BR><b>Recepción de Material: </b><BR>[" + strRM + "].";
						}
					}
					
					if(docEC != null){
						if(docEC.size() > 0){
							strEC = convierteCadena(docEC);
							String msj = Msg.getMsg(getCtx(), "EmbarqueCliente");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strEC + "].";//" <html><BR><BR><b>Embarque (cliente): </b><BR>[" + strEC + "].";
						}
					}														
				}
				
		//MMatchPo
		//MInvoice
				List<String> fp = new ArrayList<String>(); //Factura (proveedor)
				List<String> fc = new ArrayList<String>(); //Factura (cliente)
				
				where = "AND docstatus = '" + MInvoice.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("dateacct","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MInvoice> invoice = MInvoice.getInvoices(where, null, null, null,getCtx());
				if(invoice != null){					
					String strFp = "";
					String strFc = "";
					
					for(int k=0 ; k < invoice.size(); k++){						
						MInvoice inv = invoice.get(k);										
						
						MDocType doc = new MDocType(getCtx(), inv.getC_DocType_ID(), null);
						if(doc.getDocBaseType().equals(MDocType.DOCBASETYPE_APInvoice) || doc.getDocBaseType().equals(MDocType.DOCBASETYPE_APCreditMemo)){
							fp.add(inv.getDocumentNo());
						}else 
							if(doc.getDocBaseType().equals(MDocType.DOCBASETYPE_ARCreditMemo) || doc.getDocBaseType().equals(MDocType.DOCBASETYPE_ARInvoice)){
								fc.add(inv.getDocumentNo());
							}													
					}
					
					if(fp != null){
						if(fp.size() > 0){
							strFp = convierteCadena(fp);
							String msj = Msg.getMsg(getCtx(), "FacturaProveedor");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strFp + "].";//"<html><BR><BR><b>Factura (proveedor): </b><BR>[" + strFp + "]."; 
						}
					}
					
					if(fc != null){
						if(fc.size() > 0){
							strFc = convierteCadena(fc);
							String msj = Msg.getMsg(getCtx(), "FacturaCliente");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strFc + "].";//"<html><BR><BR><b>Factura (cliente): </b><BR>[" + strFc +"].";
						}
					}														
				}
				
				//MMatchInv
				//MPayment
				where = "AND docstatus = '" + MPayment.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("dateacct","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MPayment> payment = MPayment.getPayments(where, getCtx(), null);
				List<String> pagos = new ArrayList<String>();
				String strPagos="";
				
				if(payment != null){
					if(payment.size() > 0){
						for(int i=0 ; i < payment.size(); i++){
							MPayment pmt = payment.get(i);
							pagos.add(pmt.getDocumentNo());
						}
						
						if(pagos.size() > 0){
							strPagos = convierteCadena(pagos);
							String msj = Msg.getMsg(getCtx(), "Pagos");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strPagos + "].";//"<html><BR><b>Pagos: </b>[" + strPagos + "].";
						}
					}
				}
													
				//MMovement				
				where = "AND docstatus = '" + MMovement.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("MovementDate","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MMovement> mov = MMovement.getMovements(where, getCtx());
				List<String> movs = new ArrayList<String>();
				String strMovs="";
				
				if(mov != null){
					if(mov.size() > 0){
						for(int i=0 ; i < mov.size(); i++){
							MMovement mm = mov.get(i);
							movs.add(mm.getDocumentNo());
						}
						
						if(movs.size() > 0){
							strMovs = convierteCadena(movs);
							String msj = Msg.getMsg(getCtx(), "MoverInventario");
							strRet = strRet + "<BR><BR><b> " + msj + "</b><BR>[" + strMovs + "].";//"<html><BR><b>Mover Inventario: </b>[" + strMovs + "].";
						}
					}
				}
				
				//MInventory
				where = "AND docstatus = '" + MInventory.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("MovementDate","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MInventory> inventories = MInventory.getMInventories(where, getCtx());
				List<String> inv = new ArrayList<String>();
				String strInv = "";
				
				if(inventories != null){
					if(inventories.size() > 0){
						for(int j=0 ; j < inventories.size(); j++){
							MInventory mi = inventories.get(j);
							inv.add(mi.getDocumentNo());
						}
						
						if(inv.size() > 0){
							strInv = convierteCadena(inv);
							String msj = Msg.getMsg(getCtx(), "InventarioFisico");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strInv + "].";//"<html><BR><BR><b>Inventario Fisico de Almacen - Salida al gasto: </b><BR>[" + strInv + "].";
						}
					}
				}
				
				//MBankStatement
				where = " AND docstatus = '" + MBankStatement.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("StatementDate","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MBankStatement> banks = MBankStatement.getBankStatements(where, getCtx()); 
				List<String> bs = new ArrayList<String>();
				String strBs = "";
				if(banks != null){
					if(banks.size() > 0){
						for(int i = 0 ; i < banks.size(); i++){
							MBankStatement bStatement = banks.get(i);
							bs.add(bStatement.getDocumentNo());
						}
						
						if(bs.size() > 0){
							strBs = convierteCadena(bs);
							String msj = Msg.getMsg(getCtx(), "EstadosCuenta");
							strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strBs + "].";//"<html><BR><BR><b>Estados de Cuenta Bancarios: </b><BR>[" + strBs + "].";
						}
					}
				}
								
				//MJournalBatch
				//--Desde Diario (MJournal)
				where = " AND docstatus = '" + MJournal.DOCSTATUS_Completed + "' AND to_char("+DB.truncDate("DateAcct","year")+",'yyyy') = '" + year.getYear() + "' AND Posted != 'Y' ";
				List<MJournal> journals = MJournal.getJournals(where, getCtx());
				List<String> js = new ArrayList<String>();
				String strJs = "";
				
				if(journals != null){
					if(journals.size() > 0){
						for(int j=0; j < journals.size();j++){
							MJournal jl = journals.get(j);
							MJournalBatch jb = new MJournalBatch(getCtx(), jl.getGL_JournalBatch_ID(), null);
							
							if(!js.contains(jb.getDocumentNo())){
								js.add(jb.getDocumentNo());
							}
						}
						
						strJs = convierteCadena(js);
						String msj = Msg.getMsg(getCtx(), "PolizaDiario");
						strRet = strRet + "<BR><BR><b>" + msj + "</b><BR>[" + strJs + "].";//"<html><BR><BR><b>Captura de Poliza de Diario: </b><BR>[" + strJs + "].";
					}
				}
				
				
				if(docRM.size() > 0 || docEC.size() > 0 || fp.size() > 0 || fc.size() > 0 || pagos.size() > 0 || movs.size() > 0 || inv.size() > 0 || bs.size() > 0 || js.size() > 0){
					//JOptionPane pane = new JOptionPane();
					//pane.showMessageDialog(null, strRet , "Validando Cierre de Ejercicio. ", JOptionPane.WARNING_MESSAGE);
					//String errorPosteoDoc = Msg.getMsg(getCtx(), "ErrorPosteoDocumentos");
					return strRet;						
				}
		
		return "";
	}
	
	
	/**
	 * Convierte una lista en cadena
	 * @param list
	 * @param cadena
	 * @author rosy
	 * **/
	protected String convierteCadena(List<String> list){
		String cadena="";
		
		if(list != null){
			for(int i=0 ; i < list.size() ; i++){
				if(i==0)
				cadena = cadena + list.get(i);
				else{
					cadena = cadena + ", " + list.get(i);
				
					if(i%10 == 0){
						cadena = cadena + " <BR>";
						//cadena = cadena + " \n";
					}
					
					if(i >= 100){
						cadena = cadena + " y otros " + (list.size() - i) + " documentos.";
						break;
					}
				}
			}
		}		
		return cadena;
	}
}
