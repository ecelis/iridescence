package org.compiere.process;

import static org.compiere.util.Utilerias.getMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEBatchClaimD;
import org.compiere.model.MEXMEBatchClaimH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MHL7GenerationTable;
import org.compiere.model.MInvoice;
import org.compiere.model.MTable;
import org.compiere.model.X_EXME_BatchClaimH;
import org.compiere.model.X_HL7_Dashboard;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.model.billing.Billing;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.confHL7.MessageGenerator;

public class MessageGeneratorProcess extends SvrProcess {

	// private String query =
	// "SELECT TABLE.--_ID FROM -- TABLE WHERE TABLE.ISGENERATED = 'N'";

	private ArrayList<String> tableNames = new ArrayList<String>();
	private boolean isBatchClaim = false;
	private String confType;
	private final String genLogStmt = "Generation of Log for Statement Sent.";
	private boolean sendMessage = true;
			
	public MessageGeneratorProcess() {
	}

	// Generacion de Facturas a Paciente por primera vez
	private List <MInvoice> generaInvoice(Properties ctx, int EXME_BatchClaimH_ID) {
		List <MInvoice> lstInvoice = new ArrayList <MInvoice>();
		MEXMEBatchClaimD[] listaD = MEXMEBatchClaimD.gets(getCtx(), EXME_BatchClaimH_ID, null);
		Billing bill = new Billing();
		for (int i = 0; i < listaD.length; i++) {
			MEXMECtaPac ctaPac = new MEXMECtaPac(getCtx(), listaD[i].getEXME_CtaPac_ID(), null);
			MInvoice invoice = bill.process(getCtx(), ctaPac.getLstDetalle(null), ctaPac.getPaciente().getC_BPartner_ID());
			lstInvoice.add(invoice);
		}
		return lstInvoice;
	}
	
	// Generacion de log de envio de statement para paciente
	private void generaLogStmt(int EXME_BatchClaimH_ID, List<MInvoice> lstInvoice) {
		GeneraClaimLog claimLog = new GeneraClaimLog();
		try {
			claimLog.generaLog(getCtx(), EXME_BatchClaimH_ID, lstInvoice);
		} catch (Exception e) {
			log.log(Level.SEVERE, genLogStmt);
		}
	}
	
	@Override
	protected String doIt() throws Exception {

		StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		int recordId = getRecord_ID();

		if (recordId == 0) {

			for (String tableName : tableNames) {
				StringBuffer sqlWhere = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
				sqlWhere.append(" isGenerated  = 'N' AND isActive = 'Y' ")
					    .append(confType!= null ? "AND CONFTYPE = '" +  confType + "'": "")
					    .append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", tableName));
				int[] todosLosIds = MTable.getAllIDs(tableName, sqlWhere.toString(), null);

				for (int unoDeLosIds : todosLosIds) {
					MessageGenerator generator = new MessageGenerator(getCtx(), sendMessage);
					if (confType != null) {
						if (tableName.equals(X_EXME_BatchClaimH.Table_Name)) {
							MEXMEBatchClaimH batchH = new MEXMEBatchClaimH(Env.getCtx(), unoDeLosIds, null);
							msg.append(getMessage(Env.getCtx(), null, "msj.batch")).append(" #").append(batchH.getDocumentNo()).append(", ")
							   .append(getMessage(Env.getCtx(), null, "claim.DetailDoc")).append(": ").append(batchH.getTotalClaims()).append(". ");
						}
						
						
						boolean send = generator.generateMessage(unoDeLosIds, tableName, confType, null);
						if (X_HL7_MessageConf.CONFTYPE_PIStatement.equals(confType) && send) {
							// Genera facturas de paciente
							List <MInvoice> lstInvoice = generaInvoice(Env.getCtx(), unoDeLosIds);
							// Generar registro en ClaimLog
							generaLogStmt(unoDeLosIds, lstInvoice);
						}
						if (send) {
							msg.append(getMessage(Env.getCtx(), null, "msg.ordenServ.completadas"));
						} else {
							msg.append(getMessage(Env.getCtx(), null, "advancedDirectives.msj.error.generarArchivo"));
						}
					} else {
						generator.generateMessage(unoDeLosIds, tableName);
					}
										
				}
			}
			
		} else {
			MessageGenerator generator = new MessageGenerator(getCtx(), sendMessage);
			
			X_HL7_Dashboard panel = new X_HL7_Dashboard(getCtx(), recordId,
					null);
			
			
			//TODO limpiar esta llamada
			generator.generateMessage(recordId, panel.getHL7_MessageConf()
					.getHL7_Process().getAD_Table().getTableName());
		}

		return msg.toString();
	}

	@Override
	protected void prepare() {

		
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String type = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (type.equals("Type")&& para[i].getParameterAsInt()==X_EXME_BatchClaimH.Table_ID)
				isBatchClaim = Boolean.TRUE;
			else if (type.equals("ConfType"))
				confType = para[i].getParameter().toString();
			else if (type.equals("SendMessage")) {
				sendMessage = para[i].getParameter().toString().equals("Y");
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + type);
		}
		if (isBatchClaim){
			tableNames.add(X_EXME_BatchClaimH.Table_Name);
		}else{
			tableNames = getTableNames();
		}
	}

	/** obtener los nombre de las tablas marcadas en HL7_GenerationTables */
	public ArrayList<String> getTableNames() {

		ArrayList<String> tableNames = new ArrayList<String>();
		ArrayList<MHL7GenerationTable> generationTables = MHL7GenerationTable
				.getAll();

		for (MHL7GenerationTable genTable : generationTables) {

			tableNames.add(genTable.getAD_Table().getTableName());

		}

		return tableNames;
	}

}
