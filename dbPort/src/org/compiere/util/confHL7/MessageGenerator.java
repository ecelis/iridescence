package org.compiere.util.confHL7;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.model.MHL7MessageConf;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Org;
import org.compiere.model.X_EXME_BatchClaimH;
import org.compiere.model.X_HL7_Dashboard;
import org.compiere.process.ClaimGenerator;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;

public class MessageGenerator {

	private MessageSender sender;

	private static CLogger log = CLogger.getCLogger(MessageGenerator.class);

	private Properties ctx;
	private boolean sendMessage;

	private Client mirthClient;

	public MessageGenerator(Properties ctx, boolean sendMessage)
			throws ClientException {

		this.ctx = ctx;
		if (sendMessage) {
			mirthClient = MirthClient.getClient(ctx);
			sender = new MessageSender(mirthClient);
		}
		this.sendMessage = sendMessage;

	}

	public boolean generateMessage(int recordId, String tableName) throws ClientException {
		return generateMessage(recordId, tableName, null, null);
	}
	
	public boolean generateMessage(int recordId, String tableName, String confType, MessageArgs<String, Object> whereArgs)
			throws ClientException{
		boolean res = Boolean.FALSE;
		if(mirthClient == null) {//Lama .- evitar NullPointerException
			res = Boolean.FALSE;
		} else if (MHL7MessageConf.CONFTYPE_ProfessionalClaim.equals(confType) 
				|| MHL7MessageConf.CONFTYPE_InstitutionalClaim.equals(confType)) {
			res = generateClaimMessage(recordId, tableName, confType, whereArgs);
		} else if (MHL7MessageConf.CONFTYPE_PIStatement.equals(confType)) { 
			res = generateStmtMessage(recordId, tableName, confType, whereArgs);
		} else {
			res = generateMessage(String.valueOf(recordId), tableName, confType, whereArgs);
		}
		return res;
	}

	public boolean generateStmtMessage(int recordId, String tableName, String confType, MessageArgs<String, Object> whereArgs)
			throws ClientException{
		ClaimGenerator claimG = new ClaimGenerator();
		int resultado = claimG.generateStmt(ctx, recordId, confType);
		boolean res = Boolean.FALSE;
		if (resultado> 0){
			X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, resultado, null);
			if (sendMessage) {
				res = sender.sendMessage(panel);
				try{
					mirthClient.logout();
				} catch (Exception e) {
					log.log(
							Level.SEVERE,
							"Fallo al hacer logout sobre Mirth, al parecer no afecta el proceso, por eso se incluye en un try/catch/finally hl7_dashboard_id = "+ panel.getHL7_Dashboard_ID(),
							e
					);
				}
			}
		}
		if (res) {
			updatePOs(String.valueOf(recordId), tableName);
		}
		
