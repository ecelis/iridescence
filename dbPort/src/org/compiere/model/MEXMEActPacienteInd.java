package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.CreateCharge;
import org.compiere.model.bpm.GetPrice;
import org.compiere.model.bpm.MedicationSave;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.Worklist;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Modelo de Actividad Paciente Indicaciones
 * <p>
 * Productos / Servicios por paciente
 */
public class MEXMEActPacienteInd extends X_EXME_ActPacienteInd implements DocAction, Worklist {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Logger */
	protected transient static CLogger	slog =CLogger.getCLogger(MEXMEActPacienteInd.class);
	/** Id de la actividad paciente */
	private int actPacienteID = 0;
	/* Objeto adjunto de la actividad */
	private MAttachment attachment;
	/** producto */
	private MProduct product = null;
	/** Producto */
	private MEXMEProduct mProducto = null;
	/** nombre del servicio */
	private String nameServ = null;
	/** intervencion */
	private MIntervencion cpt = null;
	/** unidad de medida */
	private MUOM uom = null;
	/** nombre de la unidad de medida */
	private String uomName = null;
	/** primer diagnostico */
	private MDiagnostico diag1 = null;
	/** diagnostico */
	private MDiagnostico diag2 = null;
	/** diagnostico */
	private MDiagnostico diag3 = null;
	 /**Descriptive presumptive diagnostic 1 */
	private String diagnosis1Text = null;
	/** Descriptive presumptive diagnostic 2 */
	private String diagnosis2Text = null;
	/** Descriptive presumptive diagnostic 3 */
	private String diagnosis3Text = null;
	/** Variable para determinar si el servicio tiene relacionado un archivo. */
	public boolean hasFile = false;
	/** Resultados de laboratorio HL7 */
	private boolean hasOBX = false;
	/** Encabezado de las indicaciones medicas */
	private MEXMEActPacienteIndH actPacIndH = null;
	/** maneja HL7 */
	private boolean manejaHL7 = false;
	/** Almacen */
	private MWarehouse almacen = null;
	/** lhernandez. ticket 2330 **/
	private List<MEstudiosOBX> resultados;
	/** producton generico */
	private MEXMEGenProduct genProduct;
	/** configuracion de precios */
	private MEXMEConfigPre	configPre	= null;
	/** creacion de cargos a cuenta paciente */
//	private CreateCharge mCreateCharge;
	/** Medico */
	private MEXMEMedico medico = null;
	/** Rev code */
	private MEXMERevenueCodes beanRevCode = null;
	/** Bean */
	private BeanView beanID = null;
	/** Listado de estudios */
	private List<MEstudiosOBX> estudiosObx;
	/** Listado de errores */
	private List<ModelError> lstErrors = new ArrayList<ModelError>();
	/** */
	private MCtaPacDet cargo = null;
	
	/** Constructor
	 * @param ctx
	 * @param EXME_ActPacienteInd_ID
	 * @param trxName
	 */
	public MEXMEActPacienteInd(final Properties ctx, final int EXME_ActPacienteInd_ID,
			final String trxName) {
		super(ctx, EXME_ActPacienteInd_ID, trxName);
		if (is_new()) {
			setQtyDelivered(Env.ZERO);
			setQtyInvoiced(Env.ZERO);
			setQtyOrdered(Env.ZERO);
			setQtyReserved(Env.ZERO);

			setCosto(Env.ZERO);
			setPriceActual(Env.ZERO);
			setPriceLimit(Env.ZERO);
			setPriceList(Env.ZERO);
			setLineNetAmt(Env.ZERO);
			setFreightAmt(Env.ZERO);

			setChargeAmt(Env.ZERO);
			setIsDescription(false);
			setSurtir(true);
			setDocStatus(MEXMEActPacienteInd.DOCSTATUS_Drafted);
			setEstatus(MEXMEActPacienteInd.ESTATUS_RequestedService);
		} else {
			setHasOBX();	
		}
	}

	public MEXMEActPacienteInd(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
		setHasOBX();
	}
	
	public List<MEstudiosOBX> getEstudiosObx() {
		return getEstudios(false);
	}

	public void setEstudiosObx(List<MEstudiosOBX> estudiosObx) {
		this.estudiosObx = estudiosObx;
	}

	public List<MEstudiosOBX> getEstudios(boolean descartaNoNumericos) {
		if (estudiosObx == null) {
			estudiosObx = MEstudiosOBX.getEstudios(getCtx(),
					getEXME_ActPacienteInd_ID(), descartaNoNumericos,
					get_TrxName());
		}
		return estudiosObx;
	}

