package org.compiere.model.accessdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * 
 * @author Expert
 * 
 */
public class AccessDB {//extends POQuery {

//	/** serialVersionUID */
//	private static final long serialVersionUID = 5118520183781819264L;
//	/** */
//	private transient CLogger log = null;
//	/** String query */
//	private String sql = null;
//	/** parameters */
//	public Object[] params = null;
//	/** main table */
//	private transient PO mainTable = null;
//	/** kind of consultation process */
//	public transient POQuery mainClass = null;
//	/**  */
//	public static final String PUNTO = ".";
//	/** AS nick */
//	public static final String QUERY_AS = " AS ";
//	/** coma and space */
//	public static final String COMA_ESP = ", ";
//	/** space */
//	public static final String ESP = " ";
//
//	/**
//	 * Constructor step 1a
//	 * 
//	 * @param ctx
//	 * @param test
//	 */
//	public AccessDB(final Properties ctx, final boolean test) {
//		super(ctx, test);
//
//		log = CLogger.getCLogger(AccessDB.class);
//		this.test = test;
//	}
//
//	/**
//	 * Required values step 2a
//	 * 
//	 * @param key_ID
//	 * @param mainTable
//	 * @param mainClass
//	 */
//	public void setValues(final int pKey_ID, final PO pMainTable,
//			final POQuery pMainClass) {
//
//		this.mainTable = pMainTable;
//		this.mainClass = pMainClass;
//
//		if (pKey_ID > 0) {
//			this.params = new Object[] { pKey_ID, DB.TO_STRING(true) };
//		}
//	}
//
//	/**
//	 * Validate Required values
//	 * 
//	 * @return
//	 */
//	private boolean validate() {
//		boolean _continue = true;
//		if (ctx == null || params == null || mainTable == null
//				|| mainClass == null) {
//			_continue = false;
//		} else {
//			if (test && params != null) {
//				log.log(Level.INFO, "validate.params > " + params.length);
//			}
//		}
//		return _continue;
//	}
//
//	/**
//	 * Constructor step 1b
//	 * 
//	 * @param ctx
//	 *            context
//	 * @param key_ID
//	 *            ID of the parent table
//	 * @param mainTable
//	 *            main table
//	 * @param mainClass
//	 *            tables that you refer to or refer you to the main table
//	 */
//	public AccessDB(final Properties ctx, final int pKey_ID,
//			final Object pMainTable, final Object pMainClass,
//			final boolean pTest, final int idses) {
//		super(ctx, pTest);
//
//		this.mainTable = (PO) pMainTable;
//		this.mainClass = (POQuery) pMainClass;
//		this.detalle = idses;
//
//		if (pKey_ID > 0) {
//			this.params = new Object[] { pKey_ID, DB.TO_STRING(true) };
//		}
//	}
//
//	/**
//	 * create a query from multiple tables from a master step 3a,2b,2c
//	 * 
//	 * @return Query
//	 */
//	public Query buildSql() {
//
//		Query query = null;
//
//		if (!validate()) {
//			return query;
//		}
//
//		try {
//
//			// Prefijo detalle.
//			StringBuilder prefijo = new StringBuilder(mainTable.getP_info()
//					.getNickname());
//			prefijo.append(PUNTO);
//
//			// where
//			StringBuilder where = new StringBuilder(prefijo)
//					.append(where(mainTable));
//
//			// Order
//			StringBuilder order = new StringBuilder(prefijo)
//					.append(order(mainTable));
//
//			// from + join + otras tablas
//			StringBuilder from = from(mainTable);
//
//			// Select + otras columnas
//			StringBuilder select = select(mainTable);
//
//			// query
//			query = new Query(ctx, mainTable.getP_info().getTableName(), where
//					.toString(), null);
//
//			// Nivel de acceso
//			query.setApplyAccessFilter(true);
//
//			// solo registros activos
//			query.setOnlyActiveRecords(true);
//
//			// params
//			query.setParameters(params);
//
//			// Order
//			query.setOrderBy(order.toString());
//
//			// Select + from
//			StringBuffer sqlBuffer = new StringBuffer();
//			sqlBuffer.append(select).append(from);
//
//			// Query solo de esa tabla
//			sql = query.getSQL(sqlBuffer, mainTable.getP_info().getNickname());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			query = null;
//		}
//
//		return query;
//	}
//
//	/************************************************************************/
//
//	/**
//	 * where
//	 * 
//	 * @return StringBuilder where
//	 */
//	@Override
//	public StringBuilder where(PO firstTable) {
//
//		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		if (mainClass.where(firstTable) == null) {
//			where.append(firstTable.getP_info().getColumnKey()).append(" = ? ");
//
//			if (test) {
//				log.log(Level.INFO, "where > " + where.toString());
//			}
//		} else {
//			where.append(mainClass.where(firstTable));
//		}
//		return where;
//	}
//
//	/**
//	 * Order
//	 * 
//	 * @return StringBuilder order
//	 */
//	@Override
//	public StringBuilder order(PO firstTable) {
//
//		StringBuilder order = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		if (mainClass.order(firstTable) == null) {
//			order.append(firstTable.getP_info().getColumnKey()).append(" ");
//
//			if (test) {
//				log.log(Level.INFO, "order > " + order.toString());
//			}
//		} else {
//			order.append(mainClass.order(firstTable));
//		}
//		return order;
//	}
//
//	/**
//	 * Select + other columns
//	 * 
//	 * @return StringBuilder select
//	 */
//	@Override
//	public StringBuilder select(PO firstTable) {
//
//		StringBuilder select = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		if (mainClass.select(firstTable) == null) {
//			select.append(otherColumns()).append(" ");
//			select.append(allColumns()).append(" ");
//
//			if (test) {
//				log.log(Level.INFO, "select > " + select.toString());
//			}
//		} else {
//			select.append(mainClass.select(firstTable));
//		}
//		return select;
//	}
//
//	/**
//	 * from + joins
//	 * 
//	 * @return StringBuilder from
//	 */
//	@Override
//	public StringBuilder from(PO firstTable) {
//
//		StringBuilder from = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		if (mainClass.from(firstTable) == null) {
//			from.append(otherTables()).append(" ");
//
//			if (test) {
//				log.log(Level.INFO, "from > " + from.toString());
//			}
//		} else {
//			from.append(mainClass.from(firstTable));
//		}
//		return from;
//	}
//
//	/************************************************************************/
//
//	/**
//	 * columns in the tables that relate
//	 * 
//	 * @return
//	 */
//	private StringBuilder otherColumns() {
//		StringBuilder s_select = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY);
//
//		// first table
//		s_select.append(mainTable.getP_info().buildSelect(false, true));
//
//		// second table
//		for (int i = 0; i < mainClass.parentClasses.length; i++) {
//			s_select.append(COMA_ESP);
//			s_select.append(mainClass.parentClasses[i].getP_info().buildSelect(
//					true, true));
//		}
//
//		if (test) {
//			log.log(Level.INFO, "columnasTratamientos> " + s_select.toString());
//		}
//		return s_select;
//	}
//
//	/**
//	 * columns related to the child tables
//	 * 
//	 * @return
//	 */
//	private StringBuilder allColumns() {
//		StringBuilder s_selectOtras = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY);
//
//		for (int i = 0; i < mainClass.parentClasses.length; i++) {
//			// Listado de tablas referenciadas
//			PO[] lstPO = mainClass.subclasses.get(mainClass.parentClasses[i]
//					.get_TableName());
//
//			// 
//			if (lstPO == null) {
//				continue;
//			}
//
//			// 
//			for (int j = 0; j < lstPO.length; j++) {
//				s_selectOtras.append(COMA_ESP);
//				s_selectOtras.append(lstPO[j].getP_info().buildSelect(true,
//						true));
//			}
//		}
//
//		if (test) {
//			log.log(Level.INFO, "otrasColumnas> " + s_selectOtras.toString());
//		}
//		return s_selectOtras;
//	}
//
//	/**
//	 * tables related to the main table
//	 * 
//	 * @return
//	 */
//	private StringBuilder otherTables() {
//		StringBuilder s_from = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		s_from.append(mainTable.getP_info().buildFrom()).append(" ");
//
//		for (int i = 0; i < mainClass.parentClasses.length; i++) {
//
//			// Relacion tabla refencia a la tabla principal trat_serv ->
//			// trat_det
//			s_from.append(getLeftJoin(mainClass.parentClasses[i]
//					.get_TableName(), mainClass.parentClasses[i].getNickName(),
//					mainTable.get_TableName(), mainTable.getNickName()));
//		}
//
//		if (test) {
//			log.log(Level.INFO, "otrasTablas> " + s_from.toString());
//		}
//
//		return s_from;
//	}
//
//	/*****************************************************************************/
//
//	/**
//	 * execute the query step 4a,3b,3c
//	 */
//	public void executeQuery() {
//		this.buildSql();
//		if (test) {
//			executeQueryDBTest();
//		} else {
//			executeQueryDB();
//		}
//	}
//
//	/**
//	 * execute the query
//	 */
//	private void executeQueryDB() {
//
//		// ResultSet
//		BeanResultSet result = resultSetDB();
//
//		// object's PO
//		reload(result);
//
//	}
//
//	/**
//	 * execute the query formed
//	 * 
//	 * @return
//	 */
//	private BeanResultSet resultSetDB() {
//
//		// ResultSet
//		BeanResultSet result = new BeanResultSet(this.getSql(), Arrays
//				.asList(this.getParams()));
//		if (sql != null) {
//			result.execResultSet();
//		}
//		return result;
//	}
//
//	@SuppressWarnings("unchecked")
//	private HashMap<Class, List<PO>> hm = new HashMap<Class, List<PO>>();
//
//	@SuppressWarnings("unchecked")
//	public HashMap<Class, List<PO>> getHm() {
//		return hm;
//	}
//
//	@SuppressWarnings("unchecked")
//	public void setHm(HashMap<Class, List<PO>> hm) {
//		this.hm = hm;
//	}
//
//	@SuppressWarnings("unchecked")
//	private HashMap<Class, List<TreatmentsStatus>> hmGrid = new HashMap<Class, List<TreatmentsStatus>>();
//
//	@SuppressWarnings("unchecked")
//	public HashMap<Class, List<TreatmentsStatus>> getHmGrid() {
//		return hmGrid;
//	}
//
//	@SuppressWarnings("unchecked")
//	public void setHmGrid(HashMap<Class, List<TreatmentsStatus>> hmGrid) {
//		this.hmGrid = hmGrid;
//	}
//
//	private int detalle = 0;
//
//	public int getDetalle() {
//		return detalle;
//	}
//
//	public void setDetalle(int detalle) {
//		this.detalle = detalle;
//
//	}
//
//	/**
//	 * 
//	 * @param result
//	 */
//	private void reload(BeanResultSet result) {
//		// visualize the result
//		if (result == null || result.getMapMetaData() == null) {
//			return;
//		}
//
//		// rows of the resultset
//		for (int i = 0; i < result.getMapMetaData().size(); i++) {
//			// Related Tables
//			for (int j = 0; j < this.mainClass.parentClasses.length; j++) {
//				// ResultSet -> PO
//				PO objPO = this.mainClass.parentClasses[j];
//
//				objPO.load(result.getMapMetaData().get(i).getMap());
//
//				if (hm.containsKey(this.mainClass.parentClasses[j].getClass())) {
//					List<PO> lst = hm.get(this.mainClass.parentClasses[j]
//							.getClass());
//					if (lst != null) {
//						lst.add(objPO);
//					}
//					hm.remove(this.mainClass.parentClasses[j].getClass());
//					hm.put(this.mainClass.parentClasses[j].getClass(), lst);
//
//					TreatmentsStatus treatmentsStatus = new TreatmentsStatus(
//							ctx, this.mainClass.parentClasses[j].getClass(),
//							objPO, detalle);
//					List<TreatmentsStatus> lsthmGrid = hmGrid
//							.get(this.mainClass.parentClasses[j].getClass());
//					if (lsthmGrid != null) {
//						lsthmGrid.add(treatmentsStatus);
//					}
//					hmGrid.remove(this.mainClass.parentClasses[j].getClass());
//					hmGrid.put(this.mainClass.parentClasses[j].getClass(),
//							lsthmGrid);
//
//				} else {
//					List<PO> lstPO = new ArrayList<PO>();
//					lstPO.add(objPO);
//					hm.put(this.mainClass.parentClasses[j].getClass(), lstPO);
//
//					List<TreatmentsStatus> lsthmGrid = new ArrayList<TreatmentsStatus>();
//					TreatmentsStatus treatmentsStatus = new TreatmentsStatus(
//							ctx, this.mainClass.parentClasses[j].getClass(),
//							objPO, detalle);
//					lsthmGrid.add(treatmentsStatus);
//
//					hmGrid.put(this.mainClass.parentClasses[j].getClass(),
//							lsthmGrid);
//				}
//
//			}
//		}
//
//		// Tabla principal
//		List<PO> lstPOs = new ArrayList<PO>();
//		lstPOs.add(mainTable);
//		hm.put(mainTable.getClass(), lstPOs);
//
//		List<TreatmentsStatus> lsthmGrid = new ArrayList<TreatmentsStatus>();
//		TreatmentsStatus tStatus = new TreatmentsStatus(ctx, this.mainTable
//				.getClass(), mainTable, detalle);
//		lsthmGrid.add(tStatus);
//
//		hmGrid.put(this.mainTable.getClass(), lsthmGrid);
//	}
//
//	/**
//	 * execute the query formed test
//	 */
//	private void executeQueryDBTest() {
//
//		// ResultSet
//		BeanResultSet result = resultSetDB();
//
//		// object's PO
//		reload(result);
//
//		// visualize the result
//		if (result.getMapMetaData() != null && test) {
//
//			// rows of the resultset
//			for (int i = 0; i < result.getMapMetaData().size(); i++) {
//
//				// Related Tables
//				for (int j = 0; j < this.mainClass.parentClasses.length; j++) {
//					// table
//					log.log(Level.INFO, "Tabla name"
//							+ this.mainClass.parentClasses[j].getP_info()
//									.getTableName());
//
//					// numero de columnas del objeto
//					for (int j2 = 0; j2 < this.mainClass.parentClasses[j]
//							.getP_info().getColumnCount(); j2++) {
//
//						// name column
//						// value column
//						log
//								.log(
//										Level.INFO,
//										" columna ["
//												+ this.mainClass.parentClasses[j]
//														.getP_info()
//														.getColumnName(j2)
//												+ " valor ["
//												+ this.mainClass.parentClasses[j]
//														.get_Value(this.mainClass.parentClasses[j]
//																.getP_info()
//																.getColumnName(
//																		j2))
//												+ "] ");
//
//					}
//				}
//			}
//
//		}
//	}
//
//	/*****************************************************************************/
//
//	/**
//	 * LEFT JOIN
//	 * 
//	 * @param joinTable
//	 * @param joinNick
//	 * @param fromTable
//	 * @param fromNick
//	 * @return
//	 */
//	public static StringBuilder getLeftJoin(String joinTable, String joinNick,
//			String fromTable, String fromNick) {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" LEFT JOIN ").append(joinTable).append(ESP)
//				.append(joinNick);
//		sql.append(" ON ");
//
//		sql.append(fromNick).append(PUNTO).append(fromTable).append("_ID = ");
//		sql.append(joinNick).append(PUNTO).append(fromTable).append("_ID AND ");
//
//		sql.append(joinNick).append(".IsActive = 'Y' ");
//		return sql;
//
//	}
//
//	/**
//	 * LEFT JOIN
//	 * 
//	 * @param joinTable
//	 * @param joinNick
//	 * @param fromTable
//	 * @param fromNick
//	 * @return
//	 */
//	public static StringBuilder getLeftJoinInv(String joinTable,
//			String joinNick, String fromTable, String fromNick) {
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		sql.append(" LEFT JOIN ").append(joinTable).append(ESP)
//				.append(joinNick);
//		sql.append(" ON ");
//
//		sql.append(fromNick).append(PUNTO).append(joinTable).append("_ID = ");
//		sql.append(joinNick).append(PUNTO).append(joinTable).append("_ID AND ");
//
//		sql.append(joinNick).append(".IsActive = 'Y' ");
//		return sql;
//
//	}
//
//	/*****************************************************************************/
//
//	public Object[] getParams() {
//		return params;
//	}
//
//	public void setParams(Object[] params) {
//		this.params = params;
//	}
//
//	public String getSql() {
//		return sql;
//	}
//
//	public void setSql(String sql) {
//		this.sql = sql;
//	}
}