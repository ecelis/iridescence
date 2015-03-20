/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.compiere.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 
 * @author dpr
 */
public class ServerResources {

	public static File[] directoryFiles(String strDir) throws Exception {
		File dir = new File(strDir);
		if (!dir.exists())
			throw new Exception("Directory does not exist.");

		if (!dir.isDirectory())
			throw new Exception("Is not a Directory.");

		return dir.listFiles();
	}

	public static String[] directoryNameFiles(String strDir) throws Exception {
		File dir = new File(strDir);

		if (!dir.exists())
			throw new Exception("Directory does not exist.");

		if (!dir.isDirectory())
			throw new Exception("Is not a Directory.");

		return dir.list();
	}

	public static void saveNewTxtFile(String absoluteFileName, String txtFile)
			throws Exception {
		FileWriter writer = new FileWriter(absoluteFileName);
		writer.write(txtFile);
		writer.flush();
		writer.close();
	}

	public static String readTxtFile(String absoluteFileName) {
		StringBuffer read = new StringBuffer();
		FileReader in = null;
		BufferedReader buff = null;

		try {
			in = new FileReader(absoluteFileName);
			buff = new BufferedReader(in);
			String line = "";

			while ((line = buff.readLine()) != null) {
				read.append(line + '\n');
			}

		} catch (Exception ex) {
			System.out.println(ex);
			read = null;
		}

		finally {
			try {
				buff.close();
			} catch (Exception ex) {
			}
			try {
				in.close();
			} catch (Exception ex) {
			}

		}

		return read.toString();
	}
}
