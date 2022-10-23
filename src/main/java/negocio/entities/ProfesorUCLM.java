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
}