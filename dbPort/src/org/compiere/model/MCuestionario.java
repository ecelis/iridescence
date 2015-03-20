/*

 * Created on 12-may-2005

 *

 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.PreguntaDatavision;
import org.compiere.util.cuestionarios.Cuestionario_VO;


/**
 * Modelo de Cuestionarios
 * <b>Modificado: </b> $Author: taniap $<p>
 * <b>En :</b> $Date: 2006/09/28 20:09:41 $<p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.6 $
 */
/**
 * @author rsolorzano
 *
 */
public class MCuestionario extends X_EXME_Cuestionario {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MCuestionario.class);

    /**
     * @param ctx
     * @param EXME_Cuestionario_ID
     * @param trxName
     */
    public MCuestionario(Properties ctx, int EXME_Cuestionario_ID,
            String trxName) {
        super(ctx, EXME_Cuestionario_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MCuestionario(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
	 * Obtenemos los pacientes a partir de ciertos criterios.
	 * @param ctx
	 * @param where
	 * @param all Indica si se retornan tambien inactivos o solo activos.
	 * @return
	 */
	public static MCuestionario[] get(Properties ctx, String whereClause, String orderBy
	                            , String trxName, boolean all) {

		ArrayList<MCuestionario> list = new ArrayList<MCuestionario>();
		String sql = "SELECT * FROM EXME_Cuestionario ";

		if (whereClause != null)
			sql +=  whereClause;

		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Cuestionario");
		
		if(orderBy != null && orderBy.length() > 0)
			sql += " ORDER BY " + orderBy;

        ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql, trxName);

            rs = pstmt.executeQuery();
			while (rs.next()) {
			    MCuestionario cuest = new MCuestionario(ctx, rs, trxName);
				list.add(cuest);
			}
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
		//
		MCuestionario[] cuests = new MCuestionario[list.size()];
		list.toArray(cuests);
		return cuests;
	}

	/**
     * Obtenemos el detalle de un cuestionario determinado. Esta versi&oacute;n
     * devuelve todos el detalle ordenado por secuencia para un solo cuestionario,
     * considerando &uacute;nicamente aquellas preguntas que est&eacute;n activas.
     * 
     * @param ctx El contexto de la aplicaci&oacute;n
     * @param exmeCuestionarioId El cuestionario a recuperar
     * @param trxName El nombre de transacci&oacute;n
     * @return Un arreglo con el detalle del cuestionario.
     */
    public static MEXMECuestionarioDt[] getDetalle(Properties ctx, int exmeCuestionarioId, String trxName) {

        ArrayList<MEXMECuestionarioDt> list = new ArrayList<MEXMECuestionarioDt>();
        String sql = "SELECT * FROM EXME_CuestionarioDt WHERE EXME_Cuestionario_ID = ? AND IsActive = 'Y' ";
        sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_CuestionarioDt");
        sql+=" ORDER BY Secuencia";

        ResultSet rs = null;
    	PreparedStatement pstmt = null;

    	try {

    		pstmt = DB.prepareStatement(sql, trxName);
    		pstmt.setInt(1, exmeCuestionarioId);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    		    MEXMECuestionarioDt detalle = new MEXMECuestionarioDt(ctx, rs, trxName);
    			list.add(detalle);
    		}

    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
    	//
    	MEXMECuestionarioDt[] detalle = new MEXMECuestionarioDt[list.size()];
    	list.toArray(detalle);
    	return detalle;
    }

    /**
     * Borramos el detalle de un cuestionario.
     * @param ctx
     * @param exmeCuestionarioId
     * @param trxName
     */
    public static void borrarDetalle(Properties ctx, int exmeCuestionarioId, String trxName) throws Exception {

        /* ********* Se cambio por modelos de Compiere .- Expert:Lama
        String sql = "DELETE FROM EXME_CuestionarioDt " +
                       "WHERE EXME_Cuestionario_ID = ? AND AD_Client_ID = ? ";
        PreparedStatement pstmt = null;
        pstmt = DB.prepareStatement(sql, trxName);
        pstmt.setInt(1, exmeCuestionarioId);
        pstmt.setInt(2, Env.getAD_Client_ID(ctx));
        int i = pstmt.executeUpdate();
        ********************************************************/
            
        try {
            //Ini .- buscamos las lineas del detalle del cuestionario .- Expert:Lama
            MEXMECuestionarioDt[] cdt = MCuestionario.getDetalle(ctx, exmeCuestionarioId,trxName);
            
            //borramos cada una de las l�neas
            for (int j = 0; j < cdt.length; j++) {
                MEXMECuestionarioDt detalle = (MEXMECuestionarioDt)cdt[j];
                if(!detalle.delete(false,trxName))
                    throw new Exception();
            }
            
            //fin .- Expert:Lama
            
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, null, e);
    		 throw new Exception(e);
    	}
        
    }

	
    /**
     * Obtenemos una lista con los cuestionarios 
     * se utiliza para llenar combo-box de cuestionarios
     * @param Se incluye un registro en blanco en el combo
     * @return Una lista con los cuestionarios
     */
 /*   public static List getLstaCuest(boolean blanco) throws Exception{
         //lista con los cuestionarios
         List lstCuest = new ArrayList();

         if(blanco)
             lstCuest.add(new LabelValueBean("", "0"));

         //buscamos los cuestionarios
         String sql =  " SELECT EXME_cuestionario_id, name FROM EXME_cuestionario  " +
         " WHERE IsActive = 'Y' ";
         sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_cuestionario");
         
         sql+=" ORDER BY EXME_cuestionario_id";
         
         PreparedStatement pstmt = null;
         ResultSet rs = null; 

         try {
             pstmt = DB.prepareStatement(sql, null);
             rs = pstmt.executeQuery();
             
             while(rs.next()) {
                 LabelValueBean cuest = 
                     new LabelValueBean(rs.getString("Name"),
                             String.valueOf(rs.getLong("EXME_Cuestionario_ID"))
                     );
                 lstCuest.add(cuest);
             }
     	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
         return lstCuest;
     }
*/
     

    /**
     *  Obtenemos una lista con los cuestionarios por especialidad
     *  Se utiliza para llenar combo box de cuestionario
     *  @param La especialidad del cuestionario
     *  @param Si incluye un registro en blanco en el combo
     *  @return Una lista con los cuestionarios
     */
    public static List<LabelValueBean> getCuestionarios (long especialidad, boolean blanco, String whereClause) throws Exception{

		// lista con los cuestionarios
		final List<LabelValueBean> lstCuestionarios = new ArrayList<LabelValueBean>();

		if (blanco) {
			lstCuestionarios.add(new LabelValueBean("", "0"));
		}

		// buscamos los cuestionarios
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT EXME_Cuestionario.EXME_Cuestionario_ID, EXME_Cuestionario.Name ");
		sql.append(" FROM EXME_Cuestionario ");
		sql.append(" LEFT JOIN EXME_CuestEsp ON (EXME_Cuestionario.EXME_Cuestionario_ID = EXME_CuestEsp.EXME_Cuestionario_ID ");
		sql.append(" AND EXME_CuestEsp.IsActive = 'Y' ");
		if (especialidad > 0) {
			sql.append(" AND EXME_CuestEsp.EXME_Especialidad_ID=? ");
		}
		sql.append(" )   ");
		sql.append(" WHERE EXME_Cuestionario.IsActive = 'Y' ");

		if (whereClause != null) {
			sql.append(whereClause);
		}
		sql.append(" ORDER BY EXME_Cuestionario.Name ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			if (especialidad > 0) {
				pstmt.setLong(1, especialidad);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				final LabelValueBean cuest = new LabelValueBean(rs.getString("Name"), //
					String.valueOf(rs.getLong("EXME_Cuestionario_ID")));
				lstCuestionarios.add(cuest);
			}
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return lstCuestionarios;

	}



    /**
     *  Obtenemos una lista con las preguntas de un cuestionario
     *  @return La lista con las preguntas
     */
    public static List<LabelValueBean> getPreguntas (long cuestionario) throws Exception{

        //lista con las preguntas
        List<LabelValueBean> lstPreguntas = new ArrayList<LabelValueBean>();

        //buscamos las preguntas
        
        StringBuilder sql = new StringBuilder ();
        
         sql.append("SELECT EXME_Pregunta.EXME_Pregunta_ID, EXME_Pregunta.Name ")
            .append("FROM EXME_Pregunta, EXME_CuestionarioDt ")
            .append("WHERE EXME_Pregunta.EXME_Pregunta_ID = EXME_CuestionarioDt.EXME_Pregunta_ID ")
            .append("AND EXME_CuestionarioDt.EXME_Cuestionario_ID = ? ")
            .append("AND EXME_Pregunta.IsActive = 'Y' ")
            .append("AND EXME_CuestionarioDt.IsActive = 'Y' ")
            .append("ORDER BY EXME_CuestionarioDt.Secuencia");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setLong(1, cuestionario);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                LabelValueBean preg = 
                    new LabelValueBean(rs.getString("Name"),
                            String.valueOf(rs.getLong("EXME_Pregunta_ID"))
                );

                lstPreguntas.add(preg);
            }
    	} catch (Exception e) {    		
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return lstPreguntas;
    }

    /**
     *  Obtenemos una resultset con el detalle del cuestionario por cuestionario
     *  y tipo de pregunta
     *  @return El resultset con los tipos de pregunta
     */
 public static List<CuestionarioView> getDetalleCuest ( long cuestionario) throws Exception{
        
    	List<CuestionarioView> lista = new ArrayList<CuestionarioView>();
    	//List<MEXMEReglaCuestionario> listaRegla = new ArrayList<MEXMEReglaCuestionario>();
    	//buscamos el detalle del cuestionario
    	
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT cu.EXME_Pregunta_ID, cu.EXME_Cuestionario_ID, cu.Obligatoria, cu.Secuencia, ") 
           .append(" pr.Name, pr.TipoDato, pr.EXME_TipoPregunta_ID, tp.Name as TipoPreg, cs.Name as CuesName ") //Regresara tambien el nombre del cuestionario .. Twry
           .append(" FROM EXME_CuestionarioDt cu LEFT JOIN EXME_Pregunta pr ON (pr.EXME_Pregunta_ID = cu.EXME_Pregunta_ID ) ") 
           .append(" LEFT JOIN EXME_TipoPregunta tp ON ( pr.EXME_TipoPregunta_ID = tp.EXME_TipoPregunta_ID ) ")
           .append(" LEFT JOIN EXME_Cuestionario cs ON ( cs.EXME_Cuestionario_ID = cu.EXME_Cuestionario_ID ) ")
           .append(" WHERE cu.EXME_Cuestionario_ID = ? AND cu.IsActive = 'Y' AND pr.IsActive = 'Y' ")
           .append(" ORDER BY cu.Secuencia ");

      
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setLong(1, cuestionario);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	CuestionarioView detCuest = new CuestionarioView();
                detCuest.setCuestionario(rs.getLong("EXME_Cuestionario_ID"));
                detCuest.setNombreCuest(rs.getString("CuesName")); //Regresa tambien el nombre del cuestionario .. Twry
                detCuest.setPregunta(rs.getInt("EXME_Pregunta_ID"));
                detCuest.setNombrePreg(rs.getString("Name"));
                detCuest.setTipo(rs.getString("TipoDato"));
                detCuest.setObligatoria(rs.getString("Obligatoria"));
                detCuest.setSecuencia(rs.getInt("Secuencia"));
                detCuest.setTipoPreg(rs.getLong("EXME_TipoPregunta_ID"));
                detCuest.setNombreTipoPreg(rs.getString("TipoPreg"));
                lista.add(detCuest);
            }
            
            
        } catch (Exception e) {
            s_log.log(Level.SEVERE, "getDetalleCuest", e);
        } finally {
        	DB.close(rs, pstmt);
		}
        return lista;
    }

    /**
     *  Obtenemos una resultset con el detalle del cuestionario por tipo de pregunta
     *  y preguntas agregadas
     *  @return El resultset con los tipos de pregunta
     */
    public static CuestionarioView getDetalleCuest (int pregunta, long cuestionario) throws Exception{ // se agreg� parametro de cuestionario rsolorzano
        List<String> lista = new ArrayList<String>();
        lista.add(String.valueOf(pregunta));

        return getDetalleCuest(lista,cuestionario);
    }

    /**
     *  Obtenemos una resultset con el detalle del cuestionario por tipo de pregunta
     *  y preguntas agregadas
     *  @return El resultset con los tipos de pregunta
     */
    public static CuestionarioView getDetalleCuest (List<String> preguntas, long cuestionario) throws Exception{ 
        //buscamos el detalle del cuestionario
    	String sql = " SELECT pr.EXME_Pregunta_ID, pr.Name, pr.TipoDato, pr.EXME_Tipopregunta_id, tp.Name AS tipoPreg" +
        ", COALESCE(cu.obligatoria, 'N') AS Obligatoria, cu.secuencia, cu.EXME_Cuestionario_ID, cs.Name As CuesName " +       
        " FROM EXME_Pregunta pr LEFT JOIN EXME_TipoPregunta tp " + 
        " ON (pr.EXME_TipoPregunta_ID = tp.EXME_TipoPregunta_ID) " +
        " LEFT JOIN EXME_CuestionarioDt cu ON (pr.EXME_Pregunta_ID = cu.EXME_Pregunta_ID) " +
        " LEFT JOIN EXME_Cuestionario cs ON (cs.EXME_Cuestionario_ID = cu.EXME_Cuestionario_ID) " +
        " WHERE pr.IsActive = 'Y' AND pr.EXME_Pregunta_ID IN ( ";

        //condicionamos por preguntas
        Iterator<String> i = preguntas.iterator();
        while(i.hasNext())
            sql += i.next() + ", ";
        if(!preguntas.isEmpty())
            sql = sql.substring(0, sql.length() - 2);
        else
            sql += "0";
            
        if(cuestionario!=0){
        	sql += ") AND cs.EXME_Cuestionario_ID = " + cuestionario;
        } else {
        	sql += ")  ";
    	}
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CuestionarioView detCuest = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            if(rs.next()) {
	            detCuest = new CuestionarioView();
	            detCuest.setTipoPreg(rs.getLong("EXME_TipoPregunta_ID"));
	            detCuest.setPregunta(rs.getInt("EXME_Pregunta_ID"));
	            detCuest.setNombreTipoPreg(rs.getString("tipoPreg"));
	            detCuest.setNombrePreg(rs.getString("Name"));
	            detCuest.setTipo(rs.getString("TipoDato"));
	            detCuest.setObligatoria(rs.getString("Obligatoria"));
	            detCuest.setSecuencia(rs.getInt("Secuencia"));
	            detCuest.setCuestionario(rs.getLong("EXME_Cuestionario_ID"));
                detCuest.setNombreCuest(rs.getString("CuesName")); //Regresa tambien el nombre del cuestionario .. Twry
                
            }
            
        } catch (Exception e)
        {
            s_log.log(Level.SEVERE, "getDetalleCuest", e);
        } finally {
        	DB.close(rs, pstmt);
        }
        return detCuest;
    }

    /**
     *  Noelia Obtenemos una resultset con el detalle del cuestionario de determinado folio
     *  por cuestionario y tipo de pregunta
     *  @return El resultset con los tipos de pregunta
     */
    public static List<NotasMedicasView> getDetalleNotaMed(Properties ctx, int cuestionario, int actPAciente, int folio, 
    		String trxName) throws Exception{

    	List<NotasMedicasView> lista = new ArrayList<NotasMedicasView>();
    	
        //buscamos el detalle del cuestionario
        StringBuilder sql = new StringBuilder(" SELECT cu.EXME_Pregunta_ID, cu.EXME_Cuestionario_ID, cu.Obligatoria, cu.Secuencia, ") 
				        .append(" pr.Name, pr.TipoDato, pr.EXME_TipoPregunta_ID, tp.Name as TipoPreg , apd.respuesta, " )
				        .append(" apd.confidencial, apd.rutaimagen, apd.exme_medico_id, med.nombre_med, apd.exme_estserv_id, " )
				        .append(" est.Name AS Estacion, apd.Description ")
				        .append(" FROM EXME_ActPacienteDet apd " )
				        .append(" INNER JOIN EXME_CuestionarioDt cu ON (apd.Exme_cuestionario_id = cu.EXME_cuestionario_ID " )
				        .append(" AND apd.EXME_Pregunta_ID = cu.EXME_Pregunta_ID ) " )
				        .append(" INNER JOIN EXME_Pregunta pr ON (pr.EXME_Pregunta_ID = cu.EXME_Pregunta_ID ) " ) 
				        .append(" INNER JOIN EXME_TipoPregunta tp ON (pr.EXME_TipoPregunta_ID = tp.EXME_TipoPregunta_ID) " )   
				        .append(" INNER JOIN EXME_Medico med ON (med.EXME_Medico_id = apd.EXME_Medico_id) " )
				        .append(" INNER JOIN EXME_EstServ est ON (est.EXME_Estserv_id = apd.EXME_Estserv_id) " )
				        .append(" WHERE apd.IsActive = 'Y' " )
				        .append(" AND apd.EXME_Cuestionario_ID = ? " )
				         .append(" AND  pr.IsActive = 'Y' " )
				        .append(" AND apd.EsNotaMedica = 'Y' " )
				        .append(" AND apd.EXME_Actpaciente_id = ? " )
				        .append(" AND apd.folio = ?")
				        .append(" ORDER BY cu.Secuencia ");

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {

            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, cuestionario);
            pstmt.setInt(2, actPAciente);
            pstmt.setInt(3, folio);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                NotasMedicasView detCuest = new NotasMedicasView();
                detCuest.setCuestionario(rs.getLong("EXME_Cuestionario_ID"));
                detCuest.setPregunta(rs.getInt("EXME_Pregunta_ID"));
                detCuest.setObligatoria(rs.getString("Obligatoria"));
                detCuest.setSecuencia(rs.getInt("Secuencia"));
                detCuest.setNombrePreg(rs.getString("Name"));
                detCuest.setTipo(rs.getString("TipoDato"));
                detCuest.setTipoPreg(rs.getLong("EXME_TipoPregunta_ID"));
                detCuest.setNombreTipoPreg(rs.getString("TipoPreg"));
                detCuest.setRespuesta(rs.getString("Respuesta"));
                detCuest.setConfidencial(rs.getString("confidencial"));
                detCuest.setRutaImagen(rs.getString("rutaimagen"));
                detCuest.setExme_medico_id(rs.getInt("exme_medico_id"));
                detCuest.setNombre_med(rs.getString("nombre_med"));
                detCuest.setExme_estserv_id(rs.getInt("exme_estserv_id"));
                detCuest.setEstacion(rs.getString("Estacion"));
                detCuest.setDescripcion(rs.getString("Description"));
                lista.add(detCuest);
                
            }
            
            String anterior = "";
            for (int i=0; i<lista.size(); i++) {
            	NotasMedicasView detCuest = (NotasMedicasView) lista.get(i);
                if (!detCuest.getNombreTipoPreg().equalsIgnoreCase(anterior)){
                	NotasMedicasView cuest = new NotasMedicasView();
                    cuest.setNombreTipoPreg(detCuest.getNombreTipoPreg());
                    cuest.setNombrePreg(detCuest.getNombrePreg());
                    cuest.setTipo("titulo");
                    lista.add(i,cuest);
                    //System.out.println("anterior >>" + cuest.getTipoPreg());
                    anterior = cuest.getNombreTipoPreg();
                }
            }
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return lista;

    }
    
    /**
     * Indica si es posible borrar un archivo
     * (no debe tener actividad paciente)
     * @param ctx
     * @param cuestionario_id
     * @param trxName
     * @return
     */
    public static boolean deleteCuest(Properties ctx, int cuestionario_id, String trxName ){
        boolean retValue = false;
        
        String sql = null;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            sql = "SELECT * FROM EXME_ActPacienteDet WHERE EXME_Cuestionario_ID = ? ";
            
            sql = MEXMELookupInfo.addAccessLevelSQL(ctx,sql,"EXME_ActPacienteDet");
            
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, cuestionario_id);                       

            rs = pstmt.executeQuery();
            
            if(!rs.next())
                retValue = true;
            
        }catch (Exception e) {
            s_log.log(Level.SEVERE, sql.toString(), e);
        }finally {
        	DB.close(rs, pstmt);
        }

        return retValue;        
    }
    /**
     *  Noelia: Obtenemos una resultset con el cuestionario de la notaMedica
     *  ordenados por el folio descendentemente.
     *  @return El resultset con los tipos de pregunta
     */
    public static List<NotasMedicasView> getCuestNotaMed(Properties ctx, int cuestionario, int actPAciente,
    		String trxName) throws Exception{

    	List<NotasMedicasView> lista = new ArrayList<NotasMedicasView>();
    	
        //buscamos el detalle del cuestionario para obtener el folio
        StringBuilder sql = new StringBuilder(" SELECT * FROM ( SELECT DISTINCT apd.folio, TO_CHAR(apd.fecha,'DD/MM/YYYY HH24:Mi') as fecha,to_date(to_char(apd.fecha,'DD/MM/YYYY'),'DD/MM/YYYY') as fechaorden, med.nombre_med, est.Name, usr.name as usuario " )
        				.append(" FROM EXME_ActPacienteDet apd " )
				        .append(" LEFT JOIN EXME_Medico med ON (med.EXME_Medico_id = apd.EXME_Medico_id) " )
				        .append(" INNER JOIN EXME_EstServ est ON (est.EXME_Estserv_id = apd.EXME_Estserv_id) " )
				        .append(" INNER JOIN AD_User usr on (apd.CreatedBy = usr.ad_user_id)")
				        .append(" WHERE apd.IsActive = 'Y' " )
				        .append(" AND apd.EsNotaMedica = 'Y' " )
				        .append(" AND apd.EXME_Actpaciente_id = ? " )				        			         
				        .append(" GROUP BY apd.folio , med.nombre_med, est.name, usr.name, fecha) ORDER by fecha desc");
        
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {

            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, actPAciente);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                NotasMedicasView cuest = new NotasMedicasView();               
                cuest.setNombre_med(rs.getString("nombre_med"));              
                cuest.setEstacion(rs.getString("Name"));
                cuest.setFechaHr(rs.getString("fecha"));
                cuest.setFolio(rs.getInt("folio"));               
                //Noelia:Asignamos el ID del CUestionario configurado como NotaMedica
                cuest.setCuestID(cuestionario);
                cuest.setUsuario(rs.getString("usuario"));//**GADC**
                lista.add(cuest);                
            }
          
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e);
    	}finally {
    		DB.close(rs, pstmt);
    	}
        return lista;

    }
    
    /**
     * Liz de la Garza
     * Metodo que obtiene si el cuestionario esta configurado a que muestre solo las preguntas
     * respondidas o que muestre todas las preguntas
     * @param ctx
     * @param actpaciente_id
     * @param trxName
     * @return
     */
    public static boolean getOnlyAnswer(Properties ctx, int actpaciente_id, String trxName){
        //String onlyAnswer = null;
        boolean retValue = false; 
        StringBuilder sql = null;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            sql = new StringBuilder("SELECT TO_CHAR(cuest.OnlyAnswer) AS OnlyAnswer FROM  EXME_ActPaciente actPac ")
            .append(" LEFT JOIN EXME_Cuestionario cuest ON(cuest.EXME_Cuestionario_ID  ")
            .append(" IN (SELECT actPacDet.EXME_Cuestionario_ID FROM EXME_ActPacienteDet actPacDet ")
            .append(" WHERE actPacDet.EXME_ActPaciente_ID=actPac.EXME_ActPaciente_ID))")
            .append(" WHERE actPac.EXME_ActPaciente_ID= ?");
              
            pstmt = DB.prepareStatement(sql.toString(), null);
            pstmt.setInt(1, actpaciente_id);                       

            rs = pstmt.executeQuery();
            
            if(rs.next()){
                if(rs.getString("OnlyAnswer")!= null && rs.getString("OnlyAnswer").equals("Y")){
                	retValue=true;
                }else{
                	retValue = false;
                }
            }
            
        }catch (Exception e) {
            s_log.log(Level.SEVERE, sql.toString(), e);
        }finally {
        	DB.close(rs, pstmt);
        }

        return retValue;        
    }
    
	public static List<PreguntaDatavision> getRespuestas(Properties ctx,
			int EXME_Cuestionario_ID, String indice, String columnName, String folio) {
		List<PreguntaDatavision> respuestas = new ArrayList<PreguntaDatavision>();

		StringBuilder sql = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			sql = new StringBuilder(
					"select actd.Secuencia, Act.created as fecha, NVL(act.EXME_CtaPac_ID, 0) as ctaPac, NVL(act.EXME_CitaMedica_ID, 0) as cita, NVL(act.EXME_Paciente_ID, 0) as pac,  ")
					.append(
							"prg.name as pregunta, NVL(actd.Respuesta,' ') as respuesta, prg.TipoDato ")
					.append("from EXME_Cuestionario Cuest ")
					.append(
							"INNER JOIN EXME_CuestionarioDt Det ON Cuest.EXME_Cuestionario_ID = Det.EXME_Cuestionario_ID ")
					.append(
							"INNER JOIN EXME_Pregunta Prg ON Det.EXME_Pregunta_ID = Prg.EXME_Pregunta_ID ")
					.append(
							"INNER JOIN EXME_ActPacienteDet ActD ON (ActD.EXME_Cuestionario_ID = Cuest.EXME_Cuestionario_ID AND ActD.EXME_Pregunta_ID = Prg.EXME_Pregunta_ID) ")
					.append(
							"INNER JOIN EXME_ActPaciente Act ON ActD.EXME_ActPaciente_ID = Act.EXME_ActPaciente_ID ");
			
			if (columnName.toLowerCase().equals("cuenta")) {
				sql.append("INNER JOIN EXME_CtaPac ctaPac ON ctaPac.EXME_CtaPac_ID = act.EXME_CtaPac_ID ");
			} else if (columnName.toLowerCase().equals("paciente")) {
				sql.append("INNER JOIN EXME_Paciente paciente ON paciente.EXME_Paciente_ID = act.EXME_Paciente_ID ");
			} else {
				return null;
			}
			
			sql.append("where Cuest.EXME_Cuestionario_ID = ? ");

			if (columnName.toLowerCase().equals("cita")) {
				sql.append("and act.EXME_CitaMedica_ID = ? ");
			} else if (columnName.toLowerCase().equals("cuenta")) {
				sql.append("and cuenta.documentNo = ? ");
			} else if (columnName.toLowerCase().equals("paciente")) {
				sql.append("and paciente.value = ? ");
			} else {
				return null;
			}
			
			sql.append("and actd.folio = ? ");

			sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql
					.toString(), "EXME_Cuestionario", "Cuest"));

			sql.append(" order by act.EXME_CitaMedica_ID, actd.Secuencia ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Cuestionario_ID);
			pstmt.setString(2, indice);
			pstmt.setString(3, folio);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PreguntaDatavision pregunta = new PreguntaDatavision();
				pregunta.setRespuesta(rs.getString("respuesta"));
				pregunta.setPregunta(rs.getString("pregunta"));
				pregunta.setCitaID(rs.getInt("cita"));
				pregunta.setCtaPacID(rs.getInt("ctaPac"));
				pregunta.setPacID(rs.getInt("pac"));
				pregunta.setFechaCreacion(rs.getDate("fecha"));
				respuestas.add(pregunta);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return respuestas;
	}
	
	 public static ArrayList<Cuestionario_VO> getCuestionariosMC(int especialidad, String cadena) throws Exception{

	        //lista con los cuestionarios
	        ArrayList<Cuestionario_VO> lstCuestionarios = new ArrayList<Cuestionario_VO>();
	        
	        //buscamos los cuestionarios
	        String sql = " SELECT EXME_Cuestionario.EXME_Cuestionario_ID, EXME_Cuestionario.Name, EXME_CuestEsp.EXME_CuestEsp_ID "
	              + " FROM EXME_Cuestionario, EXME_CuestEsp "
	              + " WHERE EXME_Cuestionario.EXME_Cuestionario_ID = EXME_CuestEsp.EXME_Cuestionario_ID "
	              + " AND EXME_CuestEsp.EXME_Especialidad_ID = ? "
	              + " AND EXME_Cuestionario.IsActive = 'Y' "
	              + " AND EXME_CuestEsp.IsActive = 'Y' ";

	         if (cadena!=null)
	               sql = sql + cadena;

	        sql = sql + " ORDER BY EXME_Cuestionario.Value ";

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql, null);
	            pstmt.setLong(1, especialidad);
	            rs = pstmt.executeQuery();

	            Cuestionario_VO cuest = null;
	            while(rs.next()) {
	            	cuest = new Cuestionario_VO();
	                cuest.setCuestionarioName(rs.getString("Name"));
	                cuest.setCuestionarioId(rs.getInt("EXME_Cuestionario_ID"));
	                cuest.setCuestEspID(rs.getInt(MEXMECuestEsp.COLUMNNAME_EXME_CuestEsp_ID));
	                
	                lstCuestionarios.add(cuest);
	            }
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, sql.toString(), e);
	    	}finally {
	    		DB.close(rs, pstmt);
	    	}
	        return lstCuestionarios;

	    }
	 
	 public static ArrayList<Cuestionario_VO> getCuestionarioENF(Integer enfermedad) throws Exception{

	        //lista con los cuestionarios
	        ArrayList<Cuestionario_VO> lstCuestionarios = new ArrayList<Cuestionario_VO>();
	        
	        //buscamos los cuestionarios
	        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 	   
	        	sql.append("	select c.exme_cuestionario_id, c.name")
	        	   .append("	from exme_enfcontrolcuest e")
	        	   .append("	inner join exme_cuestionario c on(e.exme_cuestionario_id= c.exme_cuestionario_id)")
	        	   .append("	where e.exme_enfcontrolada_id = ?");
	       
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql.toString(), null);
	            pstmt.setInt(1, enfermedad.intValue());
	            rs = pstmt.executeQuery();

	            Cuestionario_VO cuest = null;
	            while(rs.next()) {
	            	cuest = new Cuestionario_VO();
	            	cuest.setCuestionarioId(rs.getInt(1));
	                cuest.setCuestionarioName(rs.getString(2));
	                
	                
	                lstCuestionarios.add(cuest);
	            }
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, sql.toString(), e);
	    	}finally {
	    		DB.close(rs, pstmt);
	    	}
	        return lstCuestionarios;

	    }

	 

	    /**
	     *  Obtenemos una lista con los cuestionarios por especialidad
	     *  Se utiliza para llenar combo box de cuestionario
	     *  @param La especialidad del cuestionario
	     *  @param Si incluye un registro en blanco en el combo
	     *  @return Una lista con los cuestionarios
	     */
	    public static List<LabelValueBean> getCuestionariosPorEnfermedadesControladas ( boolean blanco, String cadena) throws Exception{

	        //lista con los cuestionarios
	        List<LabelValueBean> lstCuestionarios = new ArrayList<LabelValueBean>();

	        if(blanco)
	            lstCuestionarios.add(new LabelValueBean("", "0"));

	        //buscamos los cuestionarios
	        String sql = " SELECT DISTINCT c.EXME_Cuestionario_ID, c.Name  "
	              + " FROM EXME_Cuestionario c  "
	              + "INNER JOIN  exme_enfcontrolcuest ec  on ec.exme_cuestionario_id=c.exme_cuestionario_id  "
	              + " WHERE  ec.IsActive = 'Y'  "
	              + "   AND c.IsActive = 'Y'   "    ;

	         if (cadena!=null)
	               sql = sql + cadena;

	        sql = sql + " ORDER BY c.Name ";

	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql, null);	       
	            rs = pstmt.executeQuery();

	            while(rs.next()) {
	                LabelValueBean cuest = 
	                    new LabelValueBean(rs.getString("Name"),
	                            String.valueOf(rs.getLong("EXME_Cuestionario_ID"))
	                );

	                lstCuestionarios.add(cuest);
	            }
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, sql.toString(), e);
	    	}finally {
	    		DB.close(rs, pstmt);
	    	}
	        return lstCuestionarios;

	    }
	    
	    /*  Obtenemos un cuestionario por value y tipo de area
	     *  
	     *  @param ctx Properties
	     *  @param value String
	     *  @param areaType String
	     *  @return MCuestionario
	     *  
	     *  @author rsolorzano
	     */  
	    public static MCuestionario getByAreaType(Properties ctx, String value, String areaType){

	    	MCuestionario cuest = null;
	        

	        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	 	   
	        	sql.append("	SELECT * FROM EXME_Cuestionario")
	        	   .append("	WHERE Value = ?")
	        	   .append("	AND TipoArea = ?");
	       
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            pstmt = DB.prepareStatement(sql.toString(), null);
	            pstmt.setString(1, value);
	            pstmt.setString(2, areaType);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	            	cuest = new MCuestionario(ctx, rs, null);
	            }
	    	} catch (Exception e) {
	    		s_log.log(Level.SEVERE, sql.toString(), e);
	    			    		
	    	}finally {
	    		DB.close(rs, pstmt);
	    	}
	    	
	        return cuest;

	    }


	 
	 
    
}