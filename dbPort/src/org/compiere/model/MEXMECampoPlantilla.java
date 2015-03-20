/**
 * 
 */
package org.compiere.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.UnzippedDocumentTemplate;
import net.sf.jooreports.templates.ZippedDocumentTemplate;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

import freemarker.core.InvalidReferenceException;

/**
 * @author Jose Isaac Garcia
 * @author David Nuncio
 * @author mvrodriguez
 * 
 */
public class MEXMECampoPlantilla extends X_EXME_CampoPlantilla {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5995337130306496367L;
	/** bitacora */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMECampoPlantilla.class);
	/** Objeto de la tabla en cuestion */
	private MTable tabla = null;
	/** Objeto del paciente */
	private MEXMEPaciente paciente = null;
	/** propiedades MEXMECampoPlantilla.props */
	static Properties props = new Properties();
	/** Objeto de la tabla en cuestion */
	private MColumn column = null;

	/**
	 * Constructo ID
	 * 
	 * @param ctx
	 * @param EXME_CampoPlantilla_ID
	 * @param trxName
	 */
	public MEXMECampoPlantilla(Properties ctx, int EXME_CampoPlantilla_ID,
			String trxName) {
		super(ctx, EXME_CampoPlantilla_ID, trxName);
	}

	/**
	 * Constructor ResultSet
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECampoPlantilla(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Campos plantillas
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECampoPlantilla> getCampos(Properties ctx,
			String trxName) {

		StringBuilder st = new StringBuilder();
		st.append(" SELECT * FROM EXME_CampoPlantilla ");
		st.append(" WHERE EXME_CampoPlantilla_Id IS NOT NULL");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", 
				X_EXME_CampoPlantilla.Table_Name));
		st.append(" ORDER BY name");
		
		return MEXMECampoPlantilla.getCampos(ctx,
				st.toString(), null, trxName);
	}
	
	public static List<MEXMECampoPlantilla> getCamposPregunta(Properties ctx,
			String trxName) {

		StringBuilder st = new StringBuilder();
		st.append(" SELECT * FROM EXME_CampoPlantilla ");
		st.append(" WHERE IsActive = 'Y' ");
		st.append(" AND EXME_Pregunta_ID >0 ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", 
				X_EXME_CampoPlantilla.Table_Name));
		st.append(" ORDER BY name");
		
		return MEXMECampoPlantilla.getCampos(ctx,
				st.toString(), null, trxName);
	}
	
	/**
	 * Campos plantillas
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECampoPlantilla> getCampos(Properties ctx,
			String sql, List<?> params, String trxName) {

		List<MEXMECampoPlantilla> lst = new ArrayList<MEXMECampoPlantilla>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMECampoPlantilla(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lst;
	}

	/**
	 * Busca en la base de datos por la lista de etiquetas proporcionada al
	 * proceso y genera un objeto de cada registro que encuentra.
	 * 
	 * @param ctx
	 * @param params
	 * @return
	 */
	public static List<MEXMECampoPlantilla> getCampos(Properties ctx,
			List<String> params) {
		StringBuilder st = new StringBuilder();
		st.append(" SELECT * FROM EXME_CampoPlantilla ");
		st.append(" WHERE value IN (");
		st.append(StringUtils.join(params.toArray(), ","));
		st.append(")");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_CampoPlantilla.Table_Name));
		st.append(" ORDER BY name");
		
		List<MEXMECampoPlantilla> lst = new ArrayList<MEXMECampoPlantilla>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMECampoPlantilla(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Selecciona los nombres, descripcion y valor donde exista una llave y
	 * cumpla con el nivel de acceso ordenado pro nombre
	 * 
	 * @param ctx
	 * @param trxName
	 * @return Listado de objetos nuevos ID=0 del tipo MEXMECampoPlantilla
	 * con nombre, descripcion y valor
	 */
	public static List<MEXMECampoPlantilla> getCamposForma(Properties ctx,
			String trxName) {
		StringBuilder st = new StringBuilder(
				"select name , description,  '${' || value || '}' as value from EXME_CampoPlantilla ");
		st.append(" where EXME_CampoPlantilla_Id IS NOT NULL");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
				.toString(), "EXME_CampoPlantilla"));
		st.append(" order by name");
		List<MEXMECampoPlantilla> lst = new ArrayList<MEXMECampoPlantilla>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMECampoPlantilla aux = new MEXMECampoPlantilla(ctx, 0,
						trxName);
				aux.setName(rs.getString(1));
				aux.setDescription(rs.getString(2));
				aux.setValue(rs.getString(3));
				lst.add(aux);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lst;
	}

	/**
	 * Objeto de la tabla
	 * @return
	 */
	public MTable getTabla() {
		if (tabla == null) {
			tabla = new MTable(getCtx(), getAD_Table_ID(),
					get_TrxName());
		}
		return tabla;
	}

	/**
	 * Obtiene el objeto tabla
	 * @param tabla
	 */
	public void setTabla(MTable tabla) {
		this.tabla = tabla;
	}

	/**
	 * Metodo Principal que ejecuta el inicio del proceso de llenado del ott
	 * layout.
	 * 
	 * @param ctx
	 * @param params
	 * @param paciente
	 * @return
	 * @throws IOException
	 */
	public static Properties generaProperties(Properties ctx,
			List<String> params, int paciente) throws IOException {

		// Obtengo todos los campos de la base de datos
		List<MEXMECampoPlantilla> campos = MEXMECampoPlantilla.getCampos(ctx,
				params);

		for (MEXMECampoPlantilla campo : campos) {

			// Se valida en caso de que ese llave no tenga columna asociada no
			// ejecuta la consulta del campo
			// mvrodriguez Se agrego el metodo getColumnName() para que hiciera
			// la validacion correcta
			if (campo.getAD_Column().getColumnName() != null) {

				StringBuilder st = new StringBuilder();
				st.append("select ").append(
						campo.getAD_Column().getColumnName()).append(" from ")
						.append(campo.getTabla().getTableName());
				st.append(" where EXME_Paciente_ID = ?");
				//
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = DB.prepareStatement(st.toString(), null);
					pstmt.setInt(1, paciente);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						validacionNullo(campo.getValue(), rs.getString(1));
					}
				} catch (Exception e) {
					s_log.log(Level.SEVERE, st.toString(), e);
				} finally {
					DB.close(rs, pstmt);
				}

			}

		}

		// Agrega la Direccion del paciente a las propiedades
		StringBuilder st = new StringBuilder();
		MEXMECampoPlantilla campo = new MEXMECampoPlantilla(ctx, null, null);
		MLocation location = new MLocation(ctx, campo.getPaciente(paciente)
				.getC_Location_ID(), null);
		MRegion region = new MRegion(ctx, location.getC_Region_ID(), null);

		st.append(location.getAddress1()).append(" ").append(
				location.getNumExt()).append(location.getAddress2())
				.append(" ").append(location.getPostal());

		validacionNullo("DirPac", st.toString());

		// Agrega la Ciudad del paciente a las propiedades
		StringBuilder st2 = new StringBuilder();
		st2.append(location.getCity());
		validacionNullo("CityPac", st2.toString());

		// Agrega el Estado del paciente a las propiedades
		StringBuilder st3 = new StringBuilder();
		st3.append(region.getName());
		validacionNullo("RegionPac", st3.toString());

		// Agrega la Fecha actual a las propiedades
		// mvrodriguez Se agrego el paso del parametro ctx
		consultaPropiedades("select sysdate from DUAL", "FechaAct", ctx);
		consultaPropiedades("select to_char("+DB.TO_DATE(DB.getTimestampForOrg(ctx))+", 'dd') from DUAL",
				"DiaAct", ctx);
		consultaPropiedades("select to_char("+DB.TO_DATE(DB.getTimestampForOrg(ctx))+", 'mm') from DUAL",
				"MesAct", ctx);
		consultaPropiedades("select to_char("+DB.TO_DATE(DB.getTimestampForOrg(ctx))+", 'yyyy') from DUAL",
				"AñoAct", ctx);
		consultaPropiedades("select to_char("+DB.TO_DATE(DB.getTimestampForOrg(ctx))+", 'HH24:MI') from DUAL",
				"HoraActual", ctx);
		consultaPropiedades(
				"SELECT VALUE || ' - ' || NOMBRE_MED FROM EXME_MEDICO WHERE EXME_MEDICO.AD_USER_ID = "
						+ Env.getAD_User_ID(ctx), "MedicoUsuario", ctx);

		return props;
	}

	/**
	 * Valida si el campo esta vacio cambia el valor por una cadena "" en las
	 * propiedades
	 * 
	 * @param llave
	 *            Identificador
	 * @param valor
	 *            Valor correspondiente a la llave
	 */
	public static void validacionNullo(String llave, String valor) {

		if (valor == null) {
			props.setProperty(llave, "");
		}
		props.setProperty(llave, valor);
	}

	
	public static void process(Properties props, File fileDoc, File template)
			throws DocumentTemplateException, ConnectException, IOException,
			InvalidReferenceException {

		DocumentTemplate docTemplate = null;

		if (template.isDirectory()) {

			docTemplate = new UnzippedDocumentTemplate(template);

		} else {

			docTemplate = new ZippedDocumentTemplate(template);

		}

		docTemplate.createDocument(props, new FileOutputStream(fileDoc));

	}

	/**
	 * Obtiene todos los registro de la tabla por 
	 * nivel de acceso, considerando la llave como un string
	 * @param ctx
	 * @return
	 * @throws IOException
	 *             //dnuncio - 2010
	 */
	public static ArrayList<String> busquedaEnvioCampos(Properties ctx) throws IOException {

		// Se crea una cadena para la consulta del
		// "catálogo de etiquetas disponibles"
		StringBuilder st = new StringBuilder(
				"SELECT '''' || value || '''' FROM EXME_CampoPlantilla WHERE ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st
				.toString(), "EXME_CampoPlantilla"));

		// Lista para contener resultados de la consulta SQL
		ArrayList<String> lista = new ArrayList<String>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			// Ciclo iterador para el llenado de la lista con los resultados de
			// la consulta SQL
			while (rs.next()) {
				lista.add(rs.getString(1));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lista;

	}

	/**
	 * 
	 * @param Consulta
	 * @param propiedad
	 * @param ctx
	 *            //mvrodriguez Se agrego el parametro de ctx
	 */
	public static void consultaPropiedades(String Consulta, String propiedad,
			Properties ctx) {

		// mvrodriguez Obtenemos el formato correspondiente de aucerdo al idioma
		SimpleDateFormat dateFormat = Constantes.getSDFFechaCortaHora(ctx);

		StringBuilder st4 = new StringBuilder(Consulta);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st4.toString(), null);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				/*
				 * mvrodriguez Se agrego que se evalue el dato sea de tipo para
				 * que cuando sea Timestamp se de el formato a la fecha de
				 * acuerdo al idioma en que se este logueado el usuario
				 */
				props.setProperty(propiedad,
						(rs.getObject(1) instanceof Timestamp ? dateFormat
								.format((Timestamp) rs.getObject(1)).toString()
								: rs.getString(1)));

			} else {
				props.setProperty(propiedad, "");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st4.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

	}

	/**
	 * Obtiene el objeto paciente
	 * 
	 * @param paciente
	 *            Objeto paciente
	 */
	public void setPaciente(MEXMEPaciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * Si el objeto no existe lo crea deacuerdo al parametro
	 * 
	 * @param paciente_id
	 *            ID del paciente
	 * @return Objeto del paciente este puede ser nuevo
	 */
	public MEXMEPaciente getPaciente(int paciente_id) {
		if (paciente == null) {
			paciente = new MEXMEPaciente(getCtx(), paciente_id, get_TrxName());
		}
		return paciente;
	}

	
	/**
	 * Solo el nombre de la variable
	 * @return
	 */
	public String getValueStr(){
		return getValue().substring(2,getValue().length()-1);
	}
	
	/**
	 * Campos plantillas
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MEXMECampoPlantilla> get(Properties ctx,
			String trxName) {

		StringBuilder st = new StringBuilder();
		st.append(" SELECT * FROM EXME_CampoPlantilla ");
		st.append(" WHERE IsActive = 'Y' ");
		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", 
				X_EXME_CampoPlantilla.Table_Name));
		st.append(" ORDER BY name");

		List<MEXMECampoPlantilla> lst = new ArrayList<MEXMECampoPlantilla>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lst.add(new MEXMECampoPlantilla(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return lst;
	}
	
	/**
	 * Buscamos la correspondencia valor/etiqueta
	 * @param ctx
	 * @param sql
	 * @param lvb
	 * @param trxName
	 * @return
	 */
    public static Properties find(Properties ctx, String sql, List <LabelValueBean> lvb, String trxName){
        
         PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()){
				int noColumns = rsmd.getColumnCount();
				for (int i = 0; i < noColumns; i++) {
					
					String value = "";
					if(rs.getObject(i+1)!=null){
					if(rs.getObject(i+1).getClass().equals(String.class)) 
						value = rs.getString(i+1);
					else 
						if(rs.getObject(i+1) instanceof Integer) 
							value = String.valueOf(rs.getInt(i+1));
						else
							if(rs.getObject(i+1).getClass().equals(Timestamp.class)) 
								value = Constantes.sdfFechaHora(ctx).format(rs.getTimestamp(i+1));
							else
								if(rs.getBigDecimal(i+1).getClass().equals(BigDecimal.class)) 
									value = String.valueOf(rs.getBigDecimal(i+1));
					}
					
					String label = null;
					if(lvb==null) {
						//TODO: Validar el tipo
						label = rsmd.getColumnName(i);
					} else {
						if(noColumns==lvb.size() && lvb.get(i).getValue().equals(String.valueOf(i+1))) {
							label = lvb.get(i).getLabel();
						}
					}
					
					validacionNullo(label, value);
				}
			}
			
        }catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		
		return props;
	}
    
    
	/**
	 * cuestionario
	 * @param folio
	 * @param cuentaPacID
	 * @param cuestionarioID
	 */
    public static Properties find(int folio, int cuentaPacID, int cuestionarioID){
		MEXMECtaPac ctaPaciente = new MEXMECtaPac(Env.getCtx(), cuentaPacID, null);
		
		List<MEXMEActPacienteDet> lstObject = MEXMEActPacienteDet.getSQLPlantilla(Env.getCtx(), 
				ctaPaciente.getEXME_Paciente_ID(), ctaPaciente.getEXME_CtaPac_ID(), 
				folio, cuestionarioID, null);
		
		for (int i = 0; i < lstObject.size(); i++) {
			validacionNullo(
					lstObject.get(i).getCampoPlantillaValue(), lstObject.get(i).getRespuesta());	
		}
		validacionNullo("table_insert","");
		return props;
	}
    
	public MColumn getColumn() {
		if (this.column == null) {
			this.column = new MColumn(getCtx(), getAD_Column_ID(), get_TrxName());
		}
		return this.column;
	}

	public void setColumn(MColumn column) {
		this.column = column;
	}
}