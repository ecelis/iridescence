package org.compiere.model.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.SecureEngine;
import org.compiere.util.Utilerias;



public class ReportInsCollection {
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (ReportInsCollection.class);

		public int encounter;
		public int extension;
		public String type;
		public String patientName;
		public String patientType;
		public Timestamp admitDate;
		public Timestamp dischargeDate;
		public String fcName;
		public double totCharges;
		public double totPmts;
		public double collected;
		private Properties ctx;

		public ReportInsCollection(ResultSet result, Properties ctx){
			this.ctx = ctx;
			try {
				encounter = result.getInt("encounter");
				extension = result.getInt("extension");
				type = result.getString("type");
				patientName = SecureEngine.decrypt(result.getString("patientName"));		
				patientType = result.getString("patientType");		
				admitDate = result.getTimestamp("admitDate");		
				dischargeDate = result.getTimestamp("dischargeDate");
				fcName = result.getString("fcName");	
				totCharges = result.getDouble("totCharges");
				totPmts = result.getDouble("totPmts");
				collected = result.getDouble("collected");
			} catch (SQLException e) {
				log .log(Level.SEVERE, "", e);
			}			
		}
		
		public ReportInsCollection(){

		}

		
		public String getEncounter() {
			return String.valueOf(encounter);
		}
		public String getExtension() {
			return String.valueOf(extension);
		}
		public String getType() {
			return type;
		}
		public String getPatientName() {
			return patientName;
		}
		public String getPatientType() {
			return patientType;
		}
		public String getAdmitDate() {
			return Constantes.sdfFecha(ctx).format(admitDate);
		}
		public String getDischargeDate() {
			return Constantes.sdfFecha(ctx).format(dischargeDate);
		}
		public String getFcName() {
			return fcName;
		}
		public String getTotCharges() {
			return Utilerias.returnWithCurrFormatUS(totCharges);
		}
		public String getTotPmts() {
			return Utilerias.returnWithCurrFormatUS(totPmts);
		}
		public String getCollected() {
			return Utilerias.returnWithPercFormatUS(collected);
		}
	
}
