package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class MEXMEPreUni extends X_EXME_PreUni implements DocAction {
	private static CLogger		slog = CLogger.getCLogger (MEXMEPreUni.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEPreUni(Properties ctx, int EXME_PREUNI_ID, String trxName) {
		super(ctx, EXME_PREUNI_ID, trxName);

	}

	public MEXMEPreUni(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	String m_processMsg = null;
	Vector<KeyNamePair> targetpartida;

	public Vector<KeyNamePair> getExme_Partida() {

		// if (targetName != null)
		// return targetName;

		// String value;
		/*
		 * if (!isSOTrx()){ value = "Y";
		 * System.out.println("******** DEBUG: isSOTrx = false"); }else{ value =
		 * "N"; System.out.println("******** DEBUG: isSOTrx = true"); }
		 * System.out
		 * .println("************ DEBUG: isSOTrx valor de de value: "+value);
		 */
		String sql = "select p.exme_partida_id,p.name from exme_partida p order by name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetpartida = new Vector<KeyNamePair>();

			targetpartida.add(new KeyNamePair(-1, ""));

			while (rs.next()) {
				targetpartida.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return targetpartida;
	}

	Vector<String> targetPreUni;

	public Vector<String> getPreUni(int preuni) {

		String sql = "select * from exme_preuni where exme_preuni_id=" + preuni;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetPreUni = new Vector<String>();

			while (rs.next()) {
				for (int i = 1; i <= 19; i++)
					targetPreUni.add(rs.getString(i));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return targetPreUni;
	}

	Vector<String> targetPreUniIDs;

	public Vector<String> getPreUniIDs() {

		String sql = "select exme_preuni_id from exme_preuni where isactive='Y' order by exme_preuni_id";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			// pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			targetPreUniIDs = new Vector<String>();

			while (rs.next()) {
				targetPreUniIDs.add(rs.getString(1));
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return targetPreUniIDs;
	}

	int targetSolicitado;

	public void actualiza(Double solicitado, int id) {

		String sql = "update exme_preuni set pre_solicitado=? where exme_preuni_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());

			pstmt.setDouble(1, solicitado);
			pstmt.setInt(2, id);

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}
	}

	String isdist = null;

	public void actualiza2(boolean bol, int id) {

		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		sql1 = "select isdistributed from exme_preuni where exme_preuni_id = ?";
		sql2 = "update exme_preuni set isdistributed='Y' where exme_preuni_id = ?";
		sql3 = "update exme_preuni set isdistributed='N' where exme_preuni_id = ?";

		try {
			pstmt1 = DB.prepareStatement(sql1, get_TrxName());
			pstmt2 = DB.prepareStatement(sql2, get_TrxName());
			pstmt3 = DB.prepareStatement(sql3, get_TrxName());

			pstmt1.setInt(1, id);
			pstmt2.setInt(1, id);

			rs1 = pstmt1.executeQuery();
			if (rs1.next())
				isdist = rs1.getString(1);
			// System.out.println("Esto es lo que vale rs1 =========== "+ isdist
			// );

			if (bol)
				rs2 = pstmt2.executeQuery();
			else
				rs2 = pstmt3.executeQuery();
			if (isdist.equals('Y'))
				;

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs1, pstmt1);
			DB.close(rs2, pstmt2);
			DB.close(pstmt3);
		}
	}

	public void eliminar(int id) {

		String sql = "update exme_preuni set isactive='N' where exme_preuni_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setDouble(1, id);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

	}

	String dist;
	boolean di;

	public boolean distribuido(int id) {

		String sql = "select isdistributed from exme_preuni where exme_preuni_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				dist = rs.getString(1);
			// System.out.println("Esto es lo que vale dist = "+ dist );
			if (dist.equals("Y"))
				di = true;
			// System.out.println("Esto es lo que vale di = "+di);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return di;
	}

	double d = 0.0;
	Vector<Double> targetAcumulado;
	Vector<Double> targetSol;

	public double getAcumulado(int preuni_id) {

		double acum = 0.0;
		String sql = "select pre_solicitado from exme_preunipe where isactive='Y' and exme_preuni_id="
				+ preuni_id;

		String sql2 = "select pre_solicitado from exme_preuni where exme_preuni_id="
				+ preuni_id;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt2 = DB.prepareStatement(sql2, get_TrxName());

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();

			targetAcumulado = new Vector<Double>();
			targetSol = new Vector<Double>();
 	
			while (rs.next()) {
				targetAcumulado.add(rs.getDouble(1));

			}
			while (rs2.next()) {
				targetSol.add(rs2.getDouble(1));
			}

			for (int i = 0; i < targetAcumulado.size(); i++) {
				d = targetAcumulado.get(i);
				acum = acum + d;
			}

			// acum = targetSol.get(0) - acum;
			// System.out.println("Solicitado:"+targetSol.get(0)+
			// " - Acumulado: "+acum);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
			DB.close(rs2, pstmt2);
		}

		return Math.round(acum * Math.pow(10, 2)) / Math.pow(10, 2);
		// return Math.round(nD*Math.pow(10,nDec))/Math.pow(10,nDec);
	}

	public MEXMEPreUni[] getLines(int ad_orgtrx_id) {
		/*
		 * if (m_lines != null) return m_lines;
		 */
		ArrayList<MEXMEPreUni> list = new ArrayList<MEXMEPreUni>();
		String sql = "select * from exme_preuni where isactive='Y' and ad_orgtrx_id=? order by documentno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, ad_orgtrx_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("rs:::::::::::"+rs.getBigDecimal(12));
				list.add(new MEXMEPreUni(getCtx(), rs, get_TrxName()));

			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "getLines", e);
		} finally {
			DB.close(rs, pstmt);
		}
		// System.out.println("Agregado a la lista: "+list);
		MEXMEPreUni[] m_partidas = new MEXMEPreUni[list.size()];
		list.toArray(m_partidas);
		list = null;
		return m_partidas;
	}

	int idPreUni;

	public int getIdPreUni(int id) {

		String sql = "select exme_preuni_id from exme_preuni where documentno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				idPreUni = rs.getInt(1);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return idPreUni;
	}

	String nomPartida;

	public String getNombrePartida(int partida) {

		String sql = "select name from exme_partida where exme_partida_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, partida);
			rs = pstmt.executeQuery();
			if (rs.next())
				nomPartida = rs.getString(1);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return nomPartida;
	}

	Vector<KeyNamePair> nameEstSer;

	public Vector<KeyNamePair> getNameEstServ(int id) {

		String sql = "select o.ad_org_id, o.name from exme_estserv e "
				+ "INNER JOIN ad_org o on e.ad_orgtrx_id= o.ad_org_id "
				+ "where e.exme_estserv_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			nameEstSer = new Vector<KeyNamePair>();
			if (rs.next())
				nameEstSer
						.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return nameEstSer;
	}

	int idEstSer;

	public int getIdEstSer(int id) {

		String sql = "select name from exme_estserv where exme_estserv_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				idEstSer = rs.getInt(1);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return idEstSer;
	}

	String nameOrgTrx = null;
	
	public String getNameOrgTrx(int ad_orgtrx_id) {

		String sql = "select name from ad_org where ad_org_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, ad_orgtrx_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				nameOrgTrx = rs.getString(1);

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return nameOrgTrx;
	}
	
	Vector<String> docResult;

	public boolean getDocExist(String docName) {
		
		boolean ban=false;
		int count=0;
		String sql = "select count(documentno) from exme_preuni where documentno= ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setString(1, docName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count=rs.getInt(1);
				if (count!=0)
					ban=true;
			}
		} catch (SQLException e) {
			slog.log(Level.SEVERE, "Error", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return ban;
	}

	public boolean approveIt() {
		// TODO
		setIsApproved(true);
		return true;
	}

	public boolean closeIt() {
		
		return false;
	}

	public String completeIt() {
		
		return null;
	}

	public File createPDF() {
		
		return null;
	}

	public BigDecimal getApprovalAmt() {
		// TODO aqui deberia de regresar algo como el tope, tipo y asi
		return null;
	}

	public int getC_Currency_ID() {
		
		return 0;
	}

	public int getDoc_User_ID() {
		
		return 0;
	}

	public String getDocumentInfo() {
		
		// MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		// return dt.getName() + " " + getDocumentNo();
		return null;
	}

	public String getProcessMsg() {
		
		return null;
	}

	public String getSummary() {
		
		return null;
	}

	public boolean invalidateIt() {
		
		return true;
	}

	public String prepareIt() {
		
		return null;
	}

	public boolean processIt(String processAction) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());

	}

	public boolean reActivateIt() {
		
		return true;
	}

	public boolean rejectIt() {
		setIsApproved(false);
		return true;
	}

	public boolean reverseAccrualIt() {
		
		return true;
	}

	public boolean reverseCorrectIt() {
		
		return true;
	}

	public boolean unlockIt() {
		
		return true;
	}

	public boolean voidIt() {
		
		return true;
	}

}
