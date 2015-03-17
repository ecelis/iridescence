package org.compiere.util;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MTable;

/**
 * Caja de b&uacute;squeda r&aacute;pida
 * 
 * @author odelarosa
 * 
 */
public class MQuickSearch {
	private static CLogger s_log = CLogger.getCLogger(MQuickSearch.class);
	public static final SimpleDateFormat LUCENE_DATEFORMAT = new SimpleDateFormat("yyyyMMddHH:mm");

	public static void closeAnalyzer(StandardAnalyzer analyzer) {
		try {
			if (analyzer != null) {
				analyzer.close();
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void closeDirectory(Directory directory) {
		try {
			if (directory != null) {
				directory.close();
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void closeRender(IndexReader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void closeSearcher(IndexSearcher searcher) {
		try {
			if (searcher != null) {
				searcher.close();
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void closeWriter(IndexWriter writer) {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	/**
	 * @param tableName
	 *            Table Name, will be the physical FileName after Client/Org IDs
	 * @param suffix
	 *            FileName suffix, added at the end after a '@' character.
	 * @return File Name
	 */
	public static String getFileName(final String tableName, final String suffix) {
		if (StringUtils.isEmpty(tableName)) {
			throw new IllegalArgumentException("Missing mandatory information");
		}
		final StringBuilder fileName = new StringBuilder(Ini.getCompiereHome());
		fileName.append(File.separatorChar).append("data");
		fileName.append(File.separatorChar).append("dbData");
		fileName.append(File.separatorChar).append(Env.getAD_Client_ID(Env.getCtx()));
		fileName.append('_').append(Env.getAD_Org_ID(Env.getCtx()));
		fileName.append(File.separatorChar).append(tableName);
		if (!StringUtils.isBlank(suffix)) {
			fileName.append('@').append(suffix);
		}
		return fileName.toString();
	}

	private Class<?> clazz = null;
	private List<ValueName> colsNames = new ArrayList<ValueName>();
	private List<String> colsNamesStr = new ArrayList<String>();
	private String columnSort;
	// For component identification (Automated test tools)
	private String containerName = null;
	private Properties ctx;
	private File dbDirectory = null;
	private String instanceName = null;
	private List<Document> lastSearch = new ArrayList<Document>();
	private StringBuilder luceneParams = new StringBuilder();
	private boolean normal = true;
	private boolean onlyActive = false;
	private Object[] params = null;
	private StringBuilder querys = new StringBuilder();
	private int results = 60;
	private boolean searchSort = Boolean.TRUE;
	private long searchTime = 0;
	private String sortField;
	private StringBuilder sql = null;
	private String suffix = null;
	private MTable table = null;
	private boolean test = false;
	private int toShow = 2;
	private String typeSort;
	/** Use the table access level to restrict data access */
	private boolean useAccessLevel = true;
	private StringBuilder where = null;

	/**
	 * Constructor simple
	 * 
	 * @param clazz
	 *            Clase X que desea analizar
	 * @param ctx
	 *            Contexto
	 * @param onlyActive
	 *            Soloamente registros activos
	 * @param useDb
	 *            Usar o no Base de datos
	 * @throws Exception
	 */
	public MQuickSearch(Class<?> clazz, Properties ctx, boolean onlyActive, boolean useDb) throws Exception {
		this(clazz, null, ctx, onlyActive, useDb, true, null, new Object[0]);
	}

	/**
	 * Constructor simple
	 * 
	 * @param clazz
	 *            Clase X que desea analizar
	 * @param ctx
	 *            Contexto
	 * @param onlyActive
	 *            Soloamente registros activos
	 * @param useDb
	 *            Usar o no Base de datos
	 * @param useAccessLevel
	 *            Use table access level to restrict data access
	 * @throws Exception
	 */
	public MQuickSearch(Class<?> clazz, Properties ctx, boolean onlyActive, boolean useDb, boolean useAccessLevel) throws Exception {
		this(clazz, null, ctx, onlyActive, useDb, useAccessLevel, null, new Object[0]);
	}

	/**
	 * Constructor con Where y par&aacute;
	 * 
	 * @param clazz
	 *            Clase X que se desea analizar
	 * @param whereClause
	 *            Clausula where
	 * @param ctx
	 *            Contexto
	 * @param onlyActive
	 *            Soloamente registros activos
	 * @param useDB
	 *            Usar o no base de datos
	 * @param suffix
	 *            Sufijo para el nombre del indice
	 * @param parameters
	 *            Par&aacute;metros
	 * @throws Exception
	 */
	public MQuickSearch(Class<?> clazz, StringBuilder whereClause, Properties ctx, boolean onlyActive, boolean useDB, boolean useAccessLevel, String suffix, Object... parameters) throws Exception {
		this(clazz, whereClause, ctx, null, null, onlyActive, useAccessLevel, suffix, parameters);
	}

	public MQuickSearch(Class<?> clazz, StringBuilder whereClause, Properties ctx, boolean onlyActive, boolean useDB, String suffix, Object... parameters) throws Exception {
		this(clazz, whereClause, ctx, onlyActive, useDB, true, suffix, parameters);
	}

	/**
	 * Constructor b&uacute;squeda predefinida
	 * 
	 * @param clazz
	 *            Clase X que se desea analizar
	 * @param ctx
	 *            Contexto
	 * @param suffix
	 *            sufijo de la tabla
	 * @param onlyActive
	 *            Soloamente registros activos
	 * @throws Exception
	 */
	public MQuickSearch(Class<?> clazz, StringBuilder whereClause, Properties ctx, String suffix, boolean onlyActive, Object... parameters) throws Exception {
		this(clazz, whereClause, ctx, onlyActive, false, true, suffix, parameters);
	}

	/**
	 * Constructor con Where y par&aacute;
	 * 
	 * @param clazz
	 *            Clase X que se desea analizar
	 * @param whereClause
	 *            Clausula where
	 * @param ctx
	 *            Contexto
	 * @param columnName
	 *            Columna para ordenar SQL
	 * @param type
	 *            Tipo de ordenamiento ASC DESC
	 * @param onlyActive
	 *            Soloamente registros activos
	 * @param suffix
	 *            Sufijo para el nombre del indice
	 * @param parameters
	 *            Par&aacute;metros
	 * @throws Exception
	 */
	public MQuickSearch(Class<?> clazz, StringBuilder whereClause, Properties ctx, String columnName, String type, boolean onlyActive, boolean useAccessLevel, String suffix, Object... parameters) throws Exception {
		this.clazz = clazz;
		// valida que la clase tenga el nombre de la tabla
		if (clazz.getField("Table_Name") == null) {
			throw new IllegalArgumentException("Class doesn't have a table_name field");
		}
		this.ctx = ctx;
		this.table = MTable.get(this.ctx, clazz.getField("Table_Name").get(0).toString());
		// valida que haya un registro en ad_table para esa tabla
		if (table == null) {
			throw new IllegalArgumentException("Couldn't find the AD_Table record for the class");
		}
		this.where = whereClause;
		this.suffix = suffix;
		// valida que siempre que se construya una busqueda con condicion where
		// lleve un sufijo
		if (where != null && where.length() > 0 && StringUtils.isBlank(suffix)) {
			throw new IllegalArgumentException("Suffix is mandatory when the search has whereclause");
		}
		this.onlyActive = onlyActive;
		this.useAccessLevel = useAccessLevel;
		this.params = parameters;
		this.columnSort = columnName;
		this.typeSort = type;

		checkTables();
		createSql();
		init(null, false);
	}

	/**
	 * Agregar par&aacute;metro a lucene
	 * 
	 * @param name
	 *            Par&aacute;metro
	 * @param value
	 *            Valor
	 * @return Si se agrego o no
	 */
	public boolean addParam(String name, String value) {
		if (colsNames.contains(name)) {
			luceneParams.append(createParam(name, value));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Comprueba las tablas de b&uacute;squeda por &iacute;ndice
	 */
	private void checkTables() {
		QuickSearchTables tables = null;
		int clientId = Env.getAD_Client_ID(ctx);
		if (QuickSearchTables.allTables.contains(new QuickSearchTables(clientId))) {
			Iterator<QuickSearchTables> i = QuickSearchTables.allTables.iterator();
			while (i.hasNext()) {
				QuickSearchTables qSearchTables = i.next();
				if (qSearchTables.getClientId() == clientId) {
					tables = qSearchTables;
					break;
				}
			}
		} else {
			tables = new QuickSearchTables(clientId);
		}
		tables.addTable(table.getTableName(), onlyActive);
		QuickSearchTables.allTables.add(tables);
	}

	/**
	 * Crea par&aacute;metro de lucene
	 * 
	 * @param name
	 *            Par&aacute;metro
	 * @param value
	 *            Valor
	 * @return
	 */
	protected String createParam(String name, String value) {
		StringBuilder st = new StringBuilder(name).append(" : (\"");
		st.append(value).append("\") ");
		return st.toString();
	}

	/**
	 * Crea el sql basado en la tabla enviada y clausula where con sus
	 * par&aacute;metros
	 * 
	 */
	private void createSql() {
		// para soportar subquerys es necesario agregar "where" al inicio de la
		// cadena con la clausa where
		sql = QuickSearchTables.createSQL(table, colsNames, colsNamesStr);

		if (where != null) {
			sql.append(where);
		}

		if (useAccessLevel) {
			if (StringUtils.containsIgnoreCase(sql.toString(), "where")) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", table.getTableName()));
			} else {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " where ", table.getTableName()));
			}
		}

		if (StringUtils.isNotBlank(columnSort)) {
			sql.append(" ORDER BY ");
			sql.append(table.getTableName());
			sql.append(".");
			sql.append(columnSort);
			if (StringUtils.isNotBlank(typeSort)) {
				sql.append(" ").append(typeSort);
			}

		}
		if (colsNames.isEmpty()) {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Clase X analizada
	 * 
	 * @return
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	public String getColumnSort() {
		return columnSort;
	}

	public String getContainerName() {
		return containerName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public List<Document> getLastSearch() {
		return lastSearch;
	}

	/**
	 * M&eacute;todo de prueba
	 * 
	 * @return
	 */
	public StringBuilder getQuerys() {
		return querys;
	}

	public int getResults() {
		return results;
	}

	/**
	 * Obtener tiempo de b&uacute;squeda
	 * 
	 * @return
	 */
	public long getSearchTime() {
		return searchTime;
	}

	public String getSortField() {
		return sortField;
	}

	/**
	 * Sql de b&uacute;squeda
	 * 
	 * @return SQL
	 */
	public StringBuilder getSql() {
		return sql;
	}

	public MTable getTable() {
		return table;
	}

	public int getToShow() {
		return toShow;
	}

	public String getTypeSort() {
		return typeSort;
	}

	/**
	 * Inicializador de la b&uacute;squeda
	 * 
	 * @param trxName
	 *            Nombre de transacci&oacute;n
	 * @param reload
	 *            Racargar o no
	 */
	public void init(String trxName, boolean reload) {
		dbDirectory = new File(getFileName(table.getTableName(), suffix));

		if (reload) {
			loadDocuments(trxName);
		} else {
			if (!dbDirectory.exists() || QuickSearchTables.getToRebuild().remove(dbDirectory.getAbsolutePath())) {
				loadDocuments(trxName);
			}
		}
	}

	/**
	 * Solamente activos
	 * 
	 * @return
	 */
	public boolean isOnlyActive() {
		return onlyActive;
	}

	public boolean isSearchSort() {
		return searchSort;
	}

	/**
	 * @return the useAccessLevel
	 */
	public boolean isUseAccessLevel() {
		return useAccessLevel;
	}

	/**
	 * Usa o no base de datos
	 * 
	 * @return
	 */
	public boolean isUseDB() {
		return false;
	}

	/**
	 * Carga los documentos a un directorio f&iacute;sico
	 */
	private void loadDocuments(String trxName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StandardAnalyzer analyzer = null;
		Directory directory = null;
		IndexWriter writer = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();

			analyzer = new StandardAnalyzer(Version.LUCENE_36);
			directory = FSDirectory.open(dbDirectory);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
			// 10 segundos
			config.setWriteLockTimeout(10000);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			writer = new IndexWriter(directory, config);
			ResultSetMetaData rsMetaData = rs.getMetaData();

			while (rs.next()) {
				Document doc = new Document();
				doc.add(new Field("tablaID", String.valueOf(table.get_ID()), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				doc.add(new Field("tabla", table.getTableName(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				doc.add(new Field("id", rs.getString(table.getTableName() + "_ID"), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				Object val = null;
				Object type = null;
				for (int i = 0; i < colsNames.size(); i++) {
					try {
						String cn = colsNames.get(i).getName();
						String text;
						val = rs.getString(cn);
						type = rsMetaData.getColumnType(i + 2);
						// Se hace el comentario porque la primer columna de una
						// consulta es el indice que no se agrega a ColsName
						if (rsMetaData.getColumnType(i + 2) == Types.TIMESTAMP) {
							val = rs.getTimestamp(cn);
							long time = 0;
							if (rs.getTimestamp(cn) == null) {
								text = "";
							} else {
								time = rs.getTimestamp(cn).getTime();
								text = LUCENE_DATEFORMAT.format((Boolean) colsNames.get(i).getValue() ? SecureEngine.decrypt(rs.getTimestamp(cn)) : rs.getTimestamp(cn));
							}

							text = StringUtils.replace(text, "/", "_");
							doc.add(new Field(cn + "_ms", QuickSearchTables.encode(time), Field.Store.YES, Field.Index.ANALYZED));
						} else {
							// Se hace un remplazo si el texto es IN, por EN
							if ((Boolean) colsNames.get(i).getValue()) {
								text = SecureEngine.decrypt(rs.getString(cn));
							} else if ("AN".equalsIgnoreCase(rs.getString(cn))) {
								text = "ANN";
							} else if ("IN".equalsIgnoreCase(rs.getString(cn))) {
								text = "EN";
							} else {
								text = rs.getString(cn);
							}
						}

						doc.add(new Field(cn, StringUtils.defaultIfEmpty(text, " "), Field.Store.YES, Field.Index.ANALYZED));
					} catch (Exception e) {
						s_log.log(Level.SEVERE, "No se pudo guardar registro en archivo " + val.toString() + " " + type.toString(), e);
					}

				}
				writer.updateDocument(new Term("id", doc.get("id")), doc);
			}
			// mandar al log .- Lama
			final StringBuilder str = new StringBuilder();
			str.append("*** MQuickSearch Table: ").append(table.getTableName());
			str.append(" Directory (sufix): ").append(dbDirectory);
			if (where != null && where.length() > 0) {
				str.append(" \n Where Clause: ").append(where);
				if (params != null) {
					str.append(" Params: ").append(StringUtils.join(params, ","));
				}
			}
			s_log.log(Level.WARNING, str.toString());
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			closeWriter(writer);
			closeDirectory(directory);
			closeAnalyzer(analyzer);
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
	}

	/**
	 * Busca en el Reader el query enviado
	 * 
	 * @param reader
	 *            Reader
	 * @param query
	 *            Query
	 * @return
	 */
	private List<Document> search(IndexSearcher searcher, Query query) {

		ScoreDoc[] hits = null;
		lastSearch = new ArrayList<Document>();
		try {
			if (StringUtils.isNotBlank(getSortField())) {
				TopFieldCollector collector = TopFieldCollector.create(new Sort(new SortField(getSortField(), SortField.STRING, isSearchSort())), results, false, false, false, false);
				searcher.search(query, null, collector);
				hits = collector.topDocs(0, results).scoreDocs;
			} else {
				TopScoreDocCollector collector = TopScoreDocCollector.create(results, true);
				searcher.search(query, collector);
				hits = collector.topDocs(0, results).scoreDocs;
			}

			for (ScoreDoc doc : hits) {
				int docId = doc.doc;
				Document d = searcher.doc(docId);
				lastSearch.add(d);
			}
		} catch (CorruptIndexException e) {
			s_log.saveError("search", e);
		} catch (IOException e) {
			s_log.saveError("search", e);
		} finally {
			closeSearcher(searcher);
		}

		return lastSearch;
	}

	/**
	 * B&uacute;squeda en archivo
	 * 
	 * @param value
	 *            Texto a buscar
	 * @return
	 */
	public synchronized List<Document> search(String value, boolean escapeSpecialCharacters) {
		lastSearch = search(value, colsNamesStr.toArray(new String[0]), escapeSpecialCharacters);
		return lastSearch;
	}

	public List<Document> search(String value, String[] columns, boolean escapeSpecialCharacters) {
		return search(value, columns, escapeSpecialCharacters, true);
	}

	/**
	 * B&uacute;squeda en archivo
	 * 
	 * @param value
	 *            Valor a buscar
	 * @param columns
	 *            Columnas a buscar
	 * @param escapeSpecialCharacters
	 * @param magicSearch
	 *            agregar comodines *
	 * @return
	 */
	public synchronized List<Document> search(String value, String[] columns, boolean escapeSpecialCharacters, boolean magicSearch) {
		lastSearch = new ArrayList<Document>();
		long t01 = System.currentTimeMillis();
		IndexSearcher searcher = null;
		IndexReader reader = null;
		Directory directory = null;
		StandardAnalyzer analyzer = null;
		try {
			if (escapeSpecialCharacters) {
				value = value.replaceAll(Constantes.ESCAPE_CHARS, Constantes.REPLACE_CHARS);
				String val2 = value == null ? "" : value.trim();
				if (magicSearch) {
					while (val2.indexOf("  ") != -1) {
						val2 = StringUtils.replace(val2, "  ", " ");
					}
					value = Constantes.MANDATORY + StringUtils.replace(val2, " ", "* *") + Constantes.MANDATORY;
				}
			} else {
				value = StringUtils.defaultIfEmpty(value, "*");
			}

			analyzer = new StandardAnalyzer(Version.LUCENE_36);
			if (normal && (!dbDirectory.exists() || QuickSearchTables.getToRebuild().remove(dbDirectory.getAbsolutePath()))) {
				init(null, true);
			}

			directory = FSDirectory.open(dbDirectory);
			reader = IndexReader.open(directory);
			searcher = new IndexSearcher(reader);

			MultiFieldQueryParser qp0 = new MultiFieldQueryParser(Version.LUCENE_36, columns, analyzer);
			qp0.setDefaultOperator(MultiFieldQueryParser.AND_OPERATOR);
			qp0.setAllowLeadingWildcard(true);
			Query q = qp0.parse(luceneParams.toString() + (isOnlyActive() ? " IsActive:(Y)" : StringUtils.EMPTY) + StringUtils.replace(value, "/", "_"));
			// Para fechas Forward Slash no es soportado.
			// Se reemplaza la diagonal por guion al crear los archivos de
			// lucene
			// String queryStr =" || (Column1:*Every* Column2:11_08_2011) ";
			if (test) {
				querys.append(q.toString()).append(Constantes.NEWLINE);
			}
			lastSearch.addAll(search(searcher, q));
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			closeSearcher(searcher);
			closeRender(reader);
			closeDirectory(directory);
			closeAnalyzer(analyzer);
		}
		long t02 = System.currentTimeMillis();
		searchTime = searchTime + (t02 - t01);
		return lastSearch;
	}

	public void setColumnSort(String columnSort) {
		this.columnSort = columnSort;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public void setId(String containerName, String instanceName) {
		setContainerName(containerName);
		setInstanceName(instanceName);
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setLuceneParams(StringBuilder luceneParams) {
		this.luceneParams = luceneParams;
	}

	public void setOnlyActive(boolean onlyActive) {
		this.onlyActive = onlyActive;
	}

	/**
	 * Par&aacute;metros del SQL
	 * 
	 * @param params
	 */
	public void setParams(Object... params) {
		this.params = params;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public void setSearchSort(boolean searchSort) {
		this.searchSort = searchSort;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * Modificar SQL
	 * 
	 * @param sql
	 */
	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	public void setToShow(int toShow) {
		this.toShow = toShow;
	}

	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}

	/**
	 * @param useAccessLevel
	 *            the useAccessLevel to set
	 */
	public void setUseAccessLevel(boolean useAccessLevel) {
		this.useAccessLevel = useAccessLevel;
	}

}
