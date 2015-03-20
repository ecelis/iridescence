package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class MEXMEConfigOV extends X_EXME_Config_OV{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static logger			*/
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigOV.class);
	
	public MEXMEConfigOV(Properties ctx, int EXME_Config_OV_ID, String trxName) {
		super(ctx, EXME_Config_OV_ID, trxName);
	}

	public MEXMEConfigOV(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


	 /**
     * Obtenemos la configuracion de Config Office Visit de un hospital en particular.
     * (Cliente + Organizacion).
     * 
     * @param ctx
     * @return
     */
    public static MEXMEConfigOV find(Properties ctx, int exmeMedicoID, String trxName){
        
    	MEXMEConfigOV retValue = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append(" SELECT * FROM EXME_Config_OV WHERE EXME_Config_OV.IsActive='Y' ");
        sql.append(" and exme_medico_id = ? ");
        if(Env.getUserPatientID(ctx) < 0) //Si es un usuario paciente no agrega el accessLevel
        	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name));
        sql.append(" ORDER BY EXME_Config_OV.AD_Org_ID DESC ");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeMedicoID);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEConfigOV(ctx, rs, trxName);				
			}
			
        }catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
    
    public static MEXMEConfigOV findActPac(Properties ctx, int actPac, String trxName){
        
    	MEXMEConfigOV retValue = null;
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
                
        sql.append(" SELECT ov.*   ");
        sql.append(" FROM EXME_Config_OV ov  ");
        sql.append(" inner join exme_actpaciente ap on ap.exme_medico_id = ov.exme_medico_id  "); 
        sql.append(" WHERE ov.IsActive='Y'  ");
        sql.append(" and ap.exme_actpaciente_id = ?  ");
        sql.append(" and ov.ad_org_id = ap.ad_org_id  ");
        sql.append(" ORDER BY ov.AD_Org_ID DESC   ");
        
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, actPac);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEConfigOV(ctx, rs, trxName);				
			}
			
        }catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
    
    public static boolean findRepetitions(Properties ctx, ArrayList<Integer> ids, String trxName){
            	
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
                
        sql.append(" select max(contador) from (   ");
        sql.append(" select count(gcd.exme_cuestionario_id) as contador   ");
        sql.append(" from exme_grupocuestionario gc   ");
        sql.append(" inner join exme_grupocuestionariodet gcd on gcd.exme_grupocuestionario_id = gc.exme_grupocuestionario_id  and gcd.isactive= 'Y' ");
        sql.append(" where gc.exme_grupocuestionario_id in ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )   "); 
        sql.append(" group by gcd.exme_cuestionario_id   ");
        sql.append(" ) resultado    ");
        
             
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1, ids.get(0));
			pstmt.setInt(2, ids.get(1));
			pstmt.setInt(3, ids.get(2));
			pstmt.setInt(4, ids.get(3));
			pstmt.setInt(5, ids.get(4));
			pstmt.setInt(6, ids.get(5));
			pstmt.setInt(7, ids.get(6));
			pstmt.setInt(8, ids.get(7));
			pstmt.setInt(9, ids.get(8));
			pstmt.setInt(10, ids.get(9));
			pstmt.setInt(11, ids.get(10));
			pstmt.setInt(12, ids.get(11));
			pstmt.setInt(13, ids.get(12));
			pstmt.setInt(14, ids.get(13));
			pstmt.setInt(15, ids.get(14));
			pstmt.setInt(16, ids.get(15));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int aux = rs.getInt(1);
				if(aux > 1){
					return true;
				}
			}
			
        }catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return false;
	}
    
    /** SingleSysEsp AD_Reference_ID=1200613 */
	public static final int SINGLESYSESP_AD_Reference_ID=1200613;
	/** Breast = BR */
	public final String SINGLESYSESP_Breast = "BR";
	/** Cardiovascular = CA */
	public final String SINGLESYSESP_Cardiovascular = "CA";
	/** Ears, Nose, Mouth, and Throat = EN */
	public final String SINGLESYSESP_EarsNoseMouthAndThroat = "EN";
	/** Eyes = EY */
	public final String SINGLESYSESP_Eyes = "EY";
	/** Genitourinary = GE */
	public final String SINGLESYSESP_Genitourinary = "GE";
	/** Hematologic/Lymphatic/Immunologic = HL */
	public final String SINGLESYSESP_HematologicLymphaticImmunologic = "HL";
	/** Musculoskeletal = MU */
	public final String SINGLESYSESP_Musculoskeletal = "MU";
	/** Neurological = NE */
	public final String SINGLESYSESP_Neurological = "NE";
	/** Psychiatric = PS */
	public final String SINGLESYSESP_Psychiatric = "PS";
	/** Respiratory = RE */
	public final String SINGLESYSESP_Respiratory = "RE";
	/** Skin = SK */
	public final String SINGLESYSESP_Skin = "SK";
    
	private MEXMEGrupoCuestionario retBreast = null;
	public MEXMEGrupoCuestionario getBreastForm(){    	
    	if(retBreast == null && this.getBREAST_ID() > 0){
    		retBreast = new MEXMEGrupoCuestionario(Env.getCtx(), this.getBREAST_ID(), null);
    		retBreast.setSingleSysEspVal(this.SINGLESYSESP_Breast);
    	}
    	return retBreast;
    }
	
	private MEXMEGrupoCuestionario retCard = null;
    public MEXMEGrupoCuestionario getCardioForm(){    	
    	if(retCard == null && this.getCARDIO_ID() > 0){
    		retCard = new MEXMEGrupoCuestionario(Env.getCtx(), this.getCARDIO_ID(), null);
    		retCard.setSingleSysEspVal(this.SINGLESYSESP_Cardiovascular);
    	}
    	return retCard;
    }
    
    private MEXMEGrupoCuestionario retENMT = null;
    public MEXMEGrupoCuestionario getENMTForm(){    	
    	if(retENMT == null && this.getENMT_ID() > 0){
    		retENMT = new MEXMEGrupoCuestionario(Env.getCtx(), this.getENMT_ID(), null);
    		retENMT.setSingleSysEspVal(this.SINGLESYSESP_EarsNoseMouthAndThroat);
    	}
    	return retENMT;
    }
    
    private MEXMEGrupoCuestionario retEyes = null;
    public MEXMEGrupoCuestionario getEyesForm(){    	
    	if(retEyes == null && this.getEYES_ID() > 0){
    		retEyes = new MEXMEGrupoCuestionario(Env.getCtx(), this.getEYES_ID(), null);
    		retEyes.setSingleSysEspVal(this.SINGLESYSESP_Eyes);
    	}
    	return retEyes;	
    }
    
    private MEXMEGrupoCuestionario retGeni = null;
    public MEXMEGrupoCuestionario getGeniForm(){    	
    	if(retGeni == null && this.getGENIT_ID() > 0){
    		retGeni = new MEXMEGrupoCuestionario(Env.getCtx(), this.getGENIT_ID(), null);
    		retGeni.setSingleSysEspVal(this.SINGLESYSESP_Genitourinary);
    	}
    	return retGeni;
    }
    
    private MEXMEGrupoCuestionario retHema = null;
    public MEXMEGrupoCuestionario getHemaForm(){    	
    	if(retHema == null && this.getHEMATO_ID() > 0){
    		retHema = new MEXMEGrupoCuestionario(Env.getCtx(), this.getHEMATO_ID(), null);
    		retHema.setSingleSysEspVal(this.SINGLESYSESP_HematologicLymphaticImmunologic);
    	}
    	return retHema;
    }
    
    private MEXMEGrupoCuestionario retMusc = null;
    public MEXMEGrupoCuestionario getMuscForm(){    	
    	if(retMusc == null && this.getMUSCUL_ID() > 0){
    		retMusc = new MEXMEGrupoCuestionario(Env.getCtx(), this.getMUSCUL_ID(), null);
    		retMusc.setSingleSysEspVal(this.SINGLESYSESP_Musculoskeletal);
    	}
    	return retMusc;
    }
    
    private MEXMEGrupoCuestionario retNeur = null;
    public MEXMEGrupoCuestionario getNeurForm(){    	
    	if(retNeur == null && this.getNEUROL_ID() > 0){
    		retNeur = new MEXMEGrupoCuestionario(Env.getCtx(), this.getNEUROL_ID(), null);
    		retNeur.setSingleSysEspVal(this.SINGLESYSESP_Neurological);
    	}
    	return retNeur;
    }
    
    private MEXMEGrupoCuestionario retPsyc = null;
    public MEXMEGrupoCuestionario getPsycForm(){    	
    	if(retPsyc == null && this.getPSYCHI_ID() > 0){
    		retPsyc = new MEXMEGrupoCuestionario(Env.getCtx(), this.getPSYCHI_ID(), null);
    		retPsyc.setSingleSysEspVal(this.SINGLESYSESP_Psychiatric);
    	}
    	return retPsyc;
    }
    
    private MEXMEGrupoCuestionario retResp = null;
    public MEXMEGrupoCuestionario getRespForm(){    	
    	if(retResp == null && this.getRESPIR_ID() > 0){
    		retResp = new MEXMEGrupoCuestionario(Env.getCtx(), this.getRESPIR_ID(), null);
    		retResp.setSingleSysEspVal(this.SINGLESYSESP_Respiratory);
    	}
    	return retResp;
    }
    
    private MEXMEGrupoCuestionario retSkin = null;
    public MEXMEGrupoCuestionario getSkinForm(){    	
    	if(retSkin == null && this.getSKIN_ID() > 0){
    		retSkin = new MEXMEGrupoCuestionario(Env.getCtx(), this.getSKIN_ID(), null);
    		retSkin.setSingleSysEspVal(this.SINGLESYSESP_Skin);
    	}
    	return retSkin;
    }
    
    private MEXMEGrupoCuestionario retMulty = null;
    public MEXMEGrupoCuestionario getMultySys(){    	
    	if(retMulty == null && this.getMULTI_ID() > 0){
    		retMulty = new MEXMEGrupoCuestionario(Env.getCtx(), this.getMULTI_ID(), null);
    		retMulty.setMultiSysVal("Y");
    	}
    	return retMulty;
    }
    
    
    public List<MEXMEGrupoCuestionario> getLstSpecialtiesSingleSysOV() {
		final List<MEXMEGrupoCuestionario> lstSingleSysEsp = new ArrayList<MEXMEGrupoCuestionario>();
		
		try {
			if(getBreastForm() != null){ lstSingleSysEsp.add( getBreastForm());}
			if(getCardioForm() != null){ lstSingleSysEsp.add( getCardioForm());}
			if(getENMTForm() != null){ lstSingleSysEsp.add( getENMTForm());}
			if(getEyesForm() != null){ lstSingleSysEsp.add( getEyesForm());}
			if(getGeniForm() != null){ lstSingleSysEsp.add( getGeniForm());}
			if(getHemaForm() != null){ lstSingleSysEsp.add( getHemaForm());}
			if(getMuscForm() != null){ lstSingleSysEsp.add( getMuscForm());}
			if(getNeurForm() != null){ lstSingleSysEsp.add( getNeurForm());}
			if(getPsycForm() != null){ lstSingleSysEsp.add( getPsycForm());}
			if(getRespForm() != null){ lstSingleSysEsp.add( getRespForm());}
			if(getSkinForm() != null){ lstSingleSysEsp.add( getSkinForm());}
		} catch (Exception ex) {
			s_log.log(Level.SEVERE, "Error: " + ex.getMessage(), ex);
		}
		
		return lstSingleSysEsp;
	}
    
    public static MEXMEGrupoCuestionario getGrupo(List<MEXMEGrupoCuestionario> lista, int value){    	
    	for(MEXMEGrupoCuestionario aux : lista){
    		if(aux.getEXME_GrupoCuestionario_ID() == value){
    			return aux;
    		}    		
    	}    	
    	return null;
    }
    
    /**
	 * Validaciones para antes del guardado
	 * 
	 * @param newRecord
	 * @return
	 */
	@Override
	protected boolean beforeSave(boolean newRecord) {
		// Valida que solo se tenga un tipo de multi system		  
		if (newRecord && MEXMEConfigOV.find(getCtx(), this.getEXME_Medico_ID(), null) != null) {
			log.saveError(null, Utilerias.getAppMsg(Env.getCtx(), "msj.groupForms.SingleSys"));
			return false;
		}else if (!newRecord && this.getEXME_Medico_ID() != (Integer)this.get_ValueOld(this.COLUMNNAME_EXME_Medico_ID)
				&& MEXMEConfigOV.find(getCtx(), this.getEXME_Medico_ID(), null) != null){
			log.saveError(null, Utilerias.getAppMsg(Env.getCtx(), "msj.groupForms.SingleSys"));
			return false;
		}
				
		ArrayList<Integer> ids = new ArrayList<Integer>();
		 ids.add(0, this.getROS_ID());
		 ids.add(1, this.getHPI_ID());
		 ids.add(2, this.getMFSH_ID());
		 ids.add(3, this.getAssessment_ID());
		 ids.add(4, this.getBREAST_ID());
		 ids.add(5, this.getCARDIO_ID());
		 ids.add(6, this.getENMT_ID());
		 ids.add(7, this.getEYES_ID());
		 ids.add(8, this.getGENIT_ID());
		 ids.add(9, this.getHEMATO_ID());
		 ids.add(10, this.getMUSCUL_ID());
		 ids.add(11, this.getNEUROL_ID());
		 ids.add(12, this.getPSYCHI_ID());
		 ids.add(13, this.getRESPIR_ID());
		 ids.add(14, this.getSKIN_ID());
		 ids.add(15, this.getMULTI_ID());
		 
		 TreeSet<Integer> numbers = new TreeSet<Integer>();
		 int count = 0;
		 for(Integer i : ids){
			 if(i > 0){
				 numbers.add(i);
				 count++;
			 }			 
		 }
		
		if(count != numbers.size() || MEXMEConfigOV.findRepetitions(getCtx(), ids, null)){
			log.saveError(null, Utilerias.getAppMsg(Env.getCtx(), "msj.groupForms.org.MultiSys"));
			return false;
		}

		return super.beforeSave(newRecord);
	}


}
