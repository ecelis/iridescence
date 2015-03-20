/**
 * 
 */
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.I_I_EXME_PresupuestoEgr;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_ActInstitucional;
import org.compiere.model.X_EXME_ClasFuncional;
import org.compiere.model.X_EXME_ClasFuncional_Fun;
import org.compiere.model.X_EXME_ClasFuncional_Sfu;
import org.compiere.model.X_EXME_PartidaPres;
import org.compiere.model.X_EXME_PresupuestoDet;
import org.compiere.model.X_EXME_PresupuestoModif;
import org.compiere.model.X_EXME_ProgInstitucional;
import org.compiere.model.X_EXME_ProgPresupuestal;
import org.compiere.model.X_EXME_Reasignacion;
import org.compiere.model.X_I_EXME_PresupuestoEgr;
import org.compiere.util.DB;

/**
 * Clase de importacion del Presupuesto de egresos I_EXME_PresupuestoEgr con la
 * tabla final EXME_PresupuestoEgr y EXME_PresupuestoDet
 * 
 * Tambien almacena los valores de las tablas EXME_ClasFuncional
 * EXME_ActInstitucional EXME_ProgPresupuestal EXME_ProgInstitucional
 * EXME_ConceptoGasto EXME_TipoGasto EXME_PartidaPres EXME_FteFinanciamiento
 * 
 * @author twry
 * 
 */
public class ImportPresupuestoEgr extends SvrProcess {

	/** Client to be imported to */
	private int mADClientID = 0;
	/** Nombre de Presupuesto */
	private int mPresupuestoEgr = 0;
	/** Nombre de Presupuesto */
	private int mPresupModifID = 0;
	/** Delete old Imported */
	private boolean mDelOldImpor = false;
	/** cliente organizacion */
	protected String clientCheck = null;
	/** Nombre de tabla */
	protected String tableNameImport = null;
	/** Alias de la tabla */
	private final static String ALIAS = " i ";
	/** Constantes */
	private final static String UPDATE = "UPDATE ";
	/** Construccion de consultas */
	private final static String SELECT = " SELECT * FROM ";
	/** Construccion de consulta */
	private final static String WHERE = " WHERE IsActive = 'Y' ";
	/** */
	private final static String SUBCONSULTA = " AND tg.AD_Client_ID IN (0, i.AD_Client_ID) ";
	
	/**
	 * Parametros
	 * 
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			final String name = para[i].getParameterName();

			if ("AD_Client_ID".equals(name)) {
				// Cliente
				mADClientID = ((BigDecimal) para[i].getParameter()).intValue();

			} else if (X_EXME_PresupuestoModif.COLUMNNAME_EXME_PresupuestoEgr_ID
					.equals(name)) {
				// Presupuesto
				mPresupuestoEgr = ((BigDecimal) para[i].getParameter())
						.intValue();
			} else if (X_EXME_PresupuestoModif.COLUMNNAME_EXME_PresupuestoModif_ID
					.equals(name) && para[i].getParameter() != null) {
				// Modificación al presupuesto
				mPresupModifID = ((BigDecimal) para[i].getParameter())
						.intValue();

			} else if ("DeleteOldImported".equals(name)) {
				// Borrar los registros cargados con anterioridad
				mDelOldImpor = "Y".equals(para[i].getParameter());

			} else {
				log.log(Level.SEVERE,
						"ImportPresupuestoEgr.prepare - Unknown Parameter: "
								+ name);
			}
		}

		clientCheck = " AND AD_Client_ID = " + mADClientID;
		tableNameImport = I_I_EXME_PresupuestoEgr.Table_Name;
	}

	/**
	 * Importar presupuesto o la modificación a el
	 * 
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		String result = null;
		if (mPresupModifID == 0) {
			result = importPresupuesto();
		} else {
			result = importPresupuestoModif();
		}
		return result;
	}

	/**
	 * importReasignacion
	 * 
	 * @return
	 * @throws SQLException
	 */
	private String importPresupuestoModif() throws SQLException {
		log.finest("Starting ImportPresupuestoModif ....");
		String msg = null;
		// Old
		if (mDelOldImpor) {
			deleteOldImported();
		}
		// Prepare
		initImport();

		// Actualizar Referencias
		updateTipoGasto();
		updateFteFinanciamiento();
		updateProyecto();

		// Crear Referencias
		createFinalidad();
		DB.commit(true, get_TrxName());

		createFuncion();
		DB.commit(true, get_TrxName());

		createSubfuncion();
		createReasignacion();
		createActInstitucional();
		createProgPresupuestal();
		createProgInstitucional();
		createPartida();

		// actualizacion de columnas
		DB.commit(true, get_TrxName());

		// Insertar las lineas de la reasignacion
		msg = insertPresupuestoModif();
		DB.commit(true, get_TrxName());
		return msg;
	}

	/**
	 * importPresupuesto
	 * 
	 * @return
	 * @throws SQLException
	 */
	private String importPresupuesto() throws SQLException {
		log.finest("Starting ImportPresupuestoEgr ....");
		String msg = null;
		// Old
		if (mDelOldImpor) {
			deleteOldImported();
		}
		// Prepare
		initImport();

		// Actualizar Referencias
		updateTipoGasto();
		updateFteFinanciamiento();
		updateEntidadFederativa();
		updateProyecto();

		// Crear Referencias
		createFinalidad();
		DB.commit(true, get_TrxName());
		createFuncion();
		DB.commit(true, get_TrxName());

		createSubfuncion();
		createReasignacion();
		createActInstitucional();
		createProgPresupuestal();
		createProgInstitucional();
		createPartida();

		// actualizacion de columnas
		DB.commit(true, get_TrxName());

		// Insertar las lineas del presupuesto
		msg = insertPresupuestoEgr();
		DB.commit(true, get_TrxName());
		return msg;
	}

