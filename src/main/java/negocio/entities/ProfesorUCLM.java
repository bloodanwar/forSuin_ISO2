package negocio.entities;

import persistencia.*;

public class ProfesorUCLM extends Profesor {

	public Centro centroAdscripcion;
	public CategoriaProfesor categoria;
	public ProfesorUCLMDAO profesorUCLMDao;

	public ProfesorUCLM() {
		super("", "", "", false);
		profesorUCLMDao = new ProfesorUCLMDAO();
	}

	public ProfesorUCLM(String id) {
		super(id, "", "", false);
		profesorUCLMDao = new ProfesorUCLMDAO();
	}

	public ProfesorUCLM(String dni, String nombre, String apellidos, boolean doctor, CategoriaProfesor categoria, Centro centroAdscripcion) {
		super(dni, nombre, apellidos, doctor);
		this.centroAdscripcion = centroAdscripcion;
		this.categoria = categoria;
		profesorUCLMDao = new ProfesorUCLMDAO();
	} 
	
	@Override
	public String toString() {
		return "ProfesorUCLM:"
		+"\nDNI: "+this.getDni()
		+"\nNombre: "+this.getNombre()
		+"\nApellidos: "+this.getApellidos()
		+"\nDoctor: "+this.isDoctor()
		+"\nCategoria: "+this.categoria
		+"\nCentro: "+this.centroAdscripcion.getNombre()
		+"\n";
		
	}
	
	@Override
	public boolean equals(Object obj){
		if (getClass() != obj.getClass()) return false;
		ProfesorUCLM profObj = (ProfesorUCLM) obj; 
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
		if (!(this.centroAdscripcion.getNombre().equals(profObj.centroAdscripcion.getNombre()))) return false;
		if (this.categoria!=profObj.categoria) return false;

		return true;
	}
}