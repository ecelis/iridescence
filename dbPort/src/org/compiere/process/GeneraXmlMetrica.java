package org.compiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Genera un xml con las etiquetas definidas en el 
 * PQRI 2009 Registry XML Specifications
 * @author Rosy Velazquez
 *
 */
public class GeneraXmlMetrica {
	
	private Namespace ns; 
	private Properties ctx = null;
	private String fecha="";
	private String tiempo="";
	
	private String npi = "";
	private String tin = "";
	private String org = "";
	
	private String from = "";
	private String to = "";
	
	private String registryOption = "";
	private String version = "";
	private String versionXml = "4.0";
	
	private Double numeratorGrupo = 0.0;
	private Double denominatorGrupo = 0.0;	
	private String registry_name = "Model Registry";
	
	DecimalFormat df = new DecimalFormat("#0.00");
	
	Namespace ns2;
	
	private static CLogger s_log = CLogger.getCLogger(GeneraXmlMetrica.class);
	
	GeneraXmlMetrica(Properties ctx) {
		this.ctx = ctx;
	}
	
	/**
	 * Crea Element raiz
	 * @return root
	 */
	private Element creaRoot() {
		Element root = new Element("submission");
		
		root.setAttribute("type", "PQRI-REGISTRY"); //Sin Namespace !
		root.setAttribute("option", registryOption);
		root.setAttribute("version", versionXml);
		ns2 = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("noNamespaceSchemaLocation", "Registry_Payment.xsd", ns2); 
																
		return root;
	}
	
	/**
	 * Crea Element para audit data
	 * @param root
	 * @param ns
	 * @return Element auditData
	 */
	private Element creaAuditData(Element root, Namespace ns){
		
		Element auditData = new Element("file-audit-data", ns);		
		Element createDate = new Element("create-date", ns);
		Element createTime = new Element("create-time", ns);
		Element createby = new Element("create-by", ns);
		Element version = new Element("version", ns);
		Element fileNumber = new Element("file-number", ns);
		Element numFiles = new Element("number-of-files", ns);
	
		//Asignar atributo-valor a subelementos
				
		createDate.setText(fecha); //Fecha de creacion del archivo
		createTime.setText(tiempo); //Tiempo de creacion del archivo
		createby.setText(this.org); //Organizacion
		version.setText(this.version);  //Cuando cambia de version?
		fileNumber.setText("1"); //Numero del archivo ??
		numFiles.setText("1"); //Numero de archivos totales ¡?
		
		//Agregar subelementos a nodo padre
		auditData.addContent(createDate);
		auditData.addContent(createTime);
		auditData.addContent(createby);
		auditData.addContent(version);
		auditData.addContent(fileNumber);
		auditData.addContent(numFiles);
		
		return auditData;
	}
	
	/**
	 * Crea Element para audit data
	 * @param root
	 * @param ns
	 * @return Element auditData
	 */
	private Element creaAuditData(Element root, Namespace ns, int file, int files){
		
		Element auditData = new Element("file-audit-data", ns);		
		Element createDate = new Element("create-date", ns);
		Element createTime = new Element("create-time", ns);
		Element createby = new Element("create-by", ns);
		Element version = new Element("version", ns);
		Element fileNumber = new Element("file-number", ns);
		Element numFiles = new Element("number-of-files", ns);
	
		//Asignar atributo-valor a subelementos
				
		createDate.setText(fecha); //Fecha de creacion del archivo
		createTime.setText(tiempo); //Tiempo de creacion del archivo
		createby.setText(this.org); //Organizacion
		version.setText(this.version);  //Cuando cambia de version?
		fileNumber.setText(String.valueOf(file)); //Numero del archivo ??
		numFiles.setText(String.valueOf(files)); //Numero de archivos totales ¡?
		
		//Agregar subelementos a nodo padre
		auditData.addContent(createDate);
		auditData.addContent(createTime);
		auditData.addContent(createby);
		auditData.addContent(version);
		auditData.addContent(fileNumber);
		auditData.addContent(numFiles);
		
		return auditData;
	}
	
