package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * <b>Fecha:</b> 30/Septiembre/2009<p>
 * <b>Modificado: </b> $Author: Arturo Aranda XPT $<p>
 * <b>En :</b> $Date: 2009/09/30 23:06:56 $<p>
 *
 * @author Arturo Aranda XPT
 * @version $Revision: 1.5 $
 * @deprecated Sera removida, no se utiliza en ecs
 */
public class MEXMEConfigDieta extends X_EXME_ConfigDieta{
	
	private static final long serialVersionUID = 1L;
//	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigDieta.class);

//	public MEXMEConfigDieta(Properties ctx, int id, String trxName) {
//		super(ctx, id, trxName);
//	}

	public MEXMEConfigDieta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	
	/**
	 * Regresa las configuraciones disponibles de dietas
	 * @param ctx                Propiedades
	 * @param trxName            Nombre de la transaccion
	 * @deprecated
	 *
    public static List<MConfigDieta_VO> getConfiguraciones(Properties ctx, String trxName)
    {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<MConfigDieta_VO> lista = new ArrayList<MConfigDieta_VO>();
		
		try{
			
			 sql.append("SELECT dieta.EXME_ConfigDieta_ID as configID, det.EXME_ConfigDieta_Det_ID as detalleID, dieta.Tipo as tipo,"); 
			 sql.append(" dieta.Name AS confNom, det.Name as detNombre ");
			 sql.append(" FROM EXME_ConfigDieta dieta");
			 sql.append(" INNER JOIN EXME_ConfigDieta_Det det ON det.EXME_ConfigDieta_ID = dieta.EXME_ConfigDieta_ID AND det.IsActive = 'Y'");
			 sql.append(" WHERE dieta.IsActive = 'Y'");
			 sql.append(" ORDER BY dieta.Tipo, confNom, detNombre");
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			int idTmp = 0;
			MConfigDieta_VO obj = new MConfigDieta_VO();
			LabelValueBean bean0 = new LabelValueBean(" ","-1");
			obj.getValoresLst().add(bean0);
			while (rs.next()){
				if(idTmp == rs.getInt("configID")){
					LabelValueBean bean = new LabelValueBean(rs.getString("detNombre"),String.valueOf(rs.getInt("detalleID")));
					obj.getValoresLst().add(bean);
				}else{
					if(idTmp != 0){
						lista.add(obj);
						obj = new MConfigDieta_VO();
						bean0 = new LabelValueBean(" ","-1");
						obj.getValoresLst().add(bean0);
					}
					LabelValueBean bean = new LabelValueBean(rs.getString("detNombre"),String.valueOf(rs.getInt("detalleID")));
					obj.setConfigID(rs.getInt("configID"));
					obj.setTipo(rs.getString("tipo"));
					obj.setNombreConfig(rs.getString("confNom"));

					obj.getValoresLst().add(bean);
				}
				idTmp = obj.getConfigID();
			}
			if(obj!=null){
				lista.add(obj);
			}
			
			
		}
		catch(Exception e){
    		s_log.log(Level.SEVERE, sql.toString(), e);
    		
		}finally{
			DB.close(rs, pstmt);
    		sql = null;
    		rs = null;
    		pstmt = null;
		}

		return lista;
    }*/
    
//    private static List<MConfigDieta_VO> lstConfigDietaAll;
//    private static Set<String[]> dietas = new HashSet<String[]>();
//    private static int sec=1;
//    
//	public List<MConfigDieta_VO> getLstConfigDietaAll() {
//		return lstConfigDietaAll;
//	}
//
//	public static void setLstConfigDietaAll(List<MConfigDieta_VO> lstConfigDietaAll) {
//		MEXMEConfigDieta.lstConfigDietaAll = lstConfigDietaAll;
//	}
	
