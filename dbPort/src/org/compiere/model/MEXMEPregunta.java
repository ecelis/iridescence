package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.form.Option;
import org.compiere.model.form.Question;
import org.compiere.model.form.Rule;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.ecaresoft.apps.secure.Base64;

/**
 * @author Expert
 *
 */
public class MEXMEPregunta extends X_EXME_Pregunta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** bitacora */
	private static CLogger slog = CLogger
			.getCLogger(MEXMEPregunta.class);
	/**
	 * @param ctx
	 * @param EXME_Pregunta_ID
	 * @param trxName
	 */
	public MEXMEPregunta(Properties ctx, int EXME_Pregunta_ID, String trxName) {
		super(ctx, EXME_Pregunta_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPregunta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param lvb
	 * @param trxName
	 * @return
	 */
    public static  List<MEXMEPregunta> find(Properties ctx, String sql, List <?> params, String trxName){
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MEXMEPregunta> lst = new ArrayList<MEXMEPregunta>();
        try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				lst.add( new MEXMEPregunta(ctx, rs, null) );
			}
			
        }catch (Exception e) {
        	slog.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		
		return lst;
	}
    
	public static List<Question> getQuestions(Properties ctx, int categoryId, boolean isCopy) {
		List<Question> questions = new ArrayList<Question>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_pregunta ");
		sql.append("WHERE ");
		sql.append("  exme_tipopregunta_id = ? AND ");
		sql.append("  isactive = 'Y' AND ");
		sql.append("  x > 0 AND ");
		sql.append("  y > 0 ORDER BY y, x");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questions.add(fromPO(ctx, new MEXMEPregunta(ctx, rs, null), isCopy));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return questions;
	}

	/**
	 * Cargar los datos de PO a POJO
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param pregunta
	 *            Pregunta PO
	 * @param isCopy
	 *            Si es una copia
	 * @return POJO pregunta
	 */
	public static Question fromPO(Properties ctx, MEXMEPregunta pregunta, boolean isCopy) {
		Question question = new Question();
		question.setId(pregunta.getEXME_Pregunta_ID());
		question.setName(pregunta.getName());
		question.setDescription(pregunta.getDescription());
		question.setRows(pregunta.getNRows());
		question.setColumns(pregunta.getNColumns());
		question.setType(pregunta.getTipoDato());
		question.setxPosition(pregunta.getX());
		question.setyPosition(pregunta.getY());
		question.setMandatory(pregunta.isObligatoria());
		question.setPageSize(pregunta.getPageSize());
		question.setMultiple(pregunta.isMultiple());
		question.setHideLabel(pregunta.isHideLabel());
		
		List<MEXMEMensajePregunta> lstMsg = MEXMEMensajePregunta.get(ctx, question.getId(), null);
		question.setAlert(!lstMsg.isEmpty());
		
		for (MEXMEMensajePregunta mensaje : lstMsg) {
			question.addMessage(mensaje);
		}
		
		if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getType()) || MEXMEPregunta.TIPODATO_OptionList.equals(question.getType())) {
			List<MEXMEPreguntaLista> list = MEXMEPreguntaLista.get(Env.getCtx(), pregunta.getEXME_Pregunta_ID(), null);
			for (MEXMEPreguntaLista preguntaLista : list) {
				Option option = new Option();
				option.setName(preguntaLista.getName());
				option.setId(preguntaLista.getEXME_Pregunta_Lista_ID());
				option.setListValue(preguntaLista.getListValue());
				option.setSeqNo(preguntaLista.getSeqNo());
				question.getOptions().add(option);
			}
		} else if (MEXMEPregunta.TIPODATO_FixedImage.equals(question.getType())) {
			question.setFileName(pregunta.getRutaImagen());

			if (isCopy) {
				question.setValue(Base64.encodeBytes(pregunta.getFileContent()));
			}else{
				question.setValue(pregunta.getFileContent());
			}
		}
		
		MEXMEReglaCuestionario rule = MEXMEReglaCuestionario.get(Env.getCtx(), question.getId(), false, null);
		if (rule != null) {
			question.setRule(rule.getRule());
			
			if (isCopy) {
				question.setcRule(Rule.getRule(rule));
			}
		}
		
		rule = MEXMEReglaCuestionario.get(Env.getCtx(), question.getId(), true, null);
		if (rule != null) {
			question.setRule2(rule.getSumRule());
			if (isCopy) {
				question.setSum(Rule.getRule(rule));
			}
		}
		
		return question;
	}

	@Override
	public boolean delete(boolean force, String trxName) {
		List<MEXMEPreguntaLista> list = MEXMEPreguntaLista.get(Env.getCtx(), getEXME_Pregunta_ID(), null);
		for (MEXMEPreguntaLista preguntaLista : list) {
			if (!preguntaLista.delete(true, trxName)) {
				return false;
			}
		}
		return super.delete(force, trxName);
	}

	@Override
	public String getTipoDato() {
		return StringUtils.trim(super.getTipoDato());
	}
	
//	public boolean save(String trxName){
//		
//		return super.save(trxName);
//	}
}
