/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.util;


/**
 * Manejo de los flujos de trabajo
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/11/02 21:05:59 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.9 $
 */
public class Workflow {

//	//identificador del proceso
//	int proceso = 0;
//
//	//identificador del workflow
//	int workflow = 0;
//
//	//identificador de la instancia
//	int instancia = 0;
//
//	//el nombre del workflow
//	String nombre = null;
//
//	//identificador de la tabla
//	int tablaID = 0;
//
//	private String trxName;
//	
//	/** Static Logger           */
//    private static CLogger  s_log   = CLogger.getCLogger (Workflow.class);
//
//
//	public Workflow() {
//	}
//	
//	public Workflow(String trxName) {
//		this.trxName = trxName;
//	}
//
//	/**
//	* Obtiene la informacion necesaria del flujo de trabajo
//	* para la tabla indicada
//	*/
//	public void getInfo(String tabla) throws Exception {
//
//		//obtenemos los datos del flujo de trabajo
//		ResultSet rs = getProcessInfo(tabla);
//
//		if (rs.next()) {
//			proceso = rs.getInt("AD_Process_ID");
//			if ( proceso==0 )
//				s_log.log(Level.SEVERE, " --Workflow.getInfo SUPER ERROR  " );;
//			s_log.log(Level.SEVERE, " --Workflow.getInfo 03 " );;
//			workflow = rs.getInt("AD_Workflow_ID");
//
//			nombre = rs.getString("Name");
//
//			tablaID = rs.getInt("AD_Table_ID");
//
//		} else {
//			s_log.log(Level.SEVERE, "Process does not exists.");
//			rs.close();
//			rs = null;
//
//			throw new Exception ("error.noExisteProceso");
//
//		}
//		rs.close();
//		rs = null;
//	}
//
//	/**
//	 * Inicia el flujo de trabajo
//	 * @throws Exception 
//	 */
//	public void startWorkflow(Properties ctx, int recordID) throws Exception {
//
//		//Crear un nuevo ProcessInfo (nombre del proceso, id. proceso)
//		ProcessInfo pi = new ProcessInfo(nombre, proceso, tablaID, recordID);
//		pi.setTransactionName(trxName);
//
//		if (pi == null){
//			s_log.log(Level.SEVERE, " --Workflow.startWorkflow 01 " );
//			throw new Exception("Workflow does not exists.");
//		}
//		// Creamos una instancia del Proceso
//
//		MProcess process = MProcess.get(ctx, proceso);
//
//		if (process == null){
//			s_log.log(Level.SEVERE, " --Workflow.startWorkflow 02 " );
//			throw new Exception("Workflow does not exists.");
//		}
//		MPInstance pInstance = new MPInstance(process, recordID);
//		
//		if(pInstance==null){
//			s_log.log(Level.SEVERE, " --Workflow.startWorkflow 03 " );
//			throw new Exception("Workflow does not exists.");
//		}
//		//asignarle el identificador de la instancia
//		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
//
//		//y del registro
//		pi.setRecord_ID(recordID);
//
//		// usuario
//		pi.setAD_User_ID(Env.getAD_User_ID(ctx));
//		
//		//obtener un objeto MWorkflow a partir del contexto e id. workflow
//		MWorkflow wf = MWorkflow.get (ctx, workflow);
//		if(wf != null) {
//			MWFProcess retValue = wf.startWait(pi);
//		} else {
//			pi = null;
//			process = null;
//			wf = null;
//			
//			s_log.log(Level.SEVERE, "Workflow is null.");
//			
//			throw new Exception("Workflow does not exists.");
//		}
//
//	}
//	
//	/**
//	 *  Obtenemos el identificador del proceso y del workflow a iniciar
//	 *
//	 *@param  tabla          Tabla que contiene el proceso
//	 *@return                Un ResultSet con la informacion del proceso y el
//	 *      workflow
//	 *@exception  Exception  Description of the Exception
//	 */
//	public static ResultSet getProcessInfo(String tabla) throws Exception {
//		String sql = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		//obtener el identificador y el nombre del proceso
//		sql = "SELECT AD_Process.AD_Process_ID, AD_Process.AD_Workflow_ID, "
//				 + "AD_Process.Name, AD_Table.AD_Table_ID "
//				 + "FROM AD_Process, AD_Column, AD_Table "
//				 + "WHERE AD_Process.AD_Process_ID = AD_Column.AD_Process_ID AND "
//				 + "AD_Column.AD_Table_ID = AD_Table.AD_Table_ID AND "
//				 + "ColumnName = 'DocAction' AND TableName = ?";
//		
//		//sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"AD_Process");
//
//		pstmt = null;
//		rs = null;
//
//		try {
//			pstmt = DB.prepareStatement(sql, null);
//			pstmt.setString(1, tabla);
//			rs = pstmt.executeQuery();
//		} catch (SQLException e) {
//			if (WebEnv.DEBUG) {
//				s_log.log(Level.SEVERE,sql + "   tabla: " + tabla);
//			}
//
//			e.printStackTrace();
//			throw new Exception("error.getProcessInfo");
//		}
//
//		return rs;
//	}
	
	
//	/**
//	 * Dispara el workflow con la accion: Completar
//	 * valida que el estatus sea el correcto y actualiza la informacion del objeto
//	 * 
//	 * @param errorList Listado de errores
//	 * @param poModel Objeto de tipo DocAction y PO
//	 * @param trxName Transaccion
//	 */
//	public static void processModelWF(ErrorList errorList, PO objPO, String action, String trxName) {
//		try {
//			if (errorList.isEmpty() && objPO instanceof DocAction) {
//				DocAction poModel = (DocAction) objPO;
//				objPO.set_TrxName(trxName);
//				if (poModel.processIt(action)){
//					if (!poModel.save()) {
//						errorList.add(new Error(Error.EXCEPTION_ERROR, new MedsysException().getLocalizedMessage()));
//					}
//				} else {
//					errorList.add(new Error(Error.EXCEPTION_ERROR, poModel.getProcessMsg()));
//				}
//			}
//		} catch (Exception ex) {
//			errorList.add(Error.EXCEPTION_ERROR, ex.getMessage());
//		}
//	}
}