package org.compiere.util.cuestionarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.compiere.model.MEXMETCuest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

public class Pregunta_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tipoPregunta = null;
	private String  tipoPreguntaTxt = null;
	private String name = null;
	private Integer id = null;
	private Integer pregId = null;
	private String comentario = null;
	private String tipoDato = null;
	private String controlTipo = null;
	private String display = null;
	private String displayCues = null;
	private String respuesta = null;
	private String respuestaCombo = null;
	private FormFile respuestaI = null;
	private String rutaImagen = null;
	private Boolean respuestaL = new Boolean(false);
	private String checked = "";
	private Integer folio = null;
	private Integer secuencia = null;
	private String obligatoriaTxt = "N";
	private Boolean obligatoria = null;
	private Integer cuestionarioId = null;
	private List<Regla_VO> lstReglas = new ArrayList<Regla_VO>();
	private int lstReglasSize = 0;
	private Integer indice = null;
	private boolean fromRespuetas = false;
	private ArrayList<RespuestaList_VO> respuestas = new ArrayList<RespuestaList_VO>();
	private ArrayList<RespuestaList_VO> respuestasMulti = new ArrayList<RespuestaList_VO>();
	private Integer exme_t_cuest_ID = null;
	private Integer exme_enfcontrolresp_ID = null;
	private int seqPreg = 0; // secuencia para preg. repetidas .- Lama
	private Integer pacCuestId = null; //lhernandez. Cuestionarios del Paciente.
	private Integer pacRespId = null; //lhernandez. Cuestionarios del Paciente.
	private byte[] image = null; //lhernandez. Cuestionarios del Paciente.
	private int preguntaListaID = 0;
	
	//for use with ZK's AImage
	private byte[] byteArr = null;
	private String fileNameByteArr = null;

	public Pregunta_VO(){}



	public Pregunta_VO(ArrayList<RespuestaList_VO> respuestas) {
		super();
		this.respuestas = respuestas;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipoPregunta() {
		return tipoPregunta;
	}

	public void setTipoPregunta(Integer tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {

		if(tipoDato.equalsIgnoreCase("L")){
			this.respuesta = "N";
			this.respuestaL = false;
		}
		this.tipoDato = tipoDato;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getControlTipo() {
		return controlTipo;
	}

	public void setControlTipo(String controlTipo) {
		this.controlTipo = controlTipo;
	}

	public String getTipoPreguntaTxt() {
		return tipoPreguntaTxt;
	}

	public void setTipoPreguntaTxt(String tipoPreguntaTxt) {
		this.tipoPreguntaTxt = tipoPreguntaTxt;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getObligatoriaTxt() {
		return obligatoriaTxt;
	}

	public void setObligatoriaTxt(String obligatoriaTxt) {
		this.obligatoriaTxt = obligatoriaTxt;
	}

	public Boolean getObligatoria() {
		if(this.obligatoriaTxt.equalsIgnoreCase("N")){
			this.obligatoria = new Boolean(false);
		}else if(this.obligatoriaTxt.equalsIgnoreCase("Y")){
			this.obligatoria = new Boolean(true);
		}
		return obligatoria;
	}

	public void setObligatoria(Boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public Integer getCuestionarioId() {
		return cuestionarioId;
	}

	public void setCuestionarioId(Integer cuestionarioId) {
		this.cuestionarioId = cuestionarioId;
	}

	public List<Regla_VO> getLstReglas() {
		return lstReglas;
	}

	public void setLstReglas(List<Regla_VO> lstReglas) {
		this.lstReglas = lstReglas;
	}

	public String getDisplayCues() {
		if(this.lstReglas != null && this.lstReglas.size() > 0 && !this.fromRespuetas){
			for(int i = 0; i < this.lstReglas.size(); i++){
				if(this.lstReglas.get(i).getListaId() != null && this.lstReglas.get(i).getListaId().intValue() > 0 ){
					this.displayCues = "display:none;";
				}else if (this.lstReglas.get(i).getBanderaResp().equalsIgnoreCase("N")){
					//this.displayCues = "display:block;";
					this.displayCues = "display:none;";
				}else if(this.lstReglas.get(i).getBanderaResp().equalsIgnoreCase("Y")){
					//this.displayCues = "display:none;";
					this.displayCues = "display:block;";
				}
			}

		}else{

			if(this.fromRespuetas)
				this.fromRespuetas = false;

			this.displayCues = "display:block;";
		}

		if(this.displayCues.equalsIgnoreCase("display:block;") 
				&& this.obligatoriaTxt != null 
				&& this.obligatoriaTxt.equalsIgnoreCase("Y")){
			this.displayCues = 
				this.displayCues + 
				"background-color:#E1F1FF;border-style:solid;border-color:#AACEFB;";
		}


		return displayCues;
	}

	public void setDisplayCues(String displayCues) {
		this.displayCues = displayCues;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public ArrayList<RespuestaList_VO> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(ArrayList<RespuestaList_VO> respuestas) {
		this.respuestas = respuestas;
	}

	public FormFile getRespuestaI() {
		return respuestaI;
	}

	public void setRespuestaI(FormFile respuestaI) {
		this.respuestaI = respuestaI;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String saveImagen(){

		String fileName = null;
		String baseName = null;

		try {			
			//De este archivo lee 
			String env = System.getProperty ("COMPIERE_HOME");
			String propPath = env + File.separator + "CompiereEnv.properties";
			Properties properties = new Properties();
			properties.load(new FileInputStream(propPath));

			//la extension del archivo
			String extension = "jpg";

			File originalFile = null;

			if(rutaImagen != null){
				originalFile = 
					new File(
							properties.getProperty("MEDSYS_PATH_IMG") + 
							File.separator + 
							rutaImagen
					);

				fileName = rutaImagen;
			}

			//first, determine if we are getting the file from Struts or from ZK
			if(byteArr == null) {
				//it is from Struts
				FormFile file = respuestaI;

				//validamos que se haya cargado un archivo
				if(!StringUtils.isEmpty(file.getFileName())){

					// eliminamos el archivo existente
					if (originalFile != null && originalFile.exists()) {
						originalFile.delete();
					}

					extension = 
						StringUtils.substringAfterLast(file.getFileName(), ".");

					baseName =
						StringUtils.substringBeforeLast(file.getFileName(), ".");

					//nombre del archivo : CitaMedica - NoConsecutivoDeImagen
					fileName = 
						baseName + '-' + folio + '_' + id.intValue() + '.' + extension;

					//retrieve the file data
					InputStream stream = file.getInputStream();

					OutputStream bos = 
						new FileOutputStream(
								properties.getProperty("MEDSYS_PATH_IMG") 
								+ File.separator 
								+ fileName
						);

					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					bos.close();
					stream.close();
				}
			} else {
				//it is from ZK

				// eliminamos el archivo existente
				if (originalFile != null && originalFile.exists()) {
					originalFile.delete();
				}

				extension = 
					StringUtils.substringAfterLast(fileNameByteArr, ".");

				baseName =
					StringUtils.substringBeforeLast(fileNameByteArr, ".");

				//nombre del archivo : CitaMedica - NoConsecutivoDeImagen
				fileName = 
					baseName + '-' + folio + '_' + id.intValue() + '.' + extension;

				File file = 
					new File(
							properties.getProperty("MEDSYS_PATH_IMG") 
							+ File.separator 
							+ fileName
					);

				FileUtils.writeByteArrayToFile(file, byteArr);
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return fileName;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Boolean getRespuestaL() {
		if(this.respuesta != null && !this.respuesta.equalsIgnoreCase("")){
			if(this.respuesta.equalsIgnoreCase("Y"))
				this.respuestaL = new Boolean(true);
			else
				this.respuestaL = new Boolean(false);			
		}
		return respuestaL;
	}

	public void setRespuestaL(Boolean respuestaL) {
		this.respuestaL = respuestaL;
	}

	public String getChecked() {
		if(this.respuesta != null && !this.respuesta.equalsIgnoreCase("")){
			if(this.respuesta.equalsIgnoreCase("Y")){
				this.checked = "checked";
			}else if(this.respuesta.equalsIgnoreCase("N")){
				this.checked = "";			
			}
		}
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Integer getExme_t_cuest_ID() {

		if((this.folio != null && this.folio.intValue() > 0) && (this.exme_t_cuest_ID == null || this.exme_t_cuest_ID.intValue() < 0)){
			try {
				this.exme_t_cuest_ID = MEXMETCuest.getIdFromFolio(this.folio, this.cuestionarioId, this.secuencia, this.id);// secuencia para preg. repetidas .- Lama
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return exme_t_cuest_ID;
	}

	public void setExme_t_cuest_ID(Integer exme_t_cuest_ID) {
		this.exme_t_cuest_ID = exme_t_cuest_ID;
	}

	public String getRespuestaCombo() {
		return respuestaCombo;
	}

	public void setRespuestaCombo(String respuestaCombo) {
		for(int j = 0; j < this.respuestas.size(); j++){
			if(this.respuestas.get(j).getListaID().toString().equalsIgnoreCase(respuestaCombo)){
				this.respuestas.get(j).setSelected("selected=\"selected\"");
				j = this.respuestas.size();
			}
		}
		this.respuestaCombo = respuestaCombo;
	}

	public int getSeqPreg() {
		return seqPreg;
	}

	public void setSeqPreg(int seqPreg) {
		this.seqPreg = seqPreg;
	}



	public Integer getPregId() {
		return pregId;
	}



	public void setPregId(Integer pregId) {
		this.pregId = pregId;
	}


	public Integer getExme_enfcontrolresp_ID() {		
		return exme_enfcontrolresp_ID;
	}

	public void setExme_enfcontrolresp_ID(Integer exme_enfcontrolresp_ID) {
		this.exme_enfcontrolresp_ID = exme_enfcontrolresp_ID;
	}

	public int getLstReglasSize() {
		return lstReglasSize;
	}

	public void setLstReglasSize(int lstReglasSize) {
		this.lstReglasSize = lstReglasSize;
	}

	public boolean isFromRespuetas() {
		return fromRespuetas;
	}

	public void setFromRespuetas(boolean fromRespuetas) {
		this.fromRespuetas = fromRespuetas;
	}



	public byte[] getByteArr() {
		return byteArr;
	}



	public void setByteArr(byte[] byteArr) {
		this.byteArr = byteArr;
	}



	public String getFileNameByteArr() {
		return fileNameByteArr;
	}



	public void setFileNameByteArr(String fileNameByteArr) {
		this.fileNameByteArr = fileNameByteArr;
	}

	//lhernandez
	/**
	 * ID del cuestionario de paciente
	 * uso en PHR
	 * @param pacRespId
	 */
	public Integer getPacCuestId() {
		return pacCuestId;
	}
	/**
	 * ID del cuestionario de paciente
	 * uso en PHR
	 * @param pacRespId
	 */
	public void setPacCuestId(Integer pacCuestId) {
		this.pacCuestId = pacCuestId;
	}
	/**
	 * ID de la Respuesta del cuestionario de paciente
	 * uso en PHR
	 * @param pacRespId
	 */
	public Integer getPacRespId() {
		return pacRespId;
	}
	/**
	 * ID de la Respuesta del cuestionario de pacientes
	 * uso en PHR
	 * @param pacRespId
	 */
	public void setPacRespId(Integer pacRespId) {
		this.pacRespId = pacRespId;
	}
	/**
	 * Respuesta de tipo imagen
	 * uso en PHR
	 * @param pacRespId
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * Respuesta de tipo imagen
	 * uso en PHR
	 * @param pacRespId
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	//lhernandez
	
	public int getPreguntaListaID() {
		return preguntaListaID;
	}

	public void setPreguntaListaID(int preguntaListaID) {
		this.preguntaListaID = preguntaListaID;
	}



	public ArrayList<RespuestaList_VO> getRespuestasMulti() {
		return respuestasMulti;
	}



	public void setRespuestasMulti(ArrayList<RespuestaList_VO> respuestasMulti) {
		this.respuestasMulti = respuestasMulti;
	}
}


