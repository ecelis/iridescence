package org.compiere.model;

//import ij.ImagePlus;

import ij.io.TiffEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;
import org.compiere.util.mo.MO_Radiografias_VO;

/**
 * Creado:12/Junio/2009<p>
 * @author Lizeth de la Garza
 */
public class MEXMERadiografias extends X_EXME_MO_Radiografias {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private static CLogger	s_log = CLogger.getCLogger (MEXMERadiografias.class);

	//Directorio donde se guardan las imagenes
	private String RadiografiaFilePath = "";

	//Nombre del directorio con la carpeta temporal
	private String directoryName = null;

	//Nombre del archivo donde se guardan los nombres de las imagenes
	private String imageFileName = "nombreImagenes";

	//sufijo del url para la carpeta temporal de imagenes
	private String urlTemporal = null;

	//nombre dela carpeta de las imagenes en web
	private String webFolderName = "radiografias";

	//sufijo del url para el archivo csv con el nombre de las imagenes
	private String urlArchivoCSV = null;

	public String getUrlArchivoCSV() {
		return urlArchivoCSV;
	}

	public void setUrlArchivoCSV(String urlArchivoCSV) {
		this.urlArchivoCSV = urlArchivoCSV;
	}

	public String getWebFolderName() {
		return webFolderName;
	}

	public void setWebFolderName(String webFolderName) {
		this.webFolderName = webFolderName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getUrlTemporal() {
		return urlTemporal;
	}

	public void setUrlTemporal(String urlTemporal) {
		this.urlTemporal = urlTemporal;
	}

	public  MEXMERadiografias(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
	        super(ctx, EXME_CitaMedica_ID, trxName);
	    }

	    /**
	     * @param ctx
	     * @param rs
	     * @param trxName
	     */
	    public  MEXMERadiografias(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }


		/**
		 * Liz de la Garza
		 * Regresa el nombre del archivo
		 * @param FormFile file(archivo adjunto)
		 * @return String filename(nombre del archivo)
		 */

	    public static String crearNombreArchivo(FormFile file) {
			String fileName = null;

			if (file.getFileSize() > 0) {
	            //extraemos la extension del archivo
	            String extension = "";
	            int pos = file.getFileName().indexOf('.');
	            if (pos > -1)
	                extension = file.getFileName().substring(pos + 1, file.getFileName().length());
	            fileName = file.getFileName().substring(0, pos);

	            //La extension en minusculas
	            extension = extension.toLowerCase();

	            // nombre del archivo
	            fileName = fileName + "." + extension;

	        }

			return fileName;
		}

	    /**
		 * Liz de la Garza
		 * Insertar en la tabla los datos ingresados
		 * @param Properties ctx
		 * @param InputStream image
		 * @param int pacienteID
		 * @param String trxName
		 * @param int lenght (tamaño del archivo)
		 * @param String fileName
		 * @param int citaID
		 * @param String notas
		 * @return radiografiaID
		 */
	    public static synchronized int saveRadiografias(Properties ctx, InputStream image, int pacienteID
	    		, String trxName, int lenght, String fileName, int citaID, String notas) throws Exception {

	    	int radioID = 0;

	    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	sql.append(" INSERT INTO EXME_MO_Radiografias ( ")
	    	.append(" EXME_MO_Radiografias_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, ")
	    	.append(" CreatedBy, Updated, UpdatedBy, Name, EXME_Paciente_ID,Imagen, Nota ");

	    	if (citaID != 0)
	    		sql.append(" ,EXME_CitaMedica_ID");

	    	sql.append(" )");

	    	sql.append(" VALUES (?,?,?,'Y',SYSDATE,?,SYSDATE,?,?,?,?, ?");

	    	if (citaID != 0)
	    		sql.append(" ,").append(citaID);

	    	sql.append(" )");

	    	PreparedStatement pstmt = null;

	    	try {
	    		pstmt = DB.prepareStatement(sql.toString(), trxName);
	    		//Obtener el siguiente id del registro
	    		radioID = MSequence.getNextID(Env.getAD_Client_ID(ctx), "EXME_MO_Radiografias", null);

	    		pstmt.setLong(1, radioID);
	    		pstmt.setLong(2, Env.getContextAsInt(ctx, "#AD_Client_ID"));
	    		pstmt.setLong(3, Env.getContextAsInt(ctx, "#AD_Org_ID"));
	    		pstmt.setLong(4, Env.getContextAsInt(ctx, "#AD_User_ID"));
	    		pstmt.setLong(5, Env.getContextAsInt(ctx, "#AD_User_ID"));
	    		pstmt.setString(6, fileName);
	    		pstmt.setInt(7, pacienteID);
	    		//Inserci�n de la imagen en la bd
	    		pstmt.setBinaryStream(8, image, lenght);
	    		pstmt.setString(9, notas);
	    		pstmt.executeUpdate();


	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, "deleteTemptable", e);
	    		DB.rollback(true, trxName);
	    		radioID = 0;
	    	} finally {
	    		DB.close(pstmt);

	    		pstmt = null;

	    	}
	    	return radioID;
	    }
	    /**
		 * Liz de la Garza
		 * Lista de las radiograf�as asociadas al paciente
		 * @param Properties ctx
		 * @param int pacienteID
		 * @param String anio
		 * @param String mes
		 * @param int citaID
		 * @param int tratamientoID
		 * @return List<MO_Radiografias_VO>
		 */


