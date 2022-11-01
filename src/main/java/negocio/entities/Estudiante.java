package negocio.entities;

import java.util.*;
import persistencia.*;

public class Estudiante {

	public Collection<Matricula> matriculas;
	public EstudianteDAO estudianteDao;
	private String dni;
	private String nombre;
	private String apellidos;
	private String titulacion;
	private String cualificacion;
	
	public Estudiante() {
		estudianteDao = new EstudianteDAO();
	}
	
	public Estudiante(String dni) {
		estudianteDao = new EstudianteDAO();
		this.dni = dni;
	}
	
	public Estudiante(String dni, String nombre, String apellidos, String titulacion, String cualificacion) {
		estudianteDao = new EstudianteDAO();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.titulacion = titulacion;
		this.cualificacion = cualificacion;
	}
	
	public String getDni() { return dni; }
	public void setDni(String dni) { this.dni = dni; }
	
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	
	public String getTitulacion() { return titulacion; }
	public void setTitulacion(String titulacion) { this.titulacion = titulacion; }

	public String getCualificacion() { return cualificacion; }
	public void setCualificacion(String cualificacion) { this.cualificacion = cualificacion; }	
}