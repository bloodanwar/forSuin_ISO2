package persistencia;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class CentroDAO {
	
	//Centro(nombre, localizacion, atributo)
	
	public int crearNuevoCentro(Centro centro) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		return GestorBD.getInstancia().insert("INSERT INTO centro (nombre, localizacion, atributo, fechaCreacion, fechaActualizacion) VALUES ('"
				+ centro.getNombre()+"', "
				+ centro.getLocalizacion()+", "
				+ centro.getAtributo()+", "
				+ fechaCreacion+", "
				+ fechaActualizacion+")");
	}

	public Centro seleccionarCentro(Centro centro) throws SQLException {
		Vector datosCentro = GestorBD.getInstancia().select("SELECT * FROM centro WHERE id='"+centro.getNombre()+"'");
		datosCentro = (Vector) datosCentro.get(0);

		String nombre = (String) datosCentro.get(0);
		String localizacion= (String) datosCentro.get(1);
		int atributo = (Integer) datosCentro.get(2);
		
		Centro centroDevolver = new Centro(nombre, localizacion, atributo);
		
		List centros = new Centro().centroDao.listarCentros(centroDevolver);
		centroDevolver.centroDao=(CentroDAO) centros;
		
		return centroDevolver;
	}

	public int editarCentro(Centro centro) throws SQLException{
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE centro SET "
				+ "nombre='" + centro.getNombre() + "', "
				+ "localizacion=" + centro.getLocalizacion() + ", "
				+ "atributo=" + centro.getAtributo() + ", "
				+ "fechaActualizacion=" + fechaActualizacion
				+ " WHERE nombre='"+centro.getNombre());
	}

	public int eliminarCentro(Centro centro) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM centro WHERE nombre='"+centro.getNombre()+"'");
	}
	
	public List<Centro> listarCentros(Centro centroDevolver) throws SQLException {
		Vector listaCursosDatos = GestorBD.getInstancia().select("SELECT * FROM centro");
		
		List<Centro> centros = null;
		
		for (int i=0; i<listaCursosDatos.size(); i++) {
			Vector lCursosDatosTemp = (Vector) listaCursosDatos.get(i);

			String nombre = (String) lCursosDatosTemp.get(0);
			String localizacion = (String) lCursosDatosTemp.get(1);
			int atributo = (Integer) lCursosDatosTemp.get(2);
			
			centros.add(new Centro(nombre, localizacion, atributo));
		}
		
		return centros;
	}
}