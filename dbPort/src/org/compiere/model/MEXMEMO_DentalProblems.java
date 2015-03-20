package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.mo.MO_ProblemaDental_VO;

public class MEXMEMO_DentalProblems extends X_EXME_MO_DentalProblems {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEMO_DentalProblems.class);
	
	public MEXMEMO_DentalProblems(Properties ctx,
			int EXME_MO_DentalProblems_ID, String trxName) {
		super(ctx, EXME_MO_DentalProblems_ID, trxName);
	}

	public MEXMEMO_DentalProblems(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	 public static ArrayList<MO_ProblemaDental_VO> getProblemasDentales(String esOdonto) throws SQLException {
			
	    	ArrayList<MO_ProblemaDental_VO> problemas = new ArrayList<MO_ProblemaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			
		 sql.append(" 	select exme_mo_dentalproblems_id, name, description, color1, color2, imagen, diagrama, value, esodontograma,color3,isnormal")
			.append(" 	from exme_mo_dentalproblems")
			.append(" 	where esodontograma = ?")
			.append(" 	and diagrama = 'Y'")
			.append(" 	and isactive = 'Y'")
			.append(" 	order by isnormal,exme_mo_dentalproblems_id,value");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, esOdonto);
				rs = pstmt.executeQuery();
				MO_ProblemaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_ProblemaDental_VO();
				
				aux.setProblemaId(new Integer(rs.getInt(1)));
				aux.setName(rs.getString(2));
				aux.setDesc(rs.getString(3));
				aux.setColor1(rs.getString(4));
				aux.setColor2(rs.getString(5));
				aux.setImagen(rs.getString(6));
				aux.setParaDiagrama(rs.getString(7));
				aux.setValue(rs.getString(8));
				aux.setEsOdonto(rs.getString(9));
				aux.setColor3(rs.getString(10));
				aux.setSano(rs.getString(11));
				
				problemas.add(aux);				    	
			    	
				}
				
				
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
			}finally{				
				DB.close(rs, pstmt);
				
			}
			return problemas;
	    }
	 
	 public static int getProblemaDentalSano(String esOdonto) throws SQLException {
			
	    	int ret = 0;
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			
		 sql.append(" 	select exme_mo_dentalproblems_id")
			.append(" 	from exme_mo_dentalproblems")
			.append(" 	where esodontograma = ?")
			.append(" 	and diagrama = 'Y'")
			.append(" 	and isactive = 'Y'")
			.append(" 	and isnormal = 'Y'")
			.append(" 	order by isnormal,exme_mo_dentalproblems_id,value");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, esOdonto);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				
				ret = rs.getInt(1);
				  
				}
				
				
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static ArrayList<MO_ProblemaDental_VO> getProblemasDentalesTabla(Integer pac, Integer diente, String esOdonto, String vestibular, String tabla) throws SQLException {
			
	    	ArrayList<MO_ProblemaDental_VO> problemas = new ArrayList<MO_ProblemaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			
		 sql.append(" 	select exme_mo_dentalproblems_id, name, description, color1, color2, imagen, diagrama, value, esodontograma")
			.append(" 	from exme_mo_dentalproblems")
			.append(" 	where esodontograma = ?")
			.append(" 	and diagrama = 'N'")
			.append(" 	order by exme_mo_dentalproblems_id");
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, esOdonto);
				rs = pstmt.executeQuery();
				MO_ProblemaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_ProblemaDental_VO();
				
				aux.setProblemaId(new Integer(rs.getInt(1)));
				aux.setName(rs.getString(2));
				aux.setDesc(rs.getString(3));
				aux.setColor1(rs.getString(4));
				aux.setColor2(rs.getString(5));
				aux.setImagen(rs.getString(6));
				aux.setParaDiagrama(rs.getString(7));
				aux.setValue(rs.getString(8));
				aux.setEsOdonto(rs.getString(9));
				
				aux.setIsAdded(MEXMEMO_Expediente.getExpedietePerioTabla(pac, diente, aux.getProblemaId(),vestibular, tabla));
				
				problemas.add(aux);				    	
			    	
				  
				}
								
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return problemas;
	    }
	 
	 public static ArrayList<MO_ProblemaDental_VO> getProblemasDentalesImg(String esOdonto) throws SQLException {
			
	    	ArrayList<MO_ProblemaDental_VO> problemas = new ArrayList<MO_ProblemaDental_VO>();
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    	
			
		 sql.append(" 	select exme_mo_dentalproblems_id")
			.append(" 	from exme_mo_dentalproblems")
			.append(" 	where esodontograma = ?")
			.append(" 	and isactive = 'Y'")
			.append(" 	and imagen is not null");

				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setString(1, esOdonto);
				rs = pstmt.executeQuery();
				MO_ProblemaDental_VO aux = null;
				
				while (rs.next()) {
				aux= new MO_ProblemaDental_VO();
				aux.setProblemaId(new Integer(rs.getInt(1)));
				
				problemas.add(aux);				    	
			    	
				  
				}
								
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return problemas;
	    }
	 
	 
	 public static String getProblemByCaraC1(Integer pac, Integer diente, Integer cara, String esOdo) throws SQLException {
			
		 String ret = "FFFFFF";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color1")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expp.cara = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setInt(3, cara.intValue());
				pstmt.setString(4, esOdo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getString(1);	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 
	 public static String getProblemByCaraC3(Integer pac, Integer diente, String esOdo) throws SQLException {
			
		 String ret = "000000";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color3")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expe.esodontograma = ?")
	   //.append(" 	and expp.cara is null")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString(1) != null && !rs.getString(1).equalsIgnoreCase("")){
						ret = rs.getString(1);	
					}
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static int getProblemID_ByCaraC3(Integer pac, Integer diente, String esOdo) throws SQLException {
			
		
		 int ret = 0;
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color3, prob.exme_mo_dentalproblems_id")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expe.esodontograma = ?")
	   //.append(" 	and expp.cara is null")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				 ret = MEXMEMO_DentalProblems.getProblemaDentalSano(esOdo);
				 
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString(1) != null && !rs.getString(1).equalsIgnoreCase("")){
						ret = rs.getInt(2);
					}
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 public static String getProblemByCaraC2(Integer pac, Integer diente, Integer cara, String esOdo) throws SQLException {
			
		 String ret = "images/odontologia/library/B";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.imagen")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expp.cara = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setInt(3, cara.intValue());
				pstmt.setString(4, esOdo);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getString(1);	
				  
				}
			
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static int getProblemByID(Integer pac, Integer diente, Integer cara, String esOdo) throws SQLException {
			
		 int ret = 0;
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.exme_mo_dentalproblems_id")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expp.cara = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setInt(3, cara.intValue());
				pstmt.setString(4, esOdo);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getInt(1);				  
				}else{
					ret = MEXMEMO_DentalProblems.getProblemaDentalSano(esOdo);
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static String getProblemByCaraName(Integer pac, Integer diente, Integer cara, String esOdo) throws SQLException {
			
		 String ret = "";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.Name")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expp.cara = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setInt(3, cara.intValue());
				pstmt.setString(4, esOdo);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getString(1);	
				  
				}
								
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static String getProblemByCaraImg(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 String ret = "";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.imagen")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		.append(" 	and expe.esodontograma = ?")
		 //.append(" 	AND prob.color1 IS NULL ")
         //.append(" 	AND prob.color2 IS NULL")
		.append(" 	AND prob.imagen IS NOT NULL")
         .append(" 	and expp.vestibular= ?")
         .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getString(1);	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static String getProblemByCaraImgV2(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 String ret = "";
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.imagen")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	AND prob.color1 IS NULL ")
         .append(" 	AND prob.color2 IS NULL")
         .append(" 	AND prob.color3 IS NULL")
		 .append(" 	AND prob.imagen IS NOT NULL")
         .append(" 	and expp.vestibular= ?")
         .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getString(1);	
				  
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static int getProblemDentalActual(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 int ret = 0;
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.exme_mo_dentalproblems_id")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expe.esodontograma = ?")
		 .append(" 	and expp.vestibular= ?")
         .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				ret = MEXMEMO_DentalProblems.getProblemaDentalSano(esOdo);
				
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				 ret = rs.getInt(1);	
				  
				}
								
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 public static Boolean getTypeProblem(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 Boolean ret = new Boolean(true);
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color1,prob.imagen")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		.append(" 	and expe.esodontograma = ?")
		 .append(" 	and expp.vestibular= ?")
		 .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString(2) != null && !rs.getString(2).equalsIgnoreCase("")){
						ret = new Boolean(false);
					}
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }

	 public static Boolean getTypeProblemV2(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 Boolean ret = new Boolean(true);
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color1")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		.append(" 	and expe.esodontograma = ?")
		 .append(" 	and expp.vestibular= ?")
		 .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString(1) == null || rs.getString(1).equalsIgnoreCase("")){
						ret = new Boolean(false);
					}
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
	 
	 public static int getProblem_IDByImgV2(Integer pac, Integer diente, String esOdo, String vestibular, String tabla) throws SQLException {
			
		 int ret = 0;
	    	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

	  sql.append(" 	select prob.color1,prob.color2,prob.color3,prob.exme_mo_dentalproblems_id")
		 .append(" 	from exme_mo_expediente expe")
		 .append(" 	inner join exme_mo_expdentalproblems expp on (expe.exme_mo_expediente_id = expp.exme_mo_expediente_id)")
		 .append(" 	inner join exme_mo_dentalproblems prob on (expp.exme_mo_dentalproblems_id = prob.exme_mo_dentalproblems_id)")
		 .append(" 	where expe.exme_paciente_id = ?")
		 .append(" 	and expe.exme_mo_piezadental_id = ?")
		 .append(" 	and expe.esodontograma = ?")
		 //.append(" 	AND prob.color1 IS NULL ")
         //.append(" 	AND prob.color2 IS NULL")
         //.append(" 	AND prob.color3 IS NULL")
		 //.append(" 	AND prob.imagen IS NOT NULL")
         .append(" 	and expp.vestibular= ?")
         .append(" 	and expp.fromtable = ?")
		 .append(" 	order by expp.created desc");
		 
				
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    		    	
			try {
				 ret = MEXMEMO_DentalProblems.getProblemaDentalSano(esOdo);
				 
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, pac.intValue());
				pstmt.setInt(2, diente.intValue());
				pstmt.setString(3, esOdo);
				pstmt.setString(4, vestibular);
				pstmt.setString(5, tabla);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString(1) == null || rs.getString(1).equalsIgnoreCase("")){
						if(rs.getString(2) == null || rs.getString(2).equalsIgnoreCase("")){
							if(rs.getString(3) == null || rs.getString(3).equalsIgnoreCase("")){
						
						          ret = rs.getInt(4);
							}
						}
					}
				}
				
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "getCitasMedico", e);
				
			}finally{				
				DB.close(rs, pstmt);
			}
			return ret;
	    }
}
