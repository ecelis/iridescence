package org.compiere.model.bpm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;

import net.sf.jooreports.templates.DocumentTemplateException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MAttachment;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECampoPlantilla;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMEPlantilla;
import org.compiere.model.MEXMEPrescRX;
import org.compiere.model.ModelError;
import org.compiere.model.ServicioView;
import org.compiere.model.X_EXME_PrescRX;
import org.compiere.util.CLogger;
import org.compiere.util.CalcularEdadAMD;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.openoffice.Constant;
import org.openoffice.documentHandling.DocumentSaver;

/**
 * 
 * @author Expert
 * 
 */
public class GenerateDocumentOO {

	/** log */
	private static CLogger log = CLogger.getCLogger(GenerateDocumentOO.class);
	/** Listado de mensajes */
	private List<ModelError> error = new ArrayList<ModelError>();
	/** Archivo .docx */
	private String host;
	/** Puerto */
	private int port;
	/** Carpeta intermediaria */
	private static final String FOLDER_UTILS = "utils";
	/** Carpeta de temporal */
	private static final String FOLDER_TMP = "tmp";
	/** Listado de llaves para relacionar con su valor */
	private List<LabelValueBean> lvb = new ArrayList<LabelValueBean>();
	/** */ 
	private MEXMECtaPac mCtaPatient;
	/** */
	private Properties ctx;

