package org.compiere.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEBatchClaimD;
import org.compiere.model.MEXMEBatchClaimH;
import org.compiere.model.MEXMEClaimMessage;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MHL7MessageConf;
import org.compiere.model.X_EXME_BatchClaimD;
import org.compiere.model.X_HL7_Dashboard;
import org.compiere.model.X_HL7_MessageConf;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.compiere.util.Trx;
import org.compiere.util.confHL7.MessageGenerator;
import org.compiere.util.confHL7.ObjX12Edi;
import org.compiere.util.confHL7.ObjX12Edi.X12Segment;

import com.mirth.connect.client.core.ClientException;

public class ClaimGenerator extends SvrProcess {
	
	private int idHL7Dashboard;
	private int idCBPartner;
	Properties ctx;
	private Trx trx = null;
	private boolean internalTrx = Boolean.TRUE;
	
	private int numTS;
	
	private final static String MSG_TRX_BATCH = "BatchClaim_Generate";
	private final static String MSG_TRX_CLAIM = "SingleClaim_Generate";
	private final static String MSG_TRX_STMT = "BatchStmt_Generate";
	private final static String ERROR_SAVE = "Saving on HL7_Dashboard from Java";
//	private String closingRS = "Closing resultset";
	private final static String GENERATING_STATEMENTS = "Generating Statements";
//	private String generateMsg = "Generating Message";
//	private String saveXML = "Saving XML Data on HL7_Dashboard";
	private final static String MSG_SUCCESS = "Dashboard record succesfully created.";
	private final static String MSG_FAILURE = "Dashboard record could not be created.";
	private final static String MSG_STATUS = "Dashboard Save:";
	
	private final static String MSG_NOERROR = "NO.ERROR";
	private final static String MSG_NODATA = "No Data Found";
	
	public ClaimGenerator() {
		init();
	}
		
	public ClaimGenerator(Trx trx) {
		this.trx = trx;
		internalTrx = Boolean.FALSE;
	}
	
	public ClaimGenerator(Properties ctx) {
		this.ctx = ctx;
		init();
	}

	@Override
	protected String doIt() throws Exception {
		return "Success";
	}

	@Override
	protected void prepare() {
		
	}
	
	private void init() {
		idHL7Dashboard = 0;
		numTS = 0;
		idCBPartner = 0;
	}
	
	private String headerClaim(String idTrans, String confType) {
		ObjX12Edi x12 = new ObjX12Edi("~*:");
		//TODO: Mejorar la obtencion dinamica de esta informacion
		String isa06;
		String isa08;
		String versionMsg;
		
		if (X_HL7_MessageConf.CONFTYPE_InstitutionalClaim.equals(confType) || 
			X_HL7_MessageConf.CONFTYPE_ProfessionalClaim.equals(confType) ||
			X_HL7_MessageConf.CONFTYPE_PIStatement.equals(confType)) {
			isa06 = "161622439      ";
			isa08 = "161622439      ";
			versionMsg = "004010X098";
		} else {
			isa06 = "SUBnnn         ";
			isa08 = "YTH837         ";
			if (X_HL7_MessageConf.CONFTYPE_OutpatientProfessionalClaim.equals(confType)) {
				versionMsg = "005010X223A1";
			} else {
				versionMsg = "005010X223A2";
			}
			
		}
		
		SimpleDateFormat sdfHoraX12 = Constantes.getCustom("HHmm");//Lama
		
		X12Segment isa = x12.newSegment("ISA", 1);
		isa.addField("00", 1);
		isa.addField("          ", 2);
		isa.addField("00", 3);
		isa.addField("          ", 4);
		isa.addField("ZZ", 5);
		isa.addField(isa06, 6);
		isa.addField("ZZ", 7);
		isa.addField(isa08, 8);
		isa.addField(Constantes.getCustom("yyMMdd").format(DB.convert(ctx, new Date())), 9);
		isa.addField(sdfHoraX12.format(DB.convert(ctx, new Date())), 10);
		isa.addField("U", 11);
		isa.addField(versionMsg.substring(0, 5), 12);
		isa.addField(idTrans, 13);
		isa.addField("1", 14);
		isa.addField("P", 15);
		isa.addField(":", 16);
		
		X12Segment gs = x12.newSegment("GS", 2);
		gs.addField("HC", 1);
		gs.addField(isa06, 2);
		gs.addField(isa08, 3);
		gs.addField(Constantes.getSdfFecha_CDA(ctx).format(DB.convert(ctx, new Date())), 4);
		gs.addField(sdfHoraX12.format(DB.convert(ctx, new Date())), 5);
		gs.addField(":idDash", 6);
		gs.addField("X", 7);
		gs.addField(versionMsg, 8);
		
		return x12.toString();
	}
	
