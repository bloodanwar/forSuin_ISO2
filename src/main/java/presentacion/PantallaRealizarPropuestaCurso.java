package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negocio.entities.CursoPropio;
import negocio.entities.Materia;

public class PantallaRealizarPropuestaCurso extends JFrame {

	// Variables generales
	private JButton button;
	private JLabel label;
	private JScrollPane scrollPanel;    
	private JTextField tituloCurso, fechaInicio, fechaFinal, requisitoCurso, nombreMateria;
	private JTextArea descripcionCurso;
	private JList secretariosLista, centrosLista, categoriasLista, materiasLista, responsablesLista, profesoresMateriaLista;
	private JComboBox edicionCurso, tasaMatricula, ectsCurso, horas;  

	// Listas
	private String[] categorias = {"Másteres de Formación Permanente", "Especialistas", "Expertos", 
			"Cursos Universitarios de Formación Avnazada", "Cursos de Formación Continua", 
			"Microcredenciales", "Actividades formativas de corta duración", "Cursos de Verano y Extensión Universitaria", "Formación de Mayores" }; 

	private String[] centros = {"Centro 1", "Centro 2", "Centro 3"}; // Provisional -- Se lee de la base de datos
	private String[] profesores= {"Profe 1", "Profe 2", "Profe 3"}; // Provisional -- Se lee de la base de datos
	private DefaultListModel materias = new DefaultListModel(); 
	private int idMateria = 0;

	// Objetos
	private Materia materia;
	private CursoPropio curso;

	public PantallaRealizarPropuestaCurso () {
		initLayout();
		basicLayout();
		enseñanzasLayout();
		materiasLayout();
		botonesLayout();
	}

