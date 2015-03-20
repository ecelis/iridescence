/**
 *
 */
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.X_C_ElementValue;
import org.compiere.model.X_PA_ReportLine;
import org.compiere.model.X_PA_ReportSource;
import org.compiere.report.MReportSource;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * Create lines for the PA Report Line Set
 * @author mrojas
 *
 */
public class CreatePAReportSource extends SvrProcess {

	private int cElementValueId;
	private int paReportLineId;
	private int adClientId;

	private CLogger logger = CLogger.getCLogger(CreatePAReportSource.class);

	@Override
	protected void prepare() {

		adClientId = Env.getAD_Client_ID(Env.getCtx());

		final ProcessInfoParameter[] para = getParameter();
		for (ProcessInfoParameter parameter : para) {

			String paramName = parameter.getParameterName();

			if(paramName == null) {
				continue;
			} else if(X_C_ElementValue.COLUMNNAME_C_ElementValue_ID.equals(paramName)) {
				cElementValueId = parameter.getParameterAsInt();
			} else if(X_PA_ReportLine.COLUMNNAME_PA_ReportLine_ID.equals(paramName)) {
				paReportLineId = parameter.getParameterAsInt();
			}
		}

	}

	@Override
	protected String doIt() throws Exception {
		String retVal = StringUtils.EMPTY;

		// pick only elements that are not already included as report source
		String sql =
				"SELECT C_ElementValue_ID, Value, Name FROM C_ElementValue \n"
						+ "WHERE AD_Client_ID = ? AND ParentElementValue_ID = ? \n"
						+ "	AND C_ElementValue_ID NOT IN (\n"
						+ "		SELECT C_ElementValue_ID FROM PA_ReportSource WHERE PA_ReportLine_ID = ?)\n"
						+ "	AND IsActive = 'Y'";

		String deleteOldSources =
				"DELETE FROM PA_ReportSource WHERE PA_ReportLine_ID = ?";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;

		int recordsInserted = 0;
		int errors = 0;

		try {

			DB.executeUpdate(deleteOldSources, paReportLineId, null);

			pstmt.setInt(1, adClientId);
			pstmt.setInt(2, cElementValueId);
			pstmt.setInt(3, paReportLineId);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				MReportSource reportSource = new MReportSource(getCtx(), 0, null);
				reportSource.setPA_ReportLine_ID(paReportLineId);
				reportSource.setC_ElementValue_ID(rs.getInt("C_ElementValue_ID"));
				reportSource.setDescription(rs.getString("Value") + " - " + rs.getString("Name"));
				reportSource.setElementType(X_PA_ReportSource.ELEMENTTYPE_Account);

				if(reportSource.save()) {
					recordsInserted++;
				} else {
					log.warning(
							"Can't create report source for element "
									+ rs.getString("Value") + " - "
									+ rs.getString("Name")
							);

					errors++;
				}
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
		}

		retVal = "@PA_ReportSource_ID@ @Inserted@ = " + recordsInserted + " - @Errors@ = " + errors;

		return retVal;
	}


}
