package negocio.controllers;

public class MatriculaException extends Exception {
	static class MatriculaNoCreadaException extends Exception {
		public MatriculaNoCreadaException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	static class MatriculaNoEditadaException extends Exception {
		public MatriculaNoEditadaException(String errorMessage) {
	        super(errorMessage);
	    }
	}
}
