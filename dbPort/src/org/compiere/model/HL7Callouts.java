package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Language;
import org.compiere.util.Msg;

public class HL7Callouts extends CalloutEngine {

	public HL7Callouts() {
	}

	public String hasComponents(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {		
		
		if ((Boolean) value) {
			
			
			//TODO recordar donde se llaman para usar COLUMNNAME
			mTab.setValue("AD_Column_ID", null);
			mTab.setValue("AD_Table_ID", null);
			mTab.setValue("DefaultValue", null);			

		}
		
		return "";
	}

	

	/***/
	public String hl7FieldSelected(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value) {

		if (value == null) {
			return "";
		}

		MHL7Field field = new MHL7Field(ctx, (Integer) value, null);

		mTab.setValue(X_HL7_FieldDef.COLUMNNAME_HasComponents, field.isHasComponents());
		mTab.setValue(X_HL7_FieldDef.COLUMNNAME_IsRepeated, field.isRepeated());
		field = null;

		return "";
	}

	/***/
	public String hl7ComponentsSelected(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value) {

		if (value == null) {
			return "";
		}
		
		X_HL7_Component componente = new X_HL7_Component(ctx, (Integer) value, null);
		
		mTab.setValue(X_HL7_ComponentDef.COLUMNNAME_HasSubcomponents ,componente.isHasSubcomponents());
		componente = null;
		
		return null;
	}
	
	
	public String isGenerateMessage(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value){
		
		if ((Boolean)value){
			
			String sql = "SELECT count(*) FROM AD_COLUMN WHERE AD_COLUMN.AD_TABLE_ID = ? AND AD_COLUMN.COLUMNNAME = 'IsGenerated'";
			
//			HashSet<String> columnNames = new HashSet<String>();			
			
			PreparedStatement stmt = DB.prepareStatement(sql,null);
			ResultSet rs = null;			
			
			try {
				stmt.setInt(1, Integer.parseInt(mTab.getValue(X_AD_Table.COLUMNNAME_AD_Table_ID).toString()));
				
				rs = stmt.executeQuery();
				
				while (rs.next()){
					
					if(rs.getInt(1) == 0 ){
						return Msg.getMsg(Language.getLoginLanguage(), "IsGenerateMessage");						
					}
					
				}
								
				
			} catch (SQLException e) {
				log.severe(e.getMessage());

			}
			
		}
		
		return "";
	}

}
