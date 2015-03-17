package org.compiere.model.accessdb;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMETratamientosDetalle;
import org.compiere.model.Query;
import org.compiere.model.bpm.QueryTreatments;
import org.compiere.util.CLogger;

public class TestAccessDB {

//	/** Static logger */
//	private static CLogger log = CLogger.getCLogger(TestAccessDB.class);
//	/** consulta */
//	private Query consulta = null;
//	/** det */
//	private String det = null;
//
//	public void xTest(Properties ctx, int EXME_Tratamientos_Detalle_ID,
//			String trxName) {
//		probar(ctx, EXME_Tratamientos_Detalle_ID, trxName);
//
//	}
//
//	public String probar(Properties ctx, int EXME_Tratamientos_Detalle_ID,
//			String trxName) {
//
//		MEXMETratamientosDetalle td = new MEXMETratamientosDetalle(ctx,
//				EXME_Tratamientos_Detalle_ID, trxName);
//
//		QueryTreatments qt = new QueryTreatments(ctx, true);
//		qt.init();
//
//		AccessDB obj = new AccessDB(ctx, EXME_Tratamientos_Detalle_ID, td, qt,
//				true, 0);
//
//		consulta = obj.buildSql();
//		det = obj.getSql();
//		obj.executeQuery();
//
//		log.log(Level.INFO, "probar> " + det);
//		log.log(Level.INFO, "consulta > " + consulta);
//
//		String error = null;
//
//		// query solo prueba NO DEFENITIVO
//		/*
//		 * List<TreatmentsStatus> lst = td.getDetalleSesion(ctx, det,
//		 * Arrays.asList(obj.getParams()), null); if(lst!=null) for (int i = 0;
//		 * i < lst.size(); i++) { System.out.println("lst> " +i+">> "+
//		 * lst.get(i).toString()); }
//		 */
//
//		return error;
//	}

}
