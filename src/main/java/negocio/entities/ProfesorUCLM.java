package negocio.entities;

import persistencia.*;

public class ProfesorUCLM extends Profesor {

	Centro centroAdscripcion;
	CategoriaProfesor categoria;
	ProfesorUCLMDAO profesorUCLMDao;

	public ProfesorUCLM() {
		super("", "", "", false);
		profesorUCLMDao = new ProfesorUCLMDAO();
	} 
	
	
	
}