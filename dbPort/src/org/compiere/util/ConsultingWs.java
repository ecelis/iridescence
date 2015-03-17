package org.compiere.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;

public class ConsultingWs {
	
	private static CLogger s_log = CLogger.getCLogger(ConsultingWs.class);
	
	String wsType;
    TreeMap<String,String> parameters = new TreeMap<String,String>();
    
    String url;
    String language;
    String codeSystem;
    String code;
    String codeTitle;
    String drugName;
    
    public ConsultingWs(String wsType) {
        
        this.wsType = wsType;
        
        loadConfiguration();
        
    }
    
    private void loadConfiguration() {
             
        this.url = "http://apps.nlm.nih.gov/medlineplus/services/mpconnect_service.cfm";
             
    }
    
    public void setParameters(TreeMap parameters) {
        
        this.parameters = parameters;
        
    }
    
    private StringBuffer buildRequest() {
        
        StringBuffer requestUrl = new StringBuffer();
        String param;
        
        requestUrl.append(url);
        requestUrl.append("?");
        
        for( Iterator it = parameters.keySet().iterator(); it.hasNext();) { 
            param = (String)it.next();
            requestUrl.append(param);
            requestUrl.append("=");
            requestUrl.append((String)parameters.get(param));
            requestUrl.append("&");
	}
                
        return new StringBuffer(requestUrl.substring(0, requestUrl.length() - 1));
        
    }
    
    public String getUrl() {
    	
    	return buildRequest().toString();
    	
    }
    
    public StringBuffer request() {
        
        StringBuffer xml = new StringBuffer();
                
        try {
            
            URL service = new URL(buildRequest().toString());  

            URLConnection con = service.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));  
            String line;  
            
            while ((line = in.readLine()) != null) {  
            	
                xml.append(line);
                
                s_log.log(Level.FINE, line.toString());
                
            }  
            
            in.close();  
	 		
        } catch (MalformedURLException e) {  
        	s_log.log(Level.SEVERE, xml.toString(), e);
        } catch (IOException e) {  
        	s_log.log(Level.SEVERE, xml.toString(), e);  
        }  
        
        return xml;
        
    }

}
