package presentacion;

import javax.swing.JFrame;

import negocio.controllers.GestorPropuestasCursos;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Materia;
import negocio.entities.ProfesorUCLM;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.util.Iterator;

@Generated
public class PantallaDatosCurso extends JFrame {
	private DefaultTableModel materiasCurso = new DefaultTableModel(); 

	JTextArea descripcion;
	JRadioButton aceptarPropuesta;
	JRadioButton rechazarPropuesta;


	public PantallaDatosCurso (int type, ProfesorUCLM director, CursoPropio curso) {
		initLayout();
		basicLayout(type, director, curso);
		tablaMaterias(curso);

		if (type == 2)	evaluarCurso();

		botonesLayout(type, director, curso);
	}

	private void initLayout() {
		setBounds(10, 10, 800,600);
		setTitle("Gestion de cursos propuestos");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void basicLayout(final int type, final ProfesorUCLM director, final CursoPropio curso) {
		// Propiedades basicas
		getContentPane().setLayout(null);

		JLabel tasaMatricula = new JLabel("Centro: " + curso.centro.getNombre());
		tasaMatricula.setBounds(43, 75, 200, 14);
		getContentPane().add(tasaMatricula);

		JLabel tituloCurso = new JLabel("Titulo: " + curso.getNombre());
		tituloCurso.setBounds(43, 27, 250, 14);
		getContentPane().add(tituloCurso);

		JLabel precio = new JLabel("Precio: " + curso.getTasaMatricula());
		precio.setBounds(43, 175, 200, 14);
		getContentPane().add(precio);

		JLabel creditos = new JLabel("Creditos: " + curso.getECTS());
		creditos.setBounds(43, 125, 200, 14);
		getContentPane().add(creditos);

		JLabel directorLabel = new JLabel("Profesor Director: " + curso.director.getNombre());
		directorLabel.setBounds(43, 100, 200, 14);
		getContentPane().add(directorLabel);

		JLabel fechaInicio = new JLabel("Fecha Inicio: " + curso.getFechaInicio());
		fechaInicio.setBounds(43, 200, 200, 14);
		getContentPane().add(fechaInicio);

		JLabel edicion = new JLabel("Edicion: " + curso.getEdicion());
		edicion.setBounds(43, 52, 200, 14);
		getContentPane().add(edicion);

		JLabel categoria = new JLabel("Categoria: " + curso.tipo);
		categoria.setBounds(43, 150, 200, 14);
		getContentPane().add(categoria);

		JLabel fechaFin = new JLabel("Fecha Fin: " + curso.getFechaFin());
		fechaFin.setBounds(43, 225, 200, 14);
		getContentPane().add(fechaFin);

		JLabel materiasLabel = new JLabel("Materias");
		materiasLabel.setBounds(473, 27, 53, 14);
		getContentPane().add(materiasLabel);
	}

	private void evaluarCurso(){
		descripcion = new JTextArea();
		descripcion.setBounds(306, 300, 400, 163);
		getContentPane().add(descripcion);

		aceptarPropuesta = new JRadioButton("Aceptar propuesta");
		aceptarPropuesta.setBounds(125, 301, 157, 23);
		getContentPane().add(aceptarPropuesta);

		rechazarPropuesta = new JRadioButton("Rechazar Propuesta");
		rechazarPropuesta.setBounds(125, 334, 157, 23);
		getContentPane().add(rechazarPropuesta);

		ButtonGroup group = new ButtonGroup();
		group.add(aceptarPropuesta);
		group.add(rechazarPropuesta);

		aceptarPropuesta.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				descripcion.setEnabled(false);
				descripcion.setText("");
				descripcion.setBackground(Color.lightGray);
			}
		});

		rechazarPropuesta.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				descripcion.setEnabled(true);
				descripcion.setBackground(Color.white);
			}
		});
	}

	private void tablaMaterias(CursoPropio curso) {
		materiasCurso.addColumn("Nombre");
		materiasCurso.addColumn("Responsable");
		materiasCurso.addColumn("Horas");
		materiasCurso.addColumn("Fecha Inicio");
		materiasCurso.addColumn("Fecha Fin");
		
		Collection<Materia> materias = curso.materias;
		if (materias!=null) {
			Iterator<Materia> ite = materias.iterator();
			while(ite.hasNext()){
				Materia temp = ite.next();
				materiasCurso.addRow(new Object[] { temp.getNombre(), temp.responsable, temp.getHoras(), temp.getFechaInicio() ,temp.getFechaFin() });
			}
		}


		JTable materiasTable = new JTable(materiasCurso){
			@Generated @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};

		materiasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollLista = new JScrollPane(materiasTable);
		scrollLista.setBounds(306, 51, 400, 188);
		getContentPane().add(scrollLista);
	}

	private void botonesLayout(final int type, final ProfesorUCLM director, final CursoPropio curso) {
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				if (type == 0 || type == 1) new PantallaCursosAprobados(type, director);
				else new PantallaEmpleadosVicerrectorado();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 513, 200,30);
		getContentPane().add(btnNewButton);

		JButton btnMatricularEvaluar = new JButton("Matricular");
		btnMatricularEvaluar.addActionListener(new ActionListener() {
			@Generated @Override
			public void actionPerformed(ActionEvent e) {
				
				int confirm = 1;

				if (type == 0) { 
					confirm = JOptionPane.showConfirmDialog(null,"¿Matricular?","Matricular en curso",JOptionPane.YES_NO_OPTION, 1);
				} else { 
					confirm = JOptionPane.showConfirmDialog(null,"¿Evaluar propuesta?","Evaluar propuesta",JOptionPane.YES_NO_OPTION, 1);
				}
				
				if(confirm == 0)  {
					if (type == 0) {
						new PantallaMatriculacion(director, curso); //aqui hay que meter alumno
		                setVisible(false);
					}	
					else {  // Evaluar
						
						if (!evaluarPropuestaCorrecto(type)) return;
						
						GestorPropuestasCursos gestor = new GestorPropuestasCursos();
						if (aceptarPropuesta.isSelected()) curso.estado = EstadoCurso.VALIDADO;
						else curso.estado = EstadoCurso.PROPUESTA_RECHAZADA;
						
						try {
							gestor.editarPropuestaCurso(curso);
						} catch (Exception e1) {
							e1.printStackTrace();
							
						}
						
						new PantallaEmpleadosVicerrectorado();
						setVisible(false);
					}
					
				}
			}
		});
		btnMatricularEvaluar.setBounds(574, 513, 200,30);
		getContentPane().add(btnMatricularEvaluar);

		// CAMBIOS DE BOTONES
		if (type == 1) btnMatricularEvaluar.setVisible(false);
		else if (type == 2) btnMatricularEvaluar.setText("Evaluar");		
	}
	
	public boolean evaluarPropuestaCorrecto(final int type) {
		boolean result = true;
		if (!aceptarPropuesta.isSelected() && !rechazarPropuesta.isSelected()) {
			aceptarPropuesta.setBackground(new Color(222, 129, 122));
			rechazarPropuesta.setBackground(new Color(222, 129, 122));
			result = false;
		}else {
			aceptarPropuesta.setBackground(getBackground());
			rechazarPropuesta.setBackground(getBackground());
		}
		
		if (type == 2 && rechazarPropuesta.isSelected()) {
			if (descripcion.getText().equals("")) {
				descripcion.setBackground(new Color(222, 129, 122));
				result = false;
			} else {
				descripcion.setBackground(new Color(255, 255, 255));
			}
		}
		
		return result;
	}
}
