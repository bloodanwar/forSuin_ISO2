package negocio.entities;

import java.util.*;
import persistencia.*;

public class Estudiante {

	Collection<Matricula> matriculas;
	EstudianteDAO estudianteDao;
	private String dni;
	private String nombre;
	private String apellidos;
	private String titulacion;
	private String cualificacion;

}