	/**
	 * Baja la Plantilla seleccionada de la base de datos: En caso de que sea
	 * esquema de renta al documento le antepone el nombre del paciente y el
	 * numero de seguro social, en caso de no contar con el le agrega un numero
	 * aleatorio concatenado al final del nombre del paciente para evitar
	 * duplicados En el caso de que se mande directo a impresora solamente se le
	 * concatena al nombre del paciente el numero aleatorio para hacer unico el
	 * archivo a imprimir
	 * @param liga 
	 * @param folio 
	 * @param cuestionarioID 
	 * @param cuentaPacID 
	 * 
	 * @return File
	 */
	public File generateDocument(Properties pctx, MEXMECtaPac pmCtaPatient,
			int patientId, int templateId, int liga, int folio, int cuestionarioID) {
		ctx = pctx;
		// Debe existir un template relacionado
		if (templateId <= 0) {
			return null;
		}

		// Siempre sobre un paciente
		if (pmCtaPatient == null && patientId <= 0) {
			return null;
		}

		// configuracion de openoffice
		getConfigServiceManagerOpenOffice(ctx);

		// Archivo docx
		File fileDoc = null;
		// Nombre del archivo Extension del archivo
		String fileName = Constant.PREFIX_TMP;
		// Llave
		String keyUnique = "";
		mCtaPatient = pmCtaPatient;
		try {

			// Validamos que se tenga la ruta de instalacion del Open Office
			if (validConfigServiceManagerOpenOffice()) {

				// Se define la ruta para el guardado de los temporales
				// (archivos .odt listos para imprimir)
				String path = Ini.getCompiereHome() + File.separator
						+ FOLDER_UTILS + File.separator + FOLDER_TMP
						+ File.separator;
				File pathf = new File(path);

				// se obtiene el objeto de de la tabla AD_Attachment relacionado
				// con la plantilla indicada
				MAttachment attachment = MAttachment.get(ctx,
						MEXMEPlantilla.Table_ID, templateId);

				// Jose Isaac Garcia 2/sep/2010
				// Validacion que define si el archivo que se obtiene de la base
				// de datos es un documento de '.docx'
				if (filtroDoc(attachment.getEntry(0).getName())) {

					// Se genera un arreglo de bytes con el contenido del
					// archivo de la base de datos
					byte[] arr = attachment.getEntry(0).getData();

					// Obtenemos el numero de historial del paciente
					keyUnique = mCtaPatient != null ? mCtaPatient.getPaciente()
							.getValue() : new MEXMEPaciente(ctx, patientId,
							null).getValue();

					// Se valida si el paciente tenga una clave de Historial
					// Medico
					if (StringUtils.isBlank(keyUnique)) {

						fileDoc = File.createTempFile(fileName,
								'.' + Constant.MS_OFFICE_WORD_EXT, pathf);

					} else {

						fileDoc = new File(pathf, keyUnique + '.'
								+ Constant.MS_OFFICE_WORD_EXT);

					}

					/*
					 * Se crea el archivo temporal comenzando por la cadena
					 * "tmp" y terminando con extension ".odt" en la ubicación
					 * definina como path.
					 */
					File template = File.createTempFile(Constant.PREFIX_TMP,
							'.' + Constant.OPENOFFICE_WRITE_EXT_ODT, pathf);

					// Generar la plantilla partir de los binarios del archivo
					// en la base de datos

					FileUtils.writeByteArrayToFile(fileDoc, arr);

					// Se crea la instancia de la clase DocumentSaver con sus
					// respectivos parametros
					// DocumentSaver docSaver = new DocumentSaver(fileDoc,
					// template, Constant.OPENOFFICE_WRITE);
					// DocumentSaver docSaver = new
					// DocumentSaver(pathOppenOffice);

					DocumentSaver docSaver = new DocumentSaver(host, port);

					docSaver.setDocumentType(Constant.OPENOFFICE_WRITE);
					docSaver.setSourceFile(fileDoc);
					docSaver.setOutFile(template);
					
					//
					if (liga == 2) {
						docSaver.setSearch("table_insert"); 
						docSaver.setShowBorder(true);

						// Ultima prescripcion
						MEXMEPrescRX prescRX = MEXMEPrescRX.getUltimaPres(ctx,  mCtaPatient.getEXME_CtaPac_ID(), 
								X_EXME_PrescRX.TIPO_DischargeMedication, null);
						if(prescRX!=null){
						// Servicios
						List<ServicioView> lst = MEXMEActPacienteIndH.getDetalleMed(Env.getCtx(), prescRX.getEXME_PrescRX_ID() , null);
						String[][] cellData = new String [lst.size()][8];
						for(int y=0; y<lst.size(); y++) {
							ServicioView renglon = lst.get(y);

							cellData[y][0]= String.valueOf(renglon.getSecuencia());
							cellData[y][1]= renglon.getProdName();
							cellData[y][2]= String.valueOf(renglon.getCantidad());
							cellData[y][3]= renglon.getTipoDosis();
							cellData[y][4]= renglon.getFreq1();
							cellData[y][5]= renglon.getFreq2();
							cellData[y][6]= renglon.getDescripcion();
							cellData[y][7]= renglon.getComments();
						}
						docSaver.setCellData(cellData);}
					}

					// Se realiza el seteo del path donde esta instaldo el Open
					// Office
					// docSaver.setPathOpenOffice(pathOppenOffice);

					// Se graba el documento
					docSaver.documentSaver();

					Properties properties = null;
					if(liga==0){
						//Se obtiene la lista de los parametros posibles que puede tener la plantilla
						List<String> lstCampos = MEXMECampoPlantilla.busquedaEnvioCampos(ctx);

						//Se procede a obtener las atiquetas a reemplzar con sus respectivos valores
						properties = MEXMECampoPlantilla.generaProperties(ctx, lstCampos, patientId);

					} else if (liga == 1) {
						// Se procede a obtener las atiquetas a reemplzar con sus
						// respectivos valores
						properties = properties(ctx, patientId,
								templateId, mCtaPatient.getEXME_CtaPac_ID());

					} else if (liga == 2) {
						// considera todas los campos que no sean de pregunta
						properties(ctx, patientId,
								templateId, mCtaPatient.getEXME_CtaPac_ID());
						// considera todos los campos que tengan presguntas
						properties = MEXMECampoPlantilla.find( folio,  mCtaPatient.getEXME_CtaPac_ID(),  cuestionarioID);
					}
					
					// Se realiza el reemplazo de las etiquestas a sustituir en
					// el archivo doc
 					MEXMECampoPlantilla.process(properties, fileDoc, template);

					// Borramos el archivo que ya no necesitamos
					template.delete();

					// Se requiere guardar nuevamente el mismo documento para
					// que se guarde en formato correcto
					// docSaver = new DocumentSaver(fileDoc, fileDoc,
					// Constant.MS_OFFICE_WORD_2007_XML);
					// docSaver.setPathOpenOffice(pathOppenOffice);
					// docSaver.documentSaver();

					docSaver.setDocumentType(Constant.MS_OFFICE_WORD_97);
					docSaver.setSourceFile(fileDoc);
					docSaver.setOutFile(fileDoc);
					docSaver.documentSaver();

				} else {
					error.add(new ModelError(ModelError.TIPOERROR_Error,
							"advancedDirectives.msj.noDocumentoDoc"));
				}

			} else {
				return null;
				/*
				 * Messagebox.show(Utilerias.getMessage(ctx, null,
				 * "advancedDirectives.msj.noConfiguradoOpenOffice"),
				 * Utilerias.getMessage(ctx, null,
				 * "advancedDirectives.msj.noConfiguradoOpenOffice"),
				 * Messagebox.OK, Messagebox.INFORMATION);
				 */
			}

		} catch (IOException e1) {
			log.log(Level.SEVERE, "Saving media " , e1);
			fileDoc = null;
			error.add(new ModelError(ModelError.TIPOERROR_Error,
					"advancedDirectives.msj.error.generarArchivo"));
		} catch (DocumentTemplateException e2) {
			log.log(Level.SEVERE, "Saving media " , e2);
			error.add(new ModelError(ModelError.TIPOERROR_Error,
					"advancedDirectives.campoPlantilla.error.etiquetas"));
			fileDoc = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Saving media " , e);
			fileDoc = null;
			error.add(new ModelError(ModelError.TIPOERROR_Error,
					"advancedDirectives.msj.error.generarArchivo"));
		}

		return fileDoc;

	}






