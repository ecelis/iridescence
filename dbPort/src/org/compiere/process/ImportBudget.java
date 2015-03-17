package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MDocType;
import org.compiere.model.MEXME_GL_BudgetPa;
import org.compiere.model.MEXME_GL_BudgetPaPe;
import org.compiere.model.MGL_Budget;
import org.compiere.model.X_I_EXME_Budget;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Proceso de importaci&oacute;n de la tabla de Paciente.<p>
 * Creado: Ene-2010<p>
 * <p>
 * Por: $Author: JGaray $<p>
 * 
 * @author José Garay
 * @version $Revision: 1.2 $
 */
public class ImportBudget extends SvrProcess {
	
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;
	
	/** Name to be imported to	*/
	private String				m_Name = null;
	
	/** Year to be imported to	*/
	private int				m_Ejercicio = 0;
	
	/** Organization to be imported to	*/
	private int				m_C_Activity_ID = 0;
	
	
	/**
     * Constructor por defecto.
     */
    public ImportBudget() {
        super();
    }
    
    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {

    	ProcessInfoParameter[] para = getParameter();

        m_AD_Client_ID = Env.getAD_Client_ID(getCtx());

		for (int i = 0; i < para.length; i++) {

			String name = para[i].getParameterName();

			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("Name"))
				m_Name = para[i].getParameter().toString();
			else if (name.equals("Ejercicio"))
				m_Ejercicio = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_Activity_ID"))
				m_C_Activity_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
    }
    
    /**
     * Corre el proceso.
     * @return Un mensaje de estado
     * @throws Exception
     */
    protected String doIt() throws Exception {

        StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		String DocumentNo=null;
		
		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported){

			sql = new StringBuffer ("DELETE I_EXME_Budget "
				+ "WHERE I_IsImported='Y'").append(clientCheck);

			no = DB.executeUpdate(sql.toString(), null);

			log.info("Delete Old Impored =" + no);
		}



    

		//	Set Client, Org, IaActive, Created/Updated,

		sql = new StringBuffer ("UPDATE I_EXME_Budget "
			+ "SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),"
			+ " AD_Org_ID = COALESCE (AD_Org_ID, ").append(m_AD_Org_ID).append("),"
			+ " IsActive = COALESCE (IsActive, 'Y'),"
			+ " Created = COALESCE (Created, SysDate),"
			+ " CreatedBy = COALESCE (CreatedBy, 0),"
			+ " Updated = COALESCE (Updated, SysDate),"
			+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
			+ " I_ErrorMsg = NULL,"
			+ " I_IsImported = 'N' "
			+ "WHERE (I_IsImported<>'Y' OR I_IsImported IS NULL)");

		no = DB.executeUpdate(sql.toString(), null);

		log.info("Reset=" + no);
		
		//Go through Records

		
		log.fine("start inserting/updating ...");

		sql = new StringBuffer ("SELECT I_EXME_Budget_ID "
			+ "FROM I_EXME_Budget WHERE I_IsImported='N'").append(clientCheck).append(" order by I_EXME_Budget_ID  asc");
		