	/**
	 * Crea el Element del registro
	 * @param root
	 * @param ns
	 * @return Element registry
	 */
	private Element creaRegistry(Element root, Namespace ns, List<String[]> pqris){
		
		Element registry = new Element("registry", ns);		
		Element registryName = new Element("registry-name", ns);
		Element registryId = new Element("registry-id", ns);
		Element submissionMethod = new Element("submission-method", ns);		
	
		//Asignar atributo-valor a subelementos
		registryId.setText(this.tin); //use registry corporate TIN number				
				
		String submission = getSubmissionMethod(pqris);
		submissionMethod.setText(submission);
		
		registryName.setText(registry_name);
		
		//Agregar subelementos a nodo padre
		registry.addContent(registryName);
		registry.addContent(registryId);
		registry.addContent(submissionMethod);		
				
		return registry;
	}
	
	/**
	 * Obtiene el submission method
	 * @param pqris
	 * @return submission
	 * */
	private String getSubmissionMethod(List<String[]> pqris){
		
		String submission = "C";
		/* 
		  Segun estandar de xml
		  Submission Method: (A-F)
			A = 12 months, 80%, 3 or more measures
			B = 6 months, 80%, 3 or more measures
			C = 12 months, 30 consecutive, measure group 
			E = 12 months, 80%, measure group
			F = 6 months, 80%, measure group 
		 */		
		
		Double num = 0.0;
		Double den = 0.0;
		
		for(int i = 0 ; i < pqris.size(); i++){
			num = num + Double.valueOf(pqris.get(i)[1]); //Suma de numeradores del grupo
			den = den + Double.valueOf(pqris.get(i)[2]); //Suma de denominadores del grupo			
		}
		
		double porcentaje = den == 0 ? 0 : (num/den);				
		porcentaje = porcentaje * 100; //Porcentaje
		
		if(porcentaje >= 80){
			submission = "E";
		}
		
		return submission;
	}
	
	/**
	 * Crea el Element para el grupo de medidas
	 * @param root
	 * @param ns
	 * @return Element measureGroupStat
	 */
	private Element CreaMeasureGroupStat(Element root, Namespace ns, List<String[]> pqris){
		
		Element measureGroupStat = new Element("measure-group-stat", ns);		
		Element ffsPatientCount = new Element("ffs-patient-count", ns);
		Element rateNumerator = new Element("group-reporting-rate-numerator", ns);
		Element eligibleInstances = new Element("group-eligible-instances", ns);
		Element reportingRate = new Element("group-reporting-rate", ns);					
		
		for(int i = 0 ; i < pqris.size(); i++){
			numeratorGrupo = numeratorGrupo + Double.valueOf(pqris.get(i)[1]); //Suma de numeradores del grupo
			denominatorGrupo = denominatorGrupo + Double.valueOf(pqris.get(i)[2]); //Suma de denominadores del grupo			
		}		
				
		//Asignar atributo-valor a subelementos
		rateNumerator.setText(String.valueOf(numeratorGrupo.intValue()));
		eligibleInstances.setText(String.valueOf(denominatorGrupo.intValue()));
		ffsPatientCount.setText(String.valueOf(numeratorGrupo.intValue())); 
		
		//Validar division entre cero
		double rate = denominatorGrupo == 0 ? 0 : (numeratorGrupo/denominatorGrupo);		
		
		rate = rate * 100; //Porcentaje
		String rateStr = df.format(rate);
		reportingRate.setText(rateStr);
		
		//Agregar subelementos a nodo padre
		measureGroupStat.addContent(ffsPatientCount);
		measureGroupStat.addContent(rateNumerator);
		measureGroupStat.addContent(eligibleInstances);
		measureGroupStat.addContent(reportingRate);		
		
		return measureGroupStat;
	}		
	