	/**
	 * Insertar las lineas del presupuesto
	 * 
	 * @throws SQLException
	 */
	private String insertPresupuestoModif() throws SQLException {
		String msg = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			// No debe haber error para hacer la importación
			StringBuilder sql = new StringBuilder(SELECT)
					.append(tableNameImport).append(WHERE)
					.append(clientCheck).append(" AND I_IsImported<>'Y'   ")
					.append(" AND I_ErrorMsg IS NOT NULL     ")
					.append(" AND EXME_PresupuestoModif_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, mPresupModifID);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				log.finest("Error en las lineas a importar ");
				DB.close(rset, pstmt);
				msg = "@Error@";
			} else {

				DB.close(rset, pstmt);

				int noInsert = 0;
				int noError = 0;

				sql = new StringBuilder(SELECT)
						.append(tableNameImport)
						.append(WHERE)
						.append(" AND I_IsImported='N'   ").append(clientCheck)
						.append(" AND I_ErrorMsg IS NULL ")
						.append(" AND EXME_PresupuestoModif_ID = ? ");

				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, mPresupModifID);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					final X_I_EXME_PresupuestoEgr iPres = new X_I_EXME_PresupuestoEgr(
							getCtx(), rset, get_TrxName()); // expert

					final X_EXME_PresupuestoDet mPresDet = new X_EXME_PresupuestoDet(
							getCtx(), 0, get_TrxName()); // expert
					PO.copyValues(iPres, mPresDet);
					mPresDet.setName(iPres.getDescription());
					mPresDet.setValue(iPres.getDescription());

					if (mPresDet.save()) {
						log.finest("Insert X_EXME_PresupuestoModif");
						noInsert++;
						iPres.setI_IsImported(true);
						iPres.save();
					} else {
						log.finest("Error X_EXME_PresupuestoModif");
						noError++;
					}

				}

				sql = new StringBuilder(UPDATE)
						.append(tableNameImport)
						.append(ALIAS)
						.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR = Invalid Record ")
						.append(tableNameImport).append(" ' ")
						.append(" WHERE IsActive = 'Y'            ")
						.append(clientCheck)
						.append(" AND   EXME_PartidaPres_ID > 0   ")
						.append(" AND   EXME_PresupuestoModif_ID =  ")
						.append(mPresupModifID)
						.append(" AND   I_IsImported<>'Y'");

				final int num = DB.executeUpdate(sql.toString(), get_TrxName());

				log.finest("Insert " + noInsert + "Error " + noError);
				log.finest(UPDATE + tableNameImport + " = Error " + num);

