package negocio.entities;

import java.util.Date;

import persistencia.*;

public class Matricula {

	Estudiante estudiante;
	CursoPropio titulo;
	ModoPago tipoPago;
	MatriculaDAO matriculaDAO;
	private Date fecha;
	private boolean pagado;
	private int attribute;

}