package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import negocio.entities.Profesor;

public class ProfesorDAOTest {	
	
	@Test
	public void testCrearNuevoProfesor() throws SQLException {
		// PRUEBA DE CREACION PROFESOR
		Profesor profesorEsperado = new Profesor("dni", "nombre", "apellido", true);
		List<Profesor> profesoresEsperados = profesorEsperado.profesorDao.listarProfesores();
		profesoresEsperados.add(profesorEsperado);
		int position = profesoresEsperados.size()-1;

		profesorEsperado.profesorDao.crearNuevoProfesor(profesorEsperado);
		List<Profesor> profesoresObtenidos = profesorEsperado.profesorDao.listarProfesores();
		Profesor profesorObtenido = profesoresObtenidos.get(position);
		
		assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
		assertEquals(profesorEsperado.getNombre(), profesorObtenido.getNombre());
		assertEquals(profesorEsperado.getDni(), profesorObtenido.getDni());
		assertEquals(profesorEsperado.getApellidos(), profesorObtenido.getApellidos());
		assertEquals(profesorEsperado.isDoctor(), profesorObtenido.isDoctor());

		// PRUEBA DE SELECCIONAR PROFESOR
		profesorObtenido = profesorObtenido.profesorDao.seleccionarProfesor(profesorEsperado);
		assertEquals(profesorEsperado.getNombre(), profesorObtenido.getNombre());
		assertEquals(profesorEsperado.getDni(), profesorObtenido.getDni());
		assertEquals(profesorEsperado.getApellidos(), profesorObtenido.getApellidos());
		assertEquals(profesorEsperado.isDoctor(), profesorObtenido.isDoctor());
		
		// PRUEBA DE EDITAR PROFESOR
				
		
		
		// PRUEBA DE ELIMINACION PROFESOR
		profesorEsperado.profesorDao.eliminarProfesor(profesorObtenido);
		profesoresEsperados.remove(position);
		profesoresObtenidos = profesorEsperado.profesorDao.listarProfesores();

		assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());

	}

	@Test
	public void testSeleccionarProfesor() {
		//throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testEditarProfesor() {
		//throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testEliminarProfesor() {
		//throw new RuntimeException("not yet implemented");
	}

}
