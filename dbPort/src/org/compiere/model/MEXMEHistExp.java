package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.QuickSearchTables;
import org.compiere.util.Utilerias;


/**
 * Modelo para Control de Expedientes
 *
 * <b>Fecha:</b> 02/Marzo/2006<p>
 * <b>Modificado: </b> $Author: mrojas $<p>
 * <b>En :</b> $Date: 2006/08/11 02:26:22 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.4 $
 */
public class MEXMEHistExp extends X_EXME_Hist_Exp {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6140013235494466286L;
	
	
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEHistExp.class);
    public final static String DOC_TYPE_EXPEDIENTE = "Medical Record";

    /**
     * @param ctx
     * @param ID
     */
    public MEXMEHistExp(Properties ctx, int ID, String trx) {
        super(ctx, ID, trx);
    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEHistExp(Properties ctx, ResultSet rs, String trx) {
        super(ctx, rs, trx);
    }

    /**
     *  After Save
     *  @param newRecord new
     *  @param success success
     *  @return success
     */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success)
			return success;
		try {
			QuickSearchTables.checkTables(MEXMEPacienteExpedienteV.class, MEXMEPacienteExpedienteV.Table_Name, getEXME_Paciente_ID(), get_TrxName(),
				p_ctx);
			// Si alguien ocupa la vista MEXMEPacienteCtaV y crea indices con subfijo descomentar lineas siguientes
			// QuickSearchTables.deleteIndexes(getCtx(), MEXMEPacienteCtaV.Table_Name, null);
		} catch (Exception ex) {
			log.log(Level.WARNING, "QuickSearchTables.checkTables", ex);
		}
		// Si es nuevo registro, o cambio el mrn hay que actualizar
		if (newRecord || is_ValueChanged(MEXMEHistExp.COLUMNNAME_DocumentNo)) {
			MEXMECtaPayer.checkIndex(p_ctx, getEXME_Paciente_ID(), get_TrxName());
		}
		MEXMEPacienteCtaV.updateSearchPac(p_ctx, getEXME_Paciente_ID(), get_TrxName());
		if (!newRecord) {
			MEXMECtaPacV.updateIndex(getCtx(), getEXME_Paciente_ID(), getAD_Org_ID(), get_TrxName());
		}
		return true;
	}

    /**
     * Devulve la informacion de un determinado expediente
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @return
     */
    public static MEXMEHistExp get(Properties ctx, String whereClause, 
            String orderBy, String trxName){
      
        MEXMEHistExp result = null;

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT EXME_Hist_Exp.*  FROM EXME_Hist_Exp ");
		sql.append(" WHERE EXME_Hist_Exp.Isactive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (whereClause != null)
			sql.append(whereClause);

		if (orderBy != null && orderBy.length() > 0)
			sql.append(" ORDER BY ").append(orderBy);

        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new MEXMEHistExp(ctx, rs, trxName);
            }
    
    	} catch (Exception e) {
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return result;
    }
    
    /**
     * Devuelve la informacion de un determinado expediente
     * @param ctx
     * @param whereClause
     * @param orderBy
     * @param trxName
     * @return
     * @deprecated Metodo que no tiene referencia alguna. No se corrigio lo de rownum. Jesus Cantu.
     *
    public static List<MEXMEHistExp> getLines(Properties ctx, String whereClause, 
            String orderBy, boolean block, String orderBlock, /*MEXMEMejoras mejora,*
            String trxName){
    	List<MEXMEHistExp> lista = new ArrayList<MEXMEHistExp>();
        StringBuilder sql = new StringBuilder("SELECT EXME_Hist_Exp.* ")
                    .append("FROM EXME_Hist_Exp WHERE EXME_Hist_Exp.Isactive = 'Y' ");
        if (whereClause != null)
            sql.append(whereClause);
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",Table_Name));
        if(orderBy != null && orderBy.length() > 0)
            sql.append(" ORDER BY ").append(orderBy);
        if(block){
            sql = new StringBuilder("SELECT * FROM (").append(sql).append(") WHERE ROWNUM <= ");
            // Para el Numero de registros por bloque se tomar� el valor definido en la tabla
            // EXME_Mejoras siempre y cuando este sea mayor a cero, de lo contrario, 
            // o de no existir registro en mejoras, tomar� el valor del "Paths.properties"
            /*sql.append(mejora != null && mejora.getRegBloque() > 0
                    ? mejora.getRegBloque() 
                            : prop.getProperty(WebEnv.NoRegistros));*
            sql.append(MEXMEMejoras.getMaxQueryRecords(ctx));
            if(orderBlock!=null && !orderBlock.equals("")){
                sql.append(" ORDER BY ROWNUM ").append(orderBlock);
            }
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	MEXMEHistExp result = new MEXMEHistExp(ctx, rs, trxName);
            	lista.add(result);
            }
    	} catch (Exception e) {
    		log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return lista;
    } */
    
    /** */
    private MEXMEPaciente paciente = null; 
    
    /**
     * Obtenemos el Paciente 
     * @return
     */
	public MEXMEPaciente getPaciente() {
		if (paciente == null || getEXME_Paciente_ID() > 0) {
			paciente = new MEXMEPaciente(getCtx(), getEXME_Paciente_ID(), get_TrxName());
		}
		return paciente;
	}
    
	protected boolean beforeSave (boolean newRecord) {
		try {
			//Si no se ingreso un numero de historia
			if (getEXME_Paciente_ID() == 0){ 
				log.saveError("SaveError", Utilerias.getMsg(getCtx(),"odontologia.msj.Paciente"));
				return false;
			}
			//validamos para un nuevo registro
			if(newRecord){
				// checar que no tenga un expediente asignado con el mismo numero de documento y activo
				final MEXMEHistExp histExp = get(getCtx(), getEXME_Paciente_ID(), true, null);
				if (histExp != null && !histExp.getDocumentNo().equals(getDocumentNo())) {
					log.saveError("", Utilerias.getMessage(getCtx(), null, "error.expediente.existe", histExp.getDocumentNo()));
					return false;
				}
			} else {
				// un expediente NO DEBE cambiar de paciente
				if (is_ValueChanged(COLUMNNAME_EXME_Paciente_ID)) { 
					log.saveError("", Utilerias.getMessage(getCtx(), null, "captMov.error.addExpediente",getDocumentNo()));
					return false;
				}
			}
			if (getC_DocType_ID() == 0) {
				//int n = Datos.getTipoDoc(getCtx(), DOC_TYPE_EXPEDIENTE);
				setC_DocType_ID(MEXMEDocType.getOfName(getCtx(),  DOC_TYPE_EXPEDIENTE, null));
			}
			if (getDocumentNo() == null) {
				setDocumentNo(DB.getDocumentNo(getC_DocType_ID(), null, false));
			}
			// Si el usuario selecciona un Numero de expediente diferente de 0... !"0".equals(getDocumentNo())? porque....
			if (newRecord || is_ValueChanged(COLUMNNAME_DocumentNo)) { 
				// Buscamos el expediente por su Numero (getExpediente())
				final int histExp = getHistExp(getCtx(), getDocumentNo());
				// Registro encontrado. No puede grabar.
				if (histExp > 0 && (newRecord || histExp != get_ID())) {
					log.saveError("SaveError", Utilerias.getMsg(getCtx(), "error.expediente.no"));
					return false;
				}
			}
		} catch (Exception e) {
			log.saveError("SaveError",Utilerias.getMsg(getCtx(), "error.ts.noguardado"));
			return false;
		}
		return true;
	}
	
	@Override
	public void setDocumentNo(String DocumentNo) {
		if (StringUtils.isNotBlank(DocumentNo)) {
			super.setDocumentNo(StringUtils.upperCase(DocumentNo));
		}
	}

	/**
	 * Verifica si existe el paciente ya con un expediente
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEHistExp getExiste(Properties ctx, int EXME_Paciente_ID, String trxName) {
		final Query query = new Query(ctx, Table_Name, " EXME_Hist_Exp.EXME_Paciente_ID=? ", trxName)//
			.setParameters(EXME_Paciente_ID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true);

		final MEXMEHistExp result = query.first();
		if (result != null) {
			int count = query.count();
			if (count > 1) {
				log.log(Level.WARNING, "The patient " + EXME_Paciente_ID + " has a total of " + count + " expedients");
			}
		}
//		MEXMEHistExp result = null;
//        StringBuilder sql = new StringBuilder();
//        
//         sql.append(" SELECT EXME_Hist_Exp.* FROM EXME_Hist_Exp ")
//            .append(" WHERE EXME_Hist_Exp.Isactive = 'Y' AND EXME_Hist_Exp.EXME_Paciente_ID = ? ");
//       
//        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            pstmt = DB.prepareStatement(sql.toString(), trxName);
//            pstmt.setInt(1, EXME_Paciente_ID);
//            rs = pstmt.executeQuery();
//            int count = 0;
//            if (rs.next()) {
//                result = new MEXMEHistExp(ctx, rs, trxName);
//                count++;
//            }
//            while(rs.next()){
//            	count++;
//            }    
//            if(count > 1){
//            	log.log(Level.WARNING, "The patient " + EXME_Paciente_ID + " has a total of " + count + " expedients");
//            }
//    	} catch (Exception e) {    		
//    		log.log(Level.SEVERE, sql.toString(), e);
//    	}finally {
//    		DB.close(rs, pstmt);
//    	}
		return result;
	}
    
    /**
	 * Obtiene la historia del paciente
	 * 
	 * @param ctx Contexto
	 * @param pacienteID  Id del paciente
	 * @param isActive Registros activos
	 * @param trxName Trx Name
	 * @return Historia o null si no cuenta con ella
	 */
	public static MEXMEHistExp get(Properties ctx, int pacienteID, boolean isActive, String trxName) {
//		StringBuilder st = new StringBuilder("select * from EXME_Hist_Exp ");
//		st.append(" where EXME_Paciente_ID = ? and isActive = ? ");
//		st.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		MEXMEHistExp retValue = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(st.toString(), null);
//			pstmt.setInt(1, pacienteID);
//			pstmt.setString(2, DB.TO_STRING(isActive));
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retValue = new MEXMEHistExp(ctx, rs, trxName);
//			}
//		} catch (Exception e) {
//			log.log(Level.FINEST, st.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return new Query(ctx, Table_Name, " EXME_Paciente_ID=? AND isActive=? ", trxName)
		.setParameters(pacienteID, DB.TO_STRING(isActive))
		.addAccessLevelSQL(true)
		.first();
	}
	
	/** @deprecated Requiere contexto, use: {@link #getNext(Properties)}*/
	public static String getNext(){
		return DB.getDocumentNo(MEXMEDocType.getOfName(Env.getCtx(), DOC_TYPE_EXPEDIENTE, null), null, false);
	}

	/** @deprecated Requiere contexto, use: {@link #getHistExp(Properties, String)}*/
	public static int getHistExp(String document){
//		StringBuilder sql = new StringBuilder("SELECT EXME_HIST_EXP_ID FROM EXME_HIST_EXP WHERE DOCUMENTNO = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(Env.getCtx(), " ", Table_Name));
//		
//		return DB.getSQLValue(null, sql.toString() , document);
		return getHistExp(Env.getCtx(), document);
	}
	
	
	public static int getHistExp(Properties ctx, String document){
//		StringBuilder sql = new StringBuilder("SELECT EXME_HIST_EXP_ID FROM EXME_HIST_EXP WHERE DOCUMENTNO = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		return DB.getSQLValue(null, sql.toString() , document);
		return new Query(ctx, Table_Name, " EXME_Hist_Exp.DocumentNo=? ", null)
		.setParameters(document)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.firstId();
	}
	
	/**
	 * Obtener el siguiente expediente disponible
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getNext(Properties ctx) {
		String document = DB.getDocumentNo(MEXMEDocType.getOfName(ctx, MEXMEHistExp.DOC_TYPE_EXPEDIENTE, null), null, false);
		if (MEXMEHistExp.getHistExp(ctx, document) > 0) {
			document = MEXMEHistExp.getNext(ctx);
		}
		return document;
	}
	/**@deprecated solo usada en clases struts */
	public static MEXMEHistExp getByValue(Properties ctx,String value, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Hist_Exp.DocumentNo=? ", trxName)
		.setParameters(value)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.first();
	}
	/**@deprecated use {@link #get(Properties, int, boolean, String)} instead */
	public static MEXMEHistExp getForPaciente(Properties ctx, int EXME_Paciente_ID, String trxName) {
		return new Query(ctx, MEXMEHistExp.Table_Name, " EXME_Hist_Exp.EXME_Paciente_ID=? ", trxName)
		.setParameters(EXME_Paciente_ID)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.first();
	}
}