package org.compiere.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.mo.MO_Radiografias_VO;

public class MEXMETratamientosPaciente extends X_EXME_TratamientosPaciente{

	/** serialVersionUID */
	private static final long serialVersionUID = -3563274178014492048L;
	
	/** Static logger           */
	private static CLogger log = CLogger.getCLogger(MEXMETratamientosPaciente.class);
	private String piezaDental = null;
	private String nombreTratamiento = null;
	private String nombreSubEspecialidad = null;   


	/**
     * @param ctx
     * @param EXME_CitaMedica_ID
     * @param trxName
     */
    public MEXMETratamientosPaciente(Properties ctx, int MEXMETratamientosPaciente_ID, String trxName) {
        super(ctx, MEXMETratamientosPaciente_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMETratamientosPaciente(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    public String getNombreSubEspecialidad() {
		return nombreSubEspecialidad;
	}

	public void setNombreSubEspecialidad(String nombreSubEspecialidad) {
		this.nombreSubEspecialidad = nombreSubEspecialidad;
	}
    
    
    public String getPiezaDental() {
		return piezaDental;
	}

	public void setPiezaDental(String piezaDental) {
		this.piezaDental = piezaDental;
	}
	

	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}

	
	 /**
	 * Liz de la Garza
	 * Ordenar los tratamientos asociados al paciente
	 * @param Properties ctx
	 * @param int paciente_id
	 * @param String mes
	 * @param String anio
	 * @param int citaID
	 * @param int tratamientoID
	 * @return List<MO_Radiografias_VO>
	 */
    public static List<MO_Radiografias_VO> getTratamientosPac(Properties ctx,  int paciente_id, String mes, String anio, int citaID, int tratamientoID){
		List<MO_Radiografias_VO> lista = new ArrayList<MO_Radiografias_VO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT TO_CHAR(tratam.Fecha_Tratamiento,'dd/MM/yyyy')AS Fecha, trat.name AS NombreTratam , tratam.Description ")
		.append(", pieza.Value AS NombrePieza, tratam.EXME_TratamientosPaciente_ID AS tratamientoID , tratam.EXME_Tratamientos_ID as tratamID") 
		.append(" FROM EXME_TratamientosPaciente tratam ")
		.append(" INNER JOIN EXME_Tratamientos trat ON(tratam.EXME_Tratamientos_ID = trat.EXME_Tratamientos_ID) ")
		.append(" INNER JOIN EXME_MO_PiezaDental pieza ON (tratam.EXME_MO_PiezaDental_ID = pieza.EXME_MO_PiezaDental_ID) ")
		.append(" WHERE tratam.EXME_Paciente_ID = ? ")
		.append(" AND EXTRACT(MONTH FROM tratam.Fecha_Tratamiento) = ").append(mes)  
		.append(" AND EXTRACT(YEAR FROM tratam.Fecha_Tratamiento) = ").append(anio);
		if(citaID != 0){
			sql.append(" AND tratam.EXME_CitaMedica_ID = ").append(citaID);
		}
		if(tratamientoID != 0){
			sql.append(" AND tratam.EXME_TratamientosPaciente_ID = ").append(tratamientoID);
		}
		sql.append(" ORDER BY tratam.fecha_tratamiento desc, tratam.exme_tratamientos_id , pieza.exme_mo_piezadental_id");


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, paciente_id);


			rs = pstmt.executeQuery();

			while(rs.next()){

				//Vista para la cita medica
				MO_Radiografias_VO citas = new MO_Radiografias_VO();

				citas.setFecha(rs.getString("Fecha"));
				citas.setTratamPacID(rs.getInt("tratamientoID"));
				citas.setNombrePieza(rs.getString("NombrePieza"));
				citas.setNombreTratam(rs.getString("NombreTratam"));
				citas.setDescriptionTratam(rs.getString("Description"));
				citas.setTratamientoID(rs.getInt("tratamID"));

				lista.add(citas);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getCitasMedico", e);
			e.printStackTrace();
		}


		return lista;
	}
    
	/**
	 * Liz de la Garza
	 * Ordenar los tratamientos asociados al paciente
	 * @param Properties ctx
	 * @param List<MO_Radiografias_VO> lstAuxiliar
	 * @return List<MO_Radiografias_VO>
	 */    
    public static List<MO_Radiografias_VO> listaTratamientos(Properties ctx, List<MO_Radiografias_VO> lstAuxiliar){
    	List<MO_Radiografias_VO> listas = new ArrayList<MO_Radiografias_VO>();
		
    	String descrip = null;
    	String fecha = null;
    	int tratamID = 0;
    	MO_Radiografias_VO obj = new MO_Radiografias_VO();
        
    	//Ordenar la informacion por tratamiento y por piezas dentales asignadas a este
 	
    	for(int i = 0; i< lstAuxiliar.size(); i++){
    		MO_Radiografias_VO object = new MO_Radiografias_VO();
    		if(i==0){
    			descrip = lstAuxiliar.get(i).getDescriptionTratam();
    			fecha = lstAuxiliar.get(i).getFecha();
    			tratamID = lstAuxiliar.get(i).getTratamientoID();
    		}
    		//Validar que la descripcion, fecha y tratamientoID coincidan para obtener las piezas dentales asignadas a este
    		if(lstAuxiliar.get(i).getDescriptionTratam().equals(descrip)
    				&&lstAuxiliar.get(i).getFecha().equals(fecha)&&lstAuxiliar.get(i).getTratamientoID()==tratamID){
    			object.setNombrePieza(lstAuxiliar.get(i).getNombrePieza());
    			object.setTratamPacID(lstAuxiliar.get(i).getTratamPacID());
    			obj.getPiezaDental().add(object);
    			obj.setFecha(lstAuxiliar.get(i).getFecha());
    			obj.setNombreTratam(lstAuxiliar.get(i).getNombreTratam());
    			obj.setTratamientoID(lstAuxiliar.get(i).getTratamientoID());
    			if(i==(lstAuxiliar.size()-1))
    				listas.add(obj);
 				
    		}else{//Pasar al siguiente tratamiento del paciente
    			listas.add(obj);
    			obj = new MO_Radiografias_VO();
    			object.setNombrePieza(lstAuxiliar.get(i).getNombrePieza());
    			object.setTratamPacID(lstAuxiliar.get(i).getTratamPacID());
    			obj.getPiezaDental().add(object);
    			obj.setFecha(lstAuxiliar.get(i).getFecha());
    			obj.setNombreTratam(lstAuxiliar.get(i).getNombreTratam());
    			obj.setTratamientoID(lstAuxiliar.get(i).getTratamientoID());
    			descrip = lstAuxiliar.get(i).getDescriptionTratam();
    			fecha = lstAuxiliar.get(i).getFecha();
    			tratamID = lstAuxiliar.get(i).getTratamientoID();
    		}
 		
    	}
		
    	return listas;
    }
    
    private MEXMETratamientos m_tratamiento = null;
    
    public MEXMETratamientos getTratamiento(String citaNo){
    	if(m_tratamiento == null && getEXME_Tratamientos_ID() > 0){
    		return new MEXMETratamientos(getCtx(),getEXME_Tratamientos_ID(), citaNo, get_TrxName());
    	}
    	return m_tratamiento;
    }
    
    /**
	 * Regresa los tratamientos de paciente para una cita, numero de cita, tratamientos
	 * -- Se ejecuta desde Odontologia (citaNo = -1) .- LAMA
	 * @param ctx
	 * @param trxName
	 * @param pacienteID
	 * @param citaID
	 * @param piezasDentalesID
	 * @param mes
	 * @param year
	 * @param esOdontograma
	 * @return
	 */
	public static ArrayList<MEXMETratamientosPaciente> obtenerTratamientosPac(Properties ctx, String trxName, int pacienteID,
 			int citaID, ArrayList<String> piezasDentalesID, String mes, String year, String esOdontograma){		
		return obtenerTratamientosPac(ctx, trxName, pacienteID, citaID, piezasDentalesID, mes, year, -1, esOdontograma);
	}

    /**
     * Regresa los tratamientos de paciente para una cita, numero de cita, tratamientos
	 * Modificado por Lorena Lama (recibe No. de Cita)
     * @param ctx
     * @param trxName
     * @param pacienteID
     * @param citaID
     * @param piezasDentalesID
     * @param mes
     * @param year
     * @param citaNo
     * @param esOdontograma
     * @return
     */
    public static ArrayList<MEXMETratamientosPaciente> obtenerTratamientosPac(Properties ctx, String trxName, int pacienteID,
 			int citaID, ArrayList<String> piezasDentalesID, String mes, String year, int citaNo, String esOdontograma){

		ArrayList<MEXMETratamientosPaciente> resultado = new ArrayList<MEXMETratamientosPaciente>();
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT EXME_TratamientosPaciente.*, ");	
		if(piezasDentalesID.size()>0){
			sql.append(" EXME_MO_PiezaDental.Value as piezaDental, EXME_Tratamientos.Name as nombreTratamiento, EXME_Especialidad.Name as subEspecialidad  ");
		}else{
			sql.append(" ' ' as piezaDental, EXME_Tratamientos.Name as nombreTratamiento, EXME_Especialidad.Name as subEspecialidad ");
		}
		sql.append(" FROM EXME_TratamientosPaciente ");
		if(piezasDentalesID.size()>0){
			sql.append(" INNER JOIN EXME_MO_PiezaDental ON EXME_MO_PiezaDental.EXME_MO_PiezaDental_ID = EXME_TratamientosPaciente.EXME_MO_PiezaDental_ID ");
		}
		sql.append(" INNER JOIN EXME_Tratamientos ON EXME_Tratamientos.EXME_Tratamientos_ID = EXME_TratamientosPaciente.EXME_Tratamientos_ID ");
		sql.append(" LEFT JOIN EXME_Especialidad ON EXME_TratamientosPaciente.Ref_EXME_Especialidad_ID = EXME_Especialidad.EXME_Especialidad_ID ");
		sql.append(" WHERE EXME_TratamientosPaciente.EXME_Paciente_ID = ");
		sql.append(pacienteID);
		sql.append(" AND EXTRACT(MONTH FROM EXME_TratamientosPaciente.Fecha_Tratamiento) = ");
		sql.append(mes);
		sql.append(" AND EXTRACT(YEAR FROM EXME_TratamientosPaciente.Fecha_Tratamiento)  = ");
		sql.append(year);
		//if(piezasDentalesID.size()>0){
			sql.append(" AND EXME_TratamientosPaciente.EsOdontograma = '");
			sql.append(esOdontograma);
			sql.append("' ");
		//}
		if(citaID!=0){
			sql.append(" AND EXME_TratamientosPaciente.EXME_CitaMedica_ID = ");
			sql.append(citaID);
		}
		// Agregado por aplicacion de tratamientos .- Lama
		if(citaNo>0){
			sql.append(" AND EXME_TratamientosPaciente.CitaNo = ");
			sql.append(citaNo);
		}
		
		if(piezasDentalesID.size()>0){
			sql.append(" AND EXME_TratamientosPaciente.EXME_MO_PiezaDental_ID IN ( ");
			for(int i=0; i <piezasDentalesID.size(); i++){
				 String piezaDentalID = piezasDentalesID.get(i).toString();
				 if((i+1)==piezasDentalesID.size()){
					 sql.append(piezaDentalID);
				 }else{
					 sql.append(piezaDentalID + " , ");
				 }
			 }
			 sql.append(" ) ");
		}else{
			 sql.append(" AND (EXME_TratamientosPaciente.EXME_MO_PiezaDental_ID IN (0) OR EXME_TratamientosPaciente.EXME_MO_PiezaDental_ID IS NULL)");
		}
		
		sql = new StringBuffer(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_TratamientosPaciente"));
		sql.append(" ORDER BY EXME_TratamientosPaciente.Fecha_Tratamiento, EXME_TratamientosPaciente.EXME_Tratamientos_ID, EXME_TratamientosPaciente.Description");


		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 pstmt = DB.prepareStatement(sql.toString(), trxName);
			 rs = pstmt.executeQuery();
			 while (rs.next()) {
				 MEXMETratamientosPaciente obj = new MEXMETratamientosPaciente(ctx, rs, trxName);
				 obj.setPiezaDental(rs.getString("piezaDental"));
				 obj.setNombreTratamiento(rs.getString("nombreTratamiento"));
				 obj.setNombreSubEspecialidad(rs.getString("subEspecialidad"));
				 resultado.add(obj); 
			 }
		}catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
	
		}
		return resultado;
	}
    
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosPaciente> getFromCtaOPac(Properties ctx,
    		int EXME_CtaPac_ID, String[] orderByColumns, String trxName, int EXME_Paciente_ID) {
		
		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		if (EXME_CtaPac_ID >0 ) {			
			where.append(" AND EXME_CtaPac_ID = ? ");
		}else {
			where.append(" AND EXME_Paciente_ID = ?");
		}
    	

    	if (orderByColumns != null) {
    		where.append(" ORDER BY ");

    		for (int i = 0; i < orderByColumns.length; i++) {
    			where.append(orderByColumns[i]);
    			if (i < orderByColumns.length - 1) {
    				where.append(", ");
    			}
    		}
    	}

    	List<Object> params = new ArrayList<Object>();
    	if (EXME_CtaPac_ID >0 ) {	
    		params.add(EXME_CtaPac_ID);
    	}else {
			params.add(EXME_Paciente_ID);
		}
    	
    	return getTratamientos(ctx, where.toString(), params, trxName);
    }
    
