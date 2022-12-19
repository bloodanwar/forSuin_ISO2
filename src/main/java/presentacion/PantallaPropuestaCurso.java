package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import negocio.controllers.GestorConsultas;
import negocio.controllers.GestorMD5;
import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.*;

public class PantallaPropuestaCurso extends JFrame {

	// Variables generales
	private HashMap componentMap;
	private JLabel label;
	private JLabel labelRequisito;
	private JPanel mainPanel;
	private JScrollPane scrollPanel;    
	private JTextField tituloCurso;
	private JTextField requisitoCurso;
	private JTextField nombreMateria;
	private JTextField tasaMatricula;
	private JComboBox<Integer> ectsCurso;
	private JComboBox<Integer> horas;
	private int edicion;

	// Botones
	private JButton atrasBto;
	private JButton sendBto;
	private JButton addMateriaBto;
	private JButton deleteMateriaBto;

	// Fechas
	private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private Properties p = new Properties();
	private JDatePickerImpl fechaInicioCurso;
	private JDatePickerImpl fechaFinalCurso;
	private JDatePickerImpl fechaInicioMateria;
	private JDatePickerImpl fechaFinalMateria;

	// Listas y tablas
	private String[] categorias = {"Másteres de Formación Permanente", "Especialistas", "Expertos", 
			"Cursos Universitarios de Formación Avanazada", "Cursos de Formación Continua", 
			"Microcredenciales", "Actividades formativas de corta duración", "Cursos de Verano y Extensión Universitaria", "Formación de Mayores" }; 

	private TipoCurso[] tipos = {TipoCurso.MASTER, TipoCurso.ESPECIALISTA, TipoCurso.EXPERTO, 
			TipoCurso.FORMACION_AVANZADA, TipoCurso.FORMACION_CONTINUA,
			TipoCurso.MICROCREDENCIALES, TipoCurso.CORTA_DURACION, TipoCurso.VERANO, TipoCurso.MAYORES};

	private DefaultListModel<String> materias = new DefaultListModel<>(); 

	List<Centro> centrosDao = null;
	private DefaultListModel<String> centros = new DefaultListModel<>(); 

	List<Profesor> profesoresDao = null;
	private DefaultTableModel profesores = new DefaultTableModel(); 

	List<ProfesorUCLM> profesoresUCLMDao = null;
	private DefaultTableModel profesoresUCLM = new DefaultTableModel(); 

	private JList<String> centrosLista;
	private JList<String> categoriasLista;
	private JList<String> materiasLista;
	private JTable secretariosTable;
	private JTable responsablesTable;

	// Objetos
	private GestorConsultas gestorConsultas = new GestorConsultas();
	GestorPropuestasCursos gestorPropuestas = new GestorPropuestasCursos();
	private Materia materia;
	private List<Materia> materiasGuardadas = new ArrayList<>();
	private CursoPropio curso = new CursoPropio();

	// Edicion
	private int secretarioEditado = 0;
	private int centroEditado = 0;


	public PantallaPropuestaCurso (ProfesorUCLM director, CursoPropio cursoEditado, int action) { // 0 = Realizar // 1 = Editar // 2 = Edicion
		// DAOS 
		addProfesores(director);
		addProfesoresUCLM(cursoEditado, action, director);
		addCentros(cursoEditado, action, director);
		if (action >= 1) addMaterias(cursoEditado);

		// LAYOUTS
		initLayout();
		basicLayout(cursoEditado, action);
		ensenanzasLayout(cursoEditado, action);
		materiasLayout(cursoEditado);
		botonesLayout(director, cursoEditado, action);

		// MAIN
		scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setBounds(0, 0, 0,0);
		getContentPane().add(scrollPanel);
	}

	private void addProfesores(ProfesorUCLM director) { 
		profesores.addColumn("Nombre");
		profesores.addColumn("Doctor");

		try {
			profesoresDao = gestorConsultas.listarProfesores();
		} catch (SQLException e) {
			errorPopup("Error al cargar profesores", 1, director);
		}

		if(profesoresDao == null) return;
		for (int i = 0; i<profesoresDao.size(); i++) {
			Profesor profesortemp = profesoresDao.get(i);
			profesores.insertRow(i, new Object[] { profesortemp.getNombre(), profesortemp.isDoctor() });
		}
	}

