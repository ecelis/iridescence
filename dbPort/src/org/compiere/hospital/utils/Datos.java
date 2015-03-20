/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.hospital.utils;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEstServAlm;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

/**
 *  Implementaci&oacute;n del acceso a la base de datos para el sistema de
 *  hospital <p>
 *
 *  Modificado por: $Author: scardenas $ <p>
 *
 *  Fecha: $Date: 2006/10/31 19:43:03 $ <p>
 *
 *@author     miguel rojas
 *@author     gisela lee
 *@author     hassan reyes
 *@created    18 de abril de 2005
 *@version    $Revision: 1.151 $
 *@deprecated: Will be removed.
 *Razones:
 * Algunos metodos ya estan obsoletos y se indica que metodo se debe utilizar<br>
 * Algunos metodos deben de ser movidos a su modelo correspondiente <br>
 * Algunos metodos solo son usados por clases STRUTS de las cuales ya no hay soporte para ecs<br>
 * 
 */
public class Datos {
	
    /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (Datos.class);
	
//	/**@deprecated usar {@link Env#getAD_Client_Name(Properties ctx)} - para empresa del contexto*/
//	public static String nombreEmpresa(long empresa){
//		return "*****";
//	}
//
//	/** @deprecated use {@link MOrg#getName(Properties, int)}  */
//	public static String nombreOrganizacion(long org) {
//		return "****";
//	}

//	/**
//	 * Obtenemos las ubicaciones de un socio de negocios
//	 * 
//	 *@param socio
//	 *            El socio de negocios a obtener su direcction
//	 *@return La direccion del socio de negocios
//	 *@exception Exception
//	 *                Description of the Exception
//	 *@deprecated Mover a su clase correspondiente {@link MBPartnerLocation}
//	 */
//	public static long getBPartnerLocation(Properties ctx, long socio) throws Exception {
//		long ubicacion = 0;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		//buscamos la direccion del socio de negocios
//		sql.append("SELECT C_BPartner_Location.C_Location_ID ")
//			.append("FROM C_BPartner_Location ")
//			.append("WHERE C_BPartner_Location.C_BPartner_ID = ? ")
//			.append("AND C_BPartner_Location.IsActive = 'Y' ")
//			.append("AND C_BPartner_Location.C_Location_ID IS NOT NULL ")
//			.append(MEXMELookupInfo.addAccessLevelSQL(ctx,  " ", "C_BPartner_Location"))
//			.append("ORDER BY C_BPartner_Location.C_BPartner_Location_ID");
//		try {
//			ubicacion = DB.getSQLValue(null, sql.toString(), socio);
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		}
//		return ubicacion;
//	}

	/**
	 * Devuelve los almacenes a los que les puede pedir un almacen origen incluyendose a si mismo siempre y cuando se relacione a si mismo.
	 * (EXME_WarehouseRel)
	 * 
	 * @author bubuntux
	 *@param ctx
	 *@param almacen
	 *@return Lista de LabelValueBean
	 *@throws Exception
	 *@deprecated mover ala M correspondiente
	 *Usar List<KeyNamePair>
	 */
	public static List<LabelValueBean> getWarehouseRelComplete(Properties ctx, long almacen) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		sql .append( "SELECT a.M_Warehouse_ID, a.Name ")
			.append( "FROM EXME_WarehouseRel , M_Warehouse a ")
			.append( "WHERE  EXME_WarehouseRel.M_Warehouse_ID = ? ")
			.append( "AND EXME_WarehouseRel.IsActive = 'Y' AND a.IsActive = 'Y' ")
			.append( "AND EXME_WarehouseRel.M_WarehouseRel_ID = a.M_Warehouse_ID ");

		sql .append( MEXMELookupInfo.addAccessLevelSQL(ctx,  " ", "EXME_WarehouseRel"));

		sql .append( " ORDER BY a.Name");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, almacen);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean combo = new LabelValueBean(String.valueOf(rs.getString("Name")), String.valueOf(rs.getInt("M_Warehouse_ID")));
				lista.add(combo);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	

	/**
	 *  Devuelve los almacenes relacionados a una estacion de servicio.
	 *
	 *@param  estServ       la estacion de servicio seleccionada
	 *@param  ctx           Description of the Parameter
	 *@return               Una lista con los almacenes de la estacion de servicio
	 *@throws  Exception    en caso de ocurrir un error al procesar la consulta.
	 *usar metodo de {@link MWarehouse} / {@link MEstServAlm}
	 *@deprecated
	 */
	public static List<LabelValueBean> getEstServAlm(Properties ctx, long estServ, boolean isDefault)
			 throws Exception {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		sql .append( "SELECT a.M_Warehouse_ID, a.Name FROM EXME_EstServAlm , M_Warehouse a ")
				 .append( "WHERE EXME_EstServAlm.EXME_EstServ_ID = ? ")
				 .append( "AND EXME_EstServAlm.IsActive = 'Y' AND a.IsActive = 'Y' ")
				 .append( "AND EXME_EstServAlm.M_Warehouse_ID = a.M_Warehouse_ID ")
				 .append( "AND a.Consigna='N' ")//Omitimos los almacenes en consigna 
				 .append( MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_EstServAlm"));
		
		if(isDefault)
			sql .append(" ORDER BY EXME_EstServAlm.isDefault DESC, a.Name");
		else
			sql.append(" ORDER BY a.Name");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, estServ);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean lvb = new LabelValueBean(rs.getString("Name")
						, String.valueOf(rs.getLong("M_Warehouse_ID")));
				lista.add(lvb);
			}
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
    		DB.close(rs, pstmt);
    	}

		return lista;
	}

	/**
	 *  Devuelve el value de la tabla dada y registro con id dado
	 *  PRECAUCI&Oacute;N:No abuse de este metodo, cada llamada a este significa
	 *  una vuelta a la base de datos
	 *
	 *@param  tabla       tabla a consultar
	 *@param  id          ID del registro
	 *@return             Un valor String con el value del registro
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.
	 * @deprecated
	 */
	public static String getValueFrom(Properties ctx, String tabla, long id) throws Exception {
		String name = null;
		if (tabla == null)
			return name;
		Object value = Utilerias.getFieldFrom(ctx, tabla, "Value", tabla + "_ID", id);
		if (value != null) {
			if (value instanceof String)
				name = (String) value;
			else
				name = value.toString();
		}
		return name;
	}

	/**
	 *  Devuelve el ID de la tabla dada y value dado. Retorna la primera
	 *  coincidencia UPPER("value"). Combeniente para reortes.
	 *  PRECAUCI&Oacute;N:No abuse de este metodo, cada llamada a este significa
	 *  una vuelta a la base de datos
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  value       value del registro
	 *@return             long ID del registro encontrado
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.
	 *      @deprecated
	 */
	public static long getIDFromValue(Properties ctx, String tabla, String value) throws Exception {
		long id = 0;
		if (value == null || tabla == null)
			return id;
		Object val = Utilerias.getFieldFrom(ctx, tabla, tabla + "_ID", "UPPER(Value)", "%" + value.toUpperCase() + "%");

		if (val != null) {
			if (val instanceof Integer)
				id = (Integer) val;
			else
				id = Integer.parseInt(val.toString());
		}
		return id;
	}

	/**
	 *  Devuelve un objeto LabelValueBean con el ID y el Value de la tabla y value
	 *  dado
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  value       value del registro (LIKE)
	 *@return             un objeto LabenValueBean con el ID y el Value
	 *@deprecated
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 */
	public static LabelValueBean getLabelValueFromValue(Properties ctx, String tabla, String value)
			 throws Exception {
		LabelValueBean resultado = new LabelValueBean("", "0");
		if (tabla == null)
			return resultado;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// value = value.replaceAll("\\*", "%");//Lama: comodin estandar %
		sql.append("SELECT ").append(tabla).append(".").append(tabla).append("_ID, ")//
				.append(tabla).append(".").append("Value ").append(" FROM ").append(tabla).append(" WHERE UPPER(").append(tabla)//
				.append(".").append("Value) LIKE UPPER(?) AND ").append(tabla).append(".").append("IsActive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", tabla));

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, value.trim());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultado.setLabel(rs.getString("Value"));
				resultado.setValue(String.valueOf(rs.getLong(tabla + "_ID")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return resultado;
	}

	/**
	 *  Devuelve el name de la tabla dada y registro con id dado
	 *  PRECAUCI&Oacute;N:No abuse de este metodo, cada llamada a este significa
	 *  una vuelta a la base de datos
	 *
	 *@param  tabla       tabla a consultar
	 *@param  id          ID del registro
	 *@return             Un valor String con el name del registro
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.}
	 *      @deprecated
	 */
	public static String getNameFrom(Properties ctx, String tabla, long id) {
		String name = null;
		if (tabla == null) {
			return name;
		}
		Object value = Utilerias.getFieldFrom(ctx, tabla, "Name", tabla + "_ID", id);
		if (value != null) {
			if (value instanceof String) {
				name = (String) value;
			} else {
				name = value.toString();
			}
		}
		return name;
	}

//	/**
//	 *  Devuelve el valor del campo de la tabla dada y registro con id dado.
//	 *  Internamente hace: TO_CHAR(field). PRECAUCI&Oacute;N:No abuse de este
//	 *  metodo, cada llamada a este significa una vuelta a la base de datos
//	 *
//	 *@param  tabla       tabla a consultar
//	 *@param  id          ID del registro
//	 *@param  field       Description of the Parameter
//	 *@return             Un valor String con el valor del campo
//	 *@deprecated
//	 */
//	public static String getFieldFrom(Properties ctx, String tabla, String field, long id){
//		String name = null;
//		if (field == null || tabla == null)
//			return name;
//		
//		/* Se elimina el TO_CHAR por excepcion en Postgresql.
//		 * Se revisaron las referencias y solo se llama este metodo de un solo lugar (Metodo onevent de NewMP) y en ambos casos lo que manda es
//		 * o el genProductID o el PMID. Entonces no es necesario el TO_CHAR ya que no envia fechas. Si se utilizase para
//		 * Fechas, favor de considerar una programacion adecuada para el manejo de Postgresql y Oracle. Jesus Cantu
//		 * 02 Agosto 2012.
//		 */
//		//Object value = Utilerias.getFieldFrom(ctx, tabla,"TO_CHAR("+ field+ ")", tabla+"_ID", new BigDecimal(id));
//		Object value = Utilerias.getFieldFrom(ctx, tabla, field, tabla+"_ID", new BigDecimal(id));
//		
//		if(value != null){
//			if(value instanceof Timestamp)
//				name = Constantes.getSdfFechaHora().format(value);
//			else 
//				name = value.toString();
//		}
//		return name;
//	}
	
	/**
	 *  Devuelve un objeto LabelValueBean con el ID y el Nombre de la tabla y
	 *  nombre dado
	 *
	 *@param  ctx         Contexto
	 *@param  tabla       Tabla para buscar
	 *@param  blanco      blanco
	 *@throws  Exception  en caso de ocurrir un error en la consulta
	 *@deprecated Use List<ValueNamePair>
	 */
	public static List<LabelValueBean> getLstLabelValueFromName(Properties ctx, String tabla, boolean blanco)
	throws Exception {
		
		if(tabla == null) {
			return new ArrayList<LabelValueBean>();
		}
		
		List<LabelValueBean> lst = new ArrayList<LabelValueBean>();
		if(blanco){
			lst.add(new LabelValueBean("", "0"));
		}
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT ").append(tabla).append(".").append(tabla).append("_ID, ")//
		.append(tabla).append(".").append("Name ")//
		.append("FROM ").append(tabla)//
		.append(" WHERE ").append(tabla).append(".").append("IsActive = 'Y' ");//
		//.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", tabla));

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lst.add(new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt(tabla + "_ID"))));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}
}