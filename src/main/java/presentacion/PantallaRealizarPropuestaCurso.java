package presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Materia;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;

public class PantallaRealizarPropuestaCurso extends JFrame {

	// Variables generales
	private JButton button;
	private JLabel label, labelRequisito;
	private JPanel mainPanel, panel;
	private JScrollPane scrollPanel;    
	private JTextField tituloCurso, requisitoCurso, nombreMateria;
	private JTable secretariosTable, responsablesTable;
	private JList centrosLista, categoriasLista, materiasLista, profesoresMateriaLista;
	private JComboBox<Integer> tasaMatricula, ectsCurso, horas;
	private JComboBox diaInicio, mesInicio, anoInicio, diaFinal, mesFinal, anoFinal, diaInicioMateria, mesInicioMateria, anoInicioMateria, diaFinalMateria, mesFinalMateria, anoFinalMateria;  


	// Listas
	private String[] categorias = {"Másteres de Formación Permanente", "Especialistas", "Expertos", 
			"Cursos Universitarios de Formación Avnazada", "Cursos de Formación Continua", 
			"Microcredenciales", "Actividades formativas de corta duración", "Cursos de Verano y Extensión Universitaria", "Formación de Mayores" }; 

	private String[] centros = {"Centro 1", "Centro 2", "Centro 3"}; // PROVISIONAL --
	private DefaultListModel materias = new DefaultListModel(); 
	private int idMateria = 0;
	private DefaultTableModel profesores = new DefaultTableModel(); 


	// Objetos
	private Materia materia;
	private List<Materia> materiasGuardadas = new ArrayList();
	private CursoPropio curso;

	public PantallaRealizarPropuestaCurso (ProfesorUCLM director) {
		addProfesores();
		initLayout();
		basicLayout();
		enseñanzasLayout();
		materiasLayout();
		botonesLayout(director);

		scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setBounds(0, 0, 0,0);
		getContentPane().add(scrollPanel);

	}

	private void addProfesores() { 

		// ESPERANDO BBDD
		//		Profesor profesor = new Profesor();
		//		List profesoresLista = null;
		//		try {
		//			profesoresLista = profesor.profesorDao.listarProfesores();
		//		} catch (SQLException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		Iterator ite = profesoresLista.iterator();
		//		while(ite.hasNext()) {
		//			System.out.println(ite.next());
		//		}

		// PROVISIONAL -- 
		profesores.addColumn("Nombre");
		profesores.addColumn("Categoria");
		profesores.addColumn("Doctor");

		profesores.insertRow(0, new Object[] { "Profe 1", "Cat 1", "True" });
		profesores.insertRow(1, new Object[] { "Profe 2", "Cat 1", "True"});
		profesores.insertRow(2, new Object[] { "Profe 3", "Cat 2", "False" });
	}

