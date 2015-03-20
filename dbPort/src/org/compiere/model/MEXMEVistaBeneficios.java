package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEVistaBeneficios extends X_EXME_VistaBeneficios {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4158374347031684606L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEVistaBeneficios.class);

	public MEXMEVistaBeneficios(Properties ctx, int EXME_VistaBeneficios_ID, String trxName) {
		super(ctx, EXME_VistaBeneficios_ID, trxName);
	}
	
	public MEXMEVistaBeneficios(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static MEXMEVistaBeneficios[] gets(Properties ctx, int EXME_BeneficiosH_ID) {
		ArrayList<MEXMEVistaBeneficios> list = new ArrayList<MEXMEVistaBeneficios>();
		
		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT * FROM EXME_VISTABENEFICIOS WHERE IsActive = 'Y' AND EXME_BENEFICIOSH_ID = ? AND (ISDATA_IN = 'Y' OR ISDATA_OUT = 'Y' OR ISDATA = 'Y' )");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_BeneficiosH_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEVistaBeneficios reg = new MEXMEVistaBeneficios(ctx, rs, null);
				list.add(reg);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "gets: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		MEXMEVistaBeneficios[] regs = new MEXMEVistaBeneficios[list.size()];
		list.toArray(regs);
		
		return regs;
	}
	
	public static List<MEXMEVistaBeneficios> get(Properties ctx, int EXME_Paciente_ID) {
		List<MEXMEVistaBeneficios> list = new ArrayList<MEXMEVistaBeneficios>();

		StringBuffer sql = new StringBuffer(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT * FROM EXME_VISTABENEFICIOS WHERE IsActive = 'Y' AND EXME_Paciente_ID = ? AND (ISDATA_IN = 'Y' OR ISDATA_OUT = 'Y' OR ISDATA = 'Y' )");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEVistaBeneficios(ctx, rs, null));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	
	private int aseguradora = 0;

	public int getAseguradora() {
		return this.aseguradora;
	}

	public void setAseguradora(int aseguradora) {
		this.aseguradora = aseguradora;
	}
	
	private int EXME_Beneficios_ID = 0;

	public int getEXME_Beneficios_ID() {
		return this.EXME_Beneficios_ID;
	}

	public void setEXME_Beneficios_ID(int eXMEBeneficiosID) {
		this.EXME_Beneficios_ID = eXMEBeneficiosID;
	}
}
