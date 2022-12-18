package negocio.entities;

import persistencia.*;

public class Profesor {

	public ProfesorDAO profesorDao;
	private String dni;
	private String nombre;
	private String apellidos;
	private boolean doctor;
	
	public Profesor() {
		profesorDao = new ProfesorDAO();
	}
	
	public Profesor(String dni) {
		profesorDao = new ProfesorDAO();
		this.dni = dni;
	}
		
	public Profesor(String dni, String nombre, String apellidos, boolean doctor) {
		profesorDao = new ProfesorDAO();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.doctor = doctor;
	}
	
	@Override
	public String toString(){
		return "Profesor:"
		+"\nDNI: "+this.getDni()
		+"\nNombre: "+this.getNombre()
		+"\nApellidos: "+this.getApellidos()
		+"\nDoctor: "+this.isDoctor();
	}
	
	@Override
	public boolean equals(Object obj){
		if (getClass() != obj.getClass()) return false;
		Profesor profObj = (Profesor) obj; 
		if (!(this.getDni().equals(profObj.getDni()))) return false; 	
		if (this.getNombre() == null){
			if (profObj.getNombre()!=null) return false;
		} else {
			if (!(this.getNombre().equals(profObj.getNombre()))) return false;
		}
		if (this.getApellidos() == null){
			if (profObj.getApellidos()!=null) return false;
		} else {
			if (!(this.getApellidos().equals(profObj.getApellidos()))) return false;
		}	
		if (this.isDoctor() != profObj.isDoctor()) return false;
		return true;
	}
	
	public String getDni() { return dni; }
	public void setDni(String dni) { this.dni = dni; }
	
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	
	public boolean isDoctor() { return doctor; }
	public void setDoctor(boolean doctor) { this.doctor = doctor; }	
}