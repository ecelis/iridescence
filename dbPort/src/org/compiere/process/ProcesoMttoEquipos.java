package org.compiere.process;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MEXMEConfigInt;

public class ProcesoMttoEquipos extends SvrProcess {
	
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = (new BigDecimal(para[i].getParameter().toString())).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		
	}
	
	//	@Override
	protected String doIt() throws Exception {
		
		log.info("Executing daily charges process ...");
		
		//Leer Archivo if (e.getSource() == bFile)
		loadFile();
		
		MttoEquipos importacion = new MttoEquipos(getCtx(), m_AD_Client_ID, m_AD_Org_ID,
				m_deleteOldImported, get_TrxName());
		LabelValueBean result = importacion.doIt();
		addLog (0, null, new BigDecimal (result.getLabel()), "@Errors@");
		addLog (0, null, new BigDecimal (result.getValue()), "@EXME_Equipoh_ID@: @Inserted@");
		
		return "The daily charges have been applied.";
	}
	
	
	/**************************************************************************
	 *	Load File
	 * @throws FileNotFoundException 
	 */
	private void loadFile() throws FileNotFoundException 
	{
		
		//Ruta del archivo a importar
		MEXMEConfigInt inter = MEXMEConfigInt.get(getCtx(), null,null);
		//inter.getInterfase_Equipos_R();
		
		String directory = inter.getInterfase_Equipos_R();/*org.compiere.Compiere.getCompiereHome() 
		+ File.separator + "data" 
		+ File.separator + "import"
		+ File.separator + inter.getInterfase_Equipos_R();*/
		log.config(directory);
		
		//http://www.hijodelared.com.ar/?p=595
		//Le pasamos la URL del archivo CSV a leer.
		/*CsvReader reader = new CsvReader(directory);
		long count = 0;
		long errores = 0; 
		try {
			ArrayList<String>	m_data = new ArrayList<String>();
			ImpFormat 			m_format;
			int					m_record = -1;
			
			m_format = ImpFormat.load ("Importar EquipoH");
			if (m_format == null)
			{
				log.info("no hay formato asociado");
				return;
			}
			
			SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //LRHU. Cambio HH24:mi por HH:mm
			
			//Lista con las lineas que se utilizaran
			while (reader.readRecord())
			{
				//Line Info
				String[] lInfo = m_format.parseLine(reader.getRawRecord(), false, true, false);	//	no label, trace, no ignore
				int size = m_format.getRowCount();
				
				if (lInfo.length != size)
				{
					log.log(Level.SEVERE, "FormatElements=" + size + " != Fields=" + lInfo.length);
				}
				else
				{
				
					//reader.get(Integer) - devuelve el contenido del numero de columna que le pasamos.
					X_I_EXME_EquipoH iEquipoH = new X_I_EXME_EquipoH(getCtx(), 0, null);
					iEquipoH.setCodigo_Equipo(reader.get(0).trim());
					
					
					Date fechaIni = null;
					Date fechaFin = null;
					boolean fechaInvalid = false;
					try {
						fechaIni = sdfFechaHora.parse(reader.get(1).trim());
						fechaFin = sdfFechaHora.parse(reader.get(2).trim());
					} catch (ParseException e) {
						e.printStackTrace();
						fechaInvalid = true;
					}
					
					if(!fechaInvalid && fechaIni != null && fechaFin != null)
					{
					
						iEquipoH.setFecha_Ini(sdfFechaHora.format(fechaIni));
						iEquipoH.setFecha_Fin(sdfFechaHora.format(fechaFin));
						
						log.log(Level.WARNING, 
								"Filas: " + iEquipoH.getCodigo_Equipo() + 
								"-" + iEquipoH.getFecha_Ini() +
								"-" + iEquipoH.getFecha_Fin());
						
						if(iEquipoH.save(get_TrxName()))
						{
							count ++;
						}
						else
						{
							errores ++;
						}
					}//Fin fechas inicio final validas
					
					
					
				}//Fin format valido
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			log.log(Level.SEVERE, "", e);
		}
		finally
		{
			if(reader!=null)
				reader.close();
			reader=null;
		}
		*/
		//log.config("Records=" + count +", Errores=" + errores);
		
		
	}	//	cmd_loadFile
}
