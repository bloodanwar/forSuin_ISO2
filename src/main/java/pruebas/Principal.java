package pruebas;

import negocio.entities.ProfesorUCLM;
import persistencia.GestorBD;

public class Principal {

	public static void main(String[] args) throws Exception {
		ProfesorUCLM profe = new ProfesorUCLM("12457890Y");
		profe  = profe.profesorUCLMDao.seleccionarProfesorUCLM(profe);
		System.out.println(profe.isDoctor());
	}
}