	private String footerClaim(String idTrans) {
		ObjX12Edi x12 = new ObjX12Edi("~*:");
		
		X12Segment ge = x12.newSegment("GE", 1);
		ge.addField(String.valueOf(numTS), 1);
		ge.addField(":idDash", 2);
		
		X12Segment iea = x12.newSegment("IEA", 2);
		iea.addField("1", 1);
		iea.addField(idTrans, 2);
		
		return x12.toString();
	}
	
	private int getNumSegments(String message) {
		return StringUtils.countMatches(message, "~");
	}
	
	/*
	public void setC_BPartner_ID(int idCBPartner) {
		this.idCBPartner = idCBPartner;
	}
	
	public int getC_BPartner_ID() {
		return idCBPartner;
	}*/
	
	public String messageConstruct(List<MEXMECtaPacExt> lstCtaPacExt, String idTrans, String confType, int idCBPartner, int idDashboard) {
		this.idCBPartner = idCBPartner;
		String message = messageConstruct(lstCtaPacExt, idTrans, confType, null);
		if (idDashboard > 0) {
			X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, idDashboard, null);
			message = decryptMessage(message, (MHL7MessageConf) panel.getHL7_MessageConf());
		} else {
			idDashboard = 1;
		}
		message = message.replace(":idDash", StringUtils.leftPad(String.valueOf(idDashboard), 9, '0'));
		
		return message;
	}
	
	public String messageConstruct(Properties ctx, int EXME_BatchClaimH_ID, String idTrans, String confType, String trxName) {
		StringBuilder message = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		int numSeg = 0;
		List<MEXMEBatchClaimD> lstBatchD = new ArrayList<MEXMEBatchClaimD>();
		
		/** Obtenemos la lista de extensiones a incluir en el Batch**/
		lstBatchD = Arrays.asList(MEXMEBatchClaimD.gets(ctx, EXME_BatchClaimH_ID, trxName));
		
		// Header of Transaction Message
		message.append(headerClaim(idTrans, confType));
		numSeg = getNumSegments(message.toString());
		
		// Recover every claim string from the table
		for (MEXMEBatchClaimD batchD : lstBatchD) {
			// Get message for a claim
			StringBuilder claim = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			claim.append(MEXMEClaimMessage.getMessage(ctx, batchD.getEXME_CtaPacExt_ID(), batchD.getC_BPartner_ID(), confType, trxName));
			
			if (claim.toString().isEmpty() || claim.toString().startsWith(MSG_NOERROR) || claim.toString().startsWith(MSG_NODATA)) {
				MEXMECtaPacExt ctaPacExt = new MEXMECtaPacExt(ctx, batchD.getEXME_CtaPacExt_ID(), trx.getTrxName());
				if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)){
					ctaPacExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
				} else {
					ctaPacExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
				}
				
				ctaPacExt.save(trxName);
				
			} else {
				int numTemp = getNumSegments(claim.toString());
				claim.append("SE*").append(numSeg+numTemp).append('*').append(batchD.getEXME_CtaPacExt_ID()).append('~');
				// Append to final message
				message.append(claim.toString());
				numTS++;
				numSeg = getNumSegments(message.toString());
				idCBPartner = 0;
			}
		}
		
		// Footer of Transaction Message
		message.append(footerClaim(idTrans));
		
		return message.toString();
	}
	
	public String messageConstruct(List<MEXMECtaPacExt> lstCtaPacExt, String idTrans, String confType, String trxName) {
		StringBuilder message = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		int numSeg = 0;
		
		// Header of Transaction Message
		message.append(headerClaim(idTrans, confType));
		numSeg = getNumSegments(message.toString());
		
		// Recover every claim string from the table
		for (MEXMECtaPacExt ctaPacExt : lstCtaPacExt) {
			// Get message for a claim
			StringBuilder claim = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			
			if (idCBPartner == 0) {
				MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getBillingInsurance(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), confType);
				
				if (aseg != null) {
					idCBPartner = aseg.getC_BPartner_ID();
				}
			}
			
			claim.append(MEXMEClaimMessage.getMessage(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), idCBPartner, confType, trxName));
			
			if (claim.toString().equalsIgnoreCase(Constantes.ERROR)) {
				if (confType.equalsIgnoreCase(MHL7MessageConf.CONFTYPE_ProfessionalClaim)){
					ctaPacExt.setProfessionalStatus(MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
				} else {
					ctaPacExt.setInstitutionalStatus(MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
				}
				
				ctaPacExt.save(trxName);
				
			} else {
				int numTemp = getNumSegments(claim.toString());
				claim.append("SE*").append(numSeg+numTemp).append('*').append(ctaPacExt.getEXME_CtaPacExt_ID()).append('~');
				// Append to final message
				message.append(claim.toString());
				numTS++;
				numSeg = getNumSegments(message.toString());
				idCBPartner = 0;
			}
		}
		
		// Footer of Transaction Message
		message.append(footerClaim(idTrans));
		
		return message.toString();
	}
	
