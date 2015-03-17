package com.ecaresoft.acct.xml;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Generador de Archivos XML de contabilidad Uso :</br>
 * 
 * <pre>
 * <code>
 * {@link XmlFileGenerator} xmlFileGenerator = new {@link XmlFileGenerator}();
 * xmlFileGenerator.setClazz(clazz);
 * xmlFileGenerator.setObj(obj);
 * xmlFileGenerator.setValidator(obj);
 * xmlFileGenerator.setPrefix(prefix);
 * 
 * {@link ErrorList} errorList = xmlFileGenerator.generateFile();
 * if(errorList != null && errorList.isEmpty(){
 * 	{@link File} file = errorList.getXmlFile();
 * 	// Hacer algo con el archivo
 * } else {
 * 	// Mostrar errores de la lista si no es nula
 * }</code>
 * </pre>
 * 
 * @author odelarosa
 *
 */
public class XmlFileGenerator {
	private static final CLogger S_LOG = CLogger.getCLogger(XmlFileGenerator.class);
	private Class<?> clazz;
	private Object obj;
	private String prefix;
	private XmlValidator validator;
	private File xmlFile;
	private String schemaLocation;

	public XmlFileGenerator(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}

	private File createFile() {
		File file = null;
		try {

			file = File.createTempFile(prefix, ".xml", new File(MAttachmentEntry.getTmpDirectory()));
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(obj, file);

		} catch (JAXBException | IOException e) {
			S_LOG.log(Level.SEVERE, null, e);
		}

		return file;
	}

	public ErrorList generateFile() {
		ErrorList errorList = new ErrorList();

		validation: {

			if (clazz == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.claseNoDefinida"));
				break validation;
			}

			if (obj == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.noDatosParaProcesar"));
				break validation;
			}

			if (StringUtils.isBlank(prefix)) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.noPrefijo"));
				break validation;
			}

			if (validator == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.noValidador"));
				break validation;
			}

			ErrorList errorList2 = validator.validate();

			if (errorList2 != null && !errorList2.isEmpty()) {
				errorList.getList().addAll(errorList2.getList());
				break validation;
			}

		}

		if (errorList.isEmpty()) {
			xmlFile = createFile();

			if (xmlFile == null) {
				errorList.add(Error.EXCEPTION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "advancedDirectives.msj.error.generarArchivo"));
			}
		}

		return errorList;
	}

	public File getXmlFile() {
		return xmlFile;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setValidator(XmlValidator validator) {
		this.validator = validator;
	}

}
