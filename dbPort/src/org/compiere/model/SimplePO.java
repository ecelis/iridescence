/**
 * 
 */
package org.compiere.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 * <p><b>Proposito :</b> Simple persistent object.
 * </p>
 * <b>Creado : </b> 10/02/2010
 * @author mrojas
 *
 */
public abstract class SimplePO implements Serializable {

	/** The object values 				*/
	private HashMap<String, Object> properties;
	/** For metadata information 		*/
	private POInfo pOInfo;
	/**	Logger							*/
	protected transient CLogger	log = CLogger.getCLogger (getClass());
	
	private String trxName = null;
	
	public SimplePO(Properties ctx, String trxName) {
		pOInfo = initPO(ctx);
		this.trxName = trxName;
		loadProperties();
		setAD_Client_ID(Env.getAD_Client_ID(ctx));
		setAD_Org_ID(Env.getAD_Org_ID(ctx));
		setCreatedBy(Env.getAD_User_ID(ctx));
		setUpdatedBy(Env.getAD_User_ID(ctx));
	}
	
	/**
	 * Build object from ResultSet
	 * @param ctx The application context
	 * @param rs The Database ResultSet
	 * @param trxName The transaction name
	 */
	public SimplePO(Properties ctx, ResultSet rs, String trxName) {
		pOInfo = initPO(ctx);
		this.trxName = trxName;
		loadProperties(rs);
	}
	
	/**
	 *  Initialize and return PO_Info
	 *  @param ctx context
	 *  @return POInfo
	 */
	abstract protected POInfo initPO (Properties ctx);
	
	/**
	 * Returns the current value for a given property.
	 * @param column The property name
	 * @return The current value
	 */
	protected final Object get_Value(String column) {
		
		Object value = properties.get(column);
		
		if(value == null)
			log.log(Level.FINE, "Invalid property : " + column);
		
		return value;
	}
	
	/**
	 * Sets the current value for a given property.
	 * @param column The property
	 * @param value The value to set
	 */
	protected final void set_Value(String column, Object value) {
		if(properties.containsKey(column)) {
			properties.put(column, value);
		} else {
			log.log(Level.SEVERE, "Invalid property : " + column);
		}
	}
	
	/**
	 * Saves the object data.
	 * @param trxName The transaction name.
	 * @return if the data could be saved or not.
	 */
	public boolean save(String trxName, Integer user) {
		this.trxName = trxName;
		return save(user);
	}
	
	/**
	 * Saves the object data.
	 * @return if the data could be saved or not.
	 */
	public boolean save(Integer ad_user_id) {
		
		boolean status = true;
		
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append("INSERT INTO ")
				 .append(pOInfo.getTableName()).append("_".concat(ad_user_id.toString()))
				 .append(" (");
		
		StringBuilder sqlValues = new StringBuilder();
		sqlValues.append(") VALUES (");
		
		for(int i = 0; i < pOInfo.getColumnCount(); i++) {
			POInfoColumn col = pOInfo.getColumn(i);
			sqlInsert.append(col.ColumnName).append(',');
			sqlValues.append("?,");
		}
		
		String sql = 
			StringUtils.removeEnd(sqlInsert.toString(), ",") +
			StringUtils.removeEnd(sqlValues.toString(), ",");
		sql = sql + ")";
		
		log.finest("SimplePO : SQL = ".concat(sql));
		
		PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
		
		for(int i = 0; i < pOInfo.getColumnCount(); i++) {
			POInfoColumn col = pOInfo.getColumn(i);

			try {

				if(col.ColumnClass == Integer.class) {
					pstmt.setInt(i+1, properties.get(col.ColumnName) != null ? (Integer)properties.get(col.ColumnName) : 0);
				} else if (col.ColumnClass == BigDecimal.class) {
					pstmt.setBigDecimal(i+1, (BigDecimal)properties.get(col.ColumnName));
				} else if (col.ColumnClass == Boolean.class) {
					pstmt.setString(
							i+1, 
							(Boolean)properties.get(col.ColumnName)?"Y":"N"
					);
				} else if (col.ColumnClass == Timestamp.class) {
					pstmt.setTimestamp(i+1, (Timestamp)properties.get(col.ColumnName));
				} else if (col.DisplayType == DisplayType.Binary ||
						col.DisplayType == DisplayType.Image) {
					pstmt.setBytes(i+1, (byte[])properties.get(col.ColumnName));
				} else if (col.ColumnClass == String.class ||
						col.DisplayType == DisplayType.TextLong) {
					pstmt.setString(i+1, (String)properties.get(col.ColumnName));
				} else {
					pstmt.setObject(i+1, properties.get(col.ColumnName));
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "loading into properties",e);
				status = false;
				break;
			}
		}
		
		if(status) {
			try {
				pstmt.executeUpdate();
			} catch (SQLException e) {
				log.log(
						Level.SEVERE, 
						"While inserting in " + pOInfo.getTableName(), 
						e
				);
				status = false;
			}finally{
				try {
					if (pstmt != null) {
						pstmt.close();
					}					
					pstmt = null;					
				} catch (Exception e) {
					log.log(Level.SEVERE, "while closing rs and pstmt", e.getMessage());
					pstmt = null;					
				}
			}

		}
		
		return status;
	}
	
