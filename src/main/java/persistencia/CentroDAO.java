package persistencia;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class CentroDAO {
	
	//Centro(nombre, localizacion, atributo)
	
	public int crearNuevoCentro(Centro centro) {
		//TODO - Lo hace Miriam
	}

	public Centro seleccionarCentro(Centro centro) {
		//TODO - Lo hace Miriam
	}

	public Centro editarCentro(Centro centro) {
		//TODO - Lo hace Miriam
	}

	public int eliminarCentro(Centro centro) {
		//TODO - Lo hace Miriam
	}
	
	public List<Centro> listarCentros() throws SQLException {
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