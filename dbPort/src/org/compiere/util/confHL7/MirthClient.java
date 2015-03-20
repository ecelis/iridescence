package org.compiere.util.confHL7;

import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEMirthConfig;
import org.compiere.util.CLogger;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.User;

/** Factory de cliente para mirth
 * modificado por Lama .- evitar NullPointerException, Oct 2013
 */
public class MirthClient {
	private static CLogger	log	= CLogger.getCLogger(MirthClient.class);

	public static Client getClient(Properties ctx) throws ClientException {

		MEXMEMirthConfig config = MEXMEMirthConfig.get(ctx, null);
		if (config == null) {
			return null;
		} else {
			String url = config.getAddress();
			log.fine("Mirth Client logged to : " + url);
			Client mirthClient = new Client(url);

			mirthClient.login(config.getMirthUser(), config.getMirthPassword(), "0.0.0");

			User user = new User();
			user.setUsername(config.getMirthUser());
			List<User> users = mirthClient.getUser(user);

			if (mirthClient.isUserLoggedIn(users.get(0))) {// TODO revisar
				return mirthClient;
			} else {
				return null;
			}
		}
	}

}
