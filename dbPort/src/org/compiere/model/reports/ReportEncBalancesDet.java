package org.compiere.model.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;
 /*
  * Bean para Reporte Encounter Balances Detail
  * Actualizado el 10/07/2013 para soporte de Extensiones
  * 
  */



public class ReportEncBalancesDet {
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (ReportEncBalancesDet.class);

    public String EXME_CtaPac_ID;
	public String AD_Client_ID;
	public String AD_Org_ID;
	public int    encounter;
	public int    extension;
	public String mrn;
	public String billingType;
	public String status;
	public String step;
	public int	  cbPartnerPriID;
	public String primaryFC;
	public String primaryPC;
	public int    cbPartnerCurrID;
	public String currentFC;
	public String currentPC;
	public String payerName;
	public int    daysAged;
	public Timestamp lastPmt;
	public double balance;
	public double totalCharges;
	public double percent;

		public ReportEncBalancesDet(ResultSet result, Properties ctx){
			
			try {
				encounter = result.getInt("encounter");
				extension = result.getInt("extension");
				mrn = result.getString("mrn");
				billingType = result.getString("billingType");
				status = result.getString("status");
				step = result.getString("step");
				//cbPartnerPriID = result.getInt("cbPartnerPriID");
				primaryFC = result.getString("primaryFC");
				primaryPC = result.getString("primaryPC");
				//cbPartnerCurrID = result.getInt("cbPartnerCurrID");
				currentFC = result.getString("currentFC");
				currentPC = result.getString("currentPC");
				payerName = result.getString("payerName");
				daysAged = result.getInt("daysAged");
				
				lastPmt = result.getTimestamp("lastPmt");
				
				balance = result.getDouble(Utilerias.getMsg(ctx, "title.corteCaja"));
				totalCharges = result.getDouble("totalCharges");
				percent = result.getDouble("percent");
			} catch (SQLException e) {
				log .log(Level.SEVERE, "", e);
			}			
		}
		
		public String getEXME_CtaPac_ID() {	return EXME_CtaPac_ID; }
		public String getAD_Client_ID() { return AD_Client_ID; }
		public String getAD_Org_ID() { return AD_Org_ID; }
		public String getEncounter() { return String.valueOf(encounter); }
		public String getExtension() { return String.valueOf(extension); }
		public String getMrn() { return mrn; }
		public String getBillingType() {return billingType; }
		public String getStatus() { return status; }
		public String getStep() { return step; }
		public int getCbPartnerPriID() { return cbPartnerPriID; }	
		public String getPrimaryFC() { return primaryFC; }
		public String getPrimaryPC() { return primaryPC; }
		public int getCbPartnerCurrID() { return cbPartnerCurrID; }
		public String getCurrentFC() { return currentFC; }	
		public String getCurrentPC() { return currentPC; }
		public String getPayerName() { return payerName; }
		public String getDaysAged() { 

			String retVal;
			if(daysAged >= 0){
				retVal = String.valueOf(daysAged);
			}else{
				retVal = "N/A";
			}
			return retVal;
			
		}
		public String getLastPmt() {
			
			String retVal;
			if(lastPmt != null){
				retVal = Constantes.sdfFecha(Env.getCtx()).format(lastPmt);
			}else{
				retVal = "N/A";
			}
			return retVal;
		}
		
		public String getBalance() {	return BillingReports.returnWithFormat(balance);	}
		public String getTotalCharges() {return BillingReports.returnWithFormat(totalCharges);		}
		public String getPercent() {	return BillingReports.returnWithPercentajeFormat(percent);	}

		
	
}
