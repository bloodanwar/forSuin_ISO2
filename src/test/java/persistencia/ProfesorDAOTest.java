package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Profesor;

public class ProfesorDAOTest {	

	private Profesor profesor;
	private int position;

	private Profesor profesorEsperado;
	private List<Profesor> profesoresEsperados;

	private Profesor profesorObtenido;
	private List<Profesor> profesoresObtenidos;


	@Test
	public void cp1() {
		try {
			// SETUP
			String dni = "dni";
			String nombre = "nombre";
			String apellidos = "appellido";
			Boolean doctor = true;
			profesor = new Profesor(dni, nombre, apellidos, doctor);

			// ESPERADO

			profesorEsperado = profesor;
			profesoresEsperados = profesor.profesorDao.listarProfesores();
			profesoresEsperados.add(profesorEsperado);
			position = profesoresEsperados.size()-1;


			// CREACION
			profesorEsperado.profesorDao.crearNuevoProfesor(profesorEsperado);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();
			profesorObtenido = profesoresObtenidos.get(position);

			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
			assertEquals(profesorEsperado.getNombre(), profesorObtenido.getNombre());
			assertEquals(profesorEsperado.getDni(), profesorObtenido.getDni());
			assertEquals(profesorEsperado.getApellidos(), profesorObtenido.getApellidos());
			assertEquals(profesorEsperado.isDoctor(), profesorObtenido.isDoctor());

			// SELECCION + EDICCIÓN
			dni = "dni";
			nombre = "";
			apellidos = "";
			doctor = false;
			profesor = new Profesor(dni, nombre, apellidos, doctor);
			profesorEsperado = profesor;

			profesor.profesorDao.editarProfesor(profesor);
			profesorObtenido = profesor.profesorDao.seleccionarProfesor(profesorObtenido);
			assertEquals(profesorEsperado.getNombre(), profesorObtenido.getNombre());
			assertEquals(profesorEsperado.getDni(), profesorObtenido.getDni());
			assertEquals(profesorEsperado.getApellidos(), profesorObtenido.getApellidos());
			assertEquals(profesorEsperado.isDoctor(), profesorObtenido.isDoctor());


			// PRUEBA DE ELIMINACION PROFESOR
			profesor.profesorDao.eliminarProfesor(profesorObtenido);
			profesoresEsperados.remove(position);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();

			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