	private void initLayout() {	
		getContentPane().setLayout(new BorderLayout());
		setBounds(10, 10, 800,600);
		setTitle("Realizar propuesta curso");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(700, 1600));

	}

	private void basicLayout() {
		// Titulo		
		label = new JLabel("Titulo de curso");
		label.setBounds(10,10,400,30);
		mainPanel.add(label);

		tituloCurso = new JTextField();
		tituloCurso.setBounds(10,40,400,30);
		mainPanel.add(tituloCurso);

		// Profesor Secretario
		label = new JLabel("Profesor secretario");
		label.setBounds(10,96,400,30);
		mainPanel.add(label);

		secretariosTable = new JTable(profesores) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};
		secretariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(secretariosTable);
		scrollPanel.setBounds(10, 126, 400, 200);
		mainPanel.add(scrollPanel);

		// Fecha Inicio
		label = new JLabel("Fecha inicio (DD/MM/AAAA)");
		label.setBounds(10,342,200,30);
		mainPanel.add(label);

		diaInicio = new JComboBox();
		diaInicio.setBounds(10,372,40,30);
		mainPanel.add(diaInicio);

		for (int i = 1; i<32; i++) diaInicio.addItem(i);

		mesInicio = new JComboBox();
		mesInicio.setBounds(60,372,40,30);
		mainPanel.add(mesInicio);

		for (int i = 1; i<13; i++) mesInicio.addItem(i);

		anoInicio = new JComboBox();
		anoInicio.setBounds(110,372,80,30);
		mainPanel.add(anoInicio);

		for (int i = 2022; i<2100; i++) anoInicio.addItem(i);


		// Fecha Final
		label = new JLabel("Fecha final (DD/MM/AAAA)");
		label.setBounds(220,342,200,30);
		mainPanel.add(label);

		diaFinal = new JComboBox();
		diaFinal.setBounds(220,372,40,30);
		mainPanel.add(diaFinal);

		for (int i = 1; i<32; i++) diaFinal.addItem(i);

		mesFinal = new JComboBox();
		mesFinal.setBounds(270,372,40,30);
		mainPanel.add(mesFinal);

		for (int i = 1; i<13; i++) mesFinal.addItem(i);

		anoFinal = new JComboBox();
		anoFinal.setBounds(320,372,80,30);
		mainPanel.add(anoFinal);

		for (int i = 2022; i<2100; i++) anoFinal.addItem(i);


		// Edicion de curso
		label = new JLabel("Edicion de curso: 1");
		label.setBounds(450,40,200,30);
		mainPanel.add(label);

		// Tasa matricula
		label = new JLabel("Tasa Matricula");
		label.setBounds(450,90,200,30);
		mainPanel.add(label);

		tasaMatricula = new JComboBox();
		tasaMatricula.setBounds(450,131,180,30);

		for (int i = 1; i <= 100; i++) {
			tasaMatricula.addItem(i);
		}

		mainPanel.add(tasaMatricula);

		// Centro en el que se imparte
		label = new JLabel("Centro en el que se imparte");
		label.setBounds(10,413,400,30);
		mainPanel.add(label);

		// ESPERANDO BBDD --
		// Centro centro = new Centro();
		// centro.centroDao.listarCentros(centro).toArray();

		centrosLista = new JList(centros);
		centrosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(centrosLista);
		scrollPanel.setBounds(10, 440, 400, 200);
		mainPanel.add(scrollPanel);

	}

	private void enseñanzasLayout() {
		// Categoria 
		label = new JLabel("Categoria");
		label.setBounds(10,651,400,30);
		mainPanel.add(label);

		categoriasLista = new JList(categorias);
		categoriasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(categoriasLista);
		scrollPanel.setBounds(10, 681, 400, 200);
		mainPanel.add(scrollPanel);


		// Requistio -- Provisional -> Depende de categoria
		labelRequisito = new JLabel("Requsito");
		labelRequisito.setBounds(450,681,200,30);
		mainPanel.add(labelRequisito);

		requisitoCurso = new JTextField();
		requisitoCurso.setBounds(450,711,200,30);
		requisitoCurso.setEnabled(false);
		mainPanel.add(requisitoCurso);


		// ECTS -- Provisional -> Depende de categoria
		label = new JLabel("ECTS");
		label.setBounds(450,761,200,30);
		mainPanel.add(label);

		ectsCurso = new JComboBox<Integer>();
		ectsCurso.setBounds(450,791,200,30);
		ectsCurso.setEnabled(false);
		mainPanel.add(ectsCurso);


		categoriasLista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = categoriasLista.getSelectedIndex();
				int requisito = -1;				
				ectsCurso.removeAllItems();
				ectsCurso.setEnabled(true);

				switch (index) {
				case 0:
					requisito = 0;

					ectsCurso.addItem(60);
					ectsCurso.addItem(90);
					ectsCurso.addItem(120);

					break;

				case 1:
					requisito = 1;
					for (int i = 30; i<60; i++) ectsCurso.addItem(i);
					break;
				case 2:
					requisito = 0;
					for (int i = 15; i<30; i++) ectsCurso.addItem(i);
					break;
				case 3:
					for (int i = 15; i<31; i++) ectsCurso.addItem(i);
					break;
				case 4:
					for (int i = 3; i<15; i++) ectsCurso.addItem(i);
					break;
				case 5:
					for (int i = 2; i<15; i++) ectsCurso.addItem(i);
					break;
				case 6:
					for (int i = 1; i<3; i++) ectsCurso.addItem(i);
					break;
				default:
					ectsCurso.setEnabled(false);
					ectsCurso.addItem(0);
					break;
				}

				if(requisito == 0) {
					requisitoCurso.setEnabled(true);
					labelRequisito.setText("Titulación universitaria requerida");

				} else if(requisito == 1) {
					requisitoCurso.setEnabled(true);
					labelRequisito.setText("Cualificación requerida");

				}else {
					labelRequisito.setText("No es necesario requisito");
					requisitoCurso.setEnabled(false);
				}

			}
		});

	}

	private void materiasLayout() {
		// Materias creadas
		label = new JLabel("Lista de materias creadas");
		label.setBounds(10,901,200,30);
		mainPanel.add(label);

		materiasLista = new JList(materias);
		materiasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(materiasLista);
		scrollPanel.setBounds(10, 931, 400, 200);
		mainPanel.add(scrollPanel);

		// Boton para añadir materia
		button = new JButton("Añadir materia");
		button.setBounds(450,931,200,30);
		mainPanel.add(button);

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

				if(responsablesTable.getSelectionModel().isSelectionEmpty()) {
					responsablesTable.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					responsablesTable.setBackground(new Color(255, 255, 255));
				}

				if (complete) {
					materias.addElement(nombreMateria.getText());

					// CREAR MATERIA
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
					Date inicioMateria = null;
					Date finMateria = null;

					try {
						inicioMateria = formatter.parse(diaInicioMateria.getItemAt(diaInicioMateria.getSelectedIndex()) + "-" + mesInicioMateria.getItemAt(mesInicioMateria.getSelectedIndex()) + "-" + anoInicioMateria.getItemAt(anoInicioMateria.getSelectedIndex()));
						finMateria = formatter.parse(diaFinalMateria.getItemAt(diaInicioMateria.getSelectedIndex()) + "-" + mesFinalMateria.getItemAt(mesFinalMateria.getSelectedIndex()) + "-" + anoFinalMateria.getItemAt(anoFinalMateria.getSelectedIndex()));   
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  

					materia = new Materia(nombreMateria.getText(), horas.getItemAt(horas.getSelectedIndex()), inicioMateria, finMateria, null);
					materiasGuardadas.add(materia);

					// Limpiar selección
					nombreMateria.setText("");
					responsablesTable.clearSelection();

					// Prueba
					System.out.println("New Prueba ----");
					Iterator ite = materiasGuardadas.iterator();
					while(ite.hasNext()){
						Materia materia = (Materia) ite.next();
						System.out.println("Nombre: " + materia.getNombre() + ", Horas: " + materia.getHoras() + ", Fecha Inicio: " + materia.getFechaInicio());
					}
				}

			}

		});

		label = new JLabel("(Completar campos de abajo antes)");
		label.setBounds(450,961,300,30);
		mainPanel.add(label);

		// Boton para editar materia
		button = new JButton("Editar materia");
		button.setBounds(450,1021,200,30);
		mainPanel.add(button);

		// Boton para eliminar materia
		button = new JButton("Eliminar materia");
		button.setBounds(450,1071,200,30);
		mainPanel.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (materiasLista.isSelectionEmpty()) return;

				int confirm = JOptionPane.showConfirmDialog(null,"¿Eliminar materia?","Eliminar materia",JOptionPane.YES_NO_OPTION, 1);
				if (confirm == 0) {
					int index = materiasLista.getSelectedIndex();
					materias.remove(index);
					materiasGuardadas.remove(index);
				}
			}
		});

		// Nombre de materia
		label = new JLabel("Nombre de materia");
		label.setBounds(10,1141,180,30);
		mainPanel.add(label);

		nombreMateria = new JTextField();
		nombreMateria.setBounds(10,1171,180,30);
		mainPanel.add(nombreMateria);


		// Horas de materia
		label = new JLabel("Horas de materia");
		label.setBounds(220,1141,180,30);
		mainPanel.add(label);

		horas = new JComboBox();
		horas.setBounds(220,1171,180,30);

		for (int i = 1; i <= 100; i++) {
			horas.addItem(i);
		}

		mainPanel.add(horas);


		// Fecha Inicio Materia
		label = new JLabel("Fecha inicio (DD/MM/AAAA)");
		label.setBounds(10,1210,200,30);
		mainPanel.add(label);

		diaInicioMateria = new JComboBox();
		diaInicioMateria .setBounds(10,1240,40,30);
		mainPanel.add(diaInicioMateria );

		for (int i = 1; i<32; i++) diaInicioMateria .addItem(i);

		mesInicioMateria  = new JComboBox();
		mesInicioMateria .setBounds(60,1240,40,30);
		mainPanel.add(mesInicioMateria );

		for (int i = 1; i<13; i++) mesInicioMateria .addItem(i);

		anoInicioMateria  = new JComboBox();
		anoInicioMateria .setBounds(110,1240,80,30);
		mainPanel.add(anoInicioMateria );

		for (int i = 2022; i<2100; i++) anoInicioMateria .addItem(i);


		// Fecha Final Materia
		label = new JLabel("Fecha final (DD/MM/AAAA)");
		label.setBounds(220,1210,200,30);
		mainPanel.add(label);

		diaFinalMateria = new JComboBox();
		diaFinalMateria.setBounds(220,1240,40,30);
		mainPanel.add(diaFinalMateria);

		for (int i = 1; i<32; i++) diaFinalMateria.addItem(i);

		mesFinalMateria = new JComboBox();
		mesFinalMateria.setBounds(270,1240,40,30);
		mainPanel.add(mesFinalMateria);

		for (int i = 1; i<13; i++) mesFinalMateria.addItem(i);

		anoFinalMateria = new JComboBox();
		anoFinalMateria.setBounds(320,1240,80,30);
		mainPanel.add(anoFinalMateria);

		for (int i = 2022; i<2100; i++) anoFinalMateria.addItem(i);


		// Profesor responsable de materia
		label = new JLabel("Profesor responsable de materia");
		label.setBounds(10,1279,200,30);
		mainPanel.add(label);

		responsablesTable = new JTable(profesores) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};
		responsablesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(responsablesTable);
		scrollPanel.setBounds(10, 1309, 400, 200);
		mainPanel.add(scrollPanel);


	}

	private void botonesLayout(final ProfesorUCLM director) {
		// Boton para ir atras
		button = new JButton("Atras");
		button.setBounds(270,1530,200,30);
		mainPanel.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaGestionarCursos(director);
				setVisible(false);
			}

		});

		// Boton para enviar propuesta
		button = new JButton("Enviar propuesta");
		button.setBounds(500,1530,200,30);
		mainPanel.add(button);

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

				if(secretariosTable.getSelectionModel().isSelectionEmpty()) {
					secretariosTable.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					secretariosTable.setBackground(new Color(255, 255, 255));
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

				if(requisitoCurso.isEnabled() && requisitoCurso.getText().equals("")) {
					requisitoCurso.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					requisitoCurso.setBackground(new Color(255, 255, 255));
				}

				if(materias.isEmpty()) {
					materiasLista.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					materiasLista.setBackground(new Color(255, 255, 255));
				}

				if (!complete) return;
				int confirm = JOptionPane.showConfirmDialog(null,"¿Enviar propuesta?","Enviar propuesta",JOptionPane.YES_NO_OPTION, 1);

				if(confirm == 0)  {
					new PantallaGestionarCursos(director);
					setVisible(false);

					// CREAR CURSO
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
					Date fechaInicio = null;
					Date fechaFin = null;

					try {
						fechaInicio = formatter.parse(diaInicio.getItemAt(diaInicio.getSelectedIndex()) + "-" + mesInicio.getItemAt(mesInicio.getSelectedIndex()) + "-" + anoInicio.getItemAt(anoInicio.getSelectedIndex()));
						fechaFin = formatter.parse(diaFinal.getItemAt(diaFinal.getSelectedIndex()) + "-" + mesFinal.getItemAt(mesFinal.getSelectedIndex()) + "-" + anoFinal.getItemAt(anoFinal.getSelectedIndex()));   
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  

					
					// int id = idMasAlto + 1; // ESPERANDO BBDD --
					
					curso = new CursoPropio(
							null, // id
							tituloCurso.getText(), 
							ectsCurso.getItemAt(ectsCurso.getSelectedIndex()), 
							fechaInicio, 
							fechaFin, 
							tasaMatricula.getItemAt(tasaMatricula.getSelectedIndex()), 
							1, // Edicion
							EstadoCurso.PROPUESTO, 
							tipoCurso(), 
							new Centro((String) centrosLista.getSelectedValue()), // ESPERANDO BBDD --
							new ProfesorUCLM(), // ESPERANDO BBDD --
							new ProfesorUCLM() // ESPERANDO BBDD --
							);

					curso.materias.addAll(materiasGuardadas);

					// ESPERANDO BBDD --
					//					GestorPropuestasCursos gestor = new GestorPropuestasCursos();
					//					gestor.realizarPropuestaCurso(curso);

					System.out.println("Curso creado: " + curso.getNombre());
				}

			}

			private TipoCurso tipoCurso() {
				// TODO Auto-generated method stub
				TipoCurso tipoCurso = null;

				switch (categoriasLista.getSelectedIndex()) {
				case 0:
					tipoCurso = TipoCurso.MASTER;
					break;

				case 1:
					tipoCurso = TipoCurso.ESPECIALISTA;
					break;

				case 2:
					tipoCurso = TipoCurso.EXPERTO;
					break;

				case 3:
					tipoCurso = TipoCurso.FORMACION_AVANZADA;
					break;

				case 4:
					tipoCurso = TipoCurso.FORMACION_CONTINUA;
					break;

				case 5:
					tipoCurso = TipoCurso.MICROCREDENCIALES;
					break;

				case 6:
					tipoCurso = TipoCurso.CORTA_DURACION;
					break;

				case 7:
					tipoCurso = TipoCurso.VERANO;
					break;

				case 8:
					tipoCurso = TipoCurso.MAYORES;
					break;

				default:
					break;
				}

				return tipoCurso;
			}

		});

	}

}