package negocio.controllers;

public class CursoException extends Exception{
	static class CursoErroneoException extends Exception {
		public CursoErroneoException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	static class CursoNoCreadoException extends Exception {
		public CursoNoCreadoException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	static class CursoNoEditadoException extends Exception {
		public CursoNoEditadoException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	static class CursoNoEliminadoException extends Exception {
		public CursoNoEliminadoException(String errorMessage) {
	        super(errorMessage);
	    }
	}
}
