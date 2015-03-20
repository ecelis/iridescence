package org.compiere.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MEXMEProvisionVenta {

	/**
	 * Proceso para crear las polizas contables apartir de provision de venta
	 * @param ctx
	 * @param C_Period_ID
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static String poliza(Properties ctx, int C_Period_ID, String fechaStr, String documentNo, String trxName) 
	throws Exception {

		String result = null;
		MAcctSchema esquemaContable = new MAcctSchema(ctx, Env.getContextAsInt(ctx, "$C_AcctSchema_ID"), trxName);
		int gl_Category_ID = MEXMEGLCategory.getGLCategory(ctx, null);
		Timestamp fecha = DB.getTimestampForOrg(Env.getCtx());
		Timestamp fechaActual = DB.getTimestampForOrg(Env.getCtx());
		if(fechaStr!=null){
			Date fechaDate = Constantes.getSdfFecha().parse(fechaStr);
			fecha = new Timestamp(fechaDate.getTime());
		}
		
		/**
		 * Crear el JournalBatch
		 */
		MJournalBatch jourBatch = new MJournalBatch(ctx, 0, trxName);
		
		jourBatch.setC_Period_ID(C_Period_ID);
		jourBatch.setDescription("Provisi�n de Ventas");
		jourBatch.setGL_Category_ID(gl_Category_ID>0?gl_Category_ID:0);
		jourBatch.setDateDoc(fechaActual);
		jourBatch.setDateAcct(fecha);
		jourBatch.setC_DocType_ID(MEXMEDocType.getOfName(ctx, "GL Journal Batch", trxName));
		jourBatch.setC_Currency_ID(esquemaContable.getC_Currency_ID());
		
		if(documentNo.trim().length() > 0){
			int org = Env.getContextAsInt(ctx, "#AD_Org_ID");
			boolean existe = MEXMEJournalBatch.docNoExist(ctx, org, C_Period_ID, documentNo, null);
			if(existe){
				throw new Exception("documentNo Exists");
			}else{
				jourBatch.setDocumentNo(documentNo);
			}
		}
		
		if (!jourBatch.save(trxName)) {
			throw new Exception("NO BATCH");
		}
		result = jourBatch.getDocumentNo();

		/**
		 * Crear el Journal
		 */
		MJournal journal = new MJournal(ctx, 0, trxName);
		journal.setC_Currency_ID(esquemaContable.getC_Currency_ID());
		journal.setC_AcctSchema_ID(esquemaContable.getC_AcctSchema_ID());
		int docJournal = MEXMEDocType.getOfName(ctx, "GL Journal@", null);
		if(docJournal<=0){
			result = null;
			throw new Exception("NO JOURNAL LINE");
		}
		journal.setC_DocType_ID(docJournal);
		journal.setDescription("Provisi�n de Venta");
		journal.setGL_Category_ID(jourBatch.getGL_Category_ID());
		journal.setC_Period_ID(jourBatch.getC_Period_ID());
		journal.setGL_JournalBatch_ID(jourBatch.getGL_JournalBatch_ID());
		journal.setDateAcct(fecha);
		journal.setDateDoc(fechaActual);
		
		//Conversiones
		if(journal.getC_Currency_ID()!= jourBatch.getC_Currency_ID()){
			MConversionRate conversionRate = MEXMEConversionType.getConversionType(ctx, journal.getC_Currency_ID(), 
					journal.getC_Currency_ID(), null);
			if(conversionRate==null){
				result = null;
				throw new Exception("NO JOURNAL LINE");
			}
			journal.setC_ConversionType_ID(conversionRate.getC_ConversionType_ID());
			journal.setCurrencyRate(conversionRate.getMultiplyRate());//Multiply Rate

		} else {
			int conversion = MConversionType.getDefault(Env.getContextAsInt(ctx,"#AD_Client_ID"));
			if(conversion<=0){
				result = null;
				throw new Exception("NO JOURNAL LINE");
			}
			journal.setC_ConversionType_ID(conversion);
		}
		
		if (!journal.save(trxName)) {
			result = null;
			throw new Exception("NO JOURNAL");
		}
		
		/**
		 * Crear el JournalLine
		 */
		//Tomar los datos de la tabla de trabajo que se lleno previamente por sesion
		final List<MJournalLine> lista = MEXMETTProvision.getLstCtaPac(ctx, trxName);
		int cont = 1;
		
		//Crea linea x cta,categoria de prod, moneda, org trx
		for (MJournalLine journalLine : lista) {

			journalLine.setLine(cont*10);
			journalLine.setDateAcct(fecha);
			journalLine.setC_ConversionType_ID(journal.getC_ConversionType_ID());
			journalLine.setAmtAcctDr(journalLine.getAmtSourceDr());
			journalLine.setAmtAcctCr(journalLine.getAmtSourceCr());
			journalLine.setGL_Journal_ID(journal.getGL_Journal_ID());
			if(journalLine.getC_Currency_ID()<=0)
				journalLine.setC_Currency_ID(journal.getC_Currency_ID());
			
			if(journal.getC_Currency_ID()!= journalLine.getC_Currency_ID()){
				MConversionRate conversionRate = MEXMEConversionType.getConversionType(ctx, journal.getC_Currency_ID(), journalLine.getC_Currency_ID(), null);
				if(conversionRate==null){
					result = null;
					throw new Exception("NO JOURNAL LINE");
				}
				journalLine.setC_ConversionType_ID(conversionRate.getC_ConversionType_ID());
				journalLine.setCurrencyRate(conversionRate.getMultiplyRate());//Multiply Rate
				journalLine.setAmtAcctCr(MConversionRate.convert(ctx, journalLine.getAmtSourceCr(), 
						journal.getC_Currency_ID(), journalLine.getC_Currency_ID(), 
						journalLine.getDateAcct(), journalLine.getC_ConversionType_ID(),
						journalLine.getAD_Client_ID(), journalLine.getAD_Org_ID()));
				journalLine.setAmtAcctDr(MConversionRate.convert(ctx, journalLine.getAmtSourceDr(), 
						journal.getC_Currency_ID(), journalLine.getC_Currency_ID(), 
						journalLine.getDateAcct(), journalLine.getC_ConversionType_ID(),
						journalLine.getAD_Client_ID(), journalLine.getAD_Org_ID()));
			}
			
			if (!journalLine.save(trxName)) {
				result = null;
				throw new Exception("NO JOURNAL");
			}
			cont++;
		} 
		return result;
	}
}
