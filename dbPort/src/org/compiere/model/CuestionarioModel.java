package org.compiere.model;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Utilerias;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;

/**
 * 
 * @author Lorena Lama
 *
 */
public class CuestionarioModel {
	
	private static CLogger s_log = CLogger.getCLogger(CuestionarioModel.class);

	// Directorio donde se guardan las imagenes
	private String ImagenFilePath = "";

	// Nombre del directorio con la carpeta temporal
	private String directoryName = null;

	// Nombre del archivo donde se guardan los nombres de las imagenes
	private String imageFileName = "nombreImagenes";

	// sufijo del url para la carpeta temporal de imagenes
	private String urlTemporal = null;

	// nombre dela carpeta de las imagenes en web
	private String webFolderName = "cuestionario";

	// sufijo del url para el archivo csv con el nombre de las imagenes
	private String urlArchivoCSV = null;

	public final static int t_attach = 0;

	public final static int t_actPac = 1;

	public final static int t_cuest = 2;
	
	public final static String IMGCuestFilePath = "IMGCuestFilePath";

	/**
	 * Carga las imagenes de la base de datos en la ruta de Img-cuestionario para la session del usuario
	 * (edicion de imagenes fijas en cuestionario)
	 * @param ctx
	 * @param url
	 */
	public CuestionarioModel(Properties ctx, String url) {

		/*String env = System.getProperty("COMPIERE_HOME");
		Properties properties = new Properties();
		properties = WebEnv.getXPT_Properties();
		ImagenFilePath = env + File.separator + properties.getProperty("IMGCuestFilePath");*/
		
		// Obtenemos del PATH.Properties el directorio donde se creara la carpeta temporal de las imagenes
		ImagenFilePath = Ini.getCompiereHome() + File.separator + Utilerias.getPropertiesFromXPT(IMGCuestFilePath);

		// Nombre de la carpeta en web
		urlTemporal = url + webFolderName + '/' + Env.getAD_Session_ID(ctx);

		// Nombre de la carpeta del archivo .csv de nombre de imagenes
		urlArchivoCSV = urlTemporal +  '/'  + imageFileName;
		String pathName = ImagenFilePath + File.separator + Env.getAD_Session_ID(ctx);

		setDirectoryName(pathName);
	}
	
	/**
	 * Carga las imagenes de la base de datos en una ruta definida
	 * @param urlImagenes
	 */
	public CuestionarioModel(String urlImagenes) {
		//String env = System.getProperty("COMPIERE_HOME");
		// Obtenemos del PATH.Properties el directorio donde se creara la carpeta temporal de las imagenes
		ImagenFilePath =  urlImagenes;
		// Nombre de la carpeta en web
		urlTemporal = ImagenFilePath;
		setDirectoryName(ImagenFilePath);
	}
	
	
	/**
	 * Convertir el dato BLOB en archivo temporal
	 * @param ctx
	 * @param preguntaID - Id de la pregunta
	 * @param cuestionarioID - Id del cuestionario
	 * @param referenceID - Id del paciente o Act Paciente
	 * @param folio - folio de la nota / cita
	 * @param table_Name - tabla de donde se obtienendra la imagen (attachment, t_cuest, actPacienteDet)
	 * @param trxName
	 * @param isTemp - false: convierte las imagenes tiff en .png (solo consulta)  
	 * @author Liz de la Garza / Lorena Lama
	 * @return
	 */
	public boolean recuperarBLOB(Properties ctx, int preguntaID, int cuestionarioID, 
			int referenceID, String folio, int table_Name, String trxName,
			boolean isTemp) {
		return recuperarBLOB(
				ctx, 
				preguntaID, 
				cuestionarioID, 
				referenceID, 
				folio, 
				table_Name, 
				trxName, 
				isTemp, 
				false
		);
	}
	
	/**
	 * Convertir los datos BLOB existentes en la base de datos en archivo temporal
	 * (no editables)
	 * @param ctx
	 * @param preguntaID - Id de la pregunta
	 * @param cuestionarioID - Id del cuestionario
	 * @param referenceID - Id del paciente o Act Paciente
	 * @param table_Name - tabla de donde se obtienendra la imagen (attachment, t_cuest, actPacienteDet)
	 * @param folio - folio de la nota / cita
	 * @author Liz de la Garza / Lorena Lama
	 * @return
	 */
	public boolean recuperarBLOB(Properties ctx, int preguntaID, int cuestionarioID, int referenceID,  int table_Name, String folio) {
		return recuperarBLOB(ctx, preguntaID, cuestionarioID, referenceID, folio, table_Name, null, false, true);
	}