	private void setAD_Client_ID(int AD_Client_ID) {		
		properties.put("AD_Client_ID", AD_Client_ID);
	}
	
	private void setAD_Org_ID(int AD_Org_ID) {		
		properties.put("AD_Org_ID", AD_Org_ID);
	}
	
	private void setCreatedBy(int AD_User_ID) {		
		properties.put("CreatedBy", AD_User_ID);
	}
	
	private void setUpdatedBy(int AD_User_ID) {		
		properties.put("UpdatedBy", AD_User_ID);
	}
	
	/**
	 * Loads the values from the ResultSet into a HashMap, using the POColumnInfo
	 * to obtain the column names and use them as the key in the hashmap.
	 * @param rs The result set with the values to be loaded
	 */
	private void loadProperties(ResultSet rs) {
		properties = new HashMap<String, Object>();
		pOInfo.getColumnCount();
		
		for(int i = 0; i < pOInfo.getColumnCount(); i++) {
			try {
				POInfoColumn col = pOInfo.getColumn(i);

				if(col.ColumnClass == Integer.class) {
					properties.put(col.ColumnName, rs.getInt(col.ColumnName));
				} else if (col.ColumnClass == BigDecimal.class) {
					properties.put(col.ColumnName, rs.getBigDecimal(col.ColumnName));
				} else if (col.ColumnClass == Boolean.class) {
					properties.put(
							col.ColumnName,
							new Boolean(
									rs.getString(col.ColumnName).equalsIgnoreCase("Y")
							)
					);
				} else if (col.ColumnClass == Timestamp.class) {
					properties.put(col.ColumnName, rs.getTimestamp(col.ColumnName));
				} else if (col.DisplayType == DisplayType.Binary ||
						col.DisplayType == DisplayType.Image ||
						col.DisplayType == DisplayType.TextLong) {
					properties.put(
							col.ColumnName, 
							get_LOB(rs.getObject(col.ColumnName))
					);
				} else if (col.ColumnClass == String.class) {
					properties.put(col.ColumnName, rs.getString(col.ColumnName));
				} else {
					properties.put(col.ColumnName, rs.getObject(col.ColumnName));
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "loading into properties",e);
			}
		}
	}
	
	private void loadProperties() {
		properties = new HashMap<String, Object>();
		pOInfo.getColumnCount();
		
		for(int i = 0; i < pOInfo.getColumnCount(); i++) {
			try {
				POInfoColumn col = pOInfo.getColumn(i);

				if(col.ColumnClass == Integer.class) {
					properties.put(col.ColumnName, new Integer(0).intValue());
				} else if (col.ColumnClass == BigDecimal.class) {
					properties.put(col.ColumnName, new BigDecimal("0"));
				} else if (col.ColumnClass == Boolean.class) {
					properties.put(
							col.ColumnName,
							new Boolean(true).booleanValue()
					);
				} else if (col.ColumnClass == Timestamp.class) {
					properties.put(col.ColumnName, DB.getTSForOrg(Env.getCtx()));
				} else if (col.DisplayType == DisplayType.Binary ||
						col.DisplayType == DisplayType.Image ||
						col.DisplayType == DisplayType.TextLong) {
					properties.put(
							col.ColumnName, 
							get_LOB(null)
					);
				} else if (col.ColumnClass == String.class) {
					properties.put(col.ColumnName, "");
				} else {
					properties.put(col.ColumnName, null);
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "loading into properties",e);
			}
		}
	}
	
	/**
	 * Process LOB objects
	 * @param value The LOB from the database
	 * @return The Java object from the LOB
	 */
	private Object get_LOB (Object value) {
		log.fine("Value=" + value);
		if (value == null)
			return null;
		//
		Object retValue = null;
		long length = -99;
		try {
			//[ 1643996 ] Chat not working in postgres port
			//Se agrega parche para postgresql. Jesus Cantu. Copiado de PO.
			if (value instanceof String ||
					value instanceof byte[]) {
				retValue = value;
			} else if (value instanceof Clob) {		//	returns String
			
				Clob clob = (Clob)value;
				length = clob.length();
				retValue = clob.getSubString(1, (int)length);
			} else if (value instanceof Blob) { //	returns byte[]
				Blob blob = (Blob)value;
				length = blob.length();
				int index = 1;	//	correct
				if (blob.getClass().getName().equals("oracle.jdbc.rowset.OracleSerialBlob"))
					index = 0;	//	Oracle Bug Invalid Arguments
								//	at oracle.jdbc.rowset.OracleSerialBlob.getBytes(OracleSerialBlob.java:130)
				retValue = blob.getBytes(index, (int)length);
			} else {
				log.log(Level.SEVERE, "Unknown: " + value);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Length=" + length, e);
		}
		
		return retValue;
	}	//	getLOB
}
