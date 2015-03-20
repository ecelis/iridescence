package com.ecaresoft.tests;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.compiere.model.MEXMEConversation;
import org.compiere.model.MEXMEMessage;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.junit.Test;

import com.ecaresoft.tests.utils.TestUtils;

/**
 * Prueba de mensajería entre usuarios
 * 
 * @author odelarosa
 * 
 */
public class MEXMEMessageTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		TestUtils.setUp();
	}

	@Test
	public void testMessage() {
		MUser user1 = null;
		MUser user2 = null;

		// Obtenemos dos usuarios de la misma organización
		user1 = TestUtils.getRandomPO(MUser.class, MUser.getUserOrgSearch(Env.getCtx(), false).toString(), false);
		user2 = TestUtils.getRandomPO(MUser.class, MUser.getUserOrgSearch(Env.getCtx(), false).toString(), false);

		if (user1 != null && user2 != null && !user1.equals(user2)) {
			// Asignamos el usuario 1 como si fuera de logueo
			Env.setContext(Env.getCtx(), "#AD_User_ID", user1.getAD_User_ID());

			Trx trx = null;
			try {
				trx = Trx.get(Trx.createTrxName("MSG"), true);

				final MEXMEConversation conversation = new MEXMEConversation(Env.getCtx(), 0, null);

				conversation.setAD_User_ID(user1.getAD_User_ID());
				conversation.setTitle("JUnit Test");

				// Creamos la conversación
				if (conversation.save(trx.getTrxName())) {
					final MEXMEMessage message = new MEXMEMessage(Env.getCtx(), 0, null);

					message.setEXME_Conversation_ID(conversation.get_ID());
					message.setMessage("JUnit Test");

					// Creamos un mensaje
					if (message.save(trx.getTrxName())) {
						// Obtenemos las conversaciones para el usuario 1
						final List<MEXMEConversation> conversations = MEXMEConversation.get(Env.getCtx(), user1.getAD_User_ID(), trx.getTrxName());

						boolean found = false;

						for (final MEXMEConversation conversation2 : conversations) {
							// Si la conversación existe es correcto
							if (conversation2.equals(conversation)) {
								found = true;
								break;
							}
						}

						// Validamos que exista
						Assert.assertTrue(found);

						// Obtenemos los mensajes de la conversación
						final List<MEXMEMessage> msgLst = MEXMEMessage.get(Env.getCtx(), conversation.getEXME_Conversation_ID(), trx.getTrxName());

						found = false;
						for (final MEXMEMessage msg : msgLst) {
							// Si el mensje existe es correcto
							if (msg.equals(message)) {
								found = true;
							}
						}

						// Validamos si existe
						Assert.assertTrue(found);

					} else {
						// Hay error si no se guardó el mensaje
						Assert.assertTrue(false);
					}
				} else {
					// Hay error si no se guardó la conversación
					Assert.assertTrue(false);
				}
			} catch (final Exception ex) {
				Trx.rollback(trx);
				Trx.close(trx);
				Assert.assertTrue(false);
			}
		}
	}
}
