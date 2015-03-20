package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;

import org.compiere.model.MPregunta;
import org.compiere.model.MPregunta_Lista;
import org.compiere.model.MTipoPregunta;
import org.compiere.model.X_I_EXME_Pregunta;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * <p><b>Proposito : </b>Importaci#n de cuestionarios. Inserta registros en las 
 * tablas de tipo de pregunta, pregunta, y respuesta.
 * </p>
 * <b>Creado:</b> 3/Jul/2009
 * 
 * @author mrojas
 */
public class ImportQuestionnaires extends SvrProcess {

	/** Cliente al que se va a importar  */
	private int p_AD_Client_ID = 0;
	/** Organizacion a la que se va a importar  */
	private int p_AD_Org_ID = 0;
	/**	Borrar los ya importados	*/
	private boolean			p_DeleteOldImported = false;

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}


	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		log.info("Importing questionnaires ....");

		StringBuffer sql = null;
		int no = 0;

		String clientCheck = " AND AD_Client_ID = " + p_AD_Client_ID;
		
		String trxName = Trx.createTrxName();
		Trx trx = Trx.get(trxName, true);
		
		String msg = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			//Borrar los ya importados
			if (p_DeleteOldImported)
			{
				sql = new StringBuffer ("DELETE I_EXME_Pregunta "
						+ "WHERE I_IsImported='Y'").append (clientCheck);
				no = DB.executeUpdate (sql.toString (), trxName);
				log.fine("Delete Old Impored =" + no);
			}

			//Establecer Cliente, Organizacion, IsActive, Created/Updated
			sql = 
				new StringBuffer ("UPDATE I_EXME_Pregunta "
						+ "SET AD_Client_ID = COALESCE (AD_Client_ID,")
			.append (p_AD_Client_ID).append ("),"
					+ " AD_Org_ID = COALESCE (AD_Org_ID,").append (p_AD_Org_ID).append ("),");

			sql.append(" IsActive = COALESCE (IsActive, 'Y'),"
					+ " Created = COALESCE (Created, SysDate),"
					+ " CreatedBy = COALESCE (CreatedBy, 0),"
					+ " Updated = COALESCE (Updated, SysDate),"
					+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
					+ " I_ErrorMsg = NULL,"
					+ " I_IsImported = 'N' "
					+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
			no = DB.executeUpdate (sql.toString (), trxName);
			log.info ("Reset = " + no);


			//Especialidad
			sql = 
				new StringBuffer ("UPDATE I_EXME_Pregunta i "
						+ "SET EXME_Especialidad_ID = (SELECT EXME_Especialidad_ID "
						+ "  FROM EXME_Especialidad e "
						+ "  WHERE UPPER(i.Especialidad_Value) = UPPER(e.Value) " 
						+ "    AND i.AD_Client_ID = e.AD_Client_ID ");
			
	        if (DB.isOracle()) {
	        	sql.append(" AND ROWNUM=1) ");
	        } else if (DB.isPostgreSQL()) {
	        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
	        	sql.append(") ");
	        }
			
			sql.append("WHERE EXME_Especialidad_ID IS NULL AND Especialidad_Value IS NOT NULL "
						+ " AND I_IsImported<>'Y'").append(clientCheck);
			no = DB.executeUpdate (sql.toString (), trxName);
			log.fine("Set speciality from Value = " + no);

			//Tipo de pregunta
			sql = 
				new StringBuffer ("UPDATE I_EXME_Pregunta i "
						+ "SET EXME_TipoPregunta_ID = (SELECT EXME_TipoPregunta_ID "
						+ "  FROM EXME_TipoPregunta t "
						+ "  WHERE i.TipoPregunta_Value = t.Value " 
						+ "    AND i.AD_Client_ID = t.AD_Client_ID ");
			
	        if (DB.isOracle()) {
	        	sql.append(" AND ROWNUM=1) ");
	        } else if (DB.isPostgreSQL()) {
	        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
	        	sql.append(") ");
	        }
			
			sql.append("WHERE EXME_TipoPregunta_ID IS NULL AND TipoPregunta_Value IS NOT NULL "
						+ " AND I_IsImported <> 'Y'").append(clientCheck);
			no = DB.executeUpdate (sql.toString (), trxName);
			log.fine("Set question type from Value = " + no);


			if(!trx.commit()) {
				trx.rollback();
				msg = Msg.getMsg(getCtx(), "CanNotSaveQuestionnaireData");
			} else {
				
				trx.close();
				
				trxName = Trx.createTrxName();
				
				trx = Trx.get(trxName, true);
				
				//primero, insertamos los tipos de pregunta que aun no existen
				sql = 
					new StringBuffer("SELECT DISTINCT TipoPregunta_Value, ")
					.append("TipoPregunta_Name, TipoPregunta_Description ")
					.append("FROM I_EXME_Pregunta ")
					.append("WHERE I_IsImported = 'N' AND IsActive = 'Y' ")
					.append("AND EXME_TipoPregunta_ID IS NULL ")
					.append(clientCheck);
				
				pstmt = DB.prepareStatement(sql.toString(), trxName);

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MTipoPregunta tp = new MTipoPregunta(getCtx(), 0, trxName);
					tp.setValue(rs.getString("TipoPregunta_Value"));
					tp.setName(rs.getString("TipoPregunta_Name"));
					tp.setDescription(rs.getString("TipoPregunta_Description"));
						
					if(!tp.save(trxName)) {
						log.log(Level.SEVERE, "While saving Question type : " + tp.getValue());
						msg += "\n" + Msg.getMsg(getCtx(), "CanNotSaveQuestionType");
							
						sql = new StringBuffer("UPDATE I_EXME_Pregunta SET I_ErrorMsg = ? WHERE TipoPregunta_Value = ?");
						pstmt = DB.prepareStatement(sql.toString(), null);
						pstmt.setString(1, Msg.getMsg(getCtx(), "CanNotSaveQuestionType"));

						pstmt.executeUpdate();
					}
				}
				
				rs.close();
				pstmt.close();
				
				trx.commit();
				trx.close();
				
				// actualizamos de nuevo con los nuevos tipos de pregunta
				sql = 
					new StringBuffer ("UPDATE I_EXME_Pregunta i "
							+ "SET EXME_TipoPregunta_ID = (SELECT EXME_TipoPregunta_ID "
							+ "  FROM EXME_TipoPregunta t "
							+ "  WHERE i.TipoPregunta_Value = t.Value " 
							+ "    AND i.AD_Client_ID = t.AD_Client_ID AND ");
				
		        if (DB.isOracle()) {
		        	sql.append(" AND ROWNUM=1) ");
		        } else if (DB.isPostgreSQL()) {
		        	sql = new StringBuffer(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
		        	sql.append(") ");
		        }
				
				sql.append("WHERE EXME_TipoPregunta_ID IS NULL AND TipoPregunta_Value IS NOT NULL "
							+ " AND I_IsImported <> 'Y'").append(clientCheck);
				no = DB.executeUpdate (sql.toString (), null);
				log.fine("Set question type from Value = " + no);
				
				
				//ahora vamos a insertar las preguntas
				sql = 
					new StringBuffer("SELECT * FROM I_EXME_Pregunta ")
				.append("WHERE I_IsImported = 'N' ")
				.append("AND IsActive = 'Y' ")
				.append("and pregunta_value not in (select value from exme_pregunta ) ")
				.append(clientCheck);

				pstmt = DB.prepareStatement(sql.toString(), null);

				rs = pstmt.executeQuery();

				//para saber las que ya insertamos
				HashMap<String, Integer>insertadas = new HashMap<String, Integer>();
				
				while(rs.next()) {
					trxName = Trx.createTrxName();
					trx = Trx.get(trxName, true);
					
					X_I_EXME_Pregunta iPregunta = new X_I_EXME_Pregunta(getCtx(), rs, null);;
					MPregunta pregunta = null;
					boolean guardar = true;
					
					// ya se inserto la pregunta?
					if(!insertadas.containsKey(iPregunta.getPregunta_Value())) {
						//copiar de la tabla de interfaz a la tabla final
						pregunta = new MPregunta(getCtx(), iPregunta, trxName);
						
						if(!pregunta.save(trxName)) {
							log.log(Level.SEVERE, "While saving question ...", CLogger.retrieveError().getValue());
							iPregunta.setI_ErrorMsg(Msg.getMsg(getCtx(), "CanNotSaveQuestion"));
							msg += "\n" + Msg.getMsg(getCtx(), "CanNotSaveQuestion");
							guardar = false;
						} else {
							insertadas.put(
									iPregunta.getPregunta_Value(), 
									pregunta.getEXME_Pregunta_ID()
							);
						}
					}

					//tenemos respuestas fijas?
					if(guardar && 
							iPregunta.getPregunta_Lista_Value() != null && 
							iPregunta.getPregunta_Lista_Value().length() > 0) {
						
						MPregunta_Lista pl = new MPregunta_Lista(getCtx(), 0, trxName);
						pl.setDescription(iPregunta.getPregunta_Lista_Description());
						pl.setEXME_Pregunta_ID(insertadas.get(iPregunta.getPregunta_Value()));
						pl.setName(iPregunta.getPregunta_Lista_Name());
						pl.setValue(iPregunta.getPregunta_Lista_Value());
						
						if(!pl.save(trxName)) {
							log.log(Level.SEVERE, "While saving question list ...", CLogger.retrieveError().getValue());
							iPregunta.setI_ErrorMsg(Msg.getMsg(getCtx(), "CanNotSaveQuestionAnswer"));
							addLog(Msg.getMsg(getCtx(), "CanNotSaveQuestionAnswer") + " " + pl.getValue());
						} else {
							iPregunta.setEXME_Pregunta_Lista_ID(pl.getEXME_Pregunta_Lista_ID());
						}
					}
					
					//si no ocurrio ningun error, podemos guardar y afectar la transaccion
					if(guardar) {
						iPregunta.setEXME_Pregunta_ID(insertadas.get(iPregunta.getPregunta_Value()));
						iPregunta.setI_IsImported(true);
						
						trx.commit();
					} else {
						trx.rollback();
					}
					
					trx.close();
					
					iPregunta.setProcessed(true);
					
					if(!iPregunta.save()) {
						log.log(Level.SEVERE, "While saving interface question ...", CLogger.retrieveError().getValue());
						addLog(Msg.getMsg(getCtx(), "CanNotSaveQuestion") + " " + iPregunta.getPregunta_Value());
					}
					
					 
				}
				
			}
			
			msg = Msg.getMsg(getCtx(), "ImportEnded");
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, "While saving questionnaires data", e);

			addLog(e.getMessage());
			
			if(trx != null)
				trx.rollback();
		} finally {
			if(trx != null)
				trx.close();


			if(rs != null)
				rs.close();

			if(pstmt != null)
				pstmt.close();
		}

		return msg;
	}

}
