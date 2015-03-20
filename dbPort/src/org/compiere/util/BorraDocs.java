package org.compiere.util;

import java.io.File;
import java.util.Calendar;

import org.compiere.process.SvrProcess;


/**
 * Realiza el borrado de documentos word que se encuentran en utils/tmp que sean
 * del dia anterior al actual
 * @author Jose Isaac Garcia 
 * @author mvrodriguez
 */
public class BorraDocs extends SvrProcess {
	
	private static final String FOLDER_UTILS = "utils";
	private static final String FOLDER_TMP = "tmp";
	private static final String EXT_DOC = "tmp";
	private static final String PROGRAM = "MS Office Word";
	private static final String EXITO_SI = "si";
	private static final String EXITO_NO = "no";
	
	private ExtensionFileFilter exfil;
	private File carpetaTemp;
	private Calendar ayer;

	@Override
	protected String doIt() throws Exception {
		
		String exito = EXITO_NO;

		if(carpetaTemp.listFiles()!= null){
			
			File[] archivosExistentes = carpetaTemp.listFiles();
			
			for (File archivo : archivosExistentes){
				
				if(exfil.accept(archivo) && ayer.getTimeInMillis() > archivo.lastModified()){
					
					archivo.delete();
					exito = EXITO_SI;
					
				}
				
			}
			
		}
		
		return exito;
		
	}

	@Override
	protected void prepare() {
		
		carpetaTemp = new File(Ini.getCompiereHome() + File.separator + FOLDER_UTILS + File.separator + FOLDER_TMP + File.separator);
		
		exfil = new ExtensionFileFilter(EXT_DOC,PROGRAM);
		
		//Instancia de Fecha de un dia anterior al presente.
		ayer = Calendar.getInstance();
		ayer.roll(Calendar.DAY_OF_MONTH, -1);
		
	}
}