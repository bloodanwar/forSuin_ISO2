package negocio.controllers;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import negocio.controllers.CursoException.CursoErroneoException;
import negocio.controllers.CursoException.CursoNoEditadoException;
import negocio.controllers.CursoException.CursoNoEliminadoException;
import negocio.controllers.CursoException.CursoNoExisteException;
import negocio.controllers.MatriculaException.*;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Estudiante;
import negocio.entities.Materia;


public class GestorMatriculacionTest {
	private GestorMatriculacion g = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Before
	public void setUp() throws Exception{
		g= new GestorMatriculacion();
	}
	
	@Test
	public void cp1()throws Exception{ //Control que deberia no soltar excepciones y hacerlo todo bien.
		String id = "10";
		int edicion = 134;
		String dni = "98653134I";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		return;
		
	}
	
	@Test
	public void cp2()throws Exception{ //Deberia soltar error por curso
		String id = "10";
		int edicion = -1;
		String dni = "98653134I";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		return;
		
	}
	
	@Test
	public void cp3()throws Exception{ //Deberia soltar error por curso
		String id = "10";
		int edicion = 0;
		String dni = "12345678A";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		return;
		
	}
	
	@Test
	public void cp4()throws Exception{//Deberia soltar error por alumno
		String id = "test";
		int edicion = 1;
		String dni = "98653134I";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(curso, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(curso, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(curso, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		return;
	}
	
	@Test
	public void cp5()throws Exception{ //Deberia soltar error por curso
		String id = "test";
		int edicion = 2;
		String dni = "98653134I";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(null, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(null, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(null, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		return;

	}

	@Test
	public void cp6()throws Exception{ //Deberia soltar error por curso y estudiante
		String id = "test"; 
		int edicion = 3;
		String dni = "98653134I";
		
		CursoPropio curso = new CursoPropio(id, edicion);
		Estudiante estudiante = new Estudiante(dni);
		
		try{g.realizarMatriculacion(curso, estudiante);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoCreadaException e){ System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoMatricula(null, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTarjeta(null, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		try {g.realizarPagoTransferencia(null, null);
		}catch(MatriculaErroneaException e){ System.out.println(e);
		}catch(MatriculaNoEditadaException e ) {System.out.println(e);
		}catch(MatriculaNoExisteException e ) {System.out.println(e);
		}catch(SQLException e){ System.out.println(e);
		}catch(Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());	
		}
		
		return;
		
	}
}