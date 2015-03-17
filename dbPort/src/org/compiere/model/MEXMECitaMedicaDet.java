/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.bean.BalanceServicesH.BalanceService;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Modelo para ejecucion de citas pendientes de cerrar
 * 
 * @author Lorena Lama
 *
 */
public class MEXMECitaMedicaDet extends X_EXME_CitaMedicaDet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -6948865211206841153L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMECitaMedicaDet.class);

	/**
	 * @param ctx
	 * @param EXME_CitaMedicaDet_ID
	 * @param trxName
	 */
	public MEXMECitaMedicaDet(Properties ctx, int EXME_CitaMedicaDet_ID, String trxName) {
		super(ctx, EXME_CitaMedicaDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECitaMedicaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Llena los datos de la cita en ejecucion.
	 * 
	 * @param ctx
	 * @param cita
	 * @param trxName
	 */
	public MEXMECitaMedicaDet(Properties ctx, MEXMECitaMedica cita, String trxName) {
		super(ctx, 0, trxName);
		setFromAppointment(cita);
		setFromCtx(ctx);
	}

	private void setFromCtx(Properties ctx) {
		if (ctx != null) {
			setM_Warehouse_Sol_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));
		}
	}

	private void setFromAppointment(MEXMECitaMedica cita) {
		if (cita != null) {
			setEXME_CitaMedica_ID(cita.getEXME_CitaMedica_ID());
			setEXME_Medico_ID(cita.getEXME_Medico_ID());
		}
	}

	/**
	 * Obtiene las lineas del detalle de la ejecucion de cita pendiente de cerrar
	 * 
	 * @param ctx
	 * @param citaMedID
	 * @param trxName
	 * @return
	 */
	// public static List<MEXMECitaMedicaDet> getDetail(Properties ctx, int citaMedID, String trxName) {
	// List<MEXMECitaMedicaDet> retValue = new ArrayList<MEXMECitaMedicaDet>();
	//
	// StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	// PreparedStatement psmt = null;
	// ResultSet rs = null;
	//
	// try {
	// sql.append("SELECT * FROM ").append(Table_Name)
	// .append(" WHERE ").append(MEXMECitaMedica.Table_Name).append("_ID = ? ")
	// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
	//
	// psmt = DB.prepareStatement(sql.toString(), trxName);
	// psmt.setInt(1, citaMedID);
	// rs = psmt.executeQuery();
	//
	// while (rs.next()) {
	// MEXMECitaMedicaDet object = new MEXMECitaMedicaDet(ctx, rs, trxName);
	// retValue.add(object);
	// }
	//
	// } catch (Exception e) {
	// s_log.log(Level.SEVERE, sql.toString(), e);
	// } finally {
	// DB.close(rs, psmt);
	// }
	//
	// return retValue;
	//
	// }

	// private void set(MEXMECitaMedicaDet det) {
	// if (det != null) {
	// setEXME_CitaMedica_ID(det.getEXME_CitaMedica_ID());
	// setEXME_Medico_ID(det.getEXME_Medico_ID());
	// setEstatus(det.getEstatus());
	// }
	// }

	/**
	 * Guarda la informacion capturada de las tablas "ActPaciente"
	 * 
	 * @param ctx
	 * @param lstIndicaciones Lista de indicaciones "ServicioView"
	 * @param lstServicios Lista de servicios "MEXMEActPacienteInd"
	 * @param lstServHeader Encabezados de los servicios "MActPacienteIndH"
	 * @param lstDiagnosticos Lista de diagnosticos "BasicoTresProps"
	 * @param trxName
	 * @throws Exception
	 */
	// public void saveAll(Properties ctx, List<? extends ServicioView> lstIndicaciones, List<MEXMEActPacienteInd> lstServicios,
	// List<MActPacienteIndH> lstServHeader, List<BasicoTresProps> lstDiagnosticos, String trxName)
	// throws Exception {
	//
	// setEstatus(ESTATUS_Drafted);
	//
	// // elimina los registros anteriores
	// MEXMECitaMedicaDet.deleteOldValues(ctx, getEXME_CitaMedica_ID(), true, trxName);
	// // guarda las indicaciones
	// insertProduct(ctx, lstIndicaciones, trxName);
	// // guarda los servicios que no requieres ser aplicados previamente
	// insertServices(ctx, lstServHeader, lstServicios, trxName);
	// // guarda los diagnosticos
	// insertDiagnosis(ctx, lstDiagnosticos, trxName);
	// }

	// public void saveOdonto(Properties ctx,List<MEXMEActPacienteInd> lstServicios,
	// List<MActPacienteIndH> lstServHeader, String trxName) throws Exception {
	//
	// setEstatus(ESTATUS_Drafted);
	//
	// // elimina los registros anteriores
	// MEXMECitaMedicaDet.deleteOldValues(ctx, getEXME_CitaMedica_ID(), true, trxName);
	// // guarda las indicaciones
	// //insertProduct(ctx, lstIndicaciones, trxName);
	// // guarda los servicios que no requieres ser aplicados previamente
	// insertServices(ctx, lstServHeader, lstServicios, trxName);
	// // guarda los diagnosticos
	// //insertDiagnosis(ctx, lstDiagnosticos, trxName);
	// }

	/**
	 * Borra los datos anteriores de la ejecucion
	 * 
	 * @param exmeCitaMedicaID
	 * @param trxName
	 * @throws Exception
	 * @deprecated
	 */
	public static void deleteOldValues(Properties ctx, int EXME_CitaMedica_ID, boolean delete, String trxName) throws Exception {

		// Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
		if (EXME_CitaMedica_ID <= 0)
			throw new Exception("no existe la cita");

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_CitaMedicaDet WHERE EXME_CitaMedica_ID = ?");
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECitaMedicaDet cita = new MEXMECitaMedicaDet(ctx, rs, trxName);
				if (delete) {
					if (!cita.delete(true, trxName)) {
						throw new MedsysException();
					}
				} else {
					cita.setIsActive(false);
					if (!cita.save(trxName)) {
						throw new MedsysException();
					}
				}
				cita = null;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("error.insulinas.registro.eliminar");
		} finally {
			DB.close(rs, pstmt);
		}

		/* int no = 0;
		 * no = DB.executeUpdate("DELETE FROM EXME_CitaMedicaDet WHERE EXME_CitaMedica_id = ?",
		 * getEXME_CitaMedica_ID(), trxName);
		 * 
		 * if (no < 0)
		 * throw new Exception("Error al eliminar registros"); */
	}

	/**
	 * Obiene de la base de datos los servicios temporales de la cita
	 * @param ctx
	 * @param citaID
	 * @return
	 */
	public static List<BalanceService> getTodayServices(Properties ctx, int citaID) {
		List<BalanceService> lst = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT cita.EXME_Paciente_ID, cita.EXME_CitaMedicaDet_ID as apptTempId, prod.M_Product_ID, prod.name prodName, ");
			sql.append("coalesce(cita.EXME_PaqBase_Version_ID,0) EXME_PaqBase_Version_ID, coalesce(cita.EXME_ActPacienteInd_ID,0) EXME_ActPacienteInd_ID ");
		sql.append("FROM EXME_CitaMedicaDet  cita ");
		sql.append("INNER JOIN M_Product prod ON cita.M_Product_ID = prod.M_Product_ID ");
//		sql.append("LEFT JOIN EXME_CitaMedica citamed ON citamed.EXME_CitaMedica_ID = cita.EXME_CitaMedica_ID ");
		sql.append("WHERE cita.EXME_CitaMedica_ID = ? AND cita.ProductType = ? AND cita.Estatus <> ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMECitaMedicaDet.Table_Name, "cita"));

		try {
			psmt = DB.prepareStatement(sql.toString(), null);
			psmt.setInt(1, citaID);
			psmt.setString(2, MEXMECitaMedicaDet.PRODUCTTYPE_TodayService);
			psmt.setString(3, MEXMECitaMedicaDet.ESTATUS_Voided);
			rs = psmt.executeQuery();

			while (rs.next()) {
				BalanceService serv = new BalanceService();
				serv.setPatientId(rs.getInt("EXME_Paciente_ID"));
				serv.setProductId(rs.getInt("M_Product_ID"));
				serv.setApptTempId(rs.getInt("apptTempId"));
				serv.setProdName(rs.getString("prodName"));
				serv.setPaqBaseVerId(rs.getInt("EXME_PaqBase_Version_ID"));
				serv.setActIndId(rs.getInt("EXME_ActPacienteInd_ID"));
				lst.add(serv);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return lst;
	}

	/**
	 * Inserta los diagnosticos
	 * 
	 * @param ctx
	 * @param lstDiagnosticos
	 * @param trxName
	 * @throws Exception
	 */
	// private void insertDiagnosis(Properties ctx, List<BasicoTresProps> lstDiagnosticos, String trxName)
	// throws Exception {
	// for (int i = 0; i < lstDiagnosticos.size(); i++) {
	// BasicoTresProps diagnostico = lstDiagnosticos.get(i);
	//
	// MEXMECitaMedicaDet citaDet = new MEXMECitaMedicaDet(ctx, 0, trxName);
	//
	// citaDet.set(this);
	//
	// citaDet.setDescription(diagnostico.getDescripcion());
	// citaDet.setEXME_Diagnostico_ID((int) diagnostico.getId());
	//
	// if (!citaDet.save(trxName))
	// throw new Exception("Diagnosis is not saved");
	// }
	// }

	/**
	 * Inserta los servicios que no se requiere aplicar antes de cerrar la cita
	 * 
	 * @param ctx
	 * @param lstServHeader
	 * @param lstServicios
	 * @param trxName
	 * @throws Exception
	 */
	// public void insertServices(Properties ctx, List<MActPacienteIndH> lstServHeader,
	// List<MEXMEActPacienteInd> lstServicios, String trxName) throws Exception {
	//
	// for (int i = 0; i < lstServHeader.size(); i++) {
	//
	// MEXMECitaMedicaDet citaDet = new MEXMECitaMedicaDet(ctx, 0, trxName);
	//
	// citaDet.set(this);
	//
	// MActPacienteIndH header = lstServHeader.get(i);
	//
	// // header
	// citaDet.setIsService(true);
	// citaDet.setDatePromised(header.getDatePromised());
	// citaDet.setProtocolo(header.getProtocolo());
	// citaDet.setDiagnostico(header.getDiagnostico());
	// citaDet.setPriorityRule(header.getPriorityRule());
	//
	// citaDet.setM_Warehouse_Sol_ID(Env.getContextAsInt(ctx, "#M_Warehouse_ID"));
	// citaDet.setM_Warehouse_ID(header.getM_Warehouse_ID());
	//
	// // baremos la lista de servicios
	// for (int j = 0; j < lstServicios.size(); j++) {
	// MEXMEActPacienteInd serv = lstServicios.get(j);
	//
	// // si el almacen es = al del header creamos su linea
	// if (header.getM_Warehouse_ID() == serv.getM_Warehouse_ID()) {
	//
	// MEXMECitaMedicaDet citaDetServ = new MEXMECitaMedicaDet(ctx, 0, trxName);
	// MEXMECitaMedicaDet.copyValues(citaDet, citaDetServ);
	//
	// // detalle
	// citaDetServ.setM_Product_ID(serv.getM_Product_ID());
	// citaDetServ.setDescription(serv.getDescription());
	// citaDetServ.setQtyOrdered(serv.getQtyOrdered());
	// citaDetServ.setAplicar(serv.isSurtir());
	// citaDetServ.setC_UOM_ID(serv.getC_UOM_ID());
	// citaDetServ.setEXME_Diagnostico_ID(serv.getEXME_Diagnostico_ID());
	// citaDetServ.setEXME_Diagnostico2_ID(serv.getEXME_Diagnostico2_ID());
	// citaDetServ.setEXME_Diagnostico3_ID(serv.getEXME_Diagnostico3_ID());
	//
	// if (!citaDetServ.save(trxName))
	// throw new Exception("Service is not saved");
	//
	// }
	// }
	// }
	// }

	/**
	 * Inserta las indicaciones seleccionadas por el usuario (medicamentos)
	 * 
	 * @param ctx
	 * @param lstIndicaciones
	 * @param trxName
	 * @throws Exception
	 */
	// public void insertProduct(Properties ctx, List<? extends ServicioView> lstIndicaciones, String trxName) throws Exception {
	// for (int i = 0; i < lstIndicaciones.size(); i++) {
	// ServicioView indicacion = lstIndicaciones.get(i);
	//
	// MEXMECitaMedicaDet citaDet = new MEXMECitaMedicaDet(ctx, 0, trxName);
	//
	// citaDet.set(this);
	//
	// citaDet.setM_Product_ID((int) indicacion.getProdID());
	// citaDet.setDescription(indicacion.getDescripcion());
	// citaDet.setQtyOrdered(new BigDecimal(indicacion.getCantidad()));
	// citaDet.setCantTomar(new BigDecimal(indicacion.getCantTomar()));
	// citaDet.setVecesDia(indicacion.getVecesDia());
	// citaDet.setNumDias(indicacion.getNumDias());
	// citaDet.setM_Warehouse_ID( indicacion.getAlmaServ());
	// citaDet.setAplicar(indicacion.getSurtir());
	// citaDet.setC_UOM_ID((int) indicacion.getUdm());
	//
	// //Raul - Nuevos campos en la tabla temporal
	// //citaDet.setEXME_Dosis_ID(indicacion.getEXME_Dosis_ID());
	// //citaDet.setEXME_ViasAdministracion_ID(indicacion.getEXME_ViaAdministracion_ID());
	// citaDet.setTipoSurtido(indicacion.getTipoSurtidoValue());
	// citaDet.setResurtidos(new BigDecimal(indicacion.getResurtidos()));
	// citaDet.setTomadoCasa(indicacion.isTomadoCasa());
	//
	// if (!citaDet.save(trxName))
	// throw new Exception("Product is not saved");
	// }
	// }

	/**
	 * Borra el detalle de una cita en la tabla EXME_CitaMedicaDet
	 * 
	 * @return true si se borro con exito
	 *
	 *         public static boolean deleteFromVisit(Properties ctx, int citaMedID, String trxName) {
	 *         boolean retValue = true;
	 *         List<MEXMECitaMedicaDet> lstDetail = getDetail(ctx, citaMedID, trxName);
	 *         for(MEXMECitaMedicaDet detail : lstDetail) {
	 *         if(!detail.delete(true, trxName)){
	 *         retValue = false;
	 *         break;
	 *         }
	 *         }
	 *         return retValue;
	 *         }
	 */

	/**
	 * Crea el objeto para los servicios en sitio (promujer)
	 * @param appt Cita medica (requerido)
	 * @param service Servicio seleccionado (requerido)
	 * @param trxName Nombre de la transaccion con la uqe se creará el objeto
	 * @return 
	 * @throws NullPointerException si appt o service son nulos
	 */
	public static MEXMECitaMedicaDet createTodayService(MEXMECitaMedica appt, BalanceService service, String trxName){
		MEXMECitaMedicaDet det = new MEXMECitaMedicaDet(appt.getCtx(), appt, trxName);
		det.setM_Product_ID(service.getProductId());
		det.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
		det.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_TodayService);
		det.setEXME_PaqBase_Version_ID(service.getPaqBaseVerId());
		det.setEXME_Paciente_ID(service.getPatientId());//solo para promujer
		return det;
	}
	
	/**
	 * Inactiva los registros de citamedicadet FIXME revisar
	 * @param list
	 * @param appointmentId
	 * @param trxName
	 */
	public static void inactivateTemp(List<ServicioView> list, int appointmentId, String trxName) {
		if (appointmentId > 0 && !list.isEmpty()) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			final List<Object> params = new ArrayList<Object>();
			sql.append("UPDATE EXME_CitaMedicaDet SET isActive='N', Estatus='VO' ");
			sql.append("WHERE EXME_CitaMedica_ID=? ");
			params.add(appointmentId);
			sql.append(" AND EXME_CitaMedicaDet_id IN ( ");
			for (ServicioView servicioView : list) {
				if (params.size() > 1) {
					sql.append(",");
				}
				sql.append("?");
				params.add(servicioView.getCitaMedicaDetId());
			}
			sql.append(" ) ");
			Trx trx = trxName == null ? Trx.get(Trx.createTrxName("NM"), true) : null;
			try {
				int row = DB.executeUpdate(sql.toString(), params.toArray(), trxName);
				if (row >= 0) {
					Trx.commit(trx, true);
				} else {
					Trx.rollback(trx, true);
				}
			} catch (Exception e) {
				Trx.rollback(trx, true);
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				Trx.close(trx, true);
			}
		}
	}
}
