package com.ecaresoft.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Listado de errores
 * 
 * @author odelarosa
 * 
 */
public class ErrorList {

	private List<Error> list;

	/**
	 * Constructor vac&iacuteo
	 */
	public ErrorList() {
		this(new ArrayList<Error>());
	}

	/**
	 * Constructor que inicializa el listado
	 * 
	 * @param list
	 *            Listado de errores
	 */
	public ErrorList(List<Error> list) {
		this.list = list == null ? new ArrayList<Error>() : list;
	}

	/**
	 * Agregar un error a la lista
	 * 
	 * @param error
	 *            Error a agregar
	 */
	public void add(Error error) {
		list.add(error);
	}

	/**
	 * Agregar un error a la lista
	 * 
	 * @param code
	 *            C&oacutedigo de error: {@link Error#VALIDATION_ERROR},
	 *            {@link Error#UNKONOW_ERROR}, {@link Error#EXCEPTION_ERROR},
	 *            {@link Error#CONFIGURATION_ERROR}
	 * @param info
	 *            Informaci&oacuten del error
	 */
	public void add(int code, String info) {
		if(StringUtils.isNotBlank(info)){
			list.add(new Error(code, info));
		}
	}

	/**
	 * Obtener listado de errores
	 * 
	 * @return Listado de errores
	 */
	public List<Error> getList() {
		return list;
	}

	/**
	 * Revisa si la lista est&a vac&iacutea
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringUtils.join(getList(), "\n");
	}

}
