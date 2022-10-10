package negocio.entities;

import persistencia.*;

public class ProfesorUCLM extends Profesor {

	Centro centroAdscripcion;
	CategoriaProfesor categoria;
	ProfesorUCLMDAO profesorUCLMDao;
	private String nombre;
	private String apellidos;
	private String dni;

}