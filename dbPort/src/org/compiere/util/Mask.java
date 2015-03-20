package org.compiere.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * It allows a user to more easily enter fixed width input where you would like them to enter the data in a certain format (dates,phone numbers, etc). <br>
 * A mask is defined by a format made up of mask literals and mask definitions. <br>
 * Any character not in the definitions list below is considered a mask literal. <br>
 * Mask literals will be automatically entered for the user as they type and will not be able to be removed by the user. <br>
 * The following mask definitions are predefined:
 * 
 * a - Represents an alpha character (A-Z,a-z) <br>
 * 9 - Represents a numeric character (0-9) <br>
 * * - Represents an alphanumeric character (A-Z,a-z,0-9) <br>
 * 
 * @author Lorena Lama
 */
public class Mask {

	private String mask = null;

	private String definitions = null;

	private String replaceChar = null;

	public Mask(String mask, String definitions, String replaceChar) {
		super();
		this.mask = mask;
		this.definitions = definitions;
		this.replaceChar = replaceChar;
	}

	public String[] getReplaceChars() {
		return StringUtils.splitByCharacterType(replaceChar);
	}

	public String replaceChars(String txt) {
		if (replaceChar != null) {
			String[] srt = getReplaceChars();
			if (srt != null) {
				String[] rpl = new String[srt.length];
				for (int i = 0; i < srt.length; i++) {
					rpl[i] = "";
				}
				return StringUtils.replaceEach(txt, srt, rpl);
			}
		}
		return txt;
	}

	/**
	 * ZK functions<br>
	 * This is a masked input plugin for the jQuery javascript library. <br>
	 * <i>http://digitalbush.com/projects/masked-input-plugin</i>
	 * 
	 * @param uuid
	 * @return
	 */
	public StringBuilder getMask(String uuid) {
		StringBuilder command = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if (!StringUtils.isBlank(definitions)) {
			String[] str = StringUtils.split(definitions, ";,");
			if (str != null) {
				for (int j = 0; j < str.length; j++) {
					String[] split = StringUtils.split(str[j], "=");
					if (split != null && split.length == 2) {
						command.append("$.mask.definitions['");
						command.append(split[0]);
						command.append("']='");
						command.append(split[1]);
						command.append("';");
					}
				}
			}
		}
		command.append("jQuery('#");
		command.append(uuid);
		command.append("').mask('");
		command.append(mask);
		command.append("');");
		return command;
	}

	public String getMask() {
		return mask;
	}

}
