package org.compiere.util;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
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
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * Generic quick search
 * 
 * @author Lorena Lama <br>
 *         based on {@link MQuickSearch} created by Omar de la Rosa
 * 
 */
public class MQuickGenSearch {
	private static CLogger	slog			= CLogger.getCLogger(MQuickGenSearch.class);

	private List<ValueName>	colsNames		= new ArrayList<ValueName>();

	private List<String>	colsNamesStr	= new ArrayList<String>();
	private File			dbDirectory		= null;

	private Object[]		params			= null;

	private int				results			= 30;
	private StringBuilder	sql				= null;
	private String			suffix			= null;
	private String			tableName		= null;
	private Properties 		ctx				= null;
	private String   		orderType	 	= null;
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	private boolean			sortedDesc			= Boolean.FALSE;

	public MQuickGenSearch(final Properties ctx) {
		this.ctx = ctx;
	}

	/**
	 * @param sql Full SQL
	 * @param colsNames Name of the columns to return and search for.
	 * @param tableName Table Name. <br>
	 *            Will be used to recover ID as TableName+"_ID" and for the physical FileName
	 * @param suffix physical FileName suffix
	 * @param results Maximum number of search results
	 * @param parameters Parameters for the SQL
	 */
	public MQuickGenSearch(final Properties ctx, final StringBuilder sql, final List<ValueName> colsNames, final String tableName,
		final String suffix, int results, Object... parameters) {
		init(ctx, sql, colsNames, tableName, suffix, results, parameters);
	}

	public List<ValueName> getColsNames() {
		return colsNames;
	}

	public List<String> getColsNamesStr() {
		return colsNamesStr;
	}

	public File getDbDirectory() {
		return dbDirectory;
	}

