package negocio.controllers;

public class ConsultasException extends Exception {
	static class ProfesorErroneoException extends Exception {
		public ProfesorErroneoException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	static class EstadoCursoErroneoException extends Exception {
		public EstadoCursoErroneoException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	static class TipoCursoErroneoException extends Exception {
		public TipoCursoErroneoException(String errorMessage) {
			super(errorMessage);
		}
	}
}