		return res;
	}

	public boolean generateClaimMessage(int recordId, String tableName, String confType, MessageArgs<String, Object> whereArgs)throws ClientException{
		ClaimGenerator claimG = new ClaimGenerator();
		int resultado = 0;
		boolean res = Boolean.FALSE;
		if (X_EXME_BatchClaimH.Table_Name.equals(tableName)) {
			resultado = claimG.generateBatchClaim(ctx, recordId, confType);
		} else {
			resultado = claimG.generateClaim(ctx, recordId, confType);
		}


		if (resultado> 0){
			X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, resultado, null);
			if (sendMessage) {
				res = sender.sendMessage(panel);
				try{
					mirthClient.logout();
				} catch (Exception e) {
					log.log(
							Level.SEVERE,
							"Fallo al hacer logout sobre Mirth, al parecer no afecta el proceso, por eso se incluye en un try/catch/finally hl7_dashboard_id = "+ panel.getHL7_Dashboard_ID(),
							e
					);
				}
			}
		}
		if (res) {
			updatePOs(String.valueOf(recordId), tableName);
		}

		return res;
	}

	public int getMessage(String recordId, String tableName, String confType, MessageArgs<String, Object> whereArgs)
			throws ClientException {
		List<Integer> messageConfIds = 
				MHL7MessageConf.getMessageConfIds(
						tableName, 
						confType, 
						Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
						Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID")
				);

		int resultado = 0;

		if (!messageConfIds.isEmpty()) {
			MHL7MessageConf conf = new MHL7MessageConf(Env.getCtx(), messageConfIds.get(0), null);
			if (conf.getAccessLevel().equalsIgnoreCase(MHL7MessageConf.ACCESSLEVEL_Organization)){
				if (whereArgs == null){
					whereArgs  = new MessageArgs<String, Object>();
				}
				whereArgs.put(X_AD_Org.COLUMNNAME_AD_Org_ID, Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID"));
			}
			
			CallableStatement stmt = null;
			if (DB.isPostgreSQL()) {
				stmt = DB.prepareCall(" {? = CALL  sc_createmsg(?,?)} ");
			} else if (DB.isOracle()) {
				stmt = DB.prepareCall(" CALL  HL7_CREATEMSG_PROC(?,?,?,?) ");
			}
					

			try {
				if (DB.isPostgreSQL()) {
					stmt.registerOutParameter(1, Types.INTEGER);
					stmt.setString(2, recordId);
					stmt.setInt(3, conf.getHL7_MessageConf_ID());
					stmt.execute();
					resultado = stmt.getInt(1);
				} else if (DB.isOracle()) {
					stmt.registerOutParameter(3, Types.NUMERIC);
					stmt.setString(1, recordId);
					stmt.setInt(2, conf.getHL7_MessageConf_ID());
					stmt.setString(4, whereArgs== null?null:whereArgs.toString());
					stmt.execute();
					resultado = stmt.getInt(3);
				}

				log.info("Ejecutado el proceso de generacion de mensaje para :        "
						+ tableName
						+ " "
						+ recordId
						+ "y conf "
						+ conf.getHL7_MessageConf_ID()
						+ "\n\t\t"
						+ resultado
						+ "\n---------------------------------------------------------");
				stmt.close();
				if (resultado> 0){
					X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, resultado, null);
					if (sendMessage) {
						sender.sendMessage(panel);
					}
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "", e);
			}

			if (sendMessage) {
				try {
					mirthClient.logout();
				} catch (ClientException e) {
					log.log(Level.SEVERE, "", e);
				}

			}
		}

		return resultado;
	}

	public boolean generateMessage(String recordId, String tableName, String confType, MessageArgs<String, Object> whereArgs)throws ClientException{

		boolean res = Boolean.FALSE;
		Integer resultado = 0;
		List<Integer> messageConfIds = 
				MHL7MessageConf.getMessageConfIds(
						tableName, 
						confType, 
						Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
						Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID")
				);
		if(messageConfIds.isEmpty()) {
			log.log(Level.CONFIG, "No config for Table: " + tableName + " config: " + confType);
		}

		for (Integer confId : messageConfIds) {
			MHL7MessageConf conf = new MHL7MessageConf(Env.getCtx(), confId, null);
			if (conf.getAccessLevel().equalsIgnoreCase(MHL7MessageConf.ACCESSLEVEL_Organization)){
				if (whereArgs == null){
					whereArgs  = new MessageArgs<String, Object>();
				}
				whereArgs.put(X_AD_Org.COLUMNNAME_AD_Org_ID, Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID"));
			}
			CallableStatement stmt = null;
			if (DB.isPostgreSQL()) {
				stmt = DB.prepareCall(" { ? = CALL  sc_createmsg(?,?) } ");
			} else if (DB.isOracle()) {
				stmt = DB.prepareCall(" CALL  HL7_CREATEMSG_PROC(?,?,?,?) ");
			}
					

			try {
				if (DB.isPostgreSQL()) {
					stmt.registerOutParameter(1, Types.INTEGER);
					stmt.setString(2, recordId);
					stmt.setInt(3, confId.intValue());
					stmt.execute();
					resultado = stmt.getInt(1);
				} else if (DB.isOracle()) {
					stmt.registerOutParameter(3, Types.NUMERIC);
					stmt.setString(1, recordId);
					stmt.setInt(2, confId.intValue());
					stmt.setString(4, whereArgs== null?null:whereArgs.toString());
					stmt.execute();
					resultado = stmt.getInt(3);
				}

				log.info("Ejecutado el proceso de generacion de mensaje para :        "
						+ tableName
						+ " "
						+ recordId
						+ "y conf "
						+ confId
						+ "\n\t\t"
						+ resultado
						+ "\n---------------------------------------------------------");
				stmt.close();
				if (resultado> 0){
					X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, resultado, null);
					if (sendMessage) {
						res = sender.sendMessage(panel);
					}
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "", e);
			} finally {
				DB.close(stmt);
			}
		}

		if (sendMessage) {
			try{
				mirthClient.logout();
			} catch (Exception e) {
				log.log(
						Level.SEVERE,
						"Fallo al hacer logout sobre Mirth, al parecer no afecta el proceso, por eso se incluye en un try/catch/finally hl7_dashboard_id = "+ resultado,
						e
				);
			}
		}
		
		updatePOs(recordId, tableName);
		return res;
	}

	private void updatePOs(String recordId,String tableName){
		StringTokenizer tokenizer = new StringTokenizer(recordId, ",");

		while (tokenizer.hasMoreElements()) {
			Object object = (Object) tokenizer.nextElement();

			PO po = MTable.get(ctx, tableName).getPO(tableName+"_ID ="+ object, null);
			po.set_ValueOfColumn("IsGenerated", true);
			po.save();

		}
	}
}