	/**
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosPaciente> getFromCtaPac(Properties ctx,
    		int EXME_CtaPac_ID, String[] orderByColumns, String trxName) {

    	StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	where.append(" AND EXME_CtaPac_ID = ? ");

    	if (orderByColumns != null) {
    		where.append(" ORDER BY ");

    		for (int i = 0; i < orderByColumns.length; i++) {
    			where.append(orderByColumns[i]);
    			if (i < orderByColumns.length - 1) {
    				where.append(", ");
    			}
    		}
    	}

    	List<Object> params = new ArrayList<Object>();
    	params.add(EXME_CtaPac_ID);
    	
    	return getTratamientos(ctx, where.toString(), params, trxName);
    }
    
    /**
     * Busqueda de tratamientos del paciente por cuenta o por paciente
     * @param ctx
     * @param EXME_CtaPac_ID
     * @param EXME_Paciente_ID
     * @param orderBy
     * @param trxName
     * @return
     */
	public static List<MEXMETratamientosPaciente> getTratamientos(Properties ctx,
    		int EXME_CtaPac_ID, String where, List<?> params,  String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
    	sql.append(" SELECT * ")
    	.append("    FROM  EXME_TratamientosPaciente ")
    	.append("    INNER JOIN EXME_Tratamientos t ")
    	.append("          ON EXME_TratamientosPaciente.EXME_Tratamientos_ID = t.EXME_Tratamientos_ID ")
    	.append("    WHERE EXME_TratamientosPaciente.isActive= 'Y' ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))    	
    	.append(where!=null?where:" ").append(" ")
    	.append("    ORDER BY EXME_TratamientosPaciente.EXME_Tratamientos_ID, ")
    	.append("          EXME_TratamientosPaciente.EXME_TratamientosPaciente_ID DESC ");
    		

    	return get(ctx, sql.toString(), params, true, trxName);
    }
	
    /**
     * Busqueda de tratamientos del paciente
     * @param ctx
     * @param EXME_CtaPac_ID
     * @param EXME_Paciente_ID
     * @param orderBy
     * @param trxName
     * @return
     */
	public static List<MEXMETratamientosPaciente> getTratamientos(Properties ctx,
    		String where, List<?> params,  String trxName) {

    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	
    	sql.append(" SELECT * ")
    	.append("    FROM  EXME_TratamientosPaciente ")
    	.append("    WHERE isActive= 'Y' ")
    	.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))    	
    	.append(where!=null?where:" ").append(" ");