	/**
	 * Convertir el dato BLOB en archivo temporal
	 * @param ctx
	 * @param preguntaID - Id de la pregunta
	 * @param cuestionarioID - Id del cuestionario
	 * @param referenceID - Id del paciente o Act Paciente
	 * @param folio - folio de la nota / cita
	 * @param table_Name - tabla de donde se obtienendra la imagen (attachment, t_cuest, actPacienteDet)
	 * @param trxName
	 * @param isTemp - true: convierte las imagenes tiff en .png (solo consulta) 
	 * @param readOnly - true: Descarga solo la imagenes, sin crear el csv que requiere el applet para modificarlas
	 * @author Liz de la Garza / Lorena Lama
	 * @return
	 */
	public boolean recuperarBLOB(Properties ctx, int preguntaID, int cuestionarioID, int referenceID, String folio, int table_Name, String trxName,
			boolean isTemp, boolean readOnly) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String tableName = null;

			switch (table_Name) {
			case t_attach:
				tableName = MAttachment.Table_Name;
				break;
			case t_actPac:
				tableName = MEXMEActPacienteDet.Table_Name;
				break;
			case t_cuest:
				tableName = MEXMETCuest.Table_Name;
				break;
			}

			if (tableName == null)
				return false;

			List<String[]> lstImgs = new ArrayList<String[]>();
			File archivoBlob = null;
			CSVWriter writer = null;
			File file_csv = null;
			File directory = null;

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			// Creamos la carpeta del usuario que contiene las imagenes
			directory = new File(getDirectoryName());
			if(!directory.exists()) {
				directory.mkdir();
				directory.deleteOnExit(); // solo la tmp de cuestionarios ?
			}

			if (!readOnly) {
				file_csv = new File(getDirectoryName() + File.separator + imageFileName);

				if (file_csv.exists())
					this.add(lstImgs, file_csv);

				// Crear el archivo csv que contiene el nombre de las imagenes
				writer = new CSVWriter(new FileWriter(file_csv));
			}

			// Armamos el SQL
			this.getSQL(tableName, table_Name, sql, preguntaID, cuestionarioID, folio);

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int i = 1;
			if (table_Name == t_attach) {
				// table id 1, preg 2
				pstmt.setInt(i++, MPregunta.Table_ID);
			}
			else {
				// reference id = 1, folio 2
				pstmt.setInt(i++, referenceID);
				if (folio != null)
					pstmt.setString(i++, folio);
			}
			// cuest 3
			if (cuestionarioID >= 0)
				pstmt.setInt(i++, cuestionarioID);
			// si tiene preg.
			if (preguntaID > 0)
				pstmt.setInt(i++, preguntaID);
			rs = pstmt.executeQuery();

			// tomamos solo el ultimo .- Attachment
			long attacPreg = 0;

			while (rs.next()) {
				int regID = 0;
				if(table_Name != t_attach)
					regID = rs.getInt("regID");
				int pregID = rs.getInt("pregID");
				int cuestID = rs.getInt("cuestID");// si lo envio de un pregunta sin cuest, no poner cuestID
				int secuencia = rs.getInt("secuencia");
				String name = rs.getString("p_name");

				// solo la ultima imagen
				if (attacPreg == pregID)
					continue;

				String img_name = null;
				int size = 0;
				InputStream inputStr = null;
				if (table_Name == t_attach) {
					// cargamos de la bd los binary image
					MAttachment attachment = new MAttachment(ctx, rs, null);
					MAttachmentEntry[] entries = attachment.getEntries();
					archivoBlob = entries[0].getFile();

					if (archivoBlob == null)
						continue;

					img_name = archivoBlob.getName();
					// bin = rs.getBlob("BinaryData");
					ByteArrayInputStream byteInput = new ByteArrayInputStream(entries[0].getData());
					inputStr = (InputStream) byteInput;

					size = entries[0].getData().length;

					if (byteInput != null)
						byteInput.close();
				}
				else {
					// Temporal de cuestionarios o actividad del pacientes (solo lectura)
					img_name = rs.getString("Name");
					Blob bin = rs.getBlob("Image");

					inputStr = bin.getBinaryStream();
					size = (int) bin.length();
					if(readOnly) // agrega el Id del registro como identificados unico
						img_name = regID + img_name;
				}
				// 
				this.createIMGTemp(pregID, cuestID, secuencia, img_name, name, directory, inputStr, size, lstImgs, isTemp);

				// usar ultimo attachment por pregunta
				if (table_Name == t_attach && attacPreg == 0)
					attacPreg = pregID;
				if (inputStr != null)
					inputStr.close();
			}

