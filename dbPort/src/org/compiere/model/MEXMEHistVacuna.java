package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Copyright (c) 2008 Expert Sistemas Computacionales S.A. de C.V.
 * Todos los derechos  reservados.
 * Nombre del Archivo:  MEXMEHistVacuna.java 
 * Proposito: Implementacion de la funcionalidad para la aplicacion de vacunas 
 *            y consulta de cartilla de vacunacion.
 * Clases: VacunacionAction.java
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2009/05/22 01:28:38 $ <p>
 *
 * @author Twry Perez
 * @version $Revision: 1.0.0 $
 */

public class MEXMEHistVacuna extends X_EXME_Hist_Vacuna {
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEHistVacuna.class);
	
	private MEXMEVacuna vacuna = null;
	private MEXMEVacunaDet vacunaDet = null;
	
	public MEXMEVacuna getVacuna() {
		if( vacuna == null){
			vacuna = new MEXMEVacuna(getCtx(), getEXME_Vacuna_ID(), get_TrxName());
		}
		return vacuna;
	}

	public void setVacuna(MEXMEVacuna vacuna) {
		this.vacuna = vacuna;
	}

	public MEXMEVacunaDet getVacunaDet() {
		if(vacunaDet == null){
			vacunaDet = new MEXMEVacunaDet(getCtx(), getEXME_VacunaDet_ID(), get_TrxName());
		}
		return vacunaDet;
	}

	public void setVacunaDet(MEXMEVacunaDet vacunaDet) {
		this.vacunaDet = vacunaDet;
	}

	/**
	 * Constructor ID
	 * @param ctx
	 * @param EXME_Hist_Vacuna_ID
	 * @param trxName
	 */
	public MEXMEHistVacuna(Properties ctx, int EXME_Hist_Vacuna_ID, String trxName) {
		super(ctx, EXME_Hist_Vacuna_ID, trxName);
	}

	/**
	 * Constructor ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEHistVacuna(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
//	public void get(ResultSet rs) 
//	throws SQLException
//	{
//		setEXME_Hist_Vacuna_ID(rs.getInt("EXME_Hist_Vacuna_ID"));
//		setAD_Client_ID(rs.getInt("AD_Client_ID"));
//		setAD_Org_ID(rs.getInt("AD_Org_ID"));
//		setIsActive(true);
//		setEXME_Paciente_ID(rs.getInt("EXME_Paciente_ID"));
//	    setEXME_Vacuna_ID(rs.getInt("EXME_Vacuna_ID"));
//		setEXME_VacunaDet_ID(rs.getInt("EXME_VacunaDet_ID"));
//		setSecuencia(rs.getBigDecimal("Secuencia"));
//		setFechaAplica(rs.getTimestamp("FechaAplica"));
//		setIntervalo(rs.getBigDecimal("Intervalo"));
//		setC_UOM_Inter_ID(rs.getInt("C_UOM_Inter_ID"));
//		setEdad(rs.getBigDecimal("Edad"));
//		setPeso(rs.getBigDecimal("Peso"));
//		setTalla(rs.getBigDecimal("Talla"));
//		setMasaCorporal(rs.getBigDecimal("MasaCorporal"));
//		setVia(rs.getString("Via"));
//		setCantidad(rs.getBigDecimal("Cantidad"));
//		setC_UOM_ID(rs.getInt("UOMHist"));
//		setEXME_ActPaciente_ID(rs.getInt("EXME_ActPaciente_ID"));
//		setC_Campaign_ID(rs.getInt("C_Campaign_ID"));
//		setLot(rs.getString("Lot"));
//		setSerNo(rs.getString("SerNo"));
//		setFechaRegistro(rs.getTimestamp("FechaRegistro"));
//		setC_LocationReg_ID(rs.getInt("C_LocationReg_ID"));
//	}

//	public void get(MEXMEVacunaDet vacunaDet, MEXMEVacuna vacuna , X_EXME_Paciente paciente) 
//	throws SQLException
//	{
//		setEXME_Paciente_ID(paciente.getEXME_Paciente_ID());
//	    setEXME_Vacuna_ID(vacuna.getEXME_Vacuna_ID());
//		setEXME_VacunaDet_ID(vacunaDet.getEXME_VacunaDet_ID());
//		setSecuencia(new BigDecimal(vacunaDet.getSecuencia()));
//		setFechaAplica(DB.getTimestampForOrg(Env.getCtx()));
//		setIntervalo(vacunaDet.getIntervalo());
//		setC_UOM_Inter_ID(vacunaDet.getC_UOM_ID());
//		setVia(vacuna.getVia());
//		setCantidad(vacuna.getCantidad());
//		setC_UOM_ID(vacuna.getC_UOM_ID());
//		setFechaRegistro(paciente.getFechaRegistro());
//		setC_LocationReg_ID(paciente.getC_LocationReg_ID());
//	}
	
	/**
	 *  Devuelve un objeto tipo lista con las vacunas
	 *  de la cartilla con sus diversas aplicaciones
	 *  con la finalidad de formar la cartilla de vacunacion
	 *  junto con las fechas de aplicacion de acuerdo al paciente
	 *
	 * @param  ctx               Contexto
	 * @param  EXME_Paciente_ID  Identificador del paciente EXME_Paciente.EXME_Paciente_ID
	 * @return List<MEXMEVacuna> Lista de vacunas de la cartilla
	 * @throws Exception         en caso de ocurrir un error en la consulta
	 *
	public static List<MEXMEVacuna> vacunasCartillaPaciente(
			Properties ctx, int EXME_Paciente_ID, boolean soloCartilla, 
			double edad, int AD_Reference_ID )
			throws Exception {

		//Lista de vacunas
		List<MEXMEVacuna> listaVacunas = new ArrayList<MEXMEVacuna>();
		
		if(ctx==null)
			return listaVacunas;
		
		//Objeto vacuna creado por el select
		MEXMEVacuna vacuna = null;
		
		//Objeto creado con el detalle de la vacuna
		MEXMEVacunaDet vacunaDet = null;
		
		//Objeto creado con los registro de vacunas aplicadas
		MEXMEHistVacuna histVacuna = null;
		
		//Consulta
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT v.EXME_Vacuna_ID , v.IncluyeCartilla ")
		.append(", v.C_UOM_ID AS UOMVacuna ")  .append(", v.Cantidad ")
		.append(", v.Description ")	           .append(", v.EXME_Diagnostico_ID ")
		.append(", v.EXME_Diagnostico2_ID ")   .append(", v.EXME_Diagnostico3_ID ")
		.append(", v.EXME_Diagnostico4_ID ")   .append(", v.EXME_Diagnostico5_ID ")
		.append(", v.M_Product_ID ")           .append(", v.Rel_Vacuna_ID ")
		.append(", v.Sexo ")                   .append(", v.Value ")
		.append(", v.Via ")                    .append(", v.AD_Client_ID ")
		.append(", v.AD_Org_ID ")  		       .append(", vd.EXME_VacunaDet_ID ")
		.append(", vd.C_UOM_ID AS UOMDetalle ").append(", vd.EdadMaxima ")
		.append(", vd.EdadMinima ")     	   .append(", vd.Intervalo ")
		.append(", vd.Secuencia ")    		   //.append(", vd.TipoDosis ")
		.append(", vd.TipoDosis , rl.name AS DosisIngles ")
		.append(", NVL(rlt.name,' ') AS DosisIdioma ")
		.append(", h.EXME_Paciente_ID ")       .append(", h.FechaAplica  ")                                           
		.append(", h.C_UOM_Inter_ID  ")        .append(", h.Edad  ")                          
		.append(", h.Peso ")                   .append(", h.Talla ")                          
		.append(", h.MasaCorporal ")           .append(", h.C_UOM_ID AS UOMHist ")                       
		.append(", h.EXME_ActPaciente_ID ")    .append(", h.C_Campaign_ID ")                                      
		.append(", h.Lot ")                    .append(", h.SerNo ")                                             
		.append(", h.FechaRegistro ")          .append(", h.C_LocationReg_ID ")
		.append(", h.EXME_Hist_Vacuna_ID ")
		.append(", NVL(d1.Name,' ') || ', ' || NVL(d2.Name,' ') || ', ' || NVL(d3.Name,' ') || ', ' || NVL(d4.Name,' ') || ', ' || NVL(d5.Name,' ') AS Enfermedades ")
		.append(" FROM EXME_Vacuna v ")
		.append(" INNER JOIN EXME_VacunaDet vd ON v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive = 'Y' ")
		.append(" LEFT JOIN EXME_Hist_Vacuna h ON vd.EXME_VacunaDet_ID  = h.EXME_VacunaDet_ID AND h.IsActive = 'Y' AND h.EXME_Paciente_ID = ? ")
		.append(" LEFT  JOIN EXME_Diagnostico d1 ON v.EXME_Diagnostico_ID  = d1.EXME_Diagnostico_ID AND d1.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d2 ON v.EXME_Diagnostico2_ID = d2.EXME_Diagnostico_ID AND d2.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d3 ON v.EXME_Diagnostico3_ID = d3.EXME_Diagnostico_ID AND d3.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d4 ON v.EXME_Diagnostico4_ID = d4.EXME_Diagnostico_ID AND d4.IsActive = 'Y' ")
		.append(" LEFT  JOIN EXME_Diagnostico d5 ON v.EXME_Diagnostico5_ID = d5.EXME_Diagnostico_ID AND d5.IsActive = 'Y' ")
		
		.append(" INNER JOIN AD_Ref_List      rl ON rl.Value = vd.TipoDosis  AND  AD_Reference_ID = ? ")
		.append(" LEFT  JOIN AD_Ref_List_Trl rlt ON rl.AD_Ref_List_ID = rlt.AD_Ref_List_ID AND rlt.AD_Language = ? ")
		
		.append(" WHERE v.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Vacuna", "v"));
		
		if (soloCartilla)
		{
			sql.append(" AND v.IncluyeCartilla = 'Y'  ");
		}
		else
		{
			sql.append(" AND v.IncluyeCartilla = 'N'  ");
		}
		
		sql.append(" ORDER BY v.EXME_Vacuna_ID, vd.Secuencia , vd.EXME_VacunaDet_ID , h.FechaAplica ");

		if (WebEnv.DEBUG) {
			s_log.log(Level.FINE, "SQL : " + sql.toString());
		}

        String AD_Language = Env.getAD_Language(ctx);
        //boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, AD_Reference_ID);
			pstmt.setString(3, AD_Language);
			rs = pstmt.executeQuery();

			//Vacuna
			int vacunaOld = 0;
			int vacunaNew = 0;
			
			//Detalle de vacuna
			int vacunaDetOld = 0;
			int vacunaDetNew = 0;
			double intervalo = 0; //LRHU.
			Calendar actual = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			
			while (rs.next()) {

				//Vacuna iterada
				vacunaNew = rs.getInt("EXME_Vacuna_ID");
				vacunaDetNew = rs.getInt("EXME_VacunaDet_ID");
				
				//Creamos el objeto de vacuna sin que se repitan
				if ( vacunaOld != vacunaNew) 
				{
					vacuna = new MEXMEVacuna(ctx, 0, null);
					vacuna.get(rs);

					//Verificar que el detalle se agregue correctamente sobre la lista
					listaVacunas.add(vacuna);
					
					//Actualizamos la vacuna
					vacunaOld = rs.getInt("EXME_Vacuna_ID");
					
					vacuna.setSecuencias(new ArrayList<MEXMEVacunaDet>());
				}

				if(vacuna==null)
				{
					throw new Exception("Objeto vacio. No se logro construir la vacuna ");
				}
				
				//Creamos el objeto del detalle (secuencia) de la vacuna sin que se repitan
				if (vacunaDetOld != vacunaDetNew) 
				{
					vacunaDet = new MEXMEVacunaDet(ctx, 0, null);
					vacunaDet.get(rs);

					//Agregamos la secuencia a la lista
					if (vacuna.getSecuencias() != null )
						vacuna.getSecuencias().add(vacunaDet);
					
					//Actualizamos la vacuna
					vacunaDetOld = rs.getInt("EXME_VacunaDet_ID");
					
					vacunaDet.setHistorial(new ArrayList<MEXMEHistVacuna>());
				}

				if(vacunaDet==null)
				{
					throw new Exception("Objeto vacio. No se logro construir el detalle de la vacuna ");
				}
				
				//Obtenemos el hist�rico de la aplicacion de la vacuna en caso de existir
				if (rs.getInt("EXME_Hist_Vacuna_ID")>0)
				{
					histVacuna = new MEXMEHistVacuna(ctx, 0, null);
					histVacuna.get(rs);

					//Agregamos la secuencia a la lista
					if (vacunaDet.getHistorial()!=null)
					{
						vacunaDet.getHistorial().add(histVacuna);
					}
					
					//Sabemos la ultima fecha de aplicacion de la vacuna
					if (histVacuna!=null && histVacuna.getFechaAplica()!=null)
					{
						vacunaDet.setFechaAplicacion(Constantes.getSdfFecha().format(histVacuna.getFechaAplica()));
						vacunaDet.setEstatus(2); 
						//ini LRHU.
						cal.setTimeInMillis(histVacuna.getFechaAplica().getTime());
						actual.setTime(DB.getDateForOrg(ctx));
						intervalo = vacunaDet.getIntervalo().doubleValue();
						if(intervalo > 0){ // Verificamos si tiene intervalo y si es tiempo de volver a aplicarle la vacuna, si es asi cambiamos el estatus a 1 (vacuna sugerida)
							MUOM uom = new MUOM(ctx, vacunaDet.getC_UOM_ID(), null);
							int anios = 0;
							int meses = 0;;
							int dias = 0;
							//verificamos si el intervalo es en meses y dias o en a�os y meses.
							if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_MONTH)){
								meses = (int)Math.floor(intervalo); //Obtenemos meses
								dias = ((int)(intervalo - meses))*100;
								cal.add(Calendar.MONTH, meses);
								cal.add(Calendar.DATE, dias);
							}
							if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_YEAR)){
								anios = (int)(Math.floor(intervalo)); //obtenemos los a�os
								meses = (int)((intervalo - anios)*100); //Obtenemos meses
								cal.add(Calendar.YEAR, anios);
								cal.add(Calendar.MONTH, meses);
							}
							
							if(actual.getTime().after(cal.getTime())){//Si es tiempo de que se aplique la vacuna ponemos la vacuna como sugerida para aplicar.
								vacunaDet.setEstatus(1);
								vacunaDet.setFechaAplicacion(Constantes.getSdfFecha().format(DB.getTimestampForOrg(ctx)));
								 //si la edad del paciente no esta dentro del rango de la edad en que se puede aplicar la vacuna, el estatus de la vacuna es Aplicada.
								if(vacunaDet.getEdadMaxima()!= null && vacunaDet.getEdadMinima()!= null){									
									if(!(vacunaDet.getEdadMaxima().compareTo(Env.ZERO) >0  
											&& edad >= vacunaDet.getEdadMinima().doubleValue() 
											&& edad <= vacunaDet.getEdadMaxima().doubleValue())){
										vacunaDet.setEstatus(2);
									}
								}
							}
						} //Fin LRHU.
					}
				}
				else
				//Si no existe hist�rico es que nunca se ha aplicado
				{
					vacunaDet.setEstatus(0);
					
					//Ver que vacunas se pueden planear de acuerdo a la edad del paciente
					//y que no hayan sido aplicadas
					if ( vacunaDet.getEdadMaxima()!= null && vacunaDet.getEdadMinima()!= null
							&& vacunaDet.getEdadMaxima().compareTo(Env.ZERO) >0 
							&& edad >= vacunaDet.getEdadMinima().doubleValue() 
							&& edad <= vacunaDet.getEdadMaxima().doubleValue()){
						vacunaDet.setEstatus(1); vacunaDet.setFechaAplicacion(Constantes.getSdfFecha().format(DB.getTimestampForOrg(ctx)));
					}
				}
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return listaVacunas;
	}*/
	