	private void initLayout() {	
		setLayout(null);
		setBounds(10, 10, 800,800);
		setTitle("Realizar propuesta curso");
        setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	private void basicLayout() {
		// Titulo
		label = new JLabel("Titulo de curso");
		label.setBounds(10,10,400,30);
		add(label);

		tituloCurso = new JTextField();
		tituloCurso.setBounds(10,40,400,30);
		add(tituloCurso);

		// Descripción
		label = new JLabel("Descripción de curso");
		label.setBounds(10,90,400,30);
		add(label);

		descripcionCurso = new JTextArea();
		descripcionCurso.setLineWrap(true);
		scrollPanel = new JScrollPane(descripcionCurso);
		scrollPanel.setBounds(10,120,400,200);
		add(scrollPanel);

		// Profesor Secretario
		label = new JLabel("Profesor secretario");
		label.setBounds(10,330,400,30);
		add(label);

		secretariosLista = new JList(profesores);
		secretariosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(secretariosLista);
		scrollPanel.setBounds(10, 360, 400, 100);
		add(scrollPanel);

		// Fecha Inicio
		label = new JLabel("Fecha inicio (DD / MM / AAAA)");
		label.setBounds(10,480,200,30);
		add(label);

		fechaInicio = new JTextField();
		fechaInicio.setBounds(10,510,180,30);
		add(fechaInicio);

		// Fecha Final
		label = new JLabel("Fecha final (DD / MM / AAAA)");
		label.setBounds(200,480,200,30);
		add(label);

		fechaFinal = new JTextField();
		fechaFinal.setBounds(200,510,180,30);
		add(fechaFinal);

		// Edicion de curso
		label = new JLabel("Edicion de curso");
		label.setBounds(10,560,200,30);
		add(label);

		edicionCurso = new JComboBox();
		edicionCurso.setBounds(10,590,180,30);

		for (int i = 1; i <= 100; i++) {
			edicionCurso.addItem(i);
		}

		add(edicionCurso);


		// Tasa matricula
		label = new JLabel("Tasa Matricula");
		label.setBounds(200,560,200,30);
		add(label);

		tasaMatricula = new JComboBox();
		tasaMatricula.setBounds(200,590,180,30);

		for (int i = 1; i <= 100; i++) {
			tasaMatricula.addItem(i);
		}

		add(tasaMatricula);

		// Centro en el que se imparte
		label = new JLabel("Centro en el que se imparte");
		label.setBounds(10,640,400,30);
		add(label);

		centrosLista = new JList(centros);
		centrosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(centrosLista);
		scrollPanel.setBounds(10, 670, 400, 100);
		add(scrollPanel);

	}

	private void enseñanzasLayout() {
		// Categoria 
		label = new JLabel("Categoria");
		label.setBounds(500,10,400,30);
		add(label);

		categoriasLista = new JList(categorias);
		categoriasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(categoriasLista);
		scrollPanel.setBounds(500, 40, 400, 200);
		add(scrollPanel);


		// Requistio -- Provisional -> Depende de categoria
		label = new JLabel("Requsito");
		label.setBounds(500,260,200,30);
		add(label);

		requisitoCurso = new JTextField();
		requisitoCurso.setBounds(500,290,200,30);
		requisitoCurso.setEnabled(false);
		add(requisitoCurso);


		// ECTS -- Provisional -> Depende de categoria
		label = new JLabel("ECTS");
		label.setBounds(500,340,200,30);
		add(label);

		ectsCurso = new JComboBox();
		ectsCurso.setBounds(500,370,200,30);
		add(ectsCurso);


		categoriasLista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int index = categoriasLista.getSelectedIndex();
				if(index == 0 || index == 1 || index == 2) {
					requisitoCurso.setText("Universidad");

				} else if(index == 3 || index == 4) {
					requisitoCurso.setText("No Universidad");

				}else {
					requisitoCurso.setText("Indiferente");


				}
			}
		});

	}

	private void materiasLayout() {
		// Materias creadas
		label = new JLabel("Lista de materias creadas");
		label.setBounds(1000,10,200,30);
		add(label);

		materiasLista = new JList(materias);
		materiasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(materiasLista);
		scrollPanel.setBounds(1000, 40, 220, 80);
		add(scrollPanel);

		// Nombre de materia
		label = new JLabel("Nombre de materia");
		label.setBounds(1000,130,200,30);
		add(label);

		nombreMateria = new JTextField();
		nombreMateria.setBounds(1000,160,200,30);
		add(nombreMateria);


		// Horas de materia
		label = new JLabel("Horas de materia");
		label.setBounds(1300,130,200,30);
		add(label);

		horas = new JComboBox();
		horas.setBounds(1300,160,200,30);

		for (int i = 1; i <= 100; i++) {
			horas.addItem(i);
		}

		add(horas);


		// Profesor responsable de materia
		label = new JLabel("Profesor responsable de materia");
		label.setBounds(1000,210,200,30);
		add(label);

		responsablesLista = new JList(profesores);
		responsablesLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(responsablesLista);
		scrollPanel.setBounds(1000, 240, 220, 80);
		add(scrollPanel);

		// Profesores que la imparten
		label = new JLabel("Profesores que imparten la materia (Usar ctl para seleccionar varios)");
		label.setBounds(1300,210,400,30);
		add(label);

		profesoresMateriaLista = new JList(profesores);
		profesoresMateriaLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPanel = new JScrollPane(profesoresMateriaLista);
		scrollPanel.setBounds(1300, 240, 220, 80);
		add(scrollPanel);


		// Boton para añadir materia
		button = new JButton("Añadir materia");
		button.setBounds(1300,40,200,30);
		add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { 
				boolean complete = true;

				// COMPROBACION DE DATOS MATERIA
				if (nombreMateria.getText().equals("")) {
					nombreMateria.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					nombreMateria.setBackground(new Color(255, 255, 255));
				}

				if(responsablesLista.isSelectionEmpty()) {
					responsablesLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					responsablesLista.setBackground(new Color(255, 255, 255));
				}
				
				if(centrosLista.isSelectionEmpty()) {
					centrosLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					centrosLista.setBackground(new Color(255, 255, 255));
				}


				if(profesoresMateriaLista.isSelectionEmpty()) {
					profesoresMateriaLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					profesoresMateriaLista.setBackground(new Color(255, 255, 255));
				}

				if (complete) {
					materias.add(idMateria, nombreMateria.getText()); 
					idMateria++;

					// CREAR MATERIA -- materia = new Materia();

					nombreMateria.setText("");
					responsablesLista.clearSelection();
					profesoresMateriaLista.clearSelection();


				}

			}

		});

		label = new JLabel("(Completar campos de abajo antes)");
		label.setBounds(1300,70,300,30);
		add(label);


	}

	private void botonesLayout() {
		// Boton para ir atras
		button = new JButton("Atras");
		button.setBounds(1070,430,200,30);
		add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaGestionarCursos();
				setVisible(false);
			}

		});

		// Boton para enviar propuesta
		button = new JButton("Enviar propuesta");
		button.setBounds(1300,430,200,30);
		add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean complete = true;

				// COMPROBACION DE DATOS CURSO
				if (tituloCurso.getText().equals("")) {
					tituloCurso.setBackground(new Color(222, 129, 122));
					complete = false;
				} else {
					tituloCurso.setBackground(new Color(255, 255, 255));
				}

				if (descripcionCurso.getText().equals("")) {
					descripcionCurso.setBackground(new Color(222, 129, 122));
					complete = false;
				} else {
					descripcionCurso.setBackground(new Color(255, 255, 255));
				}

				if(secretariosLista.isSelectionEmpty()) {
					secretariosLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					secretariosLista.setBackground(new Color(255, 255, 255));
				}
				
				if(centrosLista.isSelectionEmpty()) {
					centrosLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					centrosLista.setBackground(new Color(255, 255, 255));
				}


				if(categoriasLista.isSelectionEmpty()) {
					categoriasLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					categoriasLista.setBackground(new Color(255, 255, 255));
				}

				if(materias.isEmpty()) {
					materiasLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					materiasLista.setBackground(new Color(255, 255, 255));
				}

				if (!complete) return;
				int confirm = JOptionPane.showConfirmDialog(null,"Confirmar enviar propuesta","Confirmación",JOptionPane.YES_NO_OPTION, 1);

				if(confirm == 0)  {
					new PantallaGestionarCursos();
					setVisible(false);

					// CREAR CURSO -- curso = new CursoPropio();
				}

			}

		});

	}





}