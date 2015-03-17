package org.compiere.model;

public class MEXMEIntervalosView {

	
	private String hora = "";
	private boolean ocupado = false;
	
	private String fecha = "";
	//Intervalo de la agenda
	private int intervalo = 0;
	//Numero de intervalos configurados
	private int numIntervalos = 0;
	//Numero de intervalos requeridos para la solicitud
	private int numInterRequeridos = 0;
	//Intervalos disponibles por dia (numIntervalos-intervalUsados)
	private int intervalDisponibles = 0;
	//Intervalos usados por dia
	private int intervalUsados = 0;
	//Dia de la semana  1-7
	private int dia = 0;

	public int getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	public int getNumIntervalos() {
		return numIntervalos;
	}
	public void setNumIntervalos(int numIntervalos) {
		this.numIntervalos = numIntervalos;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIntervalDisponibles() {
		return intervalDisponibles;
	}
	public void setIntervalDisponibles(int intervalDisponibles) {
		this.intervalDisponibles = intervalDisponibles;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getIntervalUsados() {
		return intervalUsados;
	}
	public void setIntervalUsados(int intervalUsados) {
		this.intervalUsados = intervalUsados;
	}
	public int getNumInterRequeridos() {
		return numInterRequeridos;
	}
	public void setNumInterRequeridos(int numInterRequeridos) {
		this.numInterRequeridos = numInterRequeridos;
	}
}