//	private String msgConstruct(MEXMECtaPac ctaPac, String idTrans, String confType) {
//		StringBuilder message = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//		try {
//			MessageGenerator mg = new MessageGenerator(ctx, false);
//			int idDash = mg.getMessage(String.valueOf(ctaPac.getEXME_CtaPac_ID()), MEXMECtaPac.Table_Name, confType, null);
//			if (idDash > 0) {
//				X_HL7_Dashboard d = new X_HL7_Dashboard(ctx, idDash, null);
//				message.append(d.getMessage());
//			}
//		} catch (ClientException e1) {
//			log.log(Level.SEVERE, generateMsg, e1);
//		}
//		
//		return message.toString();
//	}
	
	private String stmtConstruct(List<MEXMECtaPacExt> lstCtaPacExt, String idTrans, String confType) {
		StringBuilder message = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		try {
			MessageGenerator mg = new MessageGenerator(ctx, false);
			for (MEXMECtaPacExt extension: lstCtaPacExt) {
				int idDash = 
						mg.getMessage(
								String.valueOf(extension.getEXME_CtaPac_ID()), 
								MEXMECtaPac.Table_Name, 
								confType, 
								null
						);
				
				if (idDash > 0) {
					X_HL7_Dashboard d = new X_HL7_Dashboard(ctx, idDash, null);
					message.append(d.getMessage());
				} else {
					return null;
				}
			}
		} catch (ClientException e1) {
			log.log(Level.SEVERE, GENERATING_STATEMENTS, e1);
			return null;
		}
		
		return message.toString();
	}
	
