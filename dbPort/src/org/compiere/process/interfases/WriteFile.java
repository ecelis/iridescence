/**
 *  FileWriter de Medsys 4 a Medsys 3
 *
 *  @author     Samuel Cardenas
 *  @version    $Id: Utils.java v 1.5.6  2007/06/14 20:04 scardenas Exp $
 *  @version $Revision: $
 *
 * Modificado: $Date: $<p>
 * Por: $Author: $<p>
 */

package org.compiere.process.interfases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WriteFile {
/*
	private static  String ruta = "C:\\";
	private static  String fileName = "FACTt";
*/
	
	public static void  writeToFile (String filePrefix, ResultSet rs,String ruta,String fileName){
		
		BufferedWriter out=null;
	 	int i = 34;
   		String aChar = new Character((char)i).toString();  
   		//String workRow = null;
   		//String finalString = null;
   		String pi = null;
   		
		
   		if (!Utils.findFile(ruta,filePrefix))
   		{
   			System.out.println("Procediendo a hacer archivo : "+fileName);
		    try {
		    	int rowNum =  rs.getMetaData().getColumnCount();
		    	out = new BufferedWriter(new FileWriter(ruta+fileName+".txt"));	    	
		    	
		    	while (rs.next()){
		    		for (int a=1; a<= rowNum; a++){
		    			pi="|";
		    				    			
		    			if(rs.getString(a) == null)
		    				out.append(aChar+aChar+pi);
		    			else if(!Utils.isNumber(rs.getString(a)))
		    					out.append(aChar+rs.getString(a)+aChar+pi);
		    				else
		    					out.append(rs.getString(a)+pi);
		    				
			    	}
		    		out.newLine();
		    	}
		    
		        out.close();
		    	} catch (IOException e)    {e.printStackTrace();}
		    	  catch (SQLException e) {e.printStackTrace();}
		}
   		else {
   			System.out.println("Existe un archivo de ese tipo en  :"+ruta);
   			System.out.println("por lo tanto no se crear"+((char)160)+" el archivo....");
   			System.out.println("Saliendo....");
   			}
   			
	}	
	
	public static void  writeToFile (String filePrefix, String[][] rs,String ruta,String fileName,int rows, int cols){
		
		BufferedWriter out=null;
	 	int i = 34;
   		String aChar = new Character((char)i).toString();  
   		//String workRow = null;
   		//String finalString = null;
   		String pi = null;
   		
		
   		if (!Utils.findFile(ruta,filePrefix))
   		{
   			System.out.println("Procediendo a hacer archivo : "+fileName);
		    try {
		    	//int rowNum = rows;
		    	out = new BufferedWriter(new FileWriter(ruta+fileName+".txt"));	    	
		    	int cont=0;
		    	
		    	for (cont = 0; cont < rows;cont++){
		    		for (int a=0; a< cols; a++){
		    			
		    			pi="|";
		    					    			
		    				if(rs[cont][a] == null)
		    					out.append(aChar+aChar+pi);
		    				else if(!Utils.isNumber(rs[cont][a]))
				    			out.append(aChar+rs[cont][a]+aChar+pi);
				    		else
				    			out.append(rs[cont][a]+pi);	
		    				
		    				
		    				
			    	}
		    		out.newLine();
		    	}
		    
		        out.close();
		    	} catch (IOException e)    {e.printStackTrace();}
		}
   		else {
   			System.out.println("Existe un archivo de ese tipo en  :"+ruta);
   			System.out.println("por lo tanto no se crear"+((char)160)+" el archivo....");
   			System.out.println("Saliendo....");
   			}
   			
	}	
	
	/*
	public static void main(String[] sam){
	
		BufferedWriter out=null;
	 	int i = 34;
   		String aChar = new Character((char)i).toString();  
		
   		if (!Utils.findFile(ruta,fileName))
   		{
   			System.out.println("Procediendo a hacer archivo : "+fileName);
		    try {
		    	out = new BufferedWriter(new FileWriter(ruta+fileName));	    	
		        out.write(aChar+"GRUPO CHRISTUS MUGUERZA"+aChar+"||"+aChar+"13/06/07"+aChar+"||"+aChar+"02002000003"+aChar);
		        out.close();
	    	} catch (IOException e) { }
	    	
		}
   		else 
   			System.out.println("El archivo  que deseas crear ya exite en :"+ruta);
   			System.out.println("por lo tanto no se crear"+((char)160)+" el archivo....");
   			System.out.println("Saliendo....");
   			
	}
	*/
	
	

}