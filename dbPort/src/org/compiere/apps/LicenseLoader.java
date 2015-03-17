package org.compiere.apps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.compiere.util.DB;

public class LicenseLoader {
	
	
	private static String adClientName;
	
	static String getAdClientName(){
		if (LicenseLoader.adClientName  == null){
			String sql =  "SELECT value FROM AD_Client WHERE AD_Client_ID NOT IN(?,?) AND IsActive = 'Y'";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try{
				stmt = DB.prepareStatement(sql, null);
				stmt.setInt(1,0);
				stmt.setInt(2,11);
				rs = stmt.executeQuery();
				
				while(rs.next()){
					LicenseLoader.adClientName = rs.getString(1);					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}		
		return LicenseLoader.adClientName;
	}

	public static File getFile() {
		
		File f = new File("tmp.lic");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		stmt = DB.prepareStatement("select elm_file from elm_license where isactive = 'Y'");

		try {
			rs = stmt.executeQuery();

			if(rs.next()) {
				
				InputStream is = rs.getBinaryStream(1);
				FileOutputStream out = null;
				
				byte buf[]=new byte[1024];
			    int len;
			    
			    try {
			    	
			    	out = new FileOutputStream(f);
			    	
					while((len=is.read(buf))>0){
						out.write(buf,0,len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						if (out != null){
							out.flush();
							out.close();
						}
						if (is != null){
							is.close();
						}
						
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}else{
				JOptionPane.showMessageDialog(null,
	    			    "La licencia ha vencido o no ha sido instalada, consulte a su administrador",
	    			    "Error de Licencia",
	    			    JOptionPane.ERROR_MESSAGE);

	    		System.exit(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
	}

}
