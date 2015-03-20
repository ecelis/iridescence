package org.compiere.model;

import java.util.Properties;

/** @deprecated revisar funcionalidad! */
public class MEXMEIndicacionesMed extends X_EXME_IndicacionesMed{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8840357404596315212L;


	public MEXMEIndicacionesMed(Properties ctx, int EXME_IndicacionesMed_ID,String trxName) {
		super(ctx, EXME_IndicacionesMed_ID, trxName);
	}
	
//	private static CLogger s_log = CLogger.getCLogger(MEXMEIndicacionesMed.class);
	
	
//	private List<MEXMECuidadosPac> lstCuidadosPac = new ArrayList<MEXMECuidadosPac>();
//	private List<MEXMEEgresoPresc> lstEgresos = new ArrayList<MEXMEEgresoPresc>();
//	private List<MEXMEEsqInsulina> lstEsqInsulinas = new ArrayList<MEXMEEsqInsulina>();
//	private List<MEXMEIngresoPresc> lstIngresos = new ArrayList<MEXMEIngresoPresc>();
	

//	public static int insertFile(Properties ctx, File archivo, int ctaPacID, String trxName) throws Exception {
//
//		MEXMEIndicacionesMed reporte = new MEXMEIndicacionesMed(ctx, 0, trxName);
//		reporte.setName(archivo.getName());
//		reporte.setEXME_CtaPac_ID(ctaPacID);
//		if (!reporte.save(trxName)) {
//			throw new Exception("error.guardarReporte");
//		}
//		MAttachment att = new MAttachment(ctx, 0, trxName);
//		att.setAD_Table_ID(MEXMEIndicacionesMed.Table_ID);
//		att.setRecord_ID(reporte.getEXME_IndicacionesMed_ID());
//		att.setTitle(archivo.getName());
//		att.addEntry(archivo);
//		if (!att.save(trxName)) {
//			throw new Exception("error.guardarReporte");
//		}
//
//		return reporte.getEXME_IndicacionesMed_ID();
//	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
//	public static MEXMEIndicacionesMed get(Properties ctx, int ctaPacID, String trxName) {
//		MEXMEIndicacionesMed retValue = null;
//		final List<Object> params = new ArrayList<Object>();
//		final StringBuilder sql = new StringBuilder();
//		try {
//			sql.append(" EXME_IndicacionesMed.EXME_CtaPac_ID=? ");
//			params.add(ctaPacID);
//			retValue = new Query(ctx, Table_Name, sql.toString(), trxName)//
//					.setParameters(params)//
//					.addAccessLevelSQL(true)//
//					.setOnlyActiveRecords(true)//
//					.setOrderBy("EXME_IndicacionesMed.Created Desc")//
//					.first();
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
//		}
//		return retValue;
//	}
	/**
	 * 
	 * @param ctx
	 * @param ctaPacID
	 * @param diarioEnfID
	 * @param trxName
	 * @return
	 */
//	public static List<MEXMEIndicacionesMed> getList(Properties ctx, int ctaPacID, String trxName) {
//		List<MEXMEIndicacionesMed> lista = new ArrayList<MEXMEIndicacionesMed>();
//		final List<Object> params = new ArrayList<Object>();
//		final StringBuilder sql = new StringBuilder();
//		try {
//			sql.append(" EXME_IndicacionesMed.EXME_CtaPac_ID=? ");
//			params.add(ctaPacID);
//			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
//					.setParameters(params)//
//					.addAccessLevelSQL(true)//
//					.setOnlyActiveRecords(true)//
//					.setOrderBy("EXME_IndicacionesMed.Created Desc")//
//					.list();
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
//		}
//
//		return lista;
//	}
    
//    public List<MEXMECuidadosPac> getLstCuidadosPac() {
//		return lstCuidadosPac;
//	}
    
//    public List<MEXMEEgresoPresc> getLstEgresos() {
//		return lstEgresos;
//	}
    
//    public List<MEXMEIngresoPresc> getLstIngresos() {
//		return lstIngresos;
//	}
    
//    public List<MEXMEEsqInsulina> getLstEsqInsulinas() {
//		return lstEsqInsulinas;
//	}
    
    /**
	 * Llena la vista de indicaciones medicas
	 * SE MUEVEN METODOS A MEXMECtaPac
	 */
//	public void fill() {
//		if (getEXME_CtaPac_ID() > 0) {
////			final MEXMEConfigEnf conf = MEXMEConfigEnf.get(getCtx(), null);
////			int famSoluciones = 0;
////			if (conf != null && conf.getFam_Soluciones_ID() > 0) {
////				famSoluciones = conf.getFam_Soluciones_ID();
////			}
//			// 10.3 Ingresos
////			lstIngresos = MEXMEIngresoPresc.get(getCtx(), null, getEXME_CtaPac_ID(), famSoluciones, true, null);
//			// 10.4 Egresos
////			lstEgresos = MEXMEEgresoPresc.get(getCtx(), null, getEXME_CtaPac_ID(), 0, 0, null);
//			// 10.6 Cuidados del paciente
//			lstCuidadosPac = MEXMECuidadosPac.get(getCtx(), getEXME_CtaPac_ID(), false, 0, true, null);
//			// 10.7 Control diabetico
//			lstEsqInsulinas = MEXMEEsqInsulina.get(getCtx(), getEXME_CtaPac_ID(), null);
//		}
//	}

}
