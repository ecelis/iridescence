package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 * Copyright (c) 2008 Expert Sistemas Computacionales S.A. de C.V.
 * Todos los derechos  reservados.
 * Nombre del Archivo: MEXMEVacunaDet.java 
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

public class MEXMEVacunaDet extends X_EXME_VacunaDet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CLogger
	 */
	private static CLogger s_log = CLogger.getCLogger(MEXMEVacunaDet.class);
	/**
	 * Constructor ID
	 * @param ctx
	 * @param EXME_Hist_Vacuna_ID
	 * @param trxName
	 */
	public MEXMEVacunaDet(Properties ctx, int EXME_VacunaDet_ID, String trxName) {
		super(ctx, EXME_VacunaDet_ID, trxName);
	}

	/**
	 * Constructor ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEVacunaDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord) {

		if(getEdadMaxima() != null &&
				getEdadMaxima().compareTo(Env.ZERO) <= 0){			
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@VaccineEdadMax@"));
			return false;
		}
		if(getEdadMinima() != null &&  getEdadMaxima() != null &&
				getEdadMaxima().compareTo(Env.ZERO) > 0  
				&& !(getEdadMinima().doubleValue()>getEdadMaxima().doubleValue())){

			setEdadMinima(new BigDecimal(MEXMEVacunaDet.calculaEdad(getEdadMinima().doubleValue())));
			setEdadMaxima(new BigDecimal(MEXMEVacunaDet.calculaEdad(getEdadMaxima().doubleValue())));

		}else{
			return false;
		}
		return true;
	}	//	beforeSave
	

	public void get(ResultSet rs) 
	throws SQLException
	{
		setAD_Client_ID(rs.getInt("AD_Client_ID"));
		setAD_Org_ID(rs.getInt("AD_Org_ID"));
		setEXME_Vacuna_ID(rs.getInt("EXME_Vacuna_ID"));
		setIsActive(true);
		setEXME_VacunaDet_ID(rs.getInt("EXME_VacunaDet_ID"));
		setC_UOM_ID(rs.getInt("UOMDetalle"));
		setEdadMaxima(rs.getBigDecimal("EdadMaxima"));
		setEdadMinima(rs.getBigDecimal("EdadMinima"));
		setIntervalo(rs.getBigDecimal("Intervalo"));
		setSecuencia(rs.getInt("Secuencia"));
		setTipoDosis(rs.getString("TipoDosis"));
		
		setSeleccionada(false);
		
		boolean isBaseLanguage = Env.isBaseLanguage(Env.getAD_Language(getCtx()), "AD_Ref_List");
		setDosis(isBaseLanguage?rs.getString("DosisIngles"):rs.getString("DosisIdioma"));
		
	}
	
	/**
	 * Historia de la aplicacion de vacunas
	 */
	private List<MEXMEHistVacuna> historial = null;

	public List<MEXMEHistVacuna> getHistorial() {
		return historial;
	}

	public void setHistorial(List<MEXMEHistVacuna> historial) {
		this.historial = historial;
	}
	
	/**vacuna
	 * Permite saber si la secuencia a sido seleccionada
	 */
	private boolean seleccionada = false;

	public boolean getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
	
	/**
	 * Fecha que se mostrar en la cartilla
	 * referente a la fecha en que se aplicara o se aplico
	 */
	private String fechaAplicacion = null;
	
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	/**
	 * Status en que se encuentra la vacuna
	 * puede ir de 0 a 2
	 * 0 = sin ninguna observaci�n
	 * 1 = fecha programada
	 * 2 = vacuna aplicada
	 */
	private int estatus = 0;
	
	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}


	private String dosis;

	/**
	 * Presenta el nombre del tipo de dosis
	 * en el idioma que se ha seleccionado el 
	 * usuario y que exista la traduccion en el 
	 * diccionario de datos
	 */
	public String getDosis() {
		if(dosis == null){
			dosis = MRefList.getListName(getCtx(), TIPODOSIS_AD_Reference_ID, getTipoDosis());
		}
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	
	/**
	 * Calcula la edad en formato Anios.Meses
	 * @author LRHU
	 * @param double edad, valor en double de la edad en anios.meses
	 * @return double edadCalculada, valor correcto en anios.meses 
	 */
	public static double calculaEdad(double edad){
		double edadCalculada = 0;
		
		double edadAnios = Math.floor(edad);
		double edadMeses = (edad- edadAnios)*100;
		double edadC = Math.floor(((edadAnios*12)+(edadMeses))/12);
		edadCalculada = edadC + (((edadAnios*12)+(edadMeses))%12)/100;
		
		return edadCalculada;
	}
	
	/**
	 * Detalle de la vacuna (dosis a aplicar)
	 * @param ctx
	 * @param EXME_Vacuna_ID
	 * @return
	 * lhernandez.
	 */
	public static List<MEXMEVacunaDet> getDetalle(Properties ctx, int EXME_Vacuna_ID){
		List<MEXMEVacunaDet> lista = new ArrayList<MEXMEVacunaDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * ");
		sql.append("FROM EXME_VacunaDet "); 
		sql.append("WHERE EXME_Vacuna_ID = ? ");
		sql.append(" AND ISACTIVE = 'Y' ");
		sql.append("ORDER BY Secuencia ASC ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, EXME_Vacuna_ID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacunaDet det = new MEXMEVacunaDet(ctx, rs, null);
				 lista.add(det);
			 }
			 
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	private MEXMEVacuna vaccine;
	public MEXMEVacuna getVaccine() {
		if(vaccine == null){
			vaccine = new MEXMEVacuna(getCtx(), getEXME_Vacuna_ID(), get_TrxName());
		}
		return vaccine;
	}
	public void setVaccine(MEXMEVacuna vaccine) {
		this.vaccine = vaccine;
	}
	
	/**
	 * Crea el historico de vacuna<br>
	 * copia los valores (default) definidos en la secuencia y en la vacuna
	 * @return {@link MEXMEHistVacuna}
	 */
	public MEXMEHistVacuna createHistVaccine() {
		final MEXMEHistVacuna histVacuna = new MEXMEHistVacuna(getCtx(), 0, get_TrxName());
		histVacuna.setEXME_Vacuna_ID(getEXME_Vacuna_ID());
		histVacuna.setEXME_VacunaDet_ID(getEXME_VacunaDet_ID());
		histVacuna.setFechaRegistro(Env.getCurrentDate());
		histVacuna.setSecuencia(new BigDecimal(getSecuencia()));
		histVacuna.setVia(getVaccine().getVia());
		histVacuna.setEsLocal(true);
		histVacuna.setRejected(false);
		histVacuna.setCantidad(getVaccine().getCantidad());
		histVacuna.setC_UOM_ID(getVaccine().getC_UOM_ID());
		if (Env.getEXME_Medico_ID(getCtx()) > 0) {
			histVacuna.setEXME_Medico_ID(Env.getEXME_Medico_ID(getCtx()));
		}
		if (Env.getEXME_Enfermeria_ID(getCtx()) > 0) {
			histVacuna.setEXME_Enfermeria_ID(Env.getEXME_Enfermeria_ID(getCtx()));
		}
		return histVacuna;
	}
	
	/**
	 * Detalle de la vacuna en base a producto
	 * @param ctx
	 * @param EXME_Vacuna_ID
	 * @return
	 * lhernandez.
	 */
	public static List<MEXMEVacunaDet> getByProduct(Properties ctx, int productID){
		List<MEXMEVacunaDet> lista = new ArrayList<MEXMEVacunaDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT * FROM EXME_VacunaDet "); 
		sql.append("WHERE M_product_ID = ? ");
		sql.append(" AND ISACTIVE = 'Y' ");
		sql.append("ORDER BY Secuencia ASC ");
		
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, productID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 MEXMEVacunaDet det = new MEXMEVacunaDet(ctx, rs, null);
				 lista.add(det);
			 }
			 
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
}
