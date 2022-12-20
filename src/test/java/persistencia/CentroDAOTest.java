package persistencia;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import negocio.entities.Centro;
import negocio.entities.Profesor;

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
			nombre = "";
			localizacion = "";
			atributo = 0;
			centro = new Centro(nombre, localizacion, atributo);
			centroEsperado = centro;

			centro.centroDao.editarCentro(centro);
			centroObtenido = centro.centroDao.seleccionarCentro(centroObtenido);
			assertEquals(centroEsperado.getNombre(), centroObtenido.getNombre());
			assertEquals(centroEsperado.getLocalizacion(), centroObtenido.getLocalizacion());
			assertEquals(centroEsperado.getAtributo(), centroObtenido.getAtributo());


			// PRUEBA DE ELIMINACION PROFESOR
			centro.centroDao.eliminarCentro(centroObtenido);
			centroEsperados.remove(position);
			centrosObtenidos = centro.centroDao.listarCentros();
			assertEquals(centroEsperados.size(), centrosObtenidos.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