	    public static List<MO_Radiografias_VO> getRadiografiasPac(Properties ctx,  int paciente_id, String anio, String mes,
	    		int citaID, int tratamientoID){
	    	List<MO_Radiografias_VO> lista = new ArrayList<MO_Radiografias_VO>();

	    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	    	sql.append("SELECT radio.EXME_MO_Radiografias_ID AS RadiografiaID, radio.name AS Nombre, radio.Nota");

	    		sql.append(" , tratam.Name AS NombreTratam, tratampac.exme_tratamientospaciente_id AS tratamPacID")
	    		.append(",TO_CHAR(tratampac.Fecha_Tratamiento,'dd/MM/yyyy')AS Fecha,  tratampac.Description, tratampac.EXME_Tratamientos_ID as tratamID");
	    	sql.append(" FROM EXME_MO_Radiografias radio ");

	    	sql.append(" LEFT JOIN EXME_MO_RadiografiaDet radiodet ON(radiodet.EXME_MO_Radiografias_ID = radio.EXME_MO_Radiografias_ID) ")
	    		.append(" LEFT JOIN EXME_TratamientosPaciente tratampac ON(tratampac.exme_tratamientospaciente_id ")
	    		.append("= radiodet.exme_tratamientospaciente_id)")
	    		.append(" LEFT JOIN EXME_Tratamientos tratam ON(tratampac.exme_tratamientos_id= tratam.exme_tratamientos_id)");
	    		

	    	sql.append(" WHERE radio.EXME_Paciente_ID = ? ");
	    	if (citaID != 0)
	    		sql.append(" AND radio.EXME_CitaMedica_ID = ").append(citaID);

	    	if (tratamientoID != 0)
    			sql.append(" AND radiodet.EXME_TratamientosPaciente_ID = ").append(tratamientoID);

	    	if (tratamientoID != 0){	 //Si es por tratramiento, buscarlo por la fecha de indicacion de este

	    			sql.append(" AND EXTRACT(MONTH FROM tratampac.Fecha_Tratamiento) = ").append(mes)
		    		.append(" AND EXTRACT(YEAR FROM tratampac.Fecha_Tratamiento) = ").append(anio);

	    	} else { //Buscar por la fecha de creaci�n del registro de la radiografia en la bd
	    		sql.append(" AND EXTRACT(MONTH FROM radio.Created) = ").append(mes)
	    		.append(" AND EXTRACT(YEAR FROM radio.Created) = ").append(anio);
	    	}
	    	sql.append(" ORDER BY RADIO.Name, tratam.name");

	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	try {
	    		pstmt = DB.prepareStatement(sql.toString(), null);
	    		pstmt.setInt(1, paciente_id);

	    		//Obtenemos la informacion de las radiografias y el nombre del tratamiento en caso de que este asignada a uno
	    		rs = pstmt.executeQuery();

	    		while (rs.next()) {

	    			MO_Radiografias_VO radio = new MO_Radiografias_VO();
	    			radio.setRadioID(rs.getInt("RadiografiaID"));
	    			radio.setNombreRadio(rs.getString("Nombre"));
	    			radio.setNota(rs.getString("Nota"));

	    				radio.setNombreTratam(rs.getString("NombreTratam"));
	    				radio.setTratamPacID(rs.getInt("tratamPacID"));
	    				radio.setFecha(rs.getString("Fecha"));
	    				radio.setDescriptionTratam(rs.getString("Description"));
	    				radio.setTratamientoID(rs.getInt("tratamID"));


	    			lista.add(radio);
	    		}
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, "Error al obtener la lista de radiograf�as", e);
	    	} finally {
	    		DB.close(rs, pstmt);
	    	}

