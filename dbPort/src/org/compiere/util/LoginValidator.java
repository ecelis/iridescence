package org.compiere.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.text.StrTokenizer;
import org.compiere.model.MEXMEConfigSeguridad;

import com.ecaresoft.util.PasswordHandler;

/**
 * Clase para validar si debe o no bloquearse un usuario
 *
 * @author Omar de la Rosa
 *
 */
public class LoginValidator {

	private String cadena = null;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String passwordProv = null;
	private int intentos = 5;
	private int intentosActuales = 0;

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public String getPasswordProv() {
		return passwordProv;
	}

	public void setPasswordProv(String passwordProv) {
		this.passwordProv = passwordProv;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	/**
	 * Constructor por defecto
	 *
	 * @param ctx
	 *            Contexto
	 * @param cadena
	 *            Cadena de contexto
	 */
	public LoginValidator(Properties ctx, int AD_Client_ID, String cadena) {
		this.cadena = cadena;
		StrTokenizer st = new StrTokenizer(this.cadena, "|");
		while (st.hasNext()) {
			Usuario u = new Usuario(st.nextToken());
			if (usuarios.contains(u)) {
				int indice = usuarios.indexOf(u);
				Usuario tmp = usuarios.get(indice);
				tmp.setContador(tmp.getContador() + 1);
				usuarios.set(indice, tmp);
			} else {
				u.setContador(1);
				usuarios.add(u);
			}
		}
		MEXMEConfigSeguridad conf = MEXMEConfigSeguridad.getByClient(ctx, AD_Client_ID, null);
		if (conf != null) {
			intentos = conf.getIntentos();
		}

	}

	public static String calculaPasswordProv(){
//		Random r = new Random();
//		long value = r.nextLong();
//
//		if(value == Integer.MIN_VALUE) {
//			value = value * -1;
//		}
//
//		value = Math.abs(value);
//
//		return Long.toString(value, 36);

		PasswordHandler ph = new PasswordHandler(Env.getCtx());
		return ph.generatePassword();

	}

	/**
	 * Valida si debe o no bloquearse el usuario
	 *
	 * @param userName
	 * @return
	 */
	public boolean necesitaBloqueo(String userName) {
		boolean retValue = false;
		int indice = usuarios.indexOf(new Usuario(userName));
		if (indice != -1) {
			intentosActuales = usuarios.get(indice).getContador();
			if (intentosActuales >= intentos) {
				retValue = true;
				passwordProv = calculaPasswordProv();
			}
		}
		return retValue;
	}

	/**
	 * Clase interna para manejo de usuarios
	 *
	 * @author Omar de la Rosa
	 *
	 */
	private class Usuario {
		private int contador = 0;
		private String userName = null;

		public int getContador() {
			return contador;
		}

		public void setContador(int contador) {
			this.contador = contador;
		}

		public String getUserName() {
			return userName;
		}

		@SuppressWarnings("unused")
		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Usuario(String userName) {
			super();
			this.userName = userName;
		}

		@Override
		public boolean equals(Object obj) {
			boolean retVal = false;

			if(obj != null) {
				Usuario u = (Usuario) obj;
				if (getUserName().equalsIgnoreCase(u.getUserName())) {
					retVal = true;
				}
			}

			return retVal;
		}
	}

	public int getIntentosActuales() {
		return intentosActuales;
	}

	public void setIntentosActuales(int intentosActuales) {
		this.intentosActuales = intentosActuales;
	}
}
