package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

/**
 * <b>Prop&oacute;sito:</b> Modelo de negocios para la tabla de Extensiones.
 * <p>
 * <b>Creado: </b> 24/11/2006
 * <p>
 * <b>Por: </b> Hector Perez
 * <p>
 * <b>Modificado: </b> $Date: 2006/11/24 10:12:47 $
 * <p>
 * <b>Por: </b> $Author: HPerez $
 * <p>
 * 
 * @author HPerez
 * @version $Revision: 1.0 $
 */
//FIXME: name must  be M+TableName--> MEXMEExtension
public class MExtension extends X_EXME_Extension {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MExtension.class);

	/** la suma de descuentos aplicados en el periodo para una variable * */
//	private double descto;

	/**
	 * @param ctx
	 * @param EXME_Extension_ID
	 * @param trxName
	 */
	public MExtension(Properties ctx, int EXME_Extension_ID, String trxName) {
		super(ctx, EXME_Extension_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MExtension(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get MExtension
	 * 
	 * @param ctx
	 *            context
	 * @param whereClause
	 * @return MProduct
	 */
	
	public static MExtension[] get(Properties ctx, String whereClause,
			String trxName) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append("SELECT * FROM EXME_Extension");
		if (whereClause != null && whereClause.length() > 0)
		     sql.append(whereClause);
		     sql.append("MRole.getDefault(ctx, false).addAccessSQL(sql, EXME_Extension ,MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_Extension"));

		

		ArrayList<MExtension> list = new ArrayList<MExtension>();
		PreparedStatement pstmt = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MExtension(ctx, rs, trxName));
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		MExtension[] retValue = new MExtension[list.size()];
		list.toArray(retValue);
		return retValue;
	} // get

	/**
	 * Import Constructor
	 * 
	 * @param imp
	 *            import
	 */
	public MExtension(X_EXME_Extension imp) {
		this(imp.getCtx(), imp.getEXME_Extension_ID(), imp.get_TrxName());
		setClientOrg(imp);
		setUpdatedBy(imp.getUpdatedBy());
		setAD_Org_ID(imp.getAD_Org_ID());

	}

	/**
	 * @param AD_Org_ID
	 */
	public void setAD_Org_ID_X(int AD_Org_ID) {
		setAD_Org_ID(AD_Org_ID);
	}

}
