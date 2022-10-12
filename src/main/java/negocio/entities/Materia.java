package negocio.entities;

import java.util.Date;

import persistencia.*;

public class Materia {

	Profesor responsable;
	MateriaDAO materiaDao;
	private String nombre;
	private double horas;
	private Date fechaInicio;
	private Date fechaFin;

}