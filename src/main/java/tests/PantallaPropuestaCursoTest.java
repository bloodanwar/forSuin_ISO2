package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JTextField;

import org.junit.BeforeClass;
import org.junit.Test;

import negocio.entities.CursoPropio;
import negocio.entities.ProfesorUCLM;
import presentacion.PantallaPropuestaCurso;

public class PantallaPropuestaCursoTest {
	
	private static PantallaPropuestaCurso pantalla = null;
	
	@BeforeClass
	public static void init() {
		ProfesorUCLM director = new ProfesorUCLM();
		CursoPropio curso = new CursoPropio();
		int action = 0;
		pantalla = new PantallaPropuestaCurso(director, curso, action);
	}
	
	@Test
	public void PantantallaPropuestaCursoTest() {
		pantalla.getButton().doClick();
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
