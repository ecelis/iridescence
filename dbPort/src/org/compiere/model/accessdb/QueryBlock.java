package org.compiere.model.accessdb;

import java.io.Serializable;

public class QueryBlock implements Serializable {
	
//	/** serialVersionUID */
//	private static final long serialVersionUID = 2417484621661981386L;
//	private String keyColumn1;
//	private String keyColumn2;
//	private String keyColumn3;
//	private StringBuilder sql = null;
//	private StringBuilder sqlInit = null;
//	private StringBuilder sqlOrder = null;
//	private StringBuilder sqlCount = null;
//	private int noRegistros = 0;
//	private int maximoReg;
//	private int totalBlocks;
//	private int blocksActual;
//	/** valores inferior y superior */
//	private String valInferior1, valSuperior1;
//	private String valInferior2, valSuperior2;
//	private String valInferior3, valSuperior3;
//	private List<Bean4String> indice;
//	private List<Bean4String> indiceFin;
//	
//	/**
//	 * Constructor
//	 * @param ctx : Contexto
//	 * @param pSqlSelect : Seccion SELECT de la consulta
//	 * @param pSqlFrom : Seccion FROM de la consulta
//	 * @param pSqlWhere : Seccion WHERE de la consulta
//	 * @param pSqlOrder : Seccion ORDER BY de la consulta
//	 * @param pKeyColumn : Columna por la que se ordena
//	 */
//	public QueryBlock (Properties ctx, String pSqlSelect, String pSqlFrom, String pSqlWhere, String pSqlOrder, 
//			String pKeyColumn, String pKeyColumn2,  String pKeyColumn3){
//		super();
//		// Llaves para considerar en el WHERE y en el ORDER BY
//		keyColumn1 = pKeyColumn;
//		keyColumn2 = pKeyColumn2;
//		keyColumn3 = pKeyColumn3;
//		
//		// Creacion de las consultas
//		begin(ctx, pSqlSelect, pSqlFrom,  pSqlWhere,  pSqlOrder);
//	}
//	
//	
//	/**
//	 * createQuery
//	 * @param ctx
//	 * @param pSqlSelect
//	 * @param pSqlFrom
//	 * @param pSqlWhere
//	 * @param pSqlOrder
//	 * @return
//	 */
//	private void begin(Properties ctx, String pSqlSelect, String pSqlFrom, String pSqlWhere, String pSqlOrder)
//	{
//		//	SQL sin order
//		sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(pSqlSelect)
//		.append(pSqlFrom)
//		.append(pSqlWhere);
//		
//		// SQL Order
//		sqlOrder = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sqlOrder.append(pSqlOrder);
//		
//		// SQL inicial
//		sqlInit = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sqlInit.append(pSqlSelect)
//		.append(pSqlFrom)
//		.append(pSqlWhere)
//		.append(sqlOrder);
//		
//		// SQL contar registros
//		sqlCount = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sqlCount.append("SELECT COUNT(*) ");
//		sqlCount.append(pSqlFrom)
//		.append(pSqlWhere)
//		.append(pSqlOrder);
//		
//		// numero de registros
//		noRegistros = DB.getSQLValue(null, sqlCount.toString());
//		maximoReg = MEXMEMejoras.get(ctx).getMaxQueryRecords();
//		
//		// Numero de bloques
//		double value = noRegistros / maximoReg;
//		totalBlocks = new Double(Math.ceil(value)).intValue();
//		
//		// Para almacenes los valores del primer registro
//		// (3 valores: La clase, el nombre y el id del producto)
//		indice = new ArrayList<Bean4String>();
//		for (int i = 0; i < totalBlocks; i++) {
//			indice.add(new Bean4String());
//		}
//		
//		// Para almacenes los valores del ultimo registro 
//		// (3 valores: La clase, el nombre y el id del producto)
//		indiceFin = new ArrayList<Bean4String>();
//		for (int i = 0; i < totalBlocks; i++) {
//			indiceFin.add(new Bean4String());
//		}
//	}	//	createSelectSql
//
//	/****************************************************************************************/
//	/**
//	 * test
//	 * @return
//	 */
//	public String crearConsultaInit(){
//		return crearConsulta(0, 0, null, null);
//	}
//	
//	/**
//	 * Crea la consulta
//	 * @param block
//	 * @param direccion
//	 * @return
//	 */
//	public String crearConsulta(int pblockActual, int direccion, String where, String order){
//		
//		blocksActual = pblockActual;//0, 1
//		
//		
//		// 0 carga los 100 primeros
//		if(direccion!=0){
//			// cero que pertenece en el mismo lugar
//			if(direccion<0)//-1
//				blocksActual--;
//			else//1
//				blocksActual++;
//		}
//		
//		
//		if(blocksActual>0){
//			blockActual(pblockActual, direccion);
//		}
//		
//		StringBuilder  sqlBlock= new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		
//		sqlBlock.append(" SELECT * FROM  ( ")
//		.append(sql)// sin el order
//		.append(condicion())
//		.append(where!=null?where:" ")
//		.append(order!=null?order:sqlOrder);
//		
//		if (DB.isOracle()) {
//			sqlBlock.append(" ) WHERE rowNum <= ").append(maximoReg);
//		} else if (DB.isPostgreSQL()) {
//			sqlBlock.append(" ) AS consulta").append(StringUtils.EMPTY);
//			sqlBlock = new StringBuilder(DB.getDatabase().addPagingSQL(sqlBlock.toString(), 1, maximoReg));
//		}
//		
//		return sqlBlock.toString();
//	}
//	
//	
//	/**
//	 * Crea los limites de los tres crierios
//	 * @param block
//	 * @param direccion
//	 */
//	private void blockActual (int pblockAnterior, int direccion){
//		
//		valInferior1 = null; valSuperior1 = null;
//		valInferior2 = null; valSuperior2 = null;
//		valInferior3 = null; valSuperior3 = null;
//		
//		//0 (cero no entra. aun no tiene datos)
//		//1 (segundo bloque)
//		if(indice!=null){
//			
//			if(direccion>0){
//				valInferior1 = indiceFin.get(pblockAnterior).getCadena1();//product Class
//				//valSuperior1 = indiceFin.get(pblockAnterior).getCadena1();
//
//				valInferior2 = indiceFin.get(pblockAnterior).getCadena2();//name
//				//valSuperior2 = indiceFin.get(pblockAnterior).getCadena2();
//
//				valInferior3 = indiceFin.get(pblockAnterior).getCadena3();//id product
//				//valSuperior3 = indiceFin.get(pblockAnterior).getCadena3();
//			} else {
//				valSuperior1 = indice.get(pblockAnterior).getCadena1();
//
//				valSuperior2 = indice.get(pblockAnterior).getCadena2();
//
//				valSuperior3 = indice.get(pblockAnterior).getCadena3();
//
//			}
//
//		}
//	}
//
//	/**
//	 * condicion de limites
//	 * @return
//	 */
//	private String condicion(){
//
//		StringBuilder sqlCond = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//		if(valInferior1!=null){
//			sqlCond.append(" AND ")
//			.append(keyColumn1)
//			.append(" >= ")
//			.append(DB.TO_STRING(valInferior1));
//		}
//
//		if(valSuperior1!=null){
//			sqlCond.append(" AND ");
//			sqlCond.append(keyColumn1)
//			.append(" =< ")
//			.append(DB.TO_STRING(valSuperior1));
//		}
//
//		if(valInferior2!=null){
//			sqlCond.append(" AND ");
//			sqlCond.append(keyColumn2)
//			.append(" >= ")
//			.append(DB.TO_STRING(valInferior2));//
//		}
//
//		if(valSuperior2!=null){
//			sqlCond.append(" AND ");
//			sqlCond.append(keyColumn2)
//			.append(" =< ")
//			.append(DB.TO_STRING(valSuperior2));
//		}
//
//		if(valInferior3!=null){
//			sqlCond.append(" AND ");
//			sqlCond.append(keyColumn3)
//			.append(" >= ")
//			.append(valInferior3);//numero
//		}
//
//		if(valSuperior3!=null){
//			sqlCond.append(" AND ");
//			sqlCond.append(keyColumn3)
//			.append(" =< ")
//			.append(valSuperior3);//numero
//		}
//		
//	//	sqlCond.append(" ) ");
//
//		return sqlCond.toString();
//	}
//	
//	/****************************************************************************************/
//	public String getKeyColumn1() {
//		return this.keyColumn1;
//	}
//	public void setKeyColumn1(String keyColumn1) {
//		this.keyColumn1 = keyColumn1;
//	}
//	public String getKeyColumn2() {
//		return this.keyColumn2;
//	}
//	public void setKeyColumn2(String keyColumn2) {
//		this.keyColumn2 = keyColumn2;
//	}
//	public String getKeyColumn3() {
//		return this.keyColumn3;
//	}
//	public void setKeyColumn3(String keyColumn3) {
//		this.keyColumn3 = keyColumn3;
//	}
//	public String getValInferior1() {
//		return this.valInferior1;
//	}
//	public void setValInferior1(String valInferior1) {
//		this.valInferior1 = valInferior1;
//	}
//	public String getValSuperior1() {
//		return this.valSuperior1;
//	}
//	public void setValSuperior1(String valSuperior1) {
//		this.valSuperior1 = valSuperior1;
//	}
//	public String getValInferior2() {
//		return this.valInferior2;
//	}
//	public void setValInferior2(String valInferior2) {
//		this.valInferior2 = valInferior2;
//	}
//	public String getValSuperior2() {
//		return this.valSuperior2;
//	}
//	public void setValSuperior2(String valSuperior2) {
//		this.valSuperior2 = valSuperior2;
//	}
//	public String getValInferior3() {
//		return this.valInferior3;
//	}
//	public void setValInferior3(String valInferior3) {
//		this.valInferior3 = valInferior3;
//	}
//	public String getValSuperior3() {
//		return this.valSuperior3;
//	}
//	public void setValSuperior3(String valSuperior3) {
//		this.valSuperior3 = valSuperior3;
//	}
//	public StringBuilder getSql() {
//		return this.sql;
//	}
//	public void setSql(StringBuilder sql) {
//		this.sql = sql;
//	}
//	public StringBuilder getSqlCount() {
//		return this.sqlCount;
//	}
//	public void setSqlCount(StringBuilder sqlCount) {
//		this.sqlCount = sqlCount;
//	}
//	public int getNoRegistros() {
//		return this.noRegistros;
//	}
//	public void setNoRegistros(int noRegistros) {
//		this.noRegistros = noRegistros;
//	}
//	public int getMaximoReg() {
//		return this.maximoReg;
//	}
//	public void setMaximoReg(int maximoReg) {
//		this.maximoReg = maximoReg;
//	}
//	public int getTotalBlocks() {
//		return this.totalBlocks;
//	}
//	public void setTotalBlocks(int totalBlocks) {
//		this.totalBlocks = totalBlocks;
//	}
//	public List<Bean4String> getIndice() {
//		return this.indice;
//	}
//	public void setIndice(List<Bean4String> indice) {
//		this.indice = indice;
//	}
//	public int getBlocksActual() {
//		return this.blocksActual;
//	}
//	public void setBlocksActual(int blocks) {
//		this.blocksActual = blocks;
//	}
//	public List<Bean4String> getIndiceFin() {
//		return this.indiceFin;
//	}
//	public void setIndiceFin(List<Bean4String> indiceFin) {
//		this.indiceFin = indiceFin;
//	}
}