/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.compiere.util.DB;

/**
 * Implementacion de lafuncionalidad para mandar a imprimir los tickes en automatico
 * cuando se aplica un pago
 * <p>
 * Modificado por: $Author:  $ <p>
 * Fecha: $Date:  $ <p>
 *
 * @author Valentin Garcia
 * @version $Revision: 1.10 $
 */
public class PrintTicket  {
	
	
	/**
	 * Metodo para lle
	 * @throws SQLException 
	 *
	 */
	public static void generateTicket(int org_ID, int client_ID, String invoiceValue, String consecutivoCaja, int user_ID) throws SQLException{
		TicketDetail ticket = new TicketDetail();

			fillTicket(ticket,org_ID, client_ID, invoiceValue, consecutivoCaja,user_ID);	  
		  ticket.PrintTicket();
	}
	
	public static void fillTicket(TicketDetail ticket, int org_ID, int client_ID, String invoiceValue, String consecutivoCaja, int user_ID) throws SQLException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select Description from AD_CLient where AD_CLIENT_ID=?";
		String linea = "";
		String lineaCalle="";
		String lineaCiudad="";
		String lineaCP = "";
		int idRegion = 0;
		String lineaPaciente = "";
		String lineaNivel ="";
		ArrayList values = new ArrayList<String>();
		ArrayList detalles = new ArrayList<String>();
		ArrayList cantidades = new ArrayList<String>();
		ArrayList montos = new ArrayList<String>();
		String lineaTotal="";
		String usuario = "";
		double total = 0;
		
		ps = DB.prepareStatement(sql);
		ps.setInt(1, client_ID);
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			linea = rs.getString(1);
		}
		
		//Agregamos el Nombre de la Institucion
		  ticket.AddHeaderLine(linea.toUpperCase());
		  
		  rs = null;
		  ps=null;
		  sql="SELECT ADDRESS1,ADDRESS2,CITY,POSTAL,C_REGION_ID,EXME_TOWNCOUNCIL_ID  FROM C_LOCATION " +
		  		"WHERE C_LOCATION_ID=(select C_LOCATION_ID FROM AD_ORGINFO where AD_ORG_ID=?)";
		  
		  ps=DB.prepareStatement(sql);
		  ps.setInt(1, org_ID);
		  rs = ps.executeQuery();
		  
		  while(rs.next()){
			  //linea de la calle y numero
			  lineaCalle = rs.getString(1) + " " + rs.getString(2);
			  lineaCiudad = rs.getString(3) ;
			  lineaCP = "  C.P.: " + rs.getString(4);
			  idRegion = rs.getInt(5);
		  }
		  
		  rs = null;
		  ps=null;
		  sql="SELECT NAME FROM C_REGION WHERE C_REGION_ID=?";
		  
		  ps=DB.prepareStatement(sql);
		  ps.setInt(1, idRegion);
		  rs=ps.executeQuery();
		  
		  while(rs.next())
			  lineaCiudad = lineaCiudad + " " + rs.getString(1) +  lineaCP;
		  
		  //Agregamos La direccion, Telefono y RFC
		  ticket.AddSubHeaderLine(lineaCalle);
		  ticket.AddSubHeaderLine(lineaCiudad);
		  //Estas lineas estan temporalmente estaticas (vgarcia)
		  ticket.AddSubHeaderLine("Tel.:10640900");
		  ticket.AddSubHeaderLine("INP-6360420-3F7");
		  ticket.AddSubHeaderLine("Vigencia: 30 DIAS HABILES PARA SU DEVOLUCION A PARTIR DE SU EXPEDICION");
		  ticket.AddSubHeaderLine("   ");
		  //Numero Consecutivo de la caja
		  ticket.AddSubHeaderLine(consecutivoCaja);	  
		  ticket.AddSubHeaderLine("   ");
		  
		  
		  ps = null;
		  rs = null;
		  
		  sql="SELECT pac.VALUE as historia,pac.NAME||' '||pac.NOMBRE2||' '||pac.APELLIDO1||' '||pac.APELLIDO2 as nombre," +
		  		"bp.VALUE as socio,prod.VALUE as prod_val, prod.NAME as prod_name," +
		  		"invl.QTYINVOICED as cantidad, PRICEENTERED as monto FROM C_INVOICELINE invl " +
		  		"INNER JOIN C_INVOICE inv ON(inv.C_INVOICE_ID=invl.C_INVOICE_ID) " +
		  		"INNER JOIN M_PRODUCT prod ON(prod.M_PRODUCT_ID=invl.M_PRODUCT_ID) " +
		  		"LEFT JOIN EXME_PACIENTE pac ON (pac.EXME_PACIENTE_ID=inv.EXME_PACIENTE_ID) " +
		  		"LEFT JOIN C_BPARTNER bp ON (bp.C_BPARTNER_ID=inv.C_BPARTNER_ID) " +
		  		"WHERE inv.DOCUMENTNO=?";
		  
		  ps = DB.prepareStatement(sql);
		  ps.setString(1, invoiceValue);
		  
		  rs = ps.executeQuery();
		  int cont = 0;
		  
		  while(rs.next()){
			  lineaPaciente = rs.getString("historia") + "    " + rs.getString("nombre");
			  lineaNivel = "Nivel: " + rs.getString("socio");
			  total =  total + rs.getDouble("monto");
			  values.add(rs.getString("prod_val"));
			  detalles.add(rs.getString("prod_name"));
			  cantidades.add(new Integer(rs.getInt("cantidad")).toString());
			  montos.add(new Double(rs.getDouble("monto") * rs.getInt("cantidad")).toString());  
			  cont++;
		  }
		  
		  //Datos del BPartner
		  ticket.AddSubHeaderLine(lineaPaciente);
		  ticket.AddSubHeaderLine(lineaNivel);
		  ticket.AddSubHeaderLine("   ");

		  for(int i=0 ; i<detalles.size() ; i++){
			  ticket.AddItem(values.get(i).toString(), detalles.get(i).toString(), cantidades.get(i).toString(), montos.get(i).toString());
		  }
		  //Agregamos el total del ticket
		  ticket.AddTotal(new Double(total).toString());
		  
		  //Agregamos el footer del ticket
		  sql="Select NAME from AD_User where AD_USER_ID=?";	
		  ps = DB.prepareStatement(sql);
		  ps.setInt(1, user_ID);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				usuario = rs.getString(1);
			}
		  Calendar fecha = java.util.Calendar.getInstance();
		  String am_pm = "";
		  if(fecha.get(Calendar.AM_PM)==1)
			  am_pm="p.m.";
		  else
			  am_pm="a.m.";
		  
		  ticket.AddFooterLine(fecha.get(java.util.Calendar.DATE) + "/"
				  + fecha.get(java.util.Calendar.MONTH)    + "/"
				  + fecha.get(java.util.Calendar.YEAR)
				  + "   " + 
				  fecha.get(Calendar.HOUR) + ":" + fecha.get(Calendar.MINUTE)+ ":" + 
				  fecha.get(Calendar.SECOND) + " " + am_pm + "            " + usuario);
		  ticket.AddFooterLine("Este Recibo no es un comprobante fiscal");
		
	}
}
