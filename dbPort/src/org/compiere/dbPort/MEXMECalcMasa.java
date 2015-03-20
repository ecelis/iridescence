package org.compiere.dbPort;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MEXMESignoVitalDet;
import org.compiere.model.X_EXME_CalcMasa;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class MEXMECalcMasa extends X_EXME_CalcMasa {

	private static final long serialVersionUID = 1L;
	private static CLogger slog = CLogger.getCLogger(MEXMECalcMasa.class);

	public MEXMECalcMasa(Properties ctx, int EXME_CalcMasa_ID, String trxName) {
		super(ctx, EXME_CalcMasa_ID, trxName);
	}

	public MEXMECalcMasa(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**Lama*/
	public static boolean create(final MEXMESignoVitalDet signDet, final String activityLevel) {
		boolean saved;
		if (signDet == null || signDet.getEXME_Paciente_ID() <= 0) {
			slog.severe("MEXMESignoVitalDet and EXME_Paciente_ID are mandatory");
			saved = false;
		} else {
			final MEXMECalcMasa calc = new MEXMECalcMasa(Env.getCtx(), 0, signDet.get_TrxName());
			calc.setEXME_Paciente_ID(signDet.getEXME_Paciente_ID());
			calc.setActivityLevel(activityLevel);
			calc.setFolio(signDet.getFolio());
			saved = calc.save();
		}
		return saved;
	}

}
