package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class CentroDAO {
	
	//Centro(nombre, localizacion, atributo)
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevoCentro(Centro centro) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		return GestorBD.getInstancia().insert("INSERT INTO centro (nombre, localizacion, atributo, fechaCreacion, fechaActualizacion) VALUES ('"
				+ centro.getNombre()+"', '"
				+ centro.getLocalizacion()+"', "
				+ centro.getAtributo()+", '"
				+ dateFormat.format(fechaCreacion)+"', '"
				+ dateFormat.format(fechaActualizacion)+"')");
	}

	public Centro seleccionarCentro(Centro centro) throws SQLException {
		Vector datosCentro = GestorBD.getInstancia().select("SELECT * FROM centro WHERE nombre='"+centro.getNombre()+"'");
		datosCentro = (Vector) datosCentro.get(0);

		String nombre = (String) datosCentro.get(0);
		String localizacion= (String) datosCentro.get(1);
		int atributo = (Integer) datosCentro.get(2);
		
		Centro centroDevolver = new Centro(nombre, localizacion, atributo);
		
		//faltaria obtener la plantilla, y los cursos

		return centroDevolver;
	}

	public int editarCentro(Centro centro) throws SQLException{
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE centro SET "
				+ "nombre='" + centro.getNombre() + "', "
				+ "localizacion='" + centro.getLocalizacion() + "', "
				+ "atributo=" + centro.getAtributo() + ", "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE nombre='"+centro.getNombre()+"'");
	}

	public int eliminarCentro(Centro centro) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM centro WHERE nombre='"+centro.getNombre()+"'");
	}
	
	public List<Centro> listarCentros() throws SQLException {
		Vector listaCursosDatos = GestorBD.getInstancia().select("SELECT * FROM centro");
		
		List<Centro> centros = new ArrayList<>();
		
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