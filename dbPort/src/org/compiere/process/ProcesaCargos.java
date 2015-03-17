package org.compiere.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MEXMEConfigEC;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** 
 * Generación de cargos diarios desde el servidor
 */
public class ProcesaCargos extends SvrProcess {

	/** Lista de clientes a iterar **/
	private List<MClient> lstClient;
	/** Proceso del cargo diario */
	private final ProcesaCargoDiario mProcCDiario = new ProcesaCargoDiario();
	
	@Override
	protected void prepare() {
		//Obtenemos la lista de Clientes existentes
		lstClient = new Query(getCtx(), MClient.Table_Name, "", null).list();
	}

	@Override
	protected String doIt() throws Exception {
		final int clientId = Env.getAD_Client_ID(getCtx());
		final int orgId    = Env.getAD_Org_ID(getCtx());
		final StringBuilder msg =  new StringBuilder();
		
		for(int i= 0; i< lstClient.size(); i++){
			
			//Validamos que el cliente sea activo y no sea system
			if(lstClient.get(i).getAD_Client_ID() >0 && lstClient.get(i).isActive()){
				// Cliente
				final int pADClientID = lstClient.get(i).getAD_Client_ID();
				Env.setContext(getCtx(), "#AD_Client_ID", pADClientID);
				
				// Esquema por cliente
				final MAcctSchema acctSchema = MAcctSchema.getClientAcctSchema(getCtx(),
						Env.getAD_Client_ID(getCtx()))[0];
				if (acctSchema != null) {
					Env.setContext(getCtx(), "$C_Currency_ID",
							acctSchema.getC_Currency_ID());
				}
				
				//Obtenemos las organizacipnes padre de cada cliente
				final List<KeyNamePair> lstOrg = MOrg.getParentOrg(getCtx(), null);
				// Ejecutamos el proceso por cada organización de la lista
				for (int j = 0; j < lstOrg.size(); j++) {
					Env.setContext(getCtx(), "#AD_Org_ID", lstOrg.get(j).getKey());
					int idLocation = new MOrgInfo(new MOrg(getCtx(),  lstOrg.get(j).getKey(), null)).getC_Location_ID();
					int idCountry = new MLocation(getCtx(), idLocation, null).getC_Country_ID();
					Env.setContext(getCtx(), "#C_Country_ID", idCountry);
					
					// configuraciones de precios
					MEXMEConfigPre.setCtx(getCtx(), get_TrxName());
					MEXMEConfigEC.setCtx(getCtx(), get_TrxName());
					
					final String success = executeOrg(getCtx());
					msg.append(success==null?"":success);
				}	
			}		
		}
		
		Env.setContext(getCtx(), "#AD_Client_ID", clientId);
		Env.setContext(getCtx(), "#AD_Org_ID",orgId);
		
		return msg.length()==0?"The daily charges have been applied.":msg.toString();
	}       

	/**
	 * Cargos por organización
	 * @param ctx Contexto del servidor (NO HAY USUARIO cuando corre del lado del servidor)
	 * @return Mensajes de error.
	 */
	private String executeOrg(final Properties ctx){
		// Contexto del servidor
		mProcCDiario.setCtx(ctx);
		return mProcCDiario.executeOrg(new Timestamp(System.currentTimeMillis()));
	}
}
