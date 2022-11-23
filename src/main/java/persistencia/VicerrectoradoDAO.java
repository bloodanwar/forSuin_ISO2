package persistencia;

import java.sql.SQLException;
import java.util.Vector;

import negocio.entities.Vicerrectorado;

public class VicerrectoradoDAO {
	public Vicerrectorado seleccionarVicerrectorado(Vicerrectorado vicerrectorado) throws SQLException {
		Vector datosVicer = GestorBD.getInstancia().select("SELECT * FROM vicerrectorado WHERE dni='"+vicerrectorado.getDni()+"'");
		
		datosVicer = (Vector) datosVicer.get(0);
		
		String dni=(String) datosVicer.get(0);
		String nombre=(String) datosVicer.get(1);
		String apellidos= (String) datosVicer.get(2);
		boolean jefe=(Boolean) datosVicer.get(3);
		
		return new Vicerrectorado(dni, nombre, apellidos, jefe);
	}
}
