package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * @author odelarosa
 * 
 */
public class MEXMEMessage extends X_EXME_Message {

	private static CLogger		s_log				= CLogger.getCLogger(MEXMEMessage.class);
	private static final long	serialVersionUID	= 1411258004984413852L;

	/**
	 * Obtiene los mensajes de una conversación
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param conversationId
	 *            Id de la conversación
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de mensajes
	 */
	public static List<MEXMEMessage> get(Properties ctx, int conversationId, String trxName) {
		s_log.config("get conversationId: " + conversationId);
		// sql.append("SELECT * FROM exme_message ");
		// sql.append("WHERE exme_conversation_id = ? ");
		// sql.append(" ORDER BY created asc");
		return new Query(ctx, Table_Name, " exme_conversation_id=? ", trxName)//
			.setParameters(conversationId)//
			.setOrderBy(" Created desc ").list();
	}

	/**
	 * @param ctx
	 * @param EXME_Message_ID
	 * @param trxName
	 */
	public MEXMEMessage(Properties ctx, int EXME_Message_ID, String trxName) {
		super(ctx, EXME_Message_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMessage(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEMessage newMessage(Properties ctx, int conversationId, String textMsg, String trxName) {
		MEXMEMessage message = new MEXMEMessage(ctx, 0, trxName);
		message.setMessage(textMsg);
		message.setEXME_Conversation_ID(conversationId);
		return message;
	}

}
