package negocio.entities;

import java.util.Date;

import persistencia.*;

public class Materia {

	public Profesor responsable;
	public MateriaDAO materiaDao;
	private String nombre;
	private double horas;
	private Date fechaInicio;
	private Date fechaFin;
	
	public Materia() {
		materiaDao = new MateriaDAO();
	}
	
	public Materia(String nombre, double horas, Date fechaInicio, Date fechaFin, Profesor responsable) {
		materiaDao = new MateriaDAO();
		this.nombre = nombre;
		this.horas = horas;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.responsable = responsable;
	}

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public double getHoras() { return horas; }
	public void setHoras(double horas) { this.horas = horas; }

	public Date getFechaInicio() { return fechaInicio; }
	public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

	public Date getFechaFin() { return fechaFin; }
	public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
	
	public String toString() {
		return "Materia:"+
				"\n\tNombre: "+nombre+
				"\n\tHoras: "+horas+
				"\n\tFecha inicio: "+fechaInicio+
				"\n\tFecha fin: "+fechaFin+
				"\n\tResponsable: "+responsable.getDni();
	}
}