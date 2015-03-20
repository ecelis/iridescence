package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.util.DiscountChargesData;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.LabelsReportView;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;
import org.compiere.util.enfermeria.MCtaPac_VO;
import org.compiere.util.vo.EncounterTransVO;
import org.joda.time.DateTime;

import com.ecaresoft.api.Generic;
import com.ecaresoft.util.PackageBalance;

/**
 * Modelo de Cuenta Paciente.
 */
public class MEXMECtaPac extends X_EXME_CtaPac {

	 /** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MEXMECtaPac.class);
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Extension cero de la cuenta paciente */
	private MEXMECtaPacExt extCero = null;
	/** Datos de facturacion del paciente */
	private BeanDatosFacturacion datosFacturacion;
//	/** Socio de negocios */
//	private MBPartner bpartner = null;
	private MEXMEActPacienteIndH actPacienteIndH = null;
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param estatus
	 * @param whereclause
	 * @param trxName
	 * @return
	 */
    public static List<LabelValueBean> acctByStatus(Properties ctx, int EXME_Paciente_ID, StringBuilder estatus, String whereclause, String trxName) {
		List<LabelValueBean> lstMctaPac = new ArrayList<LabelValueBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		try {

			sql.append("SELECT * FROM EXME_CtaPac WHERE IsActive = 'Y'  ");
			sql.append(" AND EXME_Paciente_ID = ? ");

			if (estatus != null && StringUtils.isNotEmpty(estatus.toString())) {
				sql.append(" AND ENCOUNTERSTATUS in ('").append(estatus.toString());
				sql.append("')");
			} else {
				StringBuilder st = new StringBuilder();
				st.append("('").append(MEXMECtaPac.ENCOUNTERSTATUS_Admission).append("', '").append(MEXMECtaPac.ENCOUNTERSTATUS_PreAdmission)
					.append("', '").append(MEXMECtaPac.ENCOUNTERSTATUS_Predischarge).append("', '").append(MEXMECtaPac.ENCOUNTERSTATUS_Discharge)
					.append("')");

				sql.append(" AND ENCOUNTERSTATUS in ").append(st.toString());
			}
			if (whereclause != null) {
				sql.append(" AND ");
				sql.append(whereclause);
			}

			sql.append(" ORDER BY EXME_CtaPac_ID");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_Paciente_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				lstMctaPac.add(new LabelValueBean(
					ctaPac.getDocumentNo()
						+ "-"
						+ ctaPac.getEncounterStatusStr(),
						//MRefList.getNameByReferenceID(Env.getAD_Language(ctx), MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID,
						//	ctaPac.getEncounterStatus()), 
							String.valueOf(ctaPac.getEXME_CtaPac_ID())));

			}

		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return lstMctaPac;
	}
	
    /**
	 * @param ctaPacId cuenta paciente a revisar su estatus.
	 * @return si la cuenta paciente esta bloqueada o no.
	 * @throws MedsysException si la cuenta paciente esta bloqueada o no.
	 */
	public static void isLocked(final Properties ctx, final int ctaPacId) {
		if ("Y".equals(DB.getSQLValueString(null, "SELECT IsBloqueada FROM EXME_CtaPac WHERE EXME_CtaPac_ID=?", ctaPacId))) {
			// Si se encuentra usando por otro proceso, aventar una MedsysException con el mensaje correspondiente.
			throw new MedsysException(Utilerias.getAppMsg(ctx, "factExtension.error.init.users", ""));
		}
	}
    
	/**
	 * Revisa si la cuenta paciente esta bloqueada o no, si esta bloqueada regresamos una 
	 * <code>MedsysException</code> para detener el proceso en el metodo llamador. Si no esta bloqueada
	 * la bloquea, si no pudo salvar el objeto regresamos una <code>ExpertException</code>. 
	 * 
	 * @param ctx el <code>Contexto</code> actual de la aplicacion.
	 * @param mCtaPac el objeto <code>Cuenta Paciente</code> a revisar su estatus.
	 * @throws una <code>MedsysException</code> si esta bloqueada o una <code>ExpertException</code>
	 *         si no pudo salvar el objeto de <code>Cuenta Paciente</code>.
	 */
	public static void checkAccount(Properties ctx, MEXMECtaPac mCtaPac) {
		
		// Revisar si la cuenta paciente esta siendo usada
		if (mCtaPac != null && mCtaPac.getEXME_CtaPac_ID() > 0) {
			if (mCtaPac.isBloqueada()) {
		
				// Si se encuentra usando por otro proceso, aventar una MedsysException con el mensaje correspondiente.
				MedsysException medsysException = new MedsysException(Utilerias.getAppMsg(ctx, "factExtension.error.init.users", mCtaPac.getDocumentNo()));
				throw medsysException;
			} else {
				//Si no esta siendo usada, bloquearla y lanzar una ExpertException si no la pudo salvar.
				mCtaPac.setIsBloqueada(true);
			
				//desbloqueaCta = true;
			
				if (!mCtaPac.save()) {
					throw new MedsysException(Utilerias.getAppMsg(ctx, "error.indicacionesMedicas.actualizarEstatus"));
				}
			
				/* 
				 * Obtenemos la lista del Contexto para comenzar a trabajar en el guardado de las cuentas
				 * para desbloquearlas cuando se le de click al boton salir (logout). Jesus Cantu. 
				 */
			    @SuppressWarnings("unchecked")
				List<Integer> lstlCtasPac =  (List<Integer>) ctx.get("ListaIDsCtaPac");
				
				// Revisamos si la Lista de cuentas existe sino la creamos
				if (lstlCtasPac == null) {
					lstlCtasPac = new ArrayList<Integer>();
				}
				
				//Una vez creada, se agrega el id de la cta paciente que se acaba de bloquear.
				lstlCtasPac.add(mCtaPac.getEXME_CtaPac_ID());
				
				//Subimos la lista a Contexto.
				ctx.put("ListaIDsCtaPac", lstlCtasPac);
			}
		}
	}
	
