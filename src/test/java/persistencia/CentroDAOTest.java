package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import negocio.entities.Centro;

public class CentroDAOTest {

	private Centro centro;
	private int position;

	private Centro centroEsperado;
	private List<Centro> centroEsperados;

	private Centro centroObtenido;
	private List<Centro> centrosObtenidos;


	@Test
	public void cp1() {
		try {
			// SETUP
			String nombre = "nombre";
			String localizacion = "localizacion";
			int atributo = -5;
			centro = new Centro(nombre, localizacion, atributo);

			// ESPERADO
			centroEsperado = centro;
			centroEsperados = centro.centroDao.listarCentros();
			centroEsperados.add(centroEsperado);
			position = centroEsperados.size()-1;


			// CREACION
			centro.centroDao.crearNuevoCentro(centro);
			centrosObtenidos = centro.centroDao.listarCentros();
			centroObtenido = centrosObtenidos.get(position);

			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			assertEquals(centroEsperado.getNombre(), centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());

			// SELECCION + EDICCIÓN
			nombre = "nombre";
			localizacion = "";
			atributo = 0;
			centro = new Centro(nombre, localizacion, atributo);
			centroEsperado = centro;

			centro.centroDao.editarCentro(centro);
			centroObtenido = centro.centroDao.seleccionarCentro(centroObtenido);
			assertEquals(centroEsperado.getNombre(), centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());


			// ELIMINACION
			centro.centroDao.eliminarCentro(centroObtenido);
			centroEsperados.remove(position);
			centrosObtenidos = centro.centroDao.listarCentros();
			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			
		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}
	
	@Test
	public void cp2() {
		try {
			// SETUP
			String nombre = "";
			String localizacion = "";
			int atributo = 10;
			centro = new Centro(nombre, localizacion, atributo);

			// ESPERADO
			centroEsperado = centro;
			centroEsperados = centro.centroDao.listarCentros();
			centroEsperados.add(centroEsperado);
			position = centroEsperados.size()-1;


			// CREACION
			centro.centroDao.crearNuevoCentro(centro);
			centrosObtenidos = centro.centroDao.listarCentros();
			centroObtenido = centrosObtenidos.get(position);

			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			assertEquals(centroEsperado.getNombre(), centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());

			// SELECCION + EDICCIÓN
			nombre = "";
			localizacion = "localizacion";
			atributo = 0;
			centro = new Centro(nombre, localizacion, atributo);
			centroEsperado = centro;

			centro.centroDao.editarCentro(centro);
			centroObtenido = centro.centroDao.seleccionarCentro(centroObtenido);
			assertEquals(centroEsperado.getNombre(), centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());


			// ELIMINACION
			centro.centroDao.eliminarCentro(centroObtenido);
			centroEsperados.remove(position);
			centrosObtenidos = centro.centroDao.listarCentros();
			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			
		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}
	
	@Test
	public void cp3() {
		try {
			// SETUP
			String nombre = null;
			String localizacion = null;
			int atributo = 0;
			centro = new Centro(nombre, localizacion, atributo);

			// ESPERADO
			centroEsperado = centro;
			centroEsperados = centro.centroDao.listarCentros();
			centroEsperados.add(centroEsperado);
			position = centroEsperados.size()-1;


			// CREACION
			centro.centroDao.crearNuevoCentro(centro);
			centrosObtenidos = centro.centroDao.listarCentros();
			centroObtenido = centrosObtenidos.get(position);

			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			assertEquals("null", centroObtenido.getNombre());
			assertEquals("null", centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());

			// SELECCION + EDICCIÓN
			nombre = null;
			localizacion = "";
			atributo = -5;
			centro = new Centro(nombre, localizacion, atributo);
			centroEsperado = centro;

			centro.centroDao.editarCentro(centro);
			centroObtenido = centro.centroDao.seleccionarCentro(centroObtenido);
			assertEquals("null", centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());


			// ELIMINACION
			centro.centroDao.eliminarCentro(centroObtenido);
			centroEsperados.remove(position);
			centrosObtenidos = centro.centroDao.listarCentros();
			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			
		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}
	
	@Test
	public void cp4() {
		try {
			// SETUP
			centro = null;

			// CREACION
			try{
				centroEsperado.centroDao.crearNuevoCentro(centro);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// SELECCION
			try{
				centroEsperado.centroDao.seleccionarCentro(centro);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// EDICCION
			try{
				centroEsperado.centroDao.editarCentro(centro);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

			// ELIMINACION
			try{
				centroEsperado.centroDao.eliminarCentro(centro);
			}catch (NullPointerException e) {
				assertTrue(true);
			}

		} catch (SQLException e) {
			fail("HA TIRADO UNA EXCEPCION");
		}
	}
}
