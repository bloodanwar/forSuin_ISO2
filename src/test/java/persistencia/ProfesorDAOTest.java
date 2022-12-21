package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

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
			profesor.profesorDao.crearNuevoProfesor(profesor);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();
			profesorObtenido = profesoresObtenidos.get(position);
			assertEquals(profesorEsperado, profesorObtenido);

			// SELECCION + EDICCIÓN
			dni = "dni";
			nombre = "";
			apellidos = "";
			doctor = false;
			profesor = new Profesor(dni, nombre, apellidos, doctor);
			profesorEsperado = profesor;

			profesor.profesorDao.editarProfesor(profesor);
			profesorObtenido = profesor.profesorDao.seleccionarProfesor(profesorObtenido);
			assertEquals(profesorEsperado, profesorObtenido);

			// ELIMINACION
			profesor.profesorDao.eliminarProfesor(profesorObtenido);
			profesoresEsperados.remove(position);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();
			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
			
		} catch (SQLException e) {
			System.out.println(e);
			fail("HA TIRADO UNA EXCEPCION");
		}
	}

	@Test
	public void cp2() {
		try {
			// SETUP
			String dni = "";
			String nombre = "";
			String apellidos = "";
			Boolean doctor = false;
			profesor = new Profesor(dni, nombre, apellidos, doctor);

			// ESPERADO
			profesorEsperado = profesor;
			profesoresEsperados = profesor.profesorDao.listarProfesores();
			profesoresEsperados.add(profesorEsperado);
			position = profesoresEsperados.size()-1;

			// CREACION
			profesor.profesorDao.crearNuevoProfesor(profesor);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();
			profesorObtenido = profesoresObtenidos.get(position);
			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
			assertEquals(profesorEsperado, profesorObtenido);

			// SELECCION + EDICCIÓN
			dni = "";
			nombre = "nombre";
			apellidos = "apellido";
			doctor = true;
			profesor = new Profesor(dni, nombre, apellidos, doctor);
			profesorEsperado = profesor;
			
			profesor.profesorDao.editarProfesor(profesor);
			profesorObtenido = profesor.profesorDao.seleccionarProfesor(profesorObtenido);
			assertEquals(profesorEsperado, profesorObtenido);

			// ELIMINACION
			profesor.profesorDao.eliminarProfesor(profesorObtenido);
			profesoresEsperados.remove(position);
			profesoresObtenidos = profesor.profesorDao.listarProfesores();
			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
			
		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}

	@Test
	public void cp3() {
		try {
			// SETUP
			profesor = null;

			// CREACION
			try{
				profesorEsperado.profesorDao.crearNuevoProfesor(profesor);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// SELECCION
			try{
				profesorEsperado.profesorDao.seleccionarProfesor(profesor);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// EDICCION
			try{
				profesorEsperado.profesorDao.editarProfesor(profesor);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// ELIMINACION
			try{
				profesorEsperado.profesorDao.eliminarProfesor(profesor);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}
}
