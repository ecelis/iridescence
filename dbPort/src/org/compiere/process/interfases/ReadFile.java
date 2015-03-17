package org.compiere.process.interfases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.Connection;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.compiere.util.DB;
import org.compiere.util.Trx;

public class ReadFile {
	
	/**
	 * Returns the number of lines that the import file contains
	 * @param filename
	 * @return
	 */
	public static int regSize(String filename){
		BufferedReader in =null;
		int counter=0;
		String str=null;
		
		try {
			in = new BufferedReader(new FileReader(filename));
			//while para leer lineas del archivo
			while ((str = in.readLine()) != null) {	
				if (!str.trim().equalsIgnoreCase("") )
					counter++;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		finally{
			in=null;
			str=null;
		}		
		
		return counter;
		
	}
	
	/**
	 * Metodo de lectura de archivo que devuelve una lista de listas   con lo que leyo y que ademas 
	 * @param filename
	 */ 
	public static ArrayList<ArrayList<String>> readFile (String filename){
		int vr = 34;
		
		String comillas1 = new Character((char)vr).toString();
		String comillas = comillas1 + comillas1;
		String str;
		String temp;
		
		ArrayList<ArrayList<String>> lines =new ArrayList<ArrayList<String>> ();
		ArrayList<String> tokensList = new ArrayList<String>();
		
		StringTokenizer reader = null;
		
		BufferedReader in =null;
		try {
			in = new BufferedReader(new FileReader(filename));
			//while para leer lineas del archivo
			while ((str = in.readLine()) != null) {
				//if para no anexar al araylist devuelto un registro vacio y que truene
				if (!str.trim().equalsIgnoreCase("") ){
					reader = new StringTokenizer(str);
					//while para dividir en tokesn el string obtenido del archivo
					while(reader.countTokens()!=0){
						temp= reader.nextToken("|");
						if(temp.equals(comillas)){
							temp="";
						}
						tokensList.add(temp.replace(comillas1,""));
					}
					lines.add(tokensList);
					tokensList=null;
					tokensList = new ArrayList<String>();
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		finally{
			comillas1=null;
			comillas=null;
			str=null;
			temp=null;
			tokensList=null;
			reader=null;
			in=null;
			vr=0;
		}
		
		return lines;
	}
	
	/**
	 * Insert
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @param d6
	 */
	public static void save(String d1,String d2,String d3,String d4,String d5,String d6){
		//Connection connection = Conn.getConn("oscar","1521","muguerza");

        
        
		try {
			// Prepare a statement to insert a record
			String sql = "INSERT INTO datos1("
				+ "d1,"
				+ "d2,"
				+ "d3,"
				+ "d4,"
				+ "d5,"
				+ "d6) "
				+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement pstmt =null;// connection.prepareStatement(sql);
			pstmt = DB.prepareStatement(sql.toString(), null);
			// Set the values
			pstmt.setString(1, d1);
			pstmt.setString(2, d2);
			pstmt.setString(3, d3);
			pstmt.setString(4, d4);
			pstmt.setString(5, d5);
			pstmt.setString(6, d6);
			
			
			// Insert the row
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}//fin de insert
	
}

