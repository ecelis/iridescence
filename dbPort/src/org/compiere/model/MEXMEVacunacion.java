package org.compiere.model;


/**
 * Copyright (c) 2008 Expert Sistemas Computacionales S.A. de C.V. Todos los
 * derechos reservados. Nombre del Archivo: VacunacionAction.java Proposito:
 * Implementacion de la funcionalidad para la aplicacion de vacunas y consulta
 * de cartilla de vacunacion. Clases: VacunacionForm.java, Vacunacion.jsp
 * <p>
 * Modificado por: $Author: taniap $
 * <p>
 * Fecha: $Date: 2009/05/22 01:28:38 $
 * <p>
 * 
 * @author Twry Perez
 * @version $Revision: 1.0.0 $
 */
@Deprecated
public class MEXMEVacunacion {

//    private static CLogger s_log = CLogger.getCLogger(MEXMECitaMedica.class);
//
//
//    /**
//     * Metodo que revisa si el paciente tiene vacunas pendientes
//     * Nota: el query esta relacionado con el repo de Medicina Preventiva
//     * @author Julio guti
//     */
//    public static boolean tieneVacunasPendientes(int pacienteid) {
//	boolean retorno = false;
//	Date fecha = new Date();
//	String fechaActual = Constantes.getSdfFecha().format(fecha);//LRHU. la funcion que se llama en el query espera recibir un String.
//	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//
//	sql
//		.append(" select pac.NOMBRE_PAC, vac.VALUE, vacdet.TIPODOSIS, pac.SEXO, ");
//	sql.append(" uom.NAME ");
//	sql.append(" from exme_vacuna vac ");
//	sql.append(" inner join exme_vacunadet vacdet ");
//	sql.append(" on ( vac.exme_vacuna_id = vacdet.exme_vacuna_id ) ");
//	sql
//		.append(" inner join c_uom uom on (uom.c_uom_id = vacdet.c_uom_id ) ");
//	sql.append(" inner join exme_paciente pac ");
//	sql.append(" on ( ");
//	sql.append(" pac.exme_paciente_id = ? and ");
//	// --agrega los pacientes,
//	// --siempre y cuando sean del sexo requerido para la vacuna
//	sql.append(" pac.sexo = vac.sexo ");
//	// --y la edad este en el intervalo de la vacuna
//	sql
//		.append(" AND( EXME_EDAD_ANOMES( pac.FECHANAC, ? )) >= vacdet.edadminima ");
//	sql
//		.append(" AND (EXME_EDAD_ANOMES( pac.FECHANAC, ? )) <= vacdet.edadmaxima ) ");
//	sql.append(" where (( ");
//	// --Quita las vacunas si no tienen sus dependencias
//	sql
//		.append(" vac.REL_VACUNA_ID  is null ");// --Si no tiene dependencia deja el registro 
//	sql
//		.append(" OR concat(concat( pac.EXME_PACIENTE_ID, vac.rel_vacuna_id ), vacdet.exme_vacunadet_id) in ");
//	sql.append(" ( ");
//	// --Si el registro tiene una vacuna relacionada tiene que tener el
//	// ultimo detalle de la vacuna relacionada
//	sql
//		.append(" select concat(concat(exme_paciente_id, EXME_VACUNA_ID), exme_vacunadet_id) ");
//	sql.append(" from exme_hist_vacuna ");
//	sql.append(" where exme_vacuna_id  = vac.rel_vacuna_id ");
//	sql.append(" and exme_paciente_id  = pac.EXME_PACIENTE_ID ");
//	sql.append(" and exme_vacunadet_id = ");
//	sql.append(" (select vacdet1.exme_vacunadet_id ");
//	sql.append(" from exme_vacunadet vacdet1 ");
//	sql.append(" where vacdet1.exme_vacuna_id = vac.rel_vacuna_id ");
//	sql.append(" and vacdet1.secuencia        = ");
//	sql.append(" ( ");
//	// --obtiene el detalle de la vacuna con la mayor secuencia
//	sql.append(" select max(vacdet2.secuencia) ");
//	sql.append(" from exme_vacunadet vacdet2 ");
//	sql.append(" where vacdet2.exme_vacuna_id = vac.rel_vacuna_id ");
//	sql.append(" ) ");
//	sql.append(" ) ");
//	sql.append(" ) ) ");
//	// --Quitas los detalles de las vacunas que no le han puesto la
//	// secuencia anterior
//	sql
//		.append("   and vacdet.secuencia = (select min(secuencia) from exme_vacunadet where exme_vacuna_id =vac.exme_vacuna_id ) ");
//	sql
//		.append(" OR (concat( concat( pac.exme_paciente_id, vac.exme_vacuna_id), vacdet.exme_vacunadet_id ) ) in ");
//	sql
//		.append(" (select concat( concat( exme_paciente_id, exme_vacuna_id) ,(select exme_vacunadet_id from exme_vacunadet where secuencia= vacdet.secuencia - 10 and exme_vacuna_id = vac.exme_vacuna_id) ) ");
//	sql.append(" from exme_hist_vacuna ");
//	sql.append(" ) ");
//	sql.append(" And ( ");
//	// ---Quita las vacunas que ya fueron aplicadas a determinado paciente
//	sql
//		.append(" concat( concat( pac.exme_paciente_id, vac.exme_vacuna_id), vacdet.exme_vacunadet_id ) ) Not in ");
//	sql
//		.append(" (select concat( concat( exme_paciente_id, exme_vacuna_id), exme_vacunadet_id ) ");
//	sql.append(" from exme_hist_vacuna ");
//	sql.append(" )  ");
//	sql.append(" and ( ");
//	// --Siempre y cuando se cumpla el tiempo de intervalo entre cada vacuna
//	sql
//		.append(" vacdet.secuencia = (select min(secuencia) from exme_vacunadet where exme_vacuna_id =vac.exme_vacuna_id ) ");
//	sql
//		.append(" or vacdet.intervalo <=  (EXME_EDAD_ANOMES( (select hst1.fechaaplica from exme_hist_vacuna hst1 where hst1.secuencia = vacdet.secuencia - 10 and pac.exme_paciente_id = hst1.exme_paciente_id ), ? ))*100 ");
//	sql
//		.append(" or vacdet.intervalo <= (EXME_EDAD_ANOMES( (select hst1.fechaaplica from exme_hist_vacuna hst1 where hst1.secuencia = vacdet.secuencia - 10 and pac.exme_paciente_id = hst1.exme_paciente_id ), ? ))*100 ");
//	sql.append(" ) ");
//	sql.append(" )order by vac.value, vacdet.secuencia ");
//
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//	try {
//	    pstmt = DB.prepareStatement(sql.toString(), null);
//	    pstmt.setInt(1, pacienteid);
//	    pstmt.setString(2, fechaActual);
//	    pstmt.setString(3, fechaActual);
//	    pstmt.setString(4, fechaActual);
//	    pstmt.setString(5, fechaActual);
//	    rs = pstmt.executeQuery();
//	    if(rs.next()){
//	    	retorno = true;
//	    }
//	} catch (Exception e) {
//	    s_log.log(Level.SEVERE, "tieneVacunasPendientes", e);
//	} finally {
//		DB.close(rs, pstmt);
//	}
//	return retorno;
//    }
}
