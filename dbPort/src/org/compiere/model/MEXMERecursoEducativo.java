package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.ConsultingWs;
import org.compiere.util.DB;
import org.compiere.util.ReadXML;

/**
 * Recursos educativos
 * 
 * @author odelarosa
 *         http://apps2.nlm.nih.gov/medlineplus/services/demo.html<br>
 *         Modificado por: Lorena Lama (revision de codigo y comentarios) Nov 2012
 */
public class MEXMERecursoEducativo extends X_EXME_RecursoEducativo implements Comparable<MEXMERecursoEducativo> {

	private static final long	serialVersionUID	= 9219130675791399155L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMERecursoEducativo.class);

	/**
	 * @param ctx
	 * @param EXME_RecursoEducativo_ID
	 * @param trxName
	 */
	public MEXMERecursoEducativo(final Properties ctx, final int EXME_RecursoEducativo_ID, final String trxName) {
		super(ctx, EXME_RecursoEducativo_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERecursoEducativo(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene todos los recursos educativos activos.
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativo> get(final Properties ctx, final String trxName) {
		List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		try {
			lista = new Query(ctx, Table_Name, "", trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Created ")//
				.list();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.toString(), e);
		}
		return lista;
	}
	
//	Lama, Nov 2012 - Se rehace metodo con la misma firma
//	Se comenta codigo debido :
//		** se crean metodon con las lineas de codigo repetidas para medicamentos/diagnosticos/loinc
//		** no es necesario accesar nuevamente a la base de datos, 
//	       ya que los metodos que mandan lo mandaban llamar tienen como parametro 
//	       la lista de MEstudiosOBX donde la columna CODEID contiene el codigo Loinc
//		** creacion innecesaria de objetos al inicio del metodo, los cuales son vueltos a instanciar dentro del while
//	/**
//	 * Consulta WS
//	 * 
//	 */
//	public static List<MEXMERecursoEducativo> getWS(Properties ctx, Collection<Integer> productIds, List<Integer> diagnosticIds, String trxName) {
//		List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		StringBuilder sql;
//		// Iterator iter;
//		TreeMap<String, String> parametres = new TreeMap<String, String>();
//		TreeMap<String, TreeMap<String, String>> childElements = new TreeMap<String, TreeMap<String, String>>();
//		TreeMap<String, String> attribute = new TreeMap<String, String>();
//		ReadXML readXml;
//		ConsultingWs consultaWs = new ConsultingWs("cfm");
//		consultaWs.setParameters(parametres);
//		String parentElement = "entry";
//		childElements.put("title", null);
//		attribute = new TreeMap<String, String>();
//		attribute.put("href", null);
//		childElements.put("link", attribute);
//		childElements.put("summary", null);
//		parametres.put("mainSearchCriteria.v.dn", "");
//		parametres.put("informationRecipient.languageCode.c", "en");
//		if (productIds == null) {
//			productIds = new ArrayList<Integer>();
//		}
//		if (diagnosticIds == null) {
//			diagnosticIds = new ArrayList<Integer>();
//		}
//		if (productIds.size() > 0 || diagnosticIds.size() > 0) {
//			sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			if (diagnosticIds.size() > 0) {
//				parametres.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.103");
//				s_log.log(Level.FINE, "Disgnostic: " + parametres.get("mainSearchCriteria.v.c"));
//				final List<ProblemListVO> problems = new ArrayList<ProblemListVO>();
//				ProblemListVO problem;
//				sql.append(" SELECT * ");
//				sql.append("  FROM EXME_DIAGNOSTICO ");
//				sql.append(" WHERE ISACTIVE = 'Y' ");
//				sql.append("   AND EXME_DIAGNOSTICO_ID IN (");
//				sql.append(StringUtils.join(diagnosticIds, ","));
//				sql.append("  ) ");
//				try {
//					pstmt = DB.prepareStatement(sql.toString(), trxName);
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						problem = new ProblemListVO();
//						problem.setDescription(rs.getString("Name"));
//						problem.setCode(rs.getString("Value"));
//						problems.add(problem);
//					}
//				} catch (Exception e) {
//					s_log.log(Level.SEVERE, sql.toString(), e);
//				} finally {
//					DB.close(rs, pstmt);
//				}
//				Iterator<?> iter = problems.iterator();
//				while (iter.hasNext()) {
//					problem = (ProblemListVO) iter.next();
//					childElements = new TreeMap<String, TreeMap<String, String>>();
//					childElements.put("title", null);
//					attribute = new TreeMap<String, String>();
//					attribute.put("href", null);
//					childElements.put("link", attribute);
//					childElements.put("summary", null);
//					parametres.put("mainSearchCriteria.v.c", problem.getCode()); // ICD-9-CM
//					s_log.log(Level.FINE, "Medication: " + parametres.get("mainSearchCriteria.v.c"));
//					readXml = new ReadXML(parentElement);
//					if (consultaWs.request() != null) {
//						readXml.getData(consultaWs.request(), childElements);
//						MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, 0, trxName);
//						re.setDescription(problem.getDescription());
//						if (childElements.get("link").get("href") != null && !childElements.get("link").get("href").equals(StringUtils.EMPTY)) {
//							re.setURL(childElements.get("link").get("href").toString());
//							re.setTipoRecurso(MEXMERecursoEducativo.TIPORECURSO_URL);
//						}
//						lista.add(re);
//					}
//					s_log.log(Level.FINE, "URL: " + consultaWs.getUrl());
//				}
//			}
//			if (productIds.size() > 0) {
//				parametres.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.69");
//				final List<MEXMEProduct> products = new ArrayList<MEXMEProduct>();
//				MEXMEProduct product;
//				sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//				sql.append(" SELECT * ");
//				sql.append("   FROM M_PRODUCT  ");
//				sql.append("  WHERE M_PRODUCT_ID IN (");
//				sql.append(StringUtils.join(productIds, ","));
//				sql.append(" ) ");
//				try {
//					pstmt = DB.prepareStatement(sql.toString(), trxName);
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						product = new MEXMEProduct(ctx, rs, trxName);
//						products.add(product);
//					}
//				} catch (Exception e) {
//					s_log.log(Level.SEVERE, sql.toString(), e);
//				} finally {
//					DB.close(rs, pstmt);
//				}
//				Iterator<?> iter = products.iterator();
//				while (iter.hasNext()) {
//					product = (MEXMEProduct) iter.next();
//					childElements = new TreeMap<String, TreeMap<String, String>>();
//					childElements.put("title", null);
//					attribute = new TreeMap<String, String>();
//					attribute.put("href", null);
//					childElements.put("link", attribute);
//					childElements.put("summary", null);
//					parametres.put("mainSearchCriteria.v.c", product.getValue()); // NDC
//					readXml = new ReadXML(parentElement);
//					readXml.getData(consultaWs.request(), childElements);
//					MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, 0, trxName);
//					re.setDescription(product.getName());
//					if (childElements.get("link").get("href") != null && !childElements.get("link").get("href").equals(StringUtils.EMPTY)) {
//						re.setURL(childElements.get("link").get("href").toString());
//						re.setTipoRecurso(MEXMERecursoEducativo.TIPORECURSO_URL);
//					}
//					lista.add(re);
//				}
//			}
//		}
//		return lista;
//	}

	/**
	 * Obtiene los recursos educativos desde medlineplus relacionados a medicamentos y diagnosticos
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param productIds Ids de NDCs a buscar el recurso asociado
	 * @param diagnosticIds Ids de ICD-9 a buscar el recurso asociado
	 * @param trxName nombre de la transaccion
	 * @return Una lista de recursos educativos (con ID=0, de tipo URL)
	 * @author lama
	 */
	public static List<MEXMERecursoEducativo> getWS(final Properties ctx, Collection<Integer> productIds, List<Integer> diagnosticIds,
		final String trxName) {
		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		// retorna una lista vacia si no hay datos para buscar
		if ((productIds == null || productIds.isEmpty()) && (diagnosticIds == null || diagnosticIds.isEmpty())) {
			return lista;
		}
		final TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("mainSearchCriteria.v.dn", "");
		parameters.put("informationRecipient.languageCode.c", "en");

		final ConsultingWs consultaWs = new ConsultingWs("cfm");
		consultaWs.setParameters(parameters);

		// busca los recursos educativos de los diagnosticos
		if (diagnosticIds != null && !diagnosticIds.isEmpty()) {
			parameters.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.103");
			s_log.log(Level.FINE, "Diagnostic Critera: " + parameters.get("mainSearchCriteria.v.cs"));
			for (final MDiagnostico diag : getDiagnosis(ctx, diagnosticIds, trxName)) {
				lista.add(getWS(diag, parameters, consultaWs));
			}
		}
		// busca los recursos educativos de los productos
		if (productIds != null && !productIds.isEmpty()) {
			parameters.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.69");
			s_log.log(Level.FINE, "Medication Critera: " + parameters.get("mainSearchCriteria.v.cs"));
			for (final MProduct product : getProducts(ctx, productIds, trxName)) {
				lista.add(getWS(product, parameters, consultaWs));
			}
		}
		return lista;
	}

	/**
	 * Obtiene los recursos educativos del sistema relacionados a los productos y
	 * diagnosticos
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param genProdIds Listado con Ids de Productos genericos
	 * @param productIds Listado con Ids de Productos
	 * @param diagnosticIds Listado con Ids de Diagnosticos
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMERecursoEducativo> get(final Properties ctx, List<Integer> genProdIds, List<Integer> productIds,
		List<Integer> diagnosticIds, final String trxName) {
		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();

		if (productIds == null) {
			productIds = new ArrayList<Integer>();
		}

		if (diagnosticIds == null) {
			diagnosticIds = new ArrayList<Integer>();
		}

		if (genProdIds == null) {
			genProdIds = new ArrayList<Integer>();
		}

		if (productIds.size() > 0 || diagnosticIds.size() > 0 || genProdIds.size() > 0) {

			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT r.* FROM exme_recursoeducativo r ");
			sql.append("WHERE  r.isActive='Y' ");
			sql.append("AND    r.exme_recursoeducativo_id IN (");
			// diagnosis
			if (diagnosticIds.size() > 0) {
				sql.append("  SELECT exme_recursoeducativo_id ");
				sql.append("  FROM   exme_recursoeducativodiag d ");
				sql.append("  WHERE  d.exme_diagnostico_id IN (");
				sql.append(StringUtils.join(diagnosticIds, ","));
				sql.append("  ) ");
				if (productIds.size() > 0) {
					sql.append("    UNION ");
				}
				// recursos educativos de stroke
				lista.addAll(MEXMERecursoEducativoStr.getRecursos(ctx, diagnosticIds, trxName));
			}
			// products
			if (productIds.size() > 0) {
				sql.append("  SELECT exme_recursoeducativo_id ");
				sql.append("  FROM   exme_recursoeducativoprod p ");
				sql.append("  WHERE  p.m_product_id IN (");
				sql.append(StringUtils.join(productIds, ","));
				sql.append(" ) ");
				if (genProdIds.size() > 0) {
					sql.append("    UNION ");
				}
			}
			// generic products
			if (genProdIds.size() > 0) {
				sql.append("  SELECT exme_recursoeducativo_id ");
				sql.append("  FROM   exme_recursoeducativogen p ");
				sql.append("  WHERE  p.exme_genproduct_id IN (");
				sql.append(StringUtils.join(genProdIds, ","));
				sql.append(" ) ");
			}
			sql.append("  ) ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_RecursoEducativo", "r"));
			sql.append(" ORDER BY r.created");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					final MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, rs, trxName);
					if (re.isActive()) {
						lista.add(re);
					}
				}

			} catch (final Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}
		}
		return lista;
	}

	/**
	 * Obtiene los recursos educativos del sistema relacionados a un estudio y un resultado de laboratorio
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param loincId Estudio - Codigo loinc, proviene de MEstudiosObx.CodeId
	 * @param value Valor- Resultado del estudio de laboratorio, proviene de MEstudiosObx.Observacion
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMERecursoEducativo> get(final Properties ctx, final int loincId, final double value, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * ");
		sql.append(" FROM exme_recursoeducativo ");
		sql.append(" WHERE exme_recursoeducativo_id IN ");
		sql.append("  (SELECT exme_recursoeducativo_id ");
		sql.append("   FROM   exme_recursoeducativores ");
		sql.append("   WHERE  isActive='Y' "); // Regresa dos registro uno activo y otro no deberia ser solo un loinc,
		sql.append("   AND    EXME_Loinc_ID=? "); // Lama no se agrego AND.
		sql.append("   AND ?  BETWEEN level_min AND level_max ");
		sql.append("  ) ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_RecursoEducativo"));
		sql.append(" ORDER BY created");

		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, loincId);
			pstmt.setDouble(2, value);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MEXMERecursoEducativo(ctx, rs, trxName));
			}

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtiene los recursos educativos medlineplus de una lista de estudios
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param estudios Listado de estudios de laboratorio con los codigos Loinc
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMERecursoEducativo> getWS(final Properties ctx, final List<MEstudiosOBX> estudios, final String trxName) {
		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		// retorna una lista vacia si no hay datos para buscar
		if (estudios == null || estudios.isEmpty()) {
			return lista;
		}
		final TreeMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.1");
		parameters.put("mainSearchCriteria.v.dn", "");
		parameters.put("informationRecipient.languageCode.c", "en");
		s_log.log(Level.FINE, "Loinc Critera: " + parameters.get("mainSearchCriteria.v.cs"));

		final ConsultingWs consultaWs = new ConsultingWs("cfm");
		consultaWs.setParameters(parameters);
		// busca los recursos educativos de los resultados de laboratorio
		for (final MEstudiosOBX obx : estudios) {
			if (StringUtils.isNumeric(obx.getObservation())) {
				lista.add(getWS(new MEXMELoinc(ctx, obx.getCodeID(), trxName), parameters, consultaWs));
			}
		}
		return lista;
	}

// Lama, Nov 2012 - Se reemplaza por: getWS(final Properties ctx, final List<MEstudiosOBX> estudios, final String trxName)
//	Se comenta codigo debido :
//		** se crean metodon con las lineas de codigo repetidas para medicamentos/diagnosticos/loinc
//		** no es necesario accesar nuevamente a la base de datos, 
//	       ya que los metodos que mandan lo mandaban llamar tienen como parametro 
//	       la lista de MEstudiosOBX donde la columna CODEID contiene el codigo Loinc
//		** creacion innecesaria de objetos al inicio del metodo, los cuales son vueltos a instanciar dentro del while
//	
//	public static List<MEXMERecursoEducativo> getWS(Properties ctx, int loincId, String trxName) {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		// Iterator<?> iter;
//		TreeMap<String, String> parametres = new TreeMap<String, String>();
//		TreeMap<String, TreeMap<String, String>> childElements = new TreeMap<String, TreeMap<String, String>>();
//		TreeMap<String, String> attribute = new TreeMap<String, String>();
//		ReadXML readXml;
//		ConsultingWs consultaWs = new ConsultingWs("cfm");
//		consultaWs.setParameters(parametres);
//		String parentElement = "entry";
//		childElements.put("title", null);
//		attribute = new TreeMap<String, String>();
//		attribute.put("href", null);
//		childElements.put("link", attribute);
//		childElements.put("summary", null);
//		parametres.put("mainSearchCriteria.v.cs", "2.16.840.1.113883.6.1");
//		parametres.put("mainSearchCriteria.v.dn", "");
//		parametres.put("informationRecipient.languageCode.c", "en");
//		sql.append("SELECT * ");
//		sql.append("  FROM EXME_ESTUDIOSOBX ");
//		sql.append(" INNER JOIN EXME_LOINC ON (EXME_ESTUDIOSOBX.CODEID = EXME_LOINC.EXME_LOINC_ID) ");
//		sql.append(" WHERE EXME_LOINC.EXME_LOINC_ID = ? ");
//		sql.append(" ORDER BY EXME_LOINC.CREATED ");
//		final List<MEXMELoinc> labsResult = new ArrayList<MEXMELoinc>();
//		MEXMELoinc loinc;
//		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, loincId);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				loinc = new MEXMELoinc(ctx, rs, trxName);
//				labsResult.add(loinc);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		Iterator<?> iter = labsResult.iterator();
//		while (iter.hasNext()) {
//			loinc = (MEXMELoinc) iter.next();
//			parametres.put("mainSearchCriteria.v.c", loinc.getValue()); // LOINC
//			s_log.log(Level.FINE, "Lab Result: " + parametres.get("mainSearchCriteria.v.c"));
//			readXml = new ReadXML(parentElement);
//			readXml.getData(consultaWs.request(), childElements);
//			MEXMERecursoEducativo re = new MEXMERecursoEducativo(ctx, 0, trxName);
//			re.setDescription(loinc.getDescription());
//			if (childElements.get("link").get("href") != null && !childElements.get("link").get("href").equals(StringUtils.EMPTY)) {
//				re.setURL(childElements.get("link").get("href").toString());
//				re.setTipoRecurso(MEXMERecursoEducativo.TIPORECURSO_URL);
//			}
//			lista.add(re);
//		}
//		return lista;
//	}

	/**
	 * Obtiene los recursos educativos dados de alta en el sistema relacionados a los estudios de laboratorio y el resultado
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param estudios Listado de estudios de laboratorio con los codigos Loinc
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMERecursoEducativo> get(final Properties ctx, final List<MEstudiosOBX> estudios, final String trxName) {
		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		for (MEstudiosOBX estudio : estudios) {
			if (StringUtils.isNumeric(estudio.getObservation())) {
				lista.addAll(get(ctx, estudio.getCodeID(), Double.parseDouble(estudio.getObservation()), trxName));
			}
		}
		return lista;
	}

	@Override
	public boolean delete(final boolean force, final String trxName) {
		final List<MEXMERecursoEducativoDiag> lstDiag =
			MEXMERecursoEducativoDiag.getFromRecursoEducativo(getCtx(), getEXME_RecursoEducativo_ID(), trxName);
		for (final MEXMERecursoEducativoDiag diag : lstDiag) {
			if (!diag.delete(force, trxName)) {
				return false;
			}
		}
		final List<MEXMERecursoEducativoProd> lstProd =
			MEXMERecursoEducativoProd.getFromRecursoEducativo(getCtx(), getEXME_RecursoEducativo_ID(), trxName);
		for (final MEXMERecursoEducativoProd prod : lstProd) {
			if (!prod.delete(force, trxName)) {
				return false;
			}
		}
		final List<MEXMERecursoEducativoRes> lstRes =
			MEXMERecursoEducativoRes.getFromRecursoEducativo(getCtx(), getEXME_RecursoEducativo_ID(), trxName);
		for (final MEXMERecursoEducativoRes res : lstRes) {
			if (!res.delete(force, trxName)) {
				return false;
			}
		}
		final List<MEXMERecursoEducativoPac> lstPac =
			MEXMERecursoEducativoPac.getFromRecursoEducativo(getCtx(), getEXME_RecursoEducativo_ID(), trxName);
		for (final MEXMERecursoEducativoPac res : lstPac) {
			if (!res.delete(force, trxName)) {
				return false;
			}
		}

		return super.delete(force, trxName);
	}

	/**
	 * Regresa la lista de objetos MEXMERecursoEducativo que el paciente tiene relacionados en la tabla EXME_RecursoEducativoPac
	 * 
	 * @author raul
	 * @param ctx
	 * @param patientId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMERecursoEducativo> getPatientsResources(final Properties ctx, final int patientId, final int encounterFormId,
		final String trxName) {
		final List<MEXMERecursoEducativo> lista = new ArrayList<MEXMERecursoEducativo>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_RecursoEducativoPac.*, ");
		sql.append("Name as ").append(COLUMNNAME_Description).append(", ");
		sql.append("Created as ").append(COLUMNNAME_FechaRecursoPac).append(", ");
		// tipo de recurso
		sql.append("CASE WHEN URL IS NOT NULL THEN ").append(DB.TO_STRING(TIPORECURSO_URL));
		sql.append(" ELSE ").append(DB.TO_STRING(TIPORECURSO_File));
		sql.append(" END as TipoRecurso ");
		sql.append("FROM EXME_RecursoEducativoPac ");
		sql.append("WHERE isActive='Y' AND EXME_Paciente_ID=? ");
		if (encounterFormId > 0) {
			sql.append(" AND EXME_EncounterForms_ID=?");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMERecursoEducativoPac.Table_Name));
		sql.append(" ORDER BY Name, Created DESC ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, patientId);
			if (encounterFormId > 0) {
				pstmt.setInt(2, encounterFormId);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMERecursoEducativo rec = new MEXMERecursoEducativo(ctx, rs, trxName);
				lista.add(rec);
			}
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean retValue = false;
		if (obj instanceof MEXMERecursoEducativo) {
			retValue = ((MEXMERecursoEducativo) obj).getEXME_RecursoEducativo_ID() == getEXME_RecursoEducativo_ID();
		}
		return retValue;
	}

	@Override
	public int compareTo(final MEXMERecursoEducativo o) {
		return getCreated().compareTo(o.getCreated());
	}

	public static boolean isUsed(final Properties ctx, final int recursoID, final String trxName) {
		boolean retValue = false;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_RecursoEducativoPac_ID FROM EXME_RecursoEducativoPac WHERE EXME_RecursoEducativo_id = ?");
		try {
			retValue = DB.getSQLValue(trxName, sql.toString(), recursoID) > 0;
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		return retValue;
	}

//	private boolean	selected	= true;
//
//	public boolean isSelected() {
//		return selected;
//	}
//
//	public void setSelected(final boolean selected) {
//		this.selected = selected;
//	}

	/**
	 * Consulta WS a medlineplus segun el value proporcionado, crea el objeto de MEXMERecursoEducativo con el URL
	 * 
	 * @param model Modelo PO donde tomara los valores: Value (criterio para hacer la peticion) este corresponde a un NDC/ICD-9/Loinc
	 * @param parameters Mapa de parametros, con los criterios principales
	 * @param consultaWs
	 * @return El objeto MEXMERecursoEducativo con la URL del recurso educativo (NOTA: tiene ID cero)
	 */
	private static MEXMERecursoEducativo getWS(final PO model, final TreeMap<String, String> parameters, final ConsultingWs consultaWs) {
		final TreeMap<String, String> attribute = new TreeMap<String, String>();
		attribute.put("href", null);

		final TreeMap<String, TreeMap<String, String>> childElements = new TreeMap<String, TreeMap<String, String>>();
		childElements.put("title", null);
		childElements.put("link", attribute);
		childElements.put("summary", null);

		parameters.put("mainSearchCriteria.v.c", model.get_ValueAsString("Value")); // NDC // ICD-9-CM //LOINC

		final ReadXML readXml = new ReadXML("entry");
		readXml.getData(consultaWs.request(), childElements);

		final MEXMERecursoEducativo re = new MEXMERecursoEducativo(model.getCtx(), 0, model.get_TrxName());
		re.setDescription(model.get_ValueAsString("Name"));

		if (childElements.get("link").get("href") != null && !childElements.get("link").get("href").equals(StringUtils.EMPTY)) {
			re.setURL(childElements.get("link").get("href").toString());
			re.setTipoRecurso(MEXMERecursoEducativo.TIPORECURSO_URL);
		}
		s_log.log(Level.FINE, "URL: " + consultaWs.getUrl());
		s_log.log(Level.FINE, PO.class.getName() + " Critera: " + parameters.get("mainSearchCriteria.v.c"));
		return re;
	}

	/**
	 * Regresa los modelos MProduct a partir de una lista de Ids
	 * 
	 * @param ctx
	 * @param productIds
	 * @param trxName
	 * @return
	 */
	private static List<MProduct> getProducts(final Properties ctx, final Collection<Integer> productIds, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" M_PRODUCT_ID IN (");
		sql.append(StringUtils.join(productIds, ","));
		sql.append("  ) ");
		return new Query(ctx, MProduct.Table_Name, sql.toString(), trxName).list();
	}

	/**
	 * Regresa los modelos MDiagnoticos a partir de una lista de Ids
	 * 
	 * @param ctx
	 * @param diagnosticIds
	 * @param trxName
	 * @return
	 */
	private static List<MDiagnostico> getDiagnosis(final Properties ctx, final Collection<Integer> diagnosticIds, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" EXME_DIAGNOSTICO_ID IN (");
		sql.append(StringUtils.join(diagnosticIds, ","));
		sql.append("  ) ");
		return new Query(ctx, MDiagnostico.Table_Name, sql.toString(), trxName).setOnlyActiveRecords(true).list();
	}
}