	/**
	 * Baja la Plantilla seleccionada de la base de datos: En caso de que sea
	 * esquema de renta al documento le antepone el nombre del paciente y el
	 * numero de seguro social, en caso de no contar con el le agrega un numero
	 * aleatorio concatenado al final del nombre del paciente para evitar
	 * duplicados En el caso de que se mande directo a impresora solamente se le
	 * concatena al nombre del paciente el numero aleatorio para hacer unico el
	 * archivo a imprimir
	 * @param liga 
	 * @param folio 
	 * @param cuestionarioID 
	 * @param cuentaPacID 
	 * 
	 * @return File
	 */
	public void generateDocumentTest(Properties pctx, MEXMECtaPac pmCtaPatient,
			int patientId, int templateId, int liga, int folio, int cuestionarioID) {
		ctx = pctx;
		try {

			@SuppressWarnings("unused")
			Properties properties = null;
			if (liga == 2) {
				// considera todas los campos que no sean de pregunta
				properties(ctx, patientId,
						templateId, pmCtaPatient.getEXME_CtaPac_ID());
				// considera todos los campos que tengan presguntas
				properties = MEXMECampoPlantilla.find( folio,  mCtaPatient.getEXME_CtaPac_ID(),  cuestionarioID);
			}
			
			// Se realiza el reemplazo de las etiquestas a sustituir en
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Validamos que exista la configuracion del openoffice
	 * 
	 * @return
	 */
	private boolean validConfigServiceManagerOpenOffice() {

		boolean success = true;
		if (("".equals(host) || host == null) || port < 0) {
			success = false;
		}
		return success;
	}

	/**
	 * Jose Isaac Garcia 2/sep/2010 Metodo que busca el String del nombre de un
	 * archivo una extencion .docx
	 * */
	public boolean filtroDoc(String fileName) {

		String ext;
		int pos = fileName.lastIndexOf('.');

		if (pos == -1) {
			return false;
		}
		ext = fileName.substring(pos + 1);

		if (Constant.MS_OFFICE_WORD_2007_EXT.equalsIgnoreCase(ext)) {
			return true;
		}

		return false;
	}

	/**
	 * Configuracion
	 */
	private void getConfigServiceManagerOpenOffice(Properties ctx) {

		try {
			MEXMEMejoras improvement = MEXMEMejoras.get(ctx);
			this.host = improvement.get_Value(
					MEXMEMejoras.COLUMNNAME_HostServManagOOff).toString();
			this.port = Integer.parseInt(improvement.get_Value(
					MEXMEMejoras.COLUMNNAME_PortServManagOOff).toString());

		} catch (NullPointerException npe) {
			log.log(Level.SEVERE, "getConfigServiceManagerOpenOffice() "
					+ "Not configure Service Manager the Open Office", npe);
		}
	}

	/**
	 * Listado de propiedades
	 * 
	 * @param ctx
	 * @param patientId
	 * @param plantillaID
	 * @return
	 */
	public Properties properties(Properties ctx, int patientId, int plantillaID, int cuentaPacID) {
		Properties properties = null;

		// Obtener la plantilla
		MEXMEPlantilla plantilla = new MEXMEPlantilla(ctx, plantillaID, null);
		String sql = null;

		// si existe un query executarlo
		if (plantilla.getQuery() != null && plantilla.getQuery().length() > 0) {
			// considerar lo que regresa el query
			// el query debe considerar que todo venga como string
			sql = query(ctx, plantilla.getQuery());
		} else {
			// Si no existe el query se crea apartir de la configuracion
			sql = query(ctx);
		}
		sql = sql + " WHERE EXME_Paciente.EXME_Paciente_ID = " +patientId + " ";
		
		// TODO: Dando por hecho que siempre existira una relacion con cuenta paciente
		if(cuentaPacID>0 && sql.contains("EXME_CtaPac")) {
			sql = sql + " AND EXME_CtaPac.EXME_CtaPac_ID = " +cuentaPacID + " ";
		}
		
		// Si se creo alguna consulta se executa
		if (sql != null && sql.length() > 0) {
			properties = MEXMECampoPlantilla.find(ctx, sql, lvb, null);
		}

		// Edad
		/** Age*/
		MEXMEPaciente paciente = new MEXMEPaciente(ctx, patientId, null);
		CalcularEdadAMD objAge = null;
		try {
			objAge = CalcularEdadAMD.getAge(ctx, paciente.getFechaNac());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MEXMECampoPlantilla.validacionNullo("Age",objAge == null?"":objAge.getEdadAMD());
		return properties;
	}

	/**
	 * Creacion de consulta para las tablas
	 * 
	 * @param ctx
	 * @return
	 */
	public String query(Properties ctx) {
		// Si no existe queri buscamos todos los campos
		// Se obtiene la lista de los parametros disponibles
		List<MEXMECampoPlantilla> campos = MEXMECampoPlantilla.get(ctx, null);

		//
		StringBuilder from = new StringBuilder(" FROM EXME_Paciente ");
		StringBuilder select = new StringBuilder();
		select.append(" SELECT ");

		// Se define un mapa compuesto por tabla, lista de campos
		HashMap<Integer, List<MEXMECampoPlantilla>> mapa = mapa(campos);

		// Cuenta las columnas a definir, sirve para tener la relacion
		// llave, valor en el resultset
		int count = 0;

		// Iteramos el mapa para obtener los campos
		Set<Integer> tabla_ID = mapa.keySet();
		Iterator<Integer> it = tabla_ID.iterator();
		while (it.hasNext()) {

			// id tabla
			int tablita = it.next();
			List<MEXMECampoPlantilla> campos_tablita = mapa.get(tablita);

			// Si existe una tabla
			if (tablita > 0) {

				if(select.length() > 8 
						&& !select.toString().trim().endsWith(",")){
					select.append(", ");
				}
				
				String aliasTabla = null;
				for (int i = 0; i < campos_tablita.size(); i++) {

					MEXMECampoPlantilla campo = campos_tablita.get(i);

					if (i != 0) {
						select.append(", ");
					} else {
						// Nombre de la tabla a utilizar
						aliasTabla = campo.getTabla().getTableName();
						
					}

					// tabla diferente a la de paciente almenos que sea ID
					if (campo.getAD_Column_ID() > 0) {
						select.append(aliasTabla == null ? "EXME_Paciente"
								: aliasTabla);
						select.append(".");
						select.append(campo.getColumn().getColumnName());

						// llave para el docuento/Numero de columna
						count++;
						lvb.add(new LabelValueBean(campo.getValue(), String
								.valueOf(count)));

					}
				}
				if(!aliasTabla.equals("EXME_Paciente")){
					from.append(" LEFT JOIN ");
					from.append(aliasTabla);
					from.append(" ON EXME_Paciente.EXME_Paciente_ID = ");
					from.append(aliasTabla).append(".EXME_Paciente_ID ");
				}
			} else {
				// como no existe una tabla puede ser que exista un query al
				// respecto
				// por lo que se agregara como una columna mas como
				// si esta fuera un subquery

				// Listado de campos por tabla
				for (int i = 0; i < campos_tablita.size(); i++) {

					// Campo
					MEXMECampoPlantilla campo = campos_tablita.get(i);
					if (campo.getQuery() != null) {

						if (i != 0) {
							select.append(", ");
						} else {
							if (select.length() > 8 
									&& !select.toString().trim().endsWith(",")){
								select.append(", ");
							}
						}

						select.append(" (").append(campo.getQuery()).append(
								") ");

						count++;
						lvb.add(new LabelValueBean(campo.getValue(), String
								.valueOf(count)));
					}

				} // fin for
			}// fin else
		}

		String queryfinal = select.append(from.toString()).toString();
		if (queryfinal.trim().length() > 6) {
			return queryfinal;
		} else {
			return null;
		}
	}

	/**
	 * Verificamos que este bien estructurada la consulta y sabesmos cuantos
	 * valores regresara
	 * 
	 * @param ctx
	 * @param sql
	 * @return
	 */
	public String query(Properties ctx, String sql) {
		if (!sql.contains("SELECT"))
			return null;

		if (!sql.contains("FROM"))
			return null;

		try {
			StringTokenizer tok = new StringTokenizer(sql, ",");
			int count = 0;
			while (tok.hasMoreTokens()) {
				count++;
				lvb.add(new LabelValueBean("${" + tok.nextToken() + "}", String
						.valueOf(count)));
			}

		} catch (Exception e) {
			String columna = sql.substring(sql.indexOf("SELECT") + 6, sql
					.indexOf("FROM"));
			lvb.add(new LabelValueBean("${" + columna.trim() + "}", String
					.valueOf(1)));
		}
		return sql;
	}

	/**
	 * Mapa con tabla y listado de campos
	 * 
	 * @param campos
	 *            lista de campos
	 * @return
	 */
	private HashMap<Integer, List<MEXMECampoPlantilla>> mapa(
			List<MEXMECampoPlantilla> campos) {
		// Se define un mapa compuesto por tabla, lista de campos
		HashMap<Integer, List<MEXMECampoPlantilla>> mapa = new HashMap<Integer, List<MEXMECampoPlantilla>>();
		for (MEXMECampoPlantilla view : campos) {

			int tabla = (int) view.getAD_Table_ID();

			List<MEXMECampoPlantilla> lstCampos = null;
			// Buscamos la tabla
			if (mapa.containsKey(tabla)) {
				lstCampos = mapa.get(tabla);
				lstCampos.add(view);
			} else {
				lstCampos = new ArrayList<MEXMECampoPlantilla>();
				lstCampos.add(view);
				mapa.put(tabla, lstCampos);
			}
		}
		return mapa;
	}

	public List<ModelError> getError() {
		return this.error;
	}

	public void setError(List<ModelError> error) {
		this.error = error;
	}
}
