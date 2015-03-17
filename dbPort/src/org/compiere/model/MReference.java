package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;
/**
 * Reference model (List / Table / Data) 
 * 
 */
public class MReference extends X_AD_Reference {

	/** serialVersionUID */
	private static final long serialVersionUID = 5322577597492774912L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MReference.class);

	/**
	 * Constructor por id
	 * 
	 * @param ctx
	 * @param AD_Reference_ID
	 * @param trxName
	 */
	public MReference(Properties ctx, int AD_Reference_ID, String trxName) {
		super(ctx, AD_Reference_ID, trxName);
	}

	/**
	 * Constructor por resultset
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MReference(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Referencia tipo lista
	 */
	private List<ValueNamePair> refList = null;

	/**
	 * Obtenemos la etiqueta del valor que se envia
	 * 
	 * @param value
	 * @return
	 * @deprecated use {@link MRefList#get(Properties, int, String, String)}
	 */
	public String getValueList(String value) {
		String traduccion = null;
		if (StringUtils.isNotBlank(value)) {
			for (ValueNamePair pair : getRefList()) {
				if (StringUtils.equalsIgnoreCase(pair.getValue(), value)) {
					traduccion = pair.getName();
					break;
				}
			}
		}
		return traduccion;
	}
	
	public List<ValueNamePair> getRefList() {
		if(refList == null){
			refList = MRefList.getList(getCtx(), getAD_Reference_ID(), false, null);
		}
		return refList;
	}
	
	/**
	 * Returns the Table Validation reference definition if the reference
	 * is for that kind.
	 * @return A Table Validation reference definition.
	 */
	public X_AD_Ref_Table getRefTable() {
		//Lama: se cambio por Query, no se estaban cerrando el resultset ni el prepare statement
		X_AD_Ref_Table retVal = null;
		if (VALIDATIONTYPE_TableValidation.equals(getValidationType())) {
			return new Query(getCtx(), X_AD_Ref_Table.Table_Name, " AD_Reference_ID=? ", null)//
				.setParameters(getAD_Reference_ID())//
				.first();
		}
		return retVal;
	}
	
	public List<MRefList> getList() {
		if (VALIDATIONTYPE_ListValidation.equals(getValidationType())) {
			return MRefList.getList(getCtx(), getAD_Reference_ID());
		}
		return new ArrayList<MRefList>();
	}
	
	public static MReference getRefByName(Properties ctx, String name, String trxName) {
		MReference ref = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM AD_REFERENCE WHERE ISACTIVE = 'Y' AND NAME = ? ");
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ref = new MReference(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getRefByName", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return ref;
	}
}
