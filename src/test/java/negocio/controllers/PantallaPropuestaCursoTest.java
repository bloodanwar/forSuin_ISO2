package negocio.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;
import org.junit.Before;
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

	// ------ VARIABLES -------- //
	private static PantallaPropuestaCurso pantalla = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	JTextField tituloBox;
	JTable secretariosTable;
	JDatePickerImpl fechaInicioCursoBox;
	JDatePickerImpl fechaFinalCursoBox;
	JLabel edicionLbl;
	JTextField tasaBox;
	JList<String> centrosLista;
	JList categoriaLista;
	JTextField requisitoCursoBox;
	JComboBox ectsCursoBox;
	JList materiasLista;


	// ------ FUNCIÓN UTIL PARA TESTS -------- //
	public void setUp(int accion, ProfesorUCLM director,
			ArrayList<Materia> materias, Centro centro, ProfesorUCLM directorCurso, ProfesorUCLM secretario,
			EstadoCurso estado, TipoCurso tipo, String id, String nombre, int ECTS, 
			Date fechaInicio, Date fechaFin, double tasaMatricula, int edicion, String requisitos) {

		// CREACION
		CursoPropio curso = new CursoPropio(id, 
				nombre,
				ECTS, 
				fechaInicio, 
				fechaFin, 
				tasaMatricula, 
				edicion, 
				estado, 
				tipo, 
				centro, 
				secretario, 
				directorCurso, 
				requisitos
				);
		curso.materias = materias;

		pantalla = new PantallaPropuestaCurso(director, curso, accion);

		// DATOS
		pantalla = new PantallaPropuestaCurso(director, curso, accion);
		tituloBox = (JTextField) pantalla.getComponentByName("tituloBox");
		JScrollPane secretariosScroll = (JScrollPane) pantalla.getComponentByName("secretariosTable");
		secretariosTable = (JTable) secretariosScroll.getViewport().getView();
		fechaInicioCursoBox = (JDatePickerImpl) pantalla.getComponentByName("fechaInicioCursoBox");
		fechaFinalCursoBox = (JDatePickerImpl) pantalla.getComponentByName("fechaFinalCursoBox");
		edicionLbl = (JLabel) pantalla.getComponentByName("edicionLbl");
		tasaBox = (JTextField) pantalla.getComponentByName("tasaBox");
		JScrollPane centrosScroll = (JScrollPane) pantalla.getComponentByName("centrosLista");
		centrosLista = (JList) centrosScroll.getViewport().getView();
		JScrollPane categoriasScroll = (JScrollPane) pantalla.getComponentByName("categoriasLista");
		categoriaLista = (JList) categoriasScroll.getViewport().getView();
		requisitoCursoBox = (JTextField) pantalla.getComponentByName("requisitoCursoBox");
		ectsCursoBox = (JComboBox) pantalla.getComponentByName("ectsCursoBox");
		JScrollPane materiasScroll = (JScrollPane) pantalla.getComponentByName("materiasLista");
		materiasLista = (JList) materiasScroll.getViewport().getView();
	}



	// ------ PRUEBAS DE CREACION DE PANTALLA [EACH USE] -------- //

	@Test
	public void PantallaPropuestaCursoTest1() throws ParseException {
		// CREACION
		ArrayList<Materia> materias = new ArrayList();
		materias.add(new Materia("materia", 0, null, null, null));

		setUp(-5,
				new ProfesorUCLM("23568907X"),
				materias,
				new Centro("UCLM TAE"),
				new ProfesorUCLM("23568907X"),
				new ProfesorUCLM("23568907X"),
				EstadoCurso.PROPUESTO,
				TipoCurso.MASTER,
				"id",
				"nombre",
				-15,
				dateFormat.parse("01-01-2000"),
				dateFormat.parse("02-02-2000"),
				-25.0,
				-35,
				"requsito");

		// TESTEO DE DATOS 
		assertEquals(0, materiasLista.getModel().getSize());
		assertEquals(0, centrosLista.getSelectedIndex());
		assertEquals(0, secretariosTable.getSelectedRow());
		assertEquals(0, categoriaLista.getSelectedIndex());
		assertEquals("", tituloBox.getText());
		assertEquals(0, ectsCursoBox.getSelectedIndex());
		assertEquals("", fechaInicioCursoBox.getJFormattedTextField().getText());
		assertEquals("", fechaFinalCursoBox.getJFormattedTextField().getText());
		assertEquals("", tasaBox.getText());
		assertEquals("Edicion de curso: 1", edicionLbl.getText());
		assertEquals("", requisitoCursoBox.getText());

		// TESTEO DE VOLVER HACIA ATRAS
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}

	@Test
	public void PantallaPropuestaCursoTest2() throws ParseException {
		// CREACION
		ArrayList<Materia> materias = new ArrayList();
		materias.add(null);

		setUp(1,
				null,
				materias,
				null,
				null,
				null,
				EstadoCurso.VALIDADO,
				TipoCurso.EXPERTO,
				"",
				"",
				20,
				null,
				null,
				30.0,
				40,
				"");

		// TESTEO DE DATOS
		assertEquals(0, materiasLista.getModel().getSize());
		assertEquals(0, centrosLista.getSelectedIndex());
		assertEquals(0, secretariosTable.getSelectedRow());
		assertEquals(2, categoriaLista.getSelectedIndex());
		assertEquals("", tituloBox.getText());
		assertEquals("20", ectsCursoBox.getSelectedItem().toString());
		assertEquals("", fechaInicioCursoBox.getJFormattedTextField().getText());
		assertEquals("", fechaFinalCursoBox.getJFormattedTextField().getText());
		assertEquals("30.0", tasaBox.getText());
		assertEquals("Edicion de curso: 40", edicionLbl.getText());
		assertEquals("", requisitoCursoBox.getText());

		// TESTEO DE VOLVER HACIA ATRAS
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}

	@Test
	public void PantallaPropuestaCursoTest3() throws ParseException {
		// CREACION
		ArrayList<Materia> materias = new ArrayList();
		materias.add(new Materia("materia", 0, null, null, null));
		materias.add(null);

		setUp(10,
				null,
				materias,
				null,
				null,
				null,
				EstadoCurso.PROPUESTA_RECHAZADA,
				TipoCurso.ESPECIALISTA,
				null,
				null,
				0,
				dateFormat.parse("01-01-2000"),
				null,
				0.0,
				0,
				null);

		// TESTEO DE DATOS
		for (int i = 0; i < materiasLista.getModel().getSize(); i++) {
			String materiaEsperada = materias.get(i).getNombre();
			String materiaObenida = (String) materiasLista.getModel().getElementAt(i);
			assertEquals(materiaEsperada, materiaObenida);
		}	
		assertEquals(1, materiasLista.getModel().getSize());
		assertEquals(0, centrosLista.getSelectedIndex());
		assertEquals(0, secretariosTable.getSelectedRow());
		assertEquals(1, categoriaLista.getSelectedIndex());
		assertEquals("", tituloBox.getText());
		assertEquals("0", ectsCursoBox.getSelectedItem().toString());
		assertEquals("01-01-2000", fechaInicioCursoBox.getJFormattedTextField().getText());
		assertEquals("", fechaFinalCursoBox.getJFormattedTextField().getText());
		assertEquals("0.0", tasaBox.getText());
		assertEquals("Edicion de curso: 1", edicionLbl.getText());
		assertEquals("", requisitoCursoBox.getText());

		// TESTEO DE VOLVER HACIA ATRAS
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}

	@Test
	public void PantallaPropuestaCursoTest4() throws ParseException {
		// CREACION
		ArrayList<Materia> materias = new ArrayList();
		materias.add(null);
		materias.add(new Materia("materia", 0, null, null, null));

		setUp(0,
				new ProfesorUCLM("23568907X"),
				materias,
				new Centro("UCLM TAL"),
				null,
				new ProfesorUCLM("23568907X"),
				EstadoCurso.EN_MATRICULACION,
				TipoCurso.FORMACION_AVANZADA,
				"id",
				null,
				20,
				null,
				dateFormat.parse("02-02-2000"),
				-25.0,
				0,
				null);

		// TESTEO DE DATOS 
		assertEquals(0, materiasLista.getModel().getSize());
		assertEquals(0, centrosLista.getSelectedIndex());
		assertEquals(0, secretariosTable.getSelectedRow());
		assertEquals(0, categoriaLista.getSelectedIndex());
		assertEquals("", tituloBox.getText());
		assertEquals(0, ectsCursoBox.getSelectedIndex());
		assertEquals("", fechaInicioCursoBox.getJFormattedTextField().getText());
		assertEquals("", fechaFinalCursoBox.getJFormattedTextField().getText());
		assertEquals("", tasaBox.getText());
		assertEquals("Edicion de curso: 1", edicionLbl.getText());
		assertEquals("", requisitoCursoBox.getText());

		// TESTEO DE VOLVER HACIA ATRAS
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}

	@Test
	public void PantallaPropuestaCursoTest5() throws ParseException {
		// CREACION
		ArrayList<Materia> materias = new ArrayList();
		materias.add(new Materia("materia", 0, null, null, null));

		setUp(2,
				new ProfesorUCLM("23568907X"),
				materias,
				new Centro("UCLM TAL"),
				null,
				null,
				EstadoCurso.EN_IMPARTIZICION,
				TipoCurso.FORMACION_CONTINUA,
				"",
				"",
				0,
				dateFormat.parse("01-01-2000"),
				null,
				30.0,
				40,
				"");

		// TESTEO DE DATOS 
		for (int i = 0; i < materiasLista.getModel().getSize(); i++) {
			String materiaEsperada = materias.get(i).getNombre();
			String materiaObenida = (String) materiasLista.getModel().getElementAt(i);
			assertEquals(materiaEsperada, materiaObenida);
		}	
		assertEquals(1, materiasLista.getModel().getSize());
		assertEquals("UCLM TAL", centrosLista.getSelectedValue());
		assertEquals(0, secretariosTable.getSelectedRow());
		assertEquals(4, categoriaLista.getSelectedIndex());
		assertEquals("", tituloBox.getText());
		assertEquals("0", ectsCursoBox.getSelectedItem().toString());
		assertEquals("01-01-2000", fechaInicioCursoBox.getJFormattedTextField().getText());
		assertEquals("", fechaFinalCursoBox.getJFormattedTextField().getText());
		assertEquals("30.0", tasaBox.getText());
		assertEquals("Edicion de curso: 41", edicionLbl.getText());
		assertEquals("", requisitoCursoBox.getText());

		// TESTEO DE VOLVER HACIA ATRAS
		pantalla.getAtrasBto().doClick();
		assertFalse(pantalla.isVisible());
	}

	// ------ PRUEBAS DE CREACION DE CURSO [COBERTURA DE DECISIÓN] -------- //

	@Test
	public void EnviarPropuestaTest1() throws SQLException {
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


		curso.cursoPropioDao.crearNuevoCurso(curso);


		JTextField titulo = (JTextField) pantalla.getComponentByName("tituloBox");
		assertEquals(nombre, titulo.getText());

		pantalla.getSendBto().doClick();


		curso.cursoPropioDao.eliminarCursoPropio(curso);

	}


	// ------ PRUEBAS DE FUNCION TESTS -------- //

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
