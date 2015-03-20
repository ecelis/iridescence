package org.compiere.process;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MHL7MessageConf;
import org.compiere.util.confHL7.MirthClient;

import com.mirth.connect.client.core.Client;

public class CreateOutChannel extends SvrProcess {
		
	private Properties ctx;
	@SuppressWarnings("unused")
	private String trxName;
	private Client mirthClient; 
	private MHL7MessageConf conf = null;
	String inOut;

	@Override
	protected void prepare() {
		ctx = getCtx();
		trxName = get_TrxName();
		
		conf = new MHL7MessageConf(ctx, getRecord_ID(), null );
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("InOut"))
				inOut = para[i].getParameter().toString();			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		
		
		

	}

	@Override
	protected String doIt() throws Exception {
		String ret =null;
		
		mirthClient = MirthClient.getClient(getCtx());

		if(conf.updateChannel(mirthClient,inOut,true)){
			ret ="Succes";
			addLog("Succes");			
		}else{
			ret = "Failure";
			addLog("Failure");
		}
	
		return ret;
	}

}
