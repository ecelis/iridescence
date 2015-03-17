package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.mo.MO_DetallePerio_VO;
import org.compiere.util.mo.MO_PiezaDental_VO;


	public class MEXMEMO_PiezaDental extends X_EXME_MO_PiezaDental {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/** Static logger           */
	    private static CLogger s_log = CLogger.getCLogger(MEXMEMO_PiezaDental.class);

	    
	    /**
	     * @param ctx
	     * @param EXME_CitaMedica_ID
	     * @param trxName
	     */
	    public MEXMEMO_PiezaDental(Properties ctx, int EXME_MO_PiezaDentalID, String trxName) {
	        super(ctx, EXME_MO_PiezaDentalID, trxName);
	    }

	    /**
	     * @param ctx
	     * @param rs
	     * @param trxName
	     */
	    public MEXMEMO_PiezaDental(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }

	    /**
		 * Obtiene las piezas dentales configuradas en el catalogo. 
		 *
		 * @return ArrayList catalogoPiezasDentales
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static ArrayList<MO_PiezaDental_VO> getPiezasDentales() throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
				sql.append(" 	select  exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual")
				   .append(" 	from(")
				   .append(" 	select exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual")
				   .append(" 	from exme_mo_piezadental")
				   .append(" 	where isadulto = 'Y' and isactive='Y'")
				   .append(" 	and islingual = 'Y'")
				   .append(" 	order by exme_mo_piezadental_id")
				   .append(" 	)");
				
				if (DB.isPostgreSQL()) {
					sql.append(" AS ");
				} else {
					sql.append(StringUtils.EMPTY);
				}
				
				sql.append(" consulta ");
				
				if (DB.isOracle()) {
					sql.append(" 	where rownum < 17");
				}
				   
				sql.append(" 	order by exme_mo_piezadental_id");
				
				if (DB.isPostgreSQL()) {
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 16));
				}
	    		
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
					aux = new MO_PiezaDental_VO();
					
					aux.setDienteId(new Integer(rs.getInt(1)));
					aux.setDescripcion(rs.getString(3));
					aux.setNombre(rs.getString(4));
					aux.setLingual(rs.getString(5));
					
					piezas.add(aux);				    	
				}
							
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getPiezasDentales - sql: " + sql, e);
			} finally {
				DB.close(rs, pstmt);
			}
			
			return piezas;
	    }
	    
	    
	    
	    /**
		 * Obtiene las piezas dentales configuradas en el catalogo. 
		 *
		 * @return ArrayList catalogoPiezasDentales
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesLingualView(Integer pacId, String vestibular,String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta,isadulto")
				.append(" 	from exme_mo_piezadental")
				.append(" 	where isactive='Y' and islingual = 'Y'")
				.append(" 	and mapaadulto is null")
				.append(" 	and mapainfantil is null")
				.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
				.append(" 	order by secuencia");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesImg("Y"));
				
				if(MEXMEMO_DentalProblems.getTypeProblem(pacId, aux.getDienteId(),"Y",vestibular,tabla)){
					aux.setCara1C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(1),"Y"));
					aux.setCara2C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(2),"Y"));
					aux.setCara3C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(3),"Y"));
					aux.setCara4C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(4),"Y"));
					aux.setCara5C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(5),"Y"));
					aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"Y"));
					aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"Y"));
					aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"Y"));
					aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"Y"));
					aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"Y"));
					
					aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"Y"));
			}else{
					aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImg(pacId, aux.getDienteId(),"Y",vestibular,tabla));
					 if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
						 aux.setCara1D("none");
						 aux.setCara2D("none");
						 aux.setCara3D("none");
						 aux.setCara4D("none");
						 aux.setCara5D("none");
						 aux.setProbImgActIMD("block");
						 
					 }
			}
				
				
				piezas.add(aux);				    	
			    	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
	    
	    
 public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesLingualViewV2(Integer pacId, String vestibular,String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta,isadulto")
				.append(" 	from exme_mo_piezadental")
				.append(" 	where isactive='Y' and islingual = 'Y'")
				.append(" 	and mapaadulto is null")
				.append(" 	and mapainfantil is null")
				.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
				.append(" 	order by secuencia");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesImg("Y"));
				
				if(MEXMEMO_DentalProblems.getTypeProblemV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)){
					
					aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"Y")+"1.png");
					aux.setCara1(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(1),"Y")));
					
					aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"Y")+"2.png");
					aux.setCara2(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(2),"Y")));
					
					aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"Y")+"3.png");
					aux.setCara3(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(3),"Y")));
					
					aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"Y")+"4.png");
					aux.setCara4(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(4),"Y")));
					
					aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"Y")+"5.png");
					aux.setCara5(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(5),"Y")));
					
					aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"Y"));
					aux.setProbImgAct(new Integer(MEXMEMO_DentalProblems.getProblemID_ByCaraC3(pacId, aux.getDienteId(),"Y")));
					aux.setTablaDienteC3Act(new Integer(MEXMEMO_DentalProblems.getProblemID_ByCaraC3(pacId, aux.getDienteId(),"Y")));
			}else{
					aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla));
					 if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
						 aux.setCara1D("none");
						 aux.setCara2D("none");
						 aux.setCara3D("none");
						 aux.setCara4D("none");
						 aux.setCara5D("none");
						 aux.setProbImgActIMD("block");
						 aux.setProbImgAct(new Integer(MEXMEMO_DentalProblems.getProblem_IDByImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)));
						 aux.setTablaDienteC3Act(new Integer(MEXMEMO_DentalProblems.getProblem_IDByImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)));
					 }
			}
				
				
				piezas.add(aux);				    	
			    	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
	  
 public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesLingualViewPerio(Integer pacId, String vestibular, String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta,isadulto")
				.append(" 	from exme_mo_piezadental")
				.append(" 	where isactive='Y' and islingual = 'Y'")
				.append(" 	and mapaadulto is null")
				.append(" 	and mapainfantil is null")
				.append(" 	and isadulto = 'Y'")
				.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
				.append(" 	order by secuencia");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesTabla(aux.getPacId(), aux.getDienteId(),"N",vestibular,tabla));
				
				//if(MEXMEMO_DentalProblems.getTypeProblem(pacId, aux.getDienteId(),"N",vestibular, tabla)){
				/*	aux.setCara1C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(5),"N"));
					aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"N"));
				*/	
					aux.setCara1P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(5),"N"));
					aux.setCara6P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(6),"N"));
					aux.setCara7P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(7),"N"));
					aux.setCara8P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(8),"N"));
					aux.setCara9P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(9),"N"));
					
					aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"N"));
					
			//}else{
					aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImg(pacId, aux.getDienteId(),"N",vestibular,tabla));
					 //if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
						 //aux.setCara1D("none");
						 //aux.setCara2D("none");
						 //aux.setCara3D("none");
						 //aux.setCara4D("none");
						 //aux.setCara5D("none");
						 //aux.setProbImgActIMD("block");
						 
					 //}
			//}
				
				
				piezas.add(aux);				    	
			    	
				  
				}
			
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
	    
 public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesLingual() throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
								
				sql.append(" 	select  exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, mapaadulto, mapainfantil") 
				   .append(" 	from(")
				   .append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, mapaadulto, mapainfantil") 
				   .append(" 	from exme_mo_piezadental")
				   .append(" 	where isactive='Y' and islingual = 'Y'")
				   .append(" 	order by exme_mo_piezadental_id")
				   .append(" 	)");
				
				if (DB.isPostgreSQL()) {
					sql.append(" AS ");
				} else {
					sql.append(StringUtils.EMPTY);
				}
				
				sql.append(" consulta ");
				
				if (DB.isOracle()) {
					sql.append(" 	where rownum < 17");
				}
				
				sql.append(" 	order by exme_mo_piezadental_id");
				
				if (DB.isPostgreSQL()) {
					sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 16));
				}
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
					aux = new MO_PiezaDental_VO();
					
					aux.setDienteId(new Integer(rs.getInt(1)));
					aux.setDescripcion(rs.getString(3));
					aux.setNombre(rs.getString(4));
					aux.setLingual(rs.getString(5));
					aux.setRuta(rs.getString(6));
					aux.setMapaA(rs.getString(7));
					aux.setMapaI(rs.getString(8));
				
					piezas.add(aux);				    	
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getPiezasDentalesLingual - sql: " + sql, e);
				
			} finally {
				DB.close(rs, pstmt);
			}
			
			return piezas;
	    }
	    
	    
	    /**
		 * Obtiene las piezas dentales configuradas en el catalogo. 
		 *
		 * @return ArrayList catalogoPiezasDentales
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesPalatino() throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
			sql.append(" 	select  exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, mapaadulto, mapainfantil") 
			   .append(" 	from(")
			   .append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, mapaadulto, mapainfantil") 
			   .append(" 	from exme_mo_piezadental")
			   .append(" 	where isactive='Y' and islingual = 'N'")
			   .append(" 	order by exme_mo_piezadental_id")
			   .append(" 	)");
			   
			if (DB.isPostgreSQL()) {
				sql.append(" AS ");
			} else {
				sql.append(StringUtils.EMPTY);
			}
				
			sql.append(" piezaDental ");
				
			if (DB.isOracle()) {
				sql.append(" 	where rownum < 17");
			}
		   
		    sql.append(" 	order by exme_mo_piezadental_id");
		    
			if (DB.isPostgreSQL()) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 16));
			}
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
					aux = new MO_PiezaDental_VO();
					
					aux.setDienteId(new Integer(rs.getInt(1)));
					aux.setDescripcion(rs.getString(3));
					aux.setNombre(rs.getString(4));
					aux.setLingual(rs.getString(5));
					aux.setRuta(rs.getString(6));
					aux.setMapaA(rs.getString(7));
					aux.setMapaI(rs.getString(8));
					
					piezas.add(aux);				    	
				}
			
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getPiezasDentalesPalatino - sql: " 
						+ sql, e);
				
			} finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
	    
       public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesPalatinoView(Integer pacId, String vestibular, String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, isadulto")
			.append(" 	from exme_mo_piezadental")
			.append(" 	where isactive='Y' and islingual = 'N'")
			.append(" 	and mapaadulto is null")
			.append(" 	and mapainfantil is null")
			.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
			.append(" 	order by secuencia");

				
				
	    		
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesImg("Y"));
				
				if(MEXMEMO_DentalProblems.getTypeProblem(pacId, aux.getDienteId(),"Y", vestibular,tabla)){
						aux.setCara1C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(1),"Y"));
						aux.setCara2C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(2),"Y"));
						aux.setCara3C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(3),"Y"));
						aux.setCara4C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(4),"Y"));
						aux.setCara5C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(5),"Y"));
						aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"Y"));
						aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"Y"));
						aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"Y"));
						aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"Y"));
						aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"Y"));
						
						aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"Y"));
				}else{
						aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImg(pacId, aux.getDienteId(),"Y",vestibular,tabla));
						 if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
							 aux.setCara1D("none");
							 aux.setCara2D("none");
							 aux.setCara3D("none");
							 aux.setCara4D("none");
							 aux.setCara5D("none");
							 aux.setProbImgActIMD("block");
							 
						 }
				}
				piezas.add(aux);				    	
			    	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
       
       public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesPalatinoViewV2(Integer pacId, String vestibular, String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, isadulto")
			.append(" 	from exme_mo_piezadental")
			.append(" 	where isactive='Y' and islingual = 'N'")
			.append(" 	and mapaadulto is null")
			.append(" 	and mapainfantil is null")
			.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
			.append(" 	order by secuencia");

				
				
	    		
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesImg("Y"));
				
			if(MEXMEMO_DentalProblems.getTypeProblemV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)){
					
					aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"Y")+"1.png");
					aux.setCara1(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(1),"Y")));
					
					aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"Y")+"2.png");
					aux.setCara2(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(2),"Y")));
					
					aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"Y")+"3.png");
					aux.setCara3(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(3),"Y")));
					
					aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"Y")+"4.png");
					aux.setCara4(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(4),"Y")));
					
					aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"Y")+"5.png");
					aux.setCara5(new Integer(MEXMEMO_DentalProblems.getProblemByID(pacId, aux.getDienteId(), new Integer(5),"Y")));
					
					aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"Y"));
					aux.setProbImgAct(new Integer(MEXMEMO_DentalProblems.getProblemID_ByCaraC3(pacId, aux.getDienteId(),"Y")));
					aux.setTablaDienteC3Act(new Integer(MEXMEMO_DentalProblems.getProblemID_ByCaraC3(pacId, aux.getDienteId(),"Y")));
			}else{
					aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla));
					 if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
						 aux.setCara1D("none");
						 aux.setCara2D("none");
						 aux.setCara3D("none");
						 aux.setCara4D("none");
						 aux.setCara5D("none");
						 aux.setProbImgActIMD("block");
						 aux.setProbImgAct(new Integer(MEXMEMO_DentalProblems.getProblem_IDByImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)));
						 aux.setTablaDienteC3Act(new Integer(MEXMEMO_DentalProblems.getProblem_IDByImgV2(pacId, aux.getDienteId(),"Y",vestibular,tabla)));
					 }
			}
				
				
				piezas.add(aux);				    	
			    	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
       
       public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesPalatinoViewPerio(Integer pacId, String vestibular, String tabla) throws SQLException {
			
	    	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    		
			sql.append(" 	select   exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta, isadulto")
			.append(" 	from exme_mo_piezadental")
			.append(" 	where isactive='Y' and islingual = 'N'")
			.append(" 	and mapaadulto is null")
			.append(" 	and mapainfantil is null")
			.append(" 	and isadulto = 'Y'")
			.append(" 	group by isadulto, secuencia,exme_mo_piezadental_ID,NAME,DESCRIPTION,VALUE,islingual,ruta")
			.append(" 	order by secuencia");

				
				
	    		
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				rs = pstmt.executeQuery();
				MO_PiezaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_PiezaDental_VO();
				
				aux.setDienteId(new Integer(rs.getInt(1)));
				aux.setNombre(rs.getString(2));
				aux.setDescripcion(rs.getString(3));
				aux.setValue(rs.getString(4));
				aux.setLingual(rs.getString(5));
				aux.setRuta(rs.getString(6));
				aux.setDeAdulto(rs.getString(7));
				aux.setPacId(pacId);
				aux.setProblemas(MEXMEMO_DentalProblems.getProblemasDentalesTabla(aux.getPacId(), aux.getDienteId(),"N",vestibular, tabla));
				
				//if(MEXMEMO_DentalProblems.getTypeProblem(pacId, aux.getDienteId(),"N",vestibular,tabla)){
				/*	aux.setCara1C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5C(MEXMEMO_DentalProblems.getProblemByCaraC1(pacId, aux.getDienteId(), new Integer(5),"N"));
					aux.setCara1B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5B(MEXMEMO_DentalProblems.getProblemByCaraC2(pacId, aux.getDienteId(), new Integer(5),"N"));
				*/	
					aux.setCara1P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(1),"N"));
					aux.setCara2P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(2),"N"));
					aux.setCara3P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(3),"N"));
					aux.setCara4P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(4),"N"));
					aux.setCara5P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(5),"N"));
					aux.setCara6P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(6),"N"));
					aux.setCara7P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(7),"N"));
					aux.setCara8P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(8),"N"));
					aux.setCara9P(MEXMEMO_DentalProblems.getProblemByCaraName(pacId, aux.getDienteId(), new Integer(9),"N"));
					
					aux.setTablaDienteC3(MEXMEMO_DentalProblems.getProblemByCaraC3(pacId, aux.getDienteId(),"N"));
			//}else{
					aux.setProbImgActIM(MEXMEMO_DentalProblems.getProblemByCaraImg(pacId, aux.getDienteId(),"N",vestibular, tabla));
					 //if(aux.getProbImgActIM() != null && !aux.getProbImgActIM().equalsIgnoreCase("")){
						 //aux.setCara1D("none");
						 //aux.setCara2D("none");
						// aux.setCara3D("none");
						 //aux.setCara4D("none");
						 //aux.setCara5D("none");
						 //aux.setProbImgActIMD("block");
						 
					 //}
			//}
				
				piezas.add(aux);				    	
			    	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally {
				DB.close(rs, pstmt);
			}
			return piezas;
	    }
       
	    /**
		 * Obtiene informacion de las piezas dentales y su estado en el expediente. 
		 *
		 * @param String esOdontograma "Y"
		 * @param Integer pacientesID
		 *  
		 * @return ArrayList piezasDentales
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static ArrayList<MO_PiezaDental_VO> getPiezasDentalesAjax(String odonto, int pac) throws SQLException {
		
  	ArrayList<MO_PiezaDental_VO> piezas = new ArrayList<MO_PiezaDental_VO>();
  	
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
  		
			sql.append(" 	select exme_mo_piezadental.exme_mo_piezadental_ID, exme_mo_piezadental.NAME, exme_mo_piezadental.DESCRIPTION,") 
			   .append(" 	exme_mo_piezadental.VALUE")
			   .append(" 	from exme_mo_piezadental")
			   .append(" 	where exme_mo_piezadental.isactive='Y'")  
			   .append(" 	ORDER BY exme_mo_piezadental.exme_mo_piezadental_id");
  		
  	PreparedStatement pstmt = null;
  	ResultSet rs = null;
  	
  	
  		    	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			MO_PiezaDental_VO aux = null;
			MO_PiezaDental_VO aux2 = null;
			
			while (rs.next()) {
			aux= new MO_PiezaDental_VO();
			
			aux.setDienteId(new Integer(rs.getInt(1)));
			aux.setDescripcion(rs.getString(3));
			aux.setNombre(rs.getString(4));
			
			aux2= new MO_PiezaDental_VO();
			aux2 = MEXMEMO_PiezaDental.getDiente(aux.getNombre(), odonto, pac);
			if(aux2 != null){
			aux.setValue(aux2.getValue());
			aux.setSangrado(aux2.getSangrado());
			aux.setAusente(aux2.getAusente());
			aux.setAdulto(aux2.getAdulto());
			aux.setPlaca(aux2.getPlaca());
			aux.setCalculo(aux2.getCalculo());
			aux.setSupuracion(aux2.getSupuracion());
			}else{
				
				aux = null;
			}
			
			piezas.add(aux);				    	
		    	
			  
			}
		
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
			
		}finally {
			DB.close(rs, pstmt);
		}
		return piezas;
  }
  
    
	    /**
		 * Obtiene las piezas dentales configuradas para el pediodontograma.
		 *
		 * @return ArrayList Dientes configuradopas para periodontograma.
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static ArrayList<MO_DetallePerio_VO> getPiezasDentalesPerio(String ling) throws SQLException {
		
  	ArrayList<MO_DetallePerio_VO> piezas = new ArrayList<MO_DetallePerio_VO>();
  	
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
  		
			sql.append(" 	select exme_mo_piezadental.exme_mo_piezadental_ID, exme_mo_piezadental.NAME, exme_mo_piezadental.DESCRIPTION,") 
			   .append(" 	exme_mo_piezadental.VALUE,exme_mo_piezadental.islingual")
			   .append(" 	from exme_mo_piezadental") 
			   .append(" 	where exme_mo_piezadental.isactive='Y' and exme_mo_piezadental.islingual = ?")
			   .append(" 	ORDER BY exme_mo_piezadental.exme_mo_piezadental_id");
  		
  	PreparedStatement pstmt = null;
  	ResultSet rs = null;
  	
  	
  		    	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, ling);
			rs = pstmt.executeQuery();
			MO_DetallePerio_VO aux = null;
			
			while (rs.next()) {
			aux= new MO_DetallePerio_VO();
			
			aux.setDienteId(new Integer(rs.getInt(1)));
			aux.setNombre(rs.getString(2));
			aux.setDesc(rs.getString(3));
			aux.setValor(rs.getString(4));
			aux.setLingual(rs.getString(5));
			
			piezas.add(aux);				    	
		    	
			  
			}
		
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
			
		}finally {
			DB.close(rs, pstmt);
		}
		return piezas;
  }
  
	    /**
		 * Obtiene la informacion de cada pieza dental del expediente. 
		 *
		 * @param String Value del diente
		 * @param String esOdontograma "Y"
		 * @param Integer pacienteID
		 *  
		 * @return ArrayList PiezasDentales
	     * @throws SQLException 
		 * 
		 * @throws Exception
		 */
	    public static MO_PiezaDental_VO getDiente(String diente, String odonto, int pac) throws SQLException {
		
	  	
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append(" 	select ")
			.append(" 	exme_mo_piezadental.exme_mo_piezadental_ID,") 
			.append(" 	exme_mo_piezadental.NAME, ")
			.append(" 	exme_mo_piezadental.DESCRIPTION,")
			.append(" 	exme_mo_piezadental.VALUE,")
			.append(" 	exme_mo_expediente.exme_mo_piezadental_id,") 
			.append(" 	exme_mo_expediente.sangrado, ")
			.append(" 	exme_mo_expediente.ausenciadiente,") 
			.append(" 	exme_mo_expediente.esadulto,")
			.append(" 	exme_mo_expediente.placa, ")
			.append(" 	exme_mo_expediente.escalculo,") 
			.append(" 	exme_mo_expediente.essupuracion,")
			.append(" 	exme_mo_expediente.created,")
			.append(" 	exme_mo_expediente.esodontograma")
			.append(" 	from")
			.append(" 	exme_mo_expediente")
			.append(" 	inner join exme_mo_piezadental on (exme_mo_expediente.exme_mo_piezadental_id = exme_mo_piezadental.exme_mo_piezadental_id)")
			.append(" 	where exme_mo_expediente.esodontograma = ? and exme_mo_piezadental.value = ?  ")
			.append(" 	and exme_mo_expediente.exme_paciente_id = ? ")
			.append(" 	order by exme_mo_expediente.created desc");
		   
		
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	MO_PiezaDental_VO pieza = null;
		    	
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, odonto);
			pstmt.setString(2, diente);
			pstmt.setInt(3, pac);
			
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				pieza= new MO_PiezaDental_VO();
				
				pieza.setDienteId(new Integer(rs.getInt(1)));
				pieza.setNombre(rs.getString(2));
				pieza.setDescripcion(rs.getString(3));
				pieza.setValue(rs.getString(4));
				//pieza.setDienteId(new Integer(rs.getInt(5)));
				pieza.setSangrado(rs.getString(6));
				pieza.setAusente(rs.getString(7));
				pieza.setAdulto(rs.getString(8));
				pieza.setPlaca(rs.getString(9));
				pieza.setCalculo(rs.getString(10));
				pieza.setSupuracion(rs.getString(11));
				
				pieza.setIsAdulto(new Boolean(pieza.getAdulto().equalsIgnoreCase("N") ? "false" : "true"));
				pieza.setIsAusente(new Boolean(pieza.getAusente().equalsIgnoreCase("N") ? "false" : "true"));
				pieza.setIsPlaca(new Boolean(pieza.getPlaca().equalsIgnoreCase("N") ? "false" : "true"));
				pieza.setIsSangrado(new Boolean(pieza.getSangrado().equalsIgnoreCase("N") ? "false" : "true"));
				pieza.setIsCalculo(new Boolean(pieza.getCalculo().equalsIgnoreCase("N") ? "false" : "true"));
				pieza.setIsSupuracion(new Boolean(pieza.getSupuracion().equalsIgnoreCase("N") ? "false" : "true"));
				
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
			
		}finally {
			DB.close(rs, pstmt);
		}
		return pieza;
}

}
