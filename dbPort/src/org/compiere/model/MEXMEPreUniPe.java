package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEPreUniPe extends X_EXME_PreUniPe{
	
	private static CLogger		slog = CLogger.getCLogger (MEXMEPreUniPe.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	double acum = 0;
	private	static double cont = 0;
//	private static int agregado = 0;
	private static int id = 0;
	 
	public MEXMEPreUniPe(Properties ctx, int EXME_PREUNI_ID, String trxName) {
		super(ctx, EXME_PREUNI_ID, trxName);
	}
	
	public MEXMEPreUniPe(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	 Vector<String> targetMesIDs;
	 
	 	 	 
     public Vector<String> getMesIDs(int mes_id) {
           
             String sql = "select periodo from exme_preunipe where isactive='Y' and exme_preuni_id = ? ";
             PreparedStatement pstmt = null;
             ResultSet rs = null;

             try {

                     pstmt = DB.prepareStatement(sql, get_TrxName());
                     pstmt.setInt(1, mes_id);
                     rs = pstmt.executeQuery();
                     targetMesIDs = new Vector<String>();

                     while (rs.next()) {
                             targetMesIDs.add(rs.getString(1));
                     }
             } catch (SQLException e) {
                     slog.log(Level.SEVERE, "Error", e);
             } finally {
            	 DB.close(rs, pstmt);
             }

             return targetMesIDs;
     
     
     }
     
     Vector<String> targetDetalleIDs;

 	public Vector<String> getPreUniPeIDs(int preunipe_id) {
 		
 		String sql = "select exme_preunipe_id from exme_preunipe where exme_preuni_id="
 				+ preunipe_id + " and isactive='Y' order by periodo";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;

 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			
 			rs = pstmt.executeQuery();
 			targetDetalleIDs = new Vector<String>();

 			while (rs.next()) {
 				targetDetalleIDs.add(rs.getString(1));
 			}
 		} catch (SQLException e) {
 			slog.log(Level.SEVERE, "Error", e);
 		} finally {
 			DB.close(rs, pstmt);
 		}

 		return targetDetalleIDs;
 	}
     
     
     
     
     public boolean getIsAct(int id){
		 
		    String sql = "update exme_preunipe set isactive='N' where exme_preuni_id = ? ";
		   
		    PreparedStatement pstmt = null;
         boolean updated = false;

         try {
                 pstmt = DB.prepareStatement(sql, get_TrxName());
                 pstmt.setInt(1, id);
                 updated = (pstmt.executeUpdate() == 1 ) ? true : false; 
                 
         } catch (SQLException e) {
                 slog.log(Level.SEVERE, "Error", e);
         }finally {
  			DB.close(pstmt);
  		}

		 
		 return updated;
	 }
     
     public double acumular(double acumulado, double solicitado, double conta){
    	     cont = conta;	 
    		 acum = acumulado;
    		 cont = cont + acum;
    		 //System.out.print("El acumulado es: "+cont+" y el Solicitado es: "+solicitado);
    		 
    	 return cont;
     }
     
    
     
     public int validaId(int idVal){
    	
    	 	if(id == 0 || id == idVal)
    	 		id = idVal;
    	 	else id=0;
    	 
    	 	return id;
     }
     
     public void actualizaPe(Double solicitado, int id) {
  		
  		String sql = "update exme_preunipe set pre_solicitado=? where exme_preunipe_id = ?";
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;

  		try {

  			pstmt = DB.prepareStatement(sql, get_TrxName());
  			pstmt.setDouble(1, solicitado);
  			pstmt.setInt(2, id);
  			rs = pstmt.executeQuery();
  			
  		} catch (SQLException e) {
  			slog.log(Level.SEVERE, "Error", e);
  		} finally {
 			DB.close(rs, pstmt);
 		}
 	}
     
     double d = 0.0;
     Vector<Double> targetAcumulado;
     Vector<Double> targetSol;
  	public double getAcumulado(int preuni_id) {
  		
  		double acum = 0.0;
  		String sql = "select pre_solicitado from exme_preunipe where isactive='Y' and exme_preuni_id="
  				+ preuni_id;
  		
  		String sql2 ="select pre_solicitado from exme_preuni where exme_preuni_id="+ preuni_id;
  		
  		PreparedStatement pstmt = null;
  		PreparedStatement pstmt2 = null;
  		ResultSet rs = null;
  		ResultSet rs2 = null;

  		try {

  			pstmt = DB.prepareStatement(sql, get_TrxName());
  			pstmt2 = DB.prepareStatement(sql2, get_TrxName());
  			
  			rs = pstmt.executeQuery();
  			rs2= pstmt2.executeQuery();
  			
  			targetAcumulado = new Vector<Double>();
  			targetSol = new Vector<Double>();
  			
  			while (rs.next()) {
  				targetAcumulado.add(rs.getDouble(1));
  				
  			}
  			while (rs2.next()){
  				targetSol.add(rs2.getDouble(1));
  			}
  			
  			for(int i=0;i < targetAcumulado.size();i++){
  				d = targetAcumulado.get(i);
  				acum = acum + d;
  			}
  			
  			acum = targetSol.get(0) - acum;
  			//System.out.println("Solicitado:"+targetSol.get(0)+ " - Acumulado: "+acum);
  			
  		} catch (SQLException e) {
  			slog.log(Level.SEVERE, "Error", e);
  		} finally {
 			DB.close(rs, pstmt);
 		}

  		return Math.round(acum*Math.pow(10,2))/Math.pow(10,2);
  		//return Math.round(nD*Math.pow(10,nDec))/Math.pow(10,nDec);
  	}
  	
  	int idpreuni=0;
  	public int obtenIdPreUni(int id) {
  		
  		String sql = "select exme_preuni_id from exme_preunipe where exme_preunipe_id = ? ";
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;

  		try {

  			pstmt = DB.prepareStatement(sql, get_TrxName());
  			pstmt.setInt(1, id);
  			rs = pstmt.executeQuery();
  			
  			idpreuni = rs.getInt(1);
  			
  		} catch (SQLException e) {
  			slog.log(Level.SEVERE, "Error", e);
  		}
 	return idpreuni;
  	}
  	
  	public void eliminar(int id){
 		
 		String sql = "update exme_preunipe set isactive='N' where exme_preunipe_id = ?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setDouble(1, id);
 			rs = pstmt.executeQuery();
 			
 		} catch (SQLException e) {
 			slog.log(Level.SEVERE, "Error", e);
 		} finally {
 			DB.close(rs, pstmt);
 		}
 		
 	}
  	int periodo2;
  	public int getIdPreUniPe(int periodo){
  		
  		String sql = "select exme_preunipe_id from exme_preunipe where periodo = ?";
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		
  		try{
  			pstmt = DB.prepareStatement(sql, get_TrxName());
  			pstmt.setInt(1, periodo);
  			rs = pstmt.executeQuery();
  			
  			if(rs.next())
 				periodo2 = rs.getInt(1);
  			
  			//System.out.println("Esto es lo que vale periodo2: "+periodo2);
  			
  		}catch(SQLException e){
  			slog.log(Level.SEVERE, "Error", e);
  		} finally {
 			DB.close(rs, pstmt);
 		}
  		
  		return periodo2;
  	}
  	
  	public MEXMEPreUniPe[] getLines(int idPreUni) {
        
        ArrayList<MEXMEPreUniPe> list = new ArrayList<MEXMEPreUniPe>();
        String sql = "select * from exme_preunipe where exme_preuni_id = ? order by periodo";
        PreparedStatement pstmt = null;
        ResultSet rs  = null;
        try {
                pstmt = DB.prepareStatement(sql, get_TrxName());
                pstmt.setInt(1, idPreUni);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                        
                        list.add(new MEXMEPreUniPe(getCtx(), rs, get_TrxName()));
                        
                }
                
        } catch (Exception e) {
                log.log(Level.SEVERE, "getLines", e);
        }finally {
 			DB.close(rs, pstmt);
 		}
        //System.out.println("Agregado a la lista: "+list);
        MEXMEPreUniPe[] m_detalles = new MEXMEPreUniPe[list.size()];
        list.toArray(m_detalles);
        list=null;
        return m_detalles;
}
  	int idPreUniPe;
  	public int getIdPreUniPe(String periodo, int idPreUni){
 		
 		String sql = "select exme_preunipe_id from exme_preunipe where exme_preuni_id = ? and periodo = ?";
 		
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, idPreUni);
 			pstmt.setString(2, periodo);
 			rs = pstmt.executeQuery();
 			
 			if(rs.next())
 				idPreUniPe = rs.getInt(1);
 			//System.out.println("Esto es mi idPreUniPe == "+idPreUniPe);
 			
 			
 		} catch (SQLException e) {
 			slog.log(Level.SEVERE, "Error", e);
 		} finally {
 			DB.close(rs, pstmt);
 		}
 		
 		return idPreUniPe;
 	}
  	public void elimina(Properties ctx, int idPreUniPe){
 		
 		/*String sql = "delete from exme_preunipe where exme_preunipe_id = ?";
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		try {

 			pstmt = DB.prepareStatement(sql, get_TrxName());
 			pstmt.setInt(1, idPreUniPe);
 			rs = pstmt.executeQuery();
 			
 		} catch (SQLException e) {
 			slog.log(Level.SEVERE, "Error", e);
 		}*/
  	//Liz de la Garza - Cambio del proceso para utilizarlo dentro del modelo de persistencia (para log de cambios)
  		MEXMEPreUniPe preuni = new MEXMEPreUniPe(ctx, idPreUniPe, null);
  		if (preuni != null) {
  			if (!preuni.delete(false))
  				log.log(Level.SEVERE, "elimina");
  		}
 		
 	}
  	
  	
  	
    
    
}
