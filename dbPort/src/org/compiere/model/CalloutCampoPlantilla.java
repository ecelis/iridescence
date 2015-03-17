/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;

/**
 * @author Expert
 * org.compiere.model.CalloutCampoPlantilla.adColumn
 */
public class CalloutCampoPlantilla extends CalloutEngine {

	public String adColumn(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {

		if (isCalloutActive())
			return "";
		
		Integer adColumnID = null;
		if(value instanceof BigDecimal) {
			adColumnID = Integer.valueOf(value.toString());
		}else{
			adColumnID = (Integer)value;
		}
		
		if (adColumnID == null || adColumnID.intValue() == 0)
			return "";
		
		setCalloutActive(true);
		
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        int AD_Table_ID =0;
        boolean val = false;
        try {

        	pstmt = DB.prepareStatement(
        			" SELECT * FROM AD_Column WHERE AD_Table_ID = ? AND AD_Column_ID = ? ", null);

        	Integer Table = (Integer)mTab.getValue("AD_Table_ID");
        	if (Table != null)
        		AD_Table_ID = Table.intValue();

        	pstmt.setInt(1, AD_Table_ID);
        	pstmt.setInt(2, adColumnID);
        	rs = pstmt.executeQuery();
        	if(rs.next()){
        		val = true;
        	}
        }
        catch (SQLException e)
        {
        	log.log(Level.SEVERE, " SELECT * FROM AD_Column WHERE AD_Table_ID = ? ", e);
        	setCalloutActive(false);
        	return e.getLocalizedMessage();
        }

        if (!val)
			mTab.setValue("AD_Column_ID", 0);
			
		setCalloutActive(false);
		return "";
	}

}
