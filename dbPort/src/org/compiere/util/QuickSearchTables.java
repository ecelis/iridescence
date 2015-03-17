package org.compiere.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.compiere.model.MColumn;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MTable;

/**
 * Tablas de b&uacute;squeda r&aacute;pida
 * 
 * @author odelarosa
 * 
 */
public class QuickSearchTables implements Comparable<QuickSearchTables> {
	public static TreeSet<QuickSearchTables> allTables = new TreeSet<QuickSearchTables>();
	private static final String FORMAT = "000000000000000000";
	public static final long MAX_ALLOWED = 999999999999999999l;
	public static final long MIN_ALLOWED = -999999999999999999l;
	private static final char NEGATIVE_PREFIX = '-';
	// NB: NEGATIVE_PREFIX must be < POSITIVE_PREFIX
	private static final char POSITIVE_PREFIX = '0';
	private static CLogger s_log = CLogger.getCLogger(QuickSearchTables.class);
	public static TreeSet<String> toRebuild = new TreeSet<String>();

	public static void checkIndex(Properties ctx, Class<?> clazz, String tableName, int recordId, boolean onlyActive, String trxName) {
		File[] orgs = getOrgFolders(ctx);
		for (File org : orgs) {
			StringBuilder file = new StringBuilder(org.getAbsolutePath());
			file.append(File.separatorChar).append(tableName);

			final File index = new File(file.toString());
			if (index.exists()) {
				updateIndex(ctx, clazz, recordId, index, onlyActive, trxName);
			}
		}
	}

	/**
	 * Revisa si hay indices de nivel sistema y los elimina
	 * 
	 * @param ctx
	 *            Contexto
	 * @param tableName
	 *            Nombre de la Tabla
	 */
	private static void checkSystemIndex(Properties ctx, final String tableName) {
		try {
			File dbData = new File(getDBFolder(ctx).toString());
			if (dbData.exists()) {
				File[] clients = dbData.listFiles();

				for (File client : clients) {
					File[] indexes = client.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							if (name.equals(tableName) || StringUtils.startsWith(name, tableName + '@')) {
								return true;
							} else {
								return false;
							}
						}
					});

					for (File index : indexes) {
						markAsRebuild(index);
					}
				}
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Revisa si la tabla es de b&uacute;squeda r&aacute;pida
	 * 
	 * @param clazz
	 *            Clase X a analizar
	 * @param tableName
	 *            Nombre de la Tabla
	 * @param trxName
	 *            Transacci&oacute;n
	 * @param ctx
	 */
	public static void checkTables(Class<?> clazz, final String tableName, int recordId, String trxName, final Properties ctx) {
		int clientId = Env.getAD_Client_ID(ctx);
		if (clientId == 0) {
			checkSystemIndex(ctx, tableName);
		} else if (QuickSearchTables.allTables.contains(new QuickSearchTables(clientId))) {
			Iterator<QuickSearchTables> clients = allTables.iterator();
			while (clients.hasNext()) {
				QuickSearchTables qSearchTables = clients.next();
				if (clientId == qSearchTables.getClientId()) {
					if (qSearchTables.getTables().contains(new ValueName(tableName, null))) {
						try {
							final Iterator<ValueName> it = qSearchTables.getTables().iterator();
							boolean onlyActive = true;
							while (it.hasNext()) {
								ValueName vn = it.next();
								if (vn.getName().equals(tableName)) {
									onlyActive = (Boolean) vn.getValue();
									break;
								}
							}

							File[] orgs = getOrgFolders(ctx);

							for (File org : orgs) {
								StringBuilder file = new StringBuilder(org.getAbsolutePath());
								deleteIndexes(file, tableName, null); // Lama

								file.append(File.separatorChar).append(tableName);

								final File index = new File(file.toString());

								if (index.exists()) {
									updateIndex(ctx, clazz, recordId, index, onlyActive, trxName);
								}
							}

						} catch (Exception ex) {
							s_log.log(Level.SEVERE, "checkTables", ex);
							s_log.log(Level.SEVERE, "Problema actualizando Table="+tableName+ " - RecordID = "+recordId);
						}
					}
				}
			}
		}
	}

