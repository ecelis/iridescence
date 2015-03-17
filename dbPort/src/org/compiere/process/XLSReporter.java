package org.compiere.process;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.compiere.report.core.RColumn;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;

public class XLSReporter {
	private static CLogger log = CLogger.getCLogger(XLSReporter.class);
	
	@SuppressWarnings("unchecked")
	public XLSReporter(ArrayList<RColumn> cols, ArrayList<ArrayList<Object>> m_rows, CsvReportInterface csvReportInterface, List upperHeaders ){
		StringBuilder strHeader = new StringBuilder();
		StringBuilder strBody = new StringBuilder();
		StringBuilder upHeader = new StringBuilder();
		try{
			for(int i = 0; i < upperHeaders.size(); i++){
				upHeader.append("'"+upperHeaders.get(i));
				if(i!=upperHeaders.size()-1)
					upHeader.append(", ");
				upHeader.append("\n");
			}
			
			
			for(int i = 0; i < cols.size(); i++){
				strHeader.append(cols.get(i).getColHeader());
				strHeader.append(", ");
			}
			strHeader.append("\n");
	
			for(int i = 0; i < m_rows.size(); i++){
				for(int j = 0; j < cols.size(); j++){
					Object objeto = m_rows.get(i).get(j);
					if(objeto != null){
						if (objeto.getClass() == KeyNamePair.class){
							KeyNamePair temp = (KeyNamePair)objeto;
							strBody.append(temp.toString().replace(","," ").replace("\n", " ").replace("\r", " "));
						}else if (objeto.getClass() == String.class){
							String temp = (String) objeto;
							strBody.append(temp.replace(","," ").replace("\n", " ").replace("\r", " "));
						}else if (objeto.getClass() == BigDecimal.class){
							BigDecimal temp = (BigDecimal)objeto;
							strBody.append(temp.toString());
						}else if(objeto.getClass() == Timestamp.class) {
							SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
							Timestamp fecha = (Timestamp) objeto;
							strBody.append(f.format(fecha));
						}else{
							strBody.append(objeto);
						}
					}
					strBody.append(", ");
				}
				strBody.append("\n");
			}			
			strHeader.append(strBody.toString());
			upHeader.append(strHeader.toString());
			File file = File.createTempFile("rep", ".csv");
			
			FileUtils.writeStringToFile(file, upHeader.toString());
			
			csvReportInterface.getFile(file);
		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		}	
	}
}