	/**
	 * Revisar las cuentas paciente creadas antes de
	 * las 10:59 de 1 dia antes con el estado P, I y con el area A
	 * 
	 * @param ctx
	 * @param generic Generic ID = org value = timezone
	 * @param trxName
	 */
	public static void encounterCheck(Properties ctx, Generic generic, String trxName) {
		Timestamp date = generic.getValue() == null ? Env.getCurrentDate() : DB.convertTimeZone(new Timestamp(System.currentTimeMillis()), generic.getValue(), Env.getTimezone(ctx));

		DateTime d = new DateTime(date);
		d = d.minusDays(1);
		StringBuilder f = new StringBuilder(Constantes.getSdfFecha().format(d.toDate()));
		f.append(" 11:59:59 PM");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  cta.EXME_CtaPac_ID, ");
		sql.append("  cta.DateOrdered ");
		sql.append("FROM ");
		sql.append("  EXME_CtaPac cta ");
		sql.append("  INNER JOIN EXME_Area area ");
		sql.append("  ON area.EXME_Area_ID = cta.EXME_Area_ID ");
		sql.append("WHERE ");
		sql.append("  cta.DATEORDERED <= ");
		sql.append("  to_date(? ,'dd/MM/yyyy HH:MI:SS AM' ) AND ");
		sql.append("  cta.encounterStatus IN (? ,? ) AND ");
		sql.append("  area.TipoArea IN (? ) AND ");
		sql.append("  cta.ad_org_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, f.toString());
			pstmt.setString(2, ENCOUNTERSTATUS_Predischarge);
			pstmt.setString(3, ENCOUNTERSTATUS_Admission);
			pstmt.setString(4, X_EXME_Area.TIPOAREA_Ambulatory);
			// pstmt.setString(6, X_EXME_Area.TIPOAREA_Emergency);
			pstmt.setInt(5, generic.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				updateEncounter(rs.getInt(1), rs.getTimestamp(2), date, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}
	
	/**
	 * Revisar las cuentas paciente creadas desde las 11PM de 2 dias antes hasta
	 * las 10:59 de 1 dia antes con el estado P, I y con el area A, U
	 * 
	 * @param ctx
	 * @param trxName
	 */
	public static void encounterCheck(Properties ctx, String trxName) {
		List<Generic> generics = MOrg.getParentOrgTimeZones();
		for (Generic generic : generics) {
			encounterCheck(ctx, generic, trxName);
		}
	}
	
	/**
	 * Obtenemos las cuentas paciente filtrada por estatus y el area logeada
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param value Filtrar por value del paciente
	 * @param trxName Nombre de la transacci&oacute;n
	 * @return
	 */
	public static List<MEXMECtaPac> get(Properties ctx, String estatus, String billStatus, 
			String buscar, String valor, boolean area, boolean coding, boolean abstracting, 
			String whereClause, int rowNum, List<Integer> patientIds, boolean cierreCta,
			String trxName) {
		return gets(
				ctx, 
				estatus == null ? "" : estatus.trim(), 
				billStatus == null ? "" : billStatus.trim(),
				buscar, valor, area, coding, abstracting, 
				whereClause, rowNum, patientIds, cierreCta, trxName);
	}
	
	/**
	 * Obtenemos las cuentas paciente a partir de ciertos criterios.
	 * Todas aquellas que tengan al menos una extension.
	 *
	 * @param ctx
	 * @param where
	 * @return
	 */
	public static MEXMECtaPac[] get(Properties ctx, StringBuilder whereClause,
			StringBuilder orderBy, String trxName, Object... params) {

		final ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY); 
		sql.append(" SELECT EXME_CtaPac.* FROM EXME_CtaPac ")
		//freyes, solo mrn activos
		.append(" LEFT JOIN EXME_Hist_Exp he ON ( he.EXME_Paciente_ID = EXME_CtaPac.EXME_Paciente_ID and he.isactive='Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "he"))
		.append(") ")
		.append(" WHERE EXME_CtaPac.IsActive = 'Y' ")
		.append(" AND EXISTS(SELECT ext.EXME_CtaPacExt_ID FROM EXME_CtaPacExt ext ")
		.append("            WHERE ext.EXME_CtaPac_ID = EXME_CtaPac.EXME_CtaPac_ID) ");

		if (whereClause != null) {
			sql.append(whereClause);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));

		if (orderBy != null && orderBy.length() > 0) {
			sql.append(" ORDER BY ").append(orderBy);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			s_log.log(Level.FINEST, "SQL > " + sql.toString());
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				list.add(ctaPac);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		//
		MEXMECtaPac[] ctasPac = new MEXMECtaPac[list.size()];
		list.toArray(ctasPac);
		return ctasPac;

	} 
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param estatus
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECtaPac> getByStatus(Properties ctx, int EXME_Paciente_ID, StringBuilder estatus, String trxName) {
		List<MEXMECtaPac> lstMctaPac = new ArrayList<MEXMECtaPac>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		// POInfo poInfo = POInfo.getPOInfo(ctx, MEXMECtaPac.Table_ID);

		try {

			// Correccion indices ttpr
			sql.append("SELECT * FROM EXME_CtaPac WHERE IsActive = 'Y'  ");
			sql.append(" AND EXME_Paciente_ID = ? ");

			if (estatus != null) {
				sql.append(" AND ENCOUNTERSTATUS in ").append(estatus.toString());
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_CtaPac_ID");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_Paciente_ID);
			// if(estatus != null) {
			// pstmt.setString(2, estatus.toString());
			// }
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);

				lstMctaPac.add(ctaPac);
			}

		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return lstMctaPac;
	}
	/**
	 * Obtiene la cuenta paciente activa si es que existe
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return Cta paciente activa o null si no tiene
	 */
	public static MEXMECtaPac getCtaActiva(Properties ctx, int EXME_Paciente_ID) {
		return MEXMECtaPac.getCtaEstatus(ctx, EXME_Paciente_ID, ENCOUNTERSTATUS_Admission);
	}
	/**
	 * Obtiene la cuenta paciente activa si es que existe
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return Cta paciente activa o null si no tiene
	 */
	//Ren. EncounterStatus Estatus (A= Admission, C= Discharge)
	public static MEXMECtaPac getCtaEstatus(Properties ctx, int EXME_Paciente_ID, String estatus) {
		final StringBuilder st = new StringBuilder("select cta.* from exme_ctapac cta ");
		st.append("inner join exme_paciente pac on cta.exme_paciente_id = pac.exme_paciente_id ");
		st.append("where cta.isactive = 'Y' and cta.EncounterStatus =? and pac.exme_paciente_id = ? ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "cta"));
		st.append(" order by cta.DATEORDERED desc");
		MEXMECtaPac ctaPac = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setString(1, estatus);
			pstmt.setInt(2, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ctaPac = new MEXMECtaPac(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return ctaPac;
	}
	
	/**
	 * Obtengo un arreglo de un rango de cuentas pacientes dentro de un rango de
	 * fechas de creacion A traves del documentno de la tabla EXME_CtaPac
	 *
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPac[] getCtaPac(final Properties ctx, final String trxName) {

		final List <MEXMECtaPac>retValue = new ArrayList<MEXMECtaPac>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT distinct EXME_CtaPac.* ")
		.append(" FROM EXME_CtaPac ")
		.append(" INNER JOIN EXME_Cama       c ON (c.EXME_Cama_ID = EXME_CtaPac.EXME_Cama_ID) ")
		.append(" INNER JOIN EXME_Habitacion h ON (h.EXME_Habitacion_ID = c.EXME_Habitacion_ID) ")
		.append(" INNER JOIN EXME_CDiario   cd ON (h.exme_tipohabitacion_id = cd.EXME_tipoHabitacion_ID) ")
		.append(" WHERE EXME_CtaPac.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_EXME_CtaPac.Table_Name))
		.append(" AND EXME_CtaPac.Encounterstatus=? ")// .append(DB.TO_STRING(X_EXME_CtaPac.ENCOUNTERSTATUS_Admission))
		.append(" AND EXME_CtaPac.EXME_Cama_ID IS NOT NULL ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, ENCOUNTERSTATUS_Admission);
			s_log.log(Level.INFO, sql.toString());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.add(new MEXMECtaPac(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMECtaPac[] plan = new MEXMECtaPac[retValue.size()];
		retValue.toArray(plan);
		return plan;
	}
	
	/**
     * Obtengo un arreglo de cuentas pacientes.
     * @param ctx
     * @param EXME_CtaPac_IDIni
     * @param EXME_CtaPac_IDFin
     * @param FechaIni
     * @param FechaFin
     * @param trxName
     * @return
     */
    public static MEXMECtaPac[] getCtaPacAll(Properties ctx, String whereClause, String trxName) {

        final List<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

        final StringBuilder sql = new StringBuilder("SELECT EXME_CtaPac.* FROM EXME_CtaPac ");

        if(whereClause==null) {
        	sql.append(" WHERE isActive='Y' ");
        } else {
        	sql.append(whereClause);
        }
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ","EXME_CtaPac"));
        sql.append(" ORDER BY EXME_CtaPac.EXME_CtaPac_ID ");


        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	final MEXMECtaPac plan = new MEXMECtaPac(ctx, rs, trxName);
                list.add(plan);
            }

        } catch (Exception e) {
            s_log.log(Level.SEVERE, "MCtaPac.getCtaPacAll - sql: " + sql.toString(), e);
        } finally {
           DB.close(rs,pstmt);
            pstmt = null;
            rs = null;
        }

        MEXMECtaPac[] plan = new MEXMECtaPac[list.size()];
        list.toArray(plan);
        return plan;
    }
	/**
	 * Obtenemos las cuenta paciente para el paciente especificado
	 * @param ctx El contexto de la aplicacion
	 * @param EXME_Paciente_ID El identificador del paciente
	 * @param trxName El nombre de la transaccion
	 * @param AD_Org_ID Organizacion por la que se desea filtrar
	 * @return ctaPac
	 */
	public static MEXMECtaPac[] getCtaPacCert(Properties ctx, int EXME_Paciente_ID, String trxName, int AD_Org_ID) {
		List<MEXMECtaPac> lista = new ArrayList<MEXMECtaPac>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		POInfo poInfo = POInfo.getPOInfo(ctx, MEXMECtaPac.Table_ID);

		try {

			sql.append("SELECT * FROM EXME_CtaPac WHERE IsActive = 'Y'  ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", poInfo, null, AD_Org_ID));
			sql.append(" AND EXME_Paciente_ID = ? ");
			sql.append(" ORDER BY Created Desc");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				lista.add(ctaPac);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMECtaPac[] retValue = new MEXMECtaPac[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}
	
	/**
	 *  Devuelve el numero de documento de una cuenta paciente
	 *
	 *@param  ctapac      ID de la Cuenta Paciente
	 *@return             String numero de documento (documentNo)
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.
	 */
	public static String getCtaPacDocNo(Properties ctx, long ctapac) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT DocumentNo  FROM EXME_CtaPac ");
		sql.append("WHERE EXME_ctapac_id = ? AND isActive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValueString(null, sql.toString(), ctapac);
	}
	/**
	 * Devuelve un modelo con los datos basicos para un documento, en este caso,
	 * cuenta paciente, de acuerdo al identificador proporcionado.
	 * @param ctaPac El identificador de la cuenta paciente
	 * @param extension El identificador de la extension
	 *
	 * @return Un objeto de tipo DocumentView
	 *         en caso de ocurrir un error al hacer la consulta o no
	 *         localizar el registro especificado.
	 */

	public static DocumentView getCtaPacFromID(Properties ctx, int ctaPac, long extension)
	throws Exception {

		DocumentView doc = new DocumentView();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			sql.append(" SELECT EXME_CtaPac.DocumentNo, bp.C_BPartner_ID, bp.Value, bp.Name, ")
			.append(" p.Apellido1 || ' ' || p.Apellido2 || ' ' || p.Name || ' ' || p.Nombre2 AS Paciente, ")
			.append(" EXME_CtaPac.C_Currency_ID, EXME_CtaPac.C_DocType_ID ")
			.append(" FROM EXME_CtaPac INNER JOIN EXME_Paciente p ON ( EXME_CtaPac.EXME_Paciente_ID = p.EXME_Paciente_ID) ")
			.append(" INNER JOIN EXME_CtaPacExt e ON ( EXME_CtaPac.EXME_CtaPac_ID = e.EXME_CtaPac_ID AND e.IsActive = 'Y' ) ")
			.append(" INNER JOIN C_BPartner bp ON ( e.C_BPartner_ID = bp.C_BPartner_ID  AND bp.IsActive = 'Y' ) ")
			.append(" WHERE EXME_CtaPac.EXME_CtaPac_ID = ? ")
			.append(" AND EXME_CtaPac.IsActive = 'Y' ")
			.append(" AND e.EXME_CtaPacExt_ID = ? ");

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPac);
			pstmt.setLong(2, extension);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				doc.setDocumentID(ctaPac);
				doc.setDocumentNo(rs.getString("DocumentNo"));
				doc.setClienteID(rs.getInt("C_BPartner_ID"));
				doc.setClienteValue(rs.getString("Value"));
				doc.setClienteName(rs.getString("Name"));
				doc.setPacienteName(rs.getString("Paciente"));
				doc.setTotalDocto(Env.ZERO);
				doc.setSaldo(Env.ZERO);
				doc.setMonedaID(rs.getInt("C_Currency_ID"));
				// conversion type = default = 0
				doc.setDocTypeID(rs.getInt("C_DocType_ID"));
			} else {
				throw new Exception("error.caja.noDocNo");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECtaPac.getCtaPacFromID - sql = " + sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
		}

		return doc;
	}
	/**
	 *  Devuelve el ID de la cuenta paciente dado un numero de documento
	 *
	 *@param  documentNo  Numero de documento
	 *@param  ctx         Description of the Parameter
	 *@return             long Id de la cuenta paciente
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.
	 */
	public static long getCtaPacID(Properties ctx, String documentNo) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_CtaPac.EXME_ctapac_id ");
		sql.append("FROM EXME_CtaPac ");
		sql.append("WHERE  EXME_CtaPac.documentNo = ? ");
		sql.append("AND EXME_CtaPac.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(null, sql.toString(), documentNo);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param ctaPacInicial
	 * @param ctaPacFinal
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPac[] getCtaPacProv(Properties ctx, String fechaInicial, String fechaFinal, String ctaPacInicial, String ctaPacFinal, String trxName){
		ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT distinct EXME_CtaPac.* ,NVL(cpp.EXME_CtaPacExt_ID,0) as paquete, NVL(cpep.c_invoice_id,0) as invoice ")
		.append("FROM EXME_CtaPac ")
		.append("LEFT JOIN EXME_CtaPacPaq cpp ON EXME_CtaPac.EXME_CtaPac_ID = cpp.EXME_CtaPac_ID ")
		.append("INNER JOIN EXME_CtaPacDet cpd ON EXME_CtaPac.EXME_CtaPac_ID = cpd.EXME_CtaPac_ID AND cpd.QtyPendiente > 0 ")
		.append("INNER JOIN EXME_CtaPacExt cpe ON cpd.exme_ctapac_id = cpe.exme_ctapac_id ")
		.append("LEFT JOIN EXME_CtaPacExt cpep ON cpp.exme_ctapacext_id = cpep.exme_ctapacext_id ")
		.append("WHERE EXME_CtaPac.IsActive = 'Y' AND cpe.C_Invoice_ID IS NULL ")
		.append("AND to_date(to_char(EXME_CtaPac.DateOrdered, 'dd/mm/yyyy') , 'dd/mm/yyyy') ")
		.append("BETWEEN TO_DATE( ? ")//.append(fechaInicial) #1
		.append(", 'dd/MM/yyyy') AND TO_DATE( ? ")//.append(fechaFinal) #2
		.append(", 'dd/MM/yyyy') ")
		.append(" AND EXME_CtaPac.Documentno >= ? ")//.append(ctaPacInicial).append("'") #3
		.append(" AND EXME_CtaPac.Documentno <= ? ");//.append(ctaPacFinal).append("'");	#4

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));

		sql.append(" ORDER BY EXME_CtaPac.EXME_CtaPac_ID ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, fechaInicial);
			pstmt.setString(2, fechaFinal);
			pstmt.setString(3, ctaPacInicial);
			pstmt.setString(4, ctaPacFinal);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac plan = new MEXMECtaPac(ctx, rs, trxName);
				if (rs.getInt("invoice") != 0) {
					plan.setPaqueteFacturado(true);
				}
				if (rs.getInt("paquete") != 0) {
					plan.setTienePaquete(true);
				}
				list.add(plan);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMECtaPac[] plan = new MEXMECtaPac[list.size()];
		list.toArray(plan);


		return plan;
	}
	
	/***
	 * Obtiene Lista de Cuentas Paciente 
	 *@param ctx   Properties
	 *@param where clausula where iniciando con "and"
	 *@param trxName Nombre de la transaccion
	 *@author Rosy Velazquez 
	 */
	public static List<MEXMECtaPac> getCtas(Properties ctx, String where, String trxName, Object...params){
		final List <MEXMECtaPac> cuentas = new ArrayList<MEXMECtaPac>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM EXME_CtaPac cta WHERE cta.IsActive = 'Y' ");
		
		if(where != null){
			sql.append(where);
		}
			
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name, "cta"));
		sql.append(" ORDER BY cta.DATEORDERED desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				cuentas.add(ctaPac);
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return cuentas; 
	}
	
	/**
	 * Obtenemos las cuentas paciente filtrada por estatus,
	 * ademas de la estacion en la que esta el paciente
	 * @autor rsolorzano 31/08/2009
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param valor
	 * @param buscar
	 * @param estServIDs Ids de las estaciones de servicio 
	 * por los que se filtrara
	 * @param trxName Nombre de la transacci&oacute;n
	 * @param tiposPacienteIds Ids de los tipos de paciente
	 * por los que se filtrara
	 * @return
	 * @modify mvrodriguez 27/04/2011 Se sobre cargo el metodo 
	 * para que ahora sea estServID como String que recibe ids
	 * de estaciones y el tiposPacienteIds separadas por comas
	 * @deprecated
	 */
	public static List<MCtaPac_VO> getCuentasPacienteEnfermeria(Properties ctx, String estatus, String valor,
			String buscar, String estServIDs, String trxName, String tiposPacienteIds) {

		List<MCtaPac_VO> list = new ArrayList<MCtaPac_VO>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ctaPac.*, hab.name as nameHab, cama.name as nameCama ");
//				"ctaPac.exme_estserv_id as estServID, ctaPac.documentNo, , p.value,");
//		sql.append(" e.expediente, to_char(ctaPac.dateOrdered,'dd/mm/yyyy') as ctaPacDateOrdered, p.nombre_pac, m.nombre_med, p.exme_paciente_id as patientID, ");
//		sql.append(" p.value as matricula, uni.name as unidad, arma.name as arma,  ");
//		sql.append(" grado.name as grado, grupo.Name as grupo, ");
		// Ren. EncounterStatus StatusAlta
//		sql.append(" TRIM(ctaPac.EncounterStatus) as estatusAlta, ");
//		sql.append(" ctaPac.FechaAlta, ctaPac.FechaPrealta, ctaPac.EXME_TipoPaciente_ID as tipoPacienteID");
		sql.append(" FROM EXME_CtaPac ctaPac ");
//		sql.append(" INNER JOIN EXME_Paciente p ON (ctaPac.EXME_Paciente_ID = p.EXME_Paciente_ID)");
		sql.append(" INNER JOIN EXME_Medico m ON (ctaPac.EXME_Medico_ID = m.EXME_Medico_ID)");
		//freyes, un solo mrn activo por org
//		sql.append(" LEFT  JOIN EXME_Hist_Exp e ON (p.exme_paciente_id = e.exme_paciente_id and e.isactive ='Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "e"));
//		sql.append(") ");
		sql.append(" LEFT JOIN EXME_CAMA cama ON (ctaPac.exme_cama_id = cama.exme_cama_id)");
		sql.append(" LEFT JOIN EXME_Habitacion hab ON (cama.EXME_HABITACION_ID = hab.EXME_HABITACION_ID)");
//		sql.append(" LEFT JOIN EXME_Unidad uni  ON (p.EXME_UNIDAD_ID = uni.EXME_UNIDAD_ID)");
//		sql.append(" LEFT JOIN EXME_Arma arma  ON (p.EXME_ARMA_ID = arma.EXME_ARMA_ID)");
//		sql.append(" LEFT JOIN EXME_Grado grado  ON (p.EXME_GRADO_ID = grado.EXME_GRADO_ID)");
//		sql.append(" LEFT JOIN EXME_Grupo_Especialidad grupo ON (p.EXME_GRUPO_ESPECIALIDAD_ID = grupo.EXME_GRUPO_ESPECIALIDAD_ID)");
		sql.append(" WHERE ctaPac.isActive = 'Y' ");
		//sql.append(" AND hab.EXME_ESTSERV_ID = ? ");
		sql.append(" AND E.IsActive='Y' ");
		sql.append(" AND ctaPac.EXME_ESTSERV_ID IN (" + estServIDs + ") ");
		sql.append(" AND ctaPac.EncounterStatus = ? ");

		if(tiposPacienteIds != null) {

			sql.append(" AND ctaPac.EXME_TipoPaciente_ID IN (" + tiposPacienteIds + ") ");	

		}



		sql .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,"ctaPac"));

		// si el valor no es nulo
		if (StringUtils.isNotBlank(valor)) {
			//valor = valor.replaceAll("\\*", "%");//Lama: comodin estandar %
			if (buscar.equalsIgnoreCase("ctaPac.DateOrdered")) {
				sql.append(" AND ");
				
				if (DB.isOracle()) {
					sql.append("TRUNC(").append(buscar).append(") ");
				} else if (DB.isPostgreSQL()) {
					sql.append("DATE_TRUNC('day', ").append(buscar).append(") ");
				}
				
				sql.append("= TO_DATE(?, 'DD/MM/YYYY') ");
			} else {
				sql.append(" AND UPPER(").append(buscar).append(") LIKE UPPER(?) ");
			}
		}


		sql.append(" ORDER BY cama.name, ctaPac.documentNo ");


		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, estatus);
			if (StringUtils.isNotBlank(valor)) {
				pstmt.setString(2, valor);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPac_VO ctaPac = new MCtaPac_VO(ctx, rs, trxName);

//				ctaPac.setCtaPacID(rs.getInt("EXME_CtaPac_ID"));
//				ctaPac.setDocumentNo(rs.getString("documentNo"));
				ctaPac.setHabitacion(rs.getString("nameHab"));
				ctaPac.setCamaStr(rs.getString("nameCama"));
//				ctaPac.setHistoria(rs.getString("value"));
//				ctaPac.setDateOrdered(rs.getString("ctaPacDateOrdered"));
//				ctaPac.setNombrePac(rs.getString("nombre_pac"));
				ctaPac.setNombreMed(rs.getString("nombre_med"));
//				ctaPac.setHistoria(rs.getString("matricula"));
//				ctaPac.setUnidad(rs.getString("unidad"));
//				ctaPac.setArma(rs.getString("arma"));
//				ctaPac.setGrado(rs.getString("grado"));
//				ctaPac.setGrupo(rs.getString("grupo"));
//				ctaPac.setEstatusAlta(rs.getString("estatusAlta"));
//				ctaPac.setEstServID(rs.getInt("estServID"));
//				ctaPac.setPatientID(rs.getInt("patientID"));
//				if(rs.getTimestamp("FechaAlta")!=null){
//					ctaPac.setFechaAlta(Constantes.getSdfFechaHoraAmPm().format(rs.getTimestamp("FechaAlta")));
//				}
//				if(rs.getTimestamp("FechaPrealta")!=null){
//					ctaPac.setFechaPrealta(Constantes.getSdfFechaHoraAmPm().format(rs.getTimestamp("FechaPrealta")));
//				}
//				ctaPac.setTipoPacienteID(rs.getInt("tipoPacienteID"));

				list.add(ctaPac);
			}


		} catch (Exception e) {			
			s_log.log(Level.SEVERE, "MEXMECtaPac.getCuentasPacienteEnfermeria - sql: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Obtenemos las cuentas paciente filtrada por estatus,
	 * ademas de la estacion en la que esta el paciente
	 * @autor rsolorzano 31/08/2009
	 * @param ctx El contexto de la aplicacion
	 * @param trxName Nombre de la transacci&oacute;n
	 * @return
	 * @modify mvrodriguez 27/04/2011 Se sobre cargo el metodo 
	 * para que ahora sea estServID como String que recibe ids
	 * de estaciones y el tiposPacienteIds separadas por comas
	 */
	public static List<MCtaPac_VO> getVO(Properties ctx, String trxName, StringBuilder sql, List<?> params) {
		final List<MCtaPac_VO> list = new ArrayList<MCtaPac_VO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			s_log.log(Level.INFO, " sql: " + sql.toString() + " params: " + params == null ? "" : params.toString());
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MCtaPac_VO ctaPac = new MCtaPac_VO(ctx, rs, trxName);
				try {
//					ctaPac.setCtaPacID(mctapac.getEXME_CtaPac_ID());
//					ctaPac.setDocumentNo(mctapac.getDocumentNo());
//					ctaPac.setEstatusAlta(mctapac.getEncounterStatus());
//					if (ctaPac.getEstatusAlta() != null) {
//						ctaPac.setEstatusAltaDesc(mctapac.getEncounterStatusStr());
//								//MRefList.getListName(ctx, MEXMECtaPac.ENCOUNTERSTATUS_AD_Reference_ID, ctaPac.getEstatusAlta()));
//					}
//					ctaPac.setEstServID(mctapac.getEXME_EstServ_ID());
//					ctaPac.setTipoPacienteID(mctapac.getEXME_TipoPaciente_ID());
//					if (ctaPac.getTipoPacienteID() > 0) {
//						ctaPac.setTipoPaciente(new MEXMETipoPaciente(ctx, ctaPac.getTipoPacienteID(), null).getName());
//					}
//					if (mctapac.getDateOrdered() != null) {
//						ctaPac.setDateOrdered(Constantes.sdfFecha(ctx).formatConvert(mctapac.getDateOrdered()));
//					}
//					if (mctapac.getFechaAlta() != null) {
//						ctaPac.setFechaAlta(Constantes.getSDFDateTime(ctx).formatConvert(mctapac.getFechaAlta()));
//					}
//					if (mctapac.getFechaPrealta() != null) {
//						ctaPac.setFechaPrealta(Constantes.getSDFDateTime(ctx).formatConvert(mctapac.getFechaPrealta()));
//					}
//					ctaPac.setPatientID(mctapac.getEXME_Paciente_ID());
//					ctaPac.setHistoria(mctapac.getPaciente().getValue());
//					ctaPac.setNombrePac(SecureEngine.decrypt(mctapac.getPaciente().getNombre_Pac()));

					ctaPac.setHabitacion(rs.getString("nameHab"));
					ctaPac.setCamaStr(rs.getString("nameCama"));
					ctaPac.setNombreMed(rs.getString("nombre_med"));
//					ctaPac.setExpediente(rs.getString("expediente"));
//					ctaPac.setGrupo(rs.getString("grupo"));
					ctaPac.setService(rs.getString("servicio"));// lhernandez
//					ctaPac.setDiarioID(rs.getInt(I_EXME_DiarioEnf.COLUMNNAME_EXME_DiarioEnf_ID));
					list.add(ctaPac);
				} catch (Exception e) {
					s_log.log(Level.WARNING, e.toString(), e);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE,
				" sql: " + sql + (params == null ? "" : Constantes.NEWLINE + params.toString()), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param where
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECtaPac> getEncounterByStatus(Properties ctx, String where, Object[] params, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
		   .append("  * ")
		   .append("FROM ")
		   .append("  EXME_CtaPac ")
		   .append((StringUtils.isEmpty(where)?" WHERE EXME_CtaPac.IsActive ='Y' " : where));
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPac.Table_Name));
		sql.append(" order by "+MEXMECtaPac.COLUMNNAME_FechaAlta);
		List<MEXMECtaPac> lstDiv = new ArrayList<MEXMECtaPac>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (params != null && params.length > 0) {
				DB.setParameters(pstmt, params);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstDiv.add(new MEXMECtaPac(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}
	
	/**
	 * Obtenemos la extension cero de un determinada cuenta paciente.
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	public static MEXMECtaPacExt getExtCero(Properties ctx, int EXME_CtaPac_ID,
			String trxName) {
		return MEXMECtaPacExt.getExtCero(ctx, EXME_CtaPac_ID, trxName);
	}
	
	/**
	 * Obtiene el id de la extension 0 de la cuenta
	 * 
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @return Id de la extension 0
	 */
	public static int getExtZero(int ctaPacId) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("  exme_ctapacext_id ");
		sql.append("FROM ");
		sql.append("  exme_ctapac ");
		sql.append("WHERE ");
		sql.append("  exme_ctapac_id = ? ");

		return DB.getSQLValue(null, sql.toString(), ctaPacId);
	}
	
	/** Metodos de MEXMECtaPac*/
	/**
	 * Devolvemos una cuenta paciente en base al n&uacute;mero de documento
	 * relacionado.
	 *
	 * @param ctx
	 * @param documentNo
	 * @param trxName
	 * @return
	 * @throws SQLException
	 */
	public static MEXMECtaPac getForDocumentNo(Properties ctx, String documentNo, String trxName) throws SQLException {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MEXMECtaPac ctaPac = null;

		try {
			sql.append(" SELECT EXME_CtaPac.* FROM EXME_CtaPac WHERE UPPER(EXME_CtaPac.DocumentNo) LIKE UPPER(?)")
				.append(" AND EXME_CtaPac.IsActive = 'Y' ");

			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, documentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ctaPac = new MEXMECtaPac(ctx, rs, trxName);
			}

		}
		catch (SQLException e) {
			s_log.log(Level.SEVERE, "MEXMECtaPac.getForDocumentNo - sql = " + sql.toString(), e);
			throw e;
		}
		finally {
			DB.close(rs, pstmt);
		}

		return ctaPac;
	}
	public static List<MEXMECtaPac> getHistoria(Properties ctx, int EXME_CtaPac_ID) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		try {
			sql.append(" SELECT * ");
//			sql.append("EXME_CtaPac.EXME_CtaPac_ID , EXME_CtaPac.FechaAlta ");
//			sql.append(", EXME_CtaPac.ARCHIVO , EXME_CtaPac.FechaEnv");
			sql.append(" FROM EXME_CtaPac ");
			sql.append(" WHERE EXME_CtaPac.EXME_CTAPAC_ID = ? ");
			sql.append(" AND EXME_CtaPac.ARCHIVO IS NOT NULL");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY EXME_CtaPac.FechaAlta DESC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMECtaPac cp = new MEXMECtaPac(ctx, rs, null);
				list.add(cp);
			}

		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctapac
	 * @param trxName
	 * @param whereclause
	 * @param params
	 * @return
	 */
	public static List<Movement> getMovements(Properties ctx, int ctapac, String trxName, final String whereclause, final Object... params)  {

		final List<Movement> lista = new ArrayList<Movement>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// movimientos por empresa, organizacion y cuenta paciente
		sql.append(" SELECT DISTINCT M_Movement.M_Movement_ID, M_Movement.DocumentNo, M_Movement.Description, ")
		.append(" CASE M_Movement.DocStatus WHEN 'DR' THEN 'Solicitado' ")
		.append(" WHEN 'IP' THEN 'Surtido' ")
		.append(" WHEN 'CO' THEN 'Recibido' ")
		.append(" END Estatus, ")
		.append(" M_Movement.MovementDate, wsol.Name AlmSolicita, wsurt.Name AlmSurte ")
		.append(" FROM M_Movement ")
		.append(" INNER JOIN M_MovementLine ml ON (M_Movement.M_Movement_ID = ml.M_Movement_ID AND  ml.IsActive = 'Y' ) ")
		.append(" INNER JOIN M_Locator lsol ON (ml.M_LocatorTo_ID = lsol.M_Locator_id AND lsol.IsActive = 'Y' ) ")
		.append(" INNER JOIN M_Warehouse wsol ON (wsol.M_Warehouse_ID = lsol.M_Warehouse_ID AND wsol.IsActive = 'Y' ) ")
		.append(" INNER JOIN M_Locator lsurt ON (ml.M_Locator_ID = lsurt.M_Locator_id AND lsurt.IsActive = 'Y' ) ")
		.append(" INNER JOIN M_Warehouse wsurt ON (wsurt.M_Warehouse_ID = lsurt.M_Warehouse_ID AND wsurt.IsActive = 'Y' ) ")
		.append(" WHERE M_Movement.IsActive = 'Y' ")
		//.append(" AND M_Movement.DocStatus IN ('DR', 'IP' ,'CO')  ") 
		.append(" AND M_Movement.EXME_CtaPac_ID = ?  ");

		if(whereclause != null){
			sql.append(whereclause);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",MMovement.Table_Name));

		sql.append(" ORDER BY M_Movement.MovementDate DESC, M_Movement.DocumentNo DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			pstmt.setInt(index++,ctapac);
			for (int i = 0; i < params.length; i++) {
				DB.setParameter(pstmt, index++, params[i]);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Movement linea = new Movement();
				linea.setMovementID(rs.getLong("M_Movement_ID"));
				linea.setDocumentNo(rs.getString("DocumentNo"));
				linea.setDescription(rs.getString("Description"));
				linea.setMovementDate(rs.getDate("MovementDate"));
				linea.setLocatorTo(rs.getString("AlmSolicita"));
				linea.setLocator(rs.getString("AlmSurte"));
				linea.setDocStatus(rs.getString("Estatus"));
				lista.add(linea);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;
	}
	
	/**
	 * Cuentas por paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param estatus
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPac[] getOfPaciente(Properties ctx, int EXME_Paciente_ID,
			String estatus, String trxName) {
		return getOfPaciente(ctx, EXME_Paciente_ID,	estatus, null, trxName, -1, 0);
	}
	
	/**
	 * getOfPaciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param estatus
	 * @param trxName
	 * @param orderType
	 * @param AD_Org_ID
	 * @param ctapacid
	 * @return
	 */
	public static MEXMECtaPac[] getOfPaciente(Properties ctx, int EXME_Paciente_ID,
			String estatus, String trxName, String orderType, int AD_Org_ID, int ctapacid) {
		return getOfPaciente(ctx, EXME_Paciente_ID, estatus, trxName, orderType, AD_Org_ID, ctapacid, false);
	}

	/**
	 * Obtenemos las cuenta paciente para el paciente especificado
	 * @param ctx El contexto de la aplicacion
	 * @param EXME_Paciente_ID El identificador del paciente
	 * @param estatus El estatus de la cuenta paciente
	 * @param trxName El nombre de la transaccion
	 * @param AD_Org_ID Organizacion por la que se desea filtrar
	 * @param ctapacid la cuenta paciente a buscar
	 * @return Un objeto de tipo MEXMECashLine
	 */
	public static MEXMECtaPac[] getOfPaciente(Properties ctx, int EXME_Paciente_ID,
			String estatus, String trxName, String orderType, int AD_Org_ID, int ctapacid, boolean openedMX) {
		List<MEXMECtaPac> lista = new ArrayList<MEXMECtaPac>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ilParametro = 1;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		POInfo poInfo = POInfo.getPOInfo(ctx, MEXMECtaPac.Table_ID);

		try {

			//Correccion indices ttpr
			sql.append(" SELECT * FROM EXME_CtaPac WHERE IsActive = 'Y'  ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", poInfo, null, AD_Org_ID));
			sql.append(" AND EXME_Paciente_ID = ? ");

			if(estatus != null) {
				sql.append(" AND EncounterStatus = ? ");
			}
			
			// Condicionamos el parametro agregado de ctapac. Se agrego para el historial de egresos en ECE.
			// Jesus Cantu
			if (ctapacid > 0) {
				sql.append(" AND EXME_CtaPac_Id = ? ");
			}
			
			if (openedMX) {
				sql.append(" AND FechaCierre is Null ");
			}
			
			sql.append(" ORDER BY EXME_CtaPac_ID ");
			if (StringUtils.isNotEmpty(orderType)){
				sql.append(orderType);
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			// El id del paciente siempre es primero por tanto ilParametro=1
			pstmt.setInt(ilParametro, EXME_Paciente_ID);
			
			if (estatus != null) {
				//Aumentar el contador para setear el siguiente parametro
				ilParametro = ilParametro + 1;
				pstmt.setString(ilParametro, estatus);
			}
			
			// Si tiene ctapac setear el parametro correcto.
			if (ctapacid > 0) {
				//Aumentar el contador para setear el siguiente parametro
				ilParametro = ilParametro + 1;
				pstmt.setInt(ilParametro, ctapacid);
			} 
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				lista.add(ctaPac);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMECtaPac[] retValue = new MEXMECtaPac[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}

	/**
	 * Cuentas por paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param estatus
	 * @param trxName
	 * @return
	 */
	public static MEXMECtaPac[] getOfPacienteCert(Properties ctx, int EXME_Paciente_ID, String trxName) {
		return getCtaPacCert(ctx, EXME_Paciente_ID, trxName, -1);
	}
	/**
	 * Obtiene el paciente si es que existe
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @return Paciente o null si no existe
	 */
	public static MEXMEPaciente getPatient(Properties ctx, int EXME_CtaPac_ID) {
		StringBuilder st = new StringBuilder("select pac.* from exme_ctapac cta ");
		st.append("inner join exme_paciente pac on cta.exme_paciente_id = pac.exme_paciente_id ");
		st.append("where cta.isactive = 'Y' and cta.exme_ctapac_id = ? ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "cta"));
		MEXMEPaciente patient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				patient = new MEXMEPaciente(ctx, rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return patient;
	}

	/**
	 * Obtenemos las cuentas paciente filtrada por
	 * sexo Femenino, identificadas como embarazadas
	 *
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param value Filtrar por value del paciente
	 * @param trxName Nombre de la transacci&oacute;n
	 * @return
	 */
	public static MEXMECtaPac[] getListMother(Properties ctx, String buscar, Object valor, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		final ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		sql.append(" SELECT EXME_CtaPac.* FROM EXME_CtaPac ");
		sql.append(" INNER JOIN EXME_Paciente p ON  (EXME_CtaPac.EXME_Paciente_ID = p.EXME_Paciente_ID) ");
		sql.append(" INNER JOIN EXME_Medico m ON (EXME_CtaPac.EXME_Medico_ID = m.EXME_Medico_ID) ");
		//sql.append(" INNER JOIN EXME_MotivoCita mv ON (EXME_CtaPac.EXME_MotivoCita_ID = mv.EXME_MotivoCita_ID) ");
		sql.append(" INNER JOIN EXME_AdmitType mv ON (EXME_CtaPac.EXME_AdmitType_ID = mv.EXME_AdmitType_ID) ");
		sql.append(" INNER JOIN EXME_ActPaciente act ON (EXME_CtaPac.EXME_CtaPac_ID = act.EXME_CtaPac_ID) ");
		sql.append(" WHERE EXME_CtaPac.IsActive = 'Y' ");
		sql.append(" AND EXME_CtaPac.EncounterStatus = ? ");//#1
		sql.append(" AND p.Sexo = ? ");//#2
		//sql.append(" AND trunc(trunc((((86400*(sysdate-p.fechanac))/60)/60)/24)/365) >= 12 ");
		//sql.append(" AND act.isPregnant = 'Y' OR UPPER(mv.name) Like ?  ");//#3
		sql.append(" AND act.isPregnant = 'Y' ");//#3

		if (valor != null) {
			if (valor instanceof Timestamp) {
				sql.append(" AND ");
				
				if (DB.isOracle()) {
					sql.append("TRUNC(").append(buscar).append(",'DD') ");
					sql.append("= TRUNC(?,'DD')");
				} else if (DB.isPostgreSQL()) {
					sql.append("DATE_TRUNC('day', ").append(buscar).append(") ");
					sql.append("= DATE_TRUNC('day', ?)");
				}
				
			} else if (valor instanceof String) {
				valor = StringUtils.isBlank(valor.toString()) ? "%" : valor.toString().replaceAll("//*", "%").toUpperCase();
				sql.append(" AND UPPER(").append(buscar).append(") LIKE ? ");
			} else {
				sql.append(" AND ").append(buscar).append(" = ? ");
			}
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY p.Nombre_Pac ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, ENCOUNTERSTATUS_Admission);
			pstmt.setString(2, MEXMEPaciente.SEXO_Female);
			//pstmt.setString(3, "%LABOR%");
			if (valor != null) {
				DB.setParameter(pstmt, 3, valor);
			}

			rs = pstmt.executeQuery();
			MEXMECtaPac ctapac = null;
			while (rs.next()) {
				if (ctapac != null && ctapac.getEXME_CtaPac_ID() == rs.getInt("EXME_CtaPac_ID")) {
					continue;
				}
				ctapac = new MEXMECtaPac(ctx, rs, trxName);
				list.add(ctapac);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMECtaPac[] ctasPac = new MEXMECtaPac[list.size()];
		list.toArray(ctasPac);
		return ctasPac;

	}

	/**
	 * 
	 * @param ctx
	 * @param estatus
	 * @param buscar
	 * @param valor
	 * @param area
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECtaPac> gets(Properties ctx, String estatus, String estatus2, 
			String buscar, String valor, boolean area, boolean coding, boolean abstracting, 
			String whereClause, int rowNum, List<Integer> patientIds, boolean cierreCta, String trxName) {

		final ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (rowNum > 0) {
			sql.append(" SELECT * FROM (SELECT EXME_CtaPac.*");
		}else {
			sql.append(" SELECT EXME_CtaPac.*");
		}

		sql.append(" FROM EXME_CtaPac ")
		.append(" INNER JOIN EXME_Paciente p ON ( p.EXME_Paciente_ID = EXME_CtaPac.EXME_Paciente_ID AND p.IsActive = 'Y' ) ")
		//freyes, solo mrn activos
		.append(" LEFT JOIN EXME_Hist_Exp he ON ( he.EXME_Paciente_ID = p.EXME_Paciente_ID and he.isactive ='Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEHistExp.Table_Name, "he"))
		.append(") ")
		.append(" INNER JOIN EXME_Medico m ON (m.EXME_Medico_ID=EXME_CtaPac.EXME_Medico_ID) ");

		// patient type
		if(buscar != null){
			if (buscar.equalsIgnoreCase("EXME_CtaPac.EXME_TipoPaciente_ID")) {
				sql.append(" LEFT JOIN EXME_TIPOPACIENTE tp ON ( EXME_CtaPac.EXME_TipoPaciente_ID  = tp.EXME_TIPOPACIENTE_ID ) ");
			}
		}

		sql.append(" WHERE EXME_CtaPac.IsActive = 'Y'  ");
		
		//Si es la opcion de cerrar cuenta paciente que valide solamente si la fecha de cierre esta nula.
		//Para que muestre las cuentas que no se cerraron cuando se dieron de alta.
		//Cambios para Mexico. Validados con Helio y Lama el 2 de nov del 2012.
		//No importa estatus, solo fecha de cierre.
		if (cierreCta) {
		
			sql.append(" AND fechacierre is null ");
			
		} else if (StringUtils.isNotBlank(estatus)) {
			//Si no es la opcion de cierre de cuenta que haga lo que hacia normalmente
//			if (StringUtils.isNotBlank(estatus)) {
			sql.append(" AND EXME_CtaPac.Encounterstatus IN (?");
//			}
			if (StringUtils.isNotBlank(estatus2)) {
				sql.append(" ,? ");
			}
		
			sql.append(" ) ");
		}
		
		sql.append(area ? " AND EXME_CtaPac.TipoArea = ? " : "");

		// Date ordered
		if (buscar != null) {
			if (buscar.equalsIgnoreCase("EXME_CtaPac.DateOrdered") && StringUtils.isNotEmpty(buscar)) {
				valor = Constantes.WILDCARD + valor.replaceAll("//*", "%") + Constantes.WILDCARD;
				sql.append(" AND TO_CHAR(EXME_CtaPac.DateOrdered,'").append(Constantes.sdfFecha(ctx).toPattern()).append("') LIKE ? ");
			} else if (buscar.equalsIgnoreCase("EXME_CtaPac.EXME_TipoPaciente_ID")) {
				// patient type
				sql.append(" AND UPPER(tp.NAME) LIKE ? ");
				if (StringUtils.isBlank(valor)) {
					valor = "%";
				}
			} else if (StringUtils.isNotEmpty(buscar)) {
				// All
				valor = valor.replaceAll("//*", "%");
				sql.append(" AND UPPER(").append(buscar).append(") LIKE ? ");
			}
		}
		if (patientIds != null && !patientIds.isEmpty()) {
			sql.append(" AND p.exme_paciente_id in (").append(StringUtils.join(patientIds, ',')).append(") ");
		}
		// coding screen
//		if (coding) {
//			sql.append(" AND EXME_CtaPac.TipoArea <> ? ");
//		}
//		else if (abstracting) {
//			sql.append(" AND EXME_CtaPac.TipoArea = ? ");
//		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(whereClause == null ? "" : whereClause);

		sql.append(" ORDER BY EXME_CtaPac.DocumentNo DESC ");

		if (rowNum > 0) {
			sql.append(" ) ");
			
			if(DB.isPostgreSQL()) {
				sql.append("AS ctaPac");
				
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, rowNum));
			} else {
				sql.append("WHERE ROWNUM <= ").append(rowNum);
			}
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			
			//Si es cierre de cuenta no toma en cuenta los estatus, solo la fecha de cierre.
			//Jesus Cantu, revisado con Helio y Lorena Lama el 2 Nov 2012.
			if (!cierreCta && StringUtils.isNotBlank(estatus)) {

				pstmt.setString(i++, estatus);

				if (StringUtils.isNotBlank(estatus2)) {
					pstmt.setString(i++, estatus2);
				}

			}
			
			if (area) {
				pstmt.setString(i++, Env.getTipoArea(ctx));
			}
			if(StringUtils.isNotEmpty(buscar)){
				pstmt.setString(i++, valor.toUpperCase());
			}
//			if (coding || abstracting ){
//				pstmt.setString(i++, MEXMECtaPac.TIPOAREA_Hospitalization);
//			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				list.add(ctaPac);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
//		final MEXMECtaPac[] ctasPac = new MEXMECtaPac[list.size()];
//		list.toArray(ctasPac);
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static List<EncounterTransVO> getTransactions(Properties ctx, int ctaPacID, String trxName) {
		List<EncounterTransVO> lstDiv = new ArrayList<EncounterTransVO>();
		
		// Charges
		int ctaPacExtID = MEXMECtaPacExt.getExtCero(ctx, ctaPacID, trxName).getEXME_CtaPacExt_ID();
		List<MCtaPacDet> lstCtaPacDet = MCtaPacDet.getCargosByExt(ctx,ctaPacExtID, trxName);
		for (MCtaPacDet ctapacdet : lstCtaPacDet) {
			lstDiv.add(new EncounterTransVO(ctx, ctapacdet));
		}
		// Advance Payments
		List<MPayment> lstPayments = MPayment.getPaymentsByExtUSA(ctaPacID, ctaPacExtID, ctx);
		for (MPayment payment : lstPayments) {
//			if (payment.getC_Charge()!=null 
//					&& !MCharge.TYPE_Deductible.equals(payment.getC_Charge().getType()) 
//					&& !MCharge.TYPE_CoPay.equals(payment.getC_Charge().getType())
//					&& !MCharge.TYPE_Coinsurance.equals(payment.getC_Charge().getType())
//					) {
				lstDiv.add(new EncounterTransVO(ctx, payment));
			//}	
		}
		
		return lstDiv;
	}

	/**
	 * Actualiza la cuenta paciente a D
	 * 
	 * @param ctaPacId
	 *            Cuenta para actualizar
	 * @param alta
	 *            Fecha de alta
	 * @param cierre
	 *            Fecha de cierre
	 * @param trxName
	 */
	private static void updateEncounter(int ctaPacId, Timestamp alta, Timestamp cierre, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  EXME_CtaPac ");
		sql.append("SET ");
		sql.append("  encounterStatus = ? , ");
		sql.append("  fechaAlta = ? , ");
		sql.append("  fechaCierre = ? ");
		sql.append("  ,exme_dischargestatus_id = ? ");//Se coloca en duro por default el registro 1000000 GCB// gderreza
		sql.append("WHERE ");
		sql.append("  EXME_CtaPac_ID = ? ");
		try {
			DB.executeUpdate(sql.toString(), new Object[] { ENCOUNTERSTATUS_Discharge, alta, cierre, 1000000, ctaPacId}, false, trxName);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
	}

	/** actividad del paciente */
	protected MEXMEActPaciente[] actividades = null;
	/** Cama de la cuenta paciente */
	private MEXMECama cama = null;
	/** socio de negocios */
	private MBPartner mBPartner = null;
	/** */
	private MEXMEConsentimientoPac consentimientoPac = null;
	/** datos complementarios de el acuentpaciente .-Lama */
	private MCtaPacDatos ctasPacDatos = null;
	/** Paciente-Dieta .-Expert:Diana */
	private MEXMEEstServ estServ = null;
	/** unidad de ingreso */
	private MEXMEEstServ estServIng = null;
	/** direccion */
	private MLocation location = null;
	/** listado de errores */
	private List<ModelError> lstErrores = new ArrayList<ModelError>();
	/** almacen */
	private int m_Warehouse_ID = 0;
	/** extensiones de la cuenta paciente */
	private MEXMECtaPacExt[] mCtasPacExt = null;
	/** dieta */
	public MEXMEPrescDieta mDiet = null;
	/** medico */
	private MEXMEMedico medico = null;
	/** medico (attending)*/
	private MEXMEMedico attending = null;
	/** Just Prepared Flag */
	private boolean mJustPrepared = false;
	/** Order Lines */
	private MCtaPacDet[] mLines = null;
	/** motivo de cita o razon de la cuenta */
	private MEXMEMotivoCita motivoCita = null;
    /** Process Message */
	private String mProcessMsg = null;
	/** tipo de paciente */
	private MEXMETipoPaciente mTipoPac = null;
	/** obj del paciente */
	private MEXMEPaciente paciente = null;
	/** paquete facturado ? */
	private boolean paqueteFacturado = false;
	/** pendingObx */
	private boolean pendingObx = false;
	/** pendingObxCount */
	private int pendingObxCount = -1;
	/** resStatus */
	private String resStatus = null;
	/** resStatusOld */
	private String resStatusOld = null;
	/** resStatusStr */
	private String resStatusStr = null;
	/** tiene un paquete ? */
	private boolean tinePaquete = false;
	/** */ 
	private MEXMECitaMedica cita = null;
	
	/**
	 * Creacion de la cuenta paciente
	 * 
	 * @param pacienteID
	 *            id del paciente
	 * @param especialidadID
	 *            id de la especialidad del tratamiento
	 * @param medicoID
	 *            id del medico responsable
	 * @param trxname
	 *            nombre dela transaccion retorna true si no hubo errores
	 */
	public MEXMECtaPac (Properties ctx, int EXME_CtaPac_ID, int pacienteID,
			int especialidadID, int medicoID, String trxName) {

		this(ctx, EXME_CtaPac_ID, trxName);

		if (EXME_CtaPac_ID <= 0) {

			if (pacienteID <= 0 || medicoID <= 0 || especialidadID <= 0
					|| trxName == null) {
				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
						"parametros incompletos"));
				return;
			}

			setEXME_Paciente_ID(pacienteID);
			setEXME_Especialidad_ID(especialidadID);
			setEXME_Medico_ID(medicoID);
			
			// Lista de precios de configuracion
			setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, null)
					.getM_PriceList_ID());
			
			// motivo de cita - dato requerido
			int motivoId = MEXMEMotivoCita.getFirstId(ctx, null, trxName);
			if (motivoId > 0) {
				setEXME_MotivoCita_ID(motivoId);
				
				// Cuenta paciente creada para tratamientos
				//setEstatus(X_EXME_ESTATUS_Reserved);
				setEncounterStatus(ENCOUNTERSTATUS_PreAdmission);

				// Requerimos el area (Est Serv de login)
				MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
				if(estServ!=null){
					setEXME_Area_ID(estServ.getEXME_Area_ID());
				}

				// obtenemos un id
				if (!save(trxName)){
					lstErrores.add(new ModelError(
							ModelError.TIPOERROR_Informativo,
					"no se guardo el tratamiento"));
					return;
				}
			}
		}

		return;
	}
	
	public List<ModelError> getLstErrores() {
		return lstErrores;
	}
	
	/**
     * Constructor ID
     * @param ctx
     * @param EXME_CtaPac_ID
     * @param trxName
     */
	public MEXMECtaPac(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		super(ctx, EXME_CtaPac_ID, trxName);
		if (getEXME_CtaPac_ID() <= 0) {

			// obtenemos los subtipos de documento SO para cargos a cta pac
			String whereClause = 
				" AND AD_Org_ID = ? AND upper(name) not like ? ";
			
			MEXMEDocType[] dt = 
				MEXMEDocType.getOfDocSubTypeSO(
						getCtx(), 
						MDocType.DOCSUBTYPESO_CgosACtaPac,
						whereClause, 
						new Object[] {Env.getAD_Org_ID(getCtx()), "%EXT%"},
						1
				); //Lama
			setC_DocType_ID(dt.length > 0 ? dt[0].getC_DocType_ID() : 0);

			setC_DocTypeTarget_ID(getC_DocType_ID());
			setDocStatus(DocAction.STATUS_Drafted);
			setDocAction(DocAction.ACTION_Complete);
			setProcessing(false); // N
			setProcessed(false); // N
			setIsApproved(false); // N
			setPriorityRule("5");
//			setPosted(false);
			setAD_User_ID(Env.getAD_User_ID(ctx));
			setEncounterStatus(ENCOUNTERSTATUS_Admission);
			setC_Currency_ID(Env.getC_Currency_ID(ctx));
			setDateOrdered(Env.getCurrentDate());
			setDateAcct(getDateOrdered());
			setTipoArea(Env.getTipoArea(ctx)==null || Env.getTipoArea(ctx).trim().length()==0?TIPOAREA_Other:Env.getTipoArea(ctx));
			setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
			setAD_OrgTrx_ID(getEstServ().getAD_OrgTrx_ID());
			setEXME_Area_ID(getEstServ().getEXME_Area_ID());
		}
	}
	
	/**
	 * Constructor ResulSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECtaPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		if (getAD_OrgTrx_ID() <= 0) { //Lama
			if(getEXME_EstServ_ID() <= 0){
				setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
			}
			setAD_OrgTrx_ID(getEstServ().getAD_OrgTrx_ID());

			if(getEXME_Area_ID() <= 0){
				setEXME_Area_ID(getEstServ().getEXME_Area_ID());
			}
		}
	}
	
	/**
	 * Agregamos un nueva extension a la lista de extensiones relacionadas a la cuenta paciente.
	 * @param ext
	 */
	public void addExtension(final MEXMECtaPacExt ext) {
		final List<MEXMECtaPacExt> lst = getExtensiones();
		lst.add(ext);
		mCtasPacExt = new MEXMECtaPacExt[lst.size()];
		lst.toArray(mCtasPacExt);

	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		if(success){
			try {//Lama: actualizar indices
				QuickSearchTables.deleteIndexes(getCtx(), MEXMEPaciente.Table_Name, Table_Name);
			} catch (Exception ex) {
				log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
			}
		}
		return super.afterDelete(success);
	}
	
	/**
	 * After Save
	 * @param newRecord new
	 * @param success success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(!success){
			return false;
		}
		// insertamos en ActPaciente
		if (newRecord && !createActPaciente()) {
			return false;
		}
		/*Cambios para eCareSoft necesarios para el proceso de Billing
		1. Registrar en esta tabla todos los Medicos que se relacionan a cada Encuentro, 
		la funcionalidad requerida es igual a la desarrollada previamente para EXME_ActPacienteDiag, 
		es decir se mantienen los lugares de almacenamiento previo de EXME_Medico, 
		sin embargo se registran tambien en esta tabla.*/
		if(success){
			//De acuerdo a conversacion con Juan Carranza
			//un encuentro se identifica como cita por el tipo de Area.
			//Este tipo de area es exclusivamente usado en NIMBO.
			//Se almacena el EXME_Medico1 como Admitting.
			//y el EXME_Medico2 como Performing (El que ejecuta cita)
			//Actualmente ambos valores son identicos desde el proceso WApptProcesses
			if (MOrgInfo.isNimbo(getCtx(), Env.getAD_Org_ID(getCtx()))){
				
					if(!createCtaPacMed(newRecord, this.getEXME_Medico_ID(), 
							COLUMNNAME_EXME_Medico_ID, MEXMECtaPacMed.TYPE_Admitting)){
						return false;
					}
					if (!createCtaPacMed(newRecord, this.getEXME_Medico_ID(), 
							COLUMNNAME_EXME_Medico_ID, MEXMECtaPacMed.TYPE_Performing)){
						return false;
					}
				
			}else{
				if(!createCtaPacMed(newRecord, this.getEXME_Medico_ID(), COLUMNNAME_EXME_Medico_ID, MEXMECtaPacMed.TYPE_Admitting)){
					return false;
				}

				if(!createCtaPacMed(newRecord, this.getEXME_Medico2_ID(), COLUMNNAME_EXME_Medico2_ID, MEXMECtaPacMed.TYPE_Attending)){
					return false;
				}
				
				if(!createCtaPacMed(newRecord, this.getEXME_MedicoRefer_ID(), COLUMNNAME_EXME_MedicoRefer_ID, MEXMECtaPacMed.TYPE_Referring)){
					return false;
				}
			}
		}

		// si es nuevo registro
		if (newRecord && success) {
			
			MEXMEI18N i18n = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
			if(!i18n.ispatientFormMexico()){
				crearGuarantor();
			}
			// Los datos del socio de negocios (aseguradora o socio)
			final MBPartner socio = getBPartner();

			// Los datos de la lista de precios
			final MPriceList listaPrecios;
			if (socio.getM_PriceList_ID() > 0) {
				// lista de precios a partir del socio
				listaPrecios = new MPriceList(getCtx(), socio.getM_PriceList_ID(), get_TrxName());
			} else {
				// traer los datos de configPre
				listaPrecios = MEXMEConfigPre.getPriceList(getCtx(), null);
			}

			// insertamos en cta pac x extension
			if (!createCtaPacExt(socio, listaPrecios)) {
				return false;
			}

			// actualizamos la cta paciente actual con la moneda y lista de precios
			setC_Currency_ID(listaPrecios.getC_Currency_ID());
			setM_PriceList_ID(listaPrecios.getM_PriceList_ID());
			// setEXME_CtaPacExt_ID(cpe.getEXME_CtaPacExt_ID());

			if (!saveUpdate()) {
				log.saveError("Error", Utilerias.getMsg(getCtx(), "error.ts.noguardado")+" afterSave");
				return false;
			}

			// Buscar una solicitud de cirugia si hay agregar la relacion con la ctapac
			if (!MEXMEPreOperatorio.getSolicitudPac(getCtx(), getEXME_Paciente_ID(), getEXME_CtaPac_ID(), Constantes
					.sdfFecha(getCtx()).format(getCreated()), get_TrxName())) {
				log.saveError("Error", Utilerias.getMsg(getCtx(), "error.ts.noguardado")+" MEXMEPreOperatorio.getSolicitudPac");
				return false;
			}

			// bitacora de cambios
//			final MEXMECtaPacBitacora ctapacbit = new MEXMECtaPacBitacora(getCtx(), 0, get_TrxName());
//			ctapacbit.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
//			ctapacbit.setOpcion(MEXMECtaPacBitacora.OPCION_Open);
			if (!MEXMECtaPacBitacora.open(getCtx(), getEXME_CtaPac_ID(), get_TrxName())) {
				return false;
			}
			//La nota se guarda solo si el campo no esta vacio
			if (StringUtils.isNotBlank(getDescription())){
				 saveRegistrarNote();
			}
			//La nota se guarda solo si hubo cambios y el campo no esta vacio
		}else if (is_ValueChanged(COLUMNNAME_Description) && StringUtils.isNotBlank(getDescription())){
			saveRegistrarNote();
		}		//Lama: actualizar indices
		if(success){
			// Se regenera la vista EXME_Paciente_Cta_V solo para los campos que se muestran.
			if (newRecord
				|| is_ValueChanged(COLUMNNAME_DocumentNo)
				|| is_ValueChanged(COLUMNNAME_DateOrdered)
				|| is_ValueChanged(COLUMNNAME_FechaAlta)
				|| is_ValueChanged(COLUMNNAME_FechaCierre)
				|| is_ValueChanged(COLUMN_IsActive)
				|| is_ValueChanged(COLUMNNAME_EncounterStatus)){

				MEXMEPacienteCtaV.updateSearchCta(p_ctx, getEXME_CtaPac_ID(), get_TrxName());
				QuickSearchTables.checkTables(MEXMECtaPacV.class, 
						MEXMECtaPacV.Table_Name, getEXME_CtaPac_ID(), get_TrxName(), getCtx());
//			}  else if(!newRecord) {
//                // borramos los indices relacionados
//                QuickSearchTables.deleteIndexes(getCtx(), MEXMEPacienteCtaV.Table_Name, "ViewClosed_");
				// Se comenta por que ese subindice fue eliminado Dic. 2012 (Lama)
            }
		}
		
		// Se agregan las correctas validaciones.- Lama
		if (MEXMEPOS.isUpdateAfterSavePOS(success, this)) {
			// Copy the place of service to charges in case they don't have it
			final List<MCtaPacDet> lista = MCtaPacDet.getCuentaPacDetNotPOS(getCtx(), getEXME_CtaPac_ID(), null);
			for (MCtaPacDet ctaPacDet : lista) {
				ctaPacDet.setEXME_POS_ID(getEXME_POS_ID());
				if (!ctaPacDet.save(get_TrxName())) {
					log.severe(ctaPacDet.toString());
				}
			}
		}
		return success;
	} // afterSave
	
	/**
	 * 
	 */
	private void crearGuarantor() {
		
		MEXMEPacienteRelCatalog relCatPac = MEXMEPacienteRelCatalog.get(getCtx(), getEXME_Paciente_ID(), 
				MEXMEPacienteRelCatalog.TYPE_Responsible, Boolean.FALSE, getEXME_CtaPac_ID(), get_TrxName());
		MEXMEPacienteRelCatalog catalog = null;
		if (relCatPac.getEXME_PacienteRelCatalog_ID() > 0) {
			//Tenemos el Guarantor del paciente
			catalog = new MEXMEPacienteRelCatalog(getCtx(), 0, null);
			catalog.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
			catalog.setEXME_Paciente_ID(getEXME_Paciente_ID());
			catalog.setType(MEXMEPacienteRelCatalog.TYPE_Responsible);
			catalog.setIsDefault(true);
			catalog.setEXME_PacienteRel_ID(relCatPac.getEXME_PacienteRel_ID());
			catalog.setEXME_Parentesco_ID(relCatPac.getEXME_Parentesco_ID());

		}
		
		if (catalog != null){
			if (!catalog.save(get_TrxName())) {
				log.saveError("Error", "No se pudo crear PacienteRelCatalog para CtaPacID = "+ getEXME_CtaPac_ID());
			}
		}
		
	}

	/**
	 * Approve Document
	 * @return true if success
	 */
	public boolean approveIt() {
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	} // approveIt
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		boolean retValue = true;
		
		if (!newRecord && 
				!(Boolean)(get_ValueOld(COLUMNNAME_NoInsuranceCoverage) == null 
				? Boolean.FALSE 
				:get_ValueOld(COLUMNNAME_NoInsuranceCoverage)) 
			&& isNoInsuranceCoverage()){
			//Si no es registro nuevo, y antes NOINSURANCE = N 
			//y ahora NOINSURANCE = 'Y'
			//Mover el STEP a Self Pay de La Extension 0
			//Ticket 100735 gvaldez
			getExtCero().setInstitutionalStep(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
			getExtCero().setProfessionalStep(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
			if (!getExtCero().save()){
				s_log.severe("Fail Update Ext Zero changing " +
						"Insurance Coverage for CtaPacID = "+ getEXME_CtaPac_ID());
				return false;
			}
		}
		
		if(getEXME_Institucion_ID() == 0){
			setEXME_Institucion_ID(MOrgInfo.get(p_ctx, Env.getAD_Org_ID(p_ctx)).getEXME_Institucion_ID());
		}
		if(retValue) {
			// Copy the place of service from the type of patient in case it doesn't have it
			// Se agregan las correctas validaciones .- Lama
			MEXMEPOS.setPOSId(this, MEXMETipoPaciente.Table_Name);
		}
		return retValue; 
	} // beforeSave
	
	/***************************************************************************
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {
		//boolean isSOTrx = true;
		//MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		//String DocSubTypeSO = dt.getDocSubTypeSO();

		// Just prepare
		if (DOCACTION_Prepare.equals(getDocAction())) {
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}
		

		// Re-Check
		if (!mJustPrepared) {
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}
	

		log.info(toString());
		StringBuffer info = new StringBuffer();

		MCtaPacDet[] lines = getLines();
		//Alejandro. Actualiza la cantidad Aplicada y la fecha de aplicacion. Despues de realizar el shipment.
		for (MCtaPacDet linesPacDet: lines) {
			if (linesPacDet.getEXME_PlanMedLine_ID() > 0 /* && !devolucion*/) {				
				MPlanMedLine pml = new MPlanMedLine(linesPacDet.getCtx(),
					linesPacDet.getEXME_PlanMedLine_ID(), get_TrxName());				
				pml.setQtyAplied(linesPacDet.getQtyDelivered());
				if(pml.getApliedDate() == null) {
					pml.setApliedDate(linesPacDet.getDateOrdered());
				}
				if (!pml.save(get_TrxName())) {
					mProcessMsg = "No se actualizo la Programacion del Medicamento @EXME_PlanMedLine@ = " + pml.toString();
				}
			}
		}	
		// } // Shipment

		String valid = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			if (info.length() > 0) {
				info.append(" - ");
			}
			info.append(valid);
			mProcessMsg = info.toString();
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		mProcessMsg = info.toString();
		//
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	} // completeIt


	/**
	 * createActPaciente
	 * @return
	 */
	public boolean createActPaciente(){
		final MEXMEActPaciente ap = new MEXMEActPaciente(getCtx(), 0, get_TrxName());
		MEXMEActPaciente.copyValues(this, ap);
		ap.setName(getDocumentNo());
		ap.setFecha(getDateOrdered());
		ap.setProcessing(false);
		ap.setProcessed(true);
//		ap.setPosted(false);
		ap.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		final boolean saved = ap.save();
		if (saved) {
			actividades = new MEXMEActPaciente[] { ap };
		} else {
			log.saveError("Error", Utilerias.getMsg(getCtx(), "error.facturaDirecta.createActividad"));
		}
		return saved;
	}

	public MEXMEActPaciente createActPaciente(String trxName, int progQuiroId) {
		final MEXMEActPaciente ap = new MEXMEActPaciente(getCtx(), 0, trxName);
		MEXMEActPaciente.copyValues(this, ap);
		ap.setName(getDocumentNo());
		ap.setFecha(getDateOrdered());
		ap.setProcessing(false);
		ap.setProcessed(true);
//		ap.setPosted(false);
		ap.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		ap.setEXME_ProgQuiro_ID(progQuiroId);
		if (ap.save(trxName)) {
			log.saveError("Error", Utilerias.getMsg(getCtx(), "error.facturaDirecta.createActividad"));
		}
		return ap;
	}

	/**
	 * Creación de extension
	 * @param trxName
	 * @return
	 */
	public MEXMECtaPacExt createCtaPacExt(final String trxName){
		MEXMECtaPacExt newExt = new MEXMECtaPacExt(this, trxName);
		return newExt.save(trxName)?newExt:null;
	}

	/**
	 * createCtaPacExt
	 * @param socio
	 * @param listaPrecios
	 * @return
	 */
	public boolean createCtaPacExt(final MBPartner socio, final MPriceList listaPrecios){

		final MEXMECtaPacExt cpe = new MEXMECtaPacExt(getCtx(), 0, get_TrxName());
		MEXMECtaPacExt.copyValues(this, cpe);
		cpe.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		cpe.setDataExtCero();

		if (!cpe.save()) {
			log.saveError("Error", Utilerias.getMsg(getCtx(), "error.ts.noguardado")+" createCtaPacExt");
			return false;
		}

		// agregamos la extension a la lista
		addExtension(cpe);
		setEXME_CtaPacExt_ID(cpe.getEXME_CtaPacExt_ID());
		return true;
	}

	/**
	 * Se el encounter es nuevo o se modificó alguna columna de medicos relacionados 
	 * Creamos un MexmeCtaPacMed para guardar esta relación 
	 * @param newRecord
	 * @param medicoID
	 * @param columnIndex
	 * @return boolean de exito al guardar el registro
	 */
	public boolean createCtaPacMed(boolean newRecord, int medicoID, String columnIndex, String type){
		boolean exito=true;
		if ((newRecord || is_ValueChanged(columnIndex))
				&& medicoID > 0) {

			List<MEXMECtaPacMed> list = MEXMECtaPacMed.getActive(this.getEXME_CtaPac_ID(), type, get_TrxName());
			for(int i=0; i<list.size(); i++){
				MEXMECtaPacMed existing = list.get(i);
				existing.setIsActive(false);
				
				if(!existing.save()){
					exito=false;
				}
			}
			
			MEXMECtaPacMed ctaPacMed = new MEXMECtaPacMed(getCtx(), 0, get_TrxName());
			ctaPacMed.setEXME_CtaPac_ID(this.getEXME_CtaPac_ID());
			ctaPacMed.setEXME_Medico_ID(medicoID);
			ctaPacMed.setAD_Table_ID(MEXMECtaPac.Table_ID);
			ctaPacMed.setRecord_ID(this.getEXME_CtaPac_ID());
			ctaPacMed.setSequence(MEXMECtaPacMed.getSequence(this.getEXME_CtaPac_ID(), type, get_TrxName()));
			ctaPacMed.setType(type);
			ctaPacMed.setColumnName(MEXMECtaPac.Table_Name);
			MEXMEMedico med = new MEXMEMedico(getCtx(), medicoID, null);
			ctaPacMed.setPhysicianName(med.getNombre_Med());
			if(med.getEspecialidades()!=null && med.getEspecialidades().size()>0)
				ctaPacMed.setEXME_Especialidad_ID(med.getEspecialidades().get(0).getEXME_Especialidad_ID());
				
			if(!ctaPacMed.save()){
				exito =false;
			}	
		}
		return exito;

	}
	
	/**
	 * Elimina la cuenta paciente: Noelia 
	 * @param trxName
	 * @throws Exception
	 */
	public void deleteCtaPac(String trxName)  {

		if (getEXME_CtaPac_ID() > 0) {

			validarMovimientos(getCtx(), null);

			// La lista de paquetes que se le hayan asignado
			final List<MEXMECtaPacPaq> paqList = MEXMECtaPacPaq.getFromCtaPac(getCtx(), getEXME_CtaPac_ID(), null, trxName);
			for (int i = 0; i < paqList.size(); i++) {//TODO tarda
				if (!paqList.get(i).delete(true, trxName)) {
					s_log.log(Level.SEVERE, "Cannot delete MEXMECtaPacPaq ");
				}
			}

			// Obtenemos los Datos Complementarios de la cuenta paciente
			final MCtaPacDatos datos = MCtaPacDatos.getCtaPacD(this);
			if (datos != null && !datos.delete(true, trxName)) {
				s_log.log(Level.SEVERE, "Cannot delete MCtaPacDatos ");
			}

			// La Actividad Paciente
			final MEXMEActPaciente act = MEXMEActPaciente.getActPaciente(getCtx(), getEXME_CtaPac_ID(), null, trxName);
			if (act != null && !act.delete(true, trxName)) {
				s_log.log(Level.SEVERE, "Cannot delete MEXMEActPaciente ");
			}

			// La extension cero de la cuenta paciente
			final MEXMECtaPacExt ctaExt = MEXMECtaPacExt.getExtCero(getCtx(), getEXME_CtaPac_ID(), trxName);
			if (ctaExt != null) {
				this.setEXME_CtaPacExt_ID(0);
				if (!this.save(trxName)) {
					//				throw new DBException("encCtaPac.noDeleteCtaPac");
					throw new MedsysException();
				}
				// TODO : que pasa si no la puede borrar? no se trata de alguna manera especial?
				if (!ctaExt.delete(true, trxName)) {
					s_log.log(Level.SEVERE, "Cannot delete MEXMECtaPacExt DocumentNo: " + ctaExt.getDocumentNo() + ", ExtensionNo: "
							+ ctaExt.getExtensionNo() + ", EXME_CtaPac_ID: " + ctaExt.getEXME_CtaPac_ID());
				}
			}

			// bitacora de ctapac
			MEXMECtaPacBitacora[] ctaBit = MEXMECtaPacBitacora.get(getCtx(), getEXME_CtaPac_ID(), trxName);
			for (int i = 0; i < ctaBit.length; i++) {
				if (!ctaBit[i].delete(true, trxName)) {
					s_log.log(Level.SEVERE, "Cannot delete MEXMECtaPacBitacora ");
				}
			}

			// Bitacora HistCama : Noelia
			MEXMEHistCama[] listHist = MEXMEHistCama.get(getCtx(), getEXME_CtaPac_ID(), trxName);
			for (int i = 0; i < listHist.length; i++) {
				if (!listHist[i].delete(true, trxName)) {
					s_log.log(Level.SEVERE, "Cannot delete MEXMEHistCama ");
				}
			}

			// Si se le asigno una cama, se pasa a disponible limpia debido a que se trata de un error (Por eso se elimina la cuenta)
			MEXMECama.releaseBed(getCtx(), getEXME_CtaPac_ID(), trxName);

			// ctapac Changes : Lama
			DB.executeUpdate("DELETE EXME_CtaPacChanges WHERE EXME_CtaPac_ID=?", getEXME_CtaPac_ID(), false, trxName);

			if (!this.delete(true, trxName)) {
				//			throw new DBException("encCtaPac.noDeleteCtaPac");
				throw new MedsysException();
			}
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param motive
	 * @param trxName
	 * @throws Exception
	 */
	public void setPreDischarge(final Timestamp date) {
		log.config("pre discharge: " + get_ID());
		setFechaPrealta(date);
		setActualizadoPrealta(Env.getAD_User_ID(getCtx()));
		// cambiar el estatus a pre alta solo si esta como admision
		if (X_EXME_CtaPac.ENCOUNTERSTATUS_Admission.equals(getEncounterStatus())) {
			setEncounterStatus(ENCOUNTERSTATUS_Predischarge);
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param motive
	 * @param trxName
	 * @throws Exception
	 */
	public void discharge(final Timestamp date, final int motive, boolean close, final String trxName) throws Exception {
		log.config("discharge: " + get_ID());

		// actualizar el estatus a "Cerrado" y cama a NULL
		if (ENCOUNTERSTATUS_Predischarge.equals(getEncounterStatus()) || ENCOUNTERSTATUS_Admission.equals(getEncounterStatus())) {
			setEncounterStatus(ENCOUNTERSTATUS_Discharge);
		}
		if (close) {
			close(motive, Env.getCurrentDate(), trxName);
		}
		final int bedID = getEXME_Cama_ID();
		// remove bed - patient account reference
		setEXME_Cama_ID(0);//Defecto #3084
		setFechaAlta(date);

		if (!save(trxName)) {
			throw new MedsysException();
		}
		// actualizar el estatus de la cama a "Disponible Sucia" y cuenta a NULL
		// al cerrar la cuenta, liberamos la habitacion .- Lama
		MEXMECama.releaseBed(getCtx(), get_ID(), trxName);

		// Noelia: actualizamos la bitacora de cama, para saber la
		// ultima cama en la que estuvo el paciente
		if (bedID > 0) {
			MEXMEHistCama.guardar(getCtx(), 0, bedID, get_ID(), trxName);
		}
	}
	
	public void close(final int motive, final Timestamp close, final String trxName) {
		setEXME_MotivoEgreso_ID(motive);
		setFechaCierre(close);
//		// log
//		final MEXMECtaPacBitacora ctapacbit = new MEXMECtaPacBitacora(getCtx(), 0, trxName);
//		ctapacbit.setEXME_CtaPac_ID(get_ID());
//		ctapacbit.setOpcion(X_EXME_CtaPacBitacora.OPCION_Close);
		MEXMECtaPacBitacora.close(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
	}
	
	/**
	 * Obtenemos una lista de cuentas paciente para el estatus especificado y
	 * la cuenta paciente y paciente actual
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public MEXMECtaPac[] get(Properties ctx, String estatus, String tipoArea, String trxName) {
//		final List<Object> params = new ArrayList<Object>();
//		StringBuilder condicion = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		condicion.append(" AND EXME_CtaPac.EXME_Paciente_ID = ? ");
//		params.add(getEXME_Paciente_ID());
//
//		if (getEXME_CtaPac_ID() != 0) {
//			condicion.append(" AND EXME_CtaPac.EXME_CtaPac_ID <> ? ");
//			params.add(getEXME_CtaPac_ID());
//		}
//
//		condicion.append(" AND EXME_CtaPac.EncounterStatus = ? ");
//		params.add(estatus);
//		condicion.append(" AND EXME_CtaPac.TipoArea = ? ");
//		params.add(tipoArea);
//
//		return MEXMECtaPac.get(ctx, condicion, null, trxName, params.toArray());
		return get(ctx, getEXME_Paciente_ID(), getEXME_CtaPac_ID(), estatus, tipoArea, trxName);
	}
	
	/**
	 * Obtenemos una lista de cuentas paciente para el estatus especificado y
	 * la cuenta paciente y paciente actual
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public static MEXMECtaPac[] get(Properties ctx, int exme_paciente_id, int exme_ctapac_id,
			String estatus, String tipoArea, String trxName) {
		final List<Object> params = new ArrayList<Object>();
		StringBuilder condicion = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// buscamos las cuentas paciente del paciente seleccionado
		condicion.append(" AND EXME_CtaPac.EXME_Paciente_ID = ? ");
		params.add(exme_paciente_id);
		// excluimos la cuenta actual
		if (exme_ctapac_id > 0) {
			condicion.append(" AND EXME_CtaPac.EXME_CtaPac_ID <> ? ");
			params.add(exme_ctapac_id);
		}
		// busca por estatus
		condicion.append(" AND EXME_CtaPac.EncounterStatus = ? ");
		params.add(estatus);
		// valida el tipo de area
		condicion.append(" AND EXME_CtaPac.TipoArea = ? ");
		params.add(tipoArea);
		return MEXMECtaPac.get(ctx, condicion, null, trxName, params.toArray());
	}

	
	/**
	 * Obtenemos las actividades de la cuenta paciente.
	 * @param reQuery
	 * @return
	 */
	public List<MEXMEActPaciente> getActividades() {

		List<MEXMEActPaciente> list;

		MEXMEActPaciente[] actividades = getActividades(false);

		if (actividades == null) {
			list = new ArrayList<MEXMEActPaciente>();
		} else {
			list = new ArrayList<MEXMEActPaciente>(Arrays.asList(actividades));
		}

		return list;
	}

	/**
	 * Obtenemos los planes de medicamentos de la cuenta paciente.
	 * @param reQuery
	 * @return
	 */
	public MEXMEActPaciente[] getActividades(boolean reQuery) {

		if (actividades == null || actividades.length == 0 || reQuery) {
			actividades = getActividades(null);
		}

		return actividades;
	}
	
	/**
	 * Obtenemos los planes de medicamentos de la cuenta paciente.
	 * @return
	 */
	public MEXMEActPaciente[] getActividades(String whereClause) {


		ArrayList<MEXMEActPaciente> list = new ArrayList<MEXMEActPaciente>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_ActPaciente.* FROM EXME_ActPaciente ")
		.append(" WHERE EXME_ActPaciente.EXME_CtaPac_ID = ? ")
		.append(" AND EXME_ActPaciente.IsActive = 'Y' ");

		if (whereClause != null) {
			sql.append(whereClause);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",MEXMEActPaciente.Table_Name));

		sql.append(" ORDER BY EXME_ActPaciente.EXME_ActPaciente_ID ASC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEActPaciente actividades = new MEXMEActPaciente(getCtx(), rs, get_TrxName());
				actividades.setCtaPac(this);
				list.add(actividades);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		MEXMEActPaciente[] actividades = new MEXMEActPaciente[list.size()];
		list.toArray(actividades);

		return actividades;

	}
	
//	/**
//	 * Obtenemos el Socio de Negocios del Paciente de la Cuenta Paciente.
//	 * @return
//	 */
//	public MBPartner getBPartner() {
////		try {
//			if(bpartner == null){
////				bpartner = getPaciente().getBPartner();
//				bpartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
//			}
////		} catch (Exception e) {
////			s_log.log(Level.SEVERE, "getBPartner() - error", e);
////		}
//		return bpartner;
//	}

	
	/**
	 * Obtenemos la cama asignada a la cuenta paciente
	 * @return
	 */
	public MEXMECama getCama() {
		if (getEXME_Cama_ID() > 0 && (cama == null  || cama.get_ID() != getEXME_Cama_ID())) {
			cama = new MEXMECama(getCtx(), getEXME_Cama_ID(), get_TrxName());
		}
		return cama;
	}
	
	/**
	 * Obtenemos el Socio de Negocios del Paciente de la Cuenta Paciente.
	 * @return
	 */
	public MBPartner getBPartner() {
		if (mBPartner == null && getPaciente() != null) {
//			// Los datos del socio de negocios (aseguradora o socio)
//			if (getPaciente().isEsAsegurado() && getPaciente().getC_BPartner_Seg_ID() != 0) {
//				// traer los datos de la aseguradora
//				cliente = new MBPartner(getCtx(), getPaciente().getC_BPartner_Seg_ID(), get_TrxName());
//			} else {
				// no esta asegurado, traer el socio del paciente
				mBPartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
//			}
		}
		return mBPartner;
	}
	
	/**
	 * 
	 * @return
	 */
	public MEXMEConsentimientoPac getConsentimientoPac() {
		if(consentimientoPac==null){
			consentimientoPac = MEXMEConsentimientoPac.getCtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
			if(consentimientoPac!=null)
				resStatusOld = consentimientoPac.getResStatus();
		} 
		return this.consentimientoPac;
	}
	
	/**
	 * 
	 * @return
	 */
	public MEXMEConsentimientoPac getConsentimientoPacForce() {
			consentimientoPac = MEXMEConsentimientoPac.getCtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		return this.consentimientoPac;
	}
	
	/**
	 * Obtenemos los datos de la tabla ctaPacDatos.
	 * @return
	 */
	public MCtaPacDatos getCtasPacDatos() {
		if(ctasPacDatos == null){
			getCtasPacDatos(false);
		}
		return ctasPacDatos;
	}
	
	/**
	 * Obtenemos los datos de la tabla ctaPacDatos.
	 * @param reQuery Si se quiere que se consulte a la base de datos nuevamente
	 * @return
	 */
	public MCtaPacDatos getCtasPacDatos(boolean reQuery) {
		try {
			if (ctasPacDatos == null || reQuery) {
				ctasPacDatos = MCtaPacDatos.getCtaPacD(this);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

		return ctasPacDatos;
	}

	/**
	 * Obtenemos las cuentas paciente relacionadas a una cuenta paciente
	 * @param ctx El contexto de la aplicacion
	 * @param estatus El estatus de la cuenta paciente
	 * @param value Filtrar por value del paciente
	 * @param trxName Nombre de la transacci&oacute;n
	 * @return
	 * @deprecated will be removed, it was only used for new born functionality
	 */
	
	public MEXMECtaPac[] getCtasRel(Properties ctx, boolean onlyCtaPac, Integer[] parentescoID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		sql.append(" SELECT EXME_CtaPac.*, p.EXME_Paciente_ID as Paciente_ID ");
		sql.append("FROM EXME_Paciente p ");
		// EXME_CtaPac
		sql.append(onlyCtaPac ? " INNER " : " LEFT ");
		sql.append(" JOIN EXME_CtaPac  ON (p.EXME_Paciente_ID = EXME_CtaPac.EXME_Paciente_ID  ");
		sql.append(" AND EXME_CtaPac.EncounterStatus = ").append(DB.TO_STRING(MEXMECtaPac.ENCOUNTERSTATUS_Admission));
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" AND EXME_CtaPac.IsActive = 'Y' ) ");
		MEXMEI18N objcReg = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
		if (objcReg != null && objcReg.isResponsibleMexico()) {
			sql.append(" INNER JOIN EXME_PacienteRel r ON  (  ( p.EXME_Paciente_ID = r.EXME_Paciente1_ID ");
			sql.append(" OR p.EXME_Paciente_ID = r.EXME_Paciente2_ID ) ");
			if(parentescoID != null && parentescoID.length > 0 ){
				sql.append(" AND r.EXME_parentesco_ID IN ( "); //1...
				for (int i = 0; i < parentescoID.length; i++) {
					if(i>0) {
						sql.append(" , ");
					}
					sql.append(" ? ");
				}
				sql.append(" ) ");
			}
		}
		else {
			sql.append(" EXME_PacienteRelCatalog ON EXME_PacienteRelCatalog.exme_paciente_id = p.exme_paciente_id ")
			.append(" INNER JOIN EXME_PacienteRel ON r.exme_pacienterel_id = EXME_PacienteRelCatalog.exme_pacienterel_id ");
			if(parentescoID != null && parentescoID.length > 0 ) {
				sql.append(" AND EXME_PacienteRelCatalog.EXME_parentesco_ID IN ( "); 
				for (int i = 0; i < parentescoID.length; i++) {
					if(i>0) {
						sql.append(" , ");
					}
					sql.append(" ? ");
				}
				sql.append(" ) ");
			}
		}
		
		sql.append(") ");
		sql.append(" WHERE p.isActive = 'Y' AND P.EXME_Paciente_ID <> ? ");// 2
		sql.append(" AND ( CASE WHEN EXME_CtaPac.EXME_CtaPac_ID > 0 THEN EXME_CtaPac.ref_CtaPac_ID ELSE 0 END = ? "); //3
		sql.append(" OR ");
		sql.append(" CASE WHEN r.EXME_PacienteRel_ID > 0 AND r.EXME_Paciente1_ID = P.EXME_Paciente_ID THEN r.EXME_Paciente2_ID ");
		sql.append(" WHEN r.EXME_PacienteRel_ID > 0 AND r.EXME_Paciente2_ID = P.EXME_Paciente_ID THEN r.EXME_Paciente1_ID ");
		sql.append(" ELSE 0 END = ? )"); //4
		sql.append("ORDER BY NVL(EXME_CtaPac.DocumentNo,p.Nombre_Pac) DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			for (Integer id : parentescoID) {
				pstmt.setInt(i++, id);
			}
			pstmt.setInt(i++, getEXME_Paciente_ID());
			pstmt.setInt(i++, getEXME_CtaPac_ID());
			pstmt.setInt(i++, getEXME_Paciente_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				if(ctaPac.getEXME_CtaPac_ID() <= 0){
					ctaPac.setPaciente(new MEXMEPaciente(ctx, rs.getInt("Paciente_ID"), trxName));
				}
				list.add(ctaPac);
			}
		} catch (Exception e) {			
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}

		MEXMECtaPac[] ctasPac = new MEXMECtaPac[list.size()];
		list.toArray(ctasPac);
		return ctasPac;
	}
	
	
	/**
	 * Obtiene los recien nacidos relacionados con una cuenta paciente
	 * @param ctx
	 * @param parentescoID
	 * @param trxName
	 * @return
	 */
	public ArrayList<MEXMECtaPac> getNewBorns(Properties ctx, Integer[] parentescoID, boolean onlyCtaPac, String trxName) {
		
		ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT DISTINCT EXME_CTAPAC.*, EXME_PACIENTE.EXME_Paciente_ID as Paciente_ID ");
		sql.append("FROM EXME_PACIENTE ");
		sql.append("INNER JOIN EXME_PACIENTEREL ON EXME_PACIENTEREL.EXME_PACIENTE2_ID = EXME_PACIENTE.EXME_PACIENTE_ID ");
		sql.append("AND EXME_PACIENTEREL.EXME_PACIENTE1_ID = ? ");
		if(parentescoID.length >0){
			sql.append("AND EXME_PACIENTEREL.EXME_PARENTESCO_ID IN (");
			sql.append(DB.TO_STRING(parentescoID)).append(") ");
			
		}
		if(onlyCtaPac){
			sql.append("INNER  JOIN ");
		}else{
			sql.append("LEFT  JOIN ");
		}
		sql.append("EXME_CTAPAC  ON (EXME_PACIENTE.EXME_Paciente_ID = EXME_CtaPac.EXME_Paciente_ID  ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" AND EXME_CTAPAC.ISACTIVE = 'Y' ) ");
		sql.append("WHERE EXME_PACIENTE.ISACTIVE = 'Y' AND EXME_PACIENTE.EXME_PACIENTE_ID <> ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			pstmt.setInt(i, getEXME_Paciente_ID());
			i++;
			for (Integer id : parentescoID) {
				pstmt.setInt(i++, id);
			}
			pstmt.setInt(i++, getEXME_Paciente_ID());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs, trxName);
				ctaPac.setEXME_Paciente_ID(rs.getInt("Paciente_ID")); //se corrige el error que se producia al hacer zoom a un paciente RN
				list.add(ctaPac);
			}
		} catch (Exception e) {			
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
			
		
		return list;
	
	}

	/**
	 * Obtenemos el detalle de la cuenta paciente.
	 * @return
	 * 
	 */
	public MCtaPacDet[] getDetalle(String whereClause) {
		List<MCtaPacDet> list = getLstDetalle(whereClause);
		
		MCtaPacDet[] ctasPacDet = new MCtaPacDet[list.size()];
		list.toArray(ctasPacDet);

		return ctasPacDet;
	}
	
	/**
	 * 
	 * @return
	 */
	public MEXMEPrescDieta getDiet() {
		return getDiet(false);
	}
	
	/**
	 * 
	 * @param requery
	 * @return
	 */
	public MEXMEPrescDieta getDiet(boolean requery) {
		if(mDiet == null || requery){
			mDiet = MEXMEPrescDieta.getActual(getCtx(), getEXME_CtaPac_ID(), get_TrxName());//
		}
		return mDiet;
	}
	
	/**
	 * El diario de enfermeria relacionado a la cuenta paciente
	 * @return
	 * @deprecated una cuenta paciente puede tener múltiples diarios.
	 *
	public MEXMEDiarioEnf getEnfDiary() {
		if(diary == null) {
			diary = MEXMEDiarioEnf.getDiario(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return diary;
	}*/
	/**
	 * Estacion de servicio relacionada
	 * a la cuenta paciente
	 * @return
	 */
	public MEXMEEstServ getEstServ() {
		if (estServ == null && getEXME_EstServ_ID() > 0) {
			estServ = new MEXMEEstServ(getCtx(), getEXME_EstServ_ID(), get_TrxName());
		}
		return estServ;
	}
	
	/**
	 * Estacion de servicio de ingreso
	 * de la cuenta paciente
	 * @return
	 */
	public MEXMEEstServ getEstServIng() {
		if (estServIng == null && getEXME_EstServIng_ID() > 0) {
			estServIng = new MEXMEEstServ(getCtx(), getEXME_EstServIng_ID(), get_TrxName());
		}
		return estServIng;
	}
	
	/**
	 * Obtenemos las extenciones de la cuenta paciente.
	 * @param reQuery
	 * @return
	 */
	public List<MEXMECtaPacExt> getExtensiones() {
		final List<MEXMECtaPacExt> list;
		final MEXMECtaPacExt[] ctasPacExt = getExtensiones(false);
		if (ctasPacExt == null) {
			list = new ArrayList<MEXMECtaPacExt>();
		} else {
			list = new ArrayList<MEXMECtaPacExt>(Arrays.asList(ctasPacExt));
		}
		return list;
	}
	
	/**
	 * Obtenemos las extenciones de la cuenta paciente.
	 * @param reQuery Si se quiere que se consulte a la base de datos nuevamente
	 * @return
	 */
	public MEXMECtaPacExt[] getExtensiones(boolean reQuery) {
		if (mCtasPacExt == null || mCtasPacExt.length == 0 || reQuery) {
			mCtasPacExt = MEXMECtaPacExt.get(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());  
		}
		return mCtasPacExt;
	}
	
	/**
	 * Obtenemos las extenciones de la cuenta paciente.
	 * @return
	 */
	public MEXMECtaPacExt[] getExtensiones(String whereCluse) {
		return  MEXMECtaPacExt.get(getCtx(), getEXME_CtaPac_ID(), whereCluse, get_TrxName());  
	}
	
	/**
	 * Get Lines of Order. (useb by web store)
	 * @return lines
	 */
	public MCtaPacDet[] getLines() {
		return getLinesCta(false, null);
	} // getLines
	
	/**
	 * Get Lines of Order
	 *
	 * @param requery requery
	 * @param orderBy optional order by column
	 * @return lines
	 */
	public MCtaPacDet[] getLines(boolean requery, String orderBy) {
		if (mLines != null && !requery) {
			return mLines;
		}
		//
		StringBuilder orderClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		orderClause.append(" ORDER BY EXME_CtaPacDet.");
		if (orderBy != null && orderBy.length() > 0) {
			orderClause.append(orderBy);
		} else {
			orderClause.append("Line ");
		}
		mLines = getLines("AND EXME_CtaPacDet.C_UOM_ID is not null AND EXME_CtaPacDet.M_Product_Id is not null AND EXME_CtaPacDet.CgoProcesado = 'N' ", 
				orderClause.toString());
		return mLines;
	} // getLines
	/**
	 * Get Lines of Order
	 *
	 * @param whereClause where clause or null (starting with AND)
	 * @param orderClause order clause
	 * @return lines
	 */
	public MCtaPacDet[] getLines(String whereClause, String orderClause) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacDet.* FROM EXME_CtaPacDet WHERE EXME_CtaPacDet.EXME_CtaPac_ID=? ");

		if (whereClause != null) {
			sql.append(whereClause);
		}

		sql .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",MCtaPacDet.Table_Name));

		if (orderClause != null) {
			sql.append(" ").append(orderClause);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacDet ol = new MCtaPacDet(getCtx(), rs, get_TrxName());
				ol.setHeaderInfo(this);
				list.add(ol);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		MCtaPacDet[] lines = new MCtaPacDet[list.size()];
		list.toArray(lines);
		return lines;
	} // getLines
	
	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MCtaPacDet[] getLinesCta(boolean requery, String orderBy) {
		if (mLines == null || requery) {
			//
			StringBuilder orderClause = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			orderClause.append(" ORDER BY EXME_CtaPacDet.");
			if (orderBy != null && orderBy.length() > 0) {
				orderClause.append(orderBy);
			} else {
				orderClause.append("Line");
			}
			mLines = getLines(null, orderClause.toString());
		}
		return mLines;
	} // getLines
	
	/**
	 * Obtenemos la direccion del el Socio de Negocios del Paciente de la Cuenta Paciente.
	 * @return
	 */
	public MLocation getLocation() {
		if(location == null && getBPartner() != null){
			// obtenemos la direccion del socio
			MBPartnerLocation[] locations = getBPartner().getLocations(true);
			if (locations.length > 0) {
				location = locations[0].getLocation(false);
			}
		}
		return location;
	}
	
	/**
	 * Obtenemos el detalle de la cuenta paciente.
	 * @return
	 */
	public List<MCtaPacDet> getLstDetalle(String whereClause) {
		List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacDet.* FROM EXME_CtaPacDet WHERE EXME_CtaPacDet.EXME_CtaPac_ID = ? ")
		.append(" AND EXME_CtaPacDet.IsActive = 'Y' ");

		if (whereClause != null) {
			sql.append(whereClause);
		}
		sql .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",MCtaPacDet.Table_Name));

		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacDet ctaPacDet = new MCtaPacDet(getCtx(), rs, get_TrxName());
				ctaPacDet.setCtaPac(this);
				list.add(ctaPacDet);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;

	}
	
	/**
	 * 
	 * @return
	 */
	public String getM_processMsg() {
		return mProcessMsg;
	}
	
	/**
	 * Warehouse
	 * @return int Warehouse id
	 */
	public int getM_Warehouse_ID() {
		return m_Warehouse_ID;
	}
	
	/**
	 * Las indicaciones medicas relacionadas a la cuenta paciente
	 * @return
	 */
//	public MEXMEIndicacionesMed getMedicalIndications(){
//		if (med == null && !is_new()) {
//			med = MEXMEIndicacionesMed.get(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
//			if (med == null) {
//				med = new MEXMEIndicacionesMed(getCtx(), 0, null);
//				med.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
//			}
//			med.fill();
//		}
//		return med;
//	}
	/**
	 * Obtenemos el Medico de la Cuenta Paciente.
	 * @return
	 */
	public MEXMEMedico getMedico() {
		if (medico == null && getEXME_Medico_ID() > 0) {
			medico = new MEXMEMedico(getCtx(), getEXME_Medico_ID(), get_TrxName());
		}
		return medico;
	}
	
	/**
	 * Obtenemos el Medico (Attending) de la Cuenta Paciente.
	 * @return
	 */
	public MEXMEMedico getMedico2() {
		if (attending == null && getEXME_Medico2_ID() > 0) {
			attending = new MEXMEMedico(getCtx(), getEXME_Medico2_ID(), get_TrxName());
		}
		return attending;
	}
	/**
	 * Obtenemos el Motivo de la Cita
	 * @return
	 */
	public MEXMEMotivoCita getMotivoCita() {
		if (motivoCita == null && getEXME_MotivoCita_ID() > 0) {
			motivoCita = new MEXMEMotivoCita(getCtx(), getEXME_MotivoCita_ID(), get_TrxName());
		}
		return motivoCita;
	}
	/**
	 * obtenemos los pedidos solicitados y aun no aplicados
	 * @return
	 */
	public List<Movement> getMovementNoAplicados() {
		log.config("getMovementNoAplicados(): "+get_ID());
		List<Movement> lista = new ArrayList<Movement>();

		try {			
			lista.addAll(getMovements(getCtx(),  getEXME_CtaPac_ID(), get_TrxName(),//
				" AND M_Movement.DocStatus IN (?,?)", MMovement.DOCSTATUS_Drafted, MMovement.DOCSTATUS_InProgress));

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECtaPac.getMovementNoAplicados ", e);
		}
		return lista;
	}
	
	/**
	 * obtenemos las prescripciones de medicamentos pendientes por aplicar
	 * 
	 * @return
	 */
	public List<MPlanMedLine> getPendingMedications() {
		log.config("getPendingMedications: "+get_ID());
		return MPlanMedLine.getLinesPrescRX(getCtx(), getEXME_CtaPac_ID(),//
				null, null, null, null, // dates / prn / doctors
				false, MPlanMedLine.ESTATUS_Prescribed);
	}

	/**
	 * Valida si cuenta con prescripciones (MP / Planificadas) pendientes por
	 * aplicar
	 * 
	 * @return
	 */
	public boolean hasPendingMedications() {
		log.config("hasPendingMedications: "+get_ID());
		return MPlanMedLine.getCountPrescRX(getCtx(), getEXME_CtaPac_ID(),//
				null, null, MPlanMedLine.ESTATUS_Prescribed) > 0;
	}

	/**
	 * Obtenemos el Paciente de la Cuenta Paciente.
	 *
	 * @return
	 */
	public MEXMEPaciente getPaciente() {
		if (paciente == null && getEXME_Paciente_ID() > 0) {
			paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		return paciente;
	}
	
	/**
	 * Socio de negocio, tiene prioridad la aseguradora
	 * @return
	 */
	public int getPatientBPartner() {
		int retValue = -1;
		// if insurance
		if (getPaciente() != null) {
			if (getPaciente().isEsAsegurado()) {
				// first priority
				MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getFirstPriority(getCtx(), getEXME_Paciente_ID(),
						get_TrxName());
				retValue = aseg == null ? -1 : aseg.getPriority() == 1 ? aseg.getC_BPartner_ID() : -1;
			} else {
				retValue = getC_BPartner_ID();// Por cuenta paciente
			}
		}
		return retValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPendingObxCount() {
		return pendingObxCount;
	}
	
	/** Get Resuscitative Status.
	@return Resuscitative Status */	  
	public String getResStatus (String value) 
	{
		if(value!=null){
			resStatusStr = MRefList.getListName(getCtx(), X_EXME_ConsentimientoPac.RESSTATUS_AD_Reference_ID, value);
		}
		return resStatusStr;
	}

	/** Get Resuscitative Status.
	@return Resuscitative Status */	  
	public String getResStatusForce () 
	{
		if (getConsentimientoPac() != null) {
			resStatus = getConsentimientoPac().getResStatus();
		}
		return resStatus;
	}

	/**
	 * 
	 * @return
	 */
	public String getResStatusOld() {
		return this.resStatusOld;
	}

//	/**
//	 * buscar la primera aseguradora de la cuenta paciente
//	 * si no se encuentra sera el paciente
//	 * @return
//	 */
//	public int getCtaPatientBPartner() {
//		int retValue = -1;
//		// if insurance
//		if (getPaciente() != null) {
//			if (getPaciente().isEsAsegurado()) {
//				// first priority
//				MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getFirstPriority(getCtx(), getEXME_Paciente_ID(),
//						getEXME_CtaPac_ID(), get_TrxName());
//				retValue = aseg == null ? -1 : aseg.getPriority() == 1 ? aseg.getC_BPartner_ID() : -1;
//			} else {
//				retValue = getPaciente().getPatientBPartner().get_ID();
//			}
//		}
//		return retValue;
//	}

//	/**
//	 * getSubsequentAseg
//	 * @param cbPartnerAsegID
//	 * @return
//	 */
//	public int getSubsequentAseg(int cbPartnerAsegID) {
//		int retValue = -1;
//		int priority = 0;
//		if (cbPartnerAsegID > 0) {
//			priority = DB.getSQLValue(null,
//					"SELECT Priority FROM EXME_PacienteAseg WHERE EXME_Paciente_ID=? AND C_BPartner_ID=?",
//					getEXME_Paciente_ID(), cbPartnerAsegID);
//
//		}
//		MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getFromPriority(getCtx(), getEXME_Paciente_ID(), priority < 0 ? 1
//				: ++priority, get_TrxName());
//		retValue = aseg == null ? -1 : aseg.getC_BPartner_ID();
//		return retValue;
//	}

	/**
	 * 
	 * @return
	 */
	public MEXMETipoPaciente getTipoPaciente() {
		if(mTipoPac==null){
			mTipoPac = new MEXMETipoPaciente(getCtx(), getEXME_TipoPaciente_ID(), null);
		}
		return mTipoPac;
	}
	
	/**
	 * Retornamos si el paciente tiene una cuenta paciente abierta
	 * @return
	 */
	public boolean hasOpenAcct() {
		log.config("hasOpenAcct: "+get_ID());
		boolean retValue = false;

		// Revisar que solo exista una cuenta paciente activa para el paciente
		if (!getTipoArea().equals(TIPOAREA_Ambulatory)) {
			final MEXMECtaPac[] ctaPac = get(getCtx(), ENCOUNTERSTATUS_Admission, getTipoArea(), get_TrxName());
			if (ctaPac.length > 0) {
				log.saveError("Error", Utilerias.getMsg(getCtx(), "error.ctapac.abierta"));
				retValue = true;
			}
		}
		return retValue;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasPendingMovement() {
		log.config("hasPendingMovement: "+get_ID());
		//Defecto #3083
		boolean retValue = hasPendingMedications() // medications 
//				|| MEXMEActPacienteIndH.getCountServices(getCtx(), getEXME_CtaPac_ID(), 0,
//						null, " AND EXME_ActPacienteIndH.DocStatus IN (?,?,?) ", 
//						MEXMEActPacienteIndH.DOCSTATUS_Drafted,
//						MEXMEActPacienteIndH.DOCSTATUS_Approved,
//						MEXMEActPacienteIndH.DOCSTATUS_InProgress
//				) > 0// services
				|| !MEXMEActPacienteIndH.getPacienteSolPending(getCtx(), getEXME_CtaPac_ID(), true, null).isEmpty()
				|| !getMovementNoAplicados().isEmpty() ;// movements
		
		log.config("hasPendingMovement: "+ retValue);
		return retValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPaqueteFacturado() {
		return paqueteFacturado;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPendingObx() {
		return pendingObx;
	}
	
	/**
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */
	public String prepareIt() {
		log.info("prepareIt:" + toString());
		mProcessMsg = ModelValidationEngine.get().fireDocValidate(this,
				ModelValidator.TIMING_BEFORE_PREPARE);
		if (mProcessMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID())) {
			mProcessMsg = "@PeriodClosed@";
			//return DocAction.STATUS_Invalid;
		}

		// Lines
		MCtaPacDet[] lines = getLines(true, "M_Product_ID");
		if (lines.length == 0) {
			mProcessMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		mJustPrepared = true;
		// if (!DOCACTION_Complete.equals(getDocAction())) don't set for just
		// prepare
		// setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Actualiza la cama asignada al paciente
	 * 
	 * @param isNew
	 * @param errores
	 * @param forma
	 * @throws Exception
	 */
	public boolean saveCama(final boolean isNew) {
		log.config("saveCama: "+get_ID());
		boolean save = true;
		if (isNew || is_ValueChanged(MEXMECtaPac.COLUMNNAME_EXME_Cama_ID)) {
			int camaOld = isNew ? 0 : get_ValueOldAsInt(MEXMECtaPac.COLUMNNAME_EXME_Cama_ID);
			try {
				// releases old bed
				if (camaOld > 0) {
					new MEXMECama(getCtx(), camaOld, get_TrxName()).releaseBed();
				}
				// assing new one
				if (getEXME_Cama_ID() > 0) {
					final MEXMECama cama = new MEXMECama(getCtx(), getEXME_Cama_ID(), get_TrxName());
					cama.assignBed(getEXME_CtaPac_ID(), null, null, false);
					setAD_OrgTrx_ID(cama.getHabitacion().getEstServ().getAD_OrgTrx_ID());
					setEXME_EstServ_ID(cama.getHabitacion().getEXME_EstServ_ID());
				}
				// guardamos la bitacora de cama
				MEXMEHistCama.guardar(getCtx(), getEXME_Cama_ID(), camaOld, this, get_TrxName());
//				save = save(get_TrxName()); //Lama: ya no se hace save sobre EXME_CtaPac
			} catch (MedsysException e) {
				save = false;
				s_log.log(Level.WARNING, e.getMessage());
			} catch (Exception e) {
				save = false;
				s_log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return save;
	}


	/**
	 * Guardamos una nota en MRequest usando Description (Encounter - Note)
	 * @return
	 */
	private boolean saveRegistrarNote() {
		MRequest req = new MRequest(getCtx(), 0, get_TrxName());
		req.setClientOrg(Env.getAD_Client_ID(getCtx()), Env.getAD_Org_ID(getCtx()));
		req.setSummary(getDescription());
		req.setNoteType(MRequest.NOTETYPE_RegistrationNote);
		req.setStartDate(Env.getCurrentDate());
		req.setInitials(MUser.getFromID(COLUMNNAME_Description, Env.getAD_User_ID(getCtx())));
		req.setDueType(MRequest.DUETYPE_Overdue);
		req.setAD_Table_ID(MEXMECtaPac.Table_ID);
		req.setRecord_ID(getEXME_CtaPac_ID());
		return req.save();		
	}
	
	/**
	 * 
	 * @param consentimientoPac
	 */
	public void setConsentimientoPac(MEXMEConsentimientoPac consentimientoPac) {
		this.consentimientoPac = consentimientoPac;
	}

	/**
	 * 
	 * @param warehouse_ID
	 */
	public void setM_Warehouse_ID(int warehouse_ID) {
		m_Warehouse_ID = warehouse_ID;
	}
	
	/**
	 * Establecemos el medico de la cuenta paciente
	 *
	 * @param paciente el medico MMedico a establecer a la cuenta
	 */
	public void setMedico(MEXMEMedico medico) {
		this.medico = medico;
	}

	/**
	 * Establecemos el paciente de la cuenta paciente
	 *
	 * @param paciente el paciente MEXMEPaciente a establecer a la cuenta
	 */
	public void setPaciente(MEXMEPaciente paciente) {
		this.paciente = paciente;
	}
	
	/**
	 * 
	 * @param paqueteFacturado
	 */
	public void setPaqueteFacturado(boolean paqueteFacturado) {
		this.paqueteFacturado = paqueteFacturado;
	}
	
	/**
	 * 
	 * @param pendingObx
	 */
	public void setPendingObx(boolean pendingObx) {
		this.pendingObx = pendingObx;
	}
	
	/**
	 * 
	 * @param pendingObxCount
	 */
	public void setPendingObxCount(int pendingObxCount) {
		this.pendingObxCount = pendingObxCount;
	}
	
	/** Set Resuscitative Status.
	@param ResStatus Resuscitative Status	  */
	public void setResStatus (String ResStatus)
	{
		super.setResStatus(ResStatus);// Lama
		// Siempre se crea un nuevo registro nunca se actualiza
		if (getConsentimientoPac() == null || !consentimientoPac.getResStatus().equalsIgnoreCase(ResStatus)) {
			consentimientoPac = new MEXMEConsentimientoPac(getCtx(), 0, get_TrxName());
			consentimientoPac.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
			consentimientoPac.setResStatus(ResStatus);
		}
	}

	/**
	 * 
	 * @param resStatusOld
	 */
	public void setResStatusOld(String resStatusOld) {
		this.resStatusOld = resStatusOld;
	}
	
	/**
	 * 
	 * @param tienePaquete
	 */
	public void setTienePaquete(boolean tienePaquete) {
		this.tinePaquete = tienePaquete;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean tienePaquete() {
		return tinePaquete;
	}
	
	/**
	 * Desbloquea una cuenta paciente a la hora de dar boton regresar o cerrar pestaña de los 
	 * procesos de Facturacion para Mexico.
	 * 
	 * @return un <code>String</code> con el mensaje de que no pudo salvar la cuenta o nulo si fue exitoso.
	 */
	public String unlockAccount() {
		log.config("unlockAccount: "+get_ID());
		String slMessage = null;
		if (getEXME_CtaPac_ID() > 0) {
			setIsBloqueada(false);
			
			if (!save()) {
				slMessage = Utilerias.getAppMsg(getCtx(),
						"error.indicacionesMedicas.actualizarEstatus");
			}
	
			/*
			 * Obtenemos la lista de cuentas paciente del Contexto para eliminar la
			 * cuenta que se acaba de liberar al darle boton regresar.
			 */
			@SuppressWarnings("unchecked")
			List<Integer> lstlCtasPac = (List<Integer>) getCtx().get("ListaIDsCtaPac");
	
			// Eliminamos de la lista la cuenta.
			if (lstlCtasPac != null) {
				lstlCtasPac.remove((Object) getEXME_CtaPac_ID());
				getCtx().put("ListaIDsCtaPac", lstlCtasPac);
			}
		}
		
		return slMessage;
	}
	
	/**
	 * Lanza excepcion si la cuenta paciente tiene alguno de estos 
	 * movimientos relacionados. Noelia
	 * @param ctx
	 * @param trxName
	 * @throws Exception
	 */
	public void validarMovimientos(Properties ctx, String trxName) {
		log.config("validarMovimientos: "+get_ID());
		//////////////////////Busca si la cuenta paciente tiene cargos       //////////////////////////
		MCtaPacDet detalle[] = MCtaPacDet.getDetalle(ctx, getEXME_CtaPac_ID(), null ,trxName);	//TODO tarda
		if(detalle.length>0){
			throw new MedsysException("cancelaIngreso.tieneCargos");//error tiene cargos .-Lama              
		}

		//////////////////////                   Busca pedidos                     ////////////////////
		if (MMovement.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {
			throw new MedsysException("cancelaIngreso.tienePedidos"); 
		}

		//////////////////////       Busca programaciones de medicamentos          ////////////////////
		if (MPlanMed.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneMedicamentos");     
		}

		//////////////////////        Busca programaciones de quirofanos            ////////////////////
		if (MProgQuiro.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneQuirofanos");  
		}

		//////////////////////             Busca signos vitales                     ////////////////////
		if (MEXMESignoVitalDet.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneSignos"); 
		}

		//////////       Busca si tiene actividad detallada como nota o cuestionario        ////////////
		if (MEXMEActPacienteDet.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneActividad");   
		}

		//////////////////////         Busca si tiene mas de 1 extension           ////////////////////
		if (MEXMECtaPacExt.get(ctx, getEXME_CtaPac_ID(),"", trxName).length > 1) {
			throw new MedsysException("cancelaIngreso.tieneExtensiones"); 
		}

		//////////          Busca si tiene registro en In Out (Mov. de Inventarios)         ////////////
		if (MEXMEInOut.getForCtaPacId(ctx, getEXME_CtaPac_ID(), trxName)) {
			throw new MedsysException("cancelaIngreso.tieneInOut");  
		}

		//////////////////////     Busca si tiene almenos 1 egreso          //////////////////////Noelia
//		if(MEgresos.getIDByCtaPac(ctx, getEXME_CtaPac_ID(), trxName)!=0){
//			throw new MedsysException("cancelaIngreso.tieneEgresos");  
//		}

		//////////////////////     Busca si tiene Notificacion de Ingreso    //////////////////////Noelia
//		if(MNotificaIngreso.getFromCtaPac(ctx, getEXME_CtaPac_ID(), trxName) != null){
//			throw new MedsysException("cancelaIngreso.tieneNotIngreso");  
//		}

		//////   Busca si la cuenta paciente tiene registros de Diario de Enfermeria      ///////// Noelia
		MEXMEDiarioEnf diariosCtaPac[] = MEXMEDiarioEnf.get(ctx, getEXME_CtaPac_ID(), null, trxName);	//TODO tarda
		if(diariosCtaPac.length>0){
			throw new MedsysException("cancelaIngreso.tieneRegDiarioEnf");            
		}

		////////////////////       Busca si la cuenta paciente tiene contrarecibos     /////////////////Noelia
		List<MEXMEContraRecibo> contraReciboList = MEXMEContraRecibo.getAll(getCtx(), " AND EXME_ContraRecibo.EXME_CtaPac_ID= " + getEXME_CtaPac_ID(), null, trxName);
		if(!contraReciboList.isEmpty()){//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneContraRecibos");         
		}

		////////////////////    Busca si la cuenta paciente tiene Dietas relacionada        /////////////////Noelia
		MEXMEPrescDieta pacDiet = MEXMEPrescDieta.getActual(getCtx(), getEXME_CtaPac_ID(), trxName);
		if(pacDiet!=null){//TODO tarda
			throw new MedsysException("cancelaIngreso.tieneCtaPacDieta");        
		} 
	}
	
			
	/**
	 * Obtiene el listado de excepciones en los claims de Billing
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static List<BillingExceptionRptView> getBillingExceptionList(String filter, String type) throws Exception{
		final List<BillingExceptionRptView> array = new ArrayList<BillingExceptionRptView>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		boolean isUnion = Boolean.FALSE;	
		
		
		StringBuilder sql = new StringBuilder("  SELECT * ")
		.append("FROM   (  ")
		.append(" WITH CHARGES_CTAPAC AS (SELECT CPD.EXME_CtaPac_ID,fnc_IsProfessional(PO.EXME_ProductoOrg_ID, CPD.AD_Org_ID) ISPROFESSIONAL, ")
		.append(" COALESCE(SUM(CPD.LineNetAmt), 0)  TotalCharges ")
		.append(" FROM EXME_CtaPacDet CPD ")
		.append(" INNER JOIN EXME_CtaPacExt CPE ")
		.append(" ON CPE.EXME_CtaPacExt_ID = CPD.EXME_CtaPacExt_ID and CPE.ExtensionNo = 0 ")
		//.append(" ON PO.EXME_ProductoOrg_ID = fnc_getProductOrg(CPD.M_Product_ID , CPD.AD_Org_ID) ")
		.append(" INNER JOIN EXME_ProductoOrg PO ")
		.append(" ON PO.EXME_ProductoOrg_ID = fnc_getProductOrg(CPD.M_Product_ID , CPD.AD_Org_ID) ")
		.append(" WHERE CPD.AD_Client_ID = ? AND CPD.AD_Org_ID = ? ");
		if (MOrgInfo.SUPPORTBILLING_Both.equals(type)){
			isUnion = Boolean.TRUE;
		}
		if (!MOrgInfo.SUPPORTBILLING_Professional.equals(type)){
			sql.append(" AND (CPE.InstitutionalStatus IN (?,?,?,?,?) ");
		}	
		if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			if (isUnion){
				sql.append(" OR CPE.ProfessionalStatus IN (?,?,?,?,?) ");
			}else{
				sql.append(" AND (CPE.ProfessionalStatus IN (?,?,?,?,?) ");
			}
			
		}
		
		sql.append(" ) ")
		.append(" Group By CPD.EXME_CtaPac_ID, fnc_IsProfessional(PO.EXME_ProductoOrg_ID, CPD.AD_Org_ID) )  ")
		.append("SELECT * FROM ( ");
		
		if (!MOrgInfo.SUPPORTBILLING_Professional.equals(type)){
			sql.append(getExceptionsByType(MOrgInfo.SUPPORTBILLING_Institucional));
		}
		
		if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			if (isUnion){
				sql.append(" UNION ALL ");
			}
			sql.append(getExceptionsByType(MOrgInfo.SUPPORTBILLING_Professional));
		}

		sql.append(") E ");
		
		if(StringUtils.isNotEmpty(filter)){
			sql.append("where ")
			.append(filter);
		}
		
		sql.append(" ORDER BY E.billingException, E.primaryFC, E.primaryPC, E.daysAged ")
		.append(" ) T ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 1;
			pstmt.setInt(i++,Env.getAD_Client_ID(Env.getCtx()));
			pstmt.setInt(i++,Env.getAD_Org_ID(Env.getCtx()));
			
			if (!MOrgInfo.SUPPORTBILLING_Professional.equals(type)){
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorRSPFile);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMessage997);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero);
			}
			if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorRSPFile);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMessage997);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorOrdersIncomplete);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorPricesInZero);
			}

			if (!MOrgInfo.SUPPORTBILLING_Professional.equals(type)){
				
				
				pstmt.setString(i++,MEXMEPacienteAseg.SUPPORTBILLING_Institucional);
				pstmt.setString(i++,MEXMECtaPacMed.TYPE_Attending);
				pstmt.setInt(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_AD_Reference_ID);
				pstmt.setString(i++,Constantes.REG_NOT_ACTIVE);
				pstmt.setInt(i++,Env.getAD_Client_ID(Env.getCtx()));
				pstmt.setInt(i++,Env.getAD_Org_ID(Env.getCtx()));
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMandatoryFields);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorRSPFile);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorMessage997);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorOrdersIncomplete);
				pstmt.setString(i++,MEXMECtaPacExt.INSTITUTIONALSTATUS_ErrorPricesInZero);
			}
			if (!MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
				
				pstmt.setString(i++,MEXMEPacienteAseg.SUPPORTBILLING_Professional);
				pstmt.setString(i++,MEXMECtaPacMed.TYPE_Attending);
				pstmt.setInt(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_AD_Reference_ID);
				pstmt.setString(i++,Constantes.REG_ACTIVE);
				pstmt.setInt(i++,Env.getAD_Client_ID(Env.getCtx()));
				pstmt.setInt(i++,Env.getAD_Org_ID(Env.getCtx()));
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMandatoryFields);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorRSPFile);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorMessage997);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorOrdersIncomplete);
				pstmt.setString(i++,MEXMECtaPacExt.PROFESSIONALSTATUS_ErrorPricesInZero);
			}
			
			
			result = pstmt.executeQuery();
			while (result.next()){
				
				BillingExceptionRptView view = new BillingExceptionRptView();
				view.setExmeCtaPacID(result.getString("EXME_CtaPac_ID"));
				view.setAdClientID(result.getString("AD_Client_ID"));
				view.setAdOrgID(result.getString("AD_Org_ID"));
				view.setEncounter(result.getString("encounter"));
				view.setMrn(result.getString("mrn"));
				view.setPatientType(result.getString("patientType"));
				view.setBillingException(result.getString("billingException"));
				view.setBillingType(result.getString("billingType"));
				final int days = result.getInt("daysAged");
				if(days >= 0){
					view.setDaysAged(String.valueOf(days));
				}else{
					view.setDaysAged("N/A");
				}
				view.setPrimaryFC(result.getString("primaryFC"));
				view.setPrimaryPC(result.getString("primaryPC"));
				view.setTotalCharges(result.getDouble("totalCharges"));
				view.setAttending(result.getString("attending"));
				view.setTotalChargesStr(NumberFormat.getCurrencyInstance(Locale.CANADA).format(view.getTotalCharges()));
				
				array.add(view);
				
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, e.getMessage());
		}finally {
			DB.close(result, pstmt);
		}

		return array;
	}
	
	/**
	 * Obtenemos las ID de cuenta paciente 
	 * para el paciente y organizacion especificados 
	 * @param ctx El contexto de la aplicacion
	 * @param EXME_Paciente_ID El identificador del paciente
	 * @param trxName El nombre de la transaccion
	 * @param AD_Org_ID Organizacion por la que se desea filtrar
	 * @return ctaPac
	 */
	public static Integer[] getOfPatientByOrg(Properties ctx, int EXME_Paciente_ID, String trxName, int AD_Org_ID) {
		List<Integer> lista = new ArrayList<Integer>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		POInfo poInfo = POInfo.getPOInfo(ctx, MEXMECtaPac.Table_ID);

		try {

			sql.append(" SELECT EXME_CtaPac_ID ")
			   .append(" FROM EXME_CtaPac  ")
			   .append(" WHERE IsActive = 'Y' ");
			if (AD_Org_ID != -1) {
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", poInfo, null, AD_Org_ID));
			}
			sql.append(" AND EXME_Paciente_ID = ? ");
			sql.append(" ORDER BY Created Desc");

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getInt("EXME_CtaPac_ID"));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		Integer[] retValue = new Integer[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}
	
	/**
	 * Obtenemos las cuenta paciente 
	 * para la organizacion y el tipo especificados
	 * y que deben de moverse a excepciones 
	 * @param ctx El contexto de la aplicacion
	 * @param AD_Org_ID Organizacion por la que se desea filtrar
	 * @param confType Tipo de Claim
	 * @param dateFrom Desde que fecha de Alta
	 * @param dateTo Hasta que fecha de Alta
	 * @param trxName El nombre de la transaccion
	 * @return ctaPac
	 */
	public static List<MEXMECtaPac> getCtaPacToExceptions(Properties ctx, String confType, 
			Timestamp dateFrom, Timestamp dateTo,  String trxName) {
		String statusColName ="";
		String stepColName;
		int referenceID;
		List<String> stepLst = new ArrayList<String>();
		List<String> statusLst = new ArrayList<String>();
		
		if (MEXMEClaimPaymentH.BILLINGTYPE_Technical.equalsIgnoreCase(confType)){
			statusColName = MEXMECtaPacExt.COLUMNNAME_InstitutionalStatus;
			stepColName = MEXMECtaPacExt.COLUMNNAME_InstitutionalStep;
			referenceID = MEXMECtaPacExt.INSTITUTIONALSTATUS_AD_Reference_ID;
			stepLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_Default);
			stepLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_SelftPay);
			stepLst.add(MEXMECtaPacExt.INSTITUTIONALSTEP_FirstInsurance);
			statusLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled);
			statusLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable);
			statusLst.add(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete);
		}else{
			statusColName = MEXMECtaPacExt.COLUMNNAME_ProfessionalStatus;
			stepColName = MEXMECtaPacExt.COLUMNNAME_ProfessionalStep;
			referenceID = MEXMECtaPacExt.PROFESSIONALSTATUS_AD_Reference_ID;
			stepLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_Default);
			stepLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_SelftPay);
			stepLst.add(MEXMECtaPacExt.PROFESSIONALSTEP_FirstInsurance);
			statusLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled);
			statusLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable);
			statusLst.add(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CP.* ")
		   .append("FROM EXME_CTAPAC CP ")
		   .append("INNER JOIN AD_REF_LIST ARL ")
		   .append("ON ARL.AD_REFERENCE_ID = ?  ")// Parametro Lista de Referencia
		   .append("AND ARL.VALUE = CP." + statusColName + " ")
		   .append("LEFT JOIN (EXME_BATCHCLAIMD D  ")
		   .append("INNER JOIN EXME_BATCHCLAIMH H ")
		   .append("ON H.EXME_BATCHCLAIMH_ID = D.EXME_BATCHCLAIMH_ID ")
		   .append("AND H.CONFTYPE = ? ")// Parametro Tipo de Claim
		   .append("AND H.ISACTIVE = 'Y') ")
		   .append("ON D.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
		   .append("AND D.ISACTIVE = 'Y' ")
		   .append("WHERE CP.AD_CLIENT_ID = ? ")// Parametro Cliente
		   .append("AND CP.AD_ORG_ID = ? ")// Parametro Organizacion
		   .append("AND CP."+ stepColName + " IN(?,?,?) ")// Parametro STEP
		   .append("AND CP.FECHAALTA IS NOT NULL ")
		   // Ticket 0100869 gvaldez, se filtra en base a Discharge Date
		   .append("AND CP.FECHAALTA ")
		   .append("BETWEEN TO_DATE(?, 'mm/dd/yyyy HH24:MI') ")// Parametro FechaINI
		   .append("AND TO_DATE(?, 'mm/dd/yyyy HH24:MI') ")// Parametro FechaFIN
		   .append("AND H.DOCUMENTNO IS NULL ")
		   .append("AND CP." + statusColName + "  IN (?,?,?) ");// Parametro STATUS
		
		List<MEXMECtaPac> lstDiv = new ArrayList<MEXMECtaPac>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int i = 1;
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(i++, referenceID );
			pstmt.setString(i++, confType);
			pstmt.setInt(i++, Env.getAD_Client_ID(ctx));
			pstmt.setInt(i++, Env.getAD_Org_ID(ctx));
			
			for (String step : stepLst){
				pstmt.setString(i++, step);
			}
			
			pstmt.setString(i++, Constantes.getSDFDateTime(ctx).format(dateFrom));
			pstmt.setString(i++, Constantes.getSDFDateTime(ctx).format(dateTo));
			
			for (String status : statusLst){
				pstmt.setString(i++, status);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstDiv.add(new MEXMECtaPac(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lstDiv;
	}
		
	/**
	 * Obtenemos la extension cero de un determinada cuenta paciente.
	 * 
	 * @return
	 */
	public MEXMECtaPacExt getExtCero() {
		if(extCero==null){
			extCero =  new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
		}
		return extCero;
	}
	
	/**
	 * Obtiene las cuentas activas y que no tengan place of service
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @return Regresa el listado de los objetos MEXMECtaPac
	 */
	public static List<MEXMECtaPac> getCuentaPacienteNotPOS(Properties ctx, int EXME_TipoPaciente, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<MEXMECtaPac> cuentaPacienteLista = new ArrayList<MEXMECtaPac>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT * ");
			sql.append(" FROM EXME_CTAPAC ");
			sql.append(" WHERE EXME_CTAPAC.EXME_TIPOPACIENTE_ID = ? AND EXME_CTAPAC.IsActive = 'Y' AND EXME_CTAPAC.EXME_POS_ID IS NULL ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_TipoPaciente);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cuentaPacienteLista.add(new MEXMECtaPac(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMECtaPac.getCuentaPacienteNotPOS", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return cuentaPacienteLista;
	}
	
	/**
	 * Obtener listado de cuentas que no han sido procesadas en el billing queue
	 * 
	 * @param ctx
	 *            Contexto
	 * @param date
	 *            Fecha Inicial (puede ser nula)
	 * @param date1
	 *            Fecha Final (puede ser nula)
	 * @param pacId
	 *            Paciente (puede ser nulo)
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de cuentas que coinciden con los parametros enviados
	 */
	public static List<MEXMECtaPac> getNotValidOnQueue(Properties ctx, Timestamp date, Timestamp date1, int pacId, String trxName) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_ctapac cta ");
		sql.append("WHERE ");
		sql.append("  cta.validonqueue = 'N' ");
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "cta"));

		if (date != null && date1 != null) {
			sql.append("  AND cta.dateordered BETWEEN ");
			sql.append("  ? AND ");
			sql.append("  ? ");

			params.add(date);
			params.add(date1);
		}
		
		if (pacId > 0) {
			sql.append("  AND cta.exme_paciente_id = ? ");
			params.add(pacId);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MEXMECtaPac> lst = new ArrayList<MEXMECtaPac>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMECtaPac(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}
	
	
	/**
	 * Obtenemos el detalle de la cuenta paciente.
	 * @return
	 */
	public List<MCtaPacDet> getLstDetalleInv(final String confType, final int EXME_CtaPacExt_ID ) {
		
		final List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT EXME_CtaPacDet.* ")
		.append(" FROM   EXME_CtaPacDet ")
		.append(" LEFT   JOIN EXME_ProductoOrg PO ON PO.EXME_ProductoOrg_ID = FNC_GETPRODUCTORG( EXME_CtaPacDet.M_Product_ID, EXME_CtaPacDet.AD_Org_ID )  ")
		.append(" WHERE    EXME_CtaPacDet.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MCtaPacDet.Table_Name))
		.append(" AND      EXME_CtaPacDet.EXME_CtaPac_ID = ? ")
		.append(" AND FNC_IsProfessional(PO.EXME_ProductoOrg_ID, EXME_CtaPacDet.AD_Org_ID)= ")
		.append(confType.equals(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim)  ? " 'Y' " : " 'N' ")
		.append(EXME_CtaPacExt_ID>0?" AND EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ":StringUtils.EMPTY)
		.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPac_ID());pstmt.setInt(2, EXME_CtaPacExt_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacDet ctaPacDet = new MCtaPacDet(getCtx(), rs, get_TrxName());
				ctaPacDet.setCtaPac(this);
				list.add(ctaPacDet);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * Ejecuta la consulta para saber si la cuenta paciente es elegible para tener excepciones.
	 * @param ctx
	 * @param sql
	 * @param ctaPacID
	 * @param trxName
	 * @return
	 */
	public static boolean isEncounterException(Properties ctx, StringBuilder sql, int ctaPacID, String trxName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean isException = false;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1,Env.getAD_Client_ID(ctx));
			pstmt.setInt(2,Env.getAD_Org_ID(ctx));
			pstmt.setInt(3,ctaPacID);
						
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isException = true;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
			
		
		return isException;
	}
	
	
	/**
	 * Ejecuta la consulta de la excepcion para devolver en un valor numerico la cantidad o suma de los registros encontrados,
	 * este valor posteriormente sera comparado dependiendo del comparador y el valor de la regla para saber si se trata de una excepcion
	 * @param ctx
	 * @param sql
	 * @param ctaPacID
	 * @param tableID
	 * @param trxName
	 * @return
	 */
	public static int operateCodeException(Properties ctx, StringBuilder sql, int ctaPacID, int tableID, String trxName) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1,ctaPacID);
			if(tableID != MCtaPacDet.Table_ID && tableID != MDiagnostico.Table_ID){
				pstmt.setInt(2,tableID);
			}
						
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
			
		
		return num;
	}
	
	/**
	 * 
	 * @param confType
	 * @return
	 */
	public int getPatientBPartner(final String confType) {
		
		String supportBill = X_EXME_PacienteAseg.SUPPORTBILLING_Both;
		if(X_EXME_ClaimPaymentH.BILLINGTYPE_Professional.equals(confType) || X_HL7_MessageConf.CONFTYPE_OutpatientProfessionalClaim.equals(confType)) {
			supportBill = X_EXME_PacienteAseg.SUPPORTBILLING_Professional;
		} else  {
			supportBill = X_EXME_PacienteAseg.SUPPORTBILLING_Institucional;
		}
		
		int retValue = -1;
		// if insurance
		if (getPaciente() != null) {
			if (getPaciente().isEsAsegurado()) {
				// first priority
				final MEXMEPacienteAseg aseg = MEXMEPacienteAseg.getFirstPriority(getCtx(), getEXME_Paciente_ID(),
						getEXME_CtaPac_ID(), supportBill, get_TrxName());
				retValue = aseg == null ? -1 : aseg.getPriority() == 1 ? aseg.getC_BPartner_ID() : -1;
			} 

			if(retValue<=0){
				retValue = getPaciente().getPatientBPartner().get_ID();// system
			}
		}
		return retValue;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatusColor() {
		if (ENCOUNTERSTATUS_Discharge.equals(getEncounterStatus())) {
			return "#ffff85";
		}
		if (ENCOUNTERSTATUS_Predischarge.equals(getEncounterStatus())) {
			return "#9ACD32";
		}
		return "#FFFFFF";
	}
	
	/**
	 * 
	 * @param ctx
	 * @param confType
	 * @param startDate
	 * @param endDate
	 * @param trxName
	 * @return
	 */
	public static ArrayList<MEXMECtaPac> getClaimsReport(Properties ctx, String confType, Date startDate, Date endDate, String trxName) {
		ArrayList<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT ID AS EXME_CTAPAC_ID ")
		   .append("FROM TABLE(fnc_getListaClaimsR(?, ?, ?, ?, ?))");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Org_ID(ctx));
			pstmt.setString(3, confType);
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(5, new java.sql.Date(endDate.getTime()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, rs.getInt("EXME_CtaPac_ID"), trxName);
				list.add(ctaPac);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return list;
	}

	
	/**
	 * Get Lines of Order
	 *
	 * @param whereClause where clause or null (starting with AND)
	 * @param orderClause order clause
	 * @return lines
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MCtaPacDet[] getLines(String whereClause, List params) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		if (params == null) {
			params = new ArrayList();
			params.add(Env.getAD_Org_ID(getCtx()));
		}
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT  cpd.* FROM EXME_CtaPacDet cpd  ");
		sql.append(" LEFT  JOIN M_Warehouse wr ON wr.M_Warehouse_ID = cpd.M_Warehouse_ID ");
//		sql.append(" LEFT  JOIN M_Cost     cst ON  cpd.m_product_id = cst.m_product_id   AND cst.AD_Client_Id = cpd.AD_Client_Id ");
//		sql.append("   AND cst.c_acctschema_id IN (select c_acctschema_id from c_acctschema where AD_Client_Id = cpd.AD_Client_Id  ) ");
		sql.append(" WHERE cpd.IsActive = 'Y' ");
		sql .append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",MCtaPacDet.Table_Name, "cpd"));
		sql .append(" AND  cpd.AD_Org_ID = ?   ");
		
		
		if (whereClause != null) {
			sql.append(whereClause);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacDet ol = new MCtaPacDet(getCtx(), rs, get_TrxName());
				// el costo se obtiene del cargo
//				if(Env.ZERO.compareTo(ol.getCosto())==0 && rs.getBigDecimal("CURRENTCOSTPRICE")!=null){
//					ol.setCosto(rs.getBigDecimal("CURRENTCOSTPRICE"));
//				}
				ol.setHeaderInfo(this);
				list.add(ol);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		MCtaPacDet[] lines = new MCtaPacDet[list.size()];
		list.toArray(lines);
		return lines;
	} // getLines
	/**
	 * Devuelve un objeto LabelsReportView con la representacion de las propiedades
	 * del paciente para su impresion posterior en el reporte Labels.jrxml.
	 * 
	 * @param ctx el contexto de la aplicacion.
	 * @param ctapac el id de la cuenta paciente.
	 * @return un objeto LabelsReportView con la informacion almacenada.
	 * @author jcantu.
	 */
	public static LabelsReportView getPatientInfoForLabels(Properties ctx, int ctaPacID) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		LabelsReportView lblRepView = null;
		
		sql.append("select p.name, p.nombre2, p.apellido1,  p.fechanac dob, p.sexo sex, ");
		
		if (DB.isPostgreSQL()) {
			sql.append("to_char(age (ctapac.dateordered, p.fechanac),'YY') AS age, ");
		} else {
			sql.append("round(((ctapac.dateordered - p.fechanac)) / 365.25) age, ");
		}
		//sql.append(slAgeClause);
		sql.append("expe.documentno mrn, ctapac.documentno, ");
		sql.append("COALESCE(med2.apellido1,med.apellido1) as attending, ");
		sql.append("ctapac.DateOrdered admiting, P.NOMBRE_PAC ");
		sql.append("FROM EXME_ctapac ctapac ");
		sql.append("INNER JOIN EXME_paciente p ON (ctapac.EXME_paciente_id=p.EXME_paciente_ID ");
		sql.append(" AND ctapac.EXME_ctapac_ID=?) ");//1
		sql.append("INNER join EXME_hist_exp expe ON (");
		sql.append("    expe.EXME_paciente_ID=p.EXME_paciente_ID ");
		sql.append("AND expe.isactive='Y' ");
		sql.append("AND expe.ad_org_ID=ctapac.ad_org_ID) ");
		sql.append("INNER JOIN EXME_admitsource aSource ON (asource.EXME_admitsource_ID=ctapac.EXME_admitsource_ID) ");
		// medico .- Lama Tikcet #0102365
		sql.append("INNER JOIN EXME_Medico med ON (ctapac.EXME_Medico_ID=med.EXME_Medico_ID) ");
		sql.append("LEFT JOIN EXME_CtaPacMed cpm ON (");
		sql.append("    ctapac.EXME_ctapac_ID=cpm.EXME_ctapac_ID ");
		sql.append("AND cpm.isactive='Y' ");
		sql.append("AND cpm.Type=?) ");//2
		sql.append("LEFT JOIN EXME_Medico med2 ON (med2.EXME_Medico_ID=cpm.EXME_Medico_ID) ");//1
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctaPacID);
			pstmt.setString(2, X_EXME_CtaPacMed.TYPE_Admitting);//Lama
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				lblRepView = new LabelsReportView();
				
				//correcion de regresion intruducida al dejar de usar el query dentro del jrxml			
				lblRepView.setName(SecureEngine.decrypt(rs.getString(11)));
				lblRepView.setDob(rs.getTimestamp(4));
				lblRepView.setSex(rs.getString(5));
				lblRepView.setAge(rs.getBigDecimal(6));
				lblRepView.setMrn(rs.getString(7));
				lblRepView.setDocumentNo(rs.getString(8));
				lblRepView.setAdmitting(rs.getString(9));
				lblRepView.setAdmit(Constantes.getSDFFechaCortaDMA(ctx).format(rs.getObject(10)));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "sql = " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lblRepView;
	}

	/**
	 * Reactiva la cuenta paciente, borra la informacion de Alta/Pre Alta y cambia el estatus a Admssion.
	 * 
	 * @param exmeCamaId
	 *            Si el ID es mayor a cero, asigna la cama a la cuenta.
	 * @param trxName
	 * @return <b>true</b>: si se guardo correctamente la cuenta y la bitacora de la cuenta paciente <br>
	 *         <b>false</b>: La cuenta no tiene estatus Alta / PreAlta ,cuenta con otra cuenta activa, o es nueva
	 * @author Lorena Lama
	 */
	public boolean reactivate(final int exmeCamaId, String trxName) {
		if (!is_new()
				//&& (ENCOUNTERSTATUS_Discharge.equals(getEncounterStatus()) || ENCOUNTERSTATUS_Predischarge
			//			.equals(getEncounterStatus())) 
			//&& !hasOpenAcct()
			) {
			//setEncounterStatus(MEXMECtaPac.ENCOUNTERSTATUS_Admission);
			//setActualizadoPrealta(0);
			//setActualizadoAlta(0);
			//setFechaPrealta(null);
			//setFechaAlta(null);
			setFechaCierre(null);
			//setEXME_DischargeStatus_ID(0);
			//setInstruccionAlta(null);
			//setInformeAlta(null);
			//if (exmeCamaId > 0) {
			//	setEXME_Cama_ID(exmeCamaId);
			//	saveCama(false, true);
			//}
//			final MEXMECtaPacBitacora ctapacbit = new MEXMECtaPacBitacora(getCtx(), 0, null);
//			ctapacbit.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
//			ctapacbit.setOpcion(MEXMECtaPacBitacora.OPCION_Reactivation);
			return MEXMECtaPacBitacora.reactivation(getCtx(), getEXME_CtaPac_ID(), get_TrxName()) && save(trxName);
		}
		return false;
	}
	
	
	/**
	 * Reactiva la cuenta paciente, borra la informacion de Alta/Pre Alta y cambia el estatus a Admssion.
	 * 
	 * @param exmeCamaId <deprecated> sera removido
	 *            Si el ID es mayor a cero, asigna la cama a la cuenta.
	 * @param trxName
	 * @return <b>true</b>: si se guardo correctamente la cuenta y la bitacora de la cuenta paciente <br>
	 *         <b>false</b>: La cuenta no tiene estatus Alta / PreAlta ,cuenta con otra cuenta activa, o es nueva
	 * @author Lorena Lama
	 */
	public boolean revertDischarge(final int exmeCamaId, String trxName) {
		if (!is_new()
				&& (ENCOUNTERSTATUS_Discharge.equals(getEncounterStatus()) || ENCOUNTERSTATUS_Predischarge
						.equals(getEncounterStatus())) && !hasOpenAcct()) {
			setEncounterStatus(MEXMECtaPac.ENCOUNTERSTATUS_Admission);
			setActualizadoPrealta(0);
			setActualizadoAlta(0);
			setFechaPrealta(null);
			setFechaAlta(null);
			setEXME_DischargeStatus_ID(0);// TODO: registro deceso
			setInstruccionAlta(null);
			setInformeAlta(null);
//			if (exmeCamaId > 0) {
//				setEXME_Cama_ID(exmeCamaId);
//				saveCama(false, true);
//			}
			// Manda llamar la reactivacion
			if (MEXMEConfigEC.get(getCtx()).isCloseEncWD()) {
				return reactivate(exmeCamaId, trxName);
			} else {
				return save(trxName);
			}
		}
		return false;
	}

	/**
	 * Busca las referencias de los cargos con los ids
	 * del parametro
	 * @param sqlWhere Ids a buscar sus referencias
	 * @return listado de Obj MCtaPacDet que referencian
	 */
	public List<MCtaPacDet> refCCCmD(final StringBuilder sqlWhere) {
		final List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT cpd.*                    ")
		.append(" FROM   EXME_CtaPacDet cpd       ")
		.append(" WHERE  cpd.IsActive = 'Y'       ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",MCtaPacDet.Table_Name, "cpd"))
		.append(" AND    cpd.EXME_CtaPac_ID = ?   ")
		.append(" AND    cpd.Ref_CtaPacDet_ID IN (")
		.append(sqlWhere)
		.append("                                )");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCtaPacDet(getCtx(), rs, get_TrxName()));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	} // getLines

	/**
	 * 
	 * @param type
	 * @return
	 */
	private static String getExceptionsByType(String type){

		StringBuilder sql = new StringBuilder("  SELECT CP.EXME_CtaPac_ID, CP.AD_Client_ID, CP.AD_Org_ID, CP.DocumentNo as Encounter, ")
		.append("HE.DocumentNo as MRN, RL.Name billingException, RL.Value Status, ")
		.append("TP.Name patientType, TP.Value patientCode,  ");
		
		sql.append(" DATE_PART('day', CP.fechaalta - CP.dateordered) AS daysAged, ")
		.append("FC.Value valueFC, FC.NAME primaryFC, PC.Value valuePC, PC.NAME primaryPC ,  CC.totalCharges, ")
		.append("M.Nombre_Med Attending, ");
		if (MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			sql.append(DB.TO_STRING(MRefList.getListName(Env.getCtx(),
				MEXMEClaimPaymentH.BILLINGTYPE_AD_Reference_ID, MEXMEClaimPaymentH.BILLINGTYPE_Technical)));
		}else{
			sql.append(DB.TO_STRING(MRefList.getListName(Env.getCtx(), 
					MEXMEClaimPaymentH.BILLINGTYPE_AD_Reference_ID, MEXMEClaimPaymentH.BILLINGTYPE_Professional)));
		}
		sql.append(" AS BillingType, ");
		if (MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			sql.append(DB.TO_STRING(MEXMEClaimPaymentH.BILLINGTYPE_Technical));
		}else{
			sql.append(DB.TO_STRING(MEXMEClaimPaymentH.BILLINGTYPE_Professional));
		}
		sql.append(" AS BillingCode ")
		.append("FROM   EXME_CtaPac CP ")
		.append("INNER JOIN EXME_Paciente P ON P.EXME_Paciente_ID = CP.EXME_Paciente_ID ")
		.append("INNER JOIN EXME_CtaPacExt CPE ON CPE.EXME_CtaPacExt_ID = CP.EXME_CtaPacExt_ID ")
		.append("LEFT JOIN EXME_PACIENTEASEG PA ON PA.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID AND PA.SUPPORTBILLING = ? ")
		.append("AND PA.PRIORITY = '1' AND PA.ISACTIVE= 'Y' ")
		.append("INNER JOIN C_BPARTNER BP ON BP.C_BPartner_ID = COALESCE(PA.C_BPARTNER_ID, P.C_BPartner_ID) ")
		.append("LEFT  JOIN EXME_HIST_EXP HE ON HE.EXME_PACIENTE_ID = CP.EXME_PACIENTE_ID ")
		.append("AND HE.AD_CLIENT_ID = CP.AD_CLIENT_ID AND HE.AD_ORG_ID = CP.AD_ORG_ID AND HE.IsActive = 'Y' ")
		.append("INNER JOIN EXME_FinancialClass FC ON BP.EXME_FinancialClass_ID = FC.EXME_FinancialClass_ID ")
		.append("INNER JOIN EXME_PayerClass PC ON BP.EXME_PayerClass_ID = PC.EXME_PayerClass_ID ")
		.append("INNER JOIN EXME_TipoPaciente TP ON TP.EXME_TipoPaciente_ID = CP.EXME_TipoPaciente_ID ")
		.append("INNER JOIN EXME_CtaPacMed CPM ON CPM.IsActive = 'Y' ")
		.append("AND CPM.EXME_CtaPac_ID = CP.EXME_CtaPac_ID AND CPM.Type = ? ")
		.append("INNER JOIN EXME_Medico M ON M.EXME_Medico_ID = CPM.EXME_Medico_ID ")
		.append("LEFT JOIN AD_Ref_List RL ON RL.AD_Reference_ID = ? ");
		if (MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			sql.append("AND RL.Value = CPE.InstitutionalStatus ");
		}else{
			sql.append("AND RL.Value = CPE.ProfessionalStatus ");
		}
		
		sql.append("LEFT JOIN CHARGES_CTAPAC CC ON CC.EXME_CTAPAC_ID = CP.EXME_CTAPAC_ID ")
		   .append("AND CC.ISPROFESSIONAL=? ")
		   .append("WHERE CP.AD_Client_ID = ? and CP.AD_Org_ID = ? ");
		
		if (MOrgInfo.SUPPORTBILLING_Institucional.equals(type)){
			sql.append("AND CPE.InstitutionalStatus IN (?, ?, ?, ? , ? ) ");
		}else{
			sql.append("AND CPE.ProfessionalStatus IN (?, ?, ?, ? , ? ) ");
		}
		
		sql.append("AND CP.IsActive = 'Y' ");
		
		return sql.toString();
	}
	
	/**
	 * Cita
	 * @return
	 */
	public MEXMECitaMedica getCita() {
		if (cita == null) {
			try {
				cita = new Query(getCtx(), MEXMECitaMedica.Table_Name, " EXME_CtaPac_ID=? ", get_TrxName())//
						.setParameters(getEXME_CtaPac_ID())//
						.first();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "select * from exme_citamedica where exme_ctapac_id=? \n" + getEXME_CtaPac_ID(), e);
			}
		}
		return cita;
	}

	private List<MEXMECuidadosPac>	lstCuidadosPac;
	private List<MEXMEEsqInsulina>	lstEsqInsulinas;
	private List<MEXMEPrescDieta>	lstDietHistory;
	private List<MEXMEDiarioEnf>	lstDiary;

	/** Cuidados del paciente */
	public List<MEXMECuidadosPac> getLstCuidadosPac(boolean derechoHabiente) {
		if (lstCuidadosPac == null) {
			lstCuidadosPac = MEXMECuidadosPac.get(getCtx(), getEXME_CtaPac_ID(), true, 0, null, derechoHabiente);
		}
		return lstCuidadosPac;
	}

	/** Control diabetico */
	public List<MEXMEEsqInsulina> getLstEsqInsulinas() {
		if (lstEsqInsulinas == null) {
			lstEsqInsulinas = MEXMEEsqInsulina.get(getCtx(), getEXME_Paciente_ID(), null );
		}
		return lstEsqInsulinas;
	}
	
	/** Historico de dietas */
	public List<MEXMEPrescDieta> getLstDietHistory(boolean derechoHabiente) {
		if (lstDietHistory == null) {
			lstDietHistory = MEXMEPrescDieta.getHistory(getCtx(), getEXME_CtaPac_ID(), -1, null);
		}
		return lstDietHistory;
	}

	/** Historico de diarios **/
	public List<MEXMEDiarioEnf> getLstDiary() {
		if (lstDiary == null) {
			lstDiary = MEXMEDiarioEnf.getList(getCtx(), getEXME_CtaPac_ID(), null, null);
		}
		return lstDiary;
	}
	/*Propiedades para reporte eMar 4 Horas / emar4Hours.jasper*/
	/**
	 * Sexo del paciente
	 * @return Sex
	 */
	public String getSex(){
		return getPaciente().getSex();
	}
	/**
	 * Edad del paciente
	 * @return EdadAMD
	 */
	public String getAge(){
		return String.valueOf(getPaciente().getAge().getEdadAMD());
	}
	/**
	 * Alergias del paciente
	 * @return MEXMEPacienteAler.getAlergias
	 */
	public String getAllergies(){
		final StringBuilder allergies = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<BasicoTresProps> lst = MEXMEPacienteAler.getAlergias(getCtx(), getEXME_Paciente_ID(), null);
		for (BasicoTresProps btp : lst) {
			if (allergies.length() > 0) {
				allergies.append(Constantes.NEWLINE);
			}
			allergies.append(btp.getNombre());
		}
		return allergies.toString();
	}
	/**
	 * Nombre del paciente
	 * @return EXME_Paciente.NombrePac
	 */
	public String getPatientName(){
		return getPaciente() == null ? "" : getPaciente().getNombre_Pac();
	}
	/**
	 * MRN del paciente
	 * @return MRN
	 */
	public String getMRN(){
		return getPaciente().getMRN();
	}
	/**
	 * Area del paciente
	 * @return getTipoArea 
	 */
	public String getTipoAreaStr(){
		return MRefList.getListName(getCtx(), MEXMECtaPac.TIPOAREA_AD_Reference_ID, getTipoArea());
	}
	/**
	 * Tipo de paciente
	 * @return getEXME_TipoPaciente
	 */
	public String getPatientType(){
		return getEXME_TipoPaciente() != null ? getEXME_TipoPaciente().getName() : "";
	}
	/**
	 * Habitación y cama del paciente
	 * @return getHabitacion + getCama
	 */
	public String getRoomBed(){
		StringBuilder retVal = new StringBuilder("");
		if(getCama() != null){
			retVal.append(getCama().getHabitacion() != null ? getCama().getHabitacion().getName() :"");
			retVal.append(" ");
			retVal.append(getCama().getName());
		}
		return retVal.toString();
	}
	
	/**
	 * Busca el nombre del tipo de paciente de una cuenta
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @return Nombre del tipo de paciente o null
	 */
	public static String getPatientType(Properties ctx, int ctaPacId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  t.name ");
		sql.append("FROM ");
		sql.append("  exme_ctapac cta ");
		sql.append("  INNER JOIN exme_tipopaciente t ");
		sql.append("  ON cta.exme_tipopaciente_id = t.exme_tipopaciente_id ");
		sql.append("WHERE ");
		sql.append("  cta.exme_ctapac_id = ? ");
		return DB.getSQLValueString(null, sql.toString(), ctaPacId);
	}
	
	/**
	 * Estatura del paciente
	 * @return getHeight
	 */
	public String getHeight(){
		return String.valueOf(getPaciente().getHeight());
	}
	/**
	 * Peso del paciente
	 * @return getWeight
	 */
	public String getWeight(){
		return String.valueOf(getPaciente().getWeight());
	}
	
	/**
	 * Obtenemos los datos de la relacion paquete - cta
	 * @return
	 */
	public MEXMECtaPacPaq[] ctaPacPaq = null;
	
	public MEXMECtaPacPaq[] getCtasPacPaq() {
		if(ctaPacPaq == null || ctaPacPaq.length<=0){
			List<MEXMECtaPacPaq> list = MEXMECtaPacPaq.getFromCtaPac(getCtx(), getEXME_CtaPac_ID(),
				" EXME_CtaPacPaq.EXME_CtaPacPaq_ID DESC ", get_TrxName());
			ctaPacPaq = new MEXMECtaPacPaq[list.size()];
			list.toArray(ctaPacPaq);
		}
		return ctaPacPaq;
	}

	public String getEncounterStatusStr() {
		return MRefList.getListName(getCtx(), ENCOUNTERSTATUS_AD_Reference_ID, getEncounterStatus());
	}


	public boolean getFormasDePagoDeAnticipo(MFormaPago fpago) {
		return getOfCtaPac(getCtx(), getEXME_CtaPac_ID(),  fpago.getRef_FormaPago_ID(), get_TrxName());
	}
	
	
	 public  boolean getOfCtaPac(Properties ctx, int EXME_CtaPac_ID,  int EXME_FormaPago_ID, String trxName) {
	    	
	        
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	        
	        sql.append("select * ")
	           .append("from C_CASHLINE c ")
	           .append("where c.EXME_CTAPAC_ID = ? ")
	           .append("and c.EXME_formapago_ID = ? ");
	        
	        try {
	            pstmt = DB.prepareStatement(sql.toString(),trxName);
	            
	            pstmt.setInt(1,EXME_CtaPac_ID);
	            pstmt.setInt(2,EXME_FormaPago_ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
				  return true;
				}
	        } catch (Exception e) {

//				log.log(Level.SEVERE, "", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
	       
	        return false;
	    }
	 
	 	/**
	 	 * 
	 	 * @param trxName
	 	 * @return
	 	 */
		public List<MInvoice> getFacturas(final String trxName) {
			final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT C_Invoice.* ")
					.append(" FROM C_Invoice ")
					.append(" INNER JOIN C_DocType d ON (d.C_DocType_ID = C_Invoice.C_DocType_ID ) ")
					.append(" WHERE C_Invoice.IsActive = 'Y' ")
					.append(" AND   C_Invoice.EXME_CtaPac_ID = ? ")
					.append(" AND   d.DocBaseType = '"
							+ MDocType.DOCBASETYPE_ARInvoice + "' ");
			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(),
					sql.toString(), "C_Invoice"));
			sql.append(" ORDER BY C_Invoice.Updated DESC");

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, getEXME_CtaPac_ID());

				rset = pstmt.executeQuery();

				while (rset.next()) {
					lista.add(new MInvoice(getCtx(), rset, trxName));
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "MEXMECtaPac.getFacturas", e);
			} finally {
				DB.close(rset, pstmt);
			}

			return lista;
		}

	/**
	 * Hacer merge de todas las cuentas selccionadas del paciente
	 * a una sola cuenta
	 * 
	 * @param ctaPacsADesaparecer : Cuentas que se fusionaran
	 * @param trxName : nombre de transaccion
	 * @return : count>0 si se hizo la fusion de cuentas
	 */
	public int merge(final String ctaPacsADesaparecer, final String trxName) {
		int merged = 0;
		// Cambiar los paquetes de lugar
		final int countPack = mergePack(ctaPacsADesaparecer, trxName);
		if (countPack < 0) {// -1
			s_log.severe("MergePack count: -1");
			return -1;
		} else {
			merged += countPack;
			s_log.config("MergePack count: " + countPack);
		}
		// Cambiar los anticipos de lugar
		final int countPay = mergePay(ctaPacsADesaparecer, trxName);
		if (countPay < 0) {// -1
			s_log.severe("MergePay count: -1");
			return -1;
		} else {
			merged += countPay;
			s_log.config("MergePay count: " + countPay);
		}
		// Cambiar los cargos de lugar
		final int countCharge = mergeCharge(ctaPacsADesaparecer, trxName);
		if (countCharge < 0) {// -1
			s_log.severe("MergeCharge count: -1");
			return -1;
		} else {
			merged += countCharge;
			s_log.config("MergeCharge count: " + countCharge);
		}
		// merge con los cargos
		if (countPack > 0 || countCharge > 0) {
			PackageBalance.batchMatch(getCtx(), getEXME_CtaPac_ID(), trxName);
		}
		return merged;
	}

	/**
	 * Paquetes del paciente
	 * 
	 * @param ctaPacsADesaparecer
	 * @param trxName
	 * @return
	 */
	private int mergePack(final String ctaPacsADesaparecer, final String trxName) {
		// todas las relaciones cta-paq de las cuentas a hacer fusionar
		int count = 0;
		final StringBuilder whereClause = new StringBuilder();
		whereClause.append(" EXME_CtaPacPaq.EXME_CtaPac_ID IN (").append(ctaPacsADesaparecer).append(") ");
		// Se excluyen los paquetes ya incluidos en la cuenta. .- lama
		whereClause.append(" AND EXME_CtaPacPaq.EXME_PaqBase_Version_ID NOT IN ");
		whereClause.append("  (SELECT EXME_PaqBase_Version_ID FROM EXME_CtaPacPaq WHERE EXME_CtaPac_ID=? AND isActive='Y') "); 
		final String orderBy = " EXME_CtaPacPaq.EXME_CtaPacPaq_ID DESC ";
		
		final List<MEXMECtaPacPaq> lst = MEXMECtaPacPaq.get(getCtx(), whereClause.toString(), orderBy, trxName, getEXME_CtaPac_ID());
		// update: EXME_CtaPac_ID , EXME_CtaPacExt_ID
		for (MEXMECtaPacPaq mCtaPacPaq : lst) {
			mCtaPacPaq.setEXME_CtaPac_ID(getEXME_CtaPac_ID());// cuenta paciente
			mCtaPacPaq.setEXME_CtaPacExt_ID(getEXME_CtaPacExt_ID());// extension cero
			if (mCtaPacPaq.save(trxName)) {
				count++;
			} else {
				throw new MedsysException();// lama
			}
		}
		return count;
	}

	/**
	 * Pagos del paciente
	 * 
	 * @param ctaPacsADesaparecer
	 * @param trxName
	 * @return
	 */
	private int mergePay(final String ctaPacsADesaparecer, final String trxName) {
		int count = 0;
		// Anticipo de la cuenta principal
		MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), trxName);// extension cero
		// Si no tiene anticipos
		if (anticipo == null) {
			// Creamos un nuevo anticipo
			anticipo = new MEXMEAnticipo(getCtx(), getEXME_CtaPac_ID(), getEXME_CtaPacExt_ID(), trxName);
		}

		// SELECT * FROM EXME_Anticipos (si ya existe agregar el monto)
		// update: EXME_CtaPac_ID , EXME_CtaPacExt_ID
		List<MEXMEAnticipo> lstAnt = MEXMEAnticipo.getAnticiposPorPaciente(getCtx(), ctaPacsADesaparecer, trxName);
		for (MEXMEAnticipo mAnticipoOld : lstAnt) {

			anticipo.setSaldo(anticipo.getSaldo().add(mAnticipoOld.getSaldo()));
			anticipo.setTotal(anticipo.getTotal().add(mAnticipoOld.getTotal()));
			anticipo.setAplicado(anticipo.getAplicado().add(mAnticipoOld.getAplicado()));

			mAnticipoOld.setIsActive(false);

			if (mAnticipoOld.save(trxName) && anticipo.save(trxName)) {
				count++;
			} else {
				throw new MedsysException();// lama
			}
		}

		// SELECT * FROM EXME_CtaPacPag
		// update: EXME_CtaPac_ID , EXME_CtaPacExt_ID
		List<MCtaPacPag> lst = MCtaPacPag.getAnticiposPorPaciente(getCtx(), ctaPacsADesaparecer, trxName);
		for (MCtaPacPag mCtaPacPaq : lst) {
			mCtaPacPaq.setEXME_CtaPacExt_ID(getEXME_CtaPacExt_ID());// extension cero
			if (!mCtaPacPaq.save(trxName)) {
				throw new MedsysException();// lama
			}
		}

		// SELECT * FROM C_Payment
		// update: EXME_CtaPac_ID , EXME_CtaPacExt_ID
		List<MPayment> lstPag = MEXMEPayment.getPagosPorPaciente(getCtx(), ctaPacsADesaparecer, trxName);
		for (MPayment mPayment : lstPag) {
			mPayment.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
			if (!mPayment.save(trxName)) {
				throw new MedsysException();//lama
			}
		}
		return count;
	}

	/**
	 * Cargos del paciente
	 * 
	 * @param ctaPacsADesaparecer
	 * @param trxName
	 * @return
	 */
	private int mergeCharge(final String ctaPacsADesaparecer, final String trxName) {
		int count = 0;
		// update: EXME_CtaPac_ID , EXME_CtaPacExt_ID, 
		// TipoLinea = 'EX' cambiar a 'CG' <-- Error: cambia los de tipo BasePack provocando errores (null) en el match 
		
		final StringBuilder where = new StringBuilder();
		where.append(" EXME_CtaPacDet.exme_ctapac_id IN (").append(ctaPacsADesaparecer).append(") ");
		// Se excluyen los paquetes ya incluidos en la cuenta. .- lama
		where.append(" AND (EXME_CtaPacDet.TipoLinea <> ? ");
		where.append("      OR EXME_CtaPacDet.EXME_PaqBase_Version_ID NOT IN ");
		where.append("         (SELECT EXME_PaqBase_Version_ID FROM EXME_CtaPacPaq WHERE EXME_CtaPac_ID=? AND isActive='Y')) ");

		final List<MCtaPacDet> lista = new Query(getCtx(), X_EXME_CtaPacDet.Table_Name, where.toString(), trxName)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(X_EXME_CtaPacDet.TIPOLINEA_BasePack, getEXME_CtaPac_ID())
			.list();
		
		for (MCtaPacDet mCtaPacDet : lista) {
			mCtaPacDet.setEXME_CtaPac_ID(getEXME_CtaPac_ID());// cuenta paciente
			mCtaPacDet.setEXME_CtaPacExt_ID(getEXME_CtaPacExt_ID());// extension cero
			if(X_EXME_CtaPacDet.TIPOLINEA_Exempt.equals(mCtaPacDet.getTipoLinea())){
				mCtaPacDet.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);//lama
			}
			if (!mCtaPacDet.save(trxName)) {
				throw new MedsysException();//lama
			} else {
				count++;
			}
		}
		return count;
	}
		
		/***
		 * Obtiene Lista de Cuentas Paciente en la que se pueda hacer merge por paciente
		 * @param ctx   Properties
		 * @param trxName Nombre de la transaccion
		 */
		public List<MEXMECtaPac> getCtasMerge(final Properties ctx, final String trxName){
			final List <MEXMECtaPac> cuentas = new ArrayList<MEXMECtaPac>();
			final StringBuilder sql = new StringBuilder()
			
			.append(" SELECT * ")
			.append(" FROM EXME_CtaPac cta ")
			.append(" WHERE cta.IsActive = 'Y' ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_CtaPac.Table_Name, "cta"))
			.append(" and cta.EXME_Paciente_ID = ? ")
			// Cuentas cerradas
			//.append(" and cta.EncounterStatus IN ( ")
			//.append(DB.TO_STRING(X_EXME_CtaPac.ENCOUNTERSTATUS_Close))
			//.append(", ")
			//.append(DB.TO_STRING(X_EXME_CtaPac.ENCOUNTERSTATUS_Discharge))
			//.append(") ")
			// Dadas de Altas o Cerradas
			//.append(" and ( cta.fechaalta is not null ")
			.append(" AND cta.FechaCierre IS NOT NULL ")// SOLO CERRADAS
			
			// que no tenga merge anterior
			.append(" and cta.IsIdentifier = 'Y'      ")
			.append(" and cta.EXME_CtaPac_ID not in ( ")
			.append("            SELECT distinct cp.EXME_CtaPacRefer_ID            ")
			.append("            from  EXME_CtaPac cp                              ")
			.append("            where cp.EXME_CtaPacRefer_ID = cta.EXME_CtaPac_ID ")
			.append("            And   cp.EXME_CtaPac_ID <> cta.EXME_CtaPac_ID)    ")
			// no facturadas
			.append(" and cta.EXME_CtaPac_ID not in (SELECT EXME_CtaPac_ID ")
			.append("            FROM  EXME_CtaPacExt                      ")
			.append("            WHERE IsActive = 'Y'                      ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_CtaPacExt.Table_Name))
			.append("            and EXME_Paciente_ID = ?                  ")
			.append("            AND C_Invoice_ID IS NOT NULL )            ");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setInt(1, getEXME_Paciente_ID());
				pstmt.setInt(2, getEXME_Paciente_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cuentas.add(new MEXMECtaPac(ctx, rs, trxName));
				}			
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}		
			return cuentas; 
		}
		
		/**
		 * Anticipo de la extension cero
		 * @return
		 */
		public MEXMEAnticipo getAnticipoExtCero(){
			MEXMEAnticipo mAnticipoExtCero = null;
			if (mAnticipoExtCero == null) {
				mAnticipoExtCero = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), 
						getEXME_CtaPacExt_ID(), get_TrxName());// extension cero
				// Si no tiene anticipos
				if (mAnticipoExtCero == null) {
					// Creamos un nuevo anticipo
					mAnticipoExtCero = new MEXMEAnticipo(getCtx(), getEXME_CtaPac_ID(), 
							getEXME_CtaPacExt_ID(), get_TrxName());
				}
			}
			return mAnticipoExtCero;
		}

		/**
		 * 
		 * @param ctx
		 * @param CbPartnerID
		 * @param supportBilling
		 * @return
		 */
		public static List<InvoicedEXME_CtaPac> getInvoicedCtaPacs(Properties ctx, int CbPartnerID, String supportBilling) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<InvoicedEXME_CtaPac> list = new ArrayList<InvoicedEXME_CtaPac>();

			final StringBuilder sql = new StringBuilder(" SELECT distinct CTA.*, CPY.AdmitDate, CPY.FechaAlta FROM EXME_Cta_Payer CPY ")
			.append("INNER JOIN EXME_CTAPAC CTA ON CTA.EXME_CTAPAC_ID = CPY.EXME_CTAPAC_ID AND CTA.ENCOUNTERSTATUS = ? ")
			.append("INNER JOIN EXME_CTAPACEXT CTE ON CTA.EXME_CTAPAC_ID = CTE.EXME_CTAPAC_ID ")
			.append("INNER JOIN C_INVOICE I ON CTA.EXME_CTAPAC_ID = I.EXME_CTAPAC_ID ")
			.append("INNER JOIN C_DOCTYPE T ON  I.C_DOCTYPETARGET_ID = T.C_DOCTYPE_ID ")
			.append("AND (T.DOCBASETYPE = 'ARI' OR t.DOCBASETYPE = 'ARD') ")
			.append("WHERE CPY.C_BPARTNER_ID IN ");
			if (DB.isOracle()){
				sql.append(" (SELECT ID FROM TABLE(fnc_getBPartnerTree(?))) ");
			}else{
				sql.append(" (SELECT ID FROM fnc_getBPartnerTree(?)) ");
			}
			sql.append("AND CPY.SupportBilling IN (?,'BO') ").append("AND CPY.isActive = 'Y' ");

			if (X_EXME_PacienteAseg.SUPPORTBILLING_Professional.equals(supportBilling)) {
				sql.append(" AND CTE.PROFESSIONALSTATUS NOT IN( '")
				.append(MEXMECtaPacExt.PROFESSIONALSTATUS_CodingIncomplete).append("','")
				.append(MEXMECtaPacExt.PROFESSIONALSTATUS_NotApplicable).append("','")
				.append(MEXMECtaPacExt.PROFESSIONALSTATUS_NotBilled).append("')");
			} else if (X_EXME_PacienteAseg.SUPPORTBILLING_Institucional.equals(supportBilling)) {
				sql.append(" AND CTE.InstitutionalStatus NOT IN( '")
				.append(MEXMECtaPacExt.INSTITUTIONALSTATUS_CodingIncomplete).append("','")
				.append(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotApplicable).append("','")
				.append(MEXMECtaPacExt.INSTITUTIONALSTATUS_NotBilled).append("')");
			}
			String select = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), MEXMECtaPayer.Table_ID, "CPY")
					+ " order by CTA.documentNo desc";

			try {
				pstmt = DB.prepareStatement(select, null);
				pstmt.setString(1, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
				pstmt.setInt(2, CbPartnerID);
				pstmt.setString(3, supportBilling);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					list.add(new InvoicedEXME_CtaPac(ctx, rset, null));
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, e.getMessage(), e);
			} finally {
				DB.close(rset, pstmt);
			}
			return list;
		}


		/**
		 * 
		 * @author 
		 *
		 */
	public static class InvoicedEXME_CtaPac extends MEXMECtaPac{
		private static final long	serialVersionUID	= 1L;
		private final Timestamp admitDate;
		private final Timestamp dischargedtDate;
		public InvoicedEXME_CtaPac(Properties ctx, ResultSet rs, String trxName) throws SQLException {
			super(ctx, rs, trxName);
			admitDate = rs.getTimestamp(X_EXME_Cta_Payer.COLUMNNAME_AdmitDate);
			dischargedtDate = rs.getTimestamp(X_EXME_Cta_Payer.COLUMNNAME_FechaAlta);
		}
		public Timestamp getAdmitDate() {
			return admitDate;
		}

		public Timestamp getDischargedtDate() {
			return dischargedtDate;
		}		
	}
			
	
	private String service;

	public String getService() {
		if(service == null && getEXME_Area_ID() > 0){
			service = getEXME_Area().getName();
		}
		return service;
	}
	public void setService(final String service) {
		this.service = service;
	}
	
	public String getResStatusStr() {
		return MRefList.getListName(getCtx(), RESSTATUS_AD_Reference_ID, super.getResStatus());
	}
	
	@Override
	public String getNombre_Pac() {
		if (getEXME_Paciente_ID() > 0) {
			return getPatientName();
		} else {
			return super.getNombre_Pac();
		}
	}
	
	/**
	 * Socio de negocios por defecto de la cuenta paciente
	 * estan configurados en X_EXME_ConfigEC ó X_EXME_ConfigPre
	 * antes se tomaba el del paciente, pero ahora ese se quedará a nivel de system
	 * y para la cuenta paciente se tomara a nivel de organización
	 * @return
	 */
	public int getC_BPartner_ID(){
		
		// Se requiere el tipo de area para tomar la lista de precios correcta
		// y el esquema de descuento
		String tipoArea =getTipoArea() == null ? X_EXME_TipoPaciente.TIPOAREA_Hospitalization 
				: getTipoArea();


		if (getTipoPaciente() != null) {
			tipoArea = getTipoPaciente().getTipoArea();
		} else {
			tipoArea = getTipoArea();
		}
		
		// Cuando se crea el paciente con el registro rapido de paciente toma el de X_EXME_ConfigEC.getC_BPartner_ID()
		// y cuando se crea en el ingreso del paciente se crea con X_EXME_ConfigPre.getC_BPartner_ID()
		// como se sabe de que opcion se creo el paciente entonces es por tipo de paciente
		// utilizando parte de la misma logica para obtener el precio de los producto
		int socio = 0;
		if(tipoArea == null
				|| X_EXME_TipoPaciente.TIPOAREA_Ambulatory.contains(tipoArea)
				|| X_EXME_TipoPaciente.TIPOAREA_MedicalConsultation.equals(tipoArea)
				|| X_EXME_TipoPaciente.TIPOAREA_Other.equals(tipoArea)
				|| X_EXME_TipoPaciente.TIPOAREA_Emergency.equals(tipoArea) ){
			// consulta externa/ambulatorio/urgencias
			socio = MConfigEC.get(getCtx()).getC_BPartner_ID();
		} else {
			// hospitalizacion
			socio = MEXMEConfigPre.get(getCtx(),null).getC_BPartner_ID();
		}
		return socio;
	}
	
	/**
	 * Direccion de facturación del particular
	 * @return
	 */
	public BeanDatosFacturacion getDireccionParticular(){
		datosFacturacion = getPaciente().getDireccionParticular();
		// Socio de las configuraciones a nivel organizacion
		if(datosFacturacion!=null){
			datosFacturacion.setC_BPartner_ID(getC_BPartner_ID());
		}
		return datosFacturacion;
	}

	/**
	 * Metodo para Obtener la fecha de cierre
	 * 
	 * @param ctx
	 *            el contexto
	 * @param ctaPacID
	 *            el ID de la cuenta a consultar
	 * @param trxName
	 *            la transaccion
	 **/
	public static Timestamp getFechaCierre(final Properties ctx, int ctaPacID, final String trxName){
		Timestamp fechaCierre = null;
		final StringBuilder sql = new StringBuilder()
		
		.append(" SELECT FechaCierre ")
		.append(" FROM EXME_CtaPac cta ")
		.append(" WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_CtaPac.Table_Name, "cta"))
		.append(" and EXME_CtaPac_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fechaCierre = rs.getTimestamp("FechaCierre");
			}			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}		
		return fechaCierre; 
	}
	
	/**
	 * Obtiene las cuentas con paquetes pendientes
	 * 
	 * @param ctx
	 *            Contexto
	 * @param date
	 *            Fecha Inicial
	 * @param date2
	 *            Fecha Final
	 * @param trxName
	 *            Trx Name
	 * @return Listado de cargos de paquetes
	 */
	public static List<MEXMECtaPac> getEncountersWithPendingPackages(Properties ctx, Date date, Date date2, String trxName) {
		List<MEXMECtaPac> list = new ArrayList<MEXMECtaPac>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  cta.* ");
		sql.append("FROM ");
		sql.append("  exme_ctapac cta ");
		sql.append("  INNER JOIN exme_ctapacpaq pp ");
		sql.append("  ON cta.exme_ctapac_id = pp.exme_ctapac_id ");
		sql.append("  INNER JOIN exme_paqbase_version pv ");
		sql.append("  ON pp.exme_paqbase_version_id = pv.exme_paqbase_version_id ");
		sql.append("  INNER JOIN exme_paqbase pb ");
		sql.append("  ON pv.exme_paqbase_id = pb.exme_paqbase_id ");
		sql.append("  INNER JOIN exme_ctapacdet det ");
		sql.append("  ON (pp.EXME_PaqBase_Version_ID = det.EXME_PaqBase_Version_ID AND ");
		sql.append("  cta.exme_ctapac_id = det.exme_ctapac_id AND ");
		sql.append("  det.tipolinea = 'PB' AND ");
		sql.append("  det.isActive = 'Y' AND ");
		sql.append("  det.sedevolvio = 'N' AND ");
		sql.append("  det.isfacturado ='N' ) ");
		sql.append("WHERE ");
		sql.append("  pp.created BETWEEN ");
		sql.append("  ? AND ");
		sql.append("  ? AND ");
		sql.append("  pp.isactive = 'Y' AND ");
		sql.append("  pv.isactive = 'Y' AND ");
		sql.append("  pb.isactive = 'Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, MEXMECtaPac.Table_Name, "cta"));
		
		sql.append(" ORDER BY ");
		sql.append("  cta.created desc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setTimestamp(1, TimeUtil.getInitialRangeT(ctx, date));
			pstmt.setTimestamp(2, TimeUtil.getFinalRangeT(ctx, date2));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MEXMECtaPac(ctx, rs, null));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Metodo para Obtener los pacientes que han tenido descuentos
	 * 
	 * @param ctx
	 *            el contexto
	 * @param fechaIni
	 *            fecha en la que inicia la busqueda
	 * 
	 * @param fechaFin
	 * 			  fecha en la que termina la busqueda
	 * @param trxName
	 *            la transaccion
	 **/
	public static List<DiscountChargesData> getDiscountChargesData(Properties ctx,Timestamp fechaIni, Timestamp fechaFin,String trxName) {
		List<DiscountChargesData> values = new ArrayList<DiscountChargesData>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append("SELECT ");
			sql.append("  ep.Nombre_Pac ");
			sql.append("  AS nombreCuenta, ");
			sql.append("  ecp.DocumentNo ");
			sql.append("  AS cuentaPaciente, ");
			sql.append("  ci.Documentno ");
			sql.append("  AS notaRemision, ");
			sql.append("  civ.Documentno ");
			sql.append("  AS facturas, ");
			sql.append("  ci.totallines+ci.discountamt ");
			sql.append("  AS subTotal, ");
			sql.append("  ci.discountamt ");
			sql.append("  AS descuento, ");
			sql.append("  ci.totallines ");
			sql.append("  AS cargos, ");
			sql.append("  ci.grandtotal-ci.totallines ");
			sql.append("  AS impuesto, ");
			sql.append("  ci.grandtotal ");
			sql.append("  AS total ");
			sql.append("FROM ");
			sql.append("  exme_ctapac ecp ");
			sql.append("  INNER JOIN exme_paciente ");
			sql.append("  AS ep ");
			sql.append("  ON (ep.exme_paciente_id = ecp.exme_paciente_id) ");
			sql.append("  INNER JOIN exme_ctapacext ecpe ");
			sql.append("  ON (ecpe.exme_ctapac_id = ecp.exme_ctapac_id) ");
			sql.append("  INNER JOIN c_invoice ");
			sql.append("  AS ci ");
			sql.append("  ON (ecpe.c_invoice_id = ci.c_invoice_id) left ");
			sql.append("  JOIN c_invoice_customer_v ");
			sql.append("  AS civ ");
			sql.append("  ON (ci.c_invoice_id = civ.Ref_invoice_Sales_id) ");
			sql.append("WHERE ");
			sql.append("  ecpe.isactive = 'Y' AND ");
			sql.append("  ecp.ad_client_id = ? AND ");
			sql.append("  ecp.ad_org_id = ? AND ");
			sql.append("  ci.discountamt > 1 AND ");
			sql.append("  ci.dateinvoiced BETWEEN ");
			sql.append(" ? AND ");
			sql.append(" ? ORDER BY ");
			sql.append("  ci.c_invoice_id ");
			
			pstm = DB.prepareStatement(sql.toString(), trxName);
			
			pstm.setInt(1, Env.getAD_Client_ID(ctx));
			pstm.setInt(2, Env.getAD_Org_ID(ctx));
			if (fechaIni != null && fechaFin != null) {
				if (fechaIni.after(fechaFin)) {					
					pstm.setTimestamp(3, (Timestamp) TimeUtil.getFinalRange(ctx, fechaFin));
					pstm.setTimestamp(4, TimeUtil.getInitialRangeT(ctx, fechaIni));
				} else {
					pstm.setTimestamp(3,TimeUtil.getInitialRangeT(ctx, fechaIni));
					pstm.setTimestamp(4,(Timestamp) TimeUtil.getFinalRange(ctx, fechaFin));
				}
			}
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				final DiscountChargesData rValues = new DiscountChargesData();
				rValues.setNombreCuenta(SecureEngine.decrypt(rs.getString("nombreCuenta")));
				rValues.setCuentaPaciente(rs.getString("cuentaPaciente"));
				rValues.setNotaRemision(rs.getString("notaRemision"));
				rValues.setFacturas(rs.getString("facturas"));
				rValues.setSubTotal(rs.getString("subTotal"));
				rValues.setDescuento(rs.getString("descuento"));
				rValues.setCargos(rs.getString("cargos"));
				rValues.setImpuesto(rs.getString("impuesto"));
				rValues.setTotal(rs.getString("total"));
				
				values.add(rValues);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}

	/**
	 * El método regresa una Cuenta Paciente Activa de un paciente cumpliendo las reglas
	 * Utilizando como reglas donde EncounterStatus este como 'I' o 'P'
	 * Y no debe tener fecha de cierre
	 * 
	 * @param ctx El contexto de la aplicacion
	 * @param patient_id Trae la cuenta activa de un paciente
	 * @param trxName El nombre de la transaccion
	 * @return
	 */
	public static MEXMECtaPac getCurrentCtaPac(final Properties ctx, final int patient_id, final String trxName){
		return new Query(ctx, Table_Name, " EXME_Paciente_ID = ? AND EncounterStatus IN (?,?) AND FechaCierre IS NULL ", trxName)//
		.setParameters(patient_id, ENCOUNTERSTATUS_Admission, ENCOUNTERSTATUS_Predischarge)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.first();
	}
	
	
	/**
	 * Valida si la cuenta paciente es de una madre y tiene cuentas relacionadas
	 * @return
	 */
	public boolean hasChildren() {
		boolean retValue = false;
		if (X_EXME_Paciente.SEXO_Female.equals(getPaciente().getSexo()) // Female
			&& getActividades() != null && !getActividades().isEmpty() // activity
			&& getActividades().get(0).isPregnant()) { // pregnant
			// Valid kinship
			final Integer[] ids = MEXMEParentesco.getForNewborns(getCtx());
			if (ids.length > 0) {
				retValue = !getNewBorns(getCtx(), ids, false, null).isEmpty();
			}
		}
		return retValue;
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeDelete() {
		DB.executeUpdate("DELETE EXME_CtaPacMed WHERE EXME_CtaPac_ID=?", getEXME_CtaPac_ID(), false, get_TrxName());
		return true;
	}

	public MEXMEActPacienteIndH getActPacienteIndH() {
		return actPacienteIndH;
	}

	public void setActPacienteIndH(final MEXMEActPacienteIndH actPacIndH) {
		actPacienteIndH = actPacIndH;
	}
	
	/** Anticipos por cuenta paciente */
	public BigDecimal[] getCustomerAdvances(){
		BigDecimal customerAdvancesAllocated = Env.ZERO;
		BigDecimal customerAdvancesExtZero = Env.ZERO;
		final List<MEXMECtaPacPag> lst = MEXMECtaPacPag.getAnticiposPorPaciente(getCtx(),
				""+getEXME_CtaPac_ID(), null);
		for (final MEXMECtaPacPag mCtaPacPaq: lst) {
			if(mCtaPacPaq.getEXME_CtaPacExt_ID()==getEXME_CtaPacExt_ID()){
				//customerAdvancesExtZero = customerAdvancesExtZero.add(mCtaPacPaq.getPayment().getPayAmt());
				customerAdvancesExtZero = customerAdvancesExtZero.add(mCtaPacPaq.getAplicado());
			} else {
				//customerAdvancesAllocated = customerAdvancesAllocated.add(mCtaPacPaq.getPayment().getPayAmt());
				customerAdvancesAllocated = customerAdvancesAllocated.add(mCtaPacPaq.getAplicado());
			}
		}
		return new BigDecimal[]{customerAdvancesExtZero,customerAdvancesAllocated};
	}
	
	public BigDecimal getTotalBalance(){
		StringBuilder sql = new StringBuilder("select ( ");
			sql.append("coalesce((select sum(coalesce(taxamt,0) + coalesce(linenetamt,0)) from EXME_CtaPacDet "); 
			sql.append("where exme_ctapac_id = ? ");
			sql.append("),0) - ");
			sql.append("coalesce((select sum(i.grandtotal) from EXME_CtaPacExt e ");
			sql.append("inner join c_invoice i on e.c_invoice_id = i.c_invoice_id ");
			sql.append("where e.exme_ctapac_id = ? ");
			//sql.append("and i.isPaid = 'Y' ");
			sql.append("),0) ");
			sql.append(") as saldo;");
		BigDecimal saldo = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), this.getEXME_CtaPac_ID(), this.getEXME_CtaPac_ID());
		if(saldo == null){
			saldo = BigDecimal.ZERO;
		}
		return saldo;
	}
	
	public List<BigDecimal> getCustomerAdvancesSum() {
		List<BigDecimal> values = new ArrayList<BigDecimal>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append("SELECT ");
			
			sql.append(" (SELECT sum(coalesce(EXME_CtaPacPag.aplicado,0)) FROM EXME_CtaPacPag ");
			sql.append(" INNER JOIN EXME_CtaPacExt ON EXME_CtaPacPag.EXME_CtaPacExt_ID = EXME_CtaPacExt.EXME_CtaPacExt_ID ");
			sql.append(" WHERE EXME_CtaPacExt.exme_ctapac_id = ? and c_invoice_id is null) as Sin_Asignar, ");

			sql.append(" (SELECT sum(coalesce(EXME_CtaPacPag.aplicado,0)) FROM EXME_CtaPacPag ");
			sql.append(" INNER JOIN EXME_CtaPacExt ON EXME_CtaPacPag.EXME_CtaPacExt_ID = EXME_CtaPacExt.EXME_CtaPacExt_ID");
			sql.append(" WHERE EXME_CtaPacExt.exme_ctapac_id = ? and c_invoice_id is not null) as Asignado ");

			pstm = DB.prepareStatement(sql.toString(), null);
			pstm.setInt(1, getEXME_CtaPac_ID());
			pstm.setInt(2, getEXME_CtaPac_ID());

			rs = pstm.executeQuery();

			while (rs.next()) {
				values.add(new BigDecimal(rs.getInt("Sin_Asignar")));
				values.add(new BigDecimal(rs.getInt("Asignado")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstm);
		}
		return values;
	}
}
