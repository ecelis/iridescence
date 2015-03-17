package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author odelarosa
 * 
 */
public class MEXMEConversation extends X_EXME_Conversation {

	private static CLogger s_log = CLogger.getCLogger(MEXMEConversation.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3644768246885595445L;

	/**
	 * Obtiene las conversaciones de un usuario
	 * 
	 * @param ctx
	 *            Contexto
	 * @param adUserId
	 *            Id del usuario
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de conversaciones
	 */
	public static List<MEXMEConversation> get(Properties ctx, int adUserId, String trxName) {
		s_log.fine("get userId: " + adUserId);
//		sql.append("SELECT * FROM   exme_conversation ");
//		sql.append("WHERE (createdby = ? OR ad_user_id = ?) ");
//		sql.append("ORDER BY created desc");
		return new Query(ctx, Table_Name, " (createdby=? OR ad_user_id=?) ", trxName)//
			.setOrderBy(" Created DESC ")//
			.setParameters(adUserId, adUserId)//
			.list();
	}

	/**
	 * Cuenta de mensajes sin leer, globales
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static int getUnread(Properties ctx, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COUNT(m.*) ");
		sql.append("FROM ");
		sql.append("  exme_conversation c ");
		sql.append("  INNER JOIN exme_message m ");
		sql.append("  ON c.exme_conversation_id = m.exme_conversation_id ");
		sql.append("WHERE ");
		sql.append("  (c.createdby = ? OR ");
		sql.append("  c.ad_user_id = ?) AND ");
		sql.append("  m.createdBy != ? AND ");
		sql.append("  m.viewed = 'N'");

		int userId = Env.getAD_User_ID(ctx);

		return DB.getSQLValue(trxName, sql.toString(), userId, userId, userId);
	}

	/**
	 * @param ctx
	 * @param EXME_Conversation_ID
	 * @param trxName
	 */
	public MEXMEConversation(Properties ctx, int EXME_Conversation_ID, String trxName) {
		super(ctx, EXME_Conversation_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConversation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Cuenta de mensajes
	 * 
	 * @return
	 */
	public int getCount() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COUNT(*) ");
		sql.append("FROM ");
		sql.append("  exme_message ");
		sql.append("WHERE ");
		sql.append("  exme_conversation_id = ? ");
		
		return DB.getSQLValue(null, sql.toString(), get_ID());
	}

	/**
	 * Obtener cantidad de mensajes no leidos
	 * 
	 * @return
	 */
	public int getUnread() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COUNT(m.*) ");
		sql.append("FROM ");
		sql.append("  exme_conversation c ");
		sql.append("  INNER JOIN exme_message m ");
		sql.append("  ON c.exme_conversation_id = m.exme_conversation_id ");
		sql.append("WHERE ");
		sql.append("  m.viewed = 'N' AND ");
		sql.append("  c.exme_conversation_id = ? AND ");
		sql.append("  m.createdBy != ? ");

		return DB.getSQLValue(null, sql.toString(), get_ID(),Env.getAD_User_ID(getCtx()));
	}

	/**
	 * Marcar mensajes como leidos
	 */
	public void markAsRead() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  EXME_Message m ");
		sql.append("SET ");
		sql.append("  viewed = ");
		sql.append("'Y' FROM ");
		sql.append("  EXME_Conversation c ");
		sql.append("WHERE ");
		sql.append("  c.exme_conversation_id = m.exme_conversation_id AND ");
		sql.append("  m.viewed = 'N' AND ");
		sql.append("  m.createdBy != ? AND ");
		sql.append("  c.exme_conversation_id = ? ");

		int op = DB.executeUpdate(sql.toString(), new Object[] { Env.getAD_User_ID(getCtx()), get_ID() }, null);
		s_log.config("markAsRead > " + op + ", AD_User_ID:" + Env.getAD_User_ID(getCtx()) + " EXME_Conversation_ID:" + get_ID());
	}
	
	public static MEXMEConversation newConversation(Properties ctx, int userId, String title, String trxName){
		MEXMEConversation conversation = new MEXMEConversation(ctx, 0, trxName);
		conversation.setAD_User_ID(userId);
		conversation.setTitle(title);
		return conversation;
	}

}