	/**
	 * Crea el Element con las medidas del pqri
	 * @param root
	 * @param ns
	 * @return Element pqriMeasure
	 */
	private Element creaPqri(Namespace ns, String[] pqri){
		
		Element pqriMeasure = new Element("pqri-measure", ns);		
		Element pqriMeasureNumber = new Element("pqri-measure-number", ns);
		Element eligibleInstances = new Element("eligible-instances", ns);
		Element meetsPerformanceInstances = new Element("meets-performance-instances", ns);
		Element performanceExclusionInstances = new Element("performance-exclusion-instances", ns);
		Element performanceNotMetInstances = new Element("performance-not-met-instances", ns);
		Element reportingRate = new Element("reporting-rate", ns);
		Element performanceRate = new Element("performance-rate", ns);
		
		//Asignar atributo-valor a subelementos
		pqriMeasureNumber.setText(pqri[0]); //pqri number 
		eligibleInstances.setText(pqri[2]); //reporting denominator
		meetsPerformanceInstances.setText(pqri[1]); // performance numerator
		performanceExclusionInstances.setText(pqri[3]); //Performance exclusions
		performanceNotMetInstances.setText(pqri[4]); // Performance not met instances
		
		double denominator = Double.valueOf(pqri[2]);
		double numerator = Double.valueOf(pqri[1]);
		double exclusion = Double.valueOf(pqri[3]);
		double notMet = Double.valueOf(pqri[4]);
		
		pqriMeasureNumber.setText(pqri[0]);
		eligibleInstances.setText(String.valueOf(denominator));
		meetsPerformanceInstances.setText(String.valueOf(numerator));
		performanceExclusionInstances.setText(String.valueOf(exclusion));
		performanceNotMetInstances.setText(String.valueOf(notMet));

		
		//Calculo del Pocentaje
		//Validar division entre cero
		double rate = denominator == 0 ? 0 : ((numerator + exclusion + notMet) / denominator);		
		
		rate = rate * 100;			
		String rateStr = df.format(rate);
		
		reportingRate.setText(rateStr); //Reporting rate
		
		BigDecimal performance_rate = BigDecimal.ZERO; // = Performance numerator / (Reporting numerator - performance exclusions)
		
		Double divisor = this.numeratorGrupo - exclusion;
		performance_rate = divisor == 0.0 ? BigDecimal.ZERO : (new BigDecimal(numerator / divisor));// (performance numerator)
		
		if(performance_rate == null){
			performanceRate.setAttribute("nil", "true", ns2);
		}else{
			performance_rate = performance_rate.multiply(new BigDecimal(100));
			String perfRate = df.format(performance_rate.doubleValue());
			performanceRate.setText(perfRate);
		}		
		
		//Agregar subelementos a nodo padre
		pqriMeasure.addContent(pqriMeasureNumber);
		pqriMeasure.addContent(eligibleInstances);
		pqriMeasure.addContent(meetsPerformanceInstances);
		pqriMeasure.addContent(performanceExclusionInstances);
		pqriMeasure.addContent(performanceNotMetInstances);
		pqriMeasure.addContent(reportingRate);
		pqriMeasure.addContent(performanceRate);				
		
		return pqriMeasure;
	}
	
	/**
	 * Crea el elemento Provider
	 * @param root
	 * @param measureGroupStat
	 * @param pqriMeasure
	 * @param ns
	 * @return Element provider
	 */
	private Element creaProvider(Element root, Element measureGroupStat, List<String[]> pqris, Namespace ns, String measureGroup){
		
		Element provider = new Element("provider", ns);		
		Element npi = new Element("npi", ns);
		Element tin = new Element("tin", ns);
		Element waiverSigned = new Element("waiver-signed", ns);
		Element encounterFromDate = new Element("encounter-from-date", ns);
		Element encounterToDate = new Element("encounter-to-date", ns);		
		
		//Asignar atributo-valor a subelementos
		npi.setText(this.npi);
		tin.setText(this.tin);
		waiverSigned.setText("Y"); // Solo permite Y,y
		encounterFromDate.setText(this.from);
		encounterToDate.setText(this.to);		
		
		//Agregar subelementos a nodo padre
		provider.addContent(npi);
		provider.addContent(tin);
		provider.addContent(waiverSigned);
		provider.addContent(encounterFromDate);
		provider.addContent(encounterToDate);
		
		if(!measureGroup.equalsIgnoreCase("X")){
			provider.addContent(measureGroupStat);
		}
		
		for(int i = 0 ; i < pqris.size(); i++){
			Element pqri = creaPqri(ns, pqris.get(i));
			provider.addContent(pqri);
		}					
		
		return provider;
	}
	
