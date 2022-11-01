package negocio.entities;

import persistencia.*;

public class ProfesorExterno extends Profesor {

	ProfesorExternoDAO profesorExternoDao;
	private String titulacion;

	public ProfesorExterno() {
		super("", "", "", false);
		profesorExternoDao = new ProfesorExternoDAO();
	}

	public ProfesorExterno(String dni) {
		super(dni, "", "", false);
		profesorExternoDao = new ProfesorExternoDAO();
	}

	public ProfesorExterno(String dni, String nombre, String apellidos, boolean doctor, String titulacion) {
		super(dni, nombre, apellidos, doctor);
		this.titulacion = titulacion;
		profesorExternoDao = new ProfesorExternoDAO();
	}

	public String getTitulacion() {
		return titulacion;
	} 
}