//	public static List<MEXMEHistVacuna> get(Properties ctx, int EXME_CtaPac_ID, String trxName) {
//		StringBuilder st = new StringBuilder(
//				"SELECT * FROM EXME_IngresoPresc presc ")
//				.append(
//						"INNER JOIN M_Product prod on prod.M_Product_ID = presc.M_Product_ID ")
//				.append("WHERE  presc.fechaFin >= to_date(?, 'dd/MM/yyyy') ")
//				.append("AND presc.isActive = 'Y' ").append(
//						"AND EXME_CtaPac_ID = ? ").append(
//						"AND prod.EXME_ProductFam_ID = ? ");
//		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
//				.toString(), "EXME_Hist_Vacuna", "vacuna"));
//
//		st.append(" order by presc.fechaInicio ");
//
//		List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setInt(1, EXME_CtaPac_ID);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				lista.add(new MEXMEHistVacuna(ctx, rs, trxName));
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return lista;
//	}
	/**
	 * Obtiene el historico de vacunas para un paciente
	 * de acuerdo a la edad y sexo
	 * @param ctx
	 * @param EXME_Vacuna_ID
	 * @param EXME_Paciente_ID
	 * @param edad
	 * @return
	 * lhernandez.
	 * @deprecated not used
	 */
	public static List<MEXMEHistVacuna> getVacunas(Properties ctx, int EXME_Vacuna_ID, int EXME_Paciente_ID, double edad, String whereClause){
		List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT * ")
		.append("FROM EXME_Vacuna v  ")
		.append("INNER JOIN EXME_VacunaDet vd ON v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive = 'Y' ")
		.append("LEFT JOIN EXME_Hist_Vacuna h ON vd.EXME_VacunaDet_ID  = h.EXME_VacunaDet_ID AND h.IsActive = 'Y' AND h.EXME_Paciente_ID = ? ")
		.append("LEFT JOIN M_Product p on v.M_Product_ID = p.M_Product_ID ")
		.append("WHERE ")
		.append("v.exme_vacuna_id = ? ")
		.append("AND ? BETWEEN vd.edadMinima AND vd.EdadMaxima ")
		.append(whereClause != null ? whereClause : "")
		.append("ORDER BY v.exme_vacuna_id, vd.secuencia, h.fechaaplica desc ");

		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setInt(2, EXME_Vacuna_ID);
			pstmt.setDouble(3, edad);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEHistVacuna hist = null;
				if(rs.getInt("EXME_Hist_Vacuna_ID") <= 0){
					hist = new MEXMEHistVacuna(ctx,rs,null);
				}else{
					hist= new MEXMEHistVacuna(ctx, rs.getInt("EXME_Hist_Vacuna_ID"), null);
					//hist.setCantidad(hist.getCantidad());
					if(hist.getCantidad().equals("999")){
						hist.setC_UOM_ID(0);
					}
//					MEXMEVacunaDet vDet = new MEXMEVacunaDet(ctx, hist.getEXME_VacunaDet_ID(), null);
//					Calendar cal = Calendar.getInstance();
//					Calendar actual = Calendar.getInstance();
//					@SuppressWarnings("unused")
//					double intervalo = new Double(0);
//					cal.setTimeInMillis(hist.getFechaAplica().getTime());
//					actual.setTime(DB.getDateForOrg(ctx));
//					double intervalo = vDet.getIntervalo().doubleValue();
					/*if(intervalo > 0){ // Verificamos si tiene intervalo y si es tiempo de volver a aplicarle la vacuna, si es asi cambiamos el estatus a 1 (vacuna sugerida)
						MUOM uom = new MUOM(ctx, vDet.getC_UOM_ID(), null);
						int anios = 0;
						int meses = 0;;
						int dias = 0;
						//verificamos si el intervalo es en meses y dias o en a�os y meses.
						if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_MONTH)){
							meses = (int)Math.floor(intervalo); //Obtenemos meses
							dias = ((int)(intervalo - meses))*100;
							cal.add(Calendar.MONTH, meses);
							cal.add(Calendar.DATE, dias);
						}
						if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_YEAR)){
							anios = (int)(Math.floor(intervalo)); //obtenemos los a�os
							meses = (int)((intervalo - anios)*100); //Obtenemos meses
							cal.add(Calendar.YEAR, anios);
							cal.add(Calendar.MONTH, meses);
						}
						
						if(actual.getTime().after(cal.getTime())){//Si es tiempo de que se aplique la vacuna ponemos la vacuna como sugerida para aplicar.
							MEXMEHistVacuna h = new MEXMEHistVacuna(ctx,0,null);
							h.setEXME_Vacuna_ID(vDet.getEXME_Vacuna_ID());
							h.setEXME_VacunaDet_ID(vDet.getEXME_VacunaDet_ID());
							lista.add(h);
						}
					}*/
						
				}
				lista.add(hist);
			}
		}catch (Exception ex){
			s_log.log(Level.SEVERE, "getVacunas(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtiene una lista con las vacunas aplicadas para el paciente activas
	 * y que no hayan sido rechazadas
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxname
	 * @return Lista de MEXMEHistVacuna
	 * @author raul
	 */
	public static MEXMEHistVacuna getLastVaccine(Properties ctx, int EXME_Patient_ID, String trxName){
		MEXMEHistVacuna vaccine = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT EXME_HIST_VACUNA.EXME_HIST_VACUNA_ID FROM EXME_HIST_VACUNA ") 
				.append(" WHERE EXME_HIST_VACUNA.EXME_PACIENTE_ID = ? ") 
				.append(" AND EXME_HIST_VACUNA.ISACTIVE = 'Y' ") 
				.append(" AND EXME_HIST_VACUNA.REJECTED = 'N' ")
				.append(" ORDER BY EXME_HIST_VACUNA.FECHAAPLICA DESC ");
		try{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				vaccine = new MEXMEHistVacuna(ctx,rs.getInt(1),trxName);

			}
		}catch (Exception ex){
			s_log.log(Level.SEVERE, "getVacunas(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return vaccine;
	}	
	
	/**
	 * Obtiene una lista con las vacunas aplicadas para el paciente activas
	 * y que no hayan sido rechazadas
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxname
	 * @return Lista de MEXMEHistVacuna
	 * @author raul
	 *
	public static List<List<MEXMEHistVacuna>> getVaccines(Properties ctx, int EXME_Patient_ID, String trxName){
		return getVaccines(ctx, EXME_Patient_ID, 0, trxName, false);
	}
	
	/**
	 * Obtiene una lista con las vacunas aplicadas para el paciente activas
	 * y aplicadas por el medico
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxname
	 * @param IncludeRejectedOnes true para incluír las vacunas rechazadas
	 * @return Lista de MEXMEHistVacuna
	 * @author raul
	 *
	public static List<List<MEXMEHistVacuna>> getVaccines(Properties ctx, 
			int EXME_Patient_ID, 
			int EXME_Medico_ID, 
			String trxName, 
			boolean IncludeRejectedOnes){
		return getVaccines(ctx, EXME_Patient_ID, EXME_Medico_ID, trxName, IncludeRejectedOnes, -1, null, null);
	}
		
	/**
	 * Obtiene una lista con las vacunas aplicadas para el paciente activas
	 * y aplicadas por el medico
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param trxname
	 * @param excludeRejected true para incluír las vacunas rechazadas
	 * @return Lista de MEXMEHistVacuna
	 *
	public static List<List<MEXMEHistVacuna>> getVaccines(Properties ctx, 
			int EXME_Patient_ID, 
			int EXME_Medico_ID, 
			String trxName, 
			boolean IncludeRejectedOnes, 
			int AD_Org_ID, 
			StringBuilder join, 
			StringBuilder where){

		final List<List<MEXMEHistVacuna>> lista = new ArrayList<List<MEXMEHistVacuna>>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_HIST_VACUNA.* FROM EXME_HIST_VACUNA ");
		sql.append(" WHERE EXME_HIST_VACUNA.EXME_PACIENTE_ID=? ");
		sql.append(" AND EXME_HIST_VACUNA.ISACTIVE='Y' ");

		if (IncludeRejectedOnes) {
			sql.append(" AND EXME_HIST_VACUNA.REJECTED='N' ");
		}
		if (EXME_Medico_ID > 0) {
			sql.append(" AND EXME_HIST_VACUNA.EXME_MEDICO_ID=? ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_HIST_VACUNA.EXME_VACUNA_ID, EXME_HIST_VACUNA.EXME_HIST_VACUNA_ID ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			if (EXME_Medico_ID > 0) {
				pstmt.setInt(2, EXME_Medico_ID);
			}
			rs = pstmt.executeQuery();

			Integer vacunaId = null;
			List<MEXMEHistVacuna> lstHistVaccine = new ArrayList<MEXMEHistVacuna>();

			while (rs.next()) {
				final MEXMEHistVacuna hist = new MEXMEHistVacuna(ctx, rs, trxName);
				if (vacunaId == null) {
					vacunaId = hist.getEXME_Vacuna_ID();
				} else if (vacunaId != hist.getEXME_Vacuna_ID()) {
					vacunaId = hist.getEXME_Vacuna_ID();
					lista.add(lstHistVaccine);
					lstHistVaccine = new ArrayList<MEXMEHistVacuna>();
				}
				lstHistVaccine.add(hist);
			}
			if (!lstHistVaccine.isEmpty()) {
				lista.add(lstHistVaccine);
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}*/
	
	/**
	 * Regresa el nombre del recurso segun la lista de referencia
	 * con el lenguaje del usuario
	 * @return
	 */
	public String getSourceName(){
		return MRefList.getListName(getCtx(), SOURCE_AD_Reference_ID, getSource());
	}
	
	public String getSiteName() {
		return MRefList.getListName(getCtx(), SITE_AD_Reference_ID, getSite());
	}
	
	public String getRejectedReason() {
		return MRefList.getListName(getCtx(), MOTIVORECHAZO_AD_Reference_ID, getMotivoRechazo());
	}
	
	public String getTable_Name() {
		return MEXMEHistVacuna.Table_Name;
	}
	
	public static final String WEIGHT = Constantes.PESO;
	public static final String HEIGHT = Constantes.TALLA;
	
	/**
	 * Guardar signos vitales especificos.
	 * @param trxName
	 * @return
	 * @throws Exception 
	 *
	public static List<MEXMESignoVitalDet> saveVitalSigns(Properties ctx, int patientID, BigDecimal weight, BigDecimal height,
			String trxName) throws Exception {

		List<MEXMESignoVital> list1 = MEXMESignoVital.getSignosVitales(ctx, null, true,
				MEXMESignoVital.WINDOWTYPE_VitalSigns);
		
		List<MEXMESignoVitalDet> detail = new ArrayList<MEXMESignoVitalDet>();
		Interpreter inter = new Interpreter();
		int folio = MEXMESignoVitalDet.nextFolio(trxName, Env.getAD_Client_ID(ctx));
		for (MEXMESignoVital signRef : list1) {
			if (signRef.getValue().equals(Constantes.PESO) //
					|| signRef.getValue().equals(Constantes.TALLA) //
					|| signRef.getValue().equals(Constantes.MASA) //
			) {
				final MEXMESignoVitalDet det = new MEXMESignoVitalDet(ctx, 0, trxName);
				MEXMESignoVitalDet.copyValues(signRef, det);
				det.setEXME_SignoVital_ID(signRef.getEXME_SignoVital_ID());
				det.setEXME_Paciente_ID(patientID);
				det.setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
				det.setAD_User_ID(Env.getAD_User_ID(ctx));
				det.setFolio(folio);

				final MEXMESignoVital sign = det.getSignoVital();
				final MUOM uomTo = det.getUomTo(false);
				String formula = null;
				BigDecimal valor = Env.ZERO;

				try {
					if (sign.getValue().equals(Constantes.PESO)) {
						valor = weight;
					} else if (sign.getValue().equals(Constantes.TALLA)) {
						valor = height;
					} else if (sign.getValue().equals(Constantes.MASA)) {
						final MEXMEFormulaSigVital mFormula = sign.getFormulaSigVital();
						if (mFormula != null && mFormula.isActive()) {
							formula = mFormula.getFormula();
						}
					}
					if (valor.compareTo(Env.ZERO) > 0) {
						det.setValor(uomTo == null ? valor : VitalSignsUtils.getToDB(ctx, uomTo.get_ID(), valor, true));
					}
					inter.set(sign.getInterpreterKey(), (det.getValor() == null ? valor : det.getValor()).doubleValue());
					
					if (StringUtils.isNotEmpty(formula)) {
						inter.eval(sign.getInterpreterKey()+"="+formula);
						Object valorStr = inter.get(sign.getInterpreterKey());
						det.setValor(valorStr == null ? Env.ZERO : new BigDecimal(valorStr.toString()).setScale(4,
								BigDecimal.ROUND_HALF_DOWN));
					}
					// Validar que no este fuera del rango
					if (det.getValor().compareTo(sign.getValorMax()) > 0 || det.getValor().compareTo(sign.getValorMin()) < 0) {
						throw new ExceptionPar(Utilerias.getMessage(ctx, null, "error.sigVit.fueraRango"),det);
					}
					if (det.save()) {
						detail.add(det);
					}
				} catch (EvalError e) {
					s_log.finer("evaluate" + e.getMessage());// FINER: segun WVitalSigns
				}
			}
		}
		return detail;
	}*/

	/**
	 * Crea la registro en EXME_ActPaciente y EXME_ActPacienteIndH
	 * para el registro de la vacuna (producto) aplicada
	 * @param trxName
	 * @return
	 *
	public static MEXMEActPacienteIndH createActPaciente(Properties ctx, int patientID, int specialtyID, MEXMEMedico med,
			String trxName) {
		MEXMEActPacienteIndH hdr = null;
		try {

			MEXMEActPaciente actPac = new MEXMEActPaciente(ctx, 0, trxName);
			actPac.setEXME_Paciente_ID(patientID);
			actPac.setEXME_Especialidad_ID(specialtyID);
			actPac.setTipoArea(Env.getTipoArea(ctx));
			actPac.setFecha(DB.getTimestampForOrg(ctx));

			actPac.setName(Msg.translate(Env.getAD_Language(ctx), I_EXME_Vacuna.COLUMNNAME_EXME_Vacuna_ID));
			if (!actPac.save(trxName)) {
				return null;
			}

			hdr = actPac.createActPacienteIndH();
			hdr.setDescription(MTranslation.getTable_Name(ctx, MEXMEVacuna.Table_Name, Env.getAD_Language(ctx)));

			// MEDICO
			if (med != null) {
				hdr.setEXME_Medico_ID(med.getEXME_Medico_ID());
				hdr.setMedicoNombre(med.getNombre_Med() == null ? (med.getName() + " " + med.getApellido1() + " " + med
						.getApellido2()) : med.getNombre_Med());
			}

			if (!hdr.save(trxName)) {
				return null;
			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "createActPac ", ex);
			return null;
		}
		return hdr;
	}*/
	
	/**
	 * Crea la registro en EXME_ActPaciente y EXME_ActPacienteIndH
	 * para el registro de la vacuna (producto) aplicada
	 * @param trxName
	 * @return
	 *
	public static MEXMEActPaciente createActPaciente(final Properties ctx, final int patientID, 
			final int specialtyID, final MEXMEMedico med, final MEXMEVacuna vaccine, final String trxName) {
		MEXMEActPaciente actPac = null;
		try {

			actPac = new MEXMEActPaciente(ctx, 0, trxName);
			actPac.setEXME_Paciente_ID(patientID);
			actPac.setEXME_Especialidad_ID(specialtyID);
			
			String tipoArea = Env.getTipoArea(ctx);
			if(StringUtils.isEmpty(tipoArea)){
				tipoArea = X_EXME_Area.TIPOAREA_Other;// Correcto ?
			}
			
			actPac.setTipoArea(tipoArea);
			actPac.setFecha(DB.getTimestampForOrg(ctx));

			actPac.setName(Msg.translate(Env.getAD_Language(ctx), I_EXME_Vacuna.COLUMNNAME_EXME_Vacuna_ID));
			if (!actPac.save(trxName)) {
				return null;
			}

		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "createActPac ", ex);
			return null;
		}
		return actPac;
	}*/
	
	/**
	 * Inout
	 * @param ctx
	 * @param hdr
	 * @param trxName
	 * @return
	 *
	public static MEXMEInOut createInOut(Properties ctx, MEXMEActPacienteIndH hdr, String trxName ){
		// crear inout
		MEXMEInOut m_InOut = hdr.createInOut();
		m_InOut.setDescription(Msg.translate(Env.getAD_Language(ctx), I_EXME_Vacuna.COLUMNNAME_EXME_Vacuna_ID));
		if (!m_InOut.save(trxName)) {
			return null;
		}
		return m_InOut;
	}*/
	
	
	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		
		MEXMEHistVacuna vacc = MEXMEHistVacuna.getLastVaccine(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		
		if(vacc != null){
			setSeqNo(vacc.getSeqNo()+1);
		}else{
			setSeqNo(1);
			
		}
		
		return true;
	}	//	beforeSave
	
	
	/**
	 * Obtiene las vacunas pendientes de aplicacion segun la edad del paciente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param edad
	 * @param whereClause
	 * @return
	 * @deprecated not used
	 */
	public static List<MEXMEHistVacuna> getPendingVaccines(Properties ctx, int EXME_Paciente_ID, String sexo, double edad, String whereClause){
		List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// solo considerar para el filtro F y M
		boolean sexoBln = false;
		if(MEXMEVacuna.SEXO_Female.equals(sexo) || MEXMEVacuna.SEXO_Male.equals(sexo)){
			sexoBln = true;
		}
		
		sql.append("SELECT * ");
		sql.append("FROM EXME_Vacuna v  ");
		sql.append("INNER JOIN EXME_VacunaDet vd ON v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive = 'Y' ");
		sql.append("WHERE ? BETWEEN vd.edadMinima AND vd.EdadMaxima ");
		
		if(sexoBln){
			sql.append(" AND v.sexo in (");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Ambiguous)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_NotApplicable)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Other)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Unassigned)).append(",");
			sql.append("?) ");
		}
		
		sql.append(whereClause != null ? whereClause : "");
		sql.append("ORDER BY v.exme_vacuna_id, vd.secuencia ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			 int i = 0;
			 pstmt.setDouble(++i, edad);
			 if(sexoBln){
				 pstmt.setString(++i, sexo.trim());
			 }
			 
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				MEXMEHistVacuna hist = new MEXMEHistVacuna(ctx, 0, null);
				hist.setEXME_Vacuna_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_EXME_Vacuna_ID));
				hist.setEXME_VacunaDet_ID(rs.getInt(MEXMEVacunaDet.COLUMNNAME_EXME_VacunaDet_ID));
				hist.setM_Product_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_M_Product_ID));
				hist.setC_UOM_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_C_UOM_ID));
				hist.setCantidad(rs.getBigDecimal(MEXMEVacuna.COLUMNNAME_Cantidad));
				
				/*Calendar cal = Calendar.getInstance();
				Calendar actual = Calendar.getInstance();
				double intervalo = new Double(0);
				// fecha de la ultima aplicacion de esa vacuna
				// fecha aplicacion de vacuna (ultima)
				actual.setTime(new Date());
				intervalo = rs.getDouble(MEXMEVacunaDet.COLUMNNAME_Intervalo);
				if(intervalo > 0){ // Verificamos si tiene intervalo y si es tiempo de volver a aplicarle la vacuna, si es asi cambiamos el estatus a 1 (vacuna sugerida)
					MUOM uom = new MUOM(ctx, vDet.getC_UOM_ID(), null);
					int anios = 0;
					int meses = 0;;
					int dias = 0;
					//verificamos si el intervalo es en meses y dias o en a�os y meses.
					if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_MONTH)){
						meses = (int)Math.floor(intervalo); //Obtenemos meses
						dias = ((int)(intervalo - meses))*100;
						cal.add(Calendar.MONTH, meses);
						cal.add(Calendar.DATE, dias);
					}
					if(uom.getX12DE355().equalsIgnoreCase(MUOM.X12_YEAR)){
						anios = (int)(Math.floor(intervalo)); //obtenemos los a�os
						meses = (int)((intervalo - anios)*100); //Obtenemos meses
						cal.add(Calendar.YEAR, anios);
						cal.add(Calendar.MONTH, meses);
					}
					
					if(actual.getTime().after(cal.getTime())){//Si es tiempo de que se aplique la vacuna ponemos la vacuna como sugerida para aplicar.
						MEXMEHistVacuna h = new MEXMEHistVacuna(ctx,0,null);
						h.setEXME_Vacuna_ID(vDet.getEXME_Vacuna_ID());
						h.setEXME_VacunaDet_ID(vDet.getEXME_VacunaDet_ID());
						lista.add(h);
					}
				}*/
				
				lista.add(hist);
			}
		}catch (Exception ex){
			s_log.log(Level.SEVERE, "getVacunas(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	public static String getVaccineWarning(Properties ctx, int EXME_Paciente_ID, String sexo, double edad, String whereClause, Object...params){
		String ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// solo considerar para el filtro F y M
		boolean sexoBln = false;
		if(MEXMEVacuna.SEXO_Female.equals(sexo) || MEXMEVacuna.SEXO_Male.equals(sexo)){
			sexoBln = true;
		}
		
		sql.append("SELECT * ");
		sql.append("FROM EXME_Vacuna v  ");
		sql.append("INNER JOIN EXME_VacunaDet vd ON v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive = 'Y' ");
		sql.append("WHERE ? BETWEEN vd.edadMinima AND vd.EdadMaxima ");
		
		if(sexoBln){
			sql.append(" AND v.sexo in (");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Ambiguous)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_NotApplicable)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Other)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Unassigned)).append(",");
			sql.append("?) ");
		}
		
		sql.append(whereClause != null ? whereClause : "");
		sql.append("ORDER BY v.exme_vacuna_id, vd.secuencia ");
		
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			 int i = 0;
			 pstmt.setDouble(++i, edad);
			 if(sexoBln){
				 pstmt.setString(++i, sexo.trim());
			 }
			 for (Object param : params) {
				DB.setParameter(pstmt, ++i, param);
			 }
			 
			rs = pstmt.executeQuery();
			if(!rs.next()){
				ret = Utilerias.getMsg(ctx, "vacuna.error.rangoEdad");
			}
		}catch (Exception ex){
			s_log.log(Level.SEVERE, "getVacunas(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}
	
	public static List<MEXMEHistVacuna> getPendingVaccinesFromID(Properties ctx, int EXME_Paciente_ID,/* String sexo, double edad, */String whereClause, Object...params){
		final List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// solo considerar para el filtro F y M
//		boolean sexoBln = false;
//		if(MEXMEVacuna.SEXO_Female.equals(sexo) || MEXMEVacuna.SEXO_Male.equals(sexo)){
//			sexoBln = true;
//		}
		
		sql.append("SELECT * ");
		sql.append("FROM EXME_Vacuna v  ");
		sql.append("INNER JOIN EXME_VacunaDet vd ON (v.EXME_Vacuna_ID = vd.EXME_Vacuna_ID AND vd.IsActive='Y') ");
		/*sql.append("WHERE ? BETWEEN vd.edadMinima AND vd.EdadMaxima ");
		
		if(sexoBln){
			sql.append(" WHERE v.sexo in (");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Ambiguous)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_NotApplicable)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Other)).append(",");
			sql.append(DB.TO_STRING(MEXMEVacuna.SEXO_Unassigned)).append(",");
			sql.append("?) ");
		}*/
		sql.append(whereClause == null ? "" : whereClause);
		sql.append("ORDER BY v.exme_vacuna_id, vd.secuencia ");
		
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			/* int i = 0;
			 pstmt.setDouble(++i, edad);
			 if(sexoBln){
				 pstmt.setString(++i, sexo.trim());
			 }*/
			rs = pstmt.executeQuery();
			while(rs.next()){
				final MEXMEHistVacuna hist = new MEXMEHistVacuna(ctx, 0, null);
				hist.setEXME_Vacuna_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_EXME_Vacuna_ID));
				hist.setEXME_VacunaDet_ID(rs.getInt(MEXMEVacunaDet.COLUMNNAME_EXME_VacunaDet_ID));
				hist.setM_Product_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_M_Product_ID));
				hist.setC_UOM_ID(rs.getInt(MEXMEVacuna.COLUMNNAME_C_UOM_ID));
				hist.setCantidad(rs.getBigDecimal(MEXMEVacuna.COLUMNNAME_Cantidad));
				
				lista.add(hist);
			}
		}catch (Exception ex){
			s_log.log(Level.SEVERE, "getVacunas(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	public static final int TYPE_Given = 0;// default
	public static final int TYPE_All = 2;
	public static final int TYPE_Rejected = 1;
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param type
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEHistVacuna> getVaccines(Properties ctx, int EXME_Patient_ID,  int type, String trxName){
		List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();

		sql.append(" EXME_HIST_VACUNA.EXME_PACIENTE_ID=? ");
		params.add(EXME_Patient_ID);
		if (type == TYPE_Rejected || type == TYPE_Given) {
			sql.append(" AND EXME_HIST_VACUNA.REJECTED=? ");
			params.add(DB.TO_STRING(type == TYPE_Rejected));
		}
		try {
			lista = new Query(ctx, Table_Name, sql.toString(), null)//
					.setOnlyActiveRecords(true)//
					.addAccessLevelSQL(true)//
					.setParameters(params)//
					.setOrderBy("EXME_HIST_VACUNA.EXME_VACUNA_ID,EXME_HIST_VACUNA.FechaAplica ")//
					.list();
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		}
		return lista;
	}
	
	public static List<MEXMEHistVacuna> getVaccinesWT(Properties ctx, int EXME_Patient_ID, int citaID,  int type, String isExtenal, String trxName){

		List<MEXMEHistVacuna> lista = new ArrayList<MEXMEHistVacuna>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append(" (SELECT 0 AS EXME_HIST_VACUNA_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, ")
		.append(" CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_VACUNA_ID, EXME_VACUNADET_ID, SECUENCIA, FECHAAPLICA, ")
		.append(" INTERVALO, C_UOM_INTER_ID, EDAD, PESO, TALLA, MASACORPORAL, VIA, CANTIDAD, ")
		.append(" C_UOM_ID, EXME_ACTPACIENTE_ID, C_CAMPAIGN_ID, LOT, SERNO, FECHAREGISTRO, C_LOCATIONREG_ID, EXME_MEDICO_ID, ")
		.append(" EXME_ENFERMERIA_ID, SITE, SOURCE, VIS_DATE, VIS_DATEGIVEN, MANUFACTURER, ESLOCAL, REJECTED, ")
		.append(" MOTIVORECHAZO, C_UOMWEIGHT_ID, C_UOMHEIGHT_ID, C_UOMMASACORP_ID, M_ATTRIBUTESETINSTANCE_ID, EXME_LABELER_ID, M_PRODUCT_ID, SEQNO, ")		
		.append(" EXME_CITAMEDICA_ID, ISEXTERNAL, LOCATION, DESCRIPTION ")		
		.append(" FROM EXME_HIST_VACUNA ")
		.append(" WHERE EXME_HIST_VACUNA.EXME_PACIENTE_ID = ? ")
		.append(" AND EXME_HIST_VACUNA.ISACTIVE           = 'Y' ");	
		if(isExtenal != null && isExtenal.equalsIgnoreCase("Y")) {
			sql.append(" AND EXME_HIST_VACUNA.ISEXTERNAL           = 'Y'   ");
		} else if(isExtenal != null && isExtenal.equalsIgnoreCase("N")) {
			sql.append(" AND EXME_HIST_VACUNA.ISEXTERNAL           = 'N'   ");
		}
		if (type == TYPE_Rejected || type == TYPE_Given) {
			sql.append(" AND EXME_HIST_VACUNA.REJECTED           = ?   ");
			
			if(citaID > 0){
				sql.append(" AND EXME_HIST_VACUNA.EXME_CITAMEDICA_ID           = ").append(citaID);
			}
			
			sql.append(" ) UNION  ")		  
			.append(" (  SELECT EXME_HIST_VACUNA_TEMP_ID as EXME_HIST_VACUNA_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, ")
			.append(" CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_VACUNA_ID, EXME_VACUNADET_ID, SECUENCIA, FECHAAPLICA, ")
			.append(" INTERVALO, C_UOM_INTER_ID, EDAD, PESO, TALLA, MASACORPORAL, VIA, CANTIDAD, ")
			.append(" C_UOM_ID, EXME_ACTPACIENTE_ID, C_CAMPAIGN_ID, LOT, SERNO, FECHAREGISTRO, C_LOCATIONREG_ID, EXME_MEDICO_ID, ")
			.append(" EXME_ENFERMERIA_ID, SITE, SOURCE, VIS_DATE, VIS_DATEGIVEN, MANUFACTURER, ESLOCAL, REJECTED, ")
			.append(" MOTIVORECHAZO, C_UOMWEIGHT_ID, C_UOMHEIGHT_ID, C_UOMMASACORP_ID, M_ATTRIBUTESETINSTANCE_ID, EXME_LABELER_ID, M_PRODUCT_ID, SEQNO, ")
			.append(" EXME_CITAMEDICA_ID, ISEXTERNAL, LOCATION, DESCRIPTION ")
			.append(" FROM EXME_HIST_VACUNA_TEMP ")
			.append(" WHERE EXME_HIST_VACUNA_TEMP.EXME_PACIENTE_ID = ? ")
			.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y' ")
			.append(" AND EXME_HIST_VACUNA_TEMP.REJECTED           = ?   ");
			
			if(citaID > 0){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.EXME_CITAMEDICA_ID         = ").append(citaID);
			}
			
			if(isExtenal != null && isExtenal.equalsIgnoreCase("Y")){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'Y'   )");
			} else if(isExtenal != null && isExtenal.equalsIgnoreCase("N")){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'N'   )");
			} else { 
				sql.append(" )");
			}
		}else {
			sql.append(" ) UNION  ")		  
			.append(" (  SELECT EXME_HIST_VACUNA_TEMP_ID as EXME_HIST_VACUNA_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, ")
			.append(" CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_VACUNA_ID, EXME_VACUNADET_ID, SECUENCIA, FECHAAPLICA, ")
			.append(" INTERVALO, C_UOM_INTER_ID, EDAD, PESO, TALLA, MASACORPORAL, VIA, CANTIDAD, ")
			.append(" C_UOM_ID, EXME_ACTPACIENTE_ID, C_CAMPAIGN_ID, LOT, SERNO, FECHAREGISTRO, C_LOCATIONREG_ID, EXME_MEDICO_ID, ")
			.append(" EXME_ENFERMERIA_ID, SITE, SOURCE, VIS_DATE, VIS_DATEGIVEN, MANUFACTURER, ESLOCAL, REJECTED, ")
			.append(" MOTIVORECHAZO, C_UOMWEIGHT_ID, C_UOMHEIGHT_ID, C_UOMMASACORP_ID, M_ATTRIBUTESETINSTANCE_ID, EXME_LABELER_ID, M_PRODUCT_ID, SEQNO, ")
			.append(" EXME_CITAMEDICA_ID, ISEXTERNAL, LOCATION, DESCRIPTION ")
			.append(" FROM EXME_HIST_VACUNA_TEMP ")
			.append(" WHERE EXME_HIST_VACUNA_TEMP.EXME_PACIENTE_ID = ? ")
			.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y' ");
			
			if(citaID > 0){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.EXME_CITAMEDICA_ID         = ").append(citaID);
			}
			if(isExtenal != null && isExtenal.equalsIgnoreCase("Y")){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'Y'   )");
			}else if(isExtenal != null && isExtenal.equalsIgnoreCase("N")){
				sql.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'N'   )");
			}else {
				sql.append(" )");
			}
		}
		sql.append(" Order by fechaAplica desc");
	
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			if (type == TYPE_Rejected || type == TYPE_Given) {
				pstmt.setString(2, type == TYPE_Rejected ? "Y" : "N");
				pstmt.setInt(3, EXME_Patient_ID);
				pstmt.setString(4, type == TYPE_Rejected ? "Y" : "N");
			}else{
				pstmt.setInt(2, EXME_Patient_ID);
			}
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEHistVacuna hist = new MEXMEHistVacuna(ctx, rs, trxName);
				lista.add(hist);
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	public static List<Object> getVaccinesWT2(Properties ctx, int EXME_Patient_ID, int citaID,  int type, String isExtenal, String trxName){

		List<Object> lista = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder sql2 = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		sql.append(" SELECT EXME_HIST_VACUNA_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, ")
		.append(" CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_VACUNA_ID, EXME_VACUNADET_ID, SECUENCIA, FECHAAPLICA, ")
		.append(" INTERVALO, C_UOM_INTER_ID, EDAD, PESO, TALLA, MASACORPORAL, VIA, CANTIDAD, ")
		.append(" C_UOM_ID, EXME_ACTPACIENTE_ID, C_CAMPAIGN_ID, LOT, SERNO, FECHAREGISTRO, C_LOCATIONREG_ID, EXME_MEDICO_ID, ")
		.append(" EXME_ENFERMERIA_ID, SITE, SOURCE, VIS_DATE, VIS_DATEGIVEN, MANUFACTURER, ESLOCAL, REJECTED, ")
		.append(" MOTIVORECHAZO, C_UOMWEIGHT_ID, C_UOMHEIGHT_ID, C_UOMMASACORP_ID, M_ATTRIBUTESETINSTANCE_ID, EXME_LABELER_ID, M_PRODUCT_ID, SEQNO, ")		
		.append(" EXME_CITAMEDICA_ID, ISEXTERNAL, LOCATION, DESCRIPTION ")		
		.append(" FROM EXME_HIST_VACUNA ")
		.append(" WHERE EXME_HIST_VACUNA.EXME_PACIENTE_ID = ? ")
		.append(" AND EXME_HIST_VACUNA.ISACTIVE           = 'Y' ");	
		
		
		sql2.append("   SELECT EXME_HIST_VACUNA_TEMP_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, ")
		.append(" CREATEDBY, UPDATED, UPDATEDBY, EXME_PACIENTE_ID, EXME_VACUNA_ID, EXME_VACUNADET_ID, SECUENCIA, FECHAAPLICA, ")
		.append(" INTERVALO, C_UOM_INTER_ID, EDAD, PESO, TALLA, MASACORPORAL, VIA, CANTIDAD, ")
		.append(" C_UOM_ID, EXME_ACTPACIENTE_ID, C_CAMPAIGN_ID, LOT, SERNO, FECHAREGISTRO, C_LOCATIONREG_ID, EXME_MEDICO_ID, ")
		.append(" EXME_ENFERMERIA_ID, SITE, SOURCE, VIS_DATE, VIS_DATEGIVEN, MANUFACTURER, ESLOCAL, REJECTED, ")
		.append(" MOTIVORECHAZO, C_UOMWEIGHT_ID, C_UOMHEIGHT_ID, C_UOMMASACORP_ID, M_ATTRIBUTESETINSTANCE_ID, EXME_LABELER_ID, M_PRODUCT_ID, SEQNO, ")
		.append(" EXME_CITAMEDICA_ID, ISEXTERNAL, LOCATION, DESCRIPTION ")
		.append(" FROM EXME_HIST_VACUNA_TEMP ")
		.append(" WHERE EXME_HIST_VACUNA_TEMP.EXME_PACIENTE_ID = ? ")
		.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y' ");
		
		
		
		if(isExtenal != null && isExtenal.equalsIgnoreCase("Y")) {
			sql.append(" AND EXME_HIST_VACUNA.ISEXTERNAL           = 'Y'   ");
			sql2.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'Y'   ");


		} else if(isExtenal != null && isExtenal.equalsIgnoreCase("N")) {
			sql.append(" AND EXME_HIST_VACUNA.ISEXTERNAL           = 'N'   ");
			sql2.append(" AND EXME_HIST_VACUNA_TEMP.ISEXTERNAL         = 'N'   ");

		}
		if (type == TYPE_Rejected || type == TYPE_Given) {
			sql.append(" AND EXME_HIST_VACUNA.REJECTED           = ?   ");
			sql2.append(" AND EXME_HIST_VACUNA_TEMP.REJECTED           = ?   ");
		
		}
		
		if(citaID > 0){
			sql.append(" AND EXME_HIST_VACUNA.EXME_CITAMEDICA_ID           = ").append(citaID);
			sql2.append(" AND EXME_HIST_VACUNA_TEMP.EXME_CITAMEDICA_ID         = ").append(citaID);
		}
			
		sql.append(" Order by EXME_HIST_VACUNA.fechaAplica desc");
		sql2.append(" Order by EXME_HIST_VACUNA_TEMP.fechaAplica desc");
	
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			if (type == TYPE_Rejected || type == TYPE_Given) {
				pstmt.setString(2, type == TYPE_Rejected ? "Y" : "N");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEHistVacuna hist = new MEXMEHistVacuna(ctx, rs, trxName);
				lista.add(hist);
			}
			
			pstmt2 = DB.prepareStatement(sql2.toString(), trxName);
			pstmt2.setInt(1, EXME_Patient_ID);
			if (type == TYPE_Rejected || type == TYPE_Given) {
				pstmt2.setString(2, type == TYPE_Rejected ? "Y" : "N");
			}
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				MEXMEHistVacunaTemp hist = new MEXMEHistVacunaTemp(ctx, rs2, trxName);
				lista.add(hist);
			}
			
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
			DB.close(rs2, pstmt2);

		}
		return lista;
	}
	
	/** @deprecated not used */
	public static MEXMEHistVacuna getLastVaccines(Properties ctx, int EXME_Patient_ID,  int type, String trxName){

		MEXMEHistVacuna ret = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT EXME_HIST_VACUNA.* FROM EXME_HIST_VACUNA ");
		sql.append(" WHERE EXME_HIST_VACUNA.EXME_PACIENTE_ID = ? ");
		sql.append(" AND EXME_HIST_VACUNA.ISACTIVE = 'Y' ");
		if (type == TYPE_Rejected || type == TYPE_Given) {
			sql.append(" AND EXME_HIST_VACUNA.REJECTED = ? ");
		}
		sql.append(" ORDER BY EXME_HIST_VACUNA.CREATED DESC ");
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			if (type == TYPE_Rejected || type == TYPE_Given) {
				pstmt.setString(2, type == TYPE_Rejected ? "Y" : "N");
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ret = new MEXMEHistVacuna(ctx, rs, trxName);				
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}
	
	public static boolean getFromTemp(Properties ctx, int EXME_Patient_ID,  int citaID, int actPacienteID, String trxName){

		boolean ret = true;
		MEXMEHistVacuna aux = null;
		boolean existe = false;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT 0 AS EXME_HIST_VACUNA_ID, ")
		.append(" AD_CLIENT_ID,  AD_ORG_ID,  ISACTIVE, ")
		.append(" CREATED,  CREATEDBY,  UPDATED, ")
		.append(" UPDATEDBY,  EXME_PACIENTE_ID,  EXME_VACUNA_ID, ")
		.append(" EXME_VACUNADET_ID,  SECUENCIA,  FECHAAPLICA, ")
		.append(" INTERVALO,  C_UOM_INTER_ID, ")
		.append(" EDAD,  PESO,  TALLA, ")
		.append(" MASACORPORAL,  VIA,  CANTIDAD, ")
		.append(" C_UOM_ID,  EXME_ACTPACIENTE_ID,  C_CAMPAIGN_ID, ")
		.append(" LOT,  SERNO,  FECHAREGISTRO,  C_LOCATIONREG_ID, ")
		.append(" EXME_MEDICO_ID,  EXME_ENFERMERIA_ID,  SITE, ")
		.append(" SOURCE,  VIS_DATE,  VIS_DATEGIVEN,  MANUFACTURER, ")
		.append(" ESLOCAL,  REJECTED,  MOTIVORECHAZO, ")
		.append(" C_UOMWEIGHT_ID,  C_UOMHEIGHT_ID,  C_UOMMASACORP_ID, ")
		.append(" M_ATTRIBUTESETINSTANCE_ID,  EXME_LABELER_ID, ")
		.append(" M_PRODUCT_ID,  SEQNO, ")
		.append(" EXME_CITAMEDICA_ID, ")
		.append(" ISEXTERNAL, ")
		.append(" LOCATION, ")
		.append(" DESCRIPTION ")
		.append(" FROM EXME_HIST_VACUNA_TEMP ")
		.append(" WHERE EXME_HIST_VACUNA_TEMP.EXME_PACIENTE_ID = ? ")
		.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y'  ")
		.append(" AND EXME_CITAMEDICA_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			pstmt.setInt(2, citaID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				aux = new MEXMEHistVacuna(ctx, rs, trxName);
				aux.setEXME_ActPaciente_ID(actPacienteID);
				if(!aux.save(trxName)){
					return false;
				}
				if(!existe)
					existe=true;
			}
			
			if(existe)
				ret = getCleanTemp(ctx, EXME_Patient_ID,  citaID, actPacienteID, trxName);
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}
	
	public static boolean deleteTemp(Properties ctx, int histTempID, String trxName){

		boolean ret = true;
		MEXMEHistVacunaTemp aux = null;		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT EXME_HIST_VACUNA_TEMP_ID, ")
		.append(" AD_CLIENT_ID,  AD_ORG_ID,  ISACTIVE, ")
		.append(" CREATED,  CREATEDBY,  UPDATED, ")
		.append(" UPDATEDBY,  EXME_PACIENTE_ID,  EXME_VACUNA_ID, ")
		.append(" EXME_VACUNADET_ID,  SECUENCIA,  FECHAAPLICA, ")
		.append(" INTERVALO,  C_UOM_INTER_ID, ")
		.append(" EDAD,  PESO,  TALLA, ")
		.append(" MASACORPORAL,  VIA,  CANTIDAD, ")
		.append(" C_UOM_ID,  EXME_ACTPACIENTE_ID,  C_CAMPAIGN_ID, ")
		.append(" LOT,  SERNO,  FECHAREGISTRO,  C_LOCATIONREG_ID, ")
		.append(" EXME_MEDICO_ID,  EXME_ENFERMERIA_ID,  SITE, ")
		.append(" SOURCE,  VIS_DATE,  VIS_DATEGIVEN,  MANUFACTURER, ")
		.append(" ESLOCAL,  REJECTED,  MOTIVORECHAZO, ")
		.append(" C_UOMWEIGHT_ID,  C_UOMHEIGHT_ID,  C_UOMMASACORP_ID, ")
		.append(" M_ATTRIBUTESETINSTANCE_ID,  EXME_LABELER_ID, ")
		.append(" M_PRODUCT_ID,  SEQNO ")
		.append(" FROM EXME_HIST_VACUNA_TEMP ")
		.append(" WHERE EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y' ")
		//.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y'  ")
		//.append(" AND EXME_CITAMEDICA_ID = ? ")
		.append(" AND EXME_HIST_VACUNA_TEMP_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, histTempID);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				aux = new MEXMEHistVacunaTemp(ctx, rs, trxName);
				aux.setIsActive(false);
				if(!aux.save(trxName)){
					return false;
				}				
			}			
			
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return ret;
	}
	
	public static boolean getCleanTemp(Properties ctx, int EXME_Patient_ID,  int citaID, int actPacienteID, String trxName){

		MEXMEHistVacunaTemp ret = null;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT EXME_HIST_VACUNA_TEMP_ID, ")
		.append(" AD_CLIENT_ID,  AD_ORG_ID,  ISACTIVE, ")
		.append(" CREATED,  CREATEDBY,  UPDATED, ")
		.append(" UPDATEDBY,  EXME_PACIENTE_ID,  EXME_VACUNA_ID, ")
		.append(" EXME_VACUNADET_ID,  SECUENCIA,  FECHAAPLICA, ")
		.append(" INTERVALO,  C_UOM_INTER_ID, ")
		.append(" EDAD,  PESO,  TALLA, ")
		.append(" MASACORPORAL,  VIA,  CANTIDAD, ")
		.append(" C_UOM_ID,  EXME_ACTPACIENTE_ID,  C_CAMPAIGN_ID, ")
		.append(" LOT,  SERNO,  FECHAREGISTRO,  C_LOCATIONREG_ID, ")
		.append(" EXME_MEDICO_ID,  EXME_ENFERMERIA_ID,  SITE, ")
		.append(" SOURCE,  VIS_DATE,  VIS_DATEGIVEN,  MANUFACTURER, ")
		.append(" ESLOCAL,  REJECTED,  MOTIVORECHAZO, ")
		.append(" C_UOMWEIGHT_ID,  C_UOMHEIGHT_ID,  C_UOMMASACORP_ID, ")
		.append(" M_ATTRIBUTESETINSTANCE_ID,  EXME_LABELER_ID, ")
		.append(" M_PRODUCT_ID,  SEQNO ")
		.append(" FROM EXME_HIST_VACUNA_TEMP ")
		.append(" WHERE EXME_HIST_VACUNA_TEMP.EXME_PACIENTE_ID = ? ")
		.append(" AND EXME_HIST_VACUNA_TEMP.ISACTIVE           = 'Y'  ")
		.append(" AND EXME_CITAMEDICA_ID = ? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Patient_ID);
			pstmt.setInt(2, citaID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ret = new MEXMEHistVacunaTemp(ctx, rs, trxName);
				ret.setIsActive(false);
				if(!ret.save(trxName)){
					return false;
				}
			}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "getVaccines(Properties ctx)", ex);
		} finally {
			DB.close(rs, pstmt);
		}
		return true;
	}

	/**
	 * Llena el registro de historico de vacuna con los datos de la prescripcion*
	 * @param prescRx Prescripcion de medicamentos
	 */
	public void setFromPresRx(final MEXMEPrescRXDet prescRx) {
		if(prescRx != null){
			final MEXMECtaPac ctaPac = new MEXMECtaPac(getCtx(), prescRx.getEXME_CtaPac_ID(), null);
			setEXME_Paciente_ID(ctaPac.getEXME_Paciente_ID());
			setEXME_ActPaciente_ID(ctaPac.getActividades().get(0).getEXME_ActPaciente_ID());
			setEdad(new BigDecimal(ctaPac.getPaciente().getAge().getAnios()));
			if (Env.getEXME_Enfermeria_ID(getCtx(), true) <= 0) {
				setEXME_Medico_ID(prescRx.getEXME_Medico_ID());
			}
			if (prescRx.getM_Product_ID() > 0 && MEXMEMejoras.isNDC(getCtx(), prescRx.getM_Product_ID())) {
				setEXME_Labeler_ID(prescRx.getM_Product().getEXME_Labeler_ID());
				setM_Product_ID(prescRx.getM_Product_ID());
				setC_UOM_ID(prescRx.getM_Product().getC_UOM_ID());
			}
		}
	}
	
	@Override
	public void setMotivoRechazo(String MotivoRechazo) {
		try {
			super.setMotivoRechazo(MotivoRechazo);
		} catch (Exception e) {
			super.setMotivoRechazo(null);
		}
	}
	
	public String getLabeler(){
		final StringBuilder mvxlabeler = new StringBuilder();
		if (getEXME_Labeler_ID() > 0) {
			final I_EXME_Labeler labeler = getEXME_Labeler();
			mvxlabeler.append(StringUtils.defaultString(labeler.getMfgname())).append(" ");
			mvxlabeler.append(StringUtils.defaultString(labeler.getMVXCode()));
		} else if (StringUtils.isNotBlank(getManufacturer())) {
			mvxlabeler.append(getManufacturer());
		}
		return mvxlabeler.toString();
	}
	
	public String getTypeCvx(){
		final StringBuilder typeCvx = new StringBuilder();
		if (getEXME_VacunaDet_ID() > 0) {
			typeCvx.append(StringUtils.defaultString(getVacunaDet().getVaccineType())).append(" ");
		}
		typeCvx.append(StringUtils.defaultString(getVacuna().getCodeCVX()));
		return typeCvx.toString();
	}
	
	public String getVaccineValue(){
		return getVacuna().getValue();
	}
}

