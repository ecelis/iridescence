/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Encabezado de prescripción de medicamentos
 * @author lhernandez
 * Modificado por Lorena Lama
 */
public class MEXMEPrescRX extends X_EXME_PrescRX {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** log de la clase*/
	private static CLogger log = CLogger.getCLogger(MEXMEPrescRX.class);

	/**
	 * @param ctx
	 * @param EXME_PrescRX_ID
	 * @param trxName
	 */
	public MEXMEPrescRX(Properties ctx, int EXME_PrescRX_ID, String trxName) {
		super(ctx, EXME_PrescRX_ID, trxName);
		if (is_new()) {
			setProcessing(Boolean.FALSE);
			setProcessed(Boolean.FALSE);
			setDocAction(X_EXME_PrescRX.DOCACTION_None);
			setDocStatus(X_EXME_PrescRX.DOCSTATUS_Unknown);
			setDocumentNo(Constantes.getCustom("yyyyMMddhhmmss").format(new Date()));
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPrescRX(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Busca el PrexcRX  activo/existente de una cuenta paciente y el tipo, si no existe lo crea
	 * @param ctx
	 * @param exmeCtaPacId cuenta paciente
	 * @param type tipo de prescripcion 
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrescRX getPrescRX(Properties ctx, int exmeCtaPacId, String type, String trxName) {
		MEXMEPrescRX prescRX = MEXMEPrescRX.getOfCtaPac(ctx, exmeCtaPacId, type, trxName);
		if (prescRX == null ){
			prescRX = new MEXMEPrescRX(ctx, 0, trxName);
		}
		if (prescRX.getEXME_PrescRX_ID() == 0) {
			prescRX.setEXME_CtaPac_ID(exmeCtaPacId);
			prescRX.setTipo(type);
			
			if (!prescRX.save()) {
				throw new MedsysException("error.notasMed.servicios.detalle");
			}
		}
		return prescRX;
	}
	
	/**
	 * Obtiene los Home Medications por paciente
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pacId
	 *            Paciente
	 * @return Listado de Home Medications
	 */
	public static List<MEXMEPrescRX> getHomeMedByPac(final Properties ctx, final int pacId) {

		List<MEXMEPrescRX> retValue = new ArrayList<MEXMEPrescRX>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			sql.append("SELECT ");
			sql.append("  EXME_PrescRX.* ");
			sql.append("FROM ");
			sql.append("  EXME_PrescRX ");
			sql.append("  INNER JOIN exme_ctapac cta ");
			sql.append("  ON EXME_PrescRX.exme_ctapac_id = cta.exme_ctapac_id ");
			sql.append("  INNER JOIN exme_paciente pac ");
			sql.append("  ON cta.exme_paciente_id = pac.exme_paciente_id ");
			sql.append("WHERE ");
			sql.append("  pac.exme_paciente_id=? AND ");
			sql.append("  TRIM(EXME_PrescRX.TIPO) = ? AND ");
			sql.append("  EXME_PrescRX.isActive= 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
			sql.append(" ORDER BY EXME_PrescRX.Created DESC");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, pacId);
			pstmt.setString(2, TIPO_HomeMedication);

			result = pstmt.executeQuery();
			while (result.next()) {
				retValue.add(new MEXMEPrescRX(ctx, result, null));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(result, pstmt);
		}

		return retValue;
	}
	
	/** Obtiene la prescripcion actual de una cuenta paciente
	 * @author natalia
	 * @param ctx
	 * @param ctaPacID
	 * @param tipo
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrescRX getOfCtaPac(Properties ctx, int ctaPacID, String tipo, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		MEXMEPrescRX retValue = null;
		try {
			sql.append("SELECT EXME_PrescRX.* ");
			sql.append("FROM EXME_PrescRX ");
			sql.append("WHERE EXME_PrescRX.EXME_CtaPac_ID = ? ");
			sql.append("AND EXME_PrescRX.isActive = 'Y' ");
			if (StringUtils.isNotEmpty(tipo)) {
				sql.append("AND EXME_PrescRX.Tipo = ? ");
			}
			sql.append(" ORDER BY EXME_PrescRX.Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			if (StringUtils.isNotEmpty(tipo)) {
				pstmt.setString(2, tipo);
			}

			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				retValue = new MEXMEPrescRX(ctx, resultset, trxName);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(resultset, pstmt);
		}
		return retValue;
	}
	
	public static List<MEXMEPrescRX> getLstOfCtaPac(Properties ctx, int ctaPacID, String tipo, String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		List<MEXMEPrescRX> retValue = new ArrayList<MEXMEPrescRX>();
		try {
			sql.append("SELECT EXME_PrescRX.* ");
			sql.append("FROM EXME_PrescRX ");
			sql.append("WHERE EXME_PrescRX.EXME_CtaPac_ID = ? ");
			sql.append("AND EXME_PrescRX.isActive = 'Y' ");
			if (StringUtils.isNotEmpty(tipo)) {
				sql.append("AND EXME_PrescRX.Tipo = ? ");
			}
			sql.append(" ORDER BY EXME_PrescRX.Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacID);
			if (StringUtils.isNotEmpty(tipo)) {
				pstmt.setString(2, tipo);
			}

			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				retValue.add(new MEXMEPrescRX(ctx, resultset, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(resultset, pstmt);
		}
		return retValue;
	}
	

	/** Obtiene la ultima prescipcion de un paciente
	 * @author natalia
	 * @param ctx
	 * @param ctaPacID
	 * @param tipo
	 * @param trxName
	 * @return
	 */
	public static MEXMEPrescRX getUltimaPres(Properties ctx, int ctaPacID, String tipo, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("  EXME_PrescRX.EXME_CtaPac_ID=? ").append(" AND EXME_PrescRX.Tipo = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(ctaPacID);
		params.add(tipo);
		return new Query(ctx, Table_Name, sql.toString(), trxName).setParameters(params).setOnlyActiveRecords(true)
			.setOrderBy(" EXME_PrescRX.Created DESC ").first();
	}
	
	public static MEXMEPrescRX getPrescbyCita(Properties ctx, int EXME_CitaMedica_Id, String trxName) {
		MEXMEPrescRX prescRx = new Query(ctx, Table_Name, " exme_citamedica_ID=? ", trxName)//
			.setParameters(EXME_CitaMedica_Id).first();
		return prescRx == null ? new MEXMEPrescRX(ctx, 0, null) : prescRx;
	}
	

//	public static MEXMEPrescRX get(Properties ctx, String trxName, String whereClause, StringBuilder joins, String orderBy,
//		boolean onlyActiveRecords, boolean accesslevel, Object... parameters) {
//		return new Query(ctx, Table_Name, whereClause, trxName)//
//			.setJoins(joins)//
//			.setParameters(parameters)//
//			.setOrderBy(orderBy)//
//			.setOnlyActiveRecords(onlyActiveRecords)//
//			.addAccessLevelSQL(accesslevel)//
//			.first();
//	}
	
	/** Obtiene las prescripciones de un paciente
	 * @param ctx
	 * @param exmePacId
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEPrescRX> getPrescByPac(Properties ctx, int exmePacId, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		List<MEXMEPrescRX> retValue = new ArrayList<MEXMEPrescRX>();
		try {
			sql.append("SELECT prx.* ");
			sql.append("FROM EXME_PrescRX prx");
			sql.append(" INNER JOIN EXME_CTAPAC cp on (prx.exme_ctapac_id = cp.exme_ctapac_id AND cp.exme_paciente_id = ?) ");
			sql.append("WHERE prx.isActive = 'Y' AND (")
			.append(" prx.Tipo = '").append(TIPO_HomeMedication).append("' OR")
			.append(" prx.Tipo = '").append(TIPO_OV).append("' OR")
			.append(" prx.Tipo = '").append(TIPO_DischargeMedication).append("' )");
			sql.append(" ORDER BY prx.Created DESC ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmePacId);

			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				retValue.add(new MEXMEPrescRX(ctx, resultset, trxName));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(resultset, pstmt);
		}
		return retValue;
	}
	
	
	/**
	 * Obtiene la Prescripcion en base al EXME_PrescRX_ID
	 * 
	 * @param trxName
	 * @return MEXMEPrescription
	 */
	public MEXMEPrescription getPrescription(final boolean createNew) {
		return MEXMEPrescription.getPrescriptionByPrescRxId(getCtx(), get_ID(), createNew, get_TrxName());
	}
	
	/**
	 * Guardar el encabezado de la prescripcion en la tabla EXME_Prescription y EXME_PrescRXDet
	 * 
	 * @param prescription
	 * @return
	 */
	public boolean savePrescription(final MEXMEPrescription prescription) {
		boolean success = Boolean.TRUE;
		if (is_new()) {
			success = save();
		}
		if (success && prescription != null && prescription.is_new()) {
			prescription.setDocumentNo(getDocumentNo());
			final StringBuilder builder = new StringBuilder();
			builder.append(Utilerias.getMsg(getCtx(), "enfermeria.msg.medicamentos")).append(" - ")
				.append(Constantes.getSDFDateTime(getCtx()).format(getCreated()));
			prescription.setDescription(builder.toString());
			prescription.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
			prescription.setEXME_Especialidad_ID(getEXME_CtaPac().getEXME_Especialidad_ID());
			prescription.setEXME_Medico_ID(getEXME_Medico_ID());
			prescription.setM_Warehouse_ID(Env.getM_Warehouse_ID(getCtx()));
			prescription.setEXME_PrescRX_ID(getEXME_PrescRX_ID());
			success = prescription.save();
		}
		return success;
	}
	
	/**
	 * Crea un nuevo detalle a partir del encabezado.
	 * Asigna los valores del encabezado, junto con los valores por defecto:
	 * - cantidades en 1
	 * - el producto identificado como NDC temporal
	 * - la estación de servicio de la cuenta paciente (Card #1188)
	 * @return MEXMEPrescRXDet
	 */
	public MEXMEPrescRXDet newDetail() {
		final MEXMEPrescRXDet retValue = new MEXMEPrescRXDet(getCtx(), 0, get_TrxName());
		retValue.setEXME_Medico_ID(getEXME_Medico_ID());
		retValue.setEXME_PrescRX_ID(getEXME_PrescRX_ID());
		retValue.setQtyPlan(Env.ONE);// TODO: qty process
		retValue.setQuantity(Env.ONE);
		retValue.setM_Product_ID(MEXMEMejoras.get(getCtx()).getTemporaryNDC());// default
		retValue.set_ValueNoCheck(I_EXME_PrescRXDet.COLUMNNAME_NotedBy, null);//Lama: no se debe llenar por defecto
		//Card #1188 : Guardar el almacen que solicita
		if(getEXME_CtaPac_ID() > 0) {
			retValue.setEXME_EstServ_ID(getEXME_CtaPac().getEXME_EstServ_ID());
		} else {
			retValue.setEXME_EstServ_ID(Env.getEXME_EstServ_ID(getCtx()));			
		}
		return retValue;
	}
	
	/** patient's active prescriptions */
	public void savePatientActivePresc() {
		final MEXMEPaciente patient = new MEXMEPaciente(getCtx(), getEXME_CtaPac().getEXME_Paciente_ID(), null);
		patient.updatePatientActivePresc(get_TrxName(), true);
	}
	
	public static class PrescRx {
		private String name;
		private String doseRate;
		private String route;
		private String freq;
		private Date startdate;
		private String indicaciones;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDoseRate() {
			return doseRate;
		}

		public void setDoseRate(String doseRate) {
			this.doseRate = doseRate;
		}

		public String getRoute() {
			return route;
		}

		public void setRoute(String route) {
			this.route = route;
		}

		public String getFreq() {
			return freq;
		}

		public void setFreq(String freq) {
			this.freq = freq;
		}

		public Date getStartdate() {
			return startdate;
		}

		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}

		public String getIndicaciones() {
			return indicaciones;
		}

		public void setIndicaciones(String indicaciones) {
			this.indicaciones = indicaciones;
		}
	}
	public static List<PrescRx> get(Properties ctx, int encounterFormId, String trxName) {
		return get(ctx, encounterFormId, -1, -1, trxName);
	}
	public static List<PrescRx> get(Properties ctx, int encounterFormId, int patientId, int ctapacId, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  gen.Generic_Product_Name, ");
		sql.append("  det.doseRate, ");
		sql.append("  route.Name AS route, ");
		sql.append("  freq1.Name|| ', ' ||freq2.Name AS freq, ");
		sql.append("  det.startdate, ");
		sql.append("  det.Indicaciones ");
		sql.append("FROM ");
		sql.append("  EXME_PrescRxDet det ");
		sql.append("  INNER JOIN EXME_PrescRx head ");
		sql.append("  ON (det.EXME_PrescRx_ID = head.EXME_PrescRx_ID AND ");
		sql.append("  head.Tipo=? ) ");
		sql.append("  INNER JOIN EXME_CtaPac cp ");
		sql.append("  ON (cp.EXME_CtaPac_ID = head.EXME_CtaPac_ID) ");
//		sql.append("  INNER JOIN EXME_Paciente pat ");
//		sql.append("  ON (cp.EXME_Paciente_ID = pat.EXME_Paciente_ID) ");
//		sql.append("  INNER JOIN EXME_Medico med ");
//		sql.append("  ON (head.EXME_Medico_ID = med.EXME_Medico_ID) ");
		sql.append("  INNER JOIN EXME_GenProduct gen ");
		sql.append("  ON (det.EXME_GenProduct_ID = gen.EXME_GenProduct_ID) ");
		sql.append("  LEFT JOIN EXME_ViasAdministracion route ");
		sql.append("  ON (det.EXME_ViasAdministracion_ID = route.EXME_ViasAdministracion_ID) ");
		sql.append("  LEFT JOIN EXME_Frequency1 freq1 ");
		sql.append("  ON (det.EXME_Frequency1_ID = freq1.EXME_Frequency1_ID) ");
		sql.append("  LEFT JOIN EXME_Frequency2 freq2 ");
		sql.append("  ON (det.EXME_Frequency2_ID = freq2.EXME_Frequency2_ID) ");
		final int paramId;
		if(encounterFormId > 0){
			paramId = encounterFormId;
			sql.append("WHERE head.exme_encounterforms_id = ? ");
		} else if(ctapacId > 0){
			paramId = ctapacId;
			sql.append("WHERE cp.exme_ctapac_id = ? ");
		} else {
			paramId = patientId;
			sql.append("WHERE cp.exme_paciente_id = ? ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "head"));

		final List<PrescRx> list = new ArrayList<PrescRx>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, TIPO_MedicalPrescription);
			pstmt.setInt(2, paramId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final PrescRx presc = new PrescRx();
				presc.setDoseRate(rs.getString("doseRate"));
				presc.setFreq(rs.getString("freq"));
				presc.setIndicaciones(rs.getString("Indicaciones"));
				presc.setName(rs.getString("Generic_Product_Name"));
				presc.setRoute(rs.getString("route"));
				presc.setStartdate(rs.getTimestamp("startdate"));
				list.add(presc);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Buscar todos los NDC de las prescripciones activas.<br>
	 * Valida que si el NDC no es correcto (temporary, not available, null) 
	 * busca un NDC mendiante la funcion fnc_getproductorg.<br>
	 * 
	 * @param ctx
	 * @param pacienteId
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getProdsFromPac(Properties ctx, int pacienteId, String trxName) {
		return getProductId(ctx, -1, pacienteId, trxName);
	}
	
	/**
	 * Buscar todos los NDC de las prescripciones activas.<br>
	 * Valida que si el NDC no es correcto (temporary, not available, null) 
	 * busca un NDC mendiante la funcion fnc_getproductorg.<br>
	 * 
	 * @param ctx
	 * @param ctapacId
	 * @param trxName
	 * @return
	 */
	public static List<Integer> getProductId(Properties ctx, int ctapacId, String trxName) {
		return getProductId(ctx, ctapacId, -1, trxName);
	}
	
	
	/**
	 * Buscar todos los NDC de las prescripciones activas.<br>
	 * Valida que si el NDC no es correcto (temporary, not available, null) 
	 * busca un NDC mendiante la funcion fnc_getproductorg.<br>
	 * 
	 * @param ctx
	 * @param ctapacId
	 * @param pacienteId
	 * @param trxName
	 * @return
	 */
	private static List<Integer> getProductId(Properties ctx, int ctapacId, int pacienteId, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT det.M_Product_Id, det.EXME_GenProduct_ID ");
		sql.append("FROM EXME_PrescRxDet det ");
		sql.append("INNER JOIN EXME_PrescRx head ");
		sql.append("      ON ( det.EXME_PrescRx_ID=head.EXME_PrescRx_ID ");
		if (ctapacId > 0) {
			sql.append("       AND head.EXME_CtaPac_ID=? ");
		}
		sql.append("           AND head.isActive='Y' ) ");
		if (pacienteId > 0) {
			sql.append("INNER JOIN EXME_CtaPac cp ");
			sql.append("      ON ( cp.EXME_CtaPac_ID=head.EXME_CtaPac_ID ");
			sql.append("           AND cp.EXME_Paciente_ID=? ");
			sql.append("           AND cp.isActive='Y' ) ");
		}
		sql.append("WHERE det.isActive= 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "head"));

		final List<Integer> list = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			final MEXMEMejoras mejoras = MEXMEMejoras.get(ctx);
			int idx = 1;
			if(ctapacId > 0) {
				pstmt.setInt(idx++, ctapacId);
			}
			if(pacienteId > 0) {
				pstmt.setInt(idx++, pacienteId);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt(1);
				if (productId <= 0 || productId == mejoras.getTemporaryNDC()
						|| productId == mejoras.getNotAvailableNDC()) {
					productId = MProduct.getNDC(ctx, rs.getInt(2));
				}
				list.add(productId);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * @param genProdId
	 * @return
	 */
	public static StringBuilder getSqlMeds(final Properties ctx, final int genProdId, final boolean addOrderBy) {
		final StringBuilder cadena = new StringBuilder();
		// Gerardo Valdez / Zack Morton 10/05/2012 : Obtener siempre un NDC para el medicamento generico
		// cadena.append(" INNER JOIN EXME_ProductoOrg po ON po.EXME_ProductoOrg_ID=fnc_getproductorg(M_Product.m_product_id,");
		// se comenta la función debido al tiempo que tarda la ejecución del sql para postgres. Oct 16
		cadena.append(" INNER JOIN EXME_ProductoOrg po ON (M_Product.m_product_id=po.M_Product_ID ");
		cadena.append(" AND M_Product.EXME_GenProduct_ID=").append(genProdId > 0 ? genProdId : "?");
		cadena.append(" AND po.isActive='Y' ");
		cadena.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEProductoOrg.Table_Name, "po")).append(" )");
		cadena.append(" WHERE M_Product.isActive='Y' ");
		cadena.append(" AND M_Product.ProductClass IN (").append(DB.TO_STRING(MProduct.PRODUCTCLASS_Drug));
		cadena.append(" ,").append(DB.TO_STRING(MProduct.PRODUCTCLASS_Immunization));
		cadena.append(" ) ");
		cadena.append(" AND M_Product.IsSold='Y' ");
		cadena.append(" AND M_Product.ProductType=").append(DB.TO_STRING(MProduct.PRODUCTTYPE_Item));
		if (addOrderBy) {
			cadena.append(" ORDER BY po.ad_org_id DESC, po.IsFormulary DESC ");// Ago 2012
		}
		return cadena;
	}
}