	/**
	 * Permite formar la estructura del xml
	 * @param archivo
	 * @param measureGroup
	 * @param fechaMin
	 * @param fechaMax
	 * @param numerator
	 * @param denominator
	 * @return Element root
	 */
	public boolean armaXml(String archivo, String measureGroup, Date fechaMin, Date fechaMax, List<String[]> pqris, String optionRegistry, String version){
		boolean generado = false;		
		registryOption = optionRegistry;
		this.version = version;
		
		asignaFecha(fechaMin, fechaMax);
		datosProvider();
		
		Element raiz = creaRoot();
		raiz.addContent(creaAuditData(raiz, ns));
		raiz.addContent(creaRegistry(raiz, ns, pqris));
		Element provider = creaProvider(raiz, CreaMeasureGroupStat(raiz, ns, pqris), pqris, ns, measureGroup);
		Element measureGrupo = new Element("measure-group", ns);
		
		measureGrupo.setAttribute("ID", measureGroup);
		
		measureGrupo.addContent(provider);
		raiz.addContent(measureGrupo);
		
		generado = generaXML(raiz, archivo);
		return generado;
	}
	
		
	/**
	 * Permite formar la estructura del xml
	 * @param archivo
	 * @param measureGroup
	 * @param fechaMin
	 * @param fechaMax
	 * @param numerator
	 * @param denominator
	 * @return Element root
	 */
	public File armaXml(String archivo, String measureGroup, Date fechaMin, Date fechaMax, List<String[]> pqris, String optionRegistry, String version, String option, int numFile, int numFiles){
				
		registryOption = optionRegistry;
		this.version = version;
		
		asignaFecha(fechaMin, fechaMax);
		datosProvider();
		
		Element raiz = creaRoot();
		raiz.addContent(creaAuditData(raiz, ns, numFile, numFiles));
		raiz.addContent(creaRegistry(raiz, ns, pqris));
		Element provider = creaProvider(raiz, CreaMeasureGroupStat(raiz, ns, pqris), pqris, ns, measureGroup);
		Element measureGrupo = new Element("measure-group", ns);
		
		measureGrupo.setAttribute("ID", measureGroup);
		
		measureGrupo.addContent(provider);
		raiz.addContent(measureGrupo);
				
		return generaXML(raiz, archivo, "1");
	}
	
	/**
	 * Permite formar la estructura del xml
	 * @param archivo
	 * @param measureGroup
	 * @param fechaMin
	 * @param fechaMax
	 * @param numerator
	 * @param denominator
	 * @return Element root
	 */
	public File armaXml(String archivo, String measureGroup, Date fechaMin, Date fechaMax, List<String[]> pqris, String optionRegistry, String version, String option){
				
		registryOption = optionRegistry;
		this.version = version;
		
		asignaFecha(fechaMin, fechaMax);
		datosProvider();
		
		Element raiz = creaRoot();
		raiz.addContent(creaAuditData(raiz, ns));
		raiz.addContent(creaRegistry(raiz, ns, pqris));
		Element provider = creaProvider(raiz, CreaMeasureGroupStat(raiz, ns, pqris), pqris, ns, measureGroup);
		Element measureGrupo = new Element("measure-group", ns);
		
		measureGrupo.setAttribute("ID", measureGroup);
		
		measureGrupo.addContent(provider);
		raiz.addContent(measureGrupo);
				
		return generaXML(raiz, archivo, "1");
	}
	
