package org.compiere.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;

/**
 * Clase de Posteo. Contiene metodos que proven los datos contables
 * de los registros de ciertas tablas.
 * 
 * <p>
 * Fecha: $Date: 2009/31/10 22:50:58 $ <p>
 *
 * @author Expert Victoria
 * @version $Revision: 1.0 $
 */
public class Post {
	
	private static CLogger s_log = CLogger.getCLogger (Post.class);
	
	/**
	 * Metodo que obtiene los valores contables.
	 *
	 * @param ctx Contexto del sistema
	 * 
	 * @param AD_Table_ID Identificador de la tabla
	 * 
	 * @param Record_ID Identificador del registro
	 * 
	 * @AD_Language Lenguaje del contexto
	 *
	 * @return list Lista con el resumen de los valores de la cuenta.
	 */
	public static List<GenericModel> getAccount(Properties ctx, int AD_Table_ID, int Record_ID){
		List<GenericModel> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT (SELECT AD_Org.Name FROM AD_Org WHERE zz.AD_Org_ID=AD_Org.AD_Org_ID) as organizacion,");
		sql.append(" (SELECT C_ElementValue.Value||' - '||C_ElementValue.Name FROM C_ElementValue WHERE zz.Account_ID=C_ElementValue.C_ElementValue_ID) as Cuenta,");
		sql.append(" AmtAcctDr as debito,");
		sql.append(" AmtAcctCr as credito,");
		sql.append(" (SELECT M_Product.Name FROM M_Product WHERE zz.M_Product_ID=M_Product.M_Product_ID) as producto,");
		sql.append(" (SELECT C_BPartner.Name FROM C_BPartner WHERE zz.C_BPartner_ID=C_BPartner.C_BPartner_ID) as socio,");
		sql.append(" DateAcct as FechaContable,");
		sql.append(" (SELECT C_Period.Name FROM C_Period WHERE zz.C_Period_ID=C_Period.C_Period_ID) periodo,");
		sql.append(" (SELECT trl.Name FROM AD_Ref_List INNER JOIN AD_Ref_List_Trl trl  ON (AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID AND trl.AD_Language = ? ) WHERE AD_Ref_List.AD_Reference_ID=(SELECT AD_Reference_ID FROM AD_Reference WHERE IsActive='Y' AND UPPER(Name)='_POSTING TYPE') AND AD_Ref_List.Value=PostingType) as TipoPosteo");
		sql.append(" FROM Fact_Acct zz WHERE zz.C_AcctSchema_ID=(SELECT C_AcctSchema_ID FROM C_AcctSchema where isactive='Y') AND zz.AD_Table_ID = ? AND zz.Record_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","Fact_Acct", "zz"));
		sql.append(" ORDER BY zz.Fact_Acct_ID");
		
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			
			pstmt.setString(1, ctx.getProperty("#AD_Language"));
			pstmt.setInt(2, AD_Table_ID);
			pstmt.setInt(3, Record_ID);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<GenericModel>();
			GenericModel tmp=null;
			while (rs.next()){
				tmp = new GenericModel();
				
				tmp.setOrganizacion(rs.getString(1));
				tmp.setCuenta(rs.getString(2));
				tmp.setDebito(rs.getString(3));
				tmp.setCredito(rs.getString(4));
				tmp.setProducto(rs.getString(5));
				tmp.setSocio(rs.getString(6));
				tmp.setFecha(rs.getDate(7));
				tmp.setPeriodo(rs.getString(8));
				tmp.setTipo(rs.getString(9));
				
				list.add(tmp);
			}
		}catch(SQLException e){
			s_log.log(Level.SEVERE, "getAccount", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				s_log.log(Level.SEVERE, "getAccount", e);
			}
		}
		return list;
	}

}
