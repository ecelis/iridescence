package org.compiere.process;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.CSVReader;
import org.compiere.util.DB;
import org.compiere.util.SecureEngine;

/**
 * Clase para encriptar EXME_PacienteRel
 * 
 * Pasos:<br/>
 * <ul>
 * <li>Export columnas EXME_PacienteRel_ID, name, nombre2, last_name a un csv<br/>
 * con el nombre carga.csv y grabar en el Home del usuario</li>
 * <li>Ejecutar método {@link #doIt()}</li>
 * <li>Ejecutar script update.sql localizado en Home</li>
 * <li>En caso de error ejecuar backup.sql localizado en Home</li>
 * </ul>
 * 
 * @author odelarosa
 * 
 */
public class GProcess {

	/**
	 * Ejecutar proceso de encriptado
	 */
	public static void doIt() {
		try {
			CSVReader reader = new CSVReader(new FileReader(new File(System.getProperty("user.home"), "carga.csv")));
			List<String[]> lines = reader.readAll();
			List<String> querys = new ArrayList<String>();
			List<String> backup = new ArrayList<String>();
			for (String[] line : lines) {
				String id = line[0];
				String name = null;
				String nombre2 = null;
				String lastName = null;

				backup.add(getUpdate(id, line[0], line[1], line[2]));

				if (StringUtils.isNotEmpty(line[1])) {
					name = SecureEngine.encrypt(SecureEngine.decrypt(line[1]));
				}

				if (StringUtils.isNotEmpty(line[2])) {
					nombre2 = SecureEngine.encrypt(SecureEngine.decrypt(line[2]));
				}

				if (StringUtils.isNotEmpty(line[3])) {
					lastName = SecureEngine.encrypt(SecureEngine.decrypt(line[3]));
				}

				querys.add(getUpdate(id, name, nombre2, lastName));
			}

			FileUtils.writeLines(new File(System.getProperty("user.home"), "backup.sql"), querys);
			FileUtils.writeLines(new File(System.getProperty("user.home"), "update.sql"), querys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generar String de Update
	 * 
	 * @param id
	 *            Id del pacienterel
	 * @param name
	 *            Nombre
	 * @param nombre2
	 *            Nombre 2
	 * @param lastName
	 *            Apellido
	 * @return String de update
	 */
	private static String getUpdate(String id, String name, String nombre2, String lastName) {
		StringBuilder str = new StringBuilder("update exme_pacienterel set name = ");
		str.append(DB.TO_STRING(name));
		str.append(", nombre2 = ");
		str.append(DB.TO_STRING(nombre2));
		str.append(", last_name = ");
		str.append(DB.TO_STRING(lastName));
		str.append(" where exme_pacienterel_id = ");
		str.append(id);
		str.append(";");

		return str.toString();
	}

}
