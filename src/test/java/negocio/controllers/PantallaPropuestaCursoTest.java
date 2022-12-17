package negocio.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Materia;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;
import presentacion.PantallaGestionarCursos;
import presentacion.PantallaPropuestaCurso;

public class PantallaPropuestaCursoTest {
	
	private static PantallaPropuestaCurso pantalla = null;
	
	@Test
	public void PantallaPropuestaCursoTest1() {
		int action = 0;
		ProfesorUCLM director = new ProfesorUCLM("23568907X");
		
		String id = "id";
		String nombre = "nombre";
		int ECTS = 20;
		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		int tasaMatricula = 10;
		int edicion = 0;
		EstadoCurso estado = EstadoCurso.EN_IMPARTIZICION;
		TipoCurso tipo = TipoCurso.CORTA_DURACION;
		Centro centro = new Centro("UCLM TAE");
		ProfesorUCLM secretario = new ProfesorUCLM("23568907X");
		ProfesorUCLM director1 = new ProfesorUCLM("23568907X");
		String requisitos = "requisito";
		
		CursoPropio curso = new CursoPropio(id, nombre,ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estado, tipo, centro, secretario, director1, requisitos);
		curso.materias = new ArrayList<Materia>();
		curso.materias.add(new Materia("materia", 20, new Date(), new Date(), new ProfesorUCLM()));
		
		pantalla = new PantallaPropuestaCurso(director, curso, action);
		
		JTextField titulo = (JTextField) pantalla.getComponentByName("tituloBox");
		assertEquals(nombre, titulo.getText());
		
		pantalla.getSendBto().doClick();

	}
	
	@Test
	public void PantallaPropuestaCursoTest2() {
		int action = 1;
		ProfesorUCLM director = new ProfesorUCLM("23568907X");
		
		String id = "id";
		String nombre = "nombre";
		int ECTS = 20;
		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		int tasaMatricula = 10;
		int edicion = 0;
		EstadoCurso estado = EstadoCurso.EN_IMPARTIZICION;
		TipoCurso tipo = TipoCurso.CORTA_DURACION;
		Centro centro = new Centro("UCLM TAL");
		ProfesorUCLM secretario = new ProfesorUCLM("23568907X");
		ProfesorUCLM director1 = new ProfesorUCLM("23568907X");
		String requisitos = "requisito";
		
		CursoPropio curso = new CursoPropio(id, nombre,ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estado, tipo, centro, secretario, director1, requisitos);
		curso.materias = new ArrayList<Materia>();
		curso.materias.add(new Materia("materia", 20, new Date(), new Date(), new ProfesorUCLM("23568907X")));
		
		pantalla = new PantallaPropuestaCurso(director, curso, action);
		
		try {
			curso.cursoPropioDao.crearNuevoCurso(curso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTextField titulo = (JTextField) pantalla.getComponentByName("tituloBox");
		assertEquals(nombre, titulo.getText());
		
		pantalla.getSendBto().doClick();
		
		try {
			curso.cursoPropioDao.eliminarCursoPropio(curso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void AtrasBtoTest() {
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}
	
	
	@Test
	public void testTexts1() {
		JTextField argumento = new JTextField("hola");
		
		assertTrue(pantalla.testTexts(argumento));
		
		Color colorObenido = argumento.getBackground();
		Color colorEsperado = new Color(255, 255, 255);
		
		assertTrue(colorEsperado.equals(colorObenido));
	}
	
	@Test
	public void testTexts2() {
		JTextField argumento = new JTextField("");
		
		assertFalse(pantalla.testTexts(argumento));

		Color colorObenido = argumento.getBackground();
		Color colorEsperado = new Color(222, 129, 122);
		
		assertTrue(colorEsperado.equals(colorObenido));
	}
}
