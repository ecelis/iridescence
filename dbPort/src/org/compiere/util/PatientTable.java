package org.compiere.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MTable;

/**
 * Tabla del paciente
 * 
 * @author odelarosa
 * Modified by Lorena Lama
 */
public class PatientTable {
	/**
	 * Tabla
	 * 
	 * @author odelarosa
	 * 
	 */
	public static class Table implements Comparable<Table> {
		private String tableName;
		private String tableId;
		private List<String> sqls = new ArrayList<String>();

		/**
		 * Constructor por defecto
		 * 
		 * @param tableName
		 *            Nombre de la tabla
		 * @param tableId
		 *            Id de la Tabla
		 */
		public Table(String tableName, String tableId) {
			this.tableName = tableName;
			this.tableId = tableId;
		}

		/**
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Table o) {
			return o.getTableName().compareTo(o.getTableName());
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Table other = (Table) obj;
			if (tableName == null) {
				if (other.tableName != null) {
					return false;
				}
			} else if (!tableName.equalsIgnoreCase(other.tableName)) {
				return false;
			}
			return true;
		}

		/**
		 * SQLs para la tabla
		 * 
		 * @return sqls
		 */
		public List<String> getSqls() {
			return sqls;
		}

		/**
		 * Id de la Tabla
		 * 
		 * @return tableId
		 */
		public String getTableId() {
			return tableId;
		}

		/**
		 * Nombre de la Tabla
		 * 
		 * @return tableName
		 */
		public String getTableName() {
			return tableName;
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result;
			result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
			return result;
		}

		/**
		 * Asigna los sqls de la tabla
		 * 
		 * @param sqls
		 */
		public void setSqls(List<String> sqls) {
			this.sqls = sqls;
		}

		/**
		 * Asigna el id de la tabla
		 * 
		 * @param tableId
		 */
		public void setTableId(String tableId) {
			this.tableId = tableId;
		}

		/**
		 * Asigna el nombre de la tabla
		 * 
		 * @param tableName
		 */
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
	}

	private static CLogger log = CLogger.getCLogger(PatientTable.class);

	public static List<Table> tables = null;
	public static List<String> tableIds = null;

	/**
	 * Inicializa las tablas del paciente
	 * 
	 * @param ctx
	 *            Contexto
	 */
	public static void init(Properties ctx) {
		init(ctx, false);
	}

	/**
	 * Inicializa las tablas del paciente
	 * 
	 * @param ctx
	 * @param reQuery
	 */
	public static void init(Properties ctx, boolean reQuery) {
		try {
			final String cont = FileUtils.readFileToString(new File(Ini.getCompiereHome() + File.separatorChar
					+ "patientTables.txt"));
			final String[] lines = StringUtils.split(cont, "\n");
			if (tables == null || reQuery) {
				tables = new ArrayList<PatientTable.Table>();
				tableIds = new ArrayList<String>();

				for (String line : lines) {
					final String[] pair = StringUtils.split(line, "-");
					if (pair.length == 2) {
						final String tableName = StringUtils.trim(pair[0]);
						final String sql = pair[1];
						final MTable mTable = MTable.get(ctx, tableName);
						Table table = new Table(tableName, String.valueOf(mTable.getAD_Table_ID()));
						tableIds.add(String.valueOf(mTable.getAD_Table_ID()));
						final int index = tables.indexOf(table);
						if (index < 0) {
							tables.add(table);
						} else {
							table = tables.get(index);
						}
						table.getSqls().add(sql);
					}
				}
				final Set<String> set = new HashSet<String>(tableIds);
				tableIds = new ArrayList<String>(set);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "PatientTable.init", e);
		}
	}

}