	private void addProfesoresUCLM(CursoPropio cursoEditado, int action, ProfesorUCLM director) {
		profesoresUCLM.addColumn("Nombre");
		profesoresUCLM.addColumn("Categoria");
		profesoresUCLM.addColumn("Doctor");

		try {
			profesoresUCLMDao = gestorConsultas.listarProfesoresUCLM();
		} catch (SQLException e) {
			errorPopup("Error al cargar profesores", 1, director);
		}

		if(profesoresUCLMDao == null) return;
		for (int i = 0; i<profesoresUCLMDao.size(); i++) {
			ProfesorUCLM profesortemp = profesoresUCLMDao.get(i);
			profesoresUCLM.insertRow(i, new Object[] { profesortemp.getNombre(), profesortemp.categoria, profesortemp.isDoctor() });
			if(action >= 1 && cursoEditado.secretario != null && profesortemp.getDni().equals(cursoEditado.secretario.getDni())) secretarioEditado=i;
		}
	}

	private void addCentros(CursoPropio cursoEditado, int action, ProfesorUCLM director) {
		try {
			centrosDao = gestorConsultas.listarCentros();
		} catch (SQLException e) {
			errorPopup("Error al cargar centros", 1, director);
		}

		if(centrosDao == null) return;
		for (int i = 0; i<centrosDao.size(); i++) {
			Centro centrostemp = centrosDao.get(i);
			centros.add(i, centrostemp.getNombre());
			if(action >= 1 && cursoEditado.centro != null && centrostemp.getNombre().equals(cursoEditado.centro.getNombre())) centroEditado=i;
		}		
	}

	private void addMaterias(CursoPropio cursoEditado) {
		Collection<Materia> materiasEditadas = cursoEditado.materias;

		if(materiasEditadas == null) return;
		Iterator<Materia> ite = materiasEditadas.iterator();
		while(ite.hasNext()){
			Materia temp = ite.next();

			if(temp != null) {
				materias.addElement(temp.getNombre());
				materiasGuardadas.add(temp);
			}
		}
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

		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	}

	private void basicLayout(CursoPropio cursoEditado, int action) {
		// Titulo		
		label = new JLabel("Titulo de curso");
		label.setName("tituloLbl");
		label.setBounds(10,10,400,30);
		mainPanel.add(label);

		if (action >= 1 || cursoEditado == null) tituloCurso = new JTextField(cursoEditado.getNombre());
		else tituloCurso = new JTextField("");
		tituloCurso.setName("tituloBox");
		tituloCurso.setBounds(10,40,400,30);
		mainPanel.add(tituloCurso);

		// Profesor Secretario
		label = new JLabel("Profesor secretario");
		label.setName("secretarioLbl");
		label.setBounds(10,96,400,30);
		mainPanel.add(label);

		secretariosTable = new JTable(profesoresUCLM) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};

		secretariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		secretariosTable.setRowSelectionInterval(secretarioEditado, secretarioEditado);
		scrollPanel = new JScrollPane(secretariosTable);
		scrollPanel.setName("secretariosTable");
		scrollPanel.setBounds(10, 126, 400, 200);
		mainPanel.add(scrollPanel);

		// Fecha Inicio
		label = new JLabel("Fecha inicio");
		label.setName("fechaInicioLbl");
		label.setBounds(10,342,200,30);
		mainPanel.add(label);

