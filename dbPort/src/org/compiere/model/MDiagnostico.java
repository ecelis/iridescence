package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.struts.util.LabelValueBean;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GridItem;
import org.compiere.util.Utilerias;

/**
 * <b>Prop&oacute;sito : </b> Modelo de negocios para la tabla
 * de diagn&oacute;sticos<p>
 * <b>Creado     : </b> 28/04/2006 <p>
 * <b>Por        : </b> mrojas <p>
 * <b>Modificado : </b> $Date: 2006/09/04 23:59:36 $ <p>
 * <b>Por        : </b> $Author: taniap $ <p>
 *
 * @author mrojas
 * @version $Revision: 1.7 $
 */
public class MDiagnostico extends X_EXME_Diagnostico implements GridItem {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      s_log = CLogger.getCLogger (MDiagnostico.class);
    
    public MDiagnostico(Properties ctx, int EXME_Diagnostico_ID, String trxName) {
        super(ctx, EXME_Diagnostico_ID, trxName);
    }

    public MDiagnostico(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Devolvemos una lista de diagn&oacute;sticos
     * ordenados por nombre.
     * @param ctx
     * @param trxName
     * @return
     * @throws SQLException
     */
    public static ArrayList<LabelValueBean> getLstDiagnosticos(Properties ctx, String trxName, String likeValue) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //int EXME_DiagnosticoHdr_ID=MEXMEMejoras.getDiagnosticoHdr(ctx);
        ArrayList<LabelValueBean> lstDiag = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        try {

            sql.append("SELECT * FROM EXME_Diagnostico ")  
            	.append(" WHERE EXME_Diagnostico.IsActive = 'Y' "); 

			// if(EXME_DiagnosticoHdr_ID>0)
			// sql.append(" AND EXME_Diagnostico.Exme_DiagnosticoHdr_ID = " +
			// EXME_DiagnosticoHdr_ID + " ");
            
            if (likeValue != null) {
            	sql.append(" AND UPPER(EXME_Diagnostico.Name) LIKE ? ");
            }
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), Table_Name));
            
            sql.append (" ORDER BY EXME_Diagnostico.Name ");
            
            pstmt = DB.prepareStatement(sql.toString(), trxName);
            
            if (likeValue != null) {
            	pstmt.setString(1, likeValue);
            }
            rs = pstmt.executeQuery();
            
            lstDiag = new ArrayList<LabelValueBean>();
            lstDiag.add(new LabelValueBean(" ", "0"));

            while (rs.next()) {
                LabelValueBean lvb = 
                    new LabelValueBean(
                            rs.getString("Name"),
                            rs.getString("EXME_Diagnostico_ID")
                    );
                
                lstDiag.add(lvb);
            }

     	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs,pstmt);
    	}
        return lstDiag;
    }

	/**
     *  Obtenemos el nombre de un diagnostico en especifico
     *  @param El identificador del diagnostico
     *  @return El nombre del diagnostico
     */
	public static String getName(Properties ctx, int diagnostico, String trxName)  {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_Diagnostico.Name FROM EXME_Diagnostico ");
		sql.append("WHERE EXME_Diagnostico.EXME_Diagnostico_ID=? ");
		sql.append("AND EXME_Diagnostico.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Diagnostico.Name ");
		return DB.getSQLValueString(trxName, sql.toString(), diagnostico);
	}
	
	/**
     *  Obtenemos el Value de un diagnostico en especifico
     *  @param El identificador del diagnostico
     *  @return El value del diagnostico
     */
	public static String getValue (Properties ctx, int diagnostico, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_Diagnostico.Value FROM EXME_Diagnostico ");
		sql.append("WHERE EXME_Diagnostico.EXME_Diagnostico_ID=? ");
		sql.append("AND EXME_Diagnostico.IsActive='Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" ORDER BY EXME_Diagnostico.Name ");
		return DB.getSQLValueString(trxName, sql.toString(), diagnostico);
	}	
    
    /** @deprecated solo usada para struts */
    public static MDiagnostico getDiagnostico(Properties ctx, String diagValue, String trxName)
    	throws Exception{
        //el nombre de la sustancia activa
    	MDiagnostico diag = null;
    	StringBuilder sql = new StringBuilder (Constantes.INIT_CAPACITY_ARRAY);
    	sql.append ("SELECT EXME_Diagnostico.* FROM EXME_Diagnostico ")
    	.append("WHERE UPPER(EXME_Diagnostico.Value) LIKE UPPER('%") .append(diagValue)
    	.append("%') AND EXME_Diagnostico.IsActive = 'Y'");
    	
    	

		// int EXME_DiagnosticoHdr_ID = MEXMEMejoras.getDiagnosticoHdr(ctx);
		//    	
		// if (EXME_DiagnosticoHdr_ID> 0)
		// sql.append (" AND EXME_Diagnostico.Exme_DiagnosticoHdr_ID = ? ");
    	
    	PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), null);
			// if (EXME_DiagnosticoHdr_ID> 0)
			// pstmt.setInt(1,EXME_DiagnosticoHdr_ID);
            rs = pstmt.executeQuery();

            if(rs.next())
                diag = new MDiagnostico(ctx, rs, trxName);
            
          
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getDiagID " + sql.toString(), e);
            throw new Exception(e);
		} finally {
            DB.close(rs,pstmt);//Lama
            rs = null;
            pstmt = null;
            
        }
		 if(diag == null)//Mantis #0003741 Enviar mensaje .- Lama
         	throw new Exception(Utilerias.getMessage(Env.getCtx(), null,"error.citas.noExisteDiag"));

        return diag;
    }
    
    /**
	 * Obtiene los documentos de la tabla de diagnosticos
	 * @param ctx Contexto
	 * @param w IndexWriter al que perteneceran los documentos
	 */
    public static void getDocuments(Properties ctx, IndexWriter w) {
		StringBuilder st = new StringBuilder("select m.EXME_Diagnostico_ID, m.value, m.name from EXME_Diagnostico m");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Document doc = new Document();
				doc.add(new Field("label", Utilerias.getMessage(ctx, null, "msj.diagnostico"), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("tabla", MDiagnostico.Table_Name, Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("id", rs.getString("EXME_Diagnostico_ID"), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("value", rs.getString("value").toUpperCase(), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("name", rs.getString("name").toUpperCase(), Field.Store.YES, Field.Index.ANALYZED));
				w.addDocument(doc);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
	}
    
    private Timestamp fechaReg = null;

	public Timestamp getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(Timestamp fechaReg) {
		this.fechaReg = fechaReg;
	}
	
	/**
	 * Obtiene los diagnosticos de la base excluyendo los ids enviados
	 * 
	 * @param ctx
	 *            Contexto
	 * @param value
	 *            valor a buscar o null para todos
	 * @param notIn
	 *            Ids a excluir
	 * @param maxNum
	 *            Maximo de registros
	 * @param trxName
	 *            TrxName
	 * @return
	 */
	public static List<MDiagnostico> get(Properties ctx, String value, Integer[] notIn, int maxNum, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_Diagnostico WHERE isActive = 'Y' ");
		if (notIn != null && notIn.length > 0) {
			st.append("AND EXME_Diagnostico_ID not in (").append(StringUtils.join(notIn, ",")).append(") ");
		}
		if (!StringUtils.isEmpty(value)) {
			st.append("AND  (upper(value) LIKE upper('%").append(value.toUpperCase()).append("%' ) OR ")
			.append("  upper(name) LIKE upper('%").append(value.toUpperCase()).append("%' )) ");
		}
		
		if (maxNum != 0 && DB.isOracle()) {
			st.append(" AND rownum < ").append(maxNum).append(" ");
		}


		// int EXME_DiagnosticoHdr_ID = MEXMEMejoras.getDiagnosticoHdr(ctx);
		//
		// if (EXME_DiagnosticoHdr_ID > 0)
		// st.append("AND EXME_Diagnostico.Exme_DiagnosticoHdr_ID = ? ");

		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_Diagnostico"));
		st.append(" order by value, name");
		
		if (maxNum != 0 && DB.isPostgreSQL()) {
			st = new StringBuilder(DB.getDatabase().addPagingSQL(st.toString(), 1, maxNum-1));
		}
		
		List<MDiagnostico> lista = new ArrayList<MDiagnostico>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			// if (EXME_DiagnosticoHdr_ID > 0) {
			// pstmt.setInt(1, EXME_DiagnosticoHdr_ID);
			// }
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MDiagnostico(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "sql: get: " + st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
	
	private String diseaseTypeName = null;

	public String getDiseaseTypeName() {
		if (diseaseTypeName != null) {
			return diseaseTypeName;
		}
		diseaseTypeName = MRefList.getListName(getCtx(), DISEASETYPE_AD_Reference_ID, getDiseaseType().trim());
		
		return diseaseTypeName;
	}
	
	public static List<MDiagnostico> getDiagnostics(Properties ctx, String searchBy, String value, String trxName) {
		StringBuilder st = new StringBuilder("SELECT * FROM EXME_Diagnostico WHERE isActive = 'Y' ");
		if (searchBy != null) {
			st.append("AND ").append(searchBy)
			.append(" LIKE upper('%").append(value).append("%')");
		}

		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), Table_Name));
		st.append(" ORDER BY value, name");
		List<MDiagnostico> lista = new ArrayList<MDiagnostico>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), trxName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				lista.add(new MDiagnostico(ctx, rs, trxName));
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return lista;
	}
	
	
	/** Columnas de busqueda */
	public static String[] diagnostico = new String[] { X_EXME_Diagnostico.COLUMNNAME_Value,
			X_EXME_Diagnostico.COLUMNNAME_Name, X_EXME_Diagnostico.COLUMNNAME_Description, 
			X_EXME_Diagnostico.COLUMNNAME_CodOms};

	@Override
	public String[] getColumns() {
		String[] string = new String[]{"value", "name", "description", "codOms", "diseaseTypeName"};

		return string;
	}
	
	private IDColumn idColumn = null; 

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getEXME_Diagnostico_ID());
		}
		return idColumn;
	}
	
	private String comment = null;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	private boolean isCC = false;

	public boolean isCC() {
		return isCC;
	}

	public void setCC(boolean isCC) {
		this.isCC = isCC;
	}
	
	private String estatus = X_EXME_ActPacienteDiag.ESTATUS_Active;

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	private Date fechaEstatus =  null;

	public Date getFechaEstatus() {
		if(fechaEstatus == null)
			return DB.getDateForOrg(Env.getCtx());
		return fechaEstatus;
	}

	public void setFechaEstatus(Date fechaEstatus) {
		this.fechaEstatus = fechaEstatus;
	}
	
	private Date fechaDiagnosed =  null;

	public Date getFechaDiagnosed() {
		if(fechaDiagnosed == null)
			return DB.getDateForOrg(Env.getCtx());
		return fechaDiagnosed;
	}

	public void setFechaDiagnosed(Date fechaDiagnosed) {
		this.fechaDiagnosed = fechaDiagnosed;
	}

	private int EXME_ActPacienteDiag_ID = 0;

	public int getEXME_ActPacienteDiag_ID() {
		return EXME_ActPacienteDiag_ID;
	}

	/**
	 * Nota. Este campo no esta guarado en base de datos.
	 * @see AppointmentModel#getSavedDiagnostics(Properties, int, String)
	 * @param eXME_ActPacienteDiag_ID
	 */
	public void setEXME_ActPacienteDiag_ID(int eXME_ActPacienteDiag_ID) {
		EXME_ActPacienteDiag_ID = eXME_ActPacienteDiag_ID;
	}
	
	/**
	 * Obtiene el id del diagnostico/procedure de nuestro sistema
	 * 
	 * @param ctx
	 *            Ctx
	 * @param table
	 *            Nombre de tabla diagnostico/procedure
	 * @param value
	 *            Value del registro
	 * @param time
	 *            Fecha base
	 * @param trxName
	 *            Trx
	 * @return Id o -1 en caso de no tenerlo
	 */
	public static int get(Properties ctx, String table, String value, Timestamp time, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(table).append("_ID FROM ");
		sql.append(table);
		sql.append(" WHERE ISACTIVE = 'Y' AND ");
		sql.append("  value = ? ");

		boolean add = false;

		if (MDiagnostico.Table_Name.equals(table) || X_EXME_ICD9V3.Table_Name.equals(table)) {
			add = true;
			sql.append(" AND ? BETWEEN ");
			sql.append("  valid_from AND ");
			sql.append("  valid_to ");
		}

		int id = -1;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, value);

			if (add) {
				pstmt.setTimestamp(2, time);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return id;
	}
	
	private int exme_orderset_id = 0;

	public int getExme_orderset_id() {
		return exme_orderset_id;
	}

	public void setExme_orderset_id(int exme_orderset_id) {
		this.exme_orderset_id = exme_orderset_id;
	}
	
	
}