//	private boolean saveXML(int HL7_Dashboard_ID, String message) {
//		boolean success = false;
//		try {
//			message = message.replace(":idDash", StringUtils.leftPad(String.valueOf(HL7_Dashboard_ID), 9, '0'));
//			String sql = "SELECT message FROM HL7_Dashboard WHERE HL7_Dashboard_ID = ? FOR UPDATE";
//			PreparedStatement pstmt = DB.prepareStatement(sql, null);
//			pstmt.setInt(1, HL7_Dashboard_ID);
//			ResultSet rs = pstmt.executeQuery();
//			rs.next();
//			Clob clob = rs.getClob(1);
//			Writer writer = clob.setCharacterStream(0);
//			writer.write(message.toCharArray());
//			writer.flush();
//			writer.close();
//			try {
//				if (pstmt != null) pstmt.close();
//				if (rs != null) rs.close();
//			} catch (Exception e) {
//				log.log(Level.SEVERE, closingRS, e);
//			}
//			pstmt = null;
//			rs = null;
//			success = true;
//		} catch (Exception e) {
//			log.log(Level.SEVERE, saveXML, e);
//			success = false;
//		}
//		return success;
//	}
	
	private X_HL7_Dashboard createDashboard(Properties ctx, int RecordID, String message, int idMessageConf) {
		
		//Trx m_trx = Trx.get(Trx.createTrxName(MSG_TRX_BATCH), true);
		
		boolean success = Boolean.FALSE;
		
		X_HL7_Dashboard panel = new X_HL7_Dashboard(ctx, 0, trx.getTrxName());
		panel.setRecord_ID(RecordID);
		panel.setHL7_MessageConf_ID(idMessageConf);
		panel.setStatus("NS");
		panel.setMessage(message);

		if (panel.save(trx.getTrxName())) {
			success = Boolean.TRUE;
			message = 
					message.replace(":idDash", StringUtils.leftPad(String.valueOf(panel.getHL7_Dashboard_ID()), 9, '0'));
			
			panel.setMessage(message);
		
			if (!panel.save(trx.getTrxName())) {
				log.log(Level.SEVERE, ERROR_SAVE);
				panel = null;
				success = Boolean.FALSE;
			}
		} else {
			log.log(Level.SEVERE, ERROR_SAVE);
			panel = null;
		}
		
		//StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (!success) {
			panel = null;
		}
		return panel;
	}
	
	public int generateBatchClaim(Properties ctx, int EXME_BatchClaimH_ID, String confType) {
		this.ctx = ctx;
		if (trx == null) {
			trx = Trx.get(Trx.createTrxName(MSG_TRX_BATCH), true);
		}
		String message = 
					messageConstruct(ctx, EXME_BatchClaimH_ID, StringUtils.leftPad(String.valueOf(EXME_BatchClaimH_ID), 9, '0'), confType, trx.getTrxName());
			
		List<Integer> messageConfIds = 
					MHL7MessageConf.getMessageConfIds(
							MEXMECtaPacExt.Table_Name, 
							confType,
							Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
							Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID")
					);
			
		int idMC = 0;
		if (!messageConfIds.isEmpty()) {
			idMC = messageConfIds.get(0).intValue();
		}
			
		X_HL7_Dashboard panel = createDashboard(ctx, EXME_BatchClaimH_ID, message, idMC);
		
		if (panel != null) {
			idHL7Dashboard = panel.getHL7_Dashboard_ID();
			log.log(Level.INFO, MSG_STATUS, MSG_SUCCESS);
		} else {
			idHL7Dashboard = 0;
			log.log(Level.INFO, MSG_STATUS, MSG_FAILURE);
		}
		if (internalTrx) {
			if (idHL7Dashboard > 0) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}
			Trx.close(trx);
			trx = null;
		}

		return idHL7Dashboard;
	}
	
	public int generateClaim(Properties ctx, int EXME_CtaPacExt_ID, String confType) {
		if (trx == null) {
			trx = Trx.get(Trx.createTrxName(MSG_TRX_CLAIM), true);
		}
		List<MEXMECtaPacExt> lstCtaPacExt = new ArrayList<MEXMECtaPacExt>();
		this.ctx = ctx;
		lstCtaPacExt.add(new MEXMECtaPacExt(ctx, EXME_CtaPacExt_ID, trx.getTrxName()));
		String message = messageConstruct(lstCtaPacExt, StringUtils.leftPad(String.valueOf(EXME_CtaPacExt_ID), 9, '0'), confType, trx.getTrxName());
		
		List<Integer> messageConfIds = 
			MHL7MessageConf.getMessageConfIds(
					MEXMECtaPacExt.Table_Name, 
					confType,
					Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
					Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID")
			);
	
		int idMC = 0;
	
		if (!messageConfIds.isEmpty()) {
			idMC = messageConfIds.get(0).intValue();
		}
		
		X_HL7_Dashboard panel = createDashboard(ctx, EXME_CtaPacExt_ID, message, idMC);
		
		if (panel != null) {
			idHL7Dashboard = panel.getHL7_Dashboard_ID();
			log.log(Level.INFO, MSG_STATUS, MSG_SUCCESS);
		} else {
			idHL7Dashboard = 0;
			log.log(Level.INFO, MSG_STATUS, MSG_FAILURE);
		}
		if (internalTrx) {
			if (idHL7Dashboard > 0) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}
			Trx.close(trx);
			trx = null;
		}
		
		return idHL7Dashboard;
	}
	
	public int generateStmt(Properties ctx, int EXME_BatchClaimH_ID, String confType) {
		if (trx == null) {
			trx = Trx.get(Trx.createTrxName(MSG_TRX_STMT), true);
		}
		boolean success = Boolean.TRUE;
		List<MEXMECtaPacExt> lstCtaPacExt = new ArrayList<MEXMECtaPacExt>();
		this.ctx = ctx;
		
		lstCtaPacExt = Arrays.asList(MEXMEBatchClaimD.getsCtaPacExt(ctx, EXME_BatchClaimH_ID, trx.getTrxName()));
		
		String message = 
			stmtConstruct(
					lstCtaPacExt, 
					StringUtils.leftPad(String.valueOf(EXME_BatchClaimH_ID), 9, '0'), 
					confType
			);
		
		List<Integer> messageConfIds = 
			MHL7MessageConf.getMessageConfIds(
					MEXMECtaPac.Table_Name, 
					confType,
					Env.getContextAsInt(Env.getCtx(), "#AD_Client_ID"),
					Env.getContextAsInt(Env.getCtx(), "#AD_Org_ID")
			);
		
		int idMC = 0;
		
		if (!messageConfIds.isEmpty()) {
			idMC = messageConfIds.get(0).intValue();
		}
		
		if (message != null) {
			X_HL7_Dashboard panel = createDashboard(ctx, EXME_BatchClaimH_ID, message, idMC);
			
			if (panel != null) {
				idHL7Dashboard = panel.getHL7_Dashboard_ID();
				MEXMEBatchClaimH batchH = new MEXMEBatchClaimH(ctx, EXME_BatchClaimH_ID, trx.getTrxName());
				batchH.setHL7_Dashboard_ID(panel.getHL7_Dashboard_ID());
				batchH.setStatus(X_EXME_BatchClaimD.STATUS_Accepted);
			
				if (!batchH.save(trx.getTrxName())) {
					log.log(Level.SEVERE, "HL7_Dashboard_ID in BatchClaimH has not been set");
					idHL7Dashboard = 0;
					success = Boolean.FALSE;
				}
			} else {
				idHL7Dashboard = 0;
				success = Boolean.FALSE;
			}
		} else {
			idHL7Dashboard = 0;
			success = Boolean.FALSE;
		}
		
		if (success) {
			log.log(Level.INFO, MSG_STATUS, MSG_SUCCESS);
		} else {
			log.log(Level.INFO, MSG_STATUS, MSG_FAILURE);
		}
		if (internalTrx) {
			if (idHL7Dashboard > 0) {
				Trx.commit(trx);
			} else {
				Trx.rollback(trx);
			}
			Trx.close(trx);
			trx = null;
		}
		
		return idHL7Dashboard;
	}
	
	private String decryptMessage(String encryptedMessage, MHL7MessageConf conf) {

		StringBuilder decryptedMessage = new StringBuilder("");

		StringTokenizer t = null;
		if (X_HL7_MessageConf.CONFTYPE_PIStatement.equals(conf.getConfType())) {
			String sep = conf.getField_Sep().concat(" ");
			t = new StringTokenizer(encryptedMessage, sep, true);
		} else {
			t = new StringTokenizer(encryptedMessage,
								    encryptedMessage.substring(3, 4) + "\n^~", true);
		}

		while (t.hasMoreTokens()) {
			
			String value = t.nextToken();
			
			if (StringUtils.isNotBlank(value)){
				decryptedMessage.append(SecureEngine.decrypt(value));
			}else{
				decryptedMessage.append(value);
			}
			
		}
		// System.out.println(msg.toString());

		return decryptedMessage.toString();

	}
}
