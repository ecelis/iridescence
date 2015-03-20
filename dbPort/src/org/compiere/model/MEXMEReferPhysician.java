package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

/**
 * 
 * @author Unknown
 * 		Modificado Pedro Mendoza 07/08/2012 
 *			Se agregaron validaciones beforeSave usadas en ApptReferral
 *			Se agregaron procesos afterSave usadas en ApptReferral
 */
public class MEXMEReferPhysician extends X_EXME_ReferPhysician{

	public MEXMEReferPhysician(Properties ctx, int EXME_ReferPhysician_ID,
			String trxName) {
		super(ctx, EXME_ReferPhysician_ID, trxName);
	}

	public MEXMEReferPhysician(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger(MEXMEEnfermeria.class);
	
	/**
	 * Borrar logicamente los referrals physician que se tengan en un Cita Medica.
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @return
	 */
	public static boolean deleteAppointmentPhysicianReferrals(Properties ctx, int EXME_CitaMedica_ID, String trxName){
		List<MEXMEReferPhysician> oldRefers = MEXMEReferPhysician.getReferPhysicianLst(ctx, EXME_CitaMedica_ID, trxName);
		for(MEXMEReferPhysician oldRefer : oldRefers){
			if (oldRefer != null) {
				oldRefer.setIsActive(false);
				if(!oldRefer.save()){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Regresa el PRIMER Physician referral activo que retorne la base de datos
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName
	 * @deprecated Usar {@link MEXMEReferPhysician#getReferPhysicianLst(Properties, int, String)}
	 * @return
	 */
	public static MEXMEReferPhysician getReferPhysician(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		MEXMEReferPhysician obj = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_REFERPHYSICIAN WHERE EXME_CitaMedica_ID = ? AND ISACTIVE = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = new MEXMEReferPhysician(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return obj;
	}
	
	/**
	 * Metodo que retorna la lista de referencias de tipo Physician para una cita
	 * 
	 * @param ctx
	 *            requerido
	 * @param EXME_CitaMedica_ID
	 *            cita medica
	 * @return Lista con objetos de tipo MEXMEReferPhysician
	 */
	public static List<MEXMEReferPhysician> getReferPhysicianLst(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		List<MEXMEReferPhysician> lst = new ArrayList<MEXMEReferPhysician>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		sb.append("SELECT * FROM EXME_ReferPhysician WHERE EXME_CitaMedica_ID = ? AND isActive = 'Y'")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		try {
			pstmt = DB.prepareStatement(sb.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MEXMEReferPhysician referPhys = new MEXMEReferPhysician(ctx, rs, trxName);
				lst.add(referPhys);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEReferPhysician at method getReferPhysicianLst ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lst;
	}
	
	
	/**
	 * Logica de guarado usada en ApptReferral
	 * @param ctx
	 * @param toSave - List<MEXMEReferPhysician> que se guardaran
	 * @param toDelete - List<MEXMEReferPhysician> que se borraran logicamente
	 * @param EXME_CitaMedica_ID -  CitaMedica actual
	 * @param status - Estatus del referral {@link X_EXME_ReferPhysician#STATUS_AD_Reference_ID}
	 * @param trxName - transaccion
	 * @return true si no ocurrio una excepcion en el proceso
	 * @throws Exception Excepcion si el proceso fallo de alguna manera
	 */
	public static boolean saveReferrals (
			Properties ctx, 
			List<MEXMEReferPhysician> toSave,
			List<MEXMEReferPhysician> toDelete,
			int  EXME_CitaMedica_ID,
			String status, 
			String trxName) throws Exception{
		
		boolean saved = true;
		if (toSave != null && !toSave.isEmpty()) {
			MEXMECitaMedica cmActual = new MEXMECitaMedica(ctx, EXME_CitaMedica_ID, null);
			for (MEXMEReferPhysician referPhy : toSave) {
				referPhy.setStatus(status);
				
				if (!referPhy.save(trxName)) {
					return false;
				}
				
				// Crear Cita Nueva
				if (MEXMEReferPhysician.STATUS_Physician.equals(referPhy.getStatus()) && referPhy.getAD_Org_Dest_ID() > 0 && referPhy.getEXME_Medico_ID() > 0) {
					MEXMEMedico phys = new MEXMEMedico(ctx, referPhy.getEXME_Medico_ID(), null);
					MEXMECitaMedica cmNew = null;
					if (phys.getEspecialidades().isEmpty()) {
						saved = false;
						throw new Exception(
								new MEXMEMedico(ctx, phys.getEXME_Medico_ID(), null).getNombre_Med() 
								+ " - " 
								+ Utilerias.getMsg(ctx, "error.cirugiaEspecialidad"));
					} else {
						cmNew = new MEXMECitaMedica(ctx, 0, null);
						cmNew.setAD_Org_ID(referPhy.getAD_Org_Dest_ID());
						cmNew.setEXME_Paciente_ID(cmActual.getEXME_Paciente_ID());
						cmNew.setEXME_Medico_ID(referPhy.getEXME_Medico_ID());
						cmNew.setFechaHrCita(referPhy.getFecha());
						cmNew.setName(Utilerias.getMsg(ctx, "reporte.refPhysAppt"));
						cmNew.setDuracion(15);
						cmNew.setConfirmada(false);
						cmNew.setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);
						cmNew.setIsInfoSent(false);
						cmNew.setProcessing(false);
						cmNew.setUtilidad(false);
						cmNew.setEXME_Especialidad_ID(phys.getEspecialidades().get(0).getEXME_Especialidad_ID());
						cmNew.setCitaDe(MEXMECitaMedica.CITADE_PreAppointment);

						if (!cmNew.save(trxName)) {
							saved = false;
							throw new Exception("error.citas.noInsertCita");
						} else {
							referPhy.setEXME_CitaMedDest_ID(cmNew.getEXME_CitaMedica_ID());
						}
					}
				}
			}
		}
		// Se desactivan las referencias borradas
		if (toDelete != null && !toDelete.isEmpty()) {
			for (MEXMEReferPhysician deletedReferPhy : toDelete) {
				if (deletedReferPhy.getEXME_ReferPhysician_ID() > 0) {
					deletedReferPhy.setStatus(MEXMEReferPhysician.STATUS_Inactive);
					deletedReferPhy.setIsActive(false);

					if (!deletedReferPhy.save(trxName)) {
						saved = false;
					}
				}
			}
		}
		return saved;
	}
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(isActive()){
			/**
			 * Solo puede haber un tipo de referral activo por cita medica
			 */
			return MEXMEReferInpatient.deleteAppointmentHospitalReferrals(getCtx(), getEXME_CitaMedica_ID(), get_TrxName());
		}
		return true;
	}
	
}