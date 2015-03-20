package org.compiere.model.accessdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.model.ModelError;
import org.compiere.util.DB;

/**
 * 
 * @author Expert
 * 
 */
public class BeanResultSet {

//	/** query */
//	private String sql = null;
//	/** parameters */
//	private List<?> params = null;
//	/** resultset map */
//	private List<MapMetaData> lstMapMetaData = null;
//
//	/**
//	 * Constructor
//	 * 
//	 * @param ctx
//	 * @param sql
//	 * @param params
//	 * @param trxName
//	 */
//	public BeanResultSet(final String sql, final List<?> params) {
//		this.sql = sql;
//		this.params = params;
//	}
//
//	/**
//	 * execute ResultSet
//	 */
//	public List<ModelError> execResultSet() {
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ResultSetMetaData metaData = null;
//		List<ModelError> lstErrors = new ArrayList<ModelError>();
//
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			DB.setParameters(pstmt, params);
//
//			rs = pstmt.executeQuery();
//			metaData = rs.getMetaData();
//
//			lstMapMetaData = new ArrayList<MapMetaData>();
//
//			while (rs.next()) {
//
//				// new instance
//				MapMetaData beanMetaData = new MapMetaData();
//
//				// columns
//				for (int x = 1; x <= metaData.getColumnCount(); x++) {
//
//					// ID (nick table + column id)
//					String column_Nick = metaData.getColumnLabel(x);
//					String column_Name = metaData.getColumnName(x);
//					String table_Nick = metaData.getTableName(x); // t+ table id
//					String table_Name = metaData.getTableName(x);
//
//					// if the column name alias is equal to
//					if (column_Nick.contains("_") && column_Nick.contains("t")) // separates
//					// the
//					// column
//					// table
//					{
//						if (table_Nick.equals("")) {
//							table_Nick = column_Nick.substring(column_Nick
//									.indexOf("t"), column_Nick.indexOf("_"));// t+
//							// table
//							// id
//
//							int tam = table_Nick.indexOf("t") + 1;
//							if (tam <= table_Nick.length())
//								table_Name = table_Nick.substring(tam);// table
//							// id
//						}
//
//						int tam = column_Nick.indexOf("_") + 1;
//						if (tam <= column_Nick.length())
//
//							if (column_Nick.equals(column_Name))
//								column_Name = column_Nick.substring(column_Nick
//										.indexOf("_") + 1); // column id
//					}
//
//					// type and value
//					String column_Type = metaData.getColumnClassName(x);
//					Object column_Value = rs.getObject(x);
//
//					// bean resultset
//					beanMetaData.setValue(table_Nick, // table alias
//							table_Name, // table id
//							column_Nick, // column alias is not repeated
//							column_Name, // column id
//							column_Type, // type column
//							column_Value // value column
//							);
//
//				}
//
//				// list
//				lstMapMetaData.add(beanMetaData);
//			}
//
//			/*
//			 * for (int i = 0; i < mainClass.length; i++) {
//			 * 
//			 * mainClass[i].getClass().getName();// falta mexmetratamientosdet
//			 * //mainClass[i].getClass().getConstructor(Properties, ResultSet,
//			 * String)
//			 * 
//			 * try { Class cls =
//			 * Class.forName(mainClass[i].getClass().getName());
//			 * 
//			 * Class partypes[] = new Class[3]; partypes[0] = Properties.class;
//			 * partypes[1] = ResultSet.class; partypes[2] = String.class;
//			 * 
//			 * Constructor ct = cls.getConstructor(partypes);
//			 * 
//			 * Object arglist[] = new Object[3]; arglist[0] = ctx; arglist[1] =
//			 * rs; arglist[2] = new String("tt");
//			 * 
//			 * Object retobj = ct.newInstance(arglist);
//			 * System.err.println(retobj.toString()); } catch (Throwable e) {
//			 * System.err.println(e); }
//			 * 
//			 * }
//			 * 
//			 * MEXMETratamientosDetalle td1 = new MEXMETratamientosDetalle(ctx,
//			 * rs, null); MEXMETratamientosCues td2 = new
//			 * MEXMETratamientosCues(ctx, rs, null); MEXMETratamientosPaq td3 =
//			 * new MEXMETratamientosPaq(ctx, rs, null); MEXMETratamientosPres
//			 * td4 = new MEXMETratamientosPres(ctx, rs, null);
//			 * MEXMETratamientosProductos td5 = new
//			 * MEXMETratamientosProductos(ctx, rs, null); MEXMETratamientosServ
//			 * td6 = new MEXMETratamientosServ(ctx, rs, null);
//			 * System.err.println(td1.toString());
//			 * System.err.println(td2.toString());
//			 * System.err.println(td3.toString());
//			 * System.err.println(td4.toString());
//			 * System.err.println(td5.toString());
//			 * System.err.println(td6.toString());
//			 */
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			lstErrors.add(new ModelError(ModelError.TIPOERROR_Excepcion,
//					"msj.error", e.getMessage()));
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return lstErrors;
//	}
//
//	public List<MapMetaData> getMapMetaData() {
//		return lstMapMetaData;
//	}
//
//	public void setMapMetaData(final List<MapMetaData> mapMetaData) {
//		this.lstMapMetaData = mapMetaData;
//	}
//
//	/**
//	 * Class Bean
//	 * 
//	 * @author Expert
//	 */
//	public static class MapMetaData {
//
//		private Map<String, MetaData> map = null;
//
//		public MapMetaData() {
//			map = new HashMap<String, MetaData>();
//		}
//
//		/**
//		 * table_Nick : table alias, table_Name : table id column_Nick : column
//		 * alias is not repeated, column_Name : column id, column_Type : type
//		 * column column_Value : value column
//		 * 
//		 * @param tableLabel
//		 *            table alias
//		 * @param tableName
//		 *            table id
//		 * @param label
//		 *            column alias is not repeated
//		 * @param name
//		 *            column id
//		 * @param type
//		 *            type column
//		 * @param value
//		 *            value column
//		 */
//		public void setValue(String tableLabel, String tableName, String label,
//				String name, String type, Object value) {
//			// column alias is not repeated
//			map.put(label, new MetaData(tableLabel, tableName, label, name,
//					type, value));
//		}
//
//		public Map<String, MetaData> getMap() {
//			return map;
//		}
//
//		public void setMap(Map<String, MetaData> map) {
//			this.map = map;
//		}
//	}
}