    /**
	 * Devuelve unicamente las configuraciones de dieta
	 * 
	 *
    public static List<LabelValueBean> getConfigDiet(){
    	List<LabelValueBean> list = new ArrayList<LabelValueBean>();
    	list.add(new LabelValueBean(" ","-1"));
    	for (Iterator<MConfigDieta_VO> iter = lstConfigDietaAll.iterator(); iter.hasNext();) {
			MConfigDieta_VO configDieta_VO = (MConfigDieta_VO) iter.next();
			LabelValueBean bean = new LabelValueBean(configDieta_VO.getNombreConfig(),
					String.valueOf(configDieta_VO.getConfigID()));
			list.add(bean);
    	}
    	return list;
    }*/
    
    /**
	 * Devuelve el detalle de configuraciones para una determinada configuracion de dieta
	 * @param configDietID
	 * 
	 *
    public static List<LabelValueBean> getConfigDietDet(String configDietID){
    	List<LabelValueBean> list = new ArrayList<LabelValueBean>();
    	for (Iterator<MConfigDieta_VO> iter = lstConfigDietaAll.iterator(); iter.hasNext();) {
			MConfigDieta_VO configDieta_VO = (MConfigDieta_VO) iter.next();
			if (configDieta_VO.getConfigID()==Integer.valueOf(configDietID))
				list = configDieta_VO.getValoresLst();
		}
    	return list;
    }*/
    
//    public static void setConfigDietas(String[] dieta){
//    	boolean exist = false;
//    	Object[] o = dietas.toArray();
//    	for (int i = 0; i < o.length; i++) {
//			Object obj = o[i];
//			if ( dieta[1].equals("-1") ){
//    			dietas.remove((String[]) obj);
//			}
//    		if ((dieta[1].equals(((String[]) obj)[1]) && dieta[2].equals("-1") )){
//    			dietas.remove((String[]) obj);
//			}
//    		if ((dieta[1].equals(((String[]) obj)[1]) && dieta[2].equals(((String[]) obj)[2]))
//    				&& !dieta[1].equals("-1") && !dieta[2].equals("-1")
//    				|| ((String[]) obj)[1].equals("-1") && !dieta[1].equals("-1") && !dieta[2].equals("-1")
//    				||(dieta[1].equals(((String[]) obj)[1]) && ((String[]) obj)[2].equals("-1") && !dieta[1].equals("-1") && !dieta[2].equals("-1"))
//    		){
//				exist = true;
//				break;
//			}
//    		
//		}
//    	if (!exist){
//    		dieta[0]= String.valueOf(sec++);
//        	dietas.add(dieta);
//    	}
//    }
    
//    public static Set<String[]> getDietas() {
//		return dietas;
//	}

//	public static void setDietas(Set<String[]> dietas) {
//		MEXMEConfigDieta.dietas = dietas;
//	}

//	public static void setListConfigDietas(List<Object[]> dietList){
//    	
//    	for (int i = 0; i < dietList.size(); i++) {
//			dietas.add((String[])dietList.get(i));
//		}
//    	
//    }
    
//    public static List<Object[]> getAllConfigDietas(){
//    	
//    	Object[] o = dietas.toArray();
//    	int n = sec-1;
//    	List<Object[]> l = new ArrayList<Object[]>(n) ;
//    	
//    	for (int i = 0; i < o.length; i++) {
//			Object obj = o[i];
//			if(Integer.valueOf(((String[]) obj)[0])==n){
//    			l.add((Object[]) obj);
//    			n--;
//    			i=-1;
//    			continue;
//    		}
//			if(i==o.length-1 && n>=0){
//				n--;
//				i=-1;
//			}
//		}
//    	
//    	return l;
//    }
    
//    public static void deleteAllConfigDieta(){
//    	dietas.removeAll(dietas);
//    	sec=1;
//    }
    
//    public static void deleteConfigDieta(String[] dieta) {
//    	Object[] o = dietas.toArray();
//    	for (Object obj : o) {
//			if (dieta[0].equals(((String[]) obj)[0]) && dietas.contains((String[]) obj) ){
//				dietas.remove((String[]) obj);
//			}
//    	}
//    }
    
}
