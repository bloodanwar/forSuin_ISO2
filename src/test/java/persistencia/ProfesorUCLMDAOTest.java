package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import negocio.entities.CategoriaProfesor;
import negocio.entities.Centro;
import negocio.entities.ProfesorUCLM;

public class ProfesorUCLMDAOTest {	

	private ProfesorUCLM profesor;
	private int position;

	private ProfesorUCLM profesorEsperado;
	private List<ProfesorUCLM> profesoresEsperados;

	private ProfesorUCLM profesorObtenido;
	private List<ProfesorUCLM> profesoresObtenidos;


	@Test
	public void cp1() {
		try {
			// SETUP
			String dni = "dni";
			String nombre = "nombre";
			String apellidos = "appellido";
			Boolean doctor = true;
			CategoriaProfesor categoria = CategoriaProfesor.ASOCIADO;
			Centro centro = new Centro("UCLM TAL");
			
			profesor = new ProfesorUCLM(dni, nombre, apellidos, doctor, categoria, centro);
			
			// ESPERADO
			profesorEsperado = profesor;
			profesoresEsperados = profesor.profesorUCLMDao.listarProfesores();
			profesoresEsperados.add(profesorEsperado);
			position = profesoresEsperados.size()-1;
			
			// CREACION
			profesor.profesorDao.crearNuevoProfesor(profesor);
			profesoresObtenidos = profesor.profesorUCLMDao.listarProfesores();
			profesorObtenido = profesoresObtenidos.get(position);
			assertEquals(profesorEsperado, profesorObtenido);

			// SELECCION + EDICCIÓN
			dni = "dni";
			nombre = "";
			apellidos = "";
			doctor = false;
			categoria = CategoriaProfesor.AYUDANTE;
			profesor = new ProfesorUCLM(dni, nombre, apellidos, doctor, categoria, centro);
			profesorEsperado = profesor;

			profesor.profesorDao.editarProfesor(profesor);
			profesorObtenido = profesor.profesorUCLMDao.seleccionarProfesorUCLM(profesorObtenido);
			assertEquals(profesorEsperado, profesorObtenido);

			// ELIMINACION
			profesor.profesorDao.eliminarProfesor(profesorObtenido);
			profesoresEsperados.remove(position);
			profesoresObtenidos = profesor.profesorUCLMDao.listarProfesores();
			assertEquals(profesoresEsperados.size(), profesoresObtenidos.size());
			
		} catch (SQLException e) {
			System.out.println(e);
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
