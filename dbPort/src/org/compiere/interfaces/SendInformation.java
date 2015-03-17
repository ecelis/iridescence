package org.compiere.interfaces;

import java.util.logging.Level;

import org.compiere.model.MMessage;
import org.compiere.model.X_HL7_Note;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Msg;

public class SendInformation extends SvrProcess {
	
	private String processName = null;
	private String processDescription = null;
	
	@Override
	protected String doIt() throws Exception {
		X_HL7_Note note = new X_HL7_Note(getCtx(), 0, get_TrxName());
		note.setProcess(processName);
		note.setRecord_ID(getRecord_ID());
		note.setStatus(X_HL7_Note.STATUS_InProgress);
		note.setTextMsg(Msg.getMsg(getCtx(), "GenerateMessage"));
		note.setDescription(processDescription);
		note.setAD_User_ID(this.getAD_User_ID());
		note.setAD_Message_ID(MMessage.getAD_Message_ID(getCtx(), "GenerateMessage"));
		if(!note.save(get_TrxName()))
		{
			return "@Error@";
		}
		return "@OK@";
	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(name.equals("Process"))
				processName = para[i].getParameter().toString();
			else if(name.equals("Description"))
				processDescription = para[i].getParameter().toString();
			else 
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

}