	/**
	 * Asigna las fechas necesarias: la fecha y hora de creacion del archivo y los rangos del periodo reportado.
	 * @param fechaMin
	 * @param fechaMax
	 */
	private void asignaFecha(Date fechaMin, Date fechaMax){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		fecha = format.format(new Date(cal.getTimeInMillis()));
		tiempo = StringUtils.replace(timeFormat.format(new Date(cal.getTimeInMillis())), ":", "-");
		
		this.from = format.format(fechaMin);
		this.to = format.format(fechaMax);
	}
	
	/**
	 * Genera archivo xml a partir del xmlData en la ruta especificada.
	 * @param xmlData
	 * @return Document
	 */
	public boolean generaXML(Element xmlData, String archivoName) {
			Document doc = new Document(xmlData);																
			boolean generado = true;
				try{
				      XMLOutputter out=new XMLOutputter(Format.getPrettyFormat());
				      String ruta = obtieneRuta();	
				      File directorio = new File(ruta);
				      //File archivo;
				      directorio.mkdir();
				      String nombre = archivoName + "_" + fecha + ".xml";
					  File archivo = new File(ruta, nombre);				       				      				      					  
					  
				      FileOutputStream file = new FileOutputStream(archivo, false);
				      out.output(doc,file);
				      file.flush();
				      file.close();
				      //out.output(doc,System.out);
				  }catch(Exception e){
					  s_log.log(Level.SEVERE, "generalXML()", e);
					  generado = false;
				  }				  
											
			return generado;
		}
		
	/**
	 * Genera archivo xml a partir del xmlData en la ruta especificada.
	 * @param xmlData
	 * @return Document
	 */
	public File generaXML(Element xmlData, String archivoName, String opcion) {
			Document doc = new Document(xmlData);																
			//boolean generado = true;
			File archivo = null;
				try{
				      XMLOutputter out=new XMLOutputter(Format.getPrettyFormat());
				      String ruta = obtieneRuta();	
				      File directorio = new File(ruta);
				      //File archivo;
				      directorio.mkdir();
				      String nombre = archivoName + "_" + fecha + " " + tiempo + ".xml";
					  archivo = new File(ruta, nombre);				       				      				      					  
					  
				      FileOutputStream file = new FileOutputStream(archivo, false);
				      out.output(doc,file);
				      file.flush();
				      file.close();
				      //out.output(doc,System.out);
				  }catch(Exception e){
					  s_log.log(Level.SEVERE, "generaXML()", e);
				  }				  
											
			return archivo;
		}
		/**
		 * Obtiene la ruta donde se guardara el archivo
		 * @return ruta
		 * @throws IOException
		 */
		private String obtieneRuta() throws IOException{
			String env = Ini.getCompiereHome();

			String propPath = env + File.separator + "CompiereEnv.properties";            
			FileInputStream fis = new FileInputStream(propPath);            
            Properties properties = new Properties();
            properties.load(fis);
            
			String ruta = env + File.separator + "CMS_QR" + File.separator;
			
			return ruta;
		}
		
		/**
		 * Obtiene los datos necesarios de la organizacion
		 */
		private void datosProvider(){
			
			StringBuilder sql = new StringBuilder("SELECT oi.taxid, oi.npi, org.name ");
			sql.append(" FROM ad_orginfo oi inner join ad_org org on org.ad_org_id = oi.ad_org_id ")
			.append(" WHERE oi.ad_org_id = ?");
			
			/*String sqlProvider = "SELECT oi.taxid, oi.npi, org.name as organizacion "
				+ " FROM ad_orginfo oi inner join ad_org org on org.ad_org_id = oi.ad_org_id "
 			    + " WHERE oi.ad_org_id = (SELECT DISTINCT parent_org_id FROM ad_orginfo WHERE parent_org_id IS NOT NULL)";*/
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try {	
				pstmt = DB.prepareStatement(sql.toString(), null);	
				pstmt.setInt(1, Env.getAD_Org_ID(ctx));
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					this.tin = rs.getString(1);
					this.npi = rs.getString(2);					
					this.org = rs.getString(3);
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}					
		}
}