		fechaInicioCurso = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateLabelFormatter());
		fechaInicioCurso.setName("fechaInicioCursoBox");
		fechaInicioCurso.setBounds(10,372,200,30);
		mainPanel.add(fechaInicioCurso);

		// Fecha Final
		label = new JLabel("Fecha final");
		label.setName("fechaInicioLbl");
		label.setBounds(220,342,200,30);
		mainPanel.add(label);

		fechaFinalCurso = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateLabelFormatter());
		fechaFinalCurso.setName("fechaFinalCursoBox");
		fechaFinalCurso.setBounds(220,372,200,30);
		mainPanel.add(fechaFinalCurso);

		// Fechas editadas
		if (action >= 1) {
			if(cursoEditado.getFechaInicio() != null) fechaInicioCurso.getJFormattedTextField().setText(format.format(cursoEditado.getFechaInicio()));
			if(cursoEditado.getFechaFin() != null) fechaFinalCurso.getJFormattedTextField().setText(format.format(cursoEditado.getFechaFin()));
		}

		// Edicion de curso
		if (action < 1) edicion = 1;
		else if (action == 1) edicion = cursoEditado.getEdicion();
		else if (action >= 1) edicion = cursoEditado.getEdicion() + 1;
		label = new JLabel("Edicion de curso: " + edicion);
		label.setName("edicionLbl");
		label.setBounds(450,40,200,30);
		mainPanel.add(label);

		// Tasa matricula
		label = new JLabel("Tasa Matricula");
		label.setName("tasaLbl");
		label.setBounds(450,90,200,30);
		mainPanel.add(label);

		if (action >= 1) tasaMatricula = new JTextField("" + cursoEditado.getTasaMatricula());
		else tasaMatricula = new JTextField("" );
		tasaMatricula.setName("tasaBox");
		tasaMatricula.setBounds(450,131,180,30);
		mainPanel.add(tasaMatricula);

		// Centro en el que se imparte
		label = new JLabel("Centro en el que se imparte");
		label.setName("centrosLbl");
		label.setBounds(10,413,400,30);
		mainPanel.add(label);

		centrosLista = new JList<>(centros);
		centrosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		centrosLista.setSelectedIndex(centroEditado); 
		scrollPanel = new JScrollPane(centrosLista);
		scrollPanel.setName("centrosLista");
		scrollPanel.setBounds(10, 440, 400, 200);
		mainPanel.add(scrollPanel);
	}

	private void ensenanzasLayout(CursoPropio cursoEditado, int action) {
		// Categoria 
		label = new JLabel("Categoria");
		label.setName("categoriaLbl");
		label.setBounds(10,651,400,30);
		mainPanel.add(label);

		categoriasLista = new JList<String>(categorias);
		categoriasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(categoriasLista);
		scrollPanel.setName("categoriasLista");
		scrollPanel.setBounds(10, 681, 400, 200);
		mainPanel.add(scrollPanel);

		// Requistio 
		labelRequisito = new JLabel("Requsito");
		labelRequisito.setName("requisitoLbl");
		labelRequisito.setBounds(450,681,200,30);
		mainPanel.add(labelRequisito);

		if (action >= 1 || cursoEditado.requisitos == null) requisitoCurso = new JTextField(cursoEditado.requisitos);
		else requisitoCurso = new JTextField("");
		requisitoCurso.setName("requisitoCursoBox");
		requisitoCurso.setBounds(450,711,200,30);
		requisitoCurso.setEnabled(false);
		mainPanel.add(requisitoCurso);

		// ECTS
		label = new JLabel("ECTS");
		label.setName("requisitoLbl");
		label.setBounds(450,761,200,30);
		mainPanel.add(label);

		ectsCurso = new JComboBox<>();
		ectsCurso.setName("ectsCursoBox");
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

		// Categoria + ECTS editado
		if (action >= 1 && cursoEditado.tipo != null) {
			boolean complete = false;
			for (int i = 0; i< tipos.length; i++) {
				if(tipos[i] == cursoEditado.tipo) {
					categoriasLista.setSelectedIndex(i);
					break;
				}
			}

			for (int i = 0; i < ectsCurso.getItemCount(); i++) {
				if (cursoEditado.getECTS() == ectsCurso.getItemAt(i)) {
					ectsCurso.setSelectedIndex(i); 
					complete = true;
					break;
				} 
			}

			if(!complete) {
				ectsCurso.addItem(cursoEditado.getECTS());
				ectsCurso.setSelectedIndex(ectsCurso.getItemCount()-1);
			}

		} else {
			categoriasLista.setSelectedIndex(0);
		}
	}

	private void materiasLayout(CursoPropio cursoEditado) {
		// Materias creadas
		label = new JLabel("Lista de materias creadas");
		label.setName("materiasLbl");
		label.setBounds(10,901,200,30);
		mainPanel.add(label);

		materiasLista = new JList<>(materias);
		materiasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel = new JScrollPane(materiasLista);
		scrollPanel.setName("materiasLista");
		scrollPanel.setBounds(10, 931, 400, 200);
		mainPanel.add(scrollPanel);


		// Boton para añadir materia
		addMateriaBto = new JButton("Añadir materia");
		addMateriaBto.setName("añadirMateriaBto");
		addMateriaBto.setBounds(450,931,200,30);
		mainPanel.add(addMateriaBto);

		addMateriaBto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { 
				boolean complete = true;

				// COMPROBACION DE DATOS MATERIA
				if (!testTexts(nombreMateria)) {
					complete = false;
				}

				if(responsablesTable.getSelectionModel().isSelectionEmpty()) {
					responsablesTable.setBackground(new Color(222, 129, 122));
					complete = false;
				}else {
					responsablesTable.setBackground(new Color(255, 255, 255));
				}

				Date inicioMateria = null;
				Date finalMateria = null;

				try {
					inicioMateria = format.parse(fechaInicioMateria.getJFormattedTextField().getText());
					fechaInicioMateria.getJFormattedTextField().setBackground(new Color(255, 255, 255));

				} catch (ParseException e1) {
					complete = false;
					fechaInicioMateria.getJFormattedTextField().setBackground(new Color(222, 129, 122));
				}  


				try {
					finalMateria = format.parse(fechaInicioMateria.getJFormattedTextField().getText());
					fechaFinalMateria.getJFormattedTextField().setBackground(new Color(255, 255, 255));
				} catch (ParseException e1) {
					complete = false;
					fechaFinalMateria.getJFormattedTextField().setBackground(new Color(222, 129, 122));
				}  

				if (complete) {
					materias.addElement(nombreMateria.getText());

					// CREAR MATERIA
					int index = responsablesTable.getSelectedRow();
					materia = new Materia(nombreMateria.getText(), horas.getItemAt(horas.getSelectedIndex()), inicioMateria, finalMateria, profesoresDao.get(index));
					materiasGuardadas.add(materia);

					// Limpiar selección
					nombreMateria.setText("");
					responsablesTable.clearSelection();
					fechaInicioMateria.getJFormattedTextField().setText("");
					fechaFinalMateria.getJFormattedTextField().setText("");
				}
			}
		});

		label = new JLabel("(Completar campos de abajo antes)");
		label.setName("completarCamposLbl");
		label.setBounds(450,961,300,30);
		mainPanel.add(label);

		// Boton para eliminar materia
		deleteMateriaBto = new JButton("Eliminar materia");
		deleteMateriaBto.setName("eliminarMateriaBto");
		deleteMateriaBto.setBounds(450,1021,200,30);
		mainPanel.add(deleteMateriaBto);

		deleteMateriaBto.addActionListener(new ActionListener() {

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
		label.setName("nombreMateriaLbl");
		label.setBounds(10,1141,180,30);
		mainPanel.add(label);

		nombreMateria = new JTextField();
		nombreMateria.setName("nombreMateriaBox");
		nombreMateria.setBounds(10,1171,180,30);
		mainPanel.add(nombreMateria);

		// Horas de materia
		label = new JLabel("Horas de materia");
		label.setName("horasMateriaLbl");
		label.setBounds(220,1141,180,30);
		mainPanel.add(label);

		horas = new JComboBox<>();
		horas.setName("horasMateriaBox");
		horas.setBounds(220,1171,180,30);

		for (int i = 1; i <= 100; i++) {
			horas.addItem(i);
		}

		mainPanel.add(horas);

		// Fecha Inicio Materia
		label = new JLabel("Fecha inicio");
		label.setName("fechaInicioMateriaLbl");
		label.setBounds(10,1210,200,30);
		mainPanel.add(label);

		fechaInicioMateria = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateLabelFormatter());
		fechaInicioMateria.setName("fechaInicioMateriaBox");
		fechaInicioMateria.setBounds(10,1240,200,30);
		mainPanel.add(fechaInicioMateria);

		// Fecha Final Materia
		label = new JLabel("Fecha final");
		label.setName("fechaFinalMateriaLbl");
		label.setBounds(220,1210,200,30);
		mainPanel.add(label);

		fechaFinalMateria = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateLabelFormatter());
		fechaFinalMateria.setName("fechaFinalMateriaBox");
		fechaFinalMateria.setBounds(220,1240,200,30);
		mainPanel.add(fechaFinalMateria);

		// Profesor responsable de materia
		label = new JLabel("Profesor responsable de materia");
		label.setName("responsableLbl");
		label.setBounds(10,1279,200,30);
		mainPanel.add(label);

		responsablesTable = new JTable(profesores) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};

		responsablesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsablesTable.setName("responsablesTable");
		scrollPanel = new JScrollPane(responsablesTable);
		scrollPanel.setBounds(10, 1309, 400, 200);
		mainPanel.add(scrollPanel);


	}

	private void botonesLayout(final ProfesorUCLM director, final CursoPropio cursoEditado, final int action) {
		// Boton para ir atras
		atrasBto = new JButton("Atras");
		atrasBto.setName("atrasBto");
		atrasBto.setBounds(270,1530,200,30);
		mainPanel.add(atrasBto);

		atrasBto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PantallaGestionarCursos(director);
					setVisible(false);
				} catch (NullPointerException ex) {
					errorPopup("Error director invalido", 2, director);
				}

			}
		});

		// Boton para enviar propuesta
		String cadenaBoton = "";

		if(action <= 1) cadenaBoton = "Enviar propuesta";
		else if (action == 1) cadenaBoton = "Editar propuesta";
		else if(action >= 1) cadenaBoton = "Nueva edición";


		sendBto = new JButton(cadenaBoton);
		sendBto.setName("enviarBto");
		sendBto.setBounds(500,1530,200,30);
		mainPanel.add(sendBto);

		sendBto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean complete = true;


				// COMPROBACION DE DATOS CURSO
				if (!testTexts(tituloCurso) & !testTexts(requisitoCurso)) {
					complete = false;
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

				double tasa = 0.0;
				try {
					tasa = Double.parseDouble(tasaMatricula.getText());  
					tasaMatricula.setBackground(new Color(255, 255, 255));
				}catch (NumberFormatException e1) {
					tasaMatricula.setBackground(new Color(222, 129, 122));
					complete = false;
				}

				Date fechaInicio = null;
				Date fechaFinal = null;

				try {
					fechaInicio = format.parse(fechaInicioCurso.getJFormattedTextField().getText());
					fechaInicioCurso.getJFormattedTextField().setBackground(new Color(255, 255, 255));
				} catch (ParseException e1) {
					fechaInicioCurso.getJFormattedTextField().setBackground(new Color(222, 129, 122));
					complete = false;
				}  	

				try {
					fechaFinal = format.parse(fechaFinalCurso.getJFormattedTextField().getText());
					fechaFinalCurso.getJFormattedTextField().setBackground(new Color(255, 255, 255));
				} catch (ParseException e1) {
					fechaFinalCurso.getJFormattedTextField().setBackground(new Color(222, 129, 122));
					complete = false;
				}  		

				if (!complete) return;


				int confirm = JOptionPane.showConfirmDialog(null,"¿Enviar propuesta?","Enviar propuesta",JOptionPane.YES_NO_OPTION, 1);

				if(confirm == 0)  {
					String id;
					if(action == 1) id = cursoEditado.getId();
					else id = GestorMD5.getMd5(tituloCurso.getText() + fechaInicio.toString() + fechaFinal.toString());

					// CREAR CURSO
					curso = new CursoPropio(
							id,
							tituloCurso.getText(), 
							ectsCurso.getItemAt(ectsCurso.getSelectedIndex()), 
							fechaInicio, 
							fechaFinal, 
							tasa,
							edicion, // Edicion
							EstadoCurso.PROPUESTO, 
							tipos[categoriasLista.getSelectedIndex()], 
							centrosDao.get(centrosLista.getSelectedIndex()), 
							profesoresUCLMDao.get(secretariosTable.getSelectedRow()), 
							director,
							requisitoCurso.getText()
							);

					curso.materias = new ArrayList<Materia>();
					curso.materias = materiasGuardadas;
					System.out.println(materiasGuardadas);

					if (action == 1) { // Editar curso
						try {
							gestorPropuestas.editarPropuestaCurso(curso);
						} catch (Exception e1) {
							errorPopup("Error al editar curso", 1, director);
						}
					} else { // Crear + Nueva edicion
						try {
							gestorPropuestas.realizarPropuestaCurso(curso);
						} catch (Exception e1) {
							errorPopup("Error al crear curso", 1, director);
							e1.printStackTrace();
						}
					}

					new PantallaGestionarCursos(director);
					setVisible(false);
				}
			}
		});
	}

	public boolean testTexts(JTextField text) {
		boolean result = true;

		if (text.getText().equals("")) {
			text.setBackground(new Color(222, 129, 122));
			result = false;
		} else {
			text.setBackground(new Color(255, 255, 255));
		}

		return result;
	}

	public void errorPopup(String mensaje, int tipoError, ProfesorUCLM director) {
		JOptionPane.showMessageDialog(null, mensaje);
		if(tipoError == 1) {
			try {
				new PantallaGestionarCursos(director);
				setVisible(false);
			} catch (NullPointerException e) {
				errorPopup("Error director invalido", 2, director);
			}
		} else {
			new PantallaLogin();
			setVisible(false);
		}
	}
}