	/**
	 * List of prescriptions
	 * 
	 * @param ctx
	 * @param patientID
	 * @param whereClause
	 * @param trxName
	 * @param params
	 * @return
	 */
	public static List<MEXMEActPacienteInd> getPrescriptionsLst(Properties ctx,
			int patientID, String whereClause, String trxName, Object... params) {
		final List<MEXMEActPacienteInd> lista = new ArrayList<MEXMEActPacienteInd>();

		if (ctx == null) {
			return lista;
		}

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// TODO limpiar SQL
		sql.append(" SELECT api.*  ");
		sql.append(" FROM EXME_ActPaciente ");
		sql.append(" INNER JOIN EXME_ActPacienteIndH apih ON ( EXME_ActPaciente.EXME_ActPaciente_ID = apih.EXME_ActPaciente_ID ");
		sql.append(" AND EXME_ActPaciente.EXME_Paciente_ID = ? AND apih.IsActive = 'Y' AND apih.IsService = 'N' ) ");// solo
		// Prescripciones
		// ...
		sql.append(" INNER JOIN EXME_ActPacienteInd api ON ( apih.EXME_ActPacienteIndH_ID = api.EXME_ActPacienteIndH_ID AND api.IsActive = 'Y') ");
		sql.append(" INNER JOIN M_Product p ON (p.M_Product_ID = api.M_Product_ID AND p.isActive = 'Y' AND p.EXME_GenProduct_ID IS NOT NULL)");
		// sql.append(" WHERE EXME_ActPaciente.IsActive = 'Y'  ");
		// sql.append(" AND EXME_ActPaciente.EXME_Paciente_ID = ? ");
		if (whereClause != null && whereClause.length() > 0) {
			sql.append(whereClause);
		}

		sql.append(" ORDER BY api.dateordered DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, patientID);

			if (params.length > 0) {
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lista.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getPrescriptionsLst", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}

	
	public static List<MEXMEActPacienteInd> getInstitucionesServicioAPInd(Properties ctx, int EXME_CitaMedica_ID,
			String trxName) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			sql.append("SELECT DISTINCT acpaind.* FROM exme_actpaciente acpa ")
				.append("INNER JOIN exme_actpacienteindh acpaindh on acpa.exme_actpaciente_id = acpaindh.exme_actpaciente_id ")
				.append("INNER JOIN exme_actpacienteind acpaind on acpaind.exme_actpacienteindh_id = acpaindh.exme_actpacienteindh_id ")
				.append("LEFT JOIN exme_institucion inst on acpaind.exme_institucion_id = inst.exme_institucion_id ")
				.append("INNER JOIN m_product pro on pro.m_product_id = acpaind.m_product_id ")
				.append("WHERE pro.producttype = 'S' ")
				.append("AND (pro.ProductClass = 'XR' OR pro.ProductClass = 'LA') ")
				.append("AND (acpaind.proveedor is not null or acpaind.exme_institucion_id is not null) ")
				.append("AND acpa.exme_citamedica_id = ?");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}

	/**
	 * Producto
	 */
	public MEXMEProduct getMProducto() {
		if (mProducto == null && getM_Product_ID() > 0) {
			mProducto = new MEXMEProduct(getCtx(), getM_Product_ID(),
					get_TrxName());
		}
		return mProducto;
	}

	public void setMProducto(final MEXMEProduct product) {
		this.mProducto = product;
	}

	/**
	 * Permite almacenar datos adicionales al modelo
	 */
	public BeanView getBeanID() {
		return beanID;
	}

	public void setBeanID(BeanView beanID) {
		this.beanID = beanID;
	}

	/**
	 * Rev. code asignado
	 */
	public MEXMERevenueCodes getBeanRevCode() {
		return this.beanRevCode;
	}

	public void setBeanRevCode(MEXMERevenueCodes beanRevCode) {
		this.beanRevCode = beanRevCode;
	}

		
	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return Instructions de la cita medica
	 */
	public static List<MEXMEInstructions> getInstrunctions(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEInstructions> list = new ArrayList<MEXMEInstructions>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			sql.append("SELECT inst.exme_instructions_id FROM exme_actpaciente acpa ")
			.append("INNER JOIN exme_actpacienteindh acpaindh on acpa.exme_actpaciente_id = acpaindh.exme_actpaciente_id ") 
			.append("INNER JOIN exme_actpacienteind acpaind on acpaind.exme_actpacienteindh_id = acpaindh.exme_actpacienteindh_id ")
			.append("LEFT JOIN exme_instructions inst on acpaind.exme_instructions_id = inst.exme_instructions_id ")
			.append("WHERE acpaind.isinstruction = 'Y' ")
			.append("AND acpaind.exme_instructions_id is not null ")
			.append("AND inst.isactive = 'Y' ")
			.append("AND acpa.exme_citamedica_id = ?");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
					
			while (rs.next()) {
				list.add(new MEXMEInstructions(ctx, rs.getInt(1), trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return Actividades pacinete de tipo instructions de la cita medica
	 */
	public static List<MEXMEActPacienteInd> getInstActPac(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			sql.append("SELECT acpaind.* FROM exme_actpaciente acpa ")
			.append("INNER JOIN exme_actpacienteindh acpaindh on acpa.exme_actpaciente_id = acpaindh.exme_actpaciente_id ") 
			.append("INNER JOIN exme_actpacienteind acpaind on acpaind.exme_actpacienteindh_id = acpaindh.exme_actpacienteindh_id ")
			.append("LEFT JOIN exme_instructions inst on acpaind.exme_instructions_id = inst.exme_instructions_id ")
			.append("WHERE acpaind.isinstruction = 'Y' ")
			.append("AND acpaind.exme_instructions_id is not null ")
			.append("AND inst.isactive = 'Y' ")
			.append("AND acpa.exme_citamedica_id = ?");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
					
			while (rs.next()) {
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return Dieta de la cita medica
	 */
	public static MProduct getDieta(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		MProduct prod = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			sql.append("SELECT pro.m_product_id, acpaind.description FROM exme_actpaciente acpa ")
			.append("INNER JOIN exme_actpacienteindh acpaindh on acpa.exme_actpaciente_id = acpaindh.exme_actpaciente_id ") 
			.append("INNER JOIN exme_actpacienteind acpaind on acpaind.exme_actpacienteindh_id = acpaindh.exme_actpacienteindh_id ")
			.append("LEFT JOIN M_Product pro on (acpaind.M_Product_id = pro.M_Product_id) ")
			.append("WHERE pro.isactive = 'Y' AND pro.proceduretype = ? AND acpa.exme_citamedica_id = ?");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, MProduct.PROCEDURETYPE_Diet);
			pstmt.setInt(2, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
					
			while (rs.next()) {
				prod = new MProduct(ctx, rs.getInt(1), trxName);
				prod.setComment(rs.getString(2));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return prod;
	}
	
	/**
	 * Devulve los servicios que no son medicamentos y que no son estudios
	 * @param ctx
	 * @param citaMedicaId
	 * @param trxName
	 * @return
	 */
	public static List<MProduct> getOtherServices(Properties ctx, int citaMedicaId, String trxName) {
		List<MProduct> lst = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			sql.append("select pro.m_product_Id, apind.exme_actpacienteind_id, apind.EXME_OrderSet_ID from exme_actpaciente ap ")
			.append("inner join exme_citamedica cm on (cm.exme_citamedica_id = ap.exme_citamedica_id) ")
			.append("inner join exme_actpacienteindh apindh on (ap.exme_actpaciente_id = apindh.exme_actpaciente_id) ")
			.append("inner join exme_actpacienteind apind on (apind.exme_actpacienteindh_id = apindh.exme_actpacienteindh_id) ")
			.append("inner join m_product pro on (pro.m_product_id = apind.m_product_id and pro.productclass <> ? and pro.productclass <> ?) ")
			.append("where (pro.producttype = 'S' or pro.producttype = 'P') and pro.productclass <> 'DG' and apind.surtir = 'Y' ")
			.append("and apind.isactive = 'Y' and cm.exme_citamedica_id = ? ");
			pstm = DB.prepareStatement(sql.toString(), trxName);
			pstm.setString(1, MProduct.PRODUCTCLASS_XRay);
			pstm.setString(2, MProduct.PRODUCTCLASS_Laboratory);
			pstm.setInt(3, citaMedicaId);
			rs= pstm.executeQuery();
			while (rs.next()) {
				MProduct product = new MProduct(ctx, rs.getInt(1), trxName);
				product.setActPacienteIndId(rs.getInt(2));
				product.setEXME_OrderSet_ID(rs.getInt(3));
				lst.add(product);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstm);
			pstm = null;
			rs = null;
		}
		return lst;
	}
	
	
	public static List<MProduct> getStudies(Properties ctx, int citaMedicaId, boolean surtir, int orgID, String trxName) {
		List<MProduct> lst = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			sql.append("select pro.m_product_id, apind.exme_actpacienteind_id, apind.EXME_OrderSet_ID from exme_actpaciente ap ")
			.append("inner join exme_citamedica cm on (cm.exme_citamedica_id = ap.exme_citamedica_id) ")
			.append("inner join exme_actpacienteindh apindh on (ap.exme_actpaciente_id = apindh.exme_actpaciente_id) ")
			.append("inner join exme_actpacienteind apind on (apind.exme_actpacienteindh_id = apindh.exme_actpacienteindh_id) ")
			.append("inner join m_product pro on (pro.m_product_id = apind.m_product_id) ")
			.append("where ((pro.producttype = 'S' or pro.producttype = 'P') and (pro.productclass = ? or pro.productclass = ? )) ")
			.append(" and pro.productclass <> 'DG' and apind.surtir = ? ")
			.append("and apind.isactive = 'Y' and cm.exme_citamedica_id = ? "); 
			
			if (orgID > 0) {
				sql.append(" AND apind.AD_Org_Dest_ID = ?");
			}
			
			pstm = DB.prepareStatement(sql.toString(), trxName);
			pstm.setString(1, MProduct.PRODUCTCLASS_XRay);
			pstm.setString(2, MProduct.PRODUCTCLASS_Laboratory);
			pstm.setString(3, DB.TO_STRING(surtir));			
			pstm.setInt(4, citaMedicaId);
			
			if (orgID > 0) {
				pstm.setInt(5, orgID);
			}
			rs= pstm.executeQuery();
			while (rs.next()) {
				MProduct product = new MProduct(ctx, rs.getInt(1), trxName);
				product.setActPacienteIndId(rs.getInt(2));
				product.setEXME_OrderSet_ID(rs.getInt(3));
				lst.add(product);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstm);
			pstm = null;
			rs = null;
		}
		return lst;
	}
	
	/**
	 * Busqueda de un producto en estatus de solicitud
	 * @param idProduct producto a buscar
	 * @return id de la linea de la solicitud
	 * @throws SQLException
	 */
	public static List<MEXMEActPacienteInd> getLstSolicitud(final Properties ctx, final int actPacIndHID, final String trxName, final String status) {
		List<MEXMEActPacienteInd> lst = new ArrayList<MEXMEActPacienteInd>();
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			final List<Object> params = new ArrayList<Object>();
			params.add(actPacIndHID);
			sql.append(" EXME_ActPacienteIndH_ID=? ");
			if (StringUtils.isNotEmpty(status)){
				sql.append(" AND estatus=? ");
				params.add(status);
			}
			
			lst = new Query(ctx, Table_Name, sql.toString(), trxName) //
//			.setOnlyActiveRecords(true)//
//			.addAccessLevelSQL(true)//
			.setParameters(params)//
			.list();
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		}
		return lst;
	}
	
	public List<MEstudiosOBX> getEstudiosObxRef() {
		return getEstudios(false, true);
	}
	
	public List<MEstudiosOBX> getEstudios(boolean descartaNoNumericos, boolean isReferral) {
		return MEstudiosOBX.getEstudios(getCtx(), getEXME_ActPacienteInd_ID(), descartaNoNumericos, isReferral, get_TrxName());
	}
	
	/**
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return Actividades pacinete de determinada cita medica
	 */
	public static List<MEXMEActPacienteInd> getFromCita(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			sql.append(	"Select pacind.comments ,pacind.isinstruction  From Exme_Actpacienteind Pacind ")
			.append("Inner Join Exme_Actpacienteindh Pacindh On Pacind.Exme_Actpacienteindh_Id = Pacindh.Exme_Actpacienteindh_Id ")
			.append("Inner Join Exme_Citamedica Cmed On Cmed.Exme_Ctapac_Id = Pacindh.Exme_Ctapac_Id ")
			.append("Where Cmed.Exme_Citamedica_Id = ? ");

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return list;
	}
	
	/**
	 * Si el paciente tiene el producto enviado
	 * 
	 * @param ctx
	 *            App Ctx
	 * @param pacId
	 *            Paciente
	 * @param prodId
	 *            Poducto
	 * @param trxName
	 *            Trx Name
	 * @return Si tiene o no el producto enviado
	 */
	public static MEXMEActPacienteInd getLastService(Properties ctx, int pacId, int prodId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_actpacienteind ind ");
		sql.append("  INNER JOIN exme_actpacienteindh indh ");
		sql.append("  ON ind.exme_actpacienteindh_id = indh.exme_actpacienteindh_id ");
		sql.append("  INNER JOIN exme_actpaciente act ");
		sql.append("  ON indh.exme_actpaciente_id = act.exme_actpaciente_id ");
		sql.append("WHERE ");
		sql.append("  act.exme_paciente_id = ? AND ");
		sql.append("  ind.m_product_id = ? AND ");
		sql.append("  ind.isActive = 'Y' AND ");
		sql.append("  ind.docStatus = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "ind"));
		sql.append("ORDER BY ");
		sql.append("  ind.created desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMEActPacienteInd ind = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			pstmt.setInt(2, prodId);
			pstmt.setString(3, DOCSTATUS_Completed);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ind = new MEXMEActPacienteInd(ctx, rs, trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ind;
	}
	
	/**
	 * List of studies request
	 * 
	 * @param ctx
	 * @param patientID
	 * @param whereClause
	 * @param trxName
	 * @param params
	 * @return
	 */
	public static List<MEXMEActPacienteInd> getServicesLst(Properties ctx, int patientID, String whereClause,
			String trxName, Object... params) {
		final List<MEXMEActPacienteInd> lista = new ArrayList<MEXMEActPacienteInd>();

		if (ctx == null) {
			return lista;
		}

		// Obtenemos solo aquellos servicios especializados de gabinete
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT api.*  ");
		sql.append(" FROM EXME_ActPaciente ");
		sql.append(" INNER JOIN EXME_ActPacienteIndH apih ON ( EXME_ActPaciente.EXME_ActPaciente_ID = apih.EXME_ActPaciente_ID ");
		sql.append(" AND apih.IsActive = 'Y' AND apih.IsService = 'Y' ) ");// solo servicios
		sql.append(" INNER JOIN EXME_ActPacienteInd api ON ( apih.EXME_ActPacienteIndH_ID = api.EXME_ActPacienteIndH_ID AND api.IsActive = 'Y') ");
		// Servicos Especializados de Gabinete.  /**Se elimina referencia a columna isEstudio  GADC**/
		sql.append(" INNER JOIN M_Product p ON (p.M_Product_ID = api.M_Product_ID AND  p.ProductClass in (");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_XRay));
		sql.append(", ");
		sql.append(DB.TO_STRING(X_M_Product.PRODUCTCLASS_Laboratory));
		sql.append("))");	
		sql.append(" WHERE EXME_ActPaciente.IsActive = 'Y'  ");
//		sql.append(" AND EXME_ActPaciente.EXME_Paciente_ID = ? ");
		// JGaray Se agrega el filtro para mostrar servicios parciales
		sql.append(" AND ((apih.Docstatus = 'CO' AND api.IsActive='Y' ) OR (apih.Docstatus = 'IP' AND api.IsActive='Y' AND api.Estatus = 'C')) ");
		
		if (whereClause != null && whereClause.length() > 0) {
			sql.append(whereClause);
		}

		sql.append(" ORDER BY api.dateordered DESC, EXME_ActPaciente.EXME_ActPaciente_ID DESC,  ")
				.append(" apih.EXME_ActPacienteIndH_ID DESC,  api.EXME_ActPacienteInd_ID ASC, ")
				.append(" api.line ASC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			pstmt.setInt(index++, patientID);
			// Lama
			if(params.length > 0){
				for (Object param : params) {
					DB.setParameter(pstmt, index++, param);
				}
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MEXMEActPacienteInd serv = new MEXMEActPacienteInd(ctx, rs, trxName);
				lista.add(serv);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getServiciosLst", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}
	
	/**
	 * Busqueda de un producto en estatus de solicitud
	 * @param idProduct producto a buscar
	 * @return id de la linea de la solicitud
	 * @throws SQLException
	 */
	public static int getSolicitud(int idProduct, int idCtaPac) throws SQLException {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT actPacInd.EXME_ActPacienteInd_ID   ");
		sql.append(" FROM       EXME_ActPacienteInd  actPacInd ");
		sql.append(" INNER JOIN EXME_ActPacienteIndH actPacIndH  ON actPacInd.EXME_ActPacienteIndH_ID   = actPacIndH.EXME_ActPacienteIndH_ID ");
		sql.append(" WHERE actPacInd.isActive = 'Y' ");
		sql.append(" AND actPacInd.Estatus IN (").append(DB.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_RequestedService));
		sql.append(",").append(DB.TO_STRING(X_EXME_ActPacienteInd.ESTATUS_ScheduleService)).append(") ");
		sql.append(" AND actPacIndH.DocStatus IN (").append(DB.TO_STRING(X_EXME_ActPacienteIndH.DOCSTATUS_Drafted));
		sql.append(",").append(DB.TO_STRING(X_EXME_ActPacienteIndH.DOCSTATUS_InProgress)).append(") ");
		sql.append(" AND actPacIndH.EXME_CtaPac_ID = ? ");
		sql.append(" AND actPacInd.M_Product_ID = ? ");

		return DB.getSQLValueEx(null, sql.toString(), idCtaPac, idProduct);
	}
	
	
	/**
	 * Regresa los medicamentos prescritos en una cita.
	 * @param ctx Contexto
	 * @param exmeCitaMedicaID Id de la Cita
	 * @param trxName Nombre de la transaccion
	 * @return
	 */
	public static List<MEXMEActPacienteInd> getSavedMeds(Properties ctx,  int exmeCitaMedicaID, String trxName) {
		final StringBuilder sqlW = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder join = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		join.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = EXME_ActPacienteInd.exme_actpacienteindh_id) ");
		join.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)");
		join.append(" LEFT JOIN M_Product prod ON (EXME_ActPacienteInd.m_product_id = prod.m_product_id) ");//#1
		// where clause
		sqlW.append(" (prod.productclass = ? or exme_actpacienteind.exme_genproduct_id is not null) ");
		sqlW.append(" AND act.EXME_CitaMedica_ID=? ");//#2
		sqlW.append(" AND indh.isService='N' ");
		sqlW.append(" AND COALESCE(EXME_ActPacienteInd.exme_genproduct_id,EXME_ActPacienteInd.m_product_id) IS NOT NULL ");
		sqlW.append(" AND EXME_ActPacienteInd.estatus IN (");// 'S','P','C'
		sqlW.append(DB.TO_STRING(ESTATUS_ScheduleService)).append(",");
		sqlW.append(DB.TO_STRING(ESTATUS_RequestedService)).append(",");
		sqlW.append(DB.TO_STRING(ESTATUS_CompletedService)).append(")");

		List<MEXMEActPacienteInd> list = new Query(ctx, MEXMEActPacienteInd.Table_Name, sqlW.toString(), null)//
			.setJoins(join)//joins
			.setParameters(MProduct.PRODUCTCLASS_Drug, exmeCitaMedicaID)//parametros
			.setOnlyActiveRecords(true)// solo activos (actpacienteind.isactive='Y'
			.addAccessLevelSQL(true)// nivel de acceso
			.list();
		return list;
	}
	
	public static int getCountServices(final Properties ctx, final int exmeCtaPacId, final String[] prodClass,
			final Date dateIni, final Date dateFin, final String... estatus) {
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = getSQLServices(true, params, exmeCtaPacId, prodClass, dateIni, dateFin, estatus);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		return DB.getSQLValue(null, sql.toString(), params);
	}
	
	public static List<MEXMEActPacienteInd> getServices(final Properties ctx, final int exmeCtaPacId,
			final String[] prodClass, final Date dateIni, final String... estatus) {
		return getServices(ctx, exmeCtaPacId, prodClass, dateIni, null, estatus, null, null);
	}

	public static List<MEXMEActPacienteInd> getServices(final Properties ctx, final int exmeCtaPacId,
			final String[] prodClass, final Date dateIni, final Date dateFin, final String[] estatus,
			final String whereClause, final Object[] values) {
		final List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = MEXMEActPacienteInd.getSQLServices(false, params, exmeCtaPacId, prodClass, dateIni,
				dateFin, estatus);
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			if (StringUtils.isNotEmpty(whereClause)) {
				sql.append(whereClause);
				if(values!= null){
					for (Object object : values) {
						params.add(object);
					}
				}
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_ActPacienteInd.DatePromised ASC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			result = pstmt.executeQuery();

			while (result.next()) {
				list.add(new MEXMEActPacienteInd(ctx, result, null));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(result, pstmt);
		}
		return list;
	}

	public static StringBuilder getSQLServices(boolean count, 
			final List<Object> params, 
			final  int exmeCtaPacId, 
			final String[] prodClass,
			final Date dateIni,  final Date dateFin,
			final String[] estatus) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		if (count) {
			sql.append("count(EXME_ActPacienteInd.EXME_ActPacienteInd_ID) ");
		} else {
			sql.append("EXME_ActPacienteInd.* ");
		}
		sql.append("FROM  EXME_ActPacienteInd ");
		sql.append("INNER JOIN EXME_ActPacienteIndH H ON (EXME_ActPacienteInd.EXME_ActPacienteIndH_ID = H.EXME_ActPacienteIndH_ID ");
		//sql.append("INNER JOIN EXME_ActPaciente act ON (act.EXME_ActPaciente_ID = H.EXME_ActPaciente_ID");
		if (exmeCtaPacId > 0) {
			sql.append("  AND  h.EXME_CtaPac_ID     = ? ");// #1
			params.add(exmeCtaPacId);// #1
		}
		sql.append(" ) ");// #1

		// Prodclass
		if (prodClass != null && prodClass.length > 0) {
			sql.append("INNER JOIN M_Product prod ON (prod.M_Product_ID = EXME_ActPacienteInd.M_Product_ID");
			if (exmeCtaPacId > 0) {
				sql.append("  AND  prod.ProductClass  IN ( ").append(DB.TO_STRING(prodClass)).append(") ");
//				for (String pclass : prodClass) {
					params.addAll(Arrays.asList(prodClass));
//				}
			}
			sql.append(" ) ");// #1
		}

		sql.append("WHERE EXME_ActPacienteInd.IsActive='Y' ");
		sql.append("AND IsService = 'Y' ");
		
		if (dateIni != null && dateFin != null) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_ActPacienteInd.DatePromised, 'mi')");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('minute', EXME_ActPacienteInd.DatePromised)");
			}
			sql.append(" BETWEEN ? AND ? ");
			params.add(dateIni);
			params.add(dateFin);
		} else if (dateIni != null) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_ActPacienteInd.DatePromised, 'hh')");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('hour', EXME_ActPacienteInd.DatePromised)");
			}
			sql.append(" =? ");
			params.add(dateIni);
		} else if (dateFin != null) {
			if (DB.isOracle()) {
				sql.append("AND TRUNC(EXME_ActPacienteInd.DatePromised, 'hh')");
			} else if (DB.isPostgreSQL()) {
				sql.append("AND DATE_TRUNC('hour', EXME_ActPacienteInd.DatePromised) ");
			}
			sql.append(" <=? ");
			params.add(dateFin);
		}
		// status #6 ...
		if (estatus != null && estatus.length > 0) {
			sql.append("AND EXME_ActPacienteInd.Estatus IN (").append(DB.TO_STRING(estatus)).append(") ");
//			for (String status : estatus) {
			params.addAll(Arrays.asList(estatus));
//			}
		}
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		sql.append(" ORDER BY EXME_ActPacienteInd.DatePromised ASC ");
		return sql;
	}
	
	public String getPrefix(){
		final StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		str.append(getActPacIndH().get_ID());
		str.append("_");
		str.append(get_ID());
		return str.toString();
	}
	
	public String getMsgText(){
		final StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		str.append(Utilerias.getMsg(getCtx(), "surtPedido.documentNo")).append(": ");
		str.append(getActPacIndH().getDocumentNo());
		if (getProduct() != null) {
			str.append(" ");
			str.append(Utilerias.getMsg(getCtx(), "traspasos.product")).append(": ");
			str.append(getProduct().getName());
		}
		return str.toString();
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("");
		//	No Partner Info - set Template
		if (getQtyDelivered()==null || getQtyDelivered().compareTo(Env.ZERO)==0 && getQtyOrdered()!=null && getQtyOrdered().compareTo(Env.ZERO)==0 ){
			if(!getEstatus().equals(MEXMEActPacienteInd.ESTATUS_CancelledService)){
				if(getActPacienteIndH()!=null
						&& getActPacienteIndH().getDocStatus().equals(X_EXME_ActPacienteIndH.DOCSTATUS_Completed)){
					setQtyDelivered(getQtyOrdered());
				}
			}
		}
		return true;
	}	//	beforeSave
	
	/**
	 * Diagnosticos texto
	 * @return
	 */
	public String getDiagnosis1Text() {
		if (diagnosis1Text == null && get_ID() > 0) {
			diagnosis1Text = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID));
		}
		return diagnosis1Text;
	}

	public void setDiagnosis1Text(String diagnosis1Text) {
		this.diagnosis1Text = diagnosis1Text;
	}

	public String getDiagnosis2Text() {
		if (diagnosis2Text == null && get_ID() > 0) {
			diagnosis2Text = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico2_ID));
		}
		return diagnosis2Text;
	}

	public void setDiagnosis2Text(String diagnosis2Text) {
		this.diagnosis2Text = diagnosis2Text;
	}

	public String getDiagnosis3Text() {
		if (diagnosis3Text == null && get_ID() > 0) {
			diagnosis3Text = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico3_ID));
		}
		return diagnosis3Text;
	}

	public void setDiagnosis3Text(String diagnosis3Text) {
		this.diagnosis3Text = diagnosis3Text;
	}
	
	/**
	 * The diagnosis text is not saved in this table
	 * @param columnIdx
	 * @return
	 */
	private String getDiagnosisText(int columnIdx) {
		// The diagnosis text is not saved in this table
		final MActPacienteDiag actdiag = MActPacienteDiag.get(getCtx(), get_ID(), get_Table_ID(), getActPacienteID(),
				get_ValueAsInt(columnIdx), get_ColumnName(columnIdx), get_TrxName());
		return actdiag == null ? "" : actdiag.getDiagnosis() == null ? "" : actdiag.getDiagnosis();
	}
	
	
	/**  Obtiene el objeto MAttachment de la actividad */
	public MAttachment getAttachment() {
		if(attachment == null && !is_new()){
			attachment = MAttachment.get(getCtx(), get_Table_ID(), get_ID());
		}
		return attachment;
	}
	public void setAttachment(MAttachment attachment) {
		this.attachment = attachment;
	}
	
	public MEXMEMedico getMedico() {
		if (medico == null) {//RQM #4174
			int exmeMedicoId = 0;
			if (getEXME_Medico_ID() > 0) {
				exmeMedicoId = getEXME_Medico_ID();
			} else if (getActPacienteIndH().getEXME_Medico_ID() > 0) {
				exmeMedicoId = getActPacienteIndH().getEXME_Medico_ID();
			}
			if (exmeMedicoId > 0) {
				medico = new MEXMEMedico(getCtx(), exmeMedicoId, null);
			}
		}
		return medico;
	}
	
	/**
	 * Regresa una lista de objetos MDiagnostico
	 * con los diagnosticos presuntivos y los diagnosticos de resultados
	 * de la ActividadPacienteInd
	 */
	public List<MDiagnostico> getLstDiagnosis() {

		final List<MDiagnostico> lstDiag = new ArrayList<MDiagnostico>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT DIAG.* ");
		sql.append("FROM EXME_ACTPACIENTEIND IND ");
		sql.append("INNER JOIN EXME_ACTPACIENTEINDH HEAD ");
		sql.append("ON ( HEAD.EXME_ACTPACIENTEINDH_ID = IND.EXME_ACTPACIENTEINDH_ID ");
		sql.append("AND IND.EXME_ACTPACIENTEIND_ID    = ? ) ");// EXME_ActPacienteInd_ID
		sql.append("INNER JOIN EXME_ACTPACIENTEDIAG ADIAG ");
		sql.append("ON (ADIAG.EXME_ACTPACIENTE_ID = HEAD.EXME_ACTPACIENTE_ID ");
		sql.append("AND adiag.AD_Table_ID         = ? ");// EXME_ActPacienteInd
		sql.append("AND ADIAG.RECORD_ID           = IND.EXME_ACTPACIENTEIND_ID ");
		sql.append("AND ADIAG.isActive            = 'Y' ) ");
		sql.append("INNER JOIN EXME_DIAGNOSTICO DIAG ");
		sql.append("ON (ADIAG.EXME_DIAGNOSTICO_ID = DIAG.EXME_DIAGNOSTICO_ID)  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_ActPacienteInd_ID());
			pstmt.setInt(2, Table_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MDiagnostico diagnosis = new MDiagnostico(getCtx(), rs, get_TrxName());
				lstDiag.add(diagnosis);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "getLstDiagnosis", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiag;
	}

	/**
	 * DIFWKProducts
	 * @param paciente
	 * @param fecha1
	 * @param fecha2
	 * @param isNDC
	 * @return
	 * @deprecated Se usa para validar interacciones drug-drug, 
	 * los medicamentos se insertan en ActPaciente hasta que se administran, no es informacion correcta.
	 * Se debe obtener de PrescRXDet (Nimbo) o PlanMed (Cirrus). TODO: Mover de lugar correspondiente. 
	 * FIXME Errores:
	 * No filtra por ORG/CLIENT y solo considera Paciente (ClientToSystem)
	 * No considera los parametros fecha1, fecha2, solo toma la fecha actual
	 */
	public static String getDIFWKProductsValue(int paciente, Date fecha1, Date fecha2, boolean isNDC) {

		final StringBuilder lista = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		//if (isNDC) {
		sql.append("	SELECT ")
			.append(isNDC?"	     produ.PMID":"	gp.genproduct_id ")
			.append("	FROM  ")
			.append("	     EXME_ACTPACIENTE actpac")    
			.append("	     INNER JOIN EXME_ACTPACIENTEINDH actpacih ON actpac.EXME_ACTPACIENTE_ID = actpacih.EXME_ACTPACIENTE_ID")  
			.append("	     INNER JOIN EXME_ACTPACIENTEIND actpacind ON actpacih.EXME_ACTPACIENTEINDH_ID = actpacind.EXME_ACTPACIENTEINDH_ID")  
			.append("	     INNER JOIN M_PRODUCT produ ON actpacind.M_PRODUCT_ID = produ.M_PRODUCT_ID  ")
			.append(isNDC?"":"	INNER JOIN EXME_GENPRODUCT gp on produ.exme_genproduct_id = gp.exme_genproduct_id ")
			.append("	WHERE  ")
			.append("	     actpacih.ISSERVICE = 'N'")  
			.append("	     AND actpacih.EXME_TRATAMIENTOS_ID is null")  
			.append("	     AND actpac.EXME_PACIENTE_ID = ? ");
			if (DB.isOracle()) {
				sql.append("	     AND (TRUNC(actpacih.validFrom, 'DD') >= ?");
			} else if (DB.isPostgreSQL()) {
				sql.append("	     AND (DATE_TRUNC('DAY', actpacih.validFrom) >= ?");
			}
			sql.append("	     OR actpacih.validFrom IS NULL)");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, paciente);
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (lista.length() == 0) {
					lista.append(rs.getString(1));
				} else {
					lista.append("@@@").append(rs.getString(1));
				}
			}

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "getDIFWKProductsValue: sql: " + sql, e);
			slog.log(Level.SEVERE, "params: paciente:  " + paciente);
			slog.log(Level.SEVERE, "params: isNDC:  " + isNDC);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista.toString();

	}

	/**
	 * Gets the studies of a patient activity
	 * @return
	 */
	public boolean hasObx() {
		setHasOBX(getResultados() != null && !getResultados().isEmpty());
		return isHasOBX();
	}
	
	public List<MEstudiosOBX> getResultados() {
		if (resultados == null) {
			resultados = MEstudiosOBX.getEstudios(getCtx(), getEXME_ActPacienteInd_ID(), false, get_TrxName());
		}
		return resultados;
	}
	
	/**
	 * Id del registro
	 * @param actPacienteID
	 */
	public void setActPacienteID(int actPacienteID) {
		this.actPacienteID = actPacienteID;
	}

	/**
	 * buscamos el actpaciente con el actPacIndH
	 * @return
	 */
	public int getActPacienteID() {

		if (actPacienteID <= 0 && getEXME_ActPacienteIndH_ID() > 0) {
			// buscamos el actpaciente con el actPacIndH .- Lama
			final Object id = DB.getSQLValue(get_TrxName(), //
					"select EXME_ActPaciente_ID from EXME_ActPacienteIndH where EXME_ActPacienteIndH_ID=?",//
					getEXME_ActPacienteIndH_ID());
			if (id instanceof Integer) {
				actPacienteID = (Integer) id;
			} else if (id instanceof BigDecimal) {
				actPacienteID = ((BigDecimal) id).intValue();
			} else if(id == null){// lhernandez.12042011 : validar que no sea null
				actPacienteID =  0 ;
			} else {
				actPacienteID = Integer.parseInt(id.toString());
			}
		}
		return actPacienteID;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			// si no se capturan diagnosticos no entra al metodo .- Lama
			if (newRecord) {
				success = saveDiag(newRecord);
			}
			if (ESTATUS_CompletedService.equalsIgnoreCase(getEstatus())){
				for (Integer modifierID : MEXMEProductoOrg.getModifiers(Env.getCtx(), getM_Product_ID(), null)){
					if (MEXMEClaimCodes.get(Env.getCtx(), getActPacienteIndH().getCtaPac().getEXME_CtaPac_ID(),
							MEXMEModifiers.Table_ID, modifierID, Table_ID, getEXME_ActPacienteInd_ID(), get_TrxName()).size()<=0){
						X_EXME_ClaimCodes reg = new X_EXME_ClaimCodes(Env.getCtx(), 0, get_TrxName());
						reg.setAD_Table_ID(MEXMEModifiers.Table_ID);
						reg.setRecord_ID(modifierID);
						reg.setEXME_Paciente_ID(getActPacienteIndH().getCtaPac().getEXME_Paciente_ID());
						reg.setEXME_CtaPac_ID(getActPacienteIndH().getCtaPac().getEXME_CtaPac_ID());
						reg.setAD_TableOrig_ID(Table_ID);
						reg.setRecordOrig_ID(getEXME_ActPacienteInd_ID());
						if (!reg.save()){
							log.log(Level.SEVERE, "It was not possible to copy the modifiers from the ChargeMaster for ActPacienteInd_ID = "+getEXME_ActPacienteInd_ID());
						}
					}
				}
			}
		}
		return success;
	}

	/**
	 * Save/Update diagnostic of patient
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author Lorena Lama
	 */
	public boolean saveDiag(boolean newRecord) {
		
		boolean saved;
		if (getActPacienteID() <= 0) { // find actPaciente
			saved = false;
		} else {
			saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID), diagnosis1Text);
			if (saved) {
				saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico2_ID), diagnosis2Text);
			}
			if (saved) {
				saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico3_ID), diagnosis3Text);
			}
		}
		return saved;
	}
	
	/**
	 * Save/Update diagnostic of patient
	 * @param newRecord
	 * @param columnIdx
	 * @param diagnosis
	 * @return
	 */
	private boolean saveDiagnosis(boolean newRecord, int columnIdx, String diagnosis) {
		boolean saveDiag;
		boolean saved;
		if (newRecord) {
			// if has a diagnostic
			saveDiag = get_ValueAsInt(columnIdx) > 0 || StringUtils.isNotBlank(diagnosis);
		} else {
			// if diagnosis code has changed or diagnosis descriptions has been assigned
			saveDiag = is_ValueChanged(columnIdx) || StringUtils.isNotBlank(diagnosis);
		}
		saved = !saveDiag;
		if (saveDiag) {
			// The diagnosis text is not saved in this table
			saved = MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), get_ColumnName(columnIdx), diagnosis,
					null);
		}
		return saved;
	}
	
	/**
	 * Gets the studies of a patient activity
	 * @return
	 */
	public boolean isHasOBX() {
		return hasOBX;
	}

	public void setHasOBX(boolean hasOBX) {
		this.hasOBX = hasOBX;
	}
	
	public void setHasOBX() {
		try {
			// Se agrega la propiedad para observaciones de laboratorio
			this.hasOBX = MEstudiosOBX.hasOBX(getCtx(), getEXME_ActPacienteInd_ID());
		} catch (Exception e) {
			this.hasOBX = false;
			log.log(Level.SEVERE, "setHasOBX", e);
		}
	}
	
	/**
	 * Nombre de la unidad de medida
	 * @return
	 */
	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	
	/**
	 * Propiedad para almacenar el monto correspondiente 
	 * por impuesto total de la linea  
	 * (SOLO SE MANTENDRA EN MEMORIA)
	 */
	public BigDecimal totalImp = Env.ZERO;

	public BigDecimal getTotalImp() {
		return totalImp;
	}

	public void setTotalImp(BigDecimal totalImp) {
		this.totalImp = totalImp;
	}
	
	/**
	 * maneja HL7
	 * @return
	 */
	public boolean isManejaHL7() {
		return manejaHL7;
	}

	public void setManejaHL7(boolean manejaHL7) {
		this.manejaHL7 = manejaHL7;
	}

	/**
	 * Almacen Objeto
	 * @return
	 */
	public MWarehouse getAlmacen() {
		if (almacen == null && getM_Warehouse_ID() > 0){
			almacen = new MWarehouse(getCtx(), getM_Warehouse_ID(), null);
		}
		return almacen;

	}
	/**
	 * almacen identificador
	 * @return
	 */
	public int getAlmacenID() {
		return getM_Warehouse_ID();
	}

    /**
    * Obtiene una lista con el detalle de una solicitud de servicio especifica
    * @param pedido El identificador de la solicitud
    * @return Una lista de objetos ActPacienteIndView cantSurtir
    */
    public static List<ActPacienteIndView> getLineasServicio(Properties ctx, long pedido, String trxName) throws Exception{
        final List<ActPacienteIndView> resultados = new ArrayList<ActPacienteIndView>();
        
        final StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
        
         sql.append("SELECT EXME_ActPacienteInd.*, ")
         	.append(" Nvl(prd.Description,prd.name) AS producto, ")
         	.append(" uom.name AS uom_name, ")
            .append(" prd.Value AS valProd ")
            .append("FROM EXME_ActPacienteInd ")
            .append(" JOIN M_PRODUCT prd ON (EXME_ActPacienteInd.m_product_id = prd.m_product_id) ")
            .append(" LEFT JOIN C_UOM uom ON (uom.c_uom_id = EXME_ActPacienteInd.c_uom_id) ")
            .append("WHERE EXME_ActPacienteInd.EXME_actpacienteindh_id = ? ");
        
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));   
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            pstmt.setLong(1, pedido);
            rs = pstmt.executeQuery();
            slog.log(Level.INFO, "SQL MEXMEActPacienteInd.getLineasServicio", sql.toString());
            slog.log(Level.INFO, "SQL Params pedido:", pedido);
            
            while(rs.next()){
				final ActPacienteIndView view = new ActPacienteIndView();
				final MEXMEActPacienteInd model = new MEXMEActPacienteInd(ctx, rs, null);// Lama

				view.setFromModel(model);
//				view.setXXActPacienteIndHID(model.getEXME_ActPacienteIndH_ID());
//				view.setXXActPacienteIndID(model.getEXME_ActPacienteInd_ID());
//				view.setLinea(model.getLine());
//				view.setCantPedida(model.getQtyOrdered().intValue());
//				view.setDescripcion(model.getDescription());
//				view.setFechaOrd(model.getDateOrdered());
//				view.setPrecio(model.getPriceActual().doubleValue());
//				view.setCantSurtir(model.getQtyOrdered().intValue());
//				view.setAnotaciones(model.getAnotaciones());
//				final String imagen = model.getImagen();
//				if (imagen != null) {
//					view.setConFoto(true);
//					view.setLigaFoto("/eCareSoftWeb/ece/imagenes/" + imagen);
//				}
////			view.setFolio(model.getFolio());
//				view.setSurtir(model.isSurtir());
//				view.setSurtido(ESTATUS_CompletedService.equals(model.getEstatus()));
//				view.setActivo(model.isActive());
//				if (model.getEstatus() == null) {
//					view.setAccion(ESTATUS_RequestedService);
//				} else {
//					view.setAccion(model.getEstatus());
//				}
				view.setUom(rs.getString("uom_name"));
				view.setProductoValue(rs.getString("valProd"));
                resultados.add(view);
            }
        }catch(SQLException sqle){
        	throw sqle;
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return resultados;
    }
    
	/**
	 * Creamos una linea de actividad paciente apartir de un cargo Se utiliza en cargos a cuenta paciente
	 * 
	 * @param cargo
	 * @param estSer
	 * @param actPacienteIndH
	 */
	public void createLine(MCtaPacDet cargo, MEXMEEstServ estSer, MEXMEActPacienteIndH actPacienteIndH) {

		setAD_OrgTrx_ID(cargo.getAD_OrgTrx_ID());
		setTipoArea(cargo.getTipoArea());
		setEXME_Area_ID(cargo.getEXME_Area_ID());

		if (actPacienteIndH != null) {
			setEXME_ActPacienteIndH_ID(actPacienteIndH.getEXME_ActPacienteIndH_ID());
		}

		setC_Currency_ID(cargo.getC_Currency_ID());
		setC_Tax_ID(cargo.getC_Tax_ID());
		setC_UOM_ID(cargo.getC_UOM_ID());
		setChargeAmt(cargo.getChargeAmt());
		setCosto(cargo.getCosto());
		setDateDelivered(cargo.getDateDelivered());
		setDateOrdered(cargo.getDateOrdered());
		setDatePromised(cargo.getDatePromised());
		setDescription(cargo.getDescription());
		setDiscount(cargo.getDiscount());// Monto
		setFreightAmt(cargo.getFreightAmt());
		setIsActive(true);
		setIsDescription(false);
		setLine(cargo.getLine());
		setLineNetAmt(cargo.getLineNetAmt());
		setM_InOutLine_ID(cargo.getM_InOutLine_ID());
		setM_Product_ID(cargo.getM_Product_ID());
		setM_Shipper_ID(cargo.getM_Shipper_ID());
		setM_Warehouse_ID(cargo.getM_Warehouse_ID());
		setPriceActual(cargo.getPriceActual());
		setPriceLimit(cargo.getPriceLimit());
		setPriceList(cargo.getPriceList());
		setQtyDelivered(cargo.getQtyDelivered());
		setQtyInvoiced(cargo.getQtyInvoiced());
		setQtyOrdered(cargo.getQtyOrdered());
		setQtyReserved(cargo.getQtyReserved());
		setSurtir(true);

	}
	
	/**
	 * Cadena del objeto
	 */
	public String toString() {
		final StringBuilder str = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (getProduct() != null) {
			str.append(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_M_Product_ID)).append(": ")
					.append(getProduct().getName());
		}
		if (getUom() != null) {
			str.append(", UOM: ").append(getUom().getName());
		}
		str.append(", QtyOrdered: ").append(getQtyOrdered());
		str.append(", QtyDelivered: ").append(getQtyDelivered());
		str.append(", QtyInvoiced: ").append(getQtyInvoiced());
		return str.toString();
	}
	
	/**
	 * Encabezado de la indicacion medica
	 * @param actPacienteIndH
	 */
	public void setActPacienteIndH(MEXMEActPacienteIndH actPacienteIndH) {
		// Establecemos valores a partir del Header de la Indicaci�n.
		if (actPacienteIndH != null) {
			this.actPacIndH = actPacienteIndH;
			setEXME_ActPacienteIndH_ID(actPacIndH.getEXME_ActPacienteIndH_ID());
			setDateOrdered(actPacIndH.getDateOrdered());
			setM_Warehouse_ID(actPacIndH.getM_Warehouse_ID());
		}
	}
	
	/**
	 * Encabezado de la indicacion
	 * @return
	 */
	public MEXMEActPacienteIndH getActPacienteIndH() {
		if (actPacIndH == null || actPacIndH.getEXME_ActPacienteIndH_ID()<=0) {
			actPacIndH = new MEXMEActPacienteIndH(getCtx(), getEXME_ActPacienteIndH_ID(), get_TrxName());
		}
		return actPacIndH;
	}
	
	/**
	 * Unidad de medida
	 * @return Objeto MUOM
	 */
	public MUOM getUom() {
		if (uom == null && getC_UOM_ID() > 0) {
			uom = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
		}
		return uom;
	}

	/**
	 * Enzabezado de la indicacion medica
	 * @return Objeto MEXMEActPacienteIndH
	 */
	public MEXMEActPacienteIndH getActPacIndH() {
		return getActPacienteIndH();
	}
	
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return getDescription();
	}

	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		setDescription(descripcion);
	}
	
	  /**
     * GenProduct
     * @return
     */
	public MEXMEGenProduct getGenProduct() {
		if (genProduct == null && getEXME_GenProduct_ID() > 0) {
			genProduct = new MEXMEGenProduct(getCtx(), getEXME_GenProduct_ID(), get_TrxName());
		}
		return genProduct;
	}
	
	/**
	 * Diagnostico
	 * @return
	 */
	public MDiagnostico getDiag1() {
		if (diag1 == null && getEXME_Diagnostico_ID() > 0) {
			diag1 = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), get_TrxName());
		}
		return diag1;
	}

	/**
	 * Diagnostico
	 * @return
	 */
	public MDiagnostico getDiag2() {
		if (diag2 == null && getEXME_Diagnostico2_ID() > 0) {
			diag2 = new MDiagnostico(getCtx(), getEXME_Diagnostico2_ID(), get_TrxName());
		}
		return diag2;
	}

	/**
	 * Diagnostico
	 * @return
	 */
	public MDiagnostico getDiag3() {
		if (diag3 == null && getEXME_Diagnostico3_ID() > 0) {
			diag3 = new MDiagnostico(getCtx(), getEXME_Diagnostico3_ID(), get_TrxName());
		}
		return diag3;
	}
	
    /**
     * Product
     * @return
     */
	public MProduct getProduct() {
		if (getM_Product_ID() > 0 && (product == null || getM_Product_ID() != product.getM_Product_ID())) {
			product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return product;
	}
	
	 /**
     * CPT
     * @return
     */
	public MIntervencion getCPT() {
		if (cpt == null && product != null && product.getEXME_Intervencion_ID() > 0) {
			cpt = new MIntervencion(getCtx(), product.getEXME_Intervencion_ID(), get_TrxName());
		}
		return cpt;
	}
	
	/**
	 * Nombre del servicio
	 * @return
	 */
	public String getNameServ() {
		return nameServ;
	}

	public void setNameServ(String nameServ) {
		this.nameServ = nameServ;
	}

	/*@Override
	public void setDocStatus(String newStatus) {
		if (DocAction.STATUS_Completed.equals(newStatus)) {
			setEstatus(ESTATUS_CompletedService);
		} else if (DocAction.STATUS_Drafted.equals(newStatus)) {
			setEstatus(ESTATUS_RequestedService);
		} else if (DocAction.STATUS_InProgress.equals(newStatus)) {
			setEstatus(ESTATUS_ScheduleService);
		} else if (DocAction.STATUS_Voided.equals(newStatus)) {
			setEstatus(ESTATUS_CancelledService);
		} 
	}

	@Override
	public String getDocStatus() {
		// por defcto Drafted
		String status = DocAction.STATUS_Drafted;
		if (getDateDelivered() != null) {
			// si ya se ejecuto el metodo completeIt se llena la fecha de envio
			status = DocAction.STATUS_Completed;
		} else if (ESTATUS_CancelledService.equals(getEstatus())) {
			if (isActive()) {
				// para no pasar por el prepareit
				status = DocAction.STATUS_WaitingConfirmation;
			} else {
				// si ya se ejecuto voidit se desactiva el registro
				status = DocAction.STATUS_Voided;
			}
		} else if (ESTATUS_ScheduleService.equals(getEstatus())) {
			status = DocAction.STATUS_InProgress;
		}
		return status;
	}*/

	@Override
	public boolean processIt(String action) throws Exception {
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		return false;
	}

	@Override
	public boolean invalidateIt() {
		return false;
	}

	@Override
	public String prepareIt() {
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		return false;
	}

	@Override
	public boolean rejectIt() {
		return false;
	}

	@Override
	public boolean closeIt() {
		return true;
	}

	@Override
	public boolean reverseCorrectIt() {
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		return false;
	}

	@Override
	public boolean reActivateIt() {
		return false;
	}

	@Override
	public String getSummary() {
		return null;
	}

	@Override
	public String getDocumentNo() {
		return getActPacienteIndH().getDocumentNo();
	}

	@Override
	public String getDocumentInfo() {
		return null;
	}

	@Override
	public File createPDF() {
		return null;
	}

	@Override
	public String getProcessMsg() {
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		return null;
	}

	/*@Override
	public String getDocAction() {
		String action = DocAction.ACTION_None;
		if (ESTATUS_CompletedService.equals(getEstatus())) {
			action = DocAction.ACTION_Complete;
		} else if (ESTATUS_CancelledService.equals(getEstatus())) {
			action = DocAction.ACTION_Void;
		} else if (ESTATUS_RequestedService.equals(getEstatus())) {
			action = DocAction.ACTION_Approve;
		} else if (ESTATUS_ScheduleService.equals(getEstatus())) {
			action = DocAction.ACTION_Prepare;
		}
		return action;
	}*/

	@Override
	public String completeIt() {
		if (MEXMEActPacienteInd.ESTATUS_CancelledService.equals(getEstatus())) {
			voidIt();
		} else if (ESTATUS_CompletedService.equals(getEstatus())) {
			// Warehouse that applies
			if (getM_Warehouse_ID() <= 0) {
				setM_Warehouse_ID(Env.getM_Warehouse_ID(getCtx()));
			}
			// Qty Ordered
			if (Env.ZERO.compareTo(getQtyOrdered()) >= 0) {
				setQtyOrdered(Env.ONE);
			}
			// Qty Delivered
			if(Env.ZERO.compareTo(getQtyDelivered()) >= 0) {
				setQtyDelivered(getQtyOrdered());
			}
			// Date Delivered
			if(getDateDelivered() == null) {
				setDateDelivered(Env.getCurrentDate());
			}
			String priceList;
			if (getActPacIndH().getCtaPac().getEXME_CtaPac_ID() <= 0
				|| !X_EXME_CtaPac.ENCOUNTERSTATUS_Admission.equals(getActPacIndH().getCtaPac().getEncounterStatus())) {
				priceList = Constantes.PVH;
			} else {
				priceList = Constantes.PVCE; // Proviene de Consulta Externa.
			}
			// Expert. Precios en cero
			if(getTipoArea()==null || getEXME_Area_ID()==0){
				final MEXMEEstServ estServ = MEXMEEstServ.getFromCtx(getCtx());
				setTipoArea(getTipoArea()==null?estServ.getTipoArea():getTipoArea());
				setEXME_Area_ID(getEXME_Area_ID()==0?estServ.getEXME_Area_ID():getEXME_Area_ID());
			}
			
//			final MPrecios precios = PreciosVenta.precioProdVta(getCtx(), //
//				estServ.getTipoArea(),//
//				getM_Product_ID(), //
//				getQtyDelivered(), //
//				getC_UOM_ID(),//
//				priceList, 0, 0, //
//				getActPacIndH().getM_Warehouse_Sol_ID(), //
//				getActPacIndH().getM_Warehouse_ID(), //
//				estServ.getEXME_Area_ID(), //
//				getActPacIndH().getDateOrdered(),//
//				false, null);
			// Precios
			final MPrecios precios = GetPrice.getPriceActPac(this);
			
			// si tiene precio,
			boolean retValue = precios.getPriceList().compareTo(Env.ZERO) > 0 ||
			// y no esta configurado que aplique sin precios
				(getConfigPre() != null && getConfigPre().isAplicaServSinPrec());
			
			setDocStatus(DOCSTATUS_Completed);
			setDocAction(DOCACTION_None);
			
			if (!retValue || !save()) {
				return DocAction.STATUS_Invalid;
			}
		}
		return getDocStatus();
	}

	@Override
	public boolean voidIt() {
		setEstatus(ESTATUS_CancelledService);
		setIsActive(false);
		setDocAction(DOCACTION_None);
		setDocStatus(DOCSTATUS_Voided);
		return save();
	}
	
	public MEXMEConfigPre getConfigPre() {
		if (configPre == null) {
			configPre = MEXMEConfigPre.get(getCtx(), null);
		}
		return configPre;
	}

	public void setConfigPre(MEXMEConfigPre configPre) {
		this.configPre = configPre;
	}
	
	/**
	 * crea el cargo
	 * 
	 * @return
	 */
	public boolean createCharge(final Properties ctx, final int ctaPacID, final BeanActPacienteInd bean, String trxName) {
		boolean retValue = false;
//		if (mCreateCharge == null) { // si es item lo vuelve a crear
//			mCreateCharge = new CreateCharge(getCtx());
//		}
		try {
			// Si es medicamento
			if (getProduct().isItem()){
				retValue = createChargeMed(getCtx(), ctaPacID, bean, trxName);
			} else {
				retValue = createActPacIndCharges(trxName);//Lama: cargos 2014
			}
		} catch (Exception pre_e) {
			log.log(Level.SEVERE, Utilerias.getAppMsg(getCtx(), "error.servicios.noPrice", getProductName()));
		}
		return retValue;
	}
	
//	/**
//	 * Obj CreateCharge
//	 * @return CreateCharge mCreateCharge
//	 */
//	public CreateCharge getmCreateCharge() {
//		return mCreateCharge;
//	}
//	public void setmCreateCharge(CreateCharge mCreateCharge) {
//		this.mCreateCharge = mCreateCharge;
//	}
	
	/**
	 * Marcar las referencias
	 * @param ctx
	 * @param indica
	 * @param encab
	 * @param trxName
	 */
	public void referenciar(final Properties ctx, final ServicioView indica, final MEXMEActPacienteIndH encab, final String trxName){
		if (indica.getEXME_ActPacienteInd_ID() > 0) {
			MEXMEActPacienteInd lineOrig = new MEXMEActPacienteInd(ctx, indica.getEXME_ActPacienteInd_ID(), trxName);

			MEXMEActPacienteIndH headerOrig = new MEXMEActPacienteIndH(ctx, lineOrig.getEXME_ActPacienteIndH_ID(),
					trxName);
			headerOrig.setRef_ActPacienteIndH_ID(encab.getEXME_ActPacienteIndH_ID());

			if (!headerOrig.save()) {
				slog.log(Level.SEVERE, "Can not save headerOrig", CLogger.retrieveError());// TODO Revisar
			}
		}
	}
	
	/**
	 * Fechas
	 * @param indica
	 */
	public void setDates(final ServicioView indica){
		setDateOrdered(indica.getFecha() == null ? Env.getCurrentDate() : indica.getFecha());
		setDateDelivered(indica.getFechaApli() == null ? Env.getCurrentDate() : indica.getFechaApli());
	}
	
	public void setDescription(final ServicioView indica, final String cantTomar, final String vecesDia, final String numDias){
		setDescription(cantTomar + ": " + indica.getCantTomar()
				+ ". " + vecesDia + ": " + indica.getVecesDia() + ". "
				+ numDias + ": " + indica.getNumDias() + ". "
				+ indica.getDescripcion());
	}
	
	/**
	 * Precios de la actividad paciente
	 * @param encab
	 * @param indica
	 * @return
	 */
	public MEXMEActPacienteInd setPrices(final MEXMEActPacienteIndH encab, final ServicioView indica ){
		if (getM_Product_ID() > 0 && indica.getSurtir()) {
//			MPrecios precios = PreciosVenta.precioProdVta(getCtx(),
//					getTipoArea(), getM_Product_ID(),
//					new BigDecimal(indica.getCantidad()),
//					getC_UOM_ID(), PreciosVenta.PVCE,
//					encab.getM_PriceList_ID(), encab.getC_BPartner_ID(),// TODO: CARD:XXX Revisar que ambas propiedades tengan el valor correcto
//					encab.getM_Warehouse_Sol_ID(),
//					encab.getM_Warehouse_ID(), getEXME_Area_ID(),
//					getDateOrdered(), true, null);
			
			MPrecios precios = GetPrice.getPriceActPac(this);
			return precios.updatePrices(this);
		}
		return null;
	}
	
	public void getProdRevCode(final ServicioView indica ){
		// lhernandez.12042011: se agrega genProduct
		if ((int) indica.getProdID() > 0) {
			MProduct prod = new MProduct(getCtx(), (int) indica.getProdID(), null);
			setEXME_GenProduct_ID(prod.getEXME_GenProduct_ID());
			if (prod.getProdOrg() != null) {
				setEXME_RevenueCode_ID(prod.getProdOrg().getEXME_RevenueCode_ID());
			}
		} else {
			setEXME_GenProduct_ID(indica.getEXME_GenProduct_ID());
		}
	}
	
	
	/**
	 * Creacion de cargos en batch 
	 * tanto para servicios como medicamentos planeados
	 * @param lst
	 * @return
	 */
	private boolean createChargeMed(final Properties ctx, final int ctaPacID, final BeanActPacienteInd bean, String trxName) {
		// Ejecuta la carga de productos
		CreateCharge mCreateCharge =  new CreateCharge(ctx);
		// Ejecuta la creacion del expediente y la carga del medicamento
		MedicationSave mMedicationSave =  new MedicationSave(ctx,new MEXMECtaPac(ctx, ctaPacID,null),trxName);

		// Programaciones medicas
		if (bean.getEXME_ActPacienteInd_ID() <= 0 && bean.getPlanMed() != null && bean.getPMLine() != null) {
			mMedicationSave.insertActPacIndH(bean.getPlanMed(), bean.getPMLine());
		} else if (bean.getEXME_ActPacienteInd_ID() > 0) {
			mCreateCharge.insertActPacIndCharges(bean, false, false, trxName);//Lama: cargos 2014
		}

		// aplica los medicamentos/studios con expdiente previo
		if(!mCreateCharge.getLstCharges().isEmpty() ){//Lama: cargos 2014
			mCreateCharge.insertAllCharges(mCreateCharge.getLstCharges(), /* true, false, false, false,*/ trxName);
		}

		lstErrors.addAll(mCreateCharge.getErrores());
		lstErrors.addAll(mMedicationSave.getErrores());
		return true;
	}
	
	
	public List<ModelError> getLstErrors() {
		return lstErrors;
	}

	public void setLstErrors(List<ModelError> lstErrors) {
		this.lstErrors = lstErrors;
	}
	
	/**
	 * Regresa el cargo de una indicacion medica
	 * @return
	 */
	public MCtaPacDet getCargo() {
		
		if(cargo==null){
			final StringBuilder sql = new StringBuilder();
			sql.append(" EXME_ActPacienteInd_ID=? ");

			cargo = new Query(getCtx(), MCtaPacDet.Table_Name, sql.toString(), null)//
			.setParameters(getEXME_ActPacienteInd_ID())
			.setOnlyActiveRecords(true)
			.addAccessLevelSQL(true).first();
		}
		return cargo;
	}

	@Override
	public String getPatientName() {
		if (getEXME_ActPacienteIndH_ID() > 0 && getActPacienteIndH().getEXME_CtaPac_ID() > 0 && getActPacienteIndH().getCtaPac() != null) {
			return getActPacienteIndH().getCtaPac().getPatientName();
		} else {
			return "";
		}
	}

	@Override
	public String getSummaryDetail() {
		final StringBuilder str = new StringBuilder();
		if (getM_Product_ID() > 0 && getProduct() != null) {
			str.append("* ");
			str.append(getProduct().getName());
		}
		if (StringUtils.isNotBlank(getEstatus())) {
			str.append("<i> (");
			str.append(MRefList.getListName(getCtx(), MEXMEActPacienteInd.ESTATUS_AD_Reference_ID, getEstatus()));
			str.append(")</i>");
		}
		// dates
		if (getDateOrdered() != null) {
			str.append("<br><b>").append(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_DateOrdered)).append("</b>: ");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getDateOrdered()));
		}
		return str.toString();
	}

	@Override
	public boolean setAction(String action) {
		if (ACTION_AUTHENTICATE.equals(action)) {
//			approveIt();TODO
		} else if (ACTION_DISCONTINUE.equals(action)) {
			return voidIt();
		} else if (ACTION_REVIEW.equals(action)) {
			setIsOBXReviewed(true);//TODO
			return save();
		}
		return true;
	}
	
	@Override
	public String getRecordType() {
		if (getM_Product_ID() > 0 && getProduct() != null) {
			return MRefList.getListName(getCtx(), MProduct.PRODUCTCLASS_AD_Reference_ID, getProduct().getProductClass());
		} else {
			return "";
		}
	}
	 public static final String COLUMNNAME_RESULTED = "Resulted";
	 public static final String COLUMNNAME_RESULTED_BY = "ResultedBy";
	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (type == MEXMEPaciente.COLUMNNAME_Nombre_Pac.hashCode()) {
			comparable = getPatientName();
		} else if (type == MEXMEActPacienteIndH.COLUMNNAME_EXME_CtaPac_ID.hashCode()) {
			comparable = getEncounter();
		} else if (type == COLUMNNAME_DateOrdered.hashCode()) {
			comparable = getDateOrdered();
		} else if (type == COLUMNNAME_DateDelivered.hashCode()) {
			comparable = getDateDelivered();
		} else if (type == COLUMNNAME_M_Product_ID.hashCode()) {
			comparable = getProductName();
		} else if (type == COLUMNNAME_Estatus.hashCode()) {
			comparable = getEstatusName();
		} else if (type == COLUMNNAME_IsExternal.hashCode()) {
			comparable = getExternalBillable();
		} else if (type == COLUMNNAME_DatePromised.hashCode()) {
			comparable = getDatePromised();
		} else if (type == MBPartner.COLUMNNAME_C_BPartner_ID.hashCode()) {
			comparable = getPrimaryPayer();
		} else if (type == MEXMEHistExp.COLUMNNAME_DocumentNo.hashCode()) {
			comparable = getMrn();
		} else if (type == MEXMEActPacienteIndH.COLUMNNAME_MedicoNombre.hashCode()) {
			comparable = getMedicoName();
		} else if (type == COLUMNNAME_PriorityRule.hashCode()) {
			comparable = getPriorityRuleStr();
		}else if(type == COLUMNNAME_EXME_Diagnostico_ID.hashCode()){
			comparable = getResultedDate();
		}else if(type == COLUMNNAME_RESULTED.hashCode()){
			comparable = getResultedBy();
		}else if(type == COLUMNNAME_RESULTED_BY.hashCode()){
			comparable = getDiagnosisStr();
		} else {
			comparable = getActPacienteIndH().onCompare(type);
		}
		return comparable;
	}

	public String getProductName() {
		if (getM_Product_ID() <= 0 || getProduct() == null) {
			return "";
		}
		return getProduct().getName();
	}

	public String getEstatusName() {
		// si es externa
		if(!isSurtir() && getDateDelivered() == null && ESTATUS_CompletedService.equals(getEstatus())){
			return Utilerias.getMsg(getCtx(), "egresos.rm.status.transferencia.pendiente");
		} 
		// si el header está cancelado, pero el detalle no RQM #4168
		if (!ESTATUS_CancelledService.equals(getEstatus())
			&& (DOCSTATUS_Voided.equals(getDocStatus()) || DOCSTATUS_Voided.equals(getActPacienteIndH().getDocStatus()))) {
			return MRefList.getListName(getCtx(), ESTATUS_AD_Reference_ID, ESTATUS_CancelledService);
		}
		return MRefList.getListName(getCtx(), ESTATUS_AD_Reference_ID, super.getEstatus());
	}

	public String getExternalBillable() {
		StringBuilder str = new StringBuilder();
		if (!super.isSurtir()) {
			if (super.isBillable()) {
				str.append(Utilerias.getMsg(getCtx(), "msj.extBill.abrev"));
			} else {
				str.append(Msg.translate(Env.getAD_Language(getCtx()), COLUMNNAME_IsExternal));
			}
		}
		return str.toString();
	}

	public String getPrimaryPayer() {
		
		if (getEXME_ActPacienteIndH_ID() > 0 && getActPacienteIndH().getEXME_CtaPac_ID() > 0) {
			if(!MCountry.isMexico(getCtx())){	
			final StringBuilder str = new StringBuilder();
			// exme_pacienteaseg activo de la cuenta x priority = 1 supportbilling =Professional (nimbo)
			final List<MEXMEPacienteAseg> lst = MEXMEPacienteAseg.getAllbyCtaPac(getCtx(), getActPacienteIndH().getEXME_CtaPac_ID(), " Priority=1 ");
			if (lst.isEmpty()) {
				// cuando no tiene ninguno, la aseguradora es la que esta en exme_paciente.c_bpartner_id
				final MBPartner partner = getActPacienteIndH().getCtaPac().getPaciente().getPatientBPartner();
				str.append(partner.getName());
				if (StringUtils.isNotBlank(partner.getSupportBilling())) {
					str.append(" (").append(partner.getSupportBillingStr()).append(")");
				}
			} else {
				// puede tener hasta 2 primary payer uno para inst y otro para prof,
				for (MEXMEPacienteAseg aseg : lst) {
					if (str.length() > 0) {
						str.append(", ");
					}
					final MBPartner partner = (MBPartner) aseg.getC_BPartner();
					str.append(partner.getName());
					if (StringUtils.isNotBlank(partner.getSupportBilling())) {
						str.append(" (").append(partner.getSupportBillingStr()).append(")");
					}
				}
			}
			return str.toString();
		}else{
			 MEXMEPacienteAseg Aseg = MEXMEPacienteAseg.getAseguradora(getCtx(),getActPacienteIndH().getPaciente().getEXME_Paciente_ID());
	
				if(Aseg!=null){
					return Aseg.getC_BPartner().getName();
				}else{
					return "";
				}
			}
		
		}
		return "";
	}

	public String getEncounter() {
		if (getEXME_ActPacienteIndH_ID() > 0 && getActPacienteIndH().getEXME_CtaPac_ID() > 0) {
			return getActPacienteIndH().getCtaPac().getDocumentNo();
		} else {
			return "";
		}
	}

	public String getMrn() {
		if (getEXME_ActPacienteIndH_ID() > 0 && getActPacienteIndH().getEXME_CtaPac_ID() > 0 && getActPacienteIndH().getCtaPac() != null) {
			return getActPacienteIndH().getCtaPac().getPaciente().getMRN();
		} else {
			return "";
		}
	}

	public String getMedicoName() {
		String medico;
		//Obtiene nombre medico de nimboo
		if (getEXME_Medico_ID() > 0) {
			medico = getEXME_Medico().getNombre_Med();
		}
		//nombre medico que solicita
		else if(getActPacienteIndH().getEXME_MedicoSol_ID()>0){
			medico =   MEXMEMedico.nombreMedico(getCtx(), getActPacienteIndH().getEXME_MedicoSol_ID());
		}else{
			medico="";
		}
		return medico;
	}
	
	public String getDiagnosisStr() {
		final StringBuilder str = new StringBuilder();
		if (getDiag1() != null) {
			str.append(getDiag1().getName());
		}
		if (getDiag2() != null) {
			if (str.length() > 0) {
				str.append(", ");
			}
			str.append(getDiag2().getName());
		}
		if (getDiag3() != null) {
			if (str.length() > 0) {
				str.append(", ");
			}
			str.append(getDiag3().getName());
		}
		return str.toString();
	}

	
	@Override
	public void setDateDelivered(Timestamp DateDelivered) {
		super.setDateDelivered(DateDelivered);
		
		if(DateDelivered != null && is_ValueChanged(COLUMNNAME_DateDelivered) && getDeliveredBy() <= 0){
			super.setDeliveredBy(Env.getAD_User_ID(getCtx()));
		}
	}
	
	public String getPriorityRuleStr() {
		return MRefList.getListName(getCtx(), PRIORITYRULE_AD_Reference_ID, super.getPriorityRule());
	}
	
	public Timestamp getResultedDate(){
		if (hasObx() && !getResultados().isEmpty()) {
			return getResultados().get(0).getCreated();
		} else if (!MAttachmentFile.getFiles(getAttachment()).isEmpty()) {
			return getAttachment().getCreated();
		} else {
			return null;
		}
	}
	
	public String getResultedBy(){
		if (hasObx() && !getResultados().isEmpty()) {
			return getResultados().get(0).getCreatedBy(true);
		} else if (!MAttachmentFile.getFiles(getAttachment()).isEmpty()) {
			return getAttachment().getCreatedBy(true);
		} else {
			return null;
		}
	}
	
	/**  @return  Read back label: Yes / No */
	public String getReadBackStr() {
		return getActPacIndH().getReadBackStr();
	}
	
	/** @return 	enteredBy  */
	public String getUserEntered(){
		return MUser.getUserName(getCtx(), getCreatedBy());
	}
	
	public Timestamp getAuthenticated_Date(){
		return getActPacIndH().getDateApproved();
	}
	/**  @return 	AuthenticatedBy */
	public String getUserAuthenticated(){
		return getActPacIndH().getUserAuthenticated();
	}
	/**  @return 	NotedBy  */
	public String getUserNoted(){
		return getActPacIndH().getUserNoted();
	}
	
	/** @return Noted Date */
	public Timestamp getNotedDate() {
		return getActPacIndH().getNotedDate();
	}
	
	/** @return 	OrderType */
	public String getOrderTypeStr(){
		return getActPacIndH().getOrderTypeStr();
	}
	
	/** @return Discontinued date */
	public Timestamp getDiscontinuedDate() {
		return getActPacIndH().getDiscontinuedDate();
	}

	/**  @return Discontinued by */
	public String getDiscontinuedBy() {
		return getActPacIndH().getDiscontinuedBy();
	}

	/**  @return DiscontinuedOrderTypeStr() */
	public String getDiscontinuedOrderTypeStr() {
		return getActPacIndH().getDiscontinuedOrderTypeStr();
	}

	/** @return DiscontinuedNombre_Med */
	public String getDiscontinuedNombre_Med() {
		return getActPacIndH().getDiscontinuedNombre_Med();
	}
	
	/** @return Authenticated date */
	public Timestamp getDiscontinuedAuthenticatedDate() {
		return getActPacIndH().getDiscontinuedAuthenticatedDate();
	}
	
	/** @return DiscontinuedAuthenticatedBy */
	public String getUserDiscontinuedAuthenticatedBy() {
		return getActPacIndH().getUserDiscontinuedAuthenticatedBy();
	}
	/** @return DiscontinuedReadBackStr */
	public String getDiscontinuedReadBackStr() {
		return getActPacIndH().getDiscontinuedReadBackStr();
	}
	
	/**  @return 	NotedBy  */
	public String getUserDelivered(){
		return getDateDelivered() == null ? "" : MUser.getUserName(getCtx(), getDeliveredBy());
	}
	/** Set Total de linea */
	public void setLineNetAmt(){
		super.setLineNetAmt(getPriceActual().multiply(getQtyDelivered()).setScale(2,BigDecimal.ROUND_HALF_UP));
	}
	/** Set Impuesto */
	public void setTaxAmt(final MTax mTax){
		BigDecimal taxAmt = Env.ZERO;
		if (mTax != null) {
			setC_Tax_ID(mTax.getC_Tax_ID());
			taxAmt = mTax.getAmount();
		}
		setTotalImp(taxAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
	}
	
	/**
	 * Creacion de cargo de servicio individual
	 * @throws Exception
	 * Card #931
	 */
	public boolean createServiceCharge() throws Exception {
		if (!is_new() && getCargo() == null && MProduct.PRODUCTTYPE_Service.equals(getProduct().getProductType())) {
//			final CreateCharge createCharge = new CreateCharge(getCtx());
			if (createActPacIndCharges(get_TrxName())) {//Lama: cargos 2014
				return true;
			} else {
				throw new MedsysException(Utilerias.getMsg(getCtx(), "error.servicios.cargar"));
			}
		}
		return false;
	}
	
	/**
	 * Crear el cargo a partir de MEXMEActPacienteInd
	 * 
	 * @param pTrxName Nombre de la transaccion
	 *            Requerido (se da por un hecho que si existe)
	 * @return true si la insercion de cargos fue exitosa
	 */
	public boolean createActPacIndCharges(final String pTrxName) {//Lama: cargos 2014
		return new CreateCharge(getCtx()).insertActPacIndCharges(this, true, false, pTrxName);
	}
	
	public String getCancelInfo() {
		final StringBuilder str = new StringBuilder();
		if (getDateCanceled() == null || getCanceledBy() <= 0) {
			str.append(Utilerias.getMsg(getCtx(), "msj.noInfo"));
		} else {
			str.append(Utilerias.getMsg(getCtx(), "msj.dateCancelled")).append(" ");
			str.append(Constantes.getSDFDateTime(getCtx()).formatConvert(getDateCanceled())).append("\n");
			str.append(Utilerias.getMsg(getCtx(), "msj.canceladoPor")).append(" ");
			str.append(MUser.getNameOfUser(getCanceledBy()));
		}
		return str.toString();
	}
}