	/**
	 * Crear SQL en base a las columnas configuradas
	 * 
	 * @param table
	 *            Tabla a analizar
	 * @param colsNames
	 *            Contenedor Nombre de columnas
	 * @param colsNamesStr
	 *            Contenedor Nombre de columnas
	 * @return
	 */
	public static StringBuilder createSQL(MTable table, List<ValueName> colsNames, List<String> colsNamesStr) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			MColumn[] cols = table.getColumns(false);
			sql.append("SELECT ").append(table.getTableName()).append(".").append(table.getTableName()).append("_ID ");

			for (int i = 0; i < cols.length; i++) {
				// Lama - no funciona la busqueda con columnas virtuales.
				if (StringUtils.isBlank(cols[i].getColumnSQL())) {
					MColumn clmn = cols[i];
					String columnName = clmn.getColumnName();
					if (clmn.isSearchColumn() || "Value".equals(columnName) || "Name".equals(columnName) || "Description".equals(columnName) || "IsActive".equals(columnName)) {
						sql.append(", ");
						colsNames.add(new ValueName(columnName, clmn.isEncrypted()));
						colsNamesStr.add(columnName);
						sql.append(table.getTableName()).append(".");
						sql.append(columnName);
					}
				}
			}

			sql.append(" FROM ").append(table.getTableName());
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "createSQL", ex);
		}

		return sql;
	}

	/**
	 * Converts a String that was returned by {@link #encode} back to a long
	 * 
	 * @param str
	 * @return
	 */
	public static long decode(String str) {
		char prefix = str.charAt(0);
		long i = Long.parseLong(str.substring(1));
		if (prefix == POSITIVE_PREFIX) {
			// nop
		} else if (prefix == NEGATIVE_PREFIX) {
			i = i - MAX_ALLOWED - 1;
		} else {
			throw new NumberFormatException("string does not begin with the correct prefix");
		}
		return i;
	}

	/**
	 * Elimina los indices
	 * 
	 * @param ctx
	 * @param tableName
	 * @param suffix
	 * @author Lama
	 */
	public static void deleteIndexes(final Properties ctx, final String tableName, final String suffix) {
		try {
			File dbFolder = new File(getDBFolder(ctx).toString());

			File[] orgs = dbFolder.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {

					return StringUtils.startsWith(name, String.valueOf(Env.getAD_Client_ID(ctx)));
				}
			});

			for (File org : orgs) {
				StringBuilder file = new StringBuilder(org.getAbsolutePath());
				deleteIndexes(file, tableName, suffix); // Lama
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "checkTables", e);
		}

	}

	/**
	 * 
	 * @param file
	 * @param tableName
	 * @param suffix
	 * @throws IOException
	 */
	private static void deleteIndexes(final StringBuilder file, final String tableName, final String suffix) throws IOException {
		final File f = new File(file.toString());
		final File[] sFiles = f.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return StringUtils.startsWith(name, tableName + "@" + (suffix == null ? "" : suffix));
			}
		});

		for (File fileName : sFiles) {
			markAsRebuild(fileName);
		}
	}

	/**
	 * Converts a long to a String suitable for indexing
	 * 
	 * @param i
	 * @return
	 */
	public static String encode(long i) {
		if ((i < MIN_ALLOWED) || (i > MAX_ALLOWED)) {
			s_log.log(Level.SEVERE, "out of allowed range");
			return Long.toString(i);
		}
		char prefix;
		if (i < 0) {
			prefix = NEGATIVE_PREFIX;
			i = MAX_ALLOWED + i + 1;
		} else {
			prefix = POSITIVE_PREFIX;
		}
		DecimalFormat fmt = new DecimalFormat(FORMAT);
		return prefix + fmt.format(i);
	}

	public static StringBuilder getDBFolder(Properties ctx) {
		StringBuilder file = new StringBuilder(Ini.getCompiereHome());
		file.append(File.separatorChar).append("data");
		file.append(File.separatorChar).append("dbData");
		return file;
	}

	private static File[] getOrgFolders(final Properties ctx) {
		File dbFolder = new File(getDBFolder(ctx).toString());
		return dbFolder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {

				return StringUtils.startsWith(name, String.valueOf(Env.getAD_Client_ID(ctx)));
			}
		});
	}

	public static TreeSet<String> getToRebuild() {
		return toRebuild;
	}

	/**
	 * Actualizar Indice
	 * 
	 * @param ctx
	 *            Contexto
	 * @param clazz
	 *            Clase a analizar
	 * @param recordId
	 *            Registro actualizado
	 * @param dbDirectory
	 *            Directorio del indice
	 * @param onlyActive
	 *            Solamente registros activos
	 * @param trxName
	 *            Transaccion
	 */
	private static void updateIndex(Properties ctx, Class<?> clazz, int recordId, File dbDirectory, boolean onlyActive, String trxName) {
		try {
			MTable table = MTable.get(Env.getCtx(), clazz.getField("Table_Name").get(0).toString());
			List<ValueName> colsNames = new ArrayList<ValueName>();

			StringBuilder sql = createSQL(table, colsNames, new ArrayList<String>());

			sql.append(" WHERE ").append(table.getTableName()).append(".").append(table.getTableName()).append("_ID = ? ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), table.getTableName()));

			if (colsNames.isEmpty()) {
				throw new IllegalArgumentException();
			}

			StandardAnalyzer analyzer = null;
			Directory directory = null;
			IndexWriter writer = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				analyzer = new StandardAnalyzer(Version.LUCENE_36);
				directory = FSDirectory.open(dbDirectory);
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
				// 10 segundos
				config.setWriteLockTimeout(10000);
				config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
				writer = new IndexWriter(directory, config);

				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, recordId);
				rs = pstmt.executeQuery();
				ResultSetMetaData rsMetaData = rs.getMetaData();

				if (rs.next()) {
					Document doc = new Document();
					doc.add(new Field("tablaID", String.valueOf(table.get_ID()), Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc.add(new Field("tabla", table.getTableName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
					doc.add(new Field("id", rs.getString(table.getTableName() + "_ID"), Field.Store.YES, Field.Index.NOT_ANALYZED));

					for (int i = 0; i < colsNames.size(); i++) {
						String cn = colsNames.get(i).getName();
						String text;

						if (rsMetaData.getColumnType(i + 2) == Types.TIMESTAMP) {
							long time = 0;
							if (rs.getTimestamp(cn) == null) {
								text = "";
							} else {
								time = rs.getTimestamp(cn).getTime();
								text = MQuickSearch.LUCENE_DATEFORMAT.format((Boolean) colsNames.get(i).getValue() ? SecureEngine.decrypt(rs.getTimestamp(cn)) : rs.getTimestamp(cn));
							}

							text = StringUtils.replace(text, "/", "_");
							doc.add(new Field(cn + "_ms", QuickSearchTables.encode(time), Field.Store.YES, Field.Index.ANALYZED));
						} else {
							// Se hace un remplazo si el texto es IN, por EN
							if((Boolean) colsNames.get(i).getValue()) {
								text = SecureEngine.decrypt(rs.getString(cn));
							} else if("AN".equalsIgnoreCase(rs.getString(cn))){
								text = "ANN";
							} else if("IN".equalsIgnoreCase(rs.getString(cn))){
								text = "EN";
							} else {
								text = rs.getString(cn);
							}
						}

						doc.add(new Field(cn, StringUtils.defaultIfEmpty(text, " "), Field.Store.YES, Field.Index.ANALYZED));

					}
					writer.updateDocument(new Term("id", doc.get("id")), doc);
				} else {
					writer.deleteDocuments(new Term("id", String.valueOf(recordId)));
				}
				writer.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;

				if (writer != null) {
					writer.close();
				}

				if (analyzer != null) {
					analyzer.close();
				}

				if (directory != null) {
					directory.close();
				}
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "updateIndex", ex);
		}
	}

	private int clientId = 0;
	private int orgId = 0;
	private TreeSet<ValueName> tables = new TreeSet<ValueName>();

	/**
	 * Constructor por defecto
	 * 
	 * @param clientId
	 *            ID del cliente
	 */
	public QuickSearchTables(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * Agregar tabla
	 * 
	 * @param table
	 *            agregar tabla
	 */
	public void addTable(String table, boolean onlyActive) {
		tables.add(new ValueName(table, onlyActive));
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(QuickSearchTables o) {
		if (this.equals(o)) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuickSearchTables other = (QuickSearchTables) obj;
		if (clientId != other.clientId)
			return false;
		if (orgId != other.orgId)
			return false;
		return true;
	}

	/**
	 * ID de cliente
	 * 
	 * @return
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * ID de la organizacion
	 * 
	 * @return
	 */
	public int getOrgId() {
		return orgId;
	}

	/**
	 * Tablas de b&uacute;squeda
	 * 
	 * @return
	 */
	public TreeSet<ValueName> getTables() {
		return tables;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + orgId;
		return result;
	}

	/**
	 * Asignar ID del cliente
	 * 
	 * @param clientId
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * Asigna el ID de la organizacion
	 * 
	 * @param orgId
	 */
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**
	 * Asignar Tablas de B&uacute;squeda
	 * 
	 * @param tables
	 */
	public void setTables(TreeSet<ValueName> tables) {
		this.tables = tables;
	}

	public static void markAsRebuild(File file) {
		StandardAnalyzer analyzer = null;
		Directory directory = null;
		IndexWriter writer = null;

		try {
			analyzer = new StandardAnalyzer(Version.LUCENE_36);
			directory = FSDirectory.open(file);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);

			// 5 segunos
			config.setWriteLockTimeout(10000);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			writer = new IndexWriter(directory, config);

			QuickSearchTables.getToRebuild().add(file.getAbsolutePath());
			
			FileUtils.touch(file);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			MQuickSearch.closeWriter(writer);
			MQuickSearch.closeDirectory(directory);
			MQuickSearch.closeAnalyzer(analyzer);
		}
	}

	/**
	 * Reconstruir todos los indices que coincidan con el texto enviado
	 * @param text Texto a comparar, puede ser EXME_*, *Algo
	 */
	public static void rebuildAll(String text, Properties ctx) {
		File indexFolder = new File(QuickSearchTables.getDBFolder(ctx).toString());

		class NameFilter implements FilenameFilter {

			private final int END = 1;
			private final int EQUALS = 2;
			private final int START = 0;
			private String text;
			private int type;

			public NameFilter(String text) {
				if (StringUtils.startsWith(text, "*")) {
					type = END;
				} else if (StringUtils.endsWith(text, "*")) {
					type = START;
				} else {
					type = EQUALS;
				}
				this.text = StringUtils.removeStart(StringUtils.removeEnd(text, "*"), "*");
			}

			@Override
			public boolean accept(File dir, String name) {
				boolean retValue = false;

				switch (type) {
				case START:
					retValue = StringUtils.startsWith(name, text);
					break;
				case END:
					retValue = StringUtils.endsWith(name, text);
					break;
				case EQUALS:
					retValue = StringUtils.equals(name, text);
					break;
				}

				return retValue;
			}

		}
		
		// Revisamos que existan los indices a borrar, ya que si no se ha ejecutado ninguna
		// busqueda, arroja un NullPointerException. Jesus Cantu.
		File [] files = indexFolder.listFiles();
		if (files != null) { // Si existen se borran
			for (File parent : indexFolder.listFiles()) {
				for (File file : parent.listFiles(new NameFilter(text))) {
					QuickSearchTables.markAsRebuild(file);
				}
			}
		}

	}
	
}
