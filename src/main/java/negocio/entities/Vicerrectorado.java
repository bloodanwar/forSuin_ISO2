package negocio.entities;

import persistencia.VicerrectoradoDAO;

public class Vicerrectorado {
	
	public VicerrectoradoDAO vicerrectoradoDao;
	private String dni;
	private String nombre;
	private String apellidos;
	private boolean jefe;

	public Vicerrectorado() {
		vicerrectoradoDao = new VicerrectoradoDAO();
	}
	
	public Vicerrectorado(String dni) {
		vicerrectoradoDao = new VicerrectoradoDAO();
		this.dni = dni;
	}
	
	public Vicerrectorado(String dni, String nombre, String apellidos, boolean jefe) {
		vicerrectoradoDao = new VicerrectoradoDAO();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.jefe = jefe;
	}

	public String getDni() { return dni; }
	public void setDni(String dni) { this.dni = dni; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }

	public boolean isJefe() { return jefe; }
	public void setJefe(boolean jefe) { this.jefe = jefe; }
}