    	return get(ctx, sql.toString(), params, false, trxName);
    }
	
	/**
	 * Buscamos los tratamientos del paciente
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMETratamientosPaciente> get(Properties ctx,
			String sql, List<?> params, boolean leerDetalle, String trxName) {
		
		List<MEXMETratamientosPaciente> retValue = new ArrayList<MEXMETratamientosPaciente>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				MEXMETratamientosPaciente tratPac = new MEXMETratamientosPaciente(
						ctx, rs, trxName);

				// Leemos el detalle de cada tratamiento
				if(leerDetalle){
					tratPac.setTreatment(new MEXMETratamientos(ctx, rs, trxName));//TODO: Verificar si se crea o no el tratamiento
					if(tratPac.getTreatment()!=null)
						tratPac.getTreatment().getLstSesiones(true);
				}

				retValue.add(tratPac);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
    /**
     * Objeto de tratamiento
     */
    public MEXMETratamientos getTreatment(){
    	if(m_tratamiento == null && getEXME_Tratamientos_ID() > 0){
    		return new MEXMETratamientos(getCtx(),getEXME_Tratamientos_ID(), get_TrxName());
    	}
    	return m_tratamiento;
    }
    
    public void setTreatment(MEXMETratamientos treatment){
    	this.m_tratamiento = treatment;
    }
    
    /**
     * Medico que solicita el tratamiento
     */
    private MEXMEMedico medico = null;


	public MEXMEMedico getMedico() {
		if(medico == null && getEXME_Medico_ID() > 0){
    		return new MEXMEMedico(getCtx(),getEXME_Medico_ID(), get_TrxName());
    	}
    	return medico;
	}

	public void setMedico(MEXMEMedico medico) {
		this.medico = medico;
	}
    
	/**
	 * llenamos el objeto tratamiento paciente
	 * @param tratamientos objeto del tratamiento
	 * @param EXME_Paciente_ID paciente
	 * @param EXME_CtaPac_ID cuenta paciente
	 */
	public boolean crear(MEXMETratamientos tratamientos, int EXME_Paciente_ID, int EXME_CtaPac_ID){
		
		if(tratamientos==null || EXME_Paciente_ID <= 0 || EXME_CtaPac_ID <= 0)
			return false;
		
		this.setEXME_Paciente_ID(EXME_Paciente_ID);
		this.setEXME_Tratamientos_ID(tratamientos
				.getEXME_Tratamientos_ID());
		this.setFecha_Tratamiento(new Timestamp(tratamientos
				.getFecha().getTime()));
		this.setEXME_CtaPac_ID(EXME_CtaPac_ID);
		this.setDescription(tratamientos.getObservaciones());
		
		return true;
	}
	
	
    private MEXMEPaciente _paciente = null;


	public MEXMEPaciente getPaciente() {
		if(_paciente == null && getEXME_Paciente_ID() > 0){
    		return new MEXMEPaciente (getCtx(),getEXME_Paciente_ID(), get_TrxName());
    	}
    	return _paciente;
	}

	public void setPaciente(MEXMEPaciente paciente) {
		this._paciente = paciente;
	}
	
	private MEXMECtaPac _ctaPac = null;
	
 	public MEXMECtaPac getCtaPac(){
 		if(_ctaPac == null){
 			_ctaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
 		}
 		return _ctaPac;
 	}
}