			PreparedStatement pstmt = null;
			ResultSet rs = null; 
		try{
			pstmt = DB.prepareStatement(sql.toString(), null);

			rs = pstmt.executeQuery();
		
			int I_EXME_Budget_ID = 0;
			int I_EXME_Partida_ID = 0;
			int I_AD_Org_ID = 0;
			int I_Periodo = 0;
			BigDecimal I_Autorizado = null;
			List<MEXME_GL_BudgetPa> partidas = null;
			List<MEXME_GL_BudgetPaPe> periodos = null;
		
			while (rs.next())
			{
				try{
					I_EXME_Budget_ID=rs.getInt(1);
					log.fine("I_EXME_Budget_ID=" + I_EXME_Budget_ID);
			
					X_I_EXME_Budget i_budget=new X_I_EXME_Budget(getCtx(), I_EXME_Budget_ID, null);
			
					I_EXME_Partida_ID=getPartida(i_budget.getPartida().trim());
			
					if(!i_budget.getOrganizacion().trim().equals("0"))
						I_AD_Org_ID=getOrg(i_budget.getOrganizacion().trim());
					else
						I_AD_Org_ID=0;
			
					I_Periodo=Integer.parseInt(i_budget.getPeriodo().trim());
			
					I_Autorizado=new BigDecimal(i_budget.getPre_Autorizado().trim());
			
					if(partidas==null)
						partidas=new ArrayList<MEXME_GL_BudgetPa>();
			
					boolean existPair=false;
					int pair_index=0;
					int part_ref=0;
					for(int i=0; i<partidas.size();i++){
						if(partidas.get(i).getEXME_Partida_ID()==I_EXME_Partida_ID && partidas.get(i).getAD_OrgTrx_ID()==I_AD_Org_ID){
							existPair=true;
							pair_index=i;
							part_ref=partidas.get(i).getEXME_GL_BudgetPa_ID();
							i=partidas.size();
						}
					}
			
					if(existPair){
						if(periodos==null)
							periodos=new ArrayList<MEXME_GL_BudgetPaPe>();
						
						boolean existPeriod=false;
						for(int i=0;i<periodos.size();i++){
							if(periodos.get(i).getEXME_GL_BudgetPa_ID()==part_ref && periodos.get(i).getPeriodo()==I_Periodo){
								existPeriod=true;
								i=periodos.size();
							}
						}
					
						if(existPeriod){
							//existe un mes igual para esta partida.
							setErrorImport("Error: Equals Period", I_EXME_Budget_ID);
						}else if(I_Periodo==0){
							setErrorImport("Error: Period 0 or null", I_EXME_Budget_ID);
						}else{
							BigDecimal Partida_Aut=partidas.get(pair_index).getPre_Autorizado();
							partidas.get(pair_index).setPre_Autorizado(Partida_Aut.add(I_Autorizado));
						
							MEXME_GL_BudgetPaPe periodo=new MEXME_GL_BudgetPaPe(getCtx(),0,null);
						
							periodo.setPeriodo(I_Periodo);
						
							periodo.setPre_Autorizado(I_Autorizado);
							periodo.setEXME_GL_BudgetPa_ID(I_EXME_Budget_ID);
						
							periodos.add(periodo);
							//Ok
						}
					}else{
						MEXME_GL_BudgetPa partida=new MEXME_GL_BudgetPa(getCtx(),0,null);
				
						partida.setEXME_GL_BudgetPa_ID(I_EXME_Budget_ID);
				
						partida.setEXME_Partida_ID(I_EXME_Partida_ID);
						partida.setAD_OrgTrx_ID(I_AD_Org_ID);
						partida.setPre_Autorizado(I_Autorizado);
					
						partidas.add(partida);
				
						if(I_Periodo!=0){
							if(periodos==null)
								periodos=new ArrayList<MEXME_GL_BudgetPaPe>();
												
							MEXME_GL_BudgetPaPe periodo=new MEXME_GL_BudgetPaPe(getCtx(),0,null);
						
							periodo.setPeriodo(I_Periodo);
						
							periodo.setPre_Autorizado(I_Autorizado);
							periodo.setEXME_GL_BudgetPa_ID(I_EXME_Budget_ID);
						
							periodos.add(periodo);
							//Ok
						}
					}
				}
				catch(SQLException e){
					setErrorImport(e.toString(), I_EXME_Budget_ID);
					log.log(Level.SEVERE, "doIt - Reading", e);
					e.printStackTrace();
				}
			}
			
			//Obtener total de Presupuesto
			BigDecimal Total=new BigDecimal(0);
			if(partidas!=null){
				for(int i=0; i<partidas.size(); i++){
					BigDecimal res=Total;
					Total=res.add(partidas.get(i).getPre_Autorizado());
				}
			}
			
			String trxName = get_TrxName();
			Trx m_trx= Trx.get(trxName, true);
			
			//guardar encabezado
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			
			MGL_Budget mgb = new MGL_Budget(getCtx(), 0, trxName);

			mgb.setAD_Org_ID(m_AD_Org_ID);
			mgb.setName(m_Name);
			mgb.setFechaFin(new Timestamp(sdf.parse("31/12/" + m_Ejercicio).getTime()));
			mgb.setFechaIni(new Timestamp(sdf.parse("01/01/" + m_Ejercicio).getTime()));
			mgb.setEjercicio(m_Ejercicio);
			mgb.setTipoPresup("R");
			mgb.setIsPrimary(true);
			mgb.setC_Activity_ID(m_C_Activity_ID);
			mgb.setPre_Autorizado(Total);
			mgb.setBudgetStatus("C");
			mgb.setAmplia_Reduce("O");
			mgb.setIsTransfered(true);
			mgb.setIsApproved(true);
			mgb.setPre_Solicitado(new BigDecimal(0));
			mgb.setPre_Comprometido(new BigDecimal(0));
			mgb.setPre_Devengado(new BigDecimal(0));
			mgb.setPre_Ejercido(new BigDecimal(0));
			mgb.setC_DocType_ID(0);
			MDocType mdt=new MDocType(getCtx(),0,null);
			if(mdt.getDocTypeTarget("('GLD')")!=null)
				mgb.setC_DocTypeTarget_ID(Integer.parseInt(mdt.getDocTypeTarget("('GLD')").get(0).getId()));
			mgb.setIsSOTrx(false);
			mgb.setIsPrinted(true);
			
			if(!mgb.save(trxName)){
				if (m_trx != null) {
					m_trx.rollback();
					m_trx.close();
					m_trx = null;
				}
				return "@Error: Inserting@";
			}
			int gl_budget_id = mgb.getGL_Budget_ID();
			DocumentNo=mgb.getDocumentNo();
			
			if(partidas!=null){
				for(int i=0; i<partidas.size(); i++){
					//guardar partida
					MEXME_GL_BudgetPa bpa = new MEXME_GL_BudgetPa(getCtx(), 0, trxName);

					bpa.setAD_Org_ID(m_AD_Org_ID);
					bpa.setGL_Budget_ID(gl_budget_id);
					bpa.setPre_Autorizado(partidas.get(i).getPre_Autorizado());
					bpa.setIsDistributed(false);
					bpa.setEXME_Partida_ID(partidas.get(i).getEXME_Partida_ID());
					bpa.setAD_OrgTrx_ID(partidas.get(i).getAD_OrgTrx_ID());
					bpa.setPre_Solicitado(new BigDecimal("0"));
					bpa.setPre_Comprometido(new BigDecimal("0"));
					bpa.setPre_Devengado(new BigDecimal("0"));
					bpa.setPre_Ejercido(new BigDecimal("0"));
				
					if(!bpa.save(trxName)){
						if (m_trx != null) {
							m_trx.rollback();
							m_trx.close();
							m_trx = null;
						}
						return "@Error: Inserting@";
					}
					int gl_budgetpa_id = bpa.getEXME_GL_BudgetPa_ID();
				
					if(periodos!=null){
						for(int j=0; j<periodos.size(); j++){
							//guardar meses
							if(periodos.get(j).getEXME_GL_BudgetPa_ID()==partidas.get(i).getEXME_GL_BudgetPa_ID()){
								MEXME_GL_BudgetPaPe bdet = new MEXME_GL_BudgetPaPe(getCtx(), 0, trxName);

								bdet.setAD_Org_ID(m_AD_Org_ID);
								bdet.setEXME_GL_BudgetPa_ID(gl_budgetpa_id);
								bdet.setPeriodo(periodos.get(i).getPeriodo());
								bdet.setPre_Autorizado(periodos.get(i).getPre_Autorizado());
								bdet.setPre_Solicitado(new BigDecimal("0"));
								bdet.setPre_Comprometido(new BigDecimal("0"));
								bdet.setPre_Devengado(new BigDecimal("0"));
								bdet.setPre_Ejercido(new BigDecimal("0"));

								if(!bdet.save(trxName)){
									if (m_trx != null) {
										m_trx.rollback();
										m_trx.close();
										m_trx = null;
									}
									return "@Error: Inserting@";
								}
							}
						}
					}
				}
			}
			if (m_trx != null) {
				m_trx.commit();
				m_trx.close();
				m_trx = null;
			}
			
		}catch(SQLException e){
			log.log(Level.SEVERE, "doIt - Inserting", e);
			e.printStackTrace();
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				log.log(Level.SEVERE, "doIt - Inserting", e);
			}
		}
		return "DocumentNo: " + DocumentNo;
    }
    
    /**
	 * Metodo que obtiene el ID de la partida.
	 *
	 * @param Value: Llave de Busqueda que trae la tabla de importacion
	 *
	 * @return EXME_Partida_ID  Retorna el ID de la partida
	 */
	private int getPartida(String value){
		int EXME_Partida_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer ("SELECT EXME_Partida_ID FROM EXME_Partida WHERE IsActive = 'Y' AND Value = ?");
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next())
				EXME_Partida_ID=rs.getInt(1);
		}catch(SQLException e){
			EXME_Partida_ID = 0;
			log.log(Level.SEVERE, "getPartida", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				log.log(Level.SEVERE, "getPartida", e);
			}
		}
		return EXME_Partida_ID;
	}
	
	/**
	 * Metodo que obtiene el ID de la Organizacion.
	 *
	 * @param Value: Llave de Busqueda que trae la tabla de importacion
	 *
	 * @return EXME_Partida_ID  Retorna el ID de la Organizacion
	 */
	private int getOrg(String value){
		int AD_Org_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer ("SELECT AD_Org_ID FROM AD_Org WHERE IsActive = 'Y' AND Value = ?");
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next())
				AD_Org_ID=rs.getInt(1);
		}catch(SQLException e){
			AD_Org_ID = 0;
			log.log(Level.SEVERE, "getOrg", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				log.log(Level.SEVERE, "getOrg", e);
			}
		}
		return AD_Org_ID;
	}
	
	/**
	 * Metodo que agrega el error al importar.
	 *
	 * @param Value: Eror ocurrido
	 *
	 * @param I_EXME_Budget_ID: ID del registro
	 * 
	 */
	private void setErrorImport(String value, int I_EXME_Budget_ID){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer ("UPDATE I_EXME_Budget SET I_IsImported='E', I_ErrorMsg= ? WHERE I_EXME_Budget_ID=").append(I_EXME_Budget_ID);
		try{
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, value);
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			log.log(Level.SEVERE, "setErrorImport", e);
		}finally{
			try{
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			}catch (SQLException e){
				log.log(Level.SEVERE, "setErrorImport", e);
			}
		}
	}
}