				if (noError > 0) {
					msg = "@Error@";
				}
			}

		} finally {
			DB.close(rset, pstmt);
		}
		return msg;
	}

	/**
	 * Insertar las lineas del presupuesto
	 * 
	 * @throws SQLException
	 */
	private String insertPresupuestoEgr() throws SQLException {

		String msg = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// No debe haber error para hacer la importacion
			StringBuilder sql = new StringBuilder(SELECT)
					.append(tableNameImport).append(WHERE)
//					.append(clientCheck).append(" AND I_IsImported<>'Y'   ")
//					.append(" AND I_ErrorMsg IS NOT NULL     ")
					.append(clientCheck).append(" AND I_IsImported='E' ")
					.append(" AND I_ErrorMsg IS NOT NULL     ")
					.append(" AND EXME_PresupuestoEgr_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, mPresupuestoEgr);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				log.finest("Error en las lineas a importar ");
				DB.close(rset, pstmt);
				msg = "@Error@";
			} else {

				DB.close(rset, pstmt);

				int noInsert = 0;
				int noError = 0;

				sql = new StringBuilder(SELECT)
						.append(tableNameImport)
						.append(WHERE)
						.append(" AND I_IsImported='N'   ").append(clientCheck)
						.append(" AND I_ErrorMsg IS NULL ")
						.append(" AND EXME_PresupuestoEgr_ID = ? ")
						.append(" ORDER BY Line    ");

				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, mPresupuestoEgr);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					final X_I_EXME_PresupuestoEgr iPres = new X_I_EXME_PresupuestoEgr(
							getCtx(), rset, get_TrxName()); // expert

					final X_EXME_PresupuestoDet mPresDet = new X_EXME_PresupuestoDet(
							getCtx(), 0, get_TrxName()); // expert
					PO.copyValues(iPres, mPresDet);
					mPresDet.setName(iPres.getDescription());
					mPresDet.setValue(iPres.getDescription());

					if (mPresDet.save(get_TrxName())) {
						log.finest("Insert X_EXME_PresupuestoDet");
						noInsert++;
						iPres.setI_IsImported(true);
						iPres.save(get_TrxName());
					} else {
						log.finest("Error X_EXME_PresupuestoDet");
						noError++;
					}

				}

				// Actualizar lo importado
				sql = new StringBuilder(UPDATE)
						.append(tableNameImport)
						.append(ALIAS)
						.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR = Invalid Record EXME_PresupuestoEgr  ' ")
						.append(" WHERE IsActive = 'Y'            ")
						.append(clientCheck)
						.append(" AND   EXME_PresupuestoEgr_ID =  ")
						.append(mPresupuestoEgr)
						.append(" AND   I_IsImported<>'Y'");

				final int num = DB.executeUpdate(sql.toString(), get_TrxName());

				log.finest("Insert " + noInsert + "Error " + noError);
				log.finest("UPDATE I_EXME_PresupuestoEgr = Error " + num);

				if (noError > 0) {
					msg = "@Error@";
				}
			}
		} finally {
			DB.close(rset, pstmt);
		}
		return msg;
	}

	/**
	 * Borrar registros anteriores
	 */
	public void deleteOldImported() {
		// Delete Old Imported
		final StringBuilder sql = new StringBuilder("DELETE ")
				.append(tableNameImport).append(" WHERE I_IsImported='Y'")
				.append(clientCheck);

		final int num = DB.executeUpdate(sql.toString(), null);
		log.finest("Delete Old Impored =" + num);
	}

	/**
	 * Inicializar los campos de la importacion
	 */
	private void initImport() {
		// Set Client, Org, IsActive, Created/Updated
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(" SET AD_Client_ID = COALESCE (AD_Client_ID, ")
				.append(mADClientID).append(") ")
				.append(", AD_Org_ID  = COALESCE (AD_Org_ID, 0)     ")
				.append(", IsActive   = COALESCE (IsActive, 'Y')    ")
				.append(", Created    = COALESCE (Created, SysDate) ")
				.append(", CreatedBy  = COALESCE (CreatedBy, 0)     ")
				.append(", Updated    = COALESCE (Updated, SysDate) ")
				.append(", UpdatedBy  = COALESCE (UpdatedBy, 0)     ")
				.append(", EXME_PresupuestoEgr_ID = ").append(mPresupuestoEgr);
		if (mPresupModifID > 0) {
			sql.append(", EXME_PresupuestoModif_ID = ").append(mPresupModifID);
		}
		sql.append(", I_ErrorMsg = NULL   ").append(", I_IsImported = 'N' ")
				.append(" WHERE I_IsImported<>'Y' OR I_IsImported IS NULL ");

		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Reset=" + num);

		// Actualizar el tipo de unidad
		sql = new StringBuilder(UPDATE).append(tableNameImport)
				.append(ALIAS).append(" SET Tipo = ")
				.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Unidad))
				.append(" WHERE IsActive = 'Y'     ")
				.append(" AND finalidadclv is null ")
				.append(" AND funcionclv is null   ").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update TIPO_Unidad  " + num);
	}

	/**
	 * Crear las Finalidades y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createFinalidad() throws SQLException {
		insertFinalidad("finalidadClv","funcionclv","EXME_ClasFuncional",false);
		insertFinalidad("finalidadClv","funcionclv","EXME_ClasFuncional",true);
//		insertFinalidadSinNombre();

		// Actualizar el tipo finalidad
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport).append(ALIAS).append(" SET Tipo = ")
				.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Purpose))
				.append(" WHERE finalidadclv is not null   ")
				.append(" AND funcionclv is null ").append(clientCheck);
		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update TIPO_Purpose  " + num);

		// Actualizar el ID de finalidad
		sql = new StringBuilder(UPDATE).append(tableNameImport)
				.append(ALIAS).append(" SET EXME_ClasFuncional_ID = (       ")
				.append("     SELECT tg.EXME_ClasFuncional_ID ")
				.append("     FROM EXME_ClasFuncional tg      ")
				.append("     WHERE trim(tg.value)  = trim(i.finalidadclv) ")
				.append(SUBCONSULTA)
				.append("     ) ").append(" WHERE IsActive     =  'Y' ")
				.append(" AND   I_IsImported <> 'Y' ")
				.append(" AND   finalidadclv IS NOT NULL ").append(clientCheck);

		num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update EXME_ClasFuncional_ID  " + num);

		// Actualizar en caso de haber errores
		sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid finalidadclv ' ")
				.append(" WHERE EXME_ClasFuncional_ID IS NULL")
				.append(" AND   finalidadclv IS NOT NULL ")
				.append(" AND   I_IsImported<>'Y'").append(clientCheck);

		num = DB.executeUpdate(sql.toString(), get_TrxName());

		log.finest("Invalid EXME_ClasFuncional_ID =" + num);
	}

//	
//	private void insertFinalidadSinNombre() {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			// sin nombre para el caso de las importaciones de
//			// amplicacion/reduccion
//			final StringBuilder sql = new StringBuilder(
//					" SELECT finalidadClv         ").append(" FROM ")
//					.append(tableNameImport)
//					.append(" WHERE IsActive = 'Y'             ")
//					.append(" AND   finalidadclv is not null   ")
//					.append(clientCheck)
//					.append(" AND   finalidadclv not in (       ")
//					.append("     SELECT value FROM EXME_ClasFuncional ")
//					.append("     WHERE isactive = 'Y' ").append(clientCheck)
//					.append(" )                                ")
//					.append(" GROUP BY finalidadClv ");
//
//			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
//			rset = pstmt.executeQuery();
//
//			int count = 0;
//			int error = 0;
//			while (rset.next()) {
//				final X_EXME_ClasFuncional mBean = new X_EXME_ClasFuncional(
//						getCtx(), 0, get_TrxName());
//				mBean.setValue(rset.getString("finalidadClv"));
//				mBean.setName(rset.getString("finalidadClv"));
//				if (mBean.save(get_TrxName())) {
//					count++;
//				} else {
//					error++;
//				}
//			}
//			log.finest("Insert EXME_ClasFuncional Ok " + count + " Error "
//					+ error);
//		} catch (SQLException e) {
//			log.finest("SQLException insertFinalidadSinNombre ");
//		} finally {
//			DB.close(rset, pstmt);
//		}
//	}

	/** 
	 * Busca y crea la finalidad
	 * @param columnaClave: Columna que contiene el value de la tabla a insertar (finalidadClv)
	 * @param columnaSigui: Columna que indica si es sumarizado (funcionclv)
	 * @param tabla : Tabla a insertar
	 * @param nombrado : Si se busca el nombre o no
	 */
	private void insertFinalidad(final String columnaClave, final String columnaSigui, final String tabla, final boolean nombrado) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			final StringBuilder sql = new StringBuilder(" SELECT ")
			.append(columnaClave);

			if(nombrado){
				sql.append(", Description ");
			}

			sql.append(" FROM ").append(tableNameImport)
			.append(WHERE)
			.append(" AND ").append(columnaClave).append(" is not null ");//finalidadClv
			
			if(nombrado){
				sql.append(" AND ").append(columnaSigui).append(" is null  ");//funcionclv
			}
			
			sql.append(clientCheck)
			.append(" AND ").append(columnaClave).append(" not in ( ")//finalidadClv
			.append("     SELECT value FROM  ").append(tabla)//EXME_ClasFuncional
			.append("     WHERE isactive = 'Y' ").append(clientCheck)
			.append(" )                                ")
			.append(" GROUP BY ").append(columnaClave);//finalidadClv

			if(nombrado){
				sql.append(", Description ");
			}

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();

			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ClasFuncional mBean = new X_EXME_ClasFuncional(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("finalidadClv"));
				mBean.setName(nombrado?rset.getString("Description"):mBean.getValue());
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_ClasFuncional Ok " + count + " Error "
					+ error);
		} catch (SQLException e) {
			log.finest("SQLException insertFinalidad ");
		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Crear las funciones y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createFuncion() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT funcionclv, Description, EXME_ClasFuncional_ID ")
					.append(" FROM ")
					.append(tableNameImport)
					.append(" WHERE  isactive = 'Y' ")
					.append(clientCheck)
					.append(" AND    funcionclv IS NOT NULL  ")
					.append(" AND    subfuncionclv IS NULL   ")
					.append(" AND    EXME_ClasFuncional_ID>0 ")
					.append(" AND    funcionclv NOT IN (     ")
					.append("     SELECT fun.value FROM EXME_ClasFuncional_Fun fun ")
					.append("     WHERE  fun.isactive = 'Y' ")
					.append("     AND    fun.AD_Client_ID = ")
					.append(mADClientID)
					.append("     AND    fun.EXME_ClasFuncional_ID = ")
					.append(tableNameImport)
					.append(".EXME_ClasFuncional_ID ")
					.append("         )                      ")
					.append(" GROUP BY funcionclv, Description, EXME_ClasFuncional_ID ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ClasFuncional_Fun mBean = new X_EXME_ClasFuncional_Fun(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("funcionclv"));
				mBean.setName(rset.getString("Description"));
				mBean.setEXME_ClasFuncional_ID(rset
						.getInt("EXME_ClasFuncional_ID"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_ClasFuncional_Fun Ok " + count + " Error "
					+ error);
			DB.close(rset, pstmt);

			// Obtener valor sin nombre nuevos, para el caso de los ajustes
			sql = new StringBuilder(
					" SELECT funcionclv, EXME_ClasFuncional_ID ")
					.append(" FROM ")
					.append(tableNameImport)
					.append(" WHERE  isactive = 'Y' ")
					.append(clientCheck)
					.append(" AND    funcionclv IS NOT NULL  ")
					.append(" AND    EXME_ClasFuncional_ID>0 ")
					.append(" AND    funcionclv NOT IN (     ")
					.append("     SELECT fun.value FROM EXME_ClasFuncional_Fun fun ")
					.append("     WHERE  fun.isactive = 'Y' ")
					.append("     AND    fun.AD_Client_ID = ")
					.append(mADClientID)
					.append("     AND    fun.EXME_ClasFuncional_ID = ")
					.append(tableNameImport).append(".EXME_ClasFuncional_ID ")
					.append("         )                      ")
					.append(" GROUP BY funcionclv, EXME_ClasFuncional_ID ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();

			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_ClasFuncional_Fun mBean = new X_EXME_ClasFuncional_Fun(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("funcionclv"));
				mBean.setName(rset.getString("funcionclv"));
				mBean.setEXME_ClasFuncional_ID(rset
						.getInt("EXME_ClasFuncional_ID"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_ClasFuncional_Fun Ok " + count + " Error "
					+ error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE).append(tableNameImport)
					.append(ALIAS).append(" SET Tipo = ")
					.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Function))
					.append(" WHERE  isactive = 'Y' ").append(clientCheck)
					.append(" AND    funcionclv IS NOT NULL  ")
					.append(" AND    subfuncionclv IS NULL   ");
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Function  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_ClasFuncional_Fun_ID = (       ")
					.append("     SELECT tg.EXME_ClasFuncional_Fun_ID ")
					.append("     FROM EXME_ClasFuncional_Fun tg      ")
					.append("     WHERE trim(tg.value)  = trim(i.funcionclv) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y' ")
					.append(" AND   funcionclv IS NOT NULL ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ClasFuncional_Fun_ID  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid funcionclv ' ")
					.append(" WHERE EXME_ClasFuncional_Fun_ID IS NULL")
					.append(" AND   funcionclv IS NOT NULL ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_ClasFuncional_Fun_ID =" + num);
		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Crear las subfunciones y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createSubfuncion() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {

			// Obtener valor y nombre nuevos
			StringBuilder sql = new StringBuilder(
					" SELECT  subfuncionclv, Description,EXME_ClasFuncional_Fun_ID ")
					.append(" FROM ")
					.append(tableNameImport)
					.append(WHERE)
					.append(" AND subfuncionclv is not null    ")
					.append(" AND reasignacionvalue is null  ")
					.append(" AND actinsvalue is null  ")
					.append(clientCheck)
					.append(" AND EXME_ClasFuncional_Fun_ID > 0  ")
					.append(" AND subfuncionclv not in (         ")
					.append("     SELECT sfu.value FROM EXME_ClasFuncional_Sfu sfu ")
					.append("     WHERE sfu.isactive = 'Y' ")
					.append("     AND   sfu.AD_Client_ID = ")
					.append(mADClientID)
					.append("     AND   sfu.EXME_ClasFuncional_Fun_ID = ")
					.append(tableNameImport)
					.append(".EXME_ClasFuncional_Fun_ID ")
					.append(" ) ")
					.append(" GROUP BY subfuncionclv, Description, EXME_ClasFuncional_Fun_ID ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();

			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ClasFuncional_Sfu mBean = new X_EXME_ClasFuncional_Sfu(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("subfuncionclv"));
				mBean.setName(rset.getString("Description"));
				mBean.setEXME_ClasFuncional_Fun_ID(rset
						.getInt("EXME_ClasFuncional_Fun_ID"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_ClasFuncional_Sfu Ok " + count + " Error "
					+ error);
			DB.close(rset, pstmt);

			// Obtener valor sin nombre nuevos, para el caso de los ajustes
			sql = new StringBuilder(
					" SELECT SubfuncionClv, EXME_ClasFuncional_Fun_ID ")
					.append(" FROM ")
					.append(tableNameImport)
					.append(WHERE)
					.append(" AND subfuncionClv IS NOT NULL     ")
					.append(" AND EXME_ClasFuncional_Fun_ID > 0 ")
					.append(" AND Subfuncionclv NOT IN (        ")
					.append("     SELECT sfu.Value FROM EXME_ClasFuncional_Sfu sfu ")
					.append("     WHERE  sfu.IsActive = 'Y' ")
					.append("     AND    sfu.AD_Client_ID = ")
					.append(mADClientID)
					.append("     AND    sfu.EXME_ClasFuncional_Fun_ID = ")
					.append(tableNameImport)
					.append(".EXME_ClasFuncional_Fun_ID ")
					.append(" ) ")
					.append(" GROUP BY SubfuncionClv, EXME_ClasFuncional_Fun_ID ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();

			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_ClasFuncional_Sfu mBean = new X_EXME_ClasFuncional_Sfu(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("subfuncionclv"));
				mBean.setName(rset.getString("subfuncionclv"));
				mBean.setEXME_ClasFuncional_Fun_ID(rset
						.getInt("EXME_ClasFuncional_Fun_ID"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_ClasFuncional_Sfu Ok " + count + " Error "
					+ error);
			DB.close(rset, pstmt);

			// Actualizar el tipo de linea
			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET Tipo = ")
					.append(DB
							.TO_STRING(X_EXME_PresupuestoDet.TIPO_Subfunction))
					.append(" where subfuncionclv is not null    ")
					.append(" and reasignacionvalue is null  ")
					.append(" and actinsvalue is null  ").append(clientCheck);
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Subfunction  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_ClasFuncional_Sfu_ID = (     ")
					.append("     SELECT tg.EXME_ClasFuncional_Sfu_ID   ")
					.append("     FROM EXME_ClasFuncional_Sfu tg        ")
					.append("     WHERE trim(tg.value)  = trim(i.subfuncionclv) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y'             ")
					.append(" AND   subfuncionclv IS NOT NULL       ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ClasFuncional_Sfu_ID  " + num);

			sql = new StringBuilder("UPDATE I_EXME_PresupuestoEgr i   ")
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid subfuncionclv ' ")
					.append(" WHERE EXME_ClasFuncional_Sfu_ID IS NULL ")
					.append(" AND   subfuncionclv IS NOT NULL         ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_ClasFuncional_Sfu_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Actualizar la refencia ID de la tabla de importacion para que
	 * I_EXME_PresupuestoEgr.EXME_Reasignacion_ID no este vacio NO OBLIGATORIO
	 */
	public void createReasignacion() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT  ReasignacionValue, Description         ")
					.append(" FROM ").append(tableNameImport)
					.append(" where ReasignacionValue is not null    ")
					.append(" and   actinsvalue       is null  ")
					.append(clientCheck)
					.append(" AND   ReasignacionValue not in (         ")
					.append("     select value from EXME_Reasignacion ")
					.append("     where isactive = 'Y' ").append(clientCheck)
					.append(" ) ")
					.append(" GROUP BY ReasignacionValue, Description ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_Reasignacion mBean = new X_EXME_Reasignacion(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("ReasignacionValue"));
				mBean.setName(rset.getString("Description"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_Reasignacion Ok " + count + " Error "
					+ error);

			//
			sql = new StringBuilder(" SELECT  ReasignacionValue         ")
					.append(" FROM ").append(tableNameImport)
					.append(WHERE)
					.append(" AND   ReasignacionValue is not null    ")
					.append(clientCheck)
					.append(" AND   ReasignacionValue not in (         ")
					.append("     select value from EXME_Reasignacion ")
					.append("     where isactive = 'Y' ").append(clientCheck)
					.append(" ) ").append(" GROUP BY ReasignacionValue ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_Reasignacion mBean = new X_EXME_Reasignacion(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("ReasignacionValue"));
				mBean.setName(rset.getString("ReasignacionValue"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert EXME_Reasignacion Ok " + count + " Error "
					+ error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET Tipo = ")
					.append(DB
							.TO_STRING(X_EXME_PresupuestoDet.TIPO_Reassignment))
					.append(" where ReasignacionValue is not null    ")
					.append(" and   actinsvalue       is null  ")
					.append(clientCheck);
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Reassignment  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_Reasignacion_ID = (     ")
					.append("     SELECT tg.EXME_Reasignacion_ID   ")
					.append("     FROM   EXME_Reasignacion tg        ")
					.append("     WHERE  trim(tg.value)  = trim(i.ReasignacionValue) ")
					.append("     AND    tg.AD_Client_ID IN (0, i.AD_Client_ID)   ")
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y'             ")
					.append(" AND   ReasignacionValue IS NOT NULL       ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ClasFuncional_Sfu_ID  " + num);

			sql = new StringBuilder("UPDATE I_EXME_PresupuestoEgr i   ")
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid ReasignacionValue ' ")
					.append(" WHERE EXME_Reasignacion_ID IS NULL ")
					.append(" AND   ReasignacionValue IS NOT NULL         ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_Reasignacion_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Crear la actividad institucional y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createActInstitucional() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT actinsvalue, Description         ")
					.append(" FROM ").append(tableNameImport)
					.append(" WHERE  actinsvalue IS NOT NULL  ")
					.append(" AND    progpresvalue IS NULL    ")
					.append(clientCheck)
					.append(" AND    actinsvalue NOT IN (     ")
					.append("     SELECT value FROM EXME_ActInstitucional ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ")
					.append(" GROUP BY actinsvalue, Description ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ActInstitucional mBean = new X_EXME_ActInstitucional(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("actinsvalue"));
				mBean.setName(rset.getString("Description"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ActInstitucional.Table_Name + " Ok "
					+ count + " Error " + error);

			//
			sql = new StringBuilder(" SELECT actinsvalue         ")
					.append(" FROM ").append(tableNameImport)
					.append(" WHERE  actinsvalue IS NOT NULL  ")
					.append(" AND    IsActive = 'Y'           ")
					.append(clientCheck)
					.append(" AND    actinsvalue NOT IN (     ")
					.append("     SELECT value FROM EXME_ActInstitucional ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ").append(" GROUP BY actinsvalue ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_ActInstitucional mBean = new X_EXME_ActInstitucional(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("actinsvalue"));
				mBean.setName(rset.getString("actinsvalue"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ActInstitucional.Table_Name + " Ok "
					+ count + " Error " + error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE).append(tableNameImport)
					.append(ALIAS).append(" SET Tipo = ")
					.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_Contracts))
					.append(" WHERE  actinsvalue IS NOT NULL  ")
					.append(" AND    progpresvalue IS NULL    ")
					.append(clientCheck);
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Contracts  " + num);

			//
			sql = new StringBuilder("UPDATE I_EXME_PresupuestoEgr i  ")
					.append(" SET EXME_ActInstitucional_ID = (       ")
					.append("     SELECT tg.EXME_ActInstitucional_ID ")
					.append("     FROM EXME_ActInstitucional tg      ")
					.append("     WHERE trim(tg.value)  = trim(i.actinsvalue) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y'              ")
					.append(" AND   actinsvalue IS NOT NULL ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ActInstitucional_ID  " + num);

			sql = new StringBuilder("UPDATE I_EXME_PresupuestoEgr i  ")
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid actinsvalue ' ")
					.append(" WHERE EXME_ActInstitucional_ID IS NULL ")
					.append(" AND   actinsvalue IS NOT NULL          ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_ActInstitucional_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Crear el programa presupuestal y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createProgPresupuestal() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT progpresvalue, Description         ")
					.append(" FROM ").append(tableNameImport)
					.append(" WHERE isactive = 'Y'              ")
					.append(" AND progpresvalue is not null     ")
					.append(" AND proginsvalue is null ").append(clientCheck)
					.append(" AND progpresvalue not in (        ")
					.append("     SELECT value FROM EXME_ProgPresupuestal ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ")
					.append(" GROUP BY progpresvalue, Description ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ProgPresupuestal mBean = new X_EXME_ProgPresupuestal(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("progpresvalue"));
				mBean.setName(rset.getString("Description"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ProgPresupuestal.Table_Name + " Ok "
					+ count + " Error " + error);

			//
			sql = new StringBuilder(" SELECT progpresvalue         ")
					.append(" FROM ").append(tableNameImport)
					.append(" WHERE isactive = 'Y'              ")
					.append(" AND progpresvalue is not null     ")
					.append(clientCheck)
					.append(" AND progpresvalue not in (        ")
					.append("     SELECT value FROM EXME_ProgPresupuestal ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ").append(" GROUP BY progpresvalue ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_ProgPresupuestal mBean = new X_EXME_ProgPresupuestal(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("progpresvalue"));
				mBean.setName(rset.getString("progpresvalue"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ProgPresupuestal.Table_Name + " Ok "
					+ count + " Error " + error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET Tipo = ")
					.append(DB
							.TO_STRING(X_EXME_PresupuestoDet.TIPO_Prosupuestal))
					.append(" WHERE isactive = 'Y'              ")
					.append(" AND progpresvalue is not null     ")
					.append(" AND proginsvalue is null ").append(clientCheck);
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Prosupuestal  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_ProgPresupuestal_ID = (       ")
					.append("     SELECT tg.EXME_ProgPresupuestal_ID ")
					.append("     FROM EXME_ProgPresupuestal tg      ")
					.append("     WHERE trim(tg.value)  = trim(i.progpresvalue) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y'              ")
					.append(" AND   progpresvalue IS NOT NULL        ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ProgPresupuestal_ID  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid progpresvalue ' ")
					.append(" WHERE EXME_ProgPresupuestal_ID IS NULL")
					.append(" AND   progpresvalue IS NOT NULL ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_ProgPresupuestal_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Crear el programa institucional y actualizar la tabla de importación
	 * 
	 * @throws SQLException
	 */
	public void createProgInstitucional() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT Proginsvalue, Description ").append(" FROM ")
					.append(tableNameImport)
					.append(" WHERE IsActive = 'Y'         ")
					.append(clientCheck)
					.append(" AND Proginsvalue is not null ")
					.append(" AND Partvalue is null        ")
					.append(" AND Proginsvalue not in (    ")
					.append("     SELECT value FROM EXME_ProgInstitucional ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ")
					.append(" GROUP BY Proginsvalue, Description ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_ProgInstitucional mBean = new X_EXME_ProgInstitucional(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("proginsvalue"));
				mBean.setName(rset.getString("Description"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ProgInstitucional.Table_Name + " Ok "
					+ count + " Error " + error);

			//
			sql = new StringBuilder(" SELECT Proginsvalue ").append(" FROM ")
					.append(tableNameImport)
					.append(" WHERE IsActive = 'Y'         ")
					.append(clientCheck)
					.append(" AND Proginsvalue is not null ")
					.append(" AND Proginsvalue not in (    ")
					.append("     SELECT value FROM EXME_ProgInstitucional ")
					.append("     WHERE isactive = 'Y' ").append(clientCheck)
					.append(" ) ").append(" GROUP BY Proginsvalue ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();
			count = 0;
			error = 0;
			while (rset.next()) {
				final X_EXME_ProgInstitucional mBean = new X_EXME_ProgInstitucional(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("proginsvalue"));
				mBean.setName(rset.getString("proginsvalue"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_ProgInstitucional.Table_Name + " Ok "
					+ count + " Error " + error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET Tipo = ")
					.append(DB
							.TO_STRING(X_EXME_PresupuestoDet.TIPO_Institutional))
					.append(" WHERE IsActive = 'Y'         ")
					.append(clientCheck)
					.append(" AND Proginsvalue is not null ")
					.append(" AND Partvalue is null        ");
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_Institutional  " + num);

			// Actualizar
			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_ProgInstitucional_ID = (       ")
					.append("     SELECT tg.EXME_ProgInstitucional_ID ")
					.append("     FROM EXME_ProgInstitucional tg      ")
					.append("     WHERE trim(tg.value)  = trim(i.proginsvalue) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(clientCheck).append(" AND   I_IsImported <> 'Y' ")
					.append(" AND   proginsvalue IS NOT NULL ");

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_ProgInstitucional_ID  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid proginsvalue ' ")
					.append(" WHERE EXME_ProgInstitucional_ID IS NULL  ")
					.append(" AND   proginsvalue IS NOT NULL           ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_ProgInstitucional_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Debe de existir el concepto y el tipo de gasto
	 * 
	 * @throws SQLException
	 */
	public void createPartida() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			//
			StringBuilder sql = new StringBuilder(
					" SELECT partvalue, Description, EXME_TipoGasto_ID ")
					.append(" FROM  ")
					.append(tableNameImport)
					.append(" WHERE PartValue IS NOT NULL  ")
					.append(clientCheck)
					.append(" AND   PartValue NOT IN (     ")
					.append("         SELECT value FROM EXME_PartidaPres ")
					.append("         WHERE isActive = 'Y' ")
					.append(clientCheck)
					.append("       ) ")
					.append(" AND   EXME_TipoGasto_ID > 0  ")
					.append(" GROUP BY partvalue, Description, EXME_TipoGasto_ID ");

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rset = pstmt.executeQuery();

			int count = 0;
			int error = 0;
			while (rset.next()) {
				final X_EXME_PartidaPres mBean = new X_EXME_PartidaPres(
						getCtx(), 0, get_TrxName());
				mBean.setValue(rset.getString("partvalue"));
				mBean.setName(rset.getString("Description"));
				mBean.setEXME_TipoGasto_ID(rset.getInt("EXME_TipoGasto_ID"));
				// mBean.setEXME_ConceptoGasto_ID(rs.getInt("EXME_ConceptoGasto_ID"));
				if (mBean.save(get_TrxName())) {
					count++;
				} else {
					error++;
				}
			}
			log.finest("Insert " + X_EXME_PartidaPres.Table_Name + " Ok "
					+ count + " Error " + error);

			// Actualizar el tipo
			sql = new StringBuilder(UPDATE).append(tableNameImport)
					.append(ALIAS).append(" SET Tipo = ")
					.append(DB.TO_STRING(X_EXME_PresupuestoDet.TIPO_PTDA))
					.append(" WHERE PartValue IS NOT NULL  ")
					.append(clientCheck);
			int num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update TIPO_PTDA " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET EXME_PartidaPres_ID = (       ")
					.append("     SELECT tg.EXME_PartidaPres_ID ")
					.append("     FROM EXME_PartidaPres tg      ")
					.append("     WHERE trim(tg.value)  = trim(i.partvalue) ")
					.append(SUBCONSULTA)
					.append("     ) ").append(" WHERE IsActive     =  'Y' ")
					.append(" AND   I_IsImported <> 'Y' ")
					.append(" AND   partvalue IS NOT NULL ")
					.append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());
			log.finest("Update EXME_PartidaPres_ID  " + num);

			sql = new StringBuilder(UPDATE)
					.append(tableNameImport)
					.append(ALIAS)
					.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid partvalue ' ")
					.append(" WHERE EXME_PartidaPres_ID IS NULL")
					.append(" AND   partvalue IS NOT NULL ")
					.append(" AND I_IsImported<>'Y'").append(clientCheck);

			num = DB.executeUpdate(sql.toString(), get_TrxName());

			log.finest("Invalid EXME_PartidaPres_ID =" + num);

		} finally {
			DB.close(rset, pstmt);
		}
	}

	/**
	 * Actualizar la refencia ID de la tabla de importacion para que
	 * I_EXME_PresupuestoEgr.EXME_TipoGasto_ID no este vacio
	 */
	public void updateTipoGasto() {

		// -- Tipo de gasto
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport).append(ALIAS)
				.append(" SET EXME_TipoGasto_ID = (       ")
				.append("     SELECT tg.EXME_TipoGasto_ID ")
				.append("     FROM EXME_TipoGasto tg      ")
				.append("     WHERE trim(tg.value)  = trim(i.TipoGastoValue) ")
				.append("     AND   tg.IsActive    =  'Y' ")
				.append(SUBCONSULTA)
				.append("     ) ").append(" WHERE IsActive     =  'Y' ")
				.append(" AND   I_IsImported <> 'Y' ")
				.append(" AND   TipoGastoValue IS NOT NULL ")
				.append(clientCheck);

		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update EXME_TipoGasto_ID =" + num);

		sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid EXME_TipoGasto_ID' ")
				.append(" WHERE EXME_TipoGasto_ID IS NULL")
				.append(" AND TipoGastoValue IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);
		num = DB.executeUpdate(sql.toString(), get_TrxName());

		log.finest("Invalid EXME_TipoGasto_ID=" + num);
	}

	/**
	 * Actualizar la refencia ID de la tabla de importacion para que
	 * I_EXME_PresupuestoEgr.EXME_FteFinanciamiento_ID no este vacio
	 */
	public void updateFteFinanciamiento() {
		// -- Fuente de financiamiento
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET EXME_FteFinanciamiento_ID = (       ")
				.append("     SELECT tg.EXME_FteFinanciamiento_ID ")
				.append("     FROM   EXME_FteFinanciamiento tg    ")
				.append("     WHERE  trim(tg.value) = trim(i.FteFinvalue) ")
				.append("     AND    tg.IsActive    =  'Y' ")
				.append("     AND    tg.AD_Client_ID IN (0, i.AD_Client_ID)   ")
				.append("     ) ").append(" WHERE IsActive     =  'Y' ")
				.append(" AND   I_IsImported <> 'Y' ")
				.append(" AND   FteFinvalue IS NOT NULL ").append(clientCheck);

		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update EXME_FteFinanciamiento_ID =" + num);

		sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid EXME_FteFinanciamiento_ID' ")
				.append(" WHERE EXME_FteFinanciamiento_ID IS NULL")
				.append(" AND FteFinvalue IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		num = DB.executeUpdate(sql.toString(), get_TrxName());

		log.finest("Invalid EXME_FteFinanciamiento_ID=" + num);
	}

	/**
	 * Actualizar la refencia ID de la tabla de importacion para que
	 * I_EXME_PresupuestoEgr.C_Region_ID no este vacio
	 */
	public void updateEntidadFederativa() {

		// -- Entidad federativa
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET C_Region_ID = (       ")
				.append("     SELECT tg.C_Region_ID ")
				.append("     FROM   C_Region tg    ")
				.append("     WHERE  trim(tg.Entidad) = trim(i.Entidad) ")
				.append("     AND    tg.IsActive    =  'Y' ")
				.append("     AND    tg.AD_Client_ID IN (0, i.AD_Client_ID)   ")
				.append("     ) ").append(" WHERE IsActive     =  'Y' ")
				.append(" AND   I_IsImported <> 'Y' ")
				.append(" AND   Entidad IS NOT NULL ").append(clientCheck);

		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update C_Region_ID =" + num);

		sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid Entidad' ")
				.append(" WHERE C_Region_ID IS NULL")
				.append(" AND Entidad IS NOT NULL")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		num = DB.executeUpdate(sql.toString(), get_TrxName());

		log.finest("Invalid C_Region_ID=" + num);

	}

	/**
	 * Actualizar la refencia ID de la tabla de importacion para que
	 * I_EXME_PresupuestoEgr.C_Project_ID no este vacio NO OBLIGATORIO
	 */
	public void updateProyecto() {

		// -- Tipo de gasto
		StringBuilder sql = new StringBuilder(UPDATE)
				.append(tableNameImport).append(ALIAS)
				.append(" SET C_Project_ID = (       ")
				.append("     SELECT tg.C_Project_ID ")
				.append("     FROM C_Project tg      ")
				.append("     WHERE trim(tg.value)  = trim(i.Projectvalue) ")
				.append(SUBCONSULTA)
				.append("     ) ").append(" WHERE IsActive     =  'Y' ")
				.append(" AND   I_IsImported <> 'Y' ")
				.append(" AND   Projectvalue <> '0' ")
				.append(" AND   Projectvalue IS NOT NULL ").append(clientCheck);

		int num = DB.executeUpdate(sql.toString(), get_TrxName());
		log.finest("Update C_Project_ID  " + num);

		sql = new StringBuilder(UPDATE)
				.append(tableNameImport)
				.append(ALIAS)
				.append(" SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg || 'ERR=Invalid Projectvalue ' ")
				.append(" WHERE C_Project_ID IS NULL")
				.append(" AND   Projectvalue IS NOT NULL ")
				.append(" AND   Projectvalue <> '0' ")
				.append(" AND I_IsImported<>'Y'").append(clientCheck);

		num = DB.executeUpdate(sql.toString(), get_TrxName());

		log.finest("Invalid C_Project_ID =" + num);
	}

}
