/**
 *  Utilerias de Medsys 4 a Medsys 3
 *
 *  @author     Samuel Cardenas
 *  @version    $Id: Utils.java v 1.5.6  2007/06/14 20:04 scardenas Exp $
 *  @version $Revision: $
 *
 * Modificado: $Date: $<p>
 * Por: $Author: $<p>
 */
package org.compiere.process.interfases;

import java.io.File;
import java.io.FilenameFilter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	
	/**
	 * This method check if exist a file exist in certain root with the prefix  name
	 * @param filename
	 * @return
	 */
	public static  boolean findFile(String ruta, final String prefix){
		boolean found=false;
		
		 File dir = new File(ruta);	
		   
		if (!dir.exists()){
			System.out.println("El directorio "+ruta+" no existia, se procede a crear");
			dir.mkdir();
			System.out.println("El directorio "+ruta+" ha sido creador");
		}
					  	 
		   String[] children = dir.list();
		   String existFile=null;
		   
		    // It is also possible to filter the list of returned files.
		    // This example does not return any files that equals to  var filename'.
		    FilenameFilter filter = new FilenameFilter() {
		        public boolean accept(File dir, String name) {
		            return name.startsWith(prefix);
		        }
		    };
		    children = dir.list(filter);			    
		   
		    if (children == null) {
		       System.out.println("No existe el directorio : "+ruta);
		    } else {
		        for (int i=0; i<children.length; i++) {
		            // Get filename of file or directory
		             existFile = children[i];
		             found=true;
		            System.out.println(existFile);
		        }
		    }
		
		return found;
	}

	/**
	 * 
	 * @param PREFIJO
	 * @return
	 */
    public static  String assemblingDate (String PREFIJO){
    	String assembledDate=null;
    	Format formatter;
    	formatter = new SimpleDateFormat("dd.MM.yy.HH.mm");
    	assembledDate = PREFIJO+formatter.format(new Date()).replace(".","");
    	return assembledDate;
    }//finishinf the return of date 
    
    /**
     * Returning if the string is a number
     * @param txt
     * @return
     */
   public static boolean   isNumber(String txt){
	   boolean isNum =false;
	   
	   txt=txt.trim();
	   String[] nums = new String[]{"0","1","2","3","4","5","6","7","8","9","."};
	   int cont=0;
	   int numLen=0;
	   numLen = txt.length();
	   
	   if (txt != null){
		   for (int a=0;a<10;a++)
		   for (int b=0;b<numLen;b++)
				   if(String.valueOf(txt.charAt(b)).equalsIgnoreCase(nums[a]))
				   cont++;
			   }	
	   if (cont==numLen)	
		    isNum=true;
	   
	   txt=null;
	   nums = null;
	   cont=0;
	   numLen=0;

	   return isNum;
   }
   

	public static String  getFormDir (String root, final String prefix){
		
		File dir = new File(root);
	    
	    String[] children = dir.list();
	    if (children == null) {
	        // Either dir does not exist or is not a directory
	    } else {
	        for (int i=0; i<children.length; i++) {
	            // Get filename of file or directory
	            String filename = children[i];
	            System.out.println(filename);
	        }
	    }
	    
	    // It is also possible to filter the list of returned files.
	    // This example does not return any files that start with `.'.
	    FilenameFilter filter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.startsWith(prefix);
	        }
	    };
	    children = dir.list(filter);
	    /*
	    for (int i=0; i<children.length;i++)
	    	System.out.println(children[i]);*/
	    if (children.length>1)
	    	return null;
	    else
		return children[0];
	    	
	}

}