	    	return lista;
	    }

	    /**
		 * Liz de la Garza
		 * Eliminar las radiografias y su detalle de la bd
		 * @param Properties ctx
		 * @param int radiografiaID
		 * @param String trxName
		 * @return ActionErrors errores
		 */

	    public static synchronized ActionErrors deleteRadiografias(Properties ctx,  int radiografiaID , String trxName) throws Exception {

	    	ActionErrors errores = new ActionErrors();

	    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	sql.append(" DELETE FROM EXME_MO_Radiografias  ")
	    	.append(" WHERE EXME_MO_Radiografias_ID = ?");


	    	PreparedStatement pstmt = null;

	    	try {
	    		pstmt = DB.prepareStatement(sql.toString(), trxName);
	    		pstmt.setInt(1, radiografiaID);

	    		pstmt.executeUpdate();
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, "deleteRadio", e);
	    		errores.add("deleteRadiografia", new ActionError("odontologia.error.EliminaRadiografia"));
	    		
	    	} finally {
	    		DB.close(pstmt);
	    		pstmt = null;

	    	}
	    	return errores;
	    }

	    /**
		 * Liz de la Garza
		 * Convertir el dato BLOB en archivo temporal
		 * @param Properties ctx
		 * @param int pacienteID
		 * @param int citaID
		 * @param int tratamID (tratamientoPaciente asociado)
		 * @param String path (ruta del archivo)
		 * @param String trxName
		 * @param String mes
		 * @param String anio
		 * @return ActionErrors
		 */

	    public ActionErrors recuperarBLOB(Properties ctx, int pacienteID, int citaID, int tratamID, String trxName,
	    		String mes, String anio, String url) throws SQLException, IOException {
	    	ActionErrors errores = new ActionErrors();
	        FileOutputStream fos = null;
	        PreparedStatement pstmt = null;
	        String pathName = null;
	        ResultSet rs = null;
	        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	        String env = System.getProperty("COMPIERE_HOME");
	        Properties properties = new Properties();
	        properties = WebEnv.getXPT_Properties();
	        //Obtenemos del PATH.Properties el directorio donde se creara la carpeta temporal de las imagenes
	        RadiografiaFilePath = env + File.separator + properties.getProperty("RadiografiaFilePath");
	        //Nombre de la carpeta en web
	        urlTemporal = url + webFolderName + "/" + Env.getContextAsInt(ctx, "#AD_Session_ID");
	        //Nombre de la carpeta del archivo .csv de nombre de imagenes
	        urlArchivoCSV = urlTemporal + "/" + imageFileName;
	        pathName = RadiografiaFilePath + File.separator + Env.getContextAsInt(ctx, "#AD_Session_ID");
	        setDirectoryName(pathName + File.separator);

	        //Creamos la carpeta del usuario que contiene las imagenes
	        File directory = new File(pathName);
	        directory.mkdir();
	       // if(!directory.mkdir()){
	        	/*errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("odontologia.error.Archivo"));
	        	return errores;
	        }*/
	        directory.deleteOnExit();

	        sql.append("SELECT radio.EXME_MO_Radiografias_ID AS radioID, radio.Name, radio.Imagen FROM EXME_MO_Radiografias radio");

	        if (tratamID != 0) {
	        	sql.append(" INNER JOIN EXME_MO_RAdiografiaDet radiodet ON (radiodet.exme_mo_radiografias_id = radio.exme_mo_radiografias_id)")
	        	.append(" INNER JOIN EXME_TRATAMIENTOSPACIENTE tratam ON(radiodet.exme_tratamientospaciente_id= tratam.exme_tratamientospaciente_id)");

	        }
	        sql.append(" WHERE radio.EXME_Paciente_ID = ? ");
	        if (citaID != 0)
	        	sql.append(" AND radio.EXME_CitaMedica_ID = ").append(citaID);
	        if (tratamID != 0) {
	        	sql.append(" AND radiodet.EXME_TratamientosPaciente_ID =").append(tratamID)
	        	.append(" AND EXTRACT(MONTH FROM tratam.Fecha_Tratamiento) = ?")
	        	.append(" AND EXTRACT(YEAR FROM tratam.Fecha_Tratamiento) = ?");
	        } else {
	        	sql.append("  AND EXTRACT(MONTH FROM radio.Created) = ? ")
	        	.append(" AND EXTRACT(YEAR FROM radio.Created) =  ?");
	   	 }
	        sql.append(" ORDER BY radio.Name");

	        	pstmt = DB.prepareStatement(sql.toString(), trxName);
	        	pstmt.setInt(1, pacienteID);
	        	pstmt.setString(2, mes);
	        	pstmt.setString(3, anio);
	        	rs = pstmt.executeQuery();

	        	//Crear el archivo csv que contiene el nombre de las im�agenes
	        	CSVWriter writer = new CSVWriter(new FileWriter(directoryName + imageFileName));
	        	List <String[]> listadoNombres = new ArrayList<String[]>();
	            while (rs.next()) {

	            	String name = rs.getString("Name");
	            	int id = rs.getInt("radioID");

	            	String extension = crearExtension(name);
	            	String ini = StringUtils.substringBeforeLast(name, ".");
	            	//ini = "tmp" + ini;
	            	ini = id+ "_" + ini;

	            	//Creaci�n de las imagenes como archivos temporales
	            	//Lizeth de la Garza- Creamos el nombre de la imagen con su id y el nombre en la bd
	            	File file = File.createTempFile(ini, extension, directory);
	            	file.deleteOnExit();
	                fos = new FileOutputStream(file.getPath());

	                //Obtenci�n del registro binario de la imagen de la bd
	                Blob bin = rs.getBlob("Imagen");
	                InputStream inStream = bin.getBinaryStream();

	                pathName = directory + File.separator + name;

	                int size = (int) bin.length();
	                byte[] buffer = new byte[size];
	                int length = -1;
	                //Crear la imagen a partir de los binarios
	                while ((length = inStream.read(buffer)) != -1) {
	                  fos.write(buffer, 0, length);
	                }

	                //Agregamos el nombre del archivo, la ruta fisica de la carpeta temporal y el id del registro donde esta la imagen
	                String [] par = {name, file.getName(), String.valueOf(id)};
	                listadoNombres.add(par);
	            }
	            writer.writeAll(listadoNombres);
	            writer.close();


	            if (fos != null)
	                fos.close();
	            DB.close(rs, pstmt);
	            rs = null;
	            pstmt = null;

	        return errores;
	    }

	    /**
		 * Liz de la Garza
		 * Obtener la extension del archivo
		 * @param String fileName
		 * @return String
		 */

	    public static String crearExtension(String fileName){
	    	//extraemos la extension del archivo
	    	String extension = "";
	    	int pos = fileName.indexOf('.');
	    	if (pos > -1)
	    		extension = fileName.substring(pos, fileName.length());
	    	return extension;
	    }

	    /**
		 * Liz de la Garza
		 * Eliminar los archivos temporales de la carpeta de la sesion
		 * @param Properties ctx
		 * @param String path
		 * @return boolean
		 */
	    public static boolean deleteDirectory(Properties ctx, String path) throws Exception {
	    	boolean success = true;

	    	try {//Borrar la carpeta del usuario con todos los archivos que incluye
	    		File file = new File(path);
	    		if (file.exists()) {
	    			File[] files = file.listFiles();
	    			for (int i = 0; i < files.length; i++) {
	    				success = files[i].delete();
	    				if (!success)
	    					return success;
	    			}
	    			success = file.delete();
	    			if (!success)
    					return success;
	    		}
	    	} catch (Exception e) {
	    		success = false;//FIXME

	    	}
	    	return success;
	    }
	    
	    /**
		 * Liz de la Garza
		 * Actualizar radiografia (Usado desde Applet)
		 * @param String trxName - nombre de la transaccion
		 * @param Tiff Encoder image - Objeto con la imagen y sus propiedades e informacion
		 * @return 
		 */
	    public static void updateRadiografias(TiffEncoder image, String trxName) throws Exception {

	    	BLOB blob;  
	    	OutputStream outstream;
	    	int radiografiaID = image.getRadiografiaID();//ID de la radiografia

	    	//Lizeth de la Garza- Borramos solo la imagen para el registro
	    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	sql.append(" UPDATE EXME_MO_Radiografias SET imagen = empty_blob()  ")
	    	.append(" WHERE EXME_MO_Radiografias_ID =  ? ");
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;

	    	try {

	    		pstmt = DB.prepareStatement(sql.toString(), trxName);
	    		pstmt.setInt(1, radiografiaID);
	    		pstmt.executeUpdate();
	    		pstmt = null;

	    		//Lizeth de la Garza- Seleccionamos el nombre de la imagen y la imagen para realizar una
	    		//actualizacion sobre ella
	    		sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		sql.append(" SELECT Name, Imagen FROM EXME_MO_Radiografias  ")
	    		.append(" WHERE EXME_MO_Radiografias_ID = ? FOR UPDATE ");	


	    		Connection conn =  DB.getConnectionID();
	    		conn.setAutoCommit(false);//requiere ser autocommit false**
	    		pstmt = conn.prepareStatement(sql.toString());

	    		pstmt.setInt(1, radiografiaID);
	    		//Inserci�n de la imagen en la bd


	    		rs = pstmt.executeQuery();
	    		String name = null;

	    		if (rs.next()) {

	    			name = rs.getString(1);
	    			name = StringUtils.substringBeforeLast(name, ".");
	    			name = name + ".tif";//Las imagenes son convertidas a extension .tif al guardarlas desde el Applet

	    			blob = ((OracleResultSet)rs).getBLOB(2);	    				
	    			blob.open(BLOB.MODE_READWRITE);//Obtenemos el campo BLOB de la BD y se escribira sobre ella
	    			outstream = blob.setBinaryStream(0); 	 
	    			image.write(outstream);//Se escribe la imagen sobre el flujo de salida
	    			outstream.flush();
	    			outstream.close();
	    			blob.close();//Cierre del BLOB (actualizacion del registro)
	    			conn.close();		

	    		}
	    		pstmt = null;

	    		sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		sql.append(" UPDATE EXME_MO_Radiografias  ")//Actualizamos el nombre de la imagen con la extension .tif
	    		.append(" SET Name = ? WHERE EXME_MO_Radiografias_ID = ? ");

	    		pstmt = DB.prepareStatement(sql.toString(), trxName);
	    		pstmt.setString(1, name);
	    		pstmt.setInt(2, radiografiaID);
	    		pstmt.executeUpdate();


	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, "updateBlob", e);    		

	    	} finally {
	    		DB.close(rs, pstmt);
	    		rs = null; 
	    		pstmt = null;
	    	}
	    }
	    /**
	     * Liz de la Garza- Metodo que busca dentro de un directorio las imagenes
	     * relacionadas al paciente coincidentes a un criterio de busqueda
	     * Soporta el comod�n de busqueda "%"
	     * @param nombrePaciente
	     * @return List<LabelValueBean>
	     */
	    
	    public static List<LabelValueBean> getImagenesPac(String criterio){

	    	List<LabelValueBean> listImagenPac = new ArrayList<LabelValueBean>();
	    	boolean starts = true;//Empieza con...
	    	boolean ends = true;//Termina con...
	    	//Si empieza con %, habr� un String antes del criterio de busqueda
	    	if(criterio.startsWith("%")) {
	    		criterio = StringUtils.removeStart(criterio, "%");
	    		starts = false;
	    	}

	    	//Si termina con %, habr� un String despu�s del criterio de busqueda
	    	if(criterio.endsWith("%")) {
	    		criterio = StringUtils.removeEnd(criterio, "%");
	    		ends = false;
	    	}

	    	//Si hay signos de % intermedios en el String, separarlo
	    	String[] criterios = StringUtils.split(criterio, "%");
	    	String env = System.getProperty("COMPIERE_HOME");
	    	String propPath = env + File.separator + "CompiereEnv.properties";
	    	Properties properties = new Properties();
	    	try {
	    		properties.load(new FileInputStream(propPath));
	    		//Liz de la Garza-Directorio definido de las imagenes del paciente
	    		String path = properties.getProperty("MEDSYS_PATH_STOMATOLOGY");
	    		File directory = new File (path);

	    		if (directory.exists()) {
	    			File[] files = directory.listFiles();
	    			if (files.length > 0 && criterios.length > 0) {
	    				for (int i = 0; i < files.length; i++) {
	    					//Liz de la Garza-Validaci�n de la extensi�n del archivo de la lista
	    					String extension = StringUtils.substringAfterLast(files[i].getName(), ".").toUpperCase();	    					
	    					if (extension.equals("JPG") || extension.equals("GIF") || extension.equals("BMP")
	    							|| extension.equals("PNG") || extension.equals("TIF") || extension.equals("DCM")) {
	    						boolean success = false;//Indica si encontro una coincidencia
	    						String aux = null;//Auxiliar de cadena para comparaciones

	    						String file = StringUtils.substringBeforeLast(files[i].getName().trim().toUpperCase(), ".");
	    						//Solo hay un String en el array
	    						if (criterios.length == 1) {
	    							if (starts && ends) {//x
	    								if (file.equals(criterios[0]))
	    									success = true;
	    							} else if (!starts && ends) {//%x
	    								if (file.endsWith(criterios[0]))
	    									success = true;
	    							} else if (starts && !ends) {//x%
	    								if (file.startsWith(criterios[0]))
	    									success = true;
	    							} else if (!starts && !ends){//%x%
	    								if (StringUtils.contains(file, criterios[0])) 
	    									success = true;    						
	    							}
	    							//Dos Strings en el array
	    						} else if (criterios.length == 2) {
	    							if (starts && ends) {//x%y
	    								if (file.startsWith(criterios[0])) {
	    									aux = StringUtils.removeStart(file, criterios[0]);
	    									if (aux.endsWith(criterios[1]))
	    										success = true;
	    								}
	    							} else if (!starts && ends) {//%x%y
	    								if (file.endsWith(criterios[1])) {
	    									aux = StringUtils.removeEnd(file, criterios[1]);
	    									if (StringUtils.contains(aux, criterios[0]))
	    										success = true;
	    								}	
	    							} else if (starts && !ends) {//x%y%
	    								if (file.startsWith(criterios[0])) {
	    									aux = StringUtils.removeStart(file, criterios[0]);
	    									if (StringUtils.contains(aux, criterios[1]))
	    										success = true;	    							
	    								}
	    							} else if (!starts && !ends){//%x%y%
	    								if (StringUtils.contains(file, criterios[0])) { 
	    									int index = StringUtils.indexOf(file, criterios[0]);
	    									aux = file.substring(index + 1 + criterios[0].length());
	    									if (StringUtils.contains(aux, criterios[1]))
	    										success = true;
	    								}

	    							}		    				
	    						} else {//3 o m�s Strings en el array
	    							if (starts && ends) {//w%x%y%z
	    								if (file.startsWith(criterios[0])) {//Si empieza con..
	    									aux = StringUtils.removeStart(file, criterios[0]);
	    									if (aux.endsWith(criterios[criterios.length - 1])) {
	    										aux = StringUtils.removeEnd(file, criterios[criterios.length - 1]);
	    										for (int j = 1; j < (criterios.length - 1); j++) {
	    											if (StringUtils.contains(aux, criterios[j])) {
	    												if (j == criterios.length - 2) {//Si llegamos al final de los Strings a comparar								
	    													success = true;
	    													break;
	    												}
	    												int index = StringUtils.indexOf(aux, criterios[j]);
	    												//nueva cadena a comparar
	    												aux = file.substring(index + 1 + criterios[j].length());      											
	    											}
	    										}    										
	    									}
	    								}
	    							} else if (!starts && ends) {//%w%x%y%z
	    								if (file.endsWith(criterios[criterios.length - 1])) {//Si termina con...	    									
	    									aux = StringUtils.removeEnd(file, criterios[criterios.length - 1]);
	    									for (int j = 0; j < (criterios.length - 1); j++) {
	    										if (StringUtils.contains(aux, criterios[j])) {
	    											if (j == criterios.length - 2) {//Si llegamos al final de los Strings a comparar								
	    												success = true;
	    												break;
	    											}
	    											int index = StringUtils.indexOf(aux, criterios[j]);
	    											//nueva cadena a comparar
	    											aux = file.substring(index + 1 + criterios[j].length());   											
	    										}
	    									}
	    								}	
	    							} else if (starts && !ends) {//w%x%y%z%
	    								if (file.startsWith(criterios[0])) {
	    									aux = StringUtils.removeStart(file, criterios[0]);
	    									for (int j = 1; j < criterios.length ; j++) {
	    										if (StringUtils.contains(aux, criterios[j])) {
	    											if (j == criterios.length - 1) {//Si llegamos al final de los Strings a comparar								
	    												success = true;
	    												break;
	    											}
	    											int index = StringUtils.indexOf(aux, criterios[j]);
	    											//nueva cadena a comparar
	    											aux = file.substring(index + 1 + criterios[j].length());     											
	    										}
	    									} 
	    								}
	    							} else if (!starts && !ends){//%w%x%y%z%
	    								aux = file;
	    								for (int j = 0; j < criterios.length ; j++) {	    								
	    									if (StringUtils.contains(aux, criterios[j])) {
	    										if (j == criterios.length - 1) {//Si llegamos al final de los Strings a comparar								
	    											success = true;
	    											break;
	    										}
	    										int index = StringUtils.indexOf(aux, criterios[j]);
	    										//nueva cadena a comparar
	    										aux = file.substring(index + 1 + criterios[j].length());     											
	    									}
	    								}
	    							}	    					
	    						}	    					
	    						if (success)//Agregar el archivo a la lista de coincidencias
	    							listImagenPac.add(new LabelValueBean(files[i].getName(), files[i].getName()));
	    					}
	    				}
	    			}
	    		}
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, "getImagenesPac()", e);    		
	    	}
	    	return listImagenPac;
	    }

	    
}
