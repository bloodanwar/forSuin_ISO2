package pruebas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal {

	public static void main(String[] args) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		System.out.println(dateFormat.format(fechaCreacion));
	}
}