package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Materia;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;

public class PantallaEditarCurso extends JFrame {
	// Variables generales
		private JButton button;
		private JLabel label, labelRequisito;
		private JPanel mainPanel, panel;
		private JScrollPane scrollPanel;    
		private JTextField tituloCurso, tasaMatricula, requisitoCurso, nombreMateria;
		private JComboBox<Integer> ectsCurso, horas;
		private JComboBox diaInicio, mesInicio, anoInicio, diaFinal, mesFinal, anoFinal, diaInicioMateria, mesInicioMateria, anoInicioMateria, diaFinalMateria, mesFinalMateria, anoFinalMateria;  

		// Listas
		private String[] categorias = {"Másteres de Formación Permanente", "Especialistas", "Expertos", 
				"Cursos Universitarios de Formación Avnazada", "Cursos de Formación Continua", 
				"Microcredenciales", "Actividades formativas de corta duración", "Cursos de Verano y Extensión Universitaria", "Formación de Mayores" }; 

		private TipoCurso[] tipos = {TipoCurso.MASTER, TipoCurso.ESPECIALISTA, TipoCurso.EXPERTO, 
				TipoCurso.FORMACION_AVANZADA, TipoCurso.FORMACION_CONTINUA,
				TipoCurso.MICROCREDENCIALES, TipoCurso.CORTA_DURACION, TipoCurso.VERANO, TipoCurso.MAYORES};
		
		private DefaultListModel materias = new DefaultListModel(); 
		
		List centrosDao = null;
		private DefaultListModel centros = new DefaultListModel(); 
		
		List profesoresDao = null;
		private DefaultTableModel profesores = new DefaultTableModel(); 

		List profesoresUCLMDao = null;
		private DefaultTableModel profesoresUCLM = new DefaultTableModel(); 
		
		private JList centrosLista, categoriasLista, materiasLista;
		private JTable secretariosTable, responsablesTable;

		// Objetos
		private Materia materia;
		private List<Materia> materiasGuardadas = new ArrayList();
		private int idMateria = 0;
		private CursoPropio curso = new CursoPropio();
		
		
		// Edicion
		private int secretarioEditado, centroEditado, categoriaEditado;

		public PantallaEditarCurso (ProfesorUCLM director, CursoPropio cursoEditando) {
			addProfesores();
			addProfesoresUCLM(cursoEditando);
			addCentros(cursoEditando);
			initLayout();
			basicLayout(cursoEditando);
			ensenanzasLayout(cursoEditando);
			materiasLayout(cursoEditando);
			botonesLayout(director, cursoEditando);

			scrollPanel = new JScrollPane(mainPanel);
			scrollPanel.setBounds(0, 0, 0,0);
			getContentPane().add(scrollPanel);
		}

		private void addProfesores() { 
			Profesor profesor = new Profesor();

			profesores.addColumn("Nombre");
			profesores.addColumn("Doctor");

			try {
				profesoresDao = profesor.profesorDao.listarProfesores();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i<profesoresDao.size(); i++) {
				Profesor profesortemp = (Profesor) profesoresDao.get(i);
				profesores.insertRow(i, new Object[] { profesortemp.getNombre(), profesortemp.isDoctor() });
			}
		}

		private void addProfesoresUCLM(CursoPropio cursoEditando) {
			ProfesorUCLM profesor = new ProfesorUCLM();

			profesoresUCLM.addColumn("Nombre");
			profesoresUCLM.addColumn("Categoria");
			profesoresUCLM.addColumn("Doctor");

			try {
				profesoresUCLMDao = profesor.profesorUCLMDao.listarProfesores();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i<profesoresUCLMDao.size(); i++) {
				ProfesorUCLM profesortemp = (ProfesorUCLM) profesoresUCLMDao.get(i);
				profesoresUCLM.insertRow(i, new Object[] { profesortemp.getNombre(), profesortemp.categoria, profesortemp.isDoctor() });
				if(profesortemp.getDni().equals(cursoEditando.secretario.getDni())) secretarioEditado=i;
			}
		}
		
		private void addCentros(CursoPropio cursoEditando) {
			Centro centro = new Centro();

			try {
				centrosDao = centro.centroDao.listarCentros();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i<centrosDao.size(); i++) {
				Centro centrostemp = (Centro) centrosDao.get(i);
				centros.add(i, centrostemp.getNombre());;
				if(centrostemp.getNombre().equals(cursoEditando.centro.getNombre())) centroEditado=i;

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
		}

		private void basicLayout(CursoPropio cursoEditando) {
			// Titulo		
			label = new JLabel("Titulo de curso");
			label.setBounds(10,10,400,30);
			mainPanel.add(label);

			tituloCurso = new JTextField(cursoEditando.getNombre());
			tituloCurso.setBounds(10,40,400,30);
			mainPanel.add(tituloCurso);

			// Profesor Secretario
			label = new JLabel("Profesor secretario");
			label.setBounds(10,96,400,30);
			mainPanel.add(label);

			secretariosTable = new JTable(profesoresUCLM) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false; //Disallow the editing of any cell
				}
			};
						
			secretariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			secretariosTable.setRowSelectionInterval(secretarioEditado, secretarioEditado);
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
			label = new JLabel("Edicion de curso: " + cursoEditando.getEdicion());
			label.setBounds(450,40,200,30);
			mainPanel.add(label);

			// Tasa matricula
			label = new JLabel("Tasa Matricula");
			label.setBounds(450,90,200,30);
			mainPanel.add(label);

			tasaMatricula = new JTextField(""+cursoEditando.getTasaMatricula());
			tasaMatricula.setBounds(450,131,180,30);
			mainPanel.add(tasaMatricula);


			// Centro en el que se imparte
			label = new JLabel("Centro en el que se imparte");
			label.setBounds(10,413,400,30);
			mainPanel.add(label);

			centrosLista = new JList<>(centros);
			centrosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			centrosLista.setSelectedIndex(centroEditado); 
			scrollPanel = new JScrollPane(centrosLista);
			scrollPanel.setBounds(10, 440, 400, 200);
			mainPanel.add(scrollPanel);
		}

		private void ensenanzasLayout(CursoPropio cursoEditando) {
			
			for(int i=0;i<tipos.length;i++) if(cursoEditando.tipo.equals(tipos[i])) categoriaEditado=i;

			// Categoria 
			label = new JLabel("Categoria");
			label.setBounds(10,651,400,30);
			mainPanel.add(label);

			categoriasLista = new JList(categorias);
			categoriasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			categoriasLista.setSelectedIndex(categoriaEditado);
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

		private void materiasLayout(CursoPropio cursoEditando) {
			// Materias creadas
			label = new JLabel("Lista de materias creadas");
			label.setBounds(10,901,200,30);
			mainPanel.add(label);

			materiasLista = new JList(materias);
			materiasLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPanel = new JScrollPane(materiasLista);
			scrollPanel.setBounds(10, 931, 400, 200);
			mainPanel.add(scrollPanel);

			// Boton para anadir materia
			button = new JButton("Anadir materia");
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

						int index = responsablesTable.getSelectedRow();
						materia = new Materia(nombreMateria.getText(), horas.getItemAt(horas.getSelectedIndex()), inicioMateria, finMateria, (Profesor) profesoresDao.get(index));
						materiasGuardadas.add(materia);

						// Limpiar selección
						nombreMateria.setText("");
						responsablesTable.clearSelection();
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

		private void botonesLayout(final ProfesorUCLM director, final CursoPropio cursoEditando) {
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

					if (tasaMatricula.getText().equals("")) {
						tasaMatricula.setBackground(new Color(222, 129, 122));
						complete = false;
					} else {
						boolean isNumber = true;
						try {
					        double d = Double.parseDouble(tasaMatricula.getText());
					    } catch (NumberFormatException nfe) {
					    	tasaMatricula.setBackground(new Color(222, 129, 122));
					    	isNumber = false;
					    }
						
						if(isNumber) {
							tasaMatricula.setBackground(new Color(255, 255, 255));
						} else {
							complete = false;
						}
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
							e1.printStackTrace();
						}  
						
						List allCursos = null;
						
						try {
							allCursos = curso.cursoPropioDao.listarCursos();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}					
						
						String id = String.valueOf(allCursos.size());

						curso = new CursoPropio(
								cursoEditando.getId(),
								tituloCurso.getText(), 
								ectsCurso.getItemAt(ectsCurso.getSelectedIndex()), 
								fechaInicio, 
								fechaFin, 
								Double.parseDouble(tasaMatricula.getText()),
								1, // Edicion
								EstadoCurso.PROPUESTO, 
								tipos[categoriasLista.getSelectedIndex()], 
								(Centro) centrosDao.get(centrosLista.getSelectedIndex()), 
								(ProfesorUCLM) profesoresUCLMDao.get(secretariosTable.getSelectedRow()), 
								director 
								);

						curso.materias = new ArrayList<>();
						curso.materias.addAll(materiasGuardadas);
		
						GestorPropuestasCursos gestor = new GestorPropuestasCursos();
						try {
							gestor.editarPropuestaCurso(cursoEditando);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						System.out.println("Curso editado: " + curso.getNombre());
					}
				}
			});
		}
	}