	/**
	 * Create a document using the ResultSet
	 * 
	 * @param rs ResulSet
	 * @param rsMetaData 
	 * @return a new Document
	 * @throws SQLException
	 */
	private Document getDocument(final ResultSet rs, ResultSetMetaData rsMetaData) throws SQLException {
		final Document doc = new Document();
		// Record ID: TableName + "_ID"
		doc.add(new Field("id", rs.getString(tableName + "_ID"),
				Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
		boolean error = false;
		
		// Get the value in the result set for each column in the list
		for (int i = 0; i < colsNames.size(); i++) {
			String text = "";// text that will be added to the index file 
			try {
				final String colName = colsNames.get(i).getName();
				
				final boolean decrypt = (Boolean) colsNames.get(i).getValue();
				// transform dates to string when column tipe TIMESTAMP (gvaldez)
				// NOTA: El indice se esta tomando de la lista, si el SQL no va en el mismo orden
				// y lleva columnas de tipo fecha, esto se vera afectado. - Lama
				if (rsMetaData.getColumnType(i + 1) == Types.TIMESTAMP) {
					try {
						if (rs.getTimestamp(colName) == null) {
							text = "";
						} else {
							final Timestamp valTs = rs.getTimestamp(colName);
							text = Constantes.getSDFFechaCortaHora(ctx).format(decrypt ? SecureEngine.decrypt(valTs) : valTs);
						}
						// Para fechas se reemplazan los diagonales por guion bajo (gvaldez)
						text = StringUtils.replace(text, "/", "_");
					} catch (Exception e) { // Lama: se registra log especifico de TimeStamp
						error = true;
						slog.log(Level.SEVERE,
								"No se pudo guardar registro en archivo:"
										+ text + ", Columna (en RS): " + colName
										+ ", ColumnName en MetaData (en RS): " + rsMetaData.getColumnName(i + 1)
										+ ", ColumnType MetaData (en RS): " + rsMetaData.getColumnType(i + 1)
										+ " ", e);
					}
				} else {
					final String valRs = rs.getString(colName);
					text = decrypt ? SecureEngine.decrypt(valRs) : valRs;
				}
				doc.add(new Field(colName, StringUtils.defaultIfEmpty(text, " "), Field.Store.YES, Field.Index.ANALYZED));
			} catch (Exception e) {
				error = true;
				slog.log(Level.SEVERE, "No se pudo guardar registro en archivo:"
						+ text + ", Columna (en RS): " + colsNames.get(i).getName()
						+ ", ColumnName en MetaData (en RS): " + rsMetaData.getColumnName(i + 1)
						+ ", ColumnType MetaData (en RS): " + rsMetaData.getColumnType(i + 1)
						+ " ", e);
			}
		}
		
		if(error){
			slog.log(Level.SEVERE, "Error SQL>>\n " + sql.toString() + "\n colName >> " + colsNames.toString());
		}
		return doc;
	}

	public boolean isSortedDesc() {
		return sortedDesc;
	}

	public void setSortedDesc(boolean sortedDesc) {
		this.sortedDesc = sortedDesc;
	}
	
	public Object[] getParams() {
		return params;
	}

	public int getResults() {
		return results;
	}

	public StringBuilder getSql() {
		return sql;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * Initializes the search
	 * 
	 * @param sql Full SQL
	 * @param colsNames Name of the columns to return and search for. (must be included in the SQL)
	 * @param tableName Table Name. <br>
	 *            Will be used to recover ID as TableName+"_ID" and for the physical FileName
	 * @param suffix physical FileName suffix. Will be added to the index file name
	 * @param results Maximum number of search results.
	 * @param sortedDesc Sort results by First Column in Descending way
	 * @param parameters Parameters for the SQL
	 */
	public void init(final Properties ctx, final StringBuilder sql, final List<ValueName> colsNames, final String tableName,
		final String suffix, int results, final boolean sortedDesc, Object... parameters) {
		//Se elimino como requerido el subfijo 
		//gvaldez 05/28/2012
		if (sql == null || sql.length() == 0 || colsNames == null || colsNames.size() == 0
			|| StringUtils.isEmpty(tableName) ) {
			throw new IllegalArgumentException("Missing mandatory information");
		}
		this.ctx = ctx;
		this.sql = sql;
		this.tableName = tableName;
		this.params = parameters;
		this.suffix = suffix;
		this.colsNames = colsNames;
		this.sortedDesc = sortedDesc;
		for (ValueName object : colsNames) {
			this.colsNamesStr.add(object.getName());
		}
		this.results = results;
		load(false);
	}
	
	/**
	 * Initializes the search
	 * 
	 * @param sql Full SQL
	 * @param colsNames Name of the columns to return and search for. (must be included in the SQL)
	 * @param tableName Table Name. <br>
	 *            Will be used to recover ID as TableName+"_ID" and for the physical FileName
	 * @param suffix physical FileName suffix. Will be added to the index file name
	 * @param results Maximum number of search results.
	 * @param parameters Parameters for the SQL
	 */
	public void init(final Properties ctx, final StringBuilder sql, final List<ValueName> colsNames, final String tableName,
		final String suffix, int results, Object... parameters) {
		init(ctx, sql, colsNames, tableName, suffix, results,false, parameters);
	}

	/**
	 * Load documents into a physical directory
	 * 
	 * @param reload
	 *            <b>true</b> destroys the existing physical file and create a new one<br>
	 *            <b>false</b> create the physical file only if doesn't
	 *            exist
	 */
	public void load(boolean reload) {
		if (StringUtils.isEmpty(tableName) ) {
			throw new IllegalArgumentException("Missing mandatory information");
		}
		dbDirectory = new File(MQuickSearch.getFileName(tableName, suffix));
		if (reload) {
			loadDocuments();
		}
		else {
			if (!dbDirectory.exists()) {
				loadDocuments();
			}
		}
	}

	/** Load documents into a physical directory */
	private void loadDocuments() {
		if (sql == null || sql.length() == 0) {
			throw new IllegalArgumentException("Missing mandatory information");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StandardAnalyzer analyzer = null;
		Directory directory = null;
		IndexWriter writer = null;
		try {
			if (sortedDesc){
				sql.append(" order by 1 Desc");
			}
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			analyzer = new StandardAnalyzer(Version.LUCENE_33);
			directory = FSDirectory.open(dbDirectory);
			final IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			writer = new IndexWriter(directory, config);

			while (rs.next()) {
				final Document doc = getDocument(rs, rsMetaData);
				writer.updateDocument(new Term("id", doc.get("id")), doc);
			}
			writer.optimize();
		}
		catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e);
			throw new IllegalArgumentException(e);
		}
		catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			MQuickSearch.closeWriter(writer);
			MQuickSearch.closeDirectory(directory);
			MQuickSearch.closeAnalyzer(analyzer);
			DB.close(rs, pstmt);
		}
	}

	/**
	 * Find inside the Reader the sent query
	 * 
	 * @param reader Reader
	 * @param query Query
	 * @return
	 */
	private List<Document> search(IndexSearcher searcher, Query query) {
		final List<Document> documents = new ArrayList<Document>();
		try {
			final TopScoreDocCollector collector = TopScoreDocCollector.create(results, true);
			searcher.search(query, collector);
			final ScoreDoc[] hits = collector.topDocs(0, results).scoreDocs;

			for (ScoreDoc doc : hits) {
				documents.add(searcher.doc(doc.doc));
			}
		}
		catch (CorruptIndexException e) {
			slog.saveError("search", e);
		}
		catch (IOException e) {
			slog.saveError("search", e);
		}
		finally {
			MQuickSearch.closeSearcher(searcher);
		}
		return documents;
	}

	/**
	 * Search inside the physical file
	 * 
	 * @param value
	 *            Valor a buscar
	 * @param escapeSpecialCharacters
	 * @return
	 */
	public synchronized List<Document> search(String value, boolean escapeSpecialCharacters, String queryStr) {

		final List<Document> lst = new ArrayList<Document>();

		IndexSearcher searcher = null;
		IndexReader reader = null;
		Directory directory = null;
		StandardAnalyzer analyzer = null;
		try {

			if (escapeSpecialCharacters) {
				value =
				// add especial chars at the beginning
					Constantes.MANDATORY
					// INI add especial chars between each space
						+ StringUtils.replace(
						// INI replace special chars
							StringUtils.replace(
							// INI remove multiple spaces
								StringUtils.replace(value, "  ", " "),
								// END remove multiple spaces
								Constantes.ESCAPE_CHARS, Constantes.REPLACE_CHARS),
							// END replace special chars
							" ", "* *")
						// END add especial chars between each space
						// add especial chars at the end
						+ Constantes.MANDATORY;
			}

			analyzer = new StandardAnalyzer(Version.LUCENE_33);

			directory = FSDirectory.open(dbDirectory);
			reader = IndexReader.open(directory, true);
			searcher = new IndexSearcher(reader);

			MultiFieldQueryParser qp0 =
				new MultiFieldQueryParser(Version.LUCENE_33, colsNamesStr.toArray(new String[] {}), analyzer);
			//Lama: (Parche) se quita envio de AND_OPERATOR ya que para OV search se arman a mano los ANDS
			// FIXME actualmente solo se usa para BillingDashboard / OVSearch, parametrizar.
			if(StringUtils.isEmpty(queryStr)){ 
				qp0.setDefaultOperator(MultiFieldQueryParser.AND_OPERATOR);
			}
			qp0.setAllowLeadingWildcard(true);
			Query query = qp0.parse(StringUtils.replace(value, "/", "_"));
			//Para fechas Forward Slash no es soportado.
			//Se reemplaza la diagonal por guion al crear los archivos de lucene
			// String queryStr =" || (Column1:*Every* Column2:11_08_2011) ";
			if (StringUtils.isNotEmpty(queryStr)) {
				query = qp0.parse("+(" + query.toString() + ")" + queryStr + "");
			}
			lst.addAll(search(searcher, query));
		}
		catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			MQuickSearch.closeSearcher(searcher);
			MQuickSearch.closeRender(reader);
			MQuickSearch.closeDirectory(directory);
			MQuickSearch.closeAnalyzer(analyzer);
		}
		return lst;
	}
}