			if (writer != null) {
				writer.writeAll(lstImgs);
				writer.close();
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "While recovering BLOB ", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "While recovering BLOB  - closing pstmt, rs ", e);
			}
			rs = null;
			pstmt = null;
		}

		return true;
	}
	
	/**
	 * Busqueda de imagenes fijas, desde attachment, temporal de cuestionarios o ActPacienteDet
	 * @param tableName
	 * @param table_Name
	 * @param sql
	 * @deprecated, ya no se utiliza, debido a los nuevos cuestionarios. No se corrige rownum. Jesus Cantu.
	 */
	private void getSQL(String tableName, int table_Name, StringBuilder sql, int pregID, int cuestionarioID, String folio) {

		if (table_Name == t_attach) {
			sql.append("SELECT AD_Attachment.*, nvl(EXME_CuestionarioDt.secuencia, rownum) as secuencia, "); // el attachment y secuencia
		}
		else {
			sql.append("SELECT ").append(tableName + "." + tableName).append("_ID AS regID, ")//
					.append(tableName).append(".RutaImagen as Name, ") // nombre de la imagen
					.append(tableName).append(".ImagenBinary  as Image, nvl(") // imagen
					.append(tableName).append(".secuencia,rownum) as secuencia , "); // secuencia
		}
		sql.append(" EXME_Pregunta.EXME_Pregunta_ID as pregID, EXME_Pregunta.Name as p_name,  ") // ID de la pregunta, nombre preg (titulo)
				.append(" EXME_CuestionarioDt.EXME_Cuestionario_ID as cuestID ") // cuestionario
				.append(" FROM ").append(table_Name == t_attach ? "AD_Attachment" : "EXME_Pregunta");// tabla principal

		if (table_Name == t_attach)
			sql.append(" INNER JOIN EXME_Pregunta ON ( EXME_Pregunta.EXME_Pregunta_ID = AD_Attachment.Record_ID ")//
					.append(" AND AD_Attachment.AD_Table_ID = ?  ) "); // table id 1

		else {
			sql.append(" INNER JOIN ").append(tableName).append(" ON ( ")//
					.append(" EXME_Pregunta.EXME_Pregunta_ID = ").append(tableName).append(".EXME_Pregunta_ID ")// pregunta
					.append(" AND ").append(tableName).append(table_Name == t_actPac ? ".EXME_ActPaciente_ID = ? " : ".EXME_Paciente_ID = ? ");// refid 1
			if (folio != null) // si es por folio (notas medicas paciente)
				sql.append(" AND ").append(tableName).append(".folio = ? "); // folio = 2
			sql.append(") ");
		}

		sql.append(pregID <= 0 && cuestionarioID >= 0  ? " INNER " : " LEFT ")// si solo es por cuestionario
				.append(" JOIN EXME_CuestionarioDt ON (EXME_CuestionarioDt.EXME_Pregunta_ID = EXME_Pregunta.EXME_Pregunta_ID ")//
				.append(cuestionarioID >= 0 ? " AND NVL(EXME_CuestionarioDt.EXME_Cuestionario_ID,0) = ? " : "").append(") "); // cuest 3

		sql.append(" WHERE ").append(table_Name == t_attach ? "AD_Attachment.BinaryData" : (tableName + ".ImagenBinary")).append(" IS NOT NULL ")//
				.append(" AND EXME_Pregunta.TipoDato = ").append(DB.TO_STRING(MPregunta.TIPODATO_FixedImage))//
				.append(pregID > 0 ? " AND EXME_Pregunta.EXME_Pregunta_ID = ? " : "") // preg 4
				.append(" ORDER BY pregID, ").append(table_Name == t_attach ? "AD_Attachment.created desc" : "cuestID ");

		//if (WebEnv.DEBUG)
		s_log.finest("CuestionarioModel.createImg " + sql.toString());//System.out.println(sql.toString());
	}

	/**
	 * Crea los archivos temporales de las imagenes fijas
	 * @param pregID
	 * @param cuestID
	 * @param secuencia
	 * @param name
	 * @param directory
	 * @param bin
	 * @param fos
	 * @param listadoNombres
	 * @throws IOException
	 * @throws SQLException
	 */
	private void createIMGTemp(int pregID, int cuestID, int secuencia, String file_name, String title_name, File directory, InputStream inStream, int size,
			List<String[]> listadoNombres, boolean isTemp) throws IOException, SQLException {

		String extension = MEXMERadiografias.crearExtension(file_name); // StringUtils.substringAfterLast(file_name, ".");
		String ini = StringUtils.substringBeforeLast(file_name, ".");

		FileOutputStream fos = null;
		
		if (!file_name.startsWith(pregID + "_" + cuestID) && isTemp) // si es nombre de la base de datos (t_cuest)
			// nombre de archivo unico
			ini = pregID + "_" + cuestID + "_" + secuencia + "_";

		// validamos que no exista ya la imagen, antes de crearla
		// valida que la llave no se encuentre en el archivo
		if (this.validate(listadoNombres, ini)) {
			// creacion de imagenes en archivo temporal	
			File file_tmp = null;
			//Liz de la Garza- Cuando se crea la imagen solo para visualizacion desde applet
			if (isTemp) {
				file_tmp = File.createTempFile(ini, extension, directory);
				file_tmp.deleteOnExit();
				fos = new FileOutputStream(file_tmp.getPath());
			} else {//Cuando se crea la imagen para ser visible en una ventana (Expediente) 
				file_tmp = new File(directory.getPath() +  File.separatorChar + ini + extension);
				file_tmp.createNewFile();
				file_tmp.deleteOnExit();
				fos = new FileOutputStream(file_tmp.getPath());				
			}

			IOUtils.copy(inStream, fos);
			fos.close();
			//Liz delaGarza- Convertir la imagen tif a png si se requiere visualizar en una ventana			
			if (!isTemp && extension.equals(".tif"))
				this.conversionTiffaPng(file_tmp.getPath());

			// Agregamos el nombre del archivo, la ruta fisica de la carpeta temporal y el id del registro donde
			// esta la imagen
			String[] par = { title_name, file_tmp.getName(), String.valueOf(0) };// name = nombre de pregunta, id= t_cuest_id
			listadoNombres.add(par);
		}
	}

	/**
	 * 
	 * @param listadoNombres
	 * @param file
	 */
	private void add(List<String[]> listadoNombres, File file) {
		try {
			if (file != null) {
				List<String> lista = FileUtils.readLines(file);

				for (String line : lista) {
					String[] par = StringUtils.split((StringUtils.remove(line, "\"")), ",");
					listadoNombres.add(par);
				}
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Valida que no se encuentre la img en el archivo
	 * @param listado
	 * @param keyImg
	 * @return
	 */
	private boolean validate(List<String[]> listado, String keyImg) {
		for (int i = 0; i < listado.size(); i++) {
			// String[] par = StringUtils.split((StringUtils.remove(listado.get(i), "\"")), ",");
			String[] par = listado.get(i);
			String key = par[1];
			if (StringUtils.substringBeforeLast(key, "_").equals(StringUtils.substringBeforeLast(keyImg, "_"))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Actualiza el archivo con el nombre de las imagenes, con el ID de la respuesta
	 * @param tCuestID
	 * @param pregID
	 * @param cuestID
	 * @param seq
	 * @return
	 */
	public String updateLine(int tCuestID, int pregID, int cuestID, int seq) {
		File file_csv = new File(getDirectoryName() + File.separator + imageFileName);

		List<String[]> lstImgs = new ArrayList<String[]>();

		String nameImg = null;

		try {
			if (file_csv != null) {

				String imgName = pregID + "_" + cuestID + "_";
				List<String> lista = FileUtils.readLines(file_csv);
				// busca la imagen dentro del archivo
				for (String line : lista) {
					String[] par = StringUtils.split((StringUtils.remove(line, "\"")), ",");
					// [0] - Nombre
					String nombreArchivo = StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(par[1], "."), "_");

					if (nombreArchivo.startsWith(imgName)) {
						par[2] = String.valueOf(tCuestID);
						// busca en tmp , retorna el nombre del tiff si lo encontro
						nameImg =  this.getFileName(imgName);//this.getNombreArchivo(getDirectoryName() + "tmp", nombreImagen);
						//if (nameImg == null) {
							// si no, busca en la real y retorna el nombre del original
							//nameImg = this.getNombreArchivo(getDirectoryName(), imgName);
						//}
					}
					lstImgs.add(par);//actualiza el Cuest Id de la imagen
				}

				// Crear el archivo csv que contiene el nombre de las imagenes
				CSVWriter writer = new CSVWriter(new FileWriter(file_csv));
				writer.writeAll(lstImgs);
				writer.close();
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}

		return nameImg;
	}

	public String updateLine(int pregID, int cuestID, int seq) {
		File file_csv = new File(getDirectoryName() + File.separator + imageFileName);

		List<String[]> lstImgs = new ArrayList<String[]>();

		String nameImg = null;

		try {
			if (file_csv != null) {

				String imgName = pregID + "_" + cuestID + "_";
				List<String> lista = FileUtils.readLines(file_csv);
				// busca la imagen dentro del archivo
				for (String line : lista) {
					String[] par = StringUtils.split((StringUtils.remove(line, "\"")), ",");
					// [0] - Nombre
					String nombreArchivo = StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(par[1], "."), "_");

					if (nombreArchivo.startsWith(imgName)) {
						//par[2] = String.valueOf(tCuestID);
						// busca en tmp , retorna el nombre del tiff si lo encontro
						nameImg =  this.getFileName(imgName);//this.getNombreArchivo(getDirectoryName() + "tmp", nombreImagen);
						//if (nameImg == null) {
							// si no, busca en la real y retorna el nombre del original
							//nameImg = this.getNombreArchivo(getDirectoryName(), imgName);
						//}
					}
					lstImgs.add(par);//actualiza el Cuest Id de la imagen
				}

				// Crear el archivo csv que contiene el nombre de las imagenes
				CSVWriter writer = new CSVWriter(new FileWriter(file_csv));
				writer.writeAll(lstImgs);
				writer.close();
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		}

		return nameImg;
	}

	/**
	 * Elimina el archivo relacionado a lap regunta
	 * @param tCuestID
	 * @param pregID
	 * @param cuestID
	 * @param seq
	 * @return
	 * @throws Exception 
	 */
	public void deleteFile(int tCuestID, int pregID, int cuestID, int seq, boolean throwsException) throws Exception {
		File file_csv = new File(getDirectoryName() + File.separator + imageFileName);

		List<String[]> lstImgs = new ArrayList<String[]>();

		try {
			if (file_csv != null) {
				List<String> lista = FileUtils.readLines(file_csv);

				for (String line : lista) {
					String[] par = StringUtils.split((StringUtils.remove(line, "\"")), ",");
					// [0] - Nombre
					String key = StringUtils.substringBeforeLast(par[1], ".");
					// si es por cuestionario
					if (pregID <= 0)
						key = StringUtils.substringBeforeLast(key, "_");
					String msg = pregID + "_" + cuestID + "_";
					if (key.startsWith(msg)) {
						// eliminar img
						deleteDirectory(key);
						continue;
					}
					lstImgs.add(par);
				}

				// Crear el archivo csv que contiene el nombre de las imagenes
				CSVWriter writer = new CSVWriter(new FileWriter(file_csv));
				writer.writeAll(lstImgs);
				writer.close();
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, null,e);
			if(throwsException)
				throw e;
		}
	}

	/**
	 *  Eliminar los archivos temporales de la carpeta de la sesion
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDirectory(String imgName) throws Exception {
		//la borramos de ambas carpetas
		if(!deleteDirectory(getDirectoryName()+ "tmp", imgName))// directoryName Temporal
			return false;
		return deleteDirectory(getDirectoryName(), imgName);// directoryName;
		
	}

	/**
	 * Eliminar los archivos temporales de la carpeta de la sesion
	 * @param path
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteDirectory(String path, String imgName) throws Exception {
		boolean success = true;

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().startsWith(imgName)) {
					success = CuestionarioModel.forceDelete(files[i]);
					if (!success) {
						throw new Exception("Error deleting image, please try again");//FIXME: como desbloquear archivo?
					}
					return success;
				}
			}
		}
		return success;
	}


	/**
	 * Busca una imagen de acuerdo un nombre, en las carpeta existentes de la session
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public FileInputStream getFile(String imgName) throws Exception {
		FileInputStream strfile = null;

		strfile = Utilerias.getFileInputStream(getDirectoryName() + "tmp", imgName);

		if (strfile == null)
			strfile = Utilerias.getFileInputStream(getDirectoryName(), imgName);

		return strfile;

	}
	
	/**
	 * Busca una imagen de acuerdo un nombre, en las carpeta existentes de la session
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public String getFileName(String imgName) throws Exception {
		String strfile = null;

		strfile = Utilerias.getFileName(getDirectoryName() + "tmp", imgName);

		if (strfile == null)
			strfile = Utilerias.getFileName(getDirectoryName(), imgName);

		return strfile;

	}
	
	/**
	 * Elimina el archivo
	 * @param file
	 * @return
	 */
	public static boolean forceDelete(File file) {
		return Utilerias.forceDelete(file);
	}

	/**
	 * Elimina las carpetas dada una ruta, incluyendo la temporal
	 * 
	 * @param path
	 * @return
	 */
	public static boolean forceDeleteAll(String path) {
		// primero la temporal
		if (!Utilerias.forceDelete(path + "tmp"))
			s_log.log(Level.SEVERE, "forceDeleteTMP", path + "tmp");
		return Utilerias.forceDelete(path);
	}
	
	/**
	 * Elimina las carpetas de imgs de cuestionarios, incluyendo la temporal
	 * 
	 * @param path
	 * @return
	 */
	public static boolean forceDeleteAll() {
		return forceDeleteAll(getCuestDefaultPath());
	}

	
	
	/**
	 * @author Lizeth de la Garza
	 * Convierte una imagen tiff a una png
	 * @param fileName - ruta de la imagen
	 * @throws IOException
	 */
	private void conversionTiffaPng(String fileName) throws IOException {
		File file = new File(fileName);
		//Liz delaGarza-La imagen estara en el mismo directorio que la original
		SeekableStream seekableStream = new FileSeekableStream(file);
		ImageDecoder decoder = ImageCodec.createImageDecoder("tiff", seekableStream, null);
		String namePng = StringUtils.substringBeforeLast(fileName, ".");
				
		//Liz de la Garza- Funcionalidad si la imagen tif tiene mas de una pagina
		/*File f = new File(namePng);
		f.mkdirs();		

		for (int i = 0; i < decoder.getNumPages(); i++) {
			RenderedImage page = decoder.decodeAsRenderedImage(i);
			File fileObj = new File(namePng+"/" + namePng + (i+1) + ".png");	           
			ParameterBlock parBlock = new ParameterBlock();
			parBlock.addSource(page);
			parBlock.add(fileObj.toString());
			parBlock.add("png");
			RenderedOp renderedOp = JAI.create("filestore",parBlock);
			renderedOp.dispose();
		}*/
		//Liz de la Garza-Funcionalidad si solo contiene una pagina
		RenderedImage page = decoder.decodeAsRenderedImage(0);
		File fileObj = new File(namePng + ".png");//Conversion a png
		ParameterBlock parBlock = new ParameterBlock();
		parBlock.addSource(page);
		parBlock.add(fileObj.toString());
		parBlock.add("png");
		RenderedOp renderedOp = JAI.create("filestore", parBlock);
		renderedOp.dispose();

	}
	
	/**
	 * Regresa la ruta temporal de las img. de cuestionarios
	 * @return
	 */
	private static String getCuestDefaultPath() {
		String path = null;
		//compier2/imgcuestfilepath+session
		
		try {
		/*path = ((System.getProperty("COMPIERE_HOME")) 
				+ File.separator + WebEnv.getXPT_Properties().getProperty("IMGCuestFilePath"))
				+ File.separator + Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID");*/
		
			path = 	Ini.getCompiereHome() + File.separator + 
					Utilerias.getPropertiesFromXPT(IMGCuestFilePath) + File.separator + 
					Env.getAD_Session_ID(Env.getCtx());
		
		}catch (Exception e) {
			s_log.log(Level.SEVERE, "No path found", e.toString());
		}
		return path;
	}

	/*************/
	public String getImagenFilePath() {
		return ImagenFilePath;
	}

	public void setImagenFilePath(String imagenFilePath) {
		ImagenFilePath = imagenFilePath;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getUrlTemporal() {
		return urlTemporal;
	}

	public void setUrlTemporal(String urlTemporal) {
		this.urlTemporal = urlTemporal;
	}

	public String getWebFolderName() {
		return webFolderName;
	}

	public void setWebFolderName(String webFolderName) {
		this.webFolderName = webFolderName;
	}

	public String getUrlArchivoCSV() {
		return urlArchivoCSV;
	}

	public void setUrlArchivoCSV(String urlArchivoCSV) {
		this.urlArchivoCSV = urlArchivoCSV;
	}
}
