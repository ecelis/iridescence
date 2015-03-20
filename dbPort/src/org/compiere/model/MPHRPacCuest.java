package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.GridItem;

public class MPHRPacCuest extends X_PHR_PacCuest implements GridItem{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger */
	private static CLogger log = CLogger.getCLogger(MEXMEMedicoOrg.class);
	
	public MPHRPacCuest(Properties ctx, int PHR_PacCuest_ID, String trxName) {
		super(ctx, PHR_PacCuest_ID, trxName);
	}
	
	public MPHRPacCuest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	private IDColumn idColumn = null; 
	
	/**
	 * Método que retorna una lista de cuestionarios
	 * configurados por un usuario paciente.
	 * @param ctx
	 * @param usuario
	 * @return
	 */
	public static List<MPHRPacCuest> getCuestionariosPac(Properties ctx, int usuario){
		List<MPHRPacCuest> lst = new ArrayList<MPHRPacCuest>();
		StringBuilder sb = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		ResultSet result = null;
		PreparedStatement pstmt = null;
		
		sb.append("SELECT * FROM PHR_PacCuest WHERE CreatedBy = ? ");
		sb = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sb.toString(),MPHRPacCuest.Table_Name));
		sb.append(" ORDER BY Created DESC ");
		try{
			pstmt = DB.prepareStatement(sb.toString(), null);
			pstmt.setInt(1, usuario); 
			result = pstmt.executeQuery();
			
			while(result.next()){
				MPHRPacCuest pc = new MPHRPacCuest(ctx, result, null);
				lst.add(pc);
			}
		}catch(SQLException ex){
			log.log(Level.SEVERE, "MEXMEPacCuest at method getCuestionariosPac " + ex.getMessage());
		} finally{
			try{
				if(pstmt != null)
					pstmt.close();
				
				if(result != null)
					result.close();
				
				pstmt = null;
				result = null;
			}catch(SQLException e){
				log.log(Level.SEVERE, "While closing pstmt and result " + e.getMessage());
			}		
		}
		return lst;
	}

	@Override
	public String[] getColumns() {
		String[] array = {"idColumn", "value", "name", "description"};
		return array;
	}

	@Override
	public IDColumn getIdColumn() {
		if(idColumn==null){
			idColumn = new IDColumn(getPHR_PacCuest_ID());
		}
		return idColumn;